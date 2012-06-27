package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

/**
 * 错帐调整维护的MainTain Action
 * 
 * @author 付云峰
 */
public class AdjustAccountTbMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.delete", "deleteAdjustAccountInfo");
    map.put("button.print", "printAdjustAccountInfo");
    return map;
  }

  /**
   * 删除错账调整业务的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward deleteAdjustAccountInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      IdAF idAF = (IdAF) form;
      String flowHeadIdAndType = (String) idAF.getId();
      String[] str = flowHeadIdAndType.split(",");
      String flowHeadId = str[0];
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustAccountBS", this, mapping
              .getModuleConfig());

      adjustAccountBS.deleteAdjustAccountInfo(flowHeadId, securityInfo);
    } catch (BusinessException bex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    }catch (Exception e) {
        e.printStackTrace();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
            false));
        saveErrors(request, messages);
      }
    return mapping.findForward("adjustAccountTbShowdAC");
  }

  /**
   * 打印凭证的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printAdjustAccountInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
      String flowHeadIdAndType = (String) idAF.getId();
      String[] str = flowHeadIdAndType.split(",");
      String flowHeadId = str[0];
      String bizType = "";
      if (str.length>1) {
        bizType = str[1];
      }
      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustAccountBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      loancallbackTaAF = adjustAccountBS.findPrintCallbackInfo(flowHeadId);
      String bizDate = "";
      String temp_bizDate = securityInfo.getUserInfo().getPlbizDate();
      bizDate = temp_bizDate.substring(0, 4)+"-"+temp_bizDate.substring(4, 6)+"-"+temp_bizDate.substring(6, 8);
      request.setAttribute("bizDate",bizDate);
      request.setAttribute("LoancallbackTaAF", loancallbackTaAF);
      if(bizType.equals("1")){
        // 进入发放凭证
        return mapping.findForward("to_adjustaccount_putout_print");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_adjustaccount_print");
  }

}
