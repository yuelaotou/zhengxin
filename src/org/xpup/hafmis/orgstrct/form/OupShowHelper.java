package org.xpup.hafmis.orgstrct.form;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.xpup.hafmis.orgstrct.domain.OrgUnitProperty;

public class OupShowHelper {

  public String show(List oups, String property) {
    Validate.notNull(oups, "参数oups不能为空！");

    StringBuffer html = new StringBuffer();
    for (int i = 0; i < oups.size() + 1; i += 2) {
      if (i >= oups.size())
        break;

      html.append("<tr>");
      html.append(newLine);
      OrgUnitProperty oup = (OrgUnitProperty) oups.get(i);
      html.append(new OupDecorator(oup).getWholeHtml(property + "[" + i + "]"));
      if ((i + 1) > (oups.size() - 1)) {
        html.append("<td colspan=\"2\"></td>");
      } else {
        oup = (OrgUnitProperty) oups.get(i + 1);
        html.append(new OupDecorator(oup).getWholeHtml(property + "[" + (i + 1)
            + "]"));
      }
      html.append("</tr>");
      html.append(newLine);
    }
    return html.toString();
  }

  private String newLine = System.getProperty("line.separator");
}
