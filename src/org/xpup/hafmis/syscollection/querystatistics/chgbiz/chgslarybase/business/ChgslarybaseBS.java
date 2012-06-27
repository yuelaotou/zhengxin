package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentSalaryBase;

import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseTbListAF;

public class ChgslarybaseBS implements IChgslarybaseBS {

  protected ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;

  protected OrgDAO orgDAO = null;

  protected CollBankDAO collBankDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  protected ChgPaymentTailDAO chgPaymentTailDAO = null;

  protected EmpDAO empDAO = null;

  protected ChgPaymentHeadDAO chgPaymentHeadDAO = null;

  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }

  public ChgslarybaseListAF findChgslarybaseList(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Org org = new Org();
    List list = null;
    List printList = new ArrayList();
    Object obj[] = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    ChgslarybaseListAF chgslarybaseListAF = new ChgslarybaseListAF();

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
          list = chgPaymentSalaryBaseDAO
              .querySalaryBaseChgList(orgId, orgName, officecode,
                  collectionBankId, startChgMonth, endChgMonth, startBizDate,
                  endBizDate, chgStatus, orderBy, order, start, pageSize,
                  securityInfo);
          printList = chgPaymentSalaryBaseDAO
              .querySalaryBaseChgListAll(orgId, orgName,
                  officecode, collectionBankId, startChgMonth, endChgMonth,
                  startBizDate, endBizDate, chgStatus, orderBy, order, start,
                  pageSize, securityInfo);
          obj = chgPaymentSalaryBaseDAO
          .querySalaryBaseChgElements(orgId, orgName,
              officecode, collectionBankId, startChgMonth, endChgMonth,
              startBizDate, endBizDate, chgStatus, orderBy, order, start,
              pageSize, securityInfo);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      pagination.setNrOfElements(printList.size());
      chgslarybaseListAF.setList(list);
      chgslarybaseListAF
          .setTotalorgChgPaymentSalaryBase(printList);
      if(obj!=null){
        chgslarybaseListAF.setOldPaymentOrg(Integer.parseInt(obj[0].toString()));
        chgslarybaseListAF.setTotalOldPayment(new BigDecimal(obj[1].toString()));
        chgslarybaseListAF.setTotalPaySum(new BigDecimal(obj[2].toString()));
      }
      return chgslarybaseListAF;
    }
    return chgslarybaseListAF;

  }

  public ChgslarybaseTbListAF findEmpChgslarybaseList(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Org org = new Org();
    List empChgPaymentSalaryBase = null;
    List totalempChgPaymentSalaryBase = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    ChgslarybaseTbListAF chgslarybaseTbListAF = new ChgslarybaseTbListAF();
    Emp emp = new Emp();
    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("SecurityInfo");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String empName = (String) pagination.getQueryCriterions().get("empName");
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
    String chgPaymentHeadId = (String) pagination.getQueryCriterions().get(
        "chgPaymentHeadId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    if (type != null && type.equals("1")) {
      if (orgId != null && empId != null && empName != null && orgName != null
          && startChgMonth != null && endChgMonth != null
          && startBizDate != null && endBizDate != null) {

        try {
          // 当前页list
          empChgPaymentSalaryBase = chgPaymentTailDAO
              .queryChgPaymentTailByCriterionsWuht(orgId, orgName, empId,
                  empName, startChgMonth, endChgMonth, startBizDate,
                  endBizDate, orderBy, order, start, pageSize, securityInfo,
                  chgPaymentHeadId);
          // 全部list
          totalempChgPaymentSalaryBase = chgPaymentTailDAO
              .queryChgPaymentTailByCriterionsOtherWuht(orgId, orgName, empId,
                  empName, startChgMonth, endChgMonth, startBizDate,
                  endBizDate, orderBy, order, start, pageSize, securityInfo,
                  chgPaymentHeadId);
        } catch (Exception e) {
          e.printStackTrace();
        }

        BigDecimal totalPaySum = new BigDecimal(0.0);
        BigDecimal totalOldPayment = new BigDecimal(0.0);

        if (totalempChgPaymentSalaryBase != null) {
          for (int i = 0; i < totalempChgPaymentSalaryBase.size(); i++) {
            ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) totalempChgPaymentSalaryBase
                .get(i);
            ChgPaymentHead chgPaymentHead = chgPaymentHeadDAO
                .queryById(new Integer(chgPaymentTaillist.getChgPaymentHead()
                    .getId().toString()));
            chgPaymentTaillist.setChgPaymentHead(chgPaymentHead);
            // emp = empDAO.queryByCriterions(chgPaymentTaillist.getEmpId()
            // .toString(), chgPaymentHead.getOrg().getId().toString());
            chgPaymentTaillist.setEmp(chgPaymentTaillist.getEmp());
            BigDecimal oldOrgEmpPay = new BigDecimal(0.0);
            BigDecimal orgEmpPay = new BigDecimal(0.0);

            oldOrgEmpPay = oldOrgEmpPay.add(chgPaymentTaillist.getOldOrgPay());
            oldOrgEmpPay = oldOrgEmpPay.add(chgPaymentTaillist.getOldEmpPay());
            orgEmpPay = orgEmpPay.add(chgPaymentTaillist.getOrgPay());
            orgEmpPay = orgEmpPay.add(chgPaymentTaillist.getEmpPay());

            totalPaySum = totalPaySum.add(orgEmpPay);
            totalOldPayment = totalOldPayment.add(oldOrgEmpPay);

            chgPaymentTaillist.setOldOrgEmpPaySum(oldOrgEmpPay);
            chgPaymentTaillist.setOrgEmpPaySum(orgEmpPay);

          }
          int oldPaymentOrg = chgPaymentTailDAO
              .queryChgPaymentTailByCriterionsCountWuht(orgId, orgName, empId,
                  empName, startChgMonth, endChgMonth, startBizDate,
                  endBizDate, orderBy, order, start, pageSize, securityInfo,
                  chgPaymentHeadId).size();
          chgslarybaseTbListAF.setOldPaymentOrg(oldPaymentOrg);
          chgslarybaseTbListAF.setTotalPaySum(totalPaySum);
          chgslarybaseTbListAF.setTotalOldPayment(totalOldPayment);
          int count = totalempChgPaymentSalaryBase.size();
          pagination.setNrOfElements(count);
        }
      }
      if (chgslarybaseTbListAF == null) {
        chgslarybaseTbListAF = new ChgslarybaseTbListAF();
      }
      chgslarybaseTbListAF.setList(empChgPaymentSalaryBase);
      chgslarybaseTbListAF
          .setTotalempChgPaymentSalaryBase(totalempChgPaymentSalaryBase);

      chgslarybaseTbListAF.setOrg(org);

      return chgslarybaseTbListAF;
    }

    return chgslarybaseTbListAF;

  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

}
