package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.BankCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.bsinterface.IDepositCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccBcaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccBdcDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowBaseDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowDTO;


public class DepositCheckAccBS implements IDepositCheckAccBS{
  private TreasurerCredenceDAO treasurerCredenceDAO=null; 
  private BankCredenceDAO bankCredenceDAO=null;
  private BookParameterDAO bookParameterDAO=null;
  /**
   * 银行存款对账单
   * @author 郭婧平
   * 2007-10-30
   * 查询列表信息
   */
  public Object[] findDepositCheckAccList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object[] obj=new Object[2];
    String settDateSt="";
    String settDateEnd="";
    String subjectCode="";
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
      if(pagination.getQueryCriterions().get("settDateSt")!=null){
        settDateSt=(String) pagination.getQueryCriterions().get("settDateSt");
      }
      if(pagination.getQueryCriterions().get("settDateEnd")!=null){
        settDateEnd=(String) pagination.getQueryCriterions().get("settDateEnd");
      }
      if(pagination.getQueryCriterions().get("subjectCode")!=null){
        subjectCode=(String) pagination.getQueryCriterions().get("subjectCode");
      }
      List bdcList=treasurerCredenceDAO.queryDepositCheckAccBdcList(settDateSt, settDateEnd, subjectCode, officeList1, securityInfo);
      List bcaList=bankCredenceDAO.queryDepositCheckAccBcaList(settDateSt, settDateEnd, subjectCode, officeList1, securityInfo);
      //判断查询出的两个list里，相同科目代码、相同结算号的记录是否FN210.DEBIT = FN211.CREDIT and FN210.CREDIT = FN211.DEBIT
      for(int i=0;i<bdcList.size();i++){
        DepositCheckAccBdcDTO depositCheckAccBdcDTO=(DepositCheckAccBdcDTO)bdcList.get(i);
        if(!depositCheckAccBdcDTO.getSettType().equals("")){
          depositCheckAccBdcDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(depositCheckAccBdcDTO.getSettType()));
        }
        if((!depositCheckAccBdcDTO.getCredenceCharacter().equals(""))&&(!depositCheckAccBdcDTO.getCredenceNum().equals(""))){
          depositCheckAccBdcDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(depositCheckAccBdcDTO.getCredenceCharacter())+"-"+
              depositCheckAccBdcDTO.getCredenceNum());
        }else if((!depositCheckAccBdcDTO.getCredenceNum().equals(""))&&depositCheckAccBdcDTO.getCredenceCharacter().equals("")){
          depositCheckAccBdcDTO.setCredenceChaNum(depositCheckAccBdcDTO.getCredenceNum());
        }
        for(int j=0;j<bcaList.size();j++){
          DepositCheckAccBcaDTO depositCheckAccBcaDTO=(DepositCheckAccBcaDTO)bcaList.get(j);
          if(!depositCheckAccBcaDTO.getSettType().equals("")){
            depositCheckAccBcaDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(depositCheckAccBcaDTO.getSettType()));
          }
          if(depositCheckAccBdcDTO.getSubjectCode().equals(depositCheckAccBcaDTO.getSubjectCode())&&
              depositCheckAccBdcDTO.getSettNum().equals(depositCheckAccBcaDTO.getSettNum())){
            if(depositCheckAccBdcDTO.getDebit().equals(depositCheckAccBcaDTO.getCredit())&&
                depositCheckAccBdcDTO.getCredit().equals(depositCheckAccBcaDTO.getDebit())){
              depositCheckAccBdcDTO.setType("1");
              depositCheckAccBcaDTO.setType("1");
            }
          }
        }
      }
      obj[0]=bdcList;
      obj[1]=bcaList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 银行存款对账单--银行存款余额调节表window
   * @author 郭婧平
   * 2007-11-1
   * 查询页面所要显示的信息
   */
  public Object[] findDepositCheckAccWindowInfo(List bdcList,List bcaList,Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object[] obj=new Object[5];
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
      List bdcIdList=new ArrayList();
      List bcaIdList=new ArrayList();
      for(int i=0;i<bdcList.size();i++){
        DepositCheckAccBdcDTO depositCheckAccBdcDTO=(DepositCheckAccBdcDTO)bdcList.get(i);
        if(depositCheckAccBdcDTO.getType().equals("")){
          bdcIdList.add(depositCheckAccBdcDTO.getCredenceId());
        }
      }
      for(int i=0;i<bcaList.size();i++){
        DepositCheckAccBcaDTO depositCheckAccBcaDTO=(DepositCheckAccBcaDTO)bcaList.get(i);
        if(depositCheckAccBcaDTO.getType().equals("")){
          bcaIdList.add(depositCheckAccBcaDTO.getCredenceId());
        }
      }
      
      List bankList=new ArrayList();//银行已收中心未收
      List officeList=new ArrayList();//中心已收银行未收
      List bankOutList=new ArrayList();//银行已付中心未付
      List officeOutList=new ArrayList();//中心已付银行未付
      
      List baseList=treasurerCredenceDAO.queryDepositCheckAccWindowBaseList(bdcIdList, bcaIdList);
      if(baseList.size()>0){
        for(int i=0;i<baseList.size();i++){
          DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)baseList.get(i);
          if(depositCheckAccWindowBaseDTO.getType().equals("1")){
            officeList.add(depositCheckAccWindowBaseDTO);
          }else if(depositCheckAccWindowBaseDTO.getType().equals("2")){
            officeOutList.add(depositCheckAccWindowBaseDTO);
          }else if(depositCheckAccWindowBaseDTO.getType().equals("3")){
            bankOutList.add(depositCheckAccWindowBaseDTO);
          }else if(depositCheckAccWindowBaseDTO.getType().equals("4")){
            bankList.add(depositCheckAccWindowBaseDTO);
          }
        }
      }
      BigDecimal bdcBalanceSum=new BigDecimal(0.00);//银行日记账余额
      BigDecimal bcaBalanceSum=new BigDecimal(0.00);//银行对账单余额
      BigDecimal bankMoneySum=new BigDecimal(0.00);//银行已收中心未收金额总计
      BigDecimal officeMoneySum=new BigDecimal(0.00);//中心已收银行未收金额总计
      BigDecimal bankOutMoneySum=new BigDecimal(0.00);//银行已付中心未付金额总计
      BigDecimal officeOutMoneySum=new BigDecimal(0.00);//中心已付银行未付金额总计
      BigDecimal adjustOfficeBalance=new BigDecimal(0.00);//调节后余额（中心）
      BigDecimal adjustBankBalance=new BigDecimal(0.00);//调节后余额（银行）
      
      for(int i=0;i<bankList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)bankList.get(i);
        bankMoneySum=bankMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      for(int i=0;i<officeList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)officeList.get(i);
        officeMoneySum=officeMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      for(int i=0;i<bankOutList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)bankOutList.get(i);
        bankOutMoneySum=bankOutMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      for(int i=0;i<officeOutList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)officeOutList.get(i);
        officeOutMoneySum=officeOutMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      DepositCheckAccWindowDTO depositCheckAccWindowDTO=new DepositCheckAccWindowDTO();
      depositCheckAccWindowDTO.setBankMoneySum(bankMoneySum);
      depositCheckAccWindowDTO.setOfficeMoneySum(officeMoneySum);
      depositCheckAccWindowDTO.setBankOutMoneySum(bankOutMoneySum);
      depositCheckAccWindowDTO.setOfficeOutMoneySum(officeOutMoneySum);
      bdcBalanceSum=officeMoneySum.subtract(officeOutMoneySum);
      bcaBalanceSum=bankMoneySum.subtract(bankOutMoneySum);
      depositCheckAccWindowDTO.setBdcBalanceSum(bdcBalanceSum);
      depositCheckAccWindowDTO.setBcaBalanceSum(bcaBalanceSum);
      adjustOfficeBalance=adjustOfficeBalance.add(bdcBalanceSum).add(bankMoneySum).subtract(bankOutMoneySum);
      adjustBankBalance=adjustBankBalance.add(bcaBalanceSum).add(officeMoneySum).subtract(officeOutMoneySum);
      depositCheckAccWindowDTO.setAdjustOfficeBalance(adjustOfficeBalance);
      depositCheckAccWindowDTO.setAdjustBankBalance(adjustBankBalance);
      obj[0]=bankList;
      obj[1]=officeList;
      obj[2]=bankOutList;
      obj[3]=officeOutList;
      obj[4]=depositCheckAccWindowDTO;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  public void setBankCredenceDAO(BankCredenceDAO bankCredenceDAO) {
    this.bankCredenceDAO = bankCredenceDAO;
  }
  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
}
