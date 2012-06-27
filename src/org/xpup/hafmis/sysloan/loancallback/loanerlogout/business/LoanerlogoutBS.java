package org.xpup.hafmis.sysloan.loancallback.loanerlogout.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;

import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.bsinterface.ILoanerlogoutBS;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.dto.LoanerlogoutTaDTO;


public class LoanerlogoutBS implements ILoanerlogoutBS {
  private BorrowerAccDAO borrowerAccDAO = null;
  private PlOperateLogDAO plOperateLogDAO = null;
  private LoanFlowTailDAO loanFlowTailDAO=null;
  private CongealInfoDAO congealInfoDAO=null;
  /**
   * 贷款户注销
   * 
   * @authorwsh 2007-9-28 根据贷款账号查询贷款帐号是否存在 查询条件：loadKouAcc
   */
  public void findLoanerlogoutTaExit(String loanKouAcc,List loanBankList) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub

    BusinessException be = null;
    try {
      // count为查询的记录条数
      int count = 0;
      count = borrowerAccDAO.findBorrowerAccByLoanKouAcc_wsh(loanKouAcc,loanBankList)
          .intValue();
      // 条件成立说明输入的新的扣款帐号与数据库中原有扣款帐号的重复
      if (count== 0) {
        be = new BusinessException("贷款账号不存在或不在用户权限下！");
        throw be;
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  /**
   * 贷款户注销-反注销
   * 根据输入合同编号是否存在存在未记账的业务
   * @param contractId 合同编号 
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findLoanerlogouAvailable(String contractId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      BorrowerAcc borrowerAcc=new BorrowerAcc();
      borrowerAcc=borrowerAccDAO.queryById(contractId);
      if("13".equals(borrowerAcc.getContractSt())){
        be = new BusinessException("该贷款户已注销！");
        throw be;
      }
//    count为查询的记录条数
      int count=0;
      count=loanFlowTailDAO.queryCountByContractId_wsh(contractId).intValue();
//    条件成立说明该合同编号下存在未记账的业务不可以进行扣款账号修改
      if(count!=0){
        be = new BusinessException("贷款户存在未记账的业务不能注销！");
        throw be;
      }
    } catch (BusinessException e) {
      // TODO: handle exception
      e.printStackTrace();
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }    
  }


  /**
   * 贷款户注销
   * 
   * @author wsh 2007-9-29 查询列表信息
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
      ;
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = borrowerAccDAO.queryLoanerlogoutTbList(loanKouAcc, contractId,
          borrowerName, cardNum, loanBankId, orderBy, order, start, pageSize,
          loanbankList);
      countlist = borrowerAccDAO.queryLoanerlogoutTbListCount(loanKouAcc,
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


  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }


  /**
   * 贷款户注销
   * 
   * @authorwsh 2007-9-28 根据贷款账号查询办理页面所需的数据 查询条件：loadKouAcc
   */
  public LoanerlogoutTaDTO findLoanerlogoutTaInfo(String loanKouAcc,List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    LoanerlogoutTaDTO loanerlogoutTaDTO = null;
    loanerlogoutTaDTO = borrowerAccDAO.queryLoanerlogoutTaInfo_wsh(loanKouAcc,loanBankList);
    if (loanerlogoutTaDTO != null) {
      loanerlogoutTaDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
          .parseInt(loanerlogoutTaDTO.getLoanMode().toString()),
          BusiConst.PLRECOVERTYPE));
      loanerlogoutTaDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer
          .parseInt(loanerlogoutTaDTO.getCardKind().toString()),
          BusiConst.DOCUMENTSSTATE));
    }
    return loanerlogoutTaDTO;
  }
  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }
  /**
   * 贷款户注销-反注销
   * 
   * @authorwsh 2007-9-29
   */
  public void saveLoanerlogouTa(String contractId, SecurityInfo securityInfo) {
    // TODO Auto-generated method stub
    try {
      // 修改pl111
      borrowerAccDAO.updateBorrowerAccContractStatus(contractId);
      // 修改pl210
      congealInfoDAO.updateCongealInfoStatus("1",contractId);
      // 插入日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN+""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_DO));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }
  /**
   * 贷款户注销-反注销
   * 
   * @authorwsh 2007-9-28 根据合同编号进行反注销
   */
  public void trunLogoutLoanerlogout(String contractId, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
//    count为查询的记录条数
      int count=0;
      count=borrowerAccDAO.findBorrowerAccByContractSt_wsh(contractId).intValue();
      if(count==0){
        be = new BusinessException("该贷款户已正常！");
        throw be;
      }
      //修改pl111
      borrowerAccDAO.updateBorrowerAccContractStatus_wsh(contractId);
      // 修改pl210
      congealInfoDAO.updateCongealInfoStatus("0",contractId);
      // 插入日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN+""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_MAINTAIN));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw be;
    }catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
  }
  /**
   * 贷款户注销-打印
   * 
   * @author wsh 2007-9-29 查询打印信息
   */
  public List findLoanerlogoutTbPrintList(Pagination pagination,List loanbankList) throws Exception {
    // TODO Auto-generated method stub
    String loanKouAcc = "";
    String contractId = "";
    String borrowerName = "";
    String cardNum = "";
    String loanBankId = "";
    List list = null;
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
      list = borrowerAccDAO.queryLoanerlogoutTbList_wsh(loanKouAcc, contractId,
          borrowerName, cardNum, loanBankId,
          loanbankList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
   
  }
 

}
