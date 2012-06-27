package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;

/**
 * 封装了办理错帐调整业务的表单
 * 
 * @author 付云峰
 */
public class AdjustAccountAF extends ActionForm {

  AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = new AdjustAccountTaInfoDTO();
  
  /**
   * 自动挂账的Map
   */
  private Map autoOverPayMap = new HashMap();


  public Map getAutoOverPayMap() {
    return autoOverPayMap;
  }

  public void setAutoOverPayMap(Map autoOverPayMap) {
    this.autoOverPayMap = autoOverPayMap;
  }

  public AdjustAccountTaInfoDTO getAdjustAccountTaInfoDTO() {
    return adjustAccountTaInfoDTO;
  }

  public void setAdjustAccountTaInfoDTO(
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO) {
    this.adjustAccountTaInfoDTO = adjustAccountTaInfoDTO;
  }

}
