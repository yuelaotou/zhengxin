package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Houses implements Serializable {

    /** identifier field */
    private String contractId;

    /** nullable persistent field */
    private String headId;

    /** nullable persistent field */
    private String developerTel;

    /** nullable persistent field */
    private String developerPaybank;

    /** nullable persistent field */
    private String developerPaybankAAcc;

    /** nullable persistent field */
    private BigDecimal floorId;

    /** nullable persistent field */
    private String floorNum;

    /** nullable persistent field */
    private String roomNum;

    /** nullable persistent field */
    private BigDecimal firstPay;

    /** nullable persistent field */
    private BigDecimal houseTotlePrice;

    /** nullable persistent field */
    private String buyHouseContractId;

    /** nullable persistent field */
    private BigDecimal houseArea;

    /** nullable persistent field */
    private String isNowhouse;

    /** nullable persistent field */
    private String overDate;

    /** nullable persistent field */
    private String buildRightNum;

    /** nullable persistent field */
    private String agreementDate;

    /** nullable persistent field */
    private String houseAddr;

    /** nullable persistent field */
    private String bargainorName;

    /** nullable persistent field */
    private String bargainorCardKind;

    /** nullable persistent field */
    private String bargainorCardNum;

    /** nullable persistent field */
    private String bargainorTel;

    /** nullable persistent field */
    private String bargainorHousepropertyCode;

    /** nullable persistent field */
    private String bargainorMobile;

    /** nullable persistent field */
    private String bargainorHouseAddr;

    /** nullable persistent field */
    private String bargainorPaybank;

    /** nullable persistent field */
    private String bargainorPaybankAcc;

    /** nullable persistent field */
    private String bargainorHouseArea;

    /** nullable persistent field */
    private BigDecimal bargainorTotlePrice;

    /** nullable persistent field */
    private BigDecimal bargainorHouseAge;

    /** nullable persistent field */
    private String bargainorAgreementDate;

    /** persistent field */
    private String houseType;

    /** nullable persistent field */
    private String operator;

    /** nullable persistent field */
    private Date opTime;

    /** nullable persistent field */
    private String remark;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    private String photoUrl;
    private BigDecimal rebate;
    private String fundBank;
    private String fundBankAcc;
    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }
    /** full constructor */
    public Houses(String contractId, String headId, String developerTel, String developerPaybank, String developerPaybankAAcc, BigDecimal floorId, String floorNum, String roomNum, BigDecimal firstPay, BigDecimal houseTotlePrice, String buyHouseContractId, BigDecimal houseArea, String isNowhouse, String overDate, String buildRightNum, String agreementDate, String houseAddr, String bargainorName, String bargainorCardKind, String bargainorCardNum, String bargainorTel, String bargainorHousepropertyCode, String bargainorMobile, String bargainorHouseAddr, String bargainorPaybank, String bargainorPaybankAcc, String bargainorHouseArea, BigDecimal bargainorTotlePrice, BigDecimal bargainorHouseAge, String bargainorAgreementDate, String houseType, String operator, Date opTime, String remark, String reserveaA, String reserveaB, String reserveaC) {
        this.contractId = contractId;
        this.headId = headId;
        this.developerTel = developerTel;
        this.developerPaybank = developerPaybank;
        this.developerPaybankAAcc = developerPaybankAAcc;
        this.floorId = floorId;
        this.floorNum = floorNum;
        this.roomNum = roomNum;
        this.firstPay = firstPay;
        this.houseTotlePrice = houseTotlePrice;
        this.buyHouseContractId = buyHouseContractId;
        this.houseArea = houseArea;
        this.isNowhouse = isNowhouse;
        this.overDate = overDate;
        this.buildRightNum = buildRightNum;
        this.agreementDate = agreementDate;
        this.houseAddr = houseAddr;
        this.bargainorName = bargainorName;
        this.bargainorCardKind = bargainorCardKind;
        this.bargainorCardNum = bargainorCardNum;
        this.bargainorTel = bargainorTel;
        this.bargainorHousepropertyCode = bargainorHousepropertyCode;
        this.bargainorMobile = bargainorMobile;
        this.bargainorHouseAddr = bargainorHouseAddr;
        this.bargainorPaybank = bargainorPaybank;
        this.bargainorPaybankAcc = bargainorPaybankAcc;
        this.bargainorHouseArea = bargainorHouseArea;
        this.bargainorTotlePrice = bargainorTotlePrice;
        this.bargainorHouseAge = bargainorHouseAge;
        this.bargainorAgreementDate = bargainorAgreementDate;
        this.houseType = houseType;
        this.operator = operator;
        this.opTime = opTime;
        this.remark = remark;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public Houses() {
    }

    /** minimal constructor */
    public Houses(String contractId, String houseType,String operator, Date opTime ) {
        this.contractId = contractId;
        this.houseType = houseType;
        this.operator = operator;
        this.opTime = opTime;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getHeadId() {
      return headId;
    }

    public void setHeadId(String headId) {
      this.headId = headId;
    }

    public String getDeveloperTel() {
        return this.developerTel;
    }

    public void setDeveloperTel(String developerTel) {
        this.developerTel = developerTel;
    }

    public String getDeveloperPaybank() {
        return this.developerPaybank;
    }

    public void setDeveloperPaybank(String developerPaybank) {
        this.developerPaybank = developerPaybank;
    }

    public String getDeveloperPaybankAAcc() {
        return this.developerPaybankAAcc;
    }

    public void setDeveloperPaybankAAcc(String developerPaybankAAcc) {
        this.developerPaybankAAcc = developerPaybankAAcc;
    }

    public BigDecimal getFloorId() {
        return this.floorId;
    }

    public void setFloorId(BigDecimal floorId) {
        this.floorId = floorId;
    }

    public String getFloorNum() {
        return this.floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getRoomNum() {
        return this.roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public BigDecimal getFirstPay() {
        return this.firstPay;
    }

    public void setFirstPay(BigDecimal firstPay) {
        this.firstPay = firstPay;
    }

    public BigDecimal getHouseTotlePrice() {
        return this.houseTotlePrice;
    }

    public void setHouseTotlePrice(BigDecimal houseTotlePrice) {
        this.houseTotlePrice = houseTotlePrice;
    }

    public String getBuyHouseContractId() {
        return this.buyHouseContractId;
    }

    public void setBuyHouseContractId(String buyHouseContractId) {
        this.buyHouseContractId = buyHouseContractId;
    }

    public BigDecimal getHouseArea() {
        return this.houseArea;
    }

    public void setHouseArea(BigDecimal houseArea) {
        this.houseArea = houseArea;
    }

    public String getIsNowhouse() {
        return this.isNowhouse;
    }

    public void setIsNowhouse(String isNowhouse) {
        this.isNowhouse = isNowhouse;
    }

    public String getOverDate() {
        return this.overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }

    public String getBuildRightNum() {
        return this.buildRightNum;
    }

    public void setBuildRightNum(String buildRightNum) {
        this.buildRightNum = buildRightNum;
    }

    public String getAgreementDate() {
        return this.agreementDate;
    }

    public void setAgreementDate(String agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getHouseAddr() {
        return this.houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }

    public String getBargainorName() {
        return this.bargainorName;
    }

    public void setBargainorName(String bargainorName) {
        this.bargainorName = bargainorName;
    }

    public String getBargainorCardKind() {
        return this.bargainorCardKind;
    }

    public void setBargainorCardKind(String bargainorCardKind) {
        this.bargainorCardKind = bargainorCardKind;
    }

    public String getBargainorCardNum() {
        return this.bargainorCardNum;
    }

    public void setBargainorCardNum(String bargainorCardNum) {
        this.bargainorCardNum = bargainorCardNum;
    }

    public String getBargainorTel() {
        return this.bargainorTel;
    }

    public void setBargainorTel(String bargainorTel) {
        this.bargainorTel = bargainorTel;
    }

    public String getBargainorHousepropertyCode() {
        return this.bargainorHousepropertyCode;
    }

    public void setBargainorHousepropertyCode(String bargainorHousepropertyCode) {
        this.bargainorHousepropertyCode = bargainorHousepropertyCode;
    }

    public String getBargainorMobile() {
        return this.bargainorMobile;
    }

    public void setBargainorMobile(String bargainorMobile) {
        this.bargainorMobile = bargainorMobile;
    }

    public String getBargainorHouseAddr() {
        return this.bargainorHouseAddr;
    }

    public void setBargainorHouseAddr(String bargainorHouseAddr) {
        this.bargainorHouseAddr = bargainorHouseAddr;
    }

    public String getBargainorPaybank() {
        return this.bargainorPaybank;
    }

    public void setBargainorPaybank(String bargainorPaybank) {
        this.bargainorPaybank = bargainorPaybank;
    }

    public String getBargainorPaybankAcc() {
        return this.bargainorPaybankAcc;
    }

    public void setBargainorPaybankAcc(String bargainorPaybankAcc) {
        this.bargainorPaybankAcc = bargainorPaybankAcc;
    }

    public String getBargainorHouseArea() {
        return this.bargainorHouseArea;
    }

    public void setBargainorHouseArea(String bargainorHouseArea) {
        this.bargainorHouseArea = bargainorHouseArea;
    }

    public BigDecimal getBargainorTotlePrice() {
        return this.bargainorTotlePrice;
    }

    public void setBargainorTotlePrice(BigDecimal bargainorTotlePrice) {
        this.bargainorTotlePrice = bargainorTotlePrice;
    }

    public BigDecimal getBargainorHouseAge() {
        return this.bargainorHouseAge;
    }

    public void setBargainorHouseAge(BigDecimal bargainorHouseAge) {
        this.bargainorHouseAge = bargainorHouseAge;
    }

    public String getBargainorAgreementDate() {
        return this.bargainorAgreementDate;
    }

    public void setBargainorAgreementDate(String bargainorAgreementDate) {
        this.bargainorAgreementDate = bargainorAgreementDate;
    }

    public String getHouseType() {
        return this.houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
            .append("contractId", getContractId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Houses) ) return false;
        Houses castOther = (Houses) other;
        return new EqualsBuilder()
            .append(this.getContractId(), castOther.getContractId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getContractId())
            .toHashCode();
    }

    public BigDecimal getRebate() {
      return rebate;
    }

    public void setRebate(BigDecimal rebate) {
      this.rebate = rebate;
    }

    public String getFundBank() {
      return fundBank;
    }

    public void setFundBank(String fundBank) {
      this.fundBank = fundBank;
    }

    public String getFundBankAcc() {
      return fundBankAcc;
    }

    public void setFundBankAcc(String fundBankAcc) {
      this.fundBankAcc = fundBankAcc;
    }

}
