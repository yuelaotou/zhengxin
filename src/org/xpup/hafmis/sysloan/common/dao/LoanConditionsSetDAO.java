package org.xpup.hafmis.sysloan.common.dao;

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
import org.xpup.hafmis.sysloan.common.domain.entity.LoanConditionsSet;
import org.xpup.hafmis.sysloan.common.loanconditionsset.LoanConditionsParamSetDTO;

/**
 * 贷款前提条件设置PL008
 * 
 *@author 李娟
 *2007-11-12
 */
public class LoanConditionsSetDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public LoanConditionsSet queryById(Serializable id) {  
    Validate.notNull(id);
    return  (LoanConditionsSet) getHibernateTemplate().get(LoanConditionsSet.class,new Integer(id.toString()));    
  }
  /**
   * 插入记录
   * 
   * @param loanConditionsSet
   * @return
   */
  public Serializable insert(LoanConditionsSet loanConditionsSet) {
    Serializable id = null;
    try {
      Validate.notNull(loanConditionsSet);
      id = getHibernateTemplate().save(loanConditionsSet);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * hanl
   * 公积金个人账户状态__正常(影响是否能贷)  0，不是，1，是
   */
  public String querySyscollectionState(final String office) {
    String param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.param_value from pl008 p where p.office=? and p.param_num='1' and p.is_use='1'";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, office);
            if( query.uniqueResult()==null){
              return null;
            }
            return query.uniqueResult().toString();
          }
        });
    return param_value;
  }
  //查询职工是否作为借款人或者辅助借款人存在并且合同状态为还款中11的人的数量
  public String queryempLoanIsEleven(final String empName,final String cardNum) {
    String param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select count(*)" +
                         " from pl110 a, pl111 b, pl113 c" +
                         " where a.contract_id(+) = b.contract_id " +
                         " and b.contract_id = c.contract_id(+)" +
                         " and b.contract_st = '11'" +
                         " and c.status = 0 " +
                         " and ((a.borrower_name = ? and a.card_num = ?)" +
                         " or (c.name = ? and c.card_num = ? ))";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, empName);
            query.setParameter(1, cardNum);
            query.setParameter(2, empName);
            query.setParameter(3, cardNum);
            return query.uniqueResult().toString();
          }
        });
    return param_value;
  }
  //查询职工是否作为借款人或者辅助借款人存在并且合同状态为还款中11的人的数量
  public String queryempLoanIsElevena(final String empName,final String cardNum) {
    String param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            String cardNuma = cardNum;
            if(cardNum.length()==15){
              cardNuma = org.xpup.hafmis.common.util.CardMunChange.get18Id(cardNum);
            }else if(cardNum.length()==18){
              cardNuma = org.xpup.hafmis.common.util.CardMunChange.get15Id(cardNum);
            }
            String sql = "select count(t.borrowername) from othersloan t"
              +" where (t.borrowername = ? and (t.borrowercardnum = ? or t.borrowercardnum = ?)) " +
                  "or (t.assisname = ? and (t.assiscardnum = ? or t.assiscardnum = ? ))";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, empName);
            query.setParameter(1, cardNum);
            query.setParameter(2, cardNuma);
            query.setParameter(3, empName);
            query.setParameter(4, cardNum);
            query.setParameter(5, cardNuma);
            return query.uniqueResult().toString();
          }
        });
    return param_value;
  }
  public String querySyscollectionState_org_emp_count(final String orgId) {
    String param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(aa2.pk_id) from aa002 aa2,aa001 a1 where aa2.org_id = a1.id and aa2.pay_status in ('1', '3', '4') and aa2.org_id=? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, orgId);
            return query.uniqueResult().toString();
          }
        });
    return param_value;
  }
  /**
   * hanl
   * 公积金连续汇缴月数大于__ 月(影响是否能贷)  
   */
  public LoanConditionsParamSetDTO querySyscollectionMonth(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=?  and p.param_value is null and p.param_num='2' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }

              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * wsh
   * 公积金欠缴__ 月(影响是否能贷)  
   */
  public LoanConditionsParamSetDTO querySyscollectionLackMonth(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='2' and p.param_value='1' and p.and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }

              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 公积金开户时间大于__ 月(影响是否能贷)
   */
  public LoanConditionsParamSetDTO querySyscollectionOpenAccMonth(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='3' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }

              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 公积金欠缴月数要小于"?"月(影响是否能贷)
   */
  public LoanConditionsParamSetDTO querySyscollectionOpenAccMonth_wsh(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='1' and p.param_num='2' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }

              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款人实际年龄加贷款期限不超过____岁(男)或____岁(女)(影响是否能贷)0，男，1，女
   * @param borrowname
   * @param cardnum
   * @return
   */
  public List queryManAge(final String office) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='4' and p.is_use='1'";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, office);
        Iterator iterate = query.list().iterator();
       
        List llis=new ArrayList();
        Object[] obj = null;
        while (iterate.hasNext()) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          obj = (Object[]) iterate.next();
          if (obj[0] != null) {
            loanConditionsParamSetDTO.setParamValue(obj[0].toString());
          }
          if (obj[1] != null) {
            loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
          }
          llis.add(loanConditionsParamSetDTO);
        }
        return llis;
      }
    });
    return list;
  }
  /**
   * hanl
   * 单位的性质
   * @param borrowname
   * @param cardnum
   * @return
   */
  public List queryOrgNature(final String office) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select p.param_value,p.param_explain,p.param_num from pl008 p where p.office=? and p.param_value='1' and p.param_num in('15','16','17') and p.is_use='1' order by p.param_num desc";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, office);
        Iterator iterate = query.list().iterator();
       
        List llis=new ArrayList();
        Object[] obj = null;
        while (iterate.hasNext()) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          obj = (Object[]) iterate.next();
          if (obj[0] != null) {
            loanConditionsParamSetDTO.setParamValue(obj[0].toString());
          }
          if (obj[1] != null) {
            loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
          }
          if (obj[2] != null) {
            loanConditionsParamSetDTO.setParamNum(obj[2].toString());
          }
          llis.add(loanConditionsParamSetDTO);
        }
        return llis;
      }
    });
    return list;
  }
  /**
   * hanl
   * 贷款期限不低于___月,最高期限不超过___月.(影响是否能贷) 1，不低于2，不超过
   */
  public List queryLoanLimit(final String office) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='13' and p.is_use='1'";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, office);
        Iterator iterate = query.list().iterator();
       
        List llis=new ArrayList();
        Object[] obj = null;
        while (iterate.hasNext()) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          obj = (Object[]) iterate.next();
          if (obj[0] != null) {
            loanConditionsParamSetDTO.setParamValue(obj[0].toString());
          }
          if (obj[1] != null) {
            loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
          }
          llis.add(loanConditionsParamSetDTO);
        }
        return llis;
      }
    });
    return list;
  }
  /**
   * hanl
   * 贷款期限不低于___月,最高期限不超过___月.(影响是否能贷) 1，不低于2，不超过
   */
  public List queryLoanLimitMax(final String office) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='5' and p.param_value='2' and p.is_use='1'";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, office);
        Iterator iterate = query.list().iterator();
       
        List llis=new ArrayList();
        Object[] obj = null;
        while (iterate.hasNext()) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          obj = (Object[]) iterate.next();
          if (obj[0] != null) {
            loanConditionsParamSetDTO.setParamValue(obj[0].toString());
          }
          if (obj[1] != null) {
            loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
          }
          llis.add(loanConditionsParamSetDTO);
        }
        return llis;
      }
    });
    return list;
  }
  /**
   * hanl
   * 信用情况,曾逾期不超过___月.(影响是否能贷) 
   */
  public LoanConditionsParamSetDTO queryOweMonth(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='6' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 单方拥有公积金贷款金额不能超过___元,有辅助贷款人的贷款金额不能超过___元0，单方1，辅助贷款人
   */

  public LoanConditionsParamSetDTO queryLoanMoney(final String office,final String paramvalue) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='7' and p.is_use='1' and p.param_value=?";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              query.setParameter(1, paramvalue);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='8' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_wsh1(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='2' and p.param_num='17'  and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_wsh2(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='3' and p.param_num='17' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_wsh3(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='2' and p.param_num='16'  and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_wsh5(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='2' and p.param_num='15'  and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_wsh4(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='3' and p.param_num='16'  and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_wsh6(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='3' and p.param_num='15'  and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_1(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='12' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过商品房价的__ %(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceS_14(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='14' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 贷款金额不能超过二手房价的__%(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceE(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='9' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * wsh
   * 贷款金额不能超过二手房价的__%(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceE_wsh(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='9' p.param_value='1' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * wsh
   * 贷款金额不能超过二手房价的__%(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryOverHousePriceE_wsh_1(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='9' p.param_value='2' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 商品房贷款最高金额不超过___元.(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryMaxLoanMoneyS(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='10' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 二手房贷款最高金额不超过___元,(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryMaxLoanMoneyE(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='11' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 二手房贷款最高金额不超过___元,(影响贷款金额)
   */
  public LoanConditionsParamSetDTO queryMaxLoanMoneyE_w(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='14' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }
  /**
   * hanl
   * 连续汇缴月数
   */
  public boolean queryMonthCounts(final String empId,final String orgId,final String paramCount) {
    boolean  flag = false ;
    List list = null;
    int count=0;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select monthPaymentTail.monthPaymentHead.payMonth from MonthPaymentTail monthPaymentTail " +
                  "where monthPaymentTail.empId = ? and monthPaymentTail.monthPaymentHead.paymentHead.org.id = ? " +
                  "and monthPaymentTail.monthPaymentHead.paymentHead.payType_ = 'A' order by monthPaymentTail.monthPaymentHead.payMonth asc";

              Query query = session.createQuery(hql);
              query.setParameter(0, new Integer(empId));
              query.setParameter(1, new Integer(orgId));
              return query.list();
            }

          });
      Object obj1=null;
      Object obj2=null;
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
        
            obj1=(Object)list.get(i);
            if(i+1==list.size()){
              return false;
            }
            obj2=(Object)list.get(i+1);
            if(Integer.parseInt(obj2.toString())-Integer.parseInt(obj1.toString())==1){
              count += 1;              
            }else{
              count = 0;
            }
          
          if(count>Integer.parseInt(paramCount)){
            flag=true;
            break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }
  /**
   * 贷款前提条件设置
   * @author 郭婧平
   * 2007-12-5
   * 查pl008表内容
   */
  public List queryLoanConditionsSet(final String office) throws Exception {
    List list=null;
    try {
      list =(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select "+"distinct "+
                                  "pl008.param_value,"+
                                  "pl008.param_num,"+
                                  "pl008.param_explain," +
                                  "pl008.is_use "+
                                  "from PL008 pl008 " +
                                  "where pl008.office=? ";
            Query query = session.createSQLQuery(hql);
            query.setString(0, office);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              LoanConditionsSet loanConditionsSet=new LoanConditionsSet();
              obj = (Object[]) it.next();
              if(obj[0]!=null){
                loanConditionsSet.setParamValue(obj[0].toString());
              }else{
                loanConditionsSet.setParamValue("");
              }
              loanConditionsSet.setParamNum(obj[1].toString());
              if(obj[2]!=null){
                loanConditionsSet.setParamExplain(obj[2].toString());
              }else{
                loanConditionsSet.setParamExplain("");
              }
              loanConditionsSet.setIsUse(obj[3].toString());
              temp_list.add(loanConditionsSet);
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
   * 贷款前提条件设置
   * @author 郭婧平
   * 2007-12-5
   * 根据office删除pl008表内容
   */
  public void deleteLoanConditionsSet(final String office){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete LoanConditionsSet loanConditionsSet where loanConditionsSet.office=? ";          
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
   * 根据office paramnum 查询param_explain值
   * @param param_num
   * @param office
   * @return
   */
  public String queryIsUseParamValue(final String param_num ,final String office ) {
    String num="";
     num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.param_explain from pl008 t where t.param_num=? and t.office=? and t.param_value is null and t.is_use='1' ";

            Query query = session.createSQLQuery(hql);
            query.setString(0, param_num);
            query.setString(1, office);
            Iterator it = query.list().iterator();
            Object obj = "";
            while(it.hasNext())
            {
              obj = (Object) it.next();
            }
            return obj.toString();
            
          }
        });

    return num;
  }
  /**
   * 根据office paramnum 查询param_explain值
   * @param param_num
   * @param office
   * @return
   */
  public String queryIsUseParamValue_Thirteen_One(final String param_num ,final String office,final String param_value ) {
    String num="";
     num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.param_explain from pl008 t where t.param_num=? and t.office=? and t.param_value=? and t.is_use='1' ";

            Query query = session.createSQLQuery(hql);
            query.setString(0, param_num);
            query.setString(1, office);
            query.setString(2, param_value);
            Iterator it = query.list().iterator();
            Object obj = "";
            while(it.hasNext())
            {
              obj = (Object) it.next();
            }
            return obj.toString();
            
          }
        });

    return num;
  }
  public String queryParamValueCount_wsh(final String office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct t.PARAM_VALUE) " +
              " from pl008 t " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "t.OFFICE != '100'  and ";
           
          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "t.PARAM_NUM = ?  and ";
            parameters.add(paramNum);
          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
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
  public void updatePl008_wsh(final String paraValue, final String office,final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanConditionsSet loanConditionsSet set loanConditionsSet.paramValue=? where loanConditionsSet.office=? and loanConditionsSet.paramNum=?  ";
          Query query = session.createQuery(sql);
          query.setString(0, paraValue);
          query.setString(1, office);
          query.setString(2, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }
  public String queryParamValue_LJ(final String office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanConditionsSet.paramValue " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office = ?  and ";
            parameters.add(office);
          }
         
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
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
  /*
   * 更新参数表ploo9
   */
  public void updatePl008_wsh_1(final String paraValue, final String office,final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanConditionsSet loanConditionsSet set loanConditionsSet.paramValue=? where loanConditionsSet.office=? and loanConditionsSet.paramNum=?  ";
          Query query = session.createQuery(sql);
          query.setString(0, paraValue);
          query.setString(1, office);
          query.setString(2, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }
  public String queryParamExpCount_wsh(final String office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct t.PARAM_EXPLAIN) " +
              " from pl008 t " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "t.office = ?  and ";
            parameters.add(office);
          }
          
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "t.PARAM_NUM = ?  and ";
            parameters.add(paramNum);
          }
//          if (paravalue != null && !paravalue.equals("")) {
//            criterion += "loanConditionsSet.paramValue = ?  and ";
//            parameters.add(paravalue);
//          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
            System.out.println(parameters.get(i));
          }
          System.out.println("s...."+query.uniqueResult().toString());
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  public void updatePl008_wsh_2(final String paraValue, final String office,final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanConditionsSet loanConditionsSet set loanConditionsSet.paramExplain=? where loanConditionsSet.office=? and loanConditionsSet.paramNum=?  ";
          Query query = session.createQuery(sql);
          query.setString(0, paraValue);
          query.setString(1, office);
          query.setString(2, paramNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }
  public String queryParamExp_LJ(final String office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct loanConditionsSet.paramExplain) " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office != '100'  and ";
           
          }
         
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
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
  public String queryParamExp_LJ_1(final String office, final String paramNum, final String paramvalue){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct loanConditionsSet.paramExplain) " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office != '100'  and ";
           
          }
         
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = '2'  and loanConditionsSet.paramValue = '1' and";
//            parameters.add(paramNum);
          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          System.out.println(query.uniqueResult().toString());
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  public String queryParamValueCount_wsh(final String office,final String paramNum, final String paramvalu){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct loanConditionsSet.paramExplain) " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office != '100'  and ";
           
          }
          if (paramvalu != null && !paramvalu.equals("")) {
            criterion += "loanConditionsSet.paramValue = ?  and ";
            parameters.add(paramvalu);
          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
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
  public String queryParamExp_LJ_wsh(final String office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanConditionsSet.paramValue " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office = ?  and ";
            parameters.add(office);
          }
         
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
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
  public String queryParamValue_wsh_1(final String office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanConditionsSet.paramExplain " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office = ?  and ";
            parameters.add(office);
          }
         
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
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
  public String queryParamExpCount_wsh(final String office, final String paramNum,final String paravalue){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct loanConditionsSet.paramExplain) " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office != '100'  and loanConditionsSet.office != ?  and ";
            parameters.add(office);
          }
          
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
            parameters.add(paramNum);
          }
          if (paravalue != null ) {
            if(paravalue.equals("")){
              criterion += "loanConditionsSet.paramValue is null  and ";
            }else{
              criterion += "loanConditionsSet.paramValue = ?  and ";
              parameters.add(paravalue);
            }
           
           
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
  public String queryParamExpCount_xyz(final String office, final String paramNum,final String paravalue){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(t.loan_param_id) from PL008 t  " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
         
          Query query = session.createSQLQuery(hql);
          
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  public void updatePl008_wsh_num_value(final String paramExplain, final String office,final String paramNum,final String paramValue) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update LoanConditionsSet loanConditionsSet set loanConditionsSet.paramExplain=? where loanConditionsSet.office=? and loanConditionsSet.paramNum=? and loanConditionsSet.paramValue=? ";
          if(paramValue.equals("")){
            sql = "update LoanConditionsSet loanConditionsSet set loanConditionsSet.paramExplain=? where loanConditionsSet.office=? and loanConditionsSet.paramNum=? and loanConditionsSet.paramValue is null ";
          }
          Query query = session.createQuery(sql);
          query.setString(0, paramExplain);
          query.setString(1, office);
          query.setString(2, paramNum);
          if(!"".equals(paramValue)){
            query.setString(3, paramValue);
          }
         
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }
  public String queryParamExpCount_wsh(final Integer office, final String paramNum,final String paravalue){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct loanConditionsSet.paramExplain) " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office = ?  and ";
            parameters.add(office);
          }
//          if (paramType != null && !paramType.equals("")) {
//            criterion += "loanBankPara.paramType = ?  and ";
//            parameters.add(paramType);
//          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
            parameters.add(paramNum);
          }
          if (paravalue != null && !paravalue.equals("")) {
            criterion += "loanConditionsSet.paramValue = ?  and ";
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
  public String queryParamExpCount_wsh_1(final String office, final String paramNum,final String paravalue){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  loanConditionsSet.paramExplain " +
              " from LoanConditionsSet loanConditionsSet " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanConditionsSet.office = ?  and ";
            parameters.add(office);
          }
          
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanConditionsSet.paramNum = ?  and ";
            parameters.add(paramNum);
          }
          if (paravalue != null ) {
            if(paravalue.equals("")){
              criterion += "loanConditionsSet.paramValue is null  and ";
            }else{
              criterion += "loanConditionsSet.paramValue = ?  and ";
              parameters.add(paravalue);
            }
           
           
          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          System.out.println(query.uniqueResult());
          if(query.uniqueResult()==null){
            return " ";
          }
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  public Integer queryTestCount() {
     Integer count = new Integer(0);
       count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
   
         public Object doInHibernate(Session session) throws HibernateException,
             SQLException {
           String hql = "select count(t.param_explain) from PL008 t";
           Query query = session.createSQLQuery(hql);
           return Integer.valueOf(query.uniqueResult().toString());
         }
       });
       return count;
     }

}
