package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.util.framwork.MainMenu;
import org.xpup.common.util.framwork.MenuInfo;
import org.xpup.common.util.framwork.TreeInfo;
import org.xpup.common.util.framwork.TreeNode;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.MenuItem;

public class MenuItemDAO extends HibernateDaoSupport {
 
  
  public synchronized String getMenu(final String username,
      final Serializable parentId) throws Exception {
    String strMenu = "";
    strMenu = getModuleMenu(username, parentId);
    return strMenu;
  }

  // 主菜单
  private String getModuleMenu(final String username,
      final Serializable parentId) throws Exception {
    String strModuleMenu = "";
    List list = queryAll(username, parentId);
    int size = list.size();
    if (size == 0)
      return "";
    for (int i = 0; i < size; i++) {
      MenuItem menu = (MenuItem) list.get(i);
      String strMenuName = menu.getCaption().trim();
      String strMenuID = menu.getId().toString().trim();
      if (i == 0) {
        strModuleMenu = strModuleMenu
            + "<TD valign=\"bottom\" noWrap class=lt0 onmouseover=\"mhHover('msviLocalToolbar', ";
        strModuleMenu = strModuleMenu
            + "0, 'lt1')\" onmouseout=\"mhHover('msviLocalToolbar', 0";
        strModuleMenu = strModuleMenu
            + ", 'lt0')\" align=\"center\"><A href=\"Menu.jsp?nParentID="
            + strMenuID + "\" target=\"leftFrame\" >" + strMenuName
            + "</A></TD>\r\n";
      } else {
        strModuleMenu = strModuleMenu
            + "<TD valign=\"bottom\"><SPAN class=ltsep>|</SPAN></TD>\r\n";
        strModuleMenu = strModuleMenu
            + "<TD valign=\"bottom\" noWrap class=lt0 onmouseover=\"mhHover('msviLocalToolbar', ";
        strModuleMenu = strModuleMenu + i * 2
            + ", 'lt1')\" onmouseout=\"mhHover('msviLocalToolbar', " + i * 2;
        strModuleMenu = strModuleMenu
            + ", 'lt0')\" align=\"center\"><A href=\"Menu.jsp?nParentID="
            + strMenuID + "\" target=\"leftFrame\" >" + strMenuName
            + "</A></TD>\r\n";
      }
    }

    return strModuleMenu;
  }

  // 树

  public synchronized String getMenu(final String username,
      final Serializable parentId, String url) 
      throws Exception, ParseException {
    String strNode="";
    HashMap map=new HashMap();
    try{

    if (map.containsKey(parentId)) {
      strNode = (String) map.get(parentId);
    } else {
      List list = queryAll(username,parentId);
      int size = list.size();
      if (size > 0)
        strNode = getNodeString(username,list, url);
      else
        strNode = "";
      map.put(parentId, strNode);
    }  
    }catch(Exception e){
      e.printStackTrace();
    }
    return strNode;
  }

  private String getNodeString(final String username,List list, String sSelfURL) {
    String sXmlStr = "<?xml version='1.0' encoding='GBK'?>";
    sXmlStr = sXmlStr + "<Root>";
    for (int i = 0; i < list.size(); i++) {
      MenuItem menu = (MenuItem) list.get(i);
      String sTmpID = menu.getId().toString();
      String sTmpTitle = menu.getCaption();
      String sTmpURL = menu.getUrl();
      
      List listNote = queryAll(username,sTmpID);
     
      sXmlStr = sXmlStr + "<TreeNode ";
      sXmlStr = sXmlStr + "Title='" + sTmpTitle.trim() + "' ";
      sXmlStr = sXmlStr + "Caption='" + sTmpTitle.trim() + "...' ";
      if (sTmpURL != null && sTmpURL.length() > 0) {
        if (sTmpURL.trim().equals("#"))
          sTmpURL = "";
        sXmlStr = sXmlStr + "Href='" + sTmpURL + "' ";
      }
      if (listNote!=null&&listNote.size()>0) {
        String sTmpDataSrc = sSelfURL + sTmpID;
        sXmlStr = sXmlStr + "NodeXMLSrc='" + sTmpDataSrc.trim() + "' ";
      }
      sXmlStr = sXmlStr + "Target='rightFrame' ";
      sXmlStr = sXmlStr + "/>";
    }

    sXmlStr = sXmlStr + "</Root>";
    return sXmlStr;
  }

  public List queryAllRoot() {
    String hql = "select item from MenuItem item where item.parentMenuItem is null order by item.position";
    return getHibernateTemplate().find(hql);
  }

  public List queryAllRoot(final String username) {

    List roots = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Query query = session.getNamedQuery("allRoots");
        query.setString("username1", username);
        query.setString("username2", username);
        return query.list();
      }
    });
    return roots;
  }

  public List queryAllRootFromMR(final String username) {
    List roots = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select item from MenuItem item, MrRelation mrRelation inner join mrRelation.role role inner join role.users user where item.id = mrRelation.menuItem.id and item.parentMenuItem is null and user.username = :username order by item.position";
        return session.createQuery(hql).setString("username", username).list();
      }
    });
    return roots;
  }

  public List queryAllRootFromMU(final String username) {
    List items = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select item from MenuItem item, MuRelation muRelation inner join muRelation.user user where item.id = muRelation.menuItem.id and item.parentMenuItem is null and user.username = :username order by item.position";
        return session.createQuery(hql).setString("username", username).list();
      }
    });
    return items;
  }

  public List queryAll(final String username, final Serializable parentId) {
    List roots = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Query query = session.getNamedQuery("allSubs");
        query.setString("username1", username);
        query.setString("username2", username);
        query.setParameter("parentId", parentId);
        return query.list();
      }
    });
    return roots;
  }

  public List queryAllFromMR(String username, Serializable parentId) {
    String hql = "select item from MenuItem item, MrRelation mrRelation inner join mrRelation.role role inner join role.users user where item.id = mrRelation.menuItem.id and item.parentMenuItem.id = ? and user.username = ? order by item.position";
    return getHibernateTemplate()
        .find(hql, new Object[] { parentId, username });
  }

  public List queryAllFromMU(String username, Serializable parentId) {
    String hql = "select item from MenuItem item, MuRelation muRelation inner join muRelation.user user where item.id = muRelation.menuItem.id and item.parentMenuItem.id = ? and user.username = ? order by item.position";
    return getHibernateTemplate()
        .find(hql, new Object[] { parentId, username });
  }

  public List queryAllFromMR(Serializable roleId) {
    String hql = "select item from MenuItem item, MrRelation mrRelation inner join mrRelation.role role where item.id = mrRelation.menuItem.id and role.id = ?";
    return getHibernateTemplate().find(hql, roleId);
  }

  public List queryAllFromMU(Serializable userId) {
    String hql = "select item from MenuItem item, MuRelation muRelation inner join muRelation.user user where item.id = muRelation.menuItem.id and user.id = ?";
    return getHibernateTemplate().find(hql, userId);
  }

  public MenuItem queryById(Serializable id) {
    return (MenuItem) getHibernateTemplate().get(MenuItem.class, id);
  }

  public Serializable insert(final MenuItem menuItem) {
    Serializable id = getHibernateTemplate().save(menuItem);
    return id;
  }

  public void deleteById(Serializable id) throws BusinessException {
    MenuItem menuItem = queryById(id);
    if (menuItem == null)
      throw new EntityNotFoundException("菜单不存在，或已经被删除!");
    getHibernateTemplate().delete(menuItem);
  }

  
  public MenuItem queryMenuItem(String parentId) {
    MenuItem menuItem=(MenuItem)getHibernateTemplate().load(MenuItem.class,parentId);
    getHibernateTemplate().flush();
    return menuItem;
  }
  /**
   * 得到父节点
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public MenuItem getParentMenuItemID_WL() throws NumberFormatException,
      Exception {
    MenuItem menuItem = null;
    try {
      menuItem = (MenuItem)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from MenuItem t where t.caption='财务报表'";
          Query query = session.createQuery(hql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return menuItem;
  }
  /**
   * 得到MenuItem
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public MenuItem getMenuItemById_WL(final String id) throws NumberFormatException,
      Exception {
    MenuItem menuItem = null;
    try {
      menuItem = (MenuItem)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from MenuItem t where t.id= ? ";
          Query query = session.createQuery(hql);
          query.setString(0, id);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return menuItem;
  }
  /**
   * 
   * @param menuItemID
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public String getNextPositionValue_WL(final String menuItemID) throws NumberFormatException,
      Exception {
    String content = "";
    try {
      content = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select max(t.position) from MenuItem t where t.parentMenuItem.id='"+menuItemID+"' ";
          Query query = session.createQuery(hql);
          String temp_pos=query.uniqueResult().toString();
          return temp_pos;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }

}
