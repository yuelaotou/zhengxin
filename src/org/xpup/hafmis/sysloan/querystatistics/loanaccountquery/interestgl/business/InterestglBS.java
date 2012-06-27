package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.bsinterface.IInterestglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.dto.InterestglTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.form.InterestglTaAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.dto.PrincipalglTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.form.PrincipalglTaAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto.FloorDTO;
import org.xpup.security.common.domain.Userslogincollbank;

public class InterestglBS implements IInterestglBS {

  private AssistantOrgDAO assistantOrgDAO = null;// PL007 获得担保公司下拉框

  private CollBankDAO collBankDAO = null;// 银行

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// PL202流水头表

  private OrganizationUnitDAO organizationUnitDAO = null;

  private PledgeContractDAO pledgeContractDAO = null;

  private DevelopProjectDAO developProjectDAO = null;

  private DeveloperDAO developerDAO = null;

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public List getAssistantOrgNameList() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public String getMydate() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public InterestglTaAF queryListByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  public InterestglTaAF queryListByDay(String id, String date, Pagination pagination, SecurityInfo securityInfo, String radioValue, String floorname) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  public InterestglTaAF queryListByMonth(String id, String date, Pagination pagination, SecurityInfo securityInfo, String radioValue, String floorname) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

}
