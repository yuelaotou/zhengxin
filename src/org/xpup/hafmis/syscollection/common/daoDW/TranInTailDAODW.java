package org.xpup.hafmis.syscollection.common.daoDW;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninImportDTO;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;

public class TranInTailDAODW extends HibernateDaoSupport{
  /**
   * param tranInHead.id,tranInTailName,tranInTailCardNum
   * @return List
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryTraninListByCriterions(final String id,
      final String tranInTailName, final String tranInTailCardNum) throws NumberFormatException, Exception ,BusinessException{
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "tranInTail.tranInHead.id= ?  and ";
            parameters.add(new Integer(id));
          }
          if (tranInTailName != null && !tranInTailName.equals("")) {
            criterion += "tranInTail.name= ?  and ";
            parameters.add(tranInTailName);
          }
          if (tranInTailCardNum != null && !tranInTailCardNum.equals("")) {
            criterion += "tranInTail.cardNum= ?  and ";
            parameters.add(tranInTailCardNum);
          }
          if (criterion.length() != 0) 
            criterion = " where  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
         
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      });

    } catch (Exception e) {
      throw new BusinessException("单位版查找错误");
    }
    return list;
  }
  /**
   * 查询记录 07.09.21
   * 
   * @param orderBy，order，start，pageSize，page
   * @return list
   */
  public List queryTranInTail_sy(final Integer tranHeadId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select tranInTail.name,tranInTail.cardKind,tranInTail.cardNum,tranInTail.birthday,tranInTail.sex, "
            +" tranInTail.salaryBase,tranInTail.preBalance,tranInTail.curBalance,tranInTail.monthIncome,"
            +" tranInTail.curInterest,tranInTail.tel,tranInTail.mobileTel,tranInTail.orgPay,tranInTail.empPay  from TranInTail tranInTail where tranInTail.tranInHead.id= ?";
          Query query = session.createQuery(hql);
          query.setParameter(0, tranHeadId);
          List queryList = query.list();
          Iterator it = queryList.iterator();
          List t = new ArrayList();
          TraninImportDTO firsttraninImportDTO = new TraninImportDTO();
          t.add(firsttraninImportDTO);
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            TraninImportDTO traninImportDTO = new TraninImportDTO();
            if (obj[0] != null) {
              traninImportDTO.setName(obj[0].toString());
            }
           if (obj[1] != null) {
             traninImportDTO.setCardKind(obj[1].toString());
            }
           if (obj[2] != null) {
             traninImportDTO.setCardNum(obj[2].toString());
           }
           if (obj[3] != null) {
             traninImportDTO.setBirthday(obj[3].toString());
           }
           if (obj[4] != null) {
             traninImportDTO.setSex(obj[4].toString());
           }
           if (obj[5] != null) {
             traninImportDTO.setSalaryBase(obj[5].toString());
           }
           if (obj[6] != null) {
             traninImportDTO.setPreBalance(obj[6].toString());
           }
           if (obj[7] != null) {
             traninImportDTO.setCurBalance(obj[7].toString());
           }
           if (obj[8] != null) {
             traninImportDTO.setMonthIncome(obj[8].toString());
           }
           if (obj[9] != null) {
             traninImportDTO.setCurInterest(obj[9].toString());
           }
           if (obj[10] != null) {
             traninImportDTO.setTel(obj[10].toString());
           }
           if (obj[11] != null) {
             traninImportDTO.setMobileTel(obj[11].toString());
           }
           if (obj[12] != null) {
             traninImportDTO.setOrgPay(obj[12].toString());
           }
           if (obj[13] != null) {
             traninImportDTO.setEmpPay(obj[13].toString());
           }
            t.add(traninImportDTO);
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
