package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.dto;

public class ReportMngDTO {

  /** identifier field */
  private String id;

  /** persistent field */
  private String bookId;

  /** persistent field */
  private String tailtableName;

  /** persistent field */
  private String tailtableNameChinese;

  /** persistent field */
  private String colspan;

  /** persistent field */
  private String rowspan;

  /** persistent field */
  private String querymode;

  /** persistent field */
  private String urlid;

  /** persistent field */
  private String createtime;

  /** persistent field */
  private String createperson;

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public String getColspan() {
    return colspan;
  }

  public void setColspan(String colspan) {
    this.colspan = colspan;
  }

  public String getCreateperson() {
    return createperson;
  }

  public void setCreateperson(String createperson) {
    this.createperson = createperson;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getQuerymode() {
    return querymode;
  }

  public void setQuerymode(String querymode) {
    this.querymode = querymode;
  }

  public String getRowspan() {
    return rowspan;
  }

  public void setRowspan(String rowspan) {
    this.rowspan = rowspan;
  }

  public String getTailtableName() {
    return tailtableName;
  }

  public void setTailtableName(String tailtableName) {
    this.tailtableName = tailtableName;
  }

  public String getTailtableNameChinese() {
    return tailtableNameChinese;
  }

  public void setTailtableNameChinese(String tailtableNameChinese) {
    this.tailtableNameChinese = tailtableNameChinese;
  }

  public String getUrlid() {
    return urlid;
  }

  public void setUrlid(String urlid) {
    this.urlid = urlid;
  }
}
