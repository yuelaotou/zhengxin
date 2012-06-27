package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.bsinterface.IEmpOperationFlowBS;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowDTO;

public class EmpOperationFlowBS implements IEmpOperationFlowBS{
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;
  
  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }
  
  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public List findEmpHAFAccountFlowList(Pagination pagination,
      SecurityInfo securityInfo) {
    List list = new ArrayList();
    List returnlist = new ArrayList();
    int count = 0;
    Serializable orgid = (Serializable) pagination.getQueryCriterions().get(
        "orgid");
    String orgname = (String) pagination.getQueryCriterions().get("orgname");
    Serializable empid = (Serializable) pagination.getQueryCriterions().get(
        "empid");
    String empname = (String) pagination.getQueryCriterions().get("empname");
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String opStatus = (String) pagination.getQueryCriterions().get("opStatus");
    String opType = (String) pagination.getQueryCriterions().get("opType");
    String opDate = (String) pagination.getQueryCriterions().get("opDate");
    String opMoney = (String) pagination.getQueryCriterions().get("opMoney");
    String inOpDate = (String) pagination.getQueryCriterions().get("inOpDate");
    String inOpMoney = (String) pagination.getQueryCriterions()
        .get("inOpMoney");
    String opDirection = (String) pagination.getQueryCriterions().get(
        "opDirection");
    String orgbusinessflowHeadID = (String) pagination.getQueryCriterions()
        .get("orgbusinessflowHeadID");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String type = (String) pagination.getQueryCriterions().get("type");
    if (type == null) {
      type = "1";
    }
    try {
      if (!type.equals("0")) {
        if (orgbusinessflowHeadID != null && !"".equals(orgbusinessflowHeadID)) {
          OrgHAFAccountFlow head = orgHAFAccountFlowDAO.queryById(new Integer(orgbusinessflowHeadID));
          noteNum = head.getNoteNum();
        }
        list = empHAFAccountFlowDAO.queryEmpHAFAccountFlowListLJ(orgid,
            orgname, empid, empname, noteNum, docNum, opStatus, opType,
            inOpDate, opDate, inOpMoney, opMoney, opDirection, orderBy, order,
            start, pageSize, page, securityInfo, null);
        if (list != null) {
          for (int i = 0; i < list.size(); i++) {
            EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list
                .get(i);
            EmpOperationFlowDTO dto = new EmpOperationFlowDTO();

            Object obj = new Object();
            dto.setDocNum(empHAFAccountFlow.getOrgHAFAccountFlow().getDocNum());
            dto.setEmpid(new Integer(empHAFAccountFlow.getEmpId().toString()));
            dto.setEmpname(empHAFAccountFlow.getEmpName());
            if (dto.getEmpname() == null) {
              obj = empHAFAccountFlowDAO.queryByCriterionsTranInTail(dto
                  .getEmpid().toString(), empHAFAccountFlow
                  .getOrgHAFAccountFlow().getBizId().toString());
              dto.setEmpname(obj.toString());
            }
            dto.setId(empHAFAccountFlow.getId().toString());
            dto.setNoteNum(empHAFAccountFlow.getOrgHAFAccountFlow()
                .getNoteNum());
            dto.setOpDate(empHAFAccountFlow.getOrgHAFAccountFlow()
                .getSettDate());
            dto.setOpInterest(empHAFAccountFlow.getInterest().toString());
            dto.setOpMoney(empHAFAccountFlow.getCredit().add(
                empHAFAccountFlow.getDebit()).toString());
            dto.setOpStatus(BusiTools.getBusiValue(Integer
                .parseInt(empHAFAccountFlow.getOrgHAFAccountFlow()
                    .getBizStatus().toString()), BusiConst.BUSINESSSTATE));
            dto
                .setOpType(empHAFAccountFlow.getOrgHAFAccountFlow()
                    .getBizType());
            dto.setOrgid(BusiTools.convertTenNumber(empHAFAccountFlow
                .getOrgHAFAccountFlow().getOrg().getId().toString()));
            dto.setOrgname(empHAFAccountFlow.getOrgHAFAccountFlow().getOrg()
                .getOrgInfo().getName());
            dto.setReserveaA(empHAFAccountFlow.getOrgHAFAccountFlow()
                .getReserveaA());
            dto.setCheckPerson(empHAFAccountFlow.getOrgHAFAccountFlow()
                .getCheckPerson());
            System.out.println(empHAFAccountFlow.getOrgHAFAccountFlow()
                .getBizType());
            if (empHAFAccountFlow.getOrgHAFAccountFlow().getBizType().equals(
                "提取")) {
              BigDecimal bizid = empHAFAccountFlow.getOrgHAFAccountFlow()
                  .getBizId();
              String empidd = empHAFAccountFlow.getEmpId().toString();
              System.out.println(bizid + "=====>" + empidd);
              String reason = empHAFAccountFlowDAO.findReason(bizid.toString(),
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
        count = empHAFAccountFlowDAO.queryEmpHAFAccountFlowCountLJ(orgid,
            orgname, empid, empname, noteNum, docNum, opStatus, opType,
            inOpDate, opDate, inOpMoney, opMoney, opDirection, securityInfo,
            null);
      }
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return returnlist;
  }
  public List findEmpHAFAccountFlowTotalList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Serializable orgid = (Serializable)pagination.getQueryCriterions().get("orgid");
    String orgname = (String)pagination.getQueryCriterions().get("orgname");
    Serializable empid = (Serializable)pagination.getQueryCriterions().get("empid");
    String empname = (String)pagination.getQueryCriterions().get("empname");
    String noteNum = (String)pagination.getQueryCriterions().get("noteNum");
    String docNum =(String)pagination.getQueryCriterions().get("docNum");
    String opStatus =(String)pagination.getQueryCriterions().get("opStatus");
    String opType = (String)pagination.getQueryCriterions().get("opType");
    String opDate = (String)pagination.getQueryCriterions().get("opDate");
    String opMoney = (String)pagination.getQueryCriterions().get("opMoney");
    String inOpDate = (String)pagination.getQueryCriterions().get("inOpDate");
    String inOpMoney = (String)pagination.getQueryCriterions().get("inOpMoney");
    String opDirection = (String)pagination.getQueryCriterions().get("opDirection");
    String orgbusinessflowHeadID=(String)pagination.getQueryCriterions().get("orgbusinessflowHeadID");
    String type=(String)pagination.getQueryCriterions().get("type");
    if(type==null){
      type="1";
    }
    List list= new ArrayList();
    if (!type.equals("0")) {
      if (orgbusinessflowHeadID != null && !"".equals(orgbusinessflowHeadID)) {
        OrgHAFAccountFlow head = orgHAFAccountFlowDAO.
            queryById(Integer.valueOf(orgbusinessflowHeadID));
        noteNum = head.getNoteNum();
      }
      list = empHAFAccountFlowDAO.queryEmpHAFAccountFlowTotalLJ(orgid, orgname,
          empid, empname, noteNum, docNum, opStatus, opType, inOpDate, opDate,
          inOpMoney, opMoney, opDirection, securityInfo, null);
    }
    return list;
  }
  public List findEmpHAFAccountFlowPrintList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Serializable orgid = (Serializable)pagination.getQueryCriterions().get("orgid");
    String orgname = (String)pagination.getQueryCriterions().get("orgname");
    Serializable empid = (Serializable)pagination.getQueryCriterions().get("empid");
    String empname = (String)pagination.getQueryCriterions().get("empname");
    String noteNum = (String)pagination.getQueryCriterions().get("noteNum");
    String docNum =(String)pagination.getQueryCriterions().get("docNum");
    String opStatus =(String)pagination.getQueryCriterions().get("opStatus");
    String opType = (String)pagination.getQueryCriterions().get("opType");
    String opDate = (String)pagination.getQueryCriterions().get("opDate");
    String opMoney = (String)pagination.getQueryCriterions().get("opMoney");
    String inOpDate = (String)pagination.getQueryCriterions().get("inOpDate");
    String inOpMoney = (String)pagination.getQueryCriterions().get("inOpMoney");
    String opDirection = (String)pagination.getQueryCriterions().get("opDirection");
    String orgbusinessflowHeadID=(String)pagination.getQueryCriterions().get("orgbusinessflowHeadID");
    String orderBy = (String)pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    String type=(String)pagination.getQueryCriterions().get("type");
    if(type==null){
      type="1";
    }
    List list= new ArrayList();
    List returnlist = new ArrayList();
    if (!type.equals("0")) {
      if (orgbusinessflowHeadID != null && !"".equals(orgbusinessflowHeadID)) {
        OrgHAFAccountFlow head = orgHAFAccountFlowDAO.
            queryById(Integer.valueOf(orgbusinessflowHeadID));
        noteNum = head.getNoteNum();
      }
      list = empHAFAccountFlowDAO.queryEmpHAFAccountFlowListPrint(orgid,
          orgname, empid, empname, noteNum, docNum, opStatus, opType, inOpDate,
          opDate, inOpMoney, opMoney, opDirection, orderBy, order,
          securityInfo, null);
      for (int i = 0; i < list.size(); i++) {
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(i);
        EmpOperationFlowDTO dto = new EmpOperationFlowDTO();
        dto.setDocNum(empHAFAccountFlow.getOrgHAFAccountFlow().getDocNum());
        dto.setEmpid(new Integer(empHAFAccountFlow.getEmpId().toString()));
        dto.setEmpname(empHAFAccountFlow.getEmpName());
        Object obj = new Object();
        if (dto.getEmpname() == null) {
          obj = empHAFAccountFlowDAO.queryByCriterionsTranInTail(dto.getEmpid()
              .toString(), empHAFAccountFlow.getOrgHAFAccountFlow().getBizId()
              .toString());
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
        dto.setOrgid(empHAFAccountFlow.getOrgHAFAccountFlow().getOrg().getId()
            .toString());
        dto.setOrgname(empHAFAccountFlow.getOrgHAFAccountFlow().getOrg()
            .getOrgInfo().getName());
        dto.setClearPerson(empHAFAccountFlow.getOrgHAFAccountFlow()
            .getClearPerson());
        if (empHAFAccountFlow.getOrgHAFAccountFlow().getReserveaA() != null) {
          dto.setReserveaA(empHAFAccountFlow.getOrgHAFAccountFlow()
              .getReserveaA());
        } else {
          dto.setReserveaA("");
        }
        if (empHAFAccountFlow.getOrgHAFAccountFlow().getCheckPerson() != null) {
          dto.setCheckPerson(empHAFAccountFlow.getOrgHAFAccountFlow()
              .getCheckPerson());
        } else {
          dto.setCheckPerson("");
        }
        if (empHAFAccountFlow.getOrgHAFAccountFlow().getBizType().equals("提取")) {
          BigDecimal bizid = empHAFAccountFlow.getOrgHAFAccountFlow()
              .getBizId();
          String empidd = empHAFAccountFlow.getEmpId().toString();
          String reason = empHAFAccountFlowDAO.findReason(bizid.toString(),
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
    return returnlist;
  }
}