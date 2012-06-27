package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.imp.Factory;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form.BankCheckAccTbAF;

public class BankCheckAccTbImportsAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    BankCheckAccTbAF bankCheckAccTbAF = (BankCheckAccTbAF) form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    List officeList1 = null;
    try {
      // 取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    String office = bankCheckAccTbAF.getOffice();
    FormFile file = bankCheckAccTbAF.getTheFile();
    String fileType = file.getFileName().substring(
        file.getFileName().lastIndexOf(".") + 1);
    if (file.toString().length() < 1) {
      return (mapping.findForward("文件无后缀"));
    } else if (!fileType.equals("xls")) {
      return (mapping.findForward("文件后缀不是xls格式"));
    }
    ServletContext context = request.getSession().getServletContext();
    String jasperSrc = context.getRealPath("/impXml/" + "bankcheckacc_imp.xml");
    Factory faxtory = new Factory();
    File xmlfile = new File(jasperSrc);
    Map info = null;
    try {
      info = faxtory.getInfomation(xmlfile, file.getInputStream(),
          "org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.");
    } catch (Exception e) {
    }
    List bankCheckAccImpContent = new ArrayList();
    bankCheckAccImpContent = (List) info.get("BankCheckAccImpContent");
    try {
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
          .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      bankCheckAccBS.importsBankCheckAcc(bankCheckAccImpContent, office,
          securityInfo);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
      request.setAttribute("officeList1", officeList1);
      return mapping.findForward("to_bankcheckacctb_imports");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("officeList1", officeList1);
      return mapping.findForward("to_bankcheckacctb_imports");
    }
    return mapping.findForward("bankcheckacctb_show");
  }
}
