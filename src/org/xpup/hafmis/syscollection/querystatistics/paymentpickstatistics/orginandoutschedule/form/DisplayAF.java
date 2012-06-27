package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.ListDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.SearchDTO;

public class DisplayAF extends ActionForm{
  ListDTO listDTO=new ListDTO();
  SearchDTO searchDTO=new SearchDTO();
  private List list=new ArrayList();
  private Map charaterMap= new HashMap();
  private Map deptInChargeMap= new HashMap();
  private Map regionMap= new HashMap();
  private BigDecimal paymentSum=new BigDecimal(0.00);
  private BigDecimal addpaySum=new BigDecimal(0.00);
  private BigDecimal adjustaccountSum=new BigDecimal(0.00);
  private BigDecimal traninSum=new BigDecimal(0.00);
  private BigDecimal interestSum=new BigDecimal(0.00);
  private BigDecimal pickSum=new BigDecimal(0.00);
  private BigDecimal tranoutSum=new BigDecimal(0.00);
  private BigDecimal guazhangSum=new BigDecimal(0.00);
  private BigDecimal zmbalanceSum=new BigDecimal(0.00);
  public BigDecimal getZmbalanceSum() {
    return zmbalanceSum;
  }
  public BigDecimal getGuazhangSum() {
    return guazhangSum;
  }
  public void setGuazhangSum(BigDecimal guazhangSum) {
    this.guazhangSum = guazhangSum;
  }
  public void setZmbalanceSum(BigDecimal zmbalanceSum) {
    this.zmbalanceSum = zmbalanceSum;
  }
  public BigDecimal getAddpaySum() {
    return addpaySum;
  }
  public void setAddpaySum(BigDecimal addpaySum) {
    this.addpaySum = addpaySum;
  }
  public BigDecimal getAdjustaccountSum() {
    return adjustaccountSum;
  }
  public void setAdjustaccountSum(BigDecimal adjustaccountSum) {
    this.adjustaccountSum = adjustaccountSum;
  }
  public Map getCharaterMap() {
    return charaterMap;
  }
  public void setCharaterMap(Map charaterMap) {
    this.charaterMap = charaterMap;
  }
  public Map getDeptInChargeMap() {
    return deptInChargeMap;
  }
  public void setDeptInChargeMap(Map deptInChargeMap) {
    this.deptInChargeMap = deptInChargeMap;
  }
  public BigDecimal getInterestSum() {
    return interestSum;
  }
  public void setInterestSum(BigDecimal interestSum) {
    this.interestSum = interestSum;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public ListDTO getListDTO() {
    return listDTO;
  }
  public void setListDTO(ListDTO listDTO) {
    this.listDTO = listDTO;
  }
  public BigDecimal getPaymentSum() {
    return paymentSum;
  }
  public void setPaymentSum(BigDecimal paymentSum) {
    this.paymentSum = paymentSum;
  }
  public BigDecimal getPickSum() {
    return pickSum;
  }
  public void setPickSum(BigDecimal pickSum) {
    this.pickSum = pickSum;
  }
  public Map getRegionMap() {
    return regionMap;
  }
  public void setRegionMap(Map regionMap) {
    this.regionMap = regionMap;
  }
  public SearchDTO getSearchDTO() {
    return searchDTO;
  }
  public void setSearchDTO(SearchDTO searchDTO) {
    this.searchDTO = searchDTO;
  }
  public BigDecimal getTraninSum() {
    return traninSum;
  }
  public void setTraninSum(BigDecimal traninSum) {
    this.traninSum = traninSum;
  }
  public BigDecimal getTranoutSum() {
    return tranoutSum;
  }
  public void setTranoutSum(BigDecimal tranoutSum) {
    this.tranoutSum = tranoutSum;
  }
}
