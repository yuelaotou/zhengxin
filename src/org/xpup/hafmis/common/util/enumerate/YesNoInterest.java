/*
 * Created on 2005-9-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;


/**
 * Ñ¡Ôñ×´Ì¬
 * @author Àî¾ê
 *2007-6-2
 */
public class YesNoInterest extends AbsBusiProMap{

  private static final long serialVersionUID = 2003445450075369723L;

    static final Integer[] keys = {
        new Integer(BusiConst.YESINTEREST),
        new Integer(BusiConst.NOINTEREST)
        };

    static final String[] values = { "ÊÇ", "·ñ" };
    public YesNoInterest()
    {
      this.putValues(keys,values);
    }
  }


