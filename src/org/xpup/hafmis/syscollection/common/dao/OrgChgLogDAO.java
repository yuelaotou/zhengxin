package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLog;


public class OrgChgLogDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgChgLog queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgChgLog) getHibernateTemplate().get(OrgChgLog.class,id);    
  }
  /**
   * 插入记录
   * @param orgChgLog
   * @return
   */
  public Serializable insert(OrgChgLog orgChgLog){
    Serializable id = null;
    try{    
    Validate.notNull(orgChgLog);
    id = getHibernateTemplate().save(orgChgLog); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /*
   * 查询变更日志列表
   */
  public List queryOrgChgSL(final String id, final String name,
      final String chgType, final String orderBy, final String order,
      final int start, final int pageSize, final String temp_Type) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          
          String hql = "from OrgChgLog orgChgLog ";

          Vector parameters = new Vector();
          String criterion = ""; 
          if (id != null&&!id.equals("")) {
            criterion += "To_Char(orgChgLog.org.id) like ? and ";
            parameters.add("%" +id+ "%");
          }

          if (name != null&&!name.equals("")) {
            criterion += "orgChgLog.org.orgInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }
          
          if (chgType != null&&!chgType.equals("")) {
            criterion += "orgChgLog.chgType like ?  and ";
            parameters.add("%" + chgType + "%");
          }else {
            if (temp_Type == null) {
              criterion += "orgChgLog.chgType in('B','C','D') and ";
            }
          }
          
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgChgLog.org.id";

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
          List OrgChgLogList = query.list();
          session.clear();
          return OrgChgLogList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;

  }
 
 /*
  * 变更日志列表求和查询
  */
 public int queryCountOrgChgSL(final String id, final String name, final String chgType, final String temp_Type) {
   int count=0;
  try{ 
//   List list=new ArrayList();
     Integer count_ = (Integer)getHibernateTemplate().execute(
         new HibernateCallback() {

           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {

             String hql = "select count(orgChgLog.id)from OrgChgLog orgChgLog ";
             
             Vector parameters = new Vector();
             String criterion = "";
             if (id != null&&!id.equals("")) {
               criterion += "To_Char(orgChgLog.org.id) like ? escape '/' and ";
               parameters.add("%" +id+ "%");
             }

             if (name != null&&!name.equals("")) {
               criterion += "orgChgLog.org.orgInfo.name like ? escape '/' and ";
               parameters.add("%"+name+"%");
             }

             if (chgType != null&&!chgType.equals("")) {
               criterion += "orgChgLog.chgType like ?  and ";
               parameters.add("%" + chgType + "%");
             }else {
               if (temp_Type == null) {
                 criterion += "orgChgLog.chgType in('B','C','D') and ";
               }
             } 
          
             if (criterion.length() != 0)
               criterion = "where "
                   + criterion.substring(0, criterion.lastIndexOf("and"));
          
             hql = hql + criterion ;
//             System.out.println(hql);
             //session.clear();   
             Query query = session.createQuery(hql);
             for (int i = 0; i < parameters.size(); i++) {
               query.setParameter(i, parameters.get(i));
             }
             
//             System.out.println("fsdrqwerfsdfwerfrdfwer:::::"+query.uniqueResult());

             return query.uniqueResult();
           }
         }

     );
//     count=list.size();
     count= count_.intValue();
  }catch(Exception e){
    e.printStackTrace();
  }
   return count;
}
 /**
  * 单位ID查询变更
  */
 public OrgChgLog queryOrgChgSLById(final String id){
   OrgChgLog orgChgLog  = (OrgChgLog) getHibernateTemplate()
   .execute(new HibernateCallback() {
     public Object doInHibernate(Session session)
         throws HibernateException, SQLException {
       String hql = "from OrgChgLog orgChgLog ";

       Vector parameters = new Vector();
       String criterion = ""; 
       if (id != null&&!id.equals("")) {
         criterion += " orgChgLog.org.id = ? and ";
         parameters.add(new Integer(id));
       }
       if (id != null&&!id.equals("")) {
         criterion += " orgChgLog.id = (select max(orgChgLog.id) from OrgChgLog orgChgLog where orgChgLog.org.id = ?) and ";
         parameters.add(new Integer(id));
       }
       if (criterion.length() != 0)
         criterion = "where "
             + criterion.substring(0, criterion.lastIndexOf("and"));
       hql = hql + criterion;
       Query query = session.createQuery(hql);
       for (int i = 0; i < parameters.size(); i++) {
         query.setParameter(i, parameters.get(i));
       }
       return query.uniqueResult();
     }
   });
  return orgChgLog;
   
 }
}
