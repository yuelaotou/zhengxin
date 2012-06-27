package org.xpup.hafmis.syscollection.accountmng.accountopen.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.PayBank;

public class OrgOpenInfoAAC extends Action  {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      String id = request.getParameter("orgId");
      request.getSession().setAttribute("hcodeid", id);
      Org org = orgOpenAccountBS.findOPA(new Integer(id));
      if(org.getOrgInfo().getPayBank()==null){
        org.getOrgInfo().setPayBank(new PayBank());
      }
      OrgkhAF af = new OrgkhAF();
      af.setOrg(org);
    
      af.setType("2");
      Map industyMap = BusiTools.listBusiProperty(BusiConst.INDUSTRY);
      af.setIndustyMap(industyMap);
      Map orgpaywayMap = BusiTools.listBusiProperty(BusiConst.ORGPAYWAY);
      af.setOrgpaywayMap(orgpaywayMap);
      Map paymentaccuracyMap = BusiTools
          .listBusiProperty(BusiConst.PAYMENTACCURACY);
      af.setPaymentaccuracyMap(paymentaccuracyMap);
      Map inareaMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      af.setInareaMap(inareaMap);
      Map natureofunitsMap = BusiTools
          .listBusiProperty(BusiConst.NATUREOFUNITS);
      af.setNatureofunitsMap(natureofunitsMap);
      Map authoritiesMap = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      af.setAuthoritiesMap(authoritiesMap);
      
      // 当单位状态不是开户中时办事处与归集银行被禁用不能被修改。
      String orgOpenstatus = af.getOrg().getOrgInfo().getOpenstatus();
      if (!orgOpenstatus.equals("1")) {
        request.getSession().setAttribute("orgOS",orgOpenstatus );
        request.getSession().setAttribute("CollectionBank", af.getOrg().getOrgInfo().getCollectionBankId());
        request.getSession().setAttribute("Officecode", af.getOrg().getOrgInfo().getOfficecode());
      }

      request.setAttribute("openstatus", af.getOrg().getOrgInfo().getOpenstatus());
      request.setAttribute("orgkhAF", af);
      request.setAttribute("payMode", af.getOrg().getPayMode().toString());
      // 用来区别下拉菜单的状态
      request.setAttribute("state", "1");
      request.setAttribute("orgid", id);
      request.setAttribute("orgnotchang", "centerOrg");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("organization_open_detail");
  }
}
