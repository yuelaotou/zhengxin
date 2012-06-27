package org.xpup.hafmis.sysfinance.treasurermng.accountclear.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnBizActivityLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumCancelDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumMaintainDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.TreasurerCredence;
import org.xpup.hafmis.sysfinance.treasurermng.accountclear.bsinterface.IAccountClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;

public class AccountClearBS implements IAccountClearBS{
  private BookParameterDAO bookParameterDAO=null;
  private TreasurerCredenceDAO treasurerCredenceDAO=null;
  private FnDocNumMaintainDAO fnDocNumMaintainDAO=null;
  private FnDocNumCancelDAO fnDocNumCancelDAO=null;
  private FnOperateLogDAO fnOperateLogDAO=null;
  private FnBizActivityLogDAO fnBizActivityLogDAO=null;
  private AccountantCredenceDAO accountantCredenceDAO=null;
  /**
   * 出纳扎账
   * @author 郭婧平
   * 2007-11-05
   * 查询fn102表里paramExplain字段的数据
   * 查询条件：paramNum
   */
  public List findSummaryList(SecurityInfo securityInfo){
    List summrayList=new ArrayList();
    try{
      summrayList=bookParameterDAO.getParamExplain("4","10", securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return summrayList;
  }
  /**
   * 出纳扎账
   * @author 郭婧平
   * 2007-11-05
   * 显示页面的数据
   */
  public Object[] findAccountClearList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object obj[]=new Object[3];
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
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
      List list=treasurerCredenceDAO.queryCashDayClearTcList(cashDayClearTcFindDTO, officeList1,"", securityInfo, orderBy, order, start, pageSize, page);
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
      List countList=treasurerCredenceDAO.queryCashDayClearTcListCount(cashDayClearTcFindDTO, officeList1,"", securityInfo);
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
      obj[2]=countList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 出纳扎账--扎账
   * @author 郭婧平
   * 2007-11-06
   * 扎账按钮
   */
  public void account(String[] rowArray,SecurityInfo securityInfo) throws Exception{
    try{
      for(int i=0;i<rowArray.length;i++){
        String credenceId=rowArray[i];
        //判断是否CREDENCE_ST=0
        List checkList=treasurerCredenceDAO.queryTreasurerCredenceByCredenceSt(credenceId);
        if(checkList.size()==0){
          throw new BusinessException("该记录已记账，不能再扎账！");
        }
        
        String office="";
        String credenceNum="";
        String credenceDate="";
        String acredenceId="";
        TreasurerCredence treasurerCredence=treasurerCredenceDAO.queryAcredenceIdByCredenceId(credenceId);
        if(treasurerCredence!=null){
          office=treasurerCredence.getOffice();
          credenceNum=treasurerCredence.getCredenceNum();
          credenceDate=treasurerCredence.getCredenceDate();
          acredenceId=treasurerCredence.getAcredenceId();
        }
        //判断作废凭证号表 FN302办事处代码and凭证类型= 1.出纳凭证是否存在记录
        String credenceNumMin=fnDocNumMaintainDAO.getDocNumCanceldocNum(office, null, "1", securityInfo.getBookId());
        if(credenceNumMin!=null){
          //凭证号管理表 FN301最大凭证号,条件是办事处代码and凭证类型= 1.出纳凭证
          String credenceNumMax=fnDocNumMaintainDAO.getDocumentNumberMax(office, null, "1", securityInfo.getBookId());
          //取出作废凭证号表 FN302办事处代码and凭证类型= 1.出纳凭证存在记录的个数
          int count=fnDocNumCancelDAO.getDocNumCanceldocNumCount(office,null,"1",securityInfo.getBookId());
          //判断记录中最小的凭证号是否大于(凭证号管理表 FN301.DOC_NUM-FN302凭证号的个数,条件是办事处代码and凭证类型= 1.出纳凭证)
          if(Integer.parseInt(credenceNumMin)>(Integer.parseInt(credenceNumMax)-count)){
            if(acredenceId!=null){
              //更新fn201表中的cash_acc_st字段为2
              treasurerCredenceDAO.updateAccountantCredence(acredenceId,"2");
              //更新fn201表中的bank_acc_st字段为2
              treasurerCredenceDAO.updateBankAccountantCredence(acredenceId,"2");
            }
            //更新该条记录
            treasurerCredenceDAO.updateCredenceSt(credenceId, securityInfo.getUserName());
            //插入fn310表
            FnBizActivityLog fnBizActivityLog=new FnBizActivityLog();
            fnBizActivityLog.setCredenceNum(credenceNum);
            fnBizActivityLog.setCredenceType("1");
            fnBizActivityLog.setCredenceDate(credenceDate);
            fnBizActivityLog.setOffice(office);
            fnBizActivityLog.setAction("2");
            fnBizActivityLog.setOpTime(new Date());
            fnBizActivityLog.setOperator(securityInfo.getUserName());
            fnBizActivityLog.setBookId(securityInfo.getBookId());
            fnBizActivityLogDAO.insert(fnBizActivityLog);
          }else{
            throw new BusinessException("扎账失败!");
          }
        }else{
          if(acredenceId!=null){
            //更新fn201表中的cash_acc_st字段为2
            treasurerCredenceDAO.updateAccountantCredence(acredenceId,"2");
            //更新fn201表中的bank_acc_st字段为2
            treasurerCredenceDAO.updateBankAccountantCredence(acredenceId,"2");
          }
          //更新该条记录
          treasurerCredenceDAO.updateCredenceSt(credenceId, securityInfo.getUserName());
          
          //插入fn310表
          FnBizActivityLog fnBizActivityLog=new FnBizActivityLog();
          fnBizActivityLog.setCredenceNum(credenceNum);
          fnBizActivityLog.setCredenceType("1");
          fnBizActivityLog.setCredenceDate(credenceDate);
          fnBizActivityLog.setOffice(office);
          fnBizActivityLog.setAction("2");
          fnBizActivityLog.setOpTime(new Date());
          fnBizActivityLog.setOperator(securityInfo.getUserName());
          fnBizActivityLog.setBookId(securityInfo.getBookId());
          fnBizActivityLogDAO.insert(fnBizActivityLog);
        }
      }
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CLEARACCOUNT));
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ACCOUNTIN));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("扎账失败!");
    }
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }
  public void setFnDocNumMaintainDAO(FnDocNumMaintainDAO fnDocNumMaintainDAO) {
    this.fnDocNumMaintainDAO = fnDocNumMaintainDAO;
  }
  public void setFnDocNumCancelDAO(FnDocNumCancelDAO fnDocNumCancelDAO) {
    this.fnDocNumCancelDAO = fnDocNumCancelDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  public void setFnBizActivityLogDAO(FnBizActivityLogDAO fnBizActivityLogDAO) {
    this.fnBizActivityLogDAO = fnBizActivityLogDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }
}
