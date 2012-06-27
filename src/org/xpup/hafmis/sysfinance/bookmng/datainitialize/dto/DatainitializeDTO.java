package org.xpup.hafmis.sysfinance.bookmng.datainitialize.dto;

import java.io.Serializable;

/**
 * Copy Right Information   : 初始数据
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-16-2007
 * @author Administrator
 *
 */
public class DatainitializeDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  private String subjectCode = "";
  private String subjectName ="";
  private String debit = "0";
  private String credit = "0";
  private String yesterdayDebit = "0";
  private String yesterdayCredit = "0";
  private String balaceDirection = "";
  private String bookId = "";
  private String officeName = "";
  private String yesterdayRemainingSum = "0";
  
  public String getYesterdayRemainingSum() {
    return yesterdayRemainingSum;
  }
  public void setYesterdayRemainingSum(String yesterdayRemainingSum) {
    this.yesterdayRemainingSum = yesterdayRemainingSum;
  }
  public String getOfficeName() {
    return officeName;
  }
  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }
  public String getBookId() {
    return bookId;
  }
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  public String getBalaceDirection() {
    return balaceDirection;
  }
  public void setBalaceDirection(String balaceDirection) {
    this.balaceDirection = balaceDirection;
  }
  public String getCredit() {
    return credit;
  }
  public void setCredit(String credit) {
    this.credit = credit;
  }
  public String getDebit() {
    return debit;
  }
  public void setDebit(String debit) {
    this.debit = debit;
  }
  public String getSubjectCode() {
    return subjectCode;
  }
  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }
  public String getSubjectName() {
    return subjectName;
  }
  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }
  public String getYesterdayCredit() {
    return yesterdayCredit;
  }
  public void setYesterdayCredit(String yesterdayCredit) {
    this.yesterdayCredit = yesterdayCredit;
  }
  public String getYesterdayDebit() {
    return yesterdayDebit;
  }
  public void setYesterdayDebit(String yesterdayDebit) {
    this.yesterdayDebit = yesterdayDebit;
  }

}
