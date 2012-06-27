package org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.business;

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
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BankCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BankCredence;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.TreasurerCredence;
import org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.bsinterface.IBalanceinitializeBS;
import org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.dto.BalanceinitializeDTO;

/**
 * Copy Right Information   : 余额初始
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-19-2007
 */
public class BalanceinitializeBS implements IBalanceinitializeBS {

  /**
   * 获得科目代码和科目名称 
   */
  public List getSubjectCodeName(String bookId,String officeName) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    List temp_list = new ArrayList();
    try {
      if(officeName == null || officeName.equals("")){
        list = subjectDAO.getBalanceinitializeInfo(bookId);
        return list;
      }else{
        list = subjectDAO.getBalanceinitializeInfo(bookId);
        if(list == null){
          return list;
        }
        Iterator it = list.iterator();
        while(it.hasNext()){
          BalanceinitializeDTO temp_balanceinitializeDTO = (BalanceinitializeDTO)it.next();
          String subjectCode = temp_balanceinitializeDTO.getSubjectCode();
          BalanceinitializeDTO balanceinitializeDTO = subjectDAO.getDebit(subjectCode, officeName, bookId);
          balanceinitializeDTO.setSubjectCode(temp_balanceinitializeDTO.getSubjectCode());
          balanceinitializeDTO.setSubjectName(temp_balanceinitializeDTO.getSubjectName());
          temp_list.add(balanceinitializeDTO);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return temp_list;
  }
  
  /**
   * 余额初始 同一办事处下的SUBJECT_CODE中存在相同的值 返回INT 大于1有相同的值
   */
  public int getBalanceinitializeBT(String bookId, String officeName) throws Exception {
    // TODO Auto-generated method stub
    int bt = 0;
    try {
      List list = accountantCredenceDAO.getBalanceinitializeBT(bookId, officeName);
      if(list == null ||list.size() == 0){
        bt = 0;
      }else{
        bt = 2;
      }
      return bt;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  
  /**
   * 判断FN210表中是否存在SUMMAY=3 and OFFICE=所选办事处的记录
   * @param bookId
   * @param officeName
   * @return
   */
  public int is_Balanceinitialize_ZL(String bookId,String officeName)throws Exception{
    int temp_num = 0;
    try {
      temp_num = Integer.parseInt(treasurerCredenceDAO.is_Balanceinitialize_ZL(bookId, officeName));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return temp_num;
  }
  
  /**
   * 插入FN311
      OP_SYS=财务系统
      OP_MODEL=余额初始
      OP_BUTTON=1
      OP_IP=操作员ip
      OP_TIME=系统时间
      OPERATOR=操作员
   *插入FN210
      BOOK_ID=所属账套
      SUBJECT_CODE=页面上的科目代码
      DEBIT=启用期初余额
      CREBIT=0
      SUMMRAY=3
      OFFICE=所选办事处
      CREDENCE_TYPE=通过科目代码到FN110中取出SUBJECT_PROPERTY
      MAKEBILL=操作员
      CREDENCE_ST=2.记账
   * @param securityInfo
   * @param balanceinitializeDTO
   * @throws Exception
   */
  public void insertBalanceinitialize(SecurityInfo securityInfo,List list) throws Exception{
    try {
      if(list.size() !=0){
        BalanceinitializeDTO balanceinitializeDTO1 = (BalanceinitializeDTO)list.get(0);
        //插入FN311
        FnOperateLog fnOperateLog = new FnOperateLog();
        fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
        fnOperateLog.setOpModel(BusiLogConst.FN_OP_TREASURERMNG_BALANCEINITIALIZE+"");
        fnOperateLog.setOpButton("1");
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setBookId(balanceinitializeDTO1.getBookId());
        fnOperateLogDAO.insert(fnOperateLog);
        
        Iterator it = list.iterator();
        while(it.hasNext()){
          BalanceinitializeDTO balanceinitializeDTO2 = (BalanceinitializeDTO)it.next();
          double debit = Double.parseDouble(balanceinitializeDTO2.getDebit());
          if(debit == 0){
            continue;
          }else{
            //插入FN210
            TreasurerCredence treasurerCredence = new TreasurerCredence();
            treasurerCredence.setBookId(balanceinitializeDTO2.getBookId());
            treasurerCredence.setSubjectCode(balanceinitializeDTO2.getSubjectCode());
            treasurerCredence.setDebit(new BigDecimal(balanceinitializeDTO2.getDebit()));
            treasurerCredence.setCredit(new BigDecimal("0"));
            treasurerCredence.setSummray(treasurerCredenceDAO.getSummay(balanceinitializeDTO2.getBookId()));
            treasurerCredence.setOffice(balanceinitializeDTO2.getOfficeName());
            treasurerCredence.setCredenceType(subjectDAO.getSubjectProperty(balanceinitializeDTO2.getBookId(), balanceinitializeDTO2.getSubjectCode()));
            treasurerCredence.setMakebill(securityInfo.getUserName());
            treasurerCredence.setCredenceSt("2");
            //年
            String date1 = bookDAO.getUseYearmonth(balanceinitializeDTO2.getBookId())+"01";  
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
            treasurerCredence.setCredenceDate(year);
            treasurerCredence.setSettDate(year);
            treasurerCredenceDAO.insert(treasurerCredence);
            //判断 科目代码的科目属性是否是“银行存款”
            if(subjectDAO.getProperyByCode_WL(balanceinitializeDTO2.getSubjectCode(), securityInfo).equals("1")){
              //如果是 “银行存款” 插入 FN211
              BankCredence bankCredence = new BankCredence();
              bankCredence.setBookId(balanceinitializeDTO2.getBookId());
              bankCredence.setSubjectCode(balanceinitializeDTO2.getSubjectCode());
              bankCredence.setDebit(new BigDecimal("0"));
              bankCredence.setCredit(new BigDecimal(balanceinitializeDTO2.getDebit()));
              bankCredence.setSummary(treasurerCredenceDAO.getSummay(balanceinitializeDTO2.getBookId()));
              bankCredence.setOffice(balanceinitializeDTO2.getOfficeName());
              bankCredence.setCredenceType("1");
              bankCredence.setMakebill(securityInfo.getUserName());
              bankCredence.setSettDate(year);
              bankCredenceDAO.insert(bankCredence);
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 删除FN210     FN210.SUMMAY=3 and FN210.OFFICE=所选办事处的记录
   * 插入FN311
        OP_SYS=财务系统
        OP_MODEL=余额初始
        OP_BUTTON=2
        OP_IP=操作员ip
        OP_TIME=系统时间
        OPERATOR=操作员
   * 插入FN210
        BOOK_ID=所属账套
        SUBJECT_CODE=页面上的科目代码
        DEBIT=启用期初余额
        CREBIT=0
        SUMMRAY=3
        OFFICE=所选办事处
        CREDENCE_TYPE=通过科目代码到FN110中取出SUBJECT_PROPERTY
        MAKEBILL=操作员
        CREDENCE_ST=2.记账
   * @param securityInfo
   * @param list
   */
  public void deleteBalanceinitialize(SecurityInfo securityInfo,List list) throws Exception{
    try {
      BalanceinitializeDTO balanceinitializeDTO1 = (BalanceinitializeDTO)list.get(0);
      //删除FN210 
      treasurerCredenceDAO.deleteSummay_ZL(balanceinitializeDTO1.getBookId(), balanceinitializeDTO1.getOfficeName());
      treasurerCredenceDAO.deleteSummay_ZL1(balanceinitializeDTO1.getBookId(), balanceinitializeDTO1.getOfficeName());
      //插入FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_TREASURERMNG_BALANCEINITIALIZE+"");
      fnOperateLog.setOpButton("2");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(balanceinitializeDTO1.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      Iterator it = list.iterator();
      while(it.hasNext()){
        BalanceinitializeDTO balanceinitializeDTO2 = (BalanceinitializeDTO)it.next();
        double debit = Double.parseDouble(balanceinitializeDTO2.getDebit());
        if(debit <= 0){
          continue;
        }else{
          //插入FN210
          TreasurerCredence treasurerCredence = new TreasurerCredence();
          treasurerCredence.setBookId(balanceinitializeDTO2.getBookId());
          treasurerCredence.setSubjectCode(balanceinitializeDTO2.getSubjectCode());
          treasurerCredence.setDebit(new BigDecimal(balanceinitializeDTO2.getDebit()));
          treasurerCredence.setCredit(new BigDecimal("0"));
          treasurerCredence.setSummray(treasurerCredenceDAO.getSummay(balanceinitializeDTO2.getBookId()));
          treasurerCredence.setOffice(balanceinitializeDTO2.getOfficeName());
          treasurerCredence.setCredenceType(subjectDAO.getSubjectProperty(balanceinitializeDTO2.getBookId(), balanceinitializeDTO2.getSubjectCode()));
          treasurerCredence.setMakebill(securityInfo.getUserName());
          treasurerCredence.setCredenceSt("2");
          //年
          String date1 = bookDAO.getUseYearmonth(balanceinitializeDTO2.getBookId())+"01";  
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
          treasurerCredence.setCredenceDate(year);
          treasurerCredence.setSettDate(year);
          treasurerCredenceDAO.insert(treasurerCredence);
          //判断 科目代码的科目属性是否是“银行存款”
          if(subjectDAO.getProperyByCode_WL(balanceinitializeDTO2.getSubjectCode(), securityInfo).equals("1")){
            //如果是 “银行存款” 插入 FN211
            BankCredence bankCredence = new BankCredence();
            bankCredence.setBookId(balanceinitializeDTO2.getBookId());
            bankCredence.setSubjectCode(balanceinitializeDTO2.getSubjectCode());
            bankCredence.setDebit(new BigDecimal("0"));
            bankCredence.setCredit(new BigDecimal(balanceinitializeDTO2.getDebit()));
            bankCredence.setSummary(treasurerCredenceDAO.getSummay(balanceinitializeDTO2.getBookId()));
            bankCredence.setOffice(balanceinitializeDTO2.getOfficeName());
            bankCredence.setCredenceType("1");
            bankCredence.setMakebill(securityInfo.getUserName());
            bankCredence.setSettDate(year);
            bankCredenceDAO.insert(bankCredence);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private SubjectDAO subjectDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;
  private AccountantCredenceDAO accountantCredenceDAO = null;
  private TreasurerCredenceDAO treasurerCredenceDAO = null;
  private BankCredenceDAO bankCredenceDAO = null;
  private BookDAO bookDAO = null;
  
  public SubjectDAO getSubjectDAO() {
    return subjectDAO;
  }
  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }
  
  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public AccountantCredenceDAO getAccountantCredenceDAO() {
    return accountantCredenceDAO;
  }

  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public TreasurerCredenceDAO getTreasurerCredenceDAO() {
    return treasurerCredenceDAO;
  }

  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }

  public BankCredenceDAO getBankCredenceDAO() {
    return bankCredenceDAO;
  }

  public void setBankCredenceDAO(BankCredenceDAO bankCredenceDAO) {
    this.bankCredenceDAO = bankCredenceDAO;
  }

  public BookDAO getBookDAO() {
    return bookDAO;
  }

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

}
