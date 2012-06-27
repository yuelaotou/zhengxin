package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto.PickMonRepInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.bsinterface.IPickYearReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.dto.PickYearRepInfoDTO;

public class PickYearReportBS implements IPickYearReportBS {

  private PickHeadDAO pickHeadDAO = null;

  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }

  /**
   * @author wangshuang 提取统计年报表查询
   * @param pagination
   * @param securityInfo
   * @return list
   * @throws Exception
   * @throws BusinessException
   */
  public List queryPickYearRepInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    List returnList = new ArrayList();

    try {
      String office = (String) pagination.getQueryCriterions().get("office");
      String bank = (String) pagination.getQueryCriterions().get("bank");
      String years = (String) pagination.getQueryCriterions().get("year");
      String year = years.substring(0, 4);
      String month_s = years.substring(4, 6);
      String month = "";
      // 总的合计(统计每个原因的)
      // 购房
      int personCountSum_buyhouse = 0;
      BigDecimal pickMoneySum_buyhouse = new BigDecimal(0.00);
      // 死亡
      int personCountSum_death = 0;
      BigDecimal pickMoneySum_death = new BigDecimal(0.00);
      // 退休
      int personCountSum_retire = 0;
      BigDecimal pickMoneySum_retire = new BigDecimal(0.00);
      // 失业
      int personCountSum_jobless = 0;
      BigDecimal pickMoneySum_jobless = new BigDecimal(0.00);
      // 还贷
      int personCountSum_callback = 0;
      BigDecimal pickMoneySum_callback = new BigDecimal(0.00);
      // 其他
      int personCountSum_other = 0;
      BigDecimal pickMoneySum_other = new BigDecimal(0.00);
      // 扣款
      int personCountSum_deduct = 0;
      BigDecimal pickMoneySum_deduct = new BigDecimal(0.00);
      // 总计
      int personCountSum_total = 0;
      BigDecimal pickMoneySum_total = new BigDecimal(0.00);
      // 循环12次代表是十二个月
      for (int i = 0; i < 12; i++) {
        month = ((i + 1) + "").length() < 2 ? "0" + (i + 1) : "" + (i + 1);
        if (Integer.parseInt(month_s) > i) {
          list = pickHeadDAO.queryYearReportInfoList(office, bank,
              year + month, securityInfo);
        } else {
          list = null;
        }
        int personCount_buyhouse = 0;
        BigDecimal pickMoney_buyhouse = new BigDecimal(0.00);
        /* 还贷 */
        int personCount_callback = 0;
        BigDecimal pickMoney_callback = new BigDecimal(0.00);
        /* 其他 */
        int personCount_other = 0;
        BigDecimal pickMoney_other = new BigDecimal(0.00);
        /* 退休 */
        int personCount_retire = 0;
        BigDecimal pickMoney_retire = new BigDecimal(0.00);
        /* 失业 */
        int personCount_jobless = 0;
        BigDecimal pickMoney_jobless = new BigDecimal(0.00);
        /* 死亡 */
        int personCount_death = 0;
        BigDecimal pickMoney_death = new BigDecimal(0.00);
        /* 个贷扣款 */
        int personCount_deduct = 0;
        BigDecimal pickMoney_deduct = new BigDecimal(0.00);
        /* 合计(统计每个银行的) */
        int personCount_total = 0;
        BigDecimal pickMoney_total = new BigDecimal(0.00);
        // 总计
        if (list != null && list.size() != 0) {
          for (int j = 0; j < list.size(); j++) {
            PickYearRepInfoDTO dto = (PickYearRepInfoDTO) list.get(j);
            // 1.购房
            if (dto.getPickReason()
                .equals(BusiConst.PICKUPREASON_BUYHOUSE + "")) {
              personCount_buyhouse = dto.getPersonCount_temp().intValue();
              pickMoney_buyhouse = dto.getPickMoney_temp();
              personCountSum_buyhouse += dto.getPersonCount_temp().intValue();
              pickMoneySum_buyhouse = pickMoneySum_buyhouse.add(dto
                  .getPickMoney_temp());
            }
            // 死亡
            if (dto.getPickReason().equals(BusiConst.PICKUPREASON_DEATH + "")) {
              personCount_death = dto.getPersonCount_temp().intValue();
              pickMoney_death = dto.getPickMoney_temp();
              personCountSum_death += dto.getPersonCount_temp().intValue();
              pickMoneySum_death = pickMoneySum_death.add(dto
                  .getPickMoney_temp());
            }
            // 失业
            if (dto.getPickReason().equals(BusiConst.PICKUPREASON_JOBLESS + "")) {
              personCount_jobless = dto.getPersonCount_temp().intValue();
              pickMoney_jobless = dto.getPickMoney_temp();
              personCountSum_jobless += dto.getPersonCount_temp().intValue();
              pickMoneySum_jobless = pickMoneySum_jobless.add(dto
                  .getPickMoney_temp());
            }
            // 退休
            if (dto.getPickReason().equals(BusiConst.PICKUPREASON_BOWOUT + "")) {
              personCount_retire = dto.getPersonCount_temp().intValue();
              pickMoney_retire = dto.getPickMoney_temp();
              personCountSum_retire += dto.getPersonCount_temp().intValue();
              pickMoneySum_retire = pickMoneySum_retire.add(dto
                  .getPickMoney_temp());
            }
            // 还贷
            if (dto.getPickReason().equals(
                BusiConst.PICKUPREASON_GIVEMONEYClEAR + "")) {
              personCount_callback = dto.getPersonCount_temp().intValue();
              pickMoney_callback = dto.getPickMoney_temp();
              personCountSum_callback += dto.getPersonCount_temp().intValue();
              pickMoneySum_callback = pickMoneySum_callback.add(dto
                  .getPickMoney_temp());
            }
            // 扣款
            if (dto.getPickReason().equals(
                BusiConst.PICKUPREASON_GIVEMONEYBYMON + "")) {
              personCount_deduct = dto.getPersonCount_temp().intValue();
              pickMoney_deduct = dto.getPickMoney_temp();
              personCountSum_deduct += dto.getPersonCount_temp().intValue();
              pickMoneySum_deduct = pickMoneySum_deduct.add(dto
                  .getPickMoney_temp());
            }
            // 其他
            if (dto.getPickReason().equals(BusiConst.PICKUPREASON_DISEASE + "")
                || dto.getPickReason().equals(
                    BusiConst.PICKUPREASON_DISTRESS + "")
                || dto.getPickReason().equals(
                    BusiConst.PICKUPREASON_PARTREST + "")
                || dto.getPickReason().equals(
                    BusiConst.PICKUPREASON_DECRUITMENT + "")
                || dto.getPickReason().equals(
                    BusiConst.PICKUPREASON_DISTORY + "")
                || dto.getPickReason().equals(
                    BusiConst.PICKUPREASON_SETTLE + "")) {
              personCount_other += dto.getPersonCount_temp().intValue();
              pickMoney_other = pickMoney_other.add(dto.getPickMoney_temp());
              personCountSum_other += dto.getPersonCount_temp().intValue();
              pickMoneySum_other = pickMoneySum_other.add(dto
                  .getPickMoney_temp());
            }
            personCount_total += dto.getPersonCount_temp().intValue();
            pickMoney_total = pickMoney_total.add(dto.getPickMoney_temp());
            personCountSum_total += dto.getPersonCount_temp().intValue();
            pickMoneySum_total = pickMoneySum_total
                .add(dto.getPickMoney_temp());
          }
        }
        // 创建一行数据的实体
        PickYearRepInfoDTO dto1 = new PickYearRepInfoDTO();
        dto1.setPersonCount_buyhouse(new Integer(personCount_buyhouse));
        dto1.setPickMoney_buyhouse(pickMoney_buyhouse);

        dto1.setPersonCount_retire(new Integer(personCount_retire));
        dto1.setPickMoney_retire(pickMoney_retire);

        dto1.setPersonCount_death(new Integer(personCount_death));
        dto1.setPickMoney_death(pickMoney_death);

        dto1.setPersonCount_jobless(new Integer(personCount_jobless));
        dto1.setPickMoney_jobless(pickMoney_jobless);

        dto1.setPersonCount_other(new Integer(personCount_other));
        dto1.setPickMoney_other(pickMoney_other);

        dto1.setPersonCount_callback(new Integer(personCount_callback));
        dto1.setPickMoney_callback(pickMoney_callback);

        dto1.setPersonCount_deduct(new Integer(personCount_deduct));
        dto1.setPickMoney_deduct(pickMoney_deduct);

        dto1.setPersonCount_total(new Integer(personCount_total));
        dto1.setPickMoney_total(pickMoney_total);
        // 如果总钱数不为零即可求比例
        if (pickMoney_total.doubleValue() != 0) {
          dto1.setPickMoneyRate_buyhouse(pickMoney_buyhouse.divide(
              pickMoney_total, 2, BigDecimal.ROUND_HALF_UP).multiply(
              new BigDecimal(100))
              + "%");
          dto1.setPickMoneyRate_retire(pickMoney_retire.divide(pickMoney_total,
              2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
              + "%");
          dto1.setPickMoneyRate_death(pickMoney_death.divide(pickMoney_total,
              2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
              + "%");
          dto1.setPickMoneyRate_jobless(pickMoney_jobless.divide(
              pickMoney_total, 2, BigDecimal.ROUND_HALF_UP).multiply(
              new BigDecimal(100))
              + "%");
          dto1.setPickMoneyRate_other(pickMoney_other.divide(pickMoney_total,
              2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
              + "%");
          dto1.setPickMoneyRate_callback(pickMoney_callback.divide(
              pickMoney_total, 2, BigDecimal.ROUND_HALF_UP).multiply(
              new BigDecimal(100))
              + "%");
          BigDecimal remainer = new BigDecimal(1.00).subtract(pickMoney_other
              .divide(pickMoney_total, 2, BigDecimal.ROUND_HALF_UP).add(
                  pickMoney_buyhouse.divide(pickMoney_total, 2,
                      BigDecimal.ROUND_HALF_UP)).add(
                  pickMoney_retire.divide(pickMoney_total, 2,
                      BigDecimal.ROUND_HALF_UP)).add(
                  pickMoney_death.divide(pickMoney_total, 2,
                      BigDecimal.ROUND_HALF_UP)).add(
                  pickMoney_jobless.divide(pickMoney_total, 2,
                      BigDecimal.ROUND_HALF_UP)).add(
                  pickMoney_callback.divide(pickMoney_total, 2,
                      BigDecimal.ROUND_HALF_UP)));
          dto1.setPickMoneyRate_deduct(remainer.multiply(new BigDecimal(100))
              + "%");
        }
        dto1.setMonth(convertMonth(i));// 显示月份
        returnList.add(dto1);
      }

      PickYearRepInfoDTO totalDto = new PickYearRepInfoDTO();
      totalDto.setMonth("总计");
      totalDto.setPersonCount_buyhouse(new Integer(personCountSum_buyhouse));
      totalDto.setPickMoney_buyhouse(pickMoneySum_buyhouse);

      totalDto.setPersonCount_retire(new Integer(personCountSum_retire));
      totalDto.setPickMoney_retire(pickMoneySum_retire);

      totalDto.setPersonCount_death(new Integer(personCountSum_death));
      totalDto.setPickMoney_death(pickMoneySum_death);

      totalDto.setPersonCount_jobless(new Integer(personCountSum_jobless));
      totalDto.setPickMoney_jobless(pickMoneySum_jobless);

      totalDto.setPersonCount_other(new Integer(personCountSum_other));
      totalDto.setPickMoney_other(pickMoneySum_other);

      totalDto.setPersonCount_callback(new Integer(personCountSum_callback));
      totalDto.setPickMoney_callback(pickMoneySum_callback);

      totalDto.setPersonCount_deduct(new Integer(personCountSum_deduct));
      totalDto.setPickMoney_deduct(pickMoneySum_deduct);

      totalDto.setPersonCount_total(new Integer(personCountSum_total));
      totalDto.setPickMoney_total(pickMoneySum_total);
      returnList.add(totalDto);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return returnList;
  }

  private String convertMonth(int month) {
    String str = "";
    switch (month) {
    case 0:
      str = "一月";
      break;
    case 1:
      str = "二月";
      break;
    case 2:
      str = "三月";
      break;
    case 3:
      str = "四月";
      break;
    case 4:
      str = "五月";
      break;
    case 5:
      str = "六月";
      break;
    case 6:
      str = "七月";
      break;
    case 7:
      str = "八月";
      break;
    case 8:
      str = "九月";
      break;
    case 9:
      str = "十月";
      break;
    case 10:
      str = "十一月";
      break;
    case 11:
      str = "十二月";
      break;
    }
    return str;
  }

}
