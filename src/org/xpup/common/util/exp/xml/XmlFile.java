// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   XmlFile.java

package org.xpup.common.util.exp.xml;

import java.util.List;

public class XmlFile
{

    private List FileColu;
    private String name;
    private int colNum;
    private int rowNum;
    private int space;

    public XmlFile(String name, int colNum, int rowNum, int space, List FileColu)
    {
        this.FileColu = null;
        this.name = null;
        this.colNum = 0;
        this.rowNum = 0;
        this.space = 0;
        this.name = name;
        this.colNum = colNum;
        this.rowNum = rowNum;
        this.space = space;
        this.FileColu = FileColu;
    }

    public int getSpace()
    {
        return space;
    }

    public void setSpace(int space)
    {
        this.space = space;
    }

    public List getFileColu()
    {
        return FileColu;
    }

    public void setFileColu(List FileColu)
    {
        this.FileColu = FileColu;
    }

    public int getColNum()
    {
        return colNum;
    }

    public void setColNum(int colNum)
    {
        this.colNum = colNum;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getRowNum()
    {
        return rowNum;
    }

    public void setRowNum(int rowNum)
    {
        this.rowNum = rowNum;
    }
}
