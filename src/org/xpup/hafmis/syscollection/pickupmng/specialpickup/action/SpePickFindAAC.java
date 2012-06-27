package org.xpup.hafmis.syscollection.pickupmng.specialpickup.action;


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
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface.ISpePickBS;
public class SpePickFindAAC extends Action{



    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
          response.setContentType("text/html;charset=UTF-8");
          response.setHeader("Cache-Control", "no-cache");
          ActionMessages mess = new ActionMessages();
          try {
            String id=(String)request.getParameter("id");
            ISpePickBS spePickBS = (ISpePickBS) BSUtils
            .getBusinessService("spePickBS", this, mapping.getModuleConfig());
            SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
            Org org=null;
            String name="";
            String text=null;
            String f="";
            String f1="";
            boolean flag1=false;
            boolean flag2=false;
            if(id!=null&&!id.equals("")){
              
              id=BusiTools.convertSixNumber(id);
              org = spePickBS.queryOrgById(new Integer(id),securityInfo);
              
            }
            if(org!=null&&org.getOrgInfo().getOpenstatus().equals("2")){
              flag1=spePickBS.check(id, securityInfo);//错账
              flag2=spePickBS.checkTranOut(id);//转出
              if(flag1)
              { 
                f = "提示";//错账
              }
              if(flag2)
              { 
                f1 = "提示";//转出
              }
              name=org.getOrgInfo().getName();
            }
            else if(!org.getOrgInfo().getOpenstatus().equals("2"))
            {
              text="showNull2()";
              response.getWriter().write(text);
              response.getWriter().close();
            }
            text="displays('"+id+"','"+name+"','"+f+"','"+f1+"')";
            response.getWriter().write(text); 
            response.getWriter().close();
          } catch (BusinessException be) {
            String text1="showNull()";
            response.getWriter().write(text1);
            response.getWriter().close();
          }
          
          return null; 
  }
    
  }

