package org.xpup.hafmis.syscollection.pickupmng.specialpickup.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.form.SpePickAF;

public class SpePickShowAC extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      SpePickAF af=(SpePickAF)form;
      
      request.setAttribute("spePickAF", af);
      
      return mapping.findForward("to_spe_pick_new");
    }
  }

