package org.xpup.hafmis.syscollection.accounthandle.clearaccount.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceForm;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountDetailAF;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountShowAF;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInTailDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPaymentBalanceEndTransfer;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

/**
 * @author 李鹏 2007-7-10
 */
public class ClearaccountBS implements IclearAccountBS {

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private EmpDAO empDAO = null;

  private OrgDAO orgDAO = null;

  private DocNumMaintainDAO docNumMaintainDAO = null;

  private PaymentHeadDAO paymentHeadDAO = null;

  private TranInHeadDAO tranInHeadDAO = null;

  private TranOutHeadDAO tranOutHeadDAO = null;

  private DocNumCancelDAO docNumCancelDAO = null;

  private CollBankDAO collBankDAO = null;

  private TranInTailDAO tranInTailDAO = null;

  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;

  private OrgExcessPaymentDAO orgExcessPaymentDAO = null;

  private OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO = null;

  private OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO = null;

  private SecurityDAO securityDAO = null;

  public void setOrgHAFAccountFlowExcessPaymentDAO(
      OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO) {
    this.orgHAFAccountFlowExcessPaymentDAO = orgHAFAccountFlowExcessPaymentDAO;
  }

  public void setTranOutHeadDAO(TranOutHeadDAO tranOutHeadDAO) {
    this.tranOutHeadDAO = tranOutHeadDAO;
  }

  public void setTranInTailDAO(TranInTailDAO tranInTailDAO) {
    this.tranInTailDAO = tranInTailDAO;
  }

  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setTranInHeadDAO(TranInHeadDAO tranInHeadDAO) {
    this.tranInHeadDAO = tranInHeadDAO;
  }

  public void setDocNumMaintainDAO(DocNumMaintainDAO docNumMaintainDAO) {
    this.docNumMaintainDAO = docNumMaintainDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setOrgExcessPaymentDAO(OrgExcessPaymentDAO orgExcessPaymentDAO) {
    this.orgExcessPaymentDAO = orgExcessPaymentDAO;
  }

  public void setOrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO(
      OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO) {
    this.orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO = orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
  }

  /**
   * 列表显示扎账(默认)
   * 
   * @param pagination
   * @param temp_type
   * @return OrgHAFAccountFlow
   * @throws BusinessException
   */
  public ClearAccountShowAF findOrgHAFAccountFlowDefByPagination(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {

    ClearAccountShowAF ClearAccountShowAF = new ClearAccountShowAF();

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    String date = BusiTools.dateToString(new Date(),
        BusiConst.PUB_LONG_YMD_PATTERN);

    List list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowListByCriterions(
        date, orderBy, order, start, securityInfo, pageSize);

    String bisid = null;
    List list1 = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      OrgHAFAccountFlow orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
      bisid = orgHAFAccountFlow.getId().toString();

      Org org = orgDAO.queryById(new Integer(orgHAFAccountFlow.getOrg().getId()
          + ""));
      orgHAFAccountFlow.setOrg(org);
      orgHAFAccountFlow.setStatus(BusiTools.getBusiValue(Integer
          .parseInt(orgHAFAccountFlow.getBizStatus().toString()),
          BusiConst.BUSINESSSTATE));

      CollBank dto = collBankDAO.getCollBankByCollBankid(org.getOrgInfo()
          .getCollectionBankId());

      orgHAFAccountFlow.getOrg().getOrgInfo().setTemp_collectionBankname(
          dto.getCollBankName());

      if (!orgHAFAccountFlow.getCredit().toString().equals("0")) {
        orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
            BusiConst.OCCURREDDIRECTION_CREDIT, BusiConst.OCCURREDDIRECTION));
      } else if (!orgHAFAccountFlow.getDebit().toString().equals("0")) {
        orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
            BusiConst.OCCURREDDIRECTION_DEBIT, BusiConst.OCCURREDDIRECTION));
      } else {
        orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
            BusiConst.OCCURREDDIRECTION_PARALLEL, BusiConst.OCCURREDDIRECTION));
      }

      list1.add(orgHAFAccountFlow);
    }

    if (list.size() == 0)
      ClearAccountShowAF.setType("2");
    else
      ClearAccountShowAF.setType("1");

    ClearAccountShowAF.setFlowid(bisid);
    ClearAccountShowAF.setList(list1);

    List list2 = orgHAFAccountFlowDAO
        .queryOrgHAFAccountFlowAllListByCriterions(date, orderBy, order, start,
            securityInfo, pageSize);
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    for (int i = 0; i < list2.size(); i++) {
      orgHAFAccountFlow = (OrgHAFAccountFlow) list2.get(i);
      ClearAccountShowAF.setTotalPeople(new Integer(ClearAccountShowAF
          .getTotalPeople().intValue()
          + orgHAFAccountFlow.getPersonTotal().intValue()));

      if (orgHAFAccountFlow.getCredit() != null) {
        ClearAccountShowAF.setCredit(ClearAccountShowAF.getCredit().add(
            orgHAFAccountFlow.getCredit()));
      }
      if (orgHAFAccountFlow.getDebit() != null) {
        ClearAccountShowAF.setDebit(ClearAccountShowAF.getDebit().add(
            orgHAFAccountFlow.getDebit()));
      }
      if (orgHAFAccountFlow.getInterest() != null)
        ClearAccountShowAF.setTotalInterest(ClearAccountShowAF
            .getTotalInterest().add(orgHAFAccountFlow.getInterest()));
    }

    pagination.setNrOfElements(list2.size());

    return ClearAccountShowAF;
  }

  /**
   * 综合查询
   * 
   * @param pagination
   * @param temp_type
   * @return OrgHAFAccountFlow
   * @throws BusinessException
   */
  public ClearAccountShowAF findOrgHAFAccountFlowTotalByPagination(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {

    ClearAccountShowAF ClearAccountShowAF = new ClearAccountShowAF();

    String bank = (String) pagination.getQueryCriterions().get("bank");
    String status = (String) pagination.getQueryCriterions().get("status");
    String type = (String) pagination.getQueryCriterions().get("type");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String operator = (String) pagination.getQueryCriterions().get("operator");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    if (orgId != null && orgId != "") {
      if (orgId.length() == 10) {
        orgId = orgId.substring(1, orgId.length());
      }
    }
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    String temp_button_type = "1";
    String bisid = null;
    try {
      List list = new ArrayList();
      list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowByCriterionsTotal(bank,
          orgId, orgName, operator, type, status, docNum, noteNum, orderBy,
          order, start, pageSize, securityInfo);
      List list1 = new ArrayList();

      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          OrgHAFAccountFlow orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
          bisid = orgHAFAccountFlow.getId().toString();

          CollBank dto = collBankDAO.getCollBankByCollBankid(orgHAFAccountFlow
              .getOrg().getOrgInfo().getCollectionBankId());

          orgHAFAccountFlow.getOrg().getOrgInfo().setTemp_collectionBankname(
              dto.getCollBankName());

          // if(orgHAFAccountFlow.getBizStatus().doubleValue()!=4){
          // temp_button_type="2";//如果记录里有不是复合的状态的就禁用按钮
          // }
          Org org = orgDAO.queryById(new Integer(orgHAFAccountFlow.getOrg()
              .getId()
              + ""));
          orgHAFAccountFlow.setOrg(org);
          orgHAFAccountFlow.setStatus(BusiTools.getBusiValue(Integer
              .parseInt(orgHAFAccountFlow.getBizStatus().toString()),
              BusiConst.BUSINESSSTATE));

          if (!orgHAFAccountFlow.getCredit().toString().equals("0")) {
            orgHAFAccountFlow.setSetdirection(BusiTools
                .getBusiValue(BusiConst.OCCURREDDIRECTION_CREDIT,
                    BusiConst.OCCURREDDIRECTION));
          } else if (!orgHAFAccountFlow.getDebit().toString().equals("0")) {
            orgHAFAccountFlow
                .setSetdirection(BusiTools.getBusiValue(
                    BusiConst.OCCURREDDIRECTION_DEBIT,
                    BusiConst.OCCURREDDIRECTION));
          } else {
            orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
                BusiConst.OCCURREDDIRECTION_PARALLEL,
                BusiConst.OCCURREDDIRECTION));
          }

          list1.add(orgHAFAccountFlow);
        }
      } else {
        temp_button_type = "2";// 如果没有记录的就禁用按钮
      }
      ClearAccountShowAF.setType(temp_button_type);
      ClearAccountShowAF.setDocNum(docNum);
      ClearAccountShowAF.setNoteNum(noteNum);
      ClearAccountShowAF.setOrgId(orgId);
      ClearAccountShowAF.setOrgName(orgName);
      ClearAccountShowAF.setList(list1);
      ClearAccountShowAF.setFlowid(bisid);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("没有查询出记录!");
    }
    List list2 = orgHAFAccountFlowDAO
        .queryOrgHAFAccountFlowAllByCriterionsTotal(bank, orgId, orgName,
            operator, type, status, docNum, noteNum, orderBy, order, start,
            pageSize, securityInfo);
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    for (int i = 0; i < list2.size(); i++) {
      orgHAFAccountFlow = (OrgHAFAccountFlow) list2.get(i);
      ClearAccountShowAF.setTotalPeople(new Integer(ClearAccountShowAF
          .getTotalPeople().intValue()
          + orgHAFAccountFlow.getPersonTotal().intValue()));

      if (orgHAFAccountFlow.getCredit() != null) {
        ClearAccountShowAF.setCredit(ClearAccountShowAF.getCredit().add(
            orgHAFAccountFlow.getCredit()));
      }
      if (orgHAFAccountFlow.getDebit() != null) {
        ClearAccountShowAF.setDebit(ClearAccountShowAF.getDebit().add(
            orgHAFAccountFlow.getDebit()));
      }
      if (orgHAFAccountFlow.getInterest() != null)
        ClearAccountShowAF.setTotalInterest(ClearAccountShowAF
            .getTotalInterest().add(orgHAFAccountFlow.getInterest()));
    }

    pagination.setNrOfElements(list2.size());

    return ClearAccountShowAF;
  }

  /**
   * 根据流水头表id返回头表 列表显示
   * 
   * @param Pagination
   * @param securityInfo
   * @return ClearAccountDetailAF
   * @throws BusinessException
   */
  public ClearAccountDetailAF findOrgHAFAccountFlowByID(Pagination pagination,
      SecurityInfo securityInfo, ILoanDocNumDesignBS loanDocNumDesignBS)
      throws Exception, BusinessException {
    String headid = (String) pagination.getQueryCriterions().get("headid");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(headid + ""));

    String idd = orgHAFAccountFlow.getBizId().toString();
    String traninId = orgHAFAccountFlow.getOrg().getId().toString();
    String traninName = orgHAFAccountFlow.getOrg().getOrgInfo().getName();
    String biz_type = orgHAFAccountFlow.getBizType();
    String type = "0";// 默认为其他形式的
    String settDate = "";
    String empId = "";

    ClearAccountDetailAF clearAccountDetailAF = new ClearAccountDetailAF();
    List list = null;
    list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowTailList(headid, orderBy,
        order, start, pageSize);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {// 可能出现的问题 员工不在该单位下
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(i);
        empId = empHAFAccountFlow.getEmpId().toString();
        Emp emp = empDAO.queryByCriterions(empHAFAccountFlow.getEmpId()
            .toString(), orgHAFAccountFlow.getOrg().getId().toString());
        empHAFAccountFlow.setEmp(emp);
        if (!empHAFAccountFlow.getCredit().toString().equals("0")) {
          empHAFAccountFlow.setMoney(empHAFAccountFlow.getCredit());
        } else if (!empHAFAccountFlow.getDebit().toString().equals("0")) {
          empHAFAccountFlow.setMoney(empHAFAccountFlow.getDebit());
        } else {
          empHAFAccountFlow.setMoney(new BigDecimal(0.00));
        }

      }
    }

    TranInTail tranInTail = null;
    if ("汇缴".equals(biz_type)) {
      type = "5";
      List lists = new ArrayList();
      String monthstr = "";
      String monthend = "";
      lists = monthPaymentHeadDAO.getPayMonthyqf(orgHAFAccountFlow.getBizId()
          .toString());

      for (int i = 0; i < lists.size(); i++) {
        Object[] obj = (Object[]) lists.get(0);
        monthstr = obj[0].toString();
        monthend = obj[1].toString();
      }
      settDate = monthstr + "-" + monthend;

    } else if ("单位补缴".equals(biz_type)) {
      type = "6";
      List lists = new ArrayList();
      String monthstr = "";
      String monthend = "";
      lists = monthPaymentHeadDAO.getPayMonthbyyqf(orgHAFAccountFlow.getBizId()
          .toString());
      for (int i = 0; i < lists.size(); i++) {
        Object[] obj = (Object[]) lists.get(0);
        monthstr = obj[0].toString();
        monthend = obj[1].toString();
      }
      settDate = monthstr + "-" + monthend;
    } else if ("转出".equals(biz_type)) {// 转出
      type = "1";
      // TranOutHead tranOutHead = orgHAFAccountFlowDAO
      // .queryTranInOrgIdByBizId(orgHAFAccountFlow.getOrg().getId() + "");
      //
      // TranInHead tranInHead = tranInHeadDAO
      // .queryTranInOrgIdByOutorgId(tranOutHead.getTranOutOrg().getId()
      // .toString());
      // if (tranInHead != null) {
      // clearAccountDetailAF.setTraninId(tranOutHead.getTranInOrg().getId()
      // + "");
      // clearAccountDetailAF.setTraninName(tranOutHead.getTranInOrg()
      // .getOrgInfo().getName());
      // }
      // clearAccountDetailAF.setTranoutId(tranOutHead.getTranOutOrg().getId()
      // + "");
      // clearAccountDetailAF.setTranoutName(tranOutHead.getTranOutOrg()
      // .getOrgInfo().getName());
      // clearAccountDetailAF.setList(list);

      String id = orgHAFAccountFlow.getBizId().toString();
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(id));

      // TranInHead tranInHead = tranInHeadDAO
      // .queryTranInOrgIdByOutorgId(tranOutHead.getTranOutOrg().getId()
      // .toString());
      // if (tranInHead != null) {
      // bizcheckDetailAF.setTraninId(tranOutHead.getTranInOrg().getId() + "");
      // bizcheckDetailAF.setTraninName(tranOutHead.getTranInOrg().getOrgInfo()
      // .getName());
      // }
      clearAccountDetailAF.setTranoutId(tranOutHead.getTranOutOrg().getId()
          + "");
      clearAccountDetailAF.setTranoutName(tranOutHead.getTranOutOrg()
          .getOrgInfo().getName());
      clearAccountDetailAF.setList(list);

    } else if ("转入".equals(biz_type)) {// 转入
      type = "2";
      clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
          .getOrgInfo().getName());

      String empId_yg = "";
      TranInTail tranInTail_yg = null;
      Iterator iter = list.iterator();
      list = new ArrayList();
      while (iter.hasNext()) {
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) iter.next();
        empId_yg = empHAFAccountFlow.getEmpId() + "";

        List list1 = tranInTailDAO.queryTraninListByCriterionsAll_yqf(empId_yg,
            null, order, start, securityInfo);
        tranInTail_yg = new TranInTail();
        tranInTail_yg = (TranInTail) list1.get(0);
        tranInTail_yg.setEmpId(tranInTail_yg.getEmpId());
        tranInTail_yg.setName(tranInTail_yg.getName());
        tranInTail_yg.setCardNum(tranInTail_yg.getCardNum());
        BigDecimal money = tranInTail_yg.getPreBalance().add(
            tranInTail_yg.getCurBalance());
        tranInTail_yg.setReserveaA(money.toString());
        BigDecimal interest = tranInTail_yg.getPreInterest().add(
            tranInTail_yg.getCurInterest());
        tranInTail_yg.setReserveaB(interest.toString());
        list.add(tranInTail_yg);
      }
      // type = "2";
      // // clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId()
      // +
      // // "");
      // // clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
      // // .getOrgInfo().getName());
      // // clearAccountDetailAF.setList(list);
      // clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() +
      // "");
      // clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
      // .getOrgInfo().getName());
      // // orderBy
      // list = tranInTailDAO.queryTraninListByCriterionsAll_yqf(empId, null,
      // order, start, securityInfo);
      // if (list.size() != 0) {
      // for (int i = 0; i < list.size(); i++) {
      // tranInTail = (TranInTail) list.get(i);
      // BigDecimal money = tranInTail.getPreBalance().add(
      // tranInTail.getCurBalance());
      // tranInTail.setReserveaA(money.toString());
      // BigDecimal interest = tranInTail.getPreInterest().add(
      // tranInTail.getCurInterest());
      // tranInTail.setReserveaB(interest.toString());
      // }
      // }
      // clearAccountDetailAF.setList(list);

    } else if ("提取".equals(biz_type)) {// 提取
      // type = "3";
      // clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() +
      // "");
      // clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
      // .getOrgInfo().getName());
      // clearAccountDetailAF.setList(list);
      type = "3";
      clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
          .getOrgInfo().getName());
      clearAccountDetailAF.setList(list);
    } else if ("挂账".equals(biz_type)) {// 挂账
      OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO
          .queryById(new Integer(idd.toString()));
      Serializable orgId = orgExcessPayment.getOrg().getId().toString();
      BigDecimal banlance = new BigDecimal(0.00);
      banlance = this.queryOrgoverpayBalance(orgId);
      // bizcheckDetailAF.setOrgId(BusiTools.convertSixNumber(orgId.toString()));
      // bizcheckDetailAF.setUnitName(orgExcessPayment.getOrg().getOrgInfo().getName());
      // bizcheckDetailAF.setNoteNum(orgExcessPayment.getNoteNum());
      clearAccountDetailAF.setBanlance(banlance.toString());
      clearAccountDetailAF.setAmount(orgExcessPayment.getPayMoney().toString());
      clearAccountDetailAF.setReason(orgExcessPayment.getExcessReason());
      clearAccountDetailAF.setOtherList(list);

    } else {
      clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
          .getOrgInfo().getName());
      clearAccountDetailAF.setOtherList(list);
    }

    List lists = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowAllTailList(headid,
        orderBy, order, start, pageSize);
    pagination.setNrOfElements(lists.size());
    clearAccountDetailAF.setDocNum(orgHAFAccountFlow.getDocNum());
    clearAccountDetailAF.setNoteNum(orgHAFAccountFlow.getNoteNum());
    clearAccountDetailAF.setType(type);

    clearAccountDetailAF.setTraninId("0" + traninId);
    clearAccountDetailAF.setTraninName(traninName);
    // 判断 用户名设置

    try {
      String name = loanDocNumDesignBS.getNamePara();

      if (name.equals("1")) {
        clearAccountDetailAF.setOperator(orgHAFAccountFlow.getReserveaA());
      } else {
        String realName = "";
        realName = securityDAO.queryByUserid(securityInfo.getUserName());
        clearAccountDetailAF.setOperator(realName);
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    CollBank dto = collBankDAO.getCollBankByCollBankid(orgHAFAccountFlow
        .getMoneyBank());
    clearAccountDetailAF.setBank(dto.getCollBankName());

    clearAccountDetailAF.setSettDate(settDate);
    // biz_type
    clearAccountDetailAF.setBiz_type(biz_type);
    clearAccountDetailAF.setList(list);
    clearAccountDetailAF.setOtherList(lists);
    return clearAccountDetailAF;
  }

  // 查询单位挂账余额
  public BigDecimal queryOrgoverpayBalance(Serializable orgid)
      throws BusinessException {
    // TODO Auto-generated method stub
    BigDecimal orgoverpayBalance = new BigDecimal(0.00);
    if ((orgid != null)) {
      List list = orgHAFAccountFlowExcessPaymentDAO
          .queryOrgoverpayHAFAccountFlow(orgid, new Integer(3));
      for (int i = 0; i < list.size(); i++) {
        OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment = (OrgHAFAccountFlowExcessPayment) list
            .get(i);
        orgoverpayBalance = orgoverpayBalance
            .add(orgHAFAccountFlowExcessPayment.getCredit().subtract(
                orgHAFAccountFlowExcessPayment.getDebit()));
      }
      List lists = orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO
          .orgHAFAccountFlowExcessPaymentBalanceEndTransfer(orgid, new Integer(
              3));
      for (int i = 0; i < lists.size(); i++) {
        OrgHAFAccountFlowExcessPaymentBalanceEndTransfer orgHAFAccountFlowExcessPaymentBalanceEndTransfer = (OrgHAFAccountFlowExcessPaymentBalanceEndTransfer) lists
            .get(i);
        orgoverpayBalance = orgoverpayBalance
            .add(orgHAFAccountFlowExcessPaymentBalanceEndTransfer
                .getCredit()
                .subtract(
                    orgHAFAccountFlowExcessPaymentBalanceEndTransfer.getDebit()));
      }
    }

    return orgoverpayBalance;
  }

  /**
   * 根据流水头表id返回头表 打印显示
   * 
   * @param Pagination
   * @param securityInfo
   * @return ClearAccountDetailAF
   * @throws BusinessException
   */
  public ClearAccountDetailAF findOrgHAFAccountFlowCellByID(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String headid = (String) pagination.getQueryCriterions().get("headid");
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(headid + ""));

    String idd = orgHAFAccountFlow.getBizId().toString();
    String traninId = orgHAFAccountFlow.getOrg().getId().toString();
    String traninName = orgHAFAccountFlow.getOrg().getOrgInfo().getName();
    String biz_type = orgHAFAccountFlow.getBizType();
    String type = "0";// 默认为其他形式的
    String settDate = "";
    String empId = "";

    ClearAccountDetailAF clearAccountDetailAF = new ClearAccountDetailAF();
    List list = null;
    list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowTailList_WL(headid);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {// 可能出现的问题 员工不在该单位下
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(i);
        empId = empHAFAccountFlow.getEmpId().toString();
        Emp emp = empDAO.queryByCriterions(empHAFAccountFlow.getEmpId()
            .toString(), orgHAFAccountFlow.getOrg().getId().toString());
        empHAFAccountFlow.setEmp(emp);
      }
    }

    TranInTail tranInTail = null;
    if ("汇缴".equals(biz_type)) {
      type = "5";
      List lists = new ArrayList();
      String monthstr = "";
      String monthend = "";
      lists = monthPaymentHeadDAO.getPayMonthyqf(orgHAFAccountFlow.getBizId()
          .toString());

      for (int i = 0; i < lists.size(); i++) {
        Object[] obj = (Object[]) lists.get(0);
        monthstr = obj[0].toString();
        monthend = obj[1].toString();
      }
      settDate = monthstr + "-" + monthend;

    } else if ("单位补缴".equals(biz_type)) {
      type = "6";
      List lists = new ArrayList();
      String monthstr = "";
      String monthend = "";
      lists = monthPaymentHeadDAO.getPayMonthbyyqf(orgHAFAccountFlow.getBizId()
          .toString());
      for (int i = 0; i < lists.size(); i++) {
        Object[] obj = (Object[]) lists.get(0);
        monthstr = obj[0].toString();
        monthend = obj[1].toString();
      }
      settDate = monthstr + "-" + monthend;
    } else if ("转出".equals(biz_type)) {// 转出
      type = "1";
      String id = orgHAFAccountFlow.getBizId().toString();
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(id));

      clearAccountDetailAF.setTranoutId(tranOutHead.getTranOutOrg().getId()
          + "");
      clearAccountDetailAF.setTranoutName(tranOutHead.getTranOutOrg()
          .getOrgInfo().getName());
      clearAccountDetailAF.setList(list);

    } else if ("转入".equals(biz_type)) {// 转入
      type = "2";
      clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
          .getOrgInfo().getName());

      list = tranInTailDAO.queryTraninListByCriterionsAll_WL(empId,
          securityInfo);
      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          tranInTail = (TranInTail) list.get(i);
          BigDecimal money = tranInTail.getPreBalance().add(
              tranInTail.getCurBalance());
          tranInTail.setReserveaA(money.toString());
          BigDecimal interest = tranInTail.getPreInterest().add(
              tranInTail.getCurInterest());
          tranInTail.setReserveaB(interest.toString());
        }
      }
      clearAccountDetailAF.setList(list);

    } else if ("提取".equals(biz_type)) {// 提取
      type = "3";
      clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
          .getOrgInfo().getName());
      clearAccountDetailAF.setList(list);
    } else if ("挂账".equals(biz_type)) {// 挂账
      type = "4";// 临时的 ,不定和数据库相符
      OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO
          .queryById(new Integer(idd.toString()));
      Serializable orgId = orgExcessPayment.getOrg().getId().toString();
      BigDecimal banlance = new BigDecimal(0.00);
      banlance = this.queryOrgoverpayBalance(orgId);
      clearAccountDetailAF.setBanlance(banlance.toString());
      clearAccountDetailAF.setAmount(orgExcessPayment.getPayMoney().toString());
      clearAccountDetailAF.setReason(orgExcessPayment.getExcessReason());
      clearAccountDetailAF.setOtherList(list);

    } else {
      clearAccountDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      clearAccountDetailAF.setTraninName(orgHAFAccountFlow.getOrg()
          .getOrgInfo().getName());
      clearAccountDetailAF.setOtherList(list);
    }

    List lists = orgHAFAccountFlowDAO
        .queryOrgHAFAccountFlowAllTailList_WL(headid);
    pagination.setNrOfElements(lists.size());
    clearAccountDetailAF.setDocNum(orgHAFAccountFlow.getDocNum());
    if (orgHAFAccountFlow.getNoteNum() == null) {
      clearAccountDetailAF.setNoteNum("");
    } else {
      clearAccountDetailAF.setNoteNum(orgHAFAccountFlow.getNoteNum());
    }

    clearAccountDetailAF.setType(type);

    clearAccountDetailAF.setTraninId(traninId);
    clearAccountDetailAF.setTraninName(traninName);

    clearAccountDetailAF.setOperator(orgHAFAccountFlow.getReserveaA());

    CollBank dto = collBankDAO.getCollBankByCollBankid(orgHAFAccountFlow
        .getOrg().getOrgInfo().getCollectionBankId());
    clearAccountDetailAF.setBank(dto.getCollBankName());

    clearAccountDetailAF.setSettDate(settDate);
    // biz_type
    clearAccountDetailAF.setBiz_type(biz_type);
    clearAccountDetailAF.setList(list);
    clearAccountDetailAF.setOtherList(lists);
    return clearAccountDetailAF;
  }

  /**
   * 扎账
   * 
   * @param rowArray
   * @param busiDate
   * @param oper
   * @param ip
   * @param officeid
   * @return String
   * @throws BusinessException
   */
  public String dealwithClearAccount(String[] rowArray, String busiDate,
      String oper, String ip, String officeid, Pagination pagination,
      String flag, SecurityInfo securityInfo) throws BusinessException,
      HibernateException, SQLException {
    String[] clearrowArray = new String[rowArray.length];
    int arrid = 0;
    String years = busiDate.substring(0, 4);
    String yearaccount = years + "0630";// 结息日期
    boolean voild = false;
    if (Integer.parseInt(yearaccount) < Integer.parseInt(busiDate)) {
      yearaccount = (Integer.parseInt(years) + 1) + "0630";// 为下一个结息年度
    }
    String bisDate = busiDate.substring(0, 6);
    String temp = "";
    String dates = BusiTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");

    try {
      String returnofficeid = "";

      // 用办事处进行分组扎账
      List arrlist1 = orgHAFAccountFlowDAO.getrowArrayByOffice_WL(rowArray,
          oper);
      ClearAccountDTO clearAccountDTO1 = null;
      returnofficeid = "";
      for (int w = 0; w < arrlist1.size(); w++) {
        clearAccountDTO1 = (ClearAccountDTO) arrlist1.get(w);
        if (returnofficeid.equals("") && w != arrlist1.size() - 1) {
          returnofficeid = clearAccountDTO1.getOffice();
          clearrowArray[arrid] = clearAccountDTO1.getFlowid();
          arrid++;
        } else if (returnofficeid.equals(clearAccountDTO1.getOffice())
            && w != arrlist1.size() - 1) {
          clearrowArray[arrid] = clearAccountDTO1.getFlowid();
          arrid++;
        } else if ((returnofficeid.equals("") && w == arrlist1.size() - 1)
            || (returnofficeid.equals(clearAccountDTO1.getOffice()) && w == arrlist1
                .size() - 1)
            || (!returnofficeid.equals(clearAccountDTO1.getOffice()))) {
          if ((returnofficeid.equals("") && w == arrlist1.size() - 1)
              || (returnofficeid.equals(clearAccountDTO1.getOffice()) && w == arrlist1
                  .size() - 1)) {
            returnofficeid = clearAccountDTO1.getOffice();
            clearrowArray[arrid] = clearAccountDTO1.getFlowid();
            arrid++;
          }
          String[] temp_arr = new String[arrid];
          for (int q = 0; q < arrid; q++) {
            temp_arr[q] = clearrowArray[q];
          }

          List docNumCancellist = (List) docNumCancelDAO.querybydocnum(
              returnofficeid, bisDate);

          if (docNumCancellist.size() == 0) {// 在凭证号撤销维护 里不存在错账凭证号
            String flowid = "";

            int[] a = new int[temp_arr.length];
            for (int i = 0; i < temp_arr.length; i++) {
              a[i] = Integer.parseInt(temp_arr[i]);
            }
            Arrays.sort(a);

            for (int i = a.length - 1; i >= 0; i--)
              flowid += a[i] + ",";
            flowid.substring(0, flowid.lastIndexOf(","));
            if (flag.equals("1")) {// 扎账
              voild = orgHAFAccountFlowDAO.dealwithClearAccount(flowid,
                  yearaccount, years, dates, oper, ip);
            } else {// 全部扎账
              String bank = (String) pagination.getQueryCriterions()
                  .get("bank");
              String status = (String) pagination.getQueryCriterions().get(
                  "status");
              String type = (String) pagination.getQueryCriterions()
                  .get("type");
              String docNum = (String) pagination.getQueryCriterions().get(
                  "docNum");
              String operator = (String) pagination.getQueryCriterions().get(
                  "operator");
              String orgId = (String) pagination.getQueryCriterions().get(
                  "orgId");
              String orgName = (String) pagination.getQueryCriterions().get(
                  "orgName");
              List flowidlist = orgHAFAccountFlowDAO
                  .queryOrgHAFAccountFlowByCriterionsTotal_WLSQL(bank, orgId,
                      orgName, operator, type, status, docNum, securityInfo,
                      returnofficeid);

              List resList = new ArrayList();
              for (int i = 0; i < flowidlist.size(); i++) {
                OrgHAFAccountFlow orgHAFAccountFlow = this
                    .queryIsClearAccountById(flowidlist.get(i).toString());
                String temp_type = "";
                // 判断是否存在自动挂账业务
                if (orgHAFAccountFlow.getReserveaC() != null
                    && !orgHAFAccountFlow.getReserveaC().equals("")) {
                  if (orgHAFAccountFlow.getBiz_Type().equals("A")) {
                    temp_type = "C";
                  } else if (orgHAFAccountFlow.getBiz_Type().equals("C")) {
                    temp_type = "A";
                  }
                  OrgHAFAccountFlow temp_orgHAFAccountFlow = this
                      .queryIsClearAccountByBizId(orgHAFAccountFlow
                          .getReserveaC(), temp_type);
                  boolean temp_flag = true;
                  // 判断在所选列表中是否存在对应的流水id
                  for (int j = 0; j < flowidlist.size(); j++) {
                    if ((flowidlist.get(j).toString())
                        .equals(temp_orgHAFAccountFlow.getId().toString())) {
                      temp_flag = false;
                      break;
                    }
                  }
                  if (temp_flag) {
                    resList.add(temp_orgHAFAccountFlow.getId().toString());
                  }
                }
                resList.add(flowidlist.get(i).toString());
              }

              voild = orgHAFAccountFlowDAO.dealwithClearAllAccount(resList,
                  yearaccount, years, dates, oper, ip);
            }
            if (voild == false)
              temp = "2";
          } else {
            // 会计年
            // DocNumCancel docNumCancel = (DocNumCancel)
            // docNumCancellist.get(0);
            // String doc = docNumCancel.getDocNum();
            // DocNumMaintain docNumMaintain = (DocNumMaintain)
            // docNumMaintainDAO
            // .querybydocnum(returnofficeid, bisDate);
            // 如果凭证号相减取得的是空的话就跳回到页面
            // if (docNumMaintain == null) {
            // // temp = "1";
            // } else {
            // int temp_value = Integer.parseInt(docNumMaintain.getDocNum());
            // temp_value = temp_value - docNumCancellist.size();
            // 如果321里的最小凭证号大于凭证号相减返回的值的时候 开始扎账
            // if (Integer.parseInt(doc) > temp_value) {

            String flowid = "";

            int[] a = new int[temp_arr.length];
            for (int i = 0; i < temp_arr.length; i++) {
              a[i] = Integer.parseInt(temp_arr[i]);
            }
            Arrays.sort(a);

            for (int i = a.length - 1; i >= 0; i--)
              flowid += a[i] + ",";
            flowid.substring(0, flowid.lastIndexOf(","));

            if (flag.equals("1")) {// 扎账
              voild = orgHAFAccountFlowDAO.dealwithClearAccount(flowid,
                  yearaccount, years, dates, oper, ip);
            } else {// 全部扎账
              String bank = (String) pagination.getQueryCriterions()
                  .get("bank");
              String status = (String) pagination.getQueryCriterions().get(
                  "status");
              String type = (String) pagination.getQueryCriterions()
                  .get("type");
              String docNum = (String) pagination.getQueryCriterions().get(
                  "docNum");
              String operator = (String) pagination.getQueryCriterions().get(
                  "operator");
              String orgId = (String) pagination.getQueryCriterions().get(
                  "orgId");
              String orgName = (String) pagination.getQueryCriterions().get(
                  "orgName");
              List flowidlist = orgHAFAccountFlowDAO
                  .queryOrgHAFAccountFlowByCriterionsTotal_WLSQL(bank, orgId,
                      orgName, operator, type, status, docNum, securityInfo,
                      returnofficeid);

              List resList = new ArrayList();
              for (int i = 0; i < flowidlist.size(); i++) {
                OrgHAFAccountFlow orgHAFAccountFlow = this
                    .queryIsClearAccountById(flowidlist.get(i).toString());
                String temp_type = "";
                // 判断是否存在自动挂账业务
                if (orgHAFAccountFlow.getReserveaC() != null
                    && !orgHAFAccountFlow.getReserveaC().equals("")) {
                  if (orgHAFAccountFlow.getBiz_Type().equals("A")) {
                    temp_type = "C";
                  } else if (orgHAFAccountFlow.getBiz_Type().equals("C")) {
                    temp_type = "A";
                  }
                  OrgHAFAccountFlow temp_orgHAFAccountFlow = this
                      .queryIsClearAccountByBizId(orgHAFAccountFlow
                          .getReserveaC(), temp_type);
                  boolean temp_flag = true;
                  // 判断在所选列表中是否存在对应的流水id
                  for (int j = 0; j < flowidlist.size(); j++) {
                    if ((flowidlist.get(j).toString())
                        .equals(temp_orgHAFAccountFlow.getId().toString())) {
                      temp_flag = false;
                      break;
                    }
                  }
                  if (temp_flag) {
                    resList.add(temp_orgHAFAccountFlow.getId().toString());
                  }
                }
                resList.add(flowidlist.get(i).toString());
              }

              voild = orgHAFAccountFlowDAO.dealwithClearAllAccount(flowidlist,
                  yearaccount, years, dates, oper, ip);
            }
            if (voild == false)
              temp = "2";
          }
          if (!temp.equals("2")) {
            return temp;
          }
          if (!returnofficeid.equals(clearAccountDTO1.getOffice())) {
            w--;
          }
          clearrowArray = new String[rowArray.length];
          arrid = 0;
          returnofficeid = "";
        }
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return temp;
  }

  /**
   * 结算单查询
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public ClearAccountBalanceForm findClearAccountBalance(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    Serializable orgid = (Serializable) pagination.getQueryCriterions().get(
        "orgId");
    String orgname = (String) pagination.getQueryCriterions().get("orgName");
    String collBankId = (String) pagination.getQueryCriterions().get("bank1");
    String operator = (String) pagination.getQueryCriterions().get("operator");
    String startday = (String) pagination.getQueryCriterions().get("startday");
    String untilday = (String) pagination.getQueryCriterions().get("untilday");
    String bis_type1 = (String) pagination.getQueryCriterions()
        .get("bis_type1");
    ClearAccountBalanceForm clearAccountBalanceDTO = new ClearAccountBalanceForm();
    List monthpaylist = new ArrayList();
    List orgaddpaylist = new ArrayList();
    List empaddpaylist = new ArrayList();
    List orgexcesspaylist = new ArrayList();
    List orgtraninlist = new ArrayList();
    List adjustaccountotherlist = new ArrayList();
    List adjustaccountpaylist = new ArrayList();
    List adjustaccountpicklist = new ArrayList();
    List endaccruallist = new ArrayList();
    List firstlist = new ArrayList();
    List endlist = new ArrayList();
    List orgtranoutlist = new ArrayList();
    List picklist = new ArrayList();
    List pickCreditlist1 = new ArrayList();
    List pickCreditlist2 = new ArrayList();
    List pickCreditlist3 = new ArrayList();
    // List pickOtherlist = new ArrayList();
    List adjustaccountotherdebitlist = new ArrayList();
    List adjustaccountpaydebitlist = new ArrayList();
    List adjustaccountpickdebitlist = new ArrayList();
    List transOutInterestlist = new ArrayList();
    List distroypickInterestlist = new ArrayList();
    List thislist = new ArrayList();
    List accountlist = new ArrayList();
    List orgexcesspaylistSum = new ArrayList();
    ClearAccountBalanceDTO dto[] = new ClearAccountBalanceDTO[24];
    /**
     * 扎账业务类型-调缴存 "K";扎账业务类型-调提取 "L";扎账业务类型-调账 "G";扎账业务类型-提取 "D";扎账业务类型-转出 "E";
     * /**扎账业务类型-汇缴 "A";扎账业务类型-个人补缴 "B";扎账业务类型-单位补缴 "M";扎账业务类型-挂账 "C"; 扎账业务类型-转入
     * "F"; 扎账业务类型-结息 "H";扎账业务类型-公积金余额结转 "I";扎账业务类型-挂账余额结转 "J";
     */
    /**
     * 贷方
     */
    if (bis_type1 == null) {
      // 单位汇缴
      monthpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceMonthPay(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (monthpaylist.size() > 0) {
        dto[0] = (ClearAccountBalanceDTO) monthpaylist.get(0);
        clearAccountBalanceDTO.setOrg_payment_count(dto[0]
            .getOrg_payment_count());
        clearAccountBalanceDTO.setOrg_payment_balance(dto[0]
            .getOrg_payment_balance());
      }
      // 单位补缴
      orgaddpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceOrgAddPay(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (orgaddpaylist.size() > 0) {
        dto[1] = (ClearAccountBalanceDTO) orgaddpaylist.get(0);
        // clearAccountBalanceDTO.setOrg_payment_count(new Integer(dto[1]
        // .getOrg_add_payment_count().intValue()+clearAccountBalanceDTO.getOrg_payment_count().intValue()));
        // clearAccountBalanceDTO.setOrg_payment_balance(dto[1]
        // .getOrg_add_payment_balance().add(clearAccountBalanceDTO.getOrg_payment_balance()));

        clearAccountBalanceDTO.setOrg_add_payment_count(new Integer(dto[1]
            .getOrg_add_payment_count().intValue()));
        clearAccountBalanceDTO.setOrg_add_payment_balance(dto[1]
            .getOrg_add_payment_balance());
      }
      // 个人补缴
      empaddpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceEmpAddPay(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (empaddpaylist.size() > 0) {
        dto[2] = (ClearAccountBalanceDTO) empaddpaylist.get(0);
        clearAccountBalanceDTO.setEmp_add_payment_count(dto[2]
            .getEmp_add_payment_count());
        clearAccountBalanceDTO.setEmp_add_payment_balance(dto[2]
            .getEmp_add_payment_balance());
      }
      // 单位挂账
      orgexcesspaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceExcessPayment(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (orgexcesspaylist.size() > 0) {
        dto[3] = (ClearAccountBalanceDTO) orgexcesspaylist.get(0);
        clearAccountBalanceDTO.setOrg_over_pay_count(dto[3]
            .getOrg_over_pay_count());
        clearAccountBalanceDTO.setOrg_over_paybalance(dto[3]
            .getOrg_over_paybalance());
      }
      // 单位转入
      orgtraninlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceTransIn(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (orgtraninlist.size() > 0) {
        dto[4] = (ClearAccountBalanceDTO) orgtraninlist.get(0);
        clearAccountBalanceDTO
            .setOrg_tranin_count(dto[4].getOrg_tranin_count());
        clearAccountBalanceDTO.setOrg_tranin_paybalance(dto[4]
            .getOrg_tranin_paybalance());
      }
      // 错账调整
      // 调其他
      adjustaccountotherlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountOther(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountotherlist.size() > 0) {
        dto[5] = (ClearAccountBalanceDTO) adjustaccountotherlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutOther_credit_count(dto[5]
            .getAdjustaccoutOther_credit_count());
        clearAccountBalanceDTO.setAdjustaccoutOthert_credit_paybalance(dto[5]
            .getAdjustaccoutOthert_credit_paybalance());
      }
      // 调缴存
      adjustaccountpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPay(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpaylist.size() > 0) {
        dto[6] = (ClearAccountBalanceDTO) adjustaccountpaylist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPayment_credit_count(dto[6]
            .getAdjustaccoutPayment_credit_count());
        clearAccountBalanceDTO.setAdjustaccoutPayment_credit_paybalance(dto[6]
            .getAdjustaccoutPayment_credit_paybalance());
      }
      // 调提取
      adjustaccountpicklist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPick(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpicklist.size() > 0) {
        dto[7] = (ClearAccountBalanceDTO) adjustaccountpicklist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPick_credit_count(dto[7]
            .getAdjustaccoutPick_credit_count());
        clearAccountBalanceDTO.setAdjustaccoutPick_credit_paybalance(dto[7]
            .getAdjustaccoutPick_credit_paybalance());
      }
      // 结息
      endaccruallist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceEndAccrual(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (endaccruallist.size() > 0) {
        dto[8] = (ClearAccountBalanceDTO) endaccruallist.get(0);
        clearAccountBalanceDTO.setClearinteres_count(dto[8]
            .getClearinteres_count());
        clearAccountBalanceDTO.setClearinteres_paybalance(dto[8]
            .getClearinteres_paybalance());
        clearAccountBalanceDTO.setClearinteres_paybalance_1(dto[8]
            .getClearinteres_paybalance_1());
      }
      /**
       * 借方
       */
      // 单位转出
      orgtranoutlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceTransOut(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (orgtranoutlist.size() > 0) {
        dto[11] = (ClearAccountBalanceDTO) orgtranoutlist.get(0);
        clearAccountBalanceDTO.setOrg_tranout_count(dto[11]
            .getOrg_tranout_count());
        clearAccountBalanceDTO.setOrg_tranout_balance(dto[11]
            .getOrg_tranout_balance());
      }
      // 提取
      picklist = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowBalancePick(orgid,
          orgname, collBankId, startday, untilday, operator, securityInfo);
      if (picklist.size() > 0) {
        dto[12] = (ClearAccountBalanceDTO) picklist.get(0);
        clearAccountBalanceDTO.setPick_count(dto[12].getPick_count());
        clearAccountBalanceDTO.setPick_balance(dto[12].getPick_balance());
      }
      // 提取
      pickCreditlist3 = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalancePick_xiaohu(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (pickCreditlist3.size() > 0) {
        dto[23] = (ClearAccountBalanceDTO) pickCreditlist3.get(0);
        clearAccountBalanceDTO.setPick_count_xiaohu(dto[23].getPick_count());
        clearAccountBalanceDTO
            .setPick_balance_xiaohu(dto[23].getPick_balance());
      }
      // 其中：还贷 ld 修改(公积金按月还贷)
      pickCreditlist1 = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalancePickCredit(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo, new Integer(
                  BusiConst.GIVEMONEYBYMON));
      if (pickCreditlist1.size() > 0) {
        dto[13] = (ClearAccountBalanceDTO) pickCreditlist1.get(0);
        clearAccountBalanceDTO.setPick_payload_count(dto[13]
            .getPick_payload_count());
        clearAccountBalanceDTO.setPick_payload_balance(dto[13]
            .getPick_payload_balance());
      }
      // 其中：还贷 ld 修改(公积金一次性还贷款)
      pickCreditlist2 = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalancePickCredit_LD(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo,
              new Integer(BusiConst.GIVEMONEYClEAR));
      if (pickCreditlist2.size() > 0) {
        dto[22] = (ClearAccountBalanceDTO) pickCreditlist2.get(0);
        clearAccountBalanceDTO.setPick_payload_count_ld(dto[22]
            .getPick_payload_count_ld());
        clearAccountBalanceDTO.setPick_payload_balance_ld(dto[22]
            .getPick_payload_balance_ld());
      }
      // 其中：其它 笔数：总笔数-提取原因为还贷的笔数，发生额：总发生额-提取原因等于还贷的发生额
      Integer pick_otherreason_count = null;
      BigDecimal pick_otherreason_balance = new BigDecimal(0.00);
      pick_otherreason_count = new Integer(clearAccountBalanceDTO
          .getPick_count().intValue()
          - clearAccountBalanceDTO.getPick_payload_count().intValue()
          - clearAccountBalanceDTO.getPick_payload_count_ld().intValue());
      pick_otherreason_balance = clearAccountBalanceDTO.getPick_balance()
          .subtract(clearAccountBalanceDTO.getPick_payload_balance()).subtract(
              clearAccountBalanceDTO.getPick_payload_balance_ld());
      clearAccountBalanceDTO.setPick_otherreason_count(pick_otherreason_count);
      clearAccountBalanceDTO
          .setPick_otherreason_balance(pick_otherreason_balance);
      // pickOtherlist =
      // orgHAFAccountFlowDAO.queryOrgHAFAccountFlowBalancePickOther
      // (orgid, orgname, collBankId, startday, untilday,
      // operator,securityInfo);
      // if(pickOtherlist.size()>0){
      // dto[14]=(ClearAccountBalanceDTO)pickOtherlist.get(0);
      // clearAccountBalanceDTO.setPick_sumcount(new
      // Integer(dto[14].getPick_sumcount().intValue()-
      // clearAccountBalanceDTO.getPick_payload_count().intValue()));
      // clearAccountBalanceDTO.setPick_sumbalance(dto[14].getPick_sumbalance().subtract(clearAccountBalanceDTO.getPick_payload_balance()));
      // }
      // 错账调整
      // 调其他
      adjustaccountotherdebitlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountOtherDebit(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountotherdebitlist.size() > 0) {
        dto[15] = (ClearAccountBalanceDTO) adjustaccountotherdebitlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutOther_debit_count(dto[15]
            .getAdjustaccoutOther_debit_count());
        clearAccountBalanceDTO.setAdjustaccoutOther_debit_paybalance(dto[15]
            .getAdjustaccoutOther_debit_paybalance());
      }
      // 调缴存
      adjustaccountpaydebitlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPayDebit(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpaydebitlist.size() > 0) {
        dto[16] = (ClearAccountBalanceDTO) adjustaccountpaydebitlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPayment_debit_count(dto[16]
            .getAdjustaccoutPayment_debit_count());
        clearAccountBalanceDTO.setAdjustaccoutPayment_debit_paybalance(dto[16]
            .getAdjustaccoutPayment_debit_paybalance());
      }
      // 调提取
      adjustaccountpickdebitlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPickDebit(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpickdebitlist.size() > 0) {
        dto[17] = (ClearAccountBalanceDTO) adjustaccountpickdebitlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPick_debit_count(dto[17]
            .getAdjustaccoutPick_debit_count());
        clearAccountBalanceDTO.setAdjustaccoutPick_debit_paybalance(dto[17]
            .getAdjustaccoutPick_debit_paybalance());
      }
      // 转出利息
      transOutInterestlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceTransOutInterest(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (transOutInterestlist.size() > 0) {
        dto[18] = (ClearAccountBalanceDTO) transOutInterestlist.get(0);
        clearAccountBalanceDTO.setTranoutinterest_count(dto[18]
            .getTranoutinterest_count());
        clearAccountBalanceDTO.setTranoutinterest_paybalance(dto[18]
            .getTranoutinterest_paybalance());
      }
      // 销户利息
      distroypickInterestlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceDistroypickInterest(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (distroypickInterestlist.size() > 0) {
        dto[19] = (ClearAccountBalanceDTO) distroypickInterestlist.get(0);
        clearAccountBalanceDTO.setDeleteaccount_interest_count(dto[19]
            .getDeleteaccount_interest_count());
        clearAccountBalanceDTO.setDeleteaccount_interest_paybalance(dto[19]
            .getDeleteaccount_interest_paybalance());
      }
    } else if (bis_type1.equals("A")) {
      // 单位汇缴
      monthpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceMonthPay(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (monthpaylist.size() > 0) {
        dto[0] = (ClearAccountBalanceDTO) monthpaylist.get(0);
        clearAccountBalanceDTO.setOrg_payment_count(dto[0]
            .getOrg_payment_count());
        clearAccountBalanceDTO.setOrg_payment_balance(dto[0]
            .getOrg_payment_balance());
      }
    } else if (bis_type1.equals("B")) {
      // 个人补缴
      empaddpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceEmpAddPay(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (empaddpaylist.size() > 0) {
        dto[2] = (ClearAccountBalanceDTO) empaddpaylist.get(0);
        clearAccountBalanceDTO.setEmp_add_payment_count(dto[2]
            .getEmp_add_payment_count());
        clearAccountBalanceDTO.setEmp_add_payment_balance(dto[2]
            .getEmp_add_payment_balance());
      }

    } else if (bis_type1.equals("C")) {
      // 单位挂账
      orgexcesspaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceExcessPayment(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (orgexcesspaylist.size() > 0) {
        dto[3] = (ClearAccountBalanceDTO) orgexcesspaylist.get(0);
        clearAccountBalanceDTO.setOrg_over_pay_count(dto[3]
            .getOrg_over_pay_count());
        clearAccountBalanceDTO.setOrg_over_paybalance(dto[3]
            .getOrg_over_paybalance());
      }

    } else if (bis_type1.equals("D")) {
      // 提取
      picklist = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowBalancePick(orgid,
          orgname, collBankId, startday, untilday, operator, securityInfo);
      if (picklist.size() > 0) {
        dto[12] = (ClearAccountBalanceDTO) picklist.get(0);
        clearAccountBalanceDTO.setPick_count(dto[12].getPick_count());
        clearAccountBalanceDTO.setPick_balance(dto[12].getPick_balance());
      }
      // 其中：还贷(公积金按月还贷)
      pickCreditlist1 = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalancePickCredit(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo, new Integer(
                  BusiConst.GIVEMONEYBYMON));
      if (pickCreditlist1.size() > 0) {
        dto[13] = (ClearAccountBalanceDTO) pickCreditlist1.get(0);
        clearAccountBalanceDTO.setPick_payload_count(dto[13]
            .getPick_payload_count());
        clearAccountBalanceDTO.setPick_payload_balance(dto[13]
            .getPick_payload_balance());
      }
      // 其中：还贷 ld 修改(公积金一次性还贷款)
      pickCreditlist2 = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalancePickCredit_LD(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo,
              new Integer(BusiConst.GIVEMONEYClEAR));
      if (pickCreditlist2.size() > 0) {
        dto[22] = (ClearAccountBalanceDTO) pickCreditlist2.get(0);
        clearAccountBalanceDTO.setPick_payload_count_ld(dto[22]
            .getPick_payload_count_ld());
        clearAccountBalanceDTO.setPick_payload_balance_ld(dto[22]
            .getPick_payload_balance_ld());
      }
      // 其中：其它 笔数：总笔数-提取原因为还贷的笔数，发生额：总发生额-提取原因等于还贷的发生额
      Integer pick_otherreason_count = null;
      BigDecimal pick_otherreason_balance = new BigDecimal(0.00);
      pick_otherreason_count = new Integer(clearAccountBalanceDTO
          .getPick_count().intValue()
          - clearAccountBalanceDTO.getPick_payload_count().intValue()
          - clearAccountBalanceDTO.getPick_payload_count_ld().intValue());
      pick_otherreason_balance = clearAccountBalanceDTO.getPick_balance()
          .subtract(clearAccountBalanceDTO.getPick_payload_balance()).subtract(
              clearAccountBalanceDTO.getPick_payload_balance_ld());
      clearAccountBalanceDTO.setPick_otherreason_count(pick_otherreason_count);
      clearAccountBalanceDTO
          .setPick_otherreason_balance(pick_otherreason_balance);

    } else if (bis_type1.equals("E")) {
      // 转出
      orgtranoutlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceTransOut(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (orgtranoutlist.size() > 0) {
        dto[11] = (ClearAccountBalanceDTO) orgtranoutlist.get(0);
        clearAccountBalanceDTO.setOrg_tranout_count(dto[11]
            .getOrg_tranout_count());
        clearAccountBalanceDTO.setOrg_tranout_balance(dto[11]
            .getOrg_tranout_balance());
      }

    } else if (bis_type1.equals("F")) {
      // 单位转入
      orgtraninlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceTransIn(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (orgtraninlist.size() > 0) {
        dto[4] = (ClearAccountBalanceDTO) orgtraninlist.get(0);
        clearAccountBalanceDTO
            .setOrg_tranin_count(dto[4].getOrg_tranin_count());
        clearAccountBalanceDTO.setOrg_tranin_paybalance(dto[4]
            .getOrg_tranin_paybalance());
      }

    } else if (bis_type1.equals("G")) {
      // 调其他
      adjustaccountotherlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountOther(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountotherlist.size() > 0) {
        dto[5] = (ClearAccountBalanceDTO) adjustaccountotherlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutOther_credit_count(dto[5]
            .getAdjustaccoutOther_credit_count());
        clearAccountBalanceDTO.setAdjustaccoutOthert_credit_paybalance(dto[5]
            .getAdjustaccoutOthert_credit_paybalance());
      }
      // 调其他
      adjustaccountotherdebitlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountOtherDebit(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountotherdebitlist.size() > 0) {
        dto[15] = (ClearAccountBalanceDTO) adjustaccountotherdebitlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutOther_debit_count(dto[15]
            .getAdjustaccoutOther_debit_count());
        clearAccountBalanceDTO.setAdjustaccoutOther_debit_paybalance(dto[15]
            .getAdjustaccoutOther_debit_paybalance());
      }

    } else if (bis_type1.equals("H")) {
      // 结息
      endaccruallist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceEndAccrual(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (endaccruallist.size() > 0) {
        dto[8] = (ClearAccountBalanceDTO) endaccruallist.get(0);
        clearAccountBalanceDTO.setClearinteres_count(dto[8]
            .getClearinteres_count());
        clearAccountBalanceDTO.setClearinteres_paybalance(dto[8]
            .getClearinteres_paybalance());
        clearAccountBalanceDTO.setClearinteres_paybalance_1(dto[8]
            .getClearinteres_paybalance_1());
      }

    } else if (bis_type1.equals("K")) {
      // 调缴存
      adjustaccountpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPay(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpaylist.size() > 0) {
        dto[6] = (ClearAccountBalanceDTO) adjustaccountpaylist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPayment_credit_count(dto[6]
            .getAdjustaccoutPayment_credit_count());
        clearAccountBalanceDTO.setAdjustaccoutPayment_credit_paybalance(dto[6]
            .getAdjustaccoutPayment_credit_paybalance());
      }
      // 调缴存
      adjustaccountpaydebitlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPayDebit(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpaydebitlist.size() > 0) {
        dto[16] = (ClearAccountBalanceDTO) adjustaccountpaydebitlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPayment_debit_count(dto[16]
            .getAdjustaccoutPayment_debit_count());
        clearAccountBalanceDTO.setAdjustaccoutPayment_debit_paybalance(dto[16]
            .getAdjustaccoutPayment_debit_paybalance());
      }

    } else if (bis_type1.equals("L")) {
      // 调提取
      adjustaccountpicklist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPick(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpicklist.size() > 0) {
        dto[7] = (ClearAccountBalanceDTO) adjustaccountpicklist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPick_credit_count(dto[7]
            .getAdjustaccoutPick_credit_count());
        clearAccountBalanceDTO.setAdjustaccoutPick_credit_paybalance(dto[7]
            .getAdjustaccoutPick_credit_paybalance());
      }
      // 调提取
      adjustaccountpickdebitlist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceAdjustaccountPickDebit(orgid, orgname,
              collBankId, startday, untilday, operator, securityInfo);
      if (adjustaccountpickdebitlist.size() > 0) {
        dto[17] = (ClearAccountBalanceDTO) adjustaccountpickdebitlist.get(0);
        clearAccountBalanceDTO.setAdjustaccoutPick_debit_count(dto[17]
            .getAdjustaccoutPick_debit_count());
        clearAccountBalanceDTO.setAdjustaccoutPick_debit_paybalance(dto[17]
            .getAdjustaccoutPick_debit_paybalance());
      }

    } else if (bis_type1.equals("M")) {
      // 单位补缴
      orgaddpaylist = orgHAFAccountFlowDAO
          .queryOrgHAFAccountFlowBalanceOrgAddPay(orgid, orgname, collBankId,
              startday, untilday, operator, securityInfo);
      if (orgaddpaylist.size() > 0) {
        dto[1] = (ClearAccountBalanceDTO) orgaddpaylist.get(0);
        clearAccountBalanceDTO.setOrg_add_payment_count(dto[1]
            .getOrg_add_payment_count());
        clearAccountBalanceDTO.setOrg_add_payment_balance(dto[1]
            .getOrg_add_payment_balance());
      }
    }

    // 错账调整
    Integer adjustaccout_credit_count = null;
    adjustaccout_credit_count = new Integer(clearAccountBalanceDTO
        .getAdjustaccoutOther_credit_count().intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPayment_credit_count()
            .intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPick_credit_count().intValue());
    clearAccountBalanceDTO
        .setAdjustaccout_credit_count(adjustaccout_credit_count);
    BigDecimal adjustaccout_credit_paybalance = new BigDecimal(0.00);
    adjustaccout_credit_paybalance = clearAccountBalanceDTO
        .getAdjustaccoutOthert_credit_paybalance().add(
            clearAccountBalanceDTO.getAdjustaccoutPayment_credit_paybalance())
        .add(clearAccountBalanceDTO.getAdjustaccoutPick_credit_paybalance());
    clearAccountBalanceDTO
        .setAdjustaccout_credit_paybalance(adjustaccout_credit_paybalance);
    // 贷方合计 贷方笔数和发生额的合计
    Integer credit_count = null;
    credit_count = new Integer(clearAccountBalanceDTO.getOrg_payment_count()
        .intValue()
        + clearAccountBalanceDTO.getOrg_add_payment_count().intValue()
        + clearAccountBalanceDTO.getEmp_add_payment_count().intValue()
        + clearAccountBalanceDTO.getOrg_over_pay_count().intValue()
        + clearAccountBalanceDTO.getOrg_tranin_count().intValue()
        + clearAccountBalanceDTO.getAdjustaccoutOther_credit_count().intValue()
        // + clearAccountBalanceDTO.getClearinteres_count().intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPayment_credit_count()
            .intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPick_credit_count().intValue()
        + clearAccountBalanceDTO.getClearinteres_count().intValue());
    Integer credit_count1 = null;
    credit_count1 = new Integer(clearAccountBalanceDTO.getOrg_payment_count()
        .intValue()
        + clearAccountBalanceDTO.getOrg_add_payment_count().intValue()
        + clearAccountBalanceDTO.getEmp_add_payment_count().intValue()
        + clearAccountBalanceDTO.getOrg_over_pay_count().intValue());
    Integer credit_count2 = null;
    credit_count2 = new Integer(+clearAccountBalanceDTO.getOrg_tranin_count()
        .intValue()
        + clearAccountBalanceDTO.getAdjustaccoutOther_credit_count().intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPayment_credit_count()
            .intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPick_credit_count().intValue());
    BigDecimal credit_paybalance = new BigDecimal(0.00);
    credit_paybalance = clearAccountBalanceDTO.getOrg_payment_balance().add(
        clearAccountBalanceDTO.getOrg_add_payment_balance()).add(
        clearAccountBalanceDTO.getEmp_add_payment_balance()).add(
        clearAccountBalanceDTO.getOrg_over_paybalance()).add(
        clearAccountBalanceDTO.getOrg_tranin_paybalance()).add(
        clearAccountBalanceDTO.getAdjustaccout_credit_paybalance()).add(
        clearAccountBalanceDTO.getClearinteres_paybalance()).add(
        clearAccountBalanceDTO.getClearinteres_paybalance_1());
    clearAccountBalanceDTO.setCredit_count(credit_count);
    clearAccountBalanceDTO.setXiaoji1_credit_count(credit_count1);
    clearAccountBalanceDTO.setXiaoji2_credit_count(credit_count2);
    clearAccountBalanceDTO.setCredit_paybalance(credit_paybalance);
    BigDecimal credit_paybalance1 = new BigDecimal(0.00);
    credit_paybalance1 = clearAccountBalanceDTO.getOrg_payment_balance().add(
        clearAccountBalanceDTO.getOrg_add_payment_balance()).add(
        clearAccountBalanceDTO.getOrg_over_paybalance()).add(
        clearAccountBalanceDTO.getEmp_add_payment_balance());
    BigDecimal credit_paybalance2 = new BigDecimal(0.00);
    credit_paybalance2 = clearAccountBalanceDTO.getOrg_tranin_paybalance().add(
        clearAccountBalanceDTO.getAdjustaccout_credit_paybalance());
    BigDecimal credit_paybalance3 = new BigDecimal(0.00);
    credit_paybalance3 = clearAccountBalanceDTO.getClearinteres_paybalance()
        .add(clearAccountBalanceDTO.getClearinteres_paybalance_1());
    clearAccountBalanceDTO.setCredit_count(credit_count);
    clearAccountBalanceDTO.setXiaoji1_credit_count(credit_count1);
    clearAccountBalanceDTO.setXiaoji2_credit_count(credit_count2);
    clearAccountBalanceDTO.setCredit_paybalance(credit_paybalance);
    clearAccountBalanceDTO.setCredit_count(credit_count);
    clearAccountBalanceDTO.setXiaoji1_credit_count(credit_count1);
    clearAccountBalanceDTO.setXiaoji2_credit_count(credit_count2);
    clearAccountBalanceDTO.setCredit_paybalance(credit_paybalance);
    clearAccountBalanceDTO.setXiaoji1_credit_paybalance(credit_paybalance1);
    clearAccountBalanceDTO.setXiaoji2_credit_paybalance(credit_paybalance2);
    clearAccountBalanceDTO.setXiaoji3_credit_paybalance(credit_paybalance3);
    // 借方合计 借方笔数和发生额的合计（不包括两个利息）debit_count
    Integer debit_count = null;
    debit_count = new Integer(clearAccountBalanceDTO.getOrg_tranout_count()
        .intValue()
        + clearAccountBalanceDTO.getPick_count().intValue()
        + clearAccountBalanceDTO.getPick_payload_count().intValue()
        + clearAccountBalanceDTO.getPick_payload_count_ld().intValue()
        + clearAccountBalanceDTO.getPick_count_xiaohu().intValue()
        + clearAccountBalanceDTO.getPick_sumcount().intValue()
        + clearAccountBalanceDTO.getAdjustaccoutOther_debit_count().intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPayment_debit_count()
            .intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPick_debit_count().intValue());
    clearAccountBalanceDTO.setDebit_count(debit_count);
    BigDecimal debit_paybalance = new BigDecimal(0.00);
    debit_paybalance = clearAccountBalanceDTO.getOrg_tranout_balance().add(
        clearAccountBalanceDTO.getPick_balance()).add(
        clearAccountBalanceDTO.getPick_payload_balance()).add(
        clearAccountBalanceDTO.getPick_payload_balance_ld()).add(
        clearAccountBalanceDTO.getPick_balance_xiaohu()).add(
        clearAccountBalanceDTO.getPick_sumbalance()).add(
        clearAccountBalanceDTO.getAdjustaccoutOther_debit_paybalance()).add(
        clearAccountBalanceDTO.getAdjustaccoutPayment_debit_paybalance()).add(
        clearAccountBalanceDTO.getAdjustaccoutPick_debit_paybalance());
    clearAccountBalanceDTO.setDebit_paybalance(debit_paybalance);
    Integer debit_count_xiaoji = null;
    debit_count_xiaoji = new Integer(clearAccountBalanceDTO.getPick_count()
        .intValue()
        + clearAccountBalanceDTO.getPick_count_xiaohu().intValue()
        + clearAccountBalanceDTO.getPick_payload_count_ld().intValue()
        + clearAccountBalanceDTO.getPick_payload_count().intValue());
    clearAccountBalanceDTO.setDebit_count_xiaoji(debit_count_xiaoji);
    BigDecimal debit_paybalance_xiaoji = new BigDecimal(0.00);
    debit_paybalance_xiaoji = clearAccountBalanceDTO.getPick_balance().add(
        clearAccountBalanceDTO.getPick_payload_balance()).add(
        clearAccountBalanceDTO.getPick_payload_balance_ld()).add(
        clearAccountBalanceDTO.getPick_balance_xiaohu());
    clearAccountBalanceDTO.setDebit_paybalance_xiaoji(debit_paybalance_xiaoji);

    // 错账调整
    Integer adjustaccout_debit_count = null;
    adjustaccout_debit_count = new Integer(clearAccountBalanceDTO
        .getAdjustaccoutOther_debit_count().intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPayment_debit_count()
            .intValue()
        + clearAccountBalanceDTO.getAdjustaccoutPick_debit_count().intValue());
    clearAccountBalanceDTO
        .setAdjustaccout_debit_count(adjustaccout_debit_count);
    BigDecimal adjustaccout_debit_paybalance = new BigDecimal(0.00);
    adjustaccout_debit_paybalance = clearAccountBalanceDTO
        .getAdjustaccoutOther_debit_paybalance().add(
            clearAccountBalanceDTO.getAdjustaccoutPayment_debit_paybalance())
        .add(clearAccountBalanceDTO.getAdjustaccoutPick_debit_paybalance());
    clearAccountBalanceDTO
        .setAdjustaccout_debit_paybalance(adjustaccout_debit_paybalance);
    // 利息合计 转出利息、销户利息的笔数和发生额的合计
    Integer interest_count = null;
    interest_count = new Integer(clearAccountBalanceDTO
        .getTranoutinterest_count().intValue()
        + clearAccountBalanceDTO.getDeleteaccount_interest_count().intValue());
    clearAccountBalanceDTO.setDebit_interest_count(interest_count);
    BigDecimal interest_paybalance = new BigDecimal(0.00);
    interest_paybalance = clearAccountBalanceDTO
        .getTranoutinterest_paybalance().add(
            clearAccountBalanceDTO.getDeleteaccount_interest_paybalance());
    clearAccountBalanceDTO.setDebit_interest_paybalance(interest_paybalance);
    Integer debit_count_xiaoji_1 = null;
    debit_count_xiaoji_1 = new Integer(clearAccountBalanceDTO
        .getAdjustaccout_credit_count().intValue()
        + clearAccountBalanceDTO.getOrg_tranin_count().intValue());
    clearAccountBalanceDTO.setDebit_count_xiaoji_1(debit_count_xiaoji_1);
    BigDecimal debit_paybalance_xiaoji_1 = new BigDecimal(0.00);
    debit_paybalance_xiaoji_1 = clearAccountBalanceDTO
        .getAdjustaccout_credit_paybalance().add(
            clearAccountBalanceDTO.getOrg_tranin_paybalance());
    clearAccountBalanceDTO
        .setDebit_paybalance_xiaoji_1(debit_paybalance_xiaoji_1);
    // 期初余额
    firstlist = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowBalanceFirst(orgid,
        orgname, collBankId, startday, untilday, operator, securityInfo,
        bis_type1);
    if (firstlist.size() > 0) {
      dto[9] = (ClearAccountBalanceDTO) firstlist.get(0);
      clearAccountBalanceDTO.setPre_rest_paybalance(dto[9]
          .getPre_rest_paybalance());
    }
    // 期末余额
    endlist = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowBalanceEnd(orgid,
        orgname, collBankId, startday, untilday, operator, securityInfo,
        bis_type1);
    if (endlist.size() > 0) {
      dto[10] = (ClearAccountBalanceDTO) endlist.get(0);
      clearAccountBalanceDTO.setCur_rest_paybalance(dto[10]
          .getCur_rest_paybalance());
    }
    // 本期发生额
    thislist = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowBalanceThis(orgid,
        orgname, collBankId, startday, untilday, operator, securityInfo,
        bis_type1);
    if (thislist.size() > 0) {
      dto[20] = (ClearAccountBalanceDTO) thislist.get(0);
      clearAccountBalanceDTO.setPre_debit_paybalance(dto[20]
          .getPre_debit_paybalance());
    }
    // 账面余额
    accountlist = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowBalanceAccount(
        orgid, orgname, collBankId, startday, untilday, operator, securityInfo,
        bis_type1);
    if (accountlist.size() > 0) {
      dto[21] = (ClearAccountBalanceDTO) accountlist.get(0);
      clearAccountBalanceDTO.setCur_debit_paybalance(dto[21]
          .getCur_debit_paybalance());
    }
    orgexcesspaylistSum = orgHAFAccountFlowDAO
        .queryOrgHAFAccountFlowBalanceExcessPayment_wsh(orgid, orgname,
            collBankId, startday, untilday, operator, securityInfo);
    if (orgexcesspaylistSum.size() > 0) {
      dto[3] = (ClearAccountBalanceDTO) orgexcesspaylistSum.get(0);

      clearAccountBalanceDTO
          .setOrg_overpay_sum(dto[3].getOrg_over_paybalance());
    }

    if (clearAccountBalanceDTO.getOrg_tranin_paybalance().compareTo(
        new BigDecimal(0)) == 0) {
      clearAccountBalanceDTO.setOrg_tranin_paybalance(new BigDecimal("0.00"));
    }
    clearAccountBalanceDTO.setGjjYuE(clearAccountBalanceDTO
        .getCur_rest_paybalance().subtract(
            clearAccountBalanceDTO.getOrg_overpay_sum()));
    return clearAccountBalanceDTO;
  }

  /**
   * 结算单查询当天(默认查询)
   * 
   * @param securityInfo 权限
   * @return String
   * @throws Exception
   */
  public ClearAccountBalanceDTO findClearAccountBalanceByDefault(
      SecurityInfo securityInfo) throws Exception {
    ClearAccountBalanceDTO clearAccountBalanceDTO = new ClearAccountBalanceDTO();

    List list = orgHAFAccountFlowDAO
        .queryOrgHAFAccountFlowBalanceByCriterions(securityInfo);
    clearAccountBalanceDTO = (ClearAccountBalanceDTO) list.get(0);
    return clearAccountBalanceDTO;
  }

  public void setDocNumCancelDAO(DocNumCancelDAO docNumCancelDAO) {
    this.docNumCancelDAO = docNumCancelDAO;
  }

  /**
   * 默认全部可以扎账的列表
   */
  public List queryOrgHAFAccountFlowDefByPagination(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;

    list = orgHAFAccountFlowDAO
        .queryOrgHAFAccountFlowListByCriterions_WL(securityInfo);

    return list;
  }

  /**
   * 结合条件查询全部可以扎账的列表
   */
  public List queryOrgHAFAccountFlowTotalByPagination(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    List list = null;

    String bank = (String) pagination.getQueryCriterions().get("bank");
    String status = (String) pagination.getQueryCriterions().get("status");
    String type = (String) pagination.getQueryCriterions().get("type");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String operator = (String) pagination.getQueryCriterions().get("operator");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    if (orgId != null && orgId != "") {
      if (orgId.length() == 10) {
        orgId = orgId.substring(1, orgId.length());
      }
    }
    String orgName = (String) pagination.getQueryCriterions().get("orgName");

    list = new ArrayList();
    list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowByCriterionsTotal_WL(
        bank, orgId, orgName, operator, type, status, docNum, securityInfo);

    return list;
  }

  // 查询银行
  public String findCollBank(String collBankid) {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  // 根据AA101的主键ID返回对应业务的业务ID
  public String queryBizIDByFlowID(String flowID) throws Exception {
    // TODO Auto-generated method stub
    String bizID = "";
    bizID = orgHAFAccountFlowDAO.queryBizIDByFlowID_WL(flowID);
    return bizID;
  }

  /**
   * 存在未进行转入确认的转出进行扎账，系统应给出提示
   */
  public String checktraininBytrainout(String rowarray) throws Exception {
    String flag = "0";
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(rowarray));
    if (orgHAFAccountFlow.getBizType().equals("转出")) {
      flag = tranInTailDAO.getTrainoutheadid(orgHAFAccountFlow.getBizId()
          .toString());
    }
    return flag;
  }

  /**
   * 存在未进行转入确认的转出进行扎账,得到转出单位编号
   */
  public String getTraininorgid(String rowarray) throws Exception {
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(rowarray));
    String traininorgid = tranInTailDAO.getTraininorgid(orgHAFAccountFlow
        .getBizId().toString());
    return traininorgid;
  }

  /**
   * 存在未进行转入确认的转出进行扎账,得到转入单位编号
   */
  public String getTrainoutorgid(String rowarray) throws Exception {
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(rowarray));
    String trainoutorgid = tranInTailDAO.getTrainoutorgid(orgHAFAccountFlow
        .getBizId().toString());
    return trainoutorgid;
  }

  public String updateOrgHAFAccountFlow(String rowarray, String flag)
      throws Exception {
    try {
      OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
          .queryById(new Integer(rowarray));
      orgHAFAccountFlow.setIsClearAccount(new BigDecimal(flag));// 把该流水的扎账标识置为1（已扎账）
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 通过主键id查询职工流水表的实体
   */
  public OrgHAFAccountFlow queryIsClearAccountById(String id) throws Exception {
    // TODO Auto-generated method stub
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(id));
    return orgHAFAccountFlow;
  }

  /**
   * 根据业务id与类型查询流水表实体
   * 
   * @param bizId
   * @param type
   * @return
   * @throws Exception
   */
  public OrgHAFAccountFlow queryIsClearAccountByBizId(String bizId, String type)
      throws Exception {
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryByBizId_fuyf(bizId, type);
    return orgHAFAccountFlow;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public String queryBankName(String flowID) throws Exception {
    // TODO Auto-generated method stub
    try {
      CollBank dto = collBankDAO.getCollBankByCollBankid(flowID);
      return dto.getCollBankName();
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public String queryMakerPara() throws Exception {
    String name = "";
    name = collBankDAO.getNamePara();
    return name;

  }

  public String queryUserName(String userName) throws Exception {
    // TODO Auto-generated method stub
    String realName = "";
    realName = securityDAO.queryByUserid(userName);
    return realName;
  }
}
