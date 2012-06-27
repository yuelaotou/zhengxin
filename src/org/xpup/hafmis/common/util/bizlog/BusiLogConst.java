
package org.xpup.hafmis.common.util.bizlog;
/**
 * 业务日志常量类
 * @author 刘洋
 *2007-6-2
 */
public class BusiLogConst {

  public BusiLogConst() {

  }

  /*操作业务系统类型*/
  public static final String OP_SYSTEM_TYPE = "org.xpup.hafmis.common.util.bizlog.OpSystemType";
  
  /**
   * 操作业务系统类型—住房公积金系统管理系统
   */
  public static final int OP_SYSTEM_TYPE_MANAGER=0;
  /**
   * 操作业务系统类型—住房公积金归集系统
   */
  public static final int OP_SYSTEM_TYPE_COLLECTION =1;
  /**
   * 操作业务系统类型—住房公积金贷款系统
   */
  public static final int OP_SYSTEM_TYPE_LOAN =2;
  /**
   * 操作业务系统类型—住房公积金财务系统
   */
  public static final int OP_SYSTEM_TYPE_FINANCE =3;
  
  /*操作模块-开户销户*/
  public static final String OP_MODE_OPEN = "org.xpup.hafmis.common.util.bizlog.OpModeOpen";
  /**
   * 操作模块—单位开户-办理开户
   */
  public static final int OP_MODE_OPEN_ORGOPEN_DO=11;
  /**
   * 操作模块—单位开户-开户维护
   */
  public static final int OP_MODE_OPEN_ORGOPEN_MAINTAIN=12;
  /**
   * 操作模块—职工开户
   */
  public static final int OP_MODE_OPEN_EMPOPEN=13;
  /**
   * 操作模块—单位变更-办理变更
   */
  public static final int OP_MODE_OPEN_ORGCHG_DO=14;
  /**
   * 操作模块—单位变更-变更维护
   */
  public static final int OP_MODE_OPEN_ORGCHG_MAINTAIN=15;
  
  /*操作模块-变更业务*/
  public static final String OP_MODE_CHANGE = "org.xpup.hafmis.common.util.bizlog.OpModeChange";
  /**
   * 操作模块—变更业务-汇缴比例调整-办理变更
   */
  public static final int OP_MODE_CHANGE_CHGRATE_DO=21;
  /**
   * 操作模块—变更业务-汇缴比例调整-变更维护
   */
  public static final int OP_MODE_CHANGE_CHGRATE_MAINTAIN=22;
  /**
   * 操作模块—变更业务-工资基数调整-办理变更
   */
  public static final int OP_MODE_CHANGE_CHGSALARYBASE_DO=23;
  /**
   * 操作模块—变更业务-工资基数调整-变更维护
   */
  public static final int OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN=24;
  /**
   * 操作模块—变更业务-缴额调整-办理变更
   */
  public static final int OP_MODE_CHANGE_CHGPAYMENT_DO=25;
  /**
   * 操作模块—变更业务-缴额调整-变更维护
   */
  public static final int OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN=26;
  /**
   * 操作模块—变更业务-人员变更-办理变更
   */
  public static final int OP_MODE_CHANGE_CHGPERSON_DO=27;
  /**
   * 操作模块—变更业务-人员变更-变更维护
   */
  public static final int OP_MODE_CHANGE_CHGPERSON_MAINTAIN=28;
  
  /*操作模块-缴存管理*/
  public static final String OP_MODE_PAYMENTMANAGE = "org.xpup.hafmis.common.util.bizlog.OpModePaymentManage";
  /**
   * 操作模块—缴存管理-正常汇缴-办理缴存
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENT_DO=31;
  /**
   * 操作模块—缴存管理-正常汇缴-缴存维护
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENT_MAINTAIN=32;
  /**
   * 操作模块—缴存管理-单位补缴-办理缴存
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPAY_DO=33;
  /**
   * 操作模块—缴存管理-单位补缴-缴存维护
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN=34;
  /**
   * 操作模块—缴存管理-个人补缴-办理缴存
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO=35;
  /**
   * 操作模块—缴存管理-个人补缴-缴存维护
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN=36;
  /**
   * 操作模块—缴存管理-单位挂账-办理挂账
   */
  public static final int OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_DO=37;
  /**
   * 操作模块—缴存管理-单位挂账-挂账维护
   */
  public static final int OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN=38;
  /**
   * 操作模块—缴存管理-缴存到账确认
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM=39;
  /**
   * 操作模块—缴存管理-财政代扣
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT=310;
  
  /*操作模块-提取管理*/
  public static final String OP_MODE_DRAWING = "org.xpup.hafmis.common.util.bizlog.OpModeDrawing";
  /**
   * 操作模块-提取管理-办理提取-办理提取
   */
  public static final int OP_MODE_DRAWING_DRAWING_DO=41;
  /**
   * 操作模块—提取管理-办理提取-提取维护
   */
  public static final int OP_MODE_DRAWING_DRAWING_MAINTAIN=42;
  /**
   * 操作模块-提取管理-办理特殊提取-办理提取
   */
  public static final int OP_MODE_DRAWING_SPECIALDRAWING_DO=43;
  /**
   * 操作模块—提取管理-办理特殊提取-提取维护
   */
  public static final int OP_MODE_DRAWING_SPECIALDRAWING_MAINTAIN=44;
  /**
   * 操作模块—公积金还贷
   */
  public static final int OP_MODE_DRAWING_COLLLOANBACK=45;
  
  /*操作模块-转入转出*/
  public static final String OP_MODE_TRANINOUT = "org.xpup.hafmis.common.util.bizlog.OpModeTranInOut";
  /**
   * 操作模块-转入转出-办理转出-办理转出
   */
  public static final int OP_MODE_TRANINOUT_TRANOUT_DO=51;
  /**
   * 操作模块—转入转出-办理转出-转出维护
   */
  public static final int OP_MODE_TRANINOUT_TRANOUT_MAINTAIN=52;
  /**
   * 操作模块-转入转出-办理转入-待转入登记
   */
  public static final int OP_MODE_TRANINOUT_TRANIN_PREPARE=53;
  /**
   * 操作模块-转入转出-办理转入-转入登记
   */
  public static final int OP_MODE_TRANINOUT_TRANIN_CHECKIN=54;
  /**
   * 操作模块—转入转出-办理转入-转入维护
   */
  public static final int OP_MODE_TRANINOUT_TRANIN_MAINTAIN=55;
  
  /*操作模块-账务处理*/
  public static final String OP_MODE_ACCOUNTMANAGE = "org.xpup.hafmis.common.util.bizlog.OpModeAccountManage";
  /**
   * 操作模块—账务处理-业务复核
   */
  public static final int OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK =61;
  /**
   * 操作模块—账务处理-错账调整-办理错账调整
   */
  public static final int OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO=62;
  /**
   * 操作模块—账务处理-错账调整-错账调整维护
   */
  public static final int OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN=63;
  /**
   * 操作模块—账务处理-扎账-扎账
   */
  public static final int OP_MODE_ACCOUNTMANAGE_ACCOUNTING_DO=64;
  /**
   * 操作模块—账务处理-扎账-结算单查询
   */
  public static final int OP_MODE_ACCOUNTMANAGE_ACCOUNTING_INQUIRIES=65;
  /**
   * 操作模块—账务处理-日结-扎账
   */
  public static final int OP_MODE_ACCOUNTMANAGE_DAYCLEANING_DO=66;
  /**
   * 操作模块—账务处理-日结-结算单查询
   */
  public static final int OP_MODE_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES=67;
  /**
   * 操作模块—账务处理-月结-扎账
   */
  public static final int OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_DO=68;
  /**
   * 操作模块—账务处理-月结-结算单查询
   */
  public static final int OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_INQUIRIES=69;
  /**
   * 操作模块—账务处理-年终结息
   */
  public static final int OP_MODE_ACCOUNTMANAGE_INTEREST=610;
  /**
   * 操作模块—账务处理-维护利率
   */
  public static final int OP_MODE_ACCOUNTMANAGE_MAINTAINRATE=611;
  
  /**
   * 操作模块—人民银行数据导出-导出报文参数设置
   */
  public static final int OP_MODE_BANKDATAEXP_RECORDPARAMSETTING=71;
  
  /**
   * 操作模块—人民银行数据导出-导出报文头设置
   */
  public static final int OP_MODE_BANKDATAEXP_RECORDHEADSETTING=72;
  
  /**
   * 操作模块—人民银行数据导出-导出报文
   */
  public static final int OP_MODE_BANKDATAEXP_RECORD=73;
  
  /*归集全业务模块*/
  public static final String OPMODECOLL = "org.xpup.hafmis.common.util.bizlog.OpModeColl";
  
  /*个贷全业务模块*/
  public static final String OPMODELOAN = "org.xpup.hafmis.common.util.bizlog.OpModeLoan";
  
  /*财务全业务模块*/
  public static final String OPMODEFINANCE = "org.xpup.hafmis.common.util.bizlog.OpModeFinance";
  
  /*业务日志表的操作行为*/
  public static final String BIZLOG_ACTION = "org.xpup.hafmis.common.util.bizlog.BizlogAction";
  /**
   * 业务日志表的操作行为—添加
   */
  public static final int BIZLOG_ACTION_ADD =1;
  /**
   * 业务日志表的操作行为—修改
   */
  public static final int BIZLOG_ACTION_MODIFY =2;
  /**
   * 业务日志表的操作行为—删除
   */
  public static final int BIZLOG_ACTION_DELETE =3;
  /**
   * 业务日志表的操作行为—查询
   */
  public static final int BIZLOG_ACTION_QUERY =4;
  /**
   * 业务日志表的操作行为—导出
   */
  public static final int BIZLOG_ACTION_EXP =5;
  /**
   * 业务日志表的操作行为—导入
   */
  public static final int BIZBLOG_ACTION_IMP =6;
  /**
   * 业务日志表的操作行为—启用
   */
  public static final int BIZLOG_ACTION_OPENING =7;
  /**
   * 业务日志表的操作行为—撤销
   */
  public static final int BIZLOG_ACTION_REVOCATION =8;
  /**
   * 业务日志表的操作行为—全部删除
   */
  public static final int BIZLOG_ACTION_DELETEALL =9;
  /**
   * 业务日志表的操作行为—复核
   */
  public static final int BIZLOG_ACTION_CHECKS =10;
  /**
   * 业务日志表的操作行为—确认
   */
  public static final int BIZLOG_ACTION_CONFIRM =11;
  /**
   * 业务日志表的操作行为—结息
   */
  public static final int BIZLOG_ACTION_INTEREST =12;
  /**
   * 业务日志表的操作行为—扎账
   */
  public static final int BIZLOG_ACTION_ACCOUNTIN =13;
  /**
   * 业务日志表的操作行为—完成调整
   */
  public static final int BIZLOG_ACTION_ENDCHG =14;
  /**
   * 业务日志表的操作行为—审核通过
   */
  public static final int BIZLOG_ACTION_AUDITINGPASS =15;
  /**
   * 业务日志表的操作行为—审核不通过
   */
  public static final int BIZLOG_ACTION_AUDITING =16;
  /**
   * 业务日志表的操作行为—撤消审核
   */
  public static final int BIZLOG_ACTION_AUDITINGQUASH =17;
  /**
   * 业务日志表的操作行为—审批通过
   */
  public static final int BIZLOG_ACTION_PPROVALPASS =18;
  /**
   * 业务日志表的操作行为—审批不通过
   */
  public static final int BIZLOG_ACTION_PPROVAL =19;
  /**
   * 业务日志表的操作行为—撤消审批
   */
  public static final int BIZLOG_ACTION_PPROVALQUASH =20;
  /**
   * 业务日志表的操作行为—年终结转
   */
  public static final int BIZLOG_ACTION_CARRYFORWARD =21;
  /**
   * 业务日志表的操作行为—提交数据
   */
  public static final int BIZLOG_ACTION_REFERRINGDATE =22;
  /**
   * 业务日志表的操作行为—撤销提交数据
   */
  public static final int BIZLOG_ACTION_PPROVALDATA =23;
  /**
   * 业务日志表的操作行为—提取数据
   */
  public static final int BIZLOG_ACTION_PICKUPDATA =24;
  /**
   * 业务日志表的操作行为—结转余额
   */
  public static final int BIZLOG_ACTION_CARRYFORWORDBALANCE =25;

  /*操作模块-个贷-数据准备*/
  public static final String PL_OP_DATAPREPARATION = "org.xpup.hafmis.common.util.bizlog.PlOpDataPreparation";
  /**
   * 操作模块-个贷-数据准备-利率维护
   */
  public static final int PL_OP_DATAPREPARATION_RATE=11;
  /**
   * 操作模块-个贷-数据准备-银行维护
   */
  public static final int PL_OP_DATAPREPARATION_BANK=12;
  /**
   * 操作模块-个贷-数据准备-参数维护
   */
  public static final int PL_OP_DATAPREPARATION_PARAMETERS=13;
  /**
   * 操作模块-个贷-数据准备-提前还款参数维护
   */
  public static final int PL_OP_DATAPREPARATION_PREPAYMENTPARAM=14;
  /**
   * 操作模块-个贷-数据准备-开发商维护
   */
  public static final int PL_OP_DATAPREPARATION_DEVELOPERS=15;
  /**
   * 操作模块-个贷-数据准备-担保公司维护
   */
  public static final int PL_OP_DATAPREPARATION_GUARANTEECORP=16;
  /**
   * 操作模块-个贷-数据准备-代理机构维护
   */
  public static final int PL_OP_DATAPREPARATION_AGENCIES=17;
  /**
   * 操作模块-个贷-数据准备-保险公司维护
   */
  public static final int PL_OP_DATAPREPARATION_INSURANCECOMP=18;
  /**
   * 操作模块-个贷-数据准备-评估机构维护
   */
  public static final int PL_OP_DATAPREPARATION_ASSESSMENTAGEN=19;
  /**
   * 操作模块-个贷-数据准备-公证处维护
   */
  public static final int PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE=110;
  /**
   * 操作模块-个贷-数据准备-开发商维护-楼盘
   */
  public static final int PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_FLOOR=111;
  /**
   * 操作模块-个贷-数据准备-开发商维护-楼栋
   */
  public static final int PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_BUILD=115;
  /**
   * 操作模块-个贷-数据准备-公积金还贷参数设置
   */
  public static final int PL_OP_DATAPREPARATION_COLLLOANBACKPARA=112;
  /**
   * 操作模块-个贷-数据准备-银行回文格式设置
   */
  public static final int PL_OP_DATAPREPARATION_BANKPALINDROME=113;
  /**
   * 操作模块-个贷-数据准备-银行回文对应格式设置
   */
  public static final int PL_OP_DATAPREPARATION_PALINDROMFORMAT=114;

  /*操作模块-个贷-申请贷款*/
  public static final String PL_OP_LOANAPPL = "org.xpup.hafmis.common.util.bizlog.PlOpLoanAppl";
  /**
   * 操作模块-个贷-申请贷款-申请贷款-借款人信息
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO=21;
  /**
   * 操作模块-个贷-申请贷款-申请贷款-辅助借款人信息
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO=22;
  /**
   * 操作模块-个贷-申请贷款-申请贷款-购房信息
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_HOUSEINFO=23;
  /**
   * 操作模块-个贷-申请贷款-申请贷款-借款人额度信息
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_BORROWERINFOLIMITED=24;
  /**
   * 操作模块-个贷-申请贷款-申请贷款-申请贷款维护
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_LOANAPPLMAINTAIN=25;
  /**
   * 操作模块-个贷-申请贷款-特殊申请-办理特殊申请
   */
  public static final int PL_OP_LOANAPPL_SPECIALAPPL_DO=26;
  /**
   * 操作模块-个贷-申请贷款-特殊申请-特殊申请维护
   */
  public static final int PL_OP_LOANAPPL_SPECIALAPPL_MAINTAIN=27;
  /**
   * 操作模块-个贷-申请贷款-审核贷款
   */
  public static final int PL_OP_LOANAPPL_LOANAUDIT=28;
  /**
   * 操作模块-个贷-申请贷款-审批贷款
   */
  public static final int PL_OP_LOANAPPL_LOANAPPROVAL=29;
  /**
   * 操作模块-个贷-申请贷款-签订合同-借款合同信息
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_LOANCONTRACT=210;
  /**
   * 操作模块-个贷-申请贷款-签订合同-抵押合同信息
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT=211;
  /**
   * 操作模块-个贷-申请贷款-签订合同-质押合同信息
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_IMPAWNCONTRACT=212;
  /**
   * 操作模块-个贷-申请贷款-签订合同-保证人信息
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER=213;
  /**
   * 操作模块-个贷-申请贷款-签订合同-签订合同维护
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN=214;
  /**
   * 操作模块-个贷-申请贷款-收款账号修改-收款账号修改
   */
  public static final int PL_OP_LOANAPPL_GATHERINGACC_DO=215;
  /**
   * 操作模块-个贷-申请贷款-收款账号修改-收款账号维护
   */
  public static final int PL_OP_LOANAPPL_GATHERINGACC_MAINTAIN=216;
  /**
   * 操作模块-个贷-申请贷款-划款账号修改-划款账号修改
   */
  public static final int PL_OP_LOANAPPL_GIVEACC_DO=217;
  /**
   * 操作模块-个贷-申请贷款-划款账号修改-划款账号维护
   */
  public static final int PL_OP_LOANAPPL_GIVEACC_MAINTAIN=218;
  /**
   * 操作模块-个贷-申请贷款-下达发放通知书-下达发放通知书
   */
  public static final int PL_OP_LOANAPPL_ISSUEDNOTICE_DO=219;
  /**
   * 操作模块-个贷-申请贷款-下达发放通知书-发放通知书维护
   */
  public static final int PL_OP_LOANAPPL_ISSUEDNOTICE_MAINTAIN=220;
  /**
   * 操作模块-个贷-申请贷款-删除贷款合同
   */
  public static final int PL_OP_LOANAPPL_DELCONTRACT=221;
  /**
   * 操作模块-个贷-申请贷款-公积金还贷签订合同
   */
  public static final int PL_OP_LOANRETURN_MAINTAIN=222;

  /*操作模块-个贷-发放贷款*/
  public static final String PL_OP_LOANISSUED = "org.xpup.hafmis.common.util.bizlog.PlOpLoanIssued";
  /**
   * 操作模块-个贷-发放贷款-发放贷款-办理发放
   */
  public static final int PL_OP_LOANISSUED_DO=31;
  /**
   * 操作模块-个贷-发放贷款-发放贷款-发放维护
   */
  public static final int PL_OP_LOANISSUED_MAINTAIN=32;
  /**
   * 操作模块-个贷-发放贷款-打印还款计划表
   */
  public static final int PL_OP_LOANISSUED_PRINTRESTORELOAN=33;

  /*操作模块-个贷-回收贷款*/
  public static final String PL_OP_LOANRECOVER = "org.xpup.hafmis.common.util.bizlog.PlOpLoanRecover";
  /**
   * 操作模块-个贷-回收贷款-回收咨询
   */
  public static final int PL_OP_LOANRECOVER_RECOVERQUIRY=41;
  /**
   * 操作模块-个贷-回收贷款-回收贷款-办理回收
   */
  public static final int PL_OP_LOANRECOVER_DO=42;
  /**
   * 操作模块-个贷-回收贷款-回收贷款-回收维护
   */
  public static final int PL_OP_LOANRECOVER_MAINTAIN=43;
  /**
   * 操作模块-个贷-回收贷款-银行代扣导出
   */
  public static final int PL_OP_LOANRECOVER_LOANKOUEXP=44;
  /**
   * 操作模块-个贷-回收贷款-银行代扣导入
   */
  public static final int PL_OP_LOANRECOVER_LOANKOUIMP=45;
  /**
   * 操作模块-个贷-回收贷款-呆账核销-办理呆账核销
   */
  public static final int PL_OP_LOANRECOVER_BADDEBTSOFF_DO=46;
  /**
   * 操作模块-个贷-回收贷款-呆账核销-呆账核销维护
   */
  public static final int PL_OP_LOANRECOVER_BADDEBTSOFF_MAINTAIN=47;
  /**
   * 操作模块-个贷-回收贷款-核销收回-办理核销收回
   */
  public static final int PL_OP_LOANRECOVER_CANRECOVER_DO=48;
  /**
   * 操作模块-个贷-回收贷款-核销收回-核销收回维护
   */
  public static final int PL_OP_LOANRECOVER_CANRECOVER_MAINTAIN=49;
  /**
   * 操作模块-个贷-回收贷款-抵押质押解除-办理抵押质押解除
   */
  public static final int PL_OP_LOANRECOVER_LIVING_DO=410;
  /**
   * 操作模块-个贷-回收贷款-抵押质押解除-抵押质押解除维护
   */
  public static final int PL_OP_LOANRECOVER_LIVING_MAINTAIN=411;
  /**
   * 操作模块-个贷-回收贷款-贷款户注销-贷款户注销办理
   */
  public static final int PL_OP_LOANRECOVER_LOANERLOGOUT_DO=412;
  /**
   * 操作模块-个贷-回收贷款-贷款户注销-贷款户注销维护
   */
  public static final int PL_OP_LOANRECOVER_LOANERLOGOUT_MAINTAIN=413;

  /*操作模块-个贷-账务处理*/
  public static final String PL_OP_ACCOUNTMANAGE = "org.xpup.hafmis.common.util.bizlog.PlOpAccountMange";
  /**
   * 操作模块-个贷-账务处理-业务复核
   */
  public static final int PL_OP_ACCOUNTMANAGE_OPERATIONCHECK=51;
  /**
   * 操作模块—个贷-账务处理-扎账-扎账
   */
  public static final int PL_OP_ACCOUNTMANAGE_ACCOUNTING_DO=52;
  /**
   * 操作模块—个贷-账务处理-扎账-结算单查询
   */
  public static final int PL_OP_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES=53;
  /**
   * 操作模块—个贷—账务处理-错账冲正-办理错账调整
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO=54;
  /**
   * 操作模块—个贷—账务处理-错账冲正-错账调整维护
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN=55;
  /**
   * 操作模块—个贷—账务处理-挂账-办理挂账
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_DO=56;
  /**
   *操作模块—个贷—账务处理-挂账-挂账维护
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_MAINTAIN=57;
  /**
   * 操作模块-个贷-账务处理-逾期结转
   */
  public static final int PL_OP_ACCOUNTMANAGE_LATECARRYOVER=58;
  /**
   * 操作模块-个贷-账务处理-日结
   */
  public static final int PL_OP_ACCOUNTMANAGE_DAYCLEANING=59;
  /**
   * 操作模块-个贷-账务处理-月末结转
   */
  public static final int PL_OP_ACCOUNTMANAGE_MONTHLATECARRYOVER=510;
  /**
   * 操作模块-个贷-账务处理-年终结转
   */
  public static final int PL_OP_ACCOUNTMANAGE_CARRYFORWARD=511;

  /*操作模块-个贷-合同变更*/
  public static final String PL_OP_CONTRACTCHG = "org.xpup.hafmis.common.util.bizlog.PlOpContractChg";
  /**
   * 操作模块-个贷-合同变更-基本信息变更-借款人信息
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO=61;
  /**
   * 操作模块-个贷-合同变更-基本信息变更-辅助借款人信息
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO=62;
  /**
   * 操作模块-个贷-合同变更-基本信息变更-购房信息
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO=63;
  /**
   * 操作模块-个贷-合同变更-基本信息变更-基本信息变更维护
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_MAINTAIN=64;
  /**
   * 操作模块-个贷-合同变更-担保抵押变更-抵押合同信息
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT=65;
  /**
   * 操作模块-个贷-合同变更-担保抵押变更-质押合同信息
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT=66;
  /**
   * 操作模块-个贷-合同变更-担保抵押变更-保证人信息
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER=67;
  /**
   * 操作模块-个贷-合同变更-担保抵押变更-担保抵押变更维护
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_CONTRACTMAINTAIN=68;
  /**
   * 操作模块-个贷-合同变更-特殊信息变更
   */
  public static final int PL_OP_CONTRACTCHG_SPECIALINFOCHG=69;

  /*操作模块-个贷-特殊业务处理*/
  public static final String PL_OP_SPECIALBUSS = "org.xpup.hafmis.common.util.bizlog.PlOpSpecialBuss";
  /**
   * 操作模块-个贷-特殊业务处理-五级分类修改-业务办理
   */
  public static final int PL_OP_SPECIALBUSS_FIVELEVAL_DO=71;
  /**
   * 操作模块-个贷-特殊业务处理-五级分类修改-业务维护
   */
  public static final int PL_OP_SPECIALBUSS_FIVELEVAL_MAINTAIN=72;
  /**
   * 操作模块-个贷-特殊业务处理-保证金登记-业务办理
   */
  public static final int PL_OP_SPECIALBUSS_BONDREGIST_DO=73;
  /**
   * 操作模块-个贷-特殊业务处理-保证金登记-业务维护
   */
  public static final int PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN=74;
  /**
   * 操作模块-个贷-特殊业务处理-保证金结息-业务办理
   */
  public static final int PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO=75;
  /**
   * 操作模块-个贷-特殊业务处理-保证金结息-业务维护
   */
  public static final int PL_OP_SPECIALBUSS_BAILCLEARINTEREST_MAINTAIN=76;

  /*操作模块-财务-账套管理*/
  public static final String FN_OP_BOOKMNG = "org.xpup.hafmis.common.util.bizlog.FnOpBookmng";
  /**
   * 操作模块-财务-账套管理-会计科目
   */
  public static final int FN_OP_BOOKMNG_SUBJECT =11;
  /**
   * 操作模块-财务-账套管理-初始数据
   */
  public static final int FN_OP_BOOKMNG_DATAINITIALIZE =12;
  /**
   * 操作模块-财务-账套管理-启用账套
   */
  public static final int FN_OP_BOOKMNG_BOOKSTART =13;
  /**
   * 操作模块-财务-账套管理-凭证字
   */
  public static final int FN_OP_BOOKMNG_CREDENCECHAR =14;
  /**
   * 操作模块-财务-账套管理-结算方式
   */
  public static final int FN_OP_BOOKMNG_SETTLEMODLE =15;
  /**
   * 操作模块-财务-账套管理-常用摘要
   */
  public static final int FN_OP_BOOKMNG_SUMMARY =16;
  /**
   * 操作模块-财务-账套管理-科目关系设置
   */
  public static final int FN_OP_BOOKMNG_SUBJECTRELATION =17;
  /**
   * 操作模块-财务-账套管理-凭证模式设置
   */
  public static final int FN_OP_BOOKMNG_CREDENCEMODLE =18;
  /**
   * 操作模块-财务-账套管理-损益结转设置
   */
  public static final int FN_OP_BOOKMNG_SETTLEINCADDEC =19;
  /**
   * 操作模块-财务-账套管理-创建账套
   */
  public static final int FN_OP_BOOKMNG_CREATEBOOK =110;

  /*操作模块-财务-账务处理*/
  public static final String FN_OP_ACCOUNTHANDLE = "org.xpup.hafmis.common.util.bizlog.FnOpAccounthandle";
  /**
   * 操作模块-财务-账务处理-凭证录入-凭证录入
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN =211;
  /**
   * 操作模块-财务-账务处理-凭证录入-自动转账
   */
  public static final int FN_OP_ACCOUNTHANDLE_AUTOTRANSFERS =212;
  /**
   * 操作模块-财务-账务处理-凭证录入-损益结转 
   */
  public static final int FN_OP_ACCOUNTHANDLE_CARRYOVERPROFITLOSS =213;
  /**
   * 操作模块-财务-账务处理-凭证录入-凭证维护
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN =214;
  /**
   * 操作模块-财务-账务处理-凭证审核
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCECHECK =22;
  /**
   * 操作模块-财务-账务处理-凭证过账
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCECLEAR =23;

  /*操作模块-财务-出纳管理*/
  public static final String FN_OP_TREASURERMNG = "org.xpup.hafmis.common.util.bizlog.FnOpTreasurermng";
  /**
   * 操作模块-财务-出纳管理-余额初始
   */
  public static final int FN_OP_TREASURERMNG_BALANCEINITIALIZE =31;
  /**
   * 操作模块-财务-出纳管理-现金日记账-现金日记账
   */
  public static final int FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR =321;
  /**
   * 操作模块-财务-出纳管理-现金日记账-自动转账
   */
  public static final int FN_OP_TREASURERMNG_CASHDAYCLEAR_AUTOCASHDAYCLEAR =322;
  /**
   * 操作模块-财务-出纳管理-现金日记账-现金日记账维护
   */
  public static final int FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN =323;
  /**
   * 操作模块-财务-出纳管理-银行存款日记账-银行存款日记账
   */
  public static final int FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEAR =331;
  /**
   * 操作模块-财务-出纳管理-银行存款日记账-自动转账
   */
  public static final int FN_OP_TREASURERMNG_BANKDAYCLEAR_AUTOBANKDAYCLEAR =332;
  /**
   * 操作模块-财务-出纳管理-银行存款日记账-银行存款日记账维护
   */
  public static final int FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN =333;
  /**
   * 操作模块-财务-出纳管理-银行对账单-银行对账单
   */
  public static final int FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACC =341;
  /**
   * 操作模块-财务-出纳管理-银行对账单-银行对账单维护
   */
  public static final int FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN =342;
  /**
   * 操作模块-财务-出纳管理-银行存款对账单
   */
  public static final int FN_OP_TREASURERMNG_DEPOSITCHECKACC =35;
  /**
   * 操作模块-财务-出纳管理-出纳扎账
   */
  public static final int FN_OP_TREASURERMNG_CLEARACCOUNT =36;

  /*操作模块-财务-账簿管理*/
  public static final String FN_OP_ACCMNG = "org.xpup.hafmis.common.util.bizlog.FnOpAccmng";
  /**
   * 操作模块-财务-账簿管理-总账
   */
  public static final int FN_OP_ACCMNG_TOTLEACC =41;
  /**
   * 操作模块-财务-账簿管理-明细账
   */
  public static final int FN_OP_ACCMNG_LISTACC =42;
  /**
   * 操作模块-财务-账簿管理-序时账
   */
  public static final int FN_OP_ACCMNG_SEQUENCEACC =43;
  /**
   * 操作模块-财务-账簿管理-凭证汇总表
   */
  public static final int FN_OP_ACCMNG_SUBJECTMUSTER =44;
  /**
   * 操作模块-财务-账簿管理-科目日报表
   */
  public static final int FN_OP_ACCMNG_SUBJECTDAYREPORT =45;
  /**
   * 操作模块-财务-账簿管理-现金日记账
   */
  public static final int FN_OP_ACCMNG_CASHDAYCLEARREPORT =46;
  /**
   * 操作模块-财务-账簿管理-银行存款日记账
   */
  public static final int FN_OP_ACCMNG_BANKDAYCLEARREPORT =47;
  /**
   * 操作模块-财务-账簿管理-科目余额表
   */
  public static final int FN_OP_ACCMNG_SUBJECTBALANCE =48;

  /*操作模块-财务-报表管理*/
  public static final String FN_OP_REPORTMNG = "org.xpup.hafmis.common.util.bizlog.FnOpReportmng";
  /**
   * 操作模块-财务-报表管理-财务报表-创建报表
   */
  public static final int FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT =51;
}