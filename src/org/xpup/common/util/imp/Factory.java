// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Factory.java

package org.xpup.common.util.imp;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import org.xpup.common.util.imp.domn.interfaces.ImpRule;
import org.xpup.common.util.imp.domn.interfaces.impDto;
import org.xpup.common.util.imp.domn.interfaces.exception.NotFoundClassException;
import org.xpup.common.util.imp.domn.interfaces.exception.XlsFileException;
import org.xpup.common.util.imp.xml.FileColu;
import org.xpup.common.util.imp.xml.XmlFile;


import jxl.*;  
import jxl.read.biff.BiffException;

// Referenced classes of package com.imp:
//            XmlFileFactory

public class Factory
{

    private Map xmlfileMap;
    private Map infoMap;
    private String packagename;
    public static final int ERRORFLAG_LENGTH = 0;
    public static final int ERRORFLAG_NULL = 1;
    public static final int ERRORFLAG_VALUE = 2;
    public static final int ERRORFLAG_RULE = 3;


    public Factory()
    {
        xmlfileMap = null;
        infoMap = null;
        packagename = null;
    }

    public Map getInfomation(File xmlfile, InputStream stream, String packagename)
        throws XlsFileException, BiffException
    {
        this.packagename = packagename;
        getXmlFile(xmlfile);
        try
        {
            readXls(stream);
        }
        catch(XlsFileException e)
        {
            throw e;
        }
        return infoMap;
    }

    private void getXmlFile(File xmlfile)
    {
        XmlFileFactory xmlFileFactory = new XmlFileFactory();
        xmlfileMap = xmlFileFactory.getXmlFile(xmlfile);
    }

    private void readXls(InputStream stream)
        throws XlsFileException, BiffException
    {
        {
            infoMap = new HashMap();
            Set keys = xmlfileMap.keySet();
            Iterator iterator = keys.iterator();
            Workbook rwb = null;
            IOException e;
            try
            {
                rwb = Workbook.getWorkbook(stream);
            }
            // Misplaced declaration of an exception variable
            catch(IOException e1)
            {
                throw new XlsFileException("XlsFile error:xls file do not got");
            }
            try
            {
                String key;
                List dtoList;
                for(; iterator.hasNext(); infoMap.put(key, dtoList))
                {
                    key = (String)iterator.next();
                    XmlFile xmlFile = (XmlFile)xmlfileMap.get(key);
                    dtoList = null;
                    try
                    {
                        dtoList = setDto(rwb, key, xmlFile);
                    }
                    catch(XlsFileException xlsFileException)
                    {
                        throw xlsFileException;
                    }
                    catch(NotFoundClassException notFoundClassException)
                    {
                      notFoundClassException.printStackTrace();
                    }
                }

//                break label0;
            }
            finally
            {
                if(rwb != null)
                    rwb.close();
            }
            
        }

    }

    private List setDto(Workbook rwb, String key, XmlFile xmlFile)
        throws XlsFileException, NotFoundClassException
    {
        List dtoList = new ArrayList();
        Sheet rs = rwb.getSheet(0);
        List ColnamenewList = xmlFile.getFileColu();
        int colNum = xmlFile.getColNum();
        int rowNum = xmlFile.getRowNum();
        int space = xmlFile.getSpace();
        String xmlname = xmlFile.getName();
        String dtoroad = packagename + key + "DTO";
        Class c = null;
        try
        {
            c = Class.forName(dtoroad);
        }
        catch(ClassNotFoundException e1)
        {
            throw new NotFoundClassException("Dto load error!! about packagename is error or not found DTO");
        }
        if(rowNum < 0)
        {
            int rowtemp = rs.getRows();
            rowNum = rowtemp - space;
        }
        for(int j = 0; j < rowNum; j++)
        { 
            Cell cellstemp[] = rs.getRow(space + j);
            Cell cells[] = new Cell[colNum];
            for(int k = 0; k < cellstemp.length; k++){
                cells[k] = cellstemp[k];
          
            }
            createDto(c, colNum, space, cells, ColnamenewList, j, dtoList);
        }

        return dtoList;
    }

    private void createDto(Class c, int colNum, int space, Cell cells[], List ColnamenewList, int j, List dtoList)
        throws XlsFileException, NotFoundClassException
    {
        impDto dto = null;
        try
        {
            dto = (impDto)c.newInstance();
        }
        catch(InstantiationException e2)
        {
            e2.printStackTrace();
        }
        catch(IllegalAccessException e2)
        {
            e2.printStackTrace();
        }
        for(int i = 0; i < colNum; i++)
        {
            String context = null;
            if(cells[i] != null)
                context = cells[i].getContents();
            Object realvalue = null;
            FileColu coln=null;
            try{
              coln= (FileColu)ColnamenewList.get(i);
            }catch(Exception e){
              e.printStackTrace();
            }
            String colname = coln.getName();
            String colstyle = coln.getTyple();
            String error = "XlsFile is error: line:" + (space + j + 1) + " colu:" + (i + 1);
            Class parasType[] = new Class[1];
            if(colstyle.equals("java.lang.String"))
            {
                      realvalue = context;
                      parasType[0]=java.lang.String.class;
            }
            if(colstyle.equals("java.lang.Integer"))
            {
              realvalue = context;  
              int value = 0;
              value = Integer.parseInt(context.trim());
              realvalue = new Integer(value);
              parasType[0]= java.lang.Integer.class;
            }
            if(colstyle.equals("java.lang.Double"))
            {
              parasType[0]=java.lang.Double.class;
            }
            if(colstyle.equals("java.util.Date"))
            {
              parasType[0]=java.util.Date.class;
            }
            ImpRule checkclass = coln.getCheckclass();
            if(checkclass != null)
            {
                checkclass.setIsnull(coln.isIsnull());
                if(!checkclass.check(realvalue))
                {
                    XlsFileException a = new XlsFileException(error + " is matched rule!");
                    a.setLine(space + j + 1);
                    a.setCol(i + 1);
                    a.setStyle(3);
                    throw a;
                }
            }
            String method = "set" + colname;
            Method m = null;
            try
            {
                m = c.getMethod(method,parasType);
            }
            catch(SecurityException e3)
            {
                e3.printStackTrace();
            }
            catch(NoSuchMethodException e3)
            {
                throw new NotFoundClassException("Dto method error!! Dto's method is same as xml");
            } 
            Object args[] = new Object[1];
            args[0] = realvalue;
            try
            {
                m.invoke(dto, args);
            }
            catch(IllegalArgumentException e4)
            {
                e4.printStackTrace();
            }
            catch(IllegalAccessException e4)
            {
                e4.printStackTrace();
            }
            catch(InvocationTargetException e4)
            {
                e4.printStackTrace();
            }
        }

        dto.setline(space + j + 1);
        dtoList.add(dto);
    }
}
