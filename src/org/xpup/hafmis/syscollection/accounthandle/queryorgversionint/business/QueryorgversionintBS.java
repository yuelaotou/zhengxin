package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.business;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.bsinterface.IQueryorgversionintBS;
import org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.dto.QueryorgversionintDTO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.daoDW.SettInterestHeadDAODW;



public class QueryorgversionintBS implements IQueryorgversionintBS {

    private  PickHeadDAO pickHeadDAO;
    private  SettInterestHeadDAODW settInterestHeadDAODW;

    public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
      this.pickHeadDAO = pickHeadDAO;
    }
    public void setSettInterestHeadDAODW(SettInterestHeadDAODW settInterestHeadDAODW) {
      this.settInterestHeadDAODW = settInterestHeadDAODW;
    }
    public List queryList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    List  newlist = new ArrayList();
    try {
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String empId = (String) pagination.getQueryCriterions().get("empId");
      String empName = (String) pagination.getQueryCriterions().get("empName");
      String clearInterestType = (String) pagination.getQueryCriterions().get("clearInterestType");
      String value = (String) pagination.getQueryCriterions().get("key");
      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
     
      if (value != null && !"".equals(value)) {//有条件查询
         list=pickHeadDAO.findPickuplist(orgId,orgName,empId,empName,clearInterestType,orderBy,orderother,start,pageSize,page,securityInfo);//查询单位版数据库1、AA306关联AA307，AA309关联AA310，AA101关联AA102
         for(int i=0;i<list.size();i++){
           QueryorgversionintDTO dto=(QueryorgversionintDTO)list.get(i);
           String pid=dto.getPid();
           String pempid=dto.getEmpId();
           String interest=settInterestHeadDAODW.findIntersInfo(pid,pempid);//从中心库中查询利息
           dto.setInterest(new BigDecimal(interest));   
           
           if(dto.getClearInterestType().equals(BusiConst.CLEARINTERESTTYPE_YEARCLEAR)){
             BigDecimal mon=null;
             dto.setMoney(mon);
             dto.setSumMoney(dto.getInterest());
           }else{
             dto.setSumMoney(dto.getInterest().add(dto.getMoney()));
           }
           dto.setClearInterestType(BusiTools.getBusiValue_WL(dto.getClearInterestType(), BusiConst.CLEARINTERESTTYPE));
           newlist.add(dto);
         }
        int count= pickHeadDAO.findPickupalllist(orgId, orgName, empId, empName, clearInterestType, securityInfo);
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return newlist;
  }

}
