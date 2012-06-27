// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   EmailRule.java

package org.xpup.common.util.imp.rule;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xpup.common.util.imp.domn.interfaces.ImpRule;


public class EmailRule extends ImpRule
{

    public EmailRule()
    {
    }

    public boolean setRule(Object context)
    {
        Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{2,15}\\@[a-zA-Z0-9]{0,10}\\.[a-zA-Z0-9]{0,10}");
        String email = null;
        try
        {
            email = (String)context;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
