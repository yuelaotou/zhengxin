package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;

public class ChgpersonCheckChgPersonAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        
        try {   
          String chgPersonHeadID = request.getParameter("chgPersonHeadID");//头表ID

          IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

          SecurityInfo securityInfo = (SecurityInfo) request.getSession()
              .getAttribute("SecurityInfo");
          
          //进行数据校验,是否可以进行业务撤销
          chgpersonDoBS.checkDelUseMessage(chgPersonHeadID,securityInfo);
          
          String text=null;
          text="displays()";
          response.getWriter().write(text); 
          response.getWriter().close();
        
        }catch(BusinessException be){
          String text="backErrors('"+be.getLocalizedMessage()+"')";
          response.getWriter().write(text);
          response.getWriter().close();  
          return null;     
          
        }
        
        return null; 
  }
}