package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;


public class AutoInfoPickDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public AutoInfoPick queryById(Serializable id) {
    Validate.notNull(id);
    return (AutoInfoPick) getHibernateTemplate().get(
        AutoInfoPick.class, id);
  }

  /**
   * 插入记录
   * 
   * @param AutoInfoPick
   * @return
   */
  public Serializable insert(AutoInfoPick autoInfoPick) {
    Serializable id = null;
    try {
      Validate.notNull(autoInfoPick);
      id = getHibernateTemplate().save(autoInfoPick);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 删除DA001
   * @author 吴迪 20071214
   * @param org_id 单位编号
   * @param org_head_id　AA202ID
   * @param  type
   * @return
   */
  public void deleteAutoInfoPick(final String orgid,final String org_head_id,
      final String type) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from AutoInfoPick autoInfoPick where autoInfoPick.orgId=? and autoInfoPick.orgHeadId=? and autoInfoPick.type=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, new Integer(org_head_id));
          query.setParameter(2, type);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 更新DA001
   * 
   * @param AutoInfoPick
   * @return
   */
  public void update(AutoInfoPick autoInfoPick) {
    try {
      Validate.notNull(autoInfoPick);
      getHibernateTemplate().update(autoInfoPick);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 删除更新DA001　删除用
   * @author 吴迪 20071213
   * @param status
   * @param center_head_id
   * @param org_id
   * @param org_head_id
   * @param  type
   * @return
   */
  public void deleteupdateAutoInfoPick(final String status,final String center_head_id,final String centerBizDate,final String org_id,final String centerhead_id,
      final String type) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql ="update AutoInfoPick autoInfoPick set autoInfoPick.status=?,autoInfoPick.centerHeadId=?,autoInfoPick.centerBizDate=? where autoInfoPick.orgId=? and autoInfoPick.centerHeadId=? and autoInfoPick.type=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, status);
          query.setParameter(1, center_head_id);
          if(centerBizDate.equals("")){
            query.setParameter(2, centerBizDate);
          }else{
            query.setParameter(2, new Date());
          }
          query.setParameter(3, new Integer(org_id));
          query.setParameter(4, new Integer(centerhead_id));
          query.setParameter(5, type);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 判断提交状态是否为未提取
   *  @author 吴迪 20071212
   * @param org_id　单位编号
   * @param org_head_id　单位版头表id aa202的id
   * @param　type　业务类型
   * @return flag　返回true是该记录的提交状态为未提取，false为不存在未提取　
   */
  public boolean isNOPickUp(final String org_id,final String org_head_id,final String type) {
    boolean flag = true;
    String count  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select count(*) from da001 da  where da.org_id=? and da.org_head_id=? and da.type=? and da.status=0";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(org_id));
            query.setParameter(1, new Integer(org_head_id));
            query.setParameter(2, type);
            return query.uniqueResult().toString();
          }
        });
     if (new Integer(count).intValue()==0) {
      flag = false;
    }  
    return flag;
  }
  /**
   * 查询DA001中的状态
   *  @author 吴迪 20071212
   * @param org_id　单位编号
   * @param org_head_id　单位版头表id aa202的id
   * @param　type　业务类型
   * @return status
   */
  public String findStatus(final String org_id, final String org_head_id,
      final String type) {
    String status = "";
    status = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select da.status  from da001 da  where da.org_id=? and da.org_head_id=? and da.type=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, new Integer(org_id));
        query.setParameter(1, new Integer(org_head_id));
        query.setParameter(2, type);
        return query.uniqueResult();
      }
    });
    if (status == null || status.equals("")) {
      status = "2";
    }
    return status;
  }
  /**
   * 判断DA001中是否存在提交记录
   * @author 吴迪 　20071212
   * @param org_id　单位编号
   * @param org_head_id　单位版头表id aa202的id
   * @param　type　业务类型
   * @return flag 返回true为存在提交的记录，false不存在提交的记录　
   */
  public boolean isNOPickIn(final String org_id,final String org_head_id,final String type) {
    boolean flag = true;
    String count  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select count(*) from da001 da  where da.org_id=? and da.org_head_id=? and da.type=?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(org_id));
            query.setParameter(1, new Integer(org_head_id));
            query.setParameter(2, type);
            return query.uniqueResult().toString();
          }
        });
    if (new Integer(count).intValue()==0) {
      flag = false;
    }    
    return flag;
  }
  /**
   * 查询最小的org_head_id
   * @author 吴迪 　20071212
   * @param org_id　单位编号
   * @param status　提取状态，0、未提取，1、已提取
   * @param　type　　业务类型
   * @return orgheadid
   */
  public String findOrgHeadid(final String org_id,final String type,final String status) {
    String orgheadid = "";
     orgheadid  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select min(da.org_head_id) from da001 da  where da.org_id=? and da.status=? and da.type=?" ;
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(org_id));
            query.setParameter(1, status);
            query.setParameter(2, type);
            if(query.uniqueResult()==null){
              return null;
            }else{
              return query.uniqueResult().toString();
            }
          
          }
        });
    return orgheadid;
  }
  /**
   * 根据单位编号查询在DA001中STATUS=0,TYPE=M,N,O的记录
   * @author 吴迪 　20071212
   * @param orgid  单位编号
   * @return list
   */
  public int queryNoPickUpListbyOrgid(final String org_id) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.status=0 and autoInfoPick.type in ('M','N','O') and autoInfoPick.orgId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(org_id));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }
  /**
   * 取出org_head_id和type 
   * @author 吴迪 　20071212
   * @return Object[]
   */
  public Object[] queryOrgHeadidAndType(final String orgid) {
    Object[] obj = new Object[2];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select da.org_head_id, da.type"
            +" from da001 da "
            +" where da.type in ('M', 'N', 'O') "
            +" and da.status = 0"
            +" and da.org_id = ? "
            +" and da.org_head_id ="
            +" (select min(da.org_head_id)"
            +" from da001 da "
            +" where  da.org_id = ?"
            +" and  da.type in ('M', 'N', 'O')"
            +" and da.status = 0"
            +" and da.pay_head_id ="
            +" (select min(da.pay_head_id)"
            +" from da001 da "
            +" where da.org_id = ?"
            +" and da.pay_head_id is not null"
            +" and da.status = 0"
            +" and da.type in ('M', 'N', 'O')))";
          Query query = session.createSQLQuery(sql);  
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, new Integer(orgid));
          query.setParameter(2, new Integer(orgid));
          return query.uniqueResult();       
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  /**业务变更的提取方法用到
   * 若PAY_HEAD_ID都为空，取出org_head_id和type
   * @author 吴迪 　20071212
   * @return Object[]
   */
  public Object[] findOrgHeadidAndType(final String orgid) {
    Object[] obj = new Object[2];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select da.org_head_id, da.type "
                      +" from da001 da "
                      +" where da.org_id = ? "
                      +" and da.type in ('M', 'N', 'O') "
                      +" and da.status = 0 "
                      +" and da.org_head_id = (select min(da.org_head_id) "
                      +" from da001 da "
                      +" where da.org_id = ? "
                      +" and da.type in ('M', 'N', 'O') "
                      +" and da.status = 0) ";
          Query query = session.createSQLQuery(sql);  
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, new Integer(orgid));
          return query.uniqueResult();       
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * 根据单位编号查询在DA001中STATUS=0,TYPE=K的记录
   * @author wangy 　2007-12-14
   * @param orgid  单位编号
   * @return int
   */
  public int queryPickUpCountbyOrgidAndStatusAndType(final Integer org_id) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.status=0 and autoInfoPick.type='K' and autoInfoPick.orgId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, org_id);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }
  /**
   * 更新DA001 提取用
   * @author 吴迪 20071213
   * @param status
   * @param center_head_id
   * @param org_id
   * @param centerheadid
   * @param  type
   * @return
   */


  public void updateAutoInfoPick(final String status,final String center_head_id,final String centerBizDate,final String org_id,final String centerhead_id,

      final String type) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {


          String hql ="update AutoInfoPick autoInfoPick set autoInfoPick.status=?,autoInfoPick.centerHeadId=?,autoInfoPick.centerBizDate=? where autoInfoPick.orgId=? and autoInfoPick.orgHeadId=? and autoInfoPick.type=?";

          Query query = session.createQuery(hql);
          query.setParameter(0, status);
          query.setParameter(1, center_head_id);

          if(centerBizDate.equals("")){
            query.setParameter(2, centerBizDate);
          }else{
            query.setParameter(2, new Date());
          }
          query.setParameter(3, new Integer(org_id));
          query.setParameter(4, new Integer(centerhead_id));
          query.setParameter(5, type);

          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }  
  /**
   * 根据单位编号查询在DA001中center_head_id,TYPE的记录
   * @author sy 　2007-12-19
   * @param orgid  单位编号
   * @return List
   */
  public List queryPickUpCountbyOrgidAndStatusAndType(final Integer center_head_id,final String Type)throws BusinessException {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.centerHeadId=? and autoInfoPick.type=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, center_head_id);
          query.setParameter(1, Type);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 根据aa204的id查询类型为o的orgheadid
   * @author 吴迪 　20071221　
   * @param chgPersonHeadID
   * @return orgheadid
   */
  public String queryOrgHeadid(final String chgPersonHeadID) {
    String orgheadid = "";
     orgheadid  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select da001.org_head_id from DA001 da001 where da001.center_head_id=? and da001.type='O'" ;
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(chgPersonHeadID));
            if(query.uniqueResult()==null){
              return null;
            }else{
              return query.uniqueResult().toString();
            }          
          }
        });
    return orgheadid;
  }
  /**
   * 查询orgheadid
   * @author 韩亮　20071221　
   * @param chgPersonHeadID
   * @return orgheadid
   */
  public String queryOrgHeadid1(final String chgPersonHeadID) {
    String orgheadid = "";
     try {
      orgheadid  = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String ssql = "select d001.org_head_id from da001 d001 where d001.center_head_id=? and d001.type='E' ";
              Query query = session.createSQLQuery(ssql);
              query.setParameter(0,new Integer(chgPersonHeadID));
              if(query.uniqueResult()==null){
                return null;
                
              }else{
                return query.uniqueResult().toString();
              }          
            }
          });
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return orgheadid;
  }
}
