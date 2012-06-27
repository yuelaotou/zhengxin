package org.xpup.hafmis.syscollection.peoplebank.documents.bsinterface;

import java.util.Vector;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.DocumentstopDTO;

/**
 * 
 * @author 王菱
 *
 */
public interface IDocumentBS {
  //根据条件查询要导出的人民银行数据
  public Vector getAllInfo(String date,SecurityInfo securityInfo,DocumentstopDTO documentstopdto) throws Exception,BusinessException;
  //根据条件查询要导出的人民银行数据头信息
  public DocumentstopDTO gettopInfo(SecurityInfo securityInfo) throws Exception,BusinessException;
}
