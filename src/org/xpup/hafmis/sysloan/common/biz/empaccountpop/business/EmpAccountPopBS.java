/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAccountPopBS
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-11-02
 * 修改日期                   :2007-11-13通过借款人姓名和证件号码查询职工明细账；增加查询条件：职工编号、单位编号
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.bsinterface.IEmpAccountPopBS;
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.form.EmpAccountPopAF;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;

public class EmpAccountPopBS implements IEmpAccountPopBS {
  private EmpDAO empDAO = null;

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  /**
   * Description 查看职工明细账
   * 
   * @author wangy 2007-11-02
   * @param 根据条件查询列表
   * @param 由EmpAccountPopShowAC调用
   * @param borrowerName 职工姓名
   * @param cardNum 证件号码
   * @return EmpAccountPopAF
   * @throws Exception, BusinessException
   */
  public EmpAccountPopAF queryEmpAccountListByCriterions(String borrowerName,
      String cardNum, Pagination pagination) throws Exception,
      BusinessException {
    EmpAccountPopAF empAccountPopAF = new EmpAccountPopAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    // 枚举转换业务类型
    // if (bizType != null && !bizType.equals("")) {
    // try {
    // bizType = BusiTools.getBusiKey_WL(bizType,
    // BusiConst.CLEARACCOUNTBUSINESSTYPE);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    String settDate = (String) pagination.getQueryCriterions().get("settDate");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    List empAccountPopList = new ArrayList();
    empAccountPopList = empDAO.queryEmpAccountListByCriterions(borrowerName,
        cardNum, empId, orgId, bizType, settDate, start, orderBy, order,
        pageSize, page);
    empAccountPopAF.setList(empAccountPopList);
    BigDecimal debitTotal = new BigDecimal(0.00);// 借方发生额
    BigDecimal creditTotal = new BigDecimal(0.00);// 贷方发生额
    BigDecimal interestTotal = new BigDecimal(0.00);// 利息
    List tempAllList = empDAO.queryEmpAccountCountAndSumMoneyByCriterion(
        borrowerName, cardNum, empId, orgId, bizType, settDate);
    Iterator iterate = tempAllList.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      obj = (Object[]) iterate.next();
      // 借方发生额
      BigDecimal debit = new BigDecimal(0.00);// 借方发生额
      if (obj[3] != null && !obj[3].equals(""))
        debit = new BigDecimal(obj[3].toString());
      debitTotal = debitTotal.add(debit);
      // 贷方发生额
      BigDecimal credit = new BigDecimal(0.00);// 贷方发生额
      if (obj[4] != null && !obj[4].equals("")) {
        credit = new BigDecimal(obj[4].toString());
      }
      creditTotal = creditTotal.add(credit);
      // 利息
      BigDecimal interest = new BigDecimal(0.00);// 利息
      if (obj[5] != null && !obj[5].equals("")) {
        interest = new BigDecimal(obj[5].toString());
      }
      interestTotal = interestTotal.add(interest);
    }
    count = tempAllList.size();
    empAccountPopAF.setDebitTotal(debitTotal);
    empAccountPopAF.setCreditTotal(creditTotal);
    empAccountPopAF.setInterestTotal(interestTotal);
    pagination.setNrOfElements(count);
    return empAccountPopAF;
  }

  public EmpAccountPopAF queryEmpAccountListByCriterions_yg(Pagination pagination)
      throws Exception, BusinessException {
    EmpAccountPopAF empAccountPopAF = new EmpAccountPopAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String settDateA = (String) pagination.getQueryCriterions().get("settDateA");
    String settDateB = (String) pagination.getQueryCriterions().get("settDateB");
    List empAccountPopList = new ArrayList();
    empAccountPopList = empDAO.queryEmpAccountListByCriterions_yg(bizType, settDateA, settDateB, start, orderBy, order,
        pageSize, page);
    empAccountPopAF.setList(empAccountPopList);
    BigDecimal debitTotal = new BigDecimal(0.00);// 借方发生额
    BigDecimal creditTotal = new BigDecimal(0.00);// 贷方发生额
    BigDecimal interestTotal = new BigDecimal(0.00);// 利息
    List tempAllList = empDAO.queryEmpAccountCountAndSumMoneyByCriterion_yg(bizType, settDateA, settDateB);
    Iterator iterate = tempAllList.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      obj = (Object[]) iterate.next();
      // 借方发生额
      BigDecimal debit = new BigDecimal(0.00);// 借方发生额
      if (obj[3] != null && !obj[3].equals(""))
        debit = new BigDecimal(obj[3].toString());
      debitTotal = debitTotal.add(debit);
      // 贷方发生额
      BigDecimal credit = new BigDecimal(0.00);// 贷方发生额
      if (obj[4] != null && !obj[4].equals("")) {
        credit = new BigDecimal(obj[4].toString());
      }
      creditTotal = creditTotal.add(credit);
      // 利息
      BigDecimal interest = new BigDecimal(0.00);// 利息
      if (obj[5] != null && !obj[5].equals("")) {
        interest = new BigDecimal(obj[5].toString());
      }
      interestTotal = interestTotal.add(interest);
    }
    count = tempAllList.size();
    empAccountPopAF.setDebitTotal(debitTotal);
    empAccountPopAF.setCreditTotal(creditTotal);
    empAccountPopAF.setInterestTotal(interestTotal);
    pagination.setNrOfElements(count);
    return empAccountPopAF;
  }
}
