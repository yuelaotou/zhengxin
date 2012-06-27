package org.xpup.hafmis.sysfinance.bookmng.credencechar.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencechar.bsinterface.ICredencecharBS;
import org.xpup.hafmis.sysfinance.bookmng.credencechar.dto.CredencecharDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BookParameter;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : 凭证字
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-22-2007
 */
public class CredencecharBS implements ICredencecharBS {

  private BookParameterDAO bookParameterDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;

  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  /**
   * 返回查询结果(List) 凭证字信息
   */
  public List findCredencecharList(Pagination pagination,String bookId) throws Exception {
    List list=new ArrayList();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = this.bookParameterDAO.queryCredencecharList(bookId ,orderBy, order, start, pageSize,page);
    } catch (Exception e) {
      // TODO: handle exception
       e.printStackTrace();
    }
    return list;
  }

  /**
   * 返回查询结果记录数
   */
  public int queryCredencecharCount(String bookId) throws Exception {
    int count = 0;
    try {
      String temp_count = bookParameterDAO.queryCredencecharCount(bookId);
      count = Integer.parseInt(temp_count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 判断输入的凭证字在FN102.PARAM_NUM=2的记录的PARAM_EXPLAIN是否存在(插入用)
   */
  public boolean is_CredencecharParamExplainInsert(CredencecharDTO credencecharDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.queryCredencecharParamExplainInsert(credencecharDTO.getBookId(),credencecharDTO.getParamExplain());
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
   * 判断输入的凭证字在FN102.PARAM_NUM=2的记录的PARAM_EXPLAIN是否存在(修改用)
   */
  public boolean is_CredencecharParamExplainUpdate(CredencecharDTO credencecharDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.queryCredencecharParamExplainUpdate(credencecharDTO.getBookId(),credencecharDTO.getParamExplain(),credencecharDTO.getParaId());
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
      OP_MODEL=凭证字
      OP_BUTTON=1
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   * 插入FN102
      BOOK_ID=所属账套
      PARAM_NUM=2
      PARAM_DESCRIP=“凭证字”
      PARAM_VALUE=最大的PARAM_VALUE+1
      PARAM_EXPLAIN=所输的凭证字
      PARAM_EXPLAIN_EXPLAIN=所输的凭证字名称
   */
  public void insertCredencecharInfo(CredencecharDTO credencecharDTO,SecurityInfo securityInfo)throws Exception{
    try {
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR+"");
      fnOperateLog.setOpButton("1");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      //插入FN102
      BookParameter bookParameter = new BookParameter();
      bookParameter.setBookId(securityInfo.getBookId());
      bookParameter.setParamNum("2");
      bookParameter.setParamDescrip("凭证字");
      //获得fn102 表中param_value的最大值
      List temp_list = bookParameterDAO.queryParamValueMax(securityInfo.getBookId());
      if(temp_list.size() == 0){
        bookParameter.setParamValue("1");
      }else{
        int paramValue = Integer.parseInt(temp_list.get(0).toString())+1;
        bookParameter.setParamValue(paramValue+"");
      }
      bookParameter.setParamExplain(credencecharDTO.getParamExplain());
      bookParameter.setParamExplainExplain(credencecharDTO.getParamExplainExplain());
      bookParameterDAO.insert(bookParameter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  /**
   * 根据ID 查询出凭证字 和 凭证字名称
   */
  public CredencecharDTO queryCredencecharParamExplainInfo(String paraId) throws Exception {
    // TODO Auto-generated method stub
    CredencecharDTO credencecharDTO = new CredencecharDTO();
    try {
      List temp_list = bookParameterDAO.queryCredencecharParamExplainInfo(paraId);
      if(temp_list.size() == 0){
        return credencecharDTO;
      }
      String paramExplain = ((CredencecharDTO)(temp_list.get(0))).getParamExplain();
      String paramExplainExplain = ((CredencecharDTO)(temp_list.get(0))).getParamExplainExplain();
      credencecharDTO.setParamExplain(paramExplain);
      credencecharDTO.setParamExplainExplain(paramExplainExplain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return credencecharDTO;
  }

  /**
   * 更新FN102
      PARAM_EXPLAIN=所输的凭证字
      PARAM_EXPLAIN_EXPLAIN=所输的凭证字名称
   * 插入FN311
      OP_SYS=财务系统
      OP_MODEL=凭证字
      OP_BUTTON=2
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   */
  public void updateCredencecharInfo(CredencecharDTO credencecharDTO, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      //更新FN102
      this.bookParameterDAO.updateCredencecharInfo(credencecharDTO.getParaId(), credencecharDTO.getParamExplain(), credencecharDTO.getParamExplainExplain());
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR+"");
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
  public boolean isCredencecharById(String paraId) throws Exception {
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
   * 删除 FN102表中的 paraId 记录
   * 插入FN311日志
   * @param paraId
   * @throws Exception
   */
  public void deleteCredencecharInfo(String paraId,SecurityInfo securityInfo) throws Exception {
    try {
      //删除 FN102表中的 paraId 记录
      bookParameterDAO.deleteCredencecharInfo(paraId);
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR+"");
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

  /**
   * 判断该记录的FN102.paraId在FN201.CREDENCE_CHARACTER or FN210.CREDENCE_CHARACTER中是否存在
   */
  public boolean isCredencecharByParamValue(String paraId,String bookId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String existence = bookParameterDAO.isCredencecharByParamValue(paraId,bookId);
      if(existence.equals("0")){
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
}
