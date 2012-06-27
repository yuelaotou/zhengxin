package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.business;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.bsinterface.IQueryOperationLogBS;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto.QueryOperationLogFindDTO;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto.QueryOperationLogListDTO;
/**
 * Copy Right Information : 查询业务日志的BS Goldsoft Project :
 * QueryOperationLogFindAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class QueryOperationLogBS implements IQueryOperationLogBS {

  private FnOperateLogDAO fnOperateLogDAO = null;

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public Object[] findOperationLogList(Pagination pagination,
      SecurityInfo securityInfo, List userlist) throws Exception,
      BusinessException {
    QueryOperationLogFindDTO queryOperationLogFindDTO = null;
    Object[] obj = new Object[2];
    String bookId = securityInfo.getBookId();
    if (pagination.getQueryCriterions().get("queryOperationLogFindDTO") != null) {
      queryOperationLogFindDTO = (QueryOperationLogFindDTO) pagination
          .getQueryCriterions().get("queryOperationLogFindDTO");
    } else {
      queryOperationLogFindDTO = new QueryOperationLogFindDTO();
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    List list = fnOperateLogDAO.queryOperationLogList(queryOperationLogFindDTO,
        start, orderBy, order, pageSize, page, bookId, userlist);
    for (int i = 0; i < list.size(); i++) {
      QueryOperationLogListDTO queryOperationLogListDTO = (QueryOperationLogListDTO) list
          .get(i);
      queryOperationLogListDTO
          .setAction((BusiTools.getBusiValue(Integer
              .parseInt(queryOperationLogListDTO.getAction()),
              BusiConst.CREDSTATE)));
      queryOperationLogListDTO.setBizType((BusiTools.getBusiValue_WL(
          queryOperationLogListDTO.getBizType(), BusiConst.CREDENCE_NUM_TYPE)));
      String credenceCharacter = fnOperateLogDAO.queryCredenceCharacter(
          queryOperationLogListDTO.getCredenceNum(), queryOperationLogListDTO
              .getOffice(), queryOperationLogListDTO.getCredenceDate(), bookId);
      // 凭证字号
      queryOperationLogListDTO.setCredenceCharacterNum(credenceCharacter + "-"
          + queryOperationLogListDTO.getCredenceNum());
    }
    List countList = fnOperateLogDAO.queryOperationLogCount(
        queryOperationLogFindDTO, bookId, userlist);
    for (int i = 0; i < countList.size(); i++) {
      QueryOperationLogListDTO queryOperationLogListDTO = (QueryOperationLogListDTO) countList
          .get(i);
      queryOperationLogListDTO
          .setAction((BusiTools.getBusiValue(Integer
              .parseInt(queryOperationLogListDTO.getAction()),
              BusiConst.CREDSTATE)));
      queryOperationLogListDTO.setBizType((BusiTools.getBusiValue_WL(
          queryOperationLogListDTO.getBizType(), BusiConst.CREDENCE_NUM_TYPE)));
    }
    pagination.setNrOfElements(countList.size());
    obj[0] = list;
    obj[1] = countList;
    return obj;
  }
}
