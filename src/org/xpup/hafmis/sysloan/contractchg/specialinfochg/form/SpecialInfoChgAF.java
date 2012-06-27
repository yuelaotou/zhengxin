package org.xpup.hafmis.sysloan.contractchg.specialinfochg.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto.SpecialInfoChgDTO;

public class SpecialInfoChgAF extends ActionForm{
  private SpecialInfoChgDTO specialInfoChgDTO=new SpecialInfoChgDTO();

  public SpecialInfoChgDTO getSpecialInfoChgDTO() {
    return specialInfoChgDTO;
  }

  public void setSpecialInfoChgDTO(SpecialInfoChgDTO specialInfoChgDTO) {
    this.specialInfoChgDTO = specialInfoChgDTO;
  }
}
