package org.xpup.security.wsf.taglib;

import java.io.Serializable;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.taglib.TagUtils;
import org.xpup.security.common.taglib.SecurityTagBase;
import org.xpup.security.wsf.bizsrvc.IMenuControlBS;

public class MenuSelectorTag extends SecurityTagBase {

  private static final long serialVersionUID = 7943404486872397624L;

  private String name = null;

  private String scope = null;

  private String accordingTo = null;

  private String service = "securityControlBS";

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getAccordingTo() {
    return accordingTo;
  }

  public void setAccordingTo(String accordingTo) {
    this.accordingTo = accordingTo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int doEndTag() throws JspException {

    Serializable id = (Serializable) TagUtils.getInstance().lookup(pageContext,
        name, scope);
    if (id == null) {
      return (EVAL_PAGE);
    }

    IMenuControlBS menuControl = (IMenuControlBS) this.getService(service);
    try {
      List all = menuControl.loadMenuItemTree();
      List owned = null;
      if ("user".equals(accordingTo)) {
        owned = menuControl.findAllMenuItemsByUserId(id);
      } else if ("role".equals(accordingTo)) {
        owned = menuControl.findAllMenuItemsByRoleId(id);
      } else {
        throw new JspException("accordingTo ±ÿ–Î «userªÚrole£°");
      }
      MenuSelectorDrawer drawer = new MenuSelectorDrawer();
      String result = drawer.draw(all, owned);
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
