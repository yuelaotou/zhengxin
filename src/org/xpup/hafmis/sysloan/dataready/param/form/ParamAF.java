package org.xpup.hafmis.sysloan.dataready.param.form;



import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.param.dto.ParamDTO;

public class ParamAF extends ActionForm{
  private ParamDTO paramDTO=new ParamDTO();

  public ParamDTO getParamDTO() {
    return paramDTO;
  }

  public void setParamDTO(ParamDTO paramDTO) {
    this.paramDTO = paramDTO;
  }
}
