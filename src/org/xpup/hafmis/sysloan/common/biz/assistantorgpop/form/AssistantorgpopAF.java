package org.xpup.hafmis.sysloan.common.biz.assistantorgpop.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;
/**
 * 
 * @author yuqf
 *
 */
public class AssistantorgpopAF extends CriterionsAF{

  private String id = "";
  private String name = "";
  
  
  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    id = "";
    name = "";
    
  }
}
