package org.xpup.hafmis.common.util.bizlog;

/**
 * 操作模块-个贷-数据准备
 * @author 王菱
 * 2007-9-13
 */
public class PlOpDataPreparation extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_RATE),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_BANK),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PARAMETERS),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PREPAYMENTPARAM),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_DEVELOPERS),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_GUARANTEECORP),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_AGENCIES),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_INSURANCECOMP),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_ASSESSMENTAGEN),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_COLLLOANBACKPARA),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_BANKPALINDROME),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PALINDROMFORMAT)
  };

  static final String[] values = { "数据准备-利率维护", "数据准备-银行维护" ,"数据准备-参数维护",
                                   "数据准备-提前还款参数维护","数据准备-开发商维护","数据准备-担保公司维护",
                                   "数据准备-代理机构维护","数据准备-保险公司维护","数据准备-评估机构维护",
                                   "数据准备-公证处维护","数据准备-开发商维护-楼盘","数据准备-公积金还贷参数设置","数据准备-银行回文格式设置","数据准备-银行回文对应格式设置"};
  public PlOpDataPreparation()
  {
    this.putValues(keys,values);
  }

}
