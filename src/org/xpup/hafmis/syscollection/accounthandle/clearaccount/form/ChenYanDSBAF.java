package org.xpup.hafmis.syscollection.accounthandle.clearaccount.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class ChenYanDSBAF extends ActionForm {

  private static final long serialVersionUID = 1L;

  private String yearmonth = "";

  private String bank1 = "";

  private List list = new ArrayList();

  public String getBank1() {
    return bank1;
  }

  public void setBank1(String bank1) {
    this.bank1 = bank1;
  }

  public String getYearmonth() {
    return yearmonth;
  }

  public void setYearmonth(String yearmonth) {
    this.yearmonth = yearmonth;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

}
