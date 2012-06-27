package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.business;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.bsinterface.ILoanBackByFundBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.form.LoanBackByFundAF;

/**
 * @author ั๎นโ 20090303
 */
public class LoanBackByFundBS implements ILoanBackByFundBS {

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public LoanBackByFundAF queryLoanBackByFund(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    LoanBackByFundAF loanBackByFundAF = new LoanBackByFundAF();
    String bankid = (String) pagination.getQueryCriterions().get("loanBank");
    String beginDate = (String) pagination.getQueryCriterions()
        .get("beginBizDate");
    String endDate = (String) pagination.getQueryCriterions().get("endBizDate");
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List showList = orgHAFAccountFlowDAO.queryLoanBackByFund(securityInfo,
        bankid, beginDate, endDate, start, pageSize);
    List list = orgHAFAccountFlowDAO.queryLoanBackByFundData(securityInfo,
        bankid, beginDate, endDate);
    Object obj[] = (Object[])list.get(0);
    loanBackByFundAF.setData_1(new BigDecimal(obj[1].toString()));
    loanBackByFundAF.setList(showList);
    pagination.setNrOfElements(Integer.parseInt(obj[0].toString()));
    return loanBackByFundAF;
  }

}
