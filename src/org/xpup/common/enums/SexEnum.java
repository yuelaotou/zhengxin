package org.xpup.common.enums;

import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;

public final class SexEnum extends ValuedEnum {

  private static final long serialVersionUID = 7581330133294580393L;

  public static final SexEnum MALE = new SexEnum("ÄÐ", 1);

  public static final SexEnum FEMALE = new SexEnum("Å®", 2);

  protected SexEnum(String name, int value) {
    super(name, value);
  }

  public static List getEnumList() {
    return getEnumList(SexEnum.class);
  }

  public static SexEnum getEnum(int sex) {
    return (SexEnum) getEnum(SexEnum.class, sex);
  }
}
