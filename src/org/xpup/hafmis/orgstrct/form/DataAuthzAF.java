package org.xpup.hafmis.orgstrct.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.struts.action.ActionForm;
import org.xpup.security.common.domain.DaRelation;

public class DataAuthzAF extends ActionForm {

  private static final long serialVersionUID = -6748112581208341720L;

  private List daRelations = new ArrayList();

  private Serializable itemId = null;

  private List selects = new ArrayList();

  public Serializable getItemId() {
    return itemId;
  }

  public void setItemId(Serializable hafEmployeeId) {
    this.itemId = hafEmployeeId;
  }

  public List getDaRelationsSelected() {
    List selecteds = new ArrayList();
    for (int i = 0; i < daRelations.size(); i++) {
      if (selects.contains(new Integer(i))) {
        selecteds.add(daRelations.get(i));
      }
    }
    return selecteds;
  }

  public List getDaRelations() {
    return daRelations;
  }

  public void setDaRelations(List duRelations) {
    this.daRelations = duRelations;
  }

  public void setSelects(int index, String value) {
    selects.add(new Integer(index));
  }

  public void setQueryLevels(int index, String value) {
    DaRelation daRelation = (DaRelation) daRelations.get(index);
    daRelation.setQueryLevel(value);
  }

  public void setOperationLevels(int index, String value) {
    DaRelation daRelation = (DaRelation) daRelations.get(index);
    daRelation.setOperationLevel(value);
  }

  public String getDaRelationsHtml() {
    StrBuilder html = new StrBuilder();
    for (int i = 0; i < daRelations.size(); i++) {
      html.appendln(new DaRDecorator((DaRelation) daRelations.get(i))
          .getWholeHtml("selects[" + i + "]", "queryLevels[" + i + "]",
              "operationLevels[" + i + "]"));
    }
    return html.toString();
  }

}
