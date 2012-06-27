package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTbFindDTO;

public class BankCheckAccTbAF extends ActionForm {
  private List list=new ArrayList();
  private BankCheckAccTbFindDTO bankCheckAccTbFindDTO=new BankCheckAccTbFindDTO();
  
  private FormFile theFile = null;//导入文件
  private String url="";
  private String office="";
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public FormFile getTheFile() {
    return theFile;
  }
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public BankCheckAccTbFindDTO getBankCheckAccTbFindDTO() {
    return bankCheckAccTbFindDTO;
  }
  public void setBankCheckAccTbFindDTO(BankCheckAccTbFindDTO bankCheckAccTbFindDTO) {
    this.bankCheckAccTbFindDTO = bankCheckAccTbFindDTO;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
}
