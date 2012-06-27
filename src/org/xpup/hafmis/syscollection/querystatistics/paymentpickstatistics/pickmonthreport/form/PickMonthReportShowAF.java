package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form;

import java.util.List;

public class PickMonthReportShowAF {
  
  private List list;
  
  private String pickMonth;
  
  private String bizDate;
  
  private String operator;

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getPickMonth() {
    return pickMonth;
  }

  public void setPickMonth(String pickMonth) {
    this.pickMonth = pickMonth;
  }
}
