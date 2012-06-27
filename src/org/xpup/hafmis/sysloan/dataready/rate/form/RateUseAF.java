package org.xpup.hafmis.sysloan.dataready.rate.form;

import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RateUseAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = 6316402487524117893L;
  private String rateId;
  private String appMode;
  public void reset(ActionMapping mapping, ServletRequest request) {
    rateId="";
    appMode="";
  }
  public String getAppMode() {
    return appMode;
  }
  public void setAppMode(String appMode) {
    this.appMode = appMode;
  }
  public String getRateId() {
    return rateId;
  }
  public void setRateId(String rateId) {
    this.rateId = rateId;
  }
}
