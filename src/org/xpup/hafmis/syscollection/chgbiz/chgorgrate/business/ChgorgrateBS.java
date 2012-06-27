package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateTailDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgChgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgEditionDAO;
import org.xpup.hafmis.syscollection.common.daoDW.ChgOrgRateDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.OrgDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRateTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * @author 王玲 2007-6-27
 */
public class ChgorgrateBS implements IChgorgrateBS {

  private ChgOrgRateDAO chgOrgRateDAO = null;

  private ChgOrgRateDAODW chgOrgRateDAODW = null;

  private OrgChgDAO orgChgDAO = null;

  private OrgDAO orgDAO = null;

  private EmpDAO empDAO = null;

  private ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;

  private ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;

  private ChgPersonHeadDAO chgPersonHeadDAO = null;

  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private ChgOrgRateBizActivityLogDAO chgOrgRateBizActivityLogDAO = null;

  private OrgEditionDAO orgEditionDAO = null;// 中心数据库da002

  private OrgDAODW orgDAODW = null; // 单位库aa001

  private EmpDAODW empDAODW = null;// 单位数据库aa002

  private ChgOrgRateTailDAO chgOrgRateTailDAO = null;

  public void setChgOrgRateTailDAO(ChgOrgRateTailDAO chgOrgRateTailDAO) {
    this.chgOrgRateTailDAO = chgOrgRateTailDAO;
  }

  public void setOrgChgDAO(OrgChgDAO orgChgDAO) {
    this.orgChgDAO = orgChgDAO;
  }

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setChgOrgRateDAODW(ChgOrgRateDAODW chgOrgRateDAODW) {
    this.chgOrgRateDAODW = chgOrgRateDAODW;
  }

  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
  }

  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setChgOrgRateBizActivityLogDAO(
      ChgOrgRateBizActivityLogDAO chgOrgRateBizActivityLogDAO) {
    this.chgOrgRateBizActivityLogDAO = chgOrgRateBizActivityLogDAO;
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
  }

  public void setOrgDAODW(OrgDAODW orgDAODW) {
    this.orgDAODW = orgDAODW;
  }

  public void setOrgEditionDAO(OrgEditionDAO orgEditionDAO) {
    this.orgEditionDAO = orgEditionDAO;
  }

  /**
   * 根据单位ID 查询单位应缴金额
   */
  public int queryPersonCountByOrgID(String orgID) throws BusinessException,
      Exception {
    return empDAO.queryPersonCountByOrgID(orgID);
  }

  public void saveOrgChg(OrgChg orgChg) throws BusinessException {
    orgChgDAO.insert(orgChg);
  }

  /**
   * 判断该单位是否是按率缴存
   */
  public ChgOrgRate checkOrgMessage(String orgID, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    // TODO Auto-generated method stub
    ChgOrgRate chgOrgRate = null;
    try {
      Org org = null;
      org = orgDAO.queryByCriterions(orgID, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      } else if (org.getPayMode().equals("2")) {
        throw new BusinessException("该单位不是按率缴存，不能进行汇缴比例调整业务！！");
      }

      // AA201中是否存该单位未被启用的汇缴比例信息
      String status = "1";
      chgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(orgID, status);
      if (chgOrgRate == null) {
        // AA001中是否存在满足以下条件的记录
        Org returnOrg = orgDAO.getOrgByIDStatus_WL(orgID);
        if (returnOrg == null) {
          throw new BusinessException("该单位不能进行汇缴比例调整！");
        } else {
          // 是否不存在未启用的其它类型的变更
          boolean chgRateStratus = chgPersonHeadDAO.getChgStatus(new Integer(
              orgID));
          if (chgRateStratus == false) {
            throw new BusinessException("该单位有未启用的人员变更!!");
          }
          boolean chgPaymentPaymentStratus = chgPaymentPaymentDAO
              .getChgStatus(new Integer(orgID));
          if (chgPaymentPaymentStratus == false) {
            throw new BusinessException("该单位有未启用的缴额调整!!");
          }
          boolean chgPaymentSalaryBaseStratus = chgPaymentSalaryBaseDAO
              .getChgStatus(new Integer(orgID));
          if (chgPaymentSalaryBaseStratus == false) {
            throw new BusinessException("该单位有未启用的工资基数调整!!");
          }
        }
      }

      // 可以进行汇缴比例调整业务的，进行页面显示信息的查询
      // 查询变更年月，先查AA201，无就查AA302，无再查AA001的初缴年月
      String date = null;
      Org returnOrg = orgDAO.queryById(new Integer(orgID));
      if (chgOrgRate != null) {

        date = chgOrgRate.getChgMonth();
        if (date == null || date.equals("")) {
          date = monthPaymentHeadDAO.getOrgMonthPayment(orgID);
          if (date == null || date.equals("")) {
            date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
          } else {
            date = BusiTools.addMonth(date, 1);
          }
        }
      } else {
        chgOrgRate = new ChgOrgRate();
        chgOrgRate.setId("");

        date = monthPaymentHeadDAO.getOrgMonthPayment(orgID);
        if (date == null || date.equals("")) {
          date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
        } else {
          date = BusiTools.addMonth(date, 1);
        }
        chgOrgRate.setOrg(returnOrg);
        chgOrgRate.setPreOrgRate(returnOrg.getOrgRate());
        chgOrgRate.setPreEmpRate(returnOrg.getEmpRate());
        chgOrgRate.setOrgRate(new BigDecimal(0.00));
        chgOrgRate.setEmpRate(new BigDecimal(0.00));

        BigDecimal oldPayment = empDAO.getOldPayment_WL(returnOrg.getId()
            .toString());
        if (oldPayment == null) {
          chgOrgRate.setPreSumPay(new BigDecimal(0.00));
        } else {
          chgOrgRate.setPreSumPay(oldPayment);
        }

      }
      chgOrgRate.setChgMonth(date);// 存放该单位的变更年月

      BigDecimal empSalary = empDAO
          .queryEmpSalary(returnOrg.getId().toString());// 该单位下的职工工资基数
      chgOrgRate.setSalarybase(empSalary);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
    // yqf 20080927 改 单位编号补10位
    Integer tempId = (Integer) chgOrgRate.getOrg().getId();
    String sid = BusiTools.convertTenNumber(tempId.toString());
    chgOrgRate.getOrg().setSid(sid);
    return chgOrgRate;
  }

  /**
   * 做汇缴比例调整时：查询变更年月，先查AA201，无就查AA302，无再查AA001的初缴年月
   */
  public String getChgMonth(ChgOrgRate chgOrgRate, String orgID)
      throws BusinessException, Exception {

    String date = null;
    date = chgOrgRate.getChgMonth();
    if (date == null || date.equals("")) {
      date = monthPaymentHeadDAO.getOrgMonthPayment(orgID);
      if (date == null || date.equals("")) {
        Org returnOrg = orgDAO.queryById(new Integer(orgID));
        date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
      } else {
        date = BusiTools.addMonth(date, 1);
      }
    }
    return date;

  }

  /**
   * 根据单位ID 查询单位信息
   */
  public Org queryOrgByorgID(String orgID) throws BusinessException, Exception {
    // TODO Auto-generated method stub

    Org org = null;
    org = orgDAO.getOrg_WL(orgID);
    if (org == null) {
      return null;
    }
    BigDecimal empSalary = empDAO.queryEmpSalary(org.getId().toString());// 该单位下的职工工资基数
    org.setTemp_salary(empSalary);
    // yqf 20080927 改 单位编号补10位
    String tempid = org.getId().toString();
    String sid = BusiTools.convertTenNumber(tempid);
    org.setSid(sid);
    return org;
  }

  /**
   * 根据单位ID 查询单位信息
   */
  public Org queryOrgByorgIDYg(String orgID, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    // TODO Auto-generated method stub

    Org org = null;
    org = orgDAO.getOrg_YG(orgID, securityInfo);
    if (org == null) {
      return null;
    }
    BigDecimal empSalary = empDAO.queryEmpSalary(org.getId().toString());// 该单位下的职工工资基数
    org.setTemp_salary(empSalary);
    // yqf 20080927 改 单位编号补10位
    String tempid = org.getId().toString();
    String sid = BusiTools.convertTenNumber(tempid);
    org.setSid(sid);
    return org;
  }

  /**
   * 根据单位ID 查询单位应缴金额
   */
  public BigDecimal querySumPayByOrgID(String orgID) throws BusinessException,
      Exception {
    // TODO Auto-generated method stub

    BigDecimal sumPay = new BigDecimal(0.00);
    sumPay = empDAO.querySumPayByOrgID_WL(orgID);
    if (sumPay == null) {
      sumPay = new BigDecimal(0.00);
    }
    return sumPay;
  }

  /**
   * 根据AA201，进行数据录入
   */
  public BigDecimal saveChgOrgRate(ChgOrgRate chgOrgRate,
      SecurityInfo securityInfo) throws BusinessException, Exception {

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    String chgOrgRateHeadID = "";
    ChgOrgRate returnChgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(chgOrgRate
        .getOrg().getId().toString(), "1");
    // BigDecimal empSalary =
    // empDAO.queryEmpSalary(chgOrgRate.getOrg().getId().toString());//该单位下的职工工资基数

    Org returnOrg = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
    // 缴存精度ID:payPrecision
    int payPrecision = Integer.parseInt(returnOrg.getPayPrecision().toString());
    // 利用缴存精度
    ArithmeticInterface arithmeticInterface = ArithmeticFactory.getArithmetic()
        .getArithmeticDAO(payPrecision);
    List empList = empDAO.getExportData_wsh(Integer.parseInt(chgOrgRate
        .getOrg().getId().toString()));
    BigDecimal orgPay = new BigDecimal(0.00);
    BigDecimal empPay = new BigDecimal(0.00);

    if (returnChgOrgRate != null) {// 修改AA201
      returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());
      returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());
      returnChgOrgRate.setPreOrgRate(chgOrgRate.getPreOrgRate());
      returnChgOrgRate.setPreEmpRate(chgOrgRate.getPreEmpRate());

      try {
        // 计算变更后缴额
        List list = this.queryEmpSalaryByTIAOJIAN(returnChgOrgRate.getOrg()
            .getId().toString());
        for (int i = 0; i < list.size(); i++) {
          BigDecimal salarybase = (BigDecimal) list.get(i);
          orgPay = orgPay.add(arithmeticInterface.getPay(salarybase,
              returnChgOrgRate.getOrgRate()));
          empPay = empPay.add(arithmeticInterface.getPay(salarybase,
              returnChgOrgRate.getEmpRate()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      returnChgOrgRate.setOrgPay(orgPay);// 调整后单位应缴额
      returnChgOrgRate.setEmpPay(empPay);// 调整后职工应缴额

      returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());// 单位缴率
      returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());// 职工缴率
      returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());// 调整年月
      chgOrgRateHeadID = chgOrgRateDAO.insert(returnChgOrgRate).toString();
      chgOrgRateTailDAO.updateChgOrgRateTail_wsh(returnChgOrgRate.getId()
          .toString(), returnChgOrgRate.getOrgRate().toString(),
          returnChgOrgRate.getEmpRate().toString(), null, null, securityInfo);
      // if(empList!=null){
      // for (int i = 0; i < empList.size(); i++) {
      // ChgOrgRateTail chgOrgRateTail=new ChgOrgRateTail();
      // chgOrgRateTail.setChgOrgRate(returnChgOrgRate);
      // chgOrgRateTail.setEmp((Emp)empList.get(i));
      // chgOrgRateTail.setEmpId(((Emp)empList.get(i)).getEmpId());
      // chgOrgRateTail.setPayStatus(Integer.valueOf(((Emp)empList.get(i)).getPayStatus().toString()));
      // chgOrgRateTail.setOldEmpRate(chgOrgRate.getPreEmpRate());
      // chgOrgRateTail.setOldOrgRate(chgOrgRate.getPreOrgRate());
      // chgOrgRateTail.setEmpRate(chgOrgRate.getEmpRate());
      // chgOrgRateTail.setOrgRate(chgOrgRate.getOrgRate());
      // chgOrgRateTailDAO.insert(chgOrgRateTail);
      // }
      // }
    } else {// 添加AA201
      returnChgOrgRate = new ChgOrgRate();
      // 单位编号
      Org org = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
      returnChgOrgRate.setOrg(org);
      Integer count = empDAO.queryEmpCount(chgOrgRate.getOrg().getId()
          .toString());// 调整人数
      returnChgOrgRate.setChgPersonCount(count);
      returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());
      returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());
      returnChgOrgRate.setPreOrgRate(org.getOrgRate());
      returnChgOrgRate.setPreEmpRate(org.getEmpRate());
      BigDecimal oldOrgPay = empDAO.queryOrgpay(chgOrgRate.getOrg().getId()
          .toString());// 调整前单位下的单位应缴额
      BigDecimal oldEmpPay = empDAO.queryEmppay(chgOrgRate.getOrg().getId()
          .toString());// 调整前单位下的职工应缴额
      returnChgOrgRate.setOldOrgPay(oldOrgPay);
      returnChgOrgRate.setOldEmpPay(oldEmpPay);

      try {
        // 计算变更后缴额
        List list = this.queryEmpSalaryByTIAOJIAN(org.getId().toString());
        for (int i = 0; i < list.size(); i++) {
          BigDecimal salarybase = (BigDecimal) list.get(i);
          orgPay = orgPay.add(arithmeticInterface.getPay(salarybase, chgOrgRate
              .getOrgRate()));
          empPay = empPay.add(arithmeticInterface.getPay(salarybase, chgOrgRate
              .getEmpRate()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      returnChgOrgRate.setOrgPay(orgPay);// 调整后单位应缴额
      returnChgOrgRate.setEmpPay(empPay);// 调整后职工应缴额

      returnChgOrgRate.setChgStatus(new Integer(1));// 变更状态：未启用
      returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());// 调整年月
      returnChgOrgRate.setBizDate(securityInfo.getUserInfo().getBizDate());// 业务日期
      chgOrgRateHeadID = chgOrgRateDAO.insert(returnChgOrgRate).toString();
      if (empList != null) {
        for (int i = 0; i < empList.size(); i++) {
          ChgOrgRateTail chgOrgRateTail = new ChgOrgRateTail();
          chgOrgRateTail.setChgOrgRate(returnChgOrgRate);
          chgOrgRateTail.setEmp((Emp) empList.get(i));
          chgOrgRateTail.setEmpId(((Emp) empList.get(i)).getEmpId());
          chgOrgRateTail.setPayStatus(Integer.valueOf(((Emp) empList.get(i))
              .getPayStatus().toString()));
          chgOrgRateTail.setSalaryBase(((Emp) empList.get(i)).getSalaryBase());
          chgOrgRateTail.setOldEmpPay(((Emp) empList.get(i)).getEmpPay());
          chgOrgRateTail.setOldOrgPay(((Emp) empList.get(i)).getOrgPay());
          chgOrgRateTail.setEmpPay(arithmeticInterface.getPay(((Emp) empList
              .get(i)).getSalaryBase(), chgOrgRate.getEmpRate()));
          chgOrgRateTail.setOrgPay(arithmeticInterface.getPay(((Emp) empList
              .get(i)).getSalaryBase(), chgOrgRate.getOrgRate()));

          chgOrgRateTail.setOldEmpRate(chgOrgRate.getPreEmpRate());
          chgOrgRateTail.setOldOrgRate(chgOrgRate.getPreOrgRate());
          chgOrgRateTail.setEmpRate(chgOrgRate.getEmpRate());
          chgOrgRateTail.setOrgRate(chgOrgRate.getOrgRate());
          chgOrgRateTailDAO.insert(chgOrgRateTail);
        }
      }
      // 插入AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = new ChgOrgRateBizActivityLog();
      chgOrgRateBizActivityLog.setBizid(new Integer(chgOrgRateHeadID));
      chgOrgRateBizActivityLog
          .setAction(new Integer(BusiConst.BUSINESSSTATE_2));
      chgOrgRateBizActivityLog.setOpTime(new Date());
      chgOrgRateBizActivityLog.setOperator(oper);
      chgOrgRateBizActivityLogDAO.insert(chgOrgRateBizActivityLog);

    }

    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(chgOrgRateHeadID));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(returnChgOrgRate.getOrg().getId()
        .toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);

    return returnChgOrgRate.getOrgPay().add(returnChgOrgRate.getEmpPay());
  }

  /**
   * 根据AA201，进行数据修改
   */
  public BigDecimal updateChgOrgRate(ChgOrgRate chgOrgRate,
      SecurityInfo securityInfo) throws BusinessException, Exception {

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    String chgOrgRateHeadID = "";
    ChgOrgRate returnChgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(chgOrgRate
        .getOrg().getId().toString(), "1");
    BigDecimal empSalary = empDAO.queryEmpSalary(chgOrgRate.getOrg().getId()
        .toString());// 该单位下的职工工资基数

    Org returnOrg = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
    // 缴存精度ID:payPrecision
    int payPrecision = Integer.parseInt(returnOrg.getPayPrecision().toString());
    // 利用缴存精度
    ArithmeticInterface arithmeticInterface = ArithmeticFactory.getArithmetic()
        .getArithmeticDAO(payPrecision);

    // 修改AA201
    returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());
    returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());
    returnChgOrgRate.setPreOrgRate(chgOrgRate.getPreOrgRate());
    returnChgOrgRate.setPreEmpRate(chgOrgRate.getPreEmpRate());
    returnChgOrgRate.setOrgPay(arithmeticInterface.getPay(empSalary, chgOrgRate
        .getOrgRate()));// 调整后单位应缴额
    returnChgOrgRate.setEmpPay(arithmeticInterface.getPay(empSalary, chgOrgRate
        .getEmpRate()));// 调整后职工应缴额
    returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());// 单位缴率
    returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());// 职工缴率
    returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());// 调整年月
    chgOrgRateHeadID = chgOrgRateDAO.insert(returnChgOrgRate).toString();

    chgOrgRateTailDAO.updateChgOrgRateTail_wsh(returnChgOrgRate.getId()
        .toString(), returnChgOrgRate.getOrgRate().toString(), returnChgOrgRate
        .getEmpRate().toString(), null, null, securityInfo);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGRATE_MAINTAIN);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
    hafOperateLog.setOpBizId(new Integer(chgOrgRateHeadID));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(returnChgOrgRate.getOrg().getId()
        .toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);

    return returnChgOrgRate.getOrgPay().add(returnChgOrgRate.getEmpPay());
  }

  /**
   * 单选删除
   */
  public void deleteChgOrgRate(Integer id, SecurityInfo securityInfo)
      throws BusinessException {
    try {

      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();

      // 删除AA201
      chgOrgRateTailDAO.deleteChgTail(id);
      ChgOrgRate chgOrgRate = chgOrgRateDAO.queryById(id);
      chgOrgRateDAO.delete_WL(chgOrgRate);

      // 删除AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = chgOrgRateBizActivityLogDAO
          .queryChgOrgRateBiz_WL(chgOrgRate.getId().toString(), ""
              + BusiConst.BUSINESSSTATE_2);
      chgOrgRateBizActivityLogDAO.delete_WL(chgOrgRateBizActivityLog);

      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      hafOperateLog.setOpBizId(new Integer(chgOrgRate.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog
          .setOrgId(new Integer(chgOrgRate.getOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("删除失败!");
    }
  }

  /**
   * 进行数据启用
   */
  // 吴洪涛修改2008－3－18 把启用中的插入单位版的信息删掉
  public void useChgOrgRate(ChgOrgRate chgOrgRate, SecurityInfo securityInfo)
      throws BusinessException, Exception {

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    ChgOrgRate returnChgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(chgOrgRate
        .getOrg().getId().toString(), "1");
    try {
      // 判断维护中的启用要判断该笔记录的状态是否等于1，若不等于1，提示该笔业务已启用
      if (returnChgOrgRate == null) {
        throw new BusinessException("该笔业务已启用！！");
      }

      // 更新AA201
      returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());
      returnChgOrgRate.setChgStatus(new Integer(2));
      // String id=chgOrgRateDAO.insert(returnChgOrgRate).toString();

      // 更新AA001
      Org org = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
      org.setOrgRate(chgOrgRate.getOrgRate());
      org.setEmpRate(chgOrgRate.getEmpRate());
      orgDAO.insert(org);

      // 更新AA002
      List list = empDAO.getEmpList_WL(chgOrgRate.getOrg().getId().toString());
      Emp emp = null;

      // 缴存精度ID:payPrecision
      int payPrecision = Integer.parseInt(org.getPayPrecision().toString());
      // 利用缴存精度
      ArithmeticInterface arithmeticInterface = ArithmeticFactory
          .getArithmetic().getArithmeticDAO(payPrecision);
      int count = 0;
      BigDecimal money = new BigDecimal("0.00");
      for (int i = 0; i < list.size(); i++) {

        emp = (Emp) list.get(i);
        emp.setOrgPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getOrgRate()));
        emp.setEmpPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getEmpRate()));
        empDAO.insert(emp);
        if (emp.getPayStatus().compareTo(new BigDecimal("1")) == 0
            || emp.getPayStatus().compareTo(new BigDecimal("3")) == 0
            || emp.getPayStatus().compareTo(new BigDecimal("4")) == 0) {
          count++;
          money = money.add(emp.getEmpPay().add(emp.getOrgPay()));
        }
      }
      returnChgOrgRate.setReserveaA(String.valueOf(count));
      returnChgOrgRate.setReserveaB(money.toString());
      chgOrgRateDAO.insert(returnChgOrgRate);
      // 插入AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = new ChgOrgRateBizActivityLog();
      chgOrgRateBizActivityLog.setBizid(new Integer(returnChgOrgRate.getId()
          .toString()));
      chgOrgRateBizActivityLog
          .setAction(new Integer(BusiConst.BUSINESSSTATE_3));
      chgOrgRateBizActivityLog.setOpTime(new Date());
      chgOrgRateBizActivityLog.setOperator(oper);
      chgOrgRateBizActivityLogDAO.insert(chgOrgRateBizActivityLog);

      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_OPENING);
      hafOperateLog
          .setOpBizId(new Integer(returnChgOrgRate.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(returnChgOrgRate.getOrg().getId()
          .toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);
      // 单位版
      // int isOrgEdition = securityInfo.getIsOrgEdition();
      // if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER)// 为中心版
      // {
      // // 判断是否存在单位版
      // boolean flag = orgEditionDAO.findIsOrg(chgOrgRate.getOrg().getId()
      // .toString());
      // // flag为true存在单位版
      // if (flag) {
      // ChgOrgRate returnChgOrgRate2=new ChgOrgRate();
      //      
      //
      // returnChgOrgRate2.setOrg(returnChgOrgRate.getOrg()) ;
      // returnChgOrgRate2.setChgMonth(returnChgOrgRate.getChgMonth()) ;
      // returnChgOrgRate2.setOrgRate(returnChgOrgRate.getOrgRate()) ;
      // returnChgOrgRate2.setEmpRate(returnChgOrgRate.getEmpRate()) ;
      // returnChgOrgRate2.setChgPersonCount(returnChgOrgRate.getChgPersonCount())
      // ;
      // returnChgOrgRate2.setOldOrgPay(returnChgOrgRate.getOldOrgPay()) ;
      // returnChgOrgRate2.setOldEmpPay(returnChgOrgRate.getOldEmpPay()) ;
      // returnChgOrgRate2.setOrgPay(returnChgOrgRate.getOrgPay()) ;
      // returnChgOrgRate2.setEmpPay(returnChgOrgRate.getEmpPay()) ;
      // returnChgOrgRate2.setBizDate(returnChgOrgRate.getBizDate()) ;
      // returnChgOrgRate2.setChgStatus(returnChgOrgRate.getChgStatus()) ;
      // returnChgOrgRate2.setPaymentHead(returnChgOrgRate.getPaymentHead()) ;
      // returnChgOrgRate2.setReserveaA(returnChgOrgRate.getReserveaA()) ;
      // returnChgOrgRate2.setReserveaB(returnChgOrgRate.getReserveaB()) ;
      // returnChgOrgRate2.setReserveaC(returnChgOrgRate.getReserveaC()) ;
      // returnChgOrgRate2.setPreEmpRate(returnChgOrgRate.getPreEmpRate()) ;
      // returnChgOrgRate2.setPreOrgRate(returnChgOrgRate.getPreOrgRate()) ;
      // returnChgOrgRate2.setPreSumPay(returnChgOrgRate.getPreSumPay()) ;
      // returnChgOrgRate2.setSumPay(returnChgOrgRate.getSumPay()) ;
      // returnChgOrgRate2.setTemp_chgStatus(returnChgOrgRate.getTemp_chgStatus())
      // ;
      // returnChgOrgRate2.setSalarybase(returnChgOrgRate.getSalarybase()) ;
      // returnChgOrgRate2.setSumPreSumPay(returnChgOrgRate.getSumPreSumPay()) ;
      // returnChgOrgRate2.setSumSumPay(returnChgOrgRate.getSumSumPay()) ;
      // returnChgOrgRate2.setChgStatus_(returnChgOrgRate.getChgStatus_()) ;
      // returnChgOrgRate2.setRate(returnChgOrgRate.getRate()) ;
      // returnChgOrgRate2.setRate_(returnChgOrgRate.getRate_());
      //
      // System.out.println(returnChgOrgRate2+"----------------returnChgOrgRate2");
      // System.out.println("----------------id2");
      // orgDAODW.insert(returnChgOrgRate2);
      //    
      //
      // // 更新单位版AA001
      // Org orgdw = orgDAODW
      // .getOrg_WL(chgOrgRate.getOrg().getId().toString());
      // orgdw.setOrgRate(chgOrgRate.getOrgRate());
      // orgdw.setEmpRate(chgOrgRate.getEmpRate());
      //    
      // orgDAODW.insertOrg(orgdw);
      //  
      //
      // // 更新单位版数据库AA002
      // List listdw = empDAODW.getEmpList_WL(chgOrgRate.getOrg().getId()
      // .toString());
      // Emp empdw = null;
      // // 缴存精度ID:payPrecision
      // int payPrecisiondw = Integer.parseInt(orgdw.getPayPrecision()
      // .toString());
      // // 利用缴存精度
      // ArithmeticInterface arithmeticInterfacedw = ArithmeticFactory
      // .getArithmetic().getArithmeticDAO(payPrecisiondw);
      // for (int i = 0; i < listdw.size(); i++) {
      // empdw = (Emp) listdw.get(i);
      // empdw.setOrgPay(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),
      // chgOrgRate.getOrgRate()));
      // //
      // System.out.println(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getOrgRate())+"arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getOrgRate())");
      // empdw.setEmpPay(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),
      // chgOrgRate.getEmpRate()));
      // //
      // System.out.println(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getEmpRate())+"arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getEmpRate())");
      // empDAODW.insert(empdw);
      // }
      // }
      // }
    } catch (BusinessException be) {
      throw be;
    }
  }

  /**
   * 王菱 维护 第一次进入时取得列表信息
   */
  public List findChgpersonMaintainList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = chgOrgRateDAO.getChgOrgRateList_WL(orderBy, order, start,
          pageSize, page, securityInfo);
      ChgOrgRate chgOrgRate = null;
      if (list.size() != 0 || list != null) {
        for (int i = 0; i < list.size(); i++) {
          chgOrgRate = (ChgOrgRate) list.get(i);
          // 转换
          chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer
              .parseInt(chgOrgRate.getChgStatus().toString()),
              BusiConst.CHGTYPESTATUS));
          // chgOrgRate.getOrg().setId("0"+chgOrgRate.getOrg().getId().toString());
          // System.out.println("orgid=="+chgOrgRate.getOrg().getId());
        }
      }

      int count = chgOrgRateDAO.queryChgorgrateMaintainList(securityInfo);
      pagination.setNrOfElements(count);

      return list;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  /**
   * 变更维护－根据条件查询列表信息
   */
  public List findChgorgrateMaintainListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      String orgID = (String) pagination.getQueryCriterions().get("orgID");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String chgDate = (String) pagination.getQueryCriterions().get("chgDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = chgOrgRateDAO
          .getChgOrgRateListByCriterions_wsh(orderBy, order, start, pageSize,
              orgID, orgName, chgDate, endDate, page, securityInfo);
      ChgOrgRate chgOrgRate = null;
      if (list.size() != 0 || list != null) {
        for (int i = 0; i < list.size(); i++) {
          chgOrgRate = (ChgOrgRate) list.get(i);
          // 转换
          chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer
              .parseInt(chgOrgRate.getChgStatus().toString()),
              BusiConst.CHGTYPESTATUS));
          // chgOrgRate.getOrg().setId("0"+chgOrgRate.getOrg().getId().toString());
        }
      }

      int count = chgOrgRateDAO.queryChgorgrateMaintainListByCriterions_wsh(
          orgID, orgName, chgDate, endDate, securityInfo);
      pagination.setNrOfElements(count);

      return list;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  /**
   * 根据头表ID 查询AA201表信息
   */
  public ChgOrgRate queryChgorgrateMessage(String id) throws Exception,
      BusinessException {

    ChgOrgRate chgOrgRate = null;
    chgOrgRate = chgOrgRateDAO.queryById(new Integer(id));
    BigDecimal empSalary = empDAO.queryEmpSalary(chgOrgRate.getOrg().getId()
        .toString());// 该单位下的职工工资基数
    chgOrgRate.setSalarybase(empSalary);
    String tempid = chgOrgRate.getOrg().getId().toString();
    String sid = BusiTools.convertTenNumber(tempid);
    chgOrgRate.getOrg().setSid(sid);
    return chgOrgRate;

  }

  /**
   * 根据AA201头表ID进行数据校验,是否可以进行业务撤销
   */
  public void checkDelUseMessage(String chgorgrateID, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      ChgOrgRate chgOrgRate = chgOrgRateDAO
          .queryById(new Integer(chgorgrateID));

      // 判断维护中的启用要判断该笔记录的状态是否等于1，若不等于1，提示该笔业务已启用
      if (chgOrgRate.getChgStatus().toString().equals("1")) {
        throw new BusinessException("该笔业务已撤销！！");
      }

      // 该笔变更后是否存在其它变更
      Integer maxHeadID = chgPersonHeadDAO.getMaxHeadID_WL(chgOrgRate.getOrg()
          .getId().toString());
      int temp_ID = (maxHeadID.toString()).compareTo(chgorgrateID);
      if (temp_ID > 0) {
        throw new BusinessException("请先撤消后面的变更！");
      }
      // 该笔变更是否被汇缴应用
      if (chgOrgRate.getPaymentHead() != null) {
        throw new BusinessException("请先撤消应用该笔变更的汇缴！");
      }
      // 该单位下是否存在没有启用的人员变更
      boolean flag = chgOrgRateDAO.getChgStatus(new Integer(chgOrgRate.getOrg()
          .getId().toString()));
      if (flag == false) {
        throw new BusinessException("存在未启用的汇缴比例调整，请先启用！");
      }
      // 撤销启用
      this.deluserChgPersonMessage(chgorgrateID, securityInfo);

    } catch (BusinessException b) {
      b.printStackTrace();
      throw b;
    }
  }

  /**
   * 撤销启用
   */
  public void deluserChgPersonMessage(String chgorgrateID,
      SecurityInfo securityInfo) throws BusinessException {
    try {
      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();

      // 更新AA201
      ChgOrgRate chgOrgRate = chgOrgRateDAO
          .queryById(new Integer(chgorgrateID));

      chgOrgRate.setChgStatus(new Integer(1));
      chgOrgRateDAO.insert(chgOrgRate);

      // 更新AA001
      Org org = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
      org.setOrgRate(chgOrgRate.getPreOrgRate());
      org.setEmpRate(chgOrgRate.getPreEmpRate());
      orgDAO.insert(org);

      // 更新AA002
      List list = empDAO.getEmpList_WL(chgOrgRate.getOrg().getId().toString());
      Emp emp = null;

      // 缴存精度ID:payPrecision
      int payPrecision = Integer.parseInt(org.getPayPrecision().toString());
      // 利用缴存精度
      ArithmeticInterface arithmeticInterface = ArithmeticFactory
          .getArithmetic().getArithmeticDAO(payPrecision);

      for (int i = 0; i < list.size(); i++) {
        emp = (Emp) list.get(i);
        emp.setOrgPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getPreOrgRate()));
        emp.setEmpPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getPreEmpRate()));
        empDAO.insert(emp);
      }

      // 删除AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = chgOrgRateBizActivityLogDAO
          .queryChgOrgRateBiz_WL(chgOrgRate.getId().toString(), ""
              + BusiConst.BUSINESSSTATE_3);
      chgOrgRateBizActivityLogDAO.delete_WL(chgOrgRateBizActivityLog);

      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_REVOCATION);
      hafOperateLog.setOpBizId(new Integer(chgOrgRate.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog
          .setOrgId(new Integer(chgOrgRate.getOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("撤销启用失败!");
    }

  }

  /**
   * 取得职工状态为(1,3,4)的工资基数列表
   */
  public List queryEmpSalaryByTIAOJIAN(String orgID) throws BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    list = empDAO.queryEmpSalaryByTIAOJIAN_WL(orgID);
    return list;
  }

  // 吴洪涛修改 2008-3-18 单位_汇缴比例调整
  public ChgOrgRate queryChgorgrate_OrgEdition(String orgId, String chgMonth,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // 单位版
    ChgOrgRate chgOrgRate = new ChgOrgRate();
    int isOrgEdition = securityInfo.getIsOrgEdition();
    try {
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG)// 单位版{
      {
        List listPaymentHead = orgDAODW.queryPaymentHeadlist_wuht(orgId,
            chgMonth);// 查询AA301，判断应汇缴年月下该单位在中心是否汇缴
        if (listPaymentHead != null && listPaymentHead.size() > 0) {
          List listChgOrgRate = orgDAODW.queryChgOrgRatelist_wuht(orgId,
              chgMonth);// 查询AA201，判断被该汇缴应用的变更是否有汇缴比例调整
          if (listChgOrgRate != null && listChgOrgRate.size() > 0) {
            ChgOrgRate chgOrgRate_new = (ChgOrgRate) listChgOrgRate
                .get(listChgOrgRate.size() - 1);
            chgOrgRate.setEmpRate(chgOrgRate_new.getEmpRate());
            chgOrgRate.setOrgRate(chgOrgRate_new.getOrgRate());

          }
        } else {
          List listChgOrgRate = orgDAODW.queryChgOrgRateList_wuht(orgId);// 查询AA201，判断该是否存在未被汇缴应用的汇缴比例调整记录
          if (listChgOrgRate != null && listChgOrgRate.size() > 0) {
            ChgOrgRate chgOrgRate_new = (ChgOrgRate) listChgOrgRate
                .get(listChgOrgRate.size() - 1);
            chgOrgRate.setEmpRate(chgOrgRate_new.getEmpRate());
            chgOrgRate.setOrgRate(chgOrgRate_new.getOrgRate());

          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return chgOrgRate;
  }

  public List queryOrgChgList(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List list = new ArrayList();
    list = orgChgDAO.queryOrgChgList(orgid, status, print, orderBy, order,
        start, pageSize, securityInfo);
    return list;
  }

  public int queryOrgChgListAll(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    return orgChgDAO.queryOrgChgListAll(orgid, status, print, securityInfo);
  }

  public OrgChg queryOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    return orgChg;
  }

  public void deleteOrgChg(OrgChg orgChg) throws BusinessException {
    orgChgDAO.delete(orgChg);
  }

  public void submitOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setStatus("1");
  }

  public void delSubmitOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setStatus("0");
  }

  public void passOrgChgById(String id, SecurityInfo securityInfo)
      throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setPrint("1");
    orgChg.setPerson(securityInfo.getUserInfo().getRealName());
  }

  public void delPassOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setPrint("0");
    orgChg.setPerson("");
  }

  public void updateOrgChg(OrgChg orgChg) throws BusinessException {
    try {
      OrgChg orgChgs = orgChgDAO.queryById(new Integer(orgChg.getId()
          .toString()));
      orgChgs.setOrg(orgChg.getOrg());
      orgChgs.setAddCount(orgChg.getAddCount());
      orgChgs.setAddEmp(orgChg.getAddEmp());
      orgChgs.setAddMonth(orgChg.getAddMonth());
      orgChgs.setAddOrg(orgChg.getAddOrg());
      orgChgs.setAddStEndMonth(orgChg.getAddStEndMonth());
      orgChgs.setAddSum(orgChg.getAddSum());
      orgChgs.setNewPay(orgChg.getNewPay());
      orgChgs.setNewRate(orgChg.getNewRate());
      orgChgs.setPayRate(orgChg.getPayRate());
      orgChgs.setPaySalary(orgChg.getPaySalary());
      orgChgs.setPrePay(orgChg.getPrePay());
      orgChgs.setPreRate(orgChg.getPreRate());
      orgChgs.setMake(orgChg.getMake());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List queryOrgChgListCheck(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List list = new ArrayList();
    list = orgChgDAO.queryOrgChgListCheck(orgid, status, print, orderBy, order,
        start, pageSize, securityInfo);
    return list;
  }

  public int queryOrgChgListAllCheck(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    return orgChgDAO
        .queryOrgChgListAllCheck(orgid, status, print, securityInfo);
  }
}
