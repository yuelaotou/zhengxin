package org.xpup.security.commonlogin.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class LoginActionForm extends ActionForm{

  private static final long serialVersionUID = -6902535665644537026L;
  private String userName="";
  private String userPassword="";
  private String newPassword="";
  private String snewPassword="";
  private String validateCode="";
  private String systemId="";
  private String isChooseBook="";
  private String bookId="";
  private String bookName="";
  private String opSystemType="";
  private String bizdate="";
  private List systemsList=new ArrayList();
  private List userBookList=new ArrayList();
  private String systemIdChange="";
  
  
  public String getBookName() {
    return bookName;
  }
  public void setBookName(String bookName) {
    this.bookName = bookName;
  }
  public String getIsChooseBook() {
    return isChooseBook;
  }
  public void setIsChooseBook(String isChooseBook) {
    this.isChooseBook = isChooseBook;
  }
  public String getBookId() {
    return bookId;
  }
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  public List getUserBookList() {
    return userBookList;
  }
  public void setUserBookList(List userBookList) {
    this.userBookList = userBookList;
  }
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getUserPassword() {
    return userPassword;
  }
  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }
  public String getValidateCode() {
    return validateCode;
  }
  public void setValidateCode(String validateCode) {
    this.validateCode = validateCode;
  }
  public String getBizdate() {
    return bizdate;
  }
  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
  }
  public String getOpSystemType() {
    return opSystemType;
  }
  public void setOpSystemType(String opSystemType) {
    this.opSystemType = opSystemType;
  }
  public List getSystemsList() {
    return systemsList;
  }
  public void setSystemsList(List systemsList) {
    this.systemsList = systemsList;
  }
  public String getSystemIdChange() {
    return systemIdChange;
  }
  public void setSystemIdChange(String systemIdChange) {
    this.systemIdChange = systemIdChange;
  }
  public String getNewPassword() {
    return newPassword;
  }
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
  public String getSnewPassword() {
    return snewPassword;
  }
  public void setSnewPassword(String snewPassword) {
    this.snewPassword = snewPassword;
  }
  
  
}
