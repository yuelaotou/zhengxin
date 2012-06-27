package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 增值收益与分配
 * 
 *@author 郭婧平
 *2007-12-07
 */
public class IncrementInAndOut implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 科目代码
   */
  private String subjectCode="";
  /**
   * 账套
   */
  private String bookId="";
  /**
   * 代码
   */
  private String code="";
  /**
   * 结果
   */
  private BigDecimal result=new BigDecimal(0.00);
  /** full constructor */
  public IncrementInAndOut(String subjectCode, String bookId, String code, BigDecimal result) {
      this.subjectCode=subjectCode;
      this.bookId=bookId;
      this.code=code;
      this.result=result;
  }

  /** default constructor */
  public IncrementInAndOut() {
  }
  public String getBookId() {
    return bookId;
  }
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public BigDecimal getResult() {
    return result;
  }
  public void setResult(BigDecimal result) {
    this.result = result;
  }
  public String getSubjectCode() {
    return subjectCode;
  }
  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }
}
