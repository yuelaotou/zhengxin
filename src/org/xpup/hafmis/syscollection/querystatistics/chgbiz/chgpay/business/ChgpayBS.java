package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentSalaryBase;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.from.ChgpayListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.from.ChgpayTbListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseTbListAF;

public class ChgpayBS implements IChgpayBS {
  
  protected OrgDAO orgDAO = null;
  
  protected ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;
  
  protected ChgPaymentTailDAO chgPaymentTailDAO = null;
  
  protected ChgPaymentHeadDAO chgPaymentHeadDAO = null;
  
  protected EmpDAO empDAO = null;
  
  protected CollBankDAO collBankDAO = null;
  
  protected OrganizationUnitDAO organizationUnitDAO = null;
  
  
  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
  }

  public ChgpayListAF findChgpayList(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Org org = new Org();
    List orgChgPaymentPayment = null;
    List totalorgChgPaymentPayment = null;
    ChgPaymentPayment chgPaymentPayment = null;
    ChgpayListAF chgpayListAF = new ChgpayListAF();

    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("SecurityInfo");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String type = (String) pagination.getQueryCriterions().get("type");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    String collectionBankId = (String) pagination.getQueryCriterions().get(
        "collectionBankId");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String startChgMonth = (String) pagination.getQueryCriterions().get(
        "startChgMonth");
    String endChgMonth = (String) pagination.getQueryCriterions().get(
        "endChgMonth");
    String startBizDate = (String) pagination.getQueryCriterions().get(
        "startBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String chgStatus = (String) pagination.getQueryCriterions()
        .get("chgStatus");

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    if (type != null && type.equals("1")) {

      if (orgId != null && officecode != null && collectionBankId != null
          && orgName != null && startChgMonth != null && endChgMonth != null
          && startBizDate != null && endBizDate != null && chgStatus != null) {
        try {
          orgChgPaymentPayment = chgPaymentPaymentDAO
              .queryChgPaymentPaymentQueryWuht(orgId, orgName, officecode,
                  collectionBankId, startChgMonth, endChgMonth, startBizDate,
                  endBizDate, chgStatus, orderBy, order, start, pageSize,
                  securityInfo);
          totalorgChgPaymentPayment = chgPaymentPaymentDAO
              .queryChgPaymentPaymentQueryOtherWuht(orgId, orgName, officecode,
                  collectionBankId, startChgMonth, endChgMonth, startBizDate,
                  endBizDate, chgStatus, orderBy, order, start, pageSize,
                  securityInfo);
        } catch (Exception e) {
          e.printStackTrace();
        }

        BigDecimal totalPaySum = new BigDecimal(0.0);
        BigDecimal totalOldPayment = new BigDecimal(0.0);

        if (totalorgChgPaymentPayment != null
            && orgChgPaymentPayment != null) {
          for (int i = 0; i < totalorgChgPaymentPayment.size(); i++) {
            ChgPaymentPayment chgPaymentPaymentlist = (ChgPaymentPayment) totalorgChgPaymentPayment
                .get(i);
            Org org1 = orgDAO.queryByCriterions(chgPaymentPaymentlist
                .getOrg().getId().toString(), null, null, securityInfo);
            // 付云峰修改：查询出的PaySum可能出现空值，需要进行一下判断。
            BigDecimal paySum = null ;
            if (chgPaymentPaymentlist.getPaySum()!=null) {
              paySum = new BigDecimal(chgPaymentPaymentlist
                  .getPaySum().toString());
            }else{
              paySum = new BigDecimal(0.00);
            }           
            BigDecimal oldPayment = new BigDecimal(chgPaymentPaymentlist
                .getOldPayment().toString());
            totalPaySum = totalPaySum.add(paySum);
            totalOldPayment = totalOldPayment.add(oldPayment);
            chgPaymentPaymentlist.setWuhtChgStatus(BusiTools.getBusiValue(
                Integer.parseInt(chgPaymentPaymentlist.getChgStatus()
                    .toString()), BusiConst.CHGTYPESTATUS));
            BigDecimal rate = new BigDecimal(0);
            BigDecimal percentage = new BigDecimal(100);
            rate = paySum.subtract(oldPayment).divide(oldPayment, 4,
                BigDecimal.ROUND_HALF_UP).multiply(percentage).divide(
                new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
            String percentagerate = rate.toString() + "%";
            chgPaymentPaymentlist.setPercentagerate(percentagerate);
            chgPaymentPaymentlist.setOrg(org1);
            // 付云峰修改:归集银行
            CollBank collBankdto=collBankDAO.getCollBankByCollBankid(chgPaymentPaymentlist.getOrg().getOrgInfo().getCollectionBankId());
            chgPaymentPaymentlist.getOrg().getOrgInfo().setCollectionBankId(collBankdto.getCollBankName());
            // 付云峰修改:办事处
            OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(chgPaymentPaymentlist.getOrg().getOrgInfo().getOfficecode());
            chgPaymentPaymentlist.getOrg().getOrgInfo().setOfficecode(organizationUnit.getName());
       

          }

          for (int i = 0; i < orgChgPaymentPayment.size(); i++) {
            ChgPaymentPayment chgPaymentPaymentlist = (ChgPaymentPayment) orgChgPaymentPayment
                .get(i);
            Org org1 = orgDAO.queryByCriterions(chgPaymentPaymentlist
                .getOrg().getId().toString(), null, null, securityInfo);
            chgPaymentPaymentlist.setWuhtChgStatus(BusiTools.getBusiValue(
                Integer.parseInt(chgPaymentPaymentlist.getChgStatus()
                    .toString()), BusiConst.CHGTYPESTATUS));
            // 付云峰修改：查询出的PaySum可能出现空值，需要进行一下判断。
            BigDecimal paySum = null ;
            if (chgPaymentPaymentlist.getPaySum()!=null) {
              paySum = new BigDecimal(chgPaymentPaymentlist
                  .getPaySum().toString());
            }else{
              paySum = new BigDecimal(0.00);
            }   
            BigDecimal oldPayment = new BigDecimal(chgPaymentPaymentlist
                .getOldPayment().toString());
            BigDecimal rate = new BigDecimal(0);
            BigDecimal percentage = new BigDecimal(100);
            rate = paySum.subtract(oldPayment).divide(oldPayment, 4,
                BigDecimal.ROUND_HALF_UP).multiply(percentage).divide(
                new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
            String percentagerate = rate.toString() + "%";

            chgPaymentPaymentlist.setPercentagerate(percentagerate);
            chgPaymentPaymentlist.setOrg(org1);
            
            // 付云峰修改:归集银行
            CollBank collBankdto=collBankDAO.getCollBankByCollBankid(chgPaymentPaymentlist.getOrg().getOrgInfo().getCollectionBankId());
            chgPaymentPaymentlist.getOrg().getOrgInfo().setCollectionBankId(collBankdto.getCollBankName());
            // 付云峰修改:办事处
            OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(chgPaymentPaymentlist.getOrg().getOrgInfo().getOfficecode());
            chgPaymentPaymentlist.getOrg().getOrgInfo().setOfficecode(organizationUnit.getName());
   

          }

          int oldPaymentOrg = chgPaymentPaymentDAO
              .queryChgPaymentPaymentQueryoldPaymentOrgWuht(orgId, orgName,
                  officecode, collectionBankId, startChgMonth, endChgMonth,
                  startBizDate, endBizDate, chgStatus, orderBy, order, start,
                  pageSize, securityInfo).size();
          chgpayListAF.setOldPaymentOrg(oldPaymentOrg);
          chgpayListAF.setTotalPaySum(totalPaySum);
          chgpayListAF.setTotalOldPayment(totalOldPayment);
          int count = totalorgChgPaymentPayment.size();
          pagination.setNrOfElements(count);
        }
      }

      if (chgpayListAF == null) {
        chgpayListAF = new ChgpayListAF();
      }
      chgpayListAF.setList(orgChgPaymentPayment);
      chgpayListAF.setTotalorgChgPaymentPayment(totalorgChgPaymentPayment);

      chgpayListAF.setOrg(org);

      return chgpayListAF;
    }
    return chgpayListAF;

  }


  public ChgpayTbListAF findEmpChgpayList(Pagination pagination) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Org org = new Org();
    List empChgPaymentChgpay = null;
    List totalempChgPaymentChgpay = null;
 
    ChgpayTbListAF chgpayTbListAF = new ChgpayTbListAF();
  Emp emp=new Emp();
   SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions().get("SecurityInfo");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String empId = (String) pagination.getQueryCriterions().get(
        "empId");
    String empName = (String) pagination.getQueryCriterions().get(
        "empName");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String startChgMonth = (String) pagination.getQueryCriterions().get(
        "startChgMonth");
    String endChgMonth = (String) pagination.getQueryCriterions().get(
        "endChgMonth");
    String startBizDate = (String) pagination.getQueryCriterions().get(
        "startBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String type = (String) pagination.getQueryCriterions().get("type");
    String chgPaymentHeadId = (String) pagination.getQueryCriterions().get("chgPaymentHeadId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    if(type!=null && type.equals("1")){
    if (orgId != null && empId != null && empName != null
        && orgName != null && startChgMonth != null && endChgMonth != null
        && startBizDate != null && endBizDate != null ) {
   
      try {
          // 当前页list
          empChgPaymentChgpay = chgPaymentTailDAO
              .queryChgPaymentChgpayChgPaymentTailByCriterionsWuht(orgId,
                  orgName, empId, empName, startChgMonth, endChgMonth,
                  startBizDate, endBizDate, orderBy, order, start, pageSize,
                  securityInfo, chgPaymentHeadId);
          // 全部list
          totalempChgPaymentChgpay = chgPaymentTailDAO
              .queryChgPaymentChgpayChgPaymentTailByCriterionsOtherWuht(orgId,
                  orgName, empId, empName, startChgMonth, endChgMonth,
                  startBizDate, endBizDate, orderBy, order, start, pageSize,
                  securityInfo, chgPaymentHeadId);
        } catch (Exception e) {
          e.printStackTrace();
        }
      
 
      BigDecimal totalPaySum = new BigDecimal(0.0);
      BigDecimal totalOldPayment = new BigDecimal(0.0);
   
      if (totalempChgPaymentChgpay != null) {
        for (int i = 0; i < totalempChgPaymentChgpay.size(); i++) {
          ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) totalempChgPaymentChgpay
              .get(i);
          ChgPaymentHead chgPaymentHead = chgPaymentHeadDAO.queryById(new Integer(chgPaymentTaillist.getChgPaymentHead().getId().toString()));
          chgPaymentTaillist.setChgPaymentHead(chgPaymentHead);
//          emp = empDAO.queryByCriterions(chgPaymentTaillist.getEmpId()
//              .toString(), chgPaymentHead.getOrg().getId().toString());
          chgPaymentTaillist.setEmp(chgPaymentTaillist.getEmp());
   
          BigDecimal oldOrgEmpPay = new BigDecimal(0.0);
          BigDecimal orgEmpPay = new BigDecimal(0.0);
          
          
          oldOrgEmpPay=oldOrgEmpPay.add(chgPaymentTaillist.getOldOrgPay());
          oldOrgEmpPay=oldOrgEmpPay.add(chgPaymentTaillist.getOldEmpPay());
          orgEmpPay=orgEmpPay.add(chgPaymentTaillist.getOrgPay());
          orgEmpPay=orgEmpPay.add(chgPaymentTaillist.getEmpPay());
          
          totalPaySum=totalPaySum.add(orgEmpPay);
          totalOldPayment=totalOldPayment.add(oldOrgEmpPay);
        
          chgPaymentTaillist.setOldOrgEmpPaySum(oldOrgEmpPay);
          chgPaymentTaillist.setOrgEmpPaySum(orgEmpPay);
       
          
        }
        int oldPaymentOrg = chgPaymentTailDAO
        .queryChgPaymentChgpayChgPaymentTailByCriterionsCountWuht(orgId, orgName, empId,
            empName, startChgMonth, endChgMonth, startBizDate,
            endBizDate, orderBy, order, start, pageSize,securityInfo,chgPaymentHeadId).size();
        chgpayTbListAF.setOldPaymentOrg(oldPaymentOrg);
        chgpayTbListAF.setTotalPaySum(totalPaySum);
        chgpayTbListAF.setTotalOldPayment(totalOldPayment);
        int count = totalempChgPaymentChgpay.size();
        pagination.setNrOfElements(count);
      }
    }
      if (chgpayTbListAF == null) {
        chgpayTbListAF = new ChgpayTbListAF();
      }
 
      chgpayTbListAF.setList(empChgPaymentChgpay);
      chgpayTbListAF.setTotalempChgPaymentSalaryBase(totalempChgPaymentChgpay);
      chgpayTbListAF.setOrg(org);

      return chgpayTbListAF;
    }

    return chgpayTbListAF;

  }
  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }
  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }
  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }


}
