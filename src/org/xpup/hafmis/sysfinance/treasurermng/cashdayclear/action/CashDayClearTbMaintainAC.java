package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

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
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbShowListDTO;


public class CashDayClearTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.transfers", "transfers");
    map.put("button.transfers.all", "transfersAll");
    return map;
  }
  public ActionForward transfers(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      IdAF idAF = (IdAF)form;
      String[] rowArray=idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      //credenceType为0时，就是现金日记账的转账页面；为1时，就是银行存款日记账的转账页面
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      cashDayClearBS.cashDayClearTaTransfers(rowArray,credenceType,securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
    return mapping.findForward("cashdaycleartb_show");
  }
  public ActionForward transfersAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      List countList=(List)request.getSession().getAttribute("countList");
      int size=countList.size();
      String[] rowArray=new String[size];
      for(int i=0;i<size;i++){
        CashDayClearTbShowListDTO cashDayClearTbShowListDTO=(CashDayClearTbShowListDTO)countList.get(i);
        rowArray[i]=cashDayClearTbShowListDTO.getCredenceId();
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      //credenceType为0时，就是现金日记账的转账页面；为1时，就是银行存款日记账的转账页面
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      cashDayClearBS.cashDayClearTaTransfers(rowArray,credenceType,securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
    return mapping.findForward("cashdaycleartb_show");
  }
}
