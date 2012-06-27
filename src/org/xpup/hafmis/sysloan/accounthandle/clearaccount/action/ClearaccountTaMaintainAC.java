package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import java.util.HashMap;
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
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.bsinterface.IClearaccountBS;

public class ClearaccountTaMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.in.account", "accountInfo");
    map.put("button.in.accountall", "accountList");
    return map;
  }

  public ActionForward accountInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    IdAF idAF = (IdAF) form;
    ActionMessages messages = null;
    try {
      String[] rowArray = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      // 进入页面是要带权限的，从权限中取得是以中心为主还是以银行为主。以此来控制按钮灰显，要在action中设置状态。
      // 贷款还款类型1.中心为主2.银行为主
      String temp_plLoanReturnType = securityInfo.getPlLoanReturnType() + "";
      clearaccountBS.clearAccountInfo(securityInfo, rowArray,
          temp_plLoanReturnType);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("clearaccountTaShowAC");
  }

  public ActionForward accountList(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      // 贷款还款类型1.中心为主2.银行为主
      String temp_plLoanReturnType = securityInfo.getPlLoanReturnType() + "";
      //添加全部扎账的查询条件
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      clearaccountBS.clearAccountList(securityInfo, temp_plLoanReturnType,pagination);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("clearaccountTaShowAC");
  }
  protected String getPaginationKey() {
    return ClearaccountTaShowAC.PAGINATION_KEY;
  }
}
