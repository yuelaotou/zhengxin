package org.xpup.hafmis.sysfinance.bookmng.createbook.dto;

import java.io.Serializable;

/**
 * Copy Right Information   : 创建账套
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-13-2007
 * @author Administrator
 *
 */
public class CreateBookDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private String bookName = "";
  private String userYearMonth = "";
  private String opIp = "";
  private String operator = "";
  private String paramValue = "";
  private String paramExplain1 = "";
  private String paramExplain2 = "";
  private String paramExplain3 = "";
  private String paramExplain4 = "";
  private String paramExplain5 = "";
  private String paramExplain6 = "";
  private String office = "";
  
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getBookName() {
    return bookName;
  }
  public void setBookName(String bookName) {
    this.bookName = bookName;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getOpIp() {
    return opIp;
  }
  public void setOpIp(String opIp) {
    this.opIp = opIp;
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
  public String getUserYearMonth() {
    return userYearMonth;
  }
  public void setUserYearMonth(String userYearMonth) {
    this.userYearMonth = userYearMonth;
  }
}
