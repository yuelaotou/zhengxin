package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

import java.util.HashMap;

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
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckDetailAF;



/**
 * 
 * @author 李鹏
 * 2007-7-10
 */
public class BizcheckTaWindowPrintAC extends Action{  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ActionMessages messages =null;
//    try{
//      //头表ID
//      Pagination pagination = getPagination(BizcheckTaWindowAC.PAGINATION_KEY, request); 
//      saveToken(request);
//      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
//      .getAttribute("SecurityInfo");
//      PaginationUtils.updatePagination(pagination, request);  
//      IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils
//      .getBusinessService("bizcheckBS", this, mapping.getModuleConfig());
//      BizcheckDetailAF bizcheckDetailAF=bizcheckBS.findOrgHAFAccountFlowCellByID(pagination,securityInfo);
//      request.setAttribute("bizcheckDetailAF", bizcheckDetailAF);
//    }catch(BusinessException bex){
//      messages=new ActionMessages();
//      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有您要查询的信息！",
//          false));
//      saveErrors(request, messages);
//    }catch(Exception ex){
//      ex.printStackTrace();
//    }
    
    return mapping.findForward("show_report");
  }
//  private Pagination getPagination(String paginationKey,
//      HttpServletRequest request) {
//    Pagination pagination = (Pagination) request.getSession().getAttribute(
//        paginationKey);
//    if (pagination == null) {
//      pagination = new Pagination(0, 5, 1, "empHAFAccountFlow.id", "ASC",
//          new HashMap(0));
//      request.getSession().setAttribute(paginationKey, pagination);
//    }   
//    return pagination;
//  }
}
