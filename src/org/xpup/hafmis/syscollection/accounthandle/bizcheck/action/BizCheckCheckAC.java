package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.security.common.domain.Userslogincollbank;

public class BizCheckCheckAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try {
      OrgkhAF af = (OrgkhAF) form;
//      String orgId=(String)request.getParameter("orgId");
//      String isModify="";
      String orgId=(String)request.getSession().getAttribute("orgId");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      String officecodeID = request.getParameter("officecode");
//      isModify=bizcheckBS.queruIsBankModify(orgId);
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
      .getBusinessService("orgOpenAccountBS", this, mapping
          .getModuleConfig());
      Org org = orgOpenAccountBS.findOPA(new Integer(orgId));
      af.setOrg(org);
      if (request.getParameter("officecode")!=null) {
        af.getOrg().getOrgInfo().setOfficecode(officecodeID);
      }
//    取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      
      // 得到办事处下的归集银行
      OfficeDto officeDtoTest = null;
      List collBankListTest = null;
      // 从请求参数中得到办事处id
      if (request.getParameter("officecode")!=null) {
        collBankListTest = orgOpenAccountBS.queryCollBank(officecodeID);
      }else{
        officeDtoTest = (OfficeDto)officeList.get(0);
        collBankListTest = orgOpenAccountBS.queryCollBank(officeDtoTest.getOfficeCode());
      }
      
      // 判断该权限下的某个办事处对应的归集银行
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        for (int i = 0; i < collBankListTest.size(); i++) {
          CollBank collBank = (CollBank) collBankListTest.get(i);
          if (userslogincollbank.getCollBankId().equals(collBank.getCollBankId())) {
            collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
          }
        }
      }
      request.getSession(true).setAttribute("officeList1", officeList1);
      request.getSession(true).setAttribute("collBankList1", collBankList1);
      request.setAttribute("orgkhAF", af);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_modify");
  }

}
