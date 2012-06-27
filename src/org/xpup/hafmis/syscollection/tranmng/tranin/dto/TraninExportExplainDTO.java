package org.xpup.hafmis.syscollection.tranmng.tranin.dto;
import org.xpup.common.util.exp.domn.interfaces.ExpDto;
/**
 * Copy Right Information : 转入导出说明DTO Goldsoft Project :
 * TraninExportExplainDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class TraninExportExplainDTO implements ExpDto{
  private String explain = "";
  public String getInfo(String s) {
    if (s.equals("explain"))
      return explain;
    else
      return null;
  }
  public String getExplain() {
    return explain;
  }
  public void setExplain(String explain) {
    this.explain = explain;
  }
}
