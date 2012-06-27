package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.sysfinance.accmng.listacc.dto.ListaccHeadDTO;

public class checkupaccexport_expDataSource implements ExportDateSourceInterface {
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
      Factory faxtory = new Factory();
      List list = new ArrayList();
      ListaccHeadDTO dto = new ListaccHeadDTO();
      dto.setCredenceDate("凭证日期");
      dto.setCredenceNum("凭证号");
      dto.setSummary("摘要");
      dto.setDebit("借方");
      dto.setCredit("贷方");
      dto.setBalance("余额");
      list.add(dto);
      Map map = new HashMap();
      map.put("ListaccHead", list);
      map.put("Listacc", explist);
      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.sysfinance.accmng.listacc.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
