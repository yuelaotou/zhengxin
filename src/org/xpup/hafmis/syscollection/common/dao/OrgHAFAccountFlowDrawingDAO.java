package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowDrawing;

public class OrgHAFAccountFlowDrawingDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowDrawing queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowDrawing) getHibernateTemplate().get(OrgHAFAccountFlowDrawing.class,id);    
  }
  /**
   * 李文浩... 根据头表的Id找出来单位的流水表Id...
   * 但是此记录必须是唯一的一条记录..如果出现两条记录 那么是数据库的错误
   */
  public OrgHAFAccountFlow getOrgHAFAccountFlow(final Integer id){
    try{
      OrgHAFAccountFlow s = (OrgHAFAccountFlow)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              OrgHAFAccountFlow d = (OrgHAFAccountFlow)session.createQuery("from OrgHAFAccountFlowDrawing s where s.bizId=?").setBigDecimal(0, new BigDecimal(id.intValue())).uniqueResult();
              return d;
            }
          }
      );
      return s;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  //在撤消提取的时候,删除aa101里面的记录..
  public int deleteOrgFlowToRecallPickup(final int headId){
    //这条记录一定不能为null的..因为这是一个撤消提取的过程..它在完成提取的时候就已经插入了aa101的表中了
    OrgHAFAccountFlowDrawing aa101 = findByHeadIdAndStateIsOne(headId);
    getHibernateTemplate().delete(aa101);//删除aa101的那条记录
    int id = Integer.parseInt(aa101.getId().toString());
    return id;
  }
  /**
   * 李文浩...
   * 根据头表的Id能够找出来aa301头状态为1的那条记录 
   */
  public OrgHAFAccountFlowDrawing findByHeadIdAndStateIsOne(final int headId){
    try{
      OrgHAFAccountFlowDrawing s = new OrgHAFAccountFlowDrawing();
      OrgHAFAccountFlowDrawing d = (OrgHAFAccountFlowDrawing)
      getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Object obj = session.createQuery("from OrgHAFAccountFlowDrawing a where a.bizId=?")
              .setBigDecimal(0, new BigDecimal(headId)).uniqueResult();
              return obj;
            }
          }
      );
      return d;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  /**
   * 插入记录
   * @param OrgHAFAccountFlowDrawing
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowDrawing);
    id = getHibernateTemplate().save(orgHAFAccountFlowDrawing); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
