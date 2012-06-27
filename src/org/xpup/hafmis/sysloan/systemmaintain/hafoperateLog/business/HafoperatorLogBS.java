package org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.bsinterface.IHafoperatorLogBS;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class HafoperatorLogBS implements IHafoperatorLogBS {
  
  private HafOperateLogDAO hafOperateLogDAO = null;
  
  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public List findHafOperateLog(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    List returnlist=new ArrayList();
    String opmodle = (String) pagination.getQueryCriterions().get("opmodle");
    String oper = (String) pagination.getQueryCriterions().get("oper");
    String starttime = (String) pagination.getQueryCriterions().get("starttime");
    String endtime = (String) pagination.getQueryCriterions().get("endtime");
    String opaction = (String) pagination.getQueryCriterions().get("opaction");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    
    List list=hafOperateLogDAO.querySysLoanHafoperatorLog(opmodle,oper,starttime,endtime,opaction,orderBy,order,start,pageSize,page,securityInfo);
    if(list.size()!=0){
      for(int i=0;i<list.size();i++){
        PlOperateLog plOperateLog=(PlOperateLog)list.get(i);
        plOperateLog.setOpModelshow(BusiTools.getBusiValue(Integer.parseInt(plOperateLog.getOpModel().toString()),BusiLogConst.OPMODELOAN));
        plOperateLog.setOpButtonshow(BusiTools.getBusiValue(Integer.parseInt(plOperateLog.getOpButton().toString()),BusiLogConst.BIZLOG_ACTION));
        returnlist.add(plOperateLog);
      }
    }
    List tempalllist=new ArrayList();
    List alllist=new ArrayList();
    tempalllist=hafOperateLogDAO.querySysLoanHafoperatorLogCount(opmodle,oper,starttime,endtime,opaction,securityInfo);
    for(int i=0;i<tempalllist.size();i++){
      PlOperateLog plOperateLog=(PlOperateLog)tempalllist.get(i);
      plOperateLog.setOpModelshow(BusiTools.getBusiValue(Integer.parseInt(plOperateLog.getOpModel().toString()),BusiLogConst.OPMODELOAN));
      plOperateLog.setOpButtonshow(BusiTools.getBusiValue(Integer.parseInt(plOperateLog.getOpButton().toString()),BusiLogConst.BIZLOG_ACTION));
      alllist.add(plOperateLog);
    }
    pagination.getQueryCriterions().put("alllist", alllist);
    int count = alllist.size();
    pagination.setNrOfElements(count);
    
    return returnlist;
  }

}
