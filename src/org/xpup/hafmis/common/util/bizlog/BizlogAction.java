package org.xpup.hafmis.common.util.bizlog;

/**
 * 业务日志表的操作行为
 * @author 王菱
 *2007-6-21 
 */
public class BizlogAction extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
      new Integer(BusiLogConst.BIZLOG_ACTION_ADD),
      new Integer(BusiLogConst.BIZLOG_ACTION_MODIFY),
      new Integer(BusiLogConst.BIZLOG_ACTION_DELETE),
      new Integer(BusiLogConst.BIZLOG_ACTION_QUERY),
      new Integer(BusiLogConst.BIZLOG_ACTION_EXP),
      new Integer(BusiLogConst.BIZBLOG_ACTION_IMP),
      new Integer(BusiLogConst.BIZLOG_ACTION_OPENING),
      new Integer(BusiLogConst.BIZLOG_ACTION_REVOCATION),
      new Integer(BusiLogConst.BIZLOG_ACTION_DELETEALL),
      new Integer(BusiLogConst.BIZLOG_ACTION_CHECKS),
      new Integer(BusiLogConst.BIZLOG_ACTION_CONFIRM),
      new Integer(BusiLogConst.BIZLOG_ACTION_INTEREST),
      new Integer(BusiLogConst.BIZLOG_ACTION_ACCOUNTIN),
      new Integer(BusiLogConst.BIZLOG_ACTION_ENDCHG),
      new Integer(BusiLogConst.BIZLOG_ACTION_AUDITINGPASS),
      new Integer(BusiLogConst.BIZLOG_ACTION_AUDITING),
      new Integer(BusiLogConst.BIZLOG_ACTION_AUDITINGQUASH),
      new Integer(BusiLogConst.BIZLOG_ACTION_PPROVALPASS),
      new Integer(BusiLogConst.BIZLOG_ACTION_PPROVAL),
      new Integer(BusiLogConst.BIZLOG_ACTION_PPROVALQUASH),
      new Integer(BusiLogConst.BIZLOG_ACTION_CARRYFORWARD)
  };

  static final String[] values = { "添加", "修改" ,"删除","查询","导出","导入","启用","撤销","全部删除","复核","确认","结息","扎账","完成调整","审核通过","审核不通过","撤消审核","审批通过","审批不通过","撤消审批","年终结转"};
  public BizlogAction()
  {
    this.putValues(keys,values);
  }

}
