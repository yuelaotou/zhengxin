package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndOffice;
import org.xpup.hafmis.orgstrct.dto.OrgDto;

public class RelaRoleAndOfficeDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaRoleAndOffice queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaRoleAndOffice) getHibernateTemplate().get(RelaRoleAndOffice.class,id);    
  }
  /**
   * 插入记录
   * @param relaRoleAndOffice
   * @return
   */
  public Serializable insert(RelaRoleAndOffice relaRoleAndOffice){
    Serializable id = null;
    try{    
    Validate.notNull(relaRoleAndOffice);
    id = getHibernateTemplate().save(relaRoleAndOffice);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param relaRoleAndOffice
   */
  public void delete(RelaRoleAndOffice relaRoleAndOffice){
    Validate.notNull(relaRoleAndOffice);
    getHibernateTemplate().delete(relaRoleAndOffice);
  }
  /**
   * 根据角色ID和办事处ID查询
   * @param roleid
   * @param office
   * @return
   */
  public RelaRoleAndOffice queryRoleOffice(final String roleid,final String office){
    RelaRoleAndOffice relaRoleAndOffice = null;
    relaRoleAndOffice = (RelaRoleAndOffice)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " from RelaRoleAndOffice relaRoleAndOffice where relaRoleAndOffice.roleid='"+roleid+"'" +
                  " and relaRoleAndOffice.office = '"+office+"'";   

              Query query=session.createQuery(hql);     
              return query.uniqueResult();
          }
        });
    if(relaRoleAndOffice == null){
      relaRoleAndOffice = new RelaRoleAndOffice();
    }
    return relaRoleAndOffice;
  }
  /**
   * 根据角色id查询办事处
   * @param roleid
   * @param office
   * @return
   */
  public List queryOfficeByRoleid(final String roleid){
    List relaRoleAndOffice = null;
    relaRoleAndOffice = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " select organizationUnit from RelaRoleAndOffice relaRoleAndOffice,OrganizationUnit organizationUnit " +
              " where organizationUnit.type='2' and relaRoleAndOffice.office =organizationUnit.id and relaRoleAndOffice.roleid='"+roleid+"'" ;   

              Query query=session.createQuery(hql);     
              return query.list();
          }
        });
    return relaRoleAndOffice;
  }
  /**
   * 尚未分配的办事处
   * @param roleid
   * @return
   */
  public List querySpareOfficeByRoleid(final String roleid){
    List list=null;
    list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " select a.id,a.name from bb101 a where a.ou_type='2'" +
              " minus " +
              " select b.id,b.name from bb110 a,bb101 b where b.ou_type='2' and a.office=b.id and a.roleid='"+roleid+"'";   

              Query query=session.createSQLQuery(hql);     
              List list=new ArrayList();
              Iterator it=query.list().iterator();    
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();    
                if(obj[0]!=null){
                  OrgDto orgDto=new OrgDto();
                  orgDto.setOfficeid(obj[0].toString());
                  orgDto.setOfficename(obj[1].toString());
                  list.add(orgDto);
                }
              }
              return list;
          }
        });
    return list;
  }
}
