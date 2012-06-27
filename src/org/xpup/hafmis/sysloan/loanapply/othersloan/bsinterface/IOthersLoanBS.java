package org.xpup.hafmis.sysloan.loanapply.othersloan.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.OthersLoan;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanShowAF;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanTbShowAF;
public interface IOthersLoanBS {

  // 显示办理异地贷款页面
  public OthersLoanShowAF queryBorrowerListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 保存办理页面信息
  public void saveOthersLoanInfo(OthersLoanShowAF othersLoanShowAF,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  // 查询维护页面列表
  public OthersLoanTbShowAF queryOthersLoanListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException ;
  public void deleteOthersLoan(
      String id) throws Exception,
      BusinessException ;
  public OthersLoan queryOthersLoan(
      String id) throws Exception,
      BusinessException ;
  public OthersLoanTbShowAF queryOthersLoanListTcByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException ;
}
