package org.xpup.hafmis.sysloan.loanapply.giveacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.GiveAccDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.GiveAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface.IGiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.GiveaccModifyDTO;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.HouseListDTO;

public class GiveaccBS implements IGiveaccBS {
  private HousesDAO housesDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private GiveAccDAO giveAccDAO = null;

  private SecurityDAO securityDAO = null;

  /**
   * 根据合同编号查询划款帐号修改信息
   * 
   * @param contractId 合同编号
   * @param houseType 住房类型
   * @return GiveaccModifyDTO
   * @throws Exception
   * @author wsh
   */
  public GiveaccModifyDTO findGiveaccInfo(String contractId, String houseType)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    GiveaccModifyDTO giveaccModifyDTO = new GiveaccModifyDTO();
    try {
      giveaccModifyDTO = housesDAO.queryHousesInfo_wsh(contractId.toString(),
          houseType);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return giveaccModifyDTO;
  }

  /**
   * 根据输入合同编号查询合同编号是否存在
   * 
   * @param contractId 合同编号
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findGiveaccInfoExist(String contractId, List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    int count = 0;
    try {
      count = borrowerAccDAO.queryIdExist1_wsh(contractId, loanBankList)
          .intValue();
      // 条件成立说明输入合同编号不存在
      if (count == 0) {
        be = new BusinessException("合同编号不存在或不在用户权限下！");
        throw be;
      }
    } catch (BusinessException e) {
      // TODO: handle exception
      e.printStackTrace();
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  /**
   * 修改划款帐号
   * 
   * @param contractId 合同编号
   * @throws Exception
   * @author wsh
   */
  public String findHouseType(String contractId) throws Exception {
    // TODO Auto-generated method stub
    String houseType = "";
    try {
      houseType = housesDAO.findHouseType_wsh(contractId);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return houseType;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  /**
   * 修改划款帐号
   * 
   * @param contractId 合同编号
   * @param newLoanKouAcc 新扣款帐号
   * @param oldBankAcc 旧扣款帐号
   * @param securityInfo 权限
   * @throws Exception
   * @author wsh
   */
  public void modifyGiveAccInfo(String contractId, String oldSellerPayBank,
      String oldPayBankAcc, String newSellerPayBank, String newPayBankAcc,
      String houseType, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      Houses house = housesDAO.queryById(contractId);
      if ("01".equals(houseType)) {
        house.setDeveloperPaybank(newSellerPayBank);
        house.setDeveloperPaybankAAcc(newPayBankAcc);
      } else {
        house.setBargainorPaybank(newSellerPayBank);
        house.setBargainorPaybankAcc(newPayBankAcc);
      }
      GiveAcc giveAcc = new GiveAcc();
      giveAcc.setContractId(contractId);
      giveAcc.setModifyDate(securityInfo.getUserInfo().getPlbizDate());
      giveAcc.setNewPokeBank(newSellerPayBank);
      giveAcc.setNewPokeBankAcc(newPayBankAcc);
      giveAcc.setOldPokeBank(oldSellerPayBank);
      giveAcc.setOldPokeBankAcc(oldPayBankAcc);
      giveAcc.setOprator(securityInfo.getUserName());
      giveAcc.setOpTime(new Date());
      giveAccDAO.insert(giveAcc);
      PlOperateLog plOperateLog = new PlOperateLog();
      // 插入日志PL021
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN
          + ""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_DO));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      plOperateLog.setOpBizId(new BigDecimal(giveAcc.getPokeBankModifyId()
          .toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void setGiveAccDAO(GiveAccDAO giveAccDAO) {
    this.giveAccDAO = giveAccDAO;
  }

  /**
   * author wsh 根据合同编号,借款人姓名，卡号，售房人查询PL131中记录数量 2007-9-26
   * 
   * @param contractId 合同编号
   * @param borrowerName 借款人姓名
   * @param cardNum 卡号
   * @param sellerName 售房人
   * @return int
   */
  public int findHouseAccCount(String contractId, String borrowerName,
      String cardNum, String sellerName, List loanBankList) throws Exception {
    // TODO Auto-generated method stub
    int count = 0;
    try {
      count = housesDAO.queryHouseCount_wsh(contractId, borrowerName, cardNum,
          sellerName, loanBankList).intValue();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 查询划款帐号维护中要打印的单条信息和在页面中显示的信息
   * 
   * @param pagination
   * @throws Exception
   * @return List
   * @author wsh
   */
  public List findHouseAccList(Pagination pagination, List loanBankList)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String contractId = "";
      String borrowerName = "";
      String cardNum = "";
      String sellerName = "";
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      if (pagination.getQueryCriterions().get("sellerName") != null) {
        sellerName = (String) pagination.getQueryCriterions().get("sellerName");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = housesDAO.queryHouseList_wsh(contractId, borrowerName, cardNum,
          sellerName, orderBy, order, start, pageSize, loanBankList);
      for (int i = 0; i < list.size(); i++) {
        HouseListDTO houseListDTO = (HouseListDTO) list.get(i);
        // 转换真实姓名
        if (houseListDTO.getOperator() != null
            && !"".equals(houseListDTO.getOperator()))
          houseListDTO.setOperator(getUserRealName(houseListDTO.getOperator()));
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询划款帐号维护中要打印列表信息
   * 
   * @param pagination
   * @throws Exception
   * @return List
   * @author wsh
   */
  public List findHouseAccPrintList(Pagination pagination, List loanBankList)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String contractId = "";
      String borrowerName = "";
      String cardNum = "";
      String sellerName = "";
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      if (pagination.getQueryCriterions().get("sellerName") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("sellerName");
      }
      list = housesDAO.queryHousePrintList_wsh(contractId, borrowerName,
          cardNum, sellerName, loanBankList);
      for (int i = 0; i < list.size(); i++) {
        HouseListDTO houseListDTO = (HouseListDTO) list.get(i);
        // 转换真实姓名
        if (houseListDTO.getOperator() != null
            && !"".equals(houseListDTO.getOperator()))
          houseListDTO.setOperator(getUserRealName(houseListDTO.getOperator()));
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  public String getUserRealName(String name) throws Exception {
    // TODO Auto-generated method stub
    String realName = "";
    try {

      realName = securityDAO.queryByUserid(name);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return realName;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
}
