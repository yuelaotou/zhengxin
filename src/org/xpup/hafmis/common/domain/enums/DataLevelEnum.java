package org.xpup.hafmis.common.domain.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.Enum;

public final class DataLevelEnum extends Enum {

	private static final long serialVersionUID = 4364046104323543900L;

	public static final DataLevelEnum 本人 = new DataLevelEnum("本人");

	public static final DataLevelEnum 办事处 = new DataLevelEnum("办事处");

	public static final DataLevelEnum 中心 = new DataLevelEnum("中心");

	protected DataLevelEnum(String name) {
		super(name);
	}

	public static Map getEnumMap() {
		return getEnumMap(DataLevelEnum.class);
	}

	public static List getEnumList() {
		return getEnumList(DataLevelEnum.class);
	}

	public static DataLevelEnum getEnum(String dataLevel) {
		return (DataLevelEnum) getEnum(DataLevelEnum.class, dataLevel);
	}

	public static Iterator iterator() {
		return iterator(DataLevelEnum.class);
	}

}
