package org.xpup.hafmis.orgstrct.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;
import org.xpup.hafmis.orgstrct.domain.OuptItem;
import org.xpup.hafmis.orgstrct.dto.OuptDTO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IMaintainOuptBS {

  public List findOupts(Pagination pagination);

  public OrgUnitPropTemplate findOupt(Serializable id) throws BusinessException;

  public Serializable createOupt(OrgUnitPropTemplate oupt,SecurityInfo securityInfo)
      throws BusinessException;

  public void modifyOupt(OrgUnitPropTemplate oupt,SecurityInfo securityInfo) throws BusinessException;

  public void removeOupt(Serializable id,SecurityInfo securityInfo) throws BusinessException;

  public OuptDTO findOuptItems(Pagination pagination) throws BusinessException;

  public OuptItem findOuptItem(Serializable id) throws BusinessException;

  public Serializable createOuptItem(OuptItem ouptItem,SecurityInfo securityInfo)
      throws BusinessException;

  public void modifyOuptItem(OuptItem ouptItem,SecurityInfo securityInfo) throws BusinessException;

  public void removeOuptItem(Serializable id,SecurityInfo securityInfo) throws BusinessException;

}
