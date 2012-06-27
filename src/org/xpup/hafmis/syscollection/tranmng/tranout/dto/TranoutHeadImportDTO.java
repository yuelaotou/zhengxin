/*转出单位编号、转出单位名称、转入单位编号、转入单位名称、票据编号、职工编号、职工姓名、身份正类型、身份证号码、是否结息 */

package org.xpup.hafmis.syscollection.tranmng.tranout.dto;


import org.xpup.common.util.imp.domn.interfaces.impDto;


public class TranoutHeadImportDTO extends impDto{

  private static final long serialVersionUID = 0L;

  private String orgOutid; 
  private String orgOutName;
  private String orgInid; 
  private String orgInName;
  private String noteNum;
  private String orgheadid;
  private String expalanation;



  public String getOrgheadid() {
    return orgheadid;
  }

  public void setOrgheadid(String orgheadid) {
    this.orgheadid = orgheadid;
  }

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

  public String getExpalanation() {
    return expalanation;
  }

  public void setExpalanation(String expalanation) {
    this.expalanation = expalanation;
  }



  
}
