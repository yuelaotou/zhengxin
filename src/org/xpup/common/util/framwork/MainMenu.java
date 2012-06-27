// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   MainMenu.java

package org.xpup.common.util.framwork;
import java.sql.SQLException;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.xpup.security.common.dao.MenuItemDAO;

// Referenced classes of package com.stariversoft.common.liuframe.queryframwork:
//            MenuInfo

public class MainMenu 
{
    private static MainMenu menu = null;
    private HashMap map;
    
    private MainMenu()
    {
        map = new HashMap();
    }

    public static MainMenu getInstance()
    {
        if(menu == null)
            return menu = new MainMenu();
        else
            return menu;
    }

//    public synchronized String getMenu()
//        throws Exception
//    {
//        String strMenu = "";
//        strMenu = getModuleMenu();
//        return strMenu;
//    }   

//    private String getModuleMenu()
//        throws Exception
//    {
//        String strModuleMenu = "";
//        List list =getRoleMenu();
//        System.out.println("ss"+list.size());
//        int size = list.size();
//        if(size == 0)
//            return "";
//        for(int i = 0; i < size; i++)
//        {
//            MenuInfo menu = (MenuInfo)list.get(i);
//            String strMenuName = menu.getMenuname().trim();
//            String strMenuID = menu.getMenucode().trim();
//            if(i == 0)
//            {
//                strModuleMenu = strModuleMenu + "<TD valign=\"bottom\" noWrap class=lt0 onmouseover=\"mhHover('msviLocalToolbar', ";
//                strModuleMenu = strModuleMenu + "0, 'lt1')\" onmouseout=\"mhHover('msviLocalToolbar', 0";
//                strModuleMenu = strModuleMenu + ", 'lt0')\" align=\"center\"><A href=\"Menu.jsp?nParentID=" + strMenuID + "\" target=\"leftFrame\" >" + strMenuName + "</A></TD>\r\n";
//            } else
//            {
//                strModuleMenu = strModuleMenu + "<TD valign=\"bottom\"><SPAN class=ltsep>|</SPAN></TD>\r\n";
//                strModuleMenu = strModuleMenu + "<TD valign=\"bottom\" noWrap class=lt0 onmouseover=\"mhHover('msviLocalToolbar', ";
//                strModuleMenu = strModuleMenu + i * 2 + ", 'lt1')\" onmouseout=\"mhHover('msviLocalToolbar', " + i * 2;
//                strModuleMenu = strModuleMenu + ", 'lt0')\" align=\"center\"><A href=\"Menu.jsp?nParentID=" + strMenuID + "\" target=\"leftFrame\" >" + strMenuName + "</A></TD>\r\n";
//            }
//        }
//        
//        return strModuleMenu;
//    }

//    private static ArrayList getRoleMenu()
//        throws Exception
//    {
//        ArrayList list = null;
//        //Session sess = ThreadLocalSessionManager.getSession();
//        try
//        {
//          StringBuffer Hsqlstr = new StringBuffer("select a.id ,a.caption,a.url from MenuItem  a");
//            Query query = sess.createQuery(Hsqlstr.toString());
//            Iterator i = query.iterate();
//            Object object[] = (Object[])null;
//            list = new ArrayList();
//            MenuInfo info;
//            for(; i.hasNext(); list.add(info))
//            {
//                object = (Object[])i.next();
//                info = new MenuInfo();
//                info.setMenucode((String)object[0]);
//                info.setMenuname((String)object[1]);
//            }
//
//        }
//      catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return list;
//    }

    
}
