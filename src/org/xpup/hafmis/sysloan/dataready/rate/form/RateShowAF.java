package org.xpup.hafmis.sysloan.dataready.rate.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;

public class RateShowAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = -1951127203105896180L;
  private List list;
  private RateDTO rateDTO=new RateDTO();
  
  private String officecode="";
  private String usetime="";
  private String ratetype="";
  private String adjustBasis = "";
  private String latestDate;
  private String latestDateNex;

  private Map ratetypemap=new HashMap();
  
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
  
  public Map getRatetypemap() {
    return ratetypemap;
  }
  
  public void setRatetypemap(Map ratetypemap) {
    this.ratetypemap = ratetypemap;
  }
  
  public String getUsetime() {
    return usetime;
  }
  
  public void setUsetime(String usetime) {
    this.usetime = usetime;
  }
  
  public RateDTO getRateDTO() {
    return rateDTO;
  }
  
  public void setRateDTO(RateDTO rateDTO) {
    this.rateDTO = rateDTO;
  }
  
  public List getList() {
    return list;
  }
  
  public void setList(List list) {
    this.list = list;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    list=new ArrayList();
    rateDTO=new RateDTO();
  }
  
  public String getAdjustBasis() {
    return adjustBasis;
  }
  
  public void setAdjustBasis(String adjustBasis) {
    this.adjustBasis = adjustBasis;
  }

  public String getLatestDate() {
    return latestDate;
  }

  public void setLatestDate(String latestDate) {
    this.latestDate = latestDate;
  }

  public String getLatestDateNex() {
    return latestDateNex;
  }

  public void setLatestDateNex(String latestDateNex) {
    this.latestDateNex = latestDateNex;
  }

}
