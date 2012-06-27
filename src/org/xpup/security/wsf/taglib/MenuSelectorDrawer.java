package org.xpup.security.wsf.taglib;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.xpup.security.common.domain.MenuItem;

public class MenuSelectorDrawer implements Serializable {

  private static final long serialVersionUID = -4409110570834493138L;

  private Set owned = new HashSet();
  
  public int  system_1=0;
  
  public int system_2=0;
  
  public int system_3=0;
  
  public int system_4=0;
  
  public String system_type_1="-1";
  
  public String system_type_2="-1";
  
  public String system_type_3="-1";
  
  public String system_type_4="-1";
  
  public String type="-1";
  
  public List list=new ArrayList();

  private String newLine = System.getProperty("line.separator");
  //label1.innerHTML="<input type='checkBox' onclick='checks(this,"+'"'+count_var+'"'+")'>";
  public String draw(List all, List owned) {
    List menuItems = all;
    this.owned.addAll(owned);

    StringBuffer script = new StringBuffer();
    script.append("<div id=\"divTree\" style=\"overflow:auto;\"></div>");
    script.append(newLine);
    script.append("<script language=\"javascript\">");
    script.append(newLine);
    script.append("var tree=new alai_tree_help(divTree)");
    script.append(newLine);
    script.append("var root=tree.root");
    script.append(newLine);
    
    
   
//    script.append("<div id="+'"'+"board"+'"'+"></div>;");
//    script.append(newLine);
 
   
    String varible = "menu_";
    int pos = 0;
    Iterator it = menuItems.iterator();
    while (it.hasNext()) {
      MenuItem menuItem = (MenuItem) it.next();
      drawMenuItem(menuItem, script, varible + pos);
      pos++;
    }
    List list_system=new ArrayList();
    list_system.add(String.valueOf(system_1));
    list_system.add(String.valueOf(system_2));
    list_system.add(String.valueOf(system_3));
    list_system.add(String.valueOf(system_4));
    for(int i=0;i<4;i++){
      script.append(newLine);
      String b="var board = document.getElementById("+'"'+"divTree"+'"'+");";
      script.append(b);
      script.append(newLine);
      String c="var e = document.createElement("+'"'+"input"+'"'+");";
      script.append(c);
      script.append(newLine);
      String d="e.type="+'"'+"hidden"+'"';
      script.append(d);
      script.append(newLine);
      String g="e.value="+'"'+String.valueOf(list_system.get(i))+'"';
      script.append(g);
      script.append(newLine);
      String h="e.id="+'"'+"system_"+(i+1)+'"';
      script.append(h);
      script.append(newLine);
      String e="var obj=board"+".appendChild("+"e)";
      script.append(e);
      script.append(newLine);
    }
    for(int i=0;i<4;i++){
      script.append(newLine);
      String b="var board = document.getElementById("+'"'+"divTree"+'"'+");";
      script.append(b);
      script.append(newLine);
      String c="var e = document.createElement("+'"'+"input"+'"'+");";
      script.append(c);
      script.append(newLine);
      String d="e.type="+'"'+"hidden"+'"';
      script.append(d);
      script.append(newLine);
      String g="e.value="+'"'+String.valueOf(list.get(i))+'"';
      script.append(g);
      script.append(newLine);
      String h="e.id="+'"'+"system_type_"+(i+1)+'"';
      script.append(h);
      script.append(newLine);
      String e="var obj=board"+".appendChild("+"e)";
      script.append(e);
      script.append(newLine);
    }
   
    script.append("</script>");
    return script.toString();
  }

  private void drawMenuItem(MenuItem menuItem, StringBuffer script,
      String varible) {
  
    boolean checked = isMenuItemSelected(menuItem);
    if(menuItem.getOpSystemType().equals("0")&&menuItem.getUrl()!=null){
      system_1++;
    }
    if(menuItem.getOpSystemType().equals("1")&&menuItem.getUrl()!=null){
      system_2++;
    }
    if(menuItem.getOpSystemType().equals("2")&&menuItem.getUrl()!=null){
      system_3++;
    }
    if(menuItem.getOpSystemType().equals("3")&&menuItem.getUrl()!=null){
      system_4++;
    }
//    if(!menuItem.getOpSystemType().equals(String.valueOf(type))){
//      if(type.equals("-1")){
//        system_type_1=menuItem.getOpSystemType();
//        type=system_type_1;
//      }
//      type=menuItem.getOpSystemType();
//      if(!type.equals("-1")&&!system_type_1.equals("-1")&&!type.equals(system_type_1)&&system_type_3.equals("-1")&&system_type_4.equals("-1")){
//        system_type_2=menuItem.getOpSystemType();
//      }
//      if(!type.equals("-1")&&!system_type_1.equals("-1")&&!type.equals(system_type_2)&&!system_type_2.equals("-1")&&system_type_4.equals("-1")&&system_type_4.equals("-1")){
//        system_type_2=menuItem.getOpSystemType();
//      }
//      
//      type=menuItem.getOpSystemType();
   
    String parentVar = varible.substring(0, varible.lastIndexOf("_"));
    // 如果是叶子菜单但不是根菜单，则...
    if (menuItem.isLeaf() && !menuItem.isRoot()) {
      String newLeaf = "var {0} = tree.addChkNode({1},\"{2}\",\"{3}\",null,null,null,{4},\"{5}\")";

      newLeaf = MessageFormat.format(newLeaf, new Object[] { varible,
          parentVar, menuItem.getId(), menuItem.getCaption(),
          String.valueOf(checked),menuItem.getParentMenuItem().getId() });

      script.append(newLeaf);
      script.append(newLine);
      return;
    }

    // 如果是根菜单但不是叶子菜单，则...
    if (menuItem.isRoot() && !menuItem.isLeaf()) {
      list.add(menuItem.getOpSystemType());
      String newRoot = "var {0} = root.add(\"{1}\",\"{2}\")";
      newRoot = MessageFormat.format(newRoot, new Object[] { varible,
          menuItem.getId(), menuItem.getCaption() });
      script.append(newRoot);
      script.append(newLine);
    }
    // 如果既是根菜单又是叶子菜单，则...
    else if (menuItem.isRoot() && menuItem.isLeaf()) {
      String newRoot = "var {0} = tree.addChkNode({1},\"{2}\",\"{3}\",null,null,null,{4})";
      newRoot = MessageFormat.format(newRoot, new Object[] { varible, "root",
          menuItem.getId(), menuItem.getCaption(), String.valueOf(checked) });

      script.append(newRoot);
      script.append(newLine);
    }
    // 如果是带有子菜单的子菜单，则...
    else {
      String newMiddle = "var {0} = {1}.add(\"{2}\",\"{3}\")";
      newMiddle = MessageFormat.format(newMiddle, new Object[] { varible,
          parentVar, menuItem.getId(), menuItem.getCaption() });

      script.append(newMiddle);
      script.append(newLine);
    }

    int pos = 0;
    Iterator it = menuItem.getSubMenuItems().iterator();
    while (it.hasNext()) {
      MenuItem subMenuItem = (MenuItem) it.next();
      drawMenuItem(subMenuItem, script, varible + "_" + pos);
      pos++;
    }
  }

  private boolean isMenuItemSelected(MenuItem menuItem) {
    return owned.contains(menuItem);
  }
}
