package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;

/**
 * @author 王野 2007-10-03
 */
public class BailenRolTbMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    return map;
  }

  /**
   * Description 保证金登记维护
   * 
   * @author wangy 2007-10-03
   * @param 删除
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IBailenRolBS bailenRolBS = (IBailenRolBS) BSUtils.getBusinessService(
          "bailenRolBS", this, mapping.getModuleConfig());
      IdAF idAF = (IdAF) form;
      String flowHeadId = idAF.getId().toString();
      String contractId = (String) request.getParameter("contractid");
      bailenRolBS.deleteBailenRolInfo(flowHeadId, contractId, securityInfo);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("bailenRolTbShowAC");
  }

  /**
   * Description 保证金登记维护
   * 
   * @author wangy 2007-10-03
   * @param 打印
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          BailenRolTbShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IBailenRolBS bailenRolBS = (IBailenRolBS) BSUtils.getBusinessService(
          "bailenRolBS", this, mapping.getModuleConfig());
      List printList = bailenRolBS.findBailenRolTbPrint(pagination,securityInfo);
      request.setAttribute("URL", "bailenRolTbShowAC.do");
      //String userName = securityInfo.getRealName();

      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
         .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
         String userName="";
         try {
           String name = loanDocNumDesignBS.getNamePara();

           if (name.equals("1")) {
             userName = securityInfo.getUserName();
           } else {
             userName = securityInfo.getRealName();
           }

         } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
      String plbizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("plbizDate", plbizDate);
      request.setAttribute("printList", printList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailenrol_printtb");
  }

}
