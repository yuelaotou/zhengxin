package org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBank;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticePrintDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTaDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF;

public interface IIssuenoticeBS {
  public String findHouseType(String contractId) throws Exception;

  public IssuenoticeTaDTO findIssuenoticeTaInfo(String contractId,
      String houseType) throws Exception, BusinessException;

  public void findIssuenoticeAvailable(String contractId) throws Exception,
      BusinessException;

  public void saveIssuenoticeTa(String contractId, String bizDate,
      SecurityInfo securityInfo) throws Exception;

  public List findIssuenoticeTbList(Pagination pagination, List loanbankList,
      String type) throws Exception;

  public void findIssuenoticeInfoExist(String contractId, List list)
      throws Exception, BusinessException;

  public void deleteIssuenotice(String contractId, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  public void deleteIssuenoticeTb(String rowArray, SecurityInfo securityInfo)
      throws Exception;

  public String findIssuenoticeBizDate(String contractId) throws Exception;

  public Borrower findIssuenoticeBorrower(String contractId) throws Exception;

  public String getUserRealName(String name) throws Exception;

  public void saveIssuenotice(IssuenoticeTaAF issuenoticeTaAF,
      SecurityInfo securityInfo) throws BusinessException;

  public IssuenoticeTaAF queryPrintInfo(SecurityInfo securityInfo,
      IssuenoticeTaAF form) throws Exception;

  public List findIssuenoticeTcList(Pagination pagination, List loanbankList,
      String type) throws Exception;

  public void updateIssuenoticeBorrowerAcc(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public List findIssuenoticeTdList(Pagination pagination, List loanbankList,
      String type) throws Exception;

  public void updateIssuenoticeBorrowerAcc_1(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateIssuenoticeBorrowerAcc_2(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public LoanBank findIssuenoticeTaInfo(String loanBankId) throws Exception,
      BusinessException;

  public String getBankName(String loanbankid) throws Exception;

  public String findloanBankOutAccount(String contractId) throws Exception;

  public String findloanBankInAccount(String contractId) throws Exception;

  public void updateIssuenoticeBorrowerAcc_3(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateIssuenoticeBorrowerAcc_4(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public IssuenoticePrintDTO getchunaPrint(String loanBankId,
      String[] contractId, String bizDate, String realname, String username) throws Exception,
      BusinessException;
}
