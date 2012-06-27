package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;

import org.xpup.hafmis.orgstrct.domain.enums.OUTypeEnum;

public class OrgUnitUtils {

	private Serializable officeId = null;

	private Serializable departmentId = null;

	public OrgUnitUtils(OrganizationUnit orgUnit) {
		OrganizationUnit ou = orgUnit;
		do {
			OUTypeEnum type = ou.getType();
			if (OUTypeEnum.部门.equals(type)) {
				departmentId = ou.getId();
			} else if (OUTypeEnum.办事处.equals(type)) {
				officeId = ou.getId();
			}
			ou = ou.getParent();
		} while (ou != null);
	}

	public Serializable getDepartmentId() {
		return departmentId;
	}

	public Serializable getOfficeId() {
		return officeId;
	}

}
