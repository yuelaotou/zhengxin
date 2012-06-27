package org.xpup.hafmis.syscommon.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HafOperateLog extends DomainObject {

    /** 操作业务系统类型 */
    private Integer opSys;

    /** 操作模块 */
    private String opModel;
    private String opModelshow;

    /** 操作 */
    private String opButton;
    private String opButtonshow;

    /** 操作业务 */
    private Integer opBizId;

    /** 操作IP */
    private String opIp;

    /** 单位编号 */
    private Integer orgId;

    /** 操作时间 */
    private Date opTime;

    /** 操作员 */
    private String operator;
    /**备选a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;

    /** full constructor */
    public HafOperateLog(Integer opSys, String opModel, String opButton, Integer opBizId, String opIp, Integer orgId, Date opTime, String operator,String reserveaA, String reserveaB, String reserveaC) {
      
        this.opSys = opSys;
        this.opModel = opModel;
        this.opButton = opButton;
        this.opBizId = opBizId;
        this.opIp = opIp;
        this.orgId = orgId;
        this.opTime = opTime;
        this.operator = operator;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public HafOperateLog() {
    }

    public Integer getOpSys() {
        return this.opSys;
    }

    public void setOpSys(Integer opSys) {
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

    public Integer getOpBizId() {
        return this.opBizId;
    }

    public void setOpBizId(Integer opBizId) {
        this.opBizId = opBizId;
    }

    public String getOpIp() {
        return this.opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof HafOperateLog) ) return false;
        HafOperateLog castOther = (HafOperateLog) other;
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
