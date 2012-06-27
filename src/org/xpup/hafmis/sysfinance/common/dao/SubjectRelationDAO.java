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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationInfoTaDTO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTaPop1DTO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTbDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.SubjectRelation;


public class SubjectRelationDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public SubjectRelation queryById(Serializable id) {
    Validate.notNull(id);
    return (SubjectRelation) getHibernateTemplate().get(
        SubjectRelation.class, id);
  }

  /**
   * 插入记录
   * 
   * @param SubjectRelation
   * @return
   */
  public Serializable insert(SubjectRelation subjectRelation) {
    Serializable id = null;
    try {
      Validate.notNull(subjectRelation);
      id = getHibernateTemplate().save(subjectRelation);
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
            String hql = "select subjectRelation.subjectCode from SubjectRelation subjectRelation ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "subjectRelation.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());
            
            if (code != null && !code.equals("")) {
              criterion += "subjectRelation.subjectCode = ?  and ";
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
   * author wsh 科目关系设置
   * 查询科目代码是否存在
   * @param subjectCode 科目代码
   * @return
   */
  public Integer is_CodeIn_wsh(final String subjectCode,final SecurityInfo securityInfo) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.subject_code) from fn111 a ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "a.book_id = ?  and ";
            parameters.add(securityInfo.getBookId());
            
            if (subjectCode != null && !subjectCode.equals("")) {
              criterion += "a.subject_code = ?  and ";
              parameters.add(subjectCode);
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
   * author wsh 科目关系设置
   * 查询科目代码在fn110,fn111,fn201的相关信息
   * @param subjectCode 科目代码
   * @return
   */
  public SubjectrelationInfoTaDTO findSubejectRelationTaInfo(final String subjectCode,final String bookId) {
    SubjectrelationInfoTaDTO subjectrelationInfoTaDTO = (SubjectrelationInfoTaDTO) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql ="select distinct b.subject_code subjectcode,"
                    +"a.subject_name subjecname,"
                    +"a.balance_direction balancedirection,"
                    +"  ( select (( (select nvl(sum(fn201.credit), 0) from fn201 where fn201.subject_code = ? and  fn201.book_id=? and fn201.credence_st = 2) )- (select nvl(sum(fn201.debit), 0) from fn201  where fn201.subject_code = ? and fn201.book_id=? and fn201.credence_st = 2)) from dual), "
                    +"b.first_subject_code firstsubjectcode,"
                    +"b.calcul_rela_type calculrelatype "
                    +"from fn110 a, fn111 b "
                    +"where a.subject_code = b.subject_code "                                   
                    +"and b.subject_code = ? and a.book_id=? and a.book_id=b.book_id ";
        Object[] obj = null;
        Vector parameters = new Vector();
        String criterion = "";           
        if (subjectCode != null) {
          parameters.add(subjectCode);
          parameters.add(bookId);
          parameters.add(subjectCode);
          parameters.add(bookId);
          parameters.add(subjectCode);
          parameters.add(bookId);
        }        
        hql=hql+criterion;
        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }      
        obj = (Object[]) query.uniqueResult();
        SubjectrelationInfoTaDTO tempsubjectrelationInfoTaDTO = null;
        if (obj != null) {
          tempsubjectrelationInfoTaDTO = new SubjectrelationInfoTaDTO();
          if (obj[0] != null) {
            tempsubjectrelationInfoTaDTO.setSubjectCode((obj[0].toString()));
          }
          if (obj[1] != null) {
            tempsubjectrelationInfoTaDTO.setSubjectName(obj[1].toString());
          }
          if (obj[2] != null) {
            tempsubjectrelationInfoTaDTO.setBalanceDirection((obj[2].toString()));
          }
          if (obj[3] != null) {
            tempsubjectrelationInfoTaDTO.setBalance(new BigDecimal(obj[3].toString()));
          }
          if (obj[4] != null) {
            tempsubjectrelationInfoTaDTO.setFirstSubjectCode((obj[4].toString()));
          }          
          if (obj[5] != null) {
            tempsubjectrelationInfoTaDTO.setCalculRelaType(obj[5].toString());
          }       
        }
        return tempsubjectrelationInfoTaDTO;
      }
        });
    return subjectrelationInfoTaDTO;
  }
  /**
   * author wsh 科目关系设置
   * 查询科目代码在fn111的信息
   * @param subjectCode 科目代码
   * @return List
   */
  public List querySubejectRelationTaList_wsh(final String subjectCode,final int start,final int pageSize ,final String bookId){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql="select subjectRelation.calculRelaValue " +
            "from SubjectRelation subjectRelation" +
            " where subjectRelation.subjectCode=? and subjectRelation.bookId=? ";                 
       List queryList=new ArrayList();
       List temp_list=new ArrayList();
        Vector parameters = new Vector();
        if (subjectCode != null) {
          parameters.add(subjectCode);
        }   
        if (bookId != null) {
          parameters.add(bookId);
        }  
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        queryList = query.list();      
        Iterator it=queryList.iterator();
        Object obj = null;
        while (it.hasNext()) {
          obj = (Object) it.next();
          if (obj != null) {
            String calculRelaValue = "";
            if (obj != null) {
              calculRelaValue=obj.toString();
            }            
            temp_list.add(calculRelaValue);
          }
        } 
        return temp_list;
      }
        });
    return list;
  }
  /**
   * author wsh 科目关系设置
   * 查询科目代码在fn111的全部记录信息
   * @param subjectCode 科目代码
   * @return List
   */
  public List querySubejectRelationTaCountList_wsh(final String subjectCode,final String bookId){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql ="select a.calcul_rela_value calculrelavalue from fn111 a where a.subject_code=? and a.book_id=? ";                   
       List queryList=new ArrayList();
        Vector parameters = new Vector();
        String criterion = "";           
        if (subjectCode != null) {
          parameters.add(subjectCode);
        } 
        if (bookId != null) {
          parameters.add(bookId);
        }  
        hql=hql+criterion;      
        hql = hql + criterion;
        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }        
        queryList = query.list();        
        return queryList;
      }
        });
    return list;
  }
  /**
   * author wsh 科目关系设置
   * 查询核算关系值已经建立核算关系
   * @param firstsubjectCode 一级科目
   * @param calculRelaType 核算关系类型
   * @param calculRelaValue 核算关系值
   * @2007-10-16
   * @return Integer
   */
  public Integer findSubjectrelationInfoExist(final String firstsubjectCode, final String calculRelaType,final String calculRelaValue,final String bookId){
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.subject_rele_id) from fn111 a where a.calcul_rela_type=? and a.first_subject_code=? and a.calcul_rela_value=? and a.book_id=?  ";
            Vector parameters = new Vector();
            if (calculRelaType != null) {
              parameters.add(calculRelaType);
            }
            if (firstsubjectCode != null) {
              parameters.add(firstsubjectCode);
            }
            if (calculRelaValue != null) {
              parameters.add(calculRelaValue);
            }
            if (bookId != null) {
              parameters.add(bookId);
            }
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
   * author wsh 科目关系设置
   * 查询科目代码在fn110,fn111,fn201的相关信息
   * @param subjectCode 科目代码
   * @return
   */
  public SubjectrelationInfoTaDTO findSubejectRelationTa1Info(final String subjectCode,final String bookId) {
    SubjectrelationInfoTaDTO subjectrelationInfoTaDTO = (SubjectrelationInfoTaDTO) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql ="select distinct a.subject_code code,a.subject_name subjecname," +
            "a.balance_direction balancedirection," 
            +"  ( select (( (select nvl(sum(fn201.credit), 0) from fn201 where fn201.subject_code = ?  and fn201.book_id=? and fn201.credence_st = 2) )- (select nvl(sum(fn201.debit), 0) from fn201  where fn201.subject_code = ? and fn201.book_id=?  and fn201.credence_st = 2)) from dual )"
           + "from fn110 a " +
            "where a.subject_code = ? and a.book_id=? ";
        Object[] obj = null;
        Vector parameters = new Vector();
        String criterion = "";           
        if (subjectCode != null) {
          parameters.add(subjectCode);
          parameters.add(bookId);
          parameters.add(subjectCode);
          parameters.add(bookId);
          parameters.add(subjectCode);
          parameters.add(bookId);
        }        
        hql=hql+criterion;
        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        obj = (Object[]) query.uniqueResult();
        SubjectrelationInfoTaDTO tempsubjectrelationInfoTaDTO = null;
        if (obj != null) {
          tempsubjectrelationInfoTaDTO = new SubjectrelationInfoTaDTO();
          if (obj[0] != null) {
            tempsubjectrelationInfoTaDTO.setSubjectCode((obj[0].toString()));
          }
          if (obj[1] != null) {
            tempsubjectrelationInfoTaDTO.setSubjectName(obj[1].toString());
          }
          if (obj[2] != null) {
            tempsubjectrelationInfoTaDTO.setBalanceDirection((obj[2].toString()));
          }
          if (obj[3] != null) {
            tempsubjectrelationInfoTaDTO.setBalance(new BigDecimal(obj[3].toString()));
          }              
        }
        return tempsubjectrelationInfoTaDTO;
      }
        });
    return subjectrelationInfoTaDTO;
  }
  /**
   * author wsh 科目关系设置_维护_显示列表
   * @param subjectCode 科目代码
   * @param subjectName 科目名称
   * @param calculRelaType 核算关系类别
   * @param calculRelaValue 核算关系值
   * @param orderBy 
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @2007-10-18
   * @return
   */
 public List  querySubejectRelationTbList_wsh(final String subjectCode,final String subjectName,final String calculRelaType,final String calculRelaValue,final String orderBy,final String order,final int start,final int pageSize,final int page,final String bookId,final List officeList,final List bankList,final List orgIdList){
   List list=null;
   try {
     Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
         || "DESC".equalsIgnoreCase(order));
     Validate.isTrue(start >= 0);
     list =getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
             throws HibernateException, SQLException {
           String hql ="select  " +
              "b.subject_code subjectcode," +
           "a.subject_name subjectname," +
           "b.first_subject_code firstsubjectcode," +
           "b.calcul_rela_type calculrelatype," +
           "b.calcul_rela_value calculralavalue ," +
           "b.subject_rele_id subjectreleid "+
           " from fn111 b,fn110 a " +
           "where a.subject_code=b.subject_code and a.book_id=b.book_id "; 
           Vector parameters = new Vector();
           String criterion = "";          
           if (subjectCode != null&&!subjectCode.equals("")) {
             criterion += " b.subject_code=? and ";
             parameters.add(subjectCode);
           }
           if (subjectName != null&&!subjectName.equals("")) {
             criterion += " a.subject_name=? and ";
             parameters.add(subjectName);
           }
           if (calculRelaType != null&&!calculRelaType.equals("")) {
             if("0".equals(calculRelaType)){
               criterion += " b.calcul_rela_type in(0,3) and ";
             }
             if("3".equals(calculRelaType)){
               criterion += " b.calcul_rela_type in(0,3) and ";
             }else{
               criterion += " b.calcul_rela_type=? and ";
               parameters.add(calculRelaType);
             }            
           }
           
           if (calculRelaValue != null&&!calculRelaValue.equals("")) {
             criterion += " b.calcul_rela_value=? and ";
             parameters.add(calculRelaValue);
           }else if("0".equals(calculRelaType)){
             if(officeList != null && officeList.size() > 0){
               criterion += "( ";
               for (int i = 0; i < officeList.size(); i++) {
                 criterion += " b.calcul_rela_value = ? or ";
                 parameters.add(officeList.get(i).toString());
               }
               criterion = criterion.substring(0, criterion.lastIndexOf("or"));
               criterion += ") and";
             }             
           }else if("1".equals(calculRelaType)){
             if(bankList != null && bankList.size() > 0){
               criterion += "( ";
               for (int i = 0; i < bankList.size(); i++) {
                 criterion += " b.calcul_rela_value = ? or ";
                 parameters.add(bankList.get(i).toString());
               }
               criterion = criterion.substring(0, criterion.lastIndexOf("or"));
               criterion += ") and";
             }             
           }else if("2".equals(calculRelaType)){
             if(orgIdList != null && orgIdList.size() > 0){
               criterion += "( ";
               for (int i = 0; i < orgIdList.size(); i++) {
                 criterion += " b.calcul_rela_value = ? or ";
                 parameters.add(orgIdList.get(i).toString());
               }
               criterion = criterion.substring(0, criterion.lastIndexOf("or"));
               criterion += ") and";
             }             
           }                      
           if (bookId != null&&!bookId.equals("")) {
             criterion += " a.book_id=? and ";
             parameters.add(bookId);
           }   
           if (criterion.length() != 0)   
             criterion=" and "+
             criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " b.subject_rele_id ";
          String od = order;
          if (od == null)
            od = "DESC";          
          hql = hql + criterion + "order by " + ob + " " + od ;
          Query query = session.createSQLQuery(hql);          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);  
          List temp_list1=new ArrayList();
          temp_list1=query.list(); 
          if (temp_list1 == null || temp_list1.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }
          Iterator it=query.list().iterator();
          List temp_list=new ArrayList();
          Object obj[]=null;
          while(it.hasNext()){
            obj=(Object[])it.next();
            if(obj!=null){
              SubjectrelationTbDTO subjectrelationTbDTO=new SubjectrelationTbDTO();             
              if(obj[0]!=null)
                subjectrelationTbDTO.setSubjectCode(obj[0].toString());
              else
                subjectrelationTbDTO.setSubjectCode("");
              if(obj[1]!=null)
                subjectrelationTbDTO.setSubjectName(obj[1].toString());
              else
                subjectrelationTbDTO.setSubjectName("");
              if(obj[2]!=null)
                subjectrelationTbDTO.setFirstSubjectCode(obj[2].toString());
              else
                subjectrelationTbDTO.setFirstSubjectCode("");
              if(obj[3]!=null)
                subjectrelationTbDTO.setCalculRelaType(obj[3].toString());
              else
                subjectrelationTbDTO.setCalculRelaType("");
              if(obj[4]!=null)
                subjectrelationTbDTO.setCalculRelaValue(obj[4].toString());
              else
                subjectrelationTbDTO.setCalculRelaValue("");
              if(obj[5]!=null)
                subjectrelationTbDTO.setSubjectreleid(obj[5].toString());
              else
                subjectrelationTbDTO.setSubjectreleid("");
              temp_list.add(subjectrelationTbDTO);              
            }            
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
  * author wsh 科目关系设置_维护_记录数量
  * @param subjectCode 科目代码
  * @param subjectName 科目名称
  * @param calculRelaType 核算关系类别
  * @param calculRelaValue 核算关系值
  * @param orderBy 
  * @param order
  * @param start
  * @param pageSize
  * @param page
  * @2007-10-18
  * @return
  */
public List  querySubejectRelationTbCountList_wsh(final String subjectCode,final String subjectName,final String calculRelaType,final String calculRelaValue,final String bookId){
  List list=null;
  try {   
    list =getHibernateTemplate().executeFind(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
          String hql ="select " +
          "b.subject_rele_id subjectreleid "+
              " from fn111 b,fn110 a " +
              "where a.subject_code=b.subject_code and a.book_id=b.book_id ";                        
          Vector parameters = new Vector();
          String criterion = "";          
          if (subjectCode != null&&!subjectCode.equals("")) {
            criterion += " b.subject_code=? and ";
            parameters.add(subjectCode);
          }
          if (subjectName != null&&!subjectName.equals("")) {
            criterion += " a.subject_name=? and ";
            parameters.add(subjectName);
          }
          if (calculRelaType != null&&!calculRelaType.equals("")) {
            if("0".equals(calculRelaType)){
              criterion += " b.calcul_rela_type in(0,3) and ";
            }
            if("3".equals(calculRelaType)){
              criterion += " b.calcul_rela_type in(0,3) and ";
            }else{
              criterion += " b.calcul_rela_type=? and ";
              parameters.add(calculRelaType);
            }            
          }
          
          if (calculRelaValue != null&&!calculRelaValue.equals("")) {
            criterion += " b.calcul_rela_value=? and ";
            parameters.add(calculRelaValue);
          }   
          if (bookId != null&&!bookId.equals("")) {
            criterion += " a.book_id=? and ";
            parameters.add(bookId);
          }   
          if (criterion.length() != 0)   
            criterion=" and "+criterion.substring(0, criterion.lastIndexOf("and"));
              
         hql = hql + criterion;
         Query query = session.createSQLQuery(hql);          
         for (int i = 0; i < parameters.size(); i++) {
           query.setParameter(i, parameters.get(i));
         }            
         return query.list();
        }
      }
  );
  } catch (Exception e) {
    e.printStackTrace();
  }
  return list;
}
/**
 * author wsh 科目关系设置
 * 查询fn111中是否存在主键为subjectreleid的记录
 * @param subjectreleid fn111主键
 * @return
 */
public Integer findSubjectrelationExist_wsh(final String subjectreleid,final String bookId) {
  Integer count = (Integer) getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
          String hql = "select count(a.subject_rele_id) from fn111 a where a.subject_rele_id= ? and a.book_id=? ";
          Vector parameters = new Vector();
          if (subjectreleid != null) {
            parameters.add(subjectreleid);
          }
          if (bookId != null) {
            parameters.add(bookId);
          }
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
 * author wsh 科目关系设置
 * 查询fn111中是否存在主键为subjectreleid的记录
 * @param subjectreleid fn111主键
 * @return
 */
public void deleteSubjectrelationTb_wsh(final String subjectreleid) {
  try {
    getHibernateTemplate().execute(new HibernateCallback() {

      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete from SubjectRelation subjectRelation  where subjectRelation.subjectReleId= ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(subjectreleid));
        return new Integer(query.executeUpdate());
      }
    });
  } catch (Exception e) {
    e.printStackTrace();
  }
}
/**
 * 得到的登录人管理的办事处
 * @return userOfficeList
 */
  public List getUserOfficeList (final String username) {
    List userOfficeList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              List list=new ArrayList();
              String hql = 
              " select distinct b.id,b.name from bb108 a, bb101 b where b.ou_type=2 and a.office = b.id and a.username ='"+username+"'"+
              " union "+
              " select b.id, b.name from bb101 b, bb110 c, ca101 d, ca103 e where b.ou_type=2 and b.id=c.office "+
              " and c.roleid=e.role_id and e.user_id=d.id and d.username='"+username+"'"+
              " union "+
              " select b.id, b.name from bb108 a, bb101 b, bb112 f where b.ou_type=2 and a.office = b.id "+
              " and a.username = f.subusername and f.username ='"+username+"'"; 
                
              Query query=session.createSQLQuery(hql);   
              
              Iterator it=query.list().iterator();    
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();    
                if(obj[0]!=null){
                  OfficeDto officeDto=new OfficeDto();
                  officeDto.setOfficeCode(obj[0].toString());
                  officeDto.setOfficeName(obj[1].toString());
                  list.add(officeDto);
                }
              }
              return list;
          }
        });
    return userOfficeList;
  }
  /**
   * 得到用户的办事处(分页)
   * wsh
   * @return userOfficeList
   */
    public List getUserOfficeLists_wsh (final int start,final int pageSize,final String username) {
      List userOfficeList = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws SQLException,
                HibernateException {
                List list=new ArrayList();
//                String hql = 
//                " select b.id,b.name from bb108 a, bb101 b where a.office = b.id and b.ou_type='2' and a.username ='"+username+"'";
                String hql = 
                  " select distinct b.id,b.name from bb108 a, bb101 b where b.ou_type=2 and a.office = b.id and a.username ='"+username+"'"+
                  " union "+
                  " select b.id, b.name from bb101 b, bb110 c, ca101 d, ca103 e where b.ou_type=2 and b.id=c.office "+
                  " and c.roleid=e.role_id and e.user_id=d.id and d.username='"+username+"'"+
                  " union "+
                  " select b.id, b.name from bb108 a, bb101 b, bb112 f where b.ou_type=2 and a.office = b.id "+
                  " and a.username = f.subusername and f.username ='"+username+"'";   
                Query query=session.createSQLQuery(hql);   
                query.setFirstResult(start);
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                Iterator it=query.list().iterator();    
                Object obj[]=null;
                while(it.hasNext()){
                  obj=(Object[])it.next();    
                  if(obj[0]!=null){
                    SubjectrelationTaPop1DTO officeDto=new SubjectrelationTaPop1DTO();
                    officeDto.setId(obj[0].toString());
                    officeDto.setName(obj[1].toString());
                    list.add(officeDto);
                  }
                }
                return list;
            }
          });
      return userOfficeList;
    }
    /**
     * author wsh 科目关系设置
     * 查询核算关系值办事处是否已经建立核算关系在类型为办事处0和其他3记录中
     * @param firstsubjectCode 一级科目
     * @param calculRelaType 核算关系类型
     * @param calculRelaValue 核算关系值
     * @2007-10-16
     * @return Integer
     */
    public Integer findSubjectrelationInfoExist_wsh(final String firstsubjectCode, final String calculRelaType,final String calculRelaValue,final String bookId){
      Integer count = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(a.subject_rele_id) from fn111 a where a.calcul_rela_type in(0,3) and a.first_subject_code=? and a.calcul_rela_value=? and a.book_id=?  ";
              Vector parameters = new Vector();             
              if (firstsubjectCode != null) {
                parameters.add(firstsubjectCode);
              }
              if (calculRelaValue != null) {
                parameters.add(calculRelaValue);
              }
              if (bookId != null) {
                parameters.add(bookId);
              }
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
     * author wsh 科目关系设置
     * 银行的办事处是否已经建立核算关系在类型为办事处0和其他3记录中
     * @param firstsubjectCode 一级科目
     * @param calculRelaType 核算关系类型
     * @param calculRelaValue 核算关系值
     * @2007-10-16
     * @return Integer
     */
    public Integer findSubjectrelationInfoExist_bank(final String firstsubjectCode, final String calculRelaType,final String calculRelaValue,final String bookId){
      Integer count = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(a.subject_rele_id) from fn111 a where a.calcul_rela_type in(0,3) and a.first_subject_code=? and a.calcul_rela_value=? and a.book_id=?  ";
              Vector parameters = new Vector();             
              if (firstsubjectCode != null) {
                parameters.add(firstsubjectCode);
              }
              if (calculRelaValue != null) {
                parameters.add(calculRelaValue);
              }
              if (bookId != null) {
                parameters.add(bookId);
              }
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
     * author wsh 科目关系设置
     * 查询核算关系值已经建立核算关系
     * @param firstsubjectCode 一级科目
     * @param calculRelaType 核算关系类型
     * @param calculRelaValue 核算关系值
     * @2007-10-16
     * @return Integer
     */
    public Integer findSubjectrelationInfoExist_org_banshichu(final String firstsubjectCode, final String calculRelaType,final String calculRelaValue,final String bookId){
      Integer count = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(a.subject_rele_id) from fn111 a where a.calcul_rela_type in(0,3) and a.first_subject_code=? and a.calcul_rela_value=? and a.book_id=?  ";
              Vector parameters = new Vector();
              if (calculRelaType != null) {
                parameters.add(calculRelaType);
              }
              if (firstsubjectCode != null) {
                parameters.add(firstsubjectCode);
              }
              if (calculRelaValue != null) {
                parameters.add(calculRelaValue);
              }
              if (bookId != null) {
                parameters.add(bookId);
              }
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
}


