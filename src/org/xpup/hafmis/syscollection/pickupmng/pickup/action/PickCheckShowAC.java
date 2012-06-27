package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickCheckDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickCheckAF;

public class PickCheckShowAC extends Action {
  public static final String PAGINACTION_KEY = "org.xpup.hafmis.syscollection.pickupmng.pickup.action.PickCheckShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      PickCheckAF af = new PickCheckAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PickCheckShowAC.PAGINACTION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      IPickupBS pickBS = (IPickupBS) BSUtils.getBusinessService("pickupBS",
          this, mapping.getModuleConfig());
      List pickCheckList = pickBS.getPickCheckList(pagination, securityInfo);
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      if (pickCheckList != null && pickCheckList.size() != 0) {
        List list = new ArrayList();
        if (pickCheckList.size() >= page * pageSize) {
          list = pickCheckList.subList(start, start + pageSize);
        } else {
          list = pickCheckList.subList(start, pickCheckList.size());
        }
        af.setList(list);
        int person = 0;
        BigDecimal corpus = new BigDecimal(0.00);
        BigDecimal interest = new BigDecimal(0.00);
        BigDecimal corpusInterest = new BigDecimal(0.00);
        for (int i = 0; i < pickCheckList.size(); i++) {
          PickCheckDTO dto = (PickCheckDTO) pickCheckList.get(i);
          person += dto.getPersoncount();
          corpus = corpus.add(dto.getCorpus());
          interest = interest.add(dto.getInterest());
          corpusInterest = corpusInterest.add(dto.getCorpusInterest());
        }
        for (int i = 0; i < list.size(); i++) {
          PickCheckDTO dto = (PickCheckDTO) list.get(i);
          dto.setOperator(pickBS.find_user_realname(pickBS.getpickup_oper(dto
              .getId().toString())));
        }
        af.setPerson(person);
        af.setCorpus(corpus);
        af.setInterest(interest);
        af.setCorpusInterest(corpusInterest);
      }
      pagination.setNrOfElements(pickCheckList.size());
      request.setAttribute("pickCheckAF", af);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return mapping.findForward("pickcheck");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
