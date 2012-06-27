package org.xpup.hafmis.syscollection.common.dao;

import java.sql.SQLException;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class InspectionDAO extends HibernateDaoSupport{
  
  /**
   * bit
   * @param date
   * @param officeid
   * @return 公积金实际缴存额
   */
  public String actualpay(final String date,final String officeid,final String subjectCode){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
//          String sql="SELECT SUM(aa101.CREDIT - aa101.DEBIT) FROM AA101 aa101 WHERE aa101.BIZ_TYPE IN ('A','B','C','K', 'H','M') AND aa101.BIZ_STATUS='5'";
          Vector v=new Vector();
          String c = "";
          String sql=" SELECT SUM(fn201.CREDIT - fn201.DEBIT)"+
            " FROM fn201 fn201"+
            " WHERE fn201.book_id = '1'"+
            " and fn201.credence_st = '2'"+
            " and fn201.subject_code like '201%'";
          if (date!=null&&!date.equals(""))
          {
            c+=" SUBSTR(fn201.credence_date, 1, 4)=? and ";
            v.add(date);
          }
          if(officeid!=null&&!officeid.equals("")){
            c+=" fn201.office=? and";
            v.add(officeid);
          }
          if (c.length() != 0)
            c =" and "
                + c.substring(0, c.lastIndexOf("and"));
          sql=sql+c;
          Query query=session.createSQLQuery(sql);
          for(int i=0;i<v.size();i++){
            query.setParameter(i, v.get(i));
          }
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * bit add
   * 当年住房公积金应缴存额
   */
  public String querythisyearshouldpay(final String date,final String officeid){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
//          String sql=" select sum(t.org_pay + t.emp_pay) "+
//                     " from aa305_1 t, aa001 s, ba001 b "+
//                     " where t.org_id = s.id "+
//                     " and s.orginfo_id = b.id ";
          String sql=" select sum(nvl(t.org_pay,0) + nvl(t.emp_pay,0))"+
          " from aa305_1 t, aa001 s, ba001 b"+
          " where t.org_id = s.id"+
          " and s.orginfo_id = b.id"+
          " and t.open_status='2'"+
          " and substr(t.year_month, 1, 6) = ( select max(substr(t.year_month, 1, 6))"+
          " from aa305_1 t, aa001 s, ba001 b"+
          " where t.org_id = s.id"+
          " and s.orginfo_id = b.id"+
          " and t.open_status='2'"+
          " and substr(t.year_month, 1, 4) = ? and b.officecode =? )";
//          Vector v=new Vector();
//          String c="";
//          if (date!=null&&!date.equals(""))
//          {
//            c+=" substr(t.year_month, 1, 4)=? and ";
//            v.add(date);
//          }
//          if(officeid!=null&&!officeid.equals("")){
//            c+=" b.officecode =? and ";
//            v.add(officeid);
//          }
//          if (c.length() != 0)
//            c = " and "
//                + c.substring(0, c.lastIndexOf("and"));
//          sql=sql+c;
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
//          for(int i=0;i<v.size();i++){
//            query.setParameter(i, v.get(i));
//          }
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * bit add
   * 年末住房公积金个人贷款余额
   */
  public String yearlastpersonloanbalance(final String date,final String officeid,final String date1,final String officeid1){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="select (SELECT NVL(SUM(pl202.OCCUR_MONEY), 0) "
           +"FROM PL202 pl202,pl203 pl203,pl110 pl110 "
           +"WHERE (pl202.BIZ_TYPE = '1' OR "
           +"(pl202.BIZ_TYPE = '12' AND pl202.WRONG_BIZ_TYPE = '1')) "
           +"and pl202.flow_head_id=pl203.flow_head_id "
           +"and pl203.contract_id=pl110.contract_id "
           +"AND substr(pl202.BIZ_DATE, 1, 4) <= ? "
           +"and pl110.office=? "
           +"AND pl202.BIZ_ST = 6) - "
           +"(SELECT NVL(SUM(pl202.Real_Count + pl202.Real_Overdue_Corpus), 0) "
           +"FROM PL202 pl202,pl203 pl203,pl110 pl110 "
           +"WHERE pl202.flow_head_id=pl203.flow_head_id " 
           +"and pl203.contract_id=pl110.contract_id "
           +"and substr(pl202.BIZ_DATE, 1, 4) <= ? "
           +"and pl110.office=?"
           +"AND pl202.BIZ_ST = 6) "
           +"from dual";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
          query.setParameter(2, date1);
          query.setParameter(3, officeid1);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  //yqf 年末住房公积金个人贷款余额 修改至上面方法
  public String yearlastpersonloanbalance_(final String date,final String office,final String subjectCode){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
//          String sql=" select sum(t.debit - t.credit) from fn201 t ";
          String sql=" SELECT SUM(fn201.DEBIT - fn201.CREDIT)"+
                     " FROM fn201 fn201"+
                     " WHERE fn201.book_id = '1'"+
                     " and fn201.credence_st = '2'"+
                     " and fn201.subject_code like '121%'";
          Vector v=new Vector();
          String c="";
          if (date!=null&&!date.equals(""))
          {
            c+=" SUBSTR(fn201.credence_date, 1, 4) <= ? and ";
            v.add(date);
          }
          if(office!=null&&!office.equals("")){
            c+=" fn201.office =? and ";
            v.add(office);
          }
//          if(subjectCode!=null && !"".equals(subjectCode)){
//            c+=" fn201.subject_code like ? and ";
//            v.add("%"+subjectCode+"%");
//          }
          if (c.length() != 0)
            c = " and "
                + c.substring(0, c.lastIndexOf("and"));
          sql=sql+c;
          Query query=session.createSQLQuery(sql);
          for(int i=0;i<v.size();i++){
            query.setParameter(i, v.get(i));
          }
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * bit add
   * 当年住房公积金个人贷款发放额
   */
  public String personloangivemoney(final String date,final String officeid){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="SELECT NVL(SUM( pl202.OCCUR_MONEY),0) "
                     +"FROM PL202 pl202,pl203 pl203,pl110 pl110 "
                     +"WHERE (pl202.BIZ_TYPE='1' "
                     +"OR (pl202.BIZ_TYPE='12' AND pl202.WRONG_BIZ_TYPE='1')) "
                     +"and pl202.flow_head_id=pl203.flow_head_id "
                     +"and pl203.contract_id=pl110.contract_id "
                     +"AND substr(pl202.BIZ_DATE,1,4)=? "
                     +"and pl110.office=? "
                     +"AND pl202.BIZ_ST=6";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * yqf add
   * 当年住房公积金个人贷款发放额
   */
  public String personloangivemoney_(final String date,final String officeid){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
//          String sql=" select sum(t.debit) from fn201 t "+
//                     " where substr(t.credence_date, 1, 4) = ?"+
//                     " and t.subject_code like ? "+
//                     " and t.office = ? ";
          String sql = " SELECT NVL(SUM(pl202.OCCUR_MONEY), 0)"+
            " FROM PL202 pl202,"+
            " pl203 pl203,"+
            " pl110 pl110"+
            " WHERE (pl202.BIZ_TYPE = '1' OR"+
            " (pl202.BIZ_TYPE = '12' AND"+
            " pl202.WRONG_BIZ_TYPE = '1'))"+
            " and pl202.flow_head_id ="+
            " pl203.flow_head_id"+
            " and pl203.contract_id ="+
            " pl110.contract_id"+
            " AND pl202.BIZ_ST = 6"+
            " AND substr(pl202.BIZ_DATE, 1, 4) =?"+
            " and pl110.office = ?";
            
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * bit add
   * 年末住房公积金个人贷款逾期额
   */
  public String personloanoverduepay(final String date,final String officeid){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="SELECT SUM(M1) "
                      +"FROM " 
                      +"(SELECT SUM(OVERPLUS_LOAN_MONEY) AS M1 "
                      +"FROM PL111 pl111,pl110 pl110 "
                      +"WHERE pl111.contract_id=pl110.contract_id " 
                      +"and pl110.contract_id IN ( "
                      +"SELECT a.CONTRACT_ID "
                      +"FROM PL205 a "
                      +"WHERE  a.owe_month>1 "
                      +"and a.owe_date in (select max(b.owe_date) from pl205 b  where  substr(b.owe_date,0,4)=? group by b.loan_bank_id )) "
                      +"and pl110.office=? ) ";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * yqf add
   * 年末住房公积金个人贷款逾期额
   */
  public String personloanoverduepay_(final String date,final String officeid){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
//          String sql="SELECT SUM(M1) "
//                      +"FROM " 
//                      +"(SELECT SUM(OVERPLUS_LOAN_MONEY) AS M1 "
//                      +"FROM PL111 pl111,pl110 pl110 "
//                      +"WHERE pl111.contract_id=pl110.contract_id " 
//                      +"and pl110.contract_id IN ( "
//                      +"SELECT a.CONTRACT_ID "
//                      +"FROM PL205 a "
//                      +"WHERE  a.owe_month>1 "
//                      +"and a.owe_date in (select max(b.owe_date) from pl205 b  where  substr(b.owe_date,0,4)=? group by b.loan_bank_id )) "
//                      +"and pl110.office=? ) ";
//          String sql = " select sum(t.owe_corpus + t.owe_interest + t.punish_interest)"+
//                       " from pl205 t, bb105 s "+
//                       " where t.loan_bank_id = s.coll_bank_id "+
//                       " and s.office = ?"+
//                       " and substr(t.owe_date, 1, 4) <= ?";
          String sql = " select sum(nvl(t.owe_corpus,0) + "+
            " nvl(t.owe_interest,0) + "+
            " nvl(t.punish_interest,0)) "+
            " from pl205 t, bb105 s "+
            " where t.loan_bank_id ="+
            " s.coll_bank_id "+
            " and substr(t.owe_date, 1, 6) <= (select max(substr(t.owe_date, 1, 6)) "+
            " from pl205 t, bb105 s "+
            " where t.loan_bank_id =s.coll_bank_id "+
            " and s.office = ? "+
            " and substr(t.owe_date, 1, 4)=?)";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, officeid);
          query.setParameter(1, date);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * bit add
   * 当年住房公积金增值收益
   */
  public String incrementincome(final String date,final String officeid){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="SELECT SUM(CASE "
                     +"WHEN F110.BALANCE_DIRECTION = 1 THEN "
                     +"F201.CREDIT - F201.DEBIT "
                     +"WHEN F110.BALANCE_DIRECTION = 0 THEN "
                     +"F201.DEBIT - F201.CREDIT "
                     +"ELSE "
                     +"0 "
                     +"END) "
                     +"FROM FN110 F110, FN201 F201 "
                     +"WHERE F201.SUBJECT_CODE = F110.SUBJECT_CODE "
                     +"AND F201.BOOK_ID = F110.BOOK_ID "
                     +"AND F201.CREDENCE_ST = 2 "
                     +"AND SUBSTR(F201.CREDENCE_DATE, 1, 4) = ? "
                     +"AND SUBSTR(F201.SUBJECT_CODE, 1, 3) = '311' "
                     +"and F201.office=?";  
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * yqf add
   * 当年住房公积金增值收益
   */
  public String incrementincome_(final String date,final String office){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
//          String sql="SELECT SUM(CASE "
//                     +"WHEN F110.BALANCE_DIRECTION = 1 THEN "
//                     +"F201.CREDIT - F201.DEBIT "
//                     +"WHEN F110.BALANCE_DIRECTION = 0 THEN "
//                     +"F201.DEBIT - F201.CREDIT "
//                     +"ELSE "
//                     +"0 "
//                     +"END) "
//                     +"FROM FN110 F110, FN201 F201 "
//                     +"WHERE F201.SUBJECT_CODE = F110.SUBJECT_CODE "
//                     +"AND F201.BOOK_ID = F110.BOOK_ID "
//                     +"AND F201.CREDENCE_ST = 2 "
//                     +"AND SUBSTR(F201.CREDENCE_DATE, 1, 4) = ? "
//                     +"AND SUBSTR(F201.SUBJECT_CODE, 1, 3) = '311' "
//                     +"and F201.office=?";  
//          String sql=" select sum(t.credit-t.debit)"+
//                     " from fn201 t "+
//                     " where substr(t.credence_date, 1, 4) = ?"+
//                     " and t.subject_code like ? ";
          String sql = " SELECT distinct ((SELECT SUM(fn201.CREDIT)"+
            " FROM fn201 fn201"+
            " WHERE fn201.book_id = '1'"+
            " and fn201.credence_st = '2'"+
            " and fn201.subject_code like '401%')"+
            " -"+
            " (SELECT SUM(fn201.debit)"+
            " FROM fn201 fn201"+
            " WHERE fn201.book_id = '1'"+
            " and fn201.credence_st = '2'"+
            " and fn201.subject_code like '411%'))"+
            " from fn201 fn201"+
            " where SUBSTR(fn201.credence_date,1,4) <= ? and fn201.office=?";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, office);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * bit add
   * 当年住房公积金月平均缴存余额
   */
  public String monthpaybalance(final String date,final String officeid){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="SELECT SUM(aa101.CREDIT-aa101.DEBIT) /12 "
                    +"FROM AA101 aa101 "
                    +"WHERE SUBSTR(SETT_DATE,1,4) =? "
                    +"AND aa101.BIZ_TYPE IN('A','B','D','E','F','G','H','I','K','L','M') "
                    +"AND aa101.BIZ_STATUS='5' "
                    +"and aa101.officecode=?";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * bit add
   * 年末住房公积金缴存余额
   */
  public String yearlastpaybalance(final String date,final String officeid,final String subjectCode){
    Object o="0";
    try{
      o=getHibernateTemplate().execute(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
//          String sql="SELECT SUM(aa101.CREDIT-aa101.DEBIT) "
//            +"FROM AA101 aa101 "
//            +"WHERE SUBSTR(SETT_DATE,1,4) <=? "
//            +"AND aa101.BIZ_TYPE IN('A','B','D','E','F','G','H','I','K','L','M') "
//            +"AND aa101.BIZ_STATUS='5' "
//            +"and aa101.officecode=?";
//          String sql="SELECT SUM(aa101.CREDIT-aa101.DEBIT) "
//            +"FROM AA101 aa101 "
//            +"WHERE SUBSTR(SETT_DATE,1,4) <=? "
//            +"AND aa101.BIZ_STATUS='5' "
//            +"and aa101.officecode=?";
          String sql = " SELECT SUM(fn201.CREDIT - fn201.DEBIT)"+
          " FROM fn201 fn201"+
          " WHERE fn201.book_id = '1'"+
          " and fn201.credence_st = '2'"+
          " and SUBSTR(credence_date, 1, 4) <= ?"+
          " and fn201.office = ?"+
          " and fn201.subject_code like ?";
           
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, date);
          query.setParameter(1, officeid);
          query.setParameter(2, subjectCode);
          return query.uniqueResult()!=null?query.uniqueResult():"0";
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return o.toString();
  }
  /**
   * 办事处名称
   * @param office
   * @param bizDate
   * @param bankId
   * @return
   */
  public String queryOfficeName(final String officeId){
    String name = "";
    try {
      name = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t.name from bb101 t where t.id = '"+officeId+"'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }
}
