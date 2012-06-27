package org.xpup.hafmis.syscollection.tranmng.tranout.action;

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
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAF;



public class Tran_showTCAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showTCAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try { 
      /**
       * 分页
       */
      String headid =(String) request.getAttribute("headid");
      
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
     
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
      TranAF tranAF = new TranAF();
      if(headid != null){
        pagination.getQueryCriterions().put("tranOutHeadid", headid);
      }
      tranAF = tranoutBS.findTranOutInfoMX(pagination);
      request.setAttribute("tranAF", tranAF);
//      String orgid = (String) pagination.getQueryCriterions().get("id");
      
//      List list = new ArrayList();
//      TranAF tranAF = new TranAF();
//      String note_Num = null;
//      String tranStatus = null;//状态
//      TranOutHead tranOutHead = null;
//      
//      if(outorgid!=null && outorgid.equals("")){
//         list = tranoutBS.FindNot_num(outorgid);
//          if(list.size()>0 && list!=null){
//            tranOutHead = (TranOutHead)list.get(0);
//            tranStatus = tranOutHead.getTranStatus().toString();
//          }else{
//            System.out.println("没有此单位!!");
//          }
//      }
//      if(outorgid!=null&&outorgid.length()>0){
//        tranAF = tranoutBS.findtranoutOrgName(outorgid, pagination,securityInfo);
//      }
//      
//      request.setAttribute("tranAF", tranAF);
//
//      pagination.getQueryCriterions().put("pageList", tranAF.getList());
    } catch(Exception bex){
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_show_jsp");
  }

  private Pagination getPagination(String paginationKey,//初始化
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {        
      pagination = new Pagination(0, 10, 1, " tranOutTail.id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  
}
