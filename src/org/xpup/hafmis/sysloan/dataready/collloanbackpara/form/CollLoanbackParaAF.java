package org.xpup.hafmis.sysloan.dataready.collloanbackpara.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.dto.CollLoanbackParaDTO;

public class CollLoanbackParaAF extends ActionForm {
  private CollLoanbackParaDTO collLoanbackParaDTO=new CollLoanbackParaDTO();

  public CollLoanbackParaDTO getCollLoanbackParaDTO() {
    return collLoanbackParaDTO;
  }

  public void setCollLoanbackParaDTO(CollLoanbackParaDTO collLoanbackParaDTO) {
    this.collLoanbackParaDTO = collLoanbackParaDTO;
  }
}
