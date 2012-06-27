package org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.bsinterface.ICashDayClearReportBS;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;

public class CashDayClearReportBS implements ICashDayClearReportBS{
  private BookParameterDAO bookParameterDAO=null;
  private TreasurerCredenceDAO treasurerCredenceDAO=null;
  private AccountantCredenceDAO accountantCredenceDAO=null;
  /**
   * 账簿管理--现金日记账
   * @author 郭婧平
   * 2007-11-07
   * 查询fn102表里paramExplain字段的数据
   * 查询条件：paramNum
   */
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo){
    Object[] obj=new Object[3];
    List credenceCharacterList=null;
    List summrayList=null;
    List settTypeList=null;
    try{
      summrayList=bookParameterDAO.getParamExplain("4","10", securityInfo);
      credenceCharacterList=bookParameterDAO.getParamExplain("2","", securityInfo);
      settTypeList=bookParameterDAO.getParamExplain("3","", securityInfo);
      obj[0]=credenceCharacterList;
      obj[1]=summrayList;
      obj[2]=settTypeList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 账簿管理--现金日记账
   * @author 郭婧平
   * 2007-11-07
   * 显示页面的数据
   */
  public Object[] findCashDayClearReportList(String credenceType,Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object obj[]=new Object[2];
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
        cashDayClearTcFindDTO.setType("3");
      }else{
        cashDayClearTcFindDTO.setType("3");
      }
      List officeList1 = null;
      try {
        // 取出用户权限办事处
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      String orderBy=(String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize(); 
      int page = pagination.getPage();
      List list=treasurerCredenceDAO.queryCashDayClearTcList(cashDayClearTcFindDTO, officeList1,credenceType, securityInfo, orderBy, order, start, pageSize, page);
      List resultList=new ArrayList();
      if(list.size()>0){
        for(int i=0;i<list.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)list.get(i);
          cashDayClearTcShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer.parseInt(cashDayClearTcShowListDTO.getCredenceSt()), BusiConst.CREDSTATE));
          if((!cashDayClearTcShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTcShowListDTO.getCredenceNum().equals(""))){
            cashDayClearTcShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getCredenceCharacter())+"-"+
                cashDayClearTcShowListDTO.getCredenceNum());
          }else if((!cashDayClearTcShowListDTO.getCredenceNum().equals(""))&&cashDayClearTcShowListDTO.getCredenceCharacter().equals("")){
            cashDayClearTcShowListDTO.setCredenceChaNum(cashDayClearTcShowListDTO.getCredenceNum());
          }
          if(!cashDayClearTcShowListDTO.getAcredenceId().equals("")){
            Object[] object=accountantCredenceDAO.queryByCredenceId(cashDayClearTcShowListDTO.getAcredenceId());
            cashDayClearTcShowListDTO.setTemp_credenceChaNum(cashDayClearTcShowListDTO.getCredenceCharacter()+"-"+
                object[0].toString());
            cashDayClearTcShowListDTO.setOffice(object[1].toString());
          }
          if(!cashDayClearTcShowListDTO.getSummary().equals("")){
            cashDayClearTcShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSummary()));
          }
          if(!cashDayClearTcShowListDTO.getSettType().equals("")){
            cashDayClearTcShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSettType()));
          }
          resultList.add(cashDayClearTcShowListDTO);
        }
      }
      List countList=treasurerCredenceDAO.queryCashDayClearTcListCount(cashDayClearTcFindDTO, officeList1,credenceType, securityInfo);
      BigDecimal debitSum=new BigDecimal(0.00);
      BigDecimal creditSum=new BigDecimal(0.00);
      if(countList.size()>0){
        for(int i=0;i<countList.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)countList.get(i);
          debitSum=debitSum.add(cashDayClearTcShowListDTO.getDebit());
          creditSum=creditSum.add(cashDayClearTcShowListDTO.getCredit());
        }
      }
      int count=countList.size();
      pagination.setNrOfElements(count);
      cashDayClearTcFindDTO.setDebitSum(debitSum);
      cashDayClearTcFindDTO.setCreditSum(creditSum);
      obj[0]=resultList;
      obj[1]=cashDayClearTcFindDTO;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }
}
