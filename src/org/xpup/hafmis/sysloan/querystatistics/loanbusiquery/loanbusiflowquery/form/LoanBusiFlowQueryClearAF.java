/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanBusiFlowQueryClearAF
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-18
 **/
package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;

public class LoanBusiFlowQueryClearAF extends ActionForm {

  private static final long serialVersionUID = -4206364771884797623L;

  private LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO = new LoanBusiFlowQueryClearDTO();

  private List list = null;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public LoanBusiFlowQueryClearDTO getLoanBusiFlowQueryClearDTO() {
    return loanBusiFlowQueryClearDTO;
  }

  public void setLoanBusiFlowQueryClearDTO(
      LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO) {
    this.loanBusiFlowQueryClearDTO = loanBusiFlowQueryClearDTO;
  }
}
