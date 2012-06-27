package org.xpup.hafmis.syscollection.common.domain.entity;
/**
 * 单位变更日志-封存
 * 
 * @author王菱 2007-6-15
 */
public class OrgChgLogSeal extends OrgChgLog{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String chgtype;

  public String getChgtype() {
    return "封存";
  }
}