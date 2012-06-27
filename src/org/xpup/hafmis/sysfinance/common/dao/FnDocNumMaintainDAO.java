package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumMaintain;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnDocNumCancel;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnDocNumMaintain;

public class FnDocNumMaintainDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public FnDocNumMaintain queryById(Serializable id) {
    Validate.notNull(id);
    return (FnDocNumMaintain) getHibernateTemplate().get(
        FnDocNumMaintain.class, id);
  }

  /**
   * 插入记录
   * 
   * @param fnDocNumMaintain
   * @return
   */
  public Serializable insert(FnDocNumMaintain fnDocNumMaintain) {
    Serializable id = null;
    try {
      Validate.notNull(fnDocNumMaintain);
      id = getHibernateTemplate().save(fnDocNumMaintain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 插入记录
   * 
   * @param docNumMaintain
   * @return
   */
  public Serializable insert(DocNumMaintain docNumMaintain) {
    Serializable id = null;
    try {
      Validate.notNull(docNumMaintain);
      id = getHibernateTemplate().save(docNumMaintain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * @param officeCode 办事处
   * @param bizYearmonth 凭证年月
   * @param credenceNumType 凭证类型
   * @param bookId 凭证类型
   * @return
   * @throws Exception
   * @throws BusinessException
   */

  public String getFnDocNumdocNum(String officeCode, String bizYearmonth,
      String credenceNumType, String bookId) throws Exception,
      BusinessException {
    String docNumdocNum = "";
    String docNumCanceldocNum = "";
    FnDocNumCancel fnDocNumCancel = null;
    FnDocNumMaintain fnDocNumMaintain = null;
    int docnumLen = 0;
    int docnumLen1 = 0;
    int cha = 0;
    String addstring = "";
    try {
      docNumCanceldocNum = this.getDocNumCanceldocNum(officeCode, bizYearmonth,
          credenceNumType, bookId);
      if (docNumCanceldocNum != null) {
        docNumdocNum = docNumCanceldocNum;
        fnDocNumCancel = this.getCancleDocument(officeCode, bizYearmonth,
            docNumdocNum, credenceNumType, bookId);
        this.getHibernateTemplate().delete(fnDocNumCancel);
      } else {
        FnDocNumMaintain temp_fnDocNumMaintain = this.getDocumentNumber(
            officeCode, bizYearmonth, credenceNumType, bookId);
        docNumdocNum = String.valueOf((Integer.parseInt(temp_fnDocNumMaintain
            .getCredenceNum())) + 1);
        docnumLen = temp_fnDocNumMaintain.getCredenceNum().length();
        docnumLen1 = docNumdocNum.length();
        cha = docnumLen - docnumLen1;
        fnDocNumMaintain = temp_fnDocNumMaintain;
        if (cha > 0) {
          for (int i = 0; i < cha; i++) {
            addstring = addstring + "0";
          }
          docNumdocNum = addstring + docNumdocNum;
        }
        fnDocNumMaintain.setCredenceNum(docNumdocNum);
        this.getHibernateTemplate().update(fnDocNumMaintain);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("生成业务凭证号错误!");
    }
    return docNumdocNum;
  }

  /**
   * @param officeCode 办事处
   * @param bizYearmonth 凭证年月
   * @param credenceNumType 凭证类型
   * @param bookId 凭证类型
   * @return
   * @throws Exception
   * @throws BusinessException
   */

  public String getFnDocNumdocNum_WL(String officeCode, String bizYearmonth,
      String credenceNumType, String bookId) throws Exception,
      BusinessException {
    String docNumdocNum = "";
    String docNumCanceldocNum = "";
    // FnDocNumCancel fnDocNumCancel = null;
    // FnDocNumMaintain fnDocNumMaintain = null;
    int docnumLen = 0;
    int docnumLen1 = 0;
    int cha = 0;
    String addstring = "";
    try {
      docNumCanceldocNum = this.getDocNumCanceldocNum(officeCode, bizYearmonth,
          credenceNumType, bookId);
      if (docNumCanceldocNum != null) {
        docNumdocNum = docNumCanceldocNum;
        // fnDocNumCancel = this.getCancleDocument(officeCode,
        // bizYearmonth,docNumdocNum,credenceNumType,bookId);
        // this.getHibernateTemplate().delete(fnDocNumCancel);
      } else {
        FnDocNumMaintain temp_fnDocNumMaintain = this.getDocumentNumber(
            officeCode, bizYearmonth, credenceNumType, bookId);
        docNumdocNum = String.valueOf((Integer.parseInt(temp_fnDocNumMaintain
            .getCredenceNum())) + 1);
        docnumLen = temp_fnDocNumMaintain.getCredenceNum().length();
        docnumLen1 = docNumdocNum.length();
        cha = docnumLen - docnumLen1;
        // fnDocNumMaintain=temp_fnDocNumMaintain;
        if (cha > 0) {
          for (int i = 0; i < cha; i++) {
            addstring = addstring + "0";
          }
          docNumdocNum = addstring + docNumdocNum;
        }
        // fnDocNumMaintain.setCredenceNum(docNumdocNum);
        // this.getHibernateTemplate().update(fnDocNumMaintain);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("生成业务凭证号错误!");
    }
    return docNumdocNum;
  }

  /**
   * 查找作废的凭证号返回String 刘洋 officeCode 办事处,bizYearmonth 凭证年月,credenceNumType
   * 凭证类型,bookId 账套流水号
   */
  public String getDocNumCanceldocNum(final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) {
    String docNumCancel = "";
    docNumCancel = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String docNumCancel = "";
            String hql = "select min(fnDocNumCancel.cancelcredenceid) from FnDocNumCancel fnDocNumCancel  ";
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
              criterion += " fnDocNumCancel.credenceNumType = '"
                  + credenceNumType + "' and ";
            }
            if (bookId != null) {
              criterion += " fnDocNumCancel.bookId = '" + bookId + "' and ";
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query0 = session.createQuery(hql);
            docNumCancel = (String) query0.uniqueResult();
            return docNumCancel;
          }
        });
    return docNumCancel;
  }

  /**
   * 查找作废的凭证号返回对象（一条记录） 刘洋 officeCode 办事处,bizYearmonth 凭证年月,credenceNumType
   * 凭证类型,bookId 账套流水号,cancelcredenceid 作废凭证号
   */

  public FnDocNumCancel getCancleDocument(final String officeCode,
      final String bizYearmonth, final String cancelcredenceid,
      final String credenceNumType, final String bookId) {
    FnDocNumCancel fnDocNumCancel = null;
    fnDocNumCancel = (FnDocNumCancel) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            FnDocNumCancel fnDocNumCancel = null;
            String hql = "select fnDocNumCancel from FnDocNumCancel fnDocNumCancel  ";
            String criterion = "";
            if (officeCode != null) {
              criterion += " fnDocNumCancel.office ='" + officeCode + "' and ";
            } else {
              criterion += " fnDocNumCancel.office is null and ";
            }
            if (bizYearmonth != null) {
              criterion += " fnDocNumCancel.bizYearmonth = '" + bizYearmonth
                  + "' and ";
            }
            if (cancelcredenceid != null) {
              criterion += " fnDocNumCancel.cancelcredenceid= '"
                  + cancelcredenceid + "' and ";
            }
            if (credenceNumType != null) {
              criterion += " fnDocNumCancel.credenceNumType= '"
                  + credenceNumType + "' and ";
            }
            if (bookId != null) {
              criterion += " fnDocNumCancel.bookId = '" + bookId + "' and ";
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query0 = session.createQuery(hql);
            fnDocNumCancel = (FnDocNumCancel) query0.uniqueResult();
            return fnDocNumCancel;
          }
        });
    return fnDocNumCancel;
  }

  /**
   * 查找凭证号表可用的凭证号返回对象（一条记录） 刘洋 officeCode 办事处,bizYearmonth 凭证年月,credenceNumType
   * 凭证类型,bookId 账套流水号
   */
  public FnDocNumMaintain getDocumentNumber(final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) {
    FnDocNumMaintain fnDocNumMaintain = null;
    try {
      fnDocNumMaintain = (FnDocNumMaintain) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              FnDocNumMaintain temp_fnDocNumMaintain = null;
              String hql = "select fnDocNumMaintain from FnDocNumMaintain fnDocNumMaintain  ";
              String criterion = "";
              if (officeCode != null) {
                criterion += " fnDocNumMaintain.office ='" + officeCode
                    + "' and ";
              } else {
                criterion += " fnDocNumMaintain.office is null and ";
              }
              if (bizYearmonth != null) {
                criterion += " fnDocNumMaintain.bizYearmonth = '"
                    + bizYearmonth + "' and ";
              }
              if (credenceNumType != null) {
                criterion += " fnDocNumMaintain.credenceNumType = '"
                    + credenceNumType + "' and ";
              }
              if (bookId != null) {
                criterion += " fnDocNumMaintain.bookId = '" + bookId + "' and ";
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query0 = session.createQuery(hql);
              temp_fnDocNumMaintain = (FnDocNumMaintain) query0.uniqueResult();
              return temp_fnDocNumMaintain;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fnDocNumMaintain;
  }

  /**
   * 凭证过账-划账
   * 
   * @author wsh 求凭证号管理表 FN301中最大作废凭证好
   * @param bookId 账套
   * @return Integer
   */
  public Integer findCredenceclearMaxNum(final String office,
      final String type, final String bookId) {
    Integer minNun = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select max(t.credence_num) from fn301 t";
            String criterion = "";
            Integer temp_maxnun = new Integer(0);
            Vector parameters = new Vector();
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
              temp_maxnun = new Integer(query.uniqueResult().toString());
            }
            return temp_maxnun;
          }
        });
    return minNun;
  }

  /**
   * 出纳扎账--扎账
   * 
   * @author 郭婧平 2007-11-06 通过查询条件查出fn301中最大的凭证号
   */
  public String getDocumentNumberMax(final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) {
    String docNumMax = null;
    try {
      docNumMax = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select max(fnDocNumMaintain.credenceNum) from FnDocNumMaintain fnDocNumMaintain  ";
              String criterion = "";
              if (officeCode != null) {
                criterion += " fnDocNumMaintain.office ='" + officeCode
                    + "' and ";
              } else {
                criterion += " fnDocNumMaintain.office is null and ";
              }
              if (bizYearmonth != null) {
                criterion += " fnDocNumMaintain.bizYearmonth = '"
                    + bizYearmonth + "' and ";
              }
              if (credenceNumType != null) {
                criterion += " fnDocNumMaintain.credenceNumType = '"
                    + credenceNumType + "' and ";
              }
              if (bookId != null) {
                criterion += " fnDocNumMaintain.bookId = '" + bookId + "' and ";
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query0 = session.createQuery(hql);
              String rs = (String) query0.uniqueResult();
              return rs;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return docNumMax;
  }

}
