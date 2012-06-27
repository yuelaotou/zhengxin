package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.xpup.common.enums.SexEnum;
import org.xpup.hafmis.orgstrct.domain.enums.DutyEnum;
import org.xpup.security.common.domain.User;

public class HafEmployee extends User {

  private static final long serialVersionUID = 5050880502884567998L;

  /**
   * 真实姓名
   */
  private String realName = null;

  /**
   * 性别
   */
  private Integer sex = null;

  /**
   * 职务
   */
  private Integer duty = null;

  /**
   * 电子邮箱
   */
  private String email = null;

  /**
   * 描述
   */
  private String description = null;

  /**
   * 办事处ID
   */
  private Serializable officeId = null;

  /**
   * 部门ID
   */
  private Serializable departmentId = null;

  /**
   * 操作员ID
   */
  private Serializable operatorId = null;

  
  /**
   * 所属的组织单元
   */
  private OrganizationUnit organizationUnit = new OrganizationUnit();

  
  /**
   * 会计日期
   */
  private String bizDate = "";
  
  //贷款业务日期
  private String plbizDate="";
  
  //财务业务日期
  private String fbizDate="";
  
  /**
   * 预审金额
   */
  private BigDecimal checkMoney = new BigDecimal(0.00);
  
  private String userIp="";
  //吴洪涛  2008－3－14  角色
  private String rolesName;
  //吴洪涛  2008－3－14
  public String getUserIp() {
    return userIp;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DutyEnum getDuty() {
    if (duty == null)
      return null;
    return DutyEnum.getEnum(duty.intValue());
  }

  public void setDuty(DutyEnum duty) {
    if (duty == null) {
      this.duty = null;
    } else {
      this.duty = new Integer(duty.getValue());
    }
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public SexEnum getSex() {
    if (sex == null)
      return null;
    return SexEnum.getEnum(sex.intValue());
  }

  public void setSex(SexEnum sex) {
    if (sex == null) {
      this.sex = null;
    } else {
      this.sex = new Integer(sex.getValue());
    }
  }

  public OrganizationUnit getOrganizationUnit() {
    return organizationUnit;
  }

  public void setOrganizationUnit(OrganizationUnit organizationUnit) {
    this.organizationUnit = organizationUnit;
  }

  public Serializable getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Serializable departmentId) {
    this.departmentId = departmentId;
  }

  public Serializable getOfficeId() {
    return officeId;
  }  

  public void setOfficeId(Serializable officeId) {
    this.officeId = officeId;
  }

  public Serializable getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Serializable operatorId) {
    this.operatorId = operatorId;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getFbizDate() {
    return fbizDate;
  }

  public void setFbizDate(String fbizDate) {
    this.fbizDate = fbizDate;
  }

  public String getPlbizDate() {
    return plbizDate;
  }

  public void setPlbizDate(String plbizDate) {
    this.plbizDate = plbizDate;
  }

  public String getRolesName() {
    return rolesName;
  }

  public void setRolesName(String rolesName) {
    this.rolesName = rolesName;
  }

  public BigDecimal getCheckMoney() {
    return checkMoney;
  }

  public void setCheckMoney(BigDecimal checkMoney) {
    this.checkMoney = checkMoney;
  }

 

 

}
