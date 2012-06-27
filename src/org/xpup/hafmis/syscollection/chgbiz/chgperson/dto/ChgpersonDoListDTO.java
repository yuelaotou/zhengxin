package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import java.util.List;

import org.apache.struts.upload.FormFile;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;

public class ChgpersonDoListDTO {
  private ChgPersonTail chgPersonTail=new ChgPersonTail();
  private String id="";//单位编码
  private String name="";//单位名称
  private String date="";//变更年月
  private List list;//列表
  private String type = "1";
  private String flag1="1";//文本框是否只读

  private FormFile theFile = null;//导入文件
  private String url="";
  public ChgPersonTail getChgPersonTail() {
    return chgPersonTail;
  }
  public void setChgPersonTail(ChgPersonTail chgPersonTail) {
    this.chgPersonTail = chgPersonTail;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getFlag1() {
    return flag1;
  }
  public void setFlag1(String flag1) {
    this.flag1 = flag1;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public FormFile getTheFile() {
    return theFile;
  }
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
}
