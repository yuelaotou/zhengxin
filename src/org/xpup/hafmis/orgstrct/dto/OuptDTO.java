package org.xpup.hafmis.orgstrct.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;

public class OuptDTO implements Serializable {

  private static final long serialVersionUID = -1971085793012543026L;

  private List ouptItems = new ArrayList();

  private OrgUnitPropTemplate oupt = new OrgUnitPropTemplate();

  public OrgUnitPropTemplate getOupt() {
    return oupt;
  }

  public void setOupt(OrgUnitPropTemplate oupt) {
    this.oupt = oupt;
  }

  public List getOuptItems() {
    return ouptItems;
  }

  public void setOuptItems(List ouptItems) {
    this.ouptItems = ouptItems;
  }
}
