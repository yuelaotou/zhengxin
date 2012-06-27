package org.xpup.hafmis.syscollection.chgbiz.chgperson.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Copy Right Information :增加自动启封按钮，功能为：点击后，弹出窗口，显示该单位状态为转入封存的职工，可以进行选择（可全选），选择后点击确定后，将选中职工加入到变更清册中，变更类型为启封。
 * AutoChangePopAF
 * 
 * @Version : v1.0
 * @author : 吴洪涛
 * @Date : 2008.6.18
 */
public class ChgPersonAutoopenAF extends ActionForm {
  
  /**
   * 列表显示的内容
   */
  private List list;
  
  private List listAll;

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

  public List getListAll() {
    return listAll;
  }

  public void setListAll(List listAll) {
    this.listAll = listAll;
  }
  
}
