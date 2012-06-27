package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.bookmng.settleincanddec.bsinterface.ISettleincanddecBS;
import org.xpup.hafmis.sysfinance.common.dao.SettleIncAndDecDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.SettleIncAndDec;

public class SettleincanddecBS implements ISettleincanddecBS {
  private FnOperateLogDAO fnOperateLogDAO = null;

  private SubjectDAO subjectDAO = null;

  private SettleIncAndDecDAO settleIncAndDecDAO = null;

  public void setFnOperateLog(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setSettleIncAndDecDAO(SettleIncAndDecDAO settleIncAndDecDAO) {
    this.settleIncAndDecDAO = settleIncAndDecDAO;
  }

  /**
   * author wsh 损益结转设置 查询待结转科目代码在FN202.BY_SUBJECT_CODE中是否存在
   * 
   * @param subjectCode 科目代码
   * @param securityInfo 权限
   * @2007-10-25
   * @return
   */
  public Integer findSettleIncAndDecInfoExist(String subjectCode,
      SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    Integer count = new Integer(0);
    try {
      count = settleIncAndDecDAO.findSettleIncAndDecExist_wsh(subjectCode,
          securityInfo);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * author wsh 损益结转设置 查询待结转科目代码在FN110.BALANCE_DIRECTION
   * 
   * @param subjectCode 待结转科目代码
   * @param securityInfo 权限
   * @2007-10-25
   * @return
   */
  public String findSettleIncAndDecSubjectDirection(String bySubjectCode,
      SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    String subjectDirection = "";
    try {
      subjectDirection = subjectDAO.findSettleIncAndDecSubjectDirection(
          bySubjectCode, securityInfo);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return subjectDirection;
  }

  /**
   * author wsh 损益结转设置 插入数据
   * 
   * @param subjectCode 结转科目代码
   * @param bysubjectCode 待结转科目代码
   * @param bySubjectDirection 待结转科目方向
   * @param securityInfo 权限
   * @2007-10-25
   * @return
   */
  public void saveSettleIncAndDec(String bySubjectCode, String subjectCode,
      String bySubjectDirection, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      SettleIncAndDec settleIncAndDec = new SettleIncAndDec();
      if (subjectCode != null && !"".equals(subjectCode.trim())) {
        settleIncAndDec.setSubjectCode(subjectCode);
      }
      if (bySubjectCode != null && !"".equals(bySubjectCode.trim())) {
        settleIncAndDec.setBySubjectCode(bySubjectCode);
      }
      if (bySubjectDirection != null && !"".equals(bySubjectDirection.trim())) {
        settleIncAndDec.setSubjectDirection(bySubjectDirection);
        if ("0".equals(bySubjectDirection)) {
          bySubjectDirection = "1";
        } else {
          bySubjectDirection = "0";
        }
        settleIncAndDec.setBySubjectDirection(bySubjectDirection);
      }
      settleIncAndDec.setBookId(securityInfo.getBookId());
      settleIncAndDecDAO.insert(settleIncAndDec);
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_BOOKMNG_SETTLEINCADDEC));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * author wsh损益结转设置查询列表信息
   * 
   * @param pagination
   * @param securityInfo 权限
   * @2007-10-24
   * @return
   */
  public List querySettleIncAndDecList(SecurityInfo securityInfo,
      Pagination pagination) {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    int count = 0;
    String bookId = "";
    try {
      if (securityInfo.getBookId() != null) {
        bookId = securityInfo.getBookId();
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = settleIncAndDecDAO.querySettleIncAndDecList_wsh(bookId, orderBy,
          order, start, pageSize, page);
      count = settleIncAndDecDAO.querySettleIncAndDecCountList_wsh(bookId);
      pagination.setNrOfElements(count);

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * author wsh 损益结转设置 删除记录
   * 
   * @param id fn202主键
   * @param securityInfo 权限
   * @2007-10-24
   * @return
   */
  public void deleteSettleIncAndDec(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      BusinessException be = null;
      int count = 0;
      count = settleIncAndDecDAO.findSettleIncAndDecExist_wsh(id,
          securityInfo.getBookId()).intValue();
      if (count == 0) {
        be = new BusinessException("该记录已经删除！");
        throw be;
      } else {
        settleIncAndDecDAO.deletSettleIncAndDec_wsh(id, securityInfo
            .getBookId());
        FnOperateLog fnOperateLog = new FnOperateLog();
        fnOperateLog.setBookId(securityInfo.getBookId());
        fnOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpModel(String
            .valueOf(BusiLogConst.FN_OP_BOOKMNG_SETTLEINCADDEC));
        fnOperateLog.setOpSys(String
            .valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
        fnOperateLog.setOpTime(new Date());
        fnOperateLogDAO.insert(fnOperateLog);
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
   * author wsh 损益结转设置 确定 判断科目代码是否是末级科目代码
   * 
   * @param bySubjectCode 待结转科目代码
   * @param securityInfo 权限
   * @2007-10-24
   * @return
   */
  public void findSubjectrelationParentId(String bySubjectCode,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      BusinessException be = null;
      count = subjectDAO.findSubjectrelationParentId(bySubjectCode,
          securityInfo).intValue();
      if (count == 1) {
        be = new BusinessException("待结转科目代码不是末级科目代码，请重新输入！");
        throw be;
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
   * author wsh 损益结转设置 确定 判断科目代码是否是末级科目代码
   * 
   * @param bySubjectCode 待结转科目代码
   * @param securityInfo 权限
   * @2007-10-24
   * @return
   */
  public void findSubjectrelationParentId1(String SubjectCode,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = 0;
      BusinessException be = null;
      count = subjectDAO.findSubjectrelationParentId(SubjectCode, securityInfo)
          .intValue();
      if (count == 1) {
        be = new BusinessException("结转科目代码不是末级科目代码，请重新输入！");
        throw be;
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
   * author wsh 科目关系设置 判断科目代码是否是最后一级科目代码
   * 
   * @param subjectCode 科目代码
   * @2007-10-19
   * @return
   */
  public void findSubjectRelationFirstCode(String subjectCode,SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      BusinessException be = null;
      int count = 0;
      String code="";
      code=subjectDAO.is_CodeIn_WL(subjectCode, "0", securityInfo);
      if("".equals(code)||code==null){
        be = new BusinessException("该科目代码不存在！");
        throw be;
      }
      count=subjectDAO.findSubjectrelationParentId(subjectCode,securityInfo).intValue();
      if (count != 0) {
        be = new BusinessException("该科目代码不是最后一级科目！");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
}
