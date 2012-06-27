package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.dto.AheadReturnParamQueryDTO;

public class AheadReturnParamQueryAF extends ActionForm {
  private AheadReturnParamQueryDTO aheadReturnParamQueryDTO=new AheadReturnParamQueryDTO();

  public AheadReturnParamQueryDTO getAheadReturnParamQueryDTO() {
    return aheadReturnParamQueryDTO;
  }

  public void setAheadReturnParamQueryDTO(
      AheadReturnParamQueryDTO aheadReturnParamQueryDTO) {
    this.aheadReturnParamQueryDTO = aheadReturnParamQueryDTO;
  }
}
