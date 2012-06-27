package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.business;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindResultDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollinfoSumDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpAccountDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpPopDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonOrgAccountDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;

public class CollFnComparisonBS implements ICollFnComparisonBS {

  private EmpDAO empDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private OrgDAO orgDAO = null;

  protected CollBankDAO collBankDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public Object[] findCollFnComparisonEmpInfo(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {

    Object[] obj = new Object[2];
    String empId = "";
    String empName = "";
    String cardNum = "";

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orgId = (String) pagination.getQueryCriterions().get("orgId");

    if (pagination.getQueryCriterions().get("empId") != null) {
      empId = (String) pagination.getQueryCriterions().get("empId");
    }
    if (pagination.getQueryCriterions().get("empName") != null) {
      empName = (String) pagination.getQueryCriterions().get("empName");
    }
    if (pagination.getQueryCriterions().get("cardNum") != null) {
      cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    }

    List list = empDAO.queryCollFnComparisonEmpInfoList(empId, empName,
        cardNum, orgId, orderBy, order, start, pageSize, securityInfo);
    for (int i = 0; i < list.size(); i++) {
      CollFnComparisonEmpInfoDTO collFnComparisonEmpInfoDTO = (CollFnComparisonEmpInfoDTO) list
          .get(i);
      String paystatus = collFnComparisonEmpInfoDTO.getPaystatus();
      try {
        collFnComparisonEmpInfoDTO.setPaystatus(BusiTools.getBusiValue(
            new Integer(paystatus).intValue(), BusiConst.OLDPAYMENTSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    List countList = empDAO.queryCollFnComparisonEmpInfoCount(empId, empName,
        cardNum, orgId, securityInfo);
    pagination.setNrOfElements(countList.size());
    obj[0] = list;
    obj[1] = countList;
    return obj;
  }

  public Object[] findCollFnComparisonEmpAccount(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {

    Object[] obj = new Object[2];

    String orgId = "";
    String orgName = "";
    String empId = "";
    String empName = "";
    String cardNum = "";
    String timeSt = "";
    String timeEnd = "";

    BigDecimal moneysum = new BigDecimal(0.00);
    BigDecimal debit = new BigDecimal(0.00);
    BigDecimal credit = new BigDecimal(0.00);
    String aspect = "";
    BigDecimal moneysum_p = new BigDecimal(0.00);
    BigDecimal debit_p = new BigDecimal(0.00);
    BigDecimal credit_p = new BigDecimal(0.00);
    String aspect_p = "";
    String date = "";

    List qcyelist = new ArrayList();
    
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    if (pagination.getQueryCriterions().get("orgId") != null) {
      orgId = (String) pagination.getQueryCriterions().get("orgId");
    }
    if (pagination.getQueryCriterions().get("empId") != null) {
      empId = (String) pagination.getQueryCriterions().get("empId");
    }
    if (pagination.getQueryCriterions().get("orgName") != null) {
      orgName = (String) pagination.getQueryCriterions().get("orgName");
    }
    if (pagination.getQueryCriterions().get("empName") != null) {
      empName = (String) pagination.getQueryCriterions().get("empName");
    }
    if (pagination.getQueryCriterions().get("cardNum") != null) {
      cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    }
    if (pagination.getQueryCriterions().get("timeSt") != null) {
      timeSt = (String) pagination.getQueryCriterions().get("timeSt");
    }
    if (pagination.getQueryCriterions().get("timeEnd") != null) {
      timeEnd = (String) pagination.getQueryCriterions().get("timeEnd");
    }

    if (pagination.getQueryCriterions().get("qcyelist") != null) {
      qcyelist = (List) pagination.getQueryCriterions().get("qcyelist");
    }
    // 查询余额
    BigDecimal qcye = new BigDecimal(0.00);
    List list = empDAO.queryCollFnComparisonEmpAccountList(orgId, empId,
        orgName, empName, cardNum, timeSt, timeEnd, orderBy, order, start,
        pageSize);
    List countList = empDAO.queryCollFnComparisonEmpAccountCount(orgId, empId,
        orgName, empName, cardNum, timeSt, timeEnd);
    for (int i = 0; i < list.size(); i++) {
      CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO = (CollFnComparisonEmpAccountDTO) list
          .get(i);

      if (i == 0 && page == 1) {
        if (timeSt == null || timeSt.equals("")) {
          date = collFnComparisonEmpAccountDTO.getCollDate();
        }
        qcye = orgHAFAccountFlowDAO.queryqcyeTrail(orgId, empId, date);
        moneysum = moneysum.add(qcye);
      }
      if(i==0&&page>1){
//        moneysum=(BigDecimal)pagination.getQueryCriterions().get("ye");
//        aspect=(String)pagination.getQueryCriterions().get("fx");
        CollFnComparisonEmpAccountDTO dto = (CollFnComparisonEmpAccountDTO) qcyelist.get((page-1)*10-1);
        moneysum = dto.getMoneySum();
      }
      if (i > 0) {
        CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO1 = (CollFnComparisonEmpAccountDTO) countList
            .get((page - 1) * 10 + i - 1);
        if (!collFnComparisonEmpAccountDTO.getCollDate().substring(0, 4)
            .equals(
                collFnComparisonEmpAccountDTO1.getCollDate().substring(0, 4))) {
          qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
              collFnComparisonEmpAccountDTO.getOrgId(),
              collFnComparisonEmpAccountDTO.getEmpId(),
              collFnComparisonEmpAccountDTO.getCollDate());
          moneysum = qcye;
        }
      }
      debit = collFnComparisonEmpAccountDTO.getDebit();
      credit = collFnComparisonEmpAccountDTO.getCredit();
      aspect = this.getDirtection(moneysum);
      if (aspect.equals("借")) {
        if (credit.compareTo(new BigDecimal(0)) > 0
            && (credit.compareTo(moneysum.abs())) > 0) {
          moneysum = moneysum.add(credit).abs();
        } else {
          moneysum = moneysum.add(debit).subtract(credit);
        }
      } else {
        moneysum = moneysum.add(credit).subtract(debit);
      }
      aspect = this.getDirtection(moneysum);
      collFnComparisonEmpAccountDTO.setMoneySum(moneysum.abs());
      collFnComparisonEmpAccountDTO.setDirection(aspect);
      pagination.getQueryCriterions().put("ye", moneysum);
      pagination.getQueryCriterions().put("fx", aspect);
      // debit=collFnComparisonEmpAccountDTO.getDebit();
      // credit=collFnComparisonEmpAccountDTO.getCredit();
      // aspect=this.getDirtection(moneysum);
      //      
      // if(aspect.equals("借")){
      // moneysum=moneysum.add(debit).subtract(credit);
      // }else{
      // moneysum=moneysum.add(credit).subtract(debit);
      // }
      // aspect=this.getDirtection(moneysum);
      // collFnComparisonEmpAccountDTO.setMoneySum(moneysum.abs());
      // collFnComparisonEmpAccountDTO.setDirection(aspect);

      if (collFnComparisonEmpAccountDTO.getBizType() != null
          && !collFnComparisonEmpAccountDTO.getBizType().equals("")) {
        collFnComparisonEmpAccountDTO.setBizType(BusiTools.getBusiValue_WL(
            collFnComparisonEmpAccountDTO.getBizType(),
            BusiConst.CLEARACCOUNTBUSINESSTYPE));
      }
      if (collFnComparisonEmpAccountDTO.getBizStatus() != null
          && !collFnComparisonEmpAccountDTO.getBizStatus().equals("")) {
        collFnComparisonEmpAccountDTO.setBizStatus(BusiTools.getBusiValue(
            new Integer(collFnComparisonEmpAccountDTO.getBizStatus())
                .intValue(), BusiConst.BUSINESSSTATE));
      }
      if (collFnComparisonEmpAccountDTO.getBizStatus() != null
          && !collFnComparisonEmpAccountDTO.getBizStatus().equals("")
          && collFnComparisonEmpAccountDTO.getCaiwStatus() != null
          && !collFnComparisonEmpAccountDTO.getCaiwStatus().equals("")
          && collFnComparisonEmpAccountDTO.getBizStatus().equals("入账")
          && collFnComparisonEmpAccountDTO.getCaiwStatus().equals("2")) {
        collFnComparisonEmpAccountDTO.setType("");
      } else {
        collFnComparisonEmpAccountDTO.setType("0");
      }
      if (collFnComparisonEmpAccountDTO.getCredenceSt() != null
          && !collFnComparisonEmpAccountDTO.getCredenceSt().equals("")) {
        collFnComparisonEmpAccountDTO.setCredenceSt(BusiTools.getBusiValue(
            Integer.parseInt(collFnComparisonEmpAccountDTO.getCredenceSt()),
            BusiConst.CREDSTATE));
      }

    }

    for (int i = 0; i < countList.size(); i++) {
      CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO = (CollFnComparisonEmpAccountDTO) countList
          .get(i);

      if (i == 0) {
        if (timeSt == null || timeSt.equals("")) {
          date = collFnComparisonEmpAccountDTO.getCollDate();
        }
        qcye = orgHAFAccountFlowDAO.queryqcyeTrail(orgId, empId, date);
        moneysum = moneysum.add(qcye);
      }
      debit = collFnComparisonEmpAccountDTO.getDebit();
      credit = collFnComparisonEmpAccountDTO.getCredit();
      aspect = this.getDirtection(moneysum);

      if (aspect.equals("借")) {
        moneysum = moneysum.add(debit).subtract(credit);
      } else {
        moneysum = moneysum.add(credit).subtract(debit);
      }
      aspect = this.getDirtection(moneysum);
      collFnComparisonEmpAccountDTO.setMoneySum(moneysum.abs());
      collFnComparisonEmpAccountDTO.setDirection(aspect);

      if (collFnComparisonEmpAccountDTO.getBizType() != null
          && !collFnComparisonEmpAccountDTO.getBizType().equals("")) {
        collFnComparisonEmpAccountDTO.setBizType(BusiTools.getBusiValue_WL(
            collFnComparisonEmpAccountDTO.getBizType(),
            BusiConst.CLEARACCOUNTBUSINESSTYPE));
      }
      if (collFnComparisonEmpAccountDTO.getBizStatus() != null
          && !collFnComparisonEmpAccountDTO.getBizStatus().equals("")) {
        collFnComparisonEmpAccountDTO.setBizStatus(BusiTools.getBusiValue(
            new Integer(collFnComparisonEmpAccountDTO.getBizStatus())
                .intValue(), BusiConst.BUSINESSSTATE));
      } 
      if (collFnComparisonEmpAccountDTO.getBizStatus() != null
          && !collFnComparisonEmpAccountDTO.getBizStatus().equals("")
          && collFnComparisonEmpAccountDTO.getCaiwStatus() != null
          && !collFnComparisonEmpAccountDTO.getCaiwStatus().equals("")
          && collFnComparisonEmpAccountDTO.getBizStatus().equals("入账")
          && collFnComparisonEmpAccountDTO.getCaiwStatus().equals("2")) {
        collFnComparisonEmpAccountDTO.setType("");
      } else {
        collFnComparisonEmpAccountDTO.setType("0");
      }
      if (collFnComparisonEmpAccountDTO.getCredenceSt() != null
          && !collFnComparisonEmpAccountDTO.getCredenceSt().equals("")) {
        collFnComparisonEmpAccountDTO.setCredenceSt(BusiTools.getBusiValue(
            Integer.parseInt(collFnComparisonEmpAccountDTO.getCredenceSt()),
            BusiConst.CREDSTATE));
      } 
    }
    pagination.setNrOfElements(countList.size());
    obj[0] = list;
    obj[1] = countList;

    return obj;
  }

  public List queryEmpInfoPop(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");

    List list = empDAO.queryComparisonEmpInfoPopList(orgId, docNum, orderBy,
        order, start, pageSize);
    for (int i = 0; i < list.size(); i++) {
      CollFnComparisonEmpPopDTO collFnComparisonEmpPopDTO = (CollFnComparisonEmpPopDTO) list
          .get(i);
      if (collFnComparisonEmpPopDTO.getDebit().compareTo(new BigDecimal(0.00)) > 0) {
        collFnComparisonEmpPopDTO
            .setOccur(collFnComparisonEmpPopDTO.getDebit());
        collFnComparisonEmpPopDTO.setDirection("借");
      } else if (collFnComparisonEmpPopDTO.getCredit().compareTo(
          new BigDecimal(0.00)) > 0) {
        collFnComparisonEmpPopDTO.setOccur(collFnComparisonEmpPopDTO
            .getCredit());
        collFnComparisonEmpPopDTO.setDirection("贷");
      } else if (collFnComparisonEmpPopDTO.getDebit().compareTo(
          new BigDecimal(0.00)) == 0
          && collFnComparisonEmpPopDTO.getCredit().compareTo(
              new BigDecimal(0.00)) == 0) {
        collFnComparisonEmpPopDTO.setOccur(new BigDecimal(0.00));
        collFnComparisonEmpPopDTO.setDirection("平");
      }
    }
    List countList = empDAO.queryComparisonEmpInfoPopCount(orgId, docNum);

    pagination.setNrOfElements(countList.size());
    return list;
  }

  public String findOrgName(String orgId) throws Exception {
    String orgName = empDAO.queryComparisonOrgName(orgId);
    return orgName;
  }

  // 查询单位账列表信息
  public Object[] findCollectionuseinfo(SecurityInfo securityInfo,
      Pagination pagination, String orgId_1) throws Exception,
      BusinessException {
    Object obj[] = new Object[2];

    try {
      BigDecimal moneysum = new BigDecimal(0.00);
      BigDecimal debit = new BigDecimal(0.00);
      BigDecimal credit = new BigDecimal(0.00);
      String aspect = "";
      String officeCode = (String) pagination.getQueryCriterions().get(
          "officeCode");
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String timeSt = (String) pagination.getQueryCriterions().get("timeSt");
      String timeEnd = (String) pagination.getQueryCriterions().get("timeEnd");
      List qcyelist = (List) pagination.getQueryCriterions().get("qcyelist");

      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List list = new ArrayList();
      BigDecimal qcye = new BigDecimal(0.00);
      list = orgHAFAccountFlowDAO.queryCollFnComparisonList(officeCode, bankId,
          orgId, orgId_1, orgName, timeSt, timeEnd, securityInfo, orderBy,
          order, start, pageSize, page);
      List countList = orgHAFAccountFlowDAO.queryCollFnComparisonCountList(
          officeCode, bankId, orgId, orgId_1, orgName, timeSt, timeEnd,
          securityInfo, null, null);
      for (int i = 0; i < list.size(); i++) {
        CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO = (CollFnComparisonOrgAccountDTO) list
            .get(i);
        if (i == 0 && page == 1) {
          String time = timeSt;
          if (timeSt == null || timeSt.equals("")) {
            time = collFnComparisonOrgAccountDTO.getCollsett_date();
          }
          qcye = orgHAFAccountFlowDAO.queryqcye(collFnComparisonOrgAccountDTO
              .getOrg_id(), time);
          moneysum = qcye;
        }
        if(i==0&&page>1){
//          moneysum=(BigDecimal)pagination.getQueryCriterions().get("ye");
//          aspect=(String)pagination.getQueryCriterions().get("fx");
          CollFnComparisonOrgAccountDTO dto = (CollFnComparisonOrgAccountDTO) qcyelist.get((page-1)*10-1);
          moneysum = dto.getMoneySum();
        }
        if (i > 0) {
          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO1 = (CollFnComparisonOrgAccountDTO) countList
              .get((page - 1) * 10 + i - 1);
          if (!collFnComparisonOrgAccountDTO.getCollsett_date().substring(0, 4)
              .equals(
                  collFnComparisonOrgAccountDTO1.getCollsett_date().substring(
                      0, 4))) {
            qcye = orgHAFAccountFlowDAO.queryqcye(collFnComparisonOrgAccountDTO
                .getOrg_id(), collFnComparisonOrgAccountDTO.getCollsett_date());
            moneysum = qcye;
          }
        }
        debit = collFnComparisonOrgAccountDTO.getDebit();
        credit = collFnComparisonOrgAccountDTO.getCredit();
        aspect = this.getDirtection(moneysum);
        if (aspect.equals("借")) {
          if (credit.compareTo(new BigDecimal(0)) > 0
              && (credit.compareTo(moneysum.abs())) > 0) {
            moneysum = moneysum.add(credit).abs();
          } else {
            moneysum = moneysum.add(debit).subtract(credit);
          }
        } else {
          moneysum = moneysum.add(credit).subtract(debit);
        }
        aspect = this.getDirtection(moneysum);
        collFnComparisonOrgAccountDTO.setMoneySum(moneysum.abs());
        collFnComparisonOrgAccountDTO.setAspect(aspect);
        pagination.getQueryCriterions().put("ye", moneysum);
        pagination.getQueryCriterions().put("fx", aspect);

        collFnComparisonOrgAccountDTO.setTemp_collSt(BusiTools.getBusiValue_WL(
            collFnComparisonOrgAccountDTO.getBiz_type(),
            BusiConst.CLEARACCOUNTBUSINESSTYPE));

        if (collFnComparisonOrgAccountDTO.getBiz_status() != null
            && !collFnComparisonOrgAccountDTO.getBiz_status().equals("")
            && collFnComparisonOrgAccountDTO.getCaiw_type() != null
            && !collFnComparisonOrgAccountDTO.getCaiw_type().equals("")
            && collFnComparisonOrgAccountDTO.getBiz_status().equals("5")
            && collFnComparisonOrgAccountDTO.getCaiw_type().equals("2")) {
          collFnComparisonOrgAccountDTO.setType("");
        } else {
          collFnComparisonOrgAccountDTO.setType("0");
        }
      }
      int count = countList.size();
      pagination.setNrOfElements(count);
      obj[0] = list;
      obj[1] = countList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * 根据单位编号、单位名称、单位状态查询单位信息 单位弹出框查询
   */
  public List findOrgpopList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List orgpopList = null;
    Integer temp_id = null;
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    if (orgid != null && !orgid.equals("")) {
      temp_id = new Integer(orgid.trim());
      orgid = temp_id.toString();
    }
    String orgname = (String) pagination.getQueryCriterions().get("orgname");
    if (orgname != null && !orgname.equals("")) {
      orgname = orgname.trim();
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int count = 0;
    List list = new ArrayList();
    orgpopList = orgDAO.queryOrgpopListByCriterions(orgid.toString(), orgname,
        orderBy, order, start, pageSize, null, securityInfo);
    if (orgpopList != null) {
      for (int i = 0; i < orgpopList.size(); i++) {
        Org org = (Org) orgpopList.get(i);
        org.getOrgInfo().setRegion(
            BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
                .getRegion()), BusiConst.INAREA));
        list.add(org);
      }
    }
    count = orgDAO.queryOrgpopCountByCriterions(orgid.toString(), orgname,
        null, securityInfo);
    pagination.setNrOfElements(count);
    return list;
  }

  public int findOrgpopListCount(String orgid, String orgname,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    int count = 0;
    Integer temp_id = null;
    if (orgid != null && !orgid.equals("")) {
      temp_id = new Integer(orgid.trim());
      orgid = temp_id.toString();
    }
    try {
      count = orgDAO.queryOrgpopCountByCriterions(orgid, orgname, null,
          securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * AJAXAction中用到查询单位
   */
  public Org queryOrgById(Integer id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      Org org = null;
      org = orgDAO.queryByCriterions(id.toString(), null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      org = orgDAO.findById(id, securityInfo);

      return org;
    } catch (BusinessException be) {
      throw be;
    }

  }

  public List changePrintList(List list) throws Exception {
    BigDecimal debittotal = new BigDecimal(0.00);
    BigDecimal credittotal = new BigDecimal(0.00);
    BigDecimal distorytotal = new BigDecimal(0.00);
    BigDecimal debittotal_m = new BigDecimal(0.00);
    BigDecimal credittotal_m = new BigDecimal(0.00);
    BigDecimal distorytotal_m = new BigDecimal(0.00);
    BigDecimal debittotal_y = new BigDecimal(0.00);
    BigDecimal credittotal_y = new BigDecimal(0.00);
    BigDecimal distorytotal_y = new BigDecimal(0.00);
    List printList = new ArrayList();
    try {
      for (int i = 0; i < list.size(); i++) {
        CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO = (CollFnComparisonOrgAccountDTO) list
            .get(i);
        if (i == 0) {
          // 第一条
          if (collFnComparisonOrgAccountDTO.getCollsett_date().substring(4, 8)
              .equals("0101")) {
            collFnComparisonOrgAccountDTO.setTemp_collSt(BusiTools
                .getBusiValue_WL(BusiConst.FNSUMMARY_LASTYEARCLEAR,
                    BusiConst.FNSUMMARY));
          } else {
            collFnComparisonOrgAccountDTO.setTemp_collSt(BusiTools
                .getBusiValue_WL(BusiConst.FNSUMMARY_BGNBLAN,
                    BusiConst.FNSUMMARY));
          }
          collFnComparisonOrgAccountDTO
              .setMoneySum(collFnComparisonOrgAccountDTO.getMoneySum().abs());
        } else {
          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO1 = (CollFnComparisonOrgAccountDTO) list
              .get(i - 1);
          if (!collFnComparisonOrgAccountDTO.getOrg_id().equals(
              collFnComparisonOrgAccountDTO1.getOrg_id())) {
            // 单位改变:则在改变之前需增加本日、本期、本年合计
            CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_pre = (CollFnComparisonOrgAccountDTO) list
                .get(i - 1);
            CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_d = new CollFnComparisonOrgAccountDTO();
            collFnComparisonOrgAccountDTO_d
                .setCollsett_date(collFnComparisonOrgAccountDTO_pre
                    .getCollsett_date());
            collFnComparisonOrgAccountDTO_d.setDoc_num("");
            collFnComparisonOrgAccountDTO_d.setTemp_collSt(BusiTools
                .getBusiValue_WL(BusiConst.FNSUMMARY_DAYSUM,
                    BusiConst.FNSUMMARY));
            collFnComparisonOrgAccountDTO_d.setDebit(debittotal.abs());
            collFnComparisonOrgAccountDTO_d.setCredit(credittotal.abs());
            collFnComparisonOrgAccountDTO_d.setDistorybalance(distorytotal
                .abs());
            collFnComparisonOrgAccountDTO_d
                .setAspect(collFnComparisonOrgAccountDTO_pre.getAspect());
            collFnComparisonOrgAccountDTO_d
                .setMoneySum(collFnComparisonOrgAccountDTO_pre.getMoneySum());
            collFnComparisonOrgAccountDTO_d
                .setOrg_id(collFnComparisonOrgAccountDTO_pre.getOrg_id());
            collFnComparisonOrgAccountDTO_d
                .setName(collFnComparisonOrgAccountDTO_pre.getName());
            debittotal = new BigDecimal(0.00);
            credittotal = new BigDecimal(0.00);
            distorytotal = new BigDecimal(0.00);
            printList.add(collFnComparisonOrgAccountDTO_d);

            CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_m = new CollFnComparisonOrgAccountDTO();
            collFnComparisonOrgAccountDTO_m
                .setCollsett_date(collFnComparisonOrgAccountDTO_pre
                    .getCollsett_date());
            collFnComparisonOrgAccountDTO_m.setDoc_num("");
            collFnComparisonOrgAccountDTO_m.setTemp_collSt(BusiTools
                .getBusiValue_WL(BusiConst.FNSUMMARY_TERMSUM,
                    BusiConst.FNSUMMARY));
            collFnComparisonOrgAccountDTO_m.setDebit(debittotal_m.abs());
            collFnComparisonOrgAccountDTO_m.setCredit(credittotal_m.abs());
            collFnComparisonOrgAccountDTO_m.setDistorybalance(distorytotal_m
                .abs());
            collFnComparisonOrgAccountDTO_m
                .setAspect(collFnComparisonOrgAccountDTO_pre.getAspect());
            collFnComparisonOrgAccountDTO_m
                .setMoneySum(collFnComparisonOrgAccountDTO_pre.getMoneySum());
            collFnComparisonOrgAccountDTO_m
                .setOrg_id(collFnComparisonOrgAccountDTO_pre.getOrg_id());
            collFnComparisonOrgAccountDTO_m
                .setName(collFnComparisonOrgAccountDTO_pre.getName());
            debittotal_m = new BigDecimal(0.00);
            credittotal_m = new BigDecimal(0.00);
            distorytotal_m = new BigDecimal(0.00);
            printList.add(collFnComparisonOrgAccountDTO_m);

            CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_y = new CollFnComparisonOrgAccountDTO();
            collFnComparisonOrgAccountDTO_y
                .setCollsett_date(collFnComparisonOrgAccountDTO_pre
                    .getCollsett_date());
            collFnComparisonOrgAccountDTO_y.setDoc_num("");
            collFnComparisonOrgAccountDTO_y.setTemp_collSt(BusiTools
                .getBusiValue_WL(BusiConst.FNSUMMARY_YEARSUM,
                    BusiConst.FNSUMMARY));
            collFnComparisonOrgAccountDTO_y.setDebit(debittotal_y.abs());
            collFnComparisonOrgAccountDTO_y.setCredit(credittotal_y.abs());
            collFnComparisonOrgAccountDTO_y.setDistorybalance(distorytotal_y
                .abs());
            collFnComparisonOrgAccountDTO_y
                .setAspect(collFnComparisonOrgAccountDTO_pre.getAspect());
            collFnComparisonOrgAccountDTO_y
                .setMoneySum(collFnComparisonOrgAccountDTO_pre.getMoneySum());
            collFnComparisonOrgAccountDTO_y
                .setOrg_id(collFnComparisonOrgAccountDTO_pre.getOrg_id());
            collFnComparisonOrgAccountDTO_y
                .setName(collFnComparisonOrgAccountDTO_pre.getName());
            debittotal_y = new BigDecimal(0.00);
            credittotal_y = new BigDecimal(0.00);
            distorytotal_y = new BigDecimal(0.00);
            printList.add(collFnComparisonOrgAccountDTO_y);

            if (collFnComparisonOrgAccountDTO.getCollsett_date()
                .substring(4, 8).equals("0101")) {
              collFnComparisonOrgAccountDTO.setTemp_collSt(BusiTools
                  .getBusiValue_WL(BusiConst.FNSUMMARY_LASTYEARCLEAR,
                      BusiConst.FNSUMMARY));
            } else {
              collFnComparisonOrgAccountDTO.setTemp_collSt(BusiTools
                  .getBusiValue_WL(BusiConst.FNSUMMARY_BGNBLAN,
                      BusiConst.FNSUMMARY));
            }
            collFnComparisonOrgAccountDTO
                .setMoneySum(collFnComparisonOrgAccountDTO.getMoneySum().abs());

            debittotal = new BigDecimal(0.00);
            credittotal = new BigDecimal(0.00);
            distorytotal = new BigDecimal(0.00);

            debittotal_m = new BigDecimal(0.00);
            credittotal_m = new BigDecimal(0.00);
            distorytotal_m = new BigDecimal(0.00);

            debittotal_y = new BigDecimal(0.00);
            credittotal_y = new BigDecimal(0.00);
            distorytotal_y = new BigDecimal(0.00);
          } else {
            CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_pre = (CollFnComparisonOrgAccountDTO) list
                .get(i - 1);
            if (!collFnComparisonOrgAccountDTO.getCollsett_date().equals(
                collFnComparisonOrgAccountDTO1.getCollsett_date())) {
              CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO2 = new CollFnComparisonOrgAccountDTO();
              collFnComparisonOrgAccountDTO2
                  .setCollsett_date(collFnComparisonOrgAccountDTO_pre
                      .getCollsett_date());
              collFnComparisonOrgAccountDTO2.setDoc_num("");
              collFnComparisonOrgAccountDTO2.setTemp_collSt(BusiTools
                  .getBusiValue_WL(BusiConst.FNSUMMARY_DAYSUM,
                      BusiConst.FNSUMMARY));
              collFnComparisonOrgAccountDTO2.setDebit(debittotal.abs());
              collFnComparisonOrgAccountDTO2.setCredit(credittotal.abs());
              collFnComparisonOrgAccountDTO2.setDistorybalance(distorytotal
                  .abs());
              collFnComparisonOrgAccountDTO2
                  .setAspect(collFnComparisonOrgAccountDTO_pre.getAspect());
              collFnComparisonOrgAccountDTO2
                  .setMoneySum(collFnComparisonOrgAccountDTO_pre.getMoneySum());
              collFnComparisonOrgAccountDTO2
                  .setOrg_id(collFnComparisonOrgAccountDTO_pre.getOrg_id());
              collFnComparisonOrgAccountDTO2
                  .setName(collFnComparisonOrgAccountDTO_pre.getName());
              debittotal = new BigDecimal(0.00);
              credittotal = new BigDecimal(0.00);
              distorytotal = new BigDecimal(0.00);
              printList.add(collFnComparisonOrgAccountDTO2);
            }
            if (!collFnComparisonOrgAccountDTO.getCollsett_date().substring(0,
                6).equals(
                collFnComparisonOrgAccountDTO1.getCollsett_date().substring(0,
                    6))) {
              CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO2 = new CollFnComparisonOrgAccountDTO();
              collFnComparisonOrgAccountDTO2
                  .setCollsett_date(collFnComparisonOrgAccountDTO_pre
                      .getCollsett_date());
              collFnComparisonOrgAccountDTO2.setDoc_num("");
              collFnComparisonOrgAccountDTO2.setTemp_collSt(BusiTools
                  .getBusiValue_WL(BusiConst.FNSUMMARY_TERMSUM,
                      BusiConst.FNSUMMARY));
              collFnComparisonOrgAccountDTO2.setDebit(debittotal_m.abs());
              collFnComparisonOrgAccountDTO2.setCredit(credittotal_m.abs());
              collFnComparisonOrgAccountDTO2.setDistorybalance(distorytotal_m
                  .abs());
              collFnComparisonOrgAccountDTO2
                  .setAspect(collFnComparisonOrgAccountDTO_pre.getAspect());
              collFnComparisonOrgAccountDTO2
                  .setMoneySum(collFnComparisonOrgAccountDTO_pre.getMoneySum());
              collFnComparisonOrgAccountDTO2
                  .setOrg_id(collFnComparisonOrgAccountDTO_pre.getOrg_id());
              collFnComparisonOrgAccountDTO2
                  .setName(collFnComparisonOrgAccountDTO_pre.getName());
              debittotal_m = new BigDecimal(0.00);
              credittotal_m = new BigDecimal(0.00);
              distorytotal_m = new BigDecimal(0.00);
              printList.add(collFnComparisonOrgAccountDTO2);

              CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO3 = new CollFnComparisonOrgAccountDTO();
              collFnComparisonOrgAccountDTO3
                  .setCollsett_date(collFnComparisonOrgAccountDTO_pre
                      .getCollsett_date());
              collFnComparisonOrgAccountDTO3.setDoc_num("");
              collFnComparisonOrgAccountDTO3.setTemp_collSt(BusiTools
                  .getBusiValue_WL(BusiConst.FNSUMMARY_YEARSUM,
                      BusiConst.FNSUMMARY));
              collFnComparisonOrgAccountDTO3.setDebit(debittotal_y.abs());
              collFnComparisonOrgAccountDTO3.setCredit(credittotal_y.abs());
              collFnComparisonOrgAccountDTO3.setDistorybalance(distorytotal_y
                  .abs());
              collFnComparisonOrgAccountDTO3
                  .setAspect(collFnComparisonOrgAccountDTO_pre.getAspect());
              collFnComparisonOrgAccountDTO3
                  .setMoneySum(collFnComparisonOrgAccountDTO_pre.getMoneySum());
              collFnComparisonOrgAccountDTO3
                  .setOrg_id(collFnComparisonOrgAccountDTO_pre.getOrg_id());
              collFnComparisonOrgAccountDTO3
                  .setName(collFnComparisonOrgAccountDTO_pre.getName());
              printList.add(collFnComparisonOrgAccountDTO3);
            }
            if (!collFnComparisonOrgAccountDTO.getCollsett_date().substring(0,
                4).equals(
                collFnComparisonOrgAccountDTO1.getCollsett_date().substring(0,
                    4))) {
              // 如果是同一个单位，跨年查询时
              collFnComparisonOrgAccountDTO
                  .setMoneySum(collFnComparisonOrgAccountDTO.getMoneySum()
                      .abs());

              debittotal_y = new BigDecimal(0.00);
              credittotal_y = new BigDecimal(0.00);
              distorytotal_y = new BigDecimal(0.00);
            }
          }
        }
        BigDecimal moneysum = new BigDecimal(0.00);
        moneysum = collFnComparisonOrgAccountDTO.getMoneySum();
        String aspect = "";
        aspect = collFnComparisonOrgAccountDTO.getAspect();
        if (aspect.equals("借")) {
          moneysum = moneysum.multiply(new BigDecimal(-1));
        }
        // 本日合计
        debittotal = debittotal.add(collFnComparisonOrgAccountDTO.getDebit());
        credittotal = credittotal
            .add(collFnComparisonOrgAccountDTO.getCredit());
        distorytotal = distorytotal.add(collFnComparisonOrgAccountDTO
            .getDistorybalance());

        // 本期累计
        debittotal_m = debittotal_m.add(collFnComparisonOrgAccountDTO
            .getDebit());
        credittotal_m = credittotal_m.add(collFnComparisonOrgAccountDTO
            .getCredit());
        distorytotal_m = distorytotal_m.add(collFnComparisonOrgAccountDTO
            .getDistorybalance());
        // 本年累计
        debittotal_y = debittotal_y.add(collFnComparisonOrgAccountDTO
            .getDebit());
        credittotal_y = credittotal_y.add(collFnComparisonOrgAccountDTO
            .getCredit());
        distorytotal_y = distorytotal_y.add(collFnComparisonOrgAccountDTO
            .getDistorybalance());

        printList.add(collFnComparisonOrgAccountDTO);

        // 如果是同一单位，跨年查询时，本年的第一条不进行余额累加
        if (i > 0) {
          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_qcye = (CollFnComparisonOrgAccountDTO) list
              .get(i - 1);
          if (!collFnComparisonOrgAccountDTO.getCollsett_date().substring(0, 4)
              .equals(
                  collFnComparisonOrgAccountDTO_qcye.getCollsett_date()
                      .substring(0, 4))) {
            debittotal = new BigDecimal(0.00);
            credittotal = new BigDecimal(0.00);
            distorytotal = new BigDecimal(0.00);

            debittotal_m = new BigDecimal(0.00);
            credittotal_m = new BigDecimal(0.00);
            distorytotal_m = new BigDecimal(0.00);

            debittotal_y = new BigDecimal(0.00);
            credittotal_y = new BigDecimal(0.00);
            distorytotal_y = new BigDecimal(0.00);
          }
        }
        if (i == list.size() - 1) {
          // 最后一条
          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_d = new CollFnComparisonOrgAccountDTO();
          collFnComparisonOrgAccountDTO_d
              .setCollsett_date(collFnComparisonOrgAccountDTO
                  .getCollsett_date());
          collFnComparisonOrgAccountDTO_d.setDoc_num("");
          collFnComparisonOrgAccountDTO_d
              .setTemp_collSt(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_DAYSUM, BusiConst.FNSUMMARY));
          collFnComparisonOrgAccountDTO_d.setDebit(debittotal.abs());
          collFnComparisonOrgAccountDTO_d.setCredit(credittotal.abs());
          collFnComparisonOrgAccountDTO_d.setDistorybalance(distorytotal.abs());
          collFnComparisonOrgAccountDTO_d
              .setAspect(collFnComparisonOrgAccountDTO.getAspect());
          collFnComparisonOrgAccountDTO_d
              .setMoneySum(collFnComparisonOrgAccountDTO.getMoneySum());
          collFnComparisonOrgAccountDTO_d
              .setOrg_id(collFnComparisonOrgAccountDTO.getOrg_id());
          collFnComparisonOrgAccountDTO_d.setName(collFnComparisonOrgAccountDTO
              .getName());
          debittotal = new BigDecimal(0.00);
          credittotal = new BigDecimal(0.00);
          distorytotal = new BigDecimal(0.00);
          printList.add(collFnComparisonOrgAccountDTO_d);

          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_m = new CollFnComparisonOrgAccountDTO();
          collFnComparisonOrgAccountDTO_m
              .setCollsett_date(collFnComparisonOrgAccountDTO
                  .getCollsett_date());
          collFnComparisonOrgAccountDTO_m.setDoc_num("");
          collFnComparisonOrgAccountDTO_m
              .setTemp_collSt(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_TERMSUM, BusiConst.FNSUMMARY));
          collFnComparisonOrgAccountDTO_m.setDebit(debittotal_m.abs());
          collFnComparisonOrgAccountDTO_m.setCredit(credittotal_m.abs());
          collFnComparisonOrgAccountDTO_m.setDistorybalance(distorytotal_m
              .abs());
          collFnComparisonOrgAccountDTO_m
              .setAspect(collFnComparisonOrgAccountDTO.getAspect());
          collFnComparisonOrgAccountDTO_m
              .setMoneySum(collFnComparisonOrgAccountDTO.getMoneySum());
          collFnComparisonOrgAccountDTO_m
              .setOrg_id(collFnComparisonOrgAccountDTO.getOrg_id());
          collFnComparisonOrgAccountDTO_m.setName(collFnComparisonOrgAccountDTO
              .getName());
          debittotal_m = new BigDecimal(0.00);
          credittotal_m = new BigDecimal(0.00);
          distorytotal_m = new BigDecimal(0.00);
          printList.add(collFnComparisonOrgAccountDTO_m);

          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO_y = new CollFnComparisonOrgAccountDTO();
          collFnComparisonOrgAccountDTO_y
              .setCollsett_date(collFnComparisonOrgAccountDTO
                  .getCollsett_date());
          collFnComparisonOrgAccountDTO_y.setDoc_num("");
          collFnComparisonOrgAccountDTO_y
              .setTemp_collSt(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
          collFnComparisonOrgAccountDTO_y.setDebit(debittotal_y.abs());
          collFnComparisonOrgAccountDTO_y.setCredit(credittotal_y.abs());
          collFnComparisonOrgAccountDTO_y.setDistorybalance(distorytotal_y
              .abs());
          collFnComparisonOrgAccountDTO_y
              .setAspect(collFnComparisonOrgAccountDTO.getAspect());
          collFnComparisonOrgAccountDTO_y
              .setMoneySum(collFnComparisonOrgAccountDTO.getMoneySum());
          collFnComparisonOrgAccountDTO_y
              .setOrg_id(collFnComparisonOrgAccountDTO.getOrg_id());
          collFnComparisonOrgAccountDTO_y.setName(collFnComparisonOrgAccountDTO
              .getName());
          debittotal_y = new BigDecimal(0.00);
          credittotal_y = new BigDecimal(0.00);
          distorytotal_y = new BigDecimal(0.00);
          printList.add(collFnComparisonOrgAccountDTO_y);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return printList;
  }

  /**
   * 获得发生方向
   * 
   * @param value
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String getDirtection(BigDecimal value) throws Exception,
      BusinessException {
    String dirtection = "";
    try {
      if (value.compareTo(new BigDecimal(0.00)) == 0) {
        dirtection = BusiTools.getBusiValue_WL(BusiConst.BALANCEDIRECTION_AVE,
            BusiConst.BALANCEDIRECTION);
      } else if (value.compareTo(new BigDecimal(0.00)) > 0) {
        dirtection = BusiTools.getBusiValue_WL(
            BusiConst.BALANCEDIRECTION_CREDIT, BusiConst.BALANCEDIRECTION);
      } else {
        dirtection = BusiTools.getBusiValue_WL(
            BusiConst.BALANCEDIRECTION_DEBIT, BusiConst.BALANCEDIRECTION);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dirtection;
  }

  public List findOrgAccountPrintList(String orgidst, String orgidend,
      String timest, String timeend, SecurityInfo securityInfo)
      throws Exception {
    List list = new ArrayList();
    BigDecimal moneysum_p = new BigDecimal(0.00);
    BigDecimal debit_p = new BigDecimal(0.00);
    BigDecimal credit_p = new BigDecimal(0.00);
    String aspect_p = "";
    BigDecimal qcye = new BigDecimal(0.00);
    List resultList = new ArrayList();
    try {
      list = orgHAFAccountFlowDAO.queryCollFnComparisonCountList(null, null,
          null, null, null, timest, timeend, securityInfo, orgidst, orgidend);
      for (int i = 0; i < list.size(); i++) {
        CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO = (CollFnComparisonOrgAccountDTO) list
            .get(i);
        if (i == 0) {
          qcye = orgHAFAccountFlowDAO.queryqcye(collFnComparisonOrgAccountDTO
              .getOrg_id(), timest);
          moneysum_p = qcye;
          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO1 = new CollFnComparisonOrgAccountDTO();
          collFnComparisonOrgAccountDTO1
              .setCollsett_date(collFnComparisonOrgAccountDTO
                  .getCollsett_date());
          collFnComparisonOrgAccountDTO1.setMoneySum(moneysum_p);
          collFnComparisonOrgAccountDTO1.setAspect(this
              .getDirtection(moneysum_p));
          collFnComparisonOrgAccountDTO1
              .setOrg_id(collFnComparisonOrgAccountDTO.getOrg_id());
          collFnComparisonOrgAccountDTO1.setName(collFnComparisonOrgAccountDTO
              .getName());
          resultList.add(collFnComparisonOrgAccountDTO1);
        } else {
          CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO1 = (CollFnComparisonOrgAccountDTO) list
              .get(i - 1);
          if (!collFnComparisonOrgAccountDTO.getOrg_id().equals(
              collFnComparisonOrgAccountDTO1.getOrg_id())) {
            qcye = orgHAFAccountFlowDAO.queryqcye(collFnComparisonOrgAccountDTO
                .getOrg_id(), timest);
            moneysum_p = qcye;
            CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO2 = new CollFnComparisonOrgAccountDTO();
            collFnComparisonOrgAccountDTO2
                .setCollsett_date(collFnComparisonOrgAccountDTO
                    .getCollsett_date());
            collFnComparisonOrgAccountDTO2.setMoneySum(moneysum_p);
            collFnComparisonOrgAccountDTO2.setAspect(this
                .getDirtection(moneysum_p));
            collFnComparisonOrgAccountDTO2
                .setOrg_id(collFnComparisonOrgAccountDTO.getOrg_id());
            collFnComparisonOrgAccountDTO2
                .setName(collFnComparisonOrgAccountDTO.getName());
            resultList.add(collFnComparisonOrgAccountDTO2);
          } else {
            if (!collFnComparisonOrgAccountDTO.getCollsett_date().substring(0,
                4).equals(
                collFnComparisonOrgAccountDTO1.getCollsett_date().substring(0,
                    4))) {
              qcye = orgHAFAccountFlowDAO.queryqcye(
                  collFnComparisonOrgAccountDTO.getOrg_id(),
                  collFnComparisonOrgAccountDTO.getCollsett_date());
              moneysum_p = qcye;
              CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO2 = new CollFnComparisonOrgAccountDTO();
              collFnComparisonOrgAccountDTO2
                  .setCollsett_date(collFnComparisonOrgAccountDTO
                      .getCollsett_date());
              if (collFnComparisonOrgAccountDTO.getCollsett_date().substring(4,
                  8).equals("0101")) {
                collFnComparisonOrgAccountDTO2.setTemp_collSt(BusiTools
                    .getBusiValue_WL(BusiConst.FNSUMMARY_LASTYEARCLEAR,
                        BusiConst.FNSUMMARY));
              } else {
                collFnComparisonOrgAccountDTO2.setTemp_collSt(BusiTools
                    .getBusiValue_WL(BusiConst.FNSUMMARY_BGNBLAN,
                        BusiConst.FNSUMMARY));
              }
              collFnComparisonOrgAccountDTO2.setMoneySum(moneysum_p);
              collFnComparisonOrgAccountDTO2.setAspect(this
                  .getDirtection(moneysum_p));
              collFnComparisonOrgAccountDTO2
                  .setOrg_id(collFnComparisonOrgAccountDTO.getOrg_id());
              collFnComparisonOrgAccountDTO2
                  .setName(collFnComparisonOrgAccountDTO.getName());
              resultList.add(collFnComparisonOrgAccountDTO2);
            }
          }
        }
        debit_p = collFnComparisonOrgAccountDTO.getDebit();
        credit_p = collFnComparisonOrgAccountDTO.getCredit();
        aspect_p = this.getDirtection(moneysum_p);
        if (aspect_p.equals("借")) {
          if (credit_p.compareTo(new BigDecimal(0)) > 0
              && (credit_p.compareTo(moneysum_p.abs())) > 0) {
            moneysum_p = moneysum_p.add(credit_p).abs();
          } else {
            moneysum_p = moneysum_p.add(debit_p).subtract(credit_p);
          }
        } else {
          moneysum_p = moneysum_p.add(credit_p).subtract(debit_p);
        }
        aspect_p = this.getDirtection(moneysum_p);
        collFnComparisonOrgAccountDTO.setMoneySum(moneysum_p.abs());
        collFnComparisonOrgAccountDTO.setAspect(aspect_p);

        collFnComparisonOrgAccountDTO.setTemp_collSt(BusiTools.getBusiValue_WL(
            collFnComparisonOrgAccountDTO.getBiz_type(),
            BusiConst.CLEARACCOUNTBUSINESSTYPE));
        resultList.add(collFnComparisonOrgAccountDTO);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;
  }

  public List findEmpPopList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String orgname = (String) pagination.getQueryCriterions().get("orgname");
    String empid = (String) pagination.getQueryCriterions().get("empid");
    String empname = (String) pagination.getQueryCriterions().get("empname");

    List list = empDAO.queryCollFnComparisonEmpAccountPopList(orgid, orgname,
        empid, empname, orderBy, order, start, pageSize, securityInfo);
    List countList = empDAO.queryCollFnComparisonEmpAccountPopCount(orgid,
        orgname, empid, empname, securityInfo);
    pagination.setNrOfElements(countList.size());

    return list;
  }

  public List findEmpAccountPrint(String orgidst, String orgidend,
      String empidst, String empidend, String timeSt, String timeEnd,
      SecurityInfo securityInfo) throws Exception {
    List resultList = new ArrayList();
    List tempList = new ArrayList();// 用这个集合类来封装一个职工的职工账
    BigDecimal qcye = new BigDecimal(0.00);
    String aspect = "";
    BigDecimal moneysum = new BigDecimal(0.00);
    BigDecimal debit = new BigDecimal(0.00);
    BigDecimal credit = new BigDecimal(0.00);

    String temp_orgid = "";
    String temp_empid = "";
    String temp_orgname = "";
    String temp_empname = "";
    BigDecimal temp_qcye = new BigDecimal(0.00);

    String temp_date = "";// 用来进行比较的日期
    String temp_date_m = "";
    String temp_date_y = "";
    String temp_dirtection = "";// 临时方向用来存放合计的方向
    // 日合计
    BigDecimal sumDebit_d = new BigDecimal(0.00);
    BigDecimal sumCredit_d = new BigDecimal(0.00);
    BigDecimal sumInterest_d = new BigDecimal(0.00);
    BigDecimal sumMoneySum_d = new BigDecimal(0.00);
    // 月合计
    BigDecimal sumDebit_m = new BigDecimal(0.00);
    BigDecimal sumCredit_m = new BigDecimal(0.00);
    BigDecimal sumInterest_m = new BigDecimal(0.00);
    BigDecimal sumMoneySum_m = new BigDecimal(0.00);
    // 年合计
    BigDecimal sumDebit_y = new BigDecimal(0.00);
    BigDecimal sumCredit_y = new BigDecimal(0.00);
    BigDecimal sumInterest_y = new BigDecimal(0.00);
    BigDecimal sumMoneySum_y = new BigDecimal(0.00);

    BigDecimal temp_moneysum = new BigDecimal(0.00);

    List list = empDAO.queryEmpAccountPrint(orgidst, orgidend, empidst,
        empidend, timeSt, timeEnd, securityInfo);

    for (int i = 0; i < list.size(); i++) {
      Object[] obj = new Object[2];// 分别用来存放表头信息与职工账
      CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO = (CollFnComparisonEmpAccountDTO) list
          .get(i);

      if (i == 0) {
        qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
            collFnComparisonEmpAccountDTO.getOrgId(),
            collFnComparisonEmpAccountDTO.getEmpId(),
            collFnComparisonEmpAccountDTO.getCollDate());
        moneysum = moneysum.add(qcye);
      }
      debit = collFnComparisonEmpAccountDTO.getDebit();
      credit = collFnComparisonEmpAccountDTO.getCredit();
      aspect = this.getDirtection(moneysum);
      if (aspect.equals("借")) {
        if (credit.compareTo(new BigDecimal(0)) > 0
            && (credit.compareTo(moneysum.abs())) > 0) {
          moneysum = moneysum.add(credit).abs();
        } else {
          moneysum = moneysum.add(debit).subtract(credit);
        }
      } else {
        moneysum = moneysum.add(credit).subtract(debit);
      }
      aspect = this.getDirtection(moneysum);
      collFnComparisonEmpAccountDTO.setMoneySum(moneysum.abs());
      collFnComparisonEmpAccountDTO.setDirection(aspect);

      // 转换类型与状态
      if (collFnComparisonEmpAccountDTO.getBizType() != null
          && !collFnComparisonEmpAccountDTO.getBizType().equals("")) {
        collFnComparisonEmpAccountDTO.setBizType(BusiTools.getBusiValue_WL(
            collFnComparisonEmpAccountDTO.getBizType(),
            BusiConst.CLEARACCOUNTBUSINESSTYPE));
      }

      if (i == 0) {
        temp_moneysum = moneysum.abs();
        temp_dirtection = collFnComparisonEmpAccountDTO.getDirection();
        // 初始化比较日期
        temp_date = collFnComparisonEmpAccountDTO.getCollDate();
        temp_date_m = collFnComparisonEmpAccountDTO.getCollDate();
        temp_date_y = collFnComparisonEmpAccountDTO.getCollDate();
        // 在第一次循环时赋初值,并且将职工的姓名与单位的名称得到用来生成表头
        temp_orgid = collFnComparisonEmpAccountDTO.getOrgId();
        temp_empid = collFnComparisonEmpAccountDTO.getEmpId();
        temp_orgname = collFnComparisonEmpAccountDTO.getOrgName();
        temp_empname = collFnComparisonEmpAccountDTO.getEmpName();
        // 日合计初始
        sumDebit_d = collFnComparisonEmpAccountDTO.getDebit();
        sumCredit_d = collFnComparisonEmpAccountDTO.getCredit();
        sumInterest_d = collFnComparisonEmpAccountDTO.getInterest();
        sumMoneySum_d = collFnComparisonEmpAccountDTO.getMoneySum();

        // qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
        // collFnComparisonEmpAccountDTO.getOrgId(),
        // collFnComparisonEmpAccountDTO.getEmpId(), timeSt);
        // moneysum = moneysum.add(qcye);

        CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
        // 获得年月
        String date = collFnComparisonEmpAccountDTO.getCollDate().substring(4,
            collFnComparisonEmpAccountDTO.getCollDate().length());
        if (date.equals("0101")) {
          dto.setBizType(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
        } else {
          dto.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_BGNBLAN,
              BusiConst.FNSUMMARY));
        }
        dto.setCollDate(collFnComparisonEmpAccountDTO.getCollDate());
        dto.setMoneySum(qcye);
        dto.setDirection(getDirtection(qcye));
        tempList.add(dto);

      }
      // 如果不是一个员工则重新得到余额
      if (!temp_orgid.equals(collFnComparisonEmpAccountDTO.getOrgId())
          || !temp_empid.equals(collFnComparisonEmpAccountDTO.getEmpId())) {
        qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
            collFnComparisonEmpAccountDTO.getOrgId(),
            collFnComparisonEmpAccountDTO.getEmpId(), timeSt);
        moneysum = qcye;
        debit = collFnComparisonEmpAccountDTO.getDebit();
        credit = collFnComparisonEmpAccountDTO.getCredit();
        aspect = this.getDirtection(moneysum);
        if (aspect.equals("借")) {
          moneysum = moneysum.add(debit).subtract(credit);
        } else {
          moneysum = moneysum.add(credit).subtract(debit);
        }
        aspect = this.getDirtection(moneysum);
        collFnComparisonEmpAccountDTO.setMoneySum(moneysum.abs());
        collFnComparisonEmpAccountDTO.setDirection(aspect);
      }

      // 判断是否为同一个职工编号
      if (!temp_orgid.equals(collFnComparisonEmpAccountDTO.getOrgId())
          || !temp_empid.equals(collFnComparisonEmpAccountDTO.getEmpId())
          || i == list.size() - 1) {

        String date1 = "";// 用来记录上一条记录的时间
        date1 = temp_date;
        if (!temp_date.equals(collFnComparisonEmpAccountDTO.getCollDate())) {
          // 添加日合计
          CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
          dto.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_DAYSUM,
              BusiConst.FNSUMMARY));
          dto.setDebit(sumDebit_d);
          dto.setCredit(sumCredit_d);
          dto.setInterest(sumInterest_d);
          dto.setMoneySum(temp_moneysum);
          dto.setCollDate(temp_date);
          dto.setDirection(temp_dirtection);
          tempList.add(dto);

          // 累加本月合计
          sumDebit_m = sumDebit_m.add(sumDebit_d);
          sumCredit_m = sumCredit_m.add(sumCredit_d);
          sumInterest_m = sumInterest_m.add(sumInterest_d);
          sumMoneySum_m = sumMoneySum_m.add(sumMoneySum_d);

          // 将本日合计清空
          sumDebit_d = new BigDecimal(0.00);
          sumCredit_d = new BigDecimal(0.00);
          sumInterest_d = new BigDecimal(0.00);
          sumMoneySum_d = new BigDecimal(0.00);
          temp_date = collFnComparisonEmpAccountDTO.getCollDate();
        }
        if (!temp_date_m.substring(0, 6).equals(
            collFnComparisonEmpAccountDTO.getCollDate().substring(0, 6))) {
          // 添加月合计
          CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
          dto.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_TERMSUM,
              BusiConst.FNSUMMARY));
          dto.setDebit(sumDebit_m);
          dto.setCredit(sumCredit_m);
          dto.setInterest(sumInterest_m);
          dto.setMoneySum(temp_moneysum);
          dto.setCollDate(date1);
          dto.setDirection(temp_dirtection);
          tempList.add(dto);

          // 累加年合计
          sumDebit_y = sumDebit_y.add(sumDebit_m);
          sumCredit_y = sumCredit_y.add(sumCredit_m);
          sumInterest_y = sumInterest_y.add(sumInterest_m);
          sumMoneySum_y = sumMoneySum_y.add(sumMoneySum_m);

          // 将本月合计清空
          sumDebit_m = new BigDecimal(0.00);
          sumCredit_m = new BigDecimal(0.00);
          sumInterest_m = new BigDecimal(0.00);
          sumMoneySum_m = new BigDecimal(0.00);

          if (temp_date_y.substring(0, 4).equals(
              collFnComparisonEmpAccountDTO.getCollDate().substring(0, 4))) {
            // 添加年合计
            CollFnComparisonEmpAccountDTO dto1 = new CollFnComparisonEmpAccountDTO();
            dto1.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
            dto1.setDebit(sumDebit_y);
            dto1.setCredit(sumCredit_y);
            dto1.setInterest(sumInterest_y);
            dto1.setMoneySum(temp_moneysum);
            dto1.setCollDate(date1);
            dto1.setDirection(temp_dirtection);
            tempList.add(dto1);
          }
          temp_date_m = collFnComparisonEmpAccountDTO.getCollDate();
        }
        if (!temp_date_y.substring(0, 4).equals(
            collFnComparisonEmpAccountDTO.getCollDate().substring(0, 4))) {
          // 添加年合计
          CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
          dto.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_YEARSUM,
              BusiConst.FNSUMMARY));
          dto.setDebit(sumDebit_y);
          dto.setCredit(sumCredit_y);
          dto.setInterest(sumInterest_y);
          dto.setMoneySum(temp_moneysum);
          dto.setCollDate(date1);
          dto.setDirection(temp_dirtection);
          tempList.add(dto);
          // 将本月合计清空
          sumDebit_y = new BigDecimal(0.00);
          sumCredit_y = new BigDecimal(0.00);
          sumInterest_y = new BigDecimal(0.00);
          sumMoneySum_y = new BigDecimal(0.00);

          // 求上年结转
          qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
              collFnComparisonEmpAccountDTO.getOrgId(),
              collFnComparisonEmpAccountDTO.getEmpId(),
              collFnComparisonEmpAccountDTO.getCollDate());
//          moneysum = moneysum.add(qcye);

          CollFnComparisonEmpAccountDTO dto1 = new CollFnComparisonEmpAccountDTO();
          // 获得年月
          String date = collFnComparisonEmpAccountDTO.getCollDate().substring(
              4, collFnComparisonEmpAccountDTO.getCollDate().length());
          if (date.equals("0101")) {
            dto1.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
          } else {
            dto1.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
          }
          dto1.setCollDate(collFnComparisonEmpAccountDTO.getCollDate());
          dto1.setMoneySum(qcye);
          dto1.setDirection(getDirtection(qcye));
          tempList.add(dto1);

          temp_date_y = collFnComparisonEmpAccountDTO.getCollDate();

        }

        // 封装成表头
        obj[0] = "单位编号：" + temp_orgid + " 单位名称：" + temp_orgname + " 职工编号："
            + temp_empid + " 职工姓名：" + temp_empname + " 发生时间：" + timeSt + "-"
            + timeEnd;
        // 最后一条进入
        if (i == list.size() - 1) {
          // 累加日合计
          sumDebit_d = sumDebit_d.add(collFnComparisonEmpAccountDTO.getDebit());
          sumCredit_d = sumCredit_d.add(collFnComparisonEmpAccountDTO
              .getCredit());
          sumInterest_d = sumInterest_d.add(collFnComparisonEmpAccountDTO
              .getInterest());
          sumMoneySum_d = sumMoneySum_d.add(collFnComparisonEmpAccountDTO
              .getMoneySum());
          tempList.add(collFnComparisonEmpAccountDTO);
        }

        // 累加月合计
        sumDebit_m = sumDebit_m.add(sumDebit_d);
        sumCredit_m = sumCredit_m.add(sumCredit_d);
        sumInterest_m = sumInterest_m.add(sumInterest_d);
        sumMoneySum_m = sumMoneySum_m.add(sumMoneySum_d);
        // 该职工最后一天合计
        CollFnComparisonEmpAccountDTO dto1 = new CollFnComparisonEmpAccountDTO();
        dto1.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_DAYSUM,
            BusiConst.FNSUMMARY));
        dto1.setDebit(sumDebit_d);
        dto1.setCredit(sumCredit_d);
        dto1.setInterest(sumInterest_d);
        dto1.setMoneySum(collFnComparisonEmpAccountDTO.getMoneySum());
        dto1.setDirection(temp_dirtection);
        dto1.setCollDate(temp_date);
        tempList.add(dto1);
        // 将本日合计清空
        sumDebit_d = new BigDecimal(0.00);
        sumCredit_d = new BigDecimal(0.00);
        sumInterest_d = new BigDecimal(0.00);
        sumMoneySum_d = new BigDecimal(0.00);

        // 累加年合计
        sumDebit_y = sumDebit_y.add(sumDebit_m);
        sumCredit_y = sumCredit_y.add(sumCredit_m);
        sumInterest_y = sumInterest_y.add(sumInterest_m);
        sumMoneySum_y = sumMoneySum_y.add(sumMoneySum_m);
        // 添加月合计
        CollFnComparisonEmpAccountDTO dto2 = new CollFnComparisonEmpAccountDTO();
        dto2.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_TERMSUM,
            BusiConst.FNSUMMARY));
        dto2.setDebit(sumDebit_m);
        dto2.setCredit(sumCredit_m);
        dto2.setInterest(sumInterest_m);
        dto2.setMoneySum(collFnComparisonEmpAccountDTO.getMoneySum());
        dto2.setCollDate(temp_date);
        dto2.setDirection(temp_dirtection);
        tempList.add(dto2);
        // 将本月合计清空
        sumDebit_m = new BigDecimal(0.00);
        sumCredit_m = new BigDecimal(0.00);
        sumInterest_m = new BigDecimal(0.00);
        sumMoneySum_m = new BigDecimal(0.00);

        // 添加年合计
        CollFnComparisonEmpAccountDTO dto3 = new CollFnComparisonEmpAccountDTO();
        dto3.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_YEARSUM,
            BusiConst.FNSUMMARY));
        dto3.setDebit(sumDebit_y);
        dto3.setCredit(sumCredit_y);
        dto3.setInterest(sumInterest_y);
        dto3.setMoneySum(collFnComparisonEmpAccountDTO.getMoneySum());
        dto3.setCollDate(temp_date);
        dto3.setDirection(temp_dirtection);
        tempList.add(dto3);
        // 将本月合计清空
        sumDebit_y = new BigDecimal(0.00);
        sumCredit_y = new BigDecimal(0.00);
        sumInterest_y = new BigDecimal(0.00);
        sumMoneySum_y = new BigDecimal(0.00);

        obj[1] = tempList;
        resultList.add(obj);
        tempList = new ArrayList();
        if (i != list.size() - 1) {
          // 得到上年结转与起初余额
          qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
              collFnComparisonEmpAccountDTO.getOrgId(),
              collFnComparisonEmpAccountDTO.getEmpId(),
              collFnComparisonEmpAccountDTO.getCollDate());
          moneysum = qcye;

          CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
          // 获得年月
          String date = collFnComparisonEmpAccountDTO.getCollDate().substring(
              4, collFnComparisonEmpAccountDTO.getCollDate().length());
          if (date.equals("0101")) {
            dto.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
          } else {
            dto.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
          }
          dto.setCollDate(collFnComparisonEmpAccountDTO.getCollDate());
          dto.setMoneySum(qcye.abs());
          dto.setDirection(getDirtection(qcye));
          tempList.add(dto);
          // 添加下一职工第一条记录
          tempList.add(collFnComparisonEmpAccountDTO);
        }
        // 重新给做比较的单位编号与职工编号等属性赋值，并请空集合类tempList。
        temp_orgid = collFnComparisonEmpAccountDTO.getOrgId();
        temp_empid = collFnComparisonEmpAccountDTO.getEmpId();
        temp_orgname = collFnComparisonEmpAccountDTO.getOrgName();
        temp_empname = collFnComparisonEmpAccountDTO.getEmpName();
        temp_date = collFnComparisonEmpAccountDTO.getCollDate();
        temp_date_m = collFnComparisonEmpAccountDTO.getCollDate();
        temp_date_y = collFnComparisonEmpAccountDTO.getCollDate();
      } else {
        String date1 = "";// 用来记录上一条记录的时间
        date1 = temp_date;
        if (!temp_date.equals(collFnComparisonEmpAccountDTO.getCollDate())) {
          // 添加日合计
          CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
          dto.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_DAYSUM,
              BusiConst.FNSUMMARY));
          dto.setDebit(sumDebit_d);
          dto.setCredit(sumCredit_d);
          dto.setInterest(sumInterest_d);
          dto.setMoneySum(temp_moneysum);
          dto.setCollDate(temp_date);
          dto.setDirection(temp_dirtection);
          tempList.add(dto);

          // 累加本月合计
          sumDebit_m = sumDebit_m.add(sumDebit_d);
          sumCredit_m = sumCredit_m.add(sumCredit_d);
          sumInterest_m = sumInterest_m.add(sumInterest_d);
          sumMoneySum_m = sumMoneySum_m.add(sumMoneySum_d);

          // 将本日合计清空
          sumDebit_d = new BigDecimal(0.00);
          sumCredit_d = new BigDecimal(0.00);
          sumInterest_d = new BigDecimal(0.00);
          sumMoneySum_d = new BigDecimal(0.00);
          temp_date = collFnComparisonEmpAccountDTO.getCollDate();
        }
        if (!temp_date_m.substring(0, 6).equals(
            collFnComparisonEmpAccountDTO.getCollDate().substring(0, 6))) {
          // 添加月合计
          CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
          dto.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_TERMSUM,
              BusiConst.FNSUMMARY));
          dto.setDebit(sumDebit_m);
          dto.setCredit(sumCredit_m);
          dto.setInterest(sumInterest_m);
          dto.setMoneySum(temp_moneysum);
          dto.setCollDate(date1);
          dto.setDirection(temp_dirtection);
          tempList.add(dto);

          // 累加年合计
          sumDebit_y = sumDebit_y.add(sumDebit_m);
          sumCredit_y = sumCredit_y.add(sumCredit_m);
          sumInterest_y = sumInterest_y.add(sumInterest_m);
          sumMoneySum_y = sumMoneySum_y.add(sumMoneySum_m);

          // 将本月合计清空
          sumDebit_m = new BigDecimal(0.00);
          sumCredit_m = new BigDecimal(0.00);
          sumInterest_m = new BigDecimal(0.00);
          sumMoneySum_m = new BigDecimal(0.00);
          if (temp_date_y.substring(0, 4).equals(
              collFnComparisonEmpAccountDTO.getCollDate().substring(0, 4))) {
            // 添加年合计
            CollFnComparisonEmpAccountDTO dto1 = new CollFnComparisonEmpAccountDTO();
            dto1.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
            dto1.setDebit(sumDebit_y);
            dto1.setCredit(sumCredit_y);
            dto1.setInterest(sumInterest_y);
            dto1.setMoneySum(temp_moneysum);
            dto1.setCollDate(date1);
            dto1.setDirection(temp_dirtection);
            tempList.add(dto1);
          }

          temp_date_m = collFnComparisonEmpAccountDTO.getCollDate();
        }
        if (!temp_date_y.substring(0, 4).equals(
            collFnComparisonEmpAccountDTO.getCollDate().substring(0, 4))) {
          // 添加年合计
          CollFnComparisonEmpAccountDTO dto = new CollFnComparisonEmpAccountDTO();
          dto.setBizType(BusiTools.getBusiValue_WL(BusiConst.FNSUMMARY_YEARSUM,
              BusiConst.FNSUMMARY));
          dto.setDebit(sumDebit_y);
          dto.setCredit(sumCredit_y);
          dto.setInterest(sumInterest_y);
          dto.setMoneySum(temp_moneysum);
          dto.setCollDate(date1);
          dto.setDirection(temp_dirtection);
          tempList.add(dto);
          // 将本月合计清空
          sumDebit_y = new BigDecimal(0.00);
          sumCredit_y = new BigDecimal(0.00);
          sumInterest_y = new BigDecimal(0.00);
          sumMoneySum_y = new BigDecimal(0.00);

          // 求上年结转
          qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
              collFnComparisonEmpAccountDTO.getOrgId(),
              collFnComparisonEmpAccountDTO.getEmpId(),
              collFnComparisonEmpAccountDTO.getCollDate());
//          moneysum = moneysum.add(qcye);

          CollFnComparisonEmpAccountDTO dto1 = new CollFnComparisonEmpAccountDTO();
          // 获得年月
          String date = collFnComparisonEmpAccountDTO.getCollDate().substring(
              4, collFnComparisonEmpAccountDTO.getCollDate().length());
          if (date.equals("0101")) {
            dto1.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
          } else {
            dto1.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
          }
          dto1.setCollDate(collFnComparisonEmpAccountDTO.getCollDate());
          dto1.setMoneySum(qcye.abs());
          dto1.setDirection(getDirtection(qcye));
          tempList.add(dto1);

          temp_date_y = collFnComparisonEmpAccountDTO.getCollDate();
        }
        // 封装改职工的职工账
        tempList.add(collFnComparisonEmpAccountDTO);
      }
      if (i != 0) {
        // 累加日合计
        sumDebit_d = sumDebit_d.add(collFnComparisonEmpAccountDTO.getDebit());
        sumCredit_d = sumCredit_d
            .add(collFnComparisonEmpAccountDTO.getCredit());
        sumInterest_d = sumInterest_d.add(collFnComparisonEmpAccountDTO
            .getInterest());
        sumMoneySum_d = sumMoneySum_d.add(collFnComparisonEmpAccountDTO
            .getMoneySum());
      }
      temp_moneysum = moneysum.abs();
      temp_dirtection = collFnComparisonEmpAccountDTO.getDirection();
    }

    return resultList;
  }

  /**
   * 根据插叙条件统计查询单位归集信息
   */
  public Object[] findOrgCollInfo(Pagination pagination) {
    // TODO Auto-generated method stub
    Object[] resultList = new Object[2];
    Object[] resultSum = new Object[2];
    OrgCollinfoSumDTO orgCollinfoSumDTO = new OrgCollinfoSumDTO();
    List orgCollInfoFindResultDTOs = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgCollInfoFindDTO dto = (OrgCollInfoFindDTO) pagination
        .getQueryCriterions().get("searchDTO");
    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("securityInfo");
    // 如果条件为空时不进行查询。
    if (dto != null) {
      // 查询将在列表中显示的统计内容

      List list = orgDAO.queryStatisticOrgCollInfo(dto, orderBy, order, start,
          pageSize, securityInfo);

      // 转换OrgCollInfoFindResultDTO中的属性，转换内容包括:中文的转换与小数格式的转换
      for (int i = 0; i < list.size(); i++) {

        OrgCollInfoFindResultDTO orgCollInfoFindResultDTO = (OrgCollInfoFindResultDTO) list
            .get(i);
        orgCollInfoFindResultDTO.setEmpPay(orgCollInfoFindResultDTO.getEmpPay()
            .setScale(2));
        orgCollInfoFindResultDTO.setOrgPay(orgCollInfoFindResultDTO.getOrgPay()
            .setScale(2));
        orgCollInfoFindResultDTO.setPaySum(orgCollInfoFindResultDTO.getPaySum()
            .setScale(2, BigDecimal.ROUND_HALF_UP));
        orgCollInfoFindResultDTO.setSalarySum(orgCollInfoFindResultDTO
            .getSalarySum().setScale(2));
        orgCollInfoFindResultDTO.setBalance(orgCollInfoFindResultDTO
            .getBalance().setScale(2));
        orgCollInfoFindResultDTO.setOverPay(orgCollInfoFindResultDTO
            .getOverPay().setScale(2));
        orgCollInfoFindResultDTO.setPaybalance(orgCollInfoFindResultDTO
            .getPaybalance().setScale(2, BigDecimal.ROUND_HALF_UP));
        // 归集银行
        CollBank collBankdto = collBankDAO
            .getCollBankByCollBankid(orgCollInfoFindResultDTO
                .getCollectionBankId());
        orgCollInfoFindResultDTO.setCollectionBankId(collBankdto
            .getCollBankName());
        // 办事处
        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(orgCollInfoFindResultDTO
                .getOfficecode());
        orgCollInfoFindResultDTO.setOfficecode(organizationUnit.getName());

        // 吴洪涛修改2008-1-3//5) 单位归集信息中，实存人数应为
        orgCollInfoFindResultDTO.setPersonCount(orgCollInfoFindResultDTO
            .getPersonCount());

        // 吴洪涛修改2008-1-3//5) 单位归集信息中，实存人数应为

        orgCollInfoFindResultDTOs.add(orgCollInfoFindResultDTO);
      }
      /*
       * 求出合计与count，resultSum[0]是合计的List，resultSum[1]count
       */
      resultSum = orgDAO.queryStatisticCountOrgCollInfo(dto, securityInfo);
      // 求出count
      pagination.setNrOfElements(new Integer((String) resultSum[1]).intValue());
      // 计算合计
      double empPay = 0.00;
      double orgPay = 0.00;
      int empSum = 0;
      double overPay = 0.00;
      double payBalance = 0.00;
      double paySum = 0.00;
      double salarySum = 0.00;
      double balance = 0.00;
      int allEmpSum = 0;
      List sumList = (List) resultSum[0];
      for (int i = 0; i < sumList.size(); i++) {
        OrgCollinfoSumDTO temp_DTO = (OrgCollinfoSumDTO) sumList.get(i);
        empPay += temp_DTO.getEmpPay().doubleValue();
        orgPay += temp_DTO.getOrgPay().doubleValue();
        empSum += temp_DTO.getEmpSum().intValue();
        overPay += temp_DTO.getOverPay().doubleValue();
        payBalance += temp_DTO.getPaybalance().doubleValue();
        paySum += temp_DTO.getPaySum().doubleValue();
        salarySum += temp_DTO.getSalarySum().doubleValue();
        balance += temp_DTO.getBalance().doubleValue();
        allEmpSum += temp_DTO.getPersonCount().intValue();
      }

      orgCollinfoSumDTO.setEmpPay(new BigDecimal(empPay).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setOrgPay(new BigDecimal(orgPay).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setOverPay(new BigDecimal(overPay).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setPaybalance(new BigDecimal(payBalance).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setPaySum(new BigDecimal(paySum).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setSalarySum(new BigDecimal(salarySum).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setBalance(new BigDecimal(balance).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setEmpSum(new Integer(empSum));
      orgCollinfoSumDTO.setPersonCount(new Integer(allEmpSum));
    }
    // 将列表中的信息放入Object[]中
    resultList[0] = orgCollInfoFindResultDTOs;
    resultList[1] = orgCollinfoSumDTO;

    return resultList;
  }
  public List queryqcye(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {

    String orgId = "";
    String orgName = "";
    String empId = "";
    String empName = "";
    String cardNum = "";
    String timeSt = "";
    String timeEnd = "";

    BigDecimal qcye = new BigDecimal(0.00);
    String aspect = "";
    BigDecimal moneysum = new BigDecimal(0.00);
    BigDecimal debit = new BigDecimal(0.00);
    BigDecimal credit = new BigDecimal(0.00);

    if (pagination.getQueryCriterions().get("orgId") != null) {
      orgId = (String) pagination.getQueryCriterions().get("orgId");
    }
    if (pagination.getQueryCriterions().get("empId") != null) {
      empId = (String) pagination.getQueryCriterions().get("empId");
    }
    if (pagination.getQueryCriterions().get("orgName") != null) {
      orgName = (String) pagination.getQueryCriterions().get("orgName");
    }
    if (pagination.getQueryCriterions().get("empName") != null) {
      empName = (String) pagination.getQueryCriterions().get("empName");
    }
    if (pagination.getQueryCriterions().get("cardNum") != null) {
      cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    }
    if (pagination.getQueryCriterions().get("timeSt") != null) {
      timeSt = (String) pagination.getQueryCriterions().get("timeSt");
    }
    if (pagination.getQueryCriterions().get("timeEnd") != null) {
      timeEnd = (String) pagination.getQueryCriterions().get("timeEnd");
    }

    List list = empDAO.queryCollFnComparisonEmpAccountCount(orgId, empId,
        orgName, empName, cardNum, timeSt, timeEnd);

    for (int i = 0; i < list.size(); i++) {
      CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO = (CollFnComparisonEmpAccountDTO) list
          .get(i);
      if (i == 0) {
        qcye = orgHAFAccountFlowDAO.queryqcyeTrail(
            collFnComparisonEmpAccountDTO.getOrgId(),
            collFnComparisonEmpAccountDTO.getEmpId(),
            collFnComparisonEmpAccountDTO.getCollDate());
        moneysum = moneysum.add(qcye);
      }
      debit = collFnComparisonEmpAccountDTO.getDebit();
      credit = collFnComparisonEmpAccountDTO.getCredit();
      aspect = this.getDirtection(moneysum);
      if (aspect.equals("借")) {
        if (credit.compareTo(new BigDecimal(0)) > 0
            && (credit.compareTo(moneysum.abs())) > 0) {
          moneysum = moneysum.add(credit).abs();
        } else {
          moneysum = moneysum.add(debit).subtract(credit);
        }
      } else {
        moneysum = moneysum.add(credit).subtract(debit);
      }
      aspect = this.getDirtection(moneysum);
      collFnComparisonEmpAccountDTO.setMoneySum(moneysum.abs());
      collFnComparisonEmpAccountDTO.setDirection(aspect);
    }

    return list;
  }

  public List queryqcyeorg(Pagination pagination, SecurityInfo securityInfo,
      String orgId_1) throws Exception {

    BigDecimal moneysum = new BigDecimal(0.00);
    BigDecimal debit = new BigDecimal(0.00);
    BigDecimal credit = new BigDecimal(0.00);
    String aspect = "";
    BigDecimal qcye = new BigDecimal(0.00);
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String bankId = (String) pagination.getQueryCriterions().get("bankId");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String timeSt = (String) pagination.getQueryCriterions().get("timeSt");
    String timeEnd = (String) pagination.getQueryCriterions().get("timeEnd");

    List countList = orgHAFAccountFlowDAO.queryCollFnComparisonCountList(
        officeCode, bankId, orgId, orgId_1, orgName, timeSt, timeEnd,
        securityInfo, null, null);
    for (int i = 0; i < countList.size(); i++) {
      CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO = (CollFnComparisonOrgAccountDTO) countList
          .get(i);
      if (i == 0) {
        qcye = orgHAFAccountFlowDAO.queryqcye(collFnComparisonOrgAccountDTO
            .getOrg_id(), collFnComparisonOrgAccountDTO.getCollsett_date());
        moneysum = qcye;
      }
      debit = collFnComparisonOrgAccountDTO.getDebit();
      credit = collFnComparisonOrgAccountDTO.getCredit();
      aspect = this.getDirtection(moneysum);
      if (aspect.equals("借")) {
        if (credit.compareTo(new BigDecimal(0)) > 0
            && (credit.compareTo(moneysum.abs())) > 0) {
          moneysum = moneysum.add(credit).abs();
        } else {
          moneysum = moneysum.add(debit).subtract(credit);
        }
      } else {
        moneysum = moneysum.add(credit).subtract(debit);
      }
      aspect = this.getDirtection(moneysum);
      collFnComparisonOrgAccountDTO.setMoneySum(moneysum.abs());
      collFnComparisonOrgAccountDTO.setAspect(aspect);
    }
    return countList;
  }

}
