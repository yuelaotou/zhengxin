
package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;


/**
 * »éÒö×´¿ö
 * @author ÁõÑó
 *2007-6-2
 */
public class MarryStatus extends AbsBusiProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
      new Integer(BusiConst.MARRY_STATUS_LIVE),
			new Integer(BusiConst.MARRY_STATUS_DEAD)
      };

	static final String[] values = { "Î´»é","ÒÑ»é" };
  public MarryStatus()
	{
		this.putValues(keys,values);
	}
}
