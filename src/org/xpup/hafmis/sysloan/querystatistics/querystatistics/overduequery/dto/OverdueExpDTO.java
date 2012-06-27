package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class OverdueExpDTO implements ExpDto {
  private String mobile = "";

  private String content = "";

  public String getInfo(String info) {
    if (info.equals("mobile")) {
      return this.mobile.toString();
    } else if (info.equals("content")) {
      return this.content.toString();
    }
    return null;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

}
