package org.xpup.hafmis.syscollection.peoplebank.documents.form;

import org.apache.struts.action.ActionForm;
/**
 * 
 * @author ЭѕСт
 *
 */
public class DocumentsAF extends ActionForm {   
  private String date="";

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
