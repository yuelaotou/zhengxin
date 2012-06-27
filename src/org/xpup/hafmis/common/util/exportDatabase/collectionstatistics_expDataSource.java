package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.CollectionstatisticsExportDTO;

/** 
 * 办事处
  归集银行
  单位编号
  单位名称
  单位性质
  主管部门
  所在地区
  上月归集
  本月正常汇缴
  本月单位补缴
  本月个人补缴
  本月单位挂账
  本月调缴存
  本月归集
  比率
 * @author yqf TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 *
 */
public class collectionstatistics_expDataSource implements ExportDateSourceInterface{


  public void makeFile(File xmlfile, String xlsFile, String para, List explist) throws Exception {
    // TODO Auto-generated method stub
    ArrayList collectlist = new ArrayList();
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    CollectionstatisticsExportDTO collectionstatisticsExportDTO = new  CollectionstatisticsExportDTO();
    collectionstatisticsExportDTO.setOfficeCode("办事处");
    collectionstatisticsExportDTO.setCollectionBank("归集银行");
    collectionstatisticsExportDTO.setOrgId("单位编号");
    collectionstatisticsExportDTO.setOrgName("单位名称");
    collectionstatisticsExportDTO.setOrgCharacter("单位性质");
    collectionstatisticsExportDTO.setDeptInCharge("主管部门");
    collectionstatisticsExportDTO.setRegion("所在地区");
    collectionstatisticsExportDTO.setLastMonthCollect("上月归集");
    collectionstatisticsExportDTO.setMonthPay("正常汇缴");
    collectionstatisticsExportDTO.setOrgAddPay("单位补缴");
    collectionstatisticsExportDTO.setPersonAddPay("个人补缴");
    collectionstatisticsExportDTO.setOrgOverPay("单位挂账");
    collectionstatisticsExportDTO.setChgPay("调缴存");
    collectionstatisticsExportDTO.setThisMonthCollect("本月归集");
    collectionstatisticsExportDTO.setRate("比率");
    collectlist.add(collectionstatisticsExportDTO);
    
    try{
      for(int i=0;i<explist.size();i++){
        CollectionstatisticsExportDTO collectionstatisticsExportDTO1 = new CollectionstatisticsExportDTO();
         orgHAFAccountFlow  = (OrgHAFAccountFlow)explist.get(i);
        if(orgHAFAccountFlow.getReserveaB() != null){
          collectionstatisticsExportDTO1.setOfficeCode(orgHAFAccountFlow.getReserveaB());
        }else{
        collectionstatisticsExportDTO1.setOfficeCode(null);
        }
        if(orgHAFAccountFlow.getReserveaC() != null){
          collectionstatisticsExportDTO1.setCollectionBank(orgHAFAccountFlow.getReserveaC());
        }else{
        collectionstatisticsExportDTO1.setCollectionBank(null);
        }
        if(orgHAFAccountFlow.getOrg().getId().toString() != null){
          collectionstatisticsExportDTO1.setOrgId(orgHAFAccountFlow.getOrg().getId().toString());
        }else{
        collectionstatisticsExportDTO1.setOrgId(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getName() != null){
          collectionstatisticsExportDTO1.setOrgName(orgHAFAccountFlow.getOrg().getOrgInfo().getName());
        }else{
        collectionstatisticsExportDTO1.setOrgName(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getCharacter() != null){
          collectionstatisticsExportDTO1.setOrgCharacter(orgHAFAccountFlow.getOrg().getOrgInfo().getCharacter());
        }else{
        collectionstatisticsExportDTO1.setOrgCharacter(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getDeptInCharge() != null){
          collectionstatisticsExportDTO1.setDeptInCharge(orgHAFAccountFlow.getOrg().getOrgInfo().getDeptInCharge());
        }else{
        collectionstatisticsExportDTO1.setDeptInCharge(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getRegion() != null){
          collectionstatisticsExportDTO1.setRegion(orgHAFAccountFlow.getOrg().getOrgInfo().getRegion());
        }else{
          collectionstatisticsExportDTO1.setRegion(null);
        }
        if(orgHAFAccountFlow.getLastMonthCollection()!= null){
          collectionstatisticsExportDTO1.setLastMonthCollect(orgHAFAccountFlow.getLastMonthCollection().toString());
        }else{
        collectionstatisticsExportDTO1.setLastMonthCollect(null);
        }
        if(orgHAFAccountFlow.getMonthPay()!= null){
          collectionstatisticsExportDTO1.setMonthPay(orgHAFAccountFlow.getMonthPay().toString());
        }else{
        collectionstatisticsExportDTO1.setMonthPay(null);
        }
        if(orgHAFAccountFlow.getOrgAddPay() != null){
          collectionstatisticsExportDTO1.setOrgAddPay(orgHAFAccountFlow.getOrgAddPay().toString());
        }else{
        collectionstatisticsExportDTO1.setOrgAddPay(null);
        }
        if(orgHAFAccountFlow.getPeronaddPay() != null){
          collectionstatisticsExportDTO1.setPersonAddPay(orgHAFAccountFlow.getPeronaddPay().toString());
        }else{
        collectionstatisticsExportDTO1.setPersonAddPay(null);
        }
        if(orgHAFAccountFlow.getOrgoverPay()!= null){
          collectionstatisticsExportDTO1.setOrgOverPay(orgHAFAccountFlow.getOrgoverPay().toString());
        }else{
        collectionstatisticsExportDTO1.setOrgOverPay(null);
        }
        if(orgHAFAccountFlow.getChgPay() != null){
          collectionstatisticsExportDTO1.setChgPay(orgHAFAccountFlow.getChgPay().toString());
        }else{
        collectionstatisticsExportDTO1.setChgPay(null);
        }
        if(orgHAFAccountFlow.getThisMonthCollection() != null){
          collectionstatisticsExportDTO1.setThisMonthCollect(orgHAFAccountFlow.getThisMonthCollection().toString());
        }else{
        collectionstatisticsExportDTO1.setThisMonthCollect(null);
        }
        if(orgHAFAccountFlow.getRate() != null){
          collectionstatisticsExportDTO1.setRate(new Double(orgHAFAccountFlow.getRate()).toString());
        }else{
        collectionstatisticsExportDTO1.setRate(null);
        }
        collectlist.add(collectionstatisticsExportDTO1);
      }
      Factory faxtory = new Factory();
      Map collectionsExportMap = new HashMap();
      collectionsExportMap.put("CollectionstatisticsExport", collectlist);
      faxtory.setInfomation(xmlfile, xlsFile, collectionsExportMap, "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.");
    }catch(SystemException e){
      e.printStackTrace();
    }
  }

} 
