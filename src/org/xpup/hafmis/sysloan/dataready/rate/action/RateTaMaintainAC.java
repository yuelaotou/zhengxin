package org.xpup.hafmis.sysloan.dataready.rate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRateType;
import org.xpup.hafmis.sysloan.dataready.rate.bsinterface.IRateBS;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateNewAF;
import org.xpup.hafmis.sysloan.dataready.ratetype.bsinterface.IRateTypeBS;



public class RateTaMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "addRareInfo");
    map.put("button.update", "updateRateInfo");
    map.put("button.use", "useRateInfo");
    return map;
  }
  public ActionForward addRareInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      RateNewAF rateNewAF=new RateNewAF();
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
          mapping.getModuleConfig());
      List temp_list=securityInfo.getAllOfficeList();
      List list=new ArrayList();
      OfficeDto officeDto=null;
      Iterator chaitr=temp_list.iterator();
      while(chaitr.hasNext()){
        officeDto=(OfficeDto)chaitr.next();
        list.add(new org.apache.struts.util.LabelValueBean(officeDto.getOfficeName(),""+officeDto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("list",list);
      //利率类型下拉
      List rateTypeList = rateTypeBS.findRateTypeListAll();
      List rateTypeList1 = new ArrayList();
      Iterator iterator = rateTypeList.iterator();
      LoanRateType rateType = null;
      while (iterator.hasNext()) {
        rateType = (LoanRateType) iterator.next();
        rateTypeList1.add(new org.apache.struts.util.LabelValueBean(rateType.getLoanRateExplain(),rateType.getLoanRateType()));
      }
      request.setAttribute("rateTypeList1", rateTypeList1);
      rateNewAF.setType("1");
      request.setAttribute("rateNewAF", rateNewAF);
      rateNewAF.reset(mapping, request);
     } catch(Exception e){
       e.printStackTrace();
     }
    return mapping.findForward("to_rate_new");
  }
  public ActionForward updateRateInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    IdAF idAF=(IdAF)form;
    ActionMessages messages = null;
    try{
      String rateId=idAF.getId()+"";
      RateNewAF rateNewAF=new RateNewAF();
      IRateBS rateBS = (IRateBS) BSUtils.getBusinessService("rateBS", this,
          mapping.getModuleConfig());
      rateNewAF=rateBS.findRateInfo(rateId);
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
      //利率类型下拉
      IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
          mapping.getModuleConfig());
      List rateTypeList = rateTypeBS.findRateTypeListAll();
      List rateTypeList1 = new ArrayList();
      Iterator iterator = rateTypeList.iterator();
      LoanRateType rateType = null;
      while (iterator.hasNext()) {
        rateType = (LoanRateType) iterator.next();
        rateTypeList1.add(new org.apache.struts.util.LabelValueBean(rateType.getLoanRateExplain(),rateType.getLoanRateType()));
      }
      request.setAttribute("rateTypeList1", rateTypeList1);
      request.setAttribute("rateNewAF", rateNewAF);
      rateNewAF.reset(mapping, request);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
      return mapping.findForward("rateTaShowAC");
    } catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_rate_new");
  }
  public ActionForward useRateInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    IdAF idAF=(IdAF)form;
    ActionMessages messages = null;
    try{
      String rateId=idAF.getId()+"";
      IRateBS rateBS = (IRateBS) BSUtils.getBusinessService("rateBS", this,
          mapping.getModuleConfig());
      String rateUseInfo=rateBS.checkUseInfo_sy(rateId);
      if(rateUseInfo.equals("pass")){
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("pass", false));
        if(rateId!=null&&!rateId.equals("")){
        request.getSession().setAttribute("rateId", rateId);
        }
        saveErrors(request, messages);
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("rateTaShowAC");
  }
}
