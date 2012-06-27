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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.AssistantBorrowerDTO;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.LoanreturnedbyfundSaveDTO;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.LoanreturnedbyfundTaDTO;

public class AssistantBorrowerDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public AssistantBorrower queryById(Serializable id) {
    Validate.notNull(id);
    return (AssistantBorrower) getHibernateTemplate().get(
        AssistantBorrower.class, id);
  }

  /**
   * 插入记录
   * 
   * @param AssistantBorrower
   * @return
   */
  public Serializable insert(AssistantBorrower assistantBorrower) {
    Serializable id = null;
    try {
      Validate.notNull(assistantBorrower);
      id = getHibernateTemplate().save(assistantBorrower);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * hanL 求辅助人类表
   * 
   * @param contractid
   * @return
   */
  public List findAssistanBorrowerListByContractid(final String contractid) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select p.auxiliary_id, p.emp_id, p.name, p.emp_st, p.acc_blnce, p.month_salary,p.month_pay, p.org_name,p.status,p.org_id, p.photo_url,p.card_num"
              + " from pl113 p where p.contract_id =? and (p.status = 0 or (p.status != 0 and p.reservea_c=0)) order by p.reservea_c ";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, contractid);

          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            AssistantBorrowerDTO assbor = new AssistantBorrowerDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              assbor.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              assbor.setEmpid(BusiTools.convertSixNumber(obj[1].toString()));
            }
            if (obj[2] != null) {
              assbor.setEmpname(obj[2].toString());
            }
            if (obj[3] != null) {

              assbor.setEmpst(obj[3].toString());

            }
            if (obj[4] != null) {
              assbor.setAccblnce(obj[4].toString());
            }
            if (obj[5] != null) {
              assbor.setMonthsalary(obj[5].toString());
            }
            if (obj[6] != null) {
              assbor.setMonthpay(obj[6].toString());
            }
            if (obj[7] != null) {
              assbor.setOrgname(obj[7].toString());
            }
            if (obj[8] != null) {
              try {
                assbor.setStatus(BusiTools.getBusiValue(Integer.parseInt(obj[8]
                    .toString()), BusiConst.PLCOMMONSTATUS_1));
              } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if (obj[9] != null) {
              assbor.setOrgid(obj[9].toString());
            }
            if (obj[10] != null) {
              assbor.setPhotoUrl(obj[10].toString());
            }
            if (obj[11] != null) {
              assbor.setCardnum(obj[11].toString());
            }
            templist.add(assbor);
          }
          return templist;

        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List findAssistanBorrowerListByContractid_yg(final String contractid) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select p.auxiliary_id, p.emp_id, p.name, p.emp_st, p.acc_blnce, p.month_salary,p.month_pay, p.org_name,p.status,p.org_id, p.photo_url,p.card_num"
              + " from pl113 p where p.contract_id =? and p.reservea_c = 0 order by p.auxiliary_id desc";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, contractid);

          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            AssistantBorrowerDTO assbor = new AssistantBorrowerDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              assbor.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              assbor.setEmpid(BusiTools.convertSixNumber(obj[1].toString()));
            }
            if (obj[2] != null) {
              assbor.setEmpname(obj[2].toString());
            }
            if (obj[3] != null) {

              assbor.setEmpst(obj[3].toString());

            }
            if (obj[4] != null) {
              assbor.setAccblnce(obj[4].toString());
            }
            if (obj[5] != null) {
              assbor.setMonthsalary(obj[5].toString());
            }
            if (obj[6] != null) {
              assbor.setMonthpay(obj[6].toString());
            }
            if (obj[7] != null) {
              assbor.setOrgname(obj[7].toString());
            }
            if (obj[8] != null) {
              try {
                assbor.setStatus(BusiTools.getBusiValue(Integer.parseInt(obj[8]
                    .toString()), BusiConst.PLCOMMONSTATUS_1));
              } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if (obj[9] != null) {
              assbor.setOrgid(obj[9].toString());
            }
            if (obj[10] != null) {
              assbor.setPhotoUrl(obj[10].toString());
            }
            if (obj[11] != null) {
              assbor.setCardnum(obj[11].toString());
            }
            templist.add(assbor);
          }
          return templist;

        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 根据合同编号查询最大的辅助借款人编号
   * 
   * @param contractid
   * @return
   */
  public String findMaxAuxiliaryidByContractid(final String contractid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(p.auxiliary_id) from pl113 p where p.contract_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            return query.uniqueResult().toString();
          }
        });
    return id;
  }

  public String findMaxAuxiliaryidByContractid_yg(final String contractid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(p.auxiliary_id) from pl113 p where p.contract_id=? and p.reservea_c = 0";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            return query.uniqueResult().toString();
          }
        });
    return id;
  }

  /**
   * hanL 根据借款人姓名证件号查询辅助人的ID
   */
  public String findAuxiliaryidByNameCardnum(final String borrowname,
      final String cardnum, final String contractaId) {

    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.auxiliary_id from pl113 p where p.name=? and p.card_num =? and p.contract_id=? and p.status = '0'";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, borrowname);
            query.setParameter(1, cardnum);
            query.setParameter(2, contractaId);
            if (query.uniqueResult() == null) {
              return null;
            } else {
              return query.uniqueResult().toString();
            }

          }
        });

    return id;
  }

  public String findAssistanBorrowerByContractidNameCardnum(
      final String contractid, final String name, final String cardnum) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.auxiliary_id from pl113 p where p.contract_id=? and p.name=? and p.card_num=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            query.setParameter(1, name);
            query.setParameter(2, cardnum);
            if (query.uniqueResult() == null) {
              return null;
            } else {
              return query.uniqueResult().toString();
            }

          }
        });

    return id;
  }

  public List findAuxiliaryidListByNameCardnum(final String name,
      final String cardnum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p.contract_id from pl113 p where p.name=? and p.card_num =?";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, name);
          query.setParameter(1, cardnum);

          return query.list();

        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 根据主键查询合同编号
   * 
   * @param contractid
   * @return
   */
  public String findContractidByAuxiliaryid(final String auxiliaryid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.contract_id from pl113 p where p.auxiliary_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, auxiliaryid);
            return query.uniqueResult();
          }
        });
    return id;
  }

  public void deleteAsistantBorrowerInfoByAuxiliaryid(final String idaf) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "delete from AssistantBorrower p where p.auxiliaryId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(idaf));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * hanl 删除pl113
   * 
   * @param id
   */
  public void deleteAsistantBorrowerList(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "delete from AssistantBorrower assistantborrower where assistantborrower.contractId=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, id);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * wsh 个贷公积金还贷签订合同——辅助借款人列表
   */
  public List findAssistanBorrowerListByContractid_wsh(final String contractid) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select p.auxiliary_id,p.emp_id,p.name,p.org_name,p.org_id,p.card_num from pl113 p where p.contract_id = ? and p.emp_id is not null order by p.auxiliary_id desc ";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, contractid);

          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            LoanreturnedbyfundTaDTO loanreturnedbyfundTaDTO = new LoanreturnedbyfundTaDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              loanreturnedbyfundTaDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              loanreturnedbyfundTaDTO.setEmpid(obj[1].toString());
            }
            if (obj[2] != null) {
              loanreturnedbyfundTaDTO.setEmpname(obj[2].toString());
            }
            if (obj[3] != null) {

              loanreturnedbyfundTaDTO.setOrgname(obj[3].toString());

            }
            if (obj[4] != null) {
              loanreturnedbyfundTaDTO.setOrgid(obj[4].toString());
            }
            if (obj[5] != null) {
              loanreturnedbyfundTaDTO.setCardnum(obj[5].toString());
            }
            templist.add(loanreturnedbyfundTaDTO);
          }
          return templist;

        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * wsh 个贷公积金还贷签订合同——辅助借款人列表保存
   */
  public LoanreturnedbyfundSaveDTO findAssistanBorrowerSaveListByContractid_wsh(
      final String id) {
    LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO1 = new LoanreturnedbyfundSaveDTO();
    try {
      loanreturnedbyfundSaveDTO1 = (LoanreturnedbyfundSaveDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select a.loan_kou_acc loankouacc,b.contract_id contractid,b.emp_id empid,b.name name,b.org_id orgid,b.org_name orgname,b.card_kind cardkind,b.card_num cardnum from pl111 a,pl113 b where a.contract_id=b.contract_id and b.auxiliary_id= ? order by b.auxiliary_id desc ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);

              LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO = new LoanreturnedbyfundSaveDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {

                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanreturnedbyfundSaveDTO.setLoanKouAcc(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanreturnedbyfundSaveDTO.setContractId(obj[1].toString());
                }
                if (obj[2] != null) {
                  loanreturnedbyfundSaveDTO.setEmpId(obj[2].toString());
                }
                if (obj[3] != null) {

                  loanreturnedbyfundSaveDTO.setEmpName(obj[3].toString());

                }
                if (obj[4] != null) {
                  loanreturnedbyfundSaveDTO.setOrgId(obj[4].toString());
                }
                if (obj[5] != null) {
                  loanreturnedbyfundSaveDTO.setOrgName(obj[5].toString());
                }
                if (obj[6] != null) {
                  loanreturnedbyfundSaveDTO.setCardKind(obj[6].toString());
                }
                if (obj[7] != null) {
                  loanreturnedbyfundSaveDTO.setCardNum(obj[7].toString());
                }

              }
              return loanreturnedbyfundSaveDTO;

            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanreturnedbyfundSaveDTO1;
  }

  /*
   * wsh 个贷公积金还贷签订合同——辅助借款人列表保存
   */
  public LoanreturnedbyfundSaveDTO findAssistanBorrowerSaveBorrowertByContractid_wsh(
      final String id) {
    LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO1 = new LoanreturnedbyfundSaveDTO();
    try {
      loanreturnedbyfundSaveDTO1 = (LoanreturnedbyfundSaveDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select b.loan_kou_acc loankouacc,b.contract_id contractid,a.emp_id empid,a.borrower_name name,a.org_id orgid,a.org_name orgname,a.card_kind cardkind,a.card_num cardnum  from pl110 a,pl111 b where a.contract_id=b.contract_id and a.contract_id= ? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);

              LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO = new LoanreturnedbyfundSaveDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {

                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanreturnedbyfundSaveDTO.setLoanKouAcc(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanreturnedbyfundSaveDTO.setContractId(obj[1].toString());
                }
                if (obj[2] != null) {
                  loanreturnedbyfundSaveDTO.setEmpId(obj[2].toString());
                }
                if (obj[3] != null) {

                  loanreturnedbyfundSaveDTO.setEmpName(obj[3].toString());

                }
                if (obj[4] != null) {
                  loanreturnedbyfundSaveDTO.setOrgId(obj[4].toString());
                }
                if (obj[5] != null) {
                  loanreturnedbyfundSaveDTO.setOrgName(obj[5].toString());
                }
                if (obj[6] != null) {
                  loanreturnedbyfundSaveDTO.setCardKind(obj[6].toString());
                }
                if (obj[7] != null) {
                  loanreturnedbyfundSaveDTO.setCardNum(obj[7].toString());
                }

              }
              return loanreturnedbyfundSaveDTO;

            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanreturnedbyfundSaveDTO1;
  }

  /**
   * 修改pl400的职工状态为作废 条件：职工编号和合同编号 郭婧平 2007.12.24
   */
  public void updateEmpStatus(final BigDecimal empid, final String contractId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update AssistantBorrower p set p.status = '1' where p.empId=? and p.contractId=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, empid);
          query.setParameter(1, contractId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 修改pl210的职工状态为解冻 条件：职工编号和合同编号 杨光 2009.04.09
   */
  public void updateEmpStatus_yg(final String name, final String cardNum, final String contractId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update CongealInfo c set c.status = '1' where c.empName=? and c.cardNum=? and c.contractId=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, name);
          query.setParameter(1, cardNum);
          query.setParameter(2, contractId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // /*
  // * wsh
  // * 个贷公积金还贷签订合同——辅助借款人列表保存
  // */
  // public AssistantBorrower
  // findAssistanBorrowerSaveBorrowertByContractid(final String id) {
  // AssistantBorrower assistantBorrower = new AssistantBorrower();
  // try {
  // assistantBorrower = (AssistantBorrower) getHibernateTemplate().execute(new
  // HibernateCallback() {
  //
  // public Object doInHibernate(Session session) throws HibernateException,
  // SQLException {
  //
  // String hql = " select contract_id, emp_id, name, card_num, card_kind,
  // org_name, org_id, org_tel, org_mail, org_addr, acc_blnce, month_salary,
  // month_pay, emp_st, bgn_date, end_date from pl113 where pl113.contract_id=?
  // ;";
  //
  // Query query = session.createSQLQuery(hql);
  // query.setParameter(0, id);
  //
  //         
  // AssistantBorrower assistantBorrower = new AssistantBorrower();
  // Iterator iterate = query.list().iterator();
  // Object[] obj = null;
  // while (iterate.hasNext()) {
  //           
  // obj = (Object[]) iterate.next();
  // if (obj[0] != null) {
  // assistantBorrower.setContractId(obj[0].toString());
  // }
  // if (obj[1] != null) {
  // assistantBorrower.setEmpId(new BigDecimal(obj[1].toString()));
  // }
  // if (obj[2] != null) {
  // assistantBorrower.sete(obj[2].toString());
  // }
  // if (obj[3] != null) {
  //
  // assistantBorrower.setEmpName(obj[3].toString());
  //  
  // }
  // if (obj[4] != null) {
  // assistantBorrower.setOrgId(obj[4].toString());
  // }
  // if (obj[5] != null) {
  // assistantBorrower.setOrgName(obj[5].toString());
  // }
  // if (obj[6] != null) {
  // assistantBorrower.setCardKind(obj[6].toString());
  // }
  // if (obj[7] != null) {
  // assistantBorrower.setCardNum(obj[7].toString());
  // }
  //           
  // }
  // return assistantBorrower;
  //
  // }
  //
  // });
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // return assistantBorrower;
  // }
  // 根据借款人职工编号查询贷款人信息
  public AssistantBorrower queryByBorrowerByEmpId(final String empId) {
    AssistantBorrower assistantBorrower = new AssistantBorrower();
    try {
      assistantBorrower = (AssistantBorrower) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select assistantBorrower from AssistantBorrower assistantBorrower,BorrowerAcc  borrowerAcc  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (empId != null) {
                criterion += "  assistantBorrower.contractId=?  and borrowerAcc.contractId=assistantBorrower.contractId and   ";
                parameters.add(empId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf(" and "));
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
    return assistantBorrower;
  }

  public List queryByBorrowerByEmpId_yg(final String empId) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select assistantBorrower from AssistantBorrower assistantBorrower,BorrowerAcc  borrowerAcc  ";
          Vector parameters = new Vector();
          String criterion = "";
          if (empId != null) {
            criterion += "  assistantBorrower.contractId=?  and borrowerAcc.contractId=assistantBorrower.contractId and   ";
            parameters.add(empId);
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // public AssistantBorrower queryByBorrowerByEmpId_1(final String empId){
  // AssistantBorrower assistantBorrower=new AssistantBorrower();
  // try{
  // assistantBorrower=(AssistantBorrower)getHibernateTemplate().execute(
  // new HibernateCallback() {
  //
  // public Object doInHibernate(Session session)
  // throws HibernateException, SQLException {
  //
  // String hql = "select assistantBorrower from AssistantBorrower
  // assistantBorrower,BorrowerAcc borrowerAcc ";
  // Vector parameters = new Vector();
  // String criterion = "";
  //
  // if (empId != null) {
  // criterion += " assistantBorrower.empId=? and
  // borrowerAcc.contractId=assistantBorrower.contractId and
  // borrowerAcc.contractSt='11' and ";
  // // parameters.add(new Integer(empId));
  // }
  // if (criterion.length() != 0)
  // criterion = " where "+ criterion.substring(0, criterion.lastIndexOf(" and
  // "));
  // hql = hql + criterion;
  // Query query = session.createQuery(hql);
  // // for (int i = 0; i < parameters.size(); i++) {
  // query.setInteger(0,Integer.parseInt(empId));
  // // }
  // return query.uniqueResult();
  // }
  // }
  // );
  // }
  // catch (Exception e) {
  // e.printStackTrace();
  // }
  // return assistantBorrower;
  // }
  public String queryByBorrowerByEmpId_1(final String empId) {
    String contracid = "";
    try {
      contracid = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p113.contract_id from pl113 p113,pl111 p111 "
                  + "where p113.contract_id=p111.contract_id and p111.contract_st='11' and p113.emp_id= "
                  + empId;

              Query query = session.createSQLQuery(hql);
              Object obj = query.uniqueResult();
              if (obj == null) {
                return null;
              } else {
                return query.uniqueResult().toString();
              }

            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contracid;
  }

  public AssistantBorrower findByContractId(final String contractId) {
    try {
      AssistantBorrower assistantBorrower = (AssistantBorrower) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              Object obj = session.createQuery(
                  "select o from AssistantBorrower o where o.contractId=?")
                  .setString(0, contractId).uniqueResult();
              return obj;
            }
          });
      return assistantBorrower;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }
}
