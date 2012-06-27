package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationInfoTaDTO;

public class SubjectRelationTaFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("Cache-Control", "no-cache");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String subjectCode = (String) request.getParameter("subjectCode");
      Integer count = new Integer(0);
      if (subjectCode != null && !"".equals(subjectCode.trim())) {
        subjectCode = (String) request.getParameter("subjectCode");
        request.getSession().setAttribute("subjectCode", subjectCode);
      }
      String text = null;
      SubjectrelationInfoTaDTO subjectrelationInfoTaDTO = new SubjectrelationInfoTaDTO();
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
      count = subjectrelationBS.findSubjectrelationExist(subjectCode,securityInfo);
      subjectrelationBS.findSubjectRelationFirstCode(subjectCode,securityInfo);
      if (!"0".equals(count.toString())) {
        subjectrelationInfoTaDTO = subjectrelationBS
            .findSubejectRelationTaInfo(subjectCode,securityInfo.getBookId());
        text = "displays('" + subjectrelationInfoTaDTO.getSubjectCode() + "','"
            + subjectrelationInfoTaDTO.getSubjectName() + "'" + ",'"
            + subjectrelationInfoTaDTO.getBalanceDirection() + "','"
            + subjectrelationInfoTaDTO.getBalance() + "'" + ",'"
            + subjectrelationInfoTaDTO.getFirstSubjectCode() + "'" + ",'"
            + subjectrelationInfoTaDTO.getCalculRelaType() + "')";
        request.getSession().setAttribute("subjectCode",
            subjectrelationInfoTaDTO.getSubjectCode());
        request.getSession().setAttribute("subjectName",
            subjectrelationInfoTaDTO.getSubjectName());
        request.getSession().setAttribute("balanceDirection",
            subjectrelationInfoTaDTO.getBalanceDirection());
        request.getSession().setAttribute("balance",
            subjectrelationInfoTaDTO.getBalance());
        request.getSession().setAttribute("firstSubjectCode",
            subjectrelationInfoTaDTO.getFirstSubjectCode());
        request.getSession().setAttribute("calculRelaType",
            subjectrelationInfoTaDTO.getCalculRelaType());
        request.getSession().setAttribute("count", count.toString());
        response.getWriter().write(text);
        response.getWriter().close();
      } else {
        subjectrelationBS.findSubjectrelationParentId(subjectCode,securityInfo);
        subjectrelationInfoTaDTO = subjectrelationBS
            .findSubejectRelationTa1Info(subjectCode,securityInfo.getBookId());
        text = "displays1('" + subjectrelationInfoTaDTO.getSubjectCode()
            + "','" + subjectrelationInfoTaDTO.getSubjectName() + "'" + ",'"
            + subjectrelationInfoTaDTO.getBalanceDirection() + "','"
            + subjectrelationInfoTaDTO.getBalance() + "'" + ",'"
            + subjectrelationInfoTaDTO.getFirstSubjectCode() + "')";
        request.getSession().setAttribute("subjectCode",
            subjectrelationInfoTaDTO.getSubjectCode());
        request.getSession().setAttribute("subjectName",
            subjectrelationInfoTaDTO.getSubjectName());
        request.getSession().setAttribute("balanceDirection",
            subjectrelationInfoTaDTO.getBalanceDirection());
        request.getSession().setAttribute("balance",
            subjectrelationInfoTaDTO.getBalance());
        request.getSession().setAttribute("firstSubjectCode",
            subjectrelationInfoTaDTO.getFirstSubjectCode());
        request.getSession().removeAttribute("calculRelaType");
        request.getSession().setAttribute("count", count.toString());
        response.getWriter().write(text);
        response.getWriter().close();
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      String text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
