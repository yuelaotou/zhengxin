package org.xpup.hafmis.sysloan.common.biz.emppop.form;
import org.apache.struts.validator.ValidatorForm;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;


public class EmployeesFaAF extends ValidatorForm {






    private static final long serialVersionUID = -3729450809147474225L;

    private Emp emp = new Emp();
    private EmpInfo empInfo = new EmpInfo();


    public EmpInfo getEmpInfo() {
      return empInfo;
    }

    public void setEmpInfo(EmpInfo empInfo) {
      this.empInfo = empInfo;
    }

    public Emp getEmp() {
      return emp;
    }

    public void setEmp(Emp emp) {
      this.emp = emp;
    }



 
  }


