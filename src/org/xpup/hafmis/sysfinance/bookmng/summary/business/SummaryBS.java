package org.xpup.hafmis.sysfinance.bookmng.summary.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.summary.bsinterface.ISummaryBS;
import org.xpup.hafmis.sysfinance.bookmng.summary.dto.SummaryDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BookParameter;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : 常用摘要
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-25-2007
 */
public class SummaryBS implements ISummaryBS {
  
  private BookParameterDAO bookParameterDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;
  
  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  
  /**
   * 返回查询结果(List) 常用摘要信息
   */
  public List findSummaryList(Pagination pagination, String bookId) throws Exception {
    List list=new ArrayList();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = this.bookParameterDAO.querySummaryList(bookId ,orderBy, order, start, pageSize,page);
    } catch (Exception e) {
      // TODO: handle exception
       e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 返回查询结果记录数
   */
  public int querySummaryCount(String bookId) throws Exception {
    int count = 0;
    try {
      String temp_count = bookParameterDAO.querySummaryCount(bookId);
      count = Integer.parseInt(temp_count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * 判断输入的常用摘要在FN102.PARAM_NUM=4的记录的PARAM_EXPLAIN是否存在(用于插入)
   */
  public boolean is_SummaryParamExplainInsert(SummaryDTO summaryDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySummaryParamExplainInsert(summaryDTO.getBookId(),summaryDTO.getParamExplain());
      int count = Integer.parseInt(temp_ParamExplain);
      //有记录
      if(count > 0){
        return false;
      }
      } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
  public boolean is_SummaryPInFn201(String bookId,String  summaryId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = bookParameterDAO.querySummaryInFn201(bookId,summaryId);
      int count = Integer.parseInt(temp_ParamExplain);
      //有记录
      if(count > 0){
        return false;
      }
      } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
  /**
   * 插入FN311
      OP_SYS=财务系统
      OP_MODEL=常用摘要
      OP_BUTTON=1
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   * 插入FN102
      BOOK_ID=所属账套
      PARAM_NUM=4
      PARAM_DESCRIP=常用摘要
      PARAM_VALUE=最大的PARAM_VALUE+1(从11开始插入）
      PARAM_EXPLAIN=所输的摘要
      PARAM_EXPLAIN_EXPLA=所输摘要名称的全拼。
   */
  public void insertSummaryInfo(SummaryDTO summaryDTO,SecurityInfo securityInfo)throws Exception{
    try {
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SUMMARY+"");
      fnOperateLog.setOpButton("1");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      //插入FN102
      BookParameter bookParameter = new BookParameter();
      bookParameter.setBookId(securityInfo.getBookId());
      bookParameter.setParamNum("4");
      bookParameter.setParamDescrip("常用摘要");
      //获得fn102 表中param_value的最大值
      List temp_list = bookParameterDAO.queryParamValue4Max(securityInfo.getBookId());
      if(temp_list.size() == 0){
        bookParameter.setParamValue("11");
      }else{
        int paramValue = Integer.parseInt(temp_list.get(0).toString())+1;
        bookParameter.setParamValue(paramValue+"");
      }
      bookParameter.setParamExplain(summaryDTO.getParamExplain());
      bookParameter.setParamExplainExplain(summaryDTO.getParamExplainPY().toUpperCase());
      bookParameterDAO.insert(bookParameter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 根据paraId 判断FN102表中是否有记录
   */
  public boolean isSummaryById(String paraId) throws Exception {
    // TODO Auto-generated method stub
    try {
      int count = Integer.parseInt(bookParameterDAO.isCredencecharById(paraId));
      if(count>0){
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
  /**
   * 判断输入的常用摘要在FN102.PARAM_NUM=4的记录的PARAM_EXPLAIN是否存在(修改用)
   */
  public boolean is_SummaryParamExplainUpdate(SummaryDTO summaryDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySummaryParamExplainUpdate(summaryDTO.getBookId(),summaryDTO.getParamExplain(),summaryDTO.getParaId());
      int count = Integer.parseInt(temp_ParamExplain);
      //有记录
      if(count > 0){
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    //没有记录可以插入
    return true;
  }
  
  /**
   * 插入FN311
      OP_SYS=财务系统
      OP_MODEL=常用摘要
      OP_BUTTON=2
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   * 更新FN102
      PARAM_EXPLAIN=所输的摘要
      PARAM_EXPLAIN_EXPLA=所输摘要名称的全拼。
   * @param summaryDTO
   * @param securityInfo
   * @throws Exception
   */
  public void updateSummaryInfo(SummaryDTO summaryDTO, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      //更新FN102
      this.bookParameterDAO.updateSummaryInfo(summaryDTO.getParaId(), summaryDTO.getParamExplain(),summaryDTO.getParamExplainPY().toUpperCase());
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SUMMARY+"");
      fnOperateLog.setOpButton("2");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 根据ID 查询常用摘要
   */
  public SummaryDTO querySummaryParamExplainInfo(String paraId) throws Exception {
    // TODO Auto-generated method stub
    SummaryDTO summaryDTO = new SummaryDTO();
    try {
      List temp_list = bookParameterDAO.querySummaryParamExplainInfo(paraId);
      if(temp_list.size() == 0){
        return summaryDTO;
      }
      String paramExplain = ((SummaryDTO)(temp_list.get(0))).getParamExplain();
      summaryDTO.setParamExplain(paramExplain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return summaryDTO;
  }
  
  
  /**
   * 判断该记录的FN102.PARA_ID在FN201.SUMMAY or FN210.SUMMAY中是否存在
   */
  public boolean isSummaryByParamValue(String paraId,String bookId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String existence = bookParameterDAO.isSummaryByParamValue(paraId, bookId);
      if(existence.equals("0")){
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
  /**
   * 删除 FN102表中的 paraId 记录
   * 插入FN311日志
   * @param paraId
   * @throws Exception
   */
  public void deleteSummaryInfo(String paraId,SecurityInfo securityInfo) throws Exception {
    try {
      //删除 FN102表中的 paraId 记录
      bookParameterDAO.deleteCredencecharInfo(paraId);
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SUMMARY+"");
      fnOperateLog.setOpButton("3");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public boolean querySummaryInFn201(String bookId, String summaryId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = bookParameterDAO.querySummaryInFn201(bookId,summaryId);
      int count = Integer.parseInt(temp_ParamExplain);
      //有记录
      if(count > 0){
        return false;
      }
      } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
}
