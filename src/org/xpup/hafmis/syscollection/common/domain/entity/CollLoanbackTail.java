package org.xpup.hafmis.syscollection.common.domain.entity;

  import java.io.Serializable;
  import java.math.BigDecimal;
  import org.apache.commons.lang.builder.EqualsBuilder;
  import org.apache.commons.lang.builder.HashCodeBuilder;
  import org.apache.commons.lang.builder.ToStringBuilder;

  /** @author Hibernate CodeGenerator */
  public class CollLoanbackTail implements Serializable {

      /** identifier field */
      private String headId;

      /** persistent field */
      private Integer id;

      /** nullable persistent field */
      private String contractId;

      /** nullable persistent field */
      private String loanKouAcc;

      /** nullable persistent field */
      private String empId;

      /** nullable persistent field */
      private String orgId;

      /** nullable persistent field */
      private String yearMonth;

      /** nullable persistent field */
      private BigDecimal shouldCorpus;

      /** nullable persistent field */
      private BigDecimal preCorpus;

      /** nullable persistent field */
      private BigDecimal currentCorpus;

      /** nullable persistent field */
      private String flag;

      /** nullable persistent field */
      private String collflag;

      /** nullable persistent field */
      private String reserveaA;

      /** nullable persistent field */
      private String reserveaB;

      /** nullable persistent field */
      private String reserveaC;

      /** full constructor */
      public CollLoanbackTail(String headId, Integer id, String contractId, String loanKouAcc, String empId, String orgId, String yearMonth, BigDecimal shouldCorpus, BigDecimal preCorpus, BigDecimal currentCorpus, String flag, String reserveaA, String reserveaB, String reserveaC) {
          this.headId = headId;
          this.id = id;
          this.contractId = contractId;
          this.loanKouAcc = loanKouAcc;
          this.empId = empId;
          this.orgId = orgId;
          this.yearMonth = yearMonth;
          this.shouldCorpus = shouldCorpus;
          this.preCorpus = preCorpus;
          this.currentCorpus = currentCorpus;
          this.flag = flag;
          this.reserveaA = reserveaA;
          this.reserveaB = reserveaB;
          this.reserveaC = reserveaC;
      }

      /** default constructor */
      public CollLoanbackTail() {
      }

      /** minimal constructor */
      public CollLoanbackTail(String headId, Integer id) {
          this.headId = headId;
          this.id = id;
      }

      public String getHeadId() {
          return this.headId;
      }

      public void setHeadId(String headId) {
          this.headId = headId;
      }

      public Integer getId() {
          return this.id;
      }

      public void setId(Integer id) {
          this.id = id;
      }

      public String getContractId() {
          return this.contractId;
      }

      public void setContractId(String contractId) {
          this.contractId = contractId;
      }

      public String getLoanKouAcc() {
          return this.loanKouAcc;
      }

      public void setLoanKouAcc(String loanKouAcc) {
          this.loanKouAcc = loanKouAcc;
      }

      public String getEmpId() {
          return this.empId;
      }

      public void setEmpId(String empId) {
          this.empId = empId;
      }

      public String getOrgId() {
          return this.orgId;
      }

      public void setOrgId(String orgId) {
          this.orgId = orgId;
      }

      public String getYearMonth() {
          return this.yearMonth;
      }

      public void setYearMonth(String yearMonth) {
          this.yearMonth = yearMonth;
      }

      public BigDecimal getShouldCorpus() {
          return this.shouldCorpus;
      }

      public void setShouldCorpus(BigDecimal shouldCorpus) {
          this.shouldCorpus = shouldCorpus;
      }

      public BigDecimal getPreCorpus() {
          return this.preCorpus;
      }

      public void setPreCorpus(BigDecimal preCorpus) {
          this.preCorpus = preCorpus;
      }

      public BigDecimal getCurrentCorpus() {
          return this.currentCorpus;
      }

      public void setCurrentCorpus(BigDecimal currentCorpus) {
          this.currentCorpus = currentCorpus;
      }

      public String getFlag() {
          return this.flag;
      }

      public void setFlag(String flag) {
          this.flag = flag;
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
              .append("headId", getHeadId())
              .toString();
      }

      public boolean equals(Object other) {
          if ( !(other instanceof CollLoanbackTail) ) return false;
          CollLoanbackTail castOther = (CollLoanbackTail) other;
          return new EqualsBuilder()
              .append(this.getHeadId(), castOther.getHeadId())
              .isEquals();
      }

      public int hashCode() {
          return new HashCodeBuilder()
              .append(getHeadId())
              .toHashCode();
      }

      public String getCollflag() {
        return collflag;
      }

      public void setCollflag(String collflag) {
        this.collflag = collflag;
      }

  }

