package org.xpup.hafmis.syscollection.accountmng.accountchg.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.accountmng.accountchg.form.OrgChgAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
public class OrgChgShowAC extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      OrgChgAF af = (OrgChgAF) form; 
    Map orgchgtypeMap=BusiTools.listBusiProperty(BusiConst.ORGCHGTYPE);
     af.setOrgchgtypeMap(orgchgtypeMap);
      request.setAttribute("orgChgAF", af); 
      return mapping.findForward("to_org_cha_new");
    }
  }

