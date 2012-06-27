package org.xpup.hafmis.syscollection.pickupmng.specialpickup.action;


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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface.ISpePickBS;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.form.SpePickAF;
public class SpePickSaveAC extends LookupDispatchAction {

    protected Map getKeyMethodMap() {
      Map map = new HashMap();
      map.put("button.pickup.infor", "back");
      map.put("button.repayment", "modify");
      map.put("button.sure", "save");  
      return map;
    }
    public ActionForward back(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      return mapping.findForward("show_organizations");
    }
    
    public ActionForward modify(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
//      OrgkhAF af = (OrgkhAF) form;
//
//      try {
//       
//        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
//            .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
//        af.getOrg().setId((Serializable)request.getSession().getAttribute("hcodeid"));
//      orgOpenAccountBS.modifyOpen(af.getOrg());
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
      return mapping.findForward("show_organizations");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      ActionMessages messages = new ActionMessages();
      try {
        SpePickAF af = (SpePickAF) form;
        SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        if(af.getSpecialPick()!=null)
        {
          String orgid=af.getSpecialPick().getOrg().getId().toString();
          String empid=af.getSpecialPick().getEmp().getEmpId().toString();
          ISpePickBS spePickBS = (ISpePickBS) BSUtils
          .getBusinessService("spePickBS", this, mapping.getModuleConfig());
          SpecialPick specialPick=spePickBS.findSpePickMoney(orgid, empid);
          spePickBS.isClearAccount(orgid, empid);
          if(specialPick!=null)
          {
            if(specialPick.getIsPick().toString().equals("1"))
            {
              spePickBS.updateOrgOpenAccount(af.getSpecialPick(),sInfo);
            }
            else
            {
              spePickBS.saveOrgOpenAccount(af.getSpecialPick(),sInfo);
            }
          }
          else
          {
            spePickBS.saveOrgOpenAccount(af.getSpecialPick(),sInfo);
          }
        }
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
        return mapping.findForward("to_spe_pick_show");
      } catch (Exception e) {
        e.printStackTrace();
      }
      return mapping.findForward("to_spe_pick_show");
    }


  }
