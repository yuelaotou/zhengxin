package org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.bsinterface.ILoanFirstCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.dto.LoanFirstCheckDTO;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.form.LoanFirstCheckShowAF;

public class LoanFirstCheckBS implements ILoanFirstCheckBS {
  BorrowerDAO borrowerDAO = null;

  BorrowerAccDAO borrowerAccDAO = null;

  HousesDAO housesDAO = null;

  LoanBankParaDAO loanBankParaDAO = null;

  CollBankDAO collBankDAO = null;

  PlOperateLogDAO plOperateLogDAO = null;

  OrganizationUnitDAO organizationUnitDAO = null;

  SecurityDAO securityDAO = null;

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public LoanFirstCheckShowAF queryLoanInfoListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanFirstCheckShowAF loanFirstCheckShowAF = new LoanFirstCheckShowAF();
    List list = null;
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String officeCode = (String) pagination.getQueryCriterions().get(
          "officeCode");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String houseType = (String) pagination.getQueryCriterions().get(
          "houseType");
      String opTimeSt = (String) pagination.getQueryCriterions().get(
          "opTimeSt");
      String opTimeEnd = (String) pagination.getQueryCriterions().get(
          "opTimeEnd");
      String contractSt = (String) pagination.getQueryCriterions().get(
          "contractSt");
      list = housesDAO.queryLoanInfoList(contractId, officeCode, borrowerName,
          cardNum, orgName, opTimeSt, opTimeEnd, houseType, contractSt, start,
          orderBy, order, pageSize, page, securityInfo);
      BigDecimal loanMoneyTotal = new BigDecimal(0.00);// 借款金额
      BigDecimal housePriceTotal = new BigDecimal(0.00);// 房价
      BigDecimal houseAreaTotal = new BigDecimal(0.00);// 建筑面积
      for (int i = 0; i < list.size(); i++) {
        LoanFirstCheckDTO dto = (LoanFirstCheckDTO) list.get(i);
        dto.setHouseType(BusiTools.getBusiValue_WL(dto.getHouseType(),
            BusiConst.PLHOUSETYPE));
        dto.setContractSt(BusiTools.getBusiValue(Integer.parseInt(dto
            .getContractSt()), BusiConst.PLCONTRACTSTATUS));
        loanMoneyTotal = loanMoneyTotal.add(new BigDecimal(dto.getLoanMoney()));
        housePriceTotal = housePriceTotal.add(new BigDecimal(dto
            .getHousePrice()));
        houseAreaTotal = houseAreaTotal.add(new BigDecimal(dto.getHouseArea()));
      }
      loanFirstCheckShowAF.setHouseAreaTotal(houseAreaTotal);
      loanFirstCheckShowAF.setHousePriceTotal(housePriceTotal);
      loanFirstCheckShowAF.setLoanMoneyTotal(loanMoneyTotal);
      loanFirstCheckShowAF.setList(list);
      int count = housesDAO.queryLoanInfoListCount(contractId, officeCode,
          borrowerName, cardNum, orgName, opTimeSt, opTimeEnd, houseType,
          contractSt, securityInfo);
      pagination.setNrOfElements(count);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return loanFirstCheckShowAF;
  }

  public void updateContractStFirCheckPass(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "")) {// 提交审核
        borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "");
      } else {
        // 枚举转换合同状态
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("该记录状态为" + contractSt + "，不能初审通过！");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void updateContractStFirCheckPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId;
    try {
      for (int i = 0; i < rowArray.length; i++) {
        contractId = rowArray[i];
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        String contractSt = borrowerAcc.getContractSt();
        if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "")) {// 提交审核
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "");
        } else {
          // 枚举转换合同状态
          contractSt = BusiTools.getBusiValue(
              Integer.parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS);
          throw new BusinessException("该记录状态为" + contractSt + "，不能初审通过！");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void updateContractStFirCheckQuash(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "")) {// 初审通过
        borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "");
      } else {
        // 枚举转换合同状态
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("该记录状态为" + contractSt + "，不能撤消初审！");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateContractStFirCheckQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId;
    try {
      for (int i = 0; i < rowArray.length; i++) {
        contractId = rowArray[i];
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        String contractSt = borrowerAcc.getContractSt();
        if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "")) {// 初审通过
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK + "");
        } else {
          // 枚举转换合同状态
          contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
              BusiConst.PLCONTRACTSTATUS);
          throw new BusinessException("该记录状态为" + contractSt + "，不能撤消初审！");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

}
