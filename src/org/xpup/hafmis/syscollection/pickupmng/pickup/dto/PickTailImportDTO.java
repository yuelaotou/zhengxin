package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;
import org.xpup.common.util.imp.domn.interfaces.impDto;
public class PickTailImportDTO extends impDto{
  private String empId;
  private String empName;
  private String cardKind;
  private String cardNumber;
  private String pickReason;
  private String pickType;
  private String pickBalance;
  private String maxPickBalance;
  private String houseNum;
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }
  public String getCardNumber() {
    return cardNumber;
  }
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
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
  public String getMaxPickBalance() {
    return maxPickBalance;
  }
  public void setMaxPickBalance(String maxPickBalance) {
    this.maxPickBalance = maxPickBalance;
  }
  public String getHouseNum() {
    return houseNum;
  }
  public void setHouseNum(String houseNum) {
    this.houseNum = houseNum;
  }
}
