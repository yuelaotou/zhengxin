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
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto.CredencemodleListDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.CredenceModle;

public class CredenceModleDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public CredenceModle queryById(Serializable id) {
    Validate.notNull(id);
    return (CredenceModle) getHibernateTemplate().get(
        CredenceModle.class, id);
  }

  /**
   * 插入记录
   * 
   * @param BookParameter
   * @return
   */
  public Serializable insert(CredenceModle credenceModle) {
    Serializable id = null;
    try {
      Validate.notNull(credenceModle);
      id = getHibernateTemplate().save(credenceModle);
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
  public List is_CodeIn_WL(final String code,final SecurityInfo securityInfo) {
    List list=new ArrayList();
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select credenceModle.subjectCode from CredenceModle credenceModle ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "credenceModle.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());
            
            if (code != null && !code.equals("")) {
              criterion += "credenceModle.subjectCode = ?  and ";
              parameters.add(code);
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
   * author wsh 凭证模式设置 查询科目代码的业务类型bizType，金额类型moneyType在fn120的记录是否存在
   * 
   * @param subjectCode 科目代码
   * @param bizType 业务类型
   * @param moneyType 金额类型
   * @param securityInfo 权限
   * @2007-10-24
   * @return
   */
  public Integer findCredencemodleInfoExist_wsh(final String subjectCode, final String bizType,final String moneyType, final SecurityInfo securityInfo,final String subjectDirection){
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.modle_id) from fn120 a  ";
            
            Vector parameters = new Vector();
            String criterion = "";
            if (bizType != null) {
              parameters.add(bizType);
              criterion += "a.biz_type = ?  and ";
            }
            if (moneyType != null) {
              parameters.add(moneyType);
              criterion += "a.money_type = ?  and ";
            }            
            if (securityInfo.getBookId() != null) {
              parameters.add(securityInfo.getBookId());
              criterion += "a.book_id = ?  and ";
            }
            if (subjectCode != null) {
              parameters.add(subjectCode);
              criterion += "a.subject_code = ?  and ";
            }
            if (subjectDirection != null) {
              parameters.add(subjectDirection);
              criterion += "a.subject_direction = ?  and ";
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            
            hql = hql + criterion;
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
   * author wsh 凭证模式设置 更新fn120 set 科目方向,summary 摘要 
   * 查询条件是bookId,bizType,moneyType,subjectCode
   * @param subjectCode 科目代码
   * @param bizType 业务类型
   * @param moneyType 金额类型
   * @param summary 摘要 
   * @param securityInfo 权限 
   * @2007-10-24
   * @return
   */
  public void updateCredencemodle_wsh(final String subjectCode,final  String bizType, final String moneyType, final String subjectDirection,final  String summary,final  SecurityInfo securityInfo){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update CredenceModle credenceModle set credenceModle.subjectDirection=? ,credenceModle.summray=? "
              + "where credenceModle.subjectCode=? "
              + "and credenceModle.bizType=? "
          + "and credenceModle.moneyType=? "
          + "and credenceModle.subjectDirection=? "
          + "and credenceModle.bookId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, subjectDirection);
          query.setString(1, summary);
          query.setString(2, subjectCode);
          query.setString(3, bizType);
          query.setString(4, moneyType);
          query.setString(5, subjectDirection);
          query.setString(6, securityInfo.getBookId());
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * author wsh 凭证模式设置 查询列表信息
   * @param subjectCode 科目代码
   * @param subjectName 科目名称
   * @param subjectDirection 科目方向
   * @param bizType 业务类型
   * @param orderBy 
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @2007-10-24
   * @return
   */
  public List queryCredencemodleList_wsh(final String subjectCode,
      final String subjectName, final String subjectDirection,
      final String bizType,final String bookId, final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct b.modle_id modleid ,"
              + " b.biz_type biztype," + "b.subject_code subjectcode,"
              + " a.subject_name subjectname,"
              + " b.subject_direction direction, " + "b.money_type moneyType "
              + " from fn110 a,fn120 b "
              + " where a.subject_code=b.subject_code and a.book_id=b.book_id ";
          Vector parameters = new Vector();
          String criterion = "";
          if (subjectCode != null && !subjectCode.equals("")) {
            criterion += " b.subject_code=? and ";
            parameters.add(subjectCode);
          }
          if (subjectName != null && !subjectName.equals("")) {
            criterion += " a.subject_name=? and ";
            parameters.add(subjectName);
          }
          if (subjectDirection != null && !subjectDirection.equals("")) {
            criterion += " b.subject_direction=? and ";
            parameters.add(subjectDirection);
          }

          if (bizType != null && !bizType.equals("")) {
            criterion += " b.biz_type=? and ";
            parameters.add(bizType);
          }
          if (bookId != null && !bookId.equals("")) {
            criterion += " b.book_id=? and ";
            parameters.add(bookId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " b.modle_id  ";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List list = query.list();
          if (list == null || list.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            list = query.list();
          }
          Iterator it = list.iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              CredencemodleListDTO credencemodleListDTO = new CredencemodleListDTO();
              if (obj[0] != null)
                credencemodleListDTO.setId(obj[0].toString());
              else
                credencemodleListDTO.setId("");
              if (obj[1] != null)

                credencemodleListDTO.setBizType(obj[1].toString());
              else
                credencemodleListDTO.setBizType("");
              if (obj[2] != null)
                credencemodleListDTO.setSubjectCode(obj[2].toString());
              else
                credencemodleListDTO.setSubjectCode("");
              if (obj[3] != null) {
                credencemodleListDTO.setSubjectName(obj[3].toString());
              } else
                credencemodleListDTO.setSubjectName("");
              if (obj[4] != null)
                credencemodleListDTO.setBalanceDirection(obj[4].toString());

              else
                credencemodleListDTO.setBalanceDirection("");
              if (obj[5] != null)
                credencemodleListDTO.setMoneyType(obj[5].toString());
              else
                credencemodleListDTO.setMoneyType("");
              temp_list.add(credencemodleListDTO);
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
   * author wsh 凭证模式设置 查询总记录数量
   * @param subjectCode 科目代码
   * @param subjectName 科目名称
   * @param subjectDirection 科目方向
   * @param bizType 业务类型
   * @2007-10-24
   * @return
   */
  public int queryCredencemodleCountList_wsh(final String subjectCode,
      final String subjectName, final String subjectDirection,
      final String bizType, final String bookId) {
    Integer count = null;
    try {     
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct b.modle_id) " 
               + " from fn110 a,fn120 b "
               + " where a.subject_code=b.subject_code and a.book_id=b.book_id  ";
          Vector parameters = new Vector();
          String criterion = "";
          if (subjectCode != null && !subjectCode.equals("")) {
            criterion += " b.subject_code=? and ";
            parameters.add(subjectCode);
          }
          if (subjectName != null && !subjectName.equals("")) {
            criterion += " a.subject_name=? and ";
            parameters.add(subjectName);
          }
          if (subjectDirection != null && !subjectDirection.equals("")) {
            criterion += " b.subject_direction=? and ";
            parameters.add(subjectDirection);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += " b.biz_type=? and ";
            parameters.add(bizType);
          }
          if (bookId != null && !bookId.equals("")) {
            criterion += " b.book_id=? and ";
            parameters.add(bookId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
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
   * author wsh 凭证模式设置 删除时查询fn120记录是否存在
   * @param id fn120主键
   * @param bookid 账套
   * @2007-10-24
   * @return
   */
  public Integer findCredencemodleExist_wsh(final String id,final String bookId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.modle_id) from fn120 a where a.modle_id=? and a.book_id=?";
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
   * author wsh  凭证模式设置
   * 删除记录
   * @param subjectreleid fn111主键
   * @return
   */
  public void deleteCredencemodle_wsh(final String id,final String bookId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "delete from CredenceModle credenceModle  where credenceModle.modleId= ? and credenceModle.bookId=? ";
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

