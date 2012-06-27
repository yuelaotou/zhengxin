package org.xpup.hafmis.common.util.enumerate;
/**
 * 账户状态-业务种类为贷款
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class AccountStateByLoan extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.ACCOUNTSTATEBYLOAN_NORMAL),
      new Integer(BusiConst.ACCOUNTSTATEBYLOAN_OVERDUE),
      new Integer(BusiConst.ACCOUNTSTATEBYLOAN_SETTLE),
      new Integer(BusiConst.ACCOUNTSTATEBYLOAN_BADDEBT)};

   static final String[] values = { "正常", "逾期","结清","呆账" };
  public AccountStateByLoan()
  {
    this.putValues(keys,values);
  }
}
