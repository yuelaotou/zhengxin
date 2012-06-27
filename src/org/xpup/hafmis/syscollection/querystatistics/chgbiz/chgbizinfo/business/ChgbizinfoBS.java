package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonHead;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.bsinterface.IChgbizinfoBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.dto.ChgbizinfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.form.ChgbizinfoAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * @author 于庆丰
 */
public class ChgbizinfoBS implements IChgbizinfoBS {

  private ChgOrgRateDAO chgOrgRateDAO = null;

  private OrgDAO orgDAO = null;

  private ChgPersonHeadDAO chgPersonHeadDAO=null;
  
  private ChgPaymentHeadDAO chgPaymentHeadDAO=null;
  
  private EmpDAO empDAO=null;



  public ChgbizinfoAF findChgorgrateByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    ChgbizinfoAF chgbizinfoAF = new ChgbizinfoAF();
    List collBankList = securityInfo.getCollBankList();
    List officeList = securityInfo.getOfficeList();
    chgbizinfoAF.setCollBankList(collBankList);
    chgbizinfoAF.setOfficeList(officeList);

    List list1 = new ArrayList();
    List list2 = new ArrayList();
    List list3 = new ArrayList();
//    int start = pagination.getFirstElementOnPage() - 1;
//    int pageSize = pagination.getPageSize();
//    String orderBy = (String) pagination.getOrderBy();
//    String order = pagination.getOrderother();
//    String officeCode = null;// 办事处
//    String collectionBank = null;// 归集银行
    String orgId = null;// 单位编号
    String orgName = null;// 单位名称
    String chgMonth = null;// 缴存年月开始
    String bizDate = null;// 缴存年月结束
//    String chgDateStart = null;// 缴存日期开始
//    String chgDateEnd = null;// 缴存日期结束
//    Integer chgStatus = null;// 业务状态
//    BigDecimal sumPreSumPay = new BigDecimal(0.00);// sum调整前
//    BigDecimal sumSumPay = new BigDecimal(0.00);// sum调整后

    // 点节点,默认不查询
    
    if (pagination.getQueryCriterions().isEmpty()) {
      list1 = new ArrayList();
    } else {
      
      orgId = ((String) pagination.getQueryCriterions().get("orgId"));// 单位编号
      orgName = (String) pagination.getQueryCriterions().get("orgName");// 单位名称
      chgMonth = (String) pagination.getQueryCriterions().get(
          "chgMonth");// 缴存年月开始
      bizDate = (String) pagination.getQueryCriterions().get("bizDate");// 缴存年月结束
//      chgDateStart = (String) pagination.getQueryCriterions().get(
//          "chgDateStart");// 缴存日期开始
//      chgDateEnd = (String) pagination.getQueryCriterions().get("chgDateEnd");// 缴存日期结束
//      chgStatus = (Integer) pagination.getQueryCriterions().get("chgStatus");// 业务状态
      //汇缴比例调整
      if(orgName==null||"".equals(orgName)){
        list1 = chgOrgRateDAO.queryChgbizinfo_wsh1(orgId,
            null, chgMonth, bizDate);
        //工资基数调整
        list2 = chgOrgRateDAO.queryChgbizinfo_wsh2(orgId,
            null, chgMonth, bizDate);
        //人员变更
        list3 = chgOrgRateDAO.queryChgbizinfo_wsh3(orgId,
            null, chgMonth, bizDate);
      }
      if(orgName!=null&&"0".equals(orgName)){
        list1 = chgOrgRateDAO.queryChgbizinfo_wsh1(orgId,
            null, chgMonth, bizDate);
        
      }
      if(orgName!=null&&"1".equals(orgName)){
        
        //工资基数调整
        list2 = chgOrgRateDAO.queryChgbizinfo_wsh2(orgId,
            null, chgMonth, bizDate);
        //人员变更
        
      }
      if(orgName!=null&&"2".equals(orgName)){
        
        //人员变更
        list3 = chgOrgRateDAO.queryChgbizinfo_wsh3(orgId,
            null, chgMonth, bizDate);
      }
      
      Org org= orgDAO.queryById(new Integer(orgId));
      if(org!=null){
        chgbizinfoAF.setOrgId(orgId);
        chgbizinfoAF.setOrgName(org.getOrgInfo().getName());
        if(org.getOrgInfo().getTransactor()!=null&&org.getOrgInfo().getTransactor().getName()!=null){
          chgbizinfoAF.setPerson(org.getOrgInfo().getTransactor().getName());
        }
        if(org.getOrgInfo().getTransactor()!=null&&org.getOrgInfo().getTransactor().getTelephone()!=null){
          chgbizinfoAF.setTel(org.getOrgInfo().getTransactor().getTelephone());
        }
        
      }
      Integer headId=new Integer(0);
      String str[]= new String[2];
      headId=chgOrgRateDAO.getMaxHeadID_wsh(orgId, chgMonth);
      if(headId!=null&&!"".equals(headId.toString())&&headId.intValue()>0){
//       ChgPersonHeadDAO ChgPersonHeadDAO=null;
//        
//       ChgPaymentHeadDAO chgPaymentHeadDAO=null;
        ChgPersonHead chgPersonHead=chgPersonHeadDAO.queryById(headId);
        ChgPaymentHead chgPaymentHead=chgPaymentHeadDAO.queryById(headId);
        ChgOrgRate chgOrgRate=chgOrgRateDAO.queryById(headId);
        if(chgPersonHead!=null){
          str[0]=chgPersonHead.getReserveaC();
          str[1]=chgPersonHead.getReserveaB();
        }
        if(chgPaymentHead!=null){
          str[0]=chgPaymentHead.getReserveaA();
          str[1]=chgPaymentHead.getReserveaB();
        }
        if(chgOrgRate!=null){
          str[0]=chgOrgRate.getReserveaA();
          str[1]=chgOrgRate.getReserveaB();
        }
      }else{
//        str=chgOrgRateDAO.queryOrgPaySum(orgId, chgMonth);
//        if(str[0]==null){
          str[0]="";
          str[1]="";
//        }
      }
     
     if(str[0]!=null&&!"".equals(str[0])){
       chgbizinfoAF.setData_9(str[0]);
     }else{
       chgbizinfoAF.setData_9("0");
     }
     if(str[1]!=null){
       chgbizinfoAF.setData_10(str[1]);
     }
    }

    if (list3 != null) {
      int count =0;
      BigDecimal balance=new BigDecimal(0.00);
      int count1 =0;
      BigDecimal balance1=new BigDecimal(0.00);
      for (int i = 0; i < list3.size(); i++) {
        ChgbizinfoDTO chgbizinfoDTO = (ChgbizinfoDTO) list3.get(i);

        // 根据办事处ID转换成名字,根据归集银行ID转换名字
        chgbizinfoDTO.setEmpid(BusiTools.convertSixNumber(chgbizinfoDTO.getEmpid()));
        if("04".equals(chgbizinfoDTO.getType())||"5".equals(chgbizinfoDTO.getType())){
          count++;
          balance=balance.add(new BigDecimal(chgbizinfoDTO.getOldPaySum()));
        }
        if("03".equals(chgbizinfoDTO.getType())){
          count1++;
          balance1=balance1.add(new BigDecimal(chgbizinfoDTO.getOldPaySum()));
        }
        if("05".equals(chgbizinfoDTO.getType())){
          count++;
          balance=balance.add(new BigDecimal(chgbizinfoDTO.getOldPaySum()));
        }
      }
      chgbizinfoAF.setData_1(String.valueOf(count));
      chgbizinfoAF.setData_2(String.valueOf(balance));
      chgbizinfoAF.setData_3(String.valueOf(count1));
      chgbizinfoAF.setData_4(String.valueOf(balance1));
    }
    int count3 =0;
    BigDecimal balance3=new BigDecimal(0.00);
    int count2 =0;
    BigDecimal balance2=new BigDecimal(0.00);
    if (list1 != null) {
      
     
      for (int i = 0; i < list1.size(); i++) {
        ChgbizinfoDTO chgbizinfoDTO = (ChgbizinfoDTO) list1.get(i);

        // 根据办事处ID转换成名字,根据归集银行ID转换名字
        
        chgbizinfoDTO.setEmpid(BusiTools.convertSixNumber(chgbizinfoDTO.getEmpid()));
        if("02".equals(chgbizinfoDTO.getType())&&new BigDecimal(chgbizinfoDTO.getOldPaySum()).compareTo(new BigDecimal(0.00))>0){
          //count2++;
          balance2=balance2.add(new BigDecimal(chgbizinfoDTO.getOldPaySum()));
        }
        if("02".equals(chgbizinfoDTO.getType())&&new BigDecimal(chgbizinfoDTO.getOldPaySum()).compareTo(new BigDecimal(0.00))<0){
         // count3++;
          balance3=balance3.add(new BigDecimal(chgbizinfoDTO.getOldPaySum()));
        }
      }
      chgbizinfoAF.setData_5(String.valueOf(balance2));
      chgbizinfoAF.setData_6(String.valueOf(balance3));
    }
    if (list2 != null) {
      balance2=new BigDecimal("0.00");
      balance3=new BigDecimal("0.00");
      for (int i = 0; i < list2.size(); i++) {
        ChgbizinfoDTO chgbizinfoDTO = (ChgbizinfoDTO) list2.get(i);
        chgbizinfoDTO.setEmpid(BusiTools.convertSixNumber(chgbizinfoDTO.getEmpid()));
        // 根据办事处ID转换成名字,根据归集银行ID转换名字
        if("01".equals(chgbizinfoDTO.getType())&&new BigDecimal(chgbizinfoDTO.getOldPaySum()).compareTo(new BigDecimal(0.00))>0){
          //count2++;
          balance2=balance2.add(new BigDecimal(chgbizinfoDTO.getOldPaySum()));
        }
        if("01".equals(chgbizinfoDTO.getType())&&new BigDecimal(chgbizinfoDTO.getOldPaySum()).compareTo(new BigDecimal(0.00))<0){
          //count3++;
          balance3=balance3.add(new BigDecimal(chgbizinfoDTO.getOldPaySum()));
        }
        
      }
      chgbizinfoAF.setData_5(String.valueOf(new BigDecimal(chgbizinfoAF.getData_5()).add(balance2)));
      chgbizinfoAF.setData_6(String.valueOf(new BigDecimal(chgbizinfoAF.getData_6()).add(balance3)));
    }
    chgbizinfoAF.setList1(list1);
    chgbizinfoAF.setList2(list2);
    chgbizinfoAF.setList3(list3);
    return chgbizinfoAF;
  }





  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }





  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }





  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }





  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }





  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
}
