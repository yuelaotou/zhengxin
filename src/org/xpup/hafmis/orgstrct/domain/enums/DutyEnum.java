package org.xpup.hafmis.orgstrct.domain.enums;

import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;

public class DutyEnum extends ValuedEnum {

	private static final long serialVersionUID = -6106108826961024623L;

	public static final DutyEnum 职员 = new DutyEnum("职员", 1);

	public static final DutyEnum 副科长 = new DutyEnum("副科长", 2);

	public static final DutyEnum 科长 = new DutyEnum("科长", 3);

	public static final DutyEnum 副处长 = new DutyEnum("副处长", 4);

	public static final DutyEnum 处长 = new DutyEnum("处长", 5);

	protected DutyEnum(String name, int value) {
		super(name, value);
	}

	public static List getEnumList() {
		return getEnumList(DutyEnum.class);
	}

	public static DutyEnum getEnum(int duty) {
		return (DutyEnum) getEnum(DutyEnum.class, duty);
	}
}
