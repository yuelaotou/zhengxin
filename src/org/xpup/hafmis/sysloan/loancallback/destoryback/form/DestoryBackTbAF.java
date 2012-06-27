package org.xpup.hafmis.sysloan.loancallback.destoryback.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DestoryBackTbAF extends ActionForm {
  private List list = new ArrayList();//显示列表

  private String docNum = "";// 凭证编号

  private String loanKouAcc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardNum = "";// 证件号码

  private Map bizStMap = new HashMap();// 业务状态类型

  private String bizSt = "";// 业务状态

  private String loanBankName = "";// 放款银行

  private List loanBankNameList = new ArrayList();//放款银行

  private BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// 回收金额-总额

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    list = new ArrayList();// 显示列表

    docNum = "";// 凭证编号

    loanKouAcc = "";// 贷款账号

    contractId = "";// 合同编号

    borrowerName = "";// 借款人姓名

    bizStMap = new HashMap();// 业务状态类型

    bizSt = "";// 业务状态

    loanBankName = "";// 放款银行

    loanBankNameList = new ArrayList();// 放款银行

    cardNum = "";// 证件号码
  }

}
