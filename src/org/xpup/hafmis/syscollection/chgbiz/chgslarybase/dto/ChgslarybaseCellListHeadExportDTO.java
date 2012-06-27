/*单位编号、单位名称、调整年月、*/
 //吴洪涛2008616
package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ChgslarybaseCellListHeadExportDTO implements ExpDto {

  private static final long serialVersionUID = 0L;

  private String orgId;

  private String orgName;

  private String chgMonth;

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getInfo(String s) {
    if (s.equals("orgId"))
      return orgId;
    if (s.equals("orgName"))
      return orgName;
    if (s.equals("chgMonth"))
      return chgMonth;

    else
      return null;
  }

}
