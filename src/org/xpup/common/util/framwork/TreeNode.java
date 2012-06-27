// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TreeNode.java

package org.xpup.common.util.framwork;

import java.text.ParseException;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


// Referenced classes of package com.stariversoft.common.liuframe.queryframwork:
//            TreeInfo

public class TreeNode
{

    private static TreeNode node = null;
    private HashMap map;
    private String strNode;

    private TreeNode()
    {
        map = new HashMap();
        strNode = "";
    }

    public static TreeNode getInstance()
    {
        if(node == null)
            return new TreeNode();
        else
            return node;
    }

//    public synchronized String getMenu(String parents, String url)
//        throws Exception, ParseException
//    {
//        if(map.containsKey(parents))
//        {
//            strNode = (String)map.get(parents);
//        } else
//        {
//            List list = getMenuNode(parents, url);
//            int size = list.size();
//            if(size > 0)
//                strNode = getNodeString(list, url);
//            else
//                strNode = "";
//            map.put(parents, strNode);
//        }
//        return strNode;
//    }

//    private List getMenuNode(String parents, String url)
//        throws Exception, ParseException
//    {
//        List list;
//        list = null;
//        try
//        {
//            ThreadLocalSessionManager.openSession();
//            if(!parents.equals("LS000"))
//                list = getNode(parents);
//        }
//        catch(Exception he)
//        {
//            he.printStackTrace();
//        }
//        finally
//        {
//            ThreadLocalSessionManager.closeSession();
//        }
//        return list;
//    }
//
//    private List getNode(String parents)
//        throws Exception
//    {
//        ArrayList list = null;
//        Session sess = ThreadLocalSessionManager.getSession();
//        try
//        {
//            StringBuffer Hsqlstr = new StringBuffer("select a.id ,a.caption,a.url from Ca104 as a");
//            //Hsqlstr.append(" where  a.parent_id='4' order by a.id");
//            Query query = sess.createQuery(Hsqlstr.toString());
//            //query.setString(0, parents);
//            Iterator i = query.iterate();
//            Object object[] = (Object[])null;
//            list = new ArrayList();
//            TreeInfo treeinfo;
//            for(; i.hasNext(); list.add(treeinfo))
//            {
//                object = (Object[])i.next();
//                treeinfo = new TreeInfo();
//                treeinfo.setMenucode((String)object[0]);
//                treeinfo.setMenuname((String)object[1]);
//                treeinfo.setUrl((String)object[2]);
//                //treeinfo.setHaschild((String)object[3]);
//            }
//
//        }
//        catch(HibernateException he)
//        {
//            he.printStackTrace();
//        }
//        return list;
//    }

    private String getNodeString(List list, String sSelfURL)
    {
        String sXmlStr = "<?xml version='1.0' encoding='GBK'?>";
        sXmlStr = sXmlStr + "<Root>";
        for(int i = 0; i < list.size(); i++)
        {
            TreeInfo menu = (TreeInfo)list.get(i);
            String sTmpID = menu.getMenucode();
            String sTmpTitle = menu.getMenuname();
            String sTmpURL = menu.getUrl();
            String sHasChild = menu.getHaschild();
            sXmlStr = sXmlStr + "<TreeNode ";
            sXmlStr = sXmlStr + "Title='" + sTmpTitle.trim() + "' ";
            sXmlStr = sXmlStr + "Caption='" + sTmpTitle.trim() + "...' ";
            if(sTmpURL != null && sTmpURL.length() > 0)
            {
                if(sTmpURL.trim().equals("#"))
                    sTmpURL = "";
                sXmlStr = sXmlStr + "Href='" + sTmpURL + "' ";
            }
            if(sHasChild.equals("1"))
            {
                String sTmpDataSrc = sSelfURL + sTmpID;
                sXmlStr = sXmlStr + "NodeXMLSrc='" + sTmpDataSrc.trim() + "' ";
            }
            sXmlStr = sXmlStr + "Target='rightFrame' ";
            sXmlStr = sXmlStr + "/>";
        }

        sXmlStr = sXmlStr + "</Root>";
        return sXmlStr;
    }

}
