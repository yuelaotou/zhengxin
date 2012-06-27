package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumCancel;

/**
 * 凭证号撤销维护 
 * 
 *@author 李娟
 *2007-6-19
 */
public class DocNumCancelDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public DocNumCancel queryById(Serializable id) {  
    Validate.notNull(id);
    return  (DocNumCancel) getHibernateTemplate().get(DocNumCancel.class,id);    
  }
  /**李鹏
   * 办事处下AA321是否存在删除的凭证号
   * @param id
   * @return
   */
  public List querybydocnum(final String officeid,final String date) {  
    Validate.notNull(officeid);
    List list = null;
    try{
      list=getHibernateTemplate().executeFind(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from DocNumCancel docNumCancel  where "+
            " docNumCancel.officeCode=? and docNumCancel.bizYearmonth=? order by docNumCancel.docNum ";
            Query query = session.createQuery(hql);
            query.setString(0,officeid);
            query.setString(1,date);
             return query.list();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 插入记录
   * @param docNumCancel
   * @return
   */
  public Serializable insert(DocNumCancel docNumCancel){
    Serializable id = null;
    try{    
    Validate.notNull(docNumCancel);
    id = getHibernateTemplate().save(docNumCancel);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 撤销到账确认-将作废的凭证号插入作废表
   */
  public void insertDocNumCancel(String docNum,String officeCode,String bizYearmonth)throws BusinessException{
    try{
     DocNumCancel docNumCancel=new DocNumCancel();
     docNumCancel.setDocNum(docNum);
     docNumCancel.setBizYearmonth(bizYearmonth);
     docNumCancel.setOfficeCode(officeCode);
    this.insert(docNumCancel);
    }catch(Exception e){
      throw new BusinessException("作废业务凭证号时出现错误!");
    }
  }  
}
