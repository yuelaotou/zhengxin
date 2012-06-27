
package org.xpup.hafmis.common.util.bizlog;

import java.util.TreeMap;
/**
 * @author ¡ı—Û
 *2007-6-2
 */
abstract class BusiLogProMap extends TreeMap {

  private static final long serialVersionUID = -498746610184746795L;

  protected void putValues(Integer[] keys,String[] values)
  {
    for (int i = 0;i<keys.length;i++)
    {
      this.put(keys[i],values[i]);
    }
  }

}
