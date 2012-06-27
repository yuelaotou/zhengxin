package org.xpup.hafmis.syscollection.paymng.agent.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;
/**
 * Copy Right Information : 代扣模版导入表头的DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentExportTitleDTO implements ExpDto{

  public String getInfo(String info) {
    if (info.equals("agentYearMonth")) {
      return this.agentYearMonth;

    }
    if (info.equals("noteNum")) {
      return this.noteNum;

    }
    if (info.equals("payMode")) {
      return this.payMode;

    }
    if (info.equals("agentType")) {
      return this.agentType;

    }
    return null;
  }
  /** 代扣年月 */
  private String agentYearMonth = "";

  /** 票据编号 */
  private String noteNum = "";

  /** 缴存方式 */
  private String payMode = "";

  /** 代扣类型 */
  private String agentType = "";

  public String getAgentType() {
    return agentType;
  }

  public void setAgentType(String agentType) {
    this.agentType = agentType;
  }

  public String getAgentYearMonth() {
    return agentYearMonth;
  }

  public void setAgentYearMonth(String agentYearMonth) {
    this.agentYearMonth = agentYearMonth;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
}
