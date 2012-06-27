package org.xpup.hafmis.sysloan.loanapply.othersloan.form;


import java.util.HashMap;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.common.domain.entity.OthersLoan;
public class OthersLoanShowAF extends ActionForm {

  private static final long serialVersionUID = -7005677950157431557L;
  
  private Map sexMap = new HashMap();

  private Map cardkingMap = new HashMap();
  
  private Map houseTypeMap = new HashMap();
  
  private Map relationMap=new HashMap();
  
  private String org_Id="";
  
  private String empId="";
  
  private String assisEmpId="";
  
  private OthersLoan othersLoan=new OthersLoan();
  public Map getCardkingMap() {
    return cardkingMap;
  }
  public void setCardkingMap(Map cardkingMap) {
    this.cardkingMap = cardkingMap;
  }
  public Map getHouseTypeMap() {
    return houseTypeMap;
  }
  public void setHouseTypeMap(Map houseTypeMap) {
    this.houseTypeMap = houseTypeMap;
  }
  public Map getSexMap() {
    return sexMap;
  }
  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }
  public OthersLoan getOthersLoan() {
    return othersLoan;
  }
  public void setOthersLoan(OthersLoan othersLoan) {
    this.othersLoan = othersLoan;
  }
  public Map getRelationMap() {
    return relationMap;
  }
  public void setRelationMap(Map relationMap) {
    this.relationMap = relationMap;
  }
  public String getOrg_Id() {
    return org_Id;
  }
  public void setOrg_Id(String org_Id) {
    this.org_Id = org_Id;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getAssisEmpId() {
    return assisEmpId;
  }
  public void setAssisEmpId(String assisEmpId) {
    this.assisEmpId = assisEmpId;
  }
}
