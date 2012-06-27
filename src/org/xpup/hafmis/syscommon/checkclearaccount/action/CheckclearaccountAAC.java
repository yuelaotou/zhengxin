package org.xpup.hafmis.syscommon.checkclearaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscommon.checkclearaccount.bsinterface.ICheckclearaccountBS;

public class CheckclearaccountAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String text = null;
      String flag="0";
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      if(securityInfo==null){
        flag="2";
      }else{
        ICheckclearaccountBS checkclearaccountBS = (ICheckclearaccountBS) BSUtils
        .getBusinessService("checkclearaccountBS", this, mapping.getModuleConfig());
        flag=checkclearaccountBS.checkclearaccount(securityInfo);
      }
      
      //flag=0:不存在当前日期未入账的业务;flag=1:存在当前日期未入账的业务;flag=2:未进入系统
      text="isclosesys('"+flag+"')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
    }
    
    return null; 
} 
}
