package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChoIdAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpKhCriteronsAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class EmpOpenSaveAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "save");
    map.put("button.update", "modify");
    map.put("button.back", "back");
    return map;
  }

  public String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.accountmng.accountopen.action.EmpOpenSaveAC";
  }

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    EmpKhCriteronsAF af = (EmpKhCriteronsAF) form;
    if(!isTokenValid(request,true))
    {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",false));         
      saveErrors(request, messages);
      saveToken(request);
    }else{
      saveToken(request);
      String id = null;
      String orgId = (String) request.getSession().getAttribute("org.id").toString();
      String emppaymonth = (String) request.getSession().getAttribute("org.emppaymonth");
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
        .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
        //  判断添加的职工是否存在 身份证号相同，但是姓名不同的记录
        List list = orgOpenAccountBS.isCardNumSame(af.getEmp().getEmpInfo().getName(), af.getEmp().getEmpInfo().getCardNum());
        if (list.size()>0) {
          Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
          af.setSexMap(sexMap);
          Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
          af.setCardKindMap(cardKindMap);
          af.setType("1");
          request.setAttribute("empKhCriteronsAF", af);
          request.setAttribute("flag", "1");
          return mapping.findForward("emp_open_new");
        }
        // 判断此单位是否已开户
        if (orgOpenAccountBS.validateOpenStatus(orgId)) {
          // 如果不存在则以及添加员工
          try {
              int flag = 0;
              flag = orgOpenAccountBS.saveEmployee(orgId, af.getEmp(), emppaymonth,
                  flag);
              if (flag == 1) {
                List tochooselist = new ArrayList();
                tochooselist = orgOpenAccountBS.getEmpFromOthers(orgId, af.getEmp());
                EmpChoIdAF empchoidaf = new EmpChoIdAF();
                empchoidaf.setList(tochooselist);
                saveToken(request);
                request.setAttribute("empChoIdAF", empchoidaf);
                request.getSession().setAttribute("empkhAF", af);
                return mapping.findForward("emp_choose");
              } else {
                orgOpenAccountBS.saveEmployeeInfo(orgId, af.getEmp(), emppaymonth, id,securityInfo);
              }
  
              EmpKhCriteronsAF aff = new EmpKhCriteronsAF();
              Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
              aff.setSexMap(sexMap);
              Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
              aff.setCardKindMap(cardKindMap);
              aff.setType("1");
              request.setAttribute("empKhCriteronsAF", aff);
              request.setAttribute("flag", "0");
              return mapping.findForward("emp_open_new");
  
          } catch (BusinessException bex) {
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
                .getLocalizedMessage().toString(), false));
            saveErrors(request, messages);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }else{
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该单位已经是开户状态不能在添加员工!", false));
          saveErrors(request, messages);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    EmpKhCriteronsAF aff = new EmpKhCriteronsAF();
    Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
    af.setSexMap(sexMap);
    Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    af.setCardKindMap(cardKindMap);
    af.setType("1");
    request.setAttribute("empKhCriteronsAF", af);
    request.setAttribute("flag", "0");
    return mapping.findForward("emp_open_new");
  }

  public ActionForward modify(ActionMapping mapping, ActionForm form,
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
      // 判断添加的职工是否存在 身份证号相同，但是姓名不同的记录
      List list = orgOpenAccountBS.isCardNumUpdateSame(af.getEmp().getEmpInfo().getName(), af.getEmp().getEmpInfo().getCardNum(),empid);
      if (list.size()>0) {
        Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
        af.setSexMap(sexMap);
        Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
        af.setCardKindMap(cardKindMap);
        af.getEmp().setId(empid);
        af.setType("1");
        request.setAttribute("empKhCriteronsAF", af);
        request.setAttribute("flag", "2");
        return mapping.findForward("emp_open_new");
      }
      Emp emp = af.getEmp();
      ActionMessages messages1 = new ActionMessages();
      boolean flag1 = orgOpenAccountBS.queryEmployee(orgId,empid );
      if (!flag1) {
        messages1.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "该职工正在还款中，不能修改身份证号", false));
        saveErrors(request, messages1);
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

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    return mapping.findForward("show_employee");
  }
}
