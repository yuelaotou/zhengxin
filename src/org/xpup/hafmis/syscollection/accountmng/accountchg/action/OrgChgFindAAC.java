package org.xpup.hafmis.syscollection.accountmng.accountchg.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountchg.bsinterface.IOrgChgBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLog;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;
import org.xpup.hafmis.syscommon.domain.entity.Transactor;

public class OrgChgFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String flag = "";
    try {
      String id = (String) request.getParameter("id");
      IOrgChgBS orgChgBS = (IOrgChgBS) BSUtils.getBusinessService("orgChgBS",
          this, mapping.getModuleConfig());
      Org org = null;
      String orgid = "";
      String name = "";
      String officecode = "";
      String code = "";
      String taxRegNum = "";
      String artificialPerson = "";
      String character = "";
      String industry = "";
      String deptInCharge = "";
      String address = "";
      String postalcode = "";
      String tel = "";
      String region = "";
      String paybankName = "";//发薪银行名称
      String paybankAccountNum = "";//发薪银行账号
      String paydate = "";
      String collectionBankId = "";
      String transactorname = "";
      String email = "";
      String telephone = "";
      String mobileTelephone = "";
      String inspector = "";
      String payMode = "";
      String hiddenpayMode = "";
      String orgRate = "";
      String empRate = "";
      String firstPayMonth = "";
      String payPrecision = "";
      String openStatus = "";
      try {
        org = orgChgBS.findOrgChgById(new Integer(id),sInfo);
      } catch (NumberFormatException e) {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "不存在该单位或没有该单位的权限", false));
        saveErrors(request, messages);
        flag = "不存在该单位或没有该单位的权限";
      }
      
//      OrgChgLog orgChgLog = new OrgChgLog();
//      orgChgLog = orgChgBS.findOrgChg(id);
      // 判断单位编号是否正确
      if (org == null || org.getOrgInfo().getOpenstatus().equals("1")) {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "不存在该单位或没有该单位的权限", false));
        saveErrors(request, messages);
        flag = "不存在该单位或没有该单位的权限";
      }else

      // 马志勇修改过了
      //if (org != null) 
      {
       if (org.getOrgInfo().getTransactor() == null) {
         org.getOrgInfo().setTransactor(new Transactor());
//          transactorname = transactor.getName();
//          email = transactor.getEmail();
//          telephone = transactor.getTelephone();
//          mobileTelephone = transactor.getMobileTelephone();
//        } else {
//          transactorname = org.getOrgInfo().getTransactor().getName();
//          email = org.getOrgInfo().getTransactor().getEmail();
//          telephone = org.getOrgInfo().getTransactor().getTelephone();
//          mobileTelephone = org.getOrgInfo().getTransactor()
//              .getMobileTelephone();
        }
        transactorname = org.getOrgInfo().getTransactor().getName();
        email = org.getOrgInfo().getTransactor().getEmail();
        telephone = org.getOrgInfo().getTransactor().getTelephone();
        mobileTelephone = org.getOrgInfo().getTransactor().getMobileTelephone();
        orgid = BusiTools.convertSixNumber(org.getId().toString());
        name = org.getOrgInfo().getName();
        code = org.getOrgInfo().getCode();
        taxRegNum = org.getOrgInfo().getTaxRegNum();
        artificialPerson = org.getOrgInfo().getArtificialPerson();
        character = BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
            .getCharacter()), BusiConst.NATUREOFUNITS);
        industry = BusiTools.getBusiValue_WL(org.getOrgInfo().getIndustry(),
            BusiConst.INDUSTRY);
        deptInCharge = BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
            .getDeptInCharge()), BusiConst.AUTHORITIES);
        address = org.getOrgInfo().getAddress();
        postalcode = org.getOrgInfo().getPostalcode();
        tel = org.getOrgInfo().getTel();
        region = BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
            .getRegion()), BusiConst.INAREA);
        if(org.getOrgInfo().getPayBank()!=null){
          paybankName = org.getOrgInfo().getPayBank().getName();
          paybankAccountNum = org.getOrgInfo().getPayBank().getAccountNum();
        }
        paydate = org.getOrgInfo().getPaydate();
        OrgInfo orgInfo = orgChgBS.getCollBankAndOffice(org.getOrgInfo()
            .getCollectionBankId(), org.getOrgInfo().getOfficecode());
        officecode = orgInfo.getTemp_officename();
        collectionBankId = orgInfo.getTemp_collectionBankname();
        inspector = org.getOrgInfo().getInspector();
        payMode = BusiTools.getBusiValue(org.getPayMode().intValue(),
            BusiConst.ORGPAYWAY);
        hiddenpayMode = org.getPayMode().toString();
        orgRate = org.getOrgRate().toString();
        empRate = org.getEmpRate().toString();
        firstPayMonth = org.getFirstPayMonth().toString();
        payPrecision = BusiTools.getBusiValue(org.getPayPrecision().intValue(),
            BusiConst.PAYMENTACCURACY);
//         chgType =orgChgLog.getChgType();
         // 根据单位状态判断变更状态
         int i = new Integer(org.getOrgInfo().getOpenstatus()).intValue();
         switch (i) {
           case 2:
             openStatus = "A";
           break;
           case 3:
             openStatus = "B";
           break;
           case 4:
             openStatus = "C";
           break;
         }
      } 
      String text = null;
      text = "displays('"+"0" + orgid + "','" + name + "','" + officecode + "','"
          + code + "','" + taxRegNum + "','" + artificialPerson + "','"
          + character + "','" + industry + "','" + deptInCharge + "','"
          + address + "','" + postalcode + "','" + tel + "','" + region + "','" 
          + paybankName + "','" + paybankAccountNum + "','"
          + paydate + "','" + collectionBankId + "' ,'" + transactorname
          + "','" + email + "','" + telephone + "','" + mobileTelephone + "','"
          + inspector + "','" + payMode + "','" + hiddenpayMode + "' ,'"
          + orgRate + "','" + empRate + "','" + firstPayMonth + "','"
          + payPrecision + "','" + openStatus + "','" + flag + "')";
      response.getWriter().write(text);
      response.getWriter().close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

}
