package org.xpup.hafmis.syscollection.accountmng.officeparam.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.accountmng.officeparam.dto.OfficeParaDTO;

public class OfficeParaAF extends ActionForm {
  private OfficeParaDTO officeParaDTO = new OfficeParaDTO();

  public OfficeParaDTO getOfficeParaDTO() {
    return officeParaDTO;
  }

  public void setOfficeParaDTO(OfficeParaDTO officeParaDTO) {
    this.officeParaDTO = officeParaDTO;
  }
}
