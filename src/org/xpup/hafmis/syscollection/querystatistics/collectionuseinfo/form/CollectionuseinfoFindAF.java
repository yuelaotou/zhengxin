package org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.form;

import org.apache.struts.action.ActionForm;

public class CollectionuseinfoFindAF extends ActionForm{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String officeCode="";
  private String collectionBankId="";
  private String queryTime="";
  public String getCollectionBankId() {
    return collectionBankId;
  }
  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }
  public String getQueryTime() {
    return queryTime;
  }
  public void setQueryTime(String queryTime) {
    this.queryTime = queryTime;
  }
}
