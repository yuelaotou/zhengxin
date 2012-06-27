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
import org.xpup.hafmis.sysloan.common.domain.entity.Build;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.HouseAddListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.DeveloperDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto.FloorDTO;

/**
 * 开发商开发项目信息表PL006
 * 
 * @author 李娟 2007-9-13
 */
public class DevelopProjectDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public DevelopProject queryById(Serializable id) {
    Validate.notNull(id);
    return (DevelopProject) getHibernateTemplate()
        .get(DevelopProject.class, id);
  }
  
  public Build queryById_build(Serializable id) {
    Validate.notNull(id);
    return (Build) getHibernateTemplate()
        .get(Build.class, id);
  }

  /**
   * 修改记录的方法
   * 
   * @param developProject
   * @author 付云峰
   */
  public void updateDevelopProject(DevelopProject developProject) {
    Validate.notNull(developProject);
    getHibernateTemplate().update(developProject);
  }
  
  public void updateBuild(Build build) {
    Validate.notNull(build);
    getHibernateTemplate().update(build);
  }
  
  public void deleteBuild(final Build build) {
   // Validate.notNull(build);
   // getHibernateTemplate().delete(build);
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update Build build "
              + "set build.build_status = '3' "
              + "where build.buildId = ? ";
          session.createQuery(hql).setString(0, build.getBuildId().toString()).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 插入记录
   * 
   * @param developProject
   * @return
   */
  public Serializable insert(DevelopProject developProject) {
    Serializable id = null;
    try {
      Validate.notNull(developProject);
      id = getHibernateTemplate().save(developProject);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  
  public Serializable insert(Build build) {
    Serializable id = null;
    try {
      Validate.notNull(build);
      id = getHibernateTemplate().save(build);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 根据主键得到楼盘状态
   * 
   * @param id
   * @return
   */
  public String queryDevelopProjectST(final Integer id) {

    String developProjectST = "";
    try {
      developProjectST = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p6.floor_st from pl006 p6 where p6.floor_id=?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);
              return query.uniqueResult();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return developProjectST;
  }
  /**
   * @author wshuang
   * 查询该开发商下的所有楼盘,按楼盘名分组
   * @param developerId
   * @param orderBy
   * @param order
   * @return
   * @throws Exception
   */
  public List queryFloorName(final String developerId,
      final String orderBy, final String order) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select p6.floor_name,max(p6.floor_addr) from pl006 p6 where p6.head_id = ? ";

          String ob = orderBy;
          if (ob == null)
            ob = " min(p6.floor_id) ";

          String od = order;
          if (od == null)
            od = "DESC";
          sql = sql + " group by p6.floor_name order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, developerId);

          FloorListDTO floorListDTO = null;
          Object obj[] = null;

          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            floorListDTO = new FloorListDTO();
            floorListDTO.setFloorName(obj[0].toString());
            if(obj[1] != null) {
              floorListDTO.setFloorAddress(obj[1].toString());
            }
            temp_list.add(floorListDTO);
          }
          return temp_list;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  public List querySubFloorInfoList(final String developerId,
      final String floorName, final String orderBy, final String order) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select p6.floor_id," + " p6.region,"
              + " p6.item_totle_amnt," + " p6.item_finish_degree,"
              + " p6.house_price," + " p6.building_area," + " p6.fund_status,"
              + " p6.floor_st,"
              + " p6.floor_addr||'-'||p6.floor_num"
              + " from pl006 p6"
              + " where p6.floor_st <> 3 and p6.head_id = ? and p6.floor_name = ?";

          String ob = orderBy;
          if (ob == null)
            ob = " p6.floor_addr ";

          String od = order;
          if (od == null)
            od = "DESC";
          sql = sql + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, developerId);
          query.setParameter(1, floorName);
          FloorListDTO floorListDTO = null;
          Object obj[] = null;

          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            floorListDTO = new FloorListDTO();
            floorListDTO.setFloorId(obj[0].toString());
            if (obj[1] != null) {
              floorListDTO.setRegion(obj[1].toString());
            }
            if (obj[2] != null) {
              floorListDTO.setItemTotleAmnt(obj[2].toString());
            }
            if (obj[3] != null) {
              floorListDTO.setItemFinishDegree(obj[3].toString());
            }
            if (obj[4] != null) {
              floorListDTO.setHousePrice(obj[4].toString());
            }
            if (obj[5] != null) {
              floorListDTO.setBuildingArea(obj[5].toString());
            }
            if (obj[6] != null) {
              floorListDTO.setFundStatus(obj[6].toString());
            }
            if (obj[7] != null) {
              floorListDTO.setFloorSt(obj[7].toString());
            }
            // 设置楼盘号(在页面就是楼盘名所以设置到这个字段当中)
            if (obj[8] != null) {
              floorListDTO.setFloorName(obj[8].toString());
            }
            temp_list.add(floorListDTO);
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
   * 根据PL005的id查询楼盘列表的相关信息
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return 列表中的内容
   * @throws Exception
   * @author 付云峰
   */
  public List queryDevelopProjectList(final String id, final String orderBy,
      final String order, final int start, final int pageSize) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select " + "p6.floor_name," + "p6.floor_num,"
              + "p6.region," + "p6.item_totle_amnt," + "p6.item_finish_degree,"
              + "p6.house_price," + "p6.building_area," + "p6.floor_st,"
              + "p6.floor_id,p6.fund_status " + " from pl006 p6 where p6.floor_st<>3 and p6.head_id=?";

          String ob = orderBy;
          if (ob == null)
            ob = " p6.floor_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, id);

          if (pageSize != 0) {
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }

          FloorListDTO floorListDTO = null;
          Object obj[] = null;

          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            floorListDTO = new FloorListDTO();

            floorListDTO.setFloorName(obj[0].toString());
            floorListDTO.setFloorNum(obj[1].toString());
            if (obj[2] != null) {
              floorListDTO.setRegion(obj[2].toString());
            }
            if (obj[3] != null) {
              floorListDTO.setItemTotleAmnt(obj[3].toString());
            }
            if (obj[4] != null) {
              floorListDTO.setItemFinishDegree(obj[4].toString());
            }
            if (obj[5] != null) {
              floorListDTO.setHousePrice(obj[5].toString());
            }
            if (obj[6] != null) {
              floorListDTO.setBuildingArea(obj[6].toString());
            }
            floorListDTO.setFloorSt(obj[7].toString());
            floorListDTO.setFloorId(obj[8].toString());
            floorListDTO.setFundStatus(obj[9].toString());

            temp_list.add(floorListDTO);
          }

          return temp_list;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  
  public List queryBuildList(final String id, final String buildId, final String buildNum, final String orderBy,
      final String order, final int start, final int pageSize, final int page) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.build_id,t.floor_id,t.build_add,t.build_num,t.build_s," +
              "t.fund_status,t.reserved from pl006_1 t " +
              "where t.BUILD_STATUS is null and t.floor_id = ? ";

          if(buildId != null && !"".equals(buildId)){
            hql += " and t.build_id like '%"+new Integer(buildId)+"%' ";
          }
          if(buildNum != null && !"".equals(buildNum)){
            hql += " and t.build_num like '%"+buildNum+"%' ";
          }
          
          String ob = orderBy;
          if (ob == null)
            ob = " t.build_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(id));

          if (pageSize != 0) {
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }

          BuildDTO buildDTO = null;
          Object obj[] = null;

          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          List queryList=query.list();
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            queryList=query.list();
          }

          Iterator iterate = queryList.iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            buildDTO = new BuildDTO();

            buildDTO.setBuildId(obj[0].toString());
            buildDTO.setFloorId(obj[1].toString());
            if (obj[2] != null) {
              buildDTO.setBuildAdd(obj[2].toString());
            }
            if (obj[3] != null) {
              buildDTO.setBuildNum(obj[3].toString());
            }
            if (obj[4] != null) {
              buildDTO.setBuild_s(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              if(obj[5].toString().equals("1")){
                buildDTO.setFundStatus("是");
              }else{
                buildDTO.setFundStatus("否");
              }
            }
            if (obj[6] != null) {
              buildDTO.setReserved(obj[6].toString());
            }
            temp_list.add(buildDTO);
          }

          return temp_list;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List queryBuildList_exp(final String id, final String buildId, final String buildNum, final String orderBy,
      final String order, final int start, final int pageSize) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.build_id,t.floor_id,t.build_add,t.build_num,t.build_s," +
              "t.fund_status,t.reserved from pl006_1 t " +
              "where t.floor_id = ? ";

          if(buildId != null && !"".equals(buildId)){
            hql += " and t.build_id like '%"+new Integer(buildId)+"%' ";
          }
          if(buildNum != null && !"".equals(buildNum)){
            hql += " and t.build_num like '%"+buildNum+"%' ";
          }
          
          String ob = orderBy;
          if (ob == null)
            ob = " t.build_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(id));         

          BuildDTO buildDTO = null;
          Object obj[] = null;

          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            buildDTO = new BuildDTO();

            buildDTO.setBuildId(obj[0].toString());
            buildDTO.setFloorId(obj[1].toString());
            if (obj[2] != null) {
              buildDTO.setBuildAdd(obj[2].toString());
            }
            if (obj[3] != null) {
              buildDTO.setBuildNum(obj[3].toString());
            }
            if (obj[4] != null) {
              buildDTO.setBuild_s(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              buildDTO.setFundStatus(obj[5].toString());
            }
            if (obj[6] != null) {
              buildDTO.setReserved(obj[6].toString());
            }
            temp_list.add(buildDTO);
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
   * 求出楼盘列表的count
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return count
   * @throws Exception
   */
  public int queryDevelopProjectCount(final String id) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select " + "p6.floor_id"
              + " from pl006 p6 where p6.floor_st<>3 and p6.head_id=?";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, id);

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }
  
  public int queryBuildCount(final String id, final String buildId, final String buildNum) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select " + "t.build_id"
              + " from pl006_1 t where t.BUILD_STATUS is null and t.floor_id=?";
          if(buildId != null && !"".equals(buildId)){
            hql += " and t.build_id like '%"+new Integer(buildId)+"%' ";
          }
          if(buildNum != null && !"".equals(buildNum)){
            hql += " and t.build_num like '%"+buildNum+"%' ";
          }
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(id));
          

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  /**
   * 返回开发商所属楼盘的漏洞号
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public List queryDevelopProjectName(final String id) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select " + "p6.floor_num"
              + " from pl006 p6 where p6.head_id=? and p6.FLOOR_ST<>'3'";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, id);

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  
  public List queryDevelopProjectid(final String id) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select " + "p6.floor_id"
              + " from pl006 p6 where p6.head_id=? and p6.FLOOR_ST<>'3'";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, id);

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List queryDevelop_SumFloorNum(final String id) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select " + "p6.build_id"
              + " from pl006_1 p6 where p6.floor_id=?";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, id);

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public String findFloorByPkid(final String floorid) {

    String floorname = "";
    try {
      floorname = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.floor_name from pl006 p where p.floor_id=?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, floorid);
              return query.uniqueResult();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return floorname;
  }
  public String findFloorByPkid_yg(final String floorid) {
    String floorname = "";
    try {
      floorname = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              
              String hql = "select p.floor_addr from pl006 p where p.floor_id=?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, floorid);
              Object obj = query.uniqueResult();
              return obj==null?"":obj.toString();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return floorname;
  }

  /**
   * hanl 求出楼盘号
   * 
   * @param floorid
   * @return
   */
  public String findFloornumByPkid(final String floorid, final String heid) {
    String floorname = null;
    try {
      floorname = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.floor_num from pl006 p where p.floor_id=? and p.head_id=? ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, floorid);
              query.setParameter(1, heid);
              return query.uniqueResult();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return floorname;
  }
  /**
   * hanl 根据楼盘和开发商id 求主键
   * 
   * @param floorid
   * @param heid
   * @return
   */
  public String findFlooridByflooridheid(final String floorid, final String heid) {

    String floorname = null;
    try {
      floorname = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.floor_id from pl006 p where p.floor_name=? and p.head_id=? ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, floorid);
              query.setParameter(1, heid);
              return query.uniqueResult().toString();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return floorname;
  }

  /**
   * hanl 根据楼盘id和开发商id求出楼号
   * 
   * @param floorId
   * @param buyhouseorgid
   * @return
   */
  public String findFloorNumByflooridheid(final String floorId,
      final String buyhouseorgid) {

    String floorname = null;
    try {
      floorname = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.floor_num from pl006 p where p.floor_id=? and p.head_id=? ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, floorId);
              query.setParameter(1, buyhouseorgid);
              if(query.uniqueResult()==null){
                return null;
              }
              return query.uniqueResult().toString();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return floorname;
  }
  /**
   * 根据楼盘开发商编号和楼盘名称查询所有的楼号
   * @param floorId
   * @param buyhouseorgid
   * @return
   */
  public List queryAllBuildNumList(final String developId,
      final String floorName) {

    List list = null;
    try {
      list =  getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select t.floor_id,t.floor_addr||'-'||t.floor_num from pl006 t where t.head_id = ? and t.floor_name = ?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, developId);
              query.setParameter(1, floorName);
              List li = new ArrayList();
              Object[] obj= null;
              Iterator it = query.list().iterator();
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                FloorListDTO fdto = new FloorListDTO();
                fdto.setFloorId(obj[0].toString());
                fdto.setFloorNum(obj[1].toString());
                li.add(fdto);
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
   * 根据楼盘号查询房屋列表
   * @author wangshuang
   * @param floorId
   * @return
   */
  public List queryHouseAddListByFloorId(final String floorId) {
    List list = null;
    try {
      list =  getHibernateTemplate().executeFind(
          new HibernateCallback() {
            
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              String hql = "select t.build_id,t.build_num,t.build_add from pl006_1 t where t.floor_id = ? ";
                         
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, floorId);
              List returnList = new ArrayList();
              Object[] obj= null;
              Iterator it = query.list().iterator();
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                HouseAddListDTO dto = new HouseAddListDTO();
                dto.setBuildId(obj[0].toString());
                if(obj[1]!=null){
                  dto.setBuildNum(obj[1].toString());
                }else{
                  dto.setBuildNum("");
                }
                if(obj[2]!=null){
                  dto.setBuildAdd(obj[2].toString());
                }else{
                  dto.setBuildAdd("");
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
  public List queryHouseAddListByContractId(final String contractId) {
    List list = null;
    try {
      list =  getHibernateTemplate().executeFind(
          new HibernateCallback() {
            
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              String hql = "select t.contract_id,t.room_num from pl006_1 t where t.contract_id = ? ";
                         
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contractId);
              List returnList = new ArrayList();
              Object[] obj= null;
              Iterator it = query.list().iterator();
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                HouseAddListDTO dto = new HouseAddListDTO();
                if(obj[0]!=null){
                  dto.setBuildNum(obj[0].toString());
                }
                if(obj[1]!=null){
                  dto.setBuildAdd(obj[1].toString());
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
   * hanl 开发商弹出框
   * 求出正常的开发商
   * @param developername
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @return
   */
  public List findDeveloperSomeList(final String developername,
      final String orderBy, final String orderother, final int start,
      final int pageSize, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          Vector parameters = new Vector();
          String criterion = "";

          if (developername != null && !"".equals(developername)) {
            criterion += " and p5.developer_name like ? ";
            parameters.add("%" + developername + "%");
          }

          String hql = "select p5.id,p5.developer_code,p5.developer_name,p5.artfclprsn,p5.artfclprsn_tel"
              + " from pl005 p5 where p5.developer_st='0' "
              + " and p5.reservea_b = 1"
              + " and p5.office " + securityInfo.getOfficeSecuritySQL();

          String ob = orderBy;
          if (ob == null)
            ob = " p5.id ";

          String od = orderother;
          if (od == null)
            od = "desc";
          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          if (pageSize != 0) {
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }

          DeveloperDTO developerDTO = null;
          Object obj[] = null;

          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            developerDTO = new DeveloperDTO();

            developerDTO.setPid(obj[0].toString());
            developerDTO.setDeveloperid(obj[1].toString());
            developerDTO.setDevelopername(obj[2].toString());

            if (obj[3] != null) {
              developerDTO.setArtfclprsn(obj[3].toString());
            }
            if (obj[4] != null) {
              developerDTO.setArtfclprsntel(obj[4].toString());
            }

            temp_list.add(developerDTO);
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
   * hanl 求出记录数
   * 求出正常的开发商
   * @param developername
   * @return
   */
  public int findDeveloperSomeCount(final String developername, 
      final SecurityInfo securityInfo) {

    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          Vector parameters = new Vector();
          String criterion = "";

          if (developername != null && !"".equals(developername)) {
            criterion += " and p5.developer_name like ? ";
            parameters.add("%"+developername+"%");
          }

          String hql = "select count(p5.id) from pl005 p5 where p5.developer_st='0' "
            + " and p5.reservea_b = 1"
            + " and p5.office " + securityInfo.getOfficeSecuritySQL();

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return count.intValue();
  }
  /**
   * hanl 开发商弹出框
   * 求出全部的开发商
   * @param developername
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @return
   */
  public List findDeveloperList(final String developername,
      final String orderBy, final String orderother, final int start,
      final int pageSize, final SecurityInfo securityInfo, final String isSpecial) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          Vector parameters = new Vector();
          String criterion = "";

          if (developername != null && !"".equals(developername)) {
            criterion += " and p5.developer_name like ? ";
            parameters.add("%"+developername+"%");
          }

          String hql = "select p5.id,p5.developer_code,p5.developer_name,p5.artfclprsn,p5.artfclprsn_tel"
              + " from pl005 p5 where p5.developer_st='0' "
              + " and p5.office " + securityInfo.getOfficeSecuritySQL();
          if(isSpecial != null) {
            hql += " and p5.reservea_b = 0";
          } else {
            hql += " and p5.reservea_b = 1";
          }
          String ob = orderBy;
          if (ob == null)
            ob = " p5.id ";

          String od = orderother;
          if (od == null)
            od = "desc";
          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          if (pageSize != 0) {
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }

          DeveloperDTO developerDTO = null;
          Object obj[] = null;

          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            developerDTO = new DeveloperDTO();

            developerDTO.setPid(obj[0].toString());
            developerDTO.setDeveloperid(obj[1].toString());
            developerDTO.setDevelopername(obj[2].toString());

            if (obj[3] != null) {
              developerDTO.setArtfclprsn(obj[3].toString());
            }
            if (obj[4] != null) {
              developerDTO.setArtfclprsntel(obj[4].toString());
            }

            temp_list.add(developerDTO);
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
   * hanl 求出记录数
   * 求出全部的开发商
   * @param developername
   * @return
   */
  public int findDeveloperCount(final String developername,
      final SecurityInfo securityInfo, final String isSpecial) {

    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          Vector parameters = new Vector();
          String criterion = "";

          if (developername != null && !"".equals(developername)) {
            criterion += " and p5.developer_name like ? ";
            parameters.add("%"+developername+"%");
          }

          String hql = "select p5.id,p5.developer_id,p5.developer_name,p5.artfclprsn,p5.artfclprsn_tel from pl005 p5 where p5.developer_st='0' "
            + " and p5.office " + securityInfo.getOfficeSecuritySQL();
          if(isSpecial != null) {
            hql += " and p5.reservea_b = 0";
          } else {
            hql += " and p5.reservea_b = 1";
          }
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return Integer.valueOf(query.list().size()+"");
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return count.intValue();
  }

  /**
   * 担保方式统计
   * 
   * @author 王野 2007-10-13 条件查询楼盘列表
   * @param floorName
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @return
   */
  public List findFloorInfoList(final String floorName, final String orderBy,
      final String orderother, final int start, final int pageSize) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p006.floor_id, p005.developer_name, p006.floor_name, p006.floor_st from pl006 p006, pl005 p005 where p006.head_id = p005.id ";

          Vector parameters = new Vector();
          String criterion = "";

          if (floorName != null && !"".equals(floorName)) {
            criterion += " p006.floor_name = ? and ";
            parameters.add(floorName);
          }

          String ob = orderBy;
          if (ob == null)
            ob = " p006.floor_id ";

          String od = orderother;
          if (od == null)
            od = "desc";

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          
          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          if (pageSize != 0) {
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
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
   * 担保方式统计
   * 
   * @author 王野 2007-10-13 查询楼盘列表记录数(count)
   * @param floorName
   * @return
   */
  public int findFloorInfoCount(final String floorName) {
    int count = 0;
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p006.floor_id, p005.developer_name, p006.floor_name, p006.floor_st from pl006 p006, pl005 p005 where p006.head_id = p005.id ";

          Vector parameters = new Vector();
          String criterion = "";

          if (floorName != null && !"".equals(floorName)) {
            criterion += " p006.floor_name = ? and ";
            parameters.add(floorName);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      });
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 担保方式统计
   * 
   * @author 王野 2007-10-13 查询全部楼盘ID和楼盘名称
   * @return
   */
  public List queryAllFloorIdAndFloorName() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p006.floor_id, p006.floor_name from pl006 p006 ";

          Query query = session.createSQLQuery(hql);

          Iterator iterate = query.list().iterator();

          FloorDTO floorDTO = null;
          List tempList = new ArrayList();
          Object obj[] = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            floorDTO = new FloorDTO();
            if (obj[0] != null)
              floorDTO.setFloorId(obj[0].toString());
            if (obj[1] != null)
              floorDTO.setFloorName(obj[1].toString());
            tempList.add(floorDTO);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 担保方式统计查询
   * 
   * @author 王野 2007-10-13 通过pl006.floor_id获得楼盘NAME和开发商ID
   * @param floorId
   * @return
   */
  public List queryFloorNameAndHeadIdByFloorId(final String floorId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.floor_name,t.head_id from pl006 t where t.floor_id = ? ";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, floorId);

          Iterator iterate = query.list().iterator();

          FloorDTO floorDTO = null;
          List tempList = new ArrayList();
          Object obj[] = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            floorDTO = new FloorDTO();
            if (obj[0] != null)
              floorDTO.setFloorName(obj[0].toString());
            if (obj[1] != null)
              floorDTO.setDeveloperId(obj[1].toString());
            tempList.add(floorDTO);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 求出下来框，楼盘
   * 
   * @param buyhouseorgid
   * @return
   */

  public List findFloorNameList(final String buyhouseorgid) {

    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p.floor_id,p.floor_name from pl006 p where p.head_id=? ";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, buyhouseorgid);

          Iterator iterate = query.list().iterator();

          List tempList = new ArrayList();
          Object obj[] = null;
          while (iterate.hasNext()) {
            DevelopProject dp=new DevelopProject();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              dp.setFloorId(new Integer(obj[0].toString()));
              dp.setFloorName(obj[1].toString());
            }
            tempList.add(dp);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public String findBuildFunStatus(final String floorId,
      final String developId) {

    String status = "";
    try {
      status = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.fund_status from pl006 p where p.floor_id=? and p.head_id=? ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, floorId);
              query.setParameter(1, developId);
              if(query.uniqueResult()==null){
                return "";
              }
              return query.uniqueResult().toString();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return status;
  }
  
  public void updateBuildStatus(final String floorId)
  throws Exception {
try {
  getHibernateTemplate().execute(new HibernateCallback() {
    public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
      String hql = "update Build build "
          + "set build.fundStatus = '0' "
          + "where build.floorId = ? ";
      session.createQuery(hql).setString(0, floorId).executeUpdate();
      return null;
    }
  });
} catch (Exception e) {
  e.printStackTrace();
}
}
  public String findFloornum(final String id) {

    String status = "";
    try {
      status = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select b.floor_num from pl006 b where b.floor_id =?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id.toString());            
              if(query.uniqueResult()==null){
                return "";
              }
              return query.uniqueResult().toString();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return status;
  }
  public String findFloorname(final String id) {

    String status = "";
    try {
      status = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select b.floor_name from pl006 b where b.floor_id =?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id.toString());            
              if(query.uniqueResult()==null){
                return "";
              }
              return query.uniqueResult().toString();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return status;
  }
  
  public String queryFloorIdByCriterion(final String developerId,
      final String floorName,final String floorNum){
    String floorId = "";
    try {
      floorId = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = " select t.floor_id from pl006 t where t.head_id = ?"
                         + " and t.floor_name = ? and t.floor_num = ?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, developerId.toString());            
              query.setParameter(1, floorName.toString());            
              query.setParameter(2, floorNum.toString());            
              if(query.uniqueResult()==null){
                return "";
              }
              return query.uniqueResult().toString();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return floorId;
  }
  

}
