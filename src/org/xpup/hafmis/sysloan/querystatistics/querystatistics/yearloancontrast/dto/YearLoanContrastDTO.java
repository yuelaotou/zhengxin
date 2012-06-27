package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.dto;

import java.math.BigDecimal;

public class YearLoanContrastDTO {
   
  private String month = "";//月份
  private String thisyearmonth = "";
  private String lastyearmonth = "";
  
  private int b_ffhs = 0;//本年发放户数
  private BigDecimal b_ffje = new BigDecimal(0.00);//本年发放金额
  private BigDecimal b_jjmj = new BigDecimal(0.00);//本年建筑面积
  private BigDecimal b_ffzj = new BigDecimal(0.00);//本年房屋总价
  
  private int w_ffhs = 0;//往年发放户数
  private BigDecimal w_ffje = new BigDecimal(0.00);//往年发放金额
  private BigDecimal w_jjmj = new BigDecimal(0.00);//往年建筑面积
  private BigDecimal w_ffzj = new BigDecimal(0.00);//往年房屋总价
  
  private String t_ffhs = "";//同比增长发放户数
  private String t_ffje = "";//同比增长发放金额
  private String t_jjmj = "";//同比增长建筑面积
  private String t_ffzj = "";//同比增长房屋总价
  
  public int getB_ffhs() {
    return b_ffhs;
  }
  public void setB_ffhs(int b_ffhs) {
    this.b_ffhs = b_ffhs;
  }
  public BigDecimal getB_ffje() {
    return b_ffje;
  }
  public void setB_ffje(BigDecimal b_ffje) {
    this.b_ffje = b_ffje;
  }
  public BigDecimal getB_ffzj() {
    return b_ffzj;
  }
  public void setB_ffzj(BigDecimal b_ffzj) {
    this.b_ffzj = b_ffzj;
  }
  public BigDecimal getB_jjmj() {
    return b_jjmj;
  }
  public void setB_jjmj(BigDecimal b_jjmj) {
    this.b_jjmj = b_jjmj;
  }
  public String getMonth() {
    return month;
  }
  public void setMonth(String month) {
    this.month = month;
  }
  public String getT_ffhs() {
    return t_ffhs;
  }
  public void setT_ffhs(String t_ffhs) {
    this.t_ffhs = t_ffhs;
  }
  public String getT_ffje() {
    return t_ffje;
  }
  public void setT_ffje(String t_ffje) {
    this.t_ffje = t_ffje;
  }
  public String getT_ffzj() {
    return t_ffzj;
  }
  public void setT_ffzj(String t_ffzj) {
    this.t_ffzj = t_ffzj;
  }
  public String getT_jjmj() {
    return t_jjmj;
  }
  public void setT_jjmj(String t_jjmj) {
    this.t_jjmj = t_jjmj;
  }
  public int getW_ffhs() {
    return w_ffhs;
  }
  public void setW_ffhs(int w_ffhs) {
    this.w_ffhs = w_ffhs;
  }
  public BigDecimal getW_ffje() {
    return w_ffje;
  }
  public void setW_ffje(BigDecimal w_ffje) {
    this.w_ffje = w_ffje;
  }
  public BigDecimal getW_ffzj() {
    return w_ffzj;
  }
  public void setW_ffzj(BigDecimal w_ffzj) {
    this.w_ffzj = w_ffzj;
  }
  public BigDecimal getW_jjmj() {
    return w_jjmj;
  }
  public void setW_jjmj(BigDecimal w_jjmj) {
    this.w_jjmj = w_jjmj;
  }
  public String getLastyearmonth() {
    return lastyearmonth;
  }
  public void setLastyearmonth(String lastyearmonth) {
    this.lastyearmonth = lastyearmonth;
  }
  public String getThisyearmonth() {
    return thisyearmonth;
  }
  public void setThisyearmonth(String thisyearmonth) {
    this.thisyearmonth = thisyearmonth;
  }
  
}
