package org.xpup.hafmis.sysloan.dataready.ratetype.form;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RateTypeNewAF extends ActionForm {
  
  private String rateId;

  private String rateType;
  
  private String rateExplain;
  
  private String rateDate;
  //按钮类型(判断是添加还是修改)
  private String buttonType;
  

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    rateType = "";
    rateExplain = "";
    rateDate = "";
  }


  public String getRateId() {
    return rateId;
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

  public void setRateId(String rateId) {
    this.rateId = rateId;
  }


  public String getButtonType() {
    return buttonType;
  }

  public void setButtonType(String buttonType) {
    this.buttonType = buttonType;
  }

  public String getRateDate() {
    return rateDate;
  }

  public void setRateDate(String rateDate) {
    this.rateDate = rateDate;
  }
  
}
