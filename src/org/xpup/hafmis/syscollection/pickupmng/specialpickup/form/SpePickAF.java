package org.xpup.hafmis.syscollection.pickupmng.specialpickup.form;


import org.apache.struts.validator.ValidatorForm;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;

  public class SpePickAF extends ValidatorForm {

    private static final long serialVersionUID = 4545439817994321837L;

    private SpecialPick specialPick = new SpecialPick();
    private String temp_cardkind;

    public SpecialPick getSpecialPick() {
      return specialPick;
    }

    public void setSpecialPick(SpecialPick specialPick) {
      this.specialPick = specialPick;
    }

    public String getTemp_cardkind() {
      return temp_cardkind;
    }

    public void setTemp_cardkind(String temp_cardkind) {
      this.temp_cardkind = temp_cardkind;
    }
 
  }
