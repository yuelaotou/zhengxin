package org.xpup.hafmis.syscollection.tranmng.tranin.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonTailDAO;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowTransInDAO;
import org.xpup.hafmis.syscollection.common.dao.PickTailDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInOrgDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInTailDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutOrgDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutTailDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.TranInTailDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowTransIn;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninImportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninImportHeadDTO;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TranTbPrintAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
import org.xpup.hafmis.syscommon.dao.EmpInfoDAO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * shiyan
 */
public class TraninBS implements ITraninBS {   
  private EmpDAO empDAO = null;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private TranInBizActivityLogDAO tranInBizActivityLogDAO = null;

  private TranOutHeadDAO tranOutHeadDAO = null;

  private TranOutTailDAO tranOutTailDAO = null; 

  private TranInHeadDAO tranInHeadDAO = null;

  private CollParaDAO collParaDAO=null;
  
  private TranOutOrgDAO tranOutOrgDAO = null;

  private TranInTailDAO tranInTailDAO = null;
  
  protected EmpInfoDAO empInfoDAO = null;

  private OrgDAO orgDAO = null;

  private TranInOrgDAO tranInOrgDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private OrgHAFAccountFlowTransInDAO orgHAFAccountFlowTransInDAO = null;

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  private BizActivityLogDAO bizActivityLogDAO = null;
  
  private PickTailDAO pickTailDAO=null;
  
  private DocNumCancelDAO docNumCancelDAO=null;
  
  private DocNumMaintainDAO docNumMaintainDAO=null;

  private ChgPersonTailDAO chgPersonTailDAO=null;
  
  private AutoInfoPickDAO autoInfoPickDAO=null;
  
  private TranInTailDAODW tranInTailDAODW=null;
  
  private EmpDAODW empDAODW=null;
  
  private String centerTranHeadId="";
  
  private OrganizationUnitDAO organizationUnitDAO = null;
  
  private CollBankDAO collBankDAO = null;
  
  private AutoInfoPickDAODW autoInfoPickDAODW=null;
  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setTranInOrgDAO(TranInOrgDAO tranInOrgDAO) {
    this.tranInOrgDAO = tranInOrgDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setTranOutHeadDAO(TranOutHeadDAO tranOutHeadDAO) {
    this.tranOutHeadDAO = tranOutHeadDAO;
  }

  public void setTranInBizActivityLogDAO(
      TranInBizActivityLogDAO tranInBizActivityLogDAO) {
    this.tranInBizActivityLogDAO = tranInBizActivityLogDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setTranInHeadDAO(TranInHeadDAO tranInHeadDAO) {
    this.tranInHeadDAO = tranInHeadDAO;
  }

  public void setTranInTailDAO(TranInTailDAO tranInTailDAO) {
    this.tranInTailDAO = tranInTailDAO;
  }

  public void setTranOutTailDAO(TranOutTailDAO tranOutTailDAO) {
    this.tranOutTailDAO = tranOutTailDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setTranOutOrgDAO(TranOutOrgDAO tranOutOrgDAO) {
    this.tranOutOrgDAO = tranOutOrgDAO;
  }

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }

  public void setOrgHAFAccountFlowTransInDAO(
      OrgHAFAccountFlowTransInDAO orgHAFAccountFlowTransInDAO) {
    this.orgHAFAccountFlowTransInDAO = orgHAFAccountFlowTransInDAO;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public void setPickTailDAO(PickTailDAO pickTailDAO) {
    this.pickTailDAO = pickTailDAO;
  }
  
  public void setDocNumMaintainDAO(DocNumMaintainDAO docNumMaintainDAO) {
    this.docNumMaintainDAO = docNumMaintainDAO;
  }
  
  public void setDocNumCancelDAO(DocNumCancelDAO docNumCancelDAO) {
    this.docNumCancelDAO = docNumCancelDAO;
  }

  public void setChgPersonTailDAO(ChgPersonTailDAO chgPersonTailDAO) {
    this.chgPersonTailDAO = chgPersonTailDAO;
  }
  public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
    this.empInfoDAO = empInfoDAO;
  }
 
  // 打印的数据
  public TraninVidAF print_sy(String tranInHeadId,SecurityInfo securityInfo) throws BusinessException {
    TraninVidAF traninVidAF = new TraninVidAF();
    TranInHead tranInHead = null;
//    List list=new ArrayList();
    List returnList = new ArrayList();
    try {
      tranInHead = tranInHeadDAO.queryById(new Integer(tranInHeadId));
      List traninList = tranInHeadDAO.queryTranIn_sy(new Integer(tranInHeadId));
      if (!traninList.equals("") && !traninList.isEmpty()) {
        Object[] tranInAmount = (Object[]) traninList.get(0);
        String traninAll = tranInAmount[1].toString();
        traninVidAF.setSumTranInAmount(new BigDecimal(traninAll));
      }
      returnList = tranInTailDAO.countTraninListByCriterions(tranInHeadId,securityInfo);
      String orgId=tranInHead.getTranInOrg().getId().toString();
      if (orgId != null && !orgId.equals("")) {
        TranInOrg tranInOrg = tranInOrgDAO.queryById(new Integer(orgId));
        traninVidAF.setInOrgId(orgId);
        traninVidAF.setInOrgName(tranInOrg.getOrgInfo().getName());
      }
      String tranOutOrgid = (String) tranInHead.getTranOutOrgId();
      if (tranOutOrgid != null && !tranOutOrgid.equals("")) {
        TranOutOrg tranOutOrg = tranOutOrgDAO.queryById(new Integer(
            tranOutOrgid));
        traninVidAF.setOutOrgId(tranOutOrgid);
        traninVidAF.setOutOrgName(tranOutOrg.getOrgInfo().getName());
      }
      traninVidAF.setList(returnList);
    } catch (Exception e) {
      throw new BusinessException("打印失败!!");
    }
    return traninVidAF;
  }

  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    // 查询办事处银行开始
    String officeName = "";
    String bankName = "";
    String str[]=new String[2];
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryByBizId_wsh(bizId, bizType);
    if (orgHAFAccountFlow != null) {
      if (orgHAFAccountFlow.getOfficeCode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(orgHAFAccountFlow
              .getOfficeCode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      if (orgHAFAccountFlow.getMoneyBank() != null) {
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(orgHAFAccountFlow.getMoneyBank());
        bankName = collBank.getCollBankName();
      }
    } else {
      Org org = null;
      String orgid = "";
      if (orgId != null) {
        orgid = orgId;
      }
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
      if (org == null && orgid != null) {
        org = new Org();
      }
      if (org.getOrgInfo().getOfficecode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(org.getOrgInfo()
              .getOfficecode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (org.getOrgInfo().getCollectionBankId() != null) {
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(org.getOrgInfo().getCollectionBankId());
        bankName = collBank.getCollBankName();
      }
    }
    //查询办事处银行结束
    str[0]=officeName;
    str[1]=bankName;
    return str;
  }
  
  // 待转入完成
  public String saveTranin_sy(String inOrgId, String tranOutHeadId,
    String tranOutOrgid,SecurityInfo securityInfo) throws BusinessException {

    String officeCode="";
    //插入记录用的信息
    String bizDate=securityInfo.getUserInfo().getBizDate();
    String operator=securityInfo.getUserInfo().getBizDate();
    //计算金额用的变量小计和总计
//    BigDecimal credit = new BigDecimal(0.00);
    BigDecimal creditAll = new BigDecimal(0.00);
    List creditList = new ArrayList();
    List empIdList = new ArrayList();
    String tranInHeadId = "";
    TranOutTail tranOutTail = null;
    Emp saveEmp = null;
    Integer empLastId = null;
    TranInHead tranInHead = new TranInHead();
//    TranInTail tranInTail = new TranInTail();
    //emp信息
    String empName="";
    String empCarNum="";
    String empBrithday="";
    String empCardKing="";
//  linan
    String empDept="";
    String temp="";
    String empTel="";
    String empMobeilTel="";
    BigDecimal empMonthIncome=new BigDecimal(0.00);
    //转入凭证号
    String docNum="";
    String temp_docNum="";
    BigDecimal empSex=new BigDecimal(0.00);
    TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
    OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn = new OrgHAFAccountFlowTransIn();
//    EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
    try {
      if(tranOutHeadId!=null&&!tranOutHeadId.equals("")){
      //查看是否有人已经做过这笔业务。
       List temp_list=tranInHeadDAO.queryTranOutHeadId_sy(tranOutHeadId);
        if(!temp_list.isEmpty()){
          throw new BusinessException("已经做过这笔业务,请核实！。");
        }
      }
      List tranOutTailList = tranOutTailDAO.queryTranOutTail(tranOutHeadId);
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(
          tranOutHeadId));
      if(tranOutHead.getTranInOrg().getOrgInfo().getCollectionBankId().equals(tranOutHead.getTranOutOrg().getOrgInfo().getCollectionBankId())){
        temp="yes";
      }
      TranInOrg tranInOrg = tranInOrgDAO.queryById(new Integer(inOrgId));
      //判断是否节息，如果有一条记录节息。就会在流水表中插入节息。
      String tempIsSettIntrerest="";
      for (int i = 0; i < tranOutTailList.size(); i++) {

        tranOutTail = (TranOutTail) tranOutTailList.get(i);
        Integer empId = tranOutTail.getEmpId();
        String inorgid= tranOutTail.getTranOutHead().getTranOutOrg().getId().toString();
        List temp_empList = empDAO.queryByEmpId_lg(new Integer(empId.toString()),new Integer(inorgid));
        Emp emp=(Emp) temp_empList.get(0);
        empName = emp.getEmpInfo().getName();
        empCarNum = emp.getEmpInfo().getCardNum();
        //判断是否经行了人员变更,如果有就不叫其修改
        ChgPersonTail chgPersonTail=null;
        chgPersonTail=chgPersonTailDAO.getChgPersonTail_WL(inOrgId, null, empName, empCarNum, "1", null);
         if(chgPersonTail!=null){
           throw new BusinessException(empName+"身份证号码"+empCarNum+"正在人员变更中,不能进行转入.");
         }
        empBrithday=emp.getEmpInfo().getBirthday();
        empSex=emp.getEmpInfo().getSex();
        empCardKing=emp.getEmpInfo().getCardKind().toString();
//      linan
        if(emp.getEmpInfo().getDepartment()==null){
          empDept = "";
          }
        else{
          empDept=emp.getEmpInfo().getDepartment().toString();
          }
        if(emp.getEmpInfo().getTel()==null){
          empTel = "";
          }
        else{
          empTel=emp.getEmpInfo().getTel().toString();
        }
        if(emp.getEmpInfo().getMobileTle()==null){
          empMobeilTel = "";
        }
        else{
          empMobeilTel=emp.getEmpInfo().getMobileTle().toString();
        }
        if(emp.getEmpInfo().getMonthIncome()==null){
         // empMonthIncome = "";
        }else{
          empMonthIncome=emp.getEmpInfo().getMonthIncome();
        }
        
        List empList = empDAO.getEmp_WL(inOrgId, empName, empCarNum);
        BigDecimal balance = new BigDecimal(0.00);
        //20071211修改的地方一共两个部分一部分是判断去那个empid一部分是插入
        String isfirstEmpid="";//判断条件用于是否往合并合并职工编号插值‘pass’插入否则不插入
        if(tranOutTail.getTranin_empid()!=null&&!tranOutTail.getTranin_empid().equals("")&&tranOutTail.getTranin_empid().intValue()!=0){
          isfirstEmpid="pass";
          empLastId=tranOutTail.getTranin_empid();
        }else{
          if (!empList.isEmpty()) {
            if (empList.size() == 1) {
              saveEmp = (Emp) empList.get(0);
              empLastId = saveEmp.getEmpId();
            } else {
              // 比较余额取最大的empid
              for (int j = 0; j < empList.size(); j++) {
                saveEmp = (Emp) empList.get(j);
                BigDecimal curBalance = saveEmp.getCurBalance();
                BigDecimal perBalance = saveEmp.getPreBalance();
                BigDecimal temp_balance = curBalance.add(perBalance);
                int s = balance.compareTo(temp_balance);
                if (s <= 0) {
                  balance=temp_balance;
                  empLastId = saveEmp.getEmpId();
                }
              }
            }
          } else {
            empLastId = empId;
          } 
        }
        //20071211修改第一部分结束
        tranInHead.setTranInOrg(tranInOrg);
        tranInHead.setTranOutHeadId(new BigDecimal(tranOutHead.getId()
            .toString()));
        tranInHead.setTranOutOrgId(tranOutOrgid);
        tranInHead.setNoteNum(tranOutHead.getNoteNum());
//        tranInHead.setDocNum(tranOutHead.getDocNum());
        // 插入的会计日期
        tranInHead.setSettDate(bizDate);
        tranInHead.setTranStatus(new BigDecimal(3.00));
        tranInHead.setTranOutHeadId(new BigDecimal(tranOutHeadId));
        if(tranInHeadId.equals("")){
          //插入凭证号
         if(inOrgId!=null&&!inOrgId.equals("")){
            //需要截取会计日期
            String  bizDate1=bizDate.substring(0, 6);
//            Org orgs=orgDAO.getOrg_WL(inOrgId);
//            officeCode= orgs.getOrgInfo().getOfficecode();
            Org orgs=orgDAO.getOrg_WL(inOrgId);
            String docNumDocument=collParaDAO.getDocNumDesignPara();
            if(docNumDocument.equals("1")){
              officeCode = orgs.getOrgInfo().getOfficecode();
            }else{
              officeCode = orgs.getOrgInfo().getCollectionBankId();
            }
            temp_docNum=this.getDocNumdocNum(officeCode, bizDate1);
            Map office = securityInfo.getOfficeInnerCodeMap();
            String officecode = office.get(officeCode).toString();
            if (officecode.length() == 1) {
              officecode = "0" + officecode;
            }
            temp_docNum=officecode+bizDate1+temp_docNum;
            tranInHead.setDocNum(temp_docNum);

          }
        tranInHeadId = (String) tranInHeadDAO.insert(tranInHead);
        docNum=temp_docNum;
        }
        TranInTail tranInTail = new TranInTail();
        if(tranOutTail.getIsSettIntrerest().toString().equals("1")){
          BigDecimal preIntegral=this.getpreInt(tranOutTail.getPreBalance(),securityInfo.getUserInfo().getBizDate());
          BigDecimal curIntegral=this.getCurInt(tranOutTail.getCurBalance(),securityInfo.getUserInfo().getBizDate());
          tranInTail.setPreIntegral(preIntegral);
          tranInTail.setCurIntegral(curIntegral);
          tranInTail.setCurRateA(tranOutTail.getCurRateA());
          tranInTail.setCurRateB(tranOutTail.getCurRateB());
          tranInTail.setCurRateC(tranOutTail.getCurRateC());
          tranInTail.setCurRateD(tranOutTail.getCurRateD());
          tranInTail.setCurRateE(tranOutTail.getCurRateE());
          tranInTail.setCurRateF(tranOutTail.getCurRateF());
          tranInTail.setCurRateG(tranOutTail.getCurRateG());
          tranInTail.setCurRateH(tranOutTail.getCurRateH());
          tranInTail.setCurRateI(tranOutTail.getCurRateI());
          tranInTail.setCurRateJ(tranOutTail.getCurRateJ());
          tranInTail.setCurRateK(tranOutTail.getCurRateK());
          tranInTail.setCurRateL(tranOutTail.getCurRateL());
          tranInTail.setCurIntegralSubA(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubB(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubC(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubD(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubE(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubF(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubG(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubH(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubI(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubJ(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubK(new BigDecimal(0.00));
          tranInTail.setCurIntegralSubL(new BigDecimal(0.00));
          tranInTail.setPreRateA(tranOutTail.getPreRateA());
          tranInTail.setPreRateB(tranOutTail.getPreRateB());
          tranInTail.setPreRateC(tranOutTail.getPreRateC());
          tranInTail.setPreRateD(tranOutTail.getPreRateD());
          tranInTail.setPreRateE(tranOutTail.getPreRateE());
          tranInTail.setPreRateF(tranOutTail.getPreRateF());
          tranInTail.setPreRateG(tranOutTail.getPreRateG());
          tranInTail.setPreRateH(tranOutTail.getPreRateH());
          tranInTail.setPreRateI(tranOutTail.getPreRateI());
          tranInTail.setPreRateJ(tranOutTail.getPreRateJ());
          tranInTail.setPreRateK(tranOutTail.getPreRateK());
          tranInTail.setPreRateL(tranOutTail.getPreRateL());
          tranInTail.setPreIntegralSubA(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubB(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubC(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubD(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubE(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubF(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubG(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubH(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubI(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubJ(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubK(new BigDecimal(0.00));
          tranInTail.setPreIntegralSubL(new BigDecimal(0.00));
          /////lwh add 
          tranInTail.setSalaryBase(emp.getSalaryBase());
          tranInTail.setEmpPay(emp.getEmpPay());
          tranInTail.setOrgPay(emp.getOrgPay());
        }else{
          tranInTail.setPreIntegral(tranOutTail.getPreIntegral());
          tranInTail.setCurIntegral(tranOutTail.getCurIntegral());
          tranInTail.setCurRateA(tranOutTail.getCurRateA());
          tranInTail.setCurRateB(tranOutTail.getCurRateB());
          tranInTail.setCurRateC(tranOutTail.getCurRateC());
          tranInTail.setCurRateD(tranOutTail.getCurRateD());
          tranInTail.setCurRateE(tranOutTail.getCurRateE());
          tranInTail.setCurRateF(tranOutTail.getCurRateF());
          tranInTail.setCurRateG(tranOutTail.getCurRateG());
          tranInTail.setCurRateH(tranOutTail.getCurRateH());
          tranInTail.setCurRateI(tranOutTail.getCurRateI());
          tranInTail.setCurRateJ(tranOutTail.getCurRateJ());
          tranInTail.setCurRateK(tranOutTail.getCurRateK());
          tranInTail.setCurRateL(tranOutTail.getCurRateL());
          tranInTail.setCurIntegralSubA(tranOutTail.getCurIntegralSubA());
          tranInTail.setCurIntegralSubB(tranOutTail.getCurIntegralSubB());
          tranInTail.setCurIntegralSubC(tranOutTail.getCurIntegralSubC());
          tranInTail.setCurIntegralSubD(tranOutTail.getCurIntegralSubD());
          tranInTail.setCurIntegralSubE(tranOutTail.getCurIntegralSubE());
          tranInTail.setCurIntegralSubF(tranOutTail.getCurIntegralSubF());
          tranInTail.setCurIntegralSubG(tranOutTail.getCurIntegralSubG());
          tranInTail.setCurIntegralSubH(tranOutTail.getCurIntegralSubH());
          tranInTail.setCurIntegralSubI(tranOutTail.getCurIntegralSubI());
          tranInTail.setCurIntegralSubJ(tranOutTail.getCurIntegralSubJ());
          tranInTail.setCurIntegralSubK(tranOutTail.getCurIntegralSubK());
          tranInTail.setCurIntegralSubL(tranOutTail.getCurIntegralSubL());
          tranInTail.setPreRateA(tranOutTail.getPreRateA());
          tranInTail.setPreRateB(tranOutTail.getPreRateB());
          tranInTail.setPreRateC(tranOutTail.getPreRateC());
          tranInTail.setPreRateD(tranOutTail.getPreRateD());
          tranInTail.setPreRateE(tranOutTail.getPreRateE());
          tranInTail.setPreRateF(tranOutTail.getPreRateF());
          tranInTail.setPreRateG(tranOutTail.getPreRateG());
          tranInTail.setPreRateH(tranOutTail.getPreRateH());
          tranInTail.setPreRateI(tranOutTail.getPreRateI());
          tranInTail.setPreRateJ(tranOutTail.getPreRateJ());
          tranInTail.setPreRateK(tranOutTail.getPreRateK());
          tranInTail.setPreRateL(tranOutTail.getPreRateL());
          tranInTail.setPreIntegralSubA(tranOutTail.getPreIntegralSubA());
          tranInTail.setPreIntegralSubB(tranOutTail.getPreIntegralSubB());
          tranInTail.setPreIntegralSubC(tranOutTail.getPreIntegralSubC());
          tranInTail.setPreIntegralSubD(tranOutTail.getPreIntegralSubD());
          tranInTail.setPreIntegralSubE(tranOutTail.getPreIntegralSubE());
          tranInTail.setPreIntegralSubF(tranOutTail.getPreIntegralSubF());
          tranInTail.setPreIntegralSubG(tranOutTail.getPreIntegralSubG());
          tranInTail.setPreIntegralSubH(tranOutTail.getPreIntegralSubH());
          tranInTail.setPreIntegralSubI(tranOutTail.getPreIntegralSubI());
          tranInTail.setPreIntegralSubJ(tranOutTail.getPreIntegralSubJ());
          tranInTail.setPreIntegralSubK(tranOutTail.getPreIntegralSubK());
          tranInTail.setPreIntegralSubL(tranOutTail.getPreIntegralSubL());
          /////lwh add 
          tranInTail.setSalaryBase(emp.getSalaryBase());
          tranInTail.setEmpPay(emp.getEmpPay());
          tranInTail.setOrgPay(emp.getOrgPay());
        }
//      为扎帐准备时间凭证
        tranInTail.setOpenDate(securityInfo.getUserInfo().getBizDate());
        tranInTail.setCurBalance(tranOutTail.getCurBalance());
        tranInTail.setPreBalance(tranOutTail.getPreBalance());
        tranInTail.setCurInterest(tranOutTail.getCurInterest());
        tranInTail.setPreInterest(tranOutTail.getPreInterest());
        //20071211第二部分修改
        if(isfirstEmpid.equals("pass")){
          tranInTail.setEmpId(empLastId);
          tranInTail.setMergerEmpid(new BigDecimal(empId.toString()));
        }else{
          if (!empList.isEmpty()) {
            tranInTail.setEmpId(empLastId);
            tranInTail.setMergerEmpid(new BigDecimal(empId.toString()));
          } else {
            tranInTail.setEmpId(empId);
          }
        }
        //20071211第二部分修改完毕
        tranInTail.setName(empName);
        tranInTail.setCardKind(new Integer(empCardKing));
        tranInTail.setBirthday(empBrithday);
        tranInTail.setSex(new Integer(empSex.toString()));
        tranInTail.setCardNum(empCarNum);
        // linan 
        tranInTail.setDept(empDept);
        tranInTail.setTel(empTel);
        tranInTail.setMobileTel(empMobeilTel);
        tranInTail.setMonthIncome(empMonthIncome);
        
        tranInTail.setTranInHead(tranInHead);
        // 判断职工的证件号码
        if (tranInTail.getCardKind().toString().equals("0")&&tranInTail.getCardNum().length()==15) {
          String num_to18 = empInfoDAO.queryCardNumTo18(tranInTail.getCardNum());
          tranInTail.setStandbyCardNum(num_to18);
        }else{
          tranInTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
        }
        tranInTailDAO.insert(tranInTail);

        // 声明312中的总额也是从310中取出的
        BigDecimal credit = new BigDecimal(0.00);
        BigDecimal temp_Balance=new BigDecimal(0.00);
        BigDecimal temp_Interest=new BigDecimal(0.00);
        temp_Balance=temp_Balance.add(tranOutTail.getCurBalance()).add(tranOutTail.getPreBalance());
        temp_Interest=temp_Interest.add(tranOutTail.getCurInterest()).add(tranOutTail.getPreInterest());
        //证明已经节息，为财务准备节息字段，向流水表中备选字段插入数据
        if(temp_Interest.doubleValue()>0){
        tempIsSettIntrerest="1";
        }
        credit=credit.add(temp_Balance).add(temp_Interest);
        creditAll = creditAll.add(credit);
        creditList.add(credit);
        empIdList.add(empLastId);
      }
//    插入到AA101
      if(tranInHeadId!=null&&!tranInHeadId.equals("")){
        TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadId,new Integer(3),"F");
        //如果同一BIZID同一业务类型已经在319中存在就不在插入了
        if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
          throw new BusinessException("此操作已经进行请核实");
        }
        }
      Org orgs = orgDAO.queryById(new Integer(inOrgId));
      // 插入到AA101
      orgHAFAccountFlowTransIn.setBizId(new BigDecimal(tranInHeadId));
      orgHAFAccountFlowTransIn.setOrg(orgs);
      //节息标示,为财务准备数据
      if(tempIsSettIntrerest.equals("1")){
      orgHAFAccountFlowTransIn.setReserveaB("1");
      }
      orgHAFAccountFlowTransIn.setSettDate(bizDate);
      orgHAFAccountFlowTransIn.setPersonTotal(new Integer(tranOutTailList.size()));
      //插入操作员姓名
      orgHAFAccountFlowTransIn.setReserveaA(securityInfo.getUserInfo().getUsername());
      orgHAFAccountFlowTransIn.setCredit(creditAll);
      orgHAFAccountFlowTransIn.setDebit(new BigDecimal(0.00));
      orgHAFAccountFlowTransIn.setInterest(new BigDecimal(0.00));
      orgHAFAccountFlowTransIn.setBizStatus(new BigDecimal(3.00));
      if(temp.equals("yes")){
        orgHAFAccountFlowTransIn.setIsFinance(new BigDecimal(2));
      }else{
        orgHAFAccountFlowTransIn.setIsFinance(new BigDecimal(1));// 财务标示
      }
      orgHAFAccountFlowTransIn.setDocNum(docNum);
      orgHAFAccountFlowTransIn.setNoteNum(tranOutHead.getNoteNum());
      // 二次升级开始
      orgHAFAccountFlowTransIn.setOfficeCode(orgs.getOrgInfo().getOfficecode());
      orgHAFAccountFlowTransIn.setMoneyBank(orgs.getOrgInfo().getCollectionBankId());
      //结束
      orgHAFAccountFlowTransInDAO.insert(orgHAFAccountFlowTransIn);
      // 插入到AA102
      for (int i = 0; i < tranOutTailList.size(); i++) {
        EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
        empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlowTransIn);
        BigDecimal temp_credit = (BigDecimal) creditList.get(i);
        empHAFAccountFlow.setCredit(temp_credit);
        empLastId = (Integer) empIdList.get(i);
        empHAFAccountFlow.setEmpId(empLastId);
        empHAFAccountFlow.setDebit(new BigDecimal(0.00));
        empHAFAccountFlow.setInterest(new BigDecimal(0.00));
        empHAFAccountFlowDAO.insert(empHAFAccountFlow);
      }
      // 插入到BA003
      Map map = new HashMap();
      String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_PREPARE).toString();
      String opbutton=new Integer(BusiLogConst.BIZLOG_ACTION_OPENING).toString();
      map.put("opModel", opMode);
      map.put("opButton", opbutton);
      map.put("opBizId", tranInHeadId);
      map.put("orgId", inOrgId);
      this.insertHafOperateLog(map,securityInfo);
      // 插入AA319
      this.insertTranInBizActivityLog(tranInHeadId, securityInfo.getUserInfo().getUsername(), "3");
    }catch(BusinessException bx){
      throw bx;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("失败!!");
    } 
    return tranInHeadId;
  }

  // 转入维护登陆查询页面
  public List queryTraninVid_sy(Pagination pagination,SecurityInfo securityInfo)
      throws BusinessException{
    List list = new ArrayList();
    List returnList = new ArrayList();
    TranInHead tranInHead = new TranInHead();
    BigDecimal sumTranInAmount = new BigDecimal(0.00);
    BigDecimal temp_TranInAmount = new BigDecimal(0.00);
    String inOrgId = (String) pagination.getQueryCriterions().get("inOrgId");
    String outOrgName = (String) pagination.getQueryCriterions().get(
        "outOrgName");
    String inOrgName = (String) pagination.getQueryCriterions()
        .get("inOrgName");
    String outOrgId = (String) pagination.getQueryCriterions().get("outOrgId");
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String settDate = (String) pagination.getQueryCriterions().get("settDate");
    String settDatea = (String) pagination.getQueryCriterions().get("settDatea");
    String tranStatus = (String) pagination.getQueryCriterions().get(
        "tranStatus");
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    TranOutOrg tranOutOrg = new TranOutOrg();
    TranInOrg tranInOrg = null;
    Integer tranOutOrgId=null;
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int count = 0;
    int sumCount = 0;
    int page = pagination.getPage();
    try{
    list = tranInHeadDAO.queryTranInListByCriterionsAll_sy(settDate,settDatea,
        tranStatus, inOrgId, outOrgId, outOrgName, inOrgName, noteNum, docNum,
        orderBy, order, start, pageSize,securityInfo,page,collBankId);
    //20071213查看状态
    String istype=securityInfo.getIsOrgEdition()+"";
    for (int i = 0; i < list.size(); i++) {
      tranInHead = (TranInHead) list.get(i);
      Integer traninHeadid = (Integer) tranInHead.getId();
      List tranInTailList = tranInTailDAO
          .countTraninListByCriterions(traninHeadid.toString(),securityInfo);
      count = tranInTailList.size();
      List traninList = tranInHeadDAO.queryTranIn_sy(traninHeadid);
      if (!traninList.equals("") && !traninList.isEmpty()) {
        Object[] tranInAmount = (Object[]) traninList.get(0);
        String traninAll = tranInAmount[1].toString();
        tranInHead.setTraninAll(traninAll);
      }
      if (!traninList.equals("") && !traninList.isEmpty()) {
        Object[] tranInAmount = (Object[]) traninList.get(0);
        temp_TranInAmount = new BigDecimal(tranInAmount[1].toString());
      }
//      sumTranInAmount = sumTranInAmount.add(temp_TranInAmount);
      // tranInHead=tranInHeadDAO.queryById(traninHeadid);
//查找转入单位转出单位
      Integer traninOrgId = (Integer) tranInHead.getTranInOrg().getId();
       if(tranInHead.getTranOutOrgId()!=null&&!tranInHead.getTranOutOrgId().equals("")){   
      tranOutOrgId =  new Integer(tranInHead.getTranOutOrgId());
       }
      tranInOrg = tranInOrgDAO.queryById(traninOrgId);
      if (tranOutOrgId != null && !tranOutOrgId.equals("")) {
        tranOutOrg = tranOutOrgDAO.queryById(tranOutOrgId);
        tranInHead.setTranOutOrg(tranOutOrg);
        tranOutOrgId=null;
      }else{
        TranOutOrg temp_tranOutOrg=new TranOutOrg();
        tranInHead.setTranOutOrg(temp_tranOutOrg);
      }
      tranInHead.setTranInOrg(tranInOrg);
      String temp_tranStatus = tranInHead.getTranStatus().toString();
      //状态转化为汉字在前台页面显示
      tranInHead.setStatus(BusiTools.getBusiValue(Integer
          .parseInt(temp_tranStatus), BusiConst.BUSINESSSTATE));
      tranInHead.setCountTranInpeople(new Integer(count).toString());
//      sumCount = sumCount + count;
      //20071213查看状态
      if(istype.equals("1")){
      String status=autoInfoPickDAODW.findStatus(traninOrgId.toString(), traninHeadid.toString(), BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_F);
        String pickUpStatus="";
       if(status.equals("0")){
        pickUpStatus=BusiTools.getBusiValue(new Integer(status).intValue(), BusiConst.OC_NOT_PICK_UP_INFO);
        }
        else if(status.equals("1")){
        pickUpStatus=BusiTools.getBusiValue(new Integer(status).intValue(), BusiConst.OC_NOT_PICK_UP_INFO);
        }else{
         pickUpStatus=BusiTools.getBusiValue(new Integer("2").intValue(), BusiConst.OC_NOT_PICK_UP_INFO);
        }
      tranInHead.setPickUpStatus(pickUpStatus);
      
      List traninListSumBalance = tranInHeadDAO.queryTranInSumBalance_sy(traninHeadid);
        if (!traninListSumBalance.equals("") && !traninListSumBalance.isEmpty()) {
          Object[] tranInSumBalance = (Object[]) traninListSumBalance.get(0);
          tranInHead.setTranInSumBalance(new BigDecimal(tranInSumBalance[1].toString()));
        }
      }
      //20071213
      returnList.add(tranInHead);
    }
    //统计个数
    List countList = tranInHeadDAO.countTranInListByCriterionsAll_sy(settDate,settDatea,
        tranStatus, inOrgId, outOrgId, outOrgName, inOrgName, noteNum, docNum,securityInfo,collBankId);
    for (int i = 0; i < countList.size(); i++) {
      tranInHead = (TranInHead) countList.get(i);
      Integer traninHeadid = (Integer) tranInHead.getId();
      List tranInTailList = tranInTailDAO
          .countTraninListByCriterions(traninHeadid.toString(),securityInfo);
      count = tranInTailList.size();
      List traninList = tranInHeadDAO.queryTranIn_sy(traninHeadid);
      if (!traninList.equals("") && !traninList.isEmpty()) {
        Object[] tranInAmount = (Object[]) traninList.get(0);
        temp_TranInAmount = new BigDecimal(tranInAmount[1].toString());
      }
      sumTranInAmount = sumTranInAmount.add(temp_TranInAmount);
      sumCount = sumCount + count;
    
    }
    int temp = 0;
    if (!countList.isEmpty()) {
      temp = countList.size();
    }
    //为前台显示灰色金额和条数
    pagination.getQueryCriterions().put("sumTranInAmount", sumTranInAmount);
    pagination.getQueryCriterions().put("sumCount",
        new Integer(sumCount).toString());
    pagination.setNrOfElements(temp);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }
  public List queryTraninVid_sy_yg(Pagination pagination,SecurityInfo securityInfo)
  throws BusinessException{
    List list = new ArrayList();
    List returnList = new ArrayList();
    TranInHead tranInHead = new TranInHead();
    BigDecimal sumTranInAmount = new BigDecimal(0.00);
    BigDecimal temp_TranInAmount = new BigDecimal(0.00);
    String inOrgId = (String) pagination.getQueryCriterions().get("inOrgId");
    String outOrgName = (String) pagination.getQueryCriterions().get(
    "outOrgName");
    String inOrgName = (String) pagination.getQueryCriterions()
    .get("inOrgName");
    String outOrgId = (String) pagination.getQueryCriterions().get("outOrgId");
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String settDate = (String) pagination.getQueryCriterions().get("settDate");
    String settDatea = (String) pagination.getQueryCriterions().get("settDatea");
    String tranStatus = (String) pagination.getQueryCriterions().get(
    "tranStatus");
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    TranOutOrg tranOutOrg = new TranOutOrg();
    TranInOrg tranInOrg = null;
    Integer tranOutOrgId=null;
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int count = 0;
    int sumCount = 0;
    int page = pagination.getPage();
    try{
      list = tranInHeadDAO.queryTranInListByCriterionsAll_sy_yg(settDate,settDatea,
          tranStatus, inOrgId, outOrgId, outOrgName, inOrgName, noteNum, docNum,
          orderBy, order, start, pageSize,securityInfo,page,collBankId);
      //20071213查看状态
      String istype=securityInfo.getIsOrgEdition()+"";
      for (int i = 0; i < list.size(); i++) {
        tranInHead = (TranInHead) list.get(i);
        Integer traninHeadid = (Integer) tranInHead.getId();
        List tranInTailList = tranInTailDAO
        .countTraninListByCriterions(traninHeadid.toString(),securityInfo);
        count = tranInTailList.size();
        List traninList = tranInHeadDAO.queryTranIn_sy(traninHeadid);
        if (!traninList.equals("") && !traninList.isEmpty()) {
          Object[] tranInAmount = (Object[]) traninList.get(0);
          String traninAll = tranInAmount[1].toString();
          tranInHead.setTraninAll(traninAll);
        }
        if (!traninList.equals("") && !traninList.isEmpty()) {
          Object[] tranInAmount = (Object[]) traninList.get(0);
          temp_TranInAmount = new BigDecimal(tranInAmount[1].toString());
        }
//      sumTranInAmount = sumTranInAmount.add(temp_TranInAmount);
        // tranInHead=tranInHeadDAO.queryById(traninHeadid);
//查找转入单位转出单位
        Integer traninOrgId = (Integer) tranInHead.getTranInOrg().getId();
        if(tranInHead.getTranOutOrgId()!=null&&!tranInHead.getTranOutOrgId().equals("")){   
          tranOutOrgId =  new Integer(tranInHead.getTranOutOrgId());
        }
        tranInOrg = tranInOrgDAO.queryById(traninOrgId);
        if (tranOutOrgId != null && !tranOutOrgId.equals("")) {
          tranOutOrg = tranOutOrgDAO.queryById(tranOutOrgId);
          tranInHead.setTranOutOrg(tranOutOrg);
          tranOutOrgId=null;
        }else{
          TranOutOrg temp_tranOutOrg=new TranOutOrg();
          tranInHead.setTranOutOrg(temp_tranOutOrg);
        }
        tranInHead.setTranInOrg(tranInOrg);
        String temp_tranStatus = tranInHead.getTranStatus().toString();
        //状态转化为汉字在前台页面显示
        tranInHead.setStatus(BusiTools.getBusiValue(Integer
            .parseInt(temp_tranStatus), BusiConst.BUSINESSSTATE));
        tranInHead.setCountTranInpeople(new Integer(count).toString());

        List traninListSumBalance = tranInHeadDAO.queryTranInSumBalance_sy(traninHeadid);
        if (!traninListSumBalance.equals("") && !traninListSumBalance.isEmpty()) {
          Object[] tranInSumBalance = (Object[]) traninListSumBalance.get(0);
          tranInHead.setTranInSumBalance(new BigDecimal(tranInSumBalance[1].toString()));
        }
//      sumCount = sumCount + count;
        //20071213查看状态
        if(istype.equals("1")){
          String status=autoInfoPickDAODW.findStatus(traninOrgId.toString(), traninHeadid.toString(), BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_F);
          String pickUpStatus="";
          if(status.equals("0")){
            pickUpStatus=BusiTools.getBusiValue(new Integer(status).intValue(), BusiConst.OC_NOT_PICK_UP_INFO);
          }
          else if(status.equals("1")){
            pickUpStatus=BusiTools.getBusiValue(new Integer(status).intValue(), BusiConst.OC_NOT_PICK_UP_INFO);
          }else{
            pickUpStatus=BusiTools.getBusiValue(new Integer("2").intValue(), BusiConst.OC_NOT_PICK_UP_INFO);
          }
          tranInHead.setPickUpStatus(pickUpStatus);
          
        }
        //20071213
        returnList.add(tranInHead);
      }
      //统计个数
      List countList = tranInHeadDAO.countTranInListByCriterionsAll_sy(settDate,settDatea,
          tranStatus, inOrgId, outOrgId, outOrgName, inOrgName, noteNum, docNum,securityInfo,collBankId);
      for (int i = 0; i < countList.size(); i++) {
        tranInHead = (TranInHead) countList.get(i);
        Integer traninHeadid = (Integer) tranInHead.getId();
        List tranInTailList = tranInTailDAO
        .countTraninListByCriterions(traninHeadid.toString(),securityInfo);
        count = tranInTailList.size();
        List traninList = tranInHeadDAO.queryTranIn_sy(traninHeadid);
        if (!traninList.equals("") && !traninList.isEmpty()) {
          Object[] tranInAmount = (Object[]) traninList.get(0);
          temp_TranInAmount = new BigDecimal(tranInAmount[1].toString());
        }
        sumTranInAmount = sumTranInAmount.add(temp_TranInAmount);
        sumCount = sumCount + count;
        
      }
      int temp = 0;
      if (!countList.isEmpty()) {
        temp = countList.size();
      }
      //为前台显示灰色金额和条数
      pagination.getQueryCriterions().put("sumTranInAmount", sumTranInAmount);
      pagination.getQueryCriterions().put("sumCount",
          new Integer(sumCount).toString());
      pagination.setNrOfElements(temp);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }


  // 为待转入页面准备数据
  public List queryTranOutListByCriterionsAll_sy(Pagination pagination,SecurityInfo securityInfo) {

    String inOrgId = (String) pagination.getQueryCriterions().get("inOrgId");
    String outOrgName = (String) pagination.getQueryCriterions().get(
        "outOrgName");
    String inOrgName = (String) pagination.getQueryCriterions()
        .get("inOrgName");
    String outOrgId = (String) pagination.getQueryCriterions().get("outOrgId");
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    TranOutHead tranOutHead = new TranOutHead();
    TranOutTail tranOutTail = new TranOutTail();
    TranOutOrg tranOutOrg = new TranOutOrg();
    TranInOrg tranInOrg = new TranInOrg();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List returnTranOutTaiList = new ArrayList();
    List is_tranOutHeadList = new ArrayList();
    List countTraninOut = new ArrayList();
    List is_returnTranOutTaiList = new ArrayList();
    List is_tranInHeadList = new ArrayList();
    // 查询in_org_id不为空tranStatus>=3的所有记录
  
    int count = 0;
      List list = tranOutHeadDAO.queryTranOutListByCriterions_sy(inOrgId, outOrgId,
          outOrgName, inOrgName, noteNum, docNum, orderBy, order,
          start, pageSize,securityInfo);
      try {
        if (!list.isEmpty()) {
          for (int i = 0; i < list.size(); i++) {
            tranOutHead = (TranOutHead) list.get(i);
            Integer traninOrgId = (Integer) tranOutHead.getTranInOrg().getId();
            Integer tranOutOrgId = (Integer) tranOutHead.getTranOutOrg().getId();
            tranInOrg = tranInOrgDAO.queryById(traninOrgId);
            tranOutOrg = tranOutOrgDAO.queryById(tranOutOrgId);
            String id = tranOutHead.getId().toString();
            
              countTraninOut = tranOutTailDAO.countTranOutListByCriterionsAll_sy(
                  id, inOrgId, outOrgId, outOrgName, inOrgName, noteNum, docNum);
              tranOutHead
                  .setCountTranOutPeople(new Integer(countTraninOut.size())
                      .toString());
              is_returnTranOutTaiList = tranOutTailDAO
                  .queryTranOutListByCriterionsAll_sy(id, inOrgId, outOrgId,
                      outOrgName, inOrgName, noteNum, docNum, orderBy, order,
                      start, pageSize);
              if (is_returnTranOutTaiList != null
                  && !is_returnTranOutTaiList.isEmpty()) {
                tranOutTail = (TranOutTail) is_returnTranOutTaiList.get(0);
                is_tranOutHeadList = tranOutTailDAO.queryTranOut_sy(new Integer(
                    id));
                Object[] tranOutAmount = (Object[]) is_tranOutHeadList.get(0);
                tranOutTail.setTranOutAmount(new BigDecimal(tranOutAmount[1]
                    .toString()));
                tranOutHead.setTranInOrg(tranInOrg);
                tranOutHead.setTranOutOrg(tranOutOrg);
                tranOutTail.setTranOutHead(tranOutHead);
                returnTranOutTaiList.add(tranOutTail);
              }
            }
          }
      } catch (Exception e) {
        e.printStackTrace();
      }
    count = tranOutHeadDAO.countTranOutListByCriterions_sy(inOrgId, outOrgId,
        outOrgName, inOrgName, noteNum, docNum,securityInfo).size();
    pagination.setNrOfElements(count);
    return returnTranOutTaiList;
  }

  // 判断同一职工姓名和证件号码的人是否在该单位存在
  public List getEmp_sy(String orgID, String empName, String cardNum) {

    List list = empDAO.getEmp_WL(orgID, empName, cardNum);
    return list;
  }

  // 自动生成empid
  public Integer makeEmpIdSL_sy() throws Exception, BusinessException {
    Integer empId = empDAO.makeEmpIdSL();
    return empId;
  }

  // 转入登记完成,改变头表状态
  public void updataTranInHead(String tranInHeadId,SecurityInfo securityInfo,String noteNum) throws BusinessException {
    try {
      OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn = new OrgHAFAccountFlowTransIn();
     
//      TranInTail tranInTail = new TranInTail();
//      BigDecimal credit = new BigDecimal(0.00);
      BigDecimal creditAll = new BigDecimal(0.00);
      List creditList = new ArrayList();
      List empIdList=new ArrayList();
      TranInHead tranInHead = tranInHeadDAO.queryById(tranInHeadId);
      //根据头表ID先判断一下改业务是否已经是完成状态
      if(tranInHead.getTranStatus().toString().equals("3")){
        throw new BusinessException("业务状态已经为确认状态!");
      }
      String inOrgId=tranInHead.getTranInOrg().getId().toString();
      //插入凭证号
      String officeCode="";
      String temp_docNum="";
      if(inOrgId!=null&&!inOrgId.equals("")){
        String  bizDate1=securityInfo.getUserInfo().getBizDate().substring(0,6);
        Org orgs=orgDAO.getOrg_WL(inOrgId);
        officeCode= orgs.getOrgInfo().getOfficecode();
        String docNum=this.getDocNumdocNum(officeCode,bizDate1);
        temp_docNum=docNum;
        
      }
      //tranInHeadDAO.insert(tranInHead);
      List list = tranInTailDAO.countTraninListByCriterions(tranInHeadId,securityInfo);
      //判断是否节息，如果有一条记录节息。就会在流水表中插入节息。
      String tempIsSettIntrerest="";
      for (int i = 0; i < list.size(); i++) {
        TranInTail tranInTail = new TranInTail();
        tranInTail = (TranInTail) list.get(i);
        BigDecimal credit = new BigDecimal(0.00);
        BigDecimal temp_Balance=new BigDecimal(0.00);
        BigDecimal temp_Interest=new BigDecimal(0.00);
        temp_Balance=temp_Balance.add(tranInTail.getCurBalance()).add(tranInTail.getPreBalance());
        temp_Interest=temp_Interest.add(tranInTail.getCurInterest()).add(tranInTail.getPreInterest());
        if(temp_Interest.doubleValue()>0){
          //证明已经节息，为财务准备节息字段，向流水表中备选字段插入数据
          tempIsSettIntrerest="1";
        }
        credit=credit.add(temp_Balance).add(temp_Interest);
        creditList.add(credit);
        empIdList.add(tranInTail.getEmpId());
        creditAll = creditAll.add(credit);
      }
    
      // 插入到表AA101
      if(tranInHeadId!=null&&!tranInHeadId.equals("")){
        TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadId,new Integer(3),"F");
        //如果同一BIZID同一业务类型已经在319中存在就不在插入了
        if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
          throw new BusinessException("此操作已经进行请核实");
        }
        }
      // 插入到AA101
      Org orgs = orgDAO.queryById(new Integer(inOrgId));
      orgHAFAccountFlowTransIn.setBizId(new BigDecimal(tranInHeadId));
      orgHAFAccountFlowTransIn.setOrg(orgs);
      orgHAFAccountFlowTransIn.setCredit(creditAll);
      orgHAFAccountFlowTransIn.setPersonTotal(new Integer(list.size()));
      orgHAFAccountFlowTransIn.setReserveaA(securityInfo.getUserInfo().getUsername());
      orgHAFAccountFlowTransIn.setSettDate(securityInfo.getUserInfo().getBizDate());
      orgHAFAccountFlowTransIn.setDebit(new BigDecimal(0.00));
      orgHAFAccountFlowTransIn.setInterest(new BigDecimal(0.00));
      orgHAFAccountFlowTransIn.setBizStatus(new BigDecimal(3.00));
      orgHAFAccountFlowTransIn.setIsFinance(new BigDecimal(1.00));
      orgHAFAccountFlowTransIn.setDocNum(temp_docNum);
      orgHAFAccountFlowTransIn.setNoteNum(noteNum);
      // 二次升级开始
      orgHAFAccountFlowTransIn.setOfficeCode(orgs.getOrgInfo().getOfficecode());
      orgHAFAccountFlowTransIn.setMoneyBank(orgs.getOrgInfo().getCollectionBankId());
      //在aa101中添加登记人
      TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadId, new Integer(1), "F");//在aa319中查找登记人
      String registrations = tranInBizActivityLog.getOperator();
      orgHAFAccountFlowTransIn.setRegistrations(registrations);
      //结束
      //节息标示,为财务准备数据
      if(tempIsSettIntrerest.equals("1")){
      orgHAFAccountFlowTransIn.setReserveaB("1");
      }
      orgHAFAccountFlowTransInDAO.insert(orgHAFAccountFlowTransIn);
      // 插入到AA102
      for (int j = 0; j < list.size(); j++) {
        EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
        Integer empLastId=null;
        empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlowTransIn);
        BigDecimal temp_credit = (BigDecimal) creditList.get(j);
        empHAFAccountFlow.setCredit(temp_credit);
        empLastId = (Integer) empIdList.get(j);
        empHAFAccountFlow.setEmpId(empLastId);
        empHAFAccountFlow.setDebit(new BigDecimal(0.00));
        empHAFAccountFlow.setInterest(new BigDecimal(0.00));
        empHAFAccountFlowDAO.insert(empHAFAccountFlow);
      }
      // 插入到BA003
      Map map = new HashMap();
      String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN).toString();
      String opbutton=new Integer(BusiLogConst.BIZLOG_ACTION_OPENING).toString();
      map.put("opModel", opMode);
      map.put("opButton", opbutton);
      map.put("opBizId", tranInHeadId);
      map.put("orgId", inOrgId);
      this.insertHafOperateLog(map,securityInfo);
      // 插入AA319
      this.insertTranInBizActivityLog(tranInHeadId, securityInfo.getUserInfo().getUsername(), "3");
      tranInHead.setDocNum(temp_docNum);
      tranInHead.setTranStatus(new BigDecimal(3.00));
      tranInHead.setSettDate(securityInfo.getUserInfo().getBizDate());
      tranInHead.setNoteNum(noteNum);
      tranInHeadDAO.updataTranInHead_sy(tranInHead);
//    20071213,中心版的时候用到
      String istype=securityInfo.getIsOrgEdition()+"";
      if(istype.equals("2")){
       List autoInfoPickList=autoInfoPickDAO.queryPickUpCountbyOrgidAndStatusAndType(new Integer(tranInHeadId), BusiConst.ORGBUSINESSTYPE_F);
       if(!autoInfoPickList.isEmpty()){
         AutoInfoPick autoInfoPick=(AutoInfoPick)autoInfoPickList.get(0);
         String orgHeadId=autoInfoPick.getOrgHeadId().toString();
         for (int i = 0; i < list.size(); i++) {
         TranInTail tranInTailtemp = (TranInTail) list.get(i);
         List orgTranTaillist=tranInTailDAODW.queryTraninListByCriterions(orgHeadId, tranInTailtemp.getName(), tranInTailtemp.getCardNum());
         if(!orgTranTaillist.isEmpty()){
           TranInTail  orgTranInTail=(TranInTail)orgTranTaillist.get(0);
           //职工编号不同更新职工编号
          if(!orgTranInTail.getEmpId().toString().equals(tranInTailtemp.getEmpId().toString())){
            empDAODW.changeEmpId_sy(tranInTailtemp.getEmpId().toString(), inOrgId, orgTranInTail.getEmpId().toString());
          }
         }
       }
       }
      }
       //20071213
    }catch (BusinessException be) {
      throw be;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
//维护完成
  public void updataTranInVidHead(String tranInHeadId,SecurityInfo securityInfo,String noteNum) throws BusinessException {
    try {
      
      OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn = new OrgHAFAccountFlowTransIn();
      TranInTail tranInTail = new TranInTail();
     String temp="";
      BigDecimal creditAll = new BigDecimal(0.00);
      List creditList = new ArrayList();
      List empIdList=new ArrayList();
      TranInHead tranInHead = tranInHeadDAO.queryById(tranInHeadId);
      if(tranInHead.getTranInOrg().getOrgInfo().getCollectionBankId().equals(tranInHead.getTranOutOrg().getOrgInfo().getCollectionBankId())){
        temp="yes";
      }
      String inOrgId=tranInHead.getTranInOrg().getId().toString();
      
      //根据头表ID先判断一下改业务是否已经是完成状态
      if(tranInHead.getTranStatus().toString().equals("3")){
        throw new BusinessException("业务状态已经为确认状态!");
      }else{
        
        //插入凭证号
        String officeCode="";
        if(inOrgId!=null&&!inOrgId.equals("")){
          String  bizDate1=securityInfo.getUserInfo().getBizDate().substring(0,6);
          Org orgs=orgDAO.getOrg_WL(inOrgId);
          String docNumDocument=collParaDAO.getDocNumDesignPara();
          if(docNumDocument.equals("1")){
            officeCode = orgs.getOrgInfo().getOfficecode();
          }else{
            officeCode = orgs.getOrgInfo().getCollectionBankId();
          }
          String docNum=this.getDocNumdocNum(officeCode,bizDate1);
          tranInHead.setDocNum(docNum);
        }
        tranInHead.setTranStatus(new BigDecimal(3.00));
        tranInHead.setSettDate(securityInfo.getUserInfo().getBizDate());
        if(noteNum!=null&&!noteNum.equals("")){
        tranInHead.setNoteNum(noteNum);
        }
        tranInHeadDAO.updataTranInHead_sy(tranInHead);
        List list = tranInTailDAO.countTraninListByCriterions(tranInHeadId,securityInfo);
        //判断是否节息，如果有一条记录节息。就会在流水表中插入节息。
        String tempIsSettIntrerest="";
        for (int i = 0; i < list.size(); i++) {
          tranInTail = (TranInTail) list.get(i);
          BigDecimal credit = new BigDecimal(0.00);
          BigDecimal temp_Balance=new BigDecimal(0.00);
          BigDecimal temp_Interest=new BigDecimal(0.00);
          temp_Balance=temp_Balance.add(tranInTail.getCurBalance()).add(tranInTail.getPreBalance());
          temp_Interest=temp_Interest.add(tranInTail.getCurInterest()).add(tranInTail.getPreInterest());
          if(temp_Interest.doubleValue()>0){
            //证明已经节息，为财务准备节息字段，向流水表中备选字段插入数据
            tempIsSettIntrerest="1";
          }
          credit=credit.add(temp_Balance).add(temp_Interest);
          creditList.add(credit);
          empIdList.add(tranInTail.getEmpId());
          creditAll = creditAll.add(credit);
        }
        // 插入到表AA101
        if(tranInHeadId!=null&&!tranInHeadId.equals("")){
          TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadId,new Integer(3),"F");
          //如果同一BIZID同一业务类型已经在319中存在就不在插入了
          if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
            throw new BusinessException("此操作已经进行请核实");
          }
          }
        // 插入到AA101
        Org orgs = orgDAO.queryById(new Integer(inOrgId));
        orgHAFAccountFlowTransIn.setBizId(new BigDecimal(tranInHeadId));
        orgHAFAccountFlowTransIn.setOrg(orgs);
        orgHAFAccountFlowTransIn.setPersonTotal(new Integer(list.size()));
        orgHAFAccountFlowTransIn.setCredit(creditAll);
        orgHAFAccountFlowTransIn.setReserveaA(securityInfo.getUserInfo().getUsername());
        orgHAFAccountFlowTransIn.setSettDate(securityInfo.getUserInfo().getBizDate());
        orgHAFAccountFlowTransIn.setDebit(new BigDecimal(0.00));
        orgHAFAccountFlowTransIn.setInterest(new BigDecimal(0.00));
        orgHAFAccountFlowTransIn.setBizStatus(new BigDecimal(3.00));
        if(temp.equals("yes")){
          orgHAFAccountFlowTransIn.setIsFinance(new BigDecimal(2));
        }else{
          orgHAFAccountFlowTransIn.setIsFinance(new BigDecimal(1));// 财务标示
        }
        orgHAFAccountFlowTransIn.setDocNum(tranInHead.getDocNum());
        orgHAFAccountFlowTransIn.setNoteNum(tranInHead.getNoteNum());
        // 二次升级开始
        orgHAFAccountFlowTransIn.setOfficeCode(orgs.getOrgInfo().getOfficecode());
        orgHAFAccountFlowTransIn.setMoneyBank(orgs.getOrgInfo().getCollectionBankId());
        TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadId, new Integer(1), "F");//在aa319中查找登记人
        String registrations = tranInBizActivityLog.getOperator();
        orgHAFAccountFlowTransIn.setRegistrations(registrations);
        //结束
//      节息标示,为财务准备数据
        if(tempIsSettIntrerest.equals("1")){
        orgHAFAccountFlowTransIn.setReserveaB("1");
        }
        orgHAFAccountFlowTransInDAO.insert(orgHAFAccountFlowTransIn);
        // 插入到AA102
        for (int j = 0; j < list.size(); j++) {
          Integer empLastId=null;
          EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
          empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlowTransIn);
          BigDecimal temp_credit = (BigDecimal) creditList.get(j);
          empHAFAccountFlow.setCredit(temp_credit);
          empLastId = (Integer) empIdList.get(j);
          empHAFAccountFlow.setEmpId(empLastId);
          empHAFAccountFlow.setDebit(new BigDecimal(0.00));
          empHAFAccountFlow.setInterest(new BigDecimal(0.00));
          empHAFAccountFlowDAO.insert(empHAFAccountFlow);
        }
        // 插入到BA003
        Map map = new HashMap();
        String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN).toString();
        String opbutton=new Integer(BusiLogConst.BIZLOG_ACTION_OPENING).toString();
        map.put("opModel", opMode);
        map.put("opButton", opbutton);
        map.put("opBizId", tranInHead.getId().toString());
        map.put("orgId", inOrgId);
        this.insertHafOperateLog(map,securityInfo);
        // 插入AA319
        this.insertTranInBizActivityLog(tranInHeadId, securityInfo.getUserInfo().getUsername(), "3");
//      20071213,中心版的时候用到
        String istype=securityInfo.getIsOrgEdition()+"";
        if(istype.equals("2")){
         List autoInfoPickList=autoInfoPickDAO.queryPickUpCountbyOrgidAndStatusAndType(new Integer(tranInHeadId), BusiConst.ORGBUSINESSTYPE_F);
         if(!autoInfoPickList.isEmpty()){
           AutoInfoPick autoInfoPick=(AutoInfoPick)autoInfoPickList.get(0);
           String orgHeadId=autoInfoPick.getOrgHeadId().toString();
           for (int i = 0; i < list.size(); i++) {
           TranInTail tranInTailtemp = (TranInTail) list.get(i);
           List orgTranTaillist=tranInTailDAODW.queryTraninListByCriterions(orgHeadId, tranInTailtemp.getName(), tranInTailtemp.getCardNum());
           if(!orgTranTaillist.isEmpty()){
             TranInTail  orgTranInTail=(TranInTail)orgTranTaillist.get(0);
             //职工编号不同更新职工编号
            if(!orgTranInTail.getEmpId().toString().equals(tranInTailtemp.getEmpId().toString())){
              empDAODW.changeEmpId_sy(tranInTailtemp.getEmpId().toString(), inOrgId, orgTranInTail.getEmpId().toString());
            }
           }
         }
         }
        }
      }
       //20071213
    }catch (BusinessException be) {
      throw be;
    }
    catch (Exception e) {
      throw new BusinessException("操作失败!!");
    }
  }

  /**
   * 不能为空字段 Birthday(); CardKind(); CardNum(); CurBalance(); CurInterest();
   * preBalance(); Name(); PreInterest(); SalaryBase(); Sex();
   */
  public void modifyTraninBatch(List traninImportList1, List traninImportList2,
      String inOrgId,SecurityInfo securityInfo) throws BusinessException {
    List traninList = new ArrayList();
    try {
      TraninImportHeadDTO traninImportHeadDTO = (TraninImportHeadDTO) traninImportList1
          .get(1);
      String inOrgIdImp = traninImportHeadDTO.getInOrgId();
      //保证操作编号是6位
      inOrgIdImp=BusiTools.convertSixNumber(inOrgIdImp);
      Org orgs = orgDAO.queryById(new Integer(inOrgId));
      String is_payMode=orgs.getPayMode().toString();
      String inOrgName = orgs.getOrgInfo().getName();
      BigDecimal orgPayRate = orgs.getOrgRate();//单位缴率
      BigDecimal empPayRate = orgs.getEmpRate();//职工缴率
      //缴存精度ID:payPrecision
      int payPrecision=Integer.parseInt(orgs.getPayPrecision().toString());
      //利用缴存精度
      ArithmeticInterface arithmeticInterface=ArithmeticFactory.getArithmetic().getArithmeticDAO(payPrecision); 
      String inOrgNameImp = traninImportHeadDTO.getInOrgName();
      String noteNumImp = traninImportHeadDTO.getNoteNum();
      TraninImportDTO traninImportDTO = new TraninImportDTO();
      TranInHead tranInHead = new TranInHead();
      TranInHead tranInHeadLast = new TranInHead();
      String temp_orgId=BusiTools.convertSixNumber(inOrgId);
      String id = "";
      if (inOrgNameImp.equals(inOrgName) && inOrgIdImp.equals(temp_orgId)) {
        for (int i = 1; i < traninImportList2.size(); i++) {
          UtilRule utilRule=new UtilRule();
          traninImportDTO = (TraninImportDTO) traninImportList2.get(i);
          if(is_payMode.equals("2")){
            if(traninImportDTO.getOrgPay()!=null&&!traninImportDTO.getOrgPay().equals("")){
              if(utilRule.moneyRule(traninImportDTO.getOrgPay(), 15, 2)==false){
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"单位缴额格式不正确");
              }
            }else{
              throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"单位缴额不能为空.");
            }
            if(traninImportDTO.getEmpPay()!=null&&!traninImportDTO.equals("")){
              if(utilRule.moneyRule(traninImportDTO.getOrgPay(), 15, 2)==false){
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"职工缴额不正确");
              }
            }else{
              throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"职工缴额不能为空.");
            }
          }
          if (traninImportDTO.getName() != null
              && !traninImportDTO.getName().equals("")) {
            if (traninImportDTO.getCardKind() != null
                && !traninImportDTO.getCardKind().equals("")) {
             
                if(traninImportDTO.getCardKind().trim().equals("0")){
                  if(!((traninImportDTO.getCardNum().length()==18)||(traninImportDTO.getCardNum().length()==15))){
                    throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"身份证位数不正确");
                  }else{
//                  判断生日不能为空
                    if(traninImportDTO.getBirthday() == null ||traninImportDTO.getBirthday().equals("")){
                      throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"出生日期不能为空");
                    }
                    String temp_cardNum = "";
                    if(traninImportDTO.getCardNum().length()==15){
                      temp_cardNum = traninImportDTO.getCardNum().subSequence(6, 12).toString();
                      temp_cardNum = "19"+temp_cardNum;
                    }else{
                          temp_cardNum = traninImportDTO.getCardNum().subSequence(6, 14).toString();
                         }
                    if(!traninImportDTO.getBirthday().equals(temp_cardNum)){
                       throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"出生日期与身份证不符合");
                    }
                  }
                }
              try {
                Integer.parseInt(traninImportDTO.getCardKind());
              } catch (Exception s) {
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"证件类型格式不正确");
              }
              if (traninImportDTO.getCardNum() != null
                  && !traninImportDTO.getCardNum().equals("")) {
//                System.out.println(traninImportDTO.getCardNum());
//                try {
//                  Integer.parseInt(traninImportDTO.getCardNum());
//                } catch (Exception s) {
//                  throw new BusinessException("类型格式不正确");
//                }
                String temp_inOrgIdImp=null;
                //模糊查询只要姓名和身份证号相同的就不叫他插入
                List empList = empDAO.getEmp_WL(temp_inOrgIdImp, traninImportDTO
                    .getName(), traninImportDTO.getCardNum());
                if (empList.isEmpty()) {
                  if (traninImportDTO.getCurBalance() != null
                      && !traninImportDTO.getCurBalance().equals("")) {
                    try {
                      Integer.parseInt(traninImportDTO.getCurBalance());
                    } catch (Exception s) {
                      throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"本年余额格式不正确");
                    }
                    if (traninImportDTO.getCurInterest() != null
                        && !traninImportDTO.getCurInterest().equals("")) {
                      try {
                        Integer.parseInt(traninImportDTO.getCurInterest());
                      } catch (Exception s) {
                        throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"利息格式不正确");
                      }
                      if (traninImportDTO.getPreBalance() != null
                          && !traninImportDTO.getPreBalance().equals("")) {
                        try {
                          Integer.parseInt(traninImportDTO.getPreBalance());
                        } catch (Exception s) {
                          throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"往年余额格式不正确");
                        }
                        if (traninImportDTO.getBirthday() != null
                            && !traninImportDTO.getBirthday().equals("")) {
                          try {
                            Integer.parseInt(traninImportDTO.getBirthday());
                          } catch (Exception s) {
                            throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"出生日期格式不正确");
                          }
                          if (traninImportDTO.getSalaryBase() != null
                              && !traninImportDTO.getSalaryBase().equals("")) {
                          
                              if(utilRule.moneyRule(traninImportDTO.getSalaryBase(), 15, 2)==false){
                                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"工资格式不正确");
                              }
                            if (traninImportDTO.getSex()!= null
                                && !traninImportDTO.getSex().equals("")) {
                              try {
                                Integer.parseInt(traninImportDTO.getSex());
                              } catch (Exception s) {
                                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"性别输入不正确");
                              }
                              // 如果证件类型是身份证，判断导入文件以中是否存在相同的职工
                              if (traninImportDTO.getCardKind().equals("0")) {
                                for (int j = 1; j < traninImportList2.size(); j++) {
                                  TraninImportDTO traninImportDTO1 = (TraninImportDTO)traninImportList2.get(j);
                                  if (i!=j) {
                                    String temp_cardNum = traninImportDTO1.getCardNum();
                                    String temp_cardNum15 = "";
                                    if (temp_cardNum.length()==18) {
                                      temp_cardNum15 = temp_cardNum.substring(0, 6)+temp_cardNum.substring(8, 17);
                                    }else{
                                      temp_cardNum15 = temp_cardNum;
                                    }
                                    if (traninImportDTO.getCardNum().equals(temp_cardNum)||traninImportDTO.getCardNum().equals(temp_cardNum15)) {
                                      throw new BusinessException("第" + (i+5) + "行记录中的身份证号与第"+(j+5)+"行记录的身份证号相同！！");
                                    }
                                  }
                                }
                              }
                              Integer empId = empDAO.makeEmpIdSL();
                              TranInTail tranInTail=new TranInTail();
                               tranInTail.setEmpId(empId);
                              if (id.equals("")) {
                                tranInHead.setTranStatus(new BigDecimal(1.00));
                                tranInHead.setNoteNum(noteNumImp);
                                TranInOrg tranInOrg = tranInOrgDAO
                                    .queryById(new Integer(inOrgIdImp));
                                tranInHead.setTranInOrg(tranInOrg);
                                id = (String) tranInHeadDAO.insert(tranInHead);
                              centerTranHeadId=id;
                              }
                              tranInHeadLast = tranInHeadDAO.queryById(id);
                              tranInTail.setTranInHead(tranInHeadLast);
                              tranInTail.setBirthday(traninImportDTO
                                  .getBirthday());
                              tranInTail.setCardKind(new Integer(
                                  traninImportDTO.getCardKind()));
                              tranInTail.setCardNum(traninImportDTO
                                  .getCardNum());
                              if(traninImportDTO.getPreBalance()!=null&&!traninImportDTO.getPreBalance().equals("")){
                              BigDecimal preIntegral=this.getpreInt(new BigDecimal(traninImportDTO.getPreBalance()),securityInfo.getUserInfo().getBizDate());
                              tranInTail.setPreIntegral(preIntegral);
                             
                              }else{
                                tranInTail.setPreIntegral(new BigDecimal(0.00));
                              }
                              if(traninImportDTO.getCurBalance()!=null&&!traninImportDTO.getCurBalance().equals("")){
                                BigDecimal curIntegral=this.getCurInt(new BigDecimal(traninImportDTO.getCurBalance()),securityInfo.getUserInfo().getBizDate());
                                tranInTail.setCurIntegral(curIntegral);
                              }else{
                                tranInTail.setCurIntegral(new BigDecimal(0.00));
                              }
                              if(traninImportDTO.getCurBalance()!=null&&!traninImportDTO.getCurBalance().equals("")){
                              tranInTail.setCurBalance(new BigDecimal(
                                  traninImportDTO.getCurBalance()));
                              }
                              if(traninImportDTO.getPreBalance()!=null&&!traninImportDTO.getPreBalance().equals("")){
                              tranInTail.setPreBalance(new BigDecimal(
                                  traninImportDTO.getPreBalance()));
                              }
                              if(traninImportDTO.getCurInterest()!=null&&!traninImportDTO.getCurInterest().equals("")){
                              tranInTail.setCurInterest(new BigDecimal(
                                  traninImportDTO.getCurInterest()));
                              }
                              if(traninImportDTO.getMonthIncome()!=null&&!traninImportDTO.getMonthIncome().equals("")){
                              tranInTail.setMonthIncome(new BigDecimal(
                                  traninImportDTO.getMonthIncome()));
                              }
                              if(traninImportDTO.getTel()!=null&&!traninImportDTO.getTel().equals("")){
                              tranInTail.setTel(traninImportDTO.getTel());
                              }
                              if(traninImportDTO.getSalaryBase()!=null&&!traninImportDTO.getSalaryBase().equals("")){
                              tranInTail.setSalaryBase(new BigDecimal(
                                  traninImportDTO.getSalaryBase()));
                              }
                              tranInTail.setSex(new Integer(traninImportDTO
                                  .getSex()));
                              if (traninImportDTO.getEmpPay() != null
                                  && !traninImportDTO.getEmpPay().equals("")) {
                                tranInTail.setEmpPay(new BigDecimal(
                                    traninImportDTO.getEmpPay()));
                              }else{
                                tranInTail.setEmpPay(arithmeticInterface.getPay(new BigDecimal(
                                    traninImportDTO.getSalaryBase()), empPayRate));
                              }
                              if (traninImportDTO.getOrgPay() != null
                                  && !traninImportDTO.getOrgPay().equals("")) {
                                tranInTail.setOrgPay(new BigDecimal(
                                    traninImportDTO.getOrgPay()));
                              }else{
                                tranInTail.setOrgPay(arithmeticInterface.getPay(new BigDecimal(
                                    traninImportDTO.getSalaryBase()), orgPayRate));
                              }
                              //为扎帐准备数据
                              tranInTail.setOpenDate(securityInfo.getUserInfo().getBizDate());
                              tranInTail.setName(traninImportDTO.getName());
                              tranInTail.setMobileTel(traninImportDTO
                                  .getMobileTel());
                              // 判断职工的证件号码
                              if (tranInTail.getCardKind().toString().equals("0")&&tranInTail.getCardNum().length()==15) {
                                String num_to18 = empInfoDAO.queryCardNumTo18(tranInTail.getCardNum());
                                tranInTail.setStandbyCardNum(num_to18);
                              }else{
                                tranInTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
                              }
                              tranInTailDAO.insert(tranInTail);
                            }
                            else{
                              throw new BusinessException("性别输入有误!");
                            }
                          }else{
                            throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"工资基数不正确");
                          }
                        }else{
                          throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"出生日不能为空!");
                        }
                      }
                    }
                  }
                }else {
                  throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+" 职工中存在此人"+"姓名为: "+traninImportDTO.getName()+" 证件号码为: "+traninImportDTO.getCardNum());
                }
                //  判断同一身份证号码下是否存在姓名不同的职工
                List temp_list = empDAO.queryEmpInfoByTranin_FYF(traninImportDTO
                    .getName(), traninImportDTO.getCardNum());
                if (temp_list.size()>0) {
                  throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"已经存在姓名不同但是身份证号相同的职工");
                }
              }else{
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"证件号码不能为空！");
              }
            }else{
              throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"证件类型不能为空！");
            }
          }else{
            throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"职工姓名不能为空！");
          }

          //判断  转入金额   单位工资基数   职工工资基数  是否为空
          if(traninImportDTO.getCurBalance() == null || traninImportDTO.getCurBalance().equals("")){
             throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"本年余额不能为空！");
          }
          if(traninImportDTO.getCurInterest() == null || traninImportDTO.getCurInterest().equals("")){
            throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"利息不能为空！");
          }
          if(traninImportDTO.getPreBalance() == null || traninImportDTO.getPreBalance().equals("")){
             throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"往年余额不能为空！");
          }
          
        }
        TraninImportHeadDTO isTraninImportHeadDTO = (TraninImportHeadDTO) traninImportList1
        .get(0);
        if(!isTraninImportHeadDTO.getInOrgId().equals("")&&!isTraninImportHeadDTO.getInOrgName().equals("")){
//        插入到BA003
        Map map = new HashMap();
        String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN).toString();
        String opbutton=new Integer(BusiLogConst.BIZBLOG_ACTION_IMP).toString();
        map.put("opModel", opMode);
        map.put("opButton", opbutton);
        map.put("opBizId", id);
        map.put("orgId", inOrgId);
        this.insertHafOperateLog(map,securityInfo);
        }
        // 插入AA319
        this.insertTranInBizActivityLog(id, securityInfo.getUserInfo().getUsername(), "1");
      }else {
        throw new BusinessException("导入单位不符");
      }
    }catch(BusinessException be){
      throw be;
    }
    catch (Exception e) {
      throw new BusinessException("导入失败!");
    }
  }

  /**
   * 不能为空字段 Birthday(); CardKind(); CardNum(); CurBalance(); CurInterest();
   * preBalance(); Name(); PreInterest(); SalaryBase(); Sex();
   */
  public void modifyTraninBatch_zl(List traninImportList1, List traninImportList2,
      String inOrgId,SecurityInfo securityInfo) throws BusinessException {
    List traninList = new ArrayList();
    try {
      TraninImportHeadDTO traninImportHeadDTO = (TraninImportHeadDTO) traninImportList1
          .get(1);
      String inOrgIdImp = traninImportHeadDTO.getInOrgId();
      //保证操作编号是6位
      inOrgIdImp=BusiTools.convertSixNumber(inOrgIdImp);
      Org orgs = orgDAO.queryById(new Integer(inOrgId));
      String is_payMode=orgs.getPayMode().toString();
      String inOrgName = orgs.getOrgInfo().getName();
      String inOrgNameImp = traninImportHeadDTO.getInOrgName();
      String noteNumImp = traninImportHeadDTO.getNoteNum();
      TraninImportDTO traninImportDTO = new TraninImportDTO();
      TranInHead tranInHead = new TranInHead();
      TranInHead tranInHeadLast = new TranInHead();
      String temp_orgId=BusiTools.convertSixNumber(inOrgId);
      String id = "";
      if (inOrgNameImp.equals(inOrgName) && inOrgIdImp.equals(temp_orgId)) {
        for (int i = 1; i < traninImportList2.size(); i++) {
          UtilRule utilRule=new UtilRule();
          traninImportDTO = (TraninImportDTO) traninImportList2.get(i);
          if(is_payMode.equals("2")){
            if(traninImportDTO.getOrgPay()!=null&&!traninImportDTO.getOrgPay().equals("")){
              if(utilRule.moneyRule(traninImportDTO.getOrgPay(), 15, 2)==false){
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"单位缴额格式不正确");
              }
            }else{
              throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"单位缴额不能为空.");
            }
            if(traninImportDTO.getEmpPay()!=null&&!traninImportDTO.equals("")){
              if(utilRule.moneyRule(traninImportDTO.getOrgPay(), 15, 2)==false){
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"职工缴额不正确");
              }
            }else{
              throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"职工缴额不能为空.");
            }
          }
          if (traninImportDTO.getName() != null
              && !traninImportDTO.getName().equals("")) {
            if (traninImportDTO.getCardKind() != null
                && !traninImportDTO.getCardKind().equals("")) {
             
                if(traninImportDTO.getCardKind().trim().equals("0")){
                  if(!((traninImportDTO.getCardNum().length()==18)||(traninImportDTO.getCardNum().length()==15))){
                    throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"身份证位数不正确");
                  }
                }
              try {
                Integer.parseInt(traninImportDTO.getCardKind());
              } catch (Exception s) {
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"类型格式不正确");
              }
              if (traninImportDTO.getCardNum() != null
                  && !traninImportDTO.getCardNum().equals("")) {
//                System.out.println(traninImportDTO.getCardNum());
//                try {
//                  Integer.parseInt(traninImportDTO.getCardNum());
//                } catch (Exception s) {
//                  throw new BusinessException("类型格式不正确");
//                }
                String temp_inOrgIdImp=null;
                //模糊查询只要姓名和身份证号相同的就不叫他插入
                List empList = empDAO.getEmp_WL(temp_inOrgIdImp, traninImportDTO
                    .getName(), traninImportDTO.getCardNum());
                if (empList.isEmpty()) {
//                  if (traninImportDTO.getCurBalance() != null
//                      && !traninImportDTO.getCurBalance().equals("")) {
//                    try {
//                      Integer.parseInt(traninImportDTO.getCurBalance());
//                    } catch (Exception s) {
//                      throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"本年余额格式不正确");
//                    }
//                    if (traninImportDTO.getCurInterest() != null
//                        && !traninImportDTO.getCurInterest().equals("")) {
//                      try {
//                        Integer.parseInt(traninImportDTO.getCurInterest());
//                      } catch (Exception s) {
//                        throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"利息格式不正确");
//                      }
//                      if (traninImportDTO.getPreBalance() != null
//                          && !traninImportDTO.getPreBalance().equals("")) {
//                        try {
//                          Integer.parseInt(traninImportDTO.getPreBalance());
//                        } catch (Exception s) {
//                          throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"往年余额格式不正确");
//                        }
//                        if (traninImportDTO.getBirthday() != null
//                            && !traninImportDTO.getBirthday().equals("")) {
//                          try {
//                            Integer.parseInt(traninImportDTO.getBirthday());
//                          } catch (Exception s) {
//                            throw new BusinessException("导入数据第"+""+i+""+"行数据,"+"出生日期格式不正确");
//                          }
                          if (traninImportDTO.getSalaryBase() != null
                              && !traninImportDTO.getSalaryBase().equals("")) {
                          
                              if(utilRule.moneyRule(traninImportDTO.getSalaryBase(), 15, 2)==false){
                                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"工资格式不正确");
                              }
                            if (traninImportDTO.getSex()!= null
                                && !traninImportDTO.getSex().equals("")) {
                              try {
                                Integer.parseInt(traninImportDTO.getSex());
                              } catch (Exception s) {
                                throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"性别输入不正确");
                              }
                              // 如果证件类型是身份证，判断导入文件以中是否存在相同的职工
                              if (traninImportDTO.getCardKind().equals("0")) {
                                for (int j = 1; j < traninImportList2.size(); j++) {
                                  TraninImportDTO traninImportDTO1 = (TraninImportDTO)traninImportList2.get(j);
                                  if (i!=j) {
                                    String temp_cardNum = traninImportDTO1.getCardNum();
                                    String temp_cardNum15 = "";
                                    if (temp_cardNum.length()==18) {
                                      temp_cardNum15 = temp_cardNum.substring(0, 6)+temp_cardNum.substring(8, 17);
                                    }else{
                                      temp_cardNum15 = temp_cardNum;
                                    }
                                    if (traninImportDTO.getCardNum().equals(temp_cardNum)||traninImportDTO.getCardNum().equals(temp_cardNum15)) {
                                      throw new BusinessException("第" + (i+5) + "行记录中的身份证号与第"+(j+5)+"行记录的身份证号相同！！");
                                    }
                                  }
                                }
                              }
                              Integer empId = empDAO.makeEmpIdSL();
                              TranInTail tranInTail=new TranInTail();
                               tranInTail.setEmpId(empId);
                              if (id.equals("")) {
                                tranInHead.setTranStatus(new BigDecimal(1.00));
                                tranInHead.setNoteNum(noteNumImp);
                                TranInOrg tranInOrg = tranInOrgDAO
                                    .queryById(new Integer(inOrgIdImp));
                                tranInHead.setTranInOrg(tranInOrg);
                                id = (String) tranInHeadDAO.insert(tranInHead);
                              centerTranHeadId=id;
                              }
                              tranInHeadLast = tranInHeadDAO.queryById(id);
                              tranInTail.setTranInHead(tranInHeadLast);
                              tranInTail.setBirthday(traninImportDTO
                                  .getBirthday());
                              tranInTail.setCardKind(new Integer(
                                  traninImportDTO.getCardKind()));
                              tranInTail.setCardNum(traninImportDTO
                                  .getCardNum());
                              if(traninImportDTO.getPreBalance()!=null&&!traninImportDTO.getPreBalance().equals("")){
                              BigDecimal preIntegral=this.getpreInt(new BigDecimal(traninImportDTO.getPreBalance()),securityInfo.getUserInfo().getBizDate());
                              tranInTail.setPreIntegral(preIntegral);
                             
                              }else{
                                tranInTail.setPreIntegral(new BigDecimal(0.00));
                              }
                              if(traninImportDTO.getCurBalance()!=null&&!traninImportDTO.getCurBalance().equals("")){
                                BigDecimal curIntegral=this.getCurInt(new BigDecimal(traninImportDTO.getCurBalance()),securityInfo.getUserInfo().getBizDate());
                                tranInTail.setCurIntegral(curIntegral);
                              }else{
                                tranInTail.setCurIntegral(new BigDecimal(0.00));
                              }
                              if(traninImportDTO.getCurBalance()!=null&&!traninImportDTO.getCurBalance().equals("")){
                              tranInTail.setCurBalance(new BigDecimal(
                                  traninImportDTO.getCurBalance()));
                              }
                              if(traninImportDTO.getPreBalance()!=null&&!traninImportDTO.getPreBalance().equals("")){
                              tranInTail.setPreBalance(new BigDecimal(
                                  traninImportDTO.getPreBalance()));
                              }
                              if(traninImportDTO.getCurInterest()!=null&&!traninImportDTO.getCurInterest().equals("")){
                              tranInTail.setCurInterest(new BigDecimal(
                                  traninImportDTO.getCurInterest()));
                              }
                              if(traninImportDTO.getMonthIncome()!=null&&!traninImportDTO.getMonthIncome().equals("")){
                              tranInTail.setMonthIncome(new BigDecimal(
                                  traninImportDTO.getMonthIncome()));
                              }
                              if(traninImportDTO.getTel()!=null&&!traninImportDTO.getTel().equals("")){
                              tranInTail.setTel(traninImportDTO.getTel());
                              }
                              if(traninImportDTO.getSalaryBase()!=null&&!traninImportDTO.getSalaryBase().equals("")){
                              tranInTail.setSalaryBase(new BigDecimal(
                                  traninImportDTO.getSalaryBase()));
                              }
                              tranInTail.setSex(new Integer(traninImportDTO
                                  .getSex()));
                              if (traninImportDTO.getEmpPay() != null
                                  && !traninImportDTO.getEmpPay().equals("")) {
                                tranInTail.setEmpPay(new BigDecimal(
                                    traninImportDTO.getEmpPay()));
                              }
                              if (traninImportDTO.getOrgPay() != null
                                  && !traninImportDTO.getOrgPay().equals("")) {
                                tranInTail.setOrgPay(new BigDecimal(
                                    traninImportDTO.getOrgPay()));
                              }
                              //为扎帐准备数据
                              tranInTail.setOpenDate(securityInfo.getUserInfo().getBizDate());
                              tranInTail.setName(traninImportDTO.getName());
                              tranInTail.setMobileTel(traninImportDTO
                                  .getMobileTel());
                              // 判断职工的证件号码
                              if (tranInTail.getCardKind().toString().equals("0")&&tranInTail.getCardNum().length()==15) {
                                String num_to18 = empInfoDAO.queryCardNumTo18(tranInTail.getCardNum());
                                tranInTail.setStandbyCardNum(num_to18);
                              }else{
                                tranInTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
                              }
                              tranInTailDAO.insert(tranInTail);
                            }
                            else{
                              throw new BusinessException("性别输入有误!");
                            }
                          }else{
                            throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"工资基数不正确");
                          }
//                        }
//                      }
//                    }
//                  }
                }else {
                  throw new BusinessException("该单位已经存在此职工!");
                }
                //  判断同一身份证号码下是否存在姓名不同的职工
                List temp_list = empDAO.queryEmpInfoByTranin_FYF(traninImportDTO
                    .getName(), traninImportDTO.getCardNum());
                if (temp_list.size()>0) {
                  throw new BusinessException("导入数据第"+""+(i+5)+""+"行数据,"+"已经存在姓名不同但是身份证号相同的职工");
                }
              }else{
                throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"证件号码不能为空！");
              }
            }else{
              throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"证件类型不能为空！");
            }
          }else{
            throw new BusinessException("导入数据第"+""+(i+5)+""+"行,"+"职工姓名不能为空！");
          }

        }
        TraninImportHeadDTO isTraninImportHeadDTO = (TraninImportHeadDTO) traninImportList1
        .get(0);
        if(!isTraninImportHeadDTO.getInOrgId().equals("")&&!isTraninImportHeadDTO.getInOrgName().equals("")){
//        插入到BA003
        Map map = new HashMap();
        String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN).toString();
        String opbutton=new Integer(BusiLogConst.BIZBLOG_ACTION_IMP).toString();
        map.put("opModel", opMode);
        map.put("opButton", opbutton);
        map.put("opBizId", id);
        map.put("orgId", inOrgId);
        this.insertHafOperateLog(map,securityInfo);
        }
        // 插入AA319
        this.insertTranInBizActivityLog(id, securityInfo.getUserInfo().getUsername(), "1");
      }else {
        throw new BusinessException("导入单位不符");
      }
    }catch(BusinessException be){
      throw be;
    }
    catch (Exception e) {
      throw new BusinessException("导入失败!");
    }
  }
  
  // 查找转入单位
  public TranInOrg queryTranInOrg(Integer id) {
    TranInOrg tranInOrg = null;
    try {
      tranInOrg = tranInOrgDAO.queryById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tranInOrg;

  }

  // 比较单位是否存在
  public Org queryOrgDAO(Integer id,SecurityInfo securityInfo) {
    Org org = null;
    try {
      org = orgDAO.queryByCriterions(id.toString(),"","",securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }
  public Org queryOrg_yg(Integer id) {
    Org org = null;
    try {
      org = orgDAO.queryById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }

  // 维护删除
  public void deletePageList(String tranInHeadById,SecurityInfo securityInfo)
      throws BusinessException {

    try {
      String orgId="";
      TranInTail tranInTail = null;
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      tranInTailDAO.deleteTranTrail(new Integer(tranInHeadById));
      TranInHead tranInHead = tranInHeadDAO.queryById(tranInHeadById);
      orgId=tranInHead.getTranInOrg().getId().toString();
        tranInHeadDAO.deleteTranHead_sy(tranInHead);
      //删除头表中以存在插入ba003操作。
      Map map = new HashMap();
      String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN).toString();
      String opbutton=new Integer(BusiLogConst.BIZLOG_ACTION_DELETE).toString();
      map.put("opModel", opMode);
      map.put("opButton", opbutton);
      map.put("opBizId", tranInHeadById);
      map.put("orgId", orgId);
      this.insertHafOperateLog(map,securityInfo);
      // 删除AA101
//      orgHAFAccountFlow = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowByCriterions(tranInHeadById,"F");
      OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn=orgHAFAccountFlowTransInDAO.queryOrgHAFAccountFlowTransIn(tranInHeadById);
      if (orgHAFAccountFlowTransIn != null && !orgHAFAccountFlowTransIn.equals("")) {

        // 删除AA102
        List empHAFAccountFlowListByOrgFlowList = empHAFAccountFlowDAO
            .queryEmpHAFAccountFlowListByOrgFlowId(orgHAFAccountFlowTransIn.getId()
                .toString());
        
        if (!empHAFAccountFlowListByOrgFlowList.isEmpty()) {
          empHAFAccountFlowDAO
              .deleteEmpHAFAccountFlowAll_sy(empHAFAccountFlowListByOrgFlowList);
        }
        // 删除AA101
        orgHAFAccountFlowTransInDAO.deleteById(orgHAFAccountFlowTransIn);
      }
     if(tranInHeadById!=null&&!tranInHeadById.equals("")){
       // 删除AA319
//       System.out.println("ok3"+tranInHeadById);
       TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadById,new Integer(1),"F");
       System.out.println(tranInBizActivityLog.getId());
       tranInBizActivityLogDAO.delete_sy(tranInBizActivityLog);
     }
     //20071213
     String istype=securityInfo.getIsOrgEdition()+"";
     if(istype.equals("2")){
       try {
         autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, "", "", orgId, tranInHeadById,BusiConst.ORGBUSINESSTYPE_F);
       } catch (Exception e) {
         throw new BusinessException("数据有问题");
       }
     }
     if(istype.equals("1")){
       //20071213查看状态
       String status=autoInfoPickDAODW.findStatus(orgId, tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
       if(status.equals("1")){
         throw new BusinessException("中心已提取数据，不可以删除!");
       }
       if(status.equals("0")){
         throw new BusinessException("请先撤销提交数据!");
       }
     }
     //20071213
    }catch(BusinessException bx){
      throw bx;
    }
    catch (Exception e) {
      throw new BusinessException("操作失败!!");
    }
  }

  // 删除页面转入全部记录
  public void deletePageList_sy(String tranInHeadById,SecurityInfo securityInfo)
      throws BusinessException {
    try {
      TranInTail tranInTail = null;
      tranInTailDAO.deleteTranTrail(new Integer(tranInHeadById));
      TranInHead tranInHead = tranInHeadDAO.queryById(tranInHeadById);
      String orgId=tranInHead.getTranInOrg().getId().toString();
      tranInHeadDAO.deleteTranHead_sy(tranInHead);
      //全部删除已经插入ba003
      Map map = new HashMap();
      String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN).toString();
      String opbutton=new Integer(BusiLogConst.BIZLOG_ACTION_DELETEALL).toString();
      map.put("opModel", opMode);
      map.put("opButton", opbutton);
      map.put("opBizId", tranInHeadById);
      map.put("orgId", orgId);
      this.insertHafOperateLog(map,securityInfo);
      // 删除AA101
      OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn=orgHAFAccountFlowTransInDAO.queryOrgHAFAccountFlowTransIn(tranInHeadById);
//      orgHAFAccountFlow = orgHAFAccountFlowDAO.queryByBizId(tranInHeadById);
      if (orgHAFAccountFlowTransIn != null && !orgHAFAccountFlowTransIn.equals("")) {

        // 删除AA102
        List empHAFAccountFlowListByOrgFlowList = empHAFAccountFlowDAO
            .queryEmpHAFAccountFlowListByOrgFlowId(orgHAFAccountFlowTransIn.getId()
                .toString());
        
        if (!empHAFAccountFlowListByOrgFlowList.isEmpty()) {
          empHAFAccountFlowDAO
              .deleteEmpHAFAccountFlowAll_sy(empHAFAccountFlowListByOrgFlowList);
        }
        // 删除AA101
        orgHAFAccountFlowTransInDAO.deleteById(orgHAFAccountFlowTransIn);
       
      }
      if(tranInHeadById!=null&&!tranInHeadById.equals("")){
        // 删除AA319
        System.out.println("ok3"+tranInHeadById);
        TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadById,new Integer(1),"F");
        System.out.println(tranInBizActivityLog.getId());
        tranInBizActivityLogDAO.delete_sy(tranInBizActivityLog);
      }
      //20071213
      String istype=securityInfo.getIsOrgEdition()+"";
      if(istype.equals("2")){
        try {
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, "", "", orgId, tranInHeadById,BusiConst.ORGBUSINESSTYPE_F);
        } catch (Exception e) {
          throw new BusinessException("数据有问题");
        }
      }
      if(istype.equals("1")){
        //20071213查看状态
        String status=autoInfoPickDAODW.findStatus(orgId, tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
        if(status.equals("1")){
          throw new BusinessException("中心已提取数据，不可以删除!");
        }
        if(status.equals("0")){
          throw new BusinessException("请先撤销提交数据!");
        }
      }
      //20071213
    }catch(BusinessException bx){
      throw bx;
    }
    catch (Exception e) {
      throw new BusinessException("操作失败!!");
    }
  }

  // 删除头表记录注意要删除对应的记录
  public void deleteTranInHead_sy(String tranInHeadById, String orgId,SecurityInfo securityInfo)
      throws BusinessException {
    OrgHAFAccountFlow orgHAFAccountFlow=null;
    String op_ip=securityInfo.getUserInfo().getUserIp();
    String operator=securityInfo.getUserInfo().getUsername();
    TranInHead tranInHead = tranInHeadDAO.queryById(tranInHeadById);
    try {
      tranInHeadDAO.deleteTranHead_sy(tranInHead);
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer
          .toString(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN));
      hafOperateLog.setOpButton(Integer
          .toString(BusiLogConst.BIZLOG_ACTION_DELETE));
      hafOperateLog.setOpBizId(new Integer(tranInHeadById));
      hafOperateLog.setOperator(operator);
      hafOperateLog.setOpIp(op_ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId));
      hafOperateLogDAO.insert(hafOperateLog);
      // 删除AA101
      OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn=orgHAFAccountFlowTransInDAO.queryOrgHAFAccountFlowTransIn(tranInHeadById);
//      orgHAFAccountFlow = orgHAFAccountFlowDAO.queryByBizId(tranInHeadById);
      if (orgHAFAccountFlowTransIn != null && !orgHAFAccountFlowTransIn.equals("")) {

        // 删除AA102
        List empHAFAccountFlowListByOrgFlowList = empHAFAccountFlowDAO
            .queryEmpHAFAccountFlowListByOrgFlowId(orgHAFAccountFlowTransIn.getId()
                .toString());
        
        if (!empHAFAccountFlowListByOrgFlowList.isEmpty()) {
          empHAFAccountFlowDAO
              .deleteEmpHAFAccountFlowAll_sy(empHAFAccountFlowListByOrgFlowList);
        }
        // 删除AA101
        orgHAFAccountFlowTransInDAO.deleteById(orgHAFAccountFlowTransIn);
       
      }
      // 删除AA319
      if(tranInHeadById!=null&&!tranInHeadById.equals("")){
      TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadById,new Integer(1),"F");
      System.out.println(tranInBizActivityLog.getId());
      tranInBizActivityLogDAO.delete_sy(tranInBizActivityLog);
      }
      } catch (Exception e) {
      throw new BusinessException("操作失败!!");
    }
  }

  // 删除尾表
  public void deleteTranInTail_sy(Integer id, String tranInHeadById,
      String orgId,SecurityInfo securityInfo) throws BusinessException {
    try {
      TranInTail tranInTail = tranInTailDAO.queryById(id);
      tranInTailDAO.deleteTranInTail_sy(tranInTail);
      int i = this.countTraninListByCriterions(tranInHeadById,securityInfo);
      if (i == 0) {
        this.deleteTranInHead_sy(tranInHeadById, orgId,securityInfo);
        //20071213
        String istype=securityInfo.getIsOrgEdition()+"";
        if(istype.equals("2")){
          try {
            autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, "", "", orgId, tranInHeadById,BusiConst.ORGBUSINESSTYPE_F);
          } catch (Exception e) {
            throw new BusinessException("数据有问题");
          }
        }
        if(istype.equals("1")){
          //20071213查看状态
          String status=autoInfoPickDAODW.findStatus(orgId, tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
          if(status.equals("1")){
            throw new BusinessException("中心已提取数据，不可以删除!");
          }
          if(status.equals("0")){
            throw new BusinessException("请先撤销提交数据!");
          }
        }
        //20071213
      }else{
      Map map = new HashMap();
      String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN).toString();
      String opbutton=new Integer(BusiLogConst.BIZLOG_ACTION_DELETE).toString();
      map.put("opModel", opMode);
      map.put("opButton", opbutton);
      map.put("opBizId", tranInHeadById);
      map.put("orgId", orgId);
      this.insertHafOperateLog(map,securityInfo);
      }
    }catch(BusinessException bx){
      throw bx;
    } 
    catch (Exception e) {
      throw new BusinessException("操作失败!!");
    }
  }

  // 更新尾表
  public void updataTranInTail_sy(TranInTail tranInTail, String tranInHeadById,SecurityInfo securityInfo)
      throws BusinessException {
    try {

      tranInTail.setEmpId(tranInTail.getEmpId());
      TranInHead tranInHead = tranInHeadDAO.queryById(new Integer(
          tranInHeadById));
      tranInTail.setTranInHead(tranInHead);
      String orgId = tranInHead.getTranInOrg().getOrgInfo().getId().toString();
      //更改积数
      BigDecimal preIntegral=this.getpreInt(tranInTail.getPreBalance(),securityInfo.getUserInfo().getBizDate());
      BigDecimal curIntegral=this.getCurInt(tranInTail.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//    为扎帐准备数据
      tranInTail.setOpenDate(securityInfo.getUserInfo().getBizDate());
      tranInTail.setPreIntegral(preIntegral);
      tranInTail.setCurIntegral(curIntegral);
      tranInTailDAO.updataTranInTail_sy(tranInTail);
      Map map = new HashMap();
      String opMode=new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN).toString();
      String opbutton=new Integer(BusiLogConst.BIZLOG_ACTION_MODIFY).toString();
      map.put("opModel", opMode);
      map.put("opButton", opbutton);
      map.put("opBizId", tranInHeadById);
      map.put("orgId", orgId);
      this.insertHafOperateLog(map,securityInfo);
    } catch (Exception e) {
      throw new BusinessException("操作失败!!");
    }
  }

  // 通过尾表id查找尾表
  public TranInTail queryTranInTailById(Integer id,SecurityInfo securityInfo) throws BusinessException {
    TranInTail tranInTail = null;
    try {
      tranInTail = tranInTailDAO.queryById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tranInTail;
  }

  // 头表插入
  public Serializable insertTranInHead(TranInHead tranInHead)
      throws BusinessException {

    Serializable id = null;
    try {
      id = tranInHeadDAO.insert(tranInHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  // 尾表插入
  public void insertTranInTail(TranInTail tranInTail) throws Exception,
      BusinessException {
    tranInTailDAO.insert(tranInTail);
  }

  // 通过头表id查找
  public TraninAddAF findTranInHeadById(Integer id, SecurityInfo securityInfo) throws BusinessException {
    TraninAddAF traninAddAF = new TraninAddAF();
    try {
      List list = new ArrayList();
      Org org = orgDAO.queryById(id);
      if (org != null && !orgDAO.equals("")) {
        if (org.getPayMode().equals(new BigDecimal(1.00))) {
          // 用于页面计算
          BigDecimal orgRate = org.getOrgRate();
          BigDecimal empRate = org.getEmpRate();
          Integer payPrecision = org.getPayPrecision();
          traninAddAF.setOrgRate(orgRate);
          traninAddAF.setEmpRate(empRate);
          traninAddAF.setPayPrecision(payPrecision);
        }
      }
      traninAddAF.setInOrgId(id.toString());
      Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
      Map documentsstateMap = BusiTools
          .listBusiProperty(BusiConst.DOCUMENTSSTATE);
      traninAddAF.setSexMap(sexMap);
      traninAddAF.setDocumentsstateMap(documentsstateMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return traninAddAF;
  }

  // 通过条件查找
  public List findTraninListByCriterions(Pagination pagination,SecurityInfo securityInfo) {

    List list = new ArrayList();

    String inOrgId = (String) pagination.getQueryCriterions().get(
        "tranInHead.InOrg.id");
    String tranInHeadId = (String) pagination.getQueryCriterions().get(
        "tranInHeadId");
    String tranInTailName = (String) pagination.getQueryCriterions().get(
        "tranInTailName");
    String tranInTailCardNum = (String) pagination.getQueryCriterions().get(
        "tranInTailCardNum");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List tranInHeadList = new ArrayList();
    List returnlist = new ArrayList();
    String tranStatus = null;
    TranInHead tranInHead = null;
    String notenumber="";
    int page = pagination.getPage();
    try {
      if (inOrgId != null && !inOrgId.equals("")) {
        tranStatus = "1";
        tranInHeadList = this.queryTranInHeandInOrgId(inOrgId, tranStatus);
        if (tranInHeadList != null && !tranInHeadList.isEmpty()) {
          tranInHead = (TranInHead) tranInHeadList.get(0);
          notenumber=tranInHead.getNoteNum();
          tranInHeadId = tranInHead.getId().toString();
          pagination.getQueryCriterions().put("tranInHeadById", tranInHeadId);
          list = tranInTailDAO.queryTraninListByCriterions(tranInHeadId,
              tranInTailName, tranInTailCardNum, orderBy, order, start,
              pageSize,securityInfo,page);
          if (list != null) {
            for (int i = 0; i < list.size(); i++) {
              TranInTail tranInTail = (TranInTail) list.get(i);
              tranInTail.setSumBalance(tranInTail.getCurBalance(), tranInTail
                  .getPreBalance());
              tranInTail.setSumInterest(tranInTail.getPreInterest(), tranInTail
                  .getCurInterest());
              tranInTail.setTraninAmount(tranInTail.getSumBalance(), tranInTail
                  .getSumInterest());
              returnlist.add(tranInTail);
            }
          }
          String istype=securityInfo.getIsOrgEdition()+"";
          if(istype.equals("2")){
        //  20071213查看状态
          String status=autoInfoPickDAO.findStatus(tranInHead.getTranInOrg().getId().toString(), tranInHead.getId().toString(), BusiConst.ORGBUSINESSTYPE_F);
          pagination.getQueryCriterions().put("istype", status);
          //20071213
          }else{
//          20071213查看状态
            String status=autoInfoPickDAODW.findStatus(tranInHead.getTranInOrg().getId().toString(), tranInHead.getId().toString(), BusiConst.ORGBUSINESSTYPE_F);
            pagination.getQueryCriterions().put("istype", status);
            //20071213
          }
        }
      }
      pagination.getQueryCriterions().put("notenumber", notenumber);
      String id = (String) pagination.getQueryCriterions()
          .get("tranInHeadById");
      int count=0;
      // 当有数据的时候统计项数
      if(!list.isEmpty()){
       count = this.countTraninListByCriterions(id,securityInfo);
      }
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnlist;
  }

  // 统计转入尾表个数
  public int countTraninListByCriterions(String id,SecurityInfo securityInfo) throws BusinessException {
    int count = 0;
    try {
      count = tranInTailDAO.countTraninListByCriterions(id,securityInfo).size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  // 查找状态
  public List queryTranInHeandInOrgId(String inOrgId, String tranStatus)
      throws BusinessException {
    List list = new ArrayList();
    try {
      list = tranInHeadDAO.queryByInOrgId(inOrgId, tranStatus);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 统计相关转入总和
  public Pagination countTraninListAll(Pagination pagination,SecurityInfo securityInfo)
      throws BusinessException {
    String id = (String) pagination.getQueryCriterions().get("tranInHeadById");
    List listAll = new ArrayList();
    List list = new ArrayList();
    List list1 = new ArrayList();
    try {
      listAll = tranInTailDAO.countTraninListByCriterions(id,securityInfo);
      if (listAll != null) {
        for (int i = 0; i < listAll.size(); i++) {
          TranInTail tranInTail = (TranInTail) listAll.get(i);
          list.add(tranInTail.getPreBalance());
          list.add(tranInTail.getCurBalance());
          list1.add(tranInTail.getPreInterest());
          list1.add(tranInTail.getCurInterest());
        }
        BigDecimal sumBalanceAll = new BigDecimal(0.00);
        BigDecimal sumInterestAll = new BigDecimal(0.00);
        BigDecimal sumTraninAll = new BigDecimal(0.00);
        for (int i = 0; i < list.size(); i++) {
          BigDecimal sumBalance = (BigDecimal) list.get(i);
          sumBalanceAll = sumBalanceAll.add(sumBalance);
        }
        for (int i = 0; i < list1.size(); i++) {
          BigDecimal sumInterest = (BigDecimal) list1.get(i);
          sumInterestAll = sumInterestAll.add(sumInterest);
        }
        sumTraninAll = sumTraninAll.add(sumBalanceAll).add(sumInterestAll);
        Integer traninPeople = new Integer(listAll.size());
        pagination.getQueryCriterions().put("traninPeople", traninPeople);
        pagination.getQueryCriterions().put("sumBalanceAll", sumBalanceAll);
        pagination.getQueryCriterions().put("sumInterestAll", sumInterestAll);
        pagination.getQueryCriterions().put("sumTraninAll", sumTraninAll);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pagination;
  }

  //

  // 插入BA003
  public void insertHafOperateLog(Map map,SecurityInfo securityInfo) {
    try {
      String opModel = (String) map.get("opModel");
      String opButton = (String) map.get("opButton");
      String opBizId = (String) map.get("opBizId");
      String op_ip=securityInfo.getUserInfo().getUserIp();
    
      String operator=securityInfo.getUserInfo().getUsername();
      String orgId = (String) map.get("orgId");
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(opModel);
      hafOperateLog.setOpButton(opButton);
      hafOperateLog.setOpBizId(new Integer(opBizId));
      hafOperateLog.setOperator(operator);
      hafOperateLog.setOpIp(op_ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId));
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 批量导处插入BA003
  public void insertHafOperateLog_sy(String orgId,SecurityInfo securityInfo) {
    String op_ip=securityInfo.getUserInfo().getUserIp();
    String operator=securityInfo.getUserInfo().getUsername();
    try {
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(
          BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN).toString());
      hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_EXP)
          .toString());
      hafOperateLog.setOpBizId(new Integer(0));
      hafOperateLog.setOperator(operator);
      hafOperateLog.setOpIp(op_ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId));
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 维护修改插入BA003
  public String modiftHafOperateLog_sy(String tranInHeadId,SecurityInfo securityInfo) {
    String orgId="";
    String op_ip=securityInfo.getUserInfo().getUserIp();
  
    String operator=securityInfo.getUserInfo().getUsername();
    try {
      TranInHead tranInHead = tranInHeadDAO.queryById(tranInHeadId);
      orgId=tranInHead.getTranInOrg().getId().toString();
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(
          BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN).toString());
      hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_MODIFY)
          .toString());
      hafOperateLog.setOpBizId(new Integer(tranInHeadId));
      hafOperateLog.setOperator(operator);
      hafOperateLog.setOpIp(op_ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId));
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgId;
  }

  // 转入维护——撤消转入登记
  public void adjustTranin_sy(String tranInHeadId, SecurityInfo securityInfo)
      throws BusinessException {
    String op_ip = securityInfo.getUserInfo().getUserIp();
    String operator = securityInfo.getUserInfo().getUsername();

    try {
      String tranStatus = "1";
      // TranInHead tranInHead = new TranInHead();
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      TranInHead tranInHead = tranInHeadDAO
          .queryById(new Integer(tranInHeadId));
      String inOrgId = tranInHead.getTranInOrg().getId().toString();

      // 根据头表ID先判断一下改业务是否已经是完成状态
      if (tranInHead.getTranStatus().toString().equals("1")) {
        throw new BusinessException("业务状态已经为录入清册状态!");
      }
      // List list = this.queryTranInHeandInOrgId(inOrgId, tranStatus);

      // tranInHead = tranInHeadDAO.queryById(new Integer(tranInHeadId));
      String tranOutHeadId = tranInHead.getTranOutHeadId().toString();
      Org orgs = orgDAO.getOrg_WL(inOrgId);
      String officeCode = "";
      String docNumDocument = collParaDAO.getDocNumDesignPara();
      if (docNumDocument.equals("1")) {
        officeCode = orgs.getOrgInfo().getOfficecode();
      } else {
        officeCode = orgs.getOrgInfo().getCollectionBankId();
      }
      String bizYearmonth = tranInHead.getSettDate().substring(0, 6);
      String docNum = tranInHead.getDocNum();
      if (tranOutHeadId != null && !tranOutHeadId.equals("")
          && !tranOutHeadId.equals("0")) {
        // List tranInTailList =
        // tranInTailDAO.countTraninListByCriterions(tranInHeadId,securityInfo);
        // 清除凭证号
        System.out.println("--------------撤消凭证号------------>");

        // tranInTailDAO.deleteList(tranInTailList);
        tranInTailDAO.deleteTranTrail(new Integer(tranInHeadId));
        tranInHeadDAO.deleteTranHead_sy(tranInHead);
        docNumCancelDAO.insertDocNumCancel(docNum.substring(8), officeCode.trim(),
            bizYearmonth.trim());
      } else {

        List list = this.queryTranInHeandInOrgId(inOrgId, tranStatus);
        if (list.isEmpty()) {
          tranInHead.setTranStatus(new BigDecimal(1.00));
          tranInHead.setSettDate("");
          tranInHead.setDocNum("");
          tranInHeadDAO.updataTranInHead_sy(tranInHead);
          docNumCancelDAO.insertDocNumCancel(docNum.substring(8), officeCode
              .trim(), bizYearmonth.trim());
        } else {
          throw new BusinessException("操作失败!!");
        }
      }
      OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn = orgHAFAccountFlowTransInDAO
          .queryOrgHAFAccountFlowTransIn(tranInHeadId);
      // orgHAFAccountFlow = orgHAFAccountFlowDAO.queryByBizId(tranInHeadId);
      if (orgHAFAccountFlowTransIn != null
          && !orgHAFAccountFlowTransIn.equals("")) {

        System.out.println("ok2");
        // 插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(new Integer(
            BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN).toString());
        hafOperateLog.setOpButton(new Integer(
            BusiLogConst.BIZLOG_ACTION_REVOCATION).toString());
        hafOperateLog.setOpBizId(new Integer(tranInHeadId));
        hafOperateLog.setOperator(operator);
        hafOperateLog.setOpIp(op_ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(inOrgId));
        hafOperateLogDAO.insert(hafOperateLog);

        // 删除AA102
        // List empHAFAccountFlowListByOrgFlowList = empHAFAccountFlowDAO
        // .queryEmpHAFAccountFlowListByOrgFlowId(orgHAFAccountFlowTransIn.getId()
        // .toString());

        // if (!empHAFAccountFlowListByOrgFlowList.isEmpty()) {
        empHAFAccountFlowDAO.deleteEmpHAFAccountFlowAll(new Integer(
            orgHAFAccountFlowTransIn.getId().toString()));
        // }
        // 删除AA101
        orgHAFAccountFlowTransInDAO.deleteById(orgHAFAccountFlowTransIn);

      }
      // 删除AA319
      if (tranInHeadId != null && !tranInHeadId.equals("")) {
        TranInBizActivityLog tranInBizActivityLog = tranInBizActivityLogDAO
            .queryByBizId(tranInHeadId, new Integer(3), "F");
        tranInBizActivityLogDAO.delete_sy(tranInBizActivityLog);
      }
    } catch (BusinessException ex) {
      throw ex;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("操作失败!!");
    }
  }

  /**
   * 添加
   * 
   * @throws BusinessException
   */
  public String addTranInTail_sy(String inOrgId, String noteNum,
      TranInTail tranInTail1,SecurityInfo securityInfo)throws BusinessException{
    String info = "";
    try {
      String tranStatus = "5";
      TranInTail tranInTail = tranInTail1;
      tranInTail.setMergerEmpid(new BigDecimal(0.00));
      // 判断职工的证件号码
      if (tranInTail.getCardKind().toString().equals("0")&&tranInTail.getCardNum().length()==15) {
        String num_to18 = empInfoDAO.queryCardNumTo18(tranInTail.getCardNum());
        tranInTail.setStandbyCardNum(num_to18);
      }else{
        tranInTail.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
      }
      TranInTail tranInTaillast = tranInTail1;
      String tranInTailName = tranInTail.getName();
      String tranInHeadId = "";
      String tranInTailCardNum = tranInTail.getCardNum();
      //判断是否经行了人员变更,如果有就不叫其修改
      ChgPersonTail chgPersonTail=null;
      chgPersonTail=chgPersonTailDAO.getChgPersonTail_WL(inOrgId, null, tranInTailName, tranInTailCardNum, "1", null);
      if(chgPersonTail!=null){
        throw new BusinessException(tranInTailName+"身份证号码"+tranInTailCardNum+"正在人员变更中,不能进行转入.");
      }
      // 判断是否有不等于5
      List list = this.queryTranInHeandInOrgId(inOrgId, tranStatus);

      Pagination pagination = new Pagination();
      if (list != null && !list.isEmpty()) {
        for (int i = 0; i < list.size(); i++) {
          TranInHead tranInHead = (TranInHead) list.get(i);
          tranInHeadId = tranInHead.getId().toString();
          // pagination.setOrder(OrderEnum.ASC);
          pagination.setOrderother("ASC");
          String inorgid_temp=null;
          List emplist = this.getEmp_sy(inorgid_temp, tranInTailName,
              tranInTailCardNum);
        List traninlist=tranInTailDAO.queryTranInTail(inOrgId,tranInTailName,tranInTailCardNum);
          // 无论哪个公司都没有相同身份证
          if (traninlist.isEmpty() && emplist.isEmpty()) {
            // 有状态为1的
            List is_tranStatus = this.queryTranInHeandInOrgId(inOrgId, "1");
            if (is_tranStatus != null && !is_tranStatus.isEmpty()) {
              Integer empId = this.makeEmpIdSL_sy();
              tranInTaillast.setEmpId(empId);
              TranInHead temp_tranInHead=(TranInHead) is_tranStatus.get(0);
              tranInTaillast.setTranInHead(temp_tranInHead);
              BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
              BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
              tranInTaillast.setPreIntegral(preIntegral);
              tranInTaillast.setCurIntegral(curIntegral);
              //为扎帐准备数据
              tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
              tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
              this.insertTranInTail(tranInTaillast);
              this.insertHafOperateLog_sy(inOrgId, temp_tranInHead.getId().toString(),securityInfo);
              return info = "showTraninListAC";
            } else {
              // 新的第一次插入
              Integer empId = this.makeEmpIdSL_sy();
              tranInTaillast.setEmpId(empId);
              TranInHead temp_tranInHead=new TranInHead();
              if(inOrgId!=null&&!inOrgId.equals("")){
                TranInOrg tranInOrg = tranInOrgDAO.queryById(new Integer(inOrgId));
                temp_tranInHead.setTranInOrg(tranInOrg);
              }
              temp_tranInHead.setNoteNum(noteNum);
              temp_tranInHead.setTranStatus(new BigDecimal(1.00));
              Integer id=new Integer( this.insertTranInHead(temp_tranInHead).toString());
              if(id!=null&&!id.equals("")){
                TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(id.toString(),new Integer(1),"F");
                //如果同一BIZID同一业务类型已经在319中存在就不在插入了
                if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
                  throw new BusinessException("此操作已经进行请核实");
                }
                }
              tranInTaillast.setTranInHead(temp_tranInHead);
              BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
              BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//            为扎帐准备数据
              tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
              tranInTaillast.setPreIntegral(preIntegral);
              tranInTaillast.setCurIntegral(curIntegral);
              tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
              this.insertTranInTail(tranInTaillast);
              this.insertHafOperateLog_sy(inOrgId, id.toString(),securityInfo);
              this.insertTranInBizActivityLog(id.toString(), securityInfo.getUserInfo().getUsername(), "1");
              return info = "showTraninListAC";
            }
          }
          // 有一个相同的empid声明相同的empid在同一个单位下
          else if (traninlist.size()>0) {
            throw new BusinessException("正在转入中");
//            return info = "showTraninListAC";
          }
          // 有一个相同empid声明在
          else if (traninlist.size() != 1 && emplist.size() == 1) {
            Emp emp1 = (Emp) emplist.get(0);
            Integer empId = emp1.getEmpId();
            tranInTaillast.setEmpId(empId);
            List is_tranStatus = this.queryTranInHeandInOrgId(inOrgId, "1");
            if (is_tranStatus != null && !is_tranStatus.isEmpty()) {
              tranInTaillast.setEmpId(empId);
              TranInHead temp_tranInHead=(TranInHead) is_tranStatus.get(0);
              tranInTaillast.setTranInHead(temp_tranInHead);
              BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
              BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//            为扎帐准备数据
              tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
              tranInTaillast.setPreIntegral(preIntegral);
              tranInTaillast.setCurIntegral(curIntegral);
              //查看是否在本单位下
              List emplist2 = this.getEmp_sy(inOrgId, tranInTailName,tranInTailCardNum);
              if(!emplist2.isEmpty()){
              tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
              }
              tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
              this.insertTranInTail(tranInTaillast);
              this.insertHafOperateLog_sy(inOrgId, temp_tranInHead.getId().toString(),securityInfo);
              return info = "showTraninListAC";
            } else {
              // 新的第一次插入
              tranInTaillast.setEmpId(empId);
              TranInHead temp_tranInHead=new TranInHead();
              if(inOrgId!=null&&!inOrgId.equals("")){
                TranInOrg tranInOrg = tranInOrgDAO.queryById(new Integer(inOrgId));
                temp_tranInHead.setTranInOrg(tranInOrg);
              }
              temp_tranInHead.setNoteNum(noteNum);
              temp_tranInHead.setTranStatus(new BigDecimal(1.00));
              Integer id=new Integer (this.insertTranInHead(temp_tranInHead).toString());
              if(id!=null&&!id.equals("")){
                TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(id.toString(),new Integer(1),"F");
                //如果同一BIZID同一业务类型已经在319中存在就不在插入了
                if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
                  throw new BusinessException("此操作已经进行请核实");
                }
                }
              tranInTaillast.setTranInHead(temp_tranInHead);
              BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
              BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//            为扎帐准备数据
              tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
              tranInTaillast.setPreIntegral(preIntegral);
              tranInTaillast.setCurIntegral(curIntegral);
              //查看是否在本单位下
              List emplist2 = this.getEmp_sy(inOrgId, tranInTailName,tranInTailCardNum);
              if(!emplist2.isEmpty()){
              tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
              }
              tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
              tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
              this.insertTranInTail(tranInTaillast);
              this.insertHafOperateLog_sy(inOrgId, id.toString(),securityInfo);
              this.insertTranInBizActivityLog(id.toString(), securityInfo.getUserInfo().getUsername(), "1");
              return info = "showTraninListAC";
            }

          }
          //判断有一个是本单位下的相同的姓名和身份证一个不是本单位下的姓名身份证相同的员工
          else if (emplist.size() > 1 ) {
            //如果仅存在一个在本单位下的直接取empid添加合并id
            List emplist2 = this.getEmp_sy(inOrgId, tranInTailName,tranInTailCardNum);
           if(emplist2.size()==1){
             Emp emp1 = (Emp) emplist.get(0);
             Integer empId = emp1.getEmpId();
             tranInTaillast.setEmpId(empId);
             List is_tranStatus = this.queryTranInHeandInOrgId(inOrgId, "1");
             if (is_tranStatus != null && !is_tranStatus.isEmpty()) {
               
               tranInTaillast.setEmpId(empId);
               TranInHead temp2_tranInHead=(TranInHead) is_tranStatus.get(0);
               tranInTaillast.setTranInHead(temp2_tranInHead);
               BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
               BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//             为扎帐准备数据
               tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
               tranInTaillast.setPreIntegral(preIntegral);
               tranInTaillast.setCurIntegral(curIntegral);
               //查看是否在本单位下
               tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
               tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
               this.insertTranInTail(tranInTaillast);
               this.insertHafOperateLog_sy(inOrgId, temp2_tranInHead.getId().toString(),securityInfo);
               return info = "showTraninListAC";
             } else {
               // 新的第一次插入
               TranInHead tranInHeadNew2 = new TranInHead();
               tranInTaillast.setEmpId(empId);
               tranInHeadNew2.setNoteNum(noteNum);
               if(inOrgId!=null&&!inOrgId.equals("")){
                 TranInOrg tranInOrg = tranInOrgDAO.queryById(new Integer(inOrgId));
                 tranInHeadNew2.setTranInOrg(tranInOrg);
               }
               tranInHeadNew2.setTranStatus(new BigDecimal(1.00));
               Integer id=new Integer( this.insertTranInHead(tranInHeadNew2).toString());
               if(id!=null&&!id.equals("")){
                 TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(id.toString(),new Integer(1),"F");
                 //如果同一BIZID同一业务类型已经在319中存在就不在插入了
                 if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
                   throw new BusinessException("此操作已经进行请核实");
                 }
                 }
               tranInTaillast.setTranInHead(tranInHeadNew2);
               BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
               BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//             为扎帐准备数据
               tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
               tranInTaillast.setPreIntegral(preIntegral);
               tranInTaillast.setCurIntegral(curIntegral);
               tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
               tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
               tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
               tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
               this.insertTranInTail(tranInTaillast);
               this.insertHafOperateLog_sy(inOrgId, id.toString(),securityInfo);
               this.insertTranInBizActivityLog(id.toString(), securityInfo.getUserInfo().getUsername(), "1");
               return info = "showTraninListAC";
             }
           
           }else{
             //跳到页面共其选取
             if(emplist2.size()==2){
               return info = "sameEmployeeMaintainAC";
             }else{
             return info = "employeeMaintainAC";
             }          
             }

          }
        }
      }//以下是状态等于5的时候判断002表 
      else {
        String inorgid_temp=null;
        List emplist = this.getEmp_sy(inorgid_temp, tranInTailName,
            tranInTailCardNum);
        // 无论哪个公司都没有相同身份证
        if (emplist.isEmpty()) {
          // 有状态为1的
          List is_tranStatus = this.queryTranInHeandInOrgId(inOrgId, "1");
          if (is_tranStatus != null && !is_tranStatus.isEmpty()) {
            Integer empId = this.makeEmpIdSL_sy();
            tranInTaillast.setEmpId(empId);
            TranInHead temp_TranInHead=(TranInHead) is_tranStatus.get(0);
            tranInTaillast.setTranInHead(temp_TranInHead);
            BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
            BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//          为扎帐准备数据
            tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
            tranInTaillast.setPreIntegral(preIntegral);
            tranInTaillast.setCurIntegral(curIntegral);
            tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
            tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
            tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
            this.insertTranInTail(tranInTaillast);
            this.insertHafOperateLog_sy(inOrgId,temp_TranInHead.getId().toString(),securityInfo);
            return info = "showTraninListAC";
          } else {
            // 新的第一次插入
          
          TranInHead tranInHeadNew = new TranInHead();
          tranInHeadNew.setNoteNum(noteNum);
          tranInHeadNew.setTranStatus(new BigDecimal(1.00));
          if(inOrgId!=null&&!inOrgId.equals("")){
          TranInOrg tranInOrg = this.queryTranInOrg(new Integer(inOrgId));
          tranInHeadNew.setTranInOrg(tranInOrg);
          }
          String tranInHeadById = (String) this.insertTranInHead(tranInHeadNew);
          if(tranInHeadById!=null&&!tranInHeadById.equals("")){
            TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(tranInHeadById,new Integer(1),"F");
            //如果同一BIZID同一业务类型已经在319中存在就不在插入了
            if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
              throw new BusinessException("此操作已经进行请核实");
            }
            }
          Integer empId = this.makeEmpIdSL_sy();
          tranInTaillast.setEmpId(empId);
          tranInTaillast.setTranInHead(tranInHeadNew);
          BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
          BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//        为扎帐准备数据
          tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
          tranInTaillast.setPreIntegral(preIntegral);
          tranInTaillast.setCurIntegral(curIntegral);
          tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
          this.insertTranInTail(tranInTaillast);
          this.insertHafOperateLog_sy(inOrgId, tranInHeadById,securityInfo);
          this.insertTranInBizActivityLog(tranInHeadById, securityInfo.getUserInfo().getUsername(), "1");
          info = "showTraninListAC";
          }
        }
        else if (emplist.size() == 1) {
        Emp emp1 = (Emp) emplist.get(0);
        Integer empId = emp1.getEmpId();
        tranInTaillast.setEmpId(empId);
        List is_tranStatus = this.queryTranInHeandInOrgId(inOrgId, "1");
        if (is_tranStatus != null && !is_tranStatus.isEmpty()) {
          tranInTaillast.setEmpId(empId);
          TranInHead temp2_tranInHead=(TranInHead) is_tranStatus.get(0);
          tranInTaillast.setTranInHead(temp2_tranInHead);
          BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
          BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//        为扎帐准备数据
          tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
          tranInTaillast.setPreIntegral(preIntegral);
          tranInTaillast.setCurIntegral(curIntegral);
          //查看是否在本单位下
          List emplist2 = this.getEmp_sy(inOrgId, tranInTailName,tranInTailCardNum);
          if(!emplist2.isEmpty()){
          tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
          }
          tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
          this.insertTranInTail(tranInTaillast);
          this.insertHafOperateLog_sy(inOrgId, temp2_tranInHead.getId().toString(),securityInfo);
//          this.insertTranInBizActivityLog(temp2_tranInHead.getId().toString(), securityInfo.getUserInfo().getUsername(), "1");
          return info = "showTraninListAC";
        } else {
          // 新的第一次插入
         
          TranInHead tranInHeadNew2 = new TranInHead();
          tranInTaillast.setEmpId(empId);
          tranInHeadNew2.setNoteNum(noteNum);
          if(inOrgId!=null&&!inOrgId.equals("")){
          TranInOrg tranInOrg = this.queryTranInOrg(new Integer(inOrgId));
          tranInHeadNew2.setTranInOrg(tranInOrg);
          }
          tranInHeadNew2.setTranStatus(new BigDecimal(1.00));
//          tranInHeadNew2.setSettDate(securityInfo.getUserInfo().getBizDate());
          Integer id=new Integer (this.insertTranInHead(tranInHeadNew2).toString());
          if(id!=null&&!id.equals("")){
            TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(id.toString(),new Integer(1),"F");
            //如果同一BIZID同一业务类型已经在319中存在就不在插入了
            if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
              throw new BusinessException("此操作已经进行请核实");
            }
            }
          tranInTaillast.setTranInHead(tranInHeadNew2);
          BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
          BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//        为扎帐准备数据
          tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
          tranInTaillast.setPreIntegral(preIntegral);
          tranInTaillast.setCurIntegral(curIntegral);
          //查看是否在本单位下
          List emplist2 = this.getEmp_sy(inOrgId, tranInTailName,tranInTailCardNum);
          if(!emplist2.isEmpty()){
          tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
          }
          tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
          tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
          tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
          this.insertTranInTail(tranInTaillast);
          this.insertHafOperateLog_sy(inOrgId, id.toString(),securityInfo);
          this.insertTranInBizActivityLog(id.toString(), securityInfo.getUserInfo().getUsername(), "1");
          return info = "showTraninListAC";
        }
      }  //判断有一个是本单位下的相同的姓名和身份证一个不是本单位下的姓名身份证相同的员工
        else if (emplist.size() > 1 ) {
          //如果仅存在一个在本单位下的直接取empid添加合并id
          List emplist2 = this.getEmp_sy(inOrgId, tranInTailName,tranInTailCardNum);
         if(emplist2.size()==1){
           Emp emp1 = (Emp) emplist2.get(0);
           Integer empId = emp1.getEmpId();
           tranInTaillast.setEmpId(empId);
           List is_tranStatus = this.queryTranInHeandInOrgId(inOrgId, "1");
           if (is_tranStatus != null && !is_tranStatus.isEmpty()) {
             tranInTaillast.setEmpId(empId);
             TranInHead temp2_tranInHead=(TranInHead) is_tranStatus.get(0);
             tranInTaillast.setTranInHead(temp2_tranInHead);
             BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
             BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//           为扎帐准备数据
             tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
             tranInTaillast.setPreIntegral(preIntegral);
             tranInTaillast.setCurIntegral(curIntegral);
             //查看是否在本单位下
             tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
             tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
             this.insertTranInTail(tranInTaillast);
             this.insertHafOperateLog_sy(inOrgId, temp2_tranInHead.getId().toString(),securityInfo);
             return info = "showTraninListAC";
           } else {
             // 新的第一次插入
            
             TranInHead tranInHeadNew2 = new TranInHead();
             tranInTaillast.setEmpId(empId);
             tranInHeadNew2.setNoteNum(noteNum);
             if(inOrgId!=null&&!inOrgId.equals("")){
             TranInOrg tranInOrg = this.queryTranInOrg(new Integer(inOrgId));
             tranInHeadNew2.setTranInOrg(tranInOrg);
             }
             tranInHeadNew2.setTranStatus(new BigDecimal(1.00));
//             tranInHeadNew2.setSettDate(securityInfo.getUserInfo().getBizDate());
             Integer id=new Integer(this.insertTranInHead(tranInHeadNew2).toString());
             if(id!=null){
               TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(id.toString(),new Integer(1),"F");
               //如果同一BIZID同一业务类型已经在319中存在就不在插入了
               if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
                 throw new BusinessException("此操作已经进行请核实");
               }
               }
             tranInTaillast.setTranInHead(tranInHeadNew2);
             BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
             BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//           为扎帐准备数据
             tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
             tranInTaillast.setPreIntegral(preIntegral);
             tranInTaillast.setCurIntegral(curIntegral);
             tranInTaillast.setMergerEmpid(new BigDecimal(empId.toString()));
             tranInTaillast.setCurIntegralSubA(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubB(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubC(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubA(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubB(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubC(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubD(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubE(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubF(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubD(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubE(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubF(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubG(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubH(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubI(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubG(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubH(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubI(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubJ(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubK(new BigDecimal(0.00));
             tranInTaillast.setCurIntegralSubL(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubJ(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubK(new BigDecimal(0.00));
             tranInTaillast.setPreIntegralSubL(new BigDecimal(0.00));
             this.insertTranInTail(tranInTaillast);
             this.insertHafOperateLog_sy(inOrgId, id.toString(),securityInfo);
             this.insertTranInBizActivityLog(id.toString(), securityInfo.getUserInfo().getUsername(), "1");
             return info = "showTraninListAC";
           }
         
         }else{
           //跳到页面共其选取
           if(emplist2.size()==2){
             return info = "sameEmployeeMaintainAC";
           }else{
           return info = "employeeMaintainAC";
           }          
           }

        }
//        else if(emplist.size() > 2){
//          // 有多个相同的empid
//          return info = "employeeMaintainAC";
//        }
      }
    }catch(BusinessException bx){
      throw bx;
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    return info;
  }

  // 查询emp
  public List queryEmp_sy(String empName, String cardNum) {
    String inorgId = null;
    Emp emp = null;
    List returnList = new ArrayList();
    try {
      List empList = this.getEmp_sy(inorgId, empName, cardNum);
      for (int i = 0; i < empList.size(); i++) {
        emp = (Emp) empList.get(i);
        BigDecimal temp_Balance = emp.getCurBalance().add(emp.getPreBalance());
        emp.setTEMP_salaryBase(temp_Balance);
        returnList.add(emp);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }
  // 查询emp本单位下有两个
  public List querySameCompanyEmp_sy(String inOrgId,String empName, String cardNum) {
    Emp emp = null;
    List returnList = new ArrayList();
    try {
      List empList = this.getEmp_sy(inOrgId, empName, cardNum);
      for (int i = 0; i < empList.size(); i++) {
        emp = (Emp) empList.get(i);
        BigDecimal temp_Balance = emp.getCurBalance().add(emp.getPreBalance());
        emp.setTEMP_salaryBase(temp_Balance);
        returnList.add(emp);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }
  // 插入BA003
  public void insertHafOperateLog_sy(String orgId, String tranInHeadId,SecurityInfo securityInfo) {
    String operator=securityInfo.getUserInfo().getUsername();
    String op_ip=securityInfo.getUserInfo().getUserIp();
  
    try {
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(
          BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN).toString());
      hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_ADD)
          .toString());
      hafOperateLog.setOpBizId(new Integer(tranInHeadId));
      hafOperateLog.setOperator(operator);
      hafOperateLog.setOpIp(op_ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId));
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 添加相同身份证和姓名的转入emp
  public void addTranInTail2_sy(String inOrgId, String noteNum,
      TranInTail tranInTail1, String id,SecurityInfo securityInfo) {
    try {
      TranInTail tranInTaillast = tranInTail1;
      // 判断职工的证件号码
      if (tranInTail1.getCardKind().toString().equals("0")&&tranInTail1.getCardNum().length()==15) {
        String num_to18 = empInfoDAO.queryCardNumTo18(tranInTail1.getCardNum());
        tranInTail1.setStandbyCardNum(num_to18);
      }else{
        tranInTail1.setStandbyCardNum("xxxxxxxxxxxxxxxxxx");
      }
      String tranInHeadId = "";
          // 有状态为1的
          List is_tranStatus = this.queryTranInHeandInOrgId(inOrgId, "1");
          if (is_tranStatus != null && !is_tranStatus.isEmpty()) {
            TranInHead tranInHead = (TranInHead) is_tranStatus.get(0);
            tranInHeadId = tranInHead.getId().toString();
            Emp emp = empDAO.queryById(new Integer(id));
            String orgid = emp.getOrg().getId().toString();
            if (inOrgId.equals(orgid)) {
              tranInTaillast.setMergerEmpid(new BigDecimal(emp.getEmpId().toString()));
            }
            BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
            BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//          为扎帐准备数据
            tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
            tranInTaillast.setPreIntegral(preIntegral);
            tranInTaillast.setCurIntegral(curIntegral);
            tranInTaillast.setEmpId(emp.getEmpId());
            tranInTaillast.setTranInHead(tranInHead);
            this.insertTranInTail(tranInTaillast);
            this.insertHafOperateLog_sy(inOrgId, tranInHeadId,securityInfo);
//            this.insertTranInBizActivityLog(tranInHeadId, securityInfo.getUserInfo().getUsername(), "1");
          } else {
            TranInOrg tranInOrg = this.queryTranInOrg(new Integer(inOrgId));
            TranInHead tranInHead = new TranInHead();
            tranInTaillast.setEmpId(new Integer(id));
            tranInHead.setNoteNum(noteNum);
//            tranInHead.setSettDate(securityInfo.getUserInfo().getBizDate());
            tranInHead.setTranInOrg(tranInOrg);
            tranInHead.setTranStatus(new BigDecimal(1.00));
            Integer newTranInHeadId=new Integer(this.insertTranInHead(tranInHead).toString());
            if(newTranInHeadId!=null){
              TranInBizActivityLog  tranInBizActivityLog=tranInBizActivityLogDAO.queryByBizId(newTranInHeadId.toString(),new Integer(1),"F");
              //如果同一BIZID同一业务类型已经在319中存在就不在插入了
              if(tranInBizActivityLog!=null&&tranInBizActivityLog.equals("")){
                throw new BusinessException("此操作已经进行请核实");
              }
              }
            Emp emp = empDAO.queryById(new Integer(id));
            String orgid = emp.getOrg().getId().toString();
            if (inOrgId.equals(orgid)) {
              tranInTaillast.setMergerEmpid(new BigDecimal(emp.getEmpId().toString()));
            }
            tranInTaillast.setEmpId(emp.getEmpId());
            BigDecimal preIntegral=this.getpreInt(tranInTaillast.getPreBalance(),securityInfo.getUserInfo().getBizDate());
            BigDecimal curIntegral=this.getCurInt(tranInTaillast.getCurBalance(),securityInfo.getUserInfo().getBizDate());
//          为扎帐准备数据
            tranInTaillast.setOpenDate(securityInfo.getUserInfo().getBizDate());
            tranInTaillast.setPreIntegral(preIntegral);
            tranInTaillast.setCurIntegral(curIntegral);
            tranInTaillast.setTranInHead(tranInHead);
            this.insertTranInTail(tranInTaillast);
            this.insertHafOperateLog_sy(inOrgId, newTranInHeadId.toString(),securityInfo);
            this.insertTranInBizActivityLog(newTranInHeadId.toString(), securityInfo.getUserInfo().getUsername(), "1");
          }
//        }
//      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  /**
   * 本年积数
   */
  public BigDecimal getCurInt(BigDecimal curBalance,String moneyDate){
    BigDecimal preInt=new BigDecimal(0.00);
    try{
      final int number = tranInTailDAO.getDay(moneyDate);
      preInt=curBalance.multiply(new BigDecimal(number+""));
    }catch(Exception s){
      s.printStackTrace();
    }
    return preInt;
  }
  /**
   * 往年积数
   */
  public BigDecimal getpreInt(BigDecimal preBalance,String moneyDate){
    BigDecimal preInt=new BigDecimal(0.00);
    try{
      final int number = tranInTailDAO.getDay(moneyDate);
      preInt=preBalance.multiply(new BigDecimal(number+""));
    }catch(Exception s){
      s.printStackTrace();
    }
    return preInt;
  }
  /** 
   *方法用于掉出相同的emp共选 
   */
  public List findEmployee(Pagination pagination){
    List emps = null;
   
    Serializable id = (Serializable) pagination.getQueryCriterions().get("id");
    String []status=(String[])pagination.getQueryCriterions().get("status");
    String cardnum = (String)pagination.getQueryCriterions().get("cardnum");
    String empname = (String)pagination.getQueryCriterions().get("empname");
//    System.out.println("woshishui"+pagination.getQueryCriterions().get("status"));
    String empid = (String)pagination.getQueryCriterions().get("empid");
    String name = (String)pagination.getQueryCriterions().get("name");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
   try{
    emps = empDAO.queryByCriterionsSL(id,name,empid,orderBy,order,start,pageSize,status,cardnum,empname);
    for(int i=0;i<emps.size();i++){
      Emp temp_emp=(Emp)emps.get(i);
      String empStatus=BusiTools.getBusiKey_WL(temp_emp.getPayStatus().toString(),BusiConst.CHGSTATUS);
      temp_emp.setEmployeeState(empStatus);
    }
    int count = empDAO.queryCountByCriterionsSL(id, name,empid,status,cardnum,empname);
    pagination.setNrOfElements(count);
   }catch(Exception e){
     e.printStackTrace();
   }
    return emps;
  }
  public void insertTranInBizActivityLog(String traninHeadId,String operator,String actionValue){
    // 插入AA319
    TranInBizActivityLog tranInBizActivityLog=new TranInBizActivityLog();
    tranInBizActivityLog.setBizid(new Integer(traninHeadId));
    tranInBizActivityLog.setAction(new Integer(actionValue));
    tranInBizActivityLog.setOpTime(new Date());
    tranInBizActivityLog.setOperator(operator);
    tranInBizActivityLogDAO.insert(tranInBizActivityLog);
  }
  //生成凭证号
  public String getDocNumdocNum(String officeCode, String bizYearmonth) throws Exception, BusinessException {
    String docNum=null;
    try{
       docNum=docNumMaintainDAO.getDocNumdocNum(officeCode, bizYearmonth);
   
      }catch(Exception e){
        e.printStackTrace();
        throw new BusinessException("生成凭证号失败!");
      }
    return docNum;
  }

  public TraninAF findTraninListByCriterionsAAC(Pagination pagination, SecurityInfo securityInfo){
    TraninAF traninAF = new TraninAF();
    List list = new ArrayList();

    String inOrgId = "";
    String tranInHeadId = (String) pagination.getQueryCriterions().get(
        "taninHeadIdAAC");
    String tranInTailName = "";
    String tranInTailCardNum = "";
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List tranInHeadList = new ArrayList();
    List returnlist = new ArrayList();
    String tranStatus = null;
    TranInHead tranInHead = null;
    String notenumber="";
    int page = pagination.getPage();
    try{
    tranInHead = (TranInHead)tranInHeadDAO.queryById(new Integer(tranInHeadId));
    notenumber=tranInHead.getNoteNum();
    traninAF.setNoteNum(notenumber);
    traninAF.setInOrgId(tranInHead.getTranInOrg().getId().toString());
    traninAF.setInOrgName(tranInHead.getTranInOrg().getOrgInfo().getName());
    traninAF.setDocNum(tranInHead.getDocNum());
    if(tranInHead.getTranOutOrgId()!=null&&!tranInHead.getTranOutOrgId().equals("")){
    TranOutOrg tranOutOrg=tranOutOrgDAO.queryById(new Integer(tranInHead.getTranOutOrgId()));
    traninAF.setOutOrgName(tranOutOrg.getOrgInfo().getName());
    traninAF.setOutOrgId(tranInHead.getTranOutOrgId());
    }
    traninAF.setTranInHeadById(tranInHeadId);
    pagination.getQueryCriterions().put("tranInHeadById", tranInHeadId);
    list = tranInTailDAO.queryTraninListByCriterions(tranInHeadId,
        tranInTailName, tranInTailCardNum, orderBy, order, start,
        pageSize,securityInfo,page);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        TranInTail tranInTail = (TranInTail) list.get(i);
        //2071211修改
        if(tranInHead.getTranOutOrgId()!=null&&!tranInHead.getTranOutOrgId().equals("")){
          if(!tranInTail.getMergerEmpid().toString().equals("0")){
            tranInTail.setTranOutEmpId(new Integer(tranInTail.getMergerEmpid().toString())); 
          }else{
            tranInTail.setTranOutEmpId(tranInTail.getEmpId()); 
          }
        }
        //20071211修改结束
        tranInTail.setSumBalance(tranInTail.getCurBalance(), tranInTail
            .getPreBalance());
        tranInTail.setSumInterest(tranInTail.getPreInterest(), tranInTail
            .getCurInterest());
        tranInTail.setTraninAmount(tranInTail.getSumBalance(), tranInTail
            .getSumInterest());
        returnlist.add(tranInTail);
      }
    }
    traninAF.setList(returnlist);
    int count=0;
    // 当有数据的时候统计项数
    if(!list.isEmpty()){
     count = this.countTraninListByCriterions(tranInHeadId,securityInfo);
    }
    pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return traninAF;
  }
  public List isCardNumSame(String empName, String cardNum)
      throws BusinessException, Exception {
    List list = empDAO.queryEmpInfoByTranin_FYF(empName, cardNum);
    return list;
  }
  public List isUpdateCardNumSame(String empName, String cardNum, String empId)
      throws BusinessException, Exception {
    List list = empDAO.queryEmpUpdateByTranin_FYF(empName, cardNum, empId);
    return list;
}

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
  }

  public void setTranInTailDAODW(TranInTailDAODW tranInTailDAODW) {
    this.tranInTailDAODW = tranInTailDAODW;
  }
  public void pickupDateAll(String inorgid,SecurityInfo securityInfo)throws BusinessException{
    List traninHeadImportList=new ArrayList();
    List traninTailImportList=new ArrayList();
    String org_head_id = autoInfoPickDAO.findOrgHeadid(inorgid, BusiConst.ORGBUSINESSTYPE_F, BusiConst.OC_NOT_PICK_UP);
    if(org_head_id!=null&&!org_head_id.equals("")){
    traninTailImportList=tranInTailDAODW.queryTranInTail_sy(new Integer(org_head_id));
    Org orgs = orgDAO.queryById(new Integer(inorgid));
    String inOrgName = orgs.getOrgInfo().getName();
    TraninImportHeadDTO traninImportHeadDTO=new TraninImportHeadDTO();
    traninImportHeadDTO.setInOrgId("");
    traninImportHeadDTO.setInOrgName("");
    traninHeadImportList.add(traninImportHeadDTO);
    traninImportHeadDTO.setInOrgId(inorgid);
    traninImportHeadDTO.setInOrgName(inOrgName);
    traninHeadImportList.add(traninImportHeadDTO);
    this.modifyTraninBatch_zl(traninHeadImportList, traninTailImportList, inorgid, securityInfo);
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog
    .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_PICKUPDATA);
    hafOperateLog.setOpBizId(new Integer(centerTranHeadId));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(inorgid));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
    try {
      autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP, centerTranHeadId, "newDate", inorgid, org_head_id,BusiConst.ORGBUSINESSTYPE_F);
    } catch (Exception e) {
      throw new BusinessException("数据有问题");
    }
    }else{
      throw new BusinessException("没有对应的单位库与之提取");
    }
   }
  //单位版的时候提交数据
  public void referringDataInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException{
    TranInHead  tranInHead = (TranInHead)tranInHeadDAO.queryById(new Integer(tranInHeadById));
    boolean status=autoInfoPickDAODW.isNOPickIn(tranInHead.getTranInOrg().getId().toString(),tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
   try{
    if(!status){
//    插入DA001
      AutoInfoPick autoInfoPick=new AutoInfoPick();
      autoInfoPick.setOrgId(new Integer(tranInHead.getTranInOrg().getId().toString()));
      autoInfoPick.setOrgHeadId(new Integer(tranInHeadById));
      autoInfoPick.setCenterHeadId(null);
      autoInfoPick.setPayHeadId(null);
      autoInfoPick.setCenterBizDate(null);
      autoInfoPick.setOrgBizDate(new Date());
      autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_F);
      autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
      autoInfoPickDAODW.insert(autoInfoPick);
      //插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
      .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_REFERRINGDATE);
      hafOperateLog.setOpBizId(new Integer(tranInHeadById));
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOrgId(new Integer(tranInHead.getTranInOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
   }else{
     throw new BusinessException("该笔业务已提交！");
     }
    }catch(BusinessException bx){
      throw bx;
    }
     catch(Exception e){
      e.printStackTrace();
    }
  }
  //单位版的时候撤销提交数据
  public void pprovalDataInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException{
    TranInHead  tranInHead = (TranInHead)tranInHeadDAO.queryById(new Integer(tranInHeadById));
    //判断DA001状态
    boolean status=autoInfoPickDAODW.isNOPickUp(tranInHead.getTranInOrg().getId().toString(),tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
    try{
      if(status){
        //未提取
        //删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(tranInHead.getTranInOrg().getId().toString(),tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
        //插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog
        .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN);
        hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_PPROVALDATA);
        hafOperateLog.setOpBizId(new Integer(tranInHeadById));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(tranInHead.getTranInOrg().getId().toString()));
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      }else{
        throw new BusinessException("该业务已被中心提取，不可以撤销提交数据!");
      }
    }catch(Exception e){
      throw new BusinessException("撤销数据失败!");
    }
  }
  //单位版的时候办理页面提交数据
  public void referringDataFirstInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException{
    TranInHead  tranInHead = (TranInHead)tranInHeadDAO.queryById(new Integer(tranInHeadById));
    boolean status=autoInfoPickDAODW.isNOPickIn(tranInHead.getTranInOrg().getId().toString(),tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
   try{
    if(!status){
//    插入DA001
      AutoInfoPick autoInfoPick=new AutoInfoPick();
      autoInfoPick.setOrgId(new Integer(tranInHead.getTranInOrg().getId().toString()));
      autoInfoPick.setOrgHeadId(new Integer(tranInHeadById));
      autoInfoPick.setCenterHeadId(null);
      autoInfoPick.setPayHeadId(null);
      autoInfoPick.setCenterBizDate(null);
      autoInfoPick.setOrgBizDate(new Date());
      autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_F);
      autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
      autoInfoPickDAODW.insert(autoInfoPick);
      //插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
      .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_REFERRINGDATE);
      hafOperateLog.setOpBizId(new Integer(tranInHeadById));
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOrgId(new Integer(tranInHead.getTranInOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
   }else{
     throw new BusinessException("该笔业务已提交！");
     }
    }catch(BusinessException bx){
      throw bx;
    }
     catch(Exception e){
      e.printStackTrace();
    }
  }
  //单位版的时候办理页面撤销提交数据
  public void pprovalDataFirstInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException{
    TranInHead  tranInHead = (TranInHead)tranInHeadDAO.queryById(new Integer(tranInHeadById));
    //判断DA001状态
    boolean status=autoInfoPickDAODW.isNOPickUp(tranInHead.getTranInOrg().getId().toString(),tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
    try{
      if(status){
        //未提取
        //删除DA001
        autoInfoPickDAODW.deleteAutoInfoPick(tranInHead.getTranInOrg().getId().toString(),tranInHeadById, BusiConst.ORGBUSINESSTYPE_F);
        //插入BA003
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog
        .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN);
        hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_PPROVALDATA);
        hafOperateLog.setOpBizId(new Integer(tranInHeadById));
        hafOperateLog.setOpIp(securityInfo.getUserIp());
        hafOperateLog.setOrgId(new Integer(tranInHead.getTranInOrg().getId().toString()));
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOperator(securityInfo.getUserName());
        hafOperateLogDAO.insert(hafOperateLog);
      }else{
        throw new BusinessException("该业务已被中心提取，不可以撤销提交数据!");
      }
    }catch(Exception e){
      throw new BusinessException("撤销数据失败!");
    }
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public TranTbPrintAF printCredence(String headid) throws BusinessException, Exception {
    TranTbPrintAF printAf = new TranTbPrintAF();
    TranInHead tranInHead = null;
    List list = new ArrayList();
    try{
      tranInHead = tranInHeadDAO.queryById(Integer.valueOf(headid));
      printAf.setDoc_num(tranInHead.getDocNum());
      if(tranInHead!=null){
        TranInOrg inOrg = tranInHead.getTranInOrg();
        if(inOrg!=null){
          printAf.setInOrgOpenBank(inOrg.getOrgInfo().getPayBank().getName());
          printAf.setInOrgId(inOrg.getId().toString());
          printAf.setInOrgName(inOrg.getOrgInfo().getName());
          if(inOrg.getOrgInfo().getPayBank()!=null)
            printAf.setInOrgOpenBank(inOrg.getOrgInfo().getPayBank().getName());
          if(inOrg.getOrgInfo().getPayBank().getAccountNum()!=null){
            printAf.setInPayBankAccNum(inOrg.getOrgInfo().getPayBank().getName());
          }
        }
        TranOutOrg tranOutOrg= tranOutOrgDAO.queryById(new Integer(tranInHead.getTranOutOrgId()));
        if(tranOutOrg!=null){
          if(tranOutOrg.getOrgInfo().getPayBank()!=null)
            printAf.setOutOrgOpenBank(tranOutOrg.getOrgInfo().getPayBank().getName());
          printAf.setOutOrgId(tranOutOrg.getId().toString());
          printAf.setOutOrgname(tranOutOrg.getOrgInfo().getName());
        }
        list = tranInTailDAO.fIndTailEmpInfoWS(headid);
        printAf.setList(list);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return printAf;
  }
  public org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printCredence_yg(String headid) throws BusinessException, Exception {
    org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printAf = new org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF();
    TranOutHead tranOutHead = null;
    List list = new ArrayList();
    try{
      tranOutHead = tranOutHeadDAO.queryById(Integer.valueOf(headid));
      printAf.setStartDate(tranOutHead.getSettDate());
      printAf.setNote_num(tranOutHead.getNoteNum());
      printAf.setDoc_num(tranOutHead.getDocNum());
      if(tranOutHead!=null){
        TranInOrg inOrg = tranOutHead.getTranInOrg();
        if(inOrg!=null){
          printAf.setInOrgId(inOrg.getId().toString());
          String inOrgCollBank = tranOutHeadDAO.FindBankName_yg(tranOutHead.getTranInOrg().getOrgInfo().getCollectionBankId());
          printAf.setInOrgCollBank(inOrgCollBank);
          printAf.setInOrgName(inOrg.getOrgInfo().getName());
          if(inOrg.getOrgInfo().getPayBank()!=null){
            printAf.setInOrgOpenBank(inOrg.getOrgInfo().getPayBank().getName());
            printAf.setInPayBankAccNum(inOrg.getOrgInfo().getPayBank().getAccountNum());
          }else{
            printAf.setInOrgOpenBank("");
            printAf.setInPayBankAccNum("");
          }
        }
        String outOrgId = tranOutHead.getTranOutOrg().getId().toString();
        if(outOrgId!=null&&!"".equals(outOrgId)){
          String outOrgCollBank = tranOutHeadDAO.FindBankName_yg(tranOutHead.getTranOutOrg().getOrgInfo().getCollectionBankId());
          printAf.setOutOrgCollBank(outOrgCollBank);
          TranOutOrg tranOutOrg = tranOutOrgDAO.queryById(new Integer(outOrgId));
          if(tranOutOrg.getOrgInfo().getPayBank()!=null){
            printAf.setOutOrgOpenBank(tranOutOrg.getOrgInfo().getPayBank().getName());
            printAf.setOutPayBankAccNum(tranOutOrg.getOrgInfo().getPayBank().getAccountNum());
          }else{
            printAf.setOutOrgOpenBank("");
            printAf.setOutPayBankAccNum("");
          }
          printAf.setOutOrgId(outOrgId);
          printAf.setOutOrgname(tranOutOrg.getOrgInfo().getName());
        }
        list = tranOutTailDAO.fIndTailEmpInfoWZQ(headid);
        for(int i=0;i<list.size();i++){
          TranOutTail tranOutTail = (TranOutTail)list.get(i);
          Emp emp=empDAO.queryByCriterions(tranOutTail.getEmpId().toString(),
              tranOutTail.getTranOutHead().getTranOutOrg().getId().toString());
          if(tranOutTail.getTranin_empid()==null){
            tranOutTail.setTranin_empid(new Integer(0));
          }
          if(tranOutTail.getTranReason()==null){
            tranOutTail.setTranReason("");
          }
          tranOutTail.setEmp(emp);
        }
        printAf.setList(list);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return printAf;
  }
  public org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printCredence_yga(String headid) throws BusinessException, Exception {
    org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printAf = new org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF();
    TranInHead tranInHead = null;
    List list = new ArrayList();
    try{
      tranInHead = tranInHeadDAO.queryById(Integer.valueOf(headid));
      printAf.setStartDate(tranInHead.getSettDate());
      printAf.setNote_num(tranInHead.getNoteNum());
      printAf.setDoc_num(tranInHead.getDocNum());
      if(tranInHead!=null){
        TranInOrg inOrg = tranInHead.getTranInOrg();
        if(inOrg!=null){
          printAf.setInOrgId(inOrg.getId().toString());
          String inOrgCollBank = tranOutHeadDAO.FindBankName_yg(tranInHead.getTranInOrg().getOrgInfo().getCollectionBankId());
          printAf.setInOrgCollBank(inOrgCollBank);
          printAf.setInOrgName(inOrg.getOrgInfo().getName());
          if(inOrg.getOrgInfo().getPayBank()!=null){
            printAf.setInOrgOpenBank(inOrg.getOrgInfo().getPayBank().getName());
            printAf.setInPayBankAccNum(inOrg.getOrgInfo().getPayBank().getAccountNum());
          }else{
            printAf.setInOrgOpenBank("");
            printAf.setInPayBankAccNum("");
          }
        }
        if(tranInHead.getTranOutOrg().getId()!=null&&!"".equals(tranInHead.getTranOutOrg().getId())){
          String outOrgId = tranInHead.getTranOutOrg().getId().toString();
          String outOrgCollBank = tranOutHeadDAO.FindBankName_yg(tranInHead.getTranOutOrg().getOrgInfo().getCollectionBankId());
          printAf.setOutOrgCollBank(outOrgCollBank);
          TranOutOrg tranOutOrg = tranOutOrgDAO.queryById(new Integer(outOrgId));
          if(tranOutOrg.getOrgInfo().getPayBank()!=null){
            printAf.setOutOrgOpenBank(tranOutOrg.getOrgInfo().getPayBank().getName());
            printAf.setOutPayBankAccNum(tranOutOrg.getOrgInfo().getPayBank().getAccountNum());
          }else{
            printAf.setOutOrgOpenBank("");
            printAf.setOutPayBankAccNum("");
          }
          printAf.setOutOrgId(outOrgId);
          printAf.setOutOrgname(tranOutOrg.getOrgInfo().getName());
        }
        list = tranInTailDAO.queryTraninListByCriterionsAll_sy_yg(headid);
//        for(int i=0;i<list.size();i++){
//          TranInTail tranInTail = (TranInTail)list.get(i);
//          Emp emp=new Emp();
//          emp.setEmpId(tranInTail.getEmpId());
//          emp.getEmpInfo().setName(tranInTail.getName());
//          emp.getEmpInfo().setCardKind(new BigDecimal(tranInTail.getCardKind().toString()));
//          emp.getEmpInfo().setCardNum(tranInTail.getCardNum());
//          emp.setPreBalance(tranInTail.getPreBalance());
//          emp.setCurBalance(tranInTail.getCurBalance());
//          emp.setPreIntegral(tranInTail.getPreIntegral());
//          emp.setCurIntegral(tranInTail.getCurIntegral());
//          tranInTail.setEmp(emp);
//        }
        printAf.setList(list);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return printAf;
  }
  /**
   * @author wangshuang
   * 获得批量打印的信息
   */
  public List printAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception {
    List list = new ArrayList();
//    int start = pagination.getFirstElementOnPage() - 1;
//    int pageSize = pagination.getPageSize();
//    String orderBy = (String) pagination.getOrderBy();
//    String order = pagination.getOrderother();
    String inOrgId = (String) pagination.getQueryCriterions().get("inOrgId");
    String outOrgName = (String) pagination.getQueryCriterions().get(
        "outOrgName");
    String inOrgName = (String) pagination.getQueryCriterions()
        .get("inOrgName");
    String outOrgId = (String) pagination.getQueryCriterions().get("outOrgId");
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String settDate = (String) pagination.getQueryCriterions().get("settDate");
    String tranStatus = (String) pagination.getQueryCriterions().get(
        "tranStatus");
//    int page = pagination.getPage();
    try{
      List totalList = tranInHeadDAO.countTranInListByCriterionsAll_sy(settDate,
          tranStatus, inOrgId, outOrgId, outOrgName, inOrgName, noteNum, docNum,securityInfo);
      for (int i = 0; i < totalList.size(); i++) {
        TranInHead head = (TranInHead)totalList.get(i);
        TranTbPrintAF af = this.printCredence(head.getId().toString());
        String [] str_in = 
          this.queryOfficeBankNames(af.getInOrgId(), "2", head.getId().toString(), "E", securityInfo);
        af.setInOrgCollBank(str_in[1]);
        String [] str_out = 
          this.queryOfficeBankNames(af.getOutOrgId(), "2", head.getId().toString(), "E", securityInfo);
        af.setOutOrgCollBank(str_out[1]);
        list.add(af);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public List tranoutTailReason(String tranOutHeadId) throws BusinessException, Exception {
//    TranOutHead tranOutHead=tranOutHeadDAO.queryById(new Integer(tranheadid));
    List list=tranOutTailDAO.queryTranOutTail_yg(tranOutHeadId);
    return list;
  }
  public String FindAA103_DayTime(String collbankid) throws Exception {
    String date = tranOutHeadDAO.findAA103_DayTime(collbankid);
    return date;
  }
  public String queryNoteNum() throws Exception {
    String notenum = orgDAO.queryNoteNum();
    return notenum;
  }

  public TranInHead queryTranInHead(String tranInHeadId) throws Exception, BusinessException {
    TranInHead tranInHead = tranInHeadDAO.queryById(new Integer(tranInHeadId));
    return tranInHead;
  }
}
