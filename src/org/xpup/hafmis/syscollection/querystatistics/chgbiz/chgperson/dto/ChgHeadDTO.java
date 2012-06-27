package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto;

import java.util.ArrayList;
import java.util.List;

public class ChgHeadDTO {

  private List list=new ArrayList();
  private ChgpersonOrgHeadDTO chgpersonOrgHeadDTO=new ChgpersonOrgHeadDTO();

  public ChgpersonOrgHeadDTO getChgpersonOrgHeadDTO() {
    return chgpersonOrgHeadDTO;
  }
  public void setChgpersonOrgHeadDTO(ChgpersonOrgHeadDTO chgpersonOrgHeadDTO) {
    this.chgpersonOrgHeadDTO = chgpersonOrgHeadDTO;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
}
