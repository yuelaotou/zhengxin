package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class TranInTail extends DomainObject {

  /** persistent field */
  private TranInHead tranInHead = new TranInHead();

  /** nullable persistent field */
  private Emp emp = new Emp();

  private Integer empId;

  /** nullable persistent field */
  private String name;

  /** nullable persistent field */
  private Integer cardKind=null;

  /** nullable persistent field */
  private String cardNum;

  /** nullable persistent field */
  private String birthday;

  /** nullable persistent field */
  private Integer sex=null;

  /** nullable persistent field */
  private String dept;

  /** nullable persistent field */
  private String tel;

  /** nullable persistent field */
  private String mobileTel;

  /** nullable persistent field */
  private BigDecimal salaryBase = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal monthIncome = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal orgPay = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal empPay = new BigDecimal(0.00);

  /** nullable persistent field */
  private String openDate;

  /** nullable persistent field */
  private BigDecimal preBalance = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal curBalance = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal preInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal curInterest = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal preIntegral = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal curIntegral = new BigDecimal(0.00);

  private BigDecimal mergerEmpid = new BigDecimal(0.00);

  /** 分段往年积数a */
  private BigDecimal preIntegralSubA = new BigDecimal(0.00);

  private BigDecimal curIntegralSubA = new BigDecimal(0.00);

  /** 往年利率a */
  private BigDecimal preRateA = new BigDecimal(0.00);

  private BigDecimal curRateA = new BigDecimal(0.00);

  /** 分段往年积数b */
  private BigDecimal preIntegralSubB = new BigDecimal(0.00);

  private BigDecimal curIntegralSubB = new BigDecimal(0.00);

  private BigDecimal preRateB = new BigDecimal(0.00);

  private BigDecimal curRateB = new BigDecimal(0.00);

  private BigDecimal preIntegralSubC = new BigDecimal(0.00);

  private BigDecimal curIntegralSubC = new BigDecimal(0.00);

  private BigDecimal preRateC = new BigDecimal(0.00);

  private BigDecimal curRateC = new BigDecimal(0.00);

  /** 求去转入的值 */
  private BigDecimal sumBalance = new BigDecimal(0.00);

  private BigDecimal sumInterest = new BigDecimal(0.00);

  private BigDecimal traninAmount = new BigDecimal(0.00);
  
  private BigDecimal sumMoney = new BigDecimal(0.00);

  private Integer tranOutEmpId;
  /** 备选a */
  private String reserveaA;

  private String reserveaB;

  private String reserveaC;
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
  
  /**备选证件号码*/
  private String standbyCardNum;
  
  /**AA312表中加一字段：is_auto_chg varchar2(1) N 是否应用过自动变更0否(空也是否)，1是*/ 
  private String isAutoChg="0";


  public String getIsAutoChg() {
    return isAutoChg;
  }

  public void setIsAutoChg(String isAutoChg) {
    this.isAutoChg = isAutoChg;
  }

  public String getStandbyCardNum() {
    return standbyCardNum;
  }

  public void setStandbyCardNum(String standbyCardNum) {
    this.standbyCardNum = standbyCardNum;
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

  public void setSumBalance(BigDecimal sumBalance) {
    this.sumBalance = sumBalance;
  }

  public void setSumInterest(BigDecimal sumInterest) {
    this.sumInterest = sumInterest;
  }

  public void setTraninAmount(BigDecimal traninAmount) {
    this.traninAmount = traninAmount;
  }

  /** full constructor */
  public TranInTail(TranInHead tranInHead, Integer empId, String name,
      Integer cardKind, String cardNum, String birthday, Integer sex,
      String dept, String tel, String mobileTel, BigDecimal salaryBase,
      BigDecimal monthIncome, BigDecimal orgPay, BigDecimal empPay,
      String openDate, BigDecimal preBalance, BigDecimal curBalance,
      BigDecimal preInterest, BigDecimal curInterest, BigDecimal preIntegral,
      BigDecimal curIntegral, BigDecimal mergerEmpid,
      BigDecimal preIntegralSubA, BigDecimal curIntegralSubA,
      BigDecimal preRateA, BigDecimal curRateA, BigDecimal preIntegralSubB,
      BigDecimal curIntegralSubB, BigDecimal preRateB, BigDecimal curRateB,
      BigDecimal preIntegralSubC, BigDecimal curIntegralSubC,
      BigDecimal preRateC, BigDecimal curRateC, String reserveaA,
      String reserveaB, String reserveaC, BigDecimal sumBalance,
      BigDecimal sumInterest, BigDecimal traninAmount) {
    this.sumBalance = sumBalance;
    this.sumInterest = sumInterest;
    this.traninAmount = traninAmount;
    this.tranInHead = tranInHead;
    this.empId = empId;
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
    this.openDate = openDate;
    this.preBalance = preBalance;
    this.curBalance = curBalance;
    this.preInterest = preInterest;
    this.curInterest = curInterest;
    this.preIntegral = preIntegral;
    this.curIntegral = curIntegral;
    this.mergerEmpid = mergerEmpid;
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
  }

  /** default constructor */
  public TranInTail() {
  }

  /** minimal constructor */
  public TranInTail(TranInHead tranInHeadId) {
    this.tranInHead = tranInHeadId;
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

  public String getOpenDate() {
    return this.openDate;
  }

  public void setOpenDate(String openDate) {
    this.openDate = openDate;
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

  public BigDecimal getPreInterest() {
    return this.preInterest;
  }

  public void setPreInterest(BigDecimal preInterest) {
    this.preInterest = preInterest;
  }

  public BigDecimal getCurInterest() {
    return this.curInterest;
  }

  public void setCurInterest(BigDecimal curInterest) {
    this.curInterest = curInterest;
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

  public String toString() {
    return new ToStringBuilder(this).append("id", getId()).toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof TranInTail))
      return false;
    TranInTail castOther = (TranInTail) other;
    return new EqualsBuilder().append(this.getId(), castOther.getId())
        .isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getId()).toHashCode();
  }

  public BigDecimal getCurIntegralSubA() {
    return curIntegralSubA;
  }

  public void setCurIntegralSubA(BigDecimal curIntegralSubA) {
    this.curIntegralSubA = curIntegralSubA;
  }

  public BigDecimal getCurIntegralSubB() {
    return curIntegralSubB;
  }

  public void setCurIntegralSubB(BigDecimal curIntegralSubB) {
    this.curIntegralSubB = curIntegralSubB;
  }

  public BigDecimal getCurIntegralSubC() {
    return curIntegralSubC;
  }

  public void setCurIntegralSubC(BigDecimal curIntegralSubC) {
    this.curIntegralSubC = curIntegralSubC;
  }

  public BigDecimal getCurRateA() {
    return curRateA;
  }

  public void setCurRateA(BigDecimal curRateA) {
    this.curRateA = curRateA;
  }

  public BigDecimal getCurRateB() {
    return curRateB;
  }

  public void setCurRateB(BigDecimal curRateB) {
    this.curRateB = curRateB;
  }

  public BigDecimal getCurRateC() {
    return curRateC;
  }

  public void setCurRateC(BigDecimal curRateC) {
    this.curRateC = curRateC;
  }

  public BigDecimal getMergerEmpid() {
    return mergerEmpid;
  }

  public void setMergerEmpid(BigDecimal mergerEmpid) {
    this.mergerEmpid = mergerEmpid;
  }

  public BigDecimal getPreIntegralSubA() {
    return preIntegralSubA;
  }

  public void setPreIntegralSubA(BigDecimal preIntegralSubA) {
    this.preIntegralSubA = preIntegralSubA;
  }

  public BigDecimal getPreIntegralSubB() {
    return preIntegralSubB;
  }

  public void setPreIntegralSubB(BigDecimal preIntegralSubB) {
    this.preIntegralSubB = preIntegralSubB;
  }

  public BigDecimal getPreIntegralSubC() {
    return preIntegralSubC;
  }

  public void setPreIntegralSubC(BigDecimal preIntegralSubC) {
    this.preIntegralSubC = preIntegralSubC;
  }

  public BigDecimal getPreRateA() {
    return preRateA;
  }

  public void setPreRateA(BigDecimal preRateA) {
    this.preRateA = preRateA;
  }

  public BigDecimal getPreRateB() {
    return preRateB;
  }

  public void setPreRateB(BigDecimal preRateB) {
    this.preRateB = preRateB;
  }

  public BigDecimal getPreRateC() {
    return preRateC;
  }

  public void setPreRateC(BigDecimal preRateC) {
    this.preRateC = preRateC;
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

  public Emp getEmp() {
    return emp;
  }

  public void setEmp(Emp emp) {
    this.emp = emp;
  }

  public TranInHead getTranInHead() {
    return tranInHead;
  }

  public void setTranInHeadId(TranInHead tranInHead) {
    this.tranInHead = tranInHead;
  }

  public void setTranInHead(TranInHead tranInHead) {
    this.tranInHead = tranInHead;
  }

  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }

  public BigDecimal getSumBalance() {
    return sumBalance;
  }

  public void setSumBalance(BigDecimal preBalance, BigDecimal curBalance) {
    this.sumBalance = preBalance.add(curBalance);
  }

  public BigDecimal getSumInterest() {
    return sumInterest;
  }

  public void setSumInterest(BigDecimal preInterest, BigDecimal curInterest) {
    this.sumInterest = preInterest.add(curInterest);
  }

  public BigDecimal getTraninAmount() {
    return traninAmount;
  }

  public void setTraninAmount(BigDecimal sumInterest, BigDecimal sumBalance) {
    this.traninAmount = sumBalance.add(sumInterest);
  }

  public Integer getTranOutEmpId() {
    return tranOutEmpId;
  }

  public void setTranOutEmpId(Integer tranOutEmpId) {
    this.tranOutEmpId = tranOutEmpId;
  }

  public BigDecimal getSumMoney() {
    return sumMoney;
  }

  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }

}
