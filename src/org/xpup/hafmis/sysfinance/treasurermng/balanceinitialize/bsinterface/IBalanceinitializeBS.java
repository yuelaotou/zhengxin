package org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information   : 余额初始
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-19-2007
 */
public interface IBalanceinitializeBS {
  //获得科目代码和科目名称 
  public List getSubjectCodeName(String bookId,String officeName) throws Exception ;
  //余额初始 同一办事处下的SUBJECT_CODE中存在相同的值 返回INT 大于1有相同的值
  public int getBalanceinitializeBT(String bookId,String officeName) throws Exception ;
  //判断FN210表中是否存在SUMMAY=3 and OFFICE=所选办事处的记录
  public int is_Balanceinitialize_ZL(String bookId,String officeName)throws Exception;
  //插入FN311 插入FN210
  public void insertBalanceinitialize(SecurityInfo securityInfo,List list) throws Exception;
  //删除FN210 插入FN311 插入FN210
  public void deleteBalanceinitialize(SecurityInfo securityInfo,List list) throws Exception;
}
