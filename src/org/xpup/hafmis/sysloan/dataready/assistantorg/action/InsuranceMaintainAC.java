/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.dataready.assistantorg.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface.IInsuranceBS;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.InsuranceAFDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.form.InsuranceAF;

/** 
 * MyEclipse Struts
 * Creation date: 09-28-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class InsuranceMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY =
    "org.xpup.hafmis.sysloan.dataready.assistantorg.action.InsuranceShowAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "remove");
    return map;
  }
  /**
   * 添加方法
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    InsuranceAF insuranceAF=new InsuranceAF();
    try{
     //证件类型，下拉列表
    Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    insuranceAF.setMap(map);
    insuranceAF.setType("添加");
    
    //张列改 头
    Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
    insuranceAF.setRegionMap(regionMap);
    //张列改 尾
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    request.setAttribute("AF", insuranceAF);
    return mapping.findForward("to_insurance_addupdate");
  } 
  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
      String id=(String)request.getParameter("id").trim();
      InsuranceAFDTO insuranceAF=null;
      try{

        IInsuranceBS insuranceBS=(IInsuranceBS)BSUtils
        .getBusinessService("insuranceBS", this, mapping.getModuleConfig());
        insuranceAF=insuranceBS.findInsuranceID(new Integer(id));
        Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
        insuranceAF.setMap(map);
        insuranceAF.setType("修改");
        
        //枚举里取 所属地区(张列修改 头)
        Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
        insuranceAF.setRegionMap(regionMap);
        //(张列修改 尾)
        
        request.setAttribute("AF", insuranceAF);
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return mapping.findForward("to_insurance_addupdate");
  }
  /**
   * 删除此记录
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    ActionMessages messages=null;
    String id=(String)request.getParameter("id").trim();
    String error="该记录已经删除!";
    try{
      IInsuranceBS insuranceBS=(IInsuranceBS)BSUtils
      .getBusinessService("insuranceBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      boolean is_asistantOrg=insuranceBS.is_Insurance_YM(new Integer(id));
      if(is_asistantOrg)
      {
        error="该记录已经删除!";
      }
      else
      {
        insuranceBS.deleteInsurance(new Integer(id),securityInfo);
        error="删除成功!";
      }
    }
    catch(Exception e)
    {
      messages= new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败!", false));
      saveErrors(request,messages);
      return mapping.findForward("assistantorgShowAC");
    }finally{
      messages= new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(error, false));
      saveErrors(request,messages);
    }
    return mapping.findForward("insuranceShowAC");
  }

}