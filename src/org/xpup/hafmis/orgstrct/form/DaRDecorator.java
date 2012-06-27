package org.xpup.hafmis.orgstrct.form;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.text.StrBuilder;
import org.xpup.common.util.HtmlUtils;
import org.xpup.security.common.domain.DaRelation;

public class DaRDecorator {
  private DaRelation dr = null;

  public DaRDecorator(DaRelation dr) {
    Validate.notNull(dr, "参数dr不能为空!");
    this.dr = dr;
  }

  public String getQueryLevelEnumHtml(String name) {
    String optionsValue = dr.getDataAccess().getQueryLevelEnum();
    String defaultValue = dr.getQueryLevel();
    String attributes = " class=\"text\" onkeydown=\"enterToTab()\" ";
    if(!dr.isSaved())
      attributes += " disabled=\"disabled\" ";
    return HtmlUtils.getSelectHtml(name, optionsValue, defaultValue, false,
        attributes);
  }

  public String getOperationLevelEnumHtml(String name) {
    String optionsValue = dr.getDataAccess().getOperationLevelEnum();
    String defaultValue = dr.getQueryLevel();
    String attributes = " class=\"text\" onkeydown=\"enterToTab()\" ";
    if(!dr.isSaved())
      attributes += " disabled=\"disabled\" ";
    return HtmlUtils.getSelectHtml(name, optionsValue, defaultValue, false,
        attributes);
  }

  public String getLableHtml() {
    String attributes = "";
    /*
     * if (!dr.isSaved()) attributes = "style=\"color:red;\"";
     */
    String label = dr.getDataAccess().getName();
    return HtmlUtils.getLabelHtml("", label, attributes);
  }

  public String getCheckBoxHtml(String name) {
    String attributes = "onclick=\"enable(this)\" ";
    if (dr.isSaved())
      attributes += "checked=\"checked\"";
    return HtmlUtils.getCheckBoxHtml(name, attributes);
  }

  public String getWholeHtml(String checkName, String queryName, String opName) {
    StrBuilder html = new StrBuilder();
    html.appendln("<tr>");
    html.append("  <td>");
    html.append(getCheckBoxHtml(checkName));
    html.appendln("</td>");
    html.append("  <td>");
    html.append(getLableHtml());
    html.appendln("</td>");
    html.append("  <td>");
    html.append(getQueryLevelEnumHtml(queryName));
    html.appendln("</td>");
    html.append("  <td>");
    html.append(getOperationLevelEnumHtml(opName));
    html.appendln("</td>");
    html.appendln("</tr>");
    return html.toString();
  }
}
