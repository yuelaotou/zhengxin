package org.xpup.hafmis.syscollection.common.domain.entity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;
/** @author Hibernate CodeGenerator */
public class PickTail extends DomainObject {
//  部分提取人数：1 销户提取人数：1 提取总人数：2 提取金额：2000 销户利息：100 提取总金额：4100
    private int somePick;//部分提取
    private int distoryPick;//销户提取
    private int sumPerson;//总提取人数
    private BigDecimal sumBalance=new BigDecimal(0.00);//提取金额
    private BigDecimal sumInterest=new BigDecimal(0.00);//提取利息
    private BigDecimal sumTotal= new BigDecimal(0.00);//利息加金额
    private String pickDisplayType;
    private BigDecimal pickSalary= new BigDecimal(0.00);
    private BigDecimal total= new BigDecimal(0.00);
    private BigDecimal pickInterest= new BigDecimal(0.00);
    private String empName ;//为了排序用　不能直接pt.getEmpName()而得到职工名
    private String time;//
    //排序的字段就可以直接写pt.empName
    /** persistent field */
    private Emp emp = new Emp();
    private String reason;
    /** persistent field */
    private BigDecimal pickType;
    private String temp_PickType;

    /** persistent field */
    private BigDecimal pickPreBalance;

    /** persistent field */
    private BigDecimal pickCurBalance;

    /** persistent field */
    private BigDecimal pickPreInterest;

    /** persistent field */
    private BigDecimal pickCurInterest;
    private Integer empId;

    /** persistent field */
    private BigDecimal pickReason;
    /**备选a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;

    //贷款账号 合同编号
    private String loankouacc;
    private String contractid;
    
    private PickHead pickHead = new PickHead();
    private SpecialPick specialPick = new SpecialPick();
    private String photourl="";
    public PickTail(PickTail pick, String time) throws Exception {
      BeanUtils.copyProperties(this, pick);
      this.time = time;
    }
    private BigDecimal preIntegral = new BigDecimal(0.00);
    private BigDecimal curIntegral= new BigDecimal(0.00);
    /** 分段往年积数A */
    private BigDecimal preIntegralSubA= new BigDecimal(0.00);
    private BigDecimal curIntegralSubA= new BigDecimal(0.00);
    private BigDecimal preRateA= new BigDecimal(0.00);
    private BigDecimal curRateA= new BigDecimal(0.00);
    /** 分段往年积数B */
    private BigDecimal preIntegralSubB= new BigDecimal(0.00);
    private BigDecimal curIntegralSubB= new BigDecimal(0.00);
    private BigDecimal preRateB= new BigDecimal(0.00);
    private BigDecimal curRateB= new BigDecimal(0.00);
    /** 分段往年积数C */
    private BigDecimal preIntegralSubC= new BigDecimal(0.00);
    private BigDecimal curIntegralSubC= new BigDecimal(0.00);
    private BigDecimal preRateC= new BigDecimal(0.00);
    private BigDecimal curRateC= new BigDecimal(0.00);
    /** 分段往年积数D */
    private BigDecimal preIntegralSubD = new BigDecimal(0.00);
    private BigDecimal curIntegralSubD = new BigDecimal(0.00);
    private BigDecimal preRateD = new BigDecimal(0.0);
    private BigDecimal curRateD = new BigDecimal(0.0);
    /** 分段往年积数E */
    private BigDecimal preIntegralSubE = new BigDecimal(0.00);
    private BigDecimal curIntegralSubE = new BigDecimal(0.00);
    private BigDecimal preRateE = new BigDecimal(0.0);
    private BigDecimal curRateE = new BigDecimal(0.0);
    /** 分段往年积数F*/
    private BigDecimal preIntegralSubF = new BigDecimal(0.00);
    private BigDecimal curIntegralSubF = new BigDecimal(0.00);
    private BigDecimal preRateF = new BigDecimal(0.0);
    private BigDecimal curRateF = new BigDecimal(0.0);
    /** 分段往年积数G */
    private BigDecimal preIntegralSubG = new BigDecimal(0.00);
    private BigDecimal curIntegralSubG = new BigDecimal(0.00);
    private BigDecimal preRateG = new BigDecimal(0.0);
    private BigDecimal curRateG = new BigDecimal(0.0);
    /** 分段往年积数H */
    private BigDecimal preIntegralSubH = new BigDecimal(0.00);
    private BigDecimal curIntegralSubH = new BigDecimal(0.00);
    private BigDecimal preRateH = new BigDecimal(0.0);
    private BigDecimal curRateH = new BigDecimal(0.0);
    /** 分段往年积数I */
    private BigDecimal preIntegralSubI = new BigDecimal(0.00);
    private BigDecimal curIntegralSubI = new BigDecimal(0.00);
    private BigDecimal preRateI = new BigDecimal(0.0);
    private BigDecimal curRateI = new BigDecimal(0.0);
    /** 分段往年积数J */
    private BigDecimal preIntegralSubJ = new BigDecimal(0.00);
    private BigDecimal curIntegralSubJ = new BigDecimal(0.00);
    private BigDecimal preRateJ = new BigDecimal(0.0);
    private BigDecimal curRateJ = new BigDecimal(0.0);
    /** 分段往年积数K */
    private BigDecimal preIntegralSubK = new BigDecimal(0.00);
    private BigDecimal curIntegralSubK = new BigDecimal(0.00);
    private BigDecimal preRateK = new BigDecimal(0.0);
    private BigDecimal curRateK = new BigDecimal(0.0);
    /** 分段往年积数L */
    private BigDecimal preIntegralSubL = new BigDecimal(0.00);
    private BigDecimal curIntegralSubL = new BigDecimal(0.00);
    private BigDecimal preRateL = new BigDecimal(0.0);
    private BigDecimal curRateL = new BigDecimal(0.0);
    
    /** full constructor */
    public PickTail(Integer empId, BigDecimal pickType, BigDecimal pickPreBalance, BigDecimal pickCurBalance, BigDecimal pickPreInterest, BigDecimal pickCurInterest, BigDecimal pickReason,String reserveaA, String reserveaB, String reserveaC,PickHead pickHead,SpecialPick specialPick ) {
        this.empId = empId;
        this.pickType = pickType;
        this.pickPreBalance = pickPreBalance;
        this.pickCurBalance = pickCurBalance;
        this.pickPreInterest = pickPreInterest;
        this.pickCurInterest = pickCurInterest;
        this.pickReason = pickReason;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.pickHead = pickHead;
        this.specialPick = specialPick;
    }
    public PickTail(PickTail pickTail,Emp emp) throws Exception{
      BeanUtils.copyProperties(this, pickTail);
      this.emp=emp;
    }
    public PickTail() {
    }
    public BigDecimal getPickType() {
        return this.pickType;
    }
    public void setPickType(BigDecimal pickType) {
        this.pickType = pickType;
    }
    public BigDecimal getPickPreBalance() {
        return this.pickPreBalance;
    }
    public void setPickPreBalance(BigDecimal pickPreBalance) {
        this.pickPreBalance = pickPreBalance;
    }
    public BigDecimal getPickCurBalance() {
        return this.pickCurBalance;
    }
    public void setPickCurBalance(BigDecimal pickCurBalance) {
        this.pickCurBalance = pickCurBalance;
    }
    public BigDecimal getPickPreInterest() {
        return this.pickPreInterest;
    }
    public void setPickPreInterest(BigDecimal pickPreInterest) {
        this.pickPreInterest = pickPreInterest;
    }
    public BigDecimal getPickCurInterest() {
        return this.pickCurInterest;
    }
    public void setPickCurInterest(BigDecimal pickCurInterest) {
        this.pickCurInterest = pickCurInterest;
    }
    public BigDecimal getPickReason() {
        return this.pickReason;
    }
    public void setPickReason(BigDecimal pickReason) {
        this.pickReason = pickReason;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    public boolean equals(Object other) {
        if ( !(other instanceof PickTail) ) return false;
        PickTail castOther = (PickTail) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public Emp getEmp() {
      return emp;
    }

    public void setEmp(Emp emp) {
      this.emp = emp;
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

    public PickHead getPickHead() {
      return pickHead;
    }

    public void setPickHead(PickHead pickHead) {
      this.pickHead = pickHead;
    }

    public Integer getEmpId() {
      return empId;
    }

    public void setEmpId(Integer empId) {
      this.empId = empId;
    }
    public int getDistoryPick() {
      return distoryPick;
    }
    public void setDistoryPick(int distoryPick) {
      this.distoryPick = distoryPick;
    }
    public String getPickDisplayType() {
      return pickDisplayType;
    }
    public void setPickDisplayType(String pickDisplayType) {
      this.pickDisplayType = pickDisplayType;
    }
    public int getSomePick() {
      return somePick;
    }
    public void setSomePick(int somePick) {
      this.somePick = somePick;
    }
    public int getSumPerson() {
      return sumPerson;
    }
    public void setSumPerson(int sumPerson) {
      this.sumPerson = sumPerson;
    }
    public BigDecimal getPickInterest() {
      return pickInterest;
    }
    public void setPickInterest(BigDecimal pickInterest) {
      this.pickInterest = pickInterest;
    }
    public BigDecimal getPickSalary() {
      return pickSalary;
    }
    public void setPickSalary(BigDecimal pickSalary) {
      this.pickSalary = pickSalary;
    }
    public BigDecimal getSumBalance() {
      return sumBalance;
    }
    public void setSumBalance(BigDecimal sumBalance) {
      this.sumBalance = sumBalance;
    }
    public BigDecimal getSumInterest() {
      return sumInterest;
    }
    public void setSumInterest(BigDecimal sumInterest) {
      this.sumInterest = sumInterest;
    }
    public BigDecimal getTotal() {
      return total;
    }
    public void setTotal(BigDecimal total) {
      this.total = total;
    }
    public String getEmpName() {
      return empName;
    }
    public void setEmpName(String empName) {
      this.empName = empName;
    }
    public BigDecimal getSumTotal() {
      return sumTotal;
    }
    public void setSumTotal(BigDecimal sumTotal) {
      this.sumTotal = sumTotal;
    }
    public SpecialPick getSpecialPick() {
      return specialPick;
    }
    public void setSpecialPick(SpecialPick specialPick) {
      this.specialPick = specialPick;
    }
    public String getTime() {
      return time;
    }
    public void setTime(String time) {
      this.time = time;
    }
    public String getReason() {
      return reason;
    }
    public void setReason(String reason) {
      this.reason = reason;
    }
    public String getTemp_PickType() {
      return temp_PickType;
    }
    public void setTemp_PickType(String temp_PickType) {
      this.temp_PickType = temp_PickType;
    }
    public String getContractid() {
      return contractid;
    }
    public void setContractid(String contractid) {
      this.contractid = contractid;
    }
    public String getLoankouacc() {
      return loankouacc;
    }
    public void setLoankouacc(String loankouacc) {
      this.loankouacc = loankouacc;
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
    public BigDecimal getCurIntegral() {
      return curIntegral;
    }
    public void setCurIntegral(BigDecimal curIntegral) {
      this.curIntegral = curIntegral;
    }
    public BigDecimal getPreIntegral() {
      return preIntegral;
    }
    public void setPreIntegral(BigDecimal preIntegral) {
      this.preIntegral = preIntegral;
    }
    public String getPhotourl() {
      return photourl;
    }
    public void setPhotourl(String photourl) {
      this.photourl = photourl;
    }
}
