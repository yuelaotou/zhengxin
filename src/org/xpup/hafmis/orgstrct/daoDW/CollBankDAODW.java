package org.xpup.hafmis.orgstrct.daoDW;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.OrgDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTaPop2DTO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTbBankDTO;
import org.xpup.security.common.domain.Userslogincollbank;


public class CollBankDAODW extends HibernateDaoSupport {

  public void queryById(Integer id) {
    CollBank collBank = (CollBank) getHibernateTemplate().load(CollBank.class,
        id);
    Validate.notNull(collBank);
  }

  public Serializable insertCollBank(CollBank collBank) throws BusinessException {
    Serializable id = null;
    try {
      id=getHibernateTemplate().save(collBank);

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new BusinessException("插入失败！！！");
    }
    return id;
  }
  public void insertCollBankDW(String id,CollBank collBank) throws BusinessException {
    Validate.notNull(collBank, "参数organizationUnit不能为空！");
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    PreparedStatement pre=null;
    String hql="insert into BB105 (id, office,coll_bank_id, coll_bank_name, status) values (?, ?, ?, ?, ?) ";     
    try {
      pre=conn.prepareStatement(hql.toString());
      pre.setInt(1, Integer.parseInt(id)); 
      pre.setString(2, collBank.getOffice());    
      pre.setInt(3, collBank.getCollBankId().intValue());    
      pre.setString(4, collBank.getCollBankName());    
      pre.setInt(5, collBank.getStatus().intValue());       
      pre.executeUpdate(); 
      } catch (SQLException e) {
        throw new BusinessException("数据操作错误");
      }
  }
  /**
   * 通过银行查询办事处
   * 郭婧平
   * @param bank
   * @return
   */
 public String getOfficeByBank(final String bank){
   String officeCode="";
   officeCode = (String) getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = 
             " select collBank.office from CollBank collBank where collBank.collBankId = '"+bank+"' ";   
             Query query=session.createQuery(hql);     
             return query.uniqueResult();
         }
       });
   return officeCode;
 }
  
  /**
   * 得到办事处下归集银行
   * @param office
   * @return
   */
 public List getOfficeCollBankList(final String office){
   
   List officeCollBankList = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = 
             " from CollBank collBank where collBank.status = 1 and collBank.office='"+office+"' ";   
             Query query=session.createQuery(hql);     
             return query.list();
         }
       });
   return officeCollBankList;
 }
 /**
  * 得到办事处下归集银行(操作员权限)
  * @param office
  * @return
  */
 
public List getOfficeCollBankList_yu(final String office,final SecurityInfo securityInfo){
   
   List officeCollBankList = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = 
             " select collBank.collBankId,collBank.collBankName from CollBank collBank,Userslogincollbank userslogincollbank " +
             " where collBank.status = 1 and collBank.collBankId=userslogincollbank.collBankId " +
             " and collBank.office='"+office+"' "+" and userslogincollbank.username "+securityInfo.getDkUserSecurityHqlSQL();   
             Query query=session.createQuery(hql); 
             List tempList = new ArrayList();
             if(query.list().size() != 0){
               Iterator iterate = query.list().iterator();
               Object[] obj = null;
             while (iterate.hasNext()) {
               obj = (Object[]) iterate.next();
               CollBank collBank = new CollBank();
               if(obj[0]!=null){
                 collBank.setCollBankId(new Integer(obj[0].toString()));
               }
               if(obj[1]!=null){
                 collBank.setCollBankName(obj[1].toString());
               }
               tempList.add(collBank);
              }
             }
             return tempList;
         }
       });
   return officeCollBankList;
 }
 /**
  * 根据用户查询归集行
  * @param username
  * @return
  */
 public List getUserCollBankList(final String username){
   List banklist=null;
   banklist = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = 
             "select collBank from RelaUserAndCollBank relaUserAndCollBank,CollBank collBank " +
             "where collBank.status = 1 and  collBank.collBankId=relaUserAndCollBank.collBankId and relaUserAndCollBank.username='"+username+"'";   

             Query query=session.createQuery(hql);     
             return query.list();
         }
       });
   return banklist;
 }
 /**
  * 登录用户下的银行
  * @param username
  * @param users
  * @return
  */
 public String getBankSecuritySQL(final String username,final boolean isCenterMng,final String officecode){
   String orgSecuritySQL="";
   if(isCenterMng){
     orgSecuritySQL = " select b.coll_bank_id as bankid,b.coll_bank_name as bankname,b.status as bankStatus, a.id as officeid,a.name as officename " +
         " from bb101 a,bb105 b " +
         " where a.id=b.office " ;
     if(officecode !=null && !officecode.equals("")){
       orgSecuritySQL+=" and b.office='"+officecode+"'";
     }
     orgSecuritySQL+=" order by a.id";
   }else{
     orgSecuritySQL =" select b.coll_bank_id  as bankid,b.coll_bank_name as bankname,b.status as bankStatus, a.id as officeid,a.name  as officename  " +
     " from  bb101 a,bb105 b,bb109 c " +
     " where  b.office=a.id and c.coll_bank_id=b.coll_bank_id  and  c.username ='"+username+"'";

     if(officecode !=null && !officecode.equals("")){
       orgSecuritySQL+=" and b.office='"+officecode+"'";
     }
     orgSecuritySQL+=" order by a.id";
 }
   return orgSecuritySQL;
 }  
 /**
  * 登录用户下的银行
  * @param username
  * @param users
  * @return
  */
 public List getBankListByUser(final String username,final boolean isCenterMng,final String officecode,final int start,
     final int pageSize){
   List userlist=null;
   userlist = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String sql = getBankSecuritySQL(username,isCenterMng,officecode);
             Query query = session.createSQLQuery(sql);

             query.setFirstResult(start);
             if (pageSize > 0)
               query.setMaxResults(pageSize);    
             List list=new ArrayList();
             Iterator it=query.list().iterator();    
             Object obj[]=null;
             while(it.hasNext()){
               obj=(Object[])it.next();    
               if(obj[0]!=null){
                 OrgDto orgDto=new OrgDto();
                 orgDto.setBankid(obj[0].toString());
                 orgDto.setBankname(obj[1].toString());
                 orgDto.setBankStatus(obj[2].toString());
                 orgDto.setOfficeid(obj[3].toString());
                 orgDto.setOfficename(obj[4].toString());
                 list.add(orgDto);
               }
             }
             return list;
         }
       });
   return userlist;
   }
 public int getBankListCountByUser(final String username,final boolean isCenterMng,final String officecode){
   int count=0;
   List userlist=null;
   userlist = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String sql = getBankSecuritySQL(username,isCenterMng,officecode);
             Query query = session.createSQLQuery(sql);
 
             List list=query.list();    
             return list;
         }
       });
   count=userlist.size();
   return count;
   }
 /**
  * 查询最大的归集银行编号
  * @return
  */
 public int getMaxCollBankid(){
   Integer  collBankId=null;
   collBankId = (Integer) getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String sql = "select max(collBank.collBankId) from CollBank collBank ";
             Query query = session.createQuery(sql);

             
             return query.uniqueResult();
         }
       });
   if(collBankId==null){
     collBankId = new Integer(0);
   }
   return collBankId.intValue();
   }
 /**
  * 查询归集银行
  * @return
  */
 public CollBank getCollBankByCollBankid(final String collBankid){
   CollBank  collBank=null;
   collBank = (CollBank) getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String sql = " from CollBank collBank where collBank.status=1 and collBank.collBankId = ? ";
             Query query = session.createQuery(sql);
             query.setParameter(0, new Integer(collBankid));

             
             return query.uniqueResult();
         }
       });
   return collBank;
   }

 /**
  * 查询归集银行
  * @return
  */
 public CollBank getCollBankByCollBankid_WL(final String collBankid,final String flag){ 
   CollBank  collBank=null;
   collBank = (CollBank) getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String sql = " from CollBank collBank where collBank.status=? and collBank.collBankId = ? ";
             Query query = session.createQuery(sql);
             query.setParameter(0, new Integer(flag));
             query.setParameter(1, new Integer(collBankid));

             return query.uniqueResult();
         }
       });
   return collBank;
   }
 /**
  * 于庆丰 根据001归集银行ＩＤ查询归集银行名称
  * @return
  */
 public CollBank getCollBankByCollBankid_(final String collBankid){
   CollBank  collBank=null;
   collBank = (CollBank) getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = " from CollBank collBank where collBank.collBankId = ? ";
             Query query = session.createQuery(hql);
             query.setParameter(0, new Integer(collBankid));
             return query.uniqueResult();
         }
       });
   return collBank;
   }
 /**
  * 查询归集银行
  * @return
  */
 public CollBank getCollBankByCollBankName(final String collBankName){
   CollBank  collBank=null;
   collBank = (CollBank) getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String sql = " from CollBank collBank where  collBank.collBankName = ? ";
             Query query = session.createQuery(sql);
             query.setParameter(0, collBankName);             
             return query.uniqueResult();
         }
       });
   return collBank;
   }
 /**
  * author wsh 科目关系设置
  * 查询弹出框的银行信息
  * @2007-10-16
  * @return List
  */
 public List queryCollBankList_wsh(final int start,final int pageSize,final String username){
   List list= getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
         throws HibernateException, SQLException {
           List queryList=new ArrayList();
           List temp_list=new ArrayList();
       String hql="select a.coll_bank_name bankname,b.name officename ,a.coll_bank_id id " +
           "from bb105 a,bb101 b,bb109 c"+
           " where a.office=b.id and a.status=1 and c.coll_bank_id=a.coll_bank_id and c.username ='"+username+"'";    
//       String hql =" select a.coll_bank_id id,a.coll_bank_name name from ca113  a where a.username='"+username+"'";    
       Query query = session.createSQLQuery(hql);        
       query.setFirstResult(start);
       if (pageSize > 0)
         query.setMaxResults(pageSize);
       queryList = query.list();      
       Iterator it=queryList.iterator();
       Object obj[] = null;
       while (it.hasNext()) {
         SubjectrelationTaPop2DTO subjectrelationTaPop2DTO=new SubjectrelationTaPop2DTO();
         obj = (Object[]) it.next();
         if (obj != null) {           
           if (obj != null) {
             if(obj[0].toString()!=null){
               subjectrelationTaPop2DTO.setCollBankName(obj[0].toString());
             }
             if(obj[1].toString()!=null){
               subjectrelationTaPop2DTO.setName(obj[1].toString());
             } 
             if(obj[2].toString()!=null){
               subjectrelationTaPop2DTO.setCollBankName(obj[2].toString());
             } 
           }            
           temp_list.add(subjectrelationTaPop2DTO);
         }
       } 
       return temp_list;
     }
       });
   return list;
 } 
 /**
  * author wsh 科目关系设置
  * 查询弹出框的银行的数量
  * @2007-10-16
  * @return List
  */
 public List queryCollBankCountList_wsh(final String username){
   List list= getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
         throws HibernateException, SQLException {
           List queryList=new ArrayList();           
           String hql="select a.coll_bank_id id " +
           "from bb105 a,bb101 b,bb109 c"+
           " where a.office=b.id and a.status=1 and c.COLL_BANK_ID=a.coll_bank_id and c.username ='"+username+"'";    
//           String hql =" select a.coll_bank_id id,a.coll_bank_name name from ca113  a where a.username='"+username+"'";  
       Query query = session.createSQLQuery(hql);                
       queryList = query.list();            
       return queryList;
     }
       });
   return list;
 } 
 /**
  * author wsh 科目关系设置
  * 查询银行所在办事处id
  * @2007-10-16
  * @return List
  */
 public String queryOfiiceId_wsh(final String collbankid){
   String id= (String)getHibernateTemplate().execute(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
         throws HibernateException, SQLException {
          String id="";         
           String hql="select b.id id " +
           "from bb105 a,bb101 b"+
           " where a.office=b.id and a.coll_bank_id =? and a.status=1  ";                                           
       Query query = session.createSQLQuery(hql);  
       query.setParameter(0, collbankid);  
       id = query.uniqueResult().toString();            
       return id;
     }
       });
   return id;
 } 
 /**
  * author wsh 科目关系设置
  * 查询办事处下银行idList
  * @2007-10-16
  * @return List
  */
 public List queryBankIdList_wsh(final String office){
   List list= getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
         throws HibernateException, SQLException {
           List queryList=new ArrayList();
           List temp_list=new ArrayList();
       String hql="select b.coll_bank_id from bb101 a,bb105 b where a.id=b.office and b.office=? ";                                            
       Query query = session.createSQLQuery(hql);        
       query.setParameter(0, office+"");  
       queryList = query.list();      
       Iterator it=queryList.iterator();
       Object obj = null;
       while (it.hasNext()) {
       String bankId="";
         obj = (Object) it.next();
         if (obj != null) {           
           if (obj != null) {
             if(obj.toString()!=null){
               bankId=obj.toString();
             }              
           }            
           temp_list.add(bankId);
         }
       } 
       return temp_list;
     }
       });
   return list;
 }
 /**
  * author wsh 科目关系设置
  * 查询所有银行的idList
  * @2007-10-16
  * @return List
  */
 public List queryCollBankIdList_wsh(){
   List list= getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
         throws HibernateException, SQLException {
           List queryList=new ArrayList();           
       String hql="select a.coll_bank_id from bb105 a where a.status=1";                                              
       Query query = session.createSQLQuery(hql);                
       queryList = query.list();            
       return queryList;
     }
       });
   return list;
 } 
 /**
  * author wsh 科目关系设置
  * 查询所有银行的id及nameList
  * @2007-10-16
  * @return List
  */
 public List queryCollBankIdNameList_wsh(){
   List list= getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
         throws HibernateException, SQLException {                  
       String hql="select a.coll_bank_id,a.coll_bank_name from bb105 a where a.status=1";                                              
       Query query = session.createSQLQuery(hql);                
       Iterator it=query.list().iterator();
       List temp_list=new ArrayList();
       Object obj[]=null;
       while(it.hasNext()){
         obj=(Object[])it.next();
         if(obj!=null){
           SubjectrelationTbBankDTO subjectrelationTbBankDTO=new SubjectrelationTbBankDTO();
           if(obj[0]!=null){
             subjectrelationTbBankDTO.setCollBankId(obj[0].toString());
           }
           if(obj[1]!=null){
             subjectrelationTbBankDTO.setCollBankName(obj[1].toString());
           }                          
           temp_list.add(subjectrelationTbBankDTO);           
         }         
       }
       return temp_list;
      }     
       });
   return list;
 } 
 /**
  * 根据登录用户查询归集行
  * @param username
  * @return
  */
 public List getUserCollBankList_wsh(final int start,final int pageSize,final String username){
   List banklist=null;
   banklist = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
           List queryList=new ArrayList();
           List temp_list=new ArrayList();
           String hql ="select distinct userslogincollbank from Userslogincollbank userslogincollbank where userslogincollbank.username ='"+username+"'";    
//             String hql = 
//               " select distinct collBank from CollBank collBank , RelaRoleAndCollBank relaRoleAndCollBank, " +
//               " User user, Role role,RelaUserAndCollBank relaUserAndCollBank, " +
//               " RelaUserAndUser relaUserAndUser "+
//               " where " +
//               " (collBank.status=1 and collBank.collBankId=relaRoleAndCollBank.collbankid and relaRoleAndCollBank.roleid=role.id and user.id=role.users.id  " +
//               " and user.username ='"+username+"') "+
//               " or (collBank.status=1 and collBank.collBankId=relaUserAndCollBank.collBankId and relaUserAndCollBank.username='"+username+
//               "') or (collBank.status=1 and collBank.collBankId=relaUserAndCollBank.collBankId and relaUserAndUser.subusername=relaUserAndCollBank.username " +
//               "  and relaUserAndUser.username='"+username+"')";     
             
             Query query=session.createQuery(hql);     
             query.setFirstResult(start);
             if (pageSize > 0)
               query.setMaxResults(pageSize);
             queryList = query.list();      
             Iterator it=queryList.iterator();
             Userslogincollbank userslogincollbank = null;
             while (it.hasNext()) {
               SubjectrelationTaPop2DTO subjectrelationTaPop2DTO=new SubjectrelationTaPop2DTO();
               userslogincollbank = (Userslogincollbank) it.next();
               if (userslogincollbank != null) {           
                 if (userslogincollbank != null) {
                   if(userslogincollbank.getCollBankName()!=null){
                     subjectrelationTaPop2DTO.setCollBankName(userslogincollbank.getCollBankName());
                   }
                   if(userslogincollbank.getCollBankId()!=null){
                     subjectrelationTaPop2DTO.setCollBankId(String.valueOf(userslogincollbank.getCollBankId()));
                   } 
                 }            
                 temp_list.add(subjectrelationTaPop2DTO);
               }
             } 
             return temp_list;
         }
       });
   return banklist;
 }
   /**
    * 根据登录用户查询归集行
    * @param username
    * @return
    */
   public List queryCollBankCountList_wsh1(final String username){
     List banklist=null;
     banklist = (List) getHibernateTemplate().executeFind(
         new HibernateCallback() {
           public Object doInHibernate(Session session) throws SQLException,
               HibernateException {
             List queryList=new ArrayList();
             List temp_list=new ArrayList();
             String hql ="select distinct userslogincollbank from Userslogincollbank userslogincollbank where userslogincollbank.username ='"+username+"'";    
//               String hql = 
//                 " select distinct collBank from CollBank collBank , RelaRoleAndCollBank relaRoleAndCollBank, " +
//                 " User user, Role role,RelaUserAndCollBank relaUserAndCollBank, " +
//                 " RelaUserAndUser relaUserAndUser "+
//                 " where " +
//                 " (collBank.status=1 and collBank.collBankId=relaRoleAndCollBank.collbankid and relaRoleAndCollBank.roleid=role.id and user.id=role.users.id  " +
//                 " and user.username ='"+username+"') "+
//                 " or (collBank.status=1 and collBank.collBankId=relaUserAndCollBank.collBankId and relaUserAndCollBank.username='"+username+
//                 "') or (collBank.status=1 and collBank.collBankId=relaUserAndCollBank.collBankId and relaUserAndUser.subusername=relaUserAndCollBank.username " +
//                 "  and relaUserAndUser.username='"+username+"')";     
               
               Query query=session.createQuery(hql);                   
               queryList = query.list();      
               Iterator it=queryList.iterator();
               Userslogincollbank userslogincollbank = null;
               while (it.hasNext()) {
                 SubjectrelationTaPop2DTO subjectrelationTaPop2DTO=new SubjectrelationTaPop2DTO();
                 userslogincollbank = (Userslogincollbank) it.next();
                 if (userslogincollbank != null) {           
                   if (userslogincollbank != null) {
                     if(userslogincollbank.getCollBankName()!=null){
                       subjectrelationTaPop2DTO.setCollBankName(userslogincollbank.getCollBankName());
                     }
                     if(userslogincollbank.getCollBankId()!=null){
                       subjectrelationTaPop2DTO.setCollBankId(String.valueOf(userslogincollbank.getCollBankId()));
                     } 
                   }            
                   temp_list.add(subjectrelationTaPop2DTO);
                 }
               } 
               return temp_list;
           }
         });
     return banklist;
   }
}
