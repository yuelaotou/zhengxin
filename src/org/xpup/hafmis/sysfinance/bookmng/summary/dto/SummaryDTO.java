package org.xpup.hafmis.sysfinance.bookmng.summary.dto;

import java.io.Serializable;

/**
 * Copy Right Information   : 常用摘要
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-25-2007
 */
public class SummaryDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String paraId = "";
  private String bookId = "";
  private String paramExplain = "";
  private String paramExplainPY = "";
  
  public String getParamExplainPY() {
    return paramExplainPY;
  }
  public void setParamExplainPY(String paramExplainPY) {
    this.paramExplainPY = paramExplainPY;
  }
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
}
