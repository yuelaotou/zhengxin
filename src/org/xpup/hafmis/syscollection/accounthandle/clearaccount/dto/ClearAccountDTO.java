package org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ClearAccountDTO implements Serializable{

  private static final long serialVersionUID = 0L;
  private Integer totalPeople=new Integer(0);//合计人数
  private BigDecimal totalMoney=new BigDecimal(0.00);//合计金额
  private BigDecimal totalInterest=new BigDecimal(0.00);//合计利息
  private List list;  //显示列表
  private List list1;
  
//扎账判断
  private BigDecimal minID=new BigDecimal(0.00);//最小业务ID
  private BigDecimal maxID=new BigDecimal(0.00);//最大业务ID
  
  //扎账中按办事处分组记录信息用到
  private String office="";
  private String flowid="";
  
  public String getFlowid() {
    return flowid;
  }
  public void setFlowid(String flowid) {
    this.flowid = flowid;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public List getList1() {
    return list1;
  }
  public void setList1(List list1) {
    this.list1 = list1;
  }
  public BigDecimal getTotalInterest() {
    return totalInterest;
  }
  public void setTotalInterest(BigDecimal totalInterest) {
    this.totalInterest = totalInterest;
  }
  public BigDecimal getTotalMoney() {
    return totalMoney;
  }
  public void setTotalMoney(BigDecimal totalMoney) {
    this.totalMoney = totalMoney;
  }
  public Integer getTotalPeople() {
    return totalPeople;
  }
  public void setTotalPeople(Integer totalPeople) {
    this.totalPeople = totalPeople;
  }
  public BigDecimal getMaxID() {
    return maxID;
  }
  public void setMaxID(BigDecimal maxID) {
    this.maxID = maxID;
  }
  public BigDecimal getMinID() {
    return minID;
  }
  public void setMinID(BigDecimal minID) {
    this.minID = minID;
  }
 
  
}
