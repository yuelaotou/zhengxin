package org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
//import org.xpup.hafmis.syscollection.pickupmng.specialpickup.dto.SpeEmpDTO;

public interface ISpePickBS {
  public Org findOrgSpePickById (Integer id) throws BusinessException;
  public Emp findSpeEmp(String orgID,String empID)throws BusinessException;
  public SpecialPick findSpePickMoney(String orgId,String empId)throws BusinessException;
  public void updateOrgOpenAccount(SpecialPick specialPick, SecurityInfo securityInfo)throws BusinessException;
  public SpecialPick findEmpSpePick(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public List findEmpSpePickList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public void deleteSpePick(Integer id, SecurityInfo securityInfo) throws BusinessException;
  public Org queryOrgById(Integer id,SecurityInfo securityInfo) throws Exception,BusinessException;
  public void saveOrgOpenAccount(SpecialPick formSpecialPick, SecurityInfo securityInfo)throws BusinessException;
//  public Emp findSpeEmp(String id)throws BusinessException;
  public boolean check(String orgID,SecurityInfo info);
  public boolean checkTranOut(String orgID);
  public List isClearAccount(String orgID,String empID) throws Exception;
}
