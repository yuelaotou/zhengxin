package org.xpup.hafmis.orgstrct.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.SystemException;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.dto.MailMessageDTO;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.OrgDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.dto.UserAssignBookDTO;
import org.xpup.hafmis.syscollection.accounthandle.dayclear.dto.DayClearDTO;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.UsersloginBook;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 权限实现
 * 
 * @author 刘洋
 */

/**
 * 密码置空
 */

public class SecurityDAO extends HibernateDaoSupport {

  public void setNull(final String id) {
    User user = (User) getHibernateTemplate().get(User.class, id);
    user.setPassword("fce51778429f1266babff0399fe1128e");
  }

  /**
   * 判断用户
   * 
   * @param username
   * @return
   */
  public User queryByUsername(final String username) {
    List users = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List users = session.createCriteria(User.class).add(
                Restrictions.eq("username", username)).list();
            return users;
          }
        });
    if (users.size() == 0) {
      return null;
    } else if (users.size() > 1) {
      throw new SystemException("用户名必须唯一！");
    }
    return (User) users.get(0);
  }

  /**
   * 办事处下的银行查询
   */
  public int findBank(final String code) {
    BigDecimal codeNumber = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String hql = "select count(*) from bb101 a,bb105 b where a.id = b.office and b.status='1'  and b.office=?";
            Query query = session.createSQLQuery(hql);
            query.setString(0, code);
            return (BigDecimal) query.uniqueResult();
          }
        });
    return codeNumber.intValue();
  }
  
  
  /**
   * 查询登录用户权限下的银行日期
   * @param username
   * @return
   */
  public List getCollBankDateList_jj(final String username) {
    List dateList = new ArrayList();

    dateList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = " select aa103.bank_id, bb105.coll_bank_name,aa103.bank_date "+
                         " from CA113 ca113, AA103 aa103,BB105 bb105 "+
                         " where ca113.coll_bank_id = aa103.bank_id and bb105.coll_bank_id=aa103.bank_id "+
                         " and ca113.username = '"+username+"' " ;
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              DayClearDTO dto = new DayClearDTO();
              dto.setCollBankId(obj[0].toString());
              dto.setCollBankName(obj[1].toString());
              dto.setCollBankDate(obj[2].toString());
              Map m = new HashMap();
              m.put("3", "1");
              m.put("4", "2");
              dto.setMap(m);
              list.add(dto);
            }
            return list;
          }
        });

    return dateList;
  }
  
  /**
   * 查询登录用户权限下的银行日期
   * @param username
   * @return
   */
  public List getLoanBankDateList_jj(final String username) {
    List dateList = new ArrayList();

    dateList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = " select pl002.loan_bank_id, bb105.coll_bank_name,pl002.biz_date "+
                         " from CA113 ca113, PL002 pl002,BB105 bb105 "+
                         " where ca113.coll_bank_id = pl002.loan_bank_id and bb105.coll_bank_id=pl002.loan_bank_id "+
                         " and ca113.username = '"+username+"' " ;
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              DayClearDTO dto = new DayClearDTO();
              dto.setCollBankId(obj[0].toString());
              dto.setCollBankName(obj[1].toString());
              dto.setCollBankDate(obj[2].toString());
              list.add(dto);
            }
            return list;
          }
        });

    return dateList;
  }
  
  
  /**
   * 更新银行业务日期
   * @param collBankId
   * @param bizDate
   */
  public void updateCollBankDate_jj(String collBankId, String bizDate) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement statement = conn.createStatement();
      String sql = "update AA103 aa103 set aa103.bank_date = '" + bizDate
          + "' where aa103.bank_id = " + collBankId;
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 更新银行业务日期
   * @param collBankId
   * @param bizDate
   */
  public void updateLoanBankDate_jj(String loanBankId,String bizDate) {
     try {
       Connection conn = getHibernateTemplate().getSessionFactory().openSession().connection();
       Statement statement = conn.createStatement();
       String sql="update PL002 pl002 set pl002.biz_date = '"+bizDate+"' where pl002.loan_bank_id = "+loanBankId;
       statement.executeUpdate(sql);
       } catch (SQLException e) {
         e.printStackTrace();
       }
  }
  /**
   * 得到用户真实姓名
   * 
   * @param username
   * @return
   */
  public String queryByUserid(final String userid) {
    String username = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String hql = "select t.real_name from bb201 t,ca101 c where t.id=c.id and c.username=?";
            Query query = session.createSQLQuery(hql);
            query.setString(0, userid);
            return query.uniqueResult();
          }
        });
    return username;
  }

  /**
   * 权限信息
   * 
   * @return SecurityInfo
   * @throws Exception
   * @throws SQLException
   */
  public SecurityInfo getSecurityInfo(final String username, final String userIp)
      throws Exception {
    SecurityInfo securityInfo = new SecurityInfo();
    insertUserCollBank(username);
    insertUserOrg(username);
    insertUserUser(username);
    insertUserBook(username);
    OfficeDto officeDto = getUserOffice(username);
    List allOfficeList = getAllOfficeList();
    List allCenterList = getAllCenterList();
    List userOfficeList = getUserOfficeList(username);
    List userUserList = getUserUserList(username, null, null);
    List allUserList = getAllUserList();
    List userCollBankList = getUserCollBankList(username);
    List userBookList = getUserBookList(username);
    String gjjSecurityHqlSQL = getGjjSecurityHqlSQL(username);
    String gjjSecuritySQL = getGjjSecuritySQL(username);
    String dkSecurityHqlSQL = getDkSecurityHqlSQL(username);
    String dkSecuritySQL = getDkSecuritySQL(username);
    String dkUserSecurityHqlSQL = getDkUserSecurityHqlSQL(username);
    String dkUserSecuritySQL = getDkUserSecuritySQL(username);
    String officeSecurityHqlSQL = getOfficeSecurityHqlSQL(username);
    String officeSecuritySQL = getOfficeSecuritySQL(username);
    Map officeInnerCodeMap = getOfficeInnerCodeMap(userOfficeList);
    int plLoanReturnType = getPLLoanReturnType();
    int isOrgEdition = getIsOrgEdition();
    int fnSettleType = getFnSettleType();
    List dkUserBankList = getDkUserBankList(username);
    String realName = queryByUserid(username);
    int agentPayModel = getAgentPayModel();
    int loanBackByCollModel = getLoanBackByCollModel();
    int isHaveOrgVersion = isHaveOrgVersion();
    int mailfunction = mailFunction();
    MailMessageDTO maildto = getMailMessage();
    securityInfo.setAllOfficeList(allOfficeList);
    securityInfo.setAllCenterList(allCenterList);
    securityInfo.setOfficeList(userOfficeList);
    securityInfo.setCollBankList(userCollBankList);
    securityInfo.setOfficeDto(officeDto);
    securityInfo.setUserName(username);
    securityInfo.setUserIp(userIp);
    securityInfo.setUserList(userUserList);
    securityInfo.setAllUserList(allUserList);
    securityInfo.setGjjSecurityHqlSQL(gjjSecurityHqlSQL);
    securityInfo.setGjjSecuritySQL(gjjSecuritySQL);
    securityInfo.setDkSecurityHqlSQL(dkSecurityHqlSQL);
    securityInfo.setDkSecuritySQL(dkSecuritySQL);
    securityInfo.setOfficeInnerCodeMap(officeInnerCodeMap);
    HafEmployee userInfo = getUserInfo(username, userIp);
    securityInfo.setUserInfo(userInfo);
    securityInfo.setPlLoanReturnType(plLoanReturnType);
    securityInfo.setIsOrgEdition(isOrgEdition);
    securityInfo.setFnSettleType(fnSettleType);
    securityInfo.setDkUserSecurityHqlSQL(dkUserSecurityHqlSQL);
    securityInfo.setDkUserSecuritySQL(dkUserSecuritySQL);
    securityInfo.setDkUserBankList(dkUserBankList);
    securityInfo.setOfficeSecurityHqlSQL(officeSecurityHqlSQL);
    securityInfo.setOfficeSecuritySQL(officeSecuritySQL);
    securityInfo.setUserBookList(userBookList);
    securityInfo.setRealName(realName);
    securityInfo.setAgentPayModel(agentPayModel);
    securityInfo.setLoanBackByCollModel(loanBackByCollModel);
    securityInfo.setIsHaveOrgVersion(isHaveOrgVersion);
    securityInfo.setMailFunction(mailfunction);
    securityInfo.setMaildto(maildto);
    return securityInfo;

  }

  /**
   * 得到登录人的办事处
   * 
   * @param username
   * @return
   */
  public OfficeDto getUserOffice(final String username) {
    OfficeDto returnOfficeDto = (OfficeDto) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            OfficeDto officeDto = new OfficeDto();
            String hql = "select  organizationUnit.id,organizationUnit.name from OrganizationUnit organizationUnit,HafEmployee hafEmployee,User user where organizationUnit.type='2' and user.id=hafEmployee.id and hafEmployee.officeId=organizationUnit.id and user.username=?";
            Query query = session.createQuery(hql);
            query.setString(0, username);
            Iterator it = query.iterate();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                officeDto.setOfficeCode(obj[0].toString());
                officeDto.setOfficeName(obj[1].toString());
              }
            }
            return officeDto;
          }
        });
    return returnOfficeDto;
  }

  /**
   * 得到所有center
   * 
   * @return center
   */
  public List getAllCenterList() {
    List officeList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List list = new ArrayList();
            String hql = "select  organizationUnit.id,organizationUnit.name from OrganizationUnit organizationUnit where organizationUnit.type='1' ";
            Query query = session.createQuery(hql);
            Iterator it = query.iterate();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setOfficeCode(obj[0].toString());
                officeDto.setOfficeName(obj[1].toString());
                list.add(officeDto);
              }
            }
            return list;
          }
        });
    return officeList;
  }

  /**
   * 得到所有office
   * 
   * @return officeList
   */
  public List getAllOfficeList() {
    List officeList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List list = new ArrayList();
            String hql = "select  organizationUnit.id,organizationUnit.name from OrganizationUnit organizationUnit where organizationUnit.type='2' ";
            Query query = session.createQuery(hql);
            Iterator it = query.iterate();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setOfficeCode(obj[0].toString());
                officeDto.setOfficeName(obj[1].toString());
                list.add(officeDto);
              }
            }
            return list;
          }
        });
    return officeList;
  }

  /**
   * 得到的登录人管理的办事处
   * 
   * @return userOfficeList
   */
  public List getUserOfficeList(final String username) {
    List userOfficeList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List list = new ArrayList();
            String hql = " select distinct b.id,b.name from bb108 a, bb101 b where b.ou_type=2 and a.office = b.id and a.username ='"
                + username
                + "'"
                + " union "
                + " select b.id, b.name from bb101 b, bb110 c, ca101 d, ca103 e where b.ou_type=2 and b.id=c.office "
                + " and c.roleid=e.role_id and e.user_id=d.id and d.username='"
                + username
                + "'"
                + " union "
                + " select b.id, b.name from bb108 a, bb101 b, bb112 f where b.ou_type=2 and a.office = b.id "
                + " and a.username = f.subusername and f.username ='"
                + username + "'";

            Query query = session.createSQLQuery(hql);

            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setOfficeCode(obj[0].toString());
                officeDto.setOfficeName(obj[1].toString());
                list.add(officeDto);
              }
            }
            return list;
          }
        });
    return userOfficeList;
  }

  /**
   * 得到用户权限用户列表
   * 
   * @param username
   * @return
   */
  public List getUserUserList(final String username, final String office,
      final String dept) {
    List userUserList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List returnList = new ArrayList();
            String hql = "";
            if ((username != null && (!username.equals("")))
                && (office != null && (!office.equals("")))
                && (dept != null && (!dept.equals("")))) {
              // hql="select user from User user ,RelaUserAndUser
              // relaUserAndUser,OrganizationUnit organizationUnit,HafEmployee
              // hafEmployee where user.username=relaUserAndUser.subusername and
              // relaUserAndUser.username='"+username+"' and ";
            } else if ((username != null && (!username.equals("")))
                && (office != null && (!office.equals("")))) {
              // hql="select user from User user ,RelaUserAndUser
              // relaUserAndUser where user.username=relaUserAndUser.subusername
              // and relaUserAndUser.username='"+username+"'";
            } else if ((username != null && (!username.equals("")))) {
              hql = "select user from User user ,RelaUserAndUser relaUserAndUser where user.username=relaUserAndUser.subusername and relaUserAndUser.username='"
                  + username + "'";
            }
            Query query = session.createQuery(hql);
            List returnTempList = query.list();
            String hql1 = "select user from User user  where user.username='"
                + username + "'";
            Query query1 = session.createQuery(hql1);
            User user = (User) query1.uniqueResult();
            returnList.add(user);
            for (int i = 0; i < returnTempList.size(); i++) {
              User usertemp = (User) returnTempList.get(i);
              returnList.add(usertemp);
            }
            return returnList;
          }
        });
    return userUserList;
  }

  /**
   * 得到用户列表
   * 
   * @return
   */
  public List getUserList(final String username, final int start, final int end) {
    List allUserList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          /*
           * (non-Javadoc)
           * 
           * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
           */
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List parameters = new ArrayList();
            String str = "";
            String hql = " from User user ";
            if (username != null && !username.equals("")) {
              str += " user.username=? and ";
              parameters.add(username);
            }
            if (str.length() != 0)
              str = " where " + str.substring(0, str.lastIndexOf("and"));
            hql = hql + str;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.setFirstResult(start).setMaxResults(end).list();
          }
        });
    return allUserList;
  }

  /**
   * 得到用户列表
   * 
   * @return
   */
  public List getUserListALL(final String username) {
    List allUserList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          /*
           * (non-Javadoc)
           * 
           * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
           */
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List parameters = new ArrayList();
            String str = "";
            String hql = " from User user ";
            if (username != null && !username.equals("")) {
              str += " user.username=?  and";
              parameters.add(username);
            }
            if (str.length() != 0)
              str = " where " + str.substring(0, str.lastIndexOf("and"));
            hql = hql + str;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    return allUserList;
  }

  /**
   * 得到所有用户列表
   * 
   * @return
   */
  public List getAllUserList() {
    List allUserList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String hql = " from User user ";
            Query query = session.createQuery(hql);
            return query.list();
          }
        });
    return allUserList;
  }

  /**
   * 得到用户单位列表
   * 
   * @return
   */
  public List getUserOrgList(final String username) {
    List allUserList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String hql = " select distinct org from Org org, RelaRoleAndOrg relaRoleAndOrg, "
                + " User user, Role role,RelaUserAndOrg relaUserAndOrg, "
                + " RelaUserAndUser relaUserAndUser "
                + " where  "
                + " (org.id=relaRoleAndOrg.orgId and relaRoleAndOrg.role=role.id and user.id=role.users.id  "
                + " and user.username ='"
                + username
                + "') "
                + " or (org.id=relaUserAndOrg.orgId and relaUserAndOrg.username='"
                + username
                + "') or (org.id=relaUserAndOrg.orgId and relaUserAndUser.subusername=relaUserAndOrg.username "
                + "  and relaUserAndUser.username='" + username + "') ";

            Query query = session.createQuery(hql);
            return query.list();
          }
        });
    return allUserList;
  }

  /**
   * 取得权限HqlSql控制到办事处,只考虑到了登录用户的办事处
   * 
   * @param username
   * @return
   */
  public String getOfficeSecurityHqlSQL(final String username) {
    String officeSecurityHqlSQL = "";
    officeSecurityHqlSQL =

    "in (select distinct relaUserAndOffice.office from RelaUserAndOffice relaUserAndOffice where relaUserAndOffice.username='"
        + username + "')";

    return officeSecurityHqlSQL;
  }

  /**
   * 取得权限HqlSql控制到办事处,只考虑到了登录用户的办事处
   * 
   * @param username
   * @return
   */
  public String getOfficeSecuritySQL(final String username) {
    String officeSecuritySQL = "";
    officeSecuritySQL =

    "in (select distinct t.office from bb108 t where t.username='" + username
        + "')";

    return officeSecuritySQL;
  }

  /**
   * 取得公积金权限HqlSql控制到单位
   * 
   * @param username
   * @return
   */
  public String getGjjSecurityHqlSQL(final String username) {
    String gjjSecurityHqlSQL = "";
    gjjSecurityHqlSQL =
    // " in (select distinct org.id from Org org, RelaRoleAndOrg relaRoleAndOrg,
    // " +
    // " User user, Role role,RelaUserAndOrg relaUserAndOrg, " +
    // " RelaUserAndUser relaUserAndUser "+
    // " where " +
    // " (org.id=relaRoleAndOrg.orgId and relaRoleAndOrg.role=role.id and
    // user.id=role.users.id " +
    // " and user.username ='"+username+"') "+
    // " or (org.id=relaUserAndOrg.orgId and
    // relaUserAndOrg.username='"+username+
    // "') or (org.id=relaUserAndOrg.orgId and
    // relaUserAndUser.subusername=relaUserAndOrg.username " +
    // " and relaUserAndUser.username='"+username+"') )";
    "in (select distinct usersloginorg.orgId from Usersloginorg usersloginorg where usersloginorg.username='"
        + username + "')";

    return gjjSecurityHqlSQL;
  }

  /**
   * 取得公积金权限Sql控制到单位
   * 
   * @param username
   * @return
   */
  public String getGjjSecuritySQL(final String username) {
    String gjjSecuritySQL = "";
    gjjSecuritySQL = "in (select distinct ca114.org_id from CA114 ca114 where ca114.username='"
        + username + "')";
    // " select distinct a.id from aa001 a, BB107 b, ca101 c, ca103 d where
    // a.id=b.org_id and b.role=d.role_id and c.id=d.user_id and c.username
    // ='"+username+"'"+
    // " union "+
    // " select a.id from aa001 a,BB106 e where a.id=e.org_id and
    // e.username='"+username+"'"+
    // " union "+
    // " select a.id from aa001 a, BB106 e, bb112 f where a.id = e.org_id and
    // f.subusername = e.username and f.username = '"+username+"'";

    return gjjSecuritySQL;
  }

  /**
   * 取得贷款权限HqlSql控制到银行
   * 
   * @param username
   * @return
   */
  public String getDkSecurityHqlSQL(final String username) {
    String dkSecurityHqlSQL = "";
    dkSecurityHqlSQL =

    "in (select distinct userslogincollbank.collBankId from Userslogincollbank userslogincollbank,LoanBank loanBank where loanBank.loanBankId=userslogincollbank.collBankId and loanBank.loanBnakSt = 1 and userslogincollbank.username='"
        + username + "')";

    return dkSecurityHqlSQL;
  }

  /**
   * 取得贷款权限Sql控制到银行
   * 
   * @param username
   * @return
   */
  public String getDkSecuritySQL(final String username) {
    String dkSecuritySQL = "";
    dkSecuritySQL = "in (select distinct ca113.coll_bank_id from CA113 ca113,PL002 pl002 where pl002.loan_bnak_st=1 and pl002.loan_bank_id=coll_bank_id and ca113.username='"
        + username + "')";
    return dkSecuritySQL;
  }

  /**
   * 取得贷款权限HqlSql控制到操作员
   * 
   * @param username
   * @return
   */
  public String getDkUserSecurityHqlSQL(final String username) {
    String dkUserSecurityHqlSQL = "";
    dkUserSecurityHqlSQL =

    "in (select distinct usersloginuser.subUsername from Usersloginuser usersloginuser where usersloginuser.username='"
        + username + "')";

    return dkUserSecurityHqlSQL;
  }

  /**
   * 取得贷款权限Sql控制到操作员
   * 
   * @param username
   * @return
   */
  public String getDkUserSecuritySQL(final String username) {
    String dkUserSecuritySQL = "";
    dkUserSecuritySQL =

    "in (select distinct ca115.subusername from CA115 ca115 where ca115.username='"
        + username + "')";

    return dkUserSecuritySQL;
  }

  /**
   * 得到登录用户信息
   * 
   * @param username
   * @return
   */
  public HafEmployee getUserInfo(final String username, final String userIp) {
    HafEmployee hafEmployee = (HafEmployee) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String hql = "select  hafEmployee from HafEmployee hafEmployee,User user where user.username='"
                + username + "' and user.id=hafEmployee.id ";
            Query query = session.createQuery(hql);
            return query.uniqueResult();
          }
        });
    hafEmployee.setUserIp(userIp);
    return hafEmployee;
  }

  /**
   * 根据登录用户查询归集行
   * 
   * @param username
   * @return
   */
  public List getUserCollBankList(final String username) {
    List banklist = null;
    banklist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {

            String hql = "select distinct userslogincollbank from Userslogincollbank userslogincollbank where userslogincollbank.username=? ";
            // String hql =
            // " select distinct collBank from CollBank collBank ,
            // RelaRoleAndCollBank relaRoleAndCollBank, " +
            // " User user, Role role,RelaUserAndCollBank relaUserAndCollBank, "
            // +
            // " RelaUserAndUser relaUserAndUser "+
            // " where " +
            // " (collBank.status=1 and
            // collBank.collBankId=relaRoleAndCollBank.collbankid and
            // relaRoleAndCollBank.roleid=role.id and user.id=role.users.id " +
            // " and user.username ='"+username+"') "+
            // " or (collBank.status=1 and
            // collBank.collBankId=relaUserAndCollBank.collBankId and
            // relaUserAndCollBank.username='"+username+
            // "') or (collBank.status=1 and
            // collBank.collBankId=relaUserAndCollBank.collBankId and
            // relaUserAndUser.subusername=relaUserAndCollBank.username " +
            // " and relaUserAndUser.username='"+username+"')";

            Query query = session.createQuery(hql);
            query.setString(0, username);
            return query.list();
          }
        });
    return banklist;
  }

  /*
   * 取得公积金权限单位ID单位nameSql @param username @return
   */
  public String getOrgSecuritySQL(final String username) {
    String orgSecuritySQL = "";
    orgSecuritySQL =
    // "select a.id as orgid ,e.name as orgname,e.OFFICECODE as offid,g.name as
    // offname,e.COLLECTION_BANK_ID as bankid,f.coll_bank_name as bankname" +
    // " from aa001 a, BB107 b, ca101 c, ca103 d,ba001 e,bb105 f,bb101 g" +
    // " where a.id=b.org_id and b.role=d.role_id and c.id=d.user_id and e.id =
    // a.orginfo_id " +
    // " and e.COLLECTION_BANK_ID=f.coll_bank_id and g.id=e.OFFICECODE " +
    // " and c.username ='"+username+"'" +
    // " union " +
    " select a.id as orgid ,c.name  as orgname,c.OFFICECODE as offid,g.name as offname,c.COLLECTION_BANK_ID as bankid,b.coll_bank_name as bankname "
        + " from  aa001 a,bb105 b,ba001 c, BB106 d,bb101 g"
        + " where b.status=1 and a.id=d.org_id and b.COLL_BANK_ID = d.COLL_BANK_ID and c.id=a.orginfo_id "
        + " and c.COLLECTION_BANK_ID=b.coll_bank_id and g.id=c.OFFICECODE "
        + " and d.username='" + username + "' order by offid,bankid";
    // +" union" +
    // " select a.id as orgid ,c.name as orgname ,c.OFFICECODE as offid,g.name
    // as offname,c.COLLECTION_BANK_ID as bankid,f.coll_bank_name as bankname "
    // +
    // " from aa001 a, BB107 b,ba001 c,BB106 d, bb112 e ,bb105 f,bb101 g" +
    // " where a.id = b.org_id and e.subusername=d.username and
    // c.id=a.orginfo_id and d.COLL_BANK_ID=f.coll_bank_id" +
    // " and c.COLLECTION_BANK_ID=f.coll_bank_id and g.id=c.OFFICECODE " +
    // " and e.username='"+username+"' order by offid";

    return orgSecuritySQL;
  }

  public String getSpareOrgSecuritySQL(final String username, final String users) {
    String orgSecuritySQL = "";
    if (this.isCenterMng(users)) {
      orgSecuritySQL = " select a.id as orgid,b.name as orgname,b.officecode as offid,c.name as offname,b.collection_bank_id as bankid,d.coll_bank_name as bankname "
          + "  from aa001 a,ba001 b,bb101 c , bb105 d "
          + "  where d.status=1 and a.orginfo_id=b.id and c.id=b.officecode and b.collection_bank_id=d.coll_bank_id "
          + "  minus "
          + " select a.id as orgid ,c.name  as orgname,c.OFFICECODE as offid,g.name as offname,c.COLLECTION_BANK_ID as bankid,b.coll_bank_name as bankname "
          + " from  aa001 a,bb105 b,ba001 c, BB106 d,bb101 g"
          + " where b.status=1 and a.id=d.org_id and b.COLL_BANK_ID = d.COLL_BANK_ID and c.id=a.orginfo_id "
          + " and c.COLLECTION_BANK_ID=b.coll_bank_id and g.id=c.OFFICECODE "
          + " and d.username='" + username + "' order by offid,bankid ";
    } else {
      orgSecuritySQL = " select a.id as orgid ,c.name  as orgname,c.OFFICECODE as offid,g.name as offname,c.COLLECTION_BANK_ID as bankid,b.coll_bank_name as bankname "
          + " from  aa001 a,bb105 b,ba001 c, BB106 d,bb101 g"
          + " where b.status=1 and a.id=d.org_id and b.COLL_BANK_ID = d.COLL_BANK_ID and c.id=a.orginfo_id "
          + " and c.COLLECTION_BANK_ID=b.coll_bank_id and g.id=c.OFFICECODE "
          + " and d.username='"
          + users
          + "'"
          + " minus "
          +
          // " ( select a.id as orgid ,e.name as orgname,e.OFFICECODE as
          // offid,g.name as offname,e.COLLECTION_BANK_ID as
          // bankid,f.coll_bank_name as bankname " +
          // " from aa001 a, BB107 b, ca101 c, ca103 d,ba001 e,bb105 f,bb101 g"
          // +
          // " where a.id=b.org_id and b.role=d.role_id and c.id=d.user_id and
          // e.id = a.orginfo_id " +
          // " and e.COLLECTION_BANK_ID=f.coll_bank_id and g.id=e.OFFICECODE " +
          // " and c.username ='"+username+"'" +
          // " union " +
          " select a.id as orgid ,c.name  as orgname,c.OFFICECODE as offid,g.name as offname,c.COLLECTION_BANK_ID as bankid,b.coll_bank_name as bankname "
          + " from  aa001 a,bb105 b,ba001 c, BB106 d,bb101 g"
          + " where b.status=1 and a.id=d.org_id and b.COLL_BANK_ID = d.COLL_BANK_ID and c.id=a.orginfo_id "
          + " and c.COLLECTION_BANK_ID=b.coll_bank_id and g.id=c.OFFICECODE "
          + " and d.username='" + username + "' order by offid,bankid ";
      // +" union" +
      // " select a.id as orgid ,c.name as orgname ,c.OFFICECODE as offid,g.name
      // as offname,c.COLLECTION_BANK_ID as bankid,f.coll_bank_name as bankname
      // " +
      // " from aa001 a, BB107 b,ba001 c,BB106 d, bb112 e ,bb105 f,bb101 g" +
      // " where a.id = b.org_id and e.subusername=d.username and
      // c.id=a.orginfo_id and d.COLL_BANK_ID=f.coll_bank_id" +
      // " and c.COLLECTION_BANK_ID=f.coll_bank_id and g.id=c.OFFICECODE " +
      // " and e.username='"+username+"' )order by offid ";
    }
    return orgSecuritySQL;
  }

  public List getUserOrgInfoList(final String username) {
    List banklist = null;
    banklist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = getOrgSecuritySQL(username);
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OrgDto orgDto = new OrgDto();
                orgDto.setOrgid(obj[0].toString());
                orgDto.setOrgname(obj[1].toString());
                orgDto.setOfficeid(obj[2].toString());
                orgDto.setOfficename(obj[3].toString());
                orgDto.setBankid(obj[4].toString());
                orgDto.setBankname(obj[5].toString());
                list.add(orgDto);
              }
            }
            return list;
          }
        });
    return banklist;
  }

  public List getUserSpareOrgInfoList(final String username, final String users) {
    List banklist = null;
    banklist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = getSpareOrgSecuritySQL(username, users);
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OrgDto orgDto = new OrgDto();
                orgDto.setOrgid(obj[0].toString());
                orgDto.setOrgname(obj[1].toString());
                orgDto.setOfficeid(obj[2].toString());
                orgDto.setOfficename(obj[3].toString());
                orgDto.setBankid(obj[4].toString());
                orgDto.setBankname(obj[5].toString());
                list.add(orgDto);
              }
            }
            return list;
          }
        });
    return banklist;
  }

  // public String getBankListSecuritySQL(final String username,final String
  // officecode){
  // String bankSecuritySQL="";
  // bankSecuritySQL="select a.id as orgid ,e.name as orgname,e.OFFICECODE as
  // offid,g.name as offname,e.COLLECTION_BANK_ID as bankid,f.coll_bank_name as
  // bankname "+
  // " from aa001 a, BB107 b, ca101 c, ca103 d,ba001 e,bb105 f,bb101 g " +
  // " where a.id=b.org_id and b.role=d.role_id and c.id=d.user_id and e.id =
  // a.orginfo_id " +
  // " and e.COLLECTION_BANK_ID=f.coll_bank_id and g.id=e.OFFICECODE " +
  // " and c.username ='"+username+"' and g.id= " +officecode+
  // " union " +
  // " select a.id as orgid ,c.name as orgname,c.OFFICECODE as offid,g.name as
  // offname,c.COLLECTION_BANK_ID as bankid,f.coll_bank_name as bankname " +
  // " from aa001 a,bb105 b,ba001 c, BB106 d,bb105 f,bb101 g " +
  // " where a.id=d.org_id and b.COLL_BANK_ID = d.COLL_BANK_ID and
  // c.id=a.orginfo_id" +
  // " and c.COLLECTION_BANK_ID=f.coll_bank_id and g.id=c.OFFICECODE " +
  // " and d.username='"+username+"' and g.id= " +officecode+
  // " union " +
  // " select a.id as orgid ,c.name as orgname ,c.OFFICECODE as offid,g.name as
  // offname,c.COLLECTION_BANK_ID as bankid,f.coll_bank_name as bankname " +
  // " from aa001 a, BB107 b,ba001 c,BB106 d, bb112 e ,bb105 f,bb101 g " +
  // " where a.id = b.org_id and e.subusername=d.username and c.id=a.orginfo_id
  // and d.COLL_BANK_ID=f.coll_bank_id " +
  // " and c.COLLECTION_BANK_ID=f.coll_bank_id and g.id=c.OFFICECODE " +
  // " and e.username='"+username+"' and g.id="+officecode+" order by offid ";
  // return bankSecuritySQL;
  // }
  // public List getBankListByUserOff(final String username,final String
  // officecode){
  // List banklist=null;
  // banklist = (List) getHibernateTemplate().executeFind(
  // new HibernateCallback() {
  // public Object doInHibernate(Session session) throws SQLException,
  // HibernateException {
  // String sql = getBankListSecuritySQL(username,officecode);
  // Query query=session.createSQLQuery(sql);
  // List list=new ArrayList();
  // Iterator it=query.list().iterator();
  // Object obj[]=null;
  // while(it.hasNext()){
  // obj=(Object[])it.next();
  // if(obj[0]!=null){
  // OrgDto orgDto=new OrgDto();
  // orgDto.setOrgid(obj[0].toString());
  // orgDto.setOrgname(obj[1].toString());
  // orgDto.setOfficeid(obj[2].toString());
  // orgDto.setOfficename(obj[3].toString());
  // orgDto.setBankid(obj[4].toString());
  // orgDto.setBankname(obj[5].toString());
  // list.add(orgDto);
  // }
  // }
  // session.flush();
  // return list;
  // }
  // });
  // return banklist;
  // }

  /**
   * 是否是中心级人员
   * 
   * @param username
   * @return
   */
  public boolean isCenterMng(final String username) {
    boolean flag = true;
    HafEmployee hafEmployee = this.getUserInfo(username, "192.168.1.1");
    if (hafEmployee.getOfficeId() != null) {
      flag = false;
    }
    return flag;
  }

  /**
   * 得到用户的办事处 jj
   * 
   * @return userOfficeList
   */
  public List getUserOfficeLists(final String username) {
    List userOfficeList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List list = new ArrayList();
            String hql = " select b.id,b.name from bb108 a, bb101 b where a.office = b.id and b.ou_type='2' and a.username ='"
                + username + "'";

            Query query = session.createSQLQuery(hql);

            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setOfficeCode(obj[0].toString());
                officeDto.setOfficeName(obj[1].toString());
                list.add(officeDto);
              }
            }
            return list;
          }
        });
    return userOfficeList;
  }

  /**
   * 用户尚未分配的办事处
   * 
   * @param username
   * @param users
   * @return
   */
  public String getSpareOfficeSecuritySQL(final String username,
      final String users) {
    String orgSecuritySQL = "";
    if (this.isCenterMng(users)) {
      orgSecuritySQL = " select a.id,a.name from bb101 a where a.ou_type='2'"
          + " minus "
          + " select b.id,b.name from bb108 a, bb101 b where b.ou_type='2' and a.office = b.id and a.username ='"
          + username + "'";
    } else {
      orgSecuritySQL = " select b.id,b.name from bb108 a, bb101 b where b.ou_type='2' and a.office = b.id and a.username ='"
          + users
          + "'"
          + " minus "
          + " select b.id,b.name from bb108 a, bb101 b where b.ou_type='2' and a.office = b.id and a.username ='"
          + username + "'";
    }
    return orgSecuritySQL;
  }

  /**
   * 用户尚未分配的办事处 jj
   * 
   * @param username
   * @return
   */
  public List getSpareUserOfficeList(final String username, final String users) {
    List userOfficeList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List list = new ArrayList();
            String hql = getSpareOfficeSecuritySQL(username, users);

            Query query = session.createSQLQuery(hql);

            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setOfficeCode(obj[0].toString());
                officeDto.setOfficeName(obj[1].toString());
                list.add(officeDto);
              }
            }
            return list;
          }
        });
    return userOfficeList;
  }

  /**
   * 用户尚未分配的银行
   * 
   * @param username
   * @param users
   * @return
   */
  public String getSpareBankSecuritySQL(final String username,
      final String users) {
    String orgSecuritySQL = "";
    if (this.isCenterMng(users)) {
      orgSecuritySQL = " select b.coll_bank_id as bankid,b.coll_bank_name as bankname, a.id as officeid,a.name as officename "
          + " from bb101 a,bb105 b "
          + " where b.status=1 and a.id=b.office and a.ou_type=2"
          + " minus "
          + " select c.coll_bank_id  as bankid,c.coll_bank_name as bankname, b.id as officeid,b.name  as officename  "
          + " from  bb101 b,bb105 c,bb109 d "
          + " where c.status=1 and b.ou_type=2 and c.office=b.id and d.coll_bank_id=c.coll_bank_id  and d.username ='"
          + username + "' order by officeid ";
    } else {
      orgSecuritySQL = " select c.coll_bank_id  as bankid,c.coll_bank_name as bankname, b.id as officeid,b.name  as officename  "
          + " from  bb101 b,bb105 c,bb109 d "
          + " where c.status=1 and b.ou_type=2 and c.office=b.id and d.coll_bank_id=c.coll_bank_id  and d.username ='"
          + users
          + "' "
          + " minus "
          + " select c.coll_bank_id  as bankid,c.coll_bank_name as bankname, b.id as officeid,b.name  as officename  "
          + " from  bb101 b,bb105 c,bb109 d "
          + " where c.status=1 and b.ou_type=2 and c.office=b.id and d.coll_bank_id=c.coll_bank_id  and d.username ='"
          + username + "' order by officeid";
    }
    return orgSecuritySQL;
  }

  /**
   * 用户已分配的银行
   * 
   * @param username
   * @param users
   * @return
   */
  public String getUserBankSecuritySQL(final String username) {
    String bankSecuritySQL = "";
    bankSecuritySQL = " select c.coll_bank_id  as bankid,c.coll_bank_name as bankname, b.id as officeid,b.name  as officename  "
        + " from  bb101 b,bb105 c,bb109 d "
        + " where c.status=1 and b.ou_type=2 and c.office=b.id and d.coll_bank_id=c.coll_bank_id  and d.username ='"
        + username + "' order by officeid";

    return bankSecuritySQL;
  }

  /**
   * 用户已分配的银行
   * 
   * @param username
   * @param users
   * @return
   */
  public List getBankListByUser(final String username) {
    List banklist = null;
    banklist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = getUserBankSecuritySQL(username);
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OrgDto orgDto = new OrgDto();
                orgDto.setBankid(obj[0].toString());
                orgDto.setBankname(obj[1].toString());
                orgDto.setOfficeid(obj[2].toString());
                orgDto.setOfficename(obj[3].toString());
                list.add(orgDto);
              }
            }
            return list;
          }
        });
    return banklist;
  }

  /**
   * 用户尚未分配的银行
   * 
   * @param username
   * @param users
   * @return
   */
  public List getSpareBankListByUser(final String username, final String users) {
    List banklist = null;
    banklist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = getSpareBankSecuritySQL(username, users);
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                OrgDto orgDto = new OrgDto();
                orgDto.setBankid(obj[0].toString());
                orgDto.setBankname(obj[1].toString());
                orgDto.setOfficeid(obj[2].toString());
                orgDto.setOfficename(obj[3].toString());
                list.add(orgDto);
              }
            }
            return list;
          }
        });
    return banklist;
  }

  /**
   * 用户已分配的用户
   * 
   * @param username
   * @return
   */
  public List getUserListByUser(final String username) {
    List userlist = null;
    userlist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = " select a.subusername from bb112 a where a.username='"
                + username + "'";
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                OrgDto orgDto = new OrgDto();
                orgDto.setUsername(obj.toString());
                list.add(orgDto);
              }
            }
            return list;
          }
        });
    return userlist;
  }

  /**
   * 登录时修改会计日期
   * 
   * @param username
   * @param opSystemType
   * @param bizDate
   * @throws BusinessException
   */
  public void updateBizDate(final String username, final String opSystemType,
      final String bizDate) throws BusinessException {
    try {
      HafEmployee hafEmployee = this.getUserInfo(username, "192.168.1.1");
      if (opSystemType.equals(Integer
          .toString((BusiLogConst.OP_SYSTEM_TYPE_COLLECTION)))) {
        hafEmployee.setBizDate(bizDate);
      } else if (opSystemType.equals(Integer
          .toString((BusiLogConst.OP_SYSTEM_TYPE_LOAN)))) {
        hafEmployee.setPlbizDate(bizDate);
      } /*else if (opSystemType.equals(Integer
          .toString((BusiLogConst.OP_SYSTEM_TYPE_FINANCE)))) {
        hafEmployee.setFbizDate(bizDate);
      }*/
    } catch (Exception e) {
      throw new BusinessException("会计日期修改失败！！！");
    }
  }

  /**
   * 用户尚未分配的用户
   * 
   * @param username
   * @param users
   * @return
   */
  public String getSpareUsersSecuritySQL(final String username,
      final String users) {
    String orgSecuritySQL = "";
    if (this.isCenterMng(users)) {
      orgSecuritySQL = " select a.username from ca101 a where a.username !='"
          + username + "'" + " minus "
          + " select a.subusername from bb112 a where a.username='" + username
          + "'";
    } else {
      orgSecuritySQL = "select a.subusername from bb112 a where a.username='"
          + users + "' and a.subusername !='" + username + "' " + " minus "
          + " select a.subusername from bb112 a where a.username='" + username
          + "'";
    }
    return orgSecuritySQL;
  }

  /**
   * 用户尚未分配的用户
   * 
   * @param username
   * @param users
   * @return
   */
  public List getSpareUserListByUser(final String username, final String users) {
    List userlist = null;
    userlist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = getSpareUsersSecuritySQL(username, users);
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                OrgDto orgDto = new OrgDto();
                orgDto.setUsername(obj.toString());
                list.add(orgDto);
              }
            }
            return list;
          }
        });
    return userlist;
  }

  public void insertUserCollBank(final String username) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call UsersLoginCollPRE(?)}");
      cs.setString(1, username);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void insertUserOrg(final String username) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call UsersLoginOrgPRE(?)}");
      cs.setString(1, username);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void insertUserUser(final String username) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call UsersLoginUserPRE(?)}");
      cs.setString(1, username);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void insertUserBook(final String username) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call UsersLoginBookPRE(?)}");
      cs.setString(1, username);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * 得到中心编号
   * 
   * @param officeList
   * @return
   */
  public Map getOfficeInnerCodeMap(List officeList) {
    final String sql = "select c.value from bb104 a,bb103 b,bb102 c where a.template_id=b.id and a.inner_name='OFFICE_INNER_CODE'  and c.oup_item_id=a.id and  c.org_unit_id=? ";
    Map officeInnerCodeMap = new HashMap();
    for (int i = 0; i < officeList.size(); i++) {
      final String officeCode = ((OfficeDto) officeList.get(i)).getOfficeCode();
      String value = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws SQLException,
                HibernateException {
              String tempValue = "";
              Query query = session.createSQLQuery(sql);
              query.setString(0, officeCode);
              Iterator it = query.list().iterator();
              Object obj = null;
              while (it.hasNext()) {
                obj = (Object) it.next();
                if (obj != null) {
                  tempValue = obj.toString();
                }
              }
              return tempValue;
            }
          });
      officeInnerCodeMap.put(officeCode, value);
    }
    return officeInnerCodeMap;
  }

  /**
   * 得到还款类型:以中心为主,以银行为主
   * 
   * @return
   * @throws Exception
   */
  public int getPLLoanReturnType() throws Exception {
    int plLoanReturnType = 999;
    final String sql = "select c.value from bb104 a, bb103 b, bb102 c,bb101 d where a.template_id = b.id and a.inner_name = 'LOAN_RETURN_TYPE'"
        + " and c.oup_item_id = a.id and c.org_unit_id=d.id and d.ou_type=1 ";
    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Integer tempValue = new Integer(999);
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                tempValue = new Integer(obj.toString());
              }
            }
            return tempValue;
          }
        });
    if (value != null) {
      plLoanReturnType = value.intValue();
    }
    return plLoanReturnType;
  }

  /**
   * 得到类型:单位版,中心版
   * 
   * @return
   * @throws Exception
   */
  public int getIsOrgEdition() throws Exception {
    int isOrgEdition = 999;
    final String sql = "select c.value from bb104 a, bb103 b, bb102 c,bb101 d where a.template_id = b.id and a.inner_name = 'ORG_EDITION'"
        + " and c.oup_item_id = a.id and c.org_unit_id=d.id and d.ou_type=1 ";
    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Integer tempValue = new Integer(999);
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                tempValue = new Integer(obj.toString());
              }
            }
            return tempValue;
          }
        });
    if (value != null) {
      isOrgEdition = value.intValue();
    }
    return isOrgEdition;
  }

  /**
   * 取得贷款权限Sql控制到银行
   * 
   * @param username
   * @return
   */
  public List getDkUserBankList(final String username) {
    List dkUserBankList = new ArrayList();

    dkUserBankList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = "select distinct ca113.coll_bank_id,ca113.coll_bank_name from CA113 ca113,PL002 pl002 where pl002.loan_bank_id=coll_bank_id and pl002.loan_bnak_st=1 and ca113.username='"
                + username + "'";
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              Userslogincollbank userslogincollbank = new Userslogincollbank();
              userslogincollbank.setCollBankId(new Integer(obj[0].toString()));
              userslogincollbank.setCollBankName(obj[1].toString());
              list.add(userslogincollbank);
            }
            return list;
          }
        });

    return dkUserBankList;
  }

  /**
   * 查询此银行代码是否在此用户权限内 jj 2007-10-22 回收导入文件时调用
   * 
   * @param username
   * @param loanBankId
   * @return
   */
  public List getDkUserBankList_LJ(final String username,
      final String loanBankId) {
    List dkUserBankList = new ArrayList();

    dkUserBankList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = "select distinct ca113.coll_bank_id,ca113.coll_bank_name "
                + " from CA113 ca113,PL002 pl002 where pl002.loan_bank_id=coll_bank_id and pl002.loan_bnak_st=1 and ca113.username='"
                + username + "' and ca113.coll_bank_id = " + loanBankId;
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              Userslogincollbank userslogincollbank = new Userslogincollbank();
              userslogincollbank.setCollBankId(new Integer(obj[0].toString()));
              userslogincollbank.setCollBankName(obj[1].toString());
              list.add(userslogincollbank);
            }
            return list;
          }
        });

    return dkUserBankList;
  }

  /**
   * 取得用户权限下的账套
   * 
   * @param username
   * @return
   */
  public List getUserBookList(final String username) {
    List userBookList = new ArrayList();
    userBookList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = "select distinct ca116.book_id,ca116.book_name from CA116 ca116 where ca116.username='"
                + username + "'";
            Query query = session.createSQLQuery(sql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              UsersloginBook usersloginBook = new UsersloginBook();
              usersloginBook.setBookId(obj[0].toString());
              usersloginBook.setBookName(obj[1].toString());
              list.add(usersloginBook);
            }
            return list;
          }
        });
    return userBookList;
  }

  /**
   * 用户办事处权限Hqsl,SQL,考虑到了权限所有情况
   * 
   * @param username
   * @return
   */
  public String getOfficeSecurity(final List userOfficeList) {
    String officeSecuritySQLOther = "";
    String tempString = "";
    for (int i = 0; i < userOfficeList.size(); i++) {
      OfficeDto officeDto = (OfficeDto) userOfficeList.get(i);
      tempString = tempString + officeDto.getOfficeCode();
      if (i < userOfficeList.size() - 1) {
        tempString = tempString + ",";
      }
    }
    officeSecuritySQLOther = " in (" + tempString + ") ";

    return officeSecuritySQLOther;
  }

  /**
   * 得到财务结算方式:统一核算,独立核算
   * 
   * @return
   * @throws Exception
   */
  public int getFnSettleType() throws Exception {
    int fnSettleType = 999;
    final String sql = "select c.value from bb104 a, bb103 b, bb102 c,bb101 d where a.template_id = b.id and a.inner_name = 'FN_SETTLE_TYPE'"
        + " and c.oup_item_id = a.id and c.org_unit_id=d.id and d.ou_type=1 ";
    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Integer tempValue = new Integer(999);
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                tempValue = new Integer(obj.toString());
              }
            }
            return tempValue;
          }
        });
    if (value != null) {
      fnSettleType = value.intValue();
    }
    return fnSettleType;
  }

  /**
   * 取得用户权限下的账套名称
   * 
   * @param username
   * @return
   */
  public String getUserBookName(final String bookId) {
    String bookName = "";
    bookName = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws SQLException,
          HibernateException {
        String sql = "select distinct ca116.book_name from CA116 ca116 where ca116.book_id='"
            + bookId + "'";
        Query query = session.createSQLQuery(sql);

        Iterator it = query.list().iterator();
        Object obj = null;
        String temp_bookName = "";
        while (it.hasNext()) {
          obj = (Object) it.next();
          temp_bookName = obj.toString();
        }
        return temp_bookName;
      }
    });
    return bookName;
  }

  /**
   * 用户尚未分配的账套
   * 
   * @param username
   * @return
   */
  public List getSpareUserBookList(final String username, final String users) {
    List userOfficeList = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String hql = getSpareBookSecuritySQL(username, users);

            Query query = session.createSQLQuery(hql);
            List list = new ArrayList();
            Iterator it = query.list().iterator();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                UserAssignBookDTO userAssignBookDTO = new UserAssignBookDTO();
                userAssignBookDTO.setBookid(obj[0].toString());
                userAssignBookDTO.setBookname(obj[1].toString());
                list.add(userAssignBookDTO);
              }
            }
            return list;
          }
        });
    return userOfficeList;
  }

  /**
   * 用户尚未分配的账套语句
   * 
   * @param username
   * @param users
   * @return
   */
  public String getSpareBookSecuritySQL(final String username,
      final String users) {
    String orgSecuritySQL = "";
    if (this.isCenterMng(users)) {
      orgSecuritySQL = " select distinct a.book_id,a.book_name from FN101 a "
          + " minus "
          + " select * from ("
          + " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113 where fn101.book_id = bb113.book_id "
          + " and bb113.username = '"
          + username
          + "' "
          + " union "
          + " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113, BB112 bb112 where fn101.book_id = bb113.book_id "
          + " and bb112.subusername = bb113.username "
          + " and bb112.username = '" + username + "' )";
    } else {
      orgSecuritySQL = " select a.book_id,a.book_name from ca116 a where a.username='"
          + users
          + "'"
          + " minus "
          + " select * from ("
          + " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113 where fn101.book_id = bb113.book_id "
          + " and bb113.username = '"
          + username
          + "' "
          + " union "
          + " select fn101.book_id as bookid, fn101.book_name as bookname from FN101 fn101, BB113 bb113, BB112 bb112 where fn101.book_id = bb113.book_id "
          + " and bb112.subusername = bb113.username "
          + " and bb112.username = '" + username + "' )";
    }
    return orgSecuritySQL;
  }

  /**
   * 得到财政代扣凭证模式
   * 
   * @return
   * @throws Exception
   */
  public int getAgentPayModel() throws Exception {
    int AgentPayModel = 999;
    final String sql = "select c.value from bb104 a, bb103 b, bb102 c,bb101 d where a.template_id = b.id and a.inner_name = 'AGENTPAY_MODEL'"
        + " and c.oup_item_id = a.id and c.org_unit_id=d.id and d.ou_type=1 ";
    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Integer tempValue = new Integer(999);
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                tempValue = new Integer(obj.toString());
              }
            }
            return tempValue;
          }
        });
    if (value != null) {
      AgentPayModel = value.intValue();
    }
    return AgentPayModel;
  }

  /**
   * 得到公积金还贷凭证模式
   * 
   * @return
   * @throws Exception
   */
  public int getLoanBackByCollModel() throws Exception {
    int LoanBackByCollModel = 999;
    final String sql = "select c.value from bb104 a, bb103 b, bb102 c,bb101 d where a.template_id = b.id and a.inner_name = 'LOANBACKBYCOLL_MODEL'"
        + " and c.oup_item_id = a.id and c.org_unit_id=d.id and d.ou_type=1 ";
    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Integer tempValue = new Integer(999);
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                tempValue = new Integer(obj.toString());
              }
            }
            return tempValue;
          }
        });
    if (value != null) {
      LoanBackByCollModel = value.intValue();
    }
    return LoanBackByCollModel;
  }

  /**
   * 得到是否存在单位版
   * 
   * @return
   * @throws Exception
   */
  public int isHaveOrgVersion() throws Exception {
    int isHaveOrgVersion = 999;
    final String sql = "select c.value from bb104 a, bb103 b, bb102 c,bb101 d where a.template_id = b.id and a.inner_name = 'ISHAVE_ORG_EDITION'"
        + " and c.oup_item_id = a.id and c.org_unit_id=d.id and d.ou_type=1 ";
    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Integer tempValue = new Integer(999);
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                tempValue = new Integer(obj.toString());
              }
            }
            return tempValue;
          }
        });
    if (value != null) {
      isHaveOrgVersion = value.intValue();
    }
    return isHaveOrgVersion;
  }

  /**
   * 得到是否启用邮件功能
   * 
   * @return
   * @throws Exception
   */
  public int mailFunction() throws Exception {
    int isHaveOrgVersion = 999;
    final String sql = "select c.value from bb104 a, bb103 b, bb102 c,bb101 d where a.template_id = b.id and a.inner_name = 'MAIL_FUNCTION'"
        + " and c.oup_item_id = a.id and c.org_unit_id=d.id and d.ou_type=1 ";
    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Integer tempValue = new Integer(999);
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object obj = null;
            while (it.hasNext()) {
              obj = (Object) it.next();
              if (obj != null) {
                tempValue = new Integer(obj.toString());
              }
            }
            return tempValue;
          }
        });
    if (value != null) {
      isHaveOrgVersion = value.intValue();
    }
    return isHaveOrgVersion;
  }

  /**
   * 获得邮件信息
   * 
   * @return
   */
  private MailMessageDTO getMailMessage() {
    MailMessageDTO dto = new MailMessageDTO();
    final String sql = "select ca200.id,ca200.addresser,ca200.addersserpassword,ca200.addressermail,ca200.mailboxtype,ca200.addresseea,ca200.addresseeb,ca200.subjectname from ca200 where ca200.is_start='1' ";
    dto = (MailMessageDTO) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            Query query = session.createSQLQuery(sql);
            Iterator it = query.list().iterator();
            Object[] obj = null;
            MailMessageDTO temp_dto=null;
            while (it.hasNext()) {
              temp_dto=new MailMessageDTO();
              obj = (Object[]) it.next();
              if (obj[0] != null) {
                temp_dto.setId(obj[0].toString());
              }
              if (obj[1] != null) {
                temp_dto.setAddresser(obj[1].toString());
              }
              if (obj[2] != null) {
                temp_dto.setAddersserpassword(obj[2].toString());
              }
              if (obj[3] != null) {
                temp_dto.setAddressermail(obj[3].toString());
              }
              if (obj[4] != null) {
                temp_dto.setMailboxtype(obj[4].toString());
              }
              if (obj[5] != null) {
                temp_dto.setAddresseea(obj[5].toString());
              }
              if (obj[6] != null) {
                temp_dto.setAddresseeb(obj[6].toString());
              }
              if (obj[7] != null) {
                temp_dto.setSubjectname(obj[7].toString());
              }
            }
            return temp_dto;
          }
        });
    return dto;
  }
  /**
   * jj
   * 查询AA309转入单位ID不为空的转出头表ID
   * @return
   */
  public List queryTranOutHeadId(final String collBankId) {
    List list=null;
    try{
      list=(List)getHibernateTemplate().executeFind(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select aa309.id from AA309 aa309,AA001 aa001,BA001 ba001 "+
                         "where aa309.in_org_id = aa001.id and aa001.orginfo_id = ba001.id "+
                         "and ba001.collection_bank_id in ("+collBankId+")";
            Query query = session.createSQLQuery(hql);
             return query.list();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  } 
  /**
   * jj
   * 根据转出头表ID查询AA311中未入账的业务
   * @return
   */
  public List queryTranInHead(final String tranOutHeadId) {
    List list=null;
    try{
      list=(List)getHibernateTemplate().executeFind(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select tranInHead.tranInOrg.orgInfo.collectionBankId" +
                " from TranInHead tranInHead where  "+
                         "tranInHead.tranStatus = 5 and tranInHead.tranOutHeadId = ?  ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new BigDecimal(tranOutHeadId));
             return query.list();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
}