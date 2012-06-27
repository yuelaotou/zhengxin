package org.xpup.hafmis.sysloan.loancallback.relievecontract.business;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.bsinterface.IRelieveContractBS;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractPrintDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractTaDTO;
import org.xpup.security.common.domain.Userslogincollbank;


public class RelieveContractBS implements IRelieveContractBS{
  private BorrowerAccDAO borrowerAccDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  /**
   * 抵押质押解除办理
   * @author 郭婧平
   * 2007-9-21
   * 根据贷款账号查询办理页面所需的数据
   * 查询条件：loadKouAcc
   */
  public RelieveContractTaDTO findRelieveContractTaInfo(String loadKouAcc,SecurityInfo securityInfo) throws Exception,BusinessException{
    // TODO Auto-generated method stub
    RelieveContractTaDTO relieveContractTaDTO=null;
    List loanbankList1 = null;
    try {
      // 取出用户权限放款银行
      List loanbankList = securityInfo.getDkUserBankList();
      loanbankList1 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanbankList1.add(bank.getCollBankId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try{
      relieveContractTaDTO=borrowerAccDAO.queryRelieveContractTaInfo(loadKouAcc,loanbankList1);
      if(relieveContractTaDTO!=null){
        relieveContractTaDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer.parseInt(relieveContractTaDTO.getLoanMode().toString()),BusiConst.PLRECOVERTYPE));
        relieveContractTaDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer.parseInt(relieveContractTaDTO.getCardKind().toString()),BusiConst.DOCUMENTSSTATE));
      }else{
        throw new BusinessException("该贷款账号不存在或状态不是已还清");
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
    return relieveContractTaDTO;
  }
  /**
   * 抵押质押解除办理
   * @author 郭婧平
   * 2007-9-21
   * 办理抵押质押解除
   */
  public void saveRelieveContractTa(String contractId,SecurityInfo securityInfo) throws Exception{
    try{
      //点击确定按钮的时候，如果已经是解除状态的，就不能再解除
      int count=borrowerAccDAO.findRelieveContractByContractId(contractId);
      if(count>0){
        throw new BusinessException("该贷款账号已经解除过，不能再解除");
      }
      //修改pl121
      borrowerAccDAO.updatePledgeContractStatus(contractId);
      //修改pl122
      borrowerAccDAO.updateImpawnContractStatus(contractId);
      // 插入日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_LOANRECOVER_LIVING_DO));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 抵押质押解除维护
   * @author 郭婧平
   * 2007-9-22
   * 查询列表信息
   */
  public List findRelieveContractTbList(Pagination pagination,List loanbankList) throws Exception{
    List list=null;
    List countlist=null;
    String loanKouAcc="";
    String contractId="";
    String borrowerName="";
    String cardNum="";
    String loanBankId="";
    try{
      if(pagination.getQueryCriterions().get("loanKouAcc")!=null){
        loanKouAcc=(String) pagination.getQueryCriterions().get("loanKouAcc");
      }
      if(pagination.getQueryCriterions().get("contractId")!=null){
        contractId=(String) pagination.getQueryCriterions().get("contractId");
      }
      if(pagination.getQueryCriterions().get("borrowerName")!=null){
        borrowerName=(String) pagination.getQueryCriterions().get("borrowerName");
      }
      if(pagination.getQueryCriterions().get("cardNum")!=null){
        cardNum=(String) pagination.getQueryCriterions().get("cardNum");
      }
      if(pagination.getQueryCriterions().get("loanBankId")!=null){
        loanBankId=(String) pagination.getQueryCriterions().get("loanBankId");
      }
      String orderBy=(String) pagination.getOrderBy();;
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list=borrowerAccDAO.queryRelieveContractTbList(loanKouAcc, contractId, borrowerName, cardNum, loanBankId, orderBy, order, start, pageSize,page,loanbankList);
      countlist=borrowerAccDAO.queryRelieveContractTbListCount(loanKouAcc, contractId, borrowerName, cardNum, loanBankId,loanbankList);
      if(countlist.size()!=0){
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 抵押质押解除维护
   * @author 郭婧平
   * 2007-9-22
   * 删除按钮
   */
  public void deleteRelieveContractTb(String contractId,SecurityInfo securityInfo) throws Exception{
    try{
      //修改pl121
      borrowerAccDAO.updatePledgeContractStatusTb(contractId);
      //修改pl122
      borrowerAccDAO.updateImpawnContractStatusTb(contractId);
      // 插入日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_LOANRECOVER_LIVING_MAINTAIN));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 抵押质押解除维护
   * @author 郭婧平
   * 2007-9-24
   * 根据合同编号查询出打印的数据
   * 查询条件：contractId
   */
  public RelieveContractPrintDTO findRelieveContractTbPrintInfo(String contractId) throws Exception{
    // TODO Auto-generated method stub
    RelieveContractPrintDTO relieveContractPrintDTO=null;
    try{
      relieveContractPrintDTO=borrowerAccDAO.queryRelieveContractTbPrintInfo(contractId);
      if(relieveContractPrintDTO!=null){
        relieveContractPrintDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer.parseInt(relieveContractPrintDTO.getLoanMode().toString()),BusiConst.PLRECOVERTYPE));
        relieveContractPrintDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer.parseInt(relieveContractPrintDTO.getCardKind().toString()),BusiConst.DOCUMENTSSTATE));
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return relieveContractPrintDTO;
  }
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
}
