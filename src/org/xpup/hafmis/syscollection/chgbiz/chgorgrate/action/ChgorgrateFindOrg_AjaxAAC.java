package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class ChgorgrateFindOrg_AjaxAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text=null;
    try {
      
      String orgID = request.getParameter("orgID");
      
      
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils
      .getBusinessService("chgorgrateBS", this, mapping.getModuleConfig()); 
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      
      String headID="";
      String id="";
      String name="";
      String payMode="";
      String chgMonth="";
      String preOrgRate="";
      String preEmpRate="";
      String orgRate="";
      String empRate="";
      String salarybase="";
      String preSumPay="";
      String sumSumPay="0";
      
      String orgEdition="";
      
      //判断该单位是否是按率缴存,并查询出页面显示的信息
      ChgOrgRate chgOrgRate = chgorgrateBS.checkOrgMessage(orgID,securityInfo);
      
      if(chgOrgRate!=null){
        headID=chgOrgRate.getId().toString();
        id = chgOrgRate.getOrg().getSid().toString();
        name = chgOrgRate.getOrg().getOrgInfo().getName();
        payMode=BusiTools.getBusiValue(chgOrgRate.getOrg().getPayMode().intValue(),BusiConst.ORGPAYWAY);
        preOrgRate = chgOrgRate.getPreOrgRate().toString();
        preEmpRate = chgOrgRate.getPreEmpRate().toString();
        orgRate=chgOrgRate.getOrgRate().toString();
        empRate=chgOrgRate.getEmpRate().toString();
        salarybase = chgOrgRate.getSalarybase().toString();
        preSumPay=chgOrgRate.getPreSumPay().toString();
        sumSumPay=(chgOrgRate.getEmpPay().add(chgOrgRate.getOrgPay())).toString();
        
        chgMonth =chgorgrateBS.getChgMonth(chgOrgRate, orgID);
        
      }
      Org org = null;
      if(chgOrgRate==null){
        org = chgorgrateBS.queryOrgByorgID(orgID);
        
        id = org.getSid().toString();
        name = org.getOrgInfo().getName();
        payMode=BusiTools.getBusiValue(chgOrgRate.getOrg().getPayMode().intValue(),BusiConst.ORGPAYWAY);
        orgRate=org.getOrgRate().toString();
        empRate=org.getEmpRate().toString();
        preSumPay=chgorgrateBS.querySumPayByOrgID(orgID).toString();
        ChgOrgRate temp_chgOrgRate=new ChgOrgRate();
        salarybase =org.getTemp_salary().toString();
        chgMonth =chgorgrateBS.getChgMonth(temp_chgOrgRate, orgID);
        
      }else{
        String message="该单位不存在！！";
        text="reportErrors('" + message + "')";
      }
//    吴洪涛修改 2008-3-18 单位_汇缴比例调整
      int isOrgEdition = securityInfo.getIsOrgEdition();
  
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG)// 单位版{
      {
      if(orgRate=="0" || empRate=="0"){
        ChgOrgRate chgOrgRate_OrgEdition = chgorgrateBS.queryChgorgrate_OrgEdition(orgID,chgMonth,securityInfo);
       if(chgOrgRate_OrgEdition.getOrgRate().toString()=="0" || chgOrgRate_OrgEdition.getEmpRate().toString()=="0"){
         text = "backErrors('" + "中心没有汇缴比例调整业务" + "')";
         response.getWriter().write(text);
         response.getWriter().close();
       }else{
         orgRate=chgOrgRate_OrgEdition.getOrgRate().toString();
         empRate=chgOrgRate_OrgEdition.getEmpRate().toString();
       }
       
      }
      
        orgEdition="orgEdition";
      }
    
      
//    吴洪涛修改 2008-3-18 单位_汇缴比例调整
      
      text="displays('"+headID+"','"+id+"','"+name+"','"+payMode+"','"+chgMonth+"','"+preOrgRate+"','"+preEmpRate+"','"+orgRate+"','"+empRate+"','"+preSumPay+"','"+salarybase+"','"+sumSumPay+"','"+orgEdition+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    } catch (BusinessException be) {
      be.printStackTrace();
      text = "backErrors('" + be.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();

    }catch(Exception e){
      e.printStackTrace();
    }

    return null;
  }
}
