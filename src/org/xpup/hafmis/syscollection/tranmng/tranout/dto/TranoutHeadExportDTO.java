/*转出单位编号、转出单位名称、转入单位编号、转入单位名称、票据编号、职工编号、职工姓名、身份正类型、身份证号码、是否结息 */

package org.xpup.hafmis.syscollection.tranmng.tranout.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;


public class TranoutHeadExportDTO implements ExpDto{

  private static final long serialVersionUID = 0L;

  private String orgOutid; 
  private String orgOutName;
  private String orgInid; 
  private String orgInName;
  private String noteNum;
  private String expalanation;
  



  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOrgInid() {
    return orgInid;
  }

  public void setOrgInid(String orgInid) {
    this.orgInid = orgInid;
  }

  public String getOrgInName() {
    return orgInName;
  }

  public void setOrgInName(String orgInName) {
    this.orgInName = orgInName;
  }

  public String getOrgOutid() {
    return orgOutid;
  }

  public void setOrgOutid(String orgOutid) {
    this.orgOutid = orgOutid;
  }

  public String getOrgOutName() {
    return orgOutName;
  }

  public void setOrgOutName(String orgOutName) {
    this.orgOutName = orgOutName;
  }





  public String getInfo(String s) {
    if(s.equals("orgOutid"))
      return orgOutid;
    if(s.equals("orgOutName"))
        return orgOutName;
    if(s.equals("orgInid"))
        return orgInid;
    if(s.equals("orgInName"))
      return orgInName;
    if(s.equals("noteNum"))
      return noteNum;
    if(s.equals("expalanation"))
      return expalanation;
    else
        return null;
  }

  public String getExpalanation() {
    return expalanation;
  }

  public void setExpalanation(String expalanation) {
    this.expalanation = expalanation;
  }

  
}
