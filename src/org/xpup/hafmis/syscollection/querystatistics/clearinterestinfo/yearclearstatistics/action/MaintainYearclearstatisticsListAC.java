package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.bsinterface.IYearclearstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;

public class MaintainYearclearstatisticsListAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.MaintainYearclearstatisticsListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.print.doc", "printlist");
    map.put("button.continuum.print", "continuum");
    return map;
  }

  public ActionForward continuum(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String beg = request.getParameter("beg");
    String end = request.getParameter("end");
    Pagination pagination = getPagination(
        ShowYearclearstatisticsListAC.PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);

    IYearclearstatisticsBS yearclearstatisticsBS = (IYearclearstatisticsBS) BSUtils
        .getBusinessService("yearclearstatisticsBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");

    List listcount = (List) pagination.getQueryCriterions().get("listcount");
    YearclearstatisticsHeadDTO dto = (YearclearstatisticsHeadDTO) listcount
        .get(0);
    String bankName = "";
    String orgId = dto.getOrgcode().toString();
    if (orgId != null) {
      bankName = yearclearstatisticsBS.findOrg(orgId);
    }
    if (bankName != null && !"".equals(bankName)) {
      request.setAttribute("bankName", bankName);
    }
    List printList = new ArrayList();
    if (beg != null && end != null && !beg.equals("") && !end.equals("")) {
      if (Integer.parseInt(end) > listcount.size()) {
        printList = listcount.subList(Integer.parseInt(beg)-1, listcount.size());
      } else {
        printList = listcount.subList(Integer.parseInt(beg)-1, Integer
            .parseInt(end));
      }
      request.setAttribute("printList", printList);
    } else {
      request.setAttribute("printList", listcount);
    }

    String userName = "";
    String name = yearclearstatisticsBS.queryMakerPara();
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    }
    request.setAttribute("userName", userName);
    request.setAttribute("setDate", securityInfo.getUserInfo().getBizDate());
    return mapping.findForward("yearclearlist_cell");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = getPagination(
        ShowYearclearstatisticsListAC.PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);

    List listcount = (List) pagination.getQueryCriterions().get("listcount");
    HttpSession session = request.getSession();
    session.setAttribute("listcount", listcount);

    return mapping.findForward("to_yearclearstatistics_report");
  }

  public ActionForward printlist(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    IdAF af = (IdAF) form;
    String headId = af.getId().toString();
    IYearclearstatisticsBS yearclearstatisticsBS = (IYearclearstatisticsBS) BSUtils
        .getBusinessService("yearclearstatisticsBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    pagination.getQueryCriterions().put("headId", headId);
    List list = null;
    list = yearclearstatisticsBS.findYearclearstatisticsAllByCriterions(
        pagination, securityInfo);
    YearclearstatisticsHeadDTO dto = (YearclearstatisticsHeadDTO) list.get(0);
    String bankName = "";
    String orgId = dto.getOrgcode().toString();
    if (orgId != null) {
      bankName = yearclearstatisticsBS.findOrg(orgId);
    }
    if (bankName != null && !"".equals(bankName)) {
      dto.setBank(bankName);
    }
    if (dto != null) {
      dto.setOldcurblance(dto.getOldcurblance().add(dto.getOldpreblance()));
      dto.setPreinterest(dto.getPreinterest().add(dto.getCurinterest()));
    }
    String clearPerson = yearclearstatisticsBS.clearperson(dto.getSet_headid());
    dto.setSet_headid(clearPerson);
    String userName = "";
    String name = yearclearstatisticsBS.queryMakerPara();
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    }
    request.setAttribute("userName", userName);
    request.setAttribute("setDate", securityInfo.getUserInfo().getBizDate());
    request.setAttribute("YearclearstatisticsHeadDTO", dto);
    return mapping.findForward("to_interest_report");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa001.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
