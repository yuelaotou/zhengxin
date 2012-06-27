package org.xpup.hafmis.sysloan.common.loanconditionsset;

public class LoanConditionsParamSetDTO {
  private String paramValue=null;  //参数值
  private String paramExplain=null;//参数说明
  private String paramNum="";//参数
  public String getParamExplain() {
    return paramExplain;
  }
  public void setParamExplain(String paramExplain) {
    this.paramExplain = paramExplain;
  }
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
  public String getParamNum() {
    return paramNum;
  }
  public void setParamNum(String paramNum) {
    this.paramNum = paramNum;
  }
  
}
