package org.xpup.hafmis.common.util.enumerate;
/**
 * 五级分类状态
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class FiveCategoryAssetsClassification extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_NORMAL),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_ATTENTION),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_SECONDARY),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_SHADINESS),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_DAMNIFY),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_UNKOWN)
      };

   static final String[] values = { "正常", "关注","次级","可疑","损失","未知","农户联保","其他" };
  public FiveCategoryAssetsClassification()
  {
    this.putValues(keys,values);
  }
}
