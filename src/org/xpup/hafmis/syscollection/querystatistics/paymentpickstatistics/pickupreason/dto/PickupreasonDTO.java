package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.dto;

public class PickupreasonDTO {
private String pickupreason="";
private String numberpeople="";
private String countpeople="";
private String pickmoney=null;
private String countmoney="";
public String getCountmoney() {
  return countmoney;
}
public void setCountmoney(String countmoney) {
  this.countmoney = countmoney;
}
public String getCountpeople() {
  return countpeople;
}
public void setCountpeople(String countpeople) {
  this.countpeople = countpeople;
}
public String getNumberpeople() {
  return numberpeople;
}
public void setNumberpeople(String numberpeople) {
  this.numberpeople = numberpeople;
}

public String getPickmoney() {
  return pickmoney;
}
public void setPickmoney(String pickmoney) {
  this.pickmoney = pickmoney;
}
public String getPickupreason() {
  return pickupreason;
}
public void setPickupreason(String pickupreason) {
  this.pickupreason = pickupreason;
}
}
