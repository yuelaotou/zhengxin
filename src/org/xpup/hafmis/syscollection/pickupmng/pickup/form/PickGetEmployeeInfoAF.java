/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.syscollection.pickupmng.pickup.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PickGetEmployeeInfoAF extends ActionForm {
  private Serializable iid = null;
  private String buttonState;
  private String empId;
  private String name;
  private String empName;
  private String cardKind;
  private String reason;
  private String cardNum;
  private String curBalance;
  private String preBalance;
  private String yearPickNumber;
  private String yearPickBalance;
  private String balance;
  private String houseNum="";
  
  private String old_yearpay="";
  public String getOld_yearpay() {
    return old_yearpay;
  }
  public void setOld_yearpay(String old_yearpay) {
    this.old_yearpay = old_yearpay;
  }
  public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}
  public String getYearPickBalance() {
    return yearPickBalance;
  }
  public void setYearPickBalance(String yearPickBalance) {
    this.yearPickBalance = yearPickBalance;
  }
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getCurBalance() {
    return curBalance;
  }
  public void setCurBalance(String curBalance) {
    this.curBalance = curBalance;
  }
 
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getPreBalance() {
    return preBalance;
  }
  public void setPreBalance(String preBalance) {
    this.preBalance = preBalance;
  }
  public String getYearPickNumber() {
    return yearPickNumber;
  }
  public void setYearPickNumber(String yearPickNumber) {
    this.yearPickNumber = yearPickNumber;
  }
  public String getBalance() {
    return balance;
  }
  public void setBalance(String balance) {
    this.balance = balance;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  public String getButtonState() {
    return buttonState;
  }
  public void setButtonState(String buttonState) {
    this.buttonState = buttonState;
  }
  public Serializable getIid() {
    return iid;
  }
  public void setIid(Serializable iid) {
    this.iid = iid;
  }
  public String getHouseNum() {
    return houseNum;
  }
  public void setHouseNum(String houseNum) {
    this.houseNum = houseNum;
  }
 
}