package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.action.ShowChgorgrateListAC;

/**
 * 
 * @author ÓÚÇì·á
 *2007-07-31
 */
public class ForwardUrlCollectionstatisticsListAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.action.ShowCollectionstatisticsListAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(PAGINATION_KEY, null);
    return mapping.findForward("to_showAC");
  }

}
