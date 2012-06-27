package org.xpup.hafmis.sysloan.dataready.develop.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;

/**
 * 封装了添加开发商内容的Form
 * 
 * @author 付云峰
 */
public class DevelopNewAF extends ActionForm {

  /** 开发商实例 */
  private Developer developer = new Developer();

  /** 用此变量来判断是否为修改 */
  private String type_button = "";

  /** 用此变量来判断是否为弹出矿 */
  private String type_window = "";

  public Developer getDeveloper() {
    return developer;
  }

  public void setDeveloper(Developer developer) {
    this.developer = developer;
  }

  public String getType_button() {
    return type_button;
  }

  public void setType_button(String type_button) {
    this.type_button = type_button;
  }

  public String getType_window() {
    return type_window;
  }

  public void setType_window(String type_window) {
    this.type_window = type_window;
  }

}
