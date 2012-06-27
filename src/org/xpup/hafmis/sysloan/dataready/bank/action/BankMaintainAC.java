package org.xpup.hafmis.sysloan.dataready.bank.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.bank.bsinterface.IBankBS;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankAFDTO;
import org.xpup.hafmis.sysloan.dataready.bank.form.BankAF;

/** 
 * MyEclipse Struts
 * Creation date: 09-22-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BankMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.bank.action.ShowBankAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "remove");
    map.put("button.use", "use");
    return map;
  }
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    try{
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");   
    List officeList = securityInfo.getAllOfficeList();
    List officeList1 = new ArrayList();
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
          .getOfficeName(), officedto.getOfficeCode()));
    }   
    request.setAttribute("officeList1", officeList1);
    List bankList1 = new ArrayList();
    request.setAttribute("bankList1", bankList1);
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    BankAF bankAF=new BankAF();
    bankAF.setType("添加");
    request.setAttribute("bankAF", bankAF);
    return mapping.findForward("to_bank_add");
  }
  /**
   * 更新
   * @param mapping
   * @param form
   * @param response
   * @return
   */
  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
      String bankID=(String)request.getParameter("id").trim();
      BankAFDTO bankAF=null;
      try{
        IBankBS bankBS = (IBankBS) BSUtils
        .getBusinessService("bankBS", this, mapping.getModuleConfig());
        bankAF=bankBS.queryBank(bankID);
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");   
    List officeList = securityInfo.getAllOfficeList();
    List officeList1 = new ArrayList();
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
    }
    request.getSession(true).setAttribute("officeList1", officeList1);

    List bankList = bankBS.getCollBankList();
    List bankList1 = new ArrayList();
    CollBank bankdto = null;   
    Iterator itr2 = bankList.iterator();    
    while (itr2.hasNext()) {
      bankdto = (CollBank) itr2.next();   
      bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
    }
    request.getSession(true).setAttribute("bankList1", bankList1);
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    bankAF.setType("修改");
    request.setAttribute("bankAF", bankAF);
      return mapping.findForward("to_bank_add");
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
    ActionMessages message=null;
    String bankID=(String)request.getParameter("id").trim();
    String err="";
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IBankBS bankBS = (IBankBS) BSUtils
      .getBusinessService("bankBS", this, mapping.getModuleConfig()); 
      err=bankBS.deleteBank_YM(new Integer(bankID),securityInfo);
    }catch(Exception e)
    {
      e.printStackTrace();
    }finally{
      message= new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(err, false));
      saveErrors(request,message);
    }
    return mapping.findForward("showBankAC");
  }
  
  /**
   * 启用按钮
   */
  public ActionForward use(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    ActionMessages message=null;
    String bankID=(String)request.getParameter("id").trim();
    String err="";
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IBankBS bankBS = (IBankBS) BSUtils
      .getBusinessService("bankBS", this, mapping.getModuleConfig());
      err=bankBS.useBank_YM(new Integer(bankID),securityInfo);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }finally{
      message= new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(err, false));
      saveErrors(request,message);
    }
    return mapping.findForward("showBankAC");
  }
}
