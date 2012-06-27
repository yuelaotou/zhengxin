package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.business;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.enums.OrderEnum;
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
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountReportDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountAdjustOtherDAO;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountAdjustPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountAdjustPickDAO;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountTailDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChangeAccountBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowAdjustOtherDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowAdjustPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowAdjustPickDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.SpecialPickDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustOther;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustPick;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeAccountBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustOther;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustPick;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/** 
 * 
 * @author 李鹏
 *2007-6-27
 */ 
public class AdjustAccountBS implements IAdjustAccountBS{

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;
  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;
  private AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO = null;
  private AdjustWrongAccountTailDAO adjustWrongAccountTailDAO = null;
  private EmpDAO empDAO = null;
  private OrgDAO orgDAO = null;
  private CollParaDAO collParaDAO = null;
  private BizActivityLogDAO bizActivityLogDAO=null;
  private AdjustWrongAccountAdjustPaymentDAO adjustWrongAccountAdjustPaymentDAO=null;
  private AdjustWrongAccountAdjustPickDAO adjustWrongAccountAdjustPickDAO=null;
  private AdjustWrongAccountAdjustOtherDAO adjustWrongAccountAdjustOtherDAO=null;
  private ChangeAccountBizActivityLogDAO changeAccountBizActivityLogDAO=null;
  private DocNumMaintainDAO docNumMaintainDAO=null;
  private DocNumCancelDAO docNumCancelDAO=null;
  private OrgHAFAccountFlowAdjustPaymentDAO orgHAFAccountFlowAdjustPaymentDAO=null;
  private OrgHAFAccountFlowAdjustPickDAO orgHAFAccountFlowAdjustPickDAO=null;
  private OrgHAFAccountFlowAdjustOtherDAO orgHAFAccountFlowAdjustOtherDAO=null;
  private HafOperateLogDAO hafOperateLogDAO=null; 
  private SpecialPickDAO specialPickDAO=null;
  private TranOutHeadDAO tranOutHeadDAO=null;
  private OrganizationUnitDAO organizationUnitDAO=null;
  private CollBankDAO collBankDAO=null;
  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }
  /**
   * 办理错账显示列表方法
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */   
  public AdjustaccountShowAF findAdjustWrongAccountHeadAndTailIDByPagination(String temp_adjustWrongAccountHead_id,Pagination pagination,String temp_type,SecurityInfo securityInfo) throws Exception,BusinessException{
    AdjustaccountDTO adjustaccountDTO=null;
    AdjustaccountShowAF adjustaccountShowAF=new AdjustaccountShowAF();
    String bizDate = securityInfo.getUserInfo().getBizDate();//业务日期
    //系统自动生成结算号：业务日期+流水号
    String noteNum1 = "";
    if("1".equals(temp_type)){ //双击错账凭证编号  进入  有状态等于5的记录
      adjustaccountDTO= this.findAdjustAccountListByCriterions(pagination);
      String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.id");
      String orgName=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name");
      String docNum=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.docNum");
      String noteNum=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.noteNum");
      String dates=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.settDate");
      noteNum1 = bizDate+orgDAO.queryNoteNum();
      adjustaccountShowAF.setList(adjustaccountDTO.getList());  
      adjustaccountShowAF.setBizDocNum(docNum);  
      adjustaccountShowAF.setBizNoteNum(noteNum);
      adjustaccountShowAF.setDate(dates);
      adjustaccountShowAF.setAdjustAccountlist(null);
      adjustaccountShowAF.setOrgId(orgId);
      adjustaccountShowAF.setOrgName(orgName);
      adjustaccountShowAF.setTotal(adjustaccountDTO.getTotal());
      adjustaccountShowAF.setType("1");
      adjustaccountShowAF.setNoteNum(adjustaccountDTO.getNoteNum());
      if("".equals(adjustaccountDTO.getNoteNum()) || adjustaccountDTO.getNoteNum() == null){
        adjustaccountShowAF.setNoteNum(noteNum1);
      }
      pagination.getQueryCriterions().put("pageList", adjustaccountDTO.getList1());
    }
    else if("2".equals(temp_type)){ //输入单位编号  进入
      String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.orgId");
      String orgName=this.findOrgInfoById(orgId,securityInfo);
      adjustaccountDTO=this.findEmpHAFAccountFlowListById(orgId,pagination,securityInfo);
      noteNum1 = bizDate+orgDAO.queryNoteNum();
      adjustaccountShowAF.setAdjustAccountlist(adjustaccountDTO.getList());  
      adjustaccountShowAF.setBizDocNum("");  
      adjustaccountShowAF.setBizNoteNum("");
      adjustaccountShowAF.setDate("");
      adjustaccountShowAF.setOrgId(orgId);
      adjustaccountShowAF.setOrgName(orgName);
      adjustaccountShowAF.setTotal(adjustaccountDTO.getTotal());
      adjustaccountShowAF.setType("2");
      adjustaccountShowAF.setNoteNum(adjustaccountDTO.getNoteNum());
      if("".equals(adjustaccountDTO.getNoteNum()) || adjustaccountDTO.getNoteNum() == null){
        adjustaccountShowAF.setNoteNum(noteNum1);
      }
      pagination.getQueryCriterions().put("pageList", adjustaccountDTO.getList1());//提取所有记录
    }else if("3".equals(temp_type)){ //没有数据
//      String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.orgId");
//      String orgName=this.findOrgInfoById(orgId,securityInfo);
      adjustaccountShowAF.setOrgId("");
      adjustaccountShowAF.setOrgName("");
      adjustaccountShowAF.setType("3");
    }else if("0".equals(temp_type)){//在101里没有该凭证号
      adjustaccountShowAF.setOrgId("");
      adjustaccountShowAF.setOrgName("");
      adjustaccountShowAF.setAdjustAccountlist(new ArrayList());
      adjustaccountShowAF.setType("");
    }else if("5".equals(temp_type)){
      adjustaccountShowAF.setOrgId("");
      adjustaccountShowAF.setOrgName("");
      adjustaccountShowAF.setAdjustAccountlist(new ArrayList());
      adjustaccountShowAF.setType("");
      throw new BusinessException("该单位存在未记账的调整业务");
    }else if("6".equals(temp_type)){
      adjustaccountShowAF.setOrgId("");
      adjustaccountShowAF.setOrgName("");
      adjustaccountShowAF.setAdjustAccountlist(new ArrayList());
      adjustaccountShowAF.setType("");
      throw new BusinessException("该业务不是缴存业务，不可以进行批量错账调整");
    }else if("7".equals(temp_type)){
      adjustaccountShowAF.setOrgId("");
      adjustaccountShowAF.setOrgName("");
      adjustaccountShowAF.setAdjustAccountlist(new ArrayList());
      adjustaccountShowAF.setType("");
      throw new BusinessException("该凭证号业务尚未记账或不存在该凭证号！");
    }else if("10".equals(temp_type)){ //单位弹出框
      String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.orgId");
      String orgName=this.findOrgInfoById(orgId,securityInfo);
      noteNum1 = bizDate+orgDAO.queryNoteNum();
      adjustaccountShowAF.setOrgId(orgId);
      adjustaccountShowAF.setOrgName(orgName);
      adjustaccountShowAF.setType("3");
      adjustaccountShowAF.setNoteNum(noteNum1);
    }else if("11".equals(temp_type)){ //完成调整用
//      String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.orgId");
//      String orgName=this.findOrgInfoById(orgId,securityInfo);
      adjustaccountShowAF.setOrgId("");
      adjustaccountShowAF.setOrgName("");
      adjustaccountShowAF.setType("");
    }else if("4".equals(temp_type)){
    String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.orgId");
    String orgName=this.findOrgInfoById(orgId,securityInfo);
    adjustaccountDTO=this.findEmpHAFAccountFlowListById(orgId,pagination,securityInfo);
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
//    this.insertHafOperateLog(temp_adjustWrongAccountHead_id,orgId,ip,oper);
    //更改过,OP_BUTTON=2，OP_BIZ_ID=该笔业务AA314.ID
    HafOperateLog hafOperateLog=new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN).toString());
//    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_QUERY)
//    .toString());
    // 付云峰修改：OP_BUTTON=2
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_MODIFY)
    .toString());
    hafOperateLog.setOpBizId(new Integer(temp_adjustWrongAccountHead_id));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgId));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);
    adjustaccountShowAF.setAdjustAccountlist(adjustaccountDTO.getList());  
    adjustaccountShowAF.setBizDocNum("");  
    adjustaccountShowAF.setBizNoteNum("");
    adjustaccountShowAF.setDate("");
    adjustaccountShowAF.setOrgId(orgId);
    adjustaccountShowAF.setOrgName(orgName);
    adjustaccountShowAF.setTotal(adjustaccountDTO.getTotal());
    adjustaccountShowAF.setType("4");
    adjustaccountShowAF.setNoteNum(adjustaccountDTO.getNoteNum());
    if("".equals(adjustaccountDTO.getNoteNum()) || adjustaccountDTO.getNoteNum() == null){
      adjustaccountShowAF.setNoteNum(noteNum1);
    }
    pagination.getQueryCriterions().put("pageList", adjustaccountDTO.getList1());//提取所有记录
    }
    return adjustaccountShowAF;
  }
  /**
   * 办理错账显示列表方法
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */   
  public AdjustaccountShowAF findAdjustWrongAccountHeadAndTailIDByPagination_sy(String temp_adjustWrongAccountHead_id,Pagination pagination,String temp_type,SecurityInfo securityInfo) throws BusinessException{
    AdjustaccountDTO adjustaccountDTO=null;
    AdjustaccountShowAF adjustaccountShowAF=new AdjustaccountShowAF();
    try{
      String bizDate = securityInfo.getUserInfo().getBizDate();//业务日期
      //系统自动生成结算号：业务日期+流水号
      String noteNum1 = "";
     if("2".equals(temp_type)){ //输入单位编号  进入
      String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.orgId");
      String orgName=this.findOrgInfoById(orgId,securityInfo);
      adjustaccountDTO=this.findEmpHAFAccountFlowListById(orgId,pagination,securityInfo);
      noteNum1 = bizDate+orgDAO.queryNoteNum();
      adjustaccountShowAF.setAdjustAccountlist(adjustaccountDTO.getList());  
      adjustaccountShowAF.setBizDocNum("");  
      adjustaccountShowAF.setBizNoteNum("");
      adjustaccountShowAF.setDate("");
      adjustaccountShowAF.setOrgId(orgId);
      adjustaccountShowAF.setOrgName(orgName);
      adjustaccountShowAF.setTotal(adjustaccountDTO.getTotal());
      adjustaccountShowAF.setType("2");
      adjustaccountShowAF.setNoteNum(adjustaccountDTO.getNoteNum());
      if("".equals(adjustaccountDTO.getNoteNum()) || adjustaccountDTO.getNoteNum() == null){
        adjustaccountShowAF.setNoteNum(noteNum1);
      }
      pagination.getQueryCriterions().put("pageList", adjustaccountDTO.getList1());//提取所有记录
    }else if("4".equals(temp_type)){
    String orgId=(String)pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.orgId");
    String orgName=this.findOrgInfoById(orgId,securityInfo);
    adjustaccountDTO=this.findEmpHAFAccountFlowListById(orgId,pagination,securityInfo);
    noteNum1 = bizDate+orgDAO.queryNoteNum();
    adjustaccountShowAF.setAdjustAccountlist(adjustaccountDTO.getList());  
    adjustaccountShowAF.setBizDocNum("");  
    adjustaccountShowAF.setBizNoteNum("");
    adjustaccountShowAF.setDate("");
    adjustaccountShowAF.setOrgId(orgId);
    adjustaccountShowAF.setOrgName(orgName);
    adjustaccountShowAF.setTotal(adjustaccountDTO.getTotal());
    adjustaccountShowAF.setType("4");
    adjustaccountShowAF.setNoteNum(adjustaccountDTO.getNoteNum());
    if("".equals(adjustaccountDTO.getNoteNum()) || adjustaccountDTO.getNoteNum() == null){
      adjustaccountShowAF.setNoteNum(noteNum1);
    }
    pagination.getQueryCriterions().put("pageList", adjustaccountDTO.getList1());//提取所有记录
    }
    }catch(BusinessException bx){
      throw bx;
    }catch(Exception e){
      e.printStackTrace();
    }
    return adjustaccountShowAF;
  }
  public AdjustaccountShowAF findAdjustWrongAccount_sy(String ids,Pagination pagination)throws BusinessException{
  AdjustaccountShowAF adjustaccountShowAF=new AdjustaccountShowAF();
  String orderBy=(String) pagination.getOrderBy();
  String order = (String) pagination.getOrderother();
  int start = pagination.getFirstElementOnPage() - 1;
  int pageSize = pagination.getPageSize(); 
  int page =pagination.getPage();
  List adjustaccountList=new ArrayList();
  List adjustaccountList1=new ArrayList();
  List adjustaccountList2=new ArrayList();
  try{
    AdjustWrongAccountHead adjustWrongAccountHead=adjustWrongAccountHeadDAO.queryById(new Integer(ids));
    if(adjustWrongAccountHead!=null){
      //用于分页
      adjustaccountList=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailListByCriterions(adjustWrongAccountHead.getId()+"",orderBy,order,start,pageSize,page);
      //用于计算,查询出所有的记录头尾表关联.
      adjustaccountList2=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(adjustWrongAccountHead.getId()+"");
      int count=0;
      if(!adjustaccountList2.isEmpty()){
        count=adjustaccountList2.size();
      }
      String temp_orgid=adjustWrongAccountHead.getOrg().getId().toString();
      Org orgs=orgDAO.queryById(new Integer(temp_orgid));
      pagination.setNrOfElements(count);
      adjustaccountShowAF.setTotal(adjustWrongAccountHead.getTotal());
      adjustaccountShowAF.setBizDocNum(adjustWrongAccountHead.getBizDocNum());  
      adjustaccountShowAF.setBizNoteNum(adjustWrongAccountHead.getBizNoteNum());
      adjustaccountShowAF.setDate(adjustWrongAccountHead.getBizDate());
      adjustaccountShowAF.setOrgId(temp_orgid);
      adjustaccountShowAF.setOrgName(orgs.getOrgInfo().getName());
      adjustaccountShowAF.setType("4");
      adjustaccountShowAF.setNoteNum(adjustWrongAccountHead.getReserveaC());
    }
    if(adjustaccountList.size()==0){
      throw new BusinessException("不存在记录");
    }else{
    for(int i=0;i<adjustaccountList.size();i++)
    {
      AdjustWrongAccountTail adjustWrongAccountTail=(AdjustWrongAccountTail)adjustaccountList.get(i);
      List list=empDAO.queryByCriterionsWZQ(adjustWrongAccountTail.getEmpId()+"",null);
      Emp emp=new Emp();
      emp=(Emp) list.get(0);
      adjustWrongAccountTail.setEmp(emp);
      adjustaccountList1.add(adjustWrongAccountTail);
    }
    }
    pagination.getQueryCriterions().put("adjustaccountList2", adjustaccountList2);
    adjustaccountShowAF.setAdjustAccountlist(adjustaccountList1);
  }catch(BusinessException bu){
    throw bu;
  }catch(Exception e){
    e.printStackTrace();
  }  
    return adjustaccountShowAF;  
  }
  /**
   * 判断员工是否在该单位下 
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */  
  public Emp checkEmp(String orgId,String empId) throws Exception,BusinessException{
    
    Emp emp=adjustWrongAccountHeadDAO.queryByOrgIdAndEmpId(orgId,empId);
    if (emp==null)
      throw new BusinessException("该单位没有该员工");
    return emp;
  }
  /**
   * 根据条件查询错账id(添加员工信息时候有用) 修改过
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */   
  public AdjustWrongAccountHead findAdjustWrongAccountHeadIDByCriterions(String orgId) throws Exception,BusinessException{
      AdjustWrongAccountHead adjustWrongAccountHead = adjustWrongAccountHeadDAO.queryByOrgId(orgId);
      return adjustWrongAccountHead;
  }
  /**
   * 判断员工是否已经被加载到315
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */   
  public boolean findHeadAndTailByCriterions(String orgId,String empId) throws Exception,BusinessException{
    
      boolean checks=true;
      List list = adjustWrongAccountHeadDAO.queryByEmpIdAndOrgId(orgId);
      for(int i=0;i<list.size();i++){
        AdjustWrongAccountTail adjustWrongAccountTail=(AdjustWrongAccountTail)list.get(i);
       if( empId.equals(adjustWrongAccountTail.getEmpId().toString())){
         checks=false;
         throw new BusinessException("该员工存在未记账的调整业务");
       }
      }
      return checks;
  }
  /**
   * 根据条件错账id和员工id 查询出该单位是否已经有过错账调整(添加员工信息时候有用)  
   * @param orgId  流水头表id
   * @param empId  员工id
   * @return Serializable
   * @throws BusinessException 
   */   
  public AdjustWrongAccountTail findAdjustWrongAccountTailByCriterions(String orgId,String empId) throws Exception,BusinessException{
    AdjustWrongAccountTail adjustWrongAccountTail=adjustWrongAccountTailDAO.queryByWrongId(orgId, empId); 
//    if(adjustWrongAccountTail==null){
//      throw new BusinessException("该员工存在未记账的调整业务");
//    }
    return adjustWrongAccountTail;
  }
  /**判断输入凭证号在错账里是否存在
   * 根据条件查询101表返回相应单位id
   * 根据条件单位id和状态不等于5查询返回实例
   * @param pagination
   * @return Serializable查询流水通过凭证号
   * @throws BusinessException 
   */  
  public List findOrgHAFAccountFlowByCriterions(String orgid,String bizdate,String bizDocNum,SecurityInfo securityInfo) throws Exception,BusinessException{
//    OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByCriterion(bizDocNum,bizdate,orgid);
//    AdjustWrongAccountHead adjustWrongAccountHead=null;
//    AdjustWrongAccountHead adjustWrongAccountHead1=null;
//    if(orgHAFAccountFlow!=null)
//    {
//      adjustWrongAccountHead = adjustWrongAccountHeadDAO.queryByIDLP(orgHAFAccountFlow.getOrg().getId(),securityInfo);
//      if(adjustWrongAccountHead==null){//证明该单位没有错账记录  在314里
//        Org org=orgDAO.queryByCriterions(orgHAFAccountFlow.getOrg().getId().toString(),"2", "", securityInfo);
//        if (org==null){//该操作员没有权限
//          throw new BusinessException("没有操作该单位的权限");
//        }
//      }else{//有错账记录
//        Org org=orgDAO.queryByCriterions(orgHAFAccountFlow.getOrg().getId().toString(),"2", "", securityInfo);
//        if (org==null){//该操作员没有权限
//          throw new BusinessException("没有操作该单位的权限");
//        }
//      }
//    }else{
//      throw new BusinessException("该凭证号并不存在");
//    }
    Org org=orgDAO.queryByCriterions(orgid, null, null, securityInfo);
    if(org==null){
      throw new BusinessException("没有操作此单位的权限！");
    }
    List adjustWrongAccountHead=null;
    adjustWrongAccountHead = adjustWrongAccountHeadDAO.queryByIDLP(orgid,securityInfo);
    if(adjustWrongAccountHead != null && adjustWrongAccountHead.size()>0){
      throw new BusinessException("此单位有未记账的错账调整业务！");
    }
    return adjustWrongAccountHead;
  }
  /**查询流水通过凭证号
   * @param bizDocNum
   * @return OrgHAFAccountFlow
   * @throws BusinessException 
   */  
//  public OrgHAFAccountFlow findOrgHAFAccountFlowByDocNumCriterions(String bizDocNum) throws Exception,BusinessException{
//    OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByDocNote(bizDocNum);
//    return  orgHAFAccountFlow;
//  }
  //修改过的
public OrgHAFAccountFlow findOrgHAFAccountFlowByDocNumCriterions(String bizDocNum,String bizdate,String orgid) throws Exception,BusinessException{
 OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByCriterion(bizDocNum,bizdate,orgid);
return  orgHAFAccountFlow;
}
  /**
   * 根据条件单位id查询返回流水id
   * @param pagination
   * @return Serializable
   * @throws BusinessException 
   */  
  public AdjustWrongAccountHead findAdjustWrongAccountHeadByOrgId(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException{
    AdjustWrongAccountHead  adjustWrongAccountHead = adjustWrongAccountHeadDAO.queryByOrgIdReAdjustWrongAccountHead(orgId,securityInfo);
    return adjustWrongAccountHead;//adjustWrongAccountHead.getId();
  }
  /**
   * 根据条件单位id和状态等于1查询返回实例
   * @param pagination
   * @return Serializable
   * @throws BusinessException 
   */   
  public AdjustWrongAccountHead findOrgHAFAccountFlowByOrgAndStatus(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException{
//    Org orgs=orgDAO.queryByCriterions(orgId,"2", "", securityInfo); 
//    if(orgs==null)
//      throw  new BusinessException("没有查看该单位的权限！！");
//  根据单位id查309表中有无此
    AdjustWrongAccountHead  adjustWrongAccountHead = adjustWrongAccountHeadDAO.queryByOrgId(orgId);
//    if(adjustWrongAccountHead==null)
//      throw  new BusinessException("没有查看该单位的权限！！");
    return adjustWrongAccountHead;
  }
  public String querySpecialPickAndTranOutHead(String orgId){
    String massage="";
    try{
    List a=specialPickDAO.queryTranOutHeadByOrgid(orgId);
    List b=tranOutHeadDAO.queryByOrgId(orgId);
    if(a.size()>0&&b.size()>0){
      massage= "存在未记账的提取和转出！！"; 
    }else if(!b.isEmpty()){
      massage="存在未记账的提取！！"; 
    }else if(!a.isEmpty()){
      massage="存在未记账的转出！！"; 
    }
    }catch(Exception e){
      e.printStackTrace();
    }
    return massage;
  }
  
  /**
   * 查看员工信息 
   * @param pagination
   * @return Serializable
   * @throws BusinessException 
   */  
  public Emp findEmpById(String id) throws Exception,BusinessException{
//    Emp emp=empDAO.queryByCriterions(id,null);
    //更改过,员工不是唯一.
    String temp=null;
    List list=empDAO.queryByCriterionsWZQ(id,temp);
    Emp emp=(Emp) list.get(0);
    return emp;
  }
  
  /**
   * 根据条件头表id查询（维护的完成调整）
   * @param pagination
   * @return Serializable
   * @throws BusinessException 
   */  
  public AdjustWrongAccountHead findOrgHAFAccountFlowById(String id) throws Exception,BusinessException{
    AdjustWrongAccountHead  adjustWrongAccountHead = adjustWrongAccountHeadDAO.queryById(new Integer(id));
    return adjustWrongAccountHead;
  }
  /**
   * 根据条件查询错账记录
   * @param pagination
   * @return
   * @throws BusinessException 
   */  
  public AdjustaccountDTO findAdjustAccountListByCriterions(Pagination pagination) throws Exception,BusinessException{
    String adjustaccount_date=(String) pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.settDate");
    String adjustaccount_docNum=(String) pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.docNum");
    String orgid=(String) pagination.getQueryCriterions().get("empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.id");
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    if("adjustWrongAccountTail.empId".equals(orderBy))
      orderBy="empHAFAccountFlow.orgHAFAccountFlow.docNum";
    else if("adjustWrongAccountTail.empName".equals(orderBy))
      orderBy="empHAFAccountFlow.orgHAFAccountFlow.docNum";
    else if("adjustWrongAccountTail.adjustMoney".equals(orderBy))
      orderBy="empHAFAccountFlow.orgHAFAccountFlow.docNum";
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    AdjustaccountDTO adjustaccountDTO=new AdjustaccountDTO();
    OrgHAFAccountFlow orgHAFAccountFlow=null;
    List empHAFAccountFlow_list=new ArrayList();   
    List empHAFAccountFlow_list1=new ArrayList(); 
    List adjustaccountList2=new ArrayList();
    if(adjustaccount_date!=null)
    {
      orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByCriterion(adjustaccount_docNum,adjustaccount_date,orgid);
      if(orgHAFAccountFlow!=null)
      { 
        adjustaccountDTO.setTotal(orgHAFAccountFlow.getTotal());
        empHAFAccountFlow_list=empHAFAccountFlowDAO.queryEmpHAFAccountFlowListByCriterions(orgHAFAccountFlow.getId()+"",orderBy,order,start,pageSize);
        adjustaccountList2=empHAFAccountFlowDAO.queryEmpHAFAccountFlowListAllByCriterions(orgHAFAccountFlow.getId()+"",orderBy,order,start,pageSize);
        int count = empHAFAccountFlowDAO.queryDemoCountByCriterions(orgHAFAccountFlow.getId()+"");
        pagination.setNrOfElements(count);
        adjustaccountDTO.setList1(adjustaccountList2);
      }else {
        throw new BusinessException("在当前日期没有该凭证号");
      }
    }
    if(empHAFAccountFlow_list.size()==0){
     // throw new BusinessException("不存在记录");
    }else{
    for(int i=0;i<empHAFAccountFlow_list.size();i++)
    {
      EmpHAFAccountFlow empHAFAccountFlow=(EmpHAFAccountFlow)empHAFAccountFlow_list.get(i);
      //旧文档
      //      Emp emp=empDAO.queryByCriterions(empHAFAccountFlow.getEmpId()+"",null);
      //    新更改，理解为获取emp信息，由于返回多个emp取其中一个的信息就够用了
      List list=empDAO.queryByCriterionsWZQ(empHAFAccountFlow.getEmpId()+"", null);
      Emp emp=(Emp) list.get(0);
      empHAFAccountFlow.setEmp(emp);
      empHAFAccountFlow.setCredit(new BigDecimal(empHAFAccountFlow.getCredit().toString()));
      empHAFAccountFlow_list1.add(empHAFAccountFlow);
    }
    }
    adjustaccountDTO.setList(empHAFAccountFlow_list1);
    return adjustaccountDTO;
   }
  
  
  
  /**
   * 根据单位id查询单位名称
   * @param orgId
   * @return
   * @throws BusinessException 
   */  
  public String findOrgInfoById(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException{
    Org orgs=orgDAO.queryByCriterions(orgId,"2", "", securityInfo); 
    if(orgs==null)
      throw  new BusinessException("没有查看该单位的权限！！");
    Org org=orgDAO.queryById(new Integer(orgId));
    if(org==null){//存在该职工
      throw  new BusinessException("没有该单位！！");
    }
    //输入单位编号时判断登录日期与单位所在的归集银行的归集银行表的日结日期是否一致，不一致不允许做业务
    String bizDate = securityInfo.getUserInfo().getBizDate();//业务日期
    String bankDate = "";
    bankDate = orgDAO.findAA103_DayTime(org.getOrgInfo().getCollectionBankId());
    if(!bizDate.equals(bankDate)){
      throw new BusinessException("登录日期与银行业务日期不一致，此单位不能做业务！");
    }
    return org.getOrgInfo().getName();
  }
  
  /**
   * 根据员工id查询员工信息
   * @param orgId
   * @return
   * @throws BusinessException 
   */  
  public Emp findEmpInfoByEmpId(Integer empId) throws Exception,BusinessException{
    Emp emp=empDAO.queryById(empId);
    return emp;
  }
  /**
   * 根据EmpHAFAccountFlow.orgHAFAccountFlow.id和orgHAFAccountFlow.id关联返回EmpHAFAccountFlow集合
   * @param orgId
   * @return
   * @throws BusinessException 
   */  
  public AdjustaccountDTO findEmpHAFAccountFlowListById(String orgId,Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException{
    AdjustaccountDTO adjustaccountDTO=new AdjustaccountDTO();  
    String orderBy=(String) pagination.getOrderBy();
//    OrderEnum oe=  pagination.getOrder();
//    String order=oe.getName();
    String order = (String) pagination.getOrderother();
    if("empHAFAccountFlow.orgHAFAccountFlow.docNum".equals(orderBy))
      orderBy="adjustWrongAccountTail.empId";
    else if("empHAFAccountFlow.empName".equals(orderBy))
      orderBy="adjustWrongAccountTail.empId";
    else if("empHAFAccountFlow.credit".equals(orderBy))
      orderBy="adjustWrongAccountTail.empId";
    else if("empHAFAccountFlow.empId".equals(orderBy))
      orderBy="adjustWrongAccountTail.empId";
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page =pagination.getPage();
    AdjustWrongAccountHead adjustWrongAccountHead=adjustWrongAccountHeadDAO.queryByOrgIdReAdjustWrongAccountHead(orgId, securityInfo);//获得流水ID
    List adjustaccountList=new ArrayList();
    List adjustaccountList1=new ArrayList();
    List adjustaccountList2=new ArrayList();
    if(adjustWrongAccountHead!=null){
      adjustaccountDTO.setTotal(adjustWrongAccountHead.getTotal());
      adjustaccountList=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailListByCriterions(adjustWrongAccountHead.getId()+"",orderBy,order,start,pageSize,page);
      adjustaccountList2=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(adjustWrongAccountHead.getId()+"");
      int count = adjustWrongAccountTailDAO.queryAdjustWrongAccountTailCountByCriterions(adjustWrongAccountHead.getId()+"");
      pagination.setNrOfElements(count);
      adjustaccountDTO.setList1(adjustaccountList2);//所有记录
      adjustaccountDTO.setNoteNum(adjustWrongAccountHead.getReserveaC());
    }
    if(adjustaccountList.size()==0){
      throw new BusinessException("不存在记录");
    }else{
    for(int i=0;i<adjustaccountList.size();i++)
    {
      AdjustWrongAccountTail adjustWrongAccountTail=(AdjustWrongAccountTail)adjustaccountList.get(i);
      //旧文档-
//      Emp emp=empDAO.queryByCriterions(adjustWrongAccountTail.getEmpId()+"",null);
      //新更改，理解为获取emp信息，由于返回多个emp取其中一个的信息就够用了
      List list=empDAO.queryByCriterionsWZQ(adjustWrongAccountTail.getEmpId()+"",null);
      Emp emp=(Emp) list.get(0);
      adjustWrongAccountTail.setEmp(emp);
      adjustaccountList1.add(adjustWrongAccountTail);
    }
    }
    adjustaccountDTO.setList(adjustaccountList1);
    return adjustaccountDTO;
  }
  
  /**
   * 插入尾表
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */  
  public Serializable insertAdjustWrongAccountTail(AdjustWrongAccountTail adjustWrongAccountTail,Integer empId,AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo,String orgId) throws Exception,BusinessException{
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    Emp emp = empDAO.queryById(empId);
    adjustWrongAccountTail.setEmp(emp);
    adjustWrongAccountTail.setAdjustWrongAccountHead(adjustWrongAccountHead);
    AdjustWrongAccountTail adjustWrongAccountTail2=adjustWrongAccountTailDAO.queryByWrongId(""+adjustWrongAccountHead.getId(), ""+empId); 
//    if(adjustWrongAccountTail2!=null)
//    System.out.println("不能插入；已经存在");
//    else System.out.println("能插入；不存在");
    Serializable ids=adjustWrongAccountTailDAO.insert(adjustWrongAccountTail);
    String flowid=adjustWrongAccountHead.getId().toString();
    HafOperateLog hafOperateLog=new HafOperateLog();
//    hafOperateLog.setOpSys(new Integer(1));
//    hafOperateLog.setOpModel("错账调整");
//    hafOperateLog.setOpButton("添加");
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_ADD)
    .toString());
    hafOperateLog.setOpBizId(new Integer(flowid.toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgId));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);
    
    return ids;
  }
  
  /**
   * 插入头尾表
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */  
  public Serializable insertAdjustWrongAccountHeadAndTail(AdjustWrongAccountTail adjustWrongAccountTail,Integer empId,String orgId,String type,SecurityInfo securityInfo,String noteNum) throws Exception,BusinessException{
    Serializable adjustWrongAccountHeadID=null;
    String ip = securityInfo.getUserInfo().getUserIp();
    //String officeid=securityInfo.getUserInfo().getOfficeId().toString();
    String oper = securityInfo.getUserInfo().getUsername();
    String bizDate = securityInfo.getUserInfo().getBizDate();
    bizDate=bizDate.substring(0,6);
    Emp emp = empDAO.queryById(empId);
    adjustWrongAccountTail.setEmp(emp);
    Org orgs = orgDAO.queryById(new Integer(orgId));
//    String docNum=docNumMaintainDAO.getDocNumdocNum(officeid,bizDate);
    if("K".equals(type.toUpperCase()))
    {
      AdjustWrongAccountAdjustPayment adjustWrongAccountAdjustPayment = new AdjustWrongAccountAdjustPayment();
      adjustWrongAccountAdjustPayment.setOrg(orgs);
 //     adjustWrongAccountAdjustPayment.setDocNum(docNum);
      adjustWrongAccountAdjustPayment.setAdjustStatus(new BigDecimal(1.00));
      adjustWrongAccountAdjustPayment.setReserveaC(noteNum);
      adjustWrongAccountHeadID =adjustWrongAccountAdjustPaymentDAO.insert(adjustWrongAccountAdjustPayment);
      adjustWrongAccountTail.setAdjustWrongAccountHead(adjustWrongAccountAdjustPayment);
      adjustWrongAccountTailDAO.insert(adjustWrongAccountTail);
    }else if("L".equals(type.toUpperCase()))
    {
      AdjustWrongAccountAdjustPick adjustWrongAccountAdjustPick = new AdjustWrongAccountAdjustPick();
      adjustWrongAccountAdjustPick.setOrg(orgs);
 //     adjustWrongAccountAdjustPick.setDocNum(docNum);
      adjustWrongAccountAdjustPick.setAdjustStatus(new BigDecimal(1.00));
      adjustWrongAccountAdjustPick.setReserveaC(noteNum);
      adjustWrongAccountHeadID =adjustWrongAccountAdjustPickDAO.insert(adjustWrongAccountAdjustPick);
      adjustWrongAccountTail.setAdjustWrongAccountHead(adjustWrongAccountAdjustPick);
      adjustWrongAccountTailDAO.insert(adjustWrongAccountTail);
    }else if("G".equals(type.toUpperCase()))
    {
      AdjustWrongAccountAdjustOther adjustWrongAccountAdjustOther = new AdjustWrongAccountAdjustOther();
      adjustWrongAccountAdjustOther.setOrg(orgs);
  //    adjustWrongAccountAdjustOther.setDocNum(docNum);
      adjustWrongAccountAdjustOther.setAdjustStatus(new BigDecimal(1.00));
      adjustWrongAccountAdjustOther.setReserveaC(noteNum);
      adjustWrongAccountHeadID=adjustWrongAccountAdjustOtherDAO.insert(adjustWrongAccountAdjustOther);
      adjustWrongAccountTail.setAdjustWrongAccountHead(adjustWrongAccountAdjustOther);
      adjustWrongAccountTailDAO.insert(adjustWrongAccountTail);
    }
    String flowid=adjustWrongAccountHeadID.toString();
    HafOperateLog hafOperateLog=new HafOperateLog();
//    hafOperateLog.setOpSys(new Integer(1));
//    hafOperateLog.setOpModel("错账调整");
//    hafOperateLog.setOpButton("添加");
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_ADD)
    .toString());
    hafOperateLog.setOpBizId(new Integer(flowid));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgId));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);
    
    ChangeAccountBizActivityLog log=new ChangeAccountBizActivityLog();
    log.setBizid(new Integer(adjustWrongAccountHeadID.toString()));
    log.setAction(new Integer(1));
    log.setOpTime(new java.util.Date());
    log.setOperator(oper);
    changeAccountBizActivityLogDAO.insert(log);
    return adjustWrongAccountHeadID;
  }
  /**
   * 修改错账调整头表状态
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws Exception 
   * @throws BusinessException 
   * @throws BusinessException 
   */  
  public AdjustWrongAccountHead updateAdjustWrongAccountHeadState(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo,String noteNum) throws BusinessException, Exception {

    String bizDate = securityInfo.getUserInfo().getBizDate();
    String orgId = adjustWrongAccountHead.getOrg().getId().toString();
    Org org=orgDAO.queryById(new Integer(orgId));

//    try{
    //String officeid=adjustWrongAccountHead.getOrg().getOrgInfo().getOfficecode();
    String docNumDocument=collParaDAO.getDocNumDesignPara();
    String officeid="";
    if(docNumDocument.equals("1")){
      officeid = org.getOrgInfo().getOfficecode();
    }else{
      officeid = org.getOrgInfo().getCollectionBankId();
    }
    AdjustWrongAccountHead adjustWrongAccountHead1=adjustWrongAccountHeadDAO.queryById(adjustWrongAccountHead.getId());
    String docNum=docNumMaintainDAO.getDocNumdocNum(officeid,bizDate.substring(0,6));
    Map office = securityInfo.getOfficeInnerCodeMap();
    String officecode = office.get(officeid).toString();
    if (officecode.length() == 1) {
      officecode = "0" + officecode;
    }
    docNum=officecode+bizDate.substring(0,6)+docNum;
    adjustWrongAccountHead1.setAdjustStatus(new BigDecimal(3.00));
    adjustWrongAccountHead1.setDocNum(docNum);
    adjustWrongAccountHead1.setBizDate(bizDate);
    if(noteNum != null && !"".equals(noteNum)){
      adjustWrongAccountHead1.setReserveaC(noteNum);
    }
    //更改过,以下程序.
    
      List empList=new ArrayList();
      //认为重复
//      AdjustWrongAccountHead adjustWrongAccountHead1_temp=null;
//      adjustWrongAccountHead1_temp=this.findAdjustWrongAccountHeadByID(adjustWrongAccountHead1.getId());
      empList=this.findAdjustWrongAccountTailByHead(adjustWrongAccountHead1.getId()+"");
      this.insertOrgHAFAccountFlowByWrongHAT(adjustWrongAccountHead1,empList,securityInfo);
//    }catch(Exception e){
//      e.printStackTrace();
//    }
    return adjustWrongAccountHead1;
  }
  
  /**
   * 将101和102里的员工和单位信息--插入到314和315中
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws Exception 
   * @throws BusinessException 
   * @throws BusinessException 
   */  
  public AdjustWrongAccountHead insertAdjustWrongHATByOrgHAT(List list,SecurityInfo securityInfo,String noteNum) throws BusinessException, Exception {

    String bizDate = securityInfo.getUserInfo().getBizDate();
    String temp_type="";
    Serializable adjustWrongAccountHeadID="";
    EmpHAFAccountFlow empHAFAccountFlow=(EmpHAFAccountFlow)list.get(0);
    OrgHAFAccountFlow orgHAFAccountFlow=orgHAFAccountFlowDAO.queryById((Integer)empHAFAccountFlow.getOrgHAFAccountFlow().getId());
    String bizdocNum=orgHAFAccountFlow.getDocNum();
    String bizNoteNum=orgHAFAccountFlow.getNoteNum();
    Org orgs =orgDAO.queryById(new Integer(orgHAFAccountFlow.getOrg().getId()+""));
//    String officeid=orgs.getOrgInfo().getOfficecode(); 
    String docNumDocument=collParaDAO.getDocNumDesignPara();
    String officeid="";
    if(docNumDocument.equals("1")){
      officeid = orgs.getOrgInfo().getOfficecode();
    }else{
      officeid = orgs.getOrgInfo().getCollectionBankId();
    }
    String docNum=docNumMaintainDAO.getDocNumdocNum(officeid,bizDate.substring(0,6));
    Map office = securityInfo.getOfficeInnerCodeMap();
    String officecode = office.get(officeid).toString();
    if (officecode.length() == 1) {
      officecode = "0" + officecode;
    }
    docNum=officecode+bizDate.substring(0,6)+docNum;
//    System.out.println("docNum:"+docNum);
    AdjustWrongAccountAdjustPayment adjustWrongAccountAdjustPayment=new AdjustWrongAccountAdjustPayment();
    AdjustWrongAccountAdjustPick adjustWrongAccountAdjustPick=new AdjustWrongAccountAdjustPick();
    AdjustWrongAccountAdjustOther adjustWrongAccountAdjustOther=new AdjustWrongAccountAdjustOther();
    if("汇缴".equals(orgHAFAccountFlow.getBizType().trim()) || "补缴".equals(orgHAFAccountFlow.getBizType().trim())|| "单位补缴".equals(orgHAFAccountFlow.getBizType().trim())){
      adjustWrongAccountAdjustPayment.setAdjustStatus(new BigDecimal(3.00));
      adjustWrongAccountAdjustPayment.setDocNum(docNum);
      adjustWrongAccountAdjustPayment.setOrg(orgs);
      adjustWrongAccountAdjustPayment.setBizDate(bizDate);
      adjustWrongAccountAdjustPayment.setBizNoteNum(bizNoteNum);
      adjustWrongAccountAdjustPayment.setBizDocNum(bizdocNum);
      adjustWrongAccountAdjustPayment.setReserveaC(noteNum);
      adjustWrongAccountHeadID=adjustWrongAccountAdjustPaymentDAO.insert(adjustWrongAccountAdjustPayment); 
      temp_type="1";
    } else if("提取".equals(orgHAFAccountFlow.getBizType().trim())){
      adjustWrongAccountAdjustPick.setAdjustStatus(new BigDecimal(3.00));
      adjustWrongAccountAdjustPick.setDocNum(docNum);
      adjustWrongAccountAdjustPick.setOrg(orgs);
      adjustWrongAccountAdjustPick.setBizDate(bizDate);
      adjustWrongAccountAdjustPick.setBizNoteNum(bizNoteNum);
      adjustWrongAccountAdjustPick.setBizDocNum(bizdocNum);
      adjustWrongAccountAdjustPick.setReserveaC(noteNum);
      adjustWrongAccountHeadID=adjustWrongAccountAdjustPickDAO.insert(adjustWrongAccountAdjustPick);
      temp_type="2";
    }else{
      adjustWrongAccountAdjustOther.setAdjustStatus(new BigDecimal(3.00));
      adjustWrongAccountAdjustOther.setDocNum(docNum);
      adjustWrongAccountAdjustOther.setOrg(orgs);
      adjustWrongAccountAdjustOther.setBizDate(bizDate);
      adjustWrongAccountAdjustOther.setBizNoteNum(bizNoteNum);
      adjustWrongAccountAdjustOther.setBizDocNum(bizdocNum);
      adjustWrongAccountAdjustOther.setReserveaC(noteNum);
      adjustWrongAccountHeadID=adjustWrongAccountAdjustOtherDAO.insert(adjustWrongAccountAdjustOther);
      temp_type="3";
    } 
    //插入尾表
//    AdjustWrongAccountHead adjustWrongAccountHead =adjustWrongAccountHeadDAO.queryById(adjustWrongAccountHeadID);
     for(int i=0;i<list.size();i++)
    {  
      AdjustWrongAccountTail adjustWrongAccountTail=new AdjustWrongAccountTail();
      EmpHAFAccountFlow empHAFAccountFlow1=(EmpHAFAccountFlow)list.get(i);
      adjustWrongAccountTail.setEmpId(empHAFAccountFlow1.getEmpId());
      if("1".equals(temp_type))
      adjustWrongAccountTail.setAdjustWrongAccountHead(adjustWrongAccountAdjustPayment);
      else if("2".equals(temp_type))
        adjustWrongAccountTail.setAdjustWrongAccountHead(adjustWrongAccountAdjustPick);
      else 
        adjustWrongAccountTail.setAdjustWrongAccountHead(adjustWrongAccountAdjustOther);
//      adjustWrongAccountTail.setSettDate(BusiTools.dateToString(new Date (),BusiConst.PUB_LONG_DATE_PATTERNS));
      //更改过,AA315表中没有插入的错账日是期不对，应该取调整的对应101业务日期
      adjustWrongAccountTail.setSettDate(orgHAFAccountFlow.getSettDate());
      if(empHAFAccountFlow1.getCredit().doubleValue()==0)
        adjustWrongAccountTail.setAdjustMoney(empHAFAccountFlow1.getDebit());
      else
        adjustWrongAccountTail.setAdjustMoney((empHAFAccountFlow1.getCredit().multiply(new BigDecimal(-1.00))));  //从102里取得 借方或贷方 然后插入315里  还用判断是否插入数据吗
      adjustWrongAccountTailDAO.insert(adjustWrongAccountTail);               //哪方插入为负数
    }
     //更改过,新添加下数行.
     AdjustWrongAccountHead adjustWrongAccountHead1=null;
     try{
     List empList=new ArrayList();
     adjustWrongAccountHead1=this.findAdjustWrongAccountHeadByID(adjustWrongAccountHeadID);
     empList=this.findAdjustWrongAccountTailByHead(adjustWrongAccountHeadID+"");
     this.insertOrgHAFAccountFlowByWrongHAT(adjustWrongAccountHead1,empList,securityInfo);
     }catch(Exception e){
       e.printStackTrace();
     }
     return adjustWrongAccountHead1;
  }
  
  /**
   * 根据错账头表查出尾表的相应员工
   * @param adjustWrongAccountHead
   * @return Serializable
   * @throws BusinessException 
   */ 
  public List findAdjustWrongAccountTailByHead(String HeadID) throws Exception,BusinessException{
   
    List list=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(HeadID);
    return list;
  }
  /**
   * 将错账头表查出尾表的相应员工插入到业务流水头尾表
   * @param adjustWrongAccountHead
   * @return Serializable
   * @throws BusinessException 
   */ 
  public void insertOrgHAFAccountFlowByWrongHAT(AdjustWrongAccountHead adjustWrongAccountHead,List empList,SecurityInfo securityInfo) {
  //  OrgHAFAccountFlow orgHAFAccountFlow =new OrgHAFAccountFlow ();
    String ip = securityInfo.getUserInfo().getUserIp();
    //String officeid=securityInfo.getUserInfo().getOfficeId().toString();
    String oper = securityInfo.getUserInfo().getUsername();
    //String bizDate = securityInfo.getUserInfo().getBizDate();
    String orgid=adjustWrongAccountHead.getOrg().getId().toString();
    double money=0;
    for(int i=0;i<empList.size();i++)
    {
      AdjustWrongAccountTail adjustWrongAccountTail=(AdjustWrongAccountTail)empList.get(i); 
      money=money+adjustWrongAccountTail.getAdjustMoney().doubleValue();
    }
    OrgHAFAccountFlowAdjustPayment orgHAFAccountFlowAdjustPayment=new OrgHAFAccountFlowAdjustPayment();
    OrgHAFAccountFlowAdjustOther orgHAFAccountFlowAdjustOther=new OrgHAFAccountFlowAdjustOther();
    OrgHAFAccountFlowAdjustPick orgHAFAccountFlowAdjustPick=new OrgHAFAccountFlowAdjustPick();
    Serializable orgHAFAccountFlowID="";
    String registrations ="";
    Org org = orgDAO.queryById(new Integer(orgid));
    if("调缴存".equals(adjustWrongAccountHead.getBizType())){
      orgHAFAccountFlowAdjustPayment.setBizStatus(adjustWrongAccountHead.getAdjustStatus());
      orgHAFAccountFlowAdjustPayment.setDocNum(adjustWrongAccountHead.getDocNum());
      //插入操作员
      orgHAFAccountFlowAdjustPayment.setReserveaA(securityInfo.getUserInfo().getUsername());
      
      ChangeAccountBizActivityLog log = changeAccountBizActivityLogDAO.findBizActivityLogById(adjustWrongAccountHead.getId().toString(), "G");
      if(log!=null){
       registrations = log.getOperator();}
       else{
        registrations=oper;
      }
      
      orgHAFAccountFlowAdjustPayment.setRegistrations(registrations);//插入登记人
      
      orgHAFAccountFlowAdjustPayment.setPersonTotal(new Integer(empList.size()));
      orgHAFAccountFlowAdjustPayment.setIsFinance(new BigDecimal(1.00));
      orgHAFAccountFlowAdjustPayment.setBizId(new BigDecimal(adjustWrongAccountHead.getId()+""));
//      orgHAFAccountFlowAdjustPayment.setSettDate(adjustWrongAccountHead.getBizDate());
      //更改过,批理错账调调整时往AA101表中插入数据时，没有插入业务日期
      orgHAFAccountFlowAdjustPayment.setSettDate(securityInfo.getUserInfo().getBizDate());
      orgHAFAccountFlowAdjustPayment.setOrg(adjustWrongAccountHead.getOrg());
      if(money<=0){
        orgHAFAccountFlowAdjustPayment.setDebit(new BigDecimal(money).abs());
        orgHAFAccountFlowAdjustPayment.setCredit(new BigDecimal(0.00));
      }else{
        orgHAFAccountFlowAdjustPayment.setDebit(new BigDecimal(0.00));
        orgHAFAccountFlowAdjustPayment.setCredit(new BigDecimal(money));
      }
      //二次升级开始
      orgHAFAccountFlowAdjustPayment.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgHAFAccountFlowAdjustPayment.setMoneyBank(org.getOrgInfo().getCollectionBankId());
      //结束
      orgHAFAccountFlowAdjustPayment.setNoteNum(adjustWrongAccountHead.getReserveaC());
      orgHAFAccountFlowID=orgHAFAccountFlowAdjustPaymentDAO.insert(orgHAFAccountFlowAdjustPayment);
    }else if("调提取".equals(adjustWrongAccountHead.getBizType())){
      orgHAFAccountFlowAdjustPick.setBizStatus(adjustWrongAccountHead.getAdjustStatus());
      orgHAFAccountFlowAdjustPick.setDocNum(adjustWrongAccountHead.getDocNum());
      orgHAFAccountFlowAdjustPick.setIsFinance(new BigDecimal(1.00));
      //插入操作员
      orgHAFAccountFlowAdjustPick.setReserveaA(securityInfo.getUserInfo().getUsername());
      ChangeAccountBizActivityLog log = changeAccountBizActivityLogDAO.findBizActivityLogById(adjustWrongAccountHead.getId().toString(), "G");
      if(log!=null){
       registrations = log.getOperator();
      }
      else{
        registrations = oper;
      }
     
      orgHAFAccountFlowAdjustPick.setRegistrations(registrations);//插入登记人
      
      orgHAFAccountFlowAdjustPick.setPersonTotal(new Integer(empList.size()));
      orgHAFAccountFlowAdjustPayment.setIsFinance(new BigDecimal(1.00));
      orgHAFAccountFlowAdjustPick.setBizId(new BigDecimal(adjustWrongAccountHead.getId()+""));
//      orgHAFAccountFlowAdjustPick.setSettDate(adjustWrongAccountHead.getBizDate());
//    更改过,批理错账调调整时往AA101表中插入数据时，没有插入业务日期
      orgHAFAccountFlowAdjustPick.setSettDate(securityInfo.getUserInfo().getBizDate());
      orgHAFAccountFlowAdjustPick.setOrg(adjustWrongAccountHead.getOrg());
      if(money<=0){
        orgHAFAccountFlowAdjustPick.setDebit(new BigDecimal(money).abs());
        orgHAFAccountFlowAdjustPick.setCredit(new BigDecimal(0.00));
      }else{
        orgHAFAccountFlowAdjustPick.setDebit(new BigDecimal(0.00));
        orgHAFAccountFlowAdjustPick.setCredit(new BigDecimal(money));
      }
      //二次升级开始
      orgHAFAccountFlowAdjustPick.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgHAFAccountFlowAdjustPick.setMoneyBank(org.getOrgInfo().getCollectionBankId());
      //结束
      orgHAFAccountFlowAdjustPick.setNoteNum(adjustWrongAccountHead.getReserveaC());
      orgHAFAccountFlowID=orgHAFAccountFlowAdjustPickDAO.insert(orgHAFAccountFlowAdjustPick);
    }else if("调其他".equals(adjustWrongAccountHead.getBizType())){
      orgHAFAccountFlowAdjustOther.setBizStatus(adjustWrongAccountHead.getAdjustStatus());
      orgHAFAccountFlowAdjustOther.setDocNum(adjustWrongAccountHead.getDocNum());
      //插入操作员
      orgHAFAccountFlowAdjustOther.setReserveaA(securityInfo.getUserInfo().getUsername());
     
      ChangeAccountBizActivityLog log = changeAccountBizActivityLogDAO.findBizActivityLogById(adjustWrongAccountHead.getId().toString(), "G");
      if(log!=null){
      registrations = log.getOperator();}
      else{
        registrations=oper;
      }
      orgHAFAccountFlowAdjustOther.setRegistrations(registrations);//插入登记人
      
      orgHAFAccountFlowAdjustOther.setPersonTotal(new Integer(empList.size()));
      orgHAFAccountFlowAdjustOther.setIsFinance(new BigDecimal(1.00));
      orgHAFAccountFlowAdjustOther.setBizId(new BigDecimal(adjustWrongAccountHead.getId()+""));
//      orgHAFAccountFlowAdjustOther.setSettDate(adjustWrongAccountHead.getBizDate());
//    更改过,批理错账调调整时往AA101表中插入数据时，没有插入业务日期
      orgHAFAccountFlowAdjustOther.setSettDate(securityInfo.getUserInfo().getBizDate());
      orgHAFAccountFlowAdjustOther.setOrg(adjustWrongAccountHead.getOrg());
      if(money<=0){
        orgHAFAccountFlowAdjustOther.setDebit(new BigDecimal(money).abs());
        orgHAFAccountFlowAdjustOther.setCredit(new BigDecimal(0.00));
      }else{
        orgHAFAccountFlowAdjustOther.setDebit(new BigDecimal(0.00));
        orgHAFAccountFlowAdjustOther.setCredit(new BigDecimal(money));
      }
      //二次升级开始
      orgHAFAccountFlowAdjustOther.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgHAFAccountFlowAdjustOther.setMoneyBank(org.getOrgInfo().getCollectionBankId());
      //结束
      orgHAFAccountFlowAdjustOther.setNoteNum(adjustWrongAccountHead.getReserveaC());
      orgHAFAccountFlowID=orgHAFAccountFlowAdjustOtherDAO.insert(orgHAFAccountFlowAdjustOther);
    } 
    OrgHAFAccountFlow orgHAFAccountFlow=orgHAFAccountFlowDAO.queryById((Integer)orgHAFAccountFlowID);
    for(int i=0;i<empList.size();i++)
    {
      
      EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
      AdjustWrongAccountTail adjustWrongAccountTail=(AdjustWrongAccountTail)empList.get(i);
      empHAFAccountFlow.setEmpId(adjustWrongAccountTail.getEmpId());
      empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlow);
      if(adjustWrongAccountTail.getAdjustMoney().doubleValue()<=0){
        empHAFAccountFlow.setCredit(new BigDecimal(0.00));
        empHAFAccountFlow.setDebit(adjustWrongAccountTail.getAdjustMoney().abs());
      }
      else{
        empHAFAccountFlow.setCredit(adjustWrongAccountTail.getAdjustMoney());
        empHAFAccountFlow.setDebit(new BigDecimal(0.00));
      }
      empHAFAccountFlowDAO.insert(empHAFAccountFlow);
    }
    //003
    HafOperateLog hafOperateLog=new HafOperateLog();
//    hafOperateLog.setOpSys(new Integer(1));
//    hafOperateLog.setOpModel("错账调整");
//    hafOperateLog.setOpButton("完成调整");
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//    hafOperateLog.setOpModel(new Integer(
//        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO).toString());
//    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_ENDCHG)
//    .toString());
    // 付云峰修改：OP_MODEL=63, OP_BUTTON=7
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_OPENING)
    .toString());
    hafOperateLog.setOpBizId(new Integer(adjustWrongAccountHead.getId().toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgid));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);
    //--插入319
    ChangeAccountBizActivityLog log=new ChangeAccountBizActivityLog();
    log.setBizid(new Integer(adjustWrongAccountHead.getId().toString()) );
    log.setAction(new Integer(3));
    log.setOpTime(new java.util.Date());
    log.setOperator(oper);
    Serializable id= changeAccountBizActivityLogDAO.insert(log);
  }
  /**
   * 根据错账头表id返回头表
   * @param id
   * @return AdjustWrongAccountHead
   * @throws BusinessException 
   */ 
  public AdjustWrongAccountHead findAdjustWrongAccountHeadByID(Serializable id) throws Exception,BusinessException{
    return adjustWrongAccountHeadDAO.queryById(new Integer (id+""));
  }
  /**
   * 根据id删除
   * @param id
   * @return String
   * @throws BusinessException 
   */ 
  public String deleteAdjustWrongAccountTailByID(String id,SecurityInfo securityInfo) throws Exception,BusinessException{
    String lastLine="";
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    AdjustWrongAccountTail adjustWrongAccountTail=adjustWrongAccountTailDAO.queryById(new Integer(id));
    String headid=adjustWrongAccountTail.getAdjustWrongAccountHead().getOrg().getId().toString();
    List list=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(adjustWrongAccountTail.getAdjustWrongAccountHead().getId()
        +"");
    AdjustWrongAccountHead adjustWrongAccountHead=adjustWrongAccountHeadDAO.queryById(new Integer(adjustWrongAccountTail.getAdjustWrongAccountHead().getId()+""));
    if(list.size()==1 && list.size()!=0){
      //删除头尾表
      adjustWrongAccountTailDAO.deleteById(new Integer(id));
      adjustWrongAccountHeadDAO.deleteById(new Integer(adjustWrongAccountTail.getAdjustWrongAccountHead().getId()+""));
      ChangeAccountBizActivityLog changeAccountBizActivityLog=null;
      if("调其他".equals(adjustWrongAccountHead.getBizType()))
      changeAccountBizActivityLog=changeAccountBizActivityLogDAO.findChangeAccountBizActivityLogByCriterion(adjustWrongAccountHead.getId().toString());
      else if("调缴存".equals(adjustWrongAccountHead.getBizType()))
        changeAccountBizActivityLog=changeAccountBizActivityLogDAO.findChangeAccountBizActivityLogByCriterion(adjustWrongAccountHead.getId().toString());
      else if("调提取".equals(adjustWrongAccountHead.getBizType()))
        changeAccountBizActivityLog=changeAccountBizActivityLogDAO.findChangeAccountBizActivityLogByCriterion(adjustWrongAccountHead.getId().toString());
      changeAccountBizActivityLogDAO.deleteById(new Integer(changeAccountBizActivityLog.getId().toString()));
      lastLine="noLine";
    }else
    adjustWrongAccountTailDAO.deleteById(new Integer(id));
    HafOperateLog hafOperateLog=new HafOperateLog();
//    hafOperateLog.setOpSys(new Integer(1));
//    hafOperateLog.setOpModel("错账调整");
//    hafOperateLog.setOpButton("删除");
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
    .toString());
    hafOperateLog.setOpBizId(new Integer(adjustWrongAccountTail.getAdjustWrongAccountHead().getId().toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(headid));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);
    return lastLine;
  }
  /**进入维护
   * 查询出错账头表状态为1和3的都查询出来
   * @param pagination
   * @return List
   * @throws BusinessException 
   */ 
  //
  public AdjustaccountDTO findAdjustWrongAccountHeadByStatus(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException{
    AdjustaccountDTO dto=new AdjustaccountDTO();
    BigDecimal allmoney= new BigDecimal(0.00);
    int persontotal=0;
    String orderBy=(String) pagination.getOrderBy();
//    OrderEnum oe=  pagination.getOrder();
//    String order=oe.getName();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page=pagination.getPage(); 
    List temp_list=adjustWrongAccountHeadDAO.queryAdjustWrongAccountHeadListByState(orderBy,order,start,pageSize,securityInfo,page);    
    List list=new ArrayList();
    
    for(int i=0;i<temp_list.size();i++){
      AdjustWrongAccountHead adjustWrongAccountHead=(AdjustWrongAccountHead) temp_list.get(i);      
      Org org=orgDAO.queryById(new Integer(adjustWrongAccountHead.getOrg().getId()+""));
      adjustWrongAccountHead.setOrg(org);
      adjustWrongAccountHead.setStatus(BusiTools.getBusiValue(Integer.parseInt(adjustWrongAccountHead.getAdjustStatus().toString()), BusiConst.BUSINESSSTATE));
      list.add(adjustWrongAccountHead);
//      if(adjustWrongAccountHead.getTotal()!=null)
//      allmoney=allmoney.add(adjustWrongAccountHead.getTotal());
//      persontotal=(persontotal+Integer.parseInt(adjustWrongAccountHead.getPersonTotal().toString()));
    }dto.setList(list);
//    dto.setTotal(allmoney);
//    dto.setPersontotal(new Integer(persontotal));
    List all_list = adjustWrongAccountHeadDAO.queryAdjustWrongAccountHeadAllListByState(orderBy,order,start,pageSize,securityInfo);
   //更改过,合计显示的为当前分页的合计
    for(int j=0;j<all_list.size();j++){
      AdjustWrongAccountHead adjustWrongAccountHead=(AdjustWrongAccountHead) all_list.get(j); 
      if(adjustWrongAccountHead.getTotal()!=null)
        allmoney=allmoney.add(adjustWrongAccountHead.getTotal());
        persontotal=(persontotal+Integer.parseInt(adjustWrongAccountHead.getPersonTotal().toString()));
    }
    dto.setTotal(allmoney);
    dto.setPersontotal(new Integer(persontotal));
    pagination.setNrOfElements(all_list.size());
    
    return dto;
  }
  /**
   * 综合查询出错账单位
   * @param adjustWrongAccountHead
   * @return List
   * @throws BusinessException 
   */  
  public AdjustaccountDTO findAdjustWrongAccountHeadByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException{
    AdjustaccountDTO dto=new AdjustaccountDTO();
    int page=pagination.getPage(); 
    Integer orgIds=(Integer)pagination.getQueryCriterions().get("id");
    String orgId;
    BigDecimal allmoney= new BigDecimal(0.00);
    int persontotal=0;
    if (orgIds==null) orgId=null;
    else orgId=orgIds.toString();
    String orgName=(String)pagination.getQueryCriterions().get("name");
    String bis_status=(String)pagination.getQueryCriterions().get("bis_status");
    String bizDocNum=(String)pagination.getQueryCriterions().get("bizDocNum");
    String date=(String)pagination.getQueryCriterions().get("date");
    String date1=(String)pagination.getQueryCriterions().get("date1");
    String orderBy=(String) pagination.getOrderBy();
//    OrderEnum oe=  pagination.getOrder();
//    String order=oe.getName();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    List temp_list=adjustWrongAccountHeadDAO.queryAdjustWrongAccountHeadListByCriterions(orgId, orgName, bis_status, bizDocNum, 
        date,date1, orderBy, order, start, pageSize,securityInfo,page);
    List list=new ArrayList();
    for(int i=0;i<temp_list.size();i++){
      AdjustWrongAccountHead adjustWrongAccountHead=(AdjustWrongAccountHead) temp_list.get(i);
      
      Org org=orgDAO.queryById(new Integer(adjustWrongAccountHead.getOrg().getId()+""));
      adjustWrongAccountHead.setOrg(org);
      adjustWrongAccountHead.setStatus(BusiTools.getBusiValue(Integer.parseInt(adjustWrongAccountHead.getAdjustStatus().toString()), BusiConst.BUSINESSSTATE));
      list.add(adjustWrongAccountHead);
//      if(adjustWrongAccountHead.getTotal()!=null)
//      allmoney=allmoney.add(adjustWrongAccountHead.getTotal());
//      persontotal=(persontotal+Integer.parseInt(adjustWrongAccountHead.getPersonTotal().toString()));
      
    }dto.setList(list);
//    dto.setTotal(allmoney);
//    dto.setPersontotal(new Integer(persontotal));
    List lists = adjustWrongAccountHeadDAO.queryAdjustWrongAccountHeadAllListByCriterions(orgId, orgName, bis_status, bizDocNum, 
        date,date1, orderBy, order, start, pageSize,securityInfo);
    //更改过,合计显示的为当前分页的合计
    for(int j=0;j<lists.size();j++){
      AdjustWrongAccountHead adjustWrongAccountHead=(AdjustWrongAccountHead) lists.get(j);
      if(adjustWrongAccountHead.getTotal()!=null)
        allmoney=allmoney.add(adjustWrongAccountHead.getTotal());
        persontotal=(persontotal+Integer.parseInt(adjustWrongAccountHead.getPersonTotal().toString()));
    }
    dto.setTotal(allmoney);
    dto.setPersontotal(new Integer(persontotal));
    pagination.setNrOfElements(lists.size());
    return dto;
  }
  
  
  /**
   * 根据id删除
   * @param id
   * @return String
   * @throws BusinessException 
   */ 
  public String deleteAdjustWrongAccountHeadAndTailByID(String id) throws Exception,BusinessException{//循环好象有问题看看谁用了
    AdjustWrongAccountTail adjustWrongAccountTail=adjustWrongAccountTailDAO.queryById(new Integer(id));
    List list=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(adjustWrongAccountTail.getAdjustWrongAccountHead().getId()
        +"");
    adjustWrongAccountHeadDAO.deleteById(new Integer(adjustWrongAccountTail.getAdjustWrongAccountHead().getId()+""));
    for(int i=0;i<list.size();i++){
      adjustWrongAccountTailDAO.deleteById(new Integer(adjustWrongAccountTail.getId()+""));
    }
    HafOperateLog hafOperateLog=new HafOperateLog();
//    hafOperateLog.setOpSys(new Integer(1));
//    hafOperateLog.setOpModel("错账调整维护");
//    hafOperateLog.setOpButton("删除");
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
    .toString());
    hafOperateLog.setOpBizId(new Integer(id));
    hafOperateLog.setOpIp("TEST");
    hafOperateLog.setOrgId(new Integer("1"));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator("NO PERSON");
    hafOperateLogDAO.insert(hafOperateLog);
    return null;
  }
  
  /** 错账维护 
   * 判断是否有同一家公司有不同的状态
   * @param orgId
   * @param empId
   * @return Serializable
   * @throws BusinessException 
   */   
  public List findAdjustWrongAccountHeadIDByOrgIdAndStatus(String orgId) throws Exception,BusinessException{
      List list=adjustWrongAccountHeadDAO.findAdjustWrongAccountHeadListByOrgId(orgId);
      return list;
  }
  
  /** 错账维护 
   *  修改头表状态
   * @param adjustWrongAccountHead
   * @return Serializable
   * @throws BusinessException 
   */   
  public void updateAdjustWrongAccountHeadByID(String id,SecurityInfo securityInfo) throws Exception,BusinessException{
    try{
//    String officeid=securityInfo.getUserInfo().getOfficeId().toString();
    AdjustWrongAccountHead adjustWrongAccountHead=adjustWrongAccountHeadDAO.queryById(new Integer(id));
    //更改过,
    String orgId=adjustWrongAccountHead.getOrg().getId().toString();
    Org org=orgDAO.queryById(new Integer(orgId));
    String officeid=org.getOrgInfo().getOfficecode(); 
    String docNumDocument=collParaDAO.getDocNumDesignPara();
    if(docNumDocument.equals("1")){
      officeid = org.getOrgInfo().getOfficecode();
    }else{
      officeid = org.getOrgInfo().getCollectionBankId();
    }
    docNumCancelDAO.insertDocNumCancel(adjustWrongAccountHead.getDocNum().substring(8), officeid, adjustWrongAccountHead.getBizDate().substring(0, 6));
    System.out.println(adjustWrongAccountHead.getDocNum()+"--bs----"+adjustWrongAccountHead.getBizDate().substring(0, 6));
    adjustWrongAccountHead.setAdjustStatus(new BigDecimal(1.00));
    adjustWrongAccountHead.setBizDate("");
    adjustWrongAccountHead.setDocNum("");
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  /**
   * 删除错账头为表
   * @param id
   * @return String
   * @throws BusinessException 
   */ 
  public void deleteAdjustWrongAccountHeadAndTailByHID(String id,SecurityInfo securityInfo) throws Exception,BusinessException{
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    List list=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(id
        +"");
    for(int i=0;i<list.size();i++){
      AdjustWrongAccountTail adjustWrongAccountTail1=(AdjustWrongAccountTail)list.get(i);
      adjustWrongAccountTailDAO.deleteById(new Integer(adjustWrongAccountTail1.getId().toString()));
    }
    AdjustWrongAccountHead adjustWrongAccountHead=adjustWrongAccountHeadDAO.queryById(new Integer(id.toString()));
    adjustWrongAccountHeadDAO.deleteById(new Integer(id+""));
    HafOperateLog hafOperateLog=new HafOperateLog();
//    hafOperateLog.setOpSys(new Integer(1));
//    hafOperateLog.setOpModel("错账调整维护");
//    hafOperateLog.setOpButton("删除");
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
    .toString());
    hafOperateLog.setOpBizId(new Integer(id));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(adjustWrongAccountHead.getOrg().getId().toString()));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(oper);
    Serializable ii=hafOperateLogDAO.insert(hafOperateLog);

    ChangeAccountBizActivityLog changeAccountBizActivityLog=changeAccountBizActivityLogDAO.findChangeAccountBizActivityLogByCriterion(id);
    changeAccountBizActivityLogDAO.deleteById(new Integer(changeAccountBizActivityLog.getId().toString()));
    
  }
  
  /**
   * 撤销调整 删除错账头为表
   * @param id
   * @return String
   * @throws BusinessException 
   */ 
  public void deleteAdjustWrongAccountAllByHID(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo) throws Exception,BusinessException{
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    List list=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(adjustWrongAccountHead.getId().toString()
        +"");
    String officeid="";
    for(int i=0;i<list.size();i++){
      AdjustWrongAccountTail adjustWrongAccountTail1=(AdjustWrongAccountTail)list.get(i);
      //更改过,目的是保证办事处一致.
      if(officeid.equals("")){
        String orgId=adjustWrongAccountTail1.getAdjustWrongAccountHead().getOrg().getId().toString();
        Org org=orgDAO.queryById(new Integer(orgId));
        officeid=org.getOrgInfo().getOfficecode(); 
        String docNumDocument=collParaDAO.getDocNumDesignPara();
        if(docNumDocument.equals("1")){
          officeid = org.getOrgInfo().getOfficecode();
        }else{
          officeid = org.getOrgInfo().getCollectionBankId();
        }
      }
      adjustWrongAccountTailDAO.deleteById(new Integer(adjustWrongAccountTail1.getId().toString()));
    }
    docNumCancelDAO.insertDocNumCancel(adjustWrongAccountHead.getDocNum().substring(8), officeid, adjustWrongAccountHead.getBizDate().substring(0, 6));
    adjustWrongAccountHeadDAO.deleteById(new Integer(adjustWrongAccountHead.getId().toString()));
  }
  
  /**
   * 打印 错账头尾表
   * @param id
   * @return String
   * @throws BusinessException 
   */ 
  public List adjustWrongAccountAllByHID(String id,SecurityInfo securityInfo) throws Exception,BusinessException{
    String empid="";
    List printlist=new ArrayList();
    List list=adjustWrongAccountTailDAO.queryAdjustWrongAccountTailAllListByCriterions(id
        +"");
    AdjustWrongAccountHead adjustWrongAccountHead1=this.findAdjustWrongAccountHeadByID(id);
    BigDecimal temp1=new BigDecimal(0.00);
    //获取办事处
    String office="";
//    List officlist=securityInfo.getAllCenterList();
//    if(officlist != null){
//      OfficeDto dto=(OfficeDto)officlist.get(0);
//      office=dto.getOfficeName();
//    }
    List personList = adjustWrongAccountTailDAO.queryPerson_jj(adjustWrongAccountHead1.getId().toString(),adjustWrongAccountHead1.getBizType());
    String collBankName = "";
    String officeid = "";
    String str[]=new String[3];
    str=this.queryOfficeBankNames_ws(adjustWrongAccountHead1.getOrg().getId().toString(), "2", id, adjustWrongAccountHead1.getBizType(), securityInfo);
    office=str[0];
    collBankName = str[1];
    officeid = str[2];
    for(int i=0;i<list.size();i++){
      AdjustaccountReportDTO adjustaccountReportDTO=new AdjustaccountReportDTO();
      AdjustWrongAccountTail adjustWrongAccountTail=(AdjustWrongAccountTail)list.get(i);
      if(!personList.isEmpty()){
        Object [] obj=null;
        Iterator it = personList.iterator();
        while(it.hasNext()){
          obj=(Object[])it.next();
          if(obj[0] != null){
            adjustaccountReportDTO.setCheckPerson(obj[0].toString());
          }
          if(obj[1] != null){
            adjustaccountReportDTO.setClearPerson(obj[1].toString());
          }
        }
      }
      System.out.println(adjustaccountReportDTO.getCheckPerson()+"==========>"+adjustaccountReportDTO.getClearPerson());
      empid=adjustWrongAccountTail.getEmpId().toString();
      adjustaccountReportDTO.setBadReason(adjustWrongAccountTail.getReason());
      adjustaccountReportDTO.setWrongdocnum(adjustWrongAccountHead1.getDocNum());
      adjustaccountReportDTO.setEmpid(adjustWrongAccountTail.getEmpId().toString());
      Emp emp=this.findEmpById(empid);
      adjustaccountReportDTO.setEmpname(emp.getEmpInfo().getName());
      adjustaccountReportDTO.setEmpidcard(emp.getEmpInfo().getCardNum());
      adjustaccountReportDTO.setAdjustaccount(adjustWrongAccountTail.getAdjustMoney());
      adjustaccountReportDTO.setWrongaccountdate(adjustWrongAccountTail.getSettDate());
      adjustaccountReportDTO.setBis_type(adjustWrongAccountHead1.getBizType());
      //放入头表数据
      BigDecimal temp= adjustWrongAccountTail.getAdjustMoney();
      temp1=temp1.add(temp);
      adjustaccountReportDTO.setOrgid(adjustWrongAccountHead1.getOrg().getId().toString());
      adjustaccountReportDTO.setOrgname(adjustWrongAccountHead1.getOrgName());
      adjustaccountReportDTO.setAdjustMoney(temp1.toString());
      //办事处放入
      adjustaccountReportDTO.setOfficename(office);
      adjustaccountReportDTO.setOpenBank(adjustWrongAccountHead1.getOrg().getOrgInfo().getPayBank().getName());
      adjustaccountReportDTO.setCollBankName(collBankName);
      adjustaccountReportDTO.setOfficeid(officeid);
      adjustaccountReportDTO.setNoteNum(adjustWrongAccountHead1.getReserveaC());
      adjustaccountReportDTO.setOpenBankAcc(adjustWrongAccountHead1.getOrg().getOrgInfo().getPayBank().getAccountNum());
      printlist.add(adjustaccountReportDTO);
    }
    return printlist;
  }
  /**
   * 查询出相应的OrgHAFAccountFlow
   * @param orgId
   * @param bizType
   * @param Status
   * @return String
   * @throws BusinessException 
   *  orgHAFAccountFlow==null 314里没有相应的bizid或状态为3的
   */
  public OrgHAFAccountFlow findOrgHAFAccountFlowByCriterions1(String orgId,BigDecimal Status,String bizType,String bizDate) throws Exception,BusinessException{
    OrgHAFAccountFlow orgHAFAccountFlow=orgHAFAccountFlowDAO.queryOrgHAFAccountFlowByCriterions(orgId,Status,bizType,bizDate);
    if(orgHAFAccountFlow==null)
      throw new BusinessException("流水表里没有记录");
    return orgHAFAccountFlow;
  }
  
  /**
   * /删除101和102里相关记录
   * @param id  流水id
   * @return String
   * @throws BusinessException 
   */ 
  
  public void deleteOrgHAFAccountFlowAndEmpByHID(String id,SecurityInfo securityInfo,String bis_id) throws Exception,BusinessException{
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    List emp_list=empHAFAccountFlowDAO.queryEmpHAFAccountFlowListAllByCriterions(id, "", "", 5, 0);
    for (int i = 0;i<emp_list.size();i++){     
      EmpHAFAccountFlow empHAFAccountFlow=(EmpHAFAccountFlow)emp_list.get(i);
      empHAFAccountFlowDAO.deleteById(empHAFAccountFlow.getId()+"");
    }
    OrgHAFAccountFlow orgHAFAccountFlow=orgHAFAccountFlowDAO.queryById(new Integer(id));
    orgHAFAccountFlowDAO.deleteById(id);
    HafOperateLog hafOperateLog=new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_REVOCATION)
    .toString());
    hafOperateLog.setOpBizId(new Integer(bis_id));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(orgHAFAccountFlow.getOrg().getId().toString()));
    hafOperateLog.setOpTime(new java.util.Date());
    hafOperateLog.setOperator(oper);
    Serializable s=hafOperateLogDAO.insert(hafOperateLog);
    ChangeAccountBizActivityLog changeAccountBizActivityLog=changeAccountBizActivityLogDAO.findChangeAccountBizActivityLogByCriterions(bis_id,"");
    changeAccountBizActivityLogDAO.deleteById(new Integer(changeAccountBizActivityLog.getId().toString()));
  }
  /**
   * 点击修改时候插入日志003
   * @param id
   * @return String
   * @throws BusinessException 
   */ 
  public void insertHafOperateLog(String bizId,String orgId,String ip,String oper) throws Exception,BusinessException{
  HafOperateLog hafOperateLog=new HafOperateLog();
//  hafOperateLog.setOpSys(new Integer(1));
//  hafOperateLog.setOpModel("错账调整维护");
//  hafOperateLog.setOpButton("查询");
  hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
  hafOperateLog.setOpModel(new Integer(
      BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN).toString());
//  hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_QUERY)
//  .toString());
  // 付云峰修改：OP_BUTTON=2
  hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_MODIFY)
  .toString());
  hafOperateLog.setOpBizId(new Integer(orgId));
  hafOperateLog.setOpIp(ip);
  hafOperateLog.setOrgId(new Integer(orgId));
  hafOperateLog.setOpTime(new java.util.Date());
  hafOperateLog.setOperator(oper);
  hafOperateLogDAO.insert(hafOperateLog);
  }
  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }
  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setAdjustWrongAccountHeadDAO(
      AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO) {
    this.adjustWrongAccountHeadDAO = adjustWrongAccountHeadDAO;
  }
  
  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
  public void setAdjustWrongAccountTailDAO(
      AdjustWrongAccountTailDAO adjustWrongAccountTailDAO) {
    this.adjustWrongAccountTailDAO = adjustWrongAccountTailDAO;
  }
  public void setAdjustWrongAccountAdjustOtherDAO(
      AdjustWrongAccountAdjustOtherDAO adjustWrongAccountAdjustOtherDAO) {
    this.adjustWrongAccountAdjustOtherDAO = adjustWrongAccountAdjustOtherDAO;
  }
  public void setAdjustWrongAccountAdjustPaymentDAO(
      AdjustWrongAccountAdjustPaymentDAO adjustWrongAccountAdjustPaymentDAO) {
    this.adjustWrongAccountAdjustPaymentDAO = adjustWrongAccountAdjustPaymentDAO;
  }
  public void setAdjustWrongAccountAdjustPickDAO(
      AdjustWrongAccountAdjustPickDAO adjustWrongAccountAdjustPickDAO) {
    this.adjustWrongAccountAdjustPickDAO = adjustWrongAccountAdjustPickDAO;
  }
  public void setChangeAccountBizActivityLogDAO(
      ChangeAccountBizActivityLogDAO changeAccountBizActivityLogDAO) {
    this.changeAccountBizActivityLogDAO = changeAccountBizActivityLogDAO;
  }
  public void setDocNumMaintainDAO(DocNumMaintainDAO docNumMaintainDAO) {
    this.docNumMaintainDAO = docNumMaintainDAO;
  }
  public void setOrgHAFAccountFlowAdjustPaymentDAO(
      OrgHAFAccountFlowAdjustPaymentDAO orgHAFAccountFlowAdjustPaymentDAO) {
    this.orgHAFAccountFlowAdjustPaymentDAO = orgHAFAccountFlowAdjustPaymentDAO;
  }
  public void setOrgHAFAccountFlowAdjustOtherDAO(
      OrgHAFAccountFlowAdjustOtherDAO orgHAFAccountFlowAdjustOtherDAO) {
    this.orgHAFAccountFlowAdjustOtherDAO = orgHAFAccountFlowAdjustOtherDAO;
  }
  public void setOrgHAFAccountFlowAdjustPickDAO(
      OrgHAFAccountFlowAdjustPickDAO orgHAFAccountFlowAdjustPickDAO) {
    this.orgHAFAccountFlowAdjustPickDAO = orgHAFAccountFlowAdjustPickDAO;
  }
  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO= hafOperateLogDAO;
  }
  public void setDocNumCancelDAO(DocNumCancelDAO docNumCancelDAO) {
    this.docNumCancelDAO = docNumCancelDAO;
  }
  public void setSpecialPickDAO(SpecialPickDAO specialPickDAO) {
    this.specialPickDAO = specialPickDAO;
  }
  public void setTranOutHeadDAO(TranOutHeadDAO tranOutHeadDAO) {
    this.tranOutHeadDAO = tranOutHeadDAO;
  }
  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    // 查询办事处银行开始
    String officeName = "";
    String bankName = "";
    String str[]=new String[2];
    if("调提取".equals(bizType)){
      bizType="L";
    }
    if("调其他".equals(bizType)){
      bizType="G";
    }
    if("调缴存".equals(bizType)){
      bizType="G";
    }
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
  //此处多加个归集银行的编号
  public String[] queryOfficeBankNames_ws(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    // 查询办事处银行开始
    String officeName = "";
    String bankName = "";
    String officeId = "";
    String str[]=new String[3];
    if("调提取".equals(bizType)){
      bizType="L";
    }
    if("调其他".equals(bizType)){
      bizType="G";
    }
    if("调缴存".equals(bizType)){
      bizType="G";
    }
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
  
  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }
  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
  public BizActivityLogDAO getBizActivityLogDAO() {
    return bizActivityLogDAO;
  }


}
