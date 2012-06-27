package org.xpup.hafmis.syscommon.domain.entity;


import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class EmpInfo extends DomainObject {
  private static final long serialVersionUID = 0L;

    /** 职工姓名 */
    private String name;
    
    /** 职工姓名 */
    private String TEMP_name;

    /** 证件类型 */
    private BigDecimal cardKind= new BigDecimal(0.00);
    /** 证件类型 */
    private String TEMP_cardKind;

    /** 证件号码 */
    private String cardNum;

    /** 证件号码 */
    private String TEMP_cardNum;

    /** 出生日期 */
    private String birthday;
    private String conSex;
    /** 性别 */
    private BigDecimal sex= new BigDecimal(0.00);

    /** 所在部门 */
    private String department;
    private String conCardType;
    /** 家庭电话 */
    private String tel;

    /** 移动电话 */
    private String mobileTle;

    /** 职工月收入 */
    private BigDecimal monthIncome= new BigDecimal(0.00);

    /** 开户日期 */
    private String opendate;
    /**备选a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    /**备选证件号码*/
    private String standbyCardNum;

    /** full constructor */
    public EmpInfo(String name, BigDecimal cardKind, String cardNum, String birthday, BigDecimal sex, String department, String tel, String mobileTle, BigDecimal monthIncome, String opendate,String reserveaA, String reserveaB, String reserveaC) {
        this.name = name;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.birthday = birthday;
        this.sex = sex;
        this.department = department;
        this.tel = tel;
        this.mobileTle = mobileTle;
        this.monthIncome = monthIncome;
        this.opendate = opendate;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }
    /** default constructor */
    public EmpInfo() {
    }
    /** minimal constructor */
    public EmpInfo(String name, BigDecimal cardKind, String cardNum, String opendate) {
      
        this.name = name;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.opendate = opendate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCardKind() {
        return this.cardKind;
    }

    public void setCardKind(BigDecimal cardKind) {
        this.cardKind = cardKind;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSex() {
        return this.sex;
    }

    public void setSex(BigDecimal sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobileTle() {
        return this.mobileTle;
    }

    public void setMobileTle(String mobileTle) {
        this.mobileTle = mobileTle;
    }

    public BigDecimal getMonthIncome() {
        return this.monthIncome;
    }

    public void setMonthIncome(BigDecimal monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getOpendate() {
        return this.opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }
  

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof EmpInfo) ) return false;
        EmpInfo castOther = (EmpInfo) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getReserveaA() {
      return reserveaA;
    }

    public void setReserveaA(String reserveaA) {
      this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
      return reserveaB;
    }

    public void setReserveaB(String reserveaB) {
      this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
      return reserveaC;
    }

    public void setReserveaC(String reserveaC) {
      this.reserveaC = reserveaC;
    }

    public String getTEMP_cardNum() {
      return TEMP_cardNum;
    }

    public void setTEMP_cardNum(String num) {
      TEMP_cardNum = num;
    }

    public String getTEMP_name() {
      return TEMP_name;
    }

    public void setTEMP_name(String temp_name) {
      TEMP_name = temp_name;
    }

    public String getTEMP_cardKind() {
      return TEMP_cardKind;
    }

    public void setTEMP_cardKind(String kind) {
      TEMP_cardKind = kind;
    }

    public String getConSex() {
      return conSex;
    }

    public void setConSex(String conSex) {
      this.conSex = conSex;
    }

    public String getConCardType() {
      return conCardType;
    }

    public void setConCardType(String conCardType) {
      this.conCardType = conCardType;
    }
    public String getStandbyCardNum() {
      return standbyCardNum;
    }
    public void setStandbyCardNum(String standbyCardNum) {
      this.standbyCardNum = standbyCardNum;
    }
}
