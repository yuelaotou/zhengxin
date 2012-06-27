package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class EmpOperationFlowAF extends CriterionsAF {

  private static final long serialVersionUID = 157830469042818336L;

  private String empid ="";
  
  private String empname = "";

  public String getEmpid() {
    return empid;
  }

  public void setEmpid(String empid) {
    this.empid = empid;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    empid = "";
    empname = "";
  }

}