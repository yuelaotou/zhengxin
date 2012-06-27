package org.xpup.hafmis.demo.form;

import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.demo.domain.entity.Demo;


/**
 * 
 * @author ¡ı—Û
 *2007-5-31
 */
public class DemoAF extends ValidatorActionForm{
  private static final long serialVersionUID = 0L;
  private Demo demo=new Demo();
  private String id="";
  private String name="";
  private List list;
  public Demo getDemo() {
    return demo;
  }

  public void setDemo(Demo demo) {
    this.demo = demo;
  }

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

  public void reset(ActionMapping mapping, ServletRequest request) {
    this.id="";
    this.name="";
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
  
  
  
}
