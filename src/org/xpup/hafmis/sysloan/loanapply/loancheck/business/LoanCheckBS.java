/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanCheckBS
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   : 2007-09-22
 **/
package org.xpup.hafmis.sysloan.loanapply.loancheck.business;

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
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loancheck.dto.LoanCheckDTO;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckShowAF;

public class LoanCheckBS implements ILoanCheckBS {

  BorrowerDAO borrowerDAO = null;

  BorrowerAccDAO borrowerAccDAO = null;

  HousesDAO housesDAO = null;

  LoanBankParaDAO loanBankParaDAO = null;

  CollBankDAO collBankDAO = null;

  PlOperateLogDAO plOperateLogDAO = null;

  OrganizationUnitDAO organizationUnitDAO = null;

  SecurityDAO securityDAO = null;

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

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  /**
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 判断银行贷款参数PL003表中，参数值是否等于AB
   * @param 由LoanCheckShowAC调用
   * @return LoanCheckShowAF
   * @throws Exception, BusinessException
   */
  public LoanCheckShowAF queryBorrowerListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    LoanCheckShowAF loanCheckShowAF = new LoanCheckShowAF();
    String isContractWrite = null;
    try {
      String paramValue = loanBankParaDAO.queryParamvalueYU();
      if (paramValue != null) {
        if (paramValue.equalsIgnoreCase("AB")) {
          loanCheckShowAF = this.queryListByCriterions(pagination,
              securityInfo, isContractWrite);
        } else {
          isContractWrite = "1";// 是否签订合同为1(已签订)
          loanCheckShowAF = this.queryListByCriterions(pagination,
              securityInfo, isContractWrite);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanCheckShowAF;
  }

  /**
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 审核贷款信息列表
   * @param 由this.queryBorrowerListByCriterions调用
   * @return LoanCheckShowAF
   * @throws Exception, BusinessException
   */
  public LoanCheckShowAF queryListByCriterions(Pagination pagination,
      SecurityInfo securityInfo, String isContractWrite) throws Exception,
      BusinessException {

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String houseType = (String) pagination.getQueryCriterions()
        .get("houseType");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String beginBackDate = (String) pagination.getQueryCriterions().get(
        "beginBackDate");
    String endBackDate = (String) pagination.getQueryCriterions().get(
        "endBackDate");
    String contractStFind = (String) pagination.getQueryCriterions().get(
        "contractStFind");
    // contractSt是用来判断是哪个节点过来的然后传入哪种默认的合同状态
    String contractStatus = (String) pagination.getQueryCriterions().get(
        "contractSt");
    String findType = (String) pagination.getQueryCriterions().get("findType");
    LoanCheckShowAF loanCheckShowAF = new LoanCheckShowAF();
    if (officeCode != null) {
      loanCheckShowAF.setOfficeCode(this.changeOffice(officeCode));
    } else {
      loanCheckShowAF.setOfficeCode("");
    }
    // 转换办事处用于打印显示
    try {

      List loancheckList = new ArrayList();
      List loancheckListAll = new ArrayList();// 打印的
      List loancheckListSum = new ArrayList();// 年内累计的
      List templist = new ArrayList();
      List templistAll = new ArrayList();
      loancheckList = borrowerDAO.queryBorrowerListByCriterions_wuht(
          contractId, officeCode, borrowerName, cardNum, loanBankName, orgName,
          houseType, beginBizDate, endBizDate, contractStFind, contractStatus,
          isContractWrite, findType, start, orderBy, order, pageSize, page,
          securityInfo, beginBackDate, endBackDate);

      count = borrowerDAO.queryBorrowerCountByCriterions_wsh(contractId,
          officeCode, borrowerName, cardNum, loanBankName, orgName, houseType,
          beginBizDate, endBizDate, contractStFind, contractStatus,
          isContractWrite, findType, securityInfo, beginBackDate, endBackDate);
      pagination.setNrOfElements(count);
      Iterator iterate = loancheckList.iterator();
      Object[] obj = null;
      BigDecimal loanTotleMoney = new BigDecimal(0.00);// 借款金额-总额
      BigDecimal loanMoney = new BigDecimal(0.00);// 借款金额
      BigDecimal totlePrice = new BigDecimal(0.00);// 房价
      BigDecimal totlePriceAll = new BigDecimal(0.00);// 合计房价
      BigDecimal houseArea = new BigDecimal(0.00);// 建筑面积
      BigDecimal houseAreaAll = new BigDecimal(0.00);// 合计建筑面积
      while (iterate.hasNext()) {
        LoanCheckDTO loanCheckDTO = new LoanCheckDTO();
        obj = (Object[]) iterate.next();
        if (obj[0] != null && !obj[0].equals(""))
          loanCheckDTO.setContractId(obj[0].toString());
        if (obj[1] != null && !obj[1].equals(""))
          loanCheckDTO.setBorrowerName(obj[1].toString());
        if (obj[2] != null && !obj[2].equals(""))
          loanCheckDTO.setCardNum(obj[2].toString());
        if (obj[3] != null && !obj[3].equals("")) {
          loanMoney = new BigDecimal(obj[3].toString());
          loanMoney = loanMoney.divide(new BigDecimal(10000), 1,
              BigDecimal.ROUND_HALF_UP);
          loanCheckDTO.setLoanMoney(loanMoney.toString());
        }
        if (obj[4] != null && !obj[4].equals(""))
          loanCheckDTO.setLoanTimeLimit(obj[4].toString());
        String loanBankId = "";
        if (obj[5] != null && !obj[5].equals(""))
          loanBankId = obj[5].toString();
        if (obj[6] != null && !obj[6].equals(""))
          loanCheckDTO.setOrgName(obj[6].toString());
        if (obj[7] != null && !obj[7].equals("")) {
          houseArea = new BigDecimal(obj[7].toString());
          houseArea = houseArea.divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_UP);
          loanCheckDTO.setHouseArea(houseArea.toString());
        }
        String house_type = "";
        if (obj[8] != null && !obj[8].equals(""))
          house_type = obj[8].toString();
        String contractSt = null;
        if (obj[9] != null && !obj[9].equals(""))
          contractSt = obj[9].toString();
        if (obj[11] != null && !obj[11].equals("")) {
          totlePrice = new BigDecimal(obj[11].toString());
          totlePrice = totlePrice.divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_UP);
          totlePriceAll = totlePriceAll.add(totlePrice);
          loanCheckDTO.setTotlePrice(totlePrice.toString());
        }
        if (obj[13] != null && !obj[13].equals(""))
          loanCheckDTO.setHouseAddr(obj[13].toString());

        if (obj[15] != null && !obj[15].equals(""))
          loanCheckDTO.setRemark(obj[15].toString());
        if (obj[16] != null && !obj[16].equals(""))
          loanCheckDTO.setAssistantborrowerName(obj[16].toString());
        if (obj[17] != null && !obj[17].equals("")) {
          loanCheckDTO.setOperator(securityDAO
              .queryByUserid(obj[17].toString()));
        }
        if (obj[18] != null && !obj[18].equals("")) {
          loanCheckDTO.setReDate(obj[18].toString());
        }
        if (obj[19] != null && !obj[19].equals("")) {
          loanCheckDTO.setPhotoUrl(obj[19].toString());
        }
        if (obj[20] != null && !obj[20].equals("")) {
          loanCheckDTO.setIsContract_write(obj[20].toString().equals("1") ? "是"
              : "否");
        }
        if (obj[21] != null && !obj[21].equals("")) {
          loanCheckDTO.setVipcheckDate(obj[21].toString());
        }
        // 转换银行名称
        if (loanBankId != null && !loanBankId.equals("")) {
          try {
            CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
                .toString());
            if (dto != null) {
              if (loanBankId.equals("0")) {
                loanCheckDTO.setLoanBankName("");
              } else {
                loanCheckDTO.setLoanBankName(dto.getCollBankName());
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        // 枚举转换购房类型
        try {
          loanCheckDTO.setHouseType(BusiTools.getBusiValue_WL(house_type,
              BusiConst.PLHOUSETYPE));
        } catch (Exception e) {
          e.printStackTrace();
        }
        // 枚举转换合同状态
        try {
          loanCheckDTO.setContractSt(BusiTools.getBusiValue(Integer.parseInt(""
              + contractSt), BusiConst.PLCONTRACTSTATUS));
        } catch (Exception e) {
          e.printStackTrace();
        }
        templist.add(loanCheckDTO);
      }
      loanCheckShowAF.setList(templist);
      loanCheckShowAF.setCount(new Integer(count));
      totlePriceAll = new BigDecimal(0.00);
      // 1、增加打印：《2008年个人住房，贷款明细表》
      loancheckListAll = borrowerDAO.queryBorrowerListByCriterionsAll_wuht(
          contractId, officeCode, borrowerName, cardNum, loanBankName, orgName,
          houseType, beginBizDate, endBizDate, contractStFind, contractStatus,
          isContractWrite, findType, start, orderBy, order, pageSize, page,
          securityInfo, beginBackDate, endBackDate);
      if (loancheckListAll != null && loancheckListAll.size() > 0) {
        Iterator iteratePrint = loancheckListAll.iterator();
        Object[] objPrint = null;
        BigDecimal loanMoneyPrint = new BigDecimal(0.00);// 借款金额
        BigDecimal totlePricePrint = new BigDecimal(0.00);// 房价
        BigDecimal houseAreaPrint = new BigDecimal(0.00);// 建筑面积
        while (iteratePrint.hasNext()) {
          LoanCheckDTO loanCheckDTO = new LoanCheckDTO();
          objPrint = (Object[]) iteratePrint.next();
          if (objPrint[0] != null && !objPrint[0].equals(""))
            loanCheckDTO.setContractId(objPrint[0].toString());
          if (objPrint[1] != null && !objPrint[1].equals(""))
            loanCheckDTO.setBorrowerName(objPrint[1].toString());
          if (objPrint[2] != null && !objPrint[2].equals(""))
            loanCheckDTO.setCardNum(objPrint[2].toString());
          if (objPrint[3] != null && !objPrint[3].equals("")) {
            loanMoneyPrint = new BigDecimal(objPrint[3].toString());
            loanMoneyPrint = loanMoneyPrint.divide(new BigDecimal(10000), 1,
                BigDecimal.ROUND_HALF_UP);
            loanTotleMoney = loanTotleMoney.add(loanMoneyPrint);
            loanCheckDTO.setLoanMoney(loanMoneyPrint.toString());
          }
          if (objPrint[4] != null && !objPrint[4].equals(""))
            loanCheckDTO.setLoanTimeLimit(objPrint[4].toString());
          String loanBankId = "";
          if (objPrint[5] != null && !objPrint[5].equals(""))
            loanBankId = objPrint[5].toString();
          if (objPrint[6] != null && !objPrint[6].equals(""))
            loanCheckDTO.setOrgName(objPrint[6].toString());
          if (objPrint[7] != null && !objPrint[7].equals("")) {
            houseAreaPrint = new BigDecimal(objPrint[7].toString());
            houseAreaPrint = houseAreaPrint.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            houseAreaAll = houseAreaAll.add(houseAreaPrint);
            loanCheckDTO.setHouseArea(houseAreaPrint.toString());
          }
          if (objPrint[8] != null && !objPrint[8].equals(""))
            houseType = objPrint[8].toString();
          String contractSt = null;
          if (objPrint[9] != null && !objPrint[9].equals(""))
            contractSt = objPrint[9].toString();
          if (objPrint[11] != null && !objPrint[11].equals("")) {
            totlePricePrint = new BigDecimal(objPrint[11].toString());
            totlePricePrint = totlePricePrint.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            totlePriceAll = totlePriceAll.add(totlePricePrint);
            loanCheckDTO.setTotlePrice(totlePricePrint.toString());
          }
          if (objPrint[13] != null && !objPrint[13].equals(""))
            loanCheckDTO.setHouseAddr(objPrint[13].toString());
          // if (objPrint[14] != null && !objPrint[14].equals(""))
          // loanCheckDTO.setHouseAddr(objPrint[14].toString());
          if (objPrint[15] != null && !objPrint[15].equals(""))
            loanCheckDTO.setRemark(objPrint[15].toString());
          if (objPrint[16] != null && !objPrint[16].equals(""))
            loanCheckDTO.setDeveloperName(objPrint[16].toString());
          if (objPrint[17] != null && !objPrint[17].equals("")) {
            loanCheckDTO.setOperator(securityDAO.queryByUserid(objPrint[17]
                .toString()));
          }
          if (objPrint[18] != null && !objPrint[18].equals(""))
            loanCheckDTO.setOffice(objPrint[18].toString());
          // 转换银行名称
          if (loanBankId != null && !loanBankId.equals("")) {
            try {
              CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
                  .toString());
              if (dto != null) {
                if (loanBankId.equals("0")) {
                  loanCheckDTO.setLoanBankName("");
                } else {
                  loanCheckDTO.setLoanBankName(dto.getCollBankName());
                }
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          // 枚举转换购房类型
          try {
            loanCheckDTO.setHouseType(BusiTools.getBusiValue_WL(houseType,
                BusiConst.PLHOUSETYPE));
          } catch (Exception e) {
            e.printStackTrace();
          }
          // 枚举转换合同状态
          try {
            loanCheckDTO.setContractSt(BusiTools.getBusiValue(Integer
                .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
          } catch (Exception e) {
            e.printStackTrace();
          }
          templistAll.add(loanCheckDTO);
        }
      }
      if (loanTotleMoney != null && !loanTotleMoney.equals("")) {
        loanCheckShowAF.setLoanTotleMoney(loanTotleMoney);
      }
      if (totlePriceAll != null && !totlePriceAll.equals("")) {
        loanCheckShowAF.setTotlePriceAll(totlePriceAll);
      }
      if (houseAreaAll != null && !houseAreaAll.equals("")) {
        loanCheckShowAF.setHouseAreaAll(houseAreaAll);
      }

      // 当年累计
      loancheckListSum = borrowerDAO.queryBorrowerListByCriterionsSum_wuht(
          officeCode, start, orderBy, order, pageSize, page, securityInfo);
      if (loancheckListSum != null && loancheckListSum.size() > 0) {
        // 累计户数
        loanCheckShowAF.setCount(new Integer(loancheckListSum.size()));

        Iterator iterateSum = loancheckListSum.iterator();
        Object[] objPrint = null;
        BigDecimal loanMoneySum = new BigDecimal(0.00);
        BigDecimal totlePriceSum = new BigDecimal(0.00);
        BigDecimal houseAreaSum = new BigDecimal(0.00);
        BigDecimal loanMoneyAllSum = new BigDecimal(0.00);
        BigDecimal totlePriceAllSum = new BigDecimal(0.00);
        BigDecimal houseAreaAllSum = new BigDecimal(0.00);
        while (iterateSum.hasNext()) {
          objPrint = (Object[]) iterateSum.next();
          if (objPrint[3] != null && !objPrint[3].equals("")) {
            loanMoneySum = new BigDecimal(objPrint[3].toString());
            loanMoneySum = loanMoneySum.divide(new BigDecimal(10000), 1,
                BigDecimal.ROUND_HALF_UP);
            loanMoneyAllSum = loanMoneyAllSum.add(loanMoneySum);
          }

          if (objPrint[7] != null && !objPrint[7].equals("")) {
            houseAreaSum = new BigDecimal(objPrint[7].toString());
            houseAreaSum = houseAreaSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            houseAreaAllSum = houseAreaAllSum.add(houseAreaSum);

          }

          if (objPrint[10] != null && !objPrint[10].equals("")) {
            houseAreaSum = new BigDecimal(objPrint[10].toString());
            houseAreaSum = houseAreaSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            houseAreaAllSum = houseAreaAllSum.add(houseAreaSum);
          }
          // wuht
          if (objPrint[11] != null && !objPrint[11].equals("")) {
            totlePriceSum = new BigDecimal(objPrint[11].toString());
            totlePriceSum = totlePriceSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            totlePriceAllSum = totlePriceAllSum.add(totlePriceSum);

          }
          if (objPrint[12] != null && !objPrint[12].equals("")) {
            totlePriceSum = new BigDecimal(objPrint[12].toString());
            totlePriceSum = totlePriceSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            totlePriceAllSum = totlePriceAllSum.add(totlePriceSum);
          }
        }
        loanCheckShowAF.setTotlePriceAllYearSum(totlePriceAllSum);
        loanCheckShowAF.setHouseAreaAllYearSum(houseAreaAllSum);
        loanCheckShowAF.setLoanTotleMoneyYearSum(loanMoneyAllSum);

      }
      loanCheckShowAF.setListAll(templistAll);

    } catch (Exception ex) {

      ex.printStackTrace();
    }

    return loanCheckShowAF;
  }

  /**
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 审核通过
   * @param 由LoanCheckMaintainAC调用
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTCheckPass(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    String contractSt = borrowerAcc.getContractSt();
    if (contractSt.equals("15")) {// 提交审核
      borrowerAcc.setContractSt("3");
    } else if (contractSt.equals("7")) { // 再次审核
      borrowerAcc.setContractSt("8");// 再次审批
    } else {
      // 枚举转换合同状态
      contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
          BusiConst.PLCONTRACTSTATUS);
      throw new BusinessException("该记录状态为" + contractSt + "，不能审核通过！");
    }
    // 插入日志 pl021 PlOperateLog PlOperateLogDAO
    String operateName = securityInfo.getUserInfo().getUsername();// 操作员
    String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
    plOperateLog.setOpModel(new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAUDIT)
        .toString());// 审核贷款 28
    plOperateLog.setOpButton(new Integer(
        BusiLogConst.BIZLOG_ACTION_AUDITINGPASS).toString());// 审核通过 15
    plOperateLog.setOpBizId(new BigDecimal(contractId));
    plOperateLog.setOpIp(userIp);
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(operateName);
    plOperateLogDAO.insert(plOperateLog);
  }

  /**
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 审核未通过 update BorrowerAcc
   * @param 由LoanCheckReasonAC调用
   * @param contractId
   * @param reasonA
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTCheckNotPass(String contractId, String reasonA,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    if (borrowerAcc.getContractSt().equals("15")) { // 提交审核
      borrowerAcc.setContractSt("5"); // 审核不通过
    } else if (borrowerAcc.getContractSt().equals("16")) { // 提交审核
      borrowerAcc.setContractSt("19"); // 审核不通过
    }
    borrowerAcc.setReasonA(reasonA);
  }

  /**
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 撤销审核 update BorrowerAcc
   * @param 由LoanCheckMaintainAC调用
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTCheckQuash(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    String contractSt = null;
    if (borrowerAcc.getContractSt().equals("3")) {// 审核通过
      borrowerAcc.setContractSt("15"); // 提交审核
    } else if (borrowerAcc.getContractSt().equals("8")) { // 再次审批
      borrowerAcc.setContractSt("7");// 再次审核
    } else {
      // 提示状态 不能审核
      // 枚举转换合同状态
      try {
        contractSt = BusiTools.getBusiValue(Integer.parseInt(""
            + borrowerAcc.getContractSt()), BusiConst.PLCONTRACTSTATUS);
      } catch (Exception e) {
        e.printStackTrace();
      }
      throw new BusinessException("该记录状态为" + contractSt + "，不能撤销审核！");
    }
    // 插入日志 pl021
    String operateName = securityInfo.getUserInfo().getUsername();// 操作员
    String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
    plOperateLog.setOpModel(new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAUDIT)
        .toString());// 审核贷款 28
    plOperateLog.setOpButton(new Integer(
        BusiLogConst.BIZLOG_ACTION_AUDITINGQUASH).toString());// 撤销审核 17
    plOperateLog.setOpBizId(new BigDecimal(contractId));
    plOperateLog.setOpIp(userIp);
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(operateName);
    plOperateLogDAO.insert(plOperateLog);
  }

  /**
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 转换office
   * @param 由LoanCheckTaShowAC调用
   * @param office
   * @return String
   * @throws Exception
   */
  public String changeOffice(String office) throws Exception {
    OrganizationUnit organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(office);
    office = organizationUnit.getName();
    return office;
  }

  public void updateContractSTCheckPassrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          Borrower borrower = borrowerDAO.queryById(contractId);
          String contractSt = borrowerAcc.getContractSt();
          if (borrowerAcc.getIsContractWrite() != null
              && borrowerAcc.getIsContractWrite().equals("0")) {
            throw new BusinessException("合同号为" + contractId + "未提交，不能审核通过！");
          }
          if (contractSt.equals("15")) {// 提交审核
            borrower.setChkPerson(securityInfo.getUserInfo().getUsername());
            borrowerAcc.setContractSt("3");
          } else if (contractSt.equals("7")) { // 再次审核
            borrowerAcc.setContractSt("8");// 再次审批
          } else {
            // 枚举转换合同状态
            contractSt = BusiTools.getBusiValue(Integer.parseInt(""
                + contractSt), BusiConst.PLCONTRACTSTATUS);
            throw new BusinessException("该记录状态为" + contractSt + "，不能审核通过！");
          }
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void updateContractSTCheckNotPassrowArray(String[] rowArray,
      String reasonA, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    try {
      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          if (borrowerAcc.getContractSt().equals("15")) { // 提交审核
            borrowerAcc.setContractSt("5"); // 审核不通过
          } else if (borrowerAcc.getContractSt().equals("16")
              || borrowerAcc.getContractSt().equals("20")) { // 提交审核
            borrowerAcc.setContractSt("19"); // 审核不通过
          }
          borrowerAcc.setReasonA(reasonA);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void updateContractSTCheckQuashrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {

      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          String contractSt = null;
          if (borrowerAcc.getContractSt().equals("3")) {// 审核通过
            borrowerAcc.setContractSt("15"); // 提交审核
          } else if (borrowerAcc.getContractSt().equals("8")) { // 再次审批
            borrowerAcc.setContractSt("7");// 再次审核
          } else {
            // 提示状态 不能审核
            // 枚举转换合同状态
            try {
              contractSt = BusiTools.getBusiValue(Integer.parseInt(""
                  + borrowerAcc.getContractSt()), BusiConst.PLCONTRACTSTATUS);
            } catch (Exception e) {
              e.printStackTrace();
            }
            throw new BusinessException("该记录状态为" + contractSt + "，不能撤销审核！");
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * 回件确认
   */
  public void updateContractStRedateSure(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_THROUGHAPPROVAL + "")
          || contractSt.equals(BusiConst.PLCONTRACTSTATUS_CHKAGAIN_NOTPASS + "")) {// 提交审核
        Borrower borrower = borrowerDAO.queryById(contractId);
        borrower.setRedate(securityInfo.getUserInfo().getPlbizDate());// 更新回件日期
        borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_REDATESURE + "");
        borrowerAcc.setRedatePerson(securityInfo.getUserInfo().getUsername());// 插入接件人
      } else {
        // 枚举转换合同状态
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("该记录状态为" + contractSt + "，不能回件确认！");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
  /**
   * 撤销回件确认
   */
  public void updateContractStRedateSureDel(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_REDATESURE + "")) {
        Borrower borrower = borrowerDAO.queryById(contractId);
        borrower.setRedate("");// 更新回件日期
        borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_THROUGHAPPROVAL + "");
        borrowerAcc.setRedatePerson("");// 插入接件人
      } else {
        // 枚举转换合同状态
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("该记录状态为" + contractSt + "，不能撤销回件确认！");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * 再次审核通过
   */
  public void updateContractStChkAgainPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId;
    try {
      for (int i = 0; i < rowArray.length; i++) {
        contractId = rowArray[i];
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        String contractSt = borrowerAcc.getContractSt();
        if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_REDATESURE + "")) {// 回件确认
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_CHKAGAIN + "");
          borrowerAcc.setChkAgainPerson(securityInfo.getUserInfo()
              .getUsername());// 插入再次审核人
        } else if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_APPROVALAGAIN_NOTPASS + "")) {// 回件确认
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_CHKAGAIN + "");
          borrowerAcc.setChkAgainPerson(securityInfo.getUserInfo()
              .getUsername());// 插入再次审核人
        } else {
          // 枚举转换合同状态
          contractSt = BusiTools.getBusiValue(
              Integer.parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS);
          throw new BusinessException("该记录状态为" + contractSt + "，不能再次审核通过！");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * 撤消再次审核
   */
  public void updateContractStChkAgainQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId;
    try {
      for (int i = 0; i < rowArray.length; i++) {
        contractId = rowArray[i];
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        String contractSt = borrowerAcc.getContractSt();
        if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_CHKAGAIN + "")) {// 提交审核
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_REDATESURE + "");
        } else {
          // 枚举转换合同状态
          contractSt = BusiTools.getBusiValue(
              Integer.parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS);
          throw new BusinessException("该记录状态为" + contractSt + "，不能撤消再次审核！");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
