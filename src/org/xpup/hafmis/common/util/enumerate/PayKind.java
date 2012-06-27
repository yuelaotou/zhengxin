package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PayKind  extends AbsBusiProMap {
 
  static final Integer[] keys = { 
    new Integer(BusiConst.PAY_KIND_PAYMENT),
    new Integer(BusiConst.PAY_KIND_ADDPAY),
    new Integer(BusiConst.PAY_KIND_ADVANCEPAY)
    };

 static final String[] values = { "»ã½É", "²¹½É","Ô¤½É" };
public PayKind()
{
  this.putValues(keys,values);
}

}
