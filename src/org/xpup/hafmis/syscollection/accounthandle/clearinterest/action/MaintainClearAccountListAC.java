package org.xpup.hafmis.syscollection.accounthandle.clearinterest.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearinterest.bsinterface.IClearaccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearinterest.dto.ClearaccountDTO;

public class MaintainClearAccountListAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.in.clearaccount", "clearaccount");
    map.put("button.in.clearaccountall", "clearaccountall");
    return map;
  }

  public ActionForward clearaccount(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    try {
      IdAF idaf = (IdAF) form;
      String[] rowArray = idaf.getRowArray();
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      //String officecode = securityInfo.getUserInfo().getOfficeId().toString();
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();
      String flag="0";
      clearaccountBS.clearacount(rowArray, "", bizDate, ip, oper, securityInfo,flag);

    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_clearaccount_list");
  }

  public ActionForward clearaccountall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    try {
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      //String officecode = securityInfo.getUserInfo().getOfficeId().toString();
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();
      HttpSession session = request.getSession();
      List list_all = (List) session.getAttribute("clearaccountalllist");
      String[] rowArray = new String[list_all.size()];
      ClearaccountDTO dto = null;
      for (int i = 0; i < list_all.size(); i++) {
        dto = new ClearaccountDTO();
        dto = (ClearaccountDTO) list_all.get(i);
        rowArray[i] = dto.getOrgcode();
      }
      String flag="1";
      clearaccountBS.clearacount(rowArray, "", bizDate, ip, oper, securityInfo,flag);

    } catch (BusinessException e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_clearaccount_list");
  }


}
