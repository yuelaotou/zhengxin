/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;

/** 
 * MyEclipse Struts
 * Creation date: 10-03-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoanapplyTdFindContractAAC extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    String contractId=(String)request.getParameter("contractId");
    request.getSession().setAttribute("contractid", contractId);
    request.getSession().setAttribute("contractid", null);
    try {
      loanapplyBS.checkTdContractIdByContractId(contractId);
      request.getSession().setAttribute("contractid", contractId);
      String text = "";
      text = "displaycon('" +""+ "');";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (BusinessException e) {
      String text = "";
      text = "displayerrors('" +e.getLocalizedMessage().toString()+ "');";
      response.getWriter().write(text);
      response.getWriter().close();

    }
		return null;
	}
}