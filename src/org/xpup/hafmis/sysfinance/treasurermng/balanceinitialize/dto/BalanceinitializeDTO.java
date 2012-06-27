package org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.dto;

import java.io.Serializable;

/**
 * Copy Right Information   : 余额初始
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-19-2007
 */
public class BalanceinitializeDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private String officeName = "";
  private String bookId = "";
  private String subjectCode = "";
  private String subjectName ="";
  private String debit = "0";
  
  public String getBookId() {
    return bookId;
  }
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  public String getDebit() {
    return debit;
  }
  public void setDebit(String debit) {
    this.debit = debit;
  }
  public String getOfficeName() {
    return officeName;
  }
  public void setOfficeName(String officeName) {
    this.officeName = officeName;
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
