package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnDocNumCancel;

public class FnDocNumCancelDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public FnDocNumCancel queryById(Serializable id) {
    Validate.notNull(id);
    return (FnDocNumCancel) getHibernateTemplate()
        .get(FnDocNumCancel.class, id);
  }

  /**
   * 插入记录
   * 
   * @param BookParameter
   * @return
   */
  public Serializable insert(FnDocNumCancel fnDocNumCancel) {
    Serializable id = null;
    try {
      Validate.notNull(fnDocNumCancel);
      id = getHibernateTemplate().save(fnDocNumCancel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 将作废的凭证号插入作废表 刘洋 officeCode 办事处,bizYearmonth 凭证年月,credenceNumType
   * 凭证类型,bookId 账套流水号
   */
  public void insertDocNumCancel(final String docNum, final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) throws BusinessException {
    try {
      FnDocNumCancel fnDocNumCancel = new FnDocNumCancel();
      fnDocNumCancel.setCancelcredenceid(docNum);
      fnDocNumCancel.setBizYearmonth(bizYearmonth);
      fnDocNumCancel.setOffice(officeCode);
      fnDocNumCancel.setBookId(bookId);
      fnDocNumCancel.setCredenceNumType(credenceNumType);
      this.insert(fnDocNumCancel);
    } catch (Exception e) {
      throw new BusinessException("作废业务凭证号时出现错误!");
    }
  }

  /**
   * 凭证过账-划账
   * 
   * @author wsh 判断作废凭证号表 FN302办事处代码=空并且凭证类型= 0.会计凭证是否存在记录 2007-10-31
   * @param bookId 账套
   * @return Integer
   */
  public Integer findCredenceclear(final String office, final String type,
      final String bookId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select count(t.credenceid) from fn302 t";
            Object obj = null;
            Integer temp_count = new Integer(0);
            Vector parameters = new Vector();
            String criterion = "";
            if (office != null && !"".equals(office)) {
              criterion += " t.office = ? and ";
              parameters.add(office);
            }
            if (type != null && !"".equals(type)) {
              criterion += " t.credence_num_type = ? and ";
              parameters.add(type);
            }
            if (bookId != null && !"".equals(bookId)) {
              criterion += " t.book_id = ? and ";
              parameters.add(bookId);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            sql = sql + criterion;
            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            obj = (Object) query.uniqueResult();
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * 凭证过账-划账
   * 
   * @author wsh 求作废凭证号中最小的凭证号
   * @param bookId 账套
   * @return Integer
   */
  public Integer findCredenceclearMinNum(final String office,
      final String type, final String bookId) {
    Integer minNun = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select min(t.credenceid) from fn302 t";
            Integer temp_minnun = new Integer(0);
            Vector parameters = new Vector();
            String criterion = "";
            if (office != null && !"".equals(office)) {
              criterion += " t.office = ? and ";
              parameters.add(office);
            }
            if (type != null && !"".equals(type)) {
              criterion += " t.credence_num_type = ? and ";
              parameters.add(type);
            }
            if (bookId != null && !"".equals(bookId)) {
              criterion += " t.book_id = ? and ";
              parameters.add(bookId);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            sql = sql + criterion;
            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult()!= null) {
              temp_minnun = new Integer(query.uniqueResult().toString());
            }
            return temp_minnun;
          }
        });
    return minNun;
  }

  /**
   * 出纳扎账--扎账
   * 
   * @author 郭婧平 2007-11-06 通过查询条件返回list的数量（fn302表）
   */
  public int getDocNumCanceldocNumCount(final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) {
    int docNumCancelCount = 0;
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select fnDocNumCancel.cancelcredenceid from FnDocNumCancel fnDocNumCancel  ";
        String criterion = "";
        if (officeCode != null) {
          criterion += " fnDocNumCancel.office = '" + officeCode + "' and ";
        } else {
          criterion += " fnDocNumCancel.office is null and ";
        }
        if (bizYearmonth != null) {
          criterion += " fnDocNumCancel.bizYearmonth = '" + bizYearmonth
              + "' and ";
        }
        if (credenceNumType != null) {
          criterion += " fnDocNumCancel.credenceNumType = '" + credenceNumType
              + "' and ";
        }
        if (bookId != null) {
          criterion += " fnDocNumCancel.bookId = '" + bookId + "' and ";
        }
        if (criterion.length() != 0)
          criterion = " where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query0 = session.createQuery(hql);
        List returnlist = (List) query0.list();
        return returnlist;
      }
    });
    if (list != null) {
      docNumCancelCount = list.size();
    }
    return docNumCancelCount;
  }
}
