package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ¸ö´û-Ç©¶©ºÏÍ¬×´Ì¬
 * @author jj
 * 2007-10-22
 */
public class PLContractWrite extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLCONTRACTWRITE_NO),
                                  new Integer(BusiConst.PLCONTRACTWRITE_YES)
                                };

  static final String[] values = { "Î´Ç©¶©","ÒÑÇ©¶©"};

  public PLContractWrite() {
    this.putValues(keys, values);
  }
}
