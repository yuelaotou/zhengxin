package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.form;



import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowDTO;

public class DepositCheckAccWindowAF extends ActionForm {
  private DepositCheckAccWindowDTO depositCheckAccWindowDTO=new DepositCheckAccWindowDTO();

  public DepositCheckAccWindowDTO getDepositCheckAccWindowDTO() {
    return depositCheckAccWindowDTO;
  }

  public void setDepositCheckAccWindowDTO(
      DepositCheckAccWindowDTO depositCheckAccWindowDTO) {
    this.depositCheckAccWindowDTO = depositCheckAccWindowDTO;
  }
}
