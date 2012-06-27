package org.xpup.hafmis.sysloan.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysloan.credit.parameter.form.CreditParamAF;

public class CreditParamDAO extends HibernateDaoSupport {

  public CreditParamAF getCreditParam() {
    CreditParamAF creditParamAF = (CreditParamAF) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

            CreditParamAF temp_creditParamAF = new CreditParamAF();
            String sql = "SELECT SJGSBBH, JRJGDM, SCBWBBH, FSDD, BZ, LXR, LXDH FROM CR001";
            Query query = session.createSQLQuery(sql);

            Object obj[] = (Object[]) query.uniqueResult();
            if (obj != null) {
              if (obj[0] != null) {
                temp_creditParamAF.setSjgsbbh(obj[0].toString().trim());
              }
              if (obj[1] != null) {
                temp_creditParamAF.setJrjgdm(obj[1].toString().trim());
              }
              if (obj[2] != null) {
                temp_creditParamAF.setScbwbbh(obj[2].toString().trim());
              }
              if (obj[3] != null) {
                temp_creditParamAF.setFsdd(obj[3].toString().trim());
              }
              if (obj[4] != null) {
                temp_creditParamAF.setBz(obj[4].toString().trim());
              }
              if (obj[5] != null) {
                temp_creditParamAF.setLxr(obj[5].toString().trim());
              }
              if (obj[6] != null) {
                temp_creditParamAF.setLxdh(obj[6].toString().trim());
              }
            }
            return temp_creditParamAF;
          }
        });
    return creditParamAF;
  }

  public void deleteCreditParam() throws BusinessException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();

      String sql = "DELETE CR001";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("É¾³ýÊý¾ÝÊ§°Ü");
    }
  }

  public void insertCreditParam(final CreditParamAF creditParamAF)
      throws BusinessException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();

      String sql = "INSERT INTO CR001 (ID, SJGSBBH, JRJGDM, SCBWBBH, FSDD, BZ, LXR, LXDH) VALUES "
          + "(1, '"
          + creditParamAF.getSjgsbbh()
          + "', '"
          + creditParamAF.getJrjgdm()
          + "', '"
          + creditParamAF.getScbwbbh()
          + "', '"
          + creditParamAF.getFsdd()
          + "', '"
          + creditParamAF.getBz()
          + "', '"
          + creditParamAF.getLxr()
          + "', '"
          + creditParamAF.getLxdh()
          + "')";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("±£´æÊ§°Ü");
    }
  }
}
