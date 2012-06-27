package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;

public class Tran_FindImpOrgNameAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try {
      String inorgid=(String)request.getParameter("inOrgId");

      ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
      
      String inorgname = null;
      Org org = null;      
      String str="";
      if(inorgid!=null&&!inorgid.equals("")){
        org = tranoutBS.fingInOrgInfo(inorgid);
          if(org!=null){
            inorgname=org.getOrgInfo().getName();
          }else{
            str="单位不存在或状态不能办理!";
          }
      }
      
      String text=null;
      String paginationKey = getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
      pagination.getQueryCriterions().put("inorgid", inorgid);
      pagination.getQueryCriterions().put("inorgname", inorgname);
      
      if(inorgname==null||inorgname.equals("")||inorgname.length()<1)
        inorgname="";
      
      text="displays2('"+inorgid+"','"+inorgname+"','"+str+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null; 
  }
  private String getPaginationKey() {
    return Tran_showAC.PAGINATION_KEY;
  }
}
