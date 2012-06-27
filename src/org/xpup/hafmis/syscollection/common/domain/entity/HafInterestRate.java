package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class HafInterestRate extends DomainObject {

    /** persistent field */
    private BigDecimal curRate=new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal preRate=new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal isStart=new BigDecimal(0.00);
    
    /** persistent field */
    private Integer isSubDemand=new Integer(0);
    
    /** persistent field */
    private Integer isSubtRegular=new Integer(0);
    
    /** persistent field */
    private String officecode;

    private String showIsStart;
    
    private String officecodeName;
    /** persistent field */
    private String bizDate;
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;

    /** full constructor */
    public HafInterestRate(Integer isSubDemand,Integer isSubtRegular,String officecode,BigDecimal curRate, BigDecimal preRate, BigDecimal isSub, BigDecimal isStart, String bizDate,String reserveaA, String reserveaB, String reserveaC) {
        this.isSubDemand = isSubDemand;
        this.isSubtRegular = isSubtRegular;
        this.officecode = officecode;
        this.curRate = curRate;
        this.preRate = preRate;
        this.isStart = isStart;
        this.bizDate = bizDate;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public HafInterestRate() {
    }

    public BigDecimal getCurRate() {
        return this.curRate;
    }

    public void setCurRate(BigDecimal curRate) {
        this.curRate = curRate;
    }

    public BigDecimal getPreRate() {
        return this.preRate;
    }

    public void setPreRate(BigDecimal preRate) {
        this.preRate = preRate;
    }

    public BigDecimal getIsStart() {
        return this.isStart;
    }

    public void setIsStart(BigDecimal isStart) {
        this.isStart = isStart;
    }

    public String getBizDate() {
        return this.bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof HafInterestRate) ) return false;
        HafInterestRate castOther = (HafInterestRate) other;
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

    public String getShowIsStart() {
      return showIsStart;
    }

    public void setShowIsStart(String showIsStart) {
      this.showIsStart = showIsStart;
    }

    public Integer getIsSubDemand() {
      return isSubDemand;
    }

    public void setIsSubDemand(Integer isSubDemand) {
      this.isSubDemand = isSubDemand;
    }

    public Integer getIsSubtRegular() {
      return isSubtRegular;
    }

    public void setIsSubtRegular(Integer isSubtRegular) {
      this.isSubtRegular = isSubtRegular;
    }
    public String getOfficecodeName() {
      return officecodeName;
    }

    public void setOfficecodeName(String officecodeName) {
      this.officecodeName = officecodeName;
    }

    public String getOfficecode() {
      return officecode;
    }

    public void setOfficecode(String officecode) {
      this.officecode = officecode;
    }

}
