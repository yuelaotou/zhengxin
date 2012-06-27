package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto;

import java.io.Serializable;
/**
 * 和银行存款日记账公用
 * @author guojingping
 *
 */
public class BookParameterDTO implements Serializable{
  
  private static final long serialVersionUID = 5743046093074567834L;

  private String bookParameterId="";
  private String bookParameterName="";
  public String getBookParameterId() {
    return bookParameterId;
  }
  public void setBookParameterId(String bookParameterId) {
    this.bookParameterId = bookParameterId;
  }
  public String getBookParameterName() {
    return bookParameterName;
  }
  public void setBookParameterName(String bookParameterName) {
    this.bookParameterName = bookParameterName;
  }
  
  
  
}
