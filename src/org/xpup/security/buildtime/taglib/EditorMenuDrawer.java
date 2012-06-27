package org.xpup.security.buildtime.taglib;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.xpup.security.common.bizsrvc.IMenuDrawer;
import org.xpup.security.common.domain.MenuItem;

public class EditorMenuDrawer implements IMenuDrawer {

  public String draw(List menuItems) {
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
    Iterator it = menuItems.iterator();
    while (it.hasNext()) {
      MenuItem menuItem = (MenuItem) it.next();
      drawMenuItem(menuItem, script, varible + pos);
      pos++;
    }
    script.append(newLine);
    script.append("</script>");

    return script.toString();
  }

  private void drawMenuItem(MenuItem menuItem, StringBuffer script,
      String varible) {

    String parentVar = varible.substring(0, varible.lastIndexOf("_"));
    // 如果是根菜单则...
    if (menuItem.isRoot()) {
      String newRoot = "var {0} = root.add(\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\")";
      newRoot = MessageFormat.format(newRoot, new Object[] { varible,
          menuItem.getId(), menuItem.getCaption(), "default", "js",
          "doEditMenuItem(node)" });
      script.append(newRoot);
      script.append(newLine);
    } else { // 如果不是根菜单则...
      String newLeaf = "var {0} = {1}.add(\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\")";
      newLeaf = MessageFormat.format(newLeaf, new Object[] { varible,
          parentVar, menuItem.getId(), menuItem.getCaption(), "default", "js",
          "doEditMenuItem(node)" });
      script.append(newLeaf);
      script.append(newLine);

      if (menuItem.isLeaf()) {
        return;
      }
    }

    int pos = 0;
    Iterator it = menuItem.getSubMenuItems().iterator();
    while (it.hasNext()) {
      MenuItem subMenuItem = (MenuItem) it.next();
      drawMenuItem(subMenuItem, script, varible + "_" + pos);
      pos++;
    }
  }

  private String newLine = System.getProperty("line.separator");

}
