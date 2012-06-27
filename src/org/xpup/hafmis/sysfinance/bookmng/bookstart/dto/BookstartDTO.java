package org.xpup.hafmis.sysfinance.bookmng.bookstart.dto;

import java.io.Serializable;

/**
 * Copy Right Information   : 启用账套
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-20-2007
 */
public class BookstartDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String bookName = "";
  private String bookId = "";
  private String useYearmonth = "";
  private String paramValue = "";
  private String paramExplain = "";
  private String paramExplain1 = "";
  private String paramExplain2 = "";
  private String paramExplain3 = "";
  private String paramExplain4 = "";
  private String paramExplain5 = "";
  private String paramExplain6 = "";
  
  private double debit = 0.0;
  private double credit = 0.0;
  
  public double getCredit() {
    return credit;
  }
  public void setCredit(double credit) {
    this.credit = credit;
  }
  public double getDebit() {
    return debit;
  }
  public void setDebit(double debit) {
    this.debit = debit;
  }
  public String getBookId() {
    return bookId;
  }
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  public String getBookName() {
    return bookName;
  }
  public void setBookName(String bookName) {
    this.bookName = bookName;
  }
  public String getParamExplain() {
    return paramExplain;
  }
  public void setParamExplain(String paramExplain) {
    this.paramExplain = paramExplain;
  }
  public String getParamExplain1() {
    return paramExplain1;
  }
  public void setParamExplain1(String paramExplain1) {
    this.paramExplain1 = paramExplain1;
  }
  public String getParamExplain2() {
    return paramExplain2;
  }
  public void setParamExplain2(String paramExplain2) {
    this.paramExplain2 = paramExplain2;
  }
  public String getParamExplain3() {
    return paramExplain3;
  }
  public void setParamExplain3(String paramExplain3) {
    this.paramExplain3 = paramExplain3;
  }
  public String getParamExplain4() {
    return paramExplain4;
  }
  public void setParamExplain4(String paramExplain4) {
    this.paramExplain4 = paramExplain4;
  }
  public String getParamExplain5() {
    return paramExplain5;
  }
  public void setParamExplain5(String paramExplain5) {
    this.paramExplain5 = paramExplain5;
  }
  public String getParamExplain6() {
    return paramExplain6;
  }
  public void setParamExplain6(String paramExplain6) {
    this.paramExplain6 = paramExplain6;
  }
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
  public String getUseYearmonth() {
    return useYearmonth;
  }
  public void setUseYearmonth(String useYearmonth) {
    this.useYearmonth = useYearmonth;
  }
}
