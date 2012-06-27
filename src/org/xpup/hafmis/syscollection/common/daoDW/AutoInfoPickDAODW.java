package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;


public class AutoInfoPickDAODW extends HibernateDaoSupport {
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
   * 删除更新DA001
   * @author 吴迪 20071213
   * @param status
   * @param center_head_id
   * @param org_id
   * @param centerheadid
   * @param  type
   * @return
   */


  public void deleteUpdateAutoInfoPick(final String status,final String center_head_id,final String centerBizDate,final String org_id,final String centerhead_id,

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
   * 更新DA001　　提取用
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
    try{
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
    }catch(Exception e){
      e.printStackTrace();
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
   * 根据单位编号查询在DA001中STATUS=0,TYPE=M(工资基数调整)、N(缴额调整)的记录
   * @author wangy 　2007-12-18
   * @param orgHeadId  缴额变更头表ID_AA202.ID
   * @param 由MonthpayBS调用
   * @return int
   */
  public int queryNoPickUpListbyOrgHeadId_M_N(final String orgHeadId) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.status=0 and autoInfoPick.type in('M','N') and autoInfoPick.orgHeadId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgHeadId));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }
  
  /**
   * 根据单位编号查询在DA001中STATUS=0,TYPE=O(人员变更)的记录
   * @author wangy 　2007-12-18
   * @param orgHeadId  人员变更头表ID_AA204.ID
   * @param 由MonthpayBS调用
   * @return int
   */
  public int queryNoPickUpListbyOrgHeadId_O(final String orgHeadId) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.status=0 and autoInfoPick.type='O' and autoInfoPick.orgHeadId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgHeadId));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * 根据单位版头表ID,更新DA001
   * @author wangy 　2007-12-18
   * @param orgHeadId  缴额变更头表ID_AA204.ID
   * @param 由MonthpayBS调用
   */
  public AutoInfoPick updateAutoInfoPickByMonthPayment_M_N(final String orgHeadId) throws BusinessException {
    AutoInfoPick autoInfoPick = null;
    try {
      autoInfoPick  = (AutoInfoPick) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql ="select autoInfoPick from AutoInfoPick autoInfoPick where autoInfoPick.orgHeadId=? and autoInfoPick.type in('M','N')";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgHeadId));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      throw new BusinessException("qqqqqq");
    }
    return autoInfoPick;
  }
  
  /**
   * 根据单位版头表ID,更新DA001
   * @author wangy 　2007-12-18
   * @param orgHeadId  人员变更头表ID_AA204.ID
   * @param 由MonthpayBS调用
   */
  public AutoInfoPick updateAutoInfoPickByMonthPayment_O(final String orgHeadId) throws BusinessException {
    AutoInfoPick autoInfoPick = null;
    try {
      autoInfoPick  = (AutoInfoPick) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql ="select autoInfoPick from AutoInfoPick autoInfoPick where autoInfoPick.orgHeadId=? and autoInfoPick.type='O'";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgHeadId));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return autoInfoPick;
  }
  
}
