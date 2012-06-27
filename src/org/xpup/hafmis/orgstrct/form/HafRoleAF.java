package org.xpup.hafmis.orgstrct.form;

import org.xpup.hafmis.common.form.DomainObjectAF;
import org.xpup.hafmis.orgstrct.domain.HafRole;

public class HafRoleAF extends DomainObjectAF {

  private static final long serialVersionUID = 5338053892527901887L;

  private HafRole hafRole = new HafRole();

  private String orgUnitId = null;

  public String getOrgUnitId() {
    return orgUnitId;
  }

  public void setOrgUnitId(String orgUnitId) {
    this.orgUnitId = orgUnitId;
  }

  public HafRole getHafRole() {
    return hafRole;
  }

  public void setHafRole(HafRole hafRole) {
    this.hafRole = hafRole;
  }

}
