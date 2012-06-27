package org.xpup.hafmis.sysloan.loanapply.specialapply.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscommon.dao.EmpInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.SpecialBorrowerDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.specialapply.bsinterface.ISpecialapplyBS;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;

public class SpecialapplyBS implements ISpecialapplyBS {

  private SpecialBorrowerDAO specialBorrowerDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private EmpDAO empDAO = null;

  private EmpInfoDAO empInfoDAO = null;
  
  private DeveloperDAO developerDAO = null;
  
  private DevelopProjectDAO developProjectDAO = null;

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public EmpDAO getEmpDAO() {
    return empDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public EmpInfoDAO getEmpInfoDAO() {
    return empInfoDAO;
  }

  public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
    this.empInfoDAO = empInfoDAO;
  }

  public PlOperateLogDAO getPlOperateLogDAO() {
    return plOperateLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public SpecialBorrowerDAO getSpecialBorrowerDAO() {
    return specialBorrowerDAO;
  }

  public void setSpecialBorrowerDAO(SpecialBorrowerDAO specialBorrowerDAO) {
    this.specialBorrowerDAO = specialBorrowerDAO;
  }

  /**
   * 根据id查询SpecialBorrower记录 返回SpecialBorrower对象
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public SpecialBorrower findSpecialBorrowerById(Integer id) throws Exception {
    SpecialBorrower specialBorrower = specialBorrowerDAO.queryById(id);
    return specialBorrower;
  }

  /**
   * 根据EMPID 返回 SpecialBorrower 如果状态为不为零返回 NULL
   * 
   * @param id orgId
   * @return
   * @throws Exception
   */
  public SpecialBorrower findSpecialBorrowerStutasByEmpId(String id,
      String orgId) throws Exception {
    SpecialBorrower specialBorrower = specialBorrowerDAO.queryStutasByEmpId(id,
        orgId);
    return specialBorrower;
  }

  /**
   * 根据EMPID 返回 SpecialBorrower 如果状态为一 显示一条记录回来
   * 
   * @param id orgId
   * @return
   * @throws Exception
   */
  public SpecialBorrower findSpecialBorrowerStutasByEmpIdTop1(String id,
      String orgId) throws Exception {
    SpecialBorrower specialBorrower = specialBorrowerDAO
        .queryStutasByEmpIdTop1(id, orgId);
    return specialBorrower;
  }

  /**
   * 根据EMPID 返回 SpecialBorrower 如果状态为一 显示一条记录回来
   * 
   * @param id orgId
   * @return
   * @throws Exception
   */
  public SpecialBorrower findSpecialBorrowerStutasByNameAndNumTop1(String name,
      String num) throws Exception {
    SpecialBorrower specialBorrower = specialBorrowerDAO
        .queryStutasByNameAndNumTop1(name, num);
    return specialBorrower;
  }

  /**
   * 根据借款人姓名、证件号码 返回 SpecialBorrower 如果状态为不为零返回 NULL
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public SpecialBorrower findSpecialBorrowerStutasByNameAndNum(String name,
      String num) throws Exception {
    SpecialBorrower specialBorrower = specialBorrowerDAO
        .queryStutasByNameAndNum(name, num);
    return specialBorrower;
  }

  /**
   * 根据id查询Emp记录 返回Emp对象
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public Emp findEmpById(String id, String orgId) throws Exception {
    List list = empDAO.queryEmpById(new Integer(id), new Integer(orgId));
    Iterator it = list.iterator();
    Emp emp = (Emp) it.next();
    return emp;
  }

  /**
   * 根据id查询SpecialapplyDTO记录 返回SpecialapplyDTO对象
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public SpecialapplyDTO findSpecialapplyInfoById(String id, String orgId)
      throws Exception {
    SpecialapplyDTO specialapplyDTO = empDAO.queryEmpByEmpId_zl(id, orgId);
    return specialapplyDTO;
  }

  /**
   * 根据EMPID查询是否有记录 (pl112表)
   * 
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public Boolean isCheckSpecialBorrowerByEmpId(String empId) throws Exception {
    Boolean is_temp = specialBorrowerDAO.isSpecialBorrowerById(empId);
    return is_temp;
  }

  /**
   * 姓名和证件号码相同 (PL112表)
   * 
   * @param borrowerName
   * @param cardNum
   * @return
   * @throws Exception
   */
  public Boolean isCheckNameANDCardNum_SpecialBorrower(String borrowerName,
      String cardNum) throws Exception {
    Boolean is_temp = specialBorrowerDAO.isSpecialBorrowerByBorrowname_zl(
        borrowerName, cardNum);
    return is_temp;
  }

  /**
   * 返回查询结果(List) 特殊借款人信息
   */
  public List findSpecialapplyList(Pagination pagination, String operator)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      String empId = (String) pagination.getQueryCriterions().get("empId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = this.specialBorrowerDAO.querySpecialapplyList_zl(empId,
          borrowerName, cardNum, orderBy, order, start, pageSize, page,
          operator);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 张列 查询pl112中条件的记录条数
   * 
   * @param privilegeBorrowerId 借款人职工编号
   * @param borrwerName 借款人姓名
   * @param cardNum 证件号码
   * @throws Exception, BusinessException
   * @return
   */
  public int findSpecialapplyCount(String privilegeBorrowerId,
      String borrwerName, String cardNum, String operator) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    // 记录条数
    int count = 0;
    try {
      count = specialBorrowerDAO.querySpecialapplyCount_zl(privilegeBorrowerId,
          borrwerName, cardNum, operator).intValue();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 查询pl112中总的记录条数
   * 
   * @return
   */
  public int findSpecialapplyAllCount() throws Exception, BusinessException {
    int count = 0;
    try {
      count = specialBorrowerDAO.querySpecialapplyCount_zl().intValue();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 张列 删除SpecialBorrower对象 并且 插入：操作日志PL021 生成PLOPERATE_LOG_ID， OP_SYS = 2 ，
   * OP_MODEL=特殊申请_特殊申请维护_删除 OP_BUTTON=3， OP_BIZ_ID=PL112的id， OP_IP=操作员的IP，
   * CONTRACT_ID=合同编号， OP_TIME=系统时间， OPERATOR=操作员
   * 
   * @param specialBorrower
   */
  public void deleteSpecialapply(Integer id, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      SpecialBorrower specialBorrower = specialBorrowerDAO.queryById(id);
      if (specialBorrower == null) {
        throw new BusinessException("该特殊申请不存在,不能删除");
      } else {
        if (specialBorrower.getStatus().equals("1")) {
          specialBorrower.setStatus("2");
        } else if (specialBorrower.getStatus().equals("0")) {
          specialBorrowerDAO.delete(specialBorrower);
        }
      }
      // 日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_MAINTAIN));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
      plOperateLog.setOpBizId(new BigDecimal(specialBorrower
          .getPrivilegeBorrowerId().toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      throw bex;
    }
  }

  public void deleteSpecialapply(String borrowerName, String cardNum,
      SecurityInfo securityInfo) throws BusinessException {
    try {
      SpecialBorrower specialBorrower = specialBorrowerDAO
          .querySpeBorrowerByNameAndNum(borrowerName, cardNum);
      if (specialBorrower == null) {
        throw new BusinessException("该特殊申请不存在,不能删除");
      } else {
        if (specialBorrower.getStatus().equals("1")) {
          specialBorrower.setStatus("2");
        } else if (specialBorrower.getStatus().equals("0")) {
          specialBorrowerDAO.delete(specialBorrower);
        }
      }
      // 日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_MAINTAIN));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
      plOperateLog.setOpBizId(new BigDecimal(specialBorrower
          .getPrivilegeBorrowerId().toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      throw bex;
    }
  }

  /**
   * 更新SpecialBorrower (pl112)表信息
   * 
   * @param specialapplyNewDTO
   */
  public void updateSpecialapply(SpecialapplyDTO specialapplyDTO)
      throws BusinessException {
    try {
      specialBorrowerDAO.update(specialapplyDTO);
      // 插入：操作日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_DO));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(specialapplyDTO
          .getPrivilegeBorrowerId().toString()));
      plOperateLog.setOpIp(specialapplyDTO.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(specialapplyDTO.getOperator());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 插入信息 插入 特殊借款人信息表PL112和 插入：操作日志PL021
   * 
   * @param specialapplyDTO
   * @param securityInfo
   */
  public void insertSpecialApplyInfo(SpecialapplyDTO specialapplyDTO,
      SecurityInfo securityInfo) throws BusinessException {
    SpecialBorrower specialBorrower = new SpecialBorrower();
    specialBorrower.setEmpId(new BigDecimal(specialapplyDTO.getEmpId()));
    specialBorrower.setBorrowerName(specialapplyDTO.getBorrowerName());
    specialBorrower.setCardKind(specialapplyDTO.getCardKind());
    specialBorrower.setCardNum(specialapplyDTO.getCardNum());
    specialBorrower.setLoanMoney(specialapplyDTO.getLoanMoney());
    specialBorrower.setLoanTimeLimit(specialapplyDTO.getLoanTimeLimit());
    specialBorrower.setOrgId(new BigDecimal(specialapplyDTO.getOrgId()
        .toString()));
    specialBorrower.setOrgName(specialapplyDTO.getOrgName());
    specialBorrower.setOperator(specialapplyDTO.getOperator());
    specialBorrower.setOpTime(new Date());
    specialBorrower.setStatus(specialapplyDTO.getStutas());
    if (specialapplyDTO.getReserveaA() == null
        || specialapplyDTO.getReserveaA().equals("")) {
      specialBorrower.setReserveaA("");
    } else {
      specialBorrower.setReserveaA(specialapplyDTO.getReserveaA());
    }
    if (specialapplyDTO.getReserveaB() == null
        || specialapplyDTO.getReserveaB().equals("")) {
      specialBorrower.setReserveaB("");
    } else {
      specialBorrower.setReserveaB(specialapplyDTO.getReserveaB());
    }
    if (specialapplyDTO.getReserveaC() == null
        || specialapplyDTO.getReserveaC().equals("")) {
      specialBorrower.setReserveaC("");
    } else {
      specialBorrower.setReserveaC(specialapplyDTO.getReserveaC());
    }
    System.out.println(specialapplyDTO.getHeadId()+"=================>");
    specialBorrower.setHeadId(specialapplyDTO.getHeadId());
    specialBorrower.setFloorId(specialapplyDTO.getFloorId());

    specialBorrower.setPerBankAcc(specialapplyDTO.getPerBankAcc());
    // 插入数据PL112表
    specialBorrowerDAO.insert(specialBorrower);
    // 日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_DO));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
    plOperateLog.setOpBizId(new BigDecimal(specialBorrower
        .getPrivilegeBorrowerId().toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());
    // 插入日志
    plOperateLogDAO.insert(plOperateLog);
  }

  public Developer queryDeveloperInfo(String headId) throws Exception, BusinessException {
    return developerDAO.queryById(Integer.valueOf(headId));
  }

  public DevelopProject queryFloorInfo(String floorId) throws Exception,
      BusinessException {
    return developProjectDAO.queryById(Integer.valueOf(floorId));
  }

  public List queryFloorListByHeadid(String headId) throws Exception {
    return developerDAO.findSomeFloorListById(headId);
  }

  public List queryFloorNumList(String headId, String floorName) throws Exception {
    return developProjectDAO.queryAllBuildNumList(headId, floorName);
  }

}
