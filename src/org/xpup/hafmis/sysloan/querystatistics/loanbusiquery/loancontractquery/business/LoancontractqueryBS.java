package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.bsinterface.ILoancontractqueryBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.dto.LoanapplyInfoDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.dto.LoanapplyrealInfoDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.form.LoanContractQueryAF;

public class LoancontractqueryBS implements ILoancontractqueryBS {
  private BorrowerDAO borrowerDAO = null;

  private AssistantOrgDAO assistantOrgDAO = null;

  private DevelopProjectDAO developProjectDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public LoanContractQueryAF showLoancontractqueryList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {

    LoanContractQueryAF loanContractQueryAF = new LoanContractQueryAF();

    try {
      // 查询条件
      String contactid = (String) pagination.getQueryCriterions().get(
          "contactid");
      String borrowername = (String) pagination.getQueryCriterions().get(
          "borrowername");
      String empid = (String) pagination.getQueryCriterions().get("empid");
      String cardnum = (String) pagination.getQueryCriterions().get("cardnum");
      String consortname = (String) pagination.getQueryCriterions().get(
          "consortname");
      String consortcardnum = (String) pagination.getQueryCriterions().get(
          "consortcardnum");
      String consortempid = (String) pagination.getQueryCriterions().get(
          "consortempid");
      String housetype = (String) pagination.getQueryCriterions().get(
          "housetype");
      String contact_st = (String) pagination.getQueryCriterions().get(
          "contact_st");
      String office = (String) pagination.getQueryCriterions().get("office");
      String loanbank = (String) pagination.getQueryCriterions()
          .get("loanbank");
      String paymood = (String) pagination.getQueryCriterions().get("paymood");
      String assistantorgid = (String) pagination.getQueryCriterions().get(
          "assistantorgid");
      String headid = (String) pagination.getQueryCriterions().get("headid");
      String houseAreaSt = (String) pagination.getQueryCriterions().get(
          "houseAreaSt");
      String houseAreaEnd = (String) pagination.getQueryCriterions().get(
          "houseAreaEnd");
      String floorName = (String) pagination.getQueryCriterions().get(
          "floorName");
      String agreementDateSt = (String) pagination.getQueryCriterions().get(
          "agreementDateSt");
      String agreementDateEnd = (String) pagination.getQueryCriterions().get(
          "agreementDateEnd");
      String loanTimeLimitSt = (String) pagination.getQueryCriterions().get(
          "loanTimeLimitSt");
      String loanTimeLimitEnd = (String) pagination.getQueryCriterions().get(
          "loanTimeLimitEnd");
      String signAgreementDateStart = (String) pagination.getQueryCriterions()
          .get("signAgreementDateStart");
      String signAgreementDateEnd = (String) pagination.getQueryCriterions()
          .get("signAgreementDateEnd");
      String loanMoneySt = (String) pagination.getQueryCriterions().get(
          "loanMoneySt");
      String loanMoneyEnd = (String) pagination.getQueryCriterions().get(
          "loanMoneyEnd");
      String isSignAgreement = (String) pagination.getQueryCriterions().get(
          "isSignAgreement");
      String loanType = (String) pagination.getQueryCriterions()
          .get("loanType");
      String isRecoverClear = (String) pagination.getQueryCriterions().get(
          "isRecoverClear");
      String recoverClearDateSt = (String) pagination.getQueryCriterions().get(
          "recoverClearDateSt");
      String recoverClearDateEnd = (String) pagination.getQueryCriterions()
          .get("recoverClearDateEnd");
      String url = (String) pagination.getQueryCriterions().get("url");
      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      // 求出列表要显示的信息 ---带分页的list
      List list = new ArrayList();
      if (url.equals("find")) {
        list = borrowerDAO.findLoanapplyInfo(contactid, borrowername, empid,
            cardnum, housetype, contact_st, office, loanbank, paymood,
            assistantorgid, headid, floorName, houseAreaSt, houseAreaEnd,
            agreementDateSt, agreementDateEnd, loanMoneySt, loanMoneyEnd,
            loanTimeLimitSt, loanTimeLimitEnd, isSignAgreement,
            signAgreementDateStart, signAgreementDateEnd, consortname,
            consortcardnum, consortempid, orderBy, orderother, start, pageSize,
            page, securityInfo, loanType, isRecoverClear, recoverClearDateSt,
            recoverClearDateEnd);
      }
      List newlist = new ArrayList();
      for (int i = 0; i < list.size(); i++) {
        LoanapplyInfoDTO ldto = new LoanapplyInfoDTO();
        ldto = (LoanapplyInfoDTO) list.get(i);
        String housestype = ldto.getHuosetype();

        LoanapplyrealInfoDTO loanapplyrealInfoDTO = new LoanapplyrealInfoDTO();
        if (housestype.equals("01")) {// 商品房
          loanapplyrealInfoDTO.setHousetolprice(ldto.getShousetolprice());
          loanapplyrealInfoDTO.setHousearea(ldto.getShousearea());
        } else {// 二手房
          loanapplyrealInfoDTO.setHousetolprice(ldto.getEhousetolprice());
          loanapplyrealInfoDTO.setHousearea(ldto.getEhousearea());
        }
        loanapplyrealInfoDTO.setContactid(ldto.getContactid());
        loanapplyrealInfoDTO.setBorrowername(ldto.getBorrowername());
        loanapplyrealInfoDTO.setCardnum(ldto.getCardnum());
        loanapplyrealInfoDTO.setHuosetype(BusiTools.getBusiValue_WL(ldto
            .getHuosetype(), BusiConst.PLHOUSETYPE));
        loanapplyrealInfoDTO.setLoanmoney(ldto.getLoanmoney());
        loanapplyrealInfoDTO.setLoanBalance(ldto.getLoanBalance());
        loanapplyrealInfoDTO.setLoanlimit(ldto.getLoanlimit());
        // 转换银行
        loanapplyrealInfoDTO.setLoanBank(collBankDAO.getCollBankByCollBankid(
            ldto.getLoanBank()).getCollBankName());
        loanapplyrealInfoDTO.setContractSt(BusiTools.getBusiValue(Integer
            .parseInt(ldto.getContractSt()), BusiConst.PLCONTRACTSTATUS));
        loanapplyrealInfoDTO.setAgreementDate(ldto.getAgreementDate());
        loanapplyrealInfoDTO.setIsSignAgreement(Integer.parseInt(ldto
            .getIsSignAgreement()) > 0 ? "是" : "否");
        BigDecimal monthrate = new BigDecimal(ldto.getMonthrate());
        loanapplyrealInfoDTO.setMonthrate(monthrate
            .multiply(new BigDecimal(100))
            + "%");
        loanapplyrealInfoDTO.setPaymood(BusiTools.getBusiValue(Integer
            .parseInt(ldto.getPaymood()), BusiConst.PLRECOVERTYPE));
        loanapplyrealInfoDTO.setClearDate(ldto.getClearDate());
        newlist.add(loanapplyrealInfoDTO);
      }

      Vector alllist = null;
      alllist = new Vector();
      if (url.equals("find")) {
        alllist = borrowerDAO.findAllLoanapplyInfo(contactid, borrowername,
            empid, cardnum, housetype, contact_st, office, loanbank, paymood,
            assistantorgid, headid, floorName, houseAreaSt, houseAreaEnd,
            agreementDateSt, agreementDateEnd, loanMoneySt, loanMoneyEnd,
            loanTimeLimitSt, loanTimeLimitEnd, isSignAgreement,
            signAgreementDateStart, signAgreementDateEnd, securityInfo,
            consortname, consortcardnum, consortempid, loanType,
            isRecoverClear, recoverClearDateSt, recoverClearDateEnd);
      }
      pagination.setNrOfElements(alllist.size());

      List newalllist = new ArrayList();
      for (int i = 0; i < alllist.size(); i++) {
        LoanapplyInfoDTO ldto = new LoanapplyInfoDTO();
        ldto = (LoanapplyInfoDTO) alllist.get(i);
        String housestype = ldto.getHuosetype();

        LoanapplyrealInfoDTO loanapplyrealInfoDTO = new LoanapplyrealInfoDTO();
        if (housestype.equals("01")) {// 商品房
          loanapplyrealInfoDTO.setHousetolprice(ldto.getShousetolprice());
          loanapplyrealInfoDTO.setHousearea(ldto.getShousearea());
        } else {// 二手房
          loanapplyrealInfoDTO.setHousetolprice(ldto.getEhousetolprice());
          loanapplyrealInfoDTO.setHousearea(ldto.getEhousearea());
        }
        loanapplyrealInfoDTO.setContactid(ldto.getContactid());
        loanapplyrealInfoDTO.setBorrowername(ldto.getBorrowername());
        loanapplyrealInfoDTO.setCardnum(ldto.getCardnum());
        loanapplyrealInfoDTO.setHuosetype(BusiTools.getBusiValue_WL(ldto
            .getHuosetype(), BusiConst.PLHOUSETYPE));
        loanapplyrealInfoDTO.setLoanmoney(ldto.getLoanmoney());
        loanapplyrealInfoDTO.setLoanBalance(ldto.getLoanBalance());// 贷款余额
        loanapplyrealInfoDTO.setLoanlimit(ldto.getLoanlimit());
        BigDecimal monthrate = new BigDecimal(ldto.getMonthrate());
        loanapplyrealInfoDTO.setContractSt(BusiTools.getBusiValue(Integer
            .parseInt(ldto.getContractSt()), BusiConst.PLCONTRACTSTATUS));
        loanapplyrealInfoDTO.setAgreementDate(ldto.getAgreementDate());
        loanapplyrealInfoDTO.setIsSignAgreement(Integer.parseInt(ldto
            .getIsSignAgreement()) > 0 ? "是" : "否");
        loanapplyrealInfoDTO.setMonthrate(monthrate
            .multiply(new BigDecimal(100))
            + "%");
        loanapplyrealInfoDTO.setPaymood(BusiTools.getBusiValue(Integer
            .parseInt(ldto.getPaymood()), BusiConst.PLRECOVERTYPE));
        newalllist.add(loanapplyrealInfoDTO);
      }
      BigDecimal temp_housetolprice = new BigDecimal("0.00");
      BigDecimal temp_housearea = new BigDecimal("0.00");
      BigDecimal temp_loanmoney = new BigDecimal("0.00");
      BigDecimal temp_loanBalance = new BigDecimal("0.00");
      for (int i = 0; i < newalllist.size(); i++) {
        LoanapplyrealInfoDTO loanapplyrealInfoDTO = (LoanapplyrealInfoDTO) newalllist
            .get(i);
        String housetolprice = loanapplyrealInfoDTO.getHousetolprice();
        String housearea = loanapplyrealInfoDTO.getHousearea();
        String loanmoney = loanapplyrealInfoDTO.getLoanmoney();
        temp_housetolprice = temp_housetolprice.add(new BigDecimal(
            housetolprice));
        temp_housearea = temp_housearea.add(new BigDecimal(housearea));
        temp_loanmoney = temp_loanmoney.add(new BigDecimal(loanmoney));
        temp_loanBalance = temp_loanBalance.add(new BigDecimal(
            loanapplyrealInfoDTO.getLoanBalance()));
      }
      if (newalllist.size() != 0) {
        if (contactid == null && borrowername == null && empid == null
            && cardnum == null && housetype == null && contact_st == null
            && paymood == null && assistantorgid == null && headid == null
            && houseAreaEnd == null && floorName == null && houseAreaSt == null
            && agreementDateSt == null && agreementDateEnd == null
            && loanMoneySt == null && loanMoneyEnd == null
            && loanTimeLimitSt == null && loanTimeLimitEnd == null
            && isSignAgreement == null) {
          if (loanbank != null && !loanbank.equals("")) {
            BigDecimal val = assistantOrgDAO
                .queryPl500CorpusConteract_(loanbank);
            temp_loanBalance = temp_loanBalance.add(val);
          } else if (office != null && !office.equals("")) {
            BigDecimal val = assistantOrgDAO
                .queryPl500CorpusConteract_office(office);
            temp_loanBalance = temp_loanBalance.add(val);
          }
        }
      }
      // 求出担保单位
      List assistantOrglist = new ArrayList();
      assistantOrglist = assistantOrgDAO.queryAssistantOrgList();
      loanContractQueryAF.setCount(new Integer(alllist.size()).toString());
      loanContractQueryAF.setHousetolprice(temp_housetolprice.toString());
      loanContractQueryAF.setHousearea(temp_housearea.toString());
      loanContractQueryAF.setLoanmoney(temp_loanmoney.toString());
      loanContractQueryAF.setLoanBalance(temp_loanBalance.toString());
      loanContractQueryAF.setAssistantorgList(assistantOrglist);
      loanContractQueryAF.setList(newlist);
      loanContractQueryAF.setListall(newalllist);
      alllist = null;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanContractQueryAF;
  }

  public List findFloorlist(String buyhouseorgid) throws Exception {
    List list = new ArrayList();
    try {
      list = developProjectDAO.findFloorNameList(buyhouseorgid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return list;
  }

}
