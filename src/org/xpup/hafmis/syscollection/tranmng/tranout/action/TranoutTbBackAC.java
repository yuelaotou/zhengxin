package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;


public class TranoutTbBackAC extends Action {
  
    public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showAC";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
    throws Exception {
      ActionMessages messages = null;
      try{
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        String userName = securityInfo.getUserName();
        String ip = securityInfo.getUserIp();
        String setDate = securityInfo.getUserInfo().getBizDate();
        IdAF idaf = (IdAF)form;
        String pkid = (String)request.getSession().getAttribute("tranoutheadid");//309 pk
        ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
  
        tranoutBS.updateTranHeadTranIn(pkid, userName, ip, setDate); 
        request.getSession().removeAttribute("tranoutheadid");
      }catch(BusinessException b){
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
            false));
        saveErrors(request, messages);      
      }
      return mapping.findForward("to_tran_showTbAC");
 
    }
}
