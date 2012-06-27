// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   XlsFileException.java

package org.xpup.common.util.imp.domn.interfaces.exception;


public class XlsFileException extends Exception
{

    private int line;
    private int col;
    private int Style;

    public XlsFileException(String message)
    {
        super(message);
        line = 0;
        col = 0;
        Style = 0;
    }

    public int getLine()
    {
        return line;
    }

    public void setLine(int line)
    {
        this.line = line;
    }

    public int getCol()
    {
        return col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public int getStyle()
    {
        return Style;
    }

    public void setStyle(int style)
    {
        Style = style;
    }
}
