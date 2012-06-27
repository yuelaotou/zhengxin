package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 辅助借款人关系 1、配偶，2、父母，3、子女 杨光 20090404 在家
 */
public class Relation extends AbsBusiProMap {
  private static final long serialVersionUID = 1L;

  static final Integer[] keys = { new Integer(BusiConst.RELATION_CONSORT),
      new Integer(BusiConst.RELATION_PARENT),
      new Integer(BusiConst.RELATION_CHILD) };

  static final String[] values = { "配偶", "父母", "子女" };

  public Relation() {
    this.putValues(keys, values);
  }

}
