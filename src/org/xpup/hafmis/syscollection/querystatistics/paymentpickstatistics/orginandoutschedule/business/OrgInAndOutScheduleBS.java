package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.bsinterface.IOrgInAndOutScheduleBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.ListDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.SearchDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.form.DisplayAF;

public class OrgInAndOutScheduleBS implements IOrgInAndOutScheduleBS{
  OrgHAFAccountFlowDAO orgHAFAccountFlowDAO=null;

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }
  /**
   *  根据查询条件统计查询单位收支明细一览表
   */
  public Object[] find(Pagination pagination) {
    // TODO Auto-generated method stub
    Object[] resultList = new Object[2];
    List list1 = new ArrayList();
    DisplayAF displayAF = new DisplayAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    SearchDTO dto = (SearchDTO)pagination.getQueryCriterions().get("search");
    SecurityInfo securityInfo = (SecurityInfo)pagination.getQueryCriterions().get("securityInfo");
    if(dto!=null){
//    查询将在列表中显示的统计内容
      List list = orgHAFAccountFlowDAO.queryStatisticInfo(dto, orderBy, order, start, pageSize, securityInfo);
      // 转换ListDTO中的属性，小数格式的转换
      for (int i = 0; i < list.size(); i++) {
        ListDTO listDTO = (ListDTO)list.get(i);
        listDTO.setJzbalance(listDTO.getJzbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setJzgzbalance(listDTO.getJzgzbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setJzzmbalance(listDTO.getJzzmbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setGzpay(listDTO.getGzpay().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setPayment(listDTO.getPayment().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setAddpay(listDTO.getAddpay().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setAdjustaccount(listDTO.getAdjustaccount().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setTanin(listDTO.getTanin().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setInterest(listDTO.getInterest().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setPick(listDTO.getPick().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setTranout(listDTO.getTranout().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setCurrentbalance(listDTO.getCurrentbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setGzbalance(listDTO.getGzbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
        listDTO.setZmbalance(listDTO.getZmbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
        list1.add(listDTO);
      }
      // 求出合计
      List list2 = orgHAFAccountFlowDAO.queryStatisticInfoSum(dto,securityInfo);
      
      double payment=0.00;
      double addpay=0.00;
      double adjustaccount=0.00;
      double tranin=0.00;
      double interest=0.00;
      double pick=0.00;
      double tranout=0.00;
      double guazhang=0.00;
      double zhanmianyue=0.00;
      for (int i = 0; i < list2.size(); i++) {
        ListDTO listDTO = (ListDTO)list2.get(i);
        payment=payment+listDTO.getPayment().doubleValue();
        addpay=addpay+listDTO.getAddpay().doubleValue();
        adjustaccount=adjustaccount+listDTO.getAdjustaccount().doubleValue();
        tranin=tranin+listDTO.getTanin().doubleValue();
        interest=interest+listDTO.getInterest().doubleValue();
        pick=pick+listDTO.getPick().doubleValue();
        tranout=tranout+listDTO.getTranout().doubleValue();
        guazhang=guazhang+listDTO.getGzpay().doubleValue();
        zhanmianyue=zhanmianyue+listDTO.getZmbalance().doubleValue();
      }
      displayAF.setPaymentSum(new BigDecimal(payment).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setAddpaySum(new BigDecimal(addpay).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setAdjustaccountSum(new BigDecimal(adjustaccount).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setTraninSum(new BigDecimal(tranin).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setInterestSum(new BigDecimal(interest).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setPickSum(new BigDecimal(pick).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setTranoutSum(new BigDecimal(tranout).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setGuazhangSum(new BigDecimal(guazhang).setScale(2, BigDecimal.ROUND_HALF_UP));
      displayAF.setZmbalanceSum(new BigDecimal(zhanmianyue).setScale(2, BigDecimal.ROUND_HALF_UP));
      // 求出count
      int count = orgHAFAccountFlowDAO.queryStatisticInfoCount(dto, securityInfo);
      pagination.setNrOfElements(count);
      
    }
//  将列表中的信息放入Object[]中
    resultList[0]=list1;
    resultList[1]=displayAF;
    return resultList;
  }
  //打印
  public List findPrintInfo(Pagination pagination) {
    // TODO Auto-generated method stub
    List list1 = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    SearchDTO dto = (SearchDTO)pagination.getQueryCriterions().get("search");
    SecurityInfo securityInfo = (SecurityInfo)pagination.getQueryCriterions().get("securityInfo");
    // 查询将在列表中显示的统计内容
    List list = orgHAFAccountFlowDAO.queryStatisticPrintInfo(dto, orderBy, order, securityInfo);
    // 转换ListDTO中的属性，小数格式的转换
    for (int i = 0; i < list.size(); i++) {
      ListDTO listDTO = (ListDTO)list.get(i);
      listDTO.setJzbalance(listDTO.getJzbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setJzgzbalance(listDTO.getJzgzbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setJzzmbalance(listDTO.getJzzmbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setGzpay(listDTO.getGzpay().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setPayment(listDTO.getPayment().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setAddpay(listDTO.getAddpay().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setAdjustaccount(listDTO.getAdjustaccount().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setTanin(listDTO.getTanin().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setInterest(listDTO.getInterest().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setPick(listDTO.getPick().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setTranout(listDTO.getTranout().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setCurrentbalance(listDTO.getCurrentbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setGzbalance(listDTO.getGzbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
      listDTO.setZmbalance(listDTO.getZmbalance().setScale(2,BigDecimal.ROUND_HALF_UP));
      list1.add(listDTO);
    }
    return list1;
  }
}
