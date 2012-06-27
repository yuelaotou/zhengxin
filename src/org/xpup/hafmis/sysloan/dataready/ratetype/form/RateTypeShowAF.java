package org.xpup.hafmis.sysloan.dataready.ratetype.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class RateTypeShowAF extends ActionForm {
  private String rateType;
  
  private String rateExplain;
  
  private String rateDate;
  
  private List list;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getRateDate() {
    return rateDate;
  }

  public void setRateDate(String rateDate) {
    this.rateDate = rateDate;
  }

  public String getRateExplain() {
    return rateExplain;
  }

  public void setRateExplain(String rateExplain) {
    this.rateExplain = rateExplain;
  }

  public String getRateType() {
    return rateType;
  }

  public void setRateType(String rateType) {
    this.rateType = rateType;
  }

  
  
}
