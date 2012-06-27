package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.EvaluateAFDTO;

public interface IEvaluateBS {
    /**
     * name 杨蒙
     * 评估公司维护-显示列表
     */
    public List findEvaluateList(Pagination pagination) throws Exception, BusinessException;
  /**
   * name 杨蒙
   * 评估公司维护 插入新记录  (**张列改**)
   */
    public void insertEvaluateList(EvaluateAFDTO evaluateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
    /**
     * name 杨蒙
     * 评估公司维护 通过id查询数据用于修改
     */
    public EvaluateAFDTO findEvaluateID(Integer id)throws Exception,BusinessException;
    /**
     * name 杨蒙
     * 评估公司维护,修改数据  (**张列改**)
     */
    public void updateEvaluate(EvaluateAFDTO evaluateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
    /**
     * name 杨蒙
     * 根据id删除PL007数据
     */
    public String deleteEvaluate(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
    /**
     * name 杨蒙
     *根据id 判断是否有记录
     *true 有此记录
     *false 无此记录
     */
    public boolean is_Evaluate_YM(Integer id)throws Exception,BusinessException;


}
