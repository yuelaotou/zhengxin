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

import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseHeadImportDTO;



public class ChgPaymentHeadDAODW extends HibernateDaoSupport {
  /**
   * 吴迪 2007-12-17
   * 查询工资基数调整的导入list
   * @param empid
   * @return
   * @throws Exception
   */
  public List findAddchgslarybaseListImportList(final String orgid) {
    List list = null;
    try {
      list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "  select aa202.org_id, ba001.name, aa202.chg_month"
          +"  from AA202 aa202, BA001 ba001 ,AA001 aa001"
          +"  where  aa202.org_id = aa001.id"
          +"  and aa001.orginfo_id=ba001.id"
           +" and aa202.org_id = ?";
            
          Query query = session.createSQLQuery(sql);
          query.setString(0,orgid);
          List queryList = query.list();
          List t = new ArrayList();
          ChgslarybaseHeadImportDTO chgslarybaseHeadImportDTOtemp= new ChgslarybaseHeadImportDTO();
          t.add(chgslarybaseHeadImportDTOtemp);
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ChgslarybaseHeadImportDTO chgslarybaseHeadImportDTO = new  ChgslarybaseHeadImportDTO();
            if (obj[0] != null && !obj[0].equals(""))
              chgslarybaseHeadImportDTO.setOrgId(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              chgslarybaseHeadImportDTO.setOrgName(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              chgslarybaseHeadImportDTO.setChgMonth(obj[2].toString());           
             t.add(chgslarybaseHeadImportDTO);
          }
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
