package org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.NameAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickGetEmployeeInfoAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupAddAF;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

public interface IPickupBS {
  public boolean queryUnitAcc(String org_id) throws BusinessException;

  public String checkpicker_yg(String card_num, String card_num_two)
      throws Exception;

  public String getAA307PhotoURL(String pickheadid);

  public void updatePickHeadReserveaA(Integer headId, String status,
      String registrations) throws BusinessException;

  public boolean getOrgPayMonthEmpPayMonth(String orgid, String empid);

  public String getPrintAllEmployeeList_yga(String id) throws BusinessException;

  public Org getOrgByHeadId(Integer headId) throws BusinessException;

  public String findPickHeadSOneByOrgId(int orgId) throws BusinessException;

  public Org orgFindById(int id, SecurityInfo info) throws BusinessException;

  public boolean overPick(int orgId, String ip, String operator,
      String moneyDate, String noteNumber, SecurityInfo info) throws Exception;

  public boolean overPickVindicate(int orgId, String ip, String operator,
      String moneyDate, String noteNumber, SecurityInfo info) throws Exception;

  public void deleteHeadTailByHeadId(int headId, String ip, String operator,
      SecurityInfo info) throws BusinessException;

  public PickHead queryById(Integer headId) throws BusinessException;

  public OrgInfo orgInfoFindById(int id) throws BusinessException;

  public OrgInfo findOrgInfoStatus(Serializable id) throws BusinessException;

  public PickHead headFindById(Serializable id) throws BusinessException;

  public PickHead findPickHeadOrgIdStatusIsOne(int id) throws BusinessException;

  public List findTailByHeadId(PickHead head) throws BusinessException;

  public List getPrintAllEmployeeList_yg(String id) throws BusinessException;

  public List getVindicateAllDate(Pagination pa, SecurityInfo info)
      throws BusinessException;

  public List findPickHeadStateByOrgIdAndEmpId(int orgId, int empId)
      throws BusinessException;

  public List findFirstPageData(Pagination p) throws BusinessException;

  public PickHead findStatusNotIsFive(int id) throws BusinessException;

  public String findOrdIdByHeadId(int headId) throws BusinessException;

  public List getVindicatePageData(Pagination pa, SecurityInfo info)
      throws BusinessException;

  public String getBatchErrorData(List headList, List tailList,
      SecurityInfo info, String note_num, String other_card_num)
      throws BusinessException, Exception;

  public SpecialPick findSpecialPickByOrgId(int id) throws BusinessException;

  public Emp findEmpByOrdIdAndEmpId(Integer orgId, Integer empId)
      throws BusinessException;

  public List findPickTailByEmpId(Integer empId) throws BusinessException;

  public List getYearPickBalance(int empId, SecurityInfo info)
      throws BusinessException;

  public String recallPickup(int headId, SecurityInfo info)
      throws BusinessException;

  public double getDistroyPickupInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException;

  public BigDecimal getDistroyPickupInterest1(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException;

  public void savePickup(PickupAddAF a,
      PickGetEmployeeInfoAF pickGetEmployeeInfoAF, SpecialPick s, String ip,
      String name, String moneyDate) throws BusinessException;

  public SpecialPick isSpecialPick(int orgId, int empId)
      throws BusinessException;

  public List getExportData(final int orgId, String ip, String operator);

  public Serializable insertPickTail(PickTail pickTail);

  public void deleteEmployee(int headId, int empId, String last, String ip,
      String operator, SecurityInfo info) throws BusinessException;

  public List findDataByHeadId(int headId) throws BusinessException;

  public void allDelete(int headId, String ip, String operator,
      SecurityInfo info) throws BusinessException;

  public List getEmployeePickInfo(Pagination p) throws BusinessException;

  public List getEmployeePickCountMoney(Pagination p) throws BusinessException;

  public Org findOrgById(Integer i) throws BusinessException;

  public List findTheOrgAllEmployee(Pagination p) throws BusinessException;

  public List getFirstPageCount(String id) throws BusinessException;

  public List getPrintAllEmployeeList(String id) throws BusinessException;

  public void update(int headId, int orgId, String ip, String operator)
      throws BusinessException;

  public boolean check(String orgID, SecurityInfo info)
      throws BusinessException;

  public NameAF findName(String orgID) throws BusinessException;

  public NameAF findpickTail(String id) throws BusinessException;

  public boolean checkTranOut(String orgID);

  public String findCollBank(String collBankid);

  public OrgInfo findOrgInfoStatus1(Serializable id) throws BusinessException;

  public void pickUpData(String headId, SecurityInfo securityInfo)
      throws BusinessException;

  public void referringData(String headId, SecurityInfo securityInfo,
      String temp_p) throws BusinessException;

  public void pprovalData(String headId, SecurityInfo securityInfo,
      String temp_p) throws BusinessException;

  // 查询单位的办事处和银行（在录入清册、登记状态为归集信息的办事处银行，在确认、复核、入账状态为流水中的办事处银行）
  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception;

  public List getExportData(final int orgId, String ip, String operator,
      Pagination pagination);

  public String findversionflag(Pagination p, PickHead ph, SecurityInfo info)
      throws BusinessException;

  public boolean updatePickHead(String id, String empId, String path)
      throws Exception;// ld_add添加扫描路径

  public List checkpicker(String card_num, String card_num_two)
      throws Exception;// 判断是否是担保人，借款人

  public String queryCollectionBankNameById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  public List querytailbyheadid(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException;// 根据头表的id查尾表的信息

  public String queryNoteNum() throws Exception, BusinessException;

  public String findAA103_DayTime(final String collbankid) throws Exception,
      BusinessException;

  public String find_empid_card_num(final String orgid, final String empid)
      throws Exception, BusinessException;

  public int getpickup_pl(final int orgId) throws Exception, BusinessException;

  public int getpickup_not_pl(final int orgId) throws Exception,
      BusinessException;

  public String getpickup_oper(final String id) throws Exception,
      BusinessException;

  public String getpickup_check(final String id) throws Exception,
      BusinessException;

  public String find_user_realname(final String user) throws Exception,
      BusinessException;

  public void updateAA306_1(final String reason) throws Exception,
      BusinessException;

  public String getAA306_1() throws Exception, BusinessException;

  public void deleteAA306_1() throws Exception, BusinessException;

  public List getpickup_not_aa306(final int orgId) throws Exception,
      BusinessException;

  public List getPickCheckList(Pagination paginaction, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  public void setPickCheckStatus(String date, String type, String id)
      throws Exception, BusinessException;
}