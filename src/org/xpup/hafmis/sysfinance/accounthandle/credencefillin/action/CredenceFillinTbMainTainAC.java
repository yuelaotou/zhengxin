package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.util.HashMap;
import java.util.List;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;

/**
 * Copy Right Information : 自动转帐MainTainAction Goldsoft Project :
 * CredenceFillinTbShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.19
 */
public class CredenceFillinTbMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.createcredence.all", "saveAllCredenceInfo");
    map.put("button.createcredence", "saveCredenceInfo");
    map.put("button.income", "income");
    map.put("button.expense", "expense");
    return map;
  }

  /**
   * 生成凭证号的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveCredenceInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      // 得到票据号以及业务状态 settNum有票据编号与业务状态组成由','隔开
      String[] settNum = null;
      String rowArray = request.getParameter("rowArrayHid");
      if (rowArray == null) {
        throw new BusinessException("请选择要转账的业务！");
      } else {
        settNum = rowArray.split("-");
      }

      String date = request.getParameter("sett_date");
      String oldCredenceNum = request.getParameter("oldCreNum");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      // 生成凭证的方法
      credenceFillinBS.saveCredenceInfo(settNum, date, oldCredenceNum,
          securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("转帐失败！",
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("credenceFillinTbShowAC");
  }

  /**
   * 全部生成凭证的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveAllCredenceInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      // 得到票据号以及业务状态 settNum有票据编号与业务状态组成由','隔开
      String[] settNum = (String[]) request.getSession().getAttribute(
          "allSettNum");

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String date = request.getParameter("sett_date");
      String oldCredenceNum = request.getParameter("oldCreNum");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      // 生成凭证的方法
      credenceFillinBS.saveCredenceInfo(settNum, date, oldCredenceNum,
          securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("转帐失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinTbShowAC");
  }

  /**
   * 查询收入的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward income(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CredenceFillinTbShowAC.PAGINATION_KEY);
      CredenceFillinTbFindDTO credenceFillinTbFindDTO = (CredenceFillinTbFindDTO) pagination
          .getQueryCriterions().get("credenceFillinTbFindDTO");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      List list = credenceFillinBS.getIncomeList(credenceFillinTbFindDTO);
      request.setAttribute("main", "yes");// 传到ShowAC 判断是否是MainTainAC过去的
      request.setAttribute("list", list);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("查询收入失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinIncomeExpenseShowAC");
  }

  /**
   * 查询支出的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward expense(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CredenceFillinTbShowAC.PAGINATION_KEY);
      CredenceFillinTbFindDTO credenceFillinTbFindDTO = (CredenceFillinTbFindDTO) pagination
          .getQueryCriterions().get("credenceFillinTbFindDTO");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      List list = credenceFillinBS.getExpenseList(credenceFillinTbFindDTO);
      request.setAttribute("main", "yes");// 传到ShowAC 判断是否是MainTainAC过去的
      request.setAttribute("list", list);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("查询支出失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinIncomeExpenseShowAC");
  }

}
