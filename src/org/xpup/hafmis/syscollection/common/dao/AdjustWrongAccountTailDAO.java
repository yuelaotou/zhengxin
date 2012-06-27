package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;

/**
 *错账调整尾表 
 * 
 *@author 李娟
 *2007-6-19
 */
public class AdjustWrongAccountTailDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public AdjustWrongAccountTail queryById(Integer id) { 
    Validate.notNull(id);
    return  (AdjustWrongAccountTail) getHibernateTemplate().get(AdjustWrongAccountTail.class,id);    
  }
  /**
   * 根据主键删除
   * @param id
   * @return
   */
  public void deleteById(Integer id) {
    try {
      Validate.notNull(id);
      AdjustWrongAccountTail adjustWrongAccountTail = queryById(id);
      getHibernateTemplate().delete(adjustWrongAccountTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  /**
   * 插入记录
   * @param adjustWrongAccountTail
   * @return
   */
  public Serializable insert(AdjustWrongAccountTail adjustWrongAccountTail){
    Serializable id = null;
    try{    
    Validate.notNull(adjustWrongAccountTail);
    id = getHibernateTemplate().save(adjustWrongAccountTail);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  
  /**
   * 根据错账调整头表ID查询出错账调整尾表相应员工列表
   * @param AdjustWrongAccountHead id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryAdjustWrongAccountTailListByCriterions(final String id,
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
              String hql = " from AdjustWrongAccountTail adjustWrongAccountTail  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += "adjustWrongAccountTail.adjustWrongAccountHead.id = ?  and ";
            //    parameters.add(new Integer(id));
                parameters.add(new Integer(id));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              String ob = orderBy;
              if (ob == null)
                ob = " adjustWrongAccountTail.empId ";
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
   * 根据错账调整头表ID查询出错账调整尾表相应员工列表(所有)
   * @param AdjustWrongAccountHead id
   * @return List
   */
  public List queryAdjustWrongAccountTailAllListByCriterions(final String id) throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from AdjustWrongAccountTail adjustWrongAccountTail where adjustWrongAccountTail.adjustWrongAccountHead.id = ? ";
              Query query = session.createQuery(hql);
              query.setParameter(0,new Integer(id));
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
   * 查询记账人、复核人
   * @param bizId
   * @param bizType
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryPerson_jj(final String bizId,final String bizType) throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select aa101.checkperson,aa101.clearperson from AA101 aa101 where aa101.biz_id = ? and aa101.biz_type = ?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0,bizId);
              query.setParameter(1, bizType);
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
   * 根据条件错账调整头表ID查询记录数
   * @param id
   * @param name
   * @return
   */
  public int queryAdjustWrongAccountTailCountByCriterions(final String id) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "from AdjustWrongAccountTail adjustWrongAccountTail  ";
              Vector parameters = new Vector();
              String criterion = "";


              if (id != null&&!id.equals("")) {
                criterion += "adjustWrongAccountTail.adjustWrongAccountHead.id = ?  and ";
                parameters.add(new Integer(id));
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
  
  /**
   * 根据错帐流水id和empId返回AdjustWrongAccountTail
   * @param 错帐流水id
   * @param empId 员工
   * @return AdjustWrongAccountTail
   */
  public AdjustWrongAccountTail queryByWrongId(final String wrongId,final String empId) {
    return (AdjustWrongAccountTail)getHibernateTemplate().execute( 
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from AdjustWrongAccountTail adjustWrongAccountTail  where "+
                         "adjustWrongAccountTail.adjustWrongAccountHead.id=? and adjustWrongAccountTail.empId=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0,new Integer(wrongId));
            query.setParameter(1,new Integer(empId));
            return query.uniqueResult();          
        }
        }); 
  }
  
  /**
   * 求该单位下总的调整金额
   * @param AdjustWrongAccountHead id
   * @return List
   */
  public BigDecimal queryAdjustWrongAccountTailTotalMoneyByCriterions(final String id) throws NumberFormatException, Exception {
    BigDecimal bigDecimal=new BigDecimal(0.00);
    try {
      bigDecimal = (BigDecimal)getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(adjustMoney)  from AdjustWrongAccountTail adjustWrongAccountTail where adjustWrongAccountTail.adjustWrongAccountHead.id = ? ";
              Query query = session.createQuery(hql);
              query.setParameter(0,new Integer(id));
              return query.uniqueResult();
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bigDecimal;
  }
  
}
