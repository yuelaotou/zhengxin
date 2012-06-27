package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.business;




import java.math.BigDecimal;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.bsinterface.ICredenceInspectionBS;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionShowDTO;

public class CredenceInspectionBS implements ICredenceInspectionBS{
  private BookParameterDAO bookParameterDAO=null;
  private AccountantCredenceDAO accountantCredenceDAO=null;
  /**
   *  凭证检查
   * author wsh
   * 2007-10-30
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
   * 凭证检查
   * author wsh
   * 
   * @param id fn201主键
   * @param securityInfo 权限
   * @2007-10-30
   * @return CredenceInspectionFindDTO
   * @throws Exception 
   */
  public CredenceInspectionFindDTO findCredenceInspectionFindDTO(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    CredenceInspectionFindDTO credenceInspectionFindDTO=(CredenceInspectionFindDTO)pagination.getQueryCriterions().get("credenceInspectionFindDTO");
    if (credenceInspectionFindDTO==null) {
      credenceInspectionFindDTO=new CredenceInspectionFindDTO();
    }  
    try{       
      List countList=accountantCredenceDAO.queryCredenceInspectionCountList(credenceInspectionFindDTO, securityInfo);
     List size = accountantCredenceDAO.queryCredenceInspectionSize(credenceInspectionFindDTO, securityInfo);
      BigDecimal debitSum=new BigDecimal(0.00);
      BigDecimal creditSum=new BigDecimal(0.00);
      if(countList.size()>0){
        for(int i=0;i<countList.size();i++){
          CredenceInspectionShowDTO credenceInspectionShowDTO=(CredenceInspectionShowDTO)countList.get(i);
          debitSum=debitSum.add(credenceInspectionShowDTO.getDebit());
          creditSum=creditSum.add(credenceInspectionShowDTO.getCredit());
        }
      }     
      credenceInspectionFindDTO.setDebitSum(debitSum);
      credenceInspectionFindDTO.setCreditSum(creditSum);
      if(size.size()>0){
        credenceInspectionFindDTO.setCount(new Integer(size.size()).toString());
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return credenceInspectionFindDTO;
  }  
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

}
