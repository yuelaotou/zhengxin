package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.bsinterface.IPaymentYearReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.dto.PaymentYearDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.dto.PaymentYearReportDTO;

public class PaymentYearReportBS implements IPaymentYearReportBS {

  private PaymentHeadDAO paymentHeadDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public List queryPaymentYearReportInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List returnList = new ArrayList();
    try {
      String office = (String) pagination.getQueryCriterions().get("office");
      String bank = (String) pagination.getQueryCriterions().get("bank");
      String year = (String) pagination.getQueryCriterions().get("year");
      List list = paymentHeadDAO.queryPaymentYearReportInfo(office, bank, year);
      String bankid = "";
      String collName = "";
      int time = 0;
      int month = 0;
      PaymentYearReportDTO dtos = null;
      PaymentYearReportDTO dtoall = new PaymentYearReportDTO();
      PaymentYearReportDTO dtolast = new PaymentYearReportDTO();
      PaymentYearReportDTO dtoadd = new PaymentYearReportDTO();
      dtoall.setCollName("合计");
      dtolast.setCollName("去年同期完成");
      dtoadd.setCollName("同比增长(%)");
      for(int i=0;i<list.size();i++){
        PaymentYearDTO dto = (PaymentYearDTO)list.get(i);
        if(!bankid.equals(dto.getCollBankId())){
          bankid=dto.getCollBankId();
          CollBank collBank = collBankDAO.getCollBankByCollBankid(bankid);
          collName=collBank.getCollBankName();
          if(time!=0){
            dtoall.setCountAll(dtos.getCountAll()+dtoall.getCountAll());
            dtoall.setPersonAll(dtos.getPersonAll()+dtoall.getPersonAll());
            dtoall.setMoneyAll(dtos.getMoneyAll().add(dtoall.getMoneyAll()));
            returnList.add(dtos);
          }
          time++;
          dtos = new PaymentYearReportDTO();
          dtos.setCollName(collName);
          if(dto.getYearmonth().substring(4,6).equals("01")){
            dtos.setCount1(dto.getCount());
            dtos.setPerson1(dto.getPerson());
            dtos.setMoney1(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount1(dto.getCount()+dtoall.getCount1());
            dtoall.setPerson1(dto.getPerson()+dtoall.getPerson1());
            dtoall.setMoney1(dto.getMoney().add(dtoall.getMoney1()));
            month=1;
          }else if(dto.getYearmonth().substring(4,6).equals("02")){
            dtos.setCount2(dto.getCount());
            dtos.setPerson2(dto.getPerson());
            dtos.setMoney2(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount2(dto.getCount()+dtoall.getCount2());
            dtoall.setPerson2(dto.getPerson()+dtoall.getPerson2());
            dtoall.setMoney2(dto.getMoney().add(dtoall.getMoney2()));
            month=2;
          }else if(dto.getYearmonth().substring(4,6).equals("03")){
            dtos.setCount3(dto.getCount());
            dtos.setPerson3(dto.getPerson());
            dtos.setMoney3(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount3(dto.getCount()+dtoall.getCount3());
            dtoall.setPerson3(dto.getPerson()+dtoall.getPerson3());
            dtoall.setMoney3(dto.getMoney().add(dtoall.getMoney3()));
            month=3;
          }else if(dto.getYearmonth().substring(4,6).equals("04")){
            dtos.setCount4(dto.getCount());
            dtos.setPerson4(dto.getPerson());
            dtos.setMoney4(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount4(dto.getCount()+dtoall.getCount4());
            dtoall.setPerson4(dto.getPerson()+dtoall.getPerson4());
            dtoall.setMoney4(dto.getMoney().add(dtoall.getMoney4()));
            month=4;
          }else if(dto.getYearmonth().substring(4,6).equals("05")){
            dtos.setCount5(dto.getCount());
            dtos.setPerson5(dto.getPerson());
            dtos.setMoney5(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount5(dto.getCount()+dtoall.getCount5());
            dtoall.setPerson5(dto.getPerson()+dtoall.getPerson5());
            dtoall.setMoney5(dto.getMoney().add(dtoall.getMoney5()));
            month=5;
          }else if(dto.getYearmonth().substring(4,6).equals("06")){
            dtos.setCount6(dto.getCount());
            dtos.setPerson6(dto.getPerson());
            dtos.setMoney6(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount6(dto.getCount()+dtoall.getCount6());
            dtoall.setPerson6(dto.getPerson()+dtoall.getPerson6());
            dtoall.setMoney6(dto.getMoney().add(dtoall.getMoney6()));
            month=6;
          }else if(dto.getYearmonth().substring(4,6).equals("07")){
            dtos.setCount7(dto.getCount());
            dtos.setPerson7(dto.getPerson());
            dtos.setMoney7(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount7(dto.getCount()+dtoall.getCount7());
            dtoall.setPerson7(dto.getPerson()+dtoall.getPerson7());
            dtoall.setMoney7(dto.getMoney().add(dtoall.getMoney7()));
            month=7;
          }else if(dto.getYearmonth().substring(4,6).equals("08")){
            dtos.setCount8(dto.getCount());
            dtos.setPerson8(dto.getPerson());
            dtos.setMoney8(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount8(dto.getCount()+dtoall.getCount8());
            dtoall.setPerson8(dto.getPerson()+dtoall.getPerson8());
            dtoall.setMoney8(dto.getMoney().add(dtoall.getMoney8()));
            month=8;
          }else if(dto.getYearmonth().substring(4,6).equals("09")){
            dtos.setCount9(dto.getCount());
            dtos.setPerson9(dto.getPerson());
            dtos.setMoney9(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount9(dto.getCount()+dtoall.getCount9());
            dtoall.setPerson9(dto.getPerson()+dtoall.getPerson9());
            dtoall.setMoney9(dto.getMoney().add(dtoall.getMoney9()));
            month=9;
          }else if(dto.getYearmonth().substring(4,6).equals("10")){
            dtos.setCount10(dto.getCount());
            dtos.setPerson10(dto.getPerson());
            dtos.setMoney10(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount10(dto.getCount()+dtoall.getCount10());
            dtoall.setPerson10(dto.getPerson()+dtoall.getPerson10());
            dtoall.setMoney10(dto.getMoney().add(dtoall.getMoney10()));
            month=10;
          }else if(dto.getYearmonth().substring(4,6).equals("11")){
            dtos.setCount11(dto.getCount());
            dtos.setPerson11(dto.getPerson());
            dtos.setMoney11(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount11(dto.getCount()+dtoall.getCount11());
            dtoall.setPerson11(dto.getPerson()+dtoall.getPerson11());
            dtoall.setMoney11(dto.getMoney().add(dtoall.getMoney11()));
            month=11;
          }else if(dto.getYearmonth().substring(4,6).equals("12")){
            dtos.setCount12(dto.getCount());
            dtos.setPerson12(dto.getPerson());
            dtos.setMoney12(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount12(dto.getCount()+dtoall.getCount12());
            dtoall.setPerson12(dto.getPerson()+dtoall.getPerson12());
            dtoall.setMoney12(dto.getMoney().add(dtoall.getMoney12()));
            month=12;
          }
        }else{
          if(dto.getYearmonth().substring(4,6).equals("01")){
            dtos.setCount1(dto.getCount());
            dtos.setPerson1(dto.getPerson());
            dtos.setMoney1(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount1(dto.getCount()+dtoall.getCount1());
            dtoall.setPerson1(dto.getPerson()+dtoall.getPerson1());
            dtoall.setMoney1(dto.getMoney().add(dtoall.getMoney1()));
            month=1;
          }else if(dto.getYearmonth().substring(4,6).equals("02")){
            dtos.setCount2(dto.getCount());
            dtos.setPerson2(dto.getPerson());
            dtos.setMoney2(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount2(dto.getCount()+dtoall.getCount2());
            dtoall.setPerson2(dto.getPerson()+dtoall.getPerson2());
            dtoall.setMoney2(dto.getMoney().add(dtoall.getMoney2()));
            month=2;
          }else if(dto.getYearmonth().substring(4,6).equals("03")){
            dtos.setCount3(dto.getCount());
            dtos.setPerson3(dto.getPerson());
            dtos.setMoney3(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount3(dto.getCount()+dtoall.getCount3());
            dtoall.setPerson3(dto.getPerson()+dtoall.getPerson3());
            dtoall.setMoney3(dto.getMoney().add(dtoall.getMoney3()));
            month=3;
          }else if(dto.getYearmonth().substring(4,6).equals("04")){
            dtos.setCount4(dto.getCount());
            dtos.setPerson4(dto.getPerson());
            dtos.setMoney4(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount4(dto.getCount()+dtoall.getCount4());
            dtoall.setPerson4(dto.getPerson()+dtoall.getPerson4());
            dtoall.setMoney4(dto.getMoney().add(dtoall.getMoney4()));
            month=4;
          }else if(dto.getYearmonth().substring(4,6).equals("05")){
            dtos.setCount5(dto.getCount());
            dtos.setPerson5(dto.getPerson());
            dtos.setMoney5(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount5(dto.getCount()+dtoall.getCount5());
            dtoall.setPerson5(dto.getPerson()+dtoall.getPerson5());
            dtoall.setMoney5(dto.getMoney().add(dtoall.getMoney5()));
            month=5;
          }else if(dto.getYearmonth().substring(4,6).equals("06")){
            dtos.setCount6(dto.getCount());
            dtos.setPerson6(dto.getPerson());
            dtos.setMoney6(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount6(dto.getCount()+dtoall.getCount6());
            dtoall.setPerson6(dto.getPerson()+dtoall.getPerson6());
            dtoall.setMoney6(dto.getMoney().add(dtoall.getMoney6()));
            month=6;
          }else if(dto.getYearmonth().substring(4,6).equals("07")){
            dtos.setCount7(dto.getCount());
            dtos.setPerson7(dto.getPerson());
            dtos.setMoney7(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount7(dto.getCount()+dtoall.getCount7());
            dtoall.setPerson7(dto.getPerson()+dtoall.getPerson7());
            dtoall.setMoney7(dto.getMoney().add(dtoall.getMoney7()));
            month=7;
          }else if(dto.getYearmonth().substring(4,6).equals("08")){
            dtos.setCount8(dto.getCount());
            dtos.setPerson8(dto.getPerson());
            dtos.setMoney8(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount8(dto.getCount()+dtoall.getCount8());
            dtoall.setPerson8(dto.getPerson()+dtoall.getPerson8());
            dtoall.setMoney8(dto.getMoney().add(dtoall.getMoney8()));
            month=8;
          }else if(dto.getYearmonth().substring(4,6).equals("09")){
            dtos.setCount9(dto.getCount());
            dtos.setPerson9(dto.getPerson());
            dtos.setMoney9(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount9(dto.getCount()+dtoall.getCount9());
            dtoall.setPerson9(dto.getPerson()+dtoall.getPerson9());
            dtoall.setMoney9(dto.getMoney().add(dtoall.getMoney9()));
            month=9;
          }else if(dto.getYearmonth().substring(4,6).equals("10")){
            dtos.setCount10(dto.getCount());
            dtos.setPerson10(dto.getPerson());
            dtos.setMoney10(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount10(dto.getCount()+dtoall.getCount10());
            dtoall.setPerson10(dto.getPerson()+dtoall.getPerson10());
            dtoall.setMoney10(dto.getMoney().add(dtoall.getMoney10()));
            month=10;
          }else if(dto.getYearmonth().substring(4,6).equals("11")){
            dtos.setCount11(dto.getCount());
            dtos.setPerson11(dto.getPerson());
            dtos.setMoney11(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount11(dto.getCount()+dtoall.getCount11());
            dtoall.setPerson11(dto.getPerson()+dtoall.getPerson11());
            dtoall.setMoney11(dto.getMoney().add(dtoall.getMoney11()));
            month=11;
          }else if(dto.getYearmonth().substring(4,6).equals("12")){
            dtos.setCount12(dto.getCount());
            dtos.setPerson12(dto.getPerson());
            dtos.setMoney12(dto.getMoney());
            dtos.setCountAll(dtos.getCountAll()+dto.getCount());
            dtos.setPersonAll(dtos.getPersonAll()+dto.getPerson());
            dtos.setMoneyAll(dtos.getMoneyAll().add(dto.getMoney()));
            dtoall.setCount12(dto.getCount()+dtoall.getCount12());
            dtoall.setPerson12(dto.getPerson()+dtoall.getPerson12());
            dtoall.setMoney12(dto.getMoney().add(dtoall.getMoney12()));
            month=12;
          }
        }
      }
      if(list!=null && list.size()!=0){
        dtoall.setCountAll(dtos.getCountAll()+dtoall.getCountAll());
        dtoall.setPersonAll(dtos.getPersonAll()+dtoall.getPersonAll());
        dtoall.setMoneyAll(dtos.getMoneyAll().add(dtoall.getMoneyAll()));
        returnList.add(dtos);
        returnList.add(dtoall);
        if(year.equals("2009")){
          dtolast.setMoney1(new BigDecimal("22989643.95"));
          dtolast.setMoney2(new BigDecimal("27792946.60"));
          dtolast.setMoney3(new BigDecimal("32482366.30"));
          dtolast.setMoney4(new BigDecimal("34257996.71"));
          dtolast.setMoney5(new BigDecimal("34108884.47"));
          dtolast.setMoney6(new BigDecimal("52135159.45"));
          dtolast.setMoney7(new BigDecimal("38827556.03"));
          dtolast.setMoney8(new BigDecimal("35072429.61"));
          dtolast.setMoney9(new BigDecimal("31515641.90"));
          dtolast.setMoney10(new BigDecimal("46316633.20"));
          dtolast.setMoney11(new BigDecimal("39813818.65"));
          dtolast.setMoney12(new BigDecimal("66814410.33"));
        }else{
          List lastlist = paymentHeadDAO.queryPaymentLastyearReportInfo(office, bank, (Integer.parseInt(year)-1)+"");
          for(int i=0;i<lastlist.size();i++){
            PaymentYearDTO dto = (PaymentYearDTO)lastlist.get(i);
            if(dto.getYearmonth().substring(4,6).equals("01")){
              dtolast.setMoney1(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("02")){
              dtolast.setMoney2(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("03")){
              dtolast.setMoney3(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("04")){
              dtolast.setMoney4(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("05")){
              dtolast.setMoney5(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("06")){
              dtolast.setMoney6(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("07")){
              dtolast.setMoney7(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("08")){
              dtolast.setMoney8(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("09")){
              dtolast.setMoney9(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("10")){
              dtolast.setMoney10(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("11")){
              dtolast.setMoney11(dto.getMoney());
            }
            if(dto.getYearmonth().substring(4,6).equals("12")){
              dtolast.setMoney12(dto.getMoney());
            }
          }
        }
        for(int i=0;i<month;i++){
          if(i==0){
            dtoadd.setMoney1(dtoall.getMoney1().subtract(dtolast.getMoney1()).divide(dtolast.getMoney1(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney1()));
          }
          if(i==1){
            dtoadd.setMoney2(dtoall.getMoney2().subtract(dtolast.getMoney2()).divide(dtolast.getMoney2(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney2()));
          }
          if(i==2){
            dtoadd.setMoney3(dtoall.getMoney3().subtract(dtolast.getMoney3()).divide(dtolast.getMoney3(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney3()));
          }
          if(i==3){
            dtoadd.setMoney4(dtoall.getMoney4().subtract(dtolast.getMoney4()).divide(dtolast.getMoney4(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney4()));
          }
          if(i==4){
            dtoadd.setMoney5(dtoall.getMoney5().subtract(dtolast.getMoney5()).divide(dtolast.getMoney5(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney5()));
          }
          if(i==5){
            dtoadd.setMoney6(dtoall.getMoney6().subtract(dtolast.getMoney6()).divide(dtolast.getMoney6(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney6()));
          }
          if(i==6){
            dtoadd.setMoney7(dtoall.getMoney7().subtract(dtolast.getMoney7()).divide(dtolast.getMoney7(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney7()));
          }
          if(i==7){
            dtoadd.setMoney8(dtoall.getMoney8().subtract(dtolast.getMoney8()).divide(dtolast.getMoney8(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney8()));
          }
          if(i==8){
            dtoadd.setMoney9(dtoall.getMoney9().subtract(dtolast.getMoney9()).divide(dtolast.getMoney9(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney9()));
          }
          if(i==9){
            dtoadd.setMoney10(dtoall.getMoney10().subtract(dtolast.getMoney10()).divide(dtolast.getMoney10(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney10()));
          }
          if(i==10){
            dtoadd.setMoney11(dtoall.getMoney11().subtract(dtolast.getMoney11()).divide(dtolast.getMoney11(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney11()));
          }
          if(i==11){
            dtoadd.setMoney12(dtoall.getMoney12().subtract(dtolast.getMoney12()).divide(dtolast.getMoney12(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            dtolast.setMoneyAll(dtolast.getMoneyAll().add(dtolast.getMoney12()));
          }
        }
        dtoadd.setMoneyAll(dtoall.getMoneyAll().subtract(dtolast.getMoneyAll()).divide(dtolast.getMoneyAll(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
        for(int i=month;i<12;i++){
          if(i==0)
            dtolast.setMoney1(new BigDecimal(0.00));
          if(i==1)
            dtolast.setMoney2(new BigDecimal(0.00));
          if(i==2)
            dtolast.setMoney3(new BigDecimal(0.00));
          if(i==3)
            dtolast.setMoney4(new BigDecimal(0.00));
          if(i==4)
            dtolast.setMoney5(new BigDecimal(0.00));
          if(i==5)
            dtolast.setMoney6(new BigDecimal(0.00));
          if(i==6)
            dtolast.setMoney7(new BigDecimal(0.00));
          if(i==7)
            dtolast.setMoney8(new BigDecimal(0.00));
          if(i==8)
            dtolast.setMoney9(new BigDecimal(0.00));
          if(i==9)
            dtolast.setMoney10(new BigDecimal(0.00));
          if(i==10)
            dtolast.setMoney11(new BigDecimal(0.00));
          if(i==11)
            dtolast.setMoney12(new BigDecimal(0.00));
        }
        if(year.equals("2009") && (office==null || office.equals("")) && (bank==null || bank.equals(""))){
          returnList.add(dtolast);
          returnList.add(dtoadd);
        }else if(!year.equals("2009")){
          returnList.add(dtolast);
          returnList.add(dtoadd);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }

}
