package org.xpup.hafmis.orgstrct.bizsrvc.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.RelaRoleAndCollBankDAO;
import org.xpup.hafmis.orgstrct.dao.RelaRoleAndOfficeDAO;
import org.xpup.hafmis.orgstrct.dao.RelaRoleAndOrgDAO;
import org.xpup.hafmis.orgstrct.dao.RelaUserAndBookDAO;
import org.xpup.hafmis.orgstrct.dao.RelaUserAndCollBankDAO;
import org.xpup.hafmis.orgstrct.dao.RelaUserAndOfficeDAO;
import org.xpup.hafmis.orgstrct.dao.RelaUserAndOrgDAO;
import org.xpup.hafmis.orgstrct.dao.RelaUserAndUserDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.daoDW.CollBankDAODW;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndCollBank;
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndOffice;
import org.xpup.hafmis.orgstrct.domain.RelaRoleAndOrg;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndBook;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndCollBank;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndOffice;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndOrg;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndUser;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.OrgDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.dto.UserAssignBookDTO;
import org.xpup.hafmis.orgstrct.dto.UserToSecurityDTO;
import org.xpup.hafmis.orgstrct.form.BankAssignRoleAF;
import org.xpup.hafmis.orgstrct.form.BankAssignUserAF;
import org.xpup.hafmis.orgstrct.form.CollBankTaAF;
import org.xpup.hafmis.orgstrct.form.OfficeAssignRoleAF;
import org.xpup.hafmis.orgstrct.form.OfficeAssignUserAF;
import org.xpup.hafmis.orgstrct.form.RoleToSecurityAF;
import org.xpup.hafmis.orgstrct.form.UserAssignBookAF;
import org.xpup.hafmis.orgstrct.form.UserAssignUsersAF;
import org.xpup.hafmis.orgstrct.form.UserToSecurityAF;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.security.common.dao.RoleDAO;
import org.xpup.security.common.domain.Userslogincollbank;

public class ToSecurityMngBS implements IToSecurityMngBS {
  
  private BookDAO bookDAO = null;

  private SecurityDAO securityDAO = null;

  private CollBankDAO collBankDAO = null;

  private CollBankDAODW collBankDAODW = null;

  private RelaUserAndOrgDAO relaUserAndOrgDAO = null;

  private OrgDAO orgDAO = null;

  private RelaUserAndOfficeDAO relaUserAndOfficeDAO = null;

  private RelaUserAndCollBankDAO relaUserAndCollBankDAO = null;

  private RoleDAO roleDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private RelaRoleAndOrgDAO relaRoleAndOrgDAO = null;

  private RelaRoleAndOfficeDAO relaRoleAndOfficeDAO = null;

  private RelaRoleAndCollBankDAO relaRoleAndCollBankDAO = null;

  private RelaUserAndUserDAO relaUserAndUserDAO = null;

  private RelaUserAndBookDAO relaUserAndBookDAO = null;

  public void setRelaUserAndBookDAO(RelaUserAndBookDAO relaUserAndBookDAO) {
    this.relaUserAndBookDAO = relaUserAndBookDAO;
  }

  public void setRelaRoleAndCollBankDAO(
      RelaRoleAndCollBankDAO relaRoleAndCollBankDAO) {
    this.relaRoleAndCollBankDAO = relaRoleAndCollBankDAO;
  }

  public void setRelaUserAndUserDAO(RelaUserAndUserDAO relaUserAndUserDAO) {
    this.relaUserAndUserDAO = relaUserAndUserDAO;
  }

  public void setRelaRoleAndOfficeDAO(RelaRoleAndOfficeDAO relaRoleAndOfficeDAO) {
    this.relaRoleAndOfficeDAO = relaRoleAndOfficeDAO;
  }

  public void setRelaRoleAndOrgDAO(RelaRoleAndOrgDAO relaRoleAndOrgDAO) {
    this.relaRoleAndOrgDAO = relaRoleAndOrgDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setCollBankDAODW(CollBankDAODW collBankDAODW) {
    this.collBankDAODW = collBankDAODW;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setRelaUserAndOrgDAO(RelaUserAndOrgDAO relaUserAndOrgDAO) {
    this.relaUserAndOrgDAO = relaUserAndOrgDAO;
  }

  public void setRelaUserAndOfficeDAO(RelaUserAndOfficeDAO relaUserAndOfficeDAO) {
    this.relaUserAndOfficeDAO = relaUserAndOfficeDAO;
  }

  public void setRelaUserAndCollBankDAO(
      RelaUserAndCollBankDAO relaUserAndCollBankDAO) {
    this.relaUserAndCollBankDAO = relaUserAndCollBankDAO;
  }

  public void setRoleDAO(RoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  /**
   * 查找办事处下是否有归集银行
   */
  public int findBank(String code) {
    int codeNumber = 0;
    codeNumber = securityDAO.findBank(code);
    return codeNumber;
  }

  /**
   * 用户列表
   */
  public List findUser(Pagination pagination) throws Exception {
    List userlist = new ArrayList();

    String username = (String) pagination.getQueryCriterions().get("username");
    int start = pagination.getFirstElementOnPage() - 1;
    int end = pagination.getPageSize();
    userlist = securityDAO.getUserList(username, start, end);
    List alllist = securityDAO.getUserListALL(username);
    pagination.setNrOfElements(alllist.size());
    return userlist;
  }

  /**
   * 用户单位
   */
  public List findUsersList() throws Exception {
    List userlist = null;
    userlist = securityDAO.getAllUserList();
    return userlist;
  }

  /**
   * 根据用户查询办事处
   */
  public List findOffice(String username) {
    List officelist = null;
    officelist = securityDAO.getUserOfficeLists(username);
    return officelist;
  }

  /**
   * 根据用户查询办事处
   */
  public List findOffice(SecurityInfo securityInfo) {
    String username = securityInfo.getUserName();
    List officelist = null;
    if (securityDAO.isCenterMng(username)) {
      officelist = securityDAO.getAllOfficeList();
    } else {
      officelist = findOffice(username);
    }
    return officelist;
  }

  public List findCollBankByusername(String username) {
    List banklist = null;
    banklist = securityDAO.getUserCollBankList(username);
    return banklist;
  }

  public UserToSecurityAF findTree(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    String username = (String) pagination.getQueryCriterions().get("username");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    String collBankId = (String) pagination.getQueryCriterions().get(
        "collBankId");
    String users = (String) pagination.getQueryCriterions().get("users");
    List banklist = new ArrayList();
    UserToSecurityAF uf = new UserToSecurityAF();
    List orginfolist = new ArrayList();
    List sparelist = new ArrayList();
    List userlist = securityInfo.getUserList();
    List allofficelist = securityInfo.getOfficeList();
    boolean b = securityDAO.isCenterMng(users);
    if (b) {
      userlist = securityDAO.getAllUserList();
      allofficelist = securityDAO.getAllOfficeList();
    } else {
      userlist = securityInfo.getUserList();
      allofficelist = findOffice(users);
    }
    if (username != null && username.length() > 0) {
      sparelist = securityDAO.getUserSpareOrgInfoList(username, users);
      orginfolist = securityDAO.getUserOrgInfoList(username);
    }
    try {
      if (officecode != null && officecode.length() > 0) {
        if (b) {
          banklist = collBankDAO.getOfficeCollBankList(officecode);
        } else {
          List list = findCollBankByusername(users);
          if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
              Userslogincollbank userslogincollbank = (Userslogincollbank) list
                  .get(i);
              CollBank collBank = collBankDAO
                  .getCollBankByCollBankid(userslogincollbank.getCollBankId()
                      .toString());
              if (collBank.getOffice().equals(officecode)) {
                banklist.add(collBank);
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    List templist = new ArrayList();
    List templist1 = new ArrayList();
    if (officecode != null && officecode.length() > 0) {
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        if (orgDto.getOfficeid().equals(officecode)) {
          templist.add(orgDto);
        }
      }
      sparelist = templist;
      if (collBankId != null && collBankId.length() > 0) {
        for (int i = 0; i < sparelist.size(); i++) {
          OrgDto orgDto = (OrgDto) sparelist.get(i);
          if (orgDto.getBankid().equals(collBankId)) {
            templist1.add(orgDto);
          }
        }
        sparelist = templist1;
      }
    }
    UserToSecurityDTO dto = new UserToSecurityDTO();
    try {
      dto.setSparelist(sparelist);
      dto.setBankList(banklist);
      dto.setOrgList(orginfolist);
      uf.setUserlist(userlist);
      uf.setOfficelist(allofficelist);
      uf.setBanklist(banklist);
      uf.setUserToSecurityDTO(dto);
      uf.setUsername(username);
      uf.setOfficename(officecode);
      uf.setBankname(collBankId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return uf;
  }

  // public Integer findBankCountsByofficecode(String username,String
  // officecode){
  // Integer count=securityDAO.getBankCountByOfficeCode(username, officecode);
  // return count;
  // }
  // public Integer findOrgCountsByBankid(String username,String
  // officecode,String bankid){
  // Integer count=securityDAO.getOrgCountByBankid(username, officecode,
  // bankid);
  // return count;
  // }
  public void deleteUserOrg(String username, String[] orgid)
      throws BusinessException {
    if (orgid == null)
      throw new BusinessException("撤消失败!");
    try {
      for (int i = 0; i < orgid.length; i++) {
        RelaUserAndOrg relaUserAndOrg = relaUserAndOrgDAO.queryByUserOrg(
            username, orgid[i]);
        relaUserAndOrgDAO.delete(relaUserAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void addUserOrg(String username, String[] orgid)
      throws BusinessException {
    if (orgid.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < orgid.length; i++) {
        Org org = orgDAO.queryById(new Integer(orgid[i]));
        RelaUserAndOrg relaUserAndOrg = new RelaUserAndOrg();
        relaUserAndOrg.setCollBankId(org.getOrgInfo().getCollectionBankId());
        relaUserAndOrg.setOffice(org.getOrgInfo().getOfficecode());
        relaUserAndOrg.setOrgId(new BigDecimal(org.getId().toString()));
        relaUserAndOrg.setUsername(username);
        relaUserAndOrgDAO.insert(relaUserAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void addAllUserOrg(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    List sparelist = new ArrayList();
    String username = (String) pagination.getQueryCriterions().get("username");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    String collBankId = (String) pagination.getQueryCriterions().get(
        "collBankId");
    try {
      if (username != null && username.length() > 0) {
        sparelist = securityDAO.getUserSpareOrgInfoList(username, securityInfo
            .getUserName());
      }
      List templist = new ArrayList();
      List templist1 = new ArrayList();
      if (officecode != null && officecode.length() > 0) {
        for (int i = 0; i < sparelist.size(); i++) {
          OrgDto orgDto = (OrgDto) sparelist.get(i);
          if (orgDto.getOfficeid().equals(officecode)) {
            templist.add(orgDto);
          }
        }
        sparelist = templist;
        if (collBankId != null && collBankId.length() > 0) {
          for (int i = 0; i < sparelist.size(); i++) {
            OrgDto orgDto = (OrgDto) sparelist.get(i);
            if (orgDto.getBankid().equals(collBankId)) {
              templist1.add(orgDto);
            }
          }
          sparelist = templist1;
        }
      }
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        Org org = orgDAO.queryById(new Integer(orgDto.getOrgid()));
        RelaUserAndOrg relaUserAndOrg = new RelaUserAndOrg();
        relaUserAndOrg.setCollBankId(org.getOrgInfo().getCollectionBankId());
        relaUserAndOrg.setOffice(org.getOrgInfo().getOfficecode());
        relaUserAndOrg.setOrgId(new BigDecimal(org.getId().toString()));
        relaUserAndOrg.setUsername(username);
        relaUserAndOrgDAO.insert(relaUserAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteAllUserOrg(String username) throws BusinessException {
    List orglist = new ArrayList();
    try {
      if (username != null && username.length() > 0) {
        orglist = securityDAO.getUserOrgInfoList(username);
      }
      for (int i = 0; i < orglist.size(); i++) {
        OrgDto orgDto = (OrgDto) orglist.get(i);
        RelaUserAndOrg relaUserAndOrg = relaUserAndOrgDAO.queryByUserOrg(
            username, orgDto.getOrgid());
        relaUserAndOrgDAO.delete(relaUserAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void addUserOffice(String username, String office)
      throws BusinessException {
    RelaUserAndOffice relaUserAndOffice = relaUserAndOfficeDAO.queryUserOff(
        username, office);
    if (relaUserAndOffice.getId() != null) {
      throw new BusinessException("分配失败!已经拥有这个权限！");
    } else {
      RelaUserAndOffice ruo = new RelaUserAndOffice();
      ruo.setOffice(office);
      ruo.setUsername(username);
      relaUserAndOfficeDAO.insert(ruo);
    }
  }

  public void addUserBank(String username, String collBankId)
      throws BusinessException {
    RelaUserAndCollBank relaUserAndCollBank = relaUserAndCollBankDAO
        .queryUserBank(username, collBankId);
    if (relaUserAndCollBank.getId() != null) {
      throw new BusinessException("分配失败!已经拥有这个权限！");
    } else {
      RelaUserAndCollBank ruc = new RelaUserAndCollBank();
      ruc.setCollBankId(collBankId);
      ruc.setUsername(username);
      relaUserAndCollBankDAO.insert(ruc);
    }
  }

  /**
   * 角色单位
   * 
   * @return
   */
  // 查询所有角色listCA102
  public List findRoleList() {
    List list = roleDAO.queryRoleList();
    return list;
  }

  // 查询所有办事处list
  public List findOrgUnitList() {
    List list = organizationUnitDAO.queryOrganizationUnitList();
    return list;
  }

  // 根据办事处查询归集行
  public List findCollBankByoffice(String office) {
    List list = collBankDAO.getOfficeCollBankList(office);
    return list;
  }

  public RoleToSecurityAF findRoleTree(Pagination pagination) throws Exception {
    RoleToSecurityAF roleToSecurityAF = new RoleToSecurityAF();
    try {
      String roleid = (String) pagination.getQueryCriterions().get("roleid");
      String officecode = (String) pagination.getQueryCriterions().get(
          "officecode");
      String collBankId = (String) pagination.getQueryCriterions().get(
          "collBankId");
      List rolelist = findRoleList();
      List orgUntilist = findOrgUnitList();
      List banklist = new ArrayList();
      List roleOrglist = new ArrayList();
      List sparelist = new ArrayList();
      if (roleid != null && roleid.length() > 0) {
        roleOrglist = relaRoleAndOrgDAO.queryRoleOrg(roleid);
        sparelist = relaRoleAndOrgDAO.querySpareRoleOrg(roleid);
      }
      if (officecode != null && officecode.length() > 0) {
        banklist = findCollBankByoffice(officecode);
      }
      List templist = new ArrayList();
      List templist1 = new ArrayList();
      if (officecode != null && officecode.length() > 0) {
        for (int i = 0; i < sparelist.size(); i++) {
          OrgDto orgDto = (OrgDto) sparelist.get(i);
          if (orgDto.getOfficeid().equals(officecode)) {
            templist.add(orgDto);
          }
        }
        sparelist = templist;
        if (collBankId != null && collBankId.length() > 0) {
          for (int i = 0; i < sparelist.size(); i++) {
            OrgDto orgDto = (OrgDto) sparelist.get(i);
            if (orgDto.getBankid().equals(collBankId)) {
              templist1.add(orgDto);
            }
          }
          sparelist = templist1;
        }
      }
      roleToSecurityAF.setRolelist(rolelist);
      roleToSecurityAF.setOfficelist(orgUntilist);
      roleToSecurityAF.setBanklist(banklist);
      roleToSecurityAF.setRoleOrglist(roleOrglist);
      roleToSecurityAF.setSparelist(sparelist);
      roleToSecurityAF.setRolename(roleid);
      roleToSecurityAF.setOfficename(officecode);
      roleToSecurityAF.setBankname(collBankId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return roleToSecurityAF;
  }

  public void addRoleOrg(String roleid, String[] orgid)
      throws BusinessException {
    if (orgid == null || orgid.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < orgid.length; i++) {
        Org org = orgDAO.queryById(new Integer(orgid[i]));
        RelaRoleAndOrg relaRoleAndOrg = new RelaRoleAndOrg();
        relaRoleAndOrg.setCollBankId(org.getOrgInfo().getCollectionBankId());
        relaRoleAndOrg.setOffice(org.getOrgInfo().getOfficecode());
        relaRoleAndOrg.setOrgId(org.getId().toString());
        relaRoleAndOrg.setRole(roleid);
        relaRoleAndOrgDAO.insert(relaRoleAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void addAllRoleOrg(Pagination pagination) throws BusinessException {
    List sparelist = new ArrayList();
    String roleid = (String) pagination.getQueryCriterions().get("roleid");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    String collBankId = (String) pagination.getQueryCriterions().get(
        "collBankId");
    try {
      if (roleid != null && roleid.length() > 0) {
        sparelist = relaRoleAndOrgDAO.querySpareRoleOrg(roleid);
      }
      List templist = new ArrayList();
      List templist1 = new ArrayList();
      if (officecode != null && officecode.length() > 0) {
        for (int i = 0; i < sparelist.size(); i++) {
          OrgDto orgDto = (OrgDto) sparelist.get(i);
          if (orgDto.getOfficeid().equals(officecode)) {
            templist.add(orgDto);
          }
        }
        sparelist = templist;
        if (collBankId != null && collBankId.length() > 0) {
          for (int i = 0; i < sparelist.size(); i++) {
            OrgDto orgDto = (OrgDto) sparelist.get(i);
            if (orgDto.getBankid().equals(collBankId)) {
              templist1.add(orgDto);
            }
          }
          sparelist = templist1;
        }
      }
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        Org org = orgDAO.queryById(new Integer(orgDto.getOrgid()));
        RelaRoleAndOrg relaRoleAndOrg = new RelaRoleAndOrg();
        relaRoleAndOrg.setCollBankId(org.getOrgInfo().getCollectionBankId());
        relaRoleAndOrg.setOffice(org.getOrgInfo().getOfficecode());
        relaRoleAndOrg.setOrgId(org.getId().toString());
        relaRoleAndOrg.setRole(roleid);
        relaRoleAndOrgDAO.insert(relaRoleAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteRoleOrg(String roleid, String[] orgid)
      throws BusinessException {
    if (orgid == null || orgid.length < 0)
      throw new BusinessException("撤消失败!");
    try {
      for (int i = 0; i < orgid.length; i++) {
        RelaRoleAndOrg relaRoleAndOrg = relaRoleAndOrgDAO.queryByRoleOrg(
            roleid, orgid[i]);
        relaRoleAndOrgDAO.delete(relaRoleAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void deleteAllRoleOrg(String roleid) throws BusinessException {
    List roleOrglist = new ArrayList();
    try {
      if (roleid != null && roleid.length() > 0) {
        roleOrglist = relaRoleAndOrgDAO.queryRoleOrg(roleid);
      }
      for (int i = 0; i < roleOrglist.size(); i++) {
        OrgDto orgDto = (OrgDto) roleOrglist.get(i);
        RelaRoleAndOrg relaRoleAndOrg = relaRoleAndOrgDAO.queryByRoleOrg(
            roleid, orgDto.getOrgid());
        relaRoleAndOrgDAO.delete(relaRoleAndOrg);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void addRoleOffice(String roleid, String office)
      throws BusinessException {
    RelaRoleAndOffice relaRoleAndOffice = relaRoleAndOfficeDAO.queryRoleOffice(
        roleid, office);
    if (relaRoleAndOffice.getId() != null) {
      throw new BusinessException("分配失败!已经拥有这个权限！");
    } else {
      RelaRoleAndOffice rro = new RelaRoleAndOffice();
      rro.setOffice(office);
      rro.setRoleid(roleid);
      relaRoleAndOfficeDAO.insert(rro);
    }
  }

  public void addRoleBank(String roleid, String collbankid)
      throws BusinessException {
    RelaRoleAndCollBank rrc = relaRoleAndCollBankDAO.queryRoleBank(roleid,
        collbankid);
    if (rrc.getId() != null) {
      throw new BusinessException("分配失败!已经拥有这个权限！");
    } else {
      RelaRoleAndCollBank relaRoleAndCollBank = new RelaRoleAndCollBank();
      relaRoleAndCollBank.setCollbankid(collbankid);
      relaRoleAndCollBank.setRoleid(roleid);
      relaRoleAndCollBankDAO.insert(relaRoleAndCollBank);
    }
  }

  /**
   * 角色办事处
   */
  public OfficeAssignRoleAF findRoleOffice(Pagination pagination)
      throws BusinessException {
    OfficeAssignRoleAF officeAssignRoleAF = new OfficeAssignRoleAF();
    String roleid = (String) pagination.getQueryCriterions().get("roleid");
    List roleOfficelist = new ArrayList();
    List sparelist = new ArrayList();
    // 查询所有角色
    List rolelist = findRoleList();

    if (roleid != null && roleid.length() > 0) {
      roleOfficelist = relaRoleAndOfficeDAO.queryOfficeByRoleid(roleid);
      sparelist = relaRoleAndOfficeDAO.querySpareOfficeByRoleid(roleid);
    }
    officeAssignRoleAF.setRolelist(rolelist);
    officeAssignRoleAF.setRoleOfficelist(roleOfficelist);
    officeAssignRoleAF.setSparelist(sparelist);
    officeAssignRoleAF.setRolename(roleid);
    return officeAssignRoleAF;
  }

  public void addRoleOffice(String roleid, String officeid[])
      throws BusinessException {
    if (officeid == null || officeid.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < officeid.length; i++) {
        RelaRoleAndOffice relaRoleAndOffice = new RelaRoleAndOffice();
        relaRoleAndOffice.setOffice(officeid[i]);
        relaRoleAndOffice.setRoleid(roleid);
        relaRoleAndOfficeDAO.insert(relaRoleAndOffice);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void addAllRoleOffice(String roleid) throws BusinessException {
    List sparelist = new ArrayList();
    try {
      if (roleid != null && roleid.length() > 0) {
        sparelist = relaRoleAndOfficeDAO.querySpareOfficeByRoleid(roleid);
      }
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        RelaRoleAndOffice relaRoleAndOffice = new RelaRoleAndOffice();
        relaRoleAndOffice.setOffice(orgDto.getOfficeid());
        relaRoleAndOffice.setRoleid(roleid);
        relaRoleAndOfficeDAO.insert(relaRoleAndOffice);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteRoleOffice(String roleid, String officeid[])
      throws BusinessException {
    if (officeid == null || officeid.length < 0)
      throw new BusinessException("撤消失败!");
    try {
      for (int i = 0; i < officeid.length; i++) {
        RelaRoleAndOffice relaRoleAndOffice = relaRoleAndOfficeDAO
            .queryRoleOffice(roleid, officeid[i]);
        relaRoleAndOfficeDAO.delete(relaRoleAndOffice);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void deleteAllRoleOffice(String roleid) throws BusinessException {
    List roleOfficelist = new ArrayList();
    try {
      roleOfficelist = relaRoleAndOfficeDAO.queryOfficeByRoleid(roleid);
      for (int i = 0; i < roleOfficelist.size(); i++) {
        OrganizationUnit organizationUnit = (OrganizationUnit) roleOfficelist
            .get(i);
        RelaRoleAndOffice relaRoleAndOffice = relaRoleAndOfficeDAO
            .queryRoleOffice(roleid, organizationUnit.getId().toString());
        relaRoleAndOfficeDAO.delete(relaRoleAndOffice);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("撤消失败!");
    }
  }

  /**
   * 角色分配银行
   */
  public BankAssignRoleAF findRoleBank(Pagination pagination) {
    BankAssignRoleAF bankAssignRoleAF = new BankAssignRoleAF();
    String roleid = (String) pagination.getQueryCriterions().get("roleid");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    List rolelist = findRoleList();
    List orgUntilist = findOrgUnitList();
    List roleBanklist = new ArrayList();
    List sparelist = new ArrayList();
    if (roleid != null && roleid.length() > 0) {
      roleBanklist = relaRoleAndCollBankDAO.queryBankByRoleid(roleid);
      sparelist = relaRoleAndCollBankDAO.querySpareBankByRoleid(roleid);
    }
    List templist = new ArrayList();
    if (officecode != null && officecode.length() > 0) {
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        if (orgDto.getOfficeid().equals(officecode)) {
          templist.add(orgDto);
        }
      }
      sparelist = templist;
    }
    bankAssignRoleAF.setRoleBanklist(roleBanklist);
    bankAssignRoleAF.setSparelist(sparelist);
    bankAssignRoleAF.setRolelist(rolelist);
    bankAssignRoleAF.setOfficelist(orgUntilist);
    bankAssignRoleAF.setRolename(roleid);
    bankAssignRoleAF.setOfficename(officecode);
    return bankAssignRoleAF;
  }

  public void addRoleBank(String roleid, String bankid[])
      throws BusinessException {
    if (bankid == null || bankid.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < bankid.length; i++) {
        RelaRoleAndCollBank relaRoleAndCollBank = new RelaRoleAndCollBank();
        relaRoleAndCollBank.setCollbankid(bankid[i]);
        relaRoleAndCollBank.setRoleid(roleid);
        relaRoleAndCollBankDAO.insert(relaRoleAndCollBank);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void addAllRoleBank(Pagination pagination) throws BusinessException {
    List sparelist = new ArrayList();
    String roleid = (String) pagination.getQueryCriterions().get("roleid");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    try {
      if (roleid != null && roleid.length() > 0) {
        sparelist = relaRoleAndCollBankDAO.querySpareBankByRoleid(roleid);
      }
      List templist = new ArrayList();
      if (officecode != null && officecode.length() > 0) {
        for (int i = 0; i < sparelist.size(); i++) {
          OrgDto orgDto = (OrgDto) sparelist.get(i);
          if (orgDto.getOfficeid().equals(officecode)) {
            templist.add(orgDto);
          }
        }
        sparelist = templist;
      }
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        RelaRoleAndCollBank relaRoleAndCollBank = new RelaRoleAndCollBank();
        relaRoleAndCollBank.setCollbankid(orgDto.getBankid());
        relaRoleAndCollBank.setRoleid(roleid);
        relaRoleAndCollBankDAO.insert(relaRoleAndCollBank);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteRoleBank(String roleid, String bankid[])
      throws BusinessException {
    if (bankid == null || bankid.length < 0)
      throw new BusinessException("撤消失败!");
    try {
      for (int i = 0; i < bankid.length; i++) {
        RelaRoleAndCollBank relaRoleAndCollBank = relaRoleAndCollBankDAO
            .queryRoleBank(roleid, bankid[i]);
        relaRoleAndCollBankDAO.delete(relaRoleAndCollBank);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void deleteAllRoleBank(String roleid) throws BusinessException {
    List roleOfficelist = new ArrayList();
    try {
      roleOfficelist = relaRoleAndCollBankDAO.queryBankByRoleid(roleid);
      for (int i = 0; i < roleOfficelist.size(); i++) {
        OrgDto orgDto = (OrgDto) roleOfficelist.get(i);
        RelaRoleAndCollBank relaRoleAndCollBank = relaRoleAndCollBankDAO
            .queryRoleBank(roleid, orgDto.getBankid());
        relaRoleAndCollBankDAO.delete(relaRoleAndCollBank);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("撤消失败!");
    }
  }

  public void addRoleAssignOffice(String roleid, String office)
      throws BusinessException {
    RelaRoleAndOffice relaRoleAndOffice = relaRoleAndOfficeDAO.queryRoleOffice(
        roleid, office);
    if (relaRoleAndOffice.getId() != null) {
      throw new BusinessException("分配失败!已经拥有这个权限！");
    } else {
      RelaRoleAndOffice rro = new RelaRoleAndOffice();
      rro.setOffice(office);
      rro.setRoleid(roleid);
      relaRoleAndOfficeDAO.insert(rro);
    }
  }

  /**
   * 用户办事处
   */
  public OfficeAssignUserAF findUserOffice(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    OfficeAssignUserAF officeAssignUserAF = new OfficeAssignUserAF();
    String username = (String) pagination.getQueryCriterions().get("username");
    String users = securityInfo.getUserName();
    List sparelist = new ArrayList();
    List userOfficelist = new ArrayList();
    List userlist = new ArrayList();
    List officelist = securityInfo.getOfficeList();
    boolean b = securityDAO.isCenterMng(users);
    if (b) {
      userlist = securityDAO.getAllUserList();
      officelist = securityDAO.getAllOfficeList();
    } else {
      userlist = securityInfo.getUserList();
      officelist = securityInfo.getOfficeList();
    }
    if (username != null && username.length() > 0) {
      userOfficelist = findOffice(username);
      sparelist = securityDAO.getSpareUserOfficeList(username, users);
    }
    officeAssignUserAF.setUserOfficelist(userOfficelist);
    officeAssignUserAF.setUserlist(userlist);
    officeAssignUserAF.setOfficelist(officelist);
    officeAssignUserAF.setSparelist(sparelist);
    officeAssignUserAF.setUsername(username);
    return officeAssignUserAF;
  }

  public void addUserOffice(String username, String officeid[])
      throws BusinessException {
    if (officeid == null || officeid.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < officeid.length; i++) {
        RelaUserAndOffice relaUserAndOffice = new RelaUserAndOffice();
        relaUserAndOffice.setOffice(officeid[i]);
        relaUserAndOffice.setUsername(username);
        relaUserAndOfficeDAO.insert(relaUserAndOffice);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void addAllUserOffice(String username, SecurityInfo securityInfo)
      throws BusinessException {
    List sparelist = new ArrayList();
    String users = securityInfo.getUserName();
    try {
      if (username != null && username.length() > 0) {
        sparelist = securityDAO.getSpareUserOfficeList(username, users);
      }
      for (int i = 0; i < sparelist.size(); i++) {
        OfficeDto officeDto = (OfficeDto) sparelist.get(i);
        RelaUserAndOffice relaUserAndOffice = new RelaUserAndOffice();
        relaUserAndOffice.setOffice(officeDto.getOfficeCode());
        relaUserAndOffice.setUsername(username);
        relaUserAndOfficeDAO.insert(relaUserAndOffice);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteUserOffice(String username, String officeid[])
      throws BusinessException {
    if (officeid == null || officeid.length < 0)
      throw new BusinessException("撤消失败!");
    try {
      for (int i = 0; i < officeid.length; i++) {
        RelaUserAndOffice relaUserAndOffice = relaUserAndOfficeDAO
            .queryUserOff(username, officeid[i]);
        relaUserAndOfficeDAO.delete(relaUserAndOffice);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void deleteAllUserOffice(String username) throws BusinessException {
    List userOfficelist = new ArrayList();
    try {
      userOfficelist = findOffice(username);
      for (int i = 0; i < userOfficelist.size(); i++) {
        OfficeDto officeDto = (OfficeDto) userOfficelist.get(i);
        RelaUserAndOffice relaUserAndOffice = relaUserAndOfficeDAO
            .queryUserOff(username, officeDto.getOfficeCode());
        relaUserAndOfficeDAO.delete(relaUserAndOffice);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("撤消失败!");
    }
  }

  /**
   * 用户银行
   */
  public BankAssignUserAF findUserBank(Pagination pagination,
      SecurityInfo securityInfo) {
    BankAssignUserAF bankAssignUserAF = new BankAssignUserAF();
    String username = (String) pagination.getQueryCriterions().get("username");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    String users = securityInfo.getUserName();
    List userlist = new ArrayList();
    List officelist = findOrgUnitList();
    List userBanklist = new ArrayList();
    List sparelist = new ArrayList();
    boolean b = securityDAO.isCenterMng(users);
    if (b) {
      userlist = securityDAO.getAllUserList();
      officelist = securityDAO.getAllOfficeList();
    } else {
      userlist = securityInfo.getUserList();
      officelist = securityInfo.getOfficeList();
    }
    if (username != null && username.length() > 0) {
      userBanklist = securityDAO.getBankListByUser(username);
      sparelist = securityDAO.getSpareBankListByUser(username, users);
    }
    List templist = new ArrayList();
    if (officecode != null && officecode.length() > 0) {
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        if (orgDto.getOfficeid().equals(officecode)) {
          templist.add(orgDto);
        }
      }
      sparelist = templist;
    }
    bankAssignUserAF.setUserBanklist(userBanklist);
    bankAssignUserAF.setSparelist(sparelist);
    bankAssignUserAF.setUserlist(userlist);
    bankAssignUserAF.setOfficelist(officelist);
    bankAssignUserAF.setUsername(username);
    bankAssignUserAF.setOfficename(officecode);
    return bankAssignUserAF;
  }

  public void addUserBank(String username, String bankid[])
      throws BusinessException {
    if (bankid == null || bankid.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < bankid.length; i++) {
        RelaUserAndCollBank relaUserAndCollBank = new RelaUserAndCollBank();
        relaUserAndCollBank.setCollBankId(bankid[i]);
        relaUserAndCollBank.setUsername(username);
        relaUserAndCollBankDAO.insert(relaUserAndCollBank);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void addAllUserBank(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    String users = securityInfo.getUserName();
    List sparelist = new ArrayList();
    String username = (String) pagination.getQueryCriterions().get("username");
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");
    try {
      if (username != null && username.length() > 0) {
        sparelist = securityDAO.getSpareBankListByUser(username, users);
      }
      List templist = new ArrayList();
      if (officecode != null && officecode.length() > 0) {
        for (int i = 0; i < sparelist.size(); i++) {
          OrgDto orgDto = (OrgDto) sparelist.get(i);
          if (orgDto.getOfficeid().equals(officecode)) {
            templist.add(orgDto);
          }
        }
        sparelist = templist;
      }
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        RelaUserAndCollBank relaUserAndCollBank = new RelaUserAndCollBank();
        relaUserAndCollBank.setCollBankId(orgDto.getBankid());
        relaUserAndCollBank.setUsername(username);
        relaUserAndCollBankDAO.insert(relaUserAndCollBank);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteUserBank(String username, String bankid[])
      throws BusinessException {
    if (bankid == null || bankid.length < 0)
      throw new BusinessException("撤消失败!");
    try {
      for (int i = 0; i < bankid.length; i++) {
        RelaUserAndCollBank relaUserAndCollBank = relaUserAndCollBankDAO
            .queryUserBank(username, bankid[i]);
        relaUserAndCollBankDAO.delete(relaUserAndCollBank);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void deleteAllUserBank(String username) throws BusinessException {
    List userBanklist = new ArrayList();
    try {
      userBanklist = securityDAO.getBankListByUser(username);
      for (int i = 0; i < userBanklist.size(); i++) {
        OrgDto orgDto = (OrgDto) userBanklist.get(i);
        RelaUserAndCollBank relaUserAndCollBank = relaUserAndCollBankDAO
            .queryUserBank(username, orgDto.getBankid());
        relaUserAndCollBankDAO.delete(relaUserAndCollBank);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("撤消失败!");
    }
  }

  public void addUserAssignOffice(String username, String office)
      throws BusinessException {
    RelaUserAndOffice relaUserAndOffice = relaUserAndOfficeDAO.queryUserOff(
        username, office);
    if (relaUserAndOffice.getId() != null) {
      throw new BusinessException("分配失败!已经拥有这个权限！");
    } else {
      RelaUserAndOffice ruo = new RelaUserAndOffice();
      ruo.setOffice(office);
      ruo.setUsername(username);
      relaUserAndOfficeDAO.insert(ruo);
    }
  }

  /**
   * 用户用户
   */
  public UserAssignUsersAF findUserUsers(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    UserAssignUsersAF userAssignUsersAF = new UserAssignUsersAF();
    String username = (String) pagination.getQueryCriterions().get("username");
    String users = securityInfo.getUserName();
    List sparelist = new ArrayList();
    List userAuserlist = new ArrayList();
    List userlist = new ArrayList();
    boolean b = securityDAO.isCenterMng(users);
    if (b) {
      userlist = securityDAO.getAllUserList();
    } else {
      userlist = securityInfo.getUserList();
    }
    if (username != null && username.length() > 0) {
      userAuserlist = securityDAO.getUserListByUser(username);
      sparelist = securityDAO.getSpareUserListByUser(username, users);
    }
    userAssignUsersAF.setUserlist(userlist);
    userAssignUsersAF.setUserAuserlist(userAuserlist);
    userAssignUsersAF.setSparelist(sparelist);
    userAssignUsersAF.setUsername(username);
    return userAssignUsersAF;
  }

  public void addUserUsers(String username, String subusername[])
      throws BusinessException {
    if (subusername == null || subusername.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < subusername.length; i++) {
        RelaUserAndUser relaUserAndUser = new RelaUserAndUser();
        relaUserAndUser.setSubusername(subusername[i]);
        relaUserAndUser.setUsername(username);
        relaUserAndUserDAO.insert(relaUserAndUser);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void addAllUserUsers(String username, SecurityInfo securityInfo)
      throws BusinessException {
    List sparelist = new ArrayList();
    String users = securityInfo.getUserName();
    try {
      if (username != null && username.length() > 0) {
        sparelist = securityDAO.getSpareUserListByUser(username, users);
      }
      for (int i = 0; i < sparelist.size(); i++) {
        OrgDto orgDto = (OrgDto) sparelist.get(i);
        RelaUserAndUser relaUserAndUser = new RelaUserAndUser();
        relaUserAndUser.setSubusername(orgDto.getUsername());
        relaUserAndUser.setUsername(username);
        relaUserAndUserDAO.insert(relaUserAndUser);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteUserUser(String username, String subusername[])
      throws BusinessException {
    if (subusername == null || subusername.length < 0)
      throw new BusinessException("撤消失败!");
    try {
      for (int i = 0; i < subusername.length; i++) {
        RelaUserAndUser relaUserAndUser = relaUserAndUserDAO.queryUserUsers(
            username, subusername[i]);
        relaUserAndUserDAO.delete(relaUserAndUser);
      }
    } catch (Exception e) {
      throw new BusinessException("撤消失败!");
    }
  }

  public void deleteAllUserUser(String username) throws BusinessException {
    List userUserlist = new ArrayList();
    try {
      userUserlist = securityDAO.getUserListByUser(username);
      for (int i = 0; i < userUserlist.size(); i++) {
        OrgDto orgDto = (OrgDto) userUserlist.get(i);
        RelaUserAndUser relaUserAndUser = relaUserAndUserDAO.queryUserUsers(
            username, orgDto.getUsername());
        relaUserAndUserDAO.delete(relaUserAndUser);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("撤消失败!");
    }
  }

  /**
   * 建立归集银行
   */
  public CollBankTaAF findBankListByUser(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    CollBankTaAF collBankTaAF = new CollBankTaAF();
    try {

      List collBanklist = new ArrayList();
      List officelist = new ArrayList();
      String username = securityInfo.getUserName();
      String officecode = (String) pagination.getQueryCriterions().get(
          "officecode");
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      boolean isCenterMng = securityDAO.isCenterMng(username);
      if (isCenterMng) {
        officelist = securityDAO.getAllOfficeList();
      } else {
        officelist = findOffice(username);
      }
      collBanklist = collBankDAO.getBankListByUser(username, isCenterMng,
          officecode, start, pageSize);
      List list = new ArrayList();
      if (collBanklist.size() > 0) {
        for (int i = 0; i < collBanklist.size(); i++) {
          OrgDto orgDto = (OrgDto) collBanklist.get(i);
          OrgDto dto = new OrgDto();
          dto.setBankStatus(BusiTools.getBusiValue(Integer.parseInt(orgDto
              .getBankStatus().toString()), BusiConst.COLLBANKSTATUS));
          dto.setBankid(orgDto.getBankid());
          dto.setBankname(orgDto.getBankname());
          dto.setOfficeid(orgDto.getOfficeid());
          dto.setOfficename(orgDto.getOfficename());
          //bit add----------
          dto.setCollectionbankacc(orgDto.getCollectionbankacc());
          dto.setContactprsn(orgDto.getContactprsn());
          dto.setContacttel(orgDto.getContacttel());
          dto.setCentername(orgDto.getCentername());
          //bit add----------
          list.add(dto);
        }
      }
      int count = collBankDAO.getBankListCountByUser(username, isCenterMng,
          officecode);
      pagination.setNrOfElements(count);
      collBankTaAF.setCollBanklist(list);
      collBankTaAF.setOfficelist(officelist);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBankTaAF;
  }

  /**
   * 添加归集银行
   */
  public void addCollBankInfo(String office, String collBankName,
      SecurityInfo securityInfo,String collectionbankacc,String contactprsn,String contacttel,String centername) throws BusinessException {
    try {
      CollBank collBank1 = collBankDAO.getCollBankByCollBankName(collBankName);
      if (collBank1 != null) {
        throw new BusinessException("该银行已存在，添加失败！");
      }
      int collBankId = collBankDAO.getMaxCollBankid();

      CollBank collBank = new CollBank();
      collBank.setCollBankId(new Integer(collBankId + 1));
      collBank.setCollBankName(collBankName);
      collBank.setOffice(office);
      collBank.setStatus(new Integer(1));
      //bit add--------------------
      collBank.setCollectionbankacc(collectionbankacc);
      collBank.setContactprsn(contactprsn);
      collBank.setContacttel(contacttel);
      collBank.setCentername(centername);
      //bit add--------------------
      Serializable id = collBankDAO.insertCollBank(collBank);
      //判断是否存在单位版
      int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          collBankDAODW.insertCollBankDW(id.toString(),collBank);    
        }
      }
      String username = securityInfo.getUserName();
      RelaUserAndCollBank relaUserAndCollBank = new RelaUserAndCollBank();
      relaUserAndCollBank.setUsername(username);
      relaUserAndCollBank.setCollBankId(id + "");
      relaUserAndCollBankDAO.insert(relaUserAndCollBank);
    }catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询要修改的归集银行
   */
  public CollBank findCollBank(String collBankid) {
    CollBank collBank = new CollBank();
    collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    return collBank;
  }

  /**
   * 修改归集银行
   */
  public void editCollBank(CollBankTaAF collBankTaAF,SecurityInfo securityInfo) throws BusinessException {
    String collBankid = collBankTaAF.getBankid();
    String collBankName = collBankTaAF.getBankname();
    String office = collBankTaAF.getOfficecode();
    //bit add---------------
    String collectionbankacc=collBankTaAF.getCollectionbankacc();
    String contactprsn=collBankTaAF.getContactprsn();
    String contacttel=collBankTaAF.getContacttel();
    String centername=collBankTaAF.getCentername();
    //bit add---------------
    CollBank collBank = null;
    try {
      CollBank collBank1 = collBankDAO.getCollBankByCollBankid(collBankid);
      if (!collBank1.getCollBankName().equals(collBankTaAF.getBankname())) {
        collBank = collBankDAO.getCollBankByCollBankName(collBankName);
      }
      if (collBank != null) {
        throw new BusinessException("该银行已存在，修改失败！");
      }
      CollBank collBank_org = null;
      //判断是否存在单位版
      int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          CollBank collBank11 = collBankDAODW.getCollBankByCollBankid(collBankid);
          if (!collBank11.getCollBankName().equals(collBankTaAF.getBankname())) {
            collBank_org = collBankDAODW.getCollBankByCollBankName(collBankName);
          }  
        }
      }
      if (collBank_org != null) {
        throw new BusinessException("该银行已存在与单位版中，修改失败！");
      }
      collBank = collBankDAO.getCollBankByCollBankid(collBankid);
      collBank.setCollBankName(collBankName);
      collBank.setOffice(office);
      //bit add-----------
      collBank.setCollectionbankacc(collectionbankacc);
      collBank.setContactprsn(contactprsn);
      collBank.setContacttel(contacttel);
      collBank.setCentername(centername);
      //bit add-----------
      //判断是否存在单位版
      if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
        int isCentEdition =securityInfo.getIsOrgEdition();
        //判断是否为中心版
        if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
          collBank_org = collBankDAODW.getCollBankByCollBankid(collBankid);
          collBank_org.setCollBankName(collBankName);
          collBank_org.setOffice(office);
        }
      }
    }catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 将归集银行状态置为作废
   * 
   * @param collBankid
   */
  public void cancelCollBank(String collBankid,SecurityInfo securityInfo) {
    CollBank collBank = null;
    collBank = collBankDAO.getCollBankByCollBankid_WL(collBankid, "1");
    collBank.setStatus(new Integer(2));
    CollBank collBank_org = null;
    //判断是否存在单位版
    int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
    if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
      int isCentEdition =securityInfo.getIsOrgEdition();
      //判断是否为中心版
      if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        collBank_org = collBankDAODW.getCollBankByCollBankid_WL(collBankid, "1");
        collBank_org.setStatus(new Integer(2));
      }
    }
  }

  /**
   * 将归集银行状态置为正常
   * 
   * @param collBankid
   */
  public void delCancelCollBank(String collBankid,SecurityInfo securityInfo) {
    CollBank collBank = null;
    collBank = collBankDAO.getCollBankByCollBankid_WL(collBankid, "2");
    collBank.setStatus(new Integer(1));
    CollBank collBank_org = null;
    //判断是否存在单位版
    int IsHaveOrgVersion =securityInfo.getIsHaveOrgVersion();
    if ( IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// 存在单位版
      int isCentEdition =securityInfo.getIsOrgEdition();
      //判断是否为中心版
      if ( isCentEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        collBank_org = collBankDAODW.getCollBankByCollBankid_WL(collBankid, "2");
        collBank_org.setStatus(new Integer(1));
      }
    }
  }

  /**
   * 用户分配账套
   */
  public UserAssignBookAF findBookUsers(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {

    UserAssignBookAF userAssignBookAF = new UserAssignBookAF();
    String username = (String) pagination.getQueryCriterions().get("username");
    List userbooklist = new ArrayList();
    List sparelist = new ArrayList();
    List usersList = new ArrayList();
    String users = securityInfo.getUserName();
    boolean b = securityDAO.isCenterMng(users);
    if (b) {
      usersList = securityDAO.getAllUserList();
    } else {
      usersList = securityInfo.getUserList();
    }
    if (username != null && username.length() > 0) {
      userbooklist = relaUserAndBookDAO.queryBookByUsername(username);
      sparelist = relaUserAndBookDAO.querySpareBookByUsername(username,
          securityInfo, b);
    }

    userAssignBookAF.setUsersList(usersList);
    userAssignBookAF.setUserbooklist(userbooklist);
    userAssignBookAF.setSparelist(sparelist);
    userAssignBookAF.setUsername(username);

    return userAssignBookAF;
  }

  public void addUserBook(String username, String bookid[])
      throws BusinessException {
    if (bookid == null || bookid.length < 0)
      throw new BusinessException("分配失败!");
    try {
      for (int i = 0; i < bookid.length; i++) {
        RelaUserAndBook ruo = new RelaUserAndBook();
        ruo.setUsername(username);
        ruo.setBookId(bookid[i]);
        String bizDate = relaUserAndBookDAO.getFBizDate(bookid[i]);
        if(bizDate == null) 
          bizDate = bookDAO.getUseYearmonth(bookid[i]);
        ruo.setBizDate(bizDate);
        relaUserAndBookDAO.insert(ruo);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }
  public void addAllUserBook(String username, SecurityInfo securityInfo)
      throws BusinessException {
    List sparelist = new ArrayList();
    String users = securityInfo.getUserName();
    try {
      if (username != null && username.length() > 0) {
        sparelist = securityDAO.getSpareUserBookList(username, users);
      }
      for (int i = 0; i < sparelist.size(); i++) {
        UserAssignBookDTO userAssignBookDTO = (UserAssignBookDTO) sparelist
            .get(i);
        RelaUserAndBook ruo = new RelaUserAndBook();
        ruo.setBookId(userAssignBookDTO.getBookid());
        ruo.setUsername(username);
        String bizDate = relaUserAndBookDAO.getFBizDate(userAssignBookDTO.getBookid());
        if(bizDate == null) 
          bizDate = bookDAO.getUseYearmonth(userAssignBookDTO.getBookid());
        ruo.setBizDate(bizDate);
        relaUserAndBookDAO.insert(ruo);
      }
    } catch (Exception e) {
      throw new BusinessException("分配失败!");
    }
  }

  public void deleteUserBook(String username, String bookid[])
      throws BusinessException {
    if (bookid == null || bookid.length < 0)
      throw new BusinessException("作废失败!");
    try {
      for (int i = 0; i < bookid.length; i++) {
        RelaUserAndBook relaUserAndBook = relaUserAndBookDAO.queryUserOff(
            username, bookid[i]);
        relaUserAndBookDAO.delete(relaUserAndBook);
      }
    } catch (Exception e) {
      throw new BusinessException("作废失败!");
    }
  }

  public void deleteAllUserBook(String username, SecurityInfo securityInfo)
      throws BusinessException {
    List userBooklist = new ArrayList();
    try {
      userBooklist = relaUserAndBookDAO.queryBookByUsername(username);
      for (int i = 0; i < userBooklist.size(); i++) {
        UserAssignBookDTO userAssignBookDTO = (UserAssignBookDTO) userBooklist
            .get(i);
        RelaUserAndBook relaUserAndBook = relaUserAndBookDAO.queryUserOff(
            username, userAssignBookDTO.getBookid());
        relaUserAndBookDAO.delete(relaUserAndBook);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("作废失败!");
    }
  }
  public void setNull(String id) throws Exception {
    try {
      if (id != null && id.length() > 0) {
        securityDAO.setNull(id);
      }

    } catch (Exception e) {
      throw new BusinessException("失败!");
    }

  }

}