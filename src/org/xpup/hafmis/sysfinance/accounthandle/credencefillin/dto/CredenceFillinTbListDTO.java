package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 封装了自动挂帐列表内容的DTO
 * Goldsoft Project : CredenceFillinTbListDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.17
 */
public class CredenceFillinTbListDTO {

  /** 显示在页面的业务类型 */
  private String bizType = "";
  
  /** 被传回的业务类型num */
  private String numBizType = "";

  /** 结算日期 */
  private String settDate = "";

  /** 结算号 */
  private String settNum = "";

  /** 发放总金额 */
  private BigDecimal sumOccurMoney = new BigDecimal(0.00);

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public BigDecimal getSumOccurMoney() {
    return sumOccurMoney;
  }

  public void setSumOccurMoney(BigDecimal sumOccurMoney) {
    this.sumOccurMoney = sumOccurMoney;
  }

  public String getNumBizType() {
    return numBizType;
  }

  public void setNumBizType(String numBizType) {
    this.numBizType = numBizType;
  }
}
