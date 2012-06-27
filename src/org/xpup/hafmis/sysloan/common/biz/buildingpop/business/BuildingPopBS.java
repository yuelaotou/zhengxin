package org.xpup.hafmis.sysloan.common.biz.buildingpop.business;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.buildingpop.bsinterface.IBuildingPopBS;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;

public class BuildingPopBS implements IBuildingPopBS {
  
  private DeveloperDAO developerDAO;
  
  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public List findBuildingList(Pagination pagination,SecurityInfo securityInfo)
    throws BusinessException{
    List list = null;
    int count = 0;
    try {
      String developerId = (String) pagination.getQueryCriterions().get("developerId");
      String developerName = (String) pagination.getQueryCriterions().get("developerName");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;  
      int pageSize = pagination.getPageSize();
      list = developerDAO.findBuildingListByHeadid(developerId,developerName,orderBy, order, start, pageSize);
      count = developerDAO.findBuildingAllListByHeadid(developerId,developerName).size();
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }
}
