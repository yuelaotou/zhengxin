package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.business;

import java.math.BigDecimal;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.bsinterface.IPaymentyearstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto.PaymentyearBankDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto.PaymentyearstatisticsDTO;

/**
 * 统计查询 -- 缴存提取统计 -- 公积金缴存情况年报表
 * 
 * @author yqf 20080920
 */
public class PaymentyearstatisticsBS implements IPaymentyearstatisticsBS {
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  /**
   * 根据条件查询--市本级
   */
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO1(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    PaymentyearstatisticsDTO paymentyearstatisticsDTO = new PaymentyearstatisticsDTO();
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String bizDate = (String) pagination.getQueryCriterions().get("bizDate");
    String officeName = "";
    if(officeCode!=null){
      officeName = orgHAFAccountFlowDAO.queryOfficeName(officeCode);
      paymentyearstatisticsDTO.setOffice(officeName);
    }
    try {
      if (!pagination.getQueryCriterions().isEmpty()) {// 查询条件不为空

        /** **************** */
        /*                 */
        /* 一月 */
        /*                 */
        /** **************** */
        int nh_hs1 = 0;// 农行户数
        int nh_rs1 = 0;// 农行人数
        BigDecimal nh_je1 = new BigDecimal(0.00);// 农行金额
        int zh_hs1 = 0;// 中行户数
        int zh_rs1 = 0;// 中行人数
        BigDecimal zh_je1 = new BigDecimal(0.00);// 中行金额
        int gh_hs1 = 0;// 工行户数
        int gh_rs1 = 0;// 工行人数
        BigDecimal gh_je1 = new BigDecimal(0.00);// 工行金额
        int jh_hs1 = 0;// 建行户数
        int jh_rs1 = 0;// 建行人数
        BigDecimal jh_je1 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_1 = new BigDecimal(0.00);// 一月合计

        // 一月 农行
        PaymentyearBankDTO paymentyearBankDTO1 = null;
        paymentyearBankDTO1 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "1");
        if (paymentyearBankDTO1 != null && !"".equals(paymentyearBankDTO1)) {
          nh_hs1 = paymentyearBankDTO1.getHushu();
          nh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "1");
          nh_je1 = paymentyearBankDTO1.getJiner();
        }
        // 一月 中行
        PaymentyearBankDTO paymentyearBankDTO2 = null;
        paymentyearBankDTO2 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "2");
        if (paymentyearBankDTO2 != null && !"".equals(paymentyearBankDTO2)) {
          zh_hs1 = paymentyearBankDTO2.getHushu();
          zh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "2");
          zh_je1 = paymentyearBankDTO2.getJiner();
        }
        // 一月 工行
        PaymentyearBankDTO paymentyearBankDTO3 = null;
        paymentyearBankDTO3 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "3");
        if (paymentyearBankDTO3 != null && !"".equals(paymentyearBankDTO3)) {
          gh_hs1 = paymentyearBankDTO3.getHushu();
          gh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "3");
          gh_je1 = paymentyearBankDTO3.getJiner();
        }
        // 一月 建行
        PaymentyearBankDTO paymentyearBankDTO4 = null;
        paymentyearBankDTO4 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "4");
        if (paymentyearBankDTO4 != null && !"".equals(paymentyearBankDTO4)) {
          jh_hs1 = paymentyearBankDTO4.getHushu();
          jh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "4");
          jh_je1 = paymentyearBankDTO4.getJiner();
        }
        sum_1 = sum_1.add(nh_je1).add(zh_je1).add(gh_je1).add(jh_je1);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs1(nh_hs1);// 农行
        paymentyearstatisticsDTO.setNh_rs1(nh_rs1);
        paymentyearstatisticsDTO.setNh_je1(nh_je1);
        paymentyearstatisticsDTO.setZh_hs1(zh_hs1);// 中行
        paymentyearstatisticsDTO.setZh_rs1(zh_rs1);
        paymentyearstatisticsDTO.setZh_je1(zh_je1);
        paymentyearstatisticsDTO.setGh_hs1(gh_hs1);// 工行
        paymentyearstatisticsDTO.setGh_rs1(gh_rs1);
        paymentyearstatisticsDTO.setGh_je1(gh_je1);
        paymentyearstatisticsDTO.setJh_hs1(jh_hs1);// 建行
        paymentyearstatisticsDTO.setJh_rs1(jh_rs1);
        paymentyearstatisticsDTO.setJh_je1(jh_je1);
        paymentyearstatisticsDTO.setSum_1(sum_1);

        /** **************** */
        /*                 */
        /* 二月 */
        /*                 */
        /** **************** */
        int nh_hs2 = 0;// 农行户数
        int nh_rs2 = 0;// 农行人数
        BigDecimal nh_je2 = new BigDecimal(0.00);// 农行金额
        int zh_hs2 = 0;// 中行户数
        int zh_rs2 = 0;// 中行人数
        BigDecimal zh_je2 = new BigDecimal(0.00);// 中行金额
        int gh_hs2 = 0;// 工行户数
        int gh_rs2 = 0;// 工行人数
        BigDecimal gh_je2 = new BigDecimal(0.00);// 工行金额
        int jh_hs2 = 0;// 建行户数
        int jh_rs2 = 0;// 建行人数
        BigDecimal jh_je2 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_2 = new BigDecimal(0.00);// 二月合计

        // 二月 农行
        PaymentyearBankDTO paymentyearBankDTO5 = null;
        paymentyearBankDTO5 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "1");
        if (paymentyearBankDTO5 != null && !"".equals(paymentyearBankDTO5)) {
          nh_hs2 = paymentyearBankDTO5.getHushu();
          nh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "1");
          nh_je2 = paymentyearBankDTO5.getJiner();
        }
        // 二月 中行
        PaymentyearBankDTO paymentyearBankDTO6 = null;
        paymentyearBankDTO6 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "2");
        if (paymentyearBankDTO6 != null && !"".equals(paymentyearBankDTO6)) {
          zh_hs2 = paymentyearBankDTO6.getHushu();
          zh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "2");
          zh_je2 = paymentyearBankDTO6.getJiner();
        }
        // 二月 工行
        PaymentyearBankDTO paymentyearBankDTO7 = null;
        paymentyearBankDTO7 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "3");
        if (paymentyearBankDTO7 != null && !"".equals(paymentyearBankDTO7)) {
          gh_hs2 = paymentyearBankDTO7.getHushu();
          gh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "3");
          gh_je2 = paymentyearBankDTO7.getJiner();
        }
        // 二月 建行
        PaymentyearBankDTO paymentyearBankDTO8 = null;
        paymentyearBankDTO8 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "4");
        if (paymentyearBankDTO8 != null && !"".equals(paymentyearBankDTO8)) {
          jh_hs2 = paymentyearBankDTO8.getHushu();
          jh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "4");
          jh_je2 = paymentyearBankDTO8.getJiner();
        }
        sum_2 = sum_2.add(nh_je2).add(zh_je2).add(gh_je2).add(jh_je2);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs2(nh_hs2);// 农行
        paymentyearstatisticsDTO.setNh_rs2(nh_rs2);
        paymentyearstatisticsDTO.setNh_je2(nh_je2);
        paymentyearstatisticsDTO.setZh_hs2(zh_hs2);// 中行
        paymentyearstatisticsDTO.setZh_rs2(zh_rs2);
        paymentyearstatisticsDTO.setZh_je2(zh_je2);
        paymentyearstatisticsDTO.setGh_hs2(gh_hs2);// 工行
        paymentyearstatisticsDTO.setGh_rs2(gh_rs2);
        paymentyearstatisticsDTO.setGh_je2(gh_je2);
        paymentyearstatisticsDTO.setJh_hs2(jh_hs2);// 建行
        paymentyearstatisticsDTO.setJh_rs2(jh_rs2);
        paymentyearstatisticsDTO.setJh_je2(jh_je2);
        paymentyearstatisticsDTO.setSum_2(sum_2);

        /** **************** */
        /*                 */
        /* 三月 */
        /*                 */
        /** **************** */
        int nh_hs3 = 0;// 农行户数
        int nh_rs3 = 0;// 农行人数
        BigDecimal nh_je3 = new BigDecimal(0.00);// 农行金额
        int zh_hs3 = 0;// 中行户数
        int zh_rs3 = 0;// 中行人数
        BigDecimal zh_je3 = new BigDecimal(0.00);// 中行金额
        int gh_hs3 = 0;// 工行户数
        int gh_rs3 = 0;// 工行人数
        BigDecimal gh_je3 = new BigDecimal(0.00);// 工行金额
        int jh_hs3 = 0;// 建行户数
        int jh_rs3 = 0;// 建行人数
        BigDecimal jh_je3 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_3 = new BigDecimal(0.00);// 二月合计

        // 三月 农行
        PaymentyearBankDTO paymentyearBankDTO9 = null;
        paymentyearBankDTO9 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "03", "1");
        if (paymentyearBankDTO9 != null && !"".equals(paymentyearBankDTO9)) {
          nh_hs3 = paymentyearBankDTO9.getHushu();
          nh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "1");
          nh_je3 = paymentyearBankDTO9.getJiner();
        }
        // 三月 中行
        PaymentyearBankDTO paymentyearBankDTO10 = null;
        paymentyearBankDTO10 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "03", "2");
        if (paymentyearBankDTO10 != null && !"".equals(paymentyearBankDTO10)) {
          zh_hs3 = paymentyearBankDTO10.getHushu();
          zh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "2");
          zh_je3 = paymentyearBankDTO10.getJiner();
        }
        // 三月 工行
        PaymentyearBankDTO paymentyearBankDTO11 = null;
        paymentyearBankDTO11 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "03", "3");
        if (paymentyearBankDTO11 != null && !"".equals(paymentyearBankDTO11)) {
          gh_hs3 = paymentyearBankDTO11.getHushu();
          gh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "3");
          gh_je3 = paymentyearBankDTO11.getJiner();
        }
        // 三月 建行
        PaymentyearBankDTO paymentyearBankDTO12 = null;
        paymentyearBankDTO12 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "03", "4");
        if (paymentyearBankDTO12 != null && !"".equals(paymentyearBankDTO12)) {
          jh_hs3 = paymentyearBankDTO12.getHushu();
          jh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "4");
          jh_je3 = paymentyearBankDTO12.getJiner();
        }
        sum_3 = sum_3.add(nh_je3).add(zh_je3).add(gh_je3).add(jh_je3);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs3(nh_hs3);// 农行
        paymentyearstatisticsDTO.setNh_rs3(nh_rs3);
        paymentyearstatisticsDTO.setNh_je3(nh_je3);
        paymentyearstatisticsDTO.setZh_hs3(zh_hs3);// 中行
        paymentyearstatisticsDTO.setZh_rs3(zh_rs3);
        paymentyearstatisticsDTO.setZh_je3(zh_je3);
        paymentyearstatisticsDTO.setGh_hs3(gh_hs3);// 工行
        paymentyearstatisticsDTO.setGh_rs3(gh_rs3);
        paymentyearstatisticsDTO.setGh_je3(gh_je3);
        paymentyearstatisticsDTO.setJh_hs3(jh_hs3);// 建行
        paymentyearstatisticsDTO.setJh_rs3(jh_rs3);
        paymentyearstatisticsDTO.setJh_je3(jh_je3);
        paymentyearstatisticsDTO.setSum_3(sum_3);

        /** **************** */
        /*                 */
        /* 四月 */
        /*                 */
        /** **************** */
        int nh_hs4 = 0;// 农行户数
        int nh_rs4 = 0;// 农行人数
        BigDecimal nh_je4 = new BigDecimal(0.00);// 农行金额
        int zh_hs4 = 0;// 中行户数
        int zh_rs4 = 0;// 中行人数
        BigDecimal zh_je4 = new BigDecimal(0.00);// 中行金额
        int gh_hs4 = 0;// 工行户数
        int gh_rs4 = 0;// 工行人数
        BigDecimal gh_je4 = new BigDecimal(0.00);// 工行金额
        int jh_hs4 = 0;// 建行户数
        int jh_rs4 = 0;// 建行人数
        BigDecimal jh_je4 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_4 = new BigDecimal(0.00);// 二月合计

        // 四月 农行
        PaymentyearBankDTO paymentyearBankDTO13 = null;
        paymentyearBankDTO13 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "1");
        if (paymentyearBankDTO13 != null && !"".equals(paymentyearBankDTO13)) {
          nh_hs4 = paymentyearBankDTO13.getHushu();
          nh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "1");
          nh_je4 = paymentyearBankDTO13.getJiner();
        }
        // 四月 中行
        PaymentyearBankDTO paymentyearBankDTO14 = null;
        paymentyearBankDTO14 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "2");
        if (paymentyearBankDTO14 != null && !"".equals(paymentyearBankDTO14)) {
          zh_hs4 = paymentyearBankDTO14.getHushu();
          zh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "2");
          zh_je4 = paymentyearBankDTO14.getJiner();
        }
        // 四月 工行
        PaymentyearBankDTO paymentyearBankDTO15 = null;
        paymentyearBankDTO15 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "3");
        if (paymentyearBankDTO15 != null && !"".equals(paymentyearBankDTO15)) {
          gh_hs4 = paymentyearBankDTO15.getHushu();
          gh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "3");
          gh_je4 = paymentyearBankDTO15.getJiner();
        }
        // 四月 建行
        PaymentyearBankDTO paymentyearBankDTO16 = null;
        paymentyearBankDTO16 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "4");
        if (paymentyearBankDTO16 != null && !"".equals(paymentyearBankDTO16)) {
          jh_hs4 = paymentyearBankDTO16.getHushu();
          jh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "4");
          jh_je4 = paymentyearBankDTO16.getJiner();
        }
        sum_4 = sum_4.add(nh_je4).add(zh_je4).add(gh_je4).add(jh_je4);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs4(nh_hs4);// 农行
        paymentyearstatisticsDTO.setNh_rs4(nh_rs4);
        paymentyearstatisticsDTO.setNh_je4(nh_je4);
        paymentyearstatisticsDTO.setZh_hs4(zh_hs4);// 中行
        paymentyearstatisticsDTO.setZh_rs4(zh_rs4);
        paymentyearstatisticsDTO.setZh_je4(zh_je4);
        paymentyearstatisticsDTO.setGh_hs4(gh_hs4);// 工行
        paymentyearstatisticsDTO.setGh_rs4(gh_rs4);
        paymentyearstatisticsDTO.setGh_je4(gh_je4);
        paymentyearstatisticsDTO.setJh_hs4(jh_hs4);// 建行
        paymentyearstatisticsDTO.setJh_rs4(jh_rs4);
        paymentyearstatisticsDTO.setJh_je4(jh_je4);
        paymentyearstatisticsDTO.setSum_4(sum_4);

        /** **************** */
        /*                 */
        /* 五月 */
        /*                 */
        /** **************** */
        int nh_hs5 = 0;// 农行户数
        int nh_rs5 = 0;// 农行人数
        BigDecimal nh_je5 = new BigDecimal(0.00);// 农行金额
        int zh_hs5 = 0;// 中行户数
        int zh_rs5 = 0;// 中行人数
        BigDecimal zh_je5 = new BigDecimal(0.00);// 中行金额
        int gh_hs5 = 0;// 工行户数
        int gh_rs5 = 0;// 工行人数
        BigDecimal gh_je5 = new BigDecimal(0.00);// 工行金额
        int jh_hs5 = 0;// 建行户数
        int jh_rs5 = 0;// 建行人数
        BigDecimal jh_je5 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_5 = new BigDecimal(0.00);// 二月合计

        // 五月 农行
        PaymentyearBankDTO paymentyearBankDTO17 = null;
        paymentyearBankDTO17 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "1");
        if (paymentyearBankDTO17 != null && !"".equals(paymentyearBankDTO17)) {
          nh_hs5 = paymentyearBankDTO17.getHushu();
          nh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "1");
          nh_je5 = paymentyearBankDTO17.getJiner();
        }
        // 五月 中行
        PaymentyearBankDTO paymentyearBankDTO18 = null;
        paymentyearBankDTO18 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "2");
        if (paymentyearBankDTO18 != null && !"".equals(paymentyearBankDTO18)) {
          zh_hs5 = paymentyearBankDTO18.getHushu();
          zh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "2");
          zh_je5 = paymentyearBankDTO18.getJiner();
        }
        // 五月 工行
        PaymentyearBankDTO paymentyearBankDTO19 = null;
        paymentyearBankDTO19 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "3");
        if (paymentyearBankDTO19 != null && !"".equals(paymentyearBankDTO19)) {
          gh_hs5 = paymentyearBankDTO19.getHushu();
          gh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "3");
          gh_je5 = paymentyearBankDTO19.getJiner();
        }
        // 五月 建行
        PaymentyearBankDTO paymentyearBankDTO20 = null;
        paymentyearBankDTO20 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "4");
        if (paymentyearBankDTO20 != null && !"".equals(paymentyearBankDTO20)) {
          jh_hs5 = paymentyearBankDTO20.getHushu();
          jh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "4");
          jh_je5 = paymentyearBankDTO20.getJiner();
        }
        sum_5 = sum_5.add(nh_je5).add(zh_je5).add(gh_je5).add(jh_je5);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs5(nh_hs5);// 农行
        paymentyearstatisticsDTO.setNh_rs5(nh_rs5);
        paymentyearstatisticsDTO.setNh_je5(nh_je5);
        paymentyearstatisticsDTO.setZh_hs5(zh_hs5);// 中行
        paymentyearstatisticsDTO.setZh_rs5(zh_rs5);
        paymentyearstatisticsDTO.setZh_je5(zh_je5);
        paymentyearstatisticsDTO.setGh_hs5(gh_hs5);// 工行
        paymentyearstatisticsDTO.setGh_rs5(gh_rs5);
        paymentyearstatisticsDTO.setGh_je5(gh_je5);
        paymentyearstatisticsDTO.setJh_hs5(jh_hs5);// 建行
        paymentyearstatisticsDTO.setJh_rs5(jh_rs5);
        paymentyearstatisticsDTO.setJh_je5(jh_je5);
        paymentyearstatisticsDTO.setSum_5(sum_5);

        /** **************** */
        /*                 */
        /* 六月 */
        /*                 */
        /** **************** */
        int nh_hs6 = 0;// 农行户数
        int nh_rs6 = 0;// 农行人数
        BigDecimal nh_je6 = new BigDecimal(0.00);// 农行金额
        int zh_hs6 = 0;// 中行户数
        int zh_rs6 = 0;// 中行人数
        BigDecimal zh_je6 = new BigDecimal(0.00);// 中行金额
        int gh_hs6 = 0;// 工行户数
        int gh_rs6 = 0;// 工行人数
        BigDecimal gh_je6 = new BigDecimal(0.00);// 工行金额
        int jh_hs6 = 0;// 建行户数
        int jh_rs6 = 0;// 建行人数
        BigDecimal jh_je6 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_6 = new BigDecimal(0.00);// 二月合计

        // 六月 农行
        PaymentyearBankDTO paymentyearBankDTO21 = null;
        paymentyearBankDTO21 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "1");
        if (paymentyearBankDTO21 != null && !"".equals(paymentyearBankDTO21)) {
          nh_hs6 = paymentyearBankDTO21.getHushu();
          nh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "1");
          nh_je6 = paymentyearBankDTO21.getJiner();
        }
        // 六月 中行
        PaymentyearBankDTO paymentyearBankDTO22 = null;
        paymentyearBankDTO22 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "2");
        if (paymentyearBankDTO22 != null && !"".equals(paymentyearBankDTO22)) {
          zh_hs6 = paymentyearBankDTO22.getHushu();
          zh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "2");
          zh_je6 = paymentyearBankDTO22.getJiner();
        }
        // 六月 工行
        PaymentyearBankDTO paymentyearBankDTO23 = null;
        paymentyearBankDTO23 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "3");
        if (paymentyearBankDTO23 != null && !"".equals(paymentyearBankDTO23)) {
          gh_hs6 = paymentyearBankDTO23.getHushu();
          gh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "3");
          gh_je6 = paymentyearBankDTO23.getJiner();
        }
        // 六月 建行
        PaymentyearBankDTO paymentyearBankDTO24 = null;
        paymentyearBankDTO24 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "4");
        if (paymentyearBankDTO24 != null && !"".equals(paymentyearBankDTO24)) {
          jh_hs6 = paymentyearBankDTO24.getHushu();
          jh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "4");
          jh_je6 = paymentyearBankDTO24.getJiner();
        }
        sum_6 = sum_6.add(nh_je6).add(zh_je6).add(gh_je6).add(jh_je6);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs6(nh_hs6);// 农行
        paymentyearstatisticsDTO.setNh_rs6(nh_rs6);
        paymentyearstatisticsDTO.setNh_je6(nh_je6);
        paymentyearstatisticsDTO.setZh_hs6(zh_hs6);// 中行
        paymentyearstatisticsDTO.setZh_rs6(zh_rs6);
        paymentyearstatisticsDTO.setZh_je6(zh_je6);
        paymentyearstatisticsDTO.setGh_hs6(gh_hs6);// 工行
        paymentyearstatisticsDTO.setGh_rs6(gh_rs6);
        paymentyearstatisticsDTO.setGh_je6(gh_je6);
        paymentyearstatisticsDTO.setJh_hs6(jh_hs6);// 建行
        paymentyearstatisticsDTO.setJh_rs6(jh_rs6);
        paymentyearstatisticsDTO.setJh_je6(jh_je6);
        paymentyearstatisticsDTO.setSum_6(sum_6);

        /** **************** */
        /*                 */
        /* 七月 */
        /*                 */
        /** **************** */
        int nh_hs7 = 0;// 农行户数
        int nh_rs7 = 0;// 农行人数
        BigDecimal nh_je7 = new BigDecimal(0.00);// 农行金额
        int zh_hs7 = 0;// 中行户数
        int zh_rs7 = 0;// 中行人数
        BigDecimal zh_je7 = new BigDecimal(0.00);// 中行金额
        int gh_hs7 = 0;// 工行户数
        int gh_rs7 = 0;// 工行人数
        BigDecimal gh_je7 = new BigDecimal(0.00);// 工行金额
        int jh_hs7 = 0;// 建行户数
        int jh_rs7 = 0;// 建行人数
        BigDecimal jh_je7 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_7 = new BigDecimal(0.00);// 二月合计

        // 六月 农行
        PaymentyearBankDTO paymentyearBankDTO25 = null;
        paymentyearBankDTO25 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "1");
        if (paymentyearBankDTO25 != null && !"".equals(paymentyearBankDTO25)) {
          nh_hs7 = paymentyearBankDTO25.getHushu();
          nh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "1");
          nh_je7 = paymentyearBankDTO25.getJiner();
        }
        // 六月 中行
        PaymentyearBankDTO paymentyearBankDTO26 = null;
        paymentyearBankDTO26 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "2");
        if (paymentyearBankDTO26 != null && !"".equals(paymentyearBankDTO26)) {
          zh_hs7 = paymentyearBankDTO26.getHushu();
          zh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "2");
          zh_je7 = paymentyearBankDTO26.getJiner();
        }
        // 六月 工行
        PaymentyearBankDTO paymentyearBankDTO27 = null;
        paymentyearBankDTO27 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "3");
        if (paymentyearBankDTO27 != null && !"".equals(paymentyearBankDTO27)) {
          gh_hs7 = paymentyearBankDTO27.getHushu();
          gh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "3");
          gh_je7 = paymentyearBankDTO27.getJiner();
        }
        // 六月 建行
        PaymentyearBankDTO paymentyearBankDTO28 = null;
        paymentyearBankDTO28 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "4");
        if (paymentyearBankDTO28 != null && !"".equals(paymentyearBankDTO28)) {
          jh_hs7 = paymentyearBankDTO28.getHushu();
          jh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "4");
          jh_je7 = paymentyearBankDTO28.getJiner();
        }
        sum_7 = sum_7.add(nh_je7).add(zh_je7).add(gh_je7).add(jh_je7);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs7(nh_hs7);// 农行
        paymentyearstatisticsDTO.setNh_rs7(nh_rs7);
        paymentyearstatisticsDTO.setNh_je7(nh_je7);
        paymentyearstatisticsDTO.setZh_hs7(zh_hs7);// 中行
        paymentyearstatisticsDTO.setZh_rs7(zh_rs7);
        paymentyearstatisticsDTO.setZh_je7(zh_je7);
        paymentyearstatisticsDTO.setGh_hs7(gh_hs7);// 工行
        paymentyearstatisticsDTO.setGh_rs7(gh_rs7);
        paymentyearstatisticsDTO.setGh_je7(gh_je7);
        paymentyearstatisticsDTO.setJh_hs7(jh_hs7);// 建行
        paymentyearstatisticsDTO.setJh_rs7(jh_rs7);
        paymentyearstatisticsDTO.setJh_je7(jh_je7);
        paymentyearstatisticsDTO.setSum_7(sum_7);

        /** **************** */
        /*                   */
        /* 八月 */
        /*                   */
        /** **************** */
        int nh_hs8 = 0;// 农行户数
        int nh_rs8 = 0;// 农行人数
        BigDecimal nh_je8 = new BigDecimal(0.00);// 农行金额
        int zh_hs8 = 0;// 中行户数
        int zh_rs8 = 0;// 中行人数
        BigDecimal zh_je8 = new BigDecimal(0.00);// 中行金额
        int gh_hs8 = 0;// 工行户数
        int gh_rs8 = 0;// 工行人数
        BigDecimal gh_je8 = new BigDecimal(0.00);// 工行金额
        int jh_hs8 = 0;// 建行户数
        int jh_rs8 = 0;// 建行人数
        BigDecimal jh_je8 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_8 = new BigDecimal(0.00);// 二月合计

        // 八月 农行
        PaymentyearBankDTO paymentyearBankDTO29 = null;
        paymentyearBankDTO29 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "1");
        if (paymentyearBankDTO29 != null && !"".equals(paymentyearBankDTO29)) {
          nh_hs8 = paymentyearBankDTO29.getHushu();
          nh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "1");
          nh_je8 = paymentyearBankDTO29.getJiner();
        }
        // 八月 中行
        PaymentyearBankDTO paymentyearBankDTO30 = null;
        paymentyearBankDTO30 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "2");
        if (paymentyearBankDTO30 != null && !"".equals(paymentyearBankDTO30)) {
          zh_hs8 = paymentyearBankDTO30.getHushu();
          zh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "2");
          zh_je8 = paymentyearBankDTO30.getJiner();
        }
        // 八月 工行
        PaymentyearBankDTO paymentyearBankDTO31 = null;
        paymentyearBankDTO31 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "3");
        if (paymentyearBankDTO31 != null && !"".equals(paymentyearBankDTO31)) {
          gh_hs8 = paymentyearBankDTO31.getHushu();
          gh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "3");
          gh_je8 = paymentyearBankDTO31.getJiner();
        }
        // 八月 建行
        PaymentyearBankDTO paymentyearBankDTO32 = null;
        paymentyearBankDTO32 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "4");
        if (paymentyearBankDTO32 != null && !"".equals(paymentyearBankDTO32)) {
          jh_hs8 = paymentyearBankDTO32.getHushu();
          jh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "4");
          jh_je8 = paymentyearBankDTO32.getJiner();
        }
        sum_8 = sum_8.add(nh_je8).add(zh_je8).add(gh_je8).add(jh_je8);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs8(nh_hs8);// 农行
        paymentyearstatisticsDTO.setNh_rs8(nh_rs8);
        paymentyearstatisticsDTO.setNh_je8(nh_je8);
        paymentyearstatisticsDTO.setZh_hs8(zh_hs8);// 中行
        paymentyearstatisticsDTO.setZh_rs8(zh_rs8);
        paymentyearstatisticsDTO.setZh_je8(zh_je8);
        paymentyearstatisticsDTO.setGh_hs8(gh_hs8);// 工行
        paymentyearstatisticsDTO.setGh_rs8(gh_rs8);
        paymentyearstatisticsDTO.setGh_je8(gh_je8);
        paymentyearstatisticsDTO.setJh_hs8(jh_hs8);// 建行
        paymentyearstatisticsDTO.setJh_rs8(jh_rs8);
        paymentyearstatisticsDTO.setJh_je8(jh_je8);
        paymentyearstatisticsDTO.setSum_8(sum_8);

        /** **************** */
        /*                   */
        /* 九月 */
        /*                   */
        /** **************** */
        int nh_hs9 = 0;// 农行户数
        int nh_rs9 = 0;// 农行人数
        BigDecimal nh_je9 = new BigDecimal(0.00);// 农行金额
        int zh_hs9 = 0;// 中行户数
        int zh_rs9 = 0;// 中行人数
        BigDecimal zh_je9 = new BigDecimal(0.00);// 中行金额
        int gh_hs9 = 0;// 工行户数
        int gh_rs9 = 0;// 工行人数
        BigDecimal gh_je9 = new BigDecimal(0.00);// 工行金额
        int jh_hs9 = 0;// 建行户数
        int jh_rs9 = 0;// 建行人数
        BigDecimal jh_je9 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_9 = new BigDecimal(0.00);// 二月合计

        // 九月 农行
        PaymentyearBankDTO paymentyearBankDTO33 = null;
        paymentyearBankDTO33 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "1");
        if (paymentyearBankDTO33 != null && !"".equals(paymentyearBankDTO33)) {
          nh_hs9 = paymentyearBankDTO33.getHushu();
          nh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "1");
          nh_je9 = paymentyearBankDTO33.getJiner();
        }
        // 九月 中行
        PaymentyearBankDTO paymentyearBankDTO34 = null;
        paymentyearBankDTO34 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "2");
        if (paymentyearBankDTO34 != null && !"".equals(paymentyearBankDTO34)) {
          zh_hs9 = paymentyearBankDTO34.getHushu();
          zh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "2");
          zh_je9 = paymentyearBankDTO34.getJiner();
        }
        // 九月 工行
        PaymentyearBankDTO paymentyearBankDTO35 = null;
        paymentyearBankDTO35 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "3");
        if (paymentyearBankDTO35 != null && !"".equals(paymentyearBankDTO35)) {
          gh_hs9 = paymentyearBankDTO35.getHushu();
          gh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "3");
          gh_je9 = paymentyearBankDTO35.getJiner();
        }
        // 九月 建行
        PaymentyearBankDTO paymentyearBankDTO36 = null;
        paymentyearBankDTO36 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "4");
        if (paymentyearBankDTO36 != null && !"".equals(paymentyearBankDTO36)) {
          jh_hs9 = paymentyearBankDTO36.getHushu();
          jh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "4");
          jh_je9 = paymentyearBankDTO36.getJiner();
        }
        sum_9 = sum_9.add(nh_je9).add(zh_je9).add(gh_je9).add(jh_je9);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs9(nh_hs9);// 农行
        paymentyearstatisticsDTO.setNh_rs9(nh_rs9);
        paymentyearstatisticsDTO.setNh_je9(nh_je9);
        paymentyearstatisticsDTO.setZh_hs9(zh_hs9);// 中行
        paymentyearstatisticsDTO.setZh_rs9(zh_rs9);
        paymentyearstatisticsDTO.setZh_je9(zh_je9);
        paymentyearstatisticsDTO.setGh_hs9(gh_hs9);// 工行
        paymentyearstatisticsDTO.setGh_rs9(gh_rs9);
        paymentyearstatisticsDTO.setGh_je9(gh_je9);
        paymentyearstatisticsDTO.setJh_hs9(jh_hs9);// 建行
        paymentyearstatisticsDTO.setJh_rs9(jh_rs9);
        paymentyearstatisticsDTO.setJh_je9(jh_je9);
        paymentyearstatisticsDTO.setSum_9(sum_9);

        /** **************** */
        /*                   */
        /* 十月 */
        /*                   */
        /** **************** */
        int nh_hs10 = 0;// 农行户数
        int nh_rs10 = 0;// 农行人数
        BigDecimal nh_je10 = new BigDecimal(0.00);// 农行金额
        int zh_hs10 = 0;// 中行户数
        int zh_rs10 = 0;// 中行人数
        BigDecimal zh_je10 = new BigDecimal(0.00);// 中行金额
        int gh_hs10 = 0;// 工行户数
        int gh_rs10 = 0;// 工行人数
        BigDecimal gh_je10 = new BigDecimal(0.00);// 工行金额
        int jh_hs10 = 0;// 建行户数
        int jh_rs10 = 0;// 建行人数
        BigDecimal jh_je10 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_10 = new BigDecimal(0.00);// 二月合计

        // 十月 农行
        PaymentyearBankDTO paymentyearBankDTO37 = null;
        paymentyearBankDTO37 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "1");
        if (paymentyearBankDTO37 != null && !"".equals(paymentyearBankDTO37)) {
          nh_hs10 = paymentyearBankDTO37.getHushu();
          nh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "1");
          nh_je10 = paymentyearBankDTO37.getJiner();
        }
        // 十月 中行
        PaymentyearBankDTO paymentyearBankDTO38 = null;
        paymentyearBankDTO38 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "2");
        if (paymentyearBankDTO38 != null && !"".equals(paymentyearBankDTO38)) {
          zh_hs10 = paymentyearBankDTO38.getHushu();
          zh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "2");
          zh_je10 = paymentyearBankDTO38.getJiner();
        }
        // 十月 工行
        PaymentyearBankDTO paymentyearBankDTO39 = null;
        paymentyearBankDTO39 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "3");
        if (paymentyearBankDTO39 != null && !"".equals(paymentyearBankDTO39)) {
          gh_hs10 = paymentyearBankDTO39.getHushu();
          gh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "3");
          gh_je10 = paymentyearBankDTO39.getJiner();
        }
        // 十月 建行
        PaymentyearBankDTO paymentyearBankDTO40 = null;
        paymentyearBankDTO40 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "4");
        if (paymentyearBankDTO40 != null && !"".equals(paymentyearBankDTO40)) {
          jh_hs10 = paymentyearBankDTO40.getHushu();
          jh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "4");
          jh_je10 = paymentyearBankDTO40.getJiner();
        }
        sum_10 = sum_10.add(nh_je10).add(zh_je10).add(gh_je10).add(jh_je10);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs10(nh_hs10);// 农行
        paymentyearstatisticsDTO.setNh_rs10(nh_rs10);
        paymentyearstatisticsDTO.setNh_je10(nh_je10);
        paymentyearstatisticsDTO.setZh_hs10(zh_hs10);// 中行
        paymentyearstatisticsDTO.setZh_rs10(zh_rs10);
        paymentyearstatisticsDTO.setZh_je10(zh_je10);
        paymentyearstatisticsDTO.setGh_hs10(gh_hs10);// 工行
        paymentyearstatisticsDTO.setGh_rs10(gh_rs10);
        paymentyearstatisticsDTO.setGh_je10(gh_je10);
        paymentyearstatisticsDTO.setJh_hs10(jh_hs10);// 建行
        paymentyearstatisticsDTO.setJh_rs10(jh_rs10);
        paymentyearstatisticsDTO.setJh_je10(jh_je10);
        paymentyearstatisticsDTO.setSum_10(sum_10);

        /** **************** */
        /*                   */
        /* 十一月 */
        /*                   */
        /** **************** */
        int nh_hs11 = 0;// 农行户数
        int nh_rs11 = 0;// 农行人数
        BigDecimal nh_je11 = new BigDecimal(0.00);// 农行金额
        int zh_hs11 = 0;// 中行户数
        int zh_rs11 = 0;// 中行人数
        BigDecimal zh_je11 = new BigDecimal(0.00);// 中行金额
        int gh_hs11 = 0;// 工行户数
        int gh_rs11 = 0;// 工行人数
        BigDecimal gh_je11 = new BigDecimal(0.00);// 工行金额
        int jh_hs11 = 0;// 建行户数
        int jh_rs11 = 0;// 建行人数
        BigDecimal jh_je11 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_11 = new BigDecimal(0.00);// 二月合计

        // 十月 农行
        PaymentyearBankDTO paymentyearBankDTO41 = null;
        paymentyearBankDTO41 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "1");
        if (paymentyearBankDTO41 != null && !"".equals(paymentyearBankDTO41)) {
          nh_hs11 = paymentyearBankDTO41.getHushu();
          nh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "1");
          nh_je11 = paymentyearBankDTO41.getJiner();
        }
        // 十月 中行
        PaymentyearBankDTO paymentyearBankDTO42 = null;
        paymentyearBankDTO42 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "2");
        if (paymentyearBankDTO42 != null && !"".equals(paymentyearBankDTO42)) {
          zh_hs11 = paymentyearBankDTO42.getHushu();
          zh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "2");
          zh_je11 = paymentyearBankDTO42.getJiner();
        }
        // 十月 工行
        PaymentyearBankDTO paymentyearBankDTO43 = null;
        paymentyearBankDTO43 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "3");
        if (paymentyearBankDTO43 != null && !"".equals(paymentyearBankDTO43)) {
          gh_hs11 = paymentyearBankDTO43.getHushu();
          gh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "3");
          gh_je11 = paymentyearBankDTO43.getJiner();
        }
        // 十月 建行
        PaymentyearBankDTO paymentyearBankDTO44 = null;
        paymentyearBankDTO44 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "4");
        if (paymentyearBankDTO44 != null && !"".equals(paymentyearBankDTO44)) {
          jh_hs11 = paymentyearBankDTO44.getHushu();
          jh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "4");
          jh_je11 = paymentyearBankDTO44.getJiner();
        }
        sum_11 = sum_11.add(nh_je11).add(zh_je11).add(gh_je11).add(jh_je11);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs11(nh_hs11);// 农行
        paymentyearstatisticsDTO.setNh_rs11(nh_rs11);
        paymentyearstatisticsDTO.setNh_je11(nh_je11);
        paymentyearstatisticsDTO.setZh_hs11(zh_hs11);// 中行
        paymentyearstatisticsDTO.setZh_rs11(zh_rs11);
        paymentyearstatisticsDTO.setZh_je11(zh_je11);
        paymentyearstatisticsDTO.setGh_hs11(gh_hs11);// 工行
        paymentyearstatisticsDTO.setGh_rs11(gh_rs11);
        paymentyearstatisticsDTO.setGh_je11(gh_je11);
        paymentyearstatisticsDTO.setJh_hs11(jh_hs11);// 建行
        paymentyearstatisticsDTO.setJh_rs11(jh_rs11);
        paymentyearstatisticsDTO.setJh_je11(jh_je11);
        paymentyearstatisticsDTO.setSum_11(sum_11);

        /** **************** */
        /*                   */
        /* 十二月 */
        /*                   */
        /** **************** */
        int nh_hs12 = 0;// 农行户数
        int nh_rs12 = 0;// 农行人数
        BigDecimal nh_je12 = new BigDecimal(0.00);// 农行金额
        int zh_hs12 = 0;// 中行户数
        int zh_rs12 = 0;// 中行人数
        BigDecimal zh_je12 = new BigDecimal(0.00);// 中行金额
        int gh_hs12 = 0;// 工行户数
        int gh_rs12 = 0;// 工行人数
        BigDecimal gh_je12 = new BigDecimal(0.00);// 工行金额
        int jh_hs12 = 0;// 建行户数
        int jh_rs12 = 0;// 建行人数
        BigDecimal jh_je12 = new BigDecimal(0.00);// 建行人数
        BigDecimal sum_12 = new BigDecimal(0.00);// 二月合计

        // 十二月 农行
        PaymentyearBankDTO paymentyearBankDTO45 = null;
        paymentyearBankDTO45 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "1");
        if (paymentyearBankDTO45 != null && !"".equals(paymentyearBankDTO45)) {
          nh_hs12 = paymentyearBankDTO45.getHushu();
          nh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "1");
          nh_je12 = paymentyearBankDTO45.getJiner();
        }
        // 十二月 中行
        PaymentyearBankDTO paymentyearBankDTO46 = null;
        paymentyearBankDTO46 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "2");
        if (paymentyearBankDTO46 != null && !"".equals(paymentyearBankDTO46)) {
          zh_hs12 = paymentyearBankDTO46.getHushu();
          zh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "2");
          zh_je12 = paymentyearBankDTO46.getJiner();
        }
        // 十二月 工行
        PaymentyearBankDTO paymentyearBankDTO47 = null;
        paymentyearBankDTO47 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "3");
        if (paymentyearBankDTO47 != null && !"".equals(paymentyearBankDTO47)) {
          gh_hs12 = paymentyearBankDTO47.getHushu();
          gh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "3");
          gh_je12 = paymentyearBankDTO47.getJiner();
        }
        // 十二月 建行
        PaymentyearBankDTO paymentyearBankDTO48 = null;
        paymentyearBankDTO48 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "4");
        if (paymentyearBankDTO48 != null && !"".equals(paymentyearBankDTO48)) {
          jh_hs12 = paymentyearBankDTO48.getHushu();
          jh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "4");
          jh_je12 = paymentyearBankDTO48.getJiner();
        }
        sum_12 = sum_12.add(nh_je12).add(zh_je12).add(gh_je12).add(jh_je12);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs12(nh_hs12);// 农行
        paymentyearstatisticsDTO.setNh_rs12(nh_rs12);
        paymentyearstatisticsDTO.setNh_je12(nh_je12);
        paymentyearstatisticsDTO.setZh_hs12(zh_hs12);// 中行
        paymentyearstatisticsDTO.setZh_rs12(zh_rs12);
        paymentyearstatisticsDTO.setZh_je12(zh_je12);
        paymentyearstatisticsDTO.setGh_hs12(gh_hs12);// 工行
        paymentyearstatisticsDTO.setGh_rs12(gh_rs12);
        paymentyearstatisticsDTO.setGh_je12(gh_je12);
        paymentyearstatisticsDTO.setJh_hs12(jh_hs12);// 建行
        paymentyearstatisticsDTO.setJh_rs12(jh_rs12);
        paymentyearstatisticsDTO.setJh_je12(jh_je12);
        paymentyearstatisticsDTO.setSum_12(sum_12);

        /** **************** */
        /*                   */
        /* 合计 */
        /*                   */
        /** **************** */
        int sum_a = 0;//
        int sum_b = 0;//
        BigDecimal sum_c = new BigDecimal(0.00);//
        int sum_d = 0;
        int sum_e = 0;//
        BigDecimal sum_f = new BigDecimal(0.00);//
        int sum_g = 0;//
        int sum_h = 0;//
        BigDecimal sum_i = new BigDecimal(0.00);//
        int sum_j = 0;//
        int sum_k = 0;//
        BigDecimal sum_l = new BigDecimal(0.00);//
        BigDecimal sum_m = new BigDecimal(0.00);//
        
        // 农行合计
        sum_a = nh_hs1 + nh_hs2 + nh_hs3 + nh_hs4 + nh_hs5 + nh_hs6 + nh_hs7
            + nh_hs8 + nh_hs9 + nh_hs10 + nh_hs11 + nh_hs12;//

        sum_b = nh_rs1 + nh_rs2 + nh_rs3 + nh_rs4 + nh_rs5 + nh_rs6 + nh_rs7
            + nh_rs8 + nh_rs9 + nh_rs10 + nh_rs11 + nh_rs12;//

        sum_c = sum_c.add(nh_je1).add(nh_je2).add(nh_je3).add(nh_je4).add(
            nh_je5).add(nh_je6).add(nh_je7).add(nh_je8).add(nh_je9)
            .add(nh_je10).add(nh_je11).add(nh_je12);//
        // 中行合计
        sum_d = zh_hs1 + zh_hs2 + zh_hs3 + zh_hs4 + zh_hs5 + zh_hs6 + zh_hs7
            + zh_hs8 + zh_hs9 + zh_hs10 + zh_hs11 + zh_hs12;//

        sum_e = zh_rs1 + zh_rs2 + zh_rs3 + zh_rs4 + zh_rs5 + zh_rs6 + zh_rs7
            + zh_rs8 + zh_rs9 + zh_rs10 + zh_rs11 + zh_rs12;//

        sum_f = sum_f.add(zh_je1).add(zh_je2).add(zh_je3).add(zh_je4).add(
            zh_je5).add(zh_je6).add(zh_je7).add(zh_je8).add(zh_je9)
            .add(zh_je10).add(zh_je11).add(zh_je12);//
        // 工行合计
        sum_g = gh_hs1 + gh_hs2 + gh_hs3 + gh_hs4 + gh_hs5 + gh_hs6 + gh_hs7
            + gh_hs8 + gh_hs9 + gh_hs10 + gh_hs11 + gh_hs12;//

        sum_h = gh_rs1 + gh_rs2 + gh_rs3 + gh_rs4 + gh_rs5 + gh_rs6 + gh_rs7
            + gh_rs8 + gh_rs9 + gh_rs10 + gh_rs11 + gh_rs12;//

        sum_i = sum_i.add(gh_je1).add(gh_je2).add(gh_je3).add(gh_je4).add(
            gh_je5).add(gh_je6).add(gh_je7).add(gh_je8).add(gh_je9)
            .add(gh_je10).add(gh_je11).add(gh_je12);//
        // 建行合计
        sum_j = jh_hs1 + jh_hs2 + jh_hs3 + jh_hs4 + jh_hs5 + jh_hs6 + jh_hs7
            + jh_hs8 + jh_hs9 + jh_hs10 + jh_hs11 + jh_hs12;//

        sum_k = jh_rs1 + jh_rs2 + jh_rs3 + jh_rs4 + jh_rs5 + jh_rs6 + jh_rs7
            + jh_rs8 + jh_rs9 + jh_rs10 + jh_rs11 + jh_rs12;//

        sum_l = sum_l.add(jh_je1).add(jh_je2).add(jh_je3).add(jh_je4).add(
            jh_je5).add(jh_je6).add(jh_je7).add(jh_je8).add(jh_je9)
            .add(jh_je10).add(jh_je11).add(jh_je12);//

        // 合计合计
        sum_m = sum_m.add(sum_1).add(sum_2).add(sum_3).add(sum_4).add(sum_5)
            .add(sum_6).add(sum_7).add(sum_8).add(sum_9).add(sum_10)
            .add(sum_11).add(sum_12);//
        
        paymentyearstatisticsDTO.setSum_a(sum_a);
        paymentyearstatisticsDTO.setSum_b(sum_b);
        paymentyearstatisticsDTO.setSum_c(sum_c);
        paymentyearstatisticsDTO.setSum_d(sum_d);
        paymentyearstatisticsDTO.setSum_e(sum_e);
        paymentyearstatisticsDTO.setSum_f(sum_f);
        paymentyearstatisticsDTO.setSum_g(sum_g);
        paymentyearstatisticsDTO.setSum_h(sum_h);
        paymentyearstatisticsDTO.setSum_i(sum_i);
        paymentyearstatisticsDTO.setSum_j(sum_j);
        paymentyearstatisticsDTO.setSum_k(sum_k);
        paymentyearstatisticsDTO.setSum_l(sum_l);
        paymentyearstatisticsDTO.setSum_m(sum_m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymentyearstatisticsDTO;
  }

  /**
   * 根据条件查询--大石桥
   */
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO2(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    PaymentyearstatisticsDTO paymentyearstatisticsDTO = new PaymentyearstatisticsDTO();
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String bizDate = (String) pagination.getQueryCriterions().get("bizDate");
    String officeName = "";
    if(officeCode!=null){
      officeName = orgHAFAccountFlowDAO.queryOfficeName(officeCode);
      paymentyearstatisticsDTO.setOffice(officeName);
    }
    try {
      if (!pagination.getQueryCriterions().isEmpty()) {// 查询条件不为空

        /** **************** */
        /*                 */
        /* 一月 */
        /*                 */
        /** **************** */
        int nh_hs1 = 0;// 农行户数
        int nh_rs1 = 0;// 农行人数
        BigDecimal nh_je1 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_1 = new BigDecimal(0.00);// 一月合计

        // 一月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO1 = null;
        paymentyearBankDTO1 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "6");
        if (paymentyearBankDTO1 != null && !"".equals(paymentyearBankDTO1)) {
          nh_hs1 = paymentyearBankDTO1.getHushu();
          nh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "6");
          nh_je1 = paymentyearBankDTO1.getJiner();
        }
       
        sum_1 = sum_1.add(nh_je1);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs1(nh_hs1);// 农行
        paymentyearstatisticsDTO.setNh_rs1(nh_rs1);
        paymentyearstatisticsDTO.setNh_je1(nh_je1);
        paymentyearstatisticsDTO.setSum_1(sum_1);

        /** **************** */
        /*                 */
        /* 二月 */
        /*                 */
        /** **************** */
        int nh_hs2 = 0;// 农行户数
        int nh_rs2 = 0;// 农行人数
        BigDecimal nh_je2 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_2 = new BigDecimal(0.00);// 二月合计

        // 二月  大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO5 = null;
        paymentyearBankDTO5 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "6");
        if (paymentyearBankDTO5 != null && !"".equals(paymentyearBankDTO5)) {
          nh_hs2 = paymentyearBankDTO5.getHushu();
          nh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "6");
          nh_je2 = paymentyearBankDTO5.getJiner();
        }
        
        sum_2 = sum_2.add(nh_je2);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs2(nh_hs2);// 农行
        paymentyearstatisticsDTO.setNh_rs2(nh_rs2);
        paymentyearstatisticsDTO.setNh_je2(nh_je2);
        paymentyearstatisticsDTO.setSum_2(sum_2);

        /** **************** */
        /*                 */
        /* 三月 */
        /*                 */
        /** **************** */
        int nh_hs3 = 0;// 农行户数
        int nh_rs3 = 0;// 农行人数
        BigDecimal nh_je3 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_3 = new BigDecimal(0.00);// 二月合计

        // 三月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO9 = null;
        paymentyearBankDTO9 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "03", "6");
        if (paymentyearBankDTO9 != null && !"".equals(paymentyearBankDTO9)) {
          nh_hs3 = paymentyearBankDTO9.getHushu();
          nh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "6");
          nh_je3 = paymentyearBankDTO9.getJiner();
        }
        
        sum_3 = sum_3.add(nh_je3);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs3(nh_hs3);// 农行
        paymentyearstatisticsDTO.setNh_rs3(nh_rs3);
        paymentyearstatisticsDTO.setNh_je3(nh_je3);
        paymentyearstatisticsDTO.setSum_3(sum_3);

        /** **************** */
        /*                 */
        /* 四月 */
        /*                 */
        /** **************** */
        int nh_hs4 = 0;// 农行户数
        int nh_rs4 = 0;// 农行人数
        BigDecimal nh_je4 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_4 = new BigDecimal(0.00);// 二月合计

        // 四月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO13 = null;
        paymentyearBankDTO13 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "6");
        if (paymentyearBankDTO13 != null && !"".equals(paymentyearBankDTO13)) {
          nh_hs4 = paymentyearBankDTO13.getHushu();
          nh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "6");
          nh_je4 = paymentyearBankDTO13.getJiner();
        }
        
        sum_4 = sum_4.add(nh_je4);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs4(nh_hs4);// 农行
        paymentyearstatisticsDTO.setNh_rs4(nh_rs4);
        paymentyearstatisticsDTO.setNh_je4(nh_je4);
        paymentyearstatisticsDTO.setSum_4(sum_4);

        /** **************** */
        /*                 */
        /* 五月 */
        /*                 */
        /** **************** */
        int nh_hs5 = 0;// 农行户数
        int nh_rs5 = 0;// 农行人数
        BigDecimal nh_je5 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_5 = new BigDecimal(0.00);// 二月合计

        // 五月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO17 = null;
        paymentyearBankDTO17 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "6");
        if (paymentyearBankDTO17 != null && !"".equals(paymentyearBankDTO17)) {
          nh_hs5 = paymentyearBankDTO17.getHushu();
          nh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "6");
          nh_je5 = paymentyearBankDTO17.getJiner();
        }
       
        sum_5 = sum_5.add(nh_je5);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs5(nh_hs5);// 农行
        paymentyearstatisticsDTO.setNh_rs5(nh_rs5);
        paymentyearstatisticsDTO.setNh_je5(nh_je5);
        paymentyearstatisticsDTO.setSum_5(sum_5);

        /** **************** */
        /*                 */
        /* 六月 */
        /*                 */
        /** **************** */
        int nh_hs6 = 0;// 农行户数
        int nh_rs6 = 0;// 农行人数
        BigDecimal nh_je6 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_6 = new BigDecimal(0.00);// 二月合计

        // 六月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO21 = null;
        paymentyearBankDTO21 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "6");
        if (paymentyearBankDTO21 != null && !"".equals(paymentyearBankDTO21)) {
          nh_hs6 = paymentyearBankDTO21.getHushu();
          nh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "6");
          nh_je6 = paymentyearBankDTO21.getJiner();
        }
        
        sum_6 = sum_6.add(nh_je6);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs6(nh_hs6);// 农行
        paymentyearstatisticsDTO.setNh_rs6(nh_rs6);
        paymentyearstatisticsDTO.setNh_je6(nh_je6);
        paymentyearstatisticsDTO.setSum_6(sum_6);

        /** **************** */
        /*                 */
        /* 七月 */
        /*                 */
        /** **************** */
        int nh_hs7 = 0;// 农行户数
        int nh_rs7 = 0;// 农行人数
        BigDecimal nh_je7 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_7 = new BigDecimal(0.00);// 二月合计

        // 六月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO25 = null;
        paymentyearBankDTO25 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "6");
        if (paymentyearBankDTO25 != null && !"".equals(paymentyearBankDTO25)) {
          nh_hs7 = paymentyearBankDTO25.getHushu();
          nh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "6");
          nh_je7 = paymentyearBankDTO25.getJiner();
        }
        
        sum_7 = sum_7.add(nh_je7);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs7(nh_hs7);// 农行
        paymentyearstatisticsDTO.setNh_rs7(nh_rs7);
        paymentyearstatisticsDTO.setNh_je7(nh_je7);
        paymentyearstatisticsDTO.setSum_7(sum_7);

        /** **************** */
        /*                   */
        /* 八月 */
        /*                   */
        /** **************** */
        int nh_hs8 = 0;// 农行户数
        int nh_rs8 = 0;// 农行人数
        BigDecimal nh_je8 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_8 = new BigDecimal(0.00);// 二月合计

        // 八月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO29 = null;
        paymentyearBankDTO29 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "6");
        if (paymentyearBankDTO29 != null && !"".equals(paymentyearBankDTO29)) {
          nh_hs8 = paymentyearBankDTO29.getHushu();
          nh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "6");
          nh_je8 = paymentyearBankDTO29.getJiner();
        }
        
        sum_8 = sum_8.add(nh_je8);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs8(nh_hs8);// 农行
        paymentyearstatisticsDTO.setNh_rs8(nh_rs8);
        paymentyearstatisticsDTO.setNh_je8(nh_je8);
        paymentyearstatisticsDTO.setSum_8(sum_8);

        /** **************** */
        /*                   */
        /* 九月 */
        /*                   */
        /** **************** */
        int nh_hs9 = 0;// 农行户数
        int nh_rs9 = 0;// 农行人数
        BigDecimal nh_je9 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_9 = new BigDecimal(0.00);// 二月合计

        // 九月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO33 = null;
        paymentyearBankDTO33 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "6");
        if (paymentyearBankDTO33 != null && !"".equals(paymentyearBankDTO33)) {
          nh_hs9 = paymentyearBankDTO33.getHushu();
          nh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "6");
          nh_je9 = paymentyearBankDTO33.getJiner();
        }
        
        sum_9 = sum_9.add(nh_je9);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs9(nh_hs9);// 农行
        paymentyearstatisticsDTO.setNh_rs9(nh_rs9);
        paymentyearstatisticsDTO.setNh_je9(nh_je9);
        paymentyearstatisticsDTO.setSum_9(sum_9);

        /** **************** */
        /*                   */
        /* 十月 */
        /*                   */
        /** **************** */
        int nh_hs10 = 0;// 农行户数
        int nh_rs10 = 0;// 农行人数
        BigDecimal nh_je10 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_10 = new BigDecimal(0.00);// 二月合计

        // 十月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO37 = null;
        paymentyearBankDTO37 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "6");
        if (paymentyearBankDTO37 != null && !"".equals(paymentyearBankDTO37)) {
          nh_hs10 = paymentyearBankDTO37.getHushu();
          nh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "6");
          nh_je10 = paymentyearBankDTO37.getJiner();
        }
        
        sum_10 = sum_10.add(nh_je10);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs10(nh_hs10);// 农行
        paymentyearstatisticsDTO.setNh_rs10(nh_rs10);
        paymentyearstatisticsDTO.setNh_je10(nh_je10);
        paymentyearstatisticsDTO.setSum_10(sum_10);

        /** **************** */
        /*                   */
        /* 十一月 */
        /*                   */
        /** **************** */
        int nh_hs11 = 0;// 农行户数
        int nh_rs11 = 0;// 农行人数
        BigDecimal nh_je11 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_11 = new BigDecimal(0.00);// 二月合计

        // 十月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO41 = null;
        paymentyearBankDTO41 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "6");
        if (paymentyearBankDTO41 != null && !"".equals(paymentyearBankDTO41)) {
          nh_hs11 = paymentyearBankDTO41.getHushu();
          nh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "6");
          nh_je11 = paymentyearBankDTO41.getJiner();
        }
        
        sum_11 = sum_11.add(nh_je11);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs11(nh_hs11);// 农行
        paymentyearstatisticsDTO.setNh_rs11(nh_rs11);
        paymentyearstatisticsDTO.setNh_je11(nh_je11);
        paymentyearstatisticsDTO.setSum_11(sum_11);

        /** **************** */
        /*                   */
        /* 十二月 */
        /*                   */
        /** **************** */
        int nh_hs12 = 0;// 农行户数
        int nh_rs12 = 0;// 农行人数
        BigDecimal nh_je12 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_12 = new BigDecimal(0.00);// 二月合计

        // 十二月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO45 = null;
        paymentyearBankDTO45 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "6");
        if (paymentyearBankDTO45 != null && !"".equals(paymentyearBankDTO45)) {
          nh_hs12 = paymentyearBankDTO45.getHushu();
          nh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "6");
          nh_je12 = paymentyearBankDTO45.getJiner();
        }
        
        sum_12 = sum_12.add(nh_je12);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs12(nh_hs12);// 农行
        paymentyearstatisticsDTO.setNh_rs12(nh_rs12);
        paymentyearstatisticsDTO.setNh_je12(nh_je12);
        paymentyearstatisticsDTO.setSum_12(sum_12);

        /** **************** */
        /*                   */
        /* 合计 */
        /*                   */
        /** **************** */
        int sum_a = 0;//
        int sum_b = 0;//
        BigDecimal sum_c = new BigDecimal(0.00);//
        BigDecimal sum_m = new BigDecimal(0.00);//
        
        // 大石桥商业银行合计
        sum_a = nh_hs1 + nh_hs2 + nh_hs3 + nh_hs4 + nh_hs5 + nh_hs6 + nh_hs7
            + nh_hs8 + nh_hs9 + nh_hs10 + nh_hs11 + nh_hs12;//

        sum_b = nh_rs1 + nh_rs2 + nh_rs3 + nh_rs4 + nh_rs5 + nh_rs6 + nh_rs7
            + nh_rs8 + nh_rs9 + nh_rs10 + nh_rs11 + nh_rs12;//

        sum_c = sum_c.add(nh_je1).add(nh_je2).add(nh_je3).add(nh_je4).add(
            nh_je5).add(nh_je6).add(nh_je7).add(nh_je8).add(nh_je9)
            .add(nh_je10).add(nh_je11).add(nh_je12);//

        // 合计合计
        sum_m = sum_m.add(sum_1).add(sum_2).add(sum_3).add(sum_4).add(sum_5)
            .add(sum_6).add(sum_7).add(sum_8).add(sum_9).add(sum_10)
            .add(sum_11).add(sum_12);//
        
        paymentyearstatisticsDTO.setSum_a(sum_a);
        paymentyearstatisticsDTO.setSum_b(sum_b);
        paymentyearstatisticsDTO.setSum_c(sum_c);
        paymentyearstatisticsDTO.setSum_m(sum_m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymentyearstatisticsDTO;
  }


  /**
   * 根据条件查询--盖州
   */
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO3(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    PaymentyearstatisticsDTO paymentyearstatisticsDTO = new PaymentyearstatisticsDTO();
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String bizDate = (String) pagination.getQueryCriterions().get("bizDate");
    String officeName = "";
    if(officeCode!=null){
      officeName = orgHAFAccountFlowDAO.queryOfficeName(officeCode);
      paymentyearstatisticsDTO.setOffice(officeName);
    }
    try {
      if (!pagination.getQueryCriterions().isEmpty()) {// 查询条件不为空

        /** **************** */
        /*                 */
        /* 一月 */
        /*                 */
        /** **************** */
        int nh_hs1 = 0;// 农行户数
        int nh_rs1 = 0;// 农行人数
        BigDecimal nh_je1 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_1 = new BigDecimal(0.00);// 一月合计

        // 一月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO1 = null;
        paymentyearBankDTO1 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "7");
        if (paymentyearBankDTO1 != null && !"".equals(paymentyearBankDTO1)) {
          nh_hs1 = paymentyearBankDTO1.getHushu();
          nh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "7");
          nh_je1 = paymentyearBankDTO1.getJiner();
        }
       
        sum_1 = sum_1.add(nh_je1);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs1(nh_hs1);// 农行
        paymentyearstatisticsDTO.setNh_rs1(nh_rs1);
        paymentyearstatisticsDTO.setNh_je1(nh_je1);
        paymentyearstatisticsDTO.setSum_1(sum_1);

        /** **************** */
        /*                 */
        /* 二月 */
        /*                 */
        /** **************** */
        int nh_hs2 = 0;// 农行户数
        int nh_rs2 = 0;// 农行人数
        BigDecimal nh_je2 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_2 = new BigDecimal(0.00);// 二月合计

        // 二月  大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO5 = null;
        paymentyearBankDTO5 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "7");
        if (paymentyearBankDTO5 != null && !"".equals(paymentyearBankDTO5)) {
          nh_hs2 = paymentyearBankDTO5.getHushu();
          nh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "7");
          nh_je2 = paymentyearBankDTO5.getJiner();
        }
        
        sum_2 = sum_2.add(nh_je2);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs2(nh_hs2);// 农行
        paymentyearstatisticsDTO.setNh_rs2(nh_rs2);
        paymentyearstatisticsDTO.setNh_je2(nh_je2);
        paymentyearstatisticsDTO.setSum_2(sum_2);

        /** **************** */
        /*                 */
        /* 三月 */
        /*                 */
        /** **************** */
        int nh_hs3 = 0;// 农行户数
        int nh_rs3 = 0;// 农行人数
        BigDecimal nh_je3 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_3 = new BigDecimal(0.00);// 二月合计

        // 三月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO9 = null;
        paymentyearBankDTO9 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "03", "7");
        if (paymentyearBankDTO9 != null && !"".equals(paymentyearBankDTO9)) {
          nh_hs3 = paymentyearBankDTO9.getHushu();
          nh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "7");
          nh_je3 = paymentyearBankDTO9.getJiner();
        }
        
        sum_3 = sum_3.add(nh_je3);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs3(nh_hs3);// 农行
        paymentyearstatisticsDTO.setNh_rs3(nh_rs3);
        paymentyearstatisticsDTO.setNh_je3(nh_je3);
        paymentyearstatisticsDTO.setSum_3(sum_3);

        /** **************** */
        /*                 */
        /* 四月 */
        /*                 */
        /** **************** */
        int nh_hs4 = 0;// 农行户数
        int nh_rs4 = 0;// 农行人数
        BigDecimal nh_je4 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_4 = new BigDecimal(0.00);// 二月合计

        // 四月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO13 = null;
        paymentyearBankDTO13 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "7");
        if (paymentyearBankDTO13 != null && !"".equals(paymentyearBankDTO13)) {
          nh_hs4 = paymentyearBankDTO13.getHushu();
          nh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "7");
          nh_je4 = paymentyearBankDTO13.getJiner();
        }
        
        sum_4 = sum_4.add(nh_je4);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs4(nh_hs4);// 农行
        paymentyearstatisticsDTO.setNh_rs4(nh_rs4);
        paymentyearstatisticsDTO.setNh_je4(nh_je4);
        paymentyearstatisticsDTO.setSum_4(sum_4);

        /** **************** */
        /*                 */
        /* 五月 */
        /*                 */
        /** **************** */
        int nh_hs5 = 0;// 农行户数
        int nh_rs5 = 0;// 农行人数
        BigDecimal nh_je5 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_5 = new BigDecimal(0.00);// 二月合计

        // 五月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO17 = null;
        paymentyearBankDTO17 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "7");
        if (paymentyearBankDTO17 != null && !"".equals(paymentyearBankDTO17)) {
          nh_hs5 = paymentyearBankDTO17.getHushu();
          nh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "7");
          nh_je5 = paymentyearBankDTO17.getJiner();
        }
       
        sum_5 = sum_5.add(nh_je5);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs5(nh_hs5);// 农行
        paymentyearstatisticsDTO.setNh_rs5(nh_rs5);
        paymentyearstatisticsDTO.setNh_je5(nh_je5);
        paymentyearstatisticsDTO.setSum_5(sum_5);

        /** **************** */
        /*                 */
        /* 六月 */
        /*                 */
        /** **************** */
        int nh_hs6 = 0;// 农行户数
        int nh_rs6 = 0;// 农行人数
        BigDecimal nh_je6 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_6 = new BigDecimal(0.00);// 二月合计

        // 六月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO21 = null;
        paymentyearBankDTO21 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "7");
        if (paymentyearBankDTO21 != null && !"".equals(paymentyearBankDTO21)) {
          nh_hs6 = paymentyearBankDTO21.getHushu();
          nh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "7");
          nh_je6 = paymentyearBankDTO21.getJiner();
        }
        
        sum_6 = sum_6.add(nh_je6);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs6(nh_hs6);// 农行
        paymentyearstatisticsDTO.setNh_rs6(nh_rs6);
        paymentyearstatisticsDTO.setNh_je6(nh_je6);
        paymentyearstatisticsDTO.setSum_6(sum_6);

        /** **************** */
        /*                 */
        /* 七月 */
        /*                 */
        /** **************** */
        int nh_hs7 = 0;// 农行户数
        int nh_rs7 = 0;// 农行人数
        BigDecimal nh_je7 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_7 = new BigDecimal(0.00);// 二月合计

        // 六月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO25 = null;
        paymentyearBankDTO25 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "7");
        if (paymentyearBankDTO25 != null && !"".equals(paymentyearBankDTO25)) {
          nh_hs7 = paymentyearBankDTO25.getHushu();
          nh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "7");
          nh_je7 = paymentyearBankDTO25.getJiner();
        }
        
        sum_7 = sum_7.add(nh_je7);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs7(nh_hs7);// 农行
        paymentyearstatisticsDTO.setNh_rs7(nh_rs7);
        paymentyearstatisticsDTO.setNh_je7(nh_je7);
        paymentyearstatisticsDTO.setSum_7(sum_7);

        /** **************** */
        /*                   */
        /* 八月 */
        /*                   */
        /** **************** */
        int nh_hs8 = 0;// 农行户数
        int nh_rs8 = 0;// 农行人数
        BigDecimal nh_je8 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_8 = new BigDecimal(0.00);// 二月合计

        // 八月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO29 = null;
        paymentyearBankDTO29 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "7");
        if (paymentyearBankDTO29 != null && !"".equals(paymentyearBankDTO29)) {
          nh_hs8 = paymentyearBankDTO29.getHushu();
          nh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "7");
          nh_je8 = paymentyearBankDTO29.getJiner();
        }
        
        sum_8 = sum_8.add(nh_je8);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs8(nh_hs8);// 农行
        paymentyearstatisticsDTO.setNh_rs8(nh_rs8);
        paymentyearstatisticsDTO.setNh_je8(nh_je8);
        paymentyearstatisticsDTO.setSum_8(sum_8);

        /** **************** */
        /*                   */
        /* 九月 */
        /*                   */
        /** **************** */
        int nh_hs9 = 0;// 农行户数
        int nh_rs9 = 0;// 农行人数
        BigDecimal nh_je9 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_9 = new BigDecimal(0.00);// 二月合计

        // 九月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO33 = null;
        paymentyearBankDTO33 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "7");
        if (paymentyearBankDTO33 != null && !"".equals(paymentyearBankDTO33)) {
          nh_hs9 = paymentyearBankDTO33.getHushu();
          nh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "7");
          nh_je9 = paymentyearBankDTO33.getJiner();
        }
        
        sum_9 = sum_9.add(nh_je9);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs9(nh_hs9);// 农行
        paymentyearstatisticsDTO.setNh_rs9(nh_rs9);
        paymentyearstatisticsDTO.setNh_je9(nh_je9);
        paymentyearstatisticsDTO.setSum_9(sum_9);

        /** **************** */
        /*                   */
        /* 十月 */
        /*                   */
        /** **************** */
        int nh_hs10 = 0;// 农行户数
        int nh_rs10 = 0;// 农行人数
        BigDecimal nh_je10 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_10 = new BigDecimal(0.00);// 二月合计

        // 十月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO37 = null;
        paymentyearBankDTO37 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "7");
        if (paymentyearBankDTO37 != null && !"".equals(paymentyearBankDTO37)) {
          nh_hs10 = paymentyearBankDTO37.getHushu();
          nh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "7");
          nh_je10 = paymentyearBankDTO37.getJiner();
        }
        
        sum_10 = sum_10.add(nh_je10);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs10(nh_hs10);// 农行
        paymentyearstatisticsDTO.setNh_rs10(nh_rs10);
        paymentyearstatisticsDTO.setNh_je10(nh_je10);
        paymentyearstatisticsDTO.setSum_10(sum_10);

        /** **************** */
        /*                   */
        /* 十一月 */
        /*                   */
        /** **************** */
        int nh_hs11 = 0;// 农行户数
        int nh_rs11 = 0;// 农行人数
        BigDecimal nh_je11 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_11 = new BigDecimal(0.00);// 二月合计

        // 十月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO41 = null;
        paymentyearBankDTO41 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "7");
        if (paymentyearBankDTO41 != null && !"".equals(paymentyearBankDTO41)) {
          nh_hs11 = paymentyearBankDTO41.getHushu();
          nh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "7");
          nh_je11 = paymentyearBankDTO41.getJiner();
        }
        
        sum_11 = sum_11.add(nh_je11);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs11(nh_hs11);// 农行
        paymentyearstatisticsDTO.setNh_rs11(nh_rs11);
        paymentyearstatisticsDTO.setNh_je11(nh_je11);
        paymentyearstatisticsDTO.setSum_11(sum_11);

        /** **************** */
        /*                   */
        /* 十二月 */
        /*                   */
        /** **************** */
        int nh_hs12 = 0;// 农行户数
        int nh_rs12 = 0;// 农行人数
        BigDecimal nh_je12 = new BigDecimal(0.00);// 农行金额
        BigDecimal sum_12 = new BigDecimal(0.00);// 二月合计

        // 十二月 大石桥商业银行
        PaymentyearBankDTO paymentyearBankDTO45 = null;
        paymentyearBankDTO45 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "7");
        if (paymentyearBankDTO45 != null && !"".equals(paymentyearBankDTO45)) {
          nh_hs12 = paymentyearBankDTO45.getHushu();
          nh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "7");
          nh_je12 = paymentyearBankDTO45.getJiner();
        }
        
        sum_12 = sum_12.add(nh_je12);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs12(nh_hs12);// 农行
        paymentyearstatisticsDTO.setNh_rs12(nh_rs12);
        paymentyearstatisticsDTO.setNh_je12(nh_je12);
        paymentyearstatisticsDTO.setSum_12(sum_12);

        /** **************** */
        /*                   */
        /* 合计 */
        /*                   */
        /** **************** */
        int sum_a = 0;//
        int sum_b = 0;//
        BigDecimal sum_c = new BigDecimal(0.00);//
        BigDecimal sum_m = new BigDecimal(0.00);//
        
        // 大石桥商业银行合计
        sum_a = nh_hs1 + nh_hs2 + nh_hs3 + nh_hs4 + nh_hs5 + nh_hs6 + nh_hs7
            + nh_hs8 + nh_hs9 + nh_hs10 + nh_hs11 + nh_hs12;//

        sum_b = nh_rs1 + nh_rs2 + nh_rs3 + nh_rs4 + nh_rs5 + nh_rs6 + nh_rs7
            + nh_rs8 + nh_rs9 + nh_rs10 + nh_rs11 + nh_rs12;//

        sum_c = sum_c.add(nh_je1).add(nh_je2).add(nh_je3).add(nh_je4).add(
            nh_je5).add(nh_je6).add(nh_je7).add(nh_je8).add(nh_je9)
            .add(nh_je10).add(nh_je11).add(nh_je12);//

        // 合计合计
        sum_m = sum_m.add(sum_1).add(sum_2).add(sum_3).add(sum_4).add(sum_5)
            .add(sum_6).add(sum_7).add(sum_8).add(sum_9).add(sum_10)
            .add(sum_11).add(sum_12);//
        
        paymentyearstatisticsDTO.setSum_a(sum_a);
        paymentyearstatisticsDTO.setSum_b(sum_b);
        paymentyearstatisticsDTO.setSum_c(sum_c);
        paymentyearstatisticsDTO.setSum_m(sum_m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymentyearstatisticsDTO;
  }

/**
 * 鲅鱼圈
 */
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO4(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    PaymentyearstatisticsDTO paymentyearstatisticsDTO = new PaymentyearstatisticsDTO();
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String bizDate = (String) pagination.getQueryCriterions().get("bizDate");
    String officeName = "";
    if(officeCode!=null){
      officeName = orgHAFAccountFlowDAO.queryOfficeName(officeCode);
      paymentyearstatisticsDTO.setOffice(officeName);
    }
    try {
      if (!pagination.getQueryCriterions().isEmpty()) {// 查询条件不为空

        /** **************** */
        /*                 */
        /* 一月 */
        /*                 */
        /** **************** */
        int nh_hs1 = 0;// 农行户数
        int nh_rs1 = 0;// 农行人数
        BigDecimal nh_je1 = new BigDecimal(0.00);// 农行金额
        int zh_hs1 = 0;// 中行户数
        int zh_rs1 = 0;// 中行人数
        BigDecimal zh_je1 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_1 = new BigDecimal(0.00);// 一月合计

        // 一月 鲅鱼圈农业银行
        PaymentyearBankDTO paymentyearBankDTO1 = null;
        paymentyearBankDTO1 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "9");
        if (paymentyearBankDTO1 != null && !"".equals(paymentyearBankDTO1)) {
          nh_hs1 = paymentyearBankDTO1.getHushu();
          nh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "9");
          nh_je1 = paymentyearBankDTO1.getJiner();
        }
        // 一月 鲅鱼圈建设银行
        PaymentyearBankDTO paymentyearBankDTO2 = null;
        paymentyearBankDTO2 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "01", "8");
        if (paymentyearBankDTO2 != null && !"".equals(paymentyearBankDTO2)) {
          zh_hs1 = paymentyearBankDTO2.getHushu();
          zh_rs1 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "01", "8");
          zh_je1 = paymentyearBankDTO2.getJiner();
        }
       
        sum_1 = sum_1.add(nh_je1).add(zh_je1);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs1(nh_hs1);// 农行
        paymentyearstatisticsDTO.setNh_rs1(nh_rs1);
        paymentyearstatisticsDTO.setNh_je1(nh_je1);
        paymentyearstatisticsDTO.setZh_hs1(zh_hs1);// 中行
        paymentyearstatisticsDTO.setZh_rs1(zh_rs1);
        paymentyearstatisticsDTO.setZh_je1(zh_je1);
        paymentyearstatisticsDTO.setSum_1(sum_1);

        /** **************** */
        /*                 */
        /* 二月 */
        /*                 */
        /** **************** */
        int nh_hs2 = 0;// 农行户数
        int nh_rs2 = 0;// 农行人数
        BigDecimal nh_je2 = new BigDecimal(0.00);// 农行金额
        int zh_hs2 = 0;// 中行户数
        int zh_rs2 = 0;// 中行人数
        BigDecimal zh_je2 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_2 = new BigDecimal(0.00);// 二月合计

        // 二月 农行
        PaymentyearBankDTO paymentyearBankDTO5 = null;
        paymentyearBankDTO5 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "9");
        if (paymentyearBankDTO5 != null && !"".equals(paymentyearBankDTO5)) {
          nh_hs2 = paymentyearBankDTO5.getHushu();
          nh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "9");
          nh_je2 = paymentyearBankDTO5.getJiner();
        }
        // 二月 中行
        PaymentyearBankDTO paymentyearBankDTO6 = null;
        paymentyearBankDTO6 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "02", "8");
        if (paymentyearBankDTO6 != null && !"".equals(paymentyearBankDTO6)) {
          zh_hs2 = paymentyearBankDTO6.getHushu();
          zh_rs2 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "02", "8");
          zh_je2 = paymentyearBankDTO6.getJiner();
        }
       
        sum_2 = sum_2.add(nh_je2).add(zh_je2);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs2(nh_hs2);// 农行
        paymentyearstatisticsDTO.setNh_rs2(nh_rs2);
        paymentyearstatisticsDTO.setNh_je2(nh_je2);
        paymentyearstatisticsDTO.setZh_hs2(zh_hs2);// 中行
        paymentyearstatisticsDTO.setZh_rs2(zh_rs2);
        paymentyearstatisticsDTO.setZh_je2(zh_je2);
        paymentyearstatisticsDTO.setSum_2(sum_2);

        /** **************** */
        /*                 */
        /* 三月 */
        /*                 */
        /** **************** */
        int nh_hs3 = 0;// 农行户数
        int nh_rs3 = 0;// 农行人数
        BigDecimal nh_je3 = new BigDecimal(0.00);// 农行金额
        int zh_hs3 = 0;// 中行户数
        int zh_rs3 = 0;// 中行人数
        BigDecimal zh_je3 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_3 = new BigDecimal(0.00);// 二月合计

        // 三月 农行
        PaymentyearBankDTO paymentyearBankDTO9 = null;
        paymentyearBankDTO9 = orgHAFAccountFlowDAO.queryPaymentyear(officeCode,
            bizDate + "03", "9");
        if (paymentyearBankDTO9 != null && !"".equals(paymentyearBankDTO9)) {
          nh_hs3 = paymentyearBankDTO9.getHushu();
          nh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "9");
          nh_je3 = paymentyearBankDTO9.getJiner();
        }
        // 三月 中行
        PaymentyearBankDTO paymentyearBankDTO10 = null;
        paymentyearBankDTO10 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "03", "8");
        if (paymentyearBankDTO10 != null && !"".equals(paymentyearBankDTO10)) {
          zh_hs3 = paymentyearBankDTO10.getHushu();
          zh_rs3 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "03", "8");
          zh_je3 = paymentyearBankDTO10.getJiner();
        }
        
        sum_3 = sum_3.add(nh_je3).add(zh_je3);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs3(nh_hs3);// 农行
        paymentyearstatisticsDTO.setNh_rs3(nh_rs3);
        paymentyearstatisticsDTO.setNh_je3(nh_je3);
        paymentyearstatisticsDTO.setZh_hs3(zh_hs3);// 中行
        paymentyearstatisticsDTO.setZh_rs3(zh_rs3);
        paymentyearstatisticsDTO.setZh_je3(zh_je3);
        paymentyearstatisticsDTO.setSum_3(sum_3);

        /** **************** */
        /*                 */
        /* 四月 */
        /*                 */
        /** **************** */
        int nh_hs4 = 0;// 农行户数
        int nh_rs4 = 0;// 农行人数
        BigDecimal nh_je4 = new BigDecimal(0.00);// 农行金额
        int zh_hs4 = 0;// 中行户数
        int zh_rs4 = 0;// 中行人数
        BigDecimal zh_je4 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_4 = new BigDecimal(0.00);// 二月合计

        // 四月 农行
        PaymentyearBankDTO paymentyearBankDTO13 = null;
        paymentyearBankDTO13 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "9");
        if (paymentyearBankDTO13 != null && !"".equals(paymentyearBankDTO13)) {
          nh_hs4 = paymentyearBankDTO13.getHushu();
          nh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "9");
          nh_je4 = paymentyearBankDTO13.getJiner();
        }
        // 四月 中行
        PaymentyearBankDTO paymentyearBankDTO14 = null;
        paymentyearBankDTO14 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "04", "8");
        if (paymentyearBankDTO14 != null && !"".equals(paymentyearBankDTO14)) {
          zh_hs4 = paymentyearBankDTO14.getHushu();
          zh_rs4 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "04", "8");
          zh_je4 = paymentyearBankDTO14.getJiner();
        }
       
        sum_4 = sum_4.add(nh_je4).add(zh_je4);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs4(nh_hs4);// 农行
        paymentyearstatisticsDTO.setNh_rs4(nh_rs4);
        paymentyearstatisticsDTO.setNh_je4(nh_je4);
        paymentyearstatisticsDTO.setZh_hs4(zh_hs4);// 中行
        paymentyearstatisticsDTO.setZh_rs4(zh_rs4);
        paymentyearstatisticsDTO.setZh_je4(zh_je4);
        paymentyearstatisticsDTO.setSum_4(sum_4);

        /** **************** */
        /*                 */
        /* 五月 */
        /*                 */
        /** **************** */
        int nh_hs5 = 0;// 农行户数
        int nh_rs5 = 0;// 农行人数
        BigDecimal nh_je5 = new BigDecimal(0.00);// 农行金额
        int zh_hs5 = 0;// 中行户数
        int zh_rs5 = 0;// 中行人数
        BigDecimal zh_je5 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_5 = new BigDecimal(0.00);// 二月合计

        // 五月 农行
        PaymentyearBankDTO paymentyearBankDTO17 = null;
        paymentyearBankDTO17 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "9");
        if (paymentyearBankDTO17 != null && !"".equals(paymentyearBankDTO17)) {
          nh_hs5 = paymentyearBankDTO17.getHushu();
          nh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "9");
          nh_je5 = paymentyearBankDTO17.getJiner();
        }
        // 五月 中行
        PaymentyearBankDTO paymentyearBankDTO18 = null;
        paymentyearBankDTO18 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "05", "8");
        if (paymentyearBankDTO18 != null && !"".equals(paymentyearBankDTO18)) {
          zh_hs5 = paymentyearBankDTO18.getHushu();
          zh_rs5 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "05", "8");
          zh_je5 = paymentyearBankDTO18.getJiner();
        }
       
        sum_5 = sum_5.add(nh_je5).add(zh_je5);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs5(nh_hs5);// 农行
        paymentyearstatisticsDTO.setNh_rs5(nh_rs5);
        paymentyearstatisticsDTO.setNh_je5(nh_je5);
        paymentyearstatisticsDTO.setZh_hs5(zh_hs5);// 中行
        paymentyearstatisticsDTO.setZh_rs5(zh_rs5);
        paymentyearstatisticsDTO.setZh_je5(zh_je5);
        paymentyearstatisticsDTO.setSum_5(sum_5);

        /** **************** */
        /*                 */
        /* 六月 */
        /*                 */
        /** **************** */
        int nh_hs6 = 0;// 农行户数
        int nh_rs6 = 0;// 农行人数
        BigDecimal nh_je6 = new BigDecimal(0.00);// 农行金额
        int zh_hs6 = 0;// 中行户数
        int zh_rs6 = 0;// 中行人数
        BigDecimal zh_je6 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_6 = new BigDecimal(0.00);// 二月合计

        // 六月 农行
        PaymentyearBankDTO paymentyearBankDTO21 = null;
        paymentyearBankDTO21 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "9");
        if (paymentyearBankDTO21 != null && !"".equals(paymentyearBankDTO21)) {
          nh_hs6 = paymentyearBankDTO21.getHushu();
          nh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "9");
          nh_je6 = paymentyearBankDTO21.getJiner();
        }
        // 六月 中行
        PaymentyearBankDTO paymentyearBankDTO22 = null;
        paymentyearBankDTO22 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "06", "8");
        if (paymentyearBankDTO22 != null && !"".equals(paymentyearBankDTO22)) {
          zh_hs6 = paymentyearBankDTO22.getHushu();
          zh_rs6 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "06", "8");
          zh_je6 = paymentyearBankDTO22.getJiner();
        }
        
        sum_6 = sum_6.add(nh_je6).add(zh_je6);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs6(nh_hs6);// 农行
        paymentyearstatisticsDTO.setNh_rs6(nh_rs6);
        paymentyearstatisticsDTO.setNh_je6(nh_je6);
        paymentyearstatisticsDTO.setZh_hs6(zh_hs6);// 中行
        paymentyearstatisticsDTO.setZh_rs6(zh_rs6);
        paymentyearstatisticsDTO.setZh_je6(zh_je6);
        paymentyearstatisticsDTO.setSum_6(sum_6);

        /** **************** */
        /*                 */
        /* 七月 */
        /*                 */
        /** **************** */
        int nh_hs7 = 0;// 农行户数
        int nh_rs7 = 0;// 农行人数
        BigDecimal nh_je7 = new BigDecimal(0.00);// 农行金额
        int zh_hs7 = 0;// 中行户数
        int zh_rs7 = 0;// 中行人数
        BigDecimal zh_je7 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_7 = new BigDecimal(0.00);// 二月合计

        // 六月 农行
        PaymentyearBankDTO paymentyearBankDTO25 = null;
        paymentyearBankDTO25 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "9");
        if (paymentyearBankDTO25 != null && !"".equals(paymentyearBankDTO25)) {
          nh_hs7 = paymentyearBankDTO25.getHushu();
          nh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "9");
          nh_je7 = paymentyearBankDTO25.getJiner();
        }
        // 六月 中行
        PaymentyearBankDTO paymentyearBankDTO26 = null;
        paymentyearBankDTO26 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "07", "8");
        if (paymentyearBankDTO26 != null && !"".equals(paymentyearBankDTO26)) {
          zh_hs7 = paymentyearBankDTO26.getHushu();
          zh_rs7 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "07", "8");
          zh_je7 = paymentyearBankDTO26.getJiner();
        }
        
        sum_7 = sum_7.add(nh_je7).add(zh_je7);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs7(nh_hs7);// 农行
        paymentyearstatisticsDTO.setNh_rs7(nh_rs7);
        paymentyearstatisticsDTO.setNh_je7(nh_je7);
        paymentyearstatisticsDTO.setZh_hs7(zh_hs7);// 中行
        paymentyearstatisticsDTO.setZh_rs7(zh_rs7);
        paymentyearstatisticsDTO.setZh_je7(zh_je7);
        paymentyearstatisticsDTO.setSum_7(sum_7);

        /** **************** */
        /*                   */
        /* 八月 */
        /*                   */
        /** **************** */
        int nh_hs8 = 0;// 农行户数
        int nh_rs8 = 0;// 农行人数
        BigDecimal nh_je8 = new BigDecimal(0.00);// 农行金额
        int zh_hs8 = 0;// 中行户数
        int zh_rs8 = 0;// 中行人数
        BigDecimal zh_je8 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_8 = new BigDecimal(0.00);// 二月合计

        // 八月 农行
        PaymentyearBankDTO paymentyearBankDTO29 = null;
        paymentyearBankDTO29 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "9");
        if (paymentyearBankDTO29 != null && !"".equals(paymentyearBankDTO29)) {
          nh_hs8 = paymentyearBankDTO29.getHushu();
          nh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "9");
          nh_je8 = paymentyearBankDTO29.getJiner();
        }
        // 八月 中行
        PaymentyearBankDTO paymentyearBankDTO30 = null;
        paymentyearBankDTO30 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "08", "8");
        if (paymentyearBankDTO30 != null && !"".equals(paymentyearBankDTO30)) {
          zh_hs8 = paymentyearBankDTO30.getHushu();
          zh_rs8 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "08", "8");
          zh_je8 = paymentyearBankDTO30.getJiner();
        }
        
        sum_8 = sum_8.add(nh_je8).add(zh_je8);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs8(nh_hs8);// 农行
        paymentyearstatisticsDTO.setNh_rs8(nh_rs8);
        paymentyearstatisticsDTO.setNh_je8(nh_je8);
        paymentyearstatisticsDTO.setZh_hs8(zh_hs8);// 中行
        paymentyearstatisticsDTO.setZh_rs8(zh_rs8);
        paymentyearstatisticsDTO.setZh_je8(zh_je8);
        paymentyearstatisticsDTO.setSum_8(sum_8);

        /** **************** */
        /*                   */
        /* 九月 */
        /*                   */
        /** **************** */
        int nh_hs9 = 0;// 农行户数
        int nh_rs9 = 0;// 农行人数
        BigDecimal nh_je9 = new BigDecimal(0.00);// 农行金额
        int zh_hs9 = 0;// 中行户数
        int zh_rs9 = 0;// 中行人数
        BigDecimal zh_je9 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_9 = new BigDecimal(0.00);// 二月合计

        // 九月 农行
        PaymentyearBankDTO paymentyearBankDTO33 = null;
        paymentyearBankDTO33 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "9");
        if (paymentyearBankDTO33 != null && !"".equals(paymentyearBankDTO33)) {
          nh_hs9 = paymentyearBankDTO33.getHushu();
          nh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "9");
          nh_je9 = paymentyearBankDTO33.getJiner();
        }
        // 九月 中行
        PaymentyearBankDTO paymentyearBankDTO34 = null;
        paymentyearBankDTO34 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "09", "8");
        if (paymentyearBankDTO34 != null && !"".equals(paymentyearBankDTO34)) {
          zh_hs9 = paymentyearBankDTO34.getHushu();
          zh_rs9 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "09", "8");
          zh_je9 = paymentyearBankDTO34.getJiner();
        }
        
        sum_9 = sum_9.add(nh_je9).add(zh_je9);// 合计一月金额

        paymentyearstatisticsDTO.setNh_hs9(nh_hs9);// 农行
        paymentyearstatisticsDTO.setNh_rs9(nh_rs9);
        paymentyearstatisticsDTO.setNh_je9(nh_je9);
        paymentyearstatisticsDTO.setZh_hs9(zh_hs9);// 中行
        paymentyearstatisticsDTO.setZh_rs9(zh_rs9);
        paymentyearstatisticsDTO.setZh_je9(zh_je9);
        paymentyearstatisticsDTO.setSum_9(sum_9);

        /** **************** */
        /*                   */
        /* 十月 */
        /*                   */
        /** **************** */
        int nh_hs10 = 0;// 农行户数
        int nh_rs10 = 0;// 农行人数
        BigDecimal nh_je10 = new BigDecimal(0.00);// 农行金额
        int zh_hs10 = 0;// 中行户数
        int zh_rs10 = 0;// 中行人数
        BigDecimal zh_je10 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_10 = new BigDecimal(0.00);// 二月合计

        // 十月 农行
        PaymentyearBankDTO paymentyearBankDTO37 = null;
        paymentyearBankDTO37 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "9");
        if (paymentyearBankDTO37 != null && !"".equals(paymentyearBankDTO37)) {
          nh_hs10 = paymentyearBankDTO37.getHushu();
          nh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "9");
          nh_je10 = paymentyearBankDTO37.getJiner();
        }
        // 十月 中行
        PaymentyearBankDTO paymentyearBankDTO38 = null;
        paymentyearBankDTO38 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "10", "8");
        if (paymentyearBankDTO38 != null && !"".equals(paymentyearBankDTO38)) {
          zh_hs10 = paymentyearBankDTO38.getHushu();
          zh_rs10 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "10", "8");
          zh_je10 = paymentyearBankDTO38.getJiner();
        }
        
        sum_10 = sum_10.add(nh_je10).add(zh_je10);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs10(nh_hs10);// 农行
        paymentyearstatisticsDTO.setNh_rs10(nh_rs10);
        paymentyearstatisticsDTO.setNh_je10(nh_je10);
        paymentyearstatisticsDTO.setZh_hs10(zh_hs10);// 中行
        paymentyearstatisticsDTO.setZh_rs10(zh_rs10);
        paymentyearstatisticsDTO.setZh_je10(zh_je10);
        paymentyearstatisticsDTO.setSum_10(sum_10);

        /** **************** */
        /*                   */
        /* 十一月 */
        /*                   */
        /** **************** */
        int nh_hs11 = 0;// 农行户数
        int nh_rs11 = 0;// 农行人数
        BigDecimal nh_je11 = new BigDecimal(0.00);// 农行金额
        int zh_hs11 = 0;// 中行户数
        int zh_rs11 = 0;// 中行人数
        BigDecimal zh_je11 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_11 = new BigDecimal(0.00);// 二月合计

        // 十月 农行
        PaymentyearBankDTO paymentyearBankDTO41 = null;
        paymentyearBankDTO41 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "9");
        if (paymentyearBankDTO41 != null && !"".equals(paymentyearBankDTO41)) {
          nh_hs11 = paymentyearBankDTO41.getHushu();
          nh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "9");
          nh_je11 = paymentyearBankDTO41.getJiner();
        }
        // 十月 中行
        PaymentyearBankDTO paymentyearBankDTO42 = null;
        paymentyearBankDTO42 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "11", "8");
        if (paymentyearBankDTO42 != null && !"".equals(paymentyearBankDTO42)) {
          zh_hs11 = paymentyearBankDTO42.getHushu();
          zh_rs11 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "11", "8");
          zh_je11 = paymentyearBankDTO42.getJiner();
        }
       
        sum_11 = sum_11.add(nh_je11).add(zh_je11);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs11(nh_hs11);// 农行
        paymentyearstatisticsDTO.setNh_rs11(nh_rs11);
        paymentyearstatisticsDTO.setNh_je11(nh_je11);
        paymentyearstatisticsDTO.setZh_hs11(zh_hs11);// 中行
        paymentyearstatisticsDTO.setZh_rs11(zh_rs11);
        paymentyearstatisticsDTO.setZh_je11(zh_je11);
        paymentyearstatisticsDTO.setSum_11(sum_11);

        /** **************** */
        /*                   */
        /* 十二月 */
        /*                   */
        /** **************** */
        int nh_hs12 = 0;// 农行户数
        int nh_rs12 = 0;// 农行人数
        BigDecimal nh_je12 = new BigDecimal(0.00);// 农行金额
        int zh_hs12 = 0;// 中行户数
        int zh_rs12 = 0;// 中行人数
        BigDecimal zh_je12 = new BigDecimal(0.00);// 中行金额
        BigDecimal sum_12 = new BigDecimal(0.00);// 二月合计

        // 十二月 农行
        PaymentyearBankDTO paymentyearBankDTO45 = null;
        paymentyearBankDTO45 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "9");
        if (paymentyearBankDTO45 != null && !"".equals(paymentyearBankDTO45)) {
          nh_hs12 = paymentyearBankDTO45.getHushu();
          nh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "9");
          nh_je12 = paymentyearBankDTO45.getJiner();
        }
        // 十二月 中行
        PaymentyearBankDTO paymentyearBankDTO46 = null;
        paymentyearBankDTO46 = orgHAFAccountFlowDAO.queryPaymentyear(
            officeCode, bizDate + "12", "8");
        if (paymentyearBankDTO46 != null && !"".equals(paymentyearBankDTO46)) {
          zh_hs12 = paymentyearBankDTO46.getHushu();
          zh_rs12 = orgHAFAccountFlowDAO.queryCountEmpId(officeCode, bizDate + "12", "8");
          zh_je12 = paymentyearBankDTO46.getJiner();
        }
        
        sum_12 = sum_12.add(nh_je12).add(zh_je12);// 合计十月金额

        paymentyearstatisticsDTO.setNh_hs12(nh_hs12);// 农行
        paymentyearstatisticsDTO.setNh_rs12(nh_rs12);
        paymentyearstatisticsDTO.setNh_je12(nh_je12);
        paymentyearstatisticsDTO.setZh_hs12(zh_hs12);// 中行
        paymentyearstatisticsDTO.setZh_rs12(zh_rs12);
        paymentyearstatisticsDTO.setZh_je12(zh_je12);
        paymentyearstatisticsDTO.setSum_12(sum_12);

        /** **************** */
        /*                   */
        /* 合计 */
        /*                   */
        /** **************** */
        int sum_a = 0;//
        int sum_b = 0;//
        BigDecimal sum_c = new BigDecimal(0.00);//
        int sum_d = 0;
        int sum_e = 0;//
        BigDecimal sum_f = new BigDecimal(0.00);//
        BigDecimal sum_m = new BigDecimal(0.00);//
        
        // 农行合计
        sum_a = nh_hs1 + nh_hs2 + nh_hs3 + nh_hs4 + nh_hs5 + nh_hs6 + nh_hs7
            + nh_hs8 + nh_hs9 + nh_hs10 + nh_hs11 + nh_hs12;//

        sum_b = nh_rs1 + nh_rs2 + nh_rs3 + nh_rs4 + nh_rs5 + nh_rs6 + nh_rs7
            + nh_rs8 + nh_rs9 + nh_rs10 + nh_rs11 + nh_rs12;//

        sum_c = sum_c.add(nh_je1).add(nh_je2).add(nh_je3).add(nh_je4).add(
            nh_je5).add(nh_je6).add(nh_je7).add(nh_je8).add(nh_je9)
            .add(nh_je10).add(nh_je11).add(nh_je12);//
        // 中行合计
        sum_d = zh_hs1 + zh_hs2 + zh_hs3 + zh_hs4 + zh_hs5 + zh_hs6 + zh_hs7
            + zh_hs8 + zh_hs9 + zh_hs10 + zh_hs11 + zh_hs12;//

        sum_e = zh_rs1 + zh_rs2 + zh_rs3 + zh_rs4 + zh_rs5 + zh_rs6 + zh_rs7
            + zh_rs8 + zh_rs9 + zh_rs10 + zh_rs11 + zh_rs12;//

        sum_f = sum_f.add(zh_je1).add(zh_je2).add(zh_je3).add(zh_je4).add(
            zh_je5).add(zh_je6).add(zh_je7).add(zh_je8).add(zh_je9)
            .add(zh_je10).add(zh_je11).add(zh_je12);//
        // 合计合计
        sum_m = sum_m.add(sum_1).add(sum_2).add(sum_3).add(sum_4).add(sum_5)
            .add(sum_6).add(sum_7).add(sum_8).add(sum_9).add(sum_10)
            .add(sum_11).add(sum_12);//
        
        paymentyearstatisticsDTO.setSum_a(sum_a);
        paymentyearstatisticsDTO.setSum_b(sum_b);
        paymentyearstatisticsDTO.setSum_c(sum_c);
        paymentyearstatisticsDTO.setSum_d(sum_d);
        paymentyearstatisticsDTO.setSum_e(sum_e);
        paymentyearstatisticsDTO.setSum_f(sum_f);
        paymentyearstatisticsDTO.setSum_m(sum_m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymentyearstatisticsDTO;
  }

}
