package org.xpup.hafmis.syscollection.tranmng.tranout.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;


public class TranoutListExportDTO implements ExpDto {

  private String empId;
  private String empName ;
  private String card_king ;
  private String card_num;
  private String Issettinrest ;
  private String tranin_empid ;
  private String tranReason;
  
  public String getTranReason() {
    return tranReason;
  }

  public void setTranReason(String tranReason) {
    this.tranReason = tranReason;
  }

  public String getCard_king() {
    return card_king;
  }

  public void setCard_king(String card_king) {
    this.card_king = card_king;
  }

  public String getCard_num() {
    return card_num;
  }

  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getIssettinrest() {
    return Issettinrest;
  }

  public void setIssettinrest(String issettinrest) {
    Issettinrest = issettinrest;
  }
  
  public String getInfo(String s) {
    if(s.equals("empId"))
      return empId;
    
    if(s.equals("empName"))
      return empName;
    
    if(s.equals("card_king"))
      return card_king;
    
    if(s.equals("empId"))
      return empId;
    
    if(s.equals("card_num"))
      return card_num;
    
    if(s.equals("Issettinrest"))
      return Issettinrest;
    
    if(s.equals("tranin_empid"))
      return tranin_empid;
    if(s.equals("tranReason"))
      return tranReason;
    
    return null;
  }

  public String getTranin_empid() {
    return tranin_empid;
  }

  public void setTranin_empid(String tranin_empid) {
    this.tranin_empid = tranin_empid;
  }
  
  
  
  

}
