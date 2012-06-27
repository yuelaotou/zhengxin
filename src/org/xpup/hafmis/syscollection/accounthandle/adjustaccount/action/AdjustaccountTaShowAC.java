package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;


/**
 * 
 * @author 李鹏
 *2007-6-28
 */    
public class AdjustaccountTaShowAC extends Action{
       
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
        Pagination pagination = getPagination(PAGINATION_KEY, request); 
    try{    
     
      saveToken(request);
      PaginationUtils.updatePagination(pagination, request);  

      AdjustaccountDTO adjustaccountDTO=null;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
      .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
      String temp_adjustWrongAccountHead_id=(String)request.getSession(false).getAttribute("temp_adjustWrongAccountHead_id");
      String temp_type=(String)request.getSession(false).getAttribute("findadjustWrongAccountHead_type");//判断是否有瞒足条件-->为1可以到101查询
      //更改过。
      String empHAFAccountFlowOrgId=(String)request.getSession(false).getAttribute("empHAFAccountFlowOrgId");
      if(empHAFAccountFlowOrgId!=null&&!empHAFAccountFlowOrgId.equals("")){
      pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.orgId", empHAFAccountFlowOrgId);
      }
      if(temp_type==null)
        temp_type="0";
      AdjustaccountShowAF adjustaccountShowAF=adjustAccountBS.findAdjustWrongAccountHeadAndTailIDByPagination(temp_adjustWrongAccountHead_id,pagination,temp_type,securityInfo);
      request.setAttribute("adjustaccountShowAF", adjustaccountShowAF);   
      
      List list=(List)pagination.getQueryCriterions().get("pageList"); 
 
   
   
   
   
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
      bex.printStackTrace();
    }catch(Exception ex){
      ex.printStackTrace();
    }
    List printlist=(List)pagination.getQueryCriterions().get("adjustprint"); 
    request.setAttribute("printlist", printlist);
    return mapping.findForward("to_adjustaccount_list");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.orgHAFAccountFlow.docNum", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
