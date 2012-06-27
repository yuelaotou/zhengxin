package org.xpup.hafmis.sysloan.dataready.bank.action;

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
import org.xpup.hafmis.sysloan.dataready.bank.bsinterface.IBankBS;
import org.xpup.hafmis.sysloan.dataready.bank.form.BankAF;

/** 
 * MyEclipse Struts
 * Creation date: 09-27-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BankOfficeAC extends Action {
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo"); 
    String officecodeID = request.getParameter("officecode");

    //取出用户权限办事处,显示在下拉菜单中
    List officeList = securityInfo.getAllOfficeList();
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
    IBankBS bankBS = (IBankBS) BSUtils
    .getBusinessService("bankBS", this, mapping
        .getModuleConfig());
    // 从请求参数中得到办事处id
    if (request.getParameter("officecode")!=null) {
      collBankListTest = bankBS.queryCollBank(officecodeID);
    }else{
      officeDtoTest = (OfficeDto)officeList.get(0);
      collBankListTest = bankBS.queryCollBank(officeDtoTest.getOfficeCode());
    }
    
    // 判断该权限下的某个办事处对应的归集银行
    List collBankList = bankBS.getCollBankList();
    List collBankList1 = new ArrayList();
    CollBank userslogincollbank = null;
    Iterator itr2 = collBankList.iterator();
    while (itr2.hasNext()) {
      userslogincollbank = (CollBank) itr2.next();
      for (int i = 0; i < collBankListTest.size(); i++) {
        CollBank collBank = (CollBank) collBankListTest.get(i);
        if (userslogincollbank.getCollBankId().equals(collBank.getCollBankId())) {
          collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
        }
      }
    }
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("bankList1", collBankList1);
    BankAF bankAF=new BankAF();
    bankAF.setOffice(officecodeID);
    bankAF.setType("添加");
    request.setAttribute("bankAF", bankAF);
    return mapping.findForward("to_bank_add");
	}
}