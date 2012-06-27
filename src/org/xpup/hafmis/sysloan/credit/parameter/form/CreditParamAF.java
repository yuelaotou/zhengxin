package org.xpup.hafmis.sysloan.credit.parameter.form;

import org.apache.struts.action.ActionForm;

/**
 * @author 杨光
 */
public class CreditParamAF extends ActionForm {

  private static final long serialVersionUID = 1L;

  /** 数据格式版本号 */
  private String sjgsbbh = null;

  /** 金融机构代码 */
  private String jrjgdm = null;

  /** 上传报文版本号 */
  private String scbwbbh = null;

  /** 发生地点 */
  private String fsdd = null;

  /** 币种 */
  private String bz = null;

  /** 联系人 */
  private String lxr = null;

  /** 联系电话 */
  private String lxdh = null;

  public String getJrjgdm() {
    return jrjgdm;
  }

  public void setJrjgdm(String jrjgdm) {
    this.jrjgdm = jrjgdm;
  }

  public String getScbwbbh() {
    return scbwbbh;
  }

  public void setScbwbbh(String scbwbbh) {
    this.scbwbbh = scbwbbh;
  }

  public String getSjgsbbh() {
    return sjgsbbh;
  }

  public void setSjgsbbh(String sjgsbbh) {
    this.sjgsbbh = sjgsbbh;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getFsdd() {
    return fsdd;
  }

  public void setFsdd(String fsdd) {
    this.fsdd = fsdd;
  }

  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

  public String getLxr() {
    return lxr;
  }

  public void setLxr(String lxr) {
    this.lxr = lxr;
  }

}
