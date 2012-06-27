package org.xpup.hafmis.common.util.enumerate;
/**
 * 最高学位
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class HighestEducationDegreeObtained extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_OTHERS),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_HONORARYDOCTOR),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_DOCTOR),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_MASTER),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_BACHELOR),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_UNKNOW)
      };

   static final String[] values = { "其他", "名誉博士", "博士", "硕士","学士","未知"};
  public HighestEducationDegreeObtained()
  {
    this.putValues(keys,values);
  }
}
