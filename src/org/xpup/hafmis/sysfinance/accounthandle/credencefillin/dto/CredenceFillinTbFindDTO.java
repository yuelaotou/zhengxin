package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

/**
 * Copy Right Information : 封装了自动转帐查询条件的DTO 
 * Goldsoft Project : CredenceFillinTbFindDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.17
 */
public class CredenceFillinTbFindDTO {

  /** 结算日期Start */
  private String settDateStart = "";

  /** 结算日期End */
  private String settDateEnd = "";

  /** 结算号 */
  private String settNum = "";

  /** 业务状态 */
  private String bizSt = "";

  /** 业务类型 */
  private String bizType = "";
  
  /** 银行 */
  private String bankId = "";

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getSettDateEnd() {
    return settDateEnd;
  }

  public void setSettDateEnd(String settDateEnd) {
    this.settDateEnd = settDateEnd;
  }

  public String getSettDateStart() {
    return settDateStart;
  }

  public void setSettDateStart(String settDateStart) {
    this.settDateStart = settDateStart;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

}
