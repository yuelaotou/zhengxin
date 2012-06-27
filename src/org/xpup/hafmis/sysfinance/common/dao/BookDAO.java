package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysfinance.bookmng.createbook.dto.CreateBookDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.Book;

public class BookDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public Book queryById(Serializable id) {
    Validate.notNull(id);
    return (Book) getHibernateTemplate().get(
        Book.class, id);
  }

  /**
   * 插入记录
   * 
   * @param Book
   * @return
   */
  public Serializable insert(Book Book) {
    Serializable id = null;
    try {
      Validate.notNull(Book);
      id = getHibernateTemplate().save(Book);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  
  /**
   * 张列 掉用存储过程 插入 FN101 FN102 FN311 FN301 表信息
   * @param createBookDTO
   * @throws BusinessException
   * @throws HibernateException
   * @throws SQLException
   */
  public void createBook(CreateBookDTO createBookDTO)throws BusinessException, HibernateException, SQLException{
    try {
      Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
      CallableStatement cs=conn.prepareCall("{call FNCREATEBOOK(?,?,?,?,?,?,?,?,?,?,?,?)}");
      cs.setString(1, createBookDTO.getBookName());
      cs.setString(2, createBookDTO.getUserYearMonth());
      cs.setString(3, createBookDTO.getOpIp());
      cs.setString(4, createBookDTO.getOperator());
      cs.setString(5, createBookDTO.getParamValue());
      cs.setString(6, createBookDTO.getParamExplain1());
      cs.setString(7, createBookDTO.getParamExplain2());
      cs.setString(8, createBookDTO.getParamExplain3());
      cs.setString(9, createBookDTO.getParamExplain4());
      cs.setString(10, createBookDTO.getParamExplain5());
      cs.setString(11, createBookDTO.getParamExplain6());
      cs.setString(12, createBookDTO.getOffice());
      cs.execute();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * 张列 获得账套名称
   * 
   * @param bookId
   * @return String
   */
  public String getBookName(final String bookId) {
    Validate.notNull(bookId);
    String bookName = "";
    try {
      bookName = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f101.book_name from fn101 f101 where f101.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bookName;
  }
  
  /**
   * 张列 获得账套启用时间
   * 
   * @param bookId
   * @return String
   */
  public String getUseYearmonth(final String bookId) {
    Validate.notNull(bookId);
    String useYearmonth = "";
    try {
      useYearmonth = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f101.use_yearmonth from fn101 f101 where f101.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return useYearmonth;
  }
  
  /**
   * 更新FN101.USE_PERSON=操作员 FN101.BOOK_ST=1
   * @param bookId
   * 张列
   */
  public void updateBook(final String bookId,final String user){
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "update Book b set b.usePerson=?,b.bookSt=1 where b.bookId=?";
          Query query=session.createQuery(sql);
          query.setParameter(0, user);
          query.setParameter(1, bookId);
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  /**
   * 查询该账套的账套状态
   * 郭婧平 
   * @param bookId
   * @return String
   */
  public String getBookSt(final String bookId) {
    String bookSt = "";
    try {
      bookSt = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f101.book_st from fn101 f101 where f101.book_id=? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bookSt;
  }
}
