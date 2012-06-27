// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   IdentityRule.java

package org.xpup.common.util.imp.rule;

import org.xpup.common.util.imp.domn.interfaces.ImpRule;



public class IdentityRule extends ImpRule
{

    public IdentityRule()
    {
    }

    public boolean setRule(Object context)
    {
        String code = null;
        try
        {
            code = (String)context;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        int length = code.trim().length();
        if(length != 18 && length != 15)
            return false;
        String ok = "0123456789";
        if(length == 15)
        {
            String a = code.substring(0, 6);
            String b = code.substring(6, 15);
            code = a + "19" + b;
        }
        for(int i = 0; i < 17; i++)
        {
            int k = ok.indexOf(code.charAt(i));
            if(k < 0)
                return false;
        }

        String year = code.substring(6, 10);
        String month = code.substring(10, 12);
        String day = code.substring(12, 14);
        int yearint = Integer.parseInt(year);
        int montint = Integer.parseInt(month);
        int dayint = Integer.parseInt(day);
        if(yearint < 1940 || yearint > 2000)
            return false;
        if(montint < 1 || montint > 12)
            return false;
        return dayint >= 1 && dayint <= 31;
    }
}
