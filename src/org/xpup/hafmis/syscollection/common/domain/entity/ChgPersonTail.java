package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;


/** @author Hibernate CodeGenerator */
public class ChgPersonTail  extends DomainObject implements Serializable {

    /** persistent field */
    private ChgPersonHead chgPersonHead=new ChgPersonHead();

    /** nullable persistent field */
    private Emp emp=new Emp();
    private Integer empId;

    /** nullable persistent field */
    private String name="";
    
    /** 职工姓名 */
    private String temp_name;
    
    private String chgType="";

    /** nullable persistent field */
    private Integer cardKind=new Integer(0);
    
    /** nullable persistent field */
    private String cardNum="";

    /** 证件号码 */
    private String temp_cardNum;
    
    /** nullable persistent field */
    private String birthday="";

    /** nullable persistent field */
    private Integer sex=new Integer(0);

    /** nullable persistent field */
    private String dept="";

    /** nullable persistent field */
    private String tel="";

    /** nullable persistent field */
    private String mobileTel="";

    /** nullable persistent field */
    private BigDecimal salaryBase=new BigDecimal(0.00);
    /** persistent field */
    private BigDecimal temp_salaryBase = new BigDecimal(0.00);      

    /** nullable persistent field */
    private BigDecimal monthIncome=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal orgPay=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal empPay=new BigDecimal(0.00);
    
    /** nullable persistent field */
    private BigDecimal temp_orgPay=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal temp_empPay=new BigDecimal(0.00);

    private BigDecimal sumPay=new BigDecimal(0.00);//缴额合计

    /** persistent field */
    private Integer oldPayStatus=new Integer(0);
    /** persistent field */
    private String temp_oldPayStatus="";
    private String temp_oldPayStatus_qfc="";
    
    private Integer newPayStatus = new Integer(0);
    private String temp_newPayStatus = "";
    private Integer isNewUse;
    /**备选证件号码*/
    private String standbyCardNum;
    
    /** persistent field */
    private String reserveaA="";
    
    /** persistent field */
    private String reserveaB="";
    
    /** persistent field */
    private String reserveaC="";
    
    private String chgreason="";
    private String temp_chgreason="";

    public String getChgreason() {
      return chgreason;
    }

    public void setChgreason(String chgreason) {
      this.chgreason = chgreason;
    }

    /** full constructor */
    public ChgPersonTail( ChgPersonHead chgPersonHead, Emp emp,String chgType, String name, Integer cardKind, String cardNum, String birthday, Integer sex, String dept, String tel, String mobileTel, BigDecimal salaryBase, BigDecimal monthIncome, BigDecimal orgPay, BigDecimal empPay, Integer oldPayStatus,Integer newPayStatus, Integer isNewUse,String reserveaA,String reserveaB,String reserveaC,BigDecimal sumPay,String chgreason) {
       
        this.chgPersonHead = chgPersonHead;
        this.emp = emp;
        this.chgType = chgType;
        this.name = name;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.birthday = birthday;
        this.sex = sex;
        this.dept = dept;
        this.tel = tel;
        this.mobileTel = mobileTel;
        this.salaryBase = salaryBase;
        this.monthIncome = monthIncome;
        this.orgPay = orgPay;
        this.empPay = empPay;
        this.oldPayStatus = oldPayStatus;
        this.newPayStatus = newPayStatus;
        this.isNewUse = isNewUse;
        this.reserveaA=reserveaA;
        this.reserveaB=reserveaB;
        this.reserveaC=reserveaC;
        this.sumPay= sumPay;
        this.chgreason=chgreason;
    }

    /** default constructor */
    public ChgPersonTail() {
    }

    /** minimal constructor */
    public ChgPersonTail(ChgPersonHead chgPersonHead,Integer oldPayStatus) {
       
        this.chgPersonHead = chgPersonHead;
        this.oldPayStatus = oldPayStatus;
 
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCardKind() {
        return this.cardKind;
    }

    public void setCardKind(Integer cardKind) {
        this.cardKind = cardKind;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobileTel() {
        return this.mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public BigDecimal getSalaryBase() {
        return this.salaryBase;
    }

    public void setSalaryBase(BigDecimal salaryBase) {
        this.salaryBase = salaryBase;
    }

    public BigDecimal getMonthIncome() {
        return this.monthIncome;
    }

    public void setMonthIncome(BigDecimal monthIncome) {
        this.monthIncome = monthIncome;
    }

    public BigDecimal getOrgPay() {
        return this.orgPay;
    }

    public void setOrgPay(BigDecimal orgPay) {
        this.orgPay = orgPay;
    }

    public BigDecimal getEmpPay() {
        return this.empPay;
    }

    public void setEmpPay(BigDecimal empPay) {
        this.empPay = empPay;
    }

    public ChgPersonHead getChgPersonHead() {
      return chgPersonHead;
    }

    public void setChgPersonHead(ChgPersonHead chgPersonHead) {
      this.chgPersonHead = chgPersonHead;
    }

    public Emp getEmp() {
      return emp;
    }

    public void setEmp(Emp emp) {
      this.emp = emp;
    }

    public Integer getOldPayStatus() {
        return this.oldPayStatus;
    }

    public void setOldPayStatus(Integer oldPayStatus) {
        this.oldPayStatus = oldPayStatus;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ChgPersonTail) ) return false;
        ChgPersonTail castOther = (ChgPersonTail) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public Integer getIsNewUse() {
      return isNewUse;
    }

    public void setIsNewUse(Integer isNewUse) {
      this.isNewUse = isNewUse;
    }

    public String getChgType() {
      return chgType;
    }

    public void setChgType(String chgType) {
      this.chgType = chgType;
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

    public Integer getNewPayStatus() {
      return newPayStatus;
    }

    public void setNewPayStatus(Integer newPayStatus) {
      this.newPayStatus = newPayStatus;
    }

    public BigDecimal getSumPay() {
      return sumPay;
    }

    public void setSumPay(BigDecimal sumPay) {
      this.sumPay = sumPay;
    }


    public String getTemp_cardNum() {
      return temp_cardNum;
    }

    public void setTemp_cardNum(String temp_cardNum) {
      this.temp_cardNum = temp_cardNum;
    }

    public BigDecimal getTemp_empPay() {
      return temp_empPay;
    }

    public void setTemp_empPay(BigDecimal temp_empPay) {
      this.temp_empPay = temp_empPay;
    }

    public String getTemp_name() {
      return temp_name;
    }

    public void setTemp_name(String temp_name) {
      this.temp_name = temp_name;
    }

    public BigDecimal getTemp_orgPay() {
      return temp_orgPay;
    }

    public void setTemp_orgPay(BigDecimal temp_orgPay) {
      this.temp_orgPay = temp_orgPay;
    }

    public BigDecimal getTemp_salaryBase() {
      return temp_salaryBase; 
    }

    public void setTemp_salaryBase(BigDecimal temp_salaryBase) {
      this.temp_salaryBase = temp_salaryBase;
    }

    public String getTemp_oldPayStatus() {
      return temp_oldPayStatus;
    }

    public void setTemp_oldPayStatus(String temp_oldPayStatus) {
      this.temp_oldPayStatus = temp_oldPayStatus;
    }

    public String getTemp_oldPayStatus_qfc() {
      return temp_oldPayStatus_qfc;
    }

    public void setTemp_oldPayStatus_qfc(String temp_oldPayStatus_qfc) {
      this.temp_oldPayStatus_qfc = temp_oldPayStatus_qfc;
    }

    public String getTemp_newPayStatus() {
      return temp_newPayStatus;
    }

    public void setTemp_newPayStatus(String temp_newPayStatus) {
      this.temp_newPayStatus = temp_newPayStatus;
    }

    public String getTemp_chgreason() {
      return temp_chgreason;
    }

    public void setTemp_chgreason(String temp_chgreason) {
      this.temp_chgreason = temp_chgreason;
    }

    public String getStandbyCardNum() {
      return standbyCardNum;
    }

    public void setStandbyCardNum(String standbyCardNum) {
      this.standbyCardNum = standbyCardNum;
    }





}
