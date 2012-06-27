package org.xpup.security.buildtime.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.xpup.security.buildtime.bizsrvc.IMaintainMenuBS;
import org.xpup.security.common.bizsrvc.IMenuDrawer;
import org.xpup.security.common.taglib.SecurityTagBase;

public class EditorMenuTag extends SecurityTagBase {

  private static final long serialVersionUID = 8253696279644450699L;

  protected String service = "securityObjectBS";

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
    IMaintainMenuBS mm = (IMaintainMenuBS) getServiceAccordingToPlugIn(service);
    try {
      IMenuDrawer drawer = (IMenuDrawer) Class.forName(drawerClass)
          .newInstance();
      List menuItems = mm.loadMenuItemTree();
      String result = drawer.draw(menuItems);
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
