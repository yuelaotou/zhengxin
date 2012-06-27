package org.xpup.hafmis.signjoint.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
import org.xpup.hafmis.signjoint.dto.TempDTO;
import org.xpup.hafmis.signjoint.entity.Sign;


public interface ISignjointBS {
  //插入签约信息
  public String saveSign(RequestSignDTO dto);
  //根据唯一标识查询出所有签约数据
  public Sign querySignBySignNum(String sign);
  //查询余额
  public String queryBalance(BaseInfoDTO dto);
  //查询余额明细
  public String queryListBalance(BaseInfoDTO dto,String startdate,String enddate);
  //查询贷款余额
  public String queryBorrowBalance(BaseInfoDTO dto);
  //查询贷款余额明细
  public String queryBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate);
  //执行老表与新表数据同步
  public void execSynProcdure();
//------------------------------------------------阜新库
  //插入签约信息
  public String saveNewSign(RequestSignDTO dto);
  //查询余额
  public String queryNewBalance(BaseInfoDTO dto);
  //查询余额明细
  public String queryNewListBalance(BaseInfoDTO dto,String startdate,String enddate);
  //查询贷款余额
  public String queryNewBorrowBalance(BaseInfoDTO dto);
  //查询贷款余额明细
  public String queryNewBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate);
//------------------------------------------------阜新库
  
//------------------------------------------------批量签约

  //列出所有该企业的员工信息
  public List queryAllEmpInfo(Pagination pagination);
  //将导入文本批量插入数据库
  public void signImpBatch(List headlist,List bodylist) throws BusinessException;
  //根据员工编号和银行卡号获得暂存表中的员工信息
  public RequestSignDTO getSingleEmpInfoByEmpidAndCardnum(String empid,String bankcardid);
  //根据员工编号和银行卡号删除暂存表中的员工信息
  public void deleteUserInfo(String orgid,String empid,String bankcardid) throws BusinessException ;
  //删除在暂存表中的一个单位下的所有员工
  public void deleteAll(String orgid) throws BusinessException;
  //查询在暂存表中该单位下的所有员工信息,导出用
  public List queryEmpListAll(Pagination pagination) throws BusinessException;
  //根据单位编号查询单位名称
  public String getOrgnameByOrgID(String orgid);
  //将用户信息插入临时表
  public void addUserInfo(TempDTO dto) throws BusinessException;
  //修改暂存表中的职工信息
  public void modifyUserInfo(TempDTO newdto,TempDTO olddto) throws BusinessException;
  //根据单位编号，职工编号，银行卡号，从暂存表中查询员工信息
  public TempDTO queryUserInfo(String orgid,String empid,String bankcardid) throws BusinessException;
  //分页查询所有历史表的数据
  public List queryHistoryList(Pagination pagination) throws BusinessException;
  //分页查询文件日志
  public List queryLog(Pagination pagination) throws BusinessException;
  
  //生成文件时，查询所有用户信息
  public List getAllUserInfo();
  

  
  public List prepareSendFile();
  
  public void prepareReceiveFile(List list)  throws Exception;
  
  public void logFile(LogDTO dto)throws Exception;
  
  public void clearTemp();
  
//------------------------------------------------批量签约
}
