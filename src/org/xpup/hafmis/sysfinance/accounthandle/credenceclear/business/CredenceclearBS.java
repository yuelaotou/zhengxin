package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.business;

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
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumCancelDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumMaintainDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.bsinterface.ICredenceclearBS;
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.dto.CredenceclearModifyDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckModifyDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckShowListDTO;

public class CredenceclearBS implements ICredenceclearBS {

  private SecurityDAO securityDAO = null;

  private BookParameterDAO bookParameterDAO = null;

  private FnOperateLogDAO fnOperateLogDAO = null;

  private FnBizActivityLogDAO fnBizActivityLogDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  private FnDocNumCancelDAO fnDocNumCancelDAO = null;

  private FnDocNumMaintainDAO fnDocNumMaintainDAO = null;

  private BookDAO bookDAO = null;

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

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
   * author wsh 凭证过账_列表
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
    // 得到账套的初始年月
    String useYearmonth = bookDAO.getUseYearmonth(securityInfo.getBookId());
    Object obj[] = new Object[3];
    List resultList = new ArrayList();
    try {
      CredencecheckFindDTO credencecheckFindDTO = (CredencecheckFindDTO) pagination
          .getQueryCriterions().get("credencecheckFindDTO");
      if (credencecheckFindDTO == null) {
        credencecheckFindDTO = new CredencecheckFindDTO();
      }
      credencecheckFindDTO.setButtonPromise1("0");
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
        if ("1".equals(credencecheckShowListDTO.getCredenceSt())) {
          credencecheckFindDTO.setButtonPromise1("1");
        }
        if ((!credencecheckShowListDTO.getCredenceCharacter().equals(""))
            && (!credencecheckShowListDTO.getCredenceNum().equals(""))) {
          credencecheckShowListDTO.setCredenceChaNum(bookParameterDAO
              .queryParamExplainByParaId(credencecheckShowListDTO
                  .getCredenceCharacter())
              + "-" + credencecheckShowListDTO.getCredenceNum());
        } else if ((!credencecheckShowListDTO.getCredenceNum().equals(""))
            && credencecheckShowListDTO.getCredenceCharacter().equals("")) {
          credencecheckShowListDTO.setCredenceChaNum(credencecheckShowListDTO
              .getCredenceNum());
        }
        credencecheckShowListDTO
            .setTemp_credenceChaNum(credencecheckShowListDTO
                .getCredenceCharacter()
                + "-" + credencecheckShowListDTO.getCredenceNum() + "-1");
        if (!credencecheckShowListDTO.getSummary().equals("")) {
          credencecheckShowListDTO
              .setTemp_summary(bookParameterDAO
                  .queryParamExplainByParaId(credencecheckShowListDTO
                      .getSummary()));
        }
        if (!credencecheckShowListDTO.getCredenceSt().equals("")) {
          credencecheckShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer
              .parseInt(credencecheckShowListDTO.getCredenceSt()),
              BusiConst.CREDSTATE));
        }
        // 转换中文
        credencecheckShowListDTO.setMakeBill(securityDAO
            .queryByUserid(credencecheckShowListDTO.getMakeBill()));
        resultList.add(credencecheckShowListDTO);
      }
      List countList = accountantCredenceDAO.queryCredencecheckCountList(type,
          credencecheckFindDTO, securityInfo, useYearmonth);
      BigDecimal debitSum = new BigDecimal(0.00);
      BigDecimal creditSum = new BigDecimal(0.00);
      if (countList.size() > 0) {
        for (int i = 0; i < countList.size(); i++) {

          CredencecheckShowListDTO credencecheckShowListDTO = (CredencecheckShowListDTO) countList
              .get(i);
          if ("1".equals(credencecheckShowListDTO.getCredenceSt())) {
            credencecheckFindDTO.setButtonPromise1("1");
          }
          debitSum = debitSum.add(credencecheckShowListDTO.getDebit());
          creditSum = creditSum.add(credencecheckShowListDTO.getCredit());
          if (!credencecheckShowListDTO.getSummary().equals("")) {
            credencecheckShowListDTO.setTemp_summary(bookParameterDAO
                .queryParamExplainByParaId(credencecheckShowListDTO
                    .getSummary()));
          }
          if (!credencecheckShowListDTO.getCredenceSt().equals("")) {
            credencecheckShowListDTO.setCredenceSt(BusiTools.getBusiValue(
                Integer.parseInt(credencecheckShowListDTO.getCredenceSt()),
                BusiConst.CREDSTATE));
          }
          if ((!credencecheckShowListDTO.getCredenceCharacter().equals(""))
              && (!credencecheckShowListDTO.getCredenceNum().equals(""))) {
            credencecheckShowListDTO.setCredenceChaNum(bookParameterDAO
                .queryParamExplainByParaId(credencecheckShowListDTO
                    .getCredenceCharacter())
                + "-" + credencecheckShowListDTO.getCredenceNum());
          } else if ((!credencecheckShowListDTO.getCredenceNum().equals(""))
              && credencecheckShowListDTO.getCredenceCharacter().equals("")) {
            credencecheckShowListDTO.setCredenceChaNum(credencecheckShowListDTO
                .getCredenceNum());
          }
        }
      }
      int count = countList.size();
      pagination.setNrOfElements(count);
      credencecheckFindDTO.setDebitSum(debitSum);
      credencecheckFindDTO.setCreditSum(creditSum);
      obj[0] = resultList;
      obj[1] = credencecheckFindDTO;
      obj[2] = countList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * author wsh 凭证划账_插入操作日志
   * 
   * @param securityInfo 权限
   * @2007-11—01
   * @return
   * @throws Exception
   */
  public void credenceclearSaveOperLog(SecurityInfo securityInfo)
      throws Exception {
    // TODO Auto-generated method stub
    FnOperateLog fnOperateLog = new FnOperateLog();
    fnOperateLog.setBookId(securityInfo.getBookId());
    fnOperateLog.setOpButton(String
        .valueOf(BusiLogConst.BIZLOG_ACTION_ACCOUNTIN));
    fnOperateLog.setOperator(securityInfo.getUserName());
    fnOperateLog.setOpIp(securityInfo.getUserIp());
    fnOperateLog.setOpModel(String
        .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECLEAR));
    fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
    fnOperateLog.setOpTime(new Date());
    fnOperateLogDAO.insert(fnOperateLog);
  }

  /**
   * author wsh 凭证划账_插入业务日志
   * 
   * @param securityInfo 权限
   * @param credenceNum 凭证号
   * @param credenceDate 凭证日期
   * @param office 办事处
   * @2007-11—01
   * @return
   * @throws Exception
   */
  public void credenceclearSaveBizLog(String credenceNum, String credenceDate,
      String office, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    FnBizActivityLog fnBizActivityLog = new FnBizActivityLog();
    fnBizActivityLog.setCredenceNum(credenceNum);
    fnBizActivityLog.setCredenceType("0");
    fnBizActivityLog.setCredenceDate(credenceDate);
    fnBizActivityLog.setOffice(office);
    fnBizActivityLog.setAction("2");
    fnBizActivityLog.setOpTime(new Date());
    fnBizActivityLog.setOperator(securityInfo.getUserName());
    fnBizActivityLog.setBookId(securityInfo.getBookId());
    fnBizActivityLogDAO.insert(fnBizActivityLog);
  }

  /**
   * author wsh 凭证过账_复选框划账
   * 
   * @param countList 所有符合条件记录
   * @param securityInfo 权限
   * @2007-10-31
   * @throws BusinessException
   */
  public void credenceclear(String id[], SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      String type = "0";
      CredencecheckModifyDTO credencecheckModifyDTO = null;
      CredenceclearModifyDTO credenceclearModifyDTO = new CredenceclearModifyDTO();
      int minNum = 0;// 要扎账的凭证中最小的凭证号
      int maxNum = 0;// 要扎账的凭证中最大的凭证号
      String maxCredenceNum = "";// fn201中最大的凭证号

      int settleType = securityInfo.getFnSettleType();// 核算方式
      List subjectList = new ArrayList();
      List list = new ArrayList();
      String date = "";
      String temp_creNum = "";
      int count = 0;// 记录要记账的不重复的凭证号个数
      for (int i = 0; i < id.length; i++) {
        credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(
            id[i], securityInfo.getBookId(), "1");
        if (credencecheckModifyDTO == null) {
          throw new BusinessException("该记录不存在或不允许过账！");
        }
        if (i == 0) {
          date = credencecheckModifyDTO.getCredenceDate();
          if (settleType == 0) {
            maxCredenceNum = accountantCredenceDAO.getMaxCredenceNum(
                securityInfo.getBookId(), null, date.substring(0, 6));
          } else {
            maxCredenceNum = accountantCredenceDAO.getMaxCredenceNum(
                securityInfo.getBookId(), credencecheckModifyDTO.getOffice(),
                date.substring(0, 6));
          }
          minNum = maxNum = Integer.parseInt(credencecheckModifyDTO
              .getCredenceNum());
          temp_creNum = credencecheckModifyDTO.getCredenceNum();
          count++;
        } else {
          if ((Integer.parseInt(credencecheckModifyDTO.getCredenceNum()) < minNum))
            minNum = Integer.parseInt(credencecheckModifyDTO.getCredenceNum());
          if ((Integer.parseInt(credencecheckModifyDTO.getCredenceNum()) > maxNum))
            maxNum = Integer.parseInt(credencecheckModifyDTO.getCredenceNum());
          if (!temp_creNum.equals(credencecheckModifyDTO.getCredenceNum())) {
            temp_creNum = credencecheckModifyDTO.getCredenceNum();
            count++;
          }
        }
        list.add(credencecheckModifyDTO);
      }
      System.out.println(count + "==========>");
      System.out.println(maxCredenceNum + "==========>");
      System.out.println(minNum + "==========>");
      System.out.println(maxNum + "==========>");
      if (maxCredenceNum == null) {
        if (minNum != 1 || count + minNum <= maxNum)
          throw new BusinessException("由于凭证不连续问题不允许过账！");
      } else if (count + Integer.parseInt(maxCredenceNum) != maxNum) {
        throw new BusinessException("由于凭证不连续问题不允许过账！");
      }

      for (int i = 0; i < id.length; i++) {
        String credenceId = id[i];
        CredencecheckModifyDTO credencecheckShowListDTO = (CredencecheckModifyDTO) list
            .get(i);
        try {
          String credenceNum = "";
          String credenceDate = "";
          String office = "";
          if (credencecheckShowListDTO.getCredenceNum() != null) {
            credenceNum = credencecheckShowListDTO.getCredenceNum();
          }
          if (credencecheckShowListDTO.getCredenceDate() != null) {
            credenceDate = credencecheckShowListDTO.getCredenceDate();
          }
          if (credencecheckShowListDTO.getOffice() != null) {
            office = credencecheckShowListDTO.getOffice();
          }
          subjectList = accountantCredenceDAO.querySubjectCodeList_wsh(office,
              credenceNum, credenceDate, securityInfo.getBookId());
          credenceclearModifyDTO = accountantCredenceDAO
              .findCredenceclearSummary(credenceId);
          if (credenceclearModifyDTO != null) {
            for (int j = 0; j < subjectList.size(); j++) {
              accountantCredenceDAO.updateCredenceclear_wsh(
                  (String) subjectList.get(j), office, securityInfo);
            }
            accountantCredenceDAO.updateCredenceclear1_wsh(
                credenceclearModifyDTO.getCredenceNum(), credenceclearModifyDTO
                    .getCredenceDate(), null,
                securityInfo);
            // 插入日志
            if (!"1".equals(type)) {
              this.credenceclearSaveOperLog(securityInfo);
            }
            this.credenceclearSaveBizLog(credenceNum, credenceDate, office,
                securityInfo);
          } else { // 获取不到credenceclearModifyDTO
            throw new BusinessException("该记录不存在或不允许过账！");
          }
        } catch (BusinessException e) {
          e.printStackTrace();
          throw e;
        } catch (Exception e) {
          e.printStackTrace();
          throw new BusinessException("过账失败");
        }
      }

    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();

    }
  }

  /**
   * author wsh 凭证过账_全部划账
   * 
   * @param countList 所有符合条件记录
   * @param securityInfo 权限
   * @2007-10-31
   * @throws BusinessException
   */
  public void credenceclearAll(List countList, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      String type = "1";
      CredencecheckModifyDTO credencecheckModifyDTO = null;
      Iterator iter = countList.iterator();
      List subjectList = new ArrayList();
      int minNum = 0;// 要扎账的凭证中最小的凭证号
      int maxNum = 0;// 要扎账的凭证中最大的凭证号
      String maxCredenceNum = "";// fn201中最大的凭证号

      int settleType = securityInfo.getFnSettleType();// 核算方式
      String date = "";
      String temp_creNum = "";
      int count = 0;// 记录要记账的不重复的凭证号个数
      for (int i = 0; i < countList.size(); i++) {
        CredencecheckShowListDTO credencecheckShowListDTO = (CredencecheckShowListDTO) countList
            .get(i);
        
        credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(
            credencecheckShowListDTO.getCredenceId(), securityInfo.getBookId(), "1");
        if (credencecheckModifyDTO == null) {
          throw new BusinessException("该录记不存在或不允许过账！");
        }
        credencecheckModifyDTO = accountantCredenceDAO.findcredencecheckCheck(
            credencecheckShowListDTO.getCredenceId(), securityInfo.getBookId(),
            "1");
        if (i == 0) {
          date = credencecheckModifyDTO.getCredenceDate();
          if (settleType == 0) {
            maxCredenceNum = accountantCredenceDAO.getMaxCredenceNum(
                securityInfo.getBookId(), null, date.substring(0, 6));
          } else {
            maxCredenceNum = accountantCredenceDAO.getMaxCredenceNum(
                securityInfo.getBookId(), credencecheckModifyDTO.getOffice(),
                date.substring(0, 6));
          }
          minNum = maxNum = Integer.parseInt(credencecheckModifyDTO
              .getCredenceNum());
          temp_creNum = credencecheckModifyDTO.getCredenceNum();
          count++;
        } else {
          if ((Integer.parseInt(credencecheckModifyDTO.getCredenceNum()) < minNum))
            minNum = Integer.parseInt(credencecheckModifyDTO.getCredenceNum());
          if ((Integer.parseInt(credencecheckModifyDTO.getCredenceNum()) > maxNum))
            maxNum = Integer.parseInt(credencecheckModifyDTO.getCredenceNum());
          if (!temp_creNum.equals(credencecheckModifyDTO.getCredenceNum())) {
            temp_creNum = credencecheckModifyDTO.getCredenceNum();
            count++;
          }
        }
      }
      System.out.println(count + "==========>");
      System.out.println(maxCredenceNum + "==========>");
      System.out.println(minNum + "==========>");
      System.out.println(maxNum + "==========>");
      if (maxCredenceNum == null) {
        if (minNum != 1 || count + minNum <= maxNum)
          throw new BusinessException("由于凭证不连续问题不允许过账！");
      } else if (count + Integer.parseInt(maxCredenceNum) != maxNum) {
        throw new BusinessException("由于凭证不连续问题不允许过账！");
      }
      while (iter.hasNext()) {
        CredencecheckShowListDTO credencecheckShowListDTO = (CredencecheckShowListDTO) iter
            .next();
        String id = credencecheckShowListDTO.getCredenceId();
        try {
          String credenceNum = "";
          String credenceDate = "";
          String office = "";
          CredenceclearModifyDTO credenceclearModifyDTO = new CredenceclearModifyDTO();
          if (credencecheckShowListDTO.getCredenceNum() != null) {
            credenceNum = credencecheckShowListDTO.getCredenceNum();
          }
          if (credencecheckShowListDTO.getCredenceDate() != null) {
            credenceDate = credencecheckShowListDTO.getCredenceDate();
          }
          if (credencecheckShowListDTO.getOffice() != null) {
            office = credencecheckShowListDTO.getOffice();
          }
          subjectList = accountantCredenceDAO.querySubjectCodeList_wsh(office,
              credenceNum, credenceDate, securityInfo.getBookId());
          credenceclearModifyDTO = accountantCredenceDAO
              .findCredenceclearSummary(id);
          if (credenceclearModifyDTO != null) {
            for (int j = 0; j < subjectList.size(); j++) {
              accountantCredenceDAO.updateCredenceclear_wsh(
                  (String) subjectList.get(j), office, securityInfo);
            }
            accountantCredenceDAO.updateCredenceclear1_wsh(
                credenceclearModifyDTO.getCredenceNum(), credenceclearModifyDTO
                    .getCredenceDate(), null,
                securityInfo);
            // 插入日志
            if (!"1".equals(type)) {
              this.credenceclearSaveOperLog(securityInfo);
            }
            this.credenceclearSaveBizLog(credenceNum, credenceDate, office,
                securityInfo);
          } else { // 获取不到credenceclearModifyDTO
            throw new BusinessException("该记录不存在或不允许过账！");
          }
        } catch (BusinessException e) {
          e.printStackTrace();
          throw e;
        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
          throw new BusinessException("过账失败");
        }
      }
      this.credenceclearSaveOperLog(securityInfo);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      throw new BusinessException("全部过账失败");
    }
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setFnBizActivityLogDAO(FnBizActivityLogDAO fnBizActivityLogDAO) {
    this.fnBizActivityLogDAO = fnBizActivityLogDAO;
  }

  public void setFnDocNumCancelDAO(FnDocNumCancelDAO fnDocNumCancelDAO) {
    this.fnDocNumCancelDAO = fnDocNumCancelDAO;
  }

  public void setFnDocNumMaintainDAO(FnDocNumMaintainDAO fnDocNumMaintainDAO) {
    this.fnDocNumMaintainDAO = fnDocNumMaintainDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

}
