package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
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
import org.xpup.hafmis.sysloan.common.domain.entity.CollLoanbackPara;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.DeveloperDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;


/**
 * 银行贷款参数PL009
 * 
 *@author 郭婧平
 *2007-12-17
 */
public class CollLoanbackParaDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public CollLoanbackPara queryById(Serializable id) {  
    Validate.notNull(id);
    return  (CollLoanbackPara) getHibernateTemplate().get(CollLoanbackPara.class,new Integer(id.toString()));    
  }
  /**
   * 插入记录
   * 
   * @param collLoanbackPara
   * @return
   */
  public Serializable insert(CollLoanbackPara collLoanbackPara) {
    Serializable id = null;
    try {
      Validate.notNull(collLoanbackPara);
      id = getHibernateTemplate().save(collLoanbackPara);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 公积金还贷参数设置
   * @author 郭婧平
   * 2007-12-17
   * 根据office查pl009表内容
   * 查询条件：office
   */
  public List queryCollLoanbackParaByOffice(final String office) throws Exception {
    List list=null;
    try {
      list =(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select "+"distinct "+
                                  "pl009.param_value,"+
                                  "pl009.param_num,"+
                                  "pl009.param_explain,"+
                                  "pl009.param_explain_explain "+
                                  "from PL009 pl009 " +
                                  "where pl009.office=? ";
            Query query = session.createSQLQuery(hql);
            query.setString(0, office);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              CollLoanbackPara collLoanbackPara=new CollLoanbackPara();
              obj = (Object[]) it.next();
              if(obj[0]!=null){
                collLoanbackPara.setParamValue(obj[0].toString());
              }else{
                collLoanbackPara.setParamValue("");
              }
              collLoanbackPara.setParamNum(obj[1].toString());
              if(obj[2]!=null){
                collLoanbackPara.setParamExplain(obj[2].toString());
              }else{
                collLoanbackPara.setParamExplain("");
              }
              if(obj[3]!=null){
                collLoanbackPara.setParamExplainExplain(obj[3].toString());
              }else{
                collLoanbackPara.setParamExplainExplain("");
              }
              temp_list.add(collLoanbackPara);
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
   * 公积金还贷参数设置
   * @author 郭婧平
   * 2007-12-17
   * 根据office删除pl009表内容
   */
  public void deleteCollLoanbackPara(final String office){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete CollLoanbackPara collLoanbackPara where collLoanbackPara.office=? ";          
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
   * 公积金还贷
   * @author 郭婧平
   * 2007-12-19
   * 根据office查pl009表的参数值
   * 查询条件：office
   */
  public Object[] queryParamValueByOffice(final String paramNum,final String office) throws Exception {
    Object[] obj=new Object[2];
    try {
      obj =(Object[])getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl009.param_value,pl009.param_explain from PL009 pl009 ";
            Vector parameters = new Vector();
            String criterion = "";
            if (paramNum != null && !paramNum.equals("")) {
              criterion += "pl009.param_num=?  and ";
              parameters.add(paramNum);
            }
            
            if (office != null && !office.equals("")) {
              criterion += "pl009.office=? and ";
              parameters.add(office);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();                                      
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 公积金还贷
   * @author  王硕
   * 2007-12-19
   * 根据office查pl009表的参数值
   * 查询条件：office
   */
  public String queryParamValueByOffice_wsh(final String paramNum,final String office) throws Exception {
    String value="";
    try {
      value =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl009.param_value from PL009 pl009 ";
            Vector parameters = new Vector();
            String criterion = "";
            if (paramNum != null && !paramNum.equals("")) {
              criterion += "pl009.param_num=?  and ";
              parameters.add(paramNum);
            }
            
            if (office != null && !office.equals("")) {
              criterion += "pl009.office=? and ";
              parameters.add(office);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            
            if(query.uniqueResult()==null){
              return "1";
            }
            return query.uniqueResult().toString();                                      
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }
  /**
   * 公积金还贷
   * @author 王硕
   * 2007-12-19
   * 根据office查pl009表的参数值
   * 查询条件：office
   */
  public String queryParamValueByOffice_wsh1(final String paramNum,final String office) throws Exception {
    String value="";
    try {
      value =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl009.loan_param_id from PL009 pl009 ";
            Vector parameters = new Vector();
            String criterion = "";
            if (paramNum != null && !paramNum.equals("")) {
              criterion += "pl009.param_num=?  and ";
              parameters.add(paramNum);
            }
            
            if (office != null && !office.equals("")) {
              criterion += "pl009.office=? and ";
              parameters.add(office);
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
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }
  public String findXieYINum(final String office,final String year) throws Exception {
    String value="";
    try {
      value =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a.xieyi_num from f_p_num a ";
            Vector parameters = new Vector();
            String criterion = "";
            if (year != null && !year.equals("")) {
              criterion += "a.year=?  and ";
              parameters.add(year);
            }
           
            if (office != null && !office.equals("")) {
              criterion += "a.office=? and ";
              parameters.add(office);
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
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }
  
  public String get_loanKouAcc(final String contractId) throws Exception {
    String value="";
    try {
      value =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.loan_kou_acc from pl111 t  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (contractId != null && !contractId.equals("")) {
              criterion += "t.contract_id=? and t.contract_st='11' and ";
              parameters.add(contractId);
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
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }
  /**
   * 张列 更新内容
   * 
   * @param specialapplyNewDTO
   */
  public void update(final String xieYiNum ,final String office,final String year) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update XieYiNum p2 set p2.xieyiNum=?" +
                      "where p2.office=? and p2.year=? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(xieYiNum));
          query.setParameter(1, office);
          query.setParameter(2, year);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public String findContractExsit(final String contractId) throws Exception {
    String value="";
    try {
      value =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.id) from pl400 a ";
            Vector parameters = new Vector();
            String criterion = "";
            if (contractId != null && !contractId.equals("")) {
              criterion += "a.contract_id=?  and ";
              parameters.add(contractId);
            }
           
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion+" and a.reservea_b='1' ";
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult().toString();                                      
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }
  public String queryParamValueCount_wsh(final String office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
//          String hql = "select count(distinct loanBankPara.paramValue) " +
//              " from LoanBankPara loanBankPara " ;
          String hql= "select count(distinct loanBankPara.PARAM_VALUE) " +
          " from pl009 loanBankPara " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "loanBankPara.OFFICE != '100'  and ";
           
          }
          
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanBankPara.PARAM_NUM = ?  and ";
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
  /*
   * 更新参数表ploo9
   */
  public void updatePl009_wsh(final String paraValue, final String office,final String paramNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update CollLoanbackPara collLoanbackPara set collLoanbackPara.paramValue=? where collLoanbackPara.office=? and collLoanbackPara.paramNum=?  ";
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
  /**
   * 根据银行ID查询参数值
   * 回收办理
   * jj
   * @param loanBankId
   * @return
   */
  public String queryParamValue_LJ(final Integer office, final String paramNum){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select collLoanbackPara.paramValue " +
              " from CollLoanbackPara collLoanbackPara " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (office != null && !office.equals("")) {
            criterion += "collLoanbackPara.office = ?  and ";
            parameters.add(office.toString());
          }
         
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "collLoanbackPara.paramNum = ?  and ";
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
}
