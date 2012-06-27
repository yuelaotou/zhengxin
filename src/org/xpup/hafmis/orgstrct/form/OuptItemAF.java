package org.xpup.hafmis.orgstrct.form;

import java.io.Serializable;

import org.xpup.hafmis.common.form.DomainObjectAF;
import org.xpup.hafmis.orgstrct.domain.OuptItem;
import org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum;
import org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum;

public class OuptItemAF extends DomainObjectAF {

  private static final long serialVersionUID = 418905358075942003L;

  private OuptItem ouptItem = new OuptItem();

  private Serializable ouptId = null;

  public Serializable getOuptId() {
    return ouptId;
  }

  public void setOuptId(Serializable ouptId) {
    this.ouptId = ouptId;
  }

  public OuptItem getOuptItem() {
    return ouptItem;
  }

  public void setOuptItem(OuptItem ouptItem) {
    this.ouptItem = ouptItem;
  }

  public int getType() {
    if (ouptItem.getType() == null)
      return PropertyTypeEnum.ATTRIBUTE.getValue();
    return ouptItem.getType().getValue();
  }

  public void setType(int type) {
    ouptItem.setType(PropertyTypeEnum.getEnum(type));
  }

  public int getValueType() {
    if (ouptItem.getValueType() == null)
      return ValueTypeEnum.STRING.getValue();
    return ouptItem.getValueType().getValue();
  }

  public void setValueType(int valueType) {
    ouptItem.setValueType(ValueTypeEnum.getEnum(valueType));
  }

}
