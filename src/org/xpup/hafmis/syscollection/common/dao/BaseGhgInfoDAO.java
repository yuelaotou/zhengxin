package org.xpup.hafmis.syscollection.common.dao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.BaseGhgInfo;
public class BaseGhgInfoDAO extends HibernateDaoSupport{
  public BaseGhgInfo queryById(Integer id) {
    Validate.notNull(id);
    return  (BaseGhgInfo) getHibernateTemplate().get(BaseGhgInfo.class,id);    
  }
  public Serializable insert(BaseGhgInfo baseGhgInfo){
    Serializable id = null;
    try{    
    Validate.notNull(baseGhgInfo);
    id = getHibernateTemplate().save(baseGhgInfo); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  public List getOpenMondifyInfoCount_YG(final String orgId,final String orgName,final String time,final String timeEnd,final SecurityInfo se){
    List list_yg=new ArrayList();
    try{
      list_yg = getHibernateTemplate().executeFind(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from BaseGhgInfo b  ";
              List parameters = new ArrayList();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += "b.org.id = ?  and ";
                parameters.add(new Integer(orgId));
              }
              if (orgName != null&&!orgName.equals("")) {
                criterion += "b.org.orgInfo.name like ?  and ";
                parameters.add("%"+orgName+"%");
              }
              if(time!=null && !time.equals("") && timeEnd!=null && !timeEnd.equals("")){
                criterion+= "to_char(b.opTime,'YYYYMMDD') between ? and ? and";
                parameters.add(time);
                parameters.add(timeEnd);
              }
              if (criterion.length() != 0)
                criterion = "where b.org.id "+se.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for(int i=0;i<parameters.size();i++){
                query.setParameter(i, parameters.get(i));
              }
              List l = query.list();
              return l;
            }
          }
      );
    }catch(Exception s){
      s.printStackTrace();
    }
    return list_yg;
  }
  public List getOpenMondifyInfoList(final String orgId,final String orgName,final String time,final String timeEnd,final String orderBy,
      final String order,final int start,final int pageSize,final SecurityInfo se){
    try{
      List list = getHibernateTemplate().executeFind(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from BaseGhgInfo b  ";
              List parameters = new ArrayList();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += "b.org.id = ?  and ";
                parameters.add(new Integer(orgId));
              }
              if (orgName != null&&!orgName.equals("")) {
//                criterion += "e.empInfo.name  like ?  and ";
//                parameters.add("%"+dto.getEmpName().trim()+"%");
                criterion += "b.org.orgInfo.name like ?  and ";
                parameters.add("%"+orgName+"%");
              }
              if(time!=null && !time.equals("") && timeEnd!=null && !timeEnd.equals("")){
                criterion+= "to_char(b.opTime,'YYYYMMDD') between ? and ? and";
                parameters.add(time);
                parameters.add(timeEnd);
              }
              if (criterion.length() != 0)//因为只要是经过这个方法就是有值的..不然还得判断一下..如果没有值写上一条加权限的语句--修改
                criterion = "where b.org.id "+se.getGjjSecurityHqlSQL()+" and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
//              criterion = "where "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
              String ob = orderBy;
              if (ob == null)
                ob = " b.org.id ";
              String od = order;
              if (od == null)
                od = "DESC";
              hql = hql + criterion + "order by " + ob + " " + order;
              Query query = session.createQuery(hql);
              for(int i=0;i<parameters.size();i++){
                query.setParameter(i, parameters.get(i));
              }
              List l = query.setFirstResult(start).setMaxResults(pageSize).list();
              return l;
            }
          }
      );
      return list;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  public int getOpenMondifyInfoCount(final String orgId,final String orgName,final String time,final String timeEnd,final SecurityInfo se){
    int count = 0;
    try{
      List list = getHibernateTemplate().executeFind(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from BaseGhgInfo b  ";
              List parameters = new ArrayList();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += "b.org.id = ?  and ";
                parameters.add(new Integer(orgId));
              }
              if (orgName != null&&!orgName.equals("")) {
                criterion += "b.org.orgInfo.name like ?  and ";
                parameters.add("%"+orgName+"%");
              }
              if(time!=null && !time.equals("") && timeEnd!=null && !timeEnd.equals("")){
                criterion+= "to_char(b.opTime,'YYYYMMDD') between ? and ? and";
                parameters.add(time);
                parameters.add(timeEnd);
              }
              if (criterion.length() != 0)
                criterion = "where b.org.id "+se.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for(int i=0;i<parameters.size();i++){
                query.setParameter(i, parameters.get(i));
              }
              List l = query.list();
              return l;
            }
          }
      );
     count = list.size();
     return count;
    }catch(Exception s){
      s.printStackTrace();
    }
    return count;
  }
  public List getPrintInfo(final String orgId,final String orgName,final String time,final String timeEnd,final SecurityInfo se){
    int count = 0;
    try{
      List list = getHibernateTemplate().executeFind(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from BaseGhgInfo b  ";
              List parameters = new ArrayList();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += "b.org.id = ?  and ";
                parameters.add(new Integer(orgId));
              }
              if (orgName != null&&!orgName.equals("")) {
                criterion += "b.org.orgInfo.name like ?  and ";
                parameters.add("%"+orgName+"%");
              }
              if(time!=null && !time.equals("") && timeEnd!=null && !timeEnd.equals("")){
                criterion+= "to_char(b.opTime,'YYYYMMDD') between ? and ? and";
                parameters.add(time);
                parameters.add(timeEnd);
              }
              if (criterion.length() != 0)
                criterion = "where b.org.id "+se.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for(int i=0;i<parameters.size();i++){
                query.setParameter(i, parameters.get(i));
              }
              List l = query.list();
              return l;
            }
          }
      );
      return list;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
}
