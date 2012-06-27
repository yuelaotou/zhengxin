package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.business;

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
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * @author 于庆丰
 */
public class ChgorgrateBS implements IChgorgrateBS {

  private ChgOrgRateDAO chgOrgRateDAO = null;

  private OrgInfoDAO orgInfoDAO = null;

  private OrgDAO orgDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgInfoDAO(OrgInfoDAO orgInfoDAO) {
    this.orgInfoDAO = orgInfoDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public ChgorgrateAF findChgorgrateByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    ChgorgrateAF chgorgrateAF = new ChgorgrateAF();
    List collBankList = securityInfo.getCollBankList();
    List officeList = securityInfo.getOfficeList();
    chgorgrateAF.setCollBankList(collBankList);
    chgorgrateAF.setOfficeList(officeList);

    List list = new ArrayList();
    List lista = new ArrayList();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    String officeCode = null;// 办事处
    String collectionBank = null;// 归集银行
    String orgId = null;// 单位编号
    String orgName = null;// 单位名称
    String chgMonthStart = null;// 缴存年月开始
    String chgMonthEnd = null;// 缴存年月结束
    String chgDateStart = null;// 缴存日期开始
    String chgDateEnd = null;// 缴存日期结束
    Integer chgStatus = null;// 业务状态
    BigDecimal sumPreSumPay = new BigDecimal(0.00);// sum调整前
    BigDecimal sumSumPay = new BigDecimal(0.00);// sum调整后

    // 点节点,默认不查询
    if (pagination.getQueryCriterions().isEmpty()) {
      list = new ArrayList();
    } else {
      officeCode = (String) pagination.getQueryCriterions().get("officeCode");// 办事处
      collectionBank = (String) pagination.getQueryCriterions().get(
          "collectionBank");// 归集银行
      orgId = (String) pagination.getQueryCriterions().get("orgId");// 单位编号
      orgName = (String) pagination.getQueryCriterions().get("orgName");// 单位名称
      chgMonthStart = (String) pagination.getQueryCriterions().get(
          "chgMonthStart");// 缴存年月开始
      chgMonthEnd = (String) pagination.getQueryCriterions().get("chgMonthEnd");// 缴存年月结束
      chgDateStart = (String) pagination.getQueryCriterions().get(
          "chgDateStart");// 缴存日期开始
      chgDateEnd = (String) pagination.getQueryCriterions().get("chgDateEnd");// 缴存日期结束
      chgStatus = (Integer) pagination.getQueryCriterions().get("chgStatus");// 业务状态

      list = chgOrgRateDAO.queryChgorgrateListByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
    }

    if (list != null) {
      double drate = 0.0;
      for (int i = 0; i < list.size(); i++) {
        ChgOrgRate chgOrgRate = (ChgOrgRate) list.get(i);

        // 根据办事处ID转换成名字,根据归集银行ID转换名字
        String officecodeId = chgOrgRate.getOrg().getOrgInfo().getOfficecode();
        String collectionbankId = chgOrgRate.getOrg().getOrgInfo()
            .getCollectionBankId();

        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(officecodeId);
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(collectionbankId);

        String unitName = organizationUnit.getName();
        String bankName = collBank.getCollBankName();

        chgOrgRate.setReserveaB(unitName);
        chgOrgRate.setReserveaC(bankName);
        // 业务状态转换
        chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer.parseInt(""
            + chgOrgRate.getChgStatus()), BusiConst.CHGTYPESTATUS));
        String orgId_ = chgOrgRate.getOrg().getId().toString();
        BusiTools busiTools = null;
        String convertOrgId = busiTools.convertSixNumber(orgId_);
        chgOrgRate.getOrg().setId(new Integer(convertOrgId));
        BigDecimal oldOrgPay = chgOrgRate.getOldOrgPay();
        BigDecimal oldEmpPay = chgOrgRate.getOldEmpPay();
        BigDecimal orgemppay = new BigDecimal(0);
        if (oldOrgPay != null || !oldOrgPay.equals("") && oldEmpPay != null
            || !oldEmpPay.equals("")) {
          orgemppay = oldOrgPay.add(oldEmpPay);
        }
        if (!orgemppay.toString().equals("0")) {
          String corId = chgOrgRate.getId().toString();
          BigDecimal rate = chgOrgRateDAO.queryRate(new Integer(corId));
          drate = rate.doubleValue() * 100;
          // chgOrgRate.setRate(rate);
          chgOrgRate.setRate_(new Double(drate).toString().substring(0,
              (new Double(drate).toString().indexOf(".")) + 2)
              + "%");
        } else {
          chgOrgRate.setRate_("0.0%");
        }
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("01")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+"12");
          
        }else
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("12")||chgOrgRate.getChgMonth().substring(4, 6).equals("11")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
          
        }else{
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4)))+"0"+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
        }
        chgOrgRate.setPay(chgOrgRate.getSumPay().subtract(chgOrgRate.getPreSumPay()));
        
        
      }
    }

    if (list.size() != 0) {
      lista = chgOrgRateDAO.queryChgorgrateListSizeByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
      List listb = chgOrgRateDAO.queryChgorgrateOrgSizeByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
      List listc = chgOrgRateDAO.queryCountsByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
      int count = listb.size();
      int counts = listc.size();
      int paginationCount = lista.size();

      for (int j = 0; j < lista.size(); j++) {
        double d_rate = 0.0;
        ChgOrgRate chgOrgRate = (ChgOrgRate) lista.get(j);
        // 根据办事处ID转换成名字,根据归集银行ID转换名字
        String officecodeId = chgOrgRate.getOrg().getOrgInfo().getOfficecode();
        String collectionbankId = chgOrgRate.getOrg().getOrgInfo()
            .getCollectionBankId();

        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(officecodeId);
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(collectionbankId);

        String unitName = organizationUnit.getName();
        String bankName = collBank.getCollBankName();

        // 业务状态转换
        chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer.parseInt(""
            + chgOrgRate.getChgStatus()), BusiConst.CHGTYPESTATUS));
        // 比率
        BigDecimal oldOrgPay = chgOrgRate.getOldOrgPay();
        BigDecimal oldEmpPay = chgOrgRate.getOldEmpPay();
        BigDecimal orgemppay = new BigDecimal(0);
        if (oldOrgPay != null || !oldOrgPay.equals("") && oldEmpPay != null
            || !oldEmpPay.equals("")) {
          orgemppay = oldOrgPay.add(oldEmpPay);
        }
        if (!orgemppay.toString().equals("0")) {
          String corId = chgOrgRate.getId().toString();
          BigDecimal rate = chgOrgRateDAO.queryRate(new Integer(corId));
          d_rate = rate.doubleValue() * 100;
          // chgOrgRate.setRate(rate);
          chgOrgRate.setRate_(new Double(d_rate).toString().substring(0,
              (new Double(d_rate).toString().indexOf(".")) + 2)
              + "%");
        } else {
          chgOrgRate.setRate_("0.0%");
        }

        chgOrgRate.setReserveaB(unitName);
        chgOrgRate.setReserveaC(bankName);
        // sum金额
        sumPreSumPay = sumPreSumPay.add(chgOrgRate.getOldEmpPay().add(
            chgOrgRate.getOldOrgPay()));
        sumSumPay = sumSumPay.add(chgOrgRate.getEmpPay().add(
            chgOrgRate.getOrgPay()));
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("01")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+"12");
          
        }else
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("12")||chgOrgRate.getChgMonth().substring(4, 6).equals("11")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
          
        }else{
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4)))+"0"+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
        }
        chgOrgRate.setPay(chgOrgRate.getSumPay().subtract(chgOrgRate.getPreSumPay()));
      }
      pagination.setNrOfElements(paginationCount);
      chgorgrateAF.setSumPre(sumPreSumPay);
      chgorgrateAF.setSumSith(sumSumPay);
      chgorgrateAF.setOrgCount(new Integer(count));
      chgorgrateAF.setCounts(new Integer(counts));
    } else {
      int paginationCount = 0;
      pagination.setNrOfElements(paginationCount);
    }
    chgorgrateAF.setList(list);
    chgorgrateAF.setAlllist(lista);
    return chgorgrateAF;
  }
}
