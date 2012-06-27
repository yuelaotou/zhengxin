package org.xpup.hafmis.signjoint.dto;
/**
 * 封装请求数据
 * @author Administrator
 *
 */
public class RequestSignDTO  {
  private String name="";//职工姓名
  private String cardnum="";//身份证号
  private String empid="";//职工号
  private String banknum="";//银行卡号

  public RequestSignDTO()
  {
  }
  public RequestSignDTO(String banknum,String name,String cardnum,String empid)
  {
    this.banknum=banknum;
    this.name=name;
    this.cardnum=cardnum;
    this.empid=empid;
  }
  public String getBanknum() {
    return banknum;
  }
  public void setBanknum(String banknum) {
    this.banknum = banknum;
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

}
