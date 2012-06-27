package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;


/**
 * 
 * @author ¿Ó≈Ù
 *2007-6-28
 */
public class AdjustaccountTaFindOrgAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        String orgId="";
        String orgName="";
        String text="";
        String types="";
        String typemassage=""; 
        ActionMessages messages =null;
        try {  
          SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
          if(securityInfo!=null){
          orgId=(String)request.getParameter("orgId");
          orgName=(String)request.getParameter("orgName");
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.orgId",orgId);
          IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
          AdjustWrongAccountHead adjustWrongAccountHead=null;
          adjustWrongAccountHead=adjustAccountBS.findOrgHAFAccountFlowByOrgAndStatus(orgId,securityInfo);
          if(adjustWrongAccountHead!=null){
            request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "2");
          }else
            request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "10");
            request.getSession(false).setAttribute("findadjustWrongAccountHead_orgId", orgId);
            String temptypes=adjustAccountBS.querySpecialPickAndTranOutHead(orgId);
            if(temptypes!=null&&!temptypes.equals("")){
              typemassage=temptypes;
            }
          } 
        } catch (BusinessException bex) {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
              false));
          saveErrors(request, messages);
          types=bex.getMessage();
     //     request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "0");  
        } 
        text="displayOrg('"+orgId+"','"+orgName+"','"+types+"','"+typemassage+"')";
        response.getWriter().write(text); 
        response.getWriter().close();
        
        return null; 
}

  protected String getPaginationKey() {
    return AdjustaccountTaShowAC.PAGINATION_KEY;
 }
}
