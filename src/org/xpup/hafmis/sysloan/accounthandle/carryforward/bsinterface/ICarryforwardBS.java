package org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardTbShowAF;

public interface ICarryforwardBS {
  // 查找显示需要年结的记录
  public CarryforwardShowAF queryBorrowerAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;

  // 年终结转以中心为主
  public String useCarryforward(String loanBankId,
      SecurityInfo securityInfo) throws BusinessException;
  
  // 年终结转以银行为主
  public String useBankCarryforward(String loanBankId,
      SecurityInfo securityInfo) throws BusinessException;
  
  //以银行为主的时候，进行验证的查询
  public String queryCarrayforwardInfo(String loanBankId,
      SecurityInfo securityInfo)throws BusinessException;
}
