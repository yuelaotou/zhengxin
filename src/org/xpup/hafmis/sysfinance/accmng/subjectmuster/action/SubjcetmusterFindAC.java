/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysfinance.accmng.subjectmuster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysfinance.accmng.subjectmuster.form.SubjectmusterAF;

/** 
 * MyEclipse Struts
 * Creation date: 11-06-2007
 * Copy Right Information : 凭证汇总
 * Project : 文件名
 * @Version : 1.0
 * @author : 张列 生成日期 : 11-06-2007
 * XDoclet definition:
 * @struts.action path="/subjcetmusterFindAC" name="subjectmusterAF" scope="request" validate="true"
 */
public class SubjcetmusterFindAC extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SubjectmusterAF subjectmusterAF = (SubjectmusterAF) form;// TODO Auto-generated method stub
    try {
      request.setAttribute("subjectmusterAF", subjectmusterAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
		return mapping.findForward("subjectmusterShowAC");
	}
}