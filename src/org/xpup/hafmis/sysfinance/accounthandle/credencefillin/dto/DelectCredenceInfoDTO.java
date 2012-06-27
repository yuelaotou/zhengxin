package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

/**
 * Copy Right Information : 封装了删除信息的DTO Goldsoft Project : DelectCredenceInfoDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.11.1
 */
public class DelectCredenceInfoDTO {

  /** FN201主键 */
  private String credenceId = "";

  /** 凭证状态 */
  private String credenceSt = "";

  /** 摘要 */
  private String summay = "";

  /** 结算号 */
  private String settNum = "";

  /** 办事处 */
  private String office = "";

  /** 科目代码 */
  private String subjectCode = "";

  /** 凭证号 */
  private String docNum = "";

  /** 凭证日期 */
  private String credenceDate = "";

  /** FN202中的业务类型 */
  private String bizType = "";
  
  /** 备选字段B */
  private String reserveB = "";
  
  /** 备选字段C */
  private String reserveC = "";

  public String getReserveC() {
    return reserveC;
  }

  public void setReserveC(String reserveC) {
    this.reserveC = reserveC;
  }

  public String getCredenceId() {
    return credenceId;
  }

  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSummay() {
    return summay;
  }

  public void setSummay(String summay) {
    this.summay = summay;
  }

  public String getCredenceDate() {
    return credenceDate;
  }

  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getReserveB() {
    return reserveB;
  }

  public void setReserveB(String reserveB) {
    this.reserveB = reserveB;
  }
}
