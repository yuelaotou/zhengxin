package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;
/**
 * Copy Right Information : 判断存在相同身份证号相同，但是姓名不同的职工继续添加的Action Goldsoft Project :
 * TraninSaveAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class TraninSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      TraninAddAF traninAddAF = (TraninAddAF) form;
      TranInTail tranInTail = traninAddAF.getTranInTail();
      String traninTailsex = traninAddAF.getTraninTailsex();
      tranInTail.setSex(new Integer(traninTailsex));
      String empName = tranInTail.getName();
      String cardNum = tranInTail.getCardNum();
      String noteNum = traninAddAF.getNoteNum();
      String tranInHeadById = traninAddAF.getTranInHeadId();
      String inOrgId = traninAddAF.getInOrgId();
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String info = traninBS.addTranInTail_sy(inOrgId, noteNum, tranInTail,securityInfo);
      if (info.equals("showTraninListAC")) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
            false));
        saveErrors(request, messages);
        return mapping.findForward("showTraninListAC");
      }
      if (info.equals("employeeMaintainAC")) {
        List list = traninBS.queryEmp_sy(empName, cardNum);
        traninAddAF.setNoteNum(noteNum);
        traninAddAF.setInOrgId(inOrgId);
        traninAddAF.setTranInTail(tranInTail);
        traninAddAF.setList(list);
        request.setAttribute("traninAddAF", traninAddAF);
        return mapping.findForward("showemppop");
      }
      if (info.equals("sameEmployeeMaintainAC")) {
        List list = traninBS.querySameCompanyEmp_sy(inOrgId,empName, cardNum);
        traninAddAF.setNoteNum(noteNum);
        traninAddAF.setInOrgId(inOrgId);
        traninAddAF.setTranInTail(tranInTail);
        traninAddAF.setList(list);
        request.setAttribute("traninAddAF", traninAddAF);
        request.getSession().setAttribute("Magssage","sameorg");
        return mapping.findForward("showemppop");
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninListAC");
  }

}
