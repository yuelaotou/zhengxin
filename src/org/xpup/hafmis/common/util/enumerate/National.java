package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 民族
 * @author 王菱
 * 2007-9-13
 */
public class National extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.NATIONAL_HAN),
                                  new Integer(BusiConst.NATIONAL_MAN) };

  static final String[] values = { "汉族","满族" };

  public National() {
    this.putValues(keys, values);
  }
}
