package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-个贷-申请贷款
 * @author 王菱
 * 2007-9-13
 */
public class PlOpLoanAppl extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
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
      new Integer(BusiLogConst.PL_OP_LOANRETURN_MAINTAIN)
  };

  static final String[] values = { "申请贷款-申请贷款-借款人信息", "申请贷款-申请贷款-辅助借款人信息" ,"申请贷款-申请贷款-购房信息",
                                   "申请贷款-申请贷款-借款人额度信息","申请贷款-申请贷款-申请贷款维护","申请贷款-特殊申请-办理特殊申请",
                                   "申请贷款-特殊申请-特殊申请维护","申请贷款-审核贷款","申请贷款-审批贷款",
                                   "申请贷款-签订合同-借款合同信息","申请贷款-签订合同-抵押合同信息","申请贷款-签订合同-质押合同信息",
                                   "申请贷款-签订合同-保证人信息","申请贷款-签订合同-签订合同维护","申请贷款-收款账号修改-收款账号修改",
                                   "申请贷款-收款账号修改-收款账号维护","申请贷款-划款账号修改-划款账号修改","申请贷款-划款账号修改-划款账号维护",
                                   "申请贷款-下达发放通知书-下达发放通知书","申请贷款-下达发放通知书-发放通知书维护","申请贷款-删除贷款合同","申请贷款-公积金还贷签订合同"};
  public PlOpLoanAppl()
  {
    this.putValues(keys,values);
  }

}
