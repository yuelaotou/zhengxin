package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.SurrogateAFDTO;

public interface ISurrogateBS {
  /**
   * name 杨蒙
   * 代理公司维护-显示列表
   */
  public List findSurrogateList(Pagination pagination) throws Exception, BusinessException;
/**
 * name 杨蒙
 * 代理公司维护 插入新记录（**张列改**）
 */
  public void insertSurrogateList(SurrogateAFDTO surrogateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 代理公司维护 通过id查询数据用于修改
   */
  public SurrogateAFDTO findSurrogateID(Integer id)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 代理公司维护,修改数据（**张列改**）
   */
  public void updateSurrogate(SurrogateAFDTO surrogateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 根据id删除PL007数据
   */
  public String deleteSurrogate(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   *根据id 判断是否有记录
   *true 有此记录
   *false 无此记录
   */
  public boolean is_Surrogate_YM(Integer id)throws Exception,BusinessException;


}
