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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoIdAF;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonEmpAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;

/**
 * Copy Right Information : 当人员变更时出现相同身份证号不同姓名时，如果选择继续添加则使用这个Action Goldsoft
 * Project : ChgpersonSaveAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.2.20
 */
public class ChgpersonSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try{
      ChgpersonEmpAF chgpersonEmpAF = (ChgpersonEmpAF)form;
      String chgMap_1=chgpersonEmpAF.getChgMap_1();//变更类型
      String documentMap_1=chgpersonEmpAF.getDocumentMap_1();//证件类型
      String sexMap_1=chgpersonEmpAF.getSexMap_1();//性别
      String partInMap_1=chgpersonEmpAF.getPartInMap_1();//是否参与汇缴
      String chgreasonMap_1=chgpersonEmpAF.getChgreasonMap_1();//变更原因
      ChgPersonTail chgPersonTail = chgpersonEmpAF.getChgPersonTail();
    
      HttpSession session = request.getSession();
      String orgID=(String)session.getAttribute("orgID");//单位编号
      String chgDate=(String)session.getAttribute("chgDate");//变更年月

      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      if(chgMap_1.equals("1")){//类型为：开户
        String flag=chgpersonDoBS.saveChgpersonDO(orgID,null,chgDate,chgMap_1,documentMap_1,sexMap_1,partInMap_1,chgreasonMap_1,chgPersonTail,null,securityInfo); 
        if(flag.equals("true")){
          List returnOtherList = new ArrayList();
          returnOtherList = chgpersonDoBS.getOtherOrgMessage_WL(orgID, chgPersonTail);
          ChgpersonDoIdAF chgpersonDoIdAF=new ChgpersonDoIdAF();
          chgpersonDoIdAF.setList(returnOtherList);
          session.setAttribute("chgpersonEmpAF_WL", chgpersonEmpAF);
          request.setAttribute("chgpersonDoIdAF", chgpersonDoIdAF);
          return mapping.findForward("to_chgperson_choose");
        }
      }else{//类型为：启封或封存
        chgpersonDoBS.insertChgpersonDO(orgID,null, chgDate, chgMap_1, null, null, null,chgreasonMap_1,chgPersonTail,securityInfo);
      }

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }catch(Exception e){
    }
    return mapping.findForward("to_chgperson_save");
  }

}
