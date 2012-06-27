package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbFindDTO;

/**
 * 和银行存款日记账公用
 * @author guojingping
 *
 */
public class CashDayClearTbAF extends ActionForm {
  private List list=new ArrayList();
  private CashDayClearTbFindDTO cashDayClearTbFindDTO=new CashDayClearTbFindDTO();
  public CashDayClearTbFindDTO getCashDayClearTbFindDTO() {
    return cashDayClearTbFindDTO;
  }
  public void setCashDayClearTbFindDTO(CashDayClearTbFindDTO cashDayClearTbFindDTO) {
    this.cashDayClearTbFindDTO = cashDayClearTbFindDTO;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
}
