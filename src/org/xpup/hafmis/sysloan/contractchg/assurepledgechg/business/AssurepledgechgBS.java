package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.AssurerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractChgDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.ImpawnContractDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.common.domain.entity.Assurer;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.CongealInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractChg;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.ImpawnContract;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.bsinterface.IAssurepledgechgBS;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.dto.AssurepledgechgTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
/**
 * 
 * @author yuqf
 *2007-10-08
 */
public class AssurepledgechgBS implements IAssurepledgechgBS{
  
  private BorrowerAccDAO borrowerAccDAO = null;// PL111
  
  private CollBankDAO collBankDAO = null;// BB105
  
  private PledgeContractDAO pledgeContractDAO = null;// pl121
  
  private HousesDAO housesDAO = null;// pl114 购房信息

  private AssistantOrgDAO assistantOrgDAO = null;// pl007 担保公司名称
  
  private PlOperateLogDAO plOperateLogDAO = null;//业务日志
  
  private ContractChgDAO contractChgDAO = null;//pl211 合同变更信息表
  
  private ImpawnContractDAO impawnContractDAO = null;
  
  private AssurerDAO assurerDAO = null;// PL123
  
  private CongealInfoDAO congealInfoDAO = null;//pl210 冻结表
   
  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }

  public void setAssurerDAO(AssurerDAO assurerDAO) {
    this.assurerDAO = assurerDAO;
  }

  public void setImpawnContractDAO(ImpawnContractDAO impawnContractDAO) {
    this.impawnContractDAO = impawnContractDAO;
  }

  public void setContractChgDAO(ContractChgDAO contractChgDAO) {
    this.contractChgDAO = contractChgDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * 默认查询PL111 查询 合同编号 借款人姓名  借款金额 借款起始日期  借款期限  借款终止日期  借款每月利率  月还本息 放款银行 查看扫描信息 
   */
  public List defaultQueryBorrowerAccList(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;  
    int pageSize = pagination.getPageSize();
    int count=0;
    int term = 0;
    String contractId = (String)pagination.getQueryCriterions().get("contractId"); //合同ID
    String debitter = (String)pagination.getQueryCriterions().get("debitter");//借款人姓名 PL110 
    String empId = (String)pagination.getQueryCriterions().get("empId");//职工编号
    String cardNum = (String)pagination.getQueryCriterions().get("cardNum");//证件号码
    String houseType = (String)pagination.getQueryCriterions().get("houseType");//购房类型
    try{
      list = borrowerAccDAO.queryBorrowerAccListByConditionYU(contractId, debitter, empId, cardNum, houseType, orderBy, order, start, pageSize,securityInfo);
      count = borrowerAccDAO.queryBorrowerAccListCountByConditionYU(contractId, debitter, empId, cardNum, houseType, orderBy, order, start, pageSize,securityInfo);
      if(list.size() != 0){
        for(int i=0;i<list.size();i++){
          AssurepledgechgTaDTO assurepledgechgTaDTO=new AssurepledgechgTaDTO();
          assurepledgechgTaDTO = (AssurepledgechgTaDTO)list.get(i);
          String tempBankId = assurepledgechgTaDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(tempBankId);//通过银行ID查询放款银行名称
          assurepledgechgTaDTO.setBank(collBank.getCollBankName());
          //通过借款开始时间和借款期限计算借款结束时间
          String debitMoneyStaDate = assurepledgechgTaDTO.getStartDate();
          if(assurepledgechgTaDTO.getLoanTimeLimit()!=null && !"".equals(assurepledgechgTaDTO.getLoanTimeLimit())){
           term = new Integer(assurepledgechgTaDTO.getLoanTimeLimit()).intValue();
          }
          String endDate = BusiTools.addMonth(debitMoneyStaDate, term);
          assurepledgechgTaDTO.setEndDate(endDate+debitMoneyStaDate.substring(6, 8));
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    pagination.setNrOfElements(count);
    return list;
  }

  /**
   * Tb 确定按钮 插入PL121 更新PL111 PL111.CONTRACT_ST=7
   */
  public void addPledgeContract(String pkId,SecurityInfo securityInfo, EndorsecontractTbAF endorsecontractTbAF,HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    
     String contractId = endorsecontractTbAF.getContractId();//合同ID
     String debitter = endorsecontractTbAF.getDebitter();//借款人姓名 PL110 
     String pledgePerson = endorsecontractTbAF.getPledgePerson();//抵押人姓名
     String office = endorsecontractTbAF.getOffice();//抵押权人（即××中心）
     String pledgeContractId = endorsecontractTbAF.getPledgeContractId();//抵押合同编号
     String assistantOrgName = endorsecontractTbAF.getAssistantOrgName();//担保公司名称
     String pledgeMatterName = endorsecontractTbAF.getPledgeMatterName();//抵押物名称
     String paperNum = endorsecontractTbAF.getPaperNum();//所有权证编号
     String paperName = endorsecontractTbAF.getPaperName();//所有权证名称
     String paperPersonName = endorsecontractTbAF.getPaperPersonName();//所有权人姓名
     String cardKind = endorsecontractTbAF.getCardKind();//所有权人证件类型
     String carNum = endorsecontractTbAF.getCarNum();//所有权人证件号码
     String tel = endorsecontractTbAF.getTel();//所有权人固定电话
     String mobile = endorsecontractTbAF.getMobile();//所有权人移动电话
     String pledgeAddr = endorsecontractTbAF.getPledgeAddr();//抵押物地址
     String area = endorsecontractTbAF.getArea();//建筑面积
     String buyHouseContractId = endorsecontractTbAF.getBuyHouseContractId();//购房合同编号
     String pledgeValue = endorsecontractTbAF.getPledgeValue();//抵押值
     String evaluateValue = endorsecontractTbAF.getEvaluateValue();//评估值
     PledgeContract pledgeContract = null;
     String operator = securityInfo.getUserName();
     String opIp = securityInfo.getUserIp();
     ContractChg contractChg1 = null;
     ContractChg contractChg2 = null;
     ContractChg contractChg3 = null;
     if(pkId != null && !"".equals(pkId)){//
       pledgeContract = pledgeContractDAO.queryById(new Integer(pkId));// 主键
       if (pledgeContract != null) {// 存在：更新
         //获得更新前的数据
         EndorsecontractTbAF preEndorsecontractTbAF = (EndorsecontractTbAF)request.getSession().getAttribute("theEndorsecontractTbAF");//theEndorsecontractTbAF
         String prePaperNum = preEndorsecontractTbAF.getPaperNum();//更新前所有权证编号
         if(prePaperNum==null){
           prePaperNum="";
         }
         String preTel = preEndorsecontractTbAF.getTel();//更新前电话
         if(preTel==null){
           preTel="";
         }
         String preMobile = preEndorsecontractTbAF.getMobile();//更新前移动电话
         if(preMobile==null){
           preMobile="";
         }
//         if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
//           // 更新购房合同编号
//           Houses houses = housesDAO.queryById(contractId);
//           houses.setBuyHouseContractId(buyHouseContractId);
//         }
//         if (assistantOrgName != null && !"".equals(assistantOrgName)) {
//           // 更新担保公司
//           String assistantOrgId = assistantOrgDAO.queryId(contractId);
//           if(assistantOrgId != null && !"".equals(assistantOrgId)){
//           AssistantOrg assistantOrg = assistantOrgDAO.queryById(new Integer(
//               assistantOrgId));
//           assistantOrg.setAssistantOrgName(assistantOrgName);
//           }
//         }
//         if (contractId != null && !"".equals(contractId)) {
//           pledgeContract.setContractId(contractId);
//         }
//         if (pledgeContractId != null && !"".equals(pledgeContractId)) {
//           pledgeContract.setPledgeContractId(pledgeContractId);
//         }
//         if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
//           pledgeContract.setPledgeMatterName(pledgeMatterName);
//         }
//         if (pledgeValue != null && !"".equals(pledgeValue)) {
//           pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
//         }
           pledgeContract.setPaperNum(paperNum);
           
           //插入pl211 合同变更信息表
           if(!paperNum.equals(prePaperNum)){
           contractChg1 = new ContractChg();
           contractChg1.setContractId(contractId);//合同编号
           contractChg1.setChgColumn("所有权证编号");//修改字段
           contractChg1.setChgBefInfo(prePaperNum);//修改前信息
           contractChg1.setChgEndInfo(paperNum);//修改后信息
           contractChg1.setOpTime(new Date());//操作时间
           contractChg1.setOperator(operator);
           String contractType = BusiConst.PLPREPAYMENTFEES_PLEDGEINFO + "";
           contractChg1.setContractType(contractType);//修改合同类型＝4 抵押合同信息
           contractChgDAO.insert(contractChg1);
           }
       
//         if (paperName != null && !"".equals(paperName)) {
//           pledgeContract.setPaperName(paperName);
//         }
//         if (paperPersonName != null && !"".equals(paperPersonName)) {
//           pledgeContract.setName(paperPersonName);
//         }
//         if (cardKind != null && !"".equals(cardKind)) {
//           pledgeContract.setCardKind(cardKind);
//         }
//         if (carNum != null && !"".equals(carNum)) {
//           pledgeContract.setCardNum(carNum);
//         }
     
           pledgeContract.setTel(tel);
           if(!tel.equals(preTel)){
           contractChg2 = new ContractChg();
           contractChg2.setContractId(contractId);//合同编号
           contractChg2.setChgColumn("所有权人固定电话");//修改字段
           contractChg2.setChgBefInfo(preTel);//修改前信
        
           contractChg2.setChgEndInfo(tel);//修改后信息

           contractChg2.setOpTime(new Date());//操作时间
           contractChg2.setOperator(operator);
           String contractType = BusiConst.PLPREPAYMENTFEES_PLEDGEINFO + "";
           contractChg2.setContractType(contractType);//修改合同类型＝4 抵押合同信息
           contractChgDAO.insert(contractChg2);
           }
    
    
           pledgeContract.setMobile(mobile);
           if(!mobile.equals(preMobile)){
           contractChg3 = new ContractChg();
           contractChg3.setContractId(contractId);//合同编号
           contractChg3.setChgColumn("所有权人移动电话");//修改字段
          
           contractChg3.setChgBefInfo(preMobile);//修改前信息
         
             contractChg3.setChgEndInfo(mobile);//修改后信息
 
           contractChg3.setOpTime(new Date());//操作时间
           contractChg3.setOperator(operator);
           String contractType = BusiConst.PLPREPAYMENTFEES_PLEDGEINFO + "";
           contractChg3.setContractType(contractType);//修改合同类型＝4 抵押合同信息
           contractChgDAO.insert(contractChg3);
           }
   
//         if (pledgeAddr != null && !"".equals(pledgeAddr)) {
//           pledgeContract.setPledgeAddr(pledgeAddr);
//         }
//         if (area != null && !"".equals(area)) {
//           pledgeContract.setArea(new BigDecimal(area));
//         }
//         if (evaluateValue != null && !"".equals(evaluateValue)) {
//           pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
//         }
        
      
         String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
         String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT + "";
         String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
         String bizId = pledgeContract.getId().toString();
         this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
       }
     }else{//不存在，插入一条新数据
      if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
       // 插入购房合同编号
       Houses houses = housesDAO.queryById(contractId);
       houses.setBuyHouseContractId(buyHouseContractId);
//       housesDAO.insert(houses);
     }
     // 插入担保公司
     if (assistantOrgName != null && !"".equals(assistantOrgName)) {
       AssistantOrg assistantOrg = new AssistantOrg();
       assistantOrg.setAssistantOrgName(assistantOrgName);
       assistantOrgDAO.insert(assistantOrg);
     }
     pledgeContract = new PledgeContract();
     if (contractId != null && !"".equals(contractId)) {
       pledgeContract.setContractId(contractId);
     }
     if (pledgeContractId != null && !"".equals(pledgeContractId)) {
       pledgeContract.setPledgeContractId(pledgeContractId);
     }
     if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
       pledgeContract.setPledgeMatterName(pledgeMatterName);
     }
     if (pledgeValue != null && !"".equals(pledgeValue)) {
       pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
     }
     if (paperNum != null && !"".equals(paperNum)) {
       pledgeContract.setPaperNum(paperNum);
     }
     if (paperName != null && !"".equals(paperName)) {
       pledgeContract.setPaperName(paperName);
     }
     if (paperPersonName != null && !"".equals(paperPersonName)) {
       pledgeContract.setName(paperPersonName);
     }
     if (cardKind != null && !"".equals(cardKind)) {
       pledgeContract.setCardKind(cardKind);
     }
     if (carNum != null && !"".equals(carNum)) {
       pledgeContract.setCardNum(carNum);
     }
     if (tel != null && !"".equals(tel)) {
       pledgeContract.setTel(tel);
     }
     if (mobile != null && !"".equals(mobile)) {
       pledgeContract.setMobile(mobile);
     }
     if (pledgeAddr != null && !"".equals(pledgeAddr)) {
       pledgeContract.setPledgeAddr(pledgeAddr);
     }
     if (area != null && !"".equals(area)) {
       pledgeContract.setArea(new BigDecimal(area));
     }
     if (evaluateValue != null && !"".equals(evaluateValue)) {
       pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
     }
     pledgeContract.setStatus("0");
     String pContractId = pledgeContractDAO.insert(pledgeContract).toString();
     request.getSession().setAttribute("pl121Id", pContractId);
     String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
     String bizId = pContractId;
    
     String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
     String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT + "";
     
     this.updatePl111(contractId);
     
     this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
     }
  }
  //更新PL111
  public void updatePl111(String id){
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(id);//更新pl111 合同状态＝7;
    borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_SECAUDIT+"");
  }
  /**
   * 插入操作日志pl201
   * 
   * @param securityInfo
   *
   * @return
   */
  public void addPlOperateLog(String opSys, String model, String button,
      String bizId, String opIp, String operator, String opBizId,String contractId) {
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(opSys));
    plOperateLog.setOpModel(model);
    plOperateLog.setOpButton(button);
    if (opBizId != null && !"".equals(opBizId)) {
      plOperateLog.setOpBizId(new BigDecimal(opBizId));
    }
    plOperateLog.setOpIp(opIp);
    plOperateLog.setContractId(bizId);
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(operator);
    plOperateLog.setContractId(contractId);
    plOperateLogDAO.insert(plOperateLog);
  }
/**
 * TB 分发－－－作废按钮
 */
  public void deletePledgeContract(String id, Pagination pagination, SecurityInfo securityInfo, HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    PledgeContract pledgeContract = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT + "";
    if (id != null && !"".equals(id)) {
      pledgeContract = pledgeContractDAO.queryById(new Integer(id));
      String bizId = pledgeContract.getId().toString();
      String contractId = pledgeContract.getContractId();
      String status = pledgeContract.getStatus();
      if (status.equals("0")) {
        pledgeContract.setStatus("1");//更新状态
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(pledgeContract.getContractId());//更新pl111 合同状态＝7;
        borrowerAcc.setContractSt("7");
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
            bizId,contractId);
      } else {
        throw new BusinessException("该纪录已作废！");
      }
    }
  }
/**
 * TC 分发--确定按钮
 */
public void addImpawnContract(String pkId, SecurityInfo securityInfo, EndorsecontractTcAF endorsecontractTcAF, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
//根据ID（pl121Id），查询PL121
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT + "";

  String contractId = endorsecontractTcAF.getContractId();// 合同ID
  String debitter = endorsecontractTcAF.getDebitter();// 借款人姓名 PL110
  String impawnContractId = endorsecontractTcAF.getImpawnContractId();// 质押合同编号
  String assistantOrgName = endorsecontractTcAF.getAssistantOrgName();// 担保公司名称
  String impawnPerson = endorsecontractTcAF.getImpawnPerson();// 质押人
  String office = endorsecontractTcAF.getOffice();// 质押权人（即××中心）
  String impawnMatterName = endorsecontractTcAF.getImpawnMatterName();// 质押物名称
  String impawnValue = endorsecontractTcAF.getImpawnValue();// 质押物价值
  String paperPersonName = endorsecontractTcAF.getPaperPersonName();// 所有权人姓名
  String cardKind = endorsecontractTcAF.getCardKind();// 所有权人证件类型
  String carNum = endorsecontractTcAF.getCarNum();// 所有权人证件号码
  String paperNum = endorsecontractTcAF.getPaperNum();// 所有权证编号
  String paperName = endorsecontractTcAF.getPaperName();// 所有权证名称
  String tel = endorsecontractTcAF.getTel();// 所有权人固定电话
  String mobile = endorsecontractTcAF.getMobile();// 所有权人移动电话

  ImpawnContract impawnContract = null;
  if (pkId != null && !"".equals(pkId)) {
    impawnContract = impawnContractDAO.queryById(new Integer(pkId));// 主键
    if (impawnContract != null) {// 存在：更新
      EndorsecontractTcAF preEndorsecontractTcAF = (EndorsecontractTcAF)request.getSession().getAttribute("theEndorsecontractTcAF");
      String preContractId = preEndorsecontractTcAF.getContractId();// 合同ID
      String preDebitter = preEndorsecontractTcAF.getDebitter();// 借款人姓名 PL110
      String preImpawnContractId = preEndorsecontractTcAF.getImpawnContractId();// 质押合同编号
      String preAssistantOrgName = preEndorsecontractTcAF.getAssistantOrgName();// 担保公司名称
      String preImpawnPerson = preEndorsecontractTcAF.getImpawnPerson();// 质押人
      String preOffice = preEndorsecontractTcAF.getOffice();// 质押权人（即××中心）
      String preImpawnMatterName = preEndorsecontractTcAF.getImpawnMatterName();// 质押物名称
      String preImpawnValue = preEndorsecontractTcAF.getImpawnValue();// 质押物价值
      String prePaperPersonName = preEndorsecontractTcAF.getPaperPersonName();// 所有权人姓名
      String preCardKind = preEndorsecontractTcAF.getCardKind();// 所有权人证件类型
      String preCarNum = preEndorsecontractTcAF.getCarNum();// 所有权人证件号码
      String prePaperNum = preEndorsecontractTcAF.getPaperNum();// 所有权证编号
      String prePaperName = preEndorsecontractTcAF.getPaperName();// 所有权证名称
      String preTel = preEndorsecontractTcAF.getTel();// 所有权人固定电话
      if(preTel == null){
        preTel="";
      }
      String preMobile = preEndorsecontractTcAF.getMobile();// 所有权人移动电话
      if(preMobile == null){
        preMobile="";
      }
      if(prePaperNum == null){
        prePaperNum = "";
      }
 
        impawnContract.setPaperNum(paperNum);
        if(!paperNum.equals(prePaperNum)){
        ContractChg contractChg1 = new ContractChg();
        contractChg1.setContractId(contractId);//合同编号
        contractChg1.setChgColumn("所有权证编号");//修改字段
        contractChg1.setChgBefInfo(prePaperNum);//修改前信息
        contractChg1.setChgEndInfo(paperNum);//修改后信息
        contractChg1.setOpTime(new Date());//操作时间
        contractChg1.setOperator(operator);
        String contractType = BusiConst.PLPREPAYMENTFEES_IMPAWNINFO + "";
        contractChg1.setContractType(contractType);//修改合同类型＝4 抵押合同信息
        contractChgDAO.insert(contractChg1);
        }
        
        impawnContract.setTel(tel);
        if(!tel.equals(preTel)){
        ContractChg contractChg2 = new ContractChg();
        contractChg2.setContractId(contractId);//合同编号
        contractChg2.setChgColumn("所有权人固定电话");//修改字段
        contractChg2.setChgBefInfo(preTel);//修改前信息
        contractChg2.setChgEndInfo(tel);//修改后信息
        contractChg2.setOpTime(new Date());//操作时间
        contractChg2.setOperator(operator);
        String contractType2 = BusiConst.PLPREPAYMENTFEES_IMPAWNINFO + "";
        contractChg2.setContractType(contractType2);//修改合同类型＝4 抵押合同信息
        contractChgDAO.insert(contractChg2);
        }
        impawnContract.setMobile(mobile);
        if(!mobile.equals(preMobile)){
        ContractChg contractChg3 = new ContractChg();
        contractChg3.setContractId(contractId);//合同编号
        contractChg3.setChgColumn("所有权人移动电话");//修改字段
        contractChg3.setChgBefInfo(preMobile);//修改前信息
        contractChg3.setChgEndInfo(mobile);//修改后信息
        contractChg3.setOpTime(new Date());//操作时间
        contractChg3.setOperator(operator);
        String contractType3 = BusiConst.PLPREPAYMENTFEES_IMPAWNINFO + "";
        contractChg3.setContractType(contractType3);//修改合同类型＝4 抵押合同信息
        contractChgDAO.insert(contractChg3);
        }
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = impawnContract.getId().toString();
      this
          .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
    }
  } else {// 不存在：插入

    // 插入担保公司
    if (assistantOrgName != null && !"".equals(assistantOrgName)) {
      AssistantOrg assistantOrg = new AssistantOrg();
      assistantOrg.setAssistantOrgName(assistantOrgName);
      assistantOrgDAO.insert(assistantOrg);
    }
    impawnContract = new ImpawnContract();
    if (contractId != null && !"".equals(contractId)) {
      impawnContract.setContractId(contractId);
    }
    if (impawnContractId != null && !"".equals(impawnContractId)) {
      impawnContract.setImpawnContractId(impawnContractId);
    }
    if (impawnMatterName != null && !"".equals(impawnMatterName)) {
      impawnContract.setImpawnMatterName(impawnMatterName);
    }
    if (impawnValue != null && !"".equals(impawnValue)) {
      impawnContract.setImpawnValue(new BigDecimal(impawnValue));
    }
    if (paperPersonName != null && !"".equals(paperPersonName)) {
      impawnContract.setName(paperPersonName);
    }
    if (cardKind != null && !"".equals(cardKind)) {
      impawnContract.setCardKind(cardKind);
    }
    if (carNum != null && !"".equals(carNum)) {
      impawnContract.setCardNum(carNum);
    }
    if (paperNum != null && !"".equals(paperNum)) {
      impawnContract.setPaperNum(paperNum);
    }
    if (paperName != null && !"".equals(paperName)) {
      impawnContract.setPaperName(paperName);
    }
    if (tel != null && !"".equals(tel)) {
      impawnContract.setTel(tel);
    }
    if (mobile != null && !"".equals(mobile)) {
      impawnContract.setMobile(mobile);
    }
    impawnContract.setStatus("0");
    String iContractId = impawnContractDAO.insert(impawnContract).toString();
    String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
    String bizId = iContractId;
    this.updatePl111(contractId);
    this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
  }
}
/**
 * 作废
 */
public void deleteImpawnContract(String id, Pagination pagination, SecurityInfo securityInfo, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
  ImpawnContract impawnContract = null;
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT + "";
  if (id != null && !"".equals(id)) {
    impawnContract = impawnContractDAO.queryById(new Integer(id));
    String bizId = impawnContract.getId().toString();
    String contractId = impawnContract.getContractId();
    String status = impawnContract.getStatus();
    if (status.equals("0")) {
      impawnContract.setStatus("1");//更新状态
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(impawnContract.getContractId());//更新pl111 合同状态＝7;
      borrowerAcc.setContractSt("7");
      String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
          bizId,contractId);
    } else {
      throw new BusinessException("该纪录已作废！");
    }
  }
}
/**
 * Td 确定
 */
public void addAssurer(String pkId, SecurityInfo securityInfo, EndorsecontractTdAF endorsecontractTdAF, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER + "";

  String contractId = endorsecontractTdAF.getContractId();// 合同ID
  String debitter = endorsecontractTdAF.getDebitter();// 借款人姓名 PL110
  String empId = endorsecontractTdAF.getEmpId();// 职工编号
  String empName = endorsecontractTdAF.getEmpName();// 职工姓名
  String cardKind = endorsecontractTdAF.getCardKind();// 证件类型
  String cardNum = endorsecontractTdAF.getCardNum();// 证件号码
  String sex = endorsecontractTdAF.getSex();// 性别
  String birthday = endorsecontractTdAF.getBirthday();// 出生日期
  String salary = endorsecontractTdAF.getSalary();// 月工资额
  String monthPay = endorsecontractTdAF.getMonthPay();// 月缴存额
  String balance = endorsecontractTdAF.getBalance();// 账户余额
  String empSt = endorsecontractTdAF.getEmpSt();// 账户状态
  String tel = endorsecontractTdAF.getTel();// 固定电话
  String mobile = endorsecontractTdAF.getMobile();// 行动电话
  String homeTel = endorsecontractTdAF.getHomeTel();// 家庭电话
  String homeAddr = endorsecontractTdAF.getHomeAddr();// 家庭住址
  String homeMail = endorsecontractTdAF.getHomeMai();// 家庭邮编
  String orgId = endorsecontractTdAF.getId();// 单位编号
  String orgName = endorsecontractTdAF.getOrgName();// 单位名称
  String orgAddr = endorsecontractTdAF.getOrgAddr();// 单位地址
  String orgTel = endorsecontractTdAF.getOrgTel();// 单位电话
  String orgMail = endorsecontractTdAF.getOrgMail();// 单位邮政编号

 
  Assurer assurer = null;
  if (pkId != null && !"".equals(pkId)) {
    assurer = assurerDAO.queryById(new Integer(pkId));// 主键查询
    if (assurer != null) {// 存在：更新
      EndorsecontractTdAF preEndorsecontractTdAF = (EndorsecontractTdAF)request.getSession().getAttribute("theEndorsecontractTdAF");
      String precontractId = preEndorsecontractTdAF.getContractId();//合同ID
      String predebitter = preEndorsecontractTdAF.getDebitter();//借款人姓名 PL110 
      String preempId = preEndorsecontractTdAF.getEmpId();//职工编号
      String preempName = preEndorsecontractTdAF.getEmpName();//职工姓名
      String precardKind = preEndorsecontractTdAF.getCardKind();//证件类型
      String precardNum = preEndorsecontractTdAF.getCardNum();//证件号码
      String presex = preEndorsecontractTdAF.getSex();//性别
      String prebirthday = preEndorsecontractTdAF.getBirthday();//出生日期
      String presalary = preEndorsecontractTdAF.getSalary();//月工资额
      String premonthPay = preEndorsecontractTdAF.getMonthPay();//月缴存额
      String prebalance = preEndorsecontractTdAF.getBalance();//账户余额
      String preempSt = preEndorsecontractTdAF.getEmpSt();//账户状态
      String pretel = preEndorsecontractTdAF.getTel();//固定电话
      if(pretel == null){
        pretel="";
      }
      String premobile = preEndorsecontractTdAF.getMobile();//行动电话
      if(premobile == null){
        premobile="";
      }
      String prehomeTel = preEndorsecontractTdAF.getHomeTel();//家庭电话
      if(prehomeTel == null){
        prehomeTel="";
      }
      String prehomeAddr = preEndorsecontractTdAF.getHomeAddr();//家庭住址
      if(prehomeAddr == null){
        prehomeAddr="";
      }
      String prehomeMai = preEndorsecontractTdAF.getHomeMai();//家庭邮编
      if(prehomeMai == null){
        prehomeMai="";
      }
      String preorgId = preEndorsecontractTdAF.getOrgId();//单位编号
      String preorgName = preEndorsecontractTdAF.getOrgName();//单位名称
      if(preorgName == null){
        preorgName="";
      }
      String preorgAddr = preEndorsecontractTdAF.getOrgAddr();//单位地址
      if(preorgAddr == null){
        preorgAddr="";
      }
      String preorgTel = preEndorsecontractTdAF.getOrgTel();//单位电话
      if(preorgTel == null){
        preorgTel="";
      }
      String preorgMail = preEndorsecontractTdAF.getOrgMail();//单位邮政编号
      if(preorgMail == null){
        preorgMail="";
      }
      
//      固定电话，移动电话，家庭电话，家庭地址，邮政编码，单位名称，单位地址，单位邮政编码，单位电话
//      其余只读
     
        assurer.setTel(tel);
        if(!tel.equals(pretel)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("固定电话");//修改字段
          contractChg1.setChgBefInfo(pretel);//修改前信息
          contractChg1.setChgEndInfo(tel);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setMobile(mobile);
        if(!mobile.equals(premobile)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("移动电话");//修改字段
          contractChg1.setChgBefInfo(premobile);//修改前信息
          contractChg1.setChgEndInfo(mobile);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
      
        assurer.setHomeTel(homeTel);
        if(!homeTel.equals(prehomeTel)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("家庭电话");//修改字段
          contractChg1.setChgBefInfo(prehomeTel);//修改前信息
          contractChg1.setChgEndInfo(homeTel);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
      
        assurer.setHomeAddr(homeAddr);
        if(!homeAddr.equals(prehomeAddr)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("家庭地址");//修改字段
          contractChg1.setChgBefInfo(prehomeAddr);//修改前信息
          contractChg1.setChgEndInfo(homeAddr);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
    
        assurer.setHomeMail(homeMail);
        if(!homeMail.equals(prehomeMai)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("家庭邮政编码");//修改字段
          contractChg1.setChgBefInfo(prehomeMai);//修改前信息
          contractChg1.setChgEndInfo(homeMail);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setOrgName(orgName);
        if(!orgName.equals(preorgName)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("单位名称");//修改字段
          contractChg1.setChgBefInfo(preorgName);//修改前信息
          contractChg1.setChgEndInfo(orgName);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setOrgAddr(orgAddr);
        if(!orgAddr.equals(preorgAddr)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("单位地址");//修改字段
          contractChg1.setChgBefInfo(preorgAddr);//修改前信息
          contractChg1.setChgEndInfo(orgAddr);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setOrgTel(orgTel);
        if(!orgTel.equals(preorgTel)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("单位电话");//修改字段
          contractChg1.setChgBefInfo(preorgTel);//修改前信息
          contractChg1.setChgEndInfo(orgTel);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
    
        assurer.setOrgMail(orgMail);
        if(!orgMail.equals(preorgMail)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//合同编号
          contractChg1.setChgColumn("单位邮政编码");//修改字段
          contractChg1.setChgBefInfo(preorgMail);//修改前信息
          contractChg1.setChgEndInfo(orgMail);//修改后信息
          contractChg1.setOpTime(new Date());//操作时间
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//修改合同类型＝6 保证人信息
          contractChgDAO.insert(contractChg1);
          }
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = assurer.getId().toString();
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
    }
  } else {// 不存在：插入
    String pl123Id = assurerDAO.queryIdYU(contractId, empName, cardNum);
    if(pl123Id != null){
      throw new BusinessException("该职工已经为保证人！");
    }else{
    assurer = new Assurer();
    if (contractId != null && !"".equals(contractId)) {
      assurer.setContractId(contractId);
    }
    // if(debitter != null && !"".equals(debitter)){
    // Borrower borrower = new Borrower();
    // borrower.setContractId(contractId);
    // borrower.setBorrowerName(debitter);
    // borrowerDAO.insert(borrower);
    // }
    if (empId != null && !"".equals(empId)) {
      assurer.setEmpId(new BigDecimal(empId));
    }
    if (empName != null && !"".equals(empName)) {
      assurer.setEmpName(empName);
    }
    if (cardKind != null && !"".equals(cardKind)) {
      assurer.setCardKind(cardKind);
    }
    if (cardNum != null && !"".equals(cardNum)) {
      assurer.setCardNum(cardNum);
    }
    if (sex != null && !"".equals(sex)) {
      assurer.setSex(sex);
    }
    if (birthday != null && !"".equals(birthday)) {
      assurer.setBirthday(birthday);
    }
    if (salary != null && !"".equals(salary)) {
      assurer.setSalary(new BigDecimal(salary));
    }
    if (monthPay != null && !"".equals(monthPay)) {
      assurer.setMonthPay(new BigDecimal(monthPay));
    }
    if (balance != null && !"".equals(balance)) {
      assurer.setBalance(new BigDecimal(balance));
    }
    if (empSt != null && !"".equals(empSt)) {
      int tempEmpSt = BusiTools.getBusiKey(empSt, BusiConst.OLDPAYMENTSTATE);
      assurer.setEmpSt(new Integer(tempEmpSt).toString());
    }
    if (tel != null && !"".equals(tel)) {
      assurer.setTel(tel);
    }
    if (mobile != null && !"".equals(mobile)) {
      assurer.setMobile(mobile);
    }
    if (homeTel != null && !"".equals(homeTel)) {
      assurer.setHomeTel(homeTel);
    }
    if (homeAddr != null && !"".equals(homeAddr)) {
      assurer.setHomeAddr(homeAddr);
    }
    if (homeMail != null && !"".equals(homeMail)) {
      assurer.setHomeMail(homeMail);
    }
    if (orgId != null && !"".equals(orgId)) {
      assurer.setOrgId(new BigDecimal(orgId));
    }
    if (orgName != null && !"".equals(orgName)) {
      assurer.setOrgName(orgName);
    }
    if (orgAddr != null && !"".equals(orgAddr)) {
      assurer.setOrgAddr(orgAddr);
    }
    if (orgTel != null && !"".equals(orgTel)) {
      assurer.setOrgTel(orgTel);
    }
    if (orgMail != null && !"".equals(orgMail)) {
      assurer.setOrgMail(orgMail);
    }
    assurer.setStatus("0");// 保证人状态 0正常
   
    //更新PL111
    this.updatePl111(contractId);
    String assurerId = assurerDAO.insert(assurer).toString();
    String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
    String bizId = assurerId;
    
    //插入PL210
    CongealInfo congealInfo = new CongealInfo();
    if(contractId != null && !"".equals(contractId)){
      congealInfo.setContractId(contractId);
    }
    if(orgId != null && !"".equals(orgId)){
      congealInfo.setOrgId(new BigDecimal(orgId));
    }
    if(empId != null && !"".equals(empId)){
      congealInfo.setEmpId(new BigDecimal(empId));
    }
    if(empName != null && !"".equals(empName)){
      congealInfo.setEmpName(empName);
    }
    if(cardKind != null && !"".equals(cardKind)){
      congealInfo.setCardKind(cardKind);
    }
    if(cardNum != null && !"".equals(cardNum)){
      congealInfo.setCardNum(cardNum);
    }
    congealInfo.setPersonId(assurerId);
    congealInfo.setStatus(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION+"");//0 冻结
    congealInfo.setType(BusiConst.PLPREPAYMENTFEES_BIALTYPE+"");//3 保证人
    congealInfoDAO.insert(congealInfo);
    this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
    }
  }
}
/**
 * Td 作废
 */
public void deleteAssurer(String id, Pagination pagination, SecurityInfo securityInfo, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
  Assurer assurer = null;
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER + "";
  if (id != null && !"".equals(id)) {
    assurer = assurerDAO.queryById(new Integer(id));
    String bizId = assurer.getId().toString();
    String contractId = assurer.getContractId();
    String status = assurer.getStatus();
    if (status.equals("0")) {
      assurer.setStatus("1");//更新状态
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);//更新pl111 合同状态＝7;
      borrowerAcc.setContractSt("7");
      //更新PL210
      String statu = BusiConst.PLPREPAYMENTFEES_CONGEALINFOTHAW+"";
      congealInfoDAO.updateCongealInfo(statu, id, contractId);
      String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
          bizId,contractId);
    } else {
      throw new BusinessException("该纪录已作废！");
    }
  }
}
  
}
