package org.xpup.hafmis.common.util.enumerate;
/**
 * 是否存在单位版
 * @author 王菱
 */
import org.xpup.hafmis.common.util.BusiConst;

public class IshaveOrgversion extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.IS_NOHAVE,
      BusiConst.IS_HAVE};

   static final String[] values = {"不存在", "存在"};
  public IshaveOrgversion()
  {
    this.putValues_Str(keys,values);
  }
}
