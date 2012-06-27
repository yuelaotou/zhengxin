package org.xpup.hafmis.sysloan.dataready.develop.bsinterface;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Build;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;
import org.xpup.hafmis.sysloan.dataready.develop.form.BuildNewAF;

public interface IDevelopBS {

  public void saveDevelopInfo(Developer developer, SecurityInfo securityInfo);
  public Developer findDeveloperInfo(Integer id);
  public boolean isDeveloperST(Integer id);
  public void modifyDeveloperST(Integer id, String type, SecurityInfo securityInfo) throws BusinessException;
  public void modifyDeveloperInfo(Developer new_developer, Developer old_developers, SecurityInfo securityInfo);
  public List findDevelopPrintList_print(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public List findDevelopList_info(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public void delDevelopInfo(Serializable id) throws Exception;
  
  public FloorDevelopInfoDTO showFloorDevelopInfo(Integer id);
  public List findFloorList(Pagination pagination)throws Exception;
  public void saveFloorInfo(DevelopProject developProject, SecurityInfo securityInfo) throws BusinessException;
  public DevelopProject findDevelopProjectInfo(Integer id);
  public void modifyFloorInfo(DevelopProject old_developProject, DevelopProject new_developProject, SecurityInfo securityInfo);
  public void modifyFloorST(Integer id, String type, SecurityInfo securityInfo) throws BusinessException;
  public List findFloorPrintList(Pagination pagination) throws Exception;
  public void updateDeveloperInfo(Integer id, String path) throws Exception;
  public void delFloorInfo(Integer id) throws Exception;
  
  public List findBuildList(Pagination pagination) throws Exception;
  public void saveBuildInfo(BuildNewAF buildNewAF,SecurityInfo securityInfo,String floorId,String developId)throws Exception;
  public Build findBuildInfo(Integer id);
  public void modifyBuildInfo(Build build, SecurityInfo securityInfo,String floorId,String developId);
  public void deleteBuildInfo(Build build, SecurityInfo securityInfo);
  public void insertHafOperateLog(String buildId,SecurityInfo securityInfo)throws BusinessException;
  public List findBuildList_exp(Pagination pagination) throws Exception;
  public void buildImportInfo(List buildImportList1, List bulidImportList2,
      String floorId,SecurityInfo securityInfo,String developId) throws Exception,BusinessException;
  public String fundStatus(String developId,String floorId);
  public void updateBuildStatus(String floorId);
  public String findFloornum(String id) throws Exception;
  public String findFloorname(final String id)  throws Exception;
}
