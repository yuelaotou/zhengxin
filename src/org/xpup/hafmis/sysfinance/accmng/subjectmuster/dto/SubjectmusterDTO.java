package org.xpup.hafmis.sysfinance.accmng.subjectmuster.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copy Right Information : 凭证汇总
 * Project : 文件名
 * @Version : 1.0
 * @author : 张列 生成日期 : 11-06-2007
 */
public class SubjectmusterDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String subjectCode = "";
  private String subjectName = "";
  private BigDecimal debitSum = new BigDecimal("0.00");
  private BigDecimal creditSum = new BigDecimal("0.00");
  
  public BigDecimal getCreditSum() {
    return creditSum;
  }
  public void setCreditSum(BigDecimal creditSum) {
    this.creditSum = creditSum;
  }
  public BigDecimal getDebitSum() {
    return debitSum;
  }
  public void setDebitSum(BigDecimal debitSum) {
    this.debitSum = debitSum;
  }
  public String getSubjectCode() {
    return subjectCode;
  }
  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }
  public String getSubjectName() {
    return subjectName;
  }
  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }
  
  
}
