package org.xpup.hafmis.syscollection.common.domain.entity;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;

/** @author Hibernate CodeGenerator */
public class Emp extends DomainObject {
    private BigDecimal balance = new BigDecimal(0.00);
    private Integer empId; 
    /** persistent field */
    private Org org=new Org();
    /** persistent field */
    private EmpInfo empInfo=new EmpInfo();

    /** persistent field */
    private BigDecimal salaryBase = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal TEMP_salaryBase = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal orgPay = new BigDecimal(0.00);
    
    /** persistent field */
    private BigDecimal empPay = new BigDecimal(0.00);
    
    private BigDecimal paySum = new BigDecimal(0.00);

    /** persistent field */
    private String orgPayMonth;

    /** persistent field */
    private String empPayMonth;

    /** persistent field */
    private BigDecimal preIntegral = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal curIntegral= new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal preBalance= new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal curBalance= new BigDecimal(0.00);
    private String employeeState;
    /** persistent field */
    private BigDecimal payStatus = new BigDecimal(0.00);
    private String payStatusStr = "";
    /** nullable persistent field */
    private BigDecimal preIntegralSubA= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curIntegralSubA= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal preRateA= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curRateA= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal preIntegralSubB= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curIntegralSubB= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal preRateB= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curRateB= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal preIntegralSubC= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curIntegralSubC= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal preRateC= new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curRateC= new BigDecimal(0.00);
    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;
    
    /**
     * 职工代扣号
     */
    private String empAgentNum = "";

    /** nullable persistent field */
    private String reserveaC;
    private String oldEmpID;
    private String cardNum;
    /** 分段往年积数D */
    private BigDecimal preIntegralSubD = new BigDecimal(0.00);
    private BigDecimal curIntegralSubD = new BigDecimal(0.00);
    /**往年利率D*/
    private BigDecimal preRateD = new BigDecimal(0.0);
    private BigDecimal curRateD = new BigDecimal(0.0);
    /**分段往年积数E*/
    private BigDecimal preIntegralSubE = new BigDecimal(0.00);
    private BigDecimal curIntegralSubE = new BigDecimal(0.00);
    private BigDecimal preRateE = new BigDecimal(0.0);
    private BigDecimal curRateE = new BigDecimal(0.0);
    private BigDecimal preIntegralSubF = new BigDecimal(0.00);
    private BigDecimal curIntegralSubF = new BigDecimal(0.00);
    private BigDecimal preRateF = new BigDecimal(0.0);
    private BigDecimal curRateF = new BigDecimal(0.0);
    /** 分段往年积数G */
    private BigDecimal preIntegralSubG = new BigDecimal(0.00);
    private BigDecimal curIntegralSubG = new BigDecimal(0.00);
    /**往年利率G*/
    private BigDecimal preRateG = new BigDecimal(0.0);
    private BigDecimal curRateG = new BigDecimal(0.0);
    /**分段往年积数H*/
    private BigDecimal preIntegralSubH = new BigDecimal(0.00);
    private BigDecimal curIntegralSubH = new BigDecimal(0.00);
    private BigDecimal preRateH = new BigDecimal(0.0);
    private BigDecimal curRateH = new BigDecimal(0.0);
    private BigDecimal preIntegralSubI = new BigDecimal(0.00);
    private BigDecimal curIntegralSubI = new BigDecimal(0.00);
    private BigDecimal preRateI = new BigDecimal(0.0);
    private BigDecimal curRateI = new BigDecimal(0.0);
    /** 分段往年积数J */
    private BigDecimal preIntegralSubJ = new BigDecimal(0.00);
    private BigDecimal curIntegralSubJ = new BigDecimal(0.00);
    /**往年利率J*/
    private BigDecimal preRateJ = new BigDecimal(0.0);
    private BigDecimal curRateJ = new BigDecimal(0.0);
    /**分段往年积数K*/
    private BigDecimal preIntegralSubK = new BigDecimal(0.00);
    private BigDecimal curIntegralSubK = new BigDecimal(0.00);
    private BigDecimal preRateK = new BigDecimal(0.0);
    private BigDecimal curRateK = new BigDecimal(0.0);
    private BigDecimal preIntegralSubL = new BigDecimal(0.00);
    private BigDecimal curIntegralSubL = new BigDecimal(0.00);
    private BigDecimal preRateL = new BigDecimal(0.0);
    private BigDecimal curRateL = new BigDecimal(0.0);
    /**
     * 缴额合计
     */
    private BigDecimal empPayCount=new BigDecimal(0.00);
    private BigDecimal payOldYear = new BigDecimal(0.00);
    private int mul;

    /** full constructor */
    public Emp(Integer empId,Org org,EmpInfo empInfo, BigDecimal salaryBase, BigDecimal orgPay, BigDecimal empPay, String orgPayMonth, String empPayMonth, BigDecimal preIntegral, BigDecimal curIntegral, BigDecimal preBalance, BigDecimal curBalance, BigDecimal payStatus, BigDecimal preIntegralSubA, BigDecimal curIntegralSubA, BigDecimal preRateA, BigDecimal curRateA, BigDecimal preIntegralSubB, BigDecimal curIntegralSubB, BigDecimal preRateB, BigDecimal curRateB, BigDecimal preIntegralSubC, BigDecimal curIntegralSubC, BigDecimal preRateC, BigDecimal curRateC,
        BigDecimal preIntegralSubD, BigDecimal curIntegralSubD, BigDecimal preRateD, BigDecimal curRateD, BigDecimal preIntegralSubE, BigDecimal curIntegralSubE, BigDecimal preRateE, BigDecimal curRateE, BigDecimal preIntegralSubF, BigDecimal curIntegralSubF, BigDecimal preRateF, BigDecimal curRateF,
        BigDecimal preIntegralSubG, BigDecimal curIntegralSubG, BigDecimal preRateG, BigDecimal curRateG, BigDecimal preIntegralSubH, BigDecimal curIntegralSubH, BigDecimal preRateH, BigDecimal curRateH, BigDecimal preIntegralSubI, BigDecimal curIntegralSubI, BigDecimal preRateI, BigDecimal curRateI,
        BigDecimal preIntegralSubJ, BigDecimal curIntegralSubJ, BigDecimal preRateJ, BigDecimal curRateJ, BigDecimal preIntegralSubK, BigDecimal curIntegralSubK, BigDecimal preRateK, BigDecimal curRateK, BigDecimal preIntegralSubL, BigDecimal curIntegralSubL, BigDecimal preRateL, BigDecimal curRateL,
        String reserveaA, String reserveaB, String reserveaC,String oldEmpID) {
        this.empId = empId;  
        this.org = org;
        this.empInfo = empInfo;
        this.salaryBase = salaryBase;
        this.orgPay = orgPay;
        this.empPay = empPay;
        this.orgPayMonth = orgPayMonth;
        this.empPayMonth = empPayMonth;
        this.preIntegral = preIntegral;
        this.curIntegral = curIntegral;
        this.preBalance = preBalance;
        this.curBalance = curBalance;
        this.payStatus = payStatus;
        this.preIntegralSubA = preIntegralSubA;
        this.curIntegralSubA = curIntegralSubA;
        this.preRateA = preRateA;
        this.curRateA = curRateA;
        this.preIntegralSubB = preIntegralSubB;
        this.curIntegralSubB = curIntegralSubB;
        this.preRateB = preRateB;
        this.curRateB = curRateB;
        this.preIntegralSubC = preIntegralSubC;
        this.curIntegralSubC = curIntegralSubC;
        this.preRateC = preRateC;
        this.curRateC = curRateC;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.oldEmpID = oldEmpID;
        this.preIntegralSubD = new BigDecimal(0.00);
        this.curIntegralSubD = new BigDecimal(0.00);
        /**往年利率D*/
        this.preRateD = preRateD;
        this.curRateD = curRateD;
        /**分段往年积数E*/
        this.preIntegralSubE = preIntegralSubE;
        this.curIntegralSubE = curIntegralSubE;
        this.preRateE = preRateE;
        this.curRateE = curRateE;
        this.preIntegralSubF = preIntegralSubF;
        this.curIntegralSubF = curIntegralSubF;
        this.preRateF = preRateF;
        this.curRateF = curRateF;
        /** 分段往年积数G */
        this.preIntegralSubG = preIntegralSubG;
        this.curIntegralSubG = curIntegralSubG;
        /**往年利率G*/
        this.preRateG =preRateG;
        this.curRateG = curRateG;
        /**分段往年积数H*/
        this.preIntegralSubH = preIntegralSubH;
        this.curIntegralSubH = curIntegralSubH;
        this.preRateH = preRateH;
        this.curRateH = curRateH;
        this.preIntegralSubI = preIntegralSubI;
        this.curIntegralSubI = curIntegralSubI;
        this.preRateI = preRateI;
        this.curRateI = curRateI;
        /** 分段往年积数J */
        this.preIntegralSubJ = preIntegralSubJ;
        this.curIntegralSubJ = curIntegralSubJ;
        /**往年利率J*/
        this.preRateJ = preRateJ;
        this.curRateJ = curRateJ;
        /**分段往年积数K*/
        this.preIntegralSubK = preIntegralSubK;
        this.curIntegralSubK = curIntegralSubK;
        this.preRateK = preRateK;
        this.curRateK = curRateK;
        this.preIntegralSubL = preIntegralSubL;
        this.curIntegralSubL = curIntegralSubL;
        this.preRateL = preRateL;
        this.curRateL = curRateL;
    }

    /** default constructor */
    public Emp() {
    }

    /** minimal constructor */
    public Emp(Integer empId,Org org, EmpInfo empInfo, BigDecimal salaryBase, BigDecimal orgPay, BigDecimal empPay, String orgPayMonth, String empPayMonth, BigDecimal preIntegral, BigDecimal curIntegral, BigDecimal preBalance, BigDecimal curBalance, BigDecimal payStatus) {
        this.empId = empId;    
        this.org = org;
        this.empInfo = empInfo;
        this.salaryBase = salaryBase;
        this.orgPay = orgPay;
        this.empPay = empPay;
        this.orgPayMonth = orgPayMonth;
        this.empPayMonth = empPayMonth;
        this.preIntegral = preIntegral;
        this.curIntegral = curIntegral;
        this.preBalance = preBalance;
        this.curBalance = curBalance;
        this.payStatus = payStatus;
    }
    
    public Org getOrg() {
        return this.org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public EmpInfo getEmpInfo() {
        return this.empInfo;
    }

    public void setEmpInfo(EmpInfo empInfo) {
        this.empInfo = empInfo;
    }

    public BigDecimal getSalaryBase() {
        return this.salaryBase;
    }

    public void setSalaryBase(BigDecimal salaryBase) {
        this.salaryBase = salaryBase;
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

    public String getOrgPayMonth() {
        return this.orgPayMonth;
    }

    public void setOrgPayMonth(String orgPayMonth) {
        this.orgPayMonth = orgPayMonth;
    }

    public String getEmpPayMonth() {
        return this.empPayMonth;
    }

    public void setEmpPayMonth(String empPayMonth) {
        this.empPayMonth = empPayMonth;
    }

    public BigDecimal getPreIntegral() {
        return this.preIntegral;
    }

    public void setPreIntegral(BigDecimal preIntegral) {
        this.preIntegral = preIntegral;
    }

    public BigDecimal getCurIntegral() {
        return this.curIntegral;
    }

    public void setCurIntegral(BigDecimal curIntegral) {
        this.curIntegral = curIntegral;
    }

    public BigDecimal getPreBalance() {
        return this.preBalance;
    }

    public void setPreBalance(BigDecimal preBalance) {
        this.preBalance = preBalance;
    }

    public BigDecimal getCurBalance() {
        return this.curBalance;
    }

    public void setCurBalance(BigDecimal curBalance) {
        this.curBalance = curBalance;
    }

    public BigDecimal getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(BigDecimal payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPreIntegralSubA() {
        return this.preIntegralSubA;
    }

    public void setPreIntegralSubA(BigDecimal preIntegralSubA) {
        this.preIntegralSubA = preIntegralSubA;
    }

    public BigDecimal getCurIntegralSubA() {
        return this.curIntegralSubA;
    }

    public void setCurIntegralSubA(BigDecimal curIntegralSubA) {
        this.curIntegralSubA = curIntegralSubA;
    }

    public BigDecimal getPreRateA() {
        return this.preRateA;
    }

    public void setPreRateA(BigDecimal preRateA) {
        this.preRateA = preRateA;
    }

    public BigDecimal getCurRateA() {
        return this.curRateA;
    }

    public void setCurRateA(BigDecimal curRateA) {
        this.curRateA = curRateA;
    }

    public BigDecimal getPreIntegralSubB() {
        return this.preIntegralSubB;
    }

    public void setPreIntegralSubB(BigDecimal preIntegralSubB) {
        this.preIntegralSubB = preIntegralSubB;
    }

    public BigDecimal getCurIntegralSubB() {
        return this.curIntegralSubB;
    }

    public void setCurIntegralSubB(BigDecimal curIntegralSubB) {
        this.curIntegralSubB = curIntegralSubB;
    }

    public BigDecimal getPreRateB() {
        return this.preRateB;
    }

    public void setPreRateB(BigDecimal preRateB) {
        this.preRateB = preRateB;
    }

    public BigDecimal getCurRateB() {
        return this.curRateB;
    }

    public void setCurRateB(BigDecimal curRateB) {
        this.curRateB = curRateB;
    }

    public BigDecimal getPreIntegralSubC() {
        return this.preIntegralSubC;
    }

    public void setPreIntegralSubC(BigDecimal preIntegralSubC) {
        this.preIntegralSubC = preIntegralSubC;
    }

    public BigDecimal getCurIntegralSubC() {
        return this.curIntegralSubC;
    }

    public void setCurIntegralSubC(BigDecimal curIntegralSubC) {
        this.curIntegralSubC = curIntegralSubC;
    }

    public BigDecimal getPreRateC() {
        return this.preRateC;
    }

    public void setPreRateC(BigDecimal preRateC) {
        this.preRateC = preRateC;
    }

    public BigDecimal getCurRateC() {
        return this.curRateC;
    }

    public void setCurRateC(BigDecimal curRateC) {
        this.curRateC = curRateC;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
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
    public boolean equals(Object other) {
        if ( !(other instanceof Emp) ) return false;
        Emp castOther = (Emp) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getOldEmpID() {
      return oldEmpID;
    }

    public void setOldEmpID(String oldEmpID) {
      this.oldEmpID = oldEmpID;
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }

    public BigDecimal getTEMP_salaryBase() {
      return TEMP_salaryBase;
    }

    public void setTEMP_salaryBase(BigDecimal base) {
      TEMP_salaryBase = base;
    }

    public BigDecimal getPaySum() {
      return paySum;
    }

    public void setPaySum(BigDecimal paySum) {
      this.paySum = this.orgPay.add(this.empPay);
    }
    public BigDecimal getEmpPayCount() {
      return empPayCount;
    }

    public void setEmpPayCount(BigDecimal empPayCount) {
      this.empPayCount = empPayCount;
    }
    public BigDecimal getBalance() {
      return balance;
    }

    public void setBalance(BigDecimal balance) {
      this.balance = balance;
    }

    public String getEmployeeState() {
      return employeeState;
    }

    public void setEmployeeState(String employeeState) {
      this.employeeState = employeeState;
    }

    public String getPayStatusStr() {
      return payStatusStr;
    }

    public void setPayStatusStr(String payStatusStr) {
      this.payStatusStr = payStatusStr;
    }

    public String getCardNum() {
      return cardNum;
    }

    public void setCardNum(String cardNum) {
      this.cardNum = cardNum;
    }

    public BigDecimal getCurIntegralSubD() {
      return curIntegralSubD;
    }

    public void setCurIntegralSubD(BigDecimal curIntegralSubD) {
      this.curIntegralSubD = curIntegralSubD;
    }

    public BigDecimal getCurIntegralSubE() {
      return curIntegralSubE;
    }

    public void setCurIntegralSubE(BigDecimal curIntegralSubE) {
      this.curIntegralSubE = curIntegralSubE;
    }

    public BigDecimal getCurIntegralSubF() {
      return curIntegralSubF;
    }

    public void setCurIntegralSubF(BigDecimal curIntegralSubF) {
      this.curIntegralSubF = curIntegralSubF;
    }

    public BigDecimal getCurIntegralSubG() {
      return curIntegralSubG;
    }

    public void setCurIntegralSubG(BigDecimal curIntegralSubG) {
      this.curIntegralSubG = curIntegralSubG;
    }

    public BigDecimal getCurIntegralSubH() {
      return curIntegralSubH;
    }

    public void setCurIntegralSubH(BigDecimal curIntegralSubH) {
      this.curIntegralSubH = curIntegralSubH;
    }

    public BigDecimal getCurIntegralSubI() {
      return curIntegralSubI;
    }

    public void setCurIntegralSubI(BigDecimal curIntegralSubI) {
      this.curIntegralSubI = curIntegralSubI;
    }

    public BigDecimal getCurIntegralSubJ() {
      return curIntegralSubJ;
    }

    public void setCurIntegralSubJ(BigDecimal curIntegralSubJ) {
      this.curIntegralSubJ = curIntegralSubJ;
    }

    public BigDecimal getCurIntegralSubK() {
      return curIntegralSubK;
    }

    public void setCurIntegralSubK(BigDecimal curIntegralSubK) {
      this.curIntegralSubK = curIntegralSubK;
    }

    public BigDecimal getCurIntegralSubL() {
      return curIntegralSubL;
    }

    public void setCurIntegralSubL(BigDecimal curIntegralSubL) {
      this.curIntegralSubL = curIntegralSubL;
    }

    public BigDecimal getCurRateD() {
      return curRateD;
    }

    public void setCurRateD(BigDecimal curRateD) {
      this.curRateD = curRateD;
    }

    public BigDecimal getCurRateE() {
      return curRateE;
    }

    public void setCurRateE(BigDecimal curRateE) {
      this.curRateE = curRateE;
    }

    public BigDecimal getCurRateF() {
      return curRateF;
    }

    public void setCurRateF(BigDecimal curRateF) {
      this.curRateF = curRateF;
    }

    public BigDecimal getCurRateG() {
      return curRateG;
    }

    public void setCurRateG(BigDecimal curRateG) {
      this.curRateG = curRateG;
    }

    public BigDecimal getCurRateH() {
      return curRateH;
    }

    public void setCurRateH(BigDecimal curRateH) {
      this.curRateH = curRateH;
    }

    public BigDecimal getCurRateI() {
      return curRateI;
    }

    public void setCurRateI(BigDecimal curRateI) {
      this.curRateI = curRateI;
    }

    public BigDecimal getCurRateJ() {
      return curRateJ;
    }

    public void setCurRateJ(BigDecimal curRateJ) {
      this.curRateJ = curRateJ;
    }

    public BigDecimal getCurRateK() {
      return curRateK;
    }

    public void setCurRateK(BigDecimal curRateK) {
      this.curRateK = curRateK;
    }

    public BigDecimal getCurRateL() {
      return curRateL;
    }

    public void setCurRateL(BigDecimal curRateL) {
      this.curRateL = curRateL;
    }

    public BigDecimal getPreIntegralSubD() {
      return preIntegralSubD;
    }

    public void setPreIntegralSubD(BigDecimal preIntegralSubD) {
      this.preIntegralSubD = preIntegralSubD;
    }

    public BigDecimal getPreIntegralSubE() {
      return preIntegralSubE;
    }

    public void setPreIntegralSubE(BigDecimal preIntegralSubE) {
      this.preIntegralSubE = preIntegralSubE;
    }

    public BigDecimal getPreIntegralSubF() {
      return preIntegralSubF;
    }

    public void setPreIntegralSubF(BigDecimal preIntegralSubF) {
      this.preIntegralSubF = preIntegralSubF;
    }

    public BigDecimal getPreIntegralSubG() {
      return preIntegralSubG;
    }

    public void setPreIntegralSubG(BigDecimal preIntegralSubG) {
      this.preIntegralSubG = preIntegralSubG;
    }

    public BigDecimal getPreIntegralSubH() {
      return preIntegralSubH;
    }

    public void setPreIntegralSubH(BigDecimal preIntegralSubH) {
      this.preIntegralSubH = preIntegralSubH;
    }

    public BigDecimal getPreIntegralSubI() {
      return preIntegralSubI;
    }

    public void setPreIntegralSubI(BigDecimal preIntegralSubI) {
      this.preIntegralSubI = preIntegralSubI;
    }

    public BigDecimal getPreIntegralSubJ() {
      return preIntegralSubJ;
    }

    public void setPreIntegralSubJ(BigDecimal preIntegralSubJ) {
      this.preIntegralSubJ = preIntegralSubJ;
    }

    public BigDecimal getPreIntegralSubK() {
      return preIntegralSubK;
    }

    public void setPreIntegralSubK(BigDecimal preIntegralSubK) {
      this.preIntegralSubK = preIntegralSubK;
    }

    public BigDecimal getPreIntegralSubL() {
      return preIntegralSubL;
    }

    public void setPreIntegralSubL(BigDecimal preIntegralSubL) {
      this.preIntegralSubL = preIntegralSubL;
    }

    public BigDecimal getPreRateD() {
      return preRateD;
    }

    public void setPreRateD(BigDecimal preRateD) {
      this.preRateD = preRateD;
    }

    public BigDecimal getPreRateE() {
      return preRateE;
    }

    public void setPreRateE(BigDecimal preRateE) {
      this.preRateE = preRateE;
    }

    public BigDecimal getPreRateF() {
      return preRateF;
    }

    public void setPreRateF(BigDecimal preRateF) {
      this.preRateF = preRateF;
    }

    public BigDecimal getPreRateG() {
      return preRateG;
    }

    public void setPreRateG(BigDecimal preRateG) {
      this.preRateG = preRateG;
    }

    public BigDecimal getPreRateH() {
      return preRateH;
    }

    public void setPreRateH(BigDecimal preRateH) {
      this.preRateH = preRateH;
    }

    public BigDecimal getPreRateI() {
      return preRateI;
    }

    public void setPreRateI(BigDecimal preRateI) {
      this.preRateI = preRateI;
    }

    public BigDecimal getPreRateJ() {
      return preRateJ;
    }

    public void setPreRateJ(BigDecimal preRateJ) {
      this.preRateJ = preRateJ;
    }

    public BigDecimal getPreRateK() {
      return preRateK;
    }

    public void setPreRateK(BigDecimal preRateK) {
      this.preRateK = preRateK;
    }

    public BigDecimal getPreRateL() {
      return preRateL;
    }

    public void setPreRateL(BigDecimal preRateL) {
      this.preRateL = preRateL;
    }

    public String getEmpAgentNum() {
      return empAgentNum;
    }

    public void setEmpAgentNum(String empAgentNum) {
      this.empAgentNum = empAgentNum;
    }

    public BigDecimal getPayOldYear() {
      return payOldYear;
    }

    public void setPayOldYear(BigDecimal payOldYear) {
      this.payOldYear = payOldYear;
    }

    public int getMul() {
      return mul;
    }

    public void setMul(int mul) {
      this.mul = mul;
    }

  
}
