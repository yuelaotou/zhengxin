package org.xpup.hafmis.syscollection.common.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;
/**
 * 
 */

/** @author Hibernate CodeGenerator */
public class SpecialPick extends DomainObject {

    /** persistent field */
    private Emp emp = new Emp();
    private Integer empId;
    private Org org = new Org();
    /** persistent field */
    private BigDecimal pickCorpus=new BigDecimal(0.00);
    private Date operateTime=new Date();
    private String temp_operateTime;
    /** persistent field */
    private String operator;
    /** persistent field */
    private BigDecimal isPick=new BigDecimal(0.00);/**±¸Ñ¡a*/
    private String temp_isPick;
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    private Integer pickPeopleNum=new Integer(0);;
    private BigDecimal pickCorpusSum=new BigDecimal(0.00);
    private String empName;
    
    

    public String getEmpName() {
      return empName;
    }

    public void setEmpName(String empName) {
      this.empName = empName;
    }

    public Integer getPickPeopleNum() {
      return pickPeopleNum;
    }

    public void setPickPeopleNum(Integer pickPeopleNum) {
      this.pickPeopleNum = pickPeopleNum;
    }

    /** full constructor */
    public SpecialPick(Integer empId,BigDecimal pickCorpus,Org org,String reserveaA, String reserveaB, String reserveaC,Date operateTime,String operator,Integer pickPeopleNum,BigDecimal pickCorpusSum,String empName) {
        this.empId = empId;
        this.pickCorpus = pickCorpus;
        this.org = org;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.operateTime = operateTime;
        this.operator = operator;
        this.pickPeopleNum=pickPeopleNum;
        this.pickCorpusSum=pickCorpusSum;
        this.empName=empName;
    }

    /** default constructor */
    public SpecialPick() {
    }

    public BigDecimal getPickCorpus() {
        return this.pickCorpus;
    }

    public void setPickCorpus(BigDecimal pickCorpus) {
        this.pickCorpus = pickCorpus;
    }

    public BigDecimal getIsPick() {
        return this.isPick;
    }

    public void setIsPick(BigDecimal isPick) {
        this.isPick = isPick;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SpecialPick) ) return false;
        SpecialPick castOther = (SpecialPick) other;
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

    public Org getOrg() {
      return org;
    }

    public void setOrg(Org org) {
      this.org = org;
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

    public String getOperator() {
      return operator;
    }

    public void setOperator(String operator) {
      this.operator = operator;
    }

    public Date getOperateTime() {
      return operateTime;
    }

    public void setOperateTime(Date operateTime) {
      this.operateTime = operateTime;
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

    public BigDecimal getPickCorpusSum() {
      return pickCorpusSum;
    }

    public void setPickCorpusSum(BigDecimal pickCorpusSum) {
      this.pickCorpusSum = pickCorpusSum;
    }

    public String getTemp_operateTime() {
      return temp_operateTime;
    }

    public void setTemp_operateTime(String temp_operateTime) {
      this.temp_operateTime = temp_operateTime;
    }

    public String getTemp_isPick() {
      return temp_isPick;
    }

    public void setTemp_isPick(String temp_isPick) {
      this.temp_isPick = temp_isPick;
    }

}
