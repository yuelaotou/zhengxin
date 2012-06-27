package org.xpup.hafmis.sysfinance.treasurermng.accountclear.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.accountclear.bsinterface.IAccountClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;


public class AccountClearMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.in.account", "account");
    map.put("button.in.accountall", "accountall");
    return map;
  }
  public ActionForward account(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      IdAF idAF = (IdAF)form;
      String[] rowArray=idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IAccountClearBS accountClearBS = (IAccountClearBS) BSUtils
      .getBusinessService("accountClearBS", this, mapping.getModuleConfig());
      accountClearBS.account(rowArray, securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("accountclear_show");
  }
  public ActionForward accountall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      List countList=(List)request.getSession().getAttribute("countList");
      int size=countList.size();
      String[] rowArray=new String[size];
      for(int i=0;i<size;i++){
        CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)countList.get(i);
        rowArray[i]=cashDayClearTcShowListDTO.getCredenceId();
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IAccountClearBS accountClearBS = (IAccountClearBS) BSUtils
      .getBusinessService("accountClearBS", this, mapping.getModuleConfig());
      accountClearBS.account(rowArray, securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
    return mapping.findForward("accountclear_show");
  }
}
