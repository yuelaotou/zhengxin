package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndOrg;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndOrg;
import org.xpup.hafmis.orgstrct.dto.OrgDto;

public class RelaRoleAndOrgDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaRoleAndOrg queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaRoleAndOrg) getHibernateTemplate().get(RelaRoleAndOrg.class,id);    
  }
  /**
   * 插入记录
   * @param relaRoleAndOrg
   * @return
   */
  public Serializable insert(RelaRoleAndOrg relaRoleAndOrg){
    Serializable id = null;
    try{    
    Validate.notNull(relaRoleAndOrg);
    id = getHibernateTemplate().save(relaRoleAndOrg);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param relaRoleAndOrg
   */
  public void delete(RelaRoleAndOrg relaRoleAndOrg){
    Validate.notNull(relaRoleAndOrg);
    getHibernateTemplate().delete(relaRoleAndOrg);
  }
  /**
   * 查询角色下的单位
   * @param rolename
   * @return
   */
 public List queryRoleOrg(final String roleid){
   List list = null;
   list= getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = "select a.org_id as orgid,d.name as orgname,a.office as officeid," +
                " b.name as officename,a.coll_bank_id as bankid,c.coll_bank_name as bankname " +
                " from bb107 a,bb101 b ,bb105 c ,ba001 d,aa001 e,ca102 f " +
                " where c.status = 1 and  a.org_id=e.id and e.orginfo_id=d.id and a.office=b.id and " +
                " a.coll_bank_id=c.coll_bank_id and a.role=f.id and f.id='"+roleid+"' order by officeid,bankid";   
             Query query=session.createSQLQuery(hql);     
             List list=new ArrayList();
             Iterator it=query.list().iterator();    
             Object obj[]=null;
             while(it.hasNext()){
               obj=(Object[])it.next();    
               if(obj[0]!=null){
                 OrgDto orgDto=new OrgDto();
                 orgDto.setOrgid(obj[0].toString());
                 orgDto.setOrgname(obj[1].toString());
                 orgDto.setOfficeid(obj[2].toString());
                 orgDto.setOfficename(obj[3].toString());
                 orgDto.setBankid(obj[4].toString());
                 orgDto.setBankname(obj[5].toString());
                 list.add(orgDto);
               }
             }
             return list;
         }
       });
   return list;
 }
 /**
  * 未分配的单位
  * @param rolename
  * @return
  */
 public List querySpareRoleOrg(final String roleid){
   List list = null;
   list= getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql ="select a.id as orgid ,d.name as orgname,d.officecode as officeid," +
                " b.name as officename ,d.collection_bank_id as bankid,c.coll_bank_name as bankname " +
                " from aa001 a,bb101 b ,bb105 c ,ba001 d " +
                " where c.status=1 and a.orginfo_id=d.id and d.officecode=b.id and d.collection_bank_id=c.coll_bank_id" +
                " minus" +
                " select e.id as orgid,d.name as orgname,a.office as officeid,b.name as officename, " +
                " a.coll_bank_id as bankid,c.coll_bank_name as bankname " +
                " from bb107 a,bb101 b ,bb105 c ,ba001 d,aa001 e,ca102 f " +
                " where c.status=1 and a.org_id=e.id and e.orginfo_id=d.id and a.office=b.id and " +
                " a.coll_bank_id=c.coll_bank_id and a.role=f.id and f.id='"+roleid+"' order by officeid,bankid";   
             Query query=session.createSQLQuery(hql);     
             List list=new ArrayList();
             Iterator it=query.list().iterator();    
             Object obj[]=null;
             while(it.hasNext()){
               obj=(Object[])it.next();    
               if(obj[0]!=null){
                 OrgDto orgDto=new OrgDto();
                 orgDto.setOrgid(obj[0].toString());
                 orgDto.setOrgname(obj[1].toString());
                 orgDto.setOfficeid(obj[2].toString());
                 orgDto.setOfficename(obj[3].toString());
                 orgDto.setBankid(obj[4].toString());
                 orgDto.setBankname(obj[5].toString());
                 list.add(orgDto);
               }
             }
             return list;
         }
       });
   return list;
 }
 /**
  * 根据角色id和单位id查询
  * @param roleid
  * @param orgid
  * @return
  */
 public RelaRoleAndOrg queryByRoleOrg(final String roleid,final String orgid){
   RelaRoleAndOrg relaRoleAndOrg = null;
   relaRoleAndOrg = (RelaRoleAndOrg)getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = 
             " from RelaRoleAndOrg relaRoleAndOrg where relaRoleAndOrg.role='"+roleid+"'" +
                 " and relaRoleAndOrg.orgId ='"+orgid+"'";   

             Query query=session.createQuery(hql);     
             return query.uniqueResult();
         }
       });
   if(relaRoleAndOrg == null){
     relaRoleAndOrg = new RelaRoleAndOrg();
   }
   return relaRoleAndOrg;
 }
}
