package org.xpup.hafmis.orgstrct.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IMaintainOrgUnitBS {

  public List loadOrgUnitTree();

  public OrganizationUnit findOrgUnit(Serializable id) throws BusinessException;

  public List findAllParameters(Serializable orgUnitId);

  public List findAllAttributes(Serializable orgUnitId);

  public List getAllParametersFromOupt(Serializable ouptId);

  public List getAllAttributesFromOupt(Serializable ouptId);

  public Serializable createOrgUnit(OrganizationUnit orgUnit,SecurityInfo securityInfo)
      throws BusinessException;

  public void modifyOrgUnit(OrganizationUnit orgUnit,SecurityInfo securityInfo) throws BusinessException;

  public void removeOrgUnit(Serializable id,SecurityInfo securityInfo) throws BusinessException;

  public List findAllOupts();

}
