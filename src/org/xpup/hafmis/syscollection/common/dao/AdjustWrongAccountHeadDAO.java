package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

/**
 * 错账调整头表 
 * 
 *@author 李娟
 *2007-6-19
 */
public class AdjustWrongAccountHeadDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public AdjustWrongAccountHead queryById(Serializable id) {  
    Validate.notNull(id);
    return  (AdjustWrongAccountHead) getHibernateTemplate().get(AdjustWrongAccountHead.class,id);    
  }
  
  /**
   * 根据主键删除
   * @param id
   * @return
   */
  public void deleteById(Serializable id) {
    try {
      Validate.notNull(id);
      AdjustWrongAccountHead adjustWrongAccountHead = queryById(id);
      getHibernateTemplate().delete(adjustWrongAccountHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 判断员工是否在该单位下 
   * @param orgId
   * @param empId
   * @param adjustStatus 状态
   * @return AdjustWrongAccountHead
   */
  public Emp queryByOrgIdAndEmpId(final String orgID,final String empID) {
    Emp emp =null;
    try{
      emp=(Emp)getHibernateTemplate().execute(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from Emp emp where "+
            "org.id=? and emp.empId=?";
            Query query = session.createQuery(hql);
             query.setParameter(0,new Integer(orgID));
             query.setParameter(1,new Integer(empID));
             return query.uniqueResult();             
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return emp;
  }
  /**
   * 根据错帐凭证号和adjustStatus状态返回AdjustWrongAccountHead
   * @param docNum 错帐凭证号
   * @param adjustStatus 状态
   * @return AdjustWrongAccountHead
   */
  public List queryByEmpIdAndOrgId(final Serializable orgID) {
    List list = null;
    try{
      list=getHibernateTemplate().executeFind(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select adjustWrongAccountTail from AdjustWrongAccountHead adjustWrongAccountHead,AdjustWrongAccountTail adjustWrongAccountTail " +
                "where adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.adjustStatus<5 and adjustWrongAccountHead.id=adjustWrongAccountTail.adjustWrongAccountHead";
            Query query = session.createQuery(hql);
             query.setParameter(0,new Integer(orgID+""));
             return query.list();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  } 
  /**
   * 根据错帐凭证号和adjustStatus状态返回AdjustWrongAccountHead
   * @param orgID 单位编号
   * @param securityInfo 权限
   * @return AdjustWrongAccountHead
   */
  public List queryByIDLP(final Serializable orgID,final SecurityInfo securityInfo) {
    List adjustWrongAccountHead =null;
    try{
      adjustWrongAccountHead=getHibernateTemplate().executeFind(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
            "adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.adjustStatus not in (5) ";
            Query query = session.createQuery(hql);
             query.setParameter(0,new Integer(orgID+""));
             return query.list();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return adjustWrongAccountHead;
  }
  /**
   * 根据错帐凭证号和adjustStatus状态返回AdjustWrongAccountHead
   * @param orgID 单位编号
   * @param securityInfo 权限
   * @return AdjustWrongAccountHead
   */
  public List queryByIDGJP(final String orgID,final SecurityInfo securityInfo) {
    List list =null;
    try{
      list=getHibernateTemplate().executeFind(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
            "adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.adjustStatus not in (5)";//and adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
            Query query = session.createQuery(hql);
             query.setParameter(0,new Integer(orgID));
             return query.list();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 判断权限
   * @param orgID 单位id
   * @param securityInfo 权限
   * @return AdjustWrongAccountHead
   */
  public AdjustWrongAccountHead queryBySecurityInfo(final Serializable orgID,final SecurityInfo securityInfo) {
    AdjustWrongAccountHead adjustWrongAccountHead =null;
    try{
      adjustWrongAccountHead=(AdjustWrongAccountHead)getHibernateTemplate().execute(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
            "adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
            Query query = session.createQuery(hql);
             query.setParameter(0,new Integer(orgID+""));
             return query.uniqueResult();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return adjustWrongAccountHead;
  }
  
  /**
   * 根据单位号返回AdjustWrongAccountHead
   * @param orgID
   * @return AdjustWrongAccountHead
   */
  public AdjustWrongAccountHead queryByOrgId(final String orgID) {
    AdjustWrongAccountHead adjustWrongAccountHead=null;
        try{
          adjustWrongAccountHead=(AdjustWrongAccountHead)getHibernateTemplate().execute(    
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
                "adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.adjustStatus=1";//and orgHAFAccountFlow.settDate>? ";
                
                Query query = session.createQuery(hql);
                 query.setString(0,orgID);
                 return query.uniqueResult();
            }
            }); 
        }catch(Exception e){
          e.printStackTrace();
        }
      return adjustWrongAccountHead;
  }
  
  /**
   * 根据单位号返回AdjustWrongAccountHead
   * @param orgID
   * @return AdjustWrongAccountHead
   */
  public AdjustWrongAccountHead queryByOrgIdReAdjustWrongAccountHead(final String orgID,final SecurityInfo securityInfo) {
    AdjustWrongAccountHead adjustWrongAccountHead=null;
        try{
          adjustWrongAccountHead=(AdjustWrongAccountHead)getHibernateTemplate().execute(    
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
                "adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.adjustStatus=1 ";
                Query query = session.createQuery(hql);
                query.setParameter(0,new Integer(orgID));
                 return query.uniqueResult();
            }
            }); 
        }catch(Exception e){
          e.printStackTrace();
        }
      return adjustWrongAccountHead;
  }
  
  /**
   * 根据单位ID取得错账ID
   * @param docNum 错帐凭证号
   * @param settDate 结算日期
   * @return OrgHAFAccountFlow
   */
  public AdjustWrongAccountHead queryByAdjustWrongAccountHeadId(final String orgId) {
    AdjustWrongAccountHead adjustWrongAccountHead=null;
    try{
      adjustWrongAccountHead=(AdjustWrongAccountHead)getHibernateTemplate().execute(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
            "adjustWrongAccountHead.org.id=?";
            Query query = session.createQuery(hql);
             query.setString(0,orgId);
             return query.uniqueResult();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return adjustWrongAccountHead;
  }
  /**
   * 插入记录
   * @param adjustWrongAccountHead
   * @return
   */
  public Serializable insert(AdjustWrongAccountHead adjustWrongAccountHead){
    Serializable id = null;
    try{    
    Validate.notNull(adjustWrongAccountHead);
    id = getHibernateTemplate().save(adjustWrongAccountHead);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  
  /** 维护 使用
   * 默认查询
   * @param adjustWrongAccountHead
   * @return
   */
  public List queryAdjustWrongAccountHeadListByState(final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final int page) throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              
              String hql = " from AdjustWrongAccountHead adjustWrongAccountHead where adjustWrongAccountHead.adjustStatus in (1,3) " +
                  "and adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL();

              String criterion = "";
              String ob = orderBy;
              if (ob == null)
                ob = " adjustWrongAccountHead.docNum ";
              String od = order;
              if (od == null)
                od = "DESC";
              hql = hql + criterion + "order by " + ob + " " + order;
              Query query = session.createQuery(hql);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize); 
              
              List queryList=query.list();
              
              if(queryList==null||queryList.size()==0){           
                query.setFirstResult(pageSize*(page-2));
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                queryList=query.list();
              } 
              return queryList;
              
              
              
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /** 维护 使用
   * 默认查询  所有记录
   * @param adjustWrongAccountHead
   * @return
   */
  public List queryAdjustWrongAccountHeadAllListByState(final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              
              String hql = " from AdjustWrongAccountHead adjustWrongAccountHead where adjustWrongAccountHead.adjustStatus in (1,3) " +
                  "and adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL();

              String criterion = "";
              String ob = orderBy;
              if (ob == null)
                ob = " adjustWrongAccountHead.docNum ";
              String od = order;
              if (od == null)
                od = "DESC";
              hql = hql + criterion + "order by " + ob + " " + order;
              Query query = session.createQuery(hql);

             
              return query.list();
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 对错账调整头表进行综合查询
   * @param id
   * @param orgName
   * @param bis_status
   * @param bizDocNum
   * @param date      
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryAdjustWrongAccountHeadListByCriterions(final String id,final String orgName,
      final String bis_status,final String bizDocNum,final String date,final String date1,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final int page) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from AdjustWrongAccountHead adjustWrongAccountHead  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id==null||id.equals("")) {
                
              }else if(id != null&&!id.equals("")){
                criterion += " to_char(adjustWrongAccountHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              if (orgName != null&&!orgName.equals("")) {
                criterion += "adjustWrongAccountHead.org.orgInfo.name like ?  and ";
                parameters.add("%" + orgName + "%");
              }
              if (bis_status != null&&!bis_status.equals("")) {
                criterion += "adjustWrongAccountHead.adjustStatus = ?  and ";
                parameters.add(new BigDecimal(bis_status));
              }
              if (bizDocNum != null&&!bizDocNum.equals("")) {
                criterion += "adjustWrongAccountHead.docNum = ?  and ";
                parameters.add(bizDocNum);
              }

              if (date != null&&!date.equals("") && date1 != null && !date1.equals("")) {
                criterion += " (adjustWrongAccountHead.bizDate between ?  and  ? ) and ";
                parameters.add(date);
                parameters.add(date1);
              }else if (date != null&&!date.equals("")) {
                criterion += " adjustWrongAccountHead.bizDate = ? and ";
                parameters.add(date);
              }else if (date1 != null && !date1.equals("")) {
                criterion += " adjustWrongAccountHead.bizDate = ? and ";
                parameters.add(date1);
              }
              if (criterion.length() != 0)
                criterion = "where adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              else {
                criterion = "where adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
              } 
              String ob = orderBy;
              if (ob == null)
                ob = " adjustWrongAccountHead.id ";
              String od = order;
              if (od == null)
                od = "DESC";
              hql = hql + criterion + "order by " + ob + " " + order;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize); 
              
              List queryList=query.list();
              
              if(queryList==null||queryList.size()==0){           
                query.setFirstResult(pageSize*(page-2));
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                queryList=query.list();
              } 
              return queryList;
              
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 对错账调整头表进行综合查询 所有记录
   * @param id
   * @param orgName
   * @param bis_status
   * @param bizDocNum
   * @param date      
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryAdjustWrongAccountHeadAllListByCriterions(final String id,final String orgName,
      final String bis_status,final String bizDocNum,final String date,final String date1,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from AdjustWrongAccountHead adjustWrongAccountHead  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id==null||id.equals("")) {
                
              }else if(id != null&&!id.equals("")){
                criterion += " to_char(adjustWrongAccountHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              if (orgName != null&&!orgName.equals("")) {
                criterion += "adjustWrongAccountHead.org.orgInfo.name like ?  and ";
                parameters.add("%" + orgName + "%");
              }
              if (bis_status != null&&!bis_status.equals("")) {
                criterion += "adjustWrongAccountHead.adjustStatus = ?  and ";
                parameters.add(new BigDecimal(bis_status));
              }
              if (bizDocNum != null&&!bizDocNum.equals("")) {
                criterion += "adjustWrongAccountHead.docNum = ?  and ";
                parameters.add(bizDocNum);
              }
              if (date != null&&!date.equals("") && date1 != null && !date1.equals("")) {
                criterion += " (adjustWrongAccountHead.bizDate ?  and  ? ) and ";
                parameters.add(date);
                parameters.add(date1);
              }else if (date != null&&!date.equals("")) {
                criterion += " adjustWrongAccountHead.bizDate = ? and ";
                parameters.add(date);
              }else if (date1 != null && !date1.equals("")) {
                criterion += " adjustWrongAccountHead.bizDate = ? and ";
                parameters.add(date1);
              }
              if (criterion.length() != 0)
                criterion = "where adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              else {
                criterion = "where adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
              } 
              String ob = orderBy;
              if (ob == null)
                ob = " adjustWrongAccountHead.docNum ";
              String od = order;
              if (od == null)
                od = "DESC";
              hql = hql + criterion + "order by " + ob + " " + order;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }      
              return query.list();
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 根据条件综合条件查询记录数
   * @param id 头表id
   * @param orgId 单位id
   * @param orgName 单位名
   * @param bis_status 状态  下拉列表使用
   * @param bis_status 状态 3
   * @param bizDocNum  凭证号
   * @param date 错账日期
   * @return int
   */
  public int queryAdjustWrongAccountHeadCountByCriterions(final String id,final String orgId,final String orgName,
      final String bis_status,final String bis_status1,final String bizDocNum,final String date) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  ";
              Vector parameters = new Vector();
              String criterion = "";


              if (id != null&&!id.equals("")) {
                criterion += "adjustWrongAccountHead.id = ?  and ";
                parameters.add(new Integer(id));
              }
              if (orgId != null&&!orgId.equals("")) {
                criterion += "adjustWrongAccountHead.org.id = ?  and ";
                parameters.add(new Integer(orgId));
              }
              if (orgName != null&&!orgName.equals("")) {
                criterion += "adjustWrongAccountHead.org.orgInfo.name = ?  and ";
                parameters.add(orgName);
              }

              
              if (bis_status1 != null&&!bis_status1.equals("")&& bis_status != null&&!bis_status.equals("")) {
                criterion += "adjustWrongAccountHead.adjustStatus = ?  or adjustWrongAccountHead.adjustStatus = ?  and ";
                
                parameters.add(new BigDecimal(bis_status));
                parameters.add(new BigDecimal(bis_status1));
                
              }
              else if (bis_status!=null && "".equals(bis_status)){
                criterion += "adjustWrongAccountHead.adjustStatus = ?  and ";
                parameters.add(new BigDecimal(bis_status)); 
              }   
              
              
              if (bizDocNum != null&&!bizDocNum.equals("")) {
                criterion += "adjustWrongAccountHead.docNum = ?  and ";
                parameters.add(bizDocNum);
              }
              if (date != null&&!date.equals("")) {
                criterion += "adjustWrongAccountHead.bizDate = ?  and ";
                parameters.add(date);
              }
              
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
           
              hql = hql + criterion ;
              session.clear();
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
 }
  
  
  /**
   * 根据单位号和状态返回查询list
   * @param orgID
   * @return list
   */
  public List findAdjustWrongAccountHeadListByOrgId(final String orgID) {
    List list=null;
        try{
          list=getHibernateTemplate().executeFind(    
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
                "adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.adjustStatus=1";//and orgHAFAccountFlow.settDate>? ";
                Query query = session.createQuery(hql);
                 query.setParameter(0,new Integer(orgID));
                 return query.list();
            }
            }); 
        }catch(Exception e){
          e.printStackTrace();
        }
      return list;
  }
  
}
