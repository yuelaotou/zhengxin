package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.business;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.bsinterface.IEmpBaseExportsBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto.EmpBaseExportsDTO;

public class EmpBaseExportsBS implements IEmpBaseExportsBS {
  private EmpDAO empDAO;

  private OrgDAO orgDAO;

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public List getEmployeeInfoAllList(String orgid, List returnList)
      throws BusinessException {
    List list = empDAO.getEmpBaseExportList(orgid);
    for (int i = 0; i < list.size(); i++) {
      EmpBaseExportsDTO dto = new EmpBaseExportsDTO();
      Object obj[] = (Object[]) list.get(i);
      dto.setEmpId(BusiTools.convertSixNumber(obj[0].toString()));
      dto.setEmpName(obj[1].toString());
      dto.setCardNum(obj[2].toString());
      dto.setSalaryBase(obj[3].toString());
      dto.setOrgPay(obj[4].toString());
      dto.setEmpPay(obj[5].toString());
      dto.setAllPay(new BigDecimal(obj[4].toString()).add(
          new BigDecimal(obj[5].toString())).toString());
      dto.setPayStatus(obj[6].toString());
      try {
        dto.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto.getPayStatus()), BusiConst.OLDPAYMENTSTATE));
      } catch (NumberFormatException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
      dto.setOrg_pay_month(obj[7].toString());
      dto.setEmp_pay_month(obj[8].toString());
      dto.setBalance(obj[9].toString());
      returnList.add(dto);
    }
    return returnList;
  }

  public Org getOrg(String orgid) throws BusinessException {
    Org org = orgDAO.queryById(new Integer(orgid));
    return org;
  }

  public EmpDAO getEmpDAO() {
    return empDAO;
  }

  public OrgDAO getOrgDAO() {
    return orgDAO;
  }

}
