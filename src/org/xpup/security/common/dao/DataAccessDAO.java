package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.exception.IllegalDuplicationException;
import org.xpup.security.common.domain.DataAccess;

public class DataAccessDAO extends HibernateDaoSupport {

  public DataAccess queryById(Serializable id) {
    return (DataAccess) super.getHibernateTemplate().get(DataAccess.class, id);
  }

  public Serializable insert(DataAccess dataAccess) throws BusinessException {
    Validate.notNull(dataAccess, "参数dataAccess不能为空！");
    Validate.notNull(dataAccess.getName(), "dataAccess.getName()不能为空！");
    Validate.notNull(dataAccess.getInnerName(),
        "dataAccess.getInnerName()不能为空！");

    int count = getHibernateTemplate()
        .find(
            "from DataAccess dataAccess where lower(dataAccess.innerName) = ? or lower(dataAccess.name) = ?",
            new Object[] { dataAccess.getInnerName().toLowerCase(),
                dataAccess.getName().toLowerCase().trim() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("数据资源的名称或内部名称出现重复，请使用其他名称！");
    }
    return super.getHibernateTemplate().save(dataAccess);
  }

  public void deleteById(Serializable id) throws BusinessException {
    DataAccess da = queryById(id);
    if (da == null)
      throw new EntityNotFoundException("数据权限不存在，或已经被删除。");
    super.getHibernateTemplate().delete(da);
  }

  public void checkBeforeUpdate(DataAccess dataAccess)
      throws IllegalDuplicationException {
    Validate.notNull(dataAccess, "参数dataAccess不能为空！");
    Validate.notNull(dataAccess.getId(), "dataAccess.getId()不能为空！");
    Validate.notNull(dataAccess.getName(), "dataAccess.getName()不能为空！");
    Validate.notNull(dataAccess.getInnerName(), "dataAccess.getSysName()不能为空！");

    int count = getHibernateTemplate()
        .find(
            "from DataAccess dataAccess where (lower(dataAccess.innerName) = ? or lower(dataAccess.name) = ?) and dataAccess.id <> ?",
            new Object[] { dataAccess.getInnerName().toLowerCase(),
                dataAccess.getName().trim(), dataAccess.getId() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("数据资源的名称或内部名称出现重复，请使用其他名称！");
    }
  }

  public List queryUnusedByUserId(final Serializable userId) {
    List unuseds = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Query query = session.getNamedQuery("unusedDataAccessesDu");
        query.setParameter("userId", userId);
        return query.list();
      }
    });
    return unuseds;
  }

  public List queryUnusedByRoleId(final Serializable roleId) {
    List unuseds = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Query query = session.getNamedQuery("unusedDataAccessesDr");
        query.setParameter("roleId", roleId);
        return query.list();
      }
    });
    return unuseds;
  }

  public List queryByCriterions(final String name, final String innerName,
      final String orderBy, final OrderEnum order, final int start,
      final int pageSize) {

    Validate.notNull(orderBy, "参数orderBy不能为空！");
    Validate.notNull(order, "参数order不能为空！");
    Validate
        .isTrue(
            orderBy.matches("dataAccess\\.(id|name|innerName)$"),
            "参数orderBy("
                + orderBy
                + ")不符合要求！它必须是dataAccess.id, dataAccess.name, dataAccess.innerName中的一个。");

    List operations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from DataAccess dataAccess ";
            Vector parameters = new Vector();
            String criterion = "";

            if (name != null) {
              criterion += "dataAccess.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (innerName != null) {
              criterion += "dataAccess.innerName like ? escape '/' and ";
              parameters.add("%" + innerName + "%");
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion + "order by " + orderBy + " "
                + order.getName();

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);

            return query.list();
          }
        });
    return operations;
  }

  public int queryCountByCriterions(final String name, final String innerName) {

    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(dataAccess.id) from DataAccess dataAccess ";
            Vector parameters = new Vector();
            String criterion = "";

            if (name != null) {
              criterion += "dataAccess.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (innerName != null) {
              criterion += "dataAccess.innerName like ? escape '/' and ";
              parameters.add("%" + innerName + "%");
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

    return count.intValue();
  }
}
