package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTbDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTbAF;
import org.xpup.security.common.domain.Userslogincollbank;


public class BadDebtDestroyTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action.BadDebtDestroyTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      BadDebtDestroyTbAF badDebtDestroyTbAF=new BadDebtDestroyTbAF();
      Map statusMap=BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
      statusMap.remove(new Integer(BusiConst.PLBUSINESSSTATE_EXP));
      badDebtDestroyTbAF.setStatusMap(statusMap);
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      List callbacklist = badDebtDestroyBS.findCallbackList(pagination, securityInfo);
      BadDebtDestroyTbDTO badDebtDestroyTbDTO = badDebtDestroyBS.findCallbackListTotal(pagination, securityInfo);
      List temp_banklist = securityInfo.getDkUserBankList();
      List banklist = new ArrayList();
      if(!temp_banklist.isEmpty()){
        Iterator itr = temp_banklist.iterator();    
        while (itr.hasNext()) {
          Userslogincollbank userslogincollbank=(Userslogincollbank)itr.next();
          banklist.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
        }
      }
      request.setAttribute("callbacklist", callbacklist);
      request.setAttribute("badDebtDestroyTbDTO", badDebtDestroyTbDTO);
      request.getSession(true).setAttribute("banklist", banklist);
      request.setAttribute("badDebtDestroyTbAF", badDebtDestroyTbAF);
      }catch(BusinessException bex){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
            false));
        saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }
  protected String getForword() {
    return "baddebtdestroy_lb";
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("status", "0");
      pagination = new Pagination(0, 10, 1, "loanFlowHead.flowHeadId", "DESC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
  
}


