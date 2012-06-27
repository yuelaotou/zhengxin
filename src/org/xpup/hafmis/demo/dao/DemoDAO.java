
package org.xpup.hafmis.demo.dao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.demo.domain.entity.Demo;

/**
 * 
 * @author 刘洋
 *2007-5-31
 */
public class DemoDAO extends HibernateDaoSupport{
  
  /**
   * 删除单个记录
   * @param demo
   */
  public void delete(Demo demo){
    Validate.notNull(demo);
    getHibernateTemplate().delete(demo);
  }
  /**
   * 删除list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public Demo queryById(Integer id) {
    Validate.notNull(id);
    return  (Demo) getHibernateTemplate().get(Demo.class,id);    
  }
  /**
   * 插入记录
   * @param demo
   * @return
   */
  public Serializable insert(Demo demo){
    Serializable id = null;
    try{    
    Validate.notNull(demo);
    id = getHibernateTemplate().save(demo);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  
  
  /**
   * 根据条件查询demo记录
   * @param id
   * @param name
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception 
   * @throws NumberFormatException 
   */
  public List queryDemoListByCriterions(final String id, final String name,
      final String orderBy, final String order, final int start,
      final int pageSize,final int page) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from Demo demo  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (id != null&&!id.equals("")) {
                criterion += "demo.id = ?  and ";
                parameters.add(new Integer(id));
              }
              
              if (name != null&&!name.equals("")) {
                criterion += "demo.name = ?  and ";
                parameters.add(name);
              }

              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " demo.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion + "order by " + ob + " " + order;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);     
              List queryList=query.list();
              
              if(queryList==null||queryList.size()==0){           
                query.setFirstResult(pageSize*(page-2));
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                queryList=query.list();
              }
              return queryList;
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  
  /**
   * 查询demo所有记录
   * @param id
   * @param name
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception 
   * @throws NumberFormatException 
   */
  
  public List queryDemoList(final String id, final String name,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from Demo demo  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (id != null&&!id.equals("")) {
                criterion += "demo.id = ?  and ";
                parameters.add(new Integer(id));
              }
              
              if (name != null&&!name.equals("")) {
                criterion += "demo.name = ?  and ";
                parameters.add(name);
              }

              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " demo.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion + "order by " + ob + " " + order;
              
              Query query = session.createQuery(hql);
             
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }           
              return query.list();
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  
  /**
   * 根据条件查询demo记录数
   * @param id
   * @param name
   * @return
   */
  public int queryDemoCountByCriterions(final String id, final String name) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "from Demo demo  ";
              Vector parameters = new Vector();
              String criterion = "";


              if (id != null&&!id.equals("")) {
                criterion += "demo.id = ?  and ";
                parameters.add(new Integer(id));
              }
              
              if (name != null&&!name.equals("")) {
                criterion += "demo.name = ?  and ";
                parameters.add(name);
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
           
              hql = hql + criterion ;
              session.clear();
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
 }
  
  public int queryD(final String id, final String name) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "from Demo demo  ";
              Query query = session.createQuery(hql);
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
 }
  
  
}
