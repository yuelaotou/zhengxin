package org.xpup.hafmis.sysfinance.bookmng.datainitialize.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information   : 初始数据
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-16-2007
 */
public interface IDatainitializeBS {
  //获得科目代码和科目名称
  public List getDatainitialize(String bookId) throws Exception;
  //获得累计借方和累计贷方
  public List getLendsMoney(String bookId,String officeName) throws Exception;
  //删除FN201
  public void deleteSummaryOffice(List list,SecurityInfo securityInfo) throws Exception;
  // 插入FN311
  public void insertSummaryOffice(List list,SecurityInfo securityInfo) throws Exception;
  //FN101 账套状态 0可用，1为禁用
  public String getBookST(String bookId)throws Exception;
  //判断FN201里是否存在SUMMAY=1 and OFFICE=所选办事处的记录
  public List is_SummayOffice(final String bookId,final String officeName) throws Exception;
}
