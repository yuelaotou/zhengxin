package org.xpup.hafmis.orgstrct.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOrgUnitBS;
import org.xpup.security.common.taglib.SecurityTagBase;

public class OrgUnitTag extends SecurityTagBase {

  private static final long serialVersionUID = 8253696279644450699L;

  protected String service = "orgStructureBS";

  protected String drawerClass = "org.xpup.security.buildtime.taglib.EditorMenuDrawer";

  public String getDrawerClass() {
    return drawerClass;
  }

  public void setDrawerClass(String drawerClass) {
    this.drawerClass = drawerClass;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public int doEndTag() throws JspException {
    IMaintainOrgUnitBS mm = (IMaintainOrgUnitBS) getServiceAccordingToPlugIn(service);
    try {
      OrgUnitDrawer drawer = new OrgUnitDrawer();
      List orgUnits = mm.loadOrgUnitTree();
      String result = drawer.draw(orgUnits);
      JspWriter out = pageContext.getOut();
      out.write(result);
    } catch (Exception ex) {
      throw new JspException(ex);
    }
    return EVAL_PAGE;
  }

  public int doStartTag() throws JspException {
    return SKIP_BODY;
  }
}
