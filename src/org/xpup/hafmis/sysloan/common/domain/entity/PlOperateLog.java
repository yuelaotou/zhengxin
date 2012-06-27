package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PlOperateLog implements Serializable {

    /** identifier field */
    private Integer ploperateLogId;

    /** persistent field */
    private BigDecimal opSys;

    /** persistent field */
    private String opModel;
    private String opModelshow;

    /** persistent field */
    private String opButton;
    private String opButtonshow;

    /** nullable persistent field */
    private BigDecimal opBizId;

    /** persistent field */
    private String opIp;

    /** nullable persistent field */
    private String contractId;

    /** persistent field */
    private Date opTime;

    /** persistent field */
    private String operator;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public PlOperateLog(Integer ploperateLogId, BigDecimal opSys, String opModel, String opButton, BigDecimal opBizId, String opIp, String contractId, Date opTime, String operator, String reserveaA, String reserveaB, String reserveaC) {
        this.ploperateLogId = ploperateLogId;
        this.opSys = opSys;
        this.opModel = opModel;
        this.opButton = opButton;
        this.opBizId = opBizId;
        this.opIp = opIp;
        this.contractId = contractId;
        this.opTime = opTime;
        this.operator = operator;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public PlOperateLog() {
    }

    /** minimal constructor */
    public PlOperateLog(Integer ploperateLogId, BigDecimal opSys, String opModel, String opButton, String opIp, Date opTime, String operator) {
        this.ploperateLogId = ploperateLogId;
        this.opSys = opSys;
        this.opModel = opModel;
        this.opButton = opButton;
        this.opIp = opIp;
        this.opTime = opTime;
        this.operator = operator;
    }

    public Integer getPloperateLogId() {
        return this.ploperateLogId;
    }

    public void setPloperateLogId(Integer ploperateLogId) {
        this.ploperateLogId = ploperateLogId;
    }

    public BigDecimal getOpSys() {
        return this.opSys;
    }

    public void setOpSys(BigDecimal opSys) {
        this.opSys = opSys;
    }

    public String getOpModel() {
        return this.opModel;
    }

    public void setOpModel(String opModel) {
        this.opModel = opModel;
    }

    public String getOpButton() {
        return this.opButton;
    }

    public void setOpButton(String opButton) {
        this.opButton = opButton;
    }

    public BigDecimal getOpBizId() {
        return this.opBizId;
    }

    public void setOpBizId(BigDecimal opBizId) {
        this.opBizId = opBizId;
    }

    public String getOpIp() {
        return this.opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
            .append("ploperateLogId", getPloperateLogId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PlOperateLog) ) return false;
        PlOperateLog castOther = (PlOperateLog) other;
        return new EqualsBuilder()
            .append(this.getPloperateLogId(), castOther.getPloperateLogId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPloperateLogId())
            .toHashCode();
    }

    public String getOpButtonshow() {
      return opButtonshow;
    }

    public void setOpButtonshow(String opButtonshow) {
      this.opButtonshow = opButtonshow;
    }

    public String getOpModelshow() {
      return opModelshow;
    }

    public void setOpModelshow(String opModelshow) {
      this.opModelshow = opModelshow;
    }

}
