package org.xpup.hafmis.syscollection.collloanback.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class CollLoanbackTaAF extends ValidatorForm {
  private static final long serialVersionUID = 4545439817994321837L;

  private FormFile theFile = null;// 导入的文件名

  private String url = "";// 导入的路径

  private String officeCode = "";// 办事处

  private String collectionBankId = "";// 放款银行

  private String batchNum = "";// 批次号

  private String c_count = "";

  private String yg_count = "0";

  private String yg_sum = "0";

  private String p_count = "0";

  private String kouLoanAcc2 = "";

  private BigDecimal m_sum = new BigDecimal(0.00);

  private BigDecimal real_kou_money = new BigDecimal(0.00);

  private String real_count = "";

  private String startdate = "";

  private String enddate = "";

  private String sun = "";

  private List list = new ArrayList();

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public FormFile getTheFile() {
    return theFile;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getCollectionBankId() {
    return collectionBankId;
  }

  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getC_count() {
    return c_count;
  }

  public void setC_count(String c_count) {
    this.c_count = c_count;
  }

  public String getKouLoanAcc2() {
    return kouLoanAcc2;
  }

  public void setKouLoanAcc2(String kouLoanAcc2) {
    this.kouLoanAcc2 = kouLoanAcc2;
  }

  public String getP_count() {
    return p_count;
  }

  public void setP_count(String p_count) {
    this.p_count = p_count;
  }

  public BigDecimal getM_sum() {
    return m_sum;
  }

  public void setM_sum(BigDecimal m_sum) {
    this.m_sum = m_sum;
  }

  public String getReal_count() {
    return real_count;
  }

  public void setReal_count(String real_count) {
    this.real_count = real_count;
  }

  public BigDecimal getReal_kou_money() {
    return real_kou_money;
  }

  public void setReal_kou_money(BigDecimal real_kou_money) {
    this.real_kou_money = real_kou_money;
  }

  public String getEnddate() {
    return enddate;
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }

  public String getStartdate() {
    return startdate;
  }

  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }

  public String getYg_count() {
    return yg_count;
  }

  public void setYg_count(String yg_count) {
    this.yg_count = yg_count;
  }

  public String getYg_sum() {
    return yg_sum;
  }

  public void setYg_sum(String yg_sum) {
    this.yg_sum = yg_sum;
  }

  public String getSun() {
    return sun;
  }

  public void setSun(String sun) {
    this.sun = sun;
  }
}
