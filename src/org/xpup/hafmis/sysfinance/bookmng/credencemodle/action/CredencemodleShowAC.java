package org.xpup.hafmis.sysfinance.bookmng.credencemodle.action;

import java.util.HashMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.bsinterface.ICredencemodleBS;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.form.CredencemodleAF;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;

public class CredencemodleShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.bookmng.credencemodle.action.CredencemodleShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      CredencemodleAF credencemodleAF = new CredencemodleAF();
      Pagination pagination = getPagination(CredencemodleShowAC.PAGINATION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List summrayList1 = null;
      List summrayList = null;
      List list = null;
      ICredencemodleBS credencemodleBS = (ICredencemodleBS) BSUtils
          .getBusinessService("credencemodleBS", this, mapping
              .getModuleConfig());
      try {
        Map bizTypeMap = BusiTools.listBusiProperty(BusiConst.FNBUSINESSTYPE);
        bizTypeMap.remove(new Integer(36));
        bizTypeMap.remove(new Integer(37));
        credencemodleAF.setBizTypeMap(bizTypeMap);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        summrayList = credencemodleBS
            .findCredencemodleSummrayList(securityInfo);
        // 从数据库中查出摘要的list，显示在下拉菜单中
        summrayList1 = new ArrayList();
        if (summrayList.size() > 0) {
          BookParameterDTO bookParameterDTO = null;
          Iterator itr1 = summrayList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTO = (BookParameterDTO) itr1.next();
            summrayList1.add(new org.apache.struts.util.LabelValueBean(
                bookParameterDTO.getBookParameterName(), bookParameterDTO
                    .getBookParameterId()));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      list = credencemodleBS.queryCredencemodleList(securityInfo, pagination);
      credencemodleAF.setList(list);
      request.setAttribute("credencemodleAF", credencemodleAF);
      request.setAttribute("summrayList1", summrayList1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_show_credencemodle");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "b.modle_id ", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
