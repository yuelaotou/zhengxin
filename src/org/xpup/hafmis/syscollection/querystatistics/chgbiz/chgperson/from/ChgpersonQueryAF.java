package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author ั๎นโ 20090506
 */
public class ChgpersonQueryAF extends ActionForm {
  private static final long serialVersionUID = -5165813203583998085L;

  private String office;

  private String bankid;

  private String begDate;

  private String endDate;

  private String type;

  private String orgid;

  private List list = new ArrayList();

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getBankid() {
    return bankid;
  }

  public void setBankid(String bankid) {
    this.bankid = bankid;
  }

  public String getBegDate() {
    return begDate;
  }

  public void setBegDate(String begDate) {
    this.begDate = begDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
}
