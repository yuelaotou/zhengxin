package org.xpup.hafmis.common.util.enumerate;
/**
 * 证件类型
 * @author 王菱
 *2007-6-21
 */
import org.xpup.hafmis.common.util.BusiConst;

public class DocumentsState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.DOCUMENTSSTATE_IDCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_HOUSEHOLDREGISTER),
      new Integer(BusiConst.DOCUMENTSSTATE_PASSPORTCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_OFFICERCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_SOLDIERCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_HKANDMACAO),
      new Integer(BusiConst.DOCUMENTSSTATE_TAIWAN),
      new Integer(BusiConst.DOCUMENTSSTATE_TEMPCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_FOREIGNERRESIDENTIALCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_POLICECARD)
//      new Integer(BusiConst.DOCUMENTSSTATE_OTHERSCARD)
      };

   static final String[] values = { "身份证","户口簿","护照", "军官证","士兵证","港澳居民来往内地通行证","台湾同胞来往内地通行证","临时身份证","外国人居留证","警官证",
//     "其他证件"
     };
  public DocumentsState()
  {
    this.putValues(keys,values);
  }
}
