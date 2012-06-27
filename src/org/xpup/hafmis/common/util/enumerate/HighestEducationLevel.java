package org.xpup.hafmis.common.util.enumerate;
/**
 * 最高学历
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class HighestEducationLevel extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_GRADUATE),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_UNDERGRADUATE),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_SPECIALIST),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_SECONDARY),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_TECHNICAL),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_HIGHSCHOOL),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_JUNIOR),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_PRIMARY),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_ILLITERACY),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_UNKNOW)
      };

   static final String[] values = { "研究生", "大学本科", "大学专科和专科学校", "中等专业学校或中等技术学校","技术学校","高中","初中","小学","文盲或半文盲","未知"};
  public HighestEducationLevel()
  {
    this.putValues(keys,values);
  }
}
