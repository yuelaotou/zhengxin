package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoIdAF;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonEmpAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;

public class ChgpersonDOSaveAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    ChgpersonEmpAF chgpersonEmpAF = (ChgpersonEmpAF) form;
    try {
      // bit add
      String str1 = chgpersonEmpAF.getTemp();
      if (str1.equals("0")) {
        return mapping.findForward("to_chgperson_save");
      }
      String chgMap_1 = chgpersonEmpAF.getChgMap_1();// 变更类型
      String documentMap_1 = chgpersonEmpAF.getDocumentMap_1();// 证件类型
      String sexMap_1 = chgpersonEmpAF.getSexMap_1();// 性别
      String partInMap_1 = chgpersonEmpAF.getPartInMap_1();// 是否参与汇缴
      String chgreasonMap_1 = chgpersonEmpAF.getChgreasonMap_1();// 变更原因
      ChgPersonTail chgPersonTail = chgpersonEmpAF.getChgPersonTail();

      String chgreasonMap_2 = chgpersonEmpAF.getChgreason_2(); // 开户的时候变更类型
      HttpSession session = request.getSession();
      String orgID = (String) session.getAttribute("orgID");// 单位编号
      String chgDate = (String) session.getAttribute("chgDate");// 变更年月

      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      if (chgMap_1.equals("1")) {// 类型为：开户
        // 判断添加的职工是否存在 身份证号相同，但是姓名不同的记录
        if (chgPersonTail.getCardKind().toString().equals("0")) {
          List list = chgpersonDoBS.isCardNumSame(orgID, chgPersonTail
              .getName(), chgPersonTail.getCardNum());
          if (list.size() > 0) {
            Map chgMap = BusiTools.listBusiProperty(BusiConst.CHGSTATUS);
            chgpersonEmpAF.setChgMap(chgMap);
            Map documentMap = BusiTools
                .listBusiProperty(BusiConst.DOCUMENTSSTATE);
            chgpersonEmpAF.setDocumentMap(documentMap);
            Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
            chgpersonEmpAF.setSexMap(sexMap);
            Map partInMap = BusiTools.listBusiProperty(BusiConst.YesNo);
            chgpersonEmpAF.setPartInMap(partInMap);
            Map chgreasonMap = BusiTools
                .listBusiProperty(BusiConst.CHGPERSONREASON);
            chgpersonEmpAF.setChgreasonMap(chgreasonMap);
            chgpersonEmpAF.setOrgID(orgID);

            Map chgreasonMap2 = BusiTools
                .listBusiProperty(BusiConst.CHGPERSONREASON);
            chgpersonEmpAF.setChgreasonMap_2(chgreasonMap2); // 新增开户的变更原因
            request.setAttribute("chgpersonEmpAF", chgpersonEmpAF);
            request.setAttribute("flag", "1");
            return mapping.findForward("to_chgpersonsave_jsp");
          }
        }
        String flag = chgpersonDoBS.saveChgpersonDO(orgID, null, chgDate,
            chgMap_1, documentMap_1, sexMap_1, partInMap_1, chgreasonMap_2,
            chgPersonTail, null, securityInfo);
        if (flag.equals("true")) {
          List returnOtherList = new ArrayList();
          returnOtherList = chgpersonDoBS.getOtherOrgMessage_WL(orgID,
              chgPersonTail);
          ChgpersonDoIdAF chgpersonDoIdAF = new ChgpersonDoIdAF();
          chgpersonDoIdAF.setList(returnOtherList);
          session.setAttribute("chgpersonEmpAF_WL", chgpersonEmpAF);
          request.setAttribute("chgpersonDoIdAF", chgpersonDoIdAF);
          return mapping.findForward("to_chgperson_choose");
        }
      } else {// 类型为：启封或封存
        chgpersonDoBS.insertChgpersonDO(orgID, null, chgDate, chgMap_1, null,
            null, null, chgreasonMap_1, chgPersonTail, securityInfo);
        // wuht
        chgpersonDoBS.updateTranInTail(orgID, chgPersonTail.getEmpId()
            .toString(), "1");
      }

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
    }
    return mapping.findForward("to_chgperson_save");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    return mapping.findForward("to_chgperson_list");
  }
}
