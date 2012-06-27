package org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.GatheringAccInfoDTO;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.ReceiveaccModifyDTO;

public interface IReceiveaccBS {
  public ReceiveaccModifyDTO findReceiveaccInfo(String contractId)
      throws Exception, BusinessException;

  public void modifyBorrowerAccInfo(String contractId, String newLoanKouAcc,
      String oldBankAcc, SecurityInfo securityInfo,String flag,String newbankId) throws Exception,
      BusinessException;

  public void findReceiveaccInfoExist(String contractId,List loanBankList) throws Exception,
      BusinessException;

  public void findReceiveaccAvailable(String contractId) throws Exception,
      BusinessException;

  public void isLoanKouAccDuplicate(String newLoanKouAcc) throws Exception,
      BusinessException;

  public List findGathingAccList(Pagination pagination,List loanBankList) throws Exception,
      BusinessException;

  public int findGathingAccCount(String contractId, String borrwerName,
      String cardNum,List loanBankList) throws Exception, BusinessException;

  public GatheringAccInfoDTO findGatheringAccInfo(String receiveBankModifyId,String username)
      throws Exception, BusinessException;
  public String findExitGJJBack(String contractId) throws Exception, BusinessException;
}
