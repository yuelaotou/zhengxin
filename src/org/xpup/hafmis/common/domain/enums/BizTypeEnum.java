package org.xpup.hafmis.common.domain.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.Enum;

public final class BizTypeEnum extends Enum {

	private static final long serialVersionUID = 4364046104323543900L;

	public static final BizTypeEnum 业务办理 = new BizTypeEnum("业务办理");

	public static final BizTypeEnum 业务维护 = new BizTypeEnum("业务维护");

	protected BizTypeEnum(String name) {
		super(name);
	}

	public static Map getEnumMap() {
		return getEnumMap(BizTypeEnum.class);
	}

	public static List getEnumList() {
		return getEnumList(BizTypeEnum.class);
	}

	public static BizTypeEnum getEnum(String bizType) {
		return (BizTypeEnum) getEnum(BizTypeEnum.class, bizType);
	}

	public static Iterator iterator() {
		return iterator(BizTypeEnum.class);
	}

}
