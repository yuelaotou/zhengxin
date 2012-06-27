package org.xpup.hafmis.syscollection.pickupmng.pickup.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDrawingDAO;
import org.xpup.hafmis.syscollection.common.dao.PartPickupConditionDAO;
import org.xpup.hafmis.syscollection.common.dao.PickBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.PickTailDAO;
import org.xpup.hafmis.syscollection.common.dao.SpecialPickDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.PickTailDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.PickupHeadDaoDW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowDrawing;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.EmpInfoPick;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.OrgSearchConditionDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickHeadImportDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickInterestReteDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickTailImportDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.NameAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickGetEmployeeInfoAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupAddAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule.DrawRuleDAO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * @author 李文浩 普通提取的业务类
 */
public class PickBS implements IPickupBS {
  private SpecialPickDAO specialPickDao;

  String temp_headid = "";

  private PickHeadDAO headDao;

  private HafOperateLogDAO hafOperateLogDAO;

  private DocNumMaintainDAO maintainDao;

  private CollParaDAO collParaDAO;

  private AutoInfoPickDAODW autoInfoPickDAODW;

  private DocNumCancelDAO insertDao;

  private EmpDAODW empDAODW;

  private EmpHAFAccountFlowDAO empFlowDao;

  private PickupHeadDaoDW pickupheadDaoDW;

  private PickTailDAODW tailDaoDW;

  private OrgHAFAccountFlowDrawingDAO orgFlowDao;

  private BizActivityLogDAO bizActivityLogDAO;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private DrawRuleDAO drawRuleDAO;

  public void setDrawRuleDAO(DrawRuleDAO drawRuleDAO) {
    this.drawRuleDAO = drawRuleDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setPickupheadDaoDW(PickupHeadDaoDW pickupheadDaoDW) {
    this.pickupheadDaoDW = pickupheadDaoDW;
  }

  public void setTailDaoDW(PickTailDAODW tailDaoDW) {
    this.tailDaoDW = tailDaoDW;
  }

  private PickTailDAO tailDao;

  private HafOperateLogDAO hafDao;

  private OrgDAO orgDao;

  private PickBizActivityLogDAO pickBizDao;

  private EmpDAO empDao;

  private CollBankDAO collBankDAO;

  private AutoInfoPickDAO autoInfoPickDAO;

  private OrganizationUnitDAO organizationUnitDAO;

  private OrgInfoDAO orgInfoDao;

  private AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO;

  private PartPickupConditionDAO partPickupConditionDAO;

  public AdjustWrongAccountHeadDAO getAdjustWrongAccountHeadDAO() {
    return adjustWrongAccountHeadDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setAdjustWrongAccountHeadDAO(
      AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO) {
    this.adjustWrongAccountHeadDAO = adjustWrongAccountHeadDAO;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setHeadDao(PickHeadDAO headDao) {
    this.headDao = headDao;
  }

  public void setOrgInfoDao(OrgInfoDAO orgInfoDao) {
    this.orgInfoDao = orgInfoDao;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setTailDao(PickTailDAO tailDao) {
    this.tailDao = tailDao;
  }

  public SpecialPick findSpecialPickByOrgId(int id) throws BusinessException {
    return specialPickDao.findSpecialPickByOrgId(id);
  }

  public void setOrgDao(OrgDAO orgDao) {
    this.orgDao = orgDao;
  }

  public Org orgFindById(int id, SecurityInfo info) throws BusinessException {// 根据id找出来单位--加权限
    return orgDao.findById(new Integer(id), info);
  }

  public Org getOrgByHeadId(Integer headId) throws BusinessException {
    return headDao.getOrgByPickHeadId(headId);
  }

  public OrgInfo orgInfoFindById(int id) throws BusinessException {
    return (OrgInfo) orgInfoDao.queryById(new Integer(id));
  }

  public OrgInfo findOrgInfoStatus(Serializable id) throws BusinessException {
    return (OrgInfo) orgInfoDao.findOrgInfoStatus(id);
  }

  public OrgInfo findOrgInfoStatus1(Serializable id) throws BusinessException {
    return (OrgInfo) orgInfoDao.findOrgInfoStatus1(id);
  }

  public PickHead headFindById(Serializable id) throws BusinessException {
    return (PickHead) headFindById(id);
  }

  public PickHead findPickHeadOrgIdStatusIsOne(int id) throws BusinessException {// 找到AA306状态为1的结果集..状态为1的只能有一条记录
    return headDao.findByOrgId(id);
  }

  public List findTailByHeadId(PickHead head) throws BusinessException {
    return tailDao.findTailByHeadId(head);
  }

  public PickHead queryById(Integer headId) throws BusinessException {
    return headDao.queryById(headId);
  }

  public void updatePickHeadReserveaA(Integer headId, String status,
      String registrations) throws BusinessException {
    PickHead pickHead = headDao.queryById(headId);
    if(status.equals("0")){
      String srea = "";
      try {
        srea = getAA306_1();
      } catch (Exception e) {
        e.printStackTrace();
      }
      List list = tailDao.findPickTailByPickHeadId(Integer.parseInt(pickHead.getId().toString()));
      PickTail tail = (PickTail)list.get(0);
      String s[]=srea.split(",");
      String y = "a";
      for(int i=0;i<s.length;i++){
        if(tail.getPickReason().toString().equals(s[i])){
          y="b";
        }
      }
      if(y.equals("a")){
        pickHead.setReserveaB("0");
      }else{
        pickHead.setReserveaB("1");
      }
    }
    pickHead.setReserveaA(status);
    String docNum = pickHead.getDocNum();
    String notNum = pickHead.getNoteNum();
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO.updateByDocNum(
        docNum, notNum);
    orgHAFAccountFlow.setRegistrations(registrations);
  }

  public boolean queryUnitAcc(String org_id) throws BusinessException {
    String str = headDao.queryUnitAcc(org_id);
    if (str.equals("0")) {
      return true;
    } else {
      return false;
    }
  }

  // 查询银行
  public String findCollBank(String collBankid) {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }

  /**
   * 
   */
  public boolean getOrgPayMonthEmpPayMonth(String orgid, String empid) {
    String ss = "";
    try {
      ss = empDao.getOrgPayMonthEmpPayMonth(orgid, empid);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return ss.equals("1");
  }

  public String getAA307PhotoURL(String pickheadid) {
    String ss = "";
    try {
      ss = empDao.getAA307PhotoURL(pickheadid);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return ss;
  }

  /**
   * 根据不同的条件来获得单位提取的的信息..
   */
  public List getVindicatePageData(Pagination pa, SecurityInfo info) {
    try {
      String orgId = (String) pa.getQueryCriterions().get("orgId");
      String orgName = (String) pa.getQueryCriterions().get("orgName");
      String noteNumber = (String) pa.getQueryCriterions().get("noteNumber");
      String docNumber = (String) pa.getQueryCriterions().get("docNumber");
      String start = (String) pa.getQueryCriterions().get("start");
      String end = (String) pa.getQueryCriterions().get("end");
      String pickDate = (String) pa.getQueryCriterions().get("pickDate");
      String pickDate_end = (String) pa.getQueryCriterions()
          .get("pickDate_end");
      String state = (String) pa.getQueryCriterions().get("state");
      String pickType = (String) pa.getQueryCriterions().get("pickType");
      String select = (String) pa.getQueryCriterions().get("select");
      String reason = (String) pa.getQueryCriterions().get("reason");
      String collectionBank = (String) pa.getQueryCriterions().get(
          "collectionBank");
      String orderBy = (String) pa.getOrderBy();
      String order = (String) pa.getOrderother();
      OrgSearchConditionDTO search = new OrgSearchConditionDTO();
      if (orgId != null)
        search.setOrgId(orgId.trim());
      if (orgName != null)
        search.setOrgName(orgName.trim());
      if (noteNumber != null)
        search.setNoteNumber(noteNumber.trim());
      if (docNumber != null)
        search.setDocNumber(docNumber.trim());
      if (start != null)
        search.setStart(start.trim());
      if (end != null)
        search.setEnd(end.trim());
      if (pickDate != null)
        search.setPickDate(pickDate.trim());
      if (pickDate_end != null)
        search.setPickDate_end(pickDate_end.trim());
      if (state != null)
        search.setState(state.trim());
      if (pickDate != null)
        search.setPickDate(pickDate.trim());
      if (select != null)
        search.setSelect(select.trim());
      if (reason != null) {
        search.setReason(reason);
      }
      if (pickType != null) {
        search.setPickType(pickType);
      }
      if (collectionBank != null) {
        search.setCollectionBank(collectionBank);
      }
      int firstStart = pa.getFirstElementOnPage() - 1;
      int pageSize = pa.getPageSize();
      List list = headDao.getVindicatePageData(search, orderBy, order,
          firstStart, pageSize, info);// 给数据加权限..
      int count = headDao.getVindicateCount(search, info);
      pa.setNrOfElements(count);
      if (!list.isEmpty()) {
        for (int i = 0; i < list.size(); i++) {
          PickHead h = (PickHead) list.get(i);
          h.setBusinessState(BusiTools.getBusiValue(h.getPickSatatus()
              .intValue(), BusiConst.BUSINESSSTATE));
          if (h.getReserveaB()!=null && h.getReserveaB().equals("0")) {
            h.setReserveaC("审批通过");
          } else {
            h.setReserveaC("审批未通过");
          }
          h.setReserveaB(headDao.getOperator_yg(h.getId().toString()));
          if (h.getReserveaA().equals("0")) {
            h.setReserveaA("审核通过");
          } else if (h.getReserveaA().equals("1")) {
            h.setReserveaA("审核未通过");
          }
        }
      }
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 获得维护提取的全部数据。。 以后用这个方法来算出来总数的汇总
   */
  public List getVindicateAllDate(Pagination pa, SecurityInfo info)
      throws BusinessException {
    String orgId = (String) pa.getQueryCriterions().get("orgId");
    String orgName = (String) pa.getQueryCriterions().get("orgName");
    String noteNumber = (String) pa.getQueryCriterions().get("noteNumber");
    String docNumber = (String) pa.getQueryCriterions().get("docNumber");
    String start = (String) pa.getQueryCriterions().get("start");
    String end = (String) pa.getQueryCriterions().get("end");
    String pickDate = (String) pa.getQueryCriterions().get("pickDate");
    String pickDate_end = (String) pa.getQueryCriterions().get("pickDate_end");
    String state = (String) pa.getQueryCriterions().get("state");
    String pickType = (String) pa.getQueryCriterions().get("pickType");
    String select = (String) pa.getQueryCriterions().get("select");
    String reason = (String) pa.getQueryCriterions().get("reason");
    String collectionBank = (String) pa.getQueryCriterions().get(
        "collectionBank");
    String orderBy = (String) pa.getOrderBy();
    String order = (String) pa.getOrderother();
    OrgSearchConditionDTO search = new OrgSearchConditionDTO();
    if (orgId != null)
      search.setOrgId(orgId.trim());
    if (orgName != null)
      search.setOrgName(orgName.trim());
    if (noteNumber != null)
      search.setNoteNumber(noteNumber.trim());
    if (docNumber != null)
      search.setDocNumber(docNumber.trim());
    if (start != null)
      search.setStart(start.trim());
    if (end != null)
      search.setEnd(end.trim());
    if (pickDate != null)
      search.setPickDate(pickDate.trim());
    if (pickDate_end != null)
      search.setPickDate_end(pickDate_end.trim());
    if (state != null)
      search.setState(state.trim());
    if (pickDate != null)
      search.setPickDate(pickDate.trim());
    if (select != null)
      search.setSelect(select.trim());
    if (reason != null) {
      search.setReason(reason);
    }
    if (pickType != null) {
      search.setPickType(pickType);
    }
    if (collectionBank != null) {
      search.setCollectionBank(collectionBank);
    }
    return headDao.getVindicateAllDate(search, info);
  }

  public List getEmployeePickCountMoney(Pagination p) throws BusinessException {
    String empId = (String) p.getQueryCriterions().get("empId");
    String time = (String) p.getQueryCriterions().get("time");
    String reason = (String) p.getQueryCriterions().get("reason");
    List list = tailDao.getEmployeePickCountMoney(empId, time, reason);
    return list;
  }

  /**
   * 根据点超连接可以获得此单位下的所有员工做的业务
   * 
   * @return
   */
  public List findTheOrgAllEmployee(Pagination p) throws BusinessException {
    String id = (String) p.getQueryCriterions().get("idValue");// 获得用户提交的id
    String orderBy = (String) p.getOrderBy();
    String order = (String) p.getOrderother();
    int start = p.getFirstElementOnPage() - 1;
    int pageSize = p.getPageSize();
    List list = tailDao.findTheOrgAllEmployee_LY(id, orderBy, order, start,
        pageSize);
    try {
      for (int i = 0; i < list.size(); i++) {// 转换提取类型的一个小小循环
        PickTail pick = (PickTail) list.get(i);
        pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
            .intValue(), BusiConst.PICKUPTYPE));
      }
      for (int i = 0; i < list.size(); i++) {// z
        PickTail tail = (PickTail) list.get(i);
        int reason = tail.getPickReason().intValue();
        if (reason <= 6) // 部分提取
          tail.setReason(BusiTools.getBusiValue(reason, BusiConst.SOMEPICK));
        else
          // 销户提取
          tail.setReason(BusiTools.getBusiValue(reason, BusiConst.DISTROYPICK));
        tail.setTotal(tail.getPickSalary().add(tail.getPickInterest()));
      }
    } catch (Exception s) {
      s.printStackTrace();
    }
    Object[] obj = tailDao.getTheOrgAllEmployee_LY(Integer.parseInt(id));
    int count = ((Integer) obj[0]).intValue();
    p.setNrOfElements(count);
    return list;
  }

  public List getPrintAllEmployeeList(String id) throws BusinessException {
    Object[] obj = tailDao.getTheOrgAllEmployee_LY(Integer.parseInt(id));
    List list = (List) obj[1];
    try {
      // for (int i = 0; i < list.size(); i++) {// 转换提取类型的一个小小循环
      // PickTail pick = (PickTail) list.get(i);
      // pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
      // .intValue(), BusiConst.PICKUPTYPE));
      // }
      // for (int i = 0; i < list.size(); i++) {// z
      // PickTail tail = (PickTail) list.get(i);
      // int reason = tail.getPickReason().intValue();
      // if (reason <= 3) // 部分提取
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.SOMEPICK));
      // else
      // // 销户提取
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.DISTROYPICK));
      // }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List getPrintAllEmployeeList_yg(String id) throws BusinessException {
    List list = tailDao.getTheOrgAllEmployee_LY_YG(Integer.parseInt(id));

    try {
      // for (int i = 0; i < list.size(); i++) {// 转换提取类型的一个小小循环
      // PickTail pick = (PickTail) list.get(i);
      // pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
      // .intValue(), BusiConst.PICKUPTYPE));
      // }
      // for (int i = 0; i < list.size(); i++) {// z
      // PickTail tail = (PickTail) list.get(i);
      // int reason = tail.getPickReason().intValue();
      // if (reason <= 3) // 部分提取
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.SOMEPICK));
      // else
      // // 销户提取
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.DISTROYPICK));
      // }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public String getPrintAllEmployeeList_yga(String id) throws BusinessException {
    return tailDao.getTheOrgAllEmployee_LY_YGA(Integer.parseInt(id));
  }

  /** 获得第一页的方法 */
  public List findFirstPageData(Pagination p) {
    String id = (String) p.getQueryCriterions().get("idValue");// 获得用户提交的id
    String orderBy = (String) p.getOrderBy();
    String order = (String) p.getOrderother();
    int start = p.getFirstElementOnPage() - 1;
    int pageSize = p.getPageSize();
    List list = tailDao.findFirstPageData_LY(id, orderBy, order, start,
        pageSize);
    if (list == null || list.isEmpty() || list.size() == 0) {
      // 这个地方是防止删除的...如果单选删除的时候..删除的是最后一页的数据..list会为0..但是数据库里还有数据..
      // 所以我们又用findFirstPageData()start为0在查一遍..这样就能够确保程序的运行
      list = tailDao.findFirstPageData_LY(id, orderBy, order, 0, pageSize);
    }
    // try {
    // for (int i = 0; i < list.size(); i++) {// 转换提取类型的一个小小循环
    // PickTail pick = (PickTail) list.get(i);
    // if (pick.getPickReason().intValue() <= 3) {
    // pick.setReason(BusiTools.getBusiValue(
    // pick.getPickReason().intValue(), BusiConst.SOMEPICK));
    // } else if (pick.getPickReason().intValue() > 3) {
    // pick.setReason(BusiTools.getBusiValue(
    // pick.getPickReason().intValue(), BusiConst.DISTROYPICK));
    // }
    // pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
    // .intValue(), BusiConst.PICKUPTYPE));
    // }
    // } catch (Exception s) {
    // s.printStackTrace();
    // }
    int count = tailDao.findFirstPageDataCount(id);
    p.setNrOfElements(count);
    return list;
  }

  public List getFirstPageCount(String id) throws BusinessException {
    Object[] obj = tailDao.queryPickTailCount_LY(Integer.parseInt(id));
    List list = (List) obj[1];
    return list;
  }

  /**
   * 根据职工的id和单位的id来判断此单位的状态是否<5
   */
  public List findPickHeadStateByOrgIdAndEmpId(int orgId, int empId)
      throws BusinessException {
    return tailDao.findPickHeadStateByOrgIdAndEmpId(orgId, empId);
  }

  /**
   * 根据单位编号来判断提取状态是否!=5
   */
  public PickHead findStatusNotIsFive(int id) throws BusinessException {
    return headDao.findStatusNotIsFive(id);
  }

  public void setSpecialPickDao(SpecialPickDAO specialPickDao) {
    this.specialPickDao = specialPickDao;
  }

  /**
   * 李文浩 根据职工编号得出来年提取金额
   */
  public List getYearPickBalance(int empId, SecurityInfo securityInfo) {
    return tailDao.getYearPickBalanceAndCount(empId, securityInfo);
  }

  /**
   * 李文浩... 根据职工编号和单位单位编号求出来此职工是否存在
   */
  public Emp findEmpByOrdIdAndEmpId(Integer orgId, Integer empId)
      throws BusinessException {
    return empDao.findEmpByOrdIdAndEmpId(orgId, empId);
  }

  /**
   * 李文浩.. 判断输入的职工编号是否在尾表里存在
   */
  public List findPickTailByEmpId(Integer empId) throws BusinessException {
    return tailDao.findPickTailByEmpId(empId.intValue());
  }

  public void setEmpDao(EmpDAO empDao) {
    this.empDao = empDao;
  }

  /**
   * 李文浩 获取自己的销户的利息
   */
  public BigDecimal getDistroyPickupInterest1(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    System.out.println(this.getCurInterest(orgId, empId, moneyDate).add(
        this.getPreInterest(orgId, empId, moneyDate)).divide(new BigDecimal(1),
        2, BigDecimal.ROUND_HALF_DOWN));
    return this.getCurInterest(orgId, empId, moneyDate).add(
        this.getPreInterest(orgId, empId, moneyDate)).divide(new BigDecimal(1),
        2, BigDecimal.ROUND_HALF_DOWN);
  }

  public double getDistroyPickupInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    return 0;
  }

  /**
   * 获得本年利息
   */
  public BigDecimal getCurInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    Org org = orgDao.queryById(orgId);
    String office = org.getOrgInfo().getOfficecode();
    BigDecimal curInterest = new BigDecimal(0.00);
    BigDecimal curSubA = null;
    BigDecimal curSubB = null;
    BigDecimal curSubC = null;
    BigDecimal curSubD = null;
    BigDecimal curSubE = null;
    BigDecimal curSubF = null;
    BigDecimal curSubG = null;
    BigDecimal curSubH = null;
    BigDecimal curSubI = null;
    BigDecimal curSubJ = null;
    BigDecimal curSubK = null;
    BigDecimal curSubL = null;
    double curIntegral = tailDao.getCurInt(orgId, empId, moneyDate);
    BigDecimal bCurIntegral = new BigDecimal(curIntegral);
    bCurIntegral = bCurIntegral.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_DOWN);
    curSubA = tailDao.getCurSubA(orgId.intValue(), empId.intValue());
    curSubB = tailDao.getCurSubB(orgId.intValue(), empId.intValue());
    curSubC = tailDao.getCurSubC(orgId.intValue(), empId.intValue());
    curSubD = tailDao.getCurSubD(orgId.intValue(), empId.intValue());
    curSubE = tailDao.getCurSubE(orgId.intValue(), empId.intValue());
    curSubF = tailDao.getCurSubF(orgId.intValue(), empId.intValue());
    curSubG = tailDao.getCurSubG(orgId.intValue(), empId.intValue());
    curSubH = tailDao.getCurSubH(orgId.intValue(), empId.intValue());
    curSubI = tailDao.getCurSubI(orgId.intValue(), empId.intValue());
    curSubJ = tailDao.getCurSubJ(orgId.intValue(), empId.intValue());
    curSubK = tailDao.getCurSubK(orgId.intValue(), empId.intValue());
    curSubL = tailDao.getCurSubL(orgId.intValue(), empId.intValue());
    double curRate = tailDao.getCurRate(office);
    System.out.println("--bPreIntegral=" + bCurIntegral + "--preRate="
        + curRate);
    curInterest = bCurIntegral.multiply(new BigDecimal(curRate)).divide(
        new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
    System.out.println("--curInterest=" + curInterest);
    curInterest = curInterest.add(curSubA).add(curSubB).add(curSubC).add(
        curSubD).add(curSubE).add(curSubF).add(curSubG).add(curSubH).add(
        curSubI).add(curSubJ).add(curSubK).add(curSubL);
    System.out.println("---curInterest=" + curInterest);
    return curInterest;
  }

  /**
   * 获得往年的利息
   */
  public BigDecimal getPreInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    Org org = orgDao.queryById(orgId);
    String office = org.getOrgInfo().getOfficecode();
    // 往年利息
    BigDecimal preInterest = new BigDecimal(0.00);
    BigDecimal preSubA = null;
    BigDecimal preSubB = null;
    BigDecimal preSubC = null;
    BigDecimal preSubD = null;
    BigDecimal preSubE = null;
    BigDecimal preSubF = null;
    BigDecimal preSubG = null;
    BigDecimal preSubH = null;
    BigDecimal preSubI = null;
    BigDecimal preSubJ = null;
    BigDecimal preSubK = null;
    BigDecimal preSubL = null;
    // double preSub = pickTailDAO.getPreSub(orgId.intValue(),
    // empId.intValue());
    // 往年积数
    double preIntegral = tailDao.getPreInt(orgId, empId, moneyDate);
    BigDecimal bPreIntegral = new BigDecimal(preIntegral);
    bPreIntegral = bPreIntegral.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_DOWN);
    preSubA = tailDao.getPreSubA(orgId.intValue(), empId.intValue());
    preSubB = tailDao.getPreSubB(orgId.intValue(), empId.intValue());
    preSubC = tailDao.getPreSubC(orgId.intValue(), empId.intValue());
    preSubD = tailDao.getPreSubD(orgId.intValue(), empId.intValue());
    preSubE = tailDao.getPreSubE(orgId.intValue(), empId.intValue());
    preSubF = tailDao.getPreSubF(orgId.intValue(), empId.intValue());
    preSubG = tailDao.getPreSubG(orgId.intValue(), empId.intValue());
    preSubH = tailDao.getPreSubH(orgId.intValue(), empId.intValue());
    preSubI = tailDao.getPreSubI(orgId.intValue(), empId.intValue());
    preSubJ = tailDao.getPreSubJ(orgId.intValue(), empId.intValue());
    preSubK = tailDao.getPreSubK(orgId.intValue(), empId.intValue());
    preSubL = tailDao.getPreSubL(orgId.intValue(), empId.intValue());
    // 往年利率
    double preRate = tailDao.getPreRate(office);
    System.out.println("--bPreIntegral=" + bPreIntegral + "--preRate="
        + preRate);
    preInterest = bPreIntegral.multiply(new BigDecimal(preRate)).divide(
        new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
    System.out.println("--preInterest=" + preInterest);
    preInterest = preInterest.add(preSubA).add(preSubB).add(preSubC).add(
        preSubD).add(preSubE).add(preSubF).add(preSubG).add(preSubH).add(
        preSubI).add(preSubJ).add(preSubK).add(preSubL);
    System.out.println("---preInterest=" + preInterest);
    return preInterest;
  }

  /**
   * 判断是否为特殊提取
   */
  public SpecialPick isSpecialPick(int orgId, int empId)
      throws BusinessException {
    return specialPickDao.isSpecialPick(orgId, empId);
  }

  /**
   * 添加的方法 李文浩
   */
  public void savePickup(PickupAddAF add,
      PickGetEmployeeInfoAF pickGetEmployeeInfoAF, SpecialPick sp, String ip,
      String userName, String moneyDate) throws BusinessException {
    try {
      String pickBalance = add.getPickBalance();
      String reason = add.getReason();
      String type = add.getType();
      Integer orgId = new Integer(add.getOrgId());
      Integer empId = new Integer(add.getEmpId());
      HafOperateLog haf = new HafOperateLog();
      PickBizActivityLog biz = new PickBizActivityLog();
      String noteNumber = null;
      String other_card_num = "";
      if (add.getNoteNumber() != null && !add.getNoteNumber().equals(""))
        noteNumber = add.getNoteNumber();
      if (add.getOther_card_num() != null
          && !add.getOther_card_num().equals(""))
        other_card_num = add.getOther_card_num();
      // 添加业务的时候，先判断一下该职工是否已经存在在业务尾表（头表状态：录入清册）中，是给出错误提示；否走正常添加业务。20080317
      List list_gjp = headDao.isDoubleAdd(orgId, empId);
      if (list_gjp.size() != 0) {
        throw new BusinessException("该职工提取信息已经进入系统，请不要重复操作！");
      }

      PickHead head = new PickHead();
      PickTail tail = new PickTail();// 这个地方是找有表的id
      Serializable isHeadIdValue = headDao.findPickHeadStatueOneByOrgId(orgId
          .intValue());// 看看头表是否存在记录
      String headId = null;
      if (sp != null) {// 如果是特殊提取..更新特殊提取的表
        // 添加业务的时候，先判断一下该职工是否已经存在在业务尾表（头表状态：未使用）中，是给出错误提示；否走正常添加业务。20080317
        List specialPickList_gjp = headDao
            .isDoubleAdd_SpecialPick(orgId, empId);
        if (specialPickList_gjp.size() != 0) {
          throw new BusinessException("该职工提取信息已经进入系统，请不要重复操作！");
        }
        sp.setIsPick(new BigDecimal(2));
        specialPickDao.updateIsPick(sp);// 只是改变特殊提取的状态..
      }
      if (isHeadIdValue == null) {// 如果头表不存在..
        head.setPickSatatus(new BigDecimal(1));
        if (noteNumber != null)// 如果存在票据编号
          head.setNoteNum(noteNumber);
        head.setReserveaA("1");
        head.getOrg().setId(orgId);
        // 这个地方插入值以后 不能够用Serializable的变量来接收 必须用String或Integer来接收
        headId = headDao.insert(head).toString();// 头表不存在...插入头表..返回刚插入的id值
      }
      if (headId == null) {// 说明头表存在记录
        headId = isHeadIdValue.toString();// 把找到的那条记录的id给headId
      }
      EmpInfoPick emp = tailDao.getEmpInfo_LY(orgId, empId);// 获得Emp对象
      // 可以根据这个对象来取出各种余额
      if (sp != null)// 是特殊提取...
        tail.setSpecialPick(sp);// 插入特殊提取的id...
      if (add.getType().equals("1")) {// 如果是部分提取..
        PartPickupConditionDTO partPickupConditionDTO = partPickupConditionDAO
            .queryPartPickupConditionInfo();

        double pickBalan = Double.parseDouble(add.getPickBalance());// 把操作员提取金额转换成double
        double preBalan = emp.getPre_balance().doubleValue();// 获得aa002往年余额
        if (sp == null) {
          if (!pickGetEmployeeInfoAF.getReason().equals("4")
              && !pickGetEmployeeInfoAF.getReason().equals("5")) {
            if (pickBalan > partPickupConditionDTO.getPickMoneyMax()
                .doubleValue()) {
              throw new BusinessException("提取金额不能大于部分提取前提录入中的最高提取限额！");
            }
            if (Integer.parseInt(pickGetEmployeeInfoAF.getYearPickNumber()) + 1 > partPickupConditionDTO
                .getPickTimeMax()) {
              throw new BusinessException("提取金额不能大于部分提取前提录入中的最大提取次数！");
            }

            if (Double.parseDouble(pickGetEmployeeInfoAF.getBalance())
                - pickBalan < partPickupConditionDTO.getLeavingsBalance()
                .doubleValue()) {
              throw new BusinessException("提取后的金额不能小于部分提取前提录入中的留足余额！");
            }
          }
        }

        if (pickBalan < preBalan) {// 提取金额小于往年金额
          tail.setPickPreBalance(new BigDecimal(pickBalan));
          tail.setPickCurBalance(new BigDecimal("0.00"));
          tail.setPickCurInterest(new BigDecimal("0.00"));
          tail.setPickPreInterest(new BigDecimal("0.00"));
          // PickHead h = new PickHead();
          // h.setId(new Integer(500));
          // h.setPickSatatus(new BigDecimal(5));
          // h.getOrg().setId(new Integer(100));
          tail.setPickHead(headDao.queryById(new Integer(headId)));
          tail.setPickReason(new BigDecimal(reason));
          tail.setPickType(new BigDecimal(type));
          tail.setEmpId(empId);
          tail.setSpecialPick(sp);
          tail.setReserveaA(add.getHouseNum());
          tailDao.insert(tail);
        } else {
          tail.setPickCurBalance(new BigDecimal(pickBalan - preBalan));
          tail.setPickPreBalance(emp.getPre_balance());
          tail.setPickCurInterest(new BigDecimal("0.00"));
          tail.setPickPreInterest(new BigDecimal("0.00"));
          // PickHead h = new PickHead();
          // h.setPickSatatus(new BigDecimal(23));
          tail.setPickHead(headDao.queryById(new Integer(headId)));
          tail.setPickReason(new BigDecimal(reason));
          tail.setPickType(new BigDecimal(type));
          tail.setEmpId(empId);
          tail.setSpecialPick(sp);
          tail.setReserveaA(add.getHouseNum());
          tailDao.insert(tail);
        }
      } else {// 销户提取
        // 销户提取时得到职工的分段利息以及积数
        PickInterestReteDTO pickInterestReteDTO = tailDao.queryInterestAndRete(
            orgId, empId);
        tail.setPickCurBalance(emp.getCur_balance());// 本年余额
        tail.setPickPreBalance(emp.getPre_balance());// 往年余额
        tail.setPickCurInterest(getCurInterest(orgId, empId, moneyDate));// 本年利息
        tail.setPickPreInterest(getPreInterest(orgId, empId, moneyDate));// 往年利息
        tail.setPickReason(new BigDecimal(reason));
        tail.setPickType(new BigDecimal(type));
        tail.setEmpId(empId);
        tail.setPickHead(headDao.queryById(new Integer(headId)));
        tail.setSpecialPick(sp);
        // 插入分段的利息以及积数
        tail.setPreIntegralSubA(pickInterestReteDTO.getPreIntegralSubA());
        tail.setCurIntegralSubA(pickInterestReteDTO.getCurIntegralSubA());
        tail.setPreRateA(pickInterestReteDTO.getPreRateA());
        tail.setCurRateA(pickInterestReteDTO.getCurRateA());

        tail.setPreIntegralSubB(pickInterestReteDTO.getPreIntegralSubB());
        tail.setCurIntegralSubB(pickInterestReteDTO.getCurIntegralSubB());
        tail.setPreRateB(pickInterestReteDTO.getPreRateB());
        tail.setCurRateB(pickInterestReteDTO.getCurRateB());

        tail.setPreIntegralSubC(pickInterestReteDTO.getPreIntegralSubC());
        tail.setCurIntegralSubC(pickInterestReteDTO.getCurIntegralSubC());
        tail.setPreRateC(pickInterestReteDTO.getPreRateC());
        tail.setCurRateC(pickInterestReteDTO.getCurRateC());

        tail.setPreIntegralSubD(pickInterestReteDTO.getPreIntegralSubD());
        tail.setCurIntegralSubD(pickInterestReteDTO.getCurIntegralSubD());
        tail.setPreRateD(pickInterestReteDTO.getPreRateD());
        tail.setCurRateD(pickInterestReteDTO.getCurRateD());

        tail.setPreIntegralSubE(pickInterestReteDTO.getPreIntegralSubE());
        tail.setCurIntegralSubE(pickInterestReteDTO.getCurIntegralSubE());
        tail.setPreRateE(pickInterestReteDTO.getPreRateE());
        tail.setCurRateE(pickInterestReteDTO.getCurRateE());

        tail.setPreIntegralSubF(pickInterestReteDTO.getPreIntegralSubF());
        tail.setCurIntegralSubF(pickInterestReteDTO.getCurIntegralSubF());
        tail.setPreRateF(pickInterestReteDTO.getPreRateF());
        tail.setCurRateF(pickInterestReteDTO.getCurRateF());

        tail.setPreIntegralSubG(pickInterestReteDTO.getPreIntegralSubG());
        tail.setCurIntegralSubG(pickInterestReteDTO.getCurIntegralSubG());
        tail.setPreRateG(pickInterestReteDTO.getPreRateG());
        tail.setCurRateG(pickInterestReteDTO.getCurRateG());

        tail.setPreIntegralSubH(pickInterestReteDTO.getPreIntegralSubH());
        tail.setCurIntegralSubH(pickInterestReteDTO.getCurIntegralSubH());
        tail.setPreRateH(pickInterestReteDTO.getPreRateH());
        tail.setCurRateH(pickInterestReteDTO.getCurRateH());

        tail.setPreIntegralSubI(pickInterestReteDTO.getPreIntegralSubI());
        tail.setCurIntegralSubI(pickInterestReteDTO.getCurIntegralSubI());
        tail.setPreRateI(pickInterestReteDTO.getPreRateI());
        tail.setCurRateI(pickInterestReteDTO.getCurRateI());

        tail.setPreIntegralSubJ(pickInterestReteDTO.getPreIntegralSubJ());
        tail.setCurIntegralSubJ(pickInterestReteDTO.getCurIntegralSubJ());
        tail.setPreRateJ(pickInterestReteDTO.getPreRateJ());
        tail.setCurRateJ(pickInterestReteDTO.getCurRateJ());

        tail.setPreIntegralSubK(pickInterestReteDTO.getPreIntegralSubK());
        tail.setCurIntegralSubK(pickInterestReteDTO.getCurIntegralSubK());
        tail.setPreRateK(pickInterestReteDTO.getPreRateK());
        tail.setCurRateK(pickInterestReteDTO.getCurRateK());

        tail.setPreIntegralSubL(pickInterestReteDTO.getPreIntegralSubL());
        tail.setCurIntegralSubL(pickInterestReteDTO.getCurIntegralSubL());
        tail.setPreRateL(pickInterestReteDTO.getPreRateL());
        tail.setCurRateL(pickInterestReteDTO.getCurRateL());

        tail.setPreIntegral(pickInterestReteDTO.getPreIntegral());
        tail.setCurIntegral(pickInterestReteDTO.getCurIntegral());

        tailDao.insert(tail);
      }
      // 插入日志
      if (isHeadIdValue == null) {// 头表不存在插入日志
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");// 操作
        haf.setOpBizId(new Integer(headId.toString()));// 取头表ID
        haf.setOpIp(ip);// IP地址
        haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
        haf.setOperator(userName);
        haf.setOrgId(orgId);
        hafDao.insert(haf);
        biz.setBizid(new Integer(headId.toString()));// 头表id
        biz.setAction(new Integer(1));
        biz.setOpTime(new Date(new java.util.Date().getTime()));
        biz.setOperator(userName);// 办理开户的操作员...和权限有关系
        pickBizDao.insert(biz);
      } else {
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");// 操作
        haf.setOpBizId(new Integer(headId.toString()));// 取头表ID
        haf.setOpIp(ip);// IP地址
        haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
        haf.setOperator(userName);
        haf.setOrgId(orgId);
        hafDao.insert(haf);
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void setHafDao(HafOperateLogDAO hafDao) {
    this.hafDao = hafDao;
  }

  public void setPickBizDao(PickBizActivityLogDAO pickBizDao) {
    this.pickBizDao = pickBizDao;
  }

  public List getExportData(final int orgId, String ip, String operator) {

    // 插入各种日志...
    HafOperateLog haf = new HafOperateLog();
    // OPA_ID为AA001中插入记录的ID
    // OP_TIME为系统时间
    // OPERATOR为办理开户的操作员
    haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
    haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
    haf.setOpButton(BusiLogConst.BIZLOG_ACTION_EXP + "");// 操作
    haf.setOpBizId(new Integer(0));// 取头表ID
    haf.setOpIp(ip);// IP地址
    haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
    haf.setOperator(operator);
    haf.setOrgId(new Integer(orgId));
    hafDao.insert(haf);
    return empDao.getExportData(orgId);
  }

  public Org findOrgById(Integer i) throws BusinessException {
    return orgDao.queryById(i);
  }

  /**
   * 李文浩... 撤消提取的方法..
   */
  public String recallPickup(int headId, SecurityInfo info) {
    try {
      PickHead h = headDao.queryById(new Integer(headId));
      int orgId = Integer.parseInt(h.getOrg().getId().toString());
      PickHead test = headDao.findByOrgId(orgId);// 如果返回null那就是不存在(可以撤消提取)。。如果返回不是null那就是存在
      if (test != null) {
        return "此单位已经在头表里有状态为1的记录了";
      } else {
        String officeCode = "";
        String docNumDocument = collParaDAO.getDocNumDesignPara();
        if (docNumDocument.equals("1")) {
          officeCode = h.getOrg().getOrgInfo().getOfficecode();
        } else {
          officeCode = h.getOrg().getOrgInfo().getCollectionBankId();
        }
        // 插入AA306的凭证号插入AA306记录
        insertDocNumCancel(h.getDocNum().substring(8), officeCode, h
            .getSettDate().toString().substring(0, 6));
        // 删除凭证编号和会计日期---修改306头表
        h.setDocNum("");
        h.setSettDate("");
        h.setPickSatatus(new BigDecimal(1));
        OrgHAFAccountFlow orgFlow = orgFlowDao
            .getOrgHAFAccountFlow(new Integer(headId));
        int aa101Id = Integer.parseInt(orgFlow.getId().toString());
        // 删除aa102...这个地方必须先删除aa102
        empFlowDao.deleteEmpHAFAccountFlowAll(new Integer(aa101Id));
        // 根据头表id删除aa101表中的记录
        orgFlowDao.deleteOrgFlowToRecallPickup(headId);// 删除aa101表里的
        // 根据头表id删除aa319 type=3
        pickBizDao.delete(pickBizDao.queryByBizId1(headId));
        // 插入BA003日志
        HafOperateLog haf = new HafOperateLog();
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// 操作模快
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_REVOCATION + "");// 操作
        haf.setOpBizId(new Integer(headId + ""));// 取头表ID
        haf.setOpIp(info.getUserIp());// IP地址
        haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
        haf.setOperator(info.getUserName());
        haf.setOrgId(new Integer(orgId));
        hafDao.insert(haf);
        headDao.updatePickHead(h);
        return null;
      }

    } catch (Exception s) {
      s.printStackTrace();
    }
    return "";// 如果撤消提取失败..那么直接返回的不是null而是出现状态存在1的错误
  }

  public String getBatchErrorData(List headList, List tailList,
      SecurityInfo info, String note_num, String other_card_num)
      throws BusinessException, Exception {
    List errorList = new ArrayList();
    if (headList.isEmpty() || headList.size() <= 0) {// 头表数据为null
      throw new BusinessException("导入数据为空!");
    }
    if (tailList.isEmpty() || tailList.size() <= 0) {// 尾表数据为null
      throw new BusinessException("导入数据为空!");
    }
    PickHeadImportDTO head = (PickHeadImportDTO) headList.get(1);
    String orgId = head.getOrgId();
    if (orgId == null || orgId.trim().length() == 0) {// 如果单位编号是null的时候
      throw new BusinessException("请你输入正确的单位编号");
    }
    try {
      Integer.parseInt(head.getOrgId());
    } catch (Exception s) {
      throw new BusinessException("单位编号格式不正确");
    }
    // Org org = orgDao.isHaveOrg(new Integer(head.getOrgId()),
    // info);//在这个地方必须调用带权限的查找单位的方法
    if (orgDao.isHaveOrg(new Integer(head.getOrgId()), info))// 不存在单位编号
      throw new BusinessException("你导入的单位编号不存在");
    else {// 存在单位编号,,看看此单位的状态是否为正常有!=5
      if (headDao.isHaveNoComplite(Integer.parseInt(orgId)))
        throw new BusinessException("导入的单位有未完成的提取状态");
    }
    String sun_re = "no";
    String sun_rea = "no";
    for (int i = 1; i < tailList.size(); i++) {
      PickTailImportDTO dto = (PickTailImportDTO) tailList.get(i);
      // 判断职工编号
      if (dto.getEmpId() == null || dto.getEmpId().trim().length() <= 0) {
        throw new BusinessException("职工编号不能为空");
      }
      try {
        Integer.parseInt(dto.getEmpId());
      } catch (Exception s) {
        throw new BusinessException("职工编号格式不正确");
      }
      String empId = dto.getEmpId();
      String result = "职工编号为" + empId;
      // 判断提取类型
      if (dto.getPickType() == null || dto.getPickType().trim().length() <= 0) {
        throw new BusinessException(result + "提取类型不能为空");
      }
      try {
        Integer.parseInt(dto.getPickType());
      } catch (Exception s) {
        throw new BusinessException(result + "提取类型格式不正确");
      }
      if (!dto.getPickType().equals("1") && !dto.getPickType().equals("2")) {
        throw new BusinessException(result + "不存在导入的提取类型");
      }
      if (dto.getPickReason().equals("1")
          && (dto.getHouseNum() == null || "".equals(dto.getHouseNum().trim()))) {
        throw new BusinessException(result + "的提取类型为购房，请填写其房照号！");
      }
      if (!dto.getPickReason().equals("1")
          && (dto.getHouseNum() != null && !"".equals(dto.getHouseNum().trim()))) {
        throw new BusinessException(result + "的提取类型不为购房，请将其房照号去掉！");
      }
      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        // 判断提取金额
        if (dto.getPickBalance() == null
            || dto.getPickBalance().trim().length() <= 0) {
          throw new BusinessException(result + "提取金额不能为空");
        }
        try {
          Double.parseDouble(dto.getPickBalance());
        } catch (Exception s) {
          throw new BusinessException(result + "提取金额格式出现错误");
        }
      }
      // 判断提取原因
      if (dto.getPickReason() == null
          || dto.getPickReason().trim().length() <= 0) {
        throw new BusinessException(result + "提取原因不能为空");
      }
      try {
        Integer.parseInt(dto.getPickReason());
      } catch (Exception s) {
        throw new BusinessException(result + "提取原因格式不正确");
      }

      if (errorPickReason(dto.getPickReason())) {
        throw new BusinessException(result + "不存在导入的提取原因");
      }
      if (dto.getPickReason().toString().equals("2")
          || dto.getPickReason().toString().equals("3")) {
        throw new BusinessException(result + "不能批量做公积金还贷或是一次性还清的业务");
      }
      // 判断单位下是否存在此职工
      if (empDao.isEmpAtOrg(new Integer(orgId), new Integer(empId))) {
        throw new BusinessException("你输入的职工编号" + empId + "此单位下不存在");
      }
      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        if (!dto.getPickReason().equals(BusiConst.BUYHOUSE + "")
            && !dto.getPickReason().equals(BusiConst.GIVEMONEYBYMON + "")
            && !dto.getPickReason().equals(BusiConst.GIVEMONEYClEAR + "")
            && !dto.getPickReason().equals(BusiConst.DISEASE + "")
            && !dto.getPickReason().equals(BusiConst.DISTRESS + "")
            && !dto.getPickReason().equals(BusiConst.PARTREST + "")) {
          throw new BusinessException(result + "的提取原因与提取类型不付！");
        }
      } else if (dto.getPickType().equals(BusiConst.PICKUPTYPE_2 + "")) {
        if (!dto.getPickReason().equals(BusiConst.BOWOUT + "")
            && !dto.getPickReason().equals(BusiConst.DEATH + "")
            && !dto.getPickReason().equals(BusiConst.DECRUITMENT + "")
            && !dto.getPickReason().equals(BusiConst.DISTORY + "")
            && !dto.getPickReason().equals(BusiConst.JOBLESS + "")
            && !dto.getPickReason().equals(BusiConst.SETTLE + "")) {
          throw new BusinessException(result + "的提取原因与提取类型不付！");
        }
      }

      /* 杨光添加，判断提取原因 */
      String checkreason = this.getAA306_1();
      String[] cr = checkreason.split(",");
      if (i == 1) {
        for (int c = 0; c < cr.length; c++) {
          if (cr[c].equals(dto.getPickReason())) {
            sun_re = "yes";
            break;
          }
        }
      } else {
        sun_rea = "no";
        for (int c = 0; c < cr.length; c++) {
          if (cr[c].equals(dto.getPickReason())) {
            sun_rea = "yes";
            break;
          }
        }
      }

      if (i!=1 && !sun_re.equals(sun_rea)) {
        throw new BusinessException("此文件存在需要审批和不需要审批的，请分开导入！");
      }
      /* 杨光添加，判断提取原因 */

      double balance = empDao.getTheEmployeeBalance(
          Integer.parseInt(head.getOrgId()), Integer.parseInt(dto.getEmpId()))
          .doubleValue();

      // 追加提取金额判断条件...

      // 查询上年度缴额.
      Emp emp = empDao.findEmpByOrdIdAndEmpId(new Integer(orgId), new Integer(
          empId));
      BigDecimal paymoney = emp.getPayOldYear();
      // 上年度为空时,取当前缴额.
      if (paymoney == null || paymoney.toString().equals("0")) {
        paymoney = emp.getEmpPay().add(emp.getOrgPay());
      }

      // 取得提取设置条件.
      PartPickupConditionDTO partPickupConditionDTO = new PartPickupConditionDTO();
      partPickupConditionDTO = drawRuleDAO.queryPartPickupConditionInfo();
      BigDecimal mul = new BigDecimal(partPickupConditionDTO.getMultiple());

      // 部分提取---特困与重大疾病时取往年余额;其它留足缴额*参数.
      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        if (dto.getPickReason().equals("4") || dto.getPickReason().equals("5")) {
          balance = drawRuleDAO.getPerbalance(
              Integer.parseInt(head.getOrgId()),
              Integer.parseInt(dto.getEmpId()), dto.getPickReason().toString())
              .doubleValue();
        } else {
          BigDecimal maxMoney = drawRuleDAO.getSomePickMoney(Integer
              .parseInt(head.getOrgId()), Integer.parseInt(dto.getEmpId()), dto
              .getPickReason().toString());
          balance = maxMoney.subtract(paymoney.multiply(mul)).doubleValue();
        }
      }

      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        if (Double.parseDouble(dto.getPickBalance()) > balance) {
          throw new BusinessException(result + "的职工提取金额过多");
        }
      }
    }
    // 插入AA306
    PickHead pickHead = new PickHead();
    pickHead.getOrg().setId(new Integer(head.getOrgId()));
    pickHead.setPickSatatus(new BigDecimal(1));

    if (head.getOrgNoteNumber() != null
        && head.getOrgNoteNumber().trim().length() > 0) {// 有票据编号
      pickHead.setNoteNum(head.getOrgNoteNumber());
    } else {
      if (note_num != "" && note_num != null) {
        pickHead.setNoteNum(note_num);
      }
    }
    pickHead.setReserveaA("1");
    Serializable headId = headDao.insert(pickHead);
    temp_headid = headId.toString();
    // 插入AA307
    // PickHead pickHeadset=headDao.queryById(new Integer(headId.toString()));
    for (int i = 1; i < tailList.size(); i++) {
      PickTailImportDTO ok = (PickTailImportDTO) tailList.get(i);
      PickTail p = new PickTail();
      String empIdInsert = ok.getEmpId();
      EmpInfoPick emp = tailDao.getEmpInfo_LY(new Integer(orgId), new Integer(
          empIdInsert));
      p.setPickHead(pickHead);// 插入头表id;
      p.setPickReason(new BigDecimal(ok.getPickReason()));// 原因
      p.setPickType(new BigDecimal(ok.getPickType()));// 类型
      p.setEmpId(new Integer(empIdInsert));// 职工编号
      p.setReserveaA(ok.getHouseNum());
      if (ok.getPickType().equals("1")) {// 部分提取..
        // if (Double.parseDouble(ok.getPickBalance()) >= emp.getBalance()
        // .doubleValue()) {// 当提取金额大于本年余额
        // p.setPickPreBalance(emp.getPreBalance());
        // p.setPickCurBalance(new BigDecimal(Double.parseDouble(ok
        // .getPickBalance())
        // - emp.getPreBalance().doubleValue()));
        // } else {
        // p.setPickPreBalance(new BigDecimal(ok.getPickBalance()));
        // p.setPickCurBalance(new BigDecimal(0.00));
        // }
        // p.setPickCurInterest(new BigDecimal(0.00));
        // p.setPickPreInterest(new BigDecimal(0.00));

        List empList = tailDao.findPickTailByEmpId(new Integer(empIdInsert)
            .intValue());
        String result = "职工编号为" + empIdInsert;
        Object[] obj = (Object[]) tailDao.getYearPickBalanceAndCount(
            new Integer(empIdInsert).intValue(), info).get(0);
        PartPickupConditionDTO partPickupConditionDTO = null;
        try {
          partPickupConditionDTO = partPickupConditionDAO
              .queryPartPickupConditionInfo();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if (partPickupConditionDTO != null) {
          if (Double.parseDouble(ok.getPickBalance()) > partPickupConditionDTO
              .getPickMoneyMax().doubleValue()) {
            throw new BusinessException(result + "的职工提取金额不能大于部分提取前提录入中的最高提取限额！");
          }

          // if(empList!=null){
          int picktime = Integer.parseInt(obj[0].toString());
          // double balance=Double.parseDouble(obj[1].toString());
          double balance = 0;
          if (picktime > partPickupConditionDTO.getPickTimeMax() - 1) {
            throw new BusinessException(result + "的职工提取金额不能大于部分提取前提录入中的最大提取次数！");
          }
          BigDecimal maxMoney = tailDao.getMaxPickMoney(
              Integer.parseInt(orgId), Integer.parseInt(empIdInsert), null);
          if (maxMoney == null) {
            balance = 0;
          } else {
            balance = Double.parseDouble(maxMoney.setScale(2).toString());
          }

          if (balance - Double.parseDouble(ok.getPickBalance()) < partPickupConditionDTO
              .getLeavingsBalance().doubleValue()) {
            throw new BusinessException(result + "的职工提取后的金额不能小于部分提取前提录入中的留足余额！");
          }

          // }

        }
        if (Double.parseDouble(ok.getPickBalance()) < emp.getPre_balance()
            .doubleValue()) {// 提取金额小于往年金额
          p.setPickPreBalance(new BigDecimal(ok.getPickBalance()));
          p.setPickCurBalance(new BigDecimal("0.00"));
          p.setPickCurInterest(new BigDecimal("0.00"));
          p.setPickPreInterest(new BigDecimal("0.00"));
        } else {
          p.setPickCurBalance(new BigDecimal(Double.parseDouble(ok
              .getPickBalance())
              - emp.getPre_balance().doubleValue()));
          p.setPickPreBalance(emp.getPre_balance());
          p.setPickCurInterest(new BigDecimal("0.00"));
          p.setPickPreInterest(new BigDecimal("0.00"));
        }
      } else {// 销户提取
        PickInterestReteDTO pickInterestReteDTO = tailDao.queryInterestAndRete(
            new Integer(orgId), new Integer(empIdInsert));
        p.setPickPreBalance(emp.getPre_balance());
        p.setPickCurBalance(emp.getCur_balance());
        p.setPickPreInterest(this.getPreInterest(new Integer(head.getOrgId()),
            new Integer(empIdInsert), info.getUserInfo().getBizDate()));
        p.setPickCurInterest(this.getCurInterest(new Integer(head.getOrgId()),
            new Integer(empIdInsert), info.getUserInfo().getBizDate()));
        // 插入分段的利息以及积数
        p.setPreIntegralSubA(pickInterestReteDTO.getPreIntegralSubA());
        p.setCurIntegralSubA(pickInterestReteDTO.getCurIntegralSubA());
        p.setPreRateA(pickInterestReteDTO.getPreRateA());
        p.setCurRateA(pickInterestReteDTO.getCurRateA());

        p.setPreIntegralSubB(pickInterestReteDTO.getPreIntegralSubB());
        p.setCurIntegralSubB(pickInterestReteDTO.getCurIntegralSubB());
        p.setPreRateB(pickInterestReteDTO.getPreRateB());
        p.setCurRateB(pickInterestReteDTO.getCurRateB());

        p.setPreIntegralSubC(pickInterestReteDTO.getPreIntegralSubC());
        p.setCurIntegralSubC(pickInterestReteDTO.getCurIntegralSubC());
        p.setPreRateC(pickInterestReteDTO.getPreRateC());
        p.setCurRateC(pickInterestReteDTO.getCurRateC());

        p.setPreIntegralSubD(pickInterestReteDTO.getPreIntegralSubD());
        p.setCurIntegralSubD(pickInterestReteDTO.getCurIntegralSubD());
        p.setPreRateD(pickInterestReteDTO.getPreRateD());
        p.setCurRateD(pickInterestReteDTO.getCurRateD());

        p.setPreIntegralSubE(pickInterestReteDTO.getPreIntegralSubE());
        p.setCurIntegralSubE(pickInterestReteDTO.getCurIntegralSubE());
        p.setPreRateE(pickInterestReteDTO.getPreRateE());
        p.setCurRateE(pickInterestReteDTO.getCurRateE());

        p.setPreIntegralSubF(pickInterestReteDTO.getPreIntegralSubF());
        p.setCurIntegralSubF(pickInterestReteDTO.getCurIntegralSubF());
        p.setPreRateF(pickInterestReteDTO.getPreRateF());
        p.setCurRateF(pickInterestReteDTO.getCurRateF());

        p.setPreIntegralSubG(pickInterestReteDTO.getPreIntegralSubG());
        p.setCurIntegralSubG(pickInterestReteDTO.getCurIntegralSubG());
        p.setPreRateG(pickInterestReteDTO.getPreRateG());
        p.setCurRateG(pickInterestReteDTO.getCurRateG());

        p.setPreIntegralSubH(pickInterestReteDTO.getPreIntegralSubH());
        p.setCurIntegralSubH(pickInterestReteDTO.getCurIntegralSubH());
        p.setPreRateH(pickInterestReteDTO.getPreRateH());
        p.setCurRateH(pickInterestReteDTO.getCurRateH());

        p.setPreIntegralSubI(pickInterestReteDTO.getPreIntegralSubI());
        p.setCurIntegralSubI(pickInterestReteDTO.getCurIntegralSubI());
        p.setPreRateI(pickInterestReteDTO.getPreRateI());
        p.setCurRateI(pickInterestReteDTO.getCurRateI());

        p.setPreIntegralSubJ(pickInterestReteDTO.getPreIntegralSubJ());
        p.setCurIntegralSubJ(pickInterestReteDTO.getCurIntegralSubJ());
        p.setPreRateJ(pickInterestReteDTO.getPreRateJ());
        p.setCurRateJ(pickInterestReteDTO.getCurRateJ());

        p.setPreIntegralSubK(pickInterestReteDTO.getPreIntegralSubK());
        p.setCurIntegralSubK(pickInterestReteDTO.getCurIntegralSubK());
        p.setPreRateK(pickInterestReteDTO.getPreRateK());
        p.setCurRateK(pickInterestReteDTO.getCurRateK());

        p.setPreIntegralSubL(pickInterestReteDTO.getPreIntegralSubL());
        p.setCurIntegralSubL(pickInterestReteDTO.getCurIntegralSubL());
        p.setPreRateL(pickInterestReteDTO.getPreRateL());
        p.setCurRateL(pickInterestReteDTO.getCurRateL());
      }
      p.setSpecialPick(null);// 这个地方必须为null
      tailDao.insert(p);
    }
    // 139完全一样
    PickHeadImportDTO head1 = (PickHeadImportDTO) headList.get(0);
    String orgId1 = head1.getOrgId();
    if (orgId1 != null) {// 说明不是从提取，插入ba003
      HafOperateLog haf = new HafOperateLog();
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
      haf.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP + "");// 操作
      haf.setOpBizId(new Integer(headId.toString()));// 取头表ID
      haf.setOpIp(info.getUserIp());// IP地址
      haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间-->系统时间
      haf.setOperator(info.getUserName());
      haf.setOrgId(new Integer(head.getOrgId()));
      hafDao.insert(haf);
    }
    PickBizActivityLog biz = new PickBizActivityLog();
    biz.setBizid(new Integer(headId.toString()));// 头表id
    biz.setAction(new Integer(1));
    biz.setOpTime(new Date(new java.util.Date().getTime()));
    biz.setOperator(info.getUserName());// 办理开户的操作员...和权限有关系
    pickBizDao.insert(biz);
    return head.getOrgId();
  }

  // 判断提取类型是否是合法的提取原因
  public boolean errorPickReason(String reason) {
    if (reason.equals("1"))
      return false;
    if (reason.equals("2"))
      return false;
    if (reason.equals("3"))
      return false;
    if (reason.equals("4"))
      return false;
    if (reason.equals("5"))
      return false;
    if (reason.equals("6"))
      return false;
    if (reason.equals("7"))
      return false;
    if (reason.equals("8"))
      return false;
    if (reason.equals("9"))
      return false;
    if (reason.equals("10"))
      return false;
    if (reason.equals("11"))
      return false;
    if (reason.equals("12"))
      return false;
    return true;
  }

  /**
   * 删除一个员工
   */
  public void deleteEmployee(int headId, int empId, String last, String ip,
      String operator, SecurityInfo info) throws BusinessException {
    try {
      SpecialPick special = tailDao.is_findEmployeeBySpecial(headId, empId);
      if (special != null) {// 此员是特殊提取的时候..我们修改此员工的状态为1
        special.setIsPick(new BigDecimal(1));
        specialPickDao.updateByIsPick(special);// 修改
      }
      PickHead head = headDao.queryById(new Integer(headId));// 根据得到的头表对象可以导航出来orgID
      // 必须放在删除头表的表的前面,不然在插入日志的时候...会出现nullPointException
      if (last.equals("删除头尾表")) {// 只剩下一条记录了..删除两个表
        int isOrgEdition = info.getIsOrgEdition();
        if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(head.getOrg()
              .getId().toString(), head.getId().toString(),
              BusiConst.ORGBUSINESSTYPE_D);
          String stype = autoInfoPickDAODW
              .findStatus(head.getOrg().getId().toString(), head.getId()
                  .toString(), BusiConst.ORGBUSINESSTYPE_D);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("中心已提取，不能删除");
          }
          if (isNoPickUp) {
            throw new BusinessException("请先撤销提交！");
          }
        } else {// 中心版
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, head.getOrg().getId().toString(),
              head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        }
        // 必须先删除尾表在删除头表...不然程序会出现错误
        tailDao.deleteByEmpIdAndHeadId(empId, headId);
        headDao.deleteById(new Integer(headId));// 根据头表id来删除头表
        // 删除319的日志
        PickBizActivityLog b = pickBizDao.queryByBizId(headId);
        pickBizDao.delete(b);
      } else {// 没有剩下最后一条记录
        tailDao.deleteByEmpIdAndHeadId(empId, headId);
      }

      HafOperateLog haf = new HafOperateLog();
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
      haf.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");// 操作
      haf.setOpBizId(new Integer(headId));// 取头表ID
      haf.setOpIp(ip);// IP地址
      haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
      haf.setOperator(operator);
      haf.setOrgId(new Integer(head.getOrg().getId().toString()));// 单位编号
      hafDao.insert(haf);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Serializable insertPickTail(PickTail pickTail) {
    return tailDao.insert(pickTail);
  }

  public List findDataByHeadId(int headId) {
    return tailDao.findDataByHeadId(headId);
  }

  /**
   * 全部删除的方法
   */
  public void allDelete(int headId, String ip, String operator,
      SecurityInfo info) throws BusinessException {
    try {
      PickHead head = headDao.queryById(new Integer(headId));// 根据得到的头表对象可以导航出来orgID
      int isOrgEdition = info.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        String stype = autoInfoPickDAODW.findStatus(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("中心已提取，不能删除");
        }
        if (isNoPickUp) {
          throw new BusinessException("请先撤销提交数据！");
        }
      } else {// 中心版
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, head.getOrg().getId().toString(),
            head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      }
      List specialPicklist = tailDao.findPickTailBySpecialPick(headId);
      if (specialPicklist != null && specialPicklist.size() > 0) {
        for (int i = 0; i < specialPicklist.size(); i++) {
          PickTail p = (PickTail) specialPicklist.get(i);
          SpecialPick special = tailDao.is_findEmployeeBySpecial(headId,
              Integer.parseInt(p.getEmpId().toString()));
          // 此员是特殊提取的时候..我们修改此员工的状态为1
          special.setIsPick(new BigDecimal(1));
          specialPickDao.updateByIsPick(special);// 修改
        }
      }
      tailDao.deleteTail(new Integer(headId));// 删除集合

      // 必须先导航 ..出来头表的对象...这样才能够得到头针对的orgId
      // 而不是删除..这样会出现null point Exception
      headDao.deleteById_LY(new Integer(headId));// 删除306
      PickBizActivityLog b = pickBizDao.queryByBizId(headId);
      pickBizDao.delete(b);
      // 插入日志
      HafOperateLog haf = new HafOperateLog();
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
      haf.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETEALL + "");// 操作
      haf.setOpBizId(new Integer(headId));// 取头表ID
      haf.setOpIp(ip);// IP地址
      haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
      haf.setOperator(operator);
      haf.setOrgId(new Integer(head.getOrg().getId().toString()));// 单位编号
      hafDao.insert(haf);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 完成提取的方法
   */
  public boolean overPick(int orgId, String ip, String operator,
      String moneyDate, String noteNumber, SecurityInfo securityInfo)
      throws Exception {
    try {
      // 获得尾表的唯一提交记录..此记录一定是存在的
      PickHead head = headDao.findByOrgId(orgId);// 根据orgId和状态为1的记录找出来头表
      Org org = orgDao.queryById(new Integer(orgId));
      String officeCode = "";
      String docNumDocument = collParaDAO.getDocNumDesignPara();
      if (docNumDocument.equals("1")) {
        officeCode = org.getOrgInfo().getOfficecode();
      } else {
        officeCode = org.getOrgInfo().getCollectionBankId();
      }
      // DocNumBS dn = new DocNumBS();
      String notenum = "";
      // //"..........更新306..........."
      if (!head.getPickSatatus().equals("1")) {
        String docNumber = getDocNumber(officeCode, moneyDate.substring(0, 6),
            securityInfo);// 根据单位信息可获得它的办事处.和.会计年月来生成凭证编号
        // head.setDocNum(docNumber);
        // head.setSettDate(moneyDate);// 插入会计日期
        // head.setPickSatatus(new BigDecimal(3));
        if (noteNumber != null) {// 如果票据编号!-==null的时候就插入...这住要是为了保证提取维护中的提取完成..让它的票据编号不让改变
          notenum = noteNumber;
          // head.setNoteNum(noteNumber);
        } else {
          notenum = head.getNoteNum();
        }
        BizActivityLog bizActivityLog = bizActivityLogDAO
            .queryBizActivityLogWuht_(head.getId().toString(), "D", "1");// 在aa319中查找登记人
        String registrations = bizActivityLog.getOperator();
        headDao.overPickUp(new Integer(head.getId().toString()), new Integer(
            orgId), docNumber, moneyDate, notenum, operator, org.getOrgInfo()
            .getOfficecode(), org.getOrgInfo().getCollectionBankId(),
            registrations);

        // headDao.updatePickHead(head);
        // ........插入 aa102..........
        // List list = tailDao.getAllEmpId(new Integer(head.getId().toString())
        // .intValue());// 因为这个List 一定会存在..因为浏览器上显示数据那就是代表为表中有数据
        // // ........插入 aa101..........
        // Serializable aa102Id = saveOrgStream(new
        // Integer(org.getId().toString()).intValue(), docNumber, moneyDate,
        // new BigDecimal(head.getId().toString()), noteNumber,operator,list);
        // OrgHAFAccountFlowDrawing orgFlow = orgFlowDao.queryById(new Integer(
        // aa102Id.toString()));// 获得刚插入进去的aa101的对象
        // for (int i = 0; i < list.size(); i++) {
        // saveEmpStream(orgFlow, new Integer(head.getId().toString()), new
        // Integer(list.get(i)
        // .toString()),new Integer(org.getId().toString()));
        // }
        // ............插入 aa319............
        PickBizActivityLog biz = new PickBizActivityLog();// 插入aa319Log
        biz.setBizid(new Integer(head.getId().toString()));
        biz.setAction(new Integer(3));
        biz.setOpTime(new Date(new java.util.Date().getTime()));
        biz.setOperator(operator);
        pickBizDao.insert(biz);
        // ............插入 ba003............
        HafOperateLog haf = new HafOperateLog();
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_OPENING + "");// 操作
        haf.setOpBizId(new Integer(head.getId().toString()));// 取头表ID
        haf.setOpIp(ip);// IP地址
        haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
        haf.setOperator(operator);
        haf.setOrgId(new Integer(orgId));
        hafDao.insert(haf);
        return true;
      }
    } catch (Exception s) {
      return false;
    }
    return false;
  }

  /**
   * 提取维护里完成提取的方法
   */
  public boolean overPickVindicate(int orgId, String ip, String operator,
      String moneyDate, String noteNumber, SecurityInfo securityInfo)
      throws Exception {
    try {
      // 获得尾表的唯一提交记录..此记录一定是存在的
      PickHead head = headDao.findByOrgId(orgId);// 根据orgId和状态为1的记录找出来头表
      Org org = orgDao.queryById(new Integer(orgId));
      String officeCode = "";
      String docNumDocument = collParaDAO.getDocNumDesignPara();
      if (docNumDocument.equals("1")) {
        officeCode = org.getOrgInfo().getOfficecode();
      } else {
        officeCode = org.getOrgInfo().getCollectionBankId();
      }
      // DocNumBS dn = new DocNumBS();
      String notenum = "";
      // //"..........更新306..........."
      if (!head.getPickSatatus().equals("1")) {
        String docNumber = getDocNumber(officeCode, moneyDate.substring(0, 6),
            securityInfo);// 根据单位信息可获得它的办事处.和.会计年月来生成凭证编号
        // head.setDocNum(docNumber);
        // head.setSettDate(moneyDate);// 插入会计日期
        // head.setPickSatatus(new BigDecimal(3));

        if (noteNumber != null) {// 如果票据编号!-==null的时候就插入...这住要是为了保证提取维护中的提取完成..让它的票据编号不让改变
          notenum = noteNumber;
          // head.setNoteNum(noteNumber);
        } else {
          notenum = head.getNoteNum();
        }
        BizActivityLog bizActivityLog = bizActivityLogDAO
            .queryBizActivityLogWuht_(head.getId().toString(), "D", "1");// 在aa319中查找登记人
        String registrations = bizActivityLog.getOperator();
        headDao.overPickUp(new Integer(head.getId().toString()), new Integer(
            orgId), docNumber, moneyDate, notenum, operator, org.getOrgInfo()
            .getOfficecode(), org.getOrgInfo().getCollectionBankId(),
            registrations);
        // headDao.updatePickHead(head);
        // List list = tailDao.getAllEmpId(new Integer(head.getId().toString())
        // .intValue());// 因为这个List 一定会存在..因为浏览器上显示数据那就是代表为表中有数据
        // // ........插入 aa101..........
        // Serializable aa102Id = saveOrgStream(new
        // Integer(org.getId().toString()).intValue(), docNumber, moneyDate,
        // new BigDecimal(head.getId().toString()), noteNumber,operator,list);
        // OrgHAFAccountFlowDrawing orgFlow = orgFlowDao.queryById(new Integer(
        // aa102Id.toString()));// 获得刚插入进去的aa101的对象
        // // ........插入 aa102..........
        // for (int i = 0; i < list.size(); i++) {
        // saveEmpStream(orgFlow, new Integer(head.getId().toString()), new
        // Integer(list.get(i)
        // .toString()),new Integer(org.getId().toString()));
        // }
        // ............插入 aa319............
        PickBizActivityLog biz = new PickBizActivityLog();// 插入aa319Log
        biz.setBizid(new Integer(head.getId().toString()));
        biz.setAction(new Integer(3));
        biz.setOpTime(new Date(new java.util.Date().getTime()));
        biz.setOperator(operator);
        pickBizDao.insert(biz);
        // ............插入 ba003............
        HafOperateLog haf = new HafOperateLog();
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// 操作模快
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_OPENING + "");// 操作
        haf.setOpBizId(new Integer(head.getId().toString()));// 取头表ID
        haf.setOpIp(ip);// IP地址
        haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
        haf.setOperator(operator);
        haf.setOrgId(new Integer(orgId));
        hafDao.insert(haf);
        return true;
      }
    } catch (Exception s) {
      return false;
    }
    return false;
  }

  /**
   * 保存职工业务流水
   */
  public void saveEmpStream(OrgHAFAccountFlow orgFlow, Integer headId,
      Integer empId, Integer orgId) {
    // ....插入AA102....职工的业务流水表 测试成功
    EmpHAFAccountFlow empStream = new EmpHAFAccountFlow();
    empStream.setEmpId(empId);
    empStream.setOrgHAFAccountFlow(orgFlow);// 单位流水号
    empStream.setCredit(new BigDecimal(0));
    empStream.setDebit(tailDao.getTheEmpBalance(headId.intValue(), orgId
        .intValue(), empId.intValue()));
    empStream.setInterest(tailDao.getTheEmpInterest(headId.intValue(), orgId
        .intValue(), empId.intValue()));
    empFlowDao.insert(empStream);
  }

  /**
   * 保存单位流水
   */
  public Serializable saveOrgStream(int orgId, String docNum, String moneyDate,
      BigDecimal headId, String noteNumber, String operator, List list) {
    // .......插入AA101.....//单位提取的业务流水表..此表对应的类是abstract..你必须找它的子类..并且是提取的子类
    try {
      /** 测试通过OK** */
      Org org = orgDao.queryById(new Integer(orgId));// 获得Org表
      OrgHAFAccountFlowDrawing orgStream = new OrgHAFAccountFlowDrawing();
      BigDecimal balance = tailDao.getDebit(new Integer(headId.intValue())
          .intValue());// 获得此单位要提多少钱..
      BigDecimal interest = tailDao.getInterest(new Integer(headId.intValue())
          .intValue());
      orgStream.setOrg(org);
      orgStream.setDocNum(docNum);
      orgStream.setSettDate(moneyDate);
      orgStream.setDebit(balance);
      orgStream.setCredit(new BigDecimal(0));
      orgStream.setInterest(interest);
      orgStream.setBizId(headId);
      orgStream.setIsFinance(new BigDecimal(1));
      orgStream.setBizStatus(new BigDecimal(3));
      orgStream.setNoteNum(noteNumber);
      orgStream.setReserveaA(operator);
      orgStream.setPersonTotal(new Integer(list.size()));
      // 二次升级开始
      orgStream.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgStream.setMoneyBank(org.getOrgInfo().getCollectionBankId());
      // 结束
      Serializable id = orgFlowDao.insert(orgStream);
      return id;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 根据头表的id来找出来单位的id
   */
  public String findOrdIdByHeadId(int headId) throws BusinessException {
    try {
      PickHead head = headDao.queryById(new Integer(headId));
      String orgId = head.getOrg().getId().toString();// 根据头表的id来获取orgid
      return orgId;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 根据单位的id找出来此头表状态为1的id 此id一定能够存在
   */
  public String findPickHeadSOneByOrgId(int orgId) throws BusinessException {
    String h = headDao.findPickHeadStatueOneByOrgId(orgId).toString();
    return h;// 这个dao方法一定不会返回null..
  }

  /**
   * 根据头表的id来删除头尾表的记录
   */
  public void deleteHeadTailByHeadId(int headId, String ip, String operator,
      SecurityInfo info) throws BusinessException {
    try {
      PickHead head = headDao.queryById(new Integer(headId));
      int isOrgEdition = info.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        String stype = autoInfoPickDAODW.findStatus(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("中心已提取，不能删除");
        }
        if (isNoPickUp) {
          throw new BusinessException("请先撤销提交数据！");
        }
      } else {// 中心版
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, head.getOrg().getId().toString(),
            head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      }
      List specialPicklist = tailDao.findPickTailBySpecialPick(headId);
      if (specialPicklist != null) {
        for (int i = 0; i < specialPicklist.size(); i++) {
          PickTail p = (PickTail) specialPicklist.get(i);
          SpecialPick special = tailDao.is_findEmployeeBySpecial(headId,
              Integer.parseInt(p.getEmpId().toString()));
          // 此员是特殊提取的时候..我们修改此员工的状态为1
          special.setIsPick(new BigDecimal(1));
          specialPickDao.updateByIsPick(special);// 修改

        }
      }

      String orgId = head.getOrg().getId().toString();// 根据头表的id来获取orgid

      // 这个地方的参数是Serializable ..但是你不能传String更不能传递Serializable
      tailDao.deleteTail(new Integer(headId));
      headDao.deleteById_LY(new Integer(headId));
      PickBizActivityLog b = pickBizDao.queryByBizId(headId);
      pickBizDao.delete(b);// 删除日志
      HafOperateLog haf = new HafOperateLog();// 插入BA003的日志
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// 操作模快
      haf.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");// 操作
      haf.setOpBizId(new Integer(headId));// 取头表ID
      haf.setOpIp(ip);// IP地址
      haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
      haf.setOperator(operator);
      haf.setOrgId(new Integer(orgId));
      hafDao.insert(haf);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /**
   * 撤消凭证号的方法.. 把凭证号码
   */
  public void insertDocNumCancel(String docNum, String officeCode,
      String bizYearmonth) throws Exception, BusinessException {
    try {// 第一个参数是306里面的凭证号,第二个是办事处编号,第三个是306的sett_date取前6位
      if (officeCode != null || bizYearmonth != null || docNum != null) {
        insertDao.insertDocNumCancel(docNum, officeCode, bizYearmonth);
      }
    } catch (Exception e) {
      throw new BusinessException("添加凭证号失败!");
    }
  }

  /**
   * 添加凭证号
   */
  public void insert(String docNum, String officeCode, String bizYearmonth)
      throws Exception, BusinessException {
    try {// 给数据库添加用的......制作凭证编号的方法
      if (officeCode != null || bizYearmonth != null || docNum != null) {
        insertDao.insertDocNumCancel(docNum, officeCode, bizYearmonth);
      }
    } catch (Exception e) {
      throw new BusinessException("添加凭证号失败!");
    }
  }

  /**
   * 生成凭证号
   */
  public String getDocNumber(String officeCode, String bizYearmonth,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String docNum = null;
    try {// 生成凭证编号的方法 第一个是此单位所在的办事处 第二个是会记年月..而不是会计日期.它比会计日期少两位
      docNum = maintainDao.getDocNumdocNum(officeCode, bizYearmonth);
      Map office = securityInfo.getOfficeInnerCodeMap();
      String officecode = office.get(officeCode).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      docNum = officecode + bizYearmonth + docNum;
    } catch (Exception e) {
      throw new BusinessException("生成凭证号失败!");
    }
    return docNum;
  }

  public void setInsertDao(DocNumCancelDAO insertDao) {
    this.insertDao = insertDao;
  }

  public void setMaintainDao(DocNumMaintainDAO maintainDao) {
    this.maintainDao = maintainDao;
  }

  public void setOrgFlowDao(OrgHAFAccountFlowDrawingDAO orgFlowDao) {
    this.orgFlowDao = orgFlowDao;
  }

  public void setEmpFlowDao(EmpHAFAccountFlowDAO empFlowDao) {
    this.empFlowDao = empFlowDao;
  }

  /**
   * 李文浩 查询职工提取信息
   */
  public List getEmployeePickInfo(Pagination p) throws BusinessException {
    String empId = (String) p.getQueryCriterions().get("empId");
    String time = (String) p.getQueryCriterions().get("time");
    String reason = (String) p.getQueryCriterions().get("reason");
    String orderBy = p.getOrderBy();
    String order = p.getOrderother();
    int start = p.getFirstElementOnPage() - 1;
    int pageSize = p.getPageSize();
    List list = tailDao.getEmployeePickInfo(empId, time, reason, orderBy,
        order, start, pageSize);
    try {
      for (int i = 0; i < list.size(); i++) {
        PickTail tail = (PickTail) list.get(i);
        if (tail.getPickType().intValue() == 1) {// 部分提取
          tail.setReason(BusiTools.getBusiValue(
              tail.getPickReason().intValue(), BusiConst.SOMEPICK));
        } else if (tail.getPickType().intValue() == 2) {
          tail.setReason(BusiTools.getBusiValue(
              tail.getPickReason().intValue(), BusiConst.DISTROYPICK));
        }
        tail.setTemp_PickType(BusiTools.getBusiValue(Integer.parseInt(tail
            .getPickType().toString()), BusiConst.PICKUPTYPE));
      }
      int count = tailDao.getEmployeePickInfoCount(empId, time, reason);
      p.setNrOfElements(count);
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public void update(int headId, int orgId, String ip, String operator)
      throws BusinessException {
    HafOperateLog haf = new HafOperateLog();// 插入BA003的日志
    haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
    haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// 操作模快
    haf.setOpButton(BusiLogConst.BIZLOG_ACTION_MODIFY + "");// 操作
    haf.setOpBizId(new Integer(headId));// 取头表ID
    haf.setOpIp(ip);// IP地址
    haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
    haf.setOperator(operator);
    haf.setOrgId(new Integer(orgId));
    hafDao.insert(haf);
  }

  public NameAF findName(String orgID) throws BusinessException {
    Org org = orgDao.findOrgInfo(orgID);
    String payBank = "";
    String payBankNum = "";
    OrganizationUnit organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(org.getOrgInfo().getOfficecode());
    CollBank collBank = collBankDAO.getCollBankByCollBankid(org.getOrgInfo()
        .getCollectionBankId());
    String organizatinUnitName = organizationUnit.getName();
    String centercollBankName = collBank.getCollBankName();
    String orgName = org.getOrgInfo().getName();

    if (org.getOrgInfo().getPayBank() == null) {
      payBank = " ";
    } else {
      payBank = org.getOrgInfo().getPayBank().getName();// 发薪银行
    }
    if (org.getOrgInfo().getPayBank() == null) {
      payBankNum = " ";
    } else {
      payBankNum = org.getOrgInfo().getPayBank().getAccountNum();// 发薪银行帐号
    }
    payBankNum = collBank.getCollectionbankacc();
    NameAF nameAF = new NameAF();
    nameAF.setOrganizatinUnitName(organizatinUnitName);
    nameAF.setCentercollBankName(centercollBankName);
    nameAF.setOrgName(orgName);
    nameAF.setPayBank(payBank);
    nameAF.setPayBankNum(payBankNum);
    return nameAF;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public NameAF findpickTail(String id) throws BusinessException {
    List list = new ArrayList();
    NameAF nameAF = new NameAF();
    BigDecimal money = new BigDecimal(0);
    int idd = new Integer(id).intValue();
    list = tailDao.findDataByHeadId(idd);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        PickTail pickTail = (PickTail) list.get(i);
        BigDecimal pickPre = pickTail.getPickPreBalance();
        BigDecimal pickCur = pickTail.getPickCurBalance();
        BigDecimal preInt = pickTail.getPickPreInterest();
        BigDecimal curInt = pickTail.getPickCurInterest();
        if (pickPre != null || !pickPre.equals("")) {
          money = money.add(pickPre);
        }
        if (pickCur != null || !pickCur.equals("")) {
          money = money.add(pickCur);
        }
        if (preInt != null || !preInt.equals("")) {
          money = money.add(preInt);
        }
        if (curInt != null || !curInt.equals("")) {
          money = money.add(curInt);
        }
      }
    }
    nameAF.setMoney(money);
    return nameAF;
  }

  // 根据单位id查314表中有无此单位
  public boolean check(String orgID, SecurityInfo info) {
    boolean b = false;
    List a = adjustWrongAccountHeadDAO.queryByIDGJP(orgID, info);
    if (a.size() > 0) {
      return true;
    }
    return b;
  }

  // 根据单位id查309表中有无此单位
  public boolean checkTranOut(String orgID) {
    boolean b = false;
    List a = headDao.queryTranOutHeadByOrgid(orgID);
    if (a.size() > 0) {
      return true;
    }
    return b;
  }

  public void setPartPickupConditionDAO(
      PartPickupConditionDAO partPickupConditionDAO) {
    this.partPickupConditionDAO = partPickupConditionDAO;
  }

  public void referringData(String headId, SecurityInfo securityInfo,
      String temp_p) throws BusinessException {

    PickHead head = headDao.queryById(new Integer(headId));// 根据得到的头表对象可以导航出来orgID
    boolean flag = autoInfoPickDAODW.isNOPickIn(head.getOrg().getId()
        .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
    try {
      if (flag) {
        throw new BusinessException("该笔业务已提交");
      } else {
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(head.getOrg().getId().toString()));
        autoInfoPick.setOrgHeadId(new Integer(head.getId().toString()));
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_D);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPick.setOrgBizDate(new Date());
        Integer temp_a = null;
        Integer temp_b = null;
        autoInfoPick.setCenterHeadId(temp_a);
        autoInfoPick.setPayHeadId(temp_b);
        autoInfoPickDAODW.insert(autoInfoPick);
        // 插入BA003：
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (temp_p.equals("1")) {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// 办理
        } else {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN));// 维护
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
        hafOperateLog.setOpBizId(new Integer(head.getId().toString()));// AA306.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(head.getOrg().getId().toString()));
        hafOperateLogDAO.insert(hafOperateLog);
      }

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void pprovalData(String headId, SecurityInfo securityInfo,
      String temp_p) throws BusinessException {
    PickHead head = headDao.queryById(new Integer(headId));// 根据得到的头表对象可以导航出来orgID
    // boolean flag=
    // autoInfoPickDAODW.isNOPickIn(head.getOrg().getId().toString(),head.getId().toString(),BusiConst.ORGBUSINESSTYPE_D);
    try {
      // if(!flag){
      String status = autoInfoPickDAODW.findStatus(head.getOrg().getId()
          .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      if (status.equals(BusiConst.OC_PICK_UP)) {
        throw new BusinessException("该业务已被中心提取，不可以撤销！");
      }
      if (status.equals(BusiConst.OC_PICK_UP_OVER)) {
        throw new BusinessException("该笔业务没有提交，不可以撤销！");
      }
      // }else{
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      autoInfoPickDAODW.deleteAutoInfoPick(head.getOrg().getId().toString(),
          head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      // 插入BA003：
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      if (temp_p.equals("1")) {
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// 办理
      } else {
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN));// 维护
      }
      hafOperateLog.setOpButton(Integer
          .toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
      hafOperateLog.setOpBizId(new Integer(head.getId().toString()));// AA306.ID
      hafOperateLog.setOperator(name);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(head.getOrg().getId().toString()));
      hafOperateLogDAO.insert(hafOperateLog);
      // }

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void pickUpData(String headId, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      String minid = autoInfoPickDAO.findOrgHeadid(headId,
          BusiConst.ORGBUSINESSTYPE_D, BusiConst.OC_NOT_PICK_UP);// 最小的头表id
      if (minid == null || "".equals(minid)) {
        throw new BusinessException("该单位不存在未提取的记录");
      } else {
        PickHead head = pickupheadDaoDW.queryById(new Integer(minid));// 根据得到的头表对象可以导航出来orgID
        List list = tailDaoDW.findPickTailList(minid);// 在单位版数据库中，根据取出的ORG_HEAD_ID查询AA306、AA307，取出的记录
        List headlist = new ArrayList();
        PickHeadImportDTO dtoo1 = new PickHeadImportDTO();
        PickHeadImportDTO dtoo2 = new PickHeadImportDTO();
        dtoo2.setOrgId(head.getOrg().getId().toString());
        dtoo2.setOrgNoteNumber(head.getNoteNum());
        headlist.add(dtoo1);
        headlist.add(dtoo2);
        this.getBatchErrorData(headlist, list, securityInfo, "", "");
        String bizdate = "1";
        autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP, temp_headid,
            bizdate, head.getOrg().getId().toString(), head.getId().toString(),
            BusiConst.ORGBUSINESSTYPE_D);
        // 插入BA003：
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// 办理
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
        hafOperateLog.setOpBizId(new Integer(temp_headid));// AA306.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(head.getOrg().getId().toString()));
        hafOperateLogDAO.insert(hafOperateLog);
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
  }

  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    // 查询办事处银行开始
    String officeName = "";
    String bankName = "";
    String str[] = new String[2];
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryByBizId_wsh(bizId, bizType);
    if (orgHAFAccountFlow != null) {
      if (orgHAFAccountFlow.getOfficeCode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(orgHAFAccountFlow
              .getOfficeCode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      if (orgHAFAccountFlow.getMoneyBank() != null) {
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(orgHAFAccountFlow.getMoneyBank());
        bankName = collBank.getCollBankName();
      }
    } else {
      Org org = null;
      String orgid = "";
      if (orgId != null) {
        orgid = orgId;
      }
      org = orgDao.queryByCriterions(orgid, "2", null, securityInfo);
      if (org == null && orgid != null) {
        org = new Org();
      }
      if (org.getOrgInfo().getOfficecode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(org.getOrgInfo()
              .getOfficecode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (org.getOrgInfo().getCollectionBankId() != null) {
        CollBank collBank = collBankDAO.getCollBankByCollBankid(org
            .getOrgInfo().getCollectionBankId());
        bankName = collBank.getCollBankName();
      }
    }
    // 查询办事处银行结束
    str[0] = officeName;
    str[1] = bankName;
    return str;
  }

  public List getExportData(final int orgId, String ip, String operator,
      Pagination pagination) {
    // 插入各种日志...
    List returnlist = new ArrayList();
    String orderArray[] = (String[]) pagination.getQueryCriterions().get(
        "orderArray");

    HafOperateLog haf = new HafOperateLog();
    // OPA_ID为AA001中插入记录的ID
    // OP_TIME为系统时间
    // OPERATOR为办理开户的操作员
    haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// 住房公积金归集系统
    haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// 操作模快
    haf.setOpButton(BusiLogConst.BIZLOG_ACTION_EXP + "");// 操作
    haf.setOpBizId(new Integer(0));// 取头表ID
    haf.setOpIp(ip);// IP地址
    haf.setOpTime(new Date(new java.util.Date().getTime()));// 时间
    haf.setOperator(operator);
    haf.setOrgId(new Integer(orgId));
    hafDao.insert(haf);
    PartPickupConditionDTO partPickupConditionDTO = new PartPickupConditionDTO();
    try {
      partPickupConditionDTO = partPickupConditionDAO
          .queryPartPickupConditionInfo();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    List templist = empDao.getExportData(orgId, orderArray);
    for (int i = 0; i < templist.size(); i++) {
      Emp emp = (Emp) templist.get(i);
      emp.setMul(partPickupConditionDTO.getMultiple());
      returnlist.add(emp);
    }
    return returnlist;
  }

  public String findversionflag(Pagination pagination, PickHead p,
      SecurityInfo securityInfo) throws BusinessException {
    // TODO Auto-generated method stub
    String flag = "0";

    // 判断是否存在单位版
    int IsHaveOrgVersion = securityInfo.getIsHaveOrgVersion();
    if (IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
      int isCentEdition = securityInfo.getIsOrgEdition();
      // 判断是否为中心版
      if (isCentEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
        String orgid = (String) pagination.getQueryCriterions().get("idValue")
            .toString();
        flag = autoInfoPickDAODW.findStatus(orgid, p.getId().toString(),
            BusiConst.ORGBUSINESSTYPE_D);
      }
    }
    return flag;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public boolean updatePickHead(String id, String empId, String path)
      throws Exception {
    boolean flag = false;
    try {
      PickTail tail = tailDao.findPickTailByHeadIdAndEmpId(new Integer(id)
          .intValue(), new Integer(empId).intValue());
      // PickHead pickhead = headDao.queryById(new Integer(id));// 查询额度信息
      if (tail != null) {
        tail.setPhotourl(path);
        flag = true;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return flag;
  }

  public List checkpicker(String card_num, String card_num_two)
      throws Exception {
    List flaglist = new ArrayList();
    try {
      flaglist = tailDao.checkperson(card_num, card_num_two);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return flaglist;
  }

  public String checkpicker_yg(String card_num, String card_num_two)
      throws Exception {
    String count = "";
    try {
      count = tailDao.checkperson_yg(card_num, card_num_two);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  // 吴洪涛 2008-6-16//查询出银行名称
  public String queryCollectionBankNameById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CollBank collBank = new CollBank();
    try {
      // 单位是否存在
      Org org = null;
      org = orgDao.queryByCriterions(id, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      String collectionBankId = org.getOrgInfo().getCollectionBankId();
      if (collectionBankId != null && !collectionBankId.equals("")) {
        collBank = empDao.getCollBankByCollBankid(collectionBankId);
      }
      if (collBank == null) {
        collBank = new CollBank();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBank.getCollBankName();
  }

  // 根据头表的id查询尾表的职工
  public List querytailbyheadid(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List returnlist = new ArrayList();
    try {
      returnlist = tailDao.getTailbyHeadid(new Integer(id).intValue());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnlist;

  }

  public String queryNoteNum() throws Exception, BusinessException {

    String o = "";
    try {
      o = orgDao.queryNoteNum();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;

  }

  public String findAA103_DayTime(final String collbankid) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = orgDao.findAA103_DayTime(collbankid);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;

  }

  public String find_empid_card_num(final String orgid, final String empid)
      throws Exception, BusinessException {
    String o = "";
    try {
      o = orgDao.find_empid_card_num(orgid, empid);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public int getpickup_pl(final int orgId) throws Exception, BusinessException {
    int o = 0;
    try {
      o = tailDao.getpickup_pl(orgId);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public int getpickup_not_pl(final int orgId) throws Exception,
      BusinessException {
    int o = 0;
    try {
      o = tailDao.getpickup_not_pl(orgId);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public String getpickup_oper(final String id) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = tailDao.getpickup_oper(id);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public String getpickup_check(final String id) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = tailDao.getpickup_check(id);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public String find_user_realname(final String user) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = orgDao.find_user_realname(user);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o + "";
  }

  public void updateAA306_1(final String reason) throws Exception,
      BusinessException {
    try {
      empDao.deleteAA306_1();
      empDao.insertIntoAA306_1(reason);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getAA306_1() throws Exception, BusinessException {
    return empDao.getAA306_1();
  }

  public void deleteAA306_1() throws Exception, BusinessException {
    empDao.deleteAA306_1();
  }

  public List getpickup_not_aa306(final int orgId) throws Exception,
      BusinessException {
    List list = null;
    try {
      list = tailDao.getpickup_not_aa306(orgId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getPickCheckList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = null;
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String orgname = (String) pagination.getQueryCriterions().get("orgname");
    String begdate = (String) pagination.getQueryCriterions().get("begdate");
    String enddate = (String) pagination.getQueryCriterions().get("enddate");
    String checkbegdate = (String) pagination.getQueryCriterions().get(
        "checkbegdate");
    String checkenddate = (String) pagination.getQueryCriterions().get(
        "checkenddate");
    String ischecked = (String) pagination.getQueryCriterions()
        .get("ischecked");
    try {
      list = tailDao.getPickCheckList(orgid, orgname, begdate, enddate,
          checkbegdate, checkenddate, ischecked);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void setPickCheckStatus(String date, String type, String id)
      throws Exception, BusinessException {
    tailDao.setPickCheckStatus(date, type, id);
  }

}
