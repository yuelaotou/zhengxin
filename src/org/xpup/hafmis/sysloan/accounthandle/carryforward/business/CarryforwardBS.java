package org.xpup.hafmis.sysloan.accounthandle.carryforward.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface.ICarryforwardBS;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.dto.CarryforwardTbDTO;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardTbShowAF;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBank;

public class CarryforwardBS implements ICarryforwardBS {

  private RestoreLoanDAO restoreLoanDAO=null;//还款计划表pl201
  
  private BorrowerAccDAO borrowerAccDAO=null;//pl111
  
  private CollBankDAO collBankDAO=null;     //转换银行名称
  
  private LoanBankDAO loanBankDAO=null;    //查找是否年节pl002 

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }
  //查找显示需要年结的记录
  public CarryforwardShowAF queryBorrowerAccList(Pagination pagination,
      SecurityInfo securityInfo)throws BusinessException{
    CarryforwardShowAF carryforwardShowAF=new CarryforwardShowAF();
    try{
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String loanBankId=(String)pagination.getQueryCriterions().get("loanBankId");        //放宽银行
      List list=new ArrayList();
      String contractSt="11";                     //业务状态是还款中
      String bizdate=securityInfo.getUserInfo().getPlbizDate(); //业务日期
      String tempbizdate=bizdate.substring(4, 6);
      String tempyear=bizdate.substring(0, 4);  //查看结转年度是否存在
      if(!tempbizdate.equals("12")){
        throw new BusinessException("年节时间只能为十二月，请核实操作日期！");
      }  
      String yearClear="";
      yearClear=loanBankDAO.queryYearClearByBankId_sy(loanBankId);
      if(yearClear.equals(tempyear)){
       throw new BusinessException("此银行已经年终结转，不能再次操作！");
       }
      String templastyear=new Integer(tempyear).intValue()-1+"";
      if(!templastyear.equals(yearClear)){
        throw new BusinessException("年终结转时间不正确！");
        }
      String loanKouYearmonth=bizdate.substring(0, 4)+"12"; //屏蔽掉没有现一年的还款记录的合同编号
      CollBank  collBank=collBankDAO.getCollBankByCollBankid_(loanBankId);               //查找出对应的银行名称用于前台显示
      list=borrowerAccDAO.queryBorrowerAccList_sy(contractSt, loanBankId, loanKouYearmonth, orderBy, order, start, pageSize, securityInfo,collBank.getCollBankName());
      carryforwardShowAF.setLoanBankId(loanBankId);
      carryforwardShowAF.setList(list);
//      String pl111id="";
      int count=0;
      List countlist=new ArrayList();
      countlist=borrowerAccDAO.countBorrowerAccList_sy(contractSt, loanBankId, loanKouYearmonth, securityInfo);
//      if(!countlist.isEmpty()){
//        for(int i=0;i<countlist.size();i++){
//          String temp_pl111id = "";
//          temp_pl111id = countlist.get(i) + "";
//          pl111id += temp_pl111id + ",";
//        }
//      }
//      pl111id=pl111id.substring(0, pl111id.lastIndexOf(","));
//      carryforwardShowAF.setPl111id(pl111id);
      if(!countlist.isEmpty())
      count=countlist.size();
      pagination.setNrOfElements(count);
    }catch(BusinessException bx){
      throw bx;
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return carryforwardShowAF;
  }
  public String useCarryforward(String loanBankId,SecurityInfo securityInfo)throws BusinessException{
    String info="";
    try{
      String bizdate=securityInfo.getUserInfo().getPlbizDate(); //会计日期
      String ip=securityInfo.getUserInfo().getUserIp(); //ip
      String operson=securityInfo.getUserInfo().getUsername(); //操作员
      restoreLoanDAO.useCarryforward_sy(loanBankId, bizdate, ip, operson);
      info="pass";
    }catch(Exception e){
      throw new BusinessException("年终结转失败！");
    }
    return info;
  }
  //以银行为主的时候，进行验证的查询
  public String queryCarrayforwardInfo(String loanBankId,
      SecurityInfo securityInfo)throws BusinessException{
    String massageinfo="";
    try{
      String bizdate=securityInfo.getUserInfo().getPlbizDate(); //业务日期
      String tempbizdate=bizdate.substring(4, 6);
      String tempyear=bizdate.substring(0, 4);  //查看结转年度是否存在
      if(!tempbizdate.equals("12")){
        throw new BusinessException("年节时间只能为十二月，请核实操作日期！");
      }
      String yearClear="";
      yearClear=loanBankDAO.queryYearClearByBankId_sy(loanBankId);
      if(yearClear.equals(tempyear)){
       throw new BusinessException("此银行已经年终结转，不能再次操作！");
       }
      String templastyear=new Integer(tempyear).intValue()-1+"";
      if(!templastyear.equals(yearClear)){
        throw new BusinessException("年终结转时间不正确！");
        }
      massageinfo="pass";
    }catch(BusinessException bx){
      throw bx;
    }
    return massageinfo;
  }
  //年终结转以银行为主的时候使用。
  public String useBankCarryforward(String loanBankId,
      SecurityInfo securityInfo) throws BusinessException{
    String info="";
    try{
      String bizdate=securityInfo.getUserInfo().getPlbizDate().substring(0, 4); //会计日期
      restoreLoanDAO.useBankCarryforward_sy(loanBankId, bizdate);
      info="pass";
    }catch(Exception e){
      throw new BusinessException("年终结转失败！");
    }
    return info;
  }
}
