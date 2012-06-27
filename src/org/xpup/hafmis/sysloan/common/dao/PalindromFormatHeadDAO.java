package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.Validate;
import java.sql.SQLException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.PalindromFormatHead;
import org.xpup.hafmis.sysloan.common.domain.entity.PalindromeFormatTail;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBankPara;
/** 
 * PL011 回文格式对应设置头表
 * @author Administrator
 *
 */
public class PalindromFormatHeadDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public PalindromFormatHead queryById(Serializable id) {
    Validate.notNull(id);
    return (PalindromFormatHead) getHibernateTemplate().get(PalindromFormatHead.class, id);
  }
  /**
   * 插入记录
   * 
   * @param assistantOrg
   * @return
   */
  public Serializable insert(PalindromFormatHead palindromFormatHead) {
    Serializable id = null;
    try {
      Validate.notNull(palindromFormatHead);
      id = getHibernateTemplate().save(palindromFormatHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 删除
   */
  public void delete(PalindromFormatHead palindromFormatHead){
    try{
      getHibernateTemplate().delete(palindromFormatHead);
    }catch(Exception a){
      a.printStackTrace();
    }
  }
  /**
   * 根据银行编号查询回文设置
   * @param id
   * @param index
   * @return
   */
  public String queryByBankId(final String id,final String index){
  String s = "";
   try {
      s = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select pft.formula from PalindromFormatHead pfh,PalindromeFormatTail pft where pfh.id=pft.flowHeadId and pfh.bankId=? and pft.centerSet=?";
              Query query = session.createQuery(hql);
              query.setParameter(0, id);
              query.setParameter(1, index);
              return query.uniqueResult();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

  return s;  
  }
  //根据银行ID 查询PL011
  public PalindromFormatHead queryPalindromFormatHeadByBankId(final String id){
    PalindromFormatHead palindromFormatHead = null;
     try {
       palindromFormatHead = (PalindromFormatHead) getHibernateTemplate().execute(
            new HibernateCallback() {

              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {

                String hql = "select pfh from PalindromFormatHead pfh where  pfh.bankId=? ";
                Query query = session.createQuery(hql);
                query.setParameter(0, id);
                return query.uniqueResult();
              }
            });

      } catch (Exception e) {
        e.printStackTrace();
      }

    return palindromFormatHead;  
    }
  /**
   * 
   * @param id
   * @return
   */
  public PalindromeFormatTail queryPalindromFormatTailByBankId(final Integer id,final String index){
    PalindromeFormatTail palindromeFormatTail = null;
     try {
       palindromeFormatTail = (PalindromeFormatTail) getHibernateTemplate().execute(
            new HibernateCallback() {

              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {

                String hql = "select pft from PalindromeFormatTail pft where  pft.flowHeadId=? and pft.centerSet=? ";
                Query query = session.createQuery(hql);
                query.setParameter(0, id);
                query.setParameter(1, index);
                return query.uniqueResult();
              }
            });

      } catch (Exception e) {
        e.printStackTrace();
      }

    return palindromeFormatTail;  
    }
}
