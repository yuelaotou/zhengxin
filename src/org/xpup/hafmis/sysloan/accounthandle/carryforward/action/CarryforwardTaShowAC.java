package org.xpup.hafmis.sysloan.accounthandle.carryforward.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface.ICarryforwardBS;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class CarryforwardTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.carryforward.action.CarryforwardTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    ActionMessages messages = null;
    RateShowAF rateShowAF=new RateShowAF();
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      request.setAttribute("timenow", securityInfo.getUserInfo().getPlbizDate().subSequence(0, 4));
      ICarryforwardBS carryforwardBS = (ICarryforwardBS) BSUtils.getBusinessService("carryforwardBS", this,
          mapping.getModuleConfig());
      //插入下拉列表银行
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
      request.getSession(true).setAttribute("banklist", banklist);
      if(!pagination.getQueryCriterions().isEmpty()){
        CarryforwardShowAF carryforwardShowAF=new CarryforwardShowAF();
        carryforwardShowAF= carryforwardBS.queryBorrowerAccList(pagination, securityInfo);
        request.setAttribute("carryforwardShowAF", carryforwardShowAF);
        request.setAttribute("massageinfo", "pass");
        carryforwardShowAF.reset(mapping, request);
      } 
    }catch(BusinessException bex){
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
    false));
    saveErrors(request, messages);
     } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_carryforwardTa_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "restoreLoan.contractId", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}