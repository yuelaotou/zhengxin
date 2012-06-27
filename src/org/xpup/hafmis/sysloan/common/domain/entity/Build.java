package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/** @author Hibernate CodeGenerator */
/**
 * @author beijilangyu
 *
 */
public class Build implements Serializable {

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    /** identifier field */

    /** persistent field */
    private Integer buildId;

    /** persistent field */
    private BigDecimal floorId;

    /** nullable persistent field */
    private String buildAdd;

    /** nullable persistent field */
    private String buildNum;

    /** persistent field */
    private BigDecimal build_s;

    /** nullable persistent field */
    private String fundStatus;
    
    /** nullable persistent field */
    private String reserved;
    
    private String build_status;
    
    public String getBuild_status() {
      return build_status;
    }

    public void setBuild_status(String build_status) {
      this.build_status = build_status;
    }

    public BigDecimal getBuild_s() {
      return build_s;
    }

    public void setBuild_s(BigDecimal build_s) {
      this.build_s = build_s;
    }

    public String getBuildAdd() {
      return buildAdd;
    }

    public void setBuildAdd(String buildAdd) {
      this.buildAdd = buildAdd;
    }

    public Integer getBuildId() {
      return buildId;
    }

    public void setBuildId(Integer buildId) {
      this.buildId = buildId;
    }

    public String getBuildNum() {
      return buildNum;
    }

    public void setBuildNum(String buildNum) {
      this.buildNum = buildNum;
    }

    public BigDecimal getFloorId() {
      return floorId;
    }

    public void setFloorId(BigDecimal floorId) {
      this.floorId = floorId;
    }

    public String getFundStatus() {
      return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
      this.fundStatus = fundStatus;
    }

    public String getReserved() {
      return reserved;
    }

    public void setReserved(String reserved) {
      this.reserved = reserved;
    }

}
