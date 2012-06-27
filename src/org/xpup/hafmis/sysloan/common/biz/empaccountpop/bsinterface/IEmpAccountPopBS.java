/**
 * Copy Right Information   : Goldsoft 
 * Project                  : IEmpAccountPopBS
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-11-02
 * 修改日期                   :2007-11-13通过借款人姓名和证件号码查询职工明细账；增加查询条件：职工编号、单位编号
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.form.EmpAccountPopAF;

public interface IEmpAccountPopBS {

  // 根据条件查询职工明细账列表
  public EmpAccountPopAF queryEmpAccountListByCriterions(String borrowerName,
      String cardNum, Pagination pagination)
      throws Exception, BusinessException;
  public EmpAccountPopAF queryEmpAccountListByCriterions_yg(Pagination pagination)
  throws Exception, BusinessException;
}
