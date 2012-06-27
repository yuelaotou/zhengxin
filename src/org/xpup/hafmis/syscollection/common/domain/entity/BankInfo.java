package org.xpup.hafmis.syscollection.common.domain.entity;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class BankInfo extends DomainObject {
    
    
  private static final long serialVersionUID = 1L;
    
    private String  num="";
    /** persistent field */
    private String descrip="";
    
    private String value="";
    
   
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    
    public String getReserveaA() {
      return reserveaA;
    }

    public void setReserveaA(String reserveaA) {
      this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
      return reserveaB;
    }

    public void setReserveaB(String reserveaB) {
      this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
      return reserveaC;
    }

    public void setReserveaC(String reserveaC) {
      this.reserveaC = reserveaC;
    }

    public String getDescrip() {
      return descrip;
    }

    public void setDescrip(String descrip) {
      this.descrip = descrip;
    }

    public String getNum() {
      return num;
    }

    public void setNum(String num) {
      this.num = num;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
}
