package org.xpup.hafmis.sysloan.loancallback.loancallback.action; 

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
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTbDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTbAF;
import org.xpup.security.common.domain.Userslogincollbank;


public class LoancallbackTbShowAC extends Action {
 
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.loancallback.action.LoancallbackTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      LoancallbackTbAF loancallbackTbAF=new LoancallbackTbAF();
      Map typeMap=BusiTools.listBusiProperty(BusiConst.PLBUSINESSTYPE);
      Map statusMap=BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
      Map yesornoMap=BusiTools.listBusiProperty(BusiConst.YesNo);
      statusMap.remove(new Integer(BusiConst.PLBUSINESSSTATE_EXP));
      Map newTypeMap = new HashMap();
      newTypeMap.put(new Integer(BusiConst.PLBUSINESSTYPE_SINGLERECOVER), typeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_SINGLERECOVER)));
      newTypeMap.put(new Integer(BusiConst.PLBUSINESSTYPE_PARTRECOVER), typeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_PARTRECOVER)));
      newTypeMap.put(new Integer(BusiConst.PLBUSINESSTYPE_ALLCLEAR), typeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_ALLCLEAR)));
      newTypeMap.put(new Integer(BusiConst.PLBUSINESSTYPE_SOMERECOVER), typeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_SOMERECOVER)));
      loancallbackTbAF.setStatusMap(statusMap);
      loancallbackTbAF.setTypeMap(newTypeMap);
      loancallbackTbAF.setYesornoMap(yesornoMap);
      Pagination pagination = getPagination(LoancallbackTbShowAC.PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
      .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      List callbacklist = loancallbackBS.findCallbackList(pagination, securityInfo);
      LoancallbackTbDTO loancallbackTbDTO = loancallbackBS.findCallbackListTotal(pagination, securityInfo);
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
      request.setAttribute("loancallbackTbDTO", loancallbackTbDTO);
      request.getSession(true).setAttribute("banklist", banklist);
      request.setAttribute("loancallbackTbAF", loancallbackTbAF);
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
    return "loancallback_lb";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("status", "0");
      pagination = new Pagination(0, 10, 1, "headid", "DESC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
  
}


