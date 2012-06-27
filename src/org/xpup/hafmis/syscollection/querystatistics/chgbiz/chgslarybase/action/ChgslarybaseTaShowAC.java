package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentSalaryBase;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseListAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 吴洪涛 
 */
public class ChgslarybaseTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.action.ChgslarybaseTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ChgslarybaseListAF chgslarybaseListAF = null;
    List list = null;
    List officeList1 =null;
    List bankList1=null;
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    pagination.getQueryCriterions().put("SecurityInfo", securityInfo);
    try {
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils
          .getBusinessService("chgslarybaseBS", this, mapping.getModuleConfig());
      chgslarybaseListAF = chgslarybaseBS.findChgslarybaseList(pagination);

      if (chgslarybaseListAF == null) {
        chgslarybaseListAF = new ChgslarybaseListAF();
      }
      // 是否显示按钮查询
      Map map = BusiTools.listBusiProperty(BusiConst.CHGTYPESTATUS);
      chgslarybaseListAF.setMap(map);
      request.setAttribute("chgslarybaseListAF", chgslarybaseListAF);
      list = chgslarybaseListAF.getList();
      List totalorgChgPaymentSalaryBase = chgslarybaseListAF.getTotalorgChgPaymentSalaryBase();
      
      if(securityInfo!=null){
      
        List bankList = securityInfo.getCollBankList();
          bankList1 = new ArrayList();
        Userslogincollbank bankdto = null;   
        Iterator itr2 = bankList.iterator();    
        while (itr2.hasNext()) {
          bankdto = (Userslogincollbank) itr2.next();   
          bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("bankList1", bankList1);

      // 付云峰修改：取办事处使用getOfficeList()方法
      List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);
      
      }
      
      if (list != null && list.size() > 0) {
        chgslarybaseListAF.setListCount("1");
      }
      chgslarybaseListAF.setBankList1(bankList1);
      chgslarybaseListAF.setOfficeList1(officeList1);
      request.setAttribute("chgslarybaseListAF", chgslarybaseListAF);
      pagination.getQueryCriterions().put("pageList", list);
      HttpSession session = request.getSession();
      session.setAttribute("cellList", totalorgChgPaymentSalaryBase);
      //pagination.getQueryCriterions().put();
      
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgslarybase_list.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, " a1.id,a2.chg_month ", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
