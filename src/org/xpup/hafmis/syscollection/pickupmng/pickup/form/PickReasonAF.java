package org.xpup.hafmis.syscollection.pickupmng.pickup.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class PickReasonAF extends ActionForm {

  private static final long serialVersionUID = 1L;

  private List somelist = new ArrayList();

  private List destroylist = new ArrayList();// ¸´Ñ¡

  private String[] somerowArray = new String[0];

  private String[] destroyrowArray = new String[0];

  public String[] getDestroyrowArray() {
    return destroyrowArray;
  }

  public void setDestroyrowArray(String[] destroyrowArray) {
    this.destroyrowArray = destroyrowArray;
  }

  public String[] getSomerowArray() {
    return somerowArray;
  }

  public void setSomerowArray(String[] somerowArray) {
    this.somerowArray = somerowArray;
  }

  public List getDestroylist() {
    return destroylist;
  }

  public void setDestroylist(List destroylist) {
    this.destroylist = destroylist;
  }

  public List getSomelist() {
    return somelist;
  }

  public void setSomelist(List somelist) {
    this.somelist = somelist;
  }

}
