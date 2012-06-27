package org.xpup.hafmis.sysloan.common.biz.emppop.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.common.biz.emppop.bsinterface.IEmpPopBS;

import  org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class EmployeesSendAC extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      return null;
    }

    public String getText(Emp emp) {
      String text = null;

      String a1 = String.valueOf(emp.getId());
      String a2 = emp.getEmpInfo().getName();
      String a3 = emp.getEmpInfo().getCardNum();
      String a4 = String.valueOf(emp.getEmpInfo().getSex());  
      String a5 = String.valueOf(emp.getEmpInfo().getDepartment());
      String a6 = emp.getEmpInfo().getTel();
      String a7 = String.valueOf(emp.getEmpInfo().getMobileTle());
      String a8 = String.valueOf(emp.getSalaryBase());
      System.out.println("1212");
      text = "displays('"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"','"+a7+"','"+a8+"')";

      return text;
    }
  }


