package org.xpup.hafmis.common.util.enumerate;
/**
 * 职务
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class Duty extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.DUTY_SENIORLEADER),
      new Integer(BusiConst.DUTY_INTERMEDIATELEADER),
      new Integer(BusiConst.DUTY_GENERALSTAFF),
      new Integer(BusiConst.DUTY_OTHERS),
      new Integer(BusiConst.DUTY_UNKNOW)
      };

   static final String[] values = { "高级领导", "中级领导", "一般员工", "其他","未知" };
  public Duty()
  {
    this.putValues(keys,values);
  }
}
