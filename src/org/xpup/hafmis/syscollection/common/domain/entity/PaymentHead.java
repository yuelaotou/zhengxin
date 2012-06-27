package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public abstract class PaymentHead extends DomainObject {

  private static final long serialVersionUID = -2903565631925836673L;

  /** identifier field */
  private String payType_ = "";

  /** identifier field */
  private String minMaxMonth = "";

  /** persistent field */
  private Org org = new Org();

  /** nullable persistent field */
  private BigDecimal payMoney = new BigDecimal(0.00);

  /** nullable persistent field */
  private String noteNum = "";

  /** nullable persistent field */
  private String docNum = "";

  /** nullable persistent field */
  private String settDate = "";

  /** persistent field */
  private Integer payStatus = new Integer(0);

  /** persistent field */
  private String payStatus_ = "";

  private BigDecimal sumPayMoney = new BigDecimal(0.00);

  private String reserveaA = "";

  /** persistent field */
  private String reserveaB = "";

  /** persistent field */
  private String reserveaC = "";

  private Integer empCount = new Integer(0);

  private BigDecimal addpaymoney = new BigDecimal(0.00);

  private String excessReason = "";

  /** 代扣批号 */
  private Integer financialPayId = new Integer(0);

  private String receivables_bank_acc = ""; // 收款单位归集银行账号

  private String receivables_bank_name = ""; // 收款单位归集银行名称

  private String payment_bank_acc = ""; // 付款单位开户银行账号

  private String payment_bank_name = ""; // 付款单位开户银行名称

  private String pay_way = ""; // 缴存方式：1划款、2支票、3现金

  private String pay_kind = ""; // 缴存种类：1汇缴，2补缴，3预缴

  private String tmpplaceKind = ""; // 缴存种类：1汇缴，2补缴，3预缴

  /** full constructor */
  public PaymentHead(Org org, BigDecimal payMoney, String noteNum,
      String docNum, String settDate, Integer payStatus, String reserveaA,
      String reserveaB, String reserveaC, String excessReason) {

    this.org = org;
    this.payMoney = payMoney;
    this.noteNum = noteNum;
    this.docNum = docNum;
    this.settDate = settDate;
    this.payStatus = payStatus;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
    this.excessReason = excessReason;
  }

  /** default constructor */
  public PaymentHead() {
  }

  /** minimal constructor */
  public PaymentHead(Org org, Integer payStatus) {

    this.org = org;
    this.payStatus = payStatus;
  }

  public Org getOrg() {
    return this.org;
  }

  public BigDecimal getAddpaymoney() {
    return addpaymoney;
  }

  public void setAddpaymoney(BigDecimal addpaymoney) {
    this.addpaymoney = addpaymoney;
  }

  public Integer getEmpCount() {
    return empCount;
  }

  public void setEmpCount(Integer empCount) {
    this.empCount = empCount;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public BigDecimal getPayMoney() {
    return this.payMoney;
  }

  public void setPayMoney(BigDecimal payMoney) {
    if (payMoney != null && !payMoney.equals("")) {
      payMoney = payMoney.divide(new BigDecimal(1), 2,
          BigDecimal.ROUND_HALF_DOWN);
    }
    this.payMoney = payMoney;
  }

  public String getNoteNum() {
    return this.noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getDocNum() {
    return this.docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getSettDate() {
    return this.settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public Integer getPayStatus() {
    return this.payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  public String toString() {
    return new ToStringBuilder(this).append("id", getId()).toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof PaymentHead))
      return false;
    PaymentHead castOther = (PaymentHead) other;
    return new EqualsBuilder().append(this.getId(), castOther.getId())
        .isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getId()).toHashCode();
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

  public abstract String getPayType();

  public String getPayStatus_() {
    return payStatus_;
  }

  public void setPayStatus_(String payStatus_) {
    this.payStatus_ = payStatus_;
  }

  public String getPayType_() {
    return payType_;
  }

  public void setPayType_(String payType_) {
    this.payType_ = payType_;
  }

  public BigDecimal getSumPayMoney() {
    return sumPayMoney;
  }

  public void setSumPayMoney(BigDecimal sumPayMoney) {
    this.sumPayMoney = sumPayMoney;
  }

  public String getExcessReason() {
    return excessReason;
  }

  public void setExcessReason(String excessReason) {
    this.excessReason = excessReason;
  }

  public Integer getFinancialPayId() {
    return financialPayId;
  }

  public void setFinancialPayId(Integer financialPayId) {
    this.financialPayId = financialPayId;
  }

  public String getPay_kind() {
    return pay_kind;
  }

  public void setPay_kind(String pay_kind) {
    this.pay_kind = pay_kind;
  }

  public String getPay_way() {
    return pay_way;
  }

  public void setPay_way(String pay_way) {
    this.pay_way = pay_way;
  }

  public String getPayment_bank_acc() {
    return payment_bank_acc;
  }

  public void setPayment_bank_acc(String payment_bank_acc) {
    this.payment_bank_acc = payment_bank_acc;
  }

  public String getPayment_bank_name() {
    return payment_bank_name;
  }

  public void setPayment_bank_name(String payment_bank_name) {
    this.payment_bank_name = payment_bank_name;
  }

  public String getReceivables_bank_acc() {
    return receivables_bank_acc;
  }

  public void setReceivables_bank_acc(String receivables_bank_acc) {
    this.receivables_bank_acc = receivables_bank_acc;
  }

  public String getReceivables_bank_name() {
    return receivables_bank_name;
  }

  public void setReceivables_bank_name(String receivables_bank_name) {
    this.receivables_bank_name = receivables_bank_name;
  }

  public String getTmpplaceKind() {
    return tmpplaceKind;
  }

  public void setTmpplaceKind(String tmpplaceKind) {
    this.tmpplaceKind = tmpplaceKind;
  }

  public String getMinMaxMonth() {
    return minMaxMonth;
  }

  public void setMinMaxMonth(String minMaxMonth) {
    this.minMaxMonth = minMaxMonth;
  }

}
