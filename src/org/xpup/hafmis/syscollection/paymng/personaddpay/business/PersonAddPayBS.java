package org.xpup.hafmis.syscollection.paymng.personaddpay.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
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
import org.xpup.hafmis.syscollection.common.dao.AddPayTailDAO;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpAddPayBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpAddPayDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AddPayTailDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpAddPayDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.AddPayExpDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpExpInfoDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayHeadImportDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayHeadPrintDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayListImportDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayMaintainDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.OrgInfoDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.PersonAddPayDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.PersonaddpayImpDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;
import org.xpup.hafmis.syscommon.dao.EmpInfoDAO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * @author 卢钢 2007-6-28
 */
public class PersonAddPayBS implements IPersonAddPayBS {

  private PaymentHeadDAO paymentHeadDAO = null;

  private EmpDAO empDAO = null;

  private OrgDAO orgDAO = null;

  private AddPayTailDAO addPayTailDAO = null;

  private EmpAddPayDAO empAddPayDAO = null;

  private EmpInfoDAO empInfoDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private CollBankDAO collBankDAO = null;

  private AddPayTailDAODW addPayTailDAODW = null;

  private EmpAddPayDAODW empAddPayDAODW = null;

  private AutoInfoPickDAO autoInfoPickDAO = null;

  private AutoInfoPickDAODW autoInfoPickDAODW = null;

  private EmpAddPayBizActivityLogDAO empAddPayBizActivityLogDAO = null;
  
  private OrganizationUnitDAO organizationUnitDAO=null;
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO=null;
  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }
  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setAddPayTailDAODW(AddPayTailDAODW addPayTailDAODW) {
    this.addPayTailDAODW = addPayTailDAODW;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setEmpAddPayDAODW(EmpAddPayDAODW empAddPayDAODW) {
    this.empAddPayDAODW = empAddPayDAODW;
  }

  public OrgDAO getOrgDAO() {
    return orgDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public PaymentHeadDAO getPaymentHeadDAO() {
    return paymentHeadDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public EmpAddPayDAO getEmpAddPayDAO() {
    return empAddPayDAO;
  }

  public void setEmpAddPayDAO(EmpAddPayDAO empAddPayDAO) {
    this.empAddPayDAO = empAddPayDAO;
  }
  
  private String center_head_id = "";// 提取时，中心版的头表ID wangy 2008-02-26

  public OrgInfoDTO findOrgInfoById(String orgId) {
    // TODO Auto-generated method stub
    OrgInfoDTO orgInfoDTO = new OrgInfoDTO();
    try {
      List list = empAddPayDAO.queryEmpAddPayByOrgId_lg(orgId, new Integer(1));
      if (list.size() > 0) {
        EmpAddPay empAddPay = (EmpAddPay) list.get(0);
        orgInfoDTO.setUnitName(empAddPay.getOrg().getOrgInfo().getName());
        orgInfoDTO.setDocNumber(empAddPay.getNoteNum());
        orgInfoDTO.setPaymentHeadId(empAddPay.getId().toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgInfoDTO;
  }

  // 查询银行
  public String findCollBank(String collBankid) {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }

  // 根据单位ID查询单位信息
  public Org findOrgInfo(Serializable id, String status,
      SecurityInfo securityInfo) throws BusinessException {
    // TODO Auto-generated method stub
    Org org = null;
    String orgid = "";
    if (id != null) {
      orgid = id.toString();
    }
    org = orgDAO.queryByCriterions(orgid, status, null, securityInfo);
    return org;
  }
  
  /**
   * jj自动生成结算号
   * @param securityInfo
   * @return
   */
  public String getNoteNum(SecurityInfo securityInfo){
    //系统自动生成结算号：业务日期+流水号
    String bizDate = securityInfo.getUserInfo().getBizDate();//业务日期
    String noteNum = bizDate+orgDAO.queryNoteNum();
    return noteNum;
  }
  public Org findOrgInfo(String orgId) {
    Org org = null;
    try {
      org = orgDAO.findOrgInfo(orgId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }

  public EmpAddPay findEmpAddPay(String id) {
    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(id.trim()));
    return empAddPay;
  }

  public List findPersonAddPayListByCriterions(Pagination pagination) {
    // TODO Auto-generated method stub
    List personAddPayList = new ArrayList();
    try {
      SecurityInfo securityInfo = (SecurityInfo) pagination
          .getQueryCriterions().get("securityInfo");
      List list = new ArrayList();
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String unitName = (String) pagination.getQueryCriterions()
          .get("unitName");
      String noteNumber = (String) pagination.getQueryCriterions().get(
          "docNumber");
      String paymentHeadId = (String) pagination.getQueryCriterions().get(
          "paymentHeadId");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      if ((paymentHeadId != null) && (paymentHeadId != "")) {
        try {
          Integer headId = new Integer(paymentHeadId);
          list = empAddPayDAO.queryByHeadId(headId, null, orderBy, order,
              start, pageSize, page);
          for (int i = 0; i < list.size(); i++) {
            PersonAddPayDTO personAddPayDTO = new PersonAddPayDTO();
            AddPayTail addPayTail = (AddPayTail) list.get(i);
            personAddPayDTO.setId(headId.toString());
            personAddPayDTO.setEmpId(BusiTools.convertSixNumber(addPayTail
                .getEmpId().toString()));
            if ((orgId != null) && (orgId != "")) {
              // String
              // empname=empDAO.getEmpname(addPayTail.getEmpId().toString());
              // if(empname!=null&&(!empname.equals(""))){
              // EmpInfo empInfo=empInfoDAO.queryById(emp.getEmpInfo().getId());
              personAddPayDTO.setEmpName(addPayTail.getEmpName());
              personAddPayDTO.setUnitAddPayAmount(addPayTail.getOrgAddPaySum());
              personAddPayDTO.setPersonAddPayAmount(addPayTail
                  .getEmpAddPaySum());
              personAddPayDTO.setAddPayAmount(addPayTail.getOrgAddPaySum().add(
                  addPayTail.getEmpAddPaySum()));
              personAddPayDTO.setAddPayBeginYearMonth(addPayTail
                  .getBeginMonth());
              personAddPayDTO.setAddPayEndYearMonth(addPayTail.getEndMonth());
              personAddPayDTO.setAddPayReason(addPayTail.getAddReason());
              personAddPayDTO
                  .setPersonSum(addPayTail.getPersonSum().toString());
              personAddPayDTO.setOrgPaySum(addPayTail.getOrgAddPaySum());
              personAddPayDTO.setEmpPaySum(addPayTail.getEmpAddPaySum());
              personAddPayDTO.setAddPaySum(addPayTail.getAddPaySum());
              personAddPayDTO.setOrgId(orgId);
              personAddPayDTO.setOrgName(unitName);
              personAddPayDTO.setDocNum(noteNumber);
              String addPayType = addPayTail.getReserveaC();
              if (addPayType != null && !"".equals(addPayType)) {
                personAddPayDTO.setPayMode(BusiTools.getBusiValue_WL(
                    addPayType, BusiConst.ADDPAYTYPE));
              }
              // 获取单位提取状态

              String statetype = autoInfoPickDAODW.findStatus(personAddPayDTO
                  .getOrgId(), personAddPayDTO.getId().toString(),
                  BusiConst.ORGBUSINESSTYPE_K);
              if (statetype != null && !"".equals(statetype)) {
                personAddPayDTO.setTempPickType(BusiTools.getBusiValue(Integer
                    .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
              }

              personAddPayList.add(personAddPayDTO);
              // }
            }
          }

          // int count =
          // addPayTailDAO.queryEmpaddpayEmpListCount(headId.toString());
          List countlist = (List) empAddPayDAO.queryByHeadIdCount(headId);
          int count = 0;
          if (countlist != null) {
            count = countlist.size();
          }
          pagination.setNrOfElements(count);
        } catch (NumberFormatException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }

      } else {
        pagination.setNrOfElements(0);
      }
    } finally {
    }
    return personAddPayList;
  }

  public List findPersonAddPayList(Pagination pagination) throws Exception {
    List emplist = new ArrayList();
    String paymentHeadId = (String) pagination.getQueryCriterions().get(
        "paymentHeadId");
    if ((paymentHeadId != null) && (paymentHeadId != "")) {
      Integer headId = new Integer(paymentHeadId);
      emplist = addPayTailDAO.queryEmpaddpayList(headId.toString());
    }
    return emplist;
  }

  public List findPersonAddPayPrintList(Pagination pagination) throws Exception {
    // TODO Auto-generated method stub
    List personAddPayList = new ArrayList();
    try {
      List list = new ArrayList();
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String unitName = (String) pagination.getQueryCriterions()
          .get("unitName");
      String noteNumber = (String) pagination.getQueryCriterions().get(
          "docNumber");
      String paymentHeadId = (String) pagination.getQueryCriterions().get(
          "paymentHeadId");
      if ((paymentHeadId != null) && (paymentHeadId != "")) {
        try {
          Integer headId = new Integer(paymentHeadId.trim());
          list = empAddPayDAO.queryByHeadIdCount(headId);
          for (int i = 0; i < list.size(); i++) {
            PersonAddPayDTO personAddPayDTO = new PersonAddPayDTO();
            AddPayTail addPayTail = (AddPayTail) list.get(i);
            personAddPayDTO.setId(headId.toString());
            personAddPayDTO.setEmpId(BusiTools.convertSixNumber(addPayTail
                .getEmpId().toString()));
            if ((orgId != null) && (orgId != "")) {
              // String
              // empname=empDAO.getEmpname(addPayTail.getEmpId().toString());
              // if(empname!=null&&(!empname.equals(""))){
              // EmpInfo empInfo=empInfoDAO.queryById(emp.getEmpInfo().getId());
              int count=0;
              count = BusiTools.monthInterval(addPayTail.getEndMonth().substring(0,4)+"-"+addPayTail.getEndMonth().substring(4,6)+"-"+"01",
                  addPayTail.getBeginMonth().substring(0,4)+"-"+addPayTail.getBeginMonth().substring(4,6)+"-"+"01");
              count += 1;
              Emp emp = empDAO.queryByCriterions(addPayTail.getEmpId().toString(),orgId);
              personAddPayDTO.setCardNum(emp.getEmpInfo().getCardNum());//身份证号
              personAddPayDTO.setPayMode(addPayTail.getReserveaC());//补缴类型
              personAddPayDTO.setSalaryBase(addPayTail.getSalaryBase());//工资基数
              personAddPayDTO.setOrgRate(addPayTail.getEmpRate());//单位缴率(HQL查询的是emprate)
              personAddPayDTO.setMonthCounts(String.valueOf(count));
              personAddPayDTO.setEmpName(addPayTail.getEmpName());
              personAddPayDTO.setUnitAddPayAmount(addPayTail.getOrgAddPaySum());
              personAddPayDTO.setPersonAddPayAmount(addPayTail
                  .getEmpAddPaySum());
              personAddPayDTO.setAddPayAmount(addPayTail.getOrgAddPaySum().add(
                  addPayTail.getEmpAddPaySum()));
              personAddPayDTO.setAddPayBeginYearMonth(addPayTail
                  .getBeginMonth());
              personAddPayDTO.setAddPayEndYearMonth(addPayTail.getEndMonth());
              personAddPayDTO.setAddPayReason(addPayTail.getAddReason());
              personAddPayDTO
                  .setPersonSum(addPayTail.getPersonSum().toString());
              personAddPayDTO.setOrgPaySum(addPayTail.getOrgAddPaySum());
              personAddPayDTO.setEmpPaySum(addPayTail.getEmpAddPaySum());
              personAddPayDTO.setAddPaySum(addPayTail.getAddPaySum());
              personAddPayDTO.setOrgId(orgId);
              personAddPayDTO.setOrgName(unitName);
              personAddPayDTO.setDocNum(noteNumber);
              personAddPayList.add(personAddPayDTO);
              // }
            }
          }
        } catch (NumberFormatException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }

      } else {
        pagination.setNrOfElements(0);
      }
    } finally {
    }
    return personAddPayList;
  }

  public List findPersonAddPayPrint(String paymentHeadId, String orgId,
      String unitName, String noteNumber) throws Exception {
    // TODO Auto-generated method stub
    List personAddPayList = new ArrayList();
    try {
      List list = new ArrayList();
      if ((paymentHeadId != null) && (paymentHeadId != "")) {
        try {
          Integer headId = new Integer(paymentHeadId);
          list = empAddPayDAO.queryByHeadIdCount(headId);
          for (int i = 0; i < list.size(); i++) {
            PersonAddPayDTO personAddPayDTO = new PersonAddPayDTO();
            AddPayTail addPayTail = (AddPayTail) list.get(i);
            personAddPayDTO.setId(headId.toString());
            personAddPayDTO.setEmpId(BusiTools.convertSixNumber(addPayTail
                .getEmpId().toString()));
            if ((orgId != null) && (orgId != "")) {
              // Emp emp=empDAO.getChgPersonEmp_WL(orgId,
              // addPayTail.getEmpId().toString());
              // if(emp!=null){
              // EmpInfo empInfo=empInfoDAO.queryById(emp.getEmpInfo().getId());
              personAddPayDTO.setEmpName(addPayTail.getEmpName());
              personAddPayDTO.setUnitAddPayAmount(addPayTail.getOrgAddPaySum());
              personAddPayDTO.setPersonAddPayAmount(addPayTail
                  .getEmpAddPaySum());
              personAddPayDTO.setAddPayAmount(addPayTail.getOrgAddPaySum().add(
                  addPayTail.getEmpAddPaySum()));
              personAddPayDTO.setAddPayBeginYearMonth(addPayTail
                  .getBeginMonth());
              personAddPayDTO.setAddPayEndYearMonth(addPayTail.getEndMonth());
              personAddPayDTO.setAddPayReason(addPayTail.getAddReason());
              personAddPayDTO
                  .setPersonSum(addPayTail.getPersonSum().toString());
              personAddPayDTO.setOrgPaySum(addPayTail.getOrgAddPaySum());
              personAddPayDTO.setEmpPaySum(addPayTail.getEmpAddPaySum());
              personAddPayDTO.setAddPaySum(addPayTail.getAddPaySum());
              personAddPayDTO.setOrgId(orgId);
              personAddPayDTO.setOrgName(unitName);
              personAddPayDTO.setDocNum(noteNumber);
              personAddPayList.add(personAddPayDTO);
            }
          }
          // }
        } catch (NumberFormatException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    } finally {
    }
    return personAddPayList;
  }

  public AddPayTailDAO getAddPayTailDAO() {
    return addPayTailDAO;
  }

  public void setAddPayTailDAO(AddPayTailDAO addPayTailDAO) {
    this.addPayTailDAO = addPayTailDAO;
  }

  public EmpInfoDAO getEmpInfoDAO() {
    return empInfoDAO;
  }

  public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
    this.empInfoDAO = empInfoDAO;
  }

  public void deleteAddPayTail(Integer empId, Integer paymentHeadId)
      throws BusinessException {
    try {
      // List list=empAddPayDAO.queryByEmpId(empId,paymentHeadId);
      //empAddPayDAO.deleteAll(paymentHeadId);
      empAddPayDAO.deleteTail(empId,paymentHeadId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("删除失败!");
    }
  }
  /**
   * 删除个人补缴尾表
   * @param addPayTailId
   * @throws BusinessException
   */
//  public void deleteAddPayTail(Integer addPayTailId)
//    throws BusinessException {
//    try {
//      empAddPayDAO.deleteTail(addPayTailId);
//    } catch (Exception e) {
//      throw new BusinessException("删除失败!");
//    }
//  }
  
  /**
   * 查询尾表缴存金额
   */
  public BigDecimal getAddPayMoney(Integer addPayHeadId)
    throws BusinessException {
      BigDecimal money = empAddPayDAO.queryTailMoney(addPayHeadId);
      return money;
  }
  public void deleteHead(String paymentHeadId) throws BusinessException {
    try {
      EmpAddPay empAddPay = empAddPayDAO.queryByHeadId(paymentHeadId);
      empAddPayDAO.delete(empAddPay);
    } catch (Exception e) {
      throw new BusinessException("删除失败!");
    }
  }

  public void deleteAll(Integer paymentHeadId) throws BusinessException {
    // TODO Auto-generated method stub

    List list = addPayTailDAO.queryByEmpId(null, paymentHeadId);
    addPayTailDAO.deleteAll(list);
  }

  public List findEmpById(Integer paymentHeadId, Integer empId)
      throws BusinessException {
    // TODO Auto-generated method stub

    List list = empAddPayDAO.queryByHeadId(paymentHeadId, empId, null, "", 0,
        0, 0);
    return list;
  }

  public EmpInfo findEmpInfoById(Integer empId) throws BusinessException {
    // TODO Auto-generated method stub

    EmpInfo empInfo = empInfoDAO.queryById(empId);
    return empInfo;
  }

  public List findEmpById_lg(Integer empId, Integer orgId)
      throws BusinessException {
    // TODO Auto-generated method stub

    List empList = empDAO.queryByEmpId_lg(empId, orgId);
    return empList;
  }

  public void addPersonAddPayTail(AddPayTail addPayTail)
      throws BusinessException {
    try {
      empAddPayDAO.insert(addPayTail);
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }
  }

  public List queryPaymentHead_lg(Integer orgId) throws BusinessException {
    // TODO Auto-generated method stub
    try {
      List paymentHeadList = new ArrayList();
      paymentHeadList = paymentHeadDAO.queryByOrgId_lg(new Integer(orgId
          .toString()));
      return paymentHeadList;
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }

  }

  public void addBiz_lg(EmpAddPayBizActivityLog empAddPayBizActivityLog)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {
      empAddPayBizActivityLogDAO.insert(empAddPayBizActivityLog);
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }
  }

  public void deletePaymentHead_lg(Integer paymentHeadId)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {
      paymentHeadDAO.deletePaymentHead_lg(paymentHeadId);
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }
  }

  public void deleteEmpAddPayBizActivityLog_lg(Integer paymentHeadId)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {
      empAddPayBizActivityLogDAO.deleteEmpAddPayBiz(paymentHeadId);
    } catch (Exception e) {
      throw new BusinessException("删除失败!");
    }
  }

  public EmpAddPayBizActivityLogDAO getEmpAddPayBizActivityLogDAO() {
    return empAddPayBizActivityLogDAO;
  }

  public void setEmpAddPayBizActivityLogDAO(
      EmpAddPayBizActivityLogDAO empAddPayBizActivityLogDAO) {
    this.empAddPayBizActivityLogDAO = empAddPayBizActivityLogDAO;
  }

  public void addHafOperateLog_lg(HafOperateLog hafOperateLog)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }
  }

  public void insertHafOperateLog_lg(Pagination pagination)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {

      String bizId = (String) pagination.getQueryCriterions().get("bizId");
      Integer id = new Integer(bizId);
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(
          BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO).toString());
      hafOperateLog.setOpButton(Integer
          .toString(BusiLogConst.BIZLOG_ACTION_ADD));
      hafOperateLog.setOpBizId(id);

      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }
  }

  public void insertHafOperateLog(HafOperateLog hafOperateLog)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }
  }

  public HafOperateLogDAO getHafOperateLogDAO() {
    return hafOperateLogDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public EmpDAO getEmpDAO() {
    return empDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public PaymentHead queryById_lg(Integer id) throws BusinessException {
    // TODO Auto-generated method stub
    try {

      PaymentHead paymentHead = paymentHeadDAO.queryById(id);
      return paymentHead;
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }

  }

  public void updatePaymentHead(PaymentHead paymentHead)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {

      paymentHeadDAO.updatePaymentHead(paymentHead);
    } catch (Exception e) {
      throw new BusinessException("添加失败!");
    }

  }

  /**
   * 根据条件查询personaddpay记录（导入）
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List modifyPersonAddPayBatch(List addPayImportList,
      Pagination pagination) throws Exception, BusinessException {
    List personAddPayList = new ArrayList();
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String beginMonths = "";
    try {
      for (int i = 0; i < addPayImportList.size(); i++) {
        PersonaddpayImpDTO personAddPayImportDTO = (PersonaddpayImpDTO) addPayImportList
            .get(i);
        if (personAddPayImportDTO.getOrgId().trim() != orgId.trim()) {
          continue;
        }
        String paymentHeadId = (String) pagination.getQueryCriterions().get(
            "paymentHeadId");
        List list = empAddPayDAO.queryByEmpId(new Integer(personAddPayImportDTO
            .getEmpId()), new Integer(paymentHeadId));
        if (list.size() < 1) {
          continue;
        }
        AddPayTail addPayTail = new AddPayTail();
        String beginMonth = personAddPayImportDTO.getBeginMonth();
        String endMonth = personAddPayImportDTO.getEndMonth();
        if (BusiTools.monthInterval(beginMonth, endMonth) == 0) {
          addPayTail.setEmpAddMoney(new BigDecimal(personAddPayImportDTO
              .getEmpPay()));
          addPayTail.setOrgAddMoney(new BigDecimal(personAddPayImportDTO
              .getOrgPay()));
          addPayTail.setEmpId(new Integer(personAddPayImportDTO.getEmpId()));
          addPayTail.setAddPaySum(new BigDecimal(personAddPayImportDTO
              .getEmpPay()).add(new BigDecimal(personAddPayImportDTO
              .getOrgPay())));
          addPayTail.setAddMonht(personAddPayImportDTO.getBeginMonth());
          addPayTail.setAddReason(personAddPayImportDTO.getReason());
          empAddPayDAO.insert(addPayTail);
        } else {
          int m = BusiTools.monthInterval(beginMonth, endMonth);
          BigDecimal months = new BigDecimal(m);
          for (int j = 0; j < m; j++) {
            beginMonths = personAddPayImportDTO.getBeginMonth();
            addPayTail.setEmpAddMoney(new BigDecimal(personAddPayImportDTO
                .getEmpPay()).divide(months, 2, BigDecimal.ROUND_HALF_UP));
            addPayTail.setOrgAddMoney(new BigDecimal(personAddPayImportDTO
                .getOrgPay()).divide(months, 2, BigDecimal.ROUND_HALF_UP));
            addPayTail.setEmpId(new Integer(personAddPayImportDTO.getEmpId()));
            addPayTail.setAddPaySum(new BigDecimal(personAddPayImportDTO
                .getEmpPay()).add(new BigDecimal(personAddPayImportDTO
                .getOrgPay())));
            addPayTail.setAddMonht(beginMonths);
            addPayTail.setAddReason(personAddPayImportDTO.getReason());
            empAddPayDAO.insert(addPayTail);
            beginMonths = BusiTools.addMonth(beginMonths, 1);
          }
        }
        EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
        empAddPayBizActivityLog.setAction(new Integer(1));
        empAddPayBizActivityLogDAO.insert(empAddPayBizActivityLog);
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(1));
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_ADD));
        hafOperateLog.setOpBizId(new Integer(0));
        hafOperateLogDAO.insert(hafOperateLog);
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("修改失败!");
    }
    return personAddPayList;
  }

  public Org findOrgById(Integer orgId, SecurityInfo securityInfo)
      throws BusinessException {
    Org org = null;
      org = orgDAO.getOrg_WL(orgId.toString());
      //输入单位编号时判断登录日期与单位所在的归集银行的归集银行表的日结日期是否一致，不一致不允许做业务
      String bizDate = securityInfo.getUserInfo().getBizDate();//业务日期
      String bankDate = "";
      bankDate = orgDAO.findAA103_DayTime(org.getOrgInfo().getCollectionBankId());
      if(!bizDate.equals(bankDate)){
        throw new BusinessException("登录日期与银行业务日期不一致，此单位不能做业务！");
      }

    return org;
  }

  public List findPaymentHeadById(String orgId) throws BusinessException {

    List paymentHeadList = new ArrayList();
    try {
      paymentHeadList = paymentHeadDAO.queryByOrgId(new Integer(orgId));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymentHeadList;
  }

  public boolean queryAddPayTailEmp(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    String paymentHeadId = (String) pagination.getQueryCriterions().get(
        "paymentHeadId");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String payStatus = "";
    if ((paymentHeadId != null) && (paymentHeadId != ""))
      list = addPayTailDAO.queryByEmpId(new Integer(empId), new Integer(
          paymentHeadId));
    if (list.size() > 0) {
      AddPayTail addPayTail = (AddPayTail) list.get(0);
      String id = addPayTail.getPaymentHead().getId().toString();
      EmpAddPay empAddPay = empAddPayDAO.queryByHeadId(id);
      payStatus = empAddPay.getPayStatus().toString();
    }
    if (payStatus.equals("1"))
      return false;
    return true;
  }

  public EmpAddPay findPersonAddPay(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = new ArrayList();
    List emplist = new ArrayList();
    EmpAddPay empAddPay = new EmpAddPay();
    BigDecimal payMoney = new BigDecimal(0.00);
    try {
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgpay = (String) pagination.getQueryCriterions().get(
          "orgAddPaySum");
      String emppay = (String) pagination.getQueryCriterions().get(
          "empAddPaySum");
      String addPayType = (String)pagination.getQueryCriterions().get(
      "addPayType");//挂账类型
      String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
      String payment_orgname = (String) pagination.getQueryCriterions().get("payment_orgname");
      Org org = orgDAO.queryById(new Integer(orgId));
      if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
        if(!org.getOrgInfo().getReserveaB().equals(payment_orgname)){
          org.getOrgInfo().setReserveaB(payment_orgname);
        }
      }else{
        if(!org.getOrgInfo().getName().equals(payment_orgname)){
          org.getOrgInfo().setReserveaB(payment_orgname);
        }
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      PersonAddPayAF  personAddPayAF=(PersonAddPayAF)pagination.getQueryCriterions().get("personAddPayAF");
      
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = empAddPayDAO.queryEmpAddPay(orgId, orderBy, order, start,
          pageSize, page);
      // 插入AA301，AA319
      if (list.size() == 0) {
        empAddPay.setPayStatus(new Integer(1));
        empAddPay.setOrg(org);
        empAddPay.setNoteNum(noteNum);
        empAddPay.setPayMoney(new BigDecimal(orgpay)
            .add(new BigDecimal(emppay)));
        empAddPay.setReceivables_bank_name(personAddPayAF.getReceivables_bank_name());
        empAddPay.setReceivables_bank_acc(personAddPayAF.getReceivables_bank_acc());
        empAddPay.setPayment_bank_name(personAddPayAF.getPayment_bank_name());
        empAddPay.setPayment_bank_acc(personAddPayAF.getPayment_bank_acc());
        empAddPay.setPay_way(personAddPayAF.getPayWay());
        empAddPay.setPay_kind(personAddPayAF.getPayKind());
        empAddPayDAO.insert(empAddPay);
        EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
        empAddPayBizActivityLog.setBizid(new Integer(empAddPay.getId()
            .toString()));
        empAddPayBizActivityLog.setAction(new Integer(1));
        empAddPayBizActivityLog.setOpTime(new Date());
        empAddPayBizActivityLog.setOperator(securityInfo.getUserName());
        empAddPayBizActivityLogDAO.insert(empAddPayBizActivityLog);
      } else {
        empAddPay = empAddPayDAO.queryEmpAddPayByOrgId(orgId, new Integer(1));
        payMoney = empAddPay.getPayMoney();
        emplist = empAddPayDAO.queryEmpAddPayByOrgId_lg(orgId, new Integer(1));
      }
      // 插入信息到AA304中
      String beginMonth = (String) pagination.getQueryCriterions().get(
          "beginMonth");
      String endMonth = (String) pagination.getQueryCriterions()
          .get("endMonth");
      String orgAddPaySum = (String) pagination.getQueryCriterions().get(
          "orgAddPaySum");
      BigDecimal orgPaySum = new BigDecimal(0.00);
      BigDecimal empPaySum = new BigDecimal(0.00);
      if ((orgAddPaySum != null) && (orgAddPaySum != "")) {
        orgPaySum = new BigDecimal(orgAddPaySum);
      }
      String empAddPaySum = (String) pagination.getQueryCriterions().get(
          "empAddPaySum");
      if ((empAddPaySum != null) && (empAddPaySum != "")) {
        empPaySum = new BigDecimal(empAddPaySum);
      }
      String reason = (String) pagination.getQueryCriterions().get("reason");
      BigDecimal sum = new BigDecimal(0.00);
      BigDecimal orgmonthpay = new BigDecimal(0.00);
      BigDecimal empmonthpay = new BigDecimal(0.00);
      sum = orgPaySum.add(empPaySum);
      int m = BusiTools.monthInterval(beginMonth, endMonth) + 1;
      BigDecimal month = new BigDecimal(0.00);
      if (m != 0) {
        month = new BigDecimal(new Integer(m).toString());
      }
      if (emplist.size() > 0) {
        empAddPay = (EmpAddPay) emplist.get(0);
        empAddPay.setPayMoney(sum);
      }

      if (beginMonth.equals(endMonth)) {
        AddPayTail addPayTail = new AddPayTail();
        String empId = (String) pagination.getQueryCriterions().get("empId");
        List lists = (List) empDAO.queryEmpById(new Integer(empId),
            new Integer(orgId));
        Emp emp = (Emp) lists.get(0);
        addPayTail.setEmp(emp);
        addPayTail.setEmpId(new Integer(empId));
        addPayTail.setPaymentHead(empAddPay);
        addPayTail.setAddMonht(beginMonth);
        addPayTail.setOrgAddMoney(new BigDecimal(orgAddPaySum));
        addPayTail.setEmpAddMoney(new BigDecimal(empAddPaySum));
        addPayTail.setAddReason(reason);
        addPayTail.setSalaryBase(emp.getSalaryBase());
        addPayTail.setOrgRate(emp.getOrg().getOrgRate());
        addPayTail.setEmpRate(emp.getOrg().getEmpRate());
        addPayTail.setReserveaC(addPayType);//补缴类型
        Serializable id = addPayTailDAO.insert(addPayTail);
        empAddPay.setPayMoney(payMoney.add(new BigDecimal(orgAddPaySum)
            .add(new BigDecimal(empAddPaySum))));
      } else {
        BigDecimal orgtemp = new BigDecimal(0.00);
        orgtemp = new BigDecimal(orgPaySum.doubleValue() % month.intValue());
        BigDecimal emptemp = new BigDecimal(0.00);
        emptemp = new BigDecimal(empPaySum.doubleValue() % month.intValue());
        orgmonthpay = (orgPaySum.subtract(orgtemp)).divide(month, 2,
            BigDecimal.ROUND_HALF_UP);
        empmonthpay = (empPaySum.subtract(emptemp)).divide(month, 2,
            BigDecimal.ROUND_HALF_UP);
        for (int i = 0; i < m; i++) {
          AddPayTail addPayTail = new AddPayTail();
          String empId = (String) pagination.getQueryCriterions().get("empId");
          List empList = empDAO.queryEmpById(new Integer(empId), new Integer(
              orgId));
          Emp emp = (Emp) empList.get(0);
          addPayTail.setEmpId(new Integer(emp.getEmpId().toString()));
          addPayTail.setEmp(emp);
          addPayTail.setPaymentHead(empAddPay);
          addPayTail.setAddMonht(beginMonth);
          if (i != m - 1) {
            addPayTail.setOrgAddMoney(orgmonthpay);
            addPayTail.setEmpAddMoney(empmonthpay);
          } else {
            addPayTail.setOrgAddMoney(orgmonthpay.add(orgtemp));
            addPayTail.setEmpAddMoney(empmonthpay.add(emptemp));
          }
          addPayTail.setAddReason(reason);
          addPayTail.setSalaryBase(emp.getSalaryBase());
          addPayTail.setOrgRate(emp.getOrg().getOrgRate());
          addPayTail.setEmpRate(emp.getOrg().getEmpRate());
          addPayTail.setReserveaC(addPayType);//补缴类型
          addPayTailDAO.insert(addPayTail);
          beginMonth = BusiTools.addMonth(beginMonth, 1);
          payMoney = payMoney.add(addPayTail.getOrgAddMoney().add(
              addPayTail.getEmpAddMoney()));
          empAddPay.setPayMoney(payMoney);
        }
      }

      // 插入BA003
      String nextInsert = (String) pagination.getQueryCriterions().get(
          "nextInsert");
      if (nextInsert.endsWith("stop")) {
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_ADD));
        hafOperateLog.setOpBizId(new Integer(empAddPay.getId().toString()));
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgId.toString()));
        hafOperateLogDAO.insert(hafOperateLog);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return empAddPay;
  }

  public void insertEmpAddPay(EmpAddPay empAddPay) throws Exception,
      BusinessException {

    try {
      empAddPayDAO.insert(empAddPay);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List queryPersonAddPay(Pagination pagination) throws Exception,
      BusinessException {
    List list = new ArrayList();
    try {
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orderBy = null;
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = empAddPayDAO.queryEmpAddPay(orgId, orderBy, order, start,
          pageSize, page);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 查询个人补缴列表
  public List findEmpaddpayList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    List list = new ArrayList();
    List returnlist = new ArrayList();
    try {
      Serializable orgId = (Serializable) pagination.getQueryCriterions().get(
          "orgId");
      if (pagination.getQueryCriterions().get("orgId") != null) {
        orgId = pagination.getQueryCriterions().get("orgId").toString();
      }
      String unitName = (String) pagination.getQueryCriterions()
          .get("unitName");
      String addPayAmount = (String) pagination.getQueryCriterions().get(
          "addPayAmount");
      String bizStatus = (String) pagination.getQueryCriterions().get(
          "bizStatus");
      String beginMonth = (String) pagination.getQueryCriterions().get(
          "beginMonth");
      String endMonth = (String) pagination.getQueryCriterions()
          .get("endMonth");
      String payMonth = (String) pagination.getQueryCriterions()
          .get("payMonth");
      String opTime = (String) pagination.getQueryCriterions().get("opTime");
      String payMonth1 = (String) pagination.getQueryCriterions()
      .get("payMonth1");
  String opTime1 = (String) pagination.getQueryCriterions().get("opTime1");
      String collBankId = (String) pagination.getQueryCriterions()
      .get("collBankId");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = addPayTailDAO.queryEmpaddpayListByCriterionsLg(orgId, unitName,
          addPayAmount, bizStatus, beginMonth, payMonth,payMonth1, orderBy, order, start,
          pageSize, page, opTime,opTime1, securityInfo,collBankId);
      // 转换业务状态
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          EmpaddpayMaintainDTO dto1 = (EmpaddpayMaintainDTO) list.get(i);
          EmpaddpayMaintainDTO dto2 = new EmpaddpayMaintainDTO();
          BeanUtils.copyProperties(dto2, dto1);
          int isOrgEdition = securityInfo.getIsOrgEdition();
          if ( isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
            // 查询提取状态
            String statetype = autoInfoPickDAODW.findStatus(dto1.getOrgId(),dto1.getId().toString(),BusiConst.ORGBUSINESSTYPE_K);
            // 转换提取状态
            dto2.setTempPickType(BusiTools.getBusiValue(Integer.parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
          }
          dto2.setOrgId(BusiTools.convertTenNumber(dto1.getOrgId()));
          dto2.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto1
              .getPayStatus()), BusiConst.BUSINESSSTATE));
          returnlist.add(dto2);
        }
      }

      int count = addPayTailDAO.queryEmpaddpayListCount(orgId, unitName,
          addPayAmount, bizStatus, beginMonth, payMonth,  payMonth1,orderBy, order, start,
          pageSize, opTime,opTime1,  securityInfo,collBankId);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnlist;
  }
  /**
   * 查询列表打印信息
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findEmpaddpayList_jj(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    List list = new ArrayList();
    List returnlist = new ArrayList();
    try {
      Serializable orgId = (Serializable) pagination.getQueryCriterions().get(
          "orgId");
      if (pagination.getQueryCriterions().get("orgId") != null) {
        orgId = pagination.getQueryCriterions().get("orgId").toString();
      }
      String unitName = (String) pagination.getQueryCriterions()
          .get("unitName");
      String addPayAmount = (String) pagination.getQueryCriterions().get(
          "addPayAmount");
      String bizStatus = (String) pagination.getQueryCriterions().get(
          "bizStatus");
      String beginMonth = (String) pagination.getQueryCriterions().get(
          "beginMonth");
      String payMonth = (String) pagination.getQueryCriterions()
          .get("payMonth");
      String opTime = (String) pagination.getQueryCriterions().get("opTime");
      String payMonth1 = (String) pagination.getQueryCriterions()
      .get("payMonth1");
  String opTime1 = (String) pagination.getQueryCriterions().get("opTime1");
      String collBankId = (String) pagination.getQueryCriterions()
      .get("collBankId");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      list = addPayTailDAO.queryEmpaddpayListByCriterions_jj(orgId, unitName,
          addPayAmount, bizStatus, beginMonth, payMonth, payMonth1, orderBy, order,opTime, opTime1,securityInfo,collBankId);
      // 转换业务状态
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          EmpaddpayMaintainDTO dto1 = (EmpaddpayMaintainDTO) list.get(i);
          EmpaddpayMaintainDTO dto2 = new EmpaddpayMaintainDTO();
          BeanUtils.copyProperties(dto2, dto1);
          int isOrgEdition = securityInfo.getIsOrgEdition();
          if ( isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
            // 查询提取状态
            String statetype = autoInfoPickDAODW.findStatus(dto1.getOrgId(),dto1.getId().toString(),BusiConst.ORGBUSINESSTYPE_K);
            // 转换提取状态
            dto2.setTempPickType(BusiTools.getBusiValue(Integer.parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
          }
          dto2.setOrgId(BusiTools.convertSixNumber(dto1.getOrgId()));
          dto2.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto1
              .getPayStatus()), BusiConst.BUSINESSSTATE));
          returnlist.add(dto2);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnlist;
  }

  // 查询个人补缴清册明细合计
  public List findEmpaddpayListCount(Pagination pagination) throws Exception {
    List list = new ArrayList();
    Serializable paymentid = (Serializable) pagination.getQueryCriterions()
        .get("paymentid");
    list = addPayTailDAO.queryEmpaddpayList(paymentid.toString().trim());

    return list;
  }

  // 个人补缴金额合计
  public BigDecimal getEmpaddpayMoney(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    BigDecimal money = new BigDecimal(0.00);
    Serializable orgId = (Serializable) pagination.getQueryCriterions().get(
        "orgId");
    if (pagination.getQueryCriterions().get("orgId") != null) {
      orgId = pagination.getQueryCriterions().get("orgId").toString();
    }
    String unitName = (String) pagination.getQueryCriterions().get("unitName");
    String addPayAmount = (String) pagination.getQueryCriterions().get(
        "addPayAmount");
    String bizStatus = (String) pagination.getQueryCriterions()
        .get("bizStatus");
    String beginMonth = (String) pagination.getQueryCriterions().get(
        "beginMonth");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    String opTime = (String) pagination.getQueryCriterions().get("opTime");
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    List list = (List) addPayTailDAO.queryEmpaddpayAmount(orgId, unitName,
        addPayAmount, bizStatus, beginMonth, payMonth, opTime, securityInfo,collBankId);
    if (list != null) {
      EmpaddpayMaintainDTO empaddpayMaintainDTO = (EmpaddpayMaintainDTO) list
          .get(0);
      money = money.add(empaddpayMaintainDTO.getPay());
    }

    return money;
  }

  // 查询个人补缴信息
  public EmpAddPay findEmpAddPayInfo(Serializable headId) throws Exception {
    EmpAddPay empAddPay = null;
    empAddPay = empAddPayDAO.queryById(new Integer(headId.toString()));
    if (empAddPay == null) {
      empAddPay = new EmpAddPay();
    }
    return empAddPay;
  }
  public BigDecimal getOverPay(String noteNum){
    BigDecimal payMoney = empAddPayDAO.getPayMoney_jj(noteNum, "D");
    return payMoney;
  }
  // 删除个人补缴信息
  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 缴存维护_删除
   * @param empaddpayId AA301业务ID
   * @param securityInfo
   * @param 由PersonAddPayTaMaintainAC调用
   * @return
   * @throws Exception, BusinessException
   */
  public void deleteAddpay(Serializable empaddpayId, SecurityInfo securityInfo)
      throws Exception {
    // 修改记录：wangy 2008-02-26
    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(empaddpayId
        .toString()));
    // 判断是否为单位版 wangye 20071212
    int isOrgEdition = securityInfo.getIsOrgEdition();
    if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
      // 判断提交状态是否为未提取
      String commitSt = "";
      commitSt = autoInfoPickDAODW.findStatus(empAddPay.getOrg().getId()
          .toString(), empaddpayId.toString(), BusiConst.ORGBUSINESSTYPE_K);
      if (commitSt != null || commitSt != "") {
        if (commitSt.equals(BusiConst.OC_NOT_PICK_UP)) {// 未提取
          throw new BusinessException("请先撤销提交数据！");
        }
        if (commitSt.equals(BusiConst.OC_PICK_UP)) {// 已提取
          throw new BusinessException("中心已提取数据，不可以再删除！");
        }
      }
    } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
      // 更新DA001
      /*
       * 关联DA001.ORG_ID=ORG_ID and DA001.CENTER_HEAD_ID=AA301.ID and
       * DA001.TYPE=K 将记录更新为： DA001.STATUS=0（未提取）； DA001.CENTER_HEAD_ID=空
       */
      String center_head_id = "";
      autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
          center_head_id, "", empAddPay.getOrg().getId().toString(),
          empaddpayId.toString(), BusiConst.ORGBUSINESSTYPE_K);
    }// 修改到此结束
    // 删除AA304中全部记录的同时删除AA301中对应的记录
    // 删除304
    // List list=empAddPayDAO.queryPaymentTailListLg(empaddpayId);
    empAddPayDAO.deleteAll(new Integer(empaddpayId.toString()));
    // 删除301
    // EmpAddPay empAddPay = empAddPayDAO.queryById(new
    // Integer(empaddpayId.toString()));// 放在本方法首行_wangy
    empAddPayDAO.delete(empAddPay);
    Serializable orgid = empAddPay.getOrg().getId();
    // 删除AA319中BIZID=AA301.Id and AA319.action=1 and AA319.type=K的记录

    EmpAddPayBizActivityLog empAddPayBizActivityLog = empAddPayBizActivityLogDAO
        .queryEmpAddPayBizActivityLog_lg(empaddpayId, new Integer(1));
    empAddPayBizActivityLogDAO.delete(empAddPayBizActivityLog);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN));
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_DELETE));
    hafOperateLog.setOpBizId(new Integer(empaddpayId.toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgid.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  // 删除个人补缴信息
  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 办理缴存_全部删除
   * @param empaddpayId AA301业务ID
   * @param securityInfo
   * @param 由PersonAddPayTaMaintainAC调用
   * @return
   * @throws Exception, BusinessException
   */
  public void deleteAddpayDo(Serializable empaddpayId, SecurityInfo securityInfo)
      throws Exception {
    // 修改记录：wangy 2008-02-26
    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(empaddpayId
        .toString()));
    // 判断是否为单位版
    int isOrgEdition = securityInfo.getIsOrgEdition();
    if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
      // 判断提交状态是否为未提取
      String commitSt = "";
      commitSt = autoInfoPickDAODW.findStatus(empAddPay.getOrg().getId()
          .toString(), empaddpayId.toString(), BusiConst.ORGBUSINESSTYPE_K);
      if (commitSt != null || commitSt != "") {
        if (commitSt.equals(BusiConst.OC_NOT_PICK_UP)) {// 未提取
          throw new BusinessException("请先撤销提交数据！");
        }
        if (commitSt.equals(BusiConst.OC_PICK_UP)) {// 已提取
          throw new BusinessException("中心已提取数据，不可以再删除！");
        }
      }
    } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
      // 更新DA001
      /*
       * 关联DA001.ORG_ID=ORG_ID and DA001.CENTER_HEAD_ID=AA301.ID and
       * DA001.TYPE=K 将记录更新为： DA001.STATUS=0（未提取）； DA001.CENTER_HEAD_ID=空
       */
      String center_head_id = "";
      autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
          center_head_id, "", empAddPay.getOrg().getId().toString(),
          empaddpayId.toString(), BusiConst.ORGBUSINESSTYPE_K);
    }// 修改到此结束
    // 删除AA304中全部记录的同时删除AA301中对应的记录
    // 删除304
    // List list=empAddPayDAO.queryPaymentTailListLg(empaddpayId);
    empAddPayDAO.deleteAll(new Integer(empaddpayId.toString()));
    // 删除301
    // EmpAddPay empAddPay = empAddPayDAO.queryById(new
    // Integer(empaddpayId.toString()));// 放在本方法首行_wangy
    empAddPayDAO.delete(empAddPay);
    Serializable orgid = empAddPay.getOrg().getId();
    // 删除AA319中BIZID=AA301.Id and AA319.action=1 and AA319.type=K的记录

    EmpAddPayBizActivityLog empAddPayBizActivityLog = empAddPayBizActivityLogDAO
        .queryEmpAddPayBizActivityLog_lg(empaddpayId, new Integer(1));
    empAddPayBizActivityLogDAO.delete(empAddPayBizActivityLog);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO).toString());
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_DELETEALL));
    hafOperateLog.setOpBizId(new Integer(empaddpayId.toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOrgId(new Integer(orgid.toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLogDAO.insert(hafOperateLog);
  }

  public void repealAddpay(Serializable empaddpayId, SecurityInfo securityInfo)
      throws Exception {
    // 是否存在AA301.PAY_STATUS=1 and AA301.PAY_TYPE=C的记录

    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(empaddpayId
        .toString()));
    Serializable orgId = empAddPay.getOrg().getId();
    EmpAddPay emp = empAddPayDAO.queryEmpAddPayByOrgId(orgId, new Integer(1));
    if (emp.getId() != null) {
      throw new BusinessException("该单位存在未完成的补缴清册，不能撤销！");
    }
    // 更新AA301.PAY_STATUS=1
    empAddPay.setPayStatus(new Integer(1));
    // 删除AA319中AA319.BIZID=AA301.ID,AA319.ACTION=2,AA319.TYPE=B
    EmpAddPayBizActivityLog empAddPayBizActivityLog = empAddPayBizActivityLogDAO
        .queryEmpAddPayBizActivityLog(empaddpayId, new Integer(2));
    empAddPayBizActivityLogDAO.delete(empAddPayBizActivityLog);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN));
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_REVOCATION));
    hafOperateLog.setOpBizId(new Integer(empaddpayId.toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgId.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  public void overAddpay(Serializable empaddpayId, SecurityInfo securityInfo)
      throws Exception {

    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(empaddpayId
        .toString()));
    Serializable orgId = empAddPay.getOrg().getId();

    // 更新AA301.PAY_STATUS=2
    empAddPay.setPayStatus(new Integer(2));
    // 向AA319中插入业务活动日志。AA319.BIZID=AA301.ID,AA319.ACTION=2,AA319.TYPE=K
    EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
    empAddPayBizActivityLog.setBizid(new Integer(empAddPay.getId().toString()));
    empAddPayBizActivityLog.setAction(new Integer(2));
    empAddPayBizActivityLog.setOpTime(new Date());
    empAddPayBizActivityLog.setOperator(securityInfo.getUserName());
    empAddPayBizActivityLogDAO.insert(empAddPayBizActivityLog);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN));
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_OPENING));
    hafOperateLog.setOpBizId(new Integer(empAddPay.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgId.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  // 查询个人补缴清册明细
  public EmpaddpayMaintainDTO findEmpaddpay(Pagination pagination)
      throws Exception, BusinessException {
    List list = new ArrayList();
    List emplist = new ArrayList();
    EmpaddpayMaintainDTO empaddpayMaintainDTO = new EmpaddpayMaintainDTO();
    Serializable paymentid = (Serializable) pagination.getQueryCriterions()
        .get("paymentid");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String pkId=paymentid.toString().trim();
    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(pkId
        .toString()));
    String orgid = empAddPay.getOrg().getId().toString();
    // list=addPayTailDAO.queryEmpaddpayEmpList(paymentid.toString(), orderBy,
    // order, start, pageSize,page);
    list = empAddPayDAO.queryByHeadId(new Integer(pkId.toString()), null,
        orderBy, order, start, pageSize, page);
    if (list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        AddPayTail m = (AddPayTail) list.get(i);
        Emp emp = empDAO.queryByCriterions(m.getEmpId().toString(), orgid);

        // m.setEmpId(new
        // Integer(BusiTools.convertSixNumber(emp.getEmpId().toString())));
        m.setPersonId(BusiTools.convertSixNumber(emp.getEmpId().toString()));
        m.setEmpName(emp.getEmpInfo().getName());
        m.setAddPaySum(m.getOrgAddPaySum().add(m.getEmpAddPaySum()));
        emplist.add(m);
      }
    }

    List countlist = (List) empAddPayDAO.queryByHeadIdCount(new Integer(
        pkId.toString()));
    int count = countlist.size();
    pagination.setNrOfElements(count);
    empaddpayMaintainDTO.setNoteNum(empAddPay.getNoteNum());
    empaddpayMaintainDTO.setDocNum(empAddPay.getDocNum());
    empaddpayMaintainDTO.setList(emplist);
    empaddpayMaintainDTO.setOrgName(empAddPay.getOrg().getOrgInfo().getName());
    empaddpayMaintainDTO.setOrgId(BusiTools.convertSixNumber(orgid));

    return empaddpayMaintainDTO;
  }

  public List findEmpaddpayListPring(String paymentid) throws Exception {
    List list = new ArrayList();
    List list1 = new ArrayList();
    list = addPayTailDAO.queryByHeadId(new Integer(paymentid));
    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(paymentid));
    if (list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        AddPayTail addPayTail = (AddPayTail) list.get(i);
        AddPayExpDTO m = new AddPayExpDTO();
        Emp emp = empDAO.queryByCriterions(addPayTail.getEmpId().toString(),
            empAddPay.getOrg().getId().toString());
        m.setOrgId(BusiTools.convertSixNumber(empAddPay.getOrg().getId()
            .toString()));
        m.setOrgName(empAddPay.getOrg().getOrgInfo().getName());
        m.setNoteNum(empAddPay.getNoteNum());
        m.setDocNum(empAddPay.getDocNum());
        m.setEmpId(BusiTools.convertSixNumber(emp.getEmpId().toString()));
        m.setEmpName(emp.getEmpInfo().getName());
        m.setOrgPay(addPayTail.getOrgAddMoney().toString());
        m.setEmpPay(addPayTail.getEmpAddMoney().toString());
        m.setPaySum(addPayTail.getOrgAddMoney()
            .add(addPayTail.getEmpAddMoney()).toString());
        m.setReason(addPayTail.getAddReason());
        m.setBeginMonth(addPayTail.getBeginMonth());
        m.setEndMonth(addPayTail.getEndMonth());
        list1.add(m);
      }
    }
    return list1;
  }

  public EmpAddPay queryHead(Integer paymentHeadId) throws BusinessException {
    try {
      EmpAddPay empAddPay = empAddPayDAO
          .queryByHeadId(paymentHeadId.toString());
      return empAddPay;
    } catch (Exception e) {
      throw new BusinessException("查询失败!");
    }
  }

  public void updateHead(EmpAddPay empAddPay) throws BusinessException {
    // TODO Auto-generated method stub
    try {
      empAddPayDAO.updateEmpAddPay(empAddPay);
    } catch (Exception e) {
      throw new BusinessException("修改失败!");
    }
  }

  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 办理缴存_删除
   * @param tailId AA304ID
   * @param headId AA301业务ID
   * @param securityInfo
   * @param 由PersonAddPayTaMaintainAC调用
   * @return
   * @throws Exception, BusinessException
   */
  public void deleteEmpaddpayList(Serializable tailId, Serializable headId,
      SecurityInfo securityInfo) throws Exception {
    // 判断该记录是否为同批补缴中的最后一条记录
    // 尾表中最大ID值

    // String id = addPayTailDAO.queryEmpList(headId.toString());
    List lists = addPayTailDAO.queryEmpList(headId);
    int maxId = 0;
    int m = 0;
    BigDecimal addpayMoney = new BigDecimal(0.00);
    List list = addPayTailDAO.queryByEmpIdLg(new Integer(tailId.toString()),
        new Integer(headId.toString()));
    for (int i = 0; i < list.size(); i++) {
      AddPayTail addPayTail = (AddPayTail) list.get(i);
      m = new Integer(addPayTail.getId().toString()).intValue();
      addpayMoney = addpayMoney.add(addPayTail.getEmpAddMoney().add(
          addPayTail.getOrgAddMoney()));
      if (m > maxId) {
        maxId = m;
      }
    }
    EmpAddPay empAddPay = empAddPayDAO
        .queryById(new Integer(headId.toString()));
    // 删除AA304中对应的该职工补缴记录
    // addPayTailDAO.deleteAll(list);
    // String bigId=new Integer(maxId).toString();
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_DELETE));
    hafOperateLog.setOpBizId(new Integer(empAddPay.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(empAddPay.getOrg().getId().toString()));
    hafOperateLogDAO.insert(hafOperateLog);
    if (list.size() == lists.size()) {
      // 是最后一条记录
      // 删除AA301
      // 判断是否为单位版
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 1单位版
        // 判断提交状态是否为未提取
        String commitSt = "";
        commitSt = autoInfoPickDAODW.findStatus(empAddPay.getOrg().getId()
            .toString(), headId.toString(), BusiConst.ORGBUSINESSTYPE_K);
        if (commitSt != null || commitSt != "") {
          if (commitSt.equals(BusiConst.OC_NOT_PICK_UP)) {// 2未提取
            throw new BusinessException("请先撤销提交数据！");
          }
          if (commitSt.equals(BusiConst.OC_PICK_UP)) {// 1已提取
            throw new BusinessException("中心已提取数据，不可以再删除！");
          }
        }
      } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 2中心版
        // 更新DA001 关联DA001.ORG_ID=ORG_ID and DA001.CENTER_HEAD_ID=AA301.ID and
        // DA001.TYPE=K
        /*
         * 将记录更新为： DA001.STATUS=0（未提取）； DA001.CENTER_HEAD_ID=空
         */
        String center_head_id = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, "", empAddPay.getOrg().getId().toString(), headId
                .toString(), BusiConst.ORGBUSINESSTYPE_K);
      }
      List empList = (List) addPayTailDAO.queryByEmpId(new Integer(tailId
          .toString()), new Integer(headId.toString()));
      addPayTailDAO.deleteAll(empList);
      empAddPayDAO.delete(empAddPay);
      // 删除AA319中BIZID=AA301.Id and AA319.action=1 and AA319.type=C的记录
      EmpAddPayBizActivityLog empAddPayBizActivityLog = empAddPayBizActivityLogDAO
          .queryEmpAddPayBizActivityLoglg(empAddPay.getId(), new Integer(1));
      empAddPayBizActivityLogDAO.delete(empAddPayBizActivityLog);
    } else {
      List empList = (List) addPayTailDAO.queryByEmpId(new Integer(tailId
          .toString()), new Integer(headId.toString()));
      addPayTailDAO.deleteAll(empList);
      // 更新AA301中对应的金额信息
      BigDecimal payMoney = empAddPay.getPayMoney();
      payMoney = payMoney.subtract(addpayMoney);
      empAddPay.setPayMoney(payMoney);
    }

  }

  public void overAddpay(Serializable tailId, Serializable headId,
      SecurityInfo securityInfo, String noteNum,Pagination pagination) throws Exception {
    // 更新AA301.PAY_STATUS=2同时更新AA301票据编号为界面中的值
    String pay_kind=(String) pagination.getQueryCriterions().get("payKind");
    String pay_way=(String) pagination.getQueryCriterions().get("payWay");
    String p_bank_name=(String) pagination.getQueryCriterions().get("p_bank_name");
    String p_bank_acc=(String) pagination.getQueryCriterions().get("p_bank_acc");
    EmpAddPay empAddPay = empAddPayDAO
        .queryById(new Integer(headId.toString()));
    empAddPay.setPayStatus(new Integer(2));
    empAddPay.setNoteNum(noteNum);
    if(pay_kind != null && !"".equals(pay_kind)){
      empAddPay.setPay_kind(pay_kind);      
    }
    if(pay_way != null && !"".equals(pay_way)){
      empAddPay.setPay_way(pay_way);
    }
    if(p_bank_name != null && !"".equals(p_bank_name)){
      empAddPay.setPayment_bank_name(p_bank_name);      
    }
    if(p_bank_acc != null && !"".equals(p_bank_acc)){
      empAddPay.setPayment_bank_acc(p_bank_acc);
    }
    // 向AA319中插入业务活动日志AA319.BIZID=AA301.ID,AA319.ACTION=2,AA319.TYPE=C
    EmpAddPayBizActivityLog empaddPayBizActivityLog = new EmpAddPayBizActivityLog();
    empaddPayBizActivityLog.setBizid(new Integer(empAddPay.getId().toString()));
    empaddPayBizActivityLog.setAction(new Integer(2));
    empaddPayBizActivityLog.setOperator(securityInfo.getUserName());
    empaddPayBizActivityLog.setOpTime(new Date());
    empAddPayBizActivityLogDAO.insert(empaddPayBizActivityLog);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
    hafOperateLog.setOpButton(Integer
        .toString(BusiLogConst.BIZLOG_ACTION_OPENING));
    hafOperateLog.setOpBizId(new Integer(empAddPay.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(empAddPay.getOrg().getId().toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  // 批量导出
 /* public List findPaylist(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    List emplist = new ArrayList();
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String orgName = (String) pagination.getQueryCriterions().get("unitName");
    String noteNum = (String) pagination.getQueryCriterions().get("docNumber");
    List list = empDAO.queryByEmpIdlg(new Integer(orgId));
    if (list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        Emp emp = (Emp) list.get(i);
        EmpExpInfoDTO empExpInfoDTO = new EmpExpInfoDTO();
        empExpInfoDTO.setOrgId(BusiTools.convertSixNumber(orgId));
        empExpInfoDTO.setOrgName(orgName);
        empExpInfoDTO.setNoteNum(noteNum);
        empExpInfoDTO.setEmpId(BusiTools.convertSixNumber(emp.getEmpId()
            .toString()));
        empExpInfoDTO.setEmpName(emp.getEmpInfo().getName());
        emplist.add(empExpInfoDTO);
      }
    }
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer
        .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_EXP));
    hafOperateLog.setOpBizId(null);
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgId.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
    return emplist;
  }*/

  // 批量导入
  public List empaddPaylist(List addpayHeadImportList,
      List addpayListImportList, String orgid, String noteNum,
      SecurityInfo securityInfo,PersonAddPayAF personAddPayAF) throws Exception {
    List list = new ArrayList();
    BigDecimal money = new BigDecimal(0.00);
    BigDecimal orgmoney = new BigDecimal(0.00);
    BigDecimal empmoney = new BigDecimal(0.00);
    String orgId = BusiTools.convertSixNumber(orgid);
    if (addpayHeadImportList.size() <= 0) {
      throw new BusinessException("导入数据为空！");
    }
    if (addpayListImportList.size() <= 0) {
      throw new BusinessException("导入数据为空！");
    }
    EmpaddpayHeadImportDTO empaddpayHeadImportDTO = (EmpaddpayHeadImportDTO) addpayHeadImportList
        .get(1);
    // 判断单位编号是否相同
    if (!orgId.equals(empaddpayHeadImportDTO.getOrgId())) {
      throw new BusinessException("选择的导入文件与输入的单位编号不符！");
    }
    Org org = orgDAO.queryById(new Integer(empaddpayHeadImportDTO.getOrgId()));
    if(personAddPayAF.getPayment_orgname()!=null){
      if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
        if(!personAddPayAF.getPayment_orgname().equals(org.getOrgInfo().getReserveaB())){
          org.getOrgInfo().setReserveaB(personAddPayAF.getPayment_orgname());
        }
      }else{
        if(!personAddPayAF.getPayment_orgname().equals(org.getOrgInfo().getName())){
          org.getOrgInfo().setReserveaB(personAddPayAF.getPayment_orgname());
        }
      }
    }
    
    for (int i = 1; i < addpayListImportList.size(); i++) {
      EmpaddpayListImportDTO empaddpayListImportDTO = (EmpaddpayListImportDTO) addpayListImportList
          .get(i);
      UtilRule utilRule = new UtilRule();
      EmpExpInfoDTO ad = new EmpExpInfoDTO();

      if (empaddpayListImportDTO.getEmpPay().equals("")) {
        throw new BusinessException("尚未输入职工补缴金额！");
      }
      if (empaddpayListImportDTO.getOrgPay().equals("")) {
        throw new BusinessException("尚未输入单位补缴金额！");
      }
      if (empaddpayListImportDTO.getBeginMonth().equals("")) {
        throw new BusinessException("尚未输入补缴开始年月！");
      }
      if (empaddpayListImportDTO.getEndMonth().equals("")) {
        throw new BusinessException("尚未输入补缴结束年月！");
      }
      if (empaddpayListImportDTO.getOrgPay().equals("")) {
        throw new BusinessException("尚未输入单位补缴金额！");
      }
      if("".equals(empaddpayListImportDTO.getAddPayType())){
        throw new BusinessException("尚未输入类型！");
      }
      if (utilRule.moneyRule(empaddpayListImportDTO.getEmpPay(), 15, 2) == false) {

        throw new BusinessException("输入的金额有误！");
      }
      if (utilRule.moneyRule(empaddpayListImportDTO.getOrgPay(), 15, 2) == false) {

        throw new BusinessException("输入的金额有误！");
      }
      orgmoney = orgmoney
          .add(new BigDecimal(empaddpayListImportDTO.getOrgPay()));
      empmoney = empmoney
          .add(new BigDecimal(empaddpayListImportDTO.getEmpPay()));
      money = orgmoney.add(empmoney);
    }
    // 插入301
    EmpAddPay empAddPay = new EmpAddPay();
    empAddPay.setNoteNum(empaddpayHeadImportDTO.getNoteNum());
    empAddPay.setOrg(org);
    empAddPay.setPayMoney(money);
    empAddPay.setPayStatus(new Integer(1));
    empAddPay.setReceivables_bank_name(personAddPayAF.getReceivables_bank_name());
    empAddPay.setReceivables_bank_acc(personAddPayAF.getReceivables_bank_acc());
    empAddPay.setPayment_bank_name(personAddPayAF.getPayment_bank_name());
    empAddPay.setPayment_bank_acc(personAddPayAF.getPayment_bank_acc());
    empAddPay.setPay_way(personAddPayAF.getPayWay());
    empAddPay.setPay_kind(personAddPayAF.getPayKind());
    // 修改记录：wangy 2008-02-26
    Serializable centerHeadId = empAddPayDAO.insert(empAddPay);
    center_head_id = centerHeadId.toString();

    // 插入304

    BigDecimal orgmonthpay = new BigDecimal(0.00);
    BigDecimal empmonthpay = new BigDecimal(0.00);
    BigDecimal orgPaySum = new BigDecimal(0.00);
    BigDecimal empPaySum = new BigDecimal(0.00);
    for (int i = 1; i < addpayListImportList.size(); i++) {
      EmpaddpayListImportDTO empaddpayListImportDTO = (EmpaddpayListImportDTO) addpayListImportList
          .get(i);
System.out.println("---------------->"+empaddpayListImportDTO.getAddPayType());
      UtilRule utilRule = new UtilRule();
      EmpExpInfoDTO ad = new EmpExpInfoDTO();
      if (utilRule.moneyRule(empaddpayListImportDTO.getEmpPay(), 15, 2) == false) {
        ad.setEmpPay(empaddpayListImportDTO.getEmpPay());
        list.add(ad);
        continue;
      }
      if (utilRule.moneyRule(empaddpayListImportDTO.getOrgPay(), 15, 2) == false) {
        ad.setOrgPay(empaddpayListImportDTO.getOrgPay());
        list.add(ad);
        continue;
      }
      // 根据单位编号找到AA002中对应职工，判断导入文件中的职工是否和包含于这些职工中
      Emp emp = empDAO.queryByCriterions(empaddpayListImportDTO.getEmpId(),
          orgid);
      if (emp == null) {
        throw new BusinessException("导入的职工不存在于本单位");
      }
      String beginMonth = empaddpayListImportDTO.getBeginMonth();
      String endMonth = empaddpayListImportDTO.getEndMonth();
      int m = BusiTools.monthInterval(beginMonth, endMonth);
      BigDecimal j = new BigDecimal(new Integer(m + 1).toString());
      if (m == 0) {
        AddPayTail addPayTail = new AddPayTail();
        addPayTail.setEmpId(new Integer(empaddpayListImportDTO.getEmpId()));
        addPayTail.setEmpName(empaddpayListImportDTO.getEmpName());
        addPayTail.setOrgAddMoney(new BigDecimal(empaddpayListImportDTO
            .getOrgPay()));
        addPayTail.setEmpAddMoney(new BigDecimal(empaddpayListImportDTO
            .getEmpPay()));
        addPayTail.setAddPaySum(addPayTail.getOrgAddMoney().add(
            addPayTail.getEmpAddMoney()));
        addPayTail.setAddMonht(empaddpayListImportDTO.getBeginMonth());
        addPayTail.setAddReason(empaddpayListImportDTO.getReason());
        addPayTail.setPaymentHead(empAddPay);
        addPayTail.setSalaryBase(emp.getSalaryBase());
        addPayTail.setOrgRate(emp.getOrg().getOrgRate());
        addPayTail.setEmpRate(emp.getOrg().getEmpRate());
        addPayTail.setReserveaC(empaddpayListImportDTO.getAddPayType());
        addPayTailDAO.insert(addPayTail);
      } else {
        orgPaySum = new BigDecimal(empaddpayListImportDTO.getOrgPay());
        empPaySum = new BigDecimal(empaddpayListImportDTO.getEmpPay());
        BigDecimal orgtemp = new BigDecimal(0.00);
        orgtemp = new BigDecimal(orgPaySum.doubleValue() % j.intValue());
        BigDecimal emptemp = new BigDecimal(0.00);
        emptemp = new BigDecimal(empPaySum.doubleValue() % j.intValue());
        orgmonthpay = (orgPaySum.subtract(orgtemp)).divide(j, 2,
            BigDecimal.ROUND_HALF_UP);
        empmonthpay = (empPaySum.subtract(emptemp)).divide(j, 2,
            BigDecimal.ROUND_HALF_UP);
        for (int k = 0; k < j.intValue(); k++) {
          AddPayTail addPayTail = new AddPayTail();
          orgPaySum = new BigDecimal(empaddpayListImportDTO.getOrgPay());
          empPaySum = new BigDecimal(empaddpayListImportDTO.getEmpPay());
          // orgmonthpay=orgPaySum.divide(j,2,BigDecimal.ROUND_HALF_UP);
          // empmonthpay=empPaySum.divide(j,2,BigDecimal.ROUND_HALF_UP);
          addPayTail.setEmpId(new Integer(empaddpayListImportDTO.getEmpId()));
          addPayTail.setEmpName(empaddpayListImportDTO.getEmpName());
          if (k != j.intValue() - 1) {
            addPayTail.setOrgAddMoney(orgmonthpay);
            addPayTail.setEmpAddMoney(empmonthpay);
          } else {
            addPayTail.setOrgAddMoney(orgmonthpay.add(orgtemp));
            addPayTail.setEmpAddMoney(empmonthpay.add(emptemp));
          }
          // addPayTail.setOrgAddMoney(orgmonthpay);
          // addPayTail.setEmpAddMoney(empmonthpay);
          addPayTail.setAddPaySum(empmonthpay.add(orgPaySum));
          addPayTail.setAddMonht(beginMonth);
          addPayTail.setAddReason(empaddpayListImportDTO.getReason());
          addPayTail.setPaymentHead(empAddPay);
          addPayTail.setSalaryBase(emp.getSalaryBase());
          addPayTail.setOrgRate(emp.getOrg().getOrgRate());
          addPayTail.setEmpRate(emp.getOrg().getEmpRate());
          addPayTail.setReserveaC(empaddpayListImportDTO.getAddPayType());
          addPayTailDAO.insert(addPayTail);
          beginMonth = BusiTools.addMonth(beginMonth, 1);
        }
      }
    }
    // 插入319向AA319中插入业务活动日志。AA319.BIZID=AA301.ID,AA319.ACTION=1,AA319.TYPE=K
    EmpAddPayBizActivityLog addPayBizActivityLog = new EmpAddPayBizActivityLog();
    addPayBizActivityLog.setBizid(new Integer(empAddPay.getId().toString()));
    addPayBizActivityLog.setAction(new Integer(1));
    addPayBizActivityLog.setOperator(securityInfo.getUserName());
    addPayBizActivityLog.setOpTime(new Date());
    empAddPayBizActivityLogDAO.insert(addPayBizActivityLog);
    // 修改记录：wangy 2008-02-26
    EmpaddpayHeadImportDTO empaddpayHeadImportDTO1 = (EmpaddpayHeadImportDTO)addpayHeadImportList.get(0);
    if (!"".equals(empaddpayHeadImportDTO1.getOrgId())) {
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer
          .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
      hafOperateLog
          .setOpButton(Integer.toString(BusiLogConst.BIZBLOG_ACTION_IMP));
      hafOperateLog.setOpBizId(new Integer(empAddPay.getId().toString()));
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgid.toString()));
      hafOperateLogDAO.insert(hafOperateLog);
    }
    return list;
  }

  public EmpaddpayHeadPrintDTO findEmpaddpayPring(String paymentid)
      throws Exception {
    int count = 0;
    EmpaddpayHeadPrintDTO dto = new EmpaddpayHeadPrintDTO();
    EmpAddPay empAddPay = new EmpAddPay();
    empAddPay = (EmpAddPay) empAddPayDAO.queryById(new Integer(paymentid));
    count = addPayTailDAO.queryByHeadId(new Integer(paymentid)).size();
    dto.setDocNum(empAddPay.getDocNum());
    dto.setOrgId(BusiTools.convertSixNumber(empAddPay.getOrg().getId()
        .toString()));
    dto.setOrgName(empAddPay.getOrg().getOrgInfo().getName().toString());
    dto.setPay(empAddPay.getPayMoney().toString());
    dto.setPayMonth(empAddPay.getSettDate());
    dto.setPersonCounts(count + "");
    return dto;
  }

  public Emp findEmpInfo(String empId, String orgId) throws Exception {
    Emp emp = empDAO.queryByCriterions(empId, orgId);
    return emp;
  }

  public EmpAddPay findHeadId(String orgId) throws Exception {
    EmpAddPay empAddPay = empAddPayDAO.queryEmpAddPayByOrgId(orgId,
        new Integer(1));
    return empAddPay;
  }

  public CollBankDAO getCollBankDAO() {
    return collBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  /**
   * Description 个人补缴
   * 
   * @author wangy 2008-02-26
   * @param 缴存维护_提交数据
   * @param empaddpayId AA301业务ID
   * @param securityInfo
   * @param 由EmpAddPayTbMaintainAC调用
   * @return 
   * @throws Exception, BusinessException
   */
  public void insertEmpAddPayReferringData(Serializable empaddpayId, SecurityInfo securityInfo) throws BusinessException {
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(empaddpayId.toString()));
    Serializable orgId = empAddPay.getOrg().getId();
    boolean isNoPickIn = autoInfoPickDAODW.isNOPickIn(empAddPay.getOrg().getId().toString(),empaddpayId.toString(), BusiConst.ORGBUSINESSTYPE_K);
    // 如果存在未提交的，提示先撤销提交
    if (isNoPickIn) {
      throw new BusinessException("该笔业务已经提交！");
    } else {
      // 插入DA001
      Integer centerHeadId = null;
      Integer payHeadId = null;
      AutoInfoPick autoInfoPick=new AutoInfoPick();
      autoInfoPick.setOrgId(new Integer(orgId.toString()));
      autoInfoPick.setOrgHeadId(new Integer(empaddpayId.toString()));
      autoInfoPick.setCenterHeadId(centerHeadId);
      autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_K);// 个人补缴
      autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
      autoInfoPick.setOrgBizDate(new Date());
      autoInfoPick.setPayHeadId(payHeadId);
      autoInfoPickDAODW.insert(autoInfoPick);
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN));
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
      hafOperateLog.setOpBizId(new Integer(empaddpayId.toString()));// AA301.ID
      hafOperateLog.setOperator(name);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId.toString()));
      hafOperateLogDAO.insert(hafOperateLog);
    }
  }

  /**
   * Description 个人补缴
   * 
   * @author wangy 2008-02-26
   * @param 缴存维护_撤销提交数据
   * @param empaddpayId AA301业务ID
   * @param securityInfo
   * @param 由EmpAddPayTbMaintainAC调用
   * @return 
   * @throws Exception, BusinessException
   */
  public void deleteAddpayPproval(Serializable empaddpayId, SecurityInfo securityInfo) throws BusinessException {
    try {
      EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(empaddpayId.toString()));
      Serializable orgId = empAddPay.getOrg().getId();
      String status=autoInfoPickDAODW.findStatus(orgId.toString(), empaddpayId.toString(), BusiConst.ORGBUSINESSTYPE_K);
      // 判断DA001.STATUS是否为0(未提取)
      if(status.equals(BusiConst.OC_NOT_PICK_UP)){
        //删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgId.toString(), empaddpayId.toString(), BusiConst.ORGBUSINESSTYPE_K);
        // 插入BA003：
        String ip=securityInfo.getUserInfo().getUserIp();
        String name=securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN));
        hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
        hafOperateLog.setOpBizId(new Integer(empaddpayId.toString()));// AA301.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgId.toString()));
        hafOperateLogDAO.insert(hafOperateLog);       
      } else {
        throw new BusinessException("该业务已被中心提取，不可以撤销！");
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }   
  }

  /**
   * Description 个人补缴
   * 
   * @author wangy 2008-02-26
   * @param 办理缴存_提交数据
   * @param orgId 单位ID
   * @param paymentId AA301业务ID
   * @param securityInfo
   * @param 由PersonAddPayTaMaintainAC调用
   * @return 
   * @throws Exception, BusinessException
   */
  public void insertEmpAddPayReferringData(String orgId, String paymentId, SecurityInfo securityInfo) throws BusinessException {
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    boolean isNoPickIn = autoInfoPickDAODW.isNOPickIn(orgId, paymentId, BusiConst.ORGBUSINESSTYPE_K);
    // 如果存在未提交的，提示先撤销提交
    if (isNoPickIn) {
      throw new BusinessException("该笔业务已经提交！");
    } else {
      // 插入DA001
      Integer centerHeadId = null;
      Integer payHeadId = null;
      AutoInfoPick autoInfoPick=new AutoInfoPick();
      autoInfoPick.setOrgId(new Integer(orgId));
      autoInfoPick.setOrgHeadId(new Integer(paymentId));
      autoInfoPick.setCenterHeadId(centerHeadId);
      autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_K);// 个人补缴
      autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
      autoInfoPick.setOrgBizDate(new Date());
      autoInfoPick.setPayHeadId(payHeadId);
      autoInfoPickDAODW.insert(autoInfoPick);
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
      hafOperateLog.setOpBizId(new Integer(paymentId));// AA301.ID
      hafOperateLog.setOperator(name);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId));
      hafOperateLogDAO.insert(hafOperateLog);
    }
  }

  /**
   * Description 个人补缴
   * 
   * @author wangy 2008-02-26
   * @param 办理缴存_撤销提交数据
   * @param orgId 单位ID
   * @param paymentId AA301业务ID
   * @param securityInfo
   * @param 由PersonAddPayTaMaintainAC调用
   * @return 
   * @throws Exception, BusinessException
   */
  public void deleteAddpayPproval(String orgId, String paymentId, SecurityInfo securityInfo) throws BusinessException {
    try {
      String status=autoInfoPickDAODW.findStatus(orgId, paymentId, BusiConst.ORGBUSINESSTYPE_K);
      // 判断DA001.STATUS是否为0(未提取)
      if(status.equals(BusiConst.OC_NOT_PICK_UP)){
        //删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgId.toString(), paymentId, BusiConst.ORGBUSINESSTYPE_K);
        // 插入BA003
        String ip=securityInfo.getUserInfo().getUserIp();
        String name=securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
        hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
        hafOperateLog.setOpBizId(new Integer(paymentId));// AA301.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgId));
        hafOperateLogDAO.insert(hafOperateLog);       
      } else {
        if(status.equals(BusiConst.OC_PICK_UP)){
          throw new BusinessException("该业务已被中心提取，不可以撤销！");
        }
        if(status.equals(BusiConst.OC_PICK_UP_OVER)){
          throw new BusinessException("该笔业务没有提交，不可以撤销！");
        }
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }   
  }

  /**
   * Description 个人补缴
   * 
   * @author wangy 2008-02-26
   * @param 办理缴存_提取数据
   * @param orgId 单位ID
   * @param securityInfo
   * @param 由PersonAddPayTaMaintainAC调用
   * @return 
   * @throws Exception, BusinessException
   */
  public void importsAddpayPickup(String orgId, SecurityInfo securityInfo) throws Exception {
      // 判断DA001中是否存在DA001.ORG_ID=ORG_ID，DA001.STATUS=0（未提取），DA001.TYPE=K的记录
      int count = autoInfoPickDAO.queryPickUpCountbyOrgidAndStatusAndType(new Integer(orgId));
      if (count > 0) {
        // 取出最小的DA001.ORG_HEAD_ID
        String minid=autoInfoPickDAO.findOrgHeadid(orgId, BusiConst.ORGBUSINESSTYPE_K, BusiConst.OC_NOT_PICK_UP);// 最小的头表id
        // 通过最小的DA001.ORG_HEAD_ID，关联单位库AA304.PAY_HEAD_ID查询AA304中的记录
        //AddPayTail
        List addpayListImportList=addPayTailDAODW.queryAddPayTailPickUpList(minid);// 关联单位库AA304.PAY_HEAD_ID查询AA304中的记录
        // 插入中心数据库（同导入方法）
        EmpAddPay empAddPay = empAddPayDAODW.queryById(new Integer(minid));
        List addpayHeadImportList=new ArrayList();
        EmpaddpayHeadImportDTO empaddpayHeadImportDTO1 = new EmpaddpayHeadImportDTO();
        EmpaddpayHeadImportDTO empaddpayHeadImportDTO2 = new EmpaddpayHeadImportDTO();
        empaddpayHeadImportDTO2.setOrgId(BusiTools.convertSixNumber(empAddPay.getOrg().getId().toString()));
        empaddpayHeadImportDTO2.setNoteNum(empAddPay.getNoteNum());
        addpayHeadImportList.add(empaddpayHeadImportDTO1);// get(1)
        addpayHeadImportList.add(empaddpayHeadImportDTO2);
        //this.empaddPaylist(addpayHeadImportList, addpayListImportList, orgId, empAddPay.getNoteNum(), securityInfo);
        // 更新DA001表
        Date sysDate = new Date();
        autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP, center_head_id, sysDate.toString(), orgId, empAddPay.getId().toString(), BusiConst.ORGBUSINESSTYPE_K);
        // 向BA003中插入操作日志信息
        // 插入BA003
        String ip=securityInfo.getUserInfo().getUserIp();
        String name=securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
        hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));// 提取数据
        hafOperateLog.setOpBizId(new Integer(center_head_id));// AA301.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgId));
        hafOperateLogDAO.insert(hafOperateLog);       
      } else {
        // 否 该单位不存在未提取的记录！
        throw new BusinessException("该单位不存在未提取的记录！");
      } 
  }
  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    // 查询办事处银行开始
    String officeName = "";
    String bankName = "";
    String str[]=new String[2];
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
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(org.getOrgInfo().getCollectionBankId());
        bankName = collBank.getCollBankName();
      }
    }
    //查询办事处银行结束
    str[0]=officeName;
    str[1]=bankName;
    return str;
  }
  
  
  public List findPaylist(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    List emplist = new ArrayList();
    String orgId=(String)pagination.getQueryCriterions().get("orgId");
    String orgName=(String)pagination.getQueryCriterions().get("unitName");
    String noteNum=(String)pagination.getQueryCriterions().get("docNumber");
    String orderArray[] = (String[]) pagination.getQueryCriterions().get("orderArray");
    Org org=orgDAO.queryById(new Integer(orgId));
    if(org != null )
      orgName=org.getOrgInfo().getName();
    List list=empDAO.queryByEmpIdLP(new Integer(orgId),orderArray,"");     
    if(list.size()>0){
      for(int i=0; i<list.size();i++){
        Emp emp=(Emp)list.get(i);
        EmpExpInfoDTO empExpInfoDTO = new EmpExpInfoDTO();
        empExpInfoDTO.setOrgId(BusiTools.convertSixNumber(orgId));
        empExpInfoDTO.setOrgName(orgName);
        empExpInfoDTO.setNoteNum(noteNum);
        empExpInfoDTO.setEmpId(BusiTools.convertSixNumber(emp.getEmpId().toString()));
        empExpInfoDTO.setEmpName(emp.getEmpInfo().getName());
        
        if(emp.getOldEmpID()==null){
          emp.setOldEmpID("");
        }
       
        emplist.add(empExpInfoDTO);
      }
    }    
  //插入BA003
  HafOperateLog hafOperateLog = new HafOperateLog();
  hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
  hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
  hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_EXP));
  hafOperateLog.setOpBizId(null);
  hafOperateLog.setOperator(securityInfo.getUserName());
  hafOperateLog.setOpIp(securityInfo.getUserIp());
  hafOperateLog.setOpTime(new Date());
  hafOperateLog.setOrgId(new Integer(orgId.toString()));
  hafOperateLogDAO.insert(hafOperateLog);
  return emplist;
}
  //
  /**
   * Description 查询单位下是否存在为完成的个人补缴
   * 
   * @author王硕 
   * @param orgId 单位ID
   * @param securityInfo
   * @param 由PersonAddPayTaMaintainAC调用
   * @return 
   * @throws Exception, BusinessException
   */
  public String findOrgOver(String orgId) throws BusinessException {
    // TODO Auto-generated method stub
    String type="";
    try {
      type = orgDAO.findOrgOver(orgId);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return type;
  }
  public void insertEmpAddPay(String orgId, String paymentHeadId,SecurityInfo securityInfo,String addpayDateSt,String addpayDateEnd,String noteNum,String noteNum_1) throws BusinessException {
    // TODO Auto-generated method stub
    try {
      List list = new ArrayList();
      BigDecimal money = new BigDecimal(0.00);
      BigDecimal orgmoney = new BigDecimal(0.00);
      BigDecimal empmoney = new BigDecimal(0.00);
      String orgid = BusiTools.convertSixNumber(orgId);
     
      Org org = orgDAO.queryById(new Integer(orgid));
      List addpayListImportList=new ArrayList();
      List addpayListImportList_1=new ArrayList();
      addpayListImportList=addPayTailDAO.queryByHeadId_wsh(noteNum_1);
     
      for (int i = 0; i < addpayListImportList.size(); i++) {
      
        UtilRule utilRule = new UtilRule();
        EmpExpInfoDTO ad = new EmpExpInfoDTO();

        Iterator it=addpayListImportList.iterator();
      Object obj[]=null;
      while(it.hasNext()){
        obj=(Object[])it.next();
        EmpaddpayListImportDTO empaddpayListImportDTO = new EmpaddpayListImportDTO();
        empaddpayListImportDTO.setEmpId(obj[0].toString());
        empaddpayListImportDTO.setBeginMonth(addpayDateSt);
        empaddpayListImportDTO.setEndMonth(addpayDateEnd);
        empaddpayListImportDTO.setOrgPay(obj[1].toString());
        empaddpayListImportDTO.setEmpPay(obj[2].toString());
       
        if((obj[3]!=null)&&(obj[3]!="")){
          empaddpayListImportDTO.setReason(obj[3].toString());
        }
        else{ 
          empaddpayListImportDTO.setReason("");
        }
        orgmoney = orgmoney
        .add(new BigDecimal(empaddpayListImportDTO.getOrgPay()));
    empmoney = empmoney
        .add(new BigDecimal(empaddpayListImportDTO.getEmpPay()));
    money = orgmoney.add(empmoney);
        addpayListImportList_1.add(empaddpayListImportDTO);
      }
      }
      // 插入301
      EmpAddPay empAddPay = new EmpAddPay();
      empAddPay.setNoteNum(noteNum);
      empAddPay.setOrg(org);
      empAddPay.setPayMoney(money);
      empAddPay.setPayStatus(new Integer(1));
      // 修改记录：wangy 2008-02-26
      Serializable centerHeadId = empAddPayDAO.insert(empAddPay);
      center_head_id = centerHeadId.toString();
      // 插入304
      BigDecimal orgmonthpay = new BigDecimal(0.00);
      BigDecimal empmonthpay = new BigDecimal(0.00);
      BigDecimal orgPaySum = new BigDecimal(0.00);
      BigDecimal empPaySum = new BigDecimal(0.00);
      for (int i = 0; i < addpayListImportList_1.size(); i++) {
        EmpaddpayListImportDTO empaddpayListImportDTO = (EmpaddpayListImportDTO) addpayListImportList_1
            .get(i);
        UtilRule utilRule = new UtilRule();
        EmpExpInfoDTO ad = new EmpExpInfoDTO();
        if (utilRule.moneyRule(empaddpayListImportDTO.getEmpPay(), 15, 2) == false) {
          ad.setEmpPay(empaddpayListImportDTO.getEmpPay());
          list.add(ad);
          continue;
        }
        if (utilRule.moneyRule(empaddpayListImportDTO.getOrgPay(), 15, 2) == false) {
          ad.setOrgPay(empaddpayListImportDTO.getOrgPay());
          list.add(ad);
          continue;
        }
        // 根据单位编号找到AA002中对应职工，判断导入文件中的职工是否和包含于这些职工中
//        Emp emp = empDAO.queryByCriterions(empaddpayListImportDTO.getEmpId(),
//            orgid);
//        if (emp == null) {
//          throw new BusinessException("导入的职工不存在于本单位");
//        }
        String beginMonth = empaddpayListImportDTO.getBeginMonth();
        String endMonth = empaddpayListImportDTO.getEndMonth();
        int m = BusiTools.monthInterval(beginMonth, endMonth);
        BigDecimal j = new BigDecimal(new Integer(m + 1).toString());
        if (m == 0) {
          AddPayTail addPayTail = new AddPayTail();
          addPayTail.setEmpId(new Integer(empaddpayListImportDTO.getEmpId()));
          addPayTail.setEmpName(empaddpayListImportDTO.getEmpName());
          addPayTail.setOrgAddMoney(new BigDecimal(empaddpayListImportDTO
              .getOrgPay()));
          addPayTail.setEmpAddMoney(new BigDecimal(empaddpayListImportDTO
              .getEmpPay()));
          addPayTail.setAddPaySum(addPayTail.getOrgAddMoney().add(
              addPayTail.getEmpAddMoney()));
          addPayTail.setAddMonht(empaddpayListImportDTO.getBeginMonth());
          addPayTail.setAddReason(empaddpayListImportDTO.getReason());
          addPayTail.setPaymentHead(empAddPay);
          addPayTailDAO.insert(addPayTail);
        } else {
          orgPaySum = new BigDecimal(empaddpayListImportDTO.getOrgPay());
          empPaySum = new BigDecimal(empaddpayListImportDTO.getEmpPay());
          BigDecimal orgtemp = new BigDecimal(0.00);
          orgtemp = new BigDecimal(orgPaySum.doubleValue() % j.intValue());
          BigDecimal emptemp = new BigDecimal(0.00);
          emptemp = new BigDecimal(empPaySum.doubleValue() % j.intValue());
          orgmonthpay = (orgPaySum.subtract(orgtemp)).divide(j, 2,
              BigDecimal.ROUND_HALF_UP);
          empmonthpay = (empPaySum.subtract(emptemp)).divide(j, 2,
              BigDecimal.ROUND_HALF_UP);
          for (int k = 0; k < j.intValue(); k++) {
            AddPayTail addPayTail = new AddPayTail();
            orgPaySum = new BigDecimal(empaddpayListImportDTO.getOrgPay());
            empPaySum = new BigDecimal(empaddpayListImportDTO.getEmpPay());
            // orgmonthpay=orgPaySum.divide(j,2,BigDecimal.ROUND_HALF_UP);
            // empmonthpay=empPaySum.divide(j,2,BigDecimal.ROUND_HALF_UP);
            addPayTail.setEmpId(new Integer(empaddpayListImportDTO.getEmpId()));
            addPayTail.setEmpName(empaddpayListImportDTO.getEmpName());
            if (k != j.intValue() - 1) {
              addPayTail.setOrgAddMoney(orgmonthpay);
              addPayTail.setEmpAddMoney(empmonthpay);
            } else {
              addPayTail.setOrgAddMoney(orgmonthpay.add(orgtemp));
              addPayTail.setEmpAddMoney(empmonthpay.add(emptemp));
            }
            // addPayTail.setOrgAddMoney(orgmonthpay);
            // addPayTail.setEmpAddMoney(empmonthpay);
            addPayTail.setAddPaySum(empmonthpay.add(orgPaySum));
            addPayTail.setAddMonht(beginMonth);
            addPayTail.setAddReason(empaddpayListImportDTO.getReason());
            addPayTail.setPaymentHead(empAddPay);
            addPayTailDAO.insert(addPayTail);
            beginMonth = BusiTools.addMonth(beginMonth, 1);
          }
        }
      }
      // 插入319向AA319中插入业务活动日志。AA319.BIZID=AA301.ID,AA319.ACTION=1,AA319.TYPE=K
      EmpAddPayBizActivityLog addPayBizActivityLog = new EmpAddPayBizActivityLog();
      addPayBizActivityLog.setBizid(new Integer(empAddPay.getId().toString()));
      addPayBizActivityLog.setAction(new Integer(1));
      addPayBizActivityLog.setOperator(securityInfo.getUserName());
      addPayBizActivityLog.setOpTime(new Date());
      empAddPayBizActivityLogDAO.insert(addPayBizActivityLog);
      // 修改记录：wangy 2008-02-26
     
     
        // 插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO));
        hafOperateLog
            .setOpButton(Integer.toString(BusiLogConst.BIZBLOG_ACTION_IMP));
        hafOperateLog.setOpBizId(new Integer(empAddPay.getId().toString()));
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgid.toString()));
        hafOperateLogDAO.insert(hafOperateLog);
    
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
  
//刘聃修改：判断AA301.PAY_STATUS是否为2
  public boolean overAddpaya(Serializable empaddpayId, SecurityInfo securityInfo){
     boolean flag=true;
   try{
     String statue = empAddPayDAO.queryStatusById(empaddpayId);
    if(statue.equals("2")){
      flag=false;
    }

    else{
      flag=true;
    }
   }catch(Exception e){
     e.printStackTrace();
   }
  
    return flag;
  }
  /**
   * ld-add修改，防止个人补缴删除发生并发操作
   */
   public List querytest(String id){
    List list=new ArrayList();
    list=empAddPayDAO.querytestbyid(id);
     return list;
   }
   public List querytest1(String id){
     List list=new ArrayList();
     list=empAddPayDAO.querytestbyid1(id);
      return list;
    }
   /**
    *ld
    */
   public List querydeleteall(Pagination pagination){
     List list = new ArrayList();
     
     String paymentHeadId = (String) pagination.getQueryCriterions().get(
         "paymentHeadId");
     String orderBy = (String) pagination.getOrderBy();
     
     String order = (String) pagination.getOrderother();
     int start = pagination.getFirstElementOnPage() - 1;
     int pageSize = pagination.getPageSize();
     int page = pagination.getPage();
     Integer headId = new Integer(paymentHeadId);
     list = empAddPayDAO.queryByHeadId(headId, null, orderBy, order,
         start, pageSize, page);
    return list;
   }
   public MonthpayJYAF findPayInfo(String paymentId) throws Exception {
     MonthpayJYAF f = new MonthpayJYAF();
     EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(paymentId.toString()));
     f.setPayment_bank_acc(empAddPay.getPayment_bank_acc());
     f.setPayment_bank_name(empAddPay.getPayment_bank_name());
     f.setOrgid(empAddPay.getOrg().getId().toString());
     f.setName(empAddPay.getOrg().getOrgInfo().getName());
     f.setMonthpayHeadId(empAddPay.getId().toString());
     return f;
   }
   public void updatePaymentInfo(Serializable paymentId,String bankName,String bankAcc,SecurityInfo securityInfo) throws Exception{
     //删除301
     EmpAddPay empAddPay = empAddPayDAO.queryById(new Integer(paymentId.toString()));
     empAddPay.setPayment_bank_name(bankName);
     empAddPay.setPayment_bank_acc(bankAcc);
   }
}
