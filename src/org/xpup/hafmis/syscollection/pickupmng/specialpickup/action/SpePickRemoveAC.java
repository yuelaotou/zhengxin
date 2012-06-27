package org.xpup.hafmis.syscollection.pickupmng.specialpickup.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface.ISpePickBS;
public class SpePickRemoveAC extends LookupDispatchAction {




      protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("button.delete", "delete");
        return map;
      }
      public ActionForward delete(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response)
          throws Exception {
        ActionMessages messages =null;
        try{
          IdAF idaf=(IdAF)form;
          String id=idaf.getId().toString();
          ISpePickBS spePickBS = (ISpePickBS) BSUtils
          .getBusinessService("spePickBS", this, mapping.getModuleConfig());
          SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
          spePickBS.deleteSpePick(new Integer (id),sInfo);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ý³É¹¦£¡",
              false));
          saveErrors(request, messages);
        }catch(BusinessException bex){
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
              false));
          saveErrors(request, messages);
        }
        return mapping.findForward("show_list");
      }
      public String getPaginationKey (){
        return "cn.net.goldsoft.hafmis.collection.action.UC06ShowOrganizationsTqtqwhlbAC";
      }


    }



