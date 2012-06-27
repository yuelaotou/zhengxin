package org.xpup.hafmis.signjoint.entity;

public class Sign {

  private Integer id;//对应着RQ001中的ID主键
  private Integer sign;//签约生成的唯一标号
  private String name;//职工姓名
  private String card_num;//身份证号
  private String empid;//职工号
  private String bank_card;//银行卡号
  public Sign()
  {
  }
  public Sign( Integer sign, String name, String card_num, String empid, String bank_card) {    this.sign = sign;
    this.name = name;
    this.card_num = card_num;
    this.empid = empid;
    this.bank_card = bank_card;
  }
  public String getBank_card() {
    return bank_card;
  }
  public void setBank_card(String bank_card) {
    this.bank_card = bank_card;
  }
  public String getCard_num() {
    return card_num;
  }
  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getSign() {
    return sign;
  }
  public void setSign(Integer sign) {
    this.sign = sign;
  }
  
  
  
  
  
  
  
  
  
}
