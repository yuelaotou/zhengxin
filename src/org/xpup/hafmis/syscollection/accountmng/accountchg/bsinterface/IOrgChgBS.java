package org.xpup.hafmis.syscollection.accountmng.accountchg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountchg.dto.OrgChgDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLog;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

public interface IOrgChgBS {
  public Org findOrgChgById(Integer id, SecurityInfo securityInfo)
      throws BusinessException;

  public Org findOrgChgById(Integer id) throws BusinessException;

  public void saveOrgChg(String id, String chgType, SecurityInfo securityInfo)
      throws Exception;

  public List findOrgChgList(Pagination pagination) throws BusinessException;

  public OrgChgLog findOrgChg(String id) throws BusinessException;

  public OrgInfo getCollBankAndOffice(String id, String officecode)
      throws BusinessException;

  public OrgChgDTO getOrgChgById(Integer id) throws BusinessException;
}
