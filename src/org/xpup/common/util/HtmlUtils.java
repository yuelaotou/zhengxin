package org.xpup.common.util;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.text.StrBuilder;

public final class HtmlUtils {

  public static String getSelectHtml(String name, String optionsValue,
      String defaultValue, boolean nullable, String attributes) {
    Validate.notNull(name, "参数name不能为空!");
    Validate.notNull(optionsValue, "参数optionsValue不能为空!");
    if (StringUtils.trimToNull(defaultValue) == null)
      defaultValue = "";
    if (StringUtils.trimToNull(attributes) == null)
      attributes = "";

    StrBuilder html = new StrBuilder();
    html.appendln("<select name=\"{0}\" id=\"{1}\" {2} >");
    String options[] = StringUtils.split(optionsValue, ",");
    if (nullable) {
      html.appendln("  <option value=\"\">&nbsp;</option>");
    }
    for (int i = 0; i < options.length; i++) {
      String[] pair = StringUtils.split(options[i], "=");
      if (pair.length == 1) {
        pair = new String[] { pair[0], pair[0] };
      }
      String selected = "";
      if (pair[1].equalsIgnoreCase(StringUtils.trimToEmpty(defaultValue))) {
        selected = "selected=\"selected\"";
      }
      html.append("  <option value=\"" + pair[1] + "\" " + selected + ">");
      html.append(pair[0]);
      html.appendln("</option>");
    }
    return MessageFormat.format(html.toString(), new Object[] { name, name,
        attributes });
  }

  public static String getTextHtml(String name, String textValue,
      String attributes) {
    Validate.notNull(name, "参数name不能为空!");
    if (StringUtils.trimToNull(textValue) == null)
      textValue = "";
    if (StringUtils.trimToNull(attributes) == null)
      attributes = "";

    String html = "<input type=\"text\" name=\"{0}\" id=\"{1}\" value=\"{2}\" {3} />";
    return MessageFormat.format(html, new String[] { name, name,
        StringUtils.trimToEmpty(textValue), attributes });
  }

  public static String getLabelHtml(String forName, String label,
      String attributes) {
    if (StringUtils.trimToNull(forName) == null) {
      forName = "";
    } else {
      forName = MessageFormat.format("for=\"{0}\"", new String[] { forName });
    }
    if (StringUtils.trimToNull(label) == null)
      label = "";
    if (StringUtils.trimToNull(attributes) == null)
      attributes = "";

    String html = "<label {0} {1}>{2}</label>";
    return MessageFormat.format(html, new String[] { forName, attributes,
        StringUtils.trimToEmpty(label) });
  }

  public static String getCheckBoxHtml(String name, String attributes) {
    Validate.notNull(name, "参数name不能为空!");
    if (StringUtils.trimToNull(attributes) == null)
      attributes = "";

    String html = "<input type=\"checkbox\" name=\"{0}\" id=\"{1}\" {2} >";
    return MessageFormat.format(html, new String[] { name, name, attributes });
  }

}
