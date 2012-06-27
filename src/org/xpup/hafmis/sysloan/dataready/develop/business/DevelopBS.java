package org.xpup.hafmis.sysloan.dataready.develop.business;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Build;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildImportDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbFindDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbListDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO;
import org.xpup.hafmis.sysloan.dataready.develop.form.BuildNewAF;

public class DevelopBS implements IDevelopBS {

  // PL005表DAO
  protected DeveloperDAO developerDAO = null;

  // PL021操作日志表DAO
  protected PlOperateLogDAO plOperateLogDAO = null;

  // PL006表DAO
  protected DevelopProjectDAO developProjectDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  /**
   * 添加开发商用到的方法
   * 
   * @author 付云峰
   */
  public void saveDevelopInfo(Developer developer, SecurityInfo securityInfo) {
    // TODO Auto-generated method stub

    // 加入开发商状态
    developer.setDeveloperSt("0");
    developer.setDeveloperCode(developer.getDeveloperId());// 插入code字段(与开发商编号一样)
    // 去掉表单中提交信息的空格
    developer = trimDeveloperInfo(developer);
    developer.setReserveaB(developer.getReserveaB());
    // 保存开发商信息
    Serializable developer_id = developerDAO.insert(developer);
    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_DEVELOPERS));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
    plOperateLog.setOpBizId(new BigDecimal(developer_id.toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  /**
   * 
   */
  public void updateDeveloperInfo(Integer id, String path) throws Exception {
    try {
      Developer developer = developerDAO.queryById(id);
      if (developer != null) {
        developer.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 添加楼盘信息的方法
   * 
   * @param developProject
   * @param securityInfo
   */
  public void saveFloorInfo(DevelopProject developProject,
      SecurityInfo securityInfo) throws BusinessException {
    try {
      // 加入楼盘状态
      developProject.setFloorSt("0");

      developProject = trimFloorInfo(developProject);
      boolean flag = false;
      String[] floorNums = developProject.getFloorNum().split(",");
      String[] areas = null;
      if (developProject.getBuildingAreas() != null
          && !"".equals(developProject.getBuildingAreas())) {
        flag = true;
        areas = developProject.getBuildingAreas().toString().split(",");
        if (areas.length != floorNums.length) {
          throw new BusinessException("输入的面积和楼盘号不一一对应,请您核实!");
        }
      }
      for (int i = 0; i < floorNums.length; i++) {
        DevelopProject dp = new DevelopProject();
        try {
          BeanUtils.copyProperties(dp, developProject);
        } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        dp.setFloorNum(floorNums[i]);
        if (flag) {
          dp.setBuildingArea(new BigDecimal(areas[i]));
        }
        Serializable developProject_id = developProjectDAO.insert(dp);
        // 插入日志PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_FLOOR));
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(developProject_id.toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());

        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
  }

  public void saveBuildInfo(BuildNewAF buildNewAF, SecurityInfo securityInfo,
      String floorId, String developId) throws Exception {

    Build build = new Build();
    build.setFloorId(new BigDecimal(floorId));
    build.setBuild_s(buildNewAF.getBuild_s());
    build.setFundStatus(buildNewAF.getFundStatus());
    build.setBuildAdd(buildNewAF.getBuildAdd().trim());
    build.setBuildNum(buildNewAF.getBuildNum().trim());
    build.setReserved(buildNewAF.getReserved().trim());

    String stutas = this.fundStatus(developId, buildNewAF.getFloorId());
    if (stutas.equals("0")) {
      build.setFundStatus("0");
    }

    Serializable build_id = developProjectDAO.insert(build);

    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_BUILD));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
    plOperateLog.setOpBizId(new BigDecimal(build_id.toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);
  }

  // 开发商维护列表
  public List findDevelopList_info(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    DevelopTbFindDTO developTbFindDTO = (DevelopTbFindDTO) pagination
        .getQueryCriterions().get("developTbFindDTO");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    // 从节点进入时pagination中没有封装查询条件的DevelopTbFindDTO，所以new一个否则DAO会出现空指针异常
    if (developTbFindDTO == null) {
      developTbFindDTO = new DevelopTbFindDTO();
    }
    String buyhouseorgid = (String) pagination.getQueryCriterions().get(
        "buyhouseorgid");
    String floorName = (String) pagination.getQueryCriterions()
        .get("floorName");
    String floorNum = (String) pagination.getQueryCriterions().get("floorNum");
    developTbFindDTO.setBuyhouseorgid(buyhouseorgid);
    System.out.println(floorName);
    if (floorName != null && !"".equals(floorName)) {
      developTbFindDTO.setFloorName(floorName);
    }
    developTbFindDTO.setFloorNum(floorNum);
    // 得到显示的列表
    List list = developerDAO.queryDeveloperTbList_info(developTbFindDTO,
        securityInfo, orderBy, order, start, pageSize, page);
    for (int i = 0; i < list.size(); i++) {
      DevelopTbListDTO developTbListDTO = (DevelopTbListDTO) list.get(i);
      // 转换办事处
      OrganizationUnit organizationUnit = organizationUnitDAO
          .queryOrganizationUnitListByCriterions(developTbListDTO.getOffice());
      developTbListDTO.setOffice(organizationUnit.getName());
      developTbListDTO.setDeveloperSt((BusiTools.getBusiValue(Integer
          .parseInt(developTbListDTO.getDeveloperSt()),
          BusiConst.PLCOMMONSTATUS_1)));
    }
    // 求出count
    int count = developerDAO.queryDeveloperTbCount_info(developTbFindDTO,
        securityInfo);
    pagination.setNrOfElements(count);
    return list;

  }

  public List findDevelopPrintList_print(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {

    DevelopTbFindDTO developTbFindDTO = (DevelopTbFindDTO) pagination
        .getQueryCriterions().get("developTbFindDTO");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = 0;
    int page = pagination.getPage();

    // 从节点进入时pagination中没有封装查询条件的DevelopTbFindDTO，所以new一个否则DAO会出现空指针异常
    if (developTbFindDTO == null) {
      developTbFindDTO = new DevelopTbFindDTO();
    }
    String buyhouseorgid = (String) pagination.getQueryCriterions().get(
        "buyhouseorgid");
    String floorName = (String) pagination.getQueryCriterions()
        .get("floorName");
    String floorNum = (String) pagination.getQueryCriterions().get("floorNum");
    developTbFindDTO.setBuyhouseorgid(buyhouseorgid);
    if (floorName != null && !"".equals(floorName)) {
      developTbFindDTO.setFloorName(floorName);
    }
    developTbFindDTO.setFloorNum(floorNum);
    List list = developerDAO.queryDeveloperTbList_info(developTbFindDTO,
        securityInfo, orderBy, order, start, pageSize, page);
    for (int i = 0; i < list.size(); i++) {
      DevelopTbListDTO developTbListDTO = (DevelopTbListDTO) list.get(i);
      // 转换办事处
      OrganizationUnit organizationUnit = organizationUnitDAO
          .queryOrganizationUnitListByCriterions(developTbListDTO.getOffice());
      developTbListDTO.setOffice(organizationUnit.getName());
      developTbListDTO.setDeveloperSt((BusiTools.getBusiValue(Integer
          .parseInt(developTbListDTO.getDeveloperSt()),
          BusiConst.PLCOMMONSTATUS_1)));
    }
    return list;
  }

  /**
   * 根据PL005表主键id查询对象
   * 
   * @author 付云峰
   */
  public Developer findDeveloperInfo(Integer id) {
    Developer developer = developerDAO.queryById(id);
    return developer;
  }

  /**
   * 根据PL006表的id查询对象用来进行修改
   * 
   * @param id
   * @return 将要修改的对象
   * @author 付云峰
   */
  public DevelopProject findDevelopProjectInfo(Integer id) {
    DevelopProject developProject = developProjectDAO.queryById(id);
    return developProject;
  }

  public Build findBuildInfo(Integer id) {
    Build build = developProjectDAO.queryById_build(id);
    return build;
  }

  /**
   * 判断开发商状态
   * 
   * @param id 为PL005主键
   * @return true为正常状态，false为作废状态
   * @author 付云峰
   */
  public boolean isDeveloperST(Integer id) {

    boolean is_DeveloperST = true;
    try {
      String developerST = developerDAO.queryDeveloperST(id);
      if (developerST.equals("0")) {
        is_DeveloperST = true;
      } else {
        is_DeveloperST = false;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return is_DeveloperST;
  }

  /**
   * 判断楼盘状态
   * 
   * @param id PL006主键
   * @return true为正常状态，false为作废状态
   * @author 付云峰
   */
  public boolean isFloorST(Integer id) {

    boolean is_FloorST = true;
    try {
      String developProjectST = developProjectDAO.queryDevelopProjectST(id);
      if (developProjectST.equals("0")) {
        is_FloorST = true;
      } else {
        is_FloorST = false;
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return is_FloorST;
  }

  /**
   * 修改开发商状态方法,作废与撤销作废都使用此方法
   * 
   * @param id 为PL005主键
   * @param type 此参数为变更后的状态
   * @author 付云峰
   */
  public void modifyDeveloperST(Integer id, String type,
      SecurityInfo securityInfo) throws BusinessException {

    Developer developer = developerDAO.queryById(id);

    // 判断操作方式，type为'0'时为撤销作废操作，否则为作废操作

    if (type.equals("0")) {
      if (isDeveloperST(id)) {
        throw new BusinessException("此开发商已经是正常状态!");
      }
    } else {
      if (!isDeveloperST(id)) {
        throw new BusinessException("此开发商已经是作废状态!");
      }
    }

    developer.setDeveloperSt(type);
    developerDAO.updateDeveloper(developer);
    developerDAO.update_floor(id.toString(), type);

    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_DEVELOPERS));
    // 判断向PL021中插入的值
    if (type.equals("0")) {
      // 取消作废
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_OPENING));
    } else {
      // 作废
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    }
    plOperateLog.setOpBizId(new BigDecimal(id.toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);
  }

  /**
   * 修改开楼盘状态方法,作废与撤销作废都使用此方法
   * 
   * @param id
   * @param type
   * @param securityInfo
   * @throws BusinessException
   */
  public void modifyFloorST(Integer id, String type, SecurityInfo securityInfo)
      throws BusinessException {

    DevelopProject developProject = developProjectDAO.queryById(id);

    // 判断操作方式，type为'0'时为撤销作废操作，否则为作废操作
    if (type.equals("0")) {
      if (isFloorST(id)) {
        throw new BusinessException("此楼盘已经是正常状态!");
      }
    } else {
      if (!isFloorST(id)) {
        throw new BusinessException("此楼盘已经是作废状态!");
      }
    }
    developProject.setFloorSt(type);
    developProjectDAO.updateDevelopProject(developProject);

    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_FLOOR));
    // 判断向PL021中插入的值
    if (type.equals("0")) {
      // 取消作废
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_OPENING));
    } else {
      // 作废
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    }
    plOperateLog.setOpBizId(new BigDecimal(id.toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  /**
   * 修改开发商信息方法
   * 
   * @param new_developer 修改后的信息
   * @param old_developer 修改前的信息
   * @author 付云峰
   */
  public void modifyDeveloperInfo(Developer new_developer,
      Developer old_developer, SecurityInfo securityInfo) {

    old_developer.setAgreementEndDate(new_developer.getAgreementEndDate()
        .trim());
    old_developer.setAgreementStartDate(new_developer.getAgreementStartDate()
        .trim());
    old_developer.setArtfclprsn(new_developer.getArtfclprsn().trim());
    old_developer.setArtfclprsnTel(new_developer.getArtfclprsnTel().trim());
    old_developer.setCode(new_developer.getCode().trim());

    old_developer.setContactMobileA(new_developer.getContactMobileA().trim());
    old_developer.setContactMobileB(new_developer.getContactMobileB().trim());
    old_developer.setContactMobileC(new_developer.getContactMobileC().trim());

    old_developer.setContactPrsnA(new_developer.getContactPrsnA().trim());
    old_developer.setContactPrsnB(new_developer.getContactPrsnB().trim());
    old_developer.setContactPrsnC(new_developer.getContactPrsnC().trim());

    old_developer.setContactTelA(new_developer.getContactTelA().trim());
    old_developer.setContactTelB(new_developer.getContactTelB().trim());
    old_developer.setContactTelC(new_developer.getContactTelC().trim());

    old_developer.setBusinessA(new_developer.getBusinessA().trim());
    old_developer.setBusinessB(new_developer.getBusinessB().trim());
    old_developer.setBusinessC(new_developer.getBusinessC().trim());

    old_developer.setDeveloperPaybankA(new_developer.getDeveloperPaybankA()
        .trim());
    old_developer.setDeveloperPaybankAAcc(new_developer
        .getDeveloperPaybankAAcc().trim());
    old_developer.setDeveloperPaybankB(new_developer.getDeveloperPaybankB()
        .trim());
    old_developer.setDeveloperPaybankBAcc(new_developer
        .getDeveloperPaybankBAcc().trim());

    old_developer.setDeveloperAddr(new_developer.getDeveloperAddr().trim());
    old_developer.setDeveloperId(new_developer.getDeveloperId().trim());
    old_developer.setDeveloperCode(new_developer.getDeveloperId().trim());// 插入code字段(与开发商编号一样)
    old_developer.setDeveloperName(new_developer.getDeveloperName().trim());
    old_developer.setLandUseId(new_developer.getLandUseId().trim());

    old_developer.setCode(new_developer.getCode().trim());
    old_developer.setOrgTel(new_developer.getOrgTel().trim());
    old_developer.setPostalCode(new_developer.getPostalCode().trim());
    old_developer.setRegion(new_developer.getRegion().trim());
    old_developer.setSalePermis(new_developer.getSalePermis().trim());
    old_developer.setWorkAddr(new_developer.getWorkAddr().trim());
    old_developer.setRemark(new_developer.getRemark().trim());
    old_developer.setOffice(new_developer.getOffice().trim());
    old_developer.setRegisterFundNumber(new_developer.getRegisterFundNumber());
    // 是否为特殊开发商
    old_developer.setReserveaB(new_developer.getReserveaB());

    developerDAO.updateDeveloper(old_developer);

    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_DEVELOPERS));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
    plOperateLog.setOpBizId(new BigDecimal(old_developer.getId().toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  /**
   * 去掉开发商空格的方法
   * 
   * @param developer 修改后的信息
   * @author 付云峰
   */
  public Developer trimDeveloperInfo(Developer developer) {

    /*
     * 办事处没有去空格
     */
    developer.setAgreementEndDate(developer.getAgreementEndDate().trim());
    developer.setAgreementStartDate(developer.getAgreementStartDate().trim());
    developer.setArtfclprsn(developer.getArtfclprsn().trim());
    developer.setArtfclprsnTel(developer.getArtfclprsnTel().trim());
    developer.setCode(developer.getCode().trim());

    developer.setContactMobileA(developer.getContactMobileA().trim());
    developer.setContactMobileB(developer.getContactMobileB().trim());
    developer.setContactMobileC(developer.getContactMobileC().trim());

    developer.setContactPrsnA(developer.getContactPrsnA().trim());
    developer.setContactPrsnB(developer.getContactPrsnB().trim());
    developer.setContactPrsnC(developer.getContactPrsnC().trim());

    developer.setContactTelA(developer.getContactTelA().trim());
    developer.setContactTelB(developer.getContactTelB().trim());
    developer.setContactTelC(developer.getContactTelC().trim());

    developer.setBusinessA(developer.getBusinessA().trim());
    developer.setBusinessB(developer.getBusinessB().trim());
    developer.setBusinessC(developer.getBusinessC().trim());

    developer.setDeveloperPaybankA(developer.getDeveloperPaybankA().trim());
    developer.setDeveloperPaybankAAcc(developer.getDeveloperPaybankAAcc()
        .trim());
    developer.setDeveloperPaybankB(developer.getDeveloperPaybankB().trim());
    developer.setDeveloperPaybankBAcc(developer.getDeveloperPaybankBAcc()
        .trim());

    developer.setDeveloperAddr(developer.getDeveloperAddr().trim());
    developer.setDeveloperId(developer.getDeveloperId().trim());
    developer.setDeveloperName(developer.getDeveloperName().trim());
    developer.setLandUseId(developer.getLandUseId().trim());

    developer.setOrgTel(developer.getOrgTel().trim());
    developer.setPostalCode(developer.getPostalCode().trim());
    developer.setRegion(developer.getRegion().trim());
    developer.setSalePermis(developer.getSalePermis().trim());
    developer.setWorkAddr(developer.getWorkAddr().trim());
    return developer;
  }

  /**
   * 去掉楼盘信息空格的方法
   * 
   * @param developProject
   */
  public DevelopProject trimFloorInfo(DevelopProject developProject) {

    developProject.setFloorName(developProject.getFloorName().trim());
    developProject.setFloorNum(developProject.getFloorNum().trim());
    developProject.setItemFinishDegree(developProject.getItemFinishDegree()
        .trim());

    return developProject;
  }

  /**
   * 查询楼盘打印列表的方法
   * 
   * @author 付云峰
   */
  public List findFloorPrintList(Pagination pagination) throws Exception {

    String id = "";
    if (pagination.getQueryCriterions().get("id") != null) {
      id = (String) pagination.getQueryCriterions().get("id");
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = 0;
    // 求出列表的内容
    List list = developProjectDAO.queryDevelopProjectList(id, null, order,
        start, pageSize);

    for (int i = 0; i < list.size(); i++) {
      FloorListDTO floorListDTO = (FloorListDTO) list.get(i);
      // 转换楼盘状态
      floorListDTO.setFloorSt((BusiTools.getBusiValue(Integer
          .parseInt(floorListDTO.getFloorSt()), BusiConst.PLCOMMONSTATUS_1)));
      // 楼盘所的在地区可能不被添写，所以做此判断
      if (floorListDTO.getRegion() != null
          && !floorListDTO.getRegion().equals("")) {
        floorListDTO.setRegion((BusiTools.getBusiValue(Integer
            .parseInt(floorListDTO.getRegion()), BusiConst.INAREA)));
      }
    }

    return list;
  }

  /**
   * 查询楼盘中要显示的开发商信息
   * 
   * @param id PL005主键
   * @author 付云峰
   */
  public FloorDevelopInfoDTO showFloorDevelopInfo(Integer id) {
    FloorDevelopInfoDTO floorDevelopInfoDTO = developerDAO.queryDevelopInfo(id);

    // 转换办事处
    OrganizationUnit organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(floorDevelopInfoDTO.getOffice());
    floorDevelopInfoDTO.setOffice(organizationUnit.getName());

    return floorDevelopInfoDTO;
  }

  /**
   * 根据PL005主键查询对应的楼盘信息列表
   * 
   * @author 付云峰
   * @throws Exception
   */
  public List findFloorList(Pagination pagination) throws Exception {

    String id = "";
    if (pagination.getQueryCriterions().get("id") != null) {
      id = (String) pagination.getQueryCriterions().get("id");
    }
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    List resultList = new ArrayList();
    FloorListDTO dto = null;
    // 求出列表的内容
    List list = developProjectDAO.queryFloorName(id, null, null);
    int page = 0;
    for (int i = 0; i < list.size(); i++) {
      dto = (FloorListDTO) list.get(i);
      FloorListDTO floorDto = new FloorListDTO();
      floorDto.setFloorFlag("1");
      floorDto.setFloorName(dto.getFloorName());
      floorDto.setFloorAddress(dto.getFloorAddress());
      floorDto.setAddressFlag("1");
      List subList = developProjectDAO.querySubFloorInfoList(id, dto
          .getFloorName().toString(), orderBy, order);
      if (subList.size() != 0) {
        resultList.add(floorDto);
      }
      for (int j = 0; j < subList.size(); j++) {
        FloorListDTO subDto = (FloorListDTO) subList.get(j);
        subDto.setFundStatus(subDto.getFundStatus().equals("1") ? "是" : "否");
        subDto.setFloorSt((BusiTools.getBusiValue(Integer.parseInt(subDto
            .getFloorSt()), BusiConst.PLCOMMONSTATUS_1)));
        // 楼盘所的在地区可能不被添写，所以做此判断
        ++page;
        resultList.add(subDto);
      }
    }
    List rList = null;
    if (start + pageSize > resultList.size()) {
      rList = resultList.subList(start, resultList.size());
    } else {
      rList = resultList.subList(start, start + pageSize);
    }
    // 求出合计
    pagination.setNrOfElements(page);

    return rList;

  }

  public void delDevelopInfo(Serializable id) throws Exception {
    developerDAO.deleteDeveloperInfo(id);
  }

  public void delFloorInfo(Integer id) throws Exception {
    developerDAO.delFloorInfo(id);
  }

  /**
   * 修改楼盘信息的方法
   * 
   * @param old_developProject 旧的楼盘对象
   * @param new_developProject 新的楼盘对象
   */
  public void modifyFloorInfo(DevelopProject old_developProject,
      DevelopProject new_developProject, SecurityInfo securityInfo) {
    old_developProject.setFloorName(new_developProject.getFloorName().trim());
    old_developProject.setFloorNum(new_developProject.getFloorNum().trim());
    old_developProject.setFloorAddr(new_developProject.getFloorAddr());
    old_developProject.setHousePrice(new_developProject.getHousePrice());
    old_developProject.setItemFinishDegree(new_developProject
        .getItemFinishDegree().trim());
    old_developProject.setItemTotleAmnt(new_developProject.getItemTotleAmnt());
    old_developProject.setRegion(new_developProject.getRegion().trim());
    old_developProject.setBuildingArea(new BigDecimal(new_developProject
        .getBuildingAreas()));
    old_developProject.setFundStatus(new_developProject.getFundStatus());

    developProjectDAO.updateDevelopProject(old_developProject);

    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_FLOOR));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
    plOperateLog.setOpBizId(new BigDecimal(old_developProject.getFloorId()
        .toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  public void modifyBuildInfo(Build build, SecurityInfo securityInfo,
      String floorId, String developId) {

    String stutas = this.fundStatus(developId, floorId);
    if (stutas.equals("0")) {
      build.setFundStatus("0");
    }

    developProjectDAO.updateBuild(build);

    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_BUILD));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
    plOperateLog.setOpBizId(new BigDecimal(build.getBuildId().toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  public void deleteBuildInfo(Build build, SecurityInfo securityInfo) {

    developProjectDAO.deleteBuild(build);

    // 插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_BUILD));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    plOperateLog.setOpBizId(new BigDecimal(build.getBuildId().toString()));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public List findBuildList(Pagination pagination) throws Exception {
    String id = (String) pagination.getQueryCriterions().get("id");
    String buildId = (String) pagination.getQueryCriterions().get("buildId");
    String buildNum = (String) pagination.getQueryCriterions().get("buildNum");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    // 求出列表的内容
    List list = developProjectDAO.queryBuildList(id, buildId, buildNum,
        orderBy, order, start, pageSize, page);

    // 求出合计
    int count = developProjectDAO.queryBuildCount(id, buildId, buildNum);
    pagination.setNrOfElements(count);

    return list;

  }

  public List findBuildList_exp(Pagination pagination) throws Exception {
    String id = (String) pagination.getQueryCriterions().get("id");
    String buildId = (String) pagination.getQueryCriterions().get("buildId");
    String buildNum = (String) pagination.getQueryCriterions().get("buildNum");

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    // 求出列表的内容
    List list = developProjectDAO.queryBuildList_exp(id, buildId, buildNum,
        orderBy, order, start, pageSize);

    // 求出合计
    int count = developProjectDAO.queryBuildCount(id, buildId, buildNum);
    pagination.setNrOfElements(count);

    return list;

  }

  // 插入日志PL021
  public void insertHafOperateLog(String buildId, SecurityInfo securityInfo) {
    try {
      // 插入日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_BUILD)
          .toString());
      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_EXP)
          .toString());
      plOperateLog.setOpBizId(new BigDecimal(0));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void buildImportInfo(List buildImportList1, List bulidImportList2,
      String floorId, SecurityInfo securityInfo, String developId)
      throws Exception, BusinessException {
    BuildImportDTO buildImportDTO = new BuildImportDTO();
    System.out.println("bulidImportList2.size()=" + bulidImportList2.size());
    for (int i = 1; i < bulidImportList2.size(); i++) {
      UtilRule utilRule = new UtilRule();
      buildImportDTO = (BuildImportDTO) bulidImportList2.get(i);
      if (buildImportDTO.getBuildNum() == null
          || "".equals(buildImportDTO.getBuildNum())) {
        throw new BusinessException("导入数据第" + "" + (i + 5) + "" + "行数据,"
            + "楼栋号不能为空!");
      }
      if (buildImportDTO.getFundStatus() == null
          || "".equals(buildImportDTO.getFundStatus())) {
        throw new BusinessException("导入数据第" + "" + (i + 5) + "" + "行数据,"
            + "是否拨款不能为空!");
      }
      if (!buildImportDTO.getFundStatus().equals("0")
          && !buildImportDTO.getFundStatus().equals("1")) {
        throw new BusinessException("导入数据第" + "" + (i + 5) + "" + "行数据,"
            + "拨款只能添(0或1)!");
      }
      if (utilRule.moneyRule(buildImportDTO.getBuild_s(), 15, 2) == false) {
        throw new BusinessException("导入数据第" + "" + (i + 5) + "" + "行数据,"
            + "建筑面积格式不正确！");
      }

      Build build = new Build();
      System.out.println("floorId=" + floorId);
      build.setFloorId(new BigDecimal(floorId));
      if (buildImportDTO.getBuildAdd().trim() != null
          && buildImportDTO.getBuildAdd().trim() != "") {
        build.setBuildAdd(buildImportDTO.getBuildAdd().trim());
      }
      build.setBuildNum(buildImportDTO.getBuildNum().trim());
      build.setBuild_s(new BigDecimal(buildImportDTO.getBuild_s()));
      build.setFundStatus(buildImportDTO.getFundStatus().trim());
      if (buildImportDTO.getReserved() != null
          && buildImportDTO.getReserved() != "") {
        build.setReserved(buildImportDTO.getReserved().trim());
      }

      // String stutas = this.fundStatus(developId, floorId);
      // if(stutas.equals("0")){
      // build.setFundStatus("0");
      // }

      Serializable build_id = developProjectDAO.insert(build);

      // 插入日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog
          .setOpModel(String
              .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_BUILD));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZBLOG_ACTION_IMP));
      plOperateLog.setOpBizId(new BigDecimal(build_id.toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());

      plOperateLogDAO.insert(plOperateLog);
    }
  }

  public String fundStatus(String developId, String floorId) {
    String status = "";
    try {
      status = developProjectDAO.findBuildFunStatus(floorId, developId);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return status;
  }

  public void updateBuildStatus(String floorId) {
    try {
      developProjectDAO.updateBuildStatus(floorId);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public String findFloornum(String id) {
    String status = "";
    try {
      status = developProjectDAO.findFloornum(id);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return status;
  }

  public String findFloorname(final String id) {
    String status = "";
    try {
      status = developProjectDAO.findFloorname(id);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return status;
  }
}
