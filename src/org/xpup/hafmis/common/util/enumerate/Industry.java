package org.xpup.hafmis.common.util.enumerate;
/**
 * 单位所属行业
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class Industry extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.INDUSTRY_A,
      BusiConst.INDUSTRY_B,
      BusiConst.INDUSTRY_C,
      BusiConst.INDUSTRY_D,
      BusiConst.INDUSTRY_E,
      BusiConst.INDUSTRY_F,
      BusiConst.INDUSTRY_G,
      BusiConst.INDUSTRY_H,
      BusiConst.INDUSTRY_I,
      BusiConst.INDUSTRY_J,
      BusiConst.INDUSTRY_K,
      BusiConst.INDUSTRY_L,
      BusiConst.INDUSTRY_M,
      BusiConst.INDUSTRY_N,
      BusiConst.INDUSTRY_O,
      BusiConst.INDUSTRY_P,
      BusiConst.INDUSTRY_Q,
      BusiConst.INDUSTRY_R,
      BusiConst.INDUSTRY_S,
      BusiConst.INDUSTRY_T,
      BusiConst.INDUSTRY_Z
      };

   static final String[] values = { "农、林、牧、渔业", "采掘业", "制造业","电力、燃气及水的生产和供应业","建筑业","交通运输、仓储和邮政业","信息传输、计算机服务和软件业","批发和零售业","住宿和餐饮业","金融业","房地产业","租赁和商务服务业","科学研究、技术服务业和地质勘查业","水利、环境和公共设施管理业","居民服务和其他服务业","教育","卫生、社会保障和社会福利业","文化、体育和娱乐业","公共管理和社会组织","国际组织","未知" };
  public Industry()
  {
    this.putValues_Str(keys,values);
  }
}
