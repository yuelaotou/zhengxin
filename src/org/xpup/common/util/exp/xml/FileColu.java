// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FileColu.java

package org.xpup.common.util.exp.xml;

import org.xpup.common.util.imp.domn.interfaces.ImpRule;




public class FileColu
{

    private ImpRule checkclass;
    private String name;
    private String typle;
    private int minlength;
    private int maxlength;
    private boolean isnull;
    private int minvalue;
    private int maxvalue;
    private int point;
    private String pattern;

    public FileColu()
    {
        checkclass = null;
        name = null;
        typle = null;
        minlength = 0;
        maxlength = 0;
        isnull = true;
        minvalue = 0;
        maxvalue = 0;
        point = 0;
        pattern = null;
    }

    public boolean isIsnull()
    {
        return isnull;
    }

    public void setIsnull(boolean isnull)
    {
        this.isnull = isnull;
    }

    public int getMaxlength()
    {
        return maxlength;
    }

    public void setMaxlength(int maxlength)
    {
        this.maxlength = maxlength;
    }

    public int getMaxvalue()
    {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue)
    {
        this.maxvalue = maxvalue;
    }

    public int getMinlength()
    {
        return minlength;
    }

    public void setMinlength(int minlength)
    {
        this.minlength = minlength;
    }

    public int getMinvalue()
    {
        return minvalue;
    }

    public void setMinvalue(int minvalue)
    {
        this.minvalue = minvalue;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    public String getTyple()
    {
        return typle;
    }

    public void setTyple(String typle)
    {
        this.typle = typle;
    }

    public ImpRule getCheckclass()
    {
        return checkclass;
    }

    public void setCheckclass(ImpRule checkclass)
    {
        this.checkclass = checkclass;
    }
}
