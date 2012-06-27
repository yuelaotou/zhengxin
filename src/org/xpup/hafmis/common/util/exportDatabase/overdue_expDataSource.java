package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.OverdueExpDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.OverdueInfoDTO;

public class overdue_expDataSource implements ExportDateSourceInterface {
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
      Factory faxtory = new Factory();
      Map map = new HashMap();
      List list = new ArrayList();
      for (int i = 0; i < explist.size(); i++) {
        OverdueInfoDTO dto = (OverdueInfoDTO) explist.get(i);
        StringBuffer sb = new StringBuffer();
        OverdueExpDTO d = new OverdueExpDTO();
        d.setMobile(dto.getBorrowerMobile());
        sb.append(dto.getBorrowerMobile()).append(",公积金贷款合同").append(
            dto.getContractId()).append(dto.getBorrowerName()).append("。还至")
            .append(dto.getRepayMonth()).append("，欠款").append(
                dto.getOverdueMonths()).append("个月，请存入").append(
                dto.getShouldPayMoney().add(dto.getPunishInterest())).append(
                "元。");
        d.setContent(sb.toString());
        list.add(d);
      }
      map.put("OverdueExp", list);
      faxtory
          .setInfomation(xmlfile, xlsFile, map,
              "org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
