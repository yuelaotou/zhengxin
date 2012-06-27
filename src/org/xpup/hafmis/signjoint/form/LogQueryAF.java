package org.xpup.hafmis.signjoint.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

public class LogQueryAF extends ValidatorActionForm{

  private String filetype;
  private String starttime;
  private String endtime;
  private List list=new ArrayList();
  public LogQueryAF() {
    this.filetype = "";
    this.starttime = "";
    this.endtime = "";
  }
  public String getEndtime() {
    return endtime;
  }
  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }
  public String getFiletype() {
    return filetype;
  }
  public void setFiletype(String filetype) {
    this.filetype = filetype;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getStarttime() {
    return starttime;
  }
  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }
  public LogQueryAF(String filetype, String starttime, String endtime) {
    this.filetype = filetype;
    this.starttime = starttime;
    this.endtime = endtime;
  }

  
}
