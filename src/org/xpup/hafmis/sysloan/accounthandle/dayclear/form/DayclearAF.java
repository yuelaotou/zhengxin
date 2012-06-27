package org.xpup.hafmis.sysloan.accounthandle.dayclear.form;

import org.apache.struts.action.ActionForm;

public class DayclearAF extends ActionForm{

  private static final long serialVersionUID = -1878630876408126612L;
  private String date="";

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
  
}
