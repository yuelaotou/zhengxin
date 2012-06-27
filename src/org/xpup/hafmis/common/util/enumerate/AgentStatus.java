package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 代扣状态
 * @author 付云峰
 *
 */
public class AgentStatus extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.AGENTSTATUS_NO,
      BusiConst.AGENTSTATUS_YES};

   static final String[] values = { "未使用", "已使用"};
  public AgentStatus()
  {
    this.putValues_Str(keys,values);
  }
}
