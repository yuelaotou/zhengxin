package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayMaintainDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayMaintainAF;

public class PersonaddpayTbWindowShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonaddpayTbWindowShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      PersonAddPayMaintainAF personAddPayMaintainAF=(PersonAddPayMaintainAF)form;
      Pagination pagination = getPagination(PersonaddpayTbWindowShowAC.PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      EmpaddpayMaintainDTO empaddpayMaintainDTO = new EmpaddpayMaintainDTO();
      String paymentid = (String) request.getAttribute("paymentid");
      if(paymentid!=null)
      pagination.getQueryCriterions().put("paymentid", paymentid);
      empaddpayMaintainDTO=personAddPayBS.findEmpaddpay(pagination);
      List list = personAddPayBS.findEmpaddpayListCount(pagination);
      BigDecimal orgpay=new BigDecimal(0.00);
      BigDecimal emppay=new BigDecimal(0.00);
      BigDecimal paysum=new BigDecimal(0.00);
      for(int i=0;i<list.size();i++){
      AddPayTail  addPayTail =(AddPayTail)list.get(i);
      orgpay=orgpay.add(addPayTail.getOrgAddMoney());
      emppay=emppay.add(addPayTail.getEmpAddMoney());
      paysum=paysum.add(addPayTail.getOrgAddMoney().add(addPayTail.getEmpAddMoney()));
      }
      personAddPayMaintainAF.setOrgPaySum(orgpay);
      personAddPayMaintainAF.setEmpPaySum(emppay);
      personAddPayMaintainAF.setPaySum(paysum);
      personAddPayMaintainAF.setPersonSum(new Integer(list.size()));
      personAddPayMaintainAF.setOrgId(empaddpayMaintainDTO.getOrgId());
      personAddPayMaintainAF.setUnitName(empaddpayMaintainDTO.getOrgName());
      personAddPayMaintainAF.setUnitName(empaddpayMaintainDTO.getOrgName());
      personAddPayMaintainAF.setDocNumber(empaddpayMaintainDTO.getDocNum());
      personAddPayMaintainAF.setNoteNum(empaddpayMaintainDTO.getNoteNum());
      if(list.size()>0 && list!=null){
        empaddpayMaintainDTO.setListCount("1");
      }  
      request.getSession().setAttribute("empaddpayMaintainDTO", empaddpayMaintainDTO);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(), false));      
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("to_detail.jsp");
  }
  
  private Pagination getPagination(String paginationKey, HttpServletRequest request) {   
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);     
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "addPayTail.empId", "ASC",new HashMap(0));         
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
