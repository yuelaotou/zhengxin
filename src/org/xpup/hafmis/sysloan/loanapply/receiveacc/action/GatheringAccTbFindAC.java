package org.xpup.hafmis.sysloan.loanapply.receiveacc.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.Pagination;

import org.xpup.hafmis.sysloan.loanapply.receiveacc.form.GatheringAccAF;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.action.GatheringAccTbShowAC;

public class GatheringAccTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      GatheringAccAF gatheringAccAF = (GatheringAccAF) form;
      HashMap criterions = makeCriterionsMap(gatheringAccAF);
      Pagination pagination = new Pagination(0, 10, 1, "receiveankmodifyid", "DESC",
          criterions);
      String paginationKey = GatheringAccTbShowAC.PAGINATION_KEY;
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_gathering_show");
  }

  protected HashMap makeCriterionsMap(GatheringAccAF gatheringAccAF) {
    HashMap map = new HashMap();
    String contractId = gatheringAccAF.getContractId();
    if (!(contractId == null || "".equals(contractId.trim()))) {
      map.put("contractId", contractId.trim());
    }
    String borrowerName = gatheringAccAF.getBorrowerName();
    if (!(borrowerName == null || "".equals(borrowerName.trim()))) {
      map.put("borrowerName", borrowerName.trim());
    }
    String cardNum = gatheringAccAF.getCardNum();
    if (!(cardNum == null || "".equals(cardNum.trim()))) {
      map.put("cardNum", cardNum.trim());
    }
    return map;
  }
}
