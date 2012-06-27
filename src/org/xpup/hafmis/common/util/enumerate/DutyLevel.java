package org.xpup.hafmis.common.util.enumerate;
/**
 * 职称
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class DutyLevel extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.DUTYLEVEL_NOT),
      new Integer(BusiConst.DUTYLEVEL_SENIOR),
      new Integer(BusiConst.DUTYLEVEL_INTERMEDIATE),
      new Integer(BusiConst.DUTYLEVEL_GENERAL),
      new Integer(BusiConst.DUTYLEVEL_UNKNOW)
      };

   static final String[] values = { "无", "高级", "中级", "初级","未知" };
  public DutyLevel()
  {
    this.putValues(keys,values);
  }
}
