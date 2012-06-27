package org.xpup.hafmis.sysfinance.systemmaintain.hafoperateLog.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class HafoperateLogAF extends ActionForm{
  private String opmodle="";
  private String oper="";
  private String starttime="";
  private String endtime="";
  private String opaction="";

  private Map opmodlemap=new HashMap();
  private Map opactionmap=new HashMap();
  
  private List list=new ArrayList();
  
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getEndtime() {
    return endtime;
  }
  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }
  public String getOpaction() {
    return opaction;
  }
  public void setOpaction(String opaction) {
    this.opaction = opaction;
  }
  public String getOper() {
    return oper;
  }
  public void setOper(String oper) {
    this.oper = oper;
  }
  public String getOpmodle() {
    return opmodle;
  }
  public void setOpmodle(String opmodle) {
    this.opmodle = opmodle;
  }
  public String getStarttime() {
    return starttime;
  }
  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }
  public Map getOpactionmap() {
    return opactionmap;
  }
  public void setOpactionmap(Map opactionmap) {
    this.opactionmap = opactionmap;
  }
  public Map getOpmodlemap() {
    return opmodlemap;
  }
  public void setOpmodlemap(Map opmodlemap) {
    this.opmodlemap = opmodlemap;
  }

}
