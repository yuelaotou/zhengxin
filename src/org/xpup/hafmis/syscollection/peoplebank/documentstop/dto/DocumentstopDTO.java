package org.xpup.hafmis.syscollection.peoplebank.documentstop.dto;

import java.io.Serializable;

public class DocumentstopDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /** dataReportingAgencies property */
  private String dataReportingAgencies = ""; //数据上报机构

  /** contactPhone property */
  private String contactPhone = "";          //联系电话

  /** locationSpace property */
  private String locationSpace = "";         //发生地点

  /** contactPreson property */
  private String contactPreson = "";         //联系人

  /** dataFormatVersion property */
  private String dataFormatVersion = "";     //数据格式版本号

  /** dataMechanism property */
  private String dataMechanism = "";         //数据发生机构

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public String getContactPreson() {
    return contactPreson;
  }

  public void setContactPreson(String contactPreson) {
    this.contactPreson = contactPreson;
  }

  public String getDataFormatVersion() {
    return dataFormatVersion;
  }

  public void setDataFormatVersion(String dataFormatVersion) {
    this.dataFormatVersion = dataFormatVersion;
  }

  public String getDataMechanism() {
    return dataMechanism;
  }

  public void setDataMechanism(String dataMechanism) {
    this.dataMechanism = dataMechanism;
  }

  public String getDataReportingAgencies() {
    return dataReportingAgencies;
  }

  public void setDataReportingAgencies(String dataReportingAgencies) {
    this.dataReportingAgencies = dataReportingAgencies;
  }

  public String getLocationSpace() {
    return locationSpace;
  }

  public void setLocationSpace(String locationSpace) {
    this.locationSpace = locationSpace;
  }
}
