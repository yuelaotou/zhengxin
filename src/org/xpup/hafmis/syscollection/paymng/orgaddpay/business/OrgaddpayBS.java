package org.xpup.hafmis.syscollection.paymng.orgaddpay.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
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
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgAddPayBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgAddPayDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.MonthpayTbWindowDto;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.AddpayInfoDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgAddpayInfoDTO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadImportDTO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadPrintDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayListImportDTO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayMaintainDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayMonthDTO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTbPickupdataWindowAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpExpInfoDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayListImportDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.daoDW.OrgAddPayTailDAODW;


public class OrgaddpayBS implements IOrgaddpayBS{
  private OrgDAO orgDAO = null;
  private EmpDAO empDAO = null;
  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;
  private MonthPaymentTailDAO monthPaymentTailDAO = null;
  private OrgAddPayDAO orgAddPayDAO = null;
  private OrgAddPayBizActivityLogDAO orgAddPayBizActivityLogDAO = null;
  private HafOperateLogDAO hafOperateLogDAO = null;
  private MonthPaymentDAO monthPaymentDAO =null;
  private CollBankDAO collBankDAO = null;
  private AutoInfoPickDAODW autoInfoPickDAODW = null;
  private AutoInfoPickDAO autoInfoPickDAO = null;
  private OrgAddPayTailDAODW orgAddPayTailDAODW = null;
  private OrganizationUnitDAO organizationUnitDAO=null;
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO=null;
  private String temp_centerheadid;
  
  
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
  
  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setOrgAddPayBizActivityLogDAO(
      OrgAddPayBizActivityLogDAO orgAddPayBizActivityLogDAO) {
    this.orgAddPayBizActivityLogDAO = orgAddPayBizActivityLogDAO;
  }

  public void setMonthPaymentTailDAO(MonthPaymentTailDAO monthPaymentTailDAO) {
    this.monthPaymentTailDAO = monthPaymentTailDAO;
  }

  public void setOrgAddPayDAO(OrgAddPayDAO orgAddPayDAO) {
    this.orgAddPayDAO = orgAddPayDAO;
  }
  public void setMonthPaymentDAO(MonthPaymentDAO monthPaymentDAO) {
    this.monthPaymentDAO = monthPaymentDAO;
  }
  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }
  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }
  public void setOrgAddPayTailDAODW(OrgAddPayTailDAODW orgAddPayTailDAODW) {
    this.orgAddPayTailDAODW = orgAddPayTailDAODW;
  }
  // 查询银行
  public String findCollBank(String collBankid) {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }
  //根据单位ID查询单位信息
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException {
    // TODO Auto-generated method stub
    Org org = null;
    String orgid="";
    if(id!=null){
      orgid=id.toString();
    }
    org = orgDAO.queryByCriterions(orgid,status,null,securityInfo);
    if(org == null && orgid !=null){
      org = new Org();
    }
    return org;
  }
  //查询单位补缴信息
  public OrgAddPay findOrgaddpayInfo(Serializable headId) throws Exception{
    OrgAddPay orgAddPay = null;
    orgAddPay = orgAddPayDAO.queryById(new Integer(headId.toString()));
    if(orgAddPay == null){
      orgAddPay = new OrgAddPay();
    }
    return orgAddPay;
  }
  //查询单位补缴清册
  public OrgaddpayTaAF findOrgaddpay(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = new ArrayList();
    List emplist = new ArrayList();
    List monthPaymentlist = new ArrayList();
    MonthPaymentHead monthPaymentHead1 = new MonthPaymentHead();
    MonthPaymentHead monthPaymentHead2 = new MonthPaymentHead();
    MonthPaymentHead monthPaymentHeadtotal = new MonthPaymentHead();
    List monthPaymentHeadList = new ArrayList();
    OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
    BigDecimal empSumpay = new BigDecimal(0.00);// 个人总金额
    BigDecimal orgSumpay = new BigDecimal(0.00);// 单位总金额
    BigDecimal addpayMoney = new BigDecimal(0.00);// 补缴总额
    // try{
    String orgid = (String) pagination.getQueryCriterions().get("id");
    String name = (String) pagination.getQueryCriterions().get("name");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    orgaddpayTaAF.setOrgid(orgid);
    Org org = null;
    int count = 0;
    // 输入单位编号时判断登录日期与单位所在的归集银行的归集银行表的日结日期是否一致，不一致不允许做业务
    String bizDate = securityInfo.getUserInfo().getBizDate();// 业务日期
    String bankDate = "";
    // 系统自动生成结算号：业务日期+流水号
    String noteNum = "";
    if (orgid != null) {
      org = this.findOrgInfo(orgid, "2", securityInfo);
      noteNum = bizDate + orgDAO.queryNoteNum();
      if (org.getId() == null) {
        throw new BusinessException("查无此单位！");
      }
      bankDate = orgDAO.findAA103_DayTime(org.getOrgInfo()
          .getCollectionBankId());
      if (!bizDate.equals(bankDate)) {
        throw new BusinessException("登录日期与银行业务日期不一致，此单位不能做业务！");
      } else {
        monthPaymentlist = monthPaymentDAO.getMonthPaymentByOrgid(org.getId());
      }
      // if(monthPaymentlist.size()<=0){
      // throw new BusinessException("该单位尚未进行过汇缴业务，不能补缴！");
      // }else{
      list = monthPaymentTailDAO.queryOrgaddpayEmpList_jj(orgid, orderBy,
          order, start, pageSize, page);
      if (list.size() > 0) {
        OrgAddpayInfoDTO tm = (OrgAddpayInfoDTO) list.get(0);
        for (int i = 0; i < list.size(); i++) {
          OrgAddpayInfoDTO m = (OrgAddpayInfoDTO) list.get(i);
          String statetype = autoInfoPickDAODW.findStatus(orgaddpayTaAF
              .getOrgid(), tm.getId().toString(), BusiConst.ORGBUSINESSTYPE_B);
          orgaddpayTaAF.setStatetype(BusiTools.getBusiValue(Integer
              .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
          Emp emp = empDAO.queryByCriterions(m.getEmpId().toString(), orgid);
          m.setEmp(emp);
          emplist.add(m);

        }
        List countList = monthPaymentTailDAO.queryCountOrgaddpayEmpList_jj(
            orgid, orderBy, order);
        if (!countList.isEmpty()) {
          count = countList.size();
        }
        monthPaymentHeadList = monthPaymentHeadDAO.queryOrgaddpayHeadLJ(orgid);
        if (!monthPaymentHeadList.isEmpty()) {
          monthPaymentHead1 = (MonthPaymentHead) monthPaymentHeadList.get(0);
          monthPaymentHead2 = (MonthPaymentHead) monthPaymentHeadList
              .get(monthPaymentHeadList.size() - 1);
          orgaddpayTaAF.setNoteNum(monthPaymentHead1.getPaymentHead()
              .getNoteNum());
          orgaddpayTaAF.setOrgid(org.getId().toString());
          orgaddpayTaAF.setPayKind(monthPaymentHead1.getPaymentHead()
              .getPay_kind());
          orgaddpayTaAF.setPayWay(monthPaymentHead1.getPaymentHead()
              .getPay_way());
          for (int i = 0; i < monthPaymentHeadList.size(); i++) {
            monthPaymentHeadtotal = (MonthPaymentHead) monthPaymentHeadList
                .get(i);
            empSumpay = empSumpay.add(monthPaymentHeadtotal.getEmpSumpay());
            orgSumpay = orgSumpay.add(monthPaymentHeadtotal.getOrgSumpay());
            addpayMoney = addpayMoney.add(monthPaymentHeadtotal
                .getAddpayMoney());
          }
        }
      } else {
        orgaddpayTaAF.setNoteNum(noteNum);
      }
    }
    monthPaymentHeadtotal.setEmpSumpay(empSumpay);
    monthPaymentHeadtotal.setOrgSumpay(orgSumpay);
    monthPaymentHeadtotal.setAddpayMoney(addpayMoney);
    pagination.setNrOfElements(count);
    orgaddpayTaAF.setList(emplist);
    orgaddpayTaAF.setMonthPaymentHead(monthPaymentHeadtotal);
    orgaddpayTaAF.setPayMonth(monthPaymentHead2.getPayMonth());
    orgaddpayTaAF.setStartPayMonth(monthPaymentHead1.getPayMonth());
    orgaddpayTaAF.setName(name);
    //    }catch(BusinessException e){
    //      e.printStackTrace();
    //    }
    return orgaddpayTaAF;

  }
  //删除补缴清册
  public void deleteOrgaddpayListing(Serializable tailId,Serializable headId,SecurityInfo securityInfo) throws Exception{
    //现在tailId取的是empId
    String empId=tailId.toString();
    //判断该记录是否为同批补缴中的最后一条记录
    //尾表中最大ID值
    String id = monthPaymentTailDAO.queryOrgaddpayListingLJ(headId).toString();
    MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headId.toString()));
    //MonthPaymentTail monthPaymentTail = monthPaymentTailDAO.queryById(new Integer(tailId.toString()));
    List monthPaymentTailList = monthPaymentTailDAO.queryOrgaddpayListing_jj(monthPaymentHead.getPaymentHead().getId(), empId);
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(monthPaymentHead.getPaymentHead().getId().toString()));
    BigDecimal sumMoney = new BigDecimal(0.00);
    sumMoney = monthPaymentTailDAO.queryOrgaddpayMoney_jj(monthPaymentHead.getPaymentHead().getId(), empId);
    //删除AA303中对应的该职工补缴记录
    if(!monthPaymentTailList.isEmpty()){
      for(int i=0;i<monthPaymentTailList.size();i++){
        Object obj=(Object)monthPaymentTailList.get(i);
        MonthPaymentTail monthPaymentTail = monthPaymentTailDAO.queryById(new Integer(obj.toString()));
        monthPaymentTailDAO.delete(monthPaymentTail);   
        //根据302的ID查询303是否还存在记录
        List monthtaillist=monthPaymentTailDAO.queryPaymentTailList(monthPaymentTail.getMonthPaymentHead().getId());
        if(monthtaillist.isEmpty()){
          //如果list为空则删除302的记录
          monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(monthPaymentTail.getMonthPaymentHead().getId().toString()));
          monthPaymentHeadDAO.delete(monthPaymentHead);
        }
      }
    }
    
    List taillist=monthPaymentTailDAO.queryPaymentTailListLJ(orgAddPay.getId());
    if(taillist.size()<=0){
      //是最后一条记录
      int orgEditionFlag = securityInfo.getIsOrgEdition();
      String commitSt = "";
      String org_id = orgAddPay.getOrg().getId().toString();
      String type = BusiConst.ORGBUSINESSTYPE_B;
      if(orgEditionFlag!=BusiConst.ORG_OR_CENTER_INFO_ORG && orgEditionFlag!=BusiConst.ORG_OR_CENTER_INFO_CENTER){
        throw new BusinessException("版本信息错误！");
      }else{
        if(orgEditionFlag == BusiConst.ORG_OR_CENTER_INFO_ORG){
          //otgEditionFlag=1——单位版，判断提交状态是否为未提取——ww
          String org_head_id = orgAddPay.getId().toString();
          commitSt = autoInfoPickDAODW.findStatus(org_id, org_head_id, type);
          if(commitSt != null||commitSt!=""){
            if(commitSt.equals(BusiConst.OC_NOT_PICK_UP)){
                throw new BusinessException("请先撤销提交数据！");
              }   
            if(commitSt.equals(BusiConst.OC_PICK_UP)){
              throw new BusinessException("数据已提取，不可以再删除！");
            }            
          }else{
            throw new BusinessException("删除失败！");
          }
        }else{
          //otgEditionFlag=2,中心版
          String center_head_id = orgAddPay.getId().toString();
          String centerheadid = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, centerheadid, centerBizDate, org_id, center_head_id, type);
        }     
      }
//      //删除AA302
//      MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headId.toString()));
//      monthPaymentHeadDAO.delete(monthPaymentHead);
      //删除AA301
      orgAddPayDAO.delete(orgAddPay);
      //删除AA319中BIZID=AA301.Id and AA319.action=2 and AA319.type=B的记录
      OrgAddPayBizActivityLog orgAddPayBizActivityLog = orgAddPayBizActivityLogDAO.queryOrgAddPayBizActivityLogLJ(orgAddPay.getId(),new Integer(1));
      orgAddPayBizActivityLogDAO.delete(orgAddPayBizActivityLog); 
      
    }else{
      //更新AA301中对应的金额信息
      BigDecimal payMoney =orgAddPay.getPayMoney();
      payMoney=payMoney.subtract(sumMoney);
      orgAddPay.setPayMoney(payMoney);
    }
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_DELETE));
    hafOperateLog.setOpBizId(new Integer(orgAddPay.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgAddPay.getOrg().getId().toString()));
    hafOperateLogDAO.insert(hafOperateLog);
    
  }
  //删除补缴清册
  public void deleteAllOrgaddpayListing(List list,Serializable headId,SecurityInfo securityInfo) throws Exception{
    MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headId.toString()));
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(monthPaymentHead.getPaymentHead().getId().toString()));
    int orgEditionFlag = securityInfo.getIsOrgEdition();
    String commitSt = "";
    String org_id = orgAddPay.getOrg().getId().toString();
    String type = BusiConst.ORGBUSINESSTYPE_B;
    if(orgEditionFlag!=BusiConst.ORG_OR_CENTER_INFO_ORG && orgEditionFlag!=BusiConst.ORG_OR_CENTER_INFO_CENTER){
      throw new BusinessException("版本信息错误！");
    }else{
      if(orgEditionFlag == BusiConst.ORG_OR_CENTER_INFO_ORG){
        //otgEditionFlag=1——单位版，判断提交状态是否为未提取——ww
        String org_head_id = orgAddPay.getId().toString();
        commitSt = autoInfoPickDAODW.findStatus(org_id, org_head_id, type);
        if(commitSt != null||commitSt!=""){
          if(commitSt.equals(BusiConst.OC_NOT_PICK_UP)){
              throw new BusinessException("请先撤销提交数据！");
            }   
          if(commitSt.equals(BusiConst.OC_PICK_UP)){
            throw new BusinessException("数据已提取，不可以再删除！");
          }            
        }else{
          throw new BusinessException("删除失败！");
        }
      }else{
        //otgEditionFlag=2,中心版
        String center_head_id = orgAddPay.getId().toString();
        String centerheadid = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, centerheadid, centerBizDate, org_id, center_head_id, type);
      }     
    }
    List monthPaymentTaillist = monthPaymentTailDAO.queryPaymentTailListLJ(monthPaymentHead.getPaymentHead().getId());
    //删除AA303
    monthPaymentTailDAO.deleteList(monthPaymentTaillist);
    //删除AA302
    //MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headId.toString()));
//    monthPaymentHeadDAO.delete(monthPaymentHead);
    monthPaymentHeadDAO.deleteMonthPaymentHead(new Integer(monthPaymentHead.getPaymentHead().getId().toString()));
    //删除AA301
    //OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(monthPaymentHead.getPaymentHead().getId().toString()));
    orgAddPayDAO.delete(orgAddPay);
    //删除AA319中BIZID=AA301.Id and AA319.action=2 and AA319.type=B的记录
    OrgAddPayBizActivityLog orgAddPayBizActivityLog = orgAddPayBizActivityLogDAO.queryOrgAddPayBizActivityLogLJ(orgAddPay.getId(),new Integer(1));
    orgAddPayBizActivityLogDAO.delete(orgAddPayBizActivityLog); 
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_DELETEALL));
    hafOperateLog.setOpBizId(new Integer(orgAddPay.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgAddPay.getOrg().getId().toString()));
    hafOperateLogDAO.insert(hafOperateLog);
    
  }
  //完成补缴登记
  public void overAddpay(Serializable tailId,Serializable headId,SecurityInfo securityInfo,String noteNum,Pagination pagination)throws Exception{
    //更新AA301.PAY_STATUS=2同时更新AA301票据编号为界面中的值
    String pay_kind=(String) pagination.getQueryCriterions().get("payKind");
    String pay_way=(String) pagination.getQueryCriterions().get("payWay");
    String payment_bank_name=(String) pagination.getQueryCriterions().get("payment_bank_name");
    String payment_bank_acc=(String) pagination.getQueryCriterions().get("payment_bank_acc");
    MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headId.toString()));
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(monthPaymentHead.getPaymentHead().getId().toString()));
    orgAddPay.setPayStatus(new Integer(2));
    if(noteNum!=null && !"".equals(noteNum)){
    orgAddPay.setNoteNum(noteNum);
    }
    if(pay_kind != null && !"".equals(pay_kind)){
      orgAddPay.setPay_kind(pay_kind);      
    }
    if(pay_way != null && !"".equals(pay_way)){
      orgAddPay.setPay_way(pay_way);
    }
    if(payment_bank_name != null && !"".equals(payment_bank_name)){
      orgAddPay.setPayment_bank_name(payment_bank_name);      
    }
    if(payment_bank_acc != null && !"".equals(payment_bank_acc)){
      orgAddPay.setPayment_bank_acc(payment_bank_acc);
    }
    //向AA319中插入业务活动日志AA319.BIZID=AA301.ID,AA319.ACTION=2,AA319.TYPE=B
    OrgAddPayBizActivityLog addPayBizActivityLog = new OrgAddPayBizActivityLog();
    addPayBizActivityLog.setBizid(new Integer(orgAddPay.getId().toString()));
    addPayBizActivityLog.setAction(new Integer(2));
    addPayBizActivityLog.setOperator(securityInfo.getUserName());
    addPayBizActivityLog.setOpTime(new Date());
    orgAddPayBizActivityLogDAO.insert(addPayBizActivityLog);
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_OPENING));
    hafOperateLog.setOpBizId(new Integer(orgAddPay.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgAddPay.getOrg().getId().toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }
  //查询单位补缴列表
  public List findOrgaddpayList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    List list = new ArrayList();
    List returnlist=new ArrayList();     
    try{
    Serializable id=(Serializable)pagination.getQueryCriterions().get("id");
    if(pagination.getQueryCriterions().get("id") != null){
      id=pagination.getQueryCriterions().get("id").toString();
    }
    String name=(String) pagination.getQueryCriterions().get("name");
    String status = (String) pagination.getQueryCriterions().get("status");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    String payType = (String) pagination.getQueryCriterions().get("payType");
    String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
    String settlementDate = (String) pagination.getQueryCriterions().get("settlementDate");
    String settlementDate1 = (String) pagination.getQueryCriterions().get("settlementDate1");
    String compare = (String)pagination.getQueryCriterions().get("compare");
    String settDate = (String) pagination.getQueryCriterions().get("settDate") ;
    String settDate1 = (String) pagination.getQueryCriterions().get("settDate1") ;
    String commitStatus = (String) pagination.getQueryCriterions().get("commitStatus") ;//ww——单位版提交状态
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page=pagination.getPage();
    list=monthPaymentTailDAO.queryOrgaddpayListByCriterionsLJ(id, name, status, payMonth, payType, payMoney,
        settlementDate,settlementDate1, compare, orderBy, order, start, pageSize,securityInfo,page,settDate,settDate1,collBankId);
    //转换业务状态
    if(list!=null){
      for(int i=0;i<list.size();i++){
        OrgaddpayMaintainDto dto1=(OrgaddpayMaintainDto)list.get(i);
        
//      ww——提交状态
        String org_id = dto1.getOrgId().toString();
        String type = BusiConst.ORGBUSINESSTYPE_B;
        String org_head_id = dto1.getId().toString();
        if(org_id!=null && org_head_id!=null){
          commitStatus = autoInfoPickDAODW.findStatus(org_id, org_head_id, type);
        }
//        dto1.setCommitStatus(commitStatus);
        //ww——提交状态结束
        
        OrgaddpayMaintainDto dto2=new OrgaddpayMaintainDto();
        BeanUtils.copyProperties(dto2, dto1);    
        dto2.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto1.getPayStatus()), BusiConst.BUSINESSSTATE));
        dto2.setPayMonth(dto1.getStartPayMonth()+"-"+dto1.getPayMonth());
        dto2.setCommitStatus(BusiTools.getBusiValue(Integer.parseInt(commitStatus), BusiConst.OC_NOT_PICK_UP_INFO));//ww——加入提交状态
        returnlist.add(dto2);
      }
    }
    int count =monthPaymentTailDAO.queryOrgaddPayCountByCriterionsLJ(id, name, status, payMonth, payType, payMoney, settlementDate, settlementDate1
        ,compare,securityInfo,settDate,settDate1,collBankId);
    pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
  }

  /**
   * 查询单位补缴列表打印
   */
  public List findOrgaddpayListPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    List list = new ArrayList();
    List returnlist=new ArrayList();     
    try{
    Serializable id=(Serializable)pagination.getQueryCriterions().get("id");
    if(pagination.getQueryCriterions().get("id") != null){
      id=pagination.getQueryCriterions().get("id").toString();
    }
    String name=(String) pagination.getQueryCriterions().get("name");
    String status = (String) pagination.getQueryCriterions().get("status");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    String payType = (String) pagination.getQueryCriterions().get("payType");
    String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
    String settlementDate = (String) pagination.getQueryCriterions().get("settlementDate");
    String settlementDate1 = (String) pagination.getQueryCriterions().get("settlementDate1");
    String compare = (String)pagination.getQueryCriterions().get("compare");
    String settDate = (String) pagination.getQueryCriterions().get("settDate") ;
    String settDate1 = (String) pagination.getQueryCriterions().get("settDate1") ;
    String commitStatus = (String) pagination.getQueryCriterions().get("commitStatus") ;//ww——单位版提交状态
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 

    list=monthPaymentTailDAO.queryOrgaddpayListByCriterionsPrint_jj(id, name, status, payMonth, payType, payMoney,
        settlementDate,settlementDate1, compare, orderBy, order,securityInfo,settDate,settDate1,collBankId);
    //转换业务状态
    if(list!=null){
      for(int i=0;i<list.size();i++){
        OrgaddpayMaintainDto dto1=(OrgaddpayMaintainDto)list.get(i);
        
//      ww——提交状态
        String org_id = dto1.getOrgId().toString();
        String type = BusiConst.ORGBUSINESSTYPE_B;
        String org_head_id = dto1.getId().toString();
        if(org_id!=null && org_head_id!=null){
          commitStatus = autoInfoPickDAODW.findStatus(org_id, org_head_id, type);
        }
//        dto1.setCommitStatus(commitStatus);
        //ww——提交状态结束
        
        OrgaddpayMaintainDto dto2=new OrgaddpayMaintainDto();
        BeanUtils.copyProperties(dto2, dto1);    
        dto2.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto1.getPayStatus()), BusiConst.BUSINESSSTATE));
        dto2.setPayMonth(dto1.getStartPayMonth()+"-"+dto1.getPayMonth());
        dto2.setCommitStatus(BusiTools.getBusiValue(Integer.parseInt(commitStatus), BusiConst.OC_NOT_PICK_UP_INFO));//ww——加入提交状态
        returnlist.add(dto2);
      }
    }

    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
  }
  //单位补缴金额合计
  public BigDecimal getOrgaddpayMoney(Pagination pagination, SecurityInfo securityInfo){
    BigDecimal money = null;
    Serializable id=(Serializable)pagination.getQueryCriterions().get("id");
    if(pagination.getQueryCriterions().get("id") != null){
      id=pagination.getQueryCriterions().get("id").toString();
    }
    String name=(String) pagination.getQueryCriterions().get("name");
    String status = (String) pagination.getQueryCriterions().get("status");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    String payType = (String) pagination.getQueryCriterions().get("payType");
    String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
    String settlementDate = (String) pagination.getQueryCriterions().get("settlementDate");
    String compare = (String)pagination.getQueryCriterions().get("compare");
    String settDate = (String) pagination.getQueryCriterions().get("settDate") ;
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    money = monthPaymentTailDAO.queryOrgaddPayMoneyByCriterionsLJ(id, name, status, payMonth, payType, 
        payMoney, settlementDate, compare,securityInfo,settDate,collBankId);
    money = money.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
    return money;
  }
  //撤消单位补缴登记
  public void repealAddpay(Serializable orgaddpayId,SecurityInfo securityInfo) throws Exception{
    //是否存在AA301.PAY_STATUS=1 and AA301.PAY_TYPE=B的记录
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(orgaddpayId.toString()));
    Serializable orgid = orgAddPay.getOrg().getId();
    OrgAddPay o = orgAddPayDAO.queryOrgAddPayByOrgIdLJ(orgid, new Integer(1));
    if(o.getId() != null){
      throw new BusinessException("该单位存在未完成的补缴清册，不能撤销！");
    }
    //更新AA301.PAY_STATUS=1
    orgAddPay.setPayStatus(new Integer(1));
    //删除AA319中AA319.BIZID=AA301.ID,AA319.ACTION=2,AA319.TYPE=B
    OrgAddPayBizActivityLog orgAddPayBizActivityLog = orgAddPayBizActivityLogDAO.queryOrgAddPayBizActivityLogLJ(orgaddpayId,new Integer(2));
    orgAddPayBizActivityLogDAO.delete(orgAddPayBizActivityLog);
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_REVOCATION));
    hafOperateLog.setOpBizId(new Integer(orgaddpayId.toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgid.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }
  //删除单位补缴信息
  public void deleteAddpay(Serializable orgaddpayId,SecurityInfo securityInfo) throws Exception{
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(orgaddpayId.toString()));
    //  判断单位版还是中心版，对提取表DA001的操作
    int orgEditionFlag = securityInfo.getIsOrgEdition();
    String commitSt = "";
    String org_id = orgAddPay.getOrg().getId().toString();
    String type = BusiConst.ORGBUSINESSTYPE_B;
    if(orgEditionFlag!=BusiConst.ORG_OR_CENTER_INFO_ORG && orgEditionFlag!=BusiConst.ORG_OR_CENTER_INFO_CENTER){
      throw new BusinessException("版本信息错误！");
    }else{
      if(orgEditionFlag == BusiConst.ORG_OR_CENTER_INFO_ORG){
        //otgEditionFlag=1——单位版，判断提交状态是否为未提取——ww
        String org_head_id = orgaddpayId.toString();
        commitSt = autoInfoPickDAODW.findStatus(org_id, org_head_id, type);
        if(commitSt != null||commitSt!=""){
          if(commitSt.equals(BusiConst.OC_NOT_PICK_UP)){
              throw new BusinessException("请先撤销提交数据！");
            }   
          if(commitSt.equals(BusiConst.OC_PICK_UP)){
            throw new BusinessException("数据已提取，不可以再删除！");
          }            
        }else{
          throw new BusinessException("删除失败！");
        }
      }else{
        //otgEditionFlag=2,中心版
        String center_head_id = orgaddpayId.toString();
        String centerheadid = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP, centerheadid, centerBizDate, org_id, center_head_id, type);
      }     
    }
    
    //删除AA303中全部记录的同时删除AA302、AA301中对应的记录
    //删除303
    List taillist=monthPaymentTailDAO.queryPaymentTailListLJ(orgaddpayId);
    monthPaymentTailDAO.deleteList(taillist);
    //删除302
    List headlist=monthPaymentHeadDAO.queryMonthPaymentHeadLJ(orgaddpayId);
    monthPaymentHeadDAO.deleteList(headlist);
    //删除301
    //OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(orgaddpayId.toString()));
    orgAddPayDAO.delete(orgAddPay);
    Serializable orgid = orgAddPay.getOrg().getId();
    //删除AA319中BIZID=AA301.Id and AA319.action=1 and AA319.type=B的记录
    OrgAddPayBizActivityLog orgAddPayBizActivityLog = orgAddPayBizActivityLogDAO.queryOrgAddPayBizActivityLogLJ(orgaddpayId,new Integer(1));
    orgAddPayBizActivityLogDAO.delete(orgAddPayBizActivityLog);
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_DELETE));
    hafOperateLog.setOpBizId(new Integer(orgaddpayId.toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgid.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
  }
  //批量导出
  public List findPaylistBatch(Serializable orgid,String payMonth,String startPayMonth,String noteNum,SecurityInfo securityInfo) throws Exception{
    List emplist = new ArrayList();
    /*
    //录入的补缴年月在AA302中是否存在且AA301.PAY_STATUS<>5(包括汇缴补缴)
    List taillist = monthPaymentHeadDAO.queryOrgAddPayHeadInfoLJ(orgid, payMonth,securityInfo);
    if(taillist != null && taillist.size()>0){
      throw new BusinessException("该单位已经存在同年月未入账的记录不能进行补缴！");
    }
    //补缴年月是否小于等于该单位的缴至年月中大的那个缴至年月（已记账状态）
    //缴至年月
    String maxMonth = monthPaymentHeadDAO.queryMaxMonthOrgaddpay(orgid);
    //301里缴至年月为空
    String orgpayMonth="";
    String emppayMonth="";
    int iMaxMonth=0;
    int iPayMonth=0;
    int iMonth = Integer.parseInt(payMonth);
    if(maxMonth != null){
      iMaxMonth= Integer.parseInt(maxMonth);
      if(iMonth > iMaxMonth){
        throw new BusinessException("该单位不能进行"+maxMonth.substring(0,4)+"年"+maxMonth.substring(4,maxMonth.length())+"月以后的补缴！");
      }
    }else{
      List list = orgDAO.queryOrgaddPaymonth(orgid);
      if(list != null && list.size()>0){
        OrgaddpayMonthDTO dto=(OrgaddpayMonthDTO)list.get(0);
        orgpayMonth = dto.getOrgPaymonth();
        emppayMonth = dto.getEmpPaymonth();
        if(Integer.parseInt(orgpayMonth)>=Integer.parseInt(emppayMonth)){
          iPayMonth=Integer.parseInt(orgpayMonth);
        }else{
          iPayMonth=Integer.parseInt(emppayMonth);
        }
        if(iMonth > iPayMonth){
          throw new BusinessException("该单位不能进行"+Integer.toString(iPayMonth).substring(0,4)+"年"+Integer.toString(iPayMonth).substring(4,Integer.toString(iPayMonth).length())+"月以后的补缴！");
        }
      }
    }
    */
    /**
     * 修改jj20071217   ------开始-------
     */
    //当终止年月大于起始年月时，导出职工表中的记录
    if(Integer.parseInt(payMonth)>Integer.parseInt(startPayMonth)){
      //取002
      List list2=empDAO.queryEmpByOrgIdSL(orgid.toString());
      if(list2.size()>0){
        for(int i=0; i<list2.size();i++){
          Emp emp = (Emp)list2.get(i);
          AddpayInfoDto addpayInfoDto = new AddpayInfoDto();
          addpayInfoDto.setOrgid(emp.getOrg().getId().toString());
          addpayInfoDto.setOrgName(emp.getOrg().getOrgInfo().getName());
          addpayInfoDto.setAddpayMonth(payMonth);
          //修改 -----开始
          addpayInfoDto.setAddStartPayMonth(startPayMonth);
          //-------结束
          addpayInfoDto.setEmpAddpayMoney("");
          addpayInfoDto.setEmpId(emp.getEmpId().toString());
          addpayInfoDto.setEmpName(emp.getEmpInfo().getName());
          addpayInfoDto.setEmpShouldpay(emp.getEmpPay().toString());
          addpayInfoDto.setNoteNum(noteNum);
          addpayInfoDto.setOrgAddpayMoney("");
          addpayInfoDto.setOrgShouldpay(emp.getOrgPay().toString());
          addpayInfoDto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
          addpayInfoDto.setSalaryBase(emp.getSalaryBase().toString());
          addpayInfoDto.setOrgRate(emp.getOrg().getOrgRate().toString());
          addpayInfoDto.setEmpRate(emp.getOrg().getEmpRate().toString());
          emplist.add(addpayInfoDto);
        }
      }
    }else{
      /**
       * jj修改结束
       */
      //录入的补缴年月在AA302中是否存在且AA301.PAY_STATUS=5
      List list = monthPaymentHeadDAO.queryMonthHeadInfoLJ(orgid, payMonth);
      if(list != null && list.size()>0){
        //取303
        MonthPaymentHead monthPaymentHead1=(MonthPaymentHead)list.get(0);
        List list1 = monthPaymentTailDAO.queryPaymentTailListLJ_(monthPaymentHead1.getId());
        if(list1.size()>0){
          for(int i=0; i<list1.size();i++){
            MonthPaymentTail monthPaymentTail = (MonthPaymentTail)list1.get(i);
            AddpayInfoDto addpayInfoDto = new AddpayInfoDto();
            Emp emp = empDAO.queryByCriterions(monthPaymentTail.getEmpId().toString(),monthPaymentTail.getMonthPaymentHead().getPaymentHead().getOrg().getId().toString());
            addpayInfoDto.setOrgid(monthPaymentTail.getMonthPaymentHead().getPaymentHead().getOrg().getId().toString());
            addpayInfoDto.setOrgName(monthPaymentTail.getMonthPaymentHead().getPaymentHead().getOrg().getOrgInfo().getName());
            addpayInfoDto.setAddpayMonth(monthPaymentTail.getMonthPaymentHead().getPayMonth());
            //修改 -----开始
            addpayInfoDto.setAddStartPayMonth(monthPaymentTail.getMonthPaymentHead().getPayMonth());
            //-------结束
            addpayInfoDto.setEmpAddpayMoney("");
            addpayInfoDto.setEmpId(monthPaymentTail.getEmpId().toString());
            addpayInfoDto.setEmpName(monthPaymentTail.getEmpName());
            addpayInfoDto.setEmpShouldpay(monthPaymentTail.getEmpShouldPay().toString());
            addpayInfoDto.setNoteNum(noteNum);
            addpayInfoDto.setOrgAddpayMoney("");
            addpayInfoDto.setOrgShouldpay(monthPaymentTail.getOrgShouldPay().toString());
            addpayInfoDto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
            addpayInfoDto.setSalaryBase(monthPaymentTail.getSalaryBase().toString());
            addpayInfoDto.setOrgRate(monthPaymentTail.getOrgRate().toString());
            addpayInfoDto.setEmpRate(monthPaymentTail.getEmpRate().toString());
            emplist.add(addpayInfoDto);
          }
        }
      }else{
        //取002
        List list2=empDAO.queryEmpByOrgIdSL(orgid.toString());
        if(list2.size()>0){
          for(int i=0; i<list2.size();i++){
            Emp emp = (Emp)list2.get(i);
            AddpayInfoDto addpayInfoDto = new AddpayInfoDto();
            addpayInfoDto.setOrgid(emp.getOrg().getId().toString());
            addpayInfoDto.setOrgName(emp.getOrg().getOrgInfo().getName());
            addpayInfoDto.setAddpayMonth(payMonth);
            //修改 -----开始
            addpayInfoDto.setAddStartPayMonth(payMonth);
            //-------结束
            addpayInfoDto.setEmpAddpayMoney("");
            addpayInfoDto.setEmpId(emp.getEmpId().toString());
            addpayInfoDto.setEmpName(emp.getEmpInfo().getName());
            addpayInfoDto.setEmpShouldpay(emp.getEmpPay().toString());
            addpayInfoDto.setNoteNum(noteNum);
            addpayInfoDto.setOrgAddpayMoney("");
            addpayInfoDto.setOrgShouldpay(emp.getOrgPay().toString());
            addpayInfoDto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
            addpayInfoDto.setSalaryBase(emp.getSalaryBase().toString());
            addpayInfoDto.setOrgRate(emp.getOrg().getOrgRate().toString());
            addpayInfoDto.setEmpRate(emp.getOrg().getEmpRate().toString());
            emplist.add(addpayInfoDto);
          }
        }
      }      
    }
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_EXP));
    hafOperateLog.setOpBizId(null);
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgid.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
    return emplist;
  }
  //批量导入
  public List addPaylistBatch(List addpayHeadImportList,List addpayListImportList,String orgid,String payMonth,
      String startPayMonth,String noteNum,SecurityInfo securityInfo,Pagination pagination) throws Exception {
    List list = new ArrayList();
    BigDecimal money = new BigDecimal(0);
    BigDecimal orgmoney = new BigDecimal(0);
    BigDecimal empmoney = new BigDecimal(0);
    if(addpayHeadImportList.size()<=0){
      throw new BusinessException("导入数据为空！");
    }
    if(addpayListImportList.size()<=0){
      throw new BusinessException("导入数据为空！");
    }
    OrgaddpayTaAF orgaddpayTaAF = (OrgaddpayTaAF)pagination.getQueryCriterions().get("orgaddpayTaAF");
    String receivables_bank_name = orgaddpayTaAF.getReceivables_bank_name();
    String receivables_bank_acc = orgaddpayTaAF.getReceivables_bank_acc();
    String payment_bank_name = orgaddpayTaAF.getPayment_bank_name();
    String payment_bank_acc = orgaddpayTaAF.getPayment_bank_acc();
    String payment_orgname = orgaddpayTaAF.getPayment_orgname();
    String payWay = orgaddpayTaAF.getPayWay();
    String payKind = orgaddpayTaAF.getPayKind();
    //录入的补缴年月在AA302中是否存在且AA301.PAY_STATUS<>5
//    List taillist = monthPaymentHeadDAO.queryOrgAddPayHeadInfoLJ(orgid,payMonth,securityInfo);
//    if(taillist != null && taillist.size()>0){
//      throw new BusinessException("该单位已经存在同年月未入账的记录不能进行补缴！");
//    }
    //补缴年月是否小于等于该单位的缴至年月中大的那个缴至年月
    //缴至年月
//    String maxMonth = monthPaymentHeadDAO.queryMaxMonth(orgid);
//    int iMonth = Integer.parseInt(payMonth);
//    if(maxMonth != null){
//      int iMaxMonth = Integer.parseInt(maxMonth);
//      if(iMonth > iMaxMonth){
//        throw new BusinessException("该单位不能进行"+maxMonth.substring(0,4)+"年"+maxMonth.substring(4,maxMonth.length())+"月以后的补缴！");
//      }
//    }
    OrgaddpayHeadImportDTO orgaddpayHeadImportDto = (OrgaddpayHeadImportDTO)addpayHeadImportList.get(1);
    
    //判断单位编号是否相同
    if(!orgid.equals(orgaddpayHeadImportDto.getOrgid())){
      throw new BusinessException("选择的导入文件与输入的单位编号不符！");
    }
    //判断单位年月是否相同
    else if(!startPayMonth.equals(orgaddpayHeadImportDto.getAddStartPayMonth())){
      throw new BusinessException("选择的导入文件与输入的补缴起始年月不符！");
    }
    if(!payMonth.equals(orgaddpayHeadImportDto.getAddpayMonth())){
      throw new BusinessException("选择的导入文件与输入的补缴终止年月不符！");
    }
    //判断票据号
//    String notenumber=orgaddpayHeadImportDto.getNoteNum();
//    if(!notenumber.equals("")&&notenumber.length()>0){
//      Pattern pNotenumber = Pattern.compile("^[0-9]\\d*$");
//      Matcher mNotenumber = pNotenumber.matcher(notenumber);
//      if (mNotenumber.find() == false) {
//        throw new BusinessException("请录入正确票据号！");
//      }else if(notenumber.length()>20){
//        throw new BusinessException("票据号输入错误！");
//      }
//    }
    Org org = orgDAO.queryById(new Integer(orgaddpayHeadImportDto.getOrgid()));
    if(payment_orgname!=null){
      if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
        if(!org.getOrgInfo().getReserveaB().equals(payment_orgname)){
          org.getOrgInfo().setReserveaB(payment_orgname);
        }
      }else{
        if(!org.getOrgInfo().getName().equals(payment_orgname)){
          org.getOrgInfo().setReserveaB(payment_orgname);
        }
      }
    }
    for(int i=1;i<addpayListImportList.size();i++){
      OrgaddpayListImportDTO orgaddpayListImportDto=(OrgaddpayListImportDTO)addpayListImportList.get(i);
      UtilRule utilRule=new UtilRule();
   //   AddpayInfoDto ad = new AddpayInfoDto();
      if(orgaddpayListImportDto.getOrgAddpayMoney().equals("")){
        throw new BusinessException("第"+(i+3)+"行尚未输入单位补缴金额！");
      }
      if(orgaddpayListImportDto.getEmpAddpayMoney().equals("")){
        throw new BusinessException("第"+(i+3)+"行尚未输入职工补缴金额！");
      }
      Pattern pmoney = Pattern.compile("([0-9]{1,20})");
      Matcher empmoney1 = pmoney.matcher(orgaddpayListImportDto.getEmpAddpayMoney());
      Matcher orgmoney1 = pmoney.matcher(orgaddpayListImportDto.getOrgAddpayMoney());
      if (orgmoney1.find() == false) {
        throw new BusinessException("第"+(i+3)+"行输入的单位补缴金额有误！");
      }
      if (empmoney1.find() == false) {
        throw new BusinessException("第"+(i+3)+"行输入的职工补缴金额有误！");
      }
      if(orgaddpayListImportDto.getOrgAddpayMoney().equals("0")&&orgaddpayListImportDto.getEmpAddpayMoney().equals("0")){
        throw new BusinessException("第"+(i+3)+"行单位补缴金额和职工补缴金额不能均为0！");
      }
      if(Double.parseDouble(orgaddpayListImportDto.getOrgAddpayMoney())< 0 ){
        throw new BusinessException("第"+(i+3)+"行单位补缴金额不能小于0！");
      }
      if(Double.parseDouble(orgaddpayListImportDto.getEmpAddpayMoney())<0){
        throw new BusinessException("第"+(i+3)+"行职工补缴金额不能小于0！");
      }
      if(utilRule.moneyRule(orgaddpayListImportDto.getOrgAddpayMoney(), 15, 2)==false){
//      ad.setOrgAddpayMoney(orgaddpayListImportDto.getOrgAddpayMoney());
//      list.add(ad);
//      continue;
      throw new BusinessException("第"+(i+3)+"行输入的单位补缴金额有误！小数点后保留两位！");
    }
      if(utilRule.moneyRule(orgaddpayListImportDto.getEmpAddpayMoney(), 15, 2)==false){
//        ad.setEmpAddpayMoney(orgaddpayListImportDto.getEmpAddpayMoney());
//        list.add(ad);
//        continue;
        throw new BusinessException("第"+(i+3)+"行输入的职工补缴金额有误！小数点后保留两位！");
      }
      int iStartMonth = Integer.parseInt(orgaddpayListImportDto.getStartPayMonth());
      int iEndMonth = Integer.parseInt(orgaddpayListImportDto.getEndPayMonth());
      int iAddStartPayMonth = Integer.parseInt(orgaddpayHeadImportDto.getAddStartPayMonth());
      int iAddpayMonth = Integer.parseInt(orgaddpayHeadImportDto.getAddpayMonth());
      if(iStartMonth<iAddStartPayMonth){
        throw new BusinessException("第"+(i+3)+"行输入的职工起始年月有误！");
      }
      if(iEndMonth>iAddpayMonth){
        throw new BusinessException("第"+(i+3)+"行输入的职工终止年月有误！");
      }
      orgmoney = orgmoney.add(new BigDecimal(orgaddpayListImportDto.getOrgAddpayMoney()));
      empmoney = empmoney.add(new BigDecimal(orgaddpayListImportDto.getEmpAddpayMoney()));
      money = orgmoney.add(empmoney);
    }
    if(noteNum == null){
      noteNum=orgaddpayHeadImportDto.getNoteNum();
    }
    //判断两个时间的间隔月份第一个参数应该为较大日期，第二个参数为较小日期日期格式为YYYY-MM
    int monthCounts=BusiTools.getDisMonths(payMonth.substring(0,4)+"-"+payMonth.substring(4,6)+"-01",
        startPayMonth.substring(0,4)+"-"+startPayMonth.substring(4,6)+"-01");
    //插入301
    OrgAddPay orgAddPay = new OrgAddPay();
    orgAddPay.setOrg(org);
//    orgAddPay.setPayMoney(money.multiply(new BigDecimal(monthCounts+1)));
    orgAddPay.setPayMoney(money);
    orgAddPay.setNoteNum(noteNum);
    orgAddPay.setPayStatus(new Integer(1));
    orgAddPay.setReceivables_bank_acc(receivables_bank_acc);
    orgAddPay.setReceivables_bank_name(receivables_bank_name);
    orgAddPay.setPayment_bank_acc(payment_bank_acc);
    orgAddPay.setPayment_bank_name(payment_bank_name);
    orgAddPay.setPay_kind(payKind);
    orgAddPay.setPay_way(payWay);
    if(orgmoney.doubleValue()>0&&empmoney.doubleValue()>0){
      orgAddPay.setPayModel(new Integer(1));
    }else if(empmoney.doubleValue()>0){
      orgAddPay.setPayModel(new Integer(3));
    }else if(orgmoney.doubleValue()>0){
      orgAddPay.setPayModel(new Integer(2));
    }
    orgAddPayDAO.insert(orgAddPay);
    temp_centerheadid = orgAddPay.getId().toString();
    //循环插入302和303
    for(int i=0;i<=monthCounts;i++){
//    插入302
      MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
      monthPaymentHead.setPaymentHead(orgAddPay);
      monthPaymentHead.setPayMonth(BusiTools.addMonth(startPayMonth,i));
      monthPaymentHeadDAO.insert(monthPaymentHead);
//      //插入303
//        for(int j=1;j<addpayListImportList.size();j++){
//          OrgaddpayListImportDTO orgaddpayListImportDto=(OrgaddpayListImportDTO)addpayListImportList.get(j);
//          MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
//          UtilRule utilRule=new UtilRule();
//          AddpayInfoDto ad = new AddpayInfoDto();
//          if(utilRule.moneyRule(orgaddpayListImportDto.getEmpAddpayMoney(), 15, 2)==false){
//            ad.setEmpAddpayMoney(orgaddpayListImportDto.getEmpAddpayMoney());
//            list.add(ad);
//            continue;
//          }
//          if(utilRule.moneyRule(orgaddpayListImportDto.getOrgAddpayMoney(), 15, 2)==false){
//            ad.setOrgAddpayMoney(orgaddpayListImportDto.getOrgAddpayMoney());
//            list.add(ad);
//            continue;
//          }
//          if(utilRule.moneyRule(orgaddpayListImportDto.getOrgShouldpay(), 15, 2)==false){
//            ad.setOrgShouldpay(orgaddpayListImportDto.getOrgShouldpay());
//            list.add(ad);
//            continue;
//          }
//          if(utilRule.moneyRule(orgaddpayListImportDto.getEmpShouldpay(), 15, 2)==false){
//            ad.setEmpShouldpay(orgaddpayListImportDto.getEmpShouldpay());
//            list.add(ad);
//            continue;
//          }
//          //根据单位编号找到AA002中对应职工，判断导入文件中的职工是否和包含于这些职工中
//          Emp emp = empDAO.queryByCriterions(orgaddpayListImportDto.getEmpId(), orgid);
//          if(emp==null){
//            throw new BusinessException("导入的职工不存在于本单位");
//          }
//          monthPaymentTail.setEmpId(new Integer(orgaddpayListImportDto.getEmpId()));
//          monthPaymentTail.setEmpRealPay(new BigDecimal(orgaddpayListImportDto.getEmpAddpayMoney()));
//          monthPaymentTail.setEmpShouldPay(new BigDecimal(orgaddpayListImportDto.getEmpShouldpay()));
//          monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
//          monthPaymentTail.setOrgRealPay(new BigDecimal(orgaddpayListImportDto.getOrgAddpayMoney()));
//          monthPaymentTail.setOrgShouldPay(new BigDecimal(orgaddpayListImportDto.getOrgShouldpay()));
//          monthPaymentTail.setSalaryBase(emp.getSalaryBase());
//          monthPaymentTail.setOrgRate(emp.getOrg().getOrgRate());
//          monthPaymentTail.setEmpRate(emp.getOrg().getEmpRate());
//          monthPaymentTailDAO.insert(monthPaymentTail);
//        }
    }
    BigDecimal orgmonthpay = new BigDecimal(0.00);
    BigDecimal empmonthpay = new BigDecimal(0.00);
    BigDecimal orgPaySum = new BigDecimal(0.00);
    BigDecimal empPaySum = new BigDecimal(0.00);
    //插入AA303
    for (int i = 1; i < addpayListImportList.size(); i++) {
      OrgaddpayListImportDTO orgaddpayListImportDto=(OrgaddpayListImportDTO)addpayListImportList.get(i);
      UtilRule utilRule=new UtilRule();
      AddpayInfoDto ad = new AddpayInfoDto();
      if(utilRule.moneyRule(orgaddpayListImportDto.getEmpAddpayMoney(), 15, 2)==false){
        ad.setEmpAddpayMoney(orgaddpayListImportDto.getEmpAddpayMoney());
        list.add(ad);
        continue;
      }
      if(utilRule.moneyRule(orgaddpayListImportDto.getOrgAddpayMoney(), 15, 2)==false){
        ad.setOrgAddpayMoney(orgaddpayListImportDto.getOrgAddpayMoney());
        list.add(ad);
        continue;
      }
      if(utilRule.moneyRule(orgaddpayListImportDto.getOrgShouldpay(), 15, 2)==false){
        ad.setOrgShouldpay(orgaddpayListImportDto.getOrgShouldpay());
        list.add(ad);
        continue;
      }
      if(utilRule.moneyRule(orgaddpayListImportDto.getEmpShouldpay(), 15, 2)==false){
        ad.setEmpShouldpay(orgaddpayListImportDto.getEmpShouldpay());
        list.add(ad);
        continue;
      }
      //根据单位编号找到AA002中对应职工，判断导入文件中的职工是否和包含于这些职工中
      Emp emp = empDAO.queryByCriterions(orgaddpayListImportDto.getEmpId(), orgid);
      if(emp==null){
        throw new BusinessException("导入的职工不存在于本单位");
      }
      String beginMonth = orgaddpayListImportDto.getStartPayMonth();
      String endMonth = orgaddpayListImportDto.getEndPayMonth();
      int m = BusiTools.monthInterval(beginMonth, endMonth);
      BigDecimal j = new BigDecimal(new Integer(m + 1).toString());
      if (m == 0) {
        MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryMonthPaymentHead_jj(temp_centerheadid.toString(), beginMonth);
        monthPaymentHead.setReserveaA("1");//加一个标识，表示有头表有尾表，之后会把没有尾表的头表删掉
        MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
        monthPaymentTail.setEmpId(new Integer(orgaddpayListImportDto.getEmpId()));
        monthPaymentTail.setEmpRealPay(new BigDecimal(orgaddpayListImportDto.getEmpAddpayMoney()));
        monthPaymentTail.setEmpShouldPay(new BigDecimal(orgaddpayListImportDto.getEmpShouldpay()));
        monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
        monthPaymentTail.setOrgRealPay(new BigDecimal(orgaddpayListImportDto.getOrgAddpayMoney()));
        monthPaymentTail.setOrgShouldPay(new BigDecimal(orgaddpayListImportDto.getOrgShouldpay()));
        monthPaymentTail.setSalaryBase(new BigDecimal(orgaddpayListImportDto.getSalaryBase()));
        monthPaymentTail.setOrgRate(new BigDecimal(orgaddpayListImportDto.getOrgRate()));
        monthPaymentTail.setEmpRate(new BigDecimal(orgaddpayListImportDto.getEmpRate()));
        monthPaymentTailDAO.insert(monthPaymentTail);
      } else {
        orgPaySum = new BigDecimal(orgaddpayListImportDto.getOrgAddpayMoney());
        empPaySum = new BigDecimal(orgaddpayListImportDto.getEmpAddpayMoney());
        BigDecimal orgtemp = new BigDecimal(0.00);
        orgtemp = new BigDecimal(orgPaySum.doubleValue() % j.intValue());
        BigDecimal emptemp = new BigDecimal(0.00);
        emptemp = new BigDecimal(empPaySum.doubleValue() % j.intValue());
        orgmonthpay = (orgPaySum.subtract(orgtemp)).divide(j, 2,
            BigDecimal.ROUND_HALF_UP);
        empmonthpay = (empPaySum.subtract(emptemp)).divide(j, 2,
            BigDecimal.ROUND_HALF_UP);
        for (int k = 0; k < j.intValue(); k++) {
          MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryMonthPaymentHead_jj(temp_centerheadid.toString(), beginMonth);
          monthPaymentHead.setReserveaA("1");//加一个标识，表示有头表有尾表，之后会把没有尾表的头表删掉
          MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
          if (k != j.intValue() - 1) {
            monthPaymentTail.setOrgRealPay(orgmonthpay);
            monthPaymentTail.setEmpRealPay(empmonthpay);
          } else {
            monthPaymentTail.setOrgRealPay(orgmonthpay.add(orgtemp));
            monthPaymentTail.setEmpRealPay(empmonthpay.add(emptemp));
          }
          monthPaymentTail.setEmpId(new Integer(orgaddpayListImportDto.getEmpId()));
          monthPaymentTail.setEmpShouldPay(new BigDecimal(orgaddpayListImportDto.getEmpShouldpay()));
          monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
          monthPaymentTail.setOrgShouldPay(new BigDecimal(orgaddpayListImportDto.getOrgShouldpay()));
          monthPaymentTail.setSalaryBase(new BigDecimal(orgaddpayListImportDto.getSalaryBase()));
          monthPaymentTail.setOrgRate(new BigDecimal(orgaddpayListImportDto.getOrgRate()));
          monthPaymentTail.setEmpRate(new BigDecimal(orgaddpayListImportDto.getEmpRate()));
          monthPaymentTailDAO.insert(monthPaymentTail);
          beginMonth = BusiTools.addMonth(beginMonth, 1);
        }
      }
    }
      //删掉AA302中没有对应的尾表的记录
      monthPaymentHeadDAO.deleteMonthPaymentHead_jj(new Integer(temp_centerheadid));
      //插入319向AA319中插入业务活动日志。AA319.BIZID=AA301.ID,AA319.ACTION=1,AA319.TYPE=B
      OrgAddPayBizActivityLog addPayBizActivityLog = new OrgAddPayBizActivityLog();
      addPayBizActivityLog.setBizid(new Integer(orgAddPay.getId().toString()));
      addPayBizActivityLog.setAction(new Integer(1));
      addPayBizActivityLog.setOperator(securityInfo.getUserName());
      addPayBizActivityLog.setOpTime(new Date());
      orgAddPayBizActivityLogDAO.insert(addPayBizActivityLog);
      //插入BA003
      OrgaddpayHeadImportDTO orgaddpayHeadImportDto_1 = (OrgaddpayHeadImportDTO)addpayHeadImportList.get(0);
      String pickupFlag = orgaddpayHeadImportDto_1.getOrgName();
      //判断是否是提取数据调用的方法，若是批量导入，pickupFlag=“单位名称”，若是被提取数据调用，pickupFlag=null或“”，提取数据方法中自己插入BA003
      if(pickupFlag != null && pickupFlag !=""){
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZBLOG_ACTION_IMP));
      hafOperateLog.setOpBizId(new Integer(orgAddPay.getId().toString()));
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgid.toString()));
      hafOperateLogDAO.insert(hafOperateLog);
      }
    return list;
  }
  //查询单位补缴清册明细
  public OrgaddpayTaAF findOrgaddpayMX(Pagination pagination) throws Exception,BusinessException{
    List list=new ArrayList();
    List emplist=new ArrayList();
    List monthPaymentHeadList = null;
    MonthPaymentHead monthPaymentHead1 = new MonthPaymentHead();
    MonthPaymentHead monthPaymentHead2 = new MonthPaymentHead();
    MonthPaymentHead monthPaymentHeadtotal = new MonthPaymentHead();
    BigDecimal empSumpay = new BigDecimal(0.00);
    BigDecimal orgSumpay = new BigDecimal(0.00);
    BigDecimal addpayMoney = new BigDecimal(0.00);
    OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
    String personCount = "";//人数
    Serializable paymentid=(Serializable)pagination.getQueryCriterions().get("paymentid");
    String paymentid_1=paymentid.toString().trim();
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(paymentid_1.toString()));
    String orgid = orgAddPay.getOrg().getId().toString();
    list=monthPaymentTailDAO.queryOrgaddpayEmpListMXLJ(paymentid_1.toString(), orderBy, order, start, pageSize);
    if(list.size()>0){
       for(int i=0;i<list.size();i++){
         MonthPaymentTail m = (MonthPaymentTail)list.get(i);
         Emp emp = empDAO.queryByCriterions(m.getEmpId().toString(),orgid);
         m.setEmp(emp);
         emplist.add(m);
       }
     }
    int count = monthPaymentTailDAO.queryOrgaddpayEmpListCountMXLJ(paymentid_1.toString());
    personCount = monthPaymentTailDAO.countOrgAddPayPerson(paymentid_1.toString());//页面合计人数
    pagination.setNrOfElements(count);
    monthPaymentHeadList=monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentid_1.toString());
    monthPaymentHead1 = (MonthPaymentHead)monthPaymentHeadList.get(0); 
    monthPaymentHead2 = (MonthPaymentHead)monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentid_1.toString()).get(monthPaymentHeadList.size()-1); 
    orgaddpayTaAF.setNoteNum(orgAddPay.getNoteNum());
    orgaddpayTaAF.setDocNum(orgAddPay.getDocNum());
    for(int i=0;i<monthPaymentHeadList.size();i++){
      monthPaymentHeadtotal = (MonthPaymentHead)monthPaymentHeadList.get(i);
      empSumpay = empSumpay.add(monthPaymentHeadtotal.getEmpSumpay());
      orgSumpay = orgSumpay.add(monthPaymentHeadtotal.getOrgSumpay());
      addpayMoney = addpayMoney.add(monthPaymentHeadtotal.getAddpayMoney());
    }
    monthPaymentHeadtotal.setEmpSumpay(empSumpay);
    monthPaymentHeadtotal.setOrgSumpay(orgSumpay);
    monthPaymentHeadtotal.setAddpayMoney(addpayMoney);
    monthPaymentHeadtotal.setPersonCount(new Integer(personCount));
    orgaddpayTaAF.setList(emplist);
    orgaddpayTaAF.setMonthPaymentHead(monthPaymentHeadtotal);
    orgaddpayTaAF.setStartPayMonth(monthPaymentHead1.getPayMonth());
    orgaddpayTaAF.setPayMonth(monthPaymentHead2.getPayMonth());
    orgaddpayTaAF.setName(orgAddPay.getOrg().getOrgInfo().getName());
    orgaddpayTaAF.setOrgid(orgid);
    return orgaddpayTaAF;
    
   }
  public OrgaddpayHeadPrintDto findOrgaddpayPring(String paymentid) throws Exception{
    int count=0;
    OrgaddpayHeadPrintDto dto = new OrgaddpayHeadPrintDto();
    MonthPaymentHead monthPaymentHead1 = new MonthPaymentHead();
    MonthPaymentHead monthPaymentHead2 = new MonthPaymentHead();
    monthPaymentHead1 = (MonthPaymentHead)monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentid.toString()).get(0); 
    monthPaymentHead2 = (MonthPaymentHead)monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentid.toString()).get(monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentid.toString()).size()-1); 
    count = monthPaymentTailDAO.queryPaymentTailListLJ(paymentid).size();
    BigDecimal payMoney = new BigDecimal(0.00);
    BigDecimal tempmoney = new BigDecimal(monthPaymentHead1.getPaymentHead().getPayMoney().toString());
    payMoney = monthPaymentDAO.getPayMoney_jj(monthPaymentHead1.getPaymentHead().getNoteNum(), "D");
    dto.setDocNum(monthPaymentHead1.getPaymentHead().getDocNum());
    dto.setOrgId(monthPaymentHead1.getPaymentHead().getOrg().getId().toString());
    dto.setOrgName(monthPaymentHead1.getPaymentHead().getOrg().getOrgInfo().getName());
    dto.setPay(tempmoney.add(payMoney).toString());//冲掉挂账的钱
    dto.setStartPayMonth(monthPaymentHead1.getPayMonth());
    dto.setPayMonth(monthPaymentHead2.getPayMonth());
    dto.setPersonCounts(""+monthPaymentHead1.getPersonCount());
    dto.setPayKind(monthPaymentHead1.getPaymentHead().getPay_kind());
    dto.setPayWay(monthPaymentHead1.getPaymentHead().getPay_way());
    dto.setPayment_bank_acc(monthPaymentHead1.getPaymentHead().getPayment_bank_acc());
    dto.setPayment_bank_name(monthPaymentHead1.getPaymentHead().getPayment_bank_name());
    return dto;
  }
  public List findOrgaddpayListPring(String paymentid) throws Exception{
   List list = new ArrayList();
   List list1= new ArrayList();
   list = monthPaymentTailDAO.queryPaymentTailListLJ(paymentid);
   OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(paymentid));
   if(list.size()>0){
     for(int i=0;i<list.size();i++){
       MonthPaymentTail monthPaymentTail = (MonthPaymentTail)list.get(i);
       MonthpayTbWindowDto m = new MonthpayTbWindowDto();
       Emp emp = empDAO.queryByCriterions(monthPaymentTail.getEmpId().toString(), orgAddPay.getOrg().getId().toString());       
       m.setEmpStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
       m.setEmpid(monthPaymentTail.getEmpId().toString());
       m.setEmpName(monthPaymentTail.getEmpName());
       m.setEmppay(monthPaymentTail.getEmpRealPay().toString());
       m.setOrgpay(monthPaymentTail.getOrgRealPay().toString());
       m.setSumpay(monthPaymentTail.getSumPay().toString());
       m.setPayMonth(monthPaymentTail.getMonthPaymentHead().getPayMonth());
       m.setOrgid(orgAddPay.getOrg().getId().toString());
       m.setOrgname(orgAddPay.getOrg().getOrgInfo().getName());
       list1.add(m);
     }
   }
   return list1;
  }
  /*****
   * ww
   * @param orgid
   * @param headid
   * @param securityInfo
   * @throws Exception
   */
  //办理页面提交方法
  public void commit_Do(Serializable headid,SecurityInfo securityInfo) throws Exception,BusinessException{
    String type = BusiConst.ORGBUSINESSTYPE_B;
    MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headid.toString()));
    String paymentHeadId = monthPaymentHead.getPaymentHead().getId().toString();
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(paymentHeadId));
    String orgid = orgAddPay.getOrg().getId().toString();
    
    boolean exist = autoInfoPickDAODW.isNOPickIn(orgid,paymentHeadId,type);
    if(exist == false){
      //插入中心版DA001
      overAddpayCommitData(orgid,paymentHeadId,type);
      
      //插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
      hafOperateLog.setOpBizId(new Integer(paymentHeadId));
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgid));
      hafOperateLogDAO.insert(hafOperateLog);

    }else{
      throw new BusinessException("该记录已提交！");
    }
  }
  //维护页面提交方法
  public void commit_Maintain(String orgid,String headid,SecurityInfo securityInfo) throws Exception,BusinessException{
    String type = BusiConst.ORGBUSINESSTYPE_B;
    boolean exist = autoInfoPickDAODW.isNOPickIn(orgid,headid,type);
    if(exist == false){
      //插入中心版DA001
      overAddpayCommitData(orgid,headid,type);
      
      //插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN));
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
      hafOperateLog.setOpBizId(new Integer(headid));
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgid));
      hafOperateLogDAO.insert(hafOperateLog);
      
    }else{
      throw new BusinessException("该记录已提交！");
    }
  }
  //插入DA001提交信息的方法
  public void overAddpayCommitData(String orgid,String headid,String type) throws Exception{
    try{
//    插入中心版DA001
      AutoInfoPick autoInfoPick = new AutoInfoPick();
      Integer centerHeadId = null;
      Integer payHeadId = null;
      Integer orgheadid = new Integer(headid);
      
      autoInfoPick.setOrgId(new Integer(orgid));
      autoInfoPick.setOrgHeadId(orgheadid);
      autoInfoPick.setCenterHeadId(centerHeadId);
      autoInfoPick.setType(type);
      autoInfoPick.setStatus(new Integer(0).toString());
      autoInfoPick.setPayHeadId(payHeadId);
      autoInfoPick.setOrgBizDate(new Date());
      autoInfoPickDAODW.insert(autoInfoPick);
            
    }catch(Exception e){
      e.printStackTrace();
    }
      
  }
  //办理页面撤销提交方法
  public void cancel_Do(Serializable headid,SecurityInfo securityInfo) throws Exception,BusinessException{
    String type = BusiConst.ORGBUSINESSTYPE_B;
    MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headid.toString()));
    String paymentHeadId = monthPaymentHead.getPaymentHead().getId().toString();
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(paymentHeadId));
    String orgid = orgAddPay.getOrg().getId().toString();
    
    boolean exist = autoInfoPickDAODW.isNOPickIn(orgid,paymentHeadId,type);
    if(exist == true){
      String st = autoInfoPickDAODW.findStatus(orgid, paymentHeadId, type);
      if(st.equals(BusiConst.OC_NOT_PICK_UP)){
        //删除中心版DA001中该业务的记录
        autoInfoPickDAODW.deleteAutoInfoPick(orgid, paymentHeadId, type);
      }else{
        throw new BusinessException("该记录已被中心提取，不可以再撤销！");
      }
      
    }else{
      throw new BusinessException("该记录已撤销！");
    }
  }
  public void cancel_Maintain(String orgid,String headid,SecurityInfo securityInfo) throws Exception,BusinessException{
    String type = BusiConst.ORGBUSINESSTYPE_B;
        
    boolean exist = autoInfoPickDAODW.isNOPickIn(orgid,headid,type);
    if(exist == true){
      String st = autoInfoPickDAODW.findStatus(orgid, headid, type);
      if(st.equals(BusiConst.OC_NOT_PICK_UP)){
        //删除中心版DA001中该业务的记录
        autoInfoPickDAODW.deleteAutoInfoPick(orgid, headid, type);
      }else{
        throw new BusinessException("该记录已被中心提取，不可以再撤销！");
      }
      
    }else{
      throw new BusinessException("该记录已撤销！");
    }
  }
  //提取方法
  public void pickupdata(String orgid,String payMonth,String startPayMonth,String noteNum,SecurityInfo securityInfo,Pagination pagination) throws Exception,BusinessException{
    String org_head_id = "";
    org_head_id = autoInfoPickDAO.findOrgHeadid(orgid, BusiConst.ORGBUSINESSTYPE_B, BusiConst.OC_NOT_PICK_UP);
    if(org_head_id == "" || org_head_id == null){
      throw new BusinessException("该单位不存在未提取的单位补缴业务！");
    }
    
    List addpayHeadImportList = new ArrayList();
    List addpayListImportList = new ArrayList();
    
    OrgaddpayHeadImportDTO orgaddpayHeadImportDTO = new OrgaddpayHeadImportDTO();
    addpayHeadImportList.add(orgaddpayHeadImportDTO);
    orgaddpayHeadImportDTO.setOrgid(orgid);
    orgaddpayHeadImportDTO.setNoteNum(noteNum);
    orgaddpayHeadImportDTO.setAddpayMonth(payMonth);
    orgaddpayHeadImportDTO.setAddStartPayMonth(startPayMonth);
    addpayHeadImportList.add(orgaddpayHeadImportDTO);
    
    //判断输入的年月与提取的年月是否一致
    List month_list = new ArrayList();
    month_list = orgAddPayTailDAODW.queryOrgMonth(org_head_id);
    if(month_list.get(0).toString().equals(startPayMonth)==false ||month_list.get(month_list.size()-1).toString().equals(payMonth)==false){
      throw new BusinessException("输入的年月与提取数据不一致，"+month_list.get(0).toString()+"-"+month_list.get(month_list.size()-1).toString());
    }
    
    addpayListImportList = orgAddPayTailDAODW.queryOrgList(org_head_id,startPayMonth);
       
    List uselessFailList = new ArrayList();
    uselessFailList = addPaylistBatch(addpayHeadImportList,addpayListImportList,orgid,payMonth,startPayMonth,noteNum,securityInfo,pagination);
    
    //更新DA001
    autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP, temp_centerheadid, "newdata", orgid, org_head_id, BusiConst.ORGBUSINESSTYPE_B);
    
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
    hafOperateLog.setOpBizId(new Integer(temp_centerheadid));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgid));
    hafOperateLogDAO.insert(hafOperateLog);
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
/*
 * 根据办事处id查询办事处名称
 * (non-Javadoc)
 * @see org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS#queryOfficeName(java.lang.String)
 */
  public String queryOfficeName(String officeCode) throws Exception {
    // TODO Auto-generated method stub
    String officeName="";
    try{
      OrganizationUnit organizationUnit=new OrganizationUnit();
      organizationUnit=organizationUnitDAO.queryById(officeCode);
      if(organizationUnit!=null){
        if(organizationUnit.getName()!=null){
          officeName=organizationUnit.getName();
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return officeName;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public OrgHAFAccountFlow queryOrgHAFAccountFlow(String id) throws Exception {
    // TODO Auto-generated method stub
    return orgHAFAccountFlowDAO.queryById(new Integer(id));
  }

  public OrgHAFAccountFlow queryOrgHAFAccountFlow(String id, String bizType) throws Exception {
    // TODO Auto-generated method stub
    try{
      return orgHAFAccountFlowDAO.queryByBizId_wsh(id, bizType);
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
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
  
  public List findPaylistBatch(Serializable orgid,String payMonth,String startPayMonth,String noteNum,SecurityInfo securityInfo,String[] orderby) throws Exception{
    List emplist = new ArrayList();
    /*
    //录入的补缴年月在AA302中是否存在且AA301.PAY_STATUS<>5(包括汇缴补缴)
    List taillist = monthPaymentHeadDAO.queryOrgAddPayHeadInfoLJ(orgid, payMonth,securityInfo);
    if(taillist != null && taillist.size()>0){
      throw new BusinessException("该单位已经存在同年月未入账的记录不能进行补缴！");
    }
    //补缴年月是否小于等于该单位的缴至年月中大的那个缴至年月（已记账状态）
    //缴至年月
    String maxMonth = monthPaymentHeadDAO.queryMaxMonthOrgaddpay(orgid);
    //301里缴至年月为空
    String orgpayMonth="";
    String emppayMonth="";
    int iMaxMonth=0;
    int iPayMonth=0;
    int iMonth = Integer.parseInt(payMonth);
    if(maxMonth != null){
      iMaxMonth= Integer.parseInt(maxMonth);
      if(iMonth > iMaxMonth){
        throw new BusinessException("该单位不能进行"+maxMonth.substring(0,4)+"年"+maxMonth.substring(4,maxMonth.length())+"月以后的补缴！");
      }
    }else{
      List list = orgDAO.queryOrgaddPaymonth(orgid);
      if(list != null && list.size()>0){
        OrgaddpayMonthDTO dto=(OrgaddpayMonthDTO)list.get(0);
        orgpayMonth = dto.getOrgPaymonth();
        emppayMonth = dto.getEmpPaymonth();
        if(Integer.parseInt(orgpayMonth)>=Integer.parseInt(emppayMonth)){
          iPayMonth=Integer.parseInt(orgpayMonth);
        }else{
          iPayMonth=Integer.parseInt(emppayMonth);
        }
        if(iMonth > iPayMonth){
          throw new BusinessException("该单位不能进行"+Integer.toString(iPayMonth).substring(0,4)+"年"+Integer.toString(iPayMonth).substring(4,Integer.toString(iPayMonth).length())+"月以后的补缴！");
        }
      }
    }
    */
    /**
     * 修改jj20071217   ------开始-------
     */
    //当终止年月大于起始年月时，导出职工表中的记录
    if(Integer.parseInt(payMonth)>Integer.parseInt(startPayMonth)){
      //取002
     // List list2=empDAO.queryEmpByOrgIdSL(orgid.toString());
        List list2=empDAO.queryEmpByOrgIdSLSort(orgid.toString(), orderby);  // 导出排序
      if(list2.size()>0){
        for(int i=0; i<list2.size();i++){
          Emp emp = (Emp)list2.get(i);
          AddpayInfoDto addpayInfoDto = new AddpayInfoDto();
          addpayInfoDto.setOrgid(emp.getOrg().getId().toString());
          addpayInfoDto.setOrgName(emp.getOrg().getOrgInfo().getName());
          addpayInfoDto.setAddpayMonth(payMonth);
          //修改 -----开始
          addpayInfoDto.setAddStartPayMonth(startPayMonth);
          //-------结束
          addpayInfoDto.setEmpAddpayMoney("");
          addpayInfoDto.setEmpId(emp.getEmpId().toString());
          addpayInfoDto.setEmpName(emp.getEmpInfo().getName());
          addpayInfoDto.setEmpShouldpay(emp.getEmpPay().toString());
          addpayInfoDto.setNoteNum(noteNum);
          addpayInfoDto.setOrgAddpayMoney("");
          addpayInfoDto.setOrgShouldpay(emp.getOrgPay().toString());
          addpayInfoDto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
          addpayInfoDto.setSalaryBase(emp.getSalaryBase().toString());
          addpayInfoDto.setOrgRate(emp.getOrg().getOrgRate().toString());
          addpayInfoDto.setEmpRate(emp.getOrg().getEmpRate().toString());
          emplist.add(addpayInfoDto);
        }
      }
    }else{
      /**
       * jj修改结束
       */
      //录入的补缴年月在AA302中是否存在且AA301.PAY_STATUS=5
      List list = monthPaymentHeadDAO.queryMonthHeadInfoLJ(orgid, payMonth);
      if(list != null && list.size()>0){
        //取303
        MonthPaymentHead monthPaymentHead1=(MonthPaymentHead)list.get(0);
       // List list1 = monthPaymentTailDAO.queryPaymentTailListLJ_(monthPaymentHead1.getId());
        List list1 = monthPaymentTailDAO.queryPaymentTailListLP(monthPaymentHead1.getPaymentHead().getId(),orderby,monthPaymentHead1.getPaymentHead().getOrg().getId().toString());
        if(list1.size()>0){
          for(int i=0; i<list1.size();i++){
            MonthPaymentTail monthPaymentTail = (MonthPaymentTail)list1.get(i);
            AddpayInfoDto addpayInfoDto = new AddpayInfoDto();
           // Emp emp = empDAO.queryByCriterions(monthPaymentTail.getEmpId().toString(),monthPaymentTail.getMonthPaymentHead().getPaymentHead().getOrg().getId().toString());
            Emp emp=empDAO.queryByCriterionsSort(monthPaymentTail.getEmpId().toString(), monthPaymentTail.getMonthPaymentHead().getPaymentHead().getOrg().getId().toString(), orderby);
            addpayInfoDto.setOrgid(monthPaymentTail.getMonthPaymentHead().getPaymentHead().getOrg().getId().toString());
            addpayInfoDto.setOrgName(monthPaymentTail.getMonthPaymentHead().getPaymentHead().getOrg().getOrgInfo().getName());
            addpayInfoDto.setAddpayMonth(monthPaymentTail.getMonthPaymentHead().getPayMonth());
            //修改 -----开始
            addpayInfoDto.setAddStartPayMonth(monthPaymentTail.getMonthPaymentHead().getPayMonth());
            //-------结束
            addpayInfoDto.setEmpAddpayMoney("");
            addpayInfoDto.setEmpId(monthPaymentTail.getEmpId().toString());
            addpayInfoDto.setEmpName(monthPaymentTail.getEmpName());
            addpayInfoDto.setEmpShouldpay(monthPaymentTail.getEmpShouldPay().toString());
            addpayInfoDto.setNoteNum(noteNum);
            addpayInfoDto.setOrgAddpayMoney("");
            addpayInfoDto.setOrgShouldpay(monthPaymentTail.getOrgShouldPay().toString());
            addpayInfoDto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
            addpayInfoDto.setSalaryBase(monthPaymentTail.getSalaryBase().toString());
            addpayInfoDto.setOrgRate(monthPaymentTail.getOrgRate().toString());
            addpayInfoDto.setEmpRate(monthPaymentTail.getEmpRate().toString());
            emplist.add(addpayInfoDto);
          }
        }
      }else{
        //取002
       // List list2=empDAO.queryEmpByOrgIdSL(orgid.toString());
        List list2=empDAO.queryEmpByOrgIdLP(orgid.toString(),orderby);
        if(list2.size()>0){
          for(int i=0; i<list2.size();i++){
            Emp emp = (Emp)list2.get(i);
            AddpayInfoDto addpayInfoDto = new AddpayInfoDto();
            addpayInfoDto.setOrgid(emp.getOrg().getId().toString());
            addpayInfoDto.setOrgName(emp.getOrg().getOrgInfo().getName());
            addpayInfoDto.setAddpayMonth(payMonth);
            //修改 -----开始
            addpayInfoDto.setAddStartPayMonth(payMonth);
            //-------结束
            addpayInfoDto.setEmpAddpayMoney("");
            addpayInfoDto.setEmpId(emp.getEmpId().toString());
            addpayInfoDto.setEmpName(emp.getEmpInfo().getName());
            addpayInfoDto.setEmpShouldpay(emp.getEmpPay().toString());
            addpayInfoDto.setNoteNum(noteNum);
            addpayInfoDto.setOrgAddpayMoney("");
            addpayInfoDto.setOrgShouldpay(emp.getOrgPay().toString());
            addpayInfoDto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
            addpayInfoDto.setSalaryBase(emp.getSalaryBase().toString());
            addpayInfoDto.setOrgRate(emp.getOrg().getOrgRate().toString());
            addpayInfoDto.setEmpRate(emp.getOrg().getEmpRate().toString());
            emplist.add(addpayInfoDto);
          }
        }
      }      
    }
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_EXP));
    hafOperateLog.setOpBizId(null);
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgid.toString()));
    hafOperateLogDAO.insert(hafOperateLog);
    return emplist;
  }
  /*
   * 判断该单位是否存在AA301.PAY_STATUS=1 and AA301.PAY_TYPE=B（单位补缴）的记录
   */
  public String queryInfoByOrgId(String orgId) throws Exception{
    String id="";
    OrgAddPay o = orgAddPayDAO.queryOrgAddPayByOrgIdLJ(orgId, new Integer(1));
    if(o.getId()!=null){
        id=o.getId().toString();
    }
    return id;
  }
  public void addpayByNotenum(OrgaddpayTbPickupdataWindowAF af,SecurityInfo securityInfo) throws Exception {
    try{
      //通过票据编号查询301表的数据
      OrgAddPay orgAddPay = orgAddPayDAO.queryOrgAddPayByNotenum(af.getNoteNum());
      //通过301表id查询汇缴头表的其中一条信息
      List monthPaymentHeadList=monthPaymentHeadDAO.queryByPaymentHeadId(orgAddPay.getId());
      MonthPaymentHead head=(MonthPaymentHead)monthPaymentHeadList.get(0);
      //通过汇缴头表id查询汇缴尾表清册
      List monthPaymentTailList=monthPaymentTailDAO.queryPaymentTailListLJ_(head.getId());
      //判断两个时间的间隔月份第一个参数应该为较大日期，第二个参数为较小日期日期格式为YYYY-MM
      int monthCounts=BusiTools.getDisMonths(af.getAddpayDateEnd().substring(0,4)+"-"+af.getAddpayDateEnd().substring(4,6)+"-01",
          af.getAddpayDateSt().substring(0,4)+"-"+af.getAddpayDateSt().substring(4,6)+"-01");
      //插入301
      OrgAddPay orgAddPay1 = new OrgAddPay();
      orgAddPay1.setOrg(orgAddPay.getOrg());
      orgAddPay1.setPayMoney(orgAddPay.getPayMoney());
      orgAddPay1.setNoteNum(af.getNoteNumB());
      orgAddPay1.setPayStatus(new Integer(1));
      orgAddPay1.setPayModel(orgAddPay.getPayModel());
      Serializable paymentHeadId=orgAddPayDAO.insert(orgAddPay1);
      //循环插入302和303
      for(int i=0;i<=monthCounts;i++){
        //插入302
        MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
        monthPaymentHead.setPaymentHead(orgAddPay1);
        monthPaymentHead.setPayMonth(BusiTools.addMonth(af.getAddpayDateSt(),i));
        monthPaymentHeadDAO.insert(monthPaymentHead);
        //插入303
          for(int j=0;j<monthPaymentTailList.size();j++){
            MonthPaymentTail monthPaymentTail =(MonthPaymentTail)monthPaymentTailList.get(j);
            MonthPaymentTail monthPaymentTail1 = new MonthPaymentTail();
            //根据单位编号找到AA002中对应职工，判断导入文件中的职工是否和包含于这些职工中
            Emp emp = empDAO.queryByCriterions(monthPaymentTail.getEmpId().toString(), orgAddPay.getOrg().getId().toString());
            if(emp==null){
              throw new BusinessException("职工编号为"+monthPaymentTail.getEmpId().toString()+"职工不存在于本单位");
            }
            monthPaymentTail1.setEmpId(monthPaymentTail.getEmpId());
            monthPaymentTail1.setEmpRealPay(monthPaymentTail.getEmpRealPay());
            monthPaymentTail1.setEmpShouldPay(monthPaymentTail.getEmpShouldPay());
            monthPaymentTail1.setMonthPaymentHead(monthPaymentHead);
            monthPaymentTail1.setOrgRealPay(monthPaymentTail.getOrgRealPay());
            monthPaymentTail1.setOrgShouldPay(monthPaymentTail.getOrgShouldPay());
            monthPaymentTailDAO.insert(monthPaymentTail1);
          }
      }
      //插入319向AA319中插入业务活动日志。AA319.BIZID=AA301.ID,AA319.ACTION=1,AA319.TYPE=B
      OrgAddPayBizActivityLog addPayBizActivityLog = new OrgAddPayBizActivityLog();
      addPayBizActivityLog.setBizid(new Integer(paymentHeadId.toString()));
      addPayBizActivityLog.setAction(new Integer(1));
      addPayBizActivityLog.setOperator(securityInfo.getUserName());
      addPayBizActivityLog.setOpTime(new Date());
      orgAddPayBizActivityLogDAO.insert(addPayBizActivityLog);
      //插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO));
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZBLOG_ACTION_IMP));
      hafOperateLog.setOpBizId(new Integer(paymentHeadId.toString()));
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgAddPay.getOrg().getId().toString()));
      hafOperateLogDAO.insert(hafOperateLog);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  //刘聃修改：根据302payheadid查询302里的id
  public String findIDld(String head_ID) {
    String  id = orgAddPayDAO.findIdTemp(head_ID);  
    return id;
  }
  public boolean findld(Serializable tailId,Serializable headId,SecurityInfo securityInfo) throws Exception{
    boolean flag=true;
    try{
      MonthPaymentHead monthPaymentHead = monthPaymentHeadDAO.queryById(new Integer(headId.toString()));
    List empid = monthPaymentTailDAO.queryOrgaddpayListing_jj(monthPaymentHead.getPaymentHead().getId(), tailId.toString());
    if(empid.size()==0){
      flag=false;
    }
    else{
      flag=true;
    }
    }catch(Exception e){e.printStackTrace();}
   
return flag;
  }
//刘聃修改：判断缴存状态
  public boolean overAddpaya(Serializable headId, SecurityInfo securityInfo){
    boolean flag=true;
  try{
    String id = monthPaymentHeadDAO.queryById(new Integer(headId.toString())).getPaymentHead().getId().toString();
    String statue = monthPaymentHeadDAO.queryStatusById(id);
   if(statue.equals("2")){
     flag=true;
   }

   else{
     flag=false;
   }
  }catch(Exception e){
    e.printStackTrace();
  }
 
   return flag;
 }
  
  public boolean findld1(Serializable orgaddpayId,SecurityInfo securityInfo) throws Exception{
    boolean flag=true;
    try{
      List taillist=monthPaymentTailDAO.queryPaymentTailListLJ(orgaddpayId);
    if(taillist.size()==0){
      flag=false;
    }
    else{
      flag=true;
    }
    }catch(Exception e){e.printStackTrace();}
   
return flag;
  }
//吴洪涛 2008-6-16//查询出银行名称
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
        collBank = empDAO.getCollBankByCollBankid(collectionBankId);
      }
      if (collBank == null) {
        collBank = new CollBank();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBank.getCollBankName();
  }
  public MonthpayJYAF findPayInfo(String paymentId) throws Exception {
    MonthpayJYAF f = new MonthpayJYAF();
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(paymentId.toString()));
    f.setPayment_bank_acc(orgAddPay.getPayment_bank_acc());
    f.setPayment_bank_name(orgAddPay.getPayment_bank_name());
    f.setOrgid(orgAddPay.getOrg().getId().toString());
    f.setName(orgAddPay.getOrg().getOrgInfo().getName());
    f.setMonthpayHeadId(orgAddPay.getId().toString());
    return f;
  }
  public void updatePaymentInfo(Serializable paymentId,String bankName,String bankAcc,SecurityInfo securityInfo) throws Exception{
    //删除301
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(paymentId.toString()));
    orgAddPay.setPayment_bank_name(bankName);
    orgAddPay.setPayment_bank_acc(bankAcc);
  }
}