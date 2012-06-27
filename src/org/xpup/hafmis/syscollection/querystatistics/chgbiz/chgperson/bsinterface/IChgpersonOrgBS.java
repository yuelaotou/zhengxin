package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonEmpHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonOrgHeadDTO;
/**
 * 
 * @author 王玲
 *2007-6-27
 */
public interface IChgpersonOrgBS {
  //根据条件查询人员变更记录
  public List findChgpersonOrgListByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //根据条件查询人员变更记录单位的合计
  public ChgpersonOrgHeadDTO findChgpersonOrgHeadByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //打印单位信息
  public ChgHeadDTO queryChgpersonOrgListAll(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;

  //根据条件查询人员变更职工记录
  public List findChgpersonEmpListByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //根据头表ID条件查询人员变更职工记录
  public List findChgpersonEmpListByChgpersonHeadID(Pagination pagination,String chgpersonHeadID,SecurityInfo securityInfo) throws Exception,BusinessException;
  //计算查询人员变更记录职工的合计
  public ChgpersonEmpHeadDTO findChgpersonEmpHeadByCriterions(List list) throws Exception,BusinessException;
  //打印职工信息
  public List queryChgpersonEmpListAll(Pagination pagination,String chgpersonHeadID,SecurityInfo securityInfo) throws Exception,BusinessException;
  // 根据条件查询人员变更记录合计项
  public ChgPersonHeadFormule findChgpersonHeadByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception,BusinessException;
  public List getChgpersonQueryList(Pagination pagination) throws Exception, BusinessException;
}