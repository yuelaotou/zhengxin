package org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface;

import java.nio.BufferUnderflowException;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF;

public interface ItranoutBS {
  public String queryTranOutHeadByTailId(String tailid) throws Exception;
  public void updateByTranOutHeadId(String headid) throws BusinessException, Exception;
  // 根据条件查询demo记录
  // public List findtranoutByCriterions(String id) throws
  // Exception,BusinessException;
  public Org fingInOrgInfo(String orgid)throws BusinessException;
  // Ajax 查询转出单位名称
  public TranAF findtranoutOrgName(String id, Pagination pagination,SecurityInfo securityInfo)throws Exception, BusinessException;
  // 查询单位信息
  public Org fingOrgInfo(String orgid,SecurityInfo securityInfo) throws BusinessException ;

  // 根据职工编号查询职工信息
  public TranAddAF findEmpInfo(String empid,String orgid,SecurityInfo securityInfo) throws Exception,BusinessException;

  // 删除但个记录
  public void deleteTailInfo(String tailid, SecurityInfo securityInfo) throws Exception, BusinessException ;
     
  // 全部删除
  public void deleteAll(List taillist, SecurityInfo securityInfo)throws Exception, BusinessException;

  // 转出维护，查询列表
  public List queryOrgInfoTb(String outOrgid, String inOrgid,
      Pagination pagination) throws Exception, BufferUnderflowException;

  // 查询票据编号
  public List FindNot_num(String orgid) throws Exception;
  //根据单位编号流水ID和业务类型查询归集银行名称
  public String queryCollBankName(String orgId,String bizId, String bizType, 
      SecurityInfo securityInfo) throws Exception;

  // 添加:插入员工信息
  public void addTranTail(TranAddAF tranAddAF,SecurityInfo securityInfo) throws BusinessException,Exception;
  
  // 通过转出单位编号查询 3-9 Pk id
  public List FindOutPkid(String outOrgid) throws Exception, BusinessException;

  // 获取凭证号
  public String GetOfficeCode(String orgid) throws Exception;
  public String GetOfficeCode_yg(String orgid) throws Exception;
  // 完成转出查询总金额
// public BigDecimal FindSumSal(String orgid) throws Exception;

  //办理转出-- 完成转出 插入 AA101
  public String updateOrgHafaccountFlow(String pkid,String docNum,String noteNum,SecurityInfo securityInfo)
  throws Exception ;
  
  //转出维护－－完成转出
  public void updateOrgHafaccountFlowTb(String pkid, String docNum,SecurityInfo securityInfo) throws Exception ;

  // 转出维护--默认查询
  public TranTbAF findTranListBydefaultWZQ(Pagination pagination,SecurityInfo securityInfos) throws Exception, BusinessException;
  public TranTbAF findTranListBydefaultWZQ_yg(Pagination pagination,SecurityInfo securityInfos) throws Exception, BusinessException;
  public String findCollBank(String collBankid) throws Exception, BusinessException ;
  // 转出维护--删除单位信息
  public void  DeleteOrg(String pkid,String ip,String username,String BizDate, SecurityInfo securityInfo)throws Exception,BufferUnderflowException ;
  
  // 转出维护－－修改
  public TranOutHead FindOrgTb(String pkid)throws Exception ;
  
  // 转出维护--撤销转出
  public void UpdateTranHead(String pkid,String username,String ip,String setDates) throws Exception;
  public String queryMakerPara() throws Exception;
  //转出维护--完成转出
  public String GetOfficeCodeTb(String Headpkid) throws Exception;
  public String GetOfficeCodeTb_yg(String Headpkid) throws Exception;
  public String GetBankName_yg(String bankid) throws Exception;
  public String FindAA103_DayTime(String collbankid) throws Exception;
  public String queryNoteNum() throws Exception;
  // 批量导出
  public List findPaylistBatch(Pagination pagination) throws Exception;
  


  
  // 批量导入
  public List addTranoutListBatch(List addtranoutHeadImportList,List addtranoutListImportList,
      String orgOutid,String orgOutName,String orgInId,String orgInName,
      String noteNum,String ip,String name,String date,SecurityInfo securityInfo)
      throws BusinessException,Exception;
  public List getTranTailListPrint(String headid);
  //凭证打印(套打)
  public TranTbPrintAF printCredence(String headid) throws BusinessException,Exception;
  //批量打印
  public List printAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException,Exception;
  
  public TranAF findTranOutInfoMX(Pagination pagination)throws Exception;
  public List findTranOutInfoMXPrint(String headid)throws Exception;
  public void updateTranHeadTranIn(String pkid, String username, String ip,
      String setDates) throws Exception ;
  public boolean findAdjustWrongFAccountByOrgid(String orgid ,SecurityInfo securityInfo);
  public boolean check(String orgID);
  public String findtraninEmpInfo(String name,String cardNum,String empid, String orgid)throws Exception,BusinessException;
  public void referringDate(String pkid, String ip, String userName, String dates, SecurityInfo securityInfo, String temp_P) throws BusinessException;
  public void pprovalDate(String pkid, String ip, String userName, String dates, SecurityInfo securityInfo, String temp_P) throws BusinessException;
  public void pickUpData(String outOrgId, SecurityInfo securityInfo)throws BusinessException;
  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception ;
  
  // 查询转出对应转入已经入帐的列表
  public List queryTransInIsFiveList(SecurityInfo securityInfo) throws Exception, BufferUnderflowException;
}
















