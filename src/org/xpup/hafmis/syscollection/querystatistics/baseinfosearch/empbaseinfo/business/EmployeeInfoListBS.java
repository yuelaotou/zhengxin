package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.bsinterface.IEmployeeInfoListBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto.EmployeeInfoDisplayDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto.EmployeeInfoSearchDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.form.EmployeeInfoSearchAF;

public class EmployeeInfoListBS implements IEmployeeInfoListBS {
  
  private EmpDAO empDao;

  public void setEmpDao(EmpDAO empDao) {
    this.empDao = empDao;
  }

  public Emp findEmpByOrdIdAndEmpId(Integer orgId, Integer empId)
      throws BusinessException {
    // 此记录一定不能为null..因为这个是点职工姓名的超连接而得到的结果
    try {
      Emp emp = empDao.findEmpByOrdIdAndEmpId(orgId, empId);
      emp.getEmpInfo().setConSex(
          BusiTools.getBusiValue(emp.getEmpInfo().getSex().intValue(),
              BusiConst.SEX));// 转换性别
      emp.getEmpInfo().setConCardType(
          BusiTools.getBusiValue(emp.getEmpInfo().getCardKind().intValue(),
              BusiConst.DOCUMENTSSTATE));// 转换证件类型
      String temporgid = emp.getOrg().getId().toString();
      String orgid = BusiTools.convertTenNumber(temporgid);
      emp.getOrg().setId(orgid);
      emp.getOrg().setConPayMode(
          BusiTools.getBusiValue(emp.getOrg().getPayMode().intValue(),
              BusiConst.ORGPAYWAY));
      return emp;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 获得职工信息列表的方法.. 此方法返回一个Object 这连个数组都是Object类型的.. 第一个元素是要显示的数据.. 第二个元素是汇总的元素
   */
  public EmployeeInfoSearchAF getEmployeeInfoList(Pagination pagination, SecurityInfo info) {
    EmployeeInfoSearchAF af = new EmployeeInfoSearchAF();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      EmployeeInfoSearchDTO dto = (EmployeeInfoSearchDTO) pagination
          .getQueryCriterions().get("search");
      if(dto == null)
        dto = new EmployeeInfoSearchDTO();
      List list = empDao.getEmployeeInfoList_LY(dto, orderBy, order, start,
          pageSize, info);
      List ll = new ArrayList();
      for (int i = 0; i < list.size(); i++) {
        EmployeeInfoDisplayDTO employeeInfoDisplayDTO = (EmployeeInfoDisplayDTO) list
            .get(i);
        if (employeeInfoDisplayDTO != null) {
          String card_num = "";
          String card_num_two = "";
          String pl_status = "";
          String empid_name = "";

          card_num = employeeInfoDisplayDTO.getCardnum().toString();
          empid_name = employeeInfoDisplayDTO.getEmpname().toString();

          if (card_num.length() == 18) {
            card_num_two = CardMunChange.get15Id(card_num);
          } else if (card_num.length() == 15) {
            card_num_two = CardMunChange.get18Id(card_num);
          }
//          List pl_statusList = (List) empDao.get_pl_status_yg(card_num,
//              card_num_two, empid_name);
          List pl_statusList = (List) empDao.get_pl_status_yg_wuht(card_num,
              card_num_two, empid_name);
        //查询的数据的贷款状态,还清的为空  
          if(pl_statusList.size()==0){
            pl_statusList = (List) empDao.get_pl_other_status(card_num,
                card_num_two, empid_name);
          }
          String temp_pl_status = "";
          for (int p = 0; p < pl_statusList.size(); p++) {
            pl_status = pl_statusList.get(p) + "";
            if (pl_status.equals("1")) {
              temp_pl_status += "借款人";
            } else if (pl_status.equals("2")) {
              temp_pl_status += "共同借款人";
            } else if (pl_status.equals("3")) {
              temp_pl_status += "保证人";
            }else{
              temp_pl_status += "异地贷款";
            }
            temp_pl_status += "/";
          }
          if (temp_pl_status.length() != 0) {
            temp_pl_status = temp_pl_status.substring(0, temp_pl_status
                .lastIndexOf("/"));
            employeeInfoDisplayDTO.setLoanYesNo(temp_pl_status);
          }
          List l = empDao.getYearPickBalanceAndCount(Integer
              .parseInt(employeeInfoDisplayDTO.getEmpid().toString
              ()), info);
          if (l != null) {
            EmployeeInfoDisplayDTO employeeInfoDisplayDTO1 = (EmployeeInfoDisplayDTO) l
                .get(0);
            employeeInfoDisplayDTO.setPickcount(employeeInfoDisplayDTO1
                .getPickcount());
            employeeInfoDisplayDTO.setPickmoney(employeeInfoDisplayDTO1
                .getPickmoney());
          }
          employeeInfoDisplayDTO.setMaxPickMoney(new BigDecimal(
              employeeInfoDisplayDTO.getBalance())
              .subtract(employeeInfoDisplayDTO.getPayOldYear().multiply(
                  new BigDecimal(12))));
          if(employeeInfoDisplayDTO.getMaxPickMoney().intValue() < 0)
            employeeInfoDisplayDTO.setMaxPickMoney(new BigDecimal(0.00));
        }
        ll.add(employeeInfoDisplayDTO);
      }
      af.setList(ll);
      Object[] obj = empDao.queryTotalInfoByCriterions(dto, info);
      af.setSumPerson(new BigDecimal(obj[0].toString()));
      af.setSumOrgPay(new BigDecimal(obj[1].toString()));
      af.setSumEmpPay(new BigDecimal(obj[2].toString()));
      af.setSumMoney(new BigDecimal(obj[4].toString()));
      af.setSumSalaryBase(new BigDecimal(obj[3].toString()));
      pagination.setNrOfElements(Integer.valueOf(obj[0].toString()).intValue());
    } catch (Exception s) {
      s.printStackTrace();
    }
    return af;
  }

  public List getEmployeeInfoAllList(Pagination pag, SecurityInfo info)
      throws BusinessException {
    List ll = new ArrayList();
    try {
      String orderBy = (String) pag.getOrderBy();
      String order = (String) pag.getOrderother();
      EmployeeInfoSearchDTO dto = (EmployeeInfoSearchDTO) pag
          .getQueryCriterions().get("search");
      if (dto == null)
        dto = new EmployeeInfoSearchDTO();
      List list = empDao
          .getEmployeeInfoList_LY(dto, orderBy, order, 0, 0, info);

      for (int i = 0; i < list.size(); i++) {
        EmployeeInfoDisplayDTO employeeInfoDisplayDTO = (EmployeeInfoDisplayDTO) list
            .get(i);
        if (employeeInfoDisplayDTO != null) {
          String card_num = "";
          String card_num_two = "";
          String pl_status = "";
          String empid_name = "";

          card_num = employeeInfoDisplayDTO.getCardnum().toString();
          empid_name = employeeInfoDisplayDTO.getEmpname().toString();

          if (card_num.length() == 18) {
            card_num_two = CardMunChange.get15Id(card_num);
          } else if (card_num.length() == 15) {
            card_num_two = CardMunChange.get18Id(card_num);
          }
//          List pl_statusList = (List) empDao.get_pl_status(card_num,
//              card_num_two, empid_name);
          List pl_statusList = (List) empDao.get_pl_status_yg_wuht(card_num,
              card_num_two, empid_name);
          
          String temp_pl_status = "";
          for (int p = 0; p < pl_statusList.size(); p++) {
            pl_status = pl_statusList.get(p) + "";
            if (pl_status.equals("1")) {
              temp_pl_status += "借款人";
            } else if (pl_status.equals("2")) {
              temp_pl_status += "共同借款人";
            } else if (pl_status.equals("3")) {
              temp_pl_status += "保证人";
            }
            temp_pl_status += "/";
          }
          if (temp_pl_status.length() != 0) {
            temp_pl_status = temp_pl_status.substring(0, temp_pl_status
                .lastIndexOf("/"));
            employeeInfoDisplayDTO.setLoanYesNo(temp_pl_status);
          }
          List l = empDao.getYearPickBalanceAndCount(Integer
              .parseInt(employeeInfoDisplayDTO.getEmpid().toString()), info);
          if (l != null) {
            EmployeeInfoDisplayDTO employeeInfoDisplayDTO1 = (EmployeeInfoDisplayDTO) l
                .get(0);
            employeeInfoDisplayDTO.setPickcount(employeeInfoDisplayDTO1
                .getPickcount());
            employeeInfoDisplayDTO.setPickmoney(employeeInfoDisplayDTO1
                .getPickmoney());
          }
          employeeInfoDisplayDTO.setMaxPickMoney(new BigDecimal(
              employeeInfoDisplayDTO.getBalance())
              .subtract(employeeInfoDisplayDTO.getPayOldYear().multiply(
                  new BigDecimal(12))));
          if (employeeInfoDisplayDTO.getMaxPickMoney().intValue() < 0)
            employeeInfoDisplayDTO.setMaxPickMoney(new BigDecimal(0.00));
        }
        ll.add(employeeInfoDisplayDTO);
      }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return ll;
  }
}
