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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.domain.entity.BankCredence;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTbShowListDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccBcaDTO;




public class BankCredenceDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public BankCredence queryById(Serializable id) {
    Validate.notNull(id);
    return (BankCredence) getHibernateTemplate().get(
        BankCredence.class, id);
  }

  /**
   * 插入记录
   * 
   * @param BankCredence
   * @return
   */
  public Serializable insert(BankCredence bankCredence) {
    Serializable id = null;
    try {
      Validate.notNull(bankCredence);
      id = getHibernateTemplate().save(bankCredence);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 银行对账单
   * @author 郭婧平
   * 2007-10-25
   * 根据主键id查找fn211表中有没有该条数据
   * 查询条件：credenceId
   */
  public Integer queryBankCredenceById(final Integer credenceId) throws Exception {
    Integer id = null;
    try {
      id = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select bankCredence.credenceId from BankCredence bankCredence where bankCredence.credenceId = ? ";
              Query query = session.createQuery(hql);
              query.setParameter(0, credenceId);
              return query.uniqueResult();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 银行对账单
   * @author 郭婧平
   * 2007-10-24
   * 根据settNum查找fn211表中有没有符合查询条件的数据
   * 查询条件：settNum,credenceId
   */
  public List queryBankCredenceBySettNum(final String settNum,final String subjectCode,final SecurityInfo securityInfo,final String credenceId) {
    List list=new ArrayList();
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select bankCredence.credenceId from BankCredence bankCredence ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "bankCredence.bookId= ? and ";
            parameters.add(securityInfo.getBookId());
            
            criterion += "bankCredence.settNum= ? and ";
            parameters.add(settNum);
            
            criterion += "bankCredence.subjectCode= ? and ";
            parameters.add(subjectCode);
            
            if (credenceId != null && !credenceId.equals("")) {
              criterion += "(bankCredence.credenceId < ?  or bankCredence.credenceId < ?) and ";
              parameters.add(new Integer(credenceId));
              parameters.add(new Integer(credenceId));
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
   * 银行对账单
   * @author 郭婧平
   * 2007-10-24
   * 根据查询条件查找FN211表中的数据
   * 查询条件：subjectCode,subjectName,settDate,settNum,summary,money
   */
  public List queryBankCheckAccTbList(final BankCheckAccTbFindDTO bankCheckAccTbFindDTO,final List officeList,final SecurityInfo securityInfo,
      final String orderBy,final String order, final int start, final int pageSize,final int page) throws Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn211.credence_id,fn211.sett_date,fn211.summary," +
              "fn211.subject_code,fn110.subject_name,fn211.debit,fn211.credit," +
              "fn211.sett_type,fn211.sett_num,fn211.dopsn " + 
              "from FN211 fn211,FN110 fn110 " + 
              "where fn211.subject_code=fn110.subject_code " +
              "and fn211.book_id=fn110.book_id " +
              "and fn211.credence_type='1' " +
              "and fn211.sett_date is not null ";
          Vector parameters = new Vector();
          String criterion = "";
          
          criterion += "fn211.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (officeList != null && officeList.size() > 0) {
            criterion += "and ( ";
            for (int i = 0; i < officeList.size(); i++) {
              criterion += " fn211.office = ? or ";
              parameters.add(officeList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and ";
          }
          if (bankCheckAccTbFindDTO.getSettDateSt().trim() != null&&!bankCheckAccTbFindDTO.getSettDateSt().trim().equals("") && bankCheckAccTbFindDTO.getSettDateEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettDateEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn211.sett_date >=? and fn211.sett_date <=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateSt().trim());
            parameters.add(bankCheckAccTbFindDTO.getSettDateEnd().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettDateSt().trim() != null&&!bankCheckAccTbFindDTO.getSettDateSt().trim().equals("") && (bankCheckAccTbFindDTO.getSettDateEnd().trim() == null || bankCheckAccTbFindDTO.getSettDateEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn211.sett_date >= ? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateSt().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettDateEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettDateEnd().trim().equals("") && (bankCheckAccTbFindDTO.getSettDateSt().trim() == null || bankCheckAccTbFindDTO.getSettDateSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn211.sett_date <= ? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateEnd().trim());
          }

          if (bankCheckAccTbFindDTO.getSummary().trim() != null && !bankCheckAccTbFindDTO.getSummary().trim().equals("")) {
            criterion += " fn211.summary=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSummary().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSubjectCode().trim() != null && !bankCheckAccTbFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn211.subject_code=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSubjectCode().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSubjectName().trim() != null && !bankCheckAccTbFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSubjectName().trim());
          }
          
          if (bankCheckAccTbFindDTO.getMoneySt().trim() != null&&!bankCheckAccTbFindDTO.getMoneySt().trim().equals("") && bankCheckAccTbFindDTO.getMoneyEnd().trim() != null&&!bankCheckAccTbFindDTO.getMoneyEnd().trim().equals("")) {//有开始金额结束金额
            criterion += " ((fn211.debit >=? and fn211.debit <=?) or (fn211.credit >=? and fn211.credit <=?)) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
          }
          
          if (bankCheckAccTbFindDTO.getMoneySt().trim() != null&&!bankCheckAccTbFindDTO.getMoneySt().trim().equals("") && (bankCheckAccTbFindDTO.getMoneyEnd().trim() == null || bankCheckAccTbFindDTO.getMoneyEnd().trim().equals(""))) {//有开始金额无结束金额
            criterion += " (fn211.debit >= ? or fn211.credit >=?) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
          }
          
          if (bankCheckAccTbFindDTO.getMoneyEnd().trim() != null&&!bankCheckAccTbFindDTO.getMoneyEnd().trim().equals("") && (bankCheckAccTbFindDTO.getMoneySt().trim() == null || bankCheckAccTbFindDTO.getMoneySt().trim().equals(""))) {//无开始金额有结束金额
            criterion += " (fn211.debit <= ? or fn211.credit <=?) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumSt().trim() != null&&!bankCheckAccTbFindDTO.getSettNumSt().trim().equals("") && bankCheckAccTbFindDTO.getSettNumEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettNumEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn211.sett_num >=to_number(?) and fn211.sett_num <=to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumSt().trim());
            parameters.add(bankCheckAccTbFindDTO.getSettNumEnd().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumSt().trim() != null&&!bankCheckAccTbFindDTO.getSettNumSt().trim().equals("") && (bankCheckAccTbFindDTO.getSettNumEnd().trim() == null || bankCheckAccTbFindDTO.getSettNumEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn211.sett_num >= to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumSt().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettNumEnd().trim().equals("") && (bankCheckAccTbFindDTO.getSettNumSt().trim() == null || bankCheckAccTbFindDTO.getSettNumSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn211.sett_num <= to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumEnd().trim());
          }
          

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " fn211.credence_id ";

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
          
          List queryList=query.list();
          
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }

          Iterator it = queryList.iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              BankCheckAccTbShowListDTO bankCheckAccTbShowListDTO = new BankCheckAccTbShowListDTO();
              bankCheckAccTbShowListDTO.setCredenceId(obj[0].toString());
              if (obj[1] != null) {
                bankCheckAccTbShowListDTO.setSettDate(obj[1].toString());
              }
              if (obj[2] !=null) {
                bankCheckAccTbShowListDTO.setSummary(obj[2].toString());
              }
              bankCheckAccTbShowListDTO.setSubjectCode(obj[3].toString());
              bankCheckAccTbShowListDTO.setSubjectName(obj[4].toString());
              bankCheckAccTbShowListDTO.setDebit(new BigDecimal(obj[5].toString()));
              bankCheckAccTbShowListDTO.setCredit(new BigDecimal(obj[6].toString()));
              if (obj[7] != null) {
                bankCheckAccTbShowListDTO.setSettType(obj[7].toString());
              }
              if (obj[8] != null) {
                bankCheckAccTbShowListDTO.setSettNum(obj[8].toString());
              }
              if (obj[9] != null) {
                bankCheckAccTbShowListDTO.setDopsn(obj[9].toString());
              }
              temp_list.add(bankCheckAccTbShowListDTO);
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
   * 银行对账单
   * @author 郭婧平
   * 2007-10-24
   * 根据查询条件查找FN211表中的数据
   * 查询条件：subjectCode,subjectName,settDate,settNum,summary,money
   */
  public List queryBankCheckAccTbListCount(final BankCheckAccTbFindDTO bankCheckAccTbFindDTO,final List officeList,final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn211.credence_id,fn211.debit,fn211.credit " +
              "from FN211 fn211,FN110 fn110 " + 
              "where fn211.subject_code=fn110.subject_code " +
              "and fn211.book_id=fn110.book_id " +
              "and fn211.credence_type='1' " +
              "and fn211.sett_date is not null ";
          Vector parameters = new Vector();
          String criterion = "";
          
          criterion += "fn211.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (officeList != null && officeList.size() > 0) {
            criterion += "and ( ";
            for (int i = 0; i < officeList.size(); i++) {
              criterion += " fn211.office = ? or ";
              parameters.add(officeList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and ";
          }
          
          if (bankCheckAccTbFindDTO.getSettDateSt().trim() != null&&!bankCheckAccTbFindDTO.getSettDateSt().trim().equals("") && bankCheckAccTbFindDTO.getSettDateEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettDateEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn211.sett_date >=? and fn211.sett_date <=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateSt().trim());
            parameters.add(bankCheckAccTbFindDTO.getSettDateEnd().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettDateSt().trim() != null&&!bankCheckAccTbFindDTO.getSettDateSt().trim().equals("") && (bankCheckAccTbFindDTO.getSettDateEnd().trim() == null || bankCheckAccTbFindDTO.getSettDateEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn211.sett_date >= ? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateSt().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettDateEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettDateEnd().trim().equals("") && (bankCheckAccTbFindDTO.getSettDateSt().trim() == null || bankCheckAccTbFindDTO.getSettDateSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn211.sett_date <= ? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateEnd().trim());
          }

          if (bankCheckAccTbFindDTO.getSummary().trim() != null && !bankCheckAccTbFindDTO.getSummary().trim().equals("")) {
            criterion += " fn211.summary=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSummary().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSubjectCode().trim() != null && !bankCheckAccTbFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn211.subject_code=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSubjectCode().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSubjectName().trim() != null && !bankCheckAccTbFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSubjectName().trim());
          }
          
          if (bankCheckAccTbFindDTO.getMoneySt().trim() != null&&!bankCheckAccTbFindDTO.getMoneySt().trim().equals("") && bankCheckAccTbFindDTO.getMoneyEnd().trim() != null&&!bankCheckAccTbFindDTO.getMoneyEnd().trim().equals("")) {//有开始金额结束金额
            criterion += " ((fn211.debit >=? and fn211.debit <=?) or (fn211.credit >=? and fn211.credit <=?)) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
          }
          
          if (bankCheckAccTbFindDTO.getMoneySt().trim() != null&&!bankCheckAccTbFindDTO.getMoneySt().trim().equals("") && (bankCheckAccTbFindDTO.getMoneyEnd().trim() == null || bankCheckAccTbFindDTO.getMoneyEnd().trim().equals(""))) {//有开始金额无结束金额
            criterion += " (fn211.debit >= ? or fn211.credit >=?) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
          }
          
          if (bankCheckAccTbFindDTO.getMoneyEnd().trim() != null&&!bankCheckAccTbFindDTO.getMoneyEnd().trim().equals("") && (bankCheckAccTbFindDTO.getMoneySt().trim() == null || bankCheckAccTbFindDTO.getMoneySt().trim().equals(""))) {//无开始金额有结束金额
            criterion += " (fn211.debit <= ? or fn211.credit <=?) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumSt().trim() != null&&!bankCheckAccTbFindDTO.getSettNumSt().trim().equals("") && bankCheckAccTbFindDTO.getSettNumEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettNumEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn211.sett_num >=to_number(?) and fn211.sett_num <=to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumSt().trim());
            parameters.add(bankCheckAccTbFindDTO.getSettNumEnd().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumSt().trim() != null&&!bankCheckAccTbFindDTO.getSettNumSt().trim().equals("") && (bankCheckAccTbFindDTO.getSettNumEnd().trim() == null || bankCheckAccTbFindDTO.getSettNumEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn211.sett_num >= to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumSt().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettNumEnd().trim().equals("") && (bankCheckAccTbFindDTO.getSettNumSt().trim() == null || bankCheckAccTbFindDTO.getSettNumSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn211.sett_num <= to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumEnd().trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              BankCheckAccTbShowListDTO bankCheckAccTbShowListDTO = new BankCheckAccTbShowListDTO();
              bankCheckAccTbShowListDTO.setCredenceId(obj[0].toString());
              bankCheckAccTbShowListDTO.setDebit(new BigDecimal(obj[1].toString()));
              bankCheckAccTbShowListDTO.setCredit(new BigDecimal(obj[2].toString()));
              temp_list.add(bankCheckAccTbShowListDTO);
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
   * 银行对账单--维护页面
   * @author 郭婧平
   * 2007-10-25
   * 根据fn211.CREDENCE_ID删除该条记录
   */
  public void deleteBankCredence(final String credenceId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from BankCredence bankCredence where bankCredence.credenceId= ? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(credenceId));
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 银行对账单--维护页面
   * @author 郭婧平
   * 2007-10-25
   * 把主键id放到list中传进来，把列表中的数据全部删除
   */
  public void deleteBankCredenceAll(final List list) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String hql = "delete from BankCredence bankCredence ";
          
          Vector parameters = new Vector();
          String criterion = "";
          
          if (list != null && list.size() > 0) {
            criterion += "where bankCredence.credenceId in ( ";
            for (int i = 0; i < list.size(); i++) {
              criterion += " ? , ";
              parameters.add(list.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += ") ";
          }
          
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 银行对账单--维护页面--修改
   * @author 郭婧平
   * 2007-10-25
   * 根据fn211.CREDENCE_ID查询相应的数据
   */
  public BankCheckAccTaDTO queryBankCredenceByCredenceId(final String credenceId) {
    BankCheckAccTaDTO bankCheckAccTaDTO=null;
    bankCheckAccTaDTO = (BankCheckAccTaDTO)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select bankCredence.office,bankCredence.subjectCode,bankCredence.summary,bankCredence.debit" +
                ",bankCredence.credit,bankCredence.settType,bankCredence.settNum,bankCredence.dopsn,bankCredence.settDate from BankCredence bankCredence " +
                "where bankCredence.credenceId=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(credenceId));
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            BankCheckAccTaDTO dto=new BankCheckAccTaDTO();
            if (obj != null) {
              dto.setOffice(obj[0].toString());
              dto.setSubjectCode(obj[1].toString());
              if (obj[2] != null) {
                dto.setSummary(obj[2].toString());
              }
              dto.setDebit(new BigDecimal(obj[3].toString()));
              dto.setCredit(new BigDecimal(obj[4].toString()));
              if (obj[5] != null) {
                dto.setSettType(obj[5].toString());
              }
              if (obj[6] != null) {
                dto.setSettNum(obj[6].toString());
              }
              if (obj[7] != null) {
                dto.setDopsn(obj[7].toString());
              }
              if (obj[8] != null) {
                dto.setSettDate(obj[8].toString());
              }
            }
            return dto;
          }
        });
    return bankCheckAccTaDTO;
  }
  /**
   * 银行对账单--维护页面--修改
   * @author 郭婧平
   * 2007-10-25
   * 根据fn211.CREDENCE_ID修改数据
   */
  public void updateBankCredenceByCredenceId(final BankCheckAccTaDTO bankCheckAccTaDTO) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update BankCredence bankCredence set bankCredence.subjectCode=? ," +
              "bankCredence.summary=?,bankCredence.debit=?,bankCredence.credit=?,bankCredence.settType=?," +
              "bankCredence.settNum=?,bankCredence.dopsn=?,bankCredence.settDate=? "
              + "where bankCredence.credenceId=? ";
          Query query=session.createQuery(hql);
          query.setString(0, bankCheckAccTaDTO.getSubjectCode().trim());
          query.setString(1, bankCheckAccTaDTO.getSummary().trim());
          query.setBigDecimal(2, bankCheckAccTaDTO.getDebit());
          query.setBigDecimal(3, bankCheckAccTaDTO.getCredit());
          query.setString(4, bankCheckAccTaDTO.getSettType().trim());
          query.setString(5, bankCheckAccTaDTO.getSettNum().trim());
          query.setString(6, bankCheckAccTaDTO.getDopsn().trim());
          query.setString(7, bankCheckAccTaDTO.getSettDate());
          query.setInteger(8, Integer.parseInt(bankCheckAccTaDTO.getCredenceId()));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 银行对账单--维护页面--打印
   * @author 郭婧平
   * 2007-10-25
   * 根据查询条件查找FN211表中的数据
   * 查询条件：subjectCode,subjectName,settDate,settNum,summary,money
   */
  public List queryBankCheckAccTbListPrint(final BankCheckAccTbFindDTO bankCheckAccTbFindDTO,final List officeList,final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn211.credence_id,fn211.sett_date,fn211.summary," +
              "fn211.subject_code,fn110.subject_name,fn211.debit,fn211.credit," +
              "fn211.sett_type,fn211.sett_num,fn211.dopsn " + 
              "from FN211 fn211,FN110 fn110 " + 
              "where fn211.subject_code=fn110.subject_code " +
              "and fn211.book_id=fn110.book_id " +
              "and fn211.credence_type='1' " +
              "and fn211.sett_date is not null ";
          Vector parameters = new Vector();
          String criterion = "";
          
          criterion += "fn211.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (officeList != null && officeList.size() > 0) {
            criterion += "and ( ";
            for (int i = 0; i < officeList.size(); i++) {
              criterion += " fn211.office = ? or ";
              parameters.add(officeList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and ";
          }
          
          if (bankCheckAccTbFindDTO.getSettDateSt().trim() != null&&!bankCheckAccTbFindDTO.getSettDateSt().trim().equals("") && bankCheckAccTbFindDTO.getSettDateEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettDateEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn211.sett_date >=? and fn211.sett_date <=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateSt().trim());
            parameters.add(bankCheckAccTbFindDTO.getSettDateEnd().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettDateSt().trim() != null&&!bankCheckAccTbFindDTO.getSettDateSt().trim().equals("") && (bankCheckAccTbFindDTO.getSettDateEnd().trim() == null || bankCheckAccTbFindDTO.getSettDateEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn211.sett_date >= ? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateSt().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettDateEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettDateEnd().trim().equals("") && (bankCheckAccTbFindDTO.getSettDateSt().trim() == null || bankCheckAccTbFindDTO.getSettDateSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn211.sett_date <= ? and ";
            parameters.add(bankCheckAccTbFindDTO.getSettDateEnd().trim());
          }

          if (bankCheckAccTbFindDTO.getSummary().trim() != null && !bankCheckAccTbFindDTO.getSummary().trim().equals("")) {
            criterion += " fn211.summary=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSummary().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSubjectCode().trim() != null && !bankCheckAccTbFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn211.subject_code=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSubjectCode().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSubjectName().trim() != null && !bankCheckAccTbFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name=? and ";
            parameters.add(bankCheckAccTbFindDTO.getSubjectName().trim());
          }
          
          if (bankCheckAccTbFindDTO.getMoneySt().trim() != null&&!bankCheckAccTbFindDTO.getMoneySt().trim().equals("") && bankCheckAccTbFindDTO.getMoneyEnd().trim() != null&&!bankCheckAccTbFindDTO.getMoneyEnd().trim().equals("")) {//有开始金额结束金额
            criterion += " ((fn211.debit >=? and fn211.debit <=?) or (fn211.credit >=? and fn211.credit <=?)) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
          }
          
          if (bankCheckAccTbFindDTO.getMoneySt().trim() != null&&!bankCheckAccTbFindDTO.getMoneySt().trim().equals("") && (bankCheckAccTbFindDTO.getMoneyEnd().trim() == null || bankCheckAccTbFindDTO.getMoneyEnd().trim().equals(""))) {//有开始金额无结束金额
            criterion += " (fn211.debit >= ? or fn211.credit >=?) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
            parameters.add(bankCheckAccTbFindDTO.getMoneySt());
          }
          
          if (bankCheckAccTbFindDTO.getMoneyEnd().trim() != null&&!bankCheckAccTbFindDTO.getMoneyEnd().trim().equals("") && (bankCheckAccTbFindDTO.getMoneySt().trim() == null || bankCheckAccTbFindDTO.getMoneySt().trim().equals(""))) {//无开始金额有结束金额
            criterion += " (fn211.debit <= ? or fn211.credit <=?) and ";
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
            parameters.add(bankCheckAccTbFindDTO.getMoneyEnd());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumSt().trim() != null&&!bankCheckAccTbFindDTO.getSettNumSt().trim().equals("") && bankCheckAccTbFindDTO.getSettNumEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettNumEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn211.sett_num >=to_number(?) and fn211.sett_num <=to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumSt().trim());
            parameters.add(bankCheckAccTbFindDTO.getSettNumEnd().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumSt().trim() != null&&!bankCheckAccTbFindDTO.getSettNumSt().trim().equals("") && (bankCheckAccTbFindDTO.getSettNumEnd().trim() == null || bankCheckAccTbFindDTO.getSettNumEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn211.sett_num >= to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumSt().trim());
          }
          
          if (bankCheckAccTbFindDTO.getSettNumEnd().trim() != null&&!bankCheckAccTbFindDTO.getSettNumEnd().trim().equals("") && (bankCheckAccTbFindDTO.getSettNumSt().trim() == null || bankCheckAccTbFindDTO.getSettNumSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn211.sett_num <= to_number(?) and ";
            parameters.add(bankCheckAccTbFindDTO.getSettNumEnd().trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              BankCheckAccTbShowListDTO bankCheckAccTbShowListDTO = new BankCheckAccTbShowListDTO();
              bankCheckAccTbShowListDTO.setCredenceId(obj[0].toString());
              if (obj[1] != null) {
                bankCheckAccTbShowListDTO.setSettDate(obj[1].toString());
              }
              if (obj[2] !=null) {
                bankCheckAccTbShowListDTO.setSummary(obj[2].toString());
              }
              bankCheckAccTbShowListDTO.setSubjectCode(obj[3].toString());
              bankCheckAccTbShowListDTO.setSubjectName(obj[4].toString());
              bankCheckAccTbShowListDTO.setDebit(new BigDecimal(obj[5].toString()));
              bankCheckAccTbShowListDTO.setCredit(new BigDecimal(obj[6].toString()));
              if (obj[7] != null) {
                bankCheckAccTbShowListDTO.setSettType(obj[7].toString());
              }
              if (obj[8] != null) {
                bankCheckAccTbShowListDTO.setSettNum(obj[8].toString());
              }
              if (obj[9] != null) {
                bankCheckAccTbShowListDTO.setDopsn(obj[9].toString());
              }
              temp_list.add(bankCheckAccTbShowListDTO);
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
   * 银行对账单
   * @author 郭婧平
   * 2007-10-27
   * 验证八位日期格式
   */
  public void checkDate(final String date) throws SQLException{
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " select to_date(?,'YYYYMMDD') from dual ";
        Query query=session.createSQLQuery(hql);
        query.setString(0, date);
        return query.uniqueResult();
      }
    });
  }
  /**
   * 银行存款对账单--银行对账单列表
   * @author 郭婧平
   * 2007-10-30
   * 根据查询条件查找FN211表中的数据
   * 查询条件：settDate,subjectCode
   */
  public List queryDepositCheckAccBcaList(final String settDateSt,final String settDateEnd,final String subjectCode,final List officeList,final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn211.sett_date,fn211.subject_code,fn211.sett_type," +
              "fn211.sett_num,fn211.debit,fn211.credit,fn211.credence_id " +
              "from FN211 fn211 " +
              "where fn211.credence_type='1' " +
              "and fn211.sett_date is not null ";
          Vector parameters = new Vector();
          String criterion = "";
          
          criterion += "fn211.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (officeList != null && officeList.size() > 0) {
            criterion += "and ( ";
            for (int i = 0; i < officeList.size(); i++) {
              criterion += " fn211.office = ? or ";
              parameters.add(officeList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and ";
          }
          if (settDateSt.trim() != null&&!settDateSt.trim().equals("") && settDateEnd.trim() != null&&!settDateEnd.trim().equals("")) {//有开始日期结束日期
            criterion += " fn211.sett_date >=? and fn211.sett_date <=? and ";
            parameters.add(settDateSt.trim());
            parameters.add(settDateEnd.trim());
          }
          
          if (settDateSt.trim() != null&&!settDateSt.trim().equals("") && (settDateEnd.trim() == null || settDateEnd.trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn211.sett_date >= ? and ";
            parameters.add(settDateSt.trim());
          }
          
          if (settDateEnd.trim() != null&&!settDateEnd.trim().equals("") && (settDateSt.trim() == null || settDateSt.trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn211.sett_date <= ? and ";
            parameters.add(settDateEnd.trim());
          }

          if (subjectCode.trim() != null && !subjectCode.trim().equals("")) {
            criterion += " fn211.subject_code=? and ";
            parameters.add(subjectCode.trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion +" order by fn211.sett_date DESC,fn211.sett_num DESC";;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              DepositCheckAccBcaDTO depositCheckAccBcaDTO = new DepositCheckAccBcaDTO();
              if (obj[0] != null) {
                depositCheckAccBcaDTO.setSettDate(obj[0].toString());
              }
              depositCheckAccBcaDTO.setSubjectCode(obj[1].toString());
              if (obj[2] != null) {
                depositCheckAccBcaDTO.setSettType(obj[2].toString());
              }
              if (obj[3] != null) {
                depositCheckAccBcaDTO.setSettNum(obj[3].toString());
              }
              depositCheckAccBcaDTO.setDebit(new BigDecimal(obj[4].toString()));
              depositCheckAccBcaDTO.setCredit(new BigDecimal(obj[5].toString()));
              depositCheckAccBcaDTO.setCredenceId(obj[6].toString());
              temp_list.add(depositCheckAccBcaDTO);
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
}
