package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;


import java.util.ArrayList;
import java.util.List;
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
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
//吴洪涛 2008-6-18/ 
public class ChgPersonAutoopenAllSaveAC  extends Action {
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
      List list = new ArrayList();
      list=(List)pagination.getQueryCriterions().get("listAll");//单位编号
      String chgDate=(String)session.getAttribute("chgDate");//变更年月
      ChgPersonTail chgPersonTail = null;     
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      if (list != null && list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        TranInTail tranInTail=(TranInTail)list.get(i);
        chgPersonTail = new ChgPersonTail();
        ChgpersonEmpInfoDTO chgpersonEmpInfoDTO = chgpersonDoBS.findChgpersonEmpInfo(tranInTail.getTranInHead().getTranInOrg().getId().toString(), tranInTail.getEmpId().toString());     
        chgPersonTail.setEmpId(tranInTail.getEmpId());        
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
        
        chgpersonDoBS.insertChgpersonDO(tranInTail.getTranInHead().getTranInOrg().getId().toString(),null, chgDate, "2", null, null, null,"3",chgPersonTail,securityInfo);
        chgpersonDoBS.updateTranInTail(tranInTail.getTranInHead().getTranInOrg().getId().toString(), tranInTail.getEmpId().toString(),"1");
      }
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