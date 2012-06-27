package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.bsinterface.ICredenceclearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.form.CredenceclearAF;

public class CredenceclearShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credenceclear.action.CredenceclearShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    String type2 = (String) request.getSession().getAttribute("type2");
    try {
      CredenceclearAF credenceclearAF = new CredenceclearAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List officeList1 = null;
      List credenceCharacterList1 = null;
      List summrayList1 = null;
      List settTypeList1 = null;
      List credenceStList1 = null;
      ICredenceclearBS credenceclearBS = (ICredenceclearBS) BSUtils
          .getBusinessService("credenceclearBS", this, mapping
              .getModuleConfig());
      Object[] obj = credenceclearBS
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
      // 取出状态的枚举放到下拉列表中
      if ("1".equals(type2)) {
        try {
          credenceStList1 = new ArrayList();
          Map credenceStMap = BusiTools.listBusiProperty(BusiConst.CREDSTATE);
          Set entries = credenceStMap.entrySet();
          if (entries != null) {
            Iterator iterator = entries.iterator();
            while (iterator.hasNext()) {
              Map.Entry entry = (Map.Entry) iterator.next();
              Object key = entry.getKey();
              Object value = entry.getValue();
              if (!"复核".equals((String) value)) {
                credenceStList1.add(new org.apache.struts.util.LabelValueBean(
                    value.toString(), key.toString()));
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        try {
          Map credenceStMap = BusiTools.listBusiProperty(BusiConst.CREDSTATE);
          credenceclearAF.setCredenceStMap(credenceStMap);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      String type = (String) request.getSession().getAttribute("type");
      if (!"1".equals(type)) {
        type = "2";
      }
      Pagination pagination = getPagination(CredenceclearShowAC.PAGINATION_KEY,
          request);
      Object[] listObj = new Object[3];
      PaginationUtils.updatePagination(pagination, request);
      // 序时账默认列表type2=1,将type设置为3
      if ("1".equals(type2)) {
        type = "3";
      }
      String type3 = (String) request.getAttribute("type3");
      if (!"1".equals(type3)) {
        listObj = credenceclearBS.findCashDayClearTbList(pagination, type,
            securityInfo);
      }
      List list = new ArrayList();
      list = (List) listObj[0];
      if (list != null) {
        credenceclearAF.setList(list);
        CredencecheckFindDTO credencecheckFindDTO = (CredencecheckFindDTO) listObj[1];
        credenceclearAF.setCredencecheckFindDTO(credencecheckFindDTO);
      }
      CredencecheckFindDTO credenceclearFindDTO = (CredencecheckFindDTO) listObj[1];
      List countList = (List) listObj[2];
      request.getSession().setAttribute("countList", countList);
      request.setAttribute("credenceclearAF", credenceclearAF);
      request.setAttribute("credencecheckFindDTO", credenceclearFindDTO);
      request.setAttribute("officeList1", officeList1);
      request.setAttribute("credenceCharacterList1", credenceCharacterList1);
      request.setAttribute("summrayList1", summrayList1);
      request.setAttribute("settTypeList1", settTypeList1);
      if ("1".equals(type2)) {
        request.setAttribute("credenceStList1", credenceStList1);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    String type3 = (String) request.getSession().getAttribute("type2");
    // 判断如何进入条件成立，是序时账进入
    if ("1".equals(type3)) {
      return mapping.findForward("sequenceacc_show");
    }
    // 判断如何进入条件成立，是序时账进入
    if ("1".equals(type2)) {
      request.getSession().setAttribute("type2", "1");
      return mapping.findForward("sequenceacc_show");

    } else {
      return mapping.findForward("credenceclear_show");
    }
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
