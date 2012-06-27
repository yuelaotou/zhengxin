package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-个贷-合同变更
 * @author 王菱
 * 2007-9-13
 */
public class PlOpContractChg extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_CONTRACTMAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_SPECIALINFOCHG)
      };

  static final String[] values = { "合同变更-基本信息变更-借款人信息", "合同变更-基本信息变更-辅助借款人信息", "合同变更-基本信息变更-购房信息",
                                   "合同变更-基本信息变更-基本信息变更维护", "合同变更-担保抵押变更-抵押合同信息", "合同变更-担保抵押变更-质押合同信息",
                                   "合同变更-担保抵押变更-保证人信息", "合同变更-担保抵押变更-担保抵押变更维护", "合同变更-特殊信息变更"
                                 };

  public PlOpContractChg() {
    this.putValues(keys, values);
  }

}