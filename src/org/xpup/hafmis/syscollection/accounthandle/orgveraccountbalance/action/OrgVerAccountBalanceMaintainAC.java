/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceMaintainAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-21
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.bsinterface.IOrgVerAccountBalanceBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IDocNumBS;

public class OrgVerAccountBalanceMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.carryforword.balance", "accBalance");
    return map;
  }

  public ActionForward accBalance(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    String docNum = "";
    try {
      IdAF f = (IdAF) form;
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          OrgVerAccountBalanceShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getBizDate();// 会计日期
      IOrgVerAccountBalanceBS orgVerAccountBalanceBS = (IOrgVerAccountBalanceBS) BSUtils
          .getBusinessService("orgVerAccountBalanceBS", this, mapping
              .getModuleConfig());
      IDocNumBS docNumBS = (IDocNumBS) BSUtils.getBusinessService("docNumBS",
          this, mapping.getModuleConfig());
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      Org org = orgVerAccountBalanceBS.queryOrgInfo(orgId, securityInfo);
      String officeCode = org.getOrgInfo().getOfficecode();
      try {
        docNum = docNumBS.getDocNumdocNum(officeCode, bizDate.substring(0, 6),securityInfo);
      } catch (Exception e) {
        throw new BusinessException("生成凭证号失败！");
      }
      orgVerAccountBalanceBS.doOrgVerAccountBalance(f.getId(), docNum,
          pagination, securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("结转余额成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("orgVerAccountBalanceShowAC");
  }

}
