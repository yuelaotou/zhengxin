package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.NotarialAFDTO;
public interface INotarialBS {
  /**
   * name 杨蒙
   * 公证处维护-显示列表
   */
  public List findNotarialList(Pagination pagination) throws Exception, BusinessException;
/**
 * name 杨蒙
 * 公证处维护 插入新记录
 */
  public void insertNotarialList(NotarialAFDTO notarialAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 公证处维护 通过id查询数据用于修改
   */
  public NotarialAFDTO findNotarialID(Integer id)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 公证处维护,修改数据
   */
  public void updateNotarial(NotarialAFDTO notarialAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 根据id删除PL007数据
   */
  public String deleteNotarial(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   *根据id 判断是否有记录
   *true 有此记录
   *false 无此记录
   */
  public boolean is_Notarial_YM(Integer id)throws Exception,BusinessException;



}
