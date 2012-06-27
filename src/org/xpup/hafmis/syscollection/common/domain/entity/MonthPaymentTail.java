package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class MonthPaymentTail extends DomainObject{

    private MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
    private MonthPaymentBizActivityLog monthPaymentBizActivityLog = null;
    
    private Emp emp = new Emp();
    private Integer empId;

    /** persistent field */
    private BigDecimal orgShouldPay= new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal empShouldPay = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal orgRealPay = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal empRealPay = new BigDecimal(0.00);
    
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    private String empName;
    private String sex;
    
    
    private BigDecimal salaryBase = new BigDecimal(0.00);
    private BigDecimal orgRate = new BigDecimal(0.00);
    private BigDecimal empRate = new BigDecimal(0.00);
    private BigDecimal sumPay = new BigDecimal(0.00);
    /** full constructor */
    public MonthPaymentTail(MonthPaymentHead monthPaymentHead,Integer empId, BigDecimal orgShouldPay, BigDecimal empShouldPay, BigDecimal orgRealPay, BigDecimal empRealPay,String reserveaA, String reserveaB, String reserveaC
        ,BigDecimal salaryBase,BigDecimal orgRate,BigDecimal empRate) {
       
        this.monthPaymentHead = monthPaymentHead;
        this.empId = empId;
        this.orgShouldPay = orgShouldPay;
        this.empShouldPay = empShouldPay;
        this.orgRealPay = orgRealPay;
        this.empRealPay = empRealPay;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.salaryBase = salaryBase;
        this.orgRate = orgRate;
        this.empRate = empRate;
    }

    /** default constructor */
    public MonthPaymentTail() {
    }

    public BigDecimal getOrgShouldPay() {
        return this.orgShouldPay;
    }

    public void setOrgShouldPay(BigDecimal orgShouldPay) {
        if(orgShouldPay != null && !orgShouldPay.equals("")){
          orgShouldPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
        }
        this.orgShouldPay = orgShouldPay;
    }

    public BigDecimal getEmpShouldPay() {
        return this.empShouldPay;
    }

    public void setEmpShouldPay(BigDecimal empShouldPay) {
        if(empShouldPay != null && !empShouldPay.equals("")){
          empShouldPay = empShouldPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
        }
        this.empShouldPay = empShouldPay;
    }

    public BigDecimal getOrgRealPay() {
        return this.orgRealPay;
    }

    public void setOrgRealPay(BigDecimal orgRealPay) {
        if(orgRealPay != null && !orgRealPay.equals("")){
          orgRealPay=orgRealPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
        }
        this.orgRealPay = orgRealPay;
    }

    public BigDecimal getEmpRealPay() {
        return this.empRealPay;
    }

    public void setEmpRealPay(BigDecimal empRealPay) {
        if(empRealPay != null && !empRealPay.equals("")){
          empRealPay = empRealPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
        }
        this.empRealPay = empRealPay;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof MonthPaymentTail) ) return false;
        MonthPaymentTail castOther = (MonthPaymentTail) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public Emp getEmp() {
      return emp;
    }

    public void setEmp(Emp emp) {
      this.emp = emp;
    }

    public MonthPaymentHead getMonthPaymentHead() {
      return monthPaymentHead;
    }

    public void setMonthPaymentHead(MonthPaymentHead monthPaymentHead) {
      this.monthPaymentHead = monthPaymentHead;
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

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

    public MonthPaymentBizActivityLog getMonthPaymentBizActivityLog() {
      return monthPaymentBizActivityLog;
    }

    public void setMonthPaymentBizActivityLog(
        MonthPaymentBizActivityLog monthPaymentBizActivityLog) {
      this.monthPaymentBizActivityLog = monthPaymentBizActivityLog;
    }
    public MonthPaymentTail(MonthPaymentTail monthPaymentTail,MonthPaymentBizActivityLog monthPaymentBizActivityLog) throws IllegalAccessException, InvocationTargetException {
      BeanUtils.copyProperties(this, monthPaymentTail);
      this.monthPaymentBizActivityLog = monthPaymentBizActivityLog;
    }

    public BigDecimal getSumPay() {
      return sumPay;
    }

    public void setSumPay(BigDecimal sumPay) {
      if(sumPay != null && !sumPay.equals("")){
        sumPay = sumPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
      }
      this.sumPay = sumPay;
    }

    public String getEmpName() {
      return empName;
    }

    public void setEmpName(String empName) {
      this.empName = empName;
    }

    public String getSex() {
      return sex;
    }

    public void setSex(String sex) {
      this.sex = sex;
    }

    public BigDecimal getEmpRate() {
      return empRate;
    }

    public void setEmpRate(BigDecimal empRate) {
      this.empRate = empRate;
    }

    public BigDecimal getOrgRate() {
      return orgRate;
    }

    public void setOrgRate(BigDecimal orgRate) {
      this.orgRate = orgRate;
    }

    public BigDecimal getSalaryBase() {
      return salaryBase;
    }

    public void setSalaryBase(BigDecimal salaryBase) {
      this.salaryBase = salaryBase;
    }

}
