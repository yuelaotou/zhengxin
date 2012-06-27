package org.xpup.hafmis.sysfinance.bookmng.settlemodle.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.bsinterface.ISettlemodleBS;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.dto.SettlemodleDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BookParameter;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : 结算方式
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-24-2007
 */
public class SettlemodleBS implements ISettlemodleBS {

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
   * 返回查询结果(List) 结算方式信息
   */
  public List findSettlemodleList(Pagination pagination, String bookId) throws Exception {
    List list=new ArrayList();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = this.bookParameterDAO.querySettlemodleList(bookId ,orderBy, order, start, pageSize,page);
    } catch (Exception e) {
      // TODO: handle exception
       e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 返回查询结果记录数
   */
  public int querySettlemodleCount(String bookId) throws Exception {
    int count = 0;
    try {
      String temp_count = bookParameterDAO.querySettlemodleCount(bookId);
      count = Integer.parseInt(temp_count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * 判断输入的结算方式在FN102.PARAM_NUM=3的记录的PARAM_EXPLAIN是否存在(用于插入)
   */
  public boolean is_SettlemodleParamExplainInsert(SettlemodleDTO settlemodleDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySettlemodleParamExplainInsert(settlemodleDTO.getBookId(),settlemodleDTO.getParamExplain());
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
   * 判断输入的结算方式在FN102.PARAM_NUM=3的记录的PARAM_EXPLAIN是否存在(修改用)
   */
  public boolean is_SettlemodleParamExplainUpdate(SettlemodleDTO settlemodleDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySettlemodleParamExplainUpdate(settlemodleDTO.getBookId(),settlemodleDTO.getParamExplain(),settlemodleDTO.getParaId());
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
      OP_MODEL=结算方式
      OP_BUTTON=1
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   * 插入FN102
      BOOK_ID=所属账套
      PARAM_NUM=2
      PARAM_DESCRIP=“结算方式”
      PARAM_VALUE=最大的PARAM_VALUE+1
      PARAM_EXPLAIN=所输的值
   */
  public void insertSettlemodleInfo(SettlemodleDTO settlemodleDTO,SecurityInfo securityInfo)throws Exception{
    try {
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE+"");
      fnOperateLog.setOpButton("1");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      //插入FN102
      BookParameter bookParameter = new BookParameter();
      bookParameter.setBookId(securityInfo.getBookId());
      bookParameter.setParamNum("3");
      bookParameter.setParamDescrip("结算方式");
      //获得fn102 表中param_value的最大值
      List temp_list = bookParameterDAO.queryParamValue3Max(securityInfo.getBookId());
      if(temp_list.size() == 0){
        bookParameter.setParamValue("1");
      }else{
        int paramValue = Integer.parseInt(temp_list.get(0).toString())+1;
        bookParameter.setParamValue(paramValue+"");
      }
      bookParameter.setParamExplain(settlemodleDTO.getParamExplain());
      bookParameterDAO.insert(bookParameter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 更新FN102
      PARAM_EXPLAIN=所输的结算方式
   * 插入FN311
      OP_SYS=财务系统
      OP_MODEL=结算方式
      OP_BUTTON=2
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员 
   */
  public void updateSettlemodleInfo(SettlemodleDTO settlemodleDTO, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      //更新FN102
      this.bookParameterDAO.updateSettlemodleInfo(settlemodleDTO.getParaId(), settlemodleDTO.getParamExplain());
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE+"");
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
   * 根据paraId 判断FN102表中是否有记录
   */
  public boolean isSettlemodleById(String paraId) throws Exception {
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
   * 根据ID 查询结算方式
   */
  public SettlemodleDTO querySettlemodleParamExplainInfo(String paraId) throws Exception {
    // TODO Auto-generated method stub
    SettlemodleDTO settlemodleDTO = new SettlemodleDTO();
    try {
      List temp_list = bookParameterDAO.querySettlemodleParamExplainInfo(paraId);
      if(temp_list.size() == 0){
        return settlemodleDTO;
      }
      String paramExplain = ((SettlemodleDTO)(temp_list.get(0))).getParamExplain();
      settlemodleDTO.setParamExplain(paramExplain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return settlemodleDTO;
  }
  
  /**
   * 判断该记录的FN102.PARA_ID在FN201.SETT_TYPE or FN210.SETT_TYPE中是否存在
   */
  public boolean isSettlemodleByParamValue(String paraId,String bookId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String existence = bookParameterDAO.isSettlemodleByParamValue(paraId, bookId);
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
  public void deleteSettlemodleInfo(String paraId,SecurityInfo securityInfo) throws Exception {
    try {
      //删除 FN102表中的 paraId 记录
      bookParameterDAO.deleteCredencecharInfo(paraId);
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE+"");
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
  
}
