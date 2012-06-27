package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.hafmis.common.form.CriterionsAF;

public class BusinesslogsearchAF extends CriterionsAF {



  private static final long serialVersionUID = 1L;

  private String beginMonth="";
  
  private String endMonth="";
  
  private String payStatus="";
  
  private String operator="";
    
  private Map bizType = new HashMap();
  
  private Map status=new HashMap(); 
  
  private List userLists=new ArrayList();
  
  private String bizId="";
  
  private String businessType="";
  
  private String bizStatus="";
  
  private String operationTime="";
  
  private String businessOperator="";
  
  private List list=new ArrayList();

  public String getBeginMonth() {
    return beginMonth;
  }

  public void setBeginMonth(String beginMonth) {
    this.beginMonth = beginMonth;
  }

  public Map getBizType() {
    
    return bizType;
  }

  public void setBizType(Map bizType) {
    
    this.bizType = bizType;
  
  }

  public String getEndMonth() {
    return endMonth;
  }

  public void setEndMonth(String endMonth) {
    this.endMonth = endMonth;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getBizId() {
    return bizId;
  }

  public void setBizId(String bizId) {
    this.bizId = bizId;
  }

  public String getBizStatus() {
    return bizStatus;
  }

  public void setBizStatus(String bizStatus) {
    this.bizStatus = bizStatus;
  }

  public String getBusinessOperator() {
    return businessOperator;
  }

  public void setBusinessOperator(String businessOperator) {
    this.businessOperator = businessOperator;
  }

  public String getBusinessType() {
    return businessType;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public String getOperationTime() {
    return operationTime;
  }

  public void setOperationTime(String operationTime) {
    this.operationTime = operationTime;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getStatus() {
    return status;
  }

  public void setStatus(Map status) {
    this.status = status;
  }

  public List getUserLists() {
    return userLists;
  }

  public void setUserList(List userLists) {
    this.userLists = userLists;
  }


}