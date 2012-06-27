package org.xpup.hafmis.sysloan.loanapply.receiveacc.business;

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
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;

import org.xpup.hafmis.orgstrct.domain.CollBank;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.GatheringAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.GatheringAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface.IReceiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.GatheringAccInfoDTO;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.ReceiveaccModifyDTO;

public class ReceiveaccBS implements IReceiveaccBS {
  private BorrowerDAO borrowerDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private GatheringAccDAO gatheringAccDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollBankDAO collBankDAO = null;

  private SecurityDAO securityDAO = null;

  /**
   * 根据合同编号查询扣款帐号修改信息
   * 
   * @param contractId 合同编号
   * @return ReceiveaccModifyDTO
   * @throws Exception
   * @author wsh
   */
  public ReceiveaccModifyDTO findReceiveaccInfo(String contractId)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ReceiveaccModifyDTO receiveaccModifyDTO = new ReceiveaccModifyDTO();
    try {
      receiveaccModifyDTO = borrowerDAO.queryBorrowerInfo_wsh(contractId
          .toString());
      String cardKind = BusiTools.getBusiValue(Integer
          .parseInt(receiveaccModifyDTO.getCardKind().toString()),
          BusiConst.DOCUMENTSSTATE);
      if (cardKind != null && !"".equals(cardKind.trim())) {
        receiveaccModifyDTO.setCardKind(cardKind);
      }
      String loanankId = receiveaccModifyDTO.getLoanankId();
      if (loanankId != null && !"".equals(loanankId.trim())) {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanankId);
        receiveaccModifyDTO.setLoanankId(dto.getCollBankName());
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return receiveaccModifyDTO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * 修改扣款帐号
   * 
   * @param contractId 合同编号
   * @param newLoanKouAcc 新扣款帐号
   * @param oldBankAcc 旧扣款帐号
   * @param securityInfo 权限
   * @throws Exception
   * @author wsh
   */
  public void modifyBorrowerAccInfo(String contractId, String newLoanKouAcc,
      String oldBankAcc, SecurityInfo securityInfo, String flag,
      String newbankId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = new BorrowerAcc();
    try {
      borrowerAcc = borrowerAccDAO.queryById(contractId);
      borrowerAcc.setLoanKouAcc(newLoanKouAcc);
      GatheringAcc gatheringAcc = new GatheringAcc();
      gatheringAcc.setContractId(contractId);
      gatheringAcc.setModifyDate(securityInfo.getUserInfo().getPlbizDate());
      gatheringAcc.setNewBankAcc(newLoanKouAcc);
      gatheringAcc.setOldBankAcc(oldBankAcc);
      gatheringAcc.setOprator(securityInfo.getUserName());
      gatheringAcc.setOpTime(new Date());
      if (newbankId != null && !"".equals(newbankId)) {
        borrowerAcc.setLoanBankId(new BigDecimal(newbankId));
        gatheringAccDAO.updatePL201bankId_wsh(newbankId, contractId);
        gatheringAccDAO.updatePL004bankId_wsh(newbankId, contractId);
      }
      gatheringAccDAO.insert(gatheringAcc);
      // 包钢
      if ("1".equals(flag)) {
        gatheringAccDAO.updatePL400KOUACC_wsh(newLoanKouAcc, contractId);
      }
      // 包钢
      PlOperateLog plOperateLog = new PlOperateLog();
      // 插入日志PL021
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN
          + ""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_DO));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      plOperateLog.setOpBizId(new BigDecimal(gatheringAcc
          .getReceiveBankModifyId().toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * 根据输入合同编号查询合同编号是否存在
   * 
   * @param contractId 合同编号
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findReceiveaccInfoExist(String contractId, List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    int count = 0;
    try {
      count = borrowerAccDAO.queryIdExist_wsh(contractId, loanBankList)
          .intValue();
      // 条件成立说明输入合同编号不存在
      if (count == 0) {
        be = new BusinessException("合同编号不存在或不在用户权限下！");
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
   * 根据输入合同编号是否存在存在未记账的业务
   * 
   * @param contractId 合同编号
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findReceiveaccAvailable(String contractId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // count为查询的记录条数
      int count = 0;
      count = loanFlowTailDAO.queryCountByContractId_wsh(contractId).intValue();
      // 条件成立说明该合同编号下存在未记账的业务不可以进行扣款账号修改
      if (count != 0) {
        be = new BusinessException("该合同编号下存在未记账的业务不可以进行扣款账号修改！");
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
   * 根据输入新的扣款帐号是否与数据库中原有扣款帐号的重复
   * 
   * @param newLoanKouAcc 新扣款帐号
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void isLoanKouAccDuplicate(String newLoanKouAcc) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // count为查询的记录条数
      int count = 0;
      count = borrowerAccDAO.findBorrowerAccByLoanKouAcc_wsh(newLoanKouAcc)
          .intValue();
      // 条件成立说明输入的新的扣款帐号与数据库中原有扣款帐号的重复
      if (count != 0) {
        be = new BusinessException("扣款账号重复！");
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
   * 查询pl130中的记录
   * 
   * @param pagination
   * @throws Exception, BusinessException
   * @return List
   * @author wsh
   */
  public List findGathingAccList(Pagination pagination, List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String contractId = "";
      String borrowerName = "";
      String cardNum = "";
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
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = gatheringAccDAO.queryGathingAccList_wsh(contractId, borrowerName,
          cardNum, orderBy, order, start, pageSize, loanBankList);
      Iterator iter = list.iterator();
      GatheringAccInfoDTO gatheringAccInfoDTO = null;
      while (iter.hasNext()) {
        gatheringAccInfoDTO = (GatheringAccInfoDTO) iter.next();
        CollBank dto = collBankDAO.getCollBankByCollBankid(gatheringAccInfoDTO
            .getLoanBankId());
        if (gatheringAccInfoDTO.getOprator() != null && !"".equals(gatheringAccInfoDTO.getOprator()))
          gatheringAccInfoDTO.setOprator(securityDAO.queryByUserid(gatheringAccInfoDTO.getOprator()));
        gatheringAccInfoDTO.setLoanBankId(dto.getCollBankName());// 银行名称
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询pl130中的记录条数
   * 
   * @param contractId 合同编号
   * @param borrwerName 借款人姓名
   * @param cardNum 证件号码
   * @throws Exception, BusinessException
   * @return int
   * @author wsh
   */
  public int findGathingAccCount(String contractId, String borrwerName,
      String cardNum, List loanBankList) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // 记录条数
    int count = 0;
    try {
      count = gatheringAccDAO.queryGathingAccCount_wsh(contractId, borrwerName,
          cardNum, loanBankList).intValue();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 查询扣款帐号维护中要打印的单条信息
   * 
   * @param receiveBankModifyId pl130 主键
   * @throws Exception, BusinessException
   * @return HouseListDTO
   * @author wsh
   */
  public GatheringAccInfoDTO findGatheringAccInfo(String receiveBankModifyId,
      String username) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    GatheringAccInfoDTO gatheringAccInfoDTO = new GatheringAccInfoDTO();
    try {
      gatheringAccInfoDTO = gatheringAccDAO
          .queryGathingAccInfo_wsh(receiveBankModifyId);
      CollBank dto = collBankDAO.getCollBankByCollBankid(gatheringAccInfoDTO
          .getLoanBankId());
      gatheringAccInfoDTO.setLoanBankId(dto.getCollBankName());// 银行名称
      String name = securityDAO.queryByUserid(username);
      gatheringAccInfoDTO.setName(name);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return gatheringAccInfoDTO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setGatheringAccDAO(GatheringAccDAO gatheringAccDAO) {
    this.gatheringAccDAO = gatheringAccDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  // 包钢
  public String findExitGJJBack(String contractId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    String flag = "0";
    try {

      flag = gatheringAccDAO.findExitGJJBack(contractId);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    if (!"0".equals(flag) && !"".equals(flag)) {
      flag = "1";
    }
    return flag;
  }
  // 包钢
}
