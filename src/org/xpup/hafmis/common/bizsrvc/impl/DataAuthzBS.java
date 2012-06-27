package org.xpup.hafmis.common.bizsrvc.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.bizsrvc.IDataAuthzBS;
import org.xpup.hafmis.common.domain.BizDataLimit;
import org.xpup.hafmis.common.domain.enums.BizTypeEnum;
import org.xpup.hafmis.common.domain.enums.DataLevelEnum;
import org.xpup.hafmis.common.domain.enums.PermissionEnum;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.domain.HafRole;
import org.xpup.security.common.dao.DrRelationDAO;
import org.xpup.security.common.dao.DuRelationDAO;
import org.xpup.security.common.dao.UserDAO;
import org.xpup.security.common.domain.DrRelation;
import org.xpup.security.common.domain.DuRelation;
import org.xpup.security.common.domain.User;

public class DataAuthzBS implements IDataAuthzBS {

	private DuRelationDAO duRelationDAO;

	private DrRelationDAO drRelationDAO;

	private UserDAO userDAO;

	public DrRelationDAO getDrRelationDAO() {
		return drRelationDAO;
	}

	public void setDrRelationDAO(DrRelationDAO drRelationDAO) {
		this.drRelationDAO = drRelationDAO;
	}

	public DuRelationDAO getDuRelationDAO() {
		return duRelationDAO;
	}

	public void setDuRelationDAO(DuRelationDAO duRelationDAO) {
		this.duRelationDAO = duRelationDAO;
	}

	@Override
	public List findBizDataLimitsForQuery(String username, BizTypeEnum bizType) throws BusinessException {

		List limits = new ArrayList();

		User user = userDAO.queryByUsername(username);
		if (user == null)
			throw new BusinessException("当前登录用户已经被删除，不能继续业务操作。");

		List drs = drRelationDAO.queryByUsername(username, bizType.getName());
		Iterator it = drs.iterator();
		while (it.hasNext()) {
			DrRelation dr = (DrRelation) it.next();
			PermissionEnum p = PermissionEnum.getEnum(dr.getQueryLevel());
			Validate.notNull(p, "不存在此种权限类型。");

			if (p.equals(PermissionEnum.所在部门)) {
				Serializable officeId = ((HafRole) dr.getRole()).getOfficeId();
				if (officeId != null) {
					BizDataLimit limit = new BizDataLimit();
					limit.setDataLevel(DataLevelEnum.办事处);
					limit.setId(officeId);
					limits.add(limit);
				} else {
					BizDataLimit limit = new BizDataLimit();
					limit.setDataLevel(DataLevelEnum.中心);
					limits.add(limit);
					return limits;
				}
			} else if (p.equals(PermissionEnum.本人)) {
				BizDataLimit limit = new BizDataLimit();
				limit.setDataLevel(DataLevelEnum.本人);
				limit.setId(user.getId());
				limits.add(limit);
			}
		}

		List dus = duRelationDAO.queryByUsername(username, bizType.getName());
		it = dus.iterator();
		while (it.hasNext()) {
			DuRelation du = (DuRelation) it.next();
			PermissionEnum p = PermissionEnum.getEnum(du.getQueryLevel());
			Validate.notNull(p, "不存在此种权限类型。");

			if (p.equals(PermissionEnum.所在部门)) {
				Serializable officeId = ((HafEmployee) du.getUser()).getOfficeId();
				if (officeId != null) {
					BizDataLimit limit = new BizDataLimit();
					limit.setDataLevel(DataLevelEnum.办事处);
					limit.setId(officeId);
					limits.add(limit);
				} else {
					BizDataLimit limit = new BizDataLimit();
					limit.setDataLevel(DataLevelEnum.中心);
					limits.add(limit);
					return limits;
				}
			} else if (p.equals(PermissionEnum.本人)) {
				BizDataLimit limit = new BizDataLimit();
				limit.setDataLevel(DataLevelEnum.本人);
				limit.setId(user.getId());
				limits.add(limit);
			}
		}

		return limits;
	}

	@Override
	public void decideForOperation(String username, BizTypeEnum bizType, Serializable officeId, Serializable operatorId)
			throws BusinessException {
		Validate.notNull(username, "参数username不能为空。");
		Validate.notNull(bizType, "参数bizType不能为空。");
		Validate.notNull(officeId, "参数officeId不能为空。");
		Validate.notNull(operatorId, "参数operatorId不能为空。");

		User user = userDAO.queryByUsername(username);
		if (user == null)
			throw new BusinessException("当前登录用户已经被删除，不能继续业务操作。");

		if (user.getId().equals(operatorId) && BizTypeEnum.业务维护.equals(bizType))
			return;

		List drs = drRelationDAO.queryByUsername(username, bizType.getName());
		Iterator it = drs.iterator();
		while (it.hasNext()) {
			DrRelation dr = (DrRelation) it.next();
			PermissionEnum p = PermissionEnum.getEnum(dr.getOperationLevel());
			Validate.notNull(p, "不存在此种权限类型。");

			Serializable id = ((HafRole) dr.getRole()).getOfficeId();
			if (p.equals(PermissionEnum.所在部门)) {
				if (id == null || id.equals(officeId)) {
					return;
				}
			}
		}

		List dus = duRelationDAO.queryByUsername(username, bizType.getName());
		it = dus.iterator();
		while (it.hasNext()) {
			DuRelation du = (DuRelation) it.next();
			PermissionEnum p = PermissionEnum.getEnum(du.getQueryLevel());
			Validate.notNull(p, "不存在此种权限类型。");

			Serializable id = ((HafEmployee) du.getUser()).getOfficeId();
			if (p.equals(PermissionEnum.所在部门)) {
				if (id == null || id.equals(officeId)) {
					return;
				}
			}
		}

		throw new BusinessException("对不起，您没有此项数据的业务期限。");
	}

}
