package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaSummayListDTO;
/**
 * Copy Right Information : 凭证录入页的ActionForm 
 * Goldsoft Project : CredenceFillinTaAF
 * 
 * @Version : v1.0
 * @author : 刘冰
 * @Date : 2007.10.22
 */
public class CredenceFillinTaAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  private String listAllContent="";
  private String typeButton="";
  private String type="";
  private String bookSt="";
  
  /** 列表内容 */
  private List summaylist;
  private CredenceFillinTaShowDTO credenceFillinTaShowDTO=new CredenceFillinTaShowDTO();
  private CredenceFillinTaSummayListDTO credenceFillinTaSummayListDTO=new CredenceFillinTaSummayListDTO();
  public String getListAllContent() {
    return listAllContent;
  }

  public CredenceFillinTaSummayListDTO getCredenceFillinTaSummayListDTO() {
    return credenceFillinTaSummayListDTO;
  }

  public void setCredenceFillinTaSummayListDTO(
      CredenceFillinTaSummayListDTO credenceFillinTaSummayListDTO) {
    this.credenceFillinTaSummayListDTO = credenceFillinTaSummayListDTO;
  }

  public void setListAllContent(String listAllContent) {
    this.listAllContent = listAllContent;
  }

  
  public String getTypeButton() {
    return typeButton;
  }

  public void setTypeButton(String typeButton) {
    this.typeButton = typeButton;
  }


  public CredenceFillinTaShowDTO getCredenceFillinTaShowDTO() {
    return credenceFillinTaShowDTO;
  }

  public void setCredenceFillinTaShowDTO(
      CredenceFillinTaShowDTO credenceFillinTaShowDTO) {
    this.credenceFillinTaShowDTO = credenceFillinTaShowDTO;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List getSummaylist() {
    return summaylist;
  }

  public void setSummaylist(List summaylist) {
    this.summaylist = summaylist;
  }

  public String getBookSt() {
    return bookSt;
  }

  public void setBookSt(String bookSt) {
    this.bookSt = bookSt;
  }

}
