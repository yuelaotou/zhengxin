package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.form.AssureborrowerqueryAF;

/** 
 * MyEclipse Struts
 * Creation date: 09-27-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AssureborrowerqueryFindAC extends Action {
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {

      try {
        AssureborrowerqueryAF assureborrowerqueryAF = (AssureborrowerqueryAF) form;
        HashMap criterions = makeCriterionsMap(assureborrowerqueryAF);
        Pagination pagination = new Pagination(0, 10, 1, "t.id", "DESC",
            criterions);
        String paginationKey = AssureborrowerqueryShowAC.PAGINATION_KEY;
        request.getSession().setAttribute(paginationKey, pagination);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      return mapping.findForward("assureborrower_show");
    }

    protected HashMap makeCriterionsMap(AssureborrowerqueryAF assureborrowerqueryAF) {
      HashMap map = new HashMap();
      String empId = assureborrowerqueryAF.getEmpId();
      if (!(empId == null || "".equals(empId.trim()))) {
        map.put("empId", empId.trim());
      }
      String empName = assureborrowerqueryAF.getEmpName();
      if (!(empName == null || "".equals(empName.trim()))) {
        map.put("empName", empName.trim());
      }
      String orgId = assureborrowerqueryAF.getOrgId();
      if (!(orgId == null || "".equals(orgId.trim()))) {
        map.put("orgId", orgId.trim());
      }
      String orgName = assureborrowerqueryAF.getOrgName();
      if (!(orgName == null || "".equals(orgName.trim()))) {
        map.put("orgName", orgName.trim());
      }
      String loanKouAcc = assureborrowerqueryAF.getLoanKouAcc();
      if (!(loanKouAcc == null || "".equals(loanKouAcc.trim()))) {
        map.put("loanKouAcc", loanKouAcc.trim());
      }
      String contractId = assureborrowerqueryAF.getContract();
      if (!(contractId == null || "".equals(contractId.trim()))) {
        map.put("contractId", contractId.trim());
      }
      return map;
    }
}