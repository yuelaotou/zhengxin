package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
/**
 * 和出纳扎账、银行存款日记账、账簿管理中的现金日记账、银行存款日记账公用
 * @author guojingping
 *
 */
public class CashDayClearTcAF extends ActionForm {
  private List list=new ArrayList();
  private CashDayClearTcFindDTO cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
  public CashDayClearTcFindDTO getCashDayClearTcFindDTO() {
    return cashDayClearTcFindDTO;
  }
  public void setCashDayClearTcFindDTO(CashDayClearTcFindDTO cashDayClearTcFindDTO) {
    this.cashDayClearTcFindDTO = cashDayClearTcFindDTO;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
}
