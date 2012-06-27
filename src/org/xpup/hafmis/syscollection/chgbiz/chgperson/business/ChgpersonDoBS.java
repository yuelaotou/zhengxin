package org.xpup.hafmis.syscollection.chgbiz.chgperson.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.AutoChangePopDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonAutoopenDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonCellListListExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonEmpInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonImpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonImpTitleDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgPersonAutoopenAF;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonTailDAO;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.OfficeParaDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgEditionDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInTailDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPersonHeadDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPersonTailDW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
import org.xpup.hafmis.syscommon.dao.EmpInfoDAO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * @author 王玲 2007-6-27
 */
public class ChgpersonDoBS implements IChgpersonDoBS {

  private ChgPersonTailDAO chgPersonTailDAO = null;

  private OrgDAO orgDAO = null;

  private EmpDAO empDAO = null;

  private ChgOrgRateDAO chgOrgRateDAO = null;

  private ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;

  private ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;

  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;

  private ChgPersonHeadDAO chgPersonHeadDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private ChgPersonBizActivityLogDAO chgPersonBizActivityLogDAO = null;

  private EmpInfoDAO empInfoDAO = null;

  private TranInHeadDAO tranInHeadDAO = null;

  private TranInTailDAO tranInTailDAO = null;

  private AutoInfoPickDAO autoInfoPickDAO = null;//

  private AutoInfoPickDAODW autoInfoPickDAODW = null;

  private ChgPersonHeadDAODW chgPersonHeadDAODW = null;// 单位版数据库aa204

  private ChgPersonTailDW chgPersonTailDW = null;// 单位版数据库aa205

  private OrgEditionDAO orgEditionDAO = null;// 中心数据库da002

  private EmpDAODW empDAODW = null;// 单位库aa002

  private OfficeParaDAO officeParaDAO = null;

  private CollParaDAO collParaDAO = null;

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  private String center_head_id = "";

  private String hafOperateLogid = "";

  public void setTranInTailDAO(TranInTailDAO tranInTailDAO) {
    this.tranInTailDAO = tranInTailDAO;
  }

  public void setTranInHeadDAO(TranInHeadDAO tranInHeadDAO) {
    this.tranInHeadDAO = tranInHeadDAO;
  }

  public void setChgPersonTailDAO(ChgPersonTailDAO chgPersonTailDAO) {
    this.chgPersonTailDAO = chgPersonTailDAO;
  }

  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
  }

  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setOfficeParaDAO(OfficeParaDAO officeParaDAO) {
    this.officeParaDAO = officeParaDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setChgPersonBizActivityLogDAO(
      ChgPersonBizActivityLogDAO chgPersonBizActivityLogDAO) {
    this.chgPersonBizActivityLogDAO = chgPersonBizActivityLogDAO;
  }

  public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
    this.empInfoDAO = empInfoDAO;
  }

  public void setChgPersonHeadDAODW(ChgPersonHeadDAODW chgPersonHeadDAODW) {
    this.chgPersonHeadDAODW = chgPersonHeadDAODW;
  }

  public void setChgPersonTailDW(ChgPersonTailDW chgPersonTailDW) {
    this.chgPersonTailDW = chgPersonTailDW;
  }

  public void setOrgEditionDAO(OrgEditionDAO orgEditionDAO) {
    this.orgEditionDAO = orgEditionDAO;
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
  }

  /**
   * 根据条件查询人员变更记录
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List findChgpersonDoListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      list = new ArrayList();

      String id = (String) pagination.getQueryCriterions().get("id");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      // 单位是否存在
      Org org = null;
      org = orgDAO.queryByCriterions(id, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      pagination.getQueryCriterions().put("name", org.getOrgInfo().getName());// 存放单位名称

      String orgStatus = null;
      orgStatus = orgDAO.getOrgStatus(id);
      if (!orgStatus.equals("2")) {// 录入的单位状态是否正常,!=2为非正常
        throw new BusinessException("BA001单位状态不是正常!!");
      } else {// 是否不存在未启用的其它类型的变更
        boolean chgRateStratus = chgOrgRateDAO.getChgStatus(new Integer(id));
        if (chgRateStratus == false) {
          throw new BusinessException("该单位有未启用的汇缴比例调整!!");
        }
        boolean chgPaymentPaymentStratus = chgPaymentPaymentDAO
            .getChgStatus(new Integer(id));
        if (chgPaymentPaymentStratus == false) {
          throw new BusinessException("该单位有未启用的缴额调整!!");
        }
        boolean chgPaymentSalaryBaseStratus = chgPaymentSalaryBaseDAO
            .getChgStatus(new Integer(id));
        if (chgPaymentSalaryBaseStratus == false) {
          throw new BusinessException("该单位有未启用的工资基数调整!!");
        }
      }
      list = chgPersonTailDAO.findChgpersonDoListByCriterions(id, orderBy,
          order, start, pageSize, page);
      ChgPersonTail chgPersonTail = null;
      for (int i = 0; i < list.size(); i++) {
        chgPersonTail = (ChgPersonTail) list.get(i);
        // 转换
        if ((chgPersonTail.getChgType().equals("1"))
            || (chgPersonTail.getChgType().equals("2"))) {
          chgPersonTail.setChgType(BusiTools.getBusiValue(1,
              BusiConst.CHGSTATUS));
        } else if (chgPersonTail.getChgType().equals("3")) {
          chgPersonTail.setChgType(BusiTools.getBusiValue(2,
              BusiConst.CHGSTATUS));
        } else {
          chgPersonTail.setChgType(BusiTools.getBusiValue(3,
              BusiConst.CHGSTATUS));
        }
        chgPersonTail.setTemp_oldPayStatus(BusiTools.getBusiValue(Integer
            .parseInt(chgPersonTail.getNewPayStatus().toString()),
            BusiConst.OLDPAYMENTSTATE));// 变更后状态
        if (chgPersonTail.getChgreason() != null
            && !chgPersonTail.getChgreason().equals("")) {
          chgPersonTail.setTemp_chgreason(BusiTools.getBusiValue(Integer
              .parseInt(chgPersonTail.getChgreason()),
              BusiConst.CHGPERSONREASON));
        }
        // 后添加，判断按钮
        // String statetype = autoInfoPickDAODW.findStatus(
        // chgPersonTail.getChgPersonHead().getOrg().getId().toString(),
        // chgPersonTail.getChgPersonHead().getId().toString(),BusiConst.ORGBUSINESSTYPE_O);
        // chgPersonTail.getChgPersonHead().setTemp_pick(BusiTools.getBusiValue(Integer.parseInt(statetype),BusiConst.OC_NOT_PICK_UP_INFO));
      }

      int count = chgPersonTailDAO.queryChgpersonDoListByCriterions(id);
      pagination.setNrOfElements(count);

      // 查询变更年月，先查AA204，无就查AA302，无再查AA001的初缴年月
      String date = null;
      ChgPersonHead chgpersonHead = chgPersonHeadDAO.getChgPersonHead_WL(id);
      date = chgpersonHead.getChngMonth();
      if (date == null || date.equals("")) {
        date = monthPaymentHeadDAO.getOrgMonthPayment(id);
        if (date == null || date.equals("")) {
          Org returnOrg = orgDAO.queryById(new Integer(id));
          date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
        } else {
          date = BusiTools.addMonth(date, 1);
        }
      }
      pagination.getQueryCriterions().put("date", date);// 存放该单位的变更年月

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据头表ID查询人员变更记录明细
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List findChgpersonDoListByHeadID(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      list = new ArrayList();

      String headid = (String) pagination.getQueryCriterions().get("headid");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = chgPersonTailDAO.findChgpersonDoListByHeadID_WL(headid, orderBy,
          order, start, pageSize, page);
      ChgPersonTail chgPersonTail = null;
      for (int i = 0; i < list.size(); i++) {
        chgPersonTail = (ChgPersonTail) list.get(i);
        // 转换
        if ((chgPersonTail.getChgType().equals("1"))
            || (chgPersonTail.getChgType().equals("2"))) {
          chgPersonTail.setChgType(BusiTools.getBusiValue(1,
              BusiConst.CHGSTATUS));
        } else if (chgPersonTail.getChgType().equals("3")) {
          chgPersonTail.setChgType(BusiTools.getBusiValue(2,
              BusiConst.CHGSTATUS));
        } else {
          chgPersonTail.setChgType(BusiTools.getBusiValue(3,
              BusiConst.CHGSTATUS));
        }
        chgPersonTail.setTemp_oldPayStatus(BusiTools.getBusiValue(Integer
            .parseInt(chgPersonTail.getNewPayStatus().toString()),
            BusiConst.OLDPAYMENTSTATE));// 变更后状态
        if (chgPersonTail.getChgreason() != null
            && !chgPersonTail.getChgreason().equals("")) {
          chgPersonTail.setTemp_chgreason(BusiTools.getBusiValue(Integer
              .parseInt(chgPersonTail.getChgreason()),
              BusiConst.CHGPERSONREASON));
        }
      }

      ChgPersonTail chgPersonTail_get0 = (ChgPersonTail) list.get(0);
      pagination.getQueryCriterions().put("showwindowid",
          chgPersonTail_get0.getChgPersonHead().getOrg().getId().toString());
      pagination.getQueryCriterions()
          .put(
              "showwindowname",
              chgPersonTail_get0.getChgPersonHead().getOrg().getOrgInfo()
                  .getName());
      pagination.getQueryCriterions().put("showwindowdate",
          chgPersonTail_get0.getChgPersonHead().getChngMonth().toString());

      int count = chgPersonTailDAO.queryChgpersonDoListByHeadID_WL(headid);
      pagination.setNrOfElements(count);

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据条件查询人员变更记录合计项
   */
  public ChgPersonHeadFormule findChgpersonHeadByCriterions(
      Pagination pagination, SecurityInfo securityInfo, String headId)
      throws Exception, BusinessException {
    ChgPersonHeadFormule dto = null;

    String id = (String) pagination.getQueryCriterions().get("id");
    dto = chgPersonHeadDAO.findChgpersonDoListByCriterions_WL(id, securityInfo);
    if (dto != null) {
      dto.setNewOldPayment(dto.getOldOldPayment().add(dto.getInsPayment())
          .subtract(dto.getMulPayment()));
    }
    if (headId != null || (dto != null && dto.getHeadId() != null)) {
      if (headId != null && dto != null) {
        ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(
            headId));
        dto.setBeforeChgperson(new Integer(chgPersonHead.getReserveaA()));
      }
      if (dto != null && dto.getHeadId() != null) {
        ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(
            dto.getHeadId()));
        dto.setBeforeChgperson(new Integer(chgPersonHead.getReserveaA()));
      }
    }
    return dto;
  }

  public ChgPersonHeadFormule findChgpersonHeadByCriterions_wsh(
      Pagination pagination, SecurityInfo securityInfo, String headId)
      throws Exception, BusinessException {
    ChgPersonHeadFormule dto = null;

    String id = (String) pagination.getQueryCriterions().get("id");
    dto = chgPersonHeadDAO.findChgpersonHeadFormuleByCriterions_WL(headId,
        securityInfo);
    if (dto != null) {
      dto.setNewOldPayment(dto.getOldOldPayment().add(dto.getInsPayment())
          .subtract(dto.getMulPayment()));
    }
    if (headId != null || (dto != null && dto.getHeadId() != null)) {
      if (headId != null && dto != null) {
        ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(
            headId));
        dto.setBeforeChgperson(new Integer(chgPersonHead.getReserveaA()));
      }
      if (dto != null && dto.getHeadId() != null) {
        ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(
            dto.getHeadId()));
        dto.setBeforeChgperson(new Integer(chgPersonHead.getReserveaA()));
      }
    }
    return dto;
  }

  /**
   * AJAXAction中用到查询单位
   */
  public Org queryOrgById(Integer id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      Org org = null;
      org = orgDAO.queryByCriterions(id.toString(), null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      org = orgDAO.queryById(id);

      return org;
    } catch (BusinessException be) {
      throw be;
    }

  }

  /**
   * 判断在本单位的转入业务中是否存在要新增的职工 王菱
   */
  public void checkTranInBusiness(String orgID, String empName,
      String documentNum) throws BusinessException {
    List list = new ArrayList();
    try {
      list = tranInHeadDAO.queryByInOrgId(orgID, "5");
      if (list.size() != 0) {
        List list1 = new ArrayList();
        list1 = tranInTailDAO.queryTranInTail(orgID, empName, documentNum);
        if (list1.size() != 0) {
          throw new BusinessException("职工：" + empName + " 存在未完成的转入业务，不能做变更业务！！");
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * 王菱 20070630 插入人员变更信息 单位编号,变更年月，变更类型，证件类型，性别，是否参与汇缴，职工实体
   */
  public String saveChgpersonDO(String orgID, String empID_, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i, SecurityInfo securityInfo) throws Exception, BusinessException {
    String flag = "false";
    try {
      String empID = null;
      String empName = null;
      String cardNum = null;
      List returnList = null;
      ChgPersonTail returnchgPersonTail = null;

      if (chgMap_1.equals("1")) {// 变更类型是：开户
        empName = chgPersonTail.getName();
        cardNum = chgPersonTail.getCardNum();

        // 判断在本单位的转入业务中是否存在要新增的职工
        this.checkTranInBusiness(orgID, chgPersonTail.getName(), chgPersonTail
            .getCardNum());

        // 在未被起用的变更清册里是否存在该职工
        returnchgPersonTail = chgPersonTailDAO.getChgPersonTail_WL(orgID,
            empID, empName, cardNum, chgMap_1, partInMap_1);
        if (returnchgPersonTail != null) {// 存在该职工
          // Emp emp = empDAO.queryByCriterions(returnchgPersonTail.getEmpId()
          // .toString(), orgID);
          // if (emp.getPayStatus().toString().equals("5")) {// 状态为5
          // empID = emp.getEmpId().toString();
          // chgPersonTail.setOldPayStatus(new Integer(5));
          // } else {
          if (i != null) {
            int v_i = Integer.parseInt(i) + 4;
            throw new BusinessException("第" + v_i + "行职工已经开过户，尚未启用！！");
          } else {
            throw new BusinessException("该职工已经开过户，尚未启用！！");
          }
          // }
        } else {// 判断同一职工姓名和证件号码的人是否在该单位存在
          returnList = empDAO.getEmp_WL(orgID, empName, cardNum);
          String payStatus = null;
          if (returnList.size() != 0) {
            payStatus = ((Emp) returnList.get(0)).getPayStatus().toString();
          }

          if (returnList.size() != 0
              && (payStatus != null && payStatus.equals("5"))) {// 存在该职工
            empID = ((Emp) returnList.get(0)).getEmpId().toString();
            chgPersonTail.setOldPayStatus(new Integer(5));

          } else if (returnList.size() != 0) {
            if (i != null) {
              int v_i = Integer.parseInt(i) + 4;
              throw new BusinessException("第" + v_i + "行职工在该单位已经存在职工编码，不能再开户！");
            } else {
              throw new BusinessException("该职工在该单位已经存在职工编码，不能再开户！");
            }
          }
        }

        // 判断该职工在别的单位是否已经开过户,如果已经存在其他账户
        List returnOtherList = new ArrayList();
        if (empID_ != null) {
          empID = empID_;
        } else {
          returnOtherList = empDAO.getOtherOrgMessage_WL(orgID, empName,
              cardNum);
          if (returnOtherList.size() == 0) {
            // empID = null;
          } else if (returnOtherList.size() == 1) {
            Emp emp = (Emp) returnOtherList.get(0);
            empID = emp.getEmpId().toString();
          } else {
            flag = "true";
            return flag;

          }
        }

        // 插入
        this.insertChgpersonDO(orgID, empID, chgDate, chgMap_1, documentMap_1,
            sexMap_1, partInMap_1, chgreasonMap_1, chgPersonTail, securityInfo);

      } else {// 变更类型是：封存或启封

        // 在未被起用的变更清册里是否存在该职工
        returnchgPersonTail = chgPersonTailDAO.getChgPersonTail_WL(orgID,
            empID_, empName, cardNum, chgMap_1, partInMap_1);
        if (returnchgPersonTail != null) {// 存在该职工
          if (i != null) {
            int v_i = Integer.parseInt(i) + 4;
            throw new BusinessException("第" + v_i + "行职工已经做过变更，尚未启用！！");
          } else {
            throw new BusinessException("该职工已经做过变更，尚未启用！！");
          }

        } else {// AA001里该单位下该职工是否可以做该变更:封存的、删除的不能再封存，正常的、调出的、销户的不能再启封

          Emp returnEmp = null;
          returnEmp = empDAO.queryByCriterions(empID_, orgID);
          String payStatus = returnEmp.getPayStatus().toString();
          if (chgMap_1.equals("2")) {// 启封时
            if (payStatus.equals("1") || payStatus.equals("3")
                || payStatus.equals("4")) {
              if (i != null) {
                int v_i = Integer.parseInt(i) + 4;
                throw new BusinessException("第" + v_i + "行职工状态不对，不可以做该变更！");
              } else {
                throw new BusinessException("该职工状态不对，不可以做该变更！");
              }
            }
          } else {// 封存时
            if (payStatus.equals("2") || payStatus.equals("5")) {
              if (i != null) {
                int v_i = Integer.parseInt(i) + 4;
                throw new BusinessException("第" + v_i + "行职工状态不对，不可以做该变更！");
              } else {
                throw new BusinessException("该职工状态不对，不可以做该变更！");
              }
            }
          }
        }

      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    }
    return flag;
  }

  /**
   * 王菱 20070630 插入人员变更信息 单位编号,变更年月，变更类型，证件类型，性别，是否参与汇缴，职工实体
   */
  public String saveChgpersonDO_wsh(String orgID, String empID_,
      String chgDate, String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i, SecurityInfo securityInfo) throws Exception, BusinessException {
    String flag = "false";
    try {
      String empID = null;
      String empName = null;
      String cardNum = null;
      List returnList = null;
      ChgPersonTail returnchgPersonTail = null;

      if (chgMap_1.equals("1")) {// 变更类型是：开户
        empName = chgPersonTail.getName();
        cardNum = chgPersonTail.getCardNum();

        // 判断在本单位的转入业务中是否存在要新增的职工
        this.checkTranInBusiness(orgID, chgPersonTail.getName(), chgPersonTail
            .getCardNum());

        // 在未被起用的变更清册里是否存在该职工
        returnchgPersonTail = chgPersonTailDAO.getChgPersonTail_WL(orgID,
            empID, empName, cardNum, chgMap_1, partInMap_1);
        if (returnchgPersonTail != null) {// 存在该职工
          // Emp emp = empDAO.queryByCriterions(returnchgPersonTail.getEmpId()
          // .toString(), orgID);
          // if (emp.getPayStatus().toString().equals("5")) {// 状态为5
          // empID = emp.getEmpId().toString();
          // chgPersonTail.setOldPayStatus(new Integer(5));
          // } else {
          if (i != null) {
            int v_i = Integer.parseInt(i) + 4;
            throw new BusinessException("第" + v_i + "行职工已经开过户，尚未启用！！");
          } else {
            throw new BusinessException("该职工已经开过户，尚未启用！！");
          }
          // }
        } else {// 判断同一职工姓名和证件号码的人是否在该单位存在
          returnList = empDAO.getEmp_WL(orgID, empName, cardNum);
          String payStatus = null;
          if (returnList.size() != 0) {
            payStatus = ((Emp) returnList.get(0)).getPayStatus().toString();
          }

          if (returnList.size() != 0
              && (payStatus != null && payStatus.equals("5"))) {// 存在该职工
            empID = ((Emp) returnList.get(0)).getEmpId().toString();
            chgPersonTail.setOldPayStatus(new Integer(5));

          } else if (returnList.size() != 0) {
            if (i != null) {
              int v_i = Integer.parseInt(i) + 4;
              throw new BusinessException("第" + v_i + "行职工在该单位已经存在职工编码，不能再开户！");
            } else {
              throw new BusinessException("该职工在该单位已经存在职工编码，不能再开户！");
            }
          }
        }

        // 判断该职工在别的单位是否已经开过户,如果已经存在其他账户
        List returnOtherList = new ArrayList();
        if (empID_ != null) {
          empID = empID_;
        } else {
          returnOtherList = empDAO.getOtherOrgMessage_WL(orgID, empName,
              cardNum);
          if (returnOtherList.size() == 0) {
            // empID = null;
          } else if (returnOtherList.size() == 1) {
            Emp emp = (Emp) returnOtherList.get(0);
            empID = emp.getEmpId().toString();
          } else {
            flag = "true";
            return flag;

          }
        }

        // 插入
        flag = this.insertChgpersonDO_wsh(orgID, empID, chgDate, chgMap_1,
            documentMap_1, sexMap_1, partInMap_1, chgreasonMap_1,
            chgPersonTail, securityInfo);

      } else {// 变更类型是：封存或启封

        // 在未被起用的变更清册里是否存在该职工
        returnchgPersonTail = chgPersonTailDAO.getChgPersonTail_WL(orgID,
            empID_, empName, cardNum, chgMap_1, partInMap_1);
        if (returnchgPersonTail != null) {// 存在该职工
          if (i != null) {
            int v_i = Integer.parseInt(i) + 4;
            throw new BusinessException("第" + v_i + "行职工已经做过变更，尚未启用！！");
          } else {
            throw new BusinessException("该职工已经做过变更，尚未启用！！");
          }

        } else {// AA001里该单位下该职工是否可以做该变更:封存的、删除的不能再封存，正常的、调出的、销户的不能再启封

          Emp returnEmp = null;
          returnEmp = empDAO.queryByCriterions(empID_, orgID);
          String payStatus = returnEmp.getPayStatus().toString();
          if (chgMap_1.equals("2")) {// 启封时
            if (payStatus.equals("1") || payStatus.equals("3")
                || payStatus.equals("4")) {
              if (i != null) {
                int v_i = Integer.parseInt(i) + 4;
                throw new BusinessException("第" + v_i + "行职工状态不对，不可以做该变更！");
              } else {
                throw new BusinessException("该职工状态不对，不可以做该变更！");
              }
            }
          } else {// 封存时
            if (payStatus.equals("2") || payStatus.equals("5")) {
              if (i != null) {
                int v_i = Integer.parseInt(i) + 4;
                throw new BusinessException("第" + v_i + "行职工状态不对，不可以做该变更！");
              } else {
                throw new BusinessException("该职工状态不对，不可以做该变更！");
              }
            }
          }
        }

      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    }
    return flag;
  }

  /**
   * 王菱 插入人员变更添加按钮的数据
   */
  public void insertChgpersonDO(String orgID, String empID_, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    ChgPersonHead chgPersonHead = null;

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();

    // 是否存在该单位未被启用的清册
    chgPersonHead = chgPersonHeadDAO.getChgPersonHead_WL(orgID);
    if (chgPersonHead.getId() == null) {// 不存在头表
      // 插入数据

      // 插入头表
      chgPersonHead = new ChgPersonHead();
      Org org = orgDAO.queryById(new Integer(orgID));
      chgPersonHead.setOrg(org);
      chgPersonHead.setChngMonth(chgDate);
      chgPersonHead.setBizDate(securityInfo.getUserInfo().getBizDate());
      chgPersonHead.setChgStatus(new Integer(1));
      Integer count = empDAO.queryEmpCount(orgID);
      if (count != null) {
        chgPersonHead.setReserveaA(count.toString());
      }
      // 查询该单位变更前应缴总额
      BigDecimal oldPayment = empDAO.getOldPayment_WL(orgID);
      if (oldPayment == null) {
        oldPayment = new BigDecimal(0.00);
      }
      chgPersonHead.setOldPayment(oldPayment);
      Serializable centerHeadId = chgPersonHeadDAO.insert(chgPersonHead);
      center_head_id = centerHeadId.toString();

      // 插入尾表

      chgPersonTail.setChgPersonHead(chgPersonHead);
      // chgPersonTail.setOldPayStatus(new Integer(6));
      if (partInMap_1 != null) {
        if (partInMap_1.equals("0")) {
          chgPersonTail.setChgType("1");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(1));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(1));
          }
        } else {
          chgPersonTail.setChgType("2");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(2));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(2));
          }
        }
        if (empID_ != null) {
          chgPersonTail.setEmpId(new Integer(empID_));
        } else {
          Integer empID = empDAO.makeEmpIdSL();
          // System.out.println("------------wl------------" + empID);
          chgPersonTail.setEmpId(empID);
        }

        chgPersonTail.setCardKind(new Integer(documentMap_1));
        chgPersonTail.setSex(new Integer(sexMap_1));

      } else {

        Emp returnEmp = empDAO.queryByCriterions(chgPersonTail.getEmpId()
            .toString(), orgID);
        chgPersonTail.setName(returnEmp.getEmpInfo().getName());
        chgPersonTail.setCardKind(new Integer(returnEmp.getEmpInfo()
            .getCardKind().toString()));
        chgPersonTail.setCardNum(returnEmp.getEmpInfo().getCardNum());
        chgPersonTail.setBirthday(returnEmp.getEmpInfo().getBirthday());
        chgPersonTail.setSalaryBase(returnEmp.getSalaryBase());
        chgPersonTail.setOrgPay(returnEmp.getOrgPay());
        chgPersonTail.setEmpPay(returnEmp.getEmpPay());

        if (chgMap_1.equals("2")) {
          chgPersonTail.setChgType("3");
          chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
              .toString()));
          chgPersonTail.setNewPayStatus(new Integer(1));
        } else {
          chgPersonTail.setChgType("4");
          if (returnEmp.getPayStatus().toString().equals("1")) {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(5));
          }
        }

        chgPersonTail.setSex(new Integer(returnEmp.getEmpInfo().getSex()
            .toString()));

      }
      chgPersonTail.setChgreason(chgreasonMap_1);
      // 判断职工的证件号码
      if (chgPersonTail.getCardKind().toString().equals("0")
          && chgPersonTail.getCardNum().length() == 15) {
        String num_to18 = empInfoDAO.queryCardNumTo18(chgPersonTail
            .getCardNum());
        chgPersonTail.setStandbyCardNum(num_to18);
      } else {
        chgPersonTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
      }
      chgPersonTailDAO.insert(chgPersonTail);

      // 插入AA319
      ChgPersonBizActivityLog chgPersonBizActivityLog = new ChgPersonBizActivityLog();
      chgPersonBizActivityLog.setBizid(new Integer(chgPersonHead.getId()
          .toString()));
      chgPersonBizActivityLog.setAction(new Integer(BusiConst.BUSINESSSTATE_1));
      chgPersonBizActivityLog.setOpTime(new Date());
      chgPersonBizActivityLog.setOperator(oper);
      chgPersonBizActivityLogDAO.insert(chgPersonBizActivityLog);

    } else {// 存在头表

      // 插入数据

      // 插入尾表

      chgPersonTail.setChgPersonHead(chgPersonHead);

      if (partInMap_1 != null) {
        if (partInMap_1.equals("0")) {
          chgPersonTail.setChgType("1");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(1));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(1));
          }
        } else {
          chgPersonTail.setChgType("2");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(2));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(2));
          }
        }
        if (empID_ != null) {
          chgPersonTail.setEmpId(new Integer(empID_));
        } else {
          Integer empID = empDAO.makeEmpIdSL();
          // System.out.println("------------wl------------" + empID);
          chgPersonTail.setEmpId(empID);
        }

        chgPersonTail.setCardKind(new Integer(documentMap_1));
        chgPersonTail.setSex(new Integer(sexMap_1));

      } else {

        Emp returnEmp = empDAO.queryByCriterions(chgPersonTail.getEmpId()
            .toString(), orgID);
        chgPersonTail.setName(returnEmp.getEmpInfo().getName());
        chgPersonTail.setCardKind(new Integer(returnEmp.getEmpInfo()
            .getCardKind().toString()));
        chgPersonTail.setCardNum(returnEmp.getEmpInfo().getCardNum());
        chgPersonTail.setBirthday(returnEmp.getEmpInfo().getBirthday());
        chgPersonTail.setSalaryBase(returnEmp.getSalaryBase());
        chgPersonTail.setOrgPay(returnEmp.getOrgPay());
        chgPersonTail.setEmpPay(returnEmp.getEmpPay());

        if (chgMap_1.equals("2")) {
          chgPersonTail.setChgType("3");
          chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
              .toString()));
          chgPersonTail.setNewPayStatus(new Integer(1));
        } else {
          chgPersonTail.setChgType("4");
          if (returnEmp.getPayStatus().toString().equals("1")) {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(5));
          }
        }

        chgPersonTail.setSex(new Integer(returnEmp.getEmpInfo().getSex()
            .toString()));
      }

      chgPersonTail.setChgreason(chgreasonMap_1);
      // 判断职工的证件号码
      if (chgPersonTail.getCardKind().toString().equals("0")
          && chgPersonTail.getCardNum().length() == 15) {
        String num_to18 = empInfoDAO.queryCardNumTo18(chgPersonTail
            .getCardNum());
        chgPersonTail.setStandbyCardNum(num_to18);
      } else {
        chgPersonTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
      }
      chgPersonTailDAO.insert(chgPersonTail);

    }

    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(chgPersonHead.getId().toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgID));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    Serializable hafOperateLogtempid = hafOperateLogDAO.insert(hafOperateLog);
    hafOperateLogid = hafOperateLogtempid.toString();

  }

  /**
   * 王菱 插入人员变更添加按钮的数据
   */
  public String insertChgpersonDO_wsh(String orgID, String empID_,
      String chgDate, String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    ChgPersonHead chgPersonHead = null;
    String headId = "";
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();

    // 是否存在该单位未被启用的清册
    chgPersonHead = chgPersonHeadDAO.getChgPersonHead_WL(orgID);
    if (chgPersonHead.getId() == null) {// 不存在头表
      // 插入数据

      // 插入头表
      chgPersonHead = new ChgPersonHead();
      Org org = orgDAO.queryById(new Integer(orgID));
      chgPersonHead.setOrg(org);
      chgPersonHead.setChngMonth(chgDate);
      chgPersonHead.setBizDate(securityInfo.getUserInfo().getBizDate());
      chgPersonHead.setChgStatus(new Integer(1));
      Integer count = empDAO.queryEmpCount(orgID);
      if (count != null) {
        chgPersonHead.setReserveaA(count.toString());
      }
      // 查询该单位变更前应缴总额
      BigDecimal oldPayment = empDAO.getOldPayment_WL(orgID);
      if (oldPayment == null) {
        oldPayment = new BigDecimal(0.00);
      }
      chgPersonHead.setOldPayment(oldPayment);
      Serializable centerHeadId = chgPersonHeadDAO.insert(chgPersonHead);
      center_head_id = centerHeadId.toString();
      headId = centerHeadId.toString();
      // 插入尾表

      chgPersonTail.setChgPersonHead(chgPersonHead);
      // chgPersonTail.setOldPayStatus(new Integer(6));
      if (partInMap_1 != null) {
        if (partInMap_1.equals("0")) {
          chgPersonTail.setChgType("1");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(1));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(1));
          }
        } else {
          chgPersonTail.setChgType("2");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(2));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(2));
          }
        }
        if (empID_ != null) {
          chgPersonTail.setEmpId(new Integer(empID_));
        } else {
          Integer empID = empDAO.makeEmpIdSL();
          // System.out.println("------------wl------------" + empID);
          chgPersonTail.setEmpId(empID);
        }

        chgPersonTail.setCardKind(new Integer(documentMap_1));
        chgPersonTail.setSex(new Integer(sexMap_1));

      } else {

        Emp returnEmp = empDAO.queryByCriterions(chgPersonTail.getEmpId()
            .toString(), orgID);
        chgPersonTail.setName(returnEmp.getEmpInfo().getName());
        chgPersonTail.setCardKind(new Integer(returnEmp.getEmpInfo()
            .getCardKind().toString()));
        chgPersonTail.setCardNum(returnEmp.getEmpInfo().getCardNum());
        chgPersonTail.setBirthday(returnEmp.getEmpInfo().getBirthday());
        chgPersonTail.setSalaryBase(returnEmp.getSalaryBase());
        chgPersonTail.setOrgPay(returnEmp.getOrgPay());
        chgPersonTail.setEmpPay(returnEmp.getEmpPay());

        if (chgMap_1.equals("2")) {
          chgPersonTail.setChgType("3");
          chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
              .toString()));
          chgPersonTail.setNewPayStatus(new Integer(1));
        } else {
          chgPersonTail.setChgType("4");
          if (returnEmp.getPayStatus().toString().equals("1")) {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(5));
          }
        }

        chgPersonTail.setSex(new Integer(returnEmp.getEmpInfo().getSex()
            .toString()));

      }
      chgPersonTail.setChgreason(chgreasonMap_1);
      // 判断职工的证件号码
      if (chgPersonTail.getCardKind().toString().equals("0")
          && chgPersonTail.getCardNum().length() == 15) {
        String num_to18 = empInfoDAO.queryCardNumTo18(chgPersonTail
            .getCardNum());
        chgPersonTail.setStandbyCardNum(num_to18);
      } else {
        chgPersonTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
      }
      chgPersonTailDAO.insert(chgPersonTail);

      // 插入AA319
      ChgPersonBizActivityLog chgPersonBizActivityLog = new ChgPersonBizActivityLog();
      chgPersonBizActivityLog.setBizid(new Integer(chgPersonHead.getId()
          .toString()));
      chgPersonBizActivityLog.setAction(new Integer(BusiConst.BUSINESSSTATE_1));
      chgPersonBizActivityLog.setOpTime(new Date());
      chgPersonBizActivityLog.setOperator(oper);
      chgPersonBizActivityLogDAO.insert(chgPersonBizActivityLog);

    } else {// 存在头表

      // 插入数据

      // 插入尾表
      headId = chgPersonHead.getId().toString();
      chgPersonTail.setChgPersonHead(chgPersonHead);

      if (partInMap_1 != null) {
        if (partInMap_1.equals("0")) {
          chgPersonTail.setChgType("1");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(1));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(1));
          }
        } else {
          chgPersonTail.setChgType("2");
          if (chgPersonTail.getOldPayStatus() != null
              && chgPersonTail.getOldPayStatus().toString().equals("5")) {
            chgPersonTail.setOldPayStatus(new Integer(5));
            chgPersonTail.setNewPayStatus(new Integer(2));
            chgPersonTail.setIsNewUse(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(6));
            chgPersonTail.setNewPayStatus(new Integer(2));
          }
        }
        if (empID_ != null) {
          chgPersonTail.setEmpId(new Integer(empID_));
        } else {
          Integer empID = empDAO.makeEmpIdSL();
          // System.out.println("------------wl------------" + empID);
          chgPersonTail.setEmpId(empID);
        }

        chgPersonTail.setCardKind(new Integer(documentMap_1));
        chgPersonTail.setSex(new Integer(sexMap_1));

      } else {

        Emp returnEmp = empDAO.queryByCriterions(chgPersonTail.getEmpId()
            .toString(), orgID);
        chgPersonTail.setName(returnEmp.getEmpInfo().getName());
        chgPersonTail.setCardKind(new Integer(returnEmp.getEmpInfo()
            .getCardKind().toString()));
        chgPersonTail.setCardNum(returnEmp.getEmpInfo().getCardNum());
        chgPersonTail.setBirthday(returnEmp.getEmpInfo().getBirthday());
        chgPersonTail.setSalaryBase(returnEmp.getSalaryBase());
        chgPersonTail.setOrgPay(returnEmp.getOrgPay());
        chgPersonTail.setEmpPay(returnEmp.getEmpPay());

        if (chgMap_1.equals("2")) {
          chgPersonTail.setChgType("3");
          chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
              .toString()));
          chgPersonTail.setNewPayStatus(new Integer(1));
        } else {
          chgPersonTail.setChgType("4");
          if (returnEmp.getPayStatus().toString().equals("1")) {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(2));
          } else {
            chgPersonTail.setOldPayStatus(new Integer(returnEmp.getPayStatus()
                .toString()));
            chgPersonTail.setNewPayStatus(new Integer(5));
          }
        }

        chgPersonTail.setSex(new Integer(returnEmp.getEmpInfo().getSex()
            .toString()));
      }

      chgPersonTail.setChgreason(chgreasonMap_1);
      // 判断职工的证件号码
      if (chgPersonTail.getCardKind().toString().equals("0")
          && chgPersonTail.getCardNum().length() == 15) {
        String num_to18 = empInfoDAO.queryCardNumTo18(chgPersonTail
            .getCardNum());
        chgPersonTail.setStandbyCardNum(num_to18);
      } else {
        chgPersonTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
      }
      chgPersonTailDAO.insert(chgPersonTail);

    }

    // 插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(chgPersonHead.getId().toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgID));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    Serializable hafOperateLogtempid = hafOperateLogDAO.insert(hafOperateLog);
    hafOperateLogid = hafOperateLogtempid.toString();
    return headId;
  }

  /**
   * 王菱 查询 :当人员变更类型为启封或封存时，进行数据校验
   */
  public ChgPersonTail getChgPersonTail_WL(String orgID, String empID,
      String chgDate, String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i) throws Exception, BusinessException {

    ChgPersonTail returnchgPersonTail = null;

    // 该职工是否是本单位的
    Emp emp = null;
    emp = empDAO.getChgPersonEmp_WL(orgID, empID);
    if (emp == null) {
      if (i != null) {
        int v_i = Integer.parseInt(i) + 4;
        throw new BusinessException("第" + v_i + "行职工不是本单位的！");
      } else {
        throw new BusinessException("该职工不是本单位的！");
      }
    } else {// 校验数据
      try {

        this.saveChgpersonDO(orgID, empID, chgDate, chgMap_1, documentMap_1,
            sexMap_1, partInMap_1, chgreasonMap_1, chgPersonTail, i, null);
      } catch (BusinessException tempbe) {
        tempbe.printStackTrace();
        throw tempbe;
      }
    }

    return returnchgPersonTail;

  }

  /**
   * 根据AA002主键查询职工信息
   */
  public Emp queryEmpByID(String id) {
    Emp emp = null;
    emp = empDAO.queryById(new Integer(id));
    return emp;
  }

  /**
   * 王菱 单选删除
   */
  public void deleteChgPersonTail(Integer id, SecurityInfo securityInfo)
      throws BusinessException {
    try {

      ChgPersonTail chgPersonTail = chgPersonTailDAO.queryById(id);
      String chgPersonHeadID = chgPersonTail.getChgPersonHead().getId()
          .toString();
      ChgPersonHead chgPersonHeadTemp = chgPersonHeadDAO.queryById(new Integer(
          chgPersonHeadID));
      String orgid = chgPersonHeadTemp.getOrg().getId().toString();
      // AA205中是否存在变更清册ID=该删除记录的变更清册ID的其它记录
      List list = new ArrayList();
      list = chgPersonTailDAO.getOtherChgPersonTail(chgPersonHeadID,
          chgPersonTail);
      // 判断是否为aa205的最后一条记录
      List listtemp = new ArrayList();
      listtemp = chgPersonTailDAO.getOtherChgPersonTail_wd(chgPersonHeadID);

      // 判断是否为单位版
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
        // 判断是否是AA205中最后一条记录
        if (listtemp.size() == 1) {
          // 判断提交状态是否为未提取
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid,
              chgPersonHeadID, BusiConst.ORGBUSINESSTYPE_O);
          // 如果存在未提交的，提示先撤销提交
          if (isNoPickUp) {
            throw new BusinessException("请先撤销提交！");
          }
          String stype = autoInfoPickDAODW.findStatus(orgid, chgPersonHeadID,
              BusiConst.ORGBUSINESSTYPE_O);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("中心已提取，不能删除");
          }
        }
      } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
        // 判断是否是AA205中最后一条记录
        if (listtemp.size() == 1) {
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, orgid, chgPersonHeadID,
              BusiConst.ORGBUSINESSTYPE_O);
        }
      }
      if (list.size() != 0) {// 存在其他记录
        chgPersonTailDAO.delete_WL(chgPersonTail);
      } else {// 不存在其他记录
        chgPersonTailDAO.delete_WL(chgPersonTail);
        ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(
            chgPersonHeadID));
        // 删除头表
        chgPersonHeadDAO.delete_WL(chgPersonHead);
        // 删除AA319
        ChgPersonBizActivityLog chgPersonBizActivityLog = chgPersonBizActivityLogDAO
            .queryChgPersonBiz_WL(chgPersonHead.getId().toString(), ""
                + BusiConst.BUSINESSSTATE_1);
        chgPersonBizActivityLogDAO.delete_WL(chgPersonBizActivityLog);

      }

      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();

      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      hafOperateLog.setOpBizId(new Integer(chgPersonHeadID));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(chgPersonTail.getChgPersonHead()
          .getOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);
      this.updateTranInTail(chgPersonTail.getChgPersonHead().getOrg().getId()
          .toString(), chgPersonTail.getEmpId().toString(), "0");

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(e.getMessage());
    }
  }

  /**
   * 王菱 删除当前页
   */
  public void deletePageList(String chgpersonHeadID, SecurityInfo securityInfo)
      throws BusinessException {
    try {

      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();
      // String chgPersonHeadID = "";

      // ChgPersonTail chgPersonTail = (ChgPersonTail) list.get(0);
      // chgPersonHeadID = chgPersonTail.getChgPersonHead().getId().toString();
      // wuht2008－6－18
      List list = chgPersonTailDAO.getChgPersonTailList_WL(chgpersonHeadID);
      if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          ChgPersonTail chgPersonTail = (ChgPersonTail) list.get(i);
          System.out.println();
          this.updateTranInTail(chgPersonTail.getChgPersonHead().getOrg()
              .getId().toString(), chgPersonTail.getEmpId().toString(), "0");
        }
      }
      // wuht2008－6－18
      chgPersonTailDAO.deleteList_WL(chgpersonHeadID);
      ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(
          chgpersonHeadID));
      String orgID = chgPersonHead.getOrg().getId().toString();
      chgPersonHeadDAO.delete_WL(chgPersonHead);// 删除头表
      // 删除AA319
      ChgPersonBizActivityLog chgPersonBizActivityLog = chgPersonBizActivityLogDAO
          .queryChgPersonBiz_WL(chgPersonHead.getId().toString(), ""
              + BusiConst.BUSINESSSTATE_1);
      chgPersonBizActivityLogDAO.delete_WL(chgPersonBizActivityLog);

      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETEALL);
      hafOperateLog.setOpBizId(new Integer(chgpersonHeadID));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(orgID));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("删除失败!");
    }
  }

  // 根据单位ID 和职工编号查询职工信息
  public Emp getEmpMessage(String orgID, String empID) throws BusinessException {
    Emp emp = null;
    emp = empDAO.queryByCriterions(empID, orgID);
    return emp;
  }

  /**
   * 启用
   */
  public void useChgPerson(String orgID, String chgDate,
      SecurityInfo securityInfo) throws BusinessException {
    try {

      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();

      // 更新AA204
      ChgPersonHead chgPersonHead = chgPersonHeadDAO.getChgPersonHead_WL(orgID);
      String chgpersonHeadID = chgPersonHead.getId().toString();
      // 判断维护中的启用要判断该笔记录的状态是否等于1，若不等于1，提示该笔业务已启用
      if (chgPersonHead.getId() == null) {
        throw new BusinessException("该笔业务已启用！！");
      }

      if (chgDate != null && !chgDate.equals("")) {
        chgPersonHead.setChngMonth(chgDate);
      }
      chgPersonHead.setChgStatus(new Integer(2));
      chgPersonHeadDAO.insert(chgPersonHead);

      ChgPersonTail chgpersonTail = null;
      List returnChgpersonTail = chgPersonTailDAO
          .getChgPersonTailList_WL(chgPersonHead.getId().toString());
      // 判断是否为中心版
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER)// 为中心版
      {
        for (int i = 0; i < returnChgpersonTail.size(); i++) {
          ChgPersonTail chgPersonTail = (ChgPersonTail) returnChgpersonTail
              .get(i);
          String chgtype = chgPersonTail.getChgType();
          String name = chgPersonTail.getName();
          String cardnum = chgPersonTail.getCardNum();
          if (chgtype != null && !chgtype.equals("")) {
            if (chgtype.equals("1") || chgtype.equals("2")) {
              // 判断是否存在单位版，flag为true存在单位版
              boolean flag = orgEditionDAO.findIsOrg(orgID);
              if (flag) {
                // 根据aa204的id在da001中查询类型为o的orgheadid
                String orgheadid = autoInfoPickDAO
                    .queryOrgHeadid(chgpersonHeadID);
                if (orgheadid != null && !orgheadid.equals("")) {
                  // 根据orgheadid、name、cardnum在单位版数据库aa205中查询empid
                  String orgempid = chgPersonTailDW.queryEmpid(orgheadid, name,
                      cardnum);
                  if (orgempid != null && !orgempid.equals("")) {
                    // 中心库中aa205的empid
                    String centerempid = chgPersonTail.getEmpId().toString();
                    // 如果centerempid不等于orgempid更新单位库
                    if (!centerempid.equals(orgempid)) {
                      // 调用存储过程
                      empDAODW.changeEmpId_sy(centerempid, orgID, orgempid);
                    }
                  }
                }
              }
            }
          }
        }
      }
      // 更新、插入AA002
      // 判断AA205表中isNewUse新增是否启用过=什么
      Emp emp = null;
      for (int i = 0; i < returnChgpersonTail.size(); i++) {
        chgpersonTail = (ChgPersonTail) returnChgpersonTail.get(i);

        emp = empDAO.queryByCriterions(chgpersonTail.getEmpId().toString(),
            orgID);
        if (emp == null)
          emp = new Emp();

        if (chgpersonTail.getIsNewUse() != null
            && chgpersonTail.getIsNewUse().toString().equals("2")) {
          // 更新AA002
          if (chgpersonTail.getChgType().equals("1")
              || chgpersonTail.getChgType().equals("3")) {
            emp.setPayStatus(new BigDecimal(1));
          } else {
            emp.setPayStatus(new BigDecimal(2));
          }
          Org returnOrg = orgDAO.queryById(new Integer(orgID));
          // EmpInfo empInfo=empInfoDAO.getEmpInfo_yg(chgpersonTail.getEmpId());
          EmpInfo empInfo = empInfoDAO.getEmpInfo(chgpersonTail.getCardNum(),
              chgpersonTail.getName());
          emp.setOrg(returnOrg);
          emp.setEmpInfo(empInfo);
          emp.setSalaryBase(chgpersonTail.getSalaryBase());
          emp.setOrgPay(chgpersonTail.getOrgPay());
          emp.setEmpPay(chgpersonTail.getEmpPay());
          emp.setOrgPayMonth(returnOrg.getOrgPayMonth());
          emp.setEmpPayMonth(returnOrg.getEmpPayMonth());
          // emp.setPreIntegral(new BigDecimal(0.00));
          // emp.setPreBalance(new BigDecimal(0.00));
          // emp.setCurIntegral(new BigDecimal(0.00));
          // emp.setCurBalance(new BigDecimal(0.00));
          emp.setEmpId(chgpersonTail.getEmpId());
          emp.setPayOldYear(chgpersonTail.getOrgPay().add(
              chgpersonTail.getEmpPay()));
          empDAO.insert(emp);

        } else {
          // 插入AA002，BA002
          if (chgpersonTail.getChgType().equals("1")
              || chgpersonTail.getChgType().equals("2")) {// 插入AA002,BA002
            emp = new Emp();
            emp.setEmpId(chgpersonTail.getEmpId());

            Org org = orgDAO.queryById(new Integer(orgID));
            emp.setOrg(org);

            // 三种情况作判断 是否插入两个表
            List returnEmp1 = new ArrayList();
            List returnEmp2 = new ArrayList();
            returnEmp1 = empDAO.getEmp_WL(null, chgpersonTail.getName(),
                chgpersonTail.getCardNum());
            returnEmp2 = empDAO.getEmp_WL(orgID, chgpersonTail.getName(),
                chgpersonTail.getCardNum());
            if (returnEmp1.size() == 0) {// 查询在BA002中不存在该职工信息
              EmpInfo empInfo = new EmpInfo();
              empInfo.setName(chgpersonTail.getName());
              empInfo.setCardKind(new BigDecimal(chgpersonTail.getCardKind()
                  .toString()));
              empInfo.setCardNum(chgpersonTail.getCardNum());
              empInfo.setBirthday(chgpersonTail.getBirthday());
              empInfo.setSex(new BigDecimal(chgpersonTail.getSex().toString()));
              empInfo.setOpendate(securityInfo.getUserInfo().getBizDate());
              // 判断职工的证件号码
              if (empInfo.getCardKind().toString().equals("0")
                  && empInfo.getCardNum().length() == 15) {
                String num_to18 = empInfoDAO.queryCardNumTo18(empInfo
                    .getCardNum());
                empInfo.setStandbyCardNum(num_to18);
              } else {
                empInfo.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
              }
              // 人员变更插入BA002:部门,电话,手机号,月收入
              empInfo.setDepartment(chgpersonTail.getDept());
              empInfo.setTel(chgpersonTail.getTel());
              empInfo.setMobileTle(chgpersonTail.getMobileTel());
              empInfo.setMonthIncome(chgpersonTail.getMonthIncome());

              String empInfoID = empInfoDAO.insert(empInfo).toString();
              emp.getEmpInfo().setId(new Integer(empInfoID));

              Org returnOrg = orgDAO.queryById(new Integer(orgID));
              emp.setSalaryBase(chgpersonTail.getSalaryBase());
              emp.setOrgPay(chgpersonTail.getOrgPay());
              emp.setEmpPay(chgpersonTail.getEmpPay());
              emp.setOrgPayMonth(returnOrg.getOrgPayMonth());
              emp.setEmpPayMonth(returnOrg.getEmpPayMonth());
              emp.setPreIntegral(new BigDecimal(0.00));
              emp.setPreBalance(new BigDecimal(0.00));
              emp.setCurIntegral(new BigDecimal(0.00));
              emp.setCurBalance(new BigDecimal(0.00));
              if (chgpersonTail.getChgType().equals("1")) {
                emp.setPayStatus(new BigDecimal(1));
              } else {
                emp.setPayStatus(new BigDecimal(2));
              }
              // 插入aa002的pay_oldyear字段
              emp.setPayOldYear(chgpersonTail.getOrgPay().add(
                  chgpersonTail.getEmpPay()));
              empDAO.insert(emp);

            } else if (returnEmp2.size() == 0) {// 在AA002中不存在该职工信息
              Org returnOrg = orgDAO.queryById(new Integer(orgID));
              EmpInfo empinfoTemp = empInfoDAO.getEmpInfo(chgpersonTail
                  .getCardNum(), chgpersonTail.getName());
              // EmpInfo
              // empinfoTemp=empInfoDAO.getEmpInfo_yg(chgpersonTail.getEmpId());
              emp.setEmpInfo(empinfoTemp);
              emp.setSalaryBase(chgpersonTail.getSalaryBase());
              emp.setOrgPay(chgpersonTail.getOrgPay());
              emp.setEmpPay(chgpersonTail.getEmpPay());
              emp.setOrgPayMonth(returnOrg.getOrgPayMonth());
              emp.setEmpPayMonth(returnOrg.getEmpPayMonth());
              emp.setPreIntegral(new BigDecimal(0.00));
              emp.setPreBalance(new BigDecimal(0.00));
              emp.setCurIntegral(new BigDecimal(0.00));
              emp.setCurBalance(new BigDecimal(0.00));
              // 插入aa002的pay_oldyear字段
              emp.setPayOldYear(chgpersonTail.getOrgPay().add(
                  chgpersonTail.getEmpPay()));
              if (chgpersonTail.getChgType().equals("1")) {
                emp.setPayStatus(new BigDecimal(1));
              } else {
                emp.setPayStatus(new BigDecimal(2));
              }
              empDAO.insert(emp);

            } else {// 更新AA002
              String empID = chgpersonTail.getEmpId().toString();
              Emp returnEmp = empDAO.queryByCriterions(empID, orgID);
              if (chgpersonTail.getChgType().equals("1")) {
                returnEmp.setPayStatus(new BigDecimal(1));
                returnEmp.setPayOldYear(chgpersonTail.getOrgPay().add(
                    chgpersonTail.getEmpPay()));
              } else {
                returnEmp.setPayStatus(new BigDecimal(2));
                returnEmp.setPayOldYear(chgpersonTail.getOrgPay().add(
                    chgpersonTail.getEmpPay()));
              }
              empDAO.insert(returnEmp);
            }

          } else if (chgpersonTail.getChgType().equals("3")) {// 更新AA002
            String empID = chgpersonTail.getEmpId().toString();
            Emp returnEmp = empDAO.queryByCriterions(empID, orgID);
            returnEmp.setPayStatus(new BigDecimal(1));
            empDAO.insert(returnEmp);

          } else {// 更新AA002
            String empID = chgpersonTail.getEmpId().toString();
            Emp returnEmp = empDAO.queryByCriterions(empID, orgID);
            if (chgpersonTail.getOldPayStatus().toString().equals("1")) {
              emp.setPayStatus(new BigDecimal(2));
            } else {
              emp.setPayStatus(new BigDecimal(5));
            }
            empDAO.insert(returnEmp);

          }
        }
      }
      // 更新AA205
      for (int j = 0; j < returnChgpersonTail.size(); j++) {
        chgpersonTail = (ChgPersonTail) returnChgpersonTail.get(j);
        chgpersonTail.setIsNewUse(new Integer(1));
        chgpersonTail.setReserveaA(chgPersonHead.getOrg().getOrgRate()
            .toString());
        chgpersonTail.setReserveaB(chgPersonHead.getOrg().getEmpRate()
            .toString());

        chgPersonTailDAO.insert(chgpersonTail);
      }
      // 更新AA204变更后应缴人数RESERVEA_A、变更后应缴总额RESERVEA_B
      // 查询AA002状态为1、3、4的职工人数、应缴总额
      ChgPersonHead chgPersonHead1 = chgPersonHeadDAO.queryById(new Integer(
          chgPersonHead.getId().toString()));
      String counts = empDAO.queryEmpCount(chgPersonHead1.getOrg().getId())
          .toString();
      String moneys = empDAO.getOldPayment_WL(
          chgPersonHead1.getOrg().getId().toString()).toString();
      chgPersonHead1.setReserveaB(moneys);
      chgPersonHead1.setReserveaC(counts);

      // 插入AA319
      ChgPersonBizActivityLog chgPersonBizActivityLog = new ChgPersonBizActivityLog();
      chgPersonBizActivityLog.setBizid(new Integer(chgPersonHead.getId()
          .toString()));
      chgPersonBizActivityLog.setAction(new Integer(BusiConst.BUSINESSSTATE_3));
      chgPersonBizActivityLog.setOpTime(new Date());
      chgPersonBizActivityLog.setOperator(oper);
      chgPersonBizActivityLogDAO.insert(chgPersonBizActivityLog);

      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_OPENING);
      hafOperateLog.setOpBizId(new Integer(chgPersonHead.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(orgID));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("启用失败!");
    }

  }

  /**
   * 根据条件查询chgperson记录返回所有记录(导出使用)
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List getChgpersonListAll(Pagination pagination, String orgID,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = new ArrayList();
    List empList = new ArrayList();
    String id = (String) pagination.getQueryCriterions().get("id");

    String orderArray[] = (String[]) pagination.getQueryCriterions().get(
        "orderArray");

    // list = empDAO.queryByCriterionsWZQ(null, id);
    list = empDAO.queryByCriterionsLP(null, id, orderArray, null);
    // 查询变更年月，先查AA204，无就查AA302，无再查AA001的初缴年月
    String date = null;
    ChgPersonHead chgpersonHead = chgPersonHeadDAO.getChgPersonHead_WL(id);
    date = chgpersonHead.getChngMonth();
    Org returnOrg = orgDAO.queryById(new Integer(id));
    if (date == null || date.equals("")) {
      date = monthPaymentHeadDAO.getOrgMonthPayment(id);
      if (date == null || date.equals("")) {
        date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
      } else {
        date = BusiTools.addMonth(date, 1);
      }
    }

    ChgpersonExpDTO chgpersonExpDTO = null;
    Emp emp = null;
    for (int i = 0; i < list.size(); i++) {
      emp = (Emp) list.get(i);
      chgpersonExpDTO = new ChgpersonExpDTO();
      chgpersonExpDTO.setOrgunitcodecontent(emp.getOrg().getId().toString());
      chgpersonExpDTO.setOrgunitnamecontent(emp.getOrg().getOrgInfo().getName()
          .toString());
      chgpersonExpDTO.setOrgunitchgmonthcontent(date);
      chgpersonExpDTO.setPaymode(emp.getOrg().getPayMode().toString());

      chgpersonExpDTO.setEmpcode("" + emp.getEmpId());
      chgpersonExpDTO.setEmpname(emp.getEmpInfo().getName());
      chgpersonExpDTO.setCardkind(emp.getEmpInfo().getCardKind().toString());
      chgpersonExpDTO.setCardnum(emp.getEmpInfo().getCardNum().toString());
      chgpersonExpDTO.setBirthday(emp.getEmpInfo().getBirthday().toString());
      chgpersonExpDTO.setSex(emp.getEmpInfo().getSex().toString());
      if (emp.getEmpInfo().getDepartment() == null) {
        chgpersonExpDTO.setDept("");
      } else {
        chgpersonExpDTO.setDept(emp.getEmpInfo().getDepartment().toString());
      }
      chgpersonExpDTO.setSalarybase(emp.getSalaryBase().toString());
      if (emp.getOrg().getPayMode().toString().equals("2")) {
        chgpersonExpDTO.setOrgpay(emp.getOrgPay().toString());
        chgpersonExpDTO.setEmppay(emp.getEmpPay().toString());
      }
      chgpersonExpDTO.setChgtype("");
      chgpersonExpDTO.setPartin("");
      chgpersonExpDTO.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp
          .getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
      empList.add(chgpersonExpDTO);
    }

    if (empList.size() == 0) {
      chgpersonExpDTO = new ChgpersonExpDTO();
      chgpersonExpDTO.setOrgunitcodecontent(returnOrg.getId().toString());
      chgpersonExpDTO.setOrgunitnamecontent(returnOrg.getOrgInfo().getName()
          .toString());
      chgpersonExpDTO.setOrgunitchgmonthcontent(date);
      chgpersonExpDTO.setPaymode(returnOrg.getPayMode().toString());
      empList.add(chgpersonExpDTO);

    }

    // 插入BA003：

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();

    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_OPENING);
    hafOperateLog.setOpBizId(null);
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgID));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);

    return empList;

  }

  /**
   * 根据条件查询记录（导入）
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List modifyChgpersonBatch(List chgpersonImpTitle,
      List chgpersonImpContent, String orgID, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List failList = null;
    ChgpersonImpTitleDTO chgpersonImpTitleDTO = (ChgpersonImpTitleDTO) chgpersonImpTitle
        .get(0);
    ChgpersonImpContentDTO chgpersonImpContentDTO = null;
    String impOrgID = chgpersonImpTitleDTO.getOrgunitcodecontent();
    impOrgID = BusiTools.convertSixNumber(impOrgID);
    String orgName = chgpersonImpTitleDTO.getOrgunitnamecontent();
    String chgMonth = chgpersonImpTitleDTO.getOrgunitchgmonthcontent();
    if (impOrgID == null || impOrgID.equals("")) {
      throw new BusinessException("单位编号没有填写！！");
    } else {
      int temp_ID = impOrgID.compareTo(orgID);
      if (temp_ID != 0) {
        throw new BusinessException("导入的单位编号不正确！！");
      }
    }

    if (orgName == null || orgName.equals("")) {
      throw new BusinessException("单位名称没有填写！！");
    } else if (chgMonth == null || chgMonth.equals("")) {
      throw new BusinessException("调整年月没有填写！！");
    } else {
      Pattern p = Pattern
          .compile("(([2][0]|[1][9])[0-9]{2}([0][1-9]|[1][0-2]))");
      Matcher m = p.matcher(chgMonth);
      if (m.find() == false) {
        throw new BusinessException("请正确录入调整年月！格式如：200706");
      }
    }

    UtilRule utilRule = new UtilRule();
    for (int i = 2; i < chgpersonImpContent.size(); i++) {
      chgpersonImpContentDTO = (ChgpersonImpContentDTO) chgpersonImpContent
          .get(i);
      String empcode = chgpersonImpContentDTO.getEmpcode();
      String empname = chgpersonImpContentDTO.getEmpname();
      String cardkind = chgpersonImpContentDTO.getCardkind();
      String cardnum = chgpersonImpContentDTO.getCardnum();
      String birthday = chgpersonImpContentDTO.getBirthday();
      String sex = chgpersonImpContentDTO.getSex();
      String dept = chgpersonImpContentDTO.getDept();
      String salarybase = chgpersonImpContentDTO.getSalarybase();
      String orgpay = chgpersonImpContentDTO.getOrgpay();
      String emppay = chgpersonImpContentDTO.getEmppay();
      String chgtype = chgpersonImpContentDTO.getChgtype();
      String partin = chgpersonImpContentDTO.getPartin();
      String chgreason = chgpersonImpContentDTO.getChgreason();

      if (cardnum.length() == 18) {

        birthday = cardnum.substring(6, 14);
        // System.out.println("18--birthday="+empOpenExpContentDTO.getBirthday());
        sex = "" + Integer.parseInt(cardnum.substring(16, 17).toString()) % 2;
        // System.out.println("18--sex="+empOpenImpContentDTO.getCardnum().substring(16,17).toString());
        if (sex.equals("0")) {
          sex = "2";
        }
        // System.out.println("18--sex="+empOpenExpContentDTO.getSex());
      } else {
        birthday = "19" + cardnum.substring(6, 12);
        // System.out.println("15--birthday="+empOpenExpContentDTO.getBirthday());
        sex = "" + Integer.parseInt(cardnum.substring(14, 15).toString()) % 2;
        // System.out.println("15--sex="+empOpenImpContentDTO.getCardnum().substring(14,15).toString());
        if (sex.equals("0")) {
          sex = "2";
        }
        // System.out.println("15--sex="+empOpenExpContentDTO.getSex());
      }
      // 判断职工必输项
      if ((empname == null || empname.equals(""))
          || (cardkind == null || cardkind.equals(""))
          || (cardnum == null || cardnum.equals(""))
          // || ( birthday == null || birthday.equals(""))
          // || (sex == null || sex.equals("") )
          || (salarybase == null || salarybase.equals(""))
          || (chgtype == null || chgtype.equals(""))) {
        i = i + 4;
        throw new BusinessException("第" + i + "行职工的必填项没有录入！！");
      } else if (utilRule.moneyRule(salarybase, 999999999, 2) == false) {
        i = i + 4;
        throw new BusinessException("第" + i + "行职工工资基数录入不正确！！");

      } else if ((!sex.equals("0")) && (!sex.equals("1")) && (!sex.equals("2"))
          && (!sex.equals("9"))) {
        i = i + 4;
        throw new BusinessException("第" + i + "行职工性别录入不正确！！");

      }
      // 判断身份证号
      if (chgtype.equals("1") && cardkind.equals("0")) {
        if (cardnum.length() == 15) {
          Pattern p15 = Pattern
              .compile("(([0-9]{6})()?([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{3}))");
          Matcher m15 = p15.matcher(cardnum);
          if (m15.find() == false) {
            i = i + 4;
            throw new BusinessException("请正确录入第" + i + "行职工身份证号！");
          }
        } else if (cardnum.length() == 18) {
          Pattern p18 = Pattern
              .compile("(([0-9]{6})()?([0-9]{4})([0-9]{2})([0-9]{2})([0-9]{3})([0-9]{1}|x|X))");
          Matcher m18 = p18.matcher(cardnum);
          if (m18.find() == false) {
            i = i + 4;
            throw new BusinessException("请正确录入第" + i + "行职工身份证号！");
          }
        } else {
          i = i + 4;
          throw new BusinessException("请正确录入第" + i + "行职工身份证号！");
        }

      }
      // 当变更类型是新增正常时判断身份证号是否重复
      if (chgtype.equals("1") && cardkind.equals("0")) {
        // 判断当前列表中变更状态为开户的职工中是否存在证件号码相同的人
        for (int j = 2; j < chgpersonImpContent.size(); j++) {
          ChgpersonImpContentDTO chgpersonImpContentDTO1 = (ChgpersonImpContentDTO) chgpersonImpContent
              .get(j);
          if (i != j && chgpersonImpContentDTO1.getChgtype().equals("1")
              && chgpersonImpContentDTO1.getCardkind().equals("0")) {
            String temp_cardNum = chgpersonImpContentDTO1.getCardnum();
            String temp_cardNum15 = "";
            if (temp_cardNum.length() == 18) {
              temp_cardNum15 = temp_cardNum.substring(0, 6)
                  + temp_cardNum.substring(8, 17);
            } else if (temp_cardNum.length() == 15) {
              temp_cardNum15 = temp_cardNum;
            }
            // if (cardnum.equals(temp_cardNum)||cardnum.equals(temp_cardNum15))
            // {
            // i=i+4;
            // j=j+4;
            // throw new BusinessException("第" + i +
            // "行记录中的身份证号与第"+j+"行记录的身份证号相同！！");
            // }
          }
        }
        // 判断该单位下是否存在与其身份证号相同的职工
        // List list = empDAO.queryEmpInfoByChgperson_FYF(empname, cardnum);
        // if (list.size()>0) {
        // i=i+4;
        // throw new BusinessException("第" + i + "行记录中的职工已经存在！！");
        // }
      }
      // 判断是否是按额缴
      Org org = orgDAO.getOrg_WL(impOrgID);
      if (org.getPayMode().toString().equals("2")) {
        if (orgpay == null || orgpay.equals("") || emppay == null
            || emppay.equals("")) {
          i = i + 4;
          throw new BusinessException("第" + i + "行职工单位缴额或职工缴额没有录入！！");
        }

        Pattern pmoney = Pattern.compile("([0-9]{1,20})");
        Matcher m1 = pmoney.matcher(orgpay);
        Matcher m2 = pmoney.matcher(emppay);
        if (utilRule.moneyRule(orgpay, 999999999, 2) == false
            || m1.find() == false) {
          i = i + 4;
          throw new BusinessException("第" + i + "行职工单位缴额录入不正确！！");
        } else if (utilRule.moneyRule(emppay, 999999999, 2) == false
            || m2.find() == false) {
          i = i + 4;
          throw new BusinessException("第" + i + "行职工职工缴额录入不正确！！");
        }
      }

      // 判断录入的变更类型 格式是否正确
      if (!chgtype.equals("1") && !chgtype.equals("2") && !chgtype.equals("3")) {
        i = i + 4;
        throw new BusinessException("第" + i + "行职工变更类型录入不正确！！");

      }
      if (chgtype.equals("1") && (!org.getPayMode().toString().equals("2"))) {
        if (!partin.equals("0") && !partin.equals("1")) {
          i = i + 4;
          throw new BusinessException("第" + i + "行职工是否参与汇缴录入不正确！！");
        }
      }

      List returnOtherList = empDAO.getOtherOrgMessage_WL(orgID, empname,
          cardnum);
      // if (returnOtherList.size() != 0&&chgtype.equals("1")) {
      // i=i+4;
      // throw new BusinessException("第" + i + "行职工已经在其他单位存在账户！！");
      //
      // }

      // 录入的变更原因是否正确
      if (chgreason != null && (!chgreason.equals(""))
          && (!chgreason.equals("1")) && (!chgreason.equals("2"))
          && (!chgreason.equals("3")) && (!chgreason.equals("4"))) {
        i = i + 4;
        throw new BusinessException("第" + i + "行职工的变更原因填写不正确！！");
      }

      // 验证判断结束－－－－－－－－－－－－－－－－－－－－－－－－－

      // 缴存精度ID:payPrecision
      int payPrecision = Integer.parseInt(org.getPayPrecision().toString());
      // 利用缴存精度
      ArithmeticInterface arithmeticInterface = ArithmeticFactory
          .getArithmetic().getArithmeticDAO(payPrecision);

      ChgPersonTail chgPersonTail = new ChgPersonTail();
      if ((empcode != null) && (!empcode.equals(""))) {
        chgPersonTail.setEmpId(new Integer(empcode));
      }
      chgPersonTail.setName(empname);
      chgPersonTail.setCardKind(new Integer(cardkind));
      chgPersonTail.setCardNum(cardnum);
      chgPersonTail.setBirthday(birthday);
      chgPersonTail
          .setSex(new Integer(BusiTools.getBusiKey(sex, BusiConst.SEX)));
      chgPersonTail.setDept(dept);
      chgPersonTail.setSalaryBase(new BigDecimal(salarybase));

      if (arithmeticInterface != null) {
        chgPersonTail.setOrgPay(arithmeticInterface.getPay(new BigDecimal(
            salarybase), org.getOrgRate()));
        chgPersonTail.setEmpPay(arithmeticInterface.getPay(new BigDecimal(
            salarybase), org.getEmpRate()));
      } else {
        chgPersonTail.setOrgPay(new BigDecimal(orgpay));
        chgPersonTail.setEmpPay(new BigDecimal(emppay));
      }

      if (chgtype.equals("1")) {

        this.saveChgpersonDO(orgID, null, chgMonth, chgtype, cardkind, sex,
            partin, chgreason, chgPersonTail, "" + i, securityInfo);

      } else {
        this.getChgPersonTail_WL(orgID, empcode, chgMonth, chgtype, cardkind,
            sex, null, chgreason, null, "" + i);
        this.insertChgpersonDO(orgID, null, chgMonth, chgtype, cardkind, sex,
            null, chgreason, chgPersonTail, securityInfo);
      }

    }

    return failList;
  }

  /**
   * 王菱 人员变更维护 第一次进入时取得列表信息
   */
  public List findChgpersonMaintainList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = chgPersonHeadDAO.getChgPersonHeadFirst_WL(orderBy, order, start,
          pageSize, page, securityInfo);
      ChgPersonHead chgPersonHead = null;
      for (int i = 0; i < list.size(); i++) {
        chgPersonHead = (ChgPersonHead) list.get(i);
        chgPersonHead.getOrg().getOrgInfo().getName();

        ChgPersonHeadFormule dto = chgPersonHeadDAO
            .queryFormule3_WL(chgPersonHead.getId().toString());
        chgPersonHead.setSumChgPerson(dto.getSumChgPerson());
        chgPersonHead.setOldOldPayment(dto.getOldOldPayment());
        // chgPersonHead.setSumSumPay(new BigDecimal(dto.getSumSumPay()));

        chgPersonHead.setSumSumPay(dto.getOldOldPayment().add(
            dto.getInsPayment()).subtract(dto.getMulPayment()));
        // 转换
        chgPersonHead.setTemp_chgStatus(BusiTools.getBusiValue(Integer
            .parseInt(chgPersonHead.getChgStatus().toString()),
            BusiConst.CHGTYPESTATUS));
        // 提取状态
        // String statetype = autoInfoPickDAODW.findStatus(
        // chgPersonHead.getOrg().getId().toString(),
        // chgPersonHead.getId().toString(),BusiConst.ORGBUSINESSTYPE_O);
        // chgPersonHead.setTemp_pick(BusiTools.getBusiValue(Integer.parseInt(statetype),BusiConst.OC_NOT_PICK_UP_INFO));
      }

      int count = chgPersonHeadDAO
          .queryChgpersonMaintainListFirst_WL(securityInfo);

      pagination.setNrOfElements(count);

      return list;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  /**
   * 王菱 人员变更维护 取得列表信息
   */
  public List findChgpersonMaintainListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String orgID = (String) pagination.getQueryCriterions().get("orgID");
      if (orgID != null && orgID != "") {
        if (orgID.length() == 10) {
          orgID = orgID.substring(1, orgID.length());
        }
      }
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String chgDate = (String) pagination.getQueryCriterions().get("chgDate");
      String endchgDate = (String) pagination.getQueryCriterions().get(
          "endchgDate");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = chgPersonHeadDAO.getChgPersonHead_wsh(orderBy, order, start,
          pageSize, orgID, orgName, chgDate, endchgDate, page, securityInfo);
      ChgPersonHead chgPersonHead = null;
      for (int i = 0; i < list.size(); i++) {
        chgPersonHead = (ChgPersonHead) list.get(i);
        chgPersonHead.getOrg().getOrgInfo().getName();

        ChgPersonHeadFormule dto = chgPersonHeadDAO
            .queryFormule3_WL(chgPersonHead.getId().toString());
        chgPersonHead.setSumChgPerson(dto.getSumChgPerson());
        chgPersonHead.setOldOldPayment(dto.getOldOldPayment());
        // chgPersonHead.setSumSumPay(new BigDecimal(dto.getSumSumPay()));

        chgPersonHead.setSumSumPay(dto.getOldOldPayment().add(
            dto.getInsPayment()).subtract(dto.getMulPayment()));
        // 转换
        chgPersonHead.setTemp_chgStatus(BusiTools.getBusiValue(Integer
            .parseInt(chgPersonHead.getChgStatus().toString()),
            BusiConst.CHGTYPESTATUS));
        // 提取状态
        // String statetype = autoInfoPickDAODW.findStatus(
        // chgPersonHead.getOrg().getId().toString(),
        // chgPersonHead.getId().toString(),BusiConst.ORGBUSINESSTYPE_O);
        // chgPersonHead.setTemp_pick(BusiTools.getBusiValue(Integer.parseInt(statetype),BusiConst.OC_NOT_PICK_UP_INFO));
      }

      int count = chgPersonHeadDAO.queryChgpersonMaintainListByCriterions_wsh(
          orgID, orgName, chgDate, endchgDate, securityInfo);
      pagination.setNrOfElements(count);

      return list;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  /**
   * 根据AA204头表ID查询orgID
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public String getOrgID(String chgPersonHeadID) throws Exception,
      BusinessException {
    String orgID = null;
    orgID = chgPersonHeadDAO.getOrgID_WL(chgPersonHeadID);
    return orgID;

  }

  /**
   * 根据AA204头表ID查询尾表LIST 然后调用this.deletePageList方法进行维护页面删除
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public void deleteChgPersonALL(String chgPersonHeadID,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(
        chgPersonHeadID));
    String orgid = chgPersonHead.getOrg().getId().toString();
    // 判断是否为单位版
    int isOrgEdition = securityInfo.getIsOrgEdition();
    if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
      // 判断提交状态是否为未提取
      boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, chgPersonHeadID,
          BusiConst.ORGBUSINESSTYPE_O);
      // 如果存在未提交的，提示先撤销提交
      if (isNoPickUp) {
        throw new BusinessException("请先撤销提交！");
      }
      String stype = autoInfoPickDAODW.findStatus(orgid, chgPersonHeadID,
          BusiConst.ORGBUSINESSTYPE_O);
      if (stype.equals(BusiConst.OC_PICK_UP)) {
        throw new BusinessException("中心已提取，不能删除");
      }
    } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
      String center_head_id = "";
      String centerBizDate = "";
      autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
          center_head_id, centerBizDate, orgid, chgPersonHeadID,
          BusiConst.ORGBUSINESSTYPE_O);
    }
    // List returnChgPersonTailList = null;
    // returnChgPersonTailList = chgPersonTailDAO
    // .getChgPersonTailList_WL(chgPersonHeadID);
    // this.deletePageList(returnChgPersonTailList, securityInfo);
    // returnChgPersonTailList = chgPersonTailDAO
    // .getChgPersonTailList_WL(chgPersonHeadID);
    this.deletePageList(chgPersonHeadID, securityInfo);
  }

  /**
   * 根据AA204头表ID进行数据校验,是否可以进行业务撤销
   */
  public void checkDelUseMessage(String chgPersonHeadID,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      String orgID = chgPersonHeadDAO.getOrgID_WL(chgPersonHeadID);
      // 该笔变更后是否存在其它变更
      Integer maxHeadID = chgPersonHeadDAO.getMaxHeadID_WL(orgID);
      // int temp_ID = (maxHeadID.toString()).compareTo(chgPersonHeadID);
      int temp_ID = maxHeadID.intValue() - Integer.parseInt(chgPersonHeadID);
      if (temp_ID > 0) {
        throw new BusinessException("请先撤消后面的变更！");
      }
      // 该笔变更是否被汇缴应用
      ChgPersonHead chgPersonHead = chgPersonHeadDAO
          .getHeadMessage_WL(chgPersonHeadID);
      if (chgPersonHead.getPaymentHead() != null) {
        throw new BusinessException("请先撤消应用该笔变更的汇缴！");
      }
      // 该单位下是否存在没有启用的人员变更
      List list = null;
      list = chgPersonHeadDAO.getChgPersonHeadNoQY_WL(orgID);
      if (list.size() != 0) {
        throw new BusinessException("存在未启用的人员变更，请先启用！");
      }
      // 撤销启用
      this.deluserChgPersonMessage(chgPersonHeadID, securityInfo);

    } catch (BusinessException b) {
      b.printStackTrace();
      throw b;
    }
  }

  // 撤销启用
  public void deluserChgPersonMessage(String chgPersonHeadID,
      SecurityInfo securityInfo) throws BusinessException {
    try {

      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();

      // 更新AA204
      ChgPersonHead chgPersonHead = chgPersonHeadDAO
          .getHeadMessage_WL(chgPersonHeadID);
      chgPersonHead.setChgStatus(new Integer(1));
      chgPersonHead.setReserveaB("");
      chgPersonHead.setReserveaC("");
      chgPersonHeadDAO.insert(chgPersonHead);

      List returnChgpersonTailList = chgPersonTailDAO
          .getChgPersonTailList_WL(chgPersonHead.getId().toString());

      // 更新AA205
      ChgPersonTail chgpersonTail = null;
      for (int i = 0; i < returnChgpersonTailList.size(); i++) {
        chgpersonTail = (ChgPersonTail) returnChgpersonTailList.get(i);
        if (chgpersonTail.getChgType().equals("1")
            || chgpersonTail.getChgType().equals("2")) {
          chgpersonTail.setIsNewUse(new Integer(2));
        }
        chgPersonTailDAO.insert(chgpersonTail);
      }

      // 更新AA002
      // 判断AA205表中isNewUse新增是否启用过=2;对应的AA002表中的职工，更新payStatus 缴存状态=5:删除
      Emp emp = null;
      ChgPersonTail returnChgPersonTail = null;
      for (int i = 0; i < returnChgpersonTailList.size(); i++) {
        returnChgPersonTail = (ChgPersonTail) returnChgpersonTailList.get(i);

        emp = empDAO.queryByCriterions(returnChgPersonTail.getEmpId()
            .toString(), chgPersonHead.getOrg().getId().toString());

        if (returnChgPersonTail.getChgType().equals("1")
            || returnChgPersonTail.getChgType().equals("2")) {
          if (returnChgPersonTail.getIsNewUse().toString().equals("2")) {
            emp.setPayStatus(new BigDecimal("" + BusiConst.OLDPAYMENTSTATE_5));
          }
        } else {
          emp.setPayStatus(new BigDecimal(returnChgPersonTail.getOldPayStatus()
              .toString()));
        }

        empDAO.insert(emp);
      }

      // 删除AA319
      ChgPersonBizActivityLog chgPersonBizActivityLog = chgPersonBizActivityLogDAO
          .queryChgPersonBiz_WL(chgPersonHead.getId().toString(), ""
              + BusiConst.BUSINESSSTATE_3);
      chgPersonBizActivityLogDAO.delete_WL(chgPersonBizActivityLog);

      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_REVOCATION);
      hafOperateLog.setOpBizId(new Integer(chgPersonHead.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(chgPersonHead.getOrg().getId()
          .toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("撤销启用失败!");
    }

  }

  public List getOtherOrgMessage_WL(String orgID, ChgPersonTail chgPersonTail) {
    List returnOtherList = new ArrayList();
    String empName = chgPersonTail.getName();
    String cardNum = chgPersonTail.getCardNum();
    returnOtherList = empDAO.getOtherOrgMessage_WL(orgID, empName, cardNum);
    return returnOtherList;
  }

  /**
   * 根据尾表ID查询对应头表ID
   */
  public String queryChgpersonHeadID(String tailID) {
    // TODO Auto-generated method stub
    String headID = "";
    try {
      headID = chgPersonHeadDAO.queryChgpersonHeadID_WL(tailID);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return headID;
  }

  /**
   * 张列 判断本年余额和往年余额 是否为0
   * 
   * @param empid
   * @param orgid
   * @return
   * @throws Exception
   */
  public boolean isChgperson(String empid, String orgid) throws Exception {
    try {
      String cur = empDAO.findChgpersonCurBalance(empid, orgid);
      String per = empDAO.findChgpersonPerBalance(empid, orgid);
      if (cur.equals("0") && per.equals("0")) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * 张列 人员当前的状态
   * 
   * @param empid
   * @param orgid
   * @return
   * @throws Exception
   */
  public String getChgpersonStatus(String empid, String orgid) throws Exception {
    String status = "";
    try {
      status = empDAO.findChgpersonStatus(empid, orgid);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return status;
  }

  public List isCardNumSame(String orgId, String empName, String cardNum)
      throws BusinessException, Exception {
    List list = empDAO.queryEmpInfoByChgperson_FYF(orgId, empName, cardNum);
    return list;
  }

  // 提交数据
  public void PickInDate(String id, SecurityInfo securityInfo, String flag)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(id));
      String orgid = chgPersonHead.getOrg().getId().toString();
      boolean isNoPickIn = autoInfoPickDAODW.isNOPickIn(orgid, id,
          BusiConst.ORGBUSINESSTYPE_O);
      if (isNoPickIn) {
        throw new BusinessException("该笔业务已提交！");
      } else {
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(orgid));
        autoInfoPick.setOrgHeadId(new Integer(id));
        autoInfoPick.setCenterHeadId(null);
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_O);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPick.setOrgBizDate(new Date());
        if (chgPersonHead.getPaymentHead() != null) {
          autoInfoPick.setPayHeadId(new Integer(chgPersonHead.getPaymentHead()
              .getId().toString()));
        } else {
          autoInfoPick.setPayHeadId(null);
        }

        autoInfoPickDAODW.insert(autoInfoPick);
        // 插入BA003：
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("1")) {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO));
        } else {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_MAINTAIN));
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
        hafOperateLog.setOpBizId(new Integer(id));// AA204.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgid));
        hafOperateLogDAO.insert(hafOperateLog);
      }

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  // 撤消提交数据
  public void removePickInDate(String id, SecurityInfo securityInfo, String flag)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      ChgPersonHead chgPersonHead = chgPersonHeadDAO.queryById(new Integer(id));
      String orgid = chgPersonHead.getOrg().getId().toString();
      String status = autoInfoPickDAODW.findStatus(orgid, id,
          BusiConst.ORGBUSINESSTYPE_O);
      if (status.equals(BusiConst.OC_NOT_PICK_UP)) {

        // 删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgid, id,
            BusiConst.ORGBUSINESSTYPE_O);
        // 插入BA003：
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("1")) {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO));
        } else {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_MAINTAIN));
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
        hafOperateLog.setOpBizId(new Integer(id));// AA301.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgid));
        hafOperateLogDAO.insert(hafOperateLog);
      } else {
        if (status.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("该业务已被中心提取，不可以撤销！");
        }
        if (status.equals(BusiConst.OC_PICK_UP_OVER)) {
          throw new BusinessException("该笔业务没有提交，不可以撤销！");
        }
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  // 提取数据
  public void pickUpDate(String orgid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      orgid = BusiTools.convertSixNumber(orgid);
      int count = autoInfoPickDAO.queryNoPickUpListbyOrgid(orgid);
      if (count == 0) {
        throw new BusinessException("该单位不存在未提取的记录！");
      }
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      String date = securityInfo.getUserInfo().getBizDate();
      // 根据orgid查询 orgheadid 和type
      Object[] obj = autoInfoPickDAO.queryOrgHeadidAndType(orgid);
      if (obj != null) {
        if ((obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_O))) {
          String orgheadID = autoInfoPickDAO.findOrgHeadid(orgid,
              BusiConst.ORGBUSINESSTYPE_O, BusiConst.OC_NOT_PICK_UP);
          // 查询导入用的list
          List chgpersonImpTitle = chgPersonHeadDAODW
              .findchgpersonImpTitle(orgid);
          List chgpersonImpContent = chgPersonTailDW
              .findchgpersonImpContent(orgheadID);
          // 调用导入方法
          modifyChgpersonBatch(chgpersonImpTitle, chgpersonImpContent, orgid,
              securityInfo);
          // 删除导入的插入BA003
          if (!hafOperateLogid.equals("")) {
            hafOperateLogDAO.delete(hafOperateLogid);
          }
          // 更新da001
          autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP,
              center_head_id, new Date().toString(), orgid, orgheadID,
              BusiConst.ORGBUSINESSTYPE_O);
          // 插入BA003：
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO));
          hafOperateLog.setOpButton(Integer
              .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
          hafOperateLog.setOpBizId(new Integer(center_head_id));// AA301.ID
          hafOperateLog.setOperator(name);
          hafOperateLog.setOpIp(ip);
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOrgId(new Integer(orgid));
          hafOperateLogDAO.insert(hafOperateLog);
        } else {
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_M)) {
            throw new BusinessException("存在未提取的工资基数调整变更！");
          }
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_N)) {
            throw new BusinessException("存在未提取的缴额调整变更！");
          }

        }
      } else {
        Object[] objtem = autoInfoPickDAO.findOrgHeadidAndType(orgid);
        if (objtem != null) {
          if ((objtem[1].toString().equals(BusiConst.ORGBUSINESSTYPE_O))) {
            String orgheadID = autoInfoPickDAO.findOrgHeadid(orgid,
                BusiConst.ORGBUSINESSTYPE_O, BusiConst.OC_NOT_PICK_UP);
            // 查询导入用的list
            List chgpersonImpTitle = chgPersonHeadDAODW
                .findchgpersonImpTitle(orgid);
            List chgpersonImpContent = chgPersonTailDW
                .findchgpersonImpContent(orgheadID);
            // 调用导入方法
            modifyChgpersonBatch(chgpersonImpTitle, chgpersonImpContent, orgid,
                securityInfo);
            // 删除导入的插入BA003
            if (!hafOperateLogid.equals("")) {
              hafOperateLogDAO.delete(hafOperateLogid);
            }
            // 更新da001
            autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP,
                center_head_id, new Date().toString(), orgid, orgheadID,
                BusiConst.ORGBUSINESSTYPE_O);
            // 插入BA003：
            HafOperateLog hafOperateLog = new HafOperateLog();
            hafOperateLog.setOpSys(new Integer(
                BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
            hafOperateLog.setOpModel(Integer
                .toString(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO));
            hafOperateLog.setOpButton(Integer
                .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
            hafOperateLog.setOpBizId(new Integer(center_head_id));// AA301.ID
            hafOperateLog.setOperator(name);
            hafOperateLog.setOpIp(ip);
            hafOperateLog.setOpTime(new Date());
            hafOperateLog.setOrgId(new Integer(orgid));
            hafOperateLogDAO.insert(hafOperateLog);
          } else {
            if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_M)) {
              throw new BusinessException("存在未提取的工资基数调整变更！");
            }
            if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_N)) {
              throw new BusinessException("存在未提取的缴额调整变更！");
            }
          }
        }
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  public void deleteFnTempTable(String type) throws Exception,
      BusinessException {
    chgPersonTailDAO.deleteFnTempTable(type);
  }

  public String isAutoChange(String orgId) throws Exception, BusinessException {
    // 判断自动变更参数是否可以进行自动变更0不启用，1启用
    String autoChangeParam = officeParaDAO.queryAutoChangeParamByParamNum("10");
    if (autoChangeParam == null || autoChangeParam.equals("0")) {
      return "0";
    } else {
      // 查询该单位下的职工是否存在职工状态=3或4
      List list = empDAO.queryAutoChangeEmpInfo(orgId);
      List temp_list = new ArrayList();
      if (list.size() == 0) {
        return "0";
      } else {
        // 得到AA204id
        Object id = chgPersonHeadDAO.queryIdByChgStatus(orgId);
        // 在插入前删除临时表
        chgPersonTailDAO.deleteFnTempTable("0");
        if (id == null) {
          // 循环插入插入临时表
          temp_list = list;
          chgPersonTailDAO.insertFnTempTable_wl(temp_list);
          // for (int i = 0; i < list.size(); i++) {
          // Object[] obj = (Object[]) list.get(i);
          // chgPersonTailDAO.insertFnTempTable(obj[0].toString(), obj[1]
          // .toString(), obj[2].toString(), obj[3].toString());
          // }
          return "1";
        } else {
          // 判断职工是否已经存在变更表中
          for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            String empId = obj[0].toString();
            int count = chgPersonTailDAO.queryEmpChangeById(id.toString(),
                empId);
            if (count == 0) {
              // 直接插入临时表
              temp_list.add(list.get(i));
              // chgPersonTailDAO.insertFnTempTable(obj[0].toString(), obj[1]
              // .toString(), obj[2].toString(), obj[3].toString());
            }
          }
          chgPersonTailDAO.insertFnTempTable_wl(temp_list);
          // 判断临时表中是否存在数据
          int tempTableCount = chgPersonTailDAO.queryFnTempTable();
          if (tempTableCount == 0) {
            return "0";
          } else {
            return "1";
          }
        }
      }
    }
  }

  // 吴洪涛 2008-6-18/
  public ChgPersonAutoopenAF findChgPersonAutoopenAF(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List chgPersonAutoopenDTOlist = new ArrayList();
    ChgPersonAutoopenAF chgPersonAutoopenAF = new ChgPersonAutoopenAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    try {
      List list = chgPersonTailDAO.queryTranInTailByCriterions(orgId, null,
          orderBy, order, start, pageSize, page);
      if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          TranInTail tranInTail = (TranInTail) list.get(i);
          ChgPersonAutoopenDTO chgPersonAutoopenDTO = new ChgPersonAutoopenDTO();
          chgPersonAutoopenDTO.setId(tranInTail.getId().toString());
          chgPersonAutoopenDTO.setEmpName(tranInTail.getName().toString());
          chgPersonAutoopenDTO.setEmpId(tranInTail.getEmpId().toString());
          chgPersonAutoopenDTO.setCardNum(tranInTail.getCardNum().toString());
          chgPersonAutoopenDTO.setPayStatus("封存");

          chgPersonAutoopenDTOlist.add(chgPersonAutoopenDTO);
        }
      }
      chgPersonAutoopenAF.setList(chgPersonAutoopenDTOlist);
      List listAll = chgPersonTailDAO.queryTranInTailByCriterionsAll(orgId,
          null, orderBy, order, start, pageSize, page);
      chgPersonAutoopenAF.setListAll(listAll);
      pagination.setNrOfElements(listAll.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return chgPersonAutoopenAF;
  }

  public List findAutoChangePopList(Pagination pagination) throws Exception,
      BusinessException {

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List list = chgPersonTailDAO.queryAutoChangePopList(orderBy, order, start,
        pageSize);
    // 转换缴存状态
    for (int i = 0; i < list.size(); i++) {
      AutoChangePopDTO autoChangePopDTO = (AutoChangePopDTO) list.get(i);
      autoChangePopDTO.setPayStatus((BusiTools
          .getBusiValue(Integer.parseInt(autoChangePopDTO.getPayStatus()),
              BusiConst.OLDPAYMENTSTATE)));
    }
    List countList = chgPersonTailDAO.queryAutoChangePopCount();

    pagination.setNrOfElements(countList.size());
    return list;
  }

  public List findAutoChangePopListALL(Pagination pagination) throws Exception,
      BusinessException {
    List list = chgPersonTailDAO.queryAutoChangePopListALL();
    // 转换缴存状态
    for (int i = 0; i < list.size(); i++) {
      AutoChangePopDTO autoChangePopDTO = (AutoChangePopDTO) list.get(i);
      autoChangePopDTO.setPayStatus((BusiTools
          .getBusiValue(Integer.parseInt(autoChangePopDTO.getPayStatus()),
              BusiConst.OLDPAYMENTSTATE)));
    }
    return list;
  }

  public ChgpersonEmpInfoDTO findChgpersonEmpInfo(String orgId, String empId)
      throws Exception, BusinessException {
    ChgpersonEmpInfoDTO chgpersonEmpInfoDTO = chgPersonTailDAO.queryEmpInfo(
        orgId, empId);
    return chgpersonEmpInfoDTO;
  }

  /**
   * bit add 查询身份证号和姓名相同的单位id
   */
  public int isExistSameEmpInfo(String orgId, String empName, String cardNum)
      throws Exception, BusinessException {
    return empDAO.queryCountByCid_Name(orgId, empName, cardNum);
  }

  // 吴洪涛 2008-6-16
  public List findChgpersonDoListAllByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      list = new ArrayList();
      String id = (String) pagination.getQueryCriterions().get("id");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      // 单位是否存在
      Org org = null;
      org = orgDAO.queryByCriterions(id, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      pagination.getQueryCriterions().put("name", org.getOrgInfo().getName());// 存放单位名称

      String orgStatus = null;
      orgStatus = orgDAO.getOrgStatus(id);
      if (!orgStatus.equals("2")) {// 录入的单位状态是否正常,!=2为非正常
        throw new BusinessException("BA001单位状态不是正常!!");
      } else {// 是否不存在未启用的其它类型的变更
        boolean chgRateStratus = chgOrgRateDAO.getChgStatus(new Integer(id));
        if (chgRateStratus == false) {
          throw new BusinessException("该单位有未启用的汇缴比例调整!!");
        }
        boolean chgPaymentPaymentStratus = chgPaymentPaymentDAO
            .getChgStatus(new Integer(id));
        if (chgPaymentPaymentStratus == false) {
          throw new BusinessException("该单位有未启用的缴额调整!!");
        }
        boolean chgPaymentSalaryBaseStratus = chgPaymentSalaryBaseDAO
            .getChgStatus(new Integer(id));
        if (chgPaymentSalaryBaseStratus == false) {
          throw new BusinessException("该单位有未启用的工资基数调整!!");
        }
      }
      String date = null;
      ChgPersonHead chgpersonHead = chgPersonHeadDAO.getChgPersonHead_WL(id);
      date = chgpersonHead.getChngMonth();
      if (date == null || date.equals("")) {
        date = monthPaymentHeadDAO.getOrgMonthPayment(id);
        if (date == null || date.equals("")) {
          Org returnOrg = orgDAO.queryById(new Integer(id));
          date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
        } else {
          date = BusiTools.addMonth(date, 1);
        }
      }
      List listAll = chgPersonTailDAO.findChgpersonDoListAllByCriterions(id,
          orderBy, order, start, pageSize, page);
      if (listAll != null && listAll.size() > 0) {
        for (int i = 0; i < listAll.size(); i++) {
          ChgPersonTail chgPersonTail = (ChgPersonTail) listAll.get(i);
          ChgPersonCellListListExportDTO chgPersonCellListListExportDTO = new ChgPersonCellListListExportDTO();
          chgPersonCellListListExportDTO.setChgMonth(date);
          chgPersonCellListListExportDTO.setOrgId(id);
          chgPersonCellListListExportDTO.setOrgName(org.getOrgInfo().getName());
          chgPersonCellListListExportDTO.setCardNum(chgPersonTail.getCardNum());
          chgPersonCellListListExportDTO.setEmpId(chgPersonTail.getEmpId()
              .toString());
          chgPersonCellListListExportDTO.setName(chgPersonTail.getName());
          chgPersonCellListListExportDTO.setEmpPay(chgPersonTail.getEmpPay()
              .toString());
          chgPersonCellListListExportDTO.setOrgPay(chgPersonTail.getOrgPay()
              .toString());
          chgPersonCellListListExportDTO.setSalaryBase(chgPersonTail
              .getSalaryBase().toString());
          chgPersonCellListListExportDTO.setSumPay(chgPersonTail.getSumPay()
              .toString());
          // 转换
          if ((chgPersonTail.getChgType().equals("1"))
              || (chgPersonTail.getChgType().equals("2"))) {
            chgPersonCellListListExportDTO.setChgType(BusiTools.getBusiValue(1,
                BusiConst.CHGSTATUS));
          } else if (chgPersonTail.getChgType().equals("3")) {
            chgPersonCellListListExportDTO.setChgType(BusiTools.getBusiValue(2,
                BusiConst.CHGSTATUS));
          } else {
            chgPersonCellListListExportDTO.setChgType(BusiTools.getBusiValue(3,
                BusiConst.CHGSTATUS));
          }
          chgPersonCellListListExportDTO.setTemp_oldPayStatus(BusiTools
              .getBusiValue(Integer.parseInt(chgPersonTail.getNewPayStatus()
                  .toString()), BusiConst.OLDPAYMENTSTATE));// 变更后状态
          if (chgPersonTail.getChgreason() != null
              && !chgPersonTail.getChgreason().equals("")) {
            chgPersonCellListListExportDTO.setTemp_chgreason(BusiTools
                .getBusiValue(Integer.parseInt(chgPersonTail.getChgreason()),
                    BusiConst.CHGPERSONREASON));
          }
          list.add(chgPersonCellListListExportDTO);
        }
      }

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 吴洪涛 2008-6-16//查询出银行名称
  public String queryCollectionBankNameById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CollBank collBank = new CollBank();
    try {
      // 单位是否存在
      Org org = null;
      org = orgDAO.queryByCriterions(id, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      String collectionBankId = org.getOrgInfo().getCollectionBankId();
      if (collectionBankId != null && !collectionBankId.equals("")) {
        collBank = chgPersonTailDAO.getCollBankByCollBankid(collectionBankId);
      }
      if (collBank == null) {
        collBank = new CollBank();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBank.getCollBankName();
  }

  public String getNamePara() throws Exception {
    String name = "";
    name = collParaDAO.getNamePara();
    return name;
  }

  /*****************************************************************************
   * （AA312表中加一字段：is_auto_chg varchar2(1) N 是否应用过自动变更0否，1是。
   * 当转入单位做人员变更时，录入转入单位账号后查询AA311，AA312转入单位并is_auto_chg=0，
   * 查询后的信息显示在弹出框里。在弹出框中点确定时更新AA312 is_auto_chg=1。 当变更撤销时更新AA312
   * is_auto_chg=0。当变更删除时更新AA312 is_auto_chg=0。）
   ****************************************************************************/
  public void updateTranInTail(String orgID, String empId, String type)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      if (type == "1") {
        list = chgPersonTailDAO.queryTranInTail(orgID, empId);
      } else {
        list = chgPersonTailDAO.queryTranInTailIsAutoChg(orgID, empId);
      }

      if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          TranInTail tranInTail = (TranInTail) list.get(i);
          tranInTail.setIsAutoChg(type);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 吴洪涛 2008-6-16
  public List findChgpersonDoListAllByCriterions_wsh(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      list = new ArrayList();
      String id = (String) pagination.getQueryCriterions().get("id");
      String headId = (String) pagination.getQueryCriterions().get("headId");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      // 单位是否存在
      Org org = null;
      org = orgDAO.queryByCriterions(id, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      pagination.getQueryCriterions().put("name", org.getOrgInfo().getName());// 存放单位名称

      String date = null;
      ChgPersonHead chgpersonHead = chgPersonHeadDAO.getChgPersonHead_WL(id);
      date = chgpersonHead.getChngMonth();
      if (date == null || date.equals("")) {
        date = monthPaymentHeadDAO.getOrgMonthPayment(id);
        if (date == null || date.equals("")) {
          Org returnOrg = orgDAO.queryById(new Integer(id));
          date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
        } else {
          date = BusiTools.addMonth(date, 1);
        }
      }
      List listAll = chgPersonTailDAO.findChgpersonDoListAllByCriterions_wsh(
          headId, orderBy, order, start, pageSize, page);
      if (listAll != null && listAll.size() > 0) {
        for (int i = 0; i < listAll.size(); i++) {
          ChgPersonTail chgPersonTail = (ChgPersonTail) listAll.get(i);
          ChgPersonCellListListExportDTO chgPersonCellListListExportDTO = new ChgPersonCellListListExportDTO();
          chgPersonCellListListExportDTO.setChgMonth(date);
          chgPersonCellListListExportDTO.setOrgId(id);
          chgPersonCellListListExportDTO.setOrgName(org.getOrgInfo().getName());
          chgPersonCellListListExportDTO.setCardNum(chgPersonTail.getCardNum());
          chgPersonCellListListExportDTO.setEmpId(chgPersonTail.getEmpId()
              .toString());
          chgPersonCellListListExportDTO.setName(chgPersonTail.getName());
          chgPersonCellListListExportDTO.setEmpPay(chgPersonTail.getEmpPay()
              .toString());
          chgPersonCellListListExportDTO.setOrgPay(chgPersonTail.getOrgPay()
              .toString());
          chgPersonCellListListExportDTO.setSalaryBase(chgPersonTail
              .getSalaryBase().toString());
          chgPersonCellListListExportDTO.setSumPay(chgPersonTail.getSumPay()
              .toString());
          // 转换
          if ((chgPersonTail.getChgType().equals("1"))
              || (chgPersonTail.getChgType().equals("2"))) {
            chgPersonCellListListExportDTO.setChgType(BusiTools.getBusiValue(1,
                BusiConst.CHGSTATUS));
          } else if (chgPersonTail.getChgType().equals("3")) {
            chgPersonCellListListExportDTO.setChgType(BusiTools.getBusiValue(2,
                BusiConst.CHGSTATUS));
          } else {
            chgPersonCellListListExportDTO.setChgType(BusiTools.getBusiValue(3,
                BusiConst.CHGSTATUS));
          }
          chgPersonCellListListExportDTO.setTemp_oldPayStatus(BusiTools
              .getBusiValue(Integer.parseInt(chgPersonTail.getNewPayStatus()
                  .toString()), BusiConst.OLDPAYMENTSTATE));// 变更后状态
          if (chgPersonTail.getChgreason() != null
              && !chgPersonTail.getChgreason().equals("")) {
            chgPersonCellListListExportDTO.setTemp_chgreason(BusiTools
                .getBusiValue(Integer.parseInt(chgPersonTail.getChgreason()),
                    BusiConst.CHGPERSONREASON));
          }
          list.add(chgPersonCellListListExportDTO);
        }
      }

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }



}
