package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.math.BigDecimal;
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
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTcFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTcAF;

/**
 * Copy Right Information : 显示损益结转列表的ShowAction Goldsoft Project :
 * CredenceFillinTcShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.24
 */
public class CredenceFillinTcShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinTcShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Object[] obj = new Object[2];
    CredenceFillinTcAF credenceFillinTcAF = new CredenceFillinTcAF();
    List temp_officeList = new ArrayList();
    List officeList1 = new ArrayList();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);

      String bookId = securityInfo.getBookId();

      // 得到核算方式
      int temp_type = securityInfo.getFnSettleType();
      if (temp_type == 1) {
        // 如果为独立核算，取出操作员权限办事处
        List officeList = securityInfo.getOfficeList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          temp_officeList.add(officedto.getOfficeCode());
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode().toString()));
        }
      } else {
        // 如果为同一核算，所有限办事处
        List officeList = securityInfo.getAllOfficeList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          temp_officeList.add(officedto.getOfficeCode());
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode().toString()));
        }
      }
      // 传入到存储过程中的凭证日期
      CredenceFillinTcFindDTO credenceFillinTcFindDTO = null;
      if (pagination.getQueryCriterions().get("credenceFillinTcFindDTO")!=null) {
        credenceFillinTcFindDTO = (CredenceFillinTcFindDTO) pagination
        .getQueryCriterions().get("credenceFillinTcFindDTO");
      }else{
        credenceFillinTcFindDTO = new CredenceFillinTcFindDTO();
      }
      request.getSession().setAttribute("credenceDateStart",
          credenceFillinTcFindDTO.getCredenceDateStart());
      request.getSession().setAttribute("credenceDateEnd",
          credenceFillinTcFindDTO.getCredenceDateEnd());
      obj = credenceFillinBS.findCredenceFillinTcList(pagination,
          temp_officeList, bookId);
      credenceFillinTcAF.setList((List) obj[0]);
      credenceFillinTcAF.setSumCreditMoney((BigDecimal) obj[2]);
      credenceFillinTcAF.setSumDebitMoney((BigDecimal) obj[3]);
      // 列表中全部的待结转信息放入session
      request.getSession().setAttribute("allinfo", (String[]) obj[4]);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 如果为统一核算方式，下拉列表显示为全部办事处，否则显示为操作员办事处
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("credenceFillinTcAF", credenceFillinTcAF);
    return mapping.findForward("to_credencefillintc_show");

  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 20, 1, "f201.subject_code", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
