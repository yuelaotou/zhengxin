package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonMaintainListAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninStayAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;

public class FindTraninStayMaintainListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    TraninStayAF traninStayAF = (TraninStayAF) form;    
    HashMap criterions = makeCriterionsMap(traninStayAF);
    String inOrgId=request.getParameter("inOrgId").trim();
    String inOrgName=request.getParameter("inOrgName").trim();
    String docNum=request.getParameter("docNum");
    String noteNum=request.getParameter("noteNum");
    String outOrgId=request.getParameter("outOrgId").trim();
    String outOrgName=request.getParameter("outOrgName").trim(); 
    if (!(inOrgId == null || "".equals(inOrgId))) {
      criterions.put("inOrgId", inOrgId);
    }
    if (!(inOrgName == null || inOrgName.length() == 0))
      criterions.put("inOrgName", inOrgName); 
    if (!(docNum == null || docNum.length() == 0))
      criterions.put("docNum", docNum);
    
    if (!(noteNum == null || noteNum.length() == 0))
      criterions.put("noteNum", noteNum);
    if (!(outOrgId == null || outOrgId.length() == 0))
      criterions.put("outOrgId", outOrgId);
    
    if (!(outOrgName == null || outOrgName.length() == 0))
      criterions.put("outOrgName", outOrgName);
    
    Pagination pagination = new Pagination(0, 10, 1,
        "id", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    traninStayAF.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_traninStayMaintain_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninStayListAC";
  }

  protected HashMap makeCriterionsMap(TraninStayAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



