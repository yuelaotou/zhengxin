package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.subjectbalance.dto.SubjectbalanceDTO;
import org.xpup.hafmis.sysfinance.accmng.subjectdayreport.dto.SubjectdayreportDTO;
import org.xpup.hafmis.sysfinance.accmng.subjectmuster.dto.SubjectmusterDTO;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto.CredencemodleDTO;
import org.xpup.hafmis.sysfinance.bookmng.subject.dto.SubjectDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.Subject;
import org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.dto.BalanceinitializeDTO;

public class SubjectDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public Subject queryById(Serializable id) {
    Validate.notNull(id);
    return (Subject) getHibernateTemplate().get(Subject.class, id);
  }

  /**
   * 插入记录
   * 
   * @param Subject
   * @return
   */
  public Serializable insert(Subject subject) {
    Serializable id = null;
    try {
      Validate.notNull(subject);
      id = getHibernateTemplate().save(subject);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 科目代码是否存在；若已经存在-返回对应的subjectId,不存在-返回""
   * 
   * @param code
   * @param states
   * @param securityInfo
   * @return
   */
  public String is_CodeIn_WL(final String code, final String states,
      final SecurityInfo securityInfo) throws BusinessException {
    String subjectId = "";
    subjectId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.subjectCode from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (code != null && !code.equals("")) {
              criterion += "subject.subjectCode = ?  and ";
              parameters.add(code);
            }
            if (states != null && !states.equals("")) {
              criterion += "subject.subjectSt = ?  and ";
              parameters.add(states);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            }
            return null;
          }
        });
    return subjectId;
  }

  /**
   * 根据科目类别查询科目信息
   * 
   * @param sortcodeflag
   * @param securityInfo
   * @return
   */
  public List findSubjectTree_WL(final String sortcodeflag,
      final SecurityInfo securityInfo) throws BusinessException {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " select subject.subjectId," 
            + " subject.subjectCode,"
            + " subject.subjectName," 
            + " subject.subjectLevel,"
            + " subject.parentSubjectCode," 
            + " subject.subjectSt "
            + " from Subject subject "
            + " where subject.subjectSt = 0 ";
        Vector parameters = new Vector();
        String criterion = "";

        criterion += "subject.bookId = ?  and ";
        parameters.add(securityInfo.getBookId());

        if (sortcodeflag != null && !sortcodeflag.equals("")) {
          criterion += "subject.subjectSortCode = ?  and ";
          parameters.add(sortcodeflag);
        }

        if (criterion.length() != 0)
          criterion = " and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion + " order by subject.subjectCode ";
        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        SubjectDTO subjectDTO = null;
        List tableList = new ArrayList();
        Object obj[] = null;
        Iterator iterate = query.list().iterator();

        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          subjectDTO = new SubjectDTO();
          subjectDTO.setSubjectId(obj[0].toString());
          subjectDTO.setSubjectCode(obj[1].toString());
          subjectDTO.setSubjectName(obj[2].toString());
          subjectDTO.setSubjectLevel(obj[3].toString());
          if (obj[4] == null) {
            subjectDTO.setParentSubjectCode("");
          } else {
            subjectDTO.setParentSubjectCode(obj[4].toString());
          }
          subjectDTO.setSubjectSt(obj[5].toString());

          tableList.add(subjectDTO);
        }

        return tableList;
      }
    });
    return list;
  }

  /**
   * 根据subjectId 查询科目信息是否存在
   * 
   * @param subjectId
   * @param securityInfo
   * @return
   */
  public String is_CodeInBySubjectId_WL(final String subjectId,
      final String states, final SecurityInfo securityInfo)
      throws BusinessException {
    String subjectCode = "";
    subjectCode = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.subjectCode from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (subjectId != null && !subjectId.equals("")) {
              criterion += "subject.subjectId = ?  and ";
              parameters.add(new Integer(subjectId));
            }
            if (states != null && !states.equals("")) {
              criterion += "subject.subjectSt = ?  and ";
              parameters.add(states);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult() == null)
              return "";
            else
              return query.uniqueResult().toString();
          }
        });
    return subjectCode;
  }

  /**
   * 科目代码在FN110.PARENT_SUBJECT_CODE中是否存在；若已经存在-返回对应的subjectId,不存在-返回""
   * 
   * @param code
   * @param states
   * @param securityInfo
   * @return
   */
  public List is_ParentCodeIn_WL(final String code, final String states,
      final SecurityInfo securityInfo) throws BusinessException {
    List subjectList = new ArrayList();
    subjectList = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select subject.subjectCode from Subject subject ";
        Vector parameters = new Vector();
        String criterion = "";

        criterion += "subject.bookId = ?  and ";
        parameters.add(securityInfo.getBookId());

        if (code != null && !code.equals("")) {
          criterion += "subject.parentSubjectCode = ?  and ";
          parameters.add(code);
        }
        if (states != null && !states.equals("")) {
          criterion += "subject.subjectSt = ?  and ";
          parameters.add(states);
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
    return subjectList;
  }

  /**
   * 得到科目的科目类别
   * 
   * @param code
   * @param securityInfo
   * @return
   */
  public String getSortcodeByCode_WL(final String code,
      final SecurityInfo securityInfo) throws BusinessException {
    String subjectId = "";
    subjectId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.subjectSortCode from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (code != null && !code.equals("")) {
              criterion += "subject.subjectCode = ?  and ";
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
            if (query.uniqueResult() == null)
              return "";
            else
              return query.uniqueResult().toString();
          }
        });
    return subjectId;
  }

  /**
   * 得到科目的科目方向
   * 
   * @param code
   * @param securityInfo
   * @return
   */
  public String getDirectionByCode_WL(final String code,
      final SecurityInfo securityInfo) throws BusinessException {
    String subjectDirection = "";
    subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.balanceDirection from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (code != null && !code.equals("")) {
              criterion += "subject.subjectCode = ?  and ";
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
            if (query.uniqueResult() == null)
              return "";
            else
              return query.uniqueResult().toString();
          }
        });
    return subjectDirection;
  }

  public String queryMaxCredenceNum(final String yearmonth, final String bookid)
      throws BusinessException {
    String credenceNum = "";
    credenceNum = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(to_number(t.credence_num)) from fn201 t "
                + "where substr(t.credence_date,0,6) = ? and t.book_id = ? ";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, yearmonth);
            query.setParameter(1, bookid);

            if (query.uniqueResult() == null)
              return "";
            else
              return query.uniqueResult().toString();
          }
        });
    return credenceNum;
  }

  /**
   * 得到科目的科目属性
   * 
   * @param code
   * @param securityInfo
   * @return
   */
  public String getProperyByCode_WL(final String code,
      final SecurityInfo securityInfo) throws BusinessException {
    String subjectId = "";
    subjectId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.subjectProperty from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (code != null && !code.equals("")) {
              criterion += "subject.subjectCode = ?  and ";
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
            if (query.uniqueResult() == null)
              return "";
            else
              return query.uniqueResult().toString();
          }
        });
    return subjectId;
  }

  /**
   * 删除科目
   * 
   * @param code
   */
  public void delete_WL(final String code, final SecurityInfo securityInfo)
      throws BusinessException {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from Subject subject where subject.subjectCode= ? and subject.bookId='"
              + securityInfo.getBookId() + "'";
          Query query = session.createQuery(sql);
          query.setParameter(0, code);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 修改
   * 
   * @param subject
   */
  public void update(Subject subject) throws BusinessException {
    try {
      Validate.notNull(subject);
      getHibernateTemplate().update(subject);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 根据科目代码，判断该条记录的FN110.PARENT_SUBJECT_CODE是否为空
   * 
   * @param code
   * @param states
   * @param securityInfo
   * @return
   */
  public String is_ParentCodeInByCode_WL(final String code,
      final String states, final SecurityInfo securityInfo)
      throws BusinessException {
    String subjectId = "";
    subjectId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.parentSubjectCode from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (code != null && !code.equals("")) {
              criterion += "subject.subjectCode = ?  and ";
              parameters.add(code);
            }
            if (states != null && !states.equals("")) {
              criterion += "subject.subjectSt = ?  and ";
              parameters.add(states);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult() == null)
              return "";
            else
              return query.uniqueResult().toString();
          }
        });
    return subjectId;
  }

  /**
   * 根据科目级别、科目状态和账套流水号查询出科目树
   * 
   * @param sortcodeflag
   * @param subjectSt
   * @param securityInfo
   * @return
   */
  public List findSubjectpopTree_WL(final String sortcodeflag,
      final List subjectSt, final SecurityInfo securityInfo)
      throws BusinessException {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select subject.subjectId, subject.subjectCode, subject.subjectName, subject.subjectLevel,subject.parentSubjectCode,subject.subjectSt from Subject subject ";
        Vector parameters = new Vector();
        String criterion = "";

        criterion += "subject.bookId = ?  and ";
        parameters.add(securityInfo.getBookId());

        if (sortcodeflag != null && !sortcodeflag.equals("")) {
          criterion += "subject.subjectSortCode = ?  and ";
          parameters.add(sortcodeflag);
        }

        if (subjectSt != null && subjectSt.size() > 0) {
          criterion += "( ";
          for (int i = 0; i < subjectSt.size(); i++) {
            criterion += " subject.subjectSt = ? or ";
            parameters.add(subjectSt.get(i).toString());
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion + " order by subject.subjectCode ";
        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        SubjectDTO subjectDTO = null;
        List tableList = new ArrayList();
        Object obj[] = null;
        Iterator iterate = query.list().iterator();

        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          subjectDTO = new SubjectDTO();
          subjectDTO.setSubjectId(obj[0].toString());
          subjectDTO.setSubjectCode(obj[1].toString());
          subjectDTO.setSubjectName(obj[2].toString());
          subjectDTO.setSubjectLevel(obj[3].toString());
          if (obj[4] == null) {
            subjectDTO.setParentSubjectCode("");
          } else {
            subjectDTO.setParentSubjectCode(obj[4].toString());
          }
          subjectDTO.setSubjectSt(obj[5].toString());

          tableList.add(subjectDTO);
        }

        return tableList;
      }
    });
    return list;
  }

  /**
   * 张列 获得科目代码和科目名称
   * 
   * @param bookId
   * @return lsit
   */
  public List getBalanceinitializeInfo(final String bookId) {
    Validate.notNull(bookId);
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select f110.subject_code,f110.subject_name from fn110 f110  "
              + "where f110.subject_property in ('0', '1') and f110.subject_code in "
              + "(select f.subject_code from fn110 f where f.book_id=? "
              + "minus "
              + "select f.parent_subject_code from fn110 f where f.book_id=? ) and f110.book_id=? order by f110.subject_code asc";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, bookId);
          query.setParameter(1, bookId);
          query.setParameter(2, bookId);
          if (query.list().size() == 0) {
            return null;
          }
          List li = new ArrayList();
          Object[] obj = null;
          Iterator it = query.list().iterator();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BalanceinitializeDTO balanceinitializeDTO = new BalanceinitializeDTO();
            balanceinitializeDTO.setSubjectCode(obj[0].toString());
            balanceinitializeDTO.setSubjectName(obj[1].toString());
            li.add(balanceinitializeDTO);
          }
          return li;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 张列 获得累计借方和累计贷方
   * 
   * @param bookId
   * @return lsit
   */
  public BalanceinitializeDTO getDebit(final String subjectCode,
      final String officeName, final String bookId) {
    Validate.notNull(subjectCode);
    Validate.notNull(officeName);
    Validate.notNull(bookId);
    BalanceinitializeDTO balanceinitializeDTO = null;
    try {
      balanceinitializeDTO = (BalanceinitializeDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              BalanceinitializeDTO balanceinitializeDTO = null;
              String hql1 = "select f210.credence_id from fn210 f210 "
                  + " where f210.subject_code=? and f210.book_id=?";
              Query query1 = session.createSQLQuery(hql1);
              query1.setParameter(0, subjectCode);
              query1.setParameter(1, bookId);
              if (query1.list().size() == 0) {
                balanceinitializeDTO = new BalanceinitializeDTO();
                balanceinitializeDTO.setDebit("0");
                return balanceinitializeDTO;
              } else {
                String hql = "select f2.debit from fn210 f2 where "
                    + "f2.subject_code = ? and f2.summray = "
                    + "(select ff.para_id from fn102 ff where "
                    + "ff.book_id = ? and ff.param_num = '4' and ff.param_value = '3') "
                    + "and f2.office = ? and f2.book_id = ? ";
                Query query = session.createSQLQuery(hql);
                query.setParameter(0, subjectCode);
                query.setParameter(1, bookId);
                query.setParameter(2, officeName);
                query.setParameter(3, bookId);
                if (query.list().size() == 0) {
                  balanceinitializeDTO = new BalanceinitializeDTO();
                  balanceinitializeDTO.setDebit("0");
                  return balanceinitializeDTO;
                } else {
                  Object temp_debit = query.uniqueResult();
                  balanceinitializeDTO = new BalanceinitializeDTO();
                  balanceinitializeDTO.setDebit(temp_debit.toString());
                }
                return balanceinitializeDTO;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return balanceinitializeDTO;
  }

  /**
   * author wsh 科目关系设置 查询输入的科目编号是否是末级科目 （调用前应判断该科目代码是否存在 调用is_CodeIn_WL）
   * 
   * @param subjectCode 科目代码
   * @param securityInfo 权限
   * @2007-10-16
   * @return Integer
   */
  public Integer findSubjectrelationParentId(final String subjectCode,
      final SecurityInfo securityInfo) {
    Integer id = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.subject_id) from fn110 a ";
            Vector parameters = new Vector();
            String criterion = "";
            criterion += "a.book_id = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "a.parent_subject_code = ?  and ";
              parameters.add(subjectCode);
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
    return id;
  }

  /**
   * 张列 取出 F110 表中的 SUBJECT_PROPERTY
   * 
   * @param bookId officeName
   * @return String
   */
  public String getSubjectProperty(final String bookId, final String subjectCode) {
    Validate.notNull(bookId);
    String stutas = "";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f110.subject_property from fn110 f110 "
              + "where f110.subject_code=? and f110.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, subjectCode);
          query.setParameter(1, bookId);
          if (query.uniqueResult() == null) {
            return "";
          } else {
            return query.uniqueResult().toString();
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  /**
   * author wsh 总账（查询条件） 判断科目代码是否是存并且是一级科目代码
   * 
   * @param subjectCode 科目代码
   * @param securityInfo 权限
   * @2007-10-23
   * @return Integer
   */
  public Integer findSubjectrelationFirstCode(final String subjectCode,
      final SecurityInfo securityInfo) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.subject_id) from fn110 a ";
            Vector parameters = new Vector();
            String criterion = "";
            criterion += "a.book_id = ?  and ";
            parameters.add(securityInfo.getBookId());
            if (subjectCode != null && !subjectCode.equals("")) {
              // 判断是否为一级科目不是作废
              criterion += "a.subject_level=1 and a.subject_code = ?  and a.subject_st='0'";
              // criterion += " a.subject_code = ? and a.subject_st='0'";
              parameters.add(subjectCode);
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
   * author wsh 凭证模式设置 查询科目代码在fn110中的科目名称
   * 
   * @param subjectCode 科目代码
   * @return
   */
  public CredencemodleDTO findSubejectRelationTaInfo(final String subjectCode,
      final SecurityInfo securityInfo) {
    CredencemodleDTO credencemodleDTO = (CredencemodleDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.subject_code subjectcode,a.subject_name subjectname from fn110 a where  a.subject_code=? and a.book_id=? ";
            Object[] obj = null;
            Vector parameters = new Vector();
            if (subjectCode != null) {
              parameters.add(subjectCode);
            }
            if (securityInfo.getBookId() != null) {
              parameters.add(securityInfo.getBookId());
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            obj = (Object[]) query.uniqueResult();
            CredencemodleDTO tempCredencemodleDTO = null;
            if (obj != null) {
              tempCredencemodleDTO = new CredencemodleDTO();
              if (obj[0] != null) {
                tempCredencemodleDTO.setSubjectCode((obj[0].toString()));
              }
              if (obj[1] != null) {
                tempCredencemodleDTO.setSubjectName(obj[1].toString());
              }
            }
            return tempCredencemodleDTO;
          }
        });
    return credencemodleDTO;
  }

  /**
   * 张列 查询1级科目代码 记录条数
   * 
   * @param bookId officeName
   * @return String
   */
  public String getSubjectCodeOneLevle(final String bookId,
      final String subjectCode) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f110.subject_id) from fn110 f110 "
              + "where f110.book_id=? and f110.subject_level=1 and f110.subject_code=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, subjectCode);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 查询凭证数
   * 
   * @param bookId
   * @return
   */
  public String getCountCredenceNum(final String bookId, final String yearMonth) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(distinct f201.credence_num) from fn201 f201 "
              + "where f201.book_id= ? and substr(f201.credence_date,0,6) = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, yearMonth);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 张列 根据条件查询一组 科目代码 针对FN110表
   * 
   * @param f110_bookId fn110表的bookId
   * @param subjectCodeStart 要从哪个1级科目代码开始查询
   * @param subjectCodeEnd 要查询1级科目代码结束位置
   * @param subjectLevelEnd 要查询科目代码的级数结束
   * @return
   */
  public String[] findSubjectCodesInfo(final String f110_bookId,
      final String subjectCodeStart, final String subjectCodeEnd,
      final String subjectLevelEnd) {
    String[] subjectCodes = null;
    subjectCodes = (String[]) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f110.book_id,f110.subject_code from fn110 f110 ";
            Vector parameters = new Vector();
            String criterion = "";

            // 查询条件
            if (f110_bookId != null && !f110_bookId.equals("")) {
              criterion += "f110.book_id = ? and ";
              parameters.add(f110_bookId);
            }
            if (subjectCodeStart != null && !subjectCodeStart.equals("")) {
              criterion += "f110.subject_code >= ? and ";
              parameters.add(subjectCodeStart);
            }
            if (subjectCodeEnd != null && !subjectCodeEnd.equals("")) {
              criterion += "f110.subject_code <= ? and ";
              parameters.add(subjectCodeEnd + "?");
            }
            if (subjectLevelEnd != null && !subjectLevelEnd.equals("")) {
              criterion += "(f110.subject_level between 1 and ? ) and ";
              parameters.add(subjectLevelEnd);
            }

            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion + " order by f110.subject_code asc";
            Query query = session.createSQLQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            Object obj[] = null;
            String[] subjectCodes = new String[query.list().size()];
            Iterator iterate = query.list().iterator();
            int temp_i = 0;
            while (iterate.hasNext()) {
              obj = (Object[]) iterate.next();
              subjectCodes[temp_i] = obj[1].toString();
              temp_i++;
            }
            return subjectCodes;
          }
        });
    return subjectCodes;
  }

  /**
   * author wsh 损益结转设置 查询待结转科目代码在FN110.BALANCE_DIRECTION
   * 
   * @param subjectCode 科目代码
   * @param securityInfo 权限
   * @2007-10-25
   * @return
   */
  public String findSettleIncAndDecSubjectDirection(final String bySubjectCode,
      final SecurityInfo securityInfo) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.balance_direction from fn110 a where a.subject_code=? and a.book_id=?";
            Vector parameters = new Vector();
            if (bySubjectCode != null && !bySubjectCode.equals("")) {
              parameters.add(bySubjectCode);
            }
            if (securityInfo.getBookId() != null
                && !securityInfo.getBookId().equals("")) {
              parameters.add(securityInfo.getBookId());
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据科目代码和bookid 获得科目名称 张列
   * 
   * @param subjectCode
   * @param bookId
   * @return
   */
  public String querySubjectNameBySubjectCode(final String subjectCode,
      final String bookId) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f110.subject_name from fn110 f110 "
                + "where f110.book_id = ? and f110.subject_code = ? ";
            Vector parameters = new Vector();
            if (bookId != null && !bookId.equals("")) {
              parameters.add(bookId);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              parameters.add(subjectCode);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据条件获得 昨日余额 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public String queryYesterdayRemainingSumBySubjectCode(
      final String subjectCode, final String bookId, final String credenceDate,
      final String officeName, final String credenceSt, final String credenceNum) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(abs(sum(f201.debit) - sum(f201.credit)),0) from fn201 f201 ";
            Vector parameters = new Vector();
            String criterion = "";

            if (bookId != null && !bookId.equals("")) {
              criterion += "f201.book_id = ? and ";
              parameters.add(bookId);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += "f201.credence_date < ? and ";
              parameters.add(credenceDate);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "f201.subject_code like ? and ";
              parameters.add(subjectCode + "%");
            }
            if (credenceSt != null && !credenceSt.equals("")) {
              criterion += "f201.credence_st = ? and ";
              parameters.add(credenceSt);
            }
            if (officeName != null && !officeName.equals("")) {
              criterion += "f201.office = ? and ";
              parameters.add(officeName);
            }
            if (credenceNum != null && !credenceNum.equals("")) {
              criterion += "f201.credence_num = ? and ";
              parameters.add(credenceNum);
            }

            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据条件获得 今日借方 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public String queryTodayDebitBySubjectCode(final String subjectCode,
      final String bookId, final String credenceDate, final String officeName,
      final String credenceSt, final String credenceNum) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(f201.debit),0) from fn201 f201 ";
            Vector parameters = new Vector();
            String criterion = "";

            if (bookId != null && !bookId.equals("")) {
              criterion += "f201.book_id = ? and ";
              parameters.add(bookId);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += "f201.credence_date = ? and ";
              parameters.add(credenceDate);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "f201.subject_code like ? and ";
              parameters.add(subjectCode + "%");
            }
            if (credenceSt != null && !credenceSt.equals("")) {
              criterion += "f201.credence_st = ? and ";
              parameters.add(credenceSt);
            }
            if (officeName != null && !officeName.equals("")) {
              criterion += "f201.office = ? and ";
              parameters.add(officeName);
            }
            if (credenceNum != null && !credenceNum.equals("")) {
              criterion += "f201.credence_num = ? and ";
              parameters.add(credenceNum);
            }

            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据条件获得 今日贷方 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public String queryTodayCreditBySubjectCode(final String subjectCode,
      final String bookId, final String credenceDate, final String officeName,
      final String credenceSt, final String credenceNum) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(f201.credit),0) from fn201 f201 ";
            Vector parameters = new Vector();
            String criterion = "";

            if (bookId != null && !bookId.equals("")) {
              criterion += "f201.book_id = ? and ";
              parameters.add(bookId);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += "f201.credence_date = ? and ";
              parameters.add(credenceDate);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "f201.subject_code like ? and ";
              parameters.add(subjectCode + "%");
            }
            if (credenceSt != null && !credenceSt.equals("")) {
              criterion += "f201.credence_st = ? and ";
              parameters.add(credenceSt);
            }
            if (officeName != null && !officeName.equals("")) {
              criterion += "f201.office = ? and ";
              parameters.add(officeName);
            }
            if (credenceNum != null && !credenceNum.equals("")) {
              criterion += "f201.credence_num = ? and ";
              parameters.add(credenceNum);
            }

            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据条件获得 今日余额 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public String queryTodayRemainingSumBySubjectCode(final String subjectCode,
      final String bookId, final String credenceDate, final String officeName,
      final String credenceSt, final String credenceNum) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(abs(sum(f201.debit) - sum(f201.credit)),0) from fn201 f201 ";
            Vector parameters = new Vector();
            String criterion = "";

            if (bookId != null && !bookId.equals("")) {
              criterion += "f201.book_id = ? and ";
              parameters.add(bookId);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += "f201.credence_date <= ? and ";
              parameters.add(credenceDate);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "f201.subject_code like ? and ";
              parameters.add(subjectCode + "%");
            }
            if (credenceSt != null && !credenceSt.equals("")) {
              criterion += "f201.credence_st = ? and ";
              parameters.add(credenceSt);
            }
            if (officeName != null && !officeName.equals("")) {
              criterion += "f201.office = ? and ";
              parameters.add(officeName);
            }
            if (credenceNum != null && !credenceNum.equals("")) {
              criterion += "f201.credence_num = ? and ";
              parameters.add(credenceNum);
            }

            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据条件获得 借贷方向 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public String queryDirectionBySubjectCode(final String subjectCode,
      final String bookId, final String credenceDate, final String officeName,
      final String credenceSt, final String credenceNum) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(f201.debit) - sum(f201.credit),0) from fn201 f201 ";
            Vector parameters = new Vector();
            String criterion = "";

            if (bookId != null && !bookId.equals("")) {
              criterion += "f201.book_id = ? and ";
              parameters.add(bookId);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += "f201.credence_date <= ? and ";
              parameters.add(credenceDate);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "f201.subject_code like ? and ";
              parameters.add(subjectCode + "%");
            }
            if (credenceSt != null && !credenceSt.equals("")) {
              criterion += "f201.credence_st = ? and ";
              parameters.add(credenceSt);
            }
            if (officeName != null && !officeName.equals("")) {
              criterion += "f201.office = ? and ";
              parameters.add(officeName);
            }
            if (credenceNum != null && !credenceNum.equals("")) {
              criterion += "f201.credence_num = ? and ";
              parameters.add(credenceNum);
            }

            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据条件获得 今日借方笔数 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public String queryTodayDebitSumBySubjectCode(final String subjectCode,
      final String bookId, final String credenceDate, final String officeName,
      final String credenceSt, final String credenceNum) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(f201.credence_id) from fn201 f201 "
                + "where (f201.debit > 0 or f201.debit < 0 ) and ";
            Vector parameters = new Vector();
            String criterion = "";

            if (bookId != null && !bookId.equals("")) {
              criterion += "f201.book_id = ? and ";
              parameters.add(bookId);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += "f201.credence_date = ? and ";
              parameters.add(credenceDate);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "f201.subject_code like ? and ";
              parameters.add(subjectCode + "%");
            }
            if (credenceSt != null && !credenceSt.equals("")) {
              criterion += "f201.credence_st = ? and ";
              parameters.add(credenceSt);
            }
            if (officeName != null && !officeName.equals("")) {
              criterion += "f201.office = ? and ";
              parameters.add(officeName);
            }
            if (credenceNum != null && !credenceNum.equals("")) {
              criterion += "f201.credence_num = ? and ";
              parameters.add(credenceNum);
            }

            if (criterion.length() != 0)
              criterion = criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 根据条件获得 今日贷方笔数 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public String queryTodayCreditSumBySubjectCode(final String subjectCode,
      final String bookId, final String credenceDate, final String officeName,
      final String credenceSt, final String credenceNum) {
    String subjectDirection = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(f201.credence_id) from fn201 f201 "
                + "where (f201.credit > 0 or f201.credit < 0 ) and ";
            Vector parameters = new Vector();
            String criterion = "";

            if (bookId != null && !bookId.equals("")) {
              criterion += "f201.book_id = ? and ";
              parameters.add(bookId);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += "f201.credence_date = ? and ";
              parameters.add(credenceDate);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "f201.subject_code like ? and ";
              parameters.add(subjectCode + "%");
            }
            if (credenceSt != null && !credenceSt.equals("")) {
              criterion += "f201.credence_st = ? and ";
              parameters.add(credenceSt);
            }
            if (officeName != null && !officeName.equals("")) {
              criterion += "f201.office = ? and ";
              parameters.add(officeName);
            }
            if (credenceNum != null && !credenceNum.equals("")) {
              criterion += "f201.credence_num = ? and ";
              parameters.add(credenceNum);
            }

            if (criterion.length() != 0)
              criterion = criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            return obj.toString();
          }
        });
    return subjectDirection;
  }

  /**
   * 查询范围内的所有末级科目代码
   * 
   * @param bookId
   * @param subjectCodeStart
   * @param subjectCodeEnd
   * @param subjectLevelEnd
   * @return
   */
  public String[] getSubjectCodesInfo_WL(final String subjectCodeStart,
      final String subjectCodeEnd, final String subjectLevelEnd,
      final SecurityInfo securityInfo) throws BusinessException {
    String[] subjectCodes = null;
    subjectCodes = (String[]) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select subject.subjectCode from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";
            criterion = " where subject.bookId='"
                + securityInfo.getBookId()
                + "' and subject.subjectCode not in "
                + " (select distinct subject_iin.parentSubjectCode from Subject subject_iin where subject_iin.bookId='"
                + securityInfo.getBookId()
                + "' and subject_iin.parentSubjectCode is not null) and  "
                + " not exists (select subject_in.parentSubjectCode from Subject subject_in where subject_in.bookId = '"
                + securityInfo.getBookId()
                + "' and subject_in.parentSubjectCode is not null and subject.subjectCode = subject_in.parentSubjectCode) and ";

            if (subjectCodeStart != null && !subjectCodeStart.equals("")) {
              criterion += "subject.subjectCode >= ? and ";
              parameters.add(subjectCodeStart);
            }
            if (subjectCodeEnd != null && !subjectCodeEnd.equals("")) {
              criterion += "subject.subjectCode <= ? and ";
              parameters.add(subjectCodeEnd + "?");
            }
            if (subjectLevelEnd != null && !subjectLevelEnd.equals("")) {
              criterion += "(subject.subjectLevel between 1 and ? ) and ";
              parameters.add(subjectLevelEnd);
            }

            if (criterion.length() != 0)
              criterion = criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion + " order by subject.subjectCode asc";
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            List temp_list = query.list();
            if (temp_list != null) {
              String[] subjectCodes = new String[temp_list.size()];
              for (int i = 0; i < temp_list.size(); i++) {
                String temp_code = (String) temp_list.get(i);
                subjectCodes[i] = temp_code;
              }
              return subjectCodes;
            } else {
              String[] subjectCodes = new String[0];
              return subjectCodes;
            }
          }
        });
    return subjectCodes;
  }

  /**
   * 根据条件查询出 昨日余额，今日借方，今日贷方，今日余额，方向，今日借方笔数，今日贷方笔数 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public SubjectdayreportDTO findSubjectdayreportInfo(final String subjectCode,
      final String bookId, final String credenceDate, final String officeName) {
    SubjectdayreportDTO subjectdayreportDTO = (SubjectdayreportDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "";
            Object[] obj = null;
            Vector parameters = new Vector();
            if (officeName.equals("")) {
              hql = "select (select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 "
                  + "where f201.book_id = ? and f201.credence_date < ? and f201.subject_code like ? "
                  + "and f201.credence_st = 2),"
                  + "(select nvl(sum(f201.debit), 0) from fn201 f201  where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? "
                  + "and f201.credence_st = 2),"
                  + "(select nvl(sum(f201.credit), 0) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? "
                  + "and f201.credence_st = 2),"
                  + "(select nvl(abs(sum(f201.debit) - sum(f201.credit)), 0) from fn201 f201 "
                  + "where f201.book_id = ? and f201.credence_date <= ? and f201.subject_code like ? "
                  + "and f201.credence_st = 2),"
                  + "(select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date <= ? and f201.subject_code like ? "
                  + "and f201.credence_st = 2),"
                  + "(select count(f201.credence_id) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? and (f201.debit > 0 or f201.debit < 0) "
                  + "and f201.credence_st = 2),"
                  + "(select count(f201.credence_id) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? and (f201.credit > 0 or f201.credit < 0) "
                  + "and f201.credence_st = 2) " + "from dual";
              for (int i = 1; i <= 7; i++) {
                parameters.add(bookId);
                parameters.add(credenceDate);
                parameters.add(subjectCode + "%");
              }
            } else {
              hql = "select (select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 "
                  + "where f201.book_id = ? and f201.credence_date < ? and f201.subject_code like ? "
                  + "and f201.office = ? and f201.credence_st = 2),"
                  + "(select nvl(sum(f201.debit), 0) from fn201 f201  where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? and f201.office = ? "
                  + "and f201.credence_st = 2),"
                  + "(select nvl(sum(f201.credit), 0) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? and f201.office = ? "
                  + "and f201.credence_st = 2),"
                  + "(select nvl(abs(sum(f201.debit) - sum(f201.credit)), 0) from fn201 f201 "
                  + "where f201.book_id = ? and f201.credence_date <= ? and f201.subject_code like ? "
                  + "and f201.office = ? and f201.credence_st = 2),"
                  + "(select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date <= ? and f201.subject_code like ?  and f201.office = ? "
                  + "and f201.credence_st = 2),"
                  + "(select count(f201.credence_id) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? and (f201.debit > 0 or f201.debit < 0) "
                  + "and f201.office = ? and f201.credence_st = 2),"
                  + "(select count(f201.credence_id) from fn201 f201 where f201.book_id = ? "
                  + "and f201.credence_date = ? and f201.subject_code like ? and (f201.credit > 0 or f201.credit < 0) "
                  + "and f201.office = ? and f201.credence_st = 2) "
                  + "from dual";
              for (int i = 1; i <= 7; i++) {
                parameters.add(bookId);
                parameters.add(credenceDate);
                parameters.add(subjectCode + "%");
                parameters.add(officeName);
              }
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            obj = (Object[]) query.uniqueResult();
            SubjectdayreportDTO subjectdayreportDTO = null;
            if (obj != null) {
              subjectdayreportDTO = new SubjectdayreportDTO();
              if (obj[0] != null) {
                subjectdayreportDTO.setYesterdayRemainingSum(new BigDecimal(
                    obj[0].toString()).abs());
                double temp = Double.parseDouble(obj[0].toString());
                if (temp > 0) {
                  subjectdayreportDTO.setDirectionYesterday("借");
                }
                if (temp < 0) {
                  subjectdayreportDTO.setDirectionYesterday("贷");
                }
                if (temp == 0) {
                  subjectdayreportDTO.setDirectionYesterday("平");
                }
              }
              if (obj[1] != null) {
                subjectdayreportDTO.setTodayDebit(new BigDecimal(obj[1]
                    .toString()));
              }
              if (obj[2] != null) {
                subjectdayreportDTO.setTodayCredit(new BigDecimal(obj[2]
                    .toString()));
              }
              if (obj[3] != null) {
                subjectdayreportDTO.setTodayRemainingSum(new BigDecimal(obj[3]
                    .toString()));
              }
              if (obj[4] != null) {
                double temp = Double.parseDouble(obj[4].toString());
                if (temp > 0) {
                  subjectdayreportDTO.setDirection("借");
                }
                if (temp < 0) {
                  subjectdayreportDTO.setDirection("贷");
                }
                if (temp == 0) {
                  subjectdayreportDTO.setDirection("平");
                }
              }
              if (obj[5] != null) {
                subjectdayreportDTO.setTodayDebitSum(Integer.parseInt(obj[5]
                    .toString()));
              }
              if (obj[6] != null) {
                subjectdayreportDTO.setTodayCreditSum(Integer.parseInt(obj[6]
                    .toString()));
              }
            }
            return subjectdayreportDTO;
          }
        });
    return subjectdayreportDTO;
  }

  /**
   * 张列 根据条件查询一组 科目代码 针对FN110表
   * 
   * @param f110_bookId fn110表的bookId
   * @param subjectCodeStart 要从哪个1级科目代码开始查询
   * @param subjectCodeEnd 要查询1级科目代码结束位置
   * @param subjectLevelEnd 要查询科目代码的级数结束
   * @param subjectSortCode 科目类别
   * @return
   */
  public String[] findSubjectCodesInfoBySubjectSortCode(
      final String f110_bookId, final String subjectCodeStart,
      final String subjectCodeEnd, final String subjectLevelEnd) {
    String[] subjectCodes = null;
    subjectCodes = (String[]) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f110.subject_sort_code,f110.subject_code from fn110 f110 ";
            Vector parameters = new Vector();
            String criterion = "";

            // 查询条件
            if (f110_bookId != null && !f110_bookId.equals("")) {
              criterion += "f110.book_id = ? and ";
              parameters.add(f110_bookId);
            }
            if (subjectCodeStart != null && !subjectCodeStart.equals("")) {
              criterion += "f110.subject_code >= ? and ";
              parameters.add(subjectCodeStart);
            }
            if (subjectCodeEnd != null && !subjectCodeEnd.equals("")) {
              criterion += "f110.subject_code <= ? and ";
              parameters.add(subjectCodeEnd + "?");
            }
            if (subjectLevelEnd != null && !subjectLevelEnd.equals("")) {
              criterion += "(f110.subject_level between 1 and ? ) and ";
              parameters.add(subjectLevelEnd);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion + " group by f110.subject_sort_code,f110.subject_code order by f110.subject_code asc";
            Query query = session.createSQLQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            Object obj[] = null;
            String[] subjectCodes = new String[query.list().size()];
            Iterator iterate = query.list().iterator();
            int temp_i = 0;
            while (iterate.hasNext()) {
              obj = (Object[]) iterate.next();
              subjectCodes[temp_i] = obj[1].toString();
              temp_i++;
            }
            return subjectCodes;
          }
        });
    return subjectCodes;
  }

  /**
   * 根据条件查询出 期初余额 本期发生额 本年累计发生额 期末余额 张列
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeCode
   * @return
   */
  public List findSubjectbalanceInfo(final String bookId, 
      final String subjectCodeStart,
      final String subjectCodeEnd, final String subjectLevel,
      final String credenceDateStart,
      final String credenceDateEnd, final String officeCode) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "";
        Vector parameters = new Vector();
        String criterion = "";
        if (officeCode.equals("")) {
          sql = " select f110.subject_code,"
              + " f110.subject_name,"
              + " max(f110.balance_direction),"
              + " (select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 "
              + " where f201.book_id = ? and substr(f201.credence_date,0,6) < ? and f201.subject_code like f110.subject_code||'%' "
              + " and f201.credence_st = 2),"

              + " (select nvl(sum(f201.debit), 0)||','||nvl(sum(f201.credit), 0) from fn201 f201  where f201.book_id = ? "
              + " and (substr(f201.credence_date,0,6) = ?) and f201.subject_code like f110.subject_code||'%' "
              + " and f201.credence_st = 2),"

              + " (select nvl(sum(f201.debit), 0)||','||nvl(sum(f201.credit), 0) from fn201 f201 where f201.book_id = ? "
              + " and (f201.credence_date between ? and ?) and f201.subject_code like f110.subject_code||'%' "
              + " and f201.credence_st = 2),"

              + " (select nvl(sum(f201.debit) - sum(f201.credit), 0)||','||nvl(sum(f201.credit) - sum(f201.debit), 0) from fn201 f201 "
              + " where f201.book_id = ? and f201.credence_date < ? and f201.subject_code like f110.subject_code||'%' "
              + " and f201.credence_st = 2),"
              
              + " max(f110.subject_st),"
              + " max(f110.expire_date)"

              + " from fn110 f110";
          parameters.add(bookId);
          parameters.add(credenceDateStart);

          parameters.add(bookId);
          parameters.add(credenceDateStart);

          parameters.add(bookId);
          parameters.add(credenceDateStart.substring(0, 4) + "0101");
          parameters.add(credenceDateEnd + "31");
          
          parameters.add(bookId);
          parameters.add(credenceDateStart.substring(0, 4) + "0101");

        } else {
          sql = " select f110.subject_code,"
              + " f110.subject_name,"
              + " max(f110.balance_direction),"
              + " (select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 "
              + " where f201.book_id = ? and substr(f201.credence_date,0,6) < ? and f201.subject_code like f110.subject_code||'%' "
              + " and f201.office = ? and f201.credence_st = 2),"

              + " (select nvl(sum(f201.debit), 0)||','||nvl(sum(f201.credit), 0) from fn201 f201  where f201.book_id = ? "
              + " and (substr(f201.credence_date,0,6) = ?) and f201.subject_code like f110.subject_code||'%' "
              + " and f201.office = ? and f201.credence_st = 2),"

              + " (select nvl(sum(f201.debit), 0)||','||nvl(sum(f201.credit), 0) from fn201 f201 where f201.book_id = ? "
              + " and (f201.credence_date between ? and ?) and f201.subject_code like f110.subject_code||'%' "
              + " and f201.office = ? and f201.credence_st = 2),"

              + " (select nvl(sum(f201.debit) - sum(f201.credit), 0)||','||nvl(sum(f201.credit) - sum(f201.debit), 0) from fn201 f201 "
              + " where f201.book_id = ? and f201.credence_date < ? and f201.subject_code like f110.subject_code||'%' "
              + " and f201.office = ? and f201.credence_st = 2),"
              
              + " max(f110.subject_st),"
              + " max(f110.expire_date)"

              + " from fn110 f110 where f110.book_id = ?";

          parameters.add(bookId);
          parameters.add(credenceDateStart);
          parameters.add(officeCode);

          parameters.add(bookId);
          parameters.add(credenceDateStart);
          parameters.add(officeCode);

          parameters.add(bookId);
          parameters.add(credenceDateStart.substring(0, 4) + "0101");
          parameters.add(credenceDateEnd + "31");
          parameters.add(officeCode);
          
          parameters.add(bookId);
          parameters.add(credenceDateStart.substring(0, 4) + "0101");
          parameters.add(officeCode);
        }
        // 查询条件
        if (bookId != null && !bookId.equals("")) {
          criterion += "f110.book_id = ? and ";
          parameters.add(bookId);
        }
        if (subjectCodeStart != null && !subjectCodeStart.equals("")) {
          criterion += "f110.subject_code >= ? and ";
          parameters.add(subjectCodeStart);
        }
        if (subjectCodeEnd != null && !subjectCodeEnd.equals("")) {
          criterion += "f110.subject_code <= ? and ";
          parameters.add(subjectCodeEnd + "?");
        }
        if (subjectLevel != null && !subjectLevel.equals("")) {
          criterion += "(f110.subject_level between 1 and ? ) and ";
          parameters.add(subjectLevel);
        }
        
        if (credenceDateStart != null && !credenceDateStart.equals("")) {
          criterion += "(f110.bizdate <= ? ) and ";
          parameters.add(credenceDateStart);
        }
        if (criterion.length() != 0)
          criterion = " where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        sql = sql + criterion + " group by f110.subject_code,f110.subject_name order by f110.subject_code asc ";
        Query query = session.createSQLQuery(sql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        List resultList = new ArrayList();
        Object obj[] = null;
        Iterator iterate = query.list().iterator();
        SubjectbalanceDTO dto = null;
        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          dto = new SubjectbalanceDTO();
          if (obj[0] != null) {
            dto.setSubjectCode(obj[0].toString());
          }
          if (obj[1] != null) {
            dto.setSubjectName(obj[1].toString());
          }

          if (obj[3] != null) {
            BigDecimal temp = new BigDecimal(obj[3].toString());
            if (temp.compareTo(new BigDecimal(0.00)) > 0) {
              dto.setFirstRemainingDebit(temp.abs());
              dto.setFirstRemainingCredit(new BigDecimal("0.00"));
            }
            if (temp.compareTo(new BigDecimal(0.00)) < 0) {
              dto.setFirstRemainingDebit(new BigDecimal("0.00"));
              dto.setFirstRemainingCredit(temp.abs());
            }
            if (temp.compareTo(new BigDecimal(0.00)) == 0) {
              dto.setFirstRemainingDebit(new BigDecimal("0.00"));
              dto.setFirstRemainingCredit(new BigDecimal("0.00"));
            }
            if (obj[2].toString().equals("0")) {
              dto.setFirstRemainingDebit(dto.getFirstRemainingDebit().subtract(
                  dto.getFirstRemainingCredit()));
            } else {
              dto.setFirstRemainingDebit(dto.getFirstRemainingCredit()
                  .subtract(dto.getFirstRemainingDebit()));
            }
            
          }
          if (obj[4] != null) {
            dto.setThisIssueDebit(new BigDecimal(obj[4].toString().split(",")[0]));
            dto.setThisIssueCredit(new BigDecimal(obj[4].toString().split(",")[1]));
          }
          if (obj[5] != null) {
            dto.setThisYearDebit(new BigDecimal(obj[5].toString().split(",")[0]));
            dto.setThisYearCredit(new BigDecimal(obj[5].toString().split(",")[1]));
          }
          if (obj[2].toString().equals("0")) {
            dto.setLastRemainingDebit(dto.getFirstRemainingDebit().add(
                dto.getThisIssueDebit().subtract(dto.getThisIssueCredit())));
            dto.setYearDebit(new BigDecimal(obj[6].toString().split(",")[0]));
          } else {
            dto.setLastRemainingDebit(dto.getFirstRemainingDebit().add(
                dto.getThisIssueCredit().subtract(dto.getThisIssueDebit())));
            dto.setYearDebit(new BigDecimal(obj[6].toString().split(",")[1]));
          }
          if (obj[7] != null) {
            dto.setSubjectSt(obj[7].toString());
          }
          if (dto.getSubjectSt().equals("0")) {
            resultList.add(dto);
          } else if(Integer.parseInt(obj[8].toString())>Integer.parseInt(credenceDateStart.substring(0,4))){
            resultList.add(dto);
          }
          /*if (obj[5] != null) {
            BigDecimal temp = new BigDecimal(obj[5].toString());
            if (temp.compareTo(new BigDecimal(0.00)) > 0) {
              dto.setLastRemainingDebit(temp.abs());
              dto.setLastRemainingCredit(new BigDecimal("0.00"));
            }
            if (temp.compareTo(new BigDecimal(0.00)) < 0) {
              dto.setLastRemainingDebit(new BigDecimal("0.00"));
              dto.setLastRemainingCredit(temp.abs());
            }
            if (temp.compareTo(new BigDecimal(0.00)) == 0) {
              dto.setLastRemainingDebit(new BigDecimal("0.00"));
              dto.setLastRemainingCredit(new BigDecimal("0.00"));
            }
            if (obj[6].toString().equals("0")) {
              dto.setLastRemainingDebit(dto.getLastRemainingDebit().subtract(
                  dto.getLastRemainingCredit()));
            } else {
              dto.setLastRemainingDebit(dto.getLastRemainingCredit().subtract(
                  dto.getLastRemainingDebit()));
            }
          }*/
        }
          
        /*
         * if (obj[6].toString().equals("0")) { if (obj[7] != null) {
         * subjectbalanceDTO.setYearDebit(new BigDecimal(obj[7].toString())); } }
         * else { if (obj[8] != null) { subjectbalanceDTO.setYearDebit(new
         * BigDecimal(obj[8].toString())); } }
         */
        return resultList;
      }
    });
    return list;
  }

  /**
   * 根据条件查询出 凭证汇总的 借方金额 贷方金额 张列
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   */
  public SubjectmusterDTO findSubjectmusterInfo(final String subjectCode,
      final String bookId, final String credenceDateStart,
      final String credenceDateEnd, final String officeName,
      final String credenceNumStart, final String credenceNumEnd) {
    SubjectmusterDTO subjectmusterDTO = (SubjectmusterDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "";
            Object[] obj = null;
            Vector parameters = new Vector();
            if (officeName.equals("")) {
              hql = "select  nvl(sum(f201.debit),0), nvl(sum(f201.credit),0) from fn201 f201 "
                  + "where f201.book_id = ? and (f201.credence_date between ? and ?) "
                  + "and f201.subject_code like ? ";
              parameters.add(bookId);
              parameters.add(credenceDateStart + "01");
              parameters.add(credenceDateEnd + "31");
              parameters.add(subjectCode + "%");
              if (!credenceNumStart.equals("") && !credenceNumEnd.equals("")) {
                hql = hql
                    + "and (f201.credence_num between to_number(?) and to_number(?)) ";
                parameters.add(credenceNumStart);
                parameters.add(credenceNumEnd);
              }
            } else {
              hql = "select  nvl(sum(f201.debit),0), nvl(sum(f201.credit),0) from fn201 f201 "
                  + "where f201.book_id = ? and (f201.credence_date between ? and ?) "
                  + "and f201.subject_code like ? and f201.office = ? ";
              parameters.add(bookId);
              parameters.add(credenceDateStart + "01");
              parameters.add(credenceDateEnd + "31");
              parameters.add(subjectCode + "%");
              parameters.add(officeName);
              if (!credenceNumStart.equals("") && !credenceNumEnd.equals("")) {
                hql = hql
                    + "and (f201.credence_num between to_number(?) and to_number(?)) ";
                parameters.add(credenceNumStart);
                parameters.add(credenceNumEnd);
              }
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            obj = (Object[]) query.uniqueResult();
            SubjectmusterDTO subjectmusterDTO = null;
            if (obj != null) {
              subjectmusterDTO = new SubjectmusterDTO();
              if (obj[0] != null) {
                subjectmusterDTO.setDebitSum(new BigDecimal(obj[0].toString()));
              }
              if (obj[1] != null) {
                subjectmusterDTO
                    .setCreditSum(new BigDecimal(obj[1].toString()));
              }
            }
            return subjectmusterDTO;
          }
        });
    return subjectmusterDTO;
  }

  /**
   * 科目代码是否存在；若已经存在-返回对应的subjectId,不存在-返回"" 判断该科目存在并且是末级科目 郭婧平
   * 
   * @param code
   * @param states
   * @param securityInfo
   * @param property
   * @return
   */
  public String is_CodeIn_GJP(final String code, final String states,
      final String property, final SecurityInfo securityInfo) {
    String subjectId = "";
    subjectId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.subjectCode from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (code != null && !code.equals("")) {
              criterion += "subject.subjectCode = ?  and "
                  + "not exists (select subject.subjectCode from Subject subject1 where subject1.parentSubjectCode=subject.subjectCode "
                  + "and subject1.bookId=subject.bookId ) and ";
              parameters.add(code);
            }
            if (states != null && !states.equals("")) {
              criterion += "subject.subjectSt = ?  and ";
              parameters.add(states);
            }
            if (property != null && !property.equals("")) {
              criterion += "subject.subjectProperty = ?  and ";
              parameters.add(property);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            }
            return null;
          }
        });
    return subjectId;
  }

  /**
   * author wsh 凭证模式设置 查询科目代码在fn110中的科目名称以及在fn210 中该凭证的相关信息
   * 
   * @param subjectCode 科目代码
   * @return
   */
  public CredencemodleDTO findCredencemodleUpdateInfo(final String subjectCode,
      final SecurityInfo securityInfo, final String id) {
    CredencemodleDTO credencemodleDTO = (CredencemodleDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.subject_code subjectcode,"
                + "a.subject_name subjectname,"
                + "b.subject_direction subjectdirection,"
                + " b.biz_type biztype," + "b.money_type moneytype,"
                + "b.summray summray " + " from fn110 a,fn120 b "
                + "where a.subject_code = b.subject_code "
                + "and a.subject_code =? " + "and a.book_id = b.book_id "
                + "and a.book_id = ? " + "and b.modle_id= ? ";
            Object[] obj = null;
            Vector parameters = new Vector();
            if (subjectCode != null) {
              parameters.add(subjectCode);
            }
            if (securityInfo.getBookId() != null) {
              parameters.add(securityInfo.getBookId());
            }
            if (id != null) {
              parameters.add(id);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            obj = (Object[]) query.uniqueResult();
            CredencemodleDTO tempCredencemodleDTO = null;
            if (obj != null) {
              tempCredencemodleDTO = new CredencemodleDTO();
              if (obj[0] != null) {
                tempCredencemodleDTO.setSubjectCode((obj[0].toString()));
              }
              if (obj[1] != null) {
                tempCredencemodleDTO.setSubjectName(obj[1].toString());
              }
              if (obj[2] != null) {
                tempCredencemodleDTO.setSubjectDirection(obj[2].toString());
              }
              if (obj[3] != null) {
                tempCredencemodleDTO.setBizType(obj[3].toString());
              }
              if (obj[4] != null) {
                tempCredencemodleDTO.setMoneyType(obj[4].toString());
              }
              if (obj[5] != null) {
                tempCredencemodleDTO.setSummray(obj[5].toString());
              }
            }
            return tempCredencemodleDTO;
          }
        });
    return credencemodleDTO;
  }

  /**
   * author wsh 凭证模式设置 判断科目代码是否是存并且是一级科目代码
   * 
   * @param subjectCode 科目代码
   * @param securityInfo 权限
   * @2007-10-23
   * @return Integer
   */
  public Integer findSubjectrelationFirstCode_wsh(final String subjectCode,
      final SecurityInfo securityInfo) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.subject_id) from fn110 a ";
            Vector parameters = new Vector();
            String criterion = "";
            criterion += "a.book_id = ?  and ";
            parameters.add(securityInfo.getBookId());
            if (subjectCode != null && !subjectCode.equals("")) {
              // 判断是否为一级科目不是作废
              // criterion += "a.subject_level=1 and a.subject_code = ? and
              // a.subject_st='0'";
              criterion += " a.subject_code = ?  and a.subject_st='0'";
              parameters.add(subjectCode);
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
   * 张列 取出 F110 表中的 SUBJECT_PROPERTY
   * 
   * @param bookId officeName
   * @return String
   */
  public String getSubjectProperty_wsh(final String bookId,
      final String subjectCode) {
    Validate.notNull(bookId);
    String stutas = "";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f110.subject_property from fn110 f110 "
              + "where f110.subject_code=? and f110.book_id=? and f110.subject_st='0'";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, subjectCode);
          query.setParameter(1, bookId);
          if (query.uniqueResult() == null) {
            return "";
          } else {
            return query.uniqueResult().toString();
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  /**
   * 查询该科目代码是否可用
   * @param bookId
   * @param subjectCode 查询的科目
   * @param date 查询的年月
   * @return
   * @throws Exception
   */
  public int checkSubjectCodeIsValid(final String bookId,
      final String subjectCode,final String date) throws Exception {
   String count = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select t.subject_st||','||t.expire_date from fn110 t";
            String criterion = "";
            Vector parameters = new Vector();
            String s = "0";
            if (bookId != null && !bookId.equals("")) {
              criterion += " t.book_id = ? and ";
              parameters.add(bookId);
            }
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += " t.subject_code = ? and ";
              parameters.add(subjectCode);
            }
            if (date != null && !date.equals("")) {
              criterion += " t.bizdate <= ?  and ";
              parameters.add(date);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            sql = sql + criterion;
            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult() != null) {
              String[] str = query.uniqueResult().toString().split(",");
              if (str[0].equals("0")) {
                s = "1";
              } else if (Integer.parseInt(str[1]) > Integer.parseInt(date
                  .substring(0, 4))) {
                s = "1";
              }
            }
            return s;
          }
        });
    return Integer.parseInt(count);
  }
  
  public String getNamePara() throws Exception {
    String param_value = "";
    param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select collPara.param_type from CollPara collPara where collPara.param_value='name' ";

            Query query = session.createQuery(hql);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return param_value;
  }
  /**
   * 查看该科目是否余额为零
   * @param bookId
   * @param subjectCode
   * @return
   * @throws Exception
   */
  public String checkIsExitBalance(final String bookId,
      final String subjectCode,final String date) throws Exception {
    String money = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select ("
                + " (select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 "
                + " where f201.book_id = ? and substr(f201.credence_date,0,6) < ? and f201.subject_code = ? "
                + " and f201.credence_st = 2) +"

                + " (select nvl(sum(f201.debit)-sum(f201.credit), 0) from fn201 f201  where f201.book_id = ? "
                + " and (substr(f201.credence_date,0,6) = ?) and f201.subject_code = ?  "
                + " and f201.credence_st = 2) +"

                + " (select nvl(sum(f201.debit)-sum(f201.credit), 0) from fn201 f201 where f201.book_id = ? "
                + " and (f201.credence_date between ? and ?) and f201.subject_code = ?  "
                + " and f201.credence_st = 2) +"

                + " (select nvl(sum(f201.debit) - sum(f201.credit), 0) from fn201 f201 "
                + " where f201.book_id = ? and f201.credence_date = ? and f201.subject_code = ?  "
                + " and f201.credence_st = 2)) from dual";

            Query query = session.createSQLQuery(sql);
            Vector parameters = new Vector();
            parameters.add(bookId);
            parameters.add(date);
            parameters.add(subjectCode);

            parameters.add(bookId);
            parameters.add(date);
            parameters.add(subjectCode);

            parameters.add(bookId);
            parameters.add(date.substring(0, 4) + "0101");
            parameters.add(date + "31");
            parameters.add(subjectCode);
            
            parameters.add(bookId);
            parameters.add(date.substring(0, 4) + "0101");
            parameters.add(subjectCode);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult().toString();
          }
        });
    return money;
  }
  public String queryPreBalance(final String bookId, final String subjectCode,
      final String startDate, final String endDate) throws Exception {
    String money = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select nvl(sum(t.debit),0)||','||nvl(sum(t.credit),0) from fn201 t where t.book_id = ? "
                + " and substr(t.credence_date,0,6) >= ? and substr(t.credence_date,0,6) < ? "
                + " and t.subject_code = ? and t.credence_st = 2";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, bookId);
            query.setParameter(1, startDate);
            query.setParameter(2, endDate);
            query.setParameter(3, subjectCode);
            return query.uniqueResult().toString();
          }
        });
    return money;
  }
}

