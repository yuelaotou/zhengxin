package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollinfoSumDTO;

public class OrgCollInfoFindAF extends ActionForm {
  
  /** 封装了查询条件的DTO*/
  OrgCollInfoFindDTO dto = new OrgCollInfoFindDTO();
  /** 初始化表单map*/
  private Map natureofunitsMap = new HashMap();
  private Map authoritiesMap = new HashMap();
  private Map openstatusMap = new HashMap();
  private Map paymodeMap = new HashMap();
  private Map regionMap = new HashMap();
  /** 查询列表 */
  private List list = new ArrayList();
  /** 合计 */
  private OrgCollinfoSumDTO orgCollinfoSumDTO = new OrgCollinfoSumDTO();
  
  public OrgCollinfoSumDTO getOrgCollinfoSumDTO() {
    return orgCollinfoSumDTO;
  }
  public void setOrgCollinfoSumDTO(OrgCollinfoSumDTO orgCollinfoSumDTO) {
    this.orgCollinfoSumDTO = orgCollinfoSumDTO;
  }
  public Map getAuthoritiesMap() {
    return authoritiesMap;
  }
  public void setAuthoritiesMap(Map authoritiesMap) {
    this.authoritiesMap = authoritiesMap;
  }
  public Map getNatureofunitsMap() {
    return natureofunitsMap;
  }
  public void setNatureofunitsMap(Map natureofunitsMap) {
    this.natureofunitsMap = natureofunitsMap;
  }
  public Map getOpenstatusMap() {
    return openstatusMap;
  }
  public void setOpenstatusMap(Map openstatusMap) {
    this.openstatusMap = openstatusMap;
  }
  public Map getPaymodeMap() {
    return paymodeMap;
  }
  public void setPaymodeMap(Map paymodeMap) {
    this.paymodeMap = paymodeMap;
  }
  public Map getRegionMap() {
    return regionMap;
  }
  public void setRegionMap(Map regionMap) {
    this.regionMap = regionMap;
  }
  public OrgCollInfoFindDTO getDto() {
    return dto;
  }
  public void setDto(OrgCollInfoFindDTO dto) {
    this.dto = dto;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  
  
  

}
