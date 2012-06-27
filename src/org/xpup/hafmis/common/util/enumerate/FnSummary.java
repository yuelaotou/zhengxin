package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 财务-账簿管理-各种合计描述
 * @author 王菱
 * 2007-10-30
 */
public class FnSummary extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.FNSUMMARY_LASTYEARCLEAR,
      BusiConst.FNSUMMARY_BGNBLAN,
      BusiConst.FNSUMMARY_DAYSUM,
      BusiConst.FNSUMMARY_TERMSUM,
      BusiConst.FNSUMMARY_YEARSUM
      };

   static final String[] values = { "上 年 结 转","期 初 余 额", "本 日 合 计", "本 月 合 计", "本 年 累 计"
                                  };
  public FnSummary()
  {
    this.putValues_Str(keys,values);
  }
}

