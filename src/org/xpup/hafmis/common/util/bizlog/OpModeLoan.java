package org.xpup.hafmis.common.util.bizlog;

public class OpModeLoan extends BusiLogProMap{
  
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
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_FLOOR),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_COLLLOANBACKPARA),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_BANKPALINDROME),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PALINDROMFORMAT),

      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_HOUSEINFO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFOLIMITED),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_LOANAPPLMAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAUDIT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPROVAL),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_LOANCONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_IMPAWNCONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GIVEACC_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GIVEACC_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_ISSUEDNOTICE_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_ISSUEDNOTICE_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_DELCONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANRETURN_MAINTAIN),
      
      new Integer(BusiLogConst.PL_OP_LOANISSUED_DO),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_PRINTRESTORELOAN),
      
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_RECOVERQUIRY),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LIVING_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LIVING_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_MAINTAIN),
      
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_OPERATIONCHECK),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_ACCOUNTING_DO),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_DO),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_LATECARRYOVER),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_DAYCLEANING),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_MONTHLATECARRYOVER),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_CARRYFORWARD),

      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_CONTRACTMAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_SPECIALINFOCHG),

      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_MAINTAIN)
  };

  static final String[] values = { "数据准备-利率维护", "数据准备-银行维护" ,"数据准备-参数维护",
                                   "数据准备-提前还款参数维护","数据准备-开发商维护","数据准备-担保公司维护",
                                   "数据准备-代理机构维护","数据准备-保险公司维护","数据准备-评估机构维护",
                                   "数据准备-公证处维护","数据准备-开发商维护-楼盘","数据准备-公积金还贷参数设置","数据准备-银行回文格式设置","数据准备-银行回文对应格式设置",
                                   
                                   "申请贷款-申请贷款-借款人信息", "申请贷款-申请贷款-辅助借款人信息" ,"申请贷款-申请贷款-购房信息",
                                   "申请贷款-申请贷款-借款人额度信息","申请贷款-申请贷款-申请贷款维护","申请贷款-特殊申请-办理特殊申请",
                                   "申请贷款-特殊申请-特殊申请维护","申请贷款-审核贷款","申请贷款-审批贷款",
                                   "申请贷款-签订合同-借款合同信息","申请贷款-签订合同-抵押合同信息","申请贷款-签订合同-质押合同信息",
                                   "申请贷款-签订合同-保证人信息","申请贷款-签订合同-签订合同维护","申请贷款-收款账号修改-收款账号修改",
                                   "申请贷款-收款账号修改-收款账号维护","申请贷款-划款账号修改-划款账号修改","申请贷款-划款账号修改-划款账号维护",
                                   "申请贷款-下达发放通知书-下达发放通知书","申请贷款-下达发放通知书-发放通知书维护","申请贷款-删除贷款合同","申请贷款-公积金还贷签订合同",
                                   
                                   "发放贷款-办理发放", "发放贷款-发放维护", "发放贷款-打印还款计划表" ,
                                   
                                   "回收贷款-回收咨询", "回收贷款-办理回收", "回收贷款-回收维护",
                                   "回收贷款-银行代扣导出", "回收贷款-银行代扣导入", "回收贷款-呆账核销-办理呆账核销",
                                   "回收贷款-呆账核销-呆账核销维护", "回收贷款-核销收回-办理核销收回", "回收贷款-核销收回-核销收回维护",
                                   "回收贷款-抵押质押解除-办理抵押质押解除", "回收贷款-抵押质押解除-抵押质押解除维护","回收贷款-贷款户注销-贷款户注销办理","回收贷款-贷款户注销-贷款户注销维护",
                                   
                                   "账务处理-业务复核", "账务处理-扎账-扎账", "账务处理-扎账-结算单查询",
                                   "账务处理-错账冲正-办理错账调整", "账务处理-错账冲正-错账调整维护", "账务处理-挂账-办理挂账",
                                   "账务处理-挂账-挂账维护", "账务处理-逾期结转", "账务处理-日结",
                                   "账务处理-月末结转","账务处理-年终结转",
                                   
                                   "合同变更-基本信息变更-借款人信息", "合同变更-基本信息变更-辅助借款人信息", "合同变更-基本信息变更-购房信息",
                                   "合同变更-基本信息变更-基本信息变更维护", "合同变更-担保抵押变更-抵押合同信息", "合同变更-担保抵押变更-质押合同信息",
                                   "合同变更-担保抵押变更-保证人信息", "合同变更-担保抵押变更-担保抵押变更维护", "合同变更-特殊信息变更",
                                   
                                   "特殊业务处理-五级分类修改-业务办理", "特殊业务处理-五级分类修改-业务维护", "特殊业务处理-保证金登记-业务办理",
                                   "特殊业务处理-保证金登记-业务维护","特殊业务处理-保证金结息-业务办理","特殊业务处理-保证金结息-业务维护"
                                   
                                 };
  public OpModeLoan()
  {
    this.putValues(keys,values);
  }

}
