package org.xpup.hafmis.sysloan.loanaccord.loanaccord.form;

import java.math.BigDecimal;
import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
public class LoanaccordNewAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = -2986928051975297775L;
  private LoanaccordDTO loanaccordDTO=new LoanaccordDTO();
  public void reset(ActionMapping mapping, ServletRequest request) {
    loanaccordDTO=new LoanaccordDTO();
  }
  public LoanaccordDTO getLoanaccordDTO() {
    return loanaccordDTO;
  }
  public void setLoanaccordDTO(LoanaccordDTO loanaccordDTO) {
    this.loanaccordDTO = loanaccordDTO;
  }
}
