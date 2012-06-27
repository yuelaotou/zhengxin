package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.dto.ParamQueryDTO;

public class ParamQueryAF extends ActionForm {
  private ParamQueryDTO paramQueryDTO=new ParamQueryDTO();

  public ParamQueryDTO getParamQueryDTO() {
    return paramQueryDTO;
  }

  public void setParamQueryDTO(ParamQueryDTO paramQueryDTO) {
    this.paramQueryDTO = paramQueryDTO;
  }
}
