package org.xpup.hafmis.sysloan.dataready.ratetype.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRateType;
import org.xpup.hafmis.sysloan.dataready.ratetype.bsinterface.IRateTypeBS;
import org.xpup.hafmis.sysloan.dataready.ratetype.form.RateTypeNewAF;

public class RateTypeTaMaintainAC extends LookupDispatchAction{

  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    return map;
  }
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      RateTypeNewAF rateTypeNewAF = new RateTypeNewAF();
      rateTypeNewAF.setButtonType("add");
      request.setAttribute("rateTypeNewAF", rateTypeNewAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_ratetype_new");
  }
  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    IdAF idAF = (IdAF)form;
    RateTypeNewAF rateTypeNewAF = new RateTypeNewAF();
    try {
      String id = idAF.getId().toString();
      IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
          mapping.getModuleConfig());
      //SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      LoanRateType loanRateType = rateTypeBS.findInfoById(new Integer(id));
      rateTypeNewAF.setButtonType("update");
      rateTypeNewAF.setRateType(loanRateType.getLoanRateType());
      rateTypeNewAF.setRateExplain(loanRateType.getLoanRateExplain());
      rateTypeNewAF.setRateDate(loanRateType.getLoanRateDate());
      rateTypeNewAF.setRateId(id);
      request.setAttribute("rateTypeNewAF", rateTypeNewAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_ratetype_new");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    IdAF idAF = (IdAF)form;
    try {
      String id = idAF.getId().toString();
      IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
          mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      rateTypeBS.deleteById(new Integer(id), securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("rateTypeTaShowAC");
  }
}
