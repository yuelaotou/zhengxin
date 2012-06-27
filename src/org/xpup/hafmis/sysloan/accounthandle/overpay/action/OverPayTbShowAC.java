package org.xpup.hafmis.sysloan.accounthandle.overpay.action;

import java.math.BigDecimal;
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
import org.xpup.hafmis.sysloan.accounthandle.overpay.bsinterface.IOverPayBS;
import org.xpup.hafmis.sysloan.accounthandle.overpay.form.OverPayTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class OverPayTbShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.overpay.action.OverPayTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      OverPayTbAF overPayTbAF=new OverPayTbAF();
      Pagination pagination = getPagination(OverPayTbShowAC.PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      
      List loanbankList1 = null;
      List loanbankList2 = null;
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getDkUserBankList();
        loanbankList1 = new ArrayList();
        loanbankList2 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
          loanbankList2.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      Map bizStMap=BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
      bizStMap.remove(new Integer("1"));
      bizStMap.remove(new Integer("2"));
      bizStMap.remove(new Integer("3"));
      overPayTbAF.getOverPayTbFindDTO().setBizStMap(bizStMap);
      
      IOverPayBS overPayBS = (IOverPayBS) BSUtils
      .getBusinessService("overPayBS", this, mapping.getModuleConfig());
      List list=new ArrayList();
      Object[] obj=new Object[2];
      obj=overPayBS.findOverPayTbList(pagination,loanbankList2);
      list=(List)obj[0];
      BigDecimal occurMoneySum=new BigDecimal(0.00);
      occurMoneySum=new BigDecimal(obj[1].toString());
      if(list.size()>0){
        overPayTbAF.setList(list);
      }
      overPayTbAF.setOccurMoneySum(occurMoneySum);
      overPayTbAF.getOverPayTbFindDTO().reset();
      request.setAttribute("overPayTbAF", overPayTbAF);
      request.setAttribute("loanbankList1", loanbankList1);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_overpaytb_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 10, 1, "pl202.flow_head_id", "DESC",
           map);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
