package org.xpup.hafmis.orgstrct.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.hafmis.orgstrct.domain.HafEmployee;
/**
 * 权限信息
 * @author 刘洋
 *
 */
public class SecurityInfo implements Serializable{

  private static final long serialVersionUID = 8241107375146934274L;

  //登录名称
  private String userName="";
  
  //登录人的真实姓名
  private String realName="";
  
  //登录IP
  private String userIp="";
  
  //用户管理办事处权限HqlSQL
  private String officeSecurityHqlSQL="";
  
//用户管理办事处权限SQL
  private String officeSecuritySQL="";
  
  //公积金权限SQL
  private String gjjSecuritySQL="";
  
  //公积金权限HqlSQL
  private String gjjSecurityHqlSQL="";
  
  //贷款权限SQL
  private String dkSecuritySQL="";
  
  //贷款权限HqlSQL
  private String dkSecurityHqlSQL="";
  
  //业务日期
  private String bizDate="";
  
  //角色ID
  private String roleId="";
  
  //登录人所属办事处
  private OfficeDto officeDto=new OfficeDto();
  
  //用户权限权限办事处
  private List officeList=new ArrayList();
  
  //所有办事处
  private List allOfficeList=new ArrayList();
  
  //所有中心
  private List allCenterList=new ArrayList();
  
  //用户权限下用户
  private List userList=new ArrayList();
  
  //所有用户
  private List allUserList=new ArrayList();
  
  //用户下归集行
  private List collBankList = new ArrayList();

  private HafEmployee UserInfo=new HafEmployee();
  //办事处内部代码MAP
  private Map officeInnerCodeMap=new HashMap();
  
  //贷款还款类型
  private int plLoanReturnType=999;
  
  //版本类型 是否为单位版
  private int isOrgEdition=999;
  
  //取得贷款权限HqlSql控制到操作员
  private String dkUserSecurityHqlSQL="";
  
  //取得贷款权限Sql控制到操作员
  private String dkUserSecuritySQL="";
  
  //贷款权限银行List
  private List dkUserBankList=new ArrayList();
  
  //用户权限账套
  private List userBookList=new ArrayList();
  
  private int fnSettleType=999;
  
  private String bookId="";
  
  //财政代扣凭证模式 
  private int AgentPayModel=999;
  
  //公积金还贷凭证模式
  private int LoanBackByCollModel=999;
  
  //得到是否存在单位版
  private int isHaveOrgVersion=999;
  
  //得到是否启用邮件功能
  private int mailFunction=999;
  
  //系统类型
  private String opSystemType="";
  
  //取得CA200中的邮件信息
  private MailMessageDTO maildto=new MailMessageDTO();
  
  public MailMessageDTO getMaildto() {
    return maildto;
  }

  public void setMaildto(MailMessageDTO maildto) {
    this.maildto = maildto;
  }

  public int getLoanBackByCollModel() {
    return LoanBackByCollModel;
  }

  public void setLoanBackByCollModel(int loanBackByCollModel) {
    LoanBackByCollModel = loanBackByCollModel;
  }

  public int getAgentPayModel() {
    return AgentPayModel;
  }

  public void setAgentPayModel(int agentPayModel) {
    AgentPayModel = agentPayModel;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public int getFnSettleType() {
    return fnSettleType;
  }

  public void setFnSettleType(int fnSettleType) {
    this.fnSettleType = fnSettleType;
  }

  public List getUserBookList() {
    return userBookList;
  }

  public void setUserBookList(List userBookList) {
    this.userBookList = userBookList;
  }

  public List getDkUserBankList() {
    return dkUserBankList;
  }

  public void setDkUserBankList(List dkUserBankList) {
    this.dkUserBankList = dkUserBankList;
  }

  public int getPlLoanReturnType() {
    return plLoanReturnType;
  }

  public void setPlLoanReturnType(int plLoanReturnType) {
    this.plLoanReturnType = plLoanReturnType;
  }

  public List getAllOfficeList() {
    return allOfficeList;
  }

  public void setAllOfficeList(List allOfficeList) {
    this.allOfficeList = allOfficeList;
  }

  public List getAllUserList() {
    return allUserList;
  }

  public void setAllUserList(List allUserList) {
    this.allUserList = allUserList;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getGjjSecuritySQL() {
    return gjjSecuritySQL;
  }

  public void setGjjSecuritySQL(String gjjSecuritySQL) {
    this.gjjSecuritySQL = gjjSecuritySQL;
  }

  public OfficeDto getOfficeDto() {
    return officeDto;
  }

  public void setOfficeDto(OfficeDto officeDto) {
    this.officeDto = officeDto;
  }

  public List getOfficeList() {
    return officeList;
  }

  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getUserIp() {
    return userIp;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }

  public List getUserList() {
    return userList;
  }

  public void setUserList(List userList) {
    this.userList = userList;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public HafEmployee getUserInfo() {
    return UserInfo;
  }

  public void setUserInfo(HafEmployee userInfo) {
    UserInfo = userInfo;
  }

  
  public String getOfficeSecurityHqlSQL() {
    return officeSecurityHqlSQL;
  }

  public void setOfficeSecurityHqlSQL(String officeSecurityHqlSQL) {
    this.officeSecurityHqlSQL = officeSecurityHqlSQL;
  }

  public String getOfficeSecuritySQL() {
    return officeSecuritySQL;
  }

  public void setOfficeSecuritySQL(String officeSecuritySQL) {
    this.officeSecuritySQL = officeSecuritySQL;
  }

  public String getGjjSecurityHqlSQL() {
    return gjjSecurityHqlSQL;
  }

  public void setGjjSecurityHqlSQL(String gjjSecurityHqlSQL) {
    this.gjjSecurityHqlSQL = gjjSecurityHqlSQL;
  }

  public List getCollBankList() {
    return collBankList;
  }

  public void setCollBankList(List collBankList) {
    this.collBankList = collBankList;
  }

  public List getAllCenterList() {
    return allCenterList;
  }

  public void setAllCenterList(List allCenterList) {
    this.allCenterList = allCenterList;
  }

  public String getDkSecurityHqlSQL() {
    return dkSecurityHqlSQL;
  }

  public void setDkSecurityHqlSQL(String dkSecurityHqlSQL) {
    this.dkSecurityHqlSQL = dkSecurityHqlSQL;
  }

  public String getDkSecuritySQL() {
    return dkSecuritySQL;
  }

  public void setDkSecuritySQL(String dkSecuritySQL) {
    this.dkSecuritySQL = dkSecuritySQL;
  }

  public Map getOfficeInnerCodeMap() {
    return officeInnerCodeMap;
  }

  public void setOfficeInnerCodeMap(Map officeInnerCodeMap) {
    this.officeInnerCodeMap = officeInnerCodeMap;
  }

  public String getDkUserSecurityHqlSQL() {
    return dkUserSecurityHqlSQL;
  }

  public void setDkUserSecurityHqlSQL(String dkUserSecurityHqlSQL) {
    this.dkUserSecurityHqlSQL = dkUserSecurityHqlSQL;
  }

  public String getDkUserSecuritySQL() {
    return dkUserSecuritySQL;
  }

  public void setDkUserSecuritySQL(String dkUserSecuritySQL) {
    this.dkUserSecuritySQL = dkUserSecuritySQL;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getOpSystemType() {
    return opSystemType;
  }

  public void setOpSystemType(String opSystemType) {
    this.opSystemType = opSystemType;
  }

  public int getIsOrgEdition() {
    return isOrgEdition;
  }

  public void setIsOrgEdition(int isOrgEdition) {
    this.isOrgEdition = isOrgEdition;
  }

  public int getIsHaveOrgVersion() {
    return isHaveOrgVersion;
  }

  public void setIsHaveOrgVersion(int isHaveOrgVersion) {
    this.isHaveOrgVersion = isHaveOrgVersion;
  }

  public int getMailFunction() {
    return mailFunction;
  }

  public void setMailFunction(int mailFunction) {
    this.mailFunction = mailFunction;
  }

}
