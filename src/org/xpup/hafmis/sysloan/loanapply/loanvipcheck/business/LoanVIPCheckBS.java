package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.business;

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
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface.ILoanVIPCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.dto.LoanVIPCheckDTO;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;

public class LoanVIPCheckBS implements ILoanVIPCheckBS {

  private BorrowerDAO borrowerDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private HousesDAO housesDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private CollBankDAO collBankDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  /**
   * Description 审批贷款
   * 
   * @author wangy 2007-09-27
   * @param 审批贷款信息列表
   * @param 由LoanVIPCheckShowAC调用
   * @return LoanVIPCheckShowAF
   * @throws Exception, BusinessException
   */
  public LoanVIPCheckShowAF queryBorrowerListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    if (officeCode != null && !"".equals(officeCode)) {
      OrganizationUnit organizationUnit = organizationUnitDAO
          .queryOrganizationUnitListByCriterions(officeCode);
      pagination.getQueryCriterions().put("office", organizationUnit.getName());
    }
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    if (loanBankName != null && !"".equals(loanBankName)) {
      String bankName = collBankDAO.getCollBankByCollBankid(loanBankName)
          .getCollBankName();
      pagination.getQueryCriterions().put("bankName", bankName);
    }
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String houseType = (String) pagination.getQueryCriterions()
        .get("houseType");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String begindate = (String) pagination.getQueryCriterions().get(
    "begindate");
    String enddate = (String) pagination.getQueryCriterions().get(
    "enddate");
    String contractStFind = (String) pagination.getQueryCriterions().get(
        "contractStFind");
    String findType = (String) pagination.getQueryCriterions().get("findType");

    LoanVIPCheckShowAF loanVIPCheckShowAF = new LoanVIPCheckShowAF();
    List list = new ArrayList();
    List templist = new ArrayList();
    List allList = borrowerDAO.queryBorrowerListByCriterions_yk(contractId,
        borrowerName, cardNum, officeCode, loanBankName, orgName, houseType,
        beginBizDate, endBizDate,begindate, enddate, contractStFind, findType, 0, orderBy, order,
        0, page, securityInfo);
    if (!allList.isEmpty())
      count = allList.size();
    Iterator iterate = allList.iterator();
    // 合计
    BigDecimal totalHousePrice = new BigDecimal(0.00);
    BigDecimal totalArea = new BigDecimal(0.00);
    BigDecimal totalLoanMoney = new BigDecimal(0.00);
    Object[] obj = null;
    while (iterate.hasNext()) {
      LoanVIPCheckDTO loanVIPCheckDTO = new LoanVIPCheckDTO();
      obj = (Object[]) iterate.next();
      if (obj[0] != null && !obj[0].equals(""))
        loanVIPCheckDTO.setContractId(obj[0].toString());
      if (obj[1] != null && !obj[1].equals(""))
        loanVIPCheckDTO.setBorrowerName(obj[1].toString());
      if (obj[2] != null && !obj[2].equals(""))
        loanVIPCheckDTO.setCardNum(obj[2].toString());
      if (obj[3] != null && !obj[3].equals("")) {
        loanVIPCheckDTO.setLoanMoney(Float.parseFloat(obj[3].toString())
            / 10000 + "");
        totalLoanMoney = totalLoanMoney.add(new BigDecimal(obj[3].toString())
            .divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN));
      }
      if (obj[4] != null && !obj[4].equals(""))
        loanVIPCheckDTO.setLoanTimeLimit(Integer.parseInt(obj[4].toString())
            / 12 + "");
      String loanBankId = "";
      if (obj[5] != null && !obj[5].equals(""))
        loanBankId = obj[5].toString();
      if (obj[6] != null && !obj[6].equals(""))
        loanVIPCheckDTO.setOrgName(obj[6].toString());
      if (obj[7] != null && !obj[7].equals("")) {
        loanVIPCheckDTO.setHouseArea(obj[7].toString());
        totalArea = totalArea.add(new BigDecimal(obj[7].toString()));
      }
      if (obj[8] != null && !obj[8].equals(""))
        houseType = obj[8].toString();
      String contractSt = null;
      if (obj[9] != null && !obj[9].equals(""))
        contractSt = obj[9].toString();
      if (obj[10] != null && !obj[10].equals("")) {
        loanVIPCheckDTO.setTotalPrice(obj[10].toString());
        totalHousePrice = totalHousePrice
            .add(new BigDecimal(obj[10].toString()));
      }
      if (obj[11] != null && !obj[11].equals(""))
        loanVIPCheckDTO.setAssistantborrowerName(obj[11].toString());
      if (obj[12] != null && !obj[12].equals(""))
        loanVIPCheckDTO.setType(obj[12].toString());
      // 转换操作员真实姓名
      if (obj[13] != null)
        loanVIPCheckDTO.setOperator(securityDAO.queryByUserid(obj[13]
            .toString()));
      if (obj[14] != null)
        loanVIPCheckDTO.setHouseAddr(obj[14].toString());
      if (obj[15] != null)
        loanVIPCheckDTO.setMark_a(obj[15].toString().equals("0") ? "否" : "是");
      if (obj[16] != null)
        loanVIPCheckDTO.setLoanvipchkDate(obj[16].toString());
      // 转换银行名称
      if (loanBankId != null && !loanBankId.equals("")) {
        try {
          CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
              .toString());
          if (dto != null)
            loanVIPCheckDTO.setLoanBankName(dto.getCollBankName());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      // 枚举转换购房类型
      try {
        loanVIPCheckDTO.setHouseType(BusiTools.getBusiValue_WL(houseType,
            BusiConst.PLHOUSETYPE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 枚举转换合同状态
      try {
        loanVIPCheckDTO.setContractSt(BusiTools.getBusiValue(Integer
            .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
      } catch (Exception e) {
        e.printStackTrace();
      }
      templist.add(loanVIPCheckDTO);
    }
    if (start + pageSize > templist.size()) {
      list = templist.subList(start, templist.size());
    } else {
      list = templist.subList(start, start + pageSize);
    }
    loanVIPCheckShowAF.setCount(Integer.valueOf(count + ""));
    loanVIPCheckShowAF.setLoanTotleMoney(totalLoanMoney);
    loanVIPCheckShowAF.setTotalArea(totalArea.toString());
    loanVIPCheckShowAF.setTotalHousePrice(totalHousePrice);
    pagination.setNrOfElements(count);
    loanVIPCheckShowAF.setAllList(templist);
    loanVIPCheckShowAF.setList(list);
    return loanVIPCheckShowAF;
  }

  /**
   * Description 审批贷款
   * 
   * @author wangy 2007-09-27
   * @param 审批通过
   * @param 由LoanVIPCheckReasonAC调用
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTApprovalPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId = null;
    for (int i = 0; i < rowArray.length; i++) {
      contractId = rowArray[i];
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      Borrower borrower = borrowerDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals("3")) {// 审核通过
        borrower.setVipChkPerson(securityInfo.getUserInfo().getUsername());
        borrower.setLoanvipcheckDate(securityInfo.getUserInfo().getPlbizDate());
        borrowerAcc.setContractSt("4");
      } else if (contractSt.equals("8")) { // 再次审批
        borrowerAcc.setContractSt("11");// 还款中
      } else {
        // 枚举转换合同状态
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("该记录状态为" + contractSt + "，不能审批通过！");
      }
      // 插入日志 pl021
      String operateName = securityInfo.getUserInfo().getUsername();// 操作员
      String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_LOANAPPL_LOANAPPROVAL).toString());// 审批贷款 29
      plOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_PPROVALPASS).toString());// 审批通过 18
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    }
  }

  public void updateContractSTlastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId = null;
    for (int i = 0; i < rowArray.length; i++) {
      contractId = rowArray[i];
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals("4") || contractSt.equals("3")) {// 审核通过
        borrowerAcc.setContractSt("14");
      } else {
        // 枚举转换合同状态
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("该记录状态为" + contractSt + "，不能终审通过！");
      }
      // 插入日志 pl021
      String operateName = securityInfo.getUserInfo().getUsername();// 操作员
      String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_LOANAPPL_LOANAPPROVAL).toString());// 终审通过
      plOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_PPROVALPASS).toString());// 终审通过
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    }

  }

  public void updateContractSTdellastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId = null;
    for (int i = 0; i < rowArray.length; i++) {
      contractId = rowArray[i];
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals("14")) {// 审核通过
        borrowerAcc.setContractSt("3");
      } else {
        // 枚举转换合同状态
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("该记录状态为" + contractSt + "，不能撤消终审通过！");
      }
      // 插入日志 pl021
      String operateName = securityInfo.getUserInfo().getUsername();// 操作员
      String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_LOANAPPL_LOANAPPROVAL).toString());// 终审通过
      plOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_PPROVALPASS).toString());// 终审通过
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    }
  }

  public void updateContractSTApprovalNotPass(String contractId,
      String reasonB, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    String contractSt = borrowerAcc.getContractSt();
    if (contractSt.equals("3")) {// 审核通过
      borrowerAcc.setContractSt("6"); // 审批不通过
    } else if (contractSt.equals("17")) {// 审核通过
      borrowerAcc.setContractSt("20"); // 审批不通过
    }
    borrowerAcc.setReasonB(reasonB);
  }

  /**
   * Description 审批贷款
   * 
   * @author wangy 2007-09-27
   * @param 审批不通过
   * @param 由LoanVIPCheckReasonAC调用
   * @param contractId
   * @param reasonB
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTApprovalNotPass(String[] rowArray,
      String reasonB, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String contractId = null;
    for (int i = 0; i < rowArray.length; i++) {
      contractId = rowArray[i];
      
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals("3")) {
        borrowerAcc.setContractSt("6");
      } else if (contractSt.equals("17")) {
        borrowerAcc.setContractSt("20");
      }
      borrowerAcc.setReasonB(reasonB);
    }
  }

  /**
   * Description 审批贷款
   * 
   * @author wangy 2007-09-27
   * @param 撤销审批
   * @param 由LoanVIPCheckMaintainAC调用
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTApprovalQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId = null;
    for (int i = 0; i < rowArray.length; i++) {
      contractId = rowArray[i];
      Borrower borrower = borrowerDAO.queryById(contractId);
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      if (borrowerAcc.getContractSt().equals("4")) {
        borrowerAcc.setContractSt("3");
        borrower.setLoanvipcheckDate(null);
      } else {
        // 提示状态 不能审批
        throw new BusinessException("状态不对不能撤消审批！");
      }
    }
  }

  /**
   * Description 审批贷款
   * 
   * @author wangy 2007-09-27
   * @param 转换office
   * @param 由LoanVIPCheckTaShowAC调用
   * @param office
   * @return String
   * @throws Exception
   */
  public String changeOffice(String office) throws Exception {
    OrganizationUnit organizationUnit = new OrganizationUnit();
    organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(office);
    office = organizationUnit.getName();
    return office;
  }

  public void updateContractStApprovalPassAgain(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId = null;
    for (int i = 0; i < rowArray.length; i++) {
      contractId = rowArray[i];
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      if (borrowerAcc.getContractSt().equals("17")) {// 审批通过
        borrowerAcc.setContractSt("18"); // 审核通过
        borrowerAcc.setVipChkAgainPerson(securityInfo.getUserInfo()
            .getUsername());// 插入再次审批人
      } else {
        // 提示状态 不能审批
        throw new BusinessException("状态不对不能再次审批！");
      }
      // 插入日志 pl021
      String operateName = securityInfo.getUserInfo().getUsername();// 操作员
      String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_LOANAPPL_LOANAPPROVAL).toString());// 审批贷款 29
      plOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_PPROVALQUASH).toString());// 撤销审批 20
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    }
  }

  public void updateContractStApprovalQuashAgain(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId = null;
    for (int i = 0; i < rowArray.length; i++) {
      contractId = rowArray[i];
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      if (borrowerAcc.getContractSt().equals("18")) {// 审批通过
        borrowerAcc.setContractSt("17"); // 审核通过
      } else {
        // 提示状态 不能审批
        throw new BusinessException("状态不对不能撤销再次审批！");
      }
      // 插入日志 pl021
      String operateName = securityInfo.getUserInfo().getUsername();// 操作员
      String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_LOANAPPL_LOANAPPROVAL).toString());// 审批贷款 29
      plOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_PPROVALQUASH).toString());// 撤销审批 20
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    }
  }

}
