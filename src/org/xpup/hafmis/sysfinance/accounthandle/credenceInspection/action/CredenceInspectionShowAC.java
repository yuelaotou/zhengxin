package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.bsinterface.ICredenceInspectionBS;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.form.CredenceInspectionAF;

public class CredenceInspectionShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.action.CredenceInspectionShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      CredenceInspectionAF credenceInspectionAF = new CredenceInspectionAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List officeList1 = null;
      List credenceCharacterList1 = null;
      try {
        // 取出用户权限办事处,显示在下拉菜单中
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      ICredenceInspectionBS credenceInspectionBS = (ICredenceInspectionBS) BSUtils
          .getBusinessService("credenceInspectionBS", this, mapping
              .getModuleConfig());
      Object[] obj = credenceInspectionBS.findCredenceCharacterList(
          securityInfo, "");
      if (obj.length > 0) {
        // 从数据库中查出凭证字的list，显示在下拉菜单中
        credenceCharacterList1 = new ArrayList();
        List credenceCharacterList = (List) obj[0];
        if (credenceCharacterList.size() > 0) {
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = credenceCharacterList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            credenceCharacterList1
                .add(new org.apache.struts.util.LabelValueBean(
                    bookParameterDTODTO.getBookParameterName(),
                    bookParameterDTODTO.getBookParameterId()));
          }
        }
      }
      request.setAttribute("credenceCharacterList1", credenceCharacterList1);
      request.setAttribute("officeList1", officeList1);
      Pagination pagination = getPagination(
          CredenceInspectionShowAC.PAGINATION_KEY, request);
      CredenceInspectionFindDTO credenceInspectionFindDTO = new CredenceInspectionFindDTO();
      credenceInspectionFindDTO.reset();
      request.setAttribute("credenceInspectionFindDTO",
          credenceInspectionFindDTO);
      if(!"1".equals((String)request.getAttribute("type"))){
        PaginationUtils.updatePagination(pagination, request);
        credenceInspectionFindDTO = credenceInspectionBS
            .findCredenceInspectionFindDTO(pagination, securityInfo);
        if("0".equals(credenceInspectionFindDTO.getCount())){
          throw new BusinessException("输入日期范围内没有符合条件的记录！");         
        }
      }
      credenceInspectionAF
          .setCredenceInspectionFindDTO(credenceInspectionFindDTO);
      credenceInspectionAF.getCredenceInspectionFindDTO().reset();
      request.setAttribute("credenceInspectionAF", credenceInspectionAF);
      request.setAttribute("credenceInspectionFindDTO",
          credenceInspectionFindDTO);
    } 
    catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_credenceInspection_show");
    }catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credenceInspection_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 0, 0, "", "", map);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
