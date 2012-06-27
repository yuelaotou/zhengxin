package org.xpup.hafmis.signjoint.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.demo.domain.entity.Demo;

public class SignAF extends IdAF{
  private String orgid="";
  private String orgname="";
  private String empid="";
  private String empname="";
  private List list=new ArrayList();
  
  public void reset(ActionMapping mapping, ServletRequest request) {
    orgid="";
    orgname="";
    empid="";
    empname="";
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

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

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  
  
  
}
