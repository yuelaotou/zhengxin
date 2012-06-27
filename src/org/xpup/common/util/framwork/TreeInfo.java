// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TreeInfo.java

package org.xpup.common.util.framwork;


public class TreeInfo
{

    private String menucode;
    private String menuname;
    private String url;
    private String haschild;

    public TreeInfo()
    {
    }

    public String getHaschild()
    {
        return haschild;
    }

    public void setHaschild(String haschild)
    {
        this.haschild = haschild;
    }

    public String getMenucode()
    {
        return menucode;
    }

    public void setMenucode(String menucode)
    {
        this.menucode = menucode;
    }

    public String getMenuname()
    {
        return menuname;
    }

    public void setMenuname(String menuname)
    {
        this.menuname = menuname;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
