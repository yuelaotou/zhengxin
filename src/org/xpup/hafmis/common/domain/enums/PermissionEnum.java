package org.xpup.hafmis.common.domain.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.Enum;

public final class PermissionEnum extends Enum {

	private static final long serialVersionUID = 4364046104323543900L;

	public static final PermissionEnum 本人 = new PermissionEnum("1");

	public static final PermissionEnum 所在部门 = new PermissionEnum("2");

	protected PermissionEnum(String name) {
		super(name);
	}

	public static Map getEnumMap() {
		return getEnumMap(PermissionEnum.class);
	}

	public static List getEnumList() {
		return getEnumList(PermissionEnum.class);
	}

	public static PermissionEnum getEnum(String dataLevel) {
		return (PermissionEnum) getEnum(PermissionEnum.class, dataLevel);
	}

	public static Iterator iterator() {
		return iterator(PermissionEnum.class);
	}

}
