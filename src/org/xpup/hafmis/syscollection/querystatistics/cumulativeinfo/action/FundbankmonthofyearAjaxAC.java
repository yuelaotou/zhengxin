package org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.bsinterface.ICumulativeinfoFindBS;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.form.CumulativeinfoFindAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 办事处，归集银行 连动 Ajax
 * 
 *@author 杨光
 *2007-10-05
 */
public class FundbankmonthofyearAjaxAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      response.setContentType("text/xml; charset=utf-8");
      Org org=new Org();
//      String collectionBankId="";
//      String count="";
      PrintWriter out = response.getWriter();
      out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
      out.println("<options>");
      String officeCode = (String) request.getParameter("officeCode");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
      .getBusinessService("orgOpenAccountBS", this, mapping
          .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      
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
      if (request.getParameter("officeCode")!=null) {
        collBankListTest = orgOpenAccountBS.queryCollBank(officeCode);
      }else{
        officeDtoTest = (OfficeDto)officeList.get(0);
        collBankListTest = orgOpenAccountBS.queryCollBank(officeDtoTest.getOfficeCode());
      }
//      String orgId=(String)request.getSession().getAttribute("orgId");
//      org=orgOpenAccountBS.findOPA(new Integer(orgId));
//      collectionBankId=org.getOrgInfo().getCollectionBankId();
//      
      // 判断该权限下的某个办事处对应的归集银行
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
   
    
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        for (int i = 0; i < collBankListTest.size(); i++) {
          CollBank  collBank = (CollBank) collBankListTest.get(i);
          if (userslogincollbank.getCollBankId().equals(collBank.getCollBankId())) {
            collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
            
            
            out.println("<value>" + userslogincollbank.getCollBankId().toString() + "</value>");
            out.println("<text>" + userslogincollbank.getCollBankName().toString() + "</text>");
          }
        }
      }
      out.println("</options>");
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}