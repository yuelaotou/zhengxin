package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.bsinterface.IParticularglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.dto.ParticularglDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.form.ParticularglTaAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTcDTO;

public class ParticularglBS implements IParticularglBS{
  
  private LoanFlowHeadDAO loanFlowHeadDAO;
  
  private BorrowerAccDAO borrowerAccDAO;
  
  private RestoreLoanDAO restoreLoanDAO;
  
  private CollBankDAO collBankDAO = null;
  
  
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }
/**
 * hanl
 * 显示明细账中，发生年页面信息
 */
  public ParticularglTaAF showparticularglList(Pagination pagination, SecurityInfo securityInfo) throws Exception {

    ParticularglTaAF particularglTaAF=new ParticularglTaAF();
    // 查询条件

    String  borrowercontractid= (String) pagination.getQueryCriterions().get("borrowercontractid");//借款合同编号
    String  bizdateB= (String) pagination.getQueryCriterions().get("bizdateB");//查询日期起始
    String  bizdateE= (String) pagination.getQueryCriterions().get("bizdateE");//查询日期截止
    String orderBy = (String) pagination.getOrderBy();
    String orderother = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    
    List list1=new ArrayList();//分页的list1
    List newlist=new ArrayList();//分页list1更改完数据
    
    List list2=new ArrayList();//分页的list
    List newlist2=new ArrayList();//分页list更改完数据
    String bankId = "";
    if(borrowercontractid!=null){
      
      List list= borrowerAccDAO.showLoandeskaccqueryallList(null, null, null, null,
          borrowercontractid, null, null, null, 
          null, null, null, null, 
          null, null, null, null, 
          null, null,securityInfo,null,
          null, null, null, null, null, null,null,null);
      if(list.size()!=0){
      LoandeskaccqueryTaDTO loandeskaccqueryTaDTO =(LoandeskaccqueryTaDTO)list.get(0);
      particularglTaAF.setContractid(loandeskaccqueryTaDTO.getContactid());
      particularglTaAF.setLoankouacc(loandeskaccqueryTaDTO.getLoankouacc());
      particularglTaAF.setBorrowername(loandeskaccqueryTaDTO.getBorrowername());
      particularglTaAF.setCardnum(loandeskaccqueryTaDTO.getCardnum());
      CollBank  collBank=collBankDAO.getCollBankByCollBankid_(loandeskaccqueryTaDTO.getPaybank());
      if(collBank!=null){
        particularglTaAF.setLoanbank(collBank.getCollBankName());
      }
      particularglTaAF.setLoanmoney(loandeskaccqueryTaDTO.getLoanmoney());
      particularglTaAF.setLoanlimit(loandeskaccqueryTaDTO.getLoanlimit());
      particularglTaAF.setNobackmoney(loandeskaccqueryTaDTO.getNobackmoney());
      particularglTaAF.setOveaerloanrepay(loandeskaccqueryTaDTO.getOvareloanrepay());
      particularglTaAF.setBallbalance(loandeskaccqueryTaDTO.getBallbalance());
      particularglTaAF.setSrealcorpus(loandeskaccqueryTaDTO.getRealcorpus());
      particularglTaAF.setSrealinterest(loandeskaccqueryTaDTO.getRealinterest());
      particularglTaAF.setSrealpunishinterest(loandeskaccqueryTaDTO.getRealpunishinterest());
      LoandeskaccqueryTcDTO dtot=new LoandeskaccqueryTcDTO();
      dtot=borrowerAccDAO.queryloanMoneyLoanmood(borrowercontractid); //求还款方式和贷款余额 
      LoandeskaccqueryTcDTO loandeskaccqueryTcDTO=new LoandeskaccqueryTcDTO();
      loandeskaccqueryTcDTO=borrowerAccDAO.findborrowerAccInfo(borrowercontractid);
      particularglTaAF.setLoanmode(BusiTools.getBusiValue(Integer.parseInt(dtot.getPayloanmood()), BusiConst.PLRECOVERTYPE));
      particularglTaAF.setOverplusloanmoney(dtot.getLoanleftmoney());
      String loanRepayDay = loandeskaccqueryTaDTO.getLoanRepayDay();
      String yearMonth=bizdateE.substring(0,6);
      String days = securityInfo.getUserInfo().getPlbizDate().substring(6,8);
      if(new Integer(loanRepayDay).intValue()< new Integer(days).intValue()){
        yearMonth = BusiTools.addMonths(yearMonth, 1);
      }
      /**
       * 月还本息
       */
      String corputInterest = restoreLoanDAO.findthisMonthPay(loandeskaccqueryTaDTO.getContactid(),yearMonth);
      if(corputInterest==null || "".equals(corputInterest)){
        yearMonth = BusiTools.addMonths(yearMonth, 1);
        corputInterest = restoreLoanDAO.findthisMonthPay(loandeskaccqueryTaDTO.getContactid(),yearMonth);
      }
      particularglTaAF.setCorputInterest(new BigDecimal(corputInterest));
      /**
       * 剩余期限
       */
      particularglTaAF.setOverlimited(loandeskaccqueryTaDTO.getOverlimited());
      /**
       * 其他欠款
       */
      particularglTaAF.setOtherArrearage(loandeskaccqueryTaDTO.getOtherArrearage().equals("0")?"否":"是");
      //结束
      int temp_plLoanReturnTypes = securityInfo.getPlLoanReturnType();
      //根据权限中的还款类型判断以哪为主
      if(temp_plLoanReturnTypes == BusiConst.PLLOANRETURNTYPE_CENTER){
       //中心为主
      
        String payday=loandeskaccqueryTaDTO.getPayday();//还款日
        String plbizdate=securityInfo.getUserInfo().getPlbizDate();
        String yearmonth=plbizdate.substring(0,6);//年月
        String day=plbizdate.substring(6);//日期
        
        int dayint=Integer.parseInt(day);
        int paydayint=Integer.parseInt(payday);
        LoandeskaccqueryTcDTO loandeskaccqueryTcDTO1=new LoandeskaccqueryTcDTO();
        if(dayint<=paydayint){//会计日小于等于还款日
          loandeskaccqueryTcDTO1=restoreLoanDAO.findOweMoneya(borrowercontractid,yearmonth);
        }else{
          loandeskaccqueryTcDTO1=restoreLoanDAO.findOweMoneyb(borrowercontractid,yearmonth);
        }
        particularglTaAF.setOwercorpus(loandeskaccqueryTcDTO1.getOwecorpus());
        particularglTaAF.setOweinterest(loandeskaccqueryTcDTO1.getOweinterest());
        particularglTaAF.setOwepunishinterest(loandeskaccqueryTcDTO1.getPunishinterest());
      }else{
        //以银行为主
        particularglTaAF.setOwercorpus(loandeskaccqueryTcDTO.getOwecorpus());
        particularglTaAF.setOweinterest(loandeskaccqueryTcDTO.getOweinterest());
        particularglTaAF.setOwepunishinterest(loandeskaccqueryTcDTO.getPunishinterest());
      }
      bankId = loandeskaccqueryTaDTO.getPaybank();
      }
      /**
       * 下月还款额
       */
      particularglTaAF.setShouldCorputInterest(new BigDecimal(particularglTaAF.getOwercorpus()).add
          (new BigDecimal(particularglTaAF.getOweinterest())).add(new BigDecimal(particularglTaAF.getOwepunishinterest()))
          .add(particularglTaAF.getCorputInterest()));
      //求出list 带分页的
      //      求出本期的 贷款年份 本期贷方 本期利息 本期罚息
       list1=loanFlowHeadDAO.findFirstParticularg(borrowercontractid,bizdateB,bizdateE,orderBy,orderother,start,pageSize,page,securityInfo);
     
      for(int i=0; i<list1.size();i++ ){
        ParticularglDTO particularglDTO=(ParticularglDTO)list1.get(i);
        String ocyear=particularglDTO.getOcyear();
        //下面求期初
       String yearB= bizdateB.substring(0, 4);
       String yearE= bizdateE.substring(0, 4);
        if(ocyear.equals(yearB)){
          yearB=bizdateB;
        }else{
          yearB=ocyear+"0101";
        }
        BigDecimal ocMoney=loanFlowHeadDAO.queryOcMoney(yearB, borrowercontractid);// 累计借方发生额中的 发放金(初期)
        BigDecimal wrongMoney=loanFlowHeadDAO.queryWrongMoney(yearB, borrowercontractid);//累计借方发生额中的 错账金额(初期)
        BigDecimal paymoney= loanFlowHeadDAO.queryPaymoney(yearB, borrowercontractid); //求贷方发生额 初期
     
        particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(paymoney).toString());//期初本金余额 
        //期初结束

        //下面求本期
        String money1=loanFlowHeadDAO.queryThisOcMoney(bizdateB, bizdateE, borrowercontractid,ocyear);//累计借方发生额中的 发放金(本期)
    
        particularglDTO.setBocmoney(money1);
        
        String money2=loanFlowHeadDAO.queryThisWrongOcMoney(bizdateB, bizdateE, borrowercontractid,ocyear);//累计借方发生额中的 发放金错账类型的(本期)
        particularglDTO.setBwocmoney(money2);
         
        particularglDTO.setThisborrower(new BigDecimal(money1).add(new BigDecimal(money2)).toString());//生成本期借方
        String money3=loanFlowHeadDAO.queryThisLoanrepay(bizdateB, bizdateE, borrowercontractid,ocyear);//挂账金额(本期)
        String money4=loanFlowHeadDAO.queryThisWrongLoanrepay(bizdateB, bizdateE, borrowercontractid,ocyear);//挂账金额错账类型(本期)
        particularglDTO.setLocmoney(money3);
        particularglDTO.setLwocmoney(money4);
       
        particularglDTO.setThisloanrepay(new BigDecimal(money3).add(new BigDecimal(money4)).toString());//生成本期的挂账金额
        String money5=loanFlowHeadDAO.queryThisballbalance(bizdateB, bizdateE, borrowercontractid,ocyear);//保证金，本期
        
        particularglDTO.setThisballbalance(money5);//生成保证金  本期 queryThisbadwocmoney
        
        String money6=loanFlowHeadDAO.queryThisbadocmoney(bizdateB, bizdateE, borrowercontractid,ocyear);//呆账核销金额 (本期)
        String money7 =loanFlowHeadDAO.queryThisbadwocmoney(bizdateB, bizdateE, borrowercontractid,ocyear);//呆账核销金额 错账账类型的(本期)
  
        particularglDTO.setBadocmoney(money6);
         
        particularglDTO.setBadwocmoney(money7);
         
        particularglDTO.setThisbaddebtmoney(new BigDecimal(money6).add(new BigDecimal(money7)).toString());//生成呆账核销金额 (本期)
        
        //下面求期末
        particularglDTO.setLastcorpus(new BigDecimal(particularglDTO.getFirstcorpus()).add(new BigDecimal(particularglDTO.getThisborrower())).subtract(new BigDecimal(particularglDTO.getThispaymoney())).toString());
     
        BigDecimal corpus = new BigDecimal(0.00);//loanFlowHeadDAO.queryPl500finalCorpus_(bizdateB, bizdateE, bankId);
        BigDecimal b_firstcorpus = new BigDecimal(0.00);
        BigDecimal b_lastcorpus = new BigDecimal(0.00);
        String firstcorpus = particularglDTO.getFirstcorpus();//期初本金余额
        String lastcorpus = particularglDTO.getLastcorpus();//期末本金余额
        if(firstcorpus!=null && !"".equals(firstcorpus)){
          b_firstcorpus = new BigDecimal(firstcorpus);
          b_firstcorpus = b_firstcorpus.add(corpus);
          particularglDTO.setFirstcorpus(b_firstcorpus.toString());
        }
        if(lastcorpus!=null && !"".equals(lastcorpus)){
          b_lastcorpus = new BigDecimal(lastcorpus);
          b_lastcorpus = b_lastcorpus.add(corpus);
          particularglDTO.setLastcorpus(b_lastcorpus.toString());
        }
        newlist.add(particularglDTO);
      }
      
      //求出不带分页的list
      list2=loanFlowHeadDAO.findFirstParticulargListAll(borrowercontractid,bizdateB,bizdateE,securityInfo);
      
      //总计 本期借方 本期贷方 本期利息 本期罚息 挂账金额 保证金 呆账核销金额 
      BigDecimal tolborrower=new BigDecimal(0.00);
      BigDecimal tolpaymoney=new BigDecimal(0.00);
      BigDecimal tolinterest=new BigDecimal(0.00);
      BigDecimal tolpunishinterest=new BigDecimal(0.00);
      BigDecimal tolloanrepay=new BigDecimal(0.00);
      BigDecimal tolballbalance=new BigDecimal(0.00);
      BigDecimal tolbaddebtmoney =new BigDecimal(0.00);
      
      for(int i=0; i<list2.size();i++ ){
        ParticularglDTO particularglDTO=(ParticularglDTO)list2.get(i);
        String ocyear=particularglDTO.getOcyear();
        //下面求期初
       String yearB= bizdateB.substring(0, 4);
       String yearE= bizdateE.substring(0, 4);
        if(ocyear.equals(yearB)){
          yearB=bizdateB;
        }else{
          yearB=ocyear+"0101";
        }
        BigDecimal ocMoney=loanFlowHeadDAO.queryOcMoney(yearB, borrowercontractid);// 累计借方发生额中的 发放金(初期)
        BigDecimal wrongMoney=loanFlowHeadDAO.queryWrongMoney(yearB, borrowercontractid);//累计借方发生额中的 错账金额(初期)
        BigDecimal paymoney= loanFlowHeadDAO.queryPaymoney(yearB, borrowercontractid); //求贷方发生额 初期
  
        particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(paymoney).toString());//期初本金余额 
        //期初结束

        //下面求本期
        String money1=loanFlowHeadDAO.queryThisOcMoney(bizdateB, bizdateE, borrowercontractid,ocyear);//累计借方发生额中的 发放金(本期)
        particularglDTO.setBocmoney(money1);
        
        String money2=loanFlowHeadDAO.queryThisWrongOcMoney(bizdateB, bizdateE, borrowercontractid,ocyear);//累计借方发生额中的 发放金错账类型的(本期)
        particularglDTO.setBwocmoney(money2);
         
        particularglDTO.setThisborrower(new BigDecimal(money1).add(new BigDecimal(money2)).toString());//生成本期借方
        String money3=loanFlowHeadDAO.queryThisLoanrepay(bizdateB, bizdateE, borrowercontractid,ocyear);//挂账金额(本期)
        String money4=loanFlowHeadDAO.queryThisWrongLoanrepay(bizdateB, bizdateE, borrowercontractid,ocyear);//挂账金额错账类型(本期)
         particularglDTO.setLocmoney(money3);
         particularglDTO.setLwocmoney(money4);
        
        particularglDTO.setThisloanrepay(new BigDecimal(money3).add(new BigDecimal(money4)).toString());//生成本期的挂账金额
        String money5 =loanFlowHeadDAO.queryThisballbalance(bizdateB, bizdateE, borrowercontractid,ocyear);//保证金，本期
        particularglDTO.setThisballbalance(money5);//生成保证金  本期 queryThisbadwocmoney
        String money6=loanFlowHeadDAO.queryThisbadocmoney(bizdateB, bizdateE, borrowercontractid,ocyear);//呆账核销金额 (本期)
        String money7 =loanFlowHeadDAO.queryThisbadwocmoney(bizdateB, bizdateE, borrowercontractid,ocyear);//呆账核销金额 错账账类型的(本期)
      
        particularglDTO.setBadocmoney(money6);
         
        particularglDTO.setBadwocmoney(money7);
         
        particularglDTO.setThisbaddebtmoney(new BigDecimal(money6).add(new BigDecimal(money7)).toString());//生成呆账核销金额 (本期)
        //下面求期末
        particularglDTO.setLastcorpus(new BigDecimal(particularglDTO.getFirstcorpus()).add(new BigDecimal(particularglDTO.getThisborrower())).subtract(new BigDecimal(particularglDTO.getThispaymoney())).toString());
        
        tolborrower=tolborrower.add(new BigDecimal(particularglDTO.getThisborrower()));
        tolpaymoney=tolpaymoney.add(new BigDecimal(particularglDTO.getThispaymoney()));
        tolinterest=tolinterest.add(new BigDecimal(particularglDTO.getThisinterest()));
        tolpunishinterest=tolpunishinterest.add(new BigDecimal(particularglDTO.getThispunishinterest()));
        tolloanrepay=tolloanrepay.add(new BigDecimal(particularglDTO.getThisloanrepay()));
        tolballbalance=tolballbalance.add(new BigDecimal(particularglDTO.getThisballbalance()));
        tolbaddebtmoney=tolbaddebtmoney.add(new BigDecimal(particularglDTO.getThisbaddebtmoney()));
     
        newlist2.add(particularglDTO);
      }
      particularglTaAF.setTolborrower(tolborrower.toString());
      particularglTaAF.setTolpaymoney(tolpaymoney.toString());
      particularglTaAF.setTolinterest(tolinterest.toString());
      particularglTaAF.setTolpunishinterest(tolpunishinterest.toString());
      particularglTaAF.setTolloanrepay(tolloanrepay.toString());
      particularglTaAF.setTolballbalance(tolballbalance.toString());
      particularglTaAF.setTolbaddebtmoney(tolbaddebtmoney.toString());
    }
    pagination.setNrOfElements(newlist2.size());
    particularglTaAF.setList(newlist);
    particularglTaAF.setAlllist(newlist2);
    particularglTaAF.setCount(newlist.size()+"");
    return particularglTaAF;
  }
/**
 * hanl
 * 显示明细账中，发生月的信息
 */
  public ParticularglTaAF showparticularglListTb(Pagination pagination, Pagination paginationta, SecurityInfo securityInfo) throws Exception {
    
    ParticularglTaAF particularglTaAF=new ParticularglTaAF();
    // 查询条件
    String  borrowercontractid= (String) paginationta.getQueryCriterions().get("borrowercontractid");//借款合同编号
    String  bizdateB= (String) paginationta.getQueryCriterions().get("bizdateB");//查询日期起始
    String  bizdateE= (String) paginationta.getQueryCriterions().get("bizdateE");//查询日期截止
    String  ocyear= (String) pagination.getQueryCriterions().get("ocyear");//发生年
    String orderBy = (String) pagination.getOrderBy();
    String orderother = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    
    List list1=new ArrayList();//分页的list1
    List newlist=new ArrayList();//分页list1更改完数据
    
    List list2=new ArrayList();//分页的list
    List newlist2=new ArrayList();//分页list更改完数据
    //下面求借款人信息
    List list= borrowerAccDAO.showLoandeskaccqueryallList(null, null, null, null,
        borrowercontractid, null, null, null, 
        null, null, null, null, 
        null, null, null, null, 
        null, null,securityInfo,null,
        null, null, null, null, null, null, null,null);
    if(list.size()!=0){
      LoandeskaccqueryTaDTO loandeskaccqueryTaDTO =(LoandeskaccqueryTaDTO)list.get(0);
      particularglTaAF.setContractid(loandeskaccqueryTaDTO.getContactid());
      particularglTaAF.setLoankouacc(loandeskaccqueryTaDTO.getLoankouacc());
      particularglTaAF.setBorrowername(loandeskaccqueryTaDTO.getBorrowername());
      particularglTaAF.setCardnum(loandeskaccqueryTaDTO.getCardnum());
      CollBank  collBank=collBankDAO.getCollBankByCollBankid_(loandeskaccqueryTaDTO.getPaybank());
      if(collBank!=null){
        particularglTaAF.setLoanbank(collBank.getCollBankName());
      }
      particularglTaAF.setLoanmoney(loandeskaccqueryTaDTO.getLoanmoney());
      particularglTaAF.setLoanlimit(loandeskaccqueryTaDTO.getLoanlimit());
      particularglTaAF.setNobackmoney(loandeskaccqueryTaDTO.getNobackmoney());
      particularglTaAF.setOveaerloanrepay(loandeskaccqueryTaDTO.getOvareloanrepay());
      particularglTaAF.setBallbalance(loandeskaccqueryTaDTO.getBallbalance());
      particularglTaAF.setSrealcorpus(loandeskaccqueryTaDTO.getRealcorpus());
      particularglTaAF.setSrealinterest(loandeskaccqueryTaDTO.getRealinterest());
      particularglTaAF.setSrealpunishinterest(loandeskaccqueryTaDTO.getRealpunishinterest());
      LoandeskaccqueryTcDTO dtot=new LoandeskaccqueryTcDTO();
      dtot=borrowerAccDAO.queryloanMoneyLoanmood(borrowercontractid);//求还款方式和贷款余额
      LoandeskaccqueryTcDTO loandeskaccqueryTcDTO=new LoandeskaccqueryTcDTO();
      loandeskaccqueryTcDTO=borrowerAccDAO.findborrowerAccInfo(borrowercontractid);
      particularglTaAF.setLoanmode(BusiTools.getBusiValue(Integer.parseInt(dtot.getPayloanmood()), BusiConst.PLRECOVERTYPE));
      particularglTaAF.setOverplusloanmoney(dtot.getLoanleftmoney());
      //结束
      int temp_plLoanReturnTypes = securityInfo.getPlLoanReturnType();
      //根据权限中的还款类型判断以哪为主
      if(temp_plLoanReturnTypes == BusiConst.PLLOANRETURNTYPE_CENTER){
       //中心为主
      
        String payday=loandeskaccqueryTaDTO.getPayday();//还款日
        String plbizdate=securityInfo.getUserInfo().getPlbizDate();
        String yearmonth=plbizdate.substring(0,6);//年月
        String day=plbizdate.substring(6);//日期
        
        int dayint=Integer.parseInt(day);
        int paydayint=Integer.parseInt(payday);
        LoandeskaccqueryTcDTO loandeskaccqueryTcDTO1=new LoandeskaccqueryTcDTO();
        if(dayint<=paydayint){//会计日小于等于还款日
          loandeskaccqueryTcDTO1=restoreLoanDAO.findOweMoneya(borrowercontractid,yearmonth);
        }else{
          loandeskaccqueryTcDTO1=restoreLoanDAO.findOweMoneyb(borrowercontractid,yearmonth);
        }
        particularglTaAF.setOwercorpus(loandeskaccqueryTcDTO1.getOwecorpus());
        particularglTaAF.setOweinterest(loandeskaccqueryTcDTO1.getOweinterest());
        particularglTaAF.setOwepunishinterest(loandeskaccqueryTcDTO1.getPunishinterest());
      }else{
        //以银行为主
        particularglTaAF.setOwercorpus(loandeskaccqueryTcDTO.getOwecorpus());
        particularglTaAF.setOweinterest(loandeskaccqueryTcDTO.getOweinterest());
        particularglTaAF.setOwepunishinterest(loandeskaccqueryTcDTO.getPunishinterest());
      }
    }//结束
    //求出list 带分页的
    //      求出本期的 贷款年份 本期贷方 本期利息 本期罚息
    String bizdateBm="";//起始年月
    String bizdateEm="";//终止年月
    if(ocyear.equals(bizdateB.substring(0,4))){//是起始年
      bizdateBm=bizdateB;
      bizdateEm=ocyear+"1231";
    }else if(ocyear.equals(bizdateE.substring(0,4))){//是终止年
      bizdateBm=ocyear+"0101";
      bizdateEm=bizdateE;
    }else{
      bizdateBm=ocyear+"0101";
      bizdateEm=ocyear+"1231";
    }
    
     list1=loanFlowHeadDAO.findFirstParticulargMonth(borrowercontractid,bizdateBm,bizdateEm,orderBy,orderother,start,pageSize,page,securityInfo);
     for(int i=0; i<list1.size();i++ ){
       ParticularglDTO particularglDTO=(ParticularglDTO)list1.get(i);
       String ocyearMonth=particularglDTO.getOcyear();
       //下面求期初
       String yearB= bizdateBm.substring(0, 6);
       String yearE= bizdateEm.substring(0, 6);
       if(ocyearMonth.equals(yearB)){
         yearB=bizdateBm;
       }else{
         yearB=ocyearMonth+"01";
       }
       BigDecimal ocMoney=loanFlowHeadDAO.queryOcMoneyMonth(yearB, borrowercontractid);// 累计借方发生额中的 发放金(初期)
       BigDecimal wrongMoney=loanFlowHeadDAO.queryWrongMoneyMonth(yearB, borrowercontractid);//累计借方发生额中的 错账金额(初期)
       BigDecimal paymoney= loanFlowHeadDAO.queryPaymoneyMonth(yearB, borrowercontractid); //求贷方发生额 初期
      
       particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(paymoney).toString());//期初本金余额 
       //期初结束

       //下面求本期
       String money1=loanFlowHeadDAO.queryThisOcMoneyMonth(bizdateBm, bizdateEm, borrowercontractid,ocyearMonth);//累计借方发生额中的 发放金(本期)
       String money2=loanFlowHeadDAO.queryThisWrongOcMoneyMonth(bizdateB, bizdateE, borrowercontractid, ocyearMonth);//累计借方发生额中的 发放金错账类型的(本期)
 
       particularglDTO.setThisborrower(new BigDecimal(money1).add(new BigDecimal(money2)).toString());//生成本期借方
       String money3 =loanFlowHeadDAO.queryThisLoanrepayMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//挂账金额(本期)
       String money4 =loanFlowHeadDAO.queryThisWrongLoanrepayMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//挂账金额错账类型(本期)
      
       particularglDTO.setThisloanrepay(new BigDecimal(money3).add(new BigDecimal(money4)).toString());//生成本期的挂账金额
       String money5 =loanFlowHeadDAO.queryThisballbalanceMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//保证金，本期
      
       particularglDTO.setThisballbalance(money5);//生成保证金  本期 queryThisbadwocmoney
       String money6 =loanFlowHeadDAO.queryThisbadocmoneyMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//呆账核销金额 (本期)
       String money7 =loanFlowHeadDAO.queryThisbadwocmoneyMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//呆账核销金额 错账账类型的(本期)
      
       particularglDTO.setThisbaddebtmoney(new BigDecimal(money6).add(new BigDecimal(money7)).toString());//生成呆账核销金额 (本期)
       //下面求期末
       particularglDTO.setLastcorpus(new BigDecimal(particularglDTO.getFirstcorpus()).add(new BigDecimal(particularglDTO.getThisborrower())).subtract(new BigDecimal(particularglDTO.getThispaymoney())).toString());
       
       newlist.add(particularglDTO);
     }
//   求出不带分页的list
     list2=loanFlowHeadDAO.findFirstParticulargListAllMonth(borrowercontractid,bizdateBm,bizdateEm,securityInfo);
     for(int i=0; i<list2.size();i++ ){
       ParticularglDTO particularglDTO=(ParticularglDTO)list2.get(i);
       String ocyearMonth=particularglDTO.getOcyear();
//     下面求期初
       String yearB= bizdateBm.substring(0, 6);
       String yearE= bizdateEm.substring(0, 6);
       if(ocyearMonth.equals(yearB)){
         yearB=bizdateBm;
       }else{
         yearB=ocyearMonth+"01";
       }
       BigDecimal ocMoney=loanFlowHeadDAO.queryOcMoneyMonth(yearB, borrowercontractid);// 累计借方发生额中的 发放金(初期)
       BigDecimal wrongMoney=loanFlowHeadDAO.queryWrongMoneyMonth(yearB, borrowercontractid);//累计借方发生额中的 错账金额(初期)
       BigDecimal paymoney= loanFlowHeadDAO.queryPaymoneyMonth(yearB, borrowercontractid); //求贷方发生额 初期
      
       particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(paymoney).toString());//期初本金余额 
       //期初结束
      
       //下面求本期
       String money1=loanFlowHeadDAO.queryThisOcMoneyMonth(bizdateBm, bizdateEm, borrowercontractid,ocyearMonth);//累计借方发生额中的 发放金(本期)
       String money2=loanFlowHeadDAO.queryThisWrongOcMoneyMonth(bizdateB, bizdateE, borrowercontractid, ocyearMonth);//累计借方发生额中的 发放金错账类型的(本期)
 
       particularglDTO.setThisborrower(new BigDecimal(money1).add(new BigDecimal(money2)).toString());//生成本期借方
       String money3 =loanFlowHeadDAO.queryThisLoanrepayMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//挂账金额(本期)
       String money4 =loanFlowHeadDAO.queryThisWrongLoanrepayMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//挂账金额错账类型(本期)
      
       particularglDTO.setThisloanrepay(new BigDecimal(money3).add(new BigDecimal(money4)).toString());//生成本期的挂账金额
       String money5 =loanFlowHeadDAO.queryThisballbalanceMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//保证金，本期
      
       particularglDTO.setThisballbalance(money5);//生成保证金  本期 queryThisbadwocmoney
       String money6 =loanFlowHeadDAO.queryThisbadocmoneyMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//呆账核销金额 (本期)
       String money7 =loanFlowHeadDAO.queryThisbadwocmoneyMonth(bizdateB, bizdateE, borrowercontractid,ocyearMonth);//呆账核销金额 错账账类型的(本期)
      
       particularglDTO.setThisbaddebtmoney(new BigDecimal(money6).add(new BigDecimal(money7)).toString());//生成呆账核销金额 (本期)
       //下面求期末
       particularglDTO.setLastcorpus(new BigDecimal(particularglDTO.getFirstcorpus()).add(new BigDecimal(particularglDTO.getThisborrower())).subtract(new BigDecimal(particularglDTO.getThispaymoney())).toString());
       
       
       newlist2.add(particularglDTO);
     }
     //总计 本期借方 本期贷方 本期利息 本期罚息 挂账金额 保证金 呆账核销金额 
      BigDecimal tolborrower=new BigDecimal(0.00);
      BigDecimal tolpaymoney=new BigDecimal(0.00);
      BigDecimal tolinterest=new BigDecimal(0.00);
      BigDecimal tolpunishinterest=new BigDecimal(0.00);
      BigDecimal tolloanrepay=new BigDecimal(0.00);
      BigDecimal tolballbalance=new BigDecimal(0.00);
      BigDecimal tolbaddebtmoney =new BigDecimal(0.00);
     for(int m=0;m<newlist2.size();m++){
       ParticularglDTO particularglDTO=(ParticularglDTO)newlist2.get(m);
       tolborrower=tolborrower.add(new BigDecimal(particularglDTO.getThisborrower()));
       tolpaymoney=tolpaymoney.add(new BigDecimal(particularglDTO.getThispaymoney()));
       tolinterest=tolinterest.add(new BigDecimal(particularglDTO.getThisinterest()));
       tolpunishinterest=tolpunishinterest.add(new BigDecimal(particularglDTO.getThispunishinterest()));
       tolloanrepay=tolloanrepay.add(new BigDecimal(particularglDTO.getThisloanrepay()));
       tolballbalance=tolballbalance.add(new BigDecimal(particularglDTO.getThisballbalance()));
       tolbaddebtmoney=tolbaddebtmoney.add(new BigDecimal(particularglDTO.getThisbaddebtmoney()));
     }
     particularglTaAF.setTolborrower(tolborrower.toString());
     particularglTaAF.setTolpaymoney(tolpaymoney.toString());
     particularglTaAF.setTolinterest(tolinterest.toString());
     particularglTaAF.setTolpunishinterest(tolpunishinterest.toString());
     particularglTaAF.setTolloanrepay(tolloanrepay.toString());
     particularglTaAF.setTolballbalance(tolballbalance.toString());
     particularglTaAF.setTolbaddebtmoney(tolbaddebtmoney.toString());
     
     pagination.setNrOfElements(newlist2.size());
     particularglTaAF.setList(newlist);
     particularglTaAF.setAlllist(newlist2);
     particularglTaAF.setCount(newlist.size()+"");
    return particularglTaAF;
  }
  /**
   * hanl
   * 显示明细账中，发生日的信息
   */
  public ParticularglTaAF showparticularglListTc(Pagination pagination, Pagination paginationta, SecurityInfo securityInfo) throws Exception {
  
    ParticularglTaAF particularglTaAF=new ParticularglTaAF();
    // 查询条件
    String  borrowercontractid= (String) paginationta.getQueryCriterions().get("borrowercontractid");//借款合同编号
    String  bizdateB= (String) paginationta.getQueryCriterions().get("bizdateB");//查询日期起始
    String  bizdateE= (String) paginationta.getQueryCriterions().get("bizdateE");//查询日期截止
    String  ocyearMonth= (String) pagination.getQueryCriterions().get("ocyearMonth");//发生年月
    String orderBy = (String) pagination.getOrderBy();
    String orderother = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String loanBankId = "";
    List list1=new ArrayList();//分页的list1
    List newlist=new ArrayList();//分页list1更改完数据
    
    List list2=new ArrayList();//分页的list
    List newlist2=new ArrayList();//分页list更改完数据
    //下面求借款人信息
    List list= borrowerAccDAO.
      showLoandeskaccqueryallList(null, null, null, null,
          borrowercontractid,null, null, null,
          null, null, null, null,
          null, null, null, null, 
          null, null,securityInfo,null,
          null,null,null,null,null,null,null,null);
    if(list.size()!=0){
      LoandeskaccqueryTaDTO loandeskaccqueryTaDTO =(LoandeskaccqueryTaDTO)list.get(0);
      particularglTaAF.setContractid(loandeskaccqueryTaDTO.getContactid());
      particularglTaAF.setLoankouacc(loandeskaccqueryTaDTO.getLoankouacc());
      particularglTaAF.setBorrowername(loandeskaccqueryTaDTO.getBorrowername());
      particularglTaAF.setCardnum(loandeskaccqueryTaDTO.getCardnum());
      CollBank  collBank=collBankDAO.getCollBankByCollBankid_(loandeskaccqueryTaDTO.getPaybank());
      if(collBank!=null){
        particularglTaAF.setLoanbank(collBank.getCollBankName());
      }
      particularglTaAF.setLoanmoney(loandeskaccqueryTaDTO.getLoanmoney());
      particularglTaAF.setLoanlimit(loandeskaccqueryTaDTO.getLoanlimit());
      particularglTaAF.setNobackmoney(loandeskaccqueryTaDTO.getNobackmoney());
      particularglTaAF.setOveaerloanrepay(loandeskaccqueryTaDTO.getOvareloanrepay());
      particularglTaAF.setBallbalance(loandeskaccqueryTaDTO.getBallbalance());
      particularglTaAF.setSrealcorpus(loandeskaccqueryTaDTO.getRealcorpus());
      particularglTaAF.setSrealinterest(loandeskaccqueryTaDTO.getRealinterest());
      particularglTaAF.setSrealpunishinterest(loandeskaccqueryTaDTO.getRealpunishinterest());
      LoandeskaccqueryTcDTO dtot=new LoandeskaccqueryTcDTO();
      dtot=borrowerAccDAO.queryloanMoneyLoanmood(borrowercontractid);//求还款方式和贷款余额
      LoandeskaccqueryTcDTO loandeskaccqueryTcDTO=new LoandeskaccqueryTcDTO();
      loandeskaccqueryTcDTO=borrowerAccDAO.findborrowerAccInfo(borrowercontractid);
      loanBankId = loandeskaccqueryTcDTO.getLoanBankId();
      particularglTaAF.setLoanmode(BusiTools.getBusiValue(Integer.parseInt(dtot.getPayloanmood()), BusiConst.PLRECOVERTYPE));
      particularglTaAF.setOverplusloanmoney(dtot.getLoanleftmoney());
      //结束
      int temp_plLoanReturnTypes = securityInfo.getPlLoanReturnType();
      //根据权限中的还款类型判断以哪为主
      if(temp_plLoanReturnTypes == BusiConst.PLLOANRETURNTYPE_CENTER){
       //中心为主
      
        String payday=loandeskaccqueryTaDTO.getPayday();//还款日
        String plbizdate=securityInfo.getUserInfo().getPlbizDate();
        String yearmonth=plbizdate.substring(0,6);//年月
        String day=plbizdate.substring(6);//日期
        
        int dayint=Integer.parseInt(day);
        int paydayint=Integer.parseInt(payday);
        LoandeskaccqueryTcDTO loandeskaccqueryTcDTO1=new LoandeskaccqueryTcDTO();
        if(dayint<=paydayint){//会计日小于等于还款日
          loandeskaccqueryTcDTO1=restoreLoanDAO.findOweMoneya(borrowercontractid,yearmonth);
        }else{
          loandeskaccqueryTcDTO1=restoreLoanDAO.findOweMoneyb(borrowercontractid,yearmonth);
        }
        particularglTaAF.setOwercorpus(loandeskaccqueryTcDTO1.getOwecorpus());
        particularglTaAF.setOweinterest(loandeskaccqueryTcDTO1.getOweinterest());
        particularglTaAF.setOwepunishinterest(loandeskaccqueryTcDTO1.getPunishinterest());
      }else{
        //以银行为主
        particularglTaAF.setOwercorpus(loandeskaccqueryTcDTO.getOwecorpus());
        particularglTaAF.setOweinterest(loandeskaccqueryTcDTO.getOweinterest());
        particularglTaAF.setOwepunishinterest(loandeskaccqueryTcDTO.getPunishinterest());
      }
    }//结束
    //求出list 带分页的
    //      求出本期的 贷款年份 本期贷方 本期利息 本期罚息
    String bizdateBm="";//起始年月
    String bizdateEm="";//终止年月
    if(ocyearMonth.equals(bizdateB.substring(0,6))){//是起始年月
      bizdateBm=bizdateB;
      bizdateEm=ocyearMonth+"31";
    }else if(ocyearMonth.equals(bizdateE.substring(0,6))){//是终止年月
      bizdateBm=ocyearMonth+"01";
      bizdateEm=bizdateE;
    }else{
      bizdateBm=ocyearMonth+"01";
      bizdateEm=ocyearMonth+"31";
    }
    
     list1=loanFlowHeadDAO.findFirstParticulargDay(borrowercontractid,bizdateBm,bizdateEm,orderBy,orderother,start,pageSize,page,securityInfo);
     for(int i=0; i<list1.size();i++ ){
       ParticularglDTO particularglDTO=(ParticularglDTO)list1.get(i);
       String ocyearMonthDay=particularglDTO.getOcyear();
       //下面求期初
      
       BigDecimal ocMoney=loanFlowHeadDAO.queryOcMoneyDay(ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金(初期)
       BigDecimal wrongMoney=loanFlowHeadDAO.queryWrongMoneyDay(ocyearMonthDay, borrowercontractid);//累计借方发生额中的 错账金额(初期)
       BigDecimal paymoney= loanFlowHeadDAO.queryPaymoneyDay(ocyearMonthDay, borrowercontractid); //求贷方发生额 初期
       BigDecimal corpus = loanFlowHeadDAO.queryPl500Corpus(bizdateBm, null, loanBankId);
       particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(paymoney).add(corpus).toString());//期初本金余额 
       //期初结束

       //下面求本期
       BigDecimal bigoc=loanFlowHeadDAO.queryThisOcMoneyDay(ocyearMonthDay, borrowercontractid);//累计借方发生额中的 发放金(本期) 
       BigDecimal bigwoc=loanFlowHeadDAO.queryThisWrongOcMoneyDay(ocyearMonthDay, borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期) 
       particularglDTO.setThisborrower(bigoc.add(bigwoc).toString());//生成本期借方
       
       BigDecimal biglo =loanFlowHeadDAO.queryThisLoanrepayDay(ocyearMonthDay, borrowercontractid);//挂账金额(本期)
       BigDecimal bigwlo =loanFlowHeadDAO.queryThisWrongLoanrepayDay(ocyearMonthDay, borrowercontractid);//挂账金额错账类型(本期)
       particularglDTO.setThisloanrepay(biglo.add(bigwlo).toString());//生成本期的挂账金额
       
       BigDecimal bigball =loanFlowHeadDAO.queryThisballbalanceDay(ocyearMonthDay, borrowercontractid);//保证金，本期
       particularglDTO.setThisballbalance(bigball.toString());                                          //生成保证金  本期 
       
       BigDecimal bigbad =loanFlowHeadDAO.queryThisbadocmoneyDay(ocyearMonthDay, borrowercontractid);//呆账核销金额 (本期)
       BigDecimal bigbadw =loanFlowHeadDAO.queryThisbadwocmoneyDay(ocyearMonthDay, borrowercontractid);//呆账核销金额 错账账类型的(本期)
      
       particularglDTO.setThisbaddebtmoney(bigbad.add(bigbadw).toString());//生成呆账核销金额 (本期)
       //下面求期末
       BigDecimal lastcorpus = loanFlowHeadDAO.queryPl500finalCorpus(null, bizdateEm, loanBankId);
       particularglDTO.setLastcorpus(new BigDecimal(particularglDTO.getFirstcorpus()).add(new BigDecimal(particularglDTO.getThisborrower())).subtract(new BigDecimal(particularglDTO.getThispaymoney())).add(lastcorpus).toString());

       newlist.add(particularglDTO);
     }
//   求出不带分页的list
    list2=loanFlowHeadDAO.findFirstParticulargListAllDay(borrowercontractid,bizdateBm,bizdateEm,securityInfo);
     for(int i=0; i<list2.size();i++ ){
       ParticularglDTO particularglDTO=(ParticularglDTO)list2.get(i);
       String ocyearMonthDay=particularglDTO.getOcyear();
       //下面求期初
       
       BigDecimal ocMoney=loanFlowHeadDAO.queryOcMoneyDay(ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金(初期)
       BigDecimal wrongMoney=loanFlowHeadDAO.queryWrongMoneyDay(ocyearMonthDay, borrowercontractid);//累计借方发生额中的 错账金额(初期)
       BigDecimal paymoney= loanFlowHeadDAO.queryPaymoneyDay(ocyearMonthDay, borrowercontractid); //求贷方发生额 初期
      
       particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(paymoney).toString());//期初本金余额 
       //期初结束
       
        //下面求本期
        BigDecimal bigoc=loanFlowHeadDAO.queryThisOcMoneyDay(ocyearMonthDay, borrowercontractid);//累计借方发生额中的 发放金(本期) 
        BigDecimal bigwoc=loanFlowHeadDAO.queryThisWrongOcMoneyDay(ocyearMonthDay, borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期) 
        particularglDTO.setThisborrower(bigoc.add(bigwoc).toString());//生成本期借方
        
        BigDecimal biglo =loanFlowHeadDAO.queryThisLoanrepayDay(ocyearMonthDay, borrowercontractid);//挂账金额(本期)
        BigDecimal bigwlo =loanFlowHeadDAO.queryThisWrongLoanrepayDay(ocyearMonthDay, borrowercontractid);//挂账金额错账类型(本期)
        particularglDTO.setThisloanrepay(biglo.add(bigwlo).toString());//生成本期的挂账金额
        
        BigDecimal bigball =loanFlowHeadDAO.queryThisballbalanceDay(ocyearMonthDay, borrowercontractid);//保证金，本期
        particularglDTO.setThisballbalance(bigball.toString());                                          //生成保证金  本期 
        
        BigDecimal bigbad =loanFlowHeadDAO.queryThisbadocmoneyDay(ocyearMonthDay, borrowercontractid);//呆账核销金额 (本期)
        BigDecimal bigbadw =loanFlowHeadDAO.queryThisbadwocmoneyDay(ocyearMonthDay, borrowercontractid);//呆账核销金额 错账账类型的(本期)
       
        particularglDTO.setThisbaddebtmoney(bigbad.add(bigbadw).toString());//生成呆账核销金额 (本期)
        //下面求期末
        particularglDTO.setLastcorpus(new BigDecimal(particularglDTO.getFirstcorpus()).add(new BigDecimal(particularglDTO.getThisborrower())).subtract(new BigDecimal(particularglDTO.getThispaymoney())).toString());

       newlist2.add(particularglDTO);
     }
     
     //总计 本期借方 本期贷方 本期利息 本期罚息 挂账金额 保证金 呆账核销金额 
      BigDecimal tolborrower=new BigDecimal(0.00);
      BigDecimal tolpaymoney=new BigDecimal(0.00);
      BigDecimal tolinterest=new BigDecimal(0.00);
      BigDecimal tolpunishinterest=new BigDecimal(0.00);
      BigDecimal tolloanrepay=new BigDecimal(0.00);
      BigDecimal tolballbalance=new BigDecimal(0.00);
      BigDecimal tolbaddebtmoney =new BigDecimal(0.00);
     for(int m=0;m<newlist2.size();m++){
       ParticularglDTO particularglDTO=(ParticularglDTO)newlist2.get(m);
       tolborrower=tolborrower.add(new BigDecimal(particularglDTO.getThisborrower()));
       tolpaymoney=tolpaymoney.add(new BigDecimal(particularglDTO.getThispaymoney()));
       tolinterest=tolinterest.add(new BigDecimal(particularglDTO.getThisinterest()));
       tolpunishinterest=tolpunishinterest.add(new BigDecimal(particularglDTO.getThispunishinterest()));
       tolloanrepay=tolloanrepay.add(new BigDecimal(particularglDTO.getThisloanrepay()));
       tolballbalance=tolballbalance.add(new BigDecimal(particularglDTO.getThisballbalance()));
       tolbaddebtmoney=tolbaddebtmoney.add(new BigDecimal(particularglDTO.getThisbaddebtmoney()));
     }
     particularglTaAF.setTolborrower(tolborrower.toString());
     particularglTaAF.setTolpaymoney(tolpaymoney.toString());
     particularglTaAF.setTolinterest(tolinterest.toString());
     particularglTaAF.setTolpunishinterest(tolpunishinterest.toString());
     particularglTaAF.setTolloanrepay(tolloanrepay.toString());
     particularglTaAF.setTolballbalance(tolballbalance.toString());
     particularglTaAF.setTolbaddebtmoney(tolbaddebtmoney.toString());
     
     pagination.setNrOfElements(newlist2.size());
     particularglTaAF.setList(newlist);
     particularglTaAF.setAlllist(newlist2);
     particularglTaAF.setCount(newlist.size()+"");
    return particularglTaAF;
  }

  /**
   * hanl 显示明细账中，发生日的信息
   */
  public ParticularglTaAF showparticularglListTd(Pagination pagination,
      Pagination paginationta, SecurityInfo securityInfo) throws Exception {

    ParticularglTaAF particularglTaAF = new ParticularglTaAF();
    // 查询条件
    String borrowercontractid = (String) paginationta.getQueryCriterions().get(
        "borrowercontractid");// 借款合同编号
    String bizdateB = (String) paginationta.getQueryCriterions()
        .get("bizdateB");// 查询日期起始
    String bizdateE = (String) paginationta.getQueryCriterions()
        .get("bizdateE");// 查询日期截止
    String orderBy = (String) pagination.getOrderBy();
    String orderother = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List list1 = new ArrayList();// 分页的list1
    List newlist = new ArrayList();// 分页list1更改完数据
    List list2 = new ArrayList();// 分页的list
    List newlist2 = new ArrayList();// 分页list更改完数据
    // 下面求借款人信息
    List list = borrowerAccDAO.showLoandeskaccqueryallList(null, null, null,
        null, borrowercontractid, null, null, null, null, null, null, null,
        null, null, null, null, null, null, securityInfo);
    if (list.size() != 0) {
      LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = (LoandeskaccqueryTaDTO) list
          .get(0);
      particularglTaAF.setContractid(loandeskaccqueryTaDTO.getContactid());
      particularglTaAF.setBorrowername(loandeskaccqueryTaDTO.getBorrowername());
      particularglTaAF.setCardnum(loandeskaccqueryTaDTO.getCardnum());
    }// 结束
    // 求出list 带分页的
    list1 = loanFlowHeadDAO.findFirstParticulargDay_wsh(borrowercontractid,
        bizdateB, bizdateE, orderBy, orderother, start, pageSize, page,
        securityInfo);
    for (int i = 0; i < list1.size(); i++) {
      ParticularglDTO particularglDTO = (ParticularglDTO) list1.get(i);
      String ocyearMonthDay = particularglDTO.getOcyear();
      // 下面求期初
      BigDecimal ocMoney = loanFlowHeadDAO.queryOcMoneyDay_1(ocyearMonthDay,
          borrowercontractid);// 累计借方发生额中的 发放金(初期)
      BigDecimal wrongMoney = loanFlowHeadDAO.queryWrongMoneyDay_1(
          ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 错账金额(初期)
      BigDecimal paymoney = loanFlowHeadDAO.queryPaymoneyDay_1(ocyearMonthDay,
          borrowercontractid); // 求贷方发生额 初期
      particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(
          paymoney).toString());// 期初本金余额
      // 挂账
      BigDecimal biglo_1 = loanFlowHeadDAO.queryThisLoanrepayDay_2(
          ocyearMonthDay, borrowercontractid);// 挂账金额(本期)
      BigDecimal bigwlo_1 = loanFlowHeadDAO.queryThisWrongLoanrepayDay_2(
          ocyearMonthDay, borrowercontractid);// 挂账金额错账类型(本期)
      if (biglo_1.add(bigwlo_1).compareTo(new BigDecimal(0.00)) > 0) {
        particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
            .getFirstcorpus()).subtract(biglo_1.add(bigwlo_1)).toString());
      } else {
        particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
            .getFirstcorpus()).add(biglo_1.add(bigwlo_1)).toString());
      }
      // 挂账
      BigDecimal bigball_1 = loanFlowHeadDAO.queryThisballbalanceDay_2(
          ocyearMonthDay, borrowercontractid);// 保证金，本期
      // 保证金
      if (bigball_1.compareTo(new BigDecimal(0.00)) > 0) {
        particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
            .getFirstcorpus()).subtract(bigball_1).toString());
      } else {
        particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
            .getFirstcorpus()).add(bigball_1).toString());
      }
      // 保证金
      BigDecimal bigbad_1 = loanFlowHeadDAO.queryThisbadocmoneyDay_2(
          ocyearMonthDay, borrowercontractid);// 呆账核销金额 (本期)
      BigDecimal bigbadw_1 = loanFlowHeadDAO.queryThisbadwocmoneyDay_2(
          ocyearMonthDay, borrowercontractid);// 呆账核销金额 错账账类型的(本期)
      // 呆账核销金额
      if (bigbad_1.add(bigbadw_1).compareTo(new BigDecimal(0.00)) > 0) {
        particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
            .getFirstcorpus()).subtract(bigbad_1.add(bigbadw_1)).toString());
      } else {
        particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
            .getFirstcorpus()).add(bigbad_1.add(bigbadw_1)).toString());
      }
      // 呆账核销金额
      // 期初结束
      // 下面求本期
      BigDecimal bigoc = loanFlowHeadDAO.queryThisOcMoneyDay_1(ocyearMonthDay,
          borrowercontractid);// 累计借方发生额中的 发放金(本期)
      BigDecimal bigwoc = loanFlowHeadDAO.queryThisWrongOcMoneyDay(
          ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金错账类型的(本期)
      particularglDTO.setThisborrower(bigoc.add(bigwoc).toString());// 生成本期借方
      String doc_BizType[] = new String[2];
      doc_BizType = loanFlowHeadDAO.queryThisOcMoneyDay_DOc_NumAnd_BizType(
          ocyearMonthDay, borrowercontractid);
      if (doc_BizType[0] != null && !"".equals(doc_BizType[0])) {
        particularglDTO.setDocNum(doc_BizType[0]);
      }
      if (doc_BizType[1] != null && !"".equals(doc_BizType[1])) {
        particularglDTO.setBizType(BusiTools.getBusiValue(new Integer(
            doc_BizType[1]).intValue(), BusiConst.PLBUSINESSTYPE));
      }
      String a=doc_BizType[2];
      String caiwtype=loanFlowHeadDAO.queryCaiwType(a);
      if(caiwtype.equals("2")){
        particularglDTO.setType("");//正常
      }else{
        particularglDTO.setType("0");//变成红色
      }
      // 挂账
      BigDecimal biglo = loanFlowHeadDAO.queryThisLoanrepayDay_1(
          ocyearMonthDay, borrowercontractid);// 挂账金额(本期)
      BigDecimal bigwlo = loanFlowHeadDAO.queryThisWrongLoanrepayDay_1(
          ocyearMonthDay, borrowercontractid);// 挂账金额错账类型(本期)
      if (biglo.add(bigwlo).compareTo(new BigDecimal(0.00)) > 0) {
        particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
            .getThispaymoney()).add(biglo.add(bigwlo)).toString()));
      } else {
        particularglDTO.setThisborrower((new BigDecimal(particularglDTO
            .getThisborrower()).add(biglo.add(bigwlo)).toString()));
      }
      // 挂账
      particularglDTO.setThisloanrepay(biglo.add(bigwlo).toString());// 生成本期的挂账金额
      BigDecimal bigball = loanFlowHeadDAO.queryThisballbalanceDay_1(
          ocyearMonthDay, borrowercontractid);// 保证金，本期
      // 保证金
      if (bigball.compareTo(new BigDecimal(0.00)) > 0) {
        particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
            .getThispaymoney()).add(bigball).toString()));
      } else {
        particularglDTO.setThisborrower((new BigDecimal(particularglDTO
            .getThisborrower()).add(bigball).toString()));
      }
      // 保证金
      particularglDTO.setThisballbalance(bigball.toString()); // 生成保证金 本期
      BigDecimal bigbad = loanFlowHeadDAO.queryThisbadocmoneyDay_1(
          ocyearMonthDay, borrowercontractid);// 呆账核销金额 (本期)
      BigDecimal bigbadw = loanFlowHeadDAO.queryThisbadwocmoneyDay_1(
          ocyearMonthDay, borrowercontractid);// 呆账核销金额 错账账类型的(本期)
      // 呆账核销金额
      if (bigbad.add(bigbadw).compareTo(new BigDecimal(0.00)) > 0) {
        particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
            .getThispaymoney()).add(bigbad.add(bigbadw)).toString()));
      } else {
        particularglDTO.setThisborrower((new BigDecimal(particularglDTO
            .getThisborrower()).add(bigbad.add(bigbadw)).toString()));
      }
      // 呆账核销金额
      particularglDTO.setThisbaddebtmoney(bigbad.add(bigbadw).toString());// 生成呆账核销金额
                                                                          // (本期)
      // 下面求期末
      particularglDTO.setLastcorpus(new BigDecimal(particularglDTO
          .getFirstcorpus()).add(
          new BigDecimal(particularglDTO.getThisborrower())).subtract(
          new BigDecimal(particularglDTO.getThispaymoney())).abs().toString());
      particularglDTO.setDerection(this.getDirtection(new BigDecimal(
          particularglDTO.getFirstcorpus()).add(
          new BigDecimal(particularglDTO.getThisborrower())).subtract(
          new BigDecimal(particularglDTO.getThispaymoney()))));
      newlist.add(particularglDTO);
    }
    // 求出不带分页的list
    list2 = loanFlowHeadDAO.findFirstParticulargListAllDay_wsh(
        borrowercontractid, bizdateB, bizdateE, securityInfo);
    for (int i = 0; i < list2.size(); i++) {
      ParticularglDTO particularglDTO = (ParticularglDTO) list2.get(i);
      newlist2.add(particularglDTO);
    }
    pagination.setNrOfElements(newlist2.size());
    particularglTaAF.setList(newlist);
    particularglTaAF.setAlllist(newlist2);
    particularglTaAF.setCount(newlist.size() + "");
    particularglTaAF.setBizdateB(bizdateB);
    particularglTaAF.setBizdateE(bizdateE);
    return particularglTaAF;
  }

  /**
   * hanl 显示明细账中，发生日的信息
   */
  public ParticularglTaAF showparticularglListTe(Pagination pagination,
      Pagination paginationta, SecurityInfo securityInfo) throws Exception {
    ParticularglTaAF particularglTaAF = new ParticularglTaAF();
    // 查询条件
    String borrowercontractid = (String) paginationta.getQueryCriterions().get(
        "borrowercontractid");// 借款合同编号
    String bizdateB = (String) paginationta.getQueryCriterions()
        .get("bizdateB");// 查询日期起始
    String bizdateE = (String) paginationta.getQueryCriterions()
        .get("bizdateE");// 查询日期截止
    String time = "";
    List contractList = (List) paginationta.getQueryCriterions().get(
        "contracIds");// 查询日期截止
    Object obj[] = new Object[7];
    List newlist = new ArrayList();// 分页list1更改完数据
    List list2 = new ArrayList();// 分页的list
    List newlist2 = new ArrayList();// 分页list更改完数据
    if (contractList != null && contractList.size() > 1) {
      for (int i = 0; i < contractList.size(); i++) {
        obj = (Object[]) contractList.get(i);
        borrowercontractid = obj[0].toString();
        // 求出不带分页的list
        list2 = loanFlowHeadDAO.findFirstParticulargListAllDay_wsh(
            borrowercontractid, bizdateB, bizdateE, securityInfo);
        if (list2.size() > 0) {
          for (int j = 0; j < list2.size(); j++) {
            ParticularglDTO particularglDTO = (ParticularglDTO) list2.get(j);
            String ocyearMonthDay = particularglDTO.getOcyear();
            particularglDTO.setContractId(borrowercontractid);
            // 下面求期初
            BigDecimal ocMoney = loanFlowHeadDAO.queryOcMoneyDay_1(
                ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金(初期)
            BigDecimal wrongMoney = loanFlowHeadDAO.queryWrongMoneyDay_1(
                ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 错账金额(初期)
            BigDecimal paymoney = loanFlowHeadDAO.queryPaymoneyDay_1(
                ocyearMonthDay, borrowercontractid); // 求贷方发生额 初期
            particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(
                paymoney).toString());// 期初本金余额
            // 期初结束
            // 挂账
            BigDecimal biglo_1 = loanFlowHeadDAO.queryThisLoanrepayDay_2(
                ocyearMonthDay, borrowercontractid);// 挂账金额(本期)
            BigDecimal bigwlo_1 = loanFlowHeadDAO.queryThisWrongLoanrepayDay_2(
                ocyearMonthDay, borrowercontractid);// 挂账金额错账类型(本期)
            if (biglo_1.add(bigwlo_1).compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO
                  .setFirstcorpus(new BigDecimal(particularglDTO
                      .getFirstcorpus()).subtract(biglo_1.add(bigwlo_1))
                      .toString());
            } else {
              particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                  .getFirstcorpus()).add(biglo_1.add(bigwlo_1)).toString());
            }
            // 挂账
            BigDecimal bigball_1 = loanFlowHeadDAO.queryThisballbalanceDay_2(
                ocyearMonthDay, borrowercontractid);// 保证金，本期
            // 保证金
            if (bigball_1.compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                  .getFirstcorpus()).subtract(bigball_1).toString());
            } else {
              particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                  .getFirstcorpus()).add(bigball_1).toString());
            }
            // 保证金
            BigDecimal bigbad_1 = loanFlowHeadDAO.queryThisbadocmoneyDay_2(
                ocyearMonthDay, borrowercontractid);// 呆账核销金额 (本期)
            BigDecimal bigbadw_1 = loanFlowHeadDAO.queryThisbadwocmoneyDay_2(
                ocyearMonthDay, borrowercontractid);// 呆账核销金额 错账账类型的(本期)
            // 呆账核销金额
            if (bigbad_1.add(bigbadw_1).compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                  .getFirstcorpus()).subtract(bigbad_1.add(bigbadw_1))
                  .toString());
            } else {
              particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                  .getFirstcorpus()).add(bigbad_1.add(bigbadw_1)).toString());
            }
            // 呆账核销金额
            // 下面求本期
            BigDecimal bigoc = loanFlowHeadDAO.queryThisOcMoneyDay_1(
                ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金(本期)
            BigDecimal bigwoc = loanFlowHeadDAO.queryThisWrongOcMoneyDay(
                ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金错账类型的(本期)
            particularglDTO.setThisborrower(bigoc.add(bigwoc).toString());// 生成本期借方
            String doc_BizType[] = new String[2];
            doc_BizType = loanFlowHeadDAO
                .queryThisOcMoneyDay_DOc_NumAnd_BizType(ocyearMonthDay,
                    borrowercontractid);
            if (doc_BizType[0] != null && !"".equals(doc_BizType[0])) {
              particularglDTO.setDocNum(doc_BizType[0]);
            }
            if (doc_BizType[1] != null && !"".equals(doc_BizType[1])) {
              particularglDTO.setBizType(BusiTools.getBusiValue(new Integer(
                  doc_BizType[1]).intValue(), BusiConst.PLBUSINESSTYPE));
            }
            // 挂账
            BigDecimal biglo = loanFlowHeadDAO.queryThisLoanrepayDay_1(
                ocyearMonthDay, borrowercontractid);// 挂账金额(本期)
            BigDecimal bigwlo = loanFlowHeadDAO.queryThisWrongLoanrepayDay_1(
                ocyearMonthDay, borrowercontractid);// 挂账金额错账类型(本期)
            if (biglo.add(bigwlo).compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
                  .getThispaymoney()).add(biglo.add(bigwlo)).toString()));
            } else {
              particularglDTO.setThisborrower((new BigDecimal(particularglDTO
                  .getThisborrower()).add(biglo.add(bigwlo)).toString()));
            }
            // 挂账
            particularglDTO.setThisloanrepay(biglo.add(bigwlo).toString());// 生成本期的挂账金额
            BigDecimal bigball = loanFlowHeadDAO.queryThisballbalanceDay_1(
                ocyearMonthDay, borrowercontractid);// 保证金，本期
            // 保证金
            if (bigball.compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
                  .getThispaymoney()).add(bigball).toString()));
            } else {
              particularglDTO.setThisborrower((new BigDecimal(particularglDTO
                  .getThisborrower()).add(bigball).toString()));
            }
            // 保证金
            particularglDTO.setThisballbalance(bigball.toString()); // 生成保证金 本期
            BigDecimal bigbad = loanFlowHeadDAO.queryThisbadocmoneyDay_1(
                ocyearMonthDay, borrowercontractid);// 呆账核销金额 (本期)
            BigDecimal bigbadw = loanFlowHeadDAO.queryThisbadwocmoneyDay_1(
                ocyearMonthDay, borrowercontractid);// 呆账核销金额 错账账类型的(本期)
            // 呆账核销金额
            if (bigbad.add(bigbadw).compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
                  .getThispaymoney()).add(bigbad.add(bigbadw)).toString()));
            } else {
              particularglDTO.setThisborrower((new BigDecimal(particularglDTO
                  .getThisborrower()).add(bigbad.add(bigbadw)).toString()));
            }
            // 呆账核销金额
            particularglDTO.setThisbaddebtmoney(bigbad.add(bigbadw).toString());// 生成呆账核销金额
                                                                                // (本期)
            // 下面求期末
            particularglDTO.setDerection(this.getDirtection(new BigDecimal(
                particularglDTO.getFirstcorpus()).add(
                new BigDecimal(particularglDTO.getThisborrower())).subtract(
                new BigDecimal(particularglDTO.getThispaymoney()))));
            particularglDTO.setLastcorpus((new BigDecimal(particularglDTO
                .getFirstcorpus()).add(new BigDecimal(particularglDTO
                .getThisborrower())).subtract(new BigDecimal(particularglDTO
                .getThispaymoney()))).abs().toString());
            if (j == 0) {
              ParticularglDTO particularglDTO_qichu = new ParticularglDTO();
              particularglDTO_qichu.setOcyear(particularglDTO.getOcyear());
              particularglDTO_qichu.setDocNum("");
              // BigDecimal
              // bigoc1=loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu(bizdateB,
              // borrowercontractid);//累计借方发生额中的 发放金(本期)
              // BigDecimal
              // bigwoc1=loanFlowHeadDAO.queryWrongMoneyDay_wsh_qichu(bizdateB,
              // borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期)
              // BigDecimal paymoney1=
              // loanFlowHeadDAO.queryPaymoneyDay_qichu_wsh(bizdateB,
              // borrowercontractid); //求贷方发生额 初期
              // BigDecimal interest=
              // loanFlowHeadDAO.queryPaymoneyDay_qichuInterest_wsh(bizdateB,
              // borrowercontractid); //求贷方发生额 初期
              // particularglDTO_qichu.setThisborrower(bigoc1.add(bigwoc1).toString());//生成本期借方
              // particularglDTO_qichu.setThispaymoney(paymoney1.toString());
              // particularglDTO_qichu.setThisinterest(interest.toString());
              particularglDTO_qichu.setThisborrower("0.00");// 生成本期借方
              particularglDTO_qichu.setThispaymoney("0.00");
              particularglDTO_qichu.setThisinterest("0.00");
              particularglDTO_qichu.setThispunishinterest("0.00");
              if (new BigDecimal(particularglDTO.getFirstcorpus())
                  .compareTo(new BigDecimal(0.00)) > 0) {
                particularglDTO_qichu.setDerection("借");
              }
              if (new BigDecimal(particularglDTO.getFirstcorpus())
                  .compareTo(new BigDecimal(0.00)) < 0) {
                particularglDTO_qichu.setDerection("贷");
              }
              if (new BigDecimal(particularglDTO.getFirstcorpus())
                  .compareTo(new BigDecimal(0.00)) == 0) {
                particularglDTO_qichu.setDerection("平");
              }
              particularglDTO_qichu.setLastcorpus(new BigDecimal(
                  particularglDTO.getFirstcorpus()).abs().toString());
              particularglDTO_qichu.setContractId(borrowercontractid);
              if (particularglDTO_qichu.getOcyear().substring(4, 8).equals(
                  "0101")) {
                particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                    BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
              } else {
                particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                    BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
              }
              newlist2.add(particularglDTO_qichu);
            }
            if (j == 0) {
              time = ocyearMonthDay;
            }
            if (!time.substring(0, 4).equals(ocyearMonthDay.substring(0, 4))) {// 不为这个科目的话,创建新的页面
              time = ocyearMonthDay;
              ParticularglDTO particularglDTO_qichu = new ParticularglDTO();
              particularglDTO_qichu.setOcyear(particularglDTO.getOcyear());
              particularglDTO_qichu.setDocNum("");
              // BigDecimal
              // bigoc1=loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu(bizdateB,
              // borrowercontractid);//累计借方发生额中的 发放金(本期)
              // BigDecimal
              // bigwoc1=loanFlowHeadDAO.queryWrongMoneyDay_wsh_qichu(bizdateB,
              // borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期)
              // BigDecimal paymoney1=
              // loanFlowHeadDAO.queryPaymoneyDay_qichu_wsh(bizdateB,
              // borrowercontractid); //求贷方发生额 初期
              // BigDecimal interest=
              // loanFlowHeadDAO.queryPaymoneyDay_qichuInterest_wsh(bizdateB,
              // borrowercontractid); //求贷方发生额 初期
              // particularglDTO_qichu.setThisborrower(bigoc1.add(bigwoc1).toString());//生成本期借方
              // particularglDTO_qichu.setThispaymoney(paymoney1.toString());
              // particularglDTO_qichu.setThisinterest(interest.toString());
              particularglDTO_qichu.setThisborrower("0.00");// 生成本期借方
              particularglDTO_qichu.setThispaymoney("0.00");
              particularglDTO_qichu.setThisinterest("0.00");
              particularglDTO_qichu.setThispunishinterest("0.00");
              if (new BigDecimal(particularglDTO.getFirstcorpus())
                  .compareTo(new BigDecimal(0.00)) > 0) {
                particularglDTO_qichu.setDerection("借");
              }
              if (new BigDecimal(particularglDTO.getFirstcorpus())
                  .compareTo(new BigDecimal(0.00)) < 0) {
                particularglDTO_qichu.setDerection("贷");
              }
              if (new BigDecimal(particularglDTO.getFirstcorpus())
                  .compareTo(new BigDecimal(0.00)) == 0) {
                particularglDTO_qichu.setDerection("平");
              }
              particularglDTO_qichu.setLastcorpus(new BigDecimal(
                  particularglDTO.getFirstcorpus()).abs().toString());
              particularglDTO_qichu.setContractId(borrowercontractid);

              if (particularglDTO_qichu.getOcyear().substring(4, 8).equals(
                  "0101")) {
                particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                    BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
              } else {
                particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                    BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
              }
              newlist2.add(particularglDTO_qichu);
            }
            newlist2.add(particularglDTO);
          }
        } else {
          // //每条合同求期初的
          ParticularglDTO particularglDTO2 = new ParticularglDTO();
          particularglDTO2.setOcyear(bizdateB);
          ParticularglDTO particularglDTO_qichu = new ParticularglDTO();
          particularglDTO_qichu.setOcyear(bizdateB);
          particularglDTO_qichu.setDocNum("");
          // BigDecimal
          // bigoc1=loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu(bizdateB,
          // borrowercontractid);//累计借方发生额中的 发放金(本期)
          // BigDecimal
          // bigwoc1=loanFlowHeadDAO.queryWrongMoneyDay_wsh_qichu(bizdateB,
          // borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期)
          // BigDecimal paymoney1=
          // loanFlowHeadDAO.queryPaymoneyDay_qichu_wsh(bizdateB,
          // borrowercontractid); //求贷方发生额 初期
          // BigDecimal interest=
          // loanFlowHeadDAO.queryPaymoneyDay_qichuInterest_wsh(bizdateB,
          // borrowercontractid); //求贷方发生额 初期
          // particularglDTO_qichu.setThisborrower(bigoc1.add(bigwoc1).toString());//生成本期借方
          // particularglDTO_qichu.setThispaymoney(paymoney1.toString());
          // particularglDTO_qichu.setThisinterest(interest.toString());
          particularglDTO_qichu.setThisborrower("0.00");// 生成本期借方
          particularglDTO_qichu.setThispaymoney("0.00");
          particularglDTO_qichu.setThisinterest("0.00");
          particularglDTO_qichu.setThispunishinterest("0.00");
          BigDecimal ocMoney = loanFlowHeadDAO.queryOcMoneyDay(bizdateB,
              borrowercontractid);// 累计借方发生额中的 发放金(初期)
          BigDecimal wrongMoney = loanFlowHeadDAO.queryWrongMoneyDay(bizdateB,
              borrowercontractid);// 累计借方发生额中的 错账金额(初期)
          BigDecimal paymoney = loanFlowHeadDAO.queryPaymoneyDay(bizdateB,
              borrowercontractid); // 求贷方发生额 初期
          if (ocMoney.add(wrongMoney).subtract(paymoney).compareTo(
              new BigDecimal(0.00)) > 0) {
            particularglDTO_qichu.setDerection("借");
          }
          if (ocMoney.add(wrongMoney).subtract(paymoney).compareTo(
              new BigDecimal(0.00)) < 0) {
            particularglDTO_qichu.setDerection("贷");
          }
          if (ocMoney.add(wrongMoney).subtract(paymoney).compareTo(
              new BigDecimal(0.00)) == 0) {
            particularglDTO_qichu.setDerection("平");
          }
          particularglDTO_qichu.setLastcorpus((ocMoney.add(wrongMoney))
              .subtract(paymoney).abs().toString());// 期初本金余额
          particularglDTO_qichu.setContractId(borrowercontractid);
          if (particularglDTO_qichu.getOcyear().substring(4, 8).equals("0101")) {
            particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
          } else {
            particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
          }
          particularglDTO2.setBizType(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
          particularglDTO2.setContractId(borrowercontractid);
          newlist2.add(particularglDTO_qichu);
        }
      }
    } else {
      // 下面求借款人信息
      List list = borrowerAccDAO.showLoandeskaccqueryallList(null, null, null,
          null, borrowercontractid, null, null, null, null, null, null, null,
          null, null, null, null, null, null, securityInfo);
      if (list.size() != 0) {
        LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = (LoandeskaccqueryTaDTO) list
            .get(0);
        particularglTaAF.setContractid(loandeskaccqueryTaDTO.getContactid());
        particularglTaAF.setLoankouacc(loandeskaccqueryTaDTO.getLoankouacc());
        particularglTaAF.setBorrowername(loandeskaccqueryTaDTO
            .getBorrowername());
        particularglTaAF.setCardnum(loandeskaccqueryTaDTO.getCardnum());
      }// 结束
      // 求出list 带分页的
      // 求出不带分页的list
      list2 = loanFlowHeadDAO.findFirstParticulargListAllDay_wsh(
          borrowercontractid, bizdateB, bizdateE, securityInfo);
      if (list2.size() > 0) {
        for (int j = 0; j < list2.size(); j++) {
          ParticularglDTO particularglDTO = (ParticularglDTO) list2.get(j);
          String ocyearMonthDay = particularglDTO.getOcyear();
          particularglDTO.setContractId(borrowercontractid);
          // 下面求期初
          BigDecimal ocMoney = loanFlowHeadDAO.queryOcMoneyDay_1(
              ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金(初期)
          BigDecimal wrongMoney = loanFlowHeadDAO.queryWrongMoneyDay_1(
              ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 错账金额(初期)
          BigDecimal paymoney = loanFlowHeadDAO.queryPaymoneyDay_1(
              ocyearMonthDay, borrowercontractid); // 求贷方发生额 初期
          particularglDTO.setFirstcorpus((ocMoney.add(wrongMoney)).subtract(
              paymoney).toString());// 期初本金余额
          // 挂账
          BigDecimal biglo_1 = loanFlowHeadDAO.queryThisLoanrepayDay_2(
              ocyearMonthDay, borrowercontractid);// 挂账金额(本期)
          BigDecimal bigwlo_1 = loanFlowHeadDAO.queryThisWrongLoanrepayDay_2(
              ocyearMonthDay, borrowercontractid);// 挂账金额错账类型(本期)
          if (biglo_1.add(bigwlo_1).compareTo(new BigDecimal(0.00)) > 0) {
            particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                .getFirstcorpus()).subtract(biglo_1.add(bigwlo_1)).toString());
          } else {
            particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                .getFirstcorpus()).add(biglo_1.add(bigwlo_1)).toString());
          }
          // 挂账
          BigDecimal bigball_1 = loanFlowHeadDAO.queryThisballbalanceDay_2(
              ocyearMonthDay, borrowercontractid);// 保证金，本期
          // 保证金
          if (bigball_1.compareTo(new BigDecimal(0.00)) > 0) {
            particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                .getFirstcorpus()).subtract(bigball_1).toString());
          } else {
            particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                .getFirstcorpus()).add(bigball_1).toString());
          }
          // 保证金
          BigDecimal bigbad_1 = loanFlowHeadDAO.queryThisbadocmoneyDay_2(
              ocyearMonthDay, borrowercontractid);// 呆账核销金额 (本期)
          BigDecimal bigbadw_1 = loanFlowHeadDAO.queryThisbadwocmoneyDay_2(
              ocyearMonthDay, borrowercontractid);// 呆账核销金额 错账账类型的(本期)
          // 呆账核销金额
          if (bigbad_1.add(bigbadw_1).compareTo(new BigDecimal(0.00)) > 0) {
            particularglDTO
                .setFirstcorpus(new BigDecimal(particularglDTO.getFirstcorpus())
                    .subtract(bigbad_1.add(bigbadw_1)).toString());
          } else {
            particularglDTO.setFirstcorpus(new BigDecimal(particularglDTO
                .getFirstcorpus()).add(bigbad_1.add(bigbadw_1)).toString());
          }
          // 呆账核销金额
          // 期初结束
          // 下面求本期
          BigDecimal bigoc = loanFlowHeadDAO.queryThisOcMoneyDay_1(
              ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金(本期)
          BigDecimal bigwoc = loanFlowHeadDAO.queryThisWrongOcMoneyDay(
              ocyearMonthDay, borrowercontractid);// 累计借方发生额中的 发放金错账类型的(本期)
          particularglDTO.setThisborrower(bigoc.add(bigwoc).toString());// 生成本期借方
          String doc_BizType[] = new String[2];
          doc_BizType = loanFlowHeadDAO.queryThisOcMoneyDay_DOc_NumAnd_BizType(
              ocyearMonthDay, borrowercontractid);
          if (doc_BizType[0] != null && !"".equals(doc_BizType[0])) {
            particularglDTO.setDocNum(doc_BizType[0]);
          }
          if (doc_BizType[1] != null && !"".equals(doc_BizType[1])) {
            particularglDTO.setBizType(BusiTools.getBusiValue(new Integer(
                doc_BizType[1]).intValue(), BusiConst.PLBUSINESSTYPE));
          }
          // 挂账
          BigDecimal biglo = loanFlowHeadDAO.queryThisLoanrepayDay_1(
              ocyearMonthDay, borrowercontractid);// 挂账金额(本期)
          BigDecimal bigwlo = loanFlowHeadDAO.queryThisWrongLoanrepayDay_1(
              ocyearMonthDay, borrowercontractid);// 挂账金额错账类型(本期)
          if (biglo.add(bigwlo).compareTo(new BigDecimal(0.00)) > 0) {
            particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
                .getThispaymoney()).add(biglo.add(bigwlo)).toString()));
          } else {
            particularglDTO.setThisborrower((new BigDecimal(particularglDTO
                .getThisborrower()).add(biglo.add(bigwlo)).toString()));
          }
          // 挂账
          particularglDTO.setThisloanrepay(biglo.add(bigwlo).toString());// 生成本期的挂账金额
          BigDecimal bigball = loanFlowHeadDAO.queryThisballbalanceDay_1(
              ocyearMonthDay, borrowercontractid);// 保证金，本期
          // 保证金
          if (bigball.compareTo(new BigDecimal(0.00)) > 0) {
            particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
                .getThispaymoney()).add(bigball).toString()));
          } else {
            particularglDTO.setThisborrower((new BigDecimal(particularglDTO
                .getThisborrower()).add(bigball).toString()));
          }
          // 保证金
          particularglDTO.setThisballbalance(bigball.toString()); // 生成保证金 本期
          BigDecimal bigbad = loanFlowHeadDAO.queryThisbadocmoneyDay_1(
              ocyearMonthDay, borrowercontractid);// 呆账核销金额 (本期)
          BigDecimal bigbadw = loanFlowHeadDAO.queryThisbadwocmoneyDay_1(
              ocyearMonthDay, borrowercontractid);// 呆账核销金额 错账账类型的(本期)
          // 呆账核销金额
          if (bigbad.add(bigbadw).compareTo(new BigDecimal(0.00)) > 0) {
            particularglDTO.setThispaymoney((new BigDecimal(particularglDTO
                .getThispaymoney()).add(bigbad.add(bigbadw)).toString()));
          } else {
            particularglDTO.setThisborrower((new BigDecimal(particularglDTO
                .getThisborrower()).add(bigbad.add(bigbadw)).toString()));
          }
          // 呆账核销金额
          particularglDTO.setDerection(this.getDirtection(new BigDecimal(
              particularglDTO.getFirstcorpus()).add(
              new BigDecimal(particularglDTO.getThisborrower())).subtract(
              new BigDecimal(particularglDTO.getThispaymoney()))));
          particularglDTO.setThisbaddebtmoney(bigbad.add(bigbadw).toString());// 生成呆账核销金额
                                                                              // (本期)
          // 下面求期末
          particularglDTO.setLastcorpus(new BigDecimal(particularglDTO
              .getFirstcorpus()).add(
              new BigDecimal(particularglDTO.getThisborrower())).subtract(
              new BigDecimal(particularglDTO.getThispaymoney())).abs()
              .toString());
          if (j == 0) {
            ParticularglDTO particularglDTO_qichu = new ParticularglDTO();
            particularglDTO_qichu.setOcyear(particularglDTO.getOcyear());
            particularglDTO_qichu.setDocNum("");
            // BigDecimal
            // bigoc1=loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu(bizdateB,
            // borrowercontractid);//累计借方发生额中的 发放金(本期)
            // BigDecimal
            // bigwoc1=loanFlowHeadDAO.queryWrongMoneyDay_wsh_qichu(bizdateB,
            // borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期)
            // BigDecimal paymoney1=
            // loanFlowHeadDAO.queryPaymoneyDay_qichu_wsh(bizdateB,
            // borrowercontractid); //求贷方发生额 初期
            // BigDecimal interest=
            // loanFlowHeadDAO.queryPaymoneyDay_qichuInterest_wsh(bizdateB,
            // borrowercontractid); //求贷方发生额 初期
            // particularglDTO_qichu.setThisborrower(bigoc1.add(bigwoc1).toString());//生成本期借方
            // particularglDTO_qichu.setThispaymoney(paymoney1.toString());
            // particularglDTO_qichu.setThisinterest(interest.toString());
            particularglDTO_qichu.setThisborrower("0.00");// 生成本期借方
            particularglDTO_qichu.setThispaymoney("0.00");
            particularglDTO_qichu.setThisinterest("0.00");
            particularglDTO_qichu.setThispunishinterest("0.00");
            if (new BigDecimal(particularglDTO.getFirstcorpus())
                .compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO_qichu.setDerection("借");
            }
            if (new BigDecimal(particularglDTO.getFirstcorpus())
                .compareTo(new BigDecimal(0.00)) < 0) {
              particularglDTO_qichu.setDerection("贷");
            }
            if (new BigDecimal(particularglDTO.getFirstcorpus())
                .compareTo(new BigDecimal(0.00)) == 0) {
              particularglDTO_qichu.setDerection("平");
            }
            particularglDTO_qichu.setLastcorpus(new BigDecimal(particularglDTO
                .getFirstcorpus()).abs().toString());

            particularglDTO_qichu.setContractId(borrowercontractid);
            if (particularglDTO_qichu.getOcyear().substring(4, 8)
                .equals("0101")) {
              particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
            } else {
              particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
            }
            newlist2.add(particularglDTO_qichu);
          }
          if (j == 0) {
            time = ocyearMonthDay;
          }
          if (!time.substring(0, 4).equals(ocyearMonthDay.substring(0, 4))) {// 不为这个科目的话,创建新的页面
            time = ocyearMonthDay;
            ParticularglDTO particularglDTO_qichu = new ParticularglDTO();
            particularglDTO_qichu.setOcyear(particularglDTO.getOcyear());
            particularglDTO_qichu.setDocNum("");
            // BigDecimal
            // bigoc1=loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu(bizdateB,
            // borrowercontractid);//累计借方发生额中的 发放金(本期)
            // BigDecimal
            // bigwoc1=loanFlowHeadDAO.queryWrongMoneyDay_wsh_qichu(bizdateB,
            // borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期)
            // BigDecimal paymoney1=
            // loanFlowHeadDAO.queryPaymoneyDay_qichu_wsh(bizdateB,
            // borrowercontractid); //求贷方发生额 初期
            // BigDecimal interest=
            // loanFlowHeadDAO.queryPaymoneyDay_qichuInterest_wsh(bizdateB,
            // borrowercontractid); //求贷方发生额 初期
            // particularglDTO_qichu.setThisborrower(bigoc1.add(bigwoc1).toString());//生成本期借方
            // particularglDTO_qichu.setThispaymoney(paymoney1.toString());
            // particularglDTO_qichu.setThisinterest(interest.toString());
            particularglDTO_qichu.setThisborrower("0.00");// 生成本期借方
            particularglDTO_qichu.setThispaymoney("0.00");
            particularglDTO_qichu.setThisinterest("0.00");
            particularglDTO_qichu.setThispunishinterest("0.00");
            particularglDTO_qichu.setLastcorpus(new BigDecimal(particularglDTO
                .getFirstcorpus()).toString());
            particularglDTO_qichu.setContractId(borrowercontractid);
            if (new BigDecimal(particularglDTO_qichu.getLastcorpus())
                .compareTo(new BigDecimal(0.00)) > 0) {
              particularglDTO_qichu.setDerection("借");
            }
            if (new BigDecimal(particularglDTO_qichu.getLastcorpus())
                .compareTo(new BigDecimal(0.00)) < 0) {
              particularglDTO_qichu.setDerection("贷");
            }
            if (new BigDecimal(particularglDTO_qichu.getLastcorpus())
                .compareTo(new BigDecimal(0.00)) == 0) {
              particularglDTO_qichu.setDerection("平");
            }
            if (particularglDTO_qichu.getOcyear().substring(4, 8)
                .equals("0101")) {
              particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
            } else {
              particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
            }
            newlist2.add(particularglDTO_qichu);
          }
          newlist2.add(particularglDTO);
        }
      } else {
        //    //每条合同求期初的
        ParticularglDTO particularglDTO2 = new ParticularglDTO();
        particularglDTO2.setOcyear(bizdateB);
        ParticularglDTO particularglDTO_qichu = new ParticularglDTO();
        particularglDTO_qichu.setOcyear(bizdateB);
        particularglDTO_qichu.setDocNum("");
        //    BigDecimal bigoc1=loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu(bizdateB, borrowercontractid);//累计借方发生额中的 发放金(本期) 
        //    BigDecimal bigwoc1=loanFlowHeadDAO.queryWrongMoneyDay_wsh_qichu(bizdateB, borrowercontractid);//累计借方发生额中的 发放金错账类型的(本期) 
        //    BigDecimal paymoney1= loanFlowHeadDAO.queryPaymoneyDay_qichu_wsh(bizdateB, borrowercontractid); //求贷方发生额 初期
        //    BigDecimal interest= loanFlowHeadDAO.queryPaymoneyDay_qichuInterest_wsh(bizdateB, borrowercontractid); //求贷方发生额 初期
        //    particularglDTO_qichu.setThisborrower(bigoc1.add(bigwoc1).toString());//生成本期借方
        //    particularglDTO_qichu.setThispaymoney(paymoney1.toString());
        //    particularglDTO_qichu.setThisinterest(interest.toString());
        particularglDTO_qichu.setThisborrower("0.00");//生成本期借方
        particularglDTO_qichu.setThispaymoney("0.00");
        particularglDTO_qichu.setThisinterest("0.00");
        particularglDTO_qichu.setThispunishinterest("0.00");
        BigDecimal ocMoney = loanFlowHeadDAO.queryOcMoneyDay(bizdateB,
            borrowercontractid);// 累计借方发生额中的 发放金(初期)
        BigDecimal wrongMoney = loanFlowHeadDAO.queryWrongMoneyDay(bizdateB,
            borrowercontractid);//累计借方发生额中的 错账金额(初期)
        BigDecimal paymoney = loanFlowHeadDAO.queryPaymoneyDay(bizdateB,
            borrowercontractid); //求贷方发生额 初期
        if (ocMoney.add(wrongMoney).subtract(paymoney).compareTo(
            new BigDecimal(0.00)) > 0) {
          particularglDTO_qichu.setDerection("借");
        }
        if (ocMoney.add(wrongMoney).subtract(paymoney).compareTo(
            new BigDecimal(0.00)) < 0) {
          particularglDTO_qichu.setDerection("贷");
        }
        if (ocMoney.add(wrongMoney).subtract(paymoney).compareTo(
            new BigDecimal(0.00)) == 0) {
          particularglDTO_qichu.setDerection("平");
        }
        particularglDTO_qichu.setLastcorpus((ocMoney.add(wrongMoney)).subtract(
            paymoney).abs().toString());//期初本金余额 
        particularglDTO_qichu.setContractId(borrowercontractid);
        if (particularglDTO_qichu.getOcyear().substring(4, 8).equals("0101")) {
          particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
        } else {
          particularglDTO_qichu.setBizType(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
        }
        particularglDTO2.setBizType(BusiTools.getBusiValue_WL(
            BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
        particularglDTO2.setContractId(borrowercontractid);
        newlist2.add(particularglDTO_qichu);
      }
    }
    pagination.setNrOfElements(newlist2.size());
    particularglTaAF.setList(newlist);
    particularglTaAF.setAlllist(newlist2);
    particularglTaAF.setCount(newlist.size() + "");
    return particularglTaAF;
  }
  
  
  
  
  public List changePrintList(List list) throws Exception {
    BigDecimal debittotal = new BigDecimal(0.00);
    BigDecimal credittotal = new BigDecimal(0.00);
    BigDecimal distorytotal = new BigDecimal(0.00);
    BigDecimal moneysumtotal = new BigDecimal(0.00);
    BigDecimal punish = new BigDecimal(0.00);
    BigDecimal debittotal_m = new BigDecimal(0.00);
    BigDecimal credittotal_m = new BigDecimal(0.00);
    BigDecimal distorytotal_m = new BigDecimal(0.00);
    BigDecimal moneysumtotal_m = new BigDecimal(0.00);
    BigDecimal punish_m = new BigDecimal(0.00);
    BigDecimal debittotal_y = new BigDecimal(0.00);
    BigDecimal credittotal_y = new BigDecimal(0.00);
    BigDecimal distorytotal_y = new BigDecimal(0.00);
    BigDecimal moneysumtotal_y = new BigDecimal(0.00);
    BigDecimal punish_y = new BigDecimal(0.00);
    List printList = new ArrayList();
    try {
      for (int i = 0; i < list.size(); i++) {
        ParticularglDTO particularglDTO = (ParticularglDTO) list.get(i);
        if (i == 0) {
        } else {
          ParticularglDTO particularglDTO1 = (ParticularglDTO) list.get(i - 1);

          // 到日 加本日合计
          if (!particularglDTO.getOcyear().substring(0, 8).equals(
              particularglDTO1.getOcyear().substring(0, 8))) {
            ParticularglDTO particularglDTO2 = new ParticularglDTO();
            particularglDTO2.setOcyear(particularglDTO1.getOcyear());
            // collFnComparisonOrgAccountDTO2.setDoc_num("");
            particularglDTO2.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_DAYSUM, BusiConst.FNSUMMARY));
            // collFnComparisonOrgAccountDTO2.setBiz_status("");
            particularglDTO2.setThisborrower(debittotal.toString());
            particularglDTO2.setThispaymoney(credittotal.toString());
            particularglDTO2.setThisinterest(distorytotal.toString());
            particularglDTO2.setDerection(particularglDTO1.getDerection());
            particularglDTO2.setLastcorpus(moneysumtotal.toString());
            particularglDTO2.setThispunishinterest(punish.toString());
            particularglDTO2.setContractId(((ParticularglDTO) printList
                .get(printList.size() - 1)).getContractId());
            debittotal = new BigDecimal(0.00);
            credittotal = new BigDecimal(0.00);
            distorytotal = new BigDecimal(0.00);
            moneysumtotal = new BigDecimal(0.00);
            punish = new BigDecimal(0.00);
            printList.add(particularglDTO2);
          }
          // 本期合计
          if (!particularglDTO.getOcyear().substring(0, 6).equals(
              particularglDTO1.getOcyear().substring(0, 6))) {
            ParticularglDTO particularglDTO2 = new ParticularglDTO();
            particularglDTO2.setOcyear(particularglDTO1.getOcyear());
            // collFnComparisonOrgAccountDTO2.setDoc_num("");
            particularglDTO2.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_TERMSUM, BusiConst.FNSUMMARY));
            // collFnComparisonOrgAccountDTO2.setBiz_status("");
            particularglDTO2.setThisborrower(debittotal_m.toString());
            particularglDTO2.setThispaymoney(credittotal_m.toString());
            particularglDTO2.setThisinterest(distorytotal_m.toString());
            particularglDTO2.setDerection(particularglDTO1.getDerection());
            particularglDTO2.setLastcorpus(particularglDTO1.getLastcorpus());
            particularglDTO2.setThispunishinterest(punish_m.toString());
            particularglDTO2.setContractId(((ParticularglDTO) printList
                .get(printList.size() - 1)).getContractId());
            debittotal_m = new BigDecimal(0.00);
            credittotal_m = new BigDecimal(0.00);
            distorytotal_m = new BigDecimal(0.00);
            moneysumtotal_m = new BigDecimal(0.00);
            punish_m = new BigDecimal(0.00);
            printList.add(particularglDTO2);
          }
          // 本年累计
          if (!particularglDTO.getOcyear().substring(0, 6).equals(
              particularglDTO1.getOcyear().substring(0, 6))) {
            ParticularglDTO particularglDTO2 = new ParticularglDTO();
            particularglDTO2.setOcyear(particularglDTO1.getOcyear());
            // collFnComparisonOrgAccountDTO2.setDoc_num("");
            particularglDTO2.setBizType(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
            BigDecimal bigoc1 = loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu_1(
                particularglDTO1.getOcyear(), particularglDTO.getContractId());// 累计借方发生额中的
                                                                                // 发放金(本期)
            BigDecimal bigwoc1 = loanFlowHeadDAO
                .queryWrongMoneyDay_wsh_qichu_1(particularglDTO1.getOcyear(),
                    particularglDTO.getContractId());// 累计借方发生额中的 发放金错账类型的(本期)
            BigDecimal paymoney1 = loanFlowHeadDAO
                .queryPaymoneyDay_qichu_wsh_1(particularglDTO1.getOcyear(),
                    particularglDTO.getContractId()); // 求贷方发生额 初期
            BigDecimal interest = loanFlowHeadDAO
                .queryPaymoneyDay_qichuInterest_wsh_1(particularglDTO1
                    .getOcyear(), particularglDTO.getContractId()); // 求贷方发生额 初期
            // collFnComparisonOrgAccountDTO2.setBiz_status("");
            BigDecimal big = bigoc1.add(bigwoc1);
            // 挂账
            BigDecimal biglo_1 = loanFlowHeadDAO.queryThisLoanrepayDay_3(
                particularglDTO.getOcyear(), particularglDTO.getContractId());// 挂账金额(本期)
            BigDecimal bigwlo_1 = loanFlowHeadDAO.queryThisWrongLoanrepayDay_3(
                particularglDTO.getOcyear(), particularglDTO.getContractId());// 挂账金额错账类型(本期)
            if (biglo_1.add(bigwlo_1).compareTo(new BigDecimal(0.00)) > 0) {
              paymoney1.add(biglo_1.add(bigwlo_1)).toString();
            } else {
              big.add(biglo_1.add(bigwlo_1));
            }
            // 挂账
            BigDecimal bigball_1 = loanFlowHeadDAO.queryThisballbalanceDay_3(
                particularglDTO.getOcyear(), particularglDTO.getContractId());// 保证金，本期
            // 保证金
            if (bigball_1.compareTo(new BigDecimal(0.00)) > 0) {
              paymoney1.add(bigball_1).toString();
            } else {
              big.add(bigball_1);
            }
            // 保证金
            BigDecimal bigbad_1 = loanFlowHeadDAO.queryThisbadocmoneyDay_3(
                particularglDTO.getOcyear(), particularglDTO.getContractId());// 呆账核销金额
                                                                              // (本期)
            BigDecimal bigbadw_1 = loanFlowHeadDAO.queryThisbadwocmoneyDay_3(
                particularglDTO.getOcyear(), particularglDTO.getContractId());// 呆账核销金额
                                                                              // 错账账类型的(本期)
            // 呆账核销金额
            if (bigbad_1.add(bigbadw_1).compareTo(new BigDecimal(0.00)) > 0) {
              paymoney1.add(bigbad_1.add(bigbadw_1)).toString();
            } else {
              big.add(bigbad_1.add(bigbadw_1));
            }
            particularglDTO2.setThisborrower(big.toString());
            particularglDTO2.setThispaymoney(paymoney1.toString());
            particularglDTO2.setThisinterest(interest.toString());
            particularglDTO2.setDerection(particularglDTO1.getDerection());
            particularglDTO2.setLastcorpus(particularglDTO1.getLastcorpus());
            particularglDTO2.setThispunishinterest(punish_y.toString());
            particularglDTO2.setContractId(((ParticularglDTO) printList
                .get(printList.size() - 1)).getContractId());
            if (!particularglDTO.getOcyear().substring(0, 4).equals(
                particularglDTO1.getOcyear().substring(0, 4))) {
              debittotal_y = new BigDecimal(0.00);
              credittotal_y = new BigDecimal(0.00);
              distorytotal_y = new BigDecimal(0.00);
              moneysumtotal_y = new BigDecimal(0.00);
              punish_y = new BigDecimal(0.00);
            }
            printList.add(particularglDTO2);
          }
        }
        if (!particularglDTO.getBizType().equals("期 初 余 额")
            && !particularglDTO.getBizType().equals("上 年 结 转")) {
          debittotal = debittotal.add(new BigDecimal(particularglDTO
              .getThisborrower()));
          credittotal = credittotal.add(new BigDecimal(particularglDTO
              .getThispaymoney()));
          distorytotal = distorytotal.add(new BigDecimal(particularglDTO
              .getThisinterest()));
          moneysumtotal = moneysumtotal.add(new BigDecimal(particularglDTO
              .getLastcorpus()));
          debittotal_m = debittotal_m.add(new BigDecimal(particularglDTO
              .getThisborrower()));
          credittotal_m = credittotal_m.add(new BigDecimal(particularglDTO
              .getThispaymoney()));
          distorytotal_m = distorytotal_m.add(new BigDecimal(particularglDTO
              .getThisinterest()));
          moneysumtotal_m = moneysumtotal_m.add(new BigDecimal(particularglDTO
              .getLastcorpus()));
          debittotal_y = debittotal_y.add(new BigDecimal(particularglDTO
              .getThisborrower()));
          credittotal_y = credittotal_y.add(new BigDecimal(particularglDTO
              .getThispaymoney()));
          distorytotal_y = distorytotal_y.add(new BigDecimal(particularglDTO
              .getThisinterest()));
          moneysumtotal_y = moneysumtotal_y.add(new BigDecimal(particularglDTO
              .getLastcorpus()));
        }
        printList.add(particularglDTO);
        if (i == list.size() - 1
            && !(((ParticularglDTO) (list.get(list.size() - 1))).getBizType())
                .equals("期 初 余 额")
            || ((ParticularglDTO) (list.get(list.size() - 1))).getBizType()
                .equals("上 年 结 转")) {
          // 最后一条
          ParticularglDTO particularglDTO_d = new ParticularglDTO();
          particularglDTO_d.setOcyear(particularglDTO.getOcyear());
          particularglDTO_d.setDocNum("");
          particularglDTO_d.setBizType(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_DAYSUM, BusiConst.FNSUMMARY));
          particularglDTO_d.setThisborrower(debittotal.toString());
          particularglDTO_d.setThispaymoney(credittotal.toString());
          particularglDTO_d.setThisinterest(distorytotal.toString());
          particularglDTO_d.setDerection(particularglDTO.getDerection());
          particularglDTO_d.setLastcorpus(particularglDTO.getLastcorpus());
          particularglDTO_d.setContractId(particularglDTO.getContractId());
          particularglDTO_d.setThispunishinterest(punish.toString());
          debittotal = new BigDecimal(0.00);
          credittotal = new BigDecimal(0.00);
          distorytotal = new BigDecimal(0.00);
          moneysumtotal = new BigDecimal(0.00);
          punish = new BigDecimal(0.00);
          printList.add(particularglDTO_d);
          ParticularglDTO particularglDTO_m = new ParticularglDTO();
          particularglDTO_m.setOcyear(particularglDTO.getOcyear());
          particularglDTO_m.setDocNum("");
          particularglDTO_m.setBizType(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_TERMSUM, BusiConst.FNSUMMARY));
          particularglDTO_m.setThisborrower(debittotal_m.toString());
          particularglDTO_m.setThispaymoney(credittotal_m.toString());
          particularglDTO_m.setThisinterest(distorytotal_m.toString());
          particularglDTO_m.setDerection(particularglDTO.getDerection());
          particularglDTO_m.setLastcorpus(particularglDTO.getLastcorpus());
          particularglDTO_m.setContractId(particularglDTO.getContractId());
          particularglDTO_m.setThispunishinterest(punish_m.toString());
          debittotal_m = new BigDecimal(0.00);
          credittotal_m = new BigDecimal(0.00);
          distorytotal_m = new BigDecimal(0.00);
          moneysumtotal_m = new BigDecimal(0.00);
          punish_m = new BigDecimal(0.00);
          printList.add(particularglDTO_m);
          ParticularglDTO particularglDTO_y = new ParticularglDTO();
          particularglDTO_y.setOcyear(particularglDTO.getOcyear());
          particularglDTO_y.setDocNum("");
          particularglDTO_y.setBizType(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
          BigDecimal bigoc1 = loanFlowHeadDAO.queryOcMoneyDay_wsh_qichu_1(
              particularglDTO.getOcyear(), particularglDTO.getContractId());// 累计借方发生额中的
                                                                            // 发放金(本期)
          BigDecimal bigwoc1 = loanFlowHeadDAO.queryWrongMoneyDay_wsh_qichu_1(
              particularglDTO.getOcyear(), particularglDTO.getContractId());// 累计借方发生额中的
                                                                            // 发放金错账类型的(本期)
          BigDecimal paymoney1 = loanFlowHeadDAO.queryPaymoneyDay_qichu_wsh_1(
              particularglDTO.getOcyear(), particularglDTO.getContractId()); // 求贷方发生额
                                                                              // 初期
          BigDecimal interest = loanFlowHeadDAO
              .queryPaymoneyDay_qichuInterest_wsh_1(
                  particularglDTO.getOcyear(), particularglDTO.getContractId()); // 求贷方发生额
                                                                                  // 初期
          BigDecimal big = bigoc1.add(bigwoc1);
          // 挂账
          BigDecimal biglo_1 = loanFlowHeadDAO.queryThisLoanrepayDay_3(
              particularglDTO.getOcyear(), particularglDTO.getContractId());// 挂账金额(本期)
          BigDecimal bigwlo_1 = loanFlowHeadDAO.queryThisWrongLoanrepayDay_3(
              particularglDTO.getOcyear(), particularglDTO.getContractId());// 挂账金额错账类型(本期)
          if (biglo_1.add(bigwlo_1).compareTo(new BigDecimal(0.00)) > 0) {
            paymoney1.add(biglo_1.add(bigwlo_1)).toString();
          } else {
            big.add(biglo_1.add(bigwlo_1));
          }
          // 挂账
          BigDecimal bigball_1 = loanFlowHeadDAO.queryThisballbalanceDay_3(
              particularglDTO.getOcyear(), particularglDTO.getContractId());// 保证金，本期
          // 保证金
          if (bigball_1.compareTo(new BigDecimal(0.00)) > 0) {
            paymoney1.add(bigball_1).toString();
          } else {
            big.add(bigball_1);
          }
          // 保证金
          BigDecimal bigbad_1 = loanFlowHeadDAO.queryThisbadocmoneyDay_3(
              particularglDTO.getOcyear(), particularglDTO.getContractId());// 呆账核销金额
                                                                            // (本期)
          BigDecimal bigbadw_1 = loanFlowHeadDAO.queryThisbadwocmoneyDay_3(
              particularglDTO.getOcyear(), particularglDTO.getContractId());// 呆账核销金额
                                                                            // 错账账类型的(本期)
          // 呆账核销金额
          if (bigbad_1.add(bigbadw_1).compareTo(new BigDecimal(0.00)) > 0) {
            paymoney1.add(bigbad_1.add(bigbadw_1)).toString();
          } else {
            big.add(bigbad_1.add(bigbadw_1));
          }
          particularglDTO_y.setThisborrower(big.toString());
          particularglDTO_y.setThispaymoney(paymoney1.toString());
          particularglDTO_y.setThisinterest(interest.toString());
          particularglDTO_y.setDerection(particularglDTO.getDerection());
          particularglDTO_y.setLastcorpus(particularglDTO.getLastcorpus());
          particularglDTO_y.setContractId(particularglDTO.getContractId());
          particularglDTO_y.setThispunishinterest(punish_y.toString());
          debittotal_y = new BigDecimal(0.00);
          credittotal_y = new BigDecimal(0.00);
          distorytotal_y = new BigDecimal(0.00);
          moneysumtotal_y = new BigDecimal(0.00);
          punish_y = new BigDecimal(0.00);
          printList.add(particularglDTO_y);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return printList;
  }
  
  /**
   * 获得发生方向
   * @param value
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String getDirtection(BigDecimal value)throws Exception, BusinessException{
    String dirtection="";
    try{
      if(value.compareTo(new BigDecimal(0.00))==0){
        dirtection=BusiTools.getBusiValue_WL(BusiConst.BALANCEDIRECTION_AVE,BusiConst.BALANCEDIRECTION);
      }else if(value.compareTo(new BigDecimal(0.00))>0){
        dirtection=BusiTools.getBusiValue_WL(BusiConst.BALANCEDIRECTION_DEBIT,BusiConst.BALANCEDIRECTION);
      }else{
        dirtection=BusiTools.getBusiValue_WL(BusiConst.BALANCEDIRECTION_CREDIT,BusiConst.BALANCEDIRECTION);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return dirtection;
  }
}
