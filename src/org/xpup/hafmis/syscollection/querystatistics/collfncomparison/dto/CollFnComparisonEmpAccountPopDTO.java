package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto;
/**
 * 封装了职工账查询弹出框列表显示内容的DTO
 * Copy Right Information :
 * Goldsoft Project : 
 *
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008-5-10 下午03:29:00
 */
public class CollFnComparisonEmpAccountPopDTO {
  private Integer orgId = new Integer(0);

  private String empId = "";

  private String orgName = "";

  private String empName = "";

  private String cardNum = "";

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
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


  public Integer getOrgId() {
    return orgId;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
