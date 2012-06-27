// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   impDto.java

package org.xpup.common.util.imp.domn.interfaces;

import java.io.Serializable;

public class impDto
    implements Serializable
{

    private int line;

    public impDto()
    {
        line = 0;
    }

    public int getline()
    {
        return line;
    }

    public void setline(int line)
    {
        this.line = line;
    }
}
