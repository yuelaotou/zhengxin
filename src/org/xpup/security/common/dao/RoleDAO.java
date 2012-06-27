package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.exception.IllegalDuplicationException;
import org.xpup.security.common.domain.Role;

public class RoleDAO extends HibernateDaoSupport {

  public List queryByUserId(Serializable userId) {
    String hql = "select role from Role role inner join role.users user where user.id = ?";
    return getHibernateTemplate().find(hql, userId);
  }

  public Serializable insert(Role role) throws BusinessException {

    Validate.notNull(role, "参数 role 不能为空");
    Validate.notNull(role.getId(), "role.getId()不能为空");

    int count = getHibernateTemplate().find(
        "from Role role where lower(role.name) = ?",
        new Object[] { role.getName().toLowerCase() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("角色名称［" + role.getName()
          + "］已经存在，角色名称必须唯一!");
    }
    return getHibernateTemplate().save(role);
  }

  public Role queryById(Serializable id) {
    Validate.notNull(id, "参数id不能为空。");
    Role role = (Role) getHibernateTemplate().get(Role.class, id);
    return role;
  }

  public void deleteById(Serializable id) throws BusinessException {
    Validate.notNull(id, "参数id不能为空。");
    Role role = queryById(id);
    if (role == null)
      throw new EntityNotFoundException("本角色已经被删除了");
    getHibernateTemplate().delete(role);
  }

  public void checkBeforeUpdate(Role role) throws BusinessException {
    Validate.notNull(role, "参数 role 不能为空");
    Validate.notNull(role.getId(), "role.getId()不能为空");

    int count = getHibernateTemplate().find(
        "from Role role where lower(role.name) = ? and role.id <> ?",
        new Object[] { role.getName().toLowerCase(), role.getId() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("角色名称［" + role.getName()
          + "］已经存在，角色名称必须唯一!");
    }
  }
  /**
   * 查询角色list
   * @return
   */
  public List queryRoleList(){
    List list=null;
    list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = " from Role role ";   

              Query query=session.createQuery(hql);     
              return query.list();
          }
        });
    return list;
  }
  /**
   * 查询应用角色的用户list
   * @return
   */
  public List queryRelationByhafRoleId(final String hafRoleId){
    List list=new ArrayList();
    list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = " select t.user_id from ca103 t where t.role_id= ?";   
              Query query=session.createSQLQuery(hql);   
              query.setString(0, hafRoleId);
              return query.list();
          }
        });
    return list;
  }
  
}
