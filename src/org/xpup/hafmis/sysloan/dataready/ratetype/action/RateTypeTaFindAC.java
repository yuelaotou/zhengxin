package org.xpup.hafmis.sysloan.dataready.ratetype.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;
import org.xpup.hafmis.sysloan.dataready.ratetype.form.RateTypeShowAF;

public class RateTypeTaFindAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    RateTypeShowAF f = (RateTypeShowAF)form;
    HashMap criterionsMap = makeCriterionsMap(f);
    String paginationKey = getPaginationKey();
    Pagination pagination = new Pagination(0, 10, 1, "loanRateType.id", "DESC",criterionsMap);
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward(getForward());
  }
  private String getForward(){
    return "rateTypeTaShowAC";
  }
  protected String getPaginationKey() {
    return RateTypeTaShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(RateTypeShowAF form) {
    HashMap m = new HashMap();
    String rateType = form.getRateType();
    if(rateType != null && !"".equals(rateType)){
      m.put("rateType", rateType.trim());
    }
    String rateExplain = form.getRateExplain();
    if(rateExplain != null && !"".equals(rateExplain)){
      m.put("rateExplain", rateExplain.trim());
    }
    String rateDate = form.getRateDate();
    if(rateDate != null && !"".equals(rateDate)){
      m.put("rateDate", rateDate.trim());
    }
    return m;
  }
}
