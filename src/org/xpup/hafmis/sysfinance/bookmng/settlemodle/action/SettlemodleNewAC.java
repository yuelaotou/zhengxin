/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysfinance.bookmng.settlemodle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.bsinterface.ISettlemodleBS;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.dto.SettlemodleDTO;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.form.SettlemodleNewAF;

/** 
 * MyEclipse Struts
 * Creation date: 10-24-2007
 * Copy Right Information   : 结算方式
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-24-2007
 * XDoclet definition:
 * @struts.action path="/settlemodleNewAC" name="settlemodleNewAF" scope="request" validate="true"
 */
public class SettlemodleNewAC extends DispatchAction {
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
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SettlemodleNewAF settlemodleNewAF = (SettlemodleNewAF) form;// TODO Auto-generated method stub
    try {
      //权限
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      //bookId
      String bookId = securityInfo.getBookId();
      String paramExplain = settlemodleNewAF.getParamExplain().trim();
      SettlemodleDTO settlemodleDTO = new SettlemodleDTO();
      settlemodleDTO.setBookId(bookId);
      settlemodleDTO.setParamExplain(paramExplain);
      
      ISettlemodleBS settlemodleBS = (ISettlemodleBS) BSUtils.getBusinessService(
          "settlemodleBS", this, mapping.getModuleConfig());
      //判断输入的结算方式在FN102.PARAM_NUM=3的记录的PARAM_EXPLAIN是否存在
      boolean temp_paramExplain = settlemodleBS.is_SettlemodleParamExplainInsert(settlemodleDTO);
      if(temp_paramExplain){
        //没有记录  插入FN311 ,FN102
        settlemodleBS.insertSettlemodleInfo(settlemodleDTO, securityInfo);
      }else{
        //有记录
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该结算方式已存在!",
            false));
        saveErrors(request, messages);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
		return mapping.findForward("settlemodleShowAC");
	}
  
  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    SettlemodleNewAF settlemodleNewAF = (SettlemodleNewAF) form;// TODO Auto-generated method stub
    try {
      //权限
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      //bookId
      String bookId = securityInfo.getBookId();
      String paraId = settlemodleNewAF.getParaId();
      String paramExplain = settlemodleNewAF.getParamExplain().trim();
      SettlemodleDTO settlemodleDTO = new SettlemodleDTO();
      settlemodleDTO.setParamExplain(paramExplain);
      settlemodleDTO.setBookId(bookId);
      settlemodleDTO.setParaId(paraId);
      ISettlemodleBS settlemodleBS = (ISettlemodleBS) BSUtils.getBusinessService(
          "settlemodleBS", this, mapping.getModuleConfig());
      
      //根据ID判断记录是否存在
      boolean temp_isCredencechar = settlemodleBS.isSettlemodleById(paraId);
      if(!temp_isCredencechar){
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该记录不存在,不能修改!",
            false));
        saveErrors(request, messages);
        return mapping.findForward("settlemodleShowAC");
      }
      
      //判断输入的结算方式在FN102.PARAM_NUM=3的记录的PARAM_EXPLAIN是否存在
      boolean temp_paramExplain = settlemodleBS.is_SettlemodleParamExplainUpdate(settlemodleDTO);
      if(temp_paramExplain){
        //没有记录  插入FN311 ,更新FN102
        settlemodleBS.updateSettlemodleInfo(settlemodleDTO, securityInfo);
      }else{
        //有记录
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该结算方式已存在!",
            false));
        saveErrors(request, messages);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("settlemodleShowAC");
  }
}