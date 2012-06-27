package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.BankCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BankCredence;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccImpContentDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTbShowListDTO;





public class BankCheckAccBS implements IBankCheckAccBS{
  private BookParameterDAO bookParameterDAO=null;
  private BankCredenceDAO bankCredenceDAO=null;
  private FnOperateLogDAO fnOperateLogDAO=null;
  private SubjectDAO subjectDAO=null;
  private BookDAO bookDAO=null;
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  /**
   * 银行对账单
   * @author 郭婧平
   * 2007-10-24
   * 查询fn102表里paramExplain字段的数据
   * 查询条件：paramNum
   */
  public Object[] findParamExplainList(SecurityInfo securityInfo,String temp){
    Object[] obj=new Object[2];
    List summrayList=null;
    List settTypeList=null;
    try{
      summrayList=bookParameterDAO.getParamExplain("4","10", securityInfo);
      if(temp.equals("")){
        settTypeList=bookParameterDAO.getParamExplain("3","", securityInfo);
      }
      obj[0]=summrayList;
      obj[1]=settTypeList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 银行对账单
   * @author 郭婧平
   * 2007-10-24
   * 办理页面的确定按钮
   */
  public void saveBankCheckAccTa(BankCheckAccTaDTO bankCheckAccTaDTO,SecurityInfo securityInfo) throws Exception{
    try{
//      //输入的结算号在FN211.SETT_NUM是否存在
//      List list=bankCredenceDAO.queryBankCredenceBySettNum(bankCheckAccTaDTO.getSettNum().trim(),bankCheckAccTaDTO.getSubjectCode().trim(),securityInfo,"");
//      if(list.size()>0){
//        throw new BusinessException("该结算号已存在！");
//      }else{
//        //插入fn211表
        BankCredence bankCredence=new BankCredence();
        bankCredence.setBookId(securityInfo.getBookId());
        bankCredence.setSubjectCode(bankCheckAccTaDTO.getSubjectCode().trim());
        bankCredence.setDebit(bankCheckAccTaDTO.getDebit());
        bankCredence.setCredit(bankCheckAccTaDTO.getCredit());
        bankCredence.setSettType(bankCheckAccTaDTO.getSettType().trim());
        bankCredence.setSettNum(bankCheckAccTaDTO.getSettNum().trim());
        bankCredence.setSettDate(bankCheckAccTaDTO.getSettDate());
        bankCredence.setSummary(bankCheckAccTaDTO.getSummary().trim());
        bankCredence.setDopsn(bankCheckAccTaDTO.getDopsn().trim());
        bankCredence.setMakebill(securityInfo.getUserName());
        bankCredence.setCredenceType("1");
        bankCredence.setOffice(bankCheckAccTaDTO.getOffice().trim());
        bankCredenceDAO.insert(bankCredence);
        //插入fn311表
        FnOperateLog fnOperateLog=new FnOperateLog();
        fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACC));
        fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setBookId(securityInfo.getBookId());
        fnOperateLogDAO.insert(fnOperateLog);
//      }
//    }catch(BusinessException bex){
//      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 银行对账单--维护页面
   * @author 郭婧平
   * 2007-10-24
   * 查询页面所显示的数据
   */
  public Object[] findBankCheckAccTbList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object obj[]=new Object[3];
    List resultList=new ArrayList();
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
    try{
      BankCheckAccTbFindDTO bankCheckAccTbFindDTO=(BankCheckAccTbFindDTO)pagination.getQueryCriterions().get("bankCheckAccTbFindDTO");
      if (bankCheckAccTbFindDTO==null) {
        bankCheckAccTbFindDTO=new BankCheckAccTbFindDTO();
      }
      String orderBy=(String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize(); 
      int page = pagination.getPage();
      List list=bankCredenceDAO.queryBankCheckAccTbList(bankCheckAccTbFindDTO, officeList1, securityInfo, orderBy, order, start, pageSize, page);
      for(int i=0;i<list.size();i++){
        BankCheckAccTbShowListDTO bankCheckAccTbShowListDTO=(BankCheckAccTbShowListDTO)list.get(i);
        if(!bankCheckAccTbShowListDTO.getSummary().equals("")){
          bankCheckAccTbShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(bankCheckAccTbShowListDTO.getSummary()));
        }
        if(!bankCheckAccTbShowListDTO.getSettType().equals("")){
          bankCheckAccTbShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(bankCheckAccTbShowListDTO.getSettType()));
        }
        resultList.add(bankCheckAccTbShowListDTO);
      }
      List countList=bankCredenceDAO.queryBankCheckAccTbListCount(bankCheckAccTbFindDTO, officeList1, securityInfo);
      BigDecimal debitSum=new BigDecimal(0.00);
      BigDecimal creditSum=new BigDecimal(0.00);
      if(countList.size()>0){
        for(int i=0;i<countList.size();i++){
          BankCheckAccTbShowListDTO bankCheckAccTbShowListDTO=(BankCheckAccTbShowListDTO)countList.get(i);
          debitSum=debitSum.add(bankCheckAccTbShowListDTO.getDebit());
          creditSum=creditSum.add(bankCheckAccTbShowListDTO.getCredit());
        }
      }
      int count=countList.size();
      pagination.setNrOfElements(count);
      bankCheckAccTbFindDTO.setDebitSum(debitSum);
      bankCheckAccTbFindDTO.setCreditSum(creditSum);
      obj[0]=resultList;
      obj[1]=bankCheckAccTbFindDTO;
      obj[2]=countList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 银行对账单--维护页面
   * @author 郭婧平
   * 2007-10-25
   * 删除按钮
   */
  public void deleteBankCheckAccTbList(String credenceId,SecurityInfo securityInfo) throws Exception{
    try{
      //查询该条记录在fn211表中是否存在
      Integer id=bankCredenceDAO.queryBankCredenceById(new Integer(credenceId));
      if(id==null){
        throw new BusinessException("该条记录已经不存在，不能再删除！");
      }
      //删除fn211记录
      bankCredenceDAO.deleteBankCredence(credenceId);
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN));
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("删除失败!");
    }
  }
  /**
   * 银行对账单--维护页面
   * @author 郭婧平
   * 2007-10-25
   * 删除按钮
   */
  public void deleteBankCheckAccTbListAll(List list,SecurityInfo securityInfo) throws Exception{
    try{
      List idList=new ArrayList();
      for(int i=0;i<list.size();i++){
        BankCheckAccTbShowListDTO bankCheckAccTbShowListDTO=(BankCheckAccTbShowListDTO)list.get(i);
        idList.add(new Integer(bankCheckAccTbShowListDTO.getCredenceId()));
      }
      //删除fn211记录
      bankCredenceDAO.deleteBankCredenceAll(idList);
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN));
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
   * 银行对账单--维护页面--修改
   * @author 郭婧平
   * 2007-10-25
   * 根据fn211.CREDENCE_ID查询相应的数据
   */
  public BankCheckAccTaDTO findModifyInfo(String credenceId,SecurityInfo securityInfo) throws Exception{
    BankCheckAccTaDTO bankCheckAccTaDTO=null;
    try{
      //查询该条记录在fn211表中是否存在
      bankCheckAccTaDTO=bankCredenceDAO.queryBankCredenceByCredenceId(credenceId);
      if(bankCheckAccTaDTO==null){
        throw new BusinessException("该条记录已经不存在，不能再修改！");
      }else{
        bankCheckAccTaDTO.setCredenceId(credenceId);
        bankCheckAccTaDTO.setTemp_office(bankCheckAccTaDTO.getOffice());
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
    return bankCheckAccTaDTO;
  }
  /**
   * 银行对账单--办理页面--修改
   * @author 郭婧平
   * 2007-10-25
   */
  public void modifyInfo(BankCheckAccTaDTO bankCheckAccTaDTO,SecurityInfo securityInfo)throws Exception{
    try{
      //输入的结算号在FN211表中是否存在
//      List list=bankCredenceDAO.queryBankCredenceBySettNum(bankCheckAccTaDTO.getSettNum().trim(),bankCheckAccTaDTO.getSubjectCode().trim(),securityInfo,bankCheckAccTaDTO.getCredenceId());
//      if(list.size()>0){
//        throw new BusinessException("该结算号已存在！");
//      }
      //修改fn211表
      bankCredenceDAO.updateBankCredenceByCredenceId(bankCheckAccTaDTO);
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN));
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
//    }catch(BusinessException bex){
//      throw bex;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("修改失败!");
    }
  }
  /**
   * 银行对账单--维护页面--打印
   * @author 郭婧平
   * 2007-10-25
   */
  public List findBankCheckAccPrintList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    List resultList=new ArrayList();
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
    try{
      BankCheckAccTbFindDTO bankCheckAccTbFindDTO=(BankCheckAccTbFindDTO)pagination.getQueryCriterions().get("bankCheckAccTbFindDTO");
      if (bankCheckAccTbFindDTO==null) {
        bankCheckAccTbFindDTO=new BankCheckAccTbFindDTO();
      }
      List list=bankCredenceDAO.queryBankCheckAccTbListPrint(bankCheckAccTbFindDTO, officeList1, securityInfo);
      for(int i=0;i<list.size();i++){
        BankCheckAccTbShowListDTO bankCheckAccTbShowListDTO=(BankCheckAccTbShowListDTO)list.get(i);
        if(!bankCheckAccTbShowListDTO.getSummary().equals("")){
          bankCheckAccTbShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(bankCheckAccTbShowListDTO.getSummary()));
        }
        if(!bankCheckAccTbShowListDTO.getSettType().equals("")){
          bankCheckAccTbShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(bankCheckAccTbShowListDTO.getSettType()));
        }
        resultList.add(bankCheckAccTbShowListDTO);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return resultList;
  }
  /**
   * 银行对账单--导入
   * @author 郭婧平
   * 2007-10-26
   * 办理页面的导入按钮
   */
  public void importsBankCheckAcc(List bankCheckAccImpContent,String office, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try{
      BankCheckAccImpContentDTO bankCheckAccImpContentDTO = null;
      UtilRule utilRule = new UtilRule();
      BankCredence bankCredence=null;
      for (int i = 1; i < bankCheckAccImpContent.size(); i++) {
        bankCheckAccImpContentDTO = (BankCheckAccImpContentDTO) bankCheckAccImpContent
            .get(i);
        String settDate = bankCheckAccImpContentDTO.getSettdate();
        String subjectCode = bankCheckAccImpContentDTO.getSubjectcode();
        String debit = bankCheckAccImpContentDTO.getDebit();
        String credit = bankCheckAccImpContentDTO.getCredit();
        String settType = bankCheckAccImpContentDTO.getSetttype();
        String settNum = bankCheckAccImpContentDTO.getSettnum();
        String summary=bankCheckAccImpContentDTO.getSummary();
        String dopsn=bankCheckAccImpContentDTO.getDopsn();
//        if(settNum!=null){
//          List list1=bankCredenceDAO.queryBankCredenceBySettNum(settNum,subjectCode,securityInfo,"");
//          if(list1.size()>0){
//            i=i+1;
//            throw new BusinessException("第" + i + "行结算号已存在！！");
//          }
//        }
        if(settNum==null||settNum.equals("")){
          i=i+1;
          throw new BusinessException("第" + i + "行结算号没有录入！！");
        }
        if(subjectCode == null||subjectCode.equals("")){
          i=i+1;
          throw new BusinessException("第" + i + "行科目代码没有录入！！");
        }
        if(subjectCode!=null&&!subjectCode.equals("")){
          String subjectId=subjectDAO.is_CodeIn_GJP(subjectCode, "0", "1", securityInfo);
          if(subjectId==null||subjectId.equals("")){
            i=i+1;
            throw new BusinessException("第" + i + "行科目代码不正确！！");
          }
        }
        if (debit == null||debit.equals("")){
          i=i+1;
          throw new BusinessException("第" + i + "行银行借方金额没有录入！！");
        }
        if (credit == null||credit.equals("")) {
          i=i+1;
          throw new BusinessException("第" + i + "行银行贷方金额没有录入！！");
        }
        if ((!credit.equals("0"))&&(!debit.equals("0"))) {
          i=i+1;
          throw new BusinessException("第" + i + "行银行贷方金额和银行借方金额只能有一方为0，另一方大于0！！");
        }
        if(!debit.equals("0")){
          Pattern p = Pattern.compile("[0-9]{1}([0-9]){0,99}");
          Matcher m = p.matcher(debit);
          if (m.find() == false) {
            i=i+1;
            throw new BusinessException("第" + i + "行请正确录入银行借方金额!!");
          }else if (utilRule.moneyRule(debit, 999999999, 2) == false) {
            i=i+1;
            throw new BusinessException("第" + i + "行银行借方金额录入不正确！！");
          }
        }
        if(!credit.equals("0")){
          Pattern p = Pattern.compile("[0-9]{1}([0-9]){0,99}");
          Matcher m = p.matcher(credit);
          if (m.find() == false) {
            i=i+1;
            throw new BusinessException("第" + i + "行请正确录入银行贷方金额!!");
          }else if (utilRule.moneyRule(credit, 999999999, 2) == false) {
            i=i+1;
            throw new BusinessException("第" + i + "行银行贷方金额录入不正确！！");
          }
        }
        if(settDate!=null&&!settDate.equals("")){
          try{
            bankCredenceDAO.checkDate(settDate);
          }catch(Exception e){
            i=i+1;
            throw new BusinessException("第" + i + "行请正确录入结算日期！格式如：20070101");
          }
        }else{
          i=i+1;
          throw new BusinessException("第" + i + "行结算日期没有录入！！");
        }
        //插入fn211表
        bankCredence=new BankCredence();
        bankCredence.setBookId(securityInfo.getBookId());
        bankCredence.setSubjectCode(subjectCode);
        bankCredence.setDebit(new BigDecimal(debit));
        bankCredence.setCredit(new BigDecimal(credit));
        bankCredence.setSettType(settType);
        bankCredence.setSettNum(settNum);
        bankCredence.setSettDate(settDate);
        bankCredence.setMakebill(securityInfo.getUserName());
        bankCredence.setCredenceType("1");
        bankCredence.setOffice(office);
        bankCredence.setSummary(summary);
        bankCredence.setDopsn(dopsn);
        bankCredenceDAO.insert(bankCredence);
      }
      //插入fn311表
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN));
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZBLOG_ACTION_IMP));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 银行对账单
   * @author 郭婧平
   * 2007-10-27
   * 查看该科目代码在数据库中是否存在
   * 条件为，该科目存在并且为末级科目，科目属性为所传credenceType参数值
   */
  public String checkSubjectCode(String subjectCode,String credenceType,SecurityInfo securityInfo){
    String subjectId="";
    if(!subjectCode.equals("")){
      subjectId=subjectDAO.is_CodeIn_GJP(subjectCode, "0", credenceType, securityInfo);
    }
    return subjectId;
  }
  /**
   * 银行对账单--办理页面
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
  public void setBankCredenceDAO(BankCredenceDAO bankCredenceDAO) {
    this.bankCredenceDAO = bankCredenceDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }
  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }
}
