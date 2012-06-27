package org.xpup.hafmis.syscommon.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;
import org.xpup.security.common.domain.User;




public class HafOperateLogDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public HafOperateLog queryById(Serializable id) {
    Validate.notNull(id);
    return  (HafOperateLog) getHibernateTemplate().get(HafOperateLog.class,id);    
  }
  /**
   * 插入记录
   * @param demo
   * @return
   */
  public Serializable insert(HafOperateLog hafOperateLog){
    Serializable id = null;
    try{    
    Validate.notNull(hafOperateLog);
    id = getHibernateTemplate().save(hafOperateLog); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  
  public List queryHafoperatorLog(final String opmodle,final String oper,final String starttime,final String endtime,final String opaction,
      final String orderBy, final String order,final int start, final int pageSize,final int page,final SecurityInfo securityInfo) {
    List hafOperateLog = new ArrayList();
    hafOperateLog = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from HafOperateLog hafOperateLog ";
            Vector parameters = new Vector();
            String criterion = "";
            if (opmodle != null && !opmodle.equals("")) {
              criterion += "hafOperateLog.opModel = ?  and ";
              parameters.add(opmodle);
            }
            if (oper != null && !oper.equals("")) {
              criterion += "hafOperateLog.operator = ?  and ";
              parameters.add(oper);
            } else {
              criterion+=" hafOperateLog.operator in (";
              List operList = securityInfo.getUserList();
              User user = null;
              Iterator itr2 = operList.iterator();
              while (itr2.hasNext()) {
                user = (User) itr2.next();
                criterion+="'"+user.getUsername()+"',";
              }criterion = criterion.substring(0, criterion.lastIndexOf(","));
              criterion += ") and ";
            }

            if (starttime != null && !starttime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') >= ?  and ";
              parameters.add(starttime);
            }
            if (endtime != null && !endtime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') <= ?  and ";
              parameters.add(endtime);
            }
            if (opaction != null && !opaction.equals("")) {
              criterion += "hafOperateLog.opButton = ?  and ";
              parameters.add(opaction);
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            
            String ob = orderBy;
            if (ob == null)
              ob = " hafOperateLog.id ";

            String od = order;
            if (od == null)
              od = "DESC";
            
            hql = hql + criterion+"  order by " + ob + " " + od;
            Query query = session.createQuery(hql);
            
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }       
            return query.list();
          }
        });
    return hafOperateLog;
  }
  
  public List querySysLoanHafoperatorLog(final String opmodle,final String oper,final String starttime,final String endtime,final String opaction,
      final String orderBy, final String order,final int start, final int pageSize,final int page,final SecurityInfo securityInfo) {
    List hafOperateLog = new ArrayList();
    hafOperateLog = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from PlOperateLog plOperateLog ";
            Vector parameters = new Vector();
            String criterion = "";
            if (opmodle != null && !opmodle.equals("")) {
              criterion += "plOperateLog.opModel = ?  and ";
              parameters.add(opmodle);
            }
            if (oper != null && !oper.equals("")) {
              criterion += "plOperateLog.operator = ?  and ";
              parameters.add(oper);
            } else {
              criterion+=" plOperateLog.operator in (";
              List operList = securityInfo.getUserList();
              User user = null;
              Iterator itr2 = operList.iterator();
              while (itr2.hasNext()) {
                user = (User) itr2.next();
                criterion+="'"+user.getUsername()+"',";
              }criterion = criterion.substring(0, criterion.lastIndexOf(","));
              criterion += ") and ";
            }

            if (starttime != null && !starttime.equals("")) {
              criterion += "to_char(plOperateLog.opTime,'yyyymmdd') >= ?  and ";
              parameters.add(starttime);
            }
            if (endtime != null && !endtime.equals("")) {
              criterion += "to_char(plOperateLog.opTime,'yyyymmdd') <= ?  and ";
              parameters.add(endtime);
            }
            if (opaction != null && !opaction.equals("")) {
              criterion += "plOperateLog.opButton = ?  and ";
              parameters.add(opaction);
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            
            String ob = orderBy;
            if (ob == null)
              ob = " plOperateLog.id ";

            String od = order;
            if (od == null)
              od = "DESC";
            
            hql = hql + criterion+"  order by " + ob + " " + od;
            Query query = session.createQuery(hql);
            
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }       
            return query.list();
          }
        });
    return hafOperateLog;
  }
  
  public List queryHafoperatorLogCount(final String opmodle,final String oper,final String starttime,final String endtime,final String opaction,
      final SecurityInfo securityInfo) {
    List hafOperateLog = new ArrayList();
    hafOperateLog = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from HafOperateLog hafOperateLog ";
            Vector parameters = new Vector();
            String criterion = "";
            if (opmodle != null && !opmodle.equals("")) {
              criterion += "hafOperateLog.opModel = ?  and ";
              parameters.add(opmodle);
            }
            if (oper != null && !oper.equals("")) {
              criterion += "hafOperateLog.operator = ?  and ";
              parameters.add(oper);
            } else {
              criterion+=" hafOperateLog.operator in (";
              List operList = securityInfo.getUserList();
              User user = null;
              Iterator itr2 = operList.iterator();
              while (itr2.hasNext()) {
                user = (User) itr2.next();
                criterion+="'"+user.getUsername()+"',";
              }criterion = criterion.substring(0, criterion.lastIndexOf(","));
              criterion += ") and ";
            }

            if (starttime != null && !starttime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') >= ?  and ";
              parameters.add(starttime);
            }
            if (endtime != null && !endtime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') <= ?  and ";
              parameters.add(endtime);
            }
            if (opaction != null && !opaction.equals("")) {
              criterion += "hafOperateLog.opButton = ?  and ";
              parameters.add(opaction);
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    return hafOperateLog;
  }
  
  public List querySysLoanHafoperatorLogCount(final String opmodle,final String oper,final String starttime,final String endtime,final String opaction,
      final SecurityInfo securityInfo) {
    List hafOperateLog = new ArrayList();
    hafOperateLog = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from PlOperateLog plOperateLog ";
            Vector parameters = new Vector();
            String criterion = "";
            if (opmodle != null && !opmodle.equals("")) {
              criterion += "plOperateLog.opModel = ?  and ";
              parameters.add(opmodle);
            }
            if (oper != null && !oper.equals("")) {
              criterion += "plOperateLog.operator = ?  and ";
              parameters.add(oper);
            } else {
              criterion+=" plOperateLog.operator in (";
              List operList = securityInfo.getUserList();
              User user = null;
              Iterator itr2 = operList.iterator();
              while (itr2.hasNext()) {
                user = (User) itr2.next();
                criterion+="'"+user.getUsername()+"',";
              }criterion = criterion.substring(0, criterion.lastIndexOf(","));
              criterion += ") and ";
            }

            if (starttime != null && !starttime.equals("")) {
              criterion += "to_char(plOperateLog.opTime,'yyyymmdd') >= ?  and ";
              parameters.add(starttime);
            }
            if (endtime != null && !endtime.equals("")) {
              criterion += "to_char(plOperateLog.opTime,'yyyymmdd') <= ?  and ";
              parameters.add(endtime);
            }
            if (opaction != null && !opaction.equals("")) {
              criterion += "plOperateLog.opButton = ?  and ";
              parameters.add(opaction);
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    return hafOperateLog;
  }
  /**
  /**
   * 删除记录
   * @author 吴迪 20071214
   * @param id
   * @return
   */
  public void delete(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from HafOperateLog hafOperateLog where hafOperateLog.id=? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(id));
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void deleteFnTempTable(String type) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
      .openSession().connection();
      String sql = "delete from FN_TEMP_TABLE where FN_TEMP_TABLE.type='"+type+"'";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
