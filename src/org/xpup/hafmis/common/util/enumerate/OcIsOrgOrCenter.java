package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class OcIsOrgOrCenter extends AbsBusiProMap{
  
  /**
   * 
   */
  private static final long serialVersionUID = -3278466823623047065L;
  static final Integer[] keys = {
    new Integer(BusiConst.ORG_OR_CENTER_INFO_ORG),
    new Integer(BusiConst.ORG_OR_CENTER_INFO_CENTER)
    };
static final String[] values = { "单位版", "中心版" };
public OcIsOrgOrCenter()
{
  this.putValues(keys,values);
}
}
