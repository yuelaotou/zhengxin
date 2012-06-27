package org.xpup.hafmis.sysfinance.bookmng.bookstart.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information   : 启用账套
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-20-2007
 */
public interface IBookstartBS {

  //获得账套名称
  public String getBookName(String bookId) throws Exception;
  //获得账套启用时间
  public String getUseYearmonth(String bookId) throws Exception;
  //获得账套启用时间的年
  public String getUserYear(String bookId) throws Exception;
  //获得账套启用时间的月
  public String getUserMonth(String bookId) throws Exception;
  //获得账套科目级数
  public String getParamValue(String bookId) throws Exception;
  //获得账套代码结构
  public String getParamExplain(String bookId) throws Exception;
  //账套启用状态
  public String getBookST(String bookId)throws Exception;
  //判断FN201.DEBIT-FN201.CREDIT是否=0
  public boolean isDebitCredit(String bookId)throws Exception;
  //更新FN101.USE_PERSON=操作员 FN101.BOOK_ST=1
  public void updateBook(SecurityInfo securityInfo, String bookId)throws Exception;
  //判断FN201中是否存在初始余额
  public String queryCredMessByOfficeID(SecurityInfo securityInfo, String officeid)throws Exception,BusinessException;
}
