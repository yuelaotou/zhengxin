package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;

public class SubjectrelationTaSave4AC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.sure", "sure");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
//      String calculRelaType = (String) request.getSession().getAttribute(
//          "calculRelaType");
      Pagination pagination = getPagination(SubjectRelationTaPop3AC.PAGINATION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      String firstSubjectCode = (String) request.getSession().getAttribute(
          "firstSubjectCode");
      String subjectCode = (String) request.getSession().getAttribute(
          "subjectCode");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");   
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
      List officeList = securityInfo.getOfficeList();
      List bankList=subjectrelationBS.querySubejectRelationTaPop2CountList(securityInfo);
      List orgList=subjectrelationBS.querySubejectRelationTaPop3CountList(pagination,securityInfo);
      subjectrelationBS.findSubjectrelationAllOExist(officeList,firstSubjectCode,securityInfo.getBookId());
      subjectrelationBS.findSubjectrelationAllBankExist(bankList,firstSubjectCode,securityInfo.getBookId());
      subjectrelationBS.findSubjectrelationAllOrgExist(orgList,firstSubjectCode,securityInfo.getBookId());
      String valueId[]=new String[officeList.size()];
     for(int i = 0; i < officeList.size(); i++){
       OfficeDto organizationUnit = (OfficeDto) officeList
       .get(i);
       valueId[i]= organizationUnit.getOfficeCode();
     }
        subjectrelationBS.subjectrelationTaSaveBank("3",
            valueId, firstSubjectCode, subjectCode,
            securityInfo);
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      request.setAttribute("count1", "1");
      return mapping.findForward("to_save_subjectrelationta");
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    request.setAttribute("count1", "1");
    return mapping.findForward("to_save_subjectrelationta");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("to_save_subjectrelationta");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "", "orgId", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}