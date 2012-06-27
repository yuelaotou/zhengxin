package org.xpup.hafmis.sysloan.common.dao;

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
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBankPara;

/**
 * 银行贷款参数PL003
 * 
 * @author 李娟 2007-9-13
 */
public class LoanBankParaDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public LoanBankPara queryById(Serializable id) {
    Validate.notNull(id);
    return (LoanBankPara) getHibernateTemplate().get(LoanBankPara.class,
        new Integer(id.toString()));
  }

  /**
   * 插入记录
   * 
   * @param loanBankPara
   * @return
   */
  public Serializable insert(LoanBankPara loanBankPara) {
    Serializable id = null;
    try {
      Validate.notNull(loanBankPara);
      id = getHibernateTemplate().save(loanBankPara);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * yuqf 查询参数序号为7 类型为A的param_value
   * 
   * @return
   */
  public String queryParamvalueYU() {
    String paramV = "";
    try {
      paramV = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct t.param_value from pl003 t where t.param_num='7' and t.param_type='A'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult();
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramV;
  }

  /**
   * yuqf 查询PL003中参数描述字段为"贷款流程"的记录中的参数值
   * 
   * @return
   */
  public String queryParamvalueYU_() {
    String paramV = "";
    try {
      paramV = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.param_value from pl003 t where t.param_descrip = '贷款流程'";
          Query query = session.createSQLQuery(hql);
          if (query.uniqueResult() != null) {
            return query.uniqueResult().toString();
          } else {
            return null;
          }
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramV;
  }

  /**
   * shiy 用于查找银行对应贷款参数 2007.09.22 param officecode return list
   */
  public List queryLoanBankPara_sy(final Integer bankId) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBankPara.loanParamId from LoanBankPara loanBankPara where loanBankPara.paramNum='9' and loanBankPara.paramType='A' and loanBankPara.paramValue='0' and loanBankPara.loanBankId=? ";
          Query query = session.createQuery(hql);
          query.setBigDecimal(0, new BigDecimal(bankId.toString()));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据银行ID查询参数值 回收办理 jj
   * 
   * @param loanBankId
   * @return
   */
  public String queryParamValue_LJ(final Integer loanBankId,
      final String paramType, final String paramNum) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanBankPara.paramValue "
                  + " from LoanBankPara loanBankPara ";
              // " where loanBankPara.paramType='A' and
              // loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
              Vector parameters = new Vector();
              String criterion = "";

              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanBankPara.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId.toString()));
              }
              if (paramType != null && !paramType.equals("")) {
                criterion += "loanBankPara.paramType = ?  and ";
                parameters.add(paramType);
              }
              if (paramNum != null && !paramNum.equals("")) {
                criterion += "loanBankPara.paramNum = ?  and ";
                parameters.add(paramNum);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  public String queryParamValue_LJ_1(final Integer loanBankId,
      final String paramType, final String paramNum) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanBankPara.paramExplain "
                  + " from LoanBankPara loanBankPara ";
              // " where loanBankPara.paramType='A' and
              // loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
              Vector parameters = new Vector();
              String criterion = "";

              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanBankPara.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId.toString()));
              }
              if (paramType != null && !paramType.equals("")) {
                criterion += "loanBankPara.paramType = ?  and ";
                parameters.add(paramType);
              }
              if (paramNum != null && !paramNum.equals("")) {
                criterion += "loanBankPara.paramNum = ?  and ";
                parameters.add(paramNum);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  public String queryParamValue_wsh_1(final Integer loanBankId,
      final String paramType, final String paramNum, final String paraValue) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanBankPara.paramExplain "
                  + " from LoanBankPara loanBankPara ";
              // " where loanBankPara.paramType='A' and
              // loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
              Vector parameters = new Vector();
              String criterion = "";

              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanBankPara.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId.toString()));
              }
              if (paramType != null && !paramType.equals("")) {
                criterion += "loanBankPara.paramType = ?  and ";
                parameters.add(paramType);
              }
              if (paramNum != null && !paramNum.equals("")) {
                criterion += "loanBankPara.paramNum = ?  and ";
                parameters.add(paramNum);
              }
              if (paraValue != null && !paraValue.equals("")) {
                criterion += "loanBankPara.paramValue = ?  and ";
                parameters.add(paraValue);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  public String queryParamValueCount_wsh(final Integer loanBankId,
      final String paramType, final String paramNum) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(distinct loanBankPara.paramValue) "
                  + " from LoanBankPara loanBankPara ";
              // " where loanBankPara.paramType='A' and
              // loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
              Vector parameters = new Vector();
              String criterion = "";

              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanBankPara.loanBankId != 100  and ";

              }
              if (paramType != null && !paramType.equals("")) {
                criterion += "loanBankPara.paramType = ?  and ";
                parameters.add(paramType);
              }
              if (paramNum != null && !paramNum.equals("")) {
                criterion += "loanBankPara.paramNum = ?  and ";
                parameters.add(paramNum);
              }

              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult().toString();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  public String queryParamExpCount_wsh(final Integer loanBankId,
      final String paramType, final String paramNum) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(distinct loanBankPara.paramExplain) "
                  + " from LoanBankPara loanBankPara ";
              // " where loanBankPara.paramType='A' and
              // loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
              Vector parameters = new Vector();
              String criterion = "";

              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanBankPara.loanBankId != 100  and ";

              }
              if (paramType != null && !paramType.equals("")) {
                criterion += "loanBankPara.paramType = ?  and ";
                parameters.add(paramType);
              }
              if (paramNum != null && !paramNum.equals("")) {
                criterion += "loanBankPara.paramNum = ?  and ";
                parameters.add(paramNum);
              }

              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult().toString();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  public String queryParamExpCount_wsh(final Integer loanBankId,
      final String paramType, final String paramNum, final String paravalue) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(distinct loanBankPara.paramExplain) "
                  + " from LoanBankPara loanBankPara ";
              // " where loanBankPara.paramType='A' and
              // loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
              Vector parameters = new Vector();
              String criterion = "";

              // if (loanBankId != null && !loanBankId.equals("")) {
              // criterion += "loanBankPara.loanBankId = ? and ";
              // parameters.add(new BigDecimal(loanBankId.toString()));
              // }
              if (paramType != null && !paramType.equals("")) {
                criterion += "loanBankPara.paramType = ?  and ";
                parameters.add(paramType);
              }
              if (paramNum != null && !paramNum.equals("")) {
                criterion += "loanBankPara.paramNum = ?  and ";
                parameters.add(paramNum);
              }
              if (paravalue != null && !paravalue.equals("")) {
                criterion += "loanBankPara.paramValue = ?  and ";
                parameters.add(paravalue);
              }

              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult().toString();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  /*
   * 更新参数表ploo3
   */
  public void updatePl003_wsh(final String paraValue, final String loanBankId,
      final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanBankPara loanBankPara set loanBankPara.paramValue=? where loanBankPara.loanBankId=? and loanBankPara.paramNum=? and loanBankPara.paramType='A' ";
          Query query = session.createQuery(sql);
          query.setString(0, paraValue);
          query.setString(1, loanBankId);
          query.setString(2, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void updatePl003_wsh_B(final String paraValue,
      final String loanBankId, final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanBankPara loanBankPara set loanBankPara.paramValue=? where loanBankPara.loanBankId=? and loanBankPara.paramNum=? and loanBankPara.paramType='B' ";
          Query query = session.createQuery(sql);
          query.setString(0, paraValue);
          query.setString(1, loanBankId);
          query.setString(2, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void updatePl003_Explian_wsh(final String paraValue,
      final String paramExplain, final String loanBankId, final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanBankPara loanBankPara set loanBankPara.paramValue=?,loanBankPara.paramExplain=? where loanBankPara.loanBankId=? and loanBankPara.paramNum=? and loanBankPara.paramType='A' ";
          Query query = session.createQuery(sql);
          query.setString(0, paraValue);
          query.setString(1, paramExplain);
          query.setString(2, loanBankId);
          query.setString(3, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void updatePl003_Explian_wsh_B(final String paraValue,
      final String paramExplain, final String loanBankId, final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanBankPara loanBankPara set loanBankPara.paramValue=?,loanBankPara.paramExplain=? where loanBankPara.loanBankId=? and loanBankPara.paramNum=? and loanBankPara.paramType='B' ";
          Query query = session.createQuery(sql);
          query.setString(0, paraValue);
          query.setString(1, paramExplain);
          query.setString(2, loanBankId);
          query.setString(3, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void updatePl003_Explian_wsh_1(final String paraValue,
      final String paramExplain, final String loanBankId, final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanBankPara loanBankPara set loanBankPara.paramExplain=? where loanBankPara.loanBankId=? and loanBankPara.paramNum=? and loanBankPara.paramValue=? and loanBankPara.paramType='A' ";
          Query query = session.createQuery(sql);
          query.setString(3, paraValue);
          query.setString(0, paramExplain);
          query.setString(1, loanBankId);
          query.setString(2, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void updatePl003_Explian_wsh_1_B(final String paraValue,
      final String paramExplain, final String loanBankId, final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanBankPara loanBankPara set loanBankPara.paramExplain=? where loanBankPara.loanBankId=? and loanBankPara.paramNum=? and loanBankPara.paramValue=? and loanBankPara.paramType='B' ";
          Query query = session.createQuery(sql);
          query.setString(3, paraValue);
          query.setString(0, paramExplain);
          query.setString(1, loanBankId);
          query.setString(2, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /**
   * 根据银行ID查询参数说明 回收办理 jj
   * 
   * @param loanBankId
   * @return
   */
  public String queryParamExplain_LJ(final Integer loanBankId,
      final String paramType, final String paramNum) {
    String paramExplain = "";
    try {
      paramExplain = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanBankPara.paramExplain "
                  + " from LoanBankPara loanBankPara ";
              Vector parameters = new Vector();
              String criterion = "";

              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanBankPara.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId.toString()));
              }
              if (paramType != null && !paramType.equals("")) {
                criterion += "loanBankPara.paramType = ?  and ";
                parameters.add(paramType);
              }
              if (paramNum != null && !paramNum.equals("")) {
                criterion += "loanBankPara.paramNum = ?  and ";
                parameters.add(paramNum);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }

  /**
   * shiy 用于查找银行对应贷款参数值 2007.09.22 param 银行id，参数数号，参数类型 return 参数值
   */
  public List queryLoanBankPara_sy(final String bankId, final String paramNum,
      final String paramType) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBankPara.paramValue,loanBankPara.paramExplain from LoanBankPara loanBankPara where loanBankPara.paramNum=? and loanBankPara.paramType=?  and loanBankPara.loanBankId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, paramNum);
          query.setString(1, paramType);
          query.setBigDecimal(2, new BigDecimal(bankId.toString()));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 参数维护
   * 
   * @author 郭婧平 2007-9-29 根据loanBankId和paramType查pl003表内容 查询条件：loanBankId
   */
  public List queryParamByLoanBankId(final String loanBankId,
      final String paramType) throws Exception {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select " + "distinct " + "pl003.param_value,"
              + "pl003.param_num," + "pl003.param_explain,pl003.reservea_a "
              + "from PL003 pl003 " + "where pl003.loan_bank_id=? "
              + "and pl003.param_type=? ";
          Query query = session.createSQLQuery(hql);
          query.setBigDecimal(0, new BigDecimal(loanBankId));
          query.setString(1, paramType);
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            LoanBankPara loanBankPara = new LoanBankPara();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              loanBankPara.setParamValue(obj[0].toString());
            } else {
              loanBankPara.setParamValue("");
            }
            loanBankPara.setParamNum(obj[1].toString());
            if (obj[2] != null) {
              loanBankPara.setParamExplain(obj[2].toString());
            } else {
              loanBankPara.setParamExplain("");
            }
            if (obj[3] != null) {
              loanBankPara.setReserveaA(obj[3].toString());
            } else {
              loanBankPara.setReserveaA("");
            }
            temp_list.add(loanBankPara);
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
   * 参数维护
   * 
   * @author 郭婧平 2007-10-08 根据paramNum和paramType查pl003中paramValue值
   */
  public String queryLoanFlow() throws Exception {
    List list = null;
    String paramValue = "";
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl003.param_value " + "from PL003 pl003 "
              + "where pl003.param_num='7' " + "and pl003.param_type='A' "
              + "and pl003.param_value is not null ";
          Query query = session.createSQLQuery(hql);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (list.size() != 0) {
      paramValue = list.get(0).toString();
    }
    return paramValue;
  }

  /**
   * 参数维护
   * 
   * @author 郭婧平 2007-9-29 根据loanBankId和paramType删除pl003表内容
   */
  public void deleteLoanBankPara(final String loanBankId, final String paramType) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete LoanBankPara loanBankPara where loanBankPara.loanBankId=? and loanBankPara.paramType=? ";
          session.createQuery(sql).setBigDecimal(0, new BigDecimal(loanBankId))
              .setString(1, paramType).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * hanl 查询PL003
   * 
   * @return
   */
  public String queryParam_value_hanl() {
    String paramV = "";
    try {
      paramV = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct p.param_value from pl003 p where p.param_num='7' and p.param_type='A'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult().toString();
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramV;
  }

  /**
   * 查询PL112_1的期限和类型 jj
   * 
   * @param contractId
   * @param status
   * @return
   */
  public List queryPL112Info_jj(final String contractId, final String status) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p.id, p.overplus_limite,p.chg_type from PL112_1 p where p.contract_id = ? and p.status = ? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, contractId);
          query.setParameter(1, status);
          return query.list();
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * jj 查询银行下是否有未记账的业务
   * 
   * @param loanBankId
   * @return
   */
  public List queryPL201Status_jj(final String loanBankId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl202.flow_head_id from PL202 pl202 where pl202.biz_st < 6 and pl202.loan_bank_id = ?";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, loanBankId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * jj 查询银行下是否有未记账的业务
   * 
   * @param loanBankId
   * @return
   */
  public List queryAA101Status_wsh(final String loanBankId,
      final List officeList, final String bizDate) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String office = "";

          for (int i = 0; i < officeList.size(); i++) {
            String temp_office = (String) officeList.get(i);
            office = office + "'" + temp_office + "'" + ",";
          }
          office = office.substring(0, office.lastIndexOf(","));
          // String hql = "select pl202.flow_head_id from PL202 pl202 where
          // pl202.biz_st < 6 and pl202.loan_bank_id = ?";
          String hql = "select aa101.id from aa101 where aa101.moneybank=? and aa101.officecode in("
              + office
              + " ) and aa101.sett_date<? and "
              + "(aa101.biz_type != 'D' or (aa101.biz_type = 'D' " +
                  "and (aa101.biz_id in (select a6.id from aa306 a6 where " +
                  "(a6.reservea_b = 0 or a6.reservea_b is null) " +
                  "and a6.id = aa101.biz_id)))) and aa101.biz_status!=5 ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, loanBankId);
          query.setParameter(1, bizDate);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryPl201Status_wsh(final String loanBankId, final String bizDate) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          // String hql = "select pl202.flow_head_id from PL202 pl202 where
          // pl202.biz_st < 6 and pl202.loan_bank_id = ?";
          String hql = "select pl202.flow_head_id from PL202 pl202 where pl202.biz_st < 6 and pl202.loan_bank_id = ? and pl202.BIZ_DATE like ? escape '/' ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, loanBankId);
          query.setParameter(1, bizDate);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryAA101MonthStatus_wsh(final String loanBankId,
      final List officeList, final String bizDate) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String office = "";

          for (int i = 0; i < officeList.size(); i++) {
            String temp_office = (String) officeList.get(i);
            office = office + "'" + temp_office + "'" + ",";
          }
          office = office.substring(0, office.lastIndexOf(","));
          // String hql = "select pl202.flow_head_id from PL202 pl202 where
          // pl202.biz_st < 6 and pl202.loan_bank_id = ?";
          String hql = "select aa101.id from aa101 where aa101.moneybank=? and aa101.officecode in("
              + office
              + " ) and aa101.biz_status!=5 and aa101.sett_date  like ? escape '/' ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, loanBankId);
          query.setParameter(1, bizDate + "%");
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public CollBank getCollBankByCollBankid_(final String collBankid) {
    CollBank collBank = null;
    collBank = (CollBank) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String hql = " from CollBank collBank where collBank.collBankId = ? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(collBankid));
            return query.uniqueResult();
          }
        });
    return collBank;
  }

  public int queryFivelevelIsUse(final String bankId) {
    Integer count = new Integer(0);
    count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = " select count(t.loan_param_id) pl003 t "
            + " where t.param_type = 'A' and t.param_num = 6 and t.reservea_a = 1 ";
        Query query = session.createSQLQuery(sql);
        if (bankId != null && !"".equals(bankId)) {
          sql += " and t.loan_bank_id = ? ";
          query.setParameter(0, bankId);
        }
        return Integer.valueOf(query.uniqueResult().toString());
      }
    });
    return count.intValue();
  }
}
