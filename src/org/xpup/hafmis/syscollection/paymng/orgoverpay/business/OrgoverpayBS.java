package org.xpup.hafmis.syscollection.paymng.orgoverpay.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPaymentBalanceEndTransfer;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.dto.OrgExcessPaymentDTO;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class OrgoverpayBS implements IOrgoverpayBS {

  private OrgExcessPaymentDAO orgExcessPaymentDAO = null;

  private MonthPaymentDAO monthPaymentDAO = null;

  private MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO = null;

  private OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO = null;

  private OrgDAO orgDAO = null;

  private CollBankDAO collBankDAO = null;

  private OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO = null;

  private OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private SecurityDAO securityDAO = null;

  private TranOutHeadDAO tranOutHeadDAO = null;

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public OrgExcessPaymentBizActivityLogDAO getOrgExcessPaymentBizActivityLogDAO() {
    return orgExcessPaymentBizActivityLogDAO;
  }

  public void setMonthPaymentBizActivityLogDAO(
      MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO) {
    this.monthPaymentBizActivityLogDAO = monthPaymentBizActivityLogDAO;
  }

  public void setMonthPaymentDAO(MonthPaymentDAO monthPaymentDAO) {
    this.monthPaymentDAO = monthPaymentDAO;
  }

  public void setOrgExcessPaymentBizActivityLogDAO(
      OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO) {
    this.orgExcessPaymentBizActivityLogDAO = orgExcessPaymentBizActivityLogDAO;
  }

  public OrgExcessPaymentDAO getOrgExcessPaymentDAO() {
    return orgExcessPaymentDAO;
  }

  public void setOrgExcessPaymentDAO(OrgExcessPaymentDAO orgExcessPaymentDAO) {
    this.orgExcessPaymentDAO = orgExcessPaymentDAO;
  }

  public OrgDAO getOrgDAO() {
    return orgDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO getOrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO() {
    return orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
  }

  public void setOrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO(
      OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO) {
    this.orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO = orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
  }

  public OrgHAFAccountFlowExcessPaymentDAO getOrgHAFAccountFlowExcessPaymentDAO() {
    return orgHAFAccountFlowExcessPaymentDAO;
  }

  public void setOrgHAFAccountFlowExcessPaymentDAO(
      OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO) {
    this.orgHAFAccountFlowExcessPaymentDAO = orgHAFAccountFlowExcessPaymentDAO;
  }

  public HafOperateLogDAO getHafOperateLogDAO() {
    return hafOperateLogDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  // 查询银行
  public String findCollBank(String collBankid) {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }

  // 根据单位编号查询单位信息
  public Org findOrgInfo(Serializable id, String status,
      SecurityInfo securityInfo) throws BusinessException {

    Org org = null;
    String orgid = "";
    if (id != null) {
      orgid = id.toString();
    }
    org = orgDAO.queryByCriterions(orgid, status, "", securityInfo);

    if (org == null && orgid != null) {
      org = new Org();
    }
    return org;
  }

  // 查询单位是否存在未到账的挂账业务
  public OrgExcessPayment findOrgoverpayInfo(Serializable orgid,
      Integer payStatus) throws BusinessException {

    OrgExcessPayment orgExcessPayment = null;
    if (orgid != null) {
      orgExcessPayment = orgExcessPaymentDAO.findOrgoverpayInfo(orgid, null);
    }
    return orgExcessPayment;
  }

  // 查询单位挂账余额
  public BigDecimal queryOrgoverpayBalance(Serializable orgid)
      throws BusinessException {
    // TODO Auto-generated method stub
    BigDecimal orgoverpayBalance = new BigDecimal(0.00);
    if ((orgid != null)) {
      List list = orgHAFAccountFlowExcessPaymentDAO
          .queryOrgoverpayHAFAccountFlow(orgid, new Integer(5));
      for (int i = 0; i < list.size(); i++) {
        OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment = (OrgHAFAccountFlowExcessPayment) list
            .get(i);
        orgoverpayBalance = orgoverpayBalance
            .add(orgHAFAccountFlowExcessPayment.getCredit().subtract(
                orgHAFAccountFlowExcessPayment.getDebit()));
      }
      List lists = orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO
          .orgHAFAccountFlowExcessPaymentBalanceEndTransfer(orgid, null);
      for (int i = 0; i < lists.size(); i++) {
        OrgHAFAccountFlowExcessPaymentBalanceEndTransfer orgHAFAccountFlowExcessPaymentBalanceEndTransfer = (OrgHAFAccountFlowExcessPaymentBalanceEndTransfer) lists
            .get(i);
        orgoverpayBalance = orgoverpayBalance
            .add(orgHAFAccountFlowExcessPaymentBalanceEndTransfer
                .getCredit()
                .subtract(
                    orgHAFAccountFlowExcessPaymentBalanceEndTransfer.getDebit()));
      }
      orgoverpayBalance=orgoverpayBalance.add(orgHAFAccountFlowExcessPaymentDAO.queryOrgoverpayfromAA301(orgid));
    }

    return orgoverpayBalance;
  }

  // 添加AA301字段AA309字段BA003字段
  public void insertPaymentHead(Serializable orgid, String payMoney,
      String noteNum, String reason, String type, SecurityInfo securityInfo)
      throws BusinessException {
    Org org = this.findOrgInfo(orgid, "2", securityInfo);
    OrgExcessPayment orgExcessPayment = new OrgExcessPayment();
    orgExcessPayment.setOrg(org);
    orgExcessPayment.setPayMoney(new BigDecimal(payMoney));
    orgExcessPayment.setNoteNum(noteNum);
    orgExcessPayment.setPayStatus(new Integer(2));
    orgExcessPayment.setExcessReason(reason);
    orgExcessPayment.setTmpplaceKind(type);
    orgExcessPaymentDAO.insert(orgExcessPayment);
    OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog = new OrgExcessPaymentBizActivityLog();
    orgExcessPaymentBizActivityLog.setBizid(new Integer(orgExcessPayment
        .getId().toString()));
    orgExcessPaymentBizActivityLog.setAction(new Integer(2));
    orgExcessPaymentBizActivityLog.setOpTime(new Date());
    orgExcessPaymentBizActivityLog.setOperator(securityInfo.getUserName());
    orgExcessPaymentBizActivityLogDAO.insert(orgExcessPaymentBizActivityLog);
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_ADD));
    hafOperateLog.setOpBizId(new Integer(orgExcessPayment.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgExcessPayment.getOrg().getId()
        .toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  // 查询AA301中的PAY_STATUS=2、PAY_TYPE=D的每一条记录
  public List queryOrgoverpayList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    List list = new ArrayList();
    List returnlist = new ArrayList();
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String unitName = (String) pagination.getQueryCriterions().get("unitName");
    String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
    String bizStatus = (String) pagination.getQueryCriterions()
        .get("bizStatus");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    String opTime = (String) pagination.getQueryCriterions().get("opTime");
    String payMonth1 = (String) pagination.getQueryCriterions()
        .get("payMonth1");
    String opTime1 = (String) pagination.getQueryCriterions().get("opTime1");
    String orderby = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    list = orgExcessPaymentDAO.queryOrgoverpayList(orgId, unitName, payMoney,
        bizStatus, payMonth, payMonth1, start, pageSize, orderby, order, page,
        opTime, opTime1, securityInfo);

    // 转换业务状态
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        OrgExcessPaymentDTO dto1 = (OrgExcessPaymentDTO) list.get(i);

        dto1.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto1
            .getPayStatus().toString()), BusiConst.BUSINESSSTATE));
        dto1.setOrgId("0" + dto1.getOrgId());
        returnlist.add(dto1);
      }
    }
    int count = orgExcessPaymentDAO
        .queryOrgoverpayCount(orgId, unitName, payMoney, bizStatus, payMonth,
            payMonth1, opTime, opTime1, securityInfo);
    pagination.setNrOfElements(count);
    return returnlist;
  }

  // 单位挂账金额合计
  public BigDecimal getEmpaddpayMoney(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    BigDecimal money = new BigDecimal(0.00);
    // List list=new ArrayList();
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String unitName = (String) pagination.getQueryCriterions().get("unitName");
    String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
    String bizStatus = (String) pagination.getQueryCriterions()
        .get("bizStatus");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    String opTime = (String) pagination.getQueryCriterions().get("opTime");
    String payMonth1 = (String) pagination.getQueryCriterions()
        .get("payMonth1");
    String opTime1 = (String) pagination.getQueryCriterions().get("opTime1");
    money = orgExcessPaymentDAO
        .queryOrgoverpayAmount(orgId, unitName, payMoney, bizStatus, payMonth,
            opTime, payMonth1, opTime1, securityInfo);
    // for(int i=0;i<list.size();i++){
    // OrgExcessPayment orgExcessPayment=(OrgExcessPayment)list.get(i);
    // money=money.add(orgExcessPayment.getPayMoney());
    // }
    return money;

  }

  // 删除AA301,AA309,插入BA003
  public void deleteOrgoverpay(String id, SecurityInfo securityInfo)
      throws BusinessException {
    OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO.queryByHeadId(id);
    // 判断此挂账是否为自动挂账
    if (orgExcessPayment.getReserveaA() != null
        && !orgExcessPayment.getReserveaA().equals("")) {
      // 删除对应的汇缴业务
      MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(
          orgExcessPayment.getReserveaA()));
      if (monthPayment != null) {
        // throw new BusinessException("自动挂账业务，请到缴存维护页面进行删除！");
        monthPaymentDAO.delete(monthPayment);
        MonthPaymentBizActivityLog monthPaymentBizActivityLog = monthPaymentBizActivityLogDAO
            .queryMonthPaymentLogByPayHeadIdLJ(monthPayment.getId(),
                new Integer(2));
        monthPaymentBizActivityLogDAO.delete(monthPaymentBizActivityLog);
      }
    }
    List list = orgExcessPaymentBizActivityLogDAO.queryOrgoverpayLog(id);
    orgExcessPaymentBizActivityLogDAO.deleteList(list);
    orgExcessPaymentDAO.delete(orgExcessPayment);
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN));
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_DELETE));
    hafOperateLog.setOpBizId(new Integer(orgExcessPayment.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgExcessPayment.getOrg().getId()
        .toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  // 单位挂账-修改
  public OrgExcessPayment updateOrgoverpay(String id, SecurityInfo securityInfo)
      throws BusinessException {
    OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO.queryByHeadId(id);
    return orgExcessPayment;
  }

  public void updateOrgoverpayById(String id, String noteNum, String payMoney,
      String reason, SecurityInfo securityInfo) throws BusinessException {
    OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO.queryByHeadId(id);
    orgExcessPayment.setNoteNum(noteNum);
    orgExcessPayment.setPayMoney(new BigDecimal(payMoney));
    orgExcessPayment.setExcessReason(reason);
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN));
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_MODIFY));
    hafOperateLog.setOpBizId(new Integer(orgExcessPayment.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgExcessPayment.getOrg().getId()
        .toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  // 查询单位挂账清册明细
  public OrgoverpayAF findOrgoverpayMX(Pagination pagination) throws Exception,
      BusinessException {

    OrgoverpayAF orgoverpayAF = new OrgoverpayAF();
    Serializable paymentid = (Serializable) pagination.getQueryCriterions()
        .get("paymentid");
    OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO
        .queryById(new Integer(paymentid.toString()));
    Serializable orgId = orgExcessPayment.getOrg().getId().toString();
    BigDecimal banlance = new BigDecimal(0.00);
    banlance = this.queryOrgoverpayBalance(orgId);
    orgoverpayAF.setOrgId("0" + (orgId.toString()));
    orgoverpayAF.setUnitName(orgExcessPayment.getOrg().getOrgInfo().getName());
    orgoverpayAF.setNoteNum(orgExcessPayment.getNoteNum());
    orgoverpayAF.setBanlance(banlance.toString());
    orgoverpayAF.setAmount(orgExcessPayment.getPayMoney().toString());
    orgoverpayAF.setReason(orgExcessPayment.getExcessReason());
    return orgoverpayAF;
  }

  // 打印操作
  public OrgoverpayAF printReport(String id) throws Exception,
      BusinessException {

    OrgoverpayAF orgoverpayAF = new OrgoverpayAF();
    OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO.findById(id);
    Serializable orgId = orgExcessPayment.getOrg().getId().toString();
    BigDecimal banlance = new BigDecimal(0.00);
    banlance = this.queryOrgoverpayBalance(orgId);
    orgoverpayAF.setOrgId("0" + orgId.toString());
    orgoverpayAF.setUnitName(orgExcessPayment.getOrg().getOrgInfo().getName());
    orgoverpayAF.setNoteNum(orgExcessPayment.getNoteNum());
    orgoverpayAF.setBanlance(banlance.toString());
    orgoverpayAF.setAmount(orgExcessPayment.getPayMoney().toString());
    orgoverpayAF.setReason(orgExcessPayment.getExcessReason());
    orgoverpayAF.setOrgBalance(orgExcessPaymentDAO
        .queryOrgBalanceByOrgID(orgId));
    orgoverpayAF.setCreNum(orgExcessPayment.getDocNum());
    String name = "";
    String checker = "";
    String maker = "";
    name = monthPaymentDAO.getNamePara();
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryByBizId_wsh(id, "C");
    if (orgHAFAccountFlow != null) {
      checker = orgHAFAccountFlow.getCheckPerson();
      maker = orgHAFAccountFlow.getClearPerson();
    }
    // orgExcessPaymentDAO.queryMaker(orgExcessPayment.getId());
    if (name.equals("1")) {
      if (checker != null && !checker.equals("")) {
        orgoverpayAF.setChecker(checker);
      }
      orgoverpayAF.setMaker(maker);
    } else {
      orgoverpayAF.setMaker(securityDAO.queryByUserid(maker)); // 转换成中文
      orgoverpayAF.setChecker(securityDAO.queryByUserid(checker));
    }

    return orgoverpayAF;
  }

  // 看是否存在缴存类型为D的并且缴存状态不等于5的数据，如果存在就不允许再做挂账业务
  public boolean isOrgoverpay(String orgId, SecurityInfo securityInfo)
      throws Exception {
    try {
      int temp_count = orgExcessPaymentDAO.queryOrgoverpayId(orgId,
          securityInfo);
      if (temp_count == 0) {
        // 可以挂帐
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 不可以挂帐
    return false;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
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
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
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

  // ld_add添加，用于判断并发操作
  public List querytest(String id) {
    List list = new ArrayList();
    list = orgExcessPaymentDAO.testquery(id);
    return list;
  }

  public SecurityDAO getSecurityDAO() {
    return securityDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public String FindAA103_DayTime(String collbankid) throws Exception {
    String date = tranOutHeadDAO.findAA103_DayTime(collbankid);
    return date;
  }

  public void setTranOutHeadDAO(TranOutHeadDAO tranOutHeadDAO) {
    this.tranOutHeadDAO = tranOutHeadDAO;
  }

  public String queryNoteNum() throws Exception {
    String notenum = orgDAO.queryNoteNum();
    return notenum;
  }

  public String[] queryBankInfor(String bankId) throws Exception {
    // TODO Auto-generated method stub
    String str[] = new String[3];
    try {
      str = orgDAO.findBankInfor(bankId);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return str;
  }

  public boolean isOrgoverpay_1(String notenum, String account,
      SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      int temp_count = orgExcessPaymentDAO.queryOrgoverpayId_1(notenum,
          account, securityInfo);
      if (temp_count > 0) {
        // 可以挂帐
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 不可以挂帐
    return false;
  }

  // 查询单位为记账的冲挂账金额
  public String queryOrgOverPayAccount(String orgId, SecurityInfo securityInfo)
      throws Exception {
    // TODO Auto-generated method stub
    String notenum = orgExcessPaymentDAO.queryOrgOverPayAccount(orgId,
        securityInfo);
    return notenum;
  }
}
