package org.xpup.hafmis.demo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.domain.entity.Demo;


/**
 * 
 * @author 刘洋
 *2007-5-31
 */
public interface IDemoBS {
  //根据条件查询demo记录
  public List findDemoListByCriterions(Pagination pagination) throws Exception,BusinessException;
  //根据id查询demo记录 
  public Demo findDemoById(Integer id)throws Exception;
  //添加记录
  public Integer addDemo(Demo demo) throws BusinessException;
  //修改记录
  public void updateDemo(Demo demo) throws BusinessException;
  //删除单条记录
  public void deleteDemo(Integer id) throws BusinessException;
  //复选删除
  public void deleteDemoMultibox(String[] rowArray) throws BusinessException;
  //删除当前页
  public void deletePageList(List list) throws BusinessException;
  //查询demo所有记录
  public List findDemoListAll(Pagination pagination) throws Exception,BusinessException;
  //批量修改
  public List modifyDemoBatch(List demoExportList) throws Exception,BusinessException;
}
