package org.xpup.hafmis.orgstrct.taglib;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;

public class OrgUnitDrawer {

  public String draw(List orgUnits) {
    StringBuffer script = new StringBuffer();
    script.append("<div id=\"divTree\" style=\"overflow:auto;\"></div>");
    script.append(newLine);
    script.append("<script language=\"javascript\">");
    script.append(newLine);
    script.append("var tree=new alai_tree_help(divTree)");
    script.append(newLine);
    script.append("var root=tree.root");
    script.append(newLine);

    String varible = "menu_";
    int pos = 0;
    Iterator it = orgUnits.iterator();
    while (it.hasNext()) {
      OrganizationUnit orgUnit = (OrganizationUnit) it.next();
      drawOrgUnit(orgUnit, script, varible + pos);
      pos++;
    }
    script.append(newLine);
    script.append("</script>");

    return script.toString();
  }

  private void drawOrgUnit(OrganizationUnit orgUnit, StringBuffer script,
      String varible) {

    String parentVar = varible.substring(0, varible.lastIndexOf("_"));
    // 如果是根菜单则...
    if (orgUnit.isRoot()) {
      String newRoot = "var {0} = root.add(\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\")";
      newRoot = MessageFormat.format(newRoot, new Object[] { varible,
          orgUnit.getId(), orgUnit.getName(), "default", "js",
          "doEditMenuItem(node)" });
      script.append(newRoot);
      script.append(newLine);
    } else { // 如果不是根菜单则...
      String newLeaf = "var {0} = {1}.add(\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\")";
      newLeaf = MessageFormat.format(newLeaf, new Object[] { varible,
          parentVar, orgUnit.getId(), orgUnit.getName(), "default", "js",
          "doEditMenuItem(node)" });
      script.append(newLeaf);
      script.append(newLine);

      if (orgUnit.isLeaf()) {
        return;
      }
    }

    int pos = 0;
    Iterator it = orgUnit.getSubOrgUnits().iterator();
    while (it.hasNext()) {
      OrganizationUnit subOrgUnit = (OrganizationUnit) it.next();
      drawOrgUnit(subOrgUnit, script, varible + "_" + pos);
      pos++;
    }
  }

  private String newLine = System.getProperty("line.separator");

}
