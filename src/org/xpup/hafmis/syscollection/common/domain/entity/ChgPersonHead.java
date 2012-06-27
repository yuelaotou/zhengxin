package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class ChgPersonHead   extends DomainObject implements Serializable {

 public ChgPersonHead(Org org,String chngMonth,Integer chgStatus){
   this.org=org;
   this.chngMonth = chngMonth;
   this.chgStatus = chgStatus;
 }
  private static final long serialVersionUID = 5342726970913964717L;

    /** persistent field */
    private Org org=new Org();

    /** persistent field */
    private String chngMonth="";

    /** persistent field */
    private String bizDate="";

    /** persistent field */
    private Integer chgStatus=new Integer(0);
    /** persistent field */
    private String temp_chgStatus="";

    /** persistent field */
    private PaymentHead paymentHead;
    
    /** persistent field */
    private String reserveaA="";
    
    /** persistent field */
    private String reserveaB="";
    
    /** persistent field */
    private String reserveaC="";
    private BigDecimal oldPayment = new BigDecimal(0.00);
    private String temp_rate;


    private Integer sumChgPerson = new Integer(0);//变更人数
    private Integer insChgperson = new Integer(0);//增加人数
    private Integer mulChgperson = new Integer(0);//减少人数
    private BigDecimal insPayment = new BigDecimal(0.00);//增加缴额
    private BigDecimal mulPayment = new BigDecimal(0.00);//减少缴额
    private BigDecimal sumOrgPay = new BigDecimal(0.00);//单位缴额
    private BigDecimal sumEmpPay = new BigDecimal(0.00);//职工缴额
    private BigDecimal sumSumPay = new BigDecimal(0.00);//缴额合计
    private BigDecimal oldOldPayment = new BigDecimal(0.00);//变更前单位应缴额
    private BigDecimal newOldPayment = new BigDecimal(0.00);//变更后单位应缴额
    
    private BigDecimal rate = new BigDecimal(0.00);//比率
    private String temp_pick="";//提交状态
    
    
    
    
    public BigDecimal getRate() {
      return rate;
    }

    public void setRate(BigDecimal rate) {
      this.rate = rate;
    }

    /** full constructor */
    public ChgPersonHead( Org org, String chngMonth, String bizDate, Integer chgStatus, PaymentHead paymentHead,BigDecimal oldPayment) {
      
        this.org = org;
        this.chngMonth = chngMonth;
        this.bizDate = bizDate;
        this.chgStatus = chgStatus;
        this.paymentHead = paymentHead;
        this.oldPayment = oldPayment;
    }
    
    /** full constructor */
    public ChgPersonHead( Org org, String chngMonth, String bizDate, Integer chgStatus, 
        PaymentHead paymentHead,String reserveaA,String reserveaB,String reserveaC,BigDecimal oldPayment) {
      
        this.org = org;
        this.chngMonth = chngMonth;
        this.bizDate = bizDate;
        this.chgStatus = chgStatus;
        this.paymentHead = paymentHead;
        this.reserveaA=reserveaA;
        this.reserveaB=reserveaB;
        this.reserveaC=reserveaC;
        this.oldPayment = oldPayment;
    }

    /** default constructor */      
    public ChgPersonHead() {
    }

    public String getChngMonth() {
        return this.chngMonth;
    }

    public void setChngMonth(String chngMonth) {
        this.chngMonth = chngMonth;
    }

    public String getBizDate() {
        return this.bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public Integer getChgStatus() {
        return this.chgStatus;
    }

    public void setChgStatus(Integer chgStatus) {
        this.chgStatus = chgStatus;
    }

    public Org getOrg() {
      return org;
    }

    public void setOrg(Org org) {
      this.org = org;
    }

    public PaymentHead getPaymentHead() {
      return paymentHead;
    }

    public void setPaymentHead(PaymentHead paymentHead) {
      this.paymentHead = paymentHead;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ChgPersonHead) ) return false;
        ChgPersonHead castOther = (ChgPersonHead) other;
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

    public BigDecimal getOldPayment() {
      return oldPayment;
    }

    public void setOldPayment(BigDecimal oldPayment) {
      this.oldPayment = oldPayment;
    }

    public Integer getInsChgperson() {
      return insChgperson;
    }

    public void setInsChgperson(Integer insChgperson) {
      this.insChgperson = insChgperson;
    }

    public BigDecimal getInsPayment() {
      return insPayment;
    }

    public void setInsPayment(BigDecimal insPayment) {
      this.insPayment = insPayment;
    }

    public Integer getMulChgperson() {
      return mulChgperson;
    }

    public void setMulChgperson(Integer mulChgperson) {
      this.mulChgperson = mulChgperson;
    }

    public BigDecimal getMulPayment() {
      return mulPayment;
    }

    public void setMulPayment(BigDecimal mulPayment) {
      this.mulPayment = mulPayment;
    }

    public BigDecimal getOldOldPayment() {
      return oldOldPayment;
    }

    public void setOldOldPayment(BigDecimal oldOldPayment) {
      this.oldOldPayment = oldOldPayment;
    }


    public Integer getSumChgPerson() {
      return sumChgPerson;
    }

    public void setSumChgPerson(Integer sumChgPerson) {
      this.sumChgPerson = sumChgPerson;
    }

    public BigDecimal getSumEmpPay() {
      return sumEmpPay;
    }

    public void setSumEmpPay(BigDecimal sumEmpPay) {
      this.sumEmpPay = sumEmpPay;
    }

    public BigDecimal getSumOrgPay() {
      return sumOrgPay;
    }

    public void setSumOrgPay(BigDecimal sumOrgPay) {
      this.sumOrgPay = sumOrgPay;
    }

    public BigDecimal getSumSumPay() {
      return sumSumPay;
    }

    public void setSumSumPay(BigDecimal sumSumPay) {
      this.sumSumPay = sumSumPay;
    }

    public String getTemp_chgStatus() {
      return temp_chgStatus;
    }

    public void setTemp_chgStatus(String temp_chgStatus) {
      this.temp_chgStatus = temp_chgStatus;
    }

    public BigDecimal getNewOldPayment() {
      return newOldPayment;
    }

    public void setNewOldPayment(BigDecimal newOldPayment) {
      this.newOldPayment = newOldPayment;
    }

    public String getTemp_rate() {
      return temp_rate;
    }

    public void setTemp_rate(String temp_rate) {
      this.temp_rate = temp_rate;
    }

    public String getTemp_pick() {
      return temp_pick;
    }

    public void setTemp_pick(String temp_pick) {
      this.temp_pick = temp_pick;
    }

}
