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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.GiveaccModifyDTO;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.HouseListDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTaDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.SpecialBankListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.dto.LoanFirstCheckDTO;

public class HousesDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public Houses queryById(Serializable id) {
    Validate.notNull(id);
    return (Houses) getHibernateTemplate().get(Houses.class, id);
  }

  /**
   * 插入记录
   * 
   * @param Houses
   * @return
   */
  public Serializable insert(Houses houses) {
    Serializable id = null;
    try {
      Validate.notNull(houses);
      id = getHibernateTemplate().save(houses);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * yuqf 根据合同编号查询购房合同编号
   * 
   * @param id
   * @return
   */
  public String queryBuyHouseContractId(final String id) {
    String buyHouseContractId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.buy_house_contract_id from pl114 t where t.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult();
          }
        });

    return buyHouseContractId;
  }

  /**
   * author wsh 查询要修改的划款帐号相关信息(住房类型为商品房01) 2007-9-25
   * 
   * @param contractId
   * @return giveaccModifyDTO
   */
  public GiveaccModifyDTO queryHousesInfo_wsh(final String contractId,
      final String houseType) {
    GiveaccModifyDTO giveaccModifyDTO = (GiveaccModifyDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "";
            if ("01".equals(houseType)) {
              hql = "select  b.contract_id contractid,"
                  + "a.borrower_name borrowername," + "a.card_kind cardkind,"
                  + "a.card_num cardnum,"
                  + "b.developer_paybank developerpaybank,"
                  + "b.developer_paybank_a_acc developerpaybankacc,"
                  + "c.developer_name," + "b.remark re "
                  + "from pl110 a, pl114 b, pl005 c "
                  + "where a.contract_id = b.contract_id and"
                  + " b.head_id = c.id and " + "b.contract_id= ? ";
            } else {
              hql = "select  b.contract_id contractid,"
                  + "a.borrower_name borrowername," + "a.card_kind cardkind,"
                  + "a.card_num cardnum,"
                  + "b.bargainor_paybank bargainorpaybank,"
                  + "b.bargainor_paybank_acc bargainorpaybankacc,"
                  + "b.bargainor_name bargainorname," + "b.remark re"
                  + " from pl110 a, pl114 b "
                  + "where a.contract_id = b.contract_id and"
                  + " b.contract_id= ? ";
            }

            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();

            GiveaccModifyDTO temp_giveaccModifyDTO = new GiveaccModifyDTO();
            if (obj == null) {
              return temp_giveaccModifyDTO;
            }

            if (obj[0] != null) {
              temp_giveaccModifyDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              temp_giveaccModifyDTO.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              temp_giveaccModifyDTO.setCardKind(obj[2].toString());
            }
            if (obj[3] != null) {
              temp_giveaccModifyDTO.setCardNum(obj[3].toString());
            }
            if (obj[4] != null) {
              temp_giveaccModifyDTO.setOldSellerPayBank(obj[4].toString());
            }
            if (obj[5] != null) {
              temp_giveaccModifyDTO.setOldPayBankAcc(obj[5].toString());
            }
            if (obj[6] != null) {
              temp_giveaccModifyDTO.setSellerName(obj[6].toString());
            }
            if (obj[7] != null) {
              temp_giveaccModifyDTO.setRemark(obj[7].toString());
            }
            return temp_giveaccModifyDTO;
          }
        });
    return giveaccModifyDTO;
  }

  /**
   * author wsh 根据合同编号查询住房类型 2007-9-25
   * 
   * @param contractId
   * @return String
   */
  public String findHouseType_wsh(final String contractId) {
    String houseType = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.house_type from pl114 a where a.contract_id= ? ";
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            String temp_housetype = "";
            if (obj != null) {
              temp_housetype = obj.toString();
            }
            return temp_housetype;
          }
        });
    return houseType;
  }

  /**
   * author wsh 根据合同编号,借款人姓名，卡号，售房人查询PL110、PL114、PL131中信息 2007-9-26
   * 
   * @param contractId 合同编号
   * @param borrowerName 借款人姓名
   * @param cardNum 卡号
   * @param sellerName 售房人
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryHouseList_wsh(final String contractId,
      final String borrowerName, final String cardNum, final String sellerName,
      final String orderBy, final String order, final int start,
      final int pageSize, final List loanBankList) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List houseList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql1 = "";
            String hql = "select distinct c.poke_bank_modify_id pokemodifyid,"
                + "b.house_type housetype," + "c.contract_id contractid,"
                + "a.borrower_name borrowername," + "a.card_num cardnum,"
                + "nvl(b.bargainor_name,'') bargainorname,"
                + "nvl(d.developer_name,'') developername,"
                + "c.new_poke_bank newpokebank,"
                + "c.new_poke_bank_acc newpokebankacc," + "b.remark re,"
                + "c.oprator op "
                + "from pl110 a, pl114 b, pl131 c, pl005 d ,pl111 e ";
            String criterion = "";
            String criterion1 = "";
            List list = new ArrayList();
            List temp_list = new ArrayList();
            Vector parameters = new Vector();
            int size = 0;
            if (contractId != null && !contractId.equals("")) {
              criterion += " c.contract_id = ?  and ";
              criterion1 += " c.contract_id = ?  and ";
              parameters.add(contractId);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " e.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
              criterion1 += criterion;
            }
            if (borrowerName != null && !borrowerName.equals("")) {
              criterion += " a.borrower_name = ?  and ";
              criterion1 += " a.borrower_name = ?  and ";
              parameters.add(borrowerName);
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " a.card_num = ? and ";
              criterion1 += " a.card_num = ? and ";
              parameters.add(cardNum);
            }
            if (sellerName != null && !sellerName.equals("")) {
              criterion += " b.bargainor_name = ? and ";
              criterion1 += " d.developer_name = ? and ";
              parameters.add(sellerName);
            }
            if (sellerName != null && !sellerName.equals("")) {
              if (criterion.length() != 0) {
                criterion = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id and e.contract_id = a.contract_id and b.head_id=d.id(+) and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }
              if (criterion1.length() != 0) {
                criterion1 = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id and e.contract_id = a.contract_id and b.head_id=d.id(+) and "
                    + criterion1.substring(0, criterion1.lastIndexOf("and"));
              }
            } else {
              if (criterion.length() != 0) {
                criterion = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id and e.contract_id = a.contract_id and  b.head_id=d.id(+) and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              } else {
                criterion = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id and e.contract_id = a.contract_id and b.head_id=d.id(+) ";
              }
            }

            String ob = orderBy;
            if (ob == null)
              ob = "pokemodifyid ";
            String od = order;
            if (od == null)
              od = "DESC";
            hql1 = hql + criterion1 + "order by " + ob + " " + od;
            hql = hql + criterion + "order by " + ob + " " + od;
            Object obj[] = null;
            if (sellerName != null && !sellerName.equals("")) {

              Query query1 = session.createSQLQuery(hql1);
              for (int i = 0; i < parameters.size(); i++) {
                query1.setParameter(i, parameters.get(i));
              }
              query1.setFirstResult(start);
              query1.setMaxResults(pageSize);
              temp_list = query1.list();
              size = query1.list().size();
              Iterator iter = temp_list.iterator();
              iter.hasNext();
              while (iter.hasNext()) {
                HouseListDTO houseListDTO = new HouseListDTO();
                obj = (Object[]) iter.next();
                if (obj[2] != null) {
                  houseListDTO.setContractId(obj[2].toString());
                }
                if (obj[3] != null) {
                  houseListDTO.setBorrowerName(obj[3].toString());
                }
                if (obj[4] != null) {
                  houseListDTO.setCardNum(obj[4].toString());
                }
                if (obj[2] != null) {
                  // 住房类型为01商品房
                  if ("1".equals(obj[2].toString())) {
                    if (obj[6] != null) {
                      houseListDTO.setSellerName(obj[6].toString());
                    }
                  }
                  // 住房类型为而手房进入
                  else {
                    if (obj[5] != null) {
                      houseListDTO.setSellerName(obj[5].toString());
                    }
                  }
                }
                if (obj[7] != null) {
                  houseListDTO.setNewSellerPayBank(obj[7].toString());
                }

                if (obj[8] != null) {
                  houseListDTO.setNewPayBankAcc(obj[8].toString());
                }
                if (obj[9] != null) {
                  houseListDTO.setRemark(obj[9].toString());
                }
                if (obj[10] != null) {
                  houseListDTO.setOperator(obj[10].toString());
                }
                list.add(houseListDTO);
              }
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            query.setFirstResult(start);
            if (size == 0) {
              query.setMaxResults(pageSize);

              temp_list = query.list();
              Iterator iter = temp_list.iterator();
              while (iter.hasNext()) {
                HouseListDTO houseListDTO = new HouseListDTO();
                obj = (Object[]) iter.next();
                if (obj[2] != null) {
                  houseListDTO.setContractId(obj[2].toString());
                }
                if (obj[3] != null) {
                  houseListDTO.setBorrowerName(obj[3].toString());
                }
                if (obj[4] != null) {
                  houseListDTO.setCardNum(obj[4].toString());
                }
                if (obj[2] != null) {
                  // 住房类型为01商品房
                  if ("1".equals(obj[2].toString())) {
                    if (obj[6] != null) {
                      houseListDTO.setSellerName(obj[6].toString());
                    }
                  }
                  // 住房类型为而手房进入
                  else {
                    if (obj[5] != null) {
                      houseListDTO.setSellerName(obj[5].toString());
                    }
                  }
                }
                if (obj[7] != null) {
                  houseListDTO.setNewSellerPayBank(obj[7].toString());
                }

                if (obj[8] != null) {
                  houseListDTO.setNewPayBankAcc(obj[8].toString());
                }
                if (obj[9] != null) {
                  houseListDTO.setRemark(obj[9].toString());
                }
                if (obj[10] != null) {
                  houseListDTO.setOperator(obj[10].toString());
                }

                list.add(houseListDTO);
              }
            }
            return list;
          }
        });
    return houseList;
  }

  /**
   * author wsh 根据合同编号,借款人姓名，卡号，售房人查询PL131中记录数量 2007-9-26
   * 
   * @param contractId 合同编号
   * @param borrowerName 借款人姓名
   * @param cardNum 卡号
   * @param sellerName 售房人
   * @return Integer
   */
  public Integer queryHouseCount_wsh(final String contractId,
      final String borrowerName, final String cardNum, final String sellerName,
      final List loanBankList) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql1 = "";
            String hql = "select count( distinct c.poke_bank_modify_id) "
                + "from pl110 a, pl114 b, pl131 c, pl005 d,pl111 e  ";
            String criterion = "";
            String criterion1 = "";
            Integer temp_count = new Integer(0);
            Vector parameters = new Vector();
            int size = 0;
            if (contractId != null && !contractId.equals("")) {
              criterion += " c.contract_id = ?  and ";
              criterion1 += " c.contract_id = ?  and ";
              parameters.add(contractId);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " e.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
              criterion1 += criterion;
            }
            if (borrowerName != null && !borrowerName.equals("")) {
              criterion += " a.borrower_name like ?  and ";
              criterion1 += " a.borrower_name like ?  and ";
              parameters.add("%" + borrowerName + "%");
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " a.card_num = ? and ";
              criterion1 += " a.card_num = ? and ";
              parameters.add(cardNum);
            }
            if (sellerName != null && !sellerName.equals("")) {
              criterion += " b.bargainor_name = ? and ";
              criterion1 += " d.developer_name = ? and ";
              parameters.add(sellerName);
            }
            if (sellerName != null && !sellerName.equals("")) {
              if (criterion.length() != 0) {
                criterion = "where c.contract_id = a.contract_id and e.contract_id = a.contract_id and b.contract_id = a.contract_id and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }
              if (criterion1.length() != 0) {
                criterion1 = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id  and e.contract_id = a.contract_id and d.id=b.head_id and "
                    + criterion1.substring(0, criterion1.lastIndexOf("and"));
              }
            } else {
              if (criterion.length() != 0) {
                criterion = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id  and e.contract_id = a.contract_id and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              } else {
                criterion = "where c.contract_id = a.contract_id  and e.contract_id = a.contract_id and b.contract_id = a.contract_id  ";
              }
            }
            hql1 = hql + criterion1;

            hql = hql + criterion;
            Object obj = null;
            if (sellerName != null && !sellerName.equals("")) {

              Query query1 = session.createSQLQuery(hql1);
              try {
                for (int i = 0; i < parameters.size(); i++) {
                  query1.setParameter(i, parameters.get(i));
                }

                obj = (Object) query1.uniqueResult();

                if (obj != null) {
                  temp_count = new Integer(obj.toString());
                }
                Query query = session.createSQLQuery(hql);
                for (int i = 0; i < parameters.size(); i++) {
                  query.setParameter(i, parameters.get(i));
                }
                if (temp_count.intValue() == 0) {
                  obj = (Object) query.uniqueResult();
                  if (obj != null) {
                    temp_count = new Integer(obj.toString());
                  }
                }
              } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
              }

            } else {
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              if (temp_count.intValue() == 0) {
                obj = (Object) query.uniqueResult();
                if (obj != null) {
                  temp_count = new Integer(obj.toString());
                }
              }
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * hanl 判断是否存在购房信息
   */
  public String findPkidByContractid(final String contractId) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.house_type from pl114 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractId);

            return query.uniqueResult();
          }
        });

    return id;
  }

  /**
   * hanl 删除pl114
   * 
   * @param id
   */
  public void deleteHousesInfo(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "delete from Houses houses where houses.contractId=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, id);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * author wsh 查询下发通知书相关信息(住房类型为商品房01) 2007-10-02
   * 
   * @param contractId 合同编号
   * @param houseType 房屋类型
   * @return giveaccModifyDTO
   */
  public IssuenoticeTaDTO queryIssuenoticeInfo_wsh(final String contractId,
      final String houseType) {
    IssuenoticeTaDTO issuenoticeTaDTO = (IssuenoticeTaDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "";
            if ("01".equals(houseType)) {
              hql = "select b.contract_id contractid,"
                  + " a.borrower_name borrowername," + " a.card_kind cardkind,"
                  + " a.card_num cardnum," + " b.loan_bank_id loanbankid,"
                  + " d.loan_money  loanmoney,"
                  + " d.loan_time_limit loantimelimit,"
                  + " d.loan_month_rate loanmonthrate,"
                  + " d.corpus_interest corpusinterest,"
                  + " d.loan_mode loanmode,"
                  + " c.developer_paybank developerpaybank,"
                  + " c.developer_paybank_a_acc developerpaybankacc,"
                  + " c.HOUSE_TOTLE_PRICE housetotalprice,"
                  + " c.HOUSE_AREA housearea," + " c.HOUSE_ADDR houseaddr, "
                  + " a.org_name orgname, " + " (select case when t.home_mobile is not null and t.org_tel is not null then t.home_mobile || ',' || t.org_tel when t.home_mobile is not null and t.org_tel is null then t.home_mobile  when t.home_mobile is null and t.org_tel is not null then  t.org_tel  end from pl110 t where t.contract_id = b.contract_id) orgtel, "
                  + " d.first_loan_money, " + " c.head_id, "
                  + " b.loan_kou_acc, "
                  + " b.redateperson, "
                  + " b.chkagainperson, "
                  + " b.vipchkagainperson, "
                  + " b.lastchkperson,b.fin_checkman "
                  + " from pl110 a, pl111 b, pl114 c, pl115 d"
                  + " where a.contract_id = b.contract_id"
                  + " and b.contract_id = c.contract_id"
                  + " and c.contract_id = d.contract_id"
                  + " and b.contract_id= ? ";
            } else {
              hql = "select b.contract_id contractid,"
                  + " a.borrower_name borrowername," + " a.card_kind cardkind,"
                  + " a.card_num cardnum," + " b.loan_bank_id loanbankid,"
                  + " d.loan_money loanmoney,"
                  + " d.loan_time_limit loantimelimit,"
                  + " d.loan_month_rate loanmonthrate,"
                  + " d.corpus_interest corpusinterest,"
                  + " d.loan_mode loanmode,"
                  + " c.bargainor_paybank bargainorpaybank,"
                  + " c.bargainor_paybank_acc bargainorpaybankacc, "
                  + " c.BARGAINOR_TOTLE_PRICE housetotalprice,"
                  + " c.BARGAINOR_HOUSE_AREA housearea,"
                  + " c.BARGAINOR_HOUSE_ADDR houseaddr, "
                  + " a.org_name orgname, " + " (select case when t.home_mobile is not null and t.org_tel is not null then t.home_mobile || ',' || t.org_tel when t.home_mobile is not null and t.org_tel is null then t.home_mobile  when t.home_mobile is null and t.org_tel is not null then  t.org_tel  end from pl110 t where t.contract_id = b.contract_id) orgtel, "
                  + " d.first_loan_money, " + " c.head_id, "
                  + " b.loan_kou_acc, "
                  + " b.redateperson, "
                  + " b.chkagainperson, "
                  + " b.vipchkagainperson, "
                  + " b.lastchkperson ,b.fin_checkman "
                  + " from pl110 a, pl111 b, pl114 c, pl115 d"
                  + " where a.contract_id = b.contract_id"
                  + " and b.contract_id = c.contract_id"
                  + " and c.contract_id = d.contract_id"
                  + " and b.contract_id= ? ";
            }
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            IssuenoticeTaDTO temp_issuenoticeTaDTO = new IssuenoticeTaDTO();
            if (obj == null) {
              return temp_issuenoticeTaDTO;
            }
            if (obj[0] != null) {
              temp_issuenoticeTaDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              temp_issuenoticeTaDTO.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              temp_issuenoticeTaDTO.setCardKind(obj[2].toString());
            }
            if (obj[3] != null) {
              temp_issuenoticeTaDTO.setCardNum(obj[3].toString());
            }
            if (obj[4] != null) {
              temp_issuenoticeTaDTO.setLoanBankId(obj[4].toString());
            }
            if (obj[5] != null) {
              temp_issuenoticeTaDTO.setLoanMoney(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              temp_issuenoticeTaDTO.setLoanTimeLimit(obj[6].toString());
            }
            if (obj[7] != null) {
              temp_issuenoticeTaDTO.setLoanMonthRate(new BigDecimal(obj[7]
                  .toString()));
            }
            if (obj[8] != null) {
              temp_issuenoticeTaDTO.setCorpusInterest(new BigDecimal(obj[8]
                  .toString()));
            }
            if (obj[9] != null) {
              temp_issuenoticeTaDTO.setLoanMode(obj[9].toString());
            }
            if (obj[10] != null) {
              temp_issuenoticeTaDTO.setSellerPayBank(obj[10].toString());
            }
            if (obj[11] != null) {
              temp_issuenoticeTaDTO.setSellerPayBankAcc(obj[11].toString());
            }
            if (obj[12] != null) {
              temp_issuenoticeTaDTO.setHouseTotalPrice(obj[12].toString());
            }
            if (obj[13] != null) {
              temp_issuenoticeTaDTO.setHouseArea(obj[13].toString());
            }
            if (obj[14] != null) {
              temp_issuenoticeTaDTO.setHouseAddr(obj[14].toString());
            }
            if (obj[15] != null) {
              temp_issuenoticeTaDTO.setOrgName(obj[15].toString());
            }
            if (obj[16] != null) {
              temp_issuenoticeTaDTO.setOrgTel(obj[16].toString());
            }
            if (obj[17] != null) {
              temp_issuenoticeTaDTO.setFirstLoanMoney(new BigDecimal(obj[17]
                  .toString()));
            }
            if (obj[18] != null) {
              temp_issuenoticeTaDTO.setHeadId((obj[18].toString()));
            }
            if (obj[19] != null) {
              temp_issuenoticeTaDTO.setLoanKouAcc(obj[19].toString());
            }
            if (obj[20] != null) {
              temp_issuenoticeTaDTO.setRedatePerson(obj[20].toString());
            }
            if (obj[21] != null) {
              temp_issuenoticeTaDTO.setChkAgainPerson(obj[21].toString());
            }
            if (obj[22] != null) {
              temp_issuenoticeTaDTO.setVipChkAgainPerson(obj[22].toString());
            }
            if (obj[23] != null) {
              temp_issuenoticeTaDTO.setLastChkPerson(obj[23].toString());
            }
            if (obj[24] != null) {
              temp_issuenoticeTaDTO.setFinChkPerson(obj[24].toString());
            }
            return temp_issuenoticeTaDTO;
          }
        });
    return issuenoticeTaDTO;
  }

  /**
   * author wsh 根据合同编号,借款人姓名，卡号，售房人查询PL110、PL114、PL131中信息 2007-9-26
   * 
   * @param contractId 合同编号
   * @param borrowerName 借款人姓名
   * @param cardNum 卡号
   * @param sellerName 售房人
   * @return List
   */
  public List queryHousePrintList_wsh(final String contractId,
      final String borrowerName, final String cardNum, final String sellerName,
      final List loanBankList) {
    List houseList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql1 = "";
            String hql = "select distinct c.poke_bank_modify_id pokemodifyid,"
                + "b.house_type housetype," + "c.contract_id contractid,"
                + "a.borrower_name borrowername," + "a.card_num cardnum,"
                + "nvl(b.bargainor_name,'') bargainorname,"
                + "nvl(d.developer_name,'') developername,"
                + "c.new_poke_bank newpokebank,"
                + "c.new_poke_bank_acc newpokebankacc," + "b.remark re,"
                + "c.oprator op "
                + "from pl110 a, pl114 b, pl131 c, pl005 d ,pl111 e ";
            String criterion = "";
            String criterion1 = "";
            List list = new ArrayList();
            List temp_list = new ArrayList();
            Vector parameters = new Vector();
            int size = 0;
            if (contractId != null && !contractId.equals("")) {
              criterion += " c.contract_id = ?  and ";
              criterion1 += " c.contract_id = ?  and ";
              parameters.add(contractId);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " e.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
              criterion1 += criterion;
            }
            if (borrowerName != null && !borrowerName.equals("")) {
              criterion += " a.borrower_name like ?  and ";
              criterion1 += " a.borrower_name like ?  and ";
              parameters.add("%" + borrowerName + "%");
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " a.card_num = ? and ";
              criterion1 += " a.card_num = ? and ";
              parameters.add(cardNum);
            }
            if (sellerName != null && !sellerName.equals("")) {
              criterion += " b.bargainor_name = ? and ";
              criterion1 += " d.developer_name = ? and ";
              parameters.add(sellerName);
            }
            if (sellerName != null && !sellerName.equals("")) {
              if (criterion.length() != 0) {
                criterion = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id  and e.contract_id = a.contract_id and b.head_id=d.id(+) and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }
              if (criterion1.length() != 0) {
                criterion1 = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id  and e.contract_id = a.contract_id and  b.head_id=d.id(+) and "
                    + criterion1.substring(0, criterion1.lastIndexOf("and"));
              }
            } else {
              if (criterion.length() != 0) {
                criterion = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id  and e.contract_id = a.contract_id and  b.head_id=d.id(+) and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              } else {
                criterion = "where c.contract_id = a.contract_id and b.contract_id = a.contract_id   and e.contract_id = a.contract_id and  b.head_id=d.id(+) ";
              }
            }
            hql1 = hql + criterion1;
            hql = hql + criterion;
            Object obj[] = null;
            if (sellerName != null && !sellerName.equals("")) {

              Query query1 = session.createSQLQuery(hql1);
              for (int i = 0; i < parameters.size(); i++) {
                query1.setParameter(i, parameters.get(i));
              }
              temp_list = query1.list();
              size = query1.list().size();
              Iterator iter = temp_list.iterator();
              iter.hasNext();
              while (iter.hasNext()) {
                HouseListDTO houseListDTO = new HouseListDTO();
                obj = (Object[]) iter.next();
                if (obj[2] != null) {
                  houseListDTO.setContractId(obj[2].toString());
                }
                if (obj[3] != null) {
                  houseListDTO.setBorrowerName(obj[3].toString());
                }
                if (obj[4] != null) {
                  houseListDTO.setCardNum(obj[4].toString());
                }
                if (obj[2] != null) {
                  // 住房类型为01商品房
                  if ("1".equals(obj[2].toString())) {
                    if (obj[6] != null) {
                      houseListDTO.setSellerName(obj[6].toString());
                    }
                  }
                  // 住房类型为而手房进入
                  else {
                    if (obj[5] != null) {
                      houseListDTO.setSellerName(obj[5].toString());
                    }
                  }
                }
                if (obj[7] != null) {
                  houseListDTO.setNewSellerPayBank(obj[7].toString());
                }

                if (obj[8] != null) {
                  houseListDTO.setNewPayBankAcc(obj[8].toString());
                }
                if (obj[9] != null) {
                  houseListDTO.setRemark(obj[9].toString());
                }
                if (obj[10] != null) {
                  houseListDTO.setOperator(obj[10].toString());
                }
                list.add(houseListDTO);
              }
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (size == 0) {
              temp_list = query.list();
              Iterator iter = temp_list.iterator();
              while (iter.hasNext()) {
                HouseListDTO houseListDTO = new HouseListDTO();
                obj = (Object[]) iter.next();
                if (obj[2] != null) {
                  houseListDTO.setContractId(obj[2].toString());
                }
                if (obj[3] != null) {
                  houseListDTO.setBorrowerName(obj[3].toString());
                }
                if (obj[4] != null) {
                  houseListDTO.setCardNum(obj[4].toString());
                }
                if (obj[2] != null) {
                  // 住房类型为01商品房
                  if ("1".equals(obj[2].toString())) {
                    if (obj[6] != null) {
                      houseListDTO.setSellerName(obj[6].toString());
                    }
                  }
                  // 住房类型为而手房进入
                  else {
                    if (obj[5] != null) {
                      houseListDTO.setSellerName(obj[5].toString());
                    }
                  }
                }
                if (obj[7] != null) {
                  houseListDTO.setNewSellerPayBank(obj[7].toString());
                }

                if (obj[8] != null) {
                  houseListDTO.setNewPayBankAcc(obj[8].toString());
                }
                if (obj[9] != null) {
                  houseListDTO.setRemark(obj[9].toString());
                }
                if (obj[10] != null) {
                  houseListDTO.setOperator(obj[10].toString());
                }

                list.add(houseListDTO);
              }
            }
            return list;
          }
        });
    return houseList;
  }

  /**
   * hanl 判断合同编号是否存在
   * 
   * @param contractid
   * @return
   */
  public String checkcontractid(final String contractid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.contract_id from pl114 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult();
          }
        });

    return id;
  }

  /**
   * yqf 查询PL005开发商名称
   * 
   * @param contractid
   * @return
   */
  public String queryDevelper(final String contractid) {
    String name = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.developer_name from pl005 t where t.id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult();
          }
        });

    return name;
  }

  // 用来看如果这个是特殊借款人，有没有将划款银行设置成个人帐户
  public SpecialBankListDTO findBankPerson(final String contractid) {
    SpecialBankListDTO specialBankListDTO = (SpecialBankListDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p112.per_bank,p112.per_bank_acc from pl112 p112,pl110 p110 "
                + "where p112.privilege_borrower_id=p110.privilege_borrower_id and p110.contract_id= "
                + contractid;

            Query query = session.createSQLQuery(hql);

            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            SpecialBankListDTO specialBankListDTO = new SpecialBankListDTO();

            if (obj == null) {
              return specialBankListDTO;
            }

            if (obj[0] != null) {
              specialBankListDTO.setBankName(obj[0].toString());
            }
            if (obj[1] != null) {
              specialBankListDTO.setBankCard(obj[1].toString());
            }

            return specialBankListDTO;
          }
        });
    return specialBankListDTO;
  }

  public IssuenoticeTaAF queryPrintInfo(final String contractid) {
    IssuenoticeTaAF f = (IssuenoticeTaAF) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p114.house_addr,p114.house_area,p114.house_totle_price, "
                + "p114.head_id,p114.bargainor_paybank_acc,p115.loan_time_limit,p115.first_loan_money,"
                + "p110.emp_id,p110.org_name,p110.org_tel "
                + " from pl114 p114,pl115 p115,pl110 p110 "
                + " where p114.contract_id=p115.contract_id and "
                + " p115.contract_id=p110.contract_id and p110.contract_id="
                + contractid;

            Query query = session.createSQLQuery(hql);
            Object obj[] = null;
            IssuenoticeTaAF printForm = new IssuenoticeTaAF();
            Iterator it = query.list().iterator();
            if (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null)
                printForm.setHouseAddr(obj[0].toString());
              if (obj[1] != null)
                printForm.setHouseArea(obj[1].toString());
              if (obj[2] != null)
                printForm.setHouseTotalPrice(obj[2].toString());
              if (obj[3] != null)
                printForm.setHeadId(obj[3].toString());
              if (obj[4] != null)
                printForm.setSellerPayBankAcc(obj[4].toString());
              if (obj[5] != null)
                printForm.setLoanTimeLimit(obj[5].toString());
              if (obj[6] != null)
                printForm.setFirstLoanMoney(new BigDecimal(obj[6].toString()));
              if (obj[7] != null)
                printForm.setEmpId(obj[7].toString());
              if (obj[8] != null)
                printForm.setOrgName(obj[8].toString());
              if (obj[9] != null)
                printForm.setOrgTel(obj[9].toString());
            }

            return printForm;
          }
        });
    return f;
  }

  /**
   * @author wshuang 查询贷款初审列表信息
   * @param contractId
   * @param officeCode
   * @param borrowerName
   * @param cardNum
   * @param orgName
   * @param opTime
   * @param houseType
   * @param contractSt
   * @return
   */
  public List queryLoanInfoList(final String contractId,
      final String officeCode, final String borrowerName, final String cardNum,
      final String orgName, final String opTimeSt, final String opTimeEnd,
      final String houseType, final String contractSt, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select p110.contract_id,"
                + " p110.borrower_name,"
                + " p110.card_num,"
                + " p110.org_name,"
                + " case when p114.house_type = '01' then p114.house_totle_price else p114.bargainor_totle_price end,"
                + " p115.loan_money,"
                + " p115.loan_time_limit,"
                + " case when p114.house_type = '01' then to_char(p114.house_area) else p114.bargainor_house_area end,"
                + " case when p114.house_type = '01' then p114.house_addr else p114.bargainor_house_addr end,"
                + " p114.house_type," + " p110.operator," + " p111.contract_st"
                + " from pl110 p110, pl111 p111, pl114 p114, pl115 p115"
                + " where p110.contract_id = p114.contract_id"
                + " and p114.contract_id = p115.contract_id"
                + " and p110.contract_id = p111.contract_id"
                + " and p110.office " + securityInfo.getOfficeSecuritySQL();
            String criterion = "";
            Vector parameters = new Vector();
            if (contractId != null && !"".equals(contractId)) {
              criterion += " p110.contract_id = ? and ";
              parameters.add(contractId);
            }
            if (officeCode != null && !"".equals(officeCode)) {
              criterion += " p110.office = ? and ";
              parameters.add(officeCode);
            }
            if (borrowerName != null && !"".equals(borrowerName)) {
              criterion += " p110.borrower_name = ? and ";
              parameters.add(borrowerName);
            }

            if (cardNum != null && !"".equals(cardNum)) {
              criterion += " p110.card_num = ? and ";
              parameters.add(cardNum);
            }

            if (orgName != null && !"".equals(orgName)) {
              criterion += " p110.org_name = ? and ";
              parameters.add(orgName);
            }

            if (houseType != null && !"".equals(houseType)) {
              criterion += " p114.house_type = ? and ";
              parameters.add(houseType);
            }

            if (opTimeSt != null && !"".equals(opTimeSt)) {
              criterion += " p110.op_time >= ? and ";
              parameters.add(opTimeSt);
            }

            if (opTimeEnd != null && !"".equals(opTimeEnd)) {
              criterion += " p110.op_time <= ? and ";
              parameters.add(opTimeEnd);
            }
            if (contractSt != null && !"".equals(contractSt)) {
              criterion += " p111.contract_st = ? and ";
              parameters.add(contractSt);
            } else {
              criterion += " p111.contract_st = 2 and ";
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            String ob = orderBy;
            if (ob == null)
              ob = " p110.contract_id ";

            String od = order;
            if (od == null)
              od = " DESC";
            sql = sql + criterion + " order by " + ob + " " + od;

            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List list = new ArrayList();

            Iterator iterater = query.list().iterator();
            Object[] obj = null;
            while (iterater.hasNext()) {
              LoanFirstCheckDTO dto = new LoanFirstCheckDTO();
              obj = (Object[]) iterater.next();
              dto.setContractId(obj[0].toString());
              dto.setBorrowerName(obj[1].toString());
              dto.setCardNum(obj[2].toString());
              if (obj[3] != null) {
                dto.setOrgName(obj[3].toString());
              }
              if (obj[4] != null) {
                dto.setHousePrice(obj[4].toString());
              }
              if (obj[5] != null) {
                dto.setLoanMoney(obj[5].toString());
              }
              if (obj[6] != null) {
                dto.setLoanTimeLimit(obj[6].toString());
              }
              if (obj[7] != null) {
                dto.setHouseArea(obj[7].toString());
              }
              if (obj[8] != null) {
                dto.setHouseAdd(obj[8].toString());
              }
              if (obj[9] != null) {
                dto.setHouseType(obj[9].toString());
              }
              if (obj[10] != null) {
                dto.setOperator(obj[10].toString());
              }
              if (obj[11] != null) {
                dto.setContractSt(obj[11].toString());
              }
              list.add(dto);
            }
            return list;
          }
        });
    return list;

  }
  /**
   * 查询查询贷款初审列表的数量
   * @param contractId
   * @param officeCode
   * @param borrowerName
   * @param cardNum
   * @param orgName
   * @param opTimeSt
   * @param opTimeEnd
   * @param houseType
   * @param contractSt
   * @param securityInfo
   * @return
   */
  public int queryLoanInfoListCount(final String contractId,
      final String officeCode, final String borrowerName, final String cardNum,
      final String orgName, final String opTimeSt, final String opTimeEnd,
      final String houseType, final String contractSt, final SecurityInfo securityInfo) {
    Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select count(p110.contract_id)"
            + " from pl110 p110, pl111 p111, pl114 p114, pl115 p115"
            + " where p110.contract_id = p114.contract_id"
            + " and p114.contract_id = p115.contract_id"
            + " and p110.contract_id = p111.contract_id"
            + " and p110.office " + securityInfo.getOfficeSecuritySQL();
        String criterion = "";
        Vector parameters = new Vector();
        if (contractId != null && !"".equals(contractId)) {
          criterion += " p110.contract_id = ? and ";
          parameters.add(contractId);
        }
        if (officeCode != null && !"".equals(officeCode)) {
          criterion += " p110.office = ? and ";
          parameters.add(officeCode);
        }
        if (borrowerName != null && !"".equals(borrowerName)) {
          criterion += " p110.borrower_name = ? and ";
          parameters.add(borrowerName);
        }

        if (cardNum != null && !"".equals(cardNum)) {
          criterion += " p110.card_num = ? and ";
          parameters.add(cardNum);
        }

        if (orgName != null && !"".equals(orgName)) {
          criterion += " p110.org_name = ? and ";
          parameters.add(orgName);
        }

        if (houseType != null && !"".equals(houseType)) {
          criterion += " p114.house_type = ? and ";
          parameters.add(houseType);
        }

        if (opTimeSt != null && !"".equals(opTimeSt)) {
          criterion += " p110.op_time >= ? and ";
          parameters.add(opTimeSt);
        }

        if (opTimeEnd != null && !"".equals(opTimeEnd)) {
          criterion += " p110.op_time <= ? and ";
          parameters.add(opTimeEnd);
        }
        if (contractSt != null && !"".equals(contractSt)) {
          criterion += " p111.contract_st = ? and ";
          parameters.add(contractSt);
        } else {
          criterion += " p111.contract_st = 2 and ";
        }
        if (criterion.length() != 0)
          criterion = " and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        sql = sql + criterion;

        Query query = session.createSQLQuery(sql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return Integer.valueOf(query.uniqueResult().toString());
      }
    });
    return count.intValue();

  }
  public String queryErShouFangSellerName(final String contractid) {
    String name = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl114.bargainor_name from pl114 where pl114.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult();
          }
        });

    return name;
  }
  public Object[] getLoanMoneyPerson(final String contractid) {
    Object[] obj = (Object[]) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select p5.loan_money,p1.lastchkperson,p1.vipchkagainperson,p1.fin_checkman from pl115 p5,pl111 p1 where p1.contract_id=p5.contract_id and p1.contract_id = ? ";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, contractid);
            return (Object[]) query.uniqueResult();
          }
        });

    return obj;
  }
}
