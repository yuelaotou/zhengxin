package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class TranOutTail extends DomainObject {
//  222 2 2 2 2 
    /** persistent field */
    private TranOutHead tranOutHead = new TranOutHead();
  
    /** nullable persistent field */
    private Emp emp = new Emp();
    private Integer empId;
    
    private BigDecimal sumPay = new  BigDecimal(0.00);

    /** persistent field */
    private BigDecimal preBalance = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal curBalance = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal preInterest = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal curInterest = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal preIntegral = new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal curIntegral = new BigDecimal(0.00);
    private BigDecimal isSettIntrerest = new BigDecimal(0.00);
    /** 分段往年积数a */
    private BigDecimal preIntegralSubA = new BigDecimal(0.00);
    private BigDecimal curIntegralSubA = new BigDecimal(0.00);
    /**往年利率a*/
    private BigDecimal preRateA = new BigDecimal(0.0);
    private BigDecimal curRateA = new BigDecimal(0.0);
    /**分段往年积数b*/
    private BigDecimal preIntegralSubB = new BigDecimal(0.00);
    private BigDecimal curIntegralSubB = new BigDecimal(0.00);
    private BigDecimal preRateB = new BigDecimal(0.0);
    private BigDecimal curRateB = new BigDecimal(0.0);
    private BigDecimal preIntegralSubC = new BigDecimal(0.00);
    private BigDecimal curIntegralSubC = new BigDecimal(0.00);
    private BigDecimal preRateC = new BigDecimal(0.0);
    private BigDecimal curRateC = new BigDecimal(0.0);
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
    /*
     * wzq
     */
    private BigDecimal sumBalance = new BigDecimal(0.00);//员工金额
    private BigDecimal sumInterest = new BigDecimal(0.00);//员工利息
    private String empName = null;//员工姓名
    private BigDecimal sumMoney = new BigDecimal(0.00);
    
    
    /**备选a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    //转移原因
    private String tranReason = "";
    private Integer tranin_empid;
    /**统计转出总金额*/
    private BigDecimal tranOutAmount =new BigDecimal(0.00);
    /** full constructor */
    public TranOutTail(TranOutHead tranOutHead,Integer empId,BigDecimal tranOutAmount, BigDecimal preBalance, BigDecimal curBalance, BigDecimal preInterest, BigDecimal curInterest, BigDecimal preIntegral, BigDecimal curIntegral, BigDecimal isSettIntrerest, BigDecimal preIntegralSubA, BigDecimal curIntegralSubA, BigDecimal preRateA, BigDecimal curRateA, BigDecimal preIntegralSubB, BigDecimal curIntegralSubB, BigDecimal preRateB, BigDecimal curRateB, BigDecimal preIntegralSubC, BigDecimal curIntegralSubC, BigDecimal preRateC, BigDecimal curRateC, String reserveaA, String reserveaB, String reserveaC,BigDecimal sumBalance,BigDecimal sumInterest,String empName,Integer tranin_empid) {
      this.tranOutAmount = tranOutAmount;
      this.tranOutHead = tranOutHead;
      this.empId = empId;
      this.preBalance = preBalance;
      this.curBalance = curBalance;
      this.preInterest = preInterest;
      this.curInterest = curInterest;
      this.preIntegral = preIntegral;
      this.curIntegral = curIntegral;
      this.isSettIntrerest = isSettIntrerest;
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
      this.sumBalance = sumBalance;
      this.sumInterest = sumInterest;
      this.empName = empName;
      this.tranin_empid=tranin_empid;
  }

  /** default constructor */
  public TranOutTail() {
  }

  /** minimal constructor */
  public TranOutTail(TranOutHead tranOutHead,Emp emp, BigDecimal preBalance, BigDecimal curBalance, BigDecimal preInterest, BigDecimal curInterest, BigDecimal preIntegral, BigDecimal curIntegral, BigDecimal isSettIntrerest) {
      this.tranOutHead = tranOutHead;
      this.emp = emp;
      this.preBalance = preBalance;
      this.curBalance = curBalance;
      this.preInterest = preInterest;
      this.curInterest = curInterest;
      this.preIntegral = preIntegral;
      this.curIntegral = curIntegral;
      this.isSettIntrerest = isSettIntrerest;
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
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TranOutTail) ) return false;
        TranOutTail castOther = (TranOutTail) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public BigDecimal getIsSettIntrerest() {
      return isSettIntrerest;
    }

    public void setIsSettIntrerest(BigDecimal isSettIntrerest) {
      this.isSettIntrerest = isSettIntrerest;
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

    public TranOutHead getTranOutHead() {
      return tranOutHead;
    }

    public void setTranOutHead(TranOutHead tranOutHead) {
      this.tranOutHead = tranOutHead;
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }


    public BigDecimal getSumPay() {
      return sumPay;
    }

    public void setSumPay(BigDecimal sumPay) {
      this.sumPay = sumPay;
    }


    public BigDecimal getTranOutAmount() {
      return tranOutAmount;
    }

    public void setTranOutAmount(BigDecimal tranOutAmount) {
      this.tranOutAmount = tranOutAmount;
    }

    public BigDecimal getSumBalance() {
      return sumBalance;
    }

    public void setSumBalance(BigDecimal sumBalance) {
      if(sumBalance != null && !sumBalance.equals("")){
        sumBalance = sumBalance.divide(new BigDecimal(1.00),2,BigDecimal.ROUND_HALF_DOWN);
      }
      this.sumBalance = sumBalance;
    }

    public BigDecimal getSumInterest() {
      return sumInterest;
    }

    public void setSumInterest(BigDecimal sumInterest) {
      if(sumInterest != null && !sumInterest.equals("")){
        sumInterest = sumInterest.divide(new BigDecimal(1.00),2,BigDecimal.ROUND_HALF_DOWN);
      }
      this.sumInterest = sumInterest;
    }

    public String getEmpName() {
      return empName;
    }

    public void setEmpName(String empName) {
      this.empName = empName;
    }

    public BigDecimal getSumMoney() {
      return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
      if(sumMoney != null && !sumMoney.equals("")){
        sumMoney = sumMoney.divide(new BigDecimal(1.00),2,BigDecimal.ROUND_HALF_DOWN);
      }
      this.sumMoney = sumMoney;
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

    public Integer getTranin_empid() {
      return tranin_empid;
    }

    public void setTranin_empid(Integer tranin_empid) {
      this.tranin_empid = tranin_empid;
    }

    public String getTranReason() {
      return tranReason;
    }

    public void setTranReason(String tranReason) {
      this.tranReason = tranReason;
    }



}
