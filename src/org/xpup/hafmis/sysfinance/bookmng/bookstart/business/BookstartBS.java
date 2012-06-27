package org.xpup.hafmis.sysfinance.bookmng.bookstart.business;

import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.bookstart.bsinterface.IBookstartBS;
import org.xpup.hafmis.sysfinance.bookmng.bookstart.dto.BookstartDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : 启用账套
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-20-2007
 */
public class BookstartBS implements IBookstartBS {

  private BookDAO bookDAO = null;
  private BookParameterDAO bookParameterDAO = null;
  private AccountantCredenceDAO accountantCredenceDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;
  
  public BookDAO getBookDAO() {
    return bookDAO;
  }
  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public AccountantCredenceDAO getAccountantCredenceDAO() {
    return accountantCredenceDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }
  
  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  
  
  /**
   * 获得账套名称
   */
  public String getBookName(String bookId) throws Exception {
    // TODO Auto-generated method stub
    String bookName = "";
    try {
      bookName = bookDAO.getBookName(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bookName;
  }
  
  /**
   * 获得账套启用时间
   */
  public String getUseYearmonth(String bookId) throws Exception {
    // TODO Auto-generated method stub
    String useYearmonth = "";
    try {
      useYearmonth = bookDAO.getUseYearmonth(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return useYearmonth;
  }
  
  /**
   * 获得账套启用时间的年
   */
  public String getUserMonth(String bookId) throws Exception {
    // TODO Auto-generated method stub
    String month = "";
    try {
      month = this.getUseYearmonth(bookId).substring(4, 6);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return month;
  }
  
  /**
   * 获得账套启用时间的月
   */
  public String getUserYear(String bookId) throws Exception {
    // TODO Auto-generated method stub
    String year = "";
    try {
      year = this.getUseYearmonth(bookId).substring(0, 4);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return year;
  }
  
  /**
   * 获得账套科目级数
   */
  public String getParamValue(String bookId) throws Exception {
    // TODO Auto-generated method stub
    String paramValue = "";
    try {
      paramValue = bookParameterDAO.getParamValue(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  
  /**
   * 获得账套代码结构
   */
  public String getParamExplain(String bookId) throws Exception {
    // TODO Auto-generated method stub
    String paramExplain = "";
    try {
      List temp_list = bookParameterDAO.getParamExplain(bookId);
      int temp_size = temp_list.size();
      for(int i=0;i<temp_size;i++){
        if(i==0){
          paramExplain += temp_list.get(i).toString()+"-";
        }
        if(i==1){
          paramExplain += temp_list.get(i).toString()+"-";
        }
        if(i==2){
          paramExplain += temp_list.get(i).toString()+"-";
        }
        if(i==3){
          paramExplain += temp_list.get(i).toString()+"-";
        }
        if(i==4){
          paramExplain += temp_list.get(i).toString()+"-";
        }
        if(i==5){
          paramExplain += temp_list.get(i).toString()+"-";
        }
      }
      paramExplain = paramExplain.substring(0, paramExplain.lastIndexOf("-"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
  
  /**
   * 账套启用状态
   */
  public String getBookST(String bookId)throws Exception{
    String stutas = "";
    try {
      stutas = accountantCredenceDAO.getBookST(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }
  
  /**
   * 判断FN201.DEBIT-FN201.CREDIT是否=0
   */
  public boolean isDebitCredit(String bookId)throws Exception{
    try {
      BookstartDTO bookstartDTO = accountantCredenceDAO.queryDebitCredit(bookId);
      double debitAll = bookstartDTO.getDebit();
      double creditAll = bookstartDTO.getCredit();
      if(debitAll - creditAll == 0){
        return true;
      }
    } catch (Exception e) {
     e.printStackTrace();
    }
    return false;
  }
  
  /**
   * 更新FN101.USE_PERSON=操作员 FN101.BOOK_ST=1
   * 插入FN311 
      OP_SYS=财务系统
      OP_MODEL=启用账套
      OP_BUTTON=7
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   */
  public void updateBook(SecurityInfo securityInfo, String bookId) throws Exception {
    // TODO Auto-generated method stub
    try {
      //更新FN101.USE_PERSON=操作员 FN101.BOOK_ST=1
      bookDAO.updateBook(bookId, securityInfo.getUserName());
      //插入FN311 
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_BOOKSTART+"");
      fnOperateLog.setOpButton("7");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(bookId);
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public String queryCredMessByOfficeID(SecurityInfo securityInfo, String officeid) throws Exception,BusinessException {
    // TODO Auto-generated method stub
    String stutas="0";
    stutas=accountantCredenceDAO.queryCredMessageByOfficeID(officeid,securityInfo.getBookId());
    return stutas;
  }
  
}
