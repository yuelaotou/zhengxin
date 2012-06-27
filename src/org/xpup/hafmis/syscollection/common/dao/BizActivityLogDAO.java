package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeSalaryBaseBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestResult;
import org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.dto.BusinesslogsearchDTO;
import org.xpup.security.common.domain.User;

/**
 * 业务活动日志 
 * 
 *@author 李娟
 *2007-6-19
 */
public class BizActivityLogDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public BizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (BizActivityLog) getHibernateTemplate().get(BizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param bizActivityLog
   * @return
   */
  public Serializable insert(BizActivityLog bizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(bizActivityLog);
    id = getHibernateTemplate().save(bizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /***
   *根据bizid查找
   *@ return BizActivityLog
   */
  public BizActivityLog queryByBizId_sy(final String biz_id){
   
      return (BizActivityLog)getHibernateTemplate().execute( 
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "from BizActivityLog bizActivityLog  where "+
                           " bizActivityLog.bizId=?  ";
              Query query = session.createQuery(hql);
              query.setBigDecimal(0, new BigDecimal(biz_id));
              return query.uniqueResult();          
          }
          }); 
  }
  public void deleteBizActivityLog(BizActivityLog bizActivityLog){
    getHibernateTemplate().delete(bizActivityLog);
  }
  
  /**
   * 于庆丰
   * 根据主键删除
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      BizActivityLog bizActivityLog = queryById(new Integer(id));
      getHibernateTemplate().delete(bizActivityLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 键删除单个记录
   * @return
   * 吴洪涛 
   */
  public void deleteWuht(BizActivityLog bizActivityLog){
    Validate.notNull(bizActivityLog);
    getHibernateTemplate().delete(bizActivityLog);
  }
  /**
   * 于庆丰
   * 根据bizid,查询
   * @param id
   * @return
   */
  public BizActivityLog queryByBizId(final String id,final Integer action,final String type){
    BizActivityLog bizActivityLog = new BizActivityLog();
    try{
      Validate.notNull(id);
      bizActivityLog = (BizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select bizActivityLog from BizActivityLog bizActivityLog ";
              Vector parameters = new Vector();
              String criterion = "";
              if(id != null && !"".equals(id)){
                criterion += "bizActivityLog.bizid = ? and ";
                parameters.add(new Integer(id));
              }
              if(action != null && !"".equals(action)){
                criterion += "bizActivityLog.action = ? and ";
                parameters.add(action);
              }
              if(type != null && !"".equals(type)){
                criterion += "bizActivityLog.types = ? and ";
                parameters.add(type);
              }
              if (criterion.length() != 0) 
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }     
              return  query.uniqueResult();
            }
          });
      }catch(Exception e){
        e.printStackTrace();
      }
      return bizActivityLog;
  }
  
  /**
   * 吴洪涛
   * 根据bizid查询
   * @param id
   * @return
   */
  public BizActivityLog queryBizActivityLogWuht(final String bizid,final String bizTrpe,final String bizStatus){
    BizActivityLog bizActivityLog = new BizActivityLog();
    try{
      Validate.notNull(bizid);
      bizActivityLog = (BizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select bizActivityLog from BizActivityLog bizActivityLog  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (bizid != null) {
                criterion += "bizActivityLog.bizid = ? and ";
                parameters.add(new Integer(bizid));
              }  
              if (bizStatus != null) {
                criterion += "bizActivityLog.types = ? and ";
                parameters.add(bizStatus);
              } 
              if (bizTrpe != null) {
                criterion += "bizActivityLog.action = ? and ";
                parameters.add(new Integer(4));
              } 
              if (criterion.length() != 0) 
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;

              Query query0 = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query0.setParameter(i, parameters.get(i));
              }     
              return  query0.uniqueResult();
            }
          });

      }catch(Exception e){
        e.printStackTrace();
      }
      return bizActivityLog;
  }
  /**
   * 于庆丰
   * 根据bizid查询
   * @param id
   * @return
   */
  public BizActivityLog queryBizActivityLogWuht_(final String bizid,final String bizTrpe,final String bizStatus){
    BizActivityLog bizActivityLog = new BizActivityLog();
    try{
      Validate.notNull(bizid);
      bizActivityLog = (BizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select bizActivityLog from BizActivityLog bizActivityLog  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (bizid != null) {
                criterion += "bizActivityLog.bizid = ? and ";
                parameters.add(new Integer(bizid));
              }  
              if (bizTrpe != null) {
                criterion += "bizActivityLog.types = ? and ";
                parameters.add(bizTrpe);
              } 
              if (bizStatus != null) {
                criterion += "bizActivityLog.action = ? and ";
                parameters.add(new Integer(bizStatus));
              } 
              if (criterion.length() != 0) 
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;

              Query query0 = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query0.setParameter(i, parameters.get(i));
              }     
              return  query0.uniqueResult();
            }
          });

      }catch(Exception e){
        e.printStackTrace();
      }
      return bizActivityLog;
  }
  
  
  public List queryBizActivityLog(final String bizType,final String operator,final String payStatus,final String beginMonth,final String endMonth,final String orderBy, final String order, final int start,final int pageSize,final SecurityInfo securityInfo){
    List list = new ArrayList();
    list = (List) getHibernateTemplate()
    .executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String sql = " select a319.bizId,a319.type,a319.action,to_char(a319.op_time,'yyyy-mm-dd'),a319.operator from aa319 a319 ";
        Vector parameters = new Vector();
        String criterion = "";
        if (bizType != null&&(!bizType.equals(""))) {
          criterion += "a319.type = ? and ";
          parameters.add(bizType);
        }  
        if (operator != null && !operator.equals("")) {
          criterion += "a319.operator = ?  and ";
          parameters.add(operator);
        } else {
          criterion+=" a319.operator in (";
          List operList = securityInfo.getUserList();
          User user = null;
          Iterator itr2 = operList.iterator();
          while (itr2.hasNext()) {
            user = (User) itr2.next();
            criterion+="'"+user.getUsername()+"',";
          }criterion = criterion.substring(0, criterion.lastIndexOf(","));
          criterion += ") and ";
        }
        if (payStatus != null&&(!payStatus.equals(""))) {
          criterion += "a319.action = ? and ";
          parameters.add(new Integer(payStatus));
        } 
        if (beginMonth != null&&(!beginMonth.equals(""))) {
          criterion += "to_char(a319.op_time,'yyyymmdd')>=? and ";
          parameters.add(beginMonth);
        } 
        if (endMonth != null&&(!endMonth.equals(""))) {
          criterion += "to_char(a319.op_time,'yyyymmdd')<=? and ";
          parameters.add(endMonth);
        } 
        if (criterion.length() != 0) 
          criterion = " where "+ criterion.substring(0, criterion.lastIndexOf("and"));
              
        sql = sql + criterion;
        String ob = orderBy;
        if (ob == null)
          ob = " a319.bizid ";

        String od = order;
        if (od == null)
          od = "DESC";

        sql = sql + "order by " + ob + " " + order;
      
        Query query = session.createSQLQuery(sql);
        Object[] obj=null;
        List logList=new ArrayList();
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
        Iterator it=query.list().iterator();
        while(it.hasNext()){
          obj=(Object[])it.next();
          BusinesslogsearchDTO dto=new BusinesslogsearchDTO();
          dto.setBizId(obj[0].toString());
          dto.setBizType(obj[1].toString());
          dto.setBizType_temp(obj[1].toString());
          dto.setPayStatus(obj[2].toString());
          dto.setOperatorTime(obj[3].toString());
          dto.setOperator(obj[4].toString());
          logList.add(dto);
        }
        return  logList;
      }
    });
    return list;
}
  
  public List queryBizActivityLogCount(final String bizType,final String operator,final String payStatus,final String beginMonth,final String endMonth,final SecurityInfo securityInfo){
    List list = new ArrayList();
    list = (List) getHibernateTemplate()
    .executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String sql = " select a319.bizId,a319.type,a319.action,to_char(a319.op_time,'yyyy-mm-dd'),a319.operator from aa319 a319 ";
        Vector parameters = new Vector();
        String criterion = "";
        if (bizType != null&&(!bizType.equals(""))) {
          criterion += "a319.type = ? and ";
          parameters.add(bizType);
        }  
        if (operator != null && !operator.equals("")) {
          criterion += "a319.operator = ?  and ";
          parameters.add(operator);
        } else {
          criterion+=" a319.operator in (";
          List operList = securityInfo.getUserList();
          User user = null;
          Iterator itr2 = operList.iterator();
          while (itr2.hasNext()) {
            user = (User) itr2.next();
            criterion+="'"+user.getUsername()+"',";
          }criterion = criterion.substring(0, criterion.lastIndexOf(","));
          criterion += ") and ";
        }
        
        if (payStatus != null&&(!payStatus.equals(""))) {
          criterion += "a319.action = ? and ";
          parameters.add(new Integer(payStatus));
        } 
        if (beginMonth != null&&(!beginMonth.equals(""))) {
          criterion += "to_char(a319.op_time,'yyyymmdd')>=? and ";
          parameters.add(beginMonth);
        } 
        if (endMonth != null&&(!endMonth.equals(""))) {
          criterion += "to_char(a319.op_time,'yyyymmdd')<=? and ";
          parameters.add(endMonth);
        } 
        
        if (criterion.length() != 0) 
          criterion = " where "+ criterion.substring(0, criterion.lastIndexOf("and"));
              
        sql = sql + criterion;
       
      
        Query query = session.createSQLQuery(sql);
        Object[] obj=null;
        List logList=new ArrayList();
        

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
        Iterator it=query.list().iterator();
        while(it.hasNext()){
          obj=(Object[])it.next();
          BusinesslogsearchDTO dto=new BusinesslogsearchDTO();
          dto.setBizId(obj[0].toString());
          dto.setBizType(obj[1].toString());
          dto.setPayStatus(obj[2].toString());
          dto.setOperatorTime(obj[3].toString());
          dto.setOperator(obj[4].toString());
          logList.add(dto);
        }
        return  logList;
      }
    });
    return list;
}
}

