package org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.dto.DefineFormulaDto;

public class DefineFormulaFindAF extends ActionForm{

  private static final long serialVersionUID = 1L;
  private DefineFormulaDto defineFormulaDto=new DefineFormulaDto();
  public DefineFormulaDto getDefineFormulaDto() {
    return defineFormulaDto;
  }
  public void setDefineFormulaDto(DefineFormulaDto defineFormulaDto) {
    this.defineFormulaDto = defineFormulaDto;
  }
  
}
