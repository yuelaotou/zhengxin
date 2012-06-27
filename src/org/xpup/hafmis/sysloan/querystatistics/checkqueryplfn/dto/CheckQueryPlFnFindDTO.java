package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto;

public class CheckQueryPlFnFindDTO {
 private String contractid="";//借款人合同编号
 private String borrowername="";//借款人姓名
 private String loankouacc="";//贷款账号
 private String cardnum="";//证件号码
 private String loanstartdateSt="";//贷款发放日期开始
 private String loanstartdateEnd="";//贷款发放日期结束
public String getBorrowername() {
  return borrowername;
}
public void setBorrowername(String borrowername) {
  this.borrowername = borrowername;
}
public String getCardnum() {
  return cardnum;
}
public void setCardnum(String cardnum) {
  this.cardnum = cardnum;
}
public String getContractid() {
  return contractid;
}
public void setContractid(String contractid) {
  this.contractid = contractid;
}
public String getLoankouacc() {
  return loankouacc;
}
public void setLoankouacc(String loankouacc) {
  this.loankouacc = loankouacc;
}
public String getLoanstartdateEnd() {
  return loanstartdateEnd;
}
public void setLoanstartdateEnd(String loanstartdateEnd) {
  this.loanstartdateEnd = loanstartdateEnd;
}
public String getLoanstartdateSt() {
  return loanstartdateSt;
}
public void setLoanstartdateSt(String loanstartdateSt) {
  this.loanstartdateSt = loanstartdateSt;
}
}
