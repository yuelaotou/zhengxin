package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class AdjustWrongAccountTail extends DomainObject {

    /** persistent field */
    private AdjustWrongAccountHead adjustWrongAccountHead;

    /** persistent field */
    private Emp emp = new Emp();
    private Integer empId;
    /** persistent field */
    private BigDecimal adjustMoney;
    private String empName;
    /** persistent field */
    private String settDate;
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    private String reason = "";
    private String remark = "";
    

    public String getReason() {
      return reason;
    }

    public void setReason(String reason) {
      this.reason = reason;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    /** full constructor */
    public AdjustWrongAccountTail(AdjustWrongAccountHead adjustWrongAccountHead,Integer empId, BigDecimal adjustHeadId, BigDecimal adjustMoney, String settDate,String reserveaA, String reserveaB, String reserveaC) {
        this.adjustWrongAccountHead = adjustWrongAccountHead;
        this.empId = empId;
        this.adjustMoney = adjustMoney;
        this.settDate = settDate;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public AdjustWrongAccountTail() {
    }

    /** minimal constructor */
    public AdjustWrongAccountTail(AdjustWrongAccountHead adjustWrongAccountHead,Integer empId, BigDecimal adjustHeadId,  BigDecimal adjustMoney, String settDate) {
        this.adjustWrongAccountHead = adjustWrongAccountHead;
        this.empId = empId;
        this.adjustMoney = adjustMoney;
        this.settDate = settDate;
    }

    public AdjustWrongAccountHead getAdjustWrongAccountHead() {
      return adjustWrongAccountHead;
    }

    public void setAdjustWrongAccountHead(
        AdjustWrongAccountHead adjustWrongAccountHead) {
      this.adjustWrongAccountHead = adjustWrongAccountHead;
    }

    public Emp getEmp() {
      return emp;
    }

    public void setEmp(Emp emp) {
      this.emp = emp;
    }

    public BigDecimal getAdjustMoney() {
        return this.adjustMoney;
    }

    public void setAdjustMoney(BigDecimal adjustMoney) {
        this.adjustMoney = adjustMoney;
    }

    public String getSettDate() {
        return this.settDate;
    }

    public void setSettDate(String settDate) {
        this.settDate = settDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AdjustWrongAccountTail) ) return false;
        AdjustWrongAccountTail castOther = (AdjustWrongAccountTail) other;
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



}
