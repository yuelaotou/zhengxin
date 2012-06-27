package org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaPrintDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTbAF;

/**
 * @author 王野 2007-10-02
 */
public interface IBailenRolBS {

  // 通过合同编号带出页面信息
  public BailenRolTaAF queryContractInfo(String id, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 插入保证金登记相关信息（页面信息）
  public BailenRolTaPrintDTO saveBailenRol(BailenRolTaDTO bailenRolTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 保证金登记列表
  public BailenRolTbAF queryBailenRolListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 删除保证金登记维护列表单个记录
  public void deleteBailenRolInfo(String flowHeadId, String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 保证金登记维护列表打印
  public List findBailenRolTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception;

  // 转换银行名称
  public String changeBank(String loanBankId) throws Exception;

  // 通过银行ID连带放款银行账号
  public String queryBailenRolTaBankAccByBankId(String bankId) throws Exception;
}
