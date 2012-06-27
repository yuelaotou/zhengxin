package org.xpup.hafmis.sysloan.loanapply.giveacc.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.giveacc.form.HouseAccAF;
import org.xpup.hafmis.sysloan.loanapply.giveacc.action.HouseTbShowAC;

public class HouseTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      HouseAccAF houseAccAF = (HouseAccAF) form;
      HashMap criterions = makeCriterionsMap(houseAccAF);
      Pagination pagination = new Pagination(0, 10, 1, "pokemodifyid", "DESC",
          criterions);
      String paginationKey = HouseTbShowAC.PAGINATION_KEY;
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_house_show");
  }

  protected HashMap makeCriterionsMap(HouseAccAF houseAccAF) {
    HashMap map = new HashMap();
    String contractId = houseAccAF.getContractId();
    if (!(contractId == null || "".equals(contractId.trim()))) {
      map.put("contractId", contractId.trim());
    }
    String borrowerName = houseAccAF.getBorrowerName();
    if (!(borrowerName == null || "".equals(borrowerName.trim()))) {
      map.put("borrowerName", borrowerName.trim());
    }
    String cardNum = houseAccAF.getCardNum();
    if (!(cardNum == null || "".equals(cardNum.trim()))) {
      map.put("cardNum", cardNum.trim());
    }
    String sellerName = houseAccAF.getSellerName();
    if (!(sellerName == null || "".equals(sellerName.trim()))) {
      map.put("sellerName", sellerName.trim());
    }
    return map;
  }
}
