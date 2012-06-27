// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FileReader.java

package org.xpup.common.util;

import java.io.*;
import java.util.*;

public class FileReader extends BufferedReader
{

    private boolean triming;
    private String delimiter;
    ArrayList biglist;

    public FileReader(InputStream inputStream)
    {
        super(new InputStreamReader(inputStream));
        triming = true;
        delimiter = ",";
        biglist = new ArrayList();
    }

    public FileReader(String filename)
        throws FileNotFoundException
    {
        super(new InputStreamReader(new FileInputStream(filename)));
        triming = true;
        delimiter = ",";
        biglist = new ArrayList();
    }

    public FileReader(File file)
        throws FileNotFoundException
    {
        super(new InputStreamReader(new FileInputStream(file)));
        triming = true;
        delimiter = ",";
        biglist = new ArrayList();
    }

    public FileReader(Reader in)
    {
        super(in);
        triming = true;
        delimiter = ",";
        biglist = new ArrayList();
    }

    public FileReader(Reader in, int sz)
    {
        super(in, sz);
        triming = true;
        delimiter = ",";
        biglist = new ArrayList();
    }

    public List getList()
        throws IOException
    {
      try{
        int size = getColSize();
        if(size == 0)
        {
            biglist = null;
        } else
        {
            for(String c[] = new String[size + 1]; (c = readRow(size)) != null;)
            {
                ArrayList list = new ArrayList();
                for(int i = 1; i < size + 1; i++)
                    list.add(c[i]);

                biglist.add(list);
            }
        }
      }catch(Exception e){
        e.printStackTrace();
      }
        return biglist;
    }

    public int getColSize()
        throws IOException
    {
        int size = 0;
        ArrayList list = new ArrayList();
        String line = readLine();
        int idx[] = new int[2];
        idx[0] = 0;
        idx[1] = 0;
        if(line == null)
            return 0;
        line = line + getDelimiter();
        for(int i = 0; idx[1] < line.lastIndexOf(getDelimiter()); i++)
        {
            idx[1] = line.indexOf(getDelimiter(), idx[0]);
            String str = getSubstring(line, idx[0], idx[1]);
            size++;
            list.add(str);
            idx[0] = idx[1] + 1;
        }

        biglist.add(list);
        return size;
    }

    public String[] readRow(int columnSize)
        throws IOException
    {
        String column[] = new String[columnSize + 1];
        String line = readLine();
        if(line == null)
            return null;
        line = line + getDelimiter();
        int delimiterCount = 0;
        int idx[] = new int[2];
        idx[0] = 0;
        idx[1] = 0;
        for(int i = 1; i <= columnSize && idx[1] < line.lastIndexOf(getDelimiter()); i++)
        {
            delimiterCount++;
            idx[1] = line.indexOf(getDelimiter(), idx[0]);
            column[i] = getSubstring(line, idx[0], idx[1]);
            idx[0] = idx[1] + 1;
        }

        return column;
    }

    public ArrayList readColumn(String xk_param)
        throws IOException
    {
        ArrayList parameter = null;
        parameter = new ArrayList();
        while(xk_param.length() > 0) 
        {
            int index = xk_param.indexOf(",");
            String tmp = xk_param.substring(0, index);
            if(tmp != null && tmp.length() > 0)
            {
                parameter.add(tmp);
                xk_param = xk_param.substring(index + 1);
            }
        }
        return parameter;
    }

    public static final ArrayList readColumnValue(String line)
    {
        ArrayList list = new ArrayList();
        if((line == null) | (line.length() <= 0))
            return list;
        for(StringTokenizer tok = new StringTokenizer(line, "/t", true); tok.hasMoreTokens();)
        {
            String token = tok.nextToken().trim();
            if(!token.equals("") && !token.equals("/t"))
                list.add(token);
        }

        return list;
    }

    private String getSubstring(String line, int idx0, int idx1)
    {
        if(triming)
            return line.substring(idx0, idx1).trim();
        else
            return line.substring(idx0, idx1);
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(String delimiter)
    {
        this.delimiter = delimiter;
    }

    public boolean isTriming()
    {
        return triming;
    }

    public void setTriming(boolean b)
    {
        triming = b;
    }
}
