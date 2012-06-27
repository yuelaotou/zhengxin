package org.xpup.hafmis.sysfinance.common.biz.queryflow.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface.IQueryFlowBS;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;

/**
 * Copy Right Information : 显示凭证流水的ShowAction Goldsoft Project : QueryFlowShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.8
 */
public class QueryFlowShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.common.biz.queryflow.QueryFlowShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String credenceId = (String) request.getParameter("credenceId");
      IQueryFlowBS queryFlowBS = (IQueryFlowBS) BSUtils.getBusinessService(
          "queryFlowBS", this, mapping.getModuleConfig());
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils.getBusinessService(
          "credenceFillinBS", this, mapping.getModuleConfig());
      String settNum = "";
      request.getSession().removeAttribute(CollectionFlowShowAC.PAGINATION_KEY);
      request.getSession().removeAttribute(LoanFlowShowAC.PAGINATION_KEY);
      // 如果credenceId不为空说明是从凭证维护中的超链接过来的
      // 否则是自动转账页面中过来的
      if(credenceId!=null){
        AccountantCredence accountantCredence = credenceFillinBS.queryById(new Integer(credenceId));
        if(accountantCredence.getReserveC()!=null&&!"".equals(accountantCredence.getReserveC())){
          settNum = accountantCredence.getReserveC();
        }else{
          settNum = accountantCredence.getSettNum();
        }
      }else{
        settNum = request.getParameter("settNum");
      }
      System.out.println(settNum+"====================================>");
      request.setAttribute("settNum", settNum);
      // 判断业务是属于归集业务还是个贷业务
      if(settNum == null) {
        return mapping.findForward("");
      }else if(settNum.indexOf(",")!=-1)
        settNum = settNum.split(",")[0];
      boolean is_Biz = queryFlowBS.IssettNum(settNum);
      if (is_Biz) {
        return mapping.findForward("collectionFlowShowAC");
      } else {
        return mapping.findForward("loanFlowShowAC");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("");
    }
  }
}
