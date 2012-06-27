package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO;

public class QueryOperationLogAF extends ActionForm{


  private static final long serialVersionUID = 2531807195056023196L;

//  LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();

  private List list = null;// 显示列表
  
  private List printList = null;// 打印列表

  private Map bizType = new HashMap();// 业务类型

  private Map bizStatus = new HashMap();// ;/业务状态
  
  private String bizTypeValue = null;// 类型
  
  private String bizStatusValue = null;// 状态
  
  private List operatorList = null;// ;/操作员
  
  private String operatorValue = null;// ;/操作员
  
  private String beginTime = null;// 起始时间
  
  private String endTime = null;// 结束时间
  
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.bizStatusValue="";
    this.bizTypeValue="";
    this.beginTime="";
    this.endTime="";
    this.operatorValue="";
  }

  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public Map getBizStatus() {
    return bizStatus;
  }

  public void setBizStatus(Map bizStatus) {
    this.bizStatus = bizStatus;
  }

  public String getBizStatusValue() {
    return bizStatusValue;
  }

  public void setBizStatusValue(String bizStatusValue) {
    this.bizStatusValue = bizStatusValue;
  }

  public Map getBizType() {
    return bizType;
  }

  public void setBizType(Map bizType) {
    this.bizType = bizType;
  }

  public String getBizTypeValue() {
    return bizTypeValue;
  }

  public void setBizTypeValue(String bizTypeValue) {
    this.bizTypeValue = bizTypeValue;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }



  public List getOperatorList() {
    return operatorList;
  }

  public void setOperatorList(List operatorList) {
    this.operatorList = operatorList;
  }

  public String getOperatorValue() {
    return operatorValue;
  }

  public void setOperatorValue(String operatorValue) {
    this.operatorValue = operatorValue;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }
  
}
