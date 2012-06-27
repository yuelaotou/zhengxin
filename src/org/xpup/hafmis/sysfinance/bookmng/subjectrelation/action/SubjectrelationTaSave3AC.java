package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;


public class SubjectrelationTaSave3AC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.sure", "sure");
    map.put("button.back", "back");
    map.put("button.allsure", "allsure");
    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String[] rowArray = idAF.getRowArray();
      String calculRelaType = (String) request.getSession().getAttribute(
          "calculRelaType");
      String firstSubjectCode = (String) request.getSession().getAttribute(
          "firstSubjectCode");
      String subjectCode = (String) request.getSession().getAttribute(
          "subjectCode");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
      for (int i = 0; i < rowArray.length; i++) {
        subjectrelationBS.findSubjectrelationInfoExist(firstSubjectCode,
            calculRelaType, rowArray[i].toString(),securityInfo.getBookId());
        subjectrelationBS.findSubejectRelationTaOrgBankExist(rowArray[i]
            .toString(), firstSubjectCode,securityInfo.getBookId());
        subjectrelationBS.findSubejectRelationTaOrgOExistorg_banshichu(rowArray[i]
            .toString(), firstSubjectCode,securityInfo.getBookId());
      }

        subjectrelationBS.subjectrelationTaSaveBank(calculRelaType, rowArray, firstSubjectCode, subjectCode, securityInfo);
  
     
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

  public ActionForward allsure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      
      String calculRelaType = (String) request.getSession().getAttribute(
          "calculRelaType");
      String firstSubjectCode = (String) request.getSession().getAttribute(
          "firstSubjectCode");
      String subjectCode = (String) request.getSession().getAttribute(
          "subjectCode");
      List countList = new ArrayList();
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(SubjectRelationTaPop3AC.PAGINATION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      countList = subjectrelationBS.querySubejectRelationTaPop3CountList(pagination,securityInfo);    
      String valueId[]=new String[countList.size()];
      for(int i=0;i<countList.size();i++){
        BigDecimal temp_id = (BigDecimal) countList.get(i);
        valueId[i]=temp_id.toString();
      }
      for (int i = 0; i < countList.size(); i++) {
        subjectrelationBS.findSubjectrelationInfoExist(firstSubjectCode,
            calculRelaType, valueId[i],securityInfo.getBookId());                      
        subjectrelationBS.findSubejectRelationTaOrgBankExist(valueId[i], firstSubjectCode,securityInfo.getBookId());
       subjectrelationBS.findSubejectRelationTaOrgOExistorg_banshichu(valueId[i], firstSubjectCode,securityInfo.getBookId());
      }
        subjectrelationBS.subjectrelationTaSaveBank(calculRelaType, valueId,
            firstSubjectCode, subjectCode, securityInfo);
     
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
