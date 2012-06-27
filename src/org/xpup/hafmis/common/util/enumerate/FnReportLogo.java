package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 财务-报表公式标识
 * @author 王菱
 * 2007-10-6
 */
public class FnReportLogo extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.REPORTLOGO_BEGBALANCE_DEBIT,
      BusiConst.REPORTLOGO_BEGBALANCE_CREDIT,
      BusiConst.REPORTLOGO_ENDBALANCE_DEBIT,
      BusiConst.REPORTLOGO_ENDBALANCE_CREDIT,
      
      BusiConst.REPORTLOGO_CURFIGURES_DEBIT,
      BusiConst.REPORTLOGO_CURFIGURES_CREDIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_DEBIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_CREDIT,
      
      BusiConst.REPORTLOGO_CURFIGURES_SUMDEBIT,
      BusiConst.REPORTLOGO_CURFIGURES_SUMCREDIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT,
      
      BusiConst.REPORTLOGO_COL,

      BusiConst.REPORTLOGO_REPAIR_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_REPAIR_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_REPAIR_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_RETIREMENT_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_RETIREMENT_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_LOSEABILITY_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_LOSEABILITY_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_ABROAD_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_ABROAD_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_ABROAD_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_OTHERS_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_OTHERS_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_OTHERS_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_FORMULA
      };

   static final String[] values = { "年初余额", "期初余额", "期末余额（不用）", "期末余额", 
                                    "本期发生额（借方）" , "本期发生额（贷方）", "本年累计发生额（借方）","本年累计发生额（贷方）" ,"上年累计发生额（借方）","上年累计发生额（贷方）" ,
                                    "本期累计发生额（借方）（不用）" , "本期累计发生额（贷方）（不用）", "期末累计发生额（借方）","期末累计发生额（贷方）" ,"上年累计发生额（借方）（不用）","上年累计发生额（贷方）（不用）" ,
                                    "行" ,
                                    
                                    "提取金额（购买、建造、翻建、大修住房提取）本期发生额", "提取金额（购买、建造、翻建、大修住房提取）本年累计发生额", "提取金额（购买、建造、翻建、大修住房提取）期末累计发生额",
                                    "提取金额（离退休提取）本期发生额", "提取金额（离退休提取）本年累计发生额", "提取金额（离退休提取）期末累计发生额", 
                                    "提取金额（完全丧失劳动能力，与单位终止劳动关系提取）本期发生额", "提取金额（完全丧失劳动能力，与单位终止劳动关系提取）本年累计发生额", "提取金额（完全丧失劳动能力，与单位终止劳动关系提取）期末累计发生额", 
                                    "提取金额（出国定居提取）本期发生额", "提取金额（出国定居提取）本年累计发生额", "提取金额（出国定居提取）期末累计发生额",
                                    "提取金额（偿还购房贷款本息提取）本期发生额", "提取金额（偿还购房贷款本息提取）本年累计发生额", "提取金额（偿还购房贷款本息提取）期末累计发生额",
                                    "提取金额（支付房租提取）本期发生额", "提取金额（支付房租提取）本年累计发生额", "提取金额（支付房租提取）期末累计发生额",
                                    "提取金额（其他提取）本期发生额", "提取金额（其他提取）本年累计发生额", "提取金额（其他提取）期末累计发生额", 
                                    
                                    "!公式："
                                  };
  public FnReportLogo()
  {
    this.putValues_Str(keys,values);
  }
}

