package org.xpup.hafmis.orgstrct.daoDW;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.domain.OrgUnitProperty;
import org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum;

public class OrgUnitPropertyDAODW extends HibernateDaoSupport {

  public List queryByCriterions(final Serializable orgUnitId,
      final PropertyTypeEnum type) {

    List props = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OrgUnitProperty oup ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgUnitId != null) {
          criterion += "oup.organizationUnit.id = ? and ";
          parameters.add(orgUnitId);
        }

        if (type != null) {
          criterion += "oup.ouptItem.type = ? and ";
          parameters.add(new Integer(type.getValue()));
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
    return props;
  }

  public Serializable insert(OrgUnitProperty orgUnitProperty) {
    return getHibernateTemplate().save(orgUnitProperty);
  }

  public void deleteByOrgUnitId(final Serializable orgUnitId) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete OrgUnitProperty oup where oup.organizationUnit.id = :orgUnitId";
        session.createQuery(hql).setParameter("orgUnitId", orgUnitId)
            .executeUpdate();
        return null;
      }
    });
  }

public Serializable insertDW(Serializable id,OrgUnitProperty orgUnitProperty) throws BusinessException {  
  Validate.notNull(orgUnitProperty, "参数orgUnitProperty不能为空！");
  Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
  PreparedStatement pre=null;
  String hql="insert into BB102 t (t.id,t.value,t.org_unit_id,t.oup_item_id) "
    +" values (?,?,?,?) "; 
  try {
    pre=conn.prepareStatement(hql.toString());
    pre.setString(1, id.toString()); 
    pre.setString(2, orgUnitProperty.getValue());    
    pre.setString(3, orgUnitProperty.getOrganizationUnit().getId().toString());
    pre.setString(4, orgUnitProperty.getOuptItem().getId().toString());   
    pre.executeUpdate(); 
    } catch (SQLException e) {
      throw new BusinessException("数据操作错误");
    }
    return id;
  }

}
