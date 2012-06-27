package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

/**
 * @author Õı“∞ 2007-10-13
 */
public class FloorPOPNewAF extends CriterionsAF {

  private static final long serialVersionUID = 1L;

  private String floorId;

  private String develperName;

  private String floorName;

  private String floorSt;

  private List list = new ArrayList();

  public String getDevelperName() {
    return develperName;
  }

  public void setDevelperName(String develperName) {
    this.develperName = develperName;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public String getFloorSt() {
    return floorSt;
  }

  public void setFloorSt(String floorSt) {
    this.floorSt = floorSt;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.floorId = "";
    this.floorName = "";
  }
}