package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumCancel;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumMaintain;
import org.xpup.hafmis.sysloan.common.domain.entity.PlDocNumCancel;
import org.xpup.hafmis.sysloan.common.domain.entity.PlDocNumMaintain;
/**
 * 凭证号管理表PL220
 */
public class PlDocNumMaintainDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public PlDocNumMaintain queryById(Serializable id) {  
    Validate.notNull(id);
    return  (PlDocNumMaintain) getHibernateTemplate().get(PlDocNumMaintain.class,id);    
  }

  /**
   * 插入记录
   * @param PlDocNumMaintain
   * @return
   */
  public Serializable insert(PlDocNumMaintain plDocNumMaintain){
    Serializable id = null;
    try{    
    Validate.notNull(plDocNumMaintain);
    id = getHibernateTemplate().save(plDocNumMaintain);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
/**
 * 生成凭证号
 * @param officeCode 办事处编号
 * @param bizYearmonth 会计年月
 * @return
 * @throws Exception
 * @throws BusinessException
 */
  public String getDocNumdocNum(String officeCode, String bizYearmonth)
      throws Exception, BusinessException {
    String DocNumdocNum = "";
    String docNumCanceldocNum = "";
    PlDocNumCancel  plDocNumCancel = null;
    PlDocNumMaintain  plDocNumMaintain = null;
    int docnumLen = 0;
    int docnumLen1 = 0;
    int cha = 0;
    String addstring = "";
    try {
      docNumCanceldocNum = this.getDocNumCanceldocNum(officeCode, bizYearmonth);
      if (docNumCanceldocNum != null) {
        DocNumdocNum = docNumCanceldocNum;
        plDocNumCancel = this.getCancleDocument(officeCode, bizYearmonth,
            DocNumdocNum);
        this.getHibernateTemplate().delete(plDocNumCancel);
      } else {
        DocNumdocNum = String.valueOf((Integer.parseInt(this.getDocumentNumber(
            officeCode, bizYearmonth).getCredenceNum())) + 1);
        docnumLen = this.getDocumentNumber(officeCode, bizYearmonth)
            .getCredenceNum().length();
        docnumLen1 = DocNumdocNum.length();
        cha = docnumLen - docnumLen1;
        plDocNumMaintain = this.getDocumentNumber(officeCode, bizYearmonth);
        if (cha > 0) {
          for (int i = 0; i < cha; i++) {
            addstring = addstring + "0";
          }
          DocNumdocNum = addstring + DocNumdocNum;
        }
        plDocNumMaintain.setCredenceNum(DocNumdocNum);
        this.getHibernateTemplate().update(plDocNumMaintain);
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
            String hql = "select min(plDocNumCancel.cancelcredenceid) from PlDocNumCancel  plDocNumCancel   ";
            Vector parameters = new Vector();
            String criterion = "";
            if (officeCode != null) {
              criterion += " plDocNumCancel.officeCode = ? and ";
              parameters.add(officeCode);
            }
            if (bizYearmonth != null) {
              criterion += " plDocNumCancel.yearMonth = ? and ";
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
   * 查找凭证号表可用的凭证号返回对象（一条记录）
   */
  public PlDocNumMaintain getDocumentNumber(final String officeCode,
      final String bizYearmonth) {
    PlDocNumMaintain  plDocNumMaintain = null;
    plDocNumMaintain = (PlDocNumMaintain) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            PlDocNumMaintain plDocNumMaintain = null;
            String hql = "select plDocNumMaintain from PlDocNumMaintain  plDocNumMaintain  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (officeCode != null) {
              criterion += " plDocNumMaintain.officeCode = ? and ";
              parameters.add(officeCode);
            }
            if (bizYearmonth != null) {
              criterion += " plDocNumMaintain.yeraMonth = ? and ";
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
              plDocNumMaintain = (PlDocNumMaintain) query0.uniqueResult();
            }
            return plDocNumMaintain;
          }
        });
    return plDocNumMaintain;
  }
  /**
   * 查找作废的凭证号返回对象（一条记录）
   */

  public PlDocNumCancel getCancleDocument(final String officeCode,
      final String bizYearmonth, final String DocumentNumber) {
    PlDocNumCancel  plDocNumCancel = null;
    plDocNumCancel = (PlDocNumCancel) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            PlDocNumCancel plDocNumCancel = null;
            String hql = "select plDocNumCancel from PlDocNumCancel  plDocNumCancel  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (officeCode != null) {
              criterion += " plDocNumCancel.officeCode = ? and ";
              parameters.add(officeCode);
            }
            if (bizYearmonth != null) {
              criterion += " plDocNumCancel.yearMonth = ? and ";
              parameters.add(bizYearmonth);
            }
            if (DocumentNumber != null) {
              criterion += " plDocNumCancel.cancelcredenceid = ? and ";
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
              plDocNumCancel = (PlDocNumCancel) query0.uniqueResult();
            }
            return plDocNumCancel;
          }
        });
    return plDocNumCancel;
  }
}
