package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class AdjustaccountDTO implements Serializable{

  private static final long serialVersionUID = 0L;
  private BigDecimal total=new BigDecimal(0.00);
  private List list;
  private List list1;
  private  Integer persontotal=new Integer(0);
  private String noteNum = "";

  public String getNoteNum() {
    return noteNum;
  }
  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public BigDecimal getTotal() {
    return total;
  }
  public void setTotal(BigDecimal total) {
    this.total = total;
  }
  public List getList1() {
    return list1;
  }
  public void setList1(List list1) {
    this.list1 = list1;
  }
  public Integer getPersontotal() {
    return persontotal;
  }
  public void setPersontotal(Integer persontotal) {
    this.persontotal = persontotal;
  }
  
}
