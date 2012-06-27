package org.xpup.hafmis.sysfinance.accmng.totleacc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.totleacc.bsinterface.ITotleaccBS;
import org.xpup.hafmis.sysfinance.accmng.totleacc.form.TotleaccAF;

public class TotleaccShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accmng.totleacc.action.TotleaccShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      TotleaccAF totleaccAF = new TotleaccAF();
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ITotleaccBS totleaccBS = (ITotleaccBS) BSUtils.getBusinessService("totleaccBS", this, mapping.getModuleConfig());
      String office = (String) pagination.getQueryCriterions().get("office");
      List list=new ArrayList();
      if (!(office == null || office.length() == 0)) {
        list=totleaccBS.querylistaccmngList(pagination,securityInfo);
//        for(int i=0;i<list.size();i++){
//          ListaccDTO dto=(ListaccDTO)list.get(i);
//          System.out.println("| "+dto.getSubjectCode()+" | "+dto.getSubjectname()+" | "+dto.getCredenceDate()+" | "+dto.getCredcharnum()+" | "+dto.getSummay()+" | "+dto.getSettType()+" | "+dto.getOthersubjectname()+" | "+dto.getDebit()+" | "+dto.getCredit()+" | "+dto.getDirtection()+" | "+dto.getMoney());
//        }
      }

      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      // 取出科目级次，显示在下拉菜单中
      List paramValue1 = new ArrayList();
      int paramValue = Integer.parseInt(totleaccBS
          .querySubjectdayreportParamValue(securityInfo.getBookId()));
      for (int i = 1; i <= paramValue; i++) {
        paramValue1.add(new org.apache.struts.util.LabelValueBean(i + "", i
            + ""));
      }
      request.setAttribute("paramValue1", paramValue1);
      request.getSession(true).setAttribute("officeList1", officeList1);

      request.getSession().setAttribute("list", list);
      request.setAttribute("totleaccAF", totleaccAF);
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_totleacc_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "",
          "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }
}
