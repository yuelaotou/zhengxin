package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto;

import java.math.BigDecimal;

public class YearclearstatisticsHeadDTO {

  private String orgcode = "";// 单位编号

  private String orgname = "";// 单位名称

  private Integer orgcounts = new Integer(0);// 单位数

  private Integer empcounts = new Integer(0);// 人数

  private BigDecimal oldpreblance = new BigDecimal(0.00);// 结息前往年余额

  private BigDecimal oldcurblance = new BigDecimal(0.00);// 结息前本年余额

  private BigDecimal preinterest = new BigDecimal(0.00);// 往年利息

  private BigDecimal curinterest = new BigDecimal(0.00);// 本年利息

  private BigDecimal preblance = new BigDecimal(0.00);// 往年余额

  private BigDecimal curblance = new BigDecimal(0.00);// 本年余额

  private BigDecimal blance = new BigDecimal(0.00);// 余额

  private BigDecimal guazhang = new BigDecimal(0.00);// 余额

  private String set_headid = "";// 结息头表ID

  private String empcode = "";// 职工编号

  private String empname = "";// 职工名称

  private String aa318id = "";

  private BigDecimal preIntegral = new BigDecimal(0.00);// 往年积数

  private BigDecimal curIntegral = new BigDecimal(0.00);// 本年积数

  private BigDecimal preRate = new BigDecimal(0.00);// 往年利率

  private BigDecimal curRate = new BigDecimal(0.00);// 本年利率

  private String types = "";// 结息类型

  private String bank = "";

  public String getAa318id() {
    return aa318id;
  }

  public void setAa318id(String aa318id) {
    this.aa318id = aa318id;
  }

  public BigDecimal getBlance() {
    return blance;
  }

  public void setBlance(BigDecimal blance) {
    this.blance = blance;
  }

  public BigDecimal getCurblance() {
    return curblance;
  }

  public void setCurblance(BigDecimal curblance) {
    this.curblance = curblance;
  }

  public BigDecimal getCurinterest() {
    return curinterest;
  }

  public void setCurinterest(BigDecimal curinterest) {
    this.curinterest = curinterest;
  }

  public Integer getEmpcounts() {
    return empcounts;
  }

  public void setEmpcounts(Integer empcounts) {
    this.empcounts = empcounts;
  }

  public BigDecimal getOldcurblance() {
    return oldcurblance;
  }

  public void setOldcurblance(BigDecimal oldcurblance) {
    this.oldcurblance = oldcurblance;
  }

  public BigDecimal getOldpreblance() {
    return oldpreblance;
  }

  public void setOldpreblance(BigDecimal oldpreblance) {
    this.oldpreblance = oldpreblance;
  }

  public Integer getOrgcounts() {
    return orgcounts;
  }

  public void setOrgcounts(Integer orgcounts) {
    this.orgcounts = orgcounts;
  }

  public BigDecimal getPreblance() {
    return preblance;
  }

  public void setPreblance(BigDecimal preblance) {
    this.preblance = preblance;
  }

  public BigDecimal getPreinterest() {
    return preinterest;
  }

  public void setPreinterest(BigDecimal preinterest) {
    this.preinterest = preinterest;
  }

  public Integer getOrgcode() {
    return new Integer(orgcode);
  }

  public void setOrgcode(String orgcode) {
    this.orgcode = orgcode;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public String getSet_headid() {
    return set_headid;
  }

  public void setSet_headid(String set_headid) {
    this.set_headid = set_headid;
  }

  public Integer getEmpcode() {
    return new Integer(empcode);
  }

  public void setEmpcode(String empcode) {
    this.empcode = empcode;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public BigDecimal getCurIntegral() {
    return curIntegral;
  }

  public void setCurIntegral(BigDecimal curIntegral) {
    this.curIntegral = curIntegral;
  }

  public BigDecimal getCurRate() {
    return curRate;
  }

  public void setCurRate(BigDecimal curRate) {
    this.curRate = curRate;
  }

  public BigDecimal getPreIntegral() {
    return preIntegral;
  }

  public void setPreIntegral(BigDecimal preIntegral) {
    this.preIntegral = preIntegral;
  }

  public BigDecimal getPreRate() {
    return preRate;
  }

  public void setPreRate(BigDecimal preRate) {
    this.preRate = preRate;
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public BigDecimal getGuazhang() {
    return guazhang;
  }

  public void setGuazhang(BigDecimal guazhang) {
    this.guazhang = guazhang;
  }

}
