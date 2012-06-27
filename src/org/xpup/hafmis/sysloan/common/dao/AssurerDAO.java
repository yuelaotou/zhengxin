package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
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
import org.xpup.hafmis.sysloan.common.domain.entity.Assurer;
import org.xpup.hafmis.sysloan.common.domain.entity.ImpawnContract;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public class AssurerDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public Assurer queryById(Serializable id) {  
    Validate.notNull(id);
    return  (Assurer) getHibernateTemplate().get(Assurer.class,id);    
  }

  /**
   * 插入记录
   * @param Assurer
   * @return
   */
  public Serializable insert(Assurer assurer){
    Serializable id = null;
    try{    
    Validate.notNull(assurer);
    id = getHibernateTemplate().save(assurer);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 根据主键删除
   * yuqf
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      // getHibernateTemplate().clear();
      Assurer sssurer = queryById(new Integer(id));

      getHibernateTemplate().delete(sssurer);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * yuqf
   * 根据合同编号查询
   * @param id
   * @return
   * 2007-09-29
   */
  public List queryIdByContractIdYU(final String id){
    List list = new ArrayList();
    try{
     list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.id from pl123 t where t.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.list();
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  /**
   * yuqf
   * 根据合同编号查询列表
   * @param id
   * @return
   * 2007-09-29
   */
  public List queryAssurerListYU(final String id){
    List list = new ArrayList();
    try{
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select t.id," +
                  "t.emp_name," +
                  "t.card_num," +
                  "t.balance," +
                  "t.salary," +
                  "t.month_pay," +
                  "t.org_name," +
                  "t.status," +
                  "t.emp_id," +
                  "t.photo_url," +
                  "t.sex," +
                  "t.birthday," +
                  "t.tel," +
                  "t.mobile," +
                  "t.home_tel," +
                  "t.home_addr," +
                  "t.home_mail," +
                  "t.org_addr," +
                  "t.org_tel," +
                  "t.org_mail," +
                  "t.org_id," +
                  "t.card_kind," +
                  "t.emp_st " +
                  " from pl123 t where t.contract_id=? order by t.id DESC ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);
              List tempList = new ArrayList();
              if(query.list().size() != 0){
                Iterator iterate = query.list().iterator();
                Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
                if(obj[0] != null){
                  endorsecontractTdAF.setId(obj[0].toString());//id
                }
                if(obj[1] != null){
                  endorsecontractTdAF.setEmpName(obj[1].toString());//职工姓名
                }
                if(obj[2] != null){
                  endorsecontractTdAF.setCardNum(obj[2].toString());//证件号码
                }
                if(obj[3] != null){
                  endorsecontractTdAF.setBalance(obj[3].toString());//余额
                }
                if(obj[4] != null){
                  endorsecontractTdAF.setSalary(obj[4].toString());//工资额
                }
                if(obj[5] != null){
                  endorsecontractTdAF.setMonthPay(obj[5].toString());//月缴存额度
                }
                if(obj[6] != null){
                  endorsecontractTdAF.setOrgName(obj[6].toString());//单位名称
                }
                if(obj[7] != null){
                  endorsecontractTdAF.setStatus(obj[7].toString());//状态
                }
                if(obj[8] != null){
                  endorsecontractTdAF.setHiddenEmpId(obj[8].toString());//职工ID
                }
                if(obj[9] != null){
                  endorsecontractTdAF.setPhotoUrl(obj[9].toString());//路径
                }
                if(obj[10] != null){
                  endorsecontractTdAF.setSex(obj[10].toString());//路径
                }
                if(obj[11] != null){
                  endorsecontractTdAF.setBirthday(obj[11].toString());//路径
                }
                if(obj[12] != null){
                  endorsecontractTdAF.setTel(obj[12].toString());//路径
                }
                if(obj[13] != null){
                  endorsecontractTdAF.setMobile(obj[13].toString());//路径
                }
                if(obj[14] != null){
                  endorsecontractTdAF.setHomeTel(obj[14].toString());//路径
                }
                if(obj[15] != null){
                  endorsecontractTdAF.setHomeAddr(obj[15].toString());//路径
                }
                if(obj[16] != null){
                  endorsecontractTdAF.setHomeMai(obj[16].toString());//路径
                }
                if(obj[17] != null){
                  endorsecontractTdAF.setOrgAddr(obj[17].toString());//路径
                }
                if(obj[18] != null){
                  endorsecontractTdAF.setOrgTel(obj[18].toString());//路径
                }
                if(obj[19] != null){
                  endorsecontractTdAF.setOrgMail(obj[19].toString());//路径
                }
                if(obj[20] != null){
                  endorsecontractTdAF.setOrgId(obj[20].toString());//路径
                }
                if(obj[21] != null){
                  endorsecontractTdAF.setCardKind(obj[21].toString());//路径
                }
                if(obj[22] != null){
                  endorsecontractTdAF.setEmpSt(obj[22].toString());//路径
                }
                tempList.add(endorsecontractTdAF);
               }
              }
              return tempList;
            }
          });
      }catch(Exception e){
        e.printStackTrace();
      }
    return list;
  }
  /**
   * yuqf
   * 根据合同编号查询最大ID
   * @param id
   * @return
   * 2007-09-29
   */
  public String queryMaxId(final String id){
    String maxId = (String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(t.id) from pl123 t where t.contract_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);
            if(query.uniqueResult() != null){
              return query.uniqueResult().toString();
            }else{
              return null;
            }
          }
        });

    return maxId;
  }
   /**
    * yuqf
    * 根据ID查询empId
    * @param id
    * @return
    * 2007-10-02
    */
   public String queryEmpId(final String id){
     String empId = "";
       empId = (String)getHibernateTemplate().execute(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = "select t.emp_id from pl123 t where t.id=?";
             Query query = session.createSQLQuery(hql);
             query.setParameter(0, id);
            
               return query.uniqueResult().toString();
            
           }
         });

     return empId;
   }
   /**
    * 根据 合同编号，职工姓名，职工身份证编号 判断PL123中该合同编号下是否存在该职工
    * @param contractId
    * @param empName
    * @param cardNum
    * @return
    */
   public String queryIdYU(final String contractId,final String empName,final String cardNum){
     String pkId = null;
     pkId = (String)getHibernateTemplate().execute(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = " select t.id from pl123 t where t.contract_id=? and t.emp_name=? and t.card_num=?";
             Query query = session.createSQLQuery(hql);
             query.setParameter(0, contractId);
             query.setParameter(1, empName);
             query.setParameter(2, cardNum);
             if(query.uniqueResult()!= null){
             return query.uniqueResult().toString();
             }else{
               return null;
             }
           }
         });

     return pkId;
   }
   /**
    * 判断同一合同下是否已经存在该担保人
    * @param contractId
    * @param empName
    * @param cardNum
    * @return
    * @author 付云峰
    */
   public Assurer queryCautionerInfo(final String contractId,final String empName,final String cardNum){
     Assurer assurer = null;
     assurer = (Assurer) getHibernateTemplate().execute(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = " from Assurer a where a.contractId=? and a.empName=? and a.cardNum=? and a.cardKind=0";
             Query query = session.createQuery(hql);
             query.setParameter(0, contractId);
             query.setParameter(1, empName);
             query.setParameter(2, cardNum);
             if(query.uniqueResult()!= null){
             return query.uniqueResult();
             }else{
               return null;
             }
           }
         });

     return assurer;
   }
   
   public List queryAssurerInfo(final String contractId){
     List list=new ArrayList();
     list = (List) getHibernateTemplate().executeFind(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = " from Assurer a where a.contractId=? and a.status=0";
             Query query = session.createQuery(hql);
             query.setParameter(0, contractId);
             return query.list();
           }
         });

     return list;
   }
}
