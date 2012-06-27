package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class BusinessLogSearch extends AbsBusiProMap {
  /**
   * 卢钢　公积金业务日志查询
   */
  private static final long serialVersionUID = 1L;

  static final String[] keys = { 
      BusiConst.BUSINESSLOGSEARCH_PAYMENT,
      BusiConst.BUSINESSLOGSEARCH_ORGADDPAY,
      BusiConst.BUSINESSLOGSEARCH_ORGOVERPAY,    
      BusiConst.BUSINESSLOGSEARCH_PICKUP,
      BusiConst.BUSINESSLOGSEARCH_TRANOUT,
      BusiConst.BUSINESSLOGSEARCH_TRANIN,    
      BusiConst.BUSINESSLOGSEARCH_CHGACCOUNT,     
      BusiConst.BUSINESSLOGSEARCH_OVERDUEINTEREST,    
      BusiConst.BUSINESSLOGSEARCH_SPECIALPICKUP, 
      BusiConst.BUSINESSLOGSEARCH_ADJUSTINTEREST,    
      BusiConst.BUSINESSLOGSEARCH_EMPADDPAY,     
      BusiConst.BUSINESSLOGSEARCH_ADJUSTPAYMENTRATE,
      BusiConst.BUSINESSLOGSEARCH_ADJUSTSALARYBASE,
      BusiConst.BUSINESSLOGSEARCH_ADJUSTPAYMENT,
      BusiConst.BUSINESSLOGSEARCH_CHGPERSON,
      BusiConst.BUSINESSLOGSEARCH_ORGOPENACCOUNT,
      BusiConst.BUSINESSLOGSEARCH_CHGORG
  
  };

  static final String[] values = { "汇缴", "单位补缴", "挂账","提取","转出","转入","调账","结息","特殊提取","利率调整","个人补缴","汇缴比例调整","工资基数调整","缴额调整","人员变更","单位开户","单位变更"};

  public BusinessLogSearch() {
    this.putValues_Str(keys, values);
  }
}
