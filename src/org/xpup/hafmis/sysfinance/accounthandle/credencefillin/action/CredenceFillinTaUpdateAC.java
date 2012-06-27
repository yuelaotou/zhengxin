package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

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
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTaAF;

/**
 * 凭证修改页确定按钮的Action
 * 
 * @author 刘冰
 */
public class CredenceFillinTaUpdateAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
        .getBusinessService("credenceFillinBS", this, mapping.getModuleConfig());
    CredenceFillinTaAF credenceFillinTaAF = (CredenceFillinTaAF) form;
    try {
      CredenceFillinTaShowDTO credenceFillinTaShowDTO = credenceFillinTaAF
          .getCredenceFillinTaShowDTO();
      String listAllContent = credenceFillinTaAF.getListAllContent();
      // 得到修改前的日期,办事处与凭证号
      String chargeOldUpDate = (String) request.getSession().getAttribute(
          "modify_chargeoldupcate");
      String modify_oldCredenceNum = (String) request.getSession()
          .getAttribute("modify_credencenum");
      String oldOffice = (String) request.getSession().getAttribute(
          "modify_oldoffice");
      credenceFillinTaShowDTO.setModify_oldCredenceNum(modify_oldCredenceNum);
      credenceFillinTaShowDTO.setChargeOldUpDate(chargeOldUpDate);
      credenceFillinTaShowDTO.setOldOffice(oldOffice);
      credenceFillinBS.updateCredenceFillinTa(credenceFillinTaShowDTO,
          securityInfo, listAllContent);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("credenceFillinTdShowdAC");
    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("保存失败！",
          false));
      saveErrors(request, messages);

    }
    return mapping.findForward("credenceFillinTdShowdAC");
  }
}
