package org.xpup.hafmis.syscollection.common.domain.entity;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class EmpHAFAccountFlow extends DomainObject {

    /**
   * 
   */
//  public EmpHAFAccountFlow(Integer id,EmpHAFAccountFlow empHAFAccountFlow) throws IllegalAccessException, InvocationTargetException {
//    BeanUtils.copyProperties(this, empHAFAccountFlow);
//    this.empId = id;
//  }
  private static final long serialVersionUID = -8820190074760588474L;
    /** persistent field */
    private Emp emp=new Emp();
    private Integer empId;
    private String empName ="";
    /** persistent field */
    private OrgHAFAccountFlow orgHAFAccountFlow ;
    private Org org=new Org();
    /** persistent field */

    private BigDecimal debit = new BigDecimal(0.00);


    /** persistent field */

    private BigDecimal credit = new BigDecimal(0.00);


    /**
     * 发生金额
     */
    private BigDecimal money= new BigDecimal(0.00);
    
    /** nullable persistent field */

    /** 期初余额 */
    private BigDecimal prebalance=new BigDecimal(0.00); 
    /** 本期贷方发生额 */
    private BigDecimal temp_credit=new BigDecimal(0.00);
    /** 本期借方发生额 */
    private BigDecimal temp_debit=new BigDecimal(0.00);
    /** 本期借方发生额 */
    private BigDecimal temp_interest=new BigDecimal(0.00);
    /** 期末余额 */
    private BigDecimal curbalance=new BigDecimal(0.00);
    /** 本期借方笔数 */
    private String countCredit="";
    /** 本期贷方笔数 */
    private String countDebit="";
    /**显示时间  */
    private String displayTme="";

    private BigDecimal interest = new BigDecimal(0.00);


    /** nullable persistent field */
    private String summary;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private BigDecimal empMoney = new BigDecimal(0.00);
    /** full constructor */
    
    public EmpHAFAccountFlow(Integer empId, OrgHAFAccountFlow orgHAFAccountFlow, BigDecimal debit, BigDecimal credit, BigDecimal interest, String summary, String reserveaA, String reserveaB, String reserveaC) {
        this.empId = empId;
        this.orgHAFAccountFlow = orgHAFAccountFlow;
        this.debit = debit;
        this.credit = credit;
        this.interest = interest;
        this.summary = summary;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public EmpHAFAccountFlow() {
    }

    /** minimal constructor */
    public EmpHAFAccountFlow(Emp emp,OrgHAFAccountFlow orgHAFAccountFlow, BigDecimal debit, BigDecimal credit) {
        this.emp = emp;
        this.orgHAFAccountFlow = orgHAFAccountFlow;
        this.debit = debit;
        this.credit = credit;
    }
    
    public Emp getEmp() {
        return this.emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public OrgHAFAccountFlow getOrgHAFAccountFlow() {
        return this.orgHAFAccountFlow;
    }

    public void setOrgHAFAccountFlow(OrgHAFAccountFlow orgHAFAccountFlow) {
        this.orgHAFAccountFlow = orgHAFAccountFlow;
    }

    public BigDecimal getDebit() {
        return this.debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return this.credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }


    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReserveaA() {
        return this.reserveaA;
    }

    public void setReserveaA(String reserveaA) {
        this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
        return this.reserveaB;
    }

    public void setReserveaB(String reserveaB) {
        this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
        return this.reserveaC;
    }

    public void setReserveaC(String reserveaC) {
        this.reserveaC = reserveaC;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof EmpHAFAccountFlow) ) return false;
        EmpHAFAccountFlow castOther = (EmpHAFAccountFlow) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

    public String getEmpName() {
      return empName;
    }

    public void setEmpName(String empName) {
      this.empName = empName;
    }

    public BigDecimal getPrebalance() {
      return prebalance;
    }

    public void setPrebalance(BigDecimal prebalance) {
      this.prebalance = prebalance;
    }

    public String getCountCredit() {
      return countCredit;
    }

    public void setCountCredit(String countCredit) {
      this.countCredit = countCredit;
    }

    public String getCountDebit() {
      return countDebit;
    }

    public void setCountDebit(String countDebit) {
      this.countDebit = countDebit;
    }

    public BigDecimal getCurbalance() {
      return curbalance;
    }

    public void setCurbalance(BigDecimal curbalance) {
      this.curbalance = curbalance;
    }

    public BigDecimal getTemp_credit() {
      return temp_credit;
    }

    public void setTemp_credit(BigDecimal temp_credit) {
      this.temp_credit = temp_credit;
    }

    public BigDecimal getTemp_debit() {
      return temp_debit;
    }

    public void setTemp_debit(BigDecimal temp_debit) {
      this.temp_debit = temp_debit;
    }

    public String getDisplayTme() {
      return displayTme;
    }

    public void setDisplayTme(String displayTme) {
      this.displayTme = displayTme;
    }


    public BigDecimal getEmpMoney() {
      return empMoney;
    }

    public void setEmpMoney(BigDecimal empMoney) {
      this.empMoney = empMoney;
    }

    public BigDecimal getMoney() {
      return money;
    }

    public void setMoney(BigDecimal money) {
      this.money = money;
    }

    public Org getOrg() {
      return org;
    }

    public void setOrg(Org org) {
      this.org = org;
    }

    public BigDecimal getTemp_interest() {
      return temp_interest;
    }

    public void setTemp_interest(BigDecimal temp_interest) {
      this.temp_interest = temp_interest;
    }


}
