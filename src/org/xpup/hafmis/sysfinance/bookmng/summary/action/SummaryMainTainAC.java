/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysfinance.bookmng.summary.action;

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
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.summary.bsinterface.ISummaryBS;
import org.xpup.hafmis.sysfinance.bookmng.summary.dto.SummaryDTO;

/** 
 * MyEclipse Struts
 * Creation date: 10-25-2007
 * Copy Right Information   : 常用摘要
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-25-2007
 * XDoclet definition:
 * @struts.action path="/summaryMainTainAC" name="idAF" parameter="method" scope="request" validate="true"
 */
public class SummaryMainTainAC extends LookupDispatchAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward updateSettlemodle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    try {
      IdAF idaf = (IdAF) form;// TODO Auto-generated method stub
      String paraId = idaf.getId().toString();
      ISummaryBS summaryBS = (ISummaryBS) BSUtils.getBusinessService(
          "summaryBS", this, mapping.getModuleConfig());
      boolean temp_isSummary = summaryBS.isSummaryById(paraId);
      if(!temp_isSummary){
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该记录不存在,不能修改!",
            false));
        saveErrors(request, messages);
        return mapping.findForward("summaryShowAC");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      temp_isSummary = summaryBS.querySummaryInFn201(securityInfo.getBookId(),paraId);
      if(!temp_isSummary){
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该摘要已被应用不能修改!",
            false));
        saveErrors(request, messages);
        return mapping.findForward("summaryShowAC");
      }
      
      //根据ID 查询常用摘要
      SummaryDTO summaryDTO = summaryBS.querySummaryParamExplainInfo(paraId);
      String paramExplain = summaryDTO.getParamExplain();
      request.setAttribute("paraId", paraId);
      request.setAttribute("paramExplain", paramExplain);
      request.setAttribute("updateInfo", "updateInfo");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("summaryShowAC");
	}
  
  public ActionForward deleteSettlemodle(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      IdAF idaf = (IdAF) form;// TODO Auto-generated method stub
      String paraId = idaf.getId().toString();
      ISummaryBS summaryBS = (ISummaryBS) BSUtils.getBusinessService(
          "summaryBS", this, mapping.getModuleConfig());
      //权限
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      //bookId
      String bookId = securityInfo.getBookId();
      //根据ID 判断是否有记录
      boolean temp_summary = summaryBS.isSummaryById(paraId);
      if(temp_summary){//有记录
        //判断该记录的FN102.PARA_ID在FN201.SUMMAY or FN210.SUMMAY中是否存在
        boolean temp_Existence = summaryBS.isSummaryByParamValue(paraId, bookId);
        if(temp_Existence){//不存在
          //删除 paramId 记录
          summaryBS.deleteSummaryInfo(paraId, securityInfo);
        }else{//存在
          ActionMessages messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该条记录已被应用，不允许删除!",
              false));
          saveErrors(request, messages);
        }
      }else{
        //没有记录
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该记录已删除!",
            false));
        saveErrors(request, messages);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("summaryShowAC");
  }

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "updateSettlemodle");
    map.put("button.delete", "deleteSettlemodle");
    return map;
  }
}