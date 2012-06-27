package org.xpup.hafmis.sysloan.dataready.develop.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbFindDTO;

/**
 * 开发商维护页的ActionForm
 * 
 * @author 付云峰
 */
public class DevelopFindAF extends ActionForm {

  /** 查询条件 */
  private DevelopTbFindDTO developTbFindDTO = new DevelopTbFindDTO();
  
  private String buyhouseorgid; // 开发商名称，用来查询的

  private String floorName; // 楼盘名称

  private String floorNum; // 楼栋
  
  /** 列表内容 */
  private List list;

  /** 开发商状态Map */
  private Map developerStMap = new HashMap();

  public Map getDeveloperStMap() {
    return developerStMap;
  }

  public void setDeveloperStMap(Map developerStMap) {
    this.developerStMap = developerStMap;
  }

  public DevelopTbFindDTO getDevelopTbFindDTO() {
    return developTbFindDTO;
  }

  public void setDevelopTbFindDTO(DevelopTbFindDTO developTbFindDTO) {
    this.developTbFindDTO = developTbFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

}
