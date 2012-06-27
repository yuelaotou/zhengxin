package org.xpup.hafmis.syscollection.tranmng.tranin.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;

/**
 * Copy Right Information : 判断存在相同身份证号相同，但是姓名不同的职工继续修改的Action Goldsoft Project :
 * TraninUpdateAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class TraninUpdateAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      TraninAddAF traninAddAF = (TraninAddAF) form;
      String tranInHeadId = traninAddAF.getTranInHeadId();
      TranInTail tranInTail = traninAddAF.getTranInTail();
      String traninTailsex = traninAddAF.getTraninTailsex();
      tranInTail.setSex(new Integer(traninTailsex));
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
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
