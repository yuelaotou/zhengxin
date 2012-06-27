package org.xpup.hafmis.syscollection.common.dao;

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
import org.xpup.hafmis.syscollection.common.domain.entity.BankInfo;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.DocumentstopDTO;

/**
 * 中国人民银行信息
 * @author zhangl
 *
 */
public class BankInfoDAO extends HibernateDaoSupport{
  

  /**
   * 查询报文头信息以及参数设置类型
   * @return
   * @throws Exception
   */
  public List gettopInfo_WL() throws Exception {
    List list=null;
    try {
      list =(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.value,(select distinct a.setting_type from aa412 a where a.setting_type is not null) from aa413 t order by t.num";
            Query query = session.createSQLQuery(hql);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              DocumentstopDTO dto=new DocumentstopDTO();
              obj = (Object[]) it.next();
              if(obj[0]!=null){
                dto.setParavalue(obj[0].toString());
              }else{
                dto.setParavalue("");
              }
              if(obj[1]!=null){
                dto.setSettingtype(obj[1].toString());
              }else{
                dto.setSettingtype("");
              }
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
  
  /**
   * 插入信息 张列
   * @param bankInfo
   * @return
   */
  public Serializable insert(BankInfo bankInfo) {
    Serializable id = null;
    try {
      Validate.notNull(bankInfo);
      id = getHibernateTemplate().save(bankInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  
  /**
   * 获得BANKINFO 记录条数 张列
   * @return
   */
  public int countBankInfo(){
    int count = 0;
    try{
      List list = getHibernateTemplate().executeFind(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from BankInfo b  ";
              List parameters = new ArrayList();
              Query query = session.createQuery(hql);
              for(int i=0;i<parameters.size();i++){
                query.setParameter(i, parameters.get(i));
              }
              List l = query.list();
              return l;
            }
          }
      );
     count = list.size();
     return count;
    }catch(Exception s){
      s.printStackTrace();
    }
    return count;
  }
  
  /**
   * 查询BANKINFO 信息 张列
   * @return
   */
  public List queryBankInfo() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List resultList = new ArrayList();
          String sql1 = "select a413.value,a413.num from aa413 a413 ";
          Query query1 = session.createSQLQuery(sql1);
          List list1 = query1.list();
          for (int i = 0; i < list1.size(); i++) {
            Object obj = list1.get(i);
            resultList.add(obj);
          }
          return resultList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 修改BANKINFO 信息 张列
   * @param list
   */
  public void updateBankInfo(final List list){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          
          for(int i=1;i<7;i++){
            String hql = "update BankInfo bi set bi.value=? where bi.num=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, list.get(i-1));
            query.setParameter(1, i+"");
            query.executeUpdate();
          }
          
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}