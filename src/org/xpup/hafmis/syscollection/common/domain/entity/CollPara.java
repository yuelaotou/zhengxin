package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

public class CollPara extends DomainObject implements Serializable{
  private BigDecimal param_id=new BigDecimal(0.00);
  private String param_descrip;
  private String param_value;
  private String param_num;
  private String param_explain;
  private String param_type;
  private String reservea_a;
  private String reservea_b;
  private String reservea_c;
  public String getParam_descrip() {
    return param_descrip;
  }
  public void setParam_descrip(String param_descrip) {
    this.param_descrip = param_descrip;
  }
  public String getParam_explain() {
    return param_explain;
  }
  public void setParam_explain(String param_explain) {
    this.param_explain = param_explain;
  }
  public BigDecimal getParam_id() {
    return param_id;
  }
  public void setParam_id(BigDecimal param_id) {
    this.param_id = param_id;
  }
  public String getParam_num() {
    return param_num;
  }
  public void setParam_num(String param_num) {
    this.param_num = param_num;
  }
  public String getParam_type() {
    return param_type;
  }
  public void setParam_type(String param_type) {
    this.param_type = param_type;
  }
  public String getParam_value() {
    return param_value;
  }
  public void setParam_value(String param_value) {
    this.param_value = param_value;
  }
  public String getReservea_a() {
    return reservea_a;
  }
  public void setReservea_a(String reservea_a) {
    this.reservea_a = reservea_a;
  }
  public String getReservea_b() {
    return reservea_b;
  }
  public void setReservea_b(String reservea_b) {
    this.reservea_b = reservea_b;
  }
  public String getReservea_c() {
    return reservea_c;
  }
  public void setReservea_c(String reservea_c) {
    this.reservea_c = reservea_c;
  }
}
