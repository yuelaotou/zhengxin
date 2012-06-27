package org.xpup.hafmis.sysfinance.bookmng.datainitialize.business;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.datainitialize.bsinterface.IDatainitializeBS;
import org.xpup.hafmis.sysfinance.bookmng.datainitialize.dto.DatainitializeDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : 初始数据
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-16-2007
 */
public class DatainitializeBS implements IDatainitializeBS {
  
  private AccountantCredenceDAO accountantCredenceDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;
  private BookParameterDAO bookParameterDAO = null;
  private SubjectDAO subjectDAO = null;
  private BookDAO bookDAO = null;
  
  public AccountantCredenceDAO getAccountantCredenceDAO() {
    return accountantCredenceDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  

  /**
   * 获得科目代码和科目名称
   * 张列
   */
  public List getDatainitialize(String bookId) throws Exception {
    List list = null;
    try {
      list = accountantCredenceDAO.getDatainitalizeInfo(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 获得累计借方和累计贷方
   * 张列
   */
  public List getLendsMoney(String bookId,String officeName) throws Exception {
    List list = null;
    List temp_list = new ArrayList();
    try {
      if(officeName.equals("")){
        list = this.getDatainitialize(bookId);
        return list;
      }
      //获得科目代码和科目名称
      list = this.getDatainitialize(bookId);
      if(list == null){
        return list;
      }
      Iterator it = list.iterator();
      while(it.hasNext()){
        DatainitializeDTO temp_datainitializeDTO = (DatainitializeDTO)it.next();
        String subjectCode = temp_datainitializeDTO.getSubjectCode();
        DatainitializeDTO datainitializeDTO = accountantCredenceDAO.getLendsMoney(subjectCode, officeName,bookId);
        datainitializeDTO.setSubjectCode(temp_datainitializeDTO.getSubjectCode());
        datainitializeDTO.setSubjectName(temp_datainitializeDTO.getSubjectName());
        temp_list.add(datainitializeDTO);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return temp_list;
  }
  
  /**
   * * 删除FN201
   * FN201.SUMMAY=1 and FN201.OFFICE=所选办事处的记录
   * 插入FN311
      OP_SYS=财务系统
      OP_MODEL=初始数据
      OP_BUTTON=2
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   *插入FN201:
      BOOK_ID=所属账套
      SUBJECT_CODE=科目代码
      SUMMAY=1
      DEBIT=累计借方
      CREDIT=累计贷方
      MAKEBILL=操作员
      OFFICE=所选办事处
   * @param datainitializeDTO 张列
   * @param securityInfo
   * @throws Exception
   */
  public void deleteSummaryOffice(List list,SecurityInfo securityInfo) throws Exception {
    try {
      DatainitializeDTO datainitializeDTO1 = (DatainitializeDTO)list.get(0);
      //删除 FN201
      accountantCredenceDAO.delete_ZL(datainitializeDTO1.getBookId(), datainitializeDTO1.getOfficeName());
      accountantCredenceDAO.delete_ZL1(datainitializeDTO1.getBookId(), datainitializeDTO1.getOfficeName());
      accountantCredenceDAO.delete_ZL2(datainitializeDTO1.getBookId(), datainitializeDTO1.getOfficeName());
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_DATAINITIALIZE+"");
      fnOperateLog.setOpButton("2");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(datainitializeDTO1.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      Iterator it = list.iterator();
      while(it.hasNext()){
        DatainitializeDTO datainitializeDTO = (DatainitializeDTO)it.next();
//        if(datainitializeDTO.getDebit().equals("0") && datainitializeDTO.getCredit().equals("0")){
//          continue;
//        }else{
          //插入 FN201
          AccountantCredence accountantCredence = new AccountantCredence();
          accountantCredence.setBookId(datainitializeDTO.getBookId());
          accountantCredence.setSubjectCode(datainitializeDTO.getSubjectCode());
          accountantCredence.setSummay(bookParameterDAO.getSummay(datainitializeDTO.getBookId()));

          accountantCredence.setDebit(new BigDecimal(datainitializeDTO.getDebit()));
          accountantCredence.setCredit(new BigDecimal(datainitializeDTO.getCredit()));
          
          accountantCredence.setMakebill(securityInfo.getUserName());
          accountantCredence.setOffice(datainitializeDTO.getOfficeName());
          accountantCredence.setCredenceSt("2");
          //年
          String date1 = bookDAO.getUseYearmonth(datainitializeDTO.getBookId())+"01";  
          DateFormat df = new SimpleDateFormat("yyyyMMdd");
          String year = "";
          try {  
            Date d1 = df.parse(date1);  
            //System.out.println("d1=="+df.format(d1));  
            Calendar  g = Calendar.getInstance();  
            g.setTime(d1);
            g.add(Calendar.DATE,-1);
            Date d2 = g.getTime();  
            //System.out.println("d2======="+df.format(d2)); 
            year = df.format(d2);
          } catch (ParseException exx) {              
            exx.printStackTrace();  
          }
          accountantCredence.setCredenceDate(year);
          accountantCredence.setSettDate(year);
          //如果是(损益类科目)直接把FN201中的(损益结转字段置为2)
          if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("4")){
            accountantCredence.setIncDecSt("2");
          }
          if(subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("0")){
            //如果是(银行属性的科目)直接把(银行账结转状态置为2)
            accountantCredence.setBankAccSt("2");
          }
          if (subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("1")){
            //如果是(现金属性的科目)直接把(现金账结转状态置为2)
            accountantCredence.setCashAccSt("2");
          }
          accountantCredenceDAO.insert(accountantCredence);
//        }
      }
      Iterator it1 = list.iterator();
      while(it1.hasNext()){
        DatainitializeDTO datainitializeDTO11 = (DatainitializeDTO)it1.next();
//        if(datainitializeDTO11.getYesterdayRemainingSum().equals("0") || datainitializeDTO11.getBalaceDirection().equals("2")){
//          continue;
//        }
        AccountantCredence accountantCredence = new AccountantCredence();
        accountantCredence.setBookId(datainitializeDTO11.getBookId());
        accountantCredence.setSubjectCode(datainitializeDTO11.getSubjectCode());
        accountantCredence.setSummay(bookParameterDAO.getSummay4(datainitializeDTO11.getBookId()));
        if(datainitializeDTO11.getBalaceDirection().equals("0")){
          BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getDebit())).add(new BigDecimal(datainitializeDTO11.getCredit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayDebit())).add(new BigDecimal(datainitializeDTO11.getYesterdayCredit()));
          accountantCredence.setDebit(temp);
          accountantCredence.setCredit(new BigDecimal("0.00"));
        }
        if(datainitializeDTO11.getBalaceDirection().equals("1")){
          BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getCredit())).add(new BigDecimal(datainitializeDTO11.getDebit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayCredit())).add(new BigDecimal(datainitializeDTO11.getYesterdayDebit()));
          accountantCredence.setDebit(new BigDecimal("0.00"));
          accountantCredence.setCredit(temp);
        }
        
        if(datainitializeDTO11.getBalaceDirection().equals("2")){
          {
            BigDecimal temp_yeaterdayDebit = new BigDecimal(datainitializeDTO11.getYesterdayDebit());
            BigDecimal temp_debit = new BigDecimal(datainitializeDTO11.getDebit());
            BigDecimal temp_yeaterdayCredit = new BigDecimal(datainitializeDTO11.getYesterdayCredit());
            BigDecimal temp_credit = new BigDecimal(datainitializeDTO11.getCredit());
//          BigDecimal temp = temp_yeaterdayDebit.add(temp_debit).subtract(temp_yeaterdayCredit).subtract(temp_credit);
            BigDecimal temp = temp_yeaterdayCredit.add(temp_credit).subtract(temp_yeaterdayDebit).subtract(temp_debit);
            if(temp.compareTo(new BigDecimal(0)) > 0){
              accountantCredence.setDebit(temp.abs());
              accountantCredence.setCredit(new BigDecimal("0.00"));
            }
            if(temp.compareTo(new BigDecimal(0)) < 0){
              accountantCredence.setDebit(new BigDecimal("0.00"));
              accountantCredence.setCredit(temp.abs());
            }
            if(temp.compareTo(new BigDecimal(0)) == 0){
//              if(temp_yeaterdayDebit.compareTo(new BigDecimal(0))==0 && temp_debit.compareTo(new BigDecimal(0))==0 && temp_yeaterdayCredit.compareTo(new BigDecimal(0))==0 && temp_credit.compareTo(new BigDecimal(0))==0 ){
//                continue;
//              }else{
                accountantCredence.setDebit(new BigDecimal("0.00"));
                accountantCredence.setCredit(new BigDecimal("0.00"));
//              }
            }
          }
        }
        
        accountantCredence.setMakebill(securityInfo.getUserName());
        accountantCredence.setOffice(datainitializeDTO11.getOfficeName());
        accountantCredence.setCredenceSt("2");
        
        //年
        String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO11.getBookId()).substring(0, 4))-2)+"1231";
        accountantCredence.setCredenceDate(year);
        accountantCredence.setSettDate(year);
        
        //如果是(损益类科目)直接把FN201中的(损益结转字段置为2)
        if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("4")){
          accountantCredence.setIncDecSt("2");
        }
        if(subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("0")){
          //如果是(银行属性的科目)直接把(银行账结转状态置为2)
          accountantCredence.setBankAccSt("2");
        }
        if (subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("1")){
          //如果是(现金属性的科目)直接把(现金账结转状态置为2)
          accountantCredence.setCashAccSt("2");
        }
        accountantCredenceDAO.insert(accountantCredence);
      }
      Iterator it2 = list.iterator();
      while(it2.hasNext()){
        DatainitializeDTO datainitializeDTO22 = (DatainitializeDTO)it2.next();
//        if(datainitializeDTO22.getYesterdayDebit().equals("0") && datainitializeDTO22.getYesterdayCredit().equals("0")){
//          continue;
//        }
        AccountantCredence accountantCredence = new AccountantCredence();
        accountantCredence.setBookId(datainitializeDTO22.getBookId());
        accountantCredence.setSubjectCode(datainitializeDTO22.getSubjectCode());
        accountantCredence.setSummay(bookParameterDAO.getSummay5(datainitializeDTO22.getBookId()));

        accountantCredence.setDebit(new BigDecimal(datainitializeDTO22.getYesterdayDebit()));
        accountantCredence.setCredit(new BigDecimal(datainitializeDTO22.getYesterdayCredit()));
        
        accountantCredence.setMakebill(securityInfo.getUserName());
        accountantCredence.setOffice(datainitializeDTO22.getOfficeName());
        accountantCredence.setCredenceSt("2");
        
        //年
        String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO22.getBookId()).substring(0, 4))-1)+"1231";
        accountantCredence.setCredenceDate(year);
        accountantCredence.setSettDate(year);
        
        //如果是(损益类科目)直接把FN201中的(损益结转字段置为2)
        if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO22.getSubjectCode(), securityInfo).equals("4")){
          accountantCredence.setIncDecSt("2");
        }
        if(subjectDAO.getProperyByCode_WL(datainitializeDTO22.getSubjectCode(), securityInfo).equals("0")){
          //如果是(银行属性的科目)直接把(银行账结转状态置为2)
          accountantCredence.setBankAccSt("2");
        }
        if (subjectDAO.getProperyByCode_WL(datainitializeDTO22.getSubjectCode(), securityInfo).equals("1")){
          //如果是(现金属性的科目)直接把(现金账结转状态置为2)
          accountantCredence.setCashAccSt("2");
        }
        accountantCredenceDAO.insert(accountantCredence);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * * 插入FN311
      OP_SYS=财务系统
      OP_MODEL=初始数据
      OP_BUTTON=2
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   *插入FN201:
      BOOK_ID=所属账套
      SUBJECT_CODE=科目代码
      SUMMAY=1
      DEBIT=累计借方
      CREDIT=累计贷方
      MAKEBILL=操作员
      OFFICE=所选办事处
   * @param datainitializeDTO  张列
   * @param securityInfo
   * @throws Exception
   */
  public void insertSummaryOffice(List list,SecurityInfo securityInfo) throws Exception{
    try {
      if(list.size() != 0){
        DatainitializeDTO datainitializeDTO1 = (DatainitializeDTO)list.get(0);
        //插入FN311
        FnOperateLog fnOperateLog = new FnOperateLog();
        fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
        fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_DATAINITIALIZE+"");
        fnOperateLog.setOpButton("1");
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setBookId(datainitializeDTO1.getBookId());
        fnOperateLogDAO.insert(fnOperateLog);
        Iterator it = list.iterator();
        while(it.hasNext()){
          DatainitializeDTO datainitializeDTO = (DatainitializeDTO)it.next();
//          if(datainitializeDTO.getDebit().equals("0") && datainitializeDTO.getCredit().equals("0")){
//            continue;
//          }else{
            //插入 FN201
            AccountantCredence accountantCredence = new AccountantCredence();
            accountantCredence.setBookId(datainitializeDTO.getBookId());
            accountantCredence.setSubjectCode(datainitializeDTO.getSubjectCode());
            accountantCredence.setSummay(bookParameterDAO.getSummay(datainitializeDTO.getBookId()));

            accountantCredence.setDebit(new BigDecimal(datainitializeDTO.getDebit()));
            accountantCredence.setCredit(new BigDecimal(datainitializeDTO.getCredit()));
            
            accountantCredence.setMakebill(securityInfo.getUserName());
            accountantCredence.setOffice(datainitializeDTO.getOfficeName());
            accountantCredence.setCredenceSt("2");
            //年
            String date1 = bookDAO.getUseYearmonth(datainitializeDTO.getBookId())+"01";  
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            String year = "";
            try {
              Date d1 = df.parse(date1);  
              //System.out.println("d1=="+df.format(d1));  
              Calendar  g = Calendar.getInstance();  
              g.setTime(d1);  
              g.add(Calendar.DATE,-1);             
              Date d2 = g.getTime();  
              //System.out.println("d2======="+df.format(d2)); 
              year = df.format(d2);
            } catch (ParseException exx) {
              exx.printStackTrace();  
            }
            accountantCredence.setCredenceDate(year);
            accountantCredence.setSettDate(year);
            //如果是(损益类科目)直接把FN201中的(损益结转字段置为2)
            if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("4")){
              accountantCredence.setIncDecSt("2");
            }
            if(subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("0")){
              //如果是(银行属性的科目)直接把(银行账结转状态置为2)
              accountantCredence.setBankAccSt("2");
            }
            if (subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("1")){
              //如果是(现金属性的科目)直接把(现金账结转状态置为2)
              accountantCredence.setCashAccSt("2");
            }
            accountantCredenceDAO.insert(accountantCredence);
//          }
        }
        Iterator it1 = list.iterator();
        while(it1.hasNext()){
          DatainitializeDTO datainitializeDTO11 = (DatainitializeDTO)it1.next();
//          if(datainitializeDTO11.getYesterdayRemainingSum().equals("0") || datainitializeDTO11.getBalaceDirection().equals("2")){
//            continue;
//          }
          AccountantCredence accountantCredence = new AccountantCredence();
          accountantCredence.setBookId(datainitializeDTO11.getBookId());
          accountantCredence.setSubjectCode(datainitializeDTO11.getSubjectCode());
          accountantCredence.setSummay(bookParameterDAO.getSummay4(datainitializeDTO11.getBookId()));
          if(datainitializeDTO11.getBalaceDirection().equals("0")){
            BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getDebit())).add(new BigDecimal(datainitializeDTO11.getCredit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayDebit())).add(new BigDecimal(datainitializeDTO11.getYesterdayCredit()));
            accountantCredence.setDebit(temp);
            accountantCredence.setCredit(new BigDecimal("0.00"));
          }
          if(datainitializeDTO11.getBalaceDirection().equals("1")){
            BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getCredit())).add(new BigDecimal(datainitializeDTO11.getDebit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayCredit())).add(new BigDecimal(datainitializeDTO11.getYesterdayDebit()));
            accountantCredence.setDebit(new BigDecimal("0.00"));
            accountantCredence.setCredit(temp);
          }
          if(datainitializeDTO11.getBalaceDirection().equals("2")){
            {
              BigDecimal temp_yeaterdayDebit = new BigDecimal(datainitializeDTO11.getYesterdayDebit());
              BigDecimal temp_debit = new BigDecimal(datainitializeDTO11.getDebit());
              BigDecimal temp_yeaterdayCredit = new BigDecimal(datainitializeDTO11.getYesterdayCredit());
              BigDecimal temp_credit = new BigDecimal(datainitializeDTO11.getCredit());
//            BigDecimal temp = temp_yeaterdayDebit.add(temp_debit).subtract(temp_yeaterdayCredit).subtract(temp_credit);
              BigDecimal temp = temp_yeaterdayCredit.add(temp_credit).subtract(temp_yeaterdayDebit).subtract(temp_debit);
              if(temp.compareTo(new BigDecimal(0)) > 0){
                accountantCredence.setDebit(temp.abs());
                accountantCredence.setCredit(new BigDecimal("0.00"));
              }
              if(temp.compareTo(new BigDecimal(0)) < 0){
                accountantCredence.setDebit(new BigDecimal("0.00"));
                accountantCredence.setCredit(temp.abs());
              }
              if(temp.compareTo(new BigDecimal(0)) == 0){
//                if(temp_yeaterdayDebit.compareTo(new BigDecimal(0))==0 && temp_debit.compareTo(new BigDecimal(0))==0 && temp_yeaterdayCredit.compareTo(new BigDecimal(0))==0 && temp_credit.compareTo(new BigDecimal(0))==0 ){
//                  continue;
//                }else{
                  accountantCredence.setDebit(new BigDecimal("0.00"));
                  accountantCredence.setCredit(new BigDecimal("0.00"));
//                }
              }
            }
          }
          
          accountantCredence.setMakebill(securityInfo.getUserName());
          accountantCredence.setOffice(datainitializeDTO11.getOfficeName());
          accountantCredence.setCredenceSt("2");
          
          //年
          String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO11.getBookId()).substring(0, 4))-2)+"1231";
          accountantCredence.setCredenceDate(year);
          accountantCredence.setSettDate(year);
          
          //如果是(损益类科目)直接把FN201中的(损益结转字段置为2)
          if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("4")){
            accountantCredence.setIncDecSt("2");
          }
          if(subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("0")){
            //如果是(银行属性的科目)直接把(银行账结转状态置为2)
            accountantCredence.setBankAccSt("2");
          }
          if (subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("1")){
            //如果是(现金属性的科目)直接把(现金账结转状态置为2)
            accountantCredence.setCashAccSt("2");
          }
          accountantCredenceDAO.insert(accountantCredence);
        }
        Iterator it2 = list.iterator();
        while(it2.hasNext()){
          DatainitializeDTO datainitializeDTO2 = (DatainitializeDTO)it2.next();
//          if(datainitializeDTO2.getYesterdayDebit().equals("0") && datainitializeDTO2.getYesterdayCredit().equals("0")){
//            continue;
//          }
          AccountantCredence accountantCredence = new AccountantCredence();
          accountantCredence.setBookId(datainitializeDTO2.getBookId());
          accountantCredence.setSubjectCode(datainitializeDTO2.getSubjectCode());
          accountantCredence.setSummay(bookParameterDAO.getSummay5(datainitializeDTO2.getBookId()));

          accountantCredence.setDebit(new BigDecimal(datainitializeDTO2.getYesterdayDebit()));
          accountantCredence.setCredit(new BigDecimal(datainitializeDTO2.getYesterdayCredit()));
          
          accountantCredence.setMakebill(securityInfo.getUserName());
          accountantCredence.setOffice(datainitializeDTO2.getOfficeName());
          accountantCredence.setCredenceSt("2");
          
          //年
          String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO2.getBookId()).substring(0, 4))-1)+"1231";
          accountantCredence.setCredenceDate(year);
          accountantCredence.setSettDate(year);
          
          //如果是(损益类科目)直接把FN201中的(损益结转字段置为2)
          if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO2.getSubjectCode(), securityInfo).equals("4")){
            accountantCredence.setIncDecSt("2");
          }
          if(subjectDAO.getProperyByCode_WL(datainitializeDTO2.getSubjectCode(), securityInfo).equals("0")){
            //如果是(银行属性的科目)直接把(银行账结转状态置为2)
            accountantCredence.setBankAccSt("2");
          }
          if (subjectDAO.getProperyByCode_WL(datainitializeDTO2.getSubjectCode(), securityInfo).equals("1")){
            //如果是(现金属性的科目)直接把(现金账结转状态置为2)
            accountantCredence.setCashAccSt("2");
          }
          accountantCredenceDAO.insert(accountantCredence);
        }
       }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * FN101 账套状态 0可用，1为禁用
   * @param bookId
   * @return
   * @throws Exception  张列
   */
  public String getBookST(String bookId)throws Exception{
    String stutas = "";
    try {
      stutas = accountantCredenceDAO.getBookST(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }
  
  /**
   * 判断FN201里是否存在SUMMAY=1 and OFFICE=所选办事处的记录
   * @param bookId
   * @param officeName
   * @return
   * @throws Exception 张列
   */
  public List is_SummayOffice(final String bookId,final String officeName) throws Exception{
    return accountantCredenceDAO.is_SummayOffice(bookId, officeName);
  }
  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public SubjectDAO getSubjectDAO() {
    return subjectDAO;
  }
  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }
  public BookDAO getBookDAO() {
    return bookDAO;
  }
  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }
}
