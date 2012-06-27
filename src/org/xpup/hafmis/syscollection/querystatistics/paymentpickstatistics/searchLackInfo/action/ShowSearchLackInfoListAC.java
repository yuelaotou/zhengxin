package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface.ISearchLackInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.form.SearchLackInfoListAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ShowSearchLackInfoListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    SearchLackInfoListAF af = new SearchLackInfoListAF();
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

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

      Map natureOfUnits = BusiTools.listBusiProperty(BusiConst.NATUREOFUNITS);
      af.setNatureOfUnits_1(natureOfUnits);//单位性质
      Map authorities_1 = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      af.setAuthorities_1(authorities_1);//主管部门
      Map inArea = BusiTools.listBusiProperty(BusiConst.INAREA);
      af.setInArea_1(inArea);//所在地区
      
      ISearchLackInfoBS searchLackInfoBS = (ISearchLackInfoBS) BSUtils
          .getBusinessService("searchLackInfoBS", this, mapping.getModuleConfig());

      HttpSession session = request.getSession();
      String firstin = (String) session.getAttribute("firstin");

      List list = null;
      SearchLackInfoHeadDTO dto = new SearchLackInfoHeadDTO();
      if (!firstin.equals("1")) {
        
        if(!pagination.getQueryCriterions().isEmpty()){
          list = searchLackInfoBS.findSearchLackInfoAllByCriterions(pagination,securityInfo);
          
          if(list!=null&&list.size()!=0){
            List listcount = (List)pagination.getQueryCriterions().get("listcount");
            dto = searchLackInfoBS.findSearchLackInfoListHead(listcount);// 查询合计项
          }
          af.setList(list);
          
        }
      } else {
        session.setAttribute("firstin", "2");
        af.setList(null);
      }
      request.setAttribute("searchLackInfoListAF", af);
      //request.setAttribute("List", list);
      request.setAttribute("searchLackInfoHeadDTO", dto);
    
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_searchLackInfo_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa305.org_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
