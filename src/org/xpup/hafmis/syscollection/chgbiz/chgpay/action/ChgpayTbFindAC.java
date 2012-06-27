package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayListAF;

public class ChgpayTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ChgpayListAF f = (ChgpayListAF) form;
    ChgpayListAF chpaylistAF = new ChgpayListAF();
    Map chgpayMap = BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
    chpaylistAF.setChgpayMap(chgpayMap);
    request.setAttribute("chpaylistAF", chpaylistAF);
    HashMap map = makeCriterionsMap(f);
    String paginationKey = ChgpayTbSouwAC.PAGINATION_KEY;
    Pagination pagination = new Pagination(0, 10, 1,
        "chgPaymentPayment.chgStatus ASC,chgPaymentPayment.id DESC", "", map);
    request.getSession().setAttribute(paginationKey, pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "goto_show";
  }

  protected HashMap makeCriterionsMap(ChgpayListAF form) {
    HashMap m = new HashMap();
    String id = ((String) form.getOrg().getId()).trim();
    if (!((id == null || "".equals(id)))) {
      m.put("org.id", id);
    }
    String name = form.getOrg().getOrgInfo().getName().trim();
    if (!(name == null || "".equals(name))) {
      m.put("org.orgInfo.name", name);
    }
    String chgMonth = form.getChgMonth().trim();
    if (!(chgMonth == null || "".equals(chgMonth))) {
      m.put("chgMonth", chgMonth);
    }
    String typetb = form.getTypetb().trim();
    if (!(typetb == null || "".equals(typetb))) {
      m.put("typetb", typetb);
    }
    return m;
  }
}