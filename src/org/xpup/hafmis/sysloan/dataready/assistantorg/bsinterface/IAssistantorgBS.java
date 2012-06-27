package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.AssistantorgAFDTO;

public interface IAssistantorgBS {
  /**
   * name 杨蒙
   * 担保公司维护-显示列表
   */
  public List findAssistantorgList(Pagination pagination) throws Exception, BusinessException;
/**
 * name 杨蒙
 * 担保公司维护 插入新记录（**张列改**）
 */
  public void insertAssistantorgList(AssistantorgAFDTO assistantorgDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 担保公司维护 通过id查询数据用于修改
   */
  public AssistantorgAFDTO findAssistantorgID(Integer id)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 担保公司维护,修改数据   （**张列改**）
   */
  public void updateAssistantOrg(AssistantorgAFDTO assistantorgDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   * 根据id删除PL007数据
   */
  public String deleteAssistantOrg(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name 杨蒙
   *根据id 判断是否有记录
   *true 有此记录
   *false 无此记录
   */
  public boolean is_asistantOrg_YM(Integer id)throws Exception,BusinessException;

}
