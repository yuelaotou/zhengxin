package org.xpup.hafmis.sysfinance.common.biz.credencepop.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;

/**
 * Copy Right Information : 显示凭证弹出框ActionForm Goldsoft Project :
 * CredencePopShowAF
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.11.3
 */
public class CredencePopShowAF extends ActionForm {
  private String check="";
  /** 凭证信息 */
  private CredencePopInfoDTO credencePopInfoDTO = new CredencePopInfoDTO();

  private String credenceId = "";
  /** 列表内容 */
  private List list;

  /** 借款合计 */
  private BigDecimal sumDebit = new BigDecimal(0.00);

  /** 贷款合计 */
  private BigDecimal sumCredit = new BigDecimal(0.00);

  public BigDecimal getSumCredit() {
    return sumCredit;
  }

  public void setSumCredit(BigDecimal sumCredit) {
    this.sumCredit = sumCredit;
  }

  public BigDecimal getSumDebit() {
    return sumDebit;
  }

  public void setSumDebit(BigDecimal sumDebit) {
    this.sumDebit = sumDebit;
  }

  public CredencePopInfoDTO getCredencePopInfoDTO() {
    return credencePopInfoDTO;
  }

  public void setCredencePopInfoDTO(CredencePopInfoDTO credencePopInfoDTO) {
    this.credencePopInfoDTO = credencePopInfoDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
  public String getCredenceId() {
    return credenceId;
  }

  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }
  public String getCheck() {
    return check;
  }

  public void setCheck(String check) {
    this.check = check;
  }
}
