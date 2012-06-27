package org.xpup.hafmis.signjoint.dto;
//序号|持卡人卡号|持卡人姓名|持卡人身份证号码|公积金帐户号|备注|
public class SendFileDTO {


  private String bankcardid;
  private String name;
  private String cardnum;
  private String empid;
  private String sign;
  public SendFileDTO(String sequence, String bankcardid, String name, String cardnum, String empid, String sign) {
    this.bankcardid = bankcardid;
    this.name = name;
    this.cardnum = cardnum;
    this.empid = empid;
    this.sign = sign;
  }
  public SendFileDTO() {

    this.bankcardid = "";
    this.name = "";
    this.cardnum = "";
    this.empid = "";
    this.sign = "";
  }
  
  
  
  public String getBankcardid() {
    return bankcardid;
  }
  public void setBankcardid(String bankcardid) {
    this.bankcardid = bankcardid;
  }
  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSign() {
    return sign;
  }
  public void setSign(String sign) {
    this.sign = sign;
  }

  
}
