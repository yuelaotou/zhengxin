package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
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

import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickTailImportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutListImportDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTcDTO;



/**
 * 转出头表
 * 
 * @author 李娟 2007-6-19
 */
public class TranOutTailDAODW extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public TranOutHead queryById(Serializable id) {
   
    Validate.notNull(id);
  
   return (TranOutHead) getHibernateTemplate().get(TranOutHead.class, id);
  }
  /**
   * 插入记录
   * 
   * @param empInfo
   * @return
   */
  public Serializable insert(TranOutHead tranOutHead) {
    Serializable id = null;
    try {
      Validate.notNull(tranOutHead);
      id = getHibernateTemplate().save(tranOutHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }
  public List findailList(final String minid) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a310.emp_id,b002.name,b002.card_kind,b002.card_num,a310.is_sett_intrerest " +
            "from aa309 a309,aa310 a310,aa002 a002,ba002 b002 where a002.emp_info_id=b002.id " +
            "and a309.id=a310.tran_out_head_id and a309.out_org_id=a002.org_id and a310.emp_id=a002.id and a310.tran_out_head_id=? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, minid);
        Iterator it = query.list().iterator();
        List temp_list = new ArrayList();
        TranoutListImportDTO tranoutListImportDTO1=new TranoutListImportDTO();
        temp_list.add(tranoutListImportDTO1);
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            TranoutListImportDTO tranoutListImportDTO=new TranoutListImportDTO();
           
            if (obj[0] != null) {
              tranoutListImportDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              tranoutListImportDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              tranoutListImportDTO.setCard_king(obj[2].toString());
            }
            if (obj[3] != null) {
              tranoutListImportDTO.setCard_num(obj[3].toString());
            }
            if (obj[4] != null) {
              tranoutListImportDTO.setIssettinrest(obj[4].toString());
            }
            temp_list.add(tranoutListImportDTO);
          }
      }
        return temp_list;
      }
    });
    return list;
  }
  /**
   * hanl
   * @param minid
   * @param empId
   * @return
   */
  public TranOutTail queryTailInfo(final String minid,final Integer empId) {
    TranOutTail tranOutTail=new TranOutTail();
    tranOutTail = (TranOutTail)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from TranOutTail tail ";
        Vector parameters = new Vector();
        String criterion = "";
        if (minid != null && !minid.equals("")) {
          criterion += "tail.tranOutHead.id=? and ";
          parameters.add(new Integer(minid));
        }
        if (empId != null && !empId.equals("")) {
          criterion += "tail.empId=? and ";
          parameters.add(empId);
        }
        if (criterion.length() != 0)
          criterion = " where  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;

        Query query0 = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query0.setParameter(i, parameters.get(i));
        }
        return query0.uniqueResult();
      }
    });

    return tranOutTail;
  
  }
  /**
   * hanl
   * 求出311的主键
   * @param minid
   * @return
   */
  public String queryPkid(final String minid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a311.id from aa311 a311 where a311.tran_out_head_id=? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, minid);
            if (query.uniqueResult() == null) {
              return null;
            }
            return query.uniqueResult().toString();
          }
        });
    return id;
  }
  /**
   * hanl
   * @param minid
   * @param empId
   * @return
   */
  public TranInTail queryinTailInfo(final String minid,final Integer empId) {
    TranInTail tranInTail =new  TranInTail();
    tranInTail = (TranInTail)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from TranInTail tail ";
        Vector parameters = new Vector();
        String criterion = "";
        if (minid != null && !minid.equals("")) {
          criterion += "tail.tranInHead.id=? and ";
          parameters.add(new Integer(minid));
        }
        if (empId != null && !empId.equals("")) {
          criterion += "tail.empId=? and ";
          parameters.add(empId);
        }
        if (criterion.length() != 0)
          criterion = " where  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;

        Query query0 = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query0.setParameter(i, parameters.get(i));
        }
        return query0.uniqueResult();
      }
    });

    return tranInTail;
  }
  
}



