package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form;

import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;


/**
 * 
 * @author 李鹏
 *2007-6-27
 */
public class AdjustaccountServiceFindAF extends ValidatorActionForm{

  private static final long serialVersionUID = 0L;
  
  private String bizDocNum="";//错账业务凭证号
  private String date=""; //日期
  private String date1=""; //日期
  private String orgId=""; //单位id
  private String orgName=""; //单位名
  private String bis_Status=""; //业务状态
  private String type=""; //按钮变灰
  private List list; //列表数据
  private Map bisMap; //列表数据

  public String getBis_Status() {
    return bis_Status;
  }
  public void setBis_Status(String bis_Status) {
    this.bis_Status = bis_Status;
  }
  public String getBizDocNum() {
    return bizDocNum;
  }
  public void setBizDocNum(String bizDocNum) {
    this.bizDocNum = bizDocNum;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }

  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public Map getBisMap() {
    return bisMap;
  }
  public void setBisMap(Map bisMap) {
    this.bisMap = bisMap;
  }
  public String getDate1() {
    return date1;
  }
  public void setDate1(String date1) {
    this.date1 = date1;
  }


}
