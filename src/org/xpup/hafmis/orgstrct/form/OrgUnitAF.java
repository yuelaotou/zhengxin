package org.xpup.hafmis.orgstrct.form;

import org.xpup.hafmis.common.form.DomainObjectAF;
import org.xpup.hafmis.orgstrct.domain.OrgUnitProperty;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.domain.enums.OUTypeEnum;

public class OrgUnitAF extends DomainObjectAF {

  private static final long serialVersionUID = 7051195281801993118L;

  private OupShowHelper oupShowHelper = new OupShowHelper();

  private OrganizationUnit orgUnit = new OrganizationUnit();

  private String parentId = null;

  public OrganizationUnit getOrgUnit() {
    return orgUnit;
  }

  public void setOrgUnit(OrganizationUnit orgUnit) {
    this.orgUnit = orgUnit;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getAttributes() {
    return oupShowHelper.show(orgUnit.getAttributes(), "attr");
  }

  public String getParameters() {
    return oupShowHelper.show(orgUnit.getParameters(), "param");
  }

  public void setAttr(int index, String value) {
    OrgUnitProperty oup = (OrgUnitProperty) orgUnit.getAttributes().get(index);
    oup.setValue(value);
  }

  public void setParam(int index, String value) {
    OrgUnitProperty oup = (OrgUnitProperty) orgUnit.getParameters().get(index);
    oup.setValue(value);
  }

  public int getType() {
    return orgUnit.getType().getValue();
  }

  public void setType(int type) {
    orgUnit.setType(OUTypeEnum.getEnum(type));
  }
}
