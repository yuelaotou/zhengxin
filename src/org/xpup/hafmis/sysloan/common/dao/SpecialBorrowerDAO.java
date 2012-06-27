package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyInfoDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyNewDTO;

/**
 * 特殊借款人信息表PL112
 * 
 * @author 李娟 2007-9-13
 */
public class SpecialBorrowerDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public SpecialBorrower queryById(final Integer id) {
    Validate.notNull(id);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from SpecialBorrower sb where sb.privilegeBorrowerId=? and sb.status != '2'";
            Query query = session.createQuery(hql);
            query.setParameter(0, id);
            List list = query.list();
            Iterator it = list.iterator();
            SpecialBorrower specialBorrower = null;
            if (it.hasNext()) {
              specialBorrower = (SpecialBorrower) it.next();
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  public SpecialBorrower queryByHeadID(Serializable id) {
    Validate.notNull(id);
    return (SpecialBorrower) getHibernateTemplate().get(SpecialBorrower.class,
        id);
  }

  /**
   * 张列根据 职工编号查询记录 状态为零
   * 
   * @param id
   * @return
   */
  public SpecialBorrower queryStutasByEmpId(final String empid,
      final String orgId) {
    Validate.notNull(empid);
    Validate.notNull(orgId);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id,p2.borrower_name,"
                + "p2.card_kind,p2.card_num,p2.org_id,p2.org_name,p2.loan_time_limit,"
                + "p2.loan_money,p2.status,p2.operator,p2.op_time,p2.reservea_a,p2.reservea_b,"
                + "p2.reservea_c,p2.per_bank,p2.per_bank_acc from pl112 p2 where p2.emp_id = ? and p2.org_id=? and p2.status=0";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, empid);
            query.setParameter(1, orgId);
            Object[] obj = (Object[]) query.uniqueResult();
            SpecialBorrower specialBorrower = null;
            if (obj == null) {
              return specialBorrower;
            } else {
              specialBorrower = new SpecialBorrower();
              if (obj[0] != null) {
                specialBorrower.setPrivilegeBorrowerId(new Integer(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                specialBorrower.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialBorrower.setCardKind(obj[2].toString());
              }
              if (obj[3] != null) {
                specialBorrower.setCardNum(obj[3].toString());
              }
              if (obj[4] != null) {
                specialBorrower.setOrgId(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                specialBorrower.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                specialBorrower.setLoanTimeLimit(obj[6].toString());
              }
              if (obj[7] != null) {
                specialBorrower.setLoanMoney(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                specialBorrower.setStatus(obj[8].toString());
              }
              if (obj[9] != null) {
                specialBorrower.setOperator(obj[9].toString());
              }
              if (obj[10] != null) {
                try {
                  specialBorrower.setOpTime(BusiTools.stringToUDate((obj[10]
                      .toString()), "yyyyMMdd"));
                } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              }
              if (obj[11] != null) {
                specialBorrower.setReserveaA(obj[11].toString());
              }
              if (obj[12] != null) {
                specialBorrower.setReserveaB(obj[12].toString());
              }
              if (obj[13] != null) {
                specialBorrower.setReserveaC(obj[13].toString());
              }
              if (obj[14] != null) {
                specialBorrower.setPerBank(obj[14].toString());
              }
              if (obj[15] != null) {
                specialBorrower.setPerBankAcc(obj[15].toString());
              }
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  /**
   * 张列根据 职工编号查询记录 状态为零
   * 
   * @param id
   * @return
   */
  public SpecialBorrower queryStutasByEmpIdTop1(final String empid,
      final String orgId) {
    Validate.notNull(empid);
    Validate.notNull(orgId);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id,p2.borrower_name,"
                + "p2.card_kind,p2.card_num,p2.org_id,p2.org_name,p2.loan_time_limit,"
                + "p2.loan_money,p2.status,p2.operator,p2.op_time,p2.reservea_a,p2.reservea_b,"
                + "p2.reservea_c from pl112 p2 where p2.emp_id = ? and p2.org_id=? and p2.status=1 and rownum=1";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, empid);
            query.setParameter(1, orgId);
            Object[] obj = (Object[]) query.uniqueResult();
            SpecialBorrower specialBorrower = null;
            if (obj == null) {
              return specialBorrower;
            } else {
              specialBorrower = new SpecialBorrower();
              if (obj[0] != null) {
                specialBorrower.setPrivilegeBorrowerId(new Integer(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                specialBorrower.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialBorrower.setCardKind(obj[2].toString());
              }
              if (obj[3] != null) {
                specialBorrower.setCardNum(obj[3].toString());
              }
              if (obj[4] != null) {
                specialBorrower.setOrgId(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                specialBorrower.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                specialBorrower.setLoanTimeLimit(obj[6].toString());
              }
              if (obj[7] != null) {
                specialBorrower.setLoanMoney(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                specialBorrower.setStatus(obj[8].toString());
              }
              if (obj[9] != null) {
                specialBorrower.setOperator(obj[9].toString());
              }
              if (obj[10] != null) {
                try {
                  specialBorrower.setOpTime(BusiTools.stringToUDate((obj[10]
                      .toString()), "yyyyMMdd"));
                } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              }
              if (obj[11] != null) {
                specialBorrower.setReserveaA(obj[11].toString());
              }
              if (obj[12] != null) {
                specialBorrower.setReserveaB(obj[12].toString());
              }
              if (obj[13] != null) {
                specialBorrower.setReserveaC(obj[13].toString());
              }
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  /**
   * 张列根据 借款人姓名、证件号码查询记录 状态为一
   * 
   * @param id
   * @return
   */
  public SpecialBorrower queryStutasByNameAndNumTop1(final String name,
      final String num) {
    Validate.notNull(name);
    Validate.notNull(num);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id,p2.borrower_name,"
                + "p2.card_kind,p2.card_num,p2.org_id,p2.org_name,p2.loan_time_limit,"
                + "p2.loan_money,p2.status,p2.operator,p2.op_time,p2.reservea_a,p2.reservea_b,"
                + "p2.reservea_c,p2.emp_id from pl112 p2 where "
                + "p2.borrower_name = ? and p2.card_num=? and p2.status=1 and rownum=1";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, name);
            query.setParameter(1, num);
            Object[] obj = (Object[]) query.uniqueResult();
            SpecialBorrower specialBorrower = null;
            if (obj == null) {
              return specialBorrower;
            } else {
              specialBorrower = new SpecialBorrower();
              if (obj[0] != null) {
                specialBorrower.setPrivilegeBorrowerId(new Integer(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                specialBorrower.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialBorrower.setCardKind(obj[2].toString());
              }
              if (obj[3] != null) {
                specialBorrower.setCardNum(obj[3].toString());
              }
              if (obj[4] != null) {
                specialBorrower.setOrgId(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                specialBorrower.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                specialBorrower.setLoanTimeLimit(obj[6].toString());
              }
              if (obj[7] != null) {
                specialBorrower.setLoanMoney(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                specialBorrower.setStatus(obj[8].toString());
              }
              if (obj[9] != null) {
                specialBorrower.setOperator(obj[9].toString());
              }
              if (obj[10] != null) {
                try {
                  specialBorrower.setOpTime(BusiTools.stringToUDate((obj[10]
                      .toString()), "yyyyMMdd"));
                } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              }
              if (obj[11] != null) {
                specialBorrower.setReserveaA(obj[11].toString());
              }
              if (obj[12] != null) {
                specialBorrower.setReserveaB(obj[12].toString());
              }
              if (obj[13] != null) {
                specialBorrower.setReserveaC(obj[13].toString());
              }
              if (obj[13] != null) {
                specialBorrower.setEmpId(new BigDecimal(obj[13].toString()));
              }
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  /**
   * 张列根据 借款人姓名、证件号码 查询记录 状态为零
   * 
   * @param id
   * @return
   */
  public SpecialBorrower queryStutasByNameAndNum(final String name,
      final String num) {
    Validate.notNull(name);
    Validate.notNull(num);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id,p2.borrower_name,"
                + "p2.card_kind,p2.card_num,p2.org_id,p2.org_name,p2.loan_time_limit,"
                + "p2.loan_money,p2.status,p2.operator,p2.op_time,p2.reservea_a,p2.reservea_b,"
                + "p2.reservea_c,p2.emp_id from pl112 p2 where p2.borrower_name = ? and p2.card_num=? and p2.status=0";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, name);
            query.setParameter(1, num);
            Object[] obj = (Object[]) query.uniqueResult();
            SpecialBorrower specialBorrower = null;
            if (obj == null) {
              return specialBorrower;
            } else {
              specialBorrower = new SpecialBorrower();
              if (obj[0] != null) {
                specialBorrower.setPrivilegeBorrowerId(new Integer(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                specialBorrower.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialBorrower.setCardKind(obj[2].toString());
              }
              if (obj[3] != null) {
                specialBorrower.setCardNum(obj[3].toString());
              }
              if (obj[4] != null) {
                specialBorrower.setOrgId(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                specialBorrower.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                specialBorrower.setLoanTimeLimit(obj[6].toString());
              }
              if (obj[7] != null) {
                specialBorrower.setLoanMoney(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                specialBorrower.setStatus(obj[8].toString());
              }
              if (obj[9] != null) {
                specialBorrower.setOperator(obj[9].toString());
              }
              if (obj[10] != null) {
                try {
                  specialBorrower.setOpTime(BusiTools.stringToUDate((obj[10]
                      .toString()), "yyyyMMdd"));
                } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              }
              if (obj[11] != null) {
                specialBorrower.setReserveaA(obj[11].toString());
              }
              if (obj[12] != null) {
                specialBorrower.setReserveaB(obj[12].toString());
              }
              if (obj[13] != null) {
                specialBorrower.setReserveaC(obj[13].toString());
              }
              if (obj[14] != null) {
                specialBorrower.setEmpId(new BigDecimal(obj[14].toString()));
              }
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  /**
   * 张列根据 职工编号查询记录
   * 
   * @param id
   * @return 要返回p2.emp_id 和orgId 才能确定一条记录
   */
  public SpecialBorrower queryByEmpId(final String empid, final String orgId) {
    Validate.notNull(empid);
    Validate.notNull(orgId);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id,p2.borrower_name,"
                + "p2.card_kind,p2.card_num,p2.org_id,p2.org_name,p2.loan_time_limit,"
                + "p2.loan_money,p2.status,p2.operator,p2.op_time,p2.reservea_a,p2.reservea_b,"
                + "p2.reservea_c from pl112 p2 where p2.emp_id = ? and p2.org_id = ?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, empid);
            query.setParameter(1, orgId);
            Object[] obj = (Object[]) query.uniqueResult();
            SpecialBorrower specialBorrower = null;
            if (obj == null) {
              return specialBorrower;
            } else {
              specialBorrower = new SpecialBorrower();
              if (obj[0] != null) {
                specialBorrower.setPrivilegeBorrowerId(new Integer(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                specialBorrower.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialBorrower.setCardKind(obj[2].toString());
              }
              if (obj[3] != null) {
                specialBorrower.setCardNum(obj[3].toString());
              }
              if (obj[4] != null) {
                specialBorrower.setOrgId(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                specialBorrower.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                specialBorrower.setLoanTimeLimit(obj[6].toString());
              }
              if (obj[7] != null) {
                specialBorrower.setLoanMoney(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                specialBorrower.setStatus(obj[8].toString());
              }
              if (obj[9] != null) {
                specialBorrower.setOperator(obj[9].toString());
              }
              if (obj[10] != null) {
                try {
                  specialBorrower.setOpTime(BusiTools.stringToUDate((obj[10]
                      .toString()), "yyyyMMdd"));
                } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              }
              if (obj[11] != null) {
                specialBorrower.setReserveaA(obj[11].toString());
              }
              if (obj[12] != null) {
                specialBorrower.setReserveaB(obj[12].toString());
              }
              if (obj[13] != null) {
                specialBorrower.setReserveaC(obj[13].toString());
              }
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  /**
   * 张列根据 职工编号查询记录
   * 
   * @param id
   * @return 要返回p2.emp_id 和orgId 才能确定一条记录
   */
  public SpecialBorrower queryByEmpId(final String empid, final String orgId,
      final String stutas) {
    Validate.notNull(empid);
    Validate.notNull(orgId);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id,p2.borrower_name,"
                + "p2.card_kind,p2.card_num,p2.org_id,p2.org_name,p2.loan_time_limit,"
                + "p2.loan_money,p2.status,p2.operator,p2.op_time,p2.reservea_a,p2.reservea_b,"
                + "p2.reservea_c from pl112 p2 where p2.emp_id = ? and p2.org_id = ? and p2.status=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, empid);
            query.setParameter(1, orgId);
            query.setParameter(2, stutas);
            Object[] obj = (Object[]) query.uniqueResult();
            SpecialBorrower specialBorrower = null;
            if (obj == null) {
              return specialBorrower;
            } else {
              specialBorrower = new SpecialBorrower();
              if (obj[0] != null) {
                specialBorrower.setPrivilegeBorrowerId(new Integer(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                specialBorrower.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialBorrower.setCardKind(obj[2].toString());
              }
              if (obj[3] != null) {
                specialBorrower.setCardNum(obj[3].toString());
              }
              if (obj[4] != null) {
                specialBorrower.setOrgId(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                specialBorrower.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                specialBorrower.setLoanTimeLimit(obj[6].toString());
              }
              if (obj[7] != null) {
                specialBorrower.setLoanMoney(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                specialBorrower.setStatus(obj[8].toString());
              }
              if (obj[9] != null) {
                specialBorrower.setOperator(obj[9].toString());
              }
              if (obj[10] != null) {
                try {
                  specialBorrower.setOpTime(BusiTools.stringToUDate((obj[10]
                      .toString()), "yyyyMMdd"));
                } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              }
              if (obj[11] != null) {
                specialBorrower.setReserveaA(obj[11].toString());
              }
              if (obj[12] != null) {
                specialBorrower.setReserveaB(obj[12].toString());
              }
              if (obj[13] != null) {
                specialBorrower.setReserveaC(obj[13].toString());
              }
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  /**
   * 插入记录
   * 
   * @param specialBorrower
   * @return
   */
  public Serializable insert(SpecialBorrower specialBorrower) {
    Serializable id = null;
    try {
      Validate.notNull(specialBorrower);
      id = getHibernateTemplate().save(specialBorrower);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 张列 判断 (BA002)借款人姓名 和 证件号码 是否一致
   * 
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public Boolean isEmpInfoByBorrowname_zl(final String borrowerName,
      final String cardNum) {
    Boolean is_specialapply = (Boolean) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select b2.id from ba002 b2 "
                + " where  b2.card_num=? and b2.name=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, cardNum);
            query.setParameter(1, borrowerName);
            if (query.uniqueResult() == null) {
              return new Boolean(false);
            }
            return new Boolean(true);
          }
        });

    return is_specialapply;
  }

  /**
   * 张列 判断 (PL112表)是否有职工编号
   * 
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public Boolean isSpecialBorrowerById(final String empId) {
    Boolean is_specialapply = (Boolean) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id from pl112 p2 where p2.emp_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, empId);
            if (query.list().size() == 0) {
              return new Boolean(false);
            }
            return new Boolean(true);
          }
        });

    return is_specialapply;
  }

  /**
   * 张列 判断 (PL112)借款人姓名 和 证件号码 是否一致
   * 
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public Boolean isSpecialBorrowerByBorrowname_zl(final String borrowerName,
      final String cardNum) {
    Boolean is_specialapply = (Boolean) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.privilege_borrower_id from pl112 p2 "
                + " where p2.borrower_name=? and (p2.card_num=? or p2.card_num=?) and p2.status!=2";
            String temp = "";
            if (cardNum.length() == 18)
              temp = CardMunChange.get15Id(cardNum);
            else if (cardNum.length() == 15)
              temp = CardMunChange.get18Id(cardNum);
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, borrowerName);
            query.setParameter(1, cardNum);
            query.setParameter(2, temp);
            if (query.list().size() == 0) {
              return new Boolean(false);
            }
            return new Boolean(true);
          }
        });

    return is_specialapply;
  }

  /**
   * 张列 根据输入的条件查询特 殊申请列表(pl112) 记录数量
   * 
   * @param String privilegeBorrowerId,
   * @param String borrwerName
   * @param String cardNum
   * @return
   */
  public Integer querySpecialapplyCount_zl(final String privilegeBorrowerId,
      final String borrwerName, final String cardNum, final String operator) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(p2.privilege_borrower_id) from pl112 p2 where p2.status != 2";
            Vector parameters = new Vector();
            String criterion = "";
            if (privilegeBorrowerId != null && !privilegeBorrowerId.equals("")) {
              criterion += " p2.emp_id = ?  and ";
              parameters.add(privilegeBorrowerId);
            }
            if (borrwerName != null && !borrwerName.equals("")) {
              criterion += " p2.borrower_name = ?  and ";
              parameters.add(borrwerName);
            }
            if (operator != null && !operator.equals("")) {
              criterion += " p2.operator = ?  and ";
              parameters.add(operator);
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " p2.card_num = ? and ";
              parameters.add(cardNum);
            }
            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
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
   * 张列 根据输入的条件查询特 殊申请列表(pl112) 记录数量(count (id))
   * 
   * @param String privilegeBorrowerId,
   * @param String borrwerName
   * @param String cardNum
   * @return
   */
  public Integer querySpecialapplyCount_zl() {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(p2.privilege_borrower_id) from pl112 p2 where p2.status!=2";
            Query query = session.createSQLQuery(hql);
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
   * 张列 根据输入的条件查询 殊申请列表(pl112)信息 (List)
   * 
   * @param String privilegeBorrowerId,
   * @param String borrwerName
   * @param String cardNum
   * @return
   */
  public List querySpecialapplyList_zl(final String empId,
      final String borrwerName, final String cardNum, final String orderBy,
      final String order, final int start, final int pageSize, final int page,
      final String operator) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List specialapplyList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.emp_id,p2.borrower_name,p2.card_num,"
                + " p2.loan_money,p2.loan_time_limit,p2.operator,p2.op_time,p2.status,p2.reservea_a,p2.privilege_borrower_id"
                + " from pl112 p2 " + " where p2.status != 2 ";
            String criterion = "";
            Vector parameters = new Vector();
            if (empId != null && !empId.equals("")) {
              criterion += " p2.emp_id = ?  and ";
              parameters.add(empId);
            }

            if (operator != null && !operator.equals("")) {
              criterion += " p2.operator = ?  and ";
              parameters.add(operator);
            }

            if (borrwerName != null && !borrwerName.equals("")) {
              criterion += " p2.borrower_name = ?  and ";
              parameters.add(borrwerName);
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " p2.card_num = ? and ";
              parameters.add(cardNum);
            }
            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = " p2.privilege_borrower_id ";
            String od = order;
            if (od == null)
              od = "DESC";
            hql = hql + criterion + " order by " + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List queryList = query.list();

            // 删除有用
            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              queryList = query.list();
            }
            Object obj[] = null;
            List list = new ArrayList();
            Iterator iter = queryList.iterator();
            while (iter.hasNext()) {
              SpecialapplyInfoDTO specialapplyInfoDTO = new SpecialapplyInfoDTO();
              obj = (Object[]) iter.next();
              if (obj[0] != null) {
                if (obj[0].toString().equals("0")) {
                  specialapplyInfoDTO.setEmpId("");
                } else {
                  specialapplyInfoDTO.setEmpId(BusiTools
                      .convertSixNumber(obj[0].toString()));
                }
              }
              if (obj[1] != null) {
                specialapplyInfoDTO.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialapplyInfoDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                specialapplyInfoDTO.setLoanMoney(new BigDecimal(obj[3]
                    .toString()));
              }
              if (obj[4] != null) {
                specialapplyInfoDTO.setLoanTimeLimit(new Integer(obj[4]
                    .toString()));
              }
              if (obj[5] != null) {
                specialapplyInfoDTO.setOperator(obj[5].toString());
              }
              if (obj[6] != null) {
                specialapplyInfoDTO.setOpTime(obj[6].toString());
              }
              if (obj[7] != null) {
                try {
                  // 枚举转换
                  specialapplyInfoDTO.setStatus(BusiTools.getBusiValue(Integer
                      .parseInt(obj[7].toString()), BusiConst.APPSTATUS));
                } catch (NumberFormatException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              }
              if (obj[8] != null) {
                specialapplyInfoDTO.setRemark(obj[8].toString());
              }
              if (obj[9] != null) {
                specialapplyInfoDTO.setId(obj[9].toString());
              }
              list.add(specialapplyInfoDTO);
            }
            return list;
          }
        });
    return specialapplyList;
  }

  /**
   * 张列 删除单个记录
   * 
   * @param demo
   */
  public void delete(SpecialBorrower specialBorrower) {
    Validate.notNull(specialBorrower);
    getHibernateTemplate().delete(specialBorrower);
  }

  /**
   * 张列 更新内容
   * 
   * @param specialapplyNewDTO
   */
  public void update(final SpecialapplyDTO specialapplyDTO) {
    Validate.notNull(specialapplyDTO);
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update SpecialBorrower p2 set p2.loanMoney=?,p2.loanTimeLimit=?,"
              + " p2.operator=?,p2.opTime=? ,"
              + " p2.headId=?,p2.floorId=?, "
              + " p2.reserveaA = ?,p2.reserveaB=?"
              + " where p2.borrowerName=? and p2.cardNum=? and p2.status='0'";
          Query query = session.createQuery(sql);
          query.setParameter(0, specialapplyDTO.getLoanMoney());
          query.setParameter(1, specialapplyDTO.getLoanTimeLimit());
          query.setParameter(2, specialapplyDTO.getOperator());
          query.setParameter(3, new Date());
          System.out.println(specialapplyDTO.getHeadId()+"=====================>");
          System.out.println(specialapplyDTO.getFloorId()+"======================>");
          query.setParameter(4, specialapplyDTO.getHeadId());
          query.setParameter(5, specialapplyDTO.getFloorId());
          query.setParameter(6, specialapplyDTO.getReserveaA());
          query.setParameter(7, specialapplyDTO.getReserveaB());
          query.setParameter(8, specialapplyDTO.getBorrowerName());
          query.setParameter(9, specialapplyDTO.getCardNum());
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * hanL 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态
   */
  public String findSpecialByBorrownameCardnum(final String borrowname,
      final String cardnum) {

    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.privilege_borrower_id from pl112 p "
                + "where p.borrower_name=? and (p.card_num = ? or p.card_num = ?) and p.status != 2";
            String temp = "";
            if (cardnum.length() == 18)
              temp = CardMunChange.get15Id(cardnum);
            else if (cardnum.length() == 15)
              temp = CardMunChange.get18Id(cardnum);
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, borrowname);
            query.setParameter(1, cardnum);
            query.setParameter(2, temp);
            String s = null;
            if (query.uniqueResult() == null) {
              return s;
            }
            return query.uniqueResult().toString();
          }
        });

    return id;
  }

  /**
   * hanL 根据合同编号判断是否有特殊申请
   */
  public String findSpecialApplyByContranctid(final String contractId) {

    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.privilege_borrower_id from pl112 p,pl110 p1 where p1.contract_id=? and p1.borrower_name=p.borrower_name and p1.card_num=p.card_num";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractId);
            if (query.uniqueResult() == null) {
              return null;
            } else {
              return query.uniqueResult().toString();
            }
          }
        });

    return id;
  }

  /**
   * hanl
   * 
   * @return
   */
  public SpecialBorrower findLoanMoneyAndLimitByPrivilegeBorrowerId(
      final String privilegeBorrowerId) {
    SpecialBorrower sb = null;
    try {
      sb = (SpecialBorrower) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select p.loan_money,p.loan_time_limit from pl112 p where p.privilege_borrower_id=?";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, privilegeBorrowerId);
              SpecialBorrower sb = new SpecialBorrower();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                sb.setLoanMoney(new BigDecimal(obj[0].toString()));
                sb.setLoanTimeLimit(obj[1].toString());
              }
              return sb;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sb;
  }

  /**
   * 张列 返回状态值
   * 
   * @param borrwerName
   * @param cardNum
   * @return
   */
  public String getSpecialApplyByName(final String borrwerName,
      final String cardNum) {
    Validate.notNull(borrwerName);
    Validate.notNull(cardNum);
    String stutas = "";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select p2.status from pl112 p2 "
              + "where p2.borrower_name=? and p2.card_num=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, borrwerName);
          query.setParameter(1, cardNum);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  /**
   * 张列 返回specialapplyNewDTO 根据借款人姓名、证件号码联合查询PL112表
   * 
   * @param borrwerName
   * @param cardNum
   * @return
   */
  public SpecialapplyNewDTO findSpecialBorrowerByName(final String borrwerName,
      final String cardNum) {
    Validate.notNull(borrwerName);
    Validate.notNull(cardNum);
    final SpecialapplyNewDTO specialapplyNewDTO = (SpecialapplyNewDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p2.card_kind,p2.org_name,p2.org_id "
                + "from pl112 p2 where p2.borrower_name = ? and p2.card_num = ?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, borrwerName);
            query.setParameter(1, cardNum);
            Object[] obj = (Object[]) query.uniqueResult();
            SpecialapplyNewDTO specialapplyNewDTO = null;
            if (obj == null) {
              return specialapplyNewDTO;
            } else {
              specialapplyNewDTO = new SpecialapplyNewDTO();
              if (obj[0] != null) {
                specialapplyNewDTO.setCardKind(obj[0].toString());
              }
              if (obj[1] != null) {
                specialapplyNewDTO.setOrgName(obj[1].toString());
              }
              if (obj[2] != null) {
                specialapplyNewDTO.setOrgId(new Integer(obj[2].toString()));
              }
              return specialapplyNewDTO;
            }
          }
        });
    return specialapplyNewDTO;
  }

  /**
   * hanl 查询特殊借款人，已启用的
   * 
   * @param integer
   * @return
   */
  public SpecialBorrower queryappstatusById(final Integer id) {
    Validate.notNull(id);
    final SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from SpecialBorrower sb where sb.privilegeBorrowerId=? and sb.status='1'";
            Query query = session.createQuery(hql);
            query.setParameter(0, id);
            List list = query.list();
            Iterator it = list.iterator();
            SpecialBorrower specialBorrower = null;
            if (it.hasNext()) {
              specialBorrower = (SpecialBorrower) it.next();
            }
            return specialBorrower;
          }
        });
    return specialBorrower;
  }

  public SpecialBorrower querySpeBorrowerByNameAndNum(
      final String borrowerName, final String cardNum) {
    SpecialBorrower specialBorrower = (SpecialBorrower) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from SpecialBorrower sb where sb.borrowerName = ? and (sb.cardNum = ? or sb.cardNum = ?) and sb.status != 2";
            String temp = "";
            if (cardNum.length() == 18)
              temp = CardMunChange.get15Id(cardNum);
            else if (cardNum.length() == 15)
              temp = CardMunChange.get18Id(cardNum);
            Query query = session.createQuery(hql);
            query.setParameter(0, borrowerName);
            query.setParameter(1, cardNum);
            query.setParameter(2, temp);
            return query.uniqueResult();
          }
        });
    return specialBorrower;
  }
}
