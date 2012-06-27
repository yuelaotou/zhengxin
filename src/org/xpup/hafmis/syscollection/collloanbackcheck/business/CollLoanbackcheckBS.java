package org.xpup.hafmis.syscollection.collloanbackcheck.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.collloanbackcheck.bsinterface.ICollLoanbackcheckBS;
import org.xpup.hafmis.syscollection.collloanbackcheck.dto.CollLoanbackcheckDTO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.PickBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class CollLoanbackcheckBS implements ICollLoanbackcheckBS {

  private PickHeadDAO pickHeadDAO = null ;
  
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null ;

  private PickBizActivityLogDAO pickBizActivityLogDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private BizActivityLogDAO bizActivityLogDAO = null;
  
  public PickHeadDAO getPickHeadDAO() {
    return pickHeadDAO;
  }
  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }
  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }
  public void setPickBizActivityLogDAO(PickBizActivityLogDAO pickBizActivityLogDAO) {
    this.pickBizActivityLogDAO = pickBizActivityLogDAO;
  }
  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }
  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }
  /**
   * 查询业务复核列表
   */
  public List collLoanbackcheckForwardFind(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception {
    List returnList=new ArrayList();
    try{
      Integer org_id=null;
      Integer emp_id=null;
      
      String flag=(String) pagination.getQueryCriterions().get("flag");
      String officeCode = (String) pagination.getQueryCriterions().get("officeCode");
      String collectionBankId = (String) pagination.getQueryCriterions().get("collectionBankId");
      String bizStatus = (String) pagination.getQueryCriterions().get("bizStatus");
      String batch_num = (String) pagination.getQueryCriterions().get("batch_num");
      String startDate = (String) pagination.getQueryCriterions().get("startDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      String orgid = (String) pagination.getQueryCriterions().get("orgid");
      if(orgid!=null && orgid !=""){
        org_id= new Integer(orgid.trim());
      }
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String empid = (String) pagination.getQueryCriterions().get("empid");
      if(empid!=null && empid !=""){
        emp_id= new Integer(empid.trim());
      }
      String empname = (String) pagination.getQueryCriterions().get("empname");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      returnList=pickHeadDAO.collLoanbackcheckFindList(flag,officeCode,collectionBankId,bizStatus,batch_num,startDate,endDate,
          orderBy,order,start,pageSize,page,org_id,orgname,emp_id,empname);

    }catch(Exception e){
      e.printStackTrace();
    }
    return returnList;
  }
  /**
   * 查询业务复核全列表
   */
  public List collLoanbackcheckForwardFindAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception {
    List returnList=new ArrayList();
    try{
      Integer org_id=null;
      Integer emp_id=null;
      
      String flag=(String) pagination.getQueryCriterions().get("flag");
      String officeCode = (String) pagination.getQueryCriterions().get("officeCode");
      String collectionBankId = (String) pagination.getQueryCriterions().get("collectionBankId");
      String bizStatus = (String) pagination.getQueryCriterions().get("bizStatus");
      String batch_num = (String) pagination.getQueryCriterions().get("batch_num");
      String startDate = (String) pagination.getQueryCriterions().get("startDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      
      String orgid = (String) pagination.getQueryCriterions().get("orgid");
      if(orgid!=null && orgid !=""){
        org_id= new Integer(orgid.trim());
      }
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String empid = (String) pagination.getQueryCriterions().get("empid");
      if(empid!=null && empid !=""){
        emp_id= new Integer(empid.trim());
      }
      String empname = (String) pagination.getQueryCriterions().get("empname");
      
      returnList=pickHeadDAO.collLoanbackcheckFindAllList(flag,officeCode,collectionBankId,bizStatus,batch_num,startDate,endDate,
          org_id,orgname,emp_id,empname);
      int count = returnList.size();
      pagination.setNrOfElements(count);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnList;
  }
  /**
   * 复核
   */
  public void bizcheck(String id, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      String seqid="";
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      //根据批次号得到AA306的ID
      List seqidList=pickHeadDAO.getPickHeadIDByButenum(id);
      
      for(int i=0;i<seqidList.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)seqidList.get(i);
        seqid=collLoanbackcheckDTO.getSeqid();
        //判断复核人与操作员是否是同一人
         OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
//        String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//        if(securityInfo.getUserName().equals(userName)){
//          throw new BusinessException("制单人与复核人不能为同一人!");
//        }
        //根据ID修改AA306和AA101的业务状态
        
        PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
        pickhead.setPickSatatus(new BigDecimal(4));
        orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
//        //插入AA319和BA003
//        // 插入AA319：
//        PickBizActivityLog pickBizActivityLog = new PickBizActivityLog();
//        pickBizActivityLog.setBizid(new Integer(seqid));
//        pickBizActivityLog.setAction(new Integer(4));
//        pickBizActivityLog.setOpTime(new Date());
//        pickBizActivityLog.setOperator(securityInfo.getUserName());
//        pickBizActivityLogDAO.insert(pickBizActivityLog);
//          // 插入BA003：
//          HafOperateLog hafOperateLog = new HafOperateLog();
//          hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//          hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
//          hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_CHECKS);
//          hafOperateLog.setOpBizId(new Integer(seqid));
//          hafOperateLog.setOperator(securityInfo.getUserName());
//          hafOperateLog.setOpIp(securityInfo.getUserIp());
//          hafOperateLog.setOpTime(new Date());
//          hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
//          hafOperateLogDAO.insert(hafOperateLog);
        
      }
      
    }catch(BusinessException be){
       throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  /**
   * 复核
   */
  public String[] clearaccount(String id, SecurityInfo securityInfo) throws BusinessException, Exception {
    String[] rowArray=null;
    try{
      String seqid="";
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      //根据批次号得到AA306的ID
      List seqidList=pickHeadDAO.getPickHeadIDByButenum(id);
      List resList = new ArrayList();
      for(int i=0;i<seqidList.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)seqidList.get(i);
        seqid=collLoanbackcheckDTO.getSeqid();
        OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
        resList.add(orgHAFAccountFlow.getId().toString());
      }
      rowArray=new String[resList.size()];
      for(int i=0;i<resList.size();i++){
        rowArray[i]=resList.get(i).toString();
      }
      return rowArray;
    }catch(BusinessException be){
      throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * 撤销复核
   */
  public void delbizcheck(String id, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      String seqid="";
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      Integer count = new Integer(0);
      count = pickHeadDAO.query_havingstatus(id);
      
    if(count.intValue()>0){
    throw new BusinessException("此批次号下的公积金还贷业务已经有扎账状态记录,不能撤销复核!");
    }
      //根据批次号得到AA306的ID
      List seqidList=pickHeadDAO.getPickHeadIDByButenum(id);
      for(int i=0;i<seqidList.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)seqidList.get(i);
        seqid=collLoanbackcheckDTO.getSeqid();
        //判断复核人与操作员是否是同一人
        OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
        String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//        if(securityInfo.getUserName().equals(userName)){
//          throw new BusinessException("制单人与复核人不能为同一人!");
//        }
        //根据ID修改AA306和AA101的业务状态
        PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
        pickhead.setPickSatatus(new BigDecimal(3));
        orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
        //操作AA319和BA003
        // 删除AA319：
        BizActivityLog bizActivityLog = new BizActivityLog();
        bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(seqid,
            "4", "D");
        if(bizActivityLog!=null){
        bizActivityLogDAO.deleteWuht(bizActivityLog);
        }
          // 插入BA003：
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
          hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_REVOCATION);
          hafOperateLog.setOpBizId(new Integer(seqid));
          hafOperateLog.setOperator(securityInfo.getUserName());
          hafOperateLog.setOpIp(securityInfo.getUserIp());
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
          hafOperateLogDAO.insert(hafOperateLog);
      }
    }catch(BusinessException be){
      throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  /**
   * 批量复核
   */
  public void bizcheckAll(List list, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      CollLoanbackcheckDTO collLoanbackcheckDTO_in=null;
//      for(int i=0;i<list.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)list.get(0);
        String seqid="";
        //根据批次号得到AA306的ID
        List seqidList=pickHeadDAO.getPickHeadIDByButenum(collLoanbackcheckDTO.getBatch_num());
        for(int j=0;j<seqidList.size();j++){
          collLoanbackcheckDTO_in=new CollLoanbackcheckDTO();
          collLoanbackcheckDTO_in=(CollLoanbackcheckDTO)seqidList.get(j);
          seqid=collLoanbackcheckDTO_in.getSeqid();
          //判断复核人与操作员是否是同一人
          OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
//          String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//          if(securityInfo.getUserName().equals(userName)){
//            throw new BusinessException("第"+(j+1)+"笔业务的制单人与复核人不能为同一人!");
//          }
          //根据ID修改AA306和AA101的业务状态
          PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
          pickhead.setPickSatatus(new BigDecimal(4));
          orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
          //插入AA319和BA003
          // 插入AA319：
//          PickBizActivityLog pickBizActivityLog = new PickBizActivityLog();
//          pickBizActivityLog.setBizid(new Integer(seqid));
//          pickBizActivityLog.setAction(new Integer(4));
//          pickBizActivityLog.setOpTime(new Date());
//          pickBizActivityLog.setOperator(securityInfo.getUserName());
//          pickBizActivityLogDAO.insert(pickBizActivityLog);
//            // 插入BA003：
//            HafOperateLog hafOperateLog = new HafOperateLog();
//            hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//            hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
//            hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_CHECKS);
//            hafOperateLog.setOpBizId(new Integer(seqid));
//            hafOperateLog.setOperator(securityInfo.getUserName());
//            hafOperateLog.setOpIp(securityInfo.getUserIp());
//            hafOperateLog.setOpTime(new Date());
//            hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
//            hafOperateLogDAO.insert(hafOperateLog);
        }
        
//      }
    }catch(BusinessException be){
       throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  /**
   * 批量撤销复核
   */
  public void delbizcheckAll(List list, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      CollLoanbackcheckDTO collLoanbackcheckDTO_in=null;
//      for(int i=0;i<list.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)list.get(0);
        String seqid="";
        //根据批次号得到AA306的ID
        List seqidList=pickHeadDAO.getPickHeadIDByButenum(collLoanbackcheckDTO.getBatch_num());
        for(int j=0;j<seqidList.size();j++){
          collLoanbackcheckDTO_in=new CollLoanbackcheckDTO();
          collLoanbackcheckDTO_in=(CollLoanbackcheckDTO)seqidList.get(j);
          seqid=collLoanbackcheckDTO_in.getSeqid();
          //判断复核人与操作员是否是同一人
          OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
          String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//          if(securityInfo.getUserName().equals(userName)){
//            throw new BusinessException("第"+(j+1)+"笔业务的制单人与复核人不能为同一人!");
//          }
          //根据ID修改AA306和AA101的业务状态
          PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
          pickhead.setPickSatatus(new BigDecimal(3));
          orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
          //操作AA319和BA003
          // 删除AA319：
//          BizActivityLog bizActivityLog = new BizActivityLog();
//          bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(seqid,
//              "4", "D");
//          bizActivityLogDAO.deleteWuht(bizActivityLog);
//            // 插入BA003：
//            HafOperateLog hafOperateLog = new HafOperateLog();
//            hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//            hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
//            hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_REVOCATION);
//            hafOperateLog.setOpBizId(new Integer(seqid));
//            hafOperateLog.setOperator(securityInfo.getUserName());
//            hafOperateLog.setOpIp(securityInfo.getUserIp());
//            hafOperateLog.setOpTime(new Date());
//            hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
//            hafOperateLogDAO.insert(hafOperateLog);
        }
//      }
    }catch(BusinessException be){
      throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }

  

}
