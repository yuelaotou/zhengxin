package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationInfoTaDTO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTaDTO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTaPop2DTO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTbDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectRelationDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.SubjectRelation;
import org.xpup.security.common.domain.Userslogincollbank;

public class SubjectrelationBS implements ISubjectrelationBS {
  private SubjectRelationDAO subjectRelationDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private CollBankDAO collBankDAO = null;

  private OrgDAO orgDAO = null;

  private FnOperateLogDAO fnOperateLogDAO = null;

  private SubjectDAO subjectDAO = null;

  private BookParameterDAO bookParameterDAO = null;
  
 
  

  /**
   * author wsh 科目关系设置 查询Ta上半部分的信息(当科目代码存在时)
   * 
   * @param subjectCode 科目编号
   * @2007-10-15
   * @return SubjectrelationInfoTaDTO
   */
  public SubjectrelationInfoTaDTO findSubejectRelationTaInfo(String subjectCode,String bookId)
  throws Exception {
// TODO Auto-generated method stub
SubjectrelationInfoTaDTO subjectrelationInfoTaDTO = new SubjectrelationInfoTaDTO();
try {
  subjectrelationInfoTaDTO = subjectRelationDAO
      .findSubejectRelationTaInfo(subjectCode,bookId);
  subjectrelationInfoTaDTO.setBalanceDirection(BusiTools.getBusiValue_WL(
     subjectrelationInfoTaDTO.getBalanceDirection()
         , BusiConst.BALANCEDIRECTION));
  double temp_balance = 0;
  temp_balance = Math.abs(subjectrelationInfoTaDTO.getBalance().doubleValue());
  String temp_b = String.valueOf(temp_balance);
  subjectrelationInfoTaDTO.setBalance(new BigDecimal(temp_b));
} catch (Exception e) {
  // TODO: handle exception
  e.printStackTrace();
}
return subjectrelationInfoTaDTO;
}

  /**
   * author wsh 科目关系设置 查询Ta上半部分的信息(当科目代码不存在时)
   * 
   * @param subjectCode 科目编号
   * @2007-10-15
   * @return SubjectrelationInfoTaDTO
   */
  public SubjectrelationInfoTaDTO findSubejectRelationTa1Info(String subjectCode,String bookId)
      throws Exception {
    // TODO Auto-generated method stub
    SubjectrelationInfoTaDTO subjectrelationInfoTaDTO = new SubjectrelationInfoTaDTO();
    try {
      String length = "";
      String firstSubjectCode = "";
      subjectrelationInfoTaDTO = subjectRelationDAO
          .findSubejectRelationTa1Info(subjectCode,bookId);
      length = bookParameterDAO.queryFirstSubjectCodeLength_wsh(bookId);
      firstSubjectCode = subjectCode.substring(0, new Integer(length)
          .intValue());
      subjectrelationInfoTaDTO.setFirstSubjectCode(firstSubjectCode);
      subjectrelationInfoTaDTO.setBalanceDirection(BusiTools.getBusiValue_WL(
          subjectrelationInfoTaDTO.getBalanceDirection()
              , BusiConst.BALANCEDIRECTION));
      double temp_balance = 0;
      temp_balance = Math.abs(subjectrelationInfoTaDTO.getBalance().doubleValue());
      String temp_b = String.valueOf(temp_balance);
      subjectrelationInfoTaDTO.setBalance(new BigDecimal(temp_b));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return subjectrelationInfoTaDTO;
  }

  /**
   * author wsh 科目关系设置 查询科目编号是否存在
   * 
   * @param subjectCode 科目编号
   * @2007-10-15
   * @return Integer
   */
  public Integer findSubjectrelationExist(String subjectCode,SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    Integer count = new Integer(0);
    try {
      count = subjectRelationDAO.is_CodeIn_wsh(subjectCode,securityInfo);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * author wsh 科目关系设置 查询Ta列表信息
   * 
   * @param subjectCode 科目编号
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTaList(String subjectCode,
      String calculRelaType, Pagination pagination,final String bookId) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    List list1 = new ArrayList();
    List countList = new ArrayList();
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = subjectRelationDAO.querySubejectRelationTaList_wsh(subjectCode,
          start, pageSize,bookId);
      countList = subjectRelationDAO
          .querySubejectRelationTaCountList_wsh(subjectCode,bookId);
      pagination.setNrOfElements(countList.size());
      Iterator iter = list.iterator();
      while (iter.hasNext()) {
        SubjectrelationTaDTO subjectrelationTaDTO = new SubjectrelationTaDTO();
        Object obj = (Object) iter.next();
        // obj[0].toString();
        String calculRelaValue = obj.toString();
        ;
        subjectrelationTaDTO.set(calculRelaValue);
        if ("0".equals(calculRelaType)) {
          // 办事处
          OrganizationUnit organizationUnit = organizationUnitDAO
              .queryOrganizationUnitListByCriterions(calculRelaValue);
          subjectrelationTaDTO.setCalculName(organizationUnit.getName());
        }
        if ("1".equals(calculRelaType)) {
          // 银行
          CollBank dto = collBankDAO.getCollBankByCollBankid(calculRelaValue);
          subjectrelationTaDTO.setCalculName(dto.getCollBankName());
        }
        if ("2".equals(calculRelaType)) {
          // 单位
          String orgName = orgDAO.findOrgName_wsh(calculRelaValue);
          subjectrelationTaDTO.setCalculName(orgName);
        }
        list1.add(subjectrelationTaDTO);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list1;
  }

  /**
   * author wsh 科目关系设置 查询办事处弹出框信息
   * 
   * @param pagination
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTaPop1List(Pagination pagination,SecurityInfo securityInfo)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list=subjectRelationDAO.getUserOfficeLists_wsh(start, pageSize, securityInfo.getUserName());
      //list = organizationUnitDAO.queryOrganizationNameList_wsh(start, pageSize);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 科目关系设置 查询办事处弹出框的记录条数
   * 
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTaPop1CountList(SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      list = organizationUnitDAO.queryOrganizationNameCountList_wsh(securityInfo.getUserName());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 科目关系设置 查询银行弹出框信息
   * 
   * @param pagination
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTaPop2List(Pagination pagination,SecurityInfo securityInfo)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {securityInfo.getCollBankList();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = collBankDAO.getUserCollBankList_wsh(start, pageSize,securityInfo.getUserName());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 科目关系设置 查询银行弹出框的记录条数
   * 
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTaPop2CountList(SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      list = collBankDAO.queryCollBankCountList_wsh1(securityInfo.getUserName());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 科目关系设置 查询单位弹出框信息
   * 
   * @param pagination
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTaPop3List(Pagination pagination,SecurityInfo securityInfo,List officeList2,List loanbankList2)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String office = "";
      String loanBankId = "";
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      if (pagination.getQueryCriterions().get("office") != null) {
        office = (String) pagination.getQueryCriterions().get("office");
      }
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      list=securityInfo.getCollBankList();
      
//      list=securityDAO.getUserOrgList(securityInfo.getUserName());
      list = orgDAO.queryOrgList_wsh(office,loanBankId,start, pageSize,securityInfo,officeList2,loanbankList2);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 科目关系设置 查询单位弹出框的记录条数
   * 
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTaPop3CountList(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    String office = "";
    String loanBankId = "";
    try {
      if (pagination.getQueryCriterions().get("office") != null) {
        office = (String) pagination.getQueryCriterions().get("office");
      }
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      List loanbankList1 = null;
      List loanbankList2 = null;
      List officeList1 = null;
      List officeList2 = null;
      try {
        // 取出用户权限办事处,显示在下拉菜单中
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        officeList2 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
          officeList2.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getCollBankList();
        loanbankList1 = new ArrayList();
        loanbankList2 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
          loanbankList2.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }    
      list = orgDAO.queryOrgCountList_wsh(office,loanBankId, securityInfo,officeList2, loanbankList2);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 科目关系设置 查询核算关系值是否已经建立核算关系
   * 
   * @param firstsubjectCode 一级科目
   * @param calculRelaType 核算关系类型
   * @param calculRelaValue 核算关系值
   * @2007-10-16
   * @return List
   */
  public void findSubjectrelationInfoExist(String firstsubjectCode,
      String calculRelaType, String calculRelaValue,String bookId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      BusinessException be = null;
      count = subjectRelationDAO.findSubjectrelationInfoExist(firstsubjectCode,
          calculRelaType, calculRelaValue,bookId).intValue();
      if (count != 0) {
        be = new BusinessException(calculRelaValue + "该核算关系值已经建立核算关系！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询 银行所在的办事处是否已经建立核算关系
   * 
   * @param collBankId 银行编号
   * @param calculRelaType 核算关系类型
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaExist2(String collBankId,
      String firstsubjectCode,String bookId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      // 办事处id
      String id = "";
      BusinessException be = null;
      id = collBankDAO.queryOfiiceId_wsh(collBankId);
      count = subjectRelationDAO.findSubjectrelationInfoExist(firstsubjectCode,
          "0", id,bookId).intValue();
      if (count != 0) {
        be = new BusinessException(id + "该核算关系值的办事处已经建立核算关系！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询 银行下的单位是否已经建立核算关系
   * 
   * @param firstsubjectCode 一级科目
   * @param collBankId 银行编号
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaOrgExist(String collBankId,
      String firstsubjectCode,String bookId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      // 办事处id
      String id = "";
      List list = new ArrayList();
      BusinessException be = null;
      list = orgDAO.queryOrgIdList_wsh(collBankId);
      for (int i = 0; i < list.size(); i++) {
        count = subjectRelationDAO.findSubjectrelationInfoExist(
            firstsubjectCode, "2", list.get(i).toString(),bookId).intValue();
        if (count != 0) {
          be = new BusinessException(id + "该核算关系值的单位已经建立核算关系！");
          throw be;
        }
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 插入类型为银行的记录
   * 
   * @param firstsubjectCode 一级科目
   * @param collBankId 银行编号
   * @2007-10-16
   * @return List
   */
  public void subjectrelationTaSaveBank(String calculRelaType,
      String calculRelaValue[], String firstSubjectCode, String subjectCode,
      SecurityInfo securityInfo) throws Exception, BusinessException  {
    // TODO Auto-generated method stub
    try {
      for(int i=0;i<calculRelaValue.length;i++){
        SubjectRelation subjectrelation = new SubjectRelation();
        subjectrelation.setBookId(securityInfo.getBookId());
        subjectrelation.setCalculRelaType(calculRelaType);
        subjectrelation.setCalculRelaValue(calculRelaValue[i]);
        subjectrelation.setFirstSubjectCode(firstSubjectCode);
        subjectrelation.setSubjectCode(subjectCode);
        subjectRelationDAO.insert(subjectrelation);
      }
      subjectrelationTaFnOperateLog(securityInfo,String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      throw new BusinessException("科目关系设置失败！");
    }
  }

  /**
   * author wsh 科目关系设置 查询办事处下的银行是否已经建立核算关系
   * 
   * @param firstsubjectCode 一级科目
   * @param officeId 办事处id
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaBankExist(String officeId,
      String firstsubjectCode,String bookId,SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {     
      List loanbankList2 = null;
      int count = 0;
      // 办事处id
      String id = "";
//      List list = new ArrayList();
      BusinessException be = null;
//      list = collBankDAO.queryBankIdList_wsh(officeId);
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getCollBankList();       
        loanbankList2 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();         
          loanbankList2.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      for (int i = 0; i < loanbankList2.size(); i++) {
        count = subjectRelationDAO.findSubjectrelationInfoExist(
            firstsubjectCode, "1", loanbankList2.get(i).toString(),bookId).intValue();
        if (count != 0) {
          be = new BusinessException(id + "该核算关系值的银行已经建立核算关系！");
          throw be;
        }
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询办事处下的单位是否已经建立核算关系
   * 
   * @param firstsubjectCode 一级科目
   * @param officeId 办事处id
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaOOrgExist(String officeId,
      String firstsubjectCode,String bookId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      // 办事处id
      String id = "";
      List list = new ArrayList();
      BusinessException be = null;
      list = orgDAO.queryOOrgIdList_wsh(officeId);
      for (int i = 0; i < list.size(); i++) {
        count = subjectRelationDAO.findSubjectrelationInfoExist(
            firstsubjectCode, "2", list.get(i).toString(),bookId).intValue();
        if (count != 0) {
          be = new BusinessException(id + "该核算关系值的单位已经建立核算关系！");
          throw be;
        }
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询单位的银行是否已经建立核算关系
   * 
   * @param firstsubjectCode 一级科目
   * @param orgId 银行编号
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaOrgBankExist(String orgId,
      String firstsubjectCode,String bookId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      // 办事处id
      String id = "";
      BusinessException be = null;
      String bankId = "";
      bankId = orgDAO.queryCollBankId_wsh(orgId);
      count = subjectRelationDAO.findSubjectrelationInfoExist(firstsubjectCode,
          "1", bankId,bookId).intValue();
      if (count != 0) {
        be = new BusinessException(id + "该核算关系值的银行已经建立核算关系！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询单位的办事处是否已经建立核算关系
   * 
   * @param firstsubjectCode 一级科目
   * @param orgId 银行编号
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaOrgOExist(String orgId,
      String firstsubjectCode,String bookId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      // 办事处id
      String id = "";
      BusinessException be = null;
      String officeId = "";
      officeId = orgDAO.queryOfficeId_wsh(orgId);
      count = subjectRelationDAO.findSubjectrelationInfoExist(firstsubjectCode,
          "0", officeId,bookId).intValue();
      if (count != 0) {
        be = new BusinessException(id + "该核算关系值的办事处已经建立核算关系！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询输入的科目编号是否是末级科目
   * 
   * @param subjectCode 科目代码
   * @2007-10-16
   * @return List
   */
  public void findSubjectrelationParentId(String subjectCode,SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      BusinessException be = null;
      int count = 0;
      count = subjectDAO.findSubjectrelationParentId(subjectCode, securityInfo).intValue();
      if (count != 0) {
        be = new BusinessException("该科目代码不是最后一级科目！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  /**
   * author wsh 科目关系设置 查询所有办事处是否建立核算关系
   * 
   * @param subjectCode 科目代码
   * @2007-10-16
   * @return List
   */
  public void findSubjectrelationAllOExist(List list,String firstsubjectCode,String bookId)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {         
      for (int i = 0; i < list.size(); i++) {
        findSubjectrelationInfoExist(firstsubjectCode, "0", list.get(i)
            .toString(),bookId);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询所有银行是否建立核算关系
   * 
   * @param subjectCode 科目代码
   * @2007-10-16
   * @return List
   */
  public void findSubjectrelationAllBankExist(List list,String firstsubjectCode,String bookId)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {    
      SubjectrelationTaPop2DTO subjectrelationTaPop2DTO=new SubjectrelationTaPop2DTO();
      for (int i = 0; i < list.size(); i++) {
        subjectrelationTaPop2DTO=(SubjectrelationTaPop2DTO)list.get(i);
        findSubjectrelationInfoExist(firstsubjectCode, "1", subjectrelationTaPop2DTO.getCollBankId()
            ,bookId);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询所有单位是否建立核算关系
   * 
   * @param subjectCode 科目代码
   * @2007-10-16
   * @return List
   */
  public void findSubjectrelationAllOrgExist(List orgList,String firstsubjectCode,String bookId)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      for (int i = 0; i < orgList.size(); i++) {
        findSubjectrelationInfoExist(firstsubjectCode, "2", orgList.get(i)
            .toString(),bookId);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询所有银行的id及nameList
   * 
   * @param subjectCode 科目代码
   * @2007-10-16
   * @return List
   */
  public List querySubjectrelationTbAllBank() throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      list = collBankDAO.queryCollBankIdNameList_wsh();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 科目关系设置 查询Tb列表信息
   * 
   * @param pagination
   * @2007-10-15
   * @return List
   */
  public List querySubejectRelationTbList(Pagination pagination,String bookId,List officeList,List bankList,List orgIdList)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    List list1 = new ArrayList();
    List countList = new ArrayList();
    String subjectCode = "";
    String subjectName = "";
    String calculRelaType = "";
    String calculRelaValue = "";
    try {
      if (pagination.getQueryCriterions().get("subjectCode") != null) {
        subjectCode = (String) pagination.getQueryCriterions().get(
            "subjectCode");
      }
      if (pagination.getQueryCriterions().get("subjectName") != null) {
        subjectName = (String) pagination.getQueryCriterions().get(
            "subjectName");
      }
      if (pagination.getQueryCriterions().get("calculRelaType") != null) {
        calculRelaType = (String) pagination.getQueryCriterions().get(
            "calculRelaType");
      }
      if (pagination.getQueryCriterions().get("calculRelaValue") != null) {
        calculRelaValue = (String) pagination.getQueryCriterions().get(
            "calculRelaValue");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = subjectRelationDAO.querySubejectRelationTbList_wsh(subjectCode,
          subjectName, calculRelaType, calculRelaValue, orderBy, order, start,
          pageSize, page,bookId,officeList,bankList, orgIdList);
      countList = subjectRelationDAO.querySubejectRelationTbCountList_wsh(
          subjectCode, subjectName, calculRelaType, calculRelaValue,bookId);
      pagination.setNrOfElements(countList.size());
      Iterator iter = list.iterator();
      while (iter.hasNext()) {
        SubjectrelationTbDTO subjectrelationTbDTO = new SubjectrelationTbDTO();
        subjectrelationTbDTO = (SubjectrelationTbDTO) iter.next();
        calculRelaType = subjectrelationTbDTO.getCalculRelaType();
        calculRelaValue = subjectrelationTbDTO.getCalculRelaValue();
        if ("0".equals(calculRelaType)) {
          // 办事处
          OrganizationUnit organizationUnit = organizationUnitDAO
              .queryOrganizationUnitListByCriterions(calculRelaValue);
          subjectrelationTbDTO.setCalculRelaName(organizationUnit.getName());
        }
        if ("1".equals(calculRelaType)) {
          // 银行
          CollBank dto = collBankDAO.getCollBankByCollBankid(calculRelaValue);
          subjectrelationTbDTO.setCalculRelaName(dto.getCollBankName());
        }
        if ("2".equals(calculRelaType)) {
          // 单位
          String orgName = orgDAO.findOrgName_wsh(calculRelaValue);
          subjectrelationTbDTO.setCalculRelaName(orgName);
        }
        if (calculRelaType.equals("0")) {
          subjectrelationTbDTO.setCalculRelaType("办事处");
        }
        if (calculRelaType.equals("1")) {
          subjectrelationTbDTO.setCalculRelaType("银行");
        }
        if (calculRelaType.equals("2")) {
          subjectrelationTbDTO.setCalculRelaType("单位");
        }
        if (calculRelaType.equals("3")) {
          subjectrelationTbDTO.setCalculRelaType("其他");
        }
        list1.add(subjectrelationTbDTO);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list1;
  }

  /**
   * author wsh 科目关系设置 删除维护列表的选中记录
   * 
   * @param subjectreleid fn111主键
   * @param SecurityInfo securityInfo
   * @2007-10-19
   * @return List
   */
  public void deleteISubjectrelationTb(String subjectreleid,String bookId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      BusinessException be = null;
      int count = 0;
      count = subjectRelationDAO.findSubjectrelationExist_wsh(subjectreleid,bookId)
          .intValue();
      if (count == 0) {
        be = new BusinessException("该记录已经删除！");
        throw be;
      } else {
        subjectRelationDAO.deleteSubjectrelationTb_wsh(subjectreleid);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 查询Tb的CountList
   * 
   * @param pagination
   * @2007-10-19
   * @return List
   */
  public List querySubejectRelationTbCountList(Pagination pagination,String bookId)
      throws Exception {
    // TODO Auto-generated method stub
    List countList = new ArrayList();
    String subjectCode = "";
    String subjectName = "";
    String calculRelaType = "";
    String calculRelaValue = "";
    try {
      if (pagination.getQueryCriterions().get("subjectCode") != null) {
        subjectCode = (String) pagination.getQueryCriterions().get(
            "subjectCode");
      }
      if (pagination.getQueryCriterions().get("subjectName") != null) {
        subjectName = (String) pagination.getQueryCriterions().get(
            "subjectName");
      }
      if (pagination.getQueryCriterions().get("calculRelaType") != null) {
        calculRelaType = (String) pagination.getQueryCriterions().get(
            "calculRelaType");
      }
      if (pagination.getQueryCriterions().get("calculRelaValue") != null) {
        calculRelaValue = (String) pagination.getQueryCriterions().get(
            "calculRelaValue");
      }
      countList = subjectRelationDAO.querySubejectRelationTbCountList_wsh(
          subjectCode, subjectName, calculRelaType, calculRelaValue,bookId);
      pagination.setNrOfElements(countList.size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return countList;
  }

  /**
   * author wsh 科目关系设置 删除维护列表的所有记录
   * 
   * @param subejectRelationTbCountList 全部记录的idList
   * @param SecurityInfo securityInfo
   * @2007-10-19
   * @return List
   */
  public void deleteAllSubjectrelationTb(List subejectRelationTbCountList,String bookId)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      Iterator iter = subejectRelationTbCountList.iterator();
      while (iter.hasNext()) {
        String subjectreleid = String.valueOf((BigDecimal) iter.next());
        BusinessException be = null;
        int count = 0;
        count = subjectRelationDAO.findSubjectrelationExist_wsh(subjectreleid,bookId)
            .intValue();
        if (count == 0) {
          be = new BusinessException("该记录已经删除！");
          throw be;
        } else {
          subjectRelationDAO.deleteSubjectrelationTb_wsh(subjectreleid);
        }
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 科目关系设置 插入日志
   * 
   * @param SecurityInfo securityInfo
   * @2007-10-19
   * @return List
   */
  public void subjectrelationTaFnOperateLog(SecurityInfo securityInfo,
      String type) {
    // TODO Auto-generated method stub
    try {
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(type);
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_BOOKMNG_SUBJECTRELATION));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void setSubjectRelationDAO(SubjectRelationDAO subjectRelationDAO) {
    this.subjectRelationDAO = subjectRelationDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setFnOperateLog(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  /**
   * author wsh 科目关系设置 判断科目代码是否是最后一级科目代码
   * 
   * @param subjectCode 科目代码
   * @2007-10-19
   * @return
   */
  public void findSubjectRelationFirstCode(String subjectCode,SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      BusinessException be = null;
      int count = 0;
      String code="";
      code=subjectDAO.is_CodeIn_WL(subjectCode, "0", securityInfo);
      if("".equals(code)||code==null){
        be = new BusinessException("该科目代码不存在或或已作废！");
        throw be;
      }
      count=subjectDAO.findSubjectrelationParentId(subjectCode,securityInfo).intValue();
      if (count != 0) {
        be = new BusinessException("该科目代码不是最后一级科目！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public List querySubejectRelationOrgIdList() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
  /**
   * author wsh 科目关系设置 查询核算关系值办事处是否已经建立核算关系在类型为办事处0和其他3记录中
   * 
   * @param firstsubjectCode 一级科目
   * @param calculRelaType 核算关系类型
   * @param calculRelaValue 核算关系值
   * @2007-10-16
   * @return List
   */
  public void findSubjectrelationInfoExist_wsh(String firstsubjectCode,
      String calculRelaType, String calculRelaValue,String bookId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      BusinessException be = null;
      count = subjectRelationDAO.findSubjectrelationInfoExist_wsh(firstsubjectCode,
          calculRelaType, calculRelaValue,bookId).intValue();
      if (count != 0) {
        be = new BusinessException(calculRelaValue + "该核算关系值已经建立核算关系！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
  /**
   * author wsh 科目关系设置 查询 银行所在的办事处是否已经建立核算关系
   * 
   * @param collBankId 银行编号
   * @param calculRelaType 核算关系类型
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaExist2_bank_banshichu(String collBankId,
      String firstsubjectCode,String bookId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      // 办事处id
      String id = "";
      BusinessException be = null;
      id = collBankDAO.queryOfiiceId_wsh(collBankId);
      count = subjectRelationDAO.findSubjectrelationInfoExist_bank(firstsubjectCode,
          "0", id,bookId).intValue();
      if (count != 0) {
        be = new BusinessException(id + "该核算关系值的办事处已经建立核算关系！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
  /**
   * author wsh 科目关系设置 查询单位的办事处是否已经建立核算关系
   * 
   * @param firstsubjectCode 一级科目
   * @param orgId 银行编号
   * @2007-10-16
   * @return List
   */
  public void findSubejectRelationTaOrgOExistorg_banshichu(String orgId,
      String firstsubjectCode,String bookId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      // 办事处id
      String id = "";
      BusinessException be = null;
      String officeId = "";
      officeId = orgDAO.queryOfficeId_wsh(orgId);
      count = subjectRelationDAO.findSubjectrelationInfoExist(firstsubjectCode,
          "0", officeId,bookId).intValue();
      if (count != 0) {
        be = new BusinessException(id + "该核算关系值的办事处已经建立核算关系！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      throw  new BusinessException("科目关系设置失败！");
    }
  }
  /**
   * 检查页面录入的科目是否存(在并且为一级科目,去掉了)
   */
  public String checkSubjectcode(String subjectcode, SecurityInfo securityInfo) throws Exception, BusinessException {
    String message="";
    try{
      String flag=subjectDAO.getSubjectProperty_wsh(securityInfo.getBookId(),subjectcode);
      if(flag==null ||flag.equals("")){
        message="录入的科目代码要求是已经存在并且不是作废的科目！！";
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return message;
  }

}
