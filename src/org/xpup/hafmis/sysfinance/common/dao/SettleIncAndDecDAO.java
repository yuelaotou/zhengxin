package org.xpup.hafmis.sysfinance.common.dao;

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
import org.xpup.hafmis.sysfinance.common.domain.entity.SettleIncAndDec;
import org.xpup.hafmis.sysfinance.bookmng.settleincanddec.dto.SettleincanddecListDTO;

public class SettleIncAndDecDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public SettleIncAndDec queryById(Serializable id) {
    Validate.notNull(id);
    return (SettleIncAndDec) getHibernateTemplate().get(
        SettleIncAndDec.class, id);
  }

  /**
   * 插入记录
   * 
   * @param SettleIncAndDec
   * @return
   */
  public Serializable insert(SettleIncAndDec settleIncAndDec) {
    Serializable id = null;
    try {
      Validate.notNull(settleIncAndDec);
      id = getHibernateTemplate().save(settleIncAndDec);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 科目代码是否存在
   * @param code
   * @param securityInfo
   * @return
   */
  public String is_CodeIn_WL(final String code,final SecurityInfo securityInfo) {
    String flag="";
    flag = (String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            List list1=new ArrayList();
            List list2=new ArrayList();
            
            String hql1 = "select settleIncAndDec.bySubjectCode from SettleIncAndDec settleIncAndDec ";
            Vector parameters1 = new Vector();
            String criterion1 = "";
            
            criterion1 += "settleIncAndDec.bookId = ?  and ";
            parameters1.add(securityInfo.getBookId());
            
            if (code != null && !code.equals("")) {
              criterion1 += "settleIncAndDec.bySubjectCode = ?  and ";
              parameters1.add(code);
            }
            
            if (criterion1.length() != 0)
              criterion1 = "where "
                  + criterion1.substring(0, criterion1.lastIndexOf("and"));
            hql1 = hql1 + criterion1;
            Query query = session.createQuery(hql1);

            for (int i = 0; i < parameters1.size(); i++) {
              query.setParameter(i, parameters1.get(i));
            }
            list1= query.list();
            
            String hql2 = "select settleIncAndDec.subjectCode from SettleIncAndDec settleIncAndDec ";
            Vector parameters2 = new Vector();
            String criterion2 = "";
            
            criterion2 += "settleIncAndDec.bookId = ?  and ";
            parameters2.add(securityInfo.getBookId());
            
            if (code != null && !code.equals("")) {
              criterion2 += "settleIncAndDec.subjectCode = ?  and ";
              parameters2.add(code);
            }
            
            if (criterion2.length() != 0)
              criterion2 = "where "
                  + criterion2.substring(0, criterion2.lastIndexOf("and"));
            hql2 = hql2 + criterion2;
            query = session.createQuery(hql2);

            for (int i = 0; i < parameters2.size(); i++) {
              query.setParameter(i, parameters2.get(i));
            }
            list2= query.list();
            
            String temp_flag="no";
            if((list1!=null&&list1.size()!=0)||(list2!=null&&list2.size()!=0)){
              temp_flag="have";
            }
            return temp_flag;
          }
        });
    return flag;
  }
  /**
   * author wsh 损益结转设置 查询待结转科目代码在FN202.BY_SUBJECT_CODE中是否存在
   * 
   * @param subjectCode 科目代码
   * @param securityInfo 权限
   * @2007-10-25
   * @return
   */
  public Integer findSettleIncAndDecExist_wsh(final String bysubjectCode, final SecurityInfo securityInfo){
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.inc_dec_id) from fn202 a where a.book_id=? and a.by_subject_code=? ";
            Vector parameters = new Vector();            
            if (securityInfo.getBookId() != null) {
              parameters.add(securityInfo.getBookId());
            }
            if (bysubjectCode != null) {
              parameters.add(bysubjectCode);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }
  /**
   * author wsh 损益结转设置 查询列表信息
   * @param bookId 账套
   * @param orderBy 
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @2007-10-25
   * @return
   */
  public List querySettleIncAndDecList_wsh(final String bookId, final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.inc_dec_id ," + "a.by_subject_code,"
              + "(select b.subject_name " + "from fn110 b "
              + "where a.by_subject_code = b.subject_code and "
              + "b.book_id = a.book_id) as by_name," + "a.subject_code,"
              + "(select b.subject_name" + " from fn110 b "
              + "where a.subject_code = b.subject_code "
              + "and b.book_id = a.book_id) as name " + "from fn202 a "
              + "where a.book_id = ?";
          Vector parameters = new Vector();
          String criterion = "";         
          if (bookId != null && !bookId.equals("")) {           
            parameters.add(bookId);
          }         
          String ob = orderBy;
          if (ob == null)
            ob = " a.by_subject_code  ";
          String od = order;
          if (od == null)
            od = "ASC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list1 = new ArrayList();
          temp_list1 = query.list();
          if (temp_list1 == null || temp_list1.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              SettleincanddecListDTO settleincanddecListDTO = new SettleincanddecListDTO();
              if (obj[0] != null)
                settleincanddecListDTO.setId(obj[0].toString());               
              else
                settleincanddecListDTO.setId("");
              if (obj[1] != null)                
                settleincanddecListDTO.setBySubjectCode(obj[1].toString());
              else
                settleincanddecListDTO.setBySubjectCode("");
              if (obj[2] != null)
                settleincanddecListDTO.setBySubjectName(obj[2].toString());               
              else
                settleincanddecListDTO.setBySubjectName("");
              if (obj[3] != null)
                settleincanddecListDTO.setSubjectCode(obj[3].toString());                
              else
                settleincanddecListDTO.setSubjectCode("");
              if (obj[4] != null)
                settleincanddecListDTO.setSubjectName(obj[4].toString());
              else
                settleincanddecListDTO.setSubjectName("");
              temp_list.add(settleincanddecListDTO);
            }
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * author wsh 损益结转设置 查询列表信息
   * @param bookId 账套
   * @2007-10-25
   * @return
   */
  public int querySettleIncAndDecCountList_wsh(final String bookId) {
    Integer count = null;
    try {
      count = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(a.inc_dec_id)" 
              + " from fn202 a "
              + " where a.book_id = ?";
          Vector parameters = new Vector();
           
          if (bookId != null && !bookId.equals("")) {           
            parameters.add(bookId);
          }         
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }          
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }
  /**
   * author wsh  损益结转设置 删除时查询fn201记录是否存在
   * @param id fn202主键
   * @param bookid 账套
   * @2007-10-25
   * @return
   */
  public Integer findSettleIncAndDecExist_wsh(final String id,final String bookId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.inc_dec_id) from fn202 a where a.inc_dec_id=? and a.book_id=?";
            Vector parameters = new Vector();            
            if (id != null) {
              parameters.add(id);
            }
            if (bookId != null) {
              parameters.add(bookId);
            }           
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }
  /**
   * author wsh  损益结转设置 删除fn202记录
   * 
   * @param id fn202主键
   * @return
   */
  public void deletSettleIncAndDec_wsh(final String id,final String bookId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "delete from SettleIncAndDec settleIncAndDec  where settleIncAndDec.incDecId= ? and settleIncAndDec.bookId=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(id));
          query.setParameter(1, bookId);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
