package org.xpup.hafmis.sysloan.common.biz.orgpop.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.orgpop.bsinterface.IOrgpopBS;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class OrgpopBS implements IOrgpopBS{
  private OrgDAO orgDAO = null;

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
  /**
   * 根据单位编号、单位名称、单位状态查询单位信息
   * 单位弹出框查询
   * 李娟
   */
  public List findOrgpopList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException{
    // TODO Auto-generated method stub
    List orgpopList = null;
    Serializable sid = (Serializable)pagination.getQueryCriterions().get("id");
    String id="";
    if(sid==null||sid.toString().length()<=0){
      id=(String)sid;
    }else{
      id = sid.toString();
      id = id.trim();
    }
    String []status=(String[])pagination.getQueryCriterions().get("status");
    String name = (String) pagination.getQueryCriterions().get("name");
    String oldId = (String) pagination.getQueryCriterions().get("oldId");
    String qx = (String) pagination.getQueryCriterions().get("qx");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;  
    int pageSize = pagination.getPageSize();
    int count=0;
    List list= new ArrayList();
 //   System.out.println(qx+"----------------y");
    if(qx.equals("yes")){
      orgpopList = orgDAO.queryOrgpopListByCriterions(id, name, oldId, orderBy, order, start, pageSize,status,securityInfo);
      if(orgpopList != null){
        for(int i=0;i<orgpopList.size();i++){
          Org org=(Org)orgpopList.get(i);
          org.getOrgInfo().setRegion(BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo().getRegion()), BusiConst.INAREA));
          list.add(org);
        }
      }
      count = orgDAO.queryOrgpopCountByCriterions(id, name,oldId,status,securityInfo);
    }else if(qx.equals("no")){
  //    System.out.println(qx+"---------------n");
      orgpopList = orgDAO.queryOrgpopListByCriterionsNo(id, name, oldId, orderBy, order, start, pageSize,status,securityInfo);
      if(orgpopList != null){
        for(int i=0;i<orgpopList.size();i++){
          Org org=(Org)orgpopList.get(i);
          org.getOrgInfo().setRegion(BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo().getRegion()), BusiConst.INAREA));
          list.add(org);
        }
      }
      count = orgDAO.queryOrgpopCountByCriterionsNo(id, name,oldId,status,securityInfo);
    }
    pagination.setNrOfElements(count);
//    for(int i=0;i<orgpopList.size();i++){
//      Org org=(Org)orgpopList.get(i);
//      System.out.println(org.getOrgInfo().getName());
//    }
    return list;
  }

}