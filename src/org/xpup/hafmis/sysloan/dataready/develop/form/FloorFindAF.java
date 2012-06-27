package org.xpup.hafmis.sysloan.dataready.develop.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;

/**
 * 楼盘信息的ActionForm
 * 
 * @author 付云峰
 */
public class FloorFindAF extends ActionForm {
  /**
   * 封装了开发商信息的DTO
   */
  private FloorDevelopInfoDTO floorDevelopInfoDTO = new FloorDevelopInfoDTO();
  
  /**
   * 封装了列表信息的List
   */
  private List list;
  /**
   * 用来判断是否第一项是表头的信息
   */
  private String flag = "";

  public FloorDevelopInfoDTO getFloorDevelopInfoDTO() {
    return floorDevelopInfoDTO;
  }

  public void setFloorDevelopInfoDTO(FloorDevelopInfoDTO floorDevelopInfoDTO) {
    this.floorDevelopInfoDTO = floorDevelopInfoDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }
}
