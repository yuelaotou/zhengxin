package org.xpup.hafmis.orgstrct.domain.enums;

import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;

public class OUTypeEnum extends ValuedEnum {

	private static final long serialVersionUID = -6106108826961024623L;

	public static final OUTypeEnum 中心 = new OUTypeEnum("中心", 1);

	public static final OUTypeEnum 办事处 = new OUTypeEnum("办事处", 2);

	public static final OUTypeEnum 部门 = new OUTypeEnum("部门", 3);

	protected OUTypeEnum(String name, int value) {
		super(name, value);
	}

	public static List getEnumList() {
		return getEnumList(OUTypeEnum.class);
	}

	public static OUTypeEnum getEnum(int type) {
		return (OUTypeEnum) getEnum(OUTypeEnum.class, type);
	}
}
