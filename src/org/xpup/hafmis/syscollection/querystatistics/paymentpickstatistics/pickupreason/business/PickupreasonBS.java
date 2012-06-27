package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.business;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.common.dao.ChangeRateBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.HafInterestRateDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.PickTailDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface.IEmpAccountBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.bsinterface.IPickupreasonBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.dto.PickupreasonDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;



public class PickupreasonBS implements IPickupreasonBS {

  private PickTailDAO pickTailDAO = null;
  public void setPickTailDAO(PickTailDAO pickTailDAO) {
    this.pickTailDAO = pickTailDAO;
  }
  /**
   * @param 三个条件office,bank,orgId
   * @return list
   * @throws BusinessException
   */
  public List findPickupreasonList_sy(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    List returnList=new ArrayList();
    List printList=new ArrayList();
    List returnPrintList=new ArrayList();
    List totalList=new ArrayList();
    List list=new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orgid=(String) pagination.getQueryCriterions().get("orgid");
    String officeid=(String) pagination.getQueryCriterions().get("officeid");
    String bankid=(String) pagination.getQueryCriterions().get("bankid");
    String date=(String) pagination.getQueryCriterions().get("date");
    try{
      //查找全部信息,为打印做数据准备.同时求出总金额和人数用于计算比率.
      totalList=pickTailDAO.countPickupreason_sy(orgid, officeid, bankid,date, securityInfo);
      BigDecimal totalpeople=new BigDecimal(0.00);
      BigDecimal totalmoney=new BigDecimal(0.00);
      for(int i=0;i<totalList.size();i++){
        PickupreasonDTO pickupreasonDTO=new PickupreasonDTO();
        Object[] pickupreason = (Object[]) totalList.get(i);
        //获得提取原因
        BigDecimal pickreason=(BigDecimal) pickupreason[0];
        //获取提取钱数
        BigDecimal pickmoney=(BigDecimal) pickupreason[1];
        //获得人数
        BigDecimal numberpeople=(BigDecimal) pickupreason[2];
        pickupreasonDTO.setPickupreason(pickreason.toString());
        pickupreasonDTO.setPickmoney(pickmoney.toString());
        pickupreasonDTO.setNumberpeople(numberpeople.toString());
        //获得中的钱数
        totalmoney=totalmoney.add(pickmoney);
        //获得总的人数
        totalpeople=totalpeople.add(numberpeople);
        printList.add(pickupreasonDTO);
         }
      //web显示总的人数
      pagination.getQueryCriterions().put("totalpeople", totalpeople.toString());
      //web显示总的金额
      pagination.getQueryCriterions().put("totalmoney", totalmoney.toString());
      //返回打印的list
      for(int j=0;j<printList.size();j++){
        PickupreasonDTO pickupreasonDTO=new PickupreasonDTO();
        pickupreasonDTO=(PickupreasonDTO) printList.get(j);
        BigDecimal numberpeople=new BigDecimal(pickupreasonDTO.getNumberpeople());
        BigDecimal pickmoney=new BigDecimal(pickupreasonDTO.getPickmoney());
//      计算金额比率
        BigDecimal countpeople=new BigDecimal(0.00);
        //计算时候保留四位有效数字。
        countpeople=numberpeople.divide(totalpeople,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100.00));
        //显示的时候保留两位小数。
        countpeople=countpeople.divide(new BigDecimal(1.00),2,BigDecimal.ROUND_HALF_UP);
        pickupreasonDTO.setCountpeople(countpeople+""+"%");
        //计算人数比率
        BigDecimal countmoney=new BigDecimal(0.00);
        //计算时候保留四位有效数字。
        countmoney=pickmoney.divide(totalmoney, 4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100.00));
        //显示的时候保留两位小数。
        countmoney=countmoney.divide(new BigDecimal(1.00),2,BigDecimal.ROUND_HALF_UP);
        pickupreasonDTO.setCountmoney(countmoney+""+"%");
        if(new Integer(pickupreasonDTO.getPickupreason()).intValue()<4){
          pickupreasonDTO.setPickupreason(BusiTools.getBusiValue(new Integer(pickupreasonDTO.getPickupreason()).intValue(),BusiConst.SOMEPICK));
          }else{
          pickupreasonDTO.setPickupreason(BusiTools.getBusiValue(new Integer(pickupreasonDTO.getPickupreason()).intValue(),BusiConst.DISTROYPICK));  
          }
        returnPrintList.add(pickupreasonDTO);
      }
      pagination.getQueryCriterions().put("returnPrintList", returnPrintList);
      //分页显示信息
      list=pickTailDAO.queryPickupreason_sy(orgid, officeid, bankid,date, securityInfo,orderBy,order,start,pageSize);
      for(int i=0;i<list.size();i++){
     PickupreasonDTO pickupreasonDTO=new PickupreasonDTO();
     Object[] pickupreason = (Object[]) list.get(i);
     //获得提取原因
     BigDecimal pickreason=(BigDecimal) pickupreason[0];
     //获取提取钱数
     BigDecimal pickmoney=(BigDecimal) pickupreason[1];
     //获得人数
     BigDecimal numberpeople=(BigDecimal) pickupreason[2]; 
     //计算金额比率
     BigDecimal countpeople=new BigDecimal(0.00);
     countpeople=numberpeople.divide(totalpeople,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100.00));
     countpeople=countpeople.divide(new BigDecimal(1.00),2,BigDecimal.ROUND_HALF_UP);
     //计算人数比率
     BigDecimal countmoney=new BigDecimal(0.00);
     countmoney=pickmoney.divide(totalmoney, 4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100.00));
     countmoney=countmoney.divide(new BigDecimal(1.00),2,BigDecimal.ROUND_HALF_UP);
     //web需要的数据
     if(new Integer(pickreason.toString()).intValue()<4){
     pickupreasonDTO.setPickupreason(BusiTools.getBusiValue(pickreason.intValue(),BusiConst.SOMEPICK));
     }else{
     pickupreasonDTO.setPickupreason(BusiTools.getBusiValue(pickreason.intValue(),BusiConst.DISTROYPICK));  
     }
     pickupreasonDTO.setPickmoney(pickmoney.toString());
     pickupreasonDTO.setNumberpeople(numberpeople.toString());
     pickupreasonDTO.setCountmoney(countmoney.toString());
     pickupreasonDTO.setCountpeople(countpeople.toString());
     returnList.add(pickupreasonDTO);
      }
      pagination.setNrOfElements(totalList.size());
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnList;
  }
}