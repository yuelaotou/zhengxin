// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Factory.java

package org.xpup.common.util.exp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;
import org.xpup.common.util.exp.xml.XmlFile;
import org.xpup.common.util.imp.domn.interfaces.exception.NotFoundClassException;
import org.xpup.common.util.imp.domn.interfaces.exception.XlsFileException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

// Referenced classes of package com.exp:
//            XmlFileFactory

public class Factory {

  private Map xmlfileMap;

  private Map infoMap;

  private String packagename;

  private Map dtomap;

  public static final int ERRORFLAG_LENGTH = 0;

  public static final int ERRORFLAG_NULL = 1;

  public static final int ERRORFLAG_VALUE = 2;

  public static final int ERRORFLAG_RULE = 3;

  public Factory() {
    xmlfileMap = null;
    infoMap = null;
    packagename = null;
    dtomap = null;
  }

  public void setInfomation(File xmlfile, String xlsfile, Map dtomap,
      String packagename) throws XlsFileException {
    this.packagename = packagename;
    this.dtomap = dtomap;
    getXmlFile(xmlfile);
    try {
      writeXls(xlsfile);
    } catch (XlsFileException e) {
      throw e;
    } catch (NotFoundClassException e) {
      e.printStackTrace();
    }
  }

  private void getXmlFile(File xmlfile) {
    XmlFileFactory xmlFileFactory = new XmlFileFactory();
    xmlfileMap = xmlFileFactory.getXmlFile(xmlfile);
  }

  private void writeXls(String xlsfile) throws XlsFileException,
      NotFoundClassException {
    infoMap = new HashMap();
    Set keys = xmlfileMap.keySet();

    Set keymap = dtomap.keySet();

    Iterator iterator = keys.iterator();
    WritableWorkbook wwb = null;
    WritableSheet rs = null;
    File file = new File(xlsfile);
    if (file.exists())
      file.delete();
    try {
      wwb = Workbook.createWorkbook(file);
      rs = wwb.createSheet("金软科技", 0);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    try {
      while (iterator.hasNext()) {
        String key = (String) iterator.next();

        if (!keymap.contains(key))
          throw new NotFoundClassException("Dto is match with xml file tag");
        XmlFile xmlFile = (XmlFile) xmlfileMap.get(key);
        List dtolist = (List) dtomap.get(key);// ExportTitleDto返回;
        try {
          getDto(rs, key, xmlFile, dtolist);
        } catch (XlsFileException e) {
          throw e;
        } catch (NotFoundClassException e) {
          e.printStackTrace();
        }
      }
    } catch (NotFoundClassException e) {
      throw e;
    } finally {
      try {
        wwb.write();
      } catch (IOException e2) {
        e2.printStackTrace();
      }
      try {
        if (wwb != null)
          wwb.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return;
  }

  private void getDto(WritableSheet rs, String key, XmlFile xmlFile,
      List dtoList) throws XlsFileException, NotFoundClassException {
    List ColnamenewList = xmlFile.getFileColu();
    int colNum = xmlFile.getColNum();
    int rowNum = xmlFile.getRowNum();
    int space = xmlFile.getSpace();
    String xmlname = xmlFile.getName();
    String dtoroad = packagename + key + "DTO";

    Class c = null;
    try {
      c = Class.forName(dtoroad);

    } catch (ClassNotFoundException e1) {
      throw new NotFoundClassException(
          "Dto load error!! about packagename is error or not found Dto");
    }
    ExpDto dto = null;
    try {
      dto = (ExpDto) c.newInstance();
    } catch (InstantiationException e2) {
      e2.printStackTrace();
    } catch (IllegalAccessException e2) {
      e2.printStackTrace();
    }
    if (rowNum < 0 || rowNum > dtoList.size())
      rowNum = dtoList.size();

    for (int j = 0; j < rowNum; j++) {
      dto = (ExpDto) dtoList.get(j);
      createCell(colNum, space, ColnamenewList, j, dto, rs);
    }

  }

  private void createCell(int colNum, int space, List ColnamenewList, int j,
      ExpDto dto, WritableSheet rs) throws XlsFileException,
      NotFoundClassException {
    for (int i = 0; i < colNum; i++) {
      String info = dto.getInfo((String) ColnamenewList.get(i));// 他会根据xml中的列名提取出相应的值（ExportContentDto.java）
      if (info == null)
        info = "";
      Label labelC = new Label(i, j + space, info);
      try {
        rs.addCell(labelC);
      } catch (RowsExceededException e) {
        e.printStackTrace();
      } catch (WriteException e) {
        e.printStackTrace();
      }
    }

  }
}
