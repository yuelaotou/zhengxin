package org.xpup.hafmis.orgstrct.dao;

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
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndCollBank;
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndOffice;
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndOrg;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndCollBank;
import org.xpup.hafmis.orgstrct.dto.OrgDto;

public class RelaRoleAndCollBankDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaRoleAndCollBank queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaRoleAndCollBank) getHibernateTemplate().get(RelaRoleAndCollBank.class,id);    
  }
  /**
   * 插入记录
   * @param relaRoleAndCollBank
   * @return
   */
  public Serializable insert(RelaRoleAndCollBank relaRoleAndCollBank){
    Serializable id = null;
    try{    
    Validate.notNull(relaRoleAndCollBank);
    id = getHibernateTemplate().save(relaRoleAndCollBank);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param relaRoleAndCollBank
   */
  public void delete(RelaRoleAndCollBank relaRoleAndCollBank){
    Validate.notNull(relaRoleAndCollBank);
    getHibernateTemplate().delete(relaRoleAndCollBank);
  }
  /**
   * 根据角色ID和银行ID查询
   * @param roleid
   * @param collbankid
   * @return
   */
  public RelaRoleAndCollBank queryRoleBank(final String roleid,final String collbankid){
    RelaRoleAndCollBank relaRoleAndCollBank = null;
    relaRoleAndCollBank = (RelaRoleAndCollBank)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " from RelaRoleAndCollBank relaRoleAndCollBank where relaRoleAndCollBank.roleid='"+roleid+"'" +
                  " and relaRoleAndCollBank.collbankid = '"+collbankid+"'";   

              Query query=session.createQuery(hql);     
              return query.uniqueResult();
          }
        });
    if(relaRoleAndCollBank == null){
      relaRoleAndCollBank = new RelaRoleAndCollBank();
    }
    return relaRoleAndCollBank;
  }
  /**
   * 角色下的银行
   * @param roleid
   * @return
   */
  public List queryBankByRoleid(final String roleid){
    List list=null;
    list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " select b.coll_bank_id as bankid,b.coll_bank_name as bankname,b.office as officeid,c.name as officename " +
              " from bb111 a,bb105 b,bb101 c " +
              " where b.status=1 and a.collbankid=b.coll_bank_id and b.office=c.id and a.roleid='"+roleid+"' order by officeid ";   

              Query query=session.createSQLQuery(hql);     
              List list=new ArrayList();
              Iterator it=query.list().iterator();    
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();    
                if(obj[0]!=null){
                  OrgDto orgDto=new OrgDto();
                  orgDto.setBankid(obj[0].toString());
                  orgDto.setBankname(obj[1].toString());
                  orgDto.setOfficeid(obj[2].toString());
                  orgDto.setOfficename(obj[3].toString());
                  list.add(orgDto);
                }
              }
              return list;
          }
        });
    return list;
  }
  /**
   * 待分配的银行
   * @param roleid
   * @return
   */
  public List querySpareBankByRoleid(final String roleid){
    List list=null;
    list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " select a.coll_bank_id as bankid,a.coll_bank_name as bankname,a.office as officeid,b.name as officename " +
              " from bb105 a,bb101 b " +
              " where a.status=1 and a.office=b.id " +
              " minus " +
              " select b.coll_bank_id as bankid,b.coll_bank_name as bankname,b.office as officeid,c.name as officename " +
              " from bb111 a,bb105 b,bb101 c " +
              " where b.status=1 and a.collbankid=b.coll_bank_id and b.office=c.id and a.roleid='"+roleid+"' order by officeid ";   

              Query query=session.createSQLQuery(hql);     
              List list=new ArrayList();
              Iterator it=query.list().iterator();    
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();    
                if(obj[0]!=null){
                  OrgDto orgDto=new OrgDto();
                  orgDto.setBankid(obj[0].toString());
                  orgDto.setBankname(obj[1].toString());
                  orgDto.setOfficeid(obj[2].toString());
                  orgDto.setOfficename(obj[3].toString());
                  list.add(orgDto);
                }
              }
              return list;
          }
        });
    return list;
  }
}
