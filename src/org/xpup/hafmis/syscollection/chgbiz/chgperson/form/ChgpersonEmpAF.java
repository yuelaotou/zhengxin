package org.xpup.hafmis.syscollection.chgbiz.chgperson.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
/**
 * 
 * @author 王玲
 *2007-6-29
 */
public class ChgpersonEmpAF extends ActionForm{

  private static final long serialVersionUID = -5165813203583998085L;
  
  
  private Map chgMap=new HashMap();//变更类型
  private String chgMap_1 ;//变更类型
  private Map documentMap=new HashMap();//证件类型
  private String documentMap_1 ;//证件类型
  private Map sexMap=new HashMap();//性别
  private String sexMap_1 ;//性别
  private Map partInMap=new HashMap();//是否参与汇缴
  private String partInMap_1 ;//是否参与汇缴
  private Map chgreasonMap=new HashMap();//变更原因
  private String chgreasonMap_1 ;//变更原因
  private ChgPersonTail chgPersonTail = new ChgPersonTail();
  private String orgID;
  private List list;
  private String orgPayMode = "";
  private String temp = "";//bit add
  public String getTemp() {
    return temp;
  }
  public void setTemp(String temp) {
    this.temp = temp;
  }
  
  private Map chgreasonMap_2= new HashMap();  
  private String chgreason_2;
  
  public String getOrgPayMode() {
    return orgPayMode;
  }
  public void setOrgPayMode(String orgPayMode) {
    this.orgPayMode = orgPayMode;
  }
  public String getOrgID() {
    return orgID;
  }
  public void setOrgID(String orgID) {
    this.orgID = orgID;
  }
  public Map getChgMap() {
    return chgMap;
  }
  public void setChgMap(Map chgMap) {
    this.chgMap = chgMap;
  }
  public Map getDocumentMap() {
    return documentMap;
  }
  public void setDocumentMap(Map documentMap) {
    this.documentMap = documentMap;
  }
  public Map getPartInMap() {
    return partInMap;
  }
  public void setPartInMap(Map partInMap) {
    this.partInMap = partInMap;
  }
  public Map getSexMap() {
    return sexMap;
  }
  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }
  public String getChgMap_1() {
    return chgMap_1;
  }
  public void setChgMap_1(String chgMap_1) {
    this.chgMap_1 = chgMap_1;
  }
  public String getDocumentMap_1() {
    return documentMap_1;
  }
  public void setDocumentMap_1(String documentMap_1) {
    this.documentMap_1 = documentMap_1;
  }
  public String getPartInMap_1() {
    return partInMap_1;
  }
  public void setPartInMap_1(String partInMap_1) {
    this.partInMap_1 = partInMap_1;
  }
  public String getSexMap_1() {
    return sexMap_1;
  }
  public void setSexMap_1(String sexMap_1) {
    this.sexMap_1 = sexMap_1;
  }
  public ChgPersonTail getChgPersonTail() {
    return chgPersonTail;
  }
  public void setChgPersonTail(ChgPersonTail chgPersonTail) {
    this.chgPersonTail = chgPersonTail;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    
    chgMap=new HashMap();//变更类型
    chgMap_1="" ;//变更类型
    documentMap=new HashMap();//证件类型
    documentMap_1="" ;//证件类型
    sexMap=new HashMap();//性别
    sexMap_1 ="";//性别
    partInMap=new HashMap();//是否参与汇缴
    partInMap_1="" ;//是否参与汇缴
    chgPersonTail = new ChgPersonTail();
    orgID="";
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public Map getChgreasonMap() {
    return chgreasonMap;
  }
  public void setChgreasonMap(Map chgreasonMap) {
    this.chgreasonMap = chgreasonMap;
  }
  public String getChgreasonMap_1() {
    return chgreasonMap_1;
  }
  public void setChgreasonMap_1(String chgreasonMap_1) {
    this.chgreasonMap_1 = chgreasonMap_1;
  }
  public String getChgreason_2() {
    return chgreason_2;
  }
  public void setChgreason_2(String chgreason_2) {
    this.chgreason_2 = chgreason_2;
  }
  public Map getChgreasonMap_2() {
    return chgreasonMap_2;
  }
  public void setChgreasonMap_2(Map chgreasonMap_2) {
    this.chgreasonMap_2 = chgreasonMap_2;
  }
  
}
