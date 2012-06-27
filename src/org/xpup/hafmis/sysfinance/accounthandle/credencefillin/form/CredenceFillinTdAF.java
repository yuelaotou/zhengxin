package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckShowListDTO;

/**
 * Copy Right Information : 显示凭证录入维护列表的ActionForm Goldsoft Project :
 * CredenceFillinTdAF
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.31
 */
public class CredenceFillinTdAF extends ActionForm {

  /** 封装了查询的条件 */
  private CredencecheckFindDTO credencecheckFindDTO = new CredencecheckFindDTO();

  /** 封装了列表的内容 */
  private CredencecheckShowListDTO credencecheckShowListDTO = new CredencecheckShowListDTO();
  
  private Map credenceStMap = new HashMap();

  private String credenceSt = "";
  
  private String type = "0";
  
  private List list;

  public CredencecheckFindDTO getCredencecheckFindDTO() {
    return credencecheckFindDTO;
  }

  public void setCredencecheckFindDTO(CredencecheckFindDTO credencecheckFindDTO) {
    this.credencecheckFindDTO = credencecheckFindDTO;
  }

  public CredencecheckShowListDTO getCredencecheckShowListDTO() {
    return credencecheckShowListDTO;
  }

  public void setCredencecheckShowListDTO(
      CredencecheckShowListDTO credencecheckShowListDTO) {
    this.credencecheckShowListDTO = credencecheckShowListDTO;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public Map getCredenceStMap() {
    return credenceStMap;
  }

  public void setCredenceStMap(Map credenceStMap) {
    this.credenceStMap = credenceStMap;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
