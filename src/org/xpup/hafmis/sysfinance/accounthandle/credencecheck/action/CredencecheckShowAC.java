package org.xpup.hafmis.sysfinance.accounthandle.credencecheck.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.bsinterface.ICredencecheckBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.form.CredencecheckAF;

public class CredencecheckShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credencecheck.action.CredencecheckShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      CredencecheckAF credencecheckAF = new CredencecheckAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List officeList1 = null;
      List credenceCharacterList1 = null;
      List summrayList1 = null;
      List settTypeList1 = null;
      ICredencecheckBS credencecheckBS = (ICredencecheckBS) BSUtils
          .getBusinessService("credencecheckBS", this, mapping
              .getModuleConfig());
      Object[] obj = credencecheckBS
          .findCredenceCharacterList(securityInfo, "");
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
        // 从数据库中查出摘要的list，显示在下拉菜单中
        summrayList1 = new ArrayList();
        List summrayList = (List) obj[1];
        if (summrayList.size() > 0) {
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = summrayList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            summrayList1.add(new org.apache.struts.util.LabelValueBean(
                bookParameterDTODTO.getBookParameterName(), bookParameterDTODTO
                    .getBookParameterId()));
          }
        }
        // 从数据库中查出结算方式的list，显示在下拉菜单中
        settTypeList1 = new ArrayList();
        List settTypeList = (List) obj[2];
        if (settTypeList.size() > 0) {
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = settTypeList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            settTypeList1.add(new org.apache.struts.util.LabelValueBean(
                bookParameterDTODTO.getBookParameterName(), bookParameterDTODTO
                    .getBookParameterId()));
          }
        }
      }
      // 取出状态的枚举放到下拉列表中
      try {
        Map credenceStMap = BusiTools.listBusiProperty(BusiConst.CREDSTATE);
        credencecheckAF.setCredenceStMap(credenceStMap);
      } catch (Exception e) {
        e.printStackTrace();
      }
      String type = (String) request.getSession().getAttribute("type");
      if (!"1".equals(type)) {
        type = "0";
      }
      Pagination pagination = getPagination(CredencecheckShowAC.PAGINATION_KEY,
          request);
      Object[] listObj = new Object[3];
      PaginationUtils.updatePagination(pagination, request);
      listObj = credencecheckBS.findCashDayClearTbList(pagination, type,
          securityInfo);
      List list = new ArrayList();
      list = (List) listObj[0];
      if (list.size() > 0) {
        credencecheckAF.setList(list);
        CredencecheckFindDTO credencecheckFindDTO = (CredencecheckFindDTO) listObj[1];
        credencecheckAF.setCredencecheckFindDTO(credencecheckFindDTO);
      }
      CredencecheckFindDTO credencecheckFindDTO = (CredencecheckFindDTO) listObj[1];
      List countList = (List) listObj[2];
      request.getSession().setAttribute("countList", countList);
      request.setAttribute("credencecheckAF", credencecheckAF);
      request.setAttribute("credencecheckFindDTO", credencecheckFindDTO);
      request.setAttribute("officeList1", officeList1);
      request.setAttribute("credenceCharacterList1", credenceCharacterList1);
      request.setAttribute("summrayList1", summrayList1);
      request.setAttribute("settTypeList1", settTypeList1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("credencecheck_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 10, 1,
          "fn201.credence_date,to_number(fn201.credence_num)", "", map);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
