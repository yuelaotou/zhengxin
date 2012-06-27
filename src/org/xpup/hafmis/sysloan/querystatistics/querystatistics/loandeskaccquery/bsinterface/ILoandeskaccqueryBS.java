package org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.form.LoanDeskaccQueryTaAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.form.LoanDeskaccQueryTbAF;

public interface ILoandeskaccqueryBS {

  public LoanDeskaccQueryTaAF showLoandeskaccList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  
  public List showLoandeskaccAllList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  public LoanDeskaccQueryTbAF showIndividualFlow(String contractIdHl,
      Pagination pagination, SecurityInfo securityInfo) throws Exception;

}
