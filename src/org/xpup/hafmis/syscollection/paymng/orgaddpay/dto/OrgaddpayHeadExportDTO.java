/*单位编号、单位名称、补缴起始年月、补缴终止年月、票据编号、职工编号、职工姓名、单位应缴金额、职工应缴金额、单位补缴金额、职工补缴金额（单补的时候另一个置0）*/
package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;


public class OrgaddpayHeadExportDTO implements ExpDto{

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

  public String getInfo(String s) {
    if(s.equals("orgid"))
      return orgid;
    if(s.equals("orgName"))
        return orgName;
    if(s.equals("addpayMonth"))
        return addpayMonth;
    if(s.equals("addStartPayMonth"))
      return addStartPayMonth;
    if(s.equals("noteNum"))
      return noteNum;
    else
        return null;
  }

  public String getAddStartPayMonth() {
    return addStartPayMonth;
  }

  public void setAddStartPayMonth(String addStartPayMonth) {
    this.addStartPayMonth = addStartPayMonth;
  }

  
}
