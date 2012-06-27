package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class TranOutHead extends DomainObject {

    /** persistent field */
  private TranInOrg tranInOrg = new TranInOrg();

  /** nullable persistent field */
  private TranOutOrg tranOutOrg = new TranOutOrg();

    /** nullable persistent field */
    private String noteNum;
    private String temp_pickState;
    /** nullable persistent field */
    private String docNum;

    /** nullable persistent field */
    private String settDate;

    /** persistent field */
    private BigDecimal tranStatus = new BigDecimal(0.00);
    private String tranStatus2 = "";
    /**备选a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    /**count people*/
    private String countTranOutPeople;
    
    private BigDecimal persons = new BigDecimal(0.00);//人数
    private BigDecimal salary = new BigDecimal(0.00);//转出金额
    private BigDecimal interest = new BigDecimal(0.00);//转出利息
    private BigDecimal sumSalary = new BigDecimal(0.00);//转出总金额
    
    /*
     * 转出维护--中间金额
     */
    private BigDecimal  sum_salary = new BigDecimal(0.00);//总金额
    private BigDecimal sum_Interst = new BigDecimal(0.00);//总利息
    private BigDecimal sum_sum = new BigDecimal(0.00);//所有单位总金额
    
    
    
    public BigDecimal getInterest() {
      return interest;
    }

    public void setInterest(BigDecimal interest) {
      this.interest = interest;
    }

    public BigDecimal getSumSalary() {
      return sumSalary;
    }

    public void setSumSalary(BigDecimal sumSalary) {
      this.sumSalary = sumSalary;
    }

    public BigDecimal getSalary() {
      return salary;
    }

    public void setSalary(BigDecimal salary) {
      this.salary = salary;
    }

   

    public BigDecimal getPersons() {
      return persons;
    }

    public void setPersons(BigDecimal persons) {
      this.persons = persons;
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

    public TranInOrg getTranInOrg() {
      return tranInOrg;
    }

    public void setTranInOrg(TranInOrg tranInOrg) {
      this.tranInOrg = tranInOrg;
    }

    public TranOutOrg getTranOutOrg() {
      return tranOutOrg;
    }

    public void setTranOutOrg(TranOutOrg tranOutOrg) {
      this.tranOutOrg = tranOutOrg;
    }

    /** full constructor */
    public TranOutHead(TranInOrg tranInOrg,TranOutOrg tranOutOrg, String noteNum,String countTranOutPeople, String docNum, String settDate, BigDecimal tranStatus,String reserveaA, String reserveaB, String reserveaC,BigDecimal persons,BigDecimal salary,BigDecimal interest,BigDecimal sumSalary) {
        this.countTranOutPeople=countTranOutPeople;
        this.tranInOrg = tranInOrg;
        this.tranOutOrg = tranOutOrg;
        this.noteNum = noteNum;
        this.docNum = docNum;
        this.settDate = settDate;
        this.tranStatus = tranStatus;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.persons = persons;
        this.salary = salary;
        this.interest = interest;
        this.sumSalary = sumSalary;
    }

    /** default constructor */
    public TranOutHead() {
    }

    /** minimal constructor */
    public TranOutHead(TranOutOrg tranOutOrg, BigDecimal tranStatus) {
        this.tranOutOrg = tranOutOrg;
        this.tranStatus = tranStatus;
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

    public BigDecimal getTranStatus() {
        return this.tranStatus;
    }

    public void setTranStatus(BigDecimal tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TranOutHead) ) return false;
        TranOutHead castOther = (TranOutHead) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getCountTranOutPeople() {
      return countTranOutPeople;
    }

    public void setCountTranOutPeople(String countTranOutPeople) {
      this.countTranOutPeople = countTranOutPeople;
    }

    public String getTranStatus2() {
      return tranStatus2;
    }

    public void setTranStatus2(String tranStatus2) {
      this.tranStatus2 = tranStatus2;
    }

    public BigDecimal getSum_Interst() {
      return sum_Interst;
    }

    public void setSum_Interst(BigDecimal sum_Interst) {
      this.sum_Interst = sum_Interst;
    }

    public BigDecimal getSum_salary() {
      return sum_salary;
    }

    public void setSum_salary(BigDecimal sum_salary) {
      this.sum_salary = sum_salary;
    }

    public BigDecimal getSum_sum() {
      return sum_sum;
    }

    public void setSum_sum(BigDecimal sum_sum) {
      this.sum_sum = sum_sum;
    }

    public String getTemp_pickState() {
      return temp_pickState;
    }

    public void setTemp_pickState(String temp_pickState) {
      this.temp_pickState = temp_pickState;
    }

    

}
