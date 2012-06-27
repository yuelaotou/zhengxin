package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

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
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.bsinterface.IChgpersonOrgBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonOrgHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from.ChgpersonOrgListAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ShowChgpersonOrgListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonOrgListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    ChgpersonOrgListAF af = (ChgpersonOrgListAF) form;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      Map chgMap = BusiTools.listBusiProperty(BusiConst.CHGTYPESTATUS);
      af.setMap(chgMap);

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

      IChgpersonOrgBS chgpersonOrgBS = (IChgpersonOrgBS) BSUtils
          .getBusinessService("chgpersonOrgBS", this, mapping.getModuleConfig());

      HttpSession session = request.getSession();
      String firstin = (String) session.getAttribute("firstin");

      List list = null;
      ChgpersonOrgHeadDTO dto = new ChgpersonOrgHeadDTO();

      if (!firstin.equals("1")) {
        if(!pagination.getQueryCriterions().isEmpty()){
          list = chgpersonOrgBS.findChgpersonOrgListByCriterions(pagination,securityInfo);
          //dto = chgpersonOrgBS.findChgpersonOrgHeadByCriterions(pagination,securityInfo);
          af.setList(list);
          
          //查询全部列表信息，打印中用到// 查询合计项
          ChgHeadDTO chgHeadDTO=chgpersonOrgBS.queryChgpersonOrgListAll(pagination,securityInfo);
          dto=chgHeadDTO.getChgpersonOrgHeadDTO();
          session.setAttribute("cellList", chgHeadDTO.getList());
        }
      } else {
        session.setAttribute("firstin", "2");
        af.setList(null);
      }
      
      request.setAttribute("chgpersonOrgListAF", af);
      request.setAttribute("chgpersonOrgHeadDTO", dto);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgpersonorg_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPersonHead.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
