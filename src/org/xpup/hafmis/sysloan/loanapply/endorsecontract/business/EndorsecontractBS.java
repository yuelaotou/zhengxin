package org.xpup.hafmis.sysloan.loanapply.endorsecontract.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.AssurerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;

import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.ImpawnContractDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.common.domain.entity.Assurer;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.ImpawnContract;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContract;

import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTeDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaChangeLoanMonthRateAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTeAF;
import org.xpup.security.common.domain.Userslogincollbank;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;

public class EndorsecontractBS implements IEndorsecontractBS {

  private LoanBankParaDAO loanBankParaDAO = null;// PL003

  private BorrowerDAO borrowerDAO = null;// PL110

  private BorrowerAccDAO borrowerAccDAO = null;// PL111

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;// PL115

  private LoanContractDAO loanContractDAO = null;// PL120

  private PlOperateLogDAO plOperateLogDAO = null;// PL021

  private CollBankDAO collBankDAO = null;// BB105

  private PledgeContractDAO pledgeContractDAO = null;// pl121

  private AssistantOrgDAO assistantOrgDAO = null;// pl007 担保公司名称

  private HousesDAO housesDAO = null;// pl114 购房信息

  private ImpawnContractDAO impawnContractDAO = null;// PL122

  private AssurerDAO assurerDAO = null;// PL123

  private EmpDAO empDAO = null;// AA002

  private LoanBankDAO loanBankDAO = null;

  private LoanRateDAO loanRateDAO = null; // pl001

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setAssurerDAO(AssurerDAO assurerDAO) {
    this.assurerDAO = assurerDAO;
  }

  public void setImpawnContractDAO(ImpawnContractDAO impawnContractDAO) {
    this.impawnContractDAO = impawnContractDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setLoanContractDAO(LoanContractDAO loanContractDAO) {
    this.loanContractDAO = loanContractDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /** ****************************************************************** */
  /** **** 借款合同信息 **** */
  /** ****************************************************************** */
  /**
   * 查询办事处工作流程类型 'AB' or 'BA'
   */
  public String queryParamValue(SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    return loanBankParaDAO.queryParamvalueYU();
  }

  /**
   * 查询PL003中参数描述字段为"贷款流程"的记录中的参数值
   * 
   * @param id
   * @param securityInfo
   * @return
   */
  // public String queryParamValue_() {
  // return loanBankParaDAO.queryParamvalueYU_();
  // }
  /**
   * 判断PL120中是否存在传入的合同编号(Ta)
   * 
   * @param id
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void check(String id, HttpServletRequest request) throws Exception,
      BusinessException {

    request.getSession().setAttribute("contractId", null);// 清空session中合同编号
    String contractId = loanContractDAO.queryByIdYU(id);
    if (contractId != null) {
      throw new BusinessException("该合同已经存在！");
    } else {
      request.getSession().setAttribute("contractId", id);
    }
  }
  /**
   * 判断PL121中是否存在传入的合同编号(Ta)状态为0
   * 
   * @param id
   * @throws Exception
   * @throws BusinessException
   */
  public String queryPL121Contract(String contractid) throws Exception,BusinessException {
    return loanContractDAO.queryById_yg(contractid);
  }

  /**
   * 判断PL120中是否存在传入的合同编号(Tb)
   * 
   * @param id
   * @throws Exception
   * @throws BusinessException
   */
  public void checkPL120(String id) throws Exception, BusinessException {
    String contractId = loanContractDAO.queryByIdYU(id);
    if (contractId != null) {
      List list = this.checkPL121(id);//

    } else {
      throw new BusinessException("请先录入借款合同！");
    }
  }

  /**
   * 判断PL121中是否存在传入的合同编号(Tb)
   * 
   * @param id
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List checkPL121(String id) throws Exception, BusinessException {
    List list = pledgeContractDAO.queryIdByContractIdYU(id);
    return list;
  }

  /**
   * 判断PL122中是否存在传入的合同编号(Tc)
   * 
   * @return
   * @throws Excetption
   * @throws BusinessException
   */
  public List checkPL122(String id) throws Exception, BusinessException {
    List list = impawnContractDAO.queryIdByContractIdYU(id);
    return list;
  }

  /**
   * 判断PL123中是否存在传入的合同编号(Td)
   * 
   * @param id
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List checkPL123(String id) throws Exception, BusinessException {
    List list = assurerDAO.queryIdByContractIdYU(id);
    return list;
  }

  /**
   * 从权限中获得中心（抵押权人，委托方）
   * 
   * @param securityInfo
   * @return
   */
  public String getCenter(SecurityInfo securityInfo) {
    String center = "";
    OfficeDto officeDto = new OfficeDto();
    List list = securityInfo.getAllCenterList();
    if (list != null) {
      officeDto = (OfficeDto) list.get(0);
      center = officeDto.getOfficeName();
    }
    return center;
  }

  /**
   * 页面显示的查询
   */
  public EndorsecontractTaAF queryContractInfo(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request, String insert) throws Exception,
      BusinessException {
    String tempmonthInterest = "";
    // TODO Auto-generated method stub
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    // EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    if (id != null && !"".equals(id)) {
      request.getSession().setAttribute("contractId", id);
      String entruster = this.getCenter(securityInfo);// 委托方**中心

      EndorsecontractTaDTO endorsecontractTaDTOPL110 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOpl111 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOpl115 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOpl120 = new EndorsecontractTaDTO();

      // 查询PL110
      endorsecontractTaDTOPL110 = borrowerDAO.queryBorrowerInfoYU(id,
          securityInfo);
      String debitter = endorsecontractTaDTOPL110.getDebitter();// 借款人
      String certificateType = endorsecontractTaDTOPL110.getCertificateType();// 证件类型
      String certificateNum = endorsecontractTaDTOPL110.getCertificateNum();// 证件号码

      // 查询PL111
      endorsecontractTaDTOpl111 = borrowerAccDAO.queryBorrowerAccInfoYU(id,
          securityInfo);
      String beentruster = endorsecontractTaDTOpl111.getBeentruster();//
      // 受托方（放款银行ID）
      // String debitMoney = endorsecontractTaDTOpl111.getDebitMoney();// 借款金额
      // String term = endorsecontractTaDTOpl111.getTerm();// 借款期限
      String isWrite = endorsecontractTaDTOpl111.getIsWrite();// 是否签订合同
      String contractSt = endorsecontractTaDTOpl111.getContractSt();// 合同状态
      // String beentrusterName = this.queryBankName(beentruster);// 受托方名称

      // 查询PL115
      endorsecontractTaDTOpl115 = borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
          id, securityInfo);
      tempmonthInterest = endorsecontractTaDTOpl115.getMonthInterest();// 每月利率
      BigDecimal hundred = new BigDecimal(100);
      BigDecimal tempBig = new BigDecimal(0.00);
      if (tempmonthInterest != null && !"".equals(tempmonthInterest)) {
        tempBig = new BigDecimal(tempmonthInterest).multiply(hundred);
      }
      String monthInterest = tempBig.toString() + "%";
      String creditType = endorsecontractTaDTOpl115.getCreditType();// 还款方式
      String debitMoney = endorsecontractTaDTOpl115.getDebitMoney();// 借款金额
      String term = endorsecontractTaDTOpl115.getTerm();// 借款期限
      String corpusInterest = endorsecontractTaDTOpl115.getCorpusInterest();// 月还本息
      String tempCreditType = "";
      if (creditType != null && !"".equals(creditType)) {
        tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
            + creditType), BusiConst.PLRECOVERTYPE);
      }

      // 条件判断
      String paramValue = this.queryParamValue(securityInfo);
      if (insert == null) {// 判断是否为点击确定按钮，否：判断，是：不判断
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.审批通过 0 未签订合同
            this.check(id, request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        } else if ("BA".equals(paramValue)) {
          if ("15".equals(contractSt) && "0".equals(isWrite)) {
            this.check(id, request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        }
      }

      // 查询PL120
      endorsecontractTaDTOpl120 = loanContractDAO.queryLoanContractInfoYU(id,
          securityInfo);
      String assurer = "";
      String photoUrl = "";
      if (endorsecontractTaDTOpl120 != null
          && !"".equals(endorsecontractTaDTOpl120)) {
        assurer = endorsecontractTaDTOpl120.getAssurer();// 保证方
        photoUrl = endorsecontractTaDTOpl120.getPhotoUrl();// 路径
      }
      // String contractSureDate =
      // endorsecontractTaDTOpl120.getContractSureDate();// 合同签订日期
      // String debitMoneyStaDate = endorsecontractTaDTOpl120
      // .getDebitMoneyStaDate();// 借款起始日期
      // String assistantOrgId =
      // endorsecontractTaDTOpl120.getAssistantOrgId();//担保公司名称

      // String assurer = "";// 保证方
      String tempcontractSureDate = BusiTools.dateToString(new Date(),
          "yyyy-MM-dd");// 合同签订日期
      String contractSureDate = tempcontractSureDate.substring(0, 4)
          + tempcontractSureDate.substring(5, 7)
          + tempcontractSureDate.substring(8, 10);
      String tempdebitMoneyStaDate = BusiTools.dateToString(new Date(),
          "yyyy-MM-dd");// 合同签订日期
      String debitMoneyStaDate = tempdebitMoneyStaDate.substring(0, 4)
          + tempdebitMoneyStaDate.substring(5, 7)
          + tempdebitMoneyStaDate.substring(8, 10);// 借款起始日期
      String tempAsistantOrgId = endorsecontractTaDTOpl120.getAssistantOrgId();
      String assistantOrgId = "";
      if (tempAsistantOrgId != null && !"".equals(tempAsistantOrgId)) {
        assistantOrgId = assistantOrgDAO.queryOrgName_(tempAsistantOrgId);// 担保公司名称
      }
      // 根据借款起始日期＋借款期限计算还款终止日期
      String debitMoneyEndDate = "";
      String postfix = "";
      if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          && term != null && !"".equals(term)) {
        postfix = debitMoneyStaDate.substring(6, 8);
        int tempTerm = new Integer(term).intValue();
        debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate, tempTerm);
        term = String.valueOf(Integer.parseInt(term) / 12);
      }
      // 查询PL114 房产证编号，房屋地址，购房类型：01、商品房 02、二手房
      Houses houses = housesDAO.queryById(id);
      String copyrightCode = "";
      String address = "";
      String houseType = "";
      String bargainorName = "";
      String headId = "";
      if (houses != null) {
        copyrightCode = houses.getBargainorHousepropertyCode();
        address = houses.getHouseAddr();
        houseType = houses.getHouseType();
        bargainorName = houses.getBargainorName();
        headId = houses.getHeadId();// 开发商头表ID
        if ("01".equals(houseType)) {// 购房类型 是01 商品房 保证方为开发商
          // 更新开发商头表查询 PL005 开发商名称
          if (headId != null) {
            assurer = housesDAO.queryDevelper(headId);
          }
        } else {// 购房类型 是02 二手房 保证方为空
          assurer = "";
        }
      }
      /** ----------------------------------------------- */
      // if (isWrite != null && !"".equals(isWrite)) {
      // if ("0".equals(isWrite)) {
      // endorsecontractTaAF.setWriteType("0");//
      // 判断是否签订，（0未签订）：保证方,放款银行，合同签订日期，借款起始日期可修改，其他只读
      // } else {// 否则（1已签订）全部只读，确定按钮禁用
      // endorsecontractTaAF.setWriteType("1");
      // }
      // }
      endorsecontractTaAF.setLoanKouAcc(endorsecontractTaDTOpl111.getLoanKouAcc());// 页面显示扣款账号
      endorsecontractTaAF.setEntruster(entruster);// 页面显示－－委托方**中心
      endorsecontractTaAF.setContractId(id);// 页面显示－－合同编号
      endorsecontractTaAF.setDebitter(debitter);// 页面显示－－借款人
      endorsecontractTaAF.setCertificateType(BusiTools.getBusiValue(Integer
          .parseInt(certificateType), BusiConst.DOCUMENTSSTATE));// 页面显示－－证件类型
      endorsecontractTaAF.setCertificateNum(certificateNum);// 页面显示－－证件号码
      endorsecontractTaAF.setBeentruster(beentruster);// 页面显示－－受托方（银行）
      endorsecontractTaAF.setDebitMoney(debitMoney);// 页面显示－－借款金额
      endorsecontractTaAF.setTerm(term);// 页面显示－－借款期限
      endorsecontractTaAF.setMonthInterest(monthInterest);// 页面显示－－每月利率
      endorsecontractTaAF.setCreditType(tempCreditType);// 页面显示－－还款方式
      endorsecontractTaAF.setDebitMoneyStaDate(debitMoneyStaDate);// 页面显示－－借款起始日期
      endorsecontractTaAF.setContractSureDate(contractSureDate);// 页面显示－－合同签订日期
      endorsecontractTaAF.setAssurer(assurer);// 页面显示－－保证方
      endorsecontractTaAF.setDebitMoneyEndDate(debitMoneyEndDate + postfix);// 页面显示－－还款终止日期
      endorsecontractTaAF.setAssistantOrgId(assistantOrgId);
      endorsecontractTaAF.setPhotoUrl(photoUrl);// 路径
      endorsecontractTaAF.setRealMonthInt(tempmonthInterest);
      endorsecontractTaAF.setHiddenloanMode(creditType);// 石岩用还款方式（INT类型）
      endorsecontractTaAF.setWriteType("0");
      endorsecontractTaAF.setCorpusInterest(corpusInterest);

      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(id);
      String contractSthl = borrowerAcc.getContractSt();// 合同状态

      String paramv = loanBankParaDAO.queryParam_value_hanl();
      String contractidhl = loanContractDAO.queryByIdYU(id);
      if (paramv.equals("BA") && contractidhl == null
          && contractSthl.equals("2")) {
        endorsecontractTaAF.setIscontactid("1");// 控制页面上的不允许签订按钮可用 显示
      } else {
        endorsecontractTaAF.setIscontactid("2");// 控制页面上的不允许签订按钮不显示
      }

      /** ----------------------------------------------- */
    }
    return endorsecontractTaAF;
  }

  // 打印和弹出框的查询(维护过来)
  public EndorsecontractTaAF queryContractInfo_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String tempmonthInterest = "";
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    if (id != null && !"".equals(id)) {
      request.getSession().setAttribute("contractId", id);
      request.getSession().setAttribute("comeFromType", "yes");
      String entruster = this.getCenter(securityInfo);// 委托方**中心

      EndorsecontractTaDTO endorsecontractTaDTOPL110 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL115 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL120 = new EndorsecontractTaDTO();

      // 查询PL110
      endorsecontractTaDTOPL110 = borrowerDAO.queryBorrowerInfoYU(id,
          securityInfo);
      String debitter = endorsecontractTaDTOPL110.getDebitter();// 借款人
      String certificateType = endorsecontractTaDTOPL110.getCertificateType();// 证件类型
      String certificateNum = endorsecontractTaDTOPL110.getCertificateNum();// 证件号码

      // 查询PL111
      endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
          securityInfo);
      String beentruster = endorsecontractTaDTO.getBeentruster();// 受托方（放款银行ID）
      String bankName = this.queryBankName(beentruster);
      // String debitMoney = endorsecontractTaDTO.getDebitMoney();// 借款金额
      // String term = endorsecontractTaDTO.getTerm();// 借款期限
      String isWrite = endorsecontractTaDTO.getIsWrite();// 是否签订合同

      // 查询PL115
      // endorsecontractTaDTOPL115 =
      // borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
      // id, securityInfo);
      // String monthInterest = endorsecontractTaDTOPL115.getMonthInterest();//
      // 每月利率
      // String creditType = endorsecontractTaDTOPL115.getCreditType();// 还款方式
      // String tempCreditType = "";
      // if (creditType != null && !"".equals(creditType)) {
      // tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
      // + creditType), BusiConst.PLRECOVERTYPE);
      // }
      endorsecontractTaDTOPL115 = borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
          id, securityInfo);
      tempmonthInterest = endorsecontractTaDTOPL115.getMonthInterest();// 每月利率
      BigDecimal hundred = new BigDecimal(100);
      BigDecimal temBig = new BigDecimal(0.00);
      if (tempmonthInterest != null && !"".equals(tempmonthInterest)) {
        temBig = new BigDecimal(tempmonthInterest).multiply(hundred);
      }
      String monthInterest = temBig.toString() + "%";
      String creditType = endorsecontractTaDTOPL115.getCreditType();// 还款方式
      String debitMoney = endorsecontractTaDTOPL115.getDebitMoney();// 借款金额
      String term = endorsecontractTaDTOPL115.getTerm();// 借款期限
      String corpusInterest = endorsecontractTaDTOPL115.getCorpusInterest();// 月还本息
      String tempCreditType = "";
      if (creditType != null && !"".equals(creditType)) {
        tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
            + creditType), BusiConst.PLRECOVERTYPE);
      }
      // 查询PL120
      endorsecontractTaDTOPL120 = loanContractDAO.queryLoanContractInfoYU(id,
          securityInfo);
      // String assurer = endorsecontractTaDTOPL120.getAssurer();// 保证方
      String photoUrl = endorsecontractTaDTOPL120.getPhotoUrl();// 路径
      String contractSureDate = endorsecontractTaDTOPL120.getContractSureDate();// 合同签订日期
      String debitMoneyStaDate = endorsecontractTaDTOPL120
          .getDebitMoneyStaDate();// 借款起始日期
      String assistantOrgId = endorsecontractTaDTOPL120.getAssistantOrgId();// 担保公司id
      String assistantOrgName = assistantOrgDAO.queryOrgName_(assistantOrgId);// 担保公司名称
      // 根据借款起始日期＋借款期限计算还款终止日期
      String debitMoneyEndDate = "";
      String postfix = "";
      if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          && term != null && !"".equals(term)) {
        postfix = debitMoneyStaDate.substring(6, 8);
        int tempTerm = new Integer(term).intValue();
        debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate, tempTerm);
        term = String.valueOf(Integer.parseInt(term) / 12);
      }

      /** ----------------------------------------------- */
      if (isWrite != null && !"".equals(isWrite)) {
        if ("0".equals(isWrite)) {
          endorsecontractTaAF.setIsview("0");// 判断是否签订，（0未签订）：保证方,放款银行，合同签订日期，借款起始日期可修改，其他只读
        } else {// 否则（1已签订）全部只读，确定按钮禁用
          endorsecontractTaAF.setIsview("1");
        }
      }
      // 查询PL114 房产证编号，房屋地址，购房类型：01、商品房 02、二手房
      Houses houses = housesDAO.queryById(id);
      String copyrightCode = "";
      String address = "";
      String houseType = "";
      String bargainorName = "";
      String assurer = "";
      String headId = "";
      if (houses != null) {
        copyrightCode = houses.getBargainorHousepropertyCode();
        address = houses.getHouseAddr();
        houseType = houses.getHouseType();
        bargainorName = houses.getBargainorName();
        headId = houses.getHeadId();// 开发商头表ID
        if ("01".equals(houseType)) {// 购房类型 是01 商品房 保证方为开发商
          // 更新开发商头表查询 PL005 开发商名称
          if (headId != null) {
            assurer = housesDAO.queryDevelper(headId);
          }
        } else {// 购房类型 是02 二手房 保证方为空
          assurer = "";
        }
      }
      // 王野修改：8个页面的弹出框-借款合同信息，查询之前要判断是否签订合同，如果未签订，不显示信息
      // if (isWrite != null && !"".equals(isWrite)) {
      // if ("1".equals(isWrite)) {
      
      endorsecontractTaAF.setEntruster(entruster);// 页面显示－－委托方**中心
      endorsecontractTaAF.setContractId(id);// 页面显示－－合同编号
      endorsecontractTaAF.setDebitter(debitter);// 页面显示－－借款人
      if (certificateType != null && !"".equals(certificateType)) {
        endorsecontractTaAF.setCertificateType(BusiTools.getBusiValue(Integer
            .parseInt("" + certificateType), BusiConst.DOCUMENTSSTATE));// 页面显示－－证件类型
      }
      
      endorsecontractTaAF.setLoanKouAcc(endorsecontractTaDTO.getLoanKouAcc());// 页面显示扣款账号
      endorsecontractTaAF.setCertificateNum(certificateNum);// 页面显示－－证件号码
      // endorsecontractTaAF.setBeentruster(beentrusterName);// 页面显示－－受托方（银行）
      endorsecontractTaAF.setBeentruster(beentruster);
      endorsecontractTaAF.setTemp_beentruster(beentruster);// 页面显示－－受托方（银行）
      // endorsecontractTaAF.setBeentruster(bankName);// 页面显示－－受托方（银行）
      endorsecontractTaAF.setDebitMoney(debitMoney);// 页面显示－－借款金额
      endorsecontractTaAF.setTerm(term);// 页面显示－－借款期限
      endorsecontractTaAF.setMonthInterest(monthInterest);// 页面显示－－每月利率
      endorsecontractTaAF.setCreditType(tempCreditType);// 页面显示－－还款方式
      endorsecontractTaAF.setDebitMoneyStaDate(debitMoneyStaDate);// 页面显示－－借款起始日期
      endorsecontractTaAF.setContractSureDate(contractSureDate);// 页面显示－－合同签订日期
      endorsecontractTaAF.setAssurer(assurer);// 页面显示－－保证方
      endorsecontractTaAF.setPhotoUrl(photoUrl);// 路径
      endorsecontractTaAF.setDebitMoneyEndDate(debitMoneyEndDate + postfix);// 页面显示－－还款终止日期
      endorsecontractTaAF.setAssistantOrgId(assistantOrgName);
      endorsecontractTaAF.setBankName(bankName);
      endorsecontractTaAF.setCorpusInterest(corpusInterest);
      endorsecontractTaAF.setRealMonthInt(tempmonthInterest);
      endorsecontractTaAF.setHiddenloanMode(creditType);// 石岩用还款方式（INT类型）
    }
    // }
    /** ----------------------------------------------- */
    // }
    return endorsecontractTaAF;
  }

  // 王野专用 8-弹出框
  public EndorsecontractTaAF queryContractInfo_wy(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String tempmonthInterest = "";
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    if (id != null && !"".equals(id)) {
      request.getSession().setAttribute("contractId", id);
      request.getSession().setAttribute("comeFromType", "yes");
      String entruster = this.getCenter(securityInfo);// 委托方**中心

      EndorsecontractTaDTO endorsecontractTaDTOPL110 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL115 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL120 = new EndorsecontractTaDTO();

      // 查询PL110
      endorsecontractTaDTOPL110 = borrowerDAO.queryBorrowerInfoYU(id,
          securityInfo);
      String debitter = endorsecontractTaDTOPL110.getDebitter();// 借款人
      String certificateType = endorsecontractTaDTOPL110.getCertificateType();// 证件类型
      String certificateNum = endorsecontractTaDTOPL110.getCertificateNum();// 证件号码

      // 查询PL111
      endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
          securityInfo);
      String beentruster = endorsecontractTaDTO.getBeentruster();// 受托方（放款银行ID）
      String loanKouAcc = endorsecontractTaDTO.getLoanKouAcc();
      String bankName = this.queryBankName(beentruster);
      // String debitMoney = endorsecontractTaDTO.getDebitMoney();// 借款金额
      // String term = endorsecontractTaDTO.getTerm();// 借款期限
      String isWrite = endorsecontractTaDTO.getIsWrite();// 是否签订合同
      String contractSt = endorsecontractTaDTO.getContractSt();// 合同状态
      String beentrusterName = this.queryBankName(beentruster);// 受托方名称

      // 查询PL115
      // endorsecontractTaDTOPL115 =
      // borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
      // id, securityInfo);
      // String monthInterest = endorsecontractTaDTOPL115.getMonthInterest();//
      // 每月利率
      // String creditType = endorsecontractTaDTOPL115.getCreditType();// 还款方式
      // String tempCreditType = "";
      // if (creditType != null && !"".equals(creditType)) {
      // tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
      // + creditType), BusiConst.PLRECOVERTYPE);
      // }
      endorsecontractTaDTOPL115 = borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
          id, securityInfo);
      tempmonthInterest = endorsecontractTaDTOPL115.getMonthInterest();// 每月利率
      BigDecimal hundred = new BigDecimal(100);
      BigDecimal temBig = new BigDecimal(0.00);
      if (tempmonthInterest != null && !"".equals(tempmonthInterest)) {
        temBig = new BigDecimal(tempmonthInterest).multiply(hundred);
      }
      String monthInterest = temBig.toString() + "%";
      String creditType = endorsecontractTaDTOPL115.getCreditType();// 还款方式
      String debitMoney = endorsecontractTaDTOPL115.getDebitMoney();// 借款金额
      String term = endorsecontractTaDTOPL115.getTerm();// 借款期限
      String tempCreditType = "";
      if (creditType != null && !"".equals(creditType)) {
        tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
            + creditType), BusiConst.PLRECOVERTYPE);
      }
      // 查询PL120
      endorsecontractTaDTOPL120 = loanContractDAO.queryLoanContractInfoYU(id,
          securityInfo);
      String assurer = endorsecontractTaDTOPL120.getAssurer();// 保证方
      String contractSureDate = endorsecontractTaDTOPL120.getContractSureDate();// 合同签订日期
      String debitMoneyStaDate = endorsecontractTaDTOPL120
          .getDebitMoneyStaDate();// 借款起始日期
      String photoUrl = endorsecontractTaDTOPL120.getPhotoUrl();
      String assistantOrgId = endorsecontractTaDTOPL120.getAssistantOrgId();// 担保公司id
      String assistantOrgName = assistantOrgDAO.queryOrgName_(assistantOrgId);// 担保公司名称

      // 根据借款起始日期＋借款期限计算还款终止日期
      String debitMoneyEndDate = "";
      String postfix = "";
      if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          && term != null && !"".equals(term)) {
        postfix = debitMoneyStaDate.substring(6, 8);
        int tempTerm = new Integer(term).intValue();
        debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate, tempTerm);
      }

      /** ----------------------------------------------- */
      if (isWrite != null && !"".equals(isWrite)) {
        if ("0".equals(isWrite)) {
          endorsecontractTaAF.setIsview("0");// 判断是否签订，（0未签订）：保证方,放款银行，合同签订日期，借款起始日期可修改，其他只读
        } else {// 否则（1已签订）全部只读，确定按钮禁用
          endorsecontractTaAF.setIsview("1");
        }
      }
      // 王野修改：8个页面的弹出框-借款合同信息，查询之前要判断是否签订合同，如果未签订，不显示信息
      if (isWrite != null && !"".equals(isWrite)) {
        if ("1".equals(isWrite)) {
          endorsecontractTaAF.setEntruster(entruster);// 页面显示－－委托方**中心
          endorsecontractTaAF.setContractId(id);// 页面显示－－合同编号
          endorsecontractTaAF.setDebitter(debitter);// 页面显示－－借款人
          endorsecontractTaAF.setCertificateType(BusiTools.getBusiValue(Integer
              .parseInt("" + certificateType), BusiConst.DOCUMENTSSTATE));// 页面显示－－证件类型
          endorsecontractTaAF.setCertificateNum(certificateNum);// 页面显示－－证件号码
          endorsecontractTaAF.setBeentruster(beentrusterName);// 页面显示－－受托方（银行）
          // endorsecontractTaAF.setBeentruster(bankName);// 页面显示－－受托方（银行）
          endorsecontractTaAF.setDebitMoney(debitMoney);// 页面显示－－借款金额
          endorsecontractTaAF.setTerm(term);// 页面显示－－借款期限
          endorsecontractTaAF.setMonthInterest(monthInterest);// 页面显示－－每月利率
          endorsecontractTaAF.setCreditType(tempCreditType);// 页面显示－－还款方式
          endorsecontractTaAF.setDebitMoneyStaDate(debitMoneyStaDate);// 页面显示－－借款起始日期
          endorsecontractTaAF.setContractSureDate(contractSureDate);// 页面显示－－合同签订日期
          endorsecontractTaAF.setAssurer(assurer);// 页面显示－－保证方
          endorsecontractTaAF.setDebitMoneyEndDate(debitMoneyEndDate + postfix);// 页面显示－－还款终止日期
          endorsecontractTaAF.setAssistantOrgId(assistantOrgName);
          endorsecontractTaAF.setBankName(bankName);
          endorsecontractTaAF.setRealMonthInt(tempmonthInterest);
          endorsecontractTaAF.setPhotoUrl(photoUrl);
          endorsecontractTaAF.setLoanKouAcc(loanKouAcc);
        }
      }
      /** ----------------------------------------------- */
    }
    return endorsecontractTaAF;
  }

  /**
   * Ta确认按钮
   */
  public void endorsecontractTaMaitainSure(String loanassistantorgId,
      EndorsecontractTaAF endorsecontractTaAF, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_LOANCONTRACT + "";
    
    String beentruster = endorsecontractTaAF.getBeentruster();// 乙方(银行)
    String contractId = endorsecontractTaAF.getContractId();
    String assurer = endorsecontractTaAF.getAssurer();// 保证方
    String contractSureDate = endorsecontractTaAF.getContractSureDate();// 合同签订日期
    String debitMoneyStaDate = endorsecontractTaAF.getDebitMoneyStaDate();// 借款起始日期
    String corpusInterest = endorsecontractTaAF.getCorpusInterest();// 月还本息
    String loanMonthRate = endorsecontractTaAF.getRealMonthInt();// 最新的利率

    LoanContract loanContract = loanContractDAO.queryById(contractId);
    Integer count = borrowerAccDAO.findBorrowerAccByLoanKouAcc_sy(endorsecontractTaAF.getLoanKouAcc());
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    if (borrowerAcc.getLoanKouAcc() == null
        || (borrowerAcc.getLoanKouAcc() != null && !borrowerAcc.getLoanKouAcc()
            .equals(endorsecontractTaAF.getLoanKouAcc()))) {
      if (count.intValue() > 0) {
        throw new BusinessException("扣款账号已使用，确认核实账号！");
      }
    }
    if (loanContract != null) {// 是，更新
      loanContract.setAssurer(assurer);
      loanContract.setAgreementDate(contractSureDate);
      loanContract.setLoanStartDate(debitMoneyStaDate);
      loanContract.setAssistantOrgId(loanassistantorgId);
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = contractId;
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    } else {// 否，插入
      LoanContract lContract = new LoanContract();
      lContract.setContractId(contractId);
      lContract.setAssurer(assurer);
      lContract.setAgreementDate(contractSureDate);
      lContract.setLoanStartDate(debitMoneyStaDate);
      lContract.setOperator(operator);
      lContract.setAssistantOrgId(loanassistantorgId);
      lContract.setOpTime(new Date());
      Serializable newContractId = loanContractDAO.insert(lContract);
      String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
      String bizId = newContractId.toString();
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    }
    // 更新PL111的银行
    if (borrowerAcc != null && beentruster != null) {
      borrowerAcc.setLoanBankId(new BigDecimal(beentruster));
      borrowerAcc.setLoanKouAcc(endorsecontractTaAF.getLoanKouAcc());
    }
    // 更新PL115的利率，月还本息
    BigDecimal tempCorpusInterest = new BigDecimal(0.00);
    if (corpusInterest != null && !corpusInterest.equals("")) {
      tempCorpusInterest = new BigDecimal(corpusInterest);
    } else {
      tempCorpusInterest = null;
    }
    borrowerLoanInfoDAO.updateBorrowerLoanInfo_sy(
        new BigDecimal(loanMonthRate), tempCorpusInterest, contractId);
  }

  /**
   * Ta扫描按钮
   */
  public void endorsecontractTaMaitainScan(
      EndorsecontractTaAF endorsecontractTaAF, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub

  }

  /**
   * 插入操作日志pl201
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

  /**
   * 根据放款银行ID查询银行名称
   * 
   * @param id
   * @return
   */
  public String queryBankName(String id) {
    String bankName = "";
    if (id != null && !id.equals("")) {
      try {
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(id);
        if (collBank != null) {
          bankName = collBank.getCollBankName();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return bankName;
  }

  /** ****************************************************************** */
  /** **** 抵押合同信息 **** */
  /** ****************************************************************** */

  /**
   * 查询120
   */

  public String queryPL120(String id) throws BusinessException {
    return loanContractDAO.queryByIdYU(id);
  }

  public EndorsecontractTbAF queryPledgeContractList(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    String debitter = "";
    String entruster = "";// 委托方**中心
    String buyHouseContractId = "";// 购房合同编号
    Borrower borrower = null;
    String sex = "";
    String orgName = "";
    String orgAddress = "";
    String orgTel = "";
    String sfz = "";
    String pledgeValues = "";// 抵押值
    Houses houses = null;
    String houseType = "";
    try {
      if (id != null && !"".equals(id)) {
        entruster = this.getCenter(securityInfo);// 委托方**中心
        String office = this.getCenter(securityInfo);
        debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名

        // 查询PL111
        endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
            securityInfo);
        String isWrite = endorsecontractTaDTO.getIsWrite();// 是否签订合同
        String contractSt = endorsecontractTaDTO.getContractSt();// 合同状态
        // 条件判断
        String paramValue = this.queryParamValue(securityInfo);
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.审批通过 0 未签订合同
            // 判断120是否存在
            endorsecontractTbAF = this.isQuery(id, debitter, office, request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        } else if ("BA".equals(paramValue)) {
          if ("15".equals(contractSt) && "0".equals(isWrite)) {
            // 判断120
            endorsecontractTbAF = this.isQuery(id, debitter, office, request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        }
        // 查询PL110
        borrower = borrowerDAO.queryById(id);
        if (borrower != null) {
          String tempsex = borrower.getSex();
          if (tempsex != null) {
            sex = BusiTools.getBusiValue(Integer.parseInt(tempsex),
                BusiConst.SEX);
          }
          sfz = borrower.getCardNum();
          orgName = borrower.getOrgName();
          orgAddress = borrower.getOrgAddr();
          orgTel = borrower.getOrgTel();
        }
      }
    } catch (BusinessException e) {
      throw e;
    }
    request.getSession().setAttribute("contractId", id);// 把新输入或选中的ID放到SESSION中
    endorsecontractTbAF.setContractId(id);
    endorsecontractTbAF.setDebitter(debitter);
    String paperPersonName = endorsecontractTbAF.getPaperPersonName();// 所有权人
    if (paperPersonName == null || "".equals(paperPersonName)) {// 所有权人为空时，所有权人=抵押人=借款人
      endorsecontractTbAF.setPaperPersonName(debitter);
      endorsecontractTbAF.setPledgePerson(debitter);
    } else {// 所有权人不为空时,抵押人=所有权人
      endorsecontractTbAF.setPledgePerson(paperPersonName);
    }
    endorsecontractTbAF.setOffice(entruster);
    String tempbuyHouseContractId = endorsecontractTbAF.getBuyHouseContractId();
    if (tempbuyHouseContractId == null || "".equals(tempbuyHouseContractId)) {
      if (id != null && !"".equals(id)) {
        houses = housesDAO.queryById(id);// PL114 购房信息

        if (houses != null) {
          buyHouseContractId = houses.getBuyHouseContractId();// 购房合同编号
          houseType = houses.getHouseType();// 房屋类型
          EndorsecontractTaDTO endorsecontractTaDTOPL115 = borrowerLoanInfoDAO
              .queryBorrowerLoanInfoYU(id, securityInfo);
          String debitMoney = endorsecontractTaDTOPL115.getDebitMoney();// 贷款金额
          endorsecontractTbAF.setDebitMoney(debitMoney);
          if ("02".equals(houseType)) {// 购房类型 是02 二手房 //折扣率 = 贷款金额/抵押值

            // 抵押值 = 二手房评估值
            if (endorsecontractTbAF.getPledgeValue() == null
                || "".equals(endorsecontractTbAF.getPledgeValue())) {
              if (houses.getBargainorTotlePrice() != null) {
                pledgeValues = houses.getBargainorTotlePrice().toString();
                endorsecontractTbAF.setPledgeValue(pledgeValues);
              }
            }

            String rebate = "";// 折扣率 //折扣率 = 贷款金额/抵押值
            BigDecimal tempvalue = new BigDecimal(0.00);
            BigDecimal tempdebitMoney = new BigDecimal(0.00);
            String temppledgeValue = endorsecontractTbAF.getPledgeValue();// 抵押值
            if (temppledgeValue != null && !"".equals(temppledgeValue)) {
              tempvalue = new BigDecimal(temppledgeValue);
            }
            if (debitMoney != null) {
              tempdebitMoney = new BigDecimal(debitMoney);
            }
            if (tempvalue.compareTo(new BigDecimal(0)) != 0) {
              rebate = tempdebitMoney.divide(tempvalue, 4,
                  BigDecimal.ROUND_HALF_UP).toString();
              if(rebate!=null&&!"".equals(rebate)){
                endorsecontractTbAF.setRebate(rebate);
              }
             
            }

          } else {// 商品房
          // 抵押值=商品房总价
            if (endorsecontractTbAF.getPledgeValue() == null
                || "".equals(endorsecontractTbAF.getPledgeValue())) {
              if (houses.getBargainorTotlePrice() != null) {
                pledgeValues = houses.getHouseTotlePrice().toString();
              }
            }
          }
          endorsecontractTbAF.setHouseType(houseType);

          endorsecontractTbAF.setBuyHouseContractId(buyHouseContractId);
        }
        if (houses.getRebate() != null) {
          endorsecontractTbAF.setRebate(houses.getRebate().toString());
        }

      }
    }
    if ("0".equals(endorsecontractTbAF.getIsPl121())) {// 没有录入过抵押人，抵押人信息默认显示为借款人信息
      // 默认显示借款人固定电话 移动电话 证件类型 证件号码
      if (borrower != null) {
        endorsecontractTbAF.setCardKind(borrower.getCardKind());
        endorsecontractTbAF.setCarNum(borrower.getCardNum());
        endorsecontractTbAF.setTel(borrower.getHouseTel());
        endorsecontractTbAF.setMobile(borrower.getHomeMobile());
        endorsecontractTbAF.setDyrgddh(borrower.getHouseTel());
        endorsecontractTbAF.setDyryddh(borrower.getHomeMobile());
      }
      String houseAddrs = "";
      String paperNum = "";
      BigDecimal pledgeValuess = new BigDecimal(0.00);
      if ("02".equals(houseType)) {// 购房类型 是02 二手房
        houseAddrs = houses.getBargainorHouseAddr();
        endorsecontractTbAF.setArea(houses.getBargainorHouseArea().toString());
        // 抵押值 = 二手房评估值
        pledgeValuess = houses.getBargainorTotlePrice();
        if (pledgeValuess != null) {
          pledgeValues = pledgeValuess.toString();
        }
        // 房产制造编号
        paperNum = houses.getBargainorHousepropertyCode();

      } else {// 商品房
        houseAddrs = houses.getHouseAddr();
        endorsecontractTbAF.setArea(houses.getHouseArea().toString());
        // 抵押值=商品房总价
        pledgeValuess = houses.getHouseTotlePrice();
        if (pledgeValuess != null) {
          pledgeValues = pledgeValuess.toString();
        }
        // 房产制造编号
        paperNum = houses.getBuildRightNum();
      }
      System.out.println("--------->" + paperNum);
      endorsecontractTbAF.setPledgeAddr(houseAddrs);
      endorsecontractTbAF.setPledgeValue(pledgeValues);
      endorsecontractTbAF.setPaperNum(paperNum);
    }
    endorsecontractTbAF.setSex(sex);
    endorsecontractTbAF.setOrgName(orgName);
    endorsecontractTbAF.setOrgAddress(orgAddress);
    endorsecontractTbAF.setOrgTel(orgTel);
    endorsecontractTbAF.setSfz(sfz);
    return endorsecontractTbAF;
  }

  // 打印
  public EndorsecontractTbAF queryPledgeContractList_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    List list = new ArrayList();
    String office = this.getCenter(securityInfo);
    String pkId = pledgeContractDAO.queryMaxId(id);
    String debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名
    Borrower borrower = borrowerDAO.queryById(id);// 根据合同ID查询借款人姓名
    BorrowerLoanInfo borrowerLoanInfo = borrowerLoanInfoDAO.queryById(id);
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(id);// 根据合同ID查询借款人姓名
    LoanContract loanContract = loanContractDAO.queryById(id);
    if (pkId != null && !"".equals(pkId)) {
      endorsecontractTbAF = this.queryPledgeContractById(pkId, office);
      list = pledgeContractDAO.queryPledgeListYU(id);// 查询列表
      endorsecontractTbAF.setList(list);
      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          EndorsecontractTbAF esTbAF = (EndorsecontractTbAF) list.get(i);
          String statuss = "";
          String tempStatuss = esTbAF.getStatus();
          if (tempStatuss != null && !"".equals(tempStatuss)) {
            statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                BusiConst.PLCOMMONSTATUS_1);
          }
          esTbAF.setStatus(statuss);
          esTbAF.setDebitter(debitter);// 抵押人
          esTbAF.setOffice(office);// 抵押权人
          if (esTbAF.getCardKind() != null && !esTbAF.getCardKind().equals("")) {
            esTbAF.setCardKind((BusiTools.getBusiValue(Integer.parseInt(esTbAF
                .getCardKind()), BusiConst.DOCUMENTSSTATE)));
          }
        }
      }
      endorsecontractTbAF.setList(list);
    }
    endorsecontractTbAF.setBorrower(borrower);
    endorsecontractTbAF.setBorrowerAcc(borrowerAcc);
    endorsecontractTbAF.setLoanContract(loanContract);
    String bankId = borrowerAcc.getLoanBankId().toString();
    endorsecontractTbAF.setBorrowerLoanInfo(borrowerLoanInfo);
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
    String bankName = collBank.getCollBankName();
    endorsecontractTbAF.setBankName(bankName);
    request.getSession().setAttribute("pl121Id", pkId);
    String tempCardkind = endorsecontractTbAF.getCardKind();
    if (tempCardkind != null && !"".equals(tempCardkind)) {
      endorsecontractTbAF.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// 页面显示－－证件类型
    }
    return endorsecontractTbAF;
  }

  // 抵押合同变更专用
  public EndorsecontractTbAF queryPledgeContractList_Chg(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    List list = new ArrayList();
    String office = this.getCenter(securityInfo);
    String pkId = "";
    pkId = (String) request.getSession().getAttribute("pl121Id");
    if (pkId == null || "".equals(pkId)) {
      pkId = pledgeContractDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名
    if (pkId != null && !"".equals(pkId)) {
      endorsecontractTbAF = this.queryPledgeContractById(pkId, office);
      list = pledgeContractDAO.queryPledgeListYU(id);// 查询列表
      endorsecontractTbAF.setList(list);
      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          EndorsecontractTbAF esTbAF = (EndorsecontractTbAF) list.get(i);
          String statuss = "";
          String tempStatuss = esTbAF.getStatus();
          if (tempStatuss != null && !"".equals(tempStatuss)) {
            statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                BusiConst.PLCOMMONSTATUS_1);
          }
          esTbAF.setStatus(statuss);
          esTbAF.setDebitter(debitter);// 抵押人
          esTbAF.setOffice(office);// 抵押权人
        }
      }
      endorsecontractTbAF.setList(list);
    }
    request.getSession().setAttribute("pl121Id", pkId);
    endorsecontractTbAF.setContractId(id);
    endorsecontractTbAF.setDebitter(debitter);// 抵押人
    endorsecontractTbAF.setOffice(office);// 抵押权人
    endorsecontractTbAF.setPledgePerson(debitter);
    return endorsecontractTbAF;
  }

  // 打印
  // public EndorsecontractTbAF queryPledgeContractList_(String id,
  // Pagination pagination, SecurityInfo securityInfo,
  // HttpServletRequest request) throws Exception, BusinessException {
  // // TODO Auto-generated method stub
  // EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
  // String office = this.getCenter(securityInfo);
  // String pkId = pledgeContractDAO.queryMaxId(id);
  // endorsecontractTbAF = this.queryPledgeContractById(pkId, office);
  // return endorsecontractTbAF;
  // }

  /**
   * 根据123Id查询123信息（默认传最大ID值）
   * 
   * @param id
   * @return
   */
  public EndorsecontractTdAF queryAssurerById(String id) {
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    Assurer assurer = assurerDAO.queryById(new Integer(id));
    String name = "";
    if (assurer != null && !"".equals(assurer)) {
      name = borrowerDAO.queryBorrowerName(assurer.getContractId());
      endorsecontractTdAF.setContractId(assurer.getContractId());// 合同编号

      endorsecontractTdAF.setDebitter(name);// 借款人
      BigDecimal tempEmpId = assurer.getEmpId();
      if (tempEmpId != null) {
        endorsecontractTdAF.setEmpId(tempEmpId.toString());// 职工编号
      }
      endorsecontractTdAF.setEmpName(assurer.getEmpName());// 职工名称
      endorsecontractTdAF.setCardKind(assurer.getCardKind());// 证件类型
      endorsecontractTdAF.setCardNum(assurer.getCardNum());// 证件号码
      endorsecontractTdAF.setSex(assurer.getSex());// 性别
      endorsecontractTdAF.setBirthday(assurer.getBirthday());// 出生日期
      BigDecimal tempSalary = assurer.getSalary();
      if (tempSalary != null) {
        endorsecontractTdAF.setSalary(tempSalary.toString());// 月工资额
      }
      BigDecimal tempMonthPay = assurer.getMonthPay();
      if (tempMonthPay != null) {
        endorsecontractTdAF.setMonthPay(tempMonthPay.toString());// 月缴存额
      }
      BigDecimal tempBalance = assurer.getBalance();
      if (tempBalance != null) {
        endorsecontractTdAF.setBalance(tempBalance.toString());// 账户余额
      }
      endorsecontractTdAF.setStatus(assurer.getStatus());// 状态
      endorsecontractTdAF.setTel(assurer.getTel());// 固定电话
      endorsecontractTdAF.setMobile(assurer.getMobile());// 移动电话
      endorsecontractTdAF.setHomeTel(assurer.getHomeTel());// 家庭电话
      endorsecontractTdAF.setHomeAddr(assurer.getHomeAddr());// 家庭地址
      endorsecontractTdAF.setHomeMai(assurer.getHomeMail());// 家庭邮编
      BigDecimal tempOrgId = assurer.getOrgId();
      if (tempOrgId != null) {
        endorsecontractTdAF.setOrgId(tempOrgId.toString());// 单位编号
      }
      endorsecontractTdAF.setOrgName(assurer.getOrgName());// 单位名称
      endorsecontractTdAF.setOrgAddr(assurer.getOrgAddr());// 单位地址
      endorsecontractTdAF.setOrgTel(assurer.getOrgTel());// 单位电话
      endorsecontractTdAF.setOrgMail(assurer.getOrgMail());// 单位邮编
      String tempEmpSt = assurer.getEmpSt();
      if (tempEmpSt != null) {
        try {
          endorsecontractTdAF.setEmpSt(BusiTools.getBusiValue(Integer
              .parseInt("" + tempEmpSt), BusiConst.OLDPAYMENTSTATE));
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }// 账户状态
      }
    }
    return endorsecontractTdAF;
  }

  /**
   * 根据122Id查询122信息（默认传最大ID值）
   * 
   * @param id
   * @return
   */
  public EndorsecontractTcAF queryImpawnContractById(String id, String office) {
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    if (id != null) {
      ImpawnContract impawnContract = impawnContractDAO.queryById(new Integer(
          id));
      String name = borrowerDAO.queryBorrowerName(impawnContract
          .getContractId());
      String paperPersonName = "";
      paperPersonName = impawnContract.getName();

      endorsecontractTcAF.setContractId(impawnContract.getContractId());// 合同编号
      endorsecontractTcAF.setDebitter(name);// 借款人
      endorsecontractTcAF.setImpawnContractId(impawnContract
          .getImpawnContractId());// 质押合同编号
      endorsecontractTcAF.setAssistantOrgName(assistantOrgDAO
          .queryOrgName(impawnContract.getContractId()));// 担保公司名称
      if (paperPersonName != null && !"".equals(paperPersonName)) {
        endorsecontractTcAF.setImpawnPerson(paperPersonName);// 抵押人
        endorsecontractTcAF.setPaperPersonName(paperPersonName);// 所有权人姓名
      } else {
        endorsecontractTcAF.setImpawnPerson(name);// 抵押人
        endorsecontractTcAF.setPaperPersonName(name);// 所有权人姓名
      }
      endorsecontractTcAF.setOffice(office);// 质押权人
      endorsecontractTcAF.setImpawnMatterName(impawnContract
          .getImpawnMatterName());// 质押物名称
      BigDecimal tempValue = impawnContract.getImpawnValue();

      if (tempValue != null) {
        endorsecontractTcAF.setImpawnValue(tempValue.toString());// 质押物价值
      }

      endorsecontractTcAF.setCardKind(impawnContract.getCardKind());// 证件类型
      endorsecontractTcAF.setCarNum(impawnContract.getCardNum());// 证件号码
      endorsecontractTcAF.setPaperNum(impawnContract.getPaperNum());// 所有权证编号
      endorsecontractTcAF.setPaperName(impawnContract.getPaperName());// 所有权证名称
      endorsecontractTcAF.setTel(impawnContract.getTel());// 固定电话
      endorsecontractTcAF.setMobile(impawnContract.getMobile());// 移动电话
    }
    return endorsecontractTcAF;
  }

  /**
   * 根据121Id查询121信息（默认传最大ID值）
   * 
   * @param id
   * @return
   */
  public EndorsecontractTbAF queryPledgeContractById(String id, String office) {
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    PledgeContract pledgeContract = pledgeContractDAO
        .queryById(new Integer(id));

    String debitter = "";// 借款人
    String paperPersonName = "";// 所有权人名称
    debitter = borrowerDAO.queryBorrowerName(pledgeContract.getContractId());
    Houses hourses = housesDAO.queryById(pledgeContract.getContractId());
    paperPersonName = pledgeContract.getName();
    if (hourses != null && hourses.getRebate() != null
        && !"".equals(hourses.getRebate())) {
      endorsecontractTbAF.setRebate(hourses.getRebate().toString());
    }
    endorsecontractTbAF.setContractId(pledgeContract.getContractId());// 合同编号
    endorsecontractTbAF.setDebitter(debitter);// 借款人
    if (paperPersonName != null && !"".equals(paperPersonName)) {
      endorsecontractTbAF.setPledgePerson(paperPersonName);
      endorsecontractTbAF.setPaperPersonName(paperPersonName);// 所有权人名称
    } else {
      endorsecontractTbAF.setPledgePerson(debitter);// 抵押人
      endorsecontractTbAF.setPaperPersonName(debitter);// 所有权人名称
    }
    endorsecontractTbAF.setOffice(office);// 抵押权人
    endorsecontractTbAF.setPledgeContractId(pledgeContract
        .getPledgeContractId());// 抵押合同编号
    endorsecontractTbAF.setAssistantOrgName(assistantOrgDAO
        .queryOrgName(pledgeContract.getContractId()));// 担保公司名称
    endorsecontractTbAF.setPledgeMatterName(pledgeContract
        .getPledgeMatterName());// 抵押物名称
    endorsecontractTbAF.setPaperNum(pledgeContract.getPaperNum());// 所有权证编号
    endorsecontractTbAF.setPaperName(pledgeContract.getPaperName());// 所有权证名称

    endorsecontractTbAF.setCarNum(pledgeContract.getCardNum());// 所有权人证件号码
    endorsecontractTbAF.setTel(pledgeContract.getTel());// 固定电话
    endorsecontractTbAF.setMobile(pledgeContract.getMobile());// 行动电话
    endorsecontractTbAF.setDyrgddh(pledgeContract.getTel());// 抵押人固定电话
    endorsecontractTbAF.setDyryddh(pledgeContract.getMobile());// 抵押人行动电话
    endorsecontractTbAF.setPledgeAddr(pledgeContract.getPledgeAddr());// 抵押物地址
    BigDecimal tempBig = pledgeContract.getArea();
    if (tempBig != null && !"".equals(tempBig)) {
      endorsecontractTbAF.setArea(tempBig.toString());// 面积
    }
    endorsecontractTbAF.setBuyHouseContractId(housesDAO
        .queryBuyHouseContractId(pledgeContract.getContractId()));// 购房合同编号
    BigDecimal tempBig2 = pledgeContract.getPledgeValue();
    if (tempBig2 != null && !"".equals(tempBig2)) {
      endorsecontractTbAF.setPledgeValue(tempBig2.toString());// 抵押值
    }
    BigDecimal tempBig3 = pledgeContract.getEvaluateValue();
    if (tempBig3 != null && !"".equals(tempBig3)) {
      endorsecontractTbAF.setEvaluateValue(tempBig3.toString());// 评估值
    }
    endorsecontractTbAF.setCardKind(pledgeContract.getCardKind());// 证件类型
    String buyHouseContractId = pledgeContract.getReserveaA();
    if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
      endorsecontractTbAF.setBuyHouseContractId(buyHouseContractId);// 购房合同编号
    }
    return endorsecontractTbAF;
  }

  /**
   * TB分发确定按钮
   */
  public void sure(Integer id, String loanassistantorgId,
      SecurityInfo securityInfo, EndorsecontractTbAF endorsecontractTbAF)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // 根据ID（pl121Id），查询PL121
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT + "";

    String contractId = endorsecontractTbAF.getContractId().trim();// 合同ID
    String debitter = endorsecontractTbAF.getDebitter().trim();// 借款人姓名 PL110
    String pledgePerson = endorsecontractTbAF.getPledgePerson().trim();// 抵押人姓名
    String office = endorsecontractTbAF.getOffice().trim();// 抵押权人（即××中心）
    String pledgeContractId = endorsecontractTbAF.getPledgeContractId().trim();// 抵押合同编号
    String assistantOrgName = endorsecontractTbAF.getAssistantOrgName().trim();// 担保公司名称
    // String loanassistantorgId =
    // endorsecontractTbAF.getLoanassistantorgId();//担保公司编号
    String pledgeMatterName = endorsecontractTbAF.getPledgeMatterName().trim();// 抵押物名称
    String paperNum = endorsecontractTbAF.getPaperNum().trim();// 所有权证编号
    String paperName = endorsecontractTbAF.getPaperName().trim();// 所有权证名称
    String paperPersonName = endorsecontractTbAF.getPaperPersonName().trim();// 所有权人姓名
    String cardKind = endorsecontractTbAF.getCardKind().trim();// 所有权人证件类型
    String carNum = endorsecontractTbAF.getCarNum().trim();// 所有权人证件号码
    String tel = endorsecontractTbAF.getTel().trim();// 所有权人固定电话
    String mobile = endorsecontractTbAF.getMobile().trim();// 所有权人移动电话
    String pledgeAddr = endorsecontractTbAF.getPledgeAddr().trim();// 抵押物地址
    String area = endorsecontractTbAF.getArea().trim();// 建筑面积
    String buyHouseContractId = endorsecontractTbAF.getBuyHouseContractId()
        .trim();// 购房合同编号
    String pledgeValue = endorsecontractTbAF.getPledgeValue().trim();// 抵押值
    String evaluateValue = endorsecontractTbAF.getEvaluateValue().trim();// 评估值
    String rebate = endorsecontractTbAF.getRebate();
    PledgeContract pledgeContract = null;
    try {
      System.out.println("sd...."+id);
      if (id != null && !"".equals(id)) {
        // ********************************************************************************//
        // 将从PL114查询出的购房合同编号，插入到pl121 备注A中，以后也从这里查询出来
        pledgeContract = pledgeContractDAO.queryById(id);// 主键
      }
      if (pledgeContract != null) {// 存在：更新

        System.out.println("contractId...."+contractId);
        if (contractId != null && !"".equals(contractId)) {
          // // 更新购房合同编号
          Houses houses = housesDAO.queryById(contractId);
          // houses.setBuyHouseContractId(buyHouseContractId);
          if(rebate!=null&&!"".equals(rebate)){
            houses.setRebate(new BigDecimal(rebate));
          }
         
          // pledgeContract.setReserveaA(buyHouseContractId);
        }
        // ********************************************************************************//
        // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
        // // 更新担保公司
        // // String assistantOrgId = assistantOrgDAO.queryId(contractId);
        // // if(assistantOrgId != null && !"".equals(assistantOrgId)){
        // AssistantOrg assistantOrg = assistantOrgDAO.queryById(new Integer(
        // loanassistantorgId));
        // assistantOrg.setAssistantOrgName(assistantOrgName);
        // // }
        // }
        if (contractId != null && !"".equals(contractId)) {
          pledgeContract.setContractId(contractId);
        }
        if (pledgeContractId != null && !"".equals(pledgeContractId)) {
          pledgeContract.setPledgeContractId(pledgeContractId);
        }
        if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
          pledgeContract.setPledgeMatterName(pledgeMatterName);
        }
        if (pledgeValue != null && !"".equals(pledgeValue)) {
          pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
        }
        if (paperNum != null && !"".equals(paperNum)) {
          pledgeContract.setPaperNum(paperNum);
        }
        if (paperName != null && !"".equals(paperName)) {
          pledgeContract.setPaperName(paperName);
        }
        if (paperPersonName != null && !"".equals(paperPersonName)) {
          pledgeContract.setName(paperPersonName);
        }
        if (cardKind != null && !"".equals(cardKind)) {
          pledgeContract.setCardKind(cardKind);
        }
        if (carNum != null && !"".equals(carNum)) {
          pledgeContract.setCardNum(carNum);
        }
        if (tel != null && !"".equals(tel)) {
          pledgeContract.setTel(tel);
        }
        if (mobile != null && !"".equals(mobile)) {
          pledgeContract.setMobile(mobile);
        }
        if (pledgeAddr != null && !"".equals(pledgeAddr)) {
          pledgeContract.setPledgeAddr(pledgeAddr);
        }
        if (area != null && !"".equals(area)) {
          pledgeContract.setArea(new BigDecimal(area));
        }
        if (evaluateValue != null && !"".equals(evaluateValue)) {
          pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
        }
        if (rebate != null && !"".equals(rebate)) {
          pledgeContract.setRebate(rebate);
        }
        String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
        String bizId = pledgeContract.getId().toString();
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {// 不存在：插入

        if (contractId != null && !"".equals(contractId)) {
          // 更新购房合同编号

          Houses houses = housesDAO.queryById(contractId);
          if(rebate!=null&&!"".equals(rebate)){
            houses.setRebate(new BigDecimal(rebate));
          }
          

        }
        // // 插入担保公司
        // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
        // AssistantOrg assistantOrg = new AssistantOrg();
        // assistantOrg.setAssistantOrgName(assistantOrgName);
        // assistantOrgDAO.insert(assistantOrg);
        // }
        pledgeContract = new PledgeContract();
        if (contractId != null && !"".equals(contractId)) {
          pledgeContract.setContractId(contractId);
        }
        if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
          pledgeContract.setReserveaA(buyHouseContractId);
        }
        if (pledgeContractId != null && !"".equals(pledgeContractId)) {
          pledgeContract.setPledgeContractId(pledgeContractId);
        }
        if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
          pledgeContract.setPledgeMatterName(pledgeMatterName);
        }
        if (pledgeValue != null && !"".equals(pledgeValue)) {
          pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
        }
        if (paperNum != null && !"".equals(paperNum)) {
          pledgeContract.setPaperNum(paperNum);
        }
        if (paperName != null && !"".equals(paperName)) {
          pledgeContract.setPaperName(paperName);
        }
        if (paperPersonName != null && !"".equals(paperPersonName)) {
          pledgeContract.setName(paperPersonName);
        }
        if (cardKind != null && !"".equals(cardKind)) {
          pledgeContract.setCardKind(cardKind);
        }
        if (carNum != null && !"".equals(carNum)) {
          pledgeContract.setCardNum(carNum);
        }
        if (tel != null && !"".equals(tel)) {
          pledgeContract.setTel(tel);
        }
        if (mobile != null && !"".equals(mobile)) {
          pledgeContract.setMobile(mobile);
        }
        if (pledgeAddr != null && !"".equals(pledgeAddr)) {
          pledgeContract.setPledgeAddr(pledgeAddr);
        }
        if (area != null && !"".equals(area)) {
          pledgeContract.setArea(new BigDecimal(area));
        }
        if (evaluateValue != null && !"".equals(evaluateValue)) {
          pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
        }
        if (rebate != null && !"".equals(rebate)) {
          pledgeContract.setRebate(rebate);
        }
        pledgeContract.setOperator(operator);
        pledgeContract.setOpTime(new Date());
        pledgeContract.setStatus("0");
        String pContractId = pledgeContractDAO.insert(pledgeContract)
            .toString();
        String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
        String bizId = pContractId;
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 分发－－修改按钮
   */
  public EndorsecontractTbAF updatePledgeContract(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    String office = this.getCenter(securityInfo);// 中心
    endorsecontractTbAF = this.queryPledgeContractById(id, office);
    return endorsecontractTbAF;
  }

  /**
   * 分发－－删除按钮
   */
  public void deletePledgeContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    PledgeContract pledgeContract = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT + "";
    String opBizId = "";
    if (id != null && !"".equals(id)) {
      pledgeContract = pledgeContractDAO.queryById(new Integer(id));
      String bizId = pledgeContract.getContractId();
      if (pledgeContract != null) {
        pledgeContractDAO.deleteById(id);
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        // if (bizId != null && !"".equals(bizId)) {
        // opBizId = loanFlowTailDAO.queryHeadId(bizId);
        // }
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {
        throw new BusinessException("该纪录已经被删除！");
      }
    }

  }

  /** ****************************************************************** */
  /** **** 质押合同信息 **** */
  /** ****************************************************************** */

  public EndorsecontractTcAF queryImpawnContractList(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String contractId = id;// 合同编号
    String debitter = "";
    String assistantOrgName = null;
    // 判断003表 "AB" or "BA"
    String entruster = null;
    EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    if (id != null && !"".equals(id)) {
      assistantOrgName = loanContractDAO.queryAssOrgByIdYU(id);
    }
    try {
      if (id != null && !"".equals(id)) {
        entruster = this.getCenter(securityInfo);// 委托方**中心
        String office = this.getCenter(securityInfo);
        debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名

        // 查询PL111
        endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
            securityInfo);
        String isWrite = endorsecontractTaDTO.getIsWrite();// 是否签订合同
        String contractSt = endorsecontractTaDTO.getContractSt();// 合同状态
        // 条件判断
        String paramValue = this.queryParamValue(securityInfo);
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.审批通过 0 未签订合同
            // 判断120是否存在
            endorsecontractTcAF = this.isQueryPL122(id, debitter, office,
                request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        } else if ("BA".equals(paramValue)) {
          if ("2".equals(contractSt) && "0".equals(isWrite)) {
            // 判断120
            endorsecontractTcAF = this.isQueryPL122(id, debitter, office,
                request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        }
      }
    } catch (BusinessException e) {
      throw e;
    }
    request.getSession().setAttribute("contractId", id);// 把新输入或选中的ID放到SESSION中
    endorsecontractTcAF.setContractId(contractId);
    endorsecontractTcAF.setDebitter(debitter);
    String paperPersonName = endorsecontractTcAF.getPaperPersonName();// 所有权人
    if (paperPersonName == null || "".equals(paperPersonName)) {// 所有权人为空时，所有权人=质押人=借款人
      endorsecontractTcAF.setPaperPersonName(debitter);
      endorsecontractTcAF.setImpawnPerson(debitter);
    } else {// 所有权人不为空时,质押人=所有权人
      endorsecontractTcAF.setImpawnPerson(paperPersonName);
    }
    endorsecontractTcAF.setOffice(entruster);
    endorsecontractTcAF.setAssistantOrgName(assistantOrgName);
    return endorsecontractTcAF;
  }

  // 用于打印
  public EndorsecontractTcAF queryImpawnContractList_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String office = this.getCenter(securityInfo);
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String maxId = "";
    Object obj = (Object) request.getSession().getAttribute("pl122Id");
    if (obj != null) {
      maxId = obj.toString();
    }
    if (maxId == null || "".equals(maxId)) {
      maxId = impawnContractDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名
    endorsecontractTcAF = this.queryImpawnContractById(maxId, office);// 根据最大ID插叙pl122
    // 查询列表
    List list = impawnContractDAO.queryImpawnListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTcAF esTcAF = (EndorsecontractTcAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTcAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
        }
        esTcAF.setStatus(statuss);
        esTcAF.setImpawnPerson(debitter);// 质押人
        esTcAF.setOffice(office);// 质押权人
        if (esTcAF.getCardKind() != null && !esTcAF.getCardKind().equals("")) {
          // 转换证件类型
          esTcAF.setCardKind((BusiTools.getBusiValue(Integer.parseInt(esTcAF
              .getCardKind()), BusiConst.DOCUMENTSSTATE)));
        }
      }
    }
    endorsecontractTcAF.setList(list);
    String tempCardkind = endorsecontractTcAF.getCardKind();
    if (tempCardkind != null && !"".equals(tempCardkind)) {
      endorsecontractTcAF.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// 页面显示－－证件类型
    }
    endorsecontractTcAF.setContractId(id);
    endorsecontractTcAF.setImpawnPerson(debitter);// 质押人
    endorsecontractTcAF.setDebitter(debitter);
    return endorsecontractTcAF;
  }

  /**
   * 担保抵押变更专用
   */
  public EndorsecontractTcAF queryImpawnContractList_Chg(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String office = this.getCenter(securityInfo);
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String maxId = impawnContractDAO.queryMaxId(id);
    String debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名
    endorsecontractTcAF = this.queryImpawnContractById(maxId, office);// 根据最大ID插叙pl122
    // 查询列表
    List list = impawnContractDAO.queryImpawnListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTcAF esTcAF = (EndorsecontractTcAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTcAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
        }
        esTcAF.setStatus(statuss);
        esTcAF.setImpawnPerson(debitter);// 质押人
        esTcAF.setOffice(office);// 质押权人
      }
    }
    endorsecontractTcAF.setList(list);
    endorsecontractTcAF.setContractId(id);
    endorsecontractTcAF.setDebitter(debitter);
    return endorsecontractTcAF;
  }

  /**
   * 先判断PL120,后PL123
   * 
   * @param id
   * @param debitter
   * @param office
   * @param request
   * @return
   */
  public EndorsecontractTdAF isQueryPL123(String id, String debitter,
      String office, HttpServletRequest request) throws Exception,
      BusinessException {
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    List list = new ArrayList();
    String pl120id = this.queryPL120(id);
    List pl123IdList = new ArrayList();
    if (pl120id != null) {
      // 判断PL123
      pl123IdList = this.checkPL123(id);
      if (pl123IdList.size() != 0) {
        // 查询列表
        list = assurerDAO.queryAssurerListYU(id);
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            EndorsecontractTdAF esTdAF = (EndorsecontractTdAF) list.get(i);
            String statuss = "";
            String tempStatuss = esTdAF.getStatus();
            if (tempStatuss != null && !"".equals(tempStatuss)) {
              statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                  BusiConst.PLCOMMONSTATUS_1);
              esTdAF.setStatus(statuss);
            }
          }
        }
        // 查询PL123合同编号为联动合同编号，ID为最大值的信息
        String maxId = assurerDAO.queryMaxId(id);
        request.getSession().setAttribute("pl123Id", maxId);
        endorsecontractTdAF = this.queryAssurerById(maxId);// 根据最大ID查询pl123
        endorsecontractTdAF.setList(list);
      }
    } else {
      throw new BusinessException("请先录入借款合同！");
    }
    return endorsecontractTdAF;
  }

  /**
   * 先判断PL120,后PL122
   * 
   * @param id
   * @param debitter
   * @param office
   * @param request
   * @return
   */
  public EndorsecontractTcAF isQueryPL122(String id, String debitter,
      String office, HttpServletRequest request) throws Exception,
      BusinessException {
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    List list = new ArrayList();
    String pl120id = this.queryPL120(id);
    List pl122IdList = new ArrayList();
    if (pl120id != null) {
      // 判断PL122
      pl122IdList = this.checkPL122(id);
      if (pl122IdList.size() != 0) {
        // 查询列表
        list = impawnContractDAO.queryImpawnListYU(id);
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            EndorsecontractTcAF esTcAF = (EndorsecontractTcAF) list.get(i);
            String statuss = "";
            String tempStatuss = esTcAF.getStatus();
            if (tempStatuss != null && !"".equals(tempStatuss)) {
              statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                  BusiConst.PLCOMMONSTATUS_1);
            }
            esTcAF.setStatus(statuss);
            esTcAF.setImpawnPerson(debitter);// 质押人
            esTcAF.setOffice(office);// 质押权人
          }
        }
        // 查询PL122合同编号为联动合同编号，ID为最大值的信息
        String maxId = impawnContractDAO.queryMaxId(id);
        request.getSession().setAttribute("pl122Id", maxId);
        endorsecontractTcAF = this.queryImpawnContractById(maxId, office);// 根据最大ID插叙pl122
        endorsecontractTcAF.setList(list);
        endorsecontractTcAF.setDebitter(debitter);
        endorsecontractTcAF.setOffice(office);
      }
    } else {
      throw new BusinessException("请先录入借款合同！");
    }
    return endorsecontractTcAF;
  }

  /**
   * 先判断PL120,后PL121
   * 
   * @param id
   * @param debitter
   * @param office
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTbAF isQuery(String id, String debitter, String office,
      HttpServletRequest request) throws Exception, BusinessException {
    List list = new ArrayList();
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    String pl120id = this.queryPL120(id);
    List pl121IdList = new ArrayList();
    if (pl120id != null) {
      String assistantOrgName = loanContractDAO.queryAssOrgByIdYU(id);
      // 判断PL121
      pl121IdList = this.checkPL121(id);
      if (pl121IdList.size() != 0) {
        list = pledgeContractDAO.queryPledgeListYU(id);// 查询列表
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            EndorsecontractTbAF esTbAF = (EndorsecontractTbAF) list.get(i);
            String statuss = "";
            String tempStatuss = esTbAF.getStatus();
            if (tempStatuss != null && !"".equals(tempStatuss)) {
              statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                  BusiConst.PLCOMMONSTATUS_1);
            }
            esTbAF.setStatus(statuss);
            esTbAF.setDebitter(debitter);// 抵押人
            esTbAF.setOffice(office);// 抵押权人

          }
        }
        // 查询PL121合同编号为联动合同编号，ID为最大值的信息
        String maxId = pledgeContractDAO.queryMaxId(id);
        /** ************************************************** */
        /* 此方法可以提出来公用 this.queryPledgeContractById */
        /** *************************************************** */
        request.getSession().setAttribute("pl121Id", new Integer(maxId));
        endorsecontractTbAF = this.queryPledgeContractById(maxId, office);// 根据最大ID查询pl121
        endorsecontractTbAF.setList(list);
        endorsecontractTbAF.setAssistantOrgName(assistantOrgName);
      } else {// 否：只显示合同编号，抵押人，借款人，中心（不显示列表）,担保公司
        endorsecontractTbAF.setContractId(id);
        endorsecontractTbAF.setDebitter(debitter);
        endorsecontractTbAF.setPledgePerson(debitter);
        endorsecontractTbAF.setOffice(office);
        endorsecontractTbAF.setAssistantOrgName(assistantOrgName);
        endorsecontractTbAF.setIsPl121("0");
      }
    } else {
      throw new BusinessException("请先录入借款合同！");
    }
    return endorsecontractTbAF;
  }

  /**
   * 分发确定按钮 TC
   */
  public void sureTc(String id, String loanassistantorgId,
      SecurityInfo securityInfo, EndorsecontractTcAF endorsecontractTcAF)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // 根据ID（pl121Id），查询PL121
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_IMPAWNCONTRACT + "";

    String contractId = endorsecontractTcAF.getContractId().trim();// 合同ID
    String debitter = endorsecontractTcAF.getDebitter().trim();// 借款人姓名 PL110
    String impawnContractId = endorsecontractTcAF.getImpawnContractId().trim();// 质押合同编号
    String assistantOrgName = endorsecontractTcAF.getAssistantOrgName().trim();// 担保公司名称
    String impawnPerson = endorsecontractTcAF.getImpawnPerson().trim();// 质押人
    String office = endorsecontractTcAF.getOffice().trim();// 质押权人（即××中心）
    String impawnMatterName = endorsecontractTcAF.getImpawnMatterName().trim();// 质押物名称
    String impawnValue = endorsecontractTcAF.getImpawnValue().trim();// 质押物价值
    String paperPersonName = endorsecontractTcAF.getPaperPersonName().trim();// 所有权人姓名
    String cardKind = endorsecontractTcAF.getCardKind().trim();// 所有权人证件类型
    String carNum = endorsecontractTcAF.getCarNum().trim();// 所有权人证件号码
    String paperNum = endorsecontractTcAF.getPaperNum().trim();// 所有权证编号
    String paperName = endorsecontractTcAF.getPaperName().trim();// 所有权证名称
    String tel = endorsecontractTcAF.getTel().trim();// 所有权人固定电话
    String mobile = endorsecontractTcAF.getMobile().trim();// 所有权人移动电话

    ImpawnContract impawnContract = null;
    if (id != null && !"".equals(id)) {
      impawnContract = impawnContractDAO.queryById(new Integer(id));// 主键
      if (impawnContract != null) {// 存在：更新
      // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
      // // 更新担保公司
      // // String assistantOrgId = assistantOrgDAO.queryId(contractId);
      // AssistantOrg assistantOrg = assistantOrgDAO.queryById(new Integer(
      // loanassistantorgId));
      // assistantOrg.setAssistantOrgName(assistantOrgName);
      // }
        if (contractId != null && !"".equals(contractId)) {
          impawnContract.setContractId(contractId);
        }
        if (impawnContractId != null && !"".equals(impawnContractId)) {
          impawnContract.setImpawnContractId(impawnContractId);
        }
        if (impawnMatterName != null && !"".equals(impawnMatterName)) {
          impawnContract.setImpawnMatterName(impawnMatterName);
        }
        if (impawnValue != null && !"".equals(impawnValue)) {
          impawnContract.setImpawnValue(new BigDecimal(impawnValue));
        }
        if (paperPersonName != null && !"".equals(paperPersonName)) {
          impawnContract.setName(paperPersonName);
        }
        if (cardKind != null && !"".equals(cardKind)) {
          impawnContract.setCardKind(cardKind);
        }
        if (carNum != null && !"".equals(carNum)) {
          impawnContract.setCardNum(carNum);
        }
        if (paperNum != null && !"".equals(paperNum)) {
          impawnContract.setPaperNum(paperNum);
        }
        if (paperName != null && !"".equals(paperName)) {
          impawnContract.setPaperName(paperName);
        }
        if (tel != null && !"".equals(tel)) {
          impawnContract.setTel(tel);
        }
        if (mobile != null && !"".equals(mobile)) {
          impawnContract.setMobile(mobile);
        }
        if (operator != null && !"".equals(operator)) {
          impawnContract.setOperator(operator);
        }
        impawnContract.setOpTime(new Date());
        String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
        String bizId = impawnContract.getId().toString();
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      }
    } else {// 不存在：插入

      // 插入担保公司
      // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
      // AssistantOrg assistantOrg = new AssistantOrg();
      // assistantOrg.setAssistantOrgName(assistantOrgName);
      // assistantOrgDAO.insert(assistantOrg);
      // }
      impawnContract = new ImpawnContract();
      if (contractId != null && !"".equals(contractId)) {
        impawnContract.setContractId(contractId);
      }
      if (impawnContractId != null && !"".equals(impawnContractId)) {
        impawnContract.setImpawnContractId(impawnContractId);
      }
      if (impawnMatterName != null && !"".equals(impawnMatterName)) {
        impawnContract.setImpawnMatterName(impawnMatterName);
      }
      if (impawnValue != null && !"".equals(impawnValue)) {
        impawnContract.setImpawnValue(new BigDecimal(impawnValue));
      }
      if (paperPersonName != null && !"".equals(paperPersonName)) {
        impawnContract.setName(paperPersonName);
      }
      if (cardKind != null && !"".equals(cardKind)) {
        impawnContract.setCardKind(cardKind);
      }
      if (carNum != null && !"".equals(carNum)) {
        impawnContract.setCardNum(carNum);
      }
      if (paperNum != null && !"".equals(paperNum)) {
        impawnContract.setPaperNum(paperNum);
      }
      if (paperName != null && !"".equals(paperName)) {
        impawnContract.setPaperName(paperName);
      }
      if (tel != null && !"".equals(tel)) {
        impawnContract.setTel(tel);
      }
      if (mobile != null && !"".equals(mobile)) {
        impawnContract.setMobile(mobile);
      }
      if (operator != null && !"".equals(operator)) {
        impawnContract.setOperator(operator);
      }
      impawnContract.setOpTime(new Date());
      impawnContract.setStatus("0");
      String iContractId = impawnContractDAO.insert(impawnContract).toString();
      String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
      String bizId = iContractId;
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    }
  }

  /**
   * TC 分发修改按钮
   */
  public EndorsecontractTcAF updateImpawnContract(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String office = this.getCenter(securityInfo);// 中心
    endorsecontractTcAF = this.queryImpawnContractById(id, office);
    return endorsecontractTcAF;
  }

  /**
   * TC 删除按钮
   */
  public void deleteImpawnContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ImpawnContract impawnContract = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT + "";
    if (id != null && !"".equals(id)) {
      impawnContract = impawnContractDAO.queryById(new Integer(id));
      String bizId = impawnContract.getContractId();
      if (impawnContract != null) {
        impawnContractDAO.deleteById(id);
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {
        throw new BusinessException("该纪录已经被删除！");
      }
    }
  }

  /** ****************************************************************** */
  /** **** 保证人信息 **** */
  /** ****************************************************************** */

  /**
   * TD查询
   */
  public EndorsecontractTdAF queryAssurerList(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    // 判断003表 "AB" or "BA"
    EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    String debitter = "";
    try {
      if (id != null && !"".equals(id)) {
        String office = this.getCenter(securityInfo);
        debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名

        // 查询PL111
        endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
            securityInfo);
        String isWrite = endorsecontractTaDTO.getIsWrite();// 是否签订合同
        String contractSt = endorsecontractTaDTO.getContractSt();// 合同状态
        // 条件判断
        String paramValue = this.queryParamValue(securityInfo);
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.审批通过 0 未签订合同
            // 判断120是否存在
            endorsecontractTdAF = this.isQueryPL123(id, debitter, office,
                request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        } else if ("BA".equals(paramValue)) {
          if ("2".equals(contractSt) && "0".equals(isWrite)) {
            // 判断120
            endorsecontractTdAF = this.isQueryPL123(id, debitter, office,
                request);
          } else {
            throw new BusinessException("该合同编号不存在或状态不对！");
          }
        }
      }
    } catch (BusinessException e) {
      throw e;
    }
    request.getSession().setAttribute("contractId", id);// 把新输入或选中的ID放到SESSION中
    endorsecontractTdAF.setContractId(id);
    endorsecontractTdAF.setDebitter(debitter);
    return endorsecontractTdAF;
  }

  // EMPID 查询
  public EndorsecontractTdAF queryAssurerListByEmpId(String id, String orgId,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    endorsecontractTdAF = empDAO.queryListByEmpId(id, orgId);
    request.getSession().setAttribute("contractId", id);// 把新输入或选中的ID放到SESSION中
    return endorsecontractTdAF;
  }

  // 用于打印
  public EndorsecontractTdAF queryAssurerList_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    // 查询PL123合同编号为联动合同编号，ID为最大值的信息
    String maxId = "";
    maxId = (String) request.getSession().getAttribute("pl123Id");
    if (maxId == null || "".equals(maxId)) {
      maxId = assurerDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名
    if (maxId != null && !"".equals(maxId)) {
      request.getSession().setAttribute("pl123Id", maxId);
      endorsecontractTdAF = this.queryAssurerById(maxId);// 根据最大ID查询pl123
      String sex = endorsecontractTdAF.getSex();
      String tempSex = "";
      if (sex != null && !"".equals(sex)) {
        tempSex = BusiTools.getBusiValue(Integer.parseInt(sex), BusiConst.SEX);
      }
      endorsecontractTdAF.setSex(tempSex);
    }
    // 查询列表
    List list = assurerDAO.queryAssurerListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTdAF esTdAF = (EndorsecontractTdAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTdAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
          esTdAF.setStatus(statuss);
        }
        if (esTdAF.getCardKind() != null && !esTdAF.getCardKind().equals("")) {
          // 转换证件类型
          esTdAF.setCardKind((BusiTools.getBusiValue(Integer.parseInt(esTdAF
              .getCardKind()), BusiConst.DOCUMENTSSTATE)));
        }
        // 转换性别
        if (esTdAF.getSex() != null && !esTdAF.getSex().equals("")) {
          // 转换证件类型
          esTdAF.setSex((BusiTools.getBusiValue(Integer.parseInt(esTdAF
              .getSex()), BusiConst.SEX)));
        }
      }
    }
    endorsecontractTdAF.setContractId(id);
    endorsecontractTdAF.setDebitter(debitter);
    endorsecontractTdAF.setList(list);
    String tempCardkind = endorsecontractTdAF.getCardKind();
    if (tempCardkind != null && !"".equals(tempCardkind)) {
      endorsecontractTdAF.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// 页面显示－－证件类型
    }
    String tempSex = endorsecontractTdAF.getSex();
    if (tempSex != null && !"".equals(tempSex)) {
      endorsecontractTdAF.setSex(tempSex);
    }
    return endorsecontractTdAF;
  }

  // 变更
  public EndorsecontractTdAF queryAssurerList_Chg(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    // 查询PL123合同编号为联动合同编号，ID为最大值的信息
    String maxId = "";
    maxId = (String) request.getSession().getAttribute("pl123Id");
    if (maxId == null || "".equals(maxId)) {
      maxId = assurerDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// 根据合同ID查询借款人姓名
    if (maxId != null && !"".equals(maxId)) {
      request.getSession().setAttribute("pl123Id", maxId);
      endorsecontractTdAF = this.queryAssurerById(maxId);// 根据最大ID查询pl123
      // String sex = endorsecontractTdAF.getSex();
      // String tempSex = "";
      // if (sex != null && !"".equals(sex)) {
      // tempSex = BusiTools.getBusiValue(Integer.parseInt(sex), BusiConst.SEX);
      // }
      // endorsecontractTdAF.setSex(sex);
    }
    // 查询列表
    List list = assurerDAO.queryAssurerListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTdAF esTdAF = (EndorsecontractTdAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTdAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
          esTdAF.setStatus(statuss);
        }
      }
    }
    endorsecontractTdAF.setContractId(id);
    endorsecontractTdAF.setDebitter(debitter);
    endorsecontractTdAF.setList(list);
    // String tempCardkind=endorsecontractTdAF.getCardKind();
    // if(tempCardkind != null && !"".equals(tempCardkind)){
    // endorsecontractTdAF.setCardKind(BusiTools.getBusiValue(Integer
    // .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// 页面显示－－证件类型
    // }

    return endorsecontractTdAF;
  }

  /**
   * 分发 确认按钮
   */
  public void sureTd(String id, SecurityInfo securityInfo,
      EndorsecontractTdAF endorsecontractTdAF) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    // 根据ID（pl121Id），查询PL121
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER + "";

    String contractId = endorsecontractTdAF.getContractId().trim();// 合同ID
    String debitter = endorsecontractTdAF.getDebitter().trim();// 借款人姓名 PL110
    String empId = endorsecontractTdAF.getEmpId().trim();// 职工编号
    String empName = endorsecontractTdAF.getEmpName().trim();// 职工姓名
    String cardKind = endorsecontractTdAF.getCardKind().trim();// 证件类型
    String cardNum = endorsecontractTdAF.getCardNum().trim();// 证件号码
    String sex = endorsecontractTdAF.getSex().trim();// 性别
    String birthday = endorsecontractTdAF.getBirthday().trim();// 出生日期
    String salary = endorsecontractTdAF.getSalary().trim();// 月工资额
    String monthPay = endorsecontractTdAF.getMonthPay().trim();// 月缴存额
    String balance = endorsecontractTdAF.getBalance().trim();// 账户余额
    String empSt = endorsecontractTdAF.getEmpSt().trim();// 账户状态
    String tel = endorsecontractTdAF.getTel().trim();// 固定电话
    String mobile = endorsecontractTdAF.getMobile().trim();// 行动电话
    String homeTel = endorsecontractTdAF.getHomeTel().trim();// 家庭电话
    String homeAddr = endorsecontractTdAF.getHomeAddr().trim();// 家庭住址
    String homeMail = endorsecontractTdAF.getHomeMai().trim();// 家庭邮编
    String orgId = endorsecontractTdAF.getOrgId().trim();// 单位编号
    String orgName = endorsecontractTdAF.getOrgName().trim();// 单位名称
    String orgAddr = endorsecontractTdAF.getOrgAddr().trim();// 单位地址
    String orgTel = endorsecontractTdAF.getOrgTel().trim();// 单位电话
    String orgMail = endorsecontractTdAF.getOrgMail().trim();// 单位邮政编号

    Assurer assurer = null;
    // if (id != null && !"".equals(id)) {
    // assurer = assurerDAO.queryById(new Integer(id));// 主键查询

    // }
    assurer = assurerDAO.queryCautionerInfo(contractId, empName, cardNum);
    if (assurer != null) {// 存在：更新
      if (empId != null && !"".equals(empId)) {
        assurer.setEmpId(new BigDecimal(empId));
      }
      if (empName != null && !"".equals(empName)) {
        assurer.setEmpName(empName);
      }
      if (cardKind != null && !"".equals(cardKind)) {
        assurer.setCardKind(cardKind);
      }
      if (cardNum != null && !"".equals(cardNum)) {
        assurer.setCardNum(cardNum);
      }
      if (sex != null && !"".equals(sex)) {
        assurer.setSex(sex);
      }
      if (birthday != null && !"".equals(birthday)) {
        assurer.setBirthday(birthday);
      }
      if (salary != null && !"".equals(salary)) {
        assurer.setSalary(new BigDecimal(salary));
      }
      if (monthPay != null && !"".equals(monthPay)) {
        assurer.setMonthPay(new BigDecimal(monthPay));
      }
      if (balance != null && !"".equals(balance)) {
        assurer.setBalance(new BigDecimal(balance));
      }
      if (empSt != null && !"".equals(empSt)) {
        int tempEmpSt = BusiTools.getBusiKey(empSt, BusiConst.OLDPAYMENTSTATE);
        assurer.setEmpSt(new Integer(tempEmpSt).toString());
      }
      if (tel != null && !"".equals(tel)) {
        assurer.setTel(tel);
      }
      if (mobile != null && !"".equals(mobile)) {
        assurer.setMobile(mobile);
      }
      if (homeTel != null && !"".equals(homeTel)) {
        assurer.setHomeTel(homeTel);
      }
      if (homeAddr != null && !"".equals(homeAddr)) {
        assurer.setHomeAddr(homeAddr);
      }
      if (homeMail != null && !"".equals(homeMail)) {
        assurer.setHomeMail(homeMail);
      }
      if (orgId != null && !"".equals(orgId)) {
        assurer.setOrgId(new BigDecimal(orgId));
      }
      if (orgName != null && !"".equals(orgName)) {
        assurer.setOrgName(orgName);
      }
      if (orgAddr != null && !"".equals(orgAddr)) {
        assurer.setOrgAddr(orgAddr);
      }
      if (orgTel != null && !"".equals(orgTel)) {
        assurer.setOrgTel(orgTel);
      }
      if (orgMail != null && !"".equals(orgMail)) {
        assurer.setOrgMail(orgMail);
      }
      assurer.setOperator(operator);// 操作员
      assurer.setOpTime(new Date());// 操作日期
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = assurer.getId().toString();
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);

    } else {// 不存在：插入

      assurer = new Assurer();
      if (contractId != null && !"".equals(contractId)) {
        assurer.setContractId(contractId);
      }
      // if(debitter != null && !"".equals(debitter)){
      // Borrower borrower = new Borrower();
      // borrower.setContractId(contractId);
      // borrower.setBorrowerName(debitter);
      // borrowerDAO.insert(borrower);
      // }
      if (empId != null && !"".equals(empId)) {
        assurer.setEmpId(new BigDecimal(empId));
      }
      if (empName != null && !"".equals(empName)) {
        assurer.setEmpName(empName);
      }
      if (cardKind != null && !"".equals(cardKind)) {
        assurer.setCardKind(cardKind);
      }
      if (cardNum != null && !"".equals(cardNum)) {
        assurer.setCardNum(cardNum);
      }
      if (sex != null && !"".equals(sex)) {
        assurer.setSex(sex);
      }
      if (birthday != null && !"".equals(birthday)) {
        assurer.setBirthday(birthday);
      }
      if (salary != null && !"".equals(salary)) {
        assurer.setSalary(new BigDecimal(salary));
      }
      if (monthPay != null && !"".equals(monthPay)) {
        assurer.setMonthPay(new BigDecimal(monthPay));
      }
      if (balance != null && !"".equals(balance)) {
        assurer.setBalance(new BigDecimal(balance));
      }
      if (empSt != null && !"".equals(empSt)) {
        int tempEmpSt = BusiTools.getBusiKey(empSt, BusiConst.OLDPAYMENTSTATE);
        assurer.setEmpSt(new Integer(tempEmpSt).toString());
      }
      if (tel != null && !"".equals(tel)) {
        assurer.setTel(tel);
      }
      if (mobile != null && !"".equals(mobile)) {
        assurer.setMobile(mobile);
      }
      if (homeTel != null && !"".equals(homeTel)) {
        assurer.setHomeTel(homeTel);
      }
      if (homeAddr != null && !"".equals(homeAddr)) {
        assurer.setHomeAddr(homeAddr);
      }
      if (homeMail != null && !"".equals(homeMail)) {
        assurer.setHomeMail(homeMail);
      }
      if (orgId != null && !"".equals(orgId)) {
        assurer.setOrgId(new BigDecimal(orgId));
      }
      if (orgName != null && !"".equals(orgName)) {
        assurer.setOrgName(orgName);
      }
      if (orgAddr != null && !"".equals(orgAddr)) {
        assurer.setOrgAddr(orgAddr);
      }
      if (orgTel != null && !"".equals(orgTel)) {
        assurer.setOrgTel(orgTel);
      }
      if (orgMail != null && !"".equals(orgMail)) {
        assurer.setOrgMail(orgMail);
      }
      assurer.setStatus("0");// 保证人状态 0正常
      assurer.setOperator(operator);// 操作员
      assurer.setOpTime(new Date());// 操作日期
      String assurerId = assurerDAO.insert(assurer).toString();
      String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
      String bizId = assurerId;
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    }
  }

  /**
   * 分发修改
   */
  public EndorsecontractTdAF updateAssurer(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    endorsecontractTdAF = this.queryAssurerById(id);
    return endorsecontractTdAF;
  }

  /**
   * 分发删除
   */
  public void deleteAssurer(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    Assurer assurer = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER + "";
    if (id != null && !"".equals(id)) {
      assurer = assurerDAO.queryById(new Integer(id));
      String bizId = assurer.getContractId();
      if (assurer != null) {
        assurerDAO.deleteById(id);
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {
        throw new BusinessException("该纪录已经被删除！");
      }
    }
  }

  /**
   * 通过ID查EMPID
   */
  public String selectAssurerEmpIdById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String empId = assurerDAO.queryEmpId(id);
    return empId;
  }

  /** ****************************************************************** */
  /** **** 维护 **** */
  /** ****************************************************************** */
  /**
   * 维护查询
   */
  public EndorsecontractTeAF queryList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    int count = 0;
    List list = new ArrayList();
    EndorsecontractTeAF endorsecontractTeAF = new EndorsecontractTeAF();
    HashMap queryCriterions = new HashMap();
    String type = this.queryParamValue(securityInfo);
    queryCriterions = (HashMap) pagination.getQueryCriterions();
    /**
     * 营口为AB (无需修改BA方向，如需修改参照AB方向)
     */
    if (type != null) {
      if (!type.equals("AB")) {
        if (queryCriterions.size() != 0) {// 有条件查询
          list = this.queryTeList(pagination, securityInfo);
          count = this.queryCountTelist(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
        } else {// 无条件查询
          // 查询PL111 是否有是否签订＝0 合同状态＝2 的纪录
          // pl111list = borrowerAccDAO.queryBorrowerListYU(securityInfo);
          // if (pl111list.size() != 0) {// 存在
          list = this.queryTe02List(pagination, securityInfo);
          count = this.queryCountTe02list(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
          // } else {
          // return new EndorsecontractTeAF();
          // }
        }
      } else {
        // 查询PL111 是否有是否签订＝0 合同状态＝4 的纪录
        if (queryCriterions.size() != 0) {// 有条件查询
          list = this.queryTeList(pagination, securityInfo);
          count = this.queryCountTelist(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
        } else {// 无条件查询
        // pl111list = borrowerAccDAO.queryBorrowerListYU_(securityInfo);
        // if (pl111list.size() != 0) {// 存在
          list = this.queryTe04List(pagination, securityInfo);
          count = this.queryCountTe04list(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
          // } else {
          // return new EndorsecontractTeAF();
          // }
        }
      }
    }
    List templist = new ArrayList();
    templist = endorsecontractTeAF.getList();
    if (templist != null) {
      EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
      for (int i = 0; i < templist.size(); i++) {
        endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
        // 转换合同状态
        endorsecontractTeDTO.setStrContractSt((BusiTools.getBusiValue(Integer
            .parseInt(endorsecontractTeDTO.getContractSt()),
            BusiConst.PLCONTRACTSTATUS)));
        String paramv = loanBankParaDAO.queryParam_value_hanl();
        String contractidhl = loanContractDAO.queryByIdYU(endorsecontractTeDTO
            .getContractId());
        if (paramv.equals("BA") && contractidhl == null) {
          endorsecontractTeDTO.setIsContract("1");// 维护页面上的不允许签订按钮可用
        } else {
          endorsecontractTeDTO.setIsContract("2");// 维护页面上的不允许签订按钮不可用
        }

        String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
        String term = endorsecontractTeDTO.getLoanTimeLimit();
        int tempDebitMoneyEndDate = 0;
        String postfix = "";
        String debitMoneyEndDate = "";
        BigDecimal tempBig = new BigDecimal(0.00);
        BigDecimal hundred = new BigDecimal(100);
        int intPostfix = 0;
        if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
            && term != null && !"".equals(term)) {
          postfix = debitMoneyStaDate.substring(6, 8);
          int temp_addYear = (Integer.parseInt(debitMoneyStaDate
              .substring(4, 6)) + Integer.parseInt(term)) / 12;
          int temp_moth = (Integer.parseInt(debitMoneyStaDate.substring(4, 6)) + Integer
              .parseInt(term)) % 12;
          intPostfix = Integer.parseInt(postfix);
          Integer tempInteger1 = new Integer(debitMoneyStaDate.substring(0, 6));
          Integer tempInteger2 = new Integer(term);
          // int tempInt1 = tempInteger1.intValue();
          // int tempInt2 = tempInteger2.intValue();
          // tempDebitMoneyEndDate = tempInt1 + tempInt2;
          if (temp_moth < 10) {
            if (temp_moth == 0) {
              tempDebitMoneyEndDate = new Integer((Integer
                  .parseInt(debitMoneyStaDate.substring(0, 4))
                  + temp_addYear - 1)
                  + "" + "12").intValue();
            } else {
              tempDebitMoneyEndDate = new Integer((Integer
                  .parseInt(debitMoneyStaDate.substring(0, 4)) + temp_addYear)
                  + "" + "0" + temp_moth).intValue();
            }
          } else {
            tempDebitMoneyEndDate = new Integer((Integer
                .parseInt(debitMoneyStaDate.substring(0, 4)) + temp_addYear)
                + "" + temp_moth).intValue();
          }
          String subString = new Integer(tempDebitMoneyEndDate).toString()
              .substring(4, 6);
          if ("01".equals(subString) || "03".equals(subString)
              || "05".equals(subString) || "07".equals(subString)
              || "08".equals(subString) || "10".equals(subString)
              || "12".equals(subString)) {
            if (intPostfix <= 31) {
              debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                  + postfix;
            }
          } else if ("04".equals(subString) || "06".equals(subString)
              || "09".equals(subString) || "11".equals(subString)) {
            if (intPostfix <= 30) {// 如果是小月，判断日期是否小于等于30，是就加上默认的，否就加30
              debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                  + postfix;
            } else {
              debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                  + "30";
            }
          } else if ("02".equals(subString)) {
            debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                + "28";
          }
          // debitMoneyEndDate = new
          // Integer(tempDebitMoneyEndDate).toString()+postfix;
          endorsecontractTeDTO.setEndDate(debitMoneyEndDate);
        }
        String loanMonthRate = endorsecontractTeDTO.getLoanMonthRate();
        if (loanMonthRate != null && !"".equals(loanMonthRate)) {
          tempBig = new BigDecimal(loanMonthRate).multiply(hundred);
        }
        endorsecontractTeDTO.setTemploanMonthRate(tempBig.toString() + "%");
      }
    }
    return endorsecontractTeAF;
  }

  // 0,2时调用
  public List queryTe02List(Pagination pagination, SecurityInfo securityInfo) {
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");// 合同编号
      String debitter = (String) pagination.getQueryCriterions()
          .get("debitter");// 借款人姓名

      String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// 证件号码
      String bank = (String) pagination.getQueryCriterions().get("bank");// 放款银行
      String loanTimeLimit = (String) pagination.getQueryCriterions().get(
          "loanTimeLimit");// 借款期限
      String startSDate = (String) pagination.getQueryCriterions().get(
          "startSDate");// 起始1日期
      String startEDate = (String) pagination.getQueryCriterions().get(
          "startEDate");// 起始2日期
      String endSDate = (String) pagination.getQueryCriterions()
          .get("endSDate");// 结束日期
      String endEDate = (String) pagination.getQueryCriterions()
          .get("endEDate");// 结束日期

      list = borrowerDAO.queryTeList02YU(contractId, debitter, empId, cardNum,
          bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate,
          start, orderBy, order, pageSize, page, securityInfo);
      if (list.size() != 0) {
        EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
        for (int i = 0; i < list.size(); i++) {
          endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
          String bankId = endorsecontractTeDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          String bankName = collBank.getCollBankName();
          endorsecontractTeDTO.setBank(bankName);
          endorsecontractTeDTO.setBankid(bankId);
          // 合同状态枚举转换
          String ContractSt = endorsecontractTeDTO.getContractStatus();
          endorsecontractTeDTO.setContractStatus(BusiTools.getBusiValue(Integer
              .parseInt(ContractSt + ""), BusiConst.PLISCONTRACTWRITE));
          // // 根据借款起始日期＋借款期限计算还款终止日期
          // String debitMoneyEndDate = "";
          // String postfix = "";
          // int tempDebitMoneyEndDate = 0;
          // String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
          // String term = endorsecontractTeDTO.getLoanTimeLimit();
          // if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          // && term != null && !"".equals(term)) {
          // Integer tempInteger1 = new Integer(debitMoneyStaDate.substring(0,
          // 6));
          // Integer tempInteger2 = new Integer(term);
          // int tempInt1 = tempInteger1.intValue();
          // int tempInt2 = tempInteger2.intValue();
          // tempDebitMoneyEndDate = tempInt1 + tempInt2;
          // debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString();
          // endorsecontractTeDTO.setEndDate(debitMoneyEndDate
          // + debitMoneyStaDate.substring(6, 8));
          // }
          if (Integer.parseInt(endorsecontractTeDTO.getLoanMode()) > 3) {
            endorsecontractTeDTO.setEntireYearMoney(endorsecontractTeDTO
                .getCorpusInterest());
            endorsecontractTeDTO.setCorpusInterest("0");
          } else {
            endorsecontractTeDTO.setEntireYearMoney("————————");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // count 纪录
  public int queryCountTe02list(Pagination pagination, SecurityInfo securityInfo) {
    int i = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");// 合同编号
    String debitter = (String) pagination.getQueryCriterions().get("debitter");// 借款人姓名

    String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// 证件号码
    String bank = (String) pagination.getQueryCriterions().get("bank");// 放款银行
    String loanTimeLimit = (String) pagination.getQueryCriterions().get(
        "loanTimeLimit");// 借款期限
    String startSDate = (String) pagination.getQueryCriterions().get(
        "startSDate");// 起始1日期
    String startEDate = (String) pagination.getQueryCriterions().get(
        "startEDate");// 起始2日期
    String endSDate = (String) pagination.getQueryCriterions().get("endSDate");// 结束日期
    String endEDate = (String) pagination.getQueryCriterions().get("endEDate");// 结束日期

    i = borrowerDAO.queryCountTeList02YU(contractId, debitter, empId, cardNum,
        bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate, start,
        orderBy, order, pageSize, page, securityInfo);
    return i;
  }

  // 0，4时调用
  public List queryTe04List(Pagination pagination, SecurityInfo securityInfo) {
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");// 合同编号
      String debitter = (String) pagination.getQueryCriterions()
          .get("debitter");// 借款人姓名

      String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// 证件号码
      String bank = (String) pagination.getQueryCriterions().get("bank");// 放款银行
      String loanTimeLimit = (String) pagination.getQueryCriterions().get(
          "loanTimeLimit");// 借款期限
      String startSDate = (String) pagination.getQueryCriterions().get(
          "startSDate");// 起始1日期
      String startEDate = (String) pagination.getQueryCriterions().get(
          "startEDate");// 起始2日期
      String endSDate = (String) pagination.getQueryCriterions()
          .get("endSDate");// 结束日期
      String endEDate = (String) pagination.getQueryCriterions()
          .get("endEDate");// 结束日期

      list = borrowerDAO.queryTeList04YU(contractId, debitter, empId, cardNum,
          bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate,
          start, orderBy, order, pageSize, page, securityInfo);
      if (list.size() != 0) {
        EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
        for (int i = 0; i < list.size(); i++) {
          endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
          String bankId = endorsecontractTeDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          String bankName = "";
          if (collBank != null && !"".equals(collBank)) {
            bankName = collBank.getCollBankName();
          }
          endorsecontractTeDTO.setBank(bankName);
          endorsecontractTeDTO.setBankid(bankId);
          // 合同状态枚举转换
          String ContractSt = endorsecontractTeDTO.getContractStatus();
          endorsecontractTeDTO.setContractStatus(BusiTools.getBusiValue(Integer
              .parseInt(ContractSt + ""), BusiConst.PLISCONTRACTWRITE));
          // 根据借款起始日期＋借款期限计算还款终止日期
          // String debitMoneyEndDate = "";
          // String postfix = "";
          // String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
          // String term = endorsecontractTeDTO.getLoanTimeLimit();
          // if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          // && term != null && !"".equals(term)) {
          // postfix = debitMoneyStaDate.substring(6, 8);
          // int tempTerm = new Integer(term).intValue();
          // debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate,
          // tempTerm);
          // endorsecontractTeDTO.setEndDate(debitMoneyEndDate
          // + postfix);
          // }
          if (Integer.parseInt(endorsecontractTeDTO.getLoanMode()) > 3) {
            endorsecontractTeDTO.setEntireYearMoney(endorsecontractTeDTO
                .getCorpusInterest());
            endorsecontractTeDTO.setCorpusInterest("0");
          } else {
            endorsecontractTeDTO.setEntireYearMoney("————————");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // count 纪录
  public int queryCountTe04list(Pagination pagination, SecurityInfo securityInfo) {
    int i = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");// 合同编号
    String debitter = (String) pagination.getQueryCriterions().get("debitter");// 借款人姓名

    String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// 证件号码
    String bank = (String) pagination.getQueryCriterions().get("bank");// 放款银行
    String loanTimeLimit = (String) pagination.getQueryCriterions().get(
        "loanTimeLimit");// 借款期限
    String startSDate = (String) pagination.getQueryCriterions().get(
        "startSDate");// 起始1日期
    String startEDate = (String) pagination.getQueryCriterions().get(
        "startEDate");// 起始2日期
    String endSDate = (String) pagination.getQueryCriterions().get("endSDate");// 结束日期
    String endEDate = (String) pagination.getQueryCriterions().get("endEDate");// 结束日期

    i = borrowerDAO.queryCountTeList04YU(contractId, debitter, empId, cardNum,
        bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate, start,
        orderBy, order, pageSize, page, securityInfo);
    return i;
  }

  // 有条件查询
  public List queryTeList(Pagination pagination, SecurityInfo securityInfo) {
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");// 合同编号
      String debitter = (String) pagination.getQueryCriterions()
          .get("debitter");// 借款人姓名

      String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// 证件号码
      String bank = (String) pagination.getQueryCriterions().get("bank");// 放款银行
      String loanTimeLimit = (String) pagination.getQueryCriterions().get(
          "loanTimeLimit");// 借款期限
      String startSDate = (String) pagination.getQueryCriterions().get(
          "startSDate");// 起始1日期
      String startEDate = (String) pagination.getQueryCriterions().get(
          "startEDate");// 起始2日期
      String endSDate = (String) pagination.getQueryCriterions()
          .get("endSDate");// 结束日期
      String endEDate = (String) pagination.getQueryCriterions()
          .get("endEDate");// 结束日期
      String contractSt = (String) pagination.getQueryCriterions().get(
          "contractSt");// 合同状态
      list = borrowerDAO.queryTeListYU(contractId, debitter, empId, cardNum,
          bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate,
          start, orderBy, order, pageSize, page, securityInfo, contractSt);
      if (list.size() != 0) {
        EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
        for (int i = 0; i < list.size(); i++) {
          endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
          String bankId = endorsecontractTeDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          String bankName = "";
          if (collBank != null && !"".equals(collBank)) {
            bankName = collBank.getCollBankName();
          }
          endorsecontractTeDTO.setBank(bankName);
          endorsecontractTeDTO.setBankid(bankId);
          // 合同状态枚举转换
          String ContractSt = endorsecontractTeDTO.getContractStatus();
          endorsecontractTeDTO.setContractStatus(BusiTools.getBusiValue(Integer
              .parseInt(ContractSt + ""), BusiConst.PLISCONTRACTWRITE));
          // 根据借款起始日期＋借款期限计算还款终止日期
          // String debitMoneyEndDate = "";
          // String postfix = "";
          // int tempDebitMoneyEndDate = 0;
          // String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
          // String term = endorsecontractTeDTO.getLoanTimeLimit();
          // if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          // && term != null && !"".equals(term)) {
          // postfix = debitMoneyStaDate.substring(6, 8);
          // int tempTerm = new Integer(term).intValue();
          // debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate,
          // tempTerm);
          // endorsecontractTeDTO.setEndDate(debitMoneyEndDate
          // + postfix);
          // }
          if (Integer.parseInt(endorsecontractTeDTO.getLoanMode()) > 3) {
            endorsecontractTeDTO.setEntireYearMoney(endorsecontractTeDTO
                .getCorpusInterest());
            endorsecontractTeDTO.setCorpusInterest("0");
          } else {
            endorsecontractTeDTO.setEntireYearMoney("————————");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // count 纪录有条件查询
  public int queryCountTelist(Pagination pagination, SecurityInfo securityInfo) {
    int i = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");// 合同编号
    String debitter = (String) pagination.getQueryCriterions().get("debitter");// 借款人姓名

    String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// 证件号码
    String bank = (String) pagination.getQueryCriterions().get("bank");// 放款银行
    String loanTimeLimit = (String) pagination.getQueryCriterions().get(
        "loanTimeLimit");// 借款期限
    String startSDate = (String) pagination.getQueryCriterions().get(
        "startSDate");// 起始1日期
    String startEDate = (String) pagination.getQueryCriterions().get(
        "startEDate");// 起始2日期
    String endSDate = (String) pagination.getQueryCriterions().get("endSDate");// 结束日期
    String endEDate = (String) pagination.getQueryCriterions().get("endEDate");// 结束日期
    String contractSt = (String) pagination.getQueryCriterions().get(
        "contractSt");// 合同状态
    i = borrowerDAO.queryCountTeListYU(contractId, debitter, empId, cardNum,
        bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate, start,
        orderBy, order, pageSize, page, securityInfo, contractSt);
    return i;
  }

  /**
   * 删除
   */
  public void deleteContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN
        + "";
    String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
    String opBizId = id;
    String bizId = id;
    BigDecimal zero = new BigDecimal(0);
    try {
      if (id != null && !"".equals(id)) {
        borrowerAcc = borrowerAccDAO.queryById(id);// 查询PL111
        if (borrowerAcc != null) {
          String isWrite = borrowerAcc.getIsContractWrite();
          if (isWrite.equals("0")) {
            borrowerAcc.setLoanBankId(zero);// 将PL111 银行设置为0
            borrowerAcc.setLoanKouAcc("");
            loanContractDAO.deleteById(id);// 删除pl120
            List list = pledgeContractDAO.queryIdByContractIdYU(id);// 查询pl121ID
            if (list.size() != 0) {
              for (int i = 0; i < list.size(); i++) {
                String pl121Id = (String) list.get(i);
                pledgeContractDAO.deleteById(pl121Id);// 删除PL121
              }
            }
            List list2 = impawnContractDAO.queryIdByContractIdYU(id);
            if (list2.size() != 0) {
              for (int i = 0; i < list2.size(); i++) {
                String pl122Id = (String) list2.get(i);
                impawnContractDAO.deleteById(pl122Id);// 删除pl122
              }
            }
            List list3 = assurerDAO.queryIdByContractIdYU(id);
            if (list3.size() != 0) {
              for (int i = 0; i < list3.size(); i++) {
                BigDecimal pl123Id = (BigDecimal) list3.get(i);
                assurerDAO.deleteById(pl123Id.toString());// 删除PL123
              }
            }

            this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
                opBizId);
          } else if (isWrite.equals("1")) {
            throw new BusinessException("合同已签订，不能删除！");
          }
        } else {
          throw new BusinessException("该纪录已经被删除！");
        }
      }
    } catch (Exception e) {
      throw new BusinessException("删除失败！");
    }
  }

  /**
   * 签订合同
   */
  public void sureContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN
        + "";
    String button = BusiLogConst.BIZLOG_ACTION_OPENING + "";
    String opBizId = id;
    String bizId = id;
    if (id != null && !"".equals(id)) {
      borrowerAcc = borrowerAccDAO.queryById(id);// 查询PL111
      if (borrowerAcc != null) {
        String isWrite = borrowerAcc.getIsContractWrite();
        if (isWrite.equals("0")) {
          borrowerAcc.setIsContractWrite("1");
          this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
              opBizId);
        } else {
          throw new BusinessException("已经签订了！");
        }
      } else {
        throw new BusinessException("已经签订了！");
      }
    }
  }

  /**
   * 撤销签订合同
   */
  public void delContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN
        + "";
    String button = BusiLogConst.BIZLOG_ACTION_REVOCATION + "";
    String opBizId = id;
    String bizId = id;
    // 判断参数里“贷款流程”是否是AB（A审批贷款B签订贷款）
    // 如果是：合同状态是4的可以撤销
    // 否则：合同状态是2的可以撤销
    borrowerAcc = borrowerAccDAO.queryById(id);// 查询PL111
    Integer loanBankId = new Integer(borrowerAcc.getLoanBankId().toString());
    int contractSt = Integer.parseInt(borrowerAcc.getContractSt());// 合同状态
    String temp_contractSt = BusiTools.getBusiValue(contractSt,
        BusiConst.PLCONTRACTSTATUS);
    String paramValue = loanBankParaDAO
        .queryParamValue_LJ(loanBankId, "A", "7");
    try {
      if (paramValue.equals(BusiConst.PLLOANPROCESS_LOANAPPROVAL
          + BusiConst.PLLOANPROCESS_CONTRACTSIGN)) {
        if (contractSt != BusiConst.PLCONTRACTSTATUS_THROUGHAPPROVAL) {
          throw new BusinessException("合同状态为" + temp_contractSt + "，不能撤销！");
        } else {
          borrowerAcc.setIsContractWrite("0");
          this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
              opBizId);
        }
      } else {
        if (contractSt != BusiConst.PLCONTRACTSTATUS_FIRSTCHECK) {
          throw new BusinessException("合同状态为" + temp_contractSt + "，不能撤销！");
        } else {
          borrowerAcc.setIsContractWrite("0");
          this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
              opBizId);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 根据合同编号查询其办事处下银行LIST
  public List queryBankList(String contractId, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    Borrower borrower = null;
    String office = null;
    try {
      // List loanBankNameList = new ArrayList();//银行
      List bankList = securityInfo.getDkUserBankList();
      if (bankList.size() != 0) {
        // Userslogincollbank userslogincollbank = null;
        // Iterator bank1 = bankList.iterator();
        if (contractId != null && !"".equals(contractId)) {
          borrower = borrowerDAO.queryById(contractId);
        }
        if (borrower != null) {
          office = borrower.getOffice();
          List banklist = loanBankDAO.queryBank_yqf(office, securityInfo);
          if (!banklist.isEmpty()) {
            Object obj[] = null;
            Iterator it = banklist.iterator();
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              list.add(new org.apache.struts.util.LabelValueBean(obj[1]
                  .toString(), obj[0].toString()));
            }
          }
        }
        // while (bank1.hasNext()) {
        // userslogincollbank = (Userslogincollbank) bank1.next();
        // Integer bankId = userslogincollbank.getCollBankId();
        // String bankName = userslogincollbank.getCollBankName();
        // if(bankId!=null && !"".equals(bankId)){
        // CollBank cb = collBankDAO.getCollBankByCollBankid(bankId.toString());
        // if(cb!=null){
        // String tempoffice = cb.getOffice();
        // if(office!=null){
        // if(office.equals(tempoffice)){
        // list.add(new org.apache.struts.util.LabelValueBean(bankName,
        // bankId.toString()));
        // }
        // }
        // }
        // }
        // // loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
        // // userslogincollbank.getCollBankName(), userslogincollbank
        // // .getCollBankId().toString()));
        // }
      }
      // ----------------------------------------------------------------------------------------
      // List list = new ArrayList();
      // List temp_list = null;
      // Borrower borrower = null;
      // String office = null;
      //
      // if(contractId!=null && !"".equals(contractId)){
      // borrower = borrowerDAO.queryById(contractId);
      // }
      // if (borrower != null) {
      // office = borrower.getOffice();
      // temp_list = collBankDAO.getOfficeCollBankList_yu(office,securityInfo);
      // Iterator bank = temp_list.iterator();
      // while (bank.hasNext()) {
      // CollBank collBank = (CollBank) bank.next();
      // list.add(new org.apache.struts.util.LabelValueBean(collBank
      // .getCollBankName(), collBank.getCollBankId().toString()));
      // }
      // }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 更新ta中的path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTa_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      LoanContract loanContract = loanContractDAO.queryById(contractId);
      if (loanContract != null) {
        loanContract.setPhotoUrl2(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 更新tb中的path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTb_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      PledgeContract pledgeContract = pledgeContractDAO.queryById(new Integer(contractId));
      if (pledgeContract != null) {
        pledgeContract.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 更新tc中的path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTc_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      ImpawnContract impawnContract = impawnContractDAO.queryById(new Integer(
          contractId));
      if (impawnContract != null) {
        impawnContract.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 更新td中的path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTd_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      Assurer assurer = assurerDAO.queryById(new Integer(contractId));
      if (assurer != null) {
        assurer.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 更具所选银行判断是否更新利率，更新月还本息。这里的数据只为了显示不做具体操作 2007.11.14 shiy
   */
  public EndorsecontractTaChangeLoanMonthRateAF queryLoanMonthRate(
      String bankId, String term, String tempLoanMonthRate, String loanMode,
      String loanMoney) {
    EndorsecontractTaChangeLoanMonthRateAF ectcAF = new EndorsecontractTaChangeLoanMonthRateAF();
    BigDecimal loanMonthRate = new BigDecimal(0.00);
    BigDecimal corpusInterest = new BigDecimal(0.00);
    try {
      // 查看银行是否采用新的利率
      List list = loanBankParaDAO.queryLoanBankPara_sy(new Integer(bankId));
      // 查看是贷款方式
      if (!list.isEmpty()) {
        if (new Integer(term).intValue() > 60) {
          String loantype = "1"; // 5年以上
          loanMonthRate = loanRateDAO.findMontRate_sy(bankId, loantype);
        } else {
          String loantype = "0"; // 1到5年
          loanMonthRate = loanRateDAO.findMontRate_sy(bankId, loantype);
        }
      } else {
        loanMonthRate = new BigDecimal(tempLoanMonthRate);
      }
      // 计算最新的月还本息
      if (loanMode.equals("2")) {
        corpusInterest = CorpusinterestBS.getCorpusInterest(new BigDecimal(
            loanMoney), loanMonthRate, term);
        ectcAF.setCorpusInterest(corpusInterest.toString());
      }
      ectcAF.setLoanMonthRate(loanMonthRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ectcAF;
  }

  public void updateContractSt(String contractId) throws Exception {
    // TODO Auto-generated method stub
    try {
      BorrowerAcc bacc = borrowerAccDAO.queryById(contractId);
      bacc.setContractSt(String.valueOf(BusiConst.PLCONTRACTSTATUS_APPL));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void updateScanTe_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      LoanContract loanContract = loanContractDAO.queryById(contractId);
      if (loanContract != null) {
        loanContract.setPhotoUrl2(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
