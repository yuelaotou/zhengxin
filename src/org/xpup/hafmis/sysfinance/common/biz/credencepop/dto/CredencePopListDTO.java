package org.xpup.hafmis.sysfinance.common.biz.credencepop.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 封装了弹出框中摘要，自由摘要，借贷金额列表的信息的DTO Goldsoft Project :
 * CredencePopListDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.11.3
 */
public class CredencePopListDTO {

  /** 摘要 */
  private String summay = "";

  /** 自由摘要 */
  private String freeSummay = "";

  /** 科目代码 */
  private String subjectCode = "";

  /** 借方金额 */
  private BigDecimal debit = new BigDecimal(0.00);

  /** 贷方金额 */
  private BigDecimal credit = new BigDecimal(0.00);
  
  /** 科目名称 */
  private String subjectName = "";

  private String settnum="";
  
  public String getSettnum() {
    return settnum;
  }

  public void setSettnum(String settnum) {
    this.settnum = settnum;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public String getFreeSummay() {
    return freeSummay;
  }

  public void setFreeSummay(String freeSummay) {
    this.freeSummay = freeSummay;
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

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }
}
