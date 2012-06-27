package org.xpup.hafmis.sysloan.dataready.bank.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBank;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.bank.bsinterface.IBankBS;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankAFDTO;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankDTO;

public class BankBS implements IBankBS{
  private LoanBankDAO loanBankDAO=null;
  private CollBankDAO collBankDAO=null;
  private OrganizationUnitDAO organizationUnitDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  /**
   * name 杨蒙
   * 数据准备:银行维护--显示列表
   */
  public List findBankList(Pagination pagination)
  {
    List list=null;
    List listAF=new ArrayList();
    CollBank dto=null;
    OrganizationUnit organizationUnit=null;
    try{
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list=loanBankDAO.findLoanBank_YM(orderBy, order, start, pageSize,page);
      if(list.size()!=0||list!=null)
      {
        for(int i=0;i<list.size();i++)
        {
          BankAFDTO bankAF=new BankAFDTO();
        LoanBank loanBank=(LoanBank)list.get(i);
        bankAF.setId(loanBank.getId()); //id
        dto=collBankDAO.getCollBankByCollBankid(loanBank.getLoanBankId().toString());
        bankAF.setBankName(dto.getCollBankName());//银行名称  
        organizationUnit=
          organizationUnitDAO.queryOrganizationUnitListByCriterions(loanBank.getOffice());
        bankAF.setOffice(organizationUnit.getName());//办事处
        bankAF.setLoanAcc(loanBank.getLoanAcc());//中心委托贷款账号
        bankAF.setInterestAcc(loanBank.getInterestAcc());//中心利息账号
        bankAF.setBankPrisident(loanBank.getBankPrisident());//银行行长
        bankAF.setBankPrisidentTel(loanBank.getBankPrisidentTel());//行长电话
        bankAF.setContactPrsn(loanBank.getContactPrsn());//联系人
        bankAF.setContactTel(loanBank.getContactTel());//联系人电话
        bankAF.setBusiness(loanBank.getBusiness());//联系人职务
        String bankSt=BusiTools.getBusiValue(Integer.parseInt(loanBank.getLoanBnakSt()),
            BusiConst.APPSTATUS);
        bankAF.setLoanBankSt(bankSt);//状态
        bankAF.setOutAccount(loanBank.getOutAccount());
        bankAF.setInAccount(loanBank.getInAccount());
        listAF.add(bankAF);
        }
      }
    
    int count = loanBankDAO.findBankCount_YM();
    pagination.setNrOfElements(count);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return listAF;
  }
  /**
   * name 杨蒙
   * 判断银行信息表 是否已经存在 办事处 银行ID相同的记录  (**张列改**)
   * return boolean
   * true 存在
   * false 空记录
   */
  public boolean isCheckBank(BankAFDTO bankAFDTO){
    boolean is_bank=false;
    try {
      BankDTO bankDTO=new BankDTO();
      bankDTO.setBankName(new BigDecimal(bankAFDTO.getBankName()));
      bankDTO.setOffice(bankAFDTO.getOffice());
      is_bank=loanBankDAO.isCheckBank_YM(bankDTO);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return is_bank;
  }
  /**
   * name 杨蒙
   * 插入新数据 银行信息表 and 操作日志  (**张列改**)
   */
  public void insertBank(BankAFDTO bankAFDTO,SecurityInfo securityInfo)
  {
    try{
      LoanBank loanBank=new LoanBank(); //创建银行对象
      loanBank.setOffice(bankAFDTO.getOffice()); //办事处
      loanBank.setLoanBankId(new BigDecimal(bankAFDTO.getBankName())); //银行编号
      loanBank.setLoanAcc(bankAFDTO.getLoanAcc());//贷款帐号
      loanBank.setInterestAcc(bankAFDTO.getInterestAcc());//利息帐号
      loanBank.setBankPrisident(bankAFDTO.getBankPrisident());//银行行长
      loanBank.setBankPrisidentTel(bankAFDTO.getBankPrisidentTel());//行长电话
      loanBank.setContactPrsn(bankAFDTO.getContactPrsn());//联系人
      loanBank.setContactTel(bankAFDTO.getContactTel());//联系人电话
      loanBank.setBusiness(bankAFDTO.getBusiness());//联系人职务
      loanBank.setOutAccount(bankAFDTO.getOutAccount());
      loanBank.setInAccount(bankAFDTO.getInAccount());
      String yearClear = (Integer.parseInt(securityInfo.getUserInfo().getPlbizDate().substring(0, 4))-1)+"";//会计日期    
      loanBank.setYearClear(yearClear);//
      loanBank.setLoanBnakSt("0");
      String logid=loanBankDAO.insert(loanBank).toString();
      PlOperateLog plOperateLog=new PlOperateLog();//创建日志对象     
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//个贷系统
      plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//银行维护
      plOperateLog.setOpButton("1");
      plOperateLog.setOpBizId(new BigDecimal(logid)); 
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);

    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  /**
   * name 杨蒙
   * 通过银行信息表 返回数据
   */
  public BankAFDTO queryBank(String id)
  {
    LoanBank loanBank=loanBankDAO.queryById(new Integer(id));
    BankAFDTO bankAF=new BankAFDTO();
    try{
    bankAF.setBankName(loanBank.getLoanBankId().toString());//银行名称  
    bankAF.setOffice(loanBank.getOffice());//办事处
    bankAF.setId(loanBank.getId());
    bankAF.setLoanAcc(loanBank.getLoanAcc());//中心委托贷款账号
    bankAF.setInterestAcc(loanBank.getInterestAcc());//中心利息账号
    bankAF.setBankPrisident(loanBank.getBankPrisident());//银行行长
    bankAF.setBankPrisidentTel(loanBank.getBankPrisidentTel());//行长电话
    bankAF.setContactPrsn(loanBank.getContactPrsn());//联系人
    bankAF.setContactTel(loanBank.getContactTel());//联系人电话
    bankAF.setBusiness(loanBank.getBusiness());//联系人职务
    bankAF.setOutAccount(loanBank.getOutAccount());
    bankAF.setInAccount(loanBank.getInAccount());
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return bankAF;
  }
  /**
   * name 杨蒙
   * 根据id删除银行信息表记录
   */
  public String deleteBank_YM(Integer id,SecurityInfo securityInfo)
  {
    LoanBank loanBank=null;
    String err="该记录不能删除";
    try{
      loanBank=loanBankDAO.queryById(id);
      if(loanBank==null)
      {
        err="该记录已经删除!";
      }
      else  //存在该记录
      {
        if(loanBank.getLoanBnakSt().equals("0"))
        {
          loanBankDAO.delete_YM(id);
          PlOperateLog plOperateLog=new PlOperateLog();//创建日志对象     
          plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//个贷系统
          plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//银行维护
          plOperateLog.setOpButton("3");
          plOperateLog.setOpBizId(new BigDecimal(id.toString())); 
          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLogDAO.insert(plOperateLog);
          err="删除成功!";
        }
        else
        {
          err="该记录不能删除";
        }
      }
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return err;
  }
  /**
   * name 杨蒙
   * 银行信息表 启用功能
   * return boolean
   * 启用成功 true
   * 启用失败 false
   */
  public String useBank_YM(Integer id,SecurityInfo securityInfo)
  {
    LoanBank loanBank=null;
    String use="该条记录已启用!";
    try{
      loanBank=loanBankDAO.queryById(id);
      if(loanBank.getLoanBnakSt().equals("0"))
      {
        loanBank.setLoanBnakSt("1");
        loanBankDAO.update(loanBank);
        PlOperateLog plOperateLog=new PlOperateLog();//创建日志对象     
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//个贷系统
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//银行维护
        plOperateLog.setOpButton("7");
        plOperateLog.setOpBizId(new BigDecimal(id.toString())); 
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
        use="启用成功!";
      }      
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return use;
  }
  /**
   * name 杨蒙
   * 根据办事处,银行 或得此条记录  (**张列改**)
   */
  public void updateBank_YM(BankAFDTO bankAFDTO,SecurityInfo securityInfo)
  {
    LoanBank bank=null;
    try{
      bank=loanBankDAO.queryById(bankAFDTO.getId());
      if(!bankAFDTO.getLoanAcc().equals(""))
        bank.setLoanAcc(bankAFDTO.getLoanAcc());//贷款帐号
      if(bankAFDTO.getInterestAcc()!=null)
        bank.setInterestAcc(bankAFDTO.getInterestAcc().trim());
      if(bankAFDTO.getBankPrisident()!=null)
        bank.setBankPrisident(bankAFDTO.getBankPrisident());
      if(bankAFDTO.getBankPrisidentTel()!=null)
        bank.setBankPrisidentTel(bankAFDTO.getBankPrisidentTel());
      if(bankAFDTO.getContactPrsn()!=null)
        bank.setContactPrsn(bankAFDTO.getContactPrsn());
      if(bankAFDTO.getContactTel()!=null)
        bank.setContactTel(bankAFDTO.getContactTel());
      if(bankAFDTO.getOutAccount()!=null)
        bank.setOutAccount(bankAFDTO.getOutAccount());
      if(bankAFDTO.getInAccount()!=null)
        bank.setInAccount(bankAFDTO.getInAccount());
      if(bankAFDTO.getBusiness()!=null)
        bank.setBusiness(bankAFDTO.getBusiness());
        loanBankDAO.update(bank);
        PlOperateLog plOperateLog=new PlOperateLog();//创建日志对象     
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//个贷系统
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//银行维护
        plOperateLog.setOpButton("2");
        plOperateLog.setOpBizId(new BigDecimal(bank.getId().toString())); 
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    } 
  }
  /**
   * name 杨蒙
   *根据id 判断是否有记录
   *true 有此记录
   *false 无此记录
   */
  public boolean is_bank_YM(Integer id)
  {
    boolean is_bank=false;
    try {
      LoanBank loanBank=loanBankDAO.queryById(id);
      if(loanBank==null)
        is_bank=true;
    } catch (RuntimeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return is_bank;
  }
  /**
   * 获得所有银行  张列
   * @return
   */
  public List getCollBankList(){
    List list = null;
    try {
      list = loanBankDAO.getCollBankList();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 得到办事处下的归集银行
   * @param office
   * @return CollBankList
   */
  public List queryCollBank(String office){
    List collBankList = null;
    try {
      collBankList = collBankDAO.getOfficeCollBankList(office);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return collBankList;
  }
  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
}
