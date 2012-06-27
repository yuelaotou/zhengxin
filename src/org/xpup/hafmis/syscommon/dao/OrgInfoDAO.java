package org.xpup.hafmis.syscommon.dao;

import java.io.Serializable;
import java.sql.SQLException;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.dto.PastyearscontrasDTO;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;


public class OrgInfoDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgInfo findOrgInfoStatus(Serializable id){
    OrgInfo orgInfo = (OrgInfo)getHibernateTemplate().get(OrgInfo.class, id);
    if(orgInfo == null)
      return null;
    //每个单位都有开户的状态 ,所以这个地方一定不能为空 除非书数据库里面的数据是错误的
    if(!orgInfo.getOpenstatus().equals("2"))
      return null;
    return orgInfo;
  }
  public OrgInfo findOrgInfoStatus1(Serializable id){
    OrgInfo orgInfo = (OrgInfo)getHibernateTemplate().get(OrgInfo.class, id);
    if(orgInfo == null)
      return null;
    //每个单位都有开户的状态 ,所以这个地方一定不能为空 除非书数据库里面的数据是错误的
    if(orgInfo.getOpenstatus().equals("1"))
      return null;
    return orgInfo;
  }
  public OrgInfo queryById(Serializable id) {
    Validate.notNull(id);
    return  (OrgInfo) getHibernateTemplate().get(OrgInfo.class,id);    
  }
  /**
   * 插入记录
   * @param demo
   * @return
   */
  public Serializable insert(OrgInfo orgInfo){
    Serializable id = null;
    try{    
    Validate.notNull(orgInfo);
    id = getHibernateTemplate().save(orgInfo);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /*
   * 删除BA001信息
   */
    public void deleteOrgInfoByIdSL(Serializable id) {
      try {

        Validate.notNull(id);
        OrgInfo orgInfo = queryById(id);
        getHibernateTemplate().delete(orgInfo);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
   public List queryByPagination(final SecurityInfo securityInfo){
     List list = new ArrayList();
     try{
       list = getHibernateTemplate().executeFind(
           new HibernateCallback() {

             public Object doInHibernate(Session session)
                 throws HibernateException, SQLException {

               String hql = " from OrgInfo orgInfo where orgInfo.id "+ securityInfo.getGjjSecurityHqlSQL();
   
                 Query query = session.createQuery(hql);
               return query.list();
             }
           }

       );
     }catch(Exception e){
       e.printStackTrace();
     }
     return list;
   }
   /**
    * 于庆丰  根据办事处查询所在地区
    * @param office
    * @return
    */
   public List queryRagionList(final String office){
     List list = new ArrayList();
     try{
       list = getHibernateTemplate().executeFind(
         new HibernateCallback(){
           public Object doInHibernate(Session session)
           throws HibernateException, SQLException {
      
         String sql = "select distinct t.region from BA001 t where t.officecode = '"+office+"' ";

           Query query = session.createSQLQuery(sql);
 
         return query.list();
       }
     }

 );
     }catch(Exception e){
       e.printStackTrace();
     }
     return list;
   }
   /**
    * 于庆丰  COUNT 开户状态 != 1的记录
    * @param office
    * @param bank
    * @param orgCharacter
    * @param dept
    * @param ragion
    * @param startDate
    * @param endDate
    * @return
    */
   public int queryCountOrgOpen(final String office,final String bank,final String orgCharacter,
       final String dept,final String ragion,final String startDate,final String endDate){
     int count = 0;
     try{
       Integer countInteger = (Integer)getHibernateTemplate().execute(
         new HibernateCallback(){
           public Object doInHibernate(Session session)
           throws HibernateException, SQLException {
      
         String hql = "select count(orgInfo.id) from OrgInfo orgInfo ";
         Vector parameters = new Vector();
         String criterion = "";
         if(office != null && !"".equals(office)){
           criterion += " orgInfo.officecode = ? and ";
           parameters.add(office);
         }
         if(bank != null && !"".equals(bank)){
           criterion += " orgInfo.collectionBankId = ? and ";
           parameters.add(bank);
         }
         if(orgCharacter != null && !"".equals(orgCharacter)){
           criterion += " orgInfo.character = ? and ";
           parameters.add(orgCharacter);
         }
         if(dept != null && !"".equals(dept)){
           criterion += " orgInfo.deptInCharge = ? and ";
           parameters.add(dept);
         }
         if(ragion != null && !"".equals(ragion)){
           criterion += " orgInfo.region = ? and ";
           parameters.add(ragion);
         }
         if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
           criterion += " (orgInfo.openDate between ?  and  ? ) and ";
           parameters.add(startDate);
           parameters.add(endDate);
         }
         if (criterion.length() != 0) 
//           criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//               + securityInfo.getGjjSecurityHqlSQL()
//               + " and "
//               + criterion.substring(0, criterion.lastIndexOf("and"));
           criterion = "where orgInfo.openstatus != '1' and "
             + criterion.substring(0, criterion.lastIndexOf("and"));

           hql = hql + criterion ;
           Query query = session.createQuery(hql);
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }
           Integer countTemp=new Integer(0);
           Iterator it=query.iterate();          
           if(it.hasNext()){
             countTemp=(Integer)it.next();
           }
           return countTemp;
       }
     });
       count = countInteger.intValue();
     }catch(Exception e){
       e.printStackTrace();
     }
     return count;
   }
}
