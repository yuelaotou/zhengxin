package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.bsinterface.IYearclearstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.form.YearclearstatisticsListAF;

public class ShowYearclearEmpAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearEmpAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    try {
      /**
       * ·ÖÒ³
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      YearclearstatisticsListAF af = (YearclearstatisticsListAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IYearclearstatisticsBS yearclearstatisticsBS = (IYearclearstatisticsBS) BSUtils
          .getBusinessService("yearclearstatisticsBS", this, mapping.getModuleConfig());

      String headid=(String)request.getParameter("headid");
      String orgid=(String)request.getParameter("orgid");
      String empcode=(String)request.getParameter("empcode");
      String oldpreblance=(String)request.getParameter("oldpreblance");
      String oldcurblance=(String)request.getParameter("oldcurblance");
      String oldblance=new BigDecimal(oldpreblance).add(new BigDecimal(oldcurblance)).toString();
      String preinterest=(String)request.getParameter("preinterest");
      String curinterest=(String)request.getParameter("curinterest");
      String interest=new BigDecimal(preinterest).add(new BigDecimal(curinterest)).toString();
      String blance=(String)request.getParameter("blance");
      
      pagination.getQueryCriterions().put("headid", headid);
      pagination.getQueryCriterions().put("orgid", orgid);
      pagination.getQueryCriterions().put("empcode", empcode);

      List list = null;
      list = yearclearstatisticsBS.findYearclearEmpByCriterions(pagination,securityInfo);
      
      af.setOldblance(oldblance);
      af.setInterest(interest);
      af.setBlance(blance);
      YearclearstatisticsHeadDTO dto = (YearclearstatisticsHeadDTO)list.get(0);
      af.setOrgId(dto.getOrgcode().toString());
      af.setEmpId(dto.getEmpcode().toString());
      af.setOrgName(dto.getOrgname());
      af.setEmpName(dto.getEmpname());
      af.setList(list);

      request.getSession().setAttribute("oldblance", oldblance);
      request.getSession().setAttribute("interest", interest);
      request.getSession().setAttribute("blance", blance);
      request.getSession().setAttribute("list", list);
      request.setAttribute("yearclearstatisticsListAF", af);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_yearclearEmp_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
