package org.xpup.hafmis.syscollection.accounthandle.ratemng.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.form.RatemngAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninIdAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninImportAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;

/**
 * shiyan
 */
public class RatemngMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowRatemngListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.delete", "delete");
    map.put("button.use", "use");
    return map;
  }

  public ActionForward use(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
      ActionMessages messages = null;
      try {
        IdAF idAF = (IdAF) form;
        String id = (String) idAF.getId();
        IRatemngBS ratemngBS = (IRatemngBS) BSUtils.getBusinessService("ratemngBS",
            this, mapping.getModuleConfig());
          SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
          ratemngBS.useRatemng_sy(securityInfo);
       }catch (BusinessException bex) {
         messages = new ActionMessages();
         messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
             .getLocalizedMessage().toString(), false));
         saveErrors(request, messages);
       }
      catch (Exception e) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ÆôÓÃÊ§°Ü£¡",
            false));
        saveErrors(request, messages);
      }
      return mapping.findForward("showRatemngListAC");
    }

  public ActionForward importsTranin(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    TraninIdAF traninIdAF = (TraninIdAF) form;
    String inOrgId = traninIdAF.getInOrgId();
    try {
      TraninImportAF forms = new TraninImportAF();
      forms.setInOrgId(inOrgId);
      request.setAttribute("traninImportAF", forms);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_tranin_imports");
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    
   try{
    saveToken(request);
    RatemngAF ratemngAF=new RatemngAF();
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    List temp_list=securityInfo.getAllOfficeList();
    List list=new ArrayList();
    OfficeDto officeDto=null;
    Iterator chaitr=temp_list.iterator();
    while(chaitr.hasNext()){
      officeDto=(OfficeDto)chaitr.next();
        list.add(new org.apache.struts.util.LabelValueBean(officeDto.getOfficeName(),""+officeDto.getOfficeCode()));
    }
    request.getSession(true).setAttribute("list",list);
    request.setAttribute("ratemngAF", ratemngAF);
    ratemngAF.reset(mapping, request);
   } catch(Exception e){
     e.printStackTrace();
   }
    return mapping.findForward("to_ratemng_add");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)throws BusinessException {
    ActionMessages messages = null;
    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();
      IRatemngBS ratemngBS = (IRatemngBS) BSUtils.getBusinessService("ratemngBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        ratemngBS.removeRatemng_sy(id,securityInfo);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ý³É¹¦£¡",
            false));
        saveErrors(request, messages);
     }
    catch (Exception e) {
      messages = new ActionMessages();

      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showRatemngListAC");
  }
}
