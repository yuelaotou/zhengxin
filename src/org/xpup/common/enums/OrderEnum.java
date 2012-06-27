package org.xpup.common.enums;

import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;

public class OrderEnum extends ValuedEnum {

  private static final long serialVersionUID = -3724166687821138903L;

  public static final OrderEnum ASC = new OrderEnum("ASC", 1);

  public static final OrderEnum DESC = new OrderEnum("DESC", 2);

  protected OrderEnum(String name, int value) {
    super(name, value);
  }

  public static List getEnumList() {
    return getEnumList(OrderEnum.class);
  }

  public static OrderEnum getEnum(int sex) {
    return (OrderEnum) getEnum(OrderEnum.class, sex);
  }
}
