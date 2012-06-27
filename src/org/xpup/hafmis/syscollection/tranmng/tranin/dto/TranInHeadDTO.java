package org.xpup.hafmis.syscollection.tranmng.tranin.dto;

import java.math.BigDecimal;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;

public class TranInHeadDTO {
  private TranInOrg tranInOrg = new TranInOrg();

  /** nullable persistent field */
  private TranOutOrg tranOutOrg = new TranOutOrg();;

  /** nullable persistent field */
  private String noteNum;

  /** nullable persistent field */
  private String docNum;

  /** nullable persistent field */
  private String settDate;

  /** nullable persistent field */
  private BigDecimal tranOutHeadId;

  /** persistent field */
  private BigDecimal tranStatus;

  /** ±¸Ñ¡a */
  private String reserveaA;

  private String reserveaB;

  private String reserveaC;

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

  /**
   * full constructor
   * 
   * @return
   */
  public void TranInHead(TranInOrg tranInOrg, TranOutOrg tranOutOrg,
      String noteNum, String docNum, String settDate, BigDecimal tranOutHeadId,
      BigDecimal tranStatus, String reserveaA, String reserveaB,
      String reserveaC) {
    this.tranInOrg = tranInOrg;
    this.tranOutOrg = tranOutOrg;
    this.noteNum = noteNum;
    this.docNum = docNum;
    this.settDate = settDate;
    this.tranOutHeadId = tranOutHeadId;
    this.tranStatus = tranStatus;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
  }

  /**
   * default constructor
   * 
   * @return
   */
  public void TranInHead() {
  }

  /**
   * minimal constructor
   * 
   * @return
   */
  public void TranInHead(TranInOrg tranInOrg, TranOutOrg tranOutOrg,
      BigDecimal tranStatus) {
    this.tranInOrg = tranInOrg;
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

  public BigDecimal getTranOutHeadId() {
    return this.tranOutHeadId;
  }

  public void setTranOutHeadId(BigDecimal tranOutHeadId) {
    this.tranOutHeadId = tranOutHeadId;
  }

  public BigDecimal getTranStatus() {
    return this.tranStatus;
  }

  public void setTranStatus(BigDecimal tranStatus) {
    this.tranStatus = tranStatus;
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
}
