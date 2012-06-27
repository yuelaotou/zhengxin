package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

/**
 * Copy Right Information : 封装了损益结转页查询条件的DTO Goldsoft Project :
 * CredenceFillinTcFindDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.25
 */
public class CredenceFillinTcFindDTO {
  
  /** 科目代码 */
  private String subjectcode = "";

  /** 科目名称 */
  private String subjectName = "";

  /** start金额 */
  private String startMoney = "";

  /** end金额 */
  private String endMoney = "";

  /** 办事处 */
  private String office = "";

  /** 凭证日期开始 */
  private String credenceDateStart = "";
  
  /** 凭证日期结束 */
  private String credenceDateEnd = "";
  
  public String getEndMoney() {
    return endMoney;
  }

  public void setEndMoney(String endMoney) {
    this.endMoney = endMoney;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getStartMoney() {
    return startMoney;
  }

  public void setStartMoney(String startMoney) {
    this.startMoney = startMoney;
  }

  public String getSubjectcode() {
    return subjectcode;
  }

  public void setSubjectcode(String subjectcode) {
    this.subjectcode = subjectcode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getCredenceDateEnd() {
    return credenceDateEnd;
  }

  public void setCredenceDateEnd(String credenceDateEnd) {
    this.credenceDateEnd = credenceDateEnd;
  }

  public String getCredenceDateStart() {
    return credenceDateStart;
  }

  public void setCredenceDateStart(String credenceDateStart) {
    this.credenceDateStart = credenceDateStart;
  }
}
