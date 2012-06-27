package org.xpup.hafmis.syscommon.domain.entity;

public class Transactor {

  /**
   * 姓名
   */
  private String name = null;

  /**
   * 电话
   */
  private String telephone = null;

  /**
   * 移动电话
   */
  private String mobileTelephone = null;

  /**
   * 电子邮箱
   */
  private String email = null;

  /**
   * @roseuid 45EFFE2C01C7
   */
  public Transactor() {

  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobileTelephone() {
    return mobileTelephone;
  }

  public void setMobileTelephone(String mobileTelephone) {
    this.mobileTelephone = mobileTelephone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
}
