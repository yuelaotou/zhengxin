package org.xpup.hafmis.sysfinance.accounthandle.dayclear.business;

import org.xpup.hafmis.orgstrct.dao.RelaUserAndBookDAO;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndBook;
import org.xpup.hafmis.sysfinance.accounthandle.dayclear.bsinterface.IDayclearBS;

/**
 * @author ЭѕСт 2007-11-16
 */
public class DayclearBS implements IDayclearBS {

  RelaUserAndBookDAO relaUserAndBookDAO = null;

  public void setRelaUserAndBookDAO(RelaUserAndBookDAO relaUserAndBookDAO) {
    this.relaUserAndBookDAO = relaUserAndBookDAO;
  }

  public String getBizDate(final String username, final String bookId) {
    RelaUserAndBook relaUserAndBook = relaUserAndBookDAO.queryUserOff(username,
        bookId);
    return relaUserAndBook.getBizDate();
  }
}
