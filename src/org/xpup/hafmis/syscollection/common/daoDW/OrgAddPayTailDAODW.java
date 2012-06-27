/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgAddPayTailDAODW
 * @version                 : 1.0
 * @author                  : ww
 * 生成日期                   :2007-12-18
 **/
package org.xpup.hafmis.syscollection.common.daoDW;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayListImportDTO;

/**
 * 单位补缴_中心版_操作单位库
 * 
 * @author ww 2007-12-17
 */
public class OrgAddPayTailDAODW extends HibernateDaoSupport {

  /**
   * Description 单位补缴，办理缴存_提取数据，查询AA303中的记录
   * 
   * @author ww 2007-12-17
   * @param minid 最小的DA001.ORG_HEAD_ID
   * @param 由OrgAddPayBS调用
   * @return list
   */
  public List queryOrgList(final String minorgheadid,final String startPayMonth) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select s.emp_id,s.emp_should_pay,s.org_should_pay,s.emp_real_pay,s.org_real_pay"
            + " from aa302 t, aa303 s where t.pay_head_id = ? and t.pay_month =? "
            + " and t.id = s.month_pay_head_id";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, new Integer(minorgheadid));
        query.setParameter(1, new Integer(startPayMonth));
        Iterator it = query.list().iterator();
        List temp_list = new ArrayList();
        OrgaddpayListImportDTO orgaddpayListImportDTO = new OrgaddpayListImportDTO();
        temp_list.add(orgaddpayListImportDTO);
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            OrgaddpayListImportDTO orgaddpayListImportDTO_1 = new OrgaddpayListImportDTO();
            if (obj[0] != null) {
              orgaddpayListImportDTO_1.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              orgaddpayListImportDTO_1.setEmpShouldpay(obj[1].toString());
            }
            if (obj[2] != null) {
              orgaddpayListImportDTO_1.setOrgShouldpay(obj[2].toString());
            }
            if (obj[3] != null) {
              orgaddpayListImportDTO_1.setEmpAddpayMoney(obj[3].toString());
            }
            if (obj[4] != null) {
              orgaddpayListImportDTO_1.setOrgAddpayMoney(obj[4].toString());
            }
           
            temp_list.add(orgaddpayListImportDTO_1);
          }
        }
        return temp_list;
      }
    });
    return list;
  }
  public List queryOrgMonth(final String minorgheadid) {
    List monthlist = null;
    monthlist = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select month_1.pay_month"
            + " from aa301 head, aa302 month_1 where head.id = ?  "
            + " and head.id = month_1.pay_head_id " 
            + " order by month_1.pay_month asc";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, new Integer(minorgheadid));
        
        return query.list();
      }
  });
    return monthlist;
  }
}