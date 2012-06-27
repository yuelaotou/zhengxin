package org.xpup.hafmis.sysloan.loanapply.loandelete.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.AssurerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractChgDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.GatheringAccDAO;
import org.xpup.hafmis.sysloan.common.dao.GiveAccDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.ImpawnContractDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractDAO;
import org.xpup.hafmis.sysloan.common.dao.OverdueInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractNumCancel;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.loandelete.bsinterface.ILoandeleteBS;
import org.xpup.security.common.domain.Userslogincollbank;

public class LoandeleteBS implements ILoandeleteBS {

  private BorrowerDAO borrowerDAO = null;// PL110

  private BorrowerAccDAO borrowerAccDAO = null;// PL111

  private LoanContractDAO loanContractDAO = null;// PL120

  private PledgeContractDAO pledgeContractDAO = null;// PL121

  private ImpawnContractDAO impawnContractDAO = null;// PL122

  private AssurerDAO assurerDAO = null;// PL123

  private PlOperateLogDAO plOperateLogDAO = null;// ÈÕÖ¾

  private ContractNumCancelDAO contractNumCancelDAO = null;// PL102 ×÷·ÏºÏÍ¬±í

  //
  private AssistantBorrowerDAO assistantBorrowerDAO = null;// PL113
  
  private HousesDAO housesDAO = null;//PL114
  
  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;//PL115 
  
  private GatheringAccDAO gatheringAccDAO = null;//PL130 
  
  private GiveAccDAO giveAccDAO = null;//PL131
  
  private RestoreLoanDAO restoreLoanDAO = null;//PL201
  
  private OverdueInfoDAO overdueInfoDAO = null;//PL205
  
  private CongealInfoDAO congealInfoDAO = null;//PL210
  
  private ContractChgDAO contractChgDAO = null;//PL211

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setAssurerDAO(AssurerDAO assurerDAO) {
    this.assurerDAO = assurerDAO;
  }

  public void setImpawnContractDAO(ImpawnContractDAO impawnContractDAO) {
    this.impawnContractDAO = impawnContractDAO;
  }

  public void setLoanContractDAO(LoanContractDAO loanContractDAO) {
    this.loanContractDAO = loanContractDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  /**
   * ´û¿î»§²éÑ¯
   * 
   * @author wsh 2007-9-29 ²éÑ¯ÁÐ±íÐÅÏ¢
   */
  public List findLoanerlogoutTbList(Pagination pagination, List loanbankList)
      throws Exception {
    List list = null;
    List countlist = null;
    String loanKouAcc = "";
    String contractId = "";
    String borrowerName = "";
    String cardNum = "";
    String loanBankId = "";
    try {
      if (pagination.getQueryCriterions().get("loanKouAcc") != null) {
        loanKouAcc = (String) pagination.getQueryCriterions().get("loanKouAcc");
      }
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = borrowerAccDAO.queryloandeleteList(loanKouAcc, contractId,
          borrowerName, cardNum, loanBankId, orderBy, order, start, pageSize,
          loanbankList, page);
      countlist = borrowerAccDAO.queryloandeleteListCount(loanKouAcc,
          contractId, borrowerName, cardNum, loanBankId, loanbankList);
      if (countlist.size() != 0) {
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void deleteById(String id, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = null;
    Borrower borrower = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_DELCONTRACT + "";
    String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
    String opBizId = id;
    String bizId = id;
    int length = 0;
    int index = 0;
    String office = "";
    String cancelcontractid = "";// ×÷·ÏºÏÍ¬±àºÅ
    try {
      borrower = borrowerDAO.queryById(id);
      if (borrower != null) {
        office = borrower.getOffice();
        borrowerDAO.deleteBorrowerInfo(id);
      }

      borrowerAccDAO.deleteBorrowerAccInfo(id);// É¾³ýPL111
      loanContractDAO.deleteById(id);// É¾³ýpl120
      List list = pledgeContractDAO.queryIdByContractIdYU(id);// ²éÑ¯pl121ID
      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          String pl121Id = (String) list.get(i);
          pledgeContractDAO.deleteById(pl121Id);// É¾³ýPL121
        }
      }
      List list2 = impawnContractDAO.queryIdByContractIdYU(id);
      if (list2.size() != 0) {
        for (int i = 0; i < list2.size(); i++) {
          String pl122Id = (String) list2.get(i);
          impawnContractDAO.deleteById(pl122Id);// É¾³ýpl122
        }
      }
      List list3 = assurerDAO.queryIdByContractIdYU(id);
      if (list3.size() != 0) {
        for (int i = 0; i < list3.size(); i++) {
          BigDecimal pl123Id = (BigDecimal) list3.get(i);
          assurerDAO.deleteById(pl123Id.toString());// É¾³ýPL123
        }
      }

      assistantBorrowerDAO.deleteAsistantBorrowerList(id);// É¾³ýPL113

      housesDAO.deleteHousesInfo(id);// É¾³ýPL114

      borrowerLoanInfoDAO.deleteBorrowerLoanInfo(id);// É¾³ýPL115

      gatheringAccDAO.deleteGatheringAcc(id);// /É¾³ýPL130

      giveAccDAO.deleteGiveAcc(id);// É¾³ýPL131

      restoreLoanDAO.deleteRestoreLoan(id);// É¾³ýPL201

      overdueInfoDAO.deleteOverdueInfoByContractId(id);// É¾³ýPL205

      congealInfoDAO.deleteCongealInfo(id);// É¾³ýPL210

      contractChgDAO.deleteContractChg(id);// É¾³ýPL211

      length = id.length();// ²åÈëPL102 ×÷·ÏºÏÍ¬±í
      if (length > 6) {
        index = length - 6;
        cancelcontractid = id.substring(index, length);
        ContractNumCancel contractNumCancel = new ContractNumCancel();
        contractNumCancel.setCancelcontractid(cancelcontractid);
        contractNumCancel.setOffice(office);
        contractNumCancel.setReserveaA(id.substring(6, 8));
        contractNumCancelDAO.insert(contractNumCancel);
      }
      this
          .addPlOperateLog(opSys, model, button, bizId, opIp, operator, opBizId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("É¾³ýÊ§°Ü£¡");
    }
  }

  /**
   * ²åÈë²Ù×÷ÈÕÖ¾pl201
   * 
   * @param securityInfo
   * @return
   */
  public void addPlOperateLog(String opSys, String model, String button,
      String bizId, String opIp, String operator, String opBizId) {

    PlOperateLog plOperateLog = new PlOperateLog();
    try {
      plOperateLog.setOpSys(new BigDecimal(opSys));
      plOperateLog.setOpModel(model);
      plOperateLog.setOpButton(button);
      // if (opBizId != null && !"".equals(opBizId)) {
      // plOperateLog.setOpBizId(new BigDecimal(opBizId));
      // }
      plOperateLog.setOpIp(opIp);
      plOperateLog.setContractId(bizId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operator);
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setContractNumCancelDAO(ContractNumCancelDAO contractNumCancelDAO) {
    this.contractNumCancelDAO = contractNumCancelDAO;
  }

  public void setContractChgDAO(ContractChgDAO contractChgDAO) {
    this.contractChgDAO = contractChgDAO;
  }

  public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
    this.assistantBorrowerDAO = assistantBorrowerDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }

  public void setGatheringAccDAO(GatheringAccDAO gatheringAccDAO) {
    this.gatheringAccDAO = gatheringAccDAO;
  }

  public void setGiveAccDAO(GiveAccDAO giveAccDAO) {
    this.giveAccDAO = giveAccDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setOverdueInfoDAO(OverdueInfoDAO overdueInfoDAO) {
    this.overdueInfoDAO = overdueInfoDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

}
