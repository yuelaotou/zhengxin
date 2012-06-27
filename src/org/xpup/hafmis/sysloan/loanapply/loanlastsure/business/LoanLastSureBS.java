/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanVIPCheckBS
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   : 2007-09-27
 **/
package org.xpup.hafmis.sysloan.loanapply.loanlastsure.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
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
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.bsinterface.ILoanLastSureBS;
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.form.LoanLastSureShowAF;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.dto.LoanVIPCheckDTO;

public class LoanLastSureBS implements ILoanLastSureBS {

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
  public LoanLastSureShowAF queryBorrowerListByCriterions(
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
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
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

    LoanLastSureShowAF loanLastSureShowAF = new LoanLastSureShowAF();
    BigDecimal loanTotleMoney = new BigDecimal(0.00);// 借款金额-总额
    BigDecimal totlePrice = new BigDecimal(0.00);// 房价
    BigDecimal totlePriceAll = new BigDecimal(0.00);// 合计房价
    BigDecimal houseArea = new BigDecimal(0.00);// 建筑面积
    BigDecimal houseAreaAll = new BigDecimal(0.00);// 合计建筑面积
    List loanvipcheckList = new ArrayList();
    List loanvipcheckListAll = new ArrayList();
    List templist = new ArrayList();
    try {
      loanvipcheckList = borrowerDAO.queryBorrowerListByCriterions_wsh(
          contractId, borrowerName, cardNum, officeCode, loanBankName, orgName, houseType,
          beginBizDate, endBizDate,begindate, enddate, contractStFind, findType, start, orderBy,
          order, pageSize, page, securityInfo);// 参数比审核少个合同签订状态
      loanvipcheckListAll = borrowerDAO.queryBorrowerListByCriterions_wsh_yg(
          contractId, borrowerName, cardNum, officeCode, loanBankName, orgName, houseType,
          beginBizDate, endBizDate,begindate, enddate, contractStFind, findType, start, orderBy,
          order, pageSize, page, securityInfo);// 参数比审核少个合同签订状态
      count = borrowerDAO.queryBorrowerCountByCriterions_wsh(contractId,
          borrowerName, cardNum, officeCode, loanBankName, orgName, houseType,
          beginBizDate, endBizDate,begindate, enddate, contractStFind, findType, securityInfo);
      loanTotleMoney = borrowerAccDAO.queryBorrowerListByCriterions_wsh(
          contractId, borrowerName, cardNum, officeCode, loanBankName, orgName, houseType,
          beginBizDate, endBizDate,begindate, enddate, contractStFind, findType, securityInfo);
      Iterator iterate = loanvipcheckList.iterator();
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
          loanVIPCheckDTO.setLoanMoney_(new BigDecimal(obj[3].toString())
              .divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN));
        }
        if (obj[4] != null && !obj[4].equals(""))
          loanVIPCheckDTO.setLoanTimeLimit(Integer.parseInt(obj[4].toString())
              / 12 + "");
        if (obj[6] != null && !obj[6].equals(""))
          loanVIPCheckDTO.setOrgName(obj[6].toString());
        if (obj[7] != null && !obj[7].equals("")) {
          loanVIPCheckDTO.setHouseArea(obj[7].toString());
        }
        if (obj[8] != null && !obj[8].equals(""))
          houseType = obj[8].toString();
        String contractSt = null;
        if (obj[9] != null && !obj[9].equals(""))
          contractSt = obj[9].toString();
        if (obj[10] != null && !obj[10].equals("")) {
          loanVIPCheckDTO.setHousePrice(new BigDecimal(obj[10].toString()));
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
        loanVIPCheckDTO.setHouseType(BusiTools.getBusiValue_WL(houseType,
            BusiConst.PLHOUSETYPE));
        // 枚举转换合同状态
        loanVIPCheckDTO.setContractSt(BusiTools.getBusiValue(Integer
            .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
        if (obj[15] != null)
          loanVIPCheckDTO.setCheckpsn(securityDAO.queryByUserid(obj[15]
              .toString()));
        if (obj[16] != null)
          loanVIPCheckDTO.setVipcheckpsn(securityDAO.queryByUserid(obj[16]
              .toString()));
        if (obj[17] != null)
          loanVIPCheckDTO.setLastcheckpsn(securityDAO.queryByUserid(obj[17]
                                                                       .toString()));
        if (obj[18] != null)
          loanVIPCheckDTO.setLastchkDate(obj[18].toString());
        templist.add(loanVIPCheckDTO);
      }
      Iterator it = loanvipcheckListAll.iterator();
      Object[] objs = null;
      while(it.hasNext()){
        objs = (Object[]) it.next();
        if (objs[7] != null && !objs[7].equals("")) {
          houseArea = new BigDecimal(objs[7].toString());
          houseArea = houseArea.divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_UP);
          houseAreaAll = houseAreaAll.add(houseArea);
        }
        if (objs[10] != null && !objs[10].equals("")) {
          totlePrice = new BigDecimal(objs[10].toString());
          totlePrice = totlePrice.divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_UP);
          totlePriceAll = totlePriceAll.add(totlePrice);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    pagination.setNrOfElements(count);
    if (loanTotleMoney != null && !loanTotleMoney.equals("")) {
      loanLastSureShowAF.setLoanTotleMoney(loanTotleMoney.divide(
          new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN));
    }
    loanLastSureShowAF.setTotlePriceAll(totlePriceAll);
    loanLastSureShowAF.setHouseAreaAll(houseAreaAll);
    loanLastSureShowAF.setList(templist);
    loanLastSureShowAF.setCount(new Integer(count));
    return loanLastSureShowAF;
  }

  public void updateContractSTlastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          Borrower borrower = borrowerDAO.queryById(contractId);
          String contractSt = borrowerAcc.getContractSt();
          if (contractSt.equals("18")) {// 再次审批通过
            borrowerAcc.setContractSt("14");
            borrowerAcc.setLastChkPerson(securityInfo.getUserInfo()
                .getUsername());// 插入终审人
            borrower.setLastcheckDate(securityInfo.getUserInfo().getPlbizDate());
          } else {
            // 枚举转换合同状态
            contractSt = BusiTools.getBusiValue(Integer.parseInt(""
                + contractSt), BusiConst.PLCONTRACTSTATUS);
            throw new BusinessException("该记录状态为" + contractSt + "，不能终审通过！");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void updateContractSTdellastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          Borrower borrower = borrowerDAO.queryById(contractId);
          String contractSt = borrowerAcc.getContractSt();
          if (contractSt.equals("14")) {// 审核通过
            borrowerAcc.setContractSt("18");
            borrowerAcc.setLastChkPerson("");
            borrower.setLastcheckDate("");
          } else {
            // 枚举转换合同状态
            contractSt = BusiTools.getBusiValue(Integer.parseInt(""
                + contractSt), BusiConst.PLCONTRACTSTATUS);
            throw new BusinessException("该记录状态为" + contractSt + "，不能撤消终审通过！");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
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
}
