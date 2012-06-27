package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.action;

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
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.bsinterface.IPickupreasonBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.form.PickupreasonAF;
import org.xpup.security.common.domain.Userslogincollbank;


/**
 * shiyan
 */
public class ShowPickupreasonListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.action.ShowPickupreasonListAC";

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
      IPickupreasonBS pickupreasonBS = (IPickupreasonBS) BSUtils.getBusinessService("pickupreasonBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
     if(!pagination.getQueryCriterions().isEmpty()){
      List list=pickupreasonBS.findPickupreasonList_sy(pagination, securityInfo);
      PickupreasonAF pickupreasonAF=new PickupreasonAF();
      pickupreasonAF.setList(list);
//     //打印准备数据
     List printList=(List) pagination.getQueryCriterions().get("returnPrintList");
     request.getSession().setAttribute("printList", printList);
//   web显示总的人数
     String totalpeople=(String) pagination.getQueryCriterions().get("totalpeople");
     pickupreasonAF.setTotalpeople(totalpeople);
//   web显示总的金额
     String totalmoney=(String) pagination.getQueryCriterions().get("totalmoney");
     pickupreasonAF.setTotalmoney(totalmoney);
      //页面显示的下拉菜单由权限中取的.
     request.setAttribute("pickupreasonAF", pickupreasonAF);
    } 
     List officelist = securityInfo.getOfficeList();
     List officelist1 = new ArrayList();
     OfficeDto dto = null;
     Iterator itr = officelist.iterator();     
     while (itr.hasNext()) {
       dto = (OfficeDto) itr.next();   
       officelist1.add(new org.apache.struts.util.LabelValueBean(dto.getOfficeName().toString(), dto.getOfficeCode().toString()));
     }
     List bankList = securityInfo.getCollBankList();
     List bankList1 = new ArrayList();
     Userslogincollbank bankdto = null;   
     Iterator itr1 = bankList.iterator();    
     while (itr1.hasNext()) {
       bankdto = (Userslogincollbank) itr1.next();   
       bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
     }
    request.setAttribute("bankList1",bankList1);
    request.setAttribute("officelist1",officelist1);
     }catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_pickupreason_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "pickreason", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
