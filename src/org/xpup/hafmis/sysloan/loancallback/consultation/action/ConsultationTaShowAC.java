package org.xpup.hafmis.sysloan.loancallback.consultation.action; 


import java.util.ArrayList;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.consultation.bsinterface.IConsultationBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class ConsultationTaShowAC extends Action {
public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.consultation.action.ConsultationTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
 
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);  
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
      .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      LoancallbackTaAF af = new LoancallbackTaAF(); 
      saveToken(request);
      String bizType=request.getParameter("bizType");
      if(bizType != null && !bizType.equals("")){
        if(bizType.equals("2")){
          pagination.getQueryCriterions().put("callbackMonth",null);
        }
      }
      pagination.getQueryCriterions().put("aheadMoney",null);
      pagination.getQueryCriterions().put("bizType", bizType);
      af = loancallbackBS.findShouldLoancallbackInfo(pagination, securityInfo);
      pagination.getQueryCriterions().put("shouldBackList",af.getShouldBackList());
      pagination.getQueryCriterions().put("identifier","consultation");
       List monthYearList = new ArrayList();
       Iterator itr = af.getMonthYearList().iterator();
       while(itr.hasNext()){
         ShouldBackListDTO dto = (ShouldBackListDTO)itr.next();
         monthYearList.add(new org.apache.struts.util.LabelValueBean(dto.getLoanKouYearmonth(), dto.getLoanKouYearmonth()));
       }
      request.setAttribute("loancallbackTaAF", af);
      request.getSession(true).setAttribute("monthYearList", monthYearList);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    
    return mapping.findForward("consultation_jy");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      pagination.getQueryCriterions().put("bizType", "2");
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}