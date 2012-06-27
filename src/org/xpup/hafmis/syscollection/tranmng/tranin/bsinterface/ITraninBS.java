package org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.TranInHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TranTbPrintAF;

/**
 * shiyan
 */
public interface ITraninBS {
  // 打印的数据
  public TraninVidAF print_sy(String tranInHeadId,SecurityInfo securityInfo) throws BusinessException;

  // 根据条件查询记录
  public List findTraninListByCriterions(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException;
  public List queryTraninVid_sy_yg(Pagination pagination,SecurityInfo securityInfo)
  throws BusinessException;
  // 根据id查找票据编号
  public TraninAddAF findTranInHeadById(Integer id, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  public String FindAA103_DayTime(String collbankid) throws Exception;
  public String queryNoteNum() throws Exception;
  // 根据状态查找转入信息
  public List queryTranInHeandInOrgId(String inOrgId, String tranStatus)
      throws Exception, BusinessException;

  // 插入转入尾表
  public void insertTranInTail(TranInTail tranInTail) throws Exception,
      BusinessException;

  // 插入转入头表
  public Serializable insertTranInHead(TranInHead tranInHead) throws Exception,
      BusinessException;

  // 根据尾表id
  public TranInTail queryTranInTailById(Integer id,SecurityInfo securityInfo) throws Exception,
      BusinessException;
  public TranInHead queryTranInHead(String id) throws Exception,
  BusinessException;

  // 更新尾表
  public void updataTranInTail_sy(TranInTail tranInTail, String tranInHeadId,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 删除尾表
  public void deleteTranInTail_sy(Integer integer, String tranInHeadById,
      String orgId,SecurityInfo securityInfo) throws Exception, BusinessException;

  // 统计转入尾表个数
  public int countTraninListByCriterions(String id,SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 删除头表记录
  public void deleteTranInHead_sy(String tranInHeadById, String orgId,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 转入删除页面
  public void deletePageList_sy(String tranInHeadById,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 删除页面信息
  public void deletePageList(String tranInHeadById,SecurityInfo securityInfo)
      throws BusinessException;

  // 查找org信息
  public Org queryOrgDAO(Integer id,SecurityInfo securityInfo) throws Exception, BusinessException;
  public Org queryOrg_yg(Integer id) throws Exception, BusinessException;

  // 查找转入单位
  public TranInOrg queryTranInOrg(Integer id) throws Exception,
      BusinessException;

  // 统计相关转入总和
  public Pagination countTraninListAll(Pagination pagination,SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 导入数据
  public void modifyTraninBatch(List traninImportList1, List traninImportList2,
      String inOrgId,SecurityInfo securityInfo) throws Exception, BusinessException;

  // 改变头表状态
  public void updataTranInHead(String tranInHeadId,SecurityInfo securityInfo,String noteNum)
      throws Exception, BusinessException;

  // 自动生成id
  public Integer makeEmpIdSL_sy() throws Exception, BusinessException;

  // 判断同一职工姓名和证件号码的人是否在该单位存在
  public List getEmp_sy(String orgID, String empName, String cardNum)
      throws Exception, BusinessException;

  // 为待转入界面准备数据
  public List queryTranOutListByCriterionsAll_sy(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 转入维护登陆查询页面
  public List queryTraninVid_sy(Pagination pagination,SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 待转入完成
  public String saveTranin_sy(String inOrgId, String tranOutHeadId,
      String tranOutOrgid,SecurityInfo securityInfo) throws BusinessException;

  // 批量导处插入BA003
  public void insertHafOperateLog_sy(String orgId,SecurityInfo securityInfo);

  // 维护修改插入BA003
  public String modiftHafOperateLog_sy(String tranInHeadId,SecurityInfo securityInfo);

  // 转入添加
  public String addTranInTail_sy(String inOrgId, String noteNum,
      TranInTail tranInTail,SecurityInfo securityInfo) throws BusinessException;

//凭证打印(套打)
  public TranTbPrintAF printCredence(String headid) throws BusinessException,Exception;
  public org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printCredence_yg(String headid) throws BusinessException,Exception;
  public org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printCredence_yga(String headid) throws BusinessException,Exception;
  //批量打印
  public List printAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException,Exception;
  
  // 所有公司下姓名身份证相同查找emp
  public List queryEmp_sy(String empName, String cardNum);
  //本单位下有两个emp
  public List querySameCompanyEmp_sy(String inOrgId,String empName, String cardNum);
  // 添加身份姓名相同的emp
  public void addTranInTail2_sy(String inOrgId, String noteNum,
      TranInTail tranInTail1, String empId,SecurityInfo securityInfo);

  // 转入维护——撤消转入登记
  public void adjustTranin_sy(String tranInHeadId,SecurityInfo securityInfo) throws BusinessException;

  public List findEmployee(Pagination pagination);
  //方法是为了显示转入的详细页面
  public TraninAF findTraninListByCriterionsAAC(Pagination pagination, SecurityInfo securityInfo);
  //转入维护完成
  public void updataTranInVidHead(String tranInHeadId, SecurityInfo securityInfo, String noteNum)throws BusinessException;
  /**
   * 查询姓名不同但是身份证号相同的职工信息
   * @param empName
   * @param cardNum
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumSame(String empName, String cardNum) throws BusinessException, Exception ;
  public List tranoutTailReason(String tranheadid) throws BusinessException, Exception ;
  /**
   * 修改时查询姓名不同但是身份证号相同的职工信息
   * @param empName
   * @param cardNum
   * @param empId
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isUpdateCardNumSame(String empName, String cardNum, String empId) throws BusinessException, Exception ;
//中心版的时候提取数据
  public void pickupDateAll(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
  //单位版的时候提交数据
  public void referringDataInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
  //单位版的时候撤销提交数据
  public void pprovalDataInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
//单位版的时候提交数据
  public void referringDataFirstInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
  //单位版的时候撤销提交数据
  public void pprovalDataFirstInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
}
