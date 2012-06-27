package org.xpup.hafmis.sysloan.loancallback.relievecontract.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractTaDTO;

public class RelieveContractTaAF extends ActionForm{
  private RelieveContractTaDTO RelieveContractTaDTO=new RelieveContractTaDTO();

  public RelieveContractTaDTO getRelieveContractTaDTO() {
    return RelieveContractTaDTO;
  }

  public void setRelieveContractTaDTO(RelieveContractTaDTO relieveContractTaDTO) {
    RelieveContractTaDTO = relieveContractTaDTO;
  }
}
