package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopFindDTO;

/**
 * 封装了错帐调整弹出框的查询条件以及列表内容的ActionForm
 * 
 * @author 付云峰
 */
public class AdjustAccountPopFindAF extends ActionForm {

  private AdjustAccountPopFindDTO adjustAccountPopFindDTO = new AdjustAccountPopFindDTO();
  
  private List list;
  
  private Map bizTypeMap = new HashMap();

  public AdjustAccountPopFindDTO getAdjustAccountPopFindDTO() {
    return adjustAccountPopFindDTO;
  }

  public void setAdjustAccountPopFindDTO(
      AdjustAccountPopFindDTO adjustAccountPopFindDTO) {
    this.adjustAccountPopFindDTO = adjustAccountPopFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }
  
}
