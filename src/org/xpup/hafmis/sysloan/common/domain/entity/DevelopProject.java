package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DevelopProject implements Serializable {

    /** identifier field */
    private Integer floorId;

    /** persistent field */
    private String headId;

    /** persistent field */
    private String floorName;

    /** persistent field */
    private String floorNum;

    /** nullable persistent field */
    private String region;

    /** nullable persistent field */
    private BigDecimal itemTotleAmnt=new BigDecimal(0.00);

    /** nullable persistent field */
    private String itemFinishDegree;

    /** nullable persistent field */
    private BigDecimal housePrice=new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal buildingArea=new BigDecimal(0.00);
    
    private String buildingAreas;

    /** persistent field */
    private String floorSt;
    
    private String floorAddr;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String fundStatus;
    
    /** full constructor */
    public DevelopProject(String fundStatus,Integer floorId, String headId, String floorName, String floorNum, String region, BigDecimal itemTotleAmnt, String itemFinishDegree, BigDecimal housePrice, BigDecimal buildingArea, String floorSt, String reserveaA, String reserveaB, String reserveaC) {
        this.fundStatus = fundStatus;
        this.floorId = floorId;
        this.headId = headId;
        this.floorName = floorName;
        this.floorNum = floorNum;
        this.region = region;
        this.itemTotleAmnt = itemTotleAmnt;
        this.itemFinishDegree = itemFinishDegree;
        this.housePrice = housePrice;
        this.buildingArea = buildingArea;
        this.floorSt = floorSt;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public DevelopProject() {
    }

    /** minimal constructor */
    public DevelopProject(String fundStatus,Integer floorId, String headId, String floorName, String floorNum, String floorSt) {
        this.floorId = floorId;
        this.headId = headId;
        this.floorName = floorName;
        this.floorNum = floorNum;
        this.floorSt = floorSt;
        this.fundStatus = fundStatus;
    }

    public Integer getFloorId() {
        return this.floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public String getHeadId() {
      return headId;
    }

    public void setHeadId(String headId) {
      this.headId = headId;
    }

    public String getFloorName() {
        return this.floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorNum() {
        return this.floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getItemTotleAmnt() {
        return this.itemTotleAmnt;
    }

    public void setItemTotleAmnt(BigDecimal itemTotleAmnt) {
        this.itemTotleAmnt = itemTotleAmnt;
    }

    public String getItemFinishDegree() {
        return this.itemFinishDegree;
    }

    public void setItemFinishDegree(String itemFinishDegree) {
        this.itemFinishDegree = itemFinishDegree;
    }

    public BigDecimal getHousePrice() {
        return this.housePrice;
    }

    public void setHousePrice(BigDecimal housePrice) {
        this.housePrice = housePrice;
    }

    public BigDecimal getBuildingArea() {
        return this.buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getFloorSt() {
        return this.floorSt;
    }

    public void setFloorSt(String floorSt) {
        this.floorSt = floorSt;
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
            .append("floorId", getFloorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DevelopProject) ) return false;
        DevelopProject castOther = (DevelopProject) other;
        return new EqualsBuilder()
            .append(this.getFloorId(), castOther.getFloorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFloorId())
            .toHashCode();
    }

    public String getFundStatus() {
      return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
      this.fundStatus = fundStatus;
    }

    public String getBuildingAreas() {
      return buildingAreas;
    }

    public void setBuildingAreas(String buildingAreas) {
      this.buildingAreas = buildingAreas;
    }

    public String getFloorAddr() {
      return floorAddr;
    }

    public void setFloorAddr(String floorAddr) {
      this.floorAddr = floorAddr;
    }

}
