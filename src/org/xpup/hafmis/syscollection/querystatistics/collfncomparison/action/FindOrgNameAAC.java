package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface.ISpePickBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
public class FindOrgNameAAC extends Action{



    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
          response.setContentType("text/html;charset=UTF-8");
          response.setHeader("Cache-Control", "no-cache");
          try {
            String id=(String)request.getParameter("id");
            ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
            .getBusinessService("collFnComparisonBS", this, mapping.getModuleConfig());
            SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
            Org org=null;
            String name="";
            String text=null;
            if(id!=null&&!id.equals("")){
              id=BusiTools.convertSixNumber(id);
              org = collFnComparisonBS.queryOrgById(new Integer(id),securityInfo);
              
            }
            if(org!=null){
              name=org.getOrgInfo().getName();
            }
            text="displays('"+id+"','"+name+"')";
            response.getWriter().write(text); 
            response.getWriter().close();
          } catch (Exception e) {
            e.printStackTrace();
          }
          
          return null; 
  }
    
  }

