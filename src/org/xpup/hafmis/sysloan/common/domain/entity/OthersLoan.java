package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class OthersLoan implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String contractId="";
  private String borrowerName="";
  private String borrowerEmpId="";
  private String borrowerCardtype="";
  

  
  private String BORROWERCARDNUM="";
  private String BORROWERAGE="";
  private String BORROWERSEX="";
  private String BORROWERORGID="";
  private String BORROWERORGNAME="";
  
  
  private String BORROWERORGTEL="";
  private String BORROWERADDR="";
  private String BORROWERHOMETEL="";
  private String BORROWERSALARYBASE="";
  private String BORROWERBALANCE="";
  
  
  private String BORROWERMONTHPAY="";
  private String BORROWERBINDATE="";
  private String BORROWERENDDATE="";
  private String BORROWERHOMEADDR="";
  private String ASSISNAME="";
  
  private String ASSISEMPID="";
  private String ASSISCARDTYPE="";
  private String ASSISCARDNUM="";
  private String ASSISAGE="";
  private String ASSISSEX="";
  
  private String ASSISMOBILE="";
  private String ASSISHOMETEL="";
  private String ASSISORGID="";
  private String ASSISORGNAME="";
  private String ASSISORGTEL="";

  private String ASSISORGADDR="";
  private String ASSISSALARYBASE="";
  private String ASSISBALANCE="";
  private String ASSISMONTHPAY="";
  private String ASSISBINDATE="";

  private String ASSISENDDATE="";
  private String ASSISHOMEADDR="";
  private String BORROWERMOBILE="";
  private String HOUSETYPE="";
  private String HOUSEDEVNAME="";
  
  private String HOUSEDEVTEL="";
  private String HOUSEAREA="";
  private String HOUSETOTALPRICE="";
  private String HOUSESELLERNAME="";
  private String HOUSESELLERTEL="";
  
  
  private String HOUSESELLERTELCARDNUM="";
  private String LOANMONEY="";
  private String LOANTIME="";
  private String LOANCITY="";
  private String OFFICE="";
  private Integer id;
  
  private String BORROWEREMPST="";
  private String BORROWERBIRTHDAY="";
  private String ASSISRELATION="";
  private String ASSISBIRTHDAY="";
  private String ASSISEMPST="";
  private String BORROWERORGADD="";
  private String BORROWERTIQU="";
  private String ASSISTIQU="";
  private String HOUSEADDR="";
  private String price="";
  private Date opTime;
  
  public String getASSISAGE() {
    return ASSISAGE;
  }
  public void setASSISAGE(String assisage) {
    ASSISAGE = assisage;
  }
  public String getASSISBALANCE() {
    return ASSISBALANCE;
  }
  public void setASSISBALANCE(String assisbalance) {
    ASSISBALANCE = assisbalance;
  }
  public String getASSISBINDATE() {
    return ASSISBINDATE;
  }
  public void setASSISBINDATE(String assisbindate) {
    ASSISBINDATE = assisbindate;
  }
  public String getASSISCARDNUM() {
    return ASSISCARDNUM;
  }
  public void setASSISCARDNUM(String assiscardnum) {
    ASSISCARDNUM = assiscardnum;
  }
  public String getASSISCARDTYPE() {
    return ASSISCARDTYPE;
  }
  public void setASSISCARDTYPE(String assiscardtype) {
    ASSISCARDTYPE = assiscardtype;
  }
  public String getASSISEMPID() {
    return ASSISEMPID;
  }
  public void setASSISEMPID(String assisempid) {
    ASSISEMPID = assisempid;
  }
  public String getASSISENDDATE() {
    return ASSISENDDATE;
  }
  public void setASSISENDDATE(String assisenddate) {
    ASSISENDDATE = assisenddate;
  }
  public String getASSISHOMEADDR() {
    return ASSISHOMEADDR;
  }
  public void setASSISHOMEADDR(String assishomeaddr) {
    ASSISHOMEADDR = assishomeaddr;
  }
  public String getASSISHOMETEL() {
    return ASSISHOMETEL;
  }
  public void setASSISHOMETEL(String assishometel) {
    ASSISHOMETEL = assishometel;
  }
  public String getASSISMOBILE() {
    return ASSISMOBILE;
  }
  public void setASSISMOBILE(String assismobile) {
    ASSISMOBILE = assismobile;
  }
  public String getASSISMONTHPAY() {
    return ASSISMONTHPAY;
  }
  public void setASSISMONTHPAY(String assismonthpay) {
    ASSISMONTHPAY = assismonthpay;
  }
  public String getASSISNAME() {
    return ASSISNAME;
  }
  public void setASSISNAME(String assisname) {
    ASSISNAME = assisname;
  }
  public String getASSISORGADDR() {
    return ASSISORGADDR;
  }
  public void setASSISORGADDR(String assisorgaddr) {
    ASSISORGADDR = assisorgaddr;
  }
  public String getASSISORGID() {
    return ASSISORGID;
  }
  public void setASSISORGID(String assisorgid) {
    ASSISORGID = assisorgid;
  }
  public String getASSISORGNAME() {
    return ASSISORGNAME;
  }
  public void setASSISORGNAME(String assisorgname) {
    ASSISORGNAME = assisorgname;
  }
  public String getASSISORGTEL() {
    return ASSISORGTEL;
  }
  public void setASSISORGTEL(String assisorgtel) {
    ASSISORGTEL = assisorgtel;
  }
  public String getASSISSALARYBASE() {
    return ASSISSALARYBASE;
  }
  public void setASSISSALARYBASE(String assissalarybase) {
    ASSISSALARYBASE = assissalarybase;
  }
  public String getASSISSEX() {
    return ASSISSEX;
  }
  public void setASSISSEX(String assissex) {
    ASSISSEX = assissex;
  }
  public String getBORROWERADDR() {
    return BORROWERADDR;
  }
  public void setBORROWERADDR(String borroweraddr) {
    BORROWERADDR = borroweraddr;
  }
  public String getBORROWERAGE() {
    return BORROWERAGE;
  }
  public void setBORROWERAGE(String borrowerage) {
    BORROWERAGE = borrowerage;
  }
  public String getBORROWERBALANCE() {
    return BORROWERBALANCE;
  }
  public void setBORROWERBALANCE(String borrowerbalance) {
    BORROWERBALANCE = borrowerbalance;
  }
  public String getBORROWERBINDATE() {
    return BORROWERBINDATE;
  }
  public void setBORROWERBINDATE(String borrowerbindate) {
    BORROWERBINDATE = borrowerbindate;
  }
  public String getBORROWERCARDNUM() {
    return BORROWERCARDNUM;
  }
  public void setBORROWERCARDNUM(String borrowercardnum) {
    BORROWERCARDNUM = borrowercardnum;
  }
  public String getBorrowerCardtype() {
    return borrowerCardtype;
  }
  public void setBorrowerCardtype(String borrowerCardtype) {
    this.borrowerCardtype = borrowerCardtype;
  }
  public String getBorrowerEmpId() {
    return borrowerEmpId;
  }
  public void setBorrowerEmpId(String borrowerEmpId) {
    this.borrowerEmpId = borrowerEmpId;
  }
  public String getBORROWERENDDATE() {
    return BORROWERENDDATE;
  }
  public void setBORROWERENDDATE(String borrowerenddate) {
    BORROWERENDDATE = borrowerenddate;
  }
  public String getBORROWERHOMEADDR() {
    return BORROWERHOMEADDR;
  }
  public void setBORROWERHOMEADDR(String borrowerhomeaddr) {
    BORROWERHOMEADDR = borrowerhomeaddr;
  }
  public String getBORROWERHOMETEL() {
    return BORROWERHOMETEL;
  }
  public void setBORROWERHOMETEL(String borrowerhometel) {
    BORROWERHOMETEL = borrowerhometel;
  }
  public String getBORROWERMOBILE() {
    return BORROWERMOBILE;
  }
  public void setBORROWERMOBILE(String borrowermobile) {
    BORROWERMOBILE = borrowermobile;
  }
  public String getBORROWERMONTHPAY() {
    return BORROWERMONTHPAY;
  }
  public void setBORROWERMONTHPAY(String borrowermonthpay) {
    BORROWERMONTHPAY = borrowermonthpay;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getBORROWERORGID() {
    return BORROWERORGID;
  }
  public void setBORROWERORGID(String borrowerorgid) {
    BORROWERORGID = borrowerorgid;
  }
  public String getBORROWERORGNAME() {
    return BORROWERORGNAME;
  }
  public void setBORROWERORGNAME(String borrowerorgname) {
    BORROWERORGNAME = borrowerorgname;
  }
  public String getBORROWERORGTEL() {
    return BORROWERORGTEL;
  }
  public void setBORROWERORGTEL(String borrowerorgtel) {
    BORROWERORGTEL = borrowerorgtel;
  }
  public String getBORROWERSALARYBASE() {
    return BORROWERSALARYBASE;
  }
  public void setBORROWERSALARYBASE(String borrowersalarybase) {
    BORROWERSALARYBASE = borrowersalarybase;
  }
  public String getBORROWERSEX() {
    return BORROWERSEX;
  }
  public void setBORROWERSEX(String borrowersex) {
    BORROWERSEX = borrowersex;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getHOUSEAREA() {
    return HOUSEAREA;
  }
  public void setHOUSEAREA(String housearea) {
    HOUSEAREA = housearea;
  }
  public String getHOUSEDEVNAME() {
    return HOUSEDEVNAME;
  }
  public void setHOUSEDEVNAME(String housedevname) {
    HOUSEDEVNAME = housedevname;
  }
  public String getHOUSEDEVTEL() {
    return HOUSEDEVTEL;
  }
  public void setHOUSEDEVTEL(String housedevtel) {
    HOUSEDEVTEL = housedevtel;
  }
  public String getHOUSESELLERNAME() {
    return HOUSESELLERNAME;
  }
  public void setHOUSESELLERNAME(String housesellername) {
    HOUSESELLERNAME = housesellername;
  }
  public String getHOUSESELLERTEL() {
    return HOUSESELLERTEL;
  }
  public void setHOUSESELLERTEL(String housesellertel) {
    HOUSESELLERTEL = housesellertel;
  }
  public String getHOUSESELLERTELCARDNUM() {
    return HOUSESELLERTELCARDNUM;
  }
  public void setHOUSESELLERTELCARDNUM(String housesellertelcardnum) {
    HOUSESELLERTELCARDNUM = housesellertelcardnum;
  }
  public String getHOUSETOTALPRICE() {
    return HOUSETOTALPRICE;
  }
  public void setHOUSETOTALPRICE(String housetotalprice) {
    HOUSETOTALPRICE = housetotalprice;
  }
  public String getHOUSETYPE() {
    return HOUSETYPE;
  }
  public void setHOUSETYPE(String housetype) {
    HOUSETYPE = housetype;
  }
  public String getLOANCITY() {
    return LOANCITY;
  }
  public void setLOANCITY(String loancity) {
    LOANCITY = loancity;
  }
  public String getLOANMONEY() {
    return LOANMONEY;
  }
  public void setLOANMONEY(String loanmoney) {
    LOANMONEY = loanmoney;
  }
  public String getLOANTIME() {
    return LOANTIME;
  }
  public void setLOANTIME(String loantime) {
    LOANTIME = loantime;
  }
  public String getOFFICE() {
    return OFFICE;
  }
  public void setOFFICE(String office) {
    OFFICE = office;
  }
  public String getBORROWEREMPST() {
    return BORROWEREMPST;
  }
  public void setBORROWEREMPST(String borrowerempst) {
    BORROWEREMPST = borrowerempst;
  }
  public String getASSISBIRTHDAY() {
    return ASSISBIRTHDAY;
  }
  public void setASSISBIRTHDAY(String assisbirthday) {
    ASSISBIRTHDAY = assisbirthday;
  }
  public String getASSISRELATION() {
    return ASSISRELATION;
  }
  public void setASSISRELATION(String assisrelation) {
    ASSISRELATION = assisrelation;
  }
  public String getBORROWERBIRTHDAY() {
    return BORROWERBIRTHDAY;
  }
  public void setBORROWERBIRTHDAY(String borrowerbirthday) {
    BORROWERBIRTHDAY = borrowerbirthday;
  }
  public String getASSISEMPST() {
    return ASSISEMPST;
  }
  public void setASSISEMPST(String assisempst) {
    ASSISEMPST = assisempst;
  }
  public String getBORROWERORGADD() {
    return BORROWERORGADD;
  }
  public void setBORROWERORGADD(String borrowerorgadd) {
    BORROWERORGADD = borrowerorgadd;
  }
  public String getASSISTIQU() {
    return ASSISTIQU;
  }
  public void setASSISTIQU(String assistiqu) {
    ASSISTIQU = assistiqu;
  }
  public String getBORROWERTIQU() {
    return BORROWERTIQU;
  }
  public void setBORROWERTIQU(String borrowertiqu) {
    BORROWERTIQU = borrowertiqu;
  }
  public String getHOUSEADDR() {
    return HOUSEADDR;
  }
  public void setHOUSEADDR(String houseaddr) {
    HOUSEADDR = houseaddr;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }
  public Date getOpTime() {
    return opTime;
  }
  public void setOpTime(Date opTime) {
    this.opTime = opTime;
  }
}
