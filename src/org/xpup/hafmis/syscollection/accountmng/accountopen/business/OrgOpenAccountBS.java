package org.xpup.hafmis.syscollection.accountmng.accountopen.business;

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
import org.xpup.hafmis.orgstrct.domain.RelaUserAndOrg;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpAgentImportDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpAgentImportTitleDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenImpContentDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenImpTitleDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.OrgAgentImportDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.PrintListDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChangeAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpkhAF;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.BaseGhgInfoDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgChgLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgEditionDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgOpenAccountBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.SearchLackInfoDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.OrgDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.OrgInfoDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.SearchLackInfoDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.BaseGhgInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgEdition;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgOpenAccountBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.SearchLackInfo;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
import org.xpup.hafmis.syscommon.dao.EmpInfoDAO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;
import org.xpup.hafmis.syscommon.domain.entity.PayBank;
import org.xpup.hafmis.syscommon.domain.entity.Transactor;

public class OrgOpenAccountBS implements IOrgOpenAccountBS {
  protected OrgDAO orgDAO = null;

  protected EmpDAO empDAO = null;

  protected EmpInfoDAO empInfoDAO = null;

  protected HafOperateLogDAO hafOperateLogDAO = null;

  protected OrgOpenAccountBizActivityLogDAO orgOpenAccountBizActivityLogDAO = null;

  protected BaseGhgInfoDAO baseGhgInfoDAO = null;

  protected OrgInfoDAO orgInfoDAO = null;

  protected OrgChgLogDAO orgChgLogDAO = null;

  protected SearchLackInfoDAO searchLackInfoDAO = null;

  protected CollBankDAO collBankDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  protected BizActivityLogDAO bizActivityLogDAO = null;

  private OrgDAODW orgDAODW = null;

  private OrgEditionDAO orgEditionDAO = null;

  private OrgInfoDAODW orgInfoDAODW = null;

  private EmpDAODW empDAODW = null;

  private SearchLackInfoDAODW searchLackInfoDAODW = null;

  private AutoInfoPickDAO autoInfoPickDAO = null;

  private AutoInfoPickDAODW autoInfoPickDAODW = null;

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public void setOrgInfoDAO(OrgInfoDAO orgInfoDAO) {
    this.orgInfoDAO = orgInfoDAO;
  }

  public Serializable saveOrgOpenAccount(Org org, SecurityInfo securityInfo,
      String isOrgorcenter) throws BusinessException, Exception {
    org.getOrgInfo().setOpenstatus("1");
    if (org.getOrgAgentNum() != null && !org.getOrgAgentNum().equals("")) {
      // 判断单位代扣号是否存在
      int count = orgDAO.queryOrgAgentNumBySave(org.getOrgAgentNum(),
          securityInfo);
      if (count > 0) {
        throw new BusinessException("代为代扣号已经存在！");
      }
    }
    // 付云峰修改：从session中取得会计日期
    org.getOrgInfo().setOpenDate(securityInfo.getUserInfo().getBizDate());
    String paymonth = BusiTools.addMonth(org.getFirstPayMonth(), -1);
    org.setEmpPayMonth(paymonth);
    org.setOrgPayMonth(paymonth);
    Serializable id = orgDAO.insert(org.getOrgInfo());

    org.getOrgInfo().setId(id);
    // org.setFirstPayment(BusiTools.addMonths(BusiTools.dateToString(
    // new java.util.Date(), BusiConst.PUB_LONG_DATE_PATTERNS), 1));

    Serializable iid = orgDAO.insert(org);
    OrgOpenAccountBizActivityLog log = new OrgOpenAccountBizActivityLog();
    // 付云峰修改: 将AA001的id放入Bizid
    log.setBizid((Integer) iid);
    log.setAction(new Integer(2));
    log.setOpTime(new java.util.Date());
    log.setOperator(securityInfo.getUserName());

    orgOpenAccountBizActivityLogDAO.insert(log);
    HafOperateLog hafOperateLog = new HafOperateLog();
    // 插入日志BA003
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(iid.toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(iid.toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
    orgDAO.insertUserOrgPre(securityInfo.getUserName(), Integer.parseInt(iid
        .toString()), Integer.parseInt(org.getOrgInfo().getCollectionBankId()),
        org.getOrgInfo().getOfficecode());
    // 20071213修改
    if (isOrgorcenter.equals("1")) {
      // 插入DA002
      OrgEdition orgEdition = new OrgEdition();
      orgEdition.setIsOrg(new Integer("1"));
      orgEdition.setOrgId(new Integer(iid + ""));
      orgEditionDAO.insert(orgEdition);
      // 插入单位版aa001
      org.getOrgInfo().setId(id);
      orgDAODW.insert(org.getOrgInfo());
      org.setId(iid);
      orgDAODW.insert(org);
    }
    // 20071213结束
    return id;
  }

  public Serializable saveOrgOpenAccount_yg(Org org, SecurityInfo securityInfo,
      String isOrgorcenter) throws BusinessException, Exception {
    org.getOrgInfo().setOpenstatus("1");
    if (org.getOrgAgentNum() != null && !org.getOrgAgentNum().equals("")) {
      // 判断单位代扣号是否存在
      int count = orgDAO.queryOrgAgentNumBySave(org.getOrgAgentNum(),
          securityInfo);
      if (count > 0) {
        throw new BusinessException("代为代扣号已经存在！");
      }
    }
    // 付云峰修改：从session中取得会计日期
    org.getOrgInfo().setOpenDate(securityInfo.getUserInfo().getBizDate());

    String paymonth = BusiTools.addMonth(org.getFirstPayMonth(), -1);
    org.setEmpPayMonth(paymonth);
    org.setOrgPayMonth(paymonth);
    Serializable officeid = org.getOrgInfo().getOfficecode();
    Map office = securityInfo.getOfficeInnerCodeMap();
    String officecode = office.get(officeid).toString();
    if (officecode.length() == 1) {
      officecode = "0" + officecode;
    }
    Serializable id = orgDAO.insert(org.getOrgInfo());

    // org.getOrgInfo().setId(id);
    // org.setFirstPayment(BusiTools.addMonths(BusiTools.dateToString(
    // new java.util.Date(), BusiConst.PUB_LONG_DATE_PATTERNS), 1));
    String myid = id.toString();
    if (myid.length() == 1) {
      myid = "00000" + myid;
    }
    if (myid.length() == 2) {
      myid = "0000" + myid;
    }
    if (myid.length() == 3) {
      myid = "000" + myid;
    }
    if (myid.length() == 4) {
      myid = "00" + myid;
    }
    if (myid.length() == 5) {
      myid = "0" + myid;
    }
    String bankcode = org.getOrgInfo().getCollectionBankId();
    if (bankcode.length() == 1) {
      bankcode = "0" + bankcode;
    }
    String id9 = officecode + bankcode + myid;
    Serializable iid = orgDAO.insert(org);
    OrgOpenAccountBizActivityLog log = new OrgOpenAccountBizActivityLog();
    // 付云峰修改: 将AA001的id放入Bizid
    log.setBizid((Integer) iid);
    log.setAction(new Integer(2));
    log.setOpTime(new java.util.Date());
    log.setOperator(securityInfo.getUserName());

    orgOpenAccountBizActivityLogDAO.insert(log);
    HafOperateLog hafOperateLog = new HafOperateLog();
    // 插入日志BA003
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(iid.toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(iid.toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
    orgDAO.insertUserOrgPre(securityInfo.getUserName(), Integer.parseInt(id9),
        Integer.parseInt(org.getOrgInfo().getCollectionBankId()), org
            .getOrgInfo().getOfficecode());
    // 20071213修改
    if (isOrgorcenter.equals("1")) {
      // 插入DA002
      OrgEdition orgEdition = new OrgEdition();
      orgEdition.setIsOrg(new Integer("1"));
      orgEdition.setOrgId(new Integer(iid + ""));
      orgEditionDAO.insert(orgEdition);
      // 插入单位版aa001
      org.getOrgInfo().setId(id);
      orgDAODW.insert(org.getOrgInfo());
      org.setId(iid);
      orgDAODW.insert(org);
    }
    // 20071213结束
    orgDAO.update_yg(id9, myid); // 将序列产生的单位ID更新为有意义的单位ID
    return id9;
  }

  public Org findOPA(Integer id) throws BusinessException {
    return orgDAO.queryById(id);
  }

  public Org findOPA_zl(Integer id, SecurityInfo info) throws BusinessException {
    return orgDAO.findById(id, info);
  }

  /**
   * @param pagination
   * @param temp_Type 用来判断是否为条件查询
   * @return
   * @throws BusinessException
   */
  public List findOrganizationsDwkh(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {

    List orgs = null;
    Serializable id = (Serializable) pagination.getQueryCriterions().get("id");
    String name = (String) pagination.getQueryCriterions().get("name");
    String temp_Type = (String) pagination.getQueryCriterions()
        .get("temp_Type");
    Serializable status = (Serializable) pagination.getQueryCriterions().get(
        "status");
    Serializable payMode = (Serializable) pagination.getQueryCriterions().get(
        "payMode");
    
    String startdate = (String) pagination.getQueryCriterions().get("startdate");
    String enddate = (String) pagination.getQueryCriterions().get("enddate");
    String pagetype = (String) pagination.getQueryCriterions().get("pagetype");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    orgs = orgDAO.queryOrgOpenByCriterions(id, name, payMode, status, orderBy,
        order, start, pageSize, temp_Type, securityInfo,startdate,enddate,pagetype);

    List returnOrgs = new ArrayList();
    if (orgs != null) {
      for (int i = 0; i < orgs.size(); i++) {

        Org org = (Org) orgs.get(i);
        try {
          // 付云峰修改：转换缴存方式与单位状态
          org.getOrgInfo().setOpenstatus(
              (BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
                  .getOpenstatus()), BusiConst.ORGSTATE)));
          org.setPayModeStr((BusiTools.getBusiValue(
              org.getPayMode().intValue(), BusiConst.ORGPAYWAY)));
          // 归集银行
          CollBank dto = collBankDAO.getCollBankByCollBankid(org.getOrgInfo()
              .getCollectionBankId());
          org.getOrgInfo().setCollectionBankId(dto.getCollBankName());
          // 办事处
          OrganizationUnit organizationUnit = organizationUnitDAO
              .queryOrganizationUnitListByCriterions(org.getOrgInfo()
                  .getOfficecode());
          org.getOrgInfo().setOfficecode(organizationUnit.getName());
          // 20071213 判断DA001状态
          String istype = securityInfo.getIsOrgEdition() + "";
          String tempstatus = "2";
          if (istype.equals("2")) {
            tempstatus = autoInfoPickDAO.findStatus(org.getId().toString(), org
                .getId().toString(), BusiConst.ORGBUSINESSTYPE_P);
          } else {
            tempstatus = autoInfoPickDAODW.findStatus(org.getId().toString(),
                org.getId().toString(), BusiConst.ORGBUSINESSTYPE_P);
          }
          if (tempstatus != null && !tempstatus.equals("")) {
            org.setOrgStatus(BusiTools.getBusiValue(new Integer(tempstatus)
                .intValue(), BusiConst.OC_NOT_PICK_UP_INFO));
          }
        } catch (NumberFormatException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }

        returnOrgs.add(org);
      }
    }

    int count = orgDAO.queryOrgOpenCountByCriterions(id, name, payMode, status,
        temp_Type, securityInfo,startdate,enddate,pagetype);
    pagination.setNrOfElements(count);
    return returnOrgs;
  }

  /**
   * 单位开户修改
   * 
   * @see org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS#modifyOpen(org.xpup.hafmis.syscollection.common.domain.entity.Org)
   */
  public Serializable modifyOpen(Org org, SecurityInfo securityInfo)
      throws BusinessException {
    // 判断单位代扣号是否重复
    int count = orgDAO.queryOrgAgentNum(org.getOrgAgentNum(), org.getId()
        .toString(), securityInfo);
    if (count > 0) {
      throw new BusinessException("单位代扣号已经存在");
    }
    Org oldOrg = orgDAO.queryById(new Integer(org.getId().toString()));

    // //吴洪涛修改 2008-3-18 查询从 RelaUserAndOrg bb106
    List listRelaUserAndOrg = orgDAO.queryRelaUserAndOrglist_wuht(oldOrg
        .getId().toString());// 中心的

    // 存放被修改的字段
    List list = new ArrayList();
    String[] column = null;
    String temp_old = "";
    String temp_new = "";

    /** 判断被修改的字段 */
    // 单位代扣号
    String oldOrgAgentNum = oldOrg.getOrgAgentNum();
    String orgAgentNum = org.getOrgAgentNum();
    if (orgAgentNum == null || orgAgentNum.equals("")) {
      orgAgentNum = "无";
    }
    if (oldOrgAgentNum == null || oldOrgAgentNum.equals("")) {
      oldOrgAgentNum = "无";
    }
    if (!oldOrgAgentNum.equals(orgAgentNum)) {
      column = new String[3];
      column[0] = "单位代扣号";
      column[1] = oldOrgAgentNum;
      column[2] = orgAgentNum;
      list.add(column);
    }
    // 单位名称
    if (!org.getOrgInfo().getName().equals(oldOrg.getOrgInfo().getName())) {
      column = new String[3];
      column[0] = "单位名称";
      // 修改前
      column[1] = oldOrg.getOrgInfo().getName();
      // 修改后
      column[2] = org.getOrgInfo().getName();
      list.add(column);
    }
    // 办事处名称
    if (!org.getOrgInfo().getOfficecode().equals(
        oldOrg.getOrgInfo().getOfficecode())) {
      column = new String[3];
      column[0] = "办事处名称";
      column[1] = oldOrg.getOrgInfo().getOfficecode();
      column[2] = org.getOrgInfo().getOfficecode();
      list.add(column);
      // //吴洪涛修改 2008-3-18 如果办事处被修改了的话 着对应的bb106 里面的每一个办事处都需要修改//中心的
      if (listRelaUserAndOrg != null && listRelaUserAndOrg.size() > 0) {
        for (int i = 0; i < listRelaUserAndOrg.size(); i++) {
          RelaUserAndOrg relaUserAndOrg = (RelaUserAndOrg) listRelaUserAndOrg
              .get(i);
          relaUserAndOrg.setOffice(org.getOrgInfo().getOfficecode());
        }
      }

    }
    // 组织机构代码

    String oldcode = oldOrg.getOrgInfo().getCode();
    String code = org.getOrgInfo().getCode();
    if (code == null || code.equals("")) {
      code = "无";
    }
    if (oldcode == null || oldcode.equals("")) {
      oldcode = "无";
    }
    if (!oldcode.equals(code)) {
      column = new String[3];
      column[0] = "组织机构代码";
      column[1] = oldcode;
      column[2] = code;
      list.add(column);
    }
    // 税务登记号
    temp_old = oldOrg.getOrgInfo().getTaxRegNum();
    temp_new = org.getOrgInfo().getTaxRegNum();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_old.equals(temp_new)) {
      column = new String[3];
      column[0] = "税务登记号";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 单位法人
    temp_old = oldOrg.getOrgInfo().getArtificialPerson();
    temp_new = org.getOrgInfo().getArtificialPerson();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_old.equals(temp_new)) {
      column = new String[3];
      column[0] = "单位法人";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    if (oldOrg.getOrgInfo().getPayBank() == null) {
      oldOrg.getOrgInfo().setPayBank(new PayBank());
    }
    // 单位发薪银行
    temp_old = oldOrg.getOrgInfo().getPayBank().getName();
    temp_new = org.getOrgInfo().getPayBank().getName();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_old.equals(temp_new)) {
      column = new String[3];
      column[0] = "单位发薪银行";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 发薪行帐号
    temp_old = oldOrg.getOrgInfo().getPayBank().getAccountNum();
    temp_new = org.getOrgInfo().getPayBank().getAccountNum();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_old.equals(temp_new)) {
      column = new String[3];
      column[0] = "发薪行帐号";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 单位性质
    if (!org.getOrgInfo().getCharacter().equals(
        oldOrg.getOrgInfo().getCharacter())) {
      column = new String[3];
      column[0] = "单位性质";
      column[1] = oldOrg.getOrgInfo().getCharacter();
      column[2] = org.getOrgInfo().getCharacter();
      list.add(column);
    }
    // 所属行业
    if (!org.getOrgInfo().getIndustry().equals(
        oldOrg.getOrgInfo().getIndustry())) {
      column = new String[3];
      column[0] = "所属行业";
      column[1] = oldOrg.getOrgInfo().getIndustry();
      column[2] = org.getOrgInfo().getIndustry();
      list.add(column);
    }
    // 主管部门
    if (!org.getOrgInfo().getDeptInCharge().equals(
        oldOrg.getOrgInfo().getDeptInCharge())) {
      column = new String[3];
      column[0] = "主管部门";
      column[1] = oldOrg.getOrgInfo().getDeptInCharge();
      column[2] = org.getOrgInfo().getDeptInCharge();
      list.add(column);
    }
    // 单位地址
    temp_old = oldOrg.getOrgInfo().getAddress();
    temp_new = org.getOrgInfo().getAddress();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "单位地址";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 邮政编码
    temp_old = oldOrg.getOrgInfo().getPostalcode();
    temp_new = org.getOrgInfo().getPostalcode();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "邮政编码";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 单位电话
    temp_old = oldOrg.getOrgInfo().getTel();
    temp_new = org.getOrgInfo().getTel();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "单位电话";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 所在地区
    if (!org.getOrgInfo().getRegion().equals(oldOrg.getOrgInfo().getRegion())) {
      column = new String[3];
      column[0] = "所在地区";
      column[1] = oldOrg.getOrgInfo().getRegion();
      column[2] = org.getOrgInfo().getRegion();
      list.add(column);
    }
    // 发薪日
    temp_old = oldOrg.getOrgInfo().getPaydate();
    temp_new = org.getOrgInfo().getPaydate();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "发薪日";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 归集银行
    if (!org.getOrgInfo().getCollectionBankId().equals(
        oldOrg.getOrgInfo().getCollectionBankId())) {
      column = new String[3];
      column[0] = "归集银行";
      column[1] = oldOrg.getOrgInfo().getCollectionBankId();
      column[2] = org.getOrgInfo().getCollectionBankId();
      list.add(column);

      // //吴洪涛修改 2008-3-18 如果办事处被修改了的话 着对应的bb106 里面的每一个归集银行都需要修改//中心的
      if (listRelaUserAndOrg != null && listRelaUserAndOrg.size() > 0) {
        for (int i = 0; i < listRelaUserAndOrg.size(); i++) {
          RelaUserAndOrg relaUserAndOrg = (RelaUserAndOrg) listRelaUserAndOrg
              .get(i);
          relaUserAndOrg.setCollBankId(org.getOrgInfo().getCollectionBankId());
        }
      }

    }
    // 单位经办人
    if(oldOrg.getOrgInfo().getTransactor()==null){
      oldOrg.getOrgInfo().setTransactor(new Transactor()); 
    }
   
   // temp_old = oldOrg.getOrgInfo().getTransactor().getName();
    temp_new = org.getOrgInfo().getTransactor().getName();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "单位经办人";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 经办人E-mial
   // temp_old = oldOrg.getOrgInfo().getTransactor().getEmail();
    temp_new = org.getOrgInfo().getTransactor().getEmail();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "经办人E-mial";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 经办人电话
    //temp_old = oldOrg.getOrgInfo().getTransactor().getTelephone();
    temp_new = org.getOrgInfo().getTransactor().getTelephone();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "经办人电话";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 经办人移动电话
   // temp_old = oldOrg.getOrgInfo().getTransactor().getMobileTelephone();
    temp_new = org.getOrgInfo().getTransactor().getMobileTelephone();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "经办人移动电话";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 稽查员
    temp_old = oldOrg.getOrgInfo().getInspector();
    temp_new = org.getOrgInfo().getInspector();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "稽查员";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 缴存方式
    if (!org.getPayMode().equals(oldOrg.getPayMode())) {
      column = new String[3];
      column[0] = "缴存方式";
      column[1] = oldOrg.getPayMode().toString();
      column[2] = org.getPayMode().toString();
      list.add(column);
    }
    // 单位缴率
    temp_old = oldOrg.getOrgRate().toString();
    temp_new = org.getOrgRate().toString();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "单位缴率";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 职工缴率
    temp_old = oldOrg.getEmpRate().toString();
    temp_new = org.getEmpRate().toString();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "职工缴率";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
    }
    // 初缴年月
    temp_old = oldOrg.getFirstPayMonth();
    temp_new = org.getFirstPayMonth();
    if (temp_old == null || temp_old.equals("")) {
      temp_old = "无";
    }
    if (temp_new == null || temp_new.equals("")) {
      temp_new = "无";
    }
    if (!temp_new.equals(temp_old)) {
      column = new String[3];
      column[0] = "初缴年月";
      column[1] = temp_old;
      column[2] = temp_new;
      list.add(column);
      String paymonth = BusiTools.addMonth(temp_new, -1);
      org.setEmpPayMonth(paymonth);
      org.setOrgPayMonth(paymonth);
    }
    // 缴存精度
    if (!org.getPayPrecision().equals(oldOrg.getPayPrecision())) {
      column = new String[3];
      column[0] = "缴存精度";
      column[1] = oldOrg.getPayPrecision().toString();
      column[2] = org.getPayPrecision().toString();
      list.add(column);
    }
    /** 判断结束 */
    // 插入AA004表
    for (int i = 0; i < list.size(); i++) {
      BaseGhgInfo baseGhgInfo = new BaseGhgInfo();
      String[] column1 = (String[]) list.get(i);
      baseGhgInfo.setChgColumn(column1[0]);
      baseGhgInfo.setChgBefInfo(column1[1]);
      baseGhgInfo.setChgEndInfo(column1[2]);
      baseGhgInfo.setOpTime(new Date());
      baseGhgInfo.setOperator(securityInfo.getUserName());
      baseGhgInfo.setOrg(oldOrg);
      baseGhgInfoDAO.insert(baseGhgInfo);
    }
    HafOperateLog hafOperateLog = new HafOperateLog();
    // 插入日志BA003
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
    hafOperateLog.setOpBizId(new Integer(org.getId().toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(org.getId().toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
    // 判断是否存在单位版20071213
    OrgEdition orgEdition = orgEditionDAO.queryByOrgId_sy(new Integer(org
        .getId().toString()));
    if (orgEdition != null) {
      orgDAODW.update(org);
    }
    return orgDAO.update(org);
  }

  /**
   * 删除单位
   */
  public int removeOrg(String orgId) throws BusinessException {
    int i = 0;
    List list = empDAO.queryEmpByOrgIdSL(orgId);
    // 判断该单位下是否有职工1 有职工，2 没有职工
    if (list.size() != 0) {
      i = 1;
    } else {
      i = 2;
    }
    return i;
  }

  /**
   * 删除单位下职工
   */
  public void removeOrgEmp(String id, SecurityInfo securityInfo)
      throws BusinessException {
   // List list = empDAO.queryEmpByOrgIdSL(id);
    Org org = new Org();
    orgOpenAccountBizActivityLogDAO.deleteOrgSL(id);
//    if (list.size() != 0) {
//      // 说明该单位在BA002中有职工
//      Emp emp = null;
//      for (int i = 0; i < list.size(); i++) {
//        emp = (Emp) list.get(i);
//        // 判断是否存在相同的empid
//        boolean flag = empDAO.getEmpidCount(emp.getEmpId());
//        // AA002
    System.out.println("---orgopenaccountbs...--------");
          empInfoDAO.deleteEmpInfoByIdSL_org(new Integer(id).toString());
          empDAO.deleteEmpByIdSL_org(new Integer(id).toString());
//        // BA002
//        if (!flag) {
         
//        }
//      }
//    }
    org = orgDAO.queryById(new Integer(id));
    // 删除AA001表的记录
    orgDAO.deleteOrgByIdSL(new Integer(org.getId().toString()));
//    try {
//      // 删除单位版下的emp
//      List deleteEmpList = empDAODW.queryByEmpId_lg(null, new Integer(org
//          .getId().toString()));
//      if (!deleteEmpList.isEmpty()) {
//        for (int q = 0; q < deleteEmpList.size(); q++) {
//          Emp emp = (Emp) deleteEmpList.get(q);
//          Integer empid = emp.getEmpId();
//          // 判断是否存在相同的empid
//          boolean flag = empDAODW.getEmpidCount(empid, new Integer(id));
//          empDAODW.deleteEmpByIdSL(new Integer(emp.getId().toString()));
//          if (!flag) {
//            empDAODW.deleteEmpInfoByIdSL(emp.getEmpInfo().getId());
//          }
//        }
//      }
//      // 20071213删除对应的单位库中的单位信息
//      Org Orgdw = orgDAODW.queryById(new Integer(org.getId().toString()));
//      if (Orgdw != null)
//        orgDAODW.deleteOrgByIdSL(Orgdw);
//      // 删除da002中信息
//      OrgEdition orgEdition = orgEditionDAO.queryByOrgId_sy(new Integer(org
//          .getId().toString()));
//      if (orgEdition != null) {
//        orgEditionDAO.deleteOrgEdition_sy(orgEdition);
//      }
//      // 20071213
//      // 删除BA001表的记录
//      if (org.getOrgInfo().getId() != null
//          && !org.getOrgInfo().getId().equals("")) {
//        orgInfoDAO.deleteOrgInfoByIdSL(org.getOrgInfo().getId());
//        // 20071213删除对应的单位库中的单位信息
//        OrgInfo orgInfodw = orgInfoDAODW.queryById(org.getOrgInfo().getId());
//        if (orgInfodw != null)
//          orgInfoDAODW.deleteOrgInfoByIdSL(orgInfodw);
//        // 20071213
//      }
//    } 
//    catch (Exception e) {
//      e.printStackTrace();
//    }
    orgDAO.deleteOrgPre(Integer.parseInt(id));
    HafOperateLog hafOperateLog = new HafOperateLog();
    // 插入日志BA003
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
    hafOperateLog.setOpBizId(new Integer(org.getId().toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(org.getId().toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
  }

  /**
   * 职工开户查询列表
   */
  public List findEmployee(Pagination pagination) throws BusinessException {
    List orgs = null;
    Serializable id = (Serializable) pagination.getQueryCriterions().get("id");
    String name = (String) pagination.getQueryCriterions().get("name");
    String cardNumber = (String) pagination.getQueryCriterions().get(
        "cardNumber");
    Serializable orgId = (Serializable) pagination.getQueryCriterions().get(
        "orgId");
    // 20071213查看状态
    String status = autoInfoPickDAODW.findStatus(orgId.toString(), orgId
        .toString(), BusiConst.ORGBUSINESSTYPE_P);
    pagination.getQueryCriterions().put("istype", status);
    // 20071213
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    orgs = empDAO.empOpenQueryByCriterionsSL(orgId, id, name, cardNumber,
        orderBy, order, start, pageSize);

    int count = empDAO.empOpenQueryCountByCriterionsSL(orgId, id, name,
        cardNumber);
    pagination.setNrOfElements(count);
    return orgs;
  }

  /**
   * 职工开户查询列表
   */
  public EmpkhAF findEmployee(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    List list = null;
    EmpkhAF af = new EmpkhAF();
    Serializable id = (Serializable) pagination.getQueryCriterions().get("id");
    String name = (String) pagination.getQueryCriterions().get("name");
    String cardNumber = (String) pagination.getQueryCriterions().get(
        "cardNumber");
    Serializable orgId = (Serializable) pagination.getQueryCriterions().get(
        "orgId");
    // 20071213查看状态
    String istype = securityInfo.getIsOrgEdition() + "";
    if (istype.equals("2")) {
      String status = autoInfoPickDAO.findStatus(orgId.toString(), orgId
          .toString(), BusiConst.ORGBUSINESSTYPE_P);
      pagination.getQueryCriterions().put("istype", status);
    } else {
      String status = autoInfoPickDAODW.findStatus(orgId.toString(), orgId
          .toString(), BusiConst.ORGBUSINESSTYPE_P);
      pagination.getQueryCriterions().put("istype", status);
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    list = empDAO.empOpenQueryByCriterionsSL(orgId, id, name, cardNumber,
        orderBy, order, start, pageSize);
    af.setList(list);
    int count = empDAO.empOpenQueryCountByCriterionsSL(orgId, id, name,
        cardNumber);
    Object[] obj = empDAO.queryTotalInfoByCriterions(orgId, id, name, cardNumber);
    af.setSalaryBaseCount(new BigDecimal(obj[3].toString()).setScale(2)+"");
    af.setOrgpaySumCount(new BigDecimal(obj[1].toString()).setScale(2)+"");
    af.setEmppaySumCount(new BigDecimal(obj[2].toString()).setScale(2)+"");
    af.setSumCount(new BigDecimal(obj[4].toString()).setScale(2)+"");
    pagination.setNrOfElements(count);
    return af;
  }

  public List findEmployeeall(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    List orgs = null;
    Serializable id = (Serializable) pagination.getQueryCriterions().get("id");
    String name = (String) pagination.getQueryCriterions().get("name");
    String cardNumber = (String) pagination.getQueryCriterions().get(
        "cardNumber");
    Serializable orgId = (Serializable) pagination.getQueryCriterions().get(
        "orgId");
    // 20071213查看状态
    String istype = securityInfo.getIsOrgEdition() + "";
    if (istype.equals("2")) {
      String status = autoInfoPickDAO.findStatus(orgId.toString(), orgId
          .toString(), BusiConst.ORGBUSINESSTYPE_P);
      pagination.getQueryCriterions().put("istype", status);
    } else {
      String status = autoInfoPickDAODW.findStatus(orgId.toString(), orgId
          .toString(), BusiConst.ORGBUSINESSTYPE_P);
      pagination.getQueryCriterions().put("istype", status);
    }
    // 20071213
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();

    orgs = empDAO.empOpenQueryByCriterionsSL(orgId, id, name, cardNumber,
        orderBy, order, 0, 0);

    int count = empDAO.empOpenQueryCountByCriterionsSL(orgId, id, name,
        cardNumber);
    pagination.setNrOfElements(count);
    return orgs;
  }

  /**
   * 添加职工信息
   */
  public int saveEmployee(String orgId, Emp emp, String emppaymonth, int flag)
      throws Exception, BusinessException {
    flag = 0;
    String empname = (String) emp.getEmpInfo().getName();
    String empcardnum = (String) emp.getEmpInfo().getCardNum();
    int i1 = empDAO.queryEmployeeCountSL(empcardnum, orgId, empname);
    if (i1 != 0)
      throw new BusinessException("该单位已经存在该职工");
    int i2 = empDAO.queryEmployeeCountSL(empcardnum, null, empname);
    if (i2 != 0)
      flag = 1;
    return flag;
  }

  /**
   * 查询其他单位是否有该职工同信息记录
   */
  public List getEmpFromOthers(String orgid, Emp emp) throws Exception {
    List list = new ArrayList();
    String empname = emp.getEmpInfo().getName();
    String carfnum = emp.getEmpInfo().getCardNum();
    list = empDAO.getOtherOrgMessage_WL(orgid, empname, carfnum);
    return list;
  }

  /**
   * 添加职工具体信息
   */
  public void saveEmployeeInfo(String orgId, Emp emp, String emppaymonth,
      String empid, SecurityInfo securityInfo) throws Exception {
//    if (emp.getEmpAgentNum() != null) {
//      int count = empDAO.queryEmpAgentNumBySave(orgId, emp.getEmpAgentNum());
//      if (count > 0) {
//        throw new BusinessException("该职工代扣号已经存在!");
//      }
//    }
    // save职工信息
    emp.getEmpInfo().setOpendate(securityInfo.getUserInfo().getBizDate());
    // 判断职工的证件号码
    if (emp.getEmpInfo().getCardKind().toString().equals("0")
        && emp.getEmpInfo().getCardNum().length() == 15) {
      String num_to18 = empInfoDAO.queryCardNumTo18(emp.getEmpInfo()
          .getCardNum());
      emp.getEmpInfo().setStandbyCardNum(num_to18);
    } else {
      emp.getEmpInfo().setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
    }

    Org org = orgDAO.queryById(new Integer(orgId));
    // save Emp信息
    if (empid == null) {
      empInfoDAO.insert(emp.getEmpInfo());
      emp.setOrg(org);
      emp.setEmpInfo(emp.getEmpInfo());
      emp.setPayStatus(new BigDecimal(1));
      emp.setEmpPayMonth(emppaymonth);
      emp.setOrgPayMonth(emppaymonth);
      emp.setPreIntegral(new BigDecimal(0.00));
      emp.setPreBalance(new BigDecimal(0.00));
      emp.setCurIntegral(new BigDecimal(0.00));
      emp.setCurBalance(new BigDecimal(0.00));
      emp.setPreBalance(new BigDecimal(0.00));
      Integer empId = empDAO.makeEmpIdSL();

      emp.setEmpId(empId);

    } else {
      emp.setOrg(org);
      emp.setEmpInfo(emp.getEmpInfo());
      emp.setPayStatus(new BigDecimal(1));
      emp.setEmpPayMonth(emppaymonth);
      emp.setOrgPayMonth(emppaymonth);
      emp.setPreIntegral(new BigDecimal(0.00));
      emp.setPreBalance(new BigDecimal(0.00));
      emp.setCurIntegral(new BigDecimal(0.00));
      emp.setCurBalance(new BigDecimal(0.00));
      emp.setPreBalance(new BigDecimal(0.00));
      emp.setEmpInfo(empDAO.queryById(new Integer(empid)).getEmpInfo());
      emp.setEmpId(empDAO.queryById(new Integer(empid)).getEmpId());
    }
    empDAO.insert(emp);
    // 插入BA003日志
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(BusiLogConst.OP_MODE_OPEN_EMPOPEN + "");
    hafOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");
    hafOperateLog.setOpBizId(new Integer(orgId.toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(orgId.toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);

  }

  /**
   * 查询职工信息(non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS#findEmp(java.lang.Integer)
   */
  public Emp findEmp(Integer id) throws BusinessException {
    return empDAO.queryById(id);
  }

  /**
   * 修改职工信息
   */
  public boolean modifyEmployee(String id, Emp emp, SecurityInfo securityInfo)
      throws BusinessException {
    boolean flag = true;
    Emp empp = empDAO.queryById(new Integer(id));
    Org org = empp.getOrg();
    // 判断职工代扣号是否存在
//    if (emp.getEmpAgentNum() != null && !emp.getEmpAgentNum().equals("")) {
//      int count = empDAO.queryEmpAgentNum(org.getId().toString(), empp
//          .getEmpId().toString(), emp.getEmpAgentNum());
//      if (count > 0) {
//        throw new BusinessException("职工代扣号已经存在");
//      }
//    }

    String empp_Name = empp.getEmpInfo().getName();
    String empp_Card = empp.getEmpInfo().getCardNum();

    // 存放被修改的字段
    List list = new ArrayList();
    String[] column = null;
    String temp_old = "";
    String temp_new = "";

    // 判断是否修改的是自己的信息
    if (empp_Name.equals(emp.getEmpInfo().getName())
        & empp_Card.equals(emp.getEmpInfo().getCardNum())) {

      /** 判断被修改的字段 */
      // 职工代扣号
//      temp_old = empp.getEmpAgentNum();
//      temp_new = emp.getEmpAgentNum();
//
//      if (temp_old == null || temp_old.equals("")) {
//        temp_old = "无";
//      }
//      if (temp_new == null || temp_new.equals("")) {
//        temp_new = "无";
//      }
//      if (!temp_new.equals(temp_old)) {
//        column = new String[3];
//        column[0] = "职工代扣号";
//        column[1] = temp_old;
//        column[2] = temp_new;
//        list.add(column);
//      }
      // 出生日期
      temp_old = empp.getEmpInfo().getBirthday();
      temp_new = emp.getEmpInfo().getBirthday();

      if (temp_old == null || temp_old.equals("")) {
        temp_old = "无";
      }
      if (temp_new == null || temp_new.equals("")) {
        temp_new = "无";
      }
      if (!temp_new.equals(temp_old)) {
        column = new String[3];
        column[0] = "出生日期";
        column[1] = temp_old;
        column[2] = temp_new;
        list.add(column);
      }
      // 证件类型
      if (!empp.getEmpInfo().getCardKind().equals(
          emp.getEmpInfo().getCardKind())) {
        column = new String[3];
        column[0] = "证件类型";
        column[1] = empp.getEmpInfo().getCardKind().toString();
        column[2] = emp.getEmpInfo().getCardKind().toString();
        list.add(column);
      }
      // 性别
      if (!empp.getEmpInfo().getSex().equals(emp.getEmpInfo().getSex())) {
        column = new String[3];
        column[0] = "性别";
        column[1] = empp.getEmpInfo().getSex().toString();
        column[2] = emp.getEmpInfo().getSex().toString();
        list.add(column);
      }
      // 所在部门
      temp_old = empp.getEmpInfo().getDepartment();
      temp_new = emp.getEmpInfo().getDepartment();

      if (temp_old == null || temp_old.equals("")) {
        temp_old = "无";
      }
      if (temp_new == null || temp_new.equals("")) {
        temp_new = "无";
      }
      if (!temp_new.equals(temp_old)) {
        column = new String[3];
        column[0] = "所在部门";
        column[1] = temp_old;
        column[2] = temp_new;
        list.add(column);
      }
      // 职工月收入
      temp_old = empp.getEmpInfo().getMonthIncome().toString();
      temp_new = emp.getEmpInfo().getMonthIncome().toString();

      if (temp_old == null || temp_old.equals("")) {
        temp_old = "无";
      }
      if (temp_new == null || temp_new.equals("")) {
        temp_new = "无";
      }
      if (!temp_new.equals(temp_old)) {
        column = new String[3];
        column[0] = "职工月收入";
        column[1] = temp_old;
        column[2] = temp_new;
        list.add(column);
      }
      // 家庭电话
      temp_old = empp.getEmpInfo().getTel();
      temp_new = emp.getEmpInfo().getTel();

      if (temp_old == null || temp_old.equals("")) {
        temp_old = "无";
      }
      if (temp_new == null || temp_new.equals("")) {
        temp_new = "无";
      }
      if (!temp_new.equals(temp_old)) {
        column = new String[3];
        column[0] = "家庭电话";
        column[1] = temp_old;
        column[2] = temp_new;
        list.add(column);
      }
      // 工资基数
      temp_old = empp.getSalaryBase().toString();
      temp_new = emp.getSalaryBase().toString();

      if (temp_old == null || temp_old.equals("")) {
        temp_old = "无";
      }
      if (temp_new == null || temp_new.equals("")) {
        temp_new = "无";
      }
      if (!temp_new.equals(temp_old)) {
        column = new String[3];
        column[0] = "工资基数";
        column[1] = temp_old;
        column[2] = temp_new;
        list.add(column);
      }
      // 单位缴额
      temp_old = empp.getOrgPay().toString();
      temp_new = emp.getOrgPay().toString();

      if (temp_old == null || temp_old.equals("")) {
        temp_old = "无";
      }
      if (temp_new == null || temp_new.equals("")) {
        temp_new = "无";
      }
      if (!temp_new.equals(temp_old)) {
        column = new String[3];
        column[0] = "单位缴额";
        column[1] = temp_old;
        column[2] = temp_new;
        list.add(column);
      }
      // 职工缴额
      temp_old = empp.getEmpPay().toString();
      temp_new = emp.getEmpPay().toString();

      if (temp_old == null || temp_old.equals("")) {
        temp_old = "无";
      }
      if (temp_new == null || temp_new.equals("")) {
        temp_new = "无";
      }
      if (!temp_new.equals(temp_old)) {
        column = new String[3];
        column[0] = "职工缴额";
        column[1] = temp_old;
        column[2] = temp_new;
        list.add(column);
      }
      // 判断职工的证件号码
      if (emp.getEmpInfo().getCardKind().toString().equals("0")
          && emp.getEmpInfo().getCardNum().length() == 15) {
        String num_to18 = empInfoDAO.queryCardNumTo18(emp.getEmpInfo()
            .getCardNum());
        emp.getEmpInfo().setStandbyCardNum(num_to18);
      } else {
        emp.getEmpInfo().setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
      }
      /** 判断结束 */
      empDAO.update(id, emp, null);
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
      hafOperateLog.setOpBizId(new Integer(id.toString()));
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOrgId(new Integer(empp.getOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
    } else {
      int i1 = empDAO.queryEmployeeCountSL(emp.getEmpInfo().getCardNum(), null,
          emp.getEmpInfo().getName());
      // 说明系统中已有该身份证号
      // if (i1 != 0) {
      // flag = false;
      // }
      if (false) {
        flag = false;
      } else {
        /** 判断被修改的字段 */
        // 职工姓名
        temp_old = empp.getEmpInfo().getName();
        temp_new = emp.getEmpInfo().getName();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "职工姓名";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 证件类型
        if (!empp.getEmpInfo().getCardKind().equals(
            emp.getEmpInfo().getCardKind())) {
          column = new String[3];
          column[0] = "证件类型";
          column[1] = empp.getEmpInfo().getCardKind().toString();
          column[2] = emp.getEmpInfo().getCardKind().toString();
          list.add(column);
        }
        // 证件号码
        temp_old = empp.getEmpInfo().getCardNum();
        temp_new = emp.getEmpInfo().getCardNum();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "证件号码";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 职工月收入
        temp_old = empp.getEmpInfo().getMonthIncome().toString();
        temp_new = emp.getEmpInfo().getMonthIncome().toString();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "职工月收入";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 出生日期
        temp_old = empp.getEmpInfo().getBirthday();
        temp_new = emp.getEmpInfo().getBirthday();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "出生日期";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 性别
        if (!empp.getEmpInfo().getSex().equals(emp.getEmpInfo().getSex())) {
          column = new String[3];
          column[0] = "性别";
          column[1] = empp.getEmpInfo().getSex().toString();
          column[2] = emp.getEmpInfo().getSex().toString();
          list.add(column);
        }
        // 所在部门
        temp_old = empp.getEmpInfo().getDepartment();
        temp_new = emp.getEmpInfo().getDepartment();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "所在部门";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 家庭电话
        temp_old = empp.getEmpInfo().getTel();
        temp_new = emp.getEmpInfo().getTel();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "家庭电话";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 工资基数
        temp_old = empp.getSalaryBase().toString();
        temp_new = emp.getSalaryBase().toString();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "工资基数";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 单位缴额
        temp_old = empp.getOrgPay().toString();
        temp_new = emp.getOrgPay().toString();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "单位缴额";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 职工缴额
        temp_old = empp.getEmpPay().toString();
        temp_new = emp.getEmpPay().toString();

        if (temp_old == null || temp_old.equals("")) {
          temp_old = "无";
        }
        if (temp_new == null || temp_new.equals("")) {
          temp_new = "无";
        }
        if (!temp_new.equals(temp_old)) {
          column = new String[3];
          column[0] = "职工缴额";
          column[1] = temp_old;
          column[2] = temp_new;
          list.add(column);
        }
        // 判断职工的证件号码
        if (emp.getEmpInfo().getCardKind().toString().equals("0")
            && emp.getEmpInfo().getCardNum().length() == 15) {
          String num_to18 = empInfoDAO.queryCardNumTo18(emp.getEmpInfo()
              .getCardNum());
          emp.getEmpInfo().setStandbyCardNum(num_to18);
        } else {
          emp.getEmpInfo().setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
        }
        /** 判断结束 */
        empDAO.update(id, emp, null);

        // 插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
        hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
        hafOperateLog.setOpBizId(new Integer(id.toString()));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(empp.getOrg().getId().toString()));
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      }

    }

    // 插入AA004表
    for (int i = 0; i < list.size(); i++) {
      BaseGhgInfo baseGhgInfo = new BaseGhgInfo();
      String[] column1 = (String[]) list.get(i);
      baseGhgInfo.setChgColumn(column1[0]);
      baseGhgInfo.setChgBefInfo(column1[1]);
      baseGhgInfo.setChgEndInfo(column1[2]);
      baseGhgInfo.setOpTime(new Date());
      baseGhgInfo.setOperator(securityInfo.getUserName());
      // baseGhgInfo.setEmp(empp);
      baseGhgInfo.setEmpId(empp.getEmpId());
      baseGhgInfo.setOrg(org);
      baseGhgInfoDAO.insert(baseGhgInfo);
    }
    return flag;
  }

  /**
   * 单选删除职工信息
   */
  public void removeEmp(String id, SecurityInfo securityInfo)
      throws BusinessException {

    Emp emp = empDAO.queryById(new Integer(id));
    Integer empid = emp.getEmpId();
    // 判断其他单位是否存在这个职工

    boolean flag = empDAO.getEmpidCount(empid);
    // AA002
    empDAO.deleteEmpByIdSL(new Integer(emp.getId().toString()));
    // BA002
    if (!flag) {
      empInfoDAO.deleteEmpInfoByIdSL(emp.getEmpInfo().getId());
    }
    // //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
    hafOperateLog.setOpBizId(new Integer(id.toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(emp.getOrg().getId().toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);

  }

  /**
   * 单选删除职工信息
   */
  public void removeEmp(String id, SecurityInfo securityInfo, String orgId)
      throws BusinessException {

    Emp emp = empDAO.queryById(new Integer(id));
    Integer empid = emp.getEmpId();
    // 判断其他单位是否存在这个职工

    boolean flag = empDAO.getEmpidCount(empid);
    System.out.println(flag);
    // AA002
    empDAO.deleteEmpByIdSL(new Integer(emp.getId().toString()));
    // BA002
    if (!flag) {
      empInfoDAO.deleteEmpInfoByIdSL(emp.getEmpInfo().getId());
    }
    // //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
    hafOperateLog.setOpBizId(new Integer(id.toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(emp.getOrg().getId().toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
    List emplist = empDAO.getExportData(new Integer(orgId).intValue());
    if (emplist.isEmpty()) {
      String istype = securityInfo.getIsOrgEdition() + "";
      if (istype.equals("1")) {
        // 判断DA001状态
        String status = autoInfoPickDAODW.findStatus(orgId, orgId,
            BusiConst.ORGBUSINESSTYPE_P);
        if (status.equals("0")) {
          throw new BusinessException("请先撤销提交数据!");
        }
        if (status.equals("1")) {
          throw new BusinessException("数据已经提取不能进行删除!");
        }
      }
      try {
        autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, "", "",
            orgId, orgId, BusiConst.ORGBUSINESSTYPE_P);
      } catch (Exception e) {
        throw new BusinessException("数据有问题");
      }
    }
  }

  /**
   * 全选删除
   */

  public boolean removeEmpAll(String orgId, SecurityInfo securityInfo)
      throws BusinessException {
    // 查询出AA002,BA003,BA002中的职工信息.返回Emp对象
    List list = empDAO.queryEmpByOrgIdSL(orgId);
    if (list.size() > 0) {
      Emp emp = null;
      // 说明该单位在BA003中有职工
      for (int i = 0; i < list.size(); i++) {
        emp = (Emp) list.get(i);
        Integer empid = emp.getEmpId();
        // 判断是否存在相同的empid
        boolean flag = empDAO.getEmpidCount(empid);
        empDAO.deleteEmpByIdSL(new Integer(emp.getId().toString()));
        if (!flag) {
          empInfoDAO.deleteEmpInfoByIdSL(emp.getEmpInfo().getId());
        }
      }
      HafOperateLog hafOperateLog = new HafOperateLog();
      // 插入日志BA003
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETEALL);
      hafOperateLog.setOpBizId(new Integer(orgId.toString()));
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOrgId(new Integer(orgId.toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
      // 20071213单位版
      String istype = securityInfo.getIsOrgEdition() + "";
      if (istype.equals("2")) {
        try {
          autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, "", "",
              orgId, orgId, BusiConst.ORGBUSINESSTYPE_P);
        } catch (Exception e) {
          throw new BusinessException("数据有问题");
        }
      }
      if (istype.equals("1")) {
        // 判断DA001状态
        String status = autoInfoPickDAODW.findStatus(orgId, orgId,
            BusiConst.ORGBUSINESSTYPE_P);
        if (status.equals("0")) {
          throw new BusinessException("请先撤销提交数据!");
        }
        if (status.equals("1")) {
          throw new BusinessException("数据已经提取不能进行删除!");
        }
      }
      return true;
    } else {
      return false;
    }

  }

  /**
   * 开户完成
   */
  public void openOver(String id, SecurityInfo securityInfo) throws Exception {
    OrgChgLog orgChgLog = new OrgChgLog();
    // Org orgChgLogorg = orgDAO.queryById(new Integer(id));
    Org org = orgDAO.queryById(new Integer(id));
    // 付云峰修改：开户状态在数据库中保存为'2'。
    org.getOrgInfo().setOpenstatus("2");
    // 更新aa002.pay_oldyear
    empDAO.updatePayOldYearOfAA002(org.getId().toString());
    orgChgLog.setOrg(org);
    if (org.getId() != null && !org.getId().equals("")) {
      orgChgLog.getOrg().setId(org.getId());
    }
    orgChgLog.setChgType("A");
    orgChgLog.setOpTime(new Date());
    orgChgLog.setOperator(securityInfo.getUserName());
    orgChgLogDAO.insert(orgChgLog);

    String BizDate = securityInfo.getUserInfo().getBizDate();
    // 初缴年月
    String sDate = org.getFirstPayMonth();
    // 会计日期
    String eDate = BizDate.substring(0, 6);

    // 计算截止日期
    String str = eDate.substring(4, 6);
    Integer strI = new Integer(str);
    if (strI.intValue() <= 6) {
      eDate = eDate.substring(0, 4) + "06";
    } else {
      int eYearI = new Integer(eDate.substring(0, 4)).intValue();
      int eYear = eYearI + 1;
      eDate = eYear + "06";
    }

    int i = BusiTools.monthInterval(sDate, eDate);
    for (int j = 0; j <= i; j++) {
      String month = BusiTools.addMonth(sDate, j);
      SearchLackInfo searchLackInfo = new SearchLackInfo();
      searchLackInfo.setYearMonth(month);
      searchLackInfo.setOrg(org);
      if (org.getOrgpaySumCount() == null) {
        searchLackInfo.setOegPay(new BigDecimal(0.00));
      } else {
        searchLackInfo.setOegPay(org.getOrgpaySumCount());
      }
      if (org.getEmppaySumCount() == null) {
        searchLackInfo.setEmpPay(new BigDecimal(0.00));
      } else {
        searchLackInfo.setEmpPay(org.getEmppaySumCount());
      }
      searchLackInfo.setEmpPayReal(new BigDecimal(0.00));
      searchLackInfo.setOrgPayReal(new BigDecimal(0.00));
      searchLackInfoDAO.insert(searchLackInfo);
    }

    OrgOpenAccountBizActivityLog log = new OrgOpenAccountBizActivityLog();
    log.setBizid(new Integer(id));
    log.setAction(new Integer(3));
    log.setOpTime(new java.util.Date());
    log.setOperator(securityInfo.getUserName());
    orgOpenAccountBizActivityLogDAO.insert(log);
    HafOperateLog hafOperateLog = new HafOperateLog();
    // 插入BA003
    if (id != null && !id.equals("")) {
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_MAINTAIN);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_OPENING);
      hafOperateLog.setOpBizId(new Integer(id.toString()));

      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOrgId(new Integer(id.toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());

      hafOperateLogDAO.insert(hafOperateLog);
    }
    // 判断是否存在单位版20071213
    OrgEdition orgEdition = orgEditionDAO.queryByOrgId_sy(new Integer(org
        .getId().toString()));
    if (orgEdition != null) {
      // 删除单位版下的emp
      List deleteEmpList = empDAODW.queryByEmpId_lg(null, new Integer(org
          .getId().toString()));
      if (!deleteEmpList.isEmpty()) {
        for (int q = 0; q < deleteEmpList.size(); q++) {
          Emp emp = (Emp) deleteEmpList.get(q);
          Integer empid = emp.getEmpId();
          // 判断是否存在相同的empid
          boolean flag = empDAODW.getEmpidCount(empid, new Integer(org.getId()
              .toString()));
          empDAODW.deleteEmpByIdSL(new Integer(emp.getId().toString()));
          if (!flag) {
            empDAODW.deleteEmpInfoByIdSL(emp.getEmpInfo().getId());
          }
        }
      }
      List emplist = empDAO.queryByCriterionsWZQ(null, org.getId().toString());
      if (!emplist.isEmpty()) {
        for (int j = 0; j < emplist.size(); j++) {
          Emp emp = (Emp) emplist.get(j);
          EmpInfo empInfo = empInfoDAO.queryById(new Integer(emp.getEmpInfo()
              .getId().toString()));
          // -------------------//
          EmpInfo empInfo1 = new EmpInfo();
          empInfo1.setCardKind(empInfo.getCardKind());
          empInfo1.setCardNum(empInfo.getCardNum());
          empInfo1.setOpendate(empInfo.getOpendate());
          empInfo1.setName(empInfo.getName());
          empInfo1.setBirthday(empInfo.getBirthday());
          empInfo1.setSex(empInfo.getSex());
          empInfo1.setMonthIncome(empInfo.getMonthIncome());
          empInfo1.setTel(empInfo.getTel());
          empInfo1.setDepartment(empInfo.getDepartment());
          String empInfoId = (String) empDAODW.insert(empInfo1);
          EmpInfo empInfo2 = empDAODW.queryEmpInfoById(new Integer(empInfoId));
          Emp emp1 = new Emp();
          emp1.setEmpInfo(empInfo2);
          emp1.setEmpId(emp.getEmpId());
          emp1.setSalaryBase(emp.getSalaryBase());
          emp1.setOrgPay(emp.getOrgPay());
          emp1.setEmpPay(emp.getEmpPay());
          emp1.setOrgPayMonth(emp.getOrgPayMonth());
          emp1.setEmpPayMonth(emp.getEmpPayMonth());
          emp1.setPreIntegral(emp.getPreIntegral());
          emp1.setCurIntegral(emp.getCurIntegral());
          emp1.setPreBalance(emp.getPreBalance());
          emp1.setCurBalance(emp.getCurBalance());
          emp1.setPayStatus(emp.getPayStatus());
          emp1.setOrg(org);
          empDAODW.insert(emp1);
        }
      }
      // 单位库开户状态正常
      Org orgDW = orgDAODW.queryById(new Integer(id));
      OrgInfo orgInfo = orgInfoDAODW.queryById(new Integer(orgDW.getOrgInfo()
          .getId().toString()));
      orgInfo.setOpenstatus(BusiConst.ORGSTATE_NORMAL + "");
      // 单位库中插入305
      for (int j = 0; j <= i; j++) {
        String month = BusiTools.addMonth(sDate, j);
        SearchLackInfo searchLackInfo = new SearchLackInfo();
        searchLackInfo.setYearMonth(month);
        searchLackInfo.setOrg(org);
        if (org.getOrgpaySumCount() == null) {
          searchLackInfo.setOegPay(new BigDecimal(0.00));
        } else {
          searchLackInfo.setOegPay(org.getOrgpaySumCount());
        }
        if (org.getEmppaySumCount() == null) {
          searchLackInfo.setEmpPay(new BigDecimal(0.00));
        } else {
          searchLackInfo.setEmpPay(org.getEmppaySumCount());
        }
        searchLackInfo.setEmpPayReal(new BigDecimal(0.00));
        searchLackInfo.setOrgPayReal(new BigDecimal(0.00));
        searchLackInfoDAODW.insert(searchLackInfo);
      }
    }
  }

  /**
   * 批量导入
   */
  public List modifyOrgOpenBatch(List empOpenImpTitle, List empOpenImpContent,

  String orgId, SecurityInfo securityInfo) throws Exception, BusinessException {

    List list = new ArrayList();
    Org org = orgDAO.queryById(new Integer(orgId));
    Object[] obj = new Object[2];
    HafOperateLog hafOperateLog = new HafOperateLog();
    EmpOpenImpTitleDTO empOpenImpTitleDTO = (EmpOpenImpTitleDTO) empOpenImpTitle
        .get(1);
    String impOrgID = empOpenImpTitleDTO.getOrgunitcode();
    if (impOrgID == null || impOrgID.equals(""))
      throw new BusinessException("单位编号不能为null!");
    else {
      int orgID = Integer.parseInt(impOrgID);
      impOrgID = orgID + "";
    }
    if (!impOrgID.equals(orgId)) {
      throw new BusinessException("单位编号不存在!");
    } else {
      for (int i = 1; i < empOpenImpContent.size(); ++i) {// 获得所有的DTO的值
                                                          // 把值给String
                                                          // 如果是按照焦虑就是10个值
                                                          // 如果按照比例就是8个值
        EmpOpenImpContentDTO empOpenImpContentDTO = (EmpOpenImpContentDTO) empOpenImpContent
            .get(i);
        String empname = "";
        if (empOpenImpContentDTO.getEmpname() != null) {
          empname = empOpenImpContentDTO.getEmpname().trim();
        }
        String cardnum = "";
        if (empOpenImpContentDTO.getCardnum() != null) {
          cardnum = empOpenImpContentDTO.getCardnum().trim();
        }

        String cardkind = "";
        if (empOpenImpContentDTO.getCardkind() != null) {
          cardkind = empOpenImpContentDTO.getCardkind().trim();
        }
        String dept = "";
        if (empOpenImpContentDTO.getDept() != null) {
          dept = empOpenImpContentDTO.getDept().trim();
        }
        String tel = "";
        if (empOpenImpContentDTO.getTel() != null) {
          tel = empOpenImpContentDTO.getTel().trim();
        }
        String mobileTle = "";
        if (empOpenImpContentDTO.getMobileTle() != null) {
          mobileTle = empOpenImpContentDTO.getMobileTle().trim();
        }
        String monthIncome = empOpenImpContentDTO.getMonthIncome();
        String salarybase = empOpenImpContentDTO.getSalarybase();
        String orgpay = "";
        String emppay = "";
        if (org.getPayMode().intValue() == 2) {// 如果从Excel导入按缴率导入进来数据的时候
          if (empOpenImpContentDTO.getOrgpay() != null) {
            orgpay = empOpenImpContentDTO.getOrgpay().trim();
          }
          if (empOpenImpContentDTO.getEmppay() != null) {
            emppay = empOpenImpContentDTO.getEmppay().trim();
          }

        }
        if (empname == null || empname.length() <= 0 || empname.length() > 20) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "职工姓名的格式不正确");
        }
        if (cardnum == null || cardnum.length() <= 0 || cardnum.length() > 20) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "证件号码的格式不正确");
        }
        if (cardkind == null || cardkind.length() <= 0
            || new Integer(cardkind).intValue() > 9
            || new Integer(cardkind).intValue() < 0) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "证件类型格式不确");
        }
//        if (cardkind.equals("0")) {
//          // 判断在导入列表中是否存在身份证号相同，但是姓名不同的职工。
//          for (int j = 1; j < empOpenImpContent.size(); j++) {
//            EmpOpenImpContentDTO temp_empOpenImpContentDTO = (EmpOpenImpContentDTO) empOpenImpContent
//                .get(j);
//            String temp_cardNum = temp_empOpenImpContentDTO.getCardnum();
//            if (i != j) {
//              // 如果是18位的身份证号，变成15位与其比较
////              if (temp_cardNum.length() == 18) {
////                String temp_cardNum15 = temp_cardNum.substring(0, 6)
////                    + temp_cardNum.substring(8, 17);
////                if (cardnum.equals(temp_cardNum15)) {
////                  throw new BusinessException("第" + (i + 4) + "行记录与" + (j + 4)
////                      + "行记录的身份证号相同！");
////                }
////              }
////              if (cardnum.equals(temp_cardNum)) {
////                throw new BusinessException("第" + (i + 4) + "行记录与" + (j + 4)
////                    + "行记录的身份证号相同！");
////              }
//            }
//          }
//        }
        try {
          Integer.parseInt(cardkind);
        } catch (Exception s) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "证件类型必须为整数");
        }
        if (dept.trim().length() > 20) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "所在部门录入长度过长");
        }

        if (tel.trim().length() > 20) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "家庭电话录入长度过大");
        }
        if (mobileTle.trim().length() > 20) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "移动电话录入长度过大");
        }
        if (monthIncome != null && !monthIncome.trim().equals("")) {
          if (monthIncome.trim().length() > 18) {
            throw new BusinessException("第" + (i + 4) + "行记录" + "月收入长度过大");
          }
          try {
            Double.parseDouble(monthIncome.trim());
          } catch (Exception s) {
            throw new BusinessException("第" + (i + 4) + "行记录" + "月收入格式错误");
          }
        }

        if (salarybase == null || salarybase.trim().length() <= 0
            || salarybase.trim().length() > 18) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "工资基数录入长度不正确");
        }
        try {
          Double.parseDouble(salarybase.trim());
        } catch (Exception s) {
          throw new BusinessException("第" + (i + 4) + "行记录" + "工资基数格式错误");
        }
        if (empInfoDAO.getEmpInfo(cardnum, empname) != null) {
          list.add(empOpenImpContentDTO);
          continue;
          // throw new
          // BusinessException("第"+(i+4)+"行记录"+"导入职工姓名为"+empname+"的职工已经存在");
        }

        if (cardkind.equals("0")) {// 验证身份证的方法
          if (cardnum.length() != 15 && cardnum.length() != 18) {
            throw new BusinessException("第" + (i + 4) + "行记录"
                + "身份证位数必须是15或18位:");
          }
          if (cardnum.length() == 15) {// 身份证如果是15位的时候
            String month = cardnum.substring(8, 10);
            String day = cardnum.substring(10, 12);
            String sex = cardnum.substring(14, 15);
            if (Integer.parseInt(month) > 12)
              throw new BusinessException("第" + (i + 4) + "行记录"
                  + "身份证的月份必须在1-12之间");
            if (Integer.parseInt(day) > 31)
              throw new BusinessException("第" + (i + 4) + "行记录"
                  + "身份证的日期必须在1-31之间");
            // if(Integer.parseInt(sex)!=1 &&
            // Integer.parseInt(sex)!=2)//男的是奇数,女的是偶数
            // throw new BusinessException("15位身份证的性别出现错误");
            String birthday = "19" + cardnum.substring(6, 8) + month + day;
            obj[0] = birthday;
            obj[1] = sex;
          }
          if (cardnum.length() == 18) {
            String month = cardnum.substring(10, 12);
            String day = cardnum.substring(12, 14);
            String sex = cardnum.substring(16, 17);
            if (Integer.parseInt(month) > 12)
              throw new BusinessException("第" + (i + 4) + "行记录"
                  + "身份证的月份必须在1-12之间");
            if (Integer.parseInt(day) > 31)
              throw new BusinessException("第" + (i + 4) + "行记录"
                  + "身份证的日期必须在1-31之间");
            // if(Integer.parseInt(sex)!=1 &&
            // Integer.parseInt(sex)!=2)//男的是奇数,女的是偶数
            // throw new BusinessException("18位身份证的性别出现错误");
            String birthday = cardnum.substring(6, 10) + month + day;
            obj[0] = birthday;
            obj[1] = sex;
          }
        }
        // 判断是否存在姓名不同但是身份证号相同的职工
//       List temp_list = empDAO.queryEmpInfoByCardNum_FYF(empname, cardnum);
//        if (temp_list.size() > 0) {
//          throw new BusinessException("第" + (i + 4) + "行记录" + "已经存在证件号相同的职工");
//        }
        // 判断职工输入数据
        org = orgDAO.queryById(new Integer(orgId));
        Emp emp = new Emp();// update的时候可以注释掉
        if (org.getPayMode().intValue() == 2) {// 缴额插入值
          if (orgpay == null || orgpay.length() <= 0 || orgpay.length() > 18) {
            throw new BusinessException("第" + (i + 4) + "行记录" + "单位缴额长度不正确");
          }
          try {
            Double.parseDouble(orgpay);
          } catch (Exception s) {
            throw new BusinessException("第" + (i + 4) + "行记录" + "单位缴额格式不正确");
          }
          if (emppay == null || emppay.length() <= 0 || emppay.length() > 18) {
            throw new BusinessException("第" + (i + 4) + "行记录" + "职工缴额长度不正确");
          }
          try {
            Double.parseDouble(emppay);
          } catch (Exception s) {
            throw new BusinessException("第" + (i + 4) + "行记录" + "职工缴额格式不正确");
          }
          emp.setOrgPay(new BigDecimal(orgpay));
          emp.setEmpPay(new BigDecimal(emppay));
        } else {
          String salaryBasels = empOpenImpContentDTO.getSalarybase().toString();// 获得基本工资
          String orgrate = org.getOrgRate().toString();// 获得单位缴率
          String emprate = org.getEmpRate().toString();// 获得职工缴率
          int payprecision = org.getPayPrecision().intValue();// 获得缴存精度-->
          ArithmeticInterface arithmeticInterface = ArithmeticFactory
              .getArithmetic().getArithmeticDAO(payprecision);// 获得是按啥进啥的接口
          BigDecimal orgPayget = arithmeticInterface.getPay(new BigDecimal(
              salaryBasels), new BigDecimal(orgrate));// 传递基本工资和缴率得出来缴额
          BigDecimal empPayget = arithmeticInterface.getPay(new BigDecimal(
              salaryBasels), new BigDecimal(emprate));
          emp.setOrgPay(orgPayget);
          emp.setEmpPay(empPayget);
        }
        EmpInfo empinfo = new EmpInfo();
        empinfo.setCardKind(new BigDecimal(cardkind));
        if (cardkind.equals("0")) {
          if (cardnum.length() == 15 || cardnum.length() == 18) {
            empinfo.setBirthday(obj[0].toString());
            int numberSex = Integer.parseInt(obj[1].toString());
            if (numberSex % 2 == 0)
              empinfo.setSex(new BigDecimal(2));
            else
              empinfo.setSex(new BigDecimal(1));
          }
        } else {
          empinfo.setBirthday("19000520");
          empinfo.setSex(new BigDecimal("" + BusiConst.SEX_MALE));
        }
        empinfo.setCardNum(cardnum);
        empinfo.setDepartment(dept);
        empinfo.setMobileTle(mobileTle);
        if (monthIncome != null && !monthIncome.trim().equals("")) {
          empinfo.setMonthIncome(new BigDecimal(monthIncome.trim()));
        } else {
          empinfo.setMonthIncome(new BigDecimal(0));
        }
        empinfo.setOpendate(securityInfo.getUserInfo().getBizDate());
        empinfo.setName(empname);
        empinfo.setTel(tel);
        Serializable empinfoid = empInfoDAO.insert(empinfo);
        emp.setEmpInfo(empinfo);
        emp.setOrg(org);
        emp.setEmpPayMonth(org.getEmpPayMonth());
        emp.setOrgPayMonth(org.getOrgPayMonth());
        emp.setPayStatus(new BigDecimal(1));
        Integer empId = empDAO.makeEmpIdSL();
        emp.setSalaryBase(new BigDecimal(salarybase.trim()));
        emp.setCurBalance(new BigDecimal(0));
        emp.setCurIntegral(new BigDecimal(0));
        emp.setPreBalance(new BigDecimal(0));
        emp.setPreIntegral(new BigDecimal(0));
        emp.setEmpId(empId);
        // 判断职工的证件号码
        if (emp.getEmpInfo().getCardKind().toString().equals("0")
            && emp.getEmpInfo().getCardNum().length() == 15) {
          String num_to18 = empInfoDAO.queryCardNumTo18(emp.getEmpInfo()
              .getCardNum());
          emp.getEmpInfo().setStandbyCardNum(num_to18);
        } else {
          emp.getEmpInfo().setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
        }
        empDAO.insert(emp);// update的时候可以注释掉
      }
    }
    // //插入BA003 --->插入BA003OK
    if (org.getId() != null && !org.getId().equals("")) {
      EmpOpenImpTitleDTO tempEmpOpenImpTitleDTO = (EmpOpenImpTitleDTO) empOpenImpTitle
          .get(0);
      if (!tempEmpOpenImpTitleDTO.getOrgunitcode().equals("")) {
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
        hafOperateLog.setOpButton("" + BusiLogConst.BIZBLOG_ACTION_IMP);
        hafOperateLog.setOpBizId(new Integer(org.getId().toString()));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(org.getId().toString()));
        hafOperateLog.setOpTime(new Date(new Date().getTime()));
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      }
    }
    return list;
  }

  public List queryEmpListByOrgid(String orgId) throws BusinessException {
    List list = empDAO.queryEmpByOrgIdSL(orgId);
    return list;

  }

  public void ExpInsert(String orgid, SecurityInfo securityInfo)
      throws BusinessException {
    HafOperateLog hafOperateLog = new HafOperateLog();
    // 插入BA003
    if (orgid != null && !orgid.equals("")) {
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_EXP);
      hafOperateLog.setOpBizId(new Integer(orgid.toString()));
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOrgId(new Integer(orgid.toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
    }
  }

  /**
   * 得到办事处下的归集银行
   * 
   * @param office
   * @return CollBankList
   */
  public List queryCollBank(String office) {
    List collBankList = collBankDAO.getOfficeCollBankList(office);
    return collBankList;
  }

  public boolean validateOpenStatus(String id) {
    boolean flag = false;
    BizActivityLog bizActivityLog = bizActivityLogDAO.queryByBizId(id,
        new Integer(3), "P");
    if (bizActivityLog == null) {
      flag = true;
    } else {
      flag = false;
    }
    return flag;
  }

  /**
   * 查询单位中是否存在该员工
   * 
   * @param orgDAO
   */
  public boolean queryEmpCount(String orgID, String empID) {
    boolean flag = true;
    Integer id = empDAO.queryById(new Integer(empID)).getEmpId();
    Emp emp = empDAO.getChgPersonEmp_WL(orgID, id.toString());
    if (emp == null) {
      flag = false;
    } else {
      flag = true;
    }
    return flag;
  }

  public List findOrgAgentList(SecurityInfo securityInfo) throws Exception {
    List list = orgDAO.queryOrgAgentlist(securityInfo);
    return list;
  }

  public List saveOrgAgentList(List orgAgentImportList,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    for (int i = 1; i < orgAgentImportList.size(); i++) {
      OrgAgentImportDTO orgAgentImportDTO = (OrgAgentImportDTO) orgAgentImportList
          .get(i);
      if (orgAgentImportDTO.getOrgId() == null
          && orgAgentImportDTO.getOrgId().equals("")) {
        throw new BusinessException("第" + (i + 1) + "行记录" + "单位编号不能为空");
      } else {
        // 查找是否有对应的单位编号
        int count = orgDAO.queryOrgId(orgAgentImportDTO.getOrgId(),
            securityInfo);
        if (count <= 0) {
          throw new BusinessException("第" + (i + 1) + "行记录" + "单位编号不存在");
        }
      }
      // 判断列表中是否存在相同的代扣号
      for (int j = 1; j < orgAgentImportList.size(); j++) {
        if (i != j) {
          OrgAgentImportDTO temp_orgAgentImportDTO = (OrgAgentImportDTO) orgAgentImportList
              .get(j);
          if (temp_orgAgentImportDTO.getOrgAgentNum() != null
              && !temp_orgAgentImportDTO.getOrgAgentNum().equals("")) {
            if (temp_orgAgentImportDTO.getOrgAgentNum().equals(
                orgAgentImportDTO.getOrgAgentNum())) {
              throw new BusinessException("第" + (i + 1) + "行记录" + "的代扣号与" + "第"
                  + (j + 1) + "行记录" + "的代扣号相同");
            }
          }
        }
      }
      if (orgAgentImportDTO.getOrgAgentNum() != null
          && !orgAgentImportDTO.getOrgAgentNum().equals("")) {
        // 查询是否有与数据库中相同的代扣号
        int count1 = orgDAO.queryOrgAgentNum(
            orgAgentImportDTO.getOrgAgentNum(), orgAgentImportDTO.getOrgId(),
            securityInfo);

        if (count1 > 0) {
          throw new BusinessException("第" + (i + 1) + "行记录" + "的代扣号在数据库中已存在");
        }
        // 判断代扣号格式
        if (orgAgentImportDTO.getOrgAgentNum().length() > 20) {
          throw new BusinessException("第" + (i + 1) + "行记录" + "代扣号长度不正确");
        }
      }
      orgDAO.updateOrgAgentNum(orgAgentImportDTO.getOrgId(), orgAgentImportDTO
          .getOrgAgentNum());
    }
    return orgAgentImportList;
  }

  public List findEmpAgentList(String orgId) throws Exception {
  //  List list = empDAO.queryEmpAgentExpList(orgId);
    List list = empDAO.queryEmpAgentExpList_wuht(orgId);
    
    return list;
  }

  public List saveEmpAgentList(List empAgentImportTitleList,
      List empAgentImportList, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    String orgId = "";
    Object[] obj = new Object[2];
    // 得到单位
    for (int i = 1; i < empAgentImportTitleList.size(); i++) {
      EmpAgentImportTitleDTO empAgentImportTitleDTO = (EmpAgentImportTitleDTO) empAgentImportTitleList
          .get(i);
      orgId = empAgentImportTitleDTO.getOrgId();
    }
    // 判断单位编号
    if (orgId == null && orgId.equals("")) {
      throw new BusinessException("单位编号不能为空");
    } else {
      // 查找是否有对应的单位编号
      int count = orgDAO.queryOrgId(orgId, securityInfo);
      if (count <= 0) {
        throw new BusinessException("单位编号不存在");
      }
    }
    // 得到列表内容
    for (int i = 1; i < empAgentImportList.size(); i++) {
      EmpAgentImportDTO empAgentImportDTO = (EmpAgentImportDTO) empAgentImportList
          .get(i);
      String empId = empAgentImportDTO.getEmpId();
      //String empAgentNum = empAgentImportDTO.getEmpAgentNum();

      String empName = empAgentImportDTO.getEmpName();
      String cardNum = empAgentImportDTO.getCardNum();
      String sex = empAgentImportDTO.getSex();
      String birthday = empAgentImportDTO.getBirthday();
      if(cardNum.length()==18){
        
        birthday= cardNum.substring(6,14);
     //   System.out.println("18--birthday="+birthday);
        sex=""+Integer.parseInt(cardNum.substring(16,17).toString())%2;
       // System.out.println("18--sex="+cardnum.substring(16,17).toString());
        if (sex.equals("0")){
          sex="2";
        }
     //   System.out.println("18--sex="+sex);
      }else{
        birthday="19"+cardNum.substring(6,12);
     //   System.out.println("15--birthday="+birthday);
        sex=""+Integer.parseInt(cardNum.substring(14,15).toString())%2;
     //   System.out.println("15--sex="+cardnum.substring(14,15).toString());
        if (sex.equals("0")){
          sex="2";
        }
    //    System.out.println("15--sex="+sex);
      }
      String cardKind = empAgentImportDTO.getCardKind();
      String tel = empAgentImportDTO.getTel();
      String mobileTle = empAgentImportDTO.getMobileTle();
      String monthIncome = empAgentImportDTO.getMonthIncome();
      String department = empAgentImportDTO.getDepartment();
      // 判断职工编号
      if (empId == null || empId.equals("")) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "职工编号不能为空");
      }
      // 判断是否有这个职工
      int count1 = empDAO.queryEmpbyEmpId_FYF(empId, orgId);
      if (count1 < 1) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "职工编号不存在");
      }
      // 判断在列表中是否有重复的职工代扣号,相同的证件号码
      for (int j = 1; j < empAgentImportList.size(); j++) {
        EmpAgentImportDTO empAgentImportDTO1 = (EmpAgentImportDTO) empAgentImportList
            .get(j);
        if (i != j) {
          // 判断证件号
          // if (empAgentImportDTO1.getCardKind().equals("0")) {
          // if(empAgentImportDTO1.getCardNum().equals(empAgentImportDTO.getCardNum())){
          // throw new
          // BusinessException("第"+(i+3)+"行记录"+"的证件号与"+"第"+(j+3)+"行记录"+"的证件号相同");
          // }
          // String temp_cardNum = empAgentImportDTO1.getCardNum();
          // // 如果为18位的身份证号码则变回15位判断
          // if (temp_cardNum.length()==18) {
          // String temp_cardNum15 = temp_cardNum.substring(0,
          // 6)+temp_cardNum.substring(8, 17);
          // if (temp_cardNum15.equals(empAgentImportDTO.getCardNum())) {
          // throw new
          // BusinessException("第"+(i+3)+"行记录"+"的证件号与"+"第"+(j+3)+"行记录"+"的证件号相同");
          // }
          // }
          // }
          // 职工代扣号
          if (empAgentImportDTO.getEmpAgentNum() != null
              && !empAgentImportDTO.getEmpAgentNum().equals("")) {
            if (empAgentImportDTO.getEmpAgentNum().equals(
                empAgentImportDTO1.getEmpAgentNum())) {
              throw new BusinessException("第" + (i + 3) + "行记录" + "的代扣号与" + "第"
                  + (j + 3) + "行记录" + "的代扣号相同");
            }
          }
        }
      }
      // 判断该单位下是否已经存在这个代扣号
//      if (empAgentImportDTO.getEmpAgentNum() != null
//          && !empAgentImportDTO.getEmpAgentNum().equals("")) {
//        int count = empDAO.queryEmpAgentNum(orgId, empId, empAgentNum);
//        if (count > 0) {
//          throw new BusinessException("第" + (i + 3) + "行记录" + "的代扣号在数据库中已经存在");
//        }
//        if (empAgentNum.length() > 20) {
//          throw new BusinessException("第" + (i + 3) + "行记录" + "职工代扣号长度不正确");
//        }
//      }
//      int count = empDAO.queryEmpAgentNum(orgId, empId, empAgentNum);
//      if (count > 0) {
//        throw new BusinessException("第" + (i + 3) + "行记录" + "的代扣号在数据库中已经存在");
//      }

      // 判断其他条件
      if (empName == null || empName.length() <= 0 || empName.length() > 20) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "职工姓名的格式不正确");
      }
      if (cardNum == null || cardNum.length() <= 0 || cardNum.length() > 20) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "证件号码的格式不正确");
      }
      if (cardKind == null || cardKind.length() <= 0
          || new Integer(cardKind).intValue() > 9
          || new Integer(cardKind).intValue() < 0) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "证件类型格式不确");
      }
      try {
        Integer.parseInt(cardKind);
      } catch (Exception s) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "证件类型必须为整数");
      }
      if (department.trim().length() > 20) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "所在部门录入长度过长");
      }

      if (tel.trim().length() > 20) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "家庭电话录入长度过大");
      }
      if (mobileTle.trim().length() > 20) {
        throw new BusinessException("第" + (i + 3) + "行记录" + "移动电话录入长度过大");
      }
      if (monthIncome != null && !monthIncome.trim().equals("")) {
        if (monthIncome.trim().length() > 18) {
          throw new BusinessException("第" + (i + 3) + "行记录" + "月收入长度过大");
        }
        try {
          Double.parseDouble(monthIncome.trim());
        } catch (Exception s) {
          throw new BusinessException("第" + (i + 3) + "行记录" + "月收入格式错误");
        }
      }
      if (cardKind.equals("0")) {// 验证身份证的方法
        if (cardNum.length() != 15 && cardNum.length() != 18) {
          throw new BusinessException("第" + (i + 3) + "行记录" + "身份证位数必须是15或18位:");
        }
        if (cardNum.length() == 15) {// 身份证如果是15位的时候
          String month = cardNum.substring(8, 10);
          String day = cardNum.substring(10, 12);
          String sex1 = cardNum.substring(14, 15);
          if (Integer.parseInt(month) > 12)
            throw new BusinessException("第" + (i + 3) + "行记录"
                + "身份证的月份必须在1-12之间");
          if (Integer.parseInt(day) > 31)
            throw new BusinessException("第" + (i + 3) + "行记录"
                + "身份证的日期必须在1-31之间");
          String birthday1 = "19" + cardNum.substring(6, 8) + month + day;
          obj[0] = birthday1;
          obj[1] = sex1;
        }
        if (cardNum.length() == 18) {
          String month = cardNum.substring(10, 12);
          String day = cardNum.substring(12, 14);
          String sex1 = cardNum.substring(16, 17);
          if (Integer.parseInt(month) > 12)
            throw new BusinessException("第" + (i + 3) + "行记录"
                + "身份证的月份必须在1-12之间");
          if (Integer.parseInt(day) > 31)
            throw new BusinessException("第" + (i + 3) + "行记录"
                + "身份证的日期必须在1-31之间");
          String birthday1 = cardNum.substring(6, 10) + month + day;
          obj[0] = birthday1;
          obj[1] = sex1;
        }
      }
      // 判断是否存在姓名不同但是身份证号相同的职工
      List temp_list = empDAO.queryUpdateCardNum_FYF(empName, cardNum, empId,
          orgId);
//      if (temp_list.size() > 0) {
//        throw new BusinessException("第" + (i + 3) + "行记录"
//            + "已经存在姓名不同但是身份证号相同的职工");
//      }
      // 判断结束
      Emp emp = empDAO.queryByCriterions(empId, orgId);
      emp.setEmpAgentNum("");
      emp.getEmpInfo().setName(empName);
      emp.getEmpInfo().setCardNum(cardNum);
      emp.getEmpInfo().setSex(new BigDecimal(sex));
      emp.getEmpInfo().setBirthday(birthday);
      emp.getEmpInfo().setCardKind(new BigDecimal(cardKind));
      emp.getEmpInfo().setTel(tel);
      emp.getEmpInfo().setMobileTle(mobileTle);
      emp.getEmpInfo().setMonthIncome(new BigDecimal(monthIncome));
      emp.getEmpInfo().setDepartment(department);
      // empDAO.updateEmpAgentNum(orgId, empId, empAgentNum, empName, cardNum,
      // sex, birthday, cardKind, tel, mobileTle, monthIncome, department);
    }
    return null;
  }

  public List isCardNumSame(String empName, String cardNum)
      throws BusinessException, Exception {
    List list = empDAO.queryEmpInfoByCardNum_FYF(empName, cardNum);
    return list;
  }

  public List isCardNumUpdateSame(String empName, String cardNum, String empId)
      throws BusinessException, Exception {
    List list = empDAO.queryUpdateCardNumById_FYF(empName, cardNum, empId);
    return list;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setOrgOpenAccountBizActivityLogDAO(
      OrgOpenAccountBizActivityLogDAO orgOpenAccountBizActivityLogDAO) {
    this.orgOpenAccountBizActivityLogDAO = orgOpenAccountBizActivityLogDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
    this.empInfoDAO = empInfoDAO;
  }

  public void setBaseGhgInfoDAO(BaseGhgInfoDAO baseGhgInfoDAO) {
    this.baseGhgInfoDAO = baseGhgInfoDAO;
  }

  public void setOrgChgLogDAO(OrgChgLogDAO orgChgLogDAO) {
    this.orgChgLogDAO = orgChgLogDAO;
  }

  public void setSearchLackInfoDAO(SearchLackInfoDAO searchLackInfoDAO) {
    this.searchLackInfoDAO = searchLackInfoDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  /**
   * 以下是单位版添加内容
   */
  /**
   * 提取数据 20071213
   */
  public void pickupDateAll(String orgId, SecurityInfo securityInfo)
      throws BusinessException {
    // 判断DA001状态
    String status = autoInfoPickDAO.findStatus(orgId, orgId,
        BusiConst.ORGBUSINESSTYPE_P);
    try {
      if (status.equals("")) {
        throw new BusinessException("此单位不存在单位版信息，无法提取！");
      } else {
        if (!status.equals("0")) {
          throw new BusinessException("该单位不存在未提取的记录！");
        } else {

          List pickupEmpList = empDAODW.queryByEmpId_lg(null,
              new Integer(orgId));
          Org org = orgDAO.queryById(new Integer(orgId));
          List empOpenImpTitle = new ArrayList();
          List empOpenImpContent = new ArrayList();
          EmpOpenImpTitleDTO empOpenImpTitleDTO = new EmpOpenImpTitleDTO();
          EmpOpenImpTitleDTO tempEmpOpenImpTitleDTO = new EmpOpenImpTitleDTO();
          if (!pickupEmpList.isEmpty()) {
            tempEmpOpenImpTitleDTO.setOrgunitcode("");
            empOpenImpTitle.add(tempEmpOpenImpTitleDTO);
            empOpenImpTitleDTO.setOrgunitcode(orgId);
            empOpenImpTitle.add(empOpenImpTitleDTO);
            EmpOpenImpContentDTO tempEmpOpenImpContentDTO = new EmpOpenImpContentDTO();
            empOpenImpContent.add(tempEmpOpenImpContentDTO);
            for (int i = 0; i < pickupEmpList.size(); i++) {
              EmpOpenImpContentDTO empOpenImpContentDTO = new EmpOpenImpContentDTO();
              Emp emp = (Emp) pickupEmpList.get(i);
              EmpInfo empInfo = empDAODW.queryEmpInfoById(new Integer(emp
                  .getEmpInfo().getId().toString()));
              empOpenImpContentDTO
                  .setCardkind(empInfo.getCardKind().toString());
              empOpenImpContentDTO.setCardnum(empInfo.getCardNum());
              empOpenImpContentDTO.setDept(empInfo.getDepartment());
              empOpenImpContentDTO.setEmpname(empInfo.getName());
              empOpenImpContentDTO.setEmppay(emp.getEmpPay().toString());
              empOpenImpContentDTO.setMobileTle(empInfo.getMobileTle());
              empOpenImpContentDTO.setMonthIncome(empInfo.getMonthIncome()
                  .toString());
              empOpenImpContentDTO.setOrgpay(emp.getOrgPay().toString());
              empOpenImpContentDTO
                  .setSalarybase(emp.getSalaryBase().toString());
              empOpenImpContentDTO.setTel(empInfo.getTel());
              empOpenImpContent.add(empOpenImpContentDTO);
            }
            try {
              this.modifyOrgOpenBatch(empOpenImpTitle, empOpenImpContent,
                  orgId, securityInfo);
            } catch (Exception e) {
              throw new BusinessException("数据有问题");
            }
          }
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
          hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_PICKUPDATA);
          hafOperateLog.setOpBizId(new Integer(orgId));
          hafOperateLog.setOpIp(securityInfo.getUserIp());
          hafOperateLog.setOrgId(new Integer(orgId));
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOperator(securityInfo.getUserName());
          hafOperateLogDAO.insert(hafOperateLog);
          try {
            autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP, orgId,
                "newDate", orgId, orgId, BusiConst.ORGBUSINESSTYPE_P);
          } catch (Exception e) {
            throw new BusinessException("数据有问题");
          }
        }
      }
    } catch (BusinessException bx) {
      throw bx;
    }
  }

  // 单位版撤销数据
  public void pprovalDataInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException {
    // 判断DA001状态
    boolean status = autoInfoPickDAODW.isNOPickUp(orgId, orgId,
        BusiConst.ORGBUSINESSTYPE_P);
    try {
      if (status) {
        // 未提取
        // 删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgId, orgId,
            BusiConst.ORGBUSINESSTYPE_P);
        // 插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(""
            + BusiLogConst.OP_MODE_OPEN_ORGOPEN_MAINTAIN);
        hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_PPROVALDATA);
        hafOperateLog.setOpBizId(new Integer(orgId));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(orgId));
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      } else {
        throw new BusinessException("该业务已被中心提取，不可以撤销提交数据!");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      throw new BusinessException("撤销数据失败!");
    }
  }

  public void referringDataInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException {
    // 判断DA001状态
    boolean status = autoInfoPickDAODW.isNOPickIn(orgId, orgId,
        BusiConst.ORGBUSINESSTYPE_P);
    try {
      if (!status) {
        // 未提取
        // 插入DA001
        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(orgId));
        autoInfoPick.setOrgHeadId(new Integer(orgId));
        autoInfoPick.setCenterHeadId(null);
        autoInfoPick.setPayHeadId(null);
        autoInfoPick.setCenterBizDate(null);
        autoInfoPick.setOrgBizDate(new Date());
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_P);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPickDAODW.insert(autoInfoPick);
        // 插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
        hafOperateLog
            .setOpButton("" + BusiLogConst.BIZLOG_ACTION_REFERRINGDATE);
        hafOperateLog.setOpBizId(new Integer(orgId));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(orgId));
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      } else {
        throw new BusinessException("已提交完毕!");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      throw new BusinessException("提交数据失败!");
    }
  }

  // 单位版撤销数据职工开户页面
  public void pprovalDataOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException {
    // 判断DA001状态
    boolean status = autoInfoPickDAODW.isNOPickUp(orgId, orgId,
        BusiConst.ORGBUSINESSTYPE_P);
    try {
      if (status) {
        // 未提取
        // 删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgId, orgId,
            BusiConst.ORGBUSINESSTYPE_P);
        // 插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
        hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_PPROVALDATA);
        hafOperateLog.setOpBizId(new Integer(orgId));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(orgId));
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      } else {
        throw new BusinessException("该业务已被中心提取，不可以撤销提交数据!");
      }
    } catch (Exception e) {
      throw new BusinessException("撤销数据失败!");
    }
  }

  // 单位版提交数据职工开户页面
  public void referringDataOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException {
    // 判断DA001状态
    boolean status = autoInfoPickDAODW.isNOPickIn(orgId, orgId,
        BusiConst.ORGBUSINESSTYPE_P);
    try {
      if (!status) {
        // 未提取
        // 插入DA001
        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(orgId));
        autoInfoPick.setOrgHeadId(new Integer(orgId));
        autoInfoPick.setCenterHeadId(null);
        autoInfoPick.setPayHeadId(null);
        autoInfoPick.setCenterBizDate(null);
        autoInfoPick.setOrgBizDate(new Date());
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_P);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPickDAODW.insert(autoInfoPick);
        // 插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_EMPOPEN);
        hafOperateLog
            .setOpButton("" + BusiLogConst.BIZLOG_ACTION_REFERRINGDATE);
        hafOperateLog.setOpBizId(new Integer(orgId));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(orgId));
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      } else {
        throw new BusinessException("已提交完毕!");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      throw new BusinessException("提交数据失败!");
    }
  }

  // 修改empId
  public EmpChangeAF changeEmpIdInfo(String orgId, SecurityInfo securityInfo,
      String id) throws BusinessException {
    EmpChangeAF empChangeAF = new EmpChangeAF();
    Emp emp = empDAO.queryById(new Integer(id));
    if (emp == null) {
      throw new BusinessException("单位没有此人!");
    } else {
      EmpInfo empInfo = empInfoDAO.queryById(new Integer(emp.getEmpInfo()
          .getId().toString()));
      String empName = empInfo.getName();
      String cardNum = empInfo.getCardNum();
      // 查看中心库中是否有此人
      List isEmpList = empDAODW.getEmp_WL(orgId, empName, cardNum, emp
          .getEmpId());
      if (!isEmpList.isEmpty()) {
        throw new BusinessException("职工编号一致，不需要修改!");
      } else {
        // 查看中心库中是否有修改empId
        List empList = empDAODW.getEmp_WL(orgId, empName, cardNum, null);
        if (!empList.isEmpty()) {
          if (empList.size() == 1) {
            Emp centerEmp = (Emp) empList.get(0);
            String centerEmpid = centerEmp.getEmpId().toString();
            if (centerEmpid.equals(emp.getEmpId())) {
              throw new BusinessException("职工编号一致，不需要修改!");
            } else {
              // 调用存储过程
              this.changeEmpid(centerEmpid, orgId, emp.getEmpId().toString());
            }
          }
          if (empList.size() > 1) {
            List returnList = new ArrayList();
            for (int i = 0; i < empList.size(); i++) {
              emp = (Emp) empList.get(i);
              BigDecimal temp_Balance = emp.getCurBalance().add(
                  emp.getPreBalance());
              emp.setTEMP_salaryBase(temp_Balance);
              returnList.add(emp);
            }
            empChangeAF.setOldEmpId(emp.getEmpId().toString());
            empChangeAF.setOrgId(orgId);
            empChangeAF.setList(returnList);
          }
        } else {
          throw new BusinessException("中心没有此人!");
        }
      }
    }
    return empChangeAF;
  }

  // 修改empId
  public void changeEmpid(String empId, String orgId, String oldEmpId)
      throws BusinessException {
    try {
      empDAO.changeEmpId_sy(empId, orgId, oldEmpId);
    } catch (Exception e) {
      throw new BusinessException("职工编号修改失败!!!");
    }
  }
  public void updateba001_tijiao(String orgId) throws BusinessException {
    try {
      orgDAO.update_ba001(orgId);
    } catch (Exception e) {
      throw new BusinessException("更新单位信息表失败！");
    }
  }
  /**
   * bit add 查询打印列表得信息
   */
  public List queryprintlist(String orgid, String orgname) {
    List templist = empDAO.queryprintlistbyidandname(orgid, orgname);
    List printlist = new ArrayList();
    int c=0;
    Object o[] = null;
    for (int i = 0; i < templist.size(); i++) {
      PrintListDTO dto = new PrintListDTO();
      o = (Object[]) templist.get(i);
      if (o[0] != null) {
        c=i+1;
        dto.setId_count(""+c);
      }
      if (o[1] != null) {
        dto.setEmpname(o[1].toString());
      }
      if (o[2] != null) {
     //   System.out.println("o[2].toString()="+o[2].toString());
        if(o[2].toString().equals("1")){
          dto.setSex("男");
        }else{
          dto.setSex("女");
        }
       
      }
      if (o[3] != null) {
        dto.setCardnum(o[3].toString());
      }
      if (o[4] != null) {
        dto.setSalarybase(o[4].toString());
      }
      if (o[5] != null) {
        dto.setSumpay(o[5].toString());
      }
      if (o[6] != null) {
        dto.setEmppay(o[6].toString());
      }
      if (o[7] != null) {
        dto.setOrgpay(o[7].toString());
      }
      if (o[8] != null) {
        dto.setOrgid("0"+o[8].toString());
      }
      if (o[9] != null) {
        dto.setOrgname(o[9].toString());
      }
      
      if (o[10] != null) {
        dto.setARTIFICIAL_PERSON(o[10].toString());
      }else{
        dto.setARTIFICIAL_PERSON("");
      }
      if (o[11] != null) {
        dto.setTRANSACTOR_NAME(o[11].toString());
      }else{
        dto.setTRANSACTOR_NAME("");
      }
      printlist.add(dto);
    }
    return printlist;
  }

  /**
   * bit add 根据单位ID查询归集银行
   */
  public String querycollbankid(Integer orgid, String orgname) {
    String collbank = orgDAO.querycollbank(orgid, orgname);
    return collbank;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setOrgDAODW(OrgDAODW orgDAODW) {
    this.orgDAODW = orgDAODW;
  }

  public void setOrgEditionDAO(OrgEditionDAO orgEditionDAO) {
    this.orgEditionDAO = orgEditionDAO;
  }

  public void setOrgInfoDAODW(OrgInfoDAODW orgInfoDAODW) {
    this.orgInfoDAODW = orgInfoDAODW;
  }

  public void setSearchLackInfoDAODW(SearchLackInfoDAODW searchLackInfoDAODW) {
    this.searchLackInfoDAODW = searchLackInfoDAODW;
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
  }

  public boolean queryEmployee(String orgId, String empid ) throws BusinessException {
    // TODO Auto-generated method stub
    Emp empp = empDAO.queryById(new Integer(empid));
    List list=empDAO.getPl110byCardNumwuht(empp.getEmpInfo().getCardNum());
    if(list!=null && list.size()>0){
      return false;
    }
    return true;
   
  }

}
