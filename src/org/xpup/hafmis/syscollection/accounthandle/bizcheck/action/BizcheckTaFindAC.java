package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckAF;

//单位的查询
public class BizcheckTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      BizcheckAF bizcheckAF = (BizcheckAF) form;
      String orgId = bizcheckAF.getOrgId();
      if (orgId != null && orgId != "") {
        if (orgId.length() == 10) {
          orgId = orgId.substring(1, orgId.length());
        }
      }
      String noteNum = bizcheckAF.getNoteNum();
      String docNum = bizcheckAF.getDocNum();
      String orgName = bizcheckAF.getOrgName();
      String operator = bizcheckAF.getOperator();
      String collectionBank = bizcheckAF.getCollectionBank();
      String bizStatus = bizcheckAF.getBizStatus().toString();
      if (bizStatus.equals("0")) {
        bizStatus = "";
      }
      String startDate = bizcheckAF.getStartDate();
      String endDate = bizcheckAF.getEndDate();
      String type = bizcheckAF.getType();
      String biz_Type = bizcheckAF.getBiz_Type();
      Pagination pagination = new Pagination(0, 10, 1,
          " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ", "DESC",
          new HashMap(0));
      String paginationKey = getPaginationKey();
      pagination.getQueryCriterions().put("orgId", orgId);
      pagination.getQueryCriterions().put("noteNum", noteNum);
      pagination.getQueryCriterions().put("docNum", docNum);

      pagination.getQueryCriterions().put("orgName", orgName);
      pagination.getQueryCriterions().put("operator", operator);
      pagination.getQueryCriterions().put("collectionBank", collectionBank);
      pagination.getQueryCriterions().put("bizStatus", bizStatus);
      pagination.getQueryCriterions().put("startDate", startDate);
      pagination.getQueryCriterions().put("endDate", endDate);
      pagination.getQueryCriterions().put("type", type);
      pagination.getQueryCriterions().put("biz_Type", biz_Type);
      pagination.getQueryCriterions().put("yga", "find");
      request.getSession().setAttribute(paginationKey, pagination);
      modifyPagination(pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_bizcheckTaShowAC.do");
  }

  protected String getPaginationKey() {
    return BizcheckTaShowAC.PAGINATION_KEY;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
