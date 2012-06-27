package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 单位性质
 * @author 王玲
 *2007-6-29
 */
public class NatureOfUnits extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
     new Integer(BusiConst.NATUREOFUNITS_1),
     new Integer(BusiConst.NATUREOFUNITS_2),
     new Integer(BusiConst.NATUREOFUNITS_3),
     new Integer(BusiConst.NATUREOFUNITS_4),
     new Integer(BusiConst.NATUREOFUNITS_5),
     new Integer(BusiConst.NATUREOFUNITS_6),
     new Integer(BusiConst.NATUREOFUNITS_7),
     new Integer(BusiConst.NATUREOFUNITS_8),
     new Integer(BusiConst.NATUREOFUNITS_9),
     new Integer(BusiConst.NATUREOFUNITS_10),
     new Integer(BusiConst.NATUREOFUNITS_11),
     new Integer(BusiConst.NATUREOFUNITS_12),
     new Integer(BusiConst.NATUREOFUNITS_13),
     new Integer(BusiConst.NATUREOFUNITS_14),
     new Integer(BusiConst.NATUREOFUNITS_15),
     new Integer(BusiConst.NATUREOFUNITS_16),
     new Integer(BusiConst.NATUREOFUNITS_17),
     new Integer(BusiConst.NATUREOFUNITS_18),
     new Integer(BusiConst.NATUREOFUNITS_19)
     };

   static final String[] values = { "中央机关事业","省机关事业","市县机关事业","中央企业","省级全民企业","省级集体企业","市级全民企业","市县级集体企业",
     "外省市单位","部队","全额行政事业", "差额行政事业", "自收自支行政事业", "国有企业", "集体企业","股份制","私有民营企业","三资企业", "其他"};
  public NatureOfUnits()
  {
    this.putValues(keys,values);
  }
}