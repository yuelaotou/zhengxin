package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 杨光
 */
public class Credit implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 主键ID */
  private Integer id;

  /** 业务号（合同号） */
  private String yewuhao;

  /** 办事处 */
  private String office;

  /** 放款银行 */
  private BigDecimal loan_bank_id;

  /** 开户日期 */
  private String kaihuriqi;

  /** 到期日期 */
  private String daoqiriqi;

  /** 贷款金额 */
  private BigDecimal daikuanjine;

  /** 贷款期限 */
  private String daikuanqixian;

  /** 还款月数 */
  private String huankuanyueshu;

  /** 剩余期限 */
  private String shengyuqixian;

  /** 应还款日期 */
  private String yinghuankuanriqi;

  /** 实际还款日期 */
  private String shijihuankuanriqi;

  /** 应还款金额 */
  private BigDecimal yinghuankuanjine;

  /** 实际还款金额 */
  private BigDecimal shijihuankuanjine;

  /** 贷款余额 */
  private BigDecimal daikuanyue;

  /** 当前逾期期数 */
  private BigDecimal dangqianyuqiqishu;

  /** 当前逾期总额 */
  private BigDecimal dangqianyuqizonge;

  /** 逾期31-60天未归还贷款本金 */
  private BigDecimal yuqiyigeyue;

  /** 逾期61-90天未归还贷款本金 */
  private BigDecimal yuqilianggeyue;

  /** 逾期91-180天未归还贷款本金 */
  private BigDecimal yuqisangeyue;

  /** 逾期180天以上未归还贷款本金 */
  private BigDecimal yuqiliugeyue;

  /** 违约次数 */
  private BigDecimal weiyuecishu;

  /** 最高逾期期数 */
  private BigDecimal zuigaoyuqiqishu;

  /** 账户状态 */
  private BigDecimal zhanghuzhuangtai;

  /** 24个月（账户）还款状态 */
  private String ershisigeyue;

  /** 账户拥有者信息提示 */
  private BigDecimal zhanghuxinxitishi;

  /** 姓名 */
  private String xingming;

  /** 证件类型 */
  private String zhengjianleixing;

  /** 证件号码 */
  private String zhengjianhaoma;

  /** 数据提取日期 */
  private String shujutiquriqi;

  /** 报文生成日期 */
  private String baowenshengchengriqi;

  /** 记录状态 */
  private BigDecimal jiluzhuangtai;

  /** 是否导出过，0是，1否。主要为了判断是否生成身份三段 */
  private BigDecimal isexport;

  public String getBaowenshengchengriqi() {
    return baowenshengchengriqi;
  }

  public void setBaowenshengchengriqi(String baowenshengchengriqi) {
    this.baowenshengchengriqi = baowenshengchengriqi;
  }

  public BigDecimal getDaikuanjine() {
    return daikuanjine;
  }

  public void setDaikuanjine(BigDecimal daikuanjine) {
    this.daikuanjine = daikuanjine;
  }

  public BigDecimal getDaikuanyue() {
    return daikuanyue;
  }

  public void setDaikuanyue(BigDecimal daikuanyue) {
    this.daikuanyue = daikuanyue;
  }

  public BigDecimal getDangqianyuqiqishu() {
    return dangqianyuqiqishu;
  }

  public void setDangqianyuqiqishu(BigDecimal dangqianyuqiqishu) {
    this.dangqianyuqiqishu = dangqianyuqiqishu;
  }

  public BigDecimal getDangqianyuqizonge() {
    return dangqianyuqizonge;
  }

  public void setDangqianyuqizonge(BigDecimal dangqianyuqizonge) {
    this.dangqianyuqizonge = dangqianyuqizonge;
  }

  public String getDaoqiriqi() {
    return daoqiriqi;
  }

  public void setDaoqiriqi(String daoqiriqi) {
    this.daoqiriqi = daoqiriqi;
  }

  public String getErshisigeyue() {
    return ershisigeyue;
  }

  public void setErshisigeyue(String ershisigeyue) {
    this.ershisigeyue = ershisigeyue;
  }

  public String getHuankuanyueshu() {
    return huankuanyueshu;
  }

  public void setHuankuanyueshu(String huankuanyueshu) {
    this.huankuanyueshu = huankuanyueshu;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getJiluzhuangtai() {
    return jiluzhuangtai;
  }

  public void setJiluzhuangtai(BigDecimal jiluzhuangtai) {
    this.jiluzhuangtai = jiluzhuangtai;
  }

  public String getKaihuriqi() {
    return kaihuriqi;
  }

  public void setKaihuriqi(String kaihuriqi) {
    this.kaihuriqi = kaihuriqi;
  }

  public String getShengyuqixian() {
    return shengyuqixian;
  }

  public void setShengyuqixian(String shengyuqixian) {
    this.shengyuqixian = shengyuqixian;
  }

  public BigDecimal getShijihuankuanjine() {
    return shijihuankuanjine;
  }

  public void setShijihuankuanjine(BigDecimal shijihuankuanjine) {
    this.shijihuankuanjine = shijihuankuanjine;
  }

  public String getShijihuankuanriqi() {
    return shijihuankuanriqi;
  }

  public void setShijihuankuanriqi(String shijihuankuanriqi) {
    this.shijihuankuanriqi = shijihuankuanriqi;
  }

  public String getShujutiquriqi() {
    return shujutiquriqi;
  }

  public void setShujutiquriqi(String shujutiquriqi) {
    this.shujutiquriqi = shujutiquriqi;
  }

  public BigDecimal getWeiyuecishu() {
    return weiyuecishu;
  }

  public void setWeiyuecishu(BigDecimal weiyuecishu) {
    this.weiyuecishu = weiyuecishu;
  }

  public String getXingming() {
    return xingming;
  }

  public void setXingming(String xingming) {
    this.xingming = xingming;
  }

  public String getYewuhao() {
    return yewuhao;
  }

  public void setYewuhao(String yewuhao) {
    this.yewuhao = yewuhao;
  }

  public BigDecimal getYinghuankuanjine() {
    return yinghuankuanjine;
  }

  public void setYinghuankuanjine(BigDecimal yinghuankuanjine) {
    this.yinghuankuanjine = yinghuankuanjine;
  }

  public String getYinghuankuanriqi() {
    return yinghuankuanriqi;
  }

  public void setYinghuankuanriqi(String yinghuankuanriqi) {
    this.yinghuankuanriqi = yinghuankuanriqi;
  }

  public BigDecimal getYuqilianggeyue() {
    return yuqilianggeyue;
  }

  public void setYuqilianggeyue(BigDecimal yuqilianggeyue) {
    this.yuqilianggeyue = yuqilianggeyue;
  }

  public BigDecimal getYuqiliugeyue() {
    return yuqiliugeyue;
  }

  public void setYuqiliugeyue(BigDecimal yuqiliugeyue) {
    this.yuqiliugeyue = yuqiliugeyue;
  }

  public BigDecimal getYuqisangeyue() {
    return yuqisangeyue;
  }

  public void setYuqisangeyue(BigDecimal yuqisangeyue) {
    this.yuqisangeyue = yuqisangeyue;
  }

  public BigDecimal getYuqiyigeyue() {
    return yuqiyigeyue;
  }

  public void setYuqiyigeyue(BigDecimal yuqiyigeyue) {
    this.yuqiyigeyue = yuqiyigeyue;
  }

  public BigDecimal getZhanghuxinxitishi() {
    return zhanghuxinxitishi;
  }

  public void setZhanghuxinxitishi(BigDecimal zhanghuxinxitishi) {
    this.zhanghuxinxitishi = zhanghuxinxitishi;
  }

  public BigDecimal getZhanghuzhuangtai() {
    return zhanghuzhuangtai;
  }

  public void setZhanghuzhuangtai(BigDecimal zhanghuzhuangtai) {
    this.zhanghuzhuangtai = zhanghuzhuangtai;
  }

  public String getZhengjianhaoma() {
    return zhengjianhaoma;
  }

  public void setZhengjianhaoma(String zhengjianhaoma) {
    this.zhengjianhaoma = zhengjianhaoma;
  }

  public String getZhengjianleixing() {
    return zhengjianleixing;
  }

  public void setZhengjianleixing(String zhengjianleixing) {
    this.zhengjianleixing = zhengjianleixing;
  }

  public BigDecimal getZuigaoyuqiqishu() {
    return zuigaoyuqiqishu;
  }

  public void setZuigaoyuqiqishu(BigDecimal zuigaoyuqiqishu) {
    this.zuigaoyuqiqishu = zuigaoyuqiqishu;
  }

  public BigDecimal getIsexport() {
    return isexport;
  }

  public void setIsexport(BigDecimal isexport) {
    this.isexport = isexport;
  }

  public String getDaikuanqixian() {
    return daikuanqixian;
  }

  public void setDaikuanqixian(String daikuanqixian) {
    this.daikuanqixian = daikuanqixian;
  }

  public BigDecimal getLoan_bank_id() {
    return loan_bank_id;
  }

  public void setLoan_bank_id(BigDecimal loan_bank_id) {
    this.loan_bank_id = loan_bank_id;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

}
