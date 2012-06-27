package org.xpup.hafmis.common.util;

/**
 * 业务常量类
 * 
 * @author 刘洋 2007-6-2
 */
public class BusiConst {

  private BusiConst() {

  }

  /**
   * 公共部分
   */
  /*
   * 上传图片所在服务器位置
   */
  public static final String UPLOAD_SERVER_PATH = "d:/serverimage";

  public static final String PUB_DATE_Y_PATTERN = "yyyy";

  public static final String PUB_DATE_D_PATTERN = "dd";

  public static final String PUB_DATE_YM_PATTERN = "yyyy-MM";

  /**
   * 长日期格式
   */
  public static final String PUB_LONG_DATE_PATTERN = "yyyy-MM-dd";

  public static final String PUB_LONG_YMD_PATTERN = "yyyyMMdd";

  public static final String PUB_LONG_DATE_PATTERNS = "yyyyMM";

  /**
   * 长时间格式
   */
  public static final String PUB_LONG_TIME_PATTERN = "HH:mm:ss";

  /**
   * 长日期时间格式
   */
  public static final String PUB_LONG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

  /**
   * 长日期时间格式，包含秒
   */

  public static final String PUB_LONG_DATE_TIME_SECOND_PATTERN = "yyyy-MM-dd HH:mm:ss";

  /**
   * 长日期时间格式，包含秒
   */

  public static final String PUB_LONG_DATE_TIME_SECOND_PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";

  /* 婚姻状况 */
  public static final String MARRY_STATUS = "org.xpup.hafmis.common.util.enumerate.MarryStatus";

  /**
   * 婚姻状况——未婚
   */
  public static final int MARRY_STATUS_LIVE = 10;

  /**
   * 婚姻状况——已婚
   */
  public static final int MARRY_STATUS_DEAD = 20;

  /* 性别 */
  public static final String SEX = "org.xpup.hafmis.common.util.enumerate.Sex";

  /**
   * 性别——未知的性别
   */
  public static final int SEX_UNKNOW = 0;

  /**
   * 性别——男
   */
  public static final int SEX_MALE = 1;

  /**
   * 性别——女
   */
  public static final int SEX_FEMALE = 2;

  /**
   * 性别——未知说明性别
   */
  public static final int SEX_ILLUSTRATED = 9;

  /* 选择状态-是否 */
  public static final String YesNo = "org.xpup.hafmis.common.util.enumerate.YesNo";

  /**
   * 是否——是
   */
  public static final int YES = 0;

  /**
   * 是否——否
   */
  public static final int NO = 1;

  /* 选择状态-是否结息 */
  public static final String YesNoInterest = "org.xpup.hafmis.common.util.enumerate.YesNoInterest";

  /**
   * 是否结息——是
   */
  public static final int YESINTEREST = 1;

  /**
   * 是否结息——否
   */
  public static final int NOINTEREST = 2;

  /* 单位缴存方式 */
  public static final String ORGPAYWAY = "org.xpup.hafmis.common.util.enumerate.OrgPayWay";

  /**
   * 单位缴存方式-单一缴率
   */
  public static final int ORGPAYWAY_RATE = 1;

  /**
   * 单位缴存方式-职工缴额
   */
  public static final int ORGPAYWAY_PAYMENT = 2;

  /* 单位状态 */
  public static final String ORGSTATE = "org.xpup.hafmis.common.util.enumerate.OrgState";

  /**
   * 单位状态-开户中
   */
  public static final int ORGSTATE_OPENING = 1;

  /**
   * 单位状态-正常
   */
  public static final int ORGSTATE_NORMAL = 2;

  /**
   * 单位状态-封存
   */
  public static final int ORGSTATE_SEAL = 3;

  /**
   * 单位状态-注销
   */
  public static final int ORGSTATE_CANCEL = 4;

  /* 缴存精度 */
  public static final String PAYMENTACCURACY = "org.xpup.hafmis.common.util.enumerate.PaymentAccuracy";

  /**
   * 缴存精度-四舍五入到元
   */
  public static final int PAYMENTACCURACY_ROUNDTOYUAN = 1;

  /**
   * 缴存精度-舍元以下
   */
  public static final int PAYMENTACCURACY_DISCARDTOYUAN = 2;

  /**
   * 缴存精度-见角进元
   */
  public static final int PAYMENTACCURACY_SEEKOKONYUAN = 3;

  /**
   * 缴存精度-见分进角
   */
  public static final int PAYMENTACCURACY_SEECENTSONKOK = 4;

  /**
   * 缴存精度-四舍五入到角
   */
  public static final int PAYMENTACCURACY_ROUNDTOKOK = 5;

  /**
   * 缴存精度-舍角以下
   */
  public static final int PAYMENTACCURACY_DISCARDTOKOK = 6;

  /**
   * 缴存精度-见角分进元
   */
  public static final int PAYMENTACCURACY_SEEKOKCENTSONYUAN = 7;

  /**
   * 缴存精度-四舍五入到分
   */
  public static final int PAYMENTACCURACY_ROUNDTOCENT = 8;

  /* 证件类型 */
  public static final String DOCUMENTSSTATE = "org.xpup.hafmis.common.util.enumerate.DocumentsState";

  /**
   * 证件类型-身份证
   */
  public static final int DOCUMENTSSTATE_IDCARD = 0;

  /**
   * 证件类型-户口簿
   */
  public static final int DOCUMENTSSTATE_HOUSEHOLDREGISTER = 1;

  /**
   * 证件类型-护照
   */
  public static final int DOCUMENTSSTATE_PASSPORTCARD = 2;

  /**
   * 证件类型-军官证
   */
  public static final int DOCUMENTSSTATE_OFFICERCARD = 3;

  /**
   * 证件类型-士兵证
   */
  public static final int DOCUMENTSSTATE_SOLDIERCARD = 4;

  /**
   * 证件类型-港澳居民来往内地通行证
   */
  public static final int DOCUMENTSSTATE_HKANDMACAO = 5;

  /**
   * 证件类型-台湾同胞来往内地通行证
   */
  public static final int DOCUMENTSSTATE_TAIWAN = 6;

  /**
   * 证件类型-临时身份证
   */
  public static final int DOCUMENTSSTATE_TEMPCARD = 7;

  /**
   * 证件类型-外国人居留证
   */
  public static final int DOCUMENTSSTATE_FOREIGNERRESIDENTIALCARD = 8;

  /**
   * 证件类型-警官证
   */
  public static final int DOCUMENTSSTATE_POLICECARD = 9;

  /**
   * 证件类型-其他证件
   */
  // public static final int DOCUMENTSSTATE_OTHERSCARD = X;
  /* 担保方式 */
  public static final String GUARANTEETYPE = "org.xpup.hafmis.common.util.enumerate.GuaranteeType";

  /**
   * 担保方式-质押（含保证金）
   */
  public static final int GUARANTEETYPE_IMPAWN = 1;

  /**
   * 担保方式-抵押
   */
  public static final int GUARANTEETYPE_MORTAGAGE = 2;

  /**
   * 担保方式-自然人担保
   */
  public static final int GUARANTEETYPE_NATURALPERSONHYPOTHECATE = 3;

  /**
   * 担保方式-信用/免担保
   */
  public static final int GUARANTEETYPE_CREDIT = 4;

  /**
   * 担保方式-组合（含自然人保证）
   */
  public static final int GUARANTEETYPE_COMBINATIONINNATURAL = 5;

  /**
   * 担保方式-组合（不含自然人保证）
   */
  public static final int GUARANTEETYPE_COMBINATIONOUTNATURAL = 6;

  /**
   * 担保方式-农户联保
   */
  public static final int GUARANTEETYPE_JOINTGUARANTY = 7;

  /**
   * 担保方式-其他
   */
  public static final int GUARANTEETYPE_OTHERS = 9;

  /* 五级分类状态 */
  public static final String FIVECATEGORYASSETSCLASSIFICATION = "org.xpup.hafmis.common.util.enumerate.FiveCategoryAssetsClassification";

  /**
   * 五级分类状态-正常
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_NORMAL = 1;

  /**
   * 五级分类状态-关注
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_ATTENTION = 2;

  /**
   * 五级分类状态-次级
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_SECONDARY = 3;

  /**
   * 五级分类状态-可疑
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_SHADINESS = 4;

  /**
   * 五级分类状态-损失
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_DAMNIFY = 5;

  /**
   * 五级分类状态-未知
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_UNKOWN = 9;

  /* 账户状态-业务种类为贷款 */
  public static final String ACCOUNTSTATEBYLOAN = "org.xpup.hafmis.common.util.enumerate.AccountStateByLoan";

  /**
   * 账户状态-业务种类为贷款-正常
   */
  public static final int ACCOUNTSTATEBYLOAN_NORMAL = 1;

  /**
   * 账户状态-业务种类为贷款-逾期
   */
  public static final int ACCOUNTSTATEBYLOAN_OVERDUE = 2;

  /**
   * 账户状态-业务种类为贷款-结清
   */
  public static final int ACCOUNTSTATEBYLOAN_SETTLE = 3;

  /**
   * 账户状态-业务种类为贷款-呆账
   */
  public static final int ACCOUNTSTATEBYLOAN_BADDEBT = 4;

  /* 账户状态-业务种类为信用卡 */
  public static final String ACCOUNTSTATEBYCREDITCARD = "org.xpup.hafmis.common.util.enumerate.AccountStateByCreditCard";

  /**
   * 账户状态-业务种类为信用卡-正常
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_NORMAL = 1;

  /**
   * 账户状态-业务种类为信用卡-冻结
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_CONGEAL = 2;

  /**
   * 账户状态-业务种类为信用卡-止付
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_STOPPAYMENT = 3;

  /**
   * 账户状态-业务种类为信用卡-销户
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_DELACCOUNT = 4;

  /**
   * 账户状态-业务种类为信用卡-呆账
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_BADDEBT = 5;

  /* 部分提取 */
  public static final String SOMEPICK = "org.xpup.hafmis.common.util.enumerate.SomePick";

  /**
   * 部分提取-购房
   */
  public static final int BUYHOUSE = 1;

  /**
   * 部分提取-公积金按月还贷
   */
  public static final int GIVEMONEYBYMON = 2;

  /**
   * 部分提取-公积金一次性还贷款
   */
  public static final int GIVEMONEYClEAR = 3;

  /**
   * 部分提取-重大疾病
   */
  public static final int DISEASE = 4;

  /**
   * 部分提取-特困
   */
  public static final int DISTRESS = 5;

  /**
   * 部分提取-部分提取其他
   */
  public static final int PARTREST = 6;

  /* 消户提取 */
  public static final String DISTROYPICK = "org.xpup.hafmis.common.util.enumerate.DistoryPick";

  /**
   * 退休
   */
  public static final int BOWOUT = 7;

  /**
   * 死亡
   */
  public static final int DEATH = 8;

  /**
   * 调出市内
   */
  public static final int DECRUITMENT = 9;

  /**
   * 失业下岗两年
   */
  public static final int JOBLESS = 10;

  /**
   * 非本市户口解除合同
   */
  public static final int DISTORY = 11;

  /**
   * 出国定居
   */
  public static final int SETTLE = 12;

  /**
   * 提取原因
   */
  public static final String PICKUPREASON = "org.xpup.hafmis.common.util.enumerate.PickupReason";

  /**
   * 部分提取-购房
   */
  public static final int PICKUPREASON_BUYHOUSE = 1;

  /**
   * 部分提取-公积金按月还贷
   */
  public static final int PICKUPREASON_GIVEMONEYBYMON = 2;

  /**
   * 部分提取-公积金一次性还贷款
   */
  public static final int PICKUPREASON_GIVEMONEYClEAR = 3;

  /**
   * 部分提取-重大疾病
   */
  public static final int PICKUPREASON_DISEASE = 4;

  /**
   * 部分提取-特困
   */
  public static final int PICKUPREASON_DISTRESS = 5;

  /**
   * 部分提取-部分提取其他
   */
  public static final int PICKUPREASON_PARTREST = 6;

  /**
   * 退休
   */
  public static final int PICKUPREASON_BOWOUT = 7;

  /**
   * 死亡
   */
  public static final int PICKUPREASON_DEATH = 8;

  /**
   * 调出市内
   */
  public static final int PICKUPREASON_DECRUITMENT = 9;

  /**
   * 失业下岗两年
   */
  public static final int PICKUPREASON_JOBLESS = 10;

  /**
   * 非本市户口解除合同
   */
  public static final int PICKUPREASON_DISTORY = 11;

  /**
   * 出国定居
   */
  public static final int PICKUPREASON_SETTLE = 12;

  /* 最高学历 */
  public static final String HIGHESTEDUCATIONLEVEL = "org.xpup.hafmis.common.util.enumerate.HighestEducationLevel";

  /**
   * 最高学历-研究生
   */
  public static final int HIGHESTEDUCATIONLEVEL_GRADUATE = 10;

  /**
   * 最高学历-大学本科（简称"大学"）
   */
  public static final int HIGHESTEDUCATIONLEVEL_UNDERGRADUATE = 20;

  /**
   * 最高学历-大学专科和专科学校（简称"大专"）
   */
  public static final int HIGHESTEDUCATIONLEVEL_SPECIALIST = 30;

  /**
   * 最高学历-中等专业学校或中等技术学校
   */
  public static final int HIGHESTEDUCATIONLEVEL_SECONDARY = 40;

  /**
   * 最高学历-技术学校
   */
  public static final int HIGHESTEDUCATIONLEVEL_TECHNICAL = 50;

  /**
   * 最高学历-高中
   */
  public static final int HIGHESTEDUCATIONLEVEL_HIGHSCHOOL = 60;

  /**
   * 最高学历-初中
   */
  public static final int HIGHESTEDUCATIONLEVEL_JUNIOR = 70;

  /**
   * 最高学历-小学
   */
  public static final int HIGHESTEDUCATIONLEVEL_PRIMARY = 80;

  /**
   * 最高学历-文盲或半文盲
   */
  public static final int HIGHESTEDUCATIONLEVEL_ILLITERACY = 90;

  /**
   * 最高学历-未知
   */
  public static final int HIGHESTEDUCATIONLEVEL_UNKNOW = 99;

  /* 最高学位 */
  public static final String HIGHESTEDUCATIONALDEGREEOBTAINED = "org.xpup.hafmis.common.util.enumerate.HighestEducationDegreeObtained";

  /**
   * 最高学位-其他
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_OTHERS = 0;

  /**
   * 最高学位-名誉博士
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_HONORARYDOCTOR = 1;

  /**
   * 最高学位-博士
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_DOCTOR = 2;

  /**
   * 最高学位-硕士
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_MASTER = 3;

  /**
   * 最高学位-学士
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_BACHELOR = 4;

  /**
   * 最高学位-未知
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_UNKNOW = 9;

  /* 职务 */
  public static final String DUTY = "org.xpup.hafmis.common.util.enumerate.Duty";

  /**
   * 职务-高级领导（行政级别局级及局级以上领导或大公司高级管理人员）
   */
  public static final int DUTY_SENIORLEADER = 1;

  /**
   * 职务-中级领导（行政级别局级以下领导或大公司中级管理人员）
   */
  public static final int DUTY_INTERMEDIATELEADER = 2;

  /**
   * 职务-一般员工
   */
  public static final int DUTY_GENERALSTAFF = 3;

  /**
   * 职务-其他
   */
  public static final int DUTY_OTHERS = 4;

  /**
   * 职务-未知
   */
  public static final int DUTY_UNKNOW = 9;

  /* 职称 */
  public static final String DUTYLEVEL = "org.xpup.hafmis.common.util.enumerate.DutyLevel";

  /**
   * 职称-无
   */
  public static final int DUTYLEVEL_NOT = 0;

  /**
   * 职称-高级
   */
  public static final int DUTYLEVEL_SENIOR = 1;

  /**
   * 职称-中级
   */
  public static final int DUTYLEVEL_INTERMEDIATE = 2;

  /**
   * 职称-初级
   */
  public static final int DUTYLEVEL_GENERAL = 3;

  /**
   * 职称-未知
   */
  public static final int DUTYLEVEL_UNKNOW = 9;

  /* 居住情况 */
  public static final String LIVINGCONTEXT = "org.xpup.hafmis.common.util.enumerate.LivingContext ";

  /**
   * 居住情况-自置
   */
  public static final int LIVINGCONTEXT_REHOUSINGYOURSELF = 1;

  /**
   * 居住情况-按揭
   */
  public static final int LIVINGCONTEXT_MORTGAGE = 2;

  /**
   * 居住情况-亲属楼宇
   */
  public static final int LIVINGCONTEXT_RELATIVEHOME = 3;

  /**
   * 居住情况-集体宿舍
   */
  public static final int LIVINGCONTEXT_COLLECTIVEQUARTERS = 4;

  /**
   * 居住情况-租房
   */
  public static final int LIVINGCONTEXT_RENTHOME = 5;

  /**
   * 居住情况-共有住宅
   */
  public static final int LIVINGCONTEXT_PUBLICHOME = 6;

  /**
   * 居住情况-其他
   */
  public static final int LIVINGCONTEXT_OTHERS = 7;

  /**
   * 居住情况-未知
   */
  public static final int LIVINGCONTEXT_UNKNOW = 9;

  /* 单位所属行业 */
  public static final String INDUSTRY = "org.xpup.hafmis.common.util.enumerate.Industry";

  /**
   * 单位所属行业-农、林、牧、渔业
   */
  public static final String INDUSTRY_A = "A";

  /**
   * 单位所属行业-采掘业
   */
  public static final String INDUSTRY_B = "B";

  /**
   * 单位所属行业-制造业
   */
  public static final String INDUSTRY_C = "C";

  /**
   * 单位所属行业-电力、燃气及水的生产和供应业
   */
  public static final String INDUSTRY_D = "D";

  /**
   * 单位所属行业-建筑业
   */
  public static final String INDUSTRY_E = "E";

  /**
   * 单位所属行业-交通运输、仓储和邮政业
   */
  public static final String INDUSTRY_F = "F";

  /**
   * 单位所属行业-信息传输、计算机服务和软件业
   */
  public static final String INDUSTRY_G = "G";

  /**
   * 单位所属行业-批发和零售业
   */
  public static final String INDUSTRY_H = "H";

  /**
   * 单位所属行业-住宿和餐饮业
   */
  public static final String INDUSTRY_I = "I";

  /**
   * 单位所属行业-金融业
   */
  public static final String INDUSTRY_J = "J";

  /**
   * 单位所属行业-房地产业
   */
  public static final String INDUSTRY_K = "K";

  /**
   * 单位所属行业-租赁和商务服务业
   */
  public static final String INDUSTRY_L = "L";

  /**
   * 单位所属行业-科学研究、技术服务业和地质勘查业
   */
  public static final String INDUSTRY_M = "M";

  /**
   * 单位所属行业-水利、环境和公共设施管理业
   */
  public static final String INDUSTRY_N = "N";

  /**
   * 单位所属行业-居民服务和其他服务业
   */
  public static final String INDUSTRY_O = "O";

  /**
   * 单位所属行业-教育
   */
  public static final String INDUSTRY_P = "P";

  /**
   * 单位所属行业-卫生、社会保障和社会福利业
   */
  public static final String INDUSTRY_Q = "Q";

  /**
   * 单位所属行业-文化、体育和娱乐业
   */
  public static final String INDUSTRY_R = "R";

  /**
   * 单位所属行业-公共管理和社会组织
   */
  public static final String INDUSTRY_S = "S";

  /**
   * 单位所属行业-国际组织
   */
  public static final String INDUSTRY_T = "T";

  /**
   * 单位所属行业-未知
   */
  public static final String INDUSTRY_Z = "Z";

  /* 人员变更中的变更状态 */
  public static final String CHGSTATUS = "org.xpup.hafmis.common.util.enumerate.ChgStatus";

  /**
   * 人员变更中的变更状态-开户
   */
  public static final int CHGSTATUS_OPEN = 1;

  /**
   * 人员变更中的变更状态-启封
   */
  public static final int CHGSTATUS_QF = 2;

  /**
   * 人员变更中的变更状态-封存
   */
  public static final int CHGSTATUS_FC = 3;

  /* 所在地区 */
  public static final String INAREA = "org.xpup.hafmis.common.util.enumerate.InArea";

  /**
   * 所在地区-市直
   */
  public static final int INAREA_SZ = 1;

  /**
   * 所在地区-大石桥
   */
  public static final int INAREA_DSQ = 2;

  /**
   * 所在地区-盖县
   */
  public static final int INAREA_GX = 3;

  /**
   * 所在地区-鲅鱼圈
   */
  public static final int INAREA_BYQ = 4;

  /* 单位性质 */
  public static final String NATUREOFUNITS = "org.xpup.hafmis.common.util.enumerate.NatureOfUnits";

  /**
   * 单位性质-中央机关事业
   */
  public static final int NATUREOFUNITS_1 = 1;

  /**
   * 单位性质-省机关事业
   */
  public static final int NATUREOFUNITS_2 = 2;

  /**
   * 单位性质-市县机关事业
   */
  public static final int NATUREOFUNITS_3 = 3;

  /**
   * 单位性质－中央企业
   */
  public static final int NATUREOFUNITS_4 = 4;

  /**
   * 单位性质－省级全民企业
   */
  public static final int NATUREOFUNITS_5 = 5;

  /**
   * 单位性质－省级集体企业
   */
  public static final int NATUREOFUNITS_6 = 6;

  /**
   * 单位性质－市级全民企业
   */
  public static final int NATUREOFUNITS_7 = 7;

  /**
   * 单位性质－市县级集体企业
   */
  public static final int NATUREOFUNITS_8 = 8;

  /**
   * 单位性质－外省市单位
   */
  public static final int NATUREOFUNITS_9 = 9;

  /**
   * 单位性质-部队
   */
  public static final int NATUREOFUNITS_10 = 10;

  /**
   * 单位性质-行政事业(全额)
   */
  public static final int NATUREOFUNITS_11 = 11;

  /**
   * 单位性质-行政事业(差额)
   */
  public static final int NATUREOFUNITS_12 = 12;

  /**
   * 单位性质－行政事业(自收自支)
   */
  public static final int NATUREOFUNITS_13 = 13;

  /**
   * 单位性质－国有企业
   */
  public static final int NATUREOFUNITS_14 = 14;

  /**
   * 单位性质－集体企业
   */
  public static final int NATUREOFUNITS_15 = 15;

  /**
   * 单位性质－股份有限公司
   */
  public static final int NATUREOFUNITS_16 = 16;

  /**
   * 单位性质－私有民营企业
   */
  public static final int NATUREOFUNITS_17 = 17;

  /**
   * 单位性质－三资企业
   */
  public static final int NATUREOFUNITS_18 = 18;

  /**
   * 单位性质－其他
   */
  public static final int NATUREOFUNITS_19 = 19;

  /* 主管部门 */
  public static final String AUTHORITIES = "org.xpup.hafmis.common.util.enumerate.Authorities";

  public static final int AUTHORITIES_1 = 1;

  public static final int AUTHORITIES_2 = 2;

  public static final int AUTHORITIES_3 = 3;

  public static final int AUTHORITIES_4 = 4;

  public static final int AUTHORITIES_5 = 5;

  public static final int AUTHORITIES_6 = 6;

  public static final int AUTHORITIES_7 = 7;

  public static final int AUTHORITIES_8 = 8;

  public static final int AUTHORITIES_9 = 9;

  public static final int AUTHORITIES_10 = 10;

  public static final int AUTHORITIES_11 = 11;

  public static final int AUTHORITIES_12 = 12;

  public static final int AUTHORITIES_13 = 13;

  public static final int AUTHORITIES_14 = 14;

  public static final int AUTHORITIES_15 = 15;

  public static final int AUTHORITIES_16 = 16;

  public static final int AUTHORITIES_17 = 17;

  public static final int AUTHORITIES_18 = 18;

  public static final int AUTHORITIES_19 = 19;

  public static final int AUTHORITIES_20 = 20;

  public static final int AUTHORITIES_22 = 22;

  public static final int AUTHORITIES_23 = 23;

  public static final int AUTHORITIES_24 = 24;

  public static final int AUTHORITIES_25 = 25;

  public static final int AUTHORITIES_26 = 26;

  public static final int AUTHORITIES_27 = 27;

  public static final int AUTHORITIES_28 = 28;

  public static final int AUTHORITIES_29 = 29;

  public static final int AUTHORITIES_30 = 30;

  public static final int AUTHORITIES_31 = 31;

  public static final int AUTHORITIES_32 = 32;

  public static final int AUTHORITIES_33 = 33;

  public static final int AUTHORITIES_34 = 34;

  public static final int AUTHORITIES_35 = 35;

  public static final int AUTHORITIES_36 = 36;

  public static final int AUTHORITIES_37 = 37;

  public static final int AUTHORITIES_38 = 38;

  public static final int AUTHORITIES_39 = 39;

  public static final int AUTHORITIES_40 = 40;

  public static final int AUTHORITIES_42 = 42;

  public static final int AUTHORITIES_43 = 43;

  public static final int AUTHORITIES_44 = 44;

  public static final int AUTHORITIES_45 = 45;

  public static final int AUTHORITIES_46 = 46;

  public static final int AUTHORITIES_47 = 47;

  public static final int AUTHORITIES_48 = 48;

  public static final int AUTHORITIES_49 = 49;

  public static final int AUTHORITIES_50 = 50;

  public static final int AUTHORITIES_51 = 51;

  public static final int AUTHORITIES_52 = 52;

  public static final int AUTHORITIES_53 = 53;

  public static final int AUTHORITIES_54 = 54;

  public static final int AUTHORITIES_55 = 55;

  public static final int AUTHORITIES_56 = 56;

  public static final int AUTHORITIES_57 = 57;

  public static final int AUTHORITIES_58 = 58;

  public static final int AUTHORITIES_59 = 59;

  public static final int AUTHORITIES_60 = 60;

  public static final int AUTHORITIES_61 = 61;

  public static final int AUTHORITIES_62 = 62;

  /**
   * 主管部门-中直
   */
  public static final int AUTHORITIES_ZHONGZHI = 63;

  /**
   * 主管部门-省直
   */
  public static final int AUTHORITIES_SHENGZHI = 64;

  /**
   * 主管部门-市直
   */
  public static final int AUTHORITIES_SHIZHI = 65;

  /**
   * 主管部门-市级以下
   */
  public static final int AUTHORITIES_SHIJIYIXIA = 66;

  /**
   * 主管部门-其他
   */
  public static final int AUTHORITIES_OTHER = 67;

  /* 调整业务类型 */
  public static final String CHGBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.ChgBusinessType";

  /**
   * 调整业务类型-调缴存
   */
  public static final String CHGBUSINESSTYPE_K = "K";

  /**
   * 调整业务类型-调提取
   */
  public static final String CHGBUSINESSTYPE_L = "L";

  /**
   * 调整业务类型-调其他
   */
  public static final String CHGBUSINESSTYPE_G = "G";

  /* 业务类型 */
  public static final String BUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.BusinessType";

  /**
   * 业务类型-正常汇缴
   */
  public static final int BUSINESSTYPE_1 = 1;

  /**
   * 业务类型-单位补缴
   */
  public static final int BUSINESSTYPE_2 = 2;

  /**
   * 业务类型-个人补缴
   */
  public static final int BUSINESSTYPE_3 = 3;

  /**
   * 业务类型-挂账
   */
  public static final int BUSINESSTYPE_4 = 4;

  /* 业务状态 */
  public static final String BUSINESSSTATE = "org.xpup.hafmis.common.util.enumerate.BusinessState";

  /**
   * 业务状态-录入清册
   */
  public static final int BUSINESSSTATE_1 = 1;

  /**
   * 业务状态-登记
   */
  public static final int BUSINESSSTATE_2 = 2;

  /**
   * 业务状态-确认
   */
  public static final int BUSINESSSTATE_3 = 3;

  /**
   * 业务状态-复核
   */
  public static final int BUSINESSSTATE_4 = 4;

  /**
   * 业务状态-入账
   */
  public static final int BUSINESSSTATE_5 = 5;

  /* 业务状态 */
  public static final String ORGOVERPAYTYPE = "org.xpup.hafmis.common.util.enumerate.OrgOverPay";

  /**
   * 挂帐类型－－冲挂帐
   */
  public static final int ORGOVERPAYTYPE_1 = 1;

  /**
   * 挂帐类型－－其他
   */
  public static final int ORGOVERPAYTYPE_2 = 2;

  /* 业务状态 */
  public static final String BUSINESSLOGSTATE = "org.xpup.hafmis.common.util.enumerate.BusinessLogState";

  /**
   * 业务状态-录入清册
   */
  public static final String BUSINESSLOGSTATE_1 = "1";

  /**
   * 业务状态-登记
   */
  public static final String BUSINESSLOGSTATE_2 = "2";

  /**
   * 业务状态-确认
   */
  public static final String BUSINESSLOGSTATE_3 = "3";

  /**
   * 业务状态-复核
   */
  public static final String BUSINESSLOGSTATE_4 = "4";

  /**
   * 业务状态-入账
   */
  public static final String BUSINESSLOGSTATE_5 = "5";

  /* 扎账- 业务状态 */
  public static final String CLEARACCOUNTSTATUS = "org.xpup.hafmis.common.util.enumerate.ClearAccountStatus";

  /**
   * 业务状态-确认
   */
  public static final int CLEARACCOUNTSTATUS_3 = 3;

  /**
   * 业务状态-复核
   */
  public static final int CLEARACCOUNTSTATUS_4 = 4;

  /**
   * 业务状态-入账
   */
  public static final int CLEARACCOUNTSTATUS_5 = 5;

  /* 业务复核 业务状态 */
  public static final String BIZCHECKBIZSTATUS = "org.xpup.hafmis.common.util.enumerate.BizcheckBizStatus";

  /**
   * 业务复核 业务状态-未复核
   */
  public static final int BIZCHECKBIZSTATUS_1 = 3;

  /**
   * 业务复核 业务状态-已复核
   */
  public static final int BIZCHECKBIZSTATUS_2 = 4;

  /* 变更前缴存状态 */
  public static final String OLDPAYMENTSTATE = "org.xpup.hafmis.common.util.enumerate.OldPaymentState";

  /**
   * 变更前缴存状态-正常
   */
  public static final int OLDPAYMENTSTATE_1 = 1;

  /**
   * 变更前缴存状态-封存
   */
  public static final int OLDPAYMENTSTATE_2 = 2;

  /**
   * 变更前缴存状态-销户
   */
  public static final int OLDPAYMENTSTATE_3 = 3;

  /**
   * 变更前缴存状态-调出
   */
  public static final int OLDPAYMENTSTATE_4 = 4;

  /**
   * 变更前缴存状态-删除
   */
  public static final int OLDPAYMENTSTATE_5 = 5;

  /**
   * 变更前缴存状态-无账户
   */
  public static final int OLDPAYMENTSTATE_6 = 6;

  /* 变更状态 */
  public static final String CHGTYPESTATUS = "org.xpup.hafmis.common.util.enumerate.ChgTypeStatus";

  /**
   * 变更状态-未启用
   */
  public static final int CHGTYPESTATUS_1 = 1;

  /**
   * 变更状态-启用
   */
  public static final int CHGTYPESTATUS_2 = 2;

  /* 提取类型 */
  public static final String PICKUPTYPE = "org.xpup.hafmis.common.util.enumerate.PickUpType";

  /**
   * 提取类型-部分提取
   */
  public static final int PICKUPTYPE_1 = 1;

  /**
   * 提取类型-销户提取
   */
  public static final int PICKUPTYPE_2 = 2;

  /* 单位变更变更类型 */
  public static final String ORGCHGSTRUTS = "org.xpup.hafmis.common.util.enumerate.OrgChgStruts";

  /**
   * 单位变更变更类型-开户
   */
  public static final String ORGCHGSTRUTS_1 = "A";

  /**
   * 单位变更变更类型-启封
   */
  public static final String ORGCHGSTRUTS_2 = "B";

  /**
   * 单位变更变更类型-封存
   */
  public static final String ORGCHGSTRUTS_3 = "C";

  /**
   * 单位变更变更类型-注销
   */
  public static final String ORGCHGSTRUTS_4 = "D";

  /* 单位变更变更状态 */
  public static final String ORGCHGTYPE = "org.xpup.hafmis.common.util.enumerate.OrgChgType";

  /**
   * 单位变更变更类型-正常
   */
  public static final String ORGCHGTYPE_1 = "A";

  /**
   * 单位变更变更类型-封存
   */
  public static final String ORGCHGTYPE_2 = "B";

  /**
   * 单位变更变更类型-注销
   */
  public static final String ORGCHGTYPE_3 = "C";

  /* 扎账业务类型 */
  public static final String CLEARACCOUNTBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.ClearAccountBusinessType";

  /**
   * 扎账业务类型-调缴存
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_K = "K";

  /**
   * 扎账业务类型-调提取
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_L = "L";

  /**
   * 扎账业务类型-调其他
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_G = "G";

  /**
   * 扎账业务类型-提取
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_D = "D";

  /**
   * 扎账业务类型-转出
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_E = "E";

  /**
   * 扎账业务类型-汇缴
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_A = "A";

  /**
   * 扎账业务类型-个人补缴
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_B = "B";

  /**
   * 扎账业务类型-单位补缴
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_M = "M";

  /**
   * 扎账业务类型-挂账
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_C = "C";

  /**
   * 扎账业务类型-转入
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_F = "F";

  /**
   * 扎账业务类型-结息
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_H = "H";

  /**
   * 扎账业务类型-公积金余额结转
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_I = "I";

  /**
   * 扎账业务类型-挂账余额结转
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_J = "J";

  /* 单位版业务类型 */
  public static final String ORGBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.OrgBusinessType";

  /**
   * 汇缴
   */
  public static final String ORGBUSINESSTYPE_A = "A";

  /**
   * 单位补缴
   */
  public static final String ORGBUSINESSTYPE_B = "B";

  /**
   * 挂账
   */
  public static final String ORGBUSINESSTYPE_C = "C";

  /**
   * 提取
   */
  public static final String ORGBUSINESSTYPE_D = "D";

  /**
   * 转出
   */
  public static final String ORGBUSINESSTYPE_E = "E";

  /**
   * 转入
   */
  public static final String ORGBUSINESSTYPE_F = "F";

  /**
   * 调账
   */
  public static final String ORGBUSINESSTYPE_G = "G";

  /**
   * 结息
   */
  public static final String ORGBUSINESSTYPE_H = "H";

  /**
   * 特殊提取
   */
  public static final String ORGBUSINESSTYPE_I = "I";

  /**
   * 利率调整
   */
  public static final String ORGBUSINESSTYPE_J = "J";

  /**
   * 个人补缴
   */
  public static final String ORGBUSINESSTYPE_K = "K";

  /**
   * 汇缴比例调整
   */
  public static final String ORGBUSINESSTYPE_L = "L";

  /**
   * 工资基数调整
   */
  public static final String ORGBUSINESSTYPE_M = "M";

  /**
   * 缴额调整
   */
  public static final String ORGBUSINESSTYPE_N = "N";

  /**
   * 人员变更
   */
  public static final String ORGBUSINESSTYPE_O = "O";

  /**
   * 单位开户
   */
  public static final String ORGBUSINESSTYPE_P = "P";

  /**
   * 单位变更
   */
  public static final String ORGBUSINESSTYPE_Q = "Q";

  /* 扎账业务类型 */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL = "org.xpup.hafmis.common.util.enumerate.ClearAccountBusinessType_WL";

  /**
   * 扎账业务类型-调缴存
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_K = "K";

  /**
   * 扎账业务类型-调提取
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_L = "L";

  /**
   * 扎账业务类型-调其他
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_G = "G";

  /**
   * 扎账业务类型-提取
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_D = "D";

  /**
   * 扎账业务类型-转出
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_E = "E";

  /**
   * 扎账业务类型-汇缴
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_A = "A";

  /**
   * 扎账业务类型-个人补缴
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_B = "B";

  /**
   * 扎账业务类型-单位补缴
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_M = "M";

  /**
   * 扎账业务类型-挂账
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_C = "C";

  /**
   * 扎账业务类型-转入
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_F = "F";

  /* 归集银行状态 */
  public static final String COLLBANKSTATUS = "org.xpup.hafmis.common.util.enumerate.CollBankStatus";

  /**
   * 归集银行状态-正常
   */
  public static final int COLLBANKSTATUS_NORMAL = 1;

  /**
   * 归集银行状态-作废
   */
  public static final int COLLBANKSTATUS_CANCELED = 2;

  /* 提取状态 */
  public static final String PICKSTATUS = "org.xpup.hafmis.common.util.enumerate.PickStatus";

  /**
   * 提取状态-未使用
   */
  public static final int PICKSTATUS_NO = 1;

  /**
   * 提取状态-已使用
   */
  public static final int PICKSTATUS_YES = 2;

  /* 借贷发生方向 */
  public static final String OCCURREDDIRECTION = "org.xpup.hafmis.common.util.enumerate.OccurredDirection";

  /**
   * 借贷发生方向-借
   */
  public static final int OCCURREDDIRECTION_DEBIT = 1;

  /**
   * 借贷发生方向-贷
   */
  public static final int OCCURREDDIRECTION_CREDIT = 2;

  /**
   * 借贷发生方向-平
   */
  public static final int OCCURREDDIRECTION_PARALLEL = 3;

  /* 人员变更的变更原因 */
  public static final String CHGPERSONREASON = "org.xpup.hafmis.common.util.enumerate.ChgpersonReason";

  /**
   * 人员变更的变更原因－其他
   */
  public static final int CHGPERSONREASON_OTHER = 1;

  /**
   * 人员变更的变更原因－转出
   */
  public static final int CHGPERSONREASON_OUT = 2;

  /**
   * 人员变更的变更原因－转入
   */
  public static final int CHGPERSONREASON_IN = 3;

  /**
   * 人员变更的变更原因－注销
   */
  public static final int CHGPERSONREASON_DEL = 4;

  /* 结息类型 */
  public static final String CLEARINTERESTTYPE = "org.xpup.hafmis.common.util.enumerate.ClearInterestType";

  /**
   * 结息类型-年终结息
   */
  public static final String CLEARINTERESTTYPE_YEARCLEAR = "A";

  /**
   * 结息类型-销户提取
   */
  public static final String CLEARINTERESTTYPE_DELACCOUNT = "B";

  /**
   * 结息类型-转移
   */
  public static final String CLEARINTERESTTYPE_TRANS = "C";

  /* 特殊业务类型 */
  public static final String ESPECIALOPERATIONTYPE = "org.xpup.hafmis.common.util.enumerate.EspecialOperationType";

  /**
   * 财政代扣
   */
  public static final String ESPECIALOPERATIONTYPE_AGENT = "1";

  /**
   * 公积金还贷
   */
  public static final String ESPECIALOPERATIONTYPE_LOANBACK = "2";

  /* 代扣状态 */
  public static final String AGENTSTATUS = "org.xpup.hafmis.common.util.enumerate.AgentStatus";

  /**
   * 未使用
   */
  public static final String AGENTSTATUS_NO = "0";

  /**
   * 已使用
   */
  public static final String AGENTSTATUS_YES = "1";

  /** -----------------------------------------贷款---------------------------------------------------------------* */
  /* 贷款还款类型 */
  public static final String PLLOANRETURNTYPE = "org.xpup.hafmis.common.util.enumerate.PLLoanReturnType";

  /**
   * 以中心为主
   */
  public static final int PLLOANRETURNTYPE_CENTER = 1;

  /**
   * 以银行为主
   */
  public static final int PLLOANRETURNTYPE_BANK = 2;

  /* 个贷-贷款类型 */
  public static final String PLLOANTYPE = "org.xpup.hafmis.common.util.enumerate.PLLoanType";

  /**
   * 个贷-贷款类型-1-5年
   */
  public static final int PLLOANTYPE_FIVE = 0;

  /**
   * 个贷-贷款类型-5年以上
   */
  public static final int PLLOANTYPE_FIVEUP = 1;

  /**
   * 个贷-贷款类型-一年期
   */
  public static final int PLLOANTYPE_ONEYEAR = 2;

  /**
   * 个贷-贷款类型-两年期
   */
  public static final int PLLOANTYPE_TWOYEAR = 3;

  /* 个贷-扣款方式 */
  public static final String PLDEBITTYPE = "org.xpup.hafmis.common.util.enumerate.PLDebitType";

  /**
   * 个贷-扣款方式-足额扣款
   */
  public static final int PLDEBITTYPE_SUFF = 1;

  /**
   * 个贷-扣款方式-全额扣款
   */
  public static final int PLDEBITTYPE_ALL = 2;

  /* 个贷-还款日 */
  public static final String PLRECOVERDAY = "org.xpup.hafmis.common.util.enumerate.PLRecoverDay";

  /**
   * 个贷-还款日-按户定日
   */
  public static final int PLRECOVERDAY_ACCOUNT = 1;

  /**
   * 个贷-还款日-统一定日
   */
  public static final int PLRECOVERDAY_DAY = 2;

  /* 个贷-贷款各项信息 */
  public static final String PLALLMESS = "org.xpup.hafmis.common.util.enumerate.PLAllMess";

  /**
   * 个贷-贷款各项信息-正常本金
   */
  public static final String PLALLMESS_CORPUS = "A";

  /**
   * 个贷-贷款各项信息-正常利息
   */
  public static final String PLALLMESS_INTEREST = "B";

  /**
   * 个贷-贷款各项信息-逾期本金
   */
  public static final String PLALLMESS_OVERDUECORPUS = "C";

  /**
   * 个贷-贷款各项信息-逾期利息
   */
  public static final String PLALLMESS_OVERDUEINTEREST = "D";

  /**
   * 个贷-贷款各项信息-罚息
   */
  public static final String PLALLMESS_PUNISHINTEREST = "E";

  /* 个贷-罚息利率类型 */
  public static final String PLPUNISHINTERESTTYPE = "org.xpup.hafmis.common.util.enumerate.PLPunishInterestType";

  /**
   * 个贷-罚息利率-按罚息日利率
   */
  public static final int PLPUNISHINTERESTTYPE_PUNISHDAYRATE = 1;

  /**
   * 个贷-罚息利率-按合同日利率
   */
  public static final int PLPUNISHINTERESTTYPE_CONTRACTDAYRATE = 2;

  /**
   * 个贷-罚息利率-按额每日
   */
  public static final int PLPUNISHINTERESTTYPE_PAYMENTDAYRATE = 3;

  /* 个贷-贷款流程 */
  public static final String PLLOANPROCESS = "org.xpup.hafmis.common.util.enumerate.PLLoanProcess";

  /**
   * 个贷-贷款各项信息-审批贷款
   */
  public static final String PLLOANPROCESS_LOANAPPROVAL = "A";

  /**
   * 个贷-贷款各项信息-签订贷款
   */
  public static final String PLLOANPROCESS_CONTRACTSIGN = "B";

  /* 个贷-利率类型 */
  public static final String PLRATETYPE = "org.xpup.hafmis.common.util.enumerate.PLRateType";

  /**
   * 个贷-利率类型-活期利率
   */
  public static final int PLRATETYPE_SUDTDEMAND = 1;

  /**
   * 个贷-利率类型-定期利率
   */
  public static final int PLRATETYPE_SUBTREGULAR = 2;

  /* 个贷-签订合同状态-未签订or已签订 */
  public static final String PLISCONTRACTWRITE = "org.xpup.hafmis.common.util.enumerate.PLContractWrite";

  /**
   * 个贷-签订合同状态-未签订
   */
  public static final int PLCONTRACTWRITE_NO = 0;

  /**
   * 个贷-签订合同状态-已签订
   */
  public static final int PLCONTRACTWRITE_YES = 1;

  /* 个贷-常用状态-正常or作废 */
  public static final String PLCOMMONSTATUS_1 = "org.xpup.hafmis.common.util.enumerate.PLCommonStatus_FIR";

  /**
   * 个贷-常用状态-正常
   */
  public static final int PLCOMMONSTATUS_1_NORMAL = 0;

  /**
   * 个贷-常用状态-作废
   */
  public static final int PLCOMMONSTATUS_1_CANCELED = 1;

  /**
   * 个贷-常用状态-解除
   */
  // public static final int PLCOMMONSTATUS_1_RELIEVE = 2;
  /* 个贷-常用状态-允许or不允许 */
  public static final String PLCOMMONSTATUS_2 = "org.xpup.hafmis.common.util.enumerate.PLCommonStatus_SEC";

  /**
   * 个贷-常用状态-不允许
   */
  public static final int PLCOMMONSTATUS_2_NOTALLOW = 1;

  /**
   * 个贷-常用状态-允许
   */
  public static final int PLCOMMONSTATUS_2_ALLOW = 2;

  /* 民族 */
  public static final String NATIONAL = "org.xpup.hafmis.common.util.enumerate.National";

  /**
   * 民族-汉族
   */
  public static final int NATIONAL_HAN = 1;

  /**
   * 民族-满族
   */
  public static final int NATIONAL_MAN = 2;

  /* 个贷-还款方式 */
  public static final String PLRECOVERTYPE = "org.xpup.hafmis.common.util.enumerate.PLRecoverType";

  /**
   * 个贷-还款方式-等额本金
   */
  public static final int PLRECOVERTYPE_CORPUS = 1;

  /**
   * 个贷-还款方式-等额本息
   */
  public static final int PLRECOVERTYPE_INTEREST = 2;

  /**
   * 个贷-还款方式-其它
   */
  public static final int PLRECOVERTYPE_OTHER = 3;

  /**
   * 个贷-还款方式-一年期
   */
  public static final int PLRECOVERTYPE_ONEYEAR = 4;

  /**
   * 个贷-还款方式-两年期
   */
  public static final int PLRECOVERTYPE_TWOYEAR = 5;

  /* 个贷-购房类型 */
  public static final String PLHOUSETYPE = "org.xpup.hafmis.common.util.enumerate.PLHouseType";

  /**
   * 个贷-购房类型-商品房
   */
  public static final String PLHOUSETYPE_HOUSING = "01";

  /**
   * 个贷-购房类型-二手房
   */
  public static final String PLHOUSETYPE_SECHOUSING = "02";
  
  /*个贷-地点类型*/
  public static final String PL_LOANTYPE  = "org.xpup.hafmis.common.util.enumerate.PLLoanWhereType";
  /**
   * 个贷-地点类型-本地
   */
  public static final String PL_LOANTYPE_LOCAL ="0";
  /**
   * 个贷-地点类型-异地
   */
  public static final String PL_LOANTYPE_OTHERS ="1";

  /* 个贷-协作单位类型 */
  public static final String PLASSISTANTORG_TYPE = "org.xpup.hafmis.common.util.enumerate.PLAssistantOrgType";

  /**
   * 个贷_协作单位类型-担保公司
   */
  public static final String PLASSURE_ORG = "A";

  /**
   * 个贷_协作单位类型-代理机构
   */
  public static final String PLSURROGATE_ORG = "B";

  /**
   * 个贷_协作单位类型-保险公司
   */
  public static final String PLSPONSION_ORG = "C";

  /**
   * 个贷_协作单位类型-评估机构
   */
  public static final String PLINSURANCE_ORG = "D";

  /**
   * 个贷_协作单位类型-公证处
   */
  public static final String PLNOTARIZATION_ORG = "E";

  /* 个贷-合同状态 */
  public static final String PLCONTRACTSTATUS = "org.xpup.hafmis.common.util.enumerate.PLContractStatus";

  /**
   * 个贷-合同状态-申请
   */
  public static final int PLCONTRACTSTATUS_APPL = 1;

  /**
   * 个贷-合同状态-提交审核
   */
  //public static final int PLCONTRACTSTATUS_AUDIT = 2;

  /**
   * 个贷-合同状态-审核通过
   */
  public static final int PLCONTRACTSTATUS_THROUGHAUDIT = 3;

  /**
   * 个贷-合同状态-审批通过
   */
  public static final int PLCONTRACTSTATUS_THROUGHAPPROVAL = 4;

  /**
   * 个贷-合同状态-审核未通过
   */
  public static final int PLCONTRACTSTATUS_NOTHROUGHAUDIT = 5;

  /**
   * 个贷-合同状态-审批未通过
   */
  public static final int PLCONTRACTSTATUS_NOTHROUGHAPPROVAL = 6;

  /**
   * 个贷-合同状态-再次审核
   */
  public static final int PLCONTRACTSTATUS_SECAUDIT = 7;

  /**
   * 个贷-合同状态-再次审批
   */
  public static final int PLCONTRACTSTATUS_SECAPPROVAL = 8;

  /**
   * 个贷-合同状态-打印借据
   */
  public static final int PLCONTRACTSTATUS_ISSUEDNOTICES = 9;

  /**
   * 个贷-合同状态-已发放
   */
  public static final int PLCONTRACTSTATUS_ISSUING = 10;

  /**
   * 个贷-合同状态-还款中
   */
  public static final int PLCONTRACTSTATUS_RECOVING = 11;

  /**
   * 个贷-合同状态-已还清
   */
  public static final int PLCONTRACTSTATUS_RECOVERCLEAR = 12;

  /**
   * 个贷-合同状态-注销
   */
  public static final int PLCONTRACTSTATUS_CANCELLATION = 13;

  /**
   * 个贷-合同状态-终审通过
   */
  public static final int PLCONTRACTSTATUS_FINALJUDGMENT = 14;

  /**
   * 个贷-合同状态-初审通过
   */
  public static final int PLCONTRACTSTATUS_FIRSTCHECK = 15;

  /**
   * 个贷-合同状态-回件确认
   */
  public static final int PLCONTRACTSTATUS_REDATESURE = 16;

  /**
   * 个贷-合同状态-拨款审核通过
   */
  public static final int PLCONTRACTSTATUS_CHKAGAIN = 17;

  /**
   * 个贷-合同状态-拨款审批通过
   */
  public static final int PLCONTRACTSTATUS_APPROVALAGAIN = 18;
  
  /**
   * 个贷-合同状态-拨款审核通过
   */
  public static final int PLCONTRACTSTATUS_CHKAGAIN_NOTPASS = 19;

  /**
   * 个贷-合同状态-拨款审批通过
   */
  public static final int PLCONTRACTSTATUS_APPROVALAGAIN_NOTPASS = 20;

  /* 个贷-提前还款类型 */
  public static final String PLADVANCERECOVERTYPE = "org.xpup.hafmis.common.util.enumerate.PLAdvanceRecoverType";

  /**
   * 个贷-提前还款类型-部分提前还款
   */
  public static final int PLADVANCERECOVERTYPE_PART = 1;

  /**
   * 个贷-提前还款类型-一次性清还
   */
  public static final int PLADVANCERECOVERTYPE_ALL = 2;

  /* 个贷-业务状态 */
  public static final String PLBUSINESSSTATE = "org.xpup.hafmis.common.util.enumerate.PLBusinessState";

  /**
   * 个贷-业务状态-导出
   */
  public static final int PLBUSINESSSTATE_EXP = 1;

  /**
   * 个贷-业务状态-导入
   */
  public static final int PLBUSINESSSTATE_IMP = 2;

  /**
   * 个贷-业务状态-登记
   */
  public static final int PLBUSINESSSTATE_SIGN = 3;

  /**
   * 个贷-业务状态-确认
   */
  public static final int BUSINESSSTATE_SURE = 4;

  /**
   * 个贷-业务状态-复核
   */
  public static final int BUSINESSSTATE_CHECK = 5;

  /**
   * 个贷-业务状态-入账
   */
  public static final int BUSINESSSTATE_CLEARACCOUNT = 6;

  /* 个贷-业务类型 */
  public static final String PLBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.PLBusinessType";

  /**
   * 个贷-业务类型-发放
   */
  public static final int PLBUSINESSTYPE_ISSUED = 1;

  /**
   * 个贷-业务类型-单笔回收
   */
  public static final int PLBUSINESSTYPE_SINGLERECOVER = 2;

  /**
   * 个贷-业务类型-部分提前还款
   */
  public static final int PLBUSINESSTYPE_PARTRECOVER = 3;

  /**
   * 个贷-业务类型-一次性还清
   */
  public static final int PLBUSINESSTYPE_ALLCLEAR = 4;

  /**
   * 个贷-业务类型-批量回收
   */
  public static final int PLBUSINESSTYPE_SOMERECOVER = 5;

  /**
   * 个贷-业务类型-呆账核销（中心）
   */
  public static final int PLBUSINESSTYPE_BADDEBTSOFFCENTRE = 6;

  /**
   * 个贷-业务类型-呆账核销（其他）
   */
  public static final int PLBUSINESSTYPE_BADDEBTSOFFOTHER = 7;

  /**
   * 个贷-业务类型-核销收回（中心）
   */
  public static final int PLBUSINESSTYPE_BADDEBTSRECOVERCENTER = 8;

  /**
   * 个贷-业务类型-核销收回（其他）
   */
  public static final int PLBUSINESSTYPE_BADDEBTSRECOVEROTHER = 9;

  /**
   * 个贷-业务类型-结转逾期
   */
  public static final int PLBUSINESSTYPE_CARRYOVERDUE = 10;

  /**
   * 个贷-业务类型-结转余额
   */
  public static final int PLBUSINESSTYPE_CARRYPAY = 11;

  /**
   * 个贷-业务类型-错账调整
   */
  public static final int PLBUSINESSTYPE_MISDIRECTCHG = 12;

  /**
   * 个贷-业务类型-挂账
   */
  public static final int PLBUSINESSTYPE_OVERPAY = 13;

  /**
   * 个贷-业务类型-保证金
   */
  public static final int PLBUSINESSTYPE_MARGIN = 14;

  /**
   * 个贷-业务类型-结息
   */
  public static final int PLBUSINESSTYPE_CLEARINTEREST = 15;

  /**
   * 个贷-业务类型-余额结转
   */
  public static final int PLBUSINESSTYPE_INITDATA = 16;

  /* 个贷-核销单位 */
  public static final String PLCANCELORG = "org.xpup.hafmis.common.util.enumerate.PLCancelORG";

  /**
   * 个贷-核销单位-中心
   */
  public static final int PLCANCELORG_CENTRE = 1;

  /**
   * 个贷-核销单位-担保公司
   */
  public static final int PLCANCELORG_GUARANTEECORP = 2;

  /**
   * 个贷-核销单位-其他
   */
  public static final int PLCANCELORG_OTHERS = 3;

  /* 个贷-提前还款后对还贷参数的更改类型 */
  public static final String PLRECOVERPARASCHG = "org.xpup.hafmis.common.util.enumerate.PLRecoverParasChg";

  /**
   * 个贷-提前还款后对还贷月数的更改类型-保持原来月还款额
   */
  public static final int PLRECOVERPARASCHG_SAMEPAY = 1;

  /**
   * 个贷-提前还款后对还贷月数的更改类型-保持剩余期限
   */
  public static final int PLRECOVERPARASCHG_SAMEMONTHS = 2;

  /**
   * 个贷-提前还款后对还贷月数的更改类型-允许改变剩余期限
   */
  public static final int PLRECOVERPARASCHG_CHGMONTHS = 3;

  /* 个贷-提前还款最低金额 */
  public static final String PLMINSPREPAYMENT = "org.xpup.hafmis.common.util.enumerate.PLMinsPrepayment";

  /**
   * 个贷-提前还款最低金额
   */
  public static final int PLMINSPREPAYMENT_1 = 1;

  /* 个贷-年度内最多允许提前还款 */
  public static final String PLRPREPAYMENTYEARTIMES = "org.xpup.hafmis.common.util.enumerate.PLPrepaymentYearTimes";

  /**
   * 个贷-年度内最多允许提前还款
   */
  public static final int PLRPREPAYMENTYEARTIMES_1 = 1;

  /* 个贷-贷款期限内最多允许提前还款 */
  public static final String PLPREPAYMENTDEADLINESTIMES = "org.xpup.hafmis.common.util.enumerate.PLPrepaymentDeadlinesTimes";

  /**
   * 个贷-贷款期限内最多允许提前还款
   */
  public static final int PLPREPAYMENTDEADLINESTIMES_1 = 1;

  /* 个贷-提前还款是否收取手续费 */
  public static final String PLPREPAYMENTFEES = "org.xpup.hafmis.common.util.enumerate.PLPrepaymentFees";

  /**
   * 个贷-提前还款是否收取手续费-是，按提前还款额
   */
  public static final int PLPREPAYMENTFEES_PREPAYMENT = 1;

  /**
   * 个贷-提前还款是否收取手续费-是，按额
   */
  public static final int PLPREPAYMENTFEES_PAYMENT = 2;

  /**
   * 个贷-提前还款是否收取手续费-否
   */
  public static final int PLPREPAYMENTFEES_NO = 3;

  /* 启用状态 */
  public static final String APPSTATUS = "org.xpup.hafmis.common.util.enumerate.AppStatus";

  /**
   * 变更状态-未启用
   */
  public static final int APPSTATUS_1 = 0;

  /**
   * 变更状态-启用
   */
  public static final int APPSTATUS_2 = 1;

  /* 个贷-合同变更信息 */
  public static final String CONTRACTCHANGEINFO = "org.xpup.hafmis.common.util.enumerate.PLContractChangeInfo";

  /**
   * 个贷-合同变更信息，借款人信息
   */
  public static final int PLPREPAYMENTFEES_BORROWERINFO = 1;

  /**
   * 个贷-合同变更信息,辅助借款人信息
   */
  public static final int PLPREPAYMENTFEES_AUXILIARYINFO = 2;

  /**
   * 个贷-合同变更信息,购房信息
   */
  public static final int PLPREPAYMENTFEES_FLOORINFO = 3;

  /**
   * 个贷-合同变更信息,抵押合同信息
   */
  public static final int PLPREPAYMENTFEES_PLEDGEINFO = 4;

  /**
   * 个贷-合同变更信息,质押合同信息
   */
  public static final int PLPREPAYMENTFEES_IMPAWNINFO = 5;

  /**
   * 个贷-合同变更信息,保证人
   */
  public static final int PLPREPAYMENTFEES_BIALINFO = 6;

  /* 个贷-被冻结类型信息 */
  public static final String CONGEALINFOTYPE = "org.xpup.hafmis.common.util.enumerate.PLCongealInfoType";

  /**
   * 个贷-被冻结类型，借款人
   */
  public static final int PLPREPAYMENTFEES_BORROWERTYPE = 1;

  /**
   * 个贷-被冻结类型,辅助借款人
   */
  public static final int PLPREPAYMENTFEES_AUXILIARYTYPE = 2;

  /**
   * 个贷-被冻结类型,保证人
   */
  public static final int PLPREPAYMENTFEES_BIALTYPE = 3;

  /* 个贷-被冻结类型信息 */
  public static final String CONGEALINFOTHAWTYPE = "org.xpup.hafmis.common.util.enumerate.PLCongealInfoThaw";

  /**
   * 个贷-被冻结类型，解冻
   */
  public static final int PLPREPAYMENTFEES_CONGEALINFOTHAW = 1;

  /**
   * 个贷-被冻结类型,冻结
   */
  public static final int PLPREPAYMENTFEES_CONGEALINFOGELATION = 0;

  /* 账户状态-业务种类为贷款 */
  public static final String GIVEBACK = "org.xpup.hafmis.common.util.enumerate.PLGiveBack";

  /**
   * 还款类型-业务种类-正常
   */
  public static final int PLPREPAYMENTFEES_NORMAL = 1;

  /**
   * 还款类型-业务种类-逾期
   */
  public static final int PLPREPAYMENTFEES_OVERDUE = 2;

  /**
   * 还款类型-业务种类-其它
   */
  public static final int PLPREPAYMENTFEES_OTHERS = 3;

  /*-----------------财务-------------------------*/
  /* 核算方式 */
  public static final String FNSEETLETYPE = "org.xpup.hafmis.common.util.enumerate.FnSettleType";

  /**
   * 核算方式-统一核算
   */
  public static final String FNSEETLETYPE_UNIONIZE = "0";

  /**
   * 核算方式-独立核算
   */
  public static final String FNSEETLETYPE_INDEPENDENCE = "1";

  /* 财务凭证号类型 */
  public static final String CREDENCE_NUM_TYPE = "org.xpup.hafmis.common.util.enumerate.CredenceNumType";

  /**
   * 财务凭证号类型-会计凭证
   */
  public static final String CREDENCE_NUM_TYPE_ACCOUNTANT = "0";

  /**
   * 财务凭证号类型-出纳凭证
   */
  public static final String CREDENCE_NUM_TYPE_TREASURER = "1";

  /* 财务-凭证类型 */
  public static final String CREDENCETYPE = "org.xpup.hafmis.common.util.enumerate.FnCredenceType";

  /**
   * 财务凭证号类型-现金凭证
   */
  public static final String CREDENCETYPE_CASH = "0";

  /**
   * 财务凭证号类型-银行存款凭证
   */
  public static final String CREDENCETYPE_BANK = "1";

  /* 财务-凭证状态&业务活动日志动作 */
  public static final String CREDSTATE = "org.xpup.hafmis.common.util.enumerate.FnCredenceState";

  /**
   * 凭证状态-确认
   */
  public static final String CREDSTATE_SIGN = "0";

  /**
   * 凭证状态-复核
   */
  public static final String CREDSTATE_CHECK = "1";

  /**
   * 凭证状态-入账
   */
  public static final String CREDSTATE_BOOKKEEPING = "2";

  /* 财务-科目类别代码 */
  public static final String SUBCATEGORYCODE = "org.xpup.hafmis.common.util.enumerate.FnSubjectCategoryCode";

  /**
   * 科目类别代码-资产
   */
  public static final String SUBCATEGORYCODE_ASSETS = "0";

  /**
   * 科目类别代码-负债
   */
  public static final String SUBCATEGORYCODE_LIABILITIES = "1";

  /**
   * 科目类别代码-权益
   */
  public static final String SUBCATEGORYCODE_INTERESTS = "2";

  /**
   * 科目类别代码-成本
   */
  public static final String SUBCATEGORYCODE_COST = "3";

  /**
   * 科目类别代码-损益
   */
  public static final String SUBCATEGORYCODE_PROFIT_LOSS = "4";

  /* 财务-余额方向 */
  public static final String BALANCEDIRECTION = "org.xpup.hafmis.common.util.enumerate.FnBalanceDirection";

  /**
   * 余额方向-借
   */
  public static final String BALANCEDIRECTION_DEBIT = "0";

  /**
   * 余额方向-贷
   */
  public static final String BALANCEDIRECTION_CREDIT = "1";

  /**
   * 余额方向-平
   */
  public static final String BALANCEDIRECTION_AVE = "2";

  /* 财务-科目属性 */
  public static final String SUBATTRIBUTE = "org.xpup.hafmis.common.util.enumerate.FnSubjectsAttribute";

  /**
   * 科目属性-现金
   */
  public static final String SUBATTRIBUTE_CASH = "0";

  /**
   * 科目属性-银行
   */
  public static final String SUBATTRIBUTE_BANK = "1";

  /**
   * 科目属性-其他
   */
  public static final String SUBATTRIBUTE_OTHERS = "2";

  /* 财务-核算关系类型 */
  public static final String SUBRELATION = "org.xpup.hafmis.common.util.enumerate.FnSubjectRelation";

  /**
   * 核算关系类型-办事处
   */
  public static final String SUBRELATION_OFFICE = "0";

  /**
   * 核算关系类型-银行
   */
  public static final String SUBRELATION_BANK = "1";

  /**
   * 核算关系类型-单位
   */
  public static final String SUBRELATION_ORG = "2";

  /**
   * 核算关系类型-其他
   */
  public static final String SUBRELATION_OTHERS = "3";

  /* 财务-报表查询方式 */
  public static final String REPORTQUERYMANNER = "org.xpup.hafmis.common.util.enumerate.FnReportQueryManner";

  /**
   * 报表查询方式-按年
   */
  public static final String REPORTQUERYMANNER_YEAR = "0";

  /**
   * 报表查询方式-按月
   */
  public static final String REPORTQUERYMANNER_MONTH = "1";

  /**
   * 报表查询方式-按日
   */
  public static final String REPORTQUERYMANNER_DAY = "2";

  /* 财务-结转状态 */
  public static final String CARRYOVERSTATE = "org.xpup.hafmis.common.util.enumerate.FnCarryoverState";

  /**
   * 结转状态-未结转
   */
  public static final String CARRYOVERSTATE_NO = "0";

  /**
   * 结转状态-已结转未记账
   */
  public static final String CARRYOVERSTATE_YESNOKEEPING = "1";

  /**
   * 结转状态-已记账
   */
  public static final String CARRYOVERSTATE_BOOKKEEPING = "2";

  /* 财务-业务类型 */
  public static final String FNBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.FnBusinessType";

  /**
   * 业务类型-公积金_缴存
   */
  public static final String FNBUSINESSTYPE_COLL_PAYMENT = "1";

  /**
   * 业务类型-公积金_部分提取
   */
  public static final String FNBUSINESSTYPE_COLL_SOMEPICK = "2";

  /**
   * 业务类型-公积金_销户提取
   */
  public static final String FNBUSINESSTYPE_COLL_ALLPICK = "3";

  /**
   * 业务类型-公积金_外部转出
   */
  public static final String FNBUSINESSTYPE_COLL_OUTTRANSOUT = "4";

  /**
   * 业务类型-公积金_外部转入
   */
  public static final String FNBUSINESSTYPE_COLL_OUTTRANSIN = "5";

  /**
   * 业务类型-公积金_内部转出
   */
  public static final String FNBUSINESSTYPE_COLL_INTRANSOUT = "6";

  /**
   * 业务类型-公积金_内部转入
   */
  public static final String FNBUSINESSTYPE_COLL_INTRANSIN = "7";

  /**
   * 业务类型-公积金_内部转入转出
   */
  public static final String FNBUSINESSTYPE_COLL_INTRANS = "8";

  /**
   * 业务类型-公积金_挂账（收）
   */
  public static final String FNBUSINESSTYPE_COLL_OVERPAYMENT = "9";

  /**
   * 业务类型-公积金_挂账（提取）
   */
  public static final String FNBUSINESSTYPE_COLL_OVERPICKPAYMENT = "10";

  /**
   * 业务类型-公积金_结息
   */
  public static final String FNBUSINESSTYPE_COLL_CLEARINTEREST = "11";

  /**
   * 业务类型-公积金_错账调增
   */
  public static final String FNBUSINESSTYPE_COLL_ERRACCOUNTUP = "12";

  /**
   * 业务类型-公积金_错账调减
   */
  public static final String FNBUSINESSTYPE_COLL_ERRACCOUNTDOWN = "13";

  /**
   * 业务类型-公积金还贷
   */
  public static final String FNBUSINESSTYPE_COLL_GJJBACK = "23";

  /**
   * 业务类型-贷款_发放
   */
  public static final String FNBUSINESSTYPE_LOAN_ACCORD = "21";

  /**
   * 业务类型-贷款_回收
   */
  public static final String FNBUSINESSTYPE_LOAN_CALLBACK = "22";

  /* 财务-金额类型 */
  public static final String FNMONEYTYPE = "org.xpup.hafmis.common.util.enumerate.FnMoneyType";

  /**
   * 金额类型-公积金_缴存金额
   */
  public static final String FNMONEYTYPE_COLL_PAYMENT = "1";

  /**
   * 金额类型-公积金_提取金额
   */
  public static final String FNMONEYTYPE_COLL_PICK = "2";

  /**
   * 金额类型-公积金_销户利息
   */
  public static final String FNMONEYTYPE_COLL_CLEARACCOUNTINTEREST = "3";

  /**
   * 金额类型-公积金_转出金额
   */
  public static final String FNMONEYTYPE_COLL_TRANSOUT = "4";

  /**
   * 金额类型-公积金_转入金额
   */
  public static final String FNMONEYTYPE_COLL_TTRANSIN = "5";

  /**
   * 金额类型-公积金_转出结息
   */
  public static final String FNMONEYTYPE_COLL_TRANSOUTINTEREST = "6";

  /**
   * 金额类型-公积金_结息利息
   */
  public static final String FNMONEYTYPE_COLL_CLEARINTEREST = "7";

  /**
   * 金额类型-公积金_挂账金额
   */
  public static final String FNMONEYTYPE_COLL_OVERPAYMENT = "8";

  /**
   * 金额类型-贷款_发放金额
   */
  public static final String FNMONEYTYPE_LOAN_ACCORD = "11";

  /**
   * 金额类型-贷款_回收正常本金
   */
  public static final String FNMONEYTYPE_LOAN_RECEVERNORMALPRINCIPAL = "12";

  /**
   * 金额类型-贷款_回收逾期本金
   */
  public static final String FNMONEYTYPE_LOAN_RECEVEROVERDUEPRINCIPAL = "13";

  /**
   * 金额类型-贷款_回收利息
   */
  public static final String FNMONEYTYPE_LOAN_RECEVERINTEREST = "14";

  /**
   * 金额类型-贷款_核销收回金额
   */
  public static final String FNMONEYTYPE_LOAN_CLEARRECOVERMONEY = "15";

  /**
   * 金额类型-贷款_挂账金额
   */
  public static final String FNMONEYTYPE_LOAN_OVERPAYMENT = "16";

  /**
   * 金额类型-贷款_保证金额
   */
  public static final String FNMONEYTYPE_LOAN_MARGIN = "17";

  /**
   * 金额类型-贷款_保证金利息
   */
  public static final String FNMONEYTYPE_LOAN_MARGININTEREST = "18";

  /**
   * 金额类型-贷款_实收金额
   */
  public static final String FNMONEYTYPE_LOAN_REALRECEVER = "19";

  /**
   * 金额类型-公积金_冲贷金额
   */
  public static final String FNMONEYTYPE_LOAN_PUNISHINTEREST = "20";
  
  /**
   * 金额类型-贷款_提取金额
   */
  public static final String FNMONEYTYPE_LOAN_PICKMONEY = "21";

  /* 财务-报表公式标识 */
  public static final String REPORTLOGO = "org.xpup.hafmis.common.util.enumerate.FnReportLogo";

  /**
   * 报表公式标识-年初余额
   */
  public static final String REPORTLOGO_BEGBALANCE_DEBIT = "AA";

  /**
   * 报表公式标识-期初余额
   */
  public static final String REPORTLOGO_BEGBALANCE_CREDIT = "AB";

  /**
   * 报表公式标识-期末余额(不用)
   */
  public static final String REPORTLOGO_ENDBALANCE_DEBIT = "AC";

  /**
   * 报表公式标识-期末余额
   */
  public static final String REPORTLOGO_ENDBALANCE_CREDIT = "AD";

  /**
   * 报表公式标识-本期发生额（借方）
   */
  public static final String REPORTLOGO_CURFIGURES_DEBIT = "AE";

  /**
   * 报表公式标识-本期发生额（贷方）
   */
  public static final String REPORTLOGO_CURFIGURES_CREDIT = "AF";

  /**
   * 报表公式标识-（原）本年发生额（借方）改为：本年累计发生额（借方）
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT = "AG";

  /**
   * 报表公式标识-（原）本年发生额（贷方）改为：本年累计发生额（贷方）
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT = "AH";

  /**
   * 报表公式标识-（原）上年发生额（借方）改为：上年累计发生额（借方）
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_DEBIT = "AI";

  /**
   * 报表公式标识-（原）上年发生额（贷方）改为：上年累计发生额（贷方）
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_CREDIT = "AJ";

  /**
   * 报表公式标识-本期累计发生额（借方）（不用）
   */
  public static final String REPORTLOGO_CURFIGURES_SUMDEBIT = "AK";

  /**
   * 报表公式标识-本期累计发生额（贷方）（不用）
   */
  public static final String REPORTLOGO_CURFIGURES_SUMCREDIT = "AL";

  /**
   * 报表公式标识-（原）本年累计发生额（借方）改为：期末累计发生额（借方）
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT = "AM";

  /**
   * 报表公式标识-（原）本年累计发生额（贷方）改为：期末累计发生额（贷方）
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT = "AN";

  /**
   * 报表公式标识-上年累计发生额（借方）（不用）
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT = "AO";

  /**
   * 报表公式标识-上年累计发生额（贷方）（不用）
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT = "AP";

  /**
   * 报表公式标识-行
   */
  public static final String REPORTLOGO_COL = "AQ";

  /**
   * 报表公式标识-提取金额（购买、建造、翻建、大修住房提取）本期发生额
   */
  public static final String REPORTLOGO_REPAIR_CURTERMAMOUNT = "BA";

  /**
   * 报表公式标识-提取金额（购买、建造、翻建、大修住房提取）本年累计发生额
   */
  public static final String REPORTLOGO_REPAIR_CURYEARAMOUNT = "BB";

  /**
   * 报表公式标识-提取金额（购买、建造、翻建、大修住房提取）期末累计发生额
   */
  public static final String REPORTLOGO_REPAIR_CURYEARSUMAMOUNT = "BC";

  /**
   * 报表公式标识-提取金额（离退休提取）本期发生额
   */
  public static final String REPORTLOGO_RETIREMENT_CURTERMAMOUNT = "BD";

  /**
   * 报表公式标识-提取金额（离退休提取）本年累计发生额
   */
  public static final String REPORTLOGO_RETIREMENT_CURYEARAMOUNT = "BE";

  /**
   * 报表公式标识-提取金额（离退休提取）期末累计发生额
   */
  public static final String REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT = "BF";

  /**
   * 报表公式标识-提取金额（完全丧失劳动能力，与单位终止劳动关系提取）本期发生额
   */
  public static final String REPORTLOGO_LOSEABILITY_CURTERMAMOUNT = "BG";

  /**
   * 报表公式标识-提取金额（完全丧失劳动能力，与单位终止劳动关系提取）本年累计发生额
   */
  public static final String REPORTLOGO_LOSEABILITY_CURYEARAMOUNT = "BH";

  /**
   * 报表公式标识-提取金额（完全丧失劳动能力，与单位终止劳动关系提取）期末累计发生额
   */
  public static final String REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT = "BI";

  /**
   * 报表公式标识-提取金额（出国定居提取）本期发生额
   */
  public static final String REPORTLOGO_ABROAD_CURTERMAMOUNT = "BJ";

  /**
   * 报表公式标识-提取金额（出国定居提取）本年累计发生额
   */
  public static final String REPORTLOGO_ABROAD_CURYEARAMOUNT = "BK";

  /**
   * 报表公式标识-提取金额（出国定居提取）期末累计发生额
   */
  public static final String REPORTLOGO_ABROAD_CURYEARSUMAMOUNT = "BL";

  /**
   * 报表公式标识-提取金额（偿还购房贷款本息提取）本期发生额
   */
  public static final String REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT = "BM";

  /**
   * 报表公式标识-提取金额（偿还购房贷款本息提取）本年累计发生额
   */
  public static final String REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT = "BN";

  /**
   * 报表公式标识-提取金额（偿还购房贷款本息提取）期末累计发生额
   */
  public static final String REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT = "BO";

  /**
   * 报表公式标识-提取金额（支付房租提取）本期发生额
   */
  public static final String REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT = "BP";

  /**
   * 报表公式标识-提取金额（支付房租提取）本年累计发生额
   */
  public static final String REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT = "BQ";

  /**
   * 报表公式标识-提取金额（支付房租提取）期末累计发生额
   */
  public static final String REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT = "BR";

  /**
   * 报表公式标识-提取金额（其他提取）本期发生额
   */
  public static final String REPORTLOGO_OTHERS_CURTERMAMOUNT = "BS";

  /**
   * 报表公式标识-提取金额（其他提取）本年累计发生额
   */
  public static final String REPORTLOGO_OTHERS_CURYEARAMOUNT = "BT";

  /**
   * 报表公式标识-提取金额（其他提取）期末累计发生额
   */
  public static final String REPORTLOGO_OTHERS_CURYEARSUMAMOUNT = "BU";

  /**
   * 报表公式标识-!公式：
   */
  public static final String REPORTLOGO_FORMULA = "J";

  /* 财务-账簿管理-各种合计描述 */
  public static final String FNSUMMARY = "org.xpup.hafmis.common.util.enumerate.FnSummary";

  /**
   * 上 年 结 转
   */
  public static final String FNSUMMARY_LASTYEARCLEAR = "0";

  /**
   * 期 初 余 额
   */
  public static final String FNSUMMARY_BGNBLAN = "1";

  /**
   * 本 日 合 计
   */
  public static final String FNSUMMARY_DAYSUM = "2";

  /**
   * 本 期 合 计
   */
  public static final String FNSUMMARY_TERMSUM = "3";

  /**
   * 本 年 累 计
   */
  public static final String FNSUMMARY_YEARSUM = "4";

  /* 财务-核算类型 */
  public static final String SETTLETYPE = "org.xpup.hafmis.common.util.enumerate.SettleType";

  /**
   * 全部
   */
  public static final String SETTLETYPE_ALL = "0";

  /**
   * 单位
   */
  public static final String SETTLETYPE_ORG = "1";

  /**
   * 银行
   */
  public static final String SETTLETYPE_BANK = "2";

  /**
   * 办事处
   */
  public static final String SETTLETYPE_OFFICE = "3";

  /* 公积金还贷状态 */
  public static final String COLLLOANBACKSTATUS = "org.xpup.hafmis.common.util.enumerate.CollLoanbackStatus";

  /**
   * 导入
   */
  public static final String COLLLOANBACKSTATUS_IMPORT = "1";

  /**
   * 扣款
   */
  public static final String COLLLOANBACKSTATUS_KOUMONEY = "2";

  /**
   * 导出
   */
  public static final String COLLLOANBACKSTATUS_EXPORT = "3";

  /* 特殊业务凭证模式 */
  public static final String SPECAILBIZMODEL = "org.xpup.hafmis.common.util.enumerate.SpecailBizModel";

  /**
   * 特殊业务凭证模式-办事处
   */
  public static final String SPECAILBIZMODEL_OFFICE = "1";

  /**
   * 特殊业务凭证模式-银行
   */
  public static final String SPECAILBIZMODEL_BANK = "2";

  /**
   * 特殊业务凭证模式-单位
   */
  public static final String SPECAILBIZMODEL_ORG = "3";

  /* 是否存在单位版 */
  public static final String ISHAVEORGVERSION = "org.xpup.hafmis.common.util.enumerate.IshaveOrgversion";

  /**
   * 是否存在单位版-不存在
   */
  public static final String IS_NOHAVE = "0";

  /**
   * 是否存在单位版-存在
   */
  public static final String IS_HAVE = "1";

  /* 个贷-阿拉伯数字转换英文数字 */
  public static final String TENNUMBER = "org.xpup.hafmis.common.util.enumerate.TenNumber";

  public static final String TENNUMBER_1 = "1";

  public static final String TENNUMBER_2 = "2";

  public static final String TENNUMBER_3 = "3";

  public static final String TENNUMBER_4 = "4";

  public static final String TENNUMBER_5 = "5";

  public static final String TENNUMBER_6 = "6";

  public static final String TENNUMBER_7 = "7";

  public static final String TENNUMBER_8 = "8";

  public static final String TENNUMBER_9 = "9";

  public static final String TENNUMBER_10 = "10";

  public static final String TENNUMBER_11 = "11";

  public static final String TENNUMBER_12 = "12";

  public static final String TENNUMBER_13 = "13";

  public static final String TENNUMBER_14 = "14";

  public static final String TENNUMBER_15 = "15";

  public static final String TENNUMBER_16 = "16";

  public static final String TENNUMBER_17 = "17";

  public static final String TENNUMBER_18 = "18";

  public static final String TENNUMBER_19 = "19";

  public static final String TENNUMBER_20 = "20";

  public static final String TENNUMBER_21 = "21";

  public static final String TENNUMBER_22 = "22";

  /* 公积金业务活动日志查询 */
  public static final String BUSINESSLOGSEARCH = "org.xpup.hafmis.common.util.enumerate.BusinessLogSearch";

  /**
   * 汇缴
   */
  public static final String BUSINESSLOGSEARCH_PAYMENT = "A";

  /**
   * 单位补缴
   */
  public static final String BUSINESSLOGSEARCH_ORGADDPAY = "B";

  /**
   * 挂账
   */
  public static final String BUSINESSLOGSEARCH_ORGOVERPAY = "C";

  /**
   * 提取
   */
  public static final String BUSINESSLOGSEARCH_PICKUP = "D";

  /**
   * 转出
   */
  public static final String BUSINESSLOGSEARCH_TRANOUT = "E";

  /**
   * 转入
   */
  public static final String BUSINESSLOGSEARCH_TRANIN = "F";

  /**
   * 调账
   */
  public static final String BUSINESSLOGSEARCH_CHGACCOUNT = "G";

  /**
   * 结息
   */
  public static final String BUSINESSLOGSEARCH_OVERDUEINTEREST = "H";

  /**
   * 特殊提取
   */
  public static final String BUSINESSLOGSEARCH_SPECIALPICKUP = "I";

  /**
   * 利率调整
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTINTEREST = "J";

  /**
   * 个人补缴
   */
  public static final String BUSINESSLOGSEARCH_EMPADDPAY = "K";

  /**
   * 汇缴比例调整
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTPAYMENTRATE = "L";

  /**
   * 工资基数调整
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTSALARYBASE = "M";

  /**
   * 缴额调整
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTPAYMENT = "N";

  /**
   * 人员变更
   */
  public static final String BUSINESSLOGSEARCH_CHGPERSON = "O";

  /**
   * 单位开户
   */
  public static final String BUSINESSLOGSEARCH_ORGOPENACCOUNT = "P";

  /**
   * 单位变更
   */
  public static final String BUSINESSLOGSEARCH_CHGORG = "Q";

  /* 企业版开发 */
  public static final String ORG_OR_CENTER_INFO = "org.xpup.hafmis.common.util.enumerate.OcIsOrgOrCenter";

  /**
   * 操作模块-开发过程中-单位版
   */
  public static final int ORG_OR_CENTER_INFO_ORG = 1;

  /**
   * 操作模块-开发过程中-中心版
   */
  public static final int ORG_OR_CENTER_INFO_CENTER = 2;

  /* 提取状态 */
  public static final String OC_NOT_PICK_UP_INFO = "org.xpup.hafmis.common.util.enumerate.OcIsPickUp";

  /**
   * 操作模块-开发过程中-未提取
   */
  public static final String OC_NOT_PICK_UP = "0";

  /**
   * 操作模块-开发过程中-已提取
   */
  public static final String OC_PICK_UP = "1";

  /**
   * 操作模块-开发过程中-未提交
   */
  public static final String OC_PICK_UP_OVER = "2";

  /*
   * 汇缴-缴存方式-划款
   */

  public static final int PAY_WAY_HUAKUAN = 1;

  /*
   * 汇缴-缴存方式-支票
   */
  public static final int PAY_WAY_CHECK = 2;

  /*
   * 汇缴-缴存方式-现金
   */
  public static final int PAY_WAY_CASH = 3;

  /*
   * 汇缴-缴存方式
   */
  public static final String PAY_WAY_INFO = "org.xpup.hafmis.common.util.enumerate.PayWay";

  /*
   * 汇缴-缴存类别-汇缴
   */

  public static final int PAY_KIND_PAYMENT = 1;

  /*
   * 汇缴-缴存类别-补缴
   */

  public static final int PAY_KIND_ADDPAY = 2;

  /*
   * 汇缴-缴存类别-预缴
   */
  public static final int PAY_KIND_ADVANCEPAY = 3;

  public static final String PAY_KIND_INFO = "org.xpup.hafmis.common.util.enumerate.PayKind";

  /**
   * 补缴类型
   */
  public static final String ADDPAYTYPE = "org.xpup.hafmis.common.util.enumerate.AddPayType";

  public static final String ADDPAYTYPE_A = "1";

  public static final String ADDPAYTYPE_B = "2";

  public static final String ADDPAYTYPE_C = "3";

  /**
   * 提前还款类型
   */
  public static final String AHEADTYPE = "org.xpup.hafmis.common.util.enumerate.AheadType";

  /**
   * 延长
   */
  public static final String AHEADTYPE_1 = "1";

  /**
   * 缩短
   */
  public static final String AHEADTYPE_2 = "2";

  /**
   * 不改变期限
   */
  public static final String AHEADTYPE_3 = "3";

  /**
   * 市本级办事处代码
   */
  public static final String OFFICECODE_SBJ = "4028810c120af23c01120b14ed840005";

  /* 辅助借款人关系 */
  public static final String RELATION = "org.xpup.hafmis.common.util.enumerate.Relation";

  /**
   * 辅助借款人关系-配偶
   */
  public static final String RELATION_CONSORT = "1";

  /**
   * 辅助借款人关系-父母
   */
  public static final String RELATION_PARENT = "2";

  /**
   * 辅助借款人关系-子女
   */
  public static final String RELATION_CHILD = "3";
  /**
   * 辅助借款人关系-朋友
   */
  // public static final String RELATION_FRIEND = "4";
}