package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
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
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopListDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.TreasurerCredence;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccBdcDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowBaseDTO;


public class TreasurerCredenceDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public TreasurerCredence queryById(Serializable id) {
    Validate.notNull(id);
    return (TreasurerCredence) getHibernateTemplate().get(
        TreasurerCredence.class, id);
  }

  /**
   * 插入记录
   * 
   * @param TreasurerCredence
   * @return
   */
  public Serializable insert(TreasurerCredence treasurerCredence) {
    Serializable id = null;
    try {
      Validate.notNull(treasurerCredence);
      id = getHibernateTemplate().save(treasurerCredence);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-25
   * 根据主键id查找fn210表中有没有该条数据
   * 查询条件：credenceId
   */
  public Integer queryTreasurerCredenceById(final Integer credenceId) throws Exception {
    Integer id = null;
    try {
      id = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select treasurerCredence.credenceId from TreasurerCredence treasurerCredence where treasurerCredence.credenceId = ? ";
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
            String hql = "select treasurerCredence.subjectCode from TreasurerCredence treasurerCredence ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "treasurerCredence.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());
            
            if (code != null && !code.equals("")) {
              criterion += "treasurerCredence.subjectCode = ?  and ";
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
   * 现金日记账
   * @author 郭婧平
   * 2007-10-12
   * 查找FN210表中该办事处下CREDENCE_TYPE=0，ID最大记录的CREDENCE_DATE
   * 查询条件：office
   */
  public String queryCredenceDateByOffice(final String office,final String credenceType,final SecurityInfo securityInfo) throws Exception {
    String credenceDate="";
    try {
      credenceDate =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select treasurerCredence.credenceDate " +
                "from TreasurerCredence treasurerCredence " +
                "where treasurerCredence.credenceId=" +
                "(select max(treasurerCredence1.credenceId) " +
                "from TreasurerCredence treasurerCredence1 " +
                "where treasurerCredence1.credenceType = ? and treasurerCredence1.office=? " +
                "and treasurerCredence1.bookId=? ) ";
            Query query = session.createQuery(hql);
            query.setParameter(0, credenceType);
            query.setParameter(1, office);
            query.setParameter(2, securityInfo.getBookId());
            return query.uniqueResult();                           
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return credenceDate;
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-13
   * 查找有没有符合查询条件的数据
   * 查询条件：settNum,credenceId
   */
  public List queryTreasurerCredenceBySettNum(final String settNum,final String subjectCode,final SecurityInfo securityInfo,final String credenceId) {
    List list=new ArrayList();
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select treasurerCredence.credenceId from TreasurerCredence treasurerCredence ";
            Vector parameters = new Vector();
            String criterion = "";
            criterion += "treasurerCredence.settNum= ? and ";
            parameters.add(settNum);
            
            criterion += "treasurerCredence.bookId= ? and ";
            parameters.add(securityInfo.getBookId());
            
            criterion += "treasurerCredence.subjectCode= ? and ";
            parameters.add(subjectCode);
            
            if (credenceId != null && !credenceId.equals("")) {
              criterion += "(treasurerCredence.credenceId < ?  or treasurerCredence.credenceId < ?) and ";
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
   * 现金日记账--自动转账页面
   * @author 郭婧平
   * 2007-10-16
   * 转账按钮,调用存储过程
   */
  public void transfers(final String credenceId,final String credenceType,final String makeBill,final String opIp,final String opModel)throws BusinessException, HibernateException, SQLException{
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    CallableStatement cs=conn.prepareCall("{call fn_tr_autotransfers(?,?,?,?,?)}");
    cs.setString(1, credenceId);
    cs.setString(2, makeBill);
    cs.setString(3, opIp);
    cs.setString(4, credenceType);
    cs.setString(5, opModel);
    cs.execute();
  }

  /**
   * 现金日记账--维护页面
   * @author 郭婧平
   * 2007-10-18
   * 根据查询条件查找FN210表中的数据
   * 查询条件：credenceDate,summary,subjectCode,subjectName,money,credenceNum,credenceSt
   */
  public List queryCashDayClearTcList(final CashDayClearTcFindDTO cashDayClearTcFindDTO,final List officeList,final String credenceType,final SecurityInfo securityInfo,
      final String orderBy,final String order, final int start, final int pageSize,final int page) throws Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn210.credence_date,fn210.credence_num,fn210.credence_character " +
              ",fn210.summray,fn210.subject_code,fn110.subject_name,fn210.debit,fn210.credit " +
              ",fn210.sett_num,fn210.sett_type,fn210.dopsn,fn210.makebill,fn210.credence_st,fn210.credence_id,fn210.sett_date,fn210.acredence_id "+
              ",(select abs(sum(a.debit) - sum(a.credit)) from fn210 a  where a.book_id = fn210.book_id  "+
              " and a.subject_code = fn210.subject_code and a.office = fn210.office and a.credence_id<=fn210.credence_id) as balance "+
              "from FN210 fn210,FN110 fn110,FN102 fn102 "
              + "where fn210.subject_code=fn110.subject_code " 
              + "and fn210.book_id=fn110.book_id "
              + "and fn210.summray=fn102.para_id "
              + "and fn210.book_id=fn102.book_id " 
              + "and (fn102.param_value  >'3' or fn102.param_value < '3') ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (credenceType.trim() != null && !credenceType.trim().equals("")) {
            criterion += "fn210.credence_type = ? and ";
            parameters.add(credenceType);
          }
            
          criterion += "fn210.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (cashDayClearTcFindDTO.getOffice().trim() != null&&!cashDayClearTcFindDTO.getOffice().trim().equals("")) {
            criterion += " and fn210.office=? and ";
            parameters.add(cashDayClearTcFindDTO.getOffice().trim());
          }else{
            if (officeList != null && officeList.size() > 0) {
              criterion += "and ( ";
              for (int i = 0; i < officeList.size(); i++) {
                criterion += " fn210.office = ? or ";
                parameters.add(officeList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and ";
            }
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.credence_date >=? and fn210.credence_date <=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateEnd().trim() == null || cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.credence_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateSt().trim() == null || cashDayClearTcFindDTO.getCredenceDateSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.credence_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTcFindDTO.getSummary().trim() != null && !cashDayClearTcFindDTO.getSummary().trim().equals("")) {
            criterion += " fn210.summray=? and ";
            parameters.add(cashDayClearTcFindDTO.getSummary().trim());
          }
          
          if (cashDayClearTcFindDTO.getSubjectCode().trim() != null && !cashDayClearTcFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn210.subject_code like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectCode().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getSubjectName().trim() != null && !cashDayClearTcFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectName().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("")) {//有开始金额结束金额
            criterion += " ((fn210.debit >=? and fn210.debit <=?) or (fn210.credit >=? and fn210.credit <=?)) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && (cashDayClearTcFindDTO.getMoneyEnd().trim() == null || cashDayClearTcFindDTO.getMoneyEnd().trim().equals(""))) {//有开始金额无结束金额
            criterion += " (fn210.debit >= ? or fn210.credit >=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
          }
          
          if (cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("") && (cashDayClearTcFindDTO.getMoneySt().trim() == null || cashDayClearTcFindDTO.getMoneySt().trim().equals(""))) {//无开始金额有结束金额
            criterion += " (fn210.debit <= ? or fn210.credit <=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("")) {//有开始凭证号结束凭证号
            criterion += " fn210.credence_num >= to_number(?) and fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumEnd().trim() == null || cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals(""))) {//有开始凭证号无结束凭证号
            criterion += " fn210.credence_num >= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumSt().trim() == null || cashDayClearTcFindDTO.getCredenceNumSt().trim().equals(""))) {//无开始凭证号有结束凭证号
            criterion += " fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          if(cashDayClearTcFindDTO.getType().equals("1")){
            if (cashDayClearTcFindDTO.getCredenceSt().trim() != null && !cashDayClearTcFindDTO.getCredenceSt().trim().equals("")) {
              criterion += " fn210.credence_st = ? and ";
              parameters.add(cashDayClearTcFindDTO.getCredenceSt().trim());
            }
          }else if(cashDayClearTcFindDTO.getType().equals("3")){
            criterion += " fn210.credence_st = '2' and ";
          }else{
            criterion += " fn210.credence_st = '0' and ";
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("")) {//有开始结算日期结束结算日期
            criterion += " fn210.sett_date >= ? and fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && (cashDayClearTcFindDTO.getSettDateEnd().trim() == null || cashDayClearTcFindDTO.getSettDateEnd().trim().equals(""))) {//有开始结算日期无结束结算日期
            criterion += " fn210.sett_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getSettDateSt().trim() == null || cashDayClearTcFindDTO.getSettDateSt().trim().equals(""))) {//无开始结算日期有结束结算日期
            criterion += " fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceCharacter().trim() != null && !cashDayClearTcFindDTO.getCredenceCharacter().trim().equals("")) {
            criterion += " fn210.credence_character=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceCharacter().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettType().trim() != null && !cashDayClearTcFindDTO.getSettType().trim().equals("")) {
            criterion += " fn210.sett_type=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettType().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettNum().trim() != null && !cashDayClearTcFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn210.sett_num=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettNum().trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy.trim();
          
          if(ob.equals("fn210.credence_character")){
            ob = " fn210.credence_character "+order+" ,fn210.credence_num ";
          }
          
          if (ob == null)
            ob = " fn210.credence_id ";

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
              CashDayClearTcShowListDTO cashDayClearTcShowListDTO = new CashDayClearTcShowListDTO();
              if (obj[0] != null) {
                cashDayClearTcShowListDTO.setCredenceDate(obj[0].toString());
              }
              if (obj[1] != null) {
                cashDayClearTcShowListDTO.setCredenceNum(obj[1].toString());
              }
              if (obj[2] !=null) {
                cashDayClearTcShowListDTO.setCredenceCharacter(obj[2].toString());
              }
              if (obj[3] != null) {
                cashDayClearTcShowListDTO.setSummary(obj[3].toString());
              }
              if (obj[4] != null) {
                cashDayClearTcShowListDTO.setSubjectCode(obj[4].toString());
              }
              cashDayClearTcShowListDTO.setSubjectName(obj[5].toString());
              if (obj[6] != null) {
                cashDayClearTcShowListDTO.setDebit(new BigDecimal(obj[6].toString()));
              }
              if (obj[7] != null) {
                cashDayClearTcShowListDTO.setCredit(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                cashDayClearTcShowListDTO.setSettNum(obj[8].toString());
              }
              if (obj[9] != null) {
                cashDayClearTcShowListDTO.setSettType(obj[9].toString());
              }
              if (obj[10] != null) {
                cashDayClearTcShowListDTO.setDopsn(obj[10].toString());
              }
              if (obj[11] != null) {
                cashDayClearTcShowListDTO.setMakebill(obj[11].toString());
              }
              if (obj[12] != null) {
                cashDayClearTcShowListDTO.setCredenceSt(obj[12].toString());
              }
              cashDayClearTcShowListDTO.setCredenceId(obj[13].toString());
              if (obj[14] != null) {
                cashDayClearTcShowListDTO.setSettDate(obj[14].toString());
              }
              if (obj[15] != null) {
                cashDayClearTcShowListDTO.setAcredenceId(obj[15].toString());
              }
              if (obj[16] != null) {
                cashDayClearTcShowListDTO.setBalance(new BigDecimal(obj[16].toString()));
              }
              temp_list.add(cashDayClearTcShowListDTO);
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
   * 现金日记账--维护页面count
   * @author 郭婧平
   * 2007-10-18
   * 根据查询条件查找FN210表中的数据的条数
   * 查询条件：credenceDate,summary,subjectCode,subjectName,money,credenceNum,credenceSt
   */
  public List queryCashDayClearTcListCount(final CashDayClearTcFindDTO cashDayClearTcFindDTO,final List officeList,final String credenceType,final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn210.debit,fn210.credit,fn210.credence_id "
              + "from FN210 fn210,FN110 fn110,FN102 fn102 "
              + "where fn210.subject_code=fn110.subject_code "
              + "and fn210.book_id=fn110.book_id "
              + "and fn210.summray=fn102.para_id "
              + "and fn210.book_id=fn102.book_id " 
              + "and (fn102.param_value  >'3' or fn102.param_value < '3') ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (credenceType.trim() != null && !credenceType.trim().equals("")) {
            criterion += "fn210.credence_type = ? and ";
            parameters.add(credenceType);
          }
            
          criterion += "fn210.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (cashDayClearTcFindDTO.getOffice().trim() != null&&!cashDayClearTcFindDTO.getOffice().trim().equals("")) {
            criterion += " and fn210.office=? and ";
            parameters.add(cashDayClearTcFindDTO.getOffice().trim());
          }else{
            if (officeList != null && officeList.size() > 0) {
              criterion += "and ( ";
              for (int i = 0; i < officeList.size(); i++) {
                criterion += " fn210.office = ? or ";
                parameters.add(officeList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and ";
            }
          }
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.credence_date >=? and fn210.credence_date <=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateEnd().trim() == null || cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.credence_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateSt().trim() == null || cashDayClearTcFindDTO.getCredenceDateSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.credence_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTcFindDTO.getSummary().trim() != null && !cashDayClearTcFindDTO.getSummary().trim().equals("")) {
            criterion += " fn210.summray=? and ";
            parameters.add(cashDayClearTcFindDTO.getSummary().trim());
          }
          
          if (cashDayClearTcFindDTO.getSubjectCode().trim() != null && !cashDayClearTcFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn210.subject_code like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectCode().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getSubjectName().trim() != null && !cashDayClearTcFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectName().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("")) {//有开始金额结束金额
            criterion += " ((fn210.debit >=? and fn210.debit <=?) or (fn210.credit >=? and fn210.credit <=?)) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && (cashDayClearTcFindDTO.getMoneyEnd().trim() == null || cashDayClearTcFindDTO.getMoneyEnd().trim().equals(""))) {//有开始金额无结束金额
            criterion += " (fn210.debit >= ? or fn210.credit >=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
          }
          
          if (cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("") && (cashDayClearTcFindDTO.getMoneySt().trim() == null || cashDayClearTcFindDTO.getMoneySt().trim().equals(""))) {//无开始金额有结束金额
            criterion += " (fn210.debit <= ? or fn210.credit <=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.credence_num >= to_number(?) and fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumEnd().trim() == null || cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.credence_num >= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumSt().trim() == null || cashDayClearTcFindDTO.getCredenceNumSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          
          if(cashDayClearTcFindDTO.getType().equals("1")){
            if (cashDayClearTcFindDTO.getCredenceSt().trim() != null && !cashDayClearTcFindDTO.getCredenceSt().trim().equals("")) {
              criterion += " fn210.credence_st = ? and ";
              parameters.add(cashDayClearTcFindDTO.getCredenceSt().trim());
            }
          }else if(cashDayClearTcFindDTO.getType().equals("3")){
            criterion += " fn210.credence_st = '2' and ";
          }else{
            criterion += " fn210.credence_st = '0' and ";
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("")) {//有开始结算日期结束结算日期
            criterion += " fn210.sett_date >= ? and fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && (cashDayClearTcFindDTO.getSettDateEnd().trim() == null || cashDayClearTcFindDTO.getSettDateEnd().trim().equals(""))) {//有开始结算日期无结束结算日期
            criterion += " fn210.sett_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getSettDateSt().trim() == null || cashDayClearTcFindDTO.getSettDateSt().trim().equals(""))) {//无开始结算日期有结束结算日期
            criterion += " fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceCharacter().trim() != null && !cashDayClearTcFindDTO.getCredenceCharacter().trim().equals("")) {
            criterion += " fn210.credence_character=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceCharacter().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettType().trim() != null && !cashDayClearTcFindDTO.getSettType().trim().equals("")) {
            criterion += " fn210.sett_type=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettType().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettNum().trim() != null && !cashDayClearTcFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn210.sett_num=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettNum().trim());
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
              CashDayClearTcShowListDTO cashDayClearTcShowListDTO = new CashDayClearTcShowListDTO();
              if (obj[0] != null) {
                cashDayClearTcShowListDTO.setDebit(new BigDecimal(obj[0].toString()));
              }
              if (obj[1] != null) {
                cashDayClearTcShowListDTO.setCredit(new BigDecimal(obj[1].toString()));
              }
              cashDayClearTcShowListDTO.setCredenceId(obj[2].toString());
              temp_list.add(cashDayClearTcShowListDTO);
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
   * 现金日记账
   * @author 郭婧平
   * 2007-10-18
   * 查找fn210表中有没有符合查询条件的数据
   * 查询条件：credenceId,credenceSt=0
   */
  public List queryTreasurerCredenceByCredenceSt(final String credenceId) {
    List list=new ArrayList();
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select treasurerCredence.credenceId from TreasurerCredence treasurerCredence " +
                "where treasurerCredence.credenceId=? and treasurerCredence.credenceSt='0' ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(credenceId));
            return query.list();
          }
        });
    return list;
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-18
   * 根据credenceId查询fn210表中的字段
   * 查询条件：credenceId
   */
  public TreasurerCredence queryAcredenceIdByCredenceId(final String credenceId) {
    TreasurerCredence treasurerCredence=null;
    treasurerCredence = (TreasurerCredence)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select treasurerCredence.acredenceId,treasurerCredence.credenceNum,treasurerCredence.credenceDate," +
                "treasurerCredence.office from TreasurerCredence treasurerCredence " +
                "where treasurerCredence.credenceId=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(credenceId));
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            TreasurerCredence trCredence=new TreasurerCredence();
            if (obj != null) {
                if (obj[0] != null) {
                  trCredence.setAcredenceId(obj[0].toString());
                }
                if (obj[1] != null) {
                  trCredence.setCredenceNum(obj[1].toString());
                }
                if (obj[2] != null) {
                  trCredence.setCredenceDate(obj[2].toString());
                }
                if (obj[3] != null) {
                  trCredence.setOffice(obj[3].toString());
                }
            }
            return trCredence;
          }
        });
    return treasurerCredence;
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-18
   * 根据fn210.ACREDENCE_ID=fn201.CREDENCE_ID更新FN201.CASH_ACC_ST为0
   * 查询条件：fn210.ACREDENCE_ID
   */
  public void updateAccountantCredence(final String acredenceId,final String cachAccSt)throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update AccountantCredence accountantCredence set accountantCredence.cashAccSt=? "
              + "where accountantCredence.credenceId=? ";
          session.createQuery(hql).setParameter(0, cachAccSt).setParameter(1, new Integer(acredenceId)).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 银行存款日记账
   * @author 郭婧平
   * 2007-10-23
   * 根据fn210.ACREDENCE_ID=fn201.CREDENCE_ID更新FN201.BANK_ACC_ST为0
   * 查询条件：fn210.ACREDENCE_ID
   */
  public void updateBankAccountantCredence(final String acredenceId,final String bankAccSt)throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update AccountantCredence accountantCredence set accountantCredence.bankAccSt=? "
              + "where accountantCredence.credenceId=? ";
          session.createQuery(hql).setParameter(0, bankAccSt).setParameter(1, new Integer(acredenceId)).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 出纳扎账
   * @author 郭婧平
   * 2007-11-07
   * 根据fn210.CREDENCE_ID更新FN210.CREDENCE_ST=2，FN210.CLEARPSN=操作员
   * 查询条件：fn210.CREDENCE_ID
   */
  public void updateCredenceSt(final String credenceId,final String clearpsn)throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update TreasurerCredence treasurerCredence set treasurerCredence.credenceSt='2', " +
              " treasurerCredence.clearpsn = ? "
              + "where treasurerCredence.credenceId=? ";
          session.createQuery(hql).setParameter(0, clearpsn).setParameter(1, new Integer(credenceId)).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-18
   * 根据fn210.CREDENCE_ID删除该条记录
   */
  public void deleteTreasurerCredence(final String credenceId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from TreasurerCredence treasurerCredence where treasurerCredence.credenceId= ? ";
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
   * 现金日记账--维护页面--修改
   * @author 郭婧平
   * 2007-10-19
   * 根据fn210.CREDENCE_ID查询相应的数据
   */
  public CashDayClearTaDTO queryTreasurerCredenceByCredenceId(final String credenceId) {
    CashDayClearTaDTO cashDayClearTaDTO=null;
    cashDayClearTaDTO = (CashDayClearTaDTO)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select treasurerCredence.office,treasurerCredence.subjectCode,treasurerCredence.credenceDate," +
                "treasurerCredence.credenceCharacter,treasurerCredence.summray,treasurerCredence.debit,treasurerCredence.credit" +
                ",treasurerCredence.settType,treasurerCredence.settNum,treasurerCredence.dopsn,treasurerCredence.settDate" +
                ",treasurerCredence.credenceId,treasurerCredence.credenceNum,treasurerCredence.acredenceId from TreasurerCredence treasurerCredence " +
                "where treasurerCredence.credenceId=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(credenceId));
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            CashDayClearTaDTO dto=new CashDayClearTaDTO();
            if (obj != null) {
              dto.setOffice(obj[0].toString());
              dto.setSubjectCode(obj[1].toString());
              if (obj[2] != null) {
                dto.setCredenceDate(obj[2].toString());
              }
              if (obj[3] != null) {
                dto.setCredenceCharacter(obj[3].toString());
              }
              if (obj[4] != null) {
                dto.setSummray(obj[4].toString());
              }
              dto.setDebit(new BigDecimal(obj[5].toString()));
              dto.setCredit(new BigDecimal(obj[6].toString()));
              if (obj[7] != null) {
                dto.setSettType(obj[7].toString());
              }
              if (obj[8] != null) {
                dto.setSettNum(obj[8].toString());
              }
              if (obj[9] != null) {
                dto.setDopsn(obj[9].toString());
              }
              if (obj[10] != null) {
                dto.setSettDate(obj[10].toString());
              }
              dto.setCredenceId(obj[11].toString());
              if (obj[12] != null) {
                dto.setCredenceNum(obj[12].toString());
              }
              if (obj[13] != null) {
                dto.setAcredenceId(obj[13].toString());
              }
            }
            return dto;
          }
        });
    return cashDayClearTaDTO;
  }
  /**
   * 现金日记账--维护页面--打印
   * @author 郭婧平
   * 2007-10-19
   * 根据查询条件查找FN210表中的数据
   * 查询条件：credenceDate,summary,subjectCode,subjectName,money,credenceNum,credenceSt
   */
  public List queryCashDayClearTcListPrint(final CashDayClearTcFindDTO cashDayClearTcFindDTO,final List officeList,final String credenceType,final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn210.credence_date,fn210.credence_num,fn210.credence_character " +
              ",fn210.summray,fn210.subject_code,fn110.subject_name,fn210.debit,fn210.credit " +
              ",fn210.sett_num,fn210.sett_type,fn210.dopsn,fn210.makebill,fn210.credence_st,fn210.credence_id,fn210.sett_date "
              + "from FN210 fn210,FN110 fn110,FN102 fn102 "
              + "where fn210.subject_code=fn110.subject_code " 
              + "and fn210.book_id=fn110.book_id "
              + "and fn210.summray=fn102.para_id "
              + "and fn210.book_id=fn102.book_id " 
              + "and (fn102.param_value  >'3' or fn102.param_value < '3') ";
          Vector parameters = new Vector();
          String criterion = "";
          
          criterion += "fn210.credence_type = ? and ";
          parameters.add(credenceType);
            
          criterion += "fn210.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (cashDayClearTcFindDTO.getOffice().trim() != null&&!cashDayClearTcFindDTO.getOffice().trim().equals("")) {
            criterion += " and fn210.office=? and ";
            parameters.add(cashDayClearTcFindDTO.getOffice().trim());
          }else{
            if (officeList != null && officeList.size() > 0) {
              criterion += "and ( ";
              for (int i = 0; i < officeList.size(); i++) {
                criterion += " fn210.office = ? or ";
                parameters.add(officeList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and ";
            }
          }
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.credence_date >=? and fn210.credence_date <=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateEnd().trim() == null || cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.credence_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateSt().trim() == null || cashDayClearTcFindDTO.getCredenceDateSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.credence_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTcFindDTO.getSummary().trim() != null && !cashDayClearTcFindDTO.getSummary().trim().equals("")) {
            criterion += " fn210.summray=? and ";
            parameters.add(cashDayClearTcFindDTO.getSummary().trim());
          }
          
          if (cashDayClearTcFindDTO.getSubjectCode().trim() != null && !cashDayClearTcFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn210.subject_code like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectCode().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getSubjectName().trim() != null && !cashDayClearTcFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectName().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("")) {//有开始金额结束金额
            criterion += " ((fn210.debit >=? and fn210.debit <=?) or (fn210.credit >=? and fn210.credit <=?)) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && (cashDayClearTcFindDTO.getMoneyEnd().trim() == null || cashDayClearTcFindDTO.getMoneyEnd().trim().equals(""))) {//有开始金额无结束金额
            criterion += " (fn210.debit >= ? or fn210.credit >=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
          }
          
          if (cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("") && (cashDayClearTcFindDTO.getMoneySt().trim() == null || cashDayClearTcFindDTO.getMoneySt().trim().equals(""))) {//无开始金额有结束金额
            criterion += " (fn210.debit <= ? or fn210.credit <=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.credence_num >= to_number(?) and fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumEnd().trim() == null || cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.credence_num >= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumSt().trim() == null || cashDayClearTcFindDTO.getCredenceNumSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          
          if(cashDayClearTcFindDTO.getType().equals("1")){
            if (cashDayClearTcFindDTO.getCredenceSt().trim() != null && !cashDayClearTcFindDTO.getCredenceSt().trim().equals("")) {
              criterion += " fn210.credence_st = ? and ";
              parameters.add(cashDayClearTcFindDTO.getCredenceSt().trim());
            }
          }else{
            criterion += " fn210.credence_st = '0' and ";
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("")) {//有开始结算日期结束结算日期
            criterion += " fn210.sett_date >= ? and fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && (cashDayClearTcFindDTO.getSettDateEnd().trim() == null || cashDayClearTcFindDTO.getSettDateEnd().trim().equals(""))) {//有开始结算日期无结束结算日期
            criterion += " fn210.sett_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getSettDateSt().trim() == null || cashDayClearTcFindDTO.getSettDateSt().trim().equals(""))) {//无开始结算日期有结束结算日期
            criterion += " fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceCharacter().trim() != null && !cashDayClearTcFindDTO.getCredenceCharacter().trim().equals("")) {
            criterion += " fn210.credence_character=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceCharacter().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettType().trim() != null && !cashDayClearTcFindDTO.getSettType().trim().equals("")) {
            criterion += " fn210.sett_type=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettType().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettNum().trim() != null && !cashDayClearTcFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn210.sett_num=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettNum().trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
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
              CashDayClearTcShowListDTO cashDayClearTcShowListDTO = new CashDayClearTcShowListDTO();
              if (obj[0] != null) {
                cashDayClearTcShowListDTO.setCredenceDate(obj[0].toString());
              }
              if (obj[1] != null) {
                cashDayClearTcShowListDTO.setCredenceNum(obj[1].toString());
              }
              if (obj[2] !=null) {
                cashDayClearTcShowListDTO.setCredenceCharacter(obj[2].toString());
              }
              if (obj[3] != null) {
                cashDayClearTcShowListDTO.setSummary(obj[3].toString());
              }
              if (obj[4] != null) {
                cashDayClearTcShowListDTO.setSubjectCode(obj[4].toString());
              }
              cashDayClearTcShowListDTO.setSubjectName(obj[5].toString());
              if (obj[6] != null) {
                cashDayClearTcShowListDTO.setDebit(new BigDecimal(obj[6].toString()));
              }
              if (obj[7] != null) {
                cashDayClearTcShowListDTO.setCredit(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                cashDayClearTcShowListDTO.setSettNum(obj[8].toString());
              }
              if (obj[9] != null) {
                cashDayClearTcShowListDTO.setSettType(obj[9].toString());
              }
              if (obj[10] != null) {
                cashDayClearTcShowListDTO.setDopsn(obj[10].toString());
              }
              if (obj[11] != null) {
                cashDayClearTcShowListDTO.setMakebill(obj[11].toString());
              }
              if (obj[12] != null) {
                cashDayClearTcShowListDTO.setCredenceSt(obj[12].toString());
              }
              cashDayClearTcShowListDTO.setCredenceId(obj[13].toString());
              if (obj[14] != null) {
                cashDayClearTcShowListDTO.setSettDate(obj[14].toString());
              }
              temp_list.add(cashDayClearTcShowListDTO);
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
  public List queryCashDayClearTcListPrint_wsh(final CashDayClearTcFindDTO cashDayClearTcFindDTO,final List officeList,final String credenceType,final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn210.credence_date,fn210.credence_num,fn210.credence_character " +
              ",fn210.summray,fn210.subject_code,fn110.subject_name,fn210.debit,fn210.credit " +
              ",fn210.sett_num,fn210.sett_type,fn210.dopsn,fn210.makebill,fn210.credence_st,fn210.credence_id,fn210.sett_date "
              + "from FN210 fn210,FN110 fn110,FN102 fn102 "
              + "where fn210.subject_code=fn110.subject_code " 
              + "and fn210.book_id=fn110.book_id "
              + "and fn210.summray=fn102.para_id "
              + "and fn210.book_id=fn102.book_id " 
              + "and (fn102.param_value  >'3' or fn102.param_value < '3') ";
          Vector parameters = new Vector();
          String criterion = "";
          
          criterion += "fn210.credence_type = ? and ";
          parameters.add(credenceType);
            
          criterion += "fn210.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (cashDayClearTcFindDTO.getOffice().trim() != null&&!cashDayClearTcFindDTO.getOffice().trim().equals("")) {
            criterion += " and fn210.office=? and ";
            parameters.add(cashDayClearTcFindDTO.getOffice().trim());
          }else{
            if (officeList != null && officeList.size() > 0) {
              criterion += "and ( ";
              for (int i = 0; i < officeList.size(); i++) {
                criterion += " fn210.office = ? or ";
                parameters.add(officeList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and ";
            }
          }
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.credence_date >=? and fn210.credence_date <=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateEnd().trim() == null || cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.credence_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceDateEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceDateSt().trim() == null || cashDayClearTcFindDTO.getCredenceDateSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.credence_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceDateEnd().trim());
          }

          if (cashDayClearTcFindDTO.getSummary().trim() != null && !cashDayClearTcFindDTO.getSummary().trim().equals("")) {
            criterion += " fn210.summray=? and ";
            parameters.add(cashDayClearTcFindDTO.getSummary().trim());
          }
          
          if (cashDayClearTcFindDTO.getSubjectCode().trim() != null && !cashDayClearTcFindDTO.getSubjectCode().trim().equals("")) {
            criterion += " fn210.subnconject_code like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectCode().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getSubjectName().trim() != null && !cashDayClearTcFindDTO.getSubjectName().trim().equals("")) {
            criterion += " fn110.subject_name like ? and ";
            parameters.add(cashDayClearTcFindDTO.getSubjectName().trim()+"%");
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("")) {//有开始金额结束金额
            criterion += " ((fn210.debit >=? and fn210.debit <=?) or (fn210.credit >=? and fn210.credit <=?)) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getMoneySt().trim() != null&&!cashDayClearTcFindDTO.getMoneySt().trim().equals("") && (cashDayClearTcFindDTO.getMoneyEnd().trim() == null || cashDayClearTcFindDTO.getMoneyEnd().trim().equals(""))) {//有开始金额无结束金额
            criterion += " (fn210.debit >= ? or fn210.credit >=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
            parameters.add(cashDayClearTcFindDTO.getMoneySt());
          }
          
          if (cashDayClearTcFindDTO.getMoneyEnd().trim() != null&&!cashDayClearTcFindDTO.getMoneyEnd().trim().equals("") && (cashDayClearTcFindDTO.getMoneySt().trim() == null || cashDayClearTcFindDTO.getMoneySt().trim().equals(""))) {//无开始金额有结束金额
            criterion += " (fn210.debit <= ? or fn210.credit <=?) and ";
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
            parameters.add(cashDayClearTcFindDTO.getMoneyEnd());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.credence_num >= to_number(?) and fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumSt().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumSt().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumEnd().trim() == null || cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.credence_num >= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceNumEnd().trim() != null&&!cashDayClearTcFindDTO.getCredenceNumEnd().trim().equals("") && (cashDayClearTcFindDTO.getCredenceNumSt().trim() == null || cashDayClearTcFindDTO.getCredenceNumSt().trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.credence_num <= to_number(?) and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceNumEnd().trim());
          }
          
          if(cashDayClearTcFindDTO.getType().equals("1")){
            if (cashDayClearTcFindDTO.getCredenceSt().trim() != null && !cashDayClearTcFindDTO.getCredenceSt().trim().equals("")) {
              criterion += " fn210.credence_st = ? and ";
              parameters.add(cashDayClearTcFindDTO.getCredenceSt().trim());
            }
          }else{
            criterion += " fn210.credence_st = '0' and ";
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("")) {//有开始结算日期结束结算日期
            criterion += " fn210.sett_date >= ? and fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateSt().trim() != null&&!cashDayClearTcFindDTO.getSettDateSt().trim().equals("") && (cashDayClearTcFindDTO.getSettDateEnd().trim() == null || cashDayClearTcFindDTO.getSettDateEnd().trim().equals(""))) {//有开始结算日期无结束结算日期
            criterion += " fn210.sett_date >= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateSt().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettDateEnd().trim() != null&&!cashDayClearTcFindDTO.getSettDateEnd().trim().equals("") && (cashDayClearTcFindDTO.getSettDateSt().trim() == null || cashDayClearTcFindDTO.getSettDateSt().trim().equals(""))) {//无开始结算日期有结束结算日期
            criterion += " fn210.sett_date <= ? and ";
            parameters.add(cashDayClearTcFindDTO.getSettDateEnd().trim());
          }
          
          if (cashDayClearTcFindDTO.getCredenceCharacter().trim() != null && !cashDayClearTcFindDTO.getCredenceCharacter().trim().equals("")) {
            criterion += " fn210.credence_character=? and ";
            parameters.add(cashDayClearTcFindDTO.getCredenceCharacter().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettType().trim() != null && !cashDayClearTcFindDTO.getSettType().trim().equals("")) {
            criterion += " fn210.sett_type=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettType().trim());
          }
          
          if (cashDayClearTcFindDTO.getSettNum().trim() != null && !cashDayClearTcFindDTO.getSettNum().trim().equals("")) {
            criterion += " fn210.sett_num=? and ";
            parameters.add(cashDayClearTcFindDTO.getSettNum().trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
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
              CashDayClearTcShowListDTO cashDayClearTcShowListDTO = new CashDayClearTcShowListDTO();
              if (obj[0] != null) {
                cashDayClearTcShowListDTO.setCredenceDate(obj[0].toString());
              }
              if (obj[1] != null) {
                cashDayClearTcShowListDTO.setCredenceNum(obj[1].toString());
              }
              if (obj[2] !=null) {
                cashDayClearTcShowListDTO.setCredenceCharacter(obj[2].toString());
              }
              if (obj[3] != null) {
                cashDayClearTcShowListDTO.setSummary(obj[3].toString());
              }
              if (obj[4] != null) {
                cashDayClearTcShowListDTO.setSubjectCode(obj[4].toString());
              }
              cashDayClearTcShowListDTO.setSubjectName(obj[5].toString());
              if (obj[6] != null) {
                cashDayClearTcShowListDTO.setDebit(new BigDecimal(obj[6].toString()));
              }
              if (obj[7] != null) {
                cashDayClearTcShowListDTO.setCredit(new BigDecimal(obj[7].toString()));
              }
              if (obj[8] != null) {
                cashDayClearTcShowListDTO.setSettNum(obj[8].toString());
              }
              if (obj[9] != null) {
                cashDayClearTcShowListDTO.setSettType(obj[9].toString());
              }
              if (obj[10] != null) {
                cashDayClearTcShowListDTO.setDopsn(obj[10].toString());
              }
              if (obj[11] != null) {
                cashDayClearTcShowListDTO.setMakebill(obj[11].toString());
              }
              if (obj[12] != null) {
                cashDayClearTcShowListDTO.setCredenceSt(obj[12].toString());
              }
              cashDayClearTcShowListDTO.setCredenceId(obj[13].toString());
              if (obj[14] != null) {
                cashDayClearTcShowListDTO.setSettDate(obj[14].toString());
              }
              temp_list.add(cashDayClearTcShowListDTO);
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
   * 张列
   * 判断FN210表中是否存在SUMMAY=3 and OFFICE=所选办事处的记录
   * @param bookId officeName
   * @return String
   */
  public String is_Balanceinitialize_ZL(final String bookId,final String officeName){
    Validate.notNull(bookId);
    String stutas = "";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(ff.credence_id) from fn210 ff " +
              "where ff.summray = (select ff.para_id from fn102 ff where " +
              "ff.book_id = ? and ff.param_num = '4' and ff.param_value = '3') " +
              "and ff.office = ? and ff.book_Id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, officeName);
          query.setParameter(2, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }
  
  /**
   * 删除FN210
       FN210.SUMMAY=3 and FN210.OFFICE=所选办事处的记录
   * 张列
   */
  public void deleteSummay_ZL(final String bookId,final String officeName)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from TreasurerCredence tc where tc.summray = " +
              "(select bp.paraId from BookParameter bp where bp.bookId = ? and " +
              "bp.paramNum = '4' and bp.paramValue = '3') and tc.office = ? and tc.bookId = ?";
          Query query=session.createQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, officeName);
          query.setParameter(2, bookId);
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  /**
   * 删除FN210
       FN210.SUMMAY=3 and FN210.OFFICE=所选办事处的记录
   * 张列
   */
  public void deleteSummay_ZL1(final String bookId,final String officeName)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from BankCredence tc where tc.summary = " +
              "(select bp.paraId from BookParameter bp where bp.bookId = ? and " +
              "bp.paramNum = '4' and bp.paramValue = '3') and tc.office = ? and tc.bookId = ?";
          Query query=session.createQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, officeName);
          query.setParameter(2, bookId);
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * 张列 获得Summay的 id值
   * 
   * @param bookId
   * @return String
   */
  public String getSummay(final String bookId) {
    Validate.notNull(bookId);
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select ff.para_id from fn102 ff where " +
              "ff.book_id=? and ff.param_num='4' and ff.param_value='3'";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  /**
   * 银行存款对账单--银行日记账列表
   * @author 郭婧平
   * 2007-10-30
   * 根据查询条件查找FN210表中的数据
   * 查询条件：settDate,subjectCode
   */
  public List queryDepositCheckAccBdcList(final String settDateSt,final String settDateEnd,final String subjectCode,final List officeList,final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select fn210.sett_date,fn210.subject_code,fn210.sett_type," +
              "fn210.sett_num,fn210.credence_character,fn210.credence_num," +
              "fn210.debit,fn210.credit,fn210.credence_date,fn210.credence_id " +
              "from FN210 fn210 " +
              "where fn210.credence_type='1' " +
              "and fn210.credence_st='2' " +
              "and fn210.sett_date is not null ";
          Vector parameters = new Vector();
          String criterion = "";
          
          criterion += "fn210.book_id = ? ";
          parameters.add(securityInfo.getBookId());
          
          if (officeList != null && officeList.size() > 0) {
            criterion += "and ( ";
            for (int i = 0; i < officeList.size(); i++) {
              criterion += " fn210.office = ? or ";
              parameters.add(officeList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and ";
          }
          if (settDateSt.trim() != null&&!settDateSt.trim().equals("") && settDateEnd.trim() != null&&!settDateEnd.trim().equals("")) {//有开始日期结束日期
            criterion += " fn210.sett_date >=? and fn210.sett_date <=? and ";
            parameters.add(settDateSt.trim());
            parameters.add(settDateEnd.trim());
          }
          
          if (settDateSt.trim() != null&&!settDateSt.trim().equals("") && (settDateEnd.trim() == null || settDateEnd.trim().equals(""))) {//有开始日期无结束日期
            criterion += " fn210.sett_date >= ? and ";
            parameters.add(settDateSt.trim());
          }
          
          if (settDateEnd.trim() != null&&!settDateEnd.trim().equals("") && (settDateSt.trim() == null || settDateSt.trim().equals(""))) {//无开始日期有结束日期
            criterion += " fn210.sett_date <= ? and ";
            parameters.add(settDateEnd.trim());
          }

          if (subjectCode.trim() != null && !subjectCode.trim().equals("")) {
            criterion += " fn210.subject_code=? and ";
            parameters.add(subjectCode.trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion +" order by fn210.sett_date DESC,fn210.sett_num DESC";
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
              DepositCheckAccBdcDTO depositCheckAccBdcDTO = new DepositCheckAccBdcDTO();
              if (obj[0] != null) {
                depositCheckAccBdcDTO.setSettDate(obj[0].toString());
              }
              depositCheckAccBdcDTO.setSubjectCode(obj[1].toString());
              if (obj[2] != null) {
                depositCheckAccBdcDTO.setSettType(obj[2].toString());
              }
              if (obj[3] != null) {
                depositCheckAccBdcDTO.setSettNum(obj[3].toString());
              }
              if (obj[4] != null) {
                depositCheckAccBdcDTO.setCredenceCharacter(obj[4].toString());
              }
              if(obj[5] !=null){
                depositCheckAccBdcDTO.setCredenceNum(obj[5].toString());
              }
              depositCheckAccBdcDTO.setDebit(new BigDecimal(obj[6].toString()));
              depositCheckAccBdcDTO.setCredit(new BigDecimal(obj[7].toString()));
              if (obj[8] != null) {
                depositCheckAccBdcDTO.setCredenceDate(obj[8].toString());
              }
              depositCheckAccBdcDTO.setCredenceId(obj[9].toString());
              temp_list.add(depositCheckAccBdcDTO);
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
   * 银行存款对账单--银行存款余额调节表window
   * @author 郭婧平
   * 2007-11-1
   * 查询页面所要显示的信息
   */
  public List queryDepositCheckAccWindowBaseList(final List bdcIdList,final List bcaIdList) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String bdcId = "";
          String bcaId = "";
          for (int i = 0; i < bdcIdList.size(); i++) {
            String temp_bdcId = (String) bdcIdList.get(i);
            bdcId = bdcId + temp_bdcId + ",";
          }
          if(!bdcId.equals("")){
            bdcId = bdcId.substring(0, bdcId.lastIndexOf(","));
          }else{
            bdcId="null";
          }
          
          for (int i = 0; i < bcaIdList.size(); i++) {
            String temp_bcaId = (String) bcaIdList.get(i);
            bcaId = bcaId + temp_bcaId + ",";
          }
          if(!bcaId.equals("")){
            bcaId = bcaId.substring(0, bcaId.lastIndexOf(","));
          }else{
            bcaId="null";
          }
          String hql = "select fn210.credence_id,fn210.sett_date,fn210.sett_num,fn210.debit as money,'1' as type " +
              "from FN210 fn210 where fn210.debit>0 " +
              "and fn210.credence_id in( " +
              bdcId +
              ") " +
              "union " +
              "select fn210.credence_id,fn210.sett_date,fn210.sett_num,fn210.credit as money,'2' as type " +
              "from FN210 fn210 where fn210.debit=0 " +
              "and fn210.credence_id in( " +
              bdcId +
              ") " +
              "union " +
              "select fn211.credence_id,fn211.sett_date,fn211.sett_num,fn211.debit as money,'3' as type " +
              "from FN211 fn211 where fn211.debit>0 " +
              "and fn211.credence_id in( " +
              bcaId +
              ") " +
              "union " +
              "select fn211.credence_id,fn211.sett_date,fn211.sett_num,fn211.credit as money,'4' as type " +
              "from FN211 fn211 where fn211.debit=0 " +
              "and fn211.credence_id in( " +
              bcaId +
              ") " ;
          
          Query query = session.createSQLQuery(hql);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO = new DepositCheckAccWindowBaseDTO();
              if (obj[1] != null) {
                depositCheckAccWindowBaseDTO.setSettDate(obj[1].toString());
              }
              if (obj[2] != null) {
                depositCheckAccWindowBaseDTO.setSettNum(obj[2].toString());
              }
              if (obj[3] != null) {
                depositCheckAccWindowBaseDTO.setMoney(new BigDecimal(obj[3].toString()));
              }
              depositCheckAccWindowBaseDTO.setType(obj[4].toString());
              temp_list.add(depositCheckAccWindowBaseDTO);
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
   * 查询弹出框列表中的内容
   * 郭婧平
   * @param docNum 凭证号
   * @param bookId bookId
   * @return
   */
  public List queryCredencePopList(final String docNum, final String bookId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f210.summray,"
              + "f210.subject_code,f210.debit,f210.credit,f110.subject_name "
              + "from fn210 f210, fn110 f110 " 
              + "where f210.subject_code = f110.subject_code "
              + "and f210.book_id = f110.book_id "
              + "and f210.credence_num = ? "
              + "and f210.book_id=? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, docNum);
          query.setParameter(1, bookId);

          Iterator it = query.list().iterator();
          CredencePopListDTO credencePopListDTO = null;
          List temp_credencePopListDTOList = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            credencePopListDTO = new CredencePopListDTO();
            obj = (Object[]) it.next();
            if (obj[0]!=null) {
              credencePopListDTO.setSummay(obj[0].toString());
            }
            if (obj[1]!=null) {
              credencePopListDTO.setSubjectCode(obj[1].toString());
            }
            if (obj[2]!=null) {
              credencePopListDTO.setDebit(new BigDecimal(obj[2].toString()));
            }
            if (obj[3]!=null) {
              credencePopListDTO.setCredit(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              credencePopListDTO.setSubjectName(obj[4].toString());
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
   * 郭婧平
   * @param docNum 凭证号
   * @param bookId bookId
   * @return
   */
  public CredencePopInfoDTO queryCredencePopInfo(final String docNum,
      final String bookId) {
    CredencePopInfoDTO credencePopInfoDTO = null;
    try {
      credencePopInfoDTO = (CredencePopInfoDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select "
                  + "max(f210.office),"
                  + "max(f210.credence_character),"
                  + "max(f210.credence_num),"
                  + "max(f210.old_credence_num),"
                  + "max(f210.credence_date),"
                  + "max(f210.sett_num),"
                  + "max(f210.sett_date),"
                  + "max(f210.clearpsn),"
                  + "max(f210.makebill),"
                  + "max(f210.account_charge),"
                  + "max(f210.sett_type) "
                  + "from fn210 f210 where f210.credence_num=? and f210.book_id=?";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, docNum);
              query.setParameter(1, bookId);

              Object[] obj = (Object[]) query.uniqueResult();
              CredencePopInfoDTO credencePopInfoDTO = new CredencePopInfoDTO();
              if (obj[0]!=null) {
                credencePopInfoDTO.setOffice(obj[0].toString());
              }
              if (obj[1]!=null) {
                credencePopInfoDTO.setCredenceCharacter(obj[1].toString());
              }
              if (obj[2]!=null) {
                credencePopInfoDTO.setCredenceNum(obj[2].toString());
              }
              if (obj[3]!=null) {
                credencePopInfoDTO.setOldCredenceNum(obj[3].toString());
              }
              if (obj[4]!=null) {
                credencePopInfoDTO.setCredenceDate(obj[4].toString());
              }
              if (obj[5]!=null) {
                credencePopInfoDTO.setSettNum(obj[5].toString());
              }
              if (obj[6]!=null) {
                credencePopInfoDTO.setSettDate(obj[6].toString());
              }
              if (obj[7]!=null) {
                credencePopInfoDTO.setClearpsn(obj[7].toString());
              }
              if (obj[8]!=null) {
                credencePopInfoDTO.setMakebill(obj[8].toString());
                credencePopInfoDTO.setAccountpsn(obj[8].toString());
              }
              if (obj[9]!=null) {
                credencePopInfoDTO.setAccountCharge(obj[9].toString());
              }
              if (obj[10]!=null) {
                credencePopInfoDTO.setSettType(obj[10].toString());
              }
              return credencePopInfoDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return credencePopInfoDTO;
  }
  
 
  public String getFBizDate(final String bookId, final String makebill, final String type) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select max(t.credence_date) from fn210 t " +
              "where t.book_id = ? and t.credence_st = '2' and t.makebill = ? and t.credence_type = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, makebill);
          query.setParameter(2, type);
          if(query.uniqueResult() != null && !query.uniqueResult().equals("")){
            return query.uniqueResult().toString();
          }
          return "";
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
}