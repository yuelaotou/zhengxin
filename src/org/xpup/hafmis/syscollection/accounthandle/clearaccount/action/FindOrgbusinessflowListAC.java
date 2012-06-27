package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;

public class FindOrgbusinessflowListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    OrgbusinessflowAF f= (OrgbusinessflowAF)form;

    HashMap criterions = makeCriterionsMap(f);
    HttpSession session=request.getSession(false);
    session.setAttribute("sessionLJ",null);   
    
    Pagination pagination = new Pagination(0, 10, 1,
        "id", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_orgbusinessflow_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ShowOrgbusinessflowListAC";
  }

  protected HashMap makeCriterionsMap(OrgbusinessflowAF form) {
    HashMap m = new HashMap();

    String officecode = form.getOfficecode();
    if (!(officecode == null || "".equals(officecode))) {
      m.put("officecode", officecode);
    }

    String bankcode = form.getBankcode();
    if (!(bankcode == null || "".equals(bankcode))) {
      m.put("bankcode", bankcode);
    }
    
    String orgid = form.getOrgcode();
    if (!(orgid == null || "".equals(orgid))) {
      m.put("orgid", orgid);
    }
    
    String orgname = form.getOrgname();
    if (!(orgname == null || orgname.length() == 0)){
      m.put("orgname", orgname);
    }

    String notenum = form.getNotenum();
    if (!(notenum == null || "".equals(notenum))) {
      m.put("notenum", notenum);
    }

    String docnum = form.getDocnum();
    if (!(docnum == null || "".equals(docnum))) {
      m.put("docnum", docnum);
    }
    
    String bsstatus = form.getBsstatus();
    if (!(bsstatus == null || bsstatus.length() == 0)){
      m.put("bsstatus", bsstatus);
    }
    
    String bstype = form.getBstype();
    if(!(bstype == null || bstype.length() == 0)){
      m.put("bstype", bstype);
    }
    String setmonthstart = form.getSetMonthStart() ;
    if(!(setmonthstart == null || setmonthstart.length() == 0)){
      m.put("setmonthstart", setmonthstart);
    }
    String setmonthend = form.getSetMonthEnd() ;
    if(!(setmonthend == null || setmonthend.length() == 0)){
      m.put("setmonthend", setmonthend);
    }
    String setmoneystart = form.getSetMoneyStart();
    if(!(setmoneystart == null || setmoneystart.length() == 0)){
      m.put("setmoneystart", setmoneystart);
    }
    String setmoneyend = form.getSetMoneyEnd();
    if(!(setmoneyend == null || setmoneyend.length() == 0)){
      m.put("setmoneyend", setmoneyend);
    }
    String setpeopcountstart = form.getSetpeopcountStart() ;
    if(!(setpeopcountstart == null || setpeopcountstart.length() == 0)){
      m.put("setpeopcountstart", setpeopcountstart);
    }
    String setpeopcountend = form.getSetpeopcountEnd();
    if(!(setpeopcountend == null || setpeopcountend.length() == 0)){
      m.put("setpeopcountend", setpeopcountend);
    }
    String setdirection = form.getSetDirection();
    if(!(setdirection == null || setdirection.length() == 0)){
      m.put("setdirection", setdirection);
    }
    
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
