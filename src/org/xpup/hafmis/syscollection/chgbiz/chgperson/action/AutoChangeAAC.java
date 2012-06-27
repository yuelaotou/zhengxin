package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.HashMap;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgPersonAutoopenAF;

/**
 * Copy Right Information : 自动变更的AJAXAction Goldsoft Project :
 * AutoChangeParamSaveAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.17
 */
public class AutoChangeAAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ChgPersonAutoopenShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String text = "";
      // 用这个变量来判断是否需要探出框
      String flag = "";
      // 从请求参数中的到单位编号
      String orgId = request.getParameter("orgID");
      
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      // 判断是否可以进行自动变更
      flag = chgpersonDoBS.isAutoChange(orgId);
      text = "display_autoChange('" + flag + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
