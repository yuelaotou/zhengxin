package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.AutoChangePopDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonEmpInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.AutoChangePopAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;

/**
 * Copy Right Information : 自动变更时插入变更表的Action Goldsoft Project :
 * AutoChangeSaveAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.17
 */
public class AutoChangeSaveALLAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      HttpSession session = request.getSession();
      String orgID=(String)session.getAttribute("orgID");//单位编号
      String chgDate=(String)session.getAttribute("chgDate");//变更年月
      ChgPersonTail chgPersonTail = null;
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      List list = (List)request.getSession().getAttribute("list_yg_chgperson");
      for(int i =0;i<list.size();i++){
        AutoChangePopDTO autoChangePopDTO = (AutoChangePopDTO) list.get(i);
        String empId = autoChangePopDTO.getEmpId();
        ChgpersonEmpInfoDTO chgpersonEmpInfoDTO = chgpersonDoBS.findChgpersonEmpInfo(orgID, empId);
        if(chgpersonEmpInfoDTO.getPayStatus().equals("2")){
          chgPersonTail = new ChgPersonTail();
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
        }else{
          chgPersonTail = new ChgPersonTail();
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
          chgpersonDoBS.insertChgpersonDO(orgID,null, chgDate, "3", null, null, null,"1",chgPersonTail,securityInfo);
        }
      }
//      for (int i = 0; i < list.size(); i++) {
//        AutoChangePopDTO autoChangePopDTO = (AutoChangePopDTO) list.get(i);
//        String empId = autoChangePopDTO.getEmpId();
//        chgPersonTail = new ChgPersonTail();
//        ChgpersonEmpInfoDTO chgpersonEmpInfoDTO = chgpersonDoBS.findChgpersonEmpInfo(orgID, empId);
//        
//        chgPersonTail.setEmpId(new Integer(empId));
//        
//        chgPersonTail.setTemp_name(chgpersonEmpInfoDTO.getEmpName());
//        chgPersonTail.setCardKind(new Integer(chgpersonEmpInfoDTO.getCardKind()));
//        chgPersonTail.setTemp_cardNum(chgpersonEmpInfoDTO.getCardNum());
//        chgPersonTail.setBirthday(chgpersonEmpInfoDTO.getBirthday());
//        chgPersonTail.setDept(chgpersonEmpInfoDTO.getDept());
//        chgPersonTail.setTel(chgpersonEmpInfoDTO.getTel());
//        chgPersonTail.setCardNum(chgpersonEmpInfoDTO.getCardNum());
//        
//        chgPersonTail.setTemp_empPay(chgpersonEmpInfoDTO.getEmpPay());
//        chgPersonTail.setTemp_orgPay(chgpersonEmpInfoDTO.getOrgPay());
//        chgPersonTail.setSalaryBase(chgpersonEmpInfoDTO.getSalaryBase());
//        
//        chgpersonDoBS.insertChgpersonDO(orgID,null, chgDate, "3", null, null, null,"1",chgPersonTail,securityInfo);
//      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_showChgpersonDoListAC");
  }

}
