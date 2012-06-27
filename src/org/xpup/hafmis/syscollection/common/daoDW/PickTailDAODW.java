package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickTailImportDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTaDTO;



public class PickTailDAODW extends HibernateDaoSupport {
 //在单位版数据库中，根据取出的ORG_HEAD_ID查询AA306、AA307，取出的记录，取出字段包括：
 // 职工编号（AA307.EMP_ID）、提取类型（AA307.PICK_TYPE）、
 // 提取原因（AA307.PICK_REASON）、
 // 提取金额（AA307.PICK_PRE_BALANCE+AA307.PICK_CUR_BALANCE）
  public List findPickTailList(final String minid) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a307.emp_id,a307.pick_type,a307.pick_reason,sum(a307.pick_pre_balance + a307.pick_cur_balance)" +
            " from aa306 a306, aa307 a307 " +
            "where a306.id = a307.pickhead_id and a306.id=? group by a307.emp_id,a307.pick_type, a307.pick_reason";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, minid);
        Iterator it = query.list().iterator();
        List temp_list = new ArrayList();
        PickTailImportDTO pickTailImportDTO1=new PickTailImportDTO();
        temp_list.add(pickTailImportDTO1);
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            PickTailImportDTO pickTailImportDTO=new PickTailImportDTO();
           
            if (obj[0] != null) {
              pickTailImportDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              pickTailImportDTO.setPickType(obj[1].toString());
            }
            if (obj[2] != null) {
              pickTailImportDTO.setPickReason(obj[2].toString());
            }
            if (obj[3] != null) {
              pickTailImportDTO.setPickBalance(obj[3].toString());
            }
            temp_list.add(pickTailImportDTO);
          }
      }
        return temp_list;
      }
    });
    return list;
  }
  /**
   * 李文浩　根据单位编号和职工编号查找AA308表
   */
  public SpecialPick  querySpecialPick(final int orgId,final int empId){
    try{
      Object obj = getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Object o = session.createQuery("from SpecialPick s where s.org.id=? and s.empId=?")
              .setInteger(0, orgId).setInteger(1, empId).setMaxResults(1).uniqueResult();
              return o;
            }
          }
      );
      return (SpecialPick)obj;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  /**
   * 插入记录
   * @param specialPick
   * @return
   */
  public Serializable insert(SpecialPick specialPick){
    Serializable id = null;
    try{    
    Validate.notNull(specialPick);
    id = getHibernateTemplate().save(specialPick);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}
