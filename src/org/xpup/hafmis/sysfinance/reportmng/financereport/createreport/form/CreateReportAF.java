package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.dto.ReportMngDTO;
/**
 * 
 * @author 王菱
 * 2007-10-16
 */
public class CreateReportAF extends ActionForm {
  
  private static final long serialVersionUID = -2138600478238560629L;
  
  private String tableid="";
  private String tablenamedef="";//定义的报表名称 
  private String colspandef="";//定义的报表行数
  private String rowspandef="";//定义的报表列数
  
  private String tablenamequery="";//查询的报表名称
  private String createtimestart="";//查询的报表创建起始日期
  private String createtimeend="";//查询的报表创建中止日期
  
  private ReportMngDTO reportMngDTO=new ReportMngDTO();
  private List list=new ArrayList();//显示的列表
  private String actionflag="0";//动作标识：0：确定；1、修改
  private String savemethod="0";//动作标识：1：确定
  

  public String getTableid() {
    return tableid;
  }
  public void setTableid(String tableid) {
    this.tableid = tableid;
  }
  public String getActionflag() {
    return actionflag;
  }
  public void setActionflag(String actionflag) {
    this.actionflag = actionflag;
  }
  public String getColspandef() {
    return colspandef;
  }
  public void setColspandef(String colspandef) {
    this.colspandef = colspandef;
  }
  public String getCreatetimeend() {
    return createtimeend;
  }
  public void setCreatetimeend(String createtimeend) {
    this.createtimeend = createtimeend;
  }
  public String getCreatetimestart() {
    return createtimestart;
  }
  public void setCreatetimestart(String createtimestart) {
    this.createtimestart = createtimestart;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public ReportMngDTO getReportMngDTO() {
    return reportMngDTO;
  }
  public void setReportMngDTO(ReportMngDTO reportMngDTO) {
    this.reportMngDTO = reportMngDTO;
  }
  public String getRowspandef() {
    return rowspandef;
  }
  public void setRowspandef(String rowspandef) {
    this.rowspandef = rowspandef;
  }
  public String getTablenamedef() {
    return tablenamedef;
  }
  public void setTablenamedef(String tablenamedef) {
    this.tablenamedef = tablenamedef;
  }
  public String getTablenamequery() {
    return tablenamequery;
  }
  public void setTablenamequery(String tablenamequery) {
    this.tablenamequery = tablenamequery;
  }
  public String getSavemethod() {
    return savemethod;
  }
  public void setSavemethod(String savemethod) {
    this.savemethod = savemethod;
  }

}
