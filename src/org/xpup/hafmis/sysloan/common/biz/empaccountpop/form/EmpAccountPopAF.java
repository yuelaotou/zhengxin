/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAccountPopAF
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-11-02
 * 修改日期                   :2007-11-13增加查询条件：职工编号、单位编号
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class EmpAccountPopAF extends CriterionsAF {

  private static final long serialVersionUID = 6561833979666200055L;

  private List list = new ArrayList();

  private String id = null;// AA101.ID

  private String docNum = null;// 凭证编号

  private String bizType = null;// 业务类型

  private String settDateA = null;// 发生时间
  private String settDateB = null;// 发生时间

  private String empId = null;// 职工编号

  private String orgId = null;// 单位编号

  private Map bstypeMap=new HashMap();
  private BigDecimal debit = new BigDecimal(0.00);// 借方发生额

  private BigDecimal credit = new BigDecimal(0.00);// 贷方发生额

  private BigDecimal interest = new BigDecimal(0.00);// 利息

  private BigDecimal debitTotal = new BigDecimal(0.00);// 借方发生额

  private BigDecimal creditTotal = new BigDecimal(0.00);// 贷方发生额

  private BigDecimal interestTotal = new BigDecimal(0.00);// 利息

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getCreditTotal() {
    return creditTotal;
  }

  public void setCreditTotal(BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getDebitTotal() {
    return debitTotal;
  }

  public void setDebitTotal(BigDecimal debitTotal) {
    this.debitTotal = debitTotal;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }



  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.bizType = "";
    this.settDateA = "";
    this.settDateB = "";
  }

  public Map getBstypeMap() {
    return bstypeMap;
  }

  public void setBstypeMap(Map bstypeMap) {
    this.bstypeMap = bstypeMap;
  }

  public String getSettDateA() {
    return settDateA;
  }

  public void setSettDateA(String settDateA) {
    this.settDateA = settDateA;
  }

  public String getSettDateB() {
    return settDateB;
  }

  public void setSettDateB(String settDateB) {
    this.settDateB = settDateB;
  }

}
