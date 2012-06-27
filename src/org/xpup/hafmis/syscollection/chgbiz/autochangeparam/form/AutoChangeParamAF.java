package org.xpup.hafmis.syscollection.chgbiz.autochangeparam.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.dto.AutoChangeParamDTO;

/**
 * Copy Right Information : 自动变更参数设置的ActionForm Goldsoft Project :
 * AutoChangeParamAF
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.17
 */
public class AutoChangeParamAF extends ActionForm {
  
  /**
   * 参数信息的DTO
   */
  private AutoChangeParamDTO autoChangeParamDTO = new AutoChangeParamDTO();

  public AutoChangeParamDTO getAutoChangeParamDTO() {
    return autoChangeParamDTO;
  }

  public void setAutoChangeParamDTO(AutoChangeParamDTO autoChangeParamDTO) {
    this.autoChangeParamDTO = autoChangeParamDTO;
  }
}
