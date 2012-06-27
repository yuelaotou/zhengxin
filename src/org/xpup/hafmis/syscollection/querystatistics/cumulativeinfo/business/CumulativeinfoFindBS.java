package org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.bsinterface.ICumulativeinfoFindBS;

public class CumulativeinfoFindBS implements ICumulativeinfoFindBS{
  protected OrgDAO orgDAO = null;

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
  /**
   * 累计归集报表
   * 通过时间，查找 年初数,期初数,本期数
   * @author 杨光
   * @return List
   */
  public List FindCumulativeinfo(SecurityInfo securityInfo,String officeCode,String collectionBankId,String queryTime) throws Exception, BusinessException {
    String year=queryTime.substring(0, 4);
    String month=queryTime.substring(4, 6);
    String preMonth2="";
    String preYear=(Integer.parseInt(year)-1)+"1231";//年初数
    String preMonth1=year+"0101";//期初数1
    if((Integer.parseInt(month)-1)<10){
      preMonth2=year+"0"+(Integer.parseInt(month)-1)+"31";//期初数2
    }else{
      preMonth2=year+(Integer.parseInt(month)-1)+"31";//期初数2
    }
    String curYear1=queryTime+"01";//本期数1
    String curYear2=queryTime+"31";//本期数2
    List returnList=new ArrayList();
    try {
      returnList=orgDAO.findCumulativeinfo(securityInfo,officeCode,collectionBankId,preYear,preMonth1,preMonth2,curYear1,curYear2,year);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      
    }
    
   
    return returnList;
  }

  public List FindCollectionBankId(String officeCode) throws Exception, BusinessException {
    List returnList=new ArrayList();
    try {
      returnList=orgDAO.findCollectionBankId(officeCode);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
    return returnList;
  }
  
  public List findFundbankmonthofyear(final SecurityInfo securityInfo,final String officeCode,
      final String collectionBankId, final String Year)
      throws Exception, BusinessException{
        List returnList=new ArrayList();
        try {
          returnList=orgDAO.findFundbankmonthofyear(securityInfo,officeCode,collectionBankId,Year);
        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }
        
        return returnList;
      }
  
  public String find_bank_realname(final String user)throws Exception,BusinessException{
    String  returnList="";
    try {
      returnList=orgDAO.find_bank_realname(user);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    } 
    return returnList;
  }
}

