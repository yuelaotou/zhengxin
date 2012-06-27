package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonTailDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.bsinterface.IChgpersonOrgBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonEmpHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonOrgHeadDTO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * @author 王玲 2007-6-27
 */
public class ChgpersonOrgBS implements IChgpersonOrgBS {

  private ChgPersonHeadDAO chgPersonHeadDAO = null;
  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  private CollBankDAO collBankDAO = null;
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  private OrganizationUnitDAO organizationUnitDAO = null;
  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  private ChgPersonTailDAO chgPersonTailDAO = null;
  public void setChgPersonTailDAO(ChgPersonTailDAO chgPersonTailDAO) {
    this.chgPersonTailDAO = chgPersonTailDAO;
  }

  /**
   * 根据条件查询人员变更记录
   */
  public List findChgpersonOrgListByCriterions(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException {

    List list = new ArrayList();

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    int page = pagination.getPage();

    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");// 办事处
    String collectionBank = (String) pagination.getQueryCriterions().get(
        "collectionBank");// 归集银行
    String orgId = (String) pagination.getQueryCriterions().get("orgId");// 单位编号
    String orgName = (String) pagination.getQueryCriterions().get("orgName");// 单位名称
    String chgMonthStart = (String) pagination.getQueryCriterions().get(
        "chgMonthStart");// 年月开始
    String chgMonthEnd = (String) pagination.getQueryCriterions().get(
        "chgMonthEnd");// 年月结束
    String chgDateStart = (String) pagination.getQueryCriterions().get(
        "chgDateStart");// 日期开始
    String chgDateEnd = (String) pagination.getQueryCriterions().get(
        "chgDateEnd");// 日期结束
    Integer chgStatus = (Integer) pagination.getQueryCriterions().get(
        "chgStatus");// 业务状态

    list = chgPersonHeadDAO.queryChgpersonOrgListByCriterions_LY(officeCode,
        collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
        chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,page,securityInfo);
    ChgPersonHead chgPersonHead = null;
    for (int i = 0; i < list.size(); i++) {
      chgPersonHead = (ChgPersonHead) list.get(i);
      String tempid = chgPersonHead.getOrg().getId().toString();
      String orgid =  BusiTools.convertTenNumber(tempid);
      chgPersonHead.getOrg().setId(orgid);
      //OrgInfo org=chgPersonHead.getOrg().getOrgInfo();
      // 转换
      //OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(chgPersonHead.getOrg().getOrgInfo().getOfficecode());
      //String officename=organizationUnitDAO.queryOrganizationName_LY(org.getOfficecode());
      //chgPersonHead.getOrg().getOrgInfo().setTemp_officename(officename);
      //CollBank dto=collBankDAO.getCollBankByCollBankid(org.getCollectionBankId());      
      //chgPersonHead.getOrg().getOrgInfo().setTemp_collectionBankname(dto.getCollBankName());
      
      //chgPersonHead.setTemp_chgStatus(BusiTools.getBusiValue(Integer
          //.parseInt(chgPersonHead.getChgStatus().toString()),
         // BusiConst.CHGTYPESTATUS));
      
      pagination.getQueryCriterions().put("flowid", chgPersonHead.getId().toString());
      ChgPersonHeadFormule chgPersonHeadFormule = this.findChgpersonHeadByCriterions(pagination,securityInfo);//查询合计项
      chgPersonHead.setSumChgPerson(chgPersonHeadFormule.getSumChgPerson());
      chgPersonHead.setOldOldPayment(chgPersonHeadFormule.getOldOldPayment());
      chgPersonHead.setInsPayment(chgPersonHeadFormule.getInsPayment());
      chgPersonHead.setMulPayment(chgPersonHeadFormule.getMulPayment());
      
      chgPersonHead.setNewOldPayment(chgPersonHead.getOldOldPayment().add(
          chgPersonHead.getInsPayment())
          .subtract(chgPersonHead.getMulPayment()));
      
      BigDecimal percentage = new BigDecimal(100.00);
      if(chgPersonHead.getOldOldPayment().toString().equals("0")){
        chgPersonHead.setRate(new BigDecimal(1.00).divide(new BigDecimal(1.00), 4, BigDecimal.ROUND_HALF_UP).multiply(percentage).divide(new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP));
      }else{
        chgPersonHead.setRate(chgPersonHead.getNewOldPayment().subtract(chgPersonHead.getOldOldPayment()).divide(chgPersonHead.getOldOldPayment(), 4, BigDecimal.ROUND_HALF_UP).multiply(percentage).divide(new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP));
      }        
      chgPersonHead.setTemp_rate(chgPersonHead.getRate().toString()+"%");
    }

    int count = chgPersonHeadDAO.queryChgpersonOrgCountByCriterions_WL(
        officeCode, collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
        chgDateStart, chgDateEnd, chgStatus,securityInfo);
    pagination.setNrOfElements(count);

    return list;
  }

  /**
   * 根据条件查询人员变更记录合计项1
   */
  public ChgPersonHeadFormule findChgpersonHeadByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    ChgPersonHeadFormule dto = null;

    String id = (String) pagination.getQueryCriterions().get("flowid");
    dto = chgPersonHeadDAO.findChgpersonHeadFormuleByCriterions_WL(id, securityInfo);
    if (dto != null) {
      dto.setNewOldPayment(dto.getOldOldPayment().add(dto.getInsPayment())
          .subtract(dto.getMulPayment()));
    }

    return dto;
  }
  
  /**
   * 根据条件查询人员变更记录单位的合计
   */
  public ChgpersonOrgHeadDTO findChgpersonOrgHeadByCriterions(
      Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException {

    List list = new ArrayList();

    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");// 办事处
    String collectionBank = (String) pagination.getQueryCriterions().get(
        "collectionBank");// 归集银行
    String orgId = (String) pagination.getQueryCriterions().get("orgId");// 单位编号
    String orgName = (String) pagination.getQueryCriterions().get("orgName");// 单位名称
    String chgMonthStart = (String) pagination.getQueryCriterions().get(
        "chgMonthStart");// 年月开始
    String chgMonthEnd = (String) pagination.getQueryCriterions().get(
        "chgMonthEnd");// 年月结束
    String chgDateStart = (String) pagination.getQueryCriterions().get(
        "chgDateStart");// 日期开始
    String chgDateEnd = (String) pagination.getQueryCriterions().get(
        "chgDateEnd");// 日期结束
    Integer chgStatus = (Integer) pagination.getQueryCriterions().get(
        "chgStatus");// 业务状态

    list = chgPersonHeadDAO.queryChgpersonOrgHeadByCriterions_WL(officeCode,
        collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
        chgDateStart, chgDateEnd, chgStatus,securityInfo);

    // 根据条件查询人员变更记录单位的合计
    ChgPersonHead chgPersonHead = null;
    ChgpersonOrgHeadDTO dto = new ChgpersonOrgHeadDTO();
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        chgPersonHead = new ChgPersonHead();
        chgPersonHead = (ChgPersonHead) list.get(i);
        
        pagination.getQueryCriterions().put("flowid", chgPersonHead.getId().toString());
        ChgPersonHeadFormule chgPersonHeadFormule = this.findChgpersonHeadByCriterions(pagination,securityInfo);//查询合计项

        dto.setPreSumPay(dto.getPreSumPay().add(
            chgPersonHeadFormule.getOldOldPayment()));// 变更前总缴额
        dto.setSumPay(dto.getSumPay().add(chgPersonHeadFormule.getOldOldPayment()).add(chgPersonHeadFormule.getInsPayment())
            .subtract(chgPersonHeadFormule.getMulPayment()));// 变更后总缴额
      }
      dto.setOrgCount(new Integer(list.size()));// 变更单位数
    }

    return dto;
  }
  
  /**
   * 查询职工列表信息
   */
  public List findChgpersonEmpListByCriterions(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException {

    List list = new ArrayList();

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    int page = pagination.getPage();

    String orgId = (String) pagination.getQueryCriterions().get("orgId");// 单位编号
    String orgName = (String) pagination.getQueryCriterions().get("orgName");// 单位名称
    String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
    String empName = (String) pagination.getQueryCriterions().get("empName");// 职工名称
    String chgMonthStart = (String) pagination.getQueryCriterions().get(
        "chgMonthStart");// 年月开始
    String chgMonthEnd = (String) pagination.getQueryCriterions().get(
        "chgMonthEnd");// 年月结束
    String chgDateStart = (String) pagination.getQueryCriterions().get(
        "chgDateStart");// 日期开始
    String chgDateEnd = (String) pagination.getQueryCriterions().get(
        "chgDateEnd");// 日期结束

    list = chgPersonTailDAO.queryChgpersonEmpListByCriterions_WL(orgId,
        orgName, empId, empName, chgMonthStart, chgMonthEnd, chgDateStart,
        chgDateEnd, start, pageSize, orderBy, order,page,securityInfo);
    ChgPersonTail chgPersonTail = null;
    for (int i = 0; i < list.size(); i++) {
      chgPersonTail = (ChgPersonTail) list.get(i);
      // 转换
      chgPersonTail.getChgPersonHead().getOrg().getOrgInfo().getName();
      chgPersonTail.setTemp_oldPayStatus(BusiTools.getBusiValue(Integer
          .parseInt(chgPersonTail.getOldPayStatus().toString()),
          BusiConst.OLDPAYMENTSTATE));
      chgPersonTail.setTemp_newPayStatus(BusiTools.getBusiValue(Integer
          .parseInt(chgPersonTail.getNewPayStatus().toString()),
          BusiConst.OLDPAYMENTSTATE));
      if(chgPersonTail.getChgreason()!=null&&!chgPersonTail.getChgreason().equals("")){
        chgPersonTail.setTemp_chgreason(BusiTools.getBusiValue(Integer
            .parseInt(chgPersonTail.getChgreason()),
            BusiConst.CHGPERSONREASON));
      }
    }
    
    

    int count = chgPersonTailDAO.queryChgpersonEmpCountByCriterions_WL(orgId,
        orgName, empId, empName,chgMonthStart, chgMonthEnd, chgDateStart,
        chgDateEnd,securityInfo);
    pagination.setNrOfElements(count);

    return list;
  }

  /**
   * 根据头表ID条件查询人员变更职工记录
   */
  public List findChgpersonEmpListByChgpersonHeadID(Pagination pagination, String chgpersonHeadID,SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = new ArrayList();

    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    int page = pagination.getPage();

    list = chgPersonTailDAO.queryChgpersonEmpListByChgpersonHeadID_WL(chgpersonHeadID, start, pageSize, orderBy, order,page,securityInfo);
    ChgPersonTail chgPersonTail = null;
    for (int i = 0; i < list.size(); i++) {
      chgPersonTail = (ChgPersonTail) list.get(i);
      // 转换
      chgPersonTail.getChgPersonHead().getOrg().getOrgInfo().getName();
      chgPersonTail.setTemp_oldPayStatus(BusiTools.getBusiValue(Integer
          .parseInt(chgPersonTail.getOldPayStatus().toString()),
          BusiConst.OLDPAYMENTSTATE));
      chgPersonTail.setTemp_newPayStatus(BusiTools.getBusiValue(Integer
          .parseInt(chgPersonTail.getNewPayStatus().toString()),
          BusiConst.OLDPAYMENTSTATE));
      if(chgPersonTail.getChgreason()!=null&&!chgPersonTail.getChgreason().equals("")){
        chgPersonTail.setTemp_chgreason(BusiTools.getBusiValue(Integer
            .parseInt(chgPersonTail.getChgreason()),
            BusiConst.CHGPERSONREASON));
      }
    }
    
    

    int count = chgPersonTailDAO.queryChgpersonEmpCountByChgpersonHeadID_WL(chgpersonHeadID,securityInfo);
    pagination.setNrOfElements(count);

    return list;
  }
  
  
  /**
   * 计职工列表的合计信息
   */
  public ChgpersonEmpHeadDTO findChgpersonEmpHeadByCriterions(List list)
      throws Exception, BusinessException {
    
    ChgpersonEmpHeadDTO chgpersonEmpHeadDTO=new ChgpersonEmpHeadDTO();
    ChgPersonTail chgPersonTail = null;
    
    for(int i=0;i<list.size();i++){
      chgPersonTail=(ChgPersonTail)list.get(i);
      if(chgPersonTail.getChgType().equals("1")||chgPersonTail.getChgType().equals("3")){
        chgpersonEmpHeadDTO.setInsCount(chgpersonEmpHeadDTO.getInsCount()+1);
        chgpersonEmpHeadDTO.setInsPayment(chgpersonEmpHeadDTO.getInsPayment().add(chgPersonTail.getOrgPay().add(chgPersonTail.getEmpPay())));
      }else if(chgPersonTail.getChgType().equals("4")){
        chgpersonEmpHeadDTO.setMulCount(chgpersonEmpHeadDTO.getMulCount()+1);
        chgpersonEmpHeadDTO.setMulPayment(chgpersonEmpHeadDTO.getMulPayment().add(chgPersonTail.getOrgPay().add(chgPersonTail.getEmpPay())));
      }
      chgpersonEmpHeadDTO.setOrgPayment(chgpersonEmpHeadDTO.getOrgPayment().add(chgPersonTail.getOrgPay()));
      chgpersonEmpHeadDTO.setEmpPayment(chgpersonEmpHeadDTO.getEmpPayment().add(chgPersonTail.getEmpPay()));
      chgpersonEmpHeadDTO.setSumPayment(chgpersonEmpHeadDTO.getOrgPayment().add(chgpersonEmpHeadDTO.getEmpPayment()));
      
    }
    chgpersonEmpHeadDTO.setSumCount(list.size());
    
    return chgpersonEmpHeadDTO;
  }

  
  /**
   * 打印单位信息
   */
  public ChgHeadDTO queryChgpersonOrgListAll(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    ChgHeadDTO chgHeadDTO = new ChgHeadDTO();
    List returnList = new ArrayList();
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");// 办事处
    String collectionBank = (String) pagination.getQueryCriterions().get(
        "collectionBank");// 归集银行
    String orgId = (String) pagination.getQueryCriterions().get("orgId");// 单位编号
    String orgName = (String) pagination.getQueryCriterions().get("orgName");// 单位名称
    String chgMonthStart = (String) pagination.getQueryCriterions().get(
        "chgMonthStart");// 年月开始
    String chgMonthEnd = (String) pagination.getQueryCriterions().get(
        "chgMonthEnd");// 年月结束
    String chgDateStart = (String) pagination.getQueryCriterions().get(
        "chgDateStart");// 日期开始
    String chgDateEnd = (String) pagination.getQueryCriterions().get(
        "chgDateEnd");// 日期结束
    Integer chgStatus = (Integer) pagination.getQueryCriterions().get(
        "chgStatus");// 业务状态

    List list = chgPersonHeadDAO.queryChgpersonOrgAllListByCriterions_LY(officeCode,
        collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
        chgDateStart, chgDateEnd, chgStatus,securityInfo);
    ChgPersonHead chgPersonHead = null;
    ChgpersonOrgHeadDTO dto=new ChgpersonOrgHeadDTO();
    for (int i = 0; i < list.size(); i++) {
      chgPersonHead = (ChgPersonHead) list.get(i);
//      // 转换
//      OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(chgPersonHead.getOrg().getOrgInfo().getOfficecode());
//      chgPersonHead.getOrg().getOrgInfo().setTemp_officename(organizationUnit.getName());
//      CollBank dto=collBankDAO.getCollBankByCollBankid(chgPersonHead.getOrg().getOrgInfo().getCollectionBankId());
//      
//      chgPersonHead.getOrg().getOrgInfo().setTemp_collectionBankname(dto.getCollBankName());
//      
//      chgPersonHead.setTemp_chgStatus(BusiTools.getBusiValue(Integer
//          .parseInt(chgPersonHead.getChgStatus().toString()),
//          BusiConst.CHGTYPESTATUS));
      
      pagination.getQueryCriterions().put("flowid", chgPersonHead.getId().toString());
      ChgPersonHeadFormule chgPersonHeadFormule = this.findChgpersonHeadByCriterions(pagination,securityInfo);//查询合计项
      chgPersonHead.setSumChgPerson(chgPersonHeadFormule.getSumChgPerson());
      chgPersonHead.setOldOldPayment(chgPersonHeadFormule.getOldOldPayment());
      chgPersonHead.setInsPayment(chgPersonHeadFormule.getInsPayment());
      chgPersonHead.setMulPayment(chgPersonHeadFormule.getMulPayment());
      
      chgPersonHead.setNewOldPayment(chgPersonHead.getOldOldPayment().add(
          chgPersonHead.getInsPayment())
          .subtract(chgPersonHead.getMulPayment()));
      BigDecimal percentage = new BigDecimal(100.00);
      if(chgPersonHead.getOldOldPayment().toString().equals("0")){
        chgPersonHead.setRate(new BigDecimal(1.00).divide(new BigDecimal(1.00), 4, BigDecimal.ROUND_HALF_UP).multiply(percentage).divide(new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP));
      }else{
        chgPersonHead.setRate(chgPersonHead.getNewOldPayment().subtract(chgPersonHead.getOldOldPayment()).divide(chgPersonHead.getOldOldPayment(), 4, BigDecimal.ROUND_HALF_UP).multiply(percentage).divide(new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP));
      }        
      chgPersonHead.setTemp_rate(chgPersonHead.getRate().toString()+"%");
    
      returnList.add(chgPersonHead);
      
      
      dto.setPreSumPay(dto.getPreSumPay().add(
          chgPersonHeadFormule.getOldOldPayment()));// 变更前总缴额
      dto.setSumPay(dto.getSumPay().add(chgPersonHeadFormule.getOldOldPayment()).add(chgPersonHeadFormule.getInsPayment())
          .subtract(chgPersonHeadFormule.getMulPayment()));// 变更后总缴额
      
    }
    dto.setOrgCount(new Integer(list.size()));
    chgHeadDTO.setChgpersonOrgHeadDTO(dto);
    chgHeadDTO.setList(list);
    return chgHeadDTO;
  }


  /**
   * 打印职工信息
   */
  public List queryChgpersonEmpListAll(Pagination pagination,String chgpersonHeadID,SecurityInfo securityInfo) throws Exception, BusinessException {
   
    List list = new ArrayList();

    String orgId = (String) pagination.getQueryCriterions().get("orgId");// 单位编号
    String orgName = (String) pagination.getQueryCriterions().get("orgName");// 单位名称
    String empId = (String) pagination.getQueryCriterions().get("empId");// 职工编号
    String empName = (String) pagination.getQueryCriterions().get("empName");// 职工名称
    String chgMonthStart = (String) pagination.getQueryCriterions().get(
        "chgMonthStart");// 年月开始
    String chgMonthEnd = (String) pagination.getQueryCriterions().get(
        "chgMonthEnd");// 年月结束
    String chgDateStart = (String) pagination.getQueryCriterions().get(
        "chgDateStart");// 日期开始
    String chgDateEnd = (String) pagination.getQueryCriterions().get(
        "chgDateEnd");// 日期结束

    list = chgPersonTailDAO.queryChgpersonEmpListPrint_WL(orgId,
        orgName, empId, empName, chgMonthStart, chgMonthEnd, chgDateStart,
        chgDateEnd,chgpersonHeadID,securityInfo);
    ChgPersonTail chgPersonTail = null;
    for (int i = 0; i < list.size(); i++) {
      chgPersonTail = (ChgPersonTail) list.get(i);
      // 转换
      chgPersonTail.getChgPersonHead().getOrg().getOrgInfo().getName();
      chgPersonTail.setTemp_oldPayStatus(BusiTools.getBusiValue(Integer
          .parseInt(chgPersonTail.getOldPayStatus().toString()),
          BusiConst.OLDPAYMENTSTATE));
      chgPersonTail.setTemp_newPayStatus(BusiTools.getBusiValue(Integer
          .parseInt(chgPersonTail.getNewPayStatus().toString()),
          BusiConst.OLDPAYMENTSTATE));
      if(chgPersonTail.getChgreason()!=null&&!chgPersonTail.getChgreason().equals("")){
        chgPersonTail.setTemp_chgreason(BusiTools.getBusiValue(Integer
            .parseInt(chgPersonTail.getChgreason()),
            BusiConst.CHGPERSONREASON));
      }
    }
    return list;
  }
  public List getChgpersonQueryList(Pagination pagination) throws Exception, BusinessException {
    List list = null;
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String office = (String) pagination.getQueryCriterions().get("office");
      String bankid = (String) pagination.getQueryCriterions().get("bankid");
      String begDate = (String) pagination.getQueryCriterions().get("begDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      String type = (String) pagination.getQueryCriterions().get("type");
      String orgid = (String) pagination.getQueryCriterions().get("orgid");
      list = chgPersonTailDAO.getChgpersonQueryList(office, bankid, begDate,
          endDate, type, orgid, start, pageSize);
      int count = chgPersonTailDAO.getChgpersonQueryListAll(office, bankid,
          begDate, endDate, type, orgid);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
