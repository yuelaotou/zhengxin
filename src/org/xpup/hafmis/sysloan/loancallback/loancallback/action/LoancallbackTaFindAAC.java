package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class LoancallbackTaFindAAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
  
          //进入页面是要带权限的，从权限中取得是以中心为主还是以银行为主。以此来控制按钮灰显，要在action中设置状态。
          //贷款还款类型1.中心为主2.银行为主
          int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
          //根据权限中的还款类型判断以哪为主
          int plLoanReturnType = 0;
          if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER){
            plLoanReturnType = 1;//中心为主
          }else if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK){
            plLoanReturnType = 2;//银行为主
          }
          String id=(String)request.getParameter("id");
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("contractId", id);
          pagination.getQueryCriterions().put("callbackMonth",null);
          text = "display('"+id+"');";
          request.getSession().setAttribute("plLoanReturnType", String.valueOf(plLoanReturnType));
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return LoancallbackTaShowAC.PAGINATION_KEY;
 }
}