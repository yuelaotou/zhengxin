package org.xpup.hafmis.common.form;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

public class IdStringAF extends ActionForm {

  private static final long serialVersionUID = -3086018077746708655L;

  private String idString = null;

  public String getIdString() {
    return idString;
  }

  public void setIdString(String idString) {
    this.idString = idString;
  }

  private String itemId = null;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public List getIdList() {
    if (idString == null) {
      idString = "";
    }
    String[] idArr = StringUtils.split(idString, ",");
    return Arrays.asList(idArr);
  }
}
