package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.form.LoanContractQueryAF;

public interface ILoancontractqueryBS {

  LoanContractQueryAF showLoancontractqueryList(Pagination pagination, SecurityInfo securityInfo) throws Exception;

  List findFloorlist(String buyhouseorgid)throws Exception;


}
