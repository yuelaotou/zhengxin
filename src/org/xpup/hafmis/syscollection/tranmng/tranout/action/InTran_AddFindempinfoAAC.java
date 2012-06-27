package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;

public class InTran_AddFindempinfoAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try {
      String orgid=(String)request.getParameter("orgid");
      String empid=(String)request.getParameter("empid");
      String cardNum=(String)request.getParameter("cardNum");
      String name=(String)request.getParameter("name");
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
      String str="";
      str=tranoutBS.findtraninEmpInfo(name,cardNum,empid, orgid);
      String text=null;
      if(str.equals("a")){
        text="displaysin('"+empid+"','a')";
        response.getWriter().write(text); 
        response.getWriter().close();
      }else if(str.equals("b")){
        text="displaysin('"+empid+"','b')";
        response.getWriter().write(text); 
        response.getWriter().close();
      }else{
        text="displaysin('"+empid+"','c')";
        response.getWriter().write(text); 
        response.getWriter().close();
      }
      
    } catch (BusinessException e) {
      String text=null;
      text="displaysin('"+""+"','"+e.getLocalizedMessage()+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
    }
      
      
  
    
    return null; 
  }

}
