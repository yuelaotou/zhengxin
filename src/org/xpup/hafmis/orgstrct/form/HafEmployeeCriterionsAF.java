package org.xpup.hafmis.orgstrct.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class HafEmployeeCriterionsAF extends CriterionsAF {

  private static final long serialVersionUID = 1190674168059570824L;

  private String username = null;

  private String realName = null;

  private String email = null;

  private int sex = -1;

  private int duty = -1;

  private int enabled = -1;

  private int locked = -1;

  public int getDuty() {
    return duty;
  }

  public void setDuty(int duty) {
    this.duty = duty;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getEnabled() {
    return enabled;
  }

  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }

  public int getLocked() {
    return locked;
  }

  public void setLocked(int locked) {
    this.locked = locked;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    super.reset(arg0, arg1);
    this.sex = -1;
    this.username = null;
    this.realName = null;
    this.email = null;
    this.enabled = -1;
    this.locked = -1;
    this.duty = -1;
  }

}
