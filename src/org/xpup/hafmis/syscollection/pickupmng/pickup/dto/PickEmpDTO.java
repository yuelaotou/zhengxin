package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;
import java.io.Serializable;
import java.math.BigDecimal;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;
public class PickEmpDTO implements ExpDto {
  private String empId;
  private String empName;
  private String cardKind;
  private String cardNumber;
  private String pickType;
  private String pickReason;
  private String pickBalance;
  private String maxPickMon;
  private String houseNum;
  public String getMaxPickMon() {
    return maxPickMon;
  }
  public void setMaxPickMon(String maxPickMon) {
    this.maxPickMon = maxPickMon;
  }
  public PickEmpDTO(){}
  public String getInfo(String s) {
    if(s.equals("empId"))
      return empId;
    if(s.equals("empName"))
      return empName;
    if(s.equals("cardKind"))
      return cardKind;
    if(s.equals("cardNumber"))
      return cardNumber;
    if(s.equals("pickType"))
      return pickType;
    if(s.equals("pickReason"))
      return pickReason;
    if(s.equals("pickBalance"))
      return pickBalance;
    if(s.equals("maxPickMon"))
      return maxPickMon;
    if(s.equals("houseNum"))
      return houseNum;
    return null;
  }
  public String getCardNumber() {
    return cardNumber;
  }
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getPickBalance() {
    return pickBalance;
  }
  public void setPickBalance(String pickBalance) {
    this.pickBalance = pickBalance;
  }
  public String getPickReason() {
    return pickReason;
  }
  public void setPickReason(String pickReason) {
    this.pickReason = pickReason;
  }
  public String getPickType() {
    return pickType;
  }
  public void setPickType(String pickType) {
    this.pickType = pickType;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }
  public String getHouseNum() {
    return houseNum;
  }
  public void setHouseNum(String houseNum) {
    this.houseNum = houseNum;
  }
}
