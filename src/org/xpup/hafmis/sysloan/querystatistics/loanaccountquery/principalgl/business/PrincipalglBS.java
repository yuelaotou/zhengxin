package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.business;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.bsinterface.IPrincipalglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.form.PrincipalglTaAF;

/**
 * @author yuqf 2007-10-19
 */
public class PrincipalglBS implements IPrincipalglBS {

  private AssistantOrgDAO assistantOrgDAO = null;// PL007 获得担保公司下拉框

  private CollBankDAO collBankDAO = null;// 银行

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// PL202流水头表

  private OrganizationUnitDAO organizationUnitDAO = null;

  private PledgeContractDAO pledgeContractDAO = null;

  private DevelopProjectDAO developProjectDAO = null;

  private DeveloperDAO developerDAO = null;

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public List getAssistantOrgNameList() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public String getMydate() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public PrincipalglTaAF queryListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  public PrincipalglTaAF queryListByDay(String id, String date,
      Pagination pagination, SecurityInfo securityInfo, String radioValue,
      String floorname) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  public PrincipalglTaAF queryListByMonth(String id, String date,
      Pagination pagination, SecurityInfo securityInfo, String radioValue,
      String floorname) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

}
