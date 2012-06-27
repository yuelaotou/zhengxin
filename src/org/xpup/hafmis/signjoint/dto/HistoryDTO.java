package org.xpup.hafmis.signjoint.dto;

public class HistoryDTO {
  //id 主键，orgid 单位编号，orgname 单位名称，empid 职工编号，
  //empname 职工姓名，cardnum 身份证号，bankcardid 银行卡号，
  //succ_fail 成功标识，biz_date 办理时间，bank_sure_date 银行确认时间，
  //operater 办理人员,sign 唯一标识
  private String id;
  private String orgid;
  private String orgname;
  private String empid;
  private String empname;
  private String cardnum;
  private String bankcardid;
  private String succ_fail;
  private String biz_date;
  private String bank_sure_date;
  private String operater;
  private String sign;
  public HistoryDTO() {
    this.id = "";
    this.orgid = "";
    this.orgname = "";
    this.empid = "";
    this.empname = "";
    this.cardnum = "";
    this.bankcardid = "";
    this.succ_fail = "";
    this.biz_date = "";
    this.bank_sure_date = "";
    this.operater = "";
    this.sign = "";
  }

  public HistoryDTO(String id, String orgid, String orgname, String empid, String empname, String cardnum, String bankcardid, String succ_fail, String biz_date, String bank_sure_date, String operater, String sign) {
    this.id = id;
    this.orgid = orgid;
    this.orgname = orgname;
    this.empid = empid;
    this.empname = empname;
    this.cardnum = cardnum;
    this.bankcardid = bankcardid;
    this.succ_fail = succ_fail;
    this.biz_date = biz_date;
    this.bank_sure_date = bank_sure_date;
    this.operater = operater;
    this.sign = sign;
  }
  public String getBank_sure_date() {
    return bank_sure_date;
  }
  public void setBank_sure_date(String bank_sure_date) {
    this.bank_sure_date = bank_sure_date;
  }
  public String getBankcardid() {
    return bankcardid;
  }
  public void setBankcardid(String bankcardid) {
    this.bankcardid = bankcardid;
  }
  public String getBiz_date() {
    return biz_date;
  }
  public void setBiz_date(String biz_date) {
    this.biz_date = biz_date;
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
  public String getEmpname() {
    return empname;
  }
  public void setEmpname(String empname) {
    this.empname = empname;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getOperater() {
    return operater;
  }
  public void setOperater(String operater) {
    this.operater = operater;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  public String getSign() {
    return sign;
  }
  public void setSign(String sign) {
    this.sign = sign;
  }
  public String getSucc_fail() {
    return succ_fail;
  }
  public void setSucc_fail(String succ_fail) {
    this.succ_fail = succ_fail;
  }
  
  
  
}
