package org.xpup.hafmis.syscollection.collloanback.action;

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
import org.xpup.hafmis.syscollection.collloanback.bsinterface.ICollLoanbackBS;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTbListDTO;

public class CollLoanbackTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.deleteall", "deleteall");
    map.put("button.delete", "delete");
    return map;
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      IdAF idAF = (IdAF)form;
      String[] rowArray=idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICollLoanbackBS collLoanbackBS = (ICollLoanbackBS) BSUtils
      .getBusinessService("collLoanbackBS", this, mapping.getModuleConfig());
      collLoanbackBS.collLoanbackDelete(rowArray, securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("collloanbacktb_show");
  }
  public ActionForward deleteall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      List countList=(List)request.getSession().getAttribute("countlist_gjp");
      int size=countList.size();
      String[] rowArray=new String[size];
      for(int i=0;i<size;i++){
        CollLoanbackTbListDTO collLoanbackTbListDTO=(CollLoanbackTbListDTO)countList.get(i);
        rowArray[i]=collLoanbackTbListDTO.getId();
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICollLoanbackBS collLoanbackBS = (ICollLoanbackBS) BSUtils
      .getBusinessService("collLoanbackBS", this, mapping.getModuleConfig());
      collLoanbackBS.collLoanbackDelete(rowArray, securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("collloanbacktb_show");
  }
}
