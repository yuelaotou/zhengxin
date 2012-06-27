package org.xpup.hafmis.sysfinance.bookmng.credencechar.dto;

import java.io.Serializable;

/**
 * Copy Right Information   : 凭证字
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-22-2007
 */
public class CredencecharDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String paraId = "";
  private String bookId = "";
  private String paramNum = "";
  private String paramValue = "";
  private String paramExplain = "";
  private String paramExplainExplain = "";
  
  public String getBookId() {
    return bookId;
  }
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  public String getParaId() {
    return paraId;
  }
  public void setParaId(String paraId) {
    this.paraId = paraId;
  }
  public String getParamExplain() {
    return paramExplain;
  }
  public void setParamExplain(String paramExplain) {
    this.paramExplain = paramExplain;
  }
  public String getParamExplainExplain() {
    return paramExplainExplain;
  }
  public void setParamExplainExplain(String paramExplainExplain) {
    this.paramExplainExplain = paramExplainExplain;
  }
  public String getParamNum() {
    return paramNum;
  }
  public void setParamNum(String paramNum) {
    this.paramNum = paramNum;
  }
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
}
