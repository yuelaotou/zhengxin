/*单位编号、单位名称、补缴年月、票据编号、职工编号、职工姓名、单位应缴金额、职工应缴金额、单位补缴金额、职工补缴金额（单补的时候另一个置0）*/
package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;


import org.xpup.common.util.imp.domn.interfaces.impDto;


public class OrgaddpayHeadImportDTO extends impDto{

  private static final long serialVersionUID = 0L;

  private String orgid; 

  private String orgName;

  private String addpayMonth;
  
  private String addStartPayMonth;

  private String noteNum;

  public String getAddpayMonth() {
    return addpayMonth;
  }

  public void setAddpayMonth(String addpayMonth) {
    this.addpayMonth = addpayMonth;
  }


  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }


  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getAddStartPayMonth() {
    return addStartPayMonth;
  }

  public void setAddStartPayMonth(String addStartPayMonth) {
    this.addStartPayMonth = addStartPayMonth;
  }

  
}
