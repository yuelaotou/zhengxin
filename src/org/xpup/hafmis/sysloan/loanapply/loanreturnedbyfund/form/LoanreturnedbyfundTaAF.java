package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;

/**
 * MyEclipse Struts Creation date: 09-21-2007 XDoclet definition:
 * 
 * @struts.form name="loanapplynewAF"
 */
public class LoanreturnedbyfundTaAF extends ActionForm {
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

  private String empId = "";

  private String ofic;

  private String sexc;

  private String cardkingc;

  private String marrayst;

  private String degreest;

  private List list = null;

  private String seq[];
  
  private String returnMonth="";

  /** 借款人信息 */
  private Borrower borrower = new Borrower();

  // 新增
  /** 辅助借款人信息 */
  private AssistantBorrower assistantBorrower = new AssistantBorrower();

  // yul add 注释..
  private String yueHuanBenXi = ""; // 月还本息

  private BigDecimal maxPickMoney = new BigDecimal(0); // 最大提取金额.

  private String maxMonth = ""; // 最大还月数.

  private BigDecimal maxPickMoney_assist = new BigDecimal(0);

  private String maxMonth_assist = "";

  private String loanMoney = ""; // 贷款金额.

  private String loanTime = ""; // 贷款期限.

  private String loanYuE = ""; // 未还本金.

  private String shengYuTime = ""; // 剩余期限.

  private String bizTime = ""; // 业务日期.

  private String borrowerMaxMoney = ""; // 借款人的最大金额.

  private String assiMaxMoney = ""; // 配偶的最大金额
  
  private String f1MaxMoney = "";//辅助借款人1最大金额
  
  private String f2MaxMoney = "";//辅助借款人2最大金额

  private String yueJiaoCun = ""; // 月缴存额.

  private String yuE = ""; // ????

  private String xieYinum = ""; // 协议号.

  private String contractId = "";

  private String borrowerName = "";

  private String borrower_cardKind = "";

  private String borrower_cardNum = "";

  private String borrow_empid = "";

  private String borrow_orgname = "";

  private String borrow_orgtel = "";

  private String borrow_orgadder = "";

  private String borrow_monthpay = "";

  private String borrow_monthsal = "";

  private String borrow_ablncc = "";

  private String borrow_bingdate = "";

  private String borrow_enddate = "";

  private String borrow_office = "";

  private String borrow_st = "";

  private String borrow_orgid = "";

  private String name = "";

  private String cardKind = "";

  private String cardNum = "";

  private String namea = "";

  private String cardKinda = "";

  private String cardNuma = "";

  private String nameb = "";

  private String cardKindb = "";

  private String cardNumb = "";

  private String print_status = "";

  private String date_stop = "";

  public String getDate_stop() {
    return date_stop;
  }

  public void setDate_stop(String date_stop) {
    this.date_stop = date_stop;
  }

  public void reset() {
    borrowerName = "";
    borrower_cardKind = "";
    borrower_cardNum = "";
    borrow_empid = "";
    borrow_orgname = "";
    borrow_orgtel = "";
    borrow_orgadder = "";
    borrow_monthpay = "";
    borrow_monthsal = "";
    borrow_ablncc = "";
    borrow_bingdate = "";
    borrow_enddate = "";
    borrow_office = "";
    borrow_st = "";
    borrow_orgid = "";

    name = "";
    cardKind = "";
    cardNum = "";
    borrow_s_empid = "";
    borrow_s_orgname = "";
    borrow_s_orgtel = "";
    borrow_s_orgadder = "";
    borrow_s_monthpay = "";
    borrow_s_monthsal = "";
    borrow_s_ablncc = "";
    borrow_s_bingdate = "";
    borrow_s_enddate = "";
    borrow_s_office = "";
    borrow_s_st = "";
    borrow_s_orgid = "";

    borrowerMaxMoney = ""; // 借款人的最大金额.
    assiMaxMoney = ""; // 配偶的最大金额
    maxMonth = ""; // 最大还月数.
    loanMoney = ""; // 贷款金额.
    loanTime = ""; // 贷款期限.
    loanYuE = ""; // 未还本金.
    shengYuTime = ""; // 剩余期限.
    yueHuanBenXi = ""; // 月还本息
    maxPickMoney = new BigDecimal(0); // 最大提取金额.

  }

  private String borrow_s_orgmail = "";

  private String borrow_s_orgmaila = "";

  private String borrow_s_orgmailb = "";

  private String borrow_s_empid = "";

  private String borrow_s_orgname = "";

  private String borrow_s_orgtel = "";

  private String borrow_s_orgadder = "";

  private String borrow_s_monthpay = "";

  private String borrow_s_monthsal = "";

  private String borrow_s_ablncc = "";

  private String borrow_s_bingdate = "";

  private String borrow_s_enddate = "";

  private String borrow_s_office = "";

  private String borrow_s_st = "";

  private String borrow_s_orgid = "";

  private String borrow_s_empida = "";

  private String borrow_s_orgnamea = "";

  private String borrow_s_orgtela = "";

  private String borrow_s_orgaddera = "";

  private String borrow_s_monthpaya = "";

  private String borrow_s_monthsala = "";

  private String borrow_s_ablncca = "";

  private String borrow_s_bingdatea = "";

  private String borrow_s_enddatea = "";

  private String borrow_s_officea = "";

  private String borrow_s_sta = "";

  private String borrow_s_orgida = "";

  private String borrow_s_empidb = "";

  private String borrow_s_orgnameb = "";

  private String borrow_s_orgtelb = "";

  private String borrow_s_orgadderb = "";

  private String borrow_s_monthpayb = "";

  private String borrow_s_monthsalb = "";

  private String borrow_s_ablnccb = "";

  private String borrow_s_bingdateb = "";

  private String borrow_s_enddateb = "";

  private String borrow_s_officeb = "";

  private String borrow_s_stb = "";

  private String borrow_s_orgidb = "";

  private String borrow_s_empname = "";

  private String borrow_s_empnamea = "";

  private String borrow_s_empnameb = "";

  private String borrow_empname = "";

  private String borrow_s_cardnum = "";

  private String borrow_s_cardnuma = "";

  private String borrow_s_cardnumb = "";

  // 新增

  private Map sexMap = new HashMap();

  private Map cardkingMap = new HashMap();

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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String[] getSeq() {
    return seq;
  }

  public void setSeq(String[] seq) {
    this.seq = seq;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public AssistantBorrower getAssistantBorrower() {
    return assistantBorrower;
  }

  public void setAssistantBorrower(AssistantBorrower assistantBorrower) {
    this.assistantBorrower = assistantBorrower;
  }

  public String getBizTime() {
    return bizTime;
  }

  public void setBizTime(String bizTime) {
    this.bizTime = bizTime;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanTime() {
    return loanTime;
  }

  public void setLoanTime(String loanTime) {
    this.loanTime = loanTime;
  }

  public String getLoanYuE() {
    return loanYuE;
  }

  public void setLoanYuE(String loanYuE) {
    this.loanYuE = loanYuE;
  }

  public String getMaxMonth() {
    return maxMonth;
  }

  public void setMaxMonth(String maxMonth) {
    this.maxMonth = maxMonth;
  }

  public String getShengYuTime() {
    return shengYuTime;
  }

  public void setShengYuTime(String shengYuTime) {
    this.shengYuTime = shengYuTime;
  }

  public String getYueHuanBenXi() {
    return yueHuanBenXi;
  }

  public void setYueHuanBenXi(String yueHuanBenXi) {
    this.yueHuanBenXi = yueHuanBenXi;
  }

  public String getAssiMaxMoney() {
    return assiMaxMoney;
  }

  public void setAssiMaxMoney(String assiMaxMoney) {
    this.assiMaxMoney = assiMaxMoney;
  }

  public String getBorrowerMaxMoney() {
    return borrowerMaxMoney;
  }

  public void setBorrowerMaxMoney(String borrowerMaxMoney) {
    this.borrowerMaxMoney = borrowerMaxMoney;
  }

  public String getYueJiaoCun() {
    return yueJiaoCun;
  }

  public void setYueJiaoCun(String yueJiaoCun) {
    this.yueJiaoCun = yueJiaoCun;
  }

  public String getYuE() {
    return yuE;
  }

  public void setYuE(String yuE) {
    this.yuE = yuE;
  }

  public String getXieYinum() {
    return xieYinum;
  }

  public void setXieYinum(String xieYinum) {
    this.xieYinum = xieYinum;
  }

  public String getBorrower_cardKind() {
    return borrower_cardKind;
  }

  public void setBorrower_cardKind(String borrower_cardKind) {
    this.borrower_cardKind = borrower_cardKind;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBorrower_cardNum() {
    return borrower_cardNum;
  }

  public void setBorrower_cardNum(String borrower_cardNum) {
    this.borrower_cardNum = borrower_cardNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getBorrow_empid() {
    return borrow_empid;
  }

  public void setBorrow_empid(String borrow_empid) {
    this.borrow_empid = borrow_empid;
  }

  public String getBorrow_s_empid() {
    return borrow_s_empid;
  }

  public void setBorrow_s_empid(String borrow_s_empid) {
    this.borrow_s_empid = borrow_s_empid;
  }

  public String getBorrow_ablncc() {
    return borrow_ablncc;
  }

  public void setBorrow_ablncc(String borrow_ablncc) {
    this.borrow_ablncc = borrow_ablncc;
  }

  public String getBorrow_bingdate() {
    return borrow_bingdate;
  }

  public void setBorrow_bingdate(String borrow_bingdate) {
    this.borrow_bingdate = borrow_bingdate;
  }

  public String getBorrow_enddate() {
    return borrow_enddate;
  }

  public void setBorrow_enddate(String borrow_enddate) {
    this.borrow_enddate = borrow_enddate;
  }

  public String getBorrow_monthpay() {
    return borrow_monthpay;
  }

  public void setBorrow_monthpay(String borrow_monthpay) {
    this.borrow_monthpay = borrow_monthpay;
  }

  public String getBorrow_monthsal() {
    return borrow_monthsal;
  }

  public void setBorrow_monthsal(String borrow_monthsal) {
    this.borrow_monthsal = borrow_monthsal;
  }

  public String getBorrow_office() {
    return borrow_office;
  }

  public void setBorrow_office(String borrow_office) {
    this.borrow_office = borrow_office;
  }

  public String getBorrow_orgadder() {
    return borrow_orgadder;
  }

  public void setBorrow_orgadder(String borrow_orgadder) {
    this.borrow_orgadder = borrow_orgadder;
  }

  public String getBorrow_orgname() {
    return borrow_orgname;
  }

  public void setBorrow_orgname(String borrow_orgname) {
    this.borrow_orgname = borrow_orgname;
  }

  public String getBorrow_orgtel() {
    return borrow_orgtel;
  }

  public void setBorrow_orgtel(String borrow_orgtel) {
    this.borrow_orgtel = borrow_orgtel;
  }

  public String getBorrow_s_ablncc() {
    return borrow_s_ablncc;
  }

  public void setBorrow_s_ablncc(String borrow_s_ablncc) {
    this.borrow_s_ablncc = borrow_s_ablncc;
  }

  public String getBorrow_s_bingdate() {
    return borrow_s_bingdate;
  }

  public void setBorrow_s_bingdate(String borrow_s_bingdate) {
    this.borrow_s_bingdate = borrow_s_bingdate;
  }

  public String getBorrow_s_enddate() {
    return borrow_s_enddate;
  }

  public void setBorrow_s_enddate(String borrow_s_enddate) {
    this.borrow_s_enddate = borrow_s_enddate;
  }

  public String getBorrow_s_monthpay() {
    return borrow_s_monthpay;
  }

  public void setBorrow_s_monthpay(String borrow_s_monthpay) {
    this.borrow_s_monthpay = borrow_s_monthpay;
  }

  public String getBorrow_s_monthsal() {
    return borrow_s_monthsal;
  }

  public void setBorrow_s_monthsal(String borrow_s_monthsal) {
    this.borrow_s_monthsal = borrow_s_monthsal;
  }

  public String getBorrow_s_office() {
    return borrow_s_office;
  }

  public void setBorrow_s_office(String borrow_s_office) {
    this.borrow_s_office = borrow_s_office;
  }

  public String getBorrow_s_orgadder() {
    return borrow_s_orgadder;
  }

  public void setBorrow_s_orgadder(String borrow_s_orgadder) {
    this.borrow_s_orgadder = borrow_s_orgadder;
  }

  public String getBorrow_s_orgname() {
    return borrow_s_orgname;
  }

  public void setBorrow_s_orgname(String borrow_s_orgname) {
    this.borrow_s_orgname = borrow_s_orgname;
  }

  public String getBorrow_s_orgtel() {
    return borrow_s_orgtel;
  }

  public void setBorrow_s_orgtel(String borrow_s_orgtel) {
    this.borrow_s_orgtel = borrow_s_orgtel;
  }

  public String getBorrow_s_st() {
    return borrow_s_st;
  }

  public void setBorrow_s_st(String borrow_s_st) {
    this.borrow_s_st = borrow_s_st;
  }

  public String getBorrow_st() {
    return borrow_st;
  }

  public void setBorrow_st(String borrow_st) {
    this.borrow_st = borrow_st;
  }

  public String getBorrow_orgid() {
    return borrow_orgid;
  }

  public void setBorrow_orgid(String borrow_orgid) {
    this.borrow_orgid = borrow_orgid;
  }

  public String getBorrow_s_orgid() {
    return borrow_s_orgid;
  }

  public void setBorrow_s_orgid(String borrow_s_orgid) {
    this.borrow_s_orgid = borrow_s_orgid;
  }

  public BigDecimal getMaxPickMoney() {
    return maxPickMoney;
  }

  public void setMaxPickMoney(BigDecimal maxPickMoney) {
    this.maxPickMoney = maxPickMoney;
  }

  public String getPrint_status() {
    return print_status;
  }

  public void setPrint_status(String print_status) {
    this.print_status = print_status;
  }

  public BigDecimal getMaxPickMoney_assist() {
    return maxPickMoney_assist;
  }

  public void setMaxPickMoney_assist(BigDecimal maxPickMoney_assist) {
    this.maxPickMoney_assist = maxPickMoney_assist;
  }

  public String getMaxMonth_assist() {
    return maxMonth_assist;
  }

  public void setMaxMonth_assist(String maxMonth_assist) {
    this.maxMonth_assist = maxMonth_assist;
  }

  public String getCardKinda() {
    return cardKinda;
  }

  public void setCardKinda(String cardKinda) {
    this.cardKinda = cardKinda;
  }

  public String getCardKindb() {
    return cardKindb;
  }

  public void setCardKindb(String cardKindb) {
    this.cardKindb = cardKindb;
  }

  public String getCardNuma() {
    return cardNuma;
  }

  public void setCardNuma(String cardNuma) {
    this.cardNuma = cardNuma;
  }

  public String getCardNumb() {
    return cardNumb;
  }

  public void setCardNumb(String cardNumb) {
    this.cardNumb = cardNumb;
  }

  public String getNamea() {
    return namea;
  }

  public void setNamea(String namea) {
    this.namea = namea;
  }

  public String getNameb() {
    return nameb;
  }

  public void setNameb(String nameb) {
    this.nameb = nameb;
  }

  public String getBorrow_s_ablncca() {
    return borrow_s_ablncca;
  }

  public void setBorrow_s_ablncca(String borrow_s_ablncca) {
    this.borrow_s_ablncca = borrow_s_ablncca;
  }

  public String getBorrow_s_ablnccb() {
    return borrow_s_ablnccb;
  }

  public void setBorrow_s_ablnccb(String borrow_s_ablnccb) {
    this.borrow_s_ablnccb = borrow_s_ablnccb;
  }

  public String getBorrow_s_bingdatea() {
    return borrow_s_bingdatea;
  }

  public void setBorrow_s_bingdatea(String borrow_s_bingdatea) {
    this.borrow_s_bingdatea = borrow_s_bingdatea;
  }

  public String getBorrow_s_bingdateb() {
    return borrow_s_bingdateb;
  }

  public void setBorrow_s_bingdateb(String borrow_s_bingdateb) {
    this.borrow_s_bingdateb = borrow_s_bingdateb;
  }

  public String getBorrow_s_empida() {
    return borrow_s_empida;
  }

  public void setBorrow_s_empida(String borrow_s_empida) {
    this.borrow_s_empida = borrow_s_empida;
  }

  public String getBorrow_s_empidb() {
    return borrow_s_empidb;
  }

  public void setBorrow_s_empidb(String borrow_s_empidb) {
    this.borrow_s_empidb = borrow_s_empidb;
  }

  public String getBorrow_s_enddatea() {
    return borrow_s_enddatea;
  }

  public void setBorrow_s_enddatea(String borrow_s_enddatea) {
    this.borrow_s_enddatea = borrow_s_enddatea;
  }

  public String getBorrow_s_enddateb() {
    return borrow_s_enddateb;
  }

  public void setBorrow_s_enddateb(String borrow_s_enddateb) {
    this.borrow_s_enddateb = borrow_s_enddateb;
  }

  public String getBorrow_s_monthpaya() {
    return borrow_s_monthpaya;
  }

  public void setBorrow_s_monthpaya(String borrow_s_monthpaya) {
    this.borrow_s_monthpaya = borrow_s_monthpaya;
  }

  public String getBorrow_s_monthpayb() {
    return borrow_s_monthpayb;
  }

  public void setBorrow_s_monthpayb(String borrow_s_monthpayb) {
    this.borrow_s_monthpayb = borrow_s_monthpayb;
  }

  public String getBorrow_s_monthsala() {
    return borrow_s_monthsala;
  }

  public void setBorrow_s_monthsala(String borrow_s_monthsala) {
    this.borrow_s_monthsala = borrow_s_monthsala;
  }

  public String getBorrow_s_monthsalb() {
    return borrow_s_monthsalb;
  }

  public void setBorrow_s_monthsalb(String borrow_s_monthsalb) {
    this.borrow_s_monthsalb = borrow_s_monthsalb;
  }

  public String getBorrow_s_officea() {
    return borrow_s_officea;
  }

  public void setBorrow_s_officea(String borrow_s_officea) {
    this.borrow_s_officea = borrow_s_officea;
  }

  public String getBorrow_s_officeb() {
    return borrow_s_officeb;
  }

  public void setBorrow_s_officeb(String borrow_s_officeb) {
    this.borrow_s_officeb = borrow_s_officeb;
  }

  public String getBorrow_s_orgaddera() {
    return borrow_s_orgaddera;
  }

  public void setBorrow_s_orgaddera(String borrow_s_orgaddera) {
    this.borrow_s_orgaddera = borrow_s_orgaddera;
  }

  public String getBorrow_s_orgadderb() {
    return borrow_s_orgadderb;
  }

  public void setBorrow_s_orgadderb(String borrow_s_orgadderb) {
    this.borrow_s_orgadderb = borrow_s_orgadderb;
  }

  public String getBorrow_s_orgida() {
    return borrow_s_orgida;
  }

  public void setBorrow_s_orgida(String borrow_s_orgida) {
    this.borrow_s_orgida = borrow_s_orgida;
  }

  public String getBorrow_s_orgidb() {
    return borrow_s_orgidb;
  }

  public void setBorrow_s_orgidb(String borrow_s_orgidb) {
    this.borrow_s_orgidb = borrow_s_orgidb;
  }

  public String getBorrow_s_orgnamea() {
    return borrow_s_orgnamea;
  }

  public void setBorrow_s_orgnamea(String borrow_s_orgnamea) {
    this.borrow_s_orgnamea = borrow_s_orgnamea;
  }

  public String getBorrow_s_orgnameb() {
    return borrow_s_orgnameb;
  }

  public void setBorrow_s_orgnameb(String borrow_s_orgnameb) {
    this.borrow_s_orgnameb = borrow_s_orgnameb;
  }

  public String getBorrow_s_orgtela() {
    return borrow_s_orgtela;
  }

  public void setBorrow_s_orgtela(String borrow_s_orgtela) {
    this.borrow_s_orgtela = borrow_s_orgtela;
  }

  public String getBorrow_s_orgtelb() {
    return borrow_s_orgtelb;
  }

  public void setBorrow_s_orgtelb(String borrow_s_orgtelb) {
    this.borrow_s_orgtelb = borrow_s_orgtelb;
  }

  public String getBorrow_s_sta() {
    return borrow_s_sta;
  }

  public void setBorrow_s_sta(String borrow_s_sta) {
    this.borrow_s_sta = borrow_s_sta;
  }

  public String getBorrow_s_stb() {
    return borrow_s_stb;
  }

  public void setBorrow_s_stb(String borrow_s_stb) {
    this.borrow_s_stb = borrow_s_stb;
  }

  public String getBorrow_s_orgmail() {
    return borrow_s_orgmail;
  }

  public void setBorrow_s_orgmail(String borrow_s_orgmail) {
    this.borrow_s_orgmail = borrow_s_orgmail;
  }

  public String getBorrow_s_orgmaila() {
    return borrow_s_orgmaila;
  }

  public void setBorrow_s_orgmaila(String borrow_s_orgmaila) {
    this.borrow_s_orgmaila = borrow_s_orgmaila;
  }

  public String getBorrow_s_orgmailb() {
    return borrow_s_orgmailb;
  }

  public void setBorrow_s_orgmailb(String borrow_s_orgmailb) {
    this.borrow_s_orgmailb = borrow_s_orgmailb;
  }

  public String getBorrow_s_empname() {
    return borrow_s_empname;
  }

  public void setBorrow_s_empname(String borrow_s_empname) {
    this.borrow_s_empname = borrow_s_empname;
  }

  public String getBorrow_s_empnamea() {
    return borrow_s_empnamea;
  }

  public void setBorrow_s_empnamea(String borrow_s_empnamea) {
    this.borrow_s_empnamea = borrow_s_empnamea;
  }

  public String getBorrow_s_empnameb() {
    return borrow_s_empnameb;
  }

  public void setBorrow_s_empnameb(String borrow_s_empnameb) {
    this.borrow_s_empnameb = borrow_s_empnameb;
  }

  public String getBorrow_empname() {
    return borrow_empname;
  }

  public void setBorrow_empname(String borrow_empname) {
    this.borrow_empname = borrow_empname;
  }

  public String getBorrow_s_cardnum() {
    return borrow_s_cardnum;
  }

  public void setBorrow_s_cardnum(String borrow_s_cardnum) {
    this.borrow_s_cardnum = borrow_s_cardnum;
  }

  public String getBorrow_s_cardnuma() {
    return borrow_s_cardnuma;
  }

  public void setBorrow_s_cardnuma(String borrow_s_cardnuma) {
    this.borrow_s_cardnuma = borrow_s_cardnuma;
  }

  public String getBorrow_s_cardnumb() {
    return borrow_s_cardnumb;
  }

  public void setBorrow_s_cardnumb(String borrow_s_cardnumb) {
    this.borrow_s_cardnumb = borrow_s_cardnumb;
  }

  public String getReturnMonth() {
    return returnMonth;
  }

  public void setReturnMonth(String returnMonth) {
    this.returnMonth = returnMonth;
  }

  public String getF1MaxMoney() {
    return f1MaxMoney;
  }

  public void setF1MaxMoney(String maxMoney) {
    f1MaxMoney = maxMoney;
  }

  public String getF2MaxMoney() {
    return f2MaxMoney;
  }

  public void setF2MaxMoney(String maxMoney) {
    f2MaxMoney = maxMoney;
  }

}