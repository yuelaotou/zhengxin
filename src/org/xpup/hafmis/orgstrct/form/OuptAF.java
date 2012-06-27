package org.xpup.hafmis.orgstrct.form;

import org.xpup.hafmis.common.form.DomainObjectAF;
import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;

public class OuptAF extends DomainObjectAF {

  private static final long serialVersionUID = 1780624394447130253L;

  private OrgUnitPropTemplate oupt = new OrgUnitPropTemplate();

  public OrgUnitPropTemplate getOupt() {
    return oupt;
  }

  public void setOupt(OrgUnitPropTemplate oupt) {
    this.oupt = oupt;
  }

}
