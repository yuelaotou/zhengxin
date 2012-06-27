package org.xpup.hafmis.syscollection.accounthandle.bizcheck.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * 
 * @author 吴洪涛
 *
 */
public class BizcheckDetailAF extends ValidatorActionForm{

  private static final long serialVersionUID = 0L;
  
//  private Integer totalPeople=new Integer(0);//合计人数
//  private BigDecimal totalMoney=new BigDecimal(0.00);//合计金额
//  private BigDecimal totalInterest=new BigDecimal(0.00);//合计利息

  private String headid;//头表id
  private String bank;
  private String docNum;
  private String noteNum;
  private String traninId; 
  private String tranoutId;
  private String traninName;
  private String tranoutName;
  private String operator;
  private String biz_type;
  private String type;//类型
  private List list;  //list (带利息的)
  private List otherList;  //其他list 
  private String settDate;
  private String banlance;
  private String amount;
  private String reason;
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getBank() {
    return bank;
  }
  public void setBank(String bank) {
    this.bank = bank;
  }
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getNoteNum() {
    return noteNum;
  }
  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }

  public List getOtherList() {
    return otherList;
  }
  public void setOtherList(List otherList) {
    this.otherList = otherList;
  }
  public String getBiz_type() {
    return biz_type;
  }
  public void setBiz_type(String biz_type) {
    this.biz_type = biz_type;
  }
  public String getTraninId() {
    return traninId;
  }
  public void setTraninId(String traninId) {
    this.traninId = traninId;
  }
  public String getTraninName() {
    return traninName;
  }
  public void setTraninName(String traninName) {
    this.traninName = traninName;
  }
  public String getTranoutId() {
    return tranoutId;
  }
  public void setTranoutId(String tranoutId) {
    this.tranoutId = tranoutId;
  }
  public String getTranoutName() {
    return tranoutName;
  }
  public void setTranoutName(String tranoutName) {
    this.tranoutName = tranoutName;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getHeadid() {
    return headid;
  }
  public void setHeadid(String headid) {
    this.headid = headid;
  }
  public String getAmount() {
    return amount;
  }
  public void setAmount(String amount) {
    this.amount = amount;
  }
  public String getBanlance() {
    return banlance;
  }
  public void setBanlance(String banlance) {
    this.banlance = banlance;
  }
}
