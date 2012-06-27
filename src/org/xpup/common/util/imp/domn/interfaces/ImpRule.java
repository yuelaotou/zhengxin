// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ImpRule.java

package org.xpup.common.util.imp.domn.interfaces;


public abstract class ImpRule
{

    private boolean isnull;

    public ImpRule()
    {
        isnull = false;
    }

    public void setIsnull(boolean isnull)
    {
        this.isnull = isnull;
    }

    public final boolean check(Object context)
    {
        if(isnull)
        {
            if(context == null)
                return true;
        } else
        if(context == null)
            return false;
        return setRule(context);
    }

    public abstract boolean setRule(Object obj);
    
}
