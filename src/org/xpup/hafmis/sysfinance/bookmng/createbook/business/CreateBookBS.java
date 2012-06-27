package org.xpup.hafmis.sysfinance.bookmng.createbook.business;

import org.xpup.hafmis.sysfinance.bookmng.createbook.bsinterface.ICreateBookBS;
import org.xpup.hafmis.sysfinance.bookmng.createbook.dto.CreateBookDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;

/**
 * Copy Right Information   : 创建账套
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-13-2007
 * @author Administrator
 *
 */
public class CreateBookBS implements ICreateBookBS {

  private BookDAO bookDAO = null ;
  
  public BookDAO getBookDAO() {
    return bookDAO;
  }

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  /**
   * 创建账套 张列
   */
  public void createBook(CreateBookDTO createBookDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      bookDAO.createBook(createBookDTO);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

}
