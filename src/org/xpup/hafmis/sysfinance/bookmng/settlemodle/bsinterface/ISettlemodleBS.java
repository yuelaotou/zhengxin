package org.xpup.hafmis.sysfinance.bookmng.settlemodle.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.dto.SettlemodleDTO;

/**
 * Copy Right Information   : 结算方式
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-24-2007
 */
public interface ISettlemodleBS {
  //返回查询结果(List) 结算方式信息
  public List findSettlemodleList(Pagination pagination,String bookId)throws Exception;
  //返回查询结果记录数
  public int querySettlemodleCount(String bookId)throws Exception;
  //判断输入的结算方式在FN102.PARAM_NUM=3的记录的PARAM_EXPLAIN是否存在(用于插入)
  public boolean is_SettlemodleParamExplainInsert(SettlemodleDTO settlemodleDTO)throws Exception;
  //判断输入的结算方式在FN102.PARAM_NUM=3的记录的PARAM_EXPLAIN是否存在(修改用)
  public boolean is_SettlemodleParamExplainUpdate(SettlemodleDTO settlemodleDTO) throws Exception;
  //更新FN102 PARAM_EXPLAIN=所输的结算方式 ,插入FN311
  public void updateSettlemodleInfo(SettlemodleDTO settlemodleDTO, SecurityInfo securityInfo) throws Exception;
  //插入 FN102 FN311表
  public void insertSettlemodleInfo(SettlemodleDTO settlemodleDTO,SecurityInfo securityInfo)throws Exception;
  //根据paraId 判断FN102表中是否有记录
  public boolean isSettlemodleById(String paraId) throws Exception;
  //根据ID 查询结算方式
  public SettlemodleDTO querySettlemodleParamExplainInfo(String paraId) throws Exception;
  //判断该记录的FN102.PARA_ID在FN201.SETT_TYPE or FN210.SETT_TYPE中是否存在
  public boolean isSettlemodleByParamValue(String paraId,String bookId) throws Exception;
  //删除 FN102表中的 paraId 记录  插入FN311日志
  public void deleteSettlemodleInfo(String paraId,SecurityInfo securityInfo) throws Exception;
}
