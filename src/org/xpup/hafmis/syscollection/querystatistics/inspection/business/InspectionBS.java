package org.xpup.hafmis.syscollection.querystatistics.inspection.business;

import java.math.BigDecimal;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.common.dao.InspectionDAO;
import org.xpup.hafmis.syscollection.querystatistics.inspection.bsinterface.IInspectionBS;
import org.xpup.hafmis.syscollection.querystatistics.inspection.form.InspectionFindAF;
import org.xpup.hafmis.syscollection.querystatistics.inspection.form.InspectionShowAF;

public class InspectionBS implements IInspectionBS{
  protected InspectionDAO inspectionDAO=null;

  public void setInspectionDAO(InspectionDAO inspectionDAO) {
    this.inspectionDAO = inspectionDAO;
  }

  
  public InspectionShowAF querygjjpayrate(Pagination pagination){
    InspectionShowAF af=new InspectionShowAF();
    InspectionFindAF iaf=(InspectionFindAF) pagination.getQueryCriterions().get("search");
    String date=iaf.getDate();
    String officeid=iaf.getOfficecode();
    String date1=iaf.getDate();
    String officeid1=iaf.getOfficecode();
    int lastyeardate=Integer.parseInt(date)-1;
    String subjectCode = "";
    String subjectCode2 = "";
    String officeName = "";
    if(officeid!=null && "".equals(officeid)){//市本级
      subjectCode = "1210101";//市本级科目代码
      subjectCode2 = "31101";
    }
    if(officeid!=null && "".equals(officeid)){//大石桥
      subjectCode = "1210102";//大石桥科目代码
      subjectCode2 = "31102";
    }
    if(officeid!=null && "".equals(officeid)){//鲅鱼圈
      subjectCode = "1210103";//鲅鱼圈科目代码
      subjectCode2 = "31103";
    }
    if(officeid!=null && "".equals(officeid)){//盖州
      subjectCode = "12120104";//盖州科目代码
      subjectCode2 = "31104";
    }
    String lastdate=new Integer(lastyeardate).toString();//上年
    String actualpay=inspectionDAO.actualpay(date, officeid,subjectCode);//当年住房公积金实际缴存额
    String baseshouldpay = inspectionDAO.querythisyearshouldpay(date, officeid);//(当年住房公积金应缴存额 基数未×12)
//  当年住房公积金应缴存额
    String thisyearshouldpay=(new BigDecimal(baseshouldpay).multiply(new BigDecimal(12))).toString();
//    String yearlastpersonloanbalance=inspectionDAO.yearlastpersonloanbalance(date, officeid, date1, officeid1);//年末住房公积金个人贷款余额
    String yearlastpersonloanbalance=inspectionDAO.yearlastpersonloanbalance_(date, officeid, subjectCode);//年末住房公积金个人贷款余额
    //System.out.println("个人贷款余额："+yearlastpersonloanbalance);
//    String personloangivemoney=inspectionDAO.personloangivemoney(date, officeid);//当年住房公积金个人贷款发放额
    String personloangivemoney=inspectionDAO.personloangivemoney_(date, officeid);//(当年住房公积金个人贷款发放额)
    //System.out.println("当年住房公积金个人贷款发放额:"+personloangivemoney);
//    String personloanoverduepay=inspectionDAO.personloanoverduepay(date, officeid);//年末住房公积金个人贷款逾期额
    String personloanoverduepay=inspectionDAO.personloanoverduepay_(date, officeid);//  (年末住房公积金个人贷款逾期额)
//    String incrementincome=inspectionDAO.incrementincome(date, officeid);//当年住房公积金增值收益
    String incrementincome=inspectionDAO.incrementincome_(date, officeid);//(当年住房公积金增值收益)
//    String monthpaybalance=inspectionDAO.monthpaybalance(date, officeid);//当年住房公积金月平均缴存余额
    String monthpaybalance = baseshouldpay;//(当年住房公积金月平均缴存余额)
    String lastactualpay=inspectionDAO.actualpay(lastdate, officeid,subjectCode);//上年公积金实际缴存额
    String lastpersonloangivemoney=inspectionDAO.personloangivemoney(lastdate, officeid);//上年住房公积金个人代款发放额
    //System.out.println("上年住房公积金个人代款发放额:"+lastpersonloangivemoney);
    String yearlastpaybalance=inspectionDAO.yearlastpaybalance(date, officeid,subjectCode);//年末住房公积金缴存余额
    //System.out.println("年末住房公积金缴存余额："+yearlastpaybalance);
    if(officeid!=null && !"".equals(officeid)){
      officeName = inspectionDAO.queryOfficeName(officeid);
    }
    //年度住房公积金缴存率
    BigDecimal a1=new BigDecimal(actualpay);//当年缴存额
    BigDecimal a2=new BigDecimal(thisyearshouldpay);
    BigDecimal yearpayrate=new BigDecimal(0.00);
    if(a1!=null && a2.intValue()!=0){
      yearpayrate = a1.divide(a2, 2, BigDecimal.ROUND_HALF_UP);
    }
    // 住房公积金缴存额增长率
    BigDecimal a3=new BigDecimal(lastactualpay);
    BigDecimal gjjpayaddrate=new BigDecimal(0.00);
    if(a1!=null && a3.intValue()!=0){
      gjjpayaddrate = a1.divide(a3, 2, BigDecimal.ROUND_HALF_UP);
    }
    // 住房公积金个人贷款比率
    BigDecimal b1=new BigDecimal(yearlastpersonloanbalance);
    BigDecimal b2=new BigDecimal(yearlastpaybalance);
    BigDecimal personloanrate=new BigDecimal(0.0);
    if(b1!=null && b2.intValue()!=0){
      personloanrate = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }
    //住房公积金个人贷款增长率
    BigDecimal c1=new BigDecimal(personloangivemoney);
    BigDecimal c2=new BigDecimal(lastpersonloangivemoney);
   
    BigDecimal personloanaddrate=new BigDecimal(0.00);   
    if(c1!=null && c2.intValue()!=0){
      personloanaddrate = c1.divide(c2, 2, BigDecimal.ROUND_HALF_UP);
    }
    // 住房公积金个人贷款逾期率
    BigDecimal d1=new BigDecimal(personloanoverduepay);
    BigDecimal d2=new BigDecimal(yearlastpersonloanbalance);
   
    BigDecimal personloanoverduerate=new BigDecimal(0.00);   
    if(d1!=null && d2.intValue()!=0){
      personloanoverduerate = d1.divide(d2, 2, BigDecimal.ROUND_HALF_UP);
    }
    // 住房公积金增值收益率
    BigDecimal e1=new BigDecimal(incrementincome);
    BigDecimal e2=new BigDecimal(thisyearshouldpay);
    
    e2 = e2.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
    BigDecimal incermentincomerate=new BigDecimal(0.00);
    if(e1!=null && e2.intValue()!=0){
      incermentincomerate = e1.divide(e2, 2, BigDecimal.ROUND_HALF_UP);
    }
    
    //住房公积金贷款风险准备金充足率
    BigDecimal f1=new BigDecimal(incrementincome);
    BigDecimal f2=new BigDecimal(monthpaybalance);
    
    BigDecimal loanmoneyforriskready=new BigDecimal(0.00);
    if(f1!=null && f2.intValue()!=0){
      loanmoneyforriskready = f1.divide(f2, 2, BigDecimal.ROUND_HALF_UP);
    }
    
    af.setGjjaddincome((incermentincomerate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setGjjpayaddrate((gjjpayaddrate.subtract(new BigDecimal(1))).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setGjjpayrate((yearpayrate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setPersonloan((personloanrate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setPersonloanaddrate((personloanaddrate.subtract(new BigDecimal(1))).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setPersonloanoverduerate((personloanoverduerate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setLoanmoneyforriskready((loanmoneyforriskready.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setOfficeName(officeName);
    af.setDate(date);
    return af;
  }
}
