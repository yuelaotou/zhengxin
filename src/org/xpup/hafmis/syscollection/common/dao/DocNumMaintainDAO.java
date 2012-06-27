package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumCancel;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumMaintain;
import java.sql.SQLException;
import java.util.Vector;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * 凭证号维护
 * 
 * @author 李娟 2007-6-19
 */
public class DocNumMaintainDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public DocNumMaintain queryById(Serializable id) {
    Validate.notNull(id);
    return (DocNumMaintain) getHibernateTemplate()
        .get(DocNumMaintain.class, id);
  }
  /** 李鹏
   * 查询凭证号
   * @param officeid
   * @param yearmonth
   * @return DocNumMaintain
   */
  public DocNumMaintain querybydocnum(final String officeid,final String yearmonth) {  
    Validate.notNull(officeid);
    
    return (DocNumMaintain)getHibernateTemplate().execute( 
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from DocNumMaintain docNumMaintain  where "+
                         " docNumMaintain.officeCode=? and docNumMaintain.bizYearmonth=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0,officeid);
            query.setParameter(1,yearmonth);
            return query.uniqueResult();          
        }
        }); 
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
   * 到账确认-生成业务凭证号 
   * 
   * @author 吴洪涛 2007-6-26 docNum：凭证号 officeCode：办事处代码 bizYearmonth：业务年月
   */

  public String getDocNumdocNum(String officeCode, String bizYearmonth)
      throws Exception, BusinessException {
    String DocNumdocNum = "";
    String docNumCanceldocNum = "";
    DocNumCancel docNumCancel = null;
    DocNumMaintain docNumMaintain = null;
    int docnumLen = 0;
    int docnumLen1 = 0;
    int cha = 0;
    String addstring = "";
    try {
      docNumCanceldocNum = this.getDocNumCanceldocNum(officeCode, bizYearmonth);
      if (docNumCanceldocNum != null) {
        DocNumdocNum = docNumCanceldocNum;
        docNumCancel = this.getCancleDocument(officeCode, bizYearmonth,
            DocNumdocNum);
        this.getHibernateTemplate().delete(docNumCancel);
      } else {
        DocNumdocNum = String.valueOf((Integer.parseInt(this.getDocumentNumber(
            officeCode, bizYearmonth).getDocNum())) + 1);
        docnumLen = this.getDocumentNumber(officeCode, bizYearmonth)
            .getDocNum().length();
        docnumLen1 = DocNumdocNum.length();
        cha = docnumLen - docnumLen1;
        docNumMaintain = this.getDocumentNumber(officeCode, bizYearmonth);
        if (cha > 0) {
          for (int i = 0; i < cha; i++) {
            addstring = addstring + "0";
          }
          DocNumdocNum = addstring + DocNumdocNum;
        }
        docNumMaintain.setDocNum(DocNumdocNum);
        this.getHibernateTemplate().update(docNumMaintain);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("生成业务凭证号错误!");
    }
    return DocNumdocNum;
  }

  /**
   * 查找作废的凭证号返回String
   */
  public String getDocNumCanceldocNum(final String officeCode,
      final String bizYearmonth) {
    String docNumCancel = "";
    docNumCancel = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String docNumCancel = "";
            String hql = "select min(docNumCancel.docNum) from DocNumCancel docNumCancel  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (officeCode != null) {
              criterion += " docNumCancel.officeCode = ? and ";
              parameters.add(officeCode);
            }
            if (bizYearmonth != null) {
              criterion += " docNumCancel.bizYearmonth = ? and ";
              parameters.add(bizYearmonth);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            if (query0.list().size() > 0) {
              docNumCancel = (String) query0.uniqueResult();
            }
            return docNumCancel;
          }
        });
    return docNumCancel;
  }

  /**
   * 查找作废的凭证号返回对象（一条记录）
   */

  public DocNumCancel getCancleDocument(final String officeCode,
      final String bizYearmonth, final String DocumentNumber) {
    DocNumCancel docNumCancel = null;
    docNumCancel = (DocNumCancel) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            DocNumCancel docNumCancel = null;
            String hql = "select docNumCancel from DocNumCancel docNumCancel  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (officeCode != null) {
              criterion += " docNumCancel.officeCode = ? and ";
              parameters.add(officeCode);
            }
            if (bizYearmonth != null) {
              criterion += " docNumCancel.bizYearmonth = ? and ";
              parameters.add(bizYearmonth);
            }
            if (DocumentNumber != null) {
              criterion += " docNumCancel.docNum = ? and ";
              parameters.add(DocumentNumber);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            if (query0.list().size() > 0) {
              docNumCancel = (DocNumCancel) query0.uniqueResult();
            }
            return docNumCancel;
          }
        });
    return docNumCancel;
  }

  /**
   * 查找凭证号表可用的凭证号返回对象（一条记录）
   */
  public DocNumMaintain getDocumentNumber(final String officeCode,
      final String bizYearmonth) {
    DocNumMaintain docNumMaintain = null;
    docNumMaintain = (DocNumMaintain) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            DocNumMaintain docNumMaintain = null;
            String hql = "select docNumMaintain from DocNumMaintain docNumMaintain  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (officeCode != null) {
              criterion += " docNum.officeCode = ? and ";
              parameters.add(officeCode);
            }
            if (bizYearmonth != null) {
              criterion += " docNum.bizYearmonth = ? and ";
              parameters.add(bizYearmonth);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            if (query0.list().size() > 0) {
              docNumMaintain = (DocNumMaintain) query0.uniqueResult();
            }
            return docNumMaintain;
          }
        });
    return docNumMaintain;
  }

}
