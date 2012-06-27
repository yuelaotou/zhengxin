package org.xpup.hafmis.sysloan.contractchg.baseinfochg.business;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractChgDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.CongealInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractChg;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.contractchg.baseinfochg.bsinterface.IBaseinfochgBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BuyHouserDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyNewDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyTeListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTeNewAF;

public class BaseinfochgBS implements IBaseinfochgBS {

  private BorrowerDAO borrowerDAO = null;

  private OrgDAO orgDAO = null;

  private DeveloperDAO developerDAO = null;

  private AssistantBorrowerDAO assistantBorrowerDAO = null;

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private EmpDAO empDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private DevelopProjectDAO developProjectDAO = null;

  private HousesDAO housesDAO = null;

  private ContractChgDAO contractChgDAO = null;

  private CongealInfoDAO congealInfoDAO = null;

  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }

  public void setContractChgDAO(ContractChgDAO contractChgDAO) {
    this.contractChgDAO = contractChgDAO;
  }

  public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
    this.assistantBorrowerDAO = assistantBorrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  // 显示列表
  public LoanapplyTeNewAF showBaseinfochgTa(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {

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
      String contranct_st = null;

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List newlist = new ArrayList();
      List list = new ArrayList();
      list = borrowerAccDAO.findBasicBorrowerAccList(contractId, borrowerName,
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
          listDto.setContractid(obj[0].toString());
          if (obj[1] != null) {
            listDto.setEmpid(BusiTools.convertSixNumber(obj[1].toString()));
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
          if (obj[5] != null) {
            listDto.setLoanmoney(obj[5].toString());
          } else {
            listDto.setLoanmoney("");
          }
          if (obj[6] != null) {
            listDto.setLoanlimit(obj[6].toString());
          } else {
            listDto.setLoanlimit("");
          }
          if (obj[7] != null) {
            String temp_st = BusiTools.getBusiValue(Integer.parseInt(obj[7]
                .toString()), BusiConst.PLCONTRACTSTATUS);
            listDto.setContract_st(temp_st);
          } else {
            listDto.setContract_st("");
          }
          newlist.add(listDto);
        }
        loanapplytenewAF.setList(newlist);
      } else {
        loanapplytenewAF.setList(list);
      }
      // 列表信息结束
      List lis = new ArrayList();
      lis = borrowerAccDAO.findBorrowerAccList(contractId, borrowerName, empId,
          cardNum, buyHouseType, contranct_st, securityInfo);

      pagination.setNrOfElements(lis.size());
      loanapplytenewAF.setCount(lis.size() + "");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytenewAF;
  }

  // 显示借款人信息
  public LoanapplyNewAF showBaseinfochgTb(String contranctid) throws Exception {

    LoanapplyNewAF loanaf = new LoanapplyNewAF();
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contranctid);
    if (borrower.getEmpSt() == null) {
      borrower.setEmpSt_("");
    } else {
      borrower.setEmpSt_(BusiTools.getBusiValue(Integer.parseInt(borrower
          .getEmpSt()), BusiConst.OLDPAYMENTSTATE));
    }
    if (borrower.getOtherArrearage() != null) {
      borrower
          .setOtherArrearage_(borrower.getOtherArrearage().equals("0") ? "是"
              : "否");
    }
    if (borrower.getMonthPay() == null) {
      borrower.setMonthPay(new BigDecimal(0.00));
    }
    if (borrower.getMonthSalary() == null) {
      borrower.setMonthSalary(new BigDecimal(0.00));
    }
    if (borrower.getAccBlnce() == null) {
      borrower.setAccBlnce(new BigDecimal(0.00));
    }
    loanaf.setBorrower(borrower);
    return loanaf;
  }

  // 修改借款人信息
  public void updateBorrowerInfo(String contranctid,
      LoanapplyNewAF loanapplyNewAF, SecurityInfo securityInfo)
      throws Exception {
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contranctid);
    try {
      String temp_i = "0";
      // 修改职务
      String business = borrower.getBusiness();
      if (business == null) {
        business = "";
      }
      if (!business.equals(loanapplyNewAF.getBorrower().getBusiness().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("职务");
        contractchg.setChgBefInfo(business);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getBusiness()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        borrower.setBusiness(loanapplyNewAF.getBorrower().getBusiness().trim());
        temp_i = "1";
      }
      // 修改身份证号
      String cardNum = borrower.getCardNum();
      if (cardNum == null) {
        cardNum = "";
      }
      if (!cardNum.equals(loanapplyNewAF.getBorrower().getCardNum().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("身份证号");
        contractchg.setChgBefInfo(cardNum);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getCardNum()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setCardNum(loanapplyNewAF.getBorrower().getCardNum().trim());
      }
      // 修改职称
      String title = borrower.getTitle();
      if (title == null) {
        title = "";
      }
      if (!title.equals(loanapplyNewAF.getBorrower().getTitle().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("职称");
        contractchg.setChgBefInfo(title);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getTitle()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setTitle(loanapplyNewAF.getBorrower().getTitle().trim());
      }
      // 修改民族
      String nation = borrower.getNation();
      if (nation == null) {
        nation = "";
      }
      if (!nation.equals(loanapplyNewAF.getBorrower().getNation().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("民族");
        contractchg.setChgBefInfo(nation);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getNation()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setNation(loanapplyNewAF.getBorrower().getNation().trim());
      }
      // 修改户籍所在地
      String nationplace = borrower.getNativePlace();
      if (nationplace == null) {
        nationplace = "";
      }
      if (!nationplace.equals(loanapplyNewAF.getBorrower().getNativePlace()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("户籍所在地");
        contractchg.setChgBefInfo(nationplace);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getNativePlace()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setNativePlace(loanapplyNewAF.getBorrower().getNativePlace()
            .trim());
      }
      // 修改婚姻状况
      String marriage = borrower.getMarriageSt();
      if (marriage == null) {
        marriage = "";
      }
      if (!marriage.equals(loanapplyNewAF.getBorrower().getMarriageSt().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("婚姻状况");
        contractchg.setChgBefInfo(marriage);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getMarriageSt()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setMarriageSt(loanapplyNewAF.getBorrower().getMarriageSt()
            .trim());
      }
      // 修改文化程度
      String degree = borrower.getDegree();
      if (degree == null) {
        degree = "";
      }
      if (!degree.equals(loanapplyNewAF.getBorrower().getDegree().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("文化程度");
        contractchg.setChgBefInfo(degree);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getDegree()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setDegree(loanapplyNewAF.getBorrower().getDegree().trim());
      }
      // 修改家庭住址
      String homeaddr = borrower.getHomeAddr();
      if (homeaddr == null) {
        homeaddr = "";
      }
      if (!homeaddr.equals(loanapplyNewAF.getBorrower().getHomeAddr().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("家庭住址");
        contractchg.setChgBefInfo(homeaddr);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHomeAddr()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHomeAddr(loanapplyNewAF.getBorrower().getHomeAddr().trim());
      }
      // 修改居住地址邮政编码
      String homemail = borrower.getHomeMail();
      if (homemail == null) {
        homemail = "";
      }
      if (!homemail.equals(loanapplyNewAF.getBorrower().getHomeMail().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("居住地址邮政编码");
        contractchg.setChgBefInfo(homemail);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHomeMail()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHomeMail(loanapplyNewAF.getBorrower().getHomeMail().trim());
      }
      // 修改住宅电话
      String housetel = borrower.getHouseTel();
      if (housetel == null) {
        housetel = "";
      }
      if (!housetel.equals(loanapplyNewAF.getBorrower().getHouseTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("住宅电话");
        contractchg.setChgBefInfo(housetel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHouseTel()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHouseTel(loanapplyNewAF.getBorrower().getHouseTel().trim());
      }
      // 修改移动电话
      String homemobile = borrower.getHomeMobile();
      if (homemobile == null) {
        homemobile = "";
      }
      if (!homemobile.equals(loanapplyNewAF.getBorrower().getHomeMobile()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("移动电话");
        contractchg.setChgBefInfo(homemobile);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHomeMobile()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHomeMobile(loanapplyNewAF.getBorrower().getHomeMobile()
            .trim());
      }
      // 修改单位名称
      String orgname = borrower.getOrgName();
      if (orgname == null) {
        orgname = "";
      }
      if (!orgname.equals(loanapplyNewAF.getBorrower().getOrgName().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("单位名称");
        contractchg.setChgBefInfo(orgname);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgName()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgName(loanapplyNewAF.getBorrower().getOrgName().trim());
      }
      // 修改单位电话
      String orgtel = borrower.getOrgTel();
      if (orgtel == null) {
        orgtel = "";
      }
      if (!orgtel.equals(loanapplyNewAF.getBorrower().getOrgTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("单位电话");
        contractchg.setChgBefInfo(orgtel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgTel()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgTel(loanapplyNewAF.getBorrower().getOrgTel().trim());
      }
      // 修改单位的邮政编码
      String orgmail = borrower.getOrgMail();
      if (orgmail == null) {
        orgmail = "";
      }
      if (!orgmail.equals(loanapplyNewAF.getBorrower().getOrgMail().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("邮政编码");
        contractchg.setChgBefInfo(orgmail);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgMail()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgMail(loanapplyNewAF.getBorrower().getOrgMail().trim());
      }
      // 修改单位地址
      String orgaddr = borrower.getOrgAddr();
      if (orgaddr == null) {
        orgaddr = "";
      }
      if (!orgaddr.equals(loanapplyNewAF.getBorrower().getOrgAddr().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("单位地址");
        contractchg.setChgBefInfo(orgaddr);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgAddr()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgAddr(loanapplyNewAF.getBorrower().getOrgAddr().trim());
      }
      // 修改其他联系人姓名A
      String contactAName = borrower.getContactAName();
      if (contactAName == null) {
        contactAName = "";
      }
      if (!contactAName.equals(loanapplyNewAF.getBorrower().getContactAName()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人姓名A");
        contractchg.setChgBefInfo(contactAName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAName(loanapplyNewAF.getBorrower().getContactAName()
            .trim());
      }
      // 修改与借款人关系A
      String relationA = borrower.getRelationA();
      if (relationA == null) {
        relationA = "";
      }
      if (!relationA.equals(loanapplyNewAF.getBorrower().getRelationA().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("与借款人关系A");
        contractchg.setChgBefInfo(relationA);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getRelationA()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setRelationA(loanapplyNewAF.getBorrower().getRelationA()
            .trim());
      }
      // 修改其他联系人工作单位A
      String contactAOrgName = borrower.getContactAOrgName();
      if (contactAOrgName == null) {
        contactAOrgName = "";
      }
      if (!contactAOrgName.equals(loanapplyNewAF.getBorrower()
          .getContactAOrgName().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人工作单位A");
        contractchg.setChgBefInfo(contactAOrgName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAOrgName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAOrgName(loanapplyNewAF.getBorrower()
            .getContactAOrgName().trim());
      }
      // 修改其他联系人单位电话A
      String contactAOrgTel = borrower.getContactAOrgTel();
      if (contactAOrgTel == null) {
        contactAOrgTel = "";
      }
      if (!contactAOrgTel.equals(loanapplyNewAF.getBorrower()
          .getContactAOrgTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人单位电话A");
        contractchg.setChgBefInfo(contactAOrgTel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAOrgTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAOrgTel(loanapplyNewAF.getBorrower()
            .getContactAOrgTel().trim());
      }
      // 修改其他联系人住宅电话A
      String contactAHouseTel = borrower.getContactAHouseTel();
      if (contactAHouseTel == null) {
        contactAHouseTel = "";
      }
      if (!contactAHouseTel.equals(loanapplyNewAF.getBorrower()
          .getContactAHouseTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人住宅电话A");
        contractchg.setChgBefInfo(contactAHouseTel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAHouseTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAHouseTel(loanapplyNewAF.getBorrower()
            .getContactAHouseTel().trim());
      }
      // 修改其他联系人移动电话A
      String contactAMobile = borrower.getContactAMobile();
      if (contactAMobile == null) {
        contactAMobile = "";
      }
      if (!contactAMobile.equals(loanapplyNewAF.getBorrower()
          .getContactAMobile().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人移动电话A");
        contractchg.setChgBefInfo(contactAMobile);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAMobile().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAMobile(loanapplyNewAF.getBorrower()
            .getContactAMobile().trim());
      }
      // 修改其他联系人姓名B
      String contactBName = borrower.getContactBName();
      if (contactBName == null) {
        contactBName = "";
      }
      if (!contactBName.equals(loanapplyNewAF.getBorrower().getContactBName()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人姓名B");
        contractchg.setChgBefInfo(contactBName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBName(loanapplyNewAF.getBorrower().getContactBName()
            .trim());
      }
      // 修改与借款人关系B
      String relationB = borrower.getRelationB();
      if (relationB == null) {
        relationB = "";
      }
      if (!relationB.equals(loanapplyNewAF.getBorrower().getRelationB().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("与借款人关系B");
        contractchg.setChgBefInfo(relationB);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getRelationB()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setRelationB(loanapplyNewAF.getBorrower().getRelationB()
            .trim());
      }
      // 修改其他联系人工作单位B
      String contactBOrgName = borrower.getContactBOrgName();
      if (contactBOrgName == null) {
        contactBOrgName = "";
      }
      if (!contactBOrgName.equals(loanapplyNewAF.getBorrower()
          .getContactBOrgName().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人工作单位B");
        contractchg.setChgBefInfo(contactBOrgName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBOrgName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBOrgName(loanapplyNewAF.getBorrower()
            .getContactBOrgName().trim());
      }
      // 修改其他联系人单位电话B
      String contactborgtel = borrower.getContactBOrgTel();
      if (contactborgtel == null) {
        contactborgtel = "";
      }
      if (!contactborgtel.equals(loanapplyNewAF.getBorrower()
          .getContactBOrgTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人单位电话B");
        contractchg.setChgBefInfo(contactborgtel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBOrgTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBOrgTel(loanapplyNewAF.getBorrower()
            .getContactBOrgTel().trim());
      }
      // 修改其他联系人住宅电话B
      String contactBHouseTel = borrower.getContactBHouseTel();
      if (contactBHouseTel == null) {
        contactBHouseTel = "";
      }
      if (!contactBHouseTel.equals(loanapplyNewAF.getBorrower()
          .getContactBHouseTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人住宅电话B");
        contractchg.setChgBefInfo(contactBHouseTel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBHouseTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBHouseTel(loanapplyNewAF.getBorrower()
            .getContactBHouseTel().trim());
      }
      // 修改其他联系人移动电话B
      String contactBMobile = borrower.getContactBMobile();
      if (contactBMobile == null) {
        contactBMobile = "";
      }
      if (!contactBMobile.equals(loanapplyNewAF.getBorrower()
          .getContactBMobile().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他联系人移动电话B");
        contractchg.setChgBefInfo(contactBMobile);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBMobile().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBMobile(loanapplyNewAF.getBorrower()
            .getContactBMobile().trim());
      }
      // 修改备注
      String remark = borrower.getRemark();
      if (remark == null) {
        remark = "";
      }
      if (!remark.equals(loanapplyNewAF.getBorrower().getRemark().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("备注");
        contractchg.setChgBefInfo(remark);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getRemark()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setRemark(loanapplyNewAF.getBorrower().getRemark().trim());
      }
      // 修改其他欠款
      String otherArrearage = borrower.getOtherArrearage();
      if (otherArrearage == null) {
        otherArrearage = "";
      }
      if (!otherArrearage.equals(loanapplyNewAF.getBorrower()
          .getOtherArrearage())) {
        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("其他欠款");
        contractchg.setChgBefInfo(otherArrearage);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getOtherArrearage());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOtherArrearage(loanapplyNewAF.getBorrower()
            .getOtherArrearage());
      }
      if (temp_i.equals("1")) {
        PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO));// 基本信息变更_借款人信息
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(contranctid));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contranctid);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * hanl 显示辅助借款人信息
   */
  public LoanapplyTbNewAF findAssistanBorrowerInfo_yg(String contractid,
      String auxiliaryid, String sun) throws BusinessException {
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
        if (ab.getStatus().equals("1")) {
          loanapplytbnewAF.setRelationStatus("作废(离婚)");
        } else {
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
      if (sun != null && sun.equals("1")) {
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
        if (ab.getStatus().equals("1")) {
          loanapplytbnewAF.setRelationStatus("作废(离婚)");
        } else {
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

  // 显示辅助借款人信息
  public LoanapplyTbNewAF findAssistanBorrowerInfochg(String contranctid,
      String maxauxiliaryid) throws Exception {

    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    try {
      List list = assistantBorrowerDAO
          .findAssistanBorrowerListByContractid(contranctid);
      if (list.size() == 0) {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contranctid);
        loanapplytbnewAF.setContractId(contranctid);
        loanapplytbnewAF.setBorrowerName(borrowernname);
      } else {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contranctid);
        String maxauxiliaryidnew = "";
        if (maxauxiliaryid == null) {
          maxauxiliaryidnew = assistantBorrowerDAO
              .findMaxAuxiliaryidByContractid(contranctid);
        } else {
          maxauxiliaryidnew = maxauxiliaryid;
        }
        AssistantBorrower ab = new AssistantBorrower();
        ab = assistantBorrowerDAO.queryById(new Integer(maxauxiliaryidnew));
        loanapplytbnewAF.setContractId(ab.getContractId());
        loanapplytbnewAF.setBorrowerName(borrowernname);
        if (ab.getEmpId() == null || ab.getEmpId().toString().equals("0")) {
          loanapplytbnewAF.setEmpId("");
        } else {
          loanapplytbnewAF.setEmpId(BusiTools.convertSixNumber(ab.getEmpId()
              .toString()));
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
        if (ab.getEmpSt() != null) {
          loanapplytbnewAF.setEmpSt(ab.getEmpSt());
        } else {
          loanapplytbnewAF.setEmpSt("");
        }
        loanapplytbnewAF.setBgnDate(ab.getBgnDate());
        loanapplytbnewAF.setEndDate(ab.getEndDate());
        loanapplytbnewAF.setList(list);
        loanapplytbnewAF.setMaxauxiliaryid(maxauxiliaryidnew);

      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytbnewAF;
  }

  // 查找辅助借款人
  public LoanapplyTbNewAF findAssistantBorrowerInfo(String empId, String orgId,
      String contractaId) throws BusinessException {

    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    try {
      boolean flag = empDAO.getEmpidCount(new Integer(empId));
      if (flag) {
        EmpInfo empinfo = empDAO.findEmpInfoByEmpid(empId, orgId);
        String empname = empinfo.getName();
        String empcardnum = empinfo.getCardNum();
        String auxiliaryid = assistantBorrowerDAO.findAuxiliaryidByNameCardnum(
            empname, empcardnum, contractaId);
        if (auxiliaryid == null) {
          LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empId, orgId);
          loanapplytbnewAF.setEmpId(empId);
          loanapplytbnewAF.setName(inf.getEmpname());
          loanapplytbnewAF.setSex(inf.getSex().toString());
          loanapplytbnewAF.setCardKind(inf.getCardking());
          // 用来放到隐藏域里的，在提交时从隐藏域里取得这两个值，
          loanapplytbnewAF.setSexhidden(inf.getSex().toString());
          loanapplytbnewAF.setCardKindhidden(inf.getCardking());

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
          throw new BusinessException("该职工已经是辅助借款人");
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

    return loanapplytbnewAF;
  }

  // 修改辅助借款人
  public void addupdateAssistantBorrowerInfo(LoanapplyTbNewAF loanapplytbnewAF,
      SecurityInfo securityInfo, String temp_addtypechg)
      throws BusinessException, Exception {
    try {
      String contractid = loanapplytbnewAF.getContractId().trim();
      String name = loanapplytbnewAF.getName().trim();
      String cardnum = loanapplytbnewAF.getCardNum().trim();
      String auxiliaryid = assistantBorrowerDAO
          .findAssistanBorrowerByContractidNameCardnum(contractid, name,
              cardnum);
      if (auxiliaryid == null) {

        AssistantBorrower ab = new AssistantBorrower();// 创建辅助借款人
        ab.setContractId(loanapplytbnewAF.getContractId().trim());
        if (!loanapplytbnewAF.getEmpId().equals("")) {
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
        if (!loanapplytbnewAF.getAge().equals("")) {
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
        if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
          ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
        }
        ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
        ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
        ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
        ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
        if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
          ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce().trim()));
        }
        if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
          ab.setMonthSalary(new BigDecimal(loanapplytbnewAF.getMonthSalary()
              .trim()));
        }
        if (!loanapplytbnewAF.getMonthPay().trim().equals("")) {
          ab.setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay().trim()));
        }
        // if(!loanapplytbnewAF.getEmpSt().trim().equals("")){
        // int empSt1 = BusiTools.getBusiKey(loanapplytbnewAF.getEmpSt().trim(),
        // BusiConst.PLCONTRACTSTATUS);
        // ab.setEmpSt(new Integer(empSt1).toString());
        // }
        ab.setEmpSt(loanapplytbnewAF.getEmpSt());
        ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
        ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
        ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
        ab.setOperator(securityInfo.getUserName().trim());
        ab.setOpTime(new Date());
        assistantBorrowerDAO.insert(ab);// 插入辅助借款人

        CongealInfo congealInfo = new CongealInfo();// 冻结表
        congealInfo.setContractId(contractid);
        if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
          congealInfo.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId()
              .trim()));
        }
        if (!loanapplytbnewAF.getEmpId().trim().equals("")) {
          congealInfo.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId()
              .trim()));
        }
        congealInfo.setEmpName(loanapplytbnewAF.getName());
        congealInfo.setCardKind(new Integer(loanapplytbnewAF
            .getCardKindhidden()).toString());// 有
        congealInfo.setCardNum(loanapplytbnewAF.getCardNum().trim());
        congealInfo.setPersonId(ab.getAuxiliaryId().toString());
        congealInfo.setStatus(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION));
        congealInfo.setType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYTYPE));
        congealInfoDAO.insert(congealInfo);// 插入冻结表

        PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// 基本信息变更_辅助借款人信息
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId().toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      } else {

        if (temp_addtypechg != null) {// 说明按过添加按钮
          auxiliaryid = assistantBorrowerDAO
              .findAssistanBorrowerByContractidNameCardnum(contractid, name,
                  cardnum);
          if (auxiliaryid == null) {// 可以添加
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
            if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
              ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
            }
            ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
            ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
            ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
            ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
            if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
              ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce()
                  .trim()));
            }
            if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
              ab.setMonthSalary(new BigDecimal(loanapplytbnewAF
                  .getMonthSalary().trim()));
            }

            if (!loanapplytbnewAF.getEmpSt().trim().equals("")) {
              int empSt1 = BusiTools.getBusiKey(loanapplytbnewAF.getEmpSt()
                  .trim(), BusiConst.PLCONTRACTSTATUS);
              ab.setEmpSt(new Integer(empSt1).toString());
            }
            ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
            ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
            ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
            ab.setOperator(securityInfo.getUserName().trim());
            ab.setOpTime(new Date());
            assistantBorrowerDAO.insert(ab);// 插入辅助借款人

            CongealInfo congealInfo = new CongealInfo();// 冻结表
            congealInfo.setContractId(contractid);
            if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
              congealInfo.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId()
                  .trim()));
            }
            if (!loanapplytbnewAF.getEmpId().trim().equals("")) {
              congealInfo.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId()
                  .trim()));
            }
            congealInfo.setEmpName(loanapplytbnewAF.getName().trim());
            congealInfo.setCardKind(new Integer(loanapplytbnewAF
                .getCardKindhidden()).toString());// 有
            congealInfo.setCardNum(loanapplytbnewAF.getCardNum().trim());
            congealInfo.setPersonId(ab.getAuxiliaryId().toString());
            congealInfo.setStatus(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION));
            congealInfo.setType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYTYPE));
            congealInfoDAO.insert(congealInfo);// 插入冻结表

            PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// 基本信息变更_辅助借款人信息
            plOperateLog.setOpButton(String
                .valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
            plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId()
                .toString()));
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpTime(new Date());
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setContractId(contractid);
            plOperateLogDAO.insert(plOperateLog);
          } else {// 已经是辅助借款人了，不能在添加了
            throw new BusinessException("该职工已经为辅助借款人");
          }
        } else { // 更新辅助借款人信息
          AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(
              auxiliaryid));
          ab.setAge(new BigDecimal(loanapplytbnewAF.getAge()));
          ab.setBirthday(loanapplytbnewAF.getBirthday());
          ab.setRelation(loanapplytbnewAF.getRelation());
          if (ab.getStatus().equals("1")) {
            ab.setStatus("0");
          }
          String temp_i = "0";
          // 民族
          String nationst = ab.getNation();
          if (nationst == null) {
            nationst = "";
          }

          if (!nationst.equals(loanapplytbnewAF.getNation().trim())) {
            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("民族");
            contractchg.setChgBefInfo(nationst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getNation().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setNation(loanapplytbnewAF.getNation().trim());
          }
          // 修改户籍所在地
          String nationplacest = ab.getNativePlace();
          if (nationplacest == null) {
            nationplacest = "";
          }
          if (!nationplacest.equals(loanapplytbnewAF.getNativePlace().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("户籍所在地");
            contractchg.setChgBefInfo(nationplacest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getNativePlace().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
          }
          // 职务
          String businessst = ab.getBusiness();
          if (businessst == null) {
            businessst = "";
          }
          if (!businessst.equals(loanapplytbnewAF.getBusiness().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("职务");
            contractchg.setChgBefInfo(businessst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getBusiness().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
          }
          // 职称
          String titlest = ab.getTitle();
          if (titlest == null) {
            titlest = "";
          }
          if (!titlest.equals(loanapplytbnewAF.getTitle().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("职称");
            contractchg.setChgBefInfo(titlest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getTitle().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setTitle(loanapplytbnewAF.getTitle().trim());
          }
          // 婚姻状况
          String marriages = ab.getMarriageSt();
          if (marriages == null) {
            marriages = "";
          }
          if (!marriages.equals(loanapplytbnewAF.getMarriageSt().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("婚姻状况");
            contractchg.setChgBefInfo(marriages);
            contractchg.setChgEndInfo(loanapplytbnewAF.getMarriageSt().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
          }
          // 文化程度
          String degreest = ab.getDegree();
          if (degreest == null) {
            degreest = "";
          }
          if (!degreest.equals(loanapplytbnewAF.getDegree().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("文化程度");
            contractchg.setChgBefInfo(degreest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getDegree().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setDegree(loanapplytbnewAF.getDegree().trim());
          }
          // 家庭住址
          String homeaddrst = ab.getHomeAddr();
          if (homeaddrst == null) {
            homeaddrst = "";
          }
          if (!homeaddrst.equals(loanapplytbnewAF.getHomeAddr().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("家庭住址");
            contractchg.setChgBefInfo(homeaddrst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHomeAddr().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
          }
          // 邮政编码
          String homemailst = ab.getHomeMail();
          if (homemailst == null) {
            homemailst = "";
          }
          if (!homemailst.equals(loanapplytbnewAF.getHomeMail().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("邮政编码");
            contractchg.setChgBefInfo(homemailst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHomeMail().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
          }
          // 移动电话
          String homemobilest = ab.getHomeMobile();
          if (homemobilest == null) {
            homemobilest = "";
          }
          if (!homemobilest.equals(loanapplytbnewAF.getHomeMobile().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("移动电话");
            contractchg.setChgBefInfo(homemobilest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHomeMobile().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
          }
          // 住宅电话
          String housetel = ab.getHouseTel();
          if (housetel == null) {
            housetel = "";
          }
          if (!housetel.equals(loanapplytbnewAF.getHouseTel().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("住宅电话");
            contractchg.setChgBefInfo(housetel);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHouseTel().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
          }
          // 单位名称
          String orgnamest = ab.getOrgName();
          if (orgnamest == null) {
            orgnamest = "";
          }
          if (!orgnamest.equals(loanapplytbnewAF.getOrgName().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("单位名称");
            contractchg.setChgBefInfo(orgnamest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgName().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
          }
          // 单位地址
          String orgaddrst = ab.getOrgAddr();
          if (orgaddrst == null) {
            orgaddrst = "";
          }
          if (!orgaddrst.equals(loanapplytbnewAF.getOrgAddr().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("单位地址");
            contractchg.setChgBefInfo(orgaddrst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgAddr().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
          }
          // 单位电话
          String orgtelst = ab.getOrgTel();
          if (orgtelst == null) {
            orgtelst = "";
          }
          if (!orgtelst.equals(loanapplytbnewAF.getOrgTel().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("单位电话");
            contractchg.setChgBefInfo(orgtelst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgTel().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
          }
          // 单位邮编
          String orgmailst = ab.getOrgMail();
          if (orgmailst == null) {
            orgmailst = "";
          }
          if (!orgmailst.equals(loanapplytbnewAF.getOrgMail().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("单位邮编");
            contractchg.setChgBefInfo(orgmailst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgMail().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
          }

          if (temp_i.equals("1")) {
            PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// 基本信息变更_辅助借款人信息
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
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 作废辅助借款人
  public void canceledAssistanBorrower(String id, SecurityInfo securityInfo)
      throws BusinessException {

    try {
      AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(id));
      ab.setOpTime(new Date());
      if (ab.getStatus().equals("0")) {
        ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_CANCELED));// 更新辅助借款人表，作废
        String contactid = ab.getContractId();
        String status = String
            .valueOf(BusiConst.PLPREPAYMENTFEES_CONGEALINFOTHAW);
        congealInfoDAO.updateCongealInfo(status, id, contactid);
        // 修改pl400表的职工状态为作废 郭婧平
        BigDecimal empId = ab.getEmpId();
        if (empId != null && !empId.equals("")) {
          assistantBorrowerDAO.updateEmpStatus(empId, contactid);
        }
        if (ab.getName() != null && !ab.getName().equals("")) {
          assistantBorrowerDAO.updateEmpStatus_yg(ab.getName(),
              ab.getCardNum(), contactid);
        }
        PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// 基本信息变更_辅助借款人信息
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
        plOperateLog.setOpBizId(new BigDecimal(id));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contactid);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        throw new BusinessException("该条记录已作废");
      }
    } catch (BusinessException e) {
      throw e;
    }

  }

  // 显示购房信息
  public LoanapplyTcNewAF showHouseInfo(String contranctid) throws Exception {
    LoanapplyTcNewAF lptcAF = new LoanapplyTcNewAF();
    try {
      String borrowerName = borrowerDAO
          .findBorrowerNameInfoByContractid(contranctid);
      lptcAF.setContractId(contranctid);
      lptcAF.setBorrowerName(borrowerName);

      Houses houses = housesDAO.queryById(contranctid);
      if (houses.getHouseType() != null) {
        lptcAF.setPhotoUrl(houses.getPhotoUrl());
        String headid = houses.getHeadId();
        if (headid != null) {// 商品房
          BuyHouserDTO buyDTO = new BuyHouserDTO();
          buyDTO = developerDAO.findHosesSomeInfo(headid);
          lptcAF.setOrgName(buyDTO.getDeveloperName());
          lptcAF.setDeveloperTel(houses.getDeveloperTel());
          lptcAF.setDeveloperPaybank(houses.getDeveloperPaybank());
          lptcAF.setDeveloperPaybankAAcc(houses.getDeveloperPaybankAAcc());
          String floorid = "";
          String floorname = "";
          String flooraddr = "";
          if (houses.getFloorId() != null) {
            floorid = houses.getFloorId().toString();// 这里是ID，要得到名字需要通过它来求出
            floorname = developProjectDAO.findFloorByPkid(floorid);
            flooraddr = developProjectDAO.findFloorByPkid_yg(floorid);
          }
          lptcAF.setFloorId(floorname);// 这里要存的是楼盘名称
          if(houses.getFloorNum()!=null){
            if(houses.getFloorNum().indexOf("-")!=0){
              lptcAF.setFloorNum(houses.getFloorNum());
            }else{
              lptcAF.setFloorNum(flooraddr+houses.getFloorNum());
            }
          }
          lptcAF.setRoomNum(houses.getRoomNum());
          // lptcAF.setFirstPay(houses.getFirstPay().toString());
          lptcAF.setHouseTotlePrice(houses.getHouseTotlePrice().toString());
          lptcAF.setHouseArea(houses.getHouseArea().toString());
          lptcAF.setHousePrice(houses.getHouseTotlePrice().divide(
              houses.getHouseArea(), 0, BigDecimal.ROUND_HALF_UP)
              + "");
          lptcAF.setBuyHouseContractId(houses.getBuyHouseContractId());
//          lptcAF.setFirstTol(houses.getFirstPay().divide(
//              houses.getHouseTotlePrice(), 2, BigDecimal.ROUND_HALF_UP)
//              .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1), 0,
//                  BigDecimal.ROUND_HALF_UP)
//              + "" + "%");
          if (houses.getIsNowhouse() != null
              && !"".equals(houses.getIsNowhouse())) {

            lptcAF.setIsNowhouse(BusiTools.getBusiValue(Integer.parseInt(houses
                .getIsNowhouse()), BusiConst.YesNo));

          }
          lptcAF.setOverDate(houses.getOverDate());
          lptcAF.setBuildRightNum(houses.getBuildRightNum());
          lptcAF.setAgreementDate(houses.getAgreementDate());
          lptcAF.setHouseAddr(houses.getHouseAddr());
          lptcAF.setRemark1(houses.getRemark());
        } else {// 二手房
          lptcAF.setBargainorName(houses.getBargainorName());
          if (houses.getBargainorCardKind() != null
              && !"".equals(houses.getBargainorCardKind())) {
            lptcAF.setBargainorCardKind(BusiTools.getBusiValue(Integer
                .parseInt(houses.getBargainorCardKind()),
                BusiConst.DOCUMENTSSTATE));
          }

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
          lptcAF.setRemark2(houses.getRemark());
        }
        if (houses.getHouseType().equals("01")) {// 商品房
          lptcAF.setHouseType("01");
          lptcAF.setHouseTypehidden("01");
        } else {// 二手房
          lptcAF.setHouseType("02");
          lptcAF.setHouseTypehidden("02");
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return lptcAF;
  }

  // 修改辅助借款人
  public void updateHousesInfo(LoanapplyTcNewAF loanapplytcnewAF,
      SecurityInfo securityInfo) throws Exception {
    try {
      String contractid = loanapplytcnewAF.getContractId();
      Houses houses = housesDAO.queryById(contractid);
      String housetype = houses.getHouseType();
      String temp_i = "0";
      if (housetype.equals("01")) {// 商品房
        // 修改产权编号
        String rightnum = houses.getBuildRightNum();
        if (rightnum == null) {
          rightnum = "";
        }
        if (!rightnum.equals(loanapplytcnewAF.getBuildRightNum().trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("产权证号码");
          contractchg.setChgBefInfo(rightnum);
          contractchg.setChgEndInfo(loanapplytcnewAF.getBuildRightNum().trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBuildRightNum(loanapplytcnewAF.getBuildRightNum().trim());
        }
      } else {// 二手房
        // 修改固定电话
        String bargainorTel = houses.getBargainorTel();
        if (bargainorTel == null) {
          bargainorTel = "";
        }
        if (!bargainorTel.equals(loanapplytcnewAF.getBargainorTel().trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("固定电话");
          contractchg.setChgBefInfo(bargainorTel);
          contractchg.setChgEndInfo(loanapplytcnewAF.getBargainorTel().trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBargainorTel(loanapplytcnewAF.getBargainorTel().trim());
        }
        // 修改原产权证编号
        String bargainorHousepropertyCode = houses
            .getBargainorHousepropertyCode();
        if (bargainorHousepropertyCode == null) {
          bargainorHousepropertyCode = "";
        }
        if (!bargainorHousepropertyCode.equals(loanapplytcnewAF
            .getBargainorHousepropertyCode().trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("原产权证编号");
          contractchg.setChgBefInfo(bargainorHousepropertyCode);
          contractchg.setChgEndInfo(loanapplytcnewAF
              .getBargainorHousepropertyCode().trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBargainorHousepropertyCode(loanapplytcnewAF
              .getBargainorHousepropertyCode().trim());
        }
        // 修改移动电话
        String bargainorMobile = houses.getBargainorMobile();
        if (bargainorMobile == null) {
          bargainorMobile = "";
        }
        if (!bargainorMobile.equals(loanapplytcnewAF.getBargainorMobile()
            .trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("移动电话");
          contractchg.setChgBefInfo(bargainorMobile);
          contractchg.setChgEndInfo(loanapplytcnewAF.getBargainorMobile()
              .trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBargainorMobile(loanapplytcnewAF.getBargainorMobile()
              .trim());
        }
      }
      if (temp_i.equals("1")) {
        PlOperateLog plOperateLog = new PlOperateLog();// 创建日志对象
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷系统
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO));// 基本信息变更_辅助借款人信息
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(contractid));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
}
