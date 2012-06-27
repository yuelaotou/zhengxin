package org.xpup.hafmis.syscollection.paymng.agent.business;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.AgentDetailDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpAgentDetailDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgAgentDetailDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.AgentDetail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAgentDetail;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAgentDetail;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentChgListDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentImportDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentImportTitleDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentInfoDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentInfoQueryTaDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.PayChgInfoDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.PersonChgInfoDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * Copy Right Information : 财政代扣BS Goldsoft Project : AgentBS
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentBS implements IAgentBS {

  private AgentDetailDAO agentDetailDAO;

  private OrgAgentDetailDAO orgAgentDetailDAO;

  private EmpAgentDetailDAO empAgentDetailDAO;

  protected HafOperateLogDAO hafOperateLogDAO;

  public void setAgentDetailDAO(AgentDetailDAO agentDetailDAO) {
    this.agentDetailDAO = agentDetailDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setEmpAgentDetailDAO(EmpAgentDetailDAO empAgentDetailDAO) {
    this.empAgentDetailDAO = empAgentDetailDAO;
  }

  public void setOrgAgentDetailDAO(OrgAgentDetailDAO orgAgentDetailDAO) {
    this.orgAgentDetailDAO = orgAgentDetailDAO;
  }

  public Object[] saveAgent(List agentImportTitle, List agentImport,
      SecurityInfo securityInfo) throws BusinessException, Exception {

    Object[] obj = new Object[2];

    AgentDetail agentDetail = null;
    OrgAgentDetail orgAgentDetail = null;
    EmpAgentDetail empAgentDetail = null;
    Serializable agentDetailId = null;
    /* 用来进行比较的临时单位代扣号 */
    String temp_orgAgentNum = "";
    /* 用来进行比较的临时单位代扣清册号 */
    String temp_orgAgentDetailId = "";
    Serializable orgAgentDetailId = null;

    AgentImportTitleDTO agentImportTitleDTO = (AgentImportTitleDTO) agentImportTitle
        .get(1);
    // 得到代扣类型
    String agentType = agentImportTitleDTO.getAgentType();
    if (agentType == null || agentType.equals("")) {
      throw new BusinessException("代扣类型不能为空");
    }
    // 得到缴存方式
    String payMode = agentImportTitleDTO.getPayMode();
    if (payMode == null || payMode.equals("")) {
      throw new BusinessException("缴存方式不能为空");
    }
    // 得到代扣年月
    String agentYearMonth = agentImportTitleDTO.getAgentYearMonth();
    if (agentYearMonth == null || agentYearMonth.equals("")) {
      throw new BusinessException("代扣年月不能为空");
    }

    if (agentType.equals("2") && payMode.equals("1")) {
      throw new BusinessException("欠缴代扣不能进行均缴");
    }

    // 如果代扣类型为2则，根据单位和年月查询AA301，AA302，AA303中的数据
    if (agentType.equals("2")) {
      /*------------------------------------欠缴代扣------------------------------------------*/
      agentDetail = new AgentDetail();
      agentDetail.setAgentYearMonth(agentYearMonth);
      agentDetail.setOperator(securityInfo.getUserInfo().getUsername());
      agentDetail.setPayMode(new Integer(payMode));
      agentDetail.setAgentType(new Integer(agentType));
      agentDetail.setNoteNum(agentImportTitleDTO.getNoteNum());
      agentDetail.setStatus("0");
      // 插入EA001
      agentDetailId = agentDetailDAO.insert(agentDetail);
      for (int i = 1; i < agentImport.size(); i++) {
        AgentImportDTO agentImportDTO = (AgentImportDTO) agentImport.get(i);
        String orgAgentNum = agentImportDTO.getOrgAgentNum();
        // 判断输入格式
        if (agentImportDTO.getOrgAgentNum() == null
            || agentImportDTO.getOrgAgentNum().equals("")) {
          throw new BusinessException("第" + (i + 4) + "行的单位代扣号不能为空");
        }
        //  判断是否有相同的职工编号
        for (int j = 0; j < agentImport.size(); j++) {
          AgentImportDTO temp_agentImportDTO = (AgentImportDTO) agentImport
              .get(j);
          if (temp_agentImportDTO.getEmpAgentNum().equals(
              agentImportDTO.getEmpAgentNum())
              && temp_agentImportDTO.getOrgAgentNum().equals(
                  agentImportDTO.getOrgAgentNum())) {
            if (i!=j) {
              throw new BusinessException("第" + (i + 4) + "行的职工代扣号与"+(j + 4)+"行职工代扣号重复！");
            }
          }
        }
        if (!orgAgentNum.equals(temp_orgAgentNum)) {
          
          // 判断单位是否在操作员权限下
          List list = agentDetailDAO.queryOrg(orgAgentNum, securityInfo);
          if (list.size() <= 0) {
            throw new BusinessException("代扣号为" + orgAgentNum + "的单位不在操作员权限下");
          }
          // 判断单位在这个代扣年月下是否存在导入的纪录
          List payModeList = agentDetailDAO.queryPayMode(orgAgentNum,
              agentYearMonth);
          if (payModeList.size() <= 0) {
            throw new BusinessException("代扣号为" + orgAgentNum
                + "的单位没有进行过导入,不能进行欠缴代扣");
          } else if (payModeList.size() == 1) {
            String temp_payMode = payModeList.get(0).toString();
            if (temp_payMode.equals("1")) {
              throw new BusinessException("代扣号为" + orgAgentNum
                  + "的单位已经做过均缴业务,不能进行欠缴代扣");
            } else if (temp_payMode.equals("2") && payMode.equals("2")) {
              throw new BusinessException("代扣号为" + orgAgentNum
                  + "的单位已经做过单位代扣业务,不能进行导入");
            } else if (temp_payMode.equals("3") && payMode.equals("3")) {
              throw new BusinessException("代扣号为" + orgAgentNum
                  + "的单位已经做过职工代扣业务,不能进行导入");
            }
          } else if (payModeList.size() > 1) {
            throw new BusinessException("代扣号为" + orgAgentNum
                + "的单位已经做过缴存业务,不能欠缴代扣");
          }
          temp_orgAgentNum = orgAgentNum;
          // 插入EA002
          orgAgentDetail = new OrgAgentDetail();
          orgAgentDetail.setAgentHeadId(new Integer(agentDetailId.toString()));
          orgAgentDetail.setOrgAgentNum(orgAgentNum);
          orgAgentDetailId = orgAgentDetailDAO
              .insert(orgAgentDetail);
          temp_orgAgentDetailId = orgAgentDetailId.toString();
          //  根据代扣号与年月查询汇缴表中的信息
          List emp_list = agentDetailDAO.queryPayMentInfo(orgAgentNum,
              agentYearMonth);
//          for (int j = 0; j < emp_list.size(); j++) {
//            empAgentDetail = (EmpAgentDetail) emp_list.get(j);
//            empAgentDetail.setOrgAgentId(new Integer(orgAgentDetailId
//                .toString()));
//            // 插入EA003
//            Serializable temp = empAgentDetailDAO.insert(empAgentDetail);
//          }
          empAgentDetail = new EmpAgentDetail();
          empAgentDetail.setEmpAgentNum(agentImportDTO.getEmpAgentNum());
          empAgentDetail.setEmpName(agentImportDTO.getEmpName());
          empAgentDetail.setCardNum(agentImportDTO.getCardNum());
          empAgentDetail.setAgentOrgPay(new BigDecimal(agentImportDTO
              .getAgentOrgPay()));
          empAgentDetail.setAgentEmpPay(new BigDecimal(agentImportDTO
              .getAgentEmpPay()));
          empAgentDetail.setAgentEmpSalary(new BigDecimal(agentImportDTO
              .getAgentEmpSalary()));
          empAgentDetail.setOrgAgentId(new Integer(temp_orgAgentDetailId));
          empAgentDetailDAO.insert(empAgentDetail);

          
          
          //
        }
      }
    } else if (agentType.equals("1")) {
      /*------------------------------------正常代扣------------------------------------------*/
      agentDetail = new AgentDetail();
      agentDetail.setAgentYearMonth(agentYearMonth);
      agentDetail.setOperator(securityInfo.getUserInfo().getUsername());
      agentDetail.setPayMode(new Integer(payMode));
      agentDetail.setAgentType(new Integer(agentType));
      agentDetail.setNoteNum(agentImportTitleDTO.getNoteNum());
      agentDetail.setStatus("0");
      // 插入EA001
      agentDetailId = agentDetailDAO.insert(agentDetail);
      for (int i = 1; i < agentImport.size(); i++) {
        AgentImportDTO agentImportDTO = (AgentImportDTO) agentImport.get(i);
        String orgAgentNum = agentImportDTO.getOrgAgentNum();
        // 判断输入格式
        if (agentImportDTO.getOrgAgentNum() == null
            || agentImportDTO.getOrgAgentNum().equals("")) {
          throw new BusinessException("第" + (i + 4) + "行的单位代扣号不能为空");
        }
        if (agentImportDTO.getEmpAgentNum() == null
            || agentImportDTO.getEmpAgentNum().equals("")) {
          throw new BusinessException("第" + (i + 4) + "行的职工代扣号不能为空");
        }
        if (agentImportDTO.getEmpName() == null
            || agentImportDTO.getEmpName().equals("")) {
          throw new BusinessException("第" + (i + 4) + "行的职工姓名不能为空");
        }
        if (agentImportDTO.getCardNum() == null
            || agentImportDTO.getCardNum().equals("")) {
          throw new BusinessException("第" + (i + 4) + "行的职工身份证号不能为空");
        }
        if (agentImportDTO.getAgentOrgPay() == null
            || agentImportDTO.getAgentOrgPay().equals("")) {
          throw new BusinessException("第" + (i + 4) + "行的单位缴额不能为空");
        }
        if (agentImportDTO.getAgentEmpPay() == null
            || agentImportDTO.getAgentEmpPay().equals("")) {
          throw new BusinessException("第" + (i + 4) + "行的职工缴额不能为空");
        }
        // 判断是否有相同的职工编号
        for (int j = 0; j < agentImport.size(); j++) {
          AgentImportDTO temp_agentImportDTO = (AgentImportDTO) agentImport
              .get(j);
          if (temp_agentImportDTO.getEmpAgentNum().equals(
              agentImportDTO.getEmpAgentNum())
              && temp_agentImportDTO.getOrgAgentNum().equals(
                  agentImportDTO.getOrgAgentNum())) {
            if (i!=j) {
              throw new BusinessException("第" + (i + 4) + "行的职工代扣号与"+(j + 4)+"行职工代扣号重复！");
            }
          }
        }
        if (!orgAgentNum.equals(temp_orgAgentNum)) {
          // 判断单位是否在操作员权限下
          List list = agentDetailDAO.queryOrg(orgAgentNum, securityInfo);
          if (list.size() <= 0) {
            throw new BusinessException("代扣号为" + orgAgentNum + "的单位不在操作员权限下");
          }
          // 判断单位在这个代扣年月下是否存在导入的纪录
          List payModeList = agentDetailDAO.queryPayMode(orgAgentNum,
              agentYearMonth);
          if (payModeList.size() > 0) {
            throw new BusinessException("代扣号为" + orgAgentNum
                + "的单位已经做过导入,不能进行正常代扣");
          }
          temp_orgAgentNum = orgAgentNum;
          // 插入EA002
          orgAgentDetail = new OrgAgentDetail();
          orgAgentDetail.setAgentHeadId(new Integer(agentDetailId.toString()));
          orgAgentDetail.setOrgAgentNum(orgAgentNum);
          orgAgentDetailId = orgAgentDetailDAO
              .insert(orgAgentDetail);
          temp_orgAgentDetailId = orgAgentDetailId.toString();
        }
        // 插入EA003
        empAgentDetail = new EmpAgentDetail();
        empAgentDetail.setEmpAgentNum(agentImportDTO.getEmpAgentNum());
        empAgentDetail.setEmpName(agentImportDTO.getEmpName());
        empAgentDetail.setCardNum(agentImportDTO.getCardNum());
        empAgentDetail.setAgentOrgPay(new BigDecimal(agentImportDTO
            .getAgentOrgPay()));
        empAgentDetail.setAgentEmpPay(new BigDecimal(agentImportDTO
            .getAgentEmpPay()));
        empAgentDetail.setAgentEmpSalary(new BigDecimal(agentImportDTO
            .getAgentEmpSalary()));
        empAgentDetail.setOrgAgentId(new Integer(temp_orgAgentDetailId));
        empAgentDetailDAO.insert(empAgentDetail);

      }
    }
    obj[0] = agentDetailId.toString();
    obj[1] = agentImportTitleDTO.getNoteNum();
    return obj;
  }

  public Object findAgentYearMonthCount(String agentYearMonth)
      throws BusinessException, Exception {
    Object obj = agentDetailDAO.queryStatusByAgentYearMonth(agentYearMonth);
    if (obj != null) {
      return obj;
    }
    return null;
  }

  public Object[] findAgentInfo(Pagination pagination,
      SecurityInfo securityInfo, String agentDetailId)
      throws BusinessException, Exception {

    Object[] obj = new Object[5];

    BigDecimal sumAgentOrgPay = new BigDecimal(0.00);
    BigDecimal sumAgentEmpPay = new BigDecimal(0.00);
    BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);
    Integer orgCount = new Integer(0);

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    List agentInfoList = agentDetailDAO.queryAgentInfoByIdList(agentDetailId,
        orderBy, order, start, pageSize, page);
    String temp_payMode = "";
    if (agentInfoList!=null&&agentInfoList.size()>0) {
      AgentInfoDTO temp_agentInfoDTO = (AgentInfoDTO) agentInfoList.get(0);
      // 得到缴存方式
      temp_payMode = temp_agentInfoDTO.getPayMode();
    }
    for (int i = 0; i < agentInfoList.size(); i++) {
      AgentInfoDTO agentInfoDTO = (AgentInfoDTO)agentInfoList.get(i);
      String payMode = agentInfoDTO.getPayMode();
      if (payMode.equals("1")) {
        agentInfoDTO.setPayMode("均缴");
      }else if (payMode.equals("2")){
        agentInfoDTO.setPayMode("缴单位");
        agentInfoDTO.setSumAgentEmpPay(new BigDecimal(0.00));
      }else if (payMode.equals("3")){
        agentInfoDTO.setPayMode("缴职工");
        agentInfoDTO.setSumAgentOrgPay(new BigDecimal(0.00));
      }
    }
    List countList = agentDetailDAO.queryAgentInfoByIdCount(agentDetailId);

    for (int i = 0; i < countList.size(); i++) {
      AgentInfoDTO agentInfoDTO = (AgentInfoDTO) countList.get(i);
      sumAgentOrgPay = sumAgentOrgPay.add(agentInfoDTO.getSumAgentOrgPay());
      sumAgentEmpPay = sumAgentEmpPay.add(agentInfoDTO.getSumAgentEmpPay());
      sumAgentEmpSalary = sumAgentEmpSalary.add(agentInfoDTO
          .getSumAgentEmpSalary());
    }
    pagination.setNrOfElements(countList.size());
    orgCount = new Integer(countList.size());
    if (temp_payMode.equals("2")) {
      sumAgentEmpPay = new BigDecimal(0.00);
    }else if (temp_payMode.equals("3")) {
      sumAgentOrgPay = new BigDecimal(0.00);
    }
    obj[0] = agentInfoList;
    obj[1] = sumAgentOrgPay;
    obj[2] = sumAgentEmpPay;
    obj[3] = sumAgentEmpSalary;
    obj[4] = orgCount;

    return obj;
  }

  public void deleteAgentInfo(String agentDetailId, String orgAgentId)
      throws BusinessException, Exception {
    
    agentDetailDAO.deleteAgentInfoByOrg(agentDetailId, orgAgentId);
  }

  public void deleteAllAgentInfo(String agentDetailId, String orgAgentNum)
      throws BusinessException, Exception {
    agentDetailDAO.deleteAgentInfoByAgentDetailId(agentDetailId, orgAgentNum);
  }

  public void deleteEmpAgentInfo(String empAgentId) {
    agentDetailDAO.deleteAgentInfoByEmpAgentId(empAgentId);
  }

  public List findAgentChgInfoList(Pagination pagination, SecurityInfo info)
      throws BusinessException, Exception {

    String agentHeadId = "";
    String agentYearMonth = "";
    if (pagination.getQueryCriterions().get("agentHeadId") != null) {
      agentHeadId = (String) pagination.getQueryCriterions().get("agentHeadId");
    }
    if (pagination.getQueryCriterions().get("agentYearMonth") != null) {
      agentYearMonth = (String) pagination.getQueryCriterions().get(
          "agentYearMonth");
    }

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    List list = agentDetailDAO.queryAgentChgInfoList(agentHeadId,
        agentYearMonth, orderBy, order, start, pageSize, page,info);
    for (int i = 0; i < list.size(); i++) {
      AgentChgListDTO agentChgListDTO = (AgentChgListDTO) list.get(i);
      agentChgListDTO.setStrStatus(BusiTools.getBusiValue_WL(agentChgListDTO.getStatus(), BusiConst.AGENTSTATUS));
      String payMode = agentChgListDTO.getPayMode();
      if (payMode.equals("1")) {
        agentChgListDTO.setPayMode("均缴");
      }else if (payMode.equals("2")){
        agentChgListDTO.setPayMode("缴单位");
        agentChgListDTO.setSumAgentEmpPay(new BigDecimal(0.00));
      }else if (payMode.equals("3")){
        agentChgListDTO.setPayMode("缴职工");
        agentChgListDTO.setSumAgentOrgPay(new BigDecimal(0.00));
      }
    }
    List countList = agentDetailDAO.queryAgentChgInfoCount(agentHeadId,
        agentYearMonth,info);
    pagination.setNrOfElements(countList.size());

    return list;
  }

  public void createAgentChange(String agentHeadId, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    // 判断变更的最小年月
    List list = agentDetailDAO.queryAgentChgMinYearMonth(agentHeadId);
    if (list.size() <= 0) {
      throw new BusinessException("请选择最小的汇缴年月办理");
    }
    try {
      agentDetailDAO.insertAgentChangeInfo(agentHeadId, securityInfo);
    } catch (BusinessException bex) {
      String errorCode = bex.getLocalizedMessage();
      if (errorCode.equals("20001")) {
        throw new BusinessException("该年月存在未扎账的汇缴!");
      } else if (errorCode.equals("20002")) {
        throw new BusinessException("单位中存在未启用的变更!");
      } else if (errorCode.equals("20003")) {
        throw new BusinessException("代扣年月小于最大的缴存年月不能进行汇缴!");
      }
    }
    //  插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_CONFIRM);
    hafOperateLog.setOpBizId(new Integer(agentHeadId));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(agentHeadId));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
  }

  public void backAgentChagneInfo(String agentHeadId, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    // 判断该代扣批号在AA301中是否存在记帐的业务
    List temp_list = agentDetailDAO.queryIsbackAgentChagne(agentHeadId);
    if (temp_list.size()>0) {
      throw new BusinessException("变更中可能存在已经记帐的业务不能进行撤销！");
    }
    // 判断该笔业务在AA301中是否为该单位下id最大的记录
    List list = agentDetailDAO.queryPayByFinancialPayId(agentHeadId);
    for (int i = 0; i < list.size(); i++) {
      Object[] obj = (Object[]) list.get(i);
      // AA301id
      String id = obj[0].toString();
      // 单位编号
      String orgId = obj[1].toString();
      Object temp_id = agentDetailDAO.queryPayIdByOrgId(orgId);
      if (!id.equals(temp_id.toString())) {
        throw new BusinessException("单位编号为" + orgId + "的单位不是最近的一笔业务");
      }
      // 删除AA319,汇缴
      agentDetailDAO.deleteBizActivityLogByPersonChg(id);
      /*---------------------------------------缴额调整----------------------------------------------*/
       // 查询该单位职工做过的缴额调整业务。
      List payChgList = agentDetailDAO.queryPayChgInfo(id, orgId);
      for (int j = 0; j < payChgList.size(); j++) {
        PayChgInfoDTO payChgInfoDTO = (PayChgInfoDTO) payChgList.get(j);
        // 修改职工信息
        String empId = payChgInfoDTO.getEmpId();
        BigDecimal oldSalaryBase = payChgInfoDTO.getOldSalaryBase();
        BigDecimal oldOrgPay = payChgInfoDTO.getOldOrgPay();
        BigDecimal oldEmpPay = payChgInfoDTO.getOldEmpPay();
        agentDetailDAO.updateEmpPay(empId, orgId, oldSalaryBase, oldOrgPay,
            oldEmpPay);
      }
      if (payChgList.size() > 0) {
        PayChgInfoDTO payChgInfoDTO = (PayChgInfoDTO) payChgList.get(0);
        String payChgId = payChgInfoDTO.getId();
        // 删除缴额调整业务的AA319表，缴额调整
        agentDetailDAO.deleteBizActivityLogByPayChg(payChgId);
        // 删除AA202,AA203
        agentDetailDAO.deletePayChg(payChgId);
      }
      /*---------------------------------------人员变更----------------------------------------------*/
      // 查询该单位职工做过的人员变更业务。
      List personChgList = agentDetailDAO.queryPersonChgInfo(id, orgId);
      if (personChgList.size() > 0) {
        PersonChgInfoDTO temp_personChgInfoDTO = (PersonChgInfoDTO) personChgList
            .get(0);
        // 删除人员变更时生成的业务日志
        String personChgId = temp_personChgInfoDTO.getId();
        // 删除缴额调整业务的AA319表，人员变更
        agentDetailDAO.deleteBizActivityLogByPersonChg(personChgId);
        for (int j = 0; j < personChgList.size(); j++) {
          PersonChgInfoDTO personChgInfoDTO = (PersonChgInfoDTO) personChgList
              .get(j);
          String status = personChgInfoDTO.getChgType();
          String p_orgId = personChgInfoDTO.getOrgId();
          String p_empId = personChgInfoDTO.getEmpId();
          String personHeadId = personChgInfoDTO.getId();
          String payStatus = personChgInfoDTO.getOldPayStatus();
          if (status.equals("1")) {
            // 新增正常
            // 删除AA002，BA002
            //agentDetailDAO.deleteEmpAndEmpInfo(p_orgId, p_empId);
            // 人员变更新增情况在撤销时不在删除BA002，而是将状态变成5。2008.3.4日修改
            agentDetailDAO.updateEmp(orgId, p_empId, "5");
          } else if (status.equals("3") || status.equals("4")) {
            // 启封与封存
            agentDetailDAO.updateEmp(p_orgId, p_empId, payStatus);
          }
        }
        // 删除人员变更表
        agentDetailDAO.deletePersonChgInfo(personChgId);
      }
    }
    /*---------------------------------------删除汇缴表----------------------------------------------*/
    // 删除AA301，AA302，AA303
    agentDetailDAO.deletePayment(agentHeadId);
    // 更新代扣清册状态
    agentDetailDAO.updateAgentStatus(agentHeadId);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_REVOCATION);
    hafOperateLog.setOpBizId(new Integer(agentHeadId));
    
  
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(agentHeadId));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
  }

  public Object[] findAgentOrgInfoList(Pagination pagination, String agentHeadId)
      throws BusinessException, Exception {

    Object[] obj = new Object[6];

    BigDecimal sumAgentOrgPay = new BigDecimal(0.00);
    BigDecimal sumAgentEmpPay = new BigDecimal(0.00);
    BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);
    Integer orgCount = new Integer(0);

    String orgId = "";
    String orgAgentNum = "";
    if (pagination.getQueryCriterions().get("orgId") != null) {
      orgId = (String) pagination.getQueryCriterions().get("orgId");
    }
    if (pagination.getQueryCriterions().get("orgAgentNum") != null) {
      orgAgentNum = (String) pagination.getQueryCriterions().get("orgAgentNum");
    }

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    List list = agentDetailDAO.queryAgentOrgInfoList(agentHeadId, orgId,
        orgAgentNum, orderBy, order, start, pageSize, page);
    /* 缴存方式*/
    String temp_payMode = "";
    if (list!=null&&list.size()>0) {
      AgentInfoDTO temp_agentInfoDTO = (AgentInfoDTO) list.get(0);
      temp_payMode = temp_agentInfoDTO.getPayMode();
    }
    for (int i = 0; i < list.size(); i++) {
      AgentInfoDTO agentInfoDTO = (AgentInfoDTO) list.get(i);
      String payMode = agentInfoDTO.getPayMode();
      if (payMode.equals("2")){
        agentInfoDTO.setSumAgentEmpPay(new BigDecimal(0.00));
      }else if (payMode.equals("3")){
        agentInfoDTO.setSumAgentOrgPay(new BigDecimal(0.00));
      }
    }
    List countList = agentDetailDAO.queryAgentOrgInfoCount(agentHeadId, orgId,
        orgAgentNum);

    for (int i = 0; i < countList.size(); i++) {
      AgentInfoDTO agentInfoDTO = (AgentInfoDTO) countList.get(i);
      sumAgentOrgPay = sumAgentOrgPay.add(agentInfoDTO.getSumAgentOrgPay());
      sumAgentEmpPay = sumAgentEmpPay.add(agentInfoDTO.getSumAgentEmpPay());
      sumAgentEmpSalary = sumAgentEmpSalary.add(agentInfoDTO
          .getSumAgentEmpSalary());
    }
    pagination.setNrOfElements(countList.size());
    orgCount = new Integer(countList.size());
    if (temp_payMode.equals("2")) {
      sumAgentEmpPay = new BigDecimal(0.00);
    }else if (temp_payMode.equals("3")) {
      sumAgentOrgPay = new BigDecimal(0.00);
    }
    obj[0] = list;
    obj[1] = sumAgentOrgPay;
    obj[2] = sumAgentEmpPay;
    obj[3] = sumAgentEmpSalary;
    obj[4] = orgCount;
    obj[5] = countList;
    return obj;
  }

  public Object[] findAgentEmpInfoList(Pagination pagination,
      String orgAgentId, String orgId, String payMode) throws BusinessException, Exception {
    Object[] obj = new Object[6];

    BigDecimal sumAgentOrgPay = new BigDecimal(0.00);
    BigDecimal sumAgentEmpPay = new BigDecimal(0.00);
    BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);
    Integer orgCount = new Integer(0);

    String empId = (String) pagination.getQueryCriterions().get("emppopid");
    String empName = (String) pagination.getQueryCriterions().get("emppopname");
    String cardNum = (String) pagination.getQueryCriterions().get(
        "emppopcardid");
    String empAgentNum = (String) pagination.getQueryCriterions().get(
        "emppopkoucode");

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    List list = agentDetailDAO.queryAgentEmpInfoList(orgAgentId, orgId, empId,
        empName, cardNum, empAgentNum, orderBy, order, start, pageSize, page);
    for (int i = 0; i < list.size(); i++) {
      AgentChgListDTO agentChgListDTO = (AgentChgListDTO) list.get(i);
      payMode=agentChgListDTO.getPayMode();
      if (payMode.equals("2")){
        agentChgListDTO.setAgentEmppopemppay(new BigDecimal(0.00));
      }else if (payMode.equals("3")){
        agentChgListDTO.setAgentEmppoporgpay(new BigDecimal(0.00));
      }
    }
    List countList = agentDetailDAO.queryAgentEmpInfoCount(orgAgentId, orgId,
        empId, empName, cardNum, empAgentNum);

    for (int i = 0; i < countList.size(); i++) {
      AgentChgListDTO agentChgListDTO = (AgentChgListDTO) countList.get(i);
      sumAgentOrgPay = sumAgentOrgPay.add(agentChgListDTO
          .getAgentEmppoporgpay());
      sumAgentEmpPay = sumAgentEmpPay.add(agentChgListDTO
          .getAgentEmppopemppay());
      sumAgentEmpSalary = sumAgentEmpSalary.add(agentChgListDTO
          .getAgentEmppopmonthsalary());
    }
    pagination.setNrOfElements(countList.size());
    orgCount = new Integer(countList.size());
    if (payMode.equals("2")) {
      sumAgentEmpPay = new BigDecimal(0.00);
    }else if (payMode.equals("3")) {
      sumAgentOrgPay = new BigDecimal(0.00);
    }
    obj[0] = list;
    obj[1] = sumAgentOrgPay;
    obj[2] = sumAgentEmpPay;
    obj[3] = sumAgentEmpSalary;
    obj[4] = orgCount;
    obj[5] = countList;
    return obj;
  }

  // --------------------------------------------------------------------------------

  /*
   * 获得代扣信息集合 list 张列 (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS#queryAgentInfoTaList(org.xpup.common.util.Pagination)
   */
  public List queryAgentInfoTaList(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String payId = (String) pagination.getQueryCriterions().get("payId");
      String payMonth = (String) pagination.getQueryCriterions()
          .get("payMonth");
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      list = agentDetailDAO.queryAgentInfoTaList(payId, payMonth, orderBy,
          order, start, pageSize,securityInfo);
      for (int i = 0; i < list.size(); i++) {
        AgentInfoQueryTaDTO agentInfoQueryTaDTO = (AgentInfoQueryTaDTO)list.get(i);
        String payMode = agentInfoQueryTaDTO.getPayMode();
        if (payMode.equals("1")) {
          agentInfoQueryTaDTO.setPayMode("均缴");
        }else if (payMode.equals("2")){
          agentInfoQueryTaDTO.setPayMode("缴单位");
        }else if (payMode.equals("3")){
          agentInfoQueryTaDTO.setPayMode("缴职工");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 获得代扣信息集合 list 张列 (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS#queryAgentInfoTaList(org.xpup.common.util.Pagination)
   */
  public int queryAgentInfoTaListCount(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      String payId = (String) pagination.getQueryCriterions().get("payId");
      String payMonth = (String) pagination.getQueryCriterions()
          .get("payMonth");
      int start = pagination.getFirstElementOnPage() - 1;
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      Integer count = agentDetailDAO.queryAgentInfoTaListCount(payId, payMonth,
          orderBy, order, start,securityInfo);
      return count.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /*
   * 获得代扣信息(单位)集合 list 张列 (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS#queryAgentInfoTaList(org.xpup.common.util.Pagination)
   */
  public List queryAgentInfoTbList(Pagination pagination) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String payId = (String) pagination.getQueryCriterions().get("payId");
      String payMonth = (String) pagination.getQueryCriterions()
          .get("payMonth");
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String orgAgentNum = (String) pagination.getQueryCriterions().get(
          "orgAgentNum");
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      list = agentDetailDAO.queryAgentInfoTbList(payId, payMonth, orgId,
          orgName, orgAgentNum, orderBy, order, start, pageSize);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 获得代扣信息(单位)集合 list 张列 (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS#queryAgentInfoTaList(org.xpup.common.util.Pagination)
   */
  public int queryAgentInfoTbListCount(Pagination pagination) throws Exception {
    // TODO Auto-generated method stub
    try {
      String payId = (String) pagination.getQueryCriterions().get("payId");
      String payMonth = (String) pagination.getQueryCriterions()
          .get("payMonth");
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String orgAgentNum = (String) pagination.getQueryCriterions().get(
          "orgAgentNum");
      int start = pagination.getFirstElementOnPage() - 1;
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      Integer count = agentDetailDAO.queryAgentInfoTbListCount(payId, payMonth,
          orgId, orgName, orgAgentNum, orderBy, order, start);
      return count.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /*
   * 获得代扣信息(职工)集合 list 张列 (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS#queryAgentInfoTaList(org.xpup.common.util.Pagination)
   */
  public List queryAgentInfoTcList(Pagination pagination) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String payId = (String) pagination.getQueryCriterions().get("payId");
      String empId = (String) pagination.getQueryCriterions().get("empId");
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String empName = (String) pagination.getQueryCriterions().get("empName");
      String empAgentNum = (String) pagination.getQueryCriterions().get(
          "empAgentNum");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      list = agentDetailDAO.queryAgentInfoTcList(payId, orgId, empId, empName,
          empAgentNum, cardNum, orderBy, order, start, pageSize);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 获得代扣信息(职工)集合 list 张列 (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS#queryAgentInfoTaList(org.xpup.common.util.Pagination)
   */
  public int queryAgentInfoTcListCount(Pagination pagination) throws Exception {
    // TODO Auto-generated method stub
    try {
      String payId = (String) pagination.getQueryCriterions().get("payId");
      String empId = (String) pagination.getQueryCriterions().get("empId");
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String empName = (String) pagination.getQueryCriterions().get("empName");
      String empAgentNum = (String) pagination.getQueryCriterions().get(
          "empAgentNum");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      int start = pagination.getFirstElementOnPage() - 1;
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      Integer count = agentDetailDAO.queryAgentInfoTcListCount(payId, orgId,
          empId, empName, empAgentNum, cardNum, orderBy, order, start);
      return count.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  // --------------------------------------------------------------------------------

}
