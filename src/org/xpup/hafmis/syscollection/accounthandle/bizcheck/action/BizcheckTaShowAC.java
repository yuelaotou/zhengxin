package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.dto.BizcheckDTO;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 吴洪涛 2007.6.27 @
 */
public class BizcheckTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaShowAC";

  public BigDecimal sumPayMoney = new BigDecimal(0.0);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    BizcheckAF bizcheckAF = null;
    List operList1 = null;
    List bankList1 = null;
    List list = null;
    List bizchectotlallist = null;
    String name=null;
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      pagination.getQueryCriterions().put("SecurityInfo", securityInfo);
      if (securityInfo != null) {
          name = securityInfo.getUserInfo().getUsername();
      }
      pagination.getQueryCriterions().put("name", name);

      if(pagination.getQueryCriterions().get("yga")==null){
        pagination.getQueryCriterions().put("yga", "search");
      }
      
      PaginationUtils.updatePagination(pagination, request);
      IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils.getBusinessService(
          "bizcheckBS", this, mapping.getModuleConfig());
      bizcheckAF = bizcheckBS.findOrgHAFAccountFlowListBydefault(pagination);
      list = bizcheckAF.getList();
      bizchectotlallist = bizcheckAF.getBizchectotlallist();
//      //查询批量复核的单位的办事处是否唯一 开始
//      Iterator iter=bizchectotlallist.iterator();
//      String orgIds="";
//      int count=0;
//      while (iter.hasNext()) {
//        BizcheckDTO element = (BizcheckDTO) iter.next();
//        orgIds+=element.getOrgId()+",";
//      }
//      if(orgIds.length()!=0){
//        orgIds=orgIds.substring(0, orgIds.lastIndexOf(","));
//        count=bizcheckBS.findOfficeCount_wsh(orgIds);
//      }
//    
//      //查询批量复核的单位的办事处是否唯一 结束
      if (list != null && list.size() > 0) {
        bizcheckAF.setListCount("1");
      } else {
        bizcheckAF = new BizcheckAF();
      }
      // 业务状态取后三位1
      Map map = BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
      Map m = new HashMap();
      m.put("3", map.get(new Integer(3)));
      m.put("4", map.get(new Integer(4)));
      m.put("5", map.get(new Integer(5)));

      if (securityInfo != null) {
        List operList = securityInfo.getUserList();
        operList1 = new ArrayList();
        User user = null;
        Iterator itr2 = operList.iterator();
        while (itr2.hasNext()) {
          user = (User) itr2.next();
          operList1.add(new org.apache.struts.util.LabelValueBean(user
              .getUsername(), user.getUsername()));
        }
        request.getSession(true).setAttribute("operList1", operList1);

        List bankList = securityInfo.getCollBankList();
          bankList1 = new ArrayList();
        Userslogincollbank bankdto = null;   
        Iterator itr3 = bankList.iterator();    
        while (itr3.hasNext()) {
          bankdto = (Userslogincollbank) itr3.next();   
          bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("bankList1", bankList1);
      }
      bizcheckAF.setBankList1(bankList1);
      bizcheckAF.setOperList1(operList1);
      bizcheckAF.setMap(m);
      bizcheckAF.setBis_Type(BusiTools
          .listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE_WL));
//      bizcheckAF.setBizStatus(new Integer("3"));
      request.setAttribute("bizcheckAF", bizcheckAF);
      pagination.getQueryCriterions().put("pageList", list);
      pagination.getQueryCriterions().put("bizchectotlallist",
          bizchectotlallist);
//      //办事处个数
//      if(count==1){
//       request.getSession().setAttribute("officeCount",
//            String.valueOf(count));
//      }else{
//        request.getSession().setAttribute("officeCount",
//            "0");
//      }
//      //办事处个数
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "没有您要查询的信息！", false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_bizcheck.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1,
          " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
