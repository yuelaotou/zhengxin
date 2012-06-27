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
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;

public class FindTraninVidMaintainListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    TraninVidAF traninVidAF = (TraninVidAF) form;      
    
    HashMap criterions = makeCriterionsMap(traninVidAF);
    String inorgd=request.getParameter("inOrgId");
    String inOrgName=request.getParameter("inOrgName");
    String settDate=request.getParameter("settDate");
    String settDatea=request.getParameter("settDatea");
    String tranStatus=request.getParameter("tranStatus");
    String docNum=request.getParameter("docNum");
    String noteNum=request.getParameter("noteNum");
    String outOrgId=request.getParameter("outOrgId");
    String outOrgName=request.getParameter("outOrgName"); 
    if (!(inorgd == null || "".equals(inorgd))) {
      criterions.put("inOrgId", inorgd);
    }
    if (!(inOrgName == null || inOrgName.length() == 0))
      criterions.put("inOrgName", inOrgName);
    
    if (!(settDate == null || settDate.length() == 0))
      criterions.put("settDate", settDate);
    if (!(settDatea == null || settDatea.length() == 0))
      criterions.put("settDatea", settDatea);

    String collBankId = traninVidAF.getCollBankId();
    if(!(collBankId == null || collBankId.length() == 0)){
      criterions.put("collBankId", traninVidAF.getCollBankId());
    }
    
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
     pagination.getQueryCriterions().put("tranStatus", tranStatus);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    traninVidAF.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_traninMaintain_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninVidListAC";
  }

  protected HashMap makeCriterionsMap(TraninVidAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



