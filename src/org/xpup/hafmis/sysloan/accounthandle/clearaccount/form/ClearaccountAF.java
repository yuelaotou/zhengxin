package org.xpup.hafmis.sysloan.accounthandle.clearaccount.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ClearaccountAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = 8426481028295934393L;

  private List list = new ArrayList(); // 显示列表

  private String docNum = null;// 凭证编号

  private String loanKouAcc = null;// 贷款账号

  private String contractId = null;// 合同编号

  private String borrowerName = null;// 借款人姓名

  private String bizType = null;// 业务类型

  private String makePerson = null;// 制单人

  private Map bizStMap = new HashMap();// 业务状态类型

  private String bizSt = null;// 业务状态

  private String loanBankName = null;// 放款银行

  private List loanBankNameList = new ArrayList();// 放款银行

  private String beginBizDate = null;// 办理日期

  private String endBizDate = null;// 办理日期

  private BigDecimal sumbackmoney = new BigDecimal(0.00); // 回收金额

  private BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 发放金额-总额
  
  private String realbackpeopercount = ""; //回收人数

  private BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// 回收本金-总额

  private BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// 回收利息-总额总额

  private BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// 回收罚息-总额

  private BigDecimal badDebtTotle = new BigDecimal(0.00);// 呆账核销金额-总额

  private BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// 挂账金额-总额

  private BigDecimal bailTotle = new BigDecimal(0.00);// 保证金-总额

  private BigDecimal bailAccrualTotle = new BigDecimal(0.00);// 保证金利息-总额
  
  private BigDecimal realCorpusTotal = new BigDecimal(0.00);//实还本金-总额
  
  private BigDecimal realInterestTotal = new BigDecimal(0.00);//实还利息-总额
  
  private BigDecimal realPunish_interestTotal = new BigDecimal(0.00);//实还罚息-总额
  
  private int realbackpeopercountTotal = 0; //回收人数

  private Map bizTypeMap = new HashMap();// 业务类型
  
  private int affirmbizSt=0;//确认状态个数
  
  private int checkbizSt=0;//复合状态个数
  
  private String printDate = "";
  
  private String checkPerson = "";
  
  private String clearAccPerson = "";

  public int getAffirmbizSt() {
    return affirmbizSt;
  }

  public void setAffirmbizSt(int affirmbizSt) {
    this.affirmbizSt = affirmbizSt;
  }

  public int getCheckbizSt() {
    return checkbizSt;
  }

  public void setCheckbizSt(int checkbizSt) {
    this.checkbizSt = checkbizSt;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public Map getBizStMap() {
    return bizStMap;
  }

  public void setBizStMap(Map bizStMap) {
    this.bizStMap = bizStMap;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public BigDecimal getBadDebtTotle() {
    return badDebtTotle;
  }

  public void setBadDebtTotle(BigDecimal badDebtTotle) {
    this.badDebtTotle = badDebtTotle;
  }

  public BigDecimal getBailAccrualTotle() {
    return bailAccrualTotle;
  }

  public void setBailAccrualTotle(BigDecimal bailAccrualTotle) {
    this.bailAccrualTotle = bailAccrualTotle;
  }

  public BigDecimal getBailTotle() {
    return bailTotle;
  }

  public void setBailTotle(BigDecimal bailTotle) {
    this.bailTotle = bailTotle;
  }

  public BigDecimal getOccurMoneyTotle() {
    return occurMoneyTotle;
  }

  public void setOccurMoneyTotle(BigDecimal occurMoneyTotle) {
    this.occurMoneyTotle = occurMoneyTotle;
  }

  public BigDecimal getPutUpMoneyTotle() {
    return putUpMoneyTotle;
  }

  public void setPutUpMoneyTotle(BigDecimal putUpMoneyTotle) {
    this.putUpMoneyTotle = putUpMoneyTotle;
  }

  public BigDecimal getRealPunishInterestTotle() {
    return realPunishInterestTotle;
  }

  public void setRealPunishInterestTotle(BigDecimal realPunishInterestTotle) {
    this.realPunishInterestTotle = realPunishInterestTotle;
  }

  public BigDecimal getReclaimAccrualTotle() {
    return reclaimAccrualTotle;
  }

  public void setReclaimAccrualTotle(BigDecimal reclaimAccrualTotle) {
    this.reclaimAccrualTotle = reclaimAccrualTotle;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    list = null;// 显示列表

    docNum = null;// 凭证编号

    loanKouAcc = null;// 贷款账号

    contractId = null;// 合同编号

    borrowerName = null;// 借款人姓名

    bizType = null;// 业务类型

    makePerson = null;// 制单人

    bizStMap = new HashMap();// 业务状态类型

    bizSt = null;// 业务状态

    loanBankName = null;// 放款银行

    loanBankNameList = new ArrayList();// 放款银行

    beginBizDate = null;// 办理日期

    endBizDate = null;// 办理日期

    occurMoneyTotle = new BigDecimal(0.00);// 发放金额-总额

    reclaimCorpusTotle = new BigDecimal(0.00);// 回收本金-总额

    reclaimAccrualTotle = new BigDecimal(0.00);// 回收利息-总额总额

    realPunishInterestTotle = new BigDecimal(0.00);// 回收罚息-总额

    badDebtTotle = new BigDecimal(0.00);// 呆账核销金额-总额

    putUpMoneyTotle = new BigDecimal(0.00);// 挂账金额-总额

    bailTotle = new BigDecimal(0.00);// 保证金-总额

    bailAccrualTotle = new BigDecimal(0.00);// 保证金利息-总额

    bizTypeMap = new HashMap();// 业务类型
  }

  public BigDecimal getSumbackmoney() {
    return sumbackmoney;
  }

  public void setSumbackmoney(BigDecimal sumbackmoney) {
    this.sumbackmoney = sumbackmoney;
  }

  public BigDecimal getRealCorpusTotal() {
    return realCorpusTotal;
  }

  public void setRealCorpusTotal(BigDecimal realCorpusTotal) {
    this.realCorpusTotal = realCorpusTotal;
  }

  public BigDecimal getRealInterestTotal() {
    return realInterestTotal;
  }

  public void setRealInterestTotal(BigDecimal realInterestTotal) {
    this.realInterestTotal = realInterestTotal;
  }

  public BigDecimal getRealPunish_interestTotal() {
    return realPunish_interestTotal;
  }

  public void setRealPunish_interestTotal(BigDecimal realPunish_interestTotal) {
    this.realPunish_interestTotal = realPunish_interestTotal;
  }

  public String getCheckPerson() {
    return checkPerson;
  }

  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }

  public String getClearAccPerson() {
    return clearAccPerson;
  }

  public void setClearAccPerson(String clearAccPerson) {
    this.clearAccPerson = clearAccPerson;
  }

  public String getPrintDate() {
    return printDate;
  }

  public void setPrintDate(String printDate) {
    this.printDate = printDate;
  }

  public String getRealbackpeopercount() {
    return realbackpeopercount;
  }

  public void setRealbackpeopercount(String realbackpeopercount) {
    this.realbackpeopercount = realbackpeopercount;
  }

  public int getRealbackpeopercountTotal() {
    return realbackpeopercountTotal;
  }

  public void setRealbackpeopercountTotal(int realbackpeopercountTotal) {
    this.realbackpeopercountTotal = realbackpeopercountTotal;
  }
}
