package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;


/**
 * 账户状态-业务种类为信用卡
 * @author 王菱
 * 2007-6-22
 */
public class AccountStateByCreditCard extends AbsBusiProMap{

  private static final long serialVersionUID = 2003445450075369723L;

    static final Integer[] keys = {
				new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_NORMAL),
				new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_CONGEAL),
        new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_STOPPAYMENT),
        new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_DELACCOUNT),
        new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_BADDEBT)
				};

		static final String[] values = { "正常", "冻结","止付","销户","呆账" };
    public AccountStateByCreditCard()
		{
			this.putValues(keys,values);
		}
	}


