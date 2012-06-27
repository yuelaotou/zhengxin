package org.xpup.hafmis.sysfinance.accmng.listacc.action;

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
import org.xpup.hafmis.sysfinance.accmng.listacc.bsinterface.IListacctBS;
import org.xpup.hafmis.sysfinance.accmng.listacc.dto.ListaccDTO;
import org.xpup.hafmis.sysfinance.accmng.listacc.form.ListaccAF;
import org.xpup.hafmis.sysfinance.accmng.subjectbalance.action.SubjectbalanceShowAC;

public class ListaccShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accmng.listacc.action.ListaccShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      ListaccAF listaccAF = new ListaccAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IListacctBS listaccBS = (IListacctBS) BSUtils.getBusinessService(
          "listaccBS", this, mapping.getModuleConfig());
      List totalList = new ArrayList();
      List list = new ArrayList();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          ListaccShowAC.PAGINATION_KEY);
      if (pagination == null) {
        // 点节点过来的
        pagination = getPagination(PAGINATION_KEY, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
        String find = (String) request.getAttribute("find");
        totalList = (List) request.getSession().getAttribute("totalList");
        if (totalList.isEmpty() || find != null) {
          totalList = listaccBS.queryDetailAccList(pagination, securityInfo);
          request.getSession().setAttribute("totalList", totalList);
        }
        if (totalList.size() != 0) {
          int start = 0;
          int page = pagination.getPage();
          String temp = "";
          int num = 0;
          int i;
          int flag = 0;
          for (i = 0; i < totalList.size(); i++,flag++) {
            ListaccDTO dto = (ListaccDTO) totalList.get(i);
            if (i == 0) {
              temp = dto.getSubjectCode();
            } else {
              if (!dto.getSubjectCode().equals(temp)) {
                flag = 0;
                temp = dto.getSubjectCode();
                num++;
              } else{
                if (flag / 10 == 1) {
                  flag = 0;
                  num++;
                }
              }
              if (page == num) {
                break;
              }
            }
          }
          int end = i;
          temp = "";
          num = 0;
          flag = 0;
          if (page > 1) {
            page = page - 1;
            for (i = 0; i < totalList.size(); i++,flag++) {
              ListaccDTO dto = (ListaccDTO) totalList.get(i);
              if (i == 0) {
                temp = dto.getSubjectCode();
              } else {
                if (!dto.getSubjectCode().equals(temp)) {
                  flag = 0;
                  temp = dto.getSubjectCode();
                  num++;
                } else{
                  if (flag / 10 == 1) {
                    flag = 0;
                    num++;
                  }
                }
                if (page == num) {
                  break;
                }
              }
            }
            start = i;
          }
          list = totalList.subList(start, end);
          request.setAttribute("subjectName", ((ListaccDTO) list.get(0))
              .getSubjectCode()
              + "-" + ((ListaccDTO) list.get(0)).getSubjectname());
        }

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
      request.getSession(true).setAttribute("officeList1", officeList1);
      request.setAttribute("list", list);
      request.getSession().setAttribute("totalList", totalList);
      request.setAttribute("listaccAF", listaccAF);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_listacc_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }
}
