package org.xpup.hafmis.sysfinance.accmng.subjectdayreport.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copy Right Information   : 科目日报表
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-26-2007
 */
public class SubjectdayreportDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String paramValue = "";
  private String bookId = "";
  private String officeName = "";
  private String credenceDate = "";
  
  private String subjectCode = "";
  private String subjectName = "";
  //昨日余额
  private BigDecimal yesterdayRemainingSum = new BigDecimal("0.00");
  private String yesterdayRemainingSumString = "";
  //方向
  private String directionYesterday = "";
  //今日借方
  private BigDecimal todayDebit = new BigDecimal("0.00");
  private String todayDebitString = "";
  //今日贷方
  private BigDecimal todayCredit = new BigDecimal("0.00");
  private String todayCreditString = "";
  //方向
  private String direction = "";
  //今日余额
  private BigDecimal todayRemainingSum = new BigDecimal("0.00");
  private String todayRemainingSumString = "";
  //今日借方笔数
  private int todayDebitSum = 0;
  private String todayDebitSumString = "";
  //今日贷方笔数
  private int todayCreditSum = 0;
  private String todayCreditSumString = "";
  
  public String getBookId() {
    return bookId;
  }
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  public String getCredenceDate() {
    return credenceDate;
  }
  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
  }
  public String getOfficeName() {
    return officeName;
  }
  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
  public String getDirection() {
    return direction;
  }
  public void setDirection(String direction) {
    this.direction = direction;
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
  public int getTodayCreditSum() {
    return todayCreditSum;
  }
  public void setTodayCreditSum(int todayCreditSum) {
    this.todayCreditSum = todayCreditSum;
  }
  public int getTodayDebitSum() {
    return todayDebitSum;
  }
  public void setTodayDebitSum(int todayDebitSum) {
    this.todayDebitSum = todayDebitSum;
  }
  public BigDecimal getTodayCredit() {
    return todayCredit;
  }
  public void setTodayCredit(BigDecimal todayCredit) {
    this.todayCredit = todayCredit;
  }
  public BigDecimal getTodayDebit() {
    return todayDebit;
  }
  public void setTodayDebit(BigDecimal todayDebit) {
    this.todayDebit = todayDebit;
  }
  public BigDecimal getTodayRemainingSum() {
    return todayRemainingSum;
  }
  public void setTodayRemainingSum(BigDecimal todayRemainingSum) {
    this.todayRemainingSum = todayRemainingSum;
  }
  public BigDecimal getYesterdayRemainingSum() {
    return yesterdayRemainingSum;
  }
  public void setYesterdayRemainingSum(BigDecimal yesterdayRemainingSum) {
    this.yesterdayRemainingSum = yesterdayRemainingSum;
  }
  public String getTodayCreditString() {
    return todayCreditString;
  }
  public void setTodayCreditString(String todayCreditString) {
    this.todayCreditString = todayCreditString;
  }
  public String getTodayCreditSumString() {
    return todayCreditSumString;
  }
  public void setTodayCreditSumString(String todayCreditSumString) {
    this.todayCreditSumString = todayCreditSumString;
  }
  public String getTodayDebitString() {
    return todayDebitString;
  }
  public void setTodayDebitString(String todayDebitString) {
    this.todayDebitString = todayDebitString;
  }
  public String getTodayDebitSumString() {
    return todayDebitSumString;
  }
  public void setTodayDebitSumString(String todayDebitSumString) {
    this.todayDebitSumString = todayDebitSumString;
  }
  public String getTodayRemainingSumString() {
    return todayRemainingSumString;
  }
  public void setTodayRemainingSumString(String todayRemainingSumString) {
    this.todayRemainingSumString = todayRemainingSumString;
  }
  public String getYesterdayRemainingSumString() {
    return yesterdayRemainingSumString;
  }
  public void setYesterdayRemainingSumString(String yesterdayRemainingSumString) {
    this.yesterdayRemainingSumString = yesterdayRemainingSumString;
  }
  public String getDirectionYesterday() {
    return directionYesterday;
  }
  public void setDirectionYesterday(String directionYesterday) {
    this.directionYesterday = directionYesterday;
  }
}
