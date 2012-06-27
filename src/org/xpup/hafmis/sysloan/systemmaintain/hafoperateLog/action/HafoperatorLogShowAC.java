package org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action;

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
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.bsinterface.IOrgbusinessflowBS;
import org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.bsinterface.IHafoperatorLogBS;
import org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.form.HafoperateLogAF;
import org.xpup.security.common.domain.User;

public class HafoperatorLogShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action.HafoperatorLogShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      HafoperateLogAF af=(HafoperateLogAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IHafoperatorLogBS hafoperatorLogBS = (IHafoperatorLogBS) BSUtils.getBusinessService("hafoperatorLogLoanBS", this, mapping.getModuleConfig());
      /**
       * ∑÷“≥
       */  
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      List operList = securityInfo.getUserList();
      List operList1 = new ArrayList();
      User user = null;
      Iterator itr2 = operList.iterator();
      while (itr2.hasNext()) {
        user = (User) itr2.next();
        operList1.add(new org.apache.struts.util.LabelValueBean(user
            .getUsername(), user.getUsername()));
      }
      List list=hafoperatorLogBS.findHafOperateLog(pagination, securityInfo);
      af.setList(list);
      
      request.getSession(true).setAttribute("operList1", operList1);
      af.setOpmodlemap(BusiTools.listBusiProperty(BusiLogConst.OPMODELOAN));
      af.setOpactionmap(BusiTools.listBusiProperty(BusiLogConst.BIZLOG_ACTION));
      request.getSession().setAttribute("alllist", pagination.getQueryCriterions().get("alllist"));//¥Ú”°
      request.setAttribute("hafoperateLogAF", af);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("success");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "plOperateLog.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
