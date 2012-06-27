package org.xpup.hafmis.syscollection.common.dao;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.PaymentBankNameDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.dto.PaymntPickDetailDTO;

/**
 * 汇缴头表
 * 
 * @author 李娟 2007-6-19
 */
public class MonthPaymentHeadDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public MonthPaymentHead queryById(Serializable id) {
    Validate.notNull(id);
    return (MonthPaymentHead) getHibernateTemplate().get(
        MonthPaymentHead.class, id);
  }

  /**
   * 插入记录
   * 
   * @param monthPaymentHead
   * @return
   */
  public Serializable insert(MonthPaymentHead monthPaymentHead) {
    Serializable id = null;
    try {
      Validate.notNull(monthPaymentHead);
      id = getHibernateTemplate().save(monthPaymentHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * 李娟 根据单位ID查询最大汇缴年月
   * 
   * @param orgid
   * @return
   */
  public String queryMaxMonth(final Serializable orgid) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            
            String hql = "select max(monthPaymentHead.payMonth) "
                + "from MonthPaymentHead monthPaymentHead where monthPaymentHead.paymentHead.payType_ in('A','B')";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "monthPaymentHead.paymentHead.org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });

    return month;
  }
  /**
   * jj
   * 查询职工缴至年月
   * @param orgid
   * @return
   */
  public String queryEmpPayMonth(final Serializable orgid) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            
            String hql = "select max(aa302.pay_month) from AA302 aa302,AA301 aa301 " +
                "where aa301.id=aa302.pay_head_id and aa301.pay_type in ('A','B') "+
                    " and aa301.pay_status in ('3','4','5') and aa301.pay_model in ('1','3') ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "aa301.org_id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });

    return month;
  }
  
  /**
   * jj
   * 查询单位缴至年月
   * @param orgid
   * @return
   */
  public String queryOrgPayMonth(final Serializable orgid) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            
            String hql = "select max(aa302.pay_month) from AA302 aa302,AA301 aa301 " +
                "where aa301.id=aa302.pay_head_id and aa301.pay_type in ('A','B') "+
                    " and aa301.pay_status in ('3','4','5') and aa301.pay_model in ('1','2') ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "aa301.org_id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });

    return month;
  }
  /**
   * 李娟
   * @param orgId
   * @param payMonth
   * @return
   */
    
  public List queryMonthPaymentHead(final Serializable orgId,
      final String payMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead , MonthPayment monthPayment ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if (payMonth != null && !payMonth.equals("")) {
            criterion += " monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = "where monthPaymentHead.paymentHead.id = monthPayment.id and monthPayment.payStatus<>5 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
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
   * 根据补缴ID和年月查询AA302
   * @param payHeadId
   * @param payMonth
   * @return
   */
  public MonthPaymentHead queryMonthPaymentHead_jj(final String payHeadId,final String payMonth) {
    MonthPaymentHead list = null;
    try {
      list = (MonthPaymentHead)getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (payHeadId != null && !payHeadId.equals("")) {
            criterion += " monthPaymentHead.paymentHead.id = ?  and ";
            parameters.add(new Integer(payHeadId.toString()));
          }
          if (payMonth != null && !payMonth.equals("")) {
            criterion += " monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 李娟
   * @param orgId
   * @param payMonth
   * @return
   */
    
  public List queryMonthPaymentHeadAccount(final Serializable orgId,
      final String payMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead , MonthPayment monthPayment ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if (payMonth != null && !payMonth.equals("")) {
            criterion += " monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = "where monthPaymentHead.paymentHead.id = monthPayment.id and monthPayment.payStatus=5 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
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
   * 杨光
   * @param orgId
   * @param payMonth
   * @return
   */
  
  public List queryMonthPaymentHeadAccount_yg(final Serializable orgId,
      final String payMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          
          String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead , MonthPayment monthPayment ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (orgId != null && !orgId.equals("")) {
            criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if (payMonth != null && !payMonth.equals("")) {
            criterion += " monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = "where monthPaymentHead.paymentHead.id = monthPayment.id and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
          
          hql = hql + criterion;
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
   * 李娟
   * @param orgId
   * @param payMonth
   * @return
   */
    
  public List queryMonthPaymentHeadAccounts(final Serializable orgId,
      final String payMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if (payMonth != null && !payMonth.equals("")) {
            criterion += " monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
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
   * 王菱 根据单位ID查询缴存年月
   */
  public String getOrgMonthPayment(final String orgId) {
    Validate.notNull(orgId);
    String date = "";
    date = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select max(monthPaymentHead.payMonth) from MonthPaymentHead monthPaymentHead where monthPaymentHead.paymentHead.org.id=? ";
        Vector parameters = new Vector();
        parameters.add(new Integer(orgId));

        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));

        return query.uniqueResult();
      }
    });
    return date;
  }
  
  /**
   * 于庆丰　根据缴存头表ID查询汇缴头表一条记录
   * @param id
   * @return
   */
  public List queryByPaymentHeadId(final Serializable id) {
    
    List list = null;
    try{
    Validate.notNull(id);
    list = (List) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select monthPaymentHead from MonthPaymentHead monthPaymentHead where monthPaymentHead.paymentHead.id = ? ";
            Vector parameters = new Vector();
            parameters.add(id);

            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
  
            return query.list();
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
    
  /**
   * 根据缴存ID查询汇缴年月
   * @param paymentId
   * @return
   */
  public String getPayMonthLJ(final Serializable paymentId) {
    Validate.notNull(paymentId);
    String date="";
    date = (String)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select min(monthPaymentHead.payMonth) from MonthPaymentHead monthPaymentHead where monthPaymentHead.paymentHead.id=? ";
        Vector parameters = new Vector();
        parameters.add(new Integer(paymentId.toString()));
       
        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));
        
        return query.uniqueResult();
      }
    });
    return date;
}
  /**
   * 删除单个记录
   * @param monthPaymentHead
   */
  public void delete(MonthPaymentHead monthPaymentHead){
    Validate.notNull(monthPaymentHead);
    getHibernateTemplate().delete(monthPaymentHead);
  }
  /**
   * 删除list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }
  
  /**
   * jj20071218
   * 根据301表ID删除302
   * @param monthPaymentId
   */
  public void deleteMonthPaymentHead(final Integer paymentId){
    try{
  getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
           String sql="delete from MonthPaymentHead monthPaymentHead where monthPaymentHead.paymentHead.id = ? ";          
           session.createQuery(sql).setInteger(0, paymentId.intValue()).executeUpdate();
            return null;
        }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * jj20080918
   * 根据301表ID删除302
   * @param monthPaymentId
   */
  public void deleteMonthPaymentHead_jj(final Integer paymentId){
    try{
  getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
           String sql="delete MonthPaymentHead monthPaymentHead where monthPaymentHead.reserveaA is null and monthPaymentHead.paymentHead.id = ? ";          
           session.createQuery(sql).setInteger(0, paymentId.intValue()).executeUpdate();
            return null;
        }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 李娟
   * 
   * @param paymentId
   * @return
   */
  public List queryMonthPaymentHeadLJ(final Serializable paymentId){
    List list=null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead,Org org  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (paymentId != null&&!paymentId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentId.toString()));
              }
              if (criterion.length() != 0)
                criterion = "where  org.id=monthPaymentHead.paymentHead.org.id and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              hql = hql + criterion +" order by monthPaymentHead.id asc ";
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
   * 李娟
   * 根据单位ID查询单位补缴头表信息
   * @param orgid
   * @return
   */
  public List queryOrgaddpayHeadLJ(final Serializable orgid){
    List monthPaymentHead=null;
    try {
      monthPaymentHead =(List) getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead , OrgAddPay orgAddPay " ;
              Vector parameters = new Vector();
              String criterion = "";

              if (orgid != null&&!orgid.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              if (criterion.length() != 0)
                criterion = "where monthPaymentHead.paymentHead.id = orgAddPay.id and orgAddPay.payStatus=1 and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              hql = hql + criterion +"order by monthPaymentHead.payMonth ASC";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }   
              return query.list();
            }
          }

      );
//      if(monthPaymentHead==null){
//        monthPaymentHead = new MonthPaymentHead();
//      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthPaymentHead;
  }
  /**
   * 李娟
   * @param orgId
   * @param payMonth
   * @return
   */
    public List queryOrgAddPayHeadInfoLJ(final Serializable orgId,final String payMonth,final SecurityInfo securityInfo){
      List list=null;
      try {
        list = getHibernateTemplate().executeFind(
            new HibernateCallback() {

              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {

                String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead ";
                Vector parameters = new Vector();
                String criterion = "";

                if (orgId != null&&!orgId.equals("")) {
                  criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
                  parameters.add(new Integer(orgId.toString()));
                }
                if(payMonth != null && !payMonth.equals("")){
                  criterion +=" monthPaymentHead.payMonth = ? and  ";
                    parameters.add(payMonth);
                }
                if (criterion.length() != 0)
                  criterion = "where monthPaymentHead.paymentHead.payStatus<>5 and " +
                      " monthPaymentHead.paymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                      + criterion.substring(0, criterion.lastIndexOf("and"));
                
                hql = hql + criterion ;
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
     * 
     * @param orgId
     * @param payMonth
     * @return
     */
    public List queryMonthHeadInfoLJ(final Serializable orgId,final String payMonth){
      List list=null;
      try {
        list = getHibernateTemplate().executeFind(
            new HibernateCallback() {

              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {

                String hql = " select monthPaymentHead from MonthPaymentHead monthPaymentHead ";
                Vector parameters = new Vector();
                String criterion = "";

                if (orgId != null&&!orgId.equals("")) {
                  criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
                  parameters.add(new Integer(orgId.toString()));
                }
                if(payMonth != null && !payMonth.equals("")){
                  criterion +=" monthPaymentHead.payMonth = ? and  ";
                    parameters.add(payMonth);
                }
                if (criterion.length() != 0)
                  criterion = "where monthPaymentHead.paymentHead.payStatus = 5 and "
                      + criterion.substring(0, criterion.lastIndexOf("and"));
                
                hql = hql + criterion +"order by monthPaymentHead.id desc";
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
     * 李娟 根据单位ID查询已记账状态的最大汇缴年月
     * 状态为5
     * @param orgid
     * @return
     */
    public String queryMaxMonthOrgaddpay(final Serializable orgid) {
      String month = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select max(monthPaymentHead.payMonth) "
                  + "from MonthPaymentHead monthPaymentHead ";
              Vector parameters = new Vector();
              String criterion = "";

              if (orgid != null && !orgid.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              if (criterion.length() != 0)
                criterion = "where monthPaymentHead.paymentHead.payStatus = 5 and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              query.setParameter(0, parameters.get(0));
              return query.uniqueResult();
            }
          });

      return month;
    }
    /**
     * 李娟 
     * 查询小于代入年月的最大年月
     * @param orgid
     * @return
     */
    public String queryMaxMonthMnothpayMX(final Serializable orgid,final String payMonth) {
      String month = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select max(monthPaymentHead.payMonth) "
                  + "from MonthPaymentHead monthPaymentHead ";
              Vector parameters = new Vector();
              String criterion = "";

              if (orgid != null && !orgid.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              if (payMonth != null && !payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth < ?  and ";
                parameters.add(payMonth);
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }     
              return query.uniqueResult();
            }
          });

      return month;
    }
    /**
     * 李娟
     * 查询302
     * @param orgid
     * @param payMonth
     * @return
     */
    public List queryMnothpaymentHeadMX(final Serializable orgid,final String payMonth) {
      List list = null;
        list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from MonthPaymentHead monthPaymentHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null && !orgid.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              if (payMonth != null && !payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ?  and ";
                parameters.add(payMonth);
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }     
              return query.list();
            }
          });

      return list;
    }
    /**
     * 于庆丰
     * 根据缴存汇缴ID查询汇缴年月
     * @param paymentId
     * @return
     */
    public List getPayMonthyqf(final Serializable paymentId) {
      Validate.notNull(paymentId);
      List list=new ArrayList();
      list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select min(a.pay_month),max(a.pay_month) from AA302 a,AA301 b where a.pay_head_id=b.id and b.pay_type = 'A' and  a.pay_head_id  = ? ";
          Vector parameters = new Vector();
          parameters.add(new Integer(paymentId.toString()));
         
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          
          return query.list();
        }
      });
      return list;
  }
    /**
     * 于庆丰
     * 根据缴存单位补缴ID查询汇缴年月
     * @param paymentId
     * @return
     */
    public List getPayMonthbyyqf(final Serializable paymentId) {
      Validate.notNull(paymentId);
      List list=new ArrayList();
      list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select min(a.pay_month),max(a.pay_month) from AA302 a,AA301 b where a.pay_head_id=b.id and b.pay_type = 'B' and  a.pay_head_id  = ? ";
          Vector parameters = new Vector();
          parameters.add(new Integer(paymentId.toString()));
         
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          
          return query.list();
        }
      });
      return list;
  }

    //查询状态从aa301中
    public String queryStatusById(final Serializable id){
      String status="";
      try{
         status=(String)getHibernateTemplate().execute(
            new HibernateCallback(){
              public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String status;
                  String  hql = "select aa301.pay_status from aa301 aa301 where aa301.id= ?";              
                  Query query = session.createSQLQuery(hql);
                  query.setParameter(0, id);
                  status = query.uniqueResult().toString();            
                  return status;           
              }
            }
        );
      }catch(Exception s){
        s.printStackTrace();
      }
      return status;
    }     
    
    
 /*
  * 根据单位编号查询 付款银行的名称
  */   
  public  List  queryPaymentBankNameList(final Integer orgid)
  {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select aa2.id,aa2.OPEN_BANK_NAME from aa00201  aa2  where aa2.org_id=?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, orgid);
              Iterator it=query.list().iterator();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                PaymentBankNameDTO dto=new PaymentBankNameDTO();
                dto.setPaymentBankNameKey(obj[0].toString());
                dto.setPaymentBankNameValues(obj[1].toString());
                temp_list.add(dto);
              }
              return temp_list;
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /*
   * 查询收款单位信息
   */
  public List queryCollectionBankInfo(final String officecode,final String collBankid)
  {
    List list=new ArrayList();
    list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select bb105.CENTER_NAME,bb105.COLL_BANK_NAME,bb105.COLLECTION_BANK_ACC  from bb105  bb105  " +
            "  where bb105.OFFICE=? and bb105.COLL_BANK_ID=? ";
       
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, officecode);
        query.setParameter(1, collBankid);
        return query.list();
      }
    });
    return list;
  }
 /*
  * 查询付款单位银行
  */
  public String queryPaymentBankName(final Integer id){
    String status="";
    try{
       status=(String)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String status;
                String  hql = "select aa00201.OPEN_BANK_NAME from aa00201 aa00201 where aa00201.id= ?";              
                Query query = session.createSQLQuery(hql);
                query.setParameter(0, id);
                status = query.uniqueResult().toString();            
                return status;           
            }
          }
      );
    }catch(Exception s){
      s.printStackTrace();
    }
    return status;
  }     
  
  /*
   * 查询bb105中的cetername
   */
  public String queryCentername(final String  officecode,final String  bankcode ,final String  collbankid ){
    String status="";
    try{
       status=(String)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String status;
                String  hql = "select bb105.center_name from bb105 bb105 where bb105.office= ? and bb105.collection_bank_acc=? and bb105.coll_bank_id=? ";              
                Query query = session.createSQLQuery(hql);
                query.setParameter(0, officecode);
                query.setParameter(1, bankcode);
                query.setParameter(2, collbankid);
                status = query.uniqueResult().toString();            
                return status;           
            }
          }
      );
    }catch(Exception s){
      s.printStackTrace();
    }
    return status;
  }     
  
  /*
   * 查找制单人
   */
  public String queryMothPaymentMaker(final String monthPaymentid) {
    String status = "";
    try {
      status = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String status;
          String hql = "select aa319.operator from  aa319  aa319  where aa319.type='A' and aa319.action='2' and  aa319.bizid=? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, monthPaymentid);
          status = query.uniqueResult().toString();
          return status;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return status;
  }
  public String queryMonth(final Serializable orgid,final String payMonth) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(monthPaymentHead.payMonth) "
                + "from MonthPaymentHead monthPaymentHead ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += " monthPaymentHead.paymentHead.org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }
            if (payMonth != null && !payMonth.equals("")) {
              criterion += " monthPaymentHead.payMonth < ?  and monthPaymentHead.paymentHead.payStatus<3 and ";
              parameters.add(payMonth);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            } 
            if(query.uniqueResult()==null){
              return "";
            }else{
              return query.uniqueResult();
            }
          }
        });

    return month;
  }
}