package org.xpup.hafmis.orgstrct.daoDW;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTaPop1DTO;

public class OrganizationUnitDAODW extends HibernateDaoSupport {

  public List queryAll(Serializable parentId) {
    List orgUnits = null;
    String hql = "from OrganizationUnit orgUnit where orgUnit.parent.id";
    if (parentId == null) {
      hql += " is null";
      orgUnits = getHibernateTemplate().find(hql);
    } else {
      hql += " = ?";
      orgUnits = getHibernateTemplate().find(hql, parentId);
    }
    return orgUnits;
  }

  public OrganizationUnit queryById(Serializable id) throws BusinessException {
    Validate.notNull(id, "参数id不能为空！");

    OrganizationUnit orgUnit = (OrganizationUnit) getHibernateTemplate().get(
        OrganizationUnit.class, id);
    if (orgUnit == null) {
      throw new EntityNotFoundException("组织单元不存在，或已经被删除！");
    }
    return orgUnit;
  }

  public Serializable insert(OrganizationUnit organizationUnit) {
    Validate.notNull(organizationUnit, "参数organizationUnit不能为空！");

    return getHibernateTemplate().save(organizationUnit);
  }
  
  public void insertDW(OrganizationUnit organizationUnit) throws BusinessException {
    Validate.notNull(organizationUnit, "参数organizationUnit不能为空！");
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    PreparedStatement pre=null;
    String hql="insert into BB101 (id, name"
      +" ,description, parent_id, position, oupt_id, ou_type) "
      +" values (?, ?, ?, ?, ?, ?, ?) ";     
    try {
      pre=conn.prepareStatement(hql.toString());
      pre.setString(1, organizationUnit.getId().toString()); 
      pre.setString(2, organizationUnit.getName());    
      pre.setString(3, organizationUnit.getDescription());    
      if(organizationUnit.getParent()==null){
        pre.setString(4, null); 
      }else{
        pre.setString(4, organizationUnit.getParent().getId().toString()); 
      } 
      pre.setInt(5, organizationUnit.getPosition());    
      pre.setString(6, organizationUnit.getOuptId());   
      int length=organizationUnit.getType().toString().length();
      pre.setInt(7, Integer.parseInt(organizationUnit.getType().toString().substring(length-2,length).substring(0,1)));    
      pre.executeUpdate(); 
      } catch (SQLException e) {
        throw new BusinessException("数据操作错误");
      }
  }
  
  public void updateDW(OrganizationUnit organizationUnit) throws BusinessException {
    Validate.notNull(organizationUnit, "参数organizationUnit不能为空！");
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    PreparedStatement pre=null;
    String hql="update bb101 t set t.name=?,t.description=?,t.parent_id=?,t.position=?,t.oupt_id=?,t.ou_type=? where t.id='"+organizationUnit.getId().toString()+"'";     
    try {
      pre=conn.prepareStatement(hql.toString());
      pre.setString(1, organizationUnit.getName());    
      pre.setString(2, organizationUnit.getDescription()); 
      if(organizationUnit.getParent()==null){
        pre.setString(3, null); 
      }else{
        pre.setString(3, organizationUnit.getParent().getId().toString()); 
      }  
      pre.setInt(4, organizationUnit.getPosition());    
      pre.setString(5, organizationUnit.getOuptId());   
      int length=organizationUnit.getType().toString().length();
      pre.setInt(6, Integer.parseInt(organizationUnit.getType().toString().substring(length-2,length).substring(0,1)));    
      pre.executeUpdate(); 
      } catch (SQLException e) {
        throw new BusinessException("数据操作错误");
      }
  }


  public void deleteById(Serializable id) throws BusinessException {
    OrganizationUnit orgUnit = queryById(id);
    getHibernateTemplate().delete(orgUnit);
  }
  /**
   * 查询所有办事处
   * @return
   */
  public List queryOrganizationUnitList(){
    List list=null;
    list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = " from OrganizationUnit organizationUnit ";   

              Query query=session.createQuery(hql);     
              return query.list();
          }
        });
    return list;
  }

  /**
   * 根据办事处ID 查询办事处
   * @return
   */
  public OrganizationUnit queryOrganizationUnitListByCriterions(final String officeId){
    OrganizationUnit organizationUnit=new OrganizationUnit() ;
    organizationUnit= (OrganizationUnit)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = " from OrganizationUnit organizationUnit "; 
              Vector parameters = new Vector();
              String criterion = "";
              
              if (officeId != null&&!officeId.equals("")) {//办事处
                criterion += "organizationUnit.id = ? and ";
                parameters.add(officeId);
              }  
              
              if (criterion.length() != 0){
                criterion =  " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }

              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
                
              return query.uniqueResult();
          }
        });
    return organizationUnit;
  }
  
  /**
   * 根据办事处ID 查询办事处
   * @return
   */
  public String queryOrganizationName_LY(final String officeId){
    String officename="";
    officename= (String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = "select bb101.name from BB101 bb101 where bb101.id=? ";          
              Query query = session.createSQLQuery(hql).setString(0,officeId);
              Iterator it=query.list().iterator();
              Object obj=null;
              if(it.hasNext()){
                obj=(Object)it.next();
              }
              if(obj==null){
                obj="";
              }
              return obj.toString();
          }
        });
    return officename;
  }
  /**
   * author wsh 科目关系设置
   * 查询弹出框的办事处名称
   * @2007-10-16
   * @return List
   */
  public List queryOrganizationNameList_wsh(final int start,final int pageSize){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            List queryList=new ArrayList();
            List temp_list=new ArrayList();
        String hql="select a.name name,a.id id " +
            "from bb101 a"+
            " where a.ou_type=2 ";                                              
        Query query = session.createSQLQuery(hql);        
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        queryList = query.list();      
        Iterator it=queryList.iterator();
        Object obj[] = null;
        while (it.hasNext()) {
          SubjectrelationTaPop1DTO subjectrelationTaPop1DTO=new SubjectrelationTaPop1DTO();
          obj = (Object[]) it.next();
          if (obj != null) {           
            if (obj != null) {
              if(obj[0].toString()!=null){
                subjectrelationTaPop1DTO.setName(obj[0].toString());
              }
              if(obj[1].toString()!=null){
                subjectrelationTaPop1DTO.setId(obj[1].toString());
              }              
            }            
            temp_list.add(subjectrelationTaPop1DTO);
          }
        } 
        return temp_list;
      }
        });
    return list;
  } 
  /**
   * author wsh 科目关系设置
   * 查询弹出框的办事处的数量
   * @param subjectCode 科目代码
   * @2007-10-16
   * @return List
   */
  public List queryOrganizationNameCountList_wsh(){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            List queryList=new ArrayList();           
        String hql="select a.id id " +
            "from bb101 a"+
            " where a.ou_type=2 ";                                              
        Query query = session.createSQLQuery(hql);                
        queryList = query.list();            
        return queryList;
      }
        });
    return list;
  } 
  /**
   * author wsh 科目关系设置
   * 查询所有办事处的idList
   * @2007-10-16
   * @return List
   */
  public List queryOrganizationIdList_wsh(){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            List queryList=new ArrayList();           
        String hql="select a.id from bb101 a where a.ou_type=2";                                              
        Query query = session.createSQLQuery(hql);                
        queryList = query.list();            
        return queryList;
      }
        });
    return list;
  } 
  /**
   * 得到用户的办事处(分页)
   * wsh
   * @return userOfficeList
   */
    public List queryOrganizationNameCountList_wsh (final String username) {
      List userOfficeList = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws SQLException,
                HibernateException {               
//                String hql = 
//                " select b.id,b.name from bb108 a, bb101 b where a.office = b.id and b.ou_type='2' and a.username ='"+username+"'";                  
//                Query query=session.createSQLQuery(hql);   
//                return query.list();  
                String hql = 
                  " select distinct b.id,b.name from bb108 a, bb101 b where b.ou_type=2 and a.office = b.id and a.username ='"+username+"'"+
                  " union "+
                  " select b.id, b.name from bb101 b, bb110 c, ca101 d, ca103 e where b.ou_type=2 and b.id=c.office "+
                  " and c.roleid=e.role_id and e.user_id=d.id and d.username='"+username+"'"+
                  " union "+
                  " select b.id, b.name from bb108 a, bb101 b, bb112 f where b.ou_type=2 and a.office = b.id "+
                  " and a.username = f.subusername and f.username ='"+username+"'"; 
                
                
                List queryList=new ArrayList();
                List temp_list=new ArrayList();
                                                     
            Query query = session.createSQLQuery(hql);        
          
            queryList = query.list();      
            Iterator it=queryList.iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              SubjectrelationTaPop1DTO subjectrelationTaPop1DTO=new SubjectrelationTaPop1DTO();
              obj = (Object[]) it.next();
              if (obj != null) {           
                if (obj != null) {
                  if(obj[1].toString()!=null){
                    subjectrelationTaPop1DTO.setName(obj[1].toString());
                  }
                  if(obj[0].toString()!=null){
                    subjectrelationTaPop1DTO.setId(obj[0].toString());
                  }              
                }            
                temp_list.add(subjectrelationTaPop1DTO);
              }
            } 
            return temp_list;
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
            }
          });
      return userOfficeList;
    }
}
