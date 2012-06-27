package org.xpup.hafmis.syscollection.chgbiz.chgpay.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayHeadImportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayListImportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayListAF;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseHeadImportDTO;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPaymentPaymentDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPersonHeadDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * @author Administrator
 */
public class ChgpayBS implements IChgpayBS {

  protected OrgDAO orgDAO = null;

  protected AutoInfoPickDAO autoInfoPickDAO = null;

  protected AutoInfoPickDAODW autoInfoPickDAODW = null;

  protected ChgOrgRateDAO chgOrgRateDAO = null;

  protected ChgPersonHeadDAO chgPersonHeadDAO = null;

  protected ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;

  protected ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;

  protected ChgPaymentTailDAO chgPaymentTailDAO = null;

  protected ChgPaymentPaymentDAODW chgPaymentPaymentDAODW = null;

  protected ChgPaymentHeadDAO chgPaymentHeadDAO = null;

  protected EmpDAO empDAO = null;

  protected HafOperateLogDAO hafOperateLogDAO = null;

  protected ChgPaymentBizActivityLogDAO chgPaymentBizActivityLogDAO = null;

  protected PaymentHeadDAO paymentHeadDAO = null;

  protected ChgPersonHeadDAODW chgPersonHeadDAODW = null;

  private String center_head_id = "";

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }

  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
  }

  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public ChgPaymentBizActivityLogDAO getChgPaymentBizActivityLogDAO() {
    return chgPaymentBizActivityLogDAO;
  }

  public void setChgPaymentBizActivityLogDAO(
      ChgPaymentBizActivityLogDAO chgPaymentBizActivityLogDAO) {
    this.chgPaymentBizActivityLogDAO = chgPaymentBizActivityLogDAO;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setChgPaymentPaymentDAODW(
      ChgPaymentPaymentDAODW chgPaymentPaymentDAODW) {
    this.chgPaymentPaymentDAODW = chgPaymentPaymentDAODW;
  }

  public void setChgPersonHeadDAODW(ChgPersonHeadDAODW chgPersonHeadDAODW) {
    this.chgPersonHeadDAODW = chgPersonHeadDAODW;
  }

  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }

  public ChgpayListAF findChgpayList(Pagination pagination) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    // TODO Auto-generated method stub
    Org org = null;
    List empChgpayChangements = null;
    ChgPaymentPayment chgPaymentPayment = null;
    ChgpayListAF chgpayListAF = new ChgpayListAF();
    Emp emp = null;
    String chgMonth = "";

    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("SecurityInfo");
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    if (((orgid != null) && (orgid.trim() != "") && (orgid.trim().length() > 0))) {

      org = orgDAO.queryByCriterions(orgid, null, null, securityInfo);
      if (org == null) {
        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("对不起，不存在该单位或者不在权限内！");

      }
      Integer orgid1 = new Integer(orgid);
      boolean Status1 = chgPaymentSalaryBaseDAO.getChgStatus(orgid1);
      boolean Status3 = chgOrgRateDAO.getChgStatus(orgid1);
      boolean Status2 = chgPersonHeadDAO.getChgStatus(orgid1);

      org = orgDAO.queryByCriterions(orgid, "2", "2", securityInfo);

      if (org == null) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("对不起，该单位缴存方式为单一缴率或者不在权限内！");
      }
      if (!Status1) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("对不起，该单位存在未启用的工资基数调整！");
      }
      if (!Status3) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("对不起，该单位存在未启用的汇缴比例调整！");
      }
      if (!Status2) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("对不起，该单位存在未启用的人员变更！");
      }
      chgpayListAF.getOrg().getOrgInfo().setName(org.getOrgInfo().getName());
      // 在chgPaymentSalaryBase中查询在org.id与chgPaymentSalaryBase.chgStatus=1

      // 的aa202的数据
      chgPaymentPayment = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);// 头表;

      if (chgPaymentPayment == null) {
        // aa301最大ID
        String headMaxId_temp = paymentHeadDAO.queryMaxID(orgid);
        if (!headMaxId_temp.equals("")) {
          String payMonth_temp = paymentHeadDAO
              .queryPaymentHeadMaxID(headMaxId_temp);
          if (!payMonth_temp.equals("")) {
            chgMonth = BusiTools.addMonth(payMonth_temp, 1);
            chgpayListAF.setChgMonth(chgMonth);
          } else {
            chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);
            chgpayListAF.setChgMonth(chgMonth);
          }
        } else {
          chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);
          chgpayListAF.setChgMonth(chgMonth);
        }
      } else {

        chgMonth = chgPaymentPayment.getChgMonth();
        // 合计：调整人数oldPaymenSum
        // //调整前单位缴额oldOrgPaySum;//调整后单位缴额OrgPaySum;//调整前职工缴额oldEmpPaySum;//调整后职工缴额EmpPaySum;//调整后应汇缴额:-->

        chgpayListAF.setChgMonth(chgPaymentPayment.getChgMonth());
        chgpayListAF.setOldPaymenSum(chgPaymentPayment.getOldPaymenSum());
        chgpayListAF.setOldOrgPaySum(chgPaymentPayment.getOldOrgPaySum());
        chgpayListAF.setOrgPaySum(chgPaymentPayment.getOrgPaySum());
        chgpayListAF.setOldEmpPaySum(chgPaymentPayment.getOldEmpPaySum());
        chgpayListAF.setEmpPaySum(chgPaymentPayment.getEmpPaySum());
        chgpayListAF.setOldPayment(chgPaymentPayment.getOldPayment());

        chgpayListAF.setId(chgPaymentPayment.getId());

        List returnList = chgPaymentSalaryBaseDAO
            .querypay_statusByHeadID_WL(chgPaymentPayment.getId().toString());
        Integer pay_status = (Integer) returnList.get(0);
        if ((chgPaymentPayment.getPaySum() == null)
            && (pay_status.toString().equals("2") || pay_status.toString()
                .equals("5"))) {
          chgpayListAF.setPaySum(new Double(chgPaymentPayment.getOldPayment()
              .toString()));
        } else if (chgPaymentPayment.getPaySum() == null) {
          chgpayListAF.setPaySum(new Double(0));
        } else {
          chgpayListAF.setPaySum(chgPaymentPayment.getPaySum());
        }

      }
      empChgpayChangements = chgPaymentTailDAO.queryChgPaymentPaymentWuht(
          orgid, chgMonth, orderBy, order, start, pageSize, page);// 尾表
      for (int i = 0; i < empChgpayChangements.size(); i++) {
        ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) empChgpayChangements
            .get(i);

        emp = empDAO.queryByCriterions(
            chgPaymentTaillist.getEmpId().toString(), orgid);

        chgPaymentTaillist.setEmp(emp);
        String statetype = autoInfoPickDAODW.findStatus(chgPaymentTaillist
            .getChgPaymentHead().getOrg().getId().toString(),
            chgPaymentTaillist.getChgPaymentHead().getId().toString(),
            BusiConst.ORGBUSINESSTYPE_N);
        chgpayListAF.setStatetype(BusiTools.getBusiValue(Integer
            .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
      }
      int count = chgPaymentTailDAO.queryChgPaymentPaymentWuht(orgid, chgMonth);
      pagination.setNrOfElements(count);

      // 缴费核定是否应用了该单位该年月的变更
      if (org == null) {
        org = new Org();
      }
      if (chgpayListAF == null) {
        chgpayListAF = new ChgpayListAF();
      }
      chgpayListAF.setList(empChgpayChangements);
      chgpayListAF.setOrg(org);

    }
    return chgpayListAF;
  }

  // 添加查询
  public ChgPaymentTail findEmpinfo(String empid, String orgid)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail = null;

    BusinessException be = null;
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    Emp emp;
    try {
      if (((empid != null) && (empid.trim() != "") && (empid.trim().length() > 0))) {
        // 录入的职工编号是否是选择的单位的：录入的职工编号在AA002对应的单位编号是否等于之前录入的单位编号
        emp = empDAO.queryByCriterions(empid, orgid);
        if (emp == null) {
          emp = new Emp();
          chgPaymentTail = new ChgPaymentTail();
          be = new BusinessException("该职工不是这个单位的！！");
          return null;
        }
        // 在未被起用的变更清册里是否存在该职工：AA202里
        // 变更类型=2.缴额调整，变更状态=1.未启用对应的id在AA203里对应的职工编号里是否有录入的职工编号
        chgPaymentPayment = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
        if (chgPaymentPayment != null) {

          chgPaymentTail = chgPaymentTailDAO.queryChgPaymentPaymentWUHT(empid,
              orgid);
        }
        if (chgPaymentTail != null) {
          chgPaymentTail = new ChgPaymentTail();
          be = new BusinessException("该职工已经做过缴额调整，如要修改，请点击修改！");
          return null;
        }
        chgPaymentTail = new ChgPaymentTail();
        chgPaymentTail.setEmp(emp);

      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
    return chgPaymentTail;
  }

  // 点击添加的确定按钮
  public void addChgPaymentTail(ChgPaymentTail chgPaymentTail,
      Pagination pagination) throws BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    ChgPaymentPayment chgPaymentPayment1 = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    Emp emp = null;
    Org org = null;
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String date = (String) pagination.getQueryCriterions().get("date");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String empid = (String) pagination.getQueryCriterions().get(
          "chgPaymentTail.empId");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      String salaryBase = (String) pagination.getQueryCriterions().get(
          "salaryBase");
      String orgPay = (String) pagination.getQueryCriterions().get("orgPay");
      String empPay = (String) pagination.getQueryCriterions().get("empPay");

      chgPaymentPayment1 = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
      String empStatus = chgPaymentPaymentDAO.empstatuslishan(
          new Integer(empid), new Integer(orgid));
      ChgPaymentTail chgPaymentTail1 = null;
      if (chgPaymentPayment1 != null) {

        chgPaymentTail1 = chgPaymentTailDAO.queryChgPaymentPaymentWUHT(empid,
            orgid);
      }
      if (chgPaymentTail1 != null) {
        be = new BusinessException("该职工已经做过缴额调整，如要修改，请点击修改！");
      } else if (!empStatus.equals("") && empStatus.equals("5")) {
        be = new BusinessException("该职工的状态为删除！");
      } else {
        chgPaymentPayment = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
        // 是否存在未被起用的变更清册：AA202里 变更类型=B.缴额调整
        // ，变更状态=1.未启用：chgPaymentPayment==null不存在
        if (chgPaymentPayment == null || chgPaymentPayment.equals("")) {
          org = orgDAO.queryById(new Integer(orgid));
          chgPaymentPayment = new ChgPaymentPayment();
          // 写new Date() 有错误

          chgPaymentPayment.setBizDate(date);

          chgPaymentPayment.setChgMonth(chgMonth);
          chgPaymentPayment.setChgStatus(new Integer(1));
          chgPaymentPayment.setOrg(org);

          List list = new ArrayList();
          list = empDAO.getEmpListWuht(orgid);
          BigDecimal sumSalaryBase = new BigDecimal(0.00);
          BigDecimal sumPay = new BigDecimal(0.00);
          if (list != null) {
            for (int i = 0; i < list.size(); i++) {
              Emp listemp = (Emp) list.get(i);
              sumSalaryBase = sumSalaryBase.add(listemp.getSalaryBase());
              sumPay = sumPay.add(listemp.getOrgPay());
              sumPay = sumPay.add(listemp.getEmpPay());
            }
          }
          chgPaymentPayment.setOldPayment(sumPay);
          chgPaymentPayment.setOldSlarayBase(sumSalaryBase);

          chgPaymentPaymentDAO.insert(chgPaymentPayment);

          // 插入AA319：
          chgPaymentBizActivityLog.setBizid(new Integer(chgPaymentPayment
              .getId().toString()));
          chgPaymentBizActivityLog.setAction(new Integer(1));
          chgPaymentBizActivityLog.setOpTime(new Date());
          chgPaymentBizActivityLog.setOperator(name);
          chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);

        } else {
          if (pkid.equals("")) {

            chgPaymentPayment = chgPaymentPaymentDAO
                .queryByCriterionsWuht(orgid);

          } else {

            chgPaymentPayment = chgPaymentPaymentDAO
                .queryById(new Integer(pkid));
          }
        }
        emp = empDAO.queryByCriterions(empid, orgid);
        chgPaymentTail.setChgPaymentHead(chgPaymentPayment);
        chgPaymentTail.setEmp(emp);
        chgPaymentTail.setPayStatus(new Integer(emp.getPayStatus().toString()));
        chgPaymentTail.setSalaryBase(new BigDecimal(salaryBase));

        chgPaymentTailDAO.insert(chgPaymentTail);

        // 插入BA003：

        pkid = chgPaymentPayment.getId().toString();
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
        int opButton = busiLogConst.BIZLOG_ACTION_ADD;
        this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw new BusinessException("");
      }
    }

  }

  // 修改的查询
  public ChgPaymentTail findChgPaymentTailWuht(String id, Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
    Emp emp = new Emp();
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    try {
      chgPaymentTail = chgPaymentTailDAO.queryById(new Integer(id));
      emp = empDAO.queryByCriterions(chgPaymentTail.getEmpId().toString(),
          orgid);
      chgPaymentTail.setEmp(emp);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("");
    }
    return chgPaymentTail;
  }

  // 修改AA202：调整年月=录入的调整年月修改AA203：插入BA003：
  public void updateChgPaymentTail(ChgPaymentTail chgPaymentTail,
      Pagination pagination) throws BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail1 = new ChgPaymentTail();
    BusinessException be = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(pkid));
      chgPaymentPayment.setChgMonth(chgMonth);
      chgPaymentTail1 = chgPaymentTailDAO.queryById(new Integer(chgPaymentTail
          .getId().toString()));
      chgPaymentTail1.setSalaryBase(chgPaymentTail.getEmp().getSalaryBase());
      chgPaymentTail1.setEmpPay(chgPaymentTail.getEmpPay());
      chgPaymentTail1.setOrgPay(chgPaymentTail.getOrgPay());
      // 插入BA003：
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      int opButton = busiLogConst.BIZLOG_ACTION_MODIFY;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw new BusinessException("");
      }
    }

  }

  // 删除
  public void deleteChgPaymentTail(Integer id, String pkid, String orgid,
      String ip, String nrOfElements, String name, SecurityInfo securityInfo)
      throws Exception, BusinessException {

    // 判断是否为单位版
    int isOrgEdition = securityInfo.getIsOrgEdition();
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    ChgPaymentPayment chgPaymentPayment = null;
    BusiLogConst busiLogConst = null;
    ChgPaymentTail chgPaymentTail = null;
    try {

      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
        // 判断是否是AA203中最后一条记录
        if (nrOfElements.equals("1")) {
          // 判断提交状态是否为未提取
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_N);
          // 如果存在未提交的，提示先撤销提交
          if (isNoPickUp) {
            throw new BusinessException("请先撤销提交！");
          }
          String stype = autoInfoPickDAODW.findStatus(orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_N);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("中心已提取，不能删除");
          }
        }
      } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        // 判断是否是AA203中最后一条记录
        if (nrOfElements.equals("1")) {
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_N);
        }
      }

      chgPaymentTail = chgPaymentTailDAO.queryById(id);
      chgPaymentTailDAO.delete(chgPaymentTail);

      if (nrOfElements.equals("1")) {

        // 删除AA319：
        chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
            .queryChgPaymentBizActivityLogByIdWuht(pkid.toString(), "1");
        chgPaymentBizActivityLogDAO.deleteWuht(chgPaymentBizActivityLog);
        // 删除AA203：
        chgPaymentTail = chgPaymentTailDAO.queryById(id);

        chgPaymentTailDAO.delete(chgPaymentTail);
        // 删除AA202：
        chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(pkid));
        chgPaymentPaymentDAO.delete(chgPaymentPayment);

      }
      int opButton = busiLogConst.BIZLOG_ACTION_DELETE;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  // 全部删除
  public void deleteAllChgPaymentTail(Integer id, String pkid, String orgid,
      String ip, String name, SecurityInfo securityInfo) throws Exception,
      BusinessException {

    // 判断是否为单位版
    int isOrgEdition = securityInfo.getIsOrgEdition();

    try {
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版

        // 判断提交状态是否为未提取
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_N);
        System.out.println("isNoPickUp======" + isNoPickUp);
        // 如果存在未提交的，提示先撤销提交
        if (isNoPickUp) {
          throw new BusinessException("请先撤销提交！");
        }
        String stype = autoInfoPickDAODW.findStatus(orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_N);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("中心已提取，不能删除");
        }
      } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_N);
      }

      // 正常全部删除
      ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
      HafOperateLog hafOperateLog = new HafOperateLog();
      ChgPaymentPayment chgPaymentPayment = null;
      BusiLogConst busiLogConst = null;
      ChgPaymentTail chgPaymentTail = null;
      List deleteList = new ArrayList();

      // 删除AA319：
      chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
          .queryChgPaymentBizActivityLogByIdWuht(pkid.toString(), "1");
      chgPaymentBizActivityLogDAO.deleteWuht(chgPaymentBizActivityLog);
      // 删除AA203：
      chgPaymentTailDAO.deleteChgTail(new Integer(pkid));
      // 删除AA2023：
      chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(pkid));
      chgPaymentPaymentDAO.delete(chgPaymentPayment);

      int opButton = busiLogConst.BIZLOG_ACTION_DELETEALL;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  // 办理页面的启用
  public void useChgPaymentPayment(Pagination pagination) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = null;
    ChgPaymentPayment chgPaymentPayment1 = null;
    Emp emp = null;
    List empChgPaymentPayment = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    String chgMonth2 = null;
    BusinessException be = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    try {
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");

      String name = (String) pagination.getQueryCriterions().get("name");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String ip = (String) pagination.getQueryCriterions().get("ip");

      chgPaymentPayment1 = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          pkid.toString(), "1");
      if (chgPaymentPayment1 == null) {
        be = new BusinessException("该职工已经做过缴额调整，已为启用，请点击未启用的启用！");
      } else {
        // 更新AA002：单位缴额 职工缴额 工资基数
        // 更新AA202：变更状态=2.已启用 调整年月=界面录入的调整年月
        chgPaymentPaymentDAO.updatePayChgUse(new Integer(orgid), new Integer(
            pkid), chgMonth);

        // 插入AA319：
        chgPaymentBizActivityLog.setBizid(new Integer(pkid));
        chgPaymentBizActivityLog.setAction(new Integer(3));
        chgPaymentBizActivityLog.setOpTime(new Date());
        chgPaymentBizActivityLog.setOperator(name);
        chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);

        // 插入BA003：
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
        int opButton = busiLogConst.BIZLOG_ACTION_OPENING;
        this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  public ChgpayListAF findOrgChgpayList(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Org org = new Org();
    List orgChgPaymentPayment = null;
    ChgpayListAF chgpayListAF = new ChgpayListAF();
    String chgMonth = (String) pagination.getQueryCriterions().get("chgMonth");
    String name = (String) pagination.getQueryCriterions().get(
        "org.orgInfo.name");
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    String typetb = (String) pagination.getQueryCriterions().get("typetb");
    int count = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("SecurityInfo");

    if (typetb != null && typetb.equals("1")) {
      orgChgPaymentPayment = chgPaymentPaymentDAO
          .queryChgPaymentPaymentByCriterionsWuht(orgid, name, chgMonth,
              orderBy, order, start, pageSize, page, securityInfo);
      count = chgPaymentPaymentDAO.queryChgPaymentPaymentByCriterionsCountWuht(
          orgid, name, chgMonth, securityInfo);// 头表;
    } else {
      orgChgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentWuht(
          orgid, name, chgMonth, orderBy, order, start, pageSize, page,
          securityInfo);
      count = chgPaymentPaymentDAO.queryChgPaymentPaymentWuht(orgid, name,
          chgMonth, securityInfo);// 头表;

    }
    if (orgChgPaymentPayment != null) {
      for (int i = 0; i < orgChgPaymentPayment.size(); i++) {
        ChgPaymentPayment chgPaymentPaymentlist = (ChgPaymentPayment) orgChgPaymentPayment
            .get(i);
        // 查询单位在 status=2, paymode=2

        chgPaymentPaymentlist.setWuhtChgStatus(BusiTools.getBusiValue(Integer
            .parseInt(chgPaymentPaymentlist.getChgStatus().toString()),
            BusiConst.CHGTYPESTATUS));
        String statetype = autoInfoPickDAODW.findStatus(chgPaymentPaymentlist
            .getOrg().getId().toString(), chgPaymentPaymentlist.getId()
            .toString(), BusiConst.ORGBUSINESSTYPE_N);
        chgPaymentPaymentlist.setTemp_pick(BusiTools.getBusiValue(Integer
            .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
      }

      pagination.setNrOfElements(count);
      if (org == null) {
        org = new Org();
      }

      if (chgpayListAF == null) {
        chgpayListAF = new ChgpayListAF();
      }
      chgpayListAF.setList(orgChgPaymentPayment);

      chgpayListAF.setOrg(org);
    }
    return chgpayListAF;

  }

  public String findOrgidById(String id) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    String orgid = null;
    BusinessException be = null;
    try {

      chgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          id, "1");
      if (chgPaymentPayment == null) {

        be = new BusinessException("该职工已经做过工资基数调整，已为启用，请点击未启用的修改！");
        return null;
      }
      orgid = chgPaymentPayment.getOrg().getId().toString();
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
    return orgid;
  }

  public void deleteAllChgPaymentTailMaintain(Integer id, String ip,
      String name, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    String orgid = null;
    ChgPaymentTail chgPaymentTail = null;
    BusiLogConst busiLogConst = null;
    List deleteList = new ArrayList();

    try {
      chgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          id.toString(), "1");
      if (chgPaymentPayment == null) {
        be = new BusinessException("该职工已经做过缴额调整，已为启用，请点击未启用的删除！");
      } else {
        orgid = chgPaymentPayment.getOrg().getId().toString();

        // 判断是否为单位版
        int isOrgEdition = securityInfo.getIsOrgEdition();
        System.out.println("isOrgEdition======" + isOrgEdition);
        if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
          // 判断提交状态是否为未提取
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, id
              .toString(), BusiConst.ORGBUSINESSTYPE_N);
          System.out.println("isNoPickUp======" + isNoPickUp);
          // 如果存在未提交的，提示先撤销提交
          if (isNoPickUp) {
            throw new BusinessException("请先撤销提交！");
          }
          String stype = autoInfoPickDAODW.findStatus(orgid, id.toString(),
              BusiConst.ORGBUSINESSTYPE_N);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("中心已提取，不能删除");
          }
        } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, orgid, id.toString(),
              BusiConst.ORGBUSINESSTYPE_N);
        }

        // 删除AA319：
        chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
            .queryChgPaymentBizActivityLogByIdWuht(id.toString(), "1");
        chgPaymentBizActivityLogDAO.deleteWuht(chgPaymentBizActivityLog);
        // 删除AA203：
        // deleteList=chgPaymentTailDAO.queryChgPaymentPaymentOtherWuht(orgid,
        // null, null, null, 0, 0);
        // chgPaymentTailDAO.deleteList(deleteList);
        chgPaymentTailDAO.deleteChgTail(id);
        // 删除AA202：
        chgPaymentPaymentDAO.delete(chgPaymentPayment);

        // // 插入BA003
        int opButton = busiLogConst.BIZLOG_ACTION_DELETE;
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN;
        this
            .addhafOperateLog(name, opButton, opModel, id.toString(), ip, orgid);

      }
    } catch (Exception e) {
      // e.printStackTrace();
      // throw be;
      throw new BusinessException(e.getMessage());
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  // 维护页面的启用
  public void useChgPaymentPaymentMaintain(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = null;
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    String orgid = null;
    String chgMonth = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    try {
      String id = (String) pagination.getQueryCriterions().get("id");
      String name = (String) pagination.getQueryCriterions().get("name");
      chgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          id.toString(), "1");
      if (chgPaymentPayment == null) {
        be = new BusinessException("该职工已经做过缴额调整，已为启用，请点击未启用的启用！");
      } else {
        chgMonth = chgPaymentPayment.getChgMonth();

        orgid = chgPaymentPayment.getOrg().getId().toString();
        String ip = (String) pagination.getQueryCriterions().get("ip");

        // 更新AA002：单位缴额 职工缴额 工资基
        // 更新AA202：变更状态=2.已启用 调整年月=界面录入的调整年月
        chgPaymentPaymentDAO.updatePayChgUse(new Integer(orgid),
            new Integer(id), chgMonth);

        // 插入AA319：
        chgPaymentBizActivityLog.setBizid(new Integer(id));
        chgPaymentBizActivityLog.setAction(new Integer(3));
        chgPaymentBizActivityLog.setOpTime(new Date());
        chgPaymentBizActivityLog.setOperator(name);
        chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);

        // 插入BA003：
        int opButton = busiLogConst.BIZLOG_ACTION_OPENING;
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN;
        this
            .addhafOperateLog(name, opButton, opModel, id.toString(), ip, orgid);
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  public boolean deluseChgPaymentPaymentMaintain(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = null;
    String chgPersonHeadid = null;
    String chgOrgRateid = null;
    String chgPaymentHeadid = null;
    Emp emp = null;
    List empChgPaymentPayment = null;
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    String orgid = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    try {
      String id = (String) pagination.getQueryCriterions().get("id");
      String name = (String) pagination.getQueryCriterions().get("name");
      int chgPaymentSalaryBaseid = Integer.parseInt(id);
      String ip = (String) pagination.getQueryCriterions().get("ip");

      // 在撤消启用的时候，先判断一下状态是否为启用
      ChgPaymentPayment chgPaymentPayment1 = null;
      ChgPaymentPayment chgPaymentPayment3 = null;
      chgPaymentPayment1 = chgPaymentPaymentDAO.queryById(new Integer(id));
      if (chgPaymentPayment1 != null) {
        orgid = chgPaymentPayment1.getOrg().getId().toString();
        chgPaymentPayment3 = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
      }
      if (chgPaymentPayment3 != null) {
        be = new BusinessException("存在未启用的缴额调整，请先启用！");
      } else {
        // * 查询人员变更头表 （AA204）的的最大的id=
        // 该笔变更后是否存在其它变更判断方法：AA201、AA202、AA204中的id是否存在大于该笔变更id的
        chgPaymentPayment = chgPaymentPaymentDAO
            .queryChgPaymentPaymentByIdWuht(id, "2");
        orgid = chgPaymentPayment.getOrg().getId().toString();
        chgPersonHeadid = chgPersonHeadDAO.getMaxHeadID_WL(orgid).toString();

        if (Integer.parseInt(chgPersonHeadid) > chgPaymentSalaryBaseid) {
          be = new BusinessException("请先撤消后面的变更！");
        } else {
          // 该笔变更是否被汇缴应用判断方法：AA202中的该笔变更对应的缴存id是否为空
          chgPaymentPayment = chgPaymentPaymentDAO
              .queryChgPaymentPaymentByIdWuht(id, "2");
          if (chgPaymentPayment == null) {

            be = new BusinessException("该职工还未启用，请点启用！");

          } else {

            PaymentHead paymentHeadid = chgPaymentPayment.getPaymentHead();
            if (paymentHeadid != null) {
              be = new BusinessException("请先撤消应用该笔变更的汇缴！");
            } else {
              ChgPaymentPayment chgPaymentPayment2 = null;
              orgid = chgPaymentPayment.getOrg().getId().toString();
              chgPaymentPayment2 = chgPaymentPaymentDAO
                  .queryByCriterionsWuht(orgid);

              if (chgPaymentPayment2 != null) {
                be = new BusinessException("存在未启用的缴额调整，请先启用！");
              } else {

                orgid = chgPaymentPayment.getOrg().getId().toString();
                // 删除AA319：
                chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
                    .queryChgPaymentBizActivityLogByIdWuht(id, "3");
                chgPaymentBizActivityLogDAO
                    .deleteWuht(chgPaymentBizActivityLog);

                // 插入BA003：
                int opButton = busiLogConst.BIZLOG_ACTION_REVOCATION;
                int opModel = busiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN;
                this.addhafOperateLog(name, opButton, opModel, id.toString(),
                    ip, orgid);
                // 更新AA002：单位缴额 职工缴额 工资基数
                // 更新AA202：变更状态=1.未启用 调整年月=界面录入的调整年月
                chgPaymentPaymentDAO.updatePayChgReUse(new Integer(orgid),
                    new Integer(id));
              }
            }
          }
        }
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  // 导出
  public List queryEmpOrgList(Pagination pagination) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    BusinessException be = null;
    BusiLogConst busiLogConst = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orgId = (String) pagination.getQueryCriterions().get("org.id");
    String ip = (String) pagination.getQueryCriterions().get("ip");
    String orgName = (String) pagination.getQueryCriterions().get(
        "org.orgInfo.name");
    String chgMonth = (String) pagination.getQueryCriterions().get("chgMonth");
    String name = (String) pagination.getQueryCriterions().get("name");

    String orderArray[] = (String[]) pagination.getQueryCriterions().get(
        "orderArray");// 排序的数组

    try {

      // list=empDAO.queryChgpayEmpOrgWuht(orgId, orgName, chgMonth, orderBy,
      // order, start, pageSize);

      list = empDAO.queryChgpayEmpOrgWuhts(orgId, orgName, chgMonth,
          orderArray, order, start, pageSize);
      // 插入BA003：
      int opButton = busiLogConst.BIZLOG_ACTION_EXP;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO;

      this.addhafOperateLog(name, opButton, opModel, "0".toString(), ip, orgId);

      if (list == null || list.size() == 0) {

        be = new BusinessException("该单位下无职工！");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }

    return list;

  }

  // 导入
  public List addChgpayListBatch(List addchgpayHeadImportList,
      List addchgpayListImportList, String orgId, String chgMonth, String ip,
      String name, String date, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub

    List list = new ArrayList();
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    if (addchgpayHeadImportList.size() <= 0) {
      throw new BusinessException("导入数据为空！");
    }
    if (addchgpayListImportList.size() <= 0) {
      throw new BusinessException("导入数据为空！");
    }

    ChgpayHeadImportDTO chgpayHeadImportDTO = (ChgpayHeadImportDTO) addchgpayHeadImportList
        .get(1);

    int OrgId1 = Integer.parseInt(chgpayHeadImportDTO.getOrgId());
    String OrgId2 = OrgId1 + "";
    if (!orgId.equals(OrgId2.trim())) {
      throw new BusinessException("选择的导入文件与输入的单位编号不符！");
    }
    Org org = null;
    ;
    org = orgDAO.queryByCriterions(chgpayHeadImportDTO.getOrgId(), "2", "2",
        securityInfo);
    boolean Status1 = chgPaymentPaymentDAO.getChgStatus(new Integer(
        chgpayHeadImportDTO.getOrgId()));
    if (!Status1 || org == null) {

      org = new Org();

      throw new BusinessException("对不起，该单位不能进行导入！");
    }
    // 插入aa202：
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    // 写new Date() 有错误

    chgPaymentPayment.setBizDate(date);
    chgPaymentPayment.setChgMonth(chgMonth);
    chgPaymentPayment.setChgStatus(new Integer(1));
    chgPaymentPayment.setOrg(org);

    List list2 = new ArrayList();
    list2 = empDAO.getEmpListWuht(orgId);
    BigDecimal sumSalaryBase = new BigDecimal(0.00);
    BigDecimal sumPay = new BigDecimal(0.00);
    if (list2 != null) {
      for (int i = 0; i < list2.size(); i++) {
        Emp listemp = (Emp) list2.get(i);
        sumSalaryBase = sumSalaryBase.add(listemp.getSalaryBase());
        sumPay = sumPay.add(listemp.getOrgPay());
        sumPay = sumPay.add(listemp.getEmpPay());
      }
    }
    chgPaymentPayment.setOldPayment(sumPay);
    chgPaymentPayment.setOldSlarayBase(sumSalaryBase);
    Serializable id = chgPaymentPaymentDAO.insert(chgPaymentPayment);
    this.center_head_id = id.toString();
    // 插入aa203
    for (int i = 1; i < addchgpayListImportList.size(); i++) {
      ChgpayListImportDTO chgpayListImportDTO = (ChgpayListImportDTO) addchgpayListImportList
          .get(i);
      UtilRule utilRule = new UtilRule();

      ChgpayInfoDTO chgpayInfoDTO = new ChgpayInfoDTO();

      Pattern pNotenumber = Pattern.compile("^([0-9]{1,10})(\\.[0-9]{1,2})?$");
      Matcher mNotenumber = pNotenumber.matcher(chgpayListImportDTO
          .getSalaryBase());
      if (mNotenumber.find() == false) {
        throw new BusinessException("请录入正确工资基数！");
      } else if (chgpayListImportDTO.getSalaryBase().length() > 20) {
        throw new BusinessException("工资基数输入错误！");
      }

      Matcher mNotenumber2 = pNotenumber.matcher(chgpayListImportDTO
          .getOrgPay());
      if (mNotenumber2.find() == false) {
        throw new BusinessException("请录入正确缴额！");
      } else if (chgpayListImportDTO.getOrgPay().length() > 20) {
        throw new BusinessException("缴额输入错误！");
      }

      Matcher mNotenumber3 = pNotenumber.matcher(chgpayListImportDTO
          .getEmpPay());
      if (mNotenumber3.find() == false) {
        throw new BusinessException("请录入正确缴额！");
      } else if (chgpayListImportDTO.getEmpPay().length() > 20) {
        throw new BusinessException("缴额输入错误！");
      }

      if (chgpayListImportDTO.getOrgPay() == null
          || chgpayListImportDTO.getOrgPay().equals("")
          || chgpayListImportDTO.getEmpPay() == null
          || chgpayListImportDTO.getEmpPay().equals("")) {
        throw new BusinessException("导入的缴额不对");
      } else if (utilRule.moneyRule(chgpayListImportDTO.getEmpPay(), 10, 2) == false) {
        chgpayInfoDTO.setEmpPay(chgpayListImportDTO.getEmpPay());
        list.add(chgpayInfoDTO);
        throw new BusinessException("导入的缴额不对");
      }
      if (utilRule.moneyRule(chgpayListImportDTO.getOrgPay(), 10, 2) == false) {
        chgpayInfoDTO.setOrgPay(chgpayListImportDTO.getOrgPay());
        list.add(chgpayInfoDTO);
        throw new BusinessException("导入的缴额不对");
      }
      if (chgpayListImportDTO.getSalaryBase() == null
          || chgpayListImportDTO.getSalaryBase().equals("")) {
        throw new BusinessException("导入的工资基数不对");
      } else if (utilRule.moneyRule(chgpayListImportDTO.getSalaryBase(), 10, 2) == false) {
        chgpayInfoDTO.setSalaryBase(chgpayListImportDTO.getSalaryBase());
        list.add(chgpayInfoDTO);
        throw new BusinessException("导入的工资基数不对");
      }

      ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
      ChgPaymentTail chgPaymentTail1 = new ChgPaymentTail();
      Emp emp = empDAO.queryByCriterions(chgpayListImportDTO.getEmpId(), orgId);
      if (emp == null) {
        throw new BusinessException("导入的职工不存在于本单位");
      }
      chgPaymentTail1 = chgPaymentTailDAO.queryChgPaymentSalaryBaseWUHT(
          chgpayListImportDTO.getEmpId(), orgId);
      if (chgPaymentTail1 != null) {
        throw new BusinessException("该职工已经做过缴额调整！");
      }

      chgPaymentTail.setChgPaymentHead(chgPaymentPayment);
      chgPaymentTail.setEmp(emp);
      chgPaymentTail.setPayStatus(new Integer(emp.getPayStatus().toString()));

      chgPaymentTail.setEmpId(new Integer(chgpayListImportDTO.getEmpId()));
      chgPaymentTail.setOldOrgPay(new BigDecimal(chgpayListImportDTO
          .getOldOrgPay()));
      chgPaymentTail.setOldEmpPay(new BigDecimal(chgpayListImportDTO
          .getOldEmpPay()));

      chgPaymentTail.setOrgPay(new BigDecimal(chgpayListImportDTO.getOrgPay()));
      chgPaymentTail.setEmpPay(new BigDecimal(chgpayListImportDTO.getEmpPay()));
      chgPaymentTail.setSalaryBase(new BigDecimal(chgpayListImportDTO
          .getSalaryBase()));
      chgPaymentTailDAO.insert(chgPaymentTail);

    }
    // 插入AA319：
    chgPaymentBizActivityLog.setBizid(new Integer(chgPaymentPayment.getId()
        .toString()));
    chgPaymentBizActivityLog.setAction(new Integer(BusiConst.BUSINESSSTATE_1));
    chgPaymentBizActivityLog.setOpTime(new Date());
    chgPaymentBizActivityLog.setOperator(name);
    chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);
    // 插入BA003：
    String pkid = chgPaymentPayment.getId().toString();
    BusiLogConst busiLogConst = null;
    int opButton = busiLogConst.BIZLOG_ACTION_EXP;
    int opModel = busiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO;

    this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgId);
    return list;
  }

  // // 插入BA003：
  public void addhafOperateLog(String name, int opButton, int opModel,
      String pkid, String ip, String orgid) throws BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;

    try {
      // 插入BA003：
      hafOperateLog
          .setOpSys(new Integer(busiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(opModel));
      hafOperateLog.setOpButton(Integer.toString(opButton));
      hafOperateLog.setOpBizId(new Integer(pkid));
      hafOperateLog.setOperator(name);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgid));
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw new BusinessException("");
      }
    }

  }

  public ChgpayListAF findChgpayWindowList(Pagination pagination)
      throws Exception, BusinessException {
    ChgpayListAF chgpayListAF = new ChgpayListAF();
    ChgPaymentPayment chgPaymentPayment = null;
    List empChgpayChangements = null;
    Emp emp = null;
    try {

      String id = (String) pagination.getQueryCriterions().get("pk_id");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(id));
      Serializable orgid = chgPaymentPayment.getOrg().getId();
      chgpayListAF.getOrg().setId(orgid);
      chgpayListAF.getOrg().getOrgInfo().setName(
          chgPaymentPayment.getOrg().getOrgInfo().getName());

      chgpayListAF.setChgMonth(chgPaymentPayment.getChgMonth());
      chgpayListAF.setOldPaymenSum(chgPaymentPayment.getOldPaymenSum());
      chgpayListAF.setOldOrgPaySum(chgPaymentPayment.getOldOrgPaySum());
      chgpayListAF.setOrgPaySum(chgPaymentPayment.getOrgPaySum());
      chgpayListAF.setOldEmpPaySum(chgPaymentPayment.getOldEmpPaySum());
      chgpayListAF.setEmpPaySum(chgPaymentPayment.getEmpPaySum());
      chgpayListAF.setOldPayment(chgPaymentPayment.getOldPayment());
      chgpayListAF.setPaySum(chgPaymentPayment.getPaySum());

      empChgpayChangements = chgPaymentTailDAO
          .queryChgPaymentTailByChgPaymentPayment(id, orderBy, order, start,
              pageSize);
      // 循环列表中的信息
      for (int i = 0; i < empChgpayChangements.size(); i++) {
        ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) empChgpayChangements
            .get(i);

        emp = empDAO.queryByCriterions(
            chgPaymentTaillist.getEmpId().toString(), orgid.toString());
        chgPaymentTaillist.setEmp(emp);

      }
      int count = chgPaymentTailDAO.countChgPaymentTailByChgPaymentPayment(id);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      // TODO: handle exception
    }

    chgpayListAF.setList(empChgpayChangements);

    return chgpayListAF;
  }

  /**
   * 数据提取
   * 
   * @author 徐世龙
   * @2008-02-26
   * @param orgid
   * @param chgMonth
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void pickUp(String orgid, String chgMonth, SecurityInfo securityInfo)
      throws Exception, BusinessException {

    try {
      int count = autoInfoPickDAO.queryNoPickUpListbyOrgid(orgid);
      if (count == 0) {
        throw new BusinessException("该单位不存在未提取的记录！");
      }
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      String date = securityInfo.getUserInfo().getBizDate();
      // 根据orgid查询pay_head_id不为空的orgheadid 和type
      Object[] obj = autoInfoPickDAO.queryOrgHeadidAndType(orgid);

      if (obj == null) {
        // PAY_HEAD_ID=null，取ORG_HEAD_ID最小的记录，得到ORG_HEAD_ID和TYPE
        obj = autoInfoPickDAO.findOrgHeadidAndType(orgid);
      }
      if (obj != null) {
        if ((obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_N))) {
          // 类型为BusiConst.ORGBUSINESSTYPE_N(缴额调整)
          String orgheadID = autoInfoPickDAO.findOrgHeadid(orgid,
              BusiConst.ORGBUSINESSTYPE_N, BusiConst.OC_NOT_PICK_UP);
          // 查询导入用的list
          List addchgslarybaseHeadImportList = chgPersonHeadDAODW
              .findAddchgpayListImportList(orgid);
          List addchgslarybaseListImportList = chgPaymentPaymentDAODW
              .findAddpaymentListImportList(orgheadID);

          // 调用导入方法
          addChgpayListBatch(addchgslarybaseHeadImportList,
              addchgslarybaseListImportList, orgid, chgMonth, ip, name, date,
              securityInfo);
          // 更新da001
          autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP,
              center_head_id, new Date().toString(), orgid, orgheadID,
              BusiConst.ORGBUSINESSTYPE_N);
          System.out.println(center_head_id + "zhongxiID");
          // 插入BA003：
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO));
          hafOperateLog.setOpButton(Integer
              .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
          hafOperateLog.setOpBizId(new Integer(center_head_id));
          hafOperateLog.setOperator(name);
          hafOperateLog.setOpIp(ip);
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOrgId(new Integer(orgid));
          hafOperateLogDAO.insert(hafOperateLog);
        } else {
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_M)) {
            throw new BusinessException("存在未提取的工资基数调整变更！");
          }
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_O)) {
            throw new BusinessException("存在未提取的人员变更！");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(e.getMessage());
    }
  }

  public PaymentHeadDAO getPaymentHeadDAO() {
    return paymentHeadDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public String queryOrgidByChgPaymentHeadID(String chgPaymentHeadID)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentHead chgPaymentHead = new ChgPaymentHead();
    String orgid = "";
    try {
      chgPaymentHead = chgPaymentHeadDAO
          .queryById(new Integer(chgPaymentHeadID));
      orgid = chgPaymentHead.getOrg().getId().toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgid;
  }

  /**
   * 办理和维护页面的提交
   * 
   * @author 徐世龙
   * @2008-02-26
   * @param id
   * @param orgid
   * @param securityInfo
   * @param flag
   * @throws Exception
   * @throws BusinessException
   */
  public void PickInChgPaymentTailMaintain(String id, String orgid,
      SecurityInfo securityInfo, String flag) throws Exception,
      BusinessException {

    try {

      // 判断DA001中是否存在提交记录
      boolean isNoPickIn = autoInfoPickDAODW.isNOPickIn(orgid, id,
          BusiConst.ORGBUSINESSTYPE_N);
      if (isNoPickIn) {
        throw new BusinessException("该笔业务已提交！");
      } else {
        ChgPaymentHead chgPaymentHead = chgPaymentHeadDAO
            .queryById(new Integer(id));
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(orgid));
        autoInfoPick.setOrgHeadId(new Integer(id));
        autoInfoPick.setCenterHeadId(null);
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_N);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPick.setOrgBizDate(new Date());
        if (chgPaymentHead.getPaymentHead() != null) {
          autoInfoPick.setPayHeadId(new Integer(chgPaymentHead.getId()
              .toString()));
        } else {
          autoInfoPick.setPayHeadId(null);
        }

        autoInfoPickDAODW.insert(autoInfoPick);
        // 插入BA003：
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("t1")) {
          // flag=t1为办理的
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO));
        } else {
          // 维护的
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN));
        }
        // 业务日志表的操作行为—提交数据
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
        hafOperateLog.setOpBizId(new Integer(id));// AA202.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgid));
        hafOperateLogDAO.insert(hafOperateLog);
      }

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  /**
   * 办理和维护的撤消提交数据
   * 
   * @author 徐世龙
   * @2008-02-26
   * @param id
   * @param orgid
   * @param securityInfo
   * @param flag
   * @throws Exception
   * @throws BusinessException
   */
  public void removePickInChgPaymentTailMaintain(String id, String orgid,
      SecurityInfo securityInfo, String flag) throws Exception,
      BusinessException {

    try {
      String status = autoInfoPickDAODW.findStatus(orgid, id,
          BusiConst.ORGBUSINESSTYPE_N);
      // 判断DA001中该笔业务的状态是否为未提取
      if (status.equals(BusiConst.OC_NOT_PICK_UP)) {

        // 删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgid, id,
            BusiConst.ORGBUSINESSTYPE_N);
        // 插入BA003：
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("c1")) {
          // 办理页面撤消提交
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO));
        } else {
          // 维护页面撤消提交
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN));
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
        hafOperateLog.setOpBizId(new Integer(id));// AA202.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgid));
        hafOperateLogDAO.insert(hafOperateLog);
      } else {
        if (status.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("该业务已被中心提取，不可以撤销！");
        }
        if (status.equals(BusiConst.OC_PICK_UP_OVER)) {
          throw new BusinessException("该笔业务没有提交，不可以撤销！");
        }
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

}
