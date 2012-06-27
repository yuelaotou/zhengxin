package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.bsinterface.IOrgbusinessflowBS;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.dto.EmpOperationFlowDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.dto.OrgbusinessflowHeadDTO;

public class OrgbusinessflowBS implements IOrgbusinessflowBS {

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private CollBankDAO collBankDAO = null;

  private SecurityDAO securityDAO = null;

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  /**
   * 根据条件查询单位业务流水列表
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List findOrgFlowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      String officecode = (String) pagination.getQueryCriterions().get(
          "officecode");
      String bankcode = (String) pagination.getQueryCriterions()
          .get("bankcode");
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      // 结算查询中点击详细信息时传入的银行
      if (bankId != null && !"".equals(bankId))
        bankcode = bankId;
      String orgcode = (String) pagination.getQueryCriterions().get("orgid");
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String notenum = (String) pagination.getQueryCriterions().get("notenum");
      String docnum = (String) pagination.getQueryCriterions().get("docnum");
      String bsstatus = (String) pagination.getQueryCriterions()
          .get("bsstatus");
      String bstype = (String) pagination.getQueryCriterions().get("bstype");
      String setmonthstart = (String) pagination.getQueryCriterions().get(
          "setmonthstart");
      String setmonthend = (String) pagination.getQueryCriterions().get(
          "setmonthend");
      String setmoneystart = (String) pagination.getQueryCriterions().get(
          "setmoneystart");
      String setmoneyend = (String) pagination.getQueryCriterions().get(
          "setmoneyend");
      String setpeopcountstart = (String) pagination.getQueryCriterions().get(
          "setpeopcountstart");
      String setpeopcountend = (String) pagination.getQueryCriterions().get(
          "setpeopcountend");
      String setdirection = (String) pagination.getQueryCriterions().get(
          "setdirection");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = orgHAFAccountFlowDAO.findOrgFlowListByCriterions_WL(officecode,
          bankcode, orgcode, orgname, notenum, docnum, bsstatus, bstype,
          setmonthstart, setmonthend, setmoneystart, setmoneyend,
          setpeopcountstart, setpeopcountend, setdirection, orderBy, order,
          start, pageSize, page, securityInfo);
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      for (int i = 0; i < list.size(); i++) {
        orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
        // 转换
        if (orgHAFAccountFlow.getBizType().equals("挂账")
            && Float.parseFloat(orgHAFAccountFlow.getDebit().toString()) > 0) {
          orgHAFAccountFlow.setMoneyTotal(orgHAFAccountFlow.getMoneyTotal()
              .multiply(new BigDecimal(-1)));
        }
        if (orgHAFAccountFlow.getInterest() == null) {
          orgHAFAccountFlow.setInterest(new BigDecimal(0.00));
        }
        if (!orgHAFAccountFlow.getCredit().toString().equals("0")) {
          orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
              BusiConst.OCCURREDDIRECTION_CREDIT, BusiConst.OCCURREDDIRECTION));
        } else if (!orgHAFAccountFlow.getDebit().toString().equals("0")) {
          orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
              BusiConst.OCCURREDDIRECTION_DEBIT, BusiConst.OCCURREDDIRECTION));
        } else {
          orgHAFAccountFlow.setSetdirection(BusiTools
              .getBusiValue(BusiConst.OCCURREDDIRECTION_PARALLEL,
                  BusiConst.OCCURREDDIRECTION));
        }
        orgHAFAccountFlow.setBis_type(BusiTools
            .getBusiValue_WL(orgHAFAccountFlow.getBiz_Type(),
                BusiConst.CLEARACCOUNTBUSINESSTYPE));
        orgHAFAccountFlow.setStatus(BusiTools.getBusiValue(Integer
            .parseInt(orgHAFAccountFlow.getBizStatus().toString()),
            BusiConst.BUSINESSSTATE));
        if (orgHAFAccountFlow.getCheckPerson() != null)
          orgHAFAccountFlow.setCheckPersonStr(securityDAO
              .queryByUserid(orgHAFAccountFlow.getCheckPerson()));
        if (orgHAFAccountFlow.getClearPerson() != null)
          orgHAFAccountFlow.setClearPersonStr(securityDAO
              .queryByUserid(orgHAFAccountFlow.getClearPerson()));
        if (orgHAFAccountFlow.getReserveaA() != null)
          orgHAFAccountFlow.setReserveaA_(securityDAO
              .queryByUserid(orgHAFAccountFlow.getReserveaA()));
      }

    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据条件查询单位业务流水全部列表
   */
  public List findOrgFlowAllListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    int count = 0;
    try {

      String officecode = (String) pagination.getQueryCriterions().get(
          "officecode");
      String bankcode = (String) pagination.getQueryCriterions()
          .get("bankcode");
      // wangshuang add将银行转换放入pagintaion中打印用
      String bankName = "";
      if (bankcode != null && !"".equals(bankcode)) {
        bankName = collBankDAO.getCollBankByCollBankid(bankcode)
            .getCollBankName();
      } else {
        bankName = "全部";
      }
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      // 结算查询中点击详细信息时传入的银行
      if (bankId != null && !"".equals(bankId))
        bankcode = bankId;
      pagination.getQueryCriterions().put("bankName", bankName);
      String orgcode = (String) pagination.getQueryCriterions().get("orgid");
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String notenum = (String) pagination.getQueryCriterions().get("notenum");
      String docnum = (String) pagination.getQueryCriterions().get("docnum");
      String bsstatus = (String) pagination.getQueryCriterions()
          .get("bsstatus");
      String bstype = (String) pagination.getQueryCriterions().get("bstype");
      String setmonthstart = (String) pagination.getQueryCriterions().get(
          "setmonthstart");
      String setmonthend = (String) pagination.getQueryCriterions().get(
          "setmonthend");
      String setmoneystart = (String) pagination.getQueryCriterions().get(
          "setmoneystart");
      String setmoneyend = (String) pagination.getQueryCriterions().get(
          "setmoneyend");
      String setpeopcountstart = (String) pagination.getQueryCriterions().get(
          "setpeopcountstart");
      String setpeopcountend = (String) pagination.getQueryCriterions().get(
          "setpeopcountend");
      String setdirection = (String) pagination.getQueryCriterions().get(
          "setdirection");

      list = orgHAFAccountFlowDAO.findOrgFlowAllListByCriterions_WL(officecode,
          bankcode, orgcode, orgname, notenum, docnum, bsstatus, bstype,
          setmonthstart, setmonthend, setmoneystart, setmoneyend,
          setpeopcountstart, setpeopcountend, setdirection, securityInfo);
      for (int i = 0; i < list.size(); i++) {
        BigDecimal orgBalance = new BigDecimal(0.00);
        OrgHAFAccountFlow orgHAFAccountFlow = null;
        orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
        for(int j=0;j<list.size();j++){
          OrgHAFAccountFlow orgHAFAccountFlowa = null;
          orgHAFAccountFlowa = (OrgHAFAccountFlow) list.get(j);
          if(Integer.parseInt(orgHAFAccountFlowa.getId().toString())<Integer.parseInt(orgHAFAccountFlow.getId().toString())){
            orgBalance=orgBalance.add(orgHAFAccountFlowa.getOrgBalance());
          }
        }
        orgHAFAccountFlow.setOrgBalancea(orgBalance.add(orgHAFAccountFlow.getOrgBalance()));
        if (orgHAFAccountFlow.getBizType().equals("挂账")
            && Float.parseFloat(orgHAFAccountFlow.getDebit().toString()) > 0) {
          orgHAFAccountFlow.setMoneyTotal(orgHAFAccountFlow.getMoneyTotal()
              .multiply(new BigDecimal(-1)));
        }
        if (orgHAFAccountFlow.getInterest() == null) {
          orgHAFAccountFlow.setInterest(new BigDecimal(0.00));
        }
        if (!orgHAFAccountFlow.getCredit().toString().equals("0")) {
          orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
              BusiConst.OCCURREDDIRECTION_CREDIT, BusiConst.OCCURREDDIRECTION));
        } else if (!orgHAFAccountFlow.getDebit().toString().equals("0")) {
          orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
              BusiConst.OCCURREDDIRECTION_DEBIT, BusiConst.OCCURREDDIRECTION));
        } else {
          orgHAFAccountFlow.setSetdirection(BusiTools
              .getBusiValue(BusiConst.OCCURREDDIRECTION_PARALLEL,
                  BusiConst.OCCURREDDIRECTION));
        }
        orgHAFAccountFlow.setBis_type(BusiTools
            .getBusiValue_WL(orgHAFAccountFlow.getBiz_Type(),
                BusiConst.CLEARACCOUNTBUSINESSTYPE));
        orgHAFAccountFlow.setStatus(BusiTools.getBusiValue(Integer
            .parseInt(orgHAFAccountFlow.getBizStatus().toString()),
            BusiConst.BUSINESSSTATE));
        if (orgHAFAccountFlow.getCheckPerson() != null)
          orgHAFAccountFlow.setCheckPersonStr(securityDAO
              .queryByUserid(orgHAFAccountFlow.getCheckPerson()));
        if (orgHAFAccountFlow.getClearPerson() != null)
          orgHAFAccountFlow.setClearPersonStr(securityDAO
              .queryByUserid(orgHAFAccountFlow.getClearPerson()));
        if (orgHAFAccountFlow.getReserveaA() != null)
          orgHAFAccountFlow.setReserveaA_(securityDAO
              .queryByUserid(orgHAFAccountFlow.getReserveaA()));
      }
      if (list != null)
        count = list.size();
      pagination.setNrOfElements(count);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 显示合计信息
   */
  public OrgbusinessflowHeadDTO findOrgFlowListHead(List list)
      throws Exception, BusinessException {
    OrgbusinessflowHeadDTO dto = new OrgbusinessflowHeadDTO();
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    try {
      for (int i = 0; i < list.size(); i++) {
        orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
        dto.setCount(new Integer(dto.getCount().intValue()
            + orgHAFAccountFlow.getPersonTotal().intValue()));
        // dto.setSumPay(dto.getSumPay().add(orgHAFAccountFlow.getMoneyTotal()));
        if (!orgHAFAccountFlow.getCredit().toString().equals("0")) {
          dto.setDesumPay(dto.getDesumPay().add(orgHAFAccountFlow.getCredit()));
        } else if (!orgHAFAccountFlow.getDebit().toString().equals("0")) {
          dto.setCdsumPay(dto.getCdsumPay().add(orgHAFAccountFlow.getDebit()));
        }
        dto.setSumInterestPay(dto.getSumInterestPay().add(
            orgHAFAccountFlow.getInterest()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  /**
   * 查询职工明细
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findEmpHAFAccountFlowList(Pagination pagination,
      SecurityInfo securityInfo) {
    List list = new ArrayList();
    List returnlist = new ArrayList();
    int count = 0;
    Serializable empid = (Serializable) pagination.getQueryCriterions().get(
        "empid");
    String empname = (String) pagination.getQueryCriterions().get("empname");
    String orgbusinessflowHeadID = (String) pagination.getQueryCriterions()
        .get("orgbusinessflowHeadID");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    try {
      list = orgHAFAccountFlowDAO.queryEmpHAFAccountFlowList_jj(empid, empname,
          orderBy, order, start, pageSize, page, securityInfo,
          orgbusinessflowHeadID);

      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(i);
          EmpOperationFlowDTO dto = new EmpOperationFlowDTO();

          Object obj = new Object();
          dto.setDocNum(empHAFAccountFlow.getOrgHAFAccountFlow().getDocNum());
          dto.setEmpid(new Integer(empHAFAccountFlow.getEmpId().toString()));
          dto.setEmpname(empHAFAccountFlow.getEmpName());
          if (dto.getEmpname() == null) {
            obj = orgHAFAccountFlowDAO.queryByCriterionsTranInTail(dto
                .getEmpid().toString(), empHAFAccountFlow
                .getOrgHAFAccountFlow().getBizId().toString());
            dto.setEmpname(obj.toString());
          }
          dto.setId(empHAFAccountFlow.getId().toString());
          dto.setNoteNum(empHAFAccountFlow.getOrgHAFAccountFlow().getNoteNum());
          dto.setOpDate(empHAFAccountFlow.getOrgHAFAccountFlow().getSettDate());
          dto.setOpInterest(empHAFAccountFlow.getInterest().toString());
          dto.setOpMoney(empHAFAccountFlow.getCredit().add(
              empHAFAccountFlow.getDebit()).toString());
          dto.setOpStatus(BusiTools.getBusiValue(Integer
              .parseInt(empHAFAccountFlow.getOrgHAFAccountFlow().getBizStatus()
                  .toString()), BusiConst.BUSINESSSTATE));
          dto.setOpType(empHAFAccountFlow.getOrgHAFAccountFlow().getBizType());
          dto.setOrgid(BusiTools.convertTenNumber(empHAFAccountFlow
              .getOrgHAFAccountFlow().getOrg().getId().toString()));
          dto.setOrgname(empHAFAccountFlow.getOrgHAFAccountFlow().getOrg()
              .getOrgInfo().getName());
          dto.setReserveaA(empHAFAccountFlow.getOrgHAFAccountFlow()
              .getReserveaA());
          dto.setCheckPerson(empHAFAccountFlow.getOrgHAFAccountFlow()
              .getCheckPerson());
          if (empHAFAccountFlow.getOrgHAFAccountFlow().getBizType()
              .equals("提取")) {
            BigDecimal bizid = empHAFAccountFlow.getOrgHAFAccountFlow()
                .getBizId();
            String empidd = empHAFAccountFlow.getEmpId().toString();
            String reason = orgHAFAccountFlowDAO.findReason(bizid.toString(),
                empidd);
            dto.setReason(BusiTools.getBusiValue(Integer.parseInt(reason),
                BusiConst.PICKUPREASON));
          }

          if (empHAFAccountFlow.getCredit().doubleValue() > 0) {
            dto.setOpDirection("贷方");
          } else {
            dto.setOpDirection("借方");
          }
          returnlist.add(dto);
        }
      }
      count = orgHAFAccountFlowDAO.queryEmpHAFAccountFlowCount_jj(empid,
          empname, securityInfo, orgbusinessflowHeadID);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return returnlist;
  }

  /**
   * 查询职工明细列表合计项
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findEmpHAFAccountFlowTotalList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    Serializable empid = (Serializable) pagination.getQueryCriterions().get(
        "empid");
    String empname = (String) pagination.getQueryCriterions().get("empname");
    String orgbusinessflowHeadID = (String) pagination.getQueryCriterions()
        .get("orgbusinessflowHeadID");
    List list = new ArrayList();
    list = orgHAFAccountFlowDAO.queryEmpHAFAccountFlowTotal_jj(empid, empname,
        securityInfo, orgbusinessflowHeadID);
    return list;
  }

  public OrgHAFAccountFlow findOrgHAFAccountFlow(String orgbusinessflowHeadID) {
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(orgbusinessflowHeadID));
    return orgHAFAccountFlow;
  }
}
