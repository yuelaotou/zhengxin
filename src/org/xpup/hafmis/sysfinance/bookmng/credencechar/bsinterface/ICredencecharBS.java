package org.xpup.hafmis.sysfinance.bookmng.credencechar.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencechar.dto.CredencecharDTO;

/**
 * Copy Right Information   : 凭证字
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-22-2007
 */
public interface ICredencecharBS {
  //返回查询结果(List) 凭证字信息
  public List findCredencecharList(Pagination pagination,String bookId)throws Exception;
  //返回查询结果记录数
  public int queryCredencecharCount(String bookId)throws Exception;
  //判断输入的凭证字在FN102.PARAM_NUM=2的记录的PARAM_EXPLAIN是否存在(用于插入)
  public boolean is_CredencecharParamExplainInsert(CredencecharDTO credencecharDTO)throws Exception;
  //判断输入的凭证字在FN102.PARAM_NUM=2的记录的PARAM_EXPLAIN是否存在(用于修改)
  public boolean is_CredencecharParamExplainUpdate(CredencecharDTO credencecharDTO)throws Exception;
  //插入表FN311和FN102
  public void insertCredencecharInfo(CredencecharDTO credencecharDTO,SecurityInfo securityInfo)throws Exception;
  //根据ID 查询出凭证字 和 凭证字名称
  public CredencecharDTO queryCredencecharParamExplainInfo(String paraId)throws Exception;
  //更新 FN102 插入FN311
  public void updateCredencecharInfo(CredencecharDTO credencecharDTO,SecurityInfo securityInfo)throws Exception;
  //判断FN102是否 有传入PARAID 的记录
  public boolean isCredencecharById(String paraId)throws Exception;
  //判断该记录的FN102.paraId在FN201.CREDENCE_CHARACTER or FN210.CREDENCE_CHARACTER中是否存在
  public boolean isCredencecharByParamValue(final String paraId,String bookId)throws Exception;
  //删除 FN102表中的 paraId 记录    插入FN311日志
  public void deleteCredencecharInfo(String paraId,SecurityInfo securityInfo) throws Exception;
}
