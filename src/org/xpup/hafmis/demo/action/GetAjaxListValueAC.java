package org.xpup.hafmis.demo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.demo.form.DemoImportAF;

public class GetAjaxListValueAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DemoImportAF demoImportAF=(DemoImportAF)form;
    System.out.println("子节点值"+demoImportAF.getUrl());
    return mapping.findForward("forwardChildXmlAction");
  }

}
