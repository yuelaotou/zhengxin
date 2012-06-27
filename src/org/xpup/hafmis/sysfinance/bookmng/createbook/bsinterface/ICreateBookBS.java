package org.xpup.hafmis.sysfinance.bookmng.createbook.bsinterface;

import org.xpup.hafmis.sysfinance.bookmng.createbook.dto.CreateBookDTO;

/**
 * Copy Right Information   : 创建账套
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-13-2007
 * @author Administrator
 *
 */
public interface ICreateBookBS {
  //创建账套
  public void createBook(CreateBookDTO createBookDTO)throws Exception;
}
