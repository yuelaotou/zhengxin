package org.xpup.hafmis.orgstrct.domain;

import org.xpup.common.domain.DomainObject;
import org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum;
import org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum;

/**
 * 模板项
 * 
 * @author 王英辉
 */
public class OuptItem extends DomainObject {

  private static final long serialVersionUID = -1045173428291767732L;

  /**
   * 所属模板
   */
  private OrgUnitPropTemplate orgUnitPropTemplate = new OrgUnitPropTemplate();

  /**
   * 属性或参数的名称
   */
  private String name = null;

  /**
   * 英文名称
   */
  private String innerName = null;

  /**
   * 属性或参数的取值
   */
  private String value = null;

  /**
   * 属性或参数的取值类型
   */
  private Integer valueType = null;

  /**
   * 枚举序列
   */
  private String enumValue = null;

  /**
   * 类型，即是属性还是参数
   */
  private Integer type = null;

  /**
   * 是否可以为空
   */
  private boolean nullable = true;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PropertyTypeEnum getType() {
    if (type == null) {
      return null;
    }
    return PropertyTypeEnum.getEnum(type.intValue());
  }

  public void setType(PropertyTypeEnum type) {
    if (type == null) {
      this.type = null;
    } else {
      this.type = new Integer(type.getValue());
    }
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public ValueTypeEnum getValueType() {
    if (valueType == null) {
      return null;
    }
    return ValueTypeEnum.getEnum(valueType.intValue());
  }

  public void setValueType(ValueTypeEnum valueType) {
    if (valueType == null) {
      this.valueType = null;
    } else {
      this.valueType = new Integer(valueType.getValue());
    }
  }

  public OrgUnitPropTemplate getOrgUnitPropTemplate() {
    return orgUnitPropTemplate;
  }

  public void setOrgUnitPropTemplate(OrgUnitPropTemplate orgUnitPropTemplate) {
    this.orgUnitPropTemplate = orgUnitPropTemplate;
  }

  public boolean isNullable() {
    return nullable;
  }

  public void setNullable(boolean nullable) {
    this.nullable = nullable;
  }

  public String getInnerName() {
    return innerName;
  }

  public void setInnerName(String innerName) {
    this.innerName = innerName;
  }

  public String getEnumValue() {
    return enumValue;
  }

  public void setEnumValue(String enumValue) {
    this.enumValue = enumValue;
  }

  public OrgUnitProperty createOrgUnitProperty() {
    OrgUnitProperty orgUnitProperty = new OrgUnitProperty();
    orgUnitProperty.setValue(this.value);
    orgUnitProperty.setOuptItem(this);
    orgUnitProperty.setSaved(false);
    return orgUnitProperty;
  }

}
