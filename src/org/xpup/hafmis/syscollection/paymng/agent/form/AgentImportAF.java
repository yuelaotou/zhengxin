package org.xpup.hafmis.syscollection.paymng.agent.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
/**
 * Copy Right Information : ´ú¿Ûµ¼ÈëActionForm Goldsoft Project :
 * AgentImportAF
 * 
 * @Version : v1.0
 * @author : ¸¶ÔÆ·å
 * @Date : 2007.12.17
 */
public class AgentImportAF extends ActionForm {
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
}
