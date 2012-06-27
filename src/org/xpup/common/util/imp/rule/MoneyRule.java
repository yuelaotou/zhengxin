// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   EmailRule.java

package org.xpup.common.util.imp.rule;
public class MoneyRule
{

    public MoneyRule()
    {
    }

    public boolean setRule(Object context,int total,int decimalCount)
    {
      try
      {
        String nums=context.toString();
        if(nums.indexOf(".")==-1)
        {
          if(nums.length()<=total)return true; 
          else return false;
        }else
        {
          if(nums.indexOf(".")<=total)
          {
            if(nums.length()-nums.indexOf(".")<=decimalCount+1)     
              return true;
            else
              return false; 
          }        
          else return false;
        }
      }
      catch(Exception e)
      {
        e.printStackTrace();
        return false;
      }
    
    }
}
