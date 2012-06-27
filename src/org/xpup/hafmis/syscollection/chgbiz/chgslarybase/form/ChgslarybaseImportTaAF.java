
package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/** 
 @author ¿Ó≈Ù
 2007-6-20
 */
public class ChgslarybaseImportTaAF extends ActionForm {
  /*
   * Generated Methods
   */

  /** 
   * Method validate
   * @param mapping
   * @param request
   * @return ActionErrors
   */
  private FormFile theFile = null;
  private String url="";
  private String orgId;
  private String chgMonth;
  
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public FormFile getTheFile() {
    return theFile;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
  public ActionErrors validate(ActionMapping mapping,
      HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  /** 
   * Method reset
   * @param mapping
   * @param request
   */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
  }

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }


}