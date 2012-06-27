package org.xpup.hafmis.sysloan.common.biz.emppop.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import org.xpup.hafmis.common.form.CriterionsAF;
public class EmployeesAF extends CriterionsAF {

    private static final long serialVersionUID = 2250934705982943571L;

    private String id = "";

    private String name = "";

    private String cardNumber = "";
    
    private String oldId = "";
    private String sfzh="";
    private String dwbh="";
    private String dwmc="";
    public String getCardNumber() {
      return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
    public void reset(ActionMapping mapping, HttpServletRequest request) {
      super.reset(mapping, request);
      this.id="";
      this.name="";
      this.cardNumber="";
      this.oldId="";
      this.sfzh="";
      this.dwbh="";
      this.dwmc="";
    }

    public String getOldId() {
      return oldId;
    }

    public void setOldId(String oldId) {
      this.oldId = oldId;
    }

    public String getDwbh() {
      return dwbh;
    }

    public void setDwbh(String dwbh) {
      this.dwbh = dwbh;
    }

    public String getDwmc() {
      return dwmc;
    }

    public void setDwmc(String dwmc) {
      this.dwmc = dwmc;
    }

    public String getSfzh() {
      return sfzh;
    }

    public void setSfzh(String sfzh) {
      this.sfzh = sfzh;
    }
  }



