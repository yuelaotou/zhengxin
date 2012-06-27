package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class AddPayTail extends DomainObject {
    
    private PaymentHead paymentHead ;
    
    private Emp emp = new Emp();
    private Integer empId;
    private String  personId="";
    /** persistent field */
    private String addMonht;
    
    private String beginMonth="";
    
    private String endMonth="";
    
    private String sex="";

    /** persistent field */
    private BigDecimal orgAddMoney=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal empAddMoney=new BigDecimal(0.00);
    
    private Integer personSum=new Integer(0);
    
    private BigDecimal orgAddPaySum=new BigDecimal(0.00);
    
    private BigDecimal empAddPaySum=new BigDecimal(0.00);
    
    private BigDecimal addPaySum=new BigDecimal(0.00);
    
    private BigDecimal addPayAmount = new BigDecimal(0.00);

    /** nullable persistent field */
    private String addReason;
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    private String empName="";
    
    private BigDecimal salaryBase = new BigDecimal(0.00);
    private BigDecimal orgRate = new BigDecimal(0.00);
    private BigDecimal empRate = new BigDecimal(0.00);
    
    /** full constructor */
    public AddPayTail(PaymentHead paymentHead, Integer empId, String addMonht, BigDecimal orgAddMoney, BigDecimal empAddMoney, String addReason,String reserveaA, String reserveaB, String reserveaC
        ,BigDecimal salaryBase,BigDecimal orgRate,BigDecimal empRate) {
        this.paymentHead = paymentHead;
        this.empId = empId;
        this.addMonht = addMonht;
        this.orgAddMoney = orgAddMoney;
        this.empAddMoney = empAddMoney;
        this.addReason = addReason;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.salaryBase = salaryBase;
        this.orgRate = orgRate;
        this.empRate = empRate;
    }

    /** default constructor */
    public AddPayTail() {
    }

    /** minimal constructor */
    public AddPayTail(PaymentHead paymentHead, Emp emp, String addMonht, BigDecimal orgAddMoney) {
      this.paymentHead = paymentHead;
      this.emp = emp;
        this.addMonht = addMonht;
        this.orgAddMoney = orgAddMoney;
    }

    public String getAddMonht() {
        return this.addMonht;
    }

    public void setAddMonht(String addMonht) {
        this.addMonht = addMonht;
    }

    public BigDecimal getOrgAddMoney() {
        return this.orgAddMoney;
    }

    public void setOrgAddMoney(BigDecimal orgAddMoney) {
      if(orgAddMoney != null && !orgAddMoney.equals("")){
        orgAddMoney = orgAddMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
      }
        this.orgAddMoney = orgAddMoney;
    }

    public BigDecimal getEmpAddMoney() {
        return this.empAddMoney;
    }

    public void setEmpAddMoney(BigDecimal empAddMoney) {
      if(empAddMoney != null && !empAddMoney.equals("")){
        empAddMoney = empAddMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
      }
        this.empAddMoney = empAddMoney;
    }

    public String getAddReason() {
        return this.addReason;
    }

    public void setAddReason(String addReason) {
        this.addReason = addReason;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AddPayTail) ) return false;
        AddPayTail castOther = (AddPayTail) other;
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

    public PaymentHead getPaymentHead() {
      return paymentHead;
    }

    public void setPaymentHead(PaymentHead paymentHead) {
      this.paymentHead = paymentHead;
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

    public BigDecimal getAddPaySum() {
      return addPaySum;
    }

    public void setAddPaySum(BigDecimal addPaySum) {
      if(addPaySum != null && !addPaySum.equals("")){
        addPaySum = addPaySum.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
      }
      this.addPaySum = addPaySum;
    }

    public BigDecimal getEmpAddPaySum() {
      return empAddPaySum;
    }

    public void setEmpAddPaySum(BigDecimal empAddPaySum) {
      if(empAddPaySum != null && !empAddPaySum.equals("")){
        empAddPaySum = empAddPaySum.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
      }
      this.empAddPaySum = empAddPaySum;
    }

    public BigDecimal getOrgAddPaySum() {
      return orgAddPaySum;
    }

    public void setOrgAddPaySum(BigDecimal orgAddPaySum) {
      if(orgAddPaySum != null && !orgAddPaySum.equals("")){
        orgAddPaySum = orgAddPaySum.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
      }
      this.orgAddPaySum = orgAddPaySum;
    }

    public Integer getPersonSum() {
     
      return personSum;
    }

    public void setPersonSum(Integer personSum) {
      this.personSum = personSum;
    }

    public String getBeginMonth() {
      return beginMonth;
    }

    public void setBeginMonth(String beginMonth) {
      this.beginMonth = beginMonth;
    }

    public String getEndMonth() {
      return endMonth;
    }

    public void setEndMonth(String endMonth) {
      this.endMonth = endMonth;
    }


    public String getSex() {
      return sex;
    }

    public void setSex(String sex) {
      this.sex = sex;
    }

    public String getEmpName() {
      return empName;
    }

    public void setEmpName(String empName) {
      this.empName = empName;
    }


    public BigDecimal getAddPayAmount() {
      return addPayAmount;
    }

    public void setAddPayAmount(BigDecimal addPayAmount) {
      this.addPayAmount = addPayAmount;
    }

    public String getPersonId() {
      return personId;
    }

    public void setPersonId(String personId) {
      this.personId = personId;
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
