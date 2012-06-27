package org.xpup.security.wsf.bizsrvc.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.SaltSource;
import net.sf.acegisecurity.providers.dao.UserCache;
import net.sf.acegisecurity.providers.encoding.PasswordEncoder;

import org.apache.commons.lang.Validate;
import org.hibernate.Hibernate;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.RelaUserAndBookDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndBook;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.OverdueInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.OverdueInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;
import org.xpup.security.common.dao.DataAccessDAO;
import org.xpup.security.common.dao.DrRelationDAO;
import org.xpup.security.common.dao.DuRelationDAO;
import org.xpup.security.common.dao.MenuItemDAO;
import org.xpup.security.common.dao.MrRelationDAO;
import org.xpup.security.common.dao.MuRelationDAO;
import org.xpup.security.common.dao.OperationDAO;
import org.xpup.security.common.dao.OrRelationDAO;
import org.xpup.security.common.dao.OuRelationDAO;
import org.xpup.security.common.dao.RoleDAO;
import org.xpup.security.common.dao.UserDAO;
import org.xpup.security.common.domain.DrRelation;
import org.xpup.security.common.domain.DuRelation;
import org.xpup.security.common.domain.MenuItem;
import org.xpup.security.common.domain.MrRelation;
import org.xpup.security.common.domain.MuRelation;
import org.xpup.security.common.domain.OrRelation;
import org.xpup.security.common.domain.OuRelation;
import org.xpup.security.common.domain.Role;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.exception.OperationDeniedException;
import org.xpup.security.wsf.bizsrvc.IDataAccessControlBS;
import org.xpup.security.wsf.bizsrvc.IMenuControlBS;
import org.xpup.security.wsf.bizsrvc.IOpControlBS;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class SecurityControlBS implements IMenuControlBS, IOpControlBS,
    IUserControlBS, IDataAccessControlBS {

  private MenuItemDAO menuItemDAO = null;
  
  private BookDAO bookDAO = null;

  public UserDAO userDAO = null;

  public RoleDAO roleDAO = null;

  private OperationDAO operationDAO = null;

  private MuRelationDAO muRelationDAO = null;

  private OuRelationDAO ouRelationDAO = null;

  private MrRelationDAO mrRelationDAO = null;

  private OrRelationDAO orRelationDAO = null;

  private DataAccessDAO dataAccessDAO = null;

  private DrRelationDAO drRelationDAO = null;

  private DuRelationDAO duRelationDAO = null;

  private SecurityDAO securityDAO = null;

  public UserCache userCache = null;

  public PasswordEncoder passwordEncoder = null;

  public SaltSource saltSource = null;

  public OverdueInfoDAO overdueInfoDAO = null;

  public BorrowerAccDAO borrowerAccDAO = null;

  public RestoreLoanDAO restoreLoanDAO = null;

  public LoanBankParaDAO loanBankParaDAO = null;
  
  private RelaUserAndBookDAO relaUserAndBookDAO = null;
  
  private AccountantCredenceDAO accountantCredenceDAO = null;

  public void setRelaUserAndBookDAO(RelaUserAndBookDAO relaUserAndBookDAO) {
    this.relaUserAndBookDAO = relaUserAndBookDAO;
  }

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setDataAccessDAO(DataAccessDAO dataAccessDAO) {
    this.dataAccessDAO = dataAccessDAO;
  }

  public void setDrRelationDAO(DrRelationDAO drRelationDAO) {
    this.drRelationDAO = drRelationDAO;
  }

  public void setDuRelationDAO(DuRelationDAO duRelationDAO) {
    this.duRelationDAO = duRelationDAO;
  }

  public void setRoleDAO(RoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public void setMenuItemDAO(MenuItemDAO menuItemDAO) {
    this.menuItemDAO = menuItemDAO;
  }

  public void setOperationDAO(OperationDAO operationDAO) {
    this.operationDAO = operationDAO;
  }

  public void setMuRelationDAO(MuRelationDAO muRelationDAO) {
    this.muRelationDAO = muRelationDAO;
  }

  public void setMrRelationDAO(MrRelationDAO mrRelationDAO) {
    this.mrRelationDAO = mrRelationDAO;
  }

  public void setOrRelationDAO(OrRelationDAO orRelationDAO) {
    this.orRelationDAO = orRelationDAO;
  }

  public void setOuRelationDAO(OuRelationDAO ouRelationDAO) {
    this.ouRelationDAO = ouRelationDAO;
  }

  public void setUserCache(UserCache userCache) {
    this.userCache = userCache;
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public void setSaltSource(SaltSource saltSource) {
    this.saltSource = saltSource;
  }

  public List findAllMenuItemsByRoleId(Serializable roleId) {

    return menuItemDAO.queryAllFromMR(roleId);
  }

  public List findAllMenuItemsByUserId(Serializable userId) {

    return menuItemDAO.queryAllFromMU(userId);
  }

  public List loadMenuItemTree() {
    List menuItems = menuItemDAO.queryAllRoot();
    Iterator it = menuItems.iterator();
    while (it.hasNext()) {
      MenuItem menuItem = (MenuItem) it.next();
      initSubMenuItems(menuItem);
    }
    return menuItems;
  }

  private void initSubMenuItems(MenuItem menuItem) {
    Hibernate.initialize(menuItem.getSubMenuItems());
    int count = menuItem.getSubMenuItems().size();
    if (count == 0) {
      return;
    }
    Iterator it = menuItem.getSubMenuItems().iterator();
    while (it.hasNext()) {
      MenuItem subMenuItem = (MenuItem) it.next();
      initSubMenuItems(subMenuItem);
    }
  }

  public MenuItem findMenuItem(Serializable id) throws BusinessException {
    MenuItem item = menuItemDAO.queryById(id);
    if (item == null)
      throw new EntityNotFoundException("菜单不存在，或已经被删除！");
    return item;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xpup.security.wsf.bizsrvc.impl.IMenuControlBS#findAllRootMenu(java.lang.String,
   *      int)
   */

  public String getMenu(final String username, final Serializable parentId)
      throws Exception {

    String items = menuItemDAO.getMenu(username, parentId);
    return items;
  }

  public String getMenu(final String username, final Serializable parentId,
      String url) throws Exception {

    String items = menuItemDAO.getMenu(username, parentId, url);
    return items;
  }

  public List findAllRootMenu(String username) {

    List items = menuItemDAO.queryAllRoot(username);
    return items;
  }

  public List findAllMenu(String username, String parentId) {
    List items = menuItemDAO.queryAll(username, parentId);
    return items;
  }

  public void assignMenuItemsToRole(Serializable roleId, List menuItemIds) {
    Validate.notNull(roleId);
    Validate.notNull(menuItemIds);

    mrRelationDAO.deleteAllByRoleId(roleId);
    Iterator it = menuItemIds.iterator();
    while (it.hasNext()) {
      String id = (String) it.next();
      List mrs = mrRelationDAO.queryByCriterions(id, roleId);
      if (mrs.size() == 0) {
        MrRelation mrRelation = new MrRelation();
        mrRelation.getMenuItem().setId(id);
        mrRelation.getRole().setId(roleId);
        mrRelationDAO.insert(mrRelation);
      }

      MenuItem item = menuItemDAO.queryById(id);
      while (item.getParentMenuItem() != null) {
        item = item.getParentMenuItem();
        List prs = mrRelationDAO.queryByCriterions(item.getId(), roleId);
        if (prs.size() > 0) {
          MrRelation pr = (MrRelation) prs.get(0);
          pr.setAuthNumber(pr.getAuthNumber() + 1);
        } else {
          MrRelation pr = new MrRelation();
          pr.getMenuItem().setId(item.getId());
          pr.getRole().setId(roleId);
          mrRelationDAO.insert(pr);
        }
      }
    }
  }

  public void assignMenuItemsToUser(Serializable userId, List menuItemIds) {
    Validate.notNull(userId);
    Validate.notNull(menuItemIds);

    muRelationDAO.deleteAllByUserId(userId);
    Iterator it = menuItemIds.iterator();
    while (it.hasNext()) {
      String id = (String) it.next();
      List mus = muRelationDAO.queryByCriterions(id, userId);
      if (mus.size() == 0) {
        MuRelation muRelation = new MuRelation();
        muRelation.getMenuItem().setId(id);
        muRelation.getUser().setId(userId);
        muRelationDAO.insert(muRelation);
      }

      MenuItem item = menuItemDAO.queryById(id);
      while (item.getParentMenuItem() != null) {
        item = item.getParentMenuItem();
        List prs = muRelationDAO.queryByCriterions(item.getId(), userId);
        if (prs.size() > 0) {
          MuRelation pr = (MuRelation) prs.get(0);
          pr.setAuthNumber(pr.getAuthNumber() + 1);
        } else {
          MuRelation pr = new MuRelation();
          pr.getMenuItem().setId(item.getId());
          pr.getUser().setId(userId);
          muRelationDAO.insert(pr);
        }
      }
    }
  }

  public void assignOperationsToRole(Serializable roleId, List operationIds) {
    Validate.notNull(roleId);
    Validate.notNull(operationIds);

    orRelationDAO.deleteAllByRoleId(roleId);
    Iterator it = operationIds.iterator();
    while (it.hasNext()) {
      String id = (String) it.next();
      OrRelation orRelation = new OrRelation();
      orRelation.getRole().setId(roleId);
      orRelation.getOperation().setId(id);
      orRelationDAO.insert(orRelation);
    }
  }

  public void assignOperationsToUser(Serializable userId, List operationIds) {
    Validate.notNull(userId);
    Validate.notNull(operationIds);

    ouRelationDAO.deleteAllByUserId(userId);
    Iterator it = operationIds.iterator();
    while (it.hasNext()) {
      String id = (String) it.next();
      OuRelation ouRelation = new OuRelation();
      ouRelation.getUser().setId(userId);
      ouRelation.getOperation().setId(id);
      ouRelationDAO.insert(ouRelation);
    }
  }

  public void assignRolesToUser(Serializable userId, List roleIds) {

    User user = userDAO.queryById(userId);
    user.getRoles().clear();
    Iterator it = roleIds.iterator();
    while (it.hasNext()) {
      String id = (String) it.next();
      Role role = roleDAO.queryById(id);
      if (role != null) {
        user.addRole(role);
      }
    }

  }

  public void decide(String username, String opInnerName)
      throws BusinessException {
    List operations1 = operationDAO.queryByCriterionsFromOU(username,
        opInnerName);
    List operations2 = operationDAO.queryByCriterionsFromOR(username,
        opInnerName);
    if (operations1.size() + operations2.size() == 0)
      throw new OperationDeniedException("对不起，您没有此项操作权限！");
  }

  public void changePassword(String username, String oldPassword,
      String newPassword) throws BusinessException {
    Validate.notNull(username, "参数username不能为空");
    Validate.notNull(oldPassword, "参数oldPassword不能为空");
    Validate.notNull(newPassword, "参数newPassword不能为空");

    User user = userDAO.queryByUsername(username);
    // 对传来的密码加密
    String oldPasswordEncoded = encodedPassword(oldPassword, user);
    String newPasswordEncoded = encodedPassword(newPassword, user);

    String password = user.getPassword();
    if (password.equals(oldPasswordEncoded)) {
      user.setPassword(newPasswordEncoded);
      removeUserFromCache(username);
    } else {
      throw new BusinessException("原密码错误，不能成功更新密码!");
    }
  }

  public void changePassword(String username, String newPassword) {
    Validate.notNull(username, "参数username不能为空");
    Validate.notNull(newPassword, "参数newPassword不能为空");

    User user = userDAO.queryByUsername(username);
    String newPasswordEncoded = encodedPassword(newPassword, user);
    user.setPassword(newPasswordEncoded);
    removeUserFromCache(username);
  }

  public String encodedPassword(String password, UserDetails user) {
    if (passwordEncoder != null) {
      return passwordEncoder.encodePassword(password, saltSource.getSalt(user));
    }
    return password;
  }

  public void removeUserFromCache(String username) {
    if (userCache != null) {
      userCache.removeUserFromCache(username);
    }
  }

  public List findDataAccessesUnusedByRoleId(Serializable roleId) {
    Validate.notNull(roleId, "参数roleId不能为空。");
    return dataAccessDAO.queryUnusedByRoleId(roleId);
  }

  public List findDataAccessesUnusedByUserId(Serializable userId) {
    Validate.notNull(userId, "参数userId不能为空。");
    return dataAccessDAO.queryUnusedByUserId(userId);
  }

  public List findDrRelationsByRoleId(Serializable roleId) {
    Validate.notNull(roleId, "参数roleId不能为空。");
    return drRelationDAO.queryByRoleId(roleId);
  }

  public List findDrRelationsByUserId(Serializable userId) {
    Validate.notNull(userId, "参数userId不能为空。");
    return null;
  }

  public List findDuRelationsByUserId(Serializable userId) {
    Validate.notNull(userId, "参数userId不能为空。");
    return duRelationDAO.queryByUserId(userId);
  }

  public void assignDuRelationsToUser(List duRelations, Serializable userId)
      throws BusinessException {
    duRelationDAO.deleteByUserId(userId);
    for (int i = 0; i < duRelations.size(); i++) {
      DuRelation duRelation = (DuRelation) duRelations.get(i);
      duRelation.getUser().setId(userId);
      duRelationDAO.insert(duRelation);
    }
  }

  public List findOperationsUnusedByUserId(Serializable userId) {
    return operationDAO.queryUnusedByUserId(userId);
  }

  public List findOperationsByUserId(Serializable userId) {

    return operationDAO.queryByUserIdFromOU(userId);
  }

  public List findOperationsByRoleId(Serializable roleId) {

    return operationDAO.queryByRoleIdFromOR(roleId);
  }

  public List findOperationsUnusedByRoleId(Serializable roleId) {

    return operationDAO.queryUnusedByRoleId(roleId);
  }

  public void assignDrRelationsToRole(List drRelations, Serializable roleId)
      throws BusinessException {

    drRelationDAO.deleteByRoleId(roleId);
    for (int i = 0; i < drRelations.size(); i++) {
      DrRelation drRelation = (DrRelation) drRelations.get(i);
      drRelation.getRole().setId(roleId);
      drRelationDAO.insert(drRelation);
    }

  }

  public User findUserbyUsername(String username) throws BusinessException {
    Validate.notNull(username, "参数username不能为空");

    return userDAO.queryByUsername(username);
  }

  public boolean checkLogin(String userName, String userPassword) {
    boolean flag;
    User user = userDAO.queryByUsername(userName);
    String encodedUserPassword = encodedPassword(userPassword, user);
    return flag = userDAO.checkLogin(userName, encodedUserPassword);
  }

  public HafEmployee getHafEmployee(final String username, final String userIp)
      throws Exception {

    return securityDAO.getUserInfo(username, userIp);

  }

  public SecurityInfo getSecurityInfo(final String username, final String userIp)
      throws Exception {

    return securityDAO.getSecurityInfo(username, userIp);

  }

  public String getUserBookName(final String bookId) {
    return securityDAO.getUserBookName(bookId);
  }

  public void updateBizDate(final String username, final String opSystemType,
      final String bizDate) throws BusinessException {
      securityDAO.updateBizDate(username, opSystemType, bizDate);
  }

  public MenuItem queryMenuItem(final String username, final String parentId) {
    // return menuItemDAO.queryMenuItem(parentId);
    List list = menuItemDAO.queryAllFromMU(username, parentId);
    List list1 = menuItemDAO.queryAllFromMR(username, parentId);
    MenuItem menuItem = null;
    if (list != null && list.size() != 0) {
      menuItem = (MenuItem) list.get(0);
    } else if (list1 != null && list1.size() != 0) {
      menuItem = (MenuItem) list1.get(0);
    }
    return menuItem;
  }

  public User outUser(String username, String password)
      throws BusinessException {
    // TODO Auto-generated method stub
    User user = userDAO.queryByUsername(username);
    user.setPassword(password);
    return user;
  }

  // ---------------------------copy生产逾期数据shiy-------------------------------------------//
  public void changMonth(final String username, final String opSystemType,
      final String bizDate, SecurityInfo securityInfo) throws BusinessException {
    securityDAO.updateBizDate(username, opSystemType, bizDate);
    // 贷款还款类型1.中心为主2.银行为主
    String temp_plLoanReturnType = securityInfo.getPlLoanReturnType() + "";
    if (temp_plLoanReturnType.equals("1")) {
      try {
        // this.createOverdueData(securityInfo);
      } catch (Exception e) {
        throw new BusinessException("逾期数据生成错误");
        // TODO Auto-generated catch block
      }
    }
  }

  /**
   * 以中心为主生成逾期数据 jj
   * 
   * @param securityInfo
   * @throws Exception
   */
  public void createOverdueData(SecurityInfo securityInfo) throws Exception {
    String username = securityInfo.getUserName();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String bizYearMonth = bizDate.substring(0, 6);
    String bizDay = bizDate.substring(6, 8);
    // overdueInfoDAO.executeCreateOverdueData(username, bizDate,new
    // Integer(bizDay),new Integer(bizYearMonth));
    this.updatePunishInterest(securityInfo);
  }

  public void updatePunishInterest(SecurityInfo securityInfo) throws Exception {
    List overDue_list = overdueInfoDAO.queryOverdueInfoContactIds(securityInfo);
    Iterator iterate = overDue_list.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      obj = (Object[]) iterate.next();
      LoancallbackTaAF af = this.getLoancallbackTaAF(obj[0].toString(),
          securityInfo);
      BigDecimal punishInterest = new BigDecimal(0.00);
      OverdueInfo overdueInfo = overdueInfoDAO.queryById(new Integer(obj[1]
          .toString()));
      List temp_list = af.getShouldBackList();
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) temp_list.get(i);
          punishInterest = punishInterest.add(dto.getPunishInterest());
        }
      }
      overdueInfo.setPunishInterest(punishInterest);
    }
  }

  public LoancallbackTaAF getLoancallbackTaAF(String contractId,
      SecurityInfo securityInfo) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    // String contractId =
    // (String)pagination.getQueryCriterions().get("contractId");
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    List borrowerList = new ArrayList();// 账户信息
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    List shouldBackList = new ArrayList();
    String yearMonth = bizDate.substring(0, 6);
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    // 从PL110及PL111取信息
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
    // 如果没有选择还至日则需判断还款日和会计日的大小
    if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
      // 会计日小于还款日
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
          contractId, yearMonth);
    } else {
      // 会计日大于等于还款日
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
          contractId, yearMonth);
    }
    af = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
    return af;
  }

  /**
   * 应还信息列表
   * 
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackList(List shouldBackList,
      BorrowerInfoDTO borrowerInfoDTO, String bizDate) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    List temp_list = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// 放款银行
    String isRate = "";// 是否记息
    String accountDate = "";// 记账日期
    String loanRepayDay = "";// 还款日 在取应还款信息时用到
    String paramType = "A";// 参数类型
    String interestMode = "";// 计算罚息方式
    String paramExplain = "";// 参数说明
    String allowdays = "";// 宽限天数
    BigDecimal temp_interest = new BigDecimal(0.00);// 临时罚息
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    // 从PL003中查询宽限天数内是否计息
    isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "5");
    // 从PL003中取计算罚息方式（按按罚息日利率、按合同日利率、按额每日XX元计算）
    interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
        "4");
    // 从PL003中取对应的参数说明
    paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
        "4");
    // PL003中查询宽限天数
    allowdays = loanBankParaDAO
        .queryParamExplain_LJ(loanBankId, paramType, "5");
    if (!shouldBackList.isEmpty()) {
      for (int i = 0; i < shouldBackList.size(); i++) {
        ShouldBackListDTO dto1 = (ShouldBackListDTO) shouldBackList.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        String loanRepayDay1 = this.getEndDay(dto1.getLoanKouYearmonth(),
            loanRepayDay);
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// 显示还款年月
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
        // 逾期天数
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        if (days > 0) {
          dto2.setLoanKouType("逾期");// 显示还款类型
        } else {
          dto2.setLoanKouType("正常");
        }
        dto2.setShouldCorpus(dto1.getShouldCorpus().subtract(
            dto1.getRealCorpus()));// 显示应还本金
        dto2.setShouldInterest(dto1.getShouldInterest().subtract(
            dto1.getRealInterest()));// 显示应还利息
        dto2.setDays(days + "");// 显示逾期天数
        if (days < 0) {
          dto2.setDays("0");
        }
        // 罚息卡住了。
        // 足条计算每月产生的罚息
        // 判断还款表(应还本金-本金+应还利息-利息)是否=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else if (days <= 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // 不等于0判断是否在宽限天数内计息
          // 条件银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数值PARAM_VALUE是否=0(宽限天数内计息)
          if (isRate.equals(BusiConst.YES + "")) {// 计息
            // 逐条判断PL201中的记账日期是否小于等于还款日
            accountDate = dto1.getBizDate();
            // String temp_day = accountDate.substring(6, 8);// 记账日期的日
            // if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay))
            // {// 小于等于还款日
            if (accountDate == null || accountDate.equals("")) {// 判断是否有记账日期：没有：减还款年月；有：减记账日期
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
                  loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                  dto1.getShouldInterest(), dto1.getRealInterest(),
                  paramExplain, dto1.getLoanRate());
            } else if (Integer.parseInt(accountDate) <= Integer.parseInt(dto1
                .getLoanKouYearmonth()
                + loanRepayDay1)) {// 小于等于还款日
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
                  loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                  dto1.getShouldInterest(), dto1.getRealInterest(),
                  paramExplain, dto1.getLoanRate());
            } else {// 大于还款日
              temp_interest = PunishInterestBS.getTempInterestByClearDate(
                  interestMode, bizDate, dto1.getBizDate(), dto1
                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
                      .getShouldInterest(), dto1.getRealInterest(),
                  paramExplain, dto1.getLoanRate());
              temp_interest = temp_interest.add(dto1.getPunishInterest())
                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// 加还款表中未还罚息
            }
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(temp_interest);
            } else {
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          } else {// 不计息
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(new BigDecimal(0.00));
            } else {// 已经逾期
              // 逐条判断PL201中的记账日期是否小于等于还款日+宽限天数
              // String temp_day = dto1.getBizDate().substring(6, 8);// 记账日期的日
              // 还款年月日+宽限天数后生成的年月日
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay1, Integer.parseInt(allowdays));
              // temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
              // if (Integer.parseInt(temp_day) <=
              // Integer.parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
              if (dto1.getBizDate() == null || dto1.getBizDate().equals("")) {// 判断是否有记账日期
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
                    loanRepayDay1, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, allowdays, dto1
                        .getLoanRate());
              } else if (Integer.parseInt(dto1.getBizDate()) <= Integer
                  .parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
                    loanRepayDay1, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, allowdays, dto1
                        .getLoanRate());
              } else {// 大于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByClearDate(
                    interestMode, bizDate, dto1.getBizDate(), dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, dto1.getLoanRate());
                // 加还款表中未还罚息
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest())
            .add(dto2.getPunishInterest()));// 显示应还本息合计
        dto2.setLoanRate(dto1.getLoanRate());// 显示每月利率
        dto2.setShow_loanRate(dto1.getShow_loanRate());
        sumCorpus = sumCorpus.add(dto2.getShouldCorpus());
        sumInterest = sumInterest.add(dto2.getShouldInterest().add(
            dto2.getPunishInterest()));
        temp_list.add(dto2);
      }
    }
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setShouldBackList(temp_list);
    return af;
  }

  /**
   * 日结
   * 
   * @param rowArray
   * @throws Exception
   * @throws BusinessException
   */
  public void updateBizDate_jj(String[] rowArray,SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String str = "";
    String collBankId = "";
    List list = new ArrayList();
    List temp_officeList = new ArrayList();
    String bizDate = "";
    List officeList = securityInfo.getOfficeList();
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      temp_officeList.add(officedto.getOfficeCode());
    }
//    try {
      if (rowArray.length > 0) {
        for (int i = 0; i < rowArray.length; i++) {
          str = rowArray[i];
          collBankId = str.substring(0, str.indexOf(","));
          bizDate = str.substring(str.indexOf(",") + 1, str.length());
          int days = BusiTools.daysOfMonth(Integer.parseInt(bizDate.substring(
              0, 4)), Integer.parseInt(bizDate.substring(4, 6)));
          int day = Integer.parseInt(bizDate.substring(6, 8));
          // 判断该银行下是否有未记账的业务
          CollBank collBank = loanBankParaDAO
              .getCollBankByCollBankid_(collBankId);

          list = loanBankParaDAO.queryAA101Status_wsh(collBankId,temp_officeList,bizDate);
          if (list.size() > 0) {
            throw new BusinessException(collBank.getCollBankName()
                + "有未记账的业务不能日结！");
          }
          if (day != days) {
//            bizDate = BusiTools.addDay(bizDate, 1);
            securityDAO.updateCollBankDate_jj(collBankId, bizDate);
          }
        }
      }
//    } catch (BusinessException e) {
//      throw new BusinessException(e.getLocalizedMessage());
//    }catch (Exception e) {
//
//    }
  }

  /**
   * 日结
   * 
   * @param rowArray
   * @throws Exception
   * @throws BusinessException
   */
  public void updatePLBizDate_jj(String[] rowArray) throws Exception,
      BusinessException {
    String str = "";
    String collBankId = "";
    String bizDate = "";
    List list = new ArrayList();
    // try{
    if (rowArray.length > 0) {
      for (int i = 0; i < rowArray.length; i++) {
        str = rowArray[i];
        collBankId = str.substring(0, str.indexOf(","));
        bizDate = str.substring(str.indexOf(",") + 1, str.length());

        // 判断该银行下是否有未记账的业务
        CollBank collBank = loanBankParaDAO
            .getCollBankByCollBankid_(collBankId);

        list = loanBankParaDAO.queryPL201Status_jj(collBankId);
        if (list.size() > 0) {
          throw new BusinessException(collBank.getCollBankName()
              + "有未记账的业务不能日结！");
        }

      }
    }
    if (rowArray.length > 0) {
      for (int i = 0; i < rowArray.length; i++) {
        str = rowArray[i];
        collBankId = str.substring(0, str.indexOf(","));
        bizDate = str.substring(str.indexOf(",") + 1, str.length());
        // 判断该银行下是否有未记账的业务
        CollBank collBank = loanBankParaDAO
            .getCollBankByCollBankid_(collBankId);

        list = loanBankParaDAO.queryPL201Status_jj(collBankId);
        if (list.size() > 0) {
          throw new BusinessException(collBank.getCollBankName()
              + "有未记账的业务不能日结！");
        }
        securityDAO.updateLoanBankDate_jj(collBankId, bizDate);
      }
    }
  }

  public String checkClear(String[] rowArray,SecurityInfo securityInfo) throws Exception, BusinessException {

    // 在月结时判断是否有转入未完成的业务
    // 在AA309中查询存在转入单位的业务即转入单位编号不为空
    // 取出这种业务的头表ID到AA311中查询：1.存在但未入账，给出提示；2.不存在，也提示。提示：有转入未入账的业务
    String str = "";
    String collBankId = "";
    String ids = "";
    

    List list1 = new ArrayList();
    List temp_officeList = new ArrayList();
    String bizDate = "";
    List officeList = securityInfo.getOfficeList();
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      temp_officeList.add(officedto.getOfficeCode());
    }
    

    if (rowArray.length > 0) {
      for (int i = 0; i < rowArray.length; i++) {
        str = rowArray[i];
        collBankId = str.substring(0, str.indexOf(","));
        ids = ids + collBankId + ",";
      }
      if (ids.length() > 0) {
        ids = ids.substring(0, ids.lastIndexOf(","));
      }
      System.out.println(ids + "---------------");
    }
    List list = securityDAO.queryTranOutHeadId(ids);
    Object obj = null;
    if (!list.isEmpty()) {
      Iterator it = list.iterator();
      while (it.hasNext()) {
        obj = (Object) it.next();
        List tempList = securityDAO.queryTranInHead(obj.toString());
        if (tempList.isEmpty()) {
          return "3";
        }
      }
    }
    int bool=0;
    String strMessage="";
    try {
    if (rowArray.length > 0) {
      for (int i = 0; i < rowArray.length; i++) {
        str = rowArray[i];
        collBankId = str.substring(0, str.indexOf(","));
        bizDate = str.substring(str.indexOf(",") + 1, str.length());
        int days = BusiTools.daysOfMonth(Integer.parseInt(bizDate.substring(
            0, 4)), Integer.parseInt(bizDate.substring(4, 6)));
        int day = Integer.parseInt(bizDate.substring(6, 8));
        // 判断该银行下是否有未记账的业务
        CollBank collBank = loanBankParaDAO
            .getCollBankByCollBankid_(collBankId);

        list1 = loanBankParaDAO.queryAA101MonthStatus_wsh(collBankId,temp_officeList,bizDate.substring(0,6)+"%");
        if (list1.size() > 0) {
          bool=1;
          strMessage=collBank.getCollBankName()+"有未记账的业务不能月结！";
        }

      }
    } }catch (Exception e) {

    }
    if(bool==1){
      return strMessage;
    }else{
      return "0";
    }
    
  }

  /**
   * 月结
   * 
   * @param rowArray
   * @throws Exception
   * @throws BusinessException
   */
  public void updateBizDateMonth_jj(String[] rowArray) throws Exception,
      BusinessException {
    String str = "";
    String collBankId = "";
    String bizDate = "";
    try {
      if (rowArray.length > 0) {
        for (int i = 0; i < rowArray.length; i++) {
          str = rowArray[i];
          collBankId = str.substring(0, str.indexOf(","));
          bizDate = str.substring(str.indexOf(",") + 1, str.length());
          bizDate = BusiTools.addMonth(bizDate, 1) + "01";
          securityDAO.updateCollBankDate_jj(collBankId, bizDate);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 月结
   * 
   * @param rowArray
   * @throws Exception
   * @throws BusinessException
   */
  public void updatePLBizDateMonth_jj(String[] rowArray) throws Exception,
      BusinessException {
    String str = "";
    String collBankId = "";
    String bizDate = "";
    List list = new ArrayList();
    
    
    try {
      if (rowArray.length > 0) {
        for (int i = 0; i < rowArray.length; i++) {
          str = rowArray[i];
          collBankId = str.substring(0, str.indexOf(","));
          bizDate = str.substring(str.indexOf(",") + 1, str.length());
          int days = BusiTools.daysOfMonth(Integer.parseInt(bizDate.substring(
              0, 4)), Integer.parseInt(bizDate.substring(4, 6)));
          int day = Integer.parseInt(bizDate.substring(6, 8));
          // 判断该银行下是否有未记账的业务
          CollBank collBank = loanBankParaDAO
              .getCollBankByCollBankid_(collBankId);

          list = loanBankParaDAO.queryPl201Status_wsh(collBankId,bizDate.substring(0, 6)+"%");
          if (list.size() > 0) {
            throw new BusinessException(collBank.getCollBankName()
                + "有未记账的业务不能月结！");
          }

        }
      }
      
      if (rowArray.length > 0) {
        for (int i = 0; i < rowArray.length; i++) {
          str = rowArray[i];
          collBankId = str.substring(0, str.indexOf(","));
          bizDate = str.substring(str.indexOf(",") + 1, str.length());
          int days = BusiTools.daysOfMonth(Integer.parseInt(bizDate.substring(
              0, 4)), Integer.parseInt(bizDate.substring(4, 6)));
          int day = Integer.parseInt(bizDate.substring(6, 8));
          // 判断该银行下是否有未记账的业务
        

          
         
          if (day != days) {
//            bizDate = BusiTools.addDay(bizDate, 1);
            securityDAO.updateCollBankDate_jj(collBankId, bizDate);
          }
        }
      }
    } catch (BusinessException e) {
      throw new BusinessException(e.getLocalizedMessage());
    }catch (Exception e) {

    }
    
    
    
    
    try {
      if (rowArray.length > 0) {
        for (int i = 0; i < rowArray.length; i++) {
          str = rowArray[i];
          collBankId = str.substring(0, str.indexOf(","));
          bizDate = str.substring(str.indexOf(",") + 1, str.length());
          bizDate = BusiTools.addMonth(bizDate, 1) + "01";
          securityDAO.updateLoanBankDate_jj(collBankId, bizDate);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 判断还款日如果大于本月最后一天返回本月最后一天
   * 
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth, String loanRepayDay) {
    String day = loanRepayDay;
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer
        .parseInt(month));
    if (Integer.parseInt(loanRepayDay) > days) {
      day = String.valueOf(days);
    }
    if (day.length() < 2 && Integer.parseInt(day) < 10) {
      day = "0" + day;
    }
    return day;
  }

  // ------------------------------------------------------------------//
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setOverdueInfoDAO(OverdueInfoDAO overdueInfoDAO) {
    this.overdueInfoDAO = overdueInfoDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }
  
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }
  
  public boolean checkIsOrg() {// 判断是否是单位进入系统，如果是返回true；否则返回false；
    boolean flag = false;
    int check = 0;
    try {
      check = securityDAO.getIsOrgEdition();
      if (check == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
        flag = true;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return flag;

  }

  public boolean checkIsCenter() {// 判断是否是中心进入系统，如果是返回true；否则返回false；
    boolean flag = false;
    int check = 0;
    try {
      check = securityDAO.getIsOrgEdition();
      if (check == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        flag = true;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return flag;

  }

  public boolean checkHaveOrg() {
    boolean flag = false;
    int check = 0;
    try {
      check = securityDAO.isHaveOrgVersion();
      if (check == Integer.parseInt(BusiConst.IS_HAVE)) {// 有单位
        flag = true;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return flag;
  }
  public String getFnBizDate(String userName, String bookId) {
    String bizDate = "";
    RelaUserAndBook relaUserAndBook = relaUserAndBookDAO.queryUserOff(userName, bookId);
    if (relaUserAndBook != null) {
      bizDate = relaUserAndBook.getBizDate();
    } else {
      bizDate = bookDAO.getUseYearmonth(bookId);
    }
    return bizDate;
  }
  public List getUserBookList(String username) {
    return securityDAO.getUserBookList(username);
  }
  public void updateFBizDate(String bookId, String bizDate) throws BusinessException {
    Integer count = accountantCredenceDAO.queryCredCount(bookId, bizDate);
    if(count.intValue() > 0) {
      throw new BusinessException("该月份有尚未记账的凭证不能月结!");
    }
    relaUserAndBookDAO.updateFBizDate(bookId, BusiTools.addMonth(bizDate, 1) + "01");
  }

  
}
