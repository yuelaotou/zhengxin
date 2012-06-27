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
import org.xpup.hafmis.syscollection.common.domain.entity.OfficePara;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.DocumentstopDTO;


/**
 * 办事处银行修改参数AA412
 * 
 *@author 王硕
 *2008-01-21
 */
public class OfficeParaDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OfficePara queryById(Serializable id) {  
    Validate.notNull(id);
    return  (OfficePara) getHibernateTemplate().get(OfficePara.class,new Integer(id.toString()));    
  }
  /**
   * 插入记录
   * 
   * @param collLoanbackPara
   * @return
   */
  public Serializable insert(OfficePara officePara) {
    Serializable id = null;
    try {
      Validate.notNull(officePara);
      id = getHibernateTemplate().save(officePara);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
 
  /**
   * 办事处银行修改参数设置
   * @author 王硕
   * 2008-01-21
   * 根据office查aa412表内容
   * 查询条件：office
   */
  public List queryOfficeParaByOffice(final String office) throws Exception {
    List list=null;
    try {
      list =(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select "+"distinct "+
                                  "aa412.param_value,"+
                                  "aa412.param_num,"+
                                  "aa412.param_explain,"+
                                  "aa412.param_explain_explain "+
                                  "from AA412 aa412 " +
                                  "where aa412.office=? ";
            Query query = session.createSQLQuery(hql);
            query.setString(0, office);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              OfficePara officePara=new OfficePara();
              obj = (Object[]) it.next();
              if(obj[0]!=null){
                officePara.setParamValue(obj[0].toString());
              }else{
                officePara.setParamValue("");
              }
              officePara.setParamNum(obj[1].toString());
              if(obj[2]!=null){
                officePara.setParamExplain(obj[2].toString());
              }else{
                officePara.setParamExplain("");
              }
              if(obj[3]!=null){
                officePara.setParamExplainExplain(obj[3].toString());
              }else{
                officePara.setParamExplainExplain("");
              }
              temp_list.add(officePara);
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
   * 办事处银行修改参数设置
   * @author 王硕
   * 2008-01-21
   * 根据office删除aa412表内容
   * 查询条件：office
   */
  public void deleteOfficePara(final String office){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete OfficePara officePara where officePara.office=? ";          
             session.createQuery(sql).setString(0, office).executeUpdate();
              return null;
          }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 根据参数数字查询参数值
   * @param num
   * @return
   * @throws Exception
   */
  public List queryparavalueBynum_WL(final String num) throws Exception {
    List list=null;
    try {
      list =(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.param_value from aa412 t where substr(t.param_num,0,INSTR(param_num, '_', 1) - 1)=? order by t.param_num";
            Query query = session.createSQLQuery(hql);
            query.setString(0, num);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj = null;
            while (it.hasNext()) {
              DocumentstopDTO dto=new DocumentstopDTO();
              obj = (Object) it.next();
              if(obj!=null){
                dto.setParavalue(obj.toString());
              }else{
                dto.setParavalue("");
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
   * 根据参数类型查询参数值
   * @return
   * @throws Exception
   */
  public String queryparavalueBytype_WL() throws Exception {
    String returnchar="";
    try {
      returnchar =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.param_value from aa412 t where t.setting_type='A'";
            Query query = session.createSQLQuery(hql);
            return query.uniqueResult();                                      
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnchar;
  }
  /**
   * 查询中国人民银行数据导出参数设置
   * 2008-02-15
   * @author 王硕
   */
  public List queryParamlist() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.param_value," +
              "a.param_num," +
              "a.setting_type " +             
              "from AA412 a " +
              "where a.setting_type is not null order by a.param_num";
          Query query = session.createSQLQuery(hql);
          Iterator it = query.list().iterator();
          List temp_List = new ArrayList();
          OfficePara officePara = null;
          Object[] obj = null;
          while (it.hasNext()) {
            officePara = new OfficePara();
            obj = (Object[])it.next();
            if (obj[0]!=null) {
              officePara.setParamValue(obj[0].toString());
            }
            if (obj[1]!=null) {
              officePara.setParamNum(obj[1].toString());
            }
            if (obj[2]!=null) {
              officePara.setSettingType(obj[2].toString());
            }
            temp_List.add(officePara);
          }
          return temp_List;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 中国人民银行数据导出参数设置
   * @author 王硕
   * 2008-01-21
   * 根据office删除aa412表内容
   * 查询条件：office
   */
  public void deleteOfficePara(){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete OfficePara officePara where officePara.settingType is not null ";          
             session.createQuery(sql).executeUpdate();
              return null;
          }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  /**
   * 根据参数序号删除参数设置
   * @param paramNum 参数序号
   * @author 付云峰
   */
  public void deleteOfficeParaByParamNum(final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete OfficePara officePara where officePara.paramNum=? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 根据参数序号查询参数值
   * @param paramNum
   * @return 参数值
   * @author 付云峰
   */
  public String queryAutoChangeParamByParamNum(final String paramNum) {
    String param = "";
    try {
      param = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a412.param_value from aa412 a412 where a412.param_num=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, paramNum);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return param;
  }
}
