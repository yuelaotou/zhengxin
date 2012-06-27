package org.xpup.hafmis.sysloan.dataready.param.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.param.dto.AheadReturnParamDTO;

public class AheadReturnParamAF extends ActionForm{
  private AheadReturnParamDTO aheadReturnParamDTO=new AheadReturnParamDTO();

  public AheadReturnParamDTO getAheadReturnParamDTO() {
    return aheadReturnParamDTO;
  }

  public void setAheadReturnParamDTO(AheadReturnParamDTO aheadReturnParamDTO) {
    this.aheadReturnParamDTO = aheadReturnParamDTO;
  }
}
