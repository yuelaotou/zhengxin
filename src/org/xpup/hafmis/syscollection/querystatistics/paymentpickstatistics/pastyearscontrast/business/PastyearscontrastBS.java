package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonTailDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentPayment;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.bsinterface.IPastyearscontrastBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.dto.PastyearscontrasDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form.PastyearscontrasAF;
import org.xpup.hafmis.syscommon.dao.EmpInfoDAO;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;
import org.xpup.security.common.domain.Userslogincollbank;
/**
 * 
 * @author 于庆丰
 *
 */
public class PastyearscontrastBS implements IPastyearscontrastBS {

  private OrgDAO orgDAO = null;
  private CollBankDAO collBankDAO = null;//bb105
  private OrgInfoDAO orgInfoDAO = null;//ba001
  private EmpInfoDAO empInfoDAO = null;//ba002
  private ChgPaymentHeadDAO chgPaymentHeadDAO = null;//aa202
  private ChgPaymentTailDAO chgPaymentTailDAO = null;//aa203
  private ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;//aa202子类--type缴额调整
  private ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;//aa202子类--type工资基数调整
  private ChgOrgRateDAO chgOrgRateDAO = null;//aa201
  private ChgPersonHeadDAO chgPersonHeadDAO = null;//aa204
  private ChgPersonTailDAO chgPersonTailDAO = null;//aa205
  private EmpDAO empDAO = null;//aa002
  private OrganizationUnitDAO organizationUnitDAO = null;//bb101

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setChgPersonTailDAO(ChgPersonTailDAO chgPersonTailDAO) {
    this.chgPersonTailDAO = chgPersonTailDAO;
  }

  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }
  
  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }

  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }

  public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
    this.empInfoDAO = empInfoDAO;
  }

  public void setOrgInfoDAO(OrgInfoDAO orgInfoDAO) {
    this.orgInfoDAO = orgInfoDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public PastyearscontrasAF queryList(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    PastyearscontrasAF pastyearscontrasAF = new PastyearscontrasAF();
    PastyearscontrasDTO pastyearscontrasDTO = new PastyearscontrasDTO();
     List collBankList=new ArrayList();
     List officeList=new ArrayList();
     collBankList = securityInfo.getCollBankList();
     officeList = securityInfo.getOfficeList();
     pastyearscontrasAF.setCollBankList(collBankList);
     pastyearscontrasAF.setOfficeList(officeList);
     if(!pagination.getQueryCriterions().isEmpty()){
       String office = (String)pagination.getQueryCriterions().get("office");
       String bank = (String)pagination.getQueryCriterions().get("bank");
       String orgCharacter = (String)pagination.getQueryCriterions().get("orgCharacter");
       String dept = (String)pagination.getQueryCriterions().get("dept");
       String ragion = (String)pagination.getQueryCriterions().get("ragion");
       String startDate = (String)pagination.getQueryCriterions().get("startDate");
       String endDate = (String)pagination.getQueryCriterions().get("endDate");
      
       //单位开户
       int orgOpen = orgInfoDAO.queryCountOrgOpen(office, bank, orgCharacter, dept, ragion, startDate, endDate);
    
       pastyearscontrasDTO.setOrgOpen(new Integer(orgOpen).toString());
       //职工开户
       int empOpen = empDAO.queryCountEmpOpen(office, bank, orgCharacter, dept, ragion, startDate, endDate);
    
       pastyearscontrasDTO.setEmpOpen(new Integer(empOpen).toString());
       /*-------------笔数------------*/
       //缴额调整笔数
       int payCount = chgPaymentPaymentDAO.queryCount(office, bank, orgCharacter, dept, ragion, startDate, endDate);
       pastyearscontrasDTO.setPayCount(new Integer(payCount).toString());
    
       //工资基数调整笔数
       int laborageCount = chgPaymentSalaryBaseDAO.queryCount(office, bank, orgCharacter, dept, ragion, startDate, endDate);
       pastyearscontrasDTO.setLaborageCount(new Integer(laborageCount).toString());
     
       //汇缴比例调整笔数
       int rateCount = chgOrgRateDAO.queryCount(office, bank, orgCharacter, dept, ragion, startDate, endDate);
       pastyearscontrasDTO.setRateCount(new Integer(rateCount).toString());
      
       //人员变更笔数
       int empCount = chgPersonHeadDAO.queryCount(office, bank, orgCharacter, dept, ragion, startDate, endDate);
       pastyearscontrasDTO.setEmpCount(new Integer(empCount).toString());
      
       int sumCount = payCount+laborageCount+rateCount+empCount;
       pastyearscontrasDTO.setSumCount(new Integer(sumCount).toString());
     
       
       /*-------------缴额变化------------*/
       //缴额调整--缴额变化
       int payChg = chgPaymentTailDAO.queryCount(office, bank, orgCharacter, dept, ragion, startDate, endDate);
       pastyearscontrasDTO.setPayChg(new Integer(payChg).toString());
    
       //工资基数调整--缴额变化
       int laborageChg = chgPaymentTailDAO.queryCount_(office, bank, orgCharacter, dept, ragion, startDate, endDate);
       pastyearscontrasDTO.setLaborageChg(new Integer(laborageChg).toString());
      
       //汇缴比例调整--缴额变化
       int rateChg = chgOrgRateDAO.querySum(office, bank, orgCharacter, dept, ragion, startDate, endDate);
       pastyearscontrasDTO.setRateChg(new Integer(rateChg).toString());
       
       //人员变更--缴额变化
       int i = chgPersonTailDAO.querySum(office, bank, orgCharacter, dept, ragion, startDate, endDate);
      
       int j = chgPersonTailDAO.querySum_(office, bank, orgCharacter, dept, ragion, startDate, endDate);
      
       int empChg = i-j;
       pastyearscontrasDTO.setEmpChg(new Integer(empChg).toString());
    
       int sumChg = payChg+laborageChg+rateChg+empChg;
       pastyearscontrasDTO.setSumChg(new Integer(sumChg).toString());
       
       pastyearscontrasAF.setPastyearscontrasDTO(pastyearscontrasDTO);
       pastyearscontrasAF.setType("1");
     }
    return pastyearscontrasAF;
  }

  public PastyearscontrasAF fingTree(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    PastyearscontrasAF pastyearscontrasAF = new PastyearscontrasAF();
    String officeCode = pagination.getQueryCriterions().get("officecode").toString();
    List officeList=new ArrayList();
    officeList = securityInfo.getOfficeList();
    List banklist = new ArrayList();
    List ragionlist = new ArrayList();
//    Map ragionNameMap = new HashMap();
    if(officeCode != null && officeCode.length()>0){
        banklist=collBankDAO.getOfficeCollBankList(officeCode);
//此处所在地区已经改为在枚举里取全部，而非根据办事处取        
//        ragionlist = orgInfoDAO.queryRagionList(officeCode);
//        if(ragionlist.size()!=0){
//          //所在地区枚举转换
//          for(int i=0;i<ragionlist.size();i++){
//            String ragion = (String)ragionlist.get(i);
//          String ragionName = BusiTools.getBusiValue(Integer.parseInt(""
//              + ragion), BusiConst.INAREA);
//          ragionNameMap.put(ragion, ragionName);
//          }
//        }
      }
//    pastyearscontrasAF.setRagionMap(ragionNameMap);
    pastyearscontrasAF.setCollBankList(banklist);
    pastyearscontrasAF.setOfficeList(officeList);
    return pastyearscontrasAF;
  }

  public String findBankName(String bankId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CollBank collBank = null;
    collBank = collBankDAO.getCollBankByCollBankid_(bankId);
    return collBank.getCollBankName();
  }

  public String findOfficeName(String officeId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
 
    return organizationUnitDAO.queryOrganizationName_LY(officeId);
   
  }



}
