package org.xpup.hafmis.sysloan.dataready.bank.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
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
public class BankAddAC extends LookupDispatchAction{  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "sure");
    map.put("button.back", "back");
    return map;
  }
  public ActionForward sure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    BankAF bankAF=(BankAF)form;
    ActionMessages message=null;
    
    //张列改 头
    BankAFDTO bankAFDTO = new BankAFDTO();
    try {
      BeanUtils.copyProperties(bankAFDTO, bankAF);
    } catch (IllegalAccessException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (InvocationTargetException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    //张列改 尾
    
    try{
    IBankBS bankBS = (IBankBS) BSUtils
    .getBusinessService("bankBS", this, mapping.getModuleConfig());
    
    //张列改 头
    boolean is_bank=bankBS.isCheckBank(bankAFDTO);  //判断是否已经存在
    //张列改 尾
    
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    if(bankAF.getType().equals("添加"))
    {
      if(is_bank)
      {
        message= new ActionMessages();
        message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该银行已经存在", false));
        saveErrors(request,message);
      }
     else
      {
       //张列改 头
        bankBS.insertBank(bankAFDTO,securityInfo);
        //张列改 尾
        
        message= new ActionMessages();
        message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加成功!", false));
        saveErrors(request,message);
      }
    }
    if(bankAF.getType().equals("修改"))
    {
      //张列改 头
      bankBS.updateBank_YM(bankAFDTO,securityInfo);
      //张列改 尾
      
    }
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return mapping.findForward("showBankAC");
  }
  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("showBankAC");
  }
}