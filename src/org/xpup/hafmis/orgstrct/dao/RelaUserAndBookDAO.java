package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndBook;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.dto.UserAssignBookDTO;

public class RelaUserAndBookDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaUserAndBook queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaUserAndBook) getHibernateTemplate().get(RelaUserAndBook.class,id);    
  }
  /**
   * 插入记录
   * @param relaUserAndBook
   * @return
   */
  public Serializable insert(RelaUserAndBook relaUserAndBook){
    Serializable id = null;
    try{    
    Validate.notNull(relaUserAndBook);
    id = getHibernateTemplate().save(relaUserAndBook);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param RelaUserAndBook
   */
  public void delete(RelaUserAndBook relaUserAndBook){
    Validate.notNull(relaUserAndBook);
    getHibernateTemplate().delete(relaUserAndBook);
  }
  /**
   * 根据用户名称查询所属的账套信息
   * @param username
   * @return
   */
  public List queryBookByUsername(final String username){
    List relaRoleAndOffice = null;
    relaRoleAndOffice = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113 where fn101.book_id = bb113.book_id "+
              " and bb113.username = '"+username+"' "+
              " union "+
              " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113, BB112 bb112 where fn101.book_id = bb113.book_id "+
              " and bb112.subusername = bb113.username "+
              " and bb112.username = '"+username+"' ";

              Query query=session.createSQLQuery(hql);  
              List list=new ArrayList();
              Iterator it=query.list().iterator();    
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();    
                if(obj[0]!=null){
                  UserAssignBookDTO userAssignBookDTO=new UserAssignBookDTO();
                  userAssignBookDTO.setBookid(obj[0].toString());
                  userAssignBookDTO.setBookname(obj[1].toString());
                  list.add(userAssignBookDTO);
                }
              }
              return list;
          }
        });
    return relaRoleAndOffice;
  }
  /**
   * 尚未分配的账套
   * @param username
   * @return
   */
  public List querySpareBookByUsername(final String username,final SecurityInfo securityInfo,final boolean b){
    List list=null;
    list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = "";
              if(b){
                hql = " select distinct a.book_id,a.book_name from FN101 a ";
              }else{
                hql= " select a.book_id,a.book_name from ca116 a where a.username='"+securityInfo.getUserName()+"' ";
              }
              hql+= " minus " +
                  " select * from ("+
                  " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113 where fn101.book_id = bb113.book_id "+
                  " and bb113.username = '"+username+"' "+
                  " union "+
                  " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113, BB112 bb112 where fn101.book_id = bb113.book_id "+
                  " and bb112.subusername = bb113.username "+
                  " and bb112.username = '"+username+"' )";

              Query query=session.createSQLQuery(hql);     
              List list=new ArrayList();
              Iterator it=query.list().iterator();    
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();    
                if(obj[0]!=null){
                  UserAssignBookDTO userAssignBookDTO=new UserAssignBookDTO();
                  userAssignBookDTO.setBookid(obj[0].toString());
                  userAssignBookDTO.setBookname(obj[1].toString());
                  list.add(userAssignBookDTO);
                }
              }
              return list;
          }
        });
    return list;
  }
  /**
   * 查询账套
   * @param username
   * @param bookid
   * @return
   */
  public RelaUserAndBook queryUserOff(final String username,final String bookid){
    RelaUserAndBook relaUserAndBook = null;
    relaUserAndBook = (RelaUserAndBook)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
                " from RelaUserAndBook relaUserAndBook where relaUserAndBook.username='"+username+"' and relaUserAndBook.bookId='"+bookid+"'" ;   
              Query query=session.createQuery(hql);     
              return query.uniqueResult();
          }
        });
    if(relaUserAndBook == null){
      relaUserAndBook = new RelaUserAndBook();
    }
    return relaUserAndBook;
  }
  public String getFBizDate(final String bookId) {
    String bizDate = "";
    bizDate = (String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String sql =  " select max(b113.biz_date) from BB113 b113 where b113.book_id = " + bookId; 
              Query query=session.createSQLQuery(sql);     
              return query.uniqueResult();
          }
        });
    return bizDate;
  }
  public void updateFBizDate(final String bookId, final String bizDate) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement statement = conn.createStatement();
      String sql = "update BB113 t set t.biz_date = '" + bizDate
          + "' where t.book_id = " + bookId;
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
