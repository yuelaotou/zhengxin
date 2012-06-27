/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.syscollection.querystatistics.inspection.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.hafmis.syscollection.querystatistics.inspection.form.InspectionShowAF;

/** 
 * MyEclipse Struts
 * Creation date: 06-24-2008
 * 
 * XDoclet definition:
 * @struts.action path="/inspectionPrintAC" name="inspectionShowAF" scope="request" validate="true"
 */
public class InspectionPrintAC extends LookupDispatchAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		InspectionShowAF inspectionShowAF = (InspectionShowAF) form;// TODO Auto-generated method stub
		request.setAttribute("af", inspectionShowAF);
    return mapping.findForward("inspectionprint.jsp");
	}

  protected Map getKeyMethodMap() {
    Map map=new HashMap();
    map.put("button.print", "print");
    return map;
  }
}