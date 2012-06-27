package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonEmpInfoDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
//吴洪涛 2008-6-18/ 
public class ChgPersonAutoopenSaveAC  extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ChgPersonAutoopenShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
 
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      String orgID=(String)pagination.getQueryCriterions().get("orgId");//单位编号
      String chgDate=(String)pagination.getQueryCriterions().get("chgMonth");//变更年月
      ChgPersonTail chgPersonTail = null;
      
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      String[] str = request.getParameterValues("empid");
      String[] empArray = str[0].split(",");
      
      for (int i = 0; i < empArray.length; i++) {
        String empId = empArray[i];
        chgPersonTail = new ChgPersonTail();
        ChgpersonEmpInfoDTO chgpersonEmpInfoDTO = chgpersonDoBS.findChgpersonEmpInfo(orgID, empId);
        
        chgPersonTail.setEmpId(new Integer(empId));
        
        chgPersonTail.setTemp_name(chgpersonEmpInfoDTO.getEmpName());
        chgPersonTail.setCardKind(new Integer(chgpersonEmpInfoDTO.getCardKind()));
        chgPersonTail.setTemp_cardNum(chgpersonEmpInfoDTO.getCardNum());
        chgPersonTail.setBirthday(chgpersonEmpInfoDTO.getBirthday());
        chgPersonTail.setDept(chgpersonEmpInfoDTO.getDept());
        chgPersonTail.setTel(chgpersonEmpInfoDTO.getTel());
        chgPersonTail.setCardNum(chgpersonEmpInfoDTO.getCardNum());
        
        chgPersonTail.setTemp_empPay(chgpersonEmpInfoDTO.getEmpPay());
        chgPersonTail.setTemp_orgPay(chgpersonEmpInfoDTO.getOrgPay());
        chgPersonTail.setSalaryBase(chgpersonEmpInfoDTO.getSalaryBase());
        
        chgpersonDoBS.insertChgpersonDO(orgID,null, chgDate, "2", null, null, null,"3",chgPersonTail,securityInfo);
        chgpersonDoBS.updateTranInTail(orgID,empId,"1");
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_chgpersonDo_list");
  
  }
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}