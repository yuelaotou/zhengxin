package org.xpup.hafmis.sysloan.dataready.bank.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankAFDTO;

public interface IBankBS {
  /**
   * name 杨蒙
   * 数据准备:银行维护--显示列表
   * return list
   */
  public List findBankList(Pagination pagination) throws Exception, BusinessException;
  /**
   * name 杨蒙
   * 判断银行信息表 是否已经存在 办事处 银行ID相同的记录 (**张列改**)
   * return boolean
   */
  public boolean isCheckBank(BankAFDTO bankAFDTO)throws Exception; 
  /**
   * name 杨蒙
   * 插入新数据 银行信息表 (**张列改**)
   */
  public void insertBank(BankAFDTO bankAFDTO,SecurityInfo securityInfo)throws Exception;
  /**
   * name 杨蒙
   * 通过银行信息表 返回数据
   */
  public BankAFDTO queryBank(String id)throws Exception;
  
  /**
   * name 杨蒙
   * 根据id删除银行信息表记录
   */
  public String deleteBank_YM(Integer id,SecurityInfo securityInfo)throws Exception;
  /**
   * name 杨蒙
   * 银行信息表 启用功能
   */
  public String useBank_YM(Integer id,SecurityInfo securityInfo)throws Exception;
  /**
   * name 杨蒙
   * 根据办事处,银行 或得此条记录 (**张列改**)
   */
  public void updateBank_YM(BankAFDTO bankAFDTO,SecurityInfo securityInfo)throws Exception;
  /**
   * 得到办事处下的归集银行
   * @param office
   * @return CollBankList
   */
  public List queryCollBank(String office);
  /**
   * name 杨蒙
   *根据id 判断是否有记录
   */
  public boolean is_bank_YM(Integer id);
  /**
   * 获得所有银行  张列
   * @return
   */
  public List getCollBankList();
}
