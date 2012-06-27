package org.xpup.hafmis.sysloan.loanapply.specialapply.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;

public interface ISpecialapplyBS {
  // 根据id查询SpecialBorrower记录
  public SpecialBorrower findSpecialBorrowerById(Integer id) throws Exception;

  // 根据id查询Emp记录
  public Emp findEmpById(String id, String orgId) throws Exception;

  // 根据职工编号和单位编号 查询PL112表中 借款人状态值
  public SpecialBorrower findSpecialBorrowerStutasByEmpId(String id,
      String orgId) throws Exception;

  // 职工借款人姓名、证件号码 查询PL112表中 借款人状态值
  public SpecialBorrower findSpecialBorrowerStutasByNameAndNum(String name,
      String num) throws Exception;

  // 职工编号 单位编号 查询PL112表中 ROWNUM=1 借款人的状态值
  public SpecialBorrower findSpecialBorrowerStutasByEmpIdTop1(String id,
      String orgId) throws Exception;

  // //职工借款人姓名、证件号码 查询PL112表中 ROWNUM=1 借款人的状态值
  public SpecialBorrower findSpecialBorrowerStutasByNameAndNumTop1(String name,
      String num) throws Exception;

  // 根据id查询SpecialapplyDTO里面的记录
  public SpecialapplyDTO findSpecialapplyInfoById(String id, String orgId)
      throws Exception;

  // 借款人姓名 和 证件号码 是否一致(PL112表) //特殊借款人信息表PL112是否存在借款人姓名、证件号码等于录入的记录
  public Boolean isCheckNameANDCardNum_SpecialBorrower(String borrowerName,
      String cardNum) throws Exception;

  // 根据EMPID查询是否有记录 (pl112表)
  public Boolean isCheckSpecialBorrowerByEmpId(String empId) throws Exception;

  // 返回查询结果(List) 特殊借款人信息
  public List findSpecialapplyList(Pagination pagination, String operator)
      throws Exception, BusinessException;

  // 查询pl112中条件的记录条数
  public int findSpecialapplyCount(String privilegeBorrowerId,
      String borrwerName, String cardNum, String operator) throws Exception,
      BusinessException;

  // 查询pl112中总的记录条数
  public int findSpecialapplyAllCount() throws Exception, BusinessException;

  // 删除单条记录
  public void deleteSpecialapply(Integer id, SecurityInfo securityInfo)
      throws BusinessException;

  public void deleteSpecialapply(String borrowerName, String cardNum,
      SecurityInfo securityInfo) throws BusinessException;

  // 更新数据记录
  public void updateSpecialapply(SpecialapplyDTO specialapplyDTO)
      throws BusinessException;

  // 插入 特殊借款人信息表PL112和 插入：操作日志PL021
  public void insertSpecialApplyInfo(SpecialapplyDTO specialapplyDTO,
      SecurityInfo securityInfo) throws BusinessException;

  public Developer queryDeveloperInfo(String headId) throws Exception,
      BusinessException;

  public DevelopProject queryFloorInfo(String floorId) throws Exception,
      BusinessException;
  public List queryFloorListByHeadid(String headId) throws Exception;
  
  public List queryFloorNumList(String headId, String floorName) throws Exception;
}
