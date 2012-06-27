package org.xpup.hafmis.syscollection.chgbiz.chgperson.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Copy Right Information : 自动变更弹出框的ActionForm Goldsoft Project :
 * AutoChangePopAF
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.18
 */
public class AutoChangePopAF extends ActionForm {
  
  /**
   * 列表显示的内容
   */
  private List list;

  private String empId = "";
  
  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
  
}
