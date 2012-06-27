package org.xpup.hafmis.orgstrct.form;

import org.xpup.common.enums.SexEnum;
import org.xpup.hafmis.common.form.DomainObjectAF;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.domain.enums.DutyEnum;

public class HafEmployeeAF extends DomainObjectAF {

  private static final long serialVersionUID = 5338053892527901887L;

  private HafEmployee hafEmployee = new HafEmployee();

  private String orgUnitId = null;

  public String getOrgUnitId() {
    return orgUnitId;
  }

  public void setOrgUnitId(String orgUnitId) {
    this.orgUnitId = orgUnitId;
  }

  public HafEmployee getHafEmployee() {
    return hafEmployee;
  }

  public void setHafEmployee(HafEmployee employee) {
    this.hafEmployee = employee;
  }

  public int getSex() {
    if (hafEmployee.getSex() == null) {
      return -1;
    }
    return hafEmployee.getSex().getValue();
  }

  public void setSex(int sex) {
    if (sex != -1)
      hafEmployee.setSex(SexEnum.getEnum(sex));
    else
      hafEmployee.setSex(null);
  }

  public int getDuty() {
    if (hafEmployee.getDuty() == null) {
      return -1;
    }
    return hafEmployee.getDuty().getValue();
  }

  public void setDuty(int duty) {
    if (duty != -1)
      hafEmployee.setDuty(DutyEnum.getEnum(duty));
    else
      hafEmployee.setDuty(null);
  }

}
