package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;


import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.PartPickupCondition;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;



public class PartPickupConditionDAO extends HibernateDaoSupport {
  /**
   * 插入记录
   * 
   * @param PartPickupCondition
   * @return
   */
  public Serializable insert(PartPickupCondition partPickupCondition) {
    Serializable id = null;
    try {
      Validate.notNull(partPickupCondition);
      id = getHibernateTemplate().save(partPickupCondition);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 部分提取前提录入
   * @author 郭婧平
   * 2007-12-6
   * 查aa401表内容
   */
  public PartPickupConditionDTO queryPartPickupConditionInfo() throws Exception {
    PartPickupConditionDTO partPickupConditionDTO=null;
    try {
      partPickupConditionDTO =(PartPickupConditionDTO)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select partPickupCondition.pickMoneyMax,"+
                                  "partPickupCondition.pickTimeMax,"+
                                  "partPickupCondition.leavingsBalance, " +
                                  "partPickupCondition.multiple " +
                                  "from PartPickupCondition partPickupCondition ";
            Query query = session.createQuery(hql);
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            PartPickupConditionDTO dto=null;
            if(obj!=null){
              dto=new PartPickupConditionDTO();
              if(obj[0]!=null){
                dto.setPickMoneyMax(new BigDecimal(obj[0].toString()));
              }
              if(obj[1]!=null){
                dto.setPickTimeMax(Integer.parseInt(obj[1].toString()));
              }else{
                dto.setPickTimeMax(0);
              }
              if(obj[2]!=null){
                dto.setLeavingsBalance(new BigDecimal(obj[2].toString()));
              }
              if(obj[3]!=null){
                dto.setMultiple(Integer.parseInt(obj[3].toString()));
              }
              
            }
            return dto;                                      
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return partPickupConditionDTO;
  }
  /**
   * 部分提取前提录入
   * @author 郭婧平
   * 2007-12-6
   * 删除aa401表内容
   */
  public void deletePartPickupCondition(){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete PartPickupCondition partPickupCondition ";          
             session.createQuery(sql).executeUpdate();
              return null;
          }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
