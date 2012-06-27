package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import org.xpup.hafmis.common.form.CriterionsAF;
public class EmployeesAF extends CriterionsAF {

    private static final long serialVersionUID = 2250934705982943571L;

    private String id = null;

    private String name = null;

    private String cardNumber = null;

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
      this.id=null;
      this.name=null;
      this.cardNumber=null;
    }
  }



