package org.xpup.hafmis.syscollection.accountmng.accountopen.form;

import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.common.form.IdAF;

  public class EmpChangeAF extends IdAF {
    /**
     * 
     */
    private static final long serialVersionUID = 3012429514542047289L;
    
    private String orgId="";
    
    private String oldEmpId="";
    
    private List list=new ArrayList();
  
    public List getList() {
      return list;
    }
  
    public void setList(List list) {
      this.list = list;
    }

    public String getOrgId() {
      return orgId;
    }

    public void setOrgId(String orgId) {
      this.orgId = orgId;
    }

    public String getOldEmpId() {
      return oldEmpId;
    }

    public void setOldEmpId(String oldEmpId) {
      this.oldEmpId = oldEmpId;
    }
}
