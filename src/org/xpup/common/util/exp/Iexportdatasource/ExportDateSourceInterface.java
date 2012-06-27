package org.xpup.common.util.exp.Iexportdatasource;

import java.io.File;

import java.util.List;

public interface ExportDateSourceInterface {

  public void makeFile(File xmlfile,String xlsfile,String orgunitcode,List list) throws Exception;
}
