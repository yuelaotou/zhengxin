package org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.bsinterface.IBeforeLoanAdvisoryBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form.BeforeLoanAdvisoryShowAF;

public class BeforeLoanAdvisoryShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.action.BeforeLoanAdvisoryShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    try {
    
    BeforeLoanAdvisoryShowAF  beforeLoanAdvisoryShowAF=(BeforeLoanAdvisoryShowAF)form;
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    String type=(String)request.getSession().getAttribute("type");
    if(type==null||type.equals(""))
    {
      pagination=null;
      beforeLoanAdvisoryShowAF.reset(); 
    }
    else
    {
     beforeLoanAdvisoryShowAF= (BeforeLoanAdvisoryShowAF)form;
    }
    IBeforeLoanAdvisoryBS  beforeLoanAdvisoryBS  = (IBeforeLoanAdvisoryBS) BSUtils.getBusinessService(
        "beforeLoanAdvisoryBS", this, mapping.getModuleConfig());
    if(type!=null&&type.equals("1"))
    {
    beforeLoanAdvisoryShowAF=beforeLoanAdvisoryBS.queryEmpInfo(pagination, securityInfo,beforeLoanAdvisoryShowAF);
    }
    if(type!=null&&type.equals("2"))
    {
      beforeLoanAdvisoryShowAF=beforeLoanAdvisoryBS.querySpouseInfo(pagination, securityInfo, beforeLoanAdvisoryShowAF);
    }
    Map houseTypeMap=BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
    beforeLoanAdvisoryShowAF.setHouseTypeMap(houseTypeMap);
    request.setAttribute("beforeLoanAdvisoryShowAF", beforeLoanAdvisoryShowAF);
   
    }
    catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_beforeLoanAdvisory");
    
   
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
