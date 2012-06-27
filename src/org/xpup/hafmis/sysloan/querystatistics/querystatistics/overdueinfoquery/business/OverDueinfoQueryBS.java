package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.OverdueInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.OverdueInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface.IOverDueinfoQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryImportDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryShowListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryTotleDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryShowListAF;

public class OverDueinfoQueryBS implements IOverDueinfoQueryBS {
  private BorrowerAccDAO borrowerAccDAO = null;// 借款人账户表 PL111

  private AssistantOrgDAO assistantOrgDAO = null; // 协作单位信息表PL007

  private OverdueInfoDAO overdueInfoDAO = null;

  private SecurityDAO securityDAO = null;
  
  private RestoreLoanDAO restoreLoanDAO = null;
  
  private LoanBankParaDAO loanBankParaDAO = null;

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setOverdueInfoDAO(OverdueInfoDAO overdueInfoDAO) {
    this.overdueInfoDAO = overdueInfoDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  /**
   * 统计查询－逾期还款信息列表
   * 
   * @author 吴迪 2007-10-23
   * @return
   */
  public OverDueinfoQueryShowListAF queryShowListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    OverDueinfoQueryShowListAF overDueinfoQueryShowListAF = new OverDueinfoQueryShowListAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String offic = (String) pagination.getQueryCriterions().get("offic");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String buyhouseorgid = (String) pagination.getQueryCriterions().get(
        "buyhouseorgid");
    String floorId = (String) pagination.getQueryCriterions().get("floorId");
    String assistantorg = (String) pagination.getQueryCriterions().get(
        "assistantorg");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String beginoweMonth = (String) pagination.getQueryCriterions().get(
        "beginoweMonth");
    String endoweMonth = (String) pagination.getQueryCriterions().get(
        "endoweMonth");
    String check = (String) pagination.getQueryCriterions().get("temp_check");
    String isNoAcquittance = (String) pagination.getQueryCriterions().get(
        "isNoAcquittance");
    // -------------------------------------------------------//
    // 查询条件
    String housetype = (String) pagination.getQueryCriterions()
        .get("housetype");
    String buildAreaMin = (String) pagination.getQueryCriterions().get(
        "buildAreaMin");
    String buildAreaMax = (String) pagination.getQueryCriterions().get(
        "buildAreaMax");
    String floorid = (String) pagination.getQueryCriterions().get("floorid");
    String floorName = (String) pagination.getQueryCriterions()
        .get("floorName");
    String floorNum = (String) pagination.getQueryCriterions().get("floorNum");
    String roomNum = (String) pagination.getQueryCriterions().get("roomNum");
    String agreement = (String) pagination.getQueryCriterions()
        .get("agreement");
    String applyDate = (String) pagination.getQueryCriterions()
        .get("applyDate");
    String otherMoney = (String) pagination.getQueryCriterions().get(
        "otherMoney");
    String startDateMin = (String) pagination.getQueryCriterions().get(
        "startDateMin");
    String startDateMax = (String) pagination.getQueryCriterions().get(
        "startDateMax");
    if (check != null && !"".equals(check)) {
      List overDueinfoQueryShowList = new ArrayList();
      overDueinfoQueryShowList = borrowerAccDAO
          .queryoverDueinfoQueryShowListByCriterions_yg(start, orderBy, order,
              pageSize, page, securityInfo, offic, loanBankName, buyhouseorgid,
              floorId, assistantorg, contractId, loanKouAcc, borrowerName,
              cardNum, beginoweMonth, endoweMonth, isNoAcquittance, housetype,
              buildAreaMin, buildAreaMax, floorid, floorName, floorNum,
              roomNum, agreement, applyDate, otherMoney, startDateMin,
              startDateMax);
      // 逾期还款信息列表
      overDueinfoQueryShowListAF.setList(overDueinfoQueryShowList);
      OverDueinfoQueryTotleDTO overDueinfoQueryTotleDTO = new OverDueinfoQueryTotleDTO();
      overDueinfoQueryTotleDTO = borrowerAccDAO
          .queryoverDueinfoQueryTotleShowListByCriterions_yg(securityInfo,
              offic, loanBankName, buyhouseorgid, floorId, assistantorg,
              contractId, loanKouAcc, borrowerName, cardNum, beginoweMonth,
              endoweMonth, isNoAcquittance, housetype, buildAreaMin,
              buildAreaMax, floorid, floorName, floorNum, roomNum, agreement,
              applyDate, otherMoney, startDateMin, startDateMax);
      // 合计信息
      BigDecimal oweCorpusTotle = overDueinfoQueryTotleDTO.getOweCorpusTotle();// 欠还本金-总额
      BigDecimal oweInterestTotle = overDueinfoQueryTotleDTO
          .getOweInterestTotle();// 欠还利息-总额
      BigDecimal punishInterest = overDueinfoQueryTotleDTO.getPunishInterest();// 欠还罚息利息-总额
      overDueinfoQueryShowListAF.setOweCorpusTotle(oweCorpusTotle);
      overDueinfoQueryShowListAF.setOweInterestTotle(oweInterestTotle);
      overDueinfoQueryShowListAF.setPunishInterest(punishInterest);
      // 列表总数
      count = overDueinfoQueryTotleDTO.getCount();
    }
    // 求担保公司
    List assistantOrglist = assistantOrgDAO.queryAssistantOrgList();
    overDueinfoQueryShowListAF.setAssistantorgList(assistantOrglist);
    pagination.setNrOfElements(count);
    return overDueinfoQueryShowListAF;
  }

  /**
   * 统计查询-逾期催还查询信息列表打印
   * 
   * @author 吴迪 2007-10-23
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List findoverDueinfoQueryPrint(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    
    OverDueinfoQueryShowListAF overDueinfoQueryShowListAF = new OverDueinfoQueryShowListAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String offic = (String) pagination.getQueryCriterions().get("offic");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String buyhouseorgid = (String) pagination.getQueryCriterions().get(
        "buyhouseorgid");
    String floorId = (String) pagination.getQueryCriterions().get("floorId");
    String assistantorg = (String) pagination.getQueryCriterions().get(
        "assistantorg");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String beginoweMonth = (String) pagination.getQueryCriterions().get(
        "beginoweMonth");
    String endoweMonth = (String) pagination.getQueryCriterions().get(
        "endoweMonth");
    String check=(String) pagination.getQueryCriterions().get(
    "temp_check");
    String isNoAcquittance = (String) pagination.getQueryCriterions().get(
    "isNoAcquittance");
    //-------------------------------------------------------//
//  查询条件
    String housetype = (String) pagination.getQueryCriterions().get("housetype");
    String buildAreaMin = (String) pagination.getQueryCriterions().get("buildAreaMin");
    String buildAreaMax = (String) pagination.getQueryCriterions().get("buildAreaMax");
    String floorid = (String) pagination.getQueryCriterions().get("floorid");
    String floorName = (String) pagination.getQueryCriterions().get("floorName");
    String floorNum = (String) pagination.getQueryCriterions().get("floorNum");
    String roomNum = (String) pagination.getQueryCriterions().get("roomNum");
    String agreement = (String) pagination.getQueryCriterions().get("agreement");
    String applyDate = (String) pagination.getQueryCriterions().get("applyDate");
    String otherMoney = (String) pagination.getQueryCriterions().get("otherMoney");
    String startDateMin = (String) pagination.getQueryCriterions().get("startDateMin");
    String startDateMax = (String) pagination.getQueryCriterions().get("startDateMax");
    
    List overDueinfoQueryShowList = new ArrayList();
    overDueinfoQueryShowList = borrowerAccDAO
        .queryoverDueinfoQueryShowListByCriterions_yga(start, orderBy, order,
            pageSize, page, securityInfo, offic, loanBankName, buyhouseorgid,
            floorId, assistantorg, contractId, loanKouAcc, borrowerName,
            cardNum, beginoweMonth, endoweMonth, isNoAcquittance, housetype,
            buildAreaMin, buildAreaMax, floorid, floorName, floorNum, roomNum,
            agreement, applyDate, otherMoney, startDateMin, startDateMax);
    
    return overDueinfoQueryShowList;
  }

  /**
   * 统计查询-逾期催还查询信息单个打印
   * 
   * @author 吴迪 2007-10-23
   * @param contractId
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public OverDueinfoQueryShowListDTO findoverDueinfoQueryPrintone(String id,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // 核销收回维护单个打印
    OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO= borrowerAccDAO.queryoverDueinfoQueryPrintoneByCriterions_wsh(
        securityInfo, id);
    return  overDueinfoQueryShowListDTO;
  }
  
  /**
   * 以中心为主生成逾期数据
   * jj
   * @param securityInfo
   * @throws Exception
   */
  public void createOverdueData(SecurityInfo securityInfo,String loanBankId) throws Exception{
    String username = securityInfo.getUserName();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String bizYearMonth = bizDate.substring(0,6);
    String bizDay = bizDate.substring(6,8);
    overdueInfoDAO.executeCreateOverdueData(username, bizDate,new Integer(bizDay),new Integer(bizYearMonth),loanBankId);
    String paramType = "A";// 参数类型
    String isRate = "";// 是否记息
    String accountDate = "";// 记账日期
    String interestMode = "";// 计算罚息方式
    String paramExplain = "";//参数说明
    String allowdays = "";// 宽限天数
    // 从PL003中查询宽限天数内是否计息
    isRate = loanBankParaDAO.queryParamValue_LJ(new Integer(loanBankId), paramType, "5");
    // 从PL003中取计算罚息方式（按按罚息日利率、按合同日利率、按额每日XX元计算）
    interestMode = loanBankParaDAO.queryParamValue_LJ(new Integer(loanBankId), paramType,
        "4");
    // 从PL003中取对应的参数说明
    paramExplain = loanBankParaDAO.queryParamExplain_LJ(new Integer(loanBankId),
        paramType, "4");
    // PL003中查询宽限天数
    allowdays = loanBankParaDAO.queryParamExplain_LJ(new Integer(loanBankId), paramType,
        "5");
    List callbackList = overdueInfoDAO.queryRestoreLoanListByBank_LJ(loanBankId, bizYearMonth, bizDay); 
    this.updatePunishInterest(securityInfo,isRate,accountDate,interestMode,paramExplain,allowdays,callbackList);
  }
  
  public void updatePunishInterest(SecurityInfo securityInfo,String isRate,String accountDate,
      String interestMode,String paramExplain,String allowdays,List callbackList)throws Exception{
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      List list = this.findCallbackList(callbackList, bizDate, isRate, accountDate, interestMode, paramExplain, allowdays);
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) list.get(i);
          overdueInfoDAO.insertOver_data_jj(dto.getContractId(),dto.getPunishInterest());
        }
      }
      overdueInfoDAO.executeUpdateOverdueData(bizDate.substring(0,6));
  }
  /**
   * 以银行为主，导入逾期数据
   * @param importList
   * @param securityInfo
   * @throws Exception
   */
  public void importOverdueData(List importList, SecurityInfo securityInfo)throws Exception{
    String loanBankId="";
    List bankList = new ArrayList();
    List borrowerAccList=null;
    String loanKouAcc = "";
    String contractId = "";
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// 合同状态 11.还款中
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String bizDateim="";
    if(importList.isEmpty()){
      throw new BusinessException("导入文件为空！");
    }else{
          OverDueinfoQueryImportDTO dto=(OverDueinfoQueryImportDTO)importList.get(0);
          loanBankId = dto.getLoanBankId();
          bizDateim = dto.getBizDate();
    }
    // 日期是否与会计日期相同
    String temp_bizDate = bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8);
    if(!temp_bizDate.equals(bizDateim)){
      throw new BusinessException("导入文件中的日期与会计日期不一致！");
    }
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(), loanBankId);
      //判断有无操作此银行的权限
      if(bankList.isEmpty()){
        throw new BusinessException("不具备该银行的权限！");
      }
      //删除该银行下在逾期信息PL205中的数据，根据会计日期删除，相同会计日期可以删除，再导入     
      overdueInfoDAO.deleteOverdueInfoByLoanBankId(loanBankId,bizDate);
      //在逾期信息PL205中插入数据（关联条件为该银行下借款人账户表PL111中合同状态=11.还款中）
      borrowerAccList = borrowerAccDAO.queryBorrowerAccInfoByLoanBankIdAcc_LJ(loanBankId, contractSt);
      if(!borrowerAccList.isEmpty()){
        Object obj=null;
        Iterator it = borrowerAccList.iterator();
        while(it.hasNext()){
          obj=(Object)it.next();
          OverdueInfo overdueInfo = new OverdueInfo();
          overdueInfo.setContractId(obj.toString());
          overdueInfo.setLoanBankId(new Integer(loanBankId));
          overdueInfo.setOweCorpus(new BigDecimal(0.00));
          overdueInfo.setOweInterest(new BigDecimal(0.00));
          overdueInfo.setPunishInterest(new BigDecimal(0.00));
          overdueInfo.setOweMonth("0");
          overdueInfo.setOweDate(bizDate);
          overdueInfoDAO.insert(overdueInfo);
        }
      }
      //用导入数据中的未还本金、未还利息、未还罚息、逾期月数、月还本息更新逾期信息PL205中对应的数据（关联合同编号）
      for(int i=1;i<importList.size();i++){
        OverDueinfoQueryImportDTO dto = (OverDueinfoQueryImportDTO)importList.get(i);
        loanKouAcc = dto.getLoanKouAcc();
        contractId = borrowerAccDAO.queryBorrowerAccByLoanKouAcc_LJ(loanKouAcc,
            contractSt, securityInfo);
        Integer pkId = overdueInfoDAO.queryOverdueInfoContactIds(contractId,securityInfo);
        if(pkId==null){
          throw new BusinessException("导入文件中的贷款账号与数据库中数据不一致！");
        }else{
          OverdueInfo overdueInfo=overdueInfoDAO.queryById(pkId);
          overdueInfo.setOweCorpus(new BigDecimal(dto.getNobackCorpus()));
          overdueInfo.setOweInterest(new BigDecimal(dto.getNobackInterest()));
          overdueInfo.setOweMonth(dto.getMonthsCount());
          overdueInfo.setPunishInterest(new BigDecimal(dto.getNobackPunishInterest()));
          overdueInfo.setReserveaA(dto.getCorpusInterest());
        }
      }
    }

  public LoancallbackTaAF getLoancallbackTaAF(String contractId,
      SecurityInfo securityInfo,String isRate,String accountDate,
      String interestMode,String paramExplain,String allowdays)throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    //String contractId = (String)pagination.getQueryCriterions().get("contractId");
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    List borrowerList = new ArrayList();// 账户信息
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    List shouldBackList = new ArrayList();
    String yearMonth=bizDate.substring(0,6);
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    // 从PL110及PL111取信息
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
    //如果没有选择还至日则需判断还款日和会计日的大小
    if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
     // 会计日小于还款日
     shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
         contractId, yearMonth);
   }else {
     // 会计日大于等于还款日
     shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
         contractId, yearMonth);
   }
    af = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate,isRate,accountDate,interestMode,paramExplain,allowdays);
    return af;
  }

  /**
   * 应还信息列表
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackList(List shouldBackList,BorrowerInfoDTO borrowerInfoDTO,String bizDate,
      String isRate,String accountDate,String interestMode,String paramExplain,String allowdays)throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    List temp_list = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    //Integer loanBankId = borrowerInfoDTO.getLoanBankId();// 放款银行
    String loanRepayDay = "";// 还款日 在取应还款信息时用到
    BigDecimal temp_interest = new BigDecimal(0.00);// 临时罚息
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    if (!shouldBackList.isEmpty()) {
      for (int i = 0; i < shouldBackList.size(); i++) {
        ShouldBackListDTO dto1 = (ShouldBackListDTO) shouldBackList.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        String loanRepayDay1 = this.getEndDay(dto1.getLoanKouYearmonth(), loanRepayDay);
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// 显示还款年月
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
        // 逾期天数
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        if (days > 0) {
          dto2.setLoanKouType("逾期");// 显示还款类型
        } else {
          dto2.setLoanKouType("正常");
        }
        dto2.setShouldCorpus(dto1.getShouldCorpus().subtract(
            dto1.getRealCorpus()));// 显示应还本金
        dto2.setShouldInterest(dto1.getShouldInterest().subtract(
            dto1.getRealInterest()));// 显示应还利息
        dto2.setDays(days + "");// 显示逾期天数
        if(days<0){
          dto2.setDays("0");
        }
        // 罚息卡住了。
        // 足条计算每月产生的罚息
        // 判断还款表(应还本金-本金+应还利息-利息)是否=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else if(days<=0){
          dto2.setPunishInterest(dto1.getPunishInterest());
        }else {
          // 不等于0判断是否在宽限天数内计息
          // 条件银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数值PARAM_VALUE是否=0(宽限天数内计息)
          if (isRate.equals(BusiConst.YES + "")) {// 计息
            // 逐条判断PL201中的记账日期是否小于等于还款日
            accountDate = dto1.getBizDate();
       //     String temp_day = accountDate.substring(6, 8);// 记账日期的日
      //      if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay)) {// 小于等于还款日
            if (accountDate==null || accountDate.equals("")) {//判断是否有记账日期：没有：减还款年月；有：减记账日期
                temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain ,dto1.getLoanRate());
            }else if(Integer.parseInt(accountDate) <= Integer.parseInt(dto1.getLoanKouYearmonth()+loanRepayDay1)){// 小于等于还款日
                temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                    .getShouldCorpus(), dto1.getRealCorpus(), dto1
                    .getShouldInterest(), dto1.getRealInterest(),
                paramExplain ,dto1.getLoanRate());
            } else {// 大于还款日
                temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                    dto1.getBizDate(), dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain,dto1.getLoanRate());
              temp_interest = temp_interest.add(dto1.getPunishInterest())
                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// 加还款表中未还罚息
            }
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(temp_interest);
            } else {
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          } else {// 不计息
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(new BigDecimal(0.00));
            } else {// 已经逾期
              // 逐条判断PL201中的记账日期是否小于等于还款日+宽限天数
   //           String temp_day = dto1.getBizDate().substring(6, 8);// 记账日期的日
              // 还款年月日+宽限天数后生成的年月日
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay1, Integer.parseInt(allowdays));
              //temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
//              if (Integer.parseInt(temp_day) <= Integer.parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
              if (dto1.getBizDate()==null || dto1.getBizDate().equals("")) {//判断是否有记账日期
                  temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                          .getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, allowdays,dto1.getLoanRate());
              } else if(Integer.parseInt(dto1.getBizDate())<=Integer.parseInt(temp_loanRepayDay)){//小于等于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, allowdays,dto1.getLoanRate());
              }else {// 大于还款日+宽限天数
                  temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                      dto1.getBizDate(),
                      dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain,dto1.getLoanRate());               
                // 加还款表中未还罚息
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              }
              dto2.setPunishInterest(temp_interest);              
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest()).add(dto2.getPunishInterest()));// 显示应还本息合计
        dto2.setLoanRate(dto1.getLoanRate());// 显示每月利率
        dto2.setShow_loanRate(dto1.getShow_loanRate());
        sumCorpus = sumCorpus.add(dto2.getShouldCorpus());
        sumInterest = sumInterest.add(dto2.getShouldInterest().add(
            dto2.getPunishInterest()));
        temp_list.add(dto2);
      }
    }
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setShouldBackList(temp_list);
    return af;
  }
  /**
   * 判断还款日如果大于本月最后一天返回本月最后一天
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth,String loanRepayDay){
    String day = loanRepayDay;
    String year = yearMonth.substring(0,4);
    String month = yearMonth.substring(4,6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer.parseInt(month));
    if(Integer.parseInt(loanRepayDay)>days){
      day = String.valueOf(days);
    }  
    if(day.length()<2&&Integer.parseInt(day)<10){
      day = "0"+day;
    }
    return day;
  }
  /**
   * 得到计算罚息的列表
   * @param callbackList
   * @param loanBankId
   * @param bizDate
   * @return
   * @throws Exception
   */
  public List findCallbackList(List callbackList,String bizDate,
      String isRate,String accountDate,String interestMode,String paramExplain,String allowdays)throws Exception{
    List temp_list = new ArrayList();
    BigDecimal temp_interest = new BigDecimal(0.00);// 临时罚息
    String loanRepayDay = "";//还款日
    if (!callbackList.isEmpty()) {
      for (int i = 0; i < callbackList.size(); i++) {
        BatchShouldBackListDTO dto1 = (BatchShouldBackListDTO)callbackList.get(i);
        BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO)callbackList.get(i);
        loanRepayDay = this.getEndDay(dto1.getLoanKouYearmonth(), dto1.getLoanRepayDay());
        int days = this.getDays(bizDate, dto1.getLoanKouYearmonth(),loanRepayDay);//逾期天数
        // 足条计算每月产生的罚息
        // 判断还款表(应还本金-本金+应还利息-利息)是否=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        }else if(days<=0){
          dto2.setPunishInterest(dto1.getPunishInterest());
        }else {
    //      loanRepayDay = dto1.getLoanRepayDay();
          // 不等于0判断是否在宽限天数内计息
          // 条件银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数值PARAM_VALUE是否=0(宽限天数内计息)
          if (isRate.equals(BusiConst.YES + "")) {// 计息
            // 逐条判断PL201中的记账日期是否小于等于还款日
            accountDate = dto1.getBizDate();
       //     String temp_day = accountDate.substring(6, 8);// 记账日期的日
      //      if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay)) {// 小于等于还款日
            if (accountDate==null || accountDate.equals("")) {//判断是否有记账日期：没有：减还款年月；有：减记账日期
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
                      .getShouldInterest(), dto1.getRealInterest(),
                  paramExplain ,dto1.getLoanRate());
            }else if(Integer.parseInt(accountDate) <= Integer.parseInt(dto1.getLoanKouYearmonth()+loanRepayDay)){// 小于等于还款日
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                  .getShouldCorpus(), dto1.getRealCorpus(), dto1
                  .getShouldInterest(), dto1.getRealInterest(),
              paramExplain ,dto1.getLoanRate());
            } else {// 大于还款日
              temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                  dto1.getBizDate(), dto1.getShouldCorpus(),
                  dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                      .getRealInterest(), paramExplain,dto1.getLoanRate());
              temp_interest = temp_interest.add(dto1.getPunishInterest())
                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// 加还款表中未还罚息
            }
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(temp_interest);
            } else {
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          } else {// 不计息
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(new BigDecimal(0.00));
            } else {// 已经逾期
              // 逐条判断PL201中的记账日期是否小于等于还款日+宽限天数
     //         String temp_day = dto1.getBizDate().substring(6, 8);// 记账日期的日
              // 还款年月日+宽限天数后生成的年月日
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay, Integer.parseInt(allowdays));
              //temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
//              if (Integer.parseInt(temp_day) <= Integer.parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
              if (dto1.getBizDate()==null || dto1.getBizDate().equals("")) {//判断是否有记账日期
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, allowdays,dto1.getLoanRate());
              } else if(Integer.parseInt(dto1.getBizDate())<=Integer.parseInt(temp_loanRepayDay)){//小于等于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, allowdays,dto1.getLoanRate());
              }else {// 大于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                    dto1.getBizDate(),
                    dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain,dto1.getLoanRate());   
                // 加还款表中未还罚息
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);      
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setLoanRate(dto1.getLoanRate());// 显示每月利率
        dto2.setDays(String.valueOf(days));
        temp_list.add(dto2);
      }
    }
    return temp_list;
  }
  /**
   * 计算逾期天数
   * @param bizDate 业务日期
   * @param yearMonth 还款年月
   * @param loanRepayDay 还款日
   * @return
   */
  public int getDays(String bizDate,String yearMonth,String loanRepayDay){
    int days=0;
    String temp_date = yearMonth.substring(0, 4) + "-"
    + yearMonth.substring(4, 6) + "-" + loanRepayDay;
    String temp_bizDate = bizDate.substring(0, 4) + "-"
    + bizDate.substring(4,6)+"-"+bizDate.substring(6,8);
    // 逾期天数
    days = BusiTools.minusDate(temp_bizDate, temp_date);
    return days;
  }
  }

