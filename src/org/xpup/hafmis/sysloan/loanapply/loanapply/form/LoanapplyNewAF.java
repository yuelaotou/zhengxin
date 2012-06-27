package org.xpup.hafmis.sysloan.loanapply.loanapply.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;

/**
 * MyEclipse Struts Creation date: 09-21-2007 XDoclet definition:
 * 
 * @struts.form name="loanapplynewAF"
 */
public class LoanapplyNewAF extends ActionForm {
  /*
   * Generated fields
   */

  private String org_Id;

  /** type property */
  private String remaind; // 用于判断是否从维护过来的

  private String isNeedDel;// 用于判断是否走过扫描

  private String type;

  private String type1;

  private String type2;

  private String type3;

  private String type4;

  private String empid = "";

  private String ofic;

  private String sexc;

  private String cardkingc;

  private String houseType;// 房屋类型

  private String marrayst;

  private String degreest;

  private BigDecimal zdjzqje = new BigDecimal(0.00);// 最大可支取金额

  private String orgRate = "";

  private String empRate = "";

  /** 借款人信息 */
  private Borrower borrower = new Borrower();

  private Map sexMap = new HashMap();

  private Map cardkingMap = new HashMap();

  /** 房屋类型 * */
  private Map houseTypeMap = new HashMap();

  private Map yesNoMap = new HashMap();

  private Map loanWhereTypeMap = new HashMap();

  /* 打印审批表时候用到 */

  private String operator = "";// 操作员(制单人)

  private String chkPerson = "";// 审核人

  private String vipChkPerson = "";// 审批人

  private String privilege_borrower_id = "";// 特殊借款人

  private String loanType = "";

  private String empId = "";

  private String payBank = "";

  private String payBankAcc = "";
  // 是否拨入个人账户
  private String isPsnalAcc;
  
  /*
   * Generated Methods
   */

  public Map getCardkingMap() {
    return cardkingMap;
  }

  public void setCardkingMap(Map cardkingMap) {
    this.cardkingMap = cardkingMap;
  }

  public Map getSexMap() {
    return sexMap;
  }

  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }

  public Borrower getBorrower() {
    return borrower;
  }

  public void setBorrower(Borrower borrower) {
    this.borrower = borrower;
  }

  /**
   * Method validate
   * 
   * @param mapping
   * @param request
   * @return ActionErrors
   */
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Method reset
   * 
   * @param mapping
   * @param request
   */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
  }

  /**
   * Returns the type.
   * 
   * @return String
   */
  public String getType() {
    return type;
  }

  /**
   * Set the type.
   * 
   * @param type The type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  public String getType1() {
    return type1;
  }

  public void setType1(String type1) {
    this.type1 = type1;
  }

  public String getType2() {
    return type2;
  }

  public void setType2(String type2) {
    this.type2 = type2;
  }

  public String getType3() {
    return type3;
  }

  public void setType3(String type3) {
    this.type3 = type3;
  }

  public String getType4() {
    return type4;
  }

  public void setType4(String type4) {
    this.type4 = type4;
  }

  public void setEmpid(String empid) {
    this.empid = empid;
  }

  public String getCardkingc() {
    return cardkingc;
  }

  public void setCardkingc(String cardkingc) {
    this.cardkingc = cardkingc;
  }

  public String getOfic() {
    return ofic;
  }

  public void setOfic(String ofic) {
    this.ofic = ofic;
  }

  public String getSexc() {
    return sexc;
  }

  public void setSexc(String sexc) {
    this.sexc = sexc;
  }

  public String getDegreest() {
    return degreest;
  }

  public void setDegreest(String degreest) {
    this.degreest = degreest;
  }

  public String getMarrayst() {
    return marrayst;
  }

  public void setMarrayst(String marrayst) {
    this.marrayst = marrayst;
  }

  public String getEmpid() {
    return empid;
  }

  public String getOrg_Id() {
    return org_Id;
  }

  public void setOrg_Id(String org_Id) {
    this.org_Id = org_Id;
  }

  public String getRemaind() {
    return remaind;
  }

  public void setRemaind(String remaind) {
    this.remaind = remaind;
  }

  public String getIsNeedDel() {
    return isNeedDel;
  }

  public void setIsNeedDel(String isNeedDel) {
    this.isNeedDel = isNeedDel;
  }

  public BigDecimal getZdjzqje() {
    return zdjzqje;
  }

  public void setZdjzqje(BigDecimal zdjzqje) {
    this.zdjzqje = zdjzqje;
  }

  public String getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(String orgRate) {
    this.orgRate = orgRate;
  }

  public String getEmpRate() {
    return empRate;
  }

  public void setEmpRate(String empRate) {
    this.empRate = empRate;
  }

  public Map getHouseTypeMap() {
    return houseTypeMap;
  }

  public void setHouseTypeMap(Map houseTypeMap) {
    this.houseTypeMap = houseTypeMap;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public Map getYesNoMap() {
    return yesNoMap;
  }

  public void setYesNoMap(Map yesNoMap) {
    this.yesNoMap = yesNoMap;
  }

  public String getChkPerson() {
    return chkPerson;
  }

  public void setChkPerson(String chkPerson) {
    this.chkPerson = chkPerson;
  }

  public String getVipChkPerson() {
    return vipChkPerson;
  }

  public void setVipChkPerson(String vipChkPerson) {
    this.vipChkPerson = vipChkPerson;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getPrivilege_borrower_id() {
    return privilege_borrower_id;
  }

  public void setPrivilege_borrower_id(String privilege_borrower_id) {
    this.privilege_borrower_id = privilege_borrower_id;
  }

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public Map getLoanWhereTypeMap() {
    return loanWhereTypeMap;
  }

  public void setLoanWhereTypeMap(Map loanWhereTypeMap) {
    this.loanWhereTypeMap = loanWhereTypeMap;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getPayBank() {
    return payBank;
  }

  public void setPayBank(String payBank) {
    this.payBank = payBank;
  }

  public String getPayBankAcc() {
    return payBankAcc;
  }

  public void setPayBankAcc(String payBankAcc) {
    this.payBankAcc = payBankAcc;
  }

  public String getIsPsnalAcc() {
    return isPsnalAcc;
  }

  public void setIsPsnalAcc(String isPsnalAcc) {
    this.isPsnalAcc = isPsnalAcc;
  }
}