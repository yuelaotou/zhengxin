package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.business;




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnBizActivityLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumCancelDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumMaintainDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnDocNumCancel;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.TreasurerCredence;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbShowListDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;




public class CashDayClearBS implements ICashDayClearBS{
  private BookParameterDAO bookParameterDAO=null;
  private TreasurerCredenceDAO treasurerCredenceDAO=null;
  private FnDocNumMaintainDAO fnDocNumMaintainDAO=null;
  private FnOperateLogDAO fnOperateLogDAO=null;
  private FnBizActivityLogDAO fnBizActivityLogDAO=null;
  private AccountantCredenceDAO accountantCredenceDAO=null;
  private FnDocNumCancelDAO fnDocNumCancelDAO=null;
  private BookDAO bookDAO=null;
  private SecurityDAO securityDAO=null;
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-12
   * 查询fn102表里paramExplain字段的数据
   * 查询条件：paramNum
   */
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo,String temp){
    Object[] obj=new Object[3];
    List credenceCharacterList=null;
    List summrayList=null;
    List settTypeList=null;
    try{
      summrayList=bookParameterDAO.getParamExplain("4","10", securityInfo);
      if(temp.equals("")){
        credenceCharacterList=bookParameterDAO.getParamExplain("2","", securityInfo);
        settTypeList=bookParameterDAO.getParamExplain("3","", securityInfo);
      }
      obj[0]=credenceCharacterList;
      obj[1]=summrayList;
      obj[2]=settTypeList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-12
   * 根据CREDENCE_TYPE查找FN210表中该办事处下，ID最大记录的CREDENCE_DATE
   * 查询条件：office
   */
  public String findCredenceDateByOffice(String office,String credenceType,SecurityInfo securityInfo){
    String credenceDate="";
    try{
      credenceDate=treasurerCredenceDAO.queryCredenceDateByOffice(office,credenceType,securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return credenceDate;
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-13
   * 办理页面的确定按钮
   */
  public void saveCashDayClearTa(CashDayClearTaDTO cashDayClearTaDTO,String credenceType,SecurityInfo securityInfo) throws Exception{
    try{
      
      //如果输入的日期大于bb201中日期的一个月，提示请先月结
      String temp_credenceDate1 = cashDayClearTaDTO.getCredenceDate();
      String temp_credenceDate2 = securityInfo.getUserInfo().getBizDate();
      String temp_yearMonth1 = temp_credenceDate1.substring(0, 6);
      String temp_yearMonth2 = temp_credenceDate2.substring(0, 6);
      if(Integer.parseInt(temp_yearMonth1) > Integer.parseInt(temp_yearMonth2)){
        throw new BusinessException("请先月结！");
      }
      
      String temp_credenceDate3 = treasurerCredenceDAO.getFBizDate(securityInfo.getBookId(),securityInfo.getUserName(),credenceType);
      if(!temp_credenceDate3.equals("")){
        if(Integer.parseInt(temp_credenceDate1) < Integer.parseInt(temp_credenceDate3)){
          throw new BusinessException("该凭证日期已记账!");
        }
      }
      
      
      
      String credenceNum="";
      //输入的结算号在FN210.SETT_NUM是否存在
      List list=treasurerCredenceDAO.queryTreasurerCredenceBySettNum(cashDayClearTaDTO.getSettNum().trim(),cashDayClearTaDTO.getSubjectCode().trim(),securityInfo,"");
      if(list.size()>0){
        throw new BusinessException("该结算号已存在！");
      }else{
        //插入fn210表
        TreasurerCredence treasurerCredence=new TreasurerCredence();
        treasurerCredence.setBookId(securityInfo.getBookId());
        treasurerCredence.setSubjectCode(cashDayClearTaDTO.getSubjectCode().trim());
        treasurerCredence.setDebit(cashDayClearTaDTO.getDebit());
        treasurerCredence.setCredit(cashDayClearTaDTO.getCredit());
        //判断统一核算或者独立核算
        int settleType = securityInfo.getFnSettleType();
        if(settleType==0){
          credenceNum=fnDocNumMaintainDAO.getFnDocNumdocNum(null, cashDayClearTaDTO.getCredenceDate().substring(0,6), "1", securityInfo.getBookId());
        }else{
          credenceNum=fnDocNumMaintainDAO.getFnDocNumdocNum(cashDayClearTaDTO.getOffice().trim(), cashDayClearTaDTO.getCredenceDate().substring(0,6), "1", securityInfo.getBookId());
        }
        if(credenceNum!=null)
        {
          treasurerCredence.setCredenceNum(credenceNum);
        }
        treasurerCredence.setSettType(cashDayClearTaDTO.getSettType().trim());
        treasurerCredence.setSettNum(cashDayClearTaDTO.getSettNum().trim());
        treasurerCredence.setSummray(cashDayClearTaDTO.getSummray().trim());
        treasurerCredence.setCredenceCharacter(cashDayClearTaDTO.getCredenceCharacter().trim());
        treasurerCredence.setOffice(cashDayClearTaDTO.getOffice().trim());
        treasurerCredence.setCredenceType(credenceType);
        treasurerCredence.setCredenceDate(cashDayClearTaDTO.getCredenceDate());
        treasurerCredence.setSettDate(cashDayClearTaDTO.getSettDate());
        treasurerCredence.setDopsn(cashDayClearTaDTO.getDopsn().trim());
        treasurerCredence.setMakebill(securityInfo.getUserName().trim());
        treasurerCredence.setCredenceSt("0");
        treasurerCredenceDAO.insert(treasurerCredence);
        //插入fn311表
        FnOperateLog fnOperateLog=new FnOperateLog();
        fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
        if(credenceType.equals("0")){
          fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR));
        }
        if(credenceType.equals("1")){
          fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
        }
        fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setBookId(securityInfo.getBookId());
        fnOperateLogDAO.insert(fnOperateLog);
        //插入fn310表
        FnBizActivityLog fnBizActivityLog=new FnBizActivityLog();
        fnBizActivityLog.setCredenceNum(credenceNum);
        fnBizActivityLog.setCredenceType("1");
        fnBizActivityLog.setCredenceDate(cashDayClearTaDTO.getCredenceDate());
        fnBizActivityLog.setOffice(cashDayClearTaDTO.getOffice().trim());
        fnBizActivityLog.setAction("0");
        fnBizActivityLog.setOpTime(new Date());
        fnBizActivityLog.setOperator(securityInfo.getUserName());
        fnBizActivityLog.setBookId(securityInfo.getBookId());
        fnBizActivityLogDAO.insert(fnBizActivityLog);
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 现金日记账--自动转账页面
   * @author 郭婧平
   * 2007-10-15
   * 查询页面所显示的数据
   */
  public Object[] findCashDayClearTbList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception{
    Object obj[]=new Object[3];
    List resultList=new ArrayList();
    try{
      CashDayClearTbFindDTO cashDayClearTbFindDTO=(CashDayClearTbFindDTO)pagination.getQueryCriterions().get("cashDayClearTbFindDTO");
      if (cashDayClearTbFindDTO==null) {
        cashDayClearTbFindDTO=new CashDayClearTbFindDTO();
      }
      String orderBy=(String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize(); 
      int page = pagination.getPage();
      List list=accountantCredenceDAO.queryCashDayClearTbList(cashDayClearTbFindDTO,credenceType,securityInfo,orderBy,order,start,pageSize,page);
      for(int i=0;i<list.size();i++){
        CashDayClearTbShowListDTO cashDayClearTbShowListDTO=(CashDayClearTbShowListDTO)list.get(i);
        if((!cashDayClearTbShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTbShowListDTO.getCredenceNum().equals(""))){
          cashDayClearTbShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTbShowListDTO.getCredenceCharacter())+"-"+
              cashDayClearTbShowListDTO.getCredenceNum());
        }else if((!cashDayClearTbShowListDTO.getCredenceNum().equals(""))&&cashDayClearTbShowListDTO.getCredenceCharacter().equals("")){
          cashDayClearTbShowListDTO.setCredenceChaNum(cashDayClearTbShowListDTO.getCredenceNum());
        }
        cashDayClearTbShowListDTO.setTemp_credenceChaNum(cashDayClearTbShowListDTO.getCredenceCharacter()+"-"+
            cashDayClearTbShowListDTO.getCredenceNum());
        if(!cashDayClearTbShowListDTO.getSummary().equals("")){
          cashDayClearTbShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTbShowListDTO.getSummary()));
        }
        resultList.add(cashDayClearTbShowListDTO);
      }
      List countList=accountantCredenceDAO.queryCashDayClearTbListCount(cashDayClearTbFindDTO,credenceType, securityInfo);
      BigDecimal debitSum=new BigDecimal(0.00);
      BigDecimal creditSum=new BigDecimal(0.00);
      if(countList.size()>0){
        for(int i=0;i<countList.size();i++){
          CashDayClearTbShowListDTO cashDayClearTbShowListDTO=(CashDayClearTbShowListDTO)countList.get(i);
          debitSum=debitSum.add(cashDayClearTbShowListDTO.getDebit());
          creditSum=creditSum.add(cashDayClearTbShowListDTO.getCredit());
        }
      }
      int count=countList.size();
      pagination.setNrOfElements(count);
      cashDayClearTbFindDTO.setDebitSum(debitSum);
      cashDayClearTbFindDTO.setCreditSum(creditSum);
      obj[0]=resultList;
      obj[1]=cashDayClearTbFindDTO;
      obj[2]=countList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 现金日记账--自动转账页面
   * @author 郭婧平
   * 2007-10-16
   * 转账按钮
   */
  public void cashDayClearTaTransfers(String[] rowArray,String credenceType,SecurityInfo securityInfo) throws Exception{
    try{
      String credenceId="";
      List credenceIdList=new ArrayList();
      for(int i=0;i<rowArray.length;i++){
        credenceId=credenceId+rowArray[i]+",";
        credenceIdList.add(rowArray[i]);
      }
      credenceId.lastIndexOf(",");
      //判断所选记录是否FN201.CASH_ACC_ST =0
      List list=accountantCredenceDAO.queryAccountantCredenceByCredenceId(credenceIdList);
      if(list.size()>0){
        throw new BusinessException("有记录已转账，转账失败!");
      }
      //调用存储过程
      //credenceType为0时，说明是现金日记账的转账；为1时，说明是银行存款日记账的转账
      String makeBill=securityInfo.getUserName();
      String opIp=securityInfo.getUserIp();
      String opModel="";
      if(credenceType.equals("0")){
        opModel=String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_AUTOCASHDAYCLEAR);
      }
      if(credenceType.equals("1")){
        opModel=String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_AUTOBANKDAYCLEAR);
      }
      treasurerCredenceDAO.transfers(credenceId,credenceType,makeBill,opIp,opModel);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("转账失败!");
    }
  }
  /**
   * 现金日记账--维护页面
   * @author 郭婧平
   * 2007-10-18
   * 显示页面的数据
   */
  public Object[] findCashDayClearTcList(String credenceType,Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object obj[]=new Object[3];
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
      }
      List officeList1 = null;
      try {
        // 取出用户权限办事处
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      String orderBy=(String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize(); 
      int page = pagination.getPage();
      List list=treasurerCredenceDAO.queryCashDayClearTcList(cashDayClearTcFindDTO, officeList1,credenceType, securityInfo, orderBy, order, start, pageSize, page);
      List resultList=new ArrayList();
      if(list.size()>0){
        for(int i=0;i<list.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)list.get(i);
          cashDayClearTcShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer.parseInt(cashDayClearTcShowListDTO.getCredenceSt()), BusiConst.CREDSTATE));
          if((!cashDayClearTcShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTcShowListDTO.getCredenceNum().equals(""))){
            cashDayClearTcShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getCredenceCharacter())+"-"+
                cashDayClearTcShowListDTO.getCredenceNum());
          }else if((!cashDayClearTcShowListDTO.getCredenceNum().equals(""))&&cashDayClearTcShowListDTO.getCredenceCharacter().equals("")){
            cashDayClearTcShowListDTO.setCredenceChaNum(cashDayClearTcShowListDTO.getCredenceNum());
          }
          if(!cashDayClearTcShowListDTO.getAcredenceId().equals("")){
            Object[] object=accountantCredenceDAO.queryByCredenceId(cashDayClearTcShowListDTO.getAcredenceId());
            cashDayClearTcShowListDTO.setTemp_credenceChaNum(cashDayClearTcShowListDTO.getCredenceCharacter()+"-"+
                object[0].toString());
            cashDayClearTcShowListDTO.setOffice(object[1].toString());
          }
          if(!cashDayClearTcShowListDTO.getSummary().equals("")){
            cashDayClearTcShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSummary()));
          }
          if(!cashDayClearTcShowListDTO.getSettType().equals("")){
            cashDayClearTcShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSettType()));
          }
          resultList.add(cashDayClearTcShowListDTO);
        }
      }
      List countList=treasurerCredenceDAO.queryCashDayClearTcListCount(cashDayClearTcFindDTO, officeList1,credenceType, securityInfo);
      BigDecimal debitSum=new BigDecimal(0.00);
      BigDecimal creditSum=new BigDecimal(0.00);
      if(countList.size()>0){
        for(int i=0;i<countList.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)countList.get(i);
          debitSum=debitSum.add(cashDayClearTcShowListDTO.getDebit());
          creditSum=creditSum.add(cashDayClearTcShowListDTO.getCredit());
        }
      }
      int count=countList.size();
      pagination.setNrOfElements(count);
      cashDayClearTcFindDTO.setDebitSum(debitSum);
      cashDayClearTcFindDTO.setCreditSum(creditSum);
      obj[0]=resultList;
      obj[1]=cashDayClearTcFindDTO;
      obj[2]=countList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 现金日记账--维护页面
   * @author 郭婧平
   * 2007-10-18
   * 删除按钮
   */
  public void deleteCashDayClearTcList(String credenceId,String credenceType,SecurityInfo securityInfo) throws Exception{
    try{
      //查询该条记录在fn210表中是否存在
      Integer id=treasurerCredenceDAO.queryTreasurerCredenceById(new Integer(credenceId));
      if(id==null){
        throw new BusinessException("该条记录已经不存在，不能再删除！");
      }
      //判断是否CREDENCE_ST=0
      List checkList=treasurerCredenceDAO.queryTreasurerCredenceByCredenceSt(credenceId);
      if(checkList.size()==0){
        throw new BusinessException("该记录已记账，不能再删除！");
      }
      //判断记录FN210.ACREDENCE_ID是否为空
      String acredenceId="";
      String credenceNum="";
      String credenceDate="";
      String office="";
      TreasurerCredence treasurerCredence1=treasurerCredenceDAO.queryAcredenceIdByCredenceId(credenceId);
      if(treasurerCredence1!=null){
        acredenceId=treasurerCredence1.getAcredenceId();
        credenceNum=treasurerCredence1.getCredenceNum();
        credenceDate=treasurerCredence1.getCredenceDate();
        office=treasurerCredence1.getOffice();
        if(acredenceId!=null){
          if(credenceType.equals("0")){
            //更新fn201表中的cash_acc_st字段为0
            treasurerCredenceDAO.updateAccountantCredence(acredenceId,"0");
          }
          if(credenceType.equals("1")){
            //更新fn201表中的bank_acc_st字段为0
            treasurerCredenceDAO.updateBankAccountantCredence(acredenceId,"0");
          }
        }
      }
      //删除fn210记录
      treasurerCredenceDAO.deleteTreasurerCredence(credenceId);
      //插入fn302表:将删除业务的凭证号插入到作废凭证号表中
      FnDocNumCancel fnDocNumCancel=new FnDocNumCancel();
      fnDocNumCancel.setBookId(securityInfo.getBookId());
      fnDocNumCancel.setCancelcredenceid(credenceNum);
      fnDocNumCancel.setBizYearmonth(credenceDate.substring(0,6));
      fnDocNumCancel.setCredenceNumType("1");
      if(securityInfo.getFnSettleType()==0){
        fnDocNumCancel.setOffice("");
      }else{
        fnDocNumCancel.setOffice(office);
      }
      fnDocNumCancelDAO.insert(fnDocNumCancel);
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      if(credenceType.equals("0")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
      }
      if(credenceType.equals("1")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN));
      }
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      //删除fn310表中的数据（CREDENCE_NUM=所选记录的凭证号 and CREDENCE_DATE=凭证日期 and  OFFICE=所属办事处 and CREDENCE_TYPE=1）
      fnBizActivityLogDAO.deleteFnBizActivityLog(credenceNum, credenceDate, office,securityInfo);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("删除失败!");
    }
  }
  /**
   * 现金日记账--维护页面
   * @author 郭婧平
   * 2007-11-29
   * 全部删除按钮
   */
  public void deleteCashDayClearTcListAll(List list,SecurityInfo securityInfo,String credenceType) throws Exception{
    try{
      for(int i=0;i<list.size();i++){
        CashDayClearTcShowListDTO cashDayClearTcShowListDTO = (CashDayClearTcShowListDTO)list.get(i);
        String credenceId=cashDayClearTcShowListDTO.getCredenceId();
        //查询该条记录在fn210表中是否存在
        Integer id=treasurerCredenceDAO.queryTreasurerCredenceById(new Integer(credenceId));
        if(id==null){
          throw new BusinessException("该条记录已经不存在，不能再删除！");
        }
        //判断是否CREDENCE_ST=0
        List checkList=treasurerCredenceDAO.queryTreasurerCredenceByCredenceSt(credenceId);
        if(checkList.size()==0){
          throw new BusinessException("该记录已记账，不能再删除！");
        }
        //判断记录FN210.ACREDENCE_ID是否为空
        String acredenceId="";
        String credenceNum="";
        String credenceDate="";
        String office="";
        TreasurerCredence treasurerCredence1=treasurerCredenceDAO.queryAcredenceIdByCredenceId(credenceId);
        if(treasurerCredence1!=null){
          acredenceId=treasurerCredence1.getAcredenceId();
          credenceNum=treasurerCredence1.getCredenceNum();
          credenceDate=treasurerCredence1.getCredenceDate();
          office=treasurerCredence1.getOffice();
          if(acredenceId!=null){
            if(credenceType.equals("0")){
              //更新fn201表中的cash_acc_st字段为0
              treasurerCredenceDAO.updateAccountantCredence(acredenceId,"0");
            }
            if(credenceType.equals("1")){
              //更新fn201表中的bank_acc_st字段为0
              treasurerCredenceDAO.updateBankAccountantCredence(acredenceId,"0");
            }
          }
        }
        //删除fn210记录
        treasurerCredenceDAO.deleteTreasurerCredence(credenceId);
        //插入fn302表:将删除业务的凭证号插入到作废凭证号表中
        FnDocNumCancel fnDocNumCancel=new FnDocNumCancel();
        fnDocNumCancel.setBookId(securityInfo.getBookId());
        fnDocNumCancel.setCancelcredenceid(credenceNum);
        fnDocNumCancel.setBizYearmonth(credenceDate.substring(0,6));
        fnDocNumCancel.setCredenceNumType("1");
        if(securityInfo.getFnSettleType()==0){
          fnDocNumCancel.setOffice("");
        }else{
          fnDocNumCancel.setOffice(office);
        }
        fnDocNumCancelDAO.insert(fnDocNumCancel);
        //删除fn310表中的数据（CREDENCE_NUM=所选记录的凭证号 and CREDENCE_DATE=凭证日期 and  OFFICE=所属办事处 and CREDENCE_TYPE=1）
        fnBizActivityLogDAO.deleteFnBizActivityLog(credenceNum, credenceDate, office,securityInfo);
      }
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      if(credenceType.equals("0")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
      }
      if(credenceType.equals("1")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN));
      }
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETEALL));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("全部删除失败!");
    }
  }
  /**
   * 现金日记账--维护页面--修改
   * @author 郭婧平
   * 2007-10-19
   * 根据fn210.CREDENCE_ID查询相应的数据
   */
  public CashDayClearTaDTO findModifyInfo(String credenceId,SecurityInfo securityInfo) throws Exception{
    CashDayClearTaDTO cashDayClearTaDTO=null;
    try{
      //查询该条记录在fn210表中是否存在
      cashDayClearTaDTO=treasurerCredenceDAO.queryTreasurerCredenceByCredenceId(credenceId);
      if(cashDayClearTaDTO==null){
        throw new BusinessException("该条记录已经不存在，不能再修改！");
      }
      //判断是否CREDENCE_ST=0
      List checkList=treasurerCredenceDAO.queryTreasurerCredenceByCredenceSt(credenceId);
      if(checkList.size()==0){
        throw new BusinessException("该记录已记账，不能再修改！");
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
    

    return cashDayClearTaDTO;
  }
  /**
   * 现金日记账--办理页面--修改
   * @author 郭婧平
   * 2007-10-19
   */
  public void modifyInfo(CashDayClearTaDTO newCashDayClearTaDTO,CashDayClearTaDTO oldCashDayClearTaDTO,String credenceType,SecurityInfo securityInfo)throws Exception{
    try{
      
      //如果输入的日期大于bb201中日期的一个月，提示请先月结
      String temp_credenceDate1 = newCashDayClearTaDTO.getCredenceDate();
      String temp_credenceDate2 = securityInfo.getUserInfo().getBizDate();
      String temp_yearMonth1 = temp_credenceDate1.substring(0, 6);
      String temp_yearMonth2 = temp_credenceDate2.substring(0, 6);
      if(Integer.parseInt(temp_yearMonth1) > Integer.parseInt(temp_yearMonth2)){
        throw new BusinessException("请先月结！");
      }
      
      String temp_credenceDate3 = treasurerCredenceDAO.getFBizDate(securityInfo.getBookId(),securityInfo.getUserName(),credenceType);
      if(!temp_credenceDate3.equals("")){
        if(Integer.parseInt(temp_credenceDate1) < Integer.parseInt(temp_credenceDate3)){
          throw new BusinessException("该凭证日期已记账!");
        }
      }
      
      String credenceNum="";
      //输入的结算号在FN210.SETT_NUM是否存在
      List list=treasurerCredenceDAO.queryTreasurerCredenceBySettNum(newCashDayClearTaDTO.getSettNum().trim(),newCashDayClearTaDTO.getSubjectCode().trim(),securityInfo,oldCashDayClearTaDTO.getCredenceId());
      if(list.size()>0){
        throw new BusinessException("该结算号已存在！");
      }
      //删除fn210记录
      treasurerCredenceDAO.deleteTreasurerCredence(oldCashDayClearTaDTO.getCredenceId());
      //删除fn310表中的数据（CREDENCE_NUM=所选记录的凭证号 and CREDENCE_DATE=凭证日期 and  OFFICE=所属办事处）
      fnBizActivityLogDAO.deleteFnBizActivityLog(oldCashDayClearTaDTO.getCredenceNum(), newCashDayClearTaDTO.getCredenceDate(), oldCashDayClearTaDTO.getOffice(),securityInfo);
      int settleType = securityInfo.getFnSettleType();
      if(settleType==1){
        fnDocNumCancelDAO.insertDocNumCancel(oldCashDayClearTaDTO.getCredenceNum(),
            oldCashDayClearTaDTO.getOffice(), oldCashDayClearTaDTO
                .getCredenceDate().substring(0, 6), "1",securityInfo.getBookId());
      }else{
        fnDocNumCancelDAO.insertDocNumCancel(oldCashDayClearTaDTO.getCredenceNum(),
            "", oldCashDayClearTaDTO
                .getCredenceDate().substring(0, 6), "1", securityInfo.getBookId()); 
      }
      
      //插入fn210表
      TreasurerCredence treasurerCredence=new TreasurerCredence();
      treasurerCredence.setBookId(securityInfo.getBookId());
      treasurerCredence.setSubjectCode(newCashDayClearTaDTO.getSubjectCode().trim());
      treasurerCredence.setDebit(newCashDayClearTaDTO.getDebit());
      treasurerCredence.setCredit(newCashDayClearTaDTO.getCredit());
      credenceNum=fnDocNumMaintainDAO.getFnDocNumdocNum(oldCashDayClearTaDTO.getOffice(), newCashDayClearTaDTO.getCredenceDate().substring(0,6), "1", securityInfo.getBookId());
      if(credenceNum!=null)
      {
        treasurerCredence.setCredenceNum(credenceNum);
      }
      treasurerCredence.setSettType(newCashDayClearTaDTO.getSettType().trim());
      treasurerCredence.setSettNum(newCashDayClearTaDTO.getSettNum().trim());
      treasurerCredence.setSummray(newCashDayClearTaDTO.getSummray().trim());
      treasurerCredence.setCredenceCharacter(newCashDayClearTaDTO.getCredenceCharacter().trim());
      treasurerCredence.setOffice(oldCashDayClearTaDTO.getOffice());
      treasurerCredence.setCredenceType(credenceType);
      treasurerCredence.setCredenceDate(newCashDayClearTaDTO.getCredenceDate());
      treasurerCredence.setSettDate(newCashDayClearTaDTO.getSettDate());
      treasurerCredence.setDopsn(newCashDayClearTaDTO.getDopsn().trim());
      treasurerCredence.setMakebill(securityInfo.getUserName());
      treasurerCredence.setCredenceSt("0");
      treasurerCredence.setAcredenceId(oldCashDayClearTaDTO.getAcredenceId());
      treasurerCredenceDAO.insert(treasurerCredence);
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      if(credenceType.equals("0")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR));
      }
      if(credenceType.equals("1")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
      }
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      //插入fn310表
      FnBizActivityLog fnBizActivityLog=new FnBizActivityLog();
      fnBizActivityLog.setCredenceNum(credenceNum);
      fnBizActivityLog.setCredenceType("1");
      fnBizActivityLog.setCredenceDate(newCashDayClearTaDTO.getCredenceDate());
      fnBizActivityLog.setOffice(oldCashDayClearTaDTO.getOffice());
      fnBizActivityLog.setAction("0");
      fnBizActivityLog.setOpTime(new Date());
      fnBizActivityLog.setOperator(securityInfo.getUserName());
      fnBizActivityLog.setBookId(securityInfo.getBookId());
      fnBizActivityLogDAO.insert(fnBizActivityLog);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 现金日记账--维护页面--打印
   * @author 郭婧平
   * 2007-10-19
   */
  public List findCashDayClearTcPrintList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception{
    List resultList=new ArrayList();
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
      }
      List officeList1 = null;
      try {
        // 取出用户权限办事处
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      List list=treasurerCredenceDAO.queryCashDayClearTcListPrint(cashDayClearTcFindDTO, officeList1,credenceType,securityInfo);
      if(list.size()>0){
        for(int i=0;i<list.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)list.get(i);
          cashDayClearTcShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer.parseInt(cashDayClearTcShowListDTO.getCredenceSt()), BusiConst.CREDSTATE));
          if((!cashDayClearTcShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTcShowListDTO.getCredenceNum().equals(""))){
            cashDayClearTcShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getCredenceCharacter())+
                cashDayClearTcShowListDTO.getCredenceNum());
          }else if((!cashDayClearTcShowListDTO.getCredenceNum().equals(""))&&cashDayClearTcShowListDTO.getCredenceCharacter().equals("")){
            cashDayClearTcShowListDTO.setCredenceChaNum(cashDayClearTcShowListDTO.getCredenceNum());
          }
          if(!cashDayClearTcShowListDTO.getSummary().equals("")){
            cashDayClearTcShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSummary()));
          }
          if(!cashDayClearTcShowListDTO.getSettType().equals("")){
            cashDayClearTcShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSettType()));
          }
          if(!cashDayClearTcShowListDTO.getMakebill().equals("")){
            cashDayClearTcShowListDTO.setMakebill(securityDAO.queryByUserid(cashDayClearTcShowListDTO.getMakebill()));
          }
          resultList.add(cashDayClearTcShowListDTO);
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return resultList;
  }
  public List findCashDayClearTcPrintList_wsh(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception{
    List resultList=new ArrayList();
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
      }
      List officeList1 = null;
      try {
        // 取出用户权限办事处
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      List list=treasurerCredenceDAO.queryCashDayClearTcListPrint_wsh(cashDayClearTcFindDTO, officeList1,credenceType,securityInfo);
      if(list.size()>0){
        for(int i=0;i<list.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)list.get(i);
          cashDayClearTcShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer.parseInt(cashDayClearTcShowListDTO.getCredenceSt()), BusiConst.CREDSTATE));
          if((!cashDayClearTcShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTcShowListDTO.getCredenceNum().equals(""))){
            cashDayClearTcShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getCredenceCharacter())+
                cashDayClearTcShowListDTO.getCredenceNum());
          }else if((!cashDayClearTcShowListDTO.getCredenceNum().equals(""))&&cashDayClearTcShowListDTO.getCredenceCharacter().equals("")){
            cashDayClearTcShowListDTO.setCredenceChaNum(cashDayClearTcShowListDTO.getCredenceNum());
          }
          if(!cashDayClearTcShowListDTO.getSummary().equals("")){
            cashDayClearTcShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSummary()));
          }
          if(!cashDayClearTcShowListDTO.getSettType().equals("")){
            cashDayClearTcShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSettType()));
          }
          if(!cashDayClearTcShowListDTO.getMakebill().equals("")){
            cashDayClearTcShowListDTO.setMakebill(securityDAO.queryByUserid(cashDayClearTcShowListDTO.getMakebill()));
          }
          resultList.add(cashDayClearTcShowListDTO);
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return resultList;
  }
  /**
   * 现金日记账--办理页面
   * @author 郭婧平
   * 2007-11-26
   * 根据bookid查询账套状态
   */
  public String findBookSt(SecurityInfo securityInfo) throws Exception{
    String bookSt="";
    try{
      bookSt=bookDAO.getBookSt(securityInfo.getBookId());
    }catch(Exception e){
      e.printStackTrace();
    }
    return bookSt;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }
  public void setFnDocNumMaintainDAO(FnDocNumMaintainDAO fnDocNumMaintainDAO) {
    this.fnDocNumMaintainDAO = fnDocNumMaintainDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  public void setFnBizActivityLogDAO(FnBizActivityLogDAO fnBizActivityLogDAO) {
    this.fnBizActivityLogDAO = fnBizActivityLogDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }
  public void setFnDocNumCancelDAO(FnDocNumCancelDAO fnDocNumCancelDAO) {
    this.fnDocNumCancelDAO = fnDocNumCancelDAO;
  }
  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }
  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
}
