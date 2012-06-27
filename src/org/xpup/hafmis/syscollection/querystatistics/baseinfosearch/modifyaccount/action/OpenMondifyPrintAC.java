package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.modifyaccount.action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
public class OpenMondifyPrintAC extends Action {
 	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		IdAF idAF = (IdAF) form;
		List list = (List)request.getSession().getAttribute("print");
    request.setAttribute("printUpdateDate", list);
		return new ActionForward("/printUpdateInfo.jsp");
	}
}