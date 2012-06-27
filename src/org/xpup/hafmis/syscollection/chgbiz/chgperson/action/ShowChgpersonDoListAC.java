package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonDoListDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;

/**
 * @author 王玲 2007-6-27
 */
public class ShowChgpersonDoListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonDoListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    ChgpersonDoListAF chgpersonDoListAF = new ChgpersonDoListAF();
    ChgpersonDoListDTO dto = new ChgpersonDoListDTO();

    try {

      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      HttpSession session = request.getSession();
      String orgIDByChgPersonHeadID = null;
      orgIDByChgPersonHeadID = (String) session
          .getAttribute("orgIDByChgPersonHeadID");
      if (orgIDByChgPersonHeadID != null) {
        pagination.getQueryCriterions().put("id", orgIDByChgPersonHeadID);
        dto.setFlag1("2");// 修改,页面信息只读
      } else {
        dto.setFlag1("1");
      }
      if (pagination.getQueryCriterions().get("id") == null
          || pagination.getQueryCriterions().get("id").equals("")) {
        request.setAttribute("flag", dto.getFlag1());
        return mapping.findForward("to_chgpersonDo_list");
      }
      // 后添加，判断按钮
      ChgPersonTail chgPersonTail = new ChgPersonTail();
      List list = chgpersonDoBS.findChgpersonDoListByCriterions(pagination,
          securityInfo);
      if (list.size() > 0) {
        chgPersonTail = (ChgPersonTail) list.get(0);
      }
      String changeHeadId = null;
      changeHeadId = (String) session.getAttribute("changeHeadId");
      ChgPersonHeadFormule chgPersonHeadFormule = chgpersonDoBS
          .findChgpersonHeadByCriterions(pagination, securityInfo, changeHeadId);// 查询合计项

      session.removeAttribute("changeHeadId");
      dto.setList(list);

      dto.setId(BusiTools.convertTenNumber((String) pagination.getQueryCriterions().get("id")));
      dto.setName((String) pagination.getQueryCriterions().get("name"));
      String TEMP_date = (String) pagination.getQueryCriterions().get("date");
      dto.setDate(TEMP_date);

      if (list.size() == 0) {// 列表中没有记录，批量导入、批量导出、添加可用
        dto.setType("2");
      } else if (list.size() != 0) {// 列表有记录：批量导入、批量导出禁用
        dto.setType("3");
      }
      int typetem = securityInfo.getIsOrgEdition();
      request.setAttribute("typetem", typetem + "");
      request.setAttribute("flag", dto.getFlag1());
      request.setAttribute("chgpersonDoListAF", dto);
      if (chgPersonHeadFormule != null) {
        chgPersonHeadFormule.setSumChgPerson(new Integer(chgPersonHeadFormule
            .getBeforeChgperson().intValue()
            + chgPersonHeadFormule.getInsChgperson().intValue()
            - chgPersonHeadFormule.getMulChgperson().intValue()));
      }
      request.setAttribute("chgpersonAF", chgPersonHeadFormule);
      // 后添加，判断按钮
      if (!"".equals(chgPersonTail.getChgPersonHead().getTemp_pick())) {
        request.setAttribute("statetype", chgPersonTail.getChgPersonHead()
            .getTemp_pick());
      }

      // 存单位id
      String id = (String) pagination.getQueryCriterions().get("id");
      session.setAttribute("orgID", BusiTools.convertTenNumber(id));
      session.setAttribute("chgDate", TEMP_date);

      chgpersonDoListAF.reset(mapping, request);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgpersonDo_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPersonTail.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
