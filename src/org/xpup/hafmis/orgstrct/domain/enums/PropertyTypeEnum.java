package org.xpup.hafmis.orgstrct.domain.enums;

import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;

public class PropertyTypeEnum extends ValuedEnum {

  private static final long serialVersionUID = 7534372788687751067L;

  public static final PropertyTypeEnum ATTRIBUTE = new PropertyTypeEnum("自然属性",
      1);

  public static final PropertyTypeEnum PARAMETER = new PropertyTypeEnum("系统参数",
      2);

  protected PropertyTypeEnum(String name, int value) {
    super(name, value);
  }

  public static List getEnumList() {
    return getEnumList(PropertyTypeEnum.class);
  }

  public static PropertyTypeEnum getEnum(int type) {
    return (PropertyTypeEnum) getEnum(PropertyTypeEnum.class, type);
  }
}
