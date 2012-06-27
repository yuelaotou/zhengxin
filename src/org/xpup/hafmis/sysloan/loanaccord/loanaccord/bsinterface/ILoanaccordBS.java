package org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;

public interface ILoanaccordBS {
  public String getLoanBankId(String contractId) throws BusinessException;
  //查询贷款发放的相关信息以便核实
  public LoanaccordNewAF queryLoanaccordInfo(String contractId,SecurityInfo securityInfo)throws BusinessException;
  //更新pl111
  public String updateBorrowerAccInfo(LoanaccordDTO loanaccordDTO,SecurityInfo securityInfo)throws BusinessException;
  //发放维护查找
  public LoanaccordShowAF queryLoanaccordList(Pagination pagination,SecurityInfo securityInfo) throws BusinessException;
  //删除发放的记录
  public void removeLoanaccordInfo(String contractId,SecurityInfo securityInfo)throws BusinessException;
  //打印凭证
  public LoanaccordDTO printLoanaccordInfo(String contractId,SecurityInfo securityInfo)throws BusinessException;
}
