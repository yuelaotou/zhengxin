package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
/**
 * 
 * @author yuqf
 *
 */
public class EndorsecontractTcShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTcShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages = null;
    String paramValue = "";
    Pagination pagination = null;
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      pagination = getPagination(PAGINATION_KEY, request);
      String contractId = "";
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());
      paramValue = endorsecontractBS.queryParamValue(securityInfo);// 参数值('AB'
                                                                    // or 'BA')
      contractId = (String) request.getParameter("contractId");
      if (contractId != null) {
        request.getSession().setAttribute("contractId", null);// 如果输入编号，则清空session
        endorsecontractTcAF = endorsecontractBS.queryImpawnContractList(
            contractId, pagination, securityInfo, request);
      } else {
        // 判断是否为联动
        contractId = (String) request.getSession().getAttribute("contractId");// 联动
                                                                              // 取得session中合同编号
        String comeFromType = (String) request.getSession().getAttribute(
            "comeFromType");// 用来判断是否为维护传来的状态
        // 判断是否是从维护过来的联动
        if (comeFromType != null) {// 是:合同编号按钮禁用 默认显示合同编号、借款人姓名
          endorsecontractTcAF.setIsButtonForbid("0");// 0禁止 1可用
          endorsecontractTcAF = endorsecontractBS.queryImpawnContractList(
              contractId, pagination, securityInfo, request);
        } else {
          endorsecontractTcAF.setIsButtonForbid("1");
          endorsecontractTcAF = endorsecontractBS.queryImpawnContractList(
              contractId, pagination, securityInfo, request);
        }
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    pagination.getQueryCriterions().put("list", endorsecontractTcAF.getList());// 将列表放在pagination中,点修改按钮的时候取出来
    try {
      // 证件类型下拉框
      Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      endorsecontractTcAF.setMap(map);
      endorsecontractTcAF.setParamValue(paramValue);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    String tcIsNeedDel=(String)request.getAttribute("tcIsNeedDel");
    endorsecontractTcAF.setIsNeedDel(tcIsNeedDel);
    request.getSession().setAttribute("theEndorsecontractTcAF", endorsecontractTcAF);
    return mapping.findForward("to_endorsecontractTc");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
