package org.xpup.hafmis.sendmail.sendmial.form;

import org.apache.struts.action.ActionForm;

public class MailinfoAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = 5159742678721582921L;

  private String addresser;// 发件人帐户

  private String addresserMail; //发件人邮箱
  
  private String addresserPassword;// 发件人帐户密码

  private String mailBoxType;// 发件人邮箱类型

  private String addresseeA;// 收件人邮箱A

  private String addresseeB;// 收件人邮箱B

  private String subjectName;// 发送主题
  
  private String mailId;  //正在使用的信息id

  public String getAddresseeA() {
    return addresseeA;
  }

  public void setAddresseeA(String addresseeA) {
    this.addresseeA = addresseeA;
  }

  public String getAddresseeB() {
    return addresseeB;
  }

  public void setAddresseeB(String addresseeB) {
    this.addresseeB = addresseeB;
  }

  public String getAddresser() {
    return addresser;
  }

  public void setAddresser(String addresser) {
    this.addresser = addresser;
  }

  public String getAddresserPassword() {
    return addresserPassword;
  }

  public void setAddresserPassword(String addresserPassword) {
    this.addresserPassword = addresserPassword;
  }

  public String getMailBoxType() {
    return mailBoxType;
  }

  public void setMailBoxType(String mailBoxType) {
    this.mailBoxType = mailBoxType;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getMailId() {
    return mailId;
  }

  public void setMailId(String mailId) {
    this.mailId = mailId;
  }

  public String getAddresserMail() {
    return addresserMail;
  }

  public void setAddresserMail(String addresserMail) {
    this.addresserMail = addresserMail;
  }
}
