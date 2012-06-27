package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.bsinterface.IYearclearstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.form.YearclearstatisticsListAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ShowYearclearstatisticsListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearstatisticsListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      YearclearstatisticsListAF af = (YearclearstatisticsListAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);

      List bankList = securityInfo.getCollBankList();
      List bankList1 = new ArrayList();
      Userslogincollbank bankdto = null;   
      Iterator itr2 = bankList.iterator();    
      while (itr2.hasNext()) {
        bankdto = (Userslogincollbank) itr2.next();   
        bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("bankList1", bankList1);

      
      IYearclearstatisticsBS yearclearstatisticsBS = (IYearclearstatisticsBS) BSUtils
          .getBusinessService("yearclearstatisticsBS", this, mapping.getModuleConfig());

      HttpSession session = request.getSession();
      String firstin = (String) session.getAttribute("firstin");

      List list = null;
      List listcount = null;
      YearclearstatisticsHeadDTO dto = new YearclearstatisticsHeadDTO();

      if (!firstin.equals("1")) {
        if(!pagination.getQueryCriterions().isEmpty()){
          list = yearclearstatisticsBS.findYearclearstatisticsAllByCriterions(pagination,securityInfo);
          
          if(list!=null&&list.size()!=0){
            listcount = (List)pagination.getQueryCriterions().get("listcount");
            dto = yearclearstatisticsBS.findYearclearstatisticsListHead(listcount);// 查询合计项
          }
          af.setList(list);
        }
      } else {
        session.setAttribute("firstin", "2");
        af.setList(null);
      }
      request.setAttribute("yearclearstatisticsListAF", af);
      request.setAttribute("yearclearstatisticsHeadDTO", dto);
      request.setAttribute("listcount", listcount);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_yearclearstatistics_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa001.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
