package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.modifyaccount.business;
import java.util.ArrayList;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.BaseGhgInfoDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.BaseGhgInfo;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.modifyaccount.bsinterface.IModifyBS;

public class ModifyBS implements IModifyBS{
  private BaseGhgInfoDAO baseInfoDao;
  private CollBankDAO collBankDAO;
  private OrganizationUnitDAO organizationUnitDAO;
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  public void setBaseInfoDao(BaseGhgInfoDAO baseInfoDao) {
    this.baseInfoDao = baseInfoDao;
  }
  public List getOpenMondifyInfoList_YG(Pagination pag,SecurityInfo a) throws BusinessException{
    String orgId = (String)pag.getQueryCriterions().get("orgId");
    String orgName = (String)pag.getQueryCriterions().get("orgName");
    String time = (String)pag.getQueryCriterions().get("start");
    String end = (String)pag.getQueryCriterions().get("end");
    List list=baseInfoDao.getOpenMondifyInfoCount_YG(orgId, orgName, time, end,a);
    List returnlist=new ArrayList();
    if(list!=null){
      for( int i=0;i<list.size();i++){
        BaseGhgInfo baseGhgInfo = (BaseGhgInfo)list.get(i);
        try {
          if(baseGhgInfo.getChgColumn().equals("缴存精度")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.PAYMENTACCURACY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.PAYMENTACCURACY));
          }
          else if(baseGhgInfo.getChgColumn().equals("缴存方式")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.ORGPAYWAY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.ORGPAYWAY));
          }
          else if(baseGhgInfo.getChgColumn().equals("归集银行")){
            CollBank collBankdto=collBankDAO.getCollBankByCollBankid(baseGhgInfo.getChgBefInfo().trim());
            baseGhgInfo.setTemp_chgBefInfo(collBankdto.getCollBankName());
            CollBank collBankdto1=collBankDAO.getCollBankByCollBankid(baseGhgInfo.getChgEndInfo().trim());
            baseGhgInfo.setTemp_chgEndInfo(collBankdto1.getCollBankName());
          }
          else if(baseGhgInfo.getChgColumn().equals("办事处名称")){
            OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(baseGhgInfo.getChgBefInfo());
            baseGhgInfo.setTemp_chgBefInfo(organizationUnit.getName());
            OrganizationUnit organizationUnit1=organizationUnitDAO.queryOrganizationUnitListByCriterions(baseGhgInfo.getChgEndInfo());
            baseGhgInfo.setTemp_chgEndInfo(organizationUnit1.getName());
          }
          else if(baseGhgInfo.getChgColumn().equals("单位性质")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.NATUREOFUNITS));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.NATUREOFUNITS));
          }
          else if(baseGhgInfo.getChgColumn().equals("所属行业")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.INDUSTRY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.INDUSTRY));
          }
          else if(baseGhgInfo.getChgColumn().equals("主管部门")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.AUTHORITIES));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.AUTHORITIES));
          }
          else if(baseGhgInfo.getChgColumn().equals("所在地区")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.INAREA));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.INAREA));
          }
          else{
            baseGhgInfo.setTemp_chgBefInfo(baseGhgInfo.getChgBefInfo());
            baseGhgInfo.setTemp_chgEndInfo(baseGhgInfo.getChgEndInfo());
          }
        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        returnlist.add(baseGhgInfo);
      }
    }
    return returnlist;
  }
  public List getOpenMondifyInfoList(Pagination pag,SecurityInfo a) throws BusinessException{
    String orgId = (String)pag.getQueryCriterions().get("orgId");
    String orgName = (String)pag.getQueryCriterions().get("orgName");
    String time = (String)pag.getQueryCriterions().get("start");
    String end = (String)pag.getQueryCriterions().get("end");
    String orderBy = (String) pag.getOrderBy();
    String order = (String) pag.getOrderother();
    int start = pag.getFirstElementOnPage() - 1;
    int pageSize = pag.getPageSize(); 
    int count = baseInfoDao.getOpenMondifyInfoCount(orgId, orgName, time, end,a);
    pag.setNrOfElements(count);
    List list = baseInfoDao.getOpenMondifyInfoList(orgId, orgName,time,end, orderBy,order,start,pageSize,a);
    List returnlist=new ArrayList();
    if(list!=null){
      for( int i=0;i<list.size();i++){
        BaseGhgInfo baseGhgInfo = (BaseGhgInfo)list.get(i);
        try {
          if(baseGhgInfo.getChgColumn().equals("缴存精度")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.PAYMENTACCURACY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.PAYMENTACCURACY));
          }
          else if(baseGhgInfo.getChgColumn().equals("缴存方式")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.ORGPAYWAY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.ORGPAYWAY));
          }
          else if(baseGhgInfo.getChgColumn().equals("归集银行")){
            CollBank collBankdto=collBankDAO.getCollBankByCollBankid(baseGhgInfo.getChgBefInfo().trim());
            baseGhgInfo.setTemp_chgBefInfo(collBankdto.getCollBankName());
            CollBank collBankdto1=collBankDAO.getCollBankByCollBankid(baseGhgInfo.getChgEndInfo().trim());
            baseGhgInfo.setTemp_chgEndInfo(collBankdto1.getCollBankName());
          }
          else if(baseGhgInfo.getChgColumn().equals("办事处名称")){
            OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(baseGhgInfo.getChgBefInfo());
            baseGhgInfo.setTemp_chgBefInfo(organizationUnit.getName());
            OrganizationUnit organizationUnit1=organizationUnitDAO.queryOrganizationUnitListByCriterions(baseGhgInfo.getChgEndInfo());
            baseGhgInfo.setTemp_chgEndInfo(organizationUnit1.getName());
          }
          else if(baseGhgInfo.getChgColumn().equals("单位性质")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.NATUREOFUNITS));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.NATUREOFUNITS));
          }
          else if(baseGhgInfo.getChgColumn().equals("所属行业")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.INDUSTRY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.INDUSTRY));
          }
          else if(baseGhgInfo.getChgColumn().equals("主管部门")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.AUTHORITIES));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.AUTHORITIES));
          }
          else if(baseGhgInfo.getChgColumn().equals("所在地区")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.INAREA));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.INAREA));
          }
          else{
            baseGhgInfo.setTemp_chgBefInfo(baseGhgInfo.getChgBefInfo());
            baseGhgInfo.setTemp_chgEndInfo(baseGhgInfo.getChgEndInfo());
          }
        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        returnlist.add(baseGhgInfo);
      }
    }
    return returnlist;
  }
  public List getPrintInfo(Pagination pag,SecurityInfo a)throws BusinessException{
    String orgId = (String)pag.getQueryCriterions().get("orgId");
    String orgName = (String)pag.getQueryCriterions().get("orgName");
    String time = (String)pag.getQueryCriterions().get("start");
    String end = (String)pag.getQueryCriterions().get("end");
    List list = baseInfoDao.getPrintInfo(orgId, orgName, time, end, a);
    List returnlist=new ArrayList();
    if(list!=null){
      for( int i=0;i<list.size();i++){
        BaseGhgInfo baseGhgInfo = (BaseGhgInfo)list.get(i);
        try {
          if(baseGhgInfo.getChgColumn().equals("缴存精度")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.PAYMENTACCURACY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.PAYMENTACCURACY));
          }
          else if(baseGhgInfo.getChgColumn().equals("缴存方式")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.ORGPAYWAY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.ORGPAYWAY));
          }
          else if(baseGhgInfo.getChgColumn().equals("归集银行")){
            CollBank collBankdto=collBankDAO.getCollBankByCollBankid(baseGhgInfo.getChgBefInfo().trim());
            baseGhgInfo.setTemp_chgBefInfo(collBankdto.getCollBankName());
            CollBank collBankdto1=collBankDAO.getCollBankByCollBankid(baseGhgInfo.getChgEndInfo().trim());
            baseGhgInfo.setTemp_chgEndInfo(collBankdto1.getCollBankName());
          }
          else if(baseGhgInfo.getChgColumn().equals("办事处名称")){
            OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(baseGhgInfo.getChgBefInfo());
            baseGhgInfo.setTemp_chgBefInfo(organizationUnit.getName());
            OrganizationUnit organizationUnit1=organizationUnitDAO.queryOrganizationUnitListByCriterions(baseGhgInfo.getChgEndInfo());
            baseGhgInfo.setTemp_chgEndInfo(organizationUnit1.getName());
          }
          else if(baseGhgInfo.getChgColumn().equals("单位性质")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.NATUREOFUNITS));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.NATUREOFUNITS));
          }
          else if(baseGhgInfo.getChgColumn().equals("所属行业")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.INDUSTRY));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.INDUSTRY));
          }
          else if(baseGhgInfo.getChgColumn().equals("主管部门")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.AUTHORITIES));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.AUTHORITIES));
          }
          else if(baseGhgInfo.getChgColumn().equals("所在地区")){
            baseGhgInfo.setTemp_chgBefInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgBefInfo()), BusiConst.INAREA));
            baseGhgInfo.setTemp_chgEndInfo(BusiTools.getBusiValue(Integer.parseInt(baseGhgInfo.getChgEndInfo()), BusiConst.INAREA));
          }
          else{
            baseGhgInfo.setTemp_chgBefInfo(baseGhgInfo.getChgBefInfo());
            baseGhgInfo.setTemp_chgEndInfo(baseGhgInfo.getChgEndInfo());
          }
        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        returnlist.add(baseGhgInfo);
      }
    }
    return returnlist;
  }
  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
}