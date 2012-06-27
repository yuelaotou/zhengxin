package org.xpup.hafmis.common.util.bizlog;

/**
 * 
 * 操作业务系统类型
 * @author 刘洋
 *2007-6-2
 */
public class OpSystemType extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { new Integer(BusiLogConst.OP_SYSTEM_TYPE_MANAGER),new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION),
      new Integer(BusiLogConst.OP_SYSTEM_TYPE_LOAN),new Integer(BusiLogConst.OP_SYSTEM_TYPE_FINANCE)};

  static final String[] values = {"住房公积金系统管理子系统","住房公积金归集子系统", "住房公积金贷款子系统" ,"住房公积金财务子系统"};
  public OpSystemType()
  {
    this.putValues(keys,values);
  }

}
