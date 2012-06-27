// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   XmlFileFactory.java

package org.xpup.common.util.exp;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.xpup.common.util.exp.xml.XmlFile;
import org.xpup.common.util.imp.domn.interfaces.exception.NotFoundClassException;
import org.xpup.common.util.imp.domn.interfaces.exception.TagException;


public class XmlFileFactory
{

    private int fileNum;
    private Map fileMap;

    public XmlFileFactory()
    {
        fileNum = 0;
        fileMap = null;
    }

    public Map getXmlFile(File xmlfile)
    {
        try
        {
            readXml(xmlfile);
        }
        catch(TagException e)
        {
            e.printStackTrace();
        }
        catch(NotFoundClassException e)
        {
            e.printStackTrace();
        }
        return fileMap;
    }

    private void readXml(File xmlfile)
        throws TagException, NotFoundClassException
    {
        SAXBuilder sb = new SAXBuilder();
        fileMap = new HashMap();
        Document doc = null;
        try
        {
            doc = sb.build(xmlfile);
        }
        catch(JDOMException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        Attribute fileA = root.getAttribute("file");
        if(fileA == null)
            throw new TagException("tag error:\"file\" do not found");
        try
        {
            fileNum = fileA.getIntValue();
        }
        catch(DataConversionException e1)
        {
            e1.printStackTrace();
            throw new TagException("tag error:\"file\" its value is error");
        }
        List files = root.getChildren();
        int i = 0;
        int colNum = 0;
        int rowNum = 0;
        int spaceNum = 0;
        for(; i < fileNum; i++)
        {
            Element fileE = (Element)files.get(i);
            Attribute nameA = fileE.getAttribute("name");
            if(nameA == null)
                throw new TagException("tag error:\"name\" do not found");
            Attribute colA = fileE.getAttribute("col");
            if(colA == null)
                throw new TagException("tag error:\"col\" do not found");
            Attribute rowA = fileE.getAttribute("row");
            if(rowA == null)
                throw new TagException("tag error:\"row\" do not found");
            Attribute spaceA = fileE.getAttribute("space");
            if(spaceA == null)
                throw new TagException("tag error:\"space\" do not found");
            String filename = nameA.getValue();
            try
            {
                colNum = colA.getIntValue();
            }
            catch(DataConversionException e2)
            {
                throw new TagException("tag error:\"col\" its value is error");
            }
            try
            {
                rowNum = rowA.getIntValue();
            }
            catch(DataConversionException e3)
            {
                throw new TagException("tag error:\"row\" its value is error");
            }
            try
            {
                spaceNum = spaceA.getIntValue();
            }
            catch(DataConversionException e4)
            {
                throw new TagException("tag error:\"space\" its value is error");
            }
            List columns = fileE.getChildren();
            if(columns == null || columns.size() == 0)
                throw new TagException("tag error:\"column\" do not found");
            List columnList = new ArrayList();
            for(int j = 0; j < colNum; j++)
            {
                Element colnameE = (Element)columns.get(j);
                String name = colnameE.getText();
                if(name == null || name.trim().length() < 1)
                    throw new TagException("tag error:\"column\" do not found");
                columnList.add(name);
            }

            fileMap.put(filename, new XmlFile(filename, colNum, rowNum, spaceNum, columnList));
        }

    }
}
