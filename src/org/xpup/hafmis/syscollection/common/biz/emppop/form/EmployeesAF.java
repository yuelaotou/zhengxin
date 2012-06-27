package org.xpup.hafmis.syscollection.common.biz.emppop.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import org.xpup.hafmis.common.form.CriterionsAF;
public class EmployeesAF extends CriterionsAF {

    private static final long serialVersionUID = 2250934705982943571L;

    private String id = "";

    private String name = "";

    private String cardNumber = "";
    
    private String oldId = "";
    
    private String orgId = "";
    
    private String orgName = "";

    public String getOrgId() {
      return orgId;
    }

    public void setOrgId(String orgId) {
      this.orgId = orgId;
    }

    public String getOrgName() {
      return orgName;
    }

    public void setOrgName(String orgName) {
      this.orgName = orgName;
    }

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
    }

    public String getOldId() {
      return oldId;
    }

    public void setOldId(String oldId) {
      this.oldId = oldId;
    }
  }