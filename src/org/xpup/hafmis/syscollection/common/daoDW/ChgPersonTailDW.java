package org.xpup.hafmis.syscollection.common.daoDW;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonImpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseListImportDTO;

public class ChgPersonTailDW extends HibernateDaoSupport {
  /**
   * 吴迪 2007-12-17
   * 查询人员变更的导入list
   * @param empid
   * @return
   * @throws Exception
   */
  public List findchgpersonImpContent(final String orgheadID) {
    List list = null;
    try {
      list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select aa205.emp_id,"
                +" aa205.name,"
              +"aa205.card_kind,"
              +"aa205.card_num,"
              +"aa205.birthday,"
              +"aa205.sex,"
              +"aa205.dept,"
              +"aa205.salary_base,"
              +"aa205.chg_type,"
              +"aa205.chgreason,"
              +"aa205.org_pay,"
              +"aa205.emp_pay "
              +" from AA205 aa205"
              +" where aa205.chg_head_id =? ";           
          Query query = session.createSQLQuery(sql);
          query.setString(0,orgheadID);
          List queryList = query.list();
          List t = new ArrayList();
          
          ChgslarybaseListImportDTO chgslarybaseListImportDTOtemp1= new ChgslarybaseListImportDTO();
          t.add(chgslarybaseListImportDTOtemp1);
          ChgslarybaseListImportDTO chgslarybaseListImportDTOtemp2= new ChgslarybaseListImportDTO();
          t.add(chgslarybaseListImportDTOtemp2);
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ChgpersonImpContentDTO chgpersonImpContentDTO = new ChgpersonImpContentDTO();
            if (obj[0] != null && !obj[0].equals(""))
              chgpersonImpContentDTO.setEmpcode(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              chgpersonImpContentDTO.setEmpname(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              chgpersonImpContentDTO.setCardkind(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
              chgpersonImpContentDTO.setCardnum(obj[3].toString());
            if (obj[4] != null && !obj[4].equals(""))
              chgpersonImpContentDTO.setBirthday(obj[4].toString());
            if (obj[5] != null && !obj[5].equals(""))
              chgpersonImpContentDTO.setSex(obj[5].toString());
            if (obj[6] != null && !obj[6].equals(""))
              chgpersonImpContentDTO.setDept(obj[6].toString());
            if (obj[7] != null && !obj[7].equals(""))
              chgpersonImpContentDTO.setSalarybase(obj[7].toString());
            if (obj[8] != null && !obj[8].equals(""))
            {
              if (obj[8].toString().equals("1")) {
                chgpersonImpContentDTO.setChgtype("1");
                chgpersonImpContentDTO.setPartin("0");
              }
              if (obj[8].toString().endsWith("2")) {
                chgpersonImpContentDTO.setChgtype("1");
                chgpersonImpContentDTO.setPartin("1");
              }
              if (obj[8].toString().endsWith("3")) {
                chgpersonImpContentDTO.setChgtype("2");
                chgpersonImpContentDTO.setPartin("0");
              }
              if (obj[8].toString().endsWith("4")) {
                chgpersonImpContentDTO.setChgtype("3");
                chgpersonImpContentDTO.setPartin("1");
              }
            } 
            if (obj[9] != null && !obj[9].equals(""))
              chgpersonImpContentDTO.setChgreason(obj[9].toString());
            if (obj[10] != null && !obj[10].equals(""))
              chgpersonImpContentDTO.setOrgpay(obj[10].toString());
            if (obj[11] != null && !obj[11].equals(""))
              chgpersonImpContentDTO.setEmppay(obj[11].toString());            
            t.add(chgpersonImpContentDTO);
          } 
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 根据orgheadid查询empid
   * @author 吴迪 　20071221　
   * @param chgPersonHeadID
   * @return orgheadid
   */
  public String queryEmpid(final String orgheadid,final String name,final String cardnum) {
    String empid ="";
    empid  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select t.emp_id from AA205 t where t.chg_head_id=? and t.name=? and t.card_num=? " ;
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(orgheadid));
            query.setParameter(1,name);        
            query.setParameter(2,cardnum);
            if(query.uniqueResult()==null){
              return null;
            }else{
              return query.uniqueResult().toString();
            }          
          }
        });
    return empid;
  }
  /**
   * 更新单位库
   * @author 吴迪 　20071221　
   * @param chgPersonHeadID
   * @return orgheadid
   */
  public void updateChgPersonTail() throws BusinessException,
      HibernateException, SQLException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn.prepareCall("{call }");
    
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("更新失败!!!");
    }
  }
}
