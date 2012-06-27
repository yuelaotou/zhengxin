package org.xpup.hafmis.sysloan.accounthandle.overpay.form;



import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTaDTO;

public class OverPayTaAF extends ActionForm{
  OverPayTaDTO overPayTaDTO=new OverPayTaDTO();
  
  public OverPayTaDTO getOverPayTaDTO() {
    return overPayTaDTO;
  }
  public void setOverPayTaDTO(OverPayTaDTO overPayTaDTO) {
    this.overPayTaDTO = overPayTaDTO;
  }
}
