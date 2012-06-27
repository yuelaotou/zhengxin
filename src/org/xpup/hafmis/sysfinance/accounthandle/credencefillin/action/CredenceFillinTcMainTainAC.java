package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;

public class CredenceFillinTcMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.SettleIncAndDecInfo.all", "saveAllSettleIncAndDecInfo");
    map.put("button.SettleIncAndDecInfo", "saveSettleIncAndDecInfo");
    return map;
  }

  /**
   * 损益结转方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveSettleIncAndDecInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String[] str = request.getParameterValues("checkbox");
      if (str==null) {
        throw new BusinessException("请选择要结转的业务！");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      // 得到凭证日期
      String CredenceDateStart = (String) request.getSession().getAttribute("credenceDateStart");
      String CredenceDateEnd = (String) request.getSession().getAttribute("credenceDateEnd");
      credenceFillinBS.saveSettleIncAndDecInfo(str, securityInfo, CredenceDateStart, CredenceDateEnd);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("损益结转失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinTcShowAC");
  }

  /**
   * 全部损益结转方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveAllSettleIncAndDecInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String[] str = (String[]) request.getSession().getAttribute("allinfo");

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      // 得到凭证日期
      String CredenceDateStart = (String) request.getSession().getAttribute("credenceDateStart");
      String CredenceDateEnd = (String) request.getSession().getAttribute("credenceDateEnd");
      credenceFillinBS.saveSettleIncAndDecInfo(str, securityInfo, CredenceDateStart, CredenceDateEnd);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("损益结转失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinTcShowAC");
  }
}
