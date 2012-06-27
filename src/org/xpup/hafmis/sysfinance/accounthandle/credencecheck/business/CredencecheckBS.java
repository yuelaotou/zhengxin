package org.xpup.hafmis.sysfinance.accounthandle.credencecheck.business;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnBizActivityLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.bsinterface.ICredencecheckBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckModifyDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckShowListDTO;

public class CredencecheckBS implements ICredencecheckBS {
  private BookParameterDAO bookParameterDAO = null;

  private FnOperateLogDAO fnOperateLogDAO = null;

  private FnBizActivityLogDAO fnBizActivityLogDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  private BookDAO bookDAO = null;

  private SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  /**
   * 凭证审核 author wsh 2007-10-28 查询fn102表里paramExplain字段的数据 查询条件：paramNum
   */
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo,
      String temp) {
    Object[] obj = new Object[3];
    List credenceCharacterList = null;
    List summrayList = null;
    List settTypeList = null;
    try {
      summrayList = bookParameterDAO.getParamExplain("4", "10", securityInfo);
      if (temp.equals("")) {
        credenceCharacterList = bookParameterDAO.getParamExplain("2", "",
            securityInfo);
        settTypeList = bookParameterDAO.getParamExplain("3", "", securityInfo);
      }
      obj[0] = credenceCharacterList;
      obj[1] = summrayList;
      obj[2] = settTypeList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * author wsh 凭证审核_列表
   * 
   * @param id fn201主键
   * @param securityInfo 权限
   * @param type 判断如何进入showAC
   * @2007-10-29
   * @return Object[]
   * @throws BusinessException
   */
  public Object[] findCashDayClearTbList(Pagination pagination, String type,
      SecurityInfo securityInfo) throws Exception {
    Object obj[] = new Object[3];
    // 得到账套的初始年月
    String useYearmonth = bookDAO.getUseYearmonth(securityInfo.getBookId());
    try {
      CredencecheckFindDTO credencecheckFindDTO = (CredencecheckFindDTO) pagination
          .getQueryCriterions().get("credencecheckFindDTO");

      if (credencecheckFindDTO == null) {
        credencecheckFindDTO = new CredencecheckFindDTO();
      }
      credencecheckFindDTO.setButtonPromise1("0");
      credencecheckFindDTO.setButtonPromise2("0");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List list = accountantCredenceDAO.queryCredencecheckList(type,
          credencecheckFindDTO, securityInfo, orderBy, order, start, pageSize,
          page, useYearmonth);
      for (int i = 0; i < list.size(); i++) {
        CredencecheckShowListDTO credencecheckShowListDTO = (CredencecheckShowListDTO) list
            .get(i);
        // if("0".equals(credencecheckShowListDTO.getCredenceSt())){
        // credencecheckFindDTO.setButtonPromise1("1");
        // }
        // if("1".equals(credencecheckShowListDTO.getCredenceSt())){
        // credencecheckFindDTO.setButtonPromise2("1");
        // }
        if (!credencecheckShowListDTO.getCredenceSt().equals("")) {
          credencecheckShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer
              .parseInt(credencecheckShowListDTO.getCredenceSt()),
              BusiConst.CREDSTATE));
        }
        // 转换中文
        credencecheckShowListDTO.setMakeBill(securityDAO
            .queryByUserid(credencecheckShowListDTO.getMakeBill()));
        credencecheckShowListDTO.setCheckpsn(securityDAO
            .queryByUserid(credencecheckShowListDTO.getCheckpsn()));
      }
      List countList = accountantCredenceDAO.queryCredencecheckCountList(type,
          credencecheckFindDTO, securityInfo, useYearmonth);
      BigDecimal debitSum = new BigDecimal(0.00);
      BigDecimal creditSum = new BigDecimal(0.00);
      if (countList.size() > 0) {
        for (int i = 0; i < countList.size(); i++) {
          CredencecheckShowListDTO credencecheckShowListDTO = (CredencecheckShowListDTO) countList
              .get(i);
          debitSum = debitSum.add(credencecheckShowListDTO.getDebit());
          creditSum = creditSum.add(credencecheckShowListDTO.getCredit());
          if ("0".equals(credencecheckShowListDTO.getCredenceSt())) {
            credencecheckFindDTO.setButtonPromise1("1");
          }
          if ("1".equals(credencecheckShowListDTO.getCredenceSt())) {
            credencecheckFindDTO.setButtonPromise2("1");
          }
          if (!credencecheckShowListDTO.getCredenceSt().equals("")) {
            credencecheckShowListDTO.setCredenceSt(BusiTools.getBusiValue(
                Integer.parseInt(credencecheckShowListDTO.getCredenceSt()),
                BusiConst.CREDSTATE));
          }
          if (!credencecheckShowListDTO.getMakeBill().equals("")) {
            credencecheckShowListDTO.setRealName(securityDAO
                .queryByUserid(credencecheckShowListDTO.getMakeBill()));
          }
          if (!credencecheckShowListDTO.getCheckpsn().equals("")) {
            credencecheckShowListDTO.setCheckpsn(securityDAO
                .queryByUserid(credencecheckShowListDTO.getCheckpsn()));
          }
        }
      }
      int count = countList.size();
      pagination.setNrOfElements(count);
      credencecheckFindDTO.setDebitSum(debitSum);
      credencecheckFindDTO.setCreditSum(creditSum);
      obj[0] = list;
      obj[1] = credencecheckFindDTO;
      obj[2] = countList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * author wsh 凭证审核_凭证审核_复核
   * 
   * @param id fn201主键
   * @param securityInfo 权限
   * @2007-10-29
   * @return Integer
   * @throws BusinessException
   */
  public void credencecheckCheck(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CredencecheckModifyDTO credencecheckModifyDTO = null;
    try {
      BusinessException be = null;
      String credenceSt = "1";
      String credenceNum = "";
      String credenceDate = "";
      String office = "";
      credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(id,
          securityInfo.getBookId(), "0");
      if (credencecheckModifyDTO == null) {
        be = new BusinessException("该录记不存在或不允许复核！");
        throw be;
      }
      if (credencecheckModifyDTO.getCredenceNum() != null) {
        credenceNum = credencecheckModifyDTO.getCredenceNum();
      }
      String flag = accountantCredenceDAO.checkIsEvaluate(credenceNum,
          credencecheckModifyDTO.getCredenceDate(), securityInfo.getBookId(),
          "0");
      String makeBill = credencecheckModifyDTO.getMakeBill();
      if(securityInfo.getUserInfo().getUsername().equals(makeBill)){
        throw new BusinessException("制单人和复核人不能为同一人!!!");
      }
      if (!flag.equals("0")) {
        be = new BusinessException("编号为" + credenceNum + "的凭证借贷方不平不允许复核");
        throw be;
      }
      if (credencecheckModifyDTO.getCredenceDate() != null) {
        credenceDate = credencecheckModifyDTO.getCredenceDate();
      }
      if(credencecheckModifyDTO.getOffice() != null)
        office = credencecheckModifyDTO.getOffice();
      accountantCredenceDAO.updatecredencecheck_wsh(credenceSt, credenceNum,
          credenceDate, null, securityInfo, securityInfo.getUserName());
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
      FnBizActivityLog fnBizActivityLog = new FnBizActivityLog();
      fnBizActivityLog.setCredenceNum(credenceNum);
      fnBizActivityLog.setCredenceType("0");
      fnBizActivityLog.setCredenceDate(credenceDate);
      fnBizActivityLog.setOffice(office);
      fnBizActivityLog.setAction("1");
      fnBizActivityLog.setOpTime(new Date());
      fnBizActivityLog.setOperator(securityInfo.getUserName());
      fnBizActivityLog.setBookId(securityInfo.getBookId());
      fnBizActivityLogDAO.insert(fnBizActivityLog);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void credencecheckCheck_yg(String id, SecurityInfo securityInfo,
      String a) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CredencecheckModifyDTO credencecheckModifyDTO = null;
    try {
      BusinessException be = null;
      String credenceSt = "1";
      String credenceNum = "";
      String credenceDate = "";
      String office = "";
      credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(id,
          securityInfo.getBookId(), "0");
      if (credencecheckModifyDTO == null) {
        be = new BusinessException("该录记不存在或不允许复核！");
        throw be;
      }
      String makeBill = credencecheckModifyDTO.getMakeBill();
      
      if (credencecheckModifyDTO.getCredenceNum() != null) {
        credenceNum = credencecheckModifyDTO.getCredenceNum();
      }
      String flag = accountantCredenceDAO.checkIsEvaluate(credenceNum,
          credencecheckModifyDTO.getCredenceDate(), securityInfo.getBookId(),
          "0");
      if (!flag.equals("0")) {
        be = new BusinessException("编号为" + credenceNum + "的凭证借贷方不平不允许复核");
        throw be;
      }
      if (credencecheckModifyDTO.getCredenceDate() != null) {
        credenceDate = credencecheckModifyDTO.getCredenceDate();
      }
      if(credencecheckModifyDTO.getOffice() != null)
        office = credencecheckModifyDTO.getOffice();
      accountantCredenceDAO.updatecredencecheck_wsh_yg(credenceSt, credenceNum,
          credenceDate, null, securityInfo, securityInfo.getUserName(), a);
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
      FnBizActivityLog fnBizActivityLog = new FnBizActivityLog();
      fnBizActivityLog.setCredenceNum(credenceNum);
      fnBizActivityLog.setCredenceType("0");
      fnBizActivityLog.setCredenceDate(credenceDate);
      fnBizActivityLog.setOffice(office);
      fnBizActivityLog.setAction("1");
      fnBizActivityLog.setOpTime(new Date());
      fnBizActivityLog.setOperator(securityInfo.getUserName());
      fnBizActivityLog.setBookId(securityInfo.getBookId());
      fnBizActivityLogDAO.insert(fnBizActivityLog);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 凭证审核_凭证审核_撤销复核
   * 
   * @param id fn201主键
   * @param securityInfo 权限
   * @2007-10-29
   * @return Integer
   * @throws BusinessException
   */
  public void credencecheckDelCheck(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CredencecheckModifyDTO credencecheckModifyDTO = null;
    try {
      BusinessException be = null;
      String credenceSt = "0";
      String credenceNum = "";
      String credenceDate = "";
      String office = "";
      credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(id,
          securityInfo.getBookId(), "1");
      if (credencecheckModifyDTO == null) {
        be = new BusinessException("该录记不存在或不允许撤销复核！");
        throw be;
      }
      if (credencecheckModifyDTO.getCredenceNum() != null) {
        credenceNum = credencecheckModifyDTO.getCredenceNum();
      }
      if (credencecheckModifyDTO.getCredenceDate() != null) {
        credenceDate = credencecheckModifyDTO.getCredenceDate();
      }
      if (credencecheckModifyDTO.getOffice() != null) {
        office = credencecheckModifyDTO.getOffice();
      }
      accountantCredenceDAO.updatecredencecheck_wsh(credenceSt, credenceNum,
          credenceDate, null, securityInfo, "");
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_AUDITINGQUASH));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
      fnBizActivityLogDAO.deleteFnBizActivityLog_wsh(credenceNum, credenceDate,
          office, securityInfo);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh 凭证审核_凭证审核_批量复核
   * 
   * @param list fn201全部记录list
   * @param securityInfo 权限
   * @2007-10-29
   * @return Integer
   * @throws BusinessException
   */
  public void credencecheckCheckAll(List list, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CredencecheckModifyDTO credencecheckModifyDTO = null;
    try {
      BusinessException be = null;
      String credenceSt = "1";
      String credenceNum = "";
      String credenceDate = "";
      String office = "";
      String flag = "";
      Iterator iter = list.iterator();
      while (iter.hasNext()) {
        CredencecheckShowListDTO element = (CredencecheckShowListDTO) iter
            .next();
        credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(
            element.getCredenceId(), securityInfo.getBookId(), "0");
        if (credencecheckModifyDTO == null) {
          be = new BusinessException("该录记不存在或不允许复核！");
          throw be;
        }
      }
      Iterator iter1 = list.iterator();
      while (iter1.hasNext()) {
        CredencecheckShowListDTO element = (CredencecheckShowListDTO) iter1
            .next();
        if (element.getCredenceNum() != null) {
          credenceNum = element.getCredenceNum();
        }
        
        if (element.getCredenceDate() != null) {
          credenceDate = element.getCredenceDate();
        }
        
        if(credencecheckModifyDTO.getOffice() != null)
          office = credencecheckModifyDTO.getOffice();
        
        String makeBill = credencecheckModifyDTO.getMakeBill();
        if(securityInfo.getUserInfo().getUsername().equals(makeBill)){
          throw new BusinessException("编号为" + credenceNum + "的凭证制单人和复核人不能为同一人!!!");
        }
        flag = accountantCredenceDAO.checkIsEvaluate(credenceNum,
            credenceDate, securityInfo.getBookId(), "0");
        if (!flag.equals("0")) {
          be = new BusinessException("编号为" + credenceNum + "的凭证借贷方不平不允许复核");
          throw be;
        }
        accountantCredenceDAO.updatecredencecheck_wsh(credenceSt, credenceNum,
            credenceDate, null, securityInfo, securityInfo.getUserName());
        FnBizActivityLog fnBizActivityLog = new FnBizActivityLog();
        fnBizActivityLog.setCredenceNum(credenceNum);
        fnBizActivityLog.setCredenceType("0");
        fnBizActivityLog.setCredenceDate(credenceDate);
        fnBizActivityLog.setOffice(office);
        fnBizActivityLog.setAction("1");
        fnBizActivityLog.setOpTime(new Date());
        fnBizActivityLog.setOperator(securityInfo.getUserName());
        fnBizActivityLog.setBookId(securityInfo.getBookId());
        fnBizActivityLogDAO.insert(fnBizActivityLog);
      }
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      throw new BusinessException("批量复核失败！");
    }
  }

  /**
   * author wsh 凭证审核_凭证审核_撤销批量复核
   * 
   * @param list fn201全部记录list
   * @param securityInfo 权限
   * @2007-10-29
   * @return Integer
   * @throws BusinessException
   */
  public void credencecheckDelCheckAll(List list, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CredencecheckModifyDTO credencecheckModifyDTO = null;
    try {
      BusinessException be = null;
      String credenceSt = "0";
      String credenceNum = "";
      String credenceDate = "";
      String office = "";
      Iterator iter = list.iterator();
      while (iter.hasNext()) {
        CredencecheckShowListDTO element = (CredencecheckShowListDTO) iter
            .next();
        credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(
            element.getCredenceId(), securityInfo.getBookId(), "1");
        if (credencecheckModifyDTO == null) {
          be = new BusinessException("该录记不存在或不允许撤销批量复核！");
          throw be;
        }
      }
      Iterator iter1 = list.iterator();
      while (iter1.hasNext()) {
        CredencecheckShowListDTO element = (CredencecheckShowListDTO) iter1
            .next();
        if (element.getCredenceNum() != null) {
          credenceNum = element.getCredenceNum();
        }
        if (element.getCredenceDate() != null) {
          credenceDate = element.getCredenceDate();
        }
        if (element.getOffice() != null) {
          office = element.getOffice();
        }
        accountantCredenceDAO.updatecredencecheck_wsh(credenceSt, credenceNum,
            credenceDate, null, securityInfo, "");
      }
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_AUDITINGQUASH));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
      fnBizActivityLogDAO.deleteFnBizActivityLog_wsh(credenceNum, credenceDate,
          office, securityInfo);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setFnBizActivityLogDAO(FnBizActivityLogDAO fnBizActivityLogDAO) {
    this.fnBizActivityLogDAO = fnBizActivityLogDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

}
