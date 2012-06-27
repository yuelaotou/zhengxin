package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;

public class AddTraninMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "add");
    map.put("button.update", "addUpdate");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("showTraninListAC");
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
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
      // 判断添加的职工是否存在 身份证号相同，但是姓名不同的记录
      if (tranInTail.getCardKind().toString().equals("0")) {
        List list = traninBS.isCardNumSame(empName, cardNum);
        if (list.size()>0) {    
          Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
          Map documentsstateMap = BusiTools
              .listBusiProperty(BusiConst.DOCUMENTSSTATE);
          traninAddAF.setSexMap(sexMap);
          traninAddAF.setDocumentsstateMap(documentsstateMap);
          traninAddAF.setType("1");
          request.setAttribute("traninAddAF", traninAddAF);
          request.setAttribute("flag", "1");
          return mapping.findForward("to_traninnew_jsp");
        }
      }
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

  public ActionForward addUpdate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      TraninAddAF traninAddAF = (TraninAddAF) form;
      String tranInHeadId = traninAddAF.getTranInHeadId();
      TranInTail tranInTail = traninAddAF.getTranInTail();
      String traninTailsex = traninAddAF.getTraninTailsex();
      tranInTail.setSex(new Integer(traninTailsex));
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      // 判断添加的职工是否存在 身份证号相同，但是姓名不同的记录
      if (tranInTail.getCardKind().toString().equals("0")) {
        List list = traninBS.isUpdateCardNumSame(traninAddAF.getTranInTail()
            .getName(), traninAddAF.getTranInTail().getCardNum(), tranInTail
            .getEmpId().toString());
        if (list.size() > 0) {
          Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
          Map documentsstateMap = BusiTools
              .listBusiProperty(BusiConst.DOCUMENTSSTATE);
          traninAddAF.setSexMap(sexMap);
          traninAddAF.setDocumentsstateMap(documentsstateMap);
          traninAddAF.setType("2");
          request.setAttribute("traninAddAF", traninAddAF);
          request.setAttribute("flag", "2");
          return mapping.findForward("to_traninnew_jsp");
        }
      }
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      traninBS.updataTranInTail_sy(tranInTail, tranInHeadId,securityInfo);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("更新成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("更新失败！",
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_train_add");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninListAC");
  }
}
