package org.xpup.hafmis.sysfinance.bookmng.subject.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface.ISubjectBS;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.CredenceModleDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SettleIncAndDecDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectRelationDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.Subject;

public class SubjectBS implements ISubjectBS {

  private BookParameterDAO bookParameterDAO = null;

  private SubjectDAO subjectDAO = null;

  private FnOperateLogDAO fnOperateLogDAO = null;

  private SubjectRelationDAO subjectRelationDAO = null;

  private CredenceModleDAO credenceModleDAO = null;

  private SettleIncAndDecDAO settleIncAndDecDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  private TreasurerCredenceDAO treasurerCredenceDAO = null;

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setSubjectRelationDAO(SubjectRelationDAO subjectRelationDAO) {
    this.subjectRelationDAO = subjectRelationDAO;
  }

  public void setCredenceModleDAO(CredenceModleDAO credenceModleDAO) {
    this.credenceModleDAO = credenceModleDAO;
  }

  public void setSettleIncAndDecDAO(SettleIncAndDecDAO settleIncAndDecDAO) {
    this.settleIncAndDecDAO = settleIncAndDecDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }

  /**
   * 新增会计科目
   */
  public void saveSubject(SubjectShowAF af, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      String code = af.getCode();// 科目代码
      String name = af.getName();// 科目名称
      String direction = af.getDirection();// 余额方向
      String sortcode = af.getSortcode();// 科目类别
      String property = af.getProperty();// 科目属性
      
      // 判断科目代码是否符合该账套代码结构
      String level = this.is_Book_CodeStructure(code, securityInfo);
      // 判断输入的科目代码在是否为一级科目代码
      boolean flag = this.is_CodeLevel_One(code, securityInfo);
      String codeLevelUp = "";
      if (flag == false) {// 非一级科目
        // 取得对应的上一级科目代码的位数
        int temp_length = this.getCodeLevel_up(code, securityInfo);
        codeLevelUp = code.substring(0, temp_length);
      }

      //判断是否已经存在该科目代码
      String codeid=subjectDAO.is_CodeIn_WL(code,null,securityInfo);
      if(codeid!=null&&!codeid.equals("")){
        throw new BusinessException("科目代码信息已经录入!!");
      }
      
      
      Subject subject = new Subject();
      subject.setBookId(securityInfo.getBookId());
      subject.setSubjectCode(code);
      subject.setSubjectName(name);
      if (codeLevelUp != null && !codeLevelUp.equals("")) {
        subject.setParentSubjectCode(codeLevelUp);
      }
      if (!level.equals("0")) {
        subject.setSubjectLevel(level);
      }
      subject.setSubjectSortCode(sortcode);
      subject.setBalanceDirection(direction);
      subject.setSubjectProperty(property);
      subject.setSubjectSt("0");
      subject.setBizDate(securityInfo.getUserInfo().getFbizDate().substring(0,6));
      subjectDAO.insert(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
  }

  /**
   * 判断科目代码是否符合该账套代码结构,若符合-返回输入科目应属于的级别
   */
  public String is_Book_CodeStructure(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    boolean flag = false;
    String level = "0";
    List codeLength = new ArrayList();
    codeLength = bookParameterDAO.getParamExplain_WL("1", securityInfo);
    if (codeLength.size() != 0) {
      String[] codestr = new String[codeLength.size()];
      for (int i = 0; i < codeLength.size(); i++) {
        codestr[i] = (String) codeLength.get(i);
      }
      String[] temp_codestr = new String[codeLength.size()];
      temp_codestr[0] = "0";
      for (int i = 0; i < codeLength.size(); i++) {
        if (i == 0) {
          temp_codestr[i] = "" + (Integer.parseInt(codestr[i]));
        } else {
          temp_codestr[i] = ""
              + (Integer.parseInt(temp_codestr[i - 1]) + Integer
                  .parseInt(codestr[i]));
        }
      }
      for (int i = 0; i < codeLength.size(); i++) {
        if (temp_codestr[i].equals((new Integer(code.length()).toString()))) {
          flag = true;
          level = new Integer(i + 1).toString();
          break;
        }
      }
    }
    if (flag == false) {
      throw new BusinessException("请输入正确的科目代码!!");
    }
    return level;
  }

  /**
   * 判断输入的科目代码是否存在
   */
  public String is_Code_In(String code, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    String subjectId = "";
    try {
      subjectId = subjectDAO.is_CodeIn_WL(code, null, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return subjectId;
  }

  /**
   * 判断输入的科目代码是否为一级科目代码
   */
  public boolean is_CodeLevel_One(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    boolean flag = false;
    List codeLength = new ArrayList();
    try {
      codeLength = bookParameterDAO.getParamExplain_WL("1", securityInfo);
      if (codeLength.size() != 0) {
        String codestr = (String) codeLength.get(0);
        if (codestr.equals((new Integer(code.length()).toString()))) {
          flag = true;
        }
      }
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return flag;
  }

  /**
   * 取得上一级科目的位数
   */
  public int getCodeLevel_up(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    int length = 0;
    List codeLength = new ArrayList();
   try{
    codeLength = bookParameterDAO.getParamExplain_WL("1", securityInfo);
    if (codeLength.size() != 0) {
      String[] codestr = new String[codeLength.size()];
      for (int i = 0; i < codeLength.size(); i++) {
        codestr[i] = (String) codeLength.get(i);
      }
      String[] temp_codestr = new String[codeLength.size()];
      temp_codestr[0] = "0";
      for (int i = 0; i < codeLength.size(); i++) {
        if (i == 0) {
          temp_codestr[i] = "" + (Integer.parseInt(codestr[i]));
        } else {
          temp_codestr[i] = ""
              + (Integer.parseInt(temp_codestr[i - 1]) + Integer
                  .parseInt(codestr[i]));
        }
      }
      for (int i = 0; i < codeLength.size(); i++) {
        if (temp_codestr[i].equals((new Integer(code.length()).toString()))) {
          length = Integer.parseInt(temp_codestr[i - 1]);
          break;
        }
      }
    }
   } catch (Exception be) {
     be.printStackTrace();
     throw new BusinessException("查询错误");
   }
    return length;
  }

  /**
   * 判断科目代码是否已经存在，and SUBJECT_ST=0
   */
  public String is_ParentCode_Normal(String code, String states,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String parentCode = "";
    try {
      parentCode = subjectDAO.is_CodeIn_WL(code, states, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return parentCode;
  }

  /**
   * 判断输入的科目代码的上一级科目在FN111.SUBJECT_CODE中是否存在
   */
  public List is_ParentCodeRelation_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = subjectRelationDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return list;
  }

  /**
   * 根据科目类别查询科目信息
   */
  public List findSubjectTree(SubjectShowAF af, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = subjectDAO.findSubjectTree_WL(af.getSortcodeflag(), securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return list;
  }

  /**
   * 得到科目的科目类别
   */
  public String getSortcodeByCode(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String sortcode = "";
    try {
      sortcode = subjectDAO.getSortcodeByCode_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return sortcode;
  }

  /**
   * 得到科目的科目方向
   */
  public String getDirectionByCode(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String direction = "";
    try {
      direction = subjectDAO.getDirectionByCode_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return direction;
  }

  /**
   * 得到科目的科目属性
   */
  public String getProperyByCode_WL(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String propery = "";
    try {
      propery = subjectDAO.getProperyByCode_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return propery;
  }

  /**
   * 删除会计科目
   */
  public void deleteSubject(String codeid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // 取得对应的科目代码
      String subjectCode = subjectDAO.is_CodeInBySubjectId_WL(codeid, null,
          securityInfo);
      // 判断该条记录的SUBJECT_ID在FN110.SUBJECT_ID中是否存在
      String subjectId = this.is_Code_In(subjectCode, securityInfo);
      if (subjectId == null || subjectId.equals("")) {
        throw new BusinessException("该科目代码已删除!!");
      }
      // 判断该条记录的科目代码在FN110.PARENT_SUBJECT_CODE中是否存在
      List parentList = this.is_ParentCodeIn_WL(subjectCode, securityInfo);
      if (parentList.size() != 0) {
        throw new BusinessException("该科目有下一级科目不能被删除!!");
      }
      // 判断该条记录的科目代码在FN111.SUBJECT_CODE中是否存在
      List codeRelationlist = this.is_ParentCodeRelation_In(subjectCode,
          securityInfo);
      if (codeRelationlist.size() != 0) {
        throw new BusinessException("该科目已建立关系不能被删除!!");
      }
      // 判断该条记录的科目代码在FN120.SUBJECT_CODE中是否存在
      List credModList = this.is_CredenceModle_In(subjectCode, securityInfo);
      if (credModList.size() != 0) {
        throw new BusinessException("该科目已设置凭证模式不能被删除!!");
      }
      // 判断该条记录的科目代码在FN202.BY_SUBJECT_CODE or FN202.SUBJECT_CODE中是否存在
      String flag = this.is_SettleIncAndDec_In(subjectCode, securityInfo);
      if (flag.equals("have")) {
        throw new BusinessException("该科目已设置损益结转不能被删除!!");
      }
      // 判断该条记录的科目代码在FN201.SUBJECT_CODE or FN210.SUBJECT_CODE中是否存在
      List accCredList = this.is_AccountantCredence_In(subjectCode,
          securityInfo);
      if (accCredList.size() != 0) {
        throw new BusinessException("该科目已应用不能被删除!!");
      }
      List treaCredList = this.is_TreasurerCredence_In(subjectCode,
          securityInfo);
      if (treaCredList.size() != 0) {
        throw new BusinessException("该科目已应用不能被删除!!");
      }
      subjectDAO.delete_WL(subjectCode,securityInfo);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }

  }

  /**
   * 判断输入的科目代码是否存在
   */
  public List is_ParentCodeIn_WL(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List subjectIdByParentList = new ArrayList();
    ;
    try {
      subjectIdByParentList = subjectDAO.is_ParentCodeIn_WL(code, null,
          securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return subjectIdByParentList;
  }

  /**
   * 判断该条记录的科目代码在FN120.SUBJECT_CODE中是否存在
   */
  public List is_CredenceModle_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = credenceModleDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return list;
  }

  /**
   * 判断该条记录的科目代码在FN202.BY_SUBJECT_CODE or FN202.SUBJECT_CODE中是否存在
   */
  public String is_SettleIncAndDec_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String flag = "";
    try {
      flag = settleIncAndDecDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return flag;
  }

  /**
   * 判断该条记录的科目代码在FN201.SUBJECT_CODE 中是否存在
   */
  public List is_AccountantCredence_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = accountantCredenceDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return list;
  }

  /**
   * 判断该条记录的科目代码在FN210.SUBJECT_CODE中是否存在
   */
  public List is_TreasurerCredence_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = treasurerCredenceDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return list;
  }

  /**
   * 作废科目
   */
  public void canceledSubject(String codeid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // 取得对应的科目代码
      String subjectCode = subjectDAO.is_CodeInBySubjectId_WL(codeid, null,
          securityInfo);
      // 判断该条记录是否FN110.SUBJECT_ST=0
      String codestate = this.is_ParentCode_Normal(subjectCode, "0",
          securityInfo);
      if (codestate == null || codestate.equals("")) {
        throw new BusinessException("该科目代码已作废!!");
      }
      // 判断该条记录的科目代码在FN110.PARENT_SUBJECT_CODE中是否存在
      List parentList = this.is_ParentCodeIn_WL(subjectCode, securityInfo);
      if (parentList.size() != 0) {
        // 判断该条记录的科目代码的下一级科目代码的FN110.SUBJECT_ST=0
        List subjectIdByParentList = subjectDAO.is_ParentCodeIn_WL(subjectCode,
            "0", securityInfo);
        if (subjectIdByParentList.size() != 0) {
          throw new BusinessException("该科目有下一级科目不能被作废!!");
        }
      }
      // 判断该条记录的科目代码在FN111.SUBJECT_CODE中是否存在
      List codeRelationlist = this.is_ParentCodeRelation_In(subjectCode,
          securityInfo);
      if (codeRelationlist.size() != 0) {
        throw new BusinessException("该科目已建立关系不能被作废!!");
      }
      // 判断该条记录的科目代码在FN120.SUBJECT_CODE中是否存在
      List credModList = this.is_CredenceModle_In(subjectCode, securityInfo);
      if (credModList.size() != 0) {
        throw new BusinessException("该科目已设置凭证模式不能被作废!!");
      }
      // 判断该条记录的科目代码在FN202.BY_SUBJECT_CODE or FN202.SUBJECT_CODE中是否存在
      String flag = this.is_SettleIncAndDec_In(subjectCode, securityInfo);
      if (flag.equals("have")) {
        throw new BusinessException("该科目已设置损益结转不能被作废!!");
      }
      String balance = subjectDAO
          .checkIsExitBalance(securityInfo.getBookId(), subjectCode,
              securityInfo.getUserInfo().getFbizDate().substring(0, 6));
      if (!balance.equals("0")) {
        throw new BusinessException("该科目仍存在余额,不能作废!!");
      }
      // 更新FN110.SUBJECT_ST=1
      Subject subject = subjectDAO.queryById(new Integer(codeid));
      subject.setSubjectSt("" + BusiConst.PLCOMMONSTATUS_1_CANCELED);
      subject.setExpireDate(securityInfo.getUserInfo().getFbizDate().substring(0,4));
      //subjectDAO.update(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }

  }

  /**
   * 撤销作废科目
   */
  public void canceledquashSubject(String codeid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // 取得对应的科目代码
      String subjectCode = subjectDAO.is_CodeInBySubjectId_WL(codeid, null,
          securityInfo);
      // 判断该条记录是否FN110.SUBJECT_ST=1
      String codestate = this.is_ParentCode_Normal(subjectCode, "1",
          securityInfo);
      if (codestate == null || codestate.equals("")) {
        throw new BusinessException("该科目代码已正常!!");
      }
      // 判断该条记录的FN110.PARENT_SUBJECT_CODE是否为空
      String subjectParentCode = this.is_ParentCodeInByCode_WL(subjectCode,
          null, securityInfo);
      if (subjectParentCode != null && !subjectParentCode.equals("")) {
        // 判断该条记录的父科目代码 的状态是否正常
        String parentCode = this.is_ParentCode_Normal(subjectParentCode, "0",
            securityInfo);
        if (parentCode == null || parentCode.equals("")) {
          throw new BusinessException("请先撤销作废上一级科目代码!!");
        }
      }

      // 更新FN110.SUBJECT_ST=0
      Subject subject = subjectDAO.queryById(new Integer(codeid));
      subject.setSubjectSt("" + BusiConst.PLCOMMONSTATUS_1_NORMAL);
      subjectDAO.update(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }

  }

  /**
   * 判断该条记录的FN110.PARENT_SUBJECT_CODE是否为空
   */
  public String is_ParentCodeInByCode_WL(String code, String states,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String parentCode = "";
    try {
      parentCode = subjectDAO
          .is_ParentCodeInByCode_WL(code, null, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return parentCode;
  }

  /**
   * 根据科目流水号查询科目信息
   */
  public SubjectShowAF findSubject(String subjectId, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    SubjectShowAF af = new SubjectShowAF();
    try {
      Subject subject = subjectDAO.queryById(new Integer(subjectId));
      af.setId(subject.getSubjectId().toString());
      af.setCode(subject.getSubjectCode());
      af.setName(subject.getSubjectName());
      af.setDirection(subject.getBalanceDirection());
      af.setSortcode(subject.getSubjectSortCode());
      af.setProperty(subject.getSubjectProperty());
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }
    return af;
  }

  /**
   * 修改科目
   */
  public void updateSubject(SubjectShowAF af, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // 更新FN110
      Subject subject = subjectDAO.queryById(new Integer(af.getId()));
      subject.setSubjectName(af.getName());
      subjectDAO.update(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("查询错误");
    }

  }

  /**
   * 添加时，根据录入的科目代码做验证，获得其一级科目信息
   */
  public SubjectShowAF getSubjectMessage(SubjectShowAF af,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      String code = af.getCode();
      // 判断科目代码是否符合该账套代码结构
      this.is_Book_CodeStructure(code, securityInfo);
      // 判断输入的科目代码是否存在
      String subjectId = this.is_Code_In(code, securityInfo);
      if (subjectId != null && !subjectId.equals("")) {
        throw new BusinessException("该科目代码已经存在!!");
      }
      // 判断输入的科目代码在是否为一级科目代码
      boolean flag = this.is_CodeLevel_One(code, securityInfo);
      String codeLevelUp = "";
      if (flag == false) {// 非一级科目
        // 取得对应的上一级科目代码的位数
        int temp_length = this.getCodeLevel_up(code, securityInfo);
        codeLevelUp = code.substring(0, temp_length);
        // 判断输入的科目代码的上一级科目代码在FN110是否已经存在，and SUBJECT_ST=0
        String parentCode = this.is_ParentCode_Normal(codeLevelUp, "0",
            securityInfo);
        if (parentCode == null || parentCode.equals("")) {
          throw new BusinessException("上级科目不存在或已作废，不能再建立子科目!!");
        }
        // 判断输入的科目代码的上一级科目在FN111.SUBJECT_CODE中是否存在
        List list = this.is_ParentCodeRelation_In(parentCode, securityInfo);
        if (list.size() != 0) {
          throw new BusinessException("上级科目已建立核算关系，不能再建立子科目!!");
        }
        // 得到父科目的科目名称
        String parentsortname = subjectDAO.querySubjectNameBySubjectCode(
            parentCode, securityInfo.getBookId());
        // 得到父科目的科目类别
        String parentsortcode = this
            .getSortcodeByCode(parentCode, securityInfo);
        // 得到父科目的科目方向
        String parentdirection = this.getDirectionByCode(parentCode,
            securityInfo);
        // 得到父科目的科目属性
        String parentpropery = this.getProperyByCode_WL(parentCode,
            securityInfo);
        af.setCode(code);
        af.setName(parentsortname);
        af.setSortcode(parentsortcode);
        af.setDirection(parentdirection);
        af.setProperty(parentpropery);
        af.setActionflag("0");
      }

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
    return af;
  }

}
