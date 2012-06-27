package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.business;

import java.util.List;
import java.util.StringTokenizer;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbFindDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbListDTO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.bsinterface.IEmpolderQueryBS;

public class EmpolderQueryBS implements IEmpolderQueryBS {

  // PL005表DAO
  protected DeveloperDAO developerDAO = null;

  // PL006表DAO
  protected DevelopProjectDAO developProjectDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  public List findDevelopList(Pagination pagination) throws Exception {
    // 这个方法已经不调用了
    return null;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
  
  
}
