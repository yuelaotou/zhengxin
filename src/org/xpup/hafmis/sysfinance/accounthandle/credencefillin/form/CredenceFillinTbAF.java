package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;

/**
 * Copy Right Information : 自动转帐页的ActionForm Goldsoft Project :
 * CredenceFillinTbAF
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.17
 */
public class CredenceFillinTbAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /** 查询条件 */
  private CredenceFillinTbFindDTO credenceFillinTbFindDTO = new CredenceFillinTbFindDTO();

  /** 列表内容 */
  private List list;

  private BigDecimal moneyall = new BigDecimal(0.00);

  /** 业务状态Map */
  private Map bizStMap = new HashMap();

  /** 业务类型Map */
  private Map bizTypeMap = new HashMap();

  public Map getBizStMap() {
    return bizStMap;
  }

  public void setBizStMap(Map bizStMap) {
    this.bizStMap = bizStMap;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }

  public CredenceFillinTbFindDTO getCredenceFillinTbFindDTO() {
    return credenceFillinTbFindDTO;
  }

  public void setCredenceFillinTbFindDTO(
      CredenceFillinTbFindDTO credenceFillinTbFindDTO) {
    this.credenceFillinTbFindDTO = credenceFillinTbFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public BigDecimal getMoneyall() {
    return moneyall;
  }

  public void setMoneyall(BigDecimal moneyall) {
    this.moneyall = moneyall;
  }

}
