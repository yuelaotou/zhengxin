package org.xpup.hafmis.sysloan.loanapply.loanapply.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractNumMngDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanConditionsSetDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.SpecialBorrowerDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractNumCancel;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.common.loanconditionsset.LoanConditionsParamSetDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.AssistantBorrowerDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BankListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BuyHouserDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyNewDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyTeListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.SpecialBankListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.DeveleperNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTeNewAF;

public class LoanapplyBS implements ILoanapplyBS {

  private BorrowerDAO borrowerDAO = null;

  private DeveloperDAO developerDAO = null;

  private LoanRateDAO loanRateDAO = null;

  private AssistantBorrowerDAO assistantBorrowerDAO = null;

  private ContractNumMngDAO contractNumMngDAO = null;

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private SpecialBorrowerDAO specialBorrowerDAO = null;

  private EmpDAO empDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private DevelopProjectDAO developProjectDAO = null;

  private HousesDAO housesDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private ContractNumCancelDAO contractNumCancelDAO = null;

  private OrgDAO orgDAO = null;

  private SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setContractNumCancelDAO(ContractNumCancelDAO contractNumCancelDAO) {
    this.contractNumCancelDAO = contractNumCancelDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
    this.assistantBorrowerDAO = assistantBorrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setContractNumMngDAO(ContractNumMngDAO contractNumMngDAO) {
    this.contractNumMngDAO = contractNumMngDAO;
  }

  public void setSpecialBorrowerDAO(SpecialBorrowerDAO specialBorrowerDAO) {
    this.specialBorrowerDAO = specialBorrowerDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  /**
   * hanl 根据职工编号和单位编号求出职工的信息
   */
  public LoanapplyNewAF findEmpInfo(String empid, String orgid)
      throws BusinessException {
    LoanapplyNewAF loanaf = new LoanapplyNewAF();
    try {
      EmpInfo empInfo = empDAO.findEmpInfoByEmpid(empid, orgid);// 查询职工姓名和身份证
      String empname = empInfo.getName();
      String cardnum = empInfo.getCardNum();
      List lls = borrowerDAO.findEmpinborrowByEmpid(empname, cardnum);// 根据借款人姓名证件号查询合同号
      // 判断是否特殊申请且拨入个人账户
      SpecialBorrower s = specialBorrowerDAO.querySpeBorrowerByNameAndNum(empname, cardnum);
      if(s != null && s.getReserveaB().equals("1"))
        loanaf.setIsPsnalAcc("0");
      if (lls.isEmpty()) {
        LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empid, orgid);// 查询借款人姓名，性别，证件类型，号码，出生年月日，住宅电话，移动电话,单位编号，单位名称等
        loanaf.setEmpid(empid);
        loanaf.getBorrower().setBorrowerName(inf.getEmpname());
        loanaf.getBorrower().setSex(inf.getSex().toString());
        loanaf.getBorrower().setCardKind(inf.getCardking());
        loanaf.getBorrower().setCardNum(inf.getCardnum());
        loanaf.getBorrower().setBirthday(inf.getBirthday());
        loanaf.getBorrower().setHouseTel(inf.getTel());
        loanaf.getBorrower().setHomeMobile(inf.getMobiletle());
        loanaf.getBorrower().setOrgId(new BigDecimal(inf.getOrgid()));
        loanaf.getBorrower().setOrgName(inf.getOrgname());
        loanaf.getBorrower().setOrgTel(inf.getOrgtel());
        loanaf.getBorrower().setOrgMail(inf.getPostalcode());
        loanaf.getBorrower().setAccBlnce(new BigDecimal(inf.getCardmoney()));
        loanaf.getBorrower().setOrgAddr(inf.getAddress());
        loanaf.getBorrower().setMonthSalary(
            new BigDecimal(inf.getMonth_income()));
        loanaf.getBorrower().setPay_oldyear(inf.getPay_oldyear());
        loanaf.getBorrower().setMonthPay(new BigDecimal(inf.getMonthpay()));
        try {
          inf.setPay_status(BusiTools.getBusiValue(Integer.parseInt(inf
              .getPay_status()), BusiConst.OLDPAYMENTSTATE));
        } catch (NumberFormatException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }
        loanaf.getBorrower().setEmpSt_(inf.getPay_status());
        loanaf.getBorrower().setBgnDate(inf.getFirst_pay_month());
        loanaf.getBorrower().setEndDate(inf.getOrg_pay_month());
        loanaf.getBorrower().setOffice(inf.getOffices());

      } else {
        boolean b = false;
        for (int i = 0; i < lls.size(); i++) {

          String contrid = (String) lls.get(i);

          b = borrowerAccDAO.isCheckBorrowByContractid(contrid);// 判断该合同是否处于12.13(状态为还款中的合同走前提条件设置的判断)

          if (b) {
            LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empid, orgid);// 查询借款人姓名，性别，证件类型，号码，出生年月日，住宅电话，移动电话,单位编号，单位名称等
            loanaf.setEmpid(empid);
            loanaf.getBorrower().setBorrowerName(inf.getEmpname());
            loanaf.getBorrower().setSex(inf.getSex().toString());
            loanaf.getBorrower().setCardKind(inf.getCardking());
            loanaf.getBorrower().setCardNum(inf.getCardnum());
            loanaf.getBorrower().setBirthday(inf.getBirthday());
            loanaf.getBorrower().setHouseTel(inf.getTel());
            loanaf.getBorrower().setHomeMobile(inf.getMobiletle());
            loanaf.getBorrower().setOrgId(new BigDecimal(inf.getOrgid()));
            loanaf.getBorrower().setOrgName(inf.getOrgname());
            loanaf.getBorrower().setOrgTel(inf.getOrgtel());
            loanaf.getBorrower().setOrgMail(inf.getPostalcode());
            loanaf.getBorrower()
                .setAccBlnce(new BigDecimal(inf.getCardmoney()));
            loanaf.getBorrower().setOrgAddr(inf.getAddress());
            loanaf.getBorrower().setMonthSalary(
                new BigDecimal(inf.getMonth_income()));
            loanaf.getBorrower().setMonthPay(new BigDecimal(inf.getMonthpay()));
            loanaf.getBorrower().setPay_oldyear(inf.getPay_oldyear());
            try {
              inf.setPay_status(BusiTools.getBusiValue(Integer.parseInt(inf
                  .getPay_status()), BusiConst.OLDPAYMENTSTATE));
            } catch (NumberFormatException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            loanaf.getBorrower().setEmpSt_(inf.getPay_status());
            loanaf.getBorrower().setBgnDate(inf.getFirst_pay_month());
            loanaf.getBorrower().setEndDate(inf.getOrg_pay_month());
            loanaf.getBorrower().setOffice(inf.getOffices());
          } else {
            LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empid, orgid);
            String empName = inf.getEmpname();
            String cardNum = inf.getCardnum();
            String empEleven = empDAO.queryempLoanIsEleven(empName, cardNum);
            if (Integer.parseInt(empEleven) > 0) {
              throw new BusinessException("该职工存在合同状态为还款中的贷款");
            }
            throw new BusinessException("该合同正在办理中");
          }
        }
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return loanaf;
  }

  /**
   * hanl 显示借款人信息
   */
  public LoanapplyNewAF showLoanapplyInfoBycontractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException {
    LoanapplyNewAF loanaf = new LoanapplyNewAF();
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contractid);// 查询pl110
    try {
      if (borrower == null) {
        throw new BusinessException("该合同不存在");
      }
      if (borrower.getEmpSt() == null) {
        borrower.setEmpSt_("");
      } else {
        borrower.setEmpSt_(BusiTools.getBusiValue(Integer.parseInt(borrower
            .getEmpSt()), BusiConst.OLDPAYMENTSTATE));
      }
    } catch (BusinessException be) {
      throw be;
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (borrower.getPrivilegeBorrowerId() != null
        && borrower.getPrivilegeBorrowerId().intValue() != 0) {
      loanaf.setPrivilege_borrower_id(borrower.getPrivilegeBorrowerId()
          .toString());
      SpecialBorrower s = specialBorrowerDAO.queryByHeadID(Integer.valueOf(borrower.getPrivilegeBorrowerId().toString()));
      if("1".equals(s.getReserveaB())) {
        loanaf.setIsPsnalAcc("0");
        loanaf.setPayBank(s.getPerBank());
        loanaf.setPayBankAcc(s.getPerBankAcc());
      }
    } else {
      loanaf.setPrivilege_borrower_id("0");
    }
    BigDecimal empid = borrower.getEmpId();
    if (empid == null) {
      loanaf.setType1("2");
      boolean flag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// 判断是否录过额度信息
      if (flag) {
        loanaf.setType1("3");
      } else {
        loanaf.setType1("4");
      }
    } else {
      loanaf.setType1("1");
    }
    // 设置最大可支取金额
    // 帐户余额-月缴存额*12
    BigDecimal zdjzqje = new BigDecimal(0.00);
    if (borrower.getPay_oldyear() != null
        && !"".equals(borrower.getPay_oldyear())) {
      zdjzqje = borrower.getAccBlnce().subtract(
          new BigDecimal(borrower.getPay_oldyear())
              .multiply(new BigDecimal(12)));
    } else if (borrower.getAccBlnce() != null && borrower.getMonthPay() != null) {
      zdjzqje = borrower.getAccBlnce().subtract(
          borrower.getMonthPay().multiply(new BigDecimal(12)));
    }
    if (zdjzqje.doubleValue() < 0) {
      zdjzqje = new BigDecimal(0.00);
    }
    String orgRate = "";
    String empRate = "";
    // 缴存比例
    if (borrower.getOrgId() != null
        && !"".equals(borrower.getOrgId().toString())) {
      Org org = orgDAO.queryById(new Integer(borrower.getOrgId().toString()));
      if (org != null) {
        orgRate = org.getOrgRate().toString();
        empRate = org.getEmpRate().toString();
        loanaf.setOrgRate(orgRate);
        loanaf.setEmpRate(empRate);
      }
    }
    loanaf.setZdjzqje(zdjzqje);
    borrower.setOtherArrearage_(borrower.getOtherArrearage().equals("0") ? "否"
        : "是");
    loanaf.setSexc(borrower.getSex());
    loanaf.setCardkingc(borrower.getCardKind());
    loanaf.setBorrower(borrower);
    // 操作员,审核人,审批人
    if (borrower.getOperator() != null && !"".equals(borrower.getOperator())) {
      loanaf.setOperator(securityDAO.queryByUserid(borrower.getOperator()));
    }
    if (borrower.getChkPerson() != null && !"".equals(borrower.getChkPerson())) {
      loanaf.setChkPerson(securityDAO.queryByUserid(borrower.getChkPerson()));
    }
    if (borrower.getVipChkPerson() != null
        && !"".equals(borrower.getVipChkPerson())) {
      loanaf.setVipChkPerson(securityDAO.queryByUserid(borrower
          .getVipChkPerson()));
    }
    Houses houses = housesDAO.queryById(contractid);// 根据合同编号查询放子信息
    if (houses != null && houses.getHouseType() != null
        && !"".equals(houses.getHouseType())) {
      loanaf.setHouseType(houses.getHouseType());
    } else {
      loanaf.setHouseType(contractid.substring(6, 8));
    }

    return loanaf;
  }

  /**
   * hanl 添加借款人信息
   */
  public String saveBorrowerInfo(LoanapplyNewAF loanapplyaf, String contractid,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    String contractId = contractid;
    try {
      Borrower borrower = loanapplyaf.getBorrower();
      Borrower borrowernew = new Borrower();
      borrowernew.setSex(loanapplyaf.getSexc().trim());
      borrowernew.setCardKind(loanapplyaf.getCardkingc().trim());
      if (contractId == null) {
        List lls = borrowerDAO.findEmpinborrowByEmpid(borrower
            .getBorrowerName().trim(), borrower.getCardNum().trim());// 根据借款人姓名证件号查询合同号
        boolean flag;
        String temp_id = "";
        if (!lls.isEmpty()) {
          for (int i = 0; i < lls.size(); i++) {
            String contrid = (String) lls.get(i);
            // 根据借款人合同编号查询合同状态处于12.13的合同号
            flag = borrowerAccDAO.isCheckBorrowByContractid(contrid);
            if (!flag) {
              temp_id = "1";
              String empEleven = empDAO.queryempLoanIsEleven(borrower
                  .getBorrowerName().trim(), borrower.getCardNum().trim());
              if (Integer.parseInt(empEleven) > 0) {
                throw new BusinessException("该职工存在合同状态为还款中的贷款");
              }
              throw new BusinessException("该合同正在办理中");
            }
          }
        }
        if ("0".equals(borrower.getEmpId().toString())) {
          int count = empDAO.queryEmpCount_yg(borrower.getBorrowerName().trim(),
              borrower.getCardNum().trim());
          if (count != 0)
            throw new BusinessException("系统中存在该职工,请选择职工账号!");
        }
        if (lls.isEmpty() || temp_id.equals("")) {// 可以正常办理
          // 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态
          String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(

          borrower.getBorrowerName().trim(), borrower.getCardNum().trim());

          String bizYearall = securityInfo.getUserInfo().getPlbizDate();
          String bizYear = bizYearall.substring(0, 4);
          contractId = contractNumMngDAO.getContractId(borrower.getOffice()
              .trim(), bizYear, loanapplyaf.getHouseType(), securityInfo
              .getOfficeInnerCodeMap());// 生成合同编号
          if (specialid != null) {
            SpecialBorrower sp = specialBorrowerDAO
                .queryByHeadID(new Integer(specialid));
            sp.setStatus(String.valueOf(BusiConst.APPSTATUS_2));
            sp.setPerBank(loanapplyaf.getPayBank().trim());
            sp.setPerBankAcc(loanapplyaf.getPayBankAcc().trim());
            borrowernew.setPrivilegeBorrowerId(new BigDecimal(specialid));
          }
          borrowernew.setContractId(contractId);
          borrowernew.setOperator(securityInfo.getUserName());
          borrowernew.setOpTime(new Date());
          if (borrower.getEmpId().toString().trim().equals("0")) {
            BigDecimal empid = null;
            borrowernew.setEmpId(empid);
          } else {
            borrowernew.setEmpId(new BigDecimal(borrower.getEmpId().toString()
                .trim()));
          }
          borrowernew.setOffice(borrower.getOffice().trim());
          borrowernew.setBorrowerName(borrower.getBorrowerName().trim());
          borrowernew.setCardNum(borrower.getCardNum().trim());
          borrowernew.setBirthday(borrower.getBirthday().trim());
          borrowernew
              .setAge(new BigDecimal(borrower.getAge().toString().trim()));
          borrowernew.setBusiness(borrower.getBusiness().trim());
          borrowernew.setTitle(borrower.getTitle().trim());
          borrowernew.setNation(borrower.getNation().trim());
          borrowernew.setNativePlace(borrower.getNativePlace().trim());
          borrowernew.setMarriageSt(borrower.getMarriageSt());
          borrowernew.setDegree(borrower.getDegree());
          borrowernew.setHomeAddr(borrower.getHomeAddr().trim());
          borrowernew.setHomeMail(borrower.getHomeMail().trim());
          borrowernew.setHouseTel(borrower.getHouseTel().trim());
          borrowernew.setHomeMobile(borrower.getHomeMobile().trim());
          borrowernew.setRemark(borrower.getRemark().trim());
          if (!borrower.getOrgId().toString().trim().equals("0")) {
            borrowernew.setOrgId(new BigDecimal(borrower.getOrgId().toString()
                .trim()));
          }
          borrowernew.setOrgName(borrower.getOrgName().trim());
          borrowernew.setOrgTel(borrower.getOrgTel().trim());
          borrowernew.setOrgMail(borrower.getOrgMail().trim());
          borrowernew.setOrgAddr(borrower.getOrgAddr().trim());
          borrowernew.setAccBlnce(new BigDecimal(borrower.getAccBlnce()
              .toString().trim()));
          borrowernew.setMonthSalary(new BigDecimal(borrower.getMonthSalary()
              .toString().trim()));
          borrowernew.setMonthPay(new BigDecimal(borrower.getMonthPay()
              .toString().trim()));
          if (!borrower.getEmpSt_().trim().equals("")) {

            borrowernew.setEmpSt(BusiTools.getBusiKey(borrower.getEmpSt_()
                .trim(), BusiConst.OLDPAYMENTSTATE)
                + "");
          } else {
            borrowernew.setEmpSt("");
          }
          borrowernew.setBgnDate(borrower.getBgnDate().trim());
          borrowernew.setEndDate(borrower.getEndDate().trim());
          borrowernew.setContactAName(borrower.getContactAName().trim());
          borrowernew.setRelationA(borrower.getRelationA().trim());
          borrowernew.setContactAOrgName(borrower.getContactAOrgName().trim());
          borrowernew.setContactAOrgTel(borrower.getContactAOrgTel().trim());
          borrowernew
              .setContactAHouseTel(borrower.getContactAHouseTel().trim());
          borrowernew.setContactAMobile(borrower.getContactAMobile().trim());
          borrowernew.setContactBName(borrower.getContactBName().trim());
          borrowernew.setRelationB(borrower.getRelationB().trim());
          borrowernew.setContactBOrgName(borrower.getContactBOrgName().trim());
          borrowernew.setContactBOrgTel(borrower.getContactBOrgTel().trim());
          borrowernew
              .setContactBHouseTel(borrower.getContactBHouseTel().trim());
          borrowernew.setContactBMobile(borrower.getContactBMobile().trim());
          // 其他联系人C
          borrowernew.setContactCName(borrower.getContactCName().trim());
          borrowernew.setRelationC(borrower.getRelationC().trim());
          borrowernew.setContactCOrgName(borrower.getContactCOrgName().trim());
          borrowernew.setContactCOrgTel(borrower.getContactCOrgTel().trim());
          borrowernew
              .setContactCHouseTel(borrower.getContactCHouseTel().trim());
          borrowernew.setContactCMobile(borrower.getContactCMobile().trim());
          borrowernew.setLoanType(borrower.getLoanType());
          if (borrower.getFundCity() != null
              && !"".equals(borrower.getFundCity())) {
            borrowernew.setFundCity(borrower.getFundCity());
          }
          // 插入pl110
          borrowerDAO.insert(borrowernew);
          // 插入帐号表
          BorrowerAcc acc = new BorrowerAcc();
          acc.setContractId(contractId);
          acc.setContractSt(String.valueOf(BusiConst.PLCONTRACTSTATUS_APPL));
          BigDecimal curIntegral = null;
          BigDecimal preIntegral = null;
          acc.setCurIntegral(curIntegral);
          acc.setPreIntegral(preIntegral);
          acc.setIsContractWrite("0");
          acc.setManualAuto("0");
          acc.setMarkA("0");
          acc.setMarkB("0");
          borrowerAccDAO.insert(acc);
          // 插入购房信息表
          Houses houses = new Houses();
          houses.setContractId(contractId);
          housesDAO.insert(houses);

          PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
          plOperateLog
              .setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
          plOperateLog.setOpModel(String
              .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO));// 贷款申请_借款人信息
          plOperateLog.setOpButton(String
              .valueOf(BusiLogConst.BIZLOG_ACTION_ADD));

          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLog.setContractId(contractId);
          plOperateLogDAO.insert(plOperateLog);
        } else {
          throw new BusinessException("该合同正在办理中");
        }

      } else {
        // 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态
        String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(

        borrower.getBorrowerName().trim(), borrower.getCardNum().trim());

        if (specialid != null) {
          SpecialBorrower sp = specialBorrowerDAO
              .queryByHeadID(new Integer(specialid));
          System.out.println(sp);
          System.out.println(loanapplyaf.getPayBank()+"=============>");
          System.out.println(loanapplyaf.getPayBankAcc()+"=============>");
          sp.setPerBank(loanapplyaf.getPayBank().trim());
          sp.setPerBankAcc(loanapplyaf.getPayBankAcc().trim());
        }
        Borrower bor = borrowerDAO.queryById(contractid);
        bor.setSex(loanapplyaf.getSexc().trim());
        bor.setCardKind(loanapplyaf.getCardkingc().trim());
        bor.setBirthday(borrower.getBirthday().trim());
        bor.setAge(borrower.getAge());
        bor.setMonthSalary(borrower.getMonthSalary());
        bor.setMonthPay(borrower.getMonthPay());
        bor.setBusiness(borrower.getBusiness().trim());
        bor.setTitle(borrower.getTitle().trim());
        bor.setNation(borrower.getNation().trim());
        bor.setNativePlace(borrower.getNativePlace().trim());
        bor.setMarriageSt(borrower.getMarriageSt().trim());
        bor.setDegree(borrower.getDegree().trim());
        bor.setHomeAddr(borrower.getHomeAddr().trim());
        bor.setHomeMail(borrower.getHomeMail().trim());
        bor.setHomeMobile(borrower.getHomeMobile().trim());
        bor.setHouseTel(borrower.getHouseTel().trim());
        bor.setOrgName(borrower.getOrgName().trim());
        bor.setOrgTel(borrower.getOrgTel().trim());
        bor.setOrgMail(borrower.getOrgMail().trim());
        bor.setOrgAddr(borrower.getOrgAddr().trim());
        bor.setContactAName(borrower.getContactAName().trim());
        bor.setRelationA(borrower.getRelationA().trim());
        bor.setContactAOrgName(borrower.getContactAOrgName().trim());
        bor.setContactAOrgTel(borrower.getContactAOrgTel().trim());
        bor.setContactAHouseTel(borrower.getContactAHouseTel().trim());
        bor.setContactAMobile(borrower.getContactAMobile().trim());
        bor.setContactBName(borrower.getContactBName().trim());
        bor.setRelationB(borrower.getRelationB().trim());
        bor.setContactBOrgName(borrower.getContactBOrgName().trim());
        bor.setContactBOrgTel(borrower.getContactBOrgTel().trim());
        bor.setContactBHouseTel(borrower.getContactBHouseTel().trim());
        bor.setContactBMobile(borrower.getContactBMobile().trim());
        bor.setContactCName(borrower.getContactCName().trim());
        bor.setRelationC(borrower.getRelationC().trim());
        bor.setContactCOrgName(borrower.getContactCOrgName().trim());
        bor.setContactCOrgTel(borrower.getContactCOrgTel().trim());
        bor.setContactCHouseTel(borrower.getContactCHouseTel().trim());
        bor.setContactCMobile(borrower.getContactCMobile().trim());

        PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO));// 贷款申请_借款人信息
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(contractid));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException e) {
      throw e;
    } catch (Exception ee) {
      ee.printStackTrace();
    }
    return contractId;
  }

  /**
   * hanl 显示辅助借款人信息
   */
  public LoanapplyTbNewAF findAssistanBorrowerInfo(String contractid,
      SecurityInfo securityInfo, String auxiliaryid) throws BusinessException {
    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    try {
      List lists = assistantBorrowerDAO
      .findAssistanBorrowerListByContractid(contractid);// 求辅助人列表
      List list = assistantBorrowerDAO
          .findAssistanBorrowerListByContractid_yg(contractid);// 求辅助人列表
      boolean flag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// 是否录过额度
      if (flag) {
        loanapplytbnewAF.setTemp_types("11");// 录过额度 添加、确定、、职工编号、修改、删除 按钮禁用
      }
      Borrower borrower =borrowerDAO.queryById(contractid);
      loanapplytbnewAF.setLoanType(borrower.getLoanType());
      if (list.size() == 0) {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contractid);// 根据合同号，查询借款人姓名
        loanapplytbnewAF.setContractId(contractid);
        loanapplytbnewAF.setBorrowerName(borrowernname);
      } else {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contractid);// 根据合同号，查询借款人姓名
        String maxauxiliaryid = "";
        if (auxiliaryid == null) {
          maxauxiliaryid = assistantBorrowerDAO
              .findMaxAuxiliaryidByContractid_yg(contractid);// 根据合同编号查询最大的辅助借款人编号
        } else {
          maxauxiliaryid = auxiliaryid;
        }
        AssistantBorrower ab = new AssistantBorrower();
        ab = assistantBorrowerDAO.queryById(new Integer(maxauxiliaryid));// 求辅助借款人
        // 缴存比例
        String orgRate = "";
        String empRate = "";
        if (ab.getOrgId() != null && !"".equals(ab.getOrgId().toString())) {
          Org org = orgDAO.queryById(new Integer(ab.getOrgId().toString()));
          if (org != null) {
            orgRate = org.getOrgRate().toString();
            empRate = org.getEmpRate().toString();
            loanapplytbnewAF.setOrgRate(orgRate);
            loanapplytbnewAF.setEmpRate(empRate);
          }
        }
        // 对象
        loanapplytbnewAF.setContractId(ab.getContractId().trim());
        loanapplytbnewAF.setBorrowerName(borrowernname.trim());
        if (ab.getEmpId() == null || ab.getEmpId().toString().equals("0")) {
          loanapplytbnewAF.setEmpId("");
        } else {
          loanapplytbnewAF.setEmpId(BusiTools.convertSixNumber(ab.getEmpId()
              .toString()));
        }
        if(ab.getStatus().equals("1")){
          loanapplytbnewAF.setRelationStatus("作废(离婚)");
        }else{
          loanapplytbnewAF.setRelationStatus("正常");
        }
        loanapplytbnewAF.setName(ab.getName());
        loanapplytbnewAF.setRelation(ab.getRelation());
        loanapplytbnewAF.setSex(ab.getSex());
        loanapplytbnewAF.setCardKind(ab.getCardKind());
        loanapplytbnewAF.setCardNum(ab.getCardNum());
        loanapplytbnewAF.setBirthday(ab.getBirthday());
        if (ab.getAge() == null) {
          loanapplytbnewAF.setAge("");
        } else {
          loanapplytbnewAF.setAge(ab.getAge().toString());
        }

        loanapplytbnewAF.setBusiness(ab.getBusiness());
        loanapplytbnewAF.setTitle(ab.getTitle());
        loanapplytbnewAF.setNation(ab.getNation());
        loanapplytbnewAF.setNativePlace(ab.getNativePlace());
        loanapplytbnewAF.setMarriageSt(ab.getMarriageSt());
        loanapplytbnewAF.setDegree(ab.getDegree());
        loanapplytbnewAF.setHomeAddr(ab.getHomeAddr());
        loanapplytbnewAF.setHomeMail(ab.getHomeMail());
        loanapplytbnewAF.setHouseTel(ab.getHouseTel());
        loanapplytbnewAF.setHomeMobile(ab.getHomeMobile());
        loanapplytbnewAF.setEmpSt(ab.getEmpSt());
        if (ab.getOrgId() == null) {
          loanapplytbnewAF.setOrgId("");
        } else {
          loanapplytbnewAF.setOrgId(BusiTools.convertTenNumber(ab.getOrgId()
              .toString()));
        }
        loanapplytbnewAF.setOrgName(ab.getOrgName());
        loanapplytbnewAF.setOrgTel(ab.getOrgTel());
        loanapplytbnewAF.setOrgMail(ab.getOrgMail());
        loanapplytbnewAF.setOrgAddr(ab.getOrgAddr());
        if (ab.getAccBlnce() == null) {
          loanapplytbnewAF.setAccBlnce("");
        } else {
          loanapplytbnewAF.setAccBlnce(ab.getAccBlnce().toString());
        }
        if (ab.getMonthSalary() == null) {
          loanapplytbnewAF.setMonthSalary("");
        } else {
          loanapplytbnewAF.setMonthSalary(ab.getMonthSalary().toString());
        }
        if (ab.getMonthPay() == null) {
          loanapplytbnewAF.setMonthPay("");
        } else {
          loanapplytbnewAF.setMonthPay(ab.getMonthPay().toString());
        }
        // 设置最大可支取金额
        // 帐户余额-月缴存额*12
        String zdjzqje = "";
        if (ab.getMaxPickBalance() != null
            && !"".equals(ab.getMaxPickBalance())) {
          zdjzqje = ab.getMaxPickBalance().toString();
        } else if (!"".equals(loanapplytbnewAF.getAccBlnce())
            && !"".equals(loanapplytbnewAF.getMonthPay())) {
          zdjzqje = new BigDecimal(loanapplytbnewAF.getAccBlnce())
              .subtract(new BigDecimal(loanapplytbnewAF.getMonthPay())
                  .multiply(new BigDecimal(12)))
              + "";
        }
        if (!zdjzqje.equals("") && Float.parseFloat(zdjzqje) < 0) {
          zdjzqje = "0";
        }
        loanapplytbnewAF.setZdjzqje(zdjzqje);
        loanapplytbnewAF.setBgnDate(ab.getBgnDate());
        loanapplytbnewAF.setEndDate(ab.getEndDate());

        loanapplytbnewAF.setMaxauxiliaryid(maxauxiliaryid);
        if (ab.getEmpId() == null) {
          boolean flag1 = borrowerLoanInfoDAO
              .isFindContractidByContractid(contractid);
          if (flag1) {
            loanapplytbnewAF.setTemp_tes("3");
          } else {
            loanapplytbnewAF.setTemp_tes("2");
          }
        } else {
          loanapplytbnewAF.setTemp_tes("1");
        }
      }
      loanapplytbnewAF.setList(lists);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytbnewAF;
  }

  /**
   * hanl 检查合同编号是否满足要求
   */
  public void checkContractIdByContractId(String contractId)
      throws BusinessException {
    try {
      String contractid = borrowerAccDAO.findContractidByContractid(contractId);// 根据合同号，查询是否有处于1.5.6这三种状态的记录
      if (contractid == null) {
        throw new BusinessException("该合同编号不存在或状态不正确！");
      } else {
        boolean flag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractId);// 判断是否录过额度
        if (flag) {
          throw new BusinessException("该借款人已经录入额度信息，不能添加辅助借款人信息！");
        }
      }
    } catch (BusinessException be) {

      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 根据合同查询借款人，辅助借款人，合同信息，等等图片路径
   * 
   * @author yangg
   * @param contractid
   * @return List
   */
  public List queryPhotoURLByContractID(String contractId)
      throws BusinessException {
    List photoURLList = null;
    try {
      photoURLList = borrowerAccDAO.queryPhotoURLByContractID(contractId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return photoURLList;
  }

  /**
   * hanl 查询辅助借款人信息
   */
  public LoanapplyTbNewAF findAssistantBorrowerInfoByempId(String empId,
      String orgId, String contractaId) throws BusinessException {
    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    if (contractaId != null && !"".equals(contractaId)) {
      try {
        boolean flag = empDAO
            .getEmpidCount_AssistantBorrower(new Integer(empId));// 判断AA002表中是否存在与将要删除的职工empid相同的纪录
        // 如果存在相同的empid返回true，否则返回false
        if (flag) {
          EmpInfo empinfo = empDAO.findEmpInfoByEmpid(empId, orgId);// 根据职工编号查询职工姓名和身份证
          String empname = empinfo.getName();
          String empcardnum = empinfo.getCardNum();
          String auxiliaryid = assistantBorrowerDAO
              .findAuxiliaryidByNameCardnum(empname, empcardnum, contractaId);// 根据借款人姓名证件号查询辅助人的ID
          if (auxiliaryid == null) {
            LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empId, orgId);// 查询借款人姓名，性别，证件类型，号码，出生年月日，住宅电话，移动电话,单位编号，单位名称等
            loanapplytbnewAF.setEmpId(empId);
            loanapplytbnewAF.setName(inf.getEmpname());
            loanapplytbnewAF.setSex(inf.getSex().toString());
            loanapplytbnewAF.setCardKind(inf.getCardking());
            loanapplytbnewAF.setCardNum(inf.getCardnum());
            loanapplytbnewAF.setBirthday(inf.getBirthday());
            loanapplytbnewAF.setHouseTel(inf.getTel());
            loanapplytbnewAF.setHomeMobile(inf.getMobiletle());
            loanapplytbnewAF.setOrgId(inf.getOrgid());
            loanapplytbnewAF.setOrgName(inf.getOrgname());
            loanapplytbnewAF.setOrgTel(inf.getOrgtel());
            loanapplytbnewAF.setOrgMail(inf.getPostalcode());
            loanapplytbnewAF.setAccBlnce(inf.getCardmoney());
            loanapplytbnewAF.setOrgAddr(inf.getAddress());
            loanapplytbnewAF.setMonthSalary(inf.getMonth_income());
            loanapplytbnewAF.setMonthPay(inf.getMonthpay());
            loanapplytbnewAF.setRelation("");
            loanapplytbnewAF.setNation("");
            loanapplytbnewAF.setNativePlace("");
            loanapplytbnewAF.setBusiness("");
            loanapplytbnewAF.setTitle("");
            loanapplytbnewAF.setMarriageSt("");
            loanapplytbnewAF.setDegree("");
            loanapplytbnewAF.setHomeAddr("");
            loanapplytbnewAF.setHomeMail("");
            loanapplytbnewAF.setPay_oldyear(inf.getPay_oldyear());
            try {
              loanapplytbnewAF.setEmpSt(BusiTools.getBusiValue(Integer
                  .parseInt(inf.getPay_status()), BusiConst.OLDPAYMENTSTATE));
            } catch (NumberFormatException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

            loanapplytbnewAF.setBgnDate(inf.getFirst_pay_month());
            loanapplytbnewAF.setEndDate(inf.getOrg_pay_month());

          } else {
            AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(
                auxiliaryid));// 求辅助借款人 对象
            String contractid = ab.getContractId();
            boolean checkcontractid = borrowerAccDAO
                .isCheckBorrowByContractid(contractid);// 判断合同是否处于12.13
            if (checkcontractid) {

              if (ab.getEmpId() == null) {
                loanapplytbnewAF.setEmpId("");
              } else {
                loanapplytbnewAF.setEmpId(ab.getEmpId().toString());
              }
              if (ab.getName() == null) {
                loanapplytbnewAF.setName("");
              } else {
                loanapplytbnewAF.setName(ab.getName());
              }
              if (ab.getRelation() == null) {
                loanapplytbnewAF.setRelation("");
              } else {
                loanapplytbnewAF.setRelation(ab.getRelation());
              }
              if (ab.getSex() == null) {
                loanapplytbnewAF.setSex("");
              } else {
                loanapplytbnewAF.setSex(ab.getSex());
              }
              if (ab.getCardKind() == null) {
                loanapplytbnewAF.setCardKind("");
              } else {
                loanapplytbnewAF.setCardKind(ab.getCardKind());
              }
              if (ab.getCardNum() == null) {
                loanapplytbnewAF.setCardNum("");
              } else {
                loanapplytbnewAF.setCardNum(ab.getCardNum());
              }
              if (ab.getBirthday() == null) {
                loanapplytbnewAF.setBirthday("");
              } else {
                loanapplytbnewAF.setBirthday(ab.getBirthday());
              }

              if (ab.getAge() == null) {
                loanapplytbnewAF.setAge("");
              } else {
                loanapplytbnewAF.setAge(ab.getAge().toString());
              }
              if (ab.getBusiness() == null) {
                loanapplytbnewAF.setBusiness("");
              } else {
                loanapplytbnewAF.setBusiness(ab.getBusiness());
              }
              if (ab.getTitle() == null) {
                loanapplytbnewAF.setTitle("");
              } else {
                loanapplytbnewAF.setTitle(ab.getTitle());
              }
              if (ab.getNation() == null) {
                loanapplytbnewAF.setNation("");
              } else {
                loanapplytbnewAF.setNation(ab.getNation());
              }
              if (ab.getNativePlace() == null) {
                loanapplytbnewAF.setNativePlace("");
              } else {
                loanapplytbnewAF.setNativePlace(ab.getNativePlace());
              }
              if (ab.getMarriageSt() == null) {
                loanapplytbnewAF.setMarriageSt("");
              } else {
                loanapplytbnewAF.setMarriageSt(ab.getMarriageSt());
              }
              if (ab.getDegree() == null) {
                loanapplytbnewAF.setDegree("");
              } else {
                loanapplytbnewAF.setDegree(ab.getDegree());
              }
              if (ab.getHomeAddr() == null) {
                loanapplytbnewAF.setHomeAddr("");
              } else {
                loanapplytbnewAF.setHomeAddr(ab.getHomeAddr());
              }
              if (ab.getHomeMail() == null) {
                loanapplytbnewAF.setHomeMail("");
              } else {
                loanapplytbnewAF.setHomeMail(ab.getHomeMail());
              }
              if (ab.getHouseTel() == null) {
                loanapplytbnewAF.setHouseTel("");
              } else {
                loanapplytbnewAF.setHouseTel(ab.getHouseTel());
              }
              if (ab.getHomeMobile() == null) {
                loanapplytbnewAF.setHomeMobile("");
              } else {
                loanapplytbnewAF.setHomeMobile(ab.getHomeMobile());
              }

              if (ab.getOrgId() == null) {
                loanapplytbnewAF.setOrgId("");
              } else {
                loanapplytbnewAF.setOrgId(ab.getOrgId().toString());
              }
              if (ab.getOrgName() == null) {
                loanapplytbnewAF.setOrgName("");
              } else {
                loanapplytbnewAF.setOrgName(ab.getOrgName());
              }
              if (ab.getOrgTel() == null) {
                loanapplytbnewAF.setOrgTel("");
              } else {
                loanapplytbnewAF.setOrgTel(ab.getOrgTel());
              }
              if (ab.getOrgMail() == null) {
                loanapplytbnewAF.setOrgMail("");
              } else {
                loanapplytbnewAF.setOrgMail(ab.getOrgMail());
              }
              if (ab.getOrgAddr() == null) {
                loanapplytbnewAF.setOrgAddr("");
              } else {
                loanapplytbnewAF.setOrgAddr(ab.getOrgAddr());
              }

              if (ab.getAccBlnce() == null) {
                loanapplytbnewAF.setAccBlnce("");
              } else {
                loanapplytbnewAF.setAccBlnce(ab.getAccBlnce().toString());
              }
              if (ab.getMonthSalary() == null) {
                loanapplytbnewAF.setMonthSalary("");
              } else {
                loanapplytbnewAF.setMonthSalary(ab.getMonthSalary().toString());
              }
              if (ab.getMonthPay() == null) {
                loanapplytbnewAF.setMonthPay("");
              } else {
                loanapplytbnewAF.setMonthPay(ab.getMonthPay().toString());
              }
              // 设置最大可提取金额
              if (ab.getMaxPickBalance() != null) {
                loanapplytbnewAF.setZdjzqje(ab.getMaxPickBalance().toString());
              } else {
                loanapplytbnewAF.setZdjzqje("0");
              }
              if (ab.getEmpSt() != null) {
                try {
                  loanapplytbnewAF.setEmpSt(BusiTools.getBusiValue(Integer
                      .parseInt(ab.getEmpSt()), BusiConst.OLDPAYMENTSTATE));
                } catch (NumberFormatException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              } else {
                loanapplytbnewAF.setEmpSt("");
              }
              if (ab.getBgnDate() == null) {
                loanapplytbnewAF.setBgnDate("");
              } else {
                loanapplytbnewAF.setBgnDate(ab.getBgnDate());
              }
              if (ab.getEndDate() == null) {
                loanapplytbnewAF.setEndDate("");
              } else {
                loanapplytbnewAF.setEndDate(ab.getEndDate());
              }

            } else {
              String empEleven = empDAO.queryempLoanIsEleven(ab.getName(), ab
                  .getCardNum());
              if (Integer.parseInt(empEleven) > 0) {
                throw new BusinessException("该职工存在合同状态为还款中的贷款");
              }
              throw new BusinessException("该合同正在办理中");
            }
          }
        } else {
          throw new BusinessException("该职工不存在");
        }
      } catch (BusinessException be) {

        throw be;
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      throw new BusinessException("请输入合同编号");
    }
    return loanapplytbnewAF;
  }

  /**
   * hanl 添加辅助借款人
   */
  public void addAssistantBorrowerInfo(LoanapplyTbNewAF loanapplytbnewAF,
      SecurityInfo securityInfo, String temp_addtype) throws BusinessException {
    try {
      String contractid = loanapplytbnewAF.getContractId().trim();
      String name = loanapplytbnewAF.getName().trim();
      String cardnum = loanapplytbnewAF.getCardNum().trim();
      String auxiliaryid = assistantBorrowerDAO
          .findAssistanBorrowerByContractidNameCardnum(contractid, name,
              cardnum);// 根据合同编号，姓名，卡号，求出辅助借款人的编号
      if (auxiliaryid == null) {
        List list = assistantBorrowerDAO.findAuxiliaryidListByNameCardnum(name,
            cardnum);// 根据名字，卡号求出合同编号
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            String oldcontracntid = (String) list.get(i);
            boolean isflag = borrowerAccDAO
                .isCheckBorrowByContractid(oldcontracntid);// 判断该合同编号是否处于11.12.13
            if (!isflag) {
              throw new BusinessException("该合同正在办理中");
            }
          }
        }
        if (loanapplytbnewAF.getEmpId() == null || loanapplytbnewAF.getEmpId().equals("")) {
          int count = empDAO.queryEmpCount_yg(name, cardnum);
          if (count != 0)
            throw new BusinessException("系统中存在该职工,请选择职工账号!");
        }
        AssistantBorrower ab = new AssistantBorrower();// 创建辅助借款人
        ab.setContractId(loanapplytbnewAF.getContractId().trim());
        if (!loanapplytbnewAF.getEmpId().trim().equals("")) {
          ab.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId().trim()));
        } else {
          BigDecimal bb = null;
          ab.setEmpId(bb);
        }
        ab.setName(loanapplytbnewAF.getName().trim());
        ab.setRelation(loanapplytbnewAF.getRelation().trim());
        ab.setSex(loanapplytbnewAF.getSexhidden().trim());
        ab.setCardKind(loanapplytbnewAF.getCardKindhidden().trim());

        ab.setCardNum(loanapplytbnewAF.getCardNum().trim());
        ab.setBirthday(loanapplytbnewAF.getBirthday().trim());
        if (!loanapplytbnewAF.getAge().trim().equals("")) {
          ab.setAge(new BigDecimal(loanapplytbnewAF.getAge().trim()));
        }
        ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
        ab.setTitle(loanapplytbnewAF.getTitle().trim());
        ab.setNation(loanapplytbnewAF.getNation().trim());
        ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
        // ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
        ab.setDegree(loanapplytbnewAF.getDegree().trim());
        ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
        ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
        ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
        ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
        BigDecimal temp_orgs = null;
        if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
          ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
        } else {
          ab.setOrgId(temp_orgs);
        }
        ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
        ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
        ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
        ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
        // 最大可支取金额
        if (loanapplytbnewAF.getZdjzqje() != null
            && !"".equals(loanapplytbnewAF.getZdjzqje())) {
          ab.setMaxPickBalance(new BigDecimal(loanapplytbnewAF.getZdjzqje()));
        }
        if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
          ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce().trim()));
        } else {
          ab.setAccBlnce(temp_orgs);
        }
        if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
          ab.setMonthSalary(new BigDecimal(loanapplytbnewAF.getMonthSalary()
              .trim()));
        }
        if (!loanapplytbnewAF.getMonthPay().trim().equals("")) {
          ab.setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay().trim()));
        } else {
          ab.setMonthPay(temp_orgs);
        }
        ab.setEmpSt(loanapplytbnewAF.getEmpSt());
        ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
        ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
        ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
        ab.setOperator(securityInfo.getUserName());
        ab.setOpTime(new Date());
        ab.setReserveaC("0");//说明原配
        assistantBorrowerDAO.insert(ab);// 插入辅助借款人

        PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// 贷款申请_借款人信息
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId().toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        if (temp_addtype != null) {// 说明走过添加按钮
          auxiliaryid = assistantBorrowerDAO
              .findAssistanBorrowerByContractidNameCardnum(contractid, name,
                  cardnum);
          if (auxiliaryid == null) {// 可以添加
            List list = assistantBorrowerDAO.findAuxiliaryidListByNameCardnum(
                name, cardnum);// 根据名字，卡号求出合同编号
            if (list.size() != 0) {
              for (int i = 0; i < list.size(); i++) {
                String oldcontracntid = (String) list.get(i);
                boolean isflag = borrowerAccDAO
                    .isCheckBorrowByContractid(oldcontracntid);// 判断该合同编号是否处于11.12.13
                if (!isflag) {
                  throw new BusinessException("该合同正在办理中");
                }
              }
            }
            AssistantBorrower ab = new AssistantBorrower();// 创建辅助借款人
            ab.setContractId(loanapplytbnewAF.getContractId().trim());
            if (!loanapplytbnewAF.getEmpId().trim().equals("")) {
              ab.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId().trim()));
            } else {
              BigDecimal bb = null;
              ab.setEmpId(bb);
            }
            ab.setName(loanapplytbnewAF.getName().trim());
            ab.setRelation(loanapplytbnewAF.getRelation().trim());
            ab.setSex(loanapplytbnewAF.getSexhidden().trim());
            ab.setCardKind(loanapplytbnewAF.getCardKindhidden().trim());

            ab.setCardNum(loanapplytbnewAF.getCardNum().trim());
            ab.setBirthday(loanapplytbnewAF.getBirthday().trim());
            if (!loanapplytbnewAF.getAge().trim().equals("")) {
              ab.setAge(new BigDecimal(loanapplytbnewAF.getAge().trim()));
            }
            ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
            ab.setTitle(loanapplytbnewAF.getTitle().trim());
            ab.setNation(loanapplytbnewAF.getNation().trim());
            ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
            ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
            ab.setDegree(loanapplytbnewAF.getDegree().trim());
            ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
            ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
            ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
            ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
            BigDecimal temp_org = null;
            if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
              ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
            } else {
              ab.setOrgId(temp_org);
            }
            ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
            ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
            ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
            ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
            if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
              ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce()
                  .trim()));
            } else {
              ab.setAccBlnce(temp_org);
            }
            if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
              ab.setMonthSalary(new BigDecimal(loanapplytbnewAF
                  .getMonthSalary().trim()));
            }
            if (!loanapplytbnewAF.getMonthPay().trim().equals("")) {
              ab.setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay()
                  .trim()));
            } else {
              ab.setMonthPay(temp_org);
            }
            if (!loanapplytbnewAF.getEmpSt().trim().equals("")) {
              int empSt1 = 0;
              try {
                empSt1 = BusiTools.getBusiKey(loanapplytbnewAF.getEmpSt()
                    .trim(), BusiConst.OLDPAYMENTSTATE);
              } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
              ab.setEmpSt(new Integer(empSt1).toString());
            }

            ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
            ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
            ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
            ab.setOperator(securityInfo.getUserName());
            ab.setOpTime(new Date());
            ab.setReserveaC("0");//说明原配
            assistantBorrowerDAO.insert(ab);// 插入辅助借款人

            PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// 贷款申请_借款人信息
            plOperateLog.setOpButton(String
                .valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
            plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId()
                .toString()));
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpTime(new Date());
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setContractId(contractid);
            plOperateLogDAO.insert(plOperateLog);
          } else {
            throw new BusinessException("该职工已经为辅助借款人");
          }
        } else {// 更新辅助借款人信息
          AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(
              auxiliaryid));
          ab.setReserveaC("0");//说明原配
          ab.setRelation(loanapplytbnewAF.getRelation().trim());
          ab.setSex(loanapplytbnewAF.getSexhidden().trim());
          ab.setCardKind(loanapplytbnewAF.getCardKindhidden().trim());
          ab.setBirthday(loanapplytbnewAF.getBirthday().trim());
          if (loanapplytbnewAF.getAge() != null
              && !"".equals(loanapplytbnewAF.getAge())) {
            ab.setAge(new BigDecimal(loanapplytbnewAF.getAge().trim()));
          }
          ab.setNation(loanapplytbnewAF.getNation().trim());
          ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
          ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
          ab.setTitle(loanapplytbnewAF.getTitle().trim());
          // ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
          ab.setDegree(loanapplytbnewAF.getDegree().trim());
          ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
          ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
          ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
          ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
          ab.setMonthSalary(new BigDecimal(loanapplytbnewAF.getMonthSalary()
              .trim()));
          ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
          ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
          ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
          ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
          if (loanapplytbnewAF.getOrgId().trim().equals("")) {
            BigDecimal orgidn = null;
            ab.setOrgId(orgidn);
          } else {
            ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
          }
          if (loanapplytbnewAF.getAccBlnce().trim().equals("")) {
            BigDecimal orgidn = null;
            ab.setAccBlnce(orgidn);
          } else {
            ab
                .setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce()
                    .trim()));
          }
          if (loanapplytbnewAF.getMonthPay().trim().equals("")) {
            BigDecimal orgidn = null;
            ab.setMonthPay(orgidn);
          } else {
            ab
                .setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay()
                    .trim()));
          }
          if (loanapplytbnewAF.getEmpSt().trim().equals("")) {

            ab.setEmpSt("");
          } else {
            ab.setEmpSt(loanapplytbnewAF.getEmpSt().trim());
          }
          if (loanapplytbnewAF.getBgnDate().trim().equals("")) {

            ab.setBgnDate("");
          } else {
            ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
          }
          if (loanapplytbnewAF.getEndDate().trim().equals("")) {

            ab.setEndDate("");
          } else {
            ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
          }

          PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
          plOperateLog
              .setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
          plOperateLog
              .setOpModel(String
                  .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// 辅助借款人信息
          plOperateLog.setOpButton(String
              .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
          plOperateLog.setOpBizId(new BigDecimal(auxiliaryid));
          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLog.setContractId(contractid);
          plOperateLogDAO.insert(plOperateLog);
        }

      }
    } catch (BusinessException e) {
      // TODO Auto-generated catch block
      throw e;
    }
  }

  /**
   * hanl 删除辅助借款人
   */
  public void deleteAsistantBorrowerInfo(String idaf, SecurityInfo securityInfo)
      throws BusinessException {

    try {
      String contractid = assistantBorrowerDAO
          .findContractidByAuxiliaryid(idaf);// 根据主键查询合同编号
      if (contractid == null) {
        throw new BusinessException("该记录已经被删除！");
      } else {
        String contrid = borrowerAccDAO.findContractidByContractid(contractid);// 根据合同号，查询是否有处于1.5.6这三种状态的记录
        if (contrid == null) {
          throw new BusinessException("该合同状态下不能删除辅助借款人信息！");
        } else {
          boolean isflag = borrowerLoanInfoDAO
              .isFindContractidByContractid(contrid);// 判断是否录过额度
          if (!isflag) {
            assistantBorrowerDAO.deleteAsistantBorrowerInfoByAuxiliaryid(idaf);// 删除辅助借款人

            PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// 辅助借款人信息
            plOperateLog.setOpButton(String
                .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
            plOperateLog.setOpBizId(new BigDecimal(idaf));
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpTime(new Date());
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setContractId(contractid);
            plOperateLogDAO.insert(plOperateLog);
          } else {
            throw new BusinessException("不能删除辅助借款人信息！");
          }
        }
      }
    } catch (BusinessException e) {
      // TODO Auto-generated catch block
      throw e;
    }

  }

  /**
   * hanl 显示购房信息
   */
  public LoanapplyTcNewAF showBuyHouseInfoBycontractid(String contractid,
      String buyhouseorgid) throws BusinessException {
    LoanapplyTcNewAF lptcAF = new LoanapplyTcNewAF();
    try {
      SpecialBankListDTO specialBankListDTO = housesDAO
          .findBankPerson(contractid);
      Houses houses = housesDAO.queryById(contractid);// 根据合同编号查询放子信息
      lptcAF.setPhotoUrl(houses.getPhotoUrl());
      boolean isflag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// 判断是否录过额度
      String borrowerName = borrowerDAO
          .findBorrowerNameInfoByContractid(contractid);// 根据合同号，查询借款人姓名
      String headid = null;
      if (houses.getHouseType() != null) {
        headid = houses.getHeadId();
      }
      if (headid != null
          || (buyhouseorgid != null && !buyhouseorgid.equals(""))) {
        // 能进来的前提是有合同编号，如果输入了售房单位
        // 即表内主键，if就是成立的。if成立了说明有表内主键或pl115里有headid
        String pkid = "";
        if (buyhouseorgid != null && !buyhouseorgid.equals("")) {
          pkid = buyhouseorgid;
        } else {
          pkid = houses.getHeadId();
        }

        lptcAF.setContractId(contractid);
        lptcAF.setBorrowerName(borrowerName);
        BuyHouserDTO buyDTO = developerDAO.findHosesSomeInfo(pkid);
        lptcAF.setOrgName(buyDTO.getDeveloperName());
        lptcAF.setDeveloperTel(buyDTO.getDeveloperTel());
        List bankList = new ArrayList();
        if (specialBankListDTO.getBankName() != null
            && !"".equals(specialBankListDTO.getBankName())) {
          BankListDTO bdto = new BankListDTO();
          bdto.setBankKey(specialBankListDTO.getBankCard());
          bdto.setBankValue(specialBankListDTO.getBankName());
          bdto.setBankacc(specialBankListDTO.getBankCard());
          bankList.add(bdto);
        } else {
          bankList = developerDAO.findBankListById(pkid);// 查询银行
        }
        lptcAF.setBanklist(bankList);
        lptcAF.setFloorlist(developerDAO.findSomeFloorListById(pkid));
        
        lptcAF.setHouseType(contractid.substring(6, 8));
        lptcAF.setHouseTypehidden(contractid.substring(6, 8));
        lptcAF.setHouseTypetemp("");// 因为houses.getHouseType() == null
      } else if (houses.getHouseType() == null) {
        //  判断是否为特殊申请
        Borrower borrower = borrowerDAO.queryById(contractid);
        
        SpecialBorrower s = null;
        if(borrower.getPrivilegeBorrowerId() != null)
          s = specialBorrowerDAO.queryByHeadID(Integer.valueOf(borrower.getPrivilegeBorrowerId().toString()));
        lptcAF.setContractId(contractid);
        lptcAF.setBorrowerName(borrowerName);
        // 根据合同号的第7,8位取房屋类型
        lptcAF.setHouseType(contractid.substring(6, 8));
        lptcAF.setHouseTypehidden(contractid.substring(6, 8));
        if (s != null) {
          // 如果为特殊申请时购房信息按下面的方法取出
          if (contractid.substring(6, 8).equals("01")) {
            List bankList = new ArrayList();
            if (s.getPerBank() != null && !"".equals(s.getPerBank())) {
              lptcAF.setDeveloperPaybank(s.getPerBankAcc());
              lptcAF.setBanNamehidden(s.getPerBank());
              lptcAF.setDeveloperPaybankAAcc(s.getPerBankAcc());
              BankListDTO bdto = new BankListDTO();
              bdto.setBankKey(s.getPerBankAcc());
              bdto.setBankValue(s.getPerBank());
              bdto.setBankacc(s.getPerBankAcc());
              bankList.add(bdto);
            } else if (s.getHeadId() != null) {
              bankList = developerDAO.findBankListById(s.getHeadId());
            }
            lptcAF.setBanklist(bankList);
            lptcAF.setBuyhouseorgid(s.getHeadId());
            if (s.getFloorId() != null) {
              // 如果特殊申请录入了楼盘和楼盘号刚让购房信息页面灰显
              lptcAF.setSpeAppFlag("0");
              String floorName = developProjectDAO.queryById(
                  Integer.valueOf(s.getFloorId())).getFloorName();
              lptcAF.setFloorId(floorName);
              // 楼盘号下拉
              List floorNumList = this.findAllBuildNumList(s.getHeadId(),
                  floorName);
              lptcAF.setFloornumlist(floorNumList);
              lptcAF.setFloorNum(s.getFloorId());
            }
            if (s.getHeadId() != null) {
              lptcAF.setFloorlist(developerDAO.findSomeFloorListById(s
                  .getHeadId()));
              lptcAF.setOrgName(developerDAO.findHosesSomeInfo(s.getHeadId())
                  .getDeveloperName());
            }

          }
        }
        lptcAF.setHouseTypetemp("");// 因为houses.getHouseType() == null
        // 说明没有如果购房信息 HouseTypetemp是空
      } 
      if(houses.getHouseType() != null) {
        if (isflag) {
          String housestype = houses.getHouseType();
          if (housestype.equals("01")) {
            lptcAF.setTemp_type("5");
            lptcAF.setRemark1(houses.getRemark());
          } else {
            lptcAF.setTemp_type("6");
            lptcAF.setRemark2(houses.getRemark());
          }
        }
        lptcAF.setContractId(contractid);
        lptcAF.setBorrowerName(borrowerName);
        if (buyhouseorgid != null && !buyhouseorgid.equals("")) {
          BuyHouserDTO buyDTO = developerDAO.findHosesSomeInfo(buyhouseorgid);
          lptcAF.setDeveloperTel(buyDTO.getDeveloperTel());
        } else {
          lptcAF.setDeveloperTel(houses.getDeveloperTel());
        }
        
        if (houses.getHeadId() != null) {
          lptcAF.setBuyhouseorgid(houses.getHeadId());
        }
        if (houses.getHouseType().equals("01")) {
          
          lptcAF.setDeveloperPaybank(houses.getDeveloperPaybankAAcc());
          lptcAF.setBanNamehidden(houses.getDeveloperPaybank());
          lptcAF.setDeveloperPaybankAAcc(houses.getDeveloperPaybankAAcc());
          if (houses.getFloorId() != null) {
            if (developProjectDAO.queryById(Integer.valueOf(houses.getFloorId()
                .toString())) != null) {
              lptcAF.setFloorId(developProjectDAO.queryById(
                  Integer.valueOf(houses.getFloorId().toString()))
                  .getFloorName());
            }
          } else {
              lptcAF.setFloorId("");
          }
          lptcAF.setFloorNum(houses.getFloorId().toString());// 这个属性对应的是楼盘号下拉的key(floorId)
          lptcAF.setRoomNum(houses.getRoomNum());
          lptcAF.setFirstPay(houses.getFirstPay() + "");
          lptcAF.setHouseTotlePrice(houses.getHouseTotlePrice() + "");
          lptcAF.setHouseArea(houses.getHouseArea() + "");
          if (houses.getHouseTotlePrice() != null) {
            lptcAF.setHousePrice(houses.getHouseTotlePrice().divide(
                houses.getHouseArea(), 0, BigDecimal.ROUND_HALF_UP)
                + "");
          } else {
            lptcAF.setHousePrice("");
          }

          lptcAF.setBuyHouseContractId(houses.getBuyHouseContractId());
          if (houses.getFirstPay() != null) {
            lptcAF.setFirstTol(houses.getFirstPay().divide(
                houses.getHouseTotlePrice(), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1), 0,
                    BigDecimal.ROUND_HALF_UP)
                + "" + "%");
          } else {

            lptcAF.setFirstTol("");
          }

          lptcAF.setIsNowhouse(houses.getIsNowhouse());
          lptcAF.setOverDate(houses.getOverDate());
          lptcAF.setBuildRightNum(houses.getBuildRightNum());
          lptcAF.setAgreementDate(houses.getAgreementDate());
          lptcAF.setHouseAddr(houses.getHouseAddr());
          lptcAF.setHouseType(houses.getHouseType());
          if (houses.getHouseType().equals("01")) {
            lptcAF.setHouseTypehidden("01");
            lptcAF.setRemark1(houses.getRemark());
          } else {
            lptcAF.setHouseTypehidden("02");
            lptcAF.setRemark2(houses.getRemark());
          }
          // 楼盘号下拉
          List floorNumList = this.findAllBuildNumList(headid, lptcAF
              .getFloorId());
          lptcAF.setFloornumlist(floorNumList);
        } else {
          lptcAF.setBargainorName(houses.getBargainorName());
          lptcAF.setBargainorCardKind(houses.getBargainorCardKind());
          lptcAF.setBargainorCardNum(houses.getBargainorCardNum());
          lptcAF.setBargainorTel(houses.getBargainorTel());
          lptcAF.setBargainorHousepropertyCode(houses
              .getBargainorHousepropertyCode());
          lptcAF.setBargainorMobile(houses.getBargainorMobile());
          lptcAF.setBargainorHouseAddr(houses.getBargainorHouseAddr());
          lptcAF.setBargainorPaybank(houses.getBargainorPaybank());
          lptcAF.setBargainorPaybankAcc(houses.getBargainorPaybankAcc());
          lptcAF.setBargainorHouseArea(houses.getBargainorHouseArea());
          lptcAF.setBargainorTotlePrice(houses.getBargainorTotlePrice() + "");
          lptcAF.setBargainorHouseAge(houses.getBargainorHouseAge() + "");
          lptcAF.setBargainorAgreementDate(houses.getBargainorAgreementDate());
          lptcAF.setHouseType(houses.getHouseType());
          if (houses.getHouseType().equals("01")) {
            lptcAF.setHouseTypehidden("01");
            lptcAF.setRemark1(houses.getRemark());
          } else {
            lptcAF.setHouseTypehidden("02");
            lptcAF.setRemark2(houses.getRemark());
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return lptcAF;
  }

  /**
   * hanl 检查合同编号
   */
  public String checkTCContractIdByContractId(String contractId)
      throws BusinessException {
    String id = null;
    try {
      String contractid = borrowerAccDAO.findContractidByContractid(contractId);// 根据合同号，查询是否有处于1.5.6这三种状态的记录
      if (contractid == null) {
        throw new BusinessException("该合同编号不存在或状态不正确！");
      } else {
        String pkid = housesDAO.findPkidByContractid(contractId);// 判断该合同编号是否存在于pl114
        if (pkid == null) {
          boolean isflag = borrowerLoanInfoDAO
              .isFindContractidByContractid(contractId);// 判断是否录过额度
          if (isflag) {
            throw new BusinessException("该借款人已经录入额度信息，不能添加购房信息");
          } else {
            id = borrowerDAO.findBorrowerNameInfoByContractid(contractId);// 根据合同号，查询借款人姓名
          }
        } else {
          throw new BusinessException("该合同购房信息已经存在，不能添加购房信息");
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return id;
  }

  /**
   * hanl 添加购房信息
   */
  public void saveHouseInfo(LoanapplyTcNewAF loanapplytcnewAF,
      SecurityInfo securityInfo) throws BusinessException {
    String contractid = loanapplytcnewAF.getContractId();
    String conid = housesDAO.checkcontractid(contractid);// 判断合同编号是否存在
    if (conid == null) {
      throw new BusinessException("该合同编号不存在");
    } else {
      String pkid = housesDAO.findPkidByContractid(contractid);// 此处取出的pkid是pl114中的购房类型，
      // 如果空，就说明没有购房信息，就新增一条，否则为更新pl114
      if (pkid == null) {// 插入pl114
        housesDAO.deleteHousesInfo(contractid);// 因为在录入借款人时，向pl114里添加了合同编号，所以此处要插入购房信息记录是，先把该条记录删除
        Houses houses = new Houses();
        houses.setContractId(contractid);
        if (loanapplytcnewAF.getHouseTypehidden().equals("01")) {// 商品房
          houses.setHeadId(loanapplytcnewAF.getBuyhouseorgid());
          houses.setDeveloperTel(loanapplytcnewAF.getDeveloperTel());
          houses.setDeveloperPaybank(loanapplytcnewAF.getBanNamehidden());// 从隐藏于里取出的
          houses.setDeveloperPaybankAAcc(loanapplytcnewAF
              .getDeveloperPaybankAAcc());
          // 根据楼盘和开发商id 求主键

          String floornums = loanapplytcnewAF.getFloorNumber();
          String floorId = loanapplytcnewAF.getFloorNum();
          // 结束
          houses.setFloorId(new BigDecimal(floorId));
          houses.setFloorNum(floornums);
          houses.setRoomNum(loanapplytcnewAF.getRoomNum());
          // houses.setFirstPay(new BigDecimal(loanapplytcnewAF.getFirstPay()));
          houses.setHouseTotlePrice(new BigDecimal(loanapplytcnewAF
              .getHouseTotlePrice()));
          houses
              .setBuyHouseContractId(loanapplytcnewAF.getBuyHouseContractId());
          houses.setHouseArea(new BigDecimal(loanapplytcnewAF.getHouseArea()));
          houses.setIsNowhouse(loanapplytcnewAF.getIsNowhouse());
          houses.setOverDate(loanapplytcnewAF.getOverDate());
          houses.setBuildRightNum(loanapplytcnewAF.getBuildRightNum());
          houses.setAgreementDate(loanapplytcnewAF.getAgreementDate());
          houses.setHouseAddr(loanapplytcnewAF.getHouseAddr());
          houses.setRemark(loanapplytcnewAF.getRemark1());
          // 增加 拨款开户行，拨款开户账号
          houses.setFundBank(loanapplytcnewAF.getFundBank());
          houses.setFundBankAcc(loanapplytcnewAF.getFundBankAcc());
        } else {// 二手房
          houses.setBargainorName(loanapplytcnewAF.getBargainorName());
          houses.setBargainorCardKind(loanapplytcnewAF.getBargainorCardKind());
          houses.setBargainorCardNum(loanapplytcnewAF.getBargainorCardNum());
          houses.setBargainorTel(loanapplytcnewAF.getBargainorTel());
          houses.setBargainorHousepropertyCode(loanapplytcnewAF
              .getBargainorHousepropertyCode());
          houses.setBargainorMobile(loanapplytcnewAF.getBargainorMobile());
          houses
              .setBargainorHouseAddr(loanapplytcnewAF.getBargainorHouseAddr());
          houses.setBargainorPaybank(loanapplytcnewAF.getBargainorPaybank());
          houses.setBargainorPaybankAcc(loanapplytcnewAF
              .getBargainorPaybankAcc());
          houses
              .setBargainorHouseArea(loanapplytcnewAF.getBargainorHouseArea());
          houses.setBargainorTotlePrice(new BigDecimal(loanapplytcnewAF
              .getBargainorTotlePrice()));
          houses.setBargainorHouseAge(new BigDecimal(loanapplytcnewAF
              .getBargainorHouseAge()));
          houses.setBargainorAgreementDate(loanapplytcnewAF
              .getBargainorAgreementDate());
          houses.setRemark(loanapplytcnewAF.getRemark2());
        }
        houses.setOperator(securityInfo.getUserName());
        houses.setOpTime(new Date());
        houses.setHouseType(loanapplytcnewAF.getHouseTypehidden());
        housesDAO.insert(houses);

      } else {// 更新pl114
        Houses houses = housesDAO.queryById(contractid);
        if (loanapplytcnewAF.getHouseTypehidden().equals("01")) {// 商品房
          houses.setHeadId(loanapplytcnewAF.getBuyhouseorgid());
          houses.setDeveloperTel(loanapplytcnewAF.getDeveloperTel());
          houses.setDeveloperPaybank(loanapplytcnewAF.getBanNamehidden());
          houses.setDeveloperPaybankAAcc(loanapplytcnewAF
              .getDeveloperPaybankAAcc());
          // 根据楼盘和开发商id 求主键
          String floornums = loanapplytcnewAF.getFloorNumber();
          String floorId = loanapplytcnewAF.getFloorNum();
          // 结束
          houses.setFloorId(new BigDecimal(floorId));
          houses.setFloorNum(floornums);
          houses.setRoomNum(loanapplytcnewAF.getRoomNum());
          // houses.setFirstPay(new BigDecimal(loanapplytcnewAF.getFirstPay()));
          houses.setHouseTotlePrice(new BigDecimal(loanapplytcnewAF
              .getHouseTotlePrice()));
          houses
              .setBuyHouseContractId(loanapplytcnewAF.getBuyHouseContractId());
          houses.setHouseArea(new BigDecimal(loanapplytcnewAF.getHouseArea()));
          houses.setIsNowhouse(loanapplytcnewAF.getIsNowhouse());
          houses.setOverDate(loanapplytcnewAF.getOverDate());
          houses.setBuildRightNum(loanapplytcnewAF.getBuildRightNum());
          houses.setAgreementDate(loanapplytcnewAF.getAgreementDate());
          houses.setHouseAddr(loanapplytcnewAF.getHouseAddr());
          houses.setRemark(loanapplytcnewAF.getRemark1());
          // 增加 拨款开户行，拨款开户账号
          houses.setFundBank(loanapplytcnewAF.getFundBank());
          houses.setFundBankAcc(loanapplytcnewAF.getFundBankAcc());
        } else {// 二手房
          houses.setBargainorName(loanapplytcnewAF.getBargainorName());
          houses.setBargainorCardKind(loanapplytcnewAF.getBargainorCardKind());
          houses.setBargainorCardNum(loanapplytcnewAF.getBargainorCardNum());
          houses.setBargainorTel(loanapplytcnewAF.getBargainorTel());
          houses.setBargainorHousepropertyCode(loanapplytcnewAF
              .getBargainorHousepropertyCode());
          houses.setBargainorMobile(loanapplytcnewAF.getBargainorMobile());
          houses
              .setBargainorHouseAddr(loanapplytcnewAF.getBargainorHouseAddr());
          houses.setBargainorPaybank(loanapplytcnewAF.getBargainorPaybank());
          houses.setBargainorPaybankAcc(loanapplytcnewAF
              .getBargainorPaybankAcc());
          houses
              .setBargainorHouseArea(loanapplytcnewAF.getBargainorHouseArea());
          houses.setBargainorTotlePrice(new BigDecimal(loanapplytcnewAF
              .getBargainorTotlePrice()));
          houses.setBargainorHouseAge(new BigDecimal(loanapplytcnewAF
              .getBargainorHouseAge()));
          houses.setBargainorAgreementDate(loanapplytcnewAF
              .getBargainorAgreementDate());
          houses.setRemark(loanapplytcnewAF.getRemark2());
        }
      }
      PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_HOUSEINFO));// 购房信息
      if (pkid == null) {// 插入
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      } else {// 更新
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      }
      plOperateLog.setOpBizId(new BigDecimal(contractid));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setContractId(contractid);
      plOperateLogDAO.insert(plOperateLog);
    }

  }

  /**
   * hanl 显示额度信息
   */
  public LoanapplyTdNewAF showBorrowerLoanInfoByContractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException {

    LoanapplyTdNewAF tdAF = new LoanapplyTdNewAF();

    try {
      String housetype = housesDAO.findPkidByContractid(contractid);// 如果有值就说明借款人信息和购房信息已经录入完毕。也只有这样才能进入
      if (housetype != null) {
        EndorsecontractTaDTO ectaAF = borrowerDAO
            .queryBorrowerInfoHl(contractid);// 查询PL110字段，用于借款合同信息
        tdAF.setContractId(contractid);
        tdAF.setBorrowerName(ectaAF.getDebitter());
        // 显示房屋总价
        Houses houses = housesDAO.queryById(contractid);
        if (houses.getHouseType().equals("01")) {
          BigDecimal houseTotlePrice = new BigDecimal(0.00);
          houseTotlePrice = houses.getHouseTotlePrice();
          if (houseTotlePrice != null) {
            tdAF.setHouseTotlePrice(houseTotlePrice.toString());
          }
        } else {
          tdAF.setHouseTotlePrice(houses.getBargainorTotlePrice().toString());
        }
        if (ectaAF.getCertificateType() != null
            && !"".equals(ectaAF.getCertificateType())) {
          tdAF.setCardKind(BusiTools.getBusiValue(Integer.parseInt(ectaAF
              .getCertificateType()), BusiConst.DOCUMENTSSTATE));
        }

        tdAF.setCardNum(ectaAF.getCertificateNum());
        tdAF.setOffice(ectaAF.getOffice());
        String privilegeBorrowerId = borrowerDAO
            .findPrivilegeBorrowerIdByContractid(contractid);// 是否是特殊借款
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractid);// 判断是否录过额度
        if (isflag) {// 如果已经有额度了，就把额度显示出来，否则只显示借款人信息
          if (privilegeBorrowerId != null) {
            tdAF.setSpid("1");
          }
          BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO
              .queryById(contractid);// 查询额度信息
          tdAF.setLoanMoney(borrowerloan.getLoanMoney() + "");// 贷款金额
          tdAF.setLoantimeLimit(Integer.parseInt(borrowerloan
              .getLoanTimeLimit())
              + "");// 贷款金额期限
          tdAF.setLoanMood(borrowerloan.getLoanMode());// 还贷方式
          tdAF.setLoanMonthRate(borrowerloan.getLoanMonthRate() + "");// 每月利息
          tdAF.setLoanMonthRatess(borrowerloan.getLoanMonthRate().multiply(
              new BigDecimal(100))
              + "%");// 每月利息
          if (borrowerloan.getLoanPoundage() == null) {
            tdAF.setLoanPoundage("");
          } else {
            tdAF.setLoanPoundage(borrowerloan.getLoanPoundage() + ""); // 手续费
          }
          if (borrowerloan.getFirstLoanMoney() == null) {
            tdAF.setFirstLoanMoney("");
          } else {
            tdAF.setFirstLoanMoney(borrowerloan.getFirstLoanMoney() + "");// 首次还
          }
          if (borrowerloan.getCorpusInterest() == null) {
            tdAF.setCorpusInterest("");
          } else {
            tdAF.setCorpusInterest(borrowerloan.getCorpusInterest() + "");// 月本息
            tdAF.setEntireYearMoney(borrowerloan.getCorpusInterest() + "");// 月本息
          }
          tdAF.setPhotoUrl(borrowerloan.getPhotoUrl());
          // 首付款占房屋总价百分比
          if (borrowerloan.getLoanMoney() != null) {
            tdAF
                .setFirstTol((houses.getHouseTotlePrice() == null ? houses
                    .getBargainorTotlePrice() : houses.getHouseTotlePrice())
                    .subtract(borrowerloan.getLoanMoney())
                    .divide(
                        (houses.getHouseTotlePrice() == null ? houses
                            .getBargainorTotlePrice() : houses
                            .getHouseTotlePrice()), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1),
                        0, BigDecimal.ROUND_HALF_UP)
                    + "%");
          } else {
            tdAF.setFirstTol("");
          }

        } else {// 如果没有额度，在判断一下是否是特殊借款人，是就填写贷款金额，期限，月利率，月还本息,并且放个变量，用来说明是特殊借款人的，页面变成只读

          if (privilegeBorrowerId != null) {

            SpecialBorrower sb = specialBorrowerDAO
                .findLoanMoneyAndLimitByPrivilegeBorrowerId(privilegeBorrowerId);// 求出贷款金额，贷款期限
            tdAF.setLoanMoney(sb.getLoanMoney().toString());
            tdAF.setLoantimeLimit(Integer.parseInt(sb.getLoanTimeLimit()) + "");
            String monthRate = this.findMonthRate(ectaAF.getOffice(), sb
                .getLoanTimeLimit(), "");
            tdAF.setLoanMonthRate(monthRate);
            BigDecimal bmonthrate = new BigDecimal(monthRate);
            tdAF.setLoanMonthRatess(bmonthrate.multiply(new BigDecimal(100))
                + "%");
            tdAF.setCorpusInterest(CorpusinterestBS.getCorpusInterest(
                sb.getLoanMoney(), bmonthrate, sb.getLoanTimeLimit())
                .toString());
            tdAF.setSpid("1");
          }
        }

      } else {
        throw new BusinessException("您尚未录入购房信息");
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tdAF;
  }

  public LoanapplyTdNewAF showBorrowerLoanInfoByContractid1(String contractid,
      SecurityInfo securityInfo) throws BusinessException {
    LoanapplyTdNewAF tdAF = new LoanapplyTdNewAF();
    try {
      String housetype = housesDAO.findPkidByContractid(contractid);// 如果有值就说明借款人信息和购房信息已经录入完毕。也只有这样才能进入
      if (housetype != null) {
        EndorsecontractTaDTO ectaAF = borrowerDAO
            .queryBorrowerInfoHl(contractid);// 查询PL110字段，用于借款合同信息
        tdAF.setContractId(contractid);
        tdAF.setBorrowerName(ectaAF.getDebitter());
        // 显示房屋总价
        Houses houses = housesDAO.queryById(contractid);
        if (houses.getHouseType().equals("01")) {
          BigDecimal houseTotlePrice = new BigDecimal(0.00);
          houseTotlePrice = houses.getHouseTotlePrice();
          if (houseTotlePrice != null) {
            tdAF.setHouseTotlePrice(houseTotlePrice.toString());
          }
        } else {
          tdAF.setHouseTotlePrice(houses.getBargainorTotlePrice().toString());
        }
        if (ectaAF.getCertificateType() != null
            && !"".equals(ectaAF.getCertificateType())) {
          tdAF.setCardKind(BusiTools.getBusiValue(Integer.parseInt(ectaAF
              .getCertificateType()), BusiConst.DOCUMENTSSTATE));
        }

        tdAF.setCardNum(ectaAF.getCertificateNum());
        tdAF.setOffice(ectaAF.getOffice());
        String privilegeBorrowerId = borrowerDAO
            .findPrivilegeBorrowerIdByContractid(contractid);// 是否是特殊借款
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractid);// 判断是否录过额度
        if (isflag) {// 如果已经有额度了，就把额度显示出来，否则只显示借款人信息
          if (privilegeBorrowerId != null) {
            tdAF.setSpid("1");
          }
          BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO
              .queryById(contractid);// 查询额度信息
          tdAF.setLoanMoney(borrowerloan.getLoanMoney() + "");// 贷款金额

          tdAF.setLoantimeLimit(Integer.parseInt(borrowerloan
              .getLoanTimeLimit())
              / 12 + "");// 贷款金额期限
          tdAF.setLoanMood(borrowerloan.getLoanMode());// 还贷方式
          tdAF.setLoanMonthRate(borrowerloan.getLoanMonthRate() + "");// 每月利息
          tdAF.setLoanMonthRatess(borrowerloan.getLoanMonthRate().multiply(
              new BigDecimal(100))
              + "%");// 每月利息
          if (borrowerloan.getLoanPoundage() == null) {
            tdAF.setLoanPoundage("");
          } else {
            tdAF.setLoanPoundage(borrowerloan.getLoanPoundage() + ""); // 手续费
          }
          if (borrowerloan.getFirstLoanMoney() == null) {
            tdAF.setFirstLoanMoney("");
          } else {
            tdAF.setFirstLoanMoney(borrowerloan.getFirstLoanMoney() + "");// 首次还
          }
          if (borrowerloan.getCorpusInterest() == null) {
            tdAF.setCorpusInterest("");
          } else {
            tdAF.setCorpusInterest(borrowerloan.getCorpusInterest() + "");// 月本息
            tdAF.setEntireYearMoney(borrowerloan.getCorpusInterest() + "");// 月本息
          }
          tdAF.setPhotoUrl(borrowerloan.getPhotoUrl());
          // 首付款占房屋总价百分比
          if (borrowerloan.getLoanMoney() != null) {
            tdAF
                .setFirstTol((houses.getHouseTotlePrice() == null ? houses
                    .getBargainorTotlePrice() : houses.getHouseTotlePrice())
                    .subtract(borrowerloan.getLoanMoney())
                    .divide(
                        (houses.getHouseTotlePrice() == null ? houses
                            .getBargainorTotlePrice() : houses
                            .getHouseTotlePrice()), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1),
                        0, BigDecimal.ROUND_HALF_UP)
                    + "%");
          } else {
            tdAF.setFirstTol("");
          }
        } else {// 如果没有额度，在判断一下是否是特殊借款人，是就填写贷款金额，期限，月利率，月还本息,并且放个变量，用来说明是特殊借款人的，页面变成只读

          if (privilegeBorrowerId != null) {

            SpecialBorrower sb = specialBorrowerDAO
                .findLoanMoneyAndLimitByPrivilegeBorrowerId(privilegeBorrowerId);// 求出贷款金额，贷款期限
            tdAF.setLoanMoney(sb.getLoanMoney().toString());
            tdAF.setLoantimeLimit(Integer.parseInt(sb.getLoanTimeLimit()) / 12
                + "");
            String monthRate = this.findMonthRate(ectaAF.getOffice(), sb
                .getLoanTimeLimit(), "");
            tdAF.setLoanMonthRate(monthRate);
            BigDecimal bmonthrate = new BigDecimal(monthRate);
            tdAF.setLoanMonthRatess(bmonthrate.multiply(new BigDecimal(100))
                + "%");
            tdAF.setCorpusInterest(CorpusinterestBS.getCorpusInterest(
                sb.getLoanMoney(), bmonthrate, sb.getLoanTimeLimit())
                .toString());
            tdAF.setSpid("1");
          }
        }

      } else {
        throw new BusinessException("您尚未录入购房信息");
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tdAF;
  }

  /**
   * hanl 求出月利率
   */
  public String findMonthRate(String office, String loantimeLimit,
      String loanMood) throws BusinessException {
    String monthRate = "";
    try {
      String loantype = "0";
      int i = Integer.parseInt(loantimeLimit);
      if (!loanMood.equals("")) {
        if (loanMood.equals("4")) {
          loantype = "2";
        } else if (loanMood.equals("5")) {
          loantype = "3";
        } else {
          if (i > 60) {
            loantype = "1";
          }
        }
      } else {
        if (i > 60) {
          loantype = "1";
        }
      }
      BigDecimal rate = loanRateDAO.findMontRate(office, loantype);// 求利率
      if (rate == null) {
        monthRate = "";
      } else {
        monthRate = rate.toString();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthRate;
  }

  /**
   * hanl 检查合同编号
   */
  public void checkTdContractIdByContractId(String contractId)
      throws BusinessException {
    try {
      String id = borrowerAccDAO.findContractidByContractid(contractId);// 根据合同号，查询是否有处于1.5.6这三种状态的记录
      if (id == null) {
        throw new BusinessException("该合同编号不存在或该合同正在办理中！");
      } else {
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractId);// 判断是否录过的
        if (isflag) {
          throw new BusinessException("该合同额度信息已经存在，不能添加额度信息");
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * hanl 添加额度信息
   */
  public void saveBorrowerLoanInfo(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {
    try {
      String contractid = loanapplytdnewAF.getContractId();
      String privilegeBorrowerId = borrowerDAO
          .findPrivilegeBorrowerIdByContractid(contractid);// 是否是特殊借款
      // 页面输入的金额和期限
      int loanMonth = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
      float loanMoney = Float.parseFloat(loanapplytdnewAF.getLoanMoney());
      // 所能贷款的最大金额和期限
      int loanMonthLimit = loanapplytdnewAF.getLoanMonthLimit();
      BigDecimal loanMoneyLimit = loanapplytdnewAF.getLoanMoneyLimit();
      if (privilegeBorrowerId == null) {
        if (loanMonth > loanMonthLimit) {
          throw new BusinessException("最大贷款年限不得超过" + loanMonthLimit + "个月");
        }
        if (loanMoney > loanMoneyLimit.floatValue()) {
          throw new BusinessException("最大贷款金额不得超过" + loanMoneyLimit + "元");
        }
      }
      boolean isflag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// 判断是否录过额度
      if (isflag) {// 更新pl115
        BorrowerLoanInfo bl = borrowerLoanInfoDAO.queryById(contractid);
        bl.setLoanMoney(new BigDecimal(loanapplytdnewAF.getLoanMoney()));
        bl.setLoanTimeLimit(loanapplytdnewAF.getLoantimeLimit());
        if (loanapplytdnewAF.getLoanMood().equals("4")) {
          bl.setLoanRateType("2");
        }
        if (loanapplytdnewAF.getLoanMood().equals("5")) {
          bl.setLoanRateType("3");
        }
        bl.setLoanMode(loanapplytdnewAF.getLoanMood());
        bl
            .setLoanMonthRate(new BigDecimal(loanapplytdnewAF
                .getLoanMonthRate()));
        bl.setLoanPoundage(new BigDecimal(loanapplytdnewAF.getLoanPoundage()));
        if (loanapplytdnewAF.getCorpusInterest().equals("")) {
          bl.setCorpusInterest(new BigDecimal(0.00));
        } else {
          // 如果还款方式为整年期贷款
          if (loanapplytdnewAF.getLoanMood().equals("4")
              || loanapplytdnewAF.getLoanMood().equals("5")) {
            bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
                .getEntireYearMoney()));
          } else {
            bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
                .getCorpusInterest()));
          }
        }
        if (!loanapplytdnewAF.getLoanMood().equals("4")
            && !loanapplytdnewAF.getLoanMood().equals("5")) {
          int i = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
          String type = "0";
          if (i > 60) {
            type = "1";
          }
          bl.setLoanRateType(type);
        }
      } else {// 插入pl115
        BorrowerLoanInfo bl = new BorrowerLoanInfo();
        bl.setContractId(contractid);
        bl.setLoanMoney(new BigDecimal(loanapplytdnewAF.getLoanMoney()));
        bl.setLoanTimeLimit(loanapplytdnewAF.getLoantimeLimit());
        if (loanapplytdnewAF.getLoanMood().equals("4")) {
          bl.setLoanRateType("2");
        }
        if (loanapplytdnewAF.getLoanMood().equals("5")) {
          bl.setLoanRateType("3");
        }
        bl.setLoanMode(loanapplytdnewAF.getLoanMood());
        bl.setLoanMonthRate(new BigDecimal(loanapplytdnewAF
                .getLoanMonthRate()));
        bl.setLoanPoundage(new BigDecimal(loanapplytdnewAF.getLoanPoundage()));
        // 如果还款方式为整年期贷款
        if (loanapplytdnewAF.getLoanMood().equals("4")
            || loanapplytdnewAF.getLoanMood().equals("5")) {
          bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
              .getEntireYearMoney()));
        } else {
          bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
              .getCorpusInterest()));
        }
        if (!loanapplytdnewAF.getLoanMood().equals("4")
            && !loanapplytdnewAF.getLoanMood().equals("5")) {
          int i = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
          String type = "0";
          if (i > 60) {
            type = "1";
          }
          bl.setLoanRateType(type);
        }
        bl.setOperator(securityInfo.getUserName());
        bl.setOpTime(new Date());
        borrowerLoanInfoDAO.insert(bl);
      }
      PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFOLIMITED));// 购房信息
      if (!isflag) {// 插入
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      } else {// 更新
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      }
      plOperateLog.setOpBizId(new BigDecimal(contractid));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setContractId(contractid);
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * hanl 显示维护列表
   */
  public LoanapplyTeNewAF showBorrowerList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    LoanapplyTeNewAF loanapplytenewAF = new LoanapplyTeNewAF();

    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String empId = (String) pagination.getQueryCriterions().get("empId");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String buyHouseType = (String) pagination.getQueryCriterions().get(
          "buyHouseType");
      String contranct_st = (String) pagination.getQueryCriterions().get(
          "contranct_st");

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List newlist = new ArrayList();
      List list = new ArrayList();
      list = borrowerAccDAO.findBorrowerAccList(contractId, borrowerName,
          empId, cardNum, buyHouseType, contranct_st, orderBy, orderother,
          start, pageSize, page, securityInfo);
      // 求出分页列表信息的
      if (list != null) {
        Object obj[] = null;
        Iterator it = list.iterator();
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          LoanapplyTeListDTO listDto = new LoanapplyTeListDTO();
          listDto.setId(obj[0].toString());
          listDto.setContractid(obj[0].toString());// obj[0]是合同编号，在根据合同编号，从pl115里求出额度信息里的值贷款金额，贷款期限
          LoanapplyTeListDTO moneylimitDTO = new LoanapplyTeListDTO();// 这里只求出贷款金额，贷款期限
          moneylimitDTO = borrowerAccDAO.findLoanmoneyLimitInfo(obj[0]
              .toString());
          if (obj[1] != null) {
            String str = "";
            for (int i = 6; i > obj[1].toString().length(); i--) {
              str += "0";
            }
            listDto.setEmpid(str + obj[1].toString());
          } else {
            listDto.setEmpid("");
          }
          listDto.setBorrowername(obj[2].toString());
          listDto.setCardnum(obj[3].toString());
          if (obj[4] != null) {
            listDto.setBuyhousetype(BusiTools.getBusiValue_WL(
                obj[4].toString(), BusiConst.PLHOUSETYPE));
          } else {
            listDto.setBuyhousetype("");
          }
          listDto.setLoanmoney(moneylimitDTO.getLoanmoney());
          listDto.setLoanlimit(moneylimitDTO.getLoanlimit());
          if (obj[7] != null) {
            listDto.setTemp_c_st(obj[7].toString());
            String temp_st = BusiTools.getBusiValue(Integer.parseInt(obj[7]
                .toString()), BusiConst.PLCONTRACTSTATUS);
            listDto.setContract_st(temp_st);
            if (temp_st.equals("审核未通过") || temp_st.equals("审批未通过")) {
              listDto.setTemp_contract_st("1");
            } else {
              listDto.setTemp_contract_st("2");
            }
          } else {
            listDto.setContract_st("");
          }
          if (obj[8] != null) {
            if (obj[8].toString().equals("0")) {
              listDto.setIsPrintApply("否");
            } else {
              listDto.setIsPrintApply("是");
            }
          }
          if (obj[9] != null) {
            listDto.setRedate(obj[9].toString());
          }
          newlist.add(listDto);
        }
        loanapplytenewAF.setList(newlist);
      } else {
        loanapplytenewAF.setList(list);
      }
      // 列表信息结束
      List newallLsit = new ArrayList();
      List lis = new ArrayList();
      lis = borrowerAccDAO.findBorrowerAccListNum(contractId, borrowerName,
          empId, cardNum, buyHouseType, contranct_st, securityInfo);
      // 求出全部列表信息的
      if (lis != null) {
        Object obj[] = null;
        Iterator it = lis.iterator();
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          LoanapplyTeListDTO lisDto = new LoanapplyTeListDTO();
          lisDto.setId(obj[0].toString());
          lisDto.setContractid(obj[0].toString());
          LoanapplyTeListDTO moneylimitDTO = new LoanapplyTeListDTO();// 这里只求出贷款金额，贷款期限
          moneylimitDTO = borrowerAccDAO.findLoanmoneyLimitInfo(obj[0]
              .toString());
          if (obj[1] != null) {
            lisDto.setEmpid(obj[1].toString());
          } else {
            lisDto.setEmpid("");
          }
          lisDto.setBorrowername(obj[2].toString());
          lisDto.setCardnum(obj[3].toString());
          if (obj[4] != null) {
            lisDto.setBuyhousetype(BusiTools.getBusiValue_WL(obj[4].toString(),
                BusiConst.PLHOUSETYPE));
          } else {
            lisDto.setBuyhousetype("");
          }
          lisDto.setLoanmoney(moneylimitDTO.getLoanmoney());
          lisDto.setLoanlimit(moneylimitDTO.getLoanlimit());
          if (obj[7] != null) {
            lisDto.setContract_st(BusiTools.getBusiValue(Integer
                .parseInt(obj[7].toString()), BusiConst.PLCONTRACTSTATUS));
          } else {
            lisDto.setContract_st("");
          }
          newallLsit.add(lisDto);
        }
        loanapplytenewAF.setLis(newallLsit);
      } else {
        loanapplytenewAF.setLis(lis);
      }
      // 列表信息结束
      pagination.setNrOfElements(lis.size());
      loanapplytenewAF.setCount(lis.size() + "");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytenewAF;
  }

  /**
   * hanl 删除维护列表里的记录
   */
  public void deleteBorrowerInfo(String id, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    try {
      EndorsecontractTaDTO dtol = borrowerDAO.queryBorrowerInfoHl(id);
      String contranctid = borrowerAccDAO.findContractidByContractid(id);// 根据合同号，查询是否有处于1.5.6这三种状态的记录
      Borrower borrower = borrowerDAO.queryById(contranctid);
      if (contranctid == null) {
        throw new BusinessException("该信息已经删除了");
      } else {
        
        if (borrower.getPrivilegeBorrowerId() == null || borrower.getPrivilegeBorrowerId().compareTo(new BigDecimal(0))==0) {// 无特殊申请
          borrowerDAO.deleteBorrowerInfo(id);// 删除借款人信息
          borrowerAccDAO.deleteBorrowerAccInfo(id);// 删除账户信息表
          assistantBorrowerDAO.deleteAsistantBorrowerList(id);// 删除辅助借款人
          housesDAO.deleteHousesInfo(id);// 删除购房信息
          borrowerLoanInfoDAO.deleteBorrowerLoanInfo(id);// 删除额度
        } else {// 有特殊申请
          borrowerDAO.deleteBorrowerInfo(id);// 删除借款人信息
          borrowerAccDAO.deleteBorrowerAccInfo(id);// 删除账户信息表
          assistantBorrowerDAO.deleteAsistantBorrowerList(id);// 删除辅助借款人
          housesDAO.deleteHousesInfo(id);// 删除购房信息
          borrowerLoanInfoDAO.deleteBorrowerLoanInfo(id);// 删除额度

          SpecialBorrower sb = specialBorrowerDAO
              .queryappstatusById(new Integer(borrower.getPrivilegeBorrowerId().toString()));// 求出特殊借款人，已启用的
          if(sb!=null){
            sb.setStatus(String.valueOf(BusiConst.APPSTATUS_1));// 更改其状态
          }

        }
        ContractNumCancel contractNumCancel = new ContractNumCancel();
        String cancelcontractid = id.substring(8, id.length());
        contractNumCancel.setCancelcontractid(cancelcontractid);
        contractNumCancel.setOffice(dtol.getOffice());
        contractNumCancel.setReserveaA(id.substring(6, 8));
        contractNumCancelDAO.insert(contractNumCancel);// 将合同编号和办事处编号插入作废表
        PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_LOANAPPLMAINTAIN));// 维护
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));// 表示删除
        plOperateLog.setOpBizId(new BigDecimal(id));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(id);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {

      e.printStackTrace();
    }

  }

  /**
   * hanl 维护列表中的提交审核
   */
  public void truepproval(String id, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      String pkid = borrowerAccDAO.findContractidByContractid(id);// 根据合同号，查询是否有处于1.5.6这三种状态的记录
      if (pkid == null) {
        throw new BusinessException("已经提交了");
      } else {
        boolean isflag = borrowerLoanInfoDAO.isFindContractidByContractid(id);// 判断是否录过额度
        if (isflag) {// 入了额度
          BorrowerAcc bacc = borrowerAccDAO.queryById(id);
          bacc.setContractSt("15");// 更改账户信息状态
        } else {// 没有入额度
          throw new BusinessException("未录入额度信息不能提交审核！");
        }
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  public void chexiaoshenqing(String id, SecurityInfo securityInfo)
    throws BusinessException {
    try {
        BorrowerAcc bacc = borrowerAccDAO.queryById(id);
        if(!bacc.getContractSt().equals("15")){
          throw new BusinessException("状态不为提交审核，不允许撤销！");
        }
        if(bacc.getIsContractWrite().equals("1")){
          throw new BusinessException("此合同已经签订，不允许撤销！");
        }
        bacc.setContractSt("1");// 更改账户信息状态
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * hanl 根据办事处Id查询办事处
   */
  public String findOfficeName(String office) throws Exception {

    String name = "";
    try {
      name = organizationUnitDAO.queryOrganizationName_LY(office);// 办事处ID 查询办事处
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }

  /**
   * hanl 求楼号
   */
  public String findFloorMun(String floorId, String buyhouseorgid)
      throws Exception {

    String floornum = null;
    try {
      floornum = developProjectDAO.findFloorNumByflooridheid(floorId,
          buyhouseorgid);// 根据楼盘id和开发商id求出楼号
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
    return floornum;
  }

  /**
   * wangshuang 根据开发商和楼盘名称查询该楼盘所有的楼号
   */
  public List findAllBuildNumList(final String developId, final String floorName)
      throws Exception {
    List list = null;
    try {
      list = developProjectDAO.queryAllBuildNumList(developId, floorName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  //根据开发商查询楼盘
  public List findFloorList(final String developerId) throws Exception {
    List list = null;
    try {
      list = developerDAO.findAllFloorListById(developerId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * @author wangshuang 根据楼盘号查询层次屋号(房屋地址)
   */
  public List findHouseAddListByFloorId(final String floorId) throws Exception {
    List list = null;
    try {
      list = developProjectDAO.queryHouseAddListByFloorId(floorId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 求出开发商列表
   */
  public DeveleperNewAF findDeveleperList(Pagination pagination,
      SecurityInfo securityInfo, String flag) throws Exception {
    DeveleperNewAF develeperNewAF = new DeveleperNewAF();
    try {
      String developername = (String) pagination.getQueryCriterions().get(
          "developername");
      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      List list = developProjectDAO.findDeveloperList(developername, orderBy,
          orderother, start, pageSize, securityInfo, flag);// 开发商列表
      int count = developProjectDAO.findDeveloperCount(developername,
          securityInfo, flag);
      pagination.setNrOfElements(count);
      develeperNewAF.setList(list);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return develeperNewAF;
  }

  /**
   * hanl 求出审核审批未通过的原因
   */
  public String findNotPassReasons(String contractId, String type)
      throws Exception {
    String reason = "";
    try {
      reason = borrowerAccDAO.findReasonBycontractId(contractId, type);// 求出审核审批未通过的原因
    } catch (RuntimeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return reason;
  }

  /**
   * hanl 显示额度信息为了打印用的
   */
  public LoanapplyTdNewAF printBorrowerLoanInfoByContractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException {

    LoanapplyTdNewAF tdAF = new LoanapplyTdNewAF();

    try {
      Houses houses = housesDAO.queryById(contractid);// 如果有值就说明借款人信息和购房信息已经录入完毕。也只有这样才能进入

      if (houses != null) {
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractid);// 判断是否录过额度
        if (isflag) {// 如果已经有额度了，就把额度显示出来，否则只显示借款人信息
          BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO
              .queryById(contractid);// 查询额度信息
          tdAF.setLoanMoney(borrowerloan.getLoanMoney() + "");// 贷款金额
          tdAF.setLoantimeLimit(borrowerloan.getLoanTimeLimit());// 贷款金额期限
          tdAF.setLoanMood(borrowerloan.getLoanMode());// 还贷方式
          tdAF.setLoanMonthRate(borrowerloan.getLoanMonthRate() + "");// 每月利息

          if (borrowerloan.getLoanPoundage() == null) {
            tdAF.setLoanPoundage("");
          } else {
            tdAF.setLoanPoundage(borrowerloan.getLoanPoundage() + ""); // 手续费
          }
          if (borrowerloan.getFirstLoanMoney() == null) {
            tdAF.setFirstLoanMoney("");
          } else {
            tdAF.setFirstLoanMoney(borrowerloan.getFirstLoanMoney() + "");// 首次还
          }
          if (borrowerloan.getCorpusInterest() == null) {
            tdAF.setCorpusInterest("");
          } else {
            tdAF.setCorpusInterest(borrowerloan.getCorpusInterest() + "");// 月本息
          }

        }
        EndorsecontractTaDTO ectaAF = borrowerDAO.queryBorrowerInfoYU(
            contractid, securityInfo);// 查询PL110字段，用于借款合同信息
        tdAF.setContractId(contractid);
        tdAF.setBorrowerName(ectaAF.getDebitter());
        tdAF.setCardKind(BusiTools.getBusiValue(Integer.parseInt(ectaAF
            .getCertificateType()), BusiConst.DOCUMENTSSTATE));
        tdAF.setCardNum(ectaAF.getCertificateNum());
        tdAF.setOffice(ectaAF.getOffice());
        if (houses.getHouseType() != null) {
          if (houses.getHouseType().equals("01")) {
            tdAF.setDeveloperName(developerDAO
                .queryDeveloperNameByDeveloperId(houses.getHeadId()));
          }
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tdAF;
  }

  /**
   * 更新pl110 只是为了把path添加到pl110中
   */

  public void updateBorrowerInfo(String contractId, String path)
      throws Exception {
    try {
      Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contractId);// 查询pl110
      if (borrower != null) {
        borrower.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * 更新pl113 只是为了把path添加到pl113中
   */
  public void updateAssistantBorrowe(String idaf, String path) throws Exception {

    try {
      AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(idaf));// 求出辅助借款人；
      if (ab != null) {
        ab.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * 更新pl114 只是为了把path添加到pl114中
   */
  public void updateHouseInfo(String contractId, String path) throws Exception {
    try {
      Houses houses = housesDAO.queryById(contractId);
      if (houses != null) {
        houses.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * 更新pl115 只是为了把path添加到pl115中
   */
  public void updateBorrowerLoanInfo(String contractId, String path)
      throws Exception {
    try {
      BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO.queryById(contractId);// 查询额度信息
      if (borrowerloan != null) {
        borrowerloan.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void deletedBorrowerLoanInfo(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    try {
      String id = borrowerAccDAO.findContractidByContractid(contractId);// 根据合同号，查询是否有处于1.5.6这三种状态的记录
      if (id != null) {
        borrowerLoanInfoDAO.deleteBorrowerLoanInfo(contractId);
      } else {
        throw new BusinessException("该合同状态不正确");
      }
    } catch (BusinessException e) {
      throw e;
    }
  }

  /**
   * hanl 求出楼盘名称和银行名称
   */
  public LoanapplyTcNewAF findSomeInfo(String headid, String floorId,
      String developerPaybankAAcc) throws Exception {
    LoanapplyTcNewAF loanapplyTcNewAF = new LoanapplyTcNewAF();
    // String floorName = developerDAO.findfloorName(floorId);
    String floorName = floorId;
    List bankList = developerDAO.findBankListById(headid);// 查询银行 headid是售房单位的主键
    String bankname = "";
    for (int i = 0; i < bankList.size(); i++) {
      BankListDTO bdto = (BankListDTO) bankList.get(i);
      if (bdto.getBankKey().equals(developerPaybankAAcc)) {
        bankname = bdto.getBankValue();
      }
    }
    loanapplyTcNewAF.setDeveloperPaybank(bankname);
    loanapplyTcNewAF.setFloorId(floorName);
    return loanapplyTcNewAF;
  }

  public String findOrgRate(String orgid) throws BusinessException {
    // TODO Auto-generated method stub
    String orgRate = empDAO.findOrgRate(orgid);// 查询职工姓名和身份证
    return orgRate;
  }

  public String findEmpRate(String orgid) throws BusinessException {
    // TODO Auto-generated method stub
    String orgRate = empDAO.findOrgRate(orgid);// 查询职工姓名和身份证
    return orgRate;
  }

  public String findFloorIdByCriterions(String developerId, String floorName,
      String floorNum) throws BusinessException {
    String floorId = "";
    try {
      floorId = developProjectDAO.queryFloorIdByCriterion(developerId,
          floorName, floorNum);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return floorId;
  }

  /**
   * 查询该职工是否存在状态为还款中的记录(如果存在则直接抛出异常)
   * 
   * @param empName
   * @param cardNum
   * @throws BusinessException
   */
  public void findEmpIsPayingback(String empName, String cardNum)
      throws BusinessException {
    try {
      String empEleven = empDAO.queryempLoanIsEleven(empName, cardNum);
      if (Integer.parseInt(empEleven) > 0) {
        throw new BusinessException("该职工存在合同状态为还款中的贷款");
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
  }

  /**
   * 更新pl110中is_print_apply是否已打印
   */
  public void updatePrintStatus(String contractId) throws BusinessException {
    Borrower borrower = borrowerDAO.queryById(contractId);
    borrower.setIsPrintApply("1");
  }

  /**
   * 更新pl110中re_date(回件日期为业务日期)
   */
  public void updateRedate(String contractId, SecurityInfo securityInfo)
      throws BusinessException {
    Borrower borrower = borrowerDAO.queryById(contractId);
    borrower.setRedate(securityInfo.getUserInfo().getPlbizDate());
  }

  /**
   * 是否双方都有公积金
   * 
   * @param contractId
   * @return true:至少有一方有 false:双方都没有
   */
  public boolean isBothHaveGjj(String contractId, SecurityInfo securityInfo) throws BusinessException {
    boolean b = true;
    int flag = 0;// 标识判断辅助借款人列表里是否存在empid为空的(默认为0)
    int flag_1 = 0;// 标识判断借款人和辅助借款人至少有一人缴存公积金要大于6个月(默认为0)
    int flag_2 = 0;// 标识判断借款人和辅助借款人至少有一人开户时间大于？个月(默认为0)
    int flag_3 = 0;// 标识判断借款人和辅助借款人至少有一人欠缴小于？个月(默认为0)
    String privilegeBorrowerId = borrowerDAO
    .findPrivilegeBorrowerIdByContractid(contractId);// 是否是特殊借款
    int monthi2;
    int monthi3;
    int monthi4;
    int lianxuJiao = 0;
    int openMonth = 0;
    int qianJiao = 0;
    String plbizdate = securityInfo.getUserInfo().getPlbizDate();
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contractId);
    String office = borrower.getOffice();
    String empid = "";
    if (borrower.getEmpId() == null) {
      empid = "0";
    } else {
      empid = borrower.getEmpId().toString();
    }
    String orgid = "";
    if (borrower.getOrgId() == null) {
      orgid = "0";
    } else {
      orgid = borrower.getOrgId().toString();
    }
    
    // 公积金连续汇缴月数应大于?个月
    LoanConditionsParamSetDTO loanConditionsParamSetDTO2 = empDAO
        .querySyscollectionMonth(office);
    LoanConditionsParamSetDTO loanConditionsParamSetDTO3 = empDAO
        .querySyscollectionOpenAccMonth(office);
    
    LoanConditionsParamSetDTO loanConditionsParamSetDTO6 = empDAO
    .querySyscollectionOpenAccMonth_wsh(office);
    if(loanConditionsParamSetDTO2 != null
        && loanConditionsParamSetDTO2.getParamExplain() != null){
      monthi2 = Integer
      .parseInt(loanConditionsParamSetDTO2.getParamExplain());
    }else{
       monthi2=0;
    }
    if(loanConditionsParamSetDTO3 != null
        && loanConditionsParamSetDTO3.getParamExplain() != null){
      monthi3 = Integer
      .parseInt(loanConditionsParamSetDTO3.getParamExplain());
    }else{
       monthi3=0;
    }
    if(loanConditionsParamSetDTO6 != null
        && loanConditionsParamSetDTO6.getParamExplain() != null){
      monthi4 = Integer
      .parseInt(loanConditionsParamSetDTO6.getParamExplain());
    }else{
       monthi4=0;
    }
    
    if ((!empid.equals("") && !empid.equals("0"))
        && ((!orgid.equals("") && !orgid.equals("0")))) {// 说明是从公积金过来的人
      String opendate = empDAO.findEmpInfoOpenDate_ws(empid, orgid);
      String orgPayMonth = empDAO.findEmpInfoOpenDate_wsh(empid, orgid);
      String empPayMonth = empDAO.findEmpInfoOpenDate_wsh_emp(empid, orgid);
      String month_1 = "";
      if (Integer.parseInt(orgPayMonth) - Integer.parseInt(empPayMonth) > 0) {
        month_1 = empPayMonth;
      } else {
        month_1 = orgPayMonth;
      }
      try {
        lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6), month_1);
        openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
            plbizdate.substring(0, 6));// 开户多少个月
        qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
            plbizdate.substring(0, 6));// 欠缴多少个月
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if (loanConditionsParamSetDTO2 != null
          && loanConditionsParamSetDTO2.getParamExplain() != null) {// 说明该限制生效
        // //连续汇缴月数,返回的对错就是是否符合参数设置
        if (lianxuJiao > monthi2) {
          flag_1 = 1;
        }
      }
      if (loanConditionsParamSetDTO3 != null
          && loanConditionsParamSetDTO3.getParamExplain() != null) {// 说明该限制生效
        // //连续汇缴月数,返回的对错就是是否符合参数设置
        if (openMonth > monthi3) {
          flag_2 = 1;
        }
      }
      if (loanConditionsParamSetDTO6 != null
          && loanConditionsParamSetDTO6.getParamExplain() != null) {// 说明该限制生效
        // //连续汇缴月数,返回的对错就是是否符合参数设置
        if (qianJiao < monthi4) {
          flag_3 = 1;
        }
      }
    }
    List list = assistantBorrowerDAO
        .findAssistanBorrowerListByContractid(contractId);
    if (flag_1 == 0||flag_2 == 0||flag_3 == 0) {
      for (int i = 0; i < list.size(); i++) {
        AssistantBorrowerDTO dto = (AssistantBorrowerDTO) list.get(i);
        if (dto.getEmpid() == null) {
          empid = "0";
        } else {
          empid = dto.getEmpid().toString();
        }
        if (dto.getOrgid() == null) {
          orgid = "0";
        } else {
          orgid = dto.getOrgid().toString();
        }
        if ((!empid.equals("") && !empid.equals("0"))
            && ((!orgid.equals("") && !orgid.equals("0")))) {// 说明是从公积金过来的人
          String opendate = empDAO.findEmpInfoOpenDate_ws(empid, orgid);
          String orgPayMonth = empDAO.findEmpInfoOpenDate_wsh(empid, orgid);
          String empPayMonth = empDAO.findEmpInfoOpenDate_wsh_emp(empid, orgid);
          String month_1 = "";
          if (Integer.parseInt(orgPayMonth) - Integer.parseInt(empPayMonth) > 0) {
            month_1 = empPayMonth;
          } else {
            month_1 = orgPayMonth;
          }
          try {
            lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6),
                month_1);
            openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
                plbizdate.substring(0, 6));// 开户多少个月
            qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
                plbizdate.substring(0, 6));// 欠缴多少个月
          } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          // //连续汇缴月数,返回的对错就是是否符合参数设置
          if (lianxuJiao > monthi2) {
            flag_1 = 1;
          }
          if (openMonth > monthi3) {
            flag_2 = 1;
          }
          if (qianJiao < monthi4) {
            flag_3 = 1;
          }
        }
      }
    }
    if (flag_1 == 0&& privilegeBorrowerId == null&&loanConditionsParamSetDTO2!=null&&loanConditionsParamSetDTO2.getParamExplain()!=null&&!"1".equals(borrower.getLoanType())) {
      throw new BusinessException("夫妻双方至少有一人公积金连续汇缴月数应大于" + monthi2 + "月");
    }
    if (flag_2 == 0&& privilegeBorrowerId == null&&loanConditionsParamSetDTO3!=null&&loanConditionsParamSetDTO3.getParamExplain()!=null&&!"1".equals(borrower.getLoanType())) {
      throw new BusinessException("夫妻双方至少有一人公积金开户月数应大于" + monthi3 + "月");
    }
    if (flag_3 == 0&& privilegeBorrowerId == null&&loanConditionsParamSetDTO6!=null&&loanConditionsParamSetDTO6.getParamExplain()!=null&&!"1".equals(borrower.getLoanType())) {
      throw new BusinessException("夫妻双方至少有一人公积金欠缴月数应小于" + monthi4 + "月");
    }
    for (int i = 0; i < list.size(); i++) {
      AssistantBorrowerDTO dto = (AssistantBorrowerDTO) list.get(i);
      if (dto.getEmpid() != null) {
        flag = 1;
        break;
      }
    }
    // 异地贷款，不考虑是否夫妻双方在营口都又公积金
    if (borrower.getLoanType() != null && borrower.getLoanType().equals("1")) {
      flag = 1;
    }
    if (privilegeBorrowerId == null && borrower.getEmpId() == null && flag == 0) {
      b = false;
      throw new BusinessException("夫妻双方至少一方有公积金!");
    }
    return b;
  }
  private LoanConditionsSetDAO loanConditionsSetDAO = null;

  public void setLoanConditionsSetDAO(LoanConditionsSetDAO loanConditionsSetDAO) {
    this.loanConditionsSetDAO = loanConditionsSetDAO;
  }
}
