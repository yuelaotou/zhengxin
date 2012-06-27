package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.io.Serializable;
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
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;

/**
 * shiyan
 */
public class ShowTraninListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      List list = null;
      String notenumber = "";
      TraninAF traninAF = new TraninAF();
      String orgId = (String) request.getSession().getAttribute("orgId");
      String buttonMagssage = (String) request.getSession().getAttribute(
          "buttonMagssage");
      if (orgId != null && !orgId.equals("")) {
        pagination.getQueryCriterions().put("tranInHead.InOrg.id", orgId);
      }
      if (buttonMagssage != null && !buttonMagssage.equals("")) {
        request.setAttribute("buttonMagssage", buttonMagssage);
        traninAF.setButtonMagssage(buttonMagssage);
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      if (pagination.getQueryCriterions().isEmpty() != true) {
        list = traninBS.findTraninListByCriterions(pagination, securityInfo);
        String inOrgId = (String) pagination.getQueryCriterions().get(
            "tranInHead.InOrg.id");
        if (list != null && !list.isEmpty()) {
          traninAF.setLoadsMassage("hi");
          notenumber = (String) pagination.getQueryCriterions().get(
              "notenumber");
          traninAF.setList(list);
        } else {
          List tranInHeadList = traninBS.queryTranInHeandInOrgId(inOrgId, "5");
          if (!tranInHeadList.equals("") && !tranInHeadList.isEmpty()) {
            TranInHead tranInHead = (TranInHead) tranInHeadList.get(0);
            String tranInHeadId = tranInHead.getId().toString();
            pagination.getQueryCriterions().put("tranInHeadById", tranInHeadId);
            traninAF.setLoadsMassage("noHi");
          } else {
            // String noteNum = request.getParameter("noteNum");
            traninAF.setLoadsMassage("nohi");
          }
        }
        pagination.getQueryCriterions().put("pageList", list);
        String tranInHeadById = (String) pagination.getQueryCriterions().get(
            "tranInHeadById");

        if (tranInHeadById != null && !tranInHeadById.equals("")) {
          Pagination pagination1 = traninBS.countTraninListAll(pagination,
              securityInfo);
          Integer traninPeople = (Integer) pagination1.getQueryCriterions()
              .get("traninPeople");
          BigDecimal sumBalanceAll = (BigDecimal) pagination1
              .getQueryCriterions().get("sumBalanceAll");
          BigDecimal sumInterestAll = (BigDecimal) pagination1
              .getQueryCriterions().get("sumInterestAll");
          BigDecimal sumTraninAll = (BigDecimal) pagination1
              .getQueryCriterions().get("sumTraninAll");
          traninAF.setTraninPeople(traninPeople.intValue());
          traninAF.setSumBalanceAll(sumBalanceAll);
          traninAF.setSumInterestAll(sumInterestAll);
          traninAF.setSumTraninAll(sumTraninAll);
        }
        String inOrgName = (String) pagination.getQueryCriterions().get(
            "inOrgName");
        String noteNum = (String) pagination.getQueryCriterions()
            .get("noteNum");

        if (notenumber != null && !notenumber.equals("")) {
          traninAF.setNoteNum(notenumber);
        } else {
          if (noteNum == null || noteNum.equals("")) {
            traninAF.setNoteNum(securityInfo.getUserInfo().getBizDate()
                + traninBS.queryNoteNum());
          } else {
            traninAF.setNoteNum(noteNum);
          }
        }
        traninAF.setInOrgId(BusiTools.convertTenNumber(inOrgId));
        traninAF.setInOrgName(inOrgName);
        traninAF.setTranInHeadById(tranInHeadById);
      } else {
        if (orgId != null && !orgId.equals("")) {
          if (traninAF.getNoteNum() == null || traninAF.getNoteNum().equals("")) {
            traninAF.setNoteNum(securityInfo.getUserInfo().getBizDate()
                + traninBS.queryNoteNum());
          }
        }
      }
      // 判断是什么提取状态
      String ispickupType = (String) pagination.getQueryCriterions().get(
          "istype");
      traninAF.setIsType(ispickupType);
      request.setAttribute("traninAF", traninAF);
      traninAF.reset(mapping, request);
      // 20071213
      // 判断是单位版还是中心版
      String isType = securityInfo.getIsOrgEdition() + "";
      request.getSession().setAttribute("isorgOrCenter", isType);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_tranin_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "tranInTail.empId", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
