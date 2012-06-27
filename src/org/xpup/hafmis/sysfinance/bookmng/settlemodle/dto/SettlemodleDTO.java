package org.xpup.hafmis.sysfinance.bookmng.settlemodle.dto;

import java.io.Serializable;

/**
 * Copy Right Information   : 结算方式
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-24-2007
 */
public class SettlemodleDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String paraId = "";
  private String bookId = "";
  private String paramExplain = "";
  
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
