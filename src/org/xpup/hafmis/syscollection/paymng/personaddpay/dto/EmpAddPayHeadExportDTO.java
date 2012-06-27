package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class EmpAddPayHeadExportDTO implements ExpDto{
  
    private String orgId=""; 

    private String orgName="";

    private String noteNum="";

    public String getInfo(String s) {
      if(s.equals("orgId"))
        return orgId;
      if(s.equals("orgName"))
          return orgName;
      if(s.equals("noteNum"))
        return noteNum;
      else
          return null;
    }

    public String getNoteNum() {
      return noteNum;
    }

    public void setNoteNum(String noteNum) {
      this.noteNum = noteNum;
    }

    public String getOrgId() {
      return orgId;
    }

    public void setOrgId(String orgId) {
      this.orgId = orgId;
    }

    public String getOrgName() {
      return orgName;
    }

    public void setOrgName(String orgName) {
      this.orgName = orgName;
    }   
  }


