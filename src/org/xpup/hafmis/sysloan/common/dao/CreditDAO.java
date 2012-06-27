package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.Credit;

public class CreditDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public Credit queryById(Serializable id) {
    Validate.notNull(id);
    return (Credit) getHibernateTemplate().get(Credit.class, id);
  }

  /**
   * 插入记录
   * 
   * @param Credit
   * @return
   */
  public Serializable insert(Credit credit) {
    Serializable id = null;
    try {
      Validate.notNull(credit);
      id = getHibernateTemplate().save(credit);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  public List getCreditList(final String shujutiquriqi,
      final String baowenshengchengriqi, final String officeCode,
      final String loanBankName, final String yewuhao,
      final String jiluzhuangtai, final int start, final String orderBy,
      final String order, final int pageSize, final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select credit from Credit credit";
          Vector parameters = new Vector();
          String criterion = "";

          if (shujutiquriqi != null) {
            criterion += "  credit.shujutiquriqi=? and ";
            parameters.add(shujutiquriqi);
          }
          if (baowenshengchengriqi != null) {
            criterion += "  credit.baowenshengchengriqi=? and ";
            parameters.add(baowenshengchengriqi);
          }
          if (officeCode != null) {
            criterion += "  credit.office=? and ";
            parameters.add(officeCode);
          }
          if (loanBankName != null) {
            criterion += "  credit.loan_bank_id=? and ";
            parameters.add(new BigDecimal(loanBankName));
          }
          if (yewuhao != null) {
            criterion += "  credit.yewuhao like ? and ";
            parameters.add("%" + yewuhao + "%");
          }
          if (jiluzhuangtai != null) {
            criterion += "  credit.jiluzhuangtai=? and ";
            parameters.add(new BigDecimal(jiluzhuangtai));
          }

          if (criterion.length() != 0) {
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " credit.id ";

          String od = order;
          if (od == null)
            od = " DESC";
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getCreditListAll(final String shujutiquriqi,
      final String baowenshengchengriqi, final String officeCode,
      final String loanBankName, final String yewuhao,
      final String jiluzhuangtai, final int start, final String orderBy,
      final String order, final int pageSize, final int page) {
    Integer n = new Integer(0);
    try {
      n = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select count(credit.id) from Credit credit";
          Vector parameters = new Vector();
          String criterion = "";

          if (shujutiquriqi != null) {
            criterion += "  credit.shujutiquriqi=? and ";
            parameters.add(shujutiquriqi);
          }
          if (baowenshengchengriqi != null) {
            criterion += "  credit.baowenshengchengriqi=? and ";
            parameters.add(baowenshengchengriqi);
          }
          if (officeCode != null) {
            criterion += "  credit.office=? and ";
            parameters.add(officeCode);
          }
          if (loanBankName != null) {
            criterion += "  credit.loan_bank_id=? and ";
            parameters.add(new BigDecimal(loanBankName));
          }
          if (yewuhao != null) {
            criterion += "  credit.yewuhao like ? and ";
            parameters.add("%" + yewuhao + "%");
          }
          if (jiluzhuangtai != null) {
            criterion += "  credit.jiluzhuangtai=? and ";
            parameters.add(new BigDecimal(jiluzhuangtai));
          }

          if (criterion.length() != 0) {
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " credit.id ";

          String od = order;
          if (od == null)
            od = " DESC";
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return new Integer(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return n.intValue();
  }

  public ResultSet exportNormal(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      // 报文头
      sql
          .append(" select varcharformat(c001.sjgsbbh, 3) || varcharformat(c001.jrjgdm, 14) ||");
      sql
          .append(" to_char(sysdate, 'YYYYMMDDHHMMSS') || varcharformat(c001.scbwbbh, 3) || '1' || '1' ||");
      sql
          .append(" (select numformat(count(c101.id), 10) || min(c101.yinghuankuanriqi) ||");
      sql.append(" max(c101.yinghuankuanriqi)");
      sql.append(" from cr101 c101");
      sql.append(" where c101.shujutiquriqi = '" + shujutiquriqi + "'");
      sql.append(" and c101.jiluzhuangtai = 0) ||");
      sql
          .append(" varcharformat(c001.lxr, 30) || varcharformat(c001.lxdh, 25) ||");
      sql.append(" varcharformat('', 30) || '\r\n' as data");
      sql.append(" from cr001 c001 ");
      sql.append(" union all ");
      // 报文体
      sql.append("select '0983' ||");// --账户记录长度
      sql.append("'A' ||");// --信息类别（基础段就是A）
      sql.append("varcharformat(c001.jrjgdm, 14) ||");// --金融机构代码
      sql.append("'1' ||");// --业务种类,1-贷款；
      sql.append("'13' ||");// --业务种类细分,13-个人住房公积金贷款；
      sql.append("varcharformat(c101.yewuhao, 40) ||");// --业务号
      sql.append("numformat(c001.fsdd, 6) ||");// --发生地点
      sql.append("numformat(c101.kaihuriqi, 8) ||");// --开户日期
      sql.append("numformat(c101.daoqiriqi, 8) ||");// --到期日期
      sql.append("varcharformat(c001.bz, 3) ||");// --币种
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --授信额度
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --共享授信额度
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --最大负债额
      sql.append("'2' ||");// --担保方式
      sql.append("'03' ||");// --还款频率
      sql.append("varcharformat(c101.huankuanyueshu, 3) ||");// --还款月数
      sql.append("varcharformat(c101.shengyuqixian, 3) ||");// --剩余还款月数
      sql.append("numformat(c101.yinghuankuanriqi, 8) ||");// --结算/应还款日期
      sql.append("numformat(c101.shijihuankuanriqi, 8) ||");// --最近一次实际还款日期
      sql.append("numformat(round(c101.yinghuankuanjine), 10) ||");// --本月应还款金额
      sql.append("numformat(round(c101.shijihuankuanjine), 10) ||");// --实际还款金额
      sql.append("numformat(round(c101.daikuanyue), 10) ||");// --余额
      sql.append("numformat(c101.dangqianyuqiqishu, 2) ||");// --当前逾期期数
      sql.append("numformat(round(c101.dangqianyuqizonge), 10) ||");// --当前逾期总额
      sql.append("numformat(round(c101.yuqiyigeyue), 10) ||");// --逾期31-60天未归还贷款本金
      sql.append("numformat(round(c101.yuqilianggeyue), 10) ||");// --逾期61-90天未归还贷款本金
      sql.append("numformat(round(c101.yuqisangeyue), 10) ||");// --逾期91-180天未归还贷款本金
      sql.append("numformat(round(c101.yuqiliugeyue), 10) ||");// --逾期180天以上未归还贷款本金
      sql.append("numformat(c101.weiyuecishu, 3) ||");// --违约次数
      sql.append("numformat(c101.zuigaoyuqiqishu, 2) ||");// --最高逾期期数
      sql.append("'9' ||");// --五级分类状态
      sql.append("numformat(c101.zhanghuzhuangtai, 1) ||");// --账户状态
      sql.append("varcharformat(c101.ershisigeyue, 24) ||");// --24个月（账户）还款状态
      sql.append("'0000000000' ||");// --透支180天以上未付余额
      sql.append("numformat(c101.zhanghuxinxitishi, 1) ||");// --账户拥有者信息提示
      sql.append("varcharformat(c101.xingming, 30) ||");// --姓名
      sql.append("numformat(c101.zhengjianleixing, 1) ||");// --证件类型
      sql.append("varcharformat(c101.zhengjianhaoma, 18) ||");// --证件号码
      sql.append("varcharformat('', 30) ||");// --预留字段

      sql.append("'B' ||");// --信息类别（身份信息段就是B）
      sql.append("numformat(c102.sex, 1) ||");// --性别
      sql.append("numformat(c102.birthday, 8) ||");// --出生日期
      sql.append("numformat(c102.marriage_st, 2) ||");// --婚姻状况
      sql.append("numformat(c102.degree, 2) ||");// --最高学历
      sql.append("'9' ||");// --最高学位
      sql.append("varcharformat(c102.house_tel, 25) ||");// --住宅电话
      sql.append("varcharformat(c102.home_mobile, 16) ||");// --手机号码
      sql.append("varcharformat(c102.org_tel, 25) ||");// --单位电话
      sql.append("varcharformat('', 30) ||");// --电子邮箱
      sql.append("varcharformat(c102.home_addr, 60) ||");// --住宅地址，通讯地址
      sql.append("numformat(c102.home_mail, 6) ||");// --通讯地址邮政编码
      sql.append("varcharformat(c102.native_place, 60) ||");// --户籍地址
      sql.append("varcharformat(c102.p_name, 30) ||");// --配偶姓名
      sql.append("varcharformat(c102.p_card_kind, 1) ||");// --配偶证件类型
      sql.append("varcharformat(c102.p_card_num, 18) ||");// --配偶证件号码
      sql.append("varcharformat(c102.p_org_name, 60) ||");// --配偶工作单位
      sql.append("varcharformat(c102.p_home_mobile, 25) ||");// --配偶工作单位

      sql.append("'C' ||");// --信息类别（职业信息段就是C）
      sql.append("'Z' ||");// --职业,Z-未知。
      sql.append("varcharformat(c102.org_name, 60) ||");// --单位名称
      sql.append("'Z' ||");// --单位所属行业,Z-未知。
      sql.append("varcharformat(c102.org_addr, 60) ||");// --单位名称
      sql.append("numformat(c102.org_mail, 6) ||");// --单位地址邮政编码
      sql.append("'0000' ||");// --本单位工作起始年份
      sql.append("'9' ||");// --职务,9-未知。
      sql.append("numformat(c102.title, 1) ||");// --职称
      sql.append("numformat(round(c102.year_salary), 10) ||");// --年收入
      sql.append("varcharformat('', 40) ||");// --工资账号
      sql.append("varcharformat('', 14) ||");// --工资账户开户银行

      sql.append("'D' ||");// --信息类别（居住地址段就是D）
      sql.append("varcharformat(c102.home_addr, 60) ||");// --居住地址
      sql.append("numformat(c102.home_mail, 6) ||");// --通讯地址邮政编码
      sql.append("'9' ");// --居住状况,9-未知。

      sql.append(" as data");// 贷款征信数据
      sql.append(" from cr101 c101, cr001 c001, cr102 c102");
      sql.append(" where c101.yewuhao = c102.contract_id");
      sql.append(" and c101.isexport = 0");//
      sql.append(" and c101.shujutiquriqi = '" + shujutiquriqi + "'");
      sql.append(" and c101.jiluzhuangtai = 0");

      sql.append(" union all ");

      sql.append(" select '0345' ||");// --账户记录长度
      sql.append("'A' ||");// --信息类别（基础段就是A）
      sql.append("varcharformat(c001.jrjgdm, 14) ||");// --金融机构代码
      sql.append("'1' ||");// --业务种类,1-贷款；
      sql.append("'13' ||");// --业务种类细分,13-个人住房公积金贷款；
      sql.append("varcharformat(c101.yewuhao, 40) ||");// --业务号
      sql.append("numformat(c001.fsdd, 6) ||");// --发生地点
      sql.append("numformat(c101.kaihuriqi, 8) ||");// --开户日期
      sql.append("numformat(c101.daoqiriqi, 8) ||");// --到期日期
      sql.append("varcharformat(c001.bz, 3) ||");// --币种
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --授信额度
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --共享授信额度
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --最大负债额
      sql.append("'2' ||");// --担保方式
      sql.append("'03' ||");// --还款频率
      sql.append("varcharformat(c101.huankuanyueshu, 3) ||");// --还款月数
      sql.append("varcharformat(c101.shengyuqixian, 3) ||");// --剩余还款月数
      sql.append("numformat(c101.yinghuankuanriqi, 8) ||");// --结算/应还款日期
      sql.append("numformat(c101.shijihuankuanriqi, 8) ||");// --最近一次实际还款日期
      sql.append("numformat(round(c101.yinghuankuanjine), 10) ||");// --本月应还款金额
      sql.append("numformat(round(c101.shijihuankuanjine), 10) ||");// --实际还款金额
      sql.append("numformat(round(c101.daikuanyue), 10) ||");// --余额
      sql.append("numformat(c101.dangqianyuqiqishu, 2) ||");// --当前逾期期数
      sql.append("numformat(round(c101.dangqianyuqizonge), 10) ||");// --当前逾期总额
      sql.append("numformat(round(c101.yuqiyigeyue), 10) ||");// --逾期31-60天未归还贷款本金
      sql.append("numformat(round(c101.yuqilianggeyue), 10) ||");// --逾期61-90天未归还贷款本金
      sql.append("numformat(round(c101.yuqisangeyue), 10) ||");// --逾期91-180天未归还贷款本金
      sql.append("numformat(round(c101.yuqiliugeyue), 10) ||");// --逾期180天以上未归还贷款本金
      sql.append("numformat(c101.weiyuecishu, 3) ||");// --违约次数
      sql.append("numformat(c101.zuigaoyuqiqishu, 2) ||");// --最高逾期期数
      sql.append("'9' ||");// --五级分类状态
      sql.append("numformat(c101.zhanghuzhuangtai, 1) ||");// --账户状态
      sql.append("varcharformat(c101.ershisigeyue, 24) ||");// --24个月（账户）还款状态
      sql.append("'0000000000' ||");// --透支180天以上未付余额
      sql.append("numformat(c101.zhanghuxinxitishi, 1) ||");// --账户拥有者信息提示
      sql.append("varcharformat(c101.xingming, 30) ||");// --姓名
      sql.append("numformat(c101.zhengjianleixing, 1) ||");// --证件类型
      sql.append("varcharformat(c101.zhengjianhaoma, 18) ||");// --证件号码
      sql.append("varcharformat('', 30)");// --预留字段
      sql.append(" as data ");// 贷款征信数据
      sql.append(" from cr101 c101, cr001 c001");
      sql.append(" where c101.isexport = 1");
      sql.append(" and c101.shujutiquriqi = '" + shujutiquriqi + "'");
      sql.append(" and c101.jiluzhuangtai = 0");
      // System.out.println(sql.toString());
      ResultSet rs = st.executeQuery(sql.toString());
      return rs;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public ResultSet exportDelete(final String shujutiquriqi,
      final String[] rowArray) {
    String row = "";
    for (int i = 0; i < rowArray.length; i++) {
      row = row.concat("'").concat(rowArray[i]).concat("',");
    }
    row = row.substring(0, row.lastIndexOf(","));
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      // 报文头
      sql
          .append("select varcharformat(c101.sjgsbbh, 3) || to_char(sysdate, 'YYYYMMDDHHMMSS') || varcharformat(c101.jrjgdm, 14) || '8' || numformat('"
              + rowArray.length
              + "',8) || varcharformat(c101.lxr, 30) || varcharformat(c101.lxdh, 25) || varcharformat('', 30) || '\r\n' as data from cr001 c101");
      sql.append(" union all ");
      // 报文体
      sql
          .append("select varcharformat(c001.jrjgdm, 14) || varcharformat(c101.yewuhao, 40) || numformat(c101.yinghuankuanriqi, 8) as data from cr101 c101, cr001 c001");
      sql.append(" where c101.yewuhao in (" + row + ")");

      ResultSet rs = st.executeQuery(sql.toString());
      return rs;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void dealWithContract(final String[] rowArray, final String status) {
    String row = "";
    for (int i = 0; i < rowArray.length; i++) {
      row = row.concat("'").concat(rowArray[i]).concat("',");
    }
    row = row.substring(0, row.lastIndexOf(","));
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql.append("update cr101 c101 set c101.jiluzhuangtai = "
          + new Integer(status));
      sql.append(" where c101.yewuhao in (" + row + ")");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void isContractExport(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql
          .append("update cr101 c101 set c101.baowenshengchengriqi = to_char(sysdate,'yyyyMMdd'), c101.isexport = "
              + new Integer(1));
      sql.append(" where c101.shujutiquriqi ='" + shujutiquriqi + "'");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getCreditCountByDate(final String shujutiquriqi) {
    Integer n = new Integer(0);
    try {
      n = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select count(credit.id) from Credit credit where credit.shujutiquriqi=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, shujutiquriqi);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return n.intValue();
  }

  public void createCredit(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql.append("call credit('" + shujutiquriqi + "')");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteCredit(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql
          .append("delete cr101 t where t.shujutiquriqi='" + shujutiquriqi
              + "'");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
