package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class DepositCheckAccAF extends ActionForm {
  /**
   * 查询条件
   */
  private String settDateSt="";//结算日期开始
  private String settDateEnd="";//结算日期结束
  private String subjectCode="";//科目
  /**
   * 列表
   */
  private List bankDayClearList=new ArrayList();//银行日记账列表
  private List bankCheckAccList=new ArrayList();//银行对账单列表
  public void clear(){
    this.settDateSt="";
    this.settDateEnd="";
    this.subjectCode="";
  }
  public List getBankCheckAccList() {
    return bankCheckAccList;
  }
  public void setBankCheckAccList(List bankCheckAccList) {
    this.bankCheckAccList = bankCheckAccList;
  }
  public List getBankDayClearList() {
    return bankDayClearList;
  }
  public void setBankDayClearList(List bankDayClearList) {
    this.bankDayClearList = bankDayClearList;
  }
  public String getSettDateEnd() {
    return settDateEnd;
  }
  public void setSettDateEnd(String settDateEnd) {
    this.settDateEnd = settDateEnd;
  }
  public String getSettDateSt() {
    return settDateSt;
  }
  public void setSettDateSt(String settDateSt) {
    this.settDateSt = settDateSt;
  }
  public String getSubjectCode() {
    return subjectCode;
  }
  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }
}
