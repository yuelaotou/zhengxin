package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountServiceShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;


/**
 * 
 * @author 李鹏
 *2007-7-6
 */
public class AdjustaccountServiceTaShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountServiceTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
        Pagination pagination = getPagination(PAGINATION_KEY, request); 
    try{
        
        saveToken(request);
        PaginationUtils.updatePagination(pagination, request);  
        IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
        .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
        String type =null;
        String print=(String)request.getAttribute("PRINT");
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
        type=(String)request.getSession().getAttribute("type");
        String types=(String)pagination.getQueryCriterions().get("type");
        if(type!=types) type=types;
        List adjustWrongAccountHeadlist=new ArrayList();
        BigDecimal total=new BigDecimal(0.00);
        Integer persontotal=new Integer(0);
        if(type==null){
          AdjustaccountDTO adjustaccountDTO=adjustAccountBS.findAdjustWrongAccountHeadByStatus(pagination,securityInfo);
          adjustWrongAccountHeadlist=adjustaccountDTO.getList();
          total=adjustaccountDTO.getTotal();
          persontotal=adjustaccountDTO.getPersontotal();
        }else{//多条件查询
          AdjustaccountDTO adjustaccountDTO =adjustAccountBS.findAdjustWrongAccountHeadByPagination(pagination,securityInfo);
          adjustWrongAccountHeadlist=adjustaccountDTO.getList();
          total=adjustaccountDTO.getTotal();
          persontotal=adjustaccountDTO.getPersontotal();
        }   
        AdjustaccountServiceShowAF adjustaccountServiceShowAF=new AdjustaccountServiceShowAF();
        Map bisMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
        adjustaccountServiceShowAF.setBisMap(bisMap);
        if(adjustWrongAccountHeadlist==null){//315里没有状态为1 或着 3 的  
        }
        else{
          adjustaccountServiceShowAF.setList(adjustWrongAccountHeadlist);
          adjustaccountServiceShowAF.setListCount(adjustWrongAccountHeadlist.size());
          adjustaccountServiceShowAF.setTotal(total);
          adjustaccountServiceShowAF.setPerson(persontotal.toString());
          request.setAttribute("adjustaccountServiceShowAF", adjustaccountServiceShowAF);
          pagination.getQueryCriterions().put("pageList", adjustWrongAccountHeadlist);
        }
        request.getSession().setAttribute("type",null);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有您要查询的信息！",
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    List printlist=(List)pagination.getQueryCriterions().get("adjustprint"); 
    request.setAttribute("printlist", printlist);
    return mapping.findForward("to_adjustaccountservice_list");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
   
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) { 
      pagination = new Pagination(0, 10, 1, "adjustWrongAccountHead.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
