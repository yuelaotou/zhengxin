/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.dataready.assistantorg.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 09-28-2007
 * 
 * XDoclet definition:
 * @struts.form name="surrogateShowListAF"
 */
public class SurrogateShowListAF extends ActionForm {
  private List list=null;
  /*
   * Generated Methods
   */

  /** 
   * Method validate
   * @param mapping
   * @param request
   * @return ActionErrors
   */
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
}