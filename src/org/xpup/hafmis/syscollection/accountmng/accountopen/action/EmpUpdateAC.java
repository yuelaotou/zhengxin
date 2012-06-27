package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.HashMap;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpKhCriteronsAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
/**
 * Copy Right Information : 当职工信息修改时出现相同身份证号不同姓名时，如果选择继续添加则使用这个Action Goldsoft
 * Project : EmpSaveAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.2.19
 */
public class EmpUpdateAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    EmpKhCriteronsAF af = (EmpKhCriteronsAF) form;
    String paginationKey = getPaginationKey();
    String orgId = (String) request.getSession().getAttribute("org.id").toString();
    String empid = request.getParameter("hidden");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      Emp emp = af.getEmp();
      boolean flag = orgOpenAccountBS.modifyEmployee(empid, emp,securityInfo);
      ActionMessages messages = new ActionMessages();
      if (!flag) {
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "该信息职工已存在", false));
          saveErrors(request, messages);
          EmpKhCriteronsAF aff = new EmpKhCriteronsAF();
          af.getEmp().setId(empid);
          Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
          af.setSexMap(sexMap);
          Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
          af.setCardKindMap(cardKindMap);
          af.setType("2");
          request.setAttribute("empKhCriteronsAF", af);
          request.setAttribute("flag", "0");
          return mapping.findForward("emp_open_new");
      }
      HashMap map = new HashMap();
      map.put("orgId", orgId);
      Pagination pagination = new Pagination(0, 10, 1, "emp.empInfo.id", "ASC",
          map);
      request.getSession().setAttribute(paginationKey, pagination);
      } catch (BusinessException bex) {
        bex.printStackTrace();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);
      }catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("show_employee");
  }
  public String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.accountmng.accountopen.action.EmpOpenSaveAC";
  }
}
