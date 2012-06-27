package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.dto.SubjectDTO;
import org.xpup.security.common.domain.MuRelation;

public class MuRelationDAO extends HibernateDaoSupport {

  public List queryByCriterions(final Serializable menuItemId,
      final Serializable userId) {

    List muRelations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from MuRelation muRelation where muRelation.user.id = :userId and muRelation.menuItem.id = :menuItemId";
            return session.createQuery(hql).setParameter("userId", userId)
                .setParameter("menuItemId", menuItemId).list();
          }
        });

    return muRelations;
  }

  public Serializable insert(MuRelation muRelation) {
    return getHibernateTemplate().save(muRelation);
  }

  public void deleteAllByUserId(final Serializable userId) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete MuRelation muRelation where muRelation.user.id = :userId";
        session.createQuery(hql).setParameter("userId", userId).executeUpdate();
        return null;
      }
    });
  }


  /**
   * 得到ca105,ca106中所有有“财务报表”节点且用户账套权限为当前账套的用户ID
   * @param securityInfo
   * @return
   */
  public List getUserIdByMenuId_WL(final String menuid,final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " select distinct(user_id) from (select t.user_id from ca105 t, bb113 b, ca101 c "+
                     " where t.user_id = c.id " +
                     " and b.username = c.username "+
                     " and b.book_id = ? "+
                     " and t.menuitem_id = ? "+
                     " union all "+
                     " select ca.user_id from ca106 t, bb113 b, ca101 c, ca103 ca "+
                     " where t.role_id = ca.role_id "+
                     " and ca.user_id = c.id "+
                     " and c.username = b.username "+
                     " and b.book_id = ? "+
                     " and t.menuitem_id = ? )";
        Query query = session.createSQLQuery(hql);
        query.setString(0, securityInfo.getBookId());
        query.setString(1, menuid);
        query.setString(2, securityInfo.getBookId());
        query.setString(3, menuid);
        if(query.list()==null){
          return new ArrayList();
        }else{
          return query.list();
        }
      }
    });
    return list;
  }

}
