package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.dto.PickYearRepInfoDTO;
import org.xpup.hafmis.sysfinance.accmng.listacc.dto.ListaccDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckModifyDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckShowListDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.dto.CredenceclearModifyDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbListDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTcFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTcListDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.DelectCredenceInfoDTO;
import org.xpup.hafmis.sysfinance.bookmng.bookstart.dto.BookstartDTO;
import org.xpup.hafmis.sysfinance.bookmng.datainitialize.dto.DatainitializeDTO;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopListDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbShowListDTO;

public class AccountantCredenceDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public AccountantCredence queryById(Serializable id) {
    Validate.notNull(id);
    return (AccountantCredence) getHibernateTemplate().get(
        AccountantCredence.class, id);
  }

  /**
   * 插入记录
   * 
   * @param AccountantCredence
   * @return
   */
  public Serializable insert(AccountantCredence accountantCredence) {
    Serializable id = null;
    try {
      Validate.notNull(accountantCredence);
      id = getHibernateTemplate().save(accountantCredence);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 删除记录
   * 
   * @param accountantCredence
   */
  public void delete(AccountantCredence accountantCredence) {
    try {
      Validate.notNull(accountantCredence);
      getHibernateTemplate().delete(accountantCredence);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 科目代码是否存在
   * 
   * @param code
   * @param securityInfo
   * @return
   */
  public List is_CodeIn_WL(final String code, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select accountantCredence.subjectCode from AccountantCredence accountantCredence ";
        Vector parameters = new Vector();
        String criterion = "";

        criterion += "accountantCredence.bookId = ?  and ";
        parameters.add(securityInfo.getBookId());

        if (code != null && !code.equals("")) {
          criterion += "accountantCredence.subjectCode = ?  and ";
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
   * 现金日记账--自动转账页面
   * 
   * @author 郭婧平 2007-10-17
   *         根据条件查询表fn201中settNum,credenceCharacter,credenceNum,subjectCode
   *         查询条件：credence_id
   */
  public List querySettNumByCredenceId(final List credenceIdList) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select accountantCredence.credenceNum,accountantCredence.credenceDate,"
            + "accountantCredence.settNum,accountantCredence.subjectCode from AccountantCredence accountantCredence ";
        Vector parameters = new Vector();
        String criterion = "";
        if (credenceIdList != null && credenceIdList.size() > 0) {
          criterion += "where ";
          for (int i = 0; i < credenceIdList.size(); i++) {
            criterion += " accountantCredence.credenceId = ? or ";
            parameters.add(new Integer(credenceIdList.get(i).toString()));
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
        }
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Iterator it = query.list().iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CashDayClearTbShowListDTO cashDayClearTbShowListDTO = new CashDayClearTbShowListDTO();
            if (obj[0] != null) {
              cashDayClearTbShowListDTO.setCredenceNum(obj[0].toString());
            }
            if (obj[1] != null) {
              cashDayClearTbShowListDTO.setCredenceDate(obj[1].toString());
            }
            if (obj[2] != null) {
              cashDayClearTbShowListDTO.setSettNum(obj[2].toString());
            }
            if (obj[3] != null) {
              cashDayClearTbShowListDTO.setSubjectCode(obj[3].toString());
            }
            temp_list.add(cashDayClearTbShowListDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * 现金日记账--自动转账页面
   * 
   * @author 郭婧平 2007-10-15 查找FN201表中符合条件的记录，放在页面的list中
   *         查询条件：cashDayClearTbFindDTO
   */
  public List queryCashDayClearTbList(
      final CashDayClearTbFindDTO cashDayClearTbFindDTO,
      final String credenceType, final SecurityInfo securityInfo,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn201.credence_date,"
              + "fn201.credence_character,"
              + "fn201.credence_num,fn201.summay,fn201.subject_code,"
              + "fn110.subject_name,fn201.debit,fn201.credit,fn201.sett_num,fn201.sett_date,fn201.credence_id,fn201.office "
              + "from FN201 fn201,FN110 fn110,FN102 fn102 "
              + "where fn201.subject_code=fn110.subject_code "
              + "and fn201.book_id=fn110.book_id "
              + "and fn201.credence_st='2' "
              + "and fn201.summay=fn102.para_id "
              + "and fn201.book_id=fn102.book_id "
              + "and fn102.param_value not in('1','4','5') ";
          Vector parameters = new Vector();
          String criterion = "";

          criterion += "fn110.subject_property = ?  and ";
          parameters.add(credenceType);

          if (credenceType.equals("0")) {
            criterion += "fn201.cash_acc_st='0'  and ";
          }

          if (credenceType.equals("1")) {
            criterion += "fn201.bank_acc_st='0'  and ";
          }

          criterion += "fn201.book_id = ?  and ";
          parameters.add(securityInfo.getBookId());

          if (cashDayClearTbFindDTO.getCredenceDateSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateSt().trim().equals("")
              && cashDayClearTbFindDTO.getCredenceDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_date >=? and fn201.credence_date <=? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceDateSt().trim());
            parameters.add(cashDayClearTbFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceDateSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateSt().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceDateEnd().trim() == null || cashDayClearTbFindDTO
                  .getCredenceDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_date >= ? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceDateSt().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceDateSt().trim() == null || cashDayClearTbFindDTO
                  .getCredenceDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_date <= ? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getSettDateSt().trim() != null
              && !cashDayClearTbFindDTO.getSettDateSt().trim().equals("")
              && cashDayClearTbFindDTO.getSettDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getSettDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.sett_date >= ? and fn201.sett_date <= ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettDateSt().trim());
            parameters.add(cashDayClearTbFindDTO.getSettDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getSettDateSt().trim() != null
              && !cashDayClearTbFindDTO.getSettDateSt().trim().equals("")
              && (cashDayClearTbFindDTO.getSettDateEnd().trim() == null || cashDayClearTbFindDTO
                  .getSettDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.sett_date >= ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettDateSt().trim());
          }

          if (cashDayClearTbFindDTO.getSettDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getSettDateEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getSettDateSt().trim() == null || cashDayClearTbFindDTO
                  .getSettDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.sett_date <= ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getSummary().trim() != null
              && !cashDayClearTbFindDTO.getSummary().trim().equals("")) {
            criterion += " fn201.summay=? and ";
            parameters.add(cashDayClearTbFindDTO.getSummary().trim());
          }

          if (cashDayClearTbFindDTO.getSubjectCode().trim() != null
              && !cashDayClearTbFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn201.subject_code like ? and ";
            parameters.add(cashDayClearTbFindDTO.getSubjectCode().trim() + "%");
          }

          if (cashDayClearTbFindDTO.getSubjectName().trim() != null
              && !cashDayClearTbFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(cashDayClearTbFindDTO.getSubjectName().trim() + "%");
          }

          if (cashDayClearTbFindDTO.getMoneySt().trim() != null
              && !cashDayClearTbFindDTO.getMoneySt().trim().equals("")
              && cashDayClearTbFindDTO.getMoneyEnd().trim() != null
              && !cashDayClearTbFindDTO.getMoneyEnd().trim().equals("")) {// 有开始金额结束金额
            criterion += " ((fn201.debit >=? and fn201.debit <=?) or (fn201.credit >=? and fn201.credit <=?)) and ";
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
          }

          if (cashDayClearTbFindDTO.getMoneySt().trim() != null
              && !cashDayClearTbFindDTO.getMoneySt().trim().equals("")
              && (cashDayClearTbFindDTO.getMoneyEnd().trim() == null || cashDayClearTbFindDTO
                  .getMoneyEnd().trim().equals(""))) {// 有开始金额无结束金额
            criterion += " (fn201.debit >= ? or fn201.credit >=?) and ";
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
          }

          if (cashDayClearTbFindDTO.getMoneyEnd().trim() != null
              && !cashDayClearTbFindDTO.getMoneyEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getMoneySt().trim() == null || cashDayClearTbFindDTO
                  .getMoneySt().trim().equals(""))) {// 无开始金额有结束金额
            criterion += " (fn201.debit <= ? or fn201.credit <=?) and ";
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
          }

          if (cashDayClearTbFindDTO.getCredenceNumSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumSt().trim().equals("")
              && cashDayClearTbFindDTO.getCredenceNumEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumEnd().trim().equals("")) {// 有开始凭证号结束凭证号
            criterion += " fn201.credence_num >=to_number(?) and fn201.credence_num <=to_number(?) and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceNumSt().trim());
            parameters.add(cashDayClearTbFindDTO.getCredenceNumEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceNumSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumSt().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceNumEnd().trim() == null || cashDayClearTbFindDTO
                  .getCredenceNumEnd().trim().equals(""))) {// 有开始凭证号无结束凭证号
            criterion += " fn201.credence_num >= to_number(?) and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceNumEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceNumEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceNumSt().trim() == null || cashDayClearTbFindDTO
                  .getCredenceNumSt().trim().equals(""))) {// 无开始凭证号有结束凭证号
            criterion += " fn201.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceNumEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceCharacter().trim() != null
              && !cashDayClearTbFindDTO.getCredenceCharacter().trim()
                  .equals("")) {
            criterion += " fn201.credence_character = ? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceCharacter().trim());
          }

          if (cashDayClearTbFindDTO.getSettType().trim() != null
              && !cashDayClearTbFindDTO.getSettType().trim().equals("")) {
            criterion += " fn201.sett_type = ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettType().trim());
          }

          List officeList1 = null;
          try {
            // 取出用户权限办事处
            List officeList = securityInfo.getOfficeList();
            officeList1 = new ArrayList();
            OfficeDto officedto = null;
            Iterator itr1 = officeList.iterator();
            while (itr1.hasNext()) {
              officedto = (OfficeDto) itr1.next();
              officeList1.add(officedto.getOfficeCode());
            }
          } catch (Exception e) {
            e.printStackTrace();
          }

          if (cashDayClearTbFindDTO.getOffice().trim() != null
              && !cashDayClearTbFindDTO.getOffice().trim().equals("")) {
            criterion += " fn201.office = ? and ";
            parameters.add(cashDayClearTbFindDTO.getOffice().trim());
          } else {
            if (officeList1 != null && officeList1.size() > 0) {
              criterion += " ( ";
              for (int i = 0; i < officeList1.size(); i++) {
                criterion += " fn201.office = ? or ";
                parameters.add(officeList1.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and ";
            }
          }

          if (cashDayClearTbFindDTO.getSettNum().trim() != null
              && !cashDayClearTbFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn201.sett_num = ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettNum().trim());
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy.trim();

          if (ob.equals("fn201.credence_character")) {
            ob = " fn201.credence_character " + order + " ,fn201.credence_num ";
          }

          if (ob == null)
            ob = " fn201.credence_id ";

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

          List queryList = query.list();

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
              CashDayClearTbShowListDTO cashDayClearTbShowListDTO = new CashDayClearTbShowListDTO();
              if (obj[0] != null) {
                cashDayClearTbShowListDTO.setCredenceDate(obj[0].toString());
              }
              if (obj[1] != null) {
                cashDayClearTbShowListDTO.setCredenceCharacter(obj[1]
                    .toString());
              }
              if (obj[2] != null) {
                cashDayClearTbShowListDTO.setCredenceNum(obj[2].toString());
              }
              if (obj[3] != null) {
                cashDayClearTbShowListDTO.setSummary(obj[3].toString());
              }
              if (obj[4] != null) {
                cashDayClearTbShowListDTO.setSubjectCode(obj[4].toString());
              }
              cashDayClearTbShowListDTO.setSubjectName(obj[5].toString());
              if (obj[6] != null) {
                cashDayClearTbShowListDTO.setDebit(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                cashDayClearTbShowListDTO.setCredit(new BigDecimal(obj[7]
                    .toString()));
              }
              if (obj[8] != null) {
                cashDayClearTbShowListDTO.setSettNum(obj[8].toString());
              }
              if (obj[9] != null) {
                cashDayClearTbShowListDTO.setSettDate(obj[9].toString());
              }
              cashDayClearTbShowListDTO.setCredenceId(obj[10].toString());
              if (obj[11] != null) {
                cashDayClearTbShowListDTO.setOffice(obj[11].toString());
              }
              temp_list.add(cashDayClearTbShowListDTO);
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
   * 现金日记账--自动转账页面
   * 
   * @author 郭婧平 2007-10-16 统计记录的条数，页面金额求和 查询条件：cashDayClearTbFindDTO
   */
  public List queryCashDayClearTbListCount(
      final CashDayClearTbFindDTO cashDayClearTbFindDTO,
      final String credenceType, final SecurityInfo securityInfo)
      throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn201.debit,fn201.credit,fn201.credence_id "
              + "from FN201 fn201,FN110 fn110,FN102 fn102 "
              + "where fn201.subject_code=fn110.subject_code "
              + "and fn201.book_id=fn110.book_id "
              + "and fn201.credence_st='2' "
              + "and fn201.summay=fn102.para_id "
              + "and fn201.book_id=fn102.book_id "
              + "and fn102.param_value not in('1','4','5') ";
          Vector parameters = new Vector();
          String criterion = "";

          criterion += "fn110.subject_property = ?  and ";
          parameters.add(credenceType);

          if (credenceType.equals("0")) {
            criterion += "fn201.cash_acc_st='0'  and ";
          }

          if (credenceType.equals("1")) {
            criterion += "fn201.bank_acc_st='0'  and ";
          }

          criterion += "fn201.book_id = ?  and ";
          parameters.add(securityInfo.getBookId());

          if (cashDayClearTbFindDTO.getCredenceDateSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateSt().trim().equals("")
              && cashDayClearTbFindDTO.getCredenceDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_date >=? and fn201.credence_date <=? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceDateSt().trim());
            parameters.add(cashDayClearTbFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceDateSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateSt().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceDateEnd().trim() == null || cashDayClearTbFindDTO
                  .getCredenceDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_date >= ? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceDateSt().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceDateEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceDateSt().trim() == null || cashDayClearTbFindDTO
                  .getCredenceDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_date <= ? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getSettDateSt().trim() != null
              && !cashDayClearTbFindDTO.getSettDateSt().trim().equals("")
              && cashDayClearTbFindDTO.getSettDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getSettDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.sett_date >= ? and fn201.sett_date <= ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettDateSt().trim());
            parameters.add(cashDayClearTbFindDTO.getSettDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getSettDateSt().trim() != null
              && !cashDayClearTbFindDTO.getSettDateSt().trim().equals("")
              && (cashDayClearTbFindDTO.getSettDateEnd().trim() == null || cashDayClearTbFindDTO
                  .getSettDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.sett_date >= ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettDateSt().trim());
          }

          if (cashDayClearTbFindDTO.getSettDateEnd().trim() != null
              && !cashDayClearTbFindDTO.getSettDateEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getSettDateSt().trim() == null || cashDayClearTbFindDTO
                  .getSettDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.sett_date <= ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettDateEnd().trim());
          }

          if (cashDayClearTbFindDTO.getSummary().trim() != null
              && !cashDayClearTbFindDTO.getSummary().trim().equals("")) {
            criterion += " fn201.summay=? and ";
            parameters.add(cashDayClearTbFindDTO.getSummary().trim());
          }

          if (cashDayClearTbFindDTO.getSubjectCode().trim() != null
              && !cashDayClearTbFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn201.subject_code like ? and ";
            parameters.add(cashDayClearTbFindDTO.getSubjectCode().trim() + "%");
          }

          if (cashDayClearTbFindDTO.getSubjectName().trim() != null
              && !cashDayClearTbFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(cashDayClearTbFindDTO.getSubjectName().trim() + "%");
          }

          if (cashDayClearTbFindDTO.getMoneySt().trim() != null
              && !cashDayClearTbFindDTO.getMoneySt().trim().equals("")
              && cashDayClearTbFindDTO.getMoneyEnd().trim() != null
              && !cashDayClearTbFindDTO.getMoneyEnd().trim().equals("")) {// 有开始金额结束金额
            criterion += " ((fn201.debit >=? and fn201.debit <=?) or (fn201.credit >=? and fn201.credit <=?)) and ";
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
          }

          if (cashDayClearTbFindDTO.getMoneySt().trim() != null
              && !cashDayClearTbFindDTO.getMoneySt().trim().equals("")
              && (cashDayClearTbFindDTO.getMoneyEnd().trim() == null || cashDayClearTbFindDTO
                  .getMoneyEnd().trim().equals(""))) {// 有开始金额无结束金额
            criterion += " (fn201.debit >= ? or fn201.credit >=?) and ";
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
            parameters.add(cashDayClearTbFindDTO.getMoneySt());
          }

          if (cashDayClearTbFindDTO.getMoneyEnd().trim() != null
              && !cashDayClearTbFindDTO.getMoneyEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getMoneySt().trim() == null || cashDayClearTbFindDTO
                  .getMoneySt().trim().equals(""))) {// 无开始金额有结束金额
            criterion += " (fn201.debit <= ? or fn201.credit <=?) and ";
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTbFindDTO.getMoneyEnd());
          }

          if (cashDayClearTbFindDTO.getCredenceNumSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumSt().trim().equals("")
              && cashDayClearTbFindDTO.getCredenceNumEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumEnd().trim().equals("")) {// 有开始凭证号结束凭证号
            criterion += " fn201.credence_num >=to_number(?) and fn201.credence_num <=to_number(?) and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceNumSt().trim());
            parameters.add(cashDayClearTbFindDTO.getCredenceNumEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceNumSt().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumSt().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceNumEnd().trim() == null || cashDayClearTbFindDTO
                  .getCredenceNumEnd().trim().equals(""))) {// 有开始凭证号无结束凭证号
            criterion += " fn201.credence_num >= to_number(?) and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceNumEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceNumEnd().trim() != null
              && !cashDayClearTbFindDTO.getCredenceNumEnd().trim().equals("")
              && (cashDayClearTbFindDTO.getCredenceNumSt().trim() == null || cashDayClearTbFindDTO
                  .getCredenceNumSt().trim().equals(""))) {// 无开始凭证号有结束凭证号
            criterion += " fn201.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceNumEnd().trim());
          }

          if (cashDayClearTbFindDTO.getCredenceCharacter().trim() != null
              && !cashDayClearTbFindDTO.getCredenceCharacter().trim()
                  .equals("")) {
            criterion += " fn201.credence_character = ? and ";
            parameters.add(cashDayClearTbFindDTO.getCredenceCharacter().trim());
          }

          if (cashDayClearTbFindDTO.getSettType().trim() != null
              && !cashDayClearTbFindDTO.getSettType().trim().equals("")) {
            criterion += " fn201.sett_type = ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettType().trim());
          }

          List officeList1 = null;
          try {
            // 取出用户权限办事处
            List officeList = securityInfo.getOfficeList();
            officeList1 = new ArrayList();
            OfficeDto officedto = null;
            Iterator itr1 = officeList.iterator();
            while (itr1.hasNext()) {
              officedto = (OfficeDto) itr1.next();
              officeList1.add(officedto.getOfficeCode());
            }
          } catch (Exception e) {
            e.printStackTrace();
          }

          if (cashDayClearTbFindDTO.getOffice().trim() != null
              && !cashDayClearTbFindDTO.getOffice().trim().equals("")) {
            criterion += " fn201.office = ? and ";
            parameters.add(cashDayClearTbFindDTO.getOffice().trim());
          } else {
            if (officeList1 != null && officeList1.size() > 0) {
              criterion += " ( ";
              for (int i = 0; i < officeList1.size(); i++) {
                criterion += " fn201.office = ? or ";
                parameters.add(officeList1.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and ";
            }
          }

          if (cashDayClearTbFindDTO.getSettNum().trim() != null
              && !cashDayClearTbFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn201.sett_num = ? and ";
            parameters.add(cashDayClearTbFindDTO.getSettNum().trim());
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
              CashDayClearTbShowListDTO cashDayClearTbShowListDTO = new CashDayClearTbShowListDTO();
              if (obj[0] != null) {
                cashDayClearTbShowListDTO.setDebit(new BigDecimal(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                cashDayClearTbShowListDTO.setCredit(new BigDecimal(obj[1]
                    .toString()));
              }
              cashDayClearTbShowListDTO.setCredenceId(obj[2].toString());
              temp_list.add(cashDayClearTbShowListDTO);
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
   * 现金日记账--自动转账页面
   * 
   * @author 郭婧平 2007-10-17 根据条件查询表fn201是否有符合要求的记录
   *         查询条件：credence_id,CASH_ACC_ST=0
   */
  public List queryAccountantCredenceByCredenceId(final List credenceIdList) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select accountantCredence.credenceId from AccountantCredence accountantCredence "
            + "where accountantCredence.cashAccSt>'0' ";
        Vector parameters = new Vector();
        String criterion = "";
        if (credenceIdList != null && credenceIdList.size() > 0) {
          criterion += "and ( ";
          for (int i = 0; i < credenceIdList.size(); i++) {
            criterion += " accountantCredence.credenceId = ? or ";
            parameters.add(new Integer(credenceIdList.get(i).toString()));
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") ";
        }
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
   * 张列 获得科目代码和科目名称
   * 
   * @param bookId
   * @return lsit
   */
  public List getDatainitalizeInfo(final String bookId) {
    Validate.notNull(bookId);
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          //  
          // String hql = "select f.subject_code,f.subject_name from fn110 f " +
          // " where f.parent_subject_code is null and f.book_id=? order by
          // f.subject_code asc";
          //            
          String hql = "select f110.subject_code, f110.subject_name from fn110 f110"
              + " where f110.subject_code in (select f.subject_code from fn110 f where f.book_id=? "
              + " minus"
              + " select f.parent_subject_code from fn110 f where f.book_id=? ) and f110.book_id=? order by f110.subject_code asc";
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
            DatainitializeDTO datainitializeDTO = new DatainitializeDTO();
            datainitializeDTO.setSubjectCode(obj[0].toString());
            datainitializeDTO.setSubjectName(obj[1].toString());
            li.add(datainitializeDTO);
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
  public DatainitializeDTO getLendsMoney(final String subjectCode,
      final String officeName, final String bookId) {
    Validate.notNull(subjectCode);
    Validate.notNull(officeName);
    Validate.notNull(bookId);
    DatainitializeDTO datainitializeDTO = null;
    try {
      datainitializeDTO = (DatainitializeDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              DatainitializeDTO datainitializeDTO = new DatainitializeDTO();
              String hql1 = "select f.credence_id from fn201 f where f.subject_code=? and f.book_id=?";
              Query query1 = session.createSQLQuery(hql1);
              query1.setParameter(0, subjectCode);
              query1.setParameter(1, bookId);
              if (query1.list().size() == 0) {
                datainitializeDTO.setDebit("0");
                datainitializeDTO.setCredit("0");
                datainitializeDTO.setBalaceDirection("2");
              } else {
                String hql3 = "select nvl(f.debit,0), nvl(f.credit,0) from fn201 f "
                    + "where f.subject_code = ? and f.summay = "
                    + "(select ff.para_id from fn102 ff "
                    + "where ff.book_id = ? "
                    + "and ff.param_num = '4' "
                    + "and ff.param_value = '5') and f.office = ? and f.book_id = ?";
                Query query3 = session.createSQLQuery(hql3);
                query3.setParameter(0, subjectCode);
                query3.setParameter(1, bookId);
                query3.setParameter(2, officeName);
                query3.setParameter(3, bookId);
                if (query3.list().size() == 0) {
                  datainitializeDTO.setYesterdayDebit("0");
                  datainitializeDTO.setYesterdayCredit("0");
                } else {
                  Object[] obj3 = null;
                  Iterator it3 = query3.list().iterator();
                  if (it3.hasNext()) {
                    obj3 = (Object[]) it3.next();
                    datainitializeDTO.setYesterdayDebit(obj3[0].toString());
                    datainitializeDTO.setYesterdayCredit(obj3[1].toString());
                  }
                }

                String hql = "select nvl(f.debit,0), nvl(f.credit,0) from fn201 f "
                    + "where f.subject_code = ? and f.summay = "
                    + "(select ff.para_id from fn102 ff "
                    + "where ff.book_id = ? "
                    + "and ff.param_num = '4' "
                    + "and ff.param_value = '1') and f.office = ? and f.book_id = ?";
                Query query = session.createSQLQuery(hql);
                query.setParameter(0, subjectCode);
                query.setParameter(1, bookId);
                query.setParameter(2, officeName);
                query.setParameter(3, bookId);
                if (query.list().size() == 0) {
                  datainitializeDTO.setDebit("0");
                  datainitializeDTO.setCredit("0");
                  datainitializeDTO.setBalaceDirection("2");
                } else {
                  Object[] obj = null;
                  Iterator it = query.list().iterator();
                  if (it.hasNext()) {
                    obj = (Object[]) it.next();
                    datainitializeDTO.setDebit(obj[0].toString());
                    datainitializeDTO.setCredit(obj[1].toString());
                  }
                }
                // 期初
                String hql11 = "select nvl(sum(f.debit),0), nvl(sum(f.credit),0) from fn201 f "
                    + "where f.subject_code = ? and (f.summay = "
                    + "(select ff.para_id from fn102 ff where ff.book_id = ? and ff.param_num = '4' "
                    + "and ff.param_value = '1') or (f.summay = "
                    + "(select ff.para_id from fn102 ff where ff.book_id=? and ff.param_num='4' "
                    + "and ff.param_value='4')) or (f.summay = "
                    + "(select ff.para_id from fn102 ff where ff.book_id=? and ff.param_num='4' "
                    + "and ff.param_value='5'))) "
                    + "and f.office = ? and f.book_id = ?";
                Query query11 = session.createSQLQuery(hql11);
                query11.setParameter(0, subjectCode);
                query11.setParameter(1, bookId);
                query11.setParameter(2, bookId);
                query11.setParameter(3, bookId);
                query11.setParameter(4, officeName);
                query11.setParameter(5, bookId);
                if (query11.list().size() == 0) {
                  datainitializeDTO.setBalaceDirection("2");
                } else {
                  Object[] obj1 = null;
                  Iterator it1 = query11.list().iterator();
                  if (it1.hasNext()) {
                    obj1 = (Object[]) it1.next();
                    BigDecimal temp_debit = new BigDecimal(obj1[0].toString());
                    BigDecimal temp_credit = new BigDecimal(obj1[1].toString());
                    if (temp_debit.compareTo(temp_credit) > 0) {
                      datainitializeDTO.setBalaceDirection("0");
                      String temp_yesterdayRemainingSum = temp_debit.subtract(
                          temp_credit).toString();
                      datainitializeDTO
                          .setYesterdayRemainingSum(temp_yesterdayRemainingSum);
                    } else if (temp_debit.compareTo(temp_credit) < 0) {
                      datainitializeDTO.setBalaceDirection("1");
                      String temp_yesterdayRemainingSum = temp_credit.subtract(
                          temp_debit).toString();
                      datainitializeDTO
                          .setYesterdayRemainingSum(temp_yesterdayRemainingSum);
                    } else {
                      datainitializeDTO.setBalaceDirection("2");
                      datainitializeDTO.setYesterdayRemainingSum("0");
                    }
                  }
                }
              }
              return datainitializeDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return datainitializeDTO;
  }

  /**
   * 张列 余额方向，0.借，1贷，2.平
   * 
   * @param subjectCode
   * @param bookId
   * @return String
   */
  public String getBalanceDirection(final String subjectCode,
      final String bookId) {
    Validate.notNull(subjectCode);
    String stutas = "";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f1.balance_direction from fn110 f1 "
              + " where f1.subject_code=? and f1.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, subjectCode);
          query.setParameter(1, bookId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  /**
   * 张列 账套启用状态
   * 
   * @param bookId
   * @return String
   */
  public String getBookST(final String bookId) {
    Validate.notNull(bookId);
    String stutas = "";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f11.book_st from fn101 f11 where f11.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  /**
   * 判断FN201里是否存在SUMMAY=1,4,5 and OFFICE=所选办事处的记录 张列
   * 
   * @param code
   * @param securityInfo
   * @return
   */
  public List is_SummayOffice(final String bookId, final String officeName) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select f2.credence_id from fn201 f2 where "
            + "f2.summay in (select ff.para_id from fn102 ff where "
            + "ff.book_id = ? and ( ( ff.param_num = '4' and ff.param_value = '1')"
            + " or ( ff.param_num = '4' and ff.param_value = '4') "
            + " or ( ff.param_num = '4' and ff.param_value = '5') ) ) "
            + "and f2.office = ? and f2.book_id = ?";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, bookId);
        query.setParameter(1, officeName);
        query.setParameter(2, bookId);
        return query.list();
      }
    });
    return list;
  }

  /**
   * 删除FN201 FN201.SUMMAY=1 and FN201.OFFICE=所选办事处的记录 张列
   */
  public void delete_ZL(final String bookId, final String officeName) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from AccountantCredence ac where "
              + "ac.summay = (select bp.paraId from BookParameter bp where "
              + "bp.bookId = ? and bp.paramNum = '4' and bp.paramValue = '1') "
              + "and ac.office = ? and ac.bookId = ?";
          Query query = session.createQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, officeName);
          query.setParameter(2, bookId);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除FN201 FN201.SUMMAY=1 and FN201.OFFICE=所选办事处的记录 张列
   */
  public void delete_ZL1(final String bookId, final String officeName) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from AccountantCredence ac where "
              + "ac.summay = (select bp.paraId from BookParameter bp where "
              + "bp.bookId = ? and bp.paramNum = '4' and bp.paramValue = '4') "
              + "and ac.office = ? and ac.bookId = ?";
          Query query = session.createQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, officeName);
          query.setParameter(2, bookId);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除FN201 FN201.SUMMAY=1 and FN201.OFFICE=所选办事处的记录 张列
   */
  public void delete_ZL2(final String bookId, final String officeName) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from AccountantCredence ac where "
              + "ac.summay = (select bp.paraId from BookParameter bp where "
              + "bp.bookId = ? and bp.paramNum = '4' and bp.paramValue = '5') "
              + "and ac.office = ? and ac.bookId = ?";
          Query query = session.createQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, officeName);
          query.setParameter(2, bookId);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询自动转帐列表的方法
   * 
   * @param dto 封装了列表的查询条件
   * @param officeList
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @author 付云峰
   */
  public List queryAutoTransferInfoList(final CredenceFillinTbFindDTO dto,
      final List officeList, final String orderBy, final String order,
      final int start, final int pageSize, final String bookId, final int page) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String office = "";
        String bizSt = "";
        String aa101St = "";
        String pl202St = "";
        for (int i = 0; i < officeList.size(); i++) {
          String temp_office = (String) officeList.get(i);
          office = office + "'" + temp_office + "'" + ",";
        }
        office = office.substring(0, office.lastIndexOf(","));

        bizSt = dto.getBizSt();
        if (bizSt.equals("A")) {
          aa101St = "'5'";
          pl202St = "'6'";
        } else if (bizSt.equals("")) {
          aa101St = "'5'";
          pl202St = "'6'";
        } else if (bizSt.equals("0")) {
          aa101St = "'3'";
          pl202St = "'4'";
        } else if (bizSt.equals("1")) {
          aa101St = "'4'";
          pl202St = "'5'";
        } else if (bizSt.equals("2")) {
          aa101St = "'5'";
          pl202St = "'6'";
        }
        String sql = "select notenum, settdate, summoney, fn120type,bankid from ("
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit - a101.debit + a101.interest) as summoney,"
            + "'1' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and (a101.biz_type in ('A', 'B', 'M') or a101.specailtype = 1 "
            + "or (a101.biz_type = 'C' and a101.reservea_b=1))"
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit) as summoney,"
            + "'2' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=3 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='D' "
            + "and a101.specailtype is null "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'3' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='D' "
            + "and a101.specailtype is null "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'4' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='E' "
            + "and a101.interest>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'5' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='F' "
            // + "and a101.reservea_b='1' "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'6' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='E' "
            + "and a101.interest=0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'7' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='F' "
            + "and (a101.reservea_b is null or a101.reservea_b ='') "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.debit) as summoney,"
            + "'8' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101, aa001 a001, ba001 b001"
            + " where a101.org_id = a001.id"
            + " and a001.orginfo_id = b001.id"
            + " and a101.is_finance = 1"
            + " and a101.biz_status in ("
            + aa101St
            + ")"
            + " and a101.biz_type = 'E'"
            + " and a101.interest = 0"
            + " and b001.officecode in ("
            + office
            + ")"
            + " and a101.note_num in (select a.note_num"
            + " from aa101 a"
            + " where a.biz_type = 'F'"
            + " and a.note_num = a101.note_num"
            + " and a.reservea_b is null"
            + " or a.reservea_b = '')"
            + "group by a101.note_num,a101.moneybank"
            // + "select a101.note_num as notenum,"
            // + "max(a101.sett_date) as settdate,"
            // + "sum(a101.debit) as summoney,"
            // + "'8' as fn120type,"
            // + " from aa101 a101,aa001 a001,ba001 b001 "
            // + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            // + "and a101.is_finance=1 "
            // + "and a101.biz_status in ("
            // + aa101St
            // + ") "
            // + "and ((a101.biz_type = 'E' and a101.interest=0 "
            // + "and b001.officecode in ("
            // + office
            // + ")) "
            // + "and (a101.biz_type = 'F' and (a101.reservea_b is null or
            // a101.reservea_b =''))) "
            // + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'9' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='C' "
            + "and a101.reservea_b is null "
            + "and a101.credit>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'10' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='C' "
            + "and a101.debit>0 "
            + "and a101.reservea_b is null "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'11' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='H' "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'12' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type in ('K','L','G') "
            + "and a101.credit>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
            + "'13' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type in ('K','L','G') "
            + "and a101.debit>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select p202.batch_num as notenum,"
            + "max(p202.biz_date) as settdate,"
            + "sum(abs(p202.real_corpus)+abs(p202.real_interest)+abs(p202.real_overdue_corpus)+abs(p202.real_overdue_interest)+abs(p202.real_punish_interest)) as summoney,"
            + "'23' as fn120type,"
            + "to_char(p202.loan_bank_id) as bankid"
            + " from pl202 p202, pl002 p002 "
            + "where p202.loan_bank_id = p002.loan_bank_id "
            + "and p202.is_finance=1 "
            + "and p202.biz_st in ("
            + pl202St
            + ") "
            + "and p202.biz_type = '5' "
            + "and p202.batch_num is not null "
            + "and p002.office in ("
            + office
            + ") "
            + "group by p202.batch_num,p202.loan_bank_id"
            + " union all "
            + "select p202.note_num as notenum,"
            + "max(p202.biz_date) as settdate,"
            + "sum(abs(p202.occur_money)) as summoney,"
            + "'21' as fn120type,"
            + "to_char(p202.loan_bank_id) as bankid"
            + " from pl202 p202, pl002 p002 "
            + "where p202.loan_bank_id = p002.loan_bank_id "
            + "and p202.is_finance=1 "
            + "and p202.biz_st in ("
            + pl202St
            + ") "
            + "and p202.biz_type = '1' "
            + "and p002.office in ("
            + office
            + ") "
            + "group by p202.note_num,p202.loan_bank_id"
            + " union all "
            + "select p202.note_num as notenum,"
            + "max(p202.biz_date) as settdate,"
            + "sum(abs(p202.real_corpus)+abs(p202.real_interest)+abs(p202.real_overdue_corpus)+abs(p202.real_overdue_interest)+abs(p202.real_punish_interest)) as summoney,"
            + "'22' as fn120type,"
            + "to_char(p202.loan_bank_id) as bankid"
            + " from pl202 p202, pl002 p002 "
            + "where p202.loan_bank_id = p002.loan_bank_id "
            + "and p202.is_finance=1 "
            + "and p202.biz_st in ("
            + pl202St
            + ") "
            + "and p202.biz_type in ('2','3','4','5') "
            + "and p202.batch_num is null "
            + "and p002.office in ("
            + office
            + ") "
            + "group by p202.note_num,p202.loan_bank_id) res ";

        Vector parameters = new Vector();
        String criterion = "";

        criterion += " res.fn120type in (select f120.biz_type from  fn120 f120 where f120.book_id="
            + bookId + ") and";

        String bizType = dto.getBizType().trim();
        if (bizType != null && !bizType.equals("")) {
          criterion += " res.fn120type = ? and ";
          parameters.add(bizType);
        }

        String settNum = dto.getSettNum().trim();
        if (settNum != null && !settNum.equals("")) {
          criterion += " res.notenum = ? and ";
          parameters.add(settNum);
        }

        String settDateStart = dto.getSettDateStart().trim();
        String settDateEnd = dto.getSettDateEnd().trim();
        if (settDateStart != null && !settDateStart.equals("")
            && settDateEnd != null && !settDateEnd.equals("")) {
          criterion += " (res.settdate between ? and ?) and ";
          parameters.add(settDateStart);
          parameters.add(settDateEnd);
        } else if (settDateStart != null && !settDateStart.equals("")
            && (settDateEnd == null || settDateEnd.equals(""))) {
          criterion += " (res.settdate)>=? and ";
          parameters.add(settDateStart);
        } else if (settDateEnd != null && !settDateEnd.equals("")
            && (settDateStart == null || settDateStart.equals(""))) {
          criterion += " (res.settdate)<=? and ";
          parameters.add(settDateEnd);
        }
        String bankId = dto.getBankId();
        if (bankId != null && !"".equals(bankId)) {
          criterion += " (res.bankid) = ? and ";
          parameters.add(bankId.trim());
        }
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = " res.notenum ";
        String od = order;
        if (od == null)
          od = "asc";

        sql = sql + criterion + "order by " + ob + " " + od;
        Query query = session.createSQLQuery(sql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        List queryList = query.list();

        Iterator it = queryList.iterator();
        CredenceFillinTbListDTO credenceFillinTbListDTO = null;
        List temp_credenceFillinTbListDTOList = new ArrayList();
        Object[] obj = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          credenceFillinTbListDTO = new CredenceFillinTbListDTO();
          if (obj[0] != null) {
            credenceFillinTbListDTO.setSettNum(obj[0].toString());
          }
          if (obj[1] != null) {
            credenceFillinTbListDTO.setSettDate(obj[1].toString());
          }
          if (obj[2] != null) {
            credenceFillinTbListDTO.setSumOccurMoney(new BigDecimal(obj[2]
                .toString()));
          }
          if (obj[3] != null) {
            credenceFillinTbListDTO.setNumBizType(obj[3].toString());
          }

          temp_credenceFillinTbListDTOList.add(credenceFillinTbListDTO);
        }

        return temp_credenceFillinTbListDTOList;
      }
    });
    return list;
  }
  /**
   * 查询自动转帐列表的方法
   * 
   * @param dto 封装了列表的查询条件
   * @param officeList
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @author 付云峰
   */
  public List queryAutoTransferInfoLista(final CredenceFillinTbFindDTO dto,
      final List officeList, final String orderBy, final String order,
      final int start, final int pageSize, final String bookId, final int page) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
      SQLException {
        String office = "";
        String bizSt = "";
        String aa101St = "";
        String pl202St = "";
        for (int i = 0; i < officeList.size(); i++) {
          String temp_office = (String) officeList.get(i);
          office = office + "'" + temp_office + "'" + ",";
        }
        office = office.substring(0, office.lastIndexOf(","));
        
        bizSt = dto.getBizSt();
        if (bizSt.equals("A")) {
          aa101St = "'5'";
          pl202St = "'6'";
        } else if (bizSt.equals("")) {
          aa101St = "'5'";
          pl202St = "'6'";
        } else if (bizSt.equals("0")) {
          aa101St = "'3'";
          pl202St = "'4'";
        } else if (bizSt.equals("1")) {
          aa101St = "'4'";
          pl202St = "'5'";
        } else if (bizSt.equals("2")) {
          aa101St = "'5'";
          pl202St = "'6'";
        }
        String sql = "select notenum, settdate, summoney, fn120type,bankid from ("
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit - a101.debit + a101.interest) as summoney,"
          + "'1' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and (a101.biz_type in ('A', 'B', 'M') or a101.specailtype = 1 "
          + "or (a101.biz_type = 'C' and a101.reservea_b=1))"
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit) as summoney,"
          + "'2' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=3 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='D' "
          + "and a101.specailtype is null "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'3' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='D' "
          + "and a101.specailtype is null "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'4' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='E' "
          + "and a101.interest>0 "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'5' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='F' "
          // + "and a101.reservea_b='1' "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'6' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='E' "
          + "and a101.interest=0 "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'7' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='F' "
          + "and (a101.reservea_b is null or a101.reservea_b ='') "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.debit) as summoney,"
          + "'8' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101, aa001 a001, ba001 b001"
          + " where a101.org_id = a001.id"
          + " and a001.orginfo_id = b001.id"
          + " and a101.is_finance = 1"
          + " and a101.biz_status in ("
          + aa101St
          + ")"
          + " and a101.biz_type = 'E'"
          + " and a101.interest = 0"
          + " and b001.officecode in ("
          + office
          + ")"
          + " and a101.note_num in (select a.note_num"
          + " from aa101 a"
          + " where a.biz_type = 'F'"
          + " and a.note_num = a101.note_num"
          + " and a.reservea_b is null"
          + " or a.reservea_b = '')"
          + "group by a101.note_num,a101.moneybank"
          // + "select a101.note_num as notenum,"
          // + "max(a101.sett_date) as settdate,"
          // + "sum(a101.debit) as summoney,"
          // + "'8' as fn120type,"
          // + " from aa101 a101,aa001 a001,ba001 b001 "
          // + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          // + "and a101.is_finance=1 "
          // + "and a101.biz_status in ("
          // + aa101St
          // + ") "
          // + "and ((a101.biz_type = 'E' and a101.interest=0 "
          // + "and b001.officecode in ("
          // + office
          // + ")) "
          // + "and (a101.biz_type = 'F' and (a101.reservea_b is null or
          // a101.reservea_b =''))) "
          // + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'9' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='C' "
          + "and a101.reservea_b is null "
          + "and a101.credit>0 "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'10' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='C' "
          + "and a101.debit>0 "
          + "and a101.reservea_b is null "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'11' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type='H' "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'12' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type in ('K','L','G') "
          + "and a101.credit>0 "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select a101.note_num as notenum,"
          + "max(a101.sett_date) as settdate,"
          + "sum(a101.credit + a101.debit + a101.interest) as summoney,"
          + "'13' as fn120type,"
          + "a101.moneybank as bankid"
          + " from aa101 a101,aa001 a001,ba001 b001 "
          + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
          + "and a101.is_finance=1 "
          + "and a101.biz_status in ("
          + aa101St
          + ") "
          + "and a101.biz_type in ('K','L','G') "
          + "and a101.debit>0 "
          + "and b001.officecode in ("
          + office
          + ") "
          + "group by a101.note_num,a101.moneybank"
          + " union all "
          + "select p202.batch_num as notenum,"
          + "max(p202.biz_date) as settdate,"
          + "sum(abs(p202.real_corpus)+abs(p202.real_interest)+abs(p202.real_overdue_corpus)+abs(p202.real_overdue_interest)+abs(p202.real_punish_interest)) as summoney,"
          + "'23' as fn120type,"
          + "to_char(p202.loan_bank_id) as bankid"
          + " from pl202 p202, pl002 p002 "
          + "where p202.loan_bank_id = p002.loan_bank_id "
          + "and p202.is_finance=1 "
          + "and p202.biz_st in ("
          + pl202St
          + ") "
          + "and p202.biz_type = '5' "
          + "and p202.batch_num is not null "
          + "and p002.office in ("
          + office
          + ") "
          + "group by p202.batch_num,p202.loan_bank_id"
          + " union all "
          + "select p202.note_num as notenum,"
          + "max(p202.biz_date) as settdate,"
          + "sum(abs(p202.occur_money)) as summoney,"
          + "'21' as fn120type,"
          + "to_char(p202.loan_bank_id) as bankid"
          + " from pl202 p202, pl002 p002 "
          + "where p202.loan_bank_id = p002.loan_bank_id "
          + "and p202.is_finance=1 "
          + "and p202.biz_st in ("
          + pl202St
          + ") "
          + "and p202.biz_type = '1' "
          + "and p002.office in ("
          + office
          + ") "
          + "group by p202.note_num,p202.loan_bank_id"
          + " union all "
          + "select p202.note_num as notenum,"
          + "max(p202.biz_date) as settdate,"
          + "sum(abs(p202.real_corpus)+abs(p202.real_interest)+abs(p202.real_overdue_corpus)+abs(p202.real_overdue_interest)+abs(p202.real_punish_interest)) as summoney,"
          + "'22' as fn120type,"
          + "to_char(p202.loan_bank_id) as bankid"
          + " from pl202 p202, pl002 p002 "
          + "where p202.loan_bank_id = p002.loan_bank_id "
          + "and p202.is_finance=1 "
          + "and p202.biz_st in ("
          + pl202St
          + ") "
          + "and p202.biz_type in ('2','3','4','5') "
          + "and p202.batch_num is null "
          + "and p002.office in ("
          + office
          + ") "
          + "group by p202.note_num,p202.loan_bank_id) res ";
        
        Vector parameters = new Vector();
        String criterion = "";
        
        criterion += " res.fn120type in (select f120.biz_type from  fn120 f120 where f120.book_id="
          + bookId + ") and";
        
        String bizType = dto.getBizType().trim();
        if (bizType != null && !bizType.equals("")) {
          criterion += " res.fn120type = ? and ";
          parameters.add(bizType);
        }
        
        String settNum = dto.getSettNum().trim();
        if (settNum != null && !settNum.equals("")) {
          criterion += " res.notenum = ? and ";
          parameters.add(settNum);
        }
        
        String settDateStart = dto.getSettDateStart().trim();
        String settDateEnd = dto.getSettDateEnd().trim();
        if (settDateStart != null && !settDateStart.equals("")
            && settDateEnd != null && !settDateEnd.equals("")) {
          criterion += " (res.settdate between ? and ?) and ";
          parameters.add(settDateStart);
          parameters.add(settDateEnd);
        } else if (settDateStart != null && !settDateStart.equals("")
            && (settDateEnd == null || settDateEnd.equals(""))) {
          criterion += " (res.settdate)>=? and ";
          parameters.add(settDateStart);
        } else if (settDateEnd != null && !settDateEnd.equals("")
            && (settDateStart == null || settDateStart.equals(""))) {
          criterion += " (res.settdate)<=? and ";
          parameters.add(settDateEnd);
        }
        String bankId = dto.getBankId();
        if (bankId != null && !"".equals(bankId)) {
          criterion += " (res.bankid) = ? and ";
          parameters.add(bankId.trim());
        }
        if (criterion.length() != 0)
          criterion = "where "
            + criterion.substring(0, criterion.lastIndexOf("and"));
        
        String ob = orderBy;
        if (ob == null)
          ob = " res.notenum ";
        String od = order;
        if (od == null)
          od = "asc";
        
        sql = sql + criterion + "order by " + ob + " " + od;
        Query query = session.createSQLQuery(sql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        List queryList = query.list();
        Iterator it = queryList.iterator();
        CredenceFillinTbListDTO credenceFillinTbListDTO = null;
        List temp_credenceFillinTbListDTOList = new ArrayList();
        Object[] obj = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          credenceFillinTbListDTO = new CredenceFillinTbListDTO();
          if (obj[0] != null) {
            credenceFillinTbListDTO.setSettNum(obj[0].toString());
          }
          if (obj[1] != null) {
            credenceFillinTbListDTO.setSettDate(obj[1].toString());
          }
          if (obj[2] != null) {
            credenceFillinTbListDTO.setSumOccurMoney(new BigDecimal(obj[2]
                                                                        .toString()));
          }
          if (obj[3] != null) {
            credenceFillinTbListDTO.setNumBizType(obj[3].toString());
          }
          
          temp_credenceFillinTbListDTOList.add(credenceFillinTbListDTO);
        }
        
        return temp_credenceFillinTbListDTOList;
      }
    });
    return list;
  }

  /**
   * 凭证录入-自动转账列表count的查询方法
   * 
   * @param dto 查询条件
   * @param officeList 办事处权限
   * @return
   * @author 付云峰
   */
  public List queryAutoTransferInfoCount(final CredenceFillinTbFindDTO dto,
      final List officeList, final String bookId) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String office = "";
        String bizSt = "";
        String aa101St = "";
        String pl202St = "";
        for (int i = 0; i < officeList.size(); i++) {
          String temp_office = (String) officeList.get(i);
          office = office + "'" + temp_office + "'" + ",";
        }
        office = office.substring(0, office.lastIndexOf(","));

        bizSt = dto.getBizSt();
        if (bizSt.equals("A")) {
          aa101St = "'5'";
          pl202St = "'6'";
        } else if (bizSt.equals("")) {
          aa101St = "'5'";
          pl202St = "'6'";
        } else if (bizSt.equals("0")) {
          aa101St = "'3'";
          pl202St = "'4'";
        } else if (bizSt.equals("1")) {
          aa101St = "'4'";
          pl202St = "'5'";
        } else if (bizSt.equals("2")) {
          aa101St = "'5'";
          pl202St = "'6'";
        }
        String sql = "select notenum, settdate, fn120type,bankid from ("
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'1' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and (a101.biz_type in ('A', 'B', 'M') or a101.specailtype = 1 "
            + "or (a101.biz_type = 'C' and a101.reservea_b = 1))"
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'2' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=3 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='D' "
            + "and a101.specailtype is null "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'3' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='D' "
            + "and a101.specailtype is null "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'4' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='E' "
            + "and a101.interest>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'5' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='F' "
            // + "and a101.reservea_b='1' "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'6' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='E' "
            + "and a101.interest=0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'7' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='F' "
            + "and (a101.reservea_b is null or a101.reservea_b ='') "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'8' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101, aa001 a001, ba001 b001"
            + " where a101.org_id = a001.id"
            + " and a001.orginfo_id = b001.id"
            + " and a101.is_finance = 1"
            + " and a101.biz_status in ("
            + aa101St
            + ")"
            + " and a101.biz_type = 'E'"
            + " and a101.interest = 0"
            + " and b001.officecode in ("
            + office
            + ")"
            + " and a101.note_num in (select a.note_num"
            + " from aa101 a"
            + " where a.biz_type = 'F'"
            + " and a.note_num = a101.note_num"
            + " and a.reservea_b is null"
            + " or a.reservea_b = '')"
            + "group by a101.note_num,a101.moneybank"
            // + "select a101.note_num as notenum,"
            // + "max(a101.sett_date) as settdate,"
            // + "'8' as fn120type,"
            // + " from aa101 a101,aa001 a001,ba001 b001 "
            // + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            // + "and a101.is_finance=1 "
            // + "and a101.biz_status in ("
            // + aa101St
            // + ") "
            // + "and ((a101.biz_type = 'E' and a101.interest=0 "
            // + "and b001.officecode in ("
            // + office
            // + ")) "
            // + "and (a101.biz_type = 'F' and (a101.reservea_b is null or
            // a101.reservea_b =''))) "
            // + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'9' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='C' "
            + "and a101.reservea_b is null "
            + "and a101.credit>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'10' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='C' "
            + "and a101.reservea_b is null "
            + "and a101.debit>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'11' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type='H' "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'12' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type in ('K','L','G') "
            + "and a101.credit>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select a101.note_num as notenum,"
            + "max(a101.sett_date) as settdate,"
            + "'13' as fn120type,"
            + "a101.moneybank as bankid"
            + " from aa101 a101,aa001 a001,ba001 b001 "
            + "where a101.org_id=a001.id and a001.orginfo_id=b001.id "
            + "and a101.is_finance=1 "
            + "and a101.biz_status in ("
            + aa101St
            + ") "
            + "and a101.biz_type in ('K','L','G') "
            + "and a101.debit>0 "
            + "and b001.officecode in ("
            + office
            + ") "
            + "group by a101.note_num,a101.moneybank"
            + " union all "
            + "select p202.batch_num as notenum,"
            + "max(p202.biz_date) as settdate,"
            + "'23' as fn120type,"
            + "to_char(p202.loan_bank_id) as bankid"
            + " from pl202 p202, pl002 p002 "
            + "where p202.loan_bank_id = p002.loan_bank_id "
            + "and p202.is_finance=1 "
            + "and p202.biz_st in ("
            + pl202St
            + ") "
            + "and p202.biz_type = '5' "
            + "and p202.batch_num is not null "
            + "and p002.office in ("
            + office
            + ") "
            + "group by p202.batch_num,p202.loan_bank_id"
            + " union all "
            + "select p202.note_num as notenum,"
            + "max(p202.biz_date) as settdate,"
            + "'21' as fn120type,"
            + "to_char(p202.loan_bank_id) as bankid"
            + " from pl202 p202, pl002 p002 "
            + "where p202.loan_bank_id = p002.loan_bank_id "
            + "and p202.is_finance=1 "
            + "and p202.biz_st in ("
            + pl202St
            + ") "
            + "and p202.biz_type = '1' "
            + "and p002.office in ("
            + office
            + ") "
            + "group by p202.note_num,p202.loan_bank_id"
            + " union all "
            + "select p202.note_num as notenum,"
            + "max(p202.biz_date) as settdate,"
            + "'22' as fn120type,"
            + "to_char(p202.loan_bank_id) as bankid"
            + " from pl202 p202, pl002 p002 "
            + "where p202.loan_bank_id = p002.loan_bank_id "
            + "and p202.is_finance=1 "
            + "and p202.batch_num is null "
            + "and p202.biz_st in ("
            + pl202St
            + ") "
            + "and p202.biz_type in ('2','3','4','5') "
            + "and p002.office in ("
            + office
            + ") "
            + "group by p202.note_num,p202.loan_bank_id) res ";

        Vector parameters = new Vector();
        String criterion = "";

        criterion += " res.fn120type in (select f120.biz_type from  fn120 f120 where f120.book_id="
            + bookId + ") and";

        String bizType = dto.getBizType().trim();
        if (bizType != null && !bizType.equals("")) {
          criterion += " res.fn120type = ? and ";
          parameters.add(bizType);
        }

        String settNum = dto.getSettNum().trim();
        if (settNum != null && !settNum.equals("")) {
          criterion += " res.notenum = ? and ";
          parameters.add(settNum);
        }

        String settDateStart = dto.getSettDateStart().trim();
        String settDateEnd = dto.getSettDateEnd().trim();
        if (settDateStart != null && !settDateStart.equals("")
            && settDateEnd != null && !settDateEnd.equals("")) {
          criterion += " (res.settdate between ? and ?) and ";
          parameters.add(settDateStart);
          parameters.add(settDateEnd);
        } else if (settDateStart != null && !settDateStart.equals("")
            && (settDateEnd == null || settDateEnd.equals(""))) {
          criterion += " (res.settdate)>=? and ";
          parameters.add(settDateStart);
        } else if (settDateEnd != null && !settDateEnd.equals("")
            && (settDateStart == null || settDateStart.equals(""))) {
          criterion += " (res.settdate)<=? and ";
          parameters.add(settDateEnd);
        }
        String bankId = dto.getBankId();
        if (bankId != null && !"".equals(bankId)) {
          criterion += " (res.bankid) = ? and ";
          parameters.add(bankId.trim());
        }
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        sql = sql + criterion;
        Query query = session.createSQLQuery(sql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Iterator it = query.list().iterator();
        CredenceFillinTbListDTO credenceFillinTbListDTO = null;
        List temp_credenceFillinTbListDTOList = new ArrayList();
        Object[] obj = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          credenceFillinTbListDTO = new CredenceFillinTbListDTO();
          if (obj[0] != null) {
            credenceFillinTbListDTO.setSettNum(obj[0].toString());
          }
          if (obj[2] != null) {
            credenceFillinTbListDTO.setBizType(obj[2].toString());
          }

          temp_credenceFillinTbListDTOList.add(credenceFillinTbListDTO);
        }

        return temp_credenceFillinTbListDTOList;
      }
    });
    return list;
  }

  /**
   * 张列 余额初始 同一办事处下的SUBJECT_CODE中存在相同的值 返回INT 大于1有相同的值
   * 
   * @param bookId officeName
   * @return String
   */
  public List getBalanceinitializeBT(final String bookId,
      final String officeName) {
    Validate.notNull(bookId);
    List stutas = null;
    try {
      stutas = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select fn210.subject_code from fn210 fn210 "
                  + "where fn210.office=? and fn210.book_id=? "
                  + "group by fn210.subject_code having count(fn210.subject_code)>1";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, officeName);
              query.setParameter(1, bookId);
              return query.list();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  /**
   * 根据票据编号来得到PL202与AA101中的业务状态与财务标识
   * 
   * @param noteNum 票据编号
   * @param bizType 业务类型
   * @return 由业务状态与财务标识所组成的数组
   * @author 付云峰
   */
  public int queryBizTypeAndIsFinance(final String noteNum, final String bizType) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          if (new Integer(bizType).intValue() < 20) {
            // 业务状态小于20时查询AA101
            int type = new Integer(bizType).intValue();
            if (type == 4 || type == 6) {
              sql = "select a101.id "
                  + "from aa101 a101 "
                  + "where (a101.biz_status in ('3','4') or a101.is_finance = 2) "
                  + "and a101.note_num=? " + "and a101.biz_type = 'E'";
            } else if (type == 5 || type == 7) {
              sql = "select a101.id "
                  + "from aa101 a101 "
                  + "where (a101.biz_status in ('3','4') or a101.is_finance = 2) "
                  + "and a101.note_num=? " + "and a101.biz_type = 'F'";
            } else {
              sql = "select a101.id "
                  + "from aa101 a101 "
                  + "where (a101.biz_status in ('3','4') or a101.is_finance = 2) "
                  + "and a101.note_num=?";
            }
          } else {
            sql = "select p202.flow_head_id " + "from pl202 p202 "
                + "where (p202.biz_st in ('4','5') or p202.is_finance=2) "
                + "and p202.note_num=?";
          }
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, noteNum);
          // query.setParameter(1, bizType);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  /**
   * 查询是否有与转帐业务相同的结算号
   * 
   * @param noteNum 将要转帐业务的结算号
   * @return 如果为空则说明没有相同的结算号
   * @author 付云峰
   */
  public List queryIsExistSettNum(final String noteNum, final String bookId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f201.credence_id from fn201 f201 where f201.sett_num=? and f201.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, noteNum);
          query.setParameter(1, bookId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 返回 debit，credit的和。 FN201表
   * 
   * @param bookId
   * @return 张列
   */
  public BookstartDTO queryDebitCredit(final String bookId) {
    BookstartDTO bookstartDTO = null;
    try {
      bookstartDTO = (BookstartDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select f.book_id,sum(f.debit),sum(f.credit) from fn201 f "
                  + "group by f.book_id having f.book_id=?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, bookId);
              Object[] obj = (Object[]) query.uniqueResult();
              BookstartDTO bookstartDTO = new BookstartDTO();
              if (obj != null) {
                if (obj[1] != null) {
                  bookstartDTO.setDebit(Double.parseDouble(obj[1].toString()));
                }
                if (obj[2] != null) {
                  bookstartDTO.setCredit(Double.parseDouble(obj[2].toString()));
                }
              }
              return bookstartDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bookstartDTO;
  }

  /**
   * 根据票据编号查询对应的单位和银行 如果业务类型大于20，到PL202中查询银行 如果业务类型小于20，则到AA101中查询单位
   * 
   * @param noteNum 票据编号
   * @param bizType 业务类型
   * @return
   * @author 付云峰
   */
  public List queryOrgAndBank(final String noteNum, final String bizType) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          if (new Integer(bizType).intValue() < 20) {
            // 业务状态小于20时查询AA101
            sql = "select distinct a101.org_id " + "from aa101 a101 "
                + "where a101.note_num=?";
          } else {
            sql = "select distinct p202.loan_bank_id " + "from pl202 p202 "
                + "where p202.note_num=?";
          }
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, noteNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 判断核算关系是否存在
   * 
   * @param noteNum 票据编号
   * @param bizType 业务类型
   * @return
   * @author 付云峰
   */
  public Integer queryIsCalculRela(final String noteNum, final String bizType,
      final String bookId) {
    Integer i = new Integer(0);
    try {
      i = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          String sql2 = "";
          if (Integer.parseInt(bizType) < 20) {
            // 业务状态小于20时查询AA101
            sql = "select distinct a101.org_id as org,b001.collection_bank_id as bank,b001.officecode as office "
                + "from aa101 a101, aa001 a001, ba001 b001 "
                + "where a101.org_id = a001.id "
                + "and a001.orginfo_id = b001.id "
                + "and a101.note_num = '"
                + noteNum + "'";
          } else if (Integer.parseInt(bizType) == 23) {
            sql = "select distinct p202.loan_bank_id as bank, p002.office as office "
                + "from pl202 p202, pl002 p002 "
                + "where p002.loan_bank_id = p202.loan_bank_id and p202.batch_num='"
                + noteNum + "'";
          } else {
            sql = "select distinct p202.loan_bank_id as bank, p002.office as office "
                + "from pl202 p202, pl002 p002 "
                + "where p002.loan_bank_id = p202.loan_bank_id and p202.note_num='"
                + noteNum + "'";
          }
          Query query = session.createSQLQuery(sql);
          List list = query.list();
          int count = 0;
          for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            String temp_str = "";
            for (int j = 0; j < obj.length; j++) {
              temp_str = temp_str + "'" + obj[j].toString() + "'" + ",";
            }
            temp_str = temp_str.substring(0, temp_str.lastIndexOf(","));
            sql2 = "select f111.first_subject_code " + "from fn111 f111"
                + " where f111.first_subject_code in"
                + " (select f120.subject_code" + " from fn120 f120"
                + " where f120.biz_type = " + bizType + " and f120.book_id = "
                + bookId + ")" + " and f111.calcul_rela_value in" + " ("
                + temp_str + ")" + " and f111.book_id = " + bookId;
            Query query2 = session.createSQLQuery(sql2);
            count += query2.list().size();
          }
          return new Integer(count);
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return i;
  }

  /**
   * 凭证生成的方法
   * 
   * @param settNum 由多个结算号与业务类型所组成的字符串，这个字符串将在存储过程中拆开
   * @param bookId bookid
   * @param userIP 操作员ip
   * @param userName 操作员
   * @throws BusinessException
   * @throws HibernateException
   * @throws SQLException
   * @author 付云峰
   */
  public void insertCredenceInfo(String settNum, String bookId, String userIP,
      String userName, String bizDate, String oldCredNum)
      throws BusinessException, HibernateException {

    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn
          .prepareCall("{call fn_ac_autotransfers(?,?,?,?,?,?)}");
      cs.setString(1, settNum);
      cs.setString(2, bookId);
      cs.setString(3, userName);
      cs.setString(4, userIP);
      cs.setString(5, bizDate);
      cs.setString(6, oldCredNum);
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询初始数据的日期
   * 
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public String getMinYearmonth_WL(final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    String yearmonth = "";
    try {
      yearmonth = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select min(t.credence_date) from fn201 t where t.book_id= ? ";
              Query query = session.createSQLQuery(sql);
              query.setString(0, securityInfo.getBookId());
              return query.uniqueResult();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return yearmonth;
  }

  /**
   * 查询年初（期初）科目余额
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryBgnblance_WL(final String subjectcode,
      final String direction, final String year, final String starperiod,
      final String endperiod, final String office,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String yearmonth = "";
              String hql = "";
              if (direction.equals("0")) {
                hql = " select sum(accountantCredence.debit)-sum(accountantCredence.credit) "
                    + " from AccountantCredence accountantCredence  ";
              } else {
                hql = " select sum(accountantCredence.credit)-sum(accountantCredence.debit) "
                    + " from AccountantCredence accountantCredence  ";
              }
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if ((starperiod != null && !starperiod.equals(""))
                  && (endperiod != null && !endperiod.equals(""))) {// 期初
                if (starperiod != null && !starperiod.equals("")) {
                  yearmonth = year.concat(starperiod).concat("01");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(yearmonth);
                }
              } else {// 年初
                yearmonth = year.concat("0101");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(yearmonth);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询年末（期末）科目余额
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryEndblance_WL(final String subjectcode,
      final String direction, final String year, final String starperiod,
      final String endperiod, final String office,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String yearmonth = "";
              String hql = "";
              if (direction.equals("0")) {
                hql = " select sum(accountantCredence.debit)-sum(accountantCredence.credit) "
                    + " from AccountantCredence accountantCredence  ";
              } else {
                hql = " select sum(accountantCredence.credit)-sum(accountantCredence.debit) "
                    + " from AccountantCredence accountantCredence  ";
              }
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if ((starperiod != null && !starperiod.equals(""))
                  && (endperiod != null && !endperiod.equals(""))) {// 期末
                if (endperiod != null && !endperiod.equals("")) {
                  if (endperiod.equals("12")) {
                    yearmonth = ("" + (Integer.parseInt(year) + 1))
                        .concat("0101");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(yearmonth);
                  } else if (endperiod.equals("09") || endperiod.equals("10")
                      || endperiod.equals("11")) {
                    yearmonth = year.concat(
                        "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(yearmonth);
                  } else {
                    yearmonth = year.concat(
                        "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(yearmonth);
                  }
                }
              } else {// 年末
                // yearmonth=(""+(Integer.parseInt(year)+1)).concat("0101");
                // criterion += " accountantCredence.credenceDate < ? and ";
                // parameters.add(yearmonth);
                if (endperiod.equals("12")) {
                  yearmonth = ("" + (Integer.parseInt(year) + 1))
                      .concat("0101");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(yearmonth);
                } else if (endperiod.equals("09") || endperiod.equals("10")
                    || endperiod.equals("11")) {
                  yearmonth = year.concat(
                      "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(yearmonth);
                } else {
                  yearmonth = year.concat(
                      "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(yearmonth);
                }
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询本期(本年累计)发生额（借方）
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryTermlendmnt_WL(final String subjectcode,
      final String year, final String starperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(accountantCredence.debit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if ((starperiod != null && !starperiod.equals(""))
                  && (endperiod != null && !endperiod.equals(""))) {// 本期
                if (starperiod != null && !starperiod.equals("")) {
                  bngyearmonth = year.concat(starperiod).concat("01");
                  criterion += " accountantCredence.credenceDate >= ? and ";
                  parameters.add(bngyearmonth);
                }
                if (endperiod != null && !endperiod.equals("")) {
                  if (endperiod.equals("12")) {
                    endyearmonth = ("" + (Integer.parseInt(year) + 1))
                        .concat("0101");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(endyearmonth);
                  } else if (endperiod.equals("09") || endperiod.equals("10")
                      || endperiod.equals("11")) {
                    endyearmonth = year.concat(
                        "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(endyearmonth);
                  } else {
                    endyearmonth = year.concat(
                        "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(endyearmonth);
                  }
                }
              } else {// 本年
                if (endperiod.equals("12")) {
                  endyearmonth = ("" + (Integer.parseInt(year) + 1))
                      .concat("0101");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(endyearmonth);
                } else if (endperiod.equals("09") || endperiod.equals("10")
                    || endperiod.equals("11")) {
                  endyearmonth = year.concat(
                      "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(endyearmonth);
                } else {
                  endyearmonth = year.concat(
                      "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(endyearmonth);
                }
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询本期(本年累计)发生额（贷方）
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryTermloanmnt_WL(final String subjectcode,
      final String year, final String starperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if ((starperiod != null && !starperiod.equals(""))
                  && (endperiod != null && !endperiod.equals(""))) {// 本期
                if (starperiod != null && !starperiod.equals("")) {
                  bngyearmonth = year.concat(starperiod).concat("01");
                  criterion += " accountantCredence.credenceDate >= ? and ";
                  parameters.add(bngyearmonth);
                }
                if (endperiod != null && !endperiod.equals("")) {
                  if (endperiod.equals("12")) {
                    endyearmonth = ("" + (Integer.parseInt(year) + 1))
                        .concat("0101");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(endyearmonth);
                  } else if (endperiod.equals("09") || endperiod.equals("10")
                      || endperiod.equals("11")) {
                    endyearmonth = year.concat(
                        "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(endyearmonth);
                  } else {
                    endyearmonth = year.concat(
                        "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < ? and ";
                    parameters.add(endyearmonth);
                  }
                }
              } else {// 本年
                if (endperiod.equals("12")) {
                  endyearmonth = ("" + (Integer.parseInt(year) + 1))
                      .concat("0101");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(endyearmonth);
                } else if (endperiod.equals("09") || endperiod.equals("10")
                    || endperiod.equals("11")) {
                  endyearmonth = year.concat(
                      "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(endyearmonth);
                } else {
                  endyearmonth = year.concat(
                      "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " accountantCredence.credenceDate < ? and ";
                  parameters.add(endyearmonth);
                }
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询上年(上年累计)发生额（借方）
   * 
   * @param subjectcode
   * @param year
   * @param flag
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryLastyearlentmnt_WL(final String subjectcode,
      final String year, final String flag, final String office,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String queryyear = "";
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(accountantCredence.debit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if (flag.equals("0")) {// 上年
                if (year != null && !year.equals("")) {
                  queryyear = "" + (Integer.parseInt(year) - 1);
                  bngyearmonth = queryyear.concat("0101");
                  criterion += " accountantCredence.credenceDate >= ? and ";
                  parameters.add(bngyearmonth);
                  endyearmonth = queryyear.concat("1231");
                  criterion += " accountantCredence.credenceDate <= ? and ";
                  parameters.add(endyearmonth);
                }
              } else {// 上年累计
                if (year != null && !year.equals("")) {
                  queryyear = "" + (Integer.parseInt(year) - 1);
                  endyearmonth = queryyear.concat("1231");
                  criterion += " accountantCredence.credenceDate <= ? and ";
                  parameters.add(endyearmonth);
                }
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询上年(上年累计)发生额（贷方）
   * 
   * @param subjectcode
   * @param year
   * @param flag
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryLastyearloanmnt_WL(final String subjectcode,
      final String year, final String flag, final String office,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String queryyear = "";
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if (flag.equals("0")) {// 上年
                if (year != null && !year.equals("")) {
                  queryyear = "" + (Integer.parseInt(year) - 1);
                  bngyearmonth = queryyear.concat("0101");
                  criterion += " accountantCredence.credenceDate >= ? and ";
                  parameters.add(bngyearmonth);
                  endyearmonth = queryyear.concat("1231");
                  criterion += " accountantCredence.credenceDate <= ? and ";
                  parameters.add(endyearmonth);
                }
              } else {// 上年累计
                if (year != null && !year.equals("")) {
                  queryyear = "" + (Integer.parseInt(year) - 1);
                  endyearmonth = queryyear.concat("1231");
                  criterion += " accountantCredence.credenceDate <= ? and ";
                  parameters.add(endyearmonth);
                }
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询本年发生额（借方）
   * 
   * @param subjectcode
   * @param year
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryYearlendmnt_WL(final String subjectcode,
      final String year, final String endperiod, final String office,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(accountantCredence.debit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }

              // 本年
              bngyearmonth = year.concat("0101");
              criterion += " accountantCredence.credenceDate >= ? and ";
              parameters.add(bngyearmonth);

              if (endperiod.equals("12")) {
                endyearmonth = ("" + (Integer.parseInt(year) + 1))
                    .concat("0101");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else if (endperiod.equals("09") || endperiod.equals("10")
                  || endperiod.equals("11")) {
                endyearmonth = year.concat(
                    "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else {
                endyearmonth = year.concat(
                    "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              }

              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询本年发生额（贷方）
   * 
   * @param subjectcode
   * @param year
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryYearloanmnt_WL(final String subjectcode,
      final String year, final String endperiod, final String office,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }

              // 本年
              bngyearmonth = year.concat("0101");
              criterion += " accountantCredence.credenceDate >= ? and ";
              parameters.add(bngyearmonth);

              if (endperiod.equals("12")) {
                endyearmonth = ("" + (Integer.parseInt(year) + 1))
                    .concat("0101");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else if (endperiod.equals("09") || endperiod.equals("10")
                  || endperiod.equals("11")) {
                endyearmonth = year.concat(
                    "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else {
                endyearmonth = year.concat(
                    "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              }

              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询提取金额-本期发生
   * 
   * @param subjectcode
   * @param year
   * @param startperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryPicktermamount_WL(final String[] picktype,
      final String year, final String startperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {
      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(pickTail.pickPreBalance)+sum(pickTail.pickCurBalance) "
                  + " from PickTail pickTail, PickHead pickHead ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where pickHead.pickSatatus='5' and pickTail.pickHead.id=pickHead.id and ";

              if (picktype != null && picktype.length != 0) {
                criterion += " pickTail.pickReason in ( ";
                for (int i = 0; i < picktype.length; i++) {
                  criterion += " '" + new BigDecimal(picktype[i]) + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (startperiod != null && !startperiod.equals("")) {
                bngyearmonth = year.concat(startperiod).concat("01");
                criterion += " pickHead.settDate >= ? and ";
                parameters.add(bngyearmonth);
              }
              if (endperiod != null && !endperiod.equals("")) {
                if (endperiod.equals("12")) {
                  endyearmonth = ("" + (Integer.parseInt(year) + 1))
                      .concat("0101");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                } else if (endperiod.equals("09") || endperiod.equals("10")
                    || endperiod.equals("11")) {
                  endyearmonth = year.concat(
                      "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                } else {
                  endyearmonth = year.concat(
                      "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                }
              }

              if (office != null && !office.equals("")) {
                criterion += " pickHead.org.orgInfo.officecode = ? and ";
                parameters.add(office);
              } else {
                criterion += " pickHead.org.orgInfo.officecode in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询提取金额-本年发生
   * 
   * @param subjectcode
   * @param year
   * @param startperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryPickyearamount_WL(final String[] picktype,
      final String year, final String startperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {
      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String bngyearmonth = "";
              String endyearmonth = "";
              String hql = " select sum(pickTail.pickPreBalance)+sum(pickTail.pickCurBalance) "
                  + " from PickTail pickTail, PickHead pickHead ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where pickHead.pickSatatus='5' and pickTail.pickHead.id=pickHead.id and ";

              if (picktype != null && picktype.length != 0) {
                criterion += " pickTail.pickReason in ( ";
                for (int i = 0; i < picktype.length; i++) {
                  criterion += " '" + new BigDecimal(picktype[i]) + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (startperiod != null && !startperiod.equals("")) {
                bngyearmonth = year.concat("0101");
                criterion += " pickHead.settDate >= ? and ";
                parameters.add(bngyearmonth);
              }
              if (endperiod != null && !endperiod.equals("")) {
                if (endperiod.equals("12")) {
                  endyearmonth = ("" + (Integer.parseInt(year) + 1))
                      .concat("0101");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                } else if (endperiod.equals("09") || endperiod.equals("10")
                    || endperiod.equals("11")) {
                  endyearmonth = year.concat(
                      "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                } else {
                  endyearmonth = year.concat(
                      "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                }
              }

              if (office != null && !office.equals("")) {
                criterion += " pickHead.org.orgInfo.officecode = ? and ";
                parameters.add(office);
              } else {
                criterion += " pickHead.org.orgInfo.officecode in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询提取金额-本年累计发生
   * 
   * @param subjectcode
   * @param year
   * @param startperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryPickyearsumamount_WL(final String[] picktype,
      final String year, final String startperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {
      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String endyearmonth = "";
              String hql = " select sum(pickTail.pickPreBalance)+sum(pickTail.pickCurBalance) "
                  + " from PickTail pickTail, PickHead pickHead ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where pickHead.pickSatatus='5' and pickTail.pickHead.id=pickHead.id and ";

              if (picktype != null && picktype.length != 0) {
                criterion += " pickTail.pickReason in ( ";
                for (int i = 0; i < picktype.length; i++) {
                  criterion += " '" + new BigDecimal(picktype[i]) + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (endperiod != null && !endperiod.equals("")) {
                if (endperiod.equals("12")) {
                  endyearmonth = ("" + (Integer.parseInt(year) + 1))
                      .concat("0101");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                } else if (endperiod.equals("09") || endperiod.equals("10")
                    || endperiod.equals("11")) {
                  endyearmonth = year.concat(
                      "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                } else {
                  endyearmonth = year.concat(
                      "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                  criterion += " pickHead.settDate < ? and ";
                  parameters.add(endyearmonth);
                }
              }

              if (office != null && !office.equals("")) {
                criterion += " pickHead.org.orgInfo.officecode = ? and ";
                parameters.add(office);
              } else {
                criterion += " pickHead.org.orgInfo.officecode in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 查询损益结转列表的方法
   * 
   * @param dto 查询条件
   * @param bookId 帐套号
   * @param officeList 办事处
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return 封装了列表内容的List
   * @author 付云峰
   */
  public List querySettleIncAndDecInfoList(final CredenceFillinTcFindDTO dto,
      final String bookId, final List officeList, final String orderBy,
      final String order, final int start, final int pageSize, final int page,
      final String useYearmonth) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String office = "";
        String str = "";
        if (dto.getOffice() != null && !dto.getOffice().equals("")) {
          str = " and f201.office = '" + dto.getOffice().trim() + "' ";
        } else {
          for (int i = 0; i < officeList.size(); i++) {
            String temp_office = (String) officeList.get(i);
            office = office + "'" + temp_office + "'" + ",";
          }
          office = office.substring(0, office.lastIndexOf(","));
          str = " and f201.office in (" + office + ") ";
        }
        String sql = "select f201.subject_code," + "f201.office,"
            + "max(f110.subject_name)," + "max(f202.by_subject_direction),"
            + "nvl(sum(f201.credit),0)," + "nvl(sum(f201.debit),0),"
            + "max(f110.balance_direction) "
            + "from fn201 f201, fn202 f202, fn110 f110 "
            + "where f201.subject_code = f202.by_subject_code "
            + "and f201.subject_code = f110.subject_code "
            + "and f201.book_id=f202.book_id " + "and f201.credence_st = 2 "
            + "and f201.book_id=f110.book_id " + "and f201.credence_date >='"
            + useYearmonth + "' and f201.inc_dec_st = 0 " + str
            + "and f201.book_id = " + bookId;

        Vector parameters = new Vector();
        String criterion = "";

        String subjectCode = dto.getSubjectcode().trim();
        if (subjectCode != null && !subjectCode.equals("")) {
          criterion += " f201.subject_code = ? and ";
          parameters.add(subjectCode);
        }

        String subjectName = dto.getSubjectName().trim();
        if (subjectName != null && !subjectName.equals("")) {
          criterion += " f110.subject_name = ? and ";
          parameters.add(subjectName);
        }

        String startMoney = dto.getStartMoney().trim();
        String endMoney = dto.getEndMoney().trim();
        if (startMoney != null && !startMoney.equals("") && endMoney != null
            && !endMoney.equals("")) {
          criterion += " ((select abs(sum(f.credit-f.debit)) from fn201 f where f.book_id=f201.book_id and f.subject_code=f201.subject_code) between ? and ?) and ";
          parameters.add(startMoney);
          parameters.add(endMoney);
        } else if (startMoney != null && !startMoney.equals("")
            && (endMoney == null || endMoney.equals(""))) {
          criterion += " ((select abs(sum(f.credit-f.debit)) from fn201 f where f.book_id=f201.book_id and f.subject_code=f201.subject_code) > ?) and ";
          parameters.add(startMoney);
        } else if (endMoney != null && !endMoney.equals("")
            && (startMoney == null || startMoney.equals(""))) {
          criterion += " ((select abs(sum(f.credit-f.debit)) from fn201 f where f.book_id=f201.book_id and f.subject_code=f201.subject_code) < ?) and ";
          parameters.add(endMoney);
        }

        String credenceDateStart = dto.getCredenceDateStart().trim();
        String credenceDateEnd = dto.getCredenceDateEnd().trim();
        if (credenceDateStart != null && !credenceDateStart.equals("")
            && credenceDateEnd != null && !credenceDateEnd.equals("")) {
          criterion += " (f201.credence_date between ? and ?) and ";
          parameters.add(credenceDateStart);
          parameters.add(credenceDateEnd);
        } else if (credenceDateStart != null && !credenceDateStart.equals("")
            && (credenceDateEnd == null || credenceDateEnd.equals(""))) {
          criterion += " (f201.credence_date > ?) and ";
          parameters.add(credenceDateStart);
        } else if (credenceDateEnd != null && !credenceDateEnd.equals("")
            && (credenceDateStart == null || credenceDateStart.equals(""))) {
          criterion += " (f201.credence_date < ?) and ";
          parameters.add(credenceDateEnd);
        }

        String ob = orderBy;
        if (ob == null)
          ob = " f201.subject_code ";
        String od = order;
        if (od == null)
          od = "ASC";

        if (criterion.length() != 0)
          criterion = "and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        sql = sql + criterion + " group by f201.subject_code, f201.office "
            + "order by " + ob + " " + od;

        Query query = session.createSQLQuery(sql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);

        CredenceFillinTcListDTO credenceFillinTcListDTO = null;
        List temp_Array = new ArrayList();

        List queryList = query.list();

        if (queryList == null || queryList.size() == 0) {
          query.setFirstResult(pageSize * (page - 2));
          queryList = query.list();
        }

        Iterator it = queryList.iterator();
        Object[] obj = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          credenceFillinTcListDTO = new CredenceFillinTcListDTO();
          if (obj[0] != null) {
            credenceFillinTcListDTO.setSubjectcode(obj[0].toString());
          }
          if (obj[1] != null) {
            credenceFillinTcListDTO.setOffice(obj[1].toString());
          }
          if (obj[2] != null) {
            credenceFillinTcListDTO.setSubjectName(obj[2].toString());
          }
          if (obj[3] != null) {
            credenceFillinTcListDTO.setBySubjectDirection(obj[3].toString());
          }
          if (obj[4] != null) {
            credenceFillinTcListDTO.setSumCredit(new BigDecimal(obj[4]
                .toString()));
          }
          if (obj[5] != null) {
            credenceFillinTcListDTO.setSumDebit(new BigDecimal(obj[5]
                .toString()));
          }
          if (obj[6] != null) {
            credenceFillinTcListDTO.setBalanceDirection(obj[6].toString());
          }
          temp_Array.add(credenceFillinTcListDTO);

        }
        return temp_Array;
      }
    });
    return list;
  }

  /**
   * 查询损益结转列表count的方法，同时需要带回部分信息用于在全部结转中使用
   * 
   * @param dto 查询条件
   * @param bookId bookId
   * @param officeList 办事处
   * @return
   */
  public List querySettleIncAndDecInfoCount(final CredenceFillinTcFindDTO dto,
      final String bookId, final List officeList, final String useYearmonth) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String office = "";
        String str = "";
        if (dto.getOffice() != null && !dto.getOffice().equals("")) {
          str = " and f201.office = '" + dto.getOffice().trim() + "' ";
        } else {
          for (int i = 0; i < officeList.size(); i++) {
            String temp_office = (String) officeList.get(i);
            office = office + "'" + temp_office + "'" + ",";
          }
          office = office.substring(0, office.lastIndexOf(","));
          str = " and f201.office in (" + office + ") ";
        }
        String sql = "select f201.subject_code," + "f201.office,"
            + "max(f202.by_subject_direction)," + "nvl(sum(f201.credit),0),"
            + "nvl(sum(f201.debit),0)," + "max(f110.balance_direction) "
            + "from fn201 f201, fn202 f202, fn110 f110 "
            + "where f201.subject_code = f202.by_subject_code "
            + "and f201.subject_code = f110.subject_code "
            + "and f201.book_id=f202.book_id " + "and f201.credence_st = 2 "
            + "and f201.book_id=f110.book_id " + "and f201.credence_date >='"
            + useYearmonth + "' and f201.inc_dec_st = 0 " + str
            + "and f201.book_id = " + bookId;

        Vector parameters = new Vector();
        String criterion = "";

        String subjectCode = dto.getSubjectcode().trim();
        if (subjectCode != null && !subjectCode.equals("")) {
          criterion += " f201.subject_code = ? and ";
          parameters.add(subjectCode);
        }

        String subjectName = dto.getSubjectName().trim();
        if (subjectName != null && !subjectName.equals("")) {
          criterion += " f110.subject_name = ? and ";
          parameters.add(subjectName);
        }

        String startMoney = dto.getStartMoney().trim();
        String endMoney = dto.getEndMoney().trim();
        if (startMoney != null && !startMoney.equals("") && endMoney != null
            && !endMoney.equals("")) {
          criterion += " ((select abs(sum(f.credit-f.debit)) from fn201 f where f.book_id=f201.book_id and f.subject_code=f201.subject_code) between ? and ?) and ";
          parameters.add(startMoney);
          parameters.add(endMoney);
        } else if (startMoney != null && !startMoney.equals("")
            && (endMoney == null || endMoney.equals(""))) {
          criterion += " ((select abs(sum(f.credit-f.debit)) from fn201 f where f.book_id=f201.book_id and f.subject_code=f201.subject_code) > ?) and ";
          parameters.add(startMoney);
        } else if (endMoney != null && !endMoney.equals("")
            && (startMoney == null || startMoney.equals(""))) {
          criterion += " ((select abs(sum(f.credit-f.debit)) from fn201 f where f.book_id=f201.book_id and f.subject_code=f201.subject_code) < ?) and ";
          parameters.add(endMoney);
        }

        String credenceDateStart = dto.getCredenceDateStart().trim();
        String credenceDateEnd = dto.getCredenceDateEnd().trim();
        if (credenceDateStart != null && !credenceDateStart.equals("")
            && credenceDateEnd != null && !credenceDateEnd.equals("")) {
          criterion += " (f201.credence_date between ? and ?) and ";
          parameters.add(credenceDateStart);
          parameters.add(credenceDateEnd);
        } else if (credenceDateStart != null && !credenceDateStart.equals("")
            && (credenceDateEnd == null || credenceDateEnd.equals(""))) {
          criterion += " (f201.credence_date > ?) and ";
          parameters.add(credenceDateStart);
        } else if (credenceDateEnd != null && !credenceDateEnd.equals("")
            && (credenceDateStart == null || credenceDateStart.equals(""))) {
          criterion += " (f201.credence_date < ?) and ";
          parameters.add(credenceDateEnd);
        }

        if (criterion.length() != 0)
          criterion = "and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        sql = sql + criterion + " group by f201.subject_code, f201.office ";

        Query query = session.createSQLQuery(sql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        CredenceFillinTcListDTO credenceFillinTcListDTO = null;
        List temp_Array = new ArrayList();
        Iterator it = query.list().iterator();
        Object[] obj = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          credenceFillinTcListDTO = new CredenceFillinTcListDTO();
          if (obj[0] != null) {
            credenceFillinTcListDTO.setSubjectcode(obj[0].toString());
          }
          if (obj[1] != null) {
            credenceFillinTcListDTO.setOffice(obj[1].toString());
          }
          if (obj[2] != null) {
            credenceFillinTcListDTO.setBySubjectDirection(obj[2].toString());
          }
          if (obj[3] != null) {
            credenceFillinTcListDTO.setSumCredit(new BigDecimal(obj[3]
                .toString()));
          }
          if (obj[4] != null) {
            credenceFillinTcListDTO.setSumDebit(new BigDecimal(obj[4]
                .toString()));
          }
          if (obj[5] != null) {
            credenceFillinTcListDTO.setBalanceDirection(obj[5].toString());
          }
          temp_Array.add(credenceFillinTcListDTO);

        }
        return temp_Array;
      }
    });
    return list;
  }

  /**
   * 判断待结转的业务在FN201表中的损益结转状态是否=0
   * 
   * @param bookid bookid
   * @param subjectcode 科目代码
   * @param office 办事处
   * @return 如果存在记录 int将大于0
   * @author 付云峰
   */
  public int isSettleIncAndDecInfo(final String bookid,
      final String subjectcode, final String office) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f201.credence_id from fn201 f201 where f201.book_id=? and f201.subject_code=? and f201.office=? and f201.inc_dec_st='1'";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookid);
          query.setParameter(1, subjectcode);
          query.setParameter(2, office);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * 损益结转的方法
   * 
   * @param settNum
   * @param bookId
   * @param userIP
   * @param userName
   * @param bizDate
   * @throws BusinessException
   * @throws HibernateException
   * @author 付云峰
   */
  public void insertSettleIncAndDecInfo(String SettleIncAndDecInfo,
      String bookId, String userIP, String userName, String bizDate,
      String credenceDateStart, String credenceDateEnd)
      throws BusinessException, HibernateException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn
          .prepareCall("{call fn_settleincanddecinfo(?,?,?,?,?,?,?)}");
      cs.setString(1, SettleIncAndDecInfo);
      cs.setString(2, bizDate);
      cs.setString(3, bookId);
      cs.setString(4, userName);
      cs.setString(5, userIP);
      cs.setString(6, credenceDateStart);
      cs.setString(7, credenceDateEnd);
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 凭证审核
   * 
   * @author wsh 2007-10-26 查找FN201表中符合条件的记录，放在页面的list中
   *         查询条件：cashDayClearTbFindDTO
   */
  public List queryCredencecheckList(final String type,
      final CredencecheckFindDTO credencecheckFindDTO,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page,
      final String useYearmonth) throws Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select max(fn201.credence_id),fn201.credence_date,fn201.credence_num,"
            + " sum(fn201.debit),"
            + " sum(fn201.credit),"
            + " max(fn201.makebill),"
            + " max(fn201.checkpsn),"
            + " max(fn201.credence_st),"
            + " max(fn201.office)"
            + " from FN201 fn201,FN110 fn110"
            + " where fn201.subject_code = fn110.subject_code  "
            + " and fn201.book_id = fn110.book_id "
            + " and fn201.credence_date >= '" + useYearmonth + "' "
            + " and fn201.credence_num is not null ";
          Vector parameters = new Vector();
          if (credencecheckFindDTO.getCredenceSt().trim() != null
              && !credencecheckFindDTO.getCredenceSt().trim().equals("")) {
            hql += " and fn201.credence_st = ? ";
            parameters.add(credencecheckFindDTO.getCredenceSt().trim());
          }else if ("1".equals(type)) {
            hql += " and fn201.credence_st in('0','1','2') ";
          } else if ("2".equals(type)) {
            hql += " and fn201.credence_st ='1' ";
          } else if ("3".equals(type)) {
            hql += " and fn201.credence_st in('0','2') ";
          } else {
            hql += " and fn201.credence_st ='0' ";
          }

          String criterion = "";
          criterion += "fn201.book_id = ?  and ";
          parameters.add(securityInfo.getBookId());
          if (credencecheckFindDTO.getCredenceDateSt().trim() != null
              && !credencecheckFindDTO.getCredenceDateSt().trim().equals("")
              && credencecheckFindDTO.getCredenceDateEnd().trim() != null
              && !credencecheckFindDTO.getCredenceDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_date >=? and fn201.credence_date <=? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateSt().trim());
            parameters.add(credencecheckFindDTO.getCredenceDateEnd().trim());
          }
          if (credencecheckFindDTO.getCredenceDateSt().trim() != null
              && !credencecheckFindDTO.getCredenceDateSt().trim().equals("")
              && (credencecheckFindDTO.getCredenceDateEnd().trim() == null || credencecheckFindDTO
                  .getCredenceDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_date >= ? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateSt().trim());
          }
          if (credencecheckFindDTO.getCredenceDateEnd().trim() != null
              && !credencecheckFindDTO.getCredenceDateEnd().trim().equals("")
              && (credencecheckFindDTO.getCredenceDateSt().trim() == null || credencecheckFindDTO
                  .getCredenceDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_date <= ? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateEnd().trim());
          }
          if (credencecheckFindDTO.getSettDateSt().trim() != null
              && !credencecheckFindDTO.getSettDateSt().trim().equals("")
              && credencecheckFindDTO.getSettDateEnd().trim() != null
              && !credencecheckFindDTO.getSettDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.sett_date >= ? and fn201.sett_date <= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateSt().trim());
            parameters.add(credencecheckFindDTO.getSettDateEnd().trim());
          }
          if (credencecheckFindDTO.getSettDateSt().trim() != null
              && !credencecheckFindDTO.getSettDateSt().trim().equals("")
              && (credencecheckFindDTO.getSettDateEnd().trim() == null || credencecheckFindDTO
                  .getSettDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.sett_date >= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateSt().trim());
          }
          if (credencecheckFindDTO.getSettDateEnd().trim() != null
              && !credencecheckFindDTO.getSettDateEnd().trim().equals("")
              && (credencecheckFindDTO.getSettDateSt().trim() == null || credencecheckFindDTO
                  .getSettDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.sett_date <= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateEnd().trim());
          }
          if (credencecheckFindDTO.getSubjectCode().trim() != null
              && !credencecheckFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn201.subject_code like ? and ";
            parameters.add(credencecheckFindDTO.getSubjectCode().trim() + "%");
          }
          if (credencecheckFindDTO.getSubjectName().trim() != null
              && !credencecheckFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(credencecheckFindDTO.getSubjectName().trim() + "%");
          }
          if (credencecheckFindDTO.getOperator().trim() != null
              && !credencecheckFindDTO.getOperator().trim().equals("")) {
            criterion += " fn201.makebill = ? and ";
            parameters.add(credencecheckFindDTO.getOperator());
          } else if (credencecheckFindDTO.getFlag() == null) {
            criterion += " fn201.makebill = '"
                + securityInfo.getUserInfo().getUsername() + "' and ";
          }
          if (credencecheckFindDTO.getCredenceNumSt().trim() != null
              && !credencecheckFindDTO.getCredenceNumSt().trim().equals("")
              && credencecheckFindDTO.getCredenceNumEnd().trim() != null
              && !credencecheckFindDTO.getCredenceNumEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_num >=to_number(?) and fn201.credence_num <=to_number(?) and ";
            parameters.add(credencecheckFindDTO.getCredenceNumSt().trim());
            parameters.add(credencecheckFindDTO.getCredenceNumEnd().trim());
          }
          if (credencecheckFindDTO.getCredenceNumSt().trim() != null
              && !credencecheckFindDTO.getCredenceNumSt().trim().equals("")
              && (credencecheckFindDTO.getCredenceNumEnd().trim() == null || credencecheckFindDTO
                  .getCredenceNumEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_num >= to_number(?) and ";
            parameters.add(credencecheckFindDTO.getCredenceNumSt().trim());
          }
          if (credencecheckFindDTO.getCredenceNumEnd().trim() != null
              && !credencecheckFindDTO.getCredenceNumEnd().trim().equals("")
              && (credencecheckFindDTO.getCredenceNumSt().trim() == null || credencecheckFindDTO
                  .getCredenceNumSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_num <= to_number(?)  and ";
            parameters.add(credencecheckFindDTO.getCredenceNumEnd().trim());
          }
          if (credencecheckFindDTO.getSettType().trim() != null
              && !credencecheckFindDTO.getSettType().trim().equals("")) {
            criterion += " fn201.sett_type = ? and ";
            parameters.add(credencecheckFindDTO.getSettType().trim());
          }

          if (credencecheckFindDTO.getSettNum().trim() != null
              && !credencecheckFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn201.sett_num = ? and ";
            parameters.add(credencecheckFindDTO.getSettNum().trim());
          }
          if (credencecheckFindDTO.getSummary() != null
              && !credencecheckFindDTO.getSummary().trim().equals("")) {
            criterion += " fn201.summay in "
              + "(select t.para_id from fn102 t where t.param_num = 4 and t.param_value > 10 and t.param_explain like ?) and";
            parameters.add("%" + credencecheckFindDTO.getSummary().trim() + "%");
          }
          if (credencecheckFindDTO.getOffice().trim() != null
              && !credencecheckFindDTO.getOffice().trim().equals("")
              && "全部".equals(credencecheckFindDTO.getOffice())) {

            criterion += " fn201.office in ( ";
            List officeList = securityInfo.getOfficeList();
            OfficeDto officedto = null;
            Iterator itr1 = officeList.iterator();
            while (itr1.hasNext()) {
              officedto = (OfficeDto) itr1.next();
              criterion += " '" + officedto.getOfficeCode() + "',";
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += " ) and ";

          } else if (credencecheckFindDTO.getOffice().trim() != null
              && !credencecheckFindDTO.getOffice().trim().equals("")) {
            criterion += " fn201.office = ? and ";
            parameters.add(credencecheckFindDTO.getOffice().trim());
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String having = "";
          if (credencecheckFindDTO.getMoneySt().trim() != null
              && !credencecheckFindDTO.getMoneySt().trim().equals("")
              && credencecheckFindDTO.getMoneyEnd().trim() != null
              && !credencecheckFindDTO.getMoneyEnd().trim().equals("")) {// 有开始金额结束金额
            having = " having sum(fn201.debit) >=? and sum(fn201.debit) <=? ";
            parameters.add(credencecheckFindDTO.getMoneySt());
            parameters.add(credencecheckFindDTO.getMoneyEnd());
          }
          if (credencecheckFindDTO.getMoneySt().trim() != null
              && !credencecheckFindDTO.getMoneySt().trim().equals("")
              && (credencecheckFindDTO.getMoneyEnd().trim() == null || credencecheckFindDTO
                  .getMoneyEnd().trim().equals(""))) {// 有开始金额无结束金额
            having = " having sum(fn201.debit) >= ? ";
            parameters.add(credencecheckFindDTO.getMoneySt());
          }
          if (credencecheckFindDTO.getMoneyEnd().trim() != null
              && !credencecheckFindDTO.getMoneyEnd().trim().equals("")
              && (credencecheckFindDTO.getMoneySt().trim() == null || credencecheckFindDTO
                  .getMoneySt().trim().equals(""))) {// 无开始金额有结束金额
            having = " having sum(fn201.debit) <= ? ";
            parameters.add(credencecheckFindDTO.getMoneyEnd());
          }
          String ob = orderBy;
          if (ob == null)
            ob = " fn201.credence_date,to_number(fn201.credence_num) ";
          String od = order;
          if (od == null)
            od = "";
          hql = hql + criterion
              + " group by fn201.credence_date,fn201.credence_num"
              + having
              + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
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
              CredencecheckShowListDTO credencecheckShowListDTO = new CredencecheckShowListDTO();
              if (obj[0] != null) {
                credencecheckShowListDTO.setCredenceId(obj[0].toString());
              }
              if (obj[1] != null) {
                credencecheckShowListDTO
                    .setCredenceDate(obj[1].toString());
              }
              if (obj[2] != null) {
                credencecheckShowListDTO.setCredenceNum(obj[2].toString());
              }
              if (obj[3] != null) {
                credencecheckShowListDTO.setDebit(new BigDecimal(obj[3]
                    .toString()));
              }
              if (obj[4] != null) {
                credencecheckShowListDTO.setCredit(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                credencecheckShowListDTO.setMakeBill(obj[5].toString());
              }
              if (obj[6] != null) {
                credencecheckShowListDTO.setCheckpsn(obj[6].toString());
              }
              if (obj[7] != null) {
                credencecheckShowListDTO.setCredenceSt(obj[7].toString());
              }
              if (obj[8] != null) {
                credencecheckShowListDTO.setOffice(obj[8].toString());
              }
              temp_list.add(credencecheckShowListDTO);
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
   * 凭证审核
   * 
   * @author wsh 2007-10-26 查找FN201表中记录数量，放在页面的list中 查询条件：cashDayClearTbFindDTO
   */
  public List queryCredencecheckCountList(final String type,
      final CredencecheckFindDTO credencecheckFindDTO,
      final SecurityInfo securityInfo, final String useYearmonth)
      throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select max(fn201.credence_id),fn201.credence_date,fn201.credence_num,"
            + " sum(fn201.debit),"
            + " sum(fn201.credit),"
            + " max(fn201.makebill),"
            + " max(fn201.checkpsn),"
            + " max(fn201.credence_st),"
            + " max(fn201.office)"
            + " from FN201 fn201,FN110 fn110"
            + " where fn201.subject_code = fn110.subject_code  "
            + " and fn201.book_id = fn110.book_id "
            + " and fn201.credence_date >= '" + useYearmonth + "' "
            + " and fn201.credence_num is not null ";
          Vector parameters = new Vector();
          if (credencecheckFindDTO.getCredenceSt().trim() != null
              && !credencecheckFindDTO.getCredenceSt().trim().equals("")) {
            hql += " and fn201.credence_st = ? ";
            parameters.add(credencecheckFindDTO.getCredenceSt().trim());
          }else if ("1".equals(type)) {
            hql += " and fn201.credence_st in('0','1','2') ";
          } else if ("2".equals(type)) {
            hql += " and fn201.credence_st ='1' ";
          } else if ("3".equals(type)) {
            hql += " and fn201.credence_st in('0','2') ";
          } else {
            hql += " and fn201.credence_st ='0' ";
          }

          String criterion = "";
          criterion += "fn201.book_id = ?  and ";
          parameters.add(securityInfo.getBookId());
          if (credencecheckFindDTO.getCredenceDateSt().trim() != null
              && !credencecheckFindDTO.getCredenceDateSt().trim().equals("")
              && credencecheckFindDTO.getCredenceDateEnd().trim() != null
              && !credencecheckFindDTO.getCredenceDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_date >=? and fn201.credence_date <=? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateSt().trim());
            parameters.add(credencecheckFindDTO.getCredenceDateEnd().trim());
          }
          if (credencecheckFindDTO.getCredenceDateSt().trim() != null
              && !credencecheckFindDTO.getCredenceDateSt().trim().equals("")
              && (credencecheckFindDTO.getCredenceDateEnd().trim() == null || credencecheckFindDTO
                  .getCredenceDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_date >= ? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateSt().trim());
          }
          if (credencecheckFindDTO.getCredenceDateEnd().trim() != null
              && !credencecheckFindDTO.getCredenceDateEnd().trim().equals("")
              && (credencecheckFindDTO.getCredenceDateSt().trim() == null || credencecheckFindDTO
                  .getCredenceDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_date <= ? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateEnd().trim());
          }
          if (credencecheckFindDTO.getSettDateSt().trim() != null
              && !credencecheckFindDTO.getSettDateSt().trim().equals("")
              && credencecheckFindDTO.getSettDateEnd().trim() != null
              && !credencecheckFindDTO.getSettDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.sett_date >= ? and fn201.sett_date <= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateSt().trim());
            parameters.add(credencecheckFindDTO.getSettDateEnd().trim());
          }
          if (credencecheckFindDTO.getSettDateSt().trim() != null
              && !credencecheckFindDTO.getSettDateSt().trim().equals("")
              && (credencecheckFindDTO.getSettDateEnd().trim() == null || credencecheckFindDTO
                  .getSettDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.sett_date >= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateSt().trim());
          }
          if (credencecheckFindDTO.getSettDateEnd().trim() != null
              && !credencecheckFindDTO.getSettDateEnd().trim().equals("")
              && (credencecheckFindDTO.getSettDateSt().trim() == null || credencecheckFindDTO
                  .getSettDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.sett_date <= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateEnd().trim());
          }
          if (credencecheckFindDTO.getSubjectCode().trim() != null
              && !credencecheckFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn201.subject_code like ? and ";
            parameters.add(credencecheckFindDTO.getSubjectCode().trim() + "%");
          }
          if (credencecheckFindDTO.getSubjectName().trim() != null
              && !credencecheckFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(credencecheckFindDTO.getSubjectName().trim() + "%");
          }
          if (credencecheckFindDTO.getOperator().trim() != null
              && !credencecheckFindDTO.getOperator().trim().equals("")) {
            criterion += " fn201.makebill = ? and ";
            parameters.add(credencecheckFindDTO.getOperator());
          } else if (credencecheckFindDTO.getFlag() == null) {
            criterion += " fn201.makebill = '"
                + securityInfo.getUserInfo().getUsername() + "' and ";
          }
          if (credencecheckFindDTO.getCredenceNumSt().trim() != null
              && !credencecheckFindDTO.getCredenceNumSt().trim().equals("")
              && credencecheckFindDTO.getCredenceNumEnd().trim() != null
              && !credencecheckFindDTO.getCredenceNumEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_num >=to_number(?) and fn201.credence_num <=to_number(?) and ";
            parameters.add(credencecheckFindDTO.getCredenceNumSt().trim());
            parameters.add(credencecheckFindDTO.getCredenceNumEnd().trim());
          }
          if (credencecheckFindDTO.getCredenceNumSt().trim() != null
              && !credencecheckFindDTO.getCredenceNumSt().trim().equals("")
              && (credencecheckFindDTO.getCredenceNumEnd().trim() == null || credencecheckFindDTO
                  .getCredenceNumEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_num >= to_number(?) and ";
            parameters.add(credencecheckFindDTO.getCredenceNumSt().trim());
          }
          if (credencecheckFindDTO.getCredenceNumEnd().trim() != null
              && !credencecheckFindDTO.getCredenceNumEnd().trim().equals("")
              && (credencecheckFindDTO.getCredenceNumSt().trim() == null || credencecheckFindDTO
                  .getCredenceNumSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_num <= to_number(?)  and ";
            parameters.add(credencecheckFindDTO.getCredenceNumEnd().trim());
          }
          if (credencecheckFindDTO.getSettType().trim() != null
              && !credencecheckFindDTO.getSettType().trim().equals("")) {
            criterion += " fn201.sett_type = ? and ";
            parameters.add(credencecheckFindDTO.getSettType().trim());
          }

          if (credencecheckFindDTO.getSettNum().trim() != null
              && !credencecheckFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn201.sett_num = ? and ";
            parameters.add(credencecheckFindDTO.getSettNum().trim());
          }
          
          if (credencecheckFindDTO.getSummary() != null
              && !credencecheckFindDTO.getSummary().trim().equals("")) {
            criterion += " fn201.summay in "
              + "(select t.para_id from fn102 t where t.param_num = 4 and t.param_value > 10 and t.param_explain like ?) and";
            parameters.add("%" + credencecheckFindDTO.getSummary().trim() + "%");
          }
          if (credencecheckFindDTO.getOffice().trim() != null
              && !credencecheckFindDTO.getOffice().trim().equals("")
              && "全部".equals(credencecheckFindDTO.getOffice())) {

            criterion += " fn201.office in ( ";
            List officeList = securityInfo.getOfficeList();
            OfficeDto officedto = null;
            Iterator itr1 = officeList.iterator();
            while (itr1.hasNext()) {
              officedto = (OfficeDto) itr1.next();
              criterion += " '" + officedto.getOfficeCode() + "',";
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += " ) and ";

          } else if (credencecheckFindDTO.getOffice().trim() != null
              && !credencecheckFindDTO.getOffice().trim().equals("")) {
            criterion += " fn201.office = ? and ";
            parameters.add(credencecheckFindDTO.getOffice().trim());
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String having = "";
          if (credencecheckFindDTO.getMoneySt().trim() != null
              && !credencecheckFindDTO.getMoneySt().trim().equals("")
              && credencecheckFindDTO.getMoneyEnd().trim() != null
              && !credencecheckFindDTO.getMoneyEnd().trim().equals("")) {// 有开始金额结束金额
            having = " having sum(fn201.debit) >=? and sum(fn201.debit) <=? ";
            parameters.add(credencecheckFindDTO.getMoneySt());
            parameters.add(credencecheckFindDTO.getMoneyEnd());
          }
          if (credencecheckFindDTO.getMoneySt().trim() != null
              && !credencecheckFindDTO.getMoneySt().trim().equals("")
              && (credencecheckFindDTO.getMoneyEnd().trim() == null || credencecheckFindDTO
                  .getMoneyEnd().trim().equals(""))) {// 有开始金额无结束金额
            having = " having sum(fn201.debit) >= ? ";
            parameters.add(credencecheckFindDTO.getMoneySt());
          }
          if (credencecheckFindDTO.getMoneyEnd().trim() != null
              && !credencecheckFindDTO.getMoneyEnd().trim().equals("")
              && (credencecheckFindDTO.getMoneySt().trim() == null || credencecheckFindDTO
                  .getMoneySt().trim().equals(""))) {// 无开始金额有结束金额
            having = " having sum(fn201.debit) <= ? ";
            parameters.add(credencecheckFindDTO.getMoneyEnd());
          }
          hql = hql + criterion
              + " group by fn201.credence_date,fn201.credence_num"
              + having
              + " order by fn201.credence_date,to_number(fn201.credence_num) ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List queryList = query.list();
          Iterator it = queryList.iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CredencecheckShowListDTO credencecheckShowListDTO = new CredencecheckShowListDTO();
            if (obj[0] != null) {
              credencecheckShowListDTO.setCredenceId(obj[0].toString());
            }
            if (obj[1] != null) {
              credencecheckShowListDTO
                  .setCredenceDate(obj[1].toString());
            }
            if (obj[2] != null) {
              credencecheckShowListDTO.setCredenceNum(obj[2].toString());
            }
            if (obj[3] != null) {
              credencecheckShowListDTO.setDebit(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              credencecheckShowListDTO.setCredit(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              credencecheckShowListDTO.setMakeBill(obj[5].toString());
            }
            if (obj[6] != null) {
              credencecheckShowListDTO.setCheckpsn(obj[6].toString());
            }
            if (obj[7] != null) {
              credencecheckShowListDTO.setCredenceSt(obj[7].toString());
            }
            if (obj[8] != null) {
              credencecheckShowListDTO.setOffice(obj[8].toString());
            }
            temp_list.add(credencecheckShowListDTO);
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
   * 凭证审核-复核
   * 
   * @author wsh 判断该要复核的记录在凭证表 FN201中的凭证状态CREDENCE_ST是否=0.确认 2007-10-29
   * @param id fn201主键
   * @param securityInfo 权限
   * @return CredencecheckModifyDTO
   */
  public CredencecheckModifyDTO findcredencecheckCheck(final String id,
      final String bookId, final String credenceSt) {
    CredencecheckModifyDTO credencecheckModifyDTO = (CredencecheckModifyDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.credence_num credencenum,"
                + " a.credence_date credencedate,"
                + " a.office office,"
                + " a.makebill"
                + " from fn201 a where a.credence_id=? and a.credence_st=? and a.book_id=?";
            Object[] obj = null;
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null) {
              parameters.add(id);
            }
            if (credenceSt != null) {
              parameters.add(credenceSt);
            }
            if (bookId != null) {
              parameters.add(bookId);
            }
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            obj = (Object[]) query.uniqueResult();
            CredencecheckModifyDTO tempCredencecheckModifyDTO = null;
            if (obj != null) {
              tempCredencecheckModifyDTO = new CredencecheckModifyDTO();
              if (obj[0] != null) {
                tempCredencecheckModifyDTO.setCredenceNum((obj[0].toString()));
              }
              if (obj[1] != null) {
                tempCredencecheckModifyDTO.setCredenceDate(obj[1].toString());
              }
              if (obj[2] != null) {
                tempCredencecheckModifyDTO.setOffice((obj[2].toString()));
              }
              if (obj[3] != null) {
                tempCredencecheckModifyDTO.setMakeBill((obj[3].toString()));
              }
            }
            return tempCredencecheckModifyDTO;
          }
        });
    return credencecheckModifyDTO;
  }

  /**
   * 查询一个凭证号的借贷方是否相等
   * 
   * @author wangshuang(1: 平 2: 不平)
   * @param credenceId
   * @param bookId
   * @param credenceSt
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public String checkIsEvaluate(final String credenceNum,
      final String credenceDate, final String bookId, final String credenceSt) {
    String flag = "";
    try {
      flag = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select nvl(sum(t.debit),0) - nvl(sum(t.credit),0) from fn201 t "
              + " where t.book_id= ? and t.credence_st = ? "
              + " and t.credence_num = ? and t.credence_date = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, credenceSt);
          query.setParameter(2, credenceNum);
          query.setParameter(3, credenceDate);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return flag;
  }

  /**
   * author wsh 凭证审核 查询条件是bookId,credenceNum,credenceDate,office
   * 
   * @param credenceNum 凭证号
   * @param credenceDate 凭证日期
   * @param office 办事处
   * @param securityInfo 权限
   * @2007-10-29
   * @return
   */
  public void updatecredencecheck_wsh(final String credenceSt,
      final String credenceNum, final String credenceDate,
      final String office, final SecurityInfo securityInfo,
      final String userName) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update AccountantCredence accountantCredence set accountantCredence.credenceSt=?,accountantCredence.checkpsn= ?  "
              + "where accountantCredence.credenceNum=? "
              + "and accountantCredence.credenceDate=? "
              + "and accountantCredence.bookId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, credenceSt);
          query.setString(1, userName);
          query.setString(2, credenceNum);
          query.setString(3, credenceDate);
          query.setString(4, securityInfo.getBookId());
          if (office != null && !"".equals(office)) {
            hql += "and accountantCredence.office=?";
            query.setString(5, office);
          }
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updatecredencecheck_wsh_yg(final String credenceSt,
      final String credenceNum, final String credenceDate,
      final String office, final SecurityInfo securityInfo,
      final String userName, final String a) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update AccountantCredence accountantCredence set accountantCredence.credenceSt=?,accountantCredence.checkpsn= ?,accountantCredence.oldCredenceNum=?  "
              + "where accountantCredence.credenceNum=? "
              + "and accountantCredence.credenceDate=? "
              + "and accountantCredence.bookId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, credenceSt);
          query.setString(1, userName);
          query.setString(2, a);
          query.setString(3, credenceNum);
          query.setString(4, credenceDate);
          query.setString(5, securityInfo.getBookId());
          if(office != null) {
            hql += "and accountantCredence.office=? ";
            query.setString(6, office);
          }
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 凭证检查
   * 
   * @author wsh 2007-10-30 查找FN201表中记录数量list，
   */
  public List queryCredenceInspectionCountList(
      final CredenceInspectionFindDTO credenceInspectionFindDTO,
      final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.debit debit,a.credit credit from fn201 a where a.book_id = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          parameters.add(securityInfo.getBookId());
          if (credenceInspectionFindDTO.getCredenceDateSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateSt().trim().equals(
                  "")
              && credenceInspectionFindDTO.getCredenceDateEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateEnd().trim().equals(
                  "")) {// 有开始日期结束日期
            criterion += " a.credence_date >=? and a.credence_date <=? and ";
            parameters
                .add(credenceInspectionFindDTO.getCredenceDateSt().trim());
            parameters.add(credenceInspectionFindDTO.getCredenceDateEnd()
                .trim());
          }
          if (credenceInspectionFindDTO.getCredenceDateSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateSt().trim().equals(
                  "")
              && (credenceInspectionFindDTO.getCredenceDateEnd().trim() == null || credenceInspectionFindDTO
                  .getCredenceDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " a.credence_date >= ? and ";
            parameters
                .add(credenceInspectionFindDTO.getCredenceDateSt().trim());
          }
          if (credenceInspectionFindDTO.getCredenceDateEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateEnd().trim().equals(
                  "")
              && (credenceInspectionFindDTO.getCredenceDateSt().trim() == null || credenceInspectionFindDTO
                  .getCredenceDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " a.credence_date <= ? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceDateEnd()
                .trim());
          }
          if (credenceInspectionFindDTO.getCredenceNumSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumSt().trim()
                  .equals("")
              && credenceInspectionFindDTO.getCredenceNumEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumEnd().trim().equals(
                  "")) {// 有开始日期结束日期
            criterion += " a.credence_num >=? and a.credence_num <=? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceNumSt().trim());
            parameters
                .add(credenceInspectionFindDTO.getCredenceNumEnd().trim());
          }
          if (credenceInspectionFindDTO.getCredenceNumSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumSt().trim()
                  .equals("")
              && (credenceInspectionFindDTO.getCredenceNumEnd().trim() == null || credenceInspectionFindDTO
                  .getCredenceNumEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " a.credence_num >= ? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceNumSt().trim());
          }
          if (credenceInspectionFindDTO.getCredenceNumEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumEnd().trim().equals(
                  "")
              && (credenceInspectionFindDTO.getCredenceNumSt().trim() == null || credenceInspectionFindDTO
                  .getCredenceNumSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " a.credence_num <= ? and ";
            parameters
                .add(credenceInspectionFindDTO.getCredenceNumEnd().trim());
          }
          if (credenceInspectionFindDTO.getCredenceCharacter().trim() != null
              && !credenceInspectionFindDTO.getCredenceCharacter().trim()
                  .equals("")) {
            criterion += " a.credence_character = ? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceCharacter()
                .trim());
          }
          if (credenceInspectionFindDTO.getOffice().trim() != null
              && !credenceInspectionFindDTO.getOffice().trim().equals("")) {
            criterion += " a.office = ? and ";
            parameters.add(credenceInspectionFindDTO.getOffice().trim());
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + "  and a.credence_date>(select f101.use_yearmonth from fn101 f101 where f101.book_id="
              + securityInfo.getBookId() + ")";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List queryList = query.list();
          Iterator it = queryList.iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              CredenceInspectionShowDTO credenceInspectionShowDTO = new CredenceInspectionShowDTO();
              if (obj[0] != null) {
                credenceInspectionShowDTO.setDebit(new BigDecimal(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                credenceInspectionShowDTO.setCredit(new BigDecimal(obj[1]
                    .toString()));
              }
              temp_list.add(credenceInspectionShowDTO);
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
   * 查询科目期初余额
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryBgnblanceBySubjectcode_WL(final String subjectcode,
      final String year, final String starperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String yearmonth = "";
              String hql = " select sum(accountantCredence.debit)-sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                // System.out.println("subjectcode:"+subjectcode);
                criterion += " accountantCredence.subjectCode = ?  and ";
                parameters.add(subjectcode);
              }
              if (starperiod != null && !starperiod.equals("")) {
                yearmonth = year.concat(starperiod).concat("01");
                // System.out.println("yearmonth:"+yearmonth);
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(yearmonth);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    if (value == null) {
      return new BigDecimal(0.00);
    } else {
      return value;
    }
  }

  public BigDecimal queryBgnblanceBySubjectcode_WS(final String subjectcode,
      final String startDate, final String office,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select sum(accountantCredence.debit)-sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode = ?  and ";
                parameters.add(subjectcode);
              }
              if (startDate != null && !startDate.equals("")) {
                criterion += " substr(accountantCredence.credenceDate,0,6) < ? and ";
                parameters.add(startDate);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    if (value == null) {
      return new BigDecimal(0.00);
    } else {
      return value;
    }
  }

  /**
   * 查询某一科目的明细账列表
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryListaccListBySubjectcode_WL(final String subjectcode,
      final String year, final String starperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String bngyearmonth = "";
          String endyearmonth = "";
          String hql = " select to_date(accountantCredence.credenceDate,'yyyy-mm-dd'),accountantCredence.credenceCharacter,accountantCredence.credenceNum,accountantCredence.summay,accountantCredence.debit,accountantCredence.credit,accountantCredence.settType,accountantCredence.office,accountantCredence.settDate "
              + " from AccountantCredence accountantCredence  ";
          Vector parameters = new Vector();
          String criterion = "";

          criterion = " where accountantCredence.bookId='"
              + securityInfo.getBookId()
              + "' and accountantCredence.credenceSt='2' and ";

          if (subjectcode != null && !subjectcode.equals("")) {
            criterion += " accountantCredence.subjectCode = ?  and ";
            parameters.add(subjectcode);
          }
          if ((starperiod != null && !starperiod.equals(""))
              && (endperiod != null && !endperiod.equals(""))) {
            if (starperiod != null && !starperiod.equals("")) {
              bngyearmonth = year.concat(starperiod).concat("01");
              criterion += " accountantCredence.credenceDate >= ? and ";
              parameters.add(bngyearmonth);
            }
            if (endperiod != null && !endperiod.equals("")) {
              if (endperiod.equals("12")) {
                endyearmonth = ("" + (Integer.parseInt(year) + 1))
                    .concat("0101");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else if (endperiod.equals("09") || endperiod.equals("10")
                  || endperiod.equals("11")) {
                endyearmonth = year.concat(
                    "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else {
                endyearmonth = year.concat(
                    "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              }
            }
          }
          if (office != null && !office.equals("")) {
            criterion += " accountantCredence.office = ? and ";
            parameters.add(office);
          } else {
            criterion += " accountantCredence.office in ( ";
            List officeList = securityInfo.getOfficeList();
            OfficeDto officedto = null;
            Iterator itr1 = officeList.iterator();
            while (itr1.hasNext()) {
              officedto = (OfficeDto) itr1.next();
              criterion += " '" + officedto.getOfficeCode() + "',";
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += " ) and ";
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion
              + " order by accountantCredence.credenceDate asc";

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          ListaccDTO listaccDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            listaccDTO = new ListaccDTO();
            listaccDTO.setCredenceDate(obj[0].toString());
            if (obj[1] == null) {
              listaccDTO.setCredenceCharacter("");
            } else {
              listaccDTO.setCredenceCharacter(obj[1].toString());
            }
            if (obj[2] == null) {
              listaccDTO.setCredenceNum("");
            } else {
              listaccDTO.setCredenceNum(obj[2].toString());
            }
            if (obj[3] == null) {
              listaccDTO.setSummay("");
            } else {
              listaccDTO.setSummay(obj[3].toString());
            }
            if (obj[4] == null) {
              listaccDTO.setCredit(new BigDecimal(0.00));
            } else {
              listaccDTO.setDebit(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] == null) {
              listaccDTO.setCredit(new BigDecimal(0.00));
            } else {
              listaccDTO.setCredit(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] == null) {
              listaccDTO.setSettType("");
            } else {
              listaccDTO.setSettType(obj[6].toString());
            }
            if (obj[7] == null) {
              listaccDTO.setOffice("");
            } else {
              listaccDTO.setOffice(obj[7].toString());
            }
            if (obj[8] == null) {
              listaccDTO.setSettDate("");
            } else {
              listaccDTO.setSettDate(obj[8].toString());
            }

            tableList.add(listaccDTO);
          }

          return tableList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 获得对方科目代码
   * 
   * @param subjectcode
   * @param credenceNum
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public String querySubjectCode_WL(final String credenceNum,
      final String criterion1, final SecurityInfo securityInfo,
      final String office, final String settdate) throws NumberFormatException,
      Exception {
    String subjectcode = "";
    try {

      subjectcode = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select accountantCredence.subjectCode from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (credenceNum != null && !credenceNum.equals("")) {
                criterion += " accountantCredence.credenceNum = ? and ";
                parameters.add(credenceNum);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              }
              if (settdate != null && !settdate.equals("")) {
                criterion += " accountantCredence.settDate = ? and ";
                parameters.add(settdate);

              }
              criterion += criterion1;

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              if (query.list().size() == 0) {
                return "";
              } else {
                return query.list().get(0);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return subjectcode;
  }

  /**
   * 获得当月最后一天的日期数
   * 
   * @param datecode
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public String findLastDay_WL(final String datecode)
      throws NumberFormatException, Exception {
    String lastday = "";
    try {
      lastday = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select LAST_DAY(to_date(?,'yyyy-mm-dd')) from dual";

              Query query = session.createSQLQuery(hql);
              query.setString(0, datecode);

              return query.uniqueResult().toString();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    lastday = lastday.substring(lastday.lastIndexOf("-") + 1);
    return lastday;
  }

  /**
   * 获得本日合计（借方）
   * 
   * @param subjectcode
   * @param daysum
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryDaylendmnt_WL(final String subjectcode,
      final String daysum, final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select sum(accountantCredence.debit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";
              String changed = "";
              try {
                SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat to = new SimpleDateFormat("yyyyMMdd");
                Date temp = from.parse(daysum);
                changed = to.format(temp);
              } catch (Exception e) {
                e.printStackTrace();
              }

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if (daysum != null && !daysum.equals("")) {
                criterion += " accountantCredence.credenceDate =? and ";
                parameters.add(changed);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * 获得本日合计（贷方）
   * 
   * @param subjectcode
   * @param daysum
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryDayloanmnt_WL(final String subjectcode,
      final String daysum, final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";
              String changed = "";
              try {
                SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat to = new SimpleDateFormat("yyyyMMdd");
                Date temp = from.parse(daysum);
                changed = to.format(temp);
              } catch (Exception e) {
                e.printStackTrace();
              }

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if (daysum != null && !daysum.equals("")) {
                criterion += " accountantCredence.credenceDate =? and ";
                parameters.add(changed);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    return value;
  }

  /**
   * author wsh 凭证过账 判断要过账的记录在凭证表 FN201中的凭证状态CREDENCE_ST是否=1.复核
   * 
   * @param id fn201主键
   * @return
   */
  public Integer is_CodeIn_wsh(final String id) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.credence_id) from fn201 a ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null && !id.equals("")) {
              criterion += "a.credence_id = ?  and a.credence_st='1' and ";
              parameters.add(id);
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
   * author wsh 凭证过账 判断要过账的凭证记录在凭证表 FN201中的摘要SUMMAY是否存在=2（自动损益结转）
   * 
   * @param id fn201主键
   * @return
   */
  public CredenceclearModifyDTO findCredenceclearSummary(final String id) {
    CredenceclearModifyDTO credenceclearModifyDTO = (CredenceclearModifyDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.summay summay,"
                + "a.subject_code subjectCode," + "a.office office,"
                + "a.credence_num credencenum,"
                + "a.credence_date credencedate " + "from fn201 a ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null && !id.equals("")) {
              criterion += "a.credence_id = ? and ";
              parameters.add(id);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            CredenceclearModifyDTO tempCredenceclearModifyDTO = null;
            if (obj != null) {
              tempCredenceclearModifyDTO = new CredenceclearModifyDTO();
              if (obj[0] != null) {
                tempCredenceclearModifyDTO.setSummary((obj[0].toString()));
              }
              if (obj[1] != null) {
                tempCredenceclearModifyDTO.setSubjectCode(obj[1].toString());
              }
              if (obj[2] != null) {
                tempCredenceclearModifyDTO.setOffice((obj[2].toString()));
              }
              if (obj[3] != null) {
                tempCredenceclearModifyDTO.setCredenceNum((obj[3].toString()));
              }
              if (obj[4] != null) {
                tempCredenceclearModifyDTO.setCredenceDate(obj[4].toString());
              }

            }
            return tempCredenceclearModifyDTO;
          }
        });
    return credenceclearModifyDTO;
  }

  /**
   * author wsh 凭证过账_凭证过账_过账 更新fn201 损益状态=1.已结转未记账为2.已记账
   * 查询条件是bookId,bizType,moneyType,subjectCode
   * 
   * @param subjectCode 科目代码
   * @param office 办事处
   * @param securityInfo 权限
   * @2007-10-24
   * @return
   * @throws BusinessException
   */
  public void updateCredenceclear_wsh(final String subjectCode,
      final String office, final SecurityInfo securityInfo)
      throws BusinessException {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update AccountantCredence accountantCredence set accountantCredence.incDecSt='2',accountantCredence.clearpsn= ? "
              + "where accountantCredence.subjectCode=? "
              + "and accountantCredence.office=? "
              + "and accountantCredence.bookId=? "
              + "and accountantCredence.incDecSt='1'";
          Query query = session.createQuery(hql);
          query.setString(0, securityInfo.getUserName());
          query.setString(1, subjectCode);
          query.setString(2, office);
          query.setString(3, securityInfo.getBookId());
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("过账失败");
    }
  }

  /**
   * author wsh 凭证过账_凭证过账_过账 更新凭证表 FN201表与其相同凭证号、凭证日期、办事处的记录的凭证状态=2.过账
   * 查询条件是bookId,bizType,moneyType,subjectCode
   * 
   * @param subjectCode 科目代码
   * @param office 办事处
   * @param securityInfo 权限
   * @2007-10-24
   * @return
   */
  public void updateCredenceclear1_wsh(final String credenceNum,
      final String credenceDate, final String office,
      final SecurityInfo securityInfo) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update AccountantCredence accountantCredence set accountantCredence.credenceSt='2',accountantCredence.clearpsn= ?  "
              + "where accountantCredence.credenceNum=? "
              + "and accountantCredence.credenceDate=? "
              + "and accountantCredence.bookId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, securityInfo.getUserName());
          query.setString(1, credenceNum);
          query.setString(2, credenceDate);
          query.setString(3, securityInfo.getBookId());
          if(office != null && !"".equals(office)) {
            hql += "and accountantCredence.office=? ";
            query.setString(4, office);
          }
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      throw new BusinessException("过账失败");
    }
  }

  /**
   * 凭证过账-划账
   * 
   * @author wsh 求fn201中办事处
   * @param id fn201 主键
   * @return Integer
   */
  public String findCredenceclearOffice(final String id) {
    String office = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.office from fn201 a where a.credence_id=? ";
            Object obj = null;
            String temp_office = "";
            Vector parameters = new Vector();
            if (id != null) {
              parameters.add(id);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            obj = (Object) query.uniqueResult();
            if (obj != null) {
              temp_office = obj.toString();
            }
            return temp_office;
          }
        });
    return office;
  }

  /**
   * 查询处将要删除的凭证信息
   * 
   * @param credenceId FN201表的 主键
   * @return
   * @author 付云峰
   */
  public DelectCredenceInfoDTO queryDelectCredenceInfo(final String credenceId,
      final String bookId) {
    DelectCredenceInfoDTO delectCredenceInfoDTO = null;
    try {
      delectCredenceInfoDTO = (DelectCredenceInfoDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select f201.credence_id," + "f201.summay,"
                  + "f201.sett_num," + "f201.credence_st,"
                  + "f201.subject_code," + "f201.office,"
                  + "f201.credence_date," + "f201.credence_num,"
                  + "f201.reserve_a," + "f201.reserve_b, " + "f201.reserve_c "
                  + "from fn201 f201 "
                  + "where f201.credence_id=? and f201.book_id=?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, new Integer(credenceId));
              query.setParameter(1, bookId);
              DelectCredenceInfoDTO temp_delectCredenceInfoDTO = null;
              if (query.uniqueResult() != null) {
                temp_delectCredenceInfoDTO = new DelectCredenceInfoDTO();
                Object[] obj = (Object[]) query.uniqueResult();
                if (obj[0] != null) {
                  temp_delectCredenceInfoDTO.setCredenceId(obj[0].toString());
                }
                if (obj[1] != null) {
                  temp_delectCredenceInfoDTO.setSummay(obj[1].toString());
                }
                if (obj[2] != null) {
                  temp_delectCredenceInfoDTO.setSettNum(obj[2].toString());
                }
                if (obj[3] != null) {
                  temp_delectCredenceInfoDTO.setCredenceSt(obj[3].toString());
                }
                if (obj[4] != null) {
                  temp_delectCredenceInfoDTO.setSubjectCode(obj[4].toString());
                }
                if (obj[5] != null) {
                  temp_delectCredenceInfoDTO.setOffice(obj[5].toString());
                }
                if (obj[6] != null) {
                  temp_delectCredenceInfoDTO.setCredenceDate(obj[6].toString());
                }
                if (obj[7] != null) {
                  temp_delectCredenceInfoDTO.setDocNum(obj[7].toString());
                }
                if (obj[8] != null) {
                  temp_delectCredenceInfoDTO.setBizType(obj[8].toString());
                }
                if (obj[9] != null) {
                  temp_delectCredenceInfoDTO.setReserveB(obj[9].toString());
                }
                if (obj[10] != null) {
                  temp_delectCredenceInfoDTO.setReserveC(obj[10].toString());
                }
              }
              return temp_delectCredenceInfoDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return delectCredenceInfoDTO;
  }

  /**
   * 判断是否为损益结转
   * 
   * @param bookId
   * @return
   * @author 付云峰
   */
  public Object isSettleIncAndDecInfo(final String bookId) {
    Object paraId = "";
    try {
      paraId = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select f102.para_id from fn102 f102 where f102.param_num='4' and f102.param_value='2' and f102.book_id=?";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, bookId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paraId;
  }

  /**
   * 在删除FN201时更新PL202
   * 
   * @param settNum 结算号
   * @author 付云峰
   */
  public void updateLoanFlowHead(final String settNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update LoanFlowHead loanFlowHead set loanFlowHead.isFinance=1 "
              + "where loanFlowHead.noteNum=? ";
          Query query = session.createQuery(hql);
          query.setString(0, settNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void updateLoanFlowHead_callback(final String settNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update LoanFlowHead loanFlowHead set loanFlowHead.isFinance=1 "
              + "where loanFlowHead.batchNum=? ";
          Query query = session.createQuery(hql);
          query.setString(0, settNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 在删除FN201时更新AA101
   * 
   * @param settNum 结算号
   * @author 付云峰
   */
  public void updateOrgHAFAccountFlow(final String settNum, final String type) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update OrgHAFAccountFlow orgHAFAccountFlow set orgHAFAccountFlow.isFinance = ? "
              + "where orgHAFAccountFlow.noteNum=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, "6".equals(type) ? "3" : "1");
          query.setParameter(1, settNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 在删除FN201业务类型为内部转出时更新AA101的方法
   * 
   * @param settNum 结算号
   * @author 付云峰
   */
  public void updateOrgHAFAccountFlow_E(final String settNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut set orgHAFAccountFlowTransOut.isFinance=1 "
              + "where orgHAFAccountFlowTransOut.interest=0 and orgHAFAccountFlowTransOut.noteNum=? and orgHAFAccountFlowTransOut.bizStatus=5 ";
          Query query = session.createQuery(hql);
          query.setString(0, settNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 在删除FN201业务类型为内部转入时更新AA101的方法
   * 
   * @param settNum 结算号
   */
  public void updateOrgHAFAccountFlow_F(final String settNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn set orgHAFAccountFlowTransIn.isFinance=1 "
              + "where (orgHAFAccountFlowTransIn.reserveaB is null or orgHAFAccountFlowTransIn.reserveaB='') "
              + "and orgHAFAccountFlowTransIn.noteNum=? and orgHAFAccountFlowTransIn.bizStatus=5";
          Query query = session.createQuery(hql);
          query.setString(0, settNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateOrgHAFAccountFlow_E1(final String settNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut set orgHAFAccountFlowTransOut.isFinance=1 "
              + "where orgHAFAccountFlowTransOut.noteNum=? and orgHAFAccountFlowTransOut.bizStatus=5 "
              + "and orgHAFAccountFlowTransOut.bizType = '转出'";
          Query query = session.createQuery(hql);
          query.setString(0, settNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateOrgHAFAccountFlow_F1(final String settNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn set orgHAFAccountFlowTransIn.isFinance=1 "
              + "where  orgHAFAccountFlowTransIn.noteNum=? and orgHAFAccountFlowTransIn.bizStatus=5"
              + "and orgHAFAccountFlowTransOut.bizType = '转入'";
          Query query = session.createQuery(hql);
          query.setString(0, settNum);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除FN201中的凭证
   * 
   * @param docNUm 凭证号
   * @param office 办事处
   * @param docNumDate 凭证日期
   */
  public void delectCredenceInfo_wsh(final String docNUm, final String office,
      final String docNumDate, final String bookId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete AccountantCredence accountantCredence where accountantCredence.credenceNum=? and accountantCredence.office=? and accountantCredence.credenceDate=? and accountantCredence.bookId=?";
          Query query = session.createQuery(sql);
          query.setString(0, docNUm);
          query.setString(1, office);
          query.setString(2, docNumDate);
          query.setString(3, bookId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除FN201中的凭证-内部转入转出业务类型使用的删除方法 办事处由备选字段B中获得
   * 
   * @param docNUm 凭证号
   * @param office 办事处
   * @param docNumDate 凭证日期
   */
  public void delectCredenceInfo_reserveB_wsh(final String docNum,
      final String office, final String docNumDate, final String bookId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete AccountantCredence accountantCredence where accountantCredence.credenceNum=? and accountantCredence.reserveB=? "
              + "and accountantCredence.credenceDate=? and accountantCredence.bookId = ?";
          Query query = session.createQuery(sql);
          query.setString(0, docNum);
          query.setString(1, office);
          query.setString(2, docNumDate);
          query.setString(3, bookId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 如果为损益结转，则更新对应FN201中的数据
   * 
   * @param subjectCode 科目代码
   * @param office 办事处
   * @param bookId bookId
   */
  public void updateSettleIncAndDecInfo(final String subjectCode,
      final String office, final String bookId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update AccountantCredence accountantCredence set accountantCredence.incDecSt=0"
              + "where accountantCredence.subjectCode=? "
              + "and accountantCredence.office=? "
              + "and accountantCredence.bookId=? "
              + "and accountantCredence.incDecSt=1";
          Query query = session.createQuery(sql);
          query.setString(0, subjectCode);
          query.setString(1, office);
          query.setString(2, bookId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询弹出框列表中的内容 付云峰
   * 
   * @param docNum 凭证号
   * @param bookId bookId
   * @return
   */
  public List queryCredencePopList(final String docNum, final String bookId,
      final String credenceDate, final String office) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f201.summay,f201.free_summay,f201.subject_code,f201.debit,f201.credit,f110.subject_name, "
              + "f201.sett_num from fn201 f201, fn110 f110 "
              + "where f201.subject_code = f110.subject_code "
              + "and f201.book_id = f110.book_id and f201.credence_num = ? "
              + "and f201.book_id = ? "
              + "and f201.credence_date = ? "
              + "order by f201.sett_num,f201.credence_id";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, docNum);
          query.setParameter(1, bookId);
          query.setParameter(2, credenceDate);

          Iterator it = query.list().iterator();
          CredencePopListDTO credencePopListDTO = null;
          List temp_credencePopListDTOList = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            credencePopListDTO = new CredencePopListDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              credencePopListDTO.setSummay(obj[0].toString());
            }
            if (obj[1] != null) {
              credencePopListDTO.setFreeSummay(obj[1].toString());
            }
            if (obj[2] != null) {
              credencePopListDTO.setSubjectCode(obj[2].toString());
            }
            if (obj[3] != null) {
              credencePopListDTO.setDebit(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              credencePopListDTO.setCredit(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              credencePopListDTO.setSubjectName(obj[5].toString());
            }
            if (obj[6] != null) {
              credencePopListDTO.setSettnum(obj[6].toString());
            }
            temp_credencePopListDTOList.add(credencePopListDTO);
          }

          return temp_credencePopListDTOList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * 查询出弹出框多条记录的共有信息
   * 
   * @param docNum 凭证号
   * @param bookId bookId 付云峰
   * @return
   */
  public CredencePopInfoDTO queryCredencePopInfo(final String docNum,
      final String bookId, final String credenceDate, final String office) {
    CredencePopInfoDTO credencePopInfoDTO = null;
    try {
      credencePopInfoDTO = (CredencePopInfoDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select "
                  + "max(case when f201.reserve_a = '2' then f201.reserve_b else f201.office end),"
                  + "max(f201.credence_character),"
                  + "max(f201.credence_num),"
                  + "max(f201.old_credence_num),"
                  + "max(f201.credence_date),"
                  + "max(f201.sett_num),"
                  + "max(f201.sett_date),"
                  + "max(f201.checkpsn),"
                  + "max(f201.clearpsn),"
                  + "max(f201.makebill),"
                  + "max(f201.accountpsn),"
                  + "max(f201.account_charge),"
                  + "max(f201.sett_type), "
                  + "max(f201.reserve_a),"
                  + "max(f201.reserve_b),"
                  + "max(f201.reserve_c)"
                  + "from fn201 f201 where f201.credence_num=? and f201.book_id=? and f201.credence_date=?";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, docNum);
              query.setParameter(1, bookId);
              query.setParameter(2, credenceDate);

              Object[] obj = (Object[]) query.uniqueResult();
              CredencePopInfoDTO credencePopInfoDTO = new CredencePopInfoDTO();
              if (obj[0] != null) {
                credencePopInfoDTO.setOffice(obj[0].toString());
              }
              if (obj[1] != null) {
                credencePopInfoDTO.setCredenceCharacter(obj[1].toString());
              }
              if (obj[2] != null) {
                credencePopInfoDTO.setCredenceNum(obj[2].toString());
              }
              if (obj[3] != null) {
                credencePopInfoDTO.setOldCredenceNum(obj[3].toString());
              }
              if (obj[4] != null) {
                credencePopInfoDTO.setCredenceDate(obj[4].toString());
              }
              if (obj[5] != null) {
                credencePopInfoDTO.setSettNum(obj[5].toString());
              }
              if (obj[6] != null) {
                credencePopInfoDTO.setSettDate(obj[6].toString());
              }
              if (obj[7] != null) {
                credencePopInfoDTO.setCheckpsn(obj[7].toString());
              }
              if (obj[8] != null) {
                credencePopInfoDTO.setClearpsn(obj[8].toString());
              }
              if (obj[9] != null) {
                credencePopInfoDTO.setMakebill(obj[9].toString());
              }
              if (obj[10] != null) {
                credencePopInfoDTO.setAccountpsn(obj[10].toString());
              }
              if (obj[11] != null) {
                credencePopInfoDTO.setAccountCharge(obj[11].toString());
              }
              if (obj[12] != null) {
                credencePopInfoDTO.setSettType(obj[12].toString());
              }
              if (obj[13] != null) {
                credencePopInfoDTO.setReserveA(obj[13].toString());
              }
              if (obj[14] != null) {
                credencePopInfoDTO.setReserveB(obj[14].toString());
              }
              if (obj[15] != null) {
                credencePopInfoDTO.setReserveC(obj[15].toString());
              }
              return credencePopInfoDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return credencePopInfoDTO;
  }

  /**
   * 根据凭证字查询说明说明
   * 
   * @param credenceCharacter 凭证字
   * @param bookId
   * @return
   */
  public Object queryParamExplain(final String credenceCharacter,
      final String bookId) {
    Object obj = null;
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f102.param_explain_explain "
              + "from fn102 f102 "
              + "where f102.param_num='2' and f102.book_id=? and f102.para_id=? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, credenceCharacter);

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * 查询fn201表里ID号最大的记录ID
   * 
   * @author 刘冰 2007-11-1 查询fn201表里ID号最大的记录ID 查询条件：officeCode所选办事处、bookId
   */
  public String queryMaxCredenceDateByOffice(final String officeCode,
      final String bookId)

  throws Exception {
    String credenceDate = null;
    try {
      credenceDate = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select max(f201.credence_date) from fn201 f201 ";
              Vector parameters = new Vector();
              String criterion = "";
              if (officeCode != null && !officeCode.equals("")) {
                criterion += " f201.office = ? and ";
                parameters.add(officeCode);
              }
              if (bookId != null && !bookId.equals("")) {
                criterion += " f201.book_id = ? and ";
                parameters.add(bookId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql = sql + criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return credenceDate;
  }

  /**
   * 查询fn201表里的记账日期
   * 
   * @author 刘冰 2007-11-1 查询fn201表里的记账日期 查询条件：credenceId
   */
  public String queryCredenceDateByOffice(final Integer credenceId)
      throws Exception {
    String chargeUpDate = "";
    try {
      chargeUpDate = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select accountantCredence.credenceDate from AccountantCredence accountantCredence where accountantCredence.credenceId = ?";
              Query query = session.createQuery(hql);
              query.setParameter(0, credenceId);
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return chargeUpDate;
  }

  /**
   * 查询借方余额与贷方余额的差
   * 
   * @param subjectcode
   * @author 刘冰
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryBanlanceDiff(final String subjectcode,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {
      // System.out.println(subjectcode);
      // System.out.println(office);
      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(accountantCredence.debit)-sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode = ?  and ";
                parameters.add(subjectcode);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    if (value == null) {
      return new BigDecimal(0.00);
    } else {
      return value;
    }
  }

  /**
   * 账簿管理--总账
   * 
   * @author wsh 2007-11-07 通过查询条件查出fn201中最大或最小的凭证号对应的凭证字
   */
  public String getDocumentNumberMinOrMax_wsh(final String type,
      final String officeCode, final String starperiod, final String endperiod,
      final String bookId, final String year, final SecurityInfo securityInfo,
      final String subjectCode) {
    String docNumMax = null;
    try {
      docNumMax = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select "
                  + type
                  + "(accountantCredence.credenceNum) from AccountantCredence accountantCredence  ";

              String criterion = "";
              String bngyearmonth = "";
              String endyearmonth = "";

              if (officeCode != null && !officeCode.equals("")) {
                criterion += " accountantCredence.office = '" + officeCode
                    + "' and accountantCredence.credenceSt='2' and ";

              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }
              if ((starperiod != null && !starperiod.equals(""))
                  && (endperiod != null && !endperiod.equals(""))) {
                if (starperiod != null && !starperiod.equals("")) {
                  bngyearmonth = year.concat(starperiod).concat("01");
                  criterion += " accountantCredence.credenceDate >= '"
                      + bngyearmonth + "' and ";

                }
                if (endperiod != null && !endperiod.equals("")) {
                  if (endperiod.equals("12")) {
                    endyearmonth = ("" + (Integer.parseInt(year) + 1))
                        .concat("0101");
                    criterion += " accountantCredence.credenceDate < '"
                        + endyearmonth + "' and ";
                  } else if (endperiod.equals("09") || endperiod.equals("10")
                      || endperiod.equals("11")) {
                    endyearmonth = year.concat(
                        "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < '"
                        + endyearmonth + "' and ";

                  } else {
                    endyearmonth = year.concat(
                        "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < '"
                        + endyearmonth + "' and ";

                  }
                }
              }
              if (bookId != null) {
                criterion += " accountantCredence.bookId = '" + bookId
                    + "' and ";
              }
              if (subjectCode != null) {
                // criterion += " accountantCredence.subjectCode =
                // '"+subjectCode+"' and ";
                criterion += " accountantCredence.subjectCode like '"
                    + subjectCode + '%' + "'  and ";
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              // String hql2="select b.credenceCharacter) from
              // AccountantCredence b where b.credenceNum in('"+hql+"') and
              // b.bookId='"+bookId+"' ";
              // String hql1="select a.paramExplain from BookParameter a where
              // a.paraId in('"+hql+"') and a.paramNum='2' ";
              Query query0 = session.createQuery(hql);
              String rs = (String) query0.uniqueResult();
              return rs;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return docNumMax;

  }

  /**
   * 账簿管理--总账
   * 
   * @author wsh 2007-11-07 通过查询条件查出fn201中最大或最小的凭证号对应的凭证字
   */
  public String getDocumentCharacterrMinOrMax_wsh(final String credenceNum,
      final String officeCode, final String starperiod, final String endperiod,
      final String bookId, final String year, final SecurityInfo securityInfo,
      final String subjectCode) {
    String docNumMax = null;
    try {
      docNumMax = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select accountantCredence.credenceCharacter from AccountantCredence accountantCredence ";

              String criterion = "";
              String bngyearmonth = "";
              String endyearmonth = "";

              if (credenceNum != null) {
                criterion += " accountantCredence.credenceNum = '"
                    + credenceNum + "' and ";
              }
              if (officeCode != null && !officeCode.equals("")) {
                criterion += " accountantCredence.office = '" + officeCode
                    + "' and ";

              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }
              if ((starperiod != null && !starperiod.equals(""))
                  && (endperiod != null && !endperiod.equals(""))) {
                if (starperiod != null && !starperiod.equals("")) {
                  bngyearmonth = year.concat(starperiod).concat("01");
                  criterion += " accountantCredence.credenceDate >= '"
                      + bngyearmonth + "' and ";

                }
                if (endperiod != null && !endperiod.equals("")) {
                  if (endperiod.equals("12")) {
                    endyearmonth = ("" + (Integer.parseInt(year) + 1))
                        .concat("0101");
                    criterion += " accountantCredence.credenceDate < '"
                        + endyearmonth + "' and ";
                  } else if (endperiod.equals("09") || endperiod.equals("10")
                      || endperiod.equals("11")) {
                    endyearmonth = year.concat(
                        "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < '"
                        + endyearmonth + "' and ";

                  } else {
                    endyearmonth = year.concat(
                        "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                    criterion += " accountantCredence.credenceDate < '"
                        + endyearmonth + "' and ";

                  }
                }
              }
              if (bookId != null) {
                criterion += " accountantCredence.bookId = '" + bookId
                    + "' and ";
              }
              if (subjectCode != null) {
                // criterion += " accountantCredence.subjectCode =
                // '"+subjectCode+"' and ";
                //              
                criterion += " accountantCredence.subjectCode like '"
                    + subjectCode + '%' + "'  and ";

              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              // String hql2="select b.credenceCharacter) from
              // AccountantCredence b where b.credenceNum in('"+hql+"') and
              // b.bookId='"+bookId+"' ";
              // String hql1="select a.paramExplain from BookParameter a where
              // a.paraId in('"+hql+"') and a.paramNum='2' ";
              Query query0 = session.createQuery(hql);
              String rs = (String) query0.list().get(0);
              return rs;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return docNumMax;

  }

  /**
   * 查询某一科目的明细账列表
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public Integer findSubjectcode_Wsh(final String subjectcode,
      final String year, final String starperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    Integer count = new Integer(0);
    try {

      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String bngyearmonth = "";
          String endyearmonth = "";
          String hql = " select count(accountantCredence.subjectCode) from AccountantCredence accountantCredence  ";
          Vector parameters = new Vector();
          String criterion = "";

          criterion = " where accountantCredence.bookId='"
              + securityInfo.getBookId()
              + "' and accountantCredence.credenceSt='2' and ";

          if (subjectcode != null && !subjectcode.equals("")) {
            criterion += " accountantCredence.subjectCode = ?  and ";
            parameters.add(subjectcode);
          }
          if ((starperiod != null && !starperiod.equals(""))
              && (endperiod != null && !endperiod.equals(""))) {
            if (starperiod != null && !starperiod.equals("")) {
              bngyearmonth = year.concat(starperiod).concat("01");
              criterion += " accountantCredence.credenceDate >= ? and ";
              parameters.add(bngyearmonth);
            }
            if (endperiod != null && !endperiod.equals("")) {
              if (endperiod.equals("12")) {
                endyearmonth = ("" + (Integer.parseInt(year) + 1))
                    .concat("0101");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else if (endperiod.equals("09") || endperiod.equals("10")
                  || endperiod.equals("11")) {
                endyearmonth = year.concat(
                    "" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              } else {
                endyearmonth = year.concat(
                    "0" + (Integer.parseInt(endperiod) + 1)).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(endyearmonth);
              }
            }
          }
          if (office != null && !office.equals("")) {
            criterion += " accountantCredence.office = ? and ";
            parameters.add(office);
          } else {
            criterion += " accountantCredence.office in ( ";
            List officeList = securityInfo.getOfficeList();
            OfficeDto officedto = null;
            Iterator itr1 = officeList.iterator();
            while (itr1.hasNext()) {
              officedto = (OfficeDto) itr1.next();
              criterion += " '" + officedto.getOfficeCode() + "',";
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += " ) and ";
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.uniqueResult().toString();
          return new Integer(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 查询单位流水账
   * 
   * @param officecode
   * @param bankcode
   * @param orgcode
   * @param orgname
   * @param noteNum
   * @param docNum
   * @param bsstatus
   * @param bstype
   * @param setmonthstart
   * @param setmonthend
   * @param setmoneystart
   * @param setmoneyend
   * @param setpeopcountstart
   * @param setpeopcountend
   * @param setdirection
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   * @author 付云峰
   */
  public List findOrgFlowListByCriterions_FYF(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String noteNum, final String docNum, final String bsstatus,
      final String bstype, final String setmonthstart,
      final String setmonthend, final String setmoneystart,
      final String setmoneyend, final String setpeopcountstart,
      final String setpeopcountend, final String setdirection,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.officecode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(bankcode);
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += " orgHAFAccountFlow.noteNum in(";
            String[] temp_noteNum = noteNum.split(",");
            for (int i = 0; i < temp_noteNum.length; i++) {
              criterion += " ? , ";
              parameters.add(temp_noteNum[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","))
                + ") and ";
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += " orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }
          if (bsstatus != null && !bsstatus.equals("")) {
            criterion += " orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(bsstatus));
          }
          if (bstype != null && !bstype.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bstype);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += "(orgHAFAccountFlow.settDate between ?  and  ? ) and ";
            parameters.add(setmonthstart);
            parameters.add(setmonthend);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && (setmonthend == null || setmonthend.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthstart);
          }

          if (setmonthend != null && !setmonthend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthend);
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && setmoneyend != null && !setmoneyend.equals("")) {
            criterion += "(orgHAFAccountFlow.moneyTotal between ?  and  ? ) and ";
            parameters.add(new BigDecimal(setmoneystart));
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && (setmoneyend == null || setmoneyend.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal  = ? and ";
            parameters.add(new BigDecimal(setmoneystart));
          }

          if (setmoneyend != null && !setmoneyend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal = ? and ";
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && setpeopcountend != null && !setpeopcountend.equals("")) {
            criterion += "(orgHAFAccountFlow.personTotal between ?  and  ? ) and ";
            parameters.add(new Integer(setpeopcountstart));
            parameters.add(new Integer(setpeopcountend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && (setpeopcountend == null || setpeopcountend.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal  = ? and ";
            parameters.add(new Integer(setpeopcountstart));
          }

          if (setpeopcountend != null && !setpeopcountend.equals("")
              && (setpeopcountstart == null || setpeopcountstart.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal = ? and ";
            parameters.add(new Integer(setpeopcountend));
          }

          if (setdirection != null && !setdirection.equals("")) {
            if (setdirection.equals("1")) {
              criterion += " orgHAFAccountFlow.debit_wl > 0 and ";
            } else if (setdirection.equals("2")) {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and ";
            } else {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and orgHAFAccountFlow.debit_wl > 0 and ";
            }
          }
          String ob = orderBy;
          if (ob == null)
            ob = " orgHAFAccountFlow.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + "order by " + ob + " " + od;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList = query.list();
          }
          return queryList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询单位流水账的count
   * 
   * @param officecode
   * @param bankcode
   * @param orgcode
   * @param orgname
   * @param noteNum
   * @param docNum
   * @param bsstatus
   * @param bstype
   * @param setmonthstart
   * @param setmonthend
   * @param setmoneystart
   * @param setmoneyend
   * @param setpeopcountstart
   * @param setpeopcountend
   * @param setdirection
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   * @author 付云峰
   */
  public List findOrgFlowListCountByCriterions_FYF(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String noteNum, final String docNum, final String bsstatus,
      final String bstype, final String setmonthstart,
      final String setmonthend, final String setmoneystart,
      final String setmoneyend, final String setpeopcountstart,
      final String setpeopcountend, final String setdirection,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.officecode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(bankcode);
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += " orgHAFAccountFlow.noteNum in(";
            String[] temp_noteNum = noteNum.split(",");
            for (int i = 0; i < temp_noteNum.length; i++) {
              criterion += " ? , ";
              parameters.add(temp_noteNum[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","))
                + ") and ";
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += " orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }
          if (bsstatus != null && !bsstatus.equals("")) {
            criterion += " orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(bsstatus));
          }
          if (bstype != null && !bstype.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bstype);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += "(orgHAFAccountFlow.settDate between ?  and  ? ) and ";
            parameters.add(setmonthstart);
            parameters.add(setmonthend);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && (setmonthend == null || setmonthend.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthstart);
          }

          if (setmonthend != null && !setmonthend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthend);
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && setmoneyend != null && !setmoneyend.equals("")) {
            criterion += "(orgHAFAccountFlow.moneyTotal between ?  and  ? ) and ";
            parameters.add(new BigDecimal(setmoneystart));
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && (setmoneyend == null || setmoneyend.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal  = ? and ";
            parameters.add(new BigDecimal(setmoneystart));
          }

          if (setmoneyend != null && !setmoneyend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal = ? and ";
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && setpeopcountend != null && !setpeopcountend.equals("")) {
            criterion += "(orgHAFAccountFlow.personTotal between ?  and  ? ) and ";
            parameters.add(new Integer(setpeopcountstart));
            parameters.add(new Integer(setpeopcountend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && (setpeopcountend == null || setpeopcountend.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal  = ? and ";
            parameters.add(new Integer(setpeopcountstart));
          }

          if (setpeopcountend != null && !setpeopcountend.equals("")
              && (setpeopcountstart == null || setpeopcountstart.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal = ? and ";
            parameters.add(new Integer(setpeopcountend));
          }

          if (setdirection != null && !setdirection.equals("")) {
            if (setdirection.equals("1")) {
              criterion += " orgHAFAccountFlow.debit_wl > 0 and ";
            } else if (setdirection.equals("2")) {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and ";
            } else {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and orgHAFAccountFlow.debit_wl > 0 and ";
            }
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          session.clear();
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

  /**
   * 判断损益结转将要更新的内容
   * 
   * @param bookId bookid
   * @param docNum 凭证号
   * @return 将要修改的科目代码
   */
  public List isUpdateSettleIncAndDecInfo(final String bookId,
      final String docNum) {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f110.subject_code "
              + "from fn110 f110 "
              + "where f110.subject_code in (select f201.subject_code from fn201 f201 where f201.credence_num = ? and f201.book_id = ?) "
              + "and f110.book_id=? and f110.subject_sort_code=4";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, docNum);
          query.setParameter(1, bookId);
          query.setParameter(2, bookId);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询科目期初余额
   * 
   * @param subjectcode
   * @param year
   * @param starperiod
   * @param endperiod
   * @param office
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryBgnblanceBySubjectcode_Wsh(final String subjectcode,
      final String year, final String starperiod, final String endperiod,
      final String office, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    BigDecimal value = new BigDecimal(0.00);
    try {

      value = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String yearmonth = "";
              String hql = " select sum(accountantCredence.debit)-sum(accountantCredence.credit) "
                  + " from AccountantCredence accountantCredence  ";
              Vector parameters = new Vector();
              String criterion = "";

              criterion = " where accountantCredence.bookId='"
                  + securityInfo.getBookId()
                  + "' and accountantCredence.credenceSt='2' and ";

              if (subjectcode != null && !subjectcode.equals("")) {
                criterion += " accountantCredence.subjectCode like ?  and ";
                parameters.add(subjectcode + "%");
              }
              if (starperiod != null && !starperiod.equals("")) {
                yearmonth = year.concat(starperiod).concat("01");
                criterion += " accountantCredence.credenceDate < ? and ";
                parameters.add(yearmonth);
              }
              if (office != null && !office.equals("")) {
                criterion += " accountantCredence.office = ? and ";
                parameters.add(office);
              } else {
                criterion += " accountantCredence.office in ( ";
                List officeList = securityInfo.getOfficeList();
                OfficeDto officedto = null;
                Iterator itr1 = officeList.iterator();
                while (itr1.hasNext()) {
                  officedto = (OfficeDto) itr1.next();
                  criterion += " '" + officedto.getOfficeCode() + "',";
                }
                criterion = criterion.substring(0, criterion.lastIndexOf(","));
                criterion += " ) and ";
              }

              if (criterion.length() != 0)
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));

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
    if (value == null) {
      return new BigDecimal(0.00);
    } else {
      return value;
    }
  }

  /**
   * 查询凭证维护连打列表的方法
   * 
   * @param credencecheckFindDTO 查询条件
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   * @throws Exception
   * @author 付云峰
   */
  public List queryCredencecheckList_FYF(final String type,
      final CredencecheckFindDTO credencecheckFindDTO,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page)
      throws Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String temp_type = "";
          if (type.equals("1")) {
            temp_type = "('0')";
          } else {
            temp_type = "('0','1','2')";
          }
          String hql = "";
          hql = " select fn201.credence_num,fn201.credence_date,max(fn201.office)"
              + " from FN201 fn201 " 
              + " where fn201.credence_num is not null "
              + " and fn201.credence_st in" + temp_type + " ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "fn201.book_id = ?  and ";
          parameters.add(securityInfo.getBookId());
          if (credencecheckFindDTO.getCredenceDateSt().trim() != null
              && !credencecheckFindDTO.getCredenceDateSt().trim().equals("")
              && credencecheckFindDTO.getCredenceDateEnd().trim() != null
              && !credencecheckFindDTO.getCredenceDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_date >=? and fn201.credence_date <=? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateSt().trim());
            parameters.add(credencecheckFindDTO.getCredenceDateEnd().trim());
          }
          if (credencecheckFindDTO.getOperator().trim() != null
              && !credencecheckFindDTO.getOperator().trim().equals("")) {
            criterion += " fn201.makebill = ? and ";
            parameters.add(credencecheckFindDTO.getOperator());
          } else if (credencecheckFindDTO.getFlag() == null) {
            criterion += " fn201.makebill = '"
                + securityInfo.getUserInfo().getUsername() + "' and ";
          }
          if (credencecheckFindDTO.getCredenceDateSt().trim() != null
              && !credencecheckFindDTO.getCredenceDateSt().trim().equals("")
              && (credencecheckFindDTO.getCredenceDateEnd().trim() == null || credencecheckFindDTO
                  .getCredenceDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_date >= ? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateSt().trim());
          }
          if (credencecheckFindDTO.getCredenceDateEnd().trim() != null
              && !credencecheckFindDTO.getCredenceDateEnd().trim().equals("")
              && (credencecheckFindDTO.getCredenceDateSt().trim() == null || credencecheckFindDTO
                  .getCredenceDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_date <= ? and ";
            parameters.add(credencecheckFindDTO.getCredenceDateEnd().trim());
          }
          if (credencecheckFindDTO.getSettDateSt().trim() != null
              && !credencecheckFindDTO.getSettDateSt().trim().equals("")
              && credencecheckFindDTO.getSettDateEnd().trim() != null
              && !credencecheckFindDTO.getSettDateEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.sett_date >= ? and fn201.sett_date <= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateSt().trim());
            parameters.add(credencecheckFindDTO.getSettDateEnd().trim());
          }
          if (credencecheckFindDTO.getSettDateSt().trim() != null
              && !credencecheckFindDTO.getSettDateSt().trim().equals("")
              && (credencecheckFindDTO.getSettDateEnd().trim() == null || credencecheckFindDTO
                  .getSettDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.sett_date >= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateSt().trim());
          }
          if (credencecheckFindDTO.getSettDateEnd().trim() != null
              && !credencecheckFindDTO.getSettDateEnd().trim().equals("")
              && (credencecheckFindDTO.getSettDateSt().trim() == null || credencecheckFindDTO
                  .getSettDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.sett_date <= ? and ";
            parameters.add(credencecheckFindDTO.getSettDateEnd().trim());
          }
          if (credencecheckFindDTO.getSummary() != null
              && !credencecheckFindDTO.getSummary().trim().equals("")) {
            criterion += " fn201.summay in "
              + "(select t.para_id from fn102 t where t.param_num = 4 and t.param_value > 10 and t.param_explain like ?) and";
            parameters.add("%" + credencecheckFindDTO.getSummary().trim() + "%");
          }
          if (credencecheckFindDTO.getSubjectCode().trim() != null
              && !credencecheckFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn201.subject_code like ? and ";
            parameters.add(credencecheckFindDTO.getSubjectCode().trim() + "%");
          }
          if (credencecheckFindDTO.getSubjectName().trim() != null
              && !credencecheckFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(credencecheckFindDTO.getSubjectName().trim() + "%");
          }
          
          if (credencecheckFindDTO.getCredenceNumSt().trim() != null
              && !credencecheckFindDTO.getCredenceNumSt().trim().equals("")
              && credencecheckFindDTO.getCredenceNumEnd().trim() != null
              && !credencecheckFindDTO.getCredenceNumEnd().trim().equals("")) {// 有开始日期结束日期
            criterion += " fn201.credence_num >=to_number(?) and fn201.credence_num <=to_number(?) and ";
            parameters.add(credencecheckFindDTO.getCredenceNumSt().trim());
            parameters.add(credencecheckFindDTO.getCredenceNumEnd().trim());
          }
          if (credencecheckFindDTO.getCredenceNumSt().trim() != null
              && !credencecheckFindDTO.getCredenceNumSt().trim().equals("")
              && (credencecheckFindDTO.getCredenceNumEnd().trim() == null || credencecheckFindDTO
                  .getCredenceNumEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " fn201.credence_num >= to_number(?) and ";
            parameters.add(credencecheckFindDTO.getCredenceNumSt().trim());
          }
          if (credencecheckFindDTO.getCredenceNumEnd().trim() != null
              && !credencecheckFindDTO.getCredenceNumEnd().trim().equals("")
              && (credencecheckFindDTO.getCredenceNumSt().trim() == null || credencecheckFindDTO
                  .getCredenceNumSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " fn201.credence_num <= to_number(?)  and ";
            parameters.add(credencecheckFindDTO.getCredenceNumEnd().trim());
          }
          if (credencecheckFindDTO.getSettType().trim() != null
              && !credencecheckFindDTO.getSettType().trim().equals("")) {
            criterion += " fn201.sett_type = ? and ";
            parameters.add(credencecheckFindDTO.getSettType().trim());
          }

          if (credencecheckFindDTO.getSettNum().trim() != null
              && !credencecheckFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn201.sett_num = ? and ";
            parameters.add(credencecheckFindDTO.getSettNum().trim());
          }
          if (credencecheckFindDTO.getOffice().trim() != null
              && !credencecheckFindDTO.getOffice().trim().equals("")
              && "全部".equals(credencecheckFindDTO.getOffice())) {

            criterion += " fn201.office in ( ";
            List officeList = securityInfo.getOfficeList();
            OfficeDto officedto = null;
            Iterator itr1 = officeList.iterator();
            while (itr1.hasNext()) {
              officedto = (OfficeDto) itr1.next();
              criterion += " '" + officedto.getOfficeCode() + "',";
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += " ) and ";

          } else if (credencecheckFindDTO.getOffice().trim() != null
              && !credencecheckFindDTO.getOffice().trim().equals("")) {
            criterion += " fn201.office = ? and ";
            parameters.add(credencecheckFindDTO.getOffice().trim());
          }
          if (credencecheckFindDTO.getCredenceSt().trim() != null
              && !credencecheckFindDTO.getCredenceSt().trim().equals("")) {
            criterion += " fn201.credence_st = ? and ";
            parameters.add(credencecheckFindDTO.getCredenceSt().trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String having = "";
          if (credencecheckFindDTO.getMoneySt().trim() != null
              && !credencecheckFindDTO.getMoneySt().trim().equals("")
              && credencecheckFindDTO.getMoneyEnd().trim() != null
              && !credencecheckFindDTO.getMoneyEnd().trim().equals("")) {// 有开始金额结束金额
            having = " having sum(fn201.debit) >=? and sum(fn201.debit) <=? ";
            parameters.add(credencecheckFindDTO.getMoneySt());
            parameters.add(credencecheckFindDTO.getMoneyEnd());
          }
          if (credencecheckFindDTO.getMoneySt().trim() != null
              && !credencecheckFindDTO.getMoneySt().trim().equals("")
              && (credencecheckFindDTO.getMoneyEnd().trim() == null || credencecheckFindDTO
                  .getMoneyEnd().trim().equals(""))) {// 有开始金额无结束金额
            having = " having sum(fn201.debit) >= ? ";
            parameters.add(credencecheckFindDTO.getMoneySt());
          }
          if (credencecheckFindDTO.getMoneyEnd().trim() != null
              && !credencecheckFindDTO.getMoneyEnd().trim().equals("")
              && (credencecheckFindDTO.getMoneySt().trim() == null || credencecheckFindDTO
                  .getMoneySt().trim().equals(""))) {// 无开始金额有结束金额
            having = " having sum(fn201.debit) <= ? ";
            parameters.add(credencecheckFindDTO.getMoneyEnd());
          }
          hql = hql + criterion
              + "group by fn201.credence_num,fn201.credence_date "
              + having
              + "order by fn201.credence_num";
          Query query = session.createSQLQuery(hql);
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

  /**
   * 凭证检查
   * 
   * @author wsh 2007-10-30 查找FN201表中记录数量list，
   */
  public List queryCredenceInspectionSize(
      final CredenceInspectionFindDTO credenceInspectionFindDTO,
      final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  a.credence_num from fn201 a where a.book_id = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          parameters.add(securityInfo.getBookId());
          if (credenceInspectionFindDTO.getCredenceDateSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateSt().trim().equals(
                  "")
              && credenceInspectionFindDTO.getCredenceDateEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateEnd().trim().equals(
                  "")) {// 有开始日期结束日期
            criterion += " a.credence_date >=? and a.credence_date <=? and ";
            parameters
                .add(credenceInspectionFindDTO.getCredenceDateSt().trim());
            parameters.add(credenceInspectionFindDTO.getCredenceDateEnd()
                .trim());
          }
          if (credenceInspectionFindDTO.getCredenceDateSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateSt().trim().equals(
                  "")
              && (credenceInspectionFindDTO.getCredenceDateEnd().trim() == null || credenceInspectionFindDTO
                  .getCredenceDateEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " a.credence_date >= ? and ";
            parameters
                .add(credenceInspectionFindDTO.getCredenceDateSt().trim());
          }
          if (credenceInspectionFindDTO.getCredenceDateEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceDateEnd().trim().equals(
                  "")
              && (credenceInspectionFindDTO.getCredenceDateSt().trim() == null || credenceInspectionFindDTO
                  .getCredenceDateSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " a.credence_date <= ? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceDateEnd()
                .trim());
          }
          if (credenceInspectionFindDTO.getCredenceNumSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumSt().trim()
                  .equals("")
              && credenceInspectionFindDTO.getCredenceNumEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumEnd().trim().equals(
                  "")) {// 有开始日期结束日期
            criterion += " a.credence_num >=? and a.credence_num <=? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceNumSt().trim());
            parameters
                .add(credenceInspectionFindDTO.getCredenceNumEnd().trim());
          }
          if (credenceInspectionFindDTO.getCredenceNumSt().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumSt().trim()
                  .equals("")
              && (credenceInspectionFindDTO.getCredenceNumEnd().trim() == null || credenceInspectionFindDTO
                  .getCredenceNumEnd().trim().equals(""))) {// 有开始日期无结束日期
            criterion += " a.credence_num >= ? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceNumSt().trim());
          }
          if (credenceInspectionFindDTO.getCredenceNumEnd().trim() != null
              && !credenceInspectionFindDTO.getCredenceNumEnd().trim().equals(
                  "")
              && (credenceInspectionFindDTO.getCredenceNumSt().trim() == null || credenceInspectionFindDTO
                  .getCredenceNumSt().trim().equals(""))) {// 无开始日期有结束日期
            criterion += " a.credence_num <= ? and ";
            parameters
                .add(credenceInspectionFindDTO.getCredenceNumEnd().trim());
          }
          if (credenceInspectionFindDTO.getCredenceCharacter().trim() != null
              && !credenceInspectionFindDTO.getCredenceCharacter().trim()
                  .equals("")) {
            criterion += " a.credence_character = ? and ";
            parameters.add(credenceInspectionFindDTO.getCredenceCharacter()
                .trim());
          }
          if (credenceInspectionFindDTO.getOffice().trim() != null
              && !credenceInspectionFindDTO.getOffice().trim().equals("")) {
            criterion += " a.office = ? and ";
            parameters.add(credenceInspectionFindDTO.getOffice().trim());
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + "  and a.credence_date>(select f101.use_yearmonth from fn101 f101 where f101.book_id="
              + securityInfo.getBookId()
              + ") group by a.credence_date,a.office,a.credence_num ";
          Query query = session.createSQLQuery(hql);
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

  /**
   * 现金日记账--维护
   * 
   * @author 郭婧平 2007-11-26 查找凭证号、办事处 查询条件：settNum,credenceId
   */
  public Object[] queryByCredenceId(final String credenceId) {
    Object[] obj = new Object[2];
    obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select accountantCredence.credenceNum,accountantCredence.office from AccountantCredence accountantCredence "
            + "where accountantCredence.credenceId=? ";
        Query query = session.createQuery(hql).setParameter(0,
            new Integer(credenceId));
        return query.uniqueResult();
      }
    });
    return obj;
  }

  /**
   * 凭证弹出框查询个贷业务流水
   * 
   * @return
   * @author 付云峰
   */
  public List queryLoanBusiFlowQueryListByCriterions_FYF(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String noteNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,bizDate,wrongBizType,loanBankID, makePerson,flowheadID,notenum from "
              + "("
              + " select pl202.doc_num as docnum,"
              + "'' as loankouacc, "
              + "'' as contractId,"
              + "'' as borrowername,"
              + "pl202.biz_type as bizType,"
              + "0 as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "0 as badDebt,"
              + "nvl(pl202.occur_money, 0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5' or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5') or pl202.biz_type = '15'"
              + " union"
              + " select pl202.doc_num as docnum,"
              + "pl203.loan_kou_acc as loankouacc,"
              + "pl203.contract_id as contractId,"
              + "pl110.borrower_name  as borrowername,"
              + "pl202.biz_type as bizType,"
              + "nvl(pl202.occur_money,0) as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "nvl(pl202.occur_money,0) as badDebt,"
              + "nvl(pl202.occur_money,0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id "
              + " and ("
              + " (pl202.biz_type in"
              + "('1', '2', '3', '4', '6', '7', '8', '9', '11', '13', '14') )"
              + " or (pl202.biz_type = '12' and  pl202.wrong_biz_type in('1', '2', '6', '7') )"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type is null )"
              + ") " + " and pl110.contract_id=pl203.contract_id" + ")";
          String criterion = "";
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null)
            ob = " flowheadID ";

          String od = order;
          if (od == null)
            od = " DESC";

          if (docNum != null && !"".equals(docNum)) {
            criterion += " docnum = ? and ";
            parameters.add(docNum);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " contractId  = ? and ";
            parameters.add(contractId);
          }

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " loanKouAcc = ? and ";
            parameters.add(loanKouAcc);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " borrowername = ? and ";
            parameters.add(borrowerName);
          }
          if (makePerson != null && !"".equals(makePerson)) {
            criterion += " makePerson = ? and ";
            parameters.add(makePerson);
          }
          if (bizType != null && !"".equals(bizType)) {
            criterion += " bizType = ? and ";
            parameters.add(bizType);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += "  bizSt = ? and ";
            parameters.add(bizSt);
          } else {
            criterion += "  bizSt in (4,5,6) and ";
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " loanBankID = ? and ";
            parameters.add(loanBankName);
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " bizDate >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " bizDate <= ? and ";
            parameters.add(endBizDate);
          }

          if (noteNum != null && !noteNum.equals("")) {
            criterion += " notenum in(";
            String[] temp_noteNum = noteNum.split(",");
            for (int i = 0; i < temp_noteNum.length; i++) {
              criterion += " ? , ";
              parameters.add(temp_noteNum[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","))
                + ") and ";
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          return queryList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 凭证弹出框查询个贷流水业务
   * 
   * @return
   * @author 付云峰
   */
  public List queryLoanBusiFlowQueryAllByCriterions_FYF(
      final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String noteNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,bizDate,wrongBizType,loanBankID, makePerson,flowheadID,notenum from "
              + "("
              + " select pl202.doc_num as docnum,"
              + "'' as loankouacc, "
              + "'' as contractId,"
              + "'' as borrowername,"
              + "pl202.biz_type as bizType,"
              + "0 as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "0 as badDebt,"
              + "nvl(pl202.occur_money, 0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5' or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5') or pl202.biz_type = '15'"
              + " union"
              + " select pl202.doc_num as docnum,"
              + "pl203.loan_kou_acc as loankouacc,"
              + "pl203.contract_id as contractId,"
              + "pl110.borrower_name  as borrowername,"
              + "pl202.biz_type as bizType,"
              + "nvl(pl202.occur_money,0) as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "nvl(pl202.occur_money,0) as badDebt,"
              + "nvl(pl202.occur_money,0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id "
              + " and ("
              + " (pl202.biz_type in"
              + "('1', '2', '3', '4', '6', '7', '8', '9', '11', '13', '14') )"
              + " or (pl202.biz_type = '12' and  pl202.wrong_biz_type in('1', '2', '6', '7') )"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type is null )"
              + ") " + " and pl110.contract_id=pl203.contract_id" + ")";
          String criterion = "";
          Vector parameters = new Vector();

          if (docNum != null && !"".equals(docNum)) {
            criterion += " docnum = ? and ";
            parameters.add(docNum);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " contractId  = ? and ";
            parameters.add(contractId);
          }

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " loanKouAcc = ? and ";
            parameters.add(loanKouAcc);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " borrowername = ? and ";
            parameters.add(borrowerName);
          }
          if (makePerson != null && !"".equals(makePerson)) {
            criterion += " makePerson = ? and ";
            parameters.add(makePerson);
          }
          if (bizType != null && !"".equals(bizType)) {
            criterion += " bizType = ? and ";
            parameters.add(bizType);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += "  bizSt = ? and ";
            parameters.add(bizSt);
          } else {
            criterion += "  bizSt in (4,5,6) and ";
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " loanBankID = ? and ";
            parameters.add(loanBankName);
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " bizDate >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " bizDate <= ? and ";
            parameters.add(endBizDate);
          }

          if (noteNum != null && !noteNum.equals("")) {
            criterion += " notenum in(";
            String[] temp_noteNum = noteNum.split(",");
            for (int i = 0; i < temp_noteNum.length; i++) {
              criterion += " ? , ";
              parameters.add(temp_noteNum[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","))
                + ") and ";
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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

  /**
   * 查询fn201中相同凭证号，办事处，账套，凭证日期的科目代码
   * 
   * @param office
   * @param credenceNum
   * @param credenceDat
   * @param bookId
   * @return List
   */

  public List querySubjectCodeList_wsh(final String office,
      final String credenceNum, final String credenceDate, final String bookId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.subject_code from fn201 a where a.book_id='"
              + bookId + "' and a.office='" + office + "' and a.credence_num='"
              + credenceNum + "' and a.credence_date='" + credenceDate + "' ";
          Query query = session.createSQLQuery(hql);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 判断该票据号下的业务是否在财政代扣或公积金还贷业务
   * 
   * @param noteNum
   * @return
   * @author 付云峰
   */
  public Object isSpecailType(final String noteNum) {
    Object obj = null;
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct a101.specailtype from aa101 a101 where a101.note_num=?";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, noteNum);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * 判断FN201中是否存在初始余额
   * 
   * @param officeid
   * @param bookId
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String queryCredMessageByOfficeID(final String officeid,
      final String bookId) throws Exception, BusinessException {
    String stutas = "0";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select count(distinct subject_code ) from ((select f110.subject_code from fn110 f110 where f110.subject_code in "
              + " (select f.subject_code from fn110 f where f.book_id = ? minus select f.parent_subject_code from fn110 f where f.book_id = ?) "
              + " and f110.book_id = ?) minus ( select f201.subject_code from fn201 f201 where f201.office = ?"
              + " and f201.book_id = ?))";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, bookId);
          query.setParameter(2, bookId);
          query.setParameter(3, officeid);
          query.setParameter(4, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  public List getCollBank() {
    List list = getHibernateTemplate().find("from CollBank s where s.status=1");
    return list;
  }

  /**
   * 删除FN201_1中的凭证
   * 
   * @param settNum 结算号
   * @param bookId 账套
   */
  public void deleteFn201_1(String settNum, String bookId) {
    Connection connection = this.getHibernateTemplate().getSessionFactory()
        .openSession().connection();
    try {
      String sql = "delete from fn201_1 t where t.book_id =" + bookId
          + " and t.sett_num in (" + settNum + ")";
      Statement st = connection.createStatement();
      st.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List queryCredenceInfoByReserveC(final String bookId,
      final String settNum, final String credenceDate) throws Exception,
      BusinessException {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select t.credence_num,max(sett_date) from fn201 t "
              + " where t.book_id = ? " + " and t.credence_date = ? "
              + " and t.reserve_c = ? " + " group by t.credence_num";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, credenceDate);
          query.setParameter(2, settNum);
          List returnList = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          while (iterator.hasNext()) {
            DelectCredenceInfoDTO dto = new DelectCredenceInfoDTO();
            obj = (Object[]) iterator.next();
            if (obj[0] != null) {
              dto.setDocNum(obj[0].toString());
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryDetailAccList(final String bookId, final String subjectCode,
      final String yearMonSt, final String yearMonEnd, final String officeCode)
      throws Exception, BusinessException {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select t.credence_date," 
              + " t.credence_num,"
              + " t.summay," 
              + " t.debit,"
              + " t.credit," 
              + " t.credence_id,"
              + " t.office" 
              + " from fn201 t" 
              + " where t.book_id = ? "
              + " and substr(t.credence_date, 0, 6) between ? and ?"
              + " and t.subject_code = ? " + " and t.credence_st = 2";
          if (officeCode != null && !"".equals(officeCode)) {
            sql += " and t.office = ? ";
          }
          sql += " order by t.credence_date,credence_num";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, yearMonSt);
          query.setParameter(2, yearMonEnd);
          query.setParameter(3, subjectCode);
          if (officeCode != null && !"".equals(officeCode)) {
            query.setParameter(4, officeCode);
          }
          List returnList = new ArrayList();
          Iterator iterator = query.list().iterator();
          ListaccDTO dto = null;
          Object[] obj = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new ListaccDTO();
            if (obj[0] != null) {
              dto.setCredenceDate(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setCredenceNum(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setSummay(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setDebit(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              dto.setCredit(new BigDecimal(obj[4].toString()));
            }
            dto.setCredenceId(obj[5].toString());
            if (obj[6] != null) {
              dto.setOffice(obj[6].toString());
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List queryExpDetailAccList(final String bookId, final String subjectCode,
      final String yearMonth)
      throws Exception, BusinessException {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select t.credence_date," 
              + " t.credence_num,"
              + " t.summay,"
              + " t.debit,"
              + " t.credit" 
              + " from fn201 t" 
              + " where t.book_id = ? "
              + " and substr(t.credence_date, 0, 6) = ?"
              + " and t.subject_code = ? " + " and t.credence_st = 2";
          sql += " order by t.credence_date,credence_num";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, yearMonth);
          query.setParameter(2, subjectCode);
          List returnList = new ArrayList();
          Iterator iterator = query.list().iterator();
          ListaccDTO dto = null;
          Object[] obj = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new ListaccDTO();
            if (obj[0] != null) {
              dto.setCredenceDate(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setCredenceNum(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setSummay(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setDebit(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              dto.setCredit(new BigDecimal(obj[4].toString()));
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询收入金额
   * @author yangg
   * @param credenceFillinTbFindDTO
   * @return List
   * @throws Exception
   * @throws BusinessException
   */
  public List getIncomeList(
      final CredenceFillinTbFindDTO credenceFillinTbFindDTO) throws Exception,
      BusinessException {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector vec = new Vector();
          String sql = " select t.org_id, b.name, t.moneybank, t.debit, t.credit,"
              + " t.interest, t.sett_date,"
              + " t.note_num, t.biz_type from aa101 t, ba001 b, aa001 a"
              + " where t.org_id = a.id and t.is_finance='1' and a.orginfo_id = b.id and ((t.biz_type in ('A', 'B', 'M')) or"
              + " (t.biz_type in ('K', 'L', 'G') and t.credit > 0) or"
              + " (t.biz_type = 'C' and t.credit > 0))"
              + " and t.biz_status in ('4','5')";

          if (credenceFillinTbFindDTO.getSettDateStart() != null
              && !credenceFillinTbFindDTO.getSettDateStart().equals("")) {
            criterion += " t.sett_date >= ? and ";
            vec.add(credenceFillinTbFindDTO.getSettDateStart());
          }
          if (credenceFillinTbFindDTO.getSettDateEnd() != null
              && !credenceFillinTbFindDTO.getSettDateEnd().equals("")) {
            criterion += " t.sett_date <= ? and ";
            vec.add(credenceFillinTbFindDTO.getSettDateEnd());
          }
          if (credenceFillinTbFindDTO.getSettNum() != null
              && !credenceFillinTbFindDTO.getSettNum().equals("")) {
            criterion += " t.note_num = ? and ";
            vec.add(credenceFillinTbFindDTO.getSettNum());
          }
          if (credenceFillinTbFindDTO.getBankId() != null
              && !credenceFillinTbFindDTO.getBankId().equals("")) {
            criterion += " t.moneybank = ? and ";
            vec.add(credenceFillinTbFindDTO.getBankId());
          }
          if (criterion.length() != 0) {
            sql += " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < vec.size(); i++) {
            query.setParameter(i, vec.get(i));
          }

          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询支出金额
   * 
   * @author yangg
   * @param credenceFillinTbFindDTO
   * @return List
   * @throws Exception
   * @throws BusinessException
   */
  public List getExpenseList(
      final CredenceFillinTbFindDTO credenceFillinTbFindDTO) throws Exception,
      BusinessException {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector vec = new Vector();
          String sql = " select t.org_id, b.name, t.moneybank, t.debit, t.credit,"
              + " t.interest, t.sett_date,"
              + " t.note_num, t.biz_type from aa101 t, ba001 b, aa001 a"
              + " where t.org_id = a.id and t.is_finance='1' and a.orginfo_id = b.id and ((t.biz_type in ('D')) or (t.biz_type in ('K', 'L', 'G') and t.debit > 0)) "
              + " and t.biz_status in ('4','5')";

          if (credenceFillinTbFindDTO.getSettDateStart() != null
              && !credenceFillinTbFindDTO.getSettDateStart().equals("")) {
            criterion += " t.sett_date >= ? and ";
            vec.add(credenceFillinTbFindDTO.getSettDateStart());
          }
          if (credenceFillinTbFindDTO.getSettDateEnd() != null
              && !credenceFillinTbFindDTO.getSettDateEnd().equals("")) {
            criterion += " t.sett_date <= ? and ";
            vec.add(credenceFillinTbFindDTO.getSettDateEnd());
          }
          if (credenceFillinTbFindDTO.getSettNum() != null
              && !credenceFillinTbFindDTO.getSettNum().equals("")) {
            criterion += " t.note_num = ? and ";
            vec.add(credenceFillinTbFindDTO.getSettNum());
          }
          if (credenceFillinTbFindDTO.getBankId() != null
              && !credenceFillinTbFindDTO.getBankId().equals("")) {
            criterion += " t.moneybank = ? and ";
            vec.add(credenceFillinTbFindDTO.getBankId());
          }
          if (criterion.length() != 0) {
            sql += " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < vec.size(); i++) {
            query.setParameter(i, vec.get(i));
          }

          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public String findCollBanknameByOrgInfoCollectionBankId(final String id) {
    CollBank collBank = null;
    collBank = (CollBank) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from CollBank collBank where collBank.collBankId= ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(id));
            return query.uniqueResult();
          }
        });
    return collBank.getCollBankName();
  }

  /**
   * 根据账套,办事处,日期返回最大的凭证号
   * 
   * @param bookId
   * @param office
   * @return
   */
  public String getMaxCredenceNum(final String bookId, final String office,
      final String credenceDate) {
    String minCredenceNum = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(to_number(t.credence_num)) from fn201 t where t.credence_st = 2 ";
            Vector parameters = new Vector();
            String criterion = "";
            if (bookId != null && !bookId.equals("")) {
              criterion += " t.book_id = ? and ";
              parameters.add(bookId);
            }
            if (office != null && !office.equals("")) {
              criterion += " t.office = ? and ";
              parameters.add(office);
            }
            if (credenceDate != null && !credenceDate.equals("")) {
              criterion += " substr(t.credence_date,0,6) = ? and ";
              parameters.add(credenceDate);
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult() != null ? query.uniqueResult()
                .toString() : null;
          }
        });
    return minCredenceNum;
  }
  /**
   * 查询该月份下是否有未记账的凭证
   * @param bookId
   * @param credenceDate
   * @return
   */
  public Integer queryCredCount(final String bookId, final String credenceDate) {
    Integer count = new Integer(0);
    count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select count(t.credence_id) from fn201 t where t.credence_st <> 2 ";
        Vector parameters = new Vector();
        String criterion = "";
        if (bookId != null && !bookId.equals("")) {
          criterion += " t.book_id = ? and ";
          parameters.add(bookId);
        }
        if (credenceDate != null && !credenceDate.equals("")) {
          criterion += " substr(t.credence_date,0,6) = ? and ";
          parameters.add(credenceDate.substring(0, 6));
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
    return count;
  }
  public String queryOfficeCode_Debit(final String noteNum,
      final String credenceDate) throws NumberFormatException, Exception {
    String office = "";
    try {
      office = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select max(a101.officecode) " + " from aa101 a101 "
              + " where a101.note_num = ? " 
              + " and a101.debit > a101.credit";
          Query query = session.createSQLQuery(sql);
          query.setString(0, noteNum);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return office;
  }

  public String queryOfficeCode_Credit(final String noteNum,
      final String credenceDate) throws NumberFormatException, Exception {
    String office = "";
    try {
      office = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select max(a101.officecode) " + " from aa101 a101 "
              + " where a101.note_num = ? " 
              + " and a101.debit < a101.credit";
          Query query = session.createSQLQuery(sql);
          query.setString(0, noteNum);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return office;
  }
}
