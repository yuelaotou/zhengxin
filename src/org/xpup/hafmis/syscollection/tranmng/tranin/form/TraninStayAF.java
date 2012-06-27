package org.xpup.hafmis.syscollection.tranmng.tranin.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

/**
 * shiyan
 */
public class TraninStayAF extends IdAF {
  /**
   * 
   */
  private static final long serialVersionUID = 4314073321220405479L;

  private String inOrgId = "";

  private String inOrgName = "";

  private String noteNum = "";

  private String docNum = "";

  private String outOrgId = "";

  private String outOrgName = "";

  private String loadsMassage = "";

  private TranOutTail tranOutTail = new TranOutTail();

  private List list;
  
  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getInOrgId() {
    return inOrgId;
  }

  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }

  public String getInOrgName() {
    return inOrgName;
  }

  public void setInOrgName(String inOrgName) {
    this.inOrgName = inOrgName;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOutOrgId() {
    return outOrgId;
  }

  public void setOutOrgId(String outOrgId) {
    this.outOrgId = outOrgId;
  }

  public String getOutOrgName() {
    return outOrgName;
  }

  public void setOutOrgName(String outOrgName) {
    this.outOrgName = outOrgName;
  }

  public TranOutTail getTranOutTail() {
    return tranOutTail;
  }

  public void setTranOutTail(TranOutTail tranOutTail) {
    this.tranOutTail = tranOutTail;
  }

  public String getLoadsMassage() {
    return loadsMassage;
  }

  public void setLoadsMassage(String loadsMassage) {
    this.loadsMassage = loadsMassage;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    inOrgId="" ;
    inOrgName="";
    noteNum="" ;
    outOrgName ="";
    docNum="" ;
    outOrgId = "";
    outOrgName="";
    loadsMassage="";
    tranOutTail = new TranOutTail();
    list=new ArrayList();
  }
}
