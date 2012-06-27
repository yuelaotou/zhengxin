package org.xpup.hafmis.syscollection.accountmng.accountchg.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountchg.bsinterface.IOrgChgBS;
import org.xpup.hafmis.syscollection.accountmng.accountchg.dto.OrgChgDTO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgChgLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLog;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;
import org.xpup.hafmis.syscommon.domain.entity.Transactor;
import org.xpup.hafmis.common.util.BusiConst;

public class OrgChgBS implements IOrgChgBS {
  protected OrgDAO orgDAO = null;

  protected OrgChgLogDAO orgChgLogDAO = null;

  protected CollBankDAO collBankDAO = null;

  protected HafOperateLogDAO hafOperateLogDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  protected ChgOrgBizActivityLogDAO chgOrgBizActivityLogDAO = null;

  public Org findOrgChgById(Integer id, SecurityInfo securityInfo)
      throws BusinessException {
    Org org = orgDAO.findById(id, securityInfo);
    return org;

  }

  public Org findOrgChgById(Integer id) throws BusinessException {
    Org org = orgDAO.queryById(id);
    return org;

  }

  public OrgChgDTO getOrgChgById(Integer id) throws BusinessException {
    OrgChgDTO orgChgDTO = new OrgChgDTO();
    OrgChgLog orgChgLog = orgChgLogDAO.queryById(id);

    orgChgDTO.setOrgname(orgChgLog.getOrg().getOrgInfo().getName());
    orgChgDTO.setOrgid(orgChgLog.getOrg().getId().toString());
    orgChgDTO.setOrgaddr(orgChgLog.getOrg().getOrgInfo().getAddress());
    orgChgDTO.setManager(orgChgLog.getOrg().getOrgInfo().getArtificialPerson());
    orgChgDTO.setOrgtel(orgChgLog.getOrg().getOrgInfo().getTel());
    try {
      orgChgDTO.setOrgtype(BusiTools.getBusiValue(Integer.parseInt(orgChgLog
          .getOrg().getOrgInfo().getCharacter()), BusiConst.NATUREOFUNITS));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (orgChgLog.getOrg().getOrgInfo().getTransactor() != null) {
      orgChgDTO.setOperatorname(orgChgLog.getOrg().getOrgInfo().getTransactor()
          .getName());
      orgChgDTO.setOperatortel(orgChgLog.getOrg().getOrgInfo().getTransactor()
          .getTelephone()
          + "/"
          + orgChgLog.getOrg().getOrgInfo().getTransactor()
              .getMobileTelephone());
    }
    orgChgDTO.setOrgrate(orgChgLog.getOrg().getOrgRate().toString());
    orgChgDTO.setBalance(orgDAO.queryEmpBalance(new Integer(orgChgLog.getOrg()
        .getId().toString())));
    orgChgDTO.setEmpnum(orgDAO.queryEmpNum(new Integer(orgChgLog.getOrg()
        .getId().toString())));
    CollBank dto = collBankDAO.getCollBankByCollBankid(orgChgLog.getOrg()
        .getOrgInfo().getCollectionBankId());
    orgChgDTO.setBankname(dto.getCollBankName());
    if (orgChgLog.getChgType().equals("C")) {
      orgChgDTO.setChgreason("封存");
    } else {
      orgChgDTO.setChgreason("销户");
    }
    orgChgDTO.setStoptime(BusiTools.dateToString(orgChgLog.getOpTime(),
        BusiConst.PUB_LONG_YMD_PATTERN));
    orgChgDTO.setOptime(BusiTools.dateToString(orgChgLog.getOpTime(),
        BusiConst.PUB_LONG_YMD_PATTERN));
    return orgChgDTO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  /*
   * 办理变更(non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.accountmng.accountchg.bsinterface.IOrgChgBS#saveOrgChg(org.xpup.hafmis.syscollection.common.domain.entity.Org,
   *      java.lang.String)
   */
  public void saveOrgChg(String id, String chgType, SecurityInfo securityInfo)
      throws Exception {
    OrgChgLog orgChgLog = new OrgChgLog();
    Org org = orgDAO.queryById(new Integer(id));
    // 更新BA001中的内容
    String openstatus = org.getOrgInfo().getOpenstatus();
    char c = chgType.charAt(0);
    switch (c) {
    case 'B':
      openstatus = "3";
      break;
    case 'A':
      openstatus = "2";
      break;
    case 'C':
      openstatus = "4";
      break;
    }
    org.getOrgInfo().setOpenstatus(openstatus);
    orgDAO.update(org);
    // 插入AA003
    orgChgLog.setOrg(org);
    // 变更类型与变更状态转换
    char ct = chgType.charAt(0);
    switch (ct) {
    case 'B':
      chgType = "C";
      break;
    case 'A':
      chgType = "B";
      break;
    case 'C':
      chgType = "D";
      break;
    }
    orgChgLog.setChgType(chgType);
    orgChgLog.setOpTime(new Date());
    orgChgLog.setOperator(securityInfo.getUserName());
    Serializable chgid = orgChgLogDAO.insert(orgChgLog);

    ChgOrgBizActivityLog log = new ChgOrgBizActivityLog();
    if (org.getId() != null && !org.getId().equals("")) {
      log.setBizid(new Integer(org.getId().toString()));
    }
    log.setAction(new Integer(3));
    // 转化日期格式
    log.setOpTime(new Date());
    log.setOperator(securityInfo.getUserName());
    chgOrgBizActivityLogDAO.insert(log);
    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGCHG_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_OPENING);
    hafOperateLog.setOpBizId(new Integer(chgid.toString()));
    String ip = securityInfo.getUserInfo().getUserIp();
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(org.getId().toString()));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);

  }

  /*
   * 变更维护列表
   */
  public List findOrgChgList(Pagination pagination) throws BusinessException {
    List orgs = null;
    String id = (String) pagination.getQueryCriterions().get("id");
    if (id != null && id.length() == 10) {
      id = id.substring(1, id.length());
    }
    String name = (String) pagination.getQueryCriterions().get("name");
    String chgType = (String) pagination.getQueryCriterions().get("chgtype");
    String temp_Type = (String) pagination.getQueryCriterions()
        .get("temp_Type");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgChgLog orgChgLog = new OrgChgLog();
    orgs = orgChgLogDAO.queryOrgChgSL(id, name, chgType, orderBy, order, start,
        pageSize, temp_Type);
    for (int i = 0; i < orgs.size(); i++) {
      orgChgLog = (OrgChgLog) orgs.get(i);
      try {
        if (orgChgLog.getOrg().getOrgInfo().getTransactor() == null) {
          Transactor transactor = new Transactor();
          String transactorname = transactor.getName();
          String email = transactor.getEmail();
          String telephone = transactor.getTelephone();
          String mobileTelephone = transactor.getMobileTelephone();
          orgChgLog.getOrg().getOrgInfo().setTransactor(transactor);
          orgChgLog.getOrg().getOrgInfo().getTransactor().setName(
              transactorname);
          orgChgLog.getOrg().getOrgInfo().getTransactor().setEmail(email);
          orgChgLog.getOrg().getOrgInfo().getTransactor().setTelephone(
              telephone);
          orgChgLog.getOrg().getOrgInfo().getTransactor().setMobileTelephone(
              mobileTelephone);
        }
        orgChgLog.setTemp_type(BusiTools.getBusiValue_WL(
            orgChgLog.getChgType(), BusiConst.ORGCHGSTRUTS));
      } catch (NumberFormatException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    int count = orgChgLogDAO.queryCountOrgChgSL(id, name, chgType, temp_Type);
    pagination.setNrOfElements(count);
    return orgs;
  }

  /**
   * 由单位ID查询变更
   * 
   * @param orgDAO
   */
  public OrgChgLog findOrgChg(String id) throws BusinessException {
    OrgChgLog orgChgLog = new OrgChgLog();
    orgChgLog = orgChgLogDAO.queryOrgChgSLById(id);
    return orgChgLog;

  }

  /**
   * 查询办事处和银行
   * 
   * @param orgDAO
   */
  public OrgInfo getCollBankAndOffice(String id, String officecode)
      throws BusinessException {
    OrgInfo orgInfo = new OrgInfo();
    OrganizationUnit organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(officecode);
    orgInfo.setTemp_officename(organizationUnit.getName());
    CollBank dto = collBankDAO.getCollBankByCollBankid(id);

    orgInfo.setTemp_collectionBankname(dto.getCollBankName());
    return orgInfo;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setOrgChgLogDAO(OrgChgLogDAO orgChgLogDAO) {
    this.orgChgLogDAO = orgChgLogDAO;
  }

  public void setChgOrgBizActivityLogDAO(
      ChgOrgBizActivityLogDAO chgOrgBizActivityLogDAO) {
    this.chgOrgBizActivityLogDAO = chgOrgBizActivityLogDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
}
