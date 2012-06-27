package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTcFindDTO;

/**
 * Copy Right Information : 损益结转页的ActionForm Goldsoft Project :
 * CredenceFillinTcAF
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.25
 */
public class CredenceFillinTcAF extends ActionForm {

  /** 查询条件DTO */
  private CredenceFillinTcFindDTO credenceFillinTcFindDTO = new CredenceFillinTcFindDTO();

  /** 列表内容 */
  private List list;

  /** 办事处Map */
  private Map officeMap = new HashMap();

  /** 借方金额合计 */
  private BigDecimal sumCreditMoney = new BigDecimal(0.00);

  /** 贷方金额合计 */
  private BigDecimal sumDebitMoney = new BigDecimal(0.00);

  public CredenceFillinTcFindDTO getCredenceFillinTcFindDTO() {
    return credenceFillinTcFindDTO;
  }

  public void setCredenceFillinTcFindDTO(
      CredenceFillinTcFindDTO credenceFillinTcFindDTO) {
    this.credenceFillinTcFindDTO = credenceFillinTcFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getOfficeMap() {
    return officeMap;
  }

  public void setOfficeMap(Map officeMap) {
    this.officeMap = officeMap;
  }

  public BigDecimal getSumCreditMoney() {
    return sumCreditMoney;
  }

  public void setSumCreditMoney(BigDecimal sumCreditMoney) {
    this.sumCreditMoney = sumCreditMoney;
  }

  public BigDecimal getSumDebitMoney() {
    return sumDebitMoney;
  }

  public void setSumDebitMoney(BigDecimal sumDebitMoney) {
    this.sumDebitMoney = sumDebitMoney;
  }
}
