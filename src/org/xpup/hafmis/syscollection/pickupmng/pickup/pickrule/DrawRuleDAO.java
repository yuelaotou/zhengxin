package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;
public class DrawRuleDAO extends HibernateDaoSupport{
  /**
   *李文浩　
   *根据比率来获得部分提取的最大金额 
   */
  public BigDecimal getSomePickMoney(int orgId, int empId, String reason){
    BigDecimal big = this.getDistroyPickMoney(orgId, empId,reason);
//    System.out.println("Big:"+big);
    return big;
  }
  /**
   * 李文浩..
   * 获得销户提取的大金额
   */
  public BigDecimal getDistroyPickMoney(int orgId, int empId,String reason){
    return this.getMaxPickMoney(orgId, empId,reason);
  }
  /**
   * 李文浩
   * 根据职工编号和单位编号同时来获得此职工的最大金额
   */
  public BigDecimal getMaxPickMoney(final int orgId,final int empId,String reason){
    try{
      Object obj = new Object();
//      if(getHibernateTemplate() == null)
//        System.out.println("****************");
//      else
//        System.out.println("-----------------");
      obj = getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Object obj = session.createQuery("select sum(e.curBalance+e.preBalance) from Emp e where e.org.id=? and e.empId=?")
              .setInteger(0, orgId).setInteger(1, empId)
              .uniqueResult();
              return obj;
            }
          }
      );
      return new BigDecimal(obj.toString());
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  /**
   * 李文浩..获得职工年提取的次数
  */
  public int findEmployeePickupCount(final String reason,final Serializable id){
    final int empId = Integer.parseInt(id.toString());
    Object number;
    number = getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Time time = new Time(new java.util.Date().getTime());
              String t = time.toLocaleString().substring(0,4);//获得系统时间的年份
              String hql ="select count(a.id) from PickHead a,PickTail b where substr(a.settDate,0,4)=? and b.pickHead.id = a.id and b.empId = ?";
              Object number = session.createQuery(hql).setParameter(0, t).setInteger(1, empId).uniqueResult();
              return number;
            }
          }
        );
    return Integer.parseInt(number.toString());
  }
  /**
   * ld_add 获取往年的余额当提取原因是重大疾病或特困时
   * */
  public BigDecimal getPerbalance(final int orgid,final int empid,final String reason){
    try {
      Object obj = new Object();
      obj = getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          Object obj = session.createQuery("select e.preBalance from Emp e where e.org.id=? and e.empId=?")
          .setInteger(0, orgid).setInteger(1, empid)
          .uniqueResult();
          return obj;
        }});
      return new BigDecimal(obj.toString());
    } catch (Exception e) {
       e.printStackTrace();
    }
    return null;
  }
 
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
}
