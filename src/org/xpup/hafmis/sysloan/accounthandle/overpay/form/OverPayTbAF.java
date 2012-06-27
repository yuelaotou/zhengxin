package org.xpup.hafmis.sysloan.accounthandle.overpay.form;


import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTbFindDTO;

public class OverPayTbAF extends ActionForm{
  private OverPayTbFindDTO overPayTbFindDTO=new OverPayTbFindDTO();
  private BigDecimal occurMoneySum=new BigDecimal(0.00);
  private List list=null;
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public OverPayTbFindDTO getOverPayTbFindDTO() {
    return overPayTbFindDTO;
  }
  public void setOverPayTbFindDTO(OverPayTbFindDTO overPayTbFindDTO) {
    this.overPayTbFindDTO = overPayTbFindDTO;
  }
  public BigDecimal getOccurMoneySum() {
    return occurMoneySum;
  }
  public void setOccurMoneySum(BigDecimal occurMoneySum) {
    this.occurMoneySum = occurMoneySum;
  }
}
