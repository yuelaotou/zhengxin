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
import org.xpup.hafmis.sysloan.common.domain.entity.ImpawnContract;
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;

public class ImpawnContractDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ImpawnContract queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ImpawnContract) getHibernateTemplate().get(ImpawnContract.class,id);    
  }

  /**
   * 插入记录
   * @param ImpawnContract
   * @return
   */
  public Serializable insert(ImpawnContract impawnContract){
    Serializable id = null;
    try{    
    Validate.notNull(impawnContract);
    id = getHibernateTemplate().save(impawnContract);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 根据主键删除
   * 
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      // getHibernateTemplate().clear();
      ImpawnContract impawnContract = queryById(new Integer(id));

      getHibernateTemplate().delete(impawnContract);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * yuqf
   * 根据合同编号查询
   * @param id
   * @return
   */
  public List queryIdByContractIdYU(final String id){
    List list = new ArrayList();
    try{
     list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.id from pl122 t where t.contract_id=?";

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
   * 根据合同编号查询PL121
   * @param id
   * @return
   */
  public List queryImpawnListYU(final String id){
    List list = new ArrayList();
    try{
    list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.id," +
                "t.impawn_matter_name," +
                "t.impawn_value," +
                "t.status," +
                "t.name," +
                "t.photo_url," +
                "t.card_num," +
                "t.card_kind," +
                "t.paper_num," +
                "t.paper_name," +
                "t.tel," +
                "t.mobile," +
                "t.impawn_contract_id " +
                " from pl122 t where t.contract_id=? order by t.id DESC ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);
            List tempList = new ArrayList();
            if(query.list().size() != 0){
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
            while (iterate.hasNext()) {
              obj = (Object[]) iterate.next();
              EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
              if(obj[0] != null){
                endorsecontractTcAF.setId(obj[0].toString());//id
              }
              if(obj[1] != null){
                endorsecontractTcAF.setImpawnMatterName(obj[1].toString());//质押物名称
              }
              if(obj[2] != null){
                endorsecontractTcAF.setImpawnValue(obj[2].toString());//质押物价值
              }
              if(obj[3] != null){
                endorsecontractTcAF.setStatus(obj[3].toString());//合同状态
              }
              if(obj[4] != null){
                endorsecontractTcAF.setPaperPersonName(obj[4].toString());//所有权人姓名
              }
              if(obj[5] != null){
                endorsecontractTcAF.setPhotoUrl(obj[5].toString());
              }
              if(obj[6] != null){
                endorsecontractTcAF.setCarNum(obj[6].toString());
              }
              if(obj[7] != null){
                endorsecontractTcAF.setCardKind(obj[7].toString());
              }
              if(obj[8] != null){
                endorsecontractTcAF.setPaperNum(obj[8].toString());
              }
              if(obj[9] != null){
                endorsecontractTcAF.setPaperName(obj[9].toString());
              }
              if(obj[10] != null){
                endorsecontractTcAF.setTel(obj[10].toString());
              }
              if(obj[11] != null){
                endorsecontractTcAF.setMobile(obj[11].toString());
              }
              if(obj[12] != null){
                endorsecontractTcAF.setImpawnContractId(obj[12].toString());
              }
              tempList.add(endorsecontractTcAF);
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
   */
  public String queryMaxId(final String id){
    String maxId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(t.id) from pl122 t where t.contract_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult();
          }
        });

    return maxId;
  }
}
