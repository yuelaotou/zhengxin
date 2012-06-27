package org.xpup.hafmis.syscollection.accounthandle.ratemng.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;

public class RatemngAF extends IdAF{

  /**
   * 维护HafInterestRate 公积金利息利率表 AA313
   */
  private static final long serialVersionUID = 7016089213451392811L;
  private List list;
  private String loadsMassage="";
  private HafInterestRate hafInterestRate=new HafInterestRate();

  private String officecode="";
  private String usetime="";
  private String ratetype="";
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    list=new ArrayList();
    loadsMassage="";
    hafInterestRate=new HafInterestRate();
  }
  public HafInterestRate getHafInterestRate() {
    return hafInterestRate;
  }
  public void setHafInterestRate(HafInterestRate hafInterestRate) {
    this.hafInterestRate = hafInterestRate;
  }
  public String getLoadsMassage() {
    return loadsMassage;
  }
  public void setLoadsMassage(String loadsMassage) {
    this.loadsMassage = loadsMassage;
  }
  public String getOfficecode() {
    return officecode;
  }
  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }
  public String getRatetype() {
    return ratetype;
  }
  public void setRatetype(String ratetype) {
    this.ratetype = ratetype;
  }
  public String getUsetime() {
    return usetime;
  }
  public void setUsetime(String usetime) {
    this.usetime = usetime;
  }

}
