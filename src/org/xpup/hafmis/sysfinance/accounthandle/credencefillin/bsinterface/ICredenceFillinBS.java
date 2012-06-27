package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;

public interface ICredenceFillinBS {

  public AccountantCredence queryById(Serializable credenceId);
  /**
   * 查询凭证录入Tb-自动挂帐页面显示的列表
   * 
   * @param pagination
   * @param officeList 办事处List
   * @return 列表的内容
   * @throws Exception
   */
  public Object[] findCredenceFillinTbList(Pagination pagination,
      List officeList, SecurityInfo securityInfo) throws Exception;
  public List getIncomeList(CredenceFillinTbFindDTO credenceFillinTbFindDTO) throws Exception ;
  public List getExpenseList(CredenceFillinTbFindDTO credenceFillinTbFindDTO) throws Exception ;
  /**
   * 判断核算方式
   * 
   * @return 0为同一核算，1独立核算
   * @throws Exception
   */
  public int findFnSettleType() throws Exception;

  /**
   * 根据输入的汉字显示对应的摘要列表
   * 
   * @author 刘冰
   * @throws Exception
   */
  public String findCredenceCharacterList(String search,
      SecurityInfo securityInfo) throws Exception;

  /**
   * 生成凭证的方法
   * 
   * @param settnum 票据号以及业务状态
   * @param securityInfo
   * @throws Exception
   */
  public void saveCredenceInfo(String[] settNum, String credenceDate, String oldCredenceNum, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * 查询Tc-损益结转列表的方法
   * 
   * @param pagination
   * @param officeList
   * @return
   * @throws Exception
   */
  public Object[] findCredenceFillinTcList(Pagination pagination,
      List officeList, String bookId) throws Exception;

  /**
   * 损益结转的方法
   * 
   * @param settNum 列表内容(包括科目代码，办事处，借方金额，贷方金额)
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void saveSettleIncAndDecInfo(String[] SettleIncAndDecInfo,
      SecurityInfo securityInfo, String credenceDateStart, String credenceDateEnd) throws Exception, BusinessException;

  /**
   * 凭证维护中删除凭证的方法
   * 
   * @param credenceId FN201表的主键
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void delectCredenceInfo(String credenceId, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * 删除所有凭证的方法
   * 
   * @param countList 列表中的全部内容
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void delectAllCredenceInfo(List countList, SecurityInfo securityInfo)
      throws Exception, BusinessException;
  
  /**
   * 查询连打列表的方法
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findContinuumPrintList(Pagination pagination, String type,
      SecurityInfo securityInfo) throws Exception;

  /**
   * 凭证录入取得日期
   * 
   * @author 刘冰 2007-11-1 查询fn201表里ID号最大的记账日期 查询条件：officeCode所选办事处、bookId
   */
  public String getCredenceDate(String officeCode, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * 凭证录入根据所填结算号判断FN201表中是否存在此结算号的记录
   * 
   * @author 刘冰 2007-11-5
   */
  public String queryIsExistSettNum(String settNum,String bookId) throws Exception,
      BusinessException;

  /**
   * 存入凭证表中
   * 
   * @author 刘冰
   * @throws Exception
   */
  public void insertCredenceFillinTa(
      CredenceFillinTaShowDTO credenceFillinTaShowDTO,
      SecurityInfo securityInfo, String listAllContent) throws Exception;

  /**
   * 帐务处理-凭证录入
   * 
   * @author 刘冰 2007-11-7 修改凭证录入
   */
  public void updateCredenceFillinTa(
      CredenceFillinTaShowDTO credenceFillinTaShowDTO,
      SecurityInfo securityInfo, String listAllContent) throws Exception;

  /**
   * 根据输入的汉字显示对应的摘要列表
   * 
   * @author 刘冰
   * @throws Exception
   */
  public String findSummayList(String search, SecurityInfo securityInfo)
      throws Exception;

  /**
   * 得到凭证字列表
   * 
   * @author 刘冰
   * @throws Exception
   */
  public List findCredenceCharacterList(SecurityInfo securityInfo)
      throws Exception;

  /**
   * 取得结算方式列表
   * 
   * @author 刘冰 2007-11-1 查询fn102表里paramExplain字段的数据 查询条件：paramNum
   */
  public List findSettTypeList(SecurityInfo securityInfo) throws Exception;

  /**
   * 判断最末级科目代码
   * 
   * @author 刘冰
   * @throws Exception
   */
  public Object[] isSubjectCodeEnd(String subjectCode, SecurityInfo securityInfo)
  throws Exception, BusinessException;

  /**
   * 凭证录入根据所选办事处取得对应的凭证号
   * 
   * @author 刘冰 2007-11-1
   */
  public String getCredenceNum(String officeCode, String bizYearmonth,
      String credenceNumType, String bookId) throws Exception;

  /**
   * 判断数据库中是否存在此摘要
   * 
   * @author 刘冰 2007-11-9
   */
  public boolean isExistSummay(String summay,SecurityInfo securityInfo) throws Exception,
      BusinessException;
  /**
   * 取得凭证录入摘要列表
   * 
   * @author 刘冰 2007-11-10 查询fn102表里paramExplain字段的数据
   */
  public List getSummayList(SecurityInfo securityInfo) throws Exception;
  /**
   * 得到凭证录入的会计科目代码对应的科目余额及方向
   * 
   * @author 刘冰 2007-11-23
   */
  public Object[] getBalanceDir(String subjectcode, String office,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * 凭证录入判断帐套状态
   * @author 刘冰
   * 2007-11-26
   * 根据bookid查询账套状态
   */
  public String findBookSt(SecurityInfo securityInfo) throws Exception;
  /**
   * 查询凭证信息的方法，用来进行修改
   * 
   * @param docNum 凭证号
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public Object[] findAccountantCredence(String docNum,
      SecurityInfo securityInfom, String credenceDate, String office) throws Exception;
  /**
   * 查询删除行后列表的方法
   * @param list
   * @return
   * @throws Exception
   */
  public Object[] findDelRowList(List list)throws Exception;
  //获得归集银行
  public List getCollBank();
}
