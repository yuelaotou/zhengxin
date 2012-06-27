package org.xpup.hafmis.orgstrct.form;

import org.apache.commons.lang.StringUtils;
import org.xpup.common.util.HtmlUtils;
import org.xpup.hafmis.orgstrct.domain.OrgUnitProperty;
import org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum;

public class OupDecorator {

  private OrgUnitProperty oup = null;

  public OupDecorator(OrgUnitProperty oup) {
    this.oup = oup;
  }

  public String getHtmlComponent(String name) {
    String html = null;
    ValueTypeEnum valueType = oup.getOuptItem().getValueType();
    if (ValueTypeEnum.ENUM.equals(valueType)) {
      String optionsValue = oup.getOuptItem().getEnumValue();
      String defaultValue = oup.getValue();
      String attributes = " class=\"text\" onkeydown=\"enterToTab()\" ";
      boolean nullable = oup.getOuptItem().isNullable();
      html = HtmlUtils.getSelectHtml(name, optionsValue, defaultValue,
          nullable, attributes);
    } else {
      String textValue = oup.getValue();
      String attributes = " class=\"text\" onkeydown=\"enterToTab()\" ";
      html = HtmlUtils.getTextHtml(name, textValue, attributes);
    }
    return html;
  }

  public String getHtmlLabel(String forName) {
    String attributes = "";
    if (!oup.isSaved())
      attributes = "style=\"color:red;\"";
    String label = oup.getOuptItem().getName() + "£∫";
    return HtmlUtils.getLabelHtml(forName, label, attributes);
  }

  public String getWholeHtml(String name) {
    StringBuffer html = new StringBuffer();
    html.append("<th>");
    html.append(getHtmlLabel(name));
    html.append("</th>");
    html.append(newLine);
    html.append("<td>");
    html.append(getHtmlComponent(name));
    html.append("</td>");
    html.append(newLine);
    return html.toString();
  }

  public String validate() {
    if (!oup.getOuptItem().isNullable()
        && StringUtils.trimToNull(oup.getValue()) == null)
      return oup.getOuptItem().getName() + " ±ÿ–ÎÃÓ–¥°£";
    return null;
  }

  private String newLine = System.getProperty("line.separator");
}
